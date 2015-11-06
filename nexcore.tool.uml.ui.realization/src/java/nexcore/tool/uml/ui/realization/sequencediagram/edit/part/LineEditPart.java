/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.realization.sequencediagram.edit.part;

import java.util.Collections;
import java.util.List;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.Attachement;
import nexcore.tool.uml.model.umldiagram.Dimension;
import nexcore.tool.uml.model.umldiagram.LifeLineNode;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.diagram.edit.part.AbstractNotationNodeEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.AttachementEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.DelegatingDragEditPartsTracker;
import nexcore.tool.uml.ui.core.diagram.edit.policy.DirectEditorPolicy;
import nexcore.tool.uml.ui.core.diagram.figure.FigureConstant;
import nexcore.tool.uml.ui.core.registry.IConstantColorRegistry;
import nexcore.tool.uml.ui.realization.sequencediagram.edit.policy.HighLightEditPolicy;
import nexcore.tool.uml.ui.realization.sequencediagram.edit.policy.LifeLineResizeableEditPolicy;
import nexcore.tool.uml.ui.realization.sequencediagram.edit.policy.SequenceDiagramGraphicalNodeEditPolicy;
import nexcore.tool.uml.ui.realization.sequencediagram.edit.policy.SequenceLineResizableEditPolicy;
import nexcore.tool.uml.ui.realization.sequencediagram.figure.FixedConnectionAnchor;
import nexcore.tool.uml.ui.realization.sequencediagram.figure.LineFigure;
import nexcore.tool.uml.ui.realization.sequencediagram.figure.MessageIncommingConnectionAnchor;
import nexcore.tool.uml.ui.realization.sequencediagram.figure.MessageOutgoingConnectionAnchor;
import nexcore.tool.uml.ui.realization.sequencediagram.util.SequenceUtil;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYAnchor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.handles.RelativeHandleLocator;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.gef.requests.SelectionRequest;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.edit.part</li>
 * <li>설 명 : LineEditPart</li>
 * <li>작성일 : 2010. 1. 15.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class LineEditPart extends AbstractNotationNodeEditPart {

    /**
     * LINE_WIDTH
     */
    public static final int LINE_SELECTABLE_WIDTH = 30;
        
    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    protected IFigure createFigure() {

        NotationNode notationNode = (NotationNode) getParent().getModel();

        LineFigure figure = new LineFigure();        
        Rectangle bound = new Rectangle(notationNode.getX() + notationNode.getWidth() / 2 - LINE_SELECTABLE_WIDTH/2, 
            notationNode.getY() + FigureConstant.FIGURE_LIFELINE_HEAD_HEIGHT,
            LINE_SELECTABLE_WIDTH, 
            notationNode.getHeight() - FigureConstant.FIGURE_LIFELINE_HEAD_HEIGHT);
        figure.setBounds(bound);
                
        return figure;
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractNotationNodeEditPart#createEditPolicies()
     */
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new HighLightEditPolicy(UiCorePlugin.getDefault()
            .getColor(IConstantColorRegistry.SkyBlue)));
        installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, createResizableEditPolicy());
    }

    /**
     * 그래픽칼 에디트 폴리시(컨넥션정책)를 생성한다. EditPolicy.GRAPHICAL_NODE_ROLE 인스톨 된다.
     * 
     * @return
     */
    @Override
    protected GraphicalNodeEditPolicy createGraphicalNodeEditPolicy() {
        return new SequenceDiagramGraphicalNodeEditPolicy();
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractChildCompartmentEditPart#createDirectEditorPolicy()
     */
    @Override
    protected DirectEditorPolicy createDirectEditorPolicy() {
        return null;
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractNotationNodeEditPart#createComponentEditPolicy()
     */
    @Override
    protected ComponentEditPolicy createComponentEditPolicy() {
        return null;
    }

    /**
     * createResizableEditPolicy
     *  
     * @return EditPolicy
     */
    private EditPolicy createResizableEditPolicy() {
        //return new LifeLineResizeableEditPolicy();
        return new SequenceLineResizableEditPolicy();
    }
    
    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
     */
    @SuppressWarnings("unchecked")
    @Override
    protected void refreshVisuals() {
        try {
            super.refreshVisuals();
            
            NotationNode notationNode = (NotationNode) getParent().getModel();
            
            LineFigure figure = (LineFigure) getFigure();
            
            Rectangle bound = new Rectangle(notationNode.getX() + notationNode.getWidth() / 2 - LINE_SELECTABLE_WIDTH/2, 
                notationNode.getY() + FigureConstant.FIGURE_LIFELINE_HEAD_HEIGHT,
                LINE_SELECTABLE_WIDTH, 
                notationNode.getHeight() - FigureConstant.FIGURE_LIFELINE_HEAD_HEIGHT);
            figure.setBounds(bound);
            setLayoutConstraint(this, figure, bound);

            int i = 0;
            ConnectionEditPart editPart;
            List editParts = getSourceConnections();
            for (i = 0; i < editParts.size(); i++) {
                editPart = (ConnectionEditPart) editParts.get(i);
                editPart.refresh();
            }
            editParts = getTargetConnections();
            for (i = 0; i < editParts.size(); i++) {
                editPart = (ConnectionEditPart) editParts.get(i);
                editPart.refresh();
            }
        } catch (Exception e) {
            Log.error(UMLMessage.MESSAGE_LINE_EDIT_PART_REFRESH_VISUALS_ERROR + e);
        }

    }
    
    /**
     * isSelected
     */
    private boolean isSelected = false;

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractNotationNodeEditPart#isSelected()
     */
    public boolean isSelected() {
        return isSelected;
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractNotationNodeEditPart#setSelected(boolean)
     */
    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractNotationNodeEditPart#isSelectable()
     */
    @Override
    public boolean isSelectable() {
        if (isSelected) {
            return true;
        }
        if (getParent().getSelected() == 2 || getSameLevelSelected()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * getSameLevelSelected
     *  
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    private boolean getSameLevelSelected() {
        List<EditPart> editParts = getParent().getChildren();
        for (EditPart editpart : editParts) {
            if (editpart.getSelected() == 2) {
                return true;
            }
        }
        return false;
    }


    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractNotationNodeEditPart#getSelected()
     */
    @Override
    public int getSelected() {
        if (super.getSelected() == 2) {
            isSelected = true;
        } else if (super.getSelected() == 0) {
            isSelected = false;
        }
        return super.getSelected();
    }

    /**
     * refreshSourceMessages void
     */
    public void refreshSourceMessages() {
        refreshSourceConnections();
    }

    /**
     * refreshTargetMessages void
     */
    public void refreshTargetMessages() {
        refreshTargetConnections();
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#getModelChildren()
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<EObject> getModelChildren() {
        return Collections.EMPTY_LIST;
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getDragTracker(org.eclipse.gef.Request)
     */
    @Override
    public DragTracker getDragTracker(Request request) {
        if (request instanceof SelectionRequest && ((SelectionRequest) request).getLastButtonPressed() == 3)
            return null;
        return new SequenceDelegatingDragEditPartsTracker(this, getParent());
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractNotationNodeEditPart#getSourceConnectionAnchor(org.eclipse.gef.ConnectionEditPart)
     */
    public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
        if (connection instanceof AttachementEditPart) {
            AbstractConnection abstractConnection = (AbstractConnection) connection.getModel();
            LifeLineNode sourceNode = SequenceUtil.getLifeLineNode((AbstractNode) connection.getSource().getModel());
            Dimension sourceAnchor = abstractConnection.getSourceAnchor();
            return new XYAnchor(new Point(sourceNode.getX() + sourceNode.getWidth() / 2, sourceAnchor.getHeight()));
        }
        if (connection.getTarget() != null && connection.getSource() != null) {
            LifeLineNode sourceNode = SequenceUtil.getLifeLineNode((AbstractNode) connection.getSource().getModel());
            LifeLineNode targetNode = SequenceUtil.getLifeLineNode((AbstractNode) connection.getTarget().getModel());
            if (sourceNode.equals(targetNode)) {
                Point point = new Point(sourceNode.getX() + sourceNode.getWidth() / 2,
                    ((AbstractView) connection.getModel()).getY());
                connection.getFigure().translateToAbsolute(point);
                return new XYAnchor(point);
            } else {
                return new MessageOutgoingConnectionAnchor(getFigure(), connection);
            }
        } else {
            return new MessageOutgoingConnectionAnchor(getFigure(), connection);
        }

    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractNotationNodeEditPart#getSourceConnectionAnchor(org.eclipse.gef.Request)
     */
    public ConnectionAnchor getSourceConnectionAnchor(Request request) {
        if (request instanceof CreateConnectionRequest) {
            if (((CreateConnectionRequest) request).getNewObject() instanceof Attachement) {
                return new XYAnchor(((CreateConnectionRequest) request).getLocation());
            }
            CreateConnectionRequest connectionRequest = (CreateConnectionRequest) request;
            Point point = connectionRequest.getLocation();
            return new FixedConnectionAnchor(getFigure(), point);
        } else if (request instanceof ReconnectRequest) {
            if (((ReconnectRequest) request).getConnectionEditPart() instanceof AttachementEditPart) {
                return new XYAnchor(((ReconnectRequest) request).getLocation());
            }
            ReconnectRequest connectionRequest = (ReconnectRequest) request;
            Point point = connectionRequest.getLocation();
            return new FixedConnectionAnchor(getFigure(), point);
        }
        return new ChopboxAnchor(getFigure());

    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractNotationNodeEditPart#getTargetConnectionAnchor(org.eclipse.gef.ConnectionEditPart)
     */
    public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
        if (connection instanceof AttachementEditPart) {
            AbstractConnection abstractConnection = (AbstractConnection) connection.getModel();
            Dimension targetAnchor = abstractConnection.getTargetAnchor();
            LifeLineNode targetNode = SequenceUtil.getLifeLineNode((AbstractNode) connection.getTarget().getModel());
            return new XYAnchor(new Point(targetNode.getX() + targetNode.getWidth() / 2, targetAnchor.getHeight()));
        }
        if (connection.getTarget() != null && connection.getSource() != null) {
            LifeLineNode sourceNode = SequenceUtil.getLifeLineNode((AbstractNode) connection.getSource().getModel());
            LifeLineNode targetNode = SequenceUtil.getLifeLineNode((AbstractNode) connection.getTarget().getModel());
            if (sourceNode.equals(targetNode)) {
                Point point = new Point(targetNode.getX() + targetNode.getWidth() / 2,
                    ((AbstractView) connection.getModel()).getY() + 10);
                connection.getFigure().translateToAbsolute(point);
                return new XYAnchor(point);
            } else {
                return new MessageIncommingConnectionAnchor(getFigure(), connection);
            }
        } else {
            return new MessageIncommingConnectionAnchor(getFigure(), connection);
        }
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractNotationNodeEditPart#getTargetConnectionAnchor(org.eclipse.gef.Request)
     */
    public ConnectionAnchor getTargetConnectionAnchor(Request request) {
        if (request instanceof CreateConnectionRequest) {
            if (((CreateConnectionRequest) request).getNewObject() instanceof Attachement) {
                return new XYAnchor(((CreateConnectionRequest) request).getLocation());
            }
            CreateConnectionRequest connectionRequest = (CreateConnectionRequest) request;
            Point point = connectionRequest.getLocation();
            return new FixedConnectionAnchor(getFigure(), point);
        } else if (request instanceof ReconnectRequest) {
            if (((ReconnectRequest) request).getConnectionEditPart() instanceof AttachementEditPart) {
                return new XYAnchor(((ReconnectRequest) request).getLocation());
            }
            ReconnectRequest connectionRequest = (ReconnectRequest) request;
            Point point = connectionRequest.getLocation();
            return new FixedConnectionAnchor(getFigure(), point);
        }
        return new ChopboxAnchor(getFigure());
    }
}
