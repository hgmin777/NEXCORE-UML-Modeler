/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.model.umldiagram.util;

import java.util.List;

import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.Attachement;
import nexcore.tool.uml.model.umldiagram.BendPoint;
import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.Dimension;
import nexcore.tool.uml.model.umldiagram.LabelNode;
import nexcore.tool.uml.model.umldiagram.LifeLineNode;
import nexcore.tool.uml.model.umldiagram.Line;
import nexcore.tool.uml.model.umldiagram.Map;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.model.umldiagram.RegionType;
import nexcore.tool.uml.model.umldiagram.Relation;
import nexcore.tool.uml.model.umldiagram.UMLDiagramPackage;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Element;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance
 * hierarchy. It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the
 * inheritance hierarchy until a non-null result is returned, which is the
 * result of the switch. <!-- end-user-doc -->
 * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.umldiagram.util</li>
 * <li>설  명 : UMLDiagramSwitch</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 * @param <T>
 */
public class UMLDiagramSwitch<T> {
    /**
     * The cached model package
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected static UMLDiagramPackage modelPackage;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     */
    public UMLDiagramSwitch() {
        if (modelPackage == null) {
            modelPackage = UMLDiagramPackage.eINSTANCE;
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    public T doSwitch(EObject theEObject) {
        return doSwitch(theEObject.eClass(), theEObject);
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    protected T doSwitch(EClass theEClass, EObject theEObject) {
        if (theEClass.eContainer() == modelPackage) {
            return doSwitch(theEClass.getClassifierID(), theEObject);
        }
        else {
            List<EClass> eSuperTypes = theEClass.getESuperTypes();
            return
                eSuperTypes.isEmpty() ?
                    defaultCase(theEObject) :
                    doSwitch(eSuperTypes.get(0), theEObject);
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    protected T doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
            case UMLDiagramPackage.ABSTRACT_CONNECTION: {
                AbstractConnection abstractConnection = (AbstractConnection)theEObject;
                T result = caseAbstractConnection(abstractConnection);
                if (result == null) result = caseAbstractView(abstractConnection);
                if (result == null) result = caseElement(abstractConnection);
                if (result == null) result = caseEModelElement(abstractConnection);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case UMLDiagramPackage.ABSTRACT_NODE: {
                AbstractNode abstractNode = (AbstractNode)theEObject;
                T result = caseAbstractNode(abstractNode);
                if (result == null) result = caseAbstractView(abstractNode);
                if (result == null) result = caseElement(abstractNode);
                if (result == null) result = caseEModelElement(abstractNode);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case UMLDiagramPackage.ABSTRACT_VIEW: {
                AbstractView abstractView = (AbstractView)theEObject;
                T result = caseAbstractView(abstractView);
                if (result == null) result = caseElement(abstractView);
                if (result == null) result = caseEModelElement(abstractView);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case UMLDiagramPackage.ATTACHEMENT: {
                Attachement attachement = (Attachement)theEObject;
                T result = caseAttachement(attachement);
                if (result == null) result = caseAbstractConnection(attachement);
                if (result == null) result = caseAbstractView(attachement);
                if (result == null) result = caseElement(attachement);
                if (result == null) result = caseEModelElement(attachement);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case UMLDiagramPackage.BEND_POINT: {
                BendPoint bendPoint = (BendPoint)theEObject;
                T result = caseBendPoint(bendPoint);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case UMLDiagramPackage.CONTAINER_NODE: {
                ContainerNode containerNode = (ContainerNode)theEObject;
                T result = caseContainerNode(containerNode);
                if (result == null) result = caseAbstractNode(containerNode);
                if (result == null) result = caseAbstractView(containerNode);
                if (result == null) result = caseElement(containerNode);
                if (result == null) result = caseEModelElement(containerNode);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case UMLDiagramPackage.DIAGRAM: {
                Diagram diagram = (Diagram)theEObject;
                T result = caseDiagram(diagram);
                if (result == null) result = caseAbstractNode(diagram);
                if (result == null) result = caseAbstractView(diagram);
                if (result == null) result = caseElement(diagram);
                if (result == null) result = caseEModelElement(diagram);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case UMLDiagramPackage.DIMENSION: {
                Dimension dimension = (Dimension)theEObject;
                T result = caseDimension(dimension);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case UMLDiagramPackage.MAP: {
                Map map = (Map)theEObject;
                T result = caseMap(map);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case UMLDiagramPackage.NOTATION_NODE: {
                NotationNode notationNode = (NotationNode)theEObject;
                T result = caseNotationNode(notationNode);
                if (result == null) result = caseAbstractNode(notationNode);
                if (result == null) result = caseAbstractView(notationNode);
                if (result == null) result = caseElement(notationNode);
                if (result == null) result = caseEModelElement(notationNode);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case UMLDiagramPackage.RELATION: {
                Relation relation = (Relation)theEObject;
                T result = caseRelation(relation);
                if (result == null) result = caseAbstractConnection(relation);
                if (result == null) result = caseAbstractView(relation);
                if (result == null) result = caseElement(relation);
                if (result == null) result = caseEModelElement(relation);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case UMLDiagramPackage.LIFE_LINE_NODE: {
                LifeLineNode lifeLineNode = (LifeLineNode)theEObject;
                T result = caseLifeLineNode(lifeLineNode);
                if (result == null) result = caseNotationNode(lifeLineNode);
                if (result == null) result = caseAbstractNode(lifeLineNode);
                if (result == null) result = caseAbstractView(lifeLineNode);
                if (result == null) result = caseElement(lifeLineNode);
                if (result == null) result = caseEModelElement(lifeLineNode);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case UMLDiagramPackage.LINE: {
                Line line = (Line)theEObject;
                T result = caseLine(line);
                if (result == null) result = caseNotationNode(line);
                if (result == null) result = caseAbstractNode(line);
                if (result == null) result = caseAbstractView(line);
                if (result == null) result = caseElement(line);
                if (result == null) result = caseEModelElement(line);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case UMLDiagramPackage.REGION_TYPE: {
                RegionType regionType = (RegionType)theEObject;
                T result = caseRegionType(regionType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case UMLDiagramPackage.LABEL_NODE: {
                LabelNode labelNode = (LabelNode)theEObject;
                T result = caseLabelNode(labelNode);
                if (result == null) result = caseAbstractNode(labelNode);
                if (result == null) result = caseAbstractView(labelNode);
                if (result == null) result = caseElement(labelNode);
                if (result == null) result = caseEModelElement(labelNode);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Connection</em>'.
     * <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Connection</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractConnection(AbstractConnection object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Node</em>'.
     * <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Node</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractNode(AbstractNode object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract View</em>'.
     * <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract View</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractView(AbstractView object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Attachement</em>'.
     * <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Attachement</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAttachement(Attachement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Bend Point</em>'.
     * <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Bend Point</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBendPoint(BendPoint object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Container Node</em>'.
     * <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Container Node</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseContainerNode(ContainerNode object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Diagram</em>'.
     * <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Diagram</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDiagram(Diagram object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Dimension</em>'.
     * <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Dimension</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDimension(Dimension object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Map</em>'. <!-- begin-user-doc --> This implementation returns null;
     * returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Map</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMap(Map object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Notation Node</em>'.
     * <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Notation Node</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNotationNode(NotationNode object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Relation</em>'.
     * <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Relation</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRelation(Relation object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Life Line Node</em>'.
     * <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Life Line Node</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLifeLineNode(LifeLineNode object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Line</em>'. <!-- begin-user-doc --> This implementation returns null;
     * returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Line</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLine(Line object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Region Type</em>'.
     * <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Region Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRegionType(RegionType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Label Node</em>'.
     * <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Label Node</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLabelNode(LabelNode object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EModel Element</em>'.
     * <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EModel Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEModelElement(EModelElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Element</em>'.
     * <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseElement(Element object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
     * <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch, but this is
     * the last case anyway. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    public T defaultCase(EObject object) {
        return null;
    }

} // UMLDiagramSwitch
