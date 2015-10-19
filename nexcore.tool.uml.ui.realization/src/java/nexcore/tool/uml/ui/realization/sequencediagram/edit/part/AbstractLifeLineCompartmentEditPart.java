/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.realization.sequencediagram.edit.part;

import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.core.diagram.command.DirectEditCellEditorLocator;
import nexcore.tool.uml.ui.core.diagram.command.DirectEditorManager;
import nexcore.tool.uml.ui.core.diagram.edit.policy.DiagramComponentEditPolicy;
import nexcore.tool.uml.ui.core.diagram.edit.policy.DirectEditorPolicy;
import nexcore.tool.uml.ui.realization.sequencediagram.edit.policy.LifeLineResizeableEditPolicy;
import nexcore.tool.uml.ui.realization.sequencediagram.edit.policy.SequenceDiagramGraphicalNodeEditPolicy;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.Type;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.edit.part</li>
 * <li>설  명 : AbstractLifeLineCompartmentEditPart</li>
 * <li>작성일 : 2010. 3. 23.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public abstract class AbstractLifeLineCompartmentEditPart extends AbstractGraphicalEditPart implements Adapter {

    /**
     * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    public void notifyChanged(Notification notification) {
        // int featureID = notification.getFeatureID(UMLDiagramPackage.class);
        int eventType = notification.getEventType();
        switch (eventType) {
            case Notification.ADD:
            case Notification.ADD_MANY:
                refreshVisuals();
                refreshChildren();
                break;
            case Notification.REMOVE:
            case Notification.REMOVE_MANY:
                refreshVisuals();
                refreshChildren();
                break;
            case Notification.MOVE:
            case Notification.UNSET:
                refreshVisuals();
                refreshChildren();
            case Notification.SET:
                refreshVisuals();
                refreshChildren();
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
            NotationNode model = (NotationNode) getModel();
            model.eAdapters().add(this);
            if (this.getParent().getModel() instanceof NotationNode) {
                AbstractView viewModel = (AbstractView) this.getParent().getModel();
                viewModel.getUmlModel().eAdapters().add(this);
                if (viewModel.getUmlModel() instanceof Lifeline
                    && ((Lifeline) viewModel.getUmlModel()).getRepresents() != null
                    && ((Lifeline) viewModel.getUmlModel()).getRepresents().getType() != null) {
                    Type type = ((Lifeline) viewModel.getUmlModel()).getRepresents().getType();
                    type.eAdapters().add(this);
                }
            }
        }
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#deactivate()
     */
    @Override
    public void deactivate() {
        if (isActive()) {
            super.deactivate();
            NotationNode model = (NotationNode) getModel();
            model.eAdapters().remove(this);
            if (this.getParent().getModel() instanceof NotationNode) {
                AbstractView viewModel = (AbstractView) this.getParent().getModel();
                viewModel.getUmlModel().eAdapters().remove(this);
                if (viewModel.getUmlModel() instanceof Lifeline
                    && ((Lifeline) viewModel.getUmlModel()).getRepresents() != null
                    && ((Lifeline) viewModel.getUmlModel()).getRepresents().getType() != null) {
                    Type type = ((Lifeline) viewModel.getUmlModel()).getRepresents().getType();
                    type.eAdapters().remove(this);
                }
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

    /**
     * performDirectEdit void
     */
    protected void performDirectEdit(Request req) {
        if (directManager == null) {
            directManager = new DirectEditorManager(this,
                TextCellEditor.class,
                new DirectEditCellEditorLocator(getFigure()));
        }
        directManager.show();
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, createComponentEditPolicy());
        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, createDirectEditorPolicy());
        installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, createSizeableEditPolicy());
        // installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE,
        // createGraphicalNodeEditPolicy());
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
     * createSizeableEditPolicy
     * 
     * @return EditPolicy
     */
    private EditPolicy createSizeableEditPolicy() {
        //return new NonResizableEditPolicy();
        return new LifeLineResizeableEditPolicy();
    }

    /**
     * 그래픽칼 에디트 폴리시(컨넥션정책)를 생성한다. EditPolicy.GRAPHICAL_NODE_ROLE 인스톨 된다.
     * 
     * @return
     */
    protected GraphicalNodeEditPolicy createGraphicalNodeEditPolicy() {
        return new SequenceDiagramGraphicalNodeEditPolicy();
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
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * @see org.eclipse.emf.common.notify.Adapter#setTarget(org.eclipse.emf.common.notify.Notifier)
     */
    public void setTarget(Notifier newTarget) {
        // TODO Auto-generated method stub

    }
}
