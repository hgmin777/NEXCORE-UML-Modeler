/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.realization.sequencediagram.edit.part;

import java.util.Collections;
import java.util.List;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.Attachement;
import nexcore.tool.uml.model.umldiagram.LifeLineNode;
import nexcore.tool.uml.model.umldiagram.Line;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.diagram.edit.part.AbstractNotationNodeEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.AttachementEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.DelegatingDragEditPartsTracker;
import nexcore.tool.uml.ui.core.diagram.edit.policy.DirectEditorPolicy;
import nexcore.tool.uml.ui.core.diagram.figure.FigureConstant;
import nexcore.tool.uml.ui.core.registry.IConstantColorRegistry;
import nexcore.tool.uml.ui.realization.sequencediagram.edit.policy.BehaviorResizableEditPolicy;
import nexcore.tool.uml.ui.realization.sequencediagram.edit.policy.HighLightEditPolicy;
import nexcore.tool.uml.ui.realization.sequencediagram.edit.policy.SequenceDiagramGraphicalNodeEditPolicy;
import nexcore.tool.uml.ui.realization.sequencediagram.figure.FixedConnectionAnchor;
import nexcore.tool.uml.ui.realization.sequencediagram.figure.MessageIncommingConnectionAnchor;
import nexcore.tool.uml.ui.realization.sequencediagram.figure.MessageOutgoingConnectionAnchor;
import nexcore.tool.uml.ui.realization.sequencediagram.util.SequenceUtil;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.XYAnchor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.DropRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.gef.requests.SelectionRequest;

/**
 * BehaviorExecutionSpecification
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.edit.part</li>
 * <li>설 명 : LifeLineActivateEditPart</li>
 * <li>작성일 : 2009. 12. 14.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class LifeLineBehaviorEditPart extends AbstractNotationNodeEditPart {

    /** margin */
    @SuppressWarnings("unused")
    private final int margin = 4;

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    protected IFigure createFigure() {

        NotationNode notationNode = (NotationNode) getModel();
        RectangleFigure figure = new RectangleFigure();
        figure.setBackgroundColor(ColorConstants.lightBlue);
        figure.setForegroundColor(UiCorePlugin.getDefault().getColor(IConstantColorRegistry.Black));
        figure.setLineWidth(2);
        figure.setFill(true);
        figure.setLocation(new Point(notationNode.getX(), notationNode.getY()));
        figure.setSize(notationNode.getWidth(), notationNode.getHeight());
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
     * Behavior의 사이즈를 조절한다.
     * 
     * @return EditPolicy
     */
    private EditPolicy createResizableEditPolicy() {
        return new BehaviorResizableEditPolicy();
        // return new NonResizableEditPolicy();
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
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractNotationNodeEditPart#createDirectEditorPolicy()
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
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
     */
    @SuppressWarnings("unchecked")
    @Override
    public void refreshVisuals() {
        try {
            super.refreshVisuals();
            
            NotationNode parentNode = (NotationNode) ((AbstractNode) getModel()).getParent();
            NotationNode behaviorNode = (NotationNode) getModel();
            RectangleFigure figure = (RectangleFigure) getFigure();

            if (parentNode instanceof Line) {
                LifeLineNode lifeLineNode = (LifeLineNode) parentNode.getParent();
                figure.setLocation(new Point(lifeLineNode.getX() + lifeLineNode.getWidth() / 2
                    - FigureConstant.FIGURE_BEHAVIOR_WIDTH / 2, behaviorNode.getY()));
            } else {
                LifeLineNode lifeLineNode = (LifeLineNode) SequenceUtil.getLine(parentNode).getParent();
                figure.setLocation(new Point(lifeLineNode.getX() + lifeLineNode.getWidth() / 2
                    - FigureConstant.FIGURE_BEHAVIOR_WIDTH / 2, behaviorNode.getY()));
                // List children = getParent().getChildren();
                // AbstractGraphicalEditPart editPart;
                // int parentX = 0;
                // for (int i = 0; i < children.size(); i++) {
                // editPart = (AbstractGraphicalEditPart) children.get(i);
                // if (parentNode.equals(editPart.getModel())) {
                // parentX = editPart.getFigure().getBounds().x;
                // }
                // }
                // figure.setLocation(new Point(parentX + parentNode.getWidth()
                // / 2 - FigureConstant.FIGURE_BEHAVIOR_WIDTH / 2
                // + margin, behaviorNode.getY()));
            }
            figure.setSize(behaviorNode.getWidth(), behaviorNode.getHeight());
            List<GraphicalEditPart> childrenBehaviorEditPartList = SequenceUtil.getChildrenBehaviorEditPart(this);
            GraphicalEditPart childBehaviorEditPart = null;
            for (int i = 0; i < childrenBehaviorEditPartList.size(); i++) {
                childBehaviorEditPart = childrenBehaviorEditPartList.get(i);
                ((LifeLineBehaviorEditPart) childBehaviorEditPart).refreshVisuals();
            }

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
            Log.error(UMLMessage.MESSAGE_LIFELINE_BEHAVIOR_EDIT_PART_REFRESH_VISUALS_ERROR + e);
        }

    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractNotationNodeEditPart#getModelChildren()
     */
    @SuppressWarnings("unchecked")
    @Override
    protected List getModelChildren() {
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
            return new ChopboxAnchor(getFigure());
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
                new ChopboxAnchor(getFigure());
            }
            DropRequest connectionRequest = (DropRequest) request;
            Point point = connectionRequest.getLocation();
            return new FixedConnectionAnchor(getFigure(), point);
        } else if (request instanceof ReconnectRequest) {
            if (!(((ReconnectRequest) request).getConnectionEditPart() instanceof MessageEditPart)) {
                new ChopboxAnchor(getFigure());
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
            return new ChopboxAnchor(getFigure());
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
                new ChopboxAnchor(getFigure());
            }
            DropRequest connectionRequest = (DropRequest) request;
            Point point = connectionRequest.getLocation();
            return new FixedConnectionAnchor(getFigure(), point);
        } else if (request instanceof ReconnectRequest) {
            if (!(((ReconnectRequest) request).getConnectionEditPart() instanceof MessageEditPart)) {
                new ChopboxAnchor(getFigure());
            }
            ReconnectRequest connectionRequest = (ReconnectRequest) request;
            Point point = connectionRequest.getLocation();
            return new FixedConnectionAnchor(getFigure(), point);
        }
        return new ChopboxAnchor(getFigure());
    }
}
