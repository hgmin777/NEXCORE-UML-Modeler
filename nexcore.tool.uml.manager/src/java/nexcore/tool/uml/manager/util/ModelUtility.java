/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.manager.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import nexcore.tool.mdd.core.util.MDDCommonUtil;
import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.manager.utility.DomainRegistry;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.query.conditions.eobjects.EObjectCondition;
import org.eclipse.emf.query.conditions.eobjects.EObjectTypeRelationCondition;
import org.eclipse.emf.query.statements.FROM;
import org.eclipse.emf.query.statements.IQueryResult;
import org.eclipse.emf.query.statements.SELECT;
import org.eclipse.emf.query.statements.WHERE;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.manager</li>
 * <li>서브 업무명 : nexcore.tool.uml.manager.util</li>
 * <li>설  명 : ModelUtility</li>
 * <li>작성일 : 2010. 11. 16.</li>
 * <li>작성자 : 신동명 </li>
 * </ul>
 */
public class ModelUtility {

    /**
     * Constructor.
     */
    private ModelUtility() {
        super();
    }

    /**
     * 주석 복사
     * 
     * @param Element
     *            , Element
     * @return void
     */
    public static void copyDocumentation(Element tgtEle, Element srcEle) {
        if (tgtEle == null || srcEle == null) {
            return;
        }

        String documentation = getComment(srcEle);
        if (documentation != null && documentation.length() > 0) {
            setComment(tgtEle, documentation);
        }
    }

    /**
     * 주석 반환
     * 
     * @param useCase
     * @return String
     */
    protected static String getComment(Element ele) {
        EList cmtList = ele.getOwnedComments();

        if (cmtList.size() > 0) {
            Comment comment = null;
            StringBuffer cmtBuf = new StringBuffer();
            for (Iterator itr = cmtList.iterator(); itr.hasNext();) {
                comment = (Comment) itr.next();

                if (cmtBuf.length() > 0) {
                    cmtBuf.append("\n"); //$NON-NLS-1$
                }
                cmtBuf.append(comment.getBody());
            }
            return cmtBuf.toString();
        } else {
            return ManagerConstant.EMPTY_STRING;
        }
    }

    /**
     * 주석 생성
     * 
     * @param elmt
     * @param doc
     *            void
     */
    protected static void setComment(Element elmt, String doc) {
        Comment comment = null;
        boolean isNotExistSameComment = true;

        if (elmt.getOwnedComments().isEmpty()) {
            comment = elmt.createOwnedComment();
            comment.setBody(doc);
            Stereotype stereotype = comment.getApplicableStereotype("Default::Documentation"); //$NON-NLS-1$
            if (stereotype != null) {
                comment.applyStereotype(stereotype);
            }
        } else {
            // 2011-11-24 nspark
            // 문서 항목을 덮어쓰기 오류 개선
            // 문서 내용이 덮어써지지 않고 ownedComment 가 새로 생성되는 문제해결
//            for (Comment tempComment : elmt.getOwnedComments()) {
//                if (tempComment.getBody().equals(doc)) {
//                    isNotExistSameComment = false;
//                    break;
//                }
//            }
            
            if (!elmt.getOwnedComments().isEmpty()) {
                Comment tempComment = elmt.getOwnedComments().get(0);
                if (tempComment.getBody().equals(doc)) {
                    isNotExistSameComment = false;
                }
            }

            if (isNotExistSameComment) {
                if (elmt.getOwnedComments().isEmpty()) {
                    comment = elmt.createOwnedComment();
                } else {
                    comment = elmt.getOwnedComments().get(0);
                }
                comment.setBody(doc);
                Stereotype stereotype = comment.getApplicableStereotype("Default::Documentation"); //$NON-NLS-1$
                if (stereotype != null) {
                    comment.applyStereotype(stereotype);
                }
            }
        }
    }

    /**
     * getModel
     *  
     * @param elementList
     * @return Model
     */
    public static Model getModel(List elementList) {
        Model model = null;
        if (elementList != null) {
            for (Iterator i = elementList.iterator(); i.hasNext();) {
                Object oj = i.next();
                if (oj instanceof Package) {
                    Package pg = (Package) oj;
                    model = pg.getModel();
                }
            }
        }
        return model;
    }

    /**
     * getInterfaceByNameInComponent
     *  
     * @param comp
     * @param name
     * @return Interface
     */
    public static Interface getInterfaceByNameInComponent(Component comp, String name) {
        Interface result = null;
        Element ele = getElementByKindWithNameInAll(comp, UMLPackage.eINSTANCE.getInterface(), name);
        if (ele != null) {
            result = (Interface) ele;
        }
        return result;
    }

    /**
     * getPackageByName
     *  
     * @param model
     * @param pkg
     * @param name
     * @return Package
     */
    public static Package getPackageByName(Model model, Package pkg, String name) {
        Package result = null;
        Element ele = getElementByKindWithNameInAll(pkg, UMLPackage.eINSTANCE.getPackage(), name);
        if (ele != null)
            result = (Package) ele;
        return result;
    }

    /**
     * getPackageByName
     *  
     * @param model
     * @param name
     * @return Package
     */
    public static Package getPackageByName(Model model, String name) {
        Package result = null;
        Element ele = getElementByKindWithNameInAll(model, UMLPackage.eINSTANCE.getPackage(), name);
        if (ele != null)
            result = (Package) ele;
        return result;
    }

