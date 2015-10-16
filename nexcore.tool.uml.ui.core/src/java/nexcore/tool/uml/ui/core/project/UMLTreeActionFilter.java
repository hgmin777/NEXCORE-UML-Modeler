/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.project;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.model.umldiagram.DiagramType;
import nexcore.tool.uml.ui.core.util.ProjectUtil;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.IActionFilter;
import org.eclipse.uml2.uml.Abstraction;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.BehaviorExecutionSpecification;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.CombinedFragment;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.ControlFlow;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ExecutionEvent;
import org.eclipse.uml2.uml.ExecutionOccurrenceSpecification;
import org.eclipse.uml2.uml.Extend;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Include;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.InteractionOperand;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageOccurrenceSpecification;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.ObjectFlow;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Realization;
import org.eclipse.uml2.uml.ReceiveOperationEvent;
import org.eclipse.uml2.uml.SendOperationEvent;
import org.eclipse.uml2.uml.Usage;
import org.eclipse.uml2.uml.UseCase;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.project</li>
 * <li>설  명 : UMLTreeActionFilter</li>
 * <li>작성일 : 2009. 12. 7.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
public class UMLTreeActionFilter implements IActionFilter {

    /** Object State name */
    private final String EOBJECT = "EOBJECT"; //$NON-NLS-1$

    /**
     * PACKAGEABLE_ELEMENT
     */
    private final String PACKAGEABLE_ELEMENT = "PACKAGEABLE_ELEMENT"; //$NON-NLS-1$

    /**
     * CLIPBOARD
     */
    private final String CLIPBOARD = "CLIPBOARD"; //$NON-NLS-1$

    /**
     * SEQUENCE_DIAGRAM
     */
    private final String SEQUENCE_DIAGRAM = "SEQUENCE_DIAGRAM"; //$NON-NLS-1$

    /**
     * FILTER
     */
    private final String FILTER = "FILTER"; //$NON-NLS-1$
    
    /**
     * DELETE_FILTER
     */
    private final String DELETE_FILTER = "DELETE_FILTER"; //$NON-NLS-1$
    
    /**
     * CLASS_MERGE_FILTER
     */
    private final String CLASS_MERGE_FILTER = "CLASS_MERGE_FILTER"; //$NON-NLS-1$

    /**
     * USECASE_MERGE_FILTER
     */
    private final String USECASE_MERGE_FILTER = "USECASE_MERGE_FILTER"; //$NON-NLS-1$

    /**
     * PACKAGE_FILTER
     */
    private final String PACKAGE_FILTER = "PACKAGE_FILTER"; //$NON-NLS-1$

    /**
     * NAMED_ELEMENT
     */
    private final String NAMED_ELEMENT = "NAMED_ELEMENT"; //$NON-NLS-1$

