/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.diagram.edit.part;

import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.LabelNode;
import nexcore.tool.uml.model.umldiagram.LabelType;
import nexcore.tool.uml.model.umldiagram.Relation;
import nexcore.tool.uml.ui.core.diagram.command.DirectEditCellEditorLocator;
import nexcore.tool.uml.ui.core.diagram.command.DirectEditorManager;
import nexcore.tool.uml.ui.core.diagram.edit.policy.DiagramComponentEditPolicy;
import nexcore.tool.uml.ui.core.diagram.edit.policy.DirectEditorPolicy;
import nexcore.tool.uml.ui.core.diagram.edit.policy.LabelFeedbackEditPolicy;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.uml2.uml.Element;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.edit.part</li>
 * <li>설 명 : AbstractLabelCompartmentEditPart</li>
 * <li>작성일 : 2010. 1. 7.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public abstract class AbstractLabelCompartmentEditPart extends AbstractGraphicalEditPart implements Adapter {

    /**
     * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    public void notifyChanged(Notification notification) {
        // int featureID = notification.getFeatureID(UMLDiagramPackage.class);
        int eventType = notification.getEventType();
        // EObject model = (EObject) ((LabelNode) getModel()).getOwner();
        switch (eventType) {
            case Notification.ADD:
            case Notification.ADD_MANY:
                refreshChildren();
                refreshVisuals();
                if (notification.getNewValue() instanceof EAnnotation) {
                    ((EAnnotation) notification.getNewValue()).eAdapters().add(this);
                }
                break;
            case Notification.REMOVE:
            case Notification.REMOVE_MANY:
                refreshVisuals();
                break;
            case Notification.MOVE:
            case Notification.UNSET:
                refreshChildren();
            case Notification.SET:
                refreshVisuals();
                refreshChildren();
                if (notification.getNewValue() instanceof EAnnotation) {
                    ((EAnnotation) notification.getNewValue()).eAdapters().add(this);
                }
                break;
            default:
                break;
        }

    }

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#activate()
     */
    @Override
    public void activate() {
        if (!isActive()) {
            super.activate();

            EObject model = (EObject) getModel();
            model.eAdapters().add(this);
            model = (EObject) ((LabelNode) getModel()).getUmlModel();
            model.eAdapters().add(this);

            model = (EObject) ((LabelNode) getModel()).getOwner();
            model.eAdapters().add(this);

            model = ((Relation) ((LabelNode) getModel()).getOwner()).getUmlModel();
            for (EAnnotation eAnnotation : ((Element) model).getEAnnotations()) {
                eAnnotation.eAdapters().add(this);
            }

            model = ((AbstractConnection) (((LabelNode) getModel()).getOwner())).getUmlModel();
            model.eAdapters().add(this);

            if (model instanceof LabelNode) {
                model = ((LabelNode) getModel()).getUmlModel();
                model.eAdapters().add(this);

                // Relationship r;
            }
        }
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#deactivate()
     */
    @Override
    public void deactivate() {
        if (!isActive()) {
            super.deactivate();

            EObject model = (EObject) getModel();
            model.eAdapters().remove(this);
            model = (EObject) ((LabelNode) getModel()).getUmlModel();
            model.eAdapters().remove(this);

            model = (EObject) ((LabelNode) getModel()).getOwner();
            model.eAdapters().remove(this);

            model = ((Relation) ((LabelNode) getModel()).getOwner()).getUmlModel();
            for (EAnnotation eAnnotation : ((Element) model).getEAnnotations()) {
                eAnnotation.eAdapters().remove(this);
            }

            model = ((AbstractConnection) (((LabelNode) getModel()).getOwner())).getUmlModel();
            model.eAdapters().remove(this);

            if (model instanceof LabelNode) {
                model = ((LabelNode) getModel()).getUmlModel();
                model.eAdapters().remove(this);
            }
        }
    }

    /** directManager */
    protected DirectEditManager directManager = null;

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#performRequest(org.eclipse.gef.Request)
     */
    public void performRequest(Request req) {
        if (req.getType().equals(RequestConstants.REQ_DIRECT_EDIT)) {
            performDirectEdit(req);
            return;
        }
        super.performRequest(req);
    }

    /** iFigure */
    private IFigure iFigure = null;

    /**
     * 
     * getIFigure
     * 
     * @return IFigure
     */
    public IFigure getIFigure() {
        return iFigure;
    }

    /** name */
    private boolean name;

    /**
     * isName
     *  
     * @return boolean
     */
    public boolean isName() {
        return name;
    }

    /** stereotype */
    private boolean stereotype;

    /**
     * isStereotype
     *  
     * @return boolean
     */
    public boolean isStereotype() {
        return stereotype;
    }

    /**
     * performDirectEdit void
     */
    protected void performDirectEdit(Request req) {
        DirectEditRequest directRequest = null;
        if (req instanceof DirectEditRequest) {
            directRequest = (DirectEditRequest) req;
        }
        if (((LabelNode) getModel()).getType() == LabelType.LABEL) {
            iFigure = getFigure().findFigureAt(directRequest.getLocation());
            if (iFigure == getFigure().getChildren().get(0)) {
                name = false;
                stereotype = true;
            } else {// if (iFigure == getFigure().getChildren().get(1)) {
                iFigure = (IFigure) getFigure().getChildren().get(1);
                name = true;
                stereotype = false;
            }
        } else if (((LabelNode) getModel()).getType() == LabelType.SOURCE_MULTIPLEX
            || ((LabelNode) getModel()).getType() == LabelType.TARGET_MULTIPLEX) {
            return;
        } else {
            iFigure = getFigure();
        }
        directManager = new DirectEditorManager(this,
            TextCellEditor.class,
            new DirectEditCellEditorLocator(iFigure),
            ((Label) iFigure).getText());

        directManager.show();
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, createComponentEditPolicy());
        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, createDirectEditorPolicy());
        installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, createSizeableEditPolicy());
    }

    /**
     * createSizeableEditPolicy
     * 
     * @return EditPolicy
     */
    private EditPolicy createSizeableEditPolicy() {
        return new LabelFeedbackEditPolicy() {

            @Override
            protected void setSelectedState(int type) {
                if (getHost().getParent().getParent().getSelected() == EditPart.SELECTED
                    || getHost().getParent().getParent().getSelected() == EditPart.SELECTED_PRIMARY) {
                    super.setSelectedState(EditPart.SELECTED_NONE);
                } else {
                    super.setSelectedState(type);
                }

            }

        };
    }

    /**
     * 콤포넌트 에디트 폴리시(삭제정책)를 생성한다. EditPolicy.COMPONENT_ROLE 인스톨 된다.
     * 
     * @return
     */
    protected ComponentEditPolicy createComponentEditPolicy() {
        return new DiagramComponentEditPolicy();
    }

    /**
     * 다이렉트 에디트 폴리시(직접편집)를 생성한다. EditPolicy.DIRECT_EDIT_ROLE 인스톨 된다.
     * 
     * @return
     */
    protected DirectEditPolicy createDirectEditorPolicy() {
        return new DirectEditorPolicy();
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    protected abstract IFigure createFigure();

    /**
     * @see org.eclipse.emf.common.notify.Adapter#getTarget()
     */
    public Notifier getTarget() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @see org.eclipse.emf.common.notify.Adapter#isAdapterForType(java.lang.Object)
     */
    public boolean isAdapterForType(Object type) {
        return false;
    }

    /**
     * @see org.eclipse.emf.common.notify.Adapter#setTarget(org.eclipse.emf.common.notify.Notifier)
     */
    public void setTarget(Notifier newTarget) {
    }
}