    /**
     * getPackageByName
     *  
     * @param pkg
     * @param name
     * @return Package
     */
    public static Package getPackageByName(Package pkg, String name) {
        Package result = null;
        Element ele = getElementByKindWithNameInAll(pkg, UMLPackage.eINSTANCE.getPackage(), name);
        if (ele != null)
            result = (Package) ele;
        return result;
    }

    /**
     * getCollaborationByName
     *  
     * @param pkg
     * @param name
     * @return Collaboration
     */
    public static Collaboration getCollaborationByName(Package pkg, String name) {
        Collaboration result = null;
        Element ele = getElementByKindWithNameInAll(pkg, UMLPackage.eINSTANCE.getCollaboration(), name);
        if (ele != null)
            result = (Collaboration) ele;
        return result;
    }

    /**
     * getInterfaceByName
     *  
     * @param model
     * @param name
     * @return Interface
     */
    public static Interface getInterfaceByName(Model model, String name) {
        Interface result = null;
        Element ele = getElementByKindWithNameInAll(model, UMLPackage.eINSTANCE.getInterface(), name);
        if (ele != null)
            result = (Interface) ele;
        return result;
    }

    /**
     * getInterfaceByName
     *  
     * @param pkg
     * @param name
     * @return Interface
     */
    public static Interface getInterfaceByName(Package pkg, String name) {
        Interface result = null;
        Element ele = getElementByKindWithNameInAll(pkg, UMLPackage.eINSTANCE.getInterface(), name);
        if (ele != null)
            result = (Interface) ele;
        return result;
    }

    /**
     * getClassByName
     *  
     * @param pkg
     * @param name
     * @return Class
     */
    public static Class getClassByName(Package pkg, String name) {
        Class result = null;
        Element ele = getElementByKindWithNameInAll(pkg, UMLPackage.eINSTANCE.getClass_(), name);
        if (ele != null)
            result = (Class) ele;
        return result;
    }

    /**
     * getClassByNameInComponent
     *  
     * @param comp
     * @param name
     * @return Class
     */
    public static Class getClassByNameInComponent(Component comp, String name) {
        Class result = null;
        Element ele = getElementByKindWithNameInAll(comp, UMLPackage.eINSTANCE.getComponent(), name);
        if (ele != null)
            result = (Class) ele;
        return result;
    }

    /**
     * getClassByName
     *  
     * @param model
     * @param name
     * @return Class
     */
    public static Class getClassByName(Model model, String name) {
        Class result = null;
        Element ele = getElementByKindWithNameInAll(model, UMLPackage.eINSTANCE.getClass_(), name);
        if (ele != null)
            result = (Class) ele;
        return result;
    }

    /**
     * getComponentByName
     *  
     * @param pkg
     * @param name
     * @return Component
     */
    public static Component getComponentByName(Package pkg, String name) {
        Component result = null;
        Element ele = getElementByKindWithNameInAll(pkg, UMLPackage.eINSTANCE.getComponent(), name);
        if (ele != null)
            result = (Component) ele;
        return result;
    }

    /**
     * getComponentByName
     *  
     * @param model
     * @param name
     * @return Component
     */
    public static Component getComponentByName(Model model, String name) {
        Component result = null;
        Element ele = getElementByKindWithNameInAll(model, UMLPackage.eINSTANCE.getComponent(), name);
        if (ele != null)
            result = (Component) ele;
        return result;
    }

    /**
     * getPackageByName
     *  
     * @param comp
     * @param name
     * @return Package
     */
    public static Package getPackageByName(Component comp, String name) {
        Package result = null;
        Element ele = getElementByKindWithNameInAll(comp, UMLPackage.eINSTANCE.getPackage(), name);
        if (ele != null)
            result = (Package) ele;
        return result;
    }

    /**
     * getPackageByNameAlways
     *  
     * @param comp
     * @param name
     * @return Package
     */
    public static Package getPackageByNameAlways(Component comp, String name) {
        Package result = null;
        Element ele = getElementByKindWithNameInAll(comp, UMLPackage.eINSTANCE.getPackage(), name);
        if (ele != null)
            result = (Package) ele;
        else {
            EClass eClass = UMLFactory.eINSTANCE.getUMLPackage().getPackage();
            PackageableElement newPackage = comp.createPackagedElement(name, eClass);
            result = (Package) newPackage;
        }
        return result;
    }

    /**
     * getPackageByNameAlways
     *  
     * @param pkg
     * @param name
     * @return Package
     */
    public static Package getPackageByNameAlways(Package pkg, String name) {
        Package result = null;
        Element ele = getElementByKindWithNameInAll(pkg, UMLPackage.eINSTANCE.getPackage(), name);
        if (ele != null)
            result = (Package) ele;
        else {
            EClass eClass = UMLFactory.eINSTANCE.getUMLPackage().getPackage();
            PackageableElement newPackage = pkg.createPackagedElement(name, eClass);
            result = (Package) newPackage;
        }
        return result;
    }

    /**
     * 모델의 컬레보레이션 목록 가져오기
     * 
     * @param model
     * @return List<Element>
     */
    public static List<Element> getCollaborationList(Model model) {
        return getListByKindInAll(model, UMLPackage.eINSTANCE.getCollaboration());
    }

