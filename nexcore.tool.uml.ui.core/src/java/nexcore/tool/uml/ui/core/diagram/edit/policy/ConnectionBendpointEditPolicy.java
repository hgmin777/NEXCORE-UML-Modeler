/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.core.diagram.edit.policy;

import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.Relation;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.diagram.command.CreateBendpointCommand;
import nexcore.tool.uml.ui.core.diagram.command.DeleteBendpointCommand;
import nexcore.tool.uml.ui.core.diagram.command.MoveBendpointCommand;
import nexcore.tool.uml.ui.core.diagram.editor.AbstractDiagramEditor;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.XYAnchor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.BendpointEditPolicy;
import org.eclipse.gef.requests.BendpointRequest;


/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.edit.policy</li>
 * <li>설  명 : ConnectionBendpointEditPolicy</li>
 * <li>작성일 : 009. 12. 22.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class ConnectionBendpointEditPolicy extends BendpointEditPolicy {

    /**
     * getCreateBendpoint
     *  
     * @param request
     * @return Command
     */
    public Command getCreateBendpoint(BendpointRequest request) {
        return getCreateBendpointCommand(request);
    }

    /**
     * @see org.eclipse.gef.editpolicies.BendpointEditPolicy#getCreateBendpointCommand(org.eclipse.gef.requests.BendpointRequest)
     */
    @Override
    protected Command getCreateBendpointCommand(BendpointRequest request) {
        CreateBendpointCommand cmd = new CreateBendpointCommand();
        Point p = request.getLocation();
        Connection conn = getConnection();

        conn.translateToRelative(p);
        cmd.setLocation(p);

        AbstractNode sourceNode = (AbstractNode) ((Relation) getHost().getModel()).getSource();
        AbstractNode targetNode = (AbstractNode) ((Relation) getHost().getModel()).getTarget();
        if (sourceNode.equals(targetNode)) {
            XYAnchor sourceAnchor = new XYAnchor(new Point(sourceNode.getX() + sourceNode.getWidth(), sourceNode.getY()
                + (int) (sourceNode.getHeight() / 2)));
            XYAnchor targetAnchor = new XYAnchor(new Point(targetNode.getX() + (int) (targetNode.getWidth() / 2),
                targetNode.getY() + targetNode.getHeight()));
            Point sourcePoint = sourceAnchor.getReferencePoint();
            Point targetPoint = targetAnchor.getReferencePoint();
            conn.translateToRelative(sourcePoint);
            conn.translateToRelative(targetPoint);

            int horizontalPoint = ((FigureCanvas) ((AbstractDiagramEditor) UiCorePlugin.getActivePage()
                .getActiveEditor()).getDiagramGraphicalViewer().getControl()).getHorizontalBar().getSelection();
            int verticalPoint = ((FigureCanvas) ((AbstractDiagramEditor) UiCorePlugin.getActivePage().getActiveEditor()).getDiagramGraphicalViewer()
                .getControl()).getVerticalBar().getSelection();
            Point scrollBarPoint = new Point(p.x + horizontalPoint, p.y + verticalPoint);
            cmd.setRelativeDimensions(scrollBarPoint.getDifference(sourcePoint),
                scrollBarPoint.getDifference(targetPoint));
            cmd.setModel(request.getSource().getModel());
            cmd.setFigure(request.getSource().getFigure());
            cmd.setIndex(request.getIndex());

            return cmd;
        } else {
            Point sourcePoint = getConnection().getSourceAnchor().getReferencePoint();
            Point targetPoint = getConnection().getTargetAnchor().getReferencePoint();

            conn.translateToRelative(sourcePoint);
            conn.translateToRelative(targetPoint);

            cmd.setRelativeDimensions(p.getDifference(sourcePoint), p.getDifference(targetPoint));

            cmd.setModel(request.getSource().getModel());
            cmd.setFigure(request.getSource().getFigure());
            cmd.setIndex(request.getIndex());

            return cmd;
        }
    }

    /**
     * @see org.eclipse.gef.editpolicies.BendpointEditPolicy#getMoveBendpointCommand(org.eclipse.gef.requests.BendpointRequest)
     */
    @Override
    protected Command getMoveBendpointCommand(BendpointRequest request) {
        MoveBendpointCommand cmd = new MoveBendpointCommand();
        Point p = request.getLocation();
        Connection conn = getConnection();

        conn.translateToRelative(p);
        cmd.setLocation(p);

        AbstractNode sourceNode = (AbstractNode) ((Relation) getHost().getModel()).getSource();
        AbstractNode targetNode = (AbstractNode) ((Relation) getHost().getModel()).getTarget();
        if (sourceNode.equals(targetNode)) {
            XYAnchor sourceAnchor = new XYAnchor(new Point(sourceNode.getX() + sourceNode.getWidth(), sourceNode.getY()
                + (int) (sourceNode.getHeight() / 2)));
            XYAnchor targetAnchor = new XYAnchor(new Point(targetNode.getX() + (int) (targetNode.getWidth() / 2),
                targetNode.getY() + targetNode.getHeight()));
            Point sourcePoint = sourceAnchor.getReferencePoint();
            Point targetPoint = targetAnchor.getReferencePoint();
            conn.translateToRelative(sourcePoint);
            conn.translateToRelative(targetPoint);

            int horizontalPoint = ((FigureCanvas) ((AbstractDiagramEditor) UiCorePlugin.getActivePage()
                .getActiveEditor()).getDiagramGraphicalViewer().getControl()).getHorizontalBar().getSelection();
            int verticalPoint = ((FigureCanvas) ((AbstractDiagramEditor) UiCorePlugin.getActivePage().getActiveEditor()).getDiagramGraphicalViewer()
                .getControl()).getVerticalBar().getSelection();
            Point scrollBarPoint = new Point(p.x + horizontalPoint, p.y + verticalPoint);
            cmd.setRelativeDimensions(scrollBarPoint.getDifference(sourcePoint),
                scrollBarPoint.getDifference(targetPoint));
            cmd.setModel(request.getSource().getModel());
            cmd.setFigure(request.getSource().getFigure());
            cmd.setIndex(request.getIndex());

            return cmd;
        } else {
            Point sourcePoint = getConnection().getSourceAnchor().getReferencePoint();
            Point targetPoint = getConnection().getTargetAnchor().getReferencePoint();

            conn.translateToRelative(sourcePoint);
            conn.translateToRelative(targetPoint);

            cmd.setRelativeDimensions(p.getDifference(sourcePoint), p.getDifference(targetPoint));

            cmd.setModel(request.getSource().getModel());
            cmd.setFigure(request.getSource().getFigure());
            cmd.setIndex(request.getIndex());

            return cmd;
        }
    }

    /**
     * @see org.eclipse.gef.editpolicies.BendpointEditPolicy#getDeleteBendpointCommand(org.eclipse.gef.requests.BendpointRequest)
     */
    @Override
    protected Command getDeleteBendpointCommand(BendpointRequest request) {
        DeleteBendpointCommand cmd = new DeleteBendpointCommand();
        Point p = request.getLocation();
        Connection conn = getConnection();

        conn.translateToRelative(p);
        cmd.setLocation(p);

        AbstractNode sourceNode = (AbstractNode) ((Relation) getHost().getModel()).getSource();
        AbstractNode targetNode = (AbstractNode) ((Relation) getHost().getModel()).getTarget();
        if (sourceNode.equals(targetNode)) {
            XYAnchor sourceAnchor = new XYAnchor(new Point(sourceNode.getX() + sourceNode.getWidth(), sourceNode.getY()
                + (int) (sourceNode.getHeight() / 2)));
            XYAnchor targetAnchor = new XYAnchor(new Point(targetNode.getX() + (int) (targetNode.getWidth() / 2),
                targetNode.getY() + targetNode.getHeight()));
            Point sourcePoint = sourceAnchor.getReferencePoint();
            Point targetPoint = targetAnchor.getReferencePoint();
            conn.translateToRelative(sourcePoint);
            conn.translateToRelative(targetPoint);

            int horizontalPoint = ((FigureCanvas) ((AbstractDiagramEditor) UiCorePlugin.getActivePage()
                .getActiveEditor()).getDiagramGraphicalViewer().getControl()).getHorizontalBar().getSelection();
            int verticalPoint = ((FigureCanvas) ((AbstractDiagramEditor) UiCorePlugin.getActivePage().getActiveEditor()).getDiagramGraphicalViewer()
                .getControl()).getVerticalBar().getSelection();
            Point scrollBarPoint = new Point(p.x + horizontalPoint, p.y + verticalPoint);
            cmd.setRelativeDimensions(scrollBarPoint.getDifference(sourcePoint),
                scrollBarPoint.getDifference(targetPoint));
            cmd.setModel(request.getSource().getModel());
            cmd.setFigure(request.getSource().getFigure());
            cmd.setIndex(request.getIndex());

            return cmd;
        } else {
            Point sourcePoint = getConnection().getSourceAnchor().getReferencePoint();
            Point targetPoint = getConnection().getTargetAnchor().getReferencePoint();

            conn.translateToRelative(sourcePoint);
            conn.translateToRelative(targetPoint);

            cmd.setRelativeDimensions(p.getDifference(sourcePoint), p.getDifference(targetPoint));

            cmd.setModel(request.getSource().getModel());
            cmd.setFigure(request.getSource().getFigure());
            cmd.setIndex(request.getIndex());

            return cmd;
        }
    }

    /**
     * callEraseConnectionFeedback
     *  
     * @param request void
     */
    public void callEraseConnectionFeedback(BendpointRequest request) {
        eraseConnectionFeedback(request);
    }
}
