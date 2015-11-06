/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.realization.sequencediagram.edit.policy;

import java.util.List;

import nexcore.tool.uml.manager.UMLPolicyManager;
import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.Attachement;
import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.LifeLineNode;
import nexcore.tool.uml.model.umldiagram.Line;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.model.umldiagram.Relation;
import nexcore.tool.uml.model.umldiagram.RelationType;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.diagram.command.ReconnectConnectionCommand;
import nexcore.tool.uml.ui.core.diagram.edit.part.AbstractNotationNodeEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.AttachementEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.DiagramRootEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.policy.DiagramGraphicalNodeEditPolicy;
import nexcore.tool.uml.ui.core.diagram.figure.FigureConstant;
import nexcore.tool.uml.ui.realization.sequencediagram.command.CreateMessageCommand;
import nexcore.tool.uml.ui.realization.sequencediagram.command.ReconnectMessageCommand;
import nexcore.tool.uml.ui.realization.sequencediagram.edit.part.LifeLineNameHeaderEditPart;
import nexcore.tool.uml.ui.realization.sequencediagram.edit.part.MessageEditPart;
import nexcore.tool.uml.ui.realization.sequencediagram.util.SequenceUtil;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.XYAnchor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.swt.widgets.ScrollBar;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.edit.policy</li>
 * <li>설 명 : SequenceDiagramGraphicalNodeEditPolicy</li>
 * <li>작성일 : 2009. 12. 21.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class SequenceDiagramGraphicalNodeEditPolicy extends DiagramGraphicalNodeEditPolicy {
    
    
    /**
     * @see org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#getCommand(org.eclipse.gef.Request)
     */
    @Override
    public Command getCommand(Request request) {
        return super.getCommand(request);
    }
    

    /**
     * @see org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#getConnectionCompleteCommand(org.eclipse.gef.requests.CreateConnectionRequest)
     */
    @Override
    protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
        if (getHost() instanceof LifeLineNameHeaderEditPart) {
            return null;
        }

        if (!(request.getStartCommand() instanceof CreateMessageCommand)) {
            return super.getConnectionCompleteCommand(request);
        }

        CreateMessageCommand startCommand = (CreateMessageCommand) request.getStartCommand();
        AbstractNode source;
        AbstractNode target;

        if (getHost().getModel() instanceof AbstractConnection) {
            return null;
        }
        source = (AbstractNode) startCommand.getSource();
        target = (AbstractNode) getHost().getModel();

        if (!UMLPolicyManager.isConnectableToTarget(startCommand.getConnection().getRelationType(),
            source.getNodeType(),
            target.getNodeType())) {
            return null;
        }

        if (source.equals(target)
            && RelationType.REPLY_MESSAGE.equals(((Relation) startCommand.getConnection()).getRelationType())) {
            return null;
        }
        
        startCommand.setTarget(target);
        Point location = new Point(request.getLocation());
        getHostFigure().translateToRelative(location);
        startCommand.setLocation(location);

        if(startCommand.getOperandNode() != null) {
            //Operand내에서 시작된 메시지는 같은 Operand 밖의 라이프라인과 생성 할 수 없다.
            if(isOutofRangeOperand(startCommand.getOperandNode(), location, SequenceUtil.getLifeLineNode(target))) {
                return null;
            }                        
        }
        
        if (startCommand.canExecute()) {
            return startCommand;
        }
        return null;

    }

    /**
     * target노드가 operandNode영역 밖인지를 검사한다.
     *  
     * @param operandNode
     * @param location 
     * @param target
     * @return boolean
     */
    private boolean isOutofRangeOperand(ContainerNode operandNode, Point location, AbstractNode target) {
        NotationNode combinedFragmentNode = (NotationNode) operandNode.getParent();
        int operandY = SequenceUtil.getOperandY(combinedFragmentNode, operandNode);
        int targetCenterX = target.getX() + target.getWidth()/2;
        if(combinedFragmentNode.getParent() instanceof Diagram) {
            if(!(targetCenterX >= combinedFragmentNode.getX()
                && targetCenterX<= combinedFragmentNode.getX() + combinedFragmentNode.getWidth())) {
                return true;
            }
        } else {
            ContainerNode parentOperandNode = (ContainerNode) combinedFragmentNode.getParent();
            NotationNode parentCombinedFagmentNode = (NotationNode) parentOperandNode.getParent();
            int absoluteX = SequenceUtil.translateToAbsoluteXByParentNode(combinedFragmentNode.getX(), parentCombinedFagmentNode);
            if(!(targetCenterX >= absoluteX
                    && targetCenterX <= absoluteX + combinedFragmentNode.getWidth())) {
                return true;
            }
        }
        if(!(location.y >= operandY
            && location.y <= operandY + operandNode.getHeight())) {
            return true;
        }
        return false;
    }

    /**
     * canvasSize
     */
    private static int canvasSize = -1;

    /**
     * @see org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#showCreationFeedback(org.eclipse.gef.requests.CreateConnectionRequest)
     */
    @Override
    protected void showCreationFeedback(CreateConnectionRequest request) {
        ScalableFreeformRootEditPart rootEditPart;
        rootEditPart = (ScalableFreeformRootEditPart) request.getSourceEditPart().getRoot();// getRootEditPart(request.getSourceEditPart());
        GraphicalViewer viewer = (GraphicalViewer) rootEditPart.getViewer();
        FigureCanvas canvas = (FigureCanvas) viewer.getControl();
        ScrollBar horizontalBar = canvas.getHorizontalBar();

        int xPoint = getHostFigure().getBounds().x + getHostFigure().getBounds().width/2;

        checkScroll(request);
        super.showCreationFeedback(request);
        
        Point point = null;
        if( getFeedbackLayer().getChildren().get(0) instanceof RectangleFigure ) {
            point = ((RectangleFigure) getFeedbackLayer().getChildren().get(0)).getLocation();
        } else if( getFeedbackLayer().getChildren().get(0) instanceof PolylineConnection ) {
            point = ((PolylineConnection) getFeedbackLayer().getChildren().get(0)).getSourceAnchor()
            .getReferencePoint();
        }
        
        Point xyPoint = null;
        if( request.getLocation().x < 0 ) {
            xyPoint = new Point(point);
        } else {
            xyPoint = new Point(xPoint - horizontalBar.getSelection(), point.y);
        }
        
        // +, -에 의한 확대/축소 시 포인트 반영 //
        DiagramRootEditPart root = (DiagramRootEditPart) getHost().getRoot();
        ZoomManager zoomManager = root.getZoomManager();
        double zoom = zoomManager.getZoom();
//        xyPoint.scale(zoom);
//        xyPoint.y -= canvas.getViewport().getViewLocation().y;
//        xyPoint.x -= canvas.getViewport().getViewLocation().x;
        // +, -에 의한 확대/축소 시 포인트 반영 //
        ConnectionAnchor cAnchor = new XYAnchor(xyPoint);
        ((PolylineConnection) getFeedbackLayer().getChildren().get(0)).setSourceAnchor(cAnchor);

    }

    /**
     * checkScroll
     *  
     * @param request void
     */
    private void checkScroll(final CreateConnectionRequest request) {
        ScalableFreeformRootEditPart rootEditPart;
        rootEditPart = (ScalableFreeformRootEditPart) request.getSourceEditPart().getRoot();// getRootEditPart(request.getSourceEditPart());
        GraphicalViewer viewer = (GraphicalViewer) rootEditPart.getViewer();
        FigureCanvas canvas = (FigureCanvas) viewer.getControl();
        ScrollBar horizontalBar = canvas.getHorizontalBar();
        Viewport viewport = canvas.getViewport();

        canvas.setVerticalScrollBarVisibility(FigureCanvas.ALWAYS);

        viewport.getVerticalRangeModel().getMaximum();
        if (canvas.getSize().x - 50 < request.getLocation().x) {
            if (request.getLocation().x > (canvas.getSize().x - 50) + horizontalBar.getSelection()) {
                viewport.setHorizontalLocation(request.getLocation().x - (canvas.getSize().x - 50)
                    + horizontalBar.getSelection());
            }
        } else if (request.getLocation().x < 10 && request.getLocation().x > 0) {
            if (request.getLocation().x - 30 > horizontalBar.getSelection()) {
                viewport.setHorizontalLocation((request.getLocation().x - 30) + horizontalBar.getSelection());
            }
        }
    }

    /**
     * @see org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#getConnectionCreateCommand(org.eclipse.gef.requests.CreateConnectionRequest)
     */
    @Override
    protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
        if (getHost() instanceof LifeLineNameHeaderEditPart) {
            return null;
        }

        if (request.getNewObject() instanceof Attachement) {
            return super.getConnectionCreateCommand(request);
        }

        Point location = new Point(request.getLocation());
        getHostFigure().translateToRelative(location);

        CreateMessageCommand command = new CreateMessageCommand((AbstractNotationNodeEditPart) getHost(),
            request.getLocation());
        AbstractConnection connection = (AbstractConnection) request.getNewObject();

        connection.setX(location.x);
        int messageTopMargin = FigureConstant.FIGURE_LIFELINE_TOP_MARGIN + FigureConstant.FIGURE_LIFELINE_HEAD_HEIGHT
            + FigureConstant.FIGURE_LIFELINE_TOP_MARGIN;
        if (location.y < messageTopMargin) {
            connection.setY(messageTopMargin);
        } else {
            connection.setY(location.y);
        }

        AbstractView objNode = (AbstractNode) (getHost().getModel());

        if (objNode instanceof AbstractConnection) {
            return null;
        }
        AbstractNode sourceNode = (AbstractNode) objNode;

        if (!UMLPolicyManager.isConnectableFromSource(connection.getRelationType(), sourceNode.getNodeType())) {
            return null;
        }
        
        //결합단편 내에서 생성되는 메시지인지 체크
        Diagram diagram = SequenceUtil.getDiagram(getHost());
        if(SequenceUtil.isDiagramHasCombinedFragement(diagram)) {
            NotationNode rangedCombinedFragment = getRangedSourceLifelineByCombinedFragment(diagram, connection, sourceNode);
            if(rangedCombinedFragment != null) {
                //결합단편내 Operand를 찾는다.
                ContainerNode opernadNode = getRangedOperand(rangedCombinedFragment, connection, sourceNode);
                if(opernadNode == null) {
                    return null;
                }
                List<NotationNode> combindFragmentNodeList = SequenceUtil.getOnlyCombindFragmentNodeList(opernadNode.getNodeList());
                if(isThereOtherCombinedFragmentYArea(combindFragmentNodeList, connection)) {
                    return null;
                }
                command.setOperandNode(opernadNode);
            } else {
                List<NotationNode> combindFragmentNodeList = SequenceUtil.getOnlyCombindFragmentNodeList(diagram.getNodeList());;
                if(isThereOtherCombinedFragmentYArea(combindFragmentNodeList, connection)) {
                    return null;
                }
            }
        }
        

        command.setDiagram(SequenceUtil.getDiagram(getHost()));
        command.setConnection(connection);
        command.setSource(sourceNode);
        request.setStartCommand(command);

        return command;

    }

    /**
     * combinedFragmentNode에서 connection이 포함될 실제 InterantionOperand를 찾는다.
     *  
     * @param rangedCombinedFragmentList
     * @param connection
     * @param sourceNode 
     * @param sourceNode
     * @return ContainerNode
     */
    private ContainerNode getRangedOperand(NotationNode combinedFragmentNode, AbstractConnection connection, AbstractNode sourceNode) {
        EList<AbstractNode> OperandNodeList = combinedFragmentNode.getCompartmentList();
        LifeLineNode lifeLineNode = SequenceUtil.getLifeLineNode(sourceNode);
        int sourceNodeX = lifeLineNode.getX() + lifeLineNode.getWidth()/2;
        for(AbstractNode abstractNode : OperandNodeList) {
            ContainerNode containerNode = (ContainerNode) abstractNode;
            int operandY = SequenceUtil.getOperandY(combinedFragmentNode, containerNode);
            int operandHeight = containerNode.getHeight();
            if(OperandNodeList.indexOf(abstractNode) == 0) {
                operandY -= UICoreConstant.FRAGMENT_LABEL_HEIGHT;
                operandHeight += UICoreConstant.FRAGMENT_LABEL_HEIGHT; 
            }
            if(connection.getY() > operandY && connection.getY() < operandY  + operandHeight) {
                List<NotationNode> combindFragmentNodeList = SequenceUtil.getOnlyCombindFragmentNodeList(containerNode.getNodeList());
                if(combindFragmentNodeList.size() > 0) {
                    for(NotationNode innerNode : combindFragmentNodeList) {
                        int absoluteY = SequenceUtil.translateToAbsoluteYByParentNode(innerNode.getY(), containerNode, combinedFragmentNode);
                        int absoluteX = SequenceUtil.translateToAbsoluteXByParentNode(innerNode.getX(), combinedFragmentNode);
                        if(sourceNodeX >= absoluteX
                            && sourceNodeX <= absoluteX + innerNode.getWidth()
                            && connection.getY() > absoluteY
                            && connection.getY() < absoluteY + innerNode.getHeight()) {
                            return getRangedOperand(innerNode, connection, sourceNode);
                        }
                    }
                    return containerNode;
                } else {
                    return containerNode;
                }
            }
        }
        
        return null;
    }
    

    /**
     * 메시지를 생성하려는 위치가 다른 combinedFragment y범위에 있는지 검사
     *  
     * @param diagram
     * @param connection
     * @return boolean
     */
    private boolean isThereOtherCombinedFragmentYArea(List<NotationNode> combindFragmentNodeList, AbstractConnection connection) {
        int fragmentY = 0;
        for(NotationNode combinedFragmentNode : combindFragmentNodeList) {
            fragmentY = combinedFragmentNode.getY();
            if(combinedFragmentNode.getParent() instanceof ContainerNode) {
                ContainerNode parentNode = (ContainerNode) combinedFragmentNode.getParent();
                fragmentY = SequenceUtil.translateToAbsoluteYByParentNode(fragmentY, parentNode, (NotationNode) parentNode.getParent());
            }
            if(connection.getY() >= fragmentY
                && connection.getY() <= fragmentY + combinedFragmentNode.getHeight()) {
                return true;
            }
        }
        return false;
    }


    /**
     * 소스 라이프라인이 CombinedFragment 범위에 포함되어 있는지 확인. CoveredBy는 아니고 그림영역으로만 확인
     * @param diagram 
     *  
     * @param connection
     * @param sourceNode 
     * @return boolean
     */
    private NotationNode getRangedSourceLifelineByCombinedFragment(Diagram diagram, AbstractConnection connection, AbstractNode sourceNode) {
        LifeLineNode lifeLineNode = SequenceUtil.getLifeLineNode(sourceNode);
        int sourceNodeX = lifeLineNode.getX() + lifeLineNode.getWidth()/2;
        List<NotationNode> combindFragmentNodeList = SequenceUtil.getOnlyCombindFragmentNodeList(diagram.getNodeList());
        for(NotationNode combinedFragmentNode : combindFragmentNodeList) {
            if(sourceNodeX >= combinedFragmentNode.getX()
                && sourceNodeX <= combinedFragmentNode.getX() + combinedFragmentNode.getWidth()
                && connection.getY() >= combinedFragmentNode.getY()
                && connection.getY() <= combinedFragmentNode.getY() + combinedFragmentNode.getHeight()) {
                return combinedFragmentNode;
            }
        }
        
        return null;
    }

    /**
     * @see org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#getReconnectSourceCommand(org.eclipse.gef.requests.ReconnectRequest)
     */
    @Override
    protected Command getReconnectSourceCommand(ReconnectRequest request) {
        Diagram diagram = SequenceUtil.getDiagram(getHost());
        if (request.getConnectionEditPart() instanceof AttachementEditPart) {
            ReconnectConnectionCommand command = new ReconnectConnectionCommand();
            command.setConnectionModel(request.getConnectionEditPart().getModel());
            command.setNewSource(getHost().getModel());
            command.setDiagram(diagram);
            command.setSourceAnchorPoint(request.getLocation());
            return command;
        } else {
            if (request.getConnectionEditPart() instanceof MessageEditPart) {
                AbstractConnection connection = (AbstractConnection) ((MessageEditPart) request.getConnectionEditPart()).getModel();
                if (RelationType.REPLY_MESSAGE.equals(connection.getRelationType())) {
                    return null;
                }
                if (connection.getTarget() != null && connection.getSource() != null) {
                    LifeLineNode sourceNode = SequenceUtil.getLifeLineNode((AbstractNode) connection.getSource());
                    LifeLineNode targetNode = SequenceUtil.getLifeLineNode((AbstractNode) connection.getTarget());
                    if (sourceNode.equals(targetNode)) {
                        return null;
                    }
                }
                // Message의 Reconnect는 Line으로만 할 수 있다.
                if (!(getHost().getModel() instanceof Line)) {
                    return null;
                }
                if(SequenceUtil.isIncludedOperand(connection)) {
                    LifeLineNode newNode = SequenceUtil.getLifeLineNode((AbstractNode) getHost().getModel());
                    if(SequenceUtil.checkOutofRangeOperandReconnectMessage(diagram, connection, newNode)) {
                        return null;
                    }
                }
                ReconnectMessageCommand command = new ReconnectMessageCommand();
                command.setConnectionModel(request.getConnectionEditPart().getModel());
                command.setNewSource(getHost().getModel());
                command.setDiagram(diagram);
                return command;
            }
        }
        return null;
    }

    /**
     * @see org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#getReconnectTargetCommand(org.eclipse.gef.requests.ReconnectRequest)
     */
    @Override
    protected Command getReconnectTargetCommand(ReconnectRequest request) {
        Diagram diagram = SequenceUtil.getDiagram(getHost());
        if (request.getConnectionEditPart() instanceof AttachementEditPart) {
            ReconnectConnectionCommand command = new ReconnectConnectionCommand();
            command.setConnectionModel(request.getConnectionEditPart().getModel());
            command.setNewTarget(getHost().getModel());
            command.setDiagram(diagram);
            command.setTargetAnchorPoint(request.getLocation());
            return command;
        } else {
            if (request.getConnectionEditPart() instanceof MessageEditPart) {
                AbstractConnection connection = (AbstractConnection) ((MessageEditPart) request.getConnectionEditPart()).getModel();
                if (connection.getTarget() != null && connection.getSource() != null) {
                    LifeLineNode sourceNode = SequenceUtil.getLifeLineNode((AbstractNode) connection.getSource());
                    LifeLineNode targetNode = SequenceUtil.getLifeLineNode((AbstractNode) connection.getTarget());
                    if (sourceNode.equals(targetNode)) {
                        return null;
                    }
                    
                }
                // Message의 Reconnect는 Line으로만 할 수 있다.
                if (!(getHost().getModel() instanceof Line)) {
                    return null;
                }
                if(SequenceUtil.isIncludedOperand(connection)) {
                    LifeLineNode newNode = SequenceUtil.getLifeLineNode((AbstractNode) getHost().getModel());
                    if(SequenceUtil.checkOutofRangeOperandReconnectMessage(diagram, connection, newNode)) {
                        return null;
                    }
                }
                ReconnectMessageCommand command = new ReconnectMessageCommand();
                command.setConnectionModel(request.getConnectionEditPart().getModel());
                command.setNewTarget(getHost().getModel());
                command.setDiagram(diagram);
                return command;
            }
        }        
        return null;
    }

    

}