    /**
     * Checks if a class contains the same name operation
     * 
     * @param obj
     *            class or interface
     * @param op
     *            The operation
     * @return True if the class contains the operation.
     */
    public static Operation getSameNameOperation(Object obj, String opName) {
        Operation foundOperation = null;
        if (opName == null || obj == null)
            return null;

        Iterator iter = null;
        if (obj instanceof Class) {
            Class cls = (Class) obj;
            iter = cls.getOperations().iterator();
        } else if (obj instanceof Interface) {
            Interface inter = (Interface) obj;
            iter = inter.getOperations().iterator();
        }

        while (foundOperation == null && iter.hasNext()) {
            Operation ownedOp = (Operation) iter.next();
            if (opName.equals(ownedOp.getName()))
                foundOperation = ownedOp;
        }
        return foundOperation;
    }

    /**
     * Checks if a class contains the same name operation
     * 
     * @param cls
     *            The class to be searched for the presence of the specified
     *            operation.
     * @param op
     *            The operation
     * @return True if the class contains the operation.
     */
    public static boolean hasSameNameOperation(Class cls, String opName) {
        boolean foundOperation = false;
        if (opName == null || cls == null)
            return false;
        Iterator iter = cls.getOwnedOperations().iterator();
        while (foundOperation == false && iter.hasNext()) {
            Operation ownedOp = (Operation) iter.next();
            if (opName.equals(ownedOp.getName()))
                foundOperation = true;
        }
        return foundOperation;
    }

    /**
     * Checks if a class contains the specified operation
     * 
     * @param cls
     *            The class to be searched for the presence of the specified
     *            operation.
     * @param op
     *            The operation
     * @return True if the class contains the operation.
     */
    public static boolean hasSameOperation(Class cls, Operation op) {
        boolean foundOperation = false;
        Iterator iter = cls.getOwnedOperations().iterator();
        while (foundOperation == false && iter.hasNext()) {
            Operation ownedOp = (Operation) iter.next();
            foundOperation = areOperationsEqual(ownedOp, op);
        }
        return foundOperation;
    }

    /**
     * Checks if a interface contains the specified operation
     * 
     * @param cls
     *            The interface to be searched for the presence of the specified
     *            operation.
     * @param op
     *            The operation
     * @return True if the interface contains the operation.
     */
    public static boolean interfaceContainsOperation(Interface intface, Operation op) {
        boolean foundOperation = false;
        Iterator iter = intface.getOwnedOperations().iterator();
        while (foundOperation == false && iter.hasNext()) {
            Operation ownedOp = (Operation) iter.next();
            foundOperation = areOperationsEqual(ownedOp, op);
        }
        return foundOperation;
    }

    /**
     * Checks if two operations are equal.
     * 
     * Two operations are defined to be equal if they have the same name and the
     * same signature.
     * 
     * @param op1
     *            Operation 1
     * @param op2
     *            Operation 2
     * @return True if the operations have the same name and signature.
     */
    public static boolean areOperationsEqual(Operation op1, Operation op2) {
        return (op1.getName().equals(op2.getName())) && haveSameSignature(op1, op2);
    }

    /**
     * Checks if two operations have the same signature.
     * 
     * Two operations are defined to have the same signature if the parameter
     * lists of the operations have the same number of parameters, the
     * parameters appear in the lists in the same order, and each parameter in
     * one operation has the same signature as the corresponding parameter of
     * the second operation. Note that the return parameter does not contribute
     * to the signature of an operation.
     * 
     * @param op1
     *            First operation
     * @param op2
     *            Second operation
     * @return True if the operations have the same signature
     */
    public static boolean haveSameSignature(Operation op1, Operation op2) {
        boolean isSame = false;

        // To ignore return parameters, create a list of non-return parameters
        // for each operation
        List op1Parameters = getNonReturnParameters(op1);
        List op2Parameters = getNonReturnParameters(op2);

        if (op1Parameters.size() == op2Parameters.size()) {
            Iterator op1paramIter = op1Parameters.iterator();
            Iterator op2paramIter = op2Parameters.iterator();

            isSame = true;

            while (op1paramIter.hasNext() && isSame == true) {
                isSame = haveSameSignature((Parameter) op1paramIter.next(), (Parameter) op2paramIter.next());
            }
        }

        return isSame;
    }

    /**
     * Checks if two parameters have the same signature.
     * 
     * Two parameters are defined to have the same signature if they are of the
     * same type and they have the same direction. The names of the parameters
     * are ignored because in the context of an operation the names do not
     * determine the signature of an operation.
     * 
     * Also, if the types of the parameters are unspecified (that is if
     * getType() for each parameter is <code>null</code>, the parameters are
     * defined to have the same signature only if their directions are the same.
     * 
     * @param p1
     *            First parameter
     * @param p2
     *            Second parameter
     * @return True if the parameters have the same types and direction
     */
    public static boolean haveSameSignature(Parameter p1, Parameter p2) {
        boolean isSame = false;
        Type type1 = p1.getType();
        Type type2 = p2.getType();

        if (p1.getDirection() == p2.getDirection()) {
            if (type1 == null && type2 == null) {
                isSame = true;
            } else if (type1 != null && type2 != null) {
                isSame = type1.eClass() == type2.eClass();
            }
        }
        return isSame;
    }

    /**
     * Creates a list of non-return (in, out, and in-out) parameters of an
     * operation.
     * 
     * @param op
     *            The specified operation
     * @return List of non-return parameters.
     */
    private static List getNonReturnParameters(Operation op) {
        ArrayList plist = new ArrayList();
        Iterator iter = op.getOwnedParameters().iterator();
        while (iter.hasNext()) {
            Parameter p = (Parameter) iter.next();
            if (p.getDirection() != ParameterDirectionKind.RETURN_LITERAL) {
                plist.add(p);
            }
        }
        return plist;
    }

