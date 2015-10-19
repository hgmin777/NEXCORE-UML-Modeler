/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.core.diagram.edit.policy;

import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.model.umldiagram.LabelNode;
import nexcore.tool.uml.model.umldiagram.LabelType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.core.diagram.edit.part.AbstractNotationNodeEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.DiagramRootEditPart;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.XYAnchor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.swt.SWT;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.edit.policy</li>
 * <li>설  명 : LabelNodeFeedbackEditPolicy</li>
 * <li>작성일 : 2011. 2. 8.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class LabelNodeFeedbackEditPolicy extends NonResizableEditPolicy {

    /**
     * @see org.eclipse.gef.editpolicies.NonResizableEditPolicy#createDragSourceFeedbackFigure()
     */
    @Override
    protected IFigure createDragSourceFeedbackFigure() {

        LabelNode nodeLabel = (LabelNode) ((AbstractNotationNodeEditPart) getHost()).getModel();
        if (nodeLabel.getType().equals(LabelType.LABEL) || nodeLabel.getType().equals(LabelType.STEREOTYPE)) {
            nodeLabel = (LabelNode) nodeLabel.getParent();
        }
        if (nodeLabel == null) {
            return null;
        }
        AbstractView parentNode = null;

        if (nodeLabel.getParent() != null) {
            if (nodeLabel.getParent() instanceof NotationNode) {
                parentNode = (NotationNode) nodeLabel.getParent();
            } else if (nodeLabel.getParent() instanceof ContainerNode) {
                parentNode = (ContainerNode) nodeLabel.getParent();
            } else if (nodeLabel.getParent() instanceof AbstractConnection) {
                parentNode = (AbstractConnection) nodeLabel.getParent();
            }
        }

        if (parentNode == null)
            return null;

        if (parentNode instanceof AbstractConnection) {
            return getLabelFeedBackWithConnectionParent(nodeLabel, parentNode);
        } else {
            return getLabelFeedBackWithNotationParent(nodeLabel, parentNode);
        }
    }

    /**
     * 
     * 
     * @param nodeLabel
     * @param parentNode
     * @return IFigure
     */
    private IFigure getLabelFeedBackWithConnectionParent(LabelNode nodeLabel, AbstractView parentNode) {

        RectangleFigure sFigure = new RectangleFigure();
        Point targetPoint = getTargetPoint(nodeLabel);

        // +, -에 의한 확대/축소 시 포인트 반영 //
        ScalableFreeformRootEditPart rootEditPart;
        rootEditPart = (ScalableFreeformRootEditPart) getHost().getRoot();
        GraphicalViewer viewer = (GraphicalViewer) rootEditPart.getViewer();
        FigureCanvas canvas = (FigureCanvas) viewer.getControl();
        
        DiagramRootEditPart root = (DiagramRootEditPart) getHost().getRoot();
        ZoomManager zoomManager = root.getZoomManager();
        double zoom = zoomManager.getZoom();
        targetPoint.scale(zoom);
        
        targetPoint.y -= canvas.getViewport().getViewLocation().y;
        targetPoint.x -= canvas.getViewport().getViewLocation().x;
        // +, -에 의한 확대/축소 시 포인트 반영 //
        
        XYAnchor targetAnchor = new XYAnchor(targetPoint);
        IFigure sourceFigure = null;

        PolylineConnection connection = new PolylineConnection();
        connection.setLineStyle(SWT.LINE_DOT);

        IFigure figure = super.createDragSourceFeedbackFigure();
        figure.setOpaque(false);
        figure.setBorder(new LineBorder(ColorConstants.black));


        if (figure != null) {
            sourceFigure = figure;
        } else {
            sourceFigure = new RoundedRectangle();
            sourceFigure.setSize(nodeLabel.getX() + parentNode.getX(), nodeLabel.getY() + parentNode.getY());
        }

        connection.setSourceAnchor(new ChopboxAnchor(sourceFigure));
        connection.setTargetAnchor(targetAnchor);

        addFeedback(sourceFigure);
        addFeedback(connection);

        return sourceFigure;
    }

    /**
     * 
     * 
     * @param nodeLabel
     * @param parentNode
     * @return IFigure
     */
    private IFigure getLabelFeedBackWithNotationParent(LabelNode nodeLabel, AbstractView parentNode) {
        RectangleFigure sFigure = new RectangleFigure();
        RectangleFigure tFigure = new RectangleFigure();

        sFigure.setLocation(new Point(nodeLabel.getX() + parentNode.getX(), nodeLabel.getY() + parentNode.getY()));
        sFigure.setSize(nodeLabel.getWidth(), nodeLabel.getHeight());
        
        // +, -에 의한 확대/축소 시 포인트 반영 //
        ScalableFreeformRootEditPart rootEditPart;
        rootEditPart = (ScalableFreeformRootEditPart) getHost().getRoot();
        GraphicalViewer viewer = (GraphicalViewer) rootEditPart.getViewer();
        FigureCanvas canvas = (FigureCanvas) viewer.getControl();
        Point targetPoint = new Point(parentNode.getX(), parentNode.getY());
        DiagramRootEditPart root = (DiagramRootEditPart) getHost().getRoot();
        ZoomManager zoomManager = root.getZoomManager();
        double zoom = zoomManager.getZoom();
        targetPoint.scale(zoom);
        
        targetPoint.y -= canvas.getViewport().getViewLocation().y;
        targetPoint.x -= canvas.getViewport().getViewLocation().x;
        tFigure.setLocation(targetPoint);
        // +, -에 의한 확대/축소 시 포인트 반영 //
        
        if (parentNode instanceof AbstractNode) {
            tFigure.setSize(((AbstractNode) parentNode).getWidth(), ((AbstractNode) parentNode).getHeight());
        }

        ChopboxAnchor targetAnchor = new ChopboxAnchor(tFigure);
        IFigure sourceFigure = null;

        PolylineConnection connection = new PolylineConnection();
        connection.setLineStyle(SWT.LINE_DOT);

        IFigure figure = super.createDragSourceFeedbackFigure();
        figure.setOpaque(false);
        figure.setBorder(new LineBorder(ColorConstants.black));

        if (figure != null) {
            sourceFigure = figure;
        } else {
            sourceFigure = new RoundedRectangle();
            sourceFigure.setSize(nodeLabel.getX() + parentNode.getX(), nodeLabel.getY() + parentNode.getY());
        }

        connection.setSourceAnchor(new ChopboxAnchor(sourceFigure));
        connection.setTargetAnchor(targetAnchor);

        addFeedback(sourceFigure);
        addFeedback(connection);

        return sourceFigure;
    }

    /**
     * @see org.eclipse.gef.editpolicies.NonResizableEditPolicy#getMoveCommand(org.eclipse.gef.requests.ChangeBoundsRequest)
     */
    @Override
    protected Command getMoveCommand(ChangeBoundsRequest request) {
        return null;
    }

    /**
     * getTargetPoint
     *  
     * @param nodeLabel
     * @return Point
     */
    private Point getTargetPoint(LabelNode nodeLabel) {
        AbstractConnection abstractConnection = (AbstractConnection) nodeLabel.getParent();

        AbstractNode sourceNode = (AbstractNode) abstractConnection.getSource();
        AbstractNode targetNode = (AbstractNode) abstractConnection.getTarget();

        RectangleFigure sourceFigure = new RectangleFigure();
        sourceFigure.setSize(sourceNode.getWidth(), sourceNode.getHeight());
        sourceFigure.setLocation(new Point(sourceNode.getX(), sourceNode.getY()));
        RectangleFigure targetFigure = new RectangleFigure();
        targetFigure.setSize(targetNode.getWidth(), targetNode.getHeight());
        targetFigure.setLocation(new Point(targetNode.getX(), targetNode.getY()));

        ChopboxAnchor sourceAnchor = new ChopboxAnchor(sourceFigure);
        ChopboxAnchor targetAnchor = new ChopboxAnchor(targetFigure);

        Point sourceAnchorLocation = new Point();
        Point targetAnchorLocation = new Point();
        sourceAnchorLocation = sourceAnchor.getLocation(targetAnchor.getReferencePoint());
        targetAnchorLocation = targetAnchor.getLocation(sourceAnchor.getReferencePoint());
        // sourceAnchorLocation = sourceAnchor.getReferencePoint();
        // targetAnchorLocation = targetAnchor.getReferencePoint();

        Point source = new Point(sourceAnchorLocation.x, sourceAnchorLocation.y);
        Point target = new Point(targetAnchorLocation.x, targetAnchorLocation.y);

        if (nodeLabel.getType().equals(LabelType.SOURCE_ROLE) || nodeLabel.getType().equals(LabelType.SOURCE_MULTIPLEX)) {
            return source;
        } else if (nodeLabel.getType().equals(LabelType.TARGET_ROLE)
            || nodeLabel.getType().equals(LabelType.TARGET_MULTIPLEX)) {
            return target;
        } else {
            return new Point((source.x + target.x) / 2, (source.y + target.y) / 2);
        }
    }
    
    /**
     * @see org.eclipse.gef.editpolicies.GraphicalEditPolicy#removeFeedback(org.eclipse.draw2d.IFigure)
     */
    @Override
    protected void removeFeedback(IFigure figure) {
        if( getFeedbackLayer().getChildren().isEmpty() ){
            return;
        } 
        if( !getFeedbackLayer().getChildren().contains(figure) ){
            return;
        }
        super.removeFeedback(figure);
    }
    
    /**
     * @see org.eclipse.gef.editpolicies.NonResizableEditPolicy#showChangeBoundsFeedback(org.eclipse.gef.requests.ChangeBoundsRequest)
     */
    @Override
    protected void showChangeBoundsFeedback(ChangeBoundsRequest request) {
        if( null == getDragSourceFeedbackFigure()) {
            return;
        }
        super.showChangeBoundsFeedback(request);
    }
}
