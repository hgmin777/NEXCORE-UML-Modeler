/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.core.diagram.edit.part;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.Attachement;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.DiagramType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.core.diagram.edit.policy.DiagramLayoutEditPolicy;
import nexcore.tool.uml.ui.core.util.ViewModelUtil;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutListener;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.RootComponentEditPolicy;
import org.eclipse.gef.editpolicies.SnapFeedbackPolicy;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Message;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.daigram.edit.part</li>
 * <li>설 명 : AbstractDiagramEditPart</li>
 * <li>작성일 : 2009. 11. 16.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */

public abstract class AbstractDiagramEditPart extends AbstractGraphicalEditPart implements Adapter {

    /** viewModelToEditPartMap */
    private Map<AbstractView, EditPart> viewModelToEditPartMap = new HashMap<AbstractView, EditPart>();

    /**
     * getEditPart
     *  
     * @param viewModel
     * @return EditPart
     */
    public EditPart getEditPart(AbstractView viewModel) {
        return viewModelToEditPartMap.get(viewModel);
    }

    /**
     * setEditPart
     *  
     * @param viewModel
     * @param editpart void
     */
    public void setEditPart(AbstractView viewModel, EditPart editpart) {
        viewModelToEditPartMap.put(viewModel, editpart);
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    @Override
    protected IFigure createFigure() {
        Figure figure = new FreeformLayer();
        figure.setBorder(new MarginBorder(10));
        figure.setLayoutManager(new FreeformLayout());
        figure.setOpaque(false);

        Diagram diagram = null;
        if (getModel() instanceof Diagram) {
            diagram = (Diagram) getModel();
            if (!(diagram.getType().equals(DiagramType.SEQUENCE_DIAGRAM))) {
                for (AbstractNode node : diagram.getNodeList()) {
                    if (node instanceof NotationNode) {
                    	ViewModelUtil.clearGarbageNode(node);
                        ViewModelUtil.setModelInfo((NotationNode) node);
                    }
                }
                for ( AbstractConnection connection : diagram.getConnectionList() ) {
                    ViewModelUtil.clearGarbageNode(connection);
                }
            }
        }

        figure.addLayoutListener(new PrintPreviewLayoutListener());
        return figure;
    }

    // 2011-10-07 강석찬
    // 다이어그램 수정시 미리보기 화면 수정
    public class PrintPreviewLayoutListener extends LayoutListener.Stub {

        public void postLayout(IFigure container) {
            super.postLayout(container);
            AbstractDiagramEditPart abstractDiagramEditPart = AbstractDiagramEditPart.this;
            EditPart rootPart = abstractDiagramEditPart.getParent();
            if (rootPart instanceof DiagramRootEditPart) {
                DiagramRootEditPart diagramRootEditPart = (DiagramRootEditPart) abstractDiagramEditPart.getParent();
                if (diagramRootEditPart.getPrintPreviewFigure().isVisible()) {
                    diagramRootEditPart.redrawPrintPreview();
                }
            }
        }
    }
    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    @Override
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new RootComponentEditPolicy());
        installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
        installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, createFeedbackPolicy());
    }

    /**
     * 레이아웃 에디트 폴리시(생성 및 위치이동 정책)를 생성한다. EditPolicy.LAYOUT_ROLE로 인스톨 된다.
     * 
     * @return
     */
    protected LayoutEditPolicy createLayoutEditPolicy() {
        return new DiagramLayoutEditPolicy();
    }

    /**
     * 피드백 에디트 폴리시를 생성한다. EditPolicy.SELECTION_FEEDBACK_ROLE 인스톨 된다.
     * 
     * @return
     */
    protected GraphicalEditPolicy createFeedbackPolicy() {
        return new SnapFeedbackPolicy();
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#getModelChildren()
     */
    @Override
    public List<EObject> getModelChildren() {
        List<EObject> list = new ArrayList<EObject>();
        list.addAll(((Diagram) getModel()).getNodeList());

        List<AbstractConnection> connectionList = new ArrayList<AbstractConnection>();
        connectionList = ((Diagram) getModel()).getConnectionList();

        if (connectionList.size() != 0) {
            for (AbstractConnection connection : connectionList) {
                if ((connection).getUmlModel() instanceof Association) {
                    list.addAll(connection.getLabels());
                }
                // else if( (connection).getUmlModel() instanceof Generalization
                // || (connection).getUmlModel() instanceof Message
                // || (connection).getUmlModel() instanceof ControlFlow ||
                // (connection).getUmlModel() instanceof ObjectFlow ||
                // connection instanceof Attachement){
                // continue;
                // }
                else if ((connection).getUmlModel() instanceof Generalization
                    || (connection).getUmlModel() instanceof Message || connection instanceof Attachement) {
                    continue;
                } else
                    list.addAll(connection.getLabels());
            }
        }

        return list;
    }

    /**
     * 다이어그램 모델을 리턴한다.
     * 
     * @return
     */
    protected Diagram getDiagramModel() {
        return (Diagram) getModel();
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
     */
    protected void refreshVisuals() {
        try {
            Rectangle bounds = this.getFigure().getBounds();
            setLayoutConstraint(this, getFigure(), bounds);
            getFigure().repaint();

        } catch (Exception e) {
            Log.error("AbstractDiagramEditPart refreshVisuals() Error " + e);
        }
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#refresh()
     */
    @SuppressWarnings("unchecked")
    @Override
    public void refresh() {
        super.refresh();
        List<EditPart> editparts = new ArrayList<EditPart>();
        editparts = getChildren();
        for (EditPart editpart : editparts) {
            editpart.refresh();
        }
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#activate()
     */
    public void activate() {
        if (!isActive()) {
            super.activate();
            ((EObject) getModel()).eAdapters().add(this);
        }
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#deactivate()
     */
    public void deactivate() {
        if (isActive()) {
            ((EObject) getModel()).eAdapters().remove(this);
            super.deactivate();
        }
    }

    /**
     * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    public void notifyChanged(Notification notification) {
        int eventType = notification.getEventType();
        switch (eventType) {
            case Notification.ADD:
            case Notification.ADD_MANY:
            case Notification.REMOVE:
            case Notification.REMOVE_MANY:
                this.refreshVisuals();
                refreshChildren();
                break;
            default:
                break;
        }
    }

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
    }

}