    /**
     * Searches a given class for a property with the specified name
     * 
     * @param cls
     *            The source class
     * @param name
     *            The name of the property
     * @return Returns the property, if present. Otherwise, returns null.
     */
    public static Property getPropertyByName(Class cls, String name) {
        return (Property) getElementByKindWithName(cls, UMLPackage.eINSTANCE.getProperty(), name);
    }

    /**
     * Gets a realization from a class.
     * 
     * Given a realization client and a realizing classifier, this method
     * retrieves a realization from the client. If the realization is not
     * present in the client, it returns a null.
     * 
     * @param realizationClient
     *            Realization client
     * @param realizingClassifier
     *            Realizing classifier - in this case an interface
     * @return A realization if present. Otherwise, returns a null.
     */

    public static InterfaceRealization getRealization(Class realizationClient, Interface realizingClassifier) {

        return realizationClient.getInterfaceRealization(null, realizingClassifier);
    }

    /**
     * 현재 엘리먼트 바로 아래 속해있는 엘리먼트를 대상으로 종류가 같고, 이름이 같은 엘리먼트 검색
     * 
     * @param src
     * @param elementKind
     * @param name
     * @return NamedElement
     */
    private static NamedElement getElementByKindWithName(NamedElement src, EClass elementKind, String name) {

        NamedElement retElement = null;
        if (src != null && name != null) {
            NamedElement element = null;
            EList elements = src.eContents();
            Object obj = null;
            for (Iterator itr = elements.iterator(); itr.hasNext();) {
                obj = itr.next();
                if (obj instanceof NamedElement) {
                    element = (NamedElement) obj;
                    if (elementKind == element.eClass() && name.equals(element.getName())) {
                        retElement = element;
                        break;
                    }
                }
            }
        }
        return retElement;
    }

    /**
     * 현재 엘리먼트 바로 아래 속해있는 이름이 같은 엘리먼트 검색
     * 
     * @param src
     * @param name
     * @return NamedElement
     */
    private static NamedElement getElementByName(NamedElement src, String name) {

        NamedElement retElement = null;
        if (src != null && name != null) {
            NamedElement element = null;
            EList elements = src.eContents();
            for (Iterator i = elements.iterator(); i.hasNext();) {
                Object obj = i.next();
                if (obj instanceof NamedElement) {
                    element = (NamedElement) obj;
                    if (name.equals(element.getName())) {
                        retElement = element;
                        break;
                    }
                }
            }
        }
        return retElement;
    }

    /**
     * 현재 엘리먼트 아래 속해있는 모든 엘리먼트를 대상으로 종류가 같은 엘리먼트 리스트 반환
     * 
     * @param src
     * @param elementKind
     * @return List<Element>
     */
    public static List<Element> getListByKindInAll(NamedElement src, EClass elementKind) {
        List<Element> retList = null;

        if (src != null) {
            NamedElement element = null;
            retList = new ArrayList<Element>();

            EList<Element> elements = src.allOwnedElements();

            for (Iterator<Element> i = elements.iterator(); i.hasNext();) {
                Object obj = i.next();

                if (obj instanceof NamedElement) {
                    element = (NamedElement) obj;

                    if (elementKind == element.eClass()) {
                        retList.add(element);
                    }
                }
            }
        }

        return retList;
    }

    /**
     * 현재 엘리먼트 아래 속해있는 모든 엘리먼트를 대상으로 종류가 같고, 이름이 같은 엘리먼트 검색
     * 
     * @param src
     * @param elementKind
     * @param name
     * @return Element
     */
    private static Element getElementByKindWithNameInAll(NamedElement src, EClass elementKind, String name) {

        Element retElement = null;
        if (src != null && name != null) {
            NamedElement element = null;
            EList elements = src.allOwnedElements();
            for (Iterator i = elements.iterator(); i.hasNext();) {
                Object obj = i.next();
                if (obj instanceof NamedElement) {
                    element = (NamedElement) obj;
                    if (elementKind == element.eClass() && name.equals(element.getName())) {
                        retElement = element;
                        break;
                    }
                }
            }
        }
        return retElement;
    }

    /**
     * 현재 엘리먼트 아래 속해있는 모든 엘리먼트를 대상으로 이름이 같은 엘리먼트 검색
     * 
     * @param src
     * @param name
     * @return Element
     */
    private static Element getElementByNameInAll(NamedElement src, String name) {

        Element retElement = null;
        if (src != null && name != null) {
            NamedElement element = null;
            EList elements = src.allOwnedElements();
            for (Iterator i = elements.iterator(); i.hasNext();) {
                Object obj = i.next();
                if (obj instanceof NamedElement) {
                    element = (NamedElement) obj;
                    if (name.equals(element.getName())) {
                        retElement = element;
                        break;
                    }
                }
            }
        }
        return retElement;
    }

    /**
     * Gets the name of the given model element.
     * 
     * It assumes that the given element is an instance of NamedElement or one
     * of its sub-classes. If the element is not an instance of NamedElement, an
     * empty string is returned.
     * 
     * @param element
     *            The model element. Should not be <code>null</code>.
     * @return Name of the element if it is an instance of NamedElement.
     *         Otherwise, an empty string is returned.
     */
    public static String getElementName(Object element) {
        String name = new String(ManagerConstant.EMPTY_STRING); //$NON-NLS-1$
        if (element != null && element instanceof NamedElement) {
            name = ((NamedElement) element).getName();
        }
        return name;
    }