    /**
     * @see org.eclipse.ui.IActionFilter#testAttribute(java.lang.Object,
     *      java.lang.String, java.lang.String)
     */
    public boolean testAttribute(Object target, String name, String value) {

        if (target instanceof ITreeNode) {
            ITreeNode node = (ITreeNode) target;
            EObject eobject = node.getEObject();

            if (name.equals(EOBJECT)) {
                boolean flag = eobject.getClass().getName().equals(value);

                if (eobject instanceof EAnnotation) {
                    EAnnotation eAnnotation = (EAnnotation) eobject;
                    if (!eAnnotation.getSource().equals("Diagram")) { //$NON-NLS-1$
                        flag = false;
                    }
                }
                return flag;

            } else if (name.equals(PACKAGEABLE_ELEMENT)) {
                return eobject instanceof PackageableElement;

            } else if (name.equals(CLIPBOARD)) {
                Collection<Object> collection = DomainRegistry.getUMLDomain().getClipboard();
                if (collection == null || collection.size() == 0) {
                    return false;
                } else {
                    return true;
                }

            } else if (name.equals(FILTER)) {

                return !(eobject instanceof Lifeline || eobject instanceof Abstraction
                    || eobject instanceof Association || eobject instanceof BehaviorExecutionSpecification
                    || eobject instanceof CombinedFragment || eobject instanceof Comment
                    || eobject instanceof Connector || eobject instanceof ControlFlow || eobject instanceof Dependency
                    || eobject instanceof ExecutionEvent || eobject instanceof ExecutionOccurrenceSpecification
                    || eobject instanceof Extend || eobject instanceof Generalization || eobject instanceof Include
                    || eobject instanceof InteractionOperand || eobject instanceof InterfaceRealization
                    || eobject instanceof Message || eobject instanceof MessageOccurrenceSpecification
                    || eobject instanceof ObjectFlow || eobject instanceof Realization
                    || eobject instanceof ReceiveOperationEvent || eobject instanceof SendOperationEvent || eobject instanceof Usage);
                
            } else if (name.equals(DELETE_FILTER)) {
                // FILTER 조건에서 Message, Lifeline을 뺌.
                return !(eobject instanceof Abstraction
                    || eobject instanceof Association || eobject instanceof BehaviorExecutionSpecification
                    || eobject instanceof CombinedFragment || eobject instanceof Comment
                    || eobject instanceof Connector || eobject instanceof ControlFlow || eobject instanceof Dependency
                    || eobject instanceof ExecutionEvent || eobject instanceof ExecutionOccurrenceSpecification
                    || eobject instanceof Extend || eobject instanceof Generalization || eobject instanceof Include
                    || eobject instanceof InteractionOperand || eobject instanceof InterfaceRealization
                    || eobject instanceof MessageOccurrenceSpecification
                    || eobject instanceof ObjectFlow || eobject instanceof Realization
                    || eobject instanceof ReceiveOperationEvent || eobject instanceof SendOperationEvent || eobject instanceof Usage);
                
            } else if (name.equals(CLASS_MERGE_FILTER)) {
                if (eobject instanceof org.eclipse.uml2.uml.Class) {
                    if (eobject instanceof Component) {
                        return false;
                    } else {
                        return true;
                    }
                } else {
                    return false;
                }

            } else if (name.equals(PACKAGE_FILTER)) {
                if (eobject instanceof org.eclipse.uml2.uml.Package) {
                    return true;
                } else {
                    return false;
                }

            } else if (name.equals(USECASE_MERGE_FILTER)) {
                // 2011-08-11 강석찬
                // 유스케이스 2개이상 없으면 merge 비활성화
                if (eobject instanceof org.eclipse.uml2.uml.UseCase) {
                    EObject parent = ((UseCase) eobject).eContainer();
                    List objList = null;
                    ArrayList<EObject> removeList = new ArrayList<EObject>();
                    if (parent instanceof Package) {
                        objList = ((Package) parent).getPackagedElements();
                        for (Object obj : objList) {
                            if ((obj instanceof UseCase) && (!obj.equals(eobject))) {
                                removeList.add((UseCase) obj);
                            }
                        }
                    }

                    if (removeList == null) {
                        return false;
                    }

                    return true;
                } else {
                    return false;
                }

            } else if (name.equals(NAMED_ELEMENT)) {
                if (eobject instanceof NamedElement && ((NamedElement) eobject).getName() != null) {
                    return true;
                }

            } else if (name.equals(SEQUENCE_DIAGRAM)) {
                // Interaction 하위에 Sequence Diagram은 1개만 생성 가능.
                if (eobject instanceof Interaction) {
                    List list = ProjectUtil.getDiagrams((Element) eobject, DiagramType.SEQUENCE_DIAGRAM);
                    if (list.size() == 0) {
                        return true;
                    }
                } else if (eobject instanceof Collaboration || eobject instanceof Package
                    || eobject instanceof Component) {
                    return true;
                }

                return false;

            }
        }
        return false;
    }

}
