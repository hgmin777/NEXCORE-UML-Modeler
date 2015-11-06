/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.analysis.activitydiagram.edit.part;

import java.util.List;

import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.LabelNode;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.model.umldiagram.UMLDiagramPackage;
import nexcore.tool.uml.ui.analysis.activitydiagram.edit.policy.ActivityDiagramComponentEditPolicy;
import nexcore.tool.uml.ui.core.diagram.edit.part.AbstractDiagramEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.AbstractNotationNodeEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.LabelNodeEditPart;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.StructuralFeature;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.analysis</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.analysis.activitydiagram.edit.part</li>
 * <li>설 명 : AbstractControlNodeEditPart</li>
 * <li>작성일 : 2011. 1. 31.</li>
 * <li>작성자 : zerotae</li>
 * </ul>
 */
public abstract class AbstractControlNodeEditPart extends AbstractNotationNodeEditPart {
    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractNotationNodeEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, createComponentEditPolicy());
        installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, createGraphicalNodeEditPolicy());
        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, createDirectEditorPolicy());
    }

    /**
     * 콤포넌트 에디트 폴리시(삭제정책)를 생성한다. EditPolicy.COMPONENT_ROLE 인스톨 된다.
     * 
     * @return
     */
    protected ComponentEditPolicy createComponentEditPolicy() {
        return new ActivityDiagramComponentEditPolicy();
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#activate()
     */
    public void activate() {
        if (!isActive()) {
            super.activate();
            EObject model = (EObject) getModel();
            model.eAdapters().add(this);

            model = ((AbstractNode) getModel()).getUmlModel();
            model.eAdapters().add(this);

            model = ((AbstractNode) getModel()).getUmlModel();
            for (Stereotype stereotype : ((Element) model).getAppliedStereotypes()) {
                stereotype.eAdapters().add(this);
            }

            model = ((AbstractNode) getModel()).getUmlModel();
            for (EAnnotation annotation : ((EModelElement) model).getEAnnotations()) {
                annotation.eAdapters().add(this);
            }
        }
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#deactivate()
     */
    public void deactivate() {
        if (isActive()) {
            super.deactivate();
            EObject model = (EObject) getModel();
            model.eAdapters().remove(this);

            model = ((AbstractNode) getModel()).getUmlModel();
            model.eAdapters().remove(this);

            model = ((AbstractNode) getModel()).getUmlModel();
            for (Stereotype stereotype : ((Element) model).getAppliedStereotypes()) {
                stereotype.eAdapters().remove(this);
            }

            model = ((AbstractNode) getModel()).getUmlModel();
            for (EAnnotation annotation : ((EModelElement) model).getEAnnotations()) {
                annotation.eAdapters().remove(this);
            }
        }
    }

    /**
     * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    public void notifyChanged(Notification notification) {
        int featureID = notification.getFeatureID(UMLDiagramPackage.class);
        int eventType = notification.getEventType();
        switch (eventType) {
            case Notification.ADD:
            case Notification.ADD_MANY:
                if (notification.getNewValue() instanceof AbstractConnection) {
                    switch (featureID) {
                        case UMLDiagramPackage.NOTATION_NODE__INCOMING_CONNECTION_LIST:
                            refreshTargetConnections();
                            break;
                        case UMLDiagramPackage.NOTATION_NODE__OUTGOING_CONNECTION_LIST:
                            refreshSourceConnections();
                            break;
                        default:
                            break;
                    }
                } else if (notification.getNewValue() instanceof StructuralFeature) {
                    refreshChildren();
                    refreshVisuals();
                } else if (notification.getNewValue() instanceof NotationNode) {
                    refreshChildren();
                } else {
                    if (notification.getNewValue() instanceof EAnnotation) {
                        if (!((EAnnotation) notification.getNewValue()).eAdapters().contains(this)) {
                            ((EAnnotation) notification.getNewValue()).eAdapters().add(this);
                        }
                    }
                    if (notification.getNewValue() instanceof EStringToStringMapEntryImpl) {
                        if (!((EStringToStringMapEntryImpl) notification.getNewValue()).eAdapters().contains(this)) {
                            ((EStringToStringMapEntryImpl) notification.getNewValue()).eAdapters().add(this);
                        }
                    }
                    refreshChildren();
                    refreshVisuals();
                }
                break;
            case Notification.REMOVE:
                refreshChildren();
                refreshVisuals();
            case Notification.REMOVE_MANY:
                refreshVisuals();
                refreshSourceConnections();
                refreshTargetConnections();
                break;
            case Notification.MOVE:
            case Notification.UNSET:
                refreshVisuals();
                break;
            case Notification.SET:
                if (notification.getNewValue() instanceof EAnnotation) {
                    ((EAnnotation) notification.getNewValue()).eAdapters().add(this);
                }
                refreshChildren();
                refreshVisuals();
                refreshSourceConnections();
                refreshTargetConnections();
                break;
            default:
                break;
        }
    }

    /**
     * nodeMoved
     *   void
     */
    public void nodeMoved() {
        AbstractDiagramEditPart diagramEditPart = getDiagramEditPart(this);
        LabelNodeEditPart labelEditPart = getLabelEditPart(diagramEditPart);
        if (labelEditPart != null) {
            labelEditPart.refresh();
        }
    }

    /**
     * getDiagramEditPart
     *  
     * @param editPart
     * @return AbstractDiagramEditPart
     */
    private AbstractDiagramEditPart getDiagramEditPart(EditPart editPart) {
        if (editPart instanceof AbstractDiagramEditPart) {
            return (AbstractDiagramEditPart) editPart;
        } else {
            return getDiagramEditPart(editPart.getParent());
        }
    }

    /**
     * getLabelEditPart
     *  
     * @param diagramEditPart
     * @return LabelNodeEditPart
     */
    private LabelNodeEditPart getLabelEditPart(AbstractDiagramEditPart diagramEditPart) {
        List<EditPart> children = diagramEditPart.getChildren();
        NotationNode node = (NotationNode) getModel();

        LabelNode nodeLabel = null;
        if (!node.getLabels().isEmpty()) {
            nodeLabel = node.getLabels().get(0);
        }
        if (nodeLabel == null) {
            return null;
        }
        for (EditPart editpart : children) {
            if (editpart.getModel().equals(nodeLabel)) {
                return (LabelNodeEditPart) editpart;
            }
        }
        return null;
    }

}