    /**
     * Creates a "getter" UML operation for a UML property.
     * 
     * @param srcProperty
     *            The UML property
     * @return The "getter" operation
     */
    public static Operation createGetter(Property srcProperty) {
        Operation getter = UMLFactory.eINSTANCE.createOperation();
        getter.setName("get_" + srcProperty.getName()); //$NON-NLS-1$

        getter.createReturnResult(srcProperty.getName(), srcProperty.getType());

        return getter;
    }

    /**
     * Creates a "setter" UML operation for a UML property.
     * 
     * @param srcProperty
     *            The UML property
     * @return The "setter" operation
     */
    public static Operation createSetter(Property srcProperty) {
        Operation setter = UMLFactory.eINSTANCE.createOperation();
        setter.setName("set_" + srcProperty.getName()); //$NON-NLS-1$

        Parameter setParm = setter.createOwnedParameter(srcProperty.getName(), srcProperty.getType());
        setParm.setDirection(ParameterDirectionKind.IN_LITERAL);

        return setter;
    }

    /**
     * Creates new UML operation which is a shallow copy of the specified source
     * operation. It copies the name and the visibility of the source operation
     * as well as the parameters. For the parameters, it copies the name, type
     * and direction of the parameters of the source operation.
     * 
     * @param srcOp
     *            Source operation
     * @return the copy of the operation
     */
    public static Operation createCopyOfOperation(Operation srcOp) {
        Operation targetOp = UMLFactory.eINSTANCE.createOperation();
        targetOp.setName(srcOp.getName());
        targetOp.setVisibility(srcOp.getVisibility());

        // EList srcParameters = srcOp.getOwnedParameters();
        EList srcParameters = srcOp.getOwnedMembers();
        for (Iterator piter = srcParameters.iterator(); piter.hasNext();) {
            Object element = piter.next();
            if (element instanceof Parameter) {
                Parameter srcParm = (Parameter) element;
                Parameter targetParm = targetOp.createOwnedParameter(srcParm.getName(), srcParm.getType());
                targetParm.setDirection(srcParm.getDirection());
            }
        }

        return targetOp;
    }

    /**
     * HierarchyName 에서 원래 이름을 추출한다.
     * 
     * @param hrchName
     * @return String
     */
    public static String extractNameFromHierarchyName(String hrchName) {

        String nameArray[] = hrchName.split(ManagerConstant.SHARP);

        if (nameArray.length > 0) {
            return nameArray[0];
        } else {
            return hrchName;
        }
    }

    /**
     * getHierarchyName
     *  
     * @param collabo
     * @return String
     */
    public static String getHierarchyName(Collaboration collabo) {
        return getHierarchyName(collabo.getName(), collabo.getNamespace().getName(), collabo.getNearestPackage());
    }

    /**
     * getHierarchyName
     *  
     * @param intact
     * @return String
     */
    public static String getHierarchyName(Interaction intact) {
        return getHierarchyName(intact.getName(), intact.getNamespace().getName(), intact.getNearestPackage());
    }

    /**
     * 상위패키지이름을 열거한 이름을 만든다. (Unique한 이름 생성)
     * 
     * @param elementName
     * @param namespaceName
     * @param nearestPackage
     * @return String
     */
    public static String getHierarchyName(String elementName, String namespaceName, Package nearestPackage) {
        StringBuffer nameBuf = new StringBuffer();
        nameBuf.append(elementName);
        nameBuf.append(ManagerConstant.SHARP);
        nameBuf.append(namespaceName);
        Package nestingPkg = nearestPackage;
        Package prvPkg = null;
        Component comp = null;

        for (int i = 0; i < 10; i++) {
            if (nestingPkg == null) {
                if (prvPkg.getOwner() instanceof Component) {
                    comp = (Component) prvPkg.getOwner();
                    nameBuf.append(comp.getName());
                    nestingPkg = comp.getNearestPackage();
                } else {
                    break;
                }
            } else {
                if (nestingPkg.getName().toLowerCase().equals(nestingPkg.getModel().getName().toLowerCase())) {
                    break;
                } else {
                    /*
                     * if(nestingPkg.getName().replaceAll(UICoreConstant.PROJECT_CONSTANTS__BLANK
                     * ,
                     * UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING).toUpperCase
                     * ().equals(ModelPropertyManager.getInstance().
                     * getUsecaseRealizationPackage
                     * ().toUpperCase().replaceAll(UICoreConstant
                     * .PROJECT_CONSTANTS__BLANK,
                     * UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING))) {
                     * nameBuf.append(ModelPropertyManager.getInstance().
                     * getUsecaseRealizationPackage()); } else {
                     */
                    nameBuf.append(nestingPkg.getName());
                    /*
                     * }
                     */
                    prvPkg = nestingPkg;
                    nestingPkg = nestingPkg.getNestingPackage();
                }
            }
        }
        return nameBuf.toString();
    }
    
    /**
     * get the operation of the message.
     * 
     * @param msg
     * @return
     */
    public static Operation getMessageOperation(Message msg) {
        if (msg != null && msg.getSignature() != null && msg.getSignature() instanceof Operation) {
            return (Operation) msg.getSignature();
        }
        return null;
    }

    /**
     * 상위에 가장 가까운 컴포넌트를 반환
     * 
     * @param inNestingPkg
     * @return String
     */
    public static NamedElement getNearstComponent(Package inNestingPkg) {
        Package nestingPkg = inNestingPkg;
        Package prvPkg = null;
        for (int i = 0; i < 20; i++) {
            if (nestingPkg == null) {
                // 분석모델에서의 Component여부 확인
                if (prvPkg != null && prvPkg.getOwner() instanceof Component) {
                    return (NamedElement) prvPkg.getOwner();
                } else {
                    break;
                }
            } else {
                // 설계모델에서의 Component여부 확인
                /*
                 * if(nestingPkg.hasKeyword(ModelPropertyManager.getInstance().getControl
                 * ().getComponentStereotype())) { return
                 * (NamedElement)nestingPkg; } else
                 */
                if (nestingPkg.getName().toLowerCase().equals(nestingPkg.getModel().getName().toLowerCase())) {
                    break;
                } else {
                    prvPkg = nestingPkg;
                    nestingPkg = nestingPkg.getNestingPackage();
                }
            }
        }
        return null;
    }

    /**
     * 현재 패키지가 Component소속인지 여부
     * 
     * @param inNestingPkg
     * @return boolean
     */
    public static boolean isComponentBranch(Package inNestingPkg) {
        Package nestingPkg = inNestingPkg;
        Package prvPkg = null;
        for (int i = 0; i < 20; i++) {
            if (nestingPkg == null) {
                // 분석모델에서의 Component여부 확인
                if (prvPkg != null && prvPkg.getOwner() instanceof Component) {
                    return true;
                } else {
                    break;
                }
            } else {
                // 설계모델에서의 Component여부 확인
                /*
                 * if(nestingPkg.hasKeyword(ModelPropertyManager.getInstance().getControl
                 * ().getComponentStereotype())) { return true; } else
                 */
                if (nestingPkg.getName().toLowerCase().equals(nestingPkg.getModel().getName().toLowerCase())) {
                    break;
                } else {
                    prvPkg = nestingPkg;
                    nestingPkg = nestingPkg.getNestingPackage();
                }
            }
        }
        return false;
    }

    /**
     * isComponentBranch
     *  
     * @param inClass
     * @return boolean
     */
    public static boolean isComponentBranch(org.eclipse.uml2.uml.Class inClass) {
        return isComponentBranch(inClass.getNearestPackage());
    }

    /**
     * isComponentBranch
     *  
     * @param inInterface
     * @return boolean
     */
    public static boolean isComponentBranch(org.eclipse.uml2.uml.Interface inInterface) {
        return isComponentBranch(inInterface.getNearestPackage());
    }

    /**
     * 타입객체가 컴포넌트 아래 속해 있는지 여부
     * 
     * @param inType
     * @return boolean
     */
    public static boolean isComponentBranch(org.eclipse.uml2.uml.Type inType) {
        if (inType instanceof org.eclipse.uml2.uml.Class) {
            return isComponentBranch((org.eclipse.uml2.uml.Class) inType);
        } else if (inType instanceof org.eclipse.uml2.uml.Interface) {
            return isComponentBranch((org.eclipse.uml2.uml.Interface) inType);
        } else {
            return false;
        }
    }

    /**
     * 타입이 같으면서 fullname에 해당하는 객체 반환.
     * (예 : 'nexcore.framework.online.channel.core.command.StdCommand')
     * 
     * @param model
     * @param elementKind
     * @param fullName
     * @return NamedElement
     */
    public static NamedElement getElementByFullPackageName(Model model, EClass elementKind, String fullName) {
    	
    	if (model != null && fullName != null) {
    		String dottedName = ManagerConstant.EMPTY_STRING;
    		NamedElement namedElement = null;
    		EList<Element> elements = model.allOwnedElements();
    		for (Element element : elements) {
				if (elementKind.isSuperTypeOf(element.eClass())) {
					namedElement = (NamedElement) element;
					dottedName = MDDCommonUtil.getMappedName(namedElement.getQualifiedName());
					if (fullName.equals(dottedName)) {
						return namedElement;
					}
				}
			}
    	}
    	return null;
    	

//        NamedElement retElement = null;
//        if (model != null && fullName != null) {
//            String pkgArray[] = fullName.split("\\."); //$NON-NLS-1$
//
//            if (pkgArray.length > 1) {
//
//                Package pkg = getPackageByName(model, pkgArray[0]);
//
//                if (pkgArray.length > 2) {
//                    for (int i = 1; i < pkgArray.length - 1; i++) {
//                        if (pkg != null) {
//                            pkg = pkg.getNestedPackage(pkgArray[i]);
//                        }
//                    }
//                }
//                if (pkg != null && pkgArray != null) {
//                    retElement = pkg.getOwnedMember(pkgArray[pkgArray.length - 1]);
//                }
//            } else {
//                retElement = getElementByKindWithName(model, elementKind, fullName);
//            }
//        }
//
//        return retElement;
    }

    /**
     * 풀패키지명에서 클래스명 반환.
     * (예 : nexcore.framework.model.util.ModelUtility 에서 ModelUtility만 반환)
     * 
     * @param fullName
     * @return String
     */
    public static String extractClassName(String fullName) {
        if (fullName == null) {
            return ManagerConstant.EMPTY_STRING;
        }

        String nameArray[] = fullName.split("\\."); //$NON-NLS-1$

        if (nameArray.length > 0) {
            return nameArray[nameArray.length - 1];
        } else {
            return fullName;
        }
    }

    /**
     * 라이프라인의 인스턴스명 반환
     * 
     * @param lifeLineName
     * @return String
     */
    public static String getLifelineInstanceName(String lifeLineName) {
        if (lifeLineName != null) {
            if (lifeLineName.length() <= 1) {
                return lifeLineName;
            } else {
                return lifeLineName.substring(0, 1).toLowerCase() + lifeLineName.substring(1);
            }
        } else {
            return ManagerConstant.EMPTY_STRING;
        }
    }

    /**
     * getFullPackageName
     *  
     * @param nearestPackage
     * @return String
     */
    public static String getFullPackageName(Package nearestPackage) {
        return getFullPackageName(nearestPackage, ManagerConstant.SLASH, false);
    }

    /**
     * getFullPackageNameUsingDot
     *  
     * @param nearestPackage
     * @return String
     */
    public static String getFullPackageNameUsingDot(Package nearestPackage) {
        return getFullPackageName(nearestPackage, ManagerConstant.DOT, false);
    }

    /**
     * 풀패키지명을 반환
     * 
     * @param nearestPackage
     *            : 가까운 패키지
     * @param delimiter
     *            : 구분자
     * @param bUcaseRealInclude
     *            : 풀패키지명에서 Usecase Realization명도 포함할 것인지 여부
     * @return String
     */
    public static String getFullPackageName(Package nearestPackage, String delim, boolean bUcaseRealInclude) {
        StringBuffer nameBuf = new StringBuffer();
        Package nestingPkg = nearestPackage;
        Package prvPkg = null;
        Component comp = null;

        for (int i = 0; i < 20; i++) {
            if (nestingPkg == null) {
                if (prvPkg.getOwner() instanceof Component) {
                    comp = (Component) prvPkg.getOwner();
                    if (nameBuf.length() > 0) {
                        nameBuf.insert(0, delim);
                    }
                    nameBuf.insert(0, comp.getName());
                    nestingPkg = comp.getNearestPackage();
                } else {
                    break;
                }
            } else {
                if (nestingPkg.getName().toLowerCase().equals(nestingPkg.getModel().getName().toLowerCase())) {
                    break;
                } else {
                    /*
                     * if(nestingPkg.getName().replaceAll(UICoreConstant.PROJECT_CONSTANTS__BLANK
                     * ,
                     * UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING).toUpperCase
                     * ().equals(ModelPropertyManager.getInstance().
                     * getUsecaseRealizationPackage
                     * ().toUpperCase().replaceAll(UICoreConstant
                     * .PROJECT_CONSTANTS__BLANK,
                     * UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING))) {
                     * if(bUcaseRealInclude) { if(nameBuf.length() > 0) {
                     * nameBuf.insert(0, delim); } nameBuf.insert(0,
                     * nestingPkg.getName()); } break; } else {
                     */
                    if (nameBuf.length() > 0) {
                        nameBuf.insert(0, delim);
                    }
                    nameBuf.insert(0, nestingPkg.getName());
                    /*
                     * }
                     */
                    prvPkg = nestingPkg;
                    nestingPkg = nestingPkg.getNestingPackage();
                }
            }
        }
        return nameBuf.toString();
    }

    /**
     * copyAncestorStructure
     *  
     * @param model
     * @param pkg void
     */
    public static void copyAncestorStructure(Model model, Package pkg) {

    }

    /**
     * listToString
     *  
     * @param inList
     * @param delimeter
     * @return String
     */
    public static String listToString(List inList, String delimeter) {
        if (inList == null)
            return null;
        StringBuffer stBuf = new StringBuffer();
        for (Iterator itr = inList.iterator(); itr.hasNext();) {
            if (stBuf.length() > 0) {
                stBuf.append(delimeter);
            }
            stBuf.append((itr.next()).toString());
        }
        return stBuf.toString();
    }

    /**
     * 글자에 한글이 포함되었는지 여부 판단
     * 
     * @param word
     * @return boolean
     */
    public static boolean isHangulInclude(String word) {

        if (word == null) {
            return false;
        }

        char c = 1;
        for (int j = 0; j < word.length(); j++) {
            c = word.charAt(j);
            if (c >= 0xac00 && c <= 0xd7a3) {
                return true;
            }
        }

        return false;
    }

    /**
     * 현재 년월일시간 반환
     * 
     * @return String
     */
    public static String getSysDateTime() {
        SimpleDateFormat dt = new SimpleDateFormat("yyyyMMddHHmmss"); //$NON-NLS-1$
        java.util.Date curTime = new java.util.Date();
        return dt.format(curTime);
    }

    /**
     * monitorWorked
     *  
     * @param monitor
     * @param workcnt void
     */
    public static void monitorWorked(IProgressMonitor monitor, int workcnt) {
        try {
            for (int i = 0; i < workcnt; i++) {
                monitor.worked(1);
                java.lang.Thread.sleep(100);
            }
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    /**
     * snapshotIterator
     *  
     * @param collection
     * @return Iterator
     */
    public static Iterator snapshotIterator(Collection collection) {
        return new ArrayList(collection).iterator();
    }

    /**
     * 프로그램 실행 전 검사
     * 
     * @return boolean
     */
    public static boolean checkValid() {
        // TODO UMLModeler.getUMLUIHelper().getSelectedElements() 구현 해야함
        // List elements = UMLModeler.getUMLUIHelper().getSelectedElements();
        // if(elements != null && elements.size() > 1) {
        // MessageDialog.openInformation(null, MDAMessage.LABEL_NOTIFICATION,
        // Messages.getString("NexcoreMda_DIALOG_MSG_RUN_NEEDONESEL"));
        // return false;
        // }
        // 
        // Object selObj = elements.get(0);
        // if(selObj != null && !(selObj instanceof
        // org.eclipse.uml2.uml.Package)) {
        // MessageDialog.openInformation(null, MDAMessage.LABEL_NOTIFICATION,
        // Messages.getString("NexcoreMda_DIALOG_MSG_RUN_NEEDSELPKG"));
        // return false;
        // }
        return true;
    }

    /**
     * 특정 패키지 내의 자식 요소들이 가지는 스테레오타입 이름 목록 반환
     * 
     * @param sourcePackage
     * @return List<String>
     */
    public static List<String> getAppliedStereotypeNameListOfChildClass(Package sourcePackage) {
        List<String> appliedStereotypeNameList = new ArrayList<String>();

        if (sourcePackage != null) {
            NamedElement element = null;
            Element obj = null;

            EList<Element> elements = sourcePackage.getOwnedElements();
            for (Iterator<Element> i = elements.iterator(); i.hasNext();) {
                obj = i.next();

                if (obj instanceof NamedElement) {
                    element = (NamedElement) obj;

                    if (element.eClass() == UMLPackage.eINSTANCE.getClass_()) {
                        for (Stereotype stereotype : element.getAppliedStereotypes()) {
                            appliedStereotypeNameList.add(stereotype.getName());
                        }
                    }
                }
            }
        }

        return appliedStereotypeNameList;
    }

    /**
     * 패키지 내의 특정 스테레오타입이 적용된 요소만 반환
     * 
     * @param specificSourceClassList
     * @param owner
     * @param operationReferenceDataType
     * @return List<Class>
     */
    public static List<Class> getSpecificSourceClassList(List<Class> specificClassList, Element owner,
                                                         String operationReferenceDataType) {
        Package parent = (Package) owner;
        Element obj = null;
        NamedElement element = null;
        List<Class> specificSourceClassList = specificClassList;

        EList<Element> elements = parent.getOwnedElements();
        for (Iterator<Element> i = elements.iterator(); i.hasNext();) {
            obj = i.next();

            if (obj instanceof NamedElement) {
                element = (NamedElement) obj;

                if (element.eClass() == UMLPackage.eINSTANCE.getPackage()) {
                    getSpecificSourceClassList(specificSourceClassList, element, operationReferenceDataType);
                } else if (element.eClass() == UMLPackage.eINSTANCE.getClass_()) {
                    for (Stereotype stereotype : element.getAppliedStereotypes()) {
                        if (stereotype.getName().equals(operationReferenceDataType)) {
                            specificSourceClassList.add((Class) element);
                            break;
                        }
                    }
                }
            }
        }

        return specificSourceClassList;
    }
    
    /**
     * sourceElement의 키워드를 targetElement로 복사한다.
     * 
     * @param sourceElement
     * @param targetElement
     */
    public static void copyKeywords(Element sourceElement, Element targetElement) {
    	
    	if (sourceElement == null || targetElement == null) {
            return;
        }
    	
    	List<String> sourceKeywords = sourceElement.getKeywords();
    	List<String> targetKeywords = targetElement.getKeywords();
    	if (targetKeywords == null || targetKeywords.size() < 1) {
    		for (String keyword : sourceKeywords) {
    			targetElement.addKeyword(keyword);
    		}
    	} else {
    		for (String keyword : sourceKeywords) {
    			if (!targetKeywords.contains(keyword)) {
    				targetElement.addKeyword(keyword);
    			}
    		}
    	}
    }

    /**
     * 
     * 
     *  
     * @param type UMLPackage.eINSTANCE.getUseCase() , UMLPackage.eINSTANCE.getClass_()
     * @return List<Element>
     */
    public static List<Element> searchTypeElement(EClass type) {
        List<Element> elements = new ArrayList<Element>();
        ResourceSet resourceSet = DomainRegistry.getUMLDomain().getResourceSet();
        Iterator<Resource> ir = resourceSet.getResources().iterator();
        while (ir.hasNext()) {
            Resource resource = ir.next();

            SELECT statement = new SELECT(new FROM(resource.getContents()),
                new WHERE(new EObjectTypeRelationCondition(type)));
            IQueryResult classList = statement.execute();

            for (Iterator<?> ir2 = classList.iterator(); ir2.hasNext();) {
                Element eObject = (Element) ir2.next();

                if (!elements.contains(eObject)) {
                    elements.add(eObject);
                }
            }
        }

        return elements;
    }
    
    /**
     * 
     * 
     *  
     * @param eClasses
     * @return List<Element>
     */
    public static List<Element> searchTypeElement(EClass[] eClasses) {
        List<Element> elements = new ArrayList<Element>();
        ResourceSet resourceSet = DomainRegistry.getUMLDomain().getResourceSet();
        Iterator<Resource> ir = resourceSet.getResources().iterator();
        
        EObjectCondition condition = null;
        
        for(EClass eClass : eClasses) {

            if (condition == null) {
                condition = new EObjectTypeRelationCondition(eClass);
            } else {
                condition = condition.OR(new EObjectTypeRelationCondition(eClass));
            }
        }
        
        while (ir.hasNext()) {
            Resource resource = ir.next();

            SELECT statement = new SELECT(new FROM(resource.getContents()),
                new WHERE(condition));
            IQueryResult classList = statement.execute();

            for (Iterator<?> ir2 = classList.iterator(); ir2.hasNext();) {
                Element eObject = (Element) ir2.next();

                if (!elements.contains(eObject)) {
                    elements.add(eObject);
                }
            }
        }

        return elements;
    }
}
