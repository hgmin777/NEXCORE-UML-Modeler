/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.realization.sequencediagram.edit.policy;

import java.util.ArrayList;
import java.util.List;

import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.model.umldiagram.RelationType;
import nexcore.tool.uml.model.umldiagram.UMLDiagramFactory;
import nexcore.tool.uml.ui.core.diagram.figure.FigureConstant;
import nexcore.tool.uml.ui.realization.sequencediagram.command.MoveMessageCommand;
import nexcore.tool.uml.ui.realization.sequencediagram.command.MoveMessageListCommand;
import nexcore.tool.uml.ui.realization.sequencediagram.edit.part.MessageEditPart;
import nexcore.tool.uml.ui.realization.sequencediagram.util.SequenceUtil;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.gef.requests.BendpointRequest;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.uml2.uml.CombinedFragment;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.InteractionOperand;
import org.eclipse.uml2.uml.Message;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.edit.policy</li>
 * <li>설 명 : MessageBendpointEditPolicy</li>
 * <li>작성일 : 2010. 1. 13.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class MessageBendpointEditPolicy extends SequenceBendpointEditPolicy {

    /** Message 이동 피드백 */
    private IFigure feedback;

    /** margin */
    private final int margin = 10;
    
    private Diagram diagram = null;

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

        checkScroll(request);

        Point location = new Point(request.getLocation());
        getHostFigure().translateToRelative(location);

        // if (getHost().getParent().getViewer().getSelectedEditParts().size() >1) {
        // // 2개 이상을 선택하고 한번에 이동 할 수 없다.
        // return null;
        // }

        if (!(request.getSource() instanceof MessageEditPart)) {
            // MessageEditPart 만 이동할 수 있다.
            return null;
        }

        if (location.y < FigureConstant.FIGURE_LIFELINE_TOP_MARGIN + FigureConstant.FIGURE_LIFELINE_HEAD_HEIGHT
            + margin) {
            // 위 위치보다 더 위로 이동 할 수 없다.
            return null;
        }

        MessageEditPart messageEditPart = (MessageEditPart) request.getSource();
        AbstractConnection messageConnection = (AbstractConnection) messageEditPart.getModel();
        diagram = (Diagram) SequenceUtil.getLifeLineNode((AbstractNode) messageConnection.getSource()).eContainer();
        if(diagram == null) {
            return null;
        }
        
        if(SequenceUtil.isIncludedOperand(messageConnection)) {
            if(SequenceUtil.checkOutofRangeOperandMoveMessage(diagram, messageConnection, location)) {
                return null;
            }
        } else {
            if(SequenceUtil.isDiagramHasCombinedFragement(diagram)) {
                if(SequenceUtil.isThereCombinedFragment(diagram, messageConnection, location)) {
                    if( canMove() == null ) {
                        return null;
                    }
                }
            }
        }
        
        List<NotationNode> diagramLevelFragments = SequenceUtil.getOnlyCombindFragmentNodeList(diagram.getNodeList());
        for (NotationNode abstractNode : diagramLevelFragments) {
            NotationNode behavior = (NotationNode) messageConnection.getTarget();
            int diff = location.y - ((AbstractConnection) messageEditPart.getModel()).getY();
            if (behavior.getY() < abstractNode.getY() && abstractNode.getY() - 10 < behavior.getY() + behavior.getHeight() + diff) {
                if( abstractNode.getY() + abstractNode.getHeight() > location.y ) {
                    return null;
                }
            }
        }

        if (RelationType.REPLY_MESSAGE.equals(messageConnection.getRelationType())) {
            NotationNode sourceBehaviorNode = (NotationNode) messageConnection.getSource();
            if (sourceBehaviorNode.getY() >= location.y) {
                // Reply일 경우 상위 메시지 보다 위로 움직일 수 없다.
                return null;
            }
        }
        
        NotationNode currentFragmentNode = null;
        NotationNode currentAncestorNode = null;
        // 현재 선택된 ContainerNode
        ContainerNode currentInteractionOperandNode = null;
        
        // 2. Behavior와 같은 프래그먼트에 소속된 동일 레벨의 프래그먼트만 시프트
        List<NotationNode> allFragments = SequenceUtil.getAllCombinedFragmentNodeInDiagram(diagram);
        // Behavior의 메시지를 가지고 Behavior가 속한 프래그먼트를 찾는다.
        NotationNode behavior = null;
        if( messageConnection.getRelationType() == RelationType.REPLY_MESSAGE ) {
            behavior = (NotationNode) messageConnection.getSource();
        } else {
            behavior = (NotationNode) messageConnection.getTarget();
        }
        AbstractConnection behaviorMessage = behavior.getIncomingConnectionList().get(0);
        List<InteractionFragment> interactionFragments = SequenceUtil.getFragmentListByMessage((Message) behaviorMessage.getUmlModel());

        if( !interactionFragments.isEmpty() ) {
            InteractionFragment firstFragment = interactionFragments.get(0);
            if( firstFragment.eContainer() instanceof InteractionOperand ) {
                InteractionOperand firstOperand = (InteractionOperand) firstFragment.eContainer();
                for (NotationNode notationNode : allFragments) {
                    if( notationNode.getUmlModel() instanceof CombinedFragment ) {
                        for( AbstractNode notationChild : notationNode.getCompartmentList() ) {
                            if( notationChild instanceof ContainerNode ) {
                                ContainerNode container = (ContainerNode) notationChild;
                                if( container.getUmlModel().equals(firstOperand) ) {
                                    currentInteractionOperandNode = container;
                                }
                            }
                        }
                        CombinedFragment combinedFragment = (CombinedFragment) notationNode.getUmlModel();
                        if (combinedFragment.getOperands().contains(firstOperand)) {
                            currentFragmentNode = notationNode;
                            currentAncestorNode = SequenceUtil.getAncestor(currentFragmentNode);
                            break;
                        }
                    }
                }
            }
            
            int absoluteY = 0;
            int parentNodeHeight = 0;
            if( currentInteractionOperandNode != null ) {
                if( currentInteractionOperandNode.getParent() instanceof NotationNode ) {
                    NotationNode parentFragment = (NotationNode) currentInteractionOperandNode.getParent();
                    parentNodeHeight = parentFragment.getHeight();
                    if( parentFragment.getParent() instanceof Diagram ) {
                        absoluteY = parentFragment.getY() + currentInteractionOperandNode.getY();
                    } else if( parentFragment.getParent() instanceof ContainerNode ) {
                        ContainerNode parentContainerNode = (ContainerNode) parentFragment.getParent();
                        absoluteY = SequenceUtil.translateToAbsoluteYByParentNode(0, parentContainerNode, parentFragment);
                    }
                }
                // if-else의 경우 메시지가 있는 위치의 ContainerNode 까지의 높이만 계산.
                int currentNodeHeight = 0;
                for( AbstractNode child : currentFragmentNode.getCompartmentList() ) {
                    currentNodeHeight += child.getHeight();
                    if(child.equals(currentInteractionOperandNode)) {
                        break; 
                    }
                }
                int delta = location.y - ((AbstractConnection) messageEditPart.getModel()).getY();
                if( delta > 0 ) {
                    if( behavior.getY() + behavior.getHeight() + delta > absoluteY + currentNodeHeight) {
                        return null;
                    }
                } else {
                    currentNodeHeight = 0;
                    for( AbstractNode child : currentFragmentNode.getCompartmentList() ) {
                        if(child.equals(currentInteractionOperandNode)) {
                            break; 
                        }
                        currentNodeHeight += child.getHeight();
                    }
                    if( behavior.getY() + delta < absoluteY + currentNodeHeight) {
                        return null;
                    }
                }
                
                if( currentFragmentNode != null ) {
                    for( AbstractNode abstractNode : currentFragmentNode.getCompartmentList() ) {
                        if( abstractNode instanceof ContainerNode ) {
                            ContainerNode container = (ContainerNode) abstractNode;
                            for( AbstractNode childNode : container.getNodeList() ) {
                                if( childNode instanceof NotationNode ) {
                                    NotationNode notation = (NotationNode) childNode;
                                    if( notation.getUmlModel() instanceof CombinedFragment ) {
                                        int absoluteYpoint = SequenceUtil.translateToAbsoluteYByParentNode(0, container, notation);
                                        if( behavior.getY() < absoluteYpoint && behavior.getY() + behavior.getHeight() < absoluteYpoint && absoluteYpoint - 21 < behavior.getY() + behavior.getHeight() + delta) {
                                            return null;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        
        List<MessageEditPart> listEditPart = new ArrayList<MessageEditPart>();
        for (Object editPart : getHost().getParent().getViewer().getSelectedEditParts()) {
            if (editPart instanceof MessageEditPart) {
                listEditPart.add((MessageEditPart) editPart);
            }
        }
        if (listEditPart.size() > 1) {
            int diff = location.y - ((AbstractConnection) messageEditPart.getModel()).getY();
            return new MoveMessageListCommand(listEditPart, location, diff);
        } else {
            if (messageConnection.getY() != location.y) {
                
                NotationNode node = canMove();
                if( node != null ) {
                    Rectangle bounds = new Rectangle();
                    SequenceUtil.getNewBounds(bounds, node);
                    if( bounds.y + node.getHeight() < location.y ) {
                        return null;
                    }
                }
                
                return new MoveMessageCommand(messageEditPart, location);
            } else {
                // 상하로만 이동 할 수 있다.
                return null;
            }
        }
    }
    
    /**
     * 
     *  
     * @return boolean
     */
    private NotationNode canMove() {
        NotationNode node = null;
        NotationNode behavior = null;
        
        if( getHost().getModel() instanceof AbstractConnection ) {
            AbstractConnection absConn = (AbstractConnection) getHost().getModel();
            if( absConn.getSource() instanceof AbstractNode ) {
                AbstractNode sourceNode = (AbstractNode) absConn.getSource();
                if( sourceNode.getNodeType() == NodeType.LIFE_LINE_BEHAVIOR ) {
                    behavior = (NotationNode) sourceNode;
                }
            } else if( absConn.getTarget() instanceof AbstractNode ) {
                AbstractNode targetNode = (AbstractNode) absConn.getTarget();
                if( targetNode.getNodeType() == NodeType.LIFE_LINE_BEHAVIOR ) {
                    behavior = (NotationNode) targetNode;
                }
            }
        }
        if( diagram == null ) {
            diagram = SequenceUtil.getDiagramByEContainer(behavior);
        }
        
        List<AbstractConnection> connectionList = SequenceUtil.getChildrenMessageListByBehavior(behavior);
        List<InteractionFragment> fragmentList = new ArrayList<InteractionFragment>();
        for (AbstractConnection connection : connectionList) {
            if (connection.getUmlModel() instanceof Message) {
                for (InteractionFragment fragment : SequenceUtil.getFragmentListByMessage((Message) connection.getUmlModel())) {
                    if (!fragmentList.contains(fragment)) {
                        fragmentList.add(fragment);
                    }
                }
            }
        }
        for (InteractionFragment fragment : fragmentList) {
            
            Element parent = fragment.getOwner();
            
            if( parent instanceof InteractionOperand ) {
                InteractionOperand operand = (InteractionOperand) parent;
                
                Element operandParent = operand.getOwner();
                if( operandParent instanceof CombinedFragment ) {
                    List<NotationNode> nodeList = SequenceUtil.getAllCombinedFragmentNodeInDiagram(diagram);
                    for( NotationNode child : nodeList ) {
                        if( child.getUmlModel().equals(operandParent) ) {
                            node = child;
                            break;
                        }
                    }
                }
            }
        }
        
        if( node == null ) {
            return node;
        }
        return node;
    }



    /**
     * checkScroll
     * 
     * 스크롤바 범위 밖으로 메시지를 이동 시킬때 스크롤을 강제적으로 이동시켜줌
     * 
     * @param request
     *            void
     */
    private void checkScroll(BendpointRequest request) {
        GraphicalViewer viewer = (GraphicalViewer) ((ScalableFreeformRootEditPart) request.getSource().getParent()).getViewer();
        FigureCanvas canvas = (FigureCanvas) viewer.getControl();
        ScrollBar verticalBar = canvas.getVerticalBar();
        Viewport viewport = canvas.getViewport();

        if (canvas.getSize().y < request.getLocation().y) {
            viewport.setVerticalLocation(request.getLocation().y - canvas.getSize().y + verticalBar.getSelection());
        } else if (request.getLocation().y < 0) {
            viewport.setVerticalLocation(request.getLocation().y + verticalBar.getSelection());
        }
    }

    /**
     * @see org.eclipse.gef.editpolicies.BendpointEditPolicy#showCreateBendpointFeedback(org.eclipse.gef.requests.BendpointRequest)
     */
    @Override
    protected void showCreateBendpointFeedback(BendpointRequest request) {

        IFigure feedback = getDragSourceFeedbackFigure();

        Rectangle bounds = new Rectangle(getInitialFeedbackBounds().getCopy());
        PrecisionRectangle rect = new PrecisionRectangle(bounds);
        getHostFigure().translateToAbsolute(rect);
        Point movePoint = request.getLocation();
        
        movePoint.x = ((GraphicalEditPart) getHost()).getFigure().getBounds().x;//movePoint.x - rect.getSize().width / 2;
        rect.setLocation(movePoint);
        feedback.translateToRelative(rect);
        feedback.setBounds(rect);
    }

    /**
     * @see org.eclipse.gef.editpolicies.BendpointEditPolicy#eraseConnectionFeedback(org.eclipse.gef.requests.BendpointRequest)
     */
    @Override
    protected void eraseConnectionFeedback(BendpointRequest request) {
        super.eraseConnectionFeedback(request);
        if (feedback != null) {
            removeFeedback(feedback);
        }
        feedback = null;
    }

    /**
     * Lazily creates and returns the feedback figure used during drags.
     * 
     * @return the feedback figure
     */
    protected IFigure getDragSourceFeedbackFigure() {
        if (feedback == null)
            feedback = createDragSourceFeedbackFigure();
        return feedback;
    }

    /**
     * Creates the figure used for feedback.
     * 
     * @return the new feedback figure
     */
    protected IFigure createDragSourceFeedbackFigure() {
        // Use a ghost rectangle for feedback
        RectangleFigure r = new RectangleFigure();
        FigureUtilities.makeGhostShape(r);
        r.setLineStyle(Graphics.LINE_DOT);
        r.setForegroundColor(ColorConstants.white);
        r.setBounds(getInitialFeedbackBounds());
        addFeedback(r);
        return r;
    }

    /**
     * Returns the bounds of the host's figure by reference to be used to
     * calculate the initial location of the feedback. The returned Rectangle
     * should not be modified. Uses handle bounds if available.
     * 
     * @return the host figure's bounding Rectangle
     */
    protected Rectangle getInitialFeedbackBounds() {
        if (((GraphicalEditPart) getHost()).getFigure() instanceof HandleBounds)
            return ((HandleBounds) ((GraphicalEditPart) getHost()).getFigure()).getHandleBounds();
        return ((GraphicalEditPart) getHost()).getFigure().getBounds();
    }

    /**
     * @see org.eclipse.gef.editpolicies.BendpointEditPolicy#getMoveBendpointCommand(org.eclipse.gef.requests.BendpointRequest)
     */
    @Override
    protected Command getMoveBendpointCommand(BendpointRequest request) {
        return null;
    }

    /**
     * @see org.eclipse.gef.editpolicies.BendpointEditPolicy#getDeleteBendpointCommand(org.eclipse.gef.requests.BendpointRequest)
     */
    @Override
    protected Command getDeleteBendpointCommand(BendpointRequest request) {
        return null;
    }
    
    /**
     * @see nexcore.tool.uml.ui.realization.sequencediagram.edit.policy.SequenceBendpointEditPolicy#getCommand(org.eclipse.gef.Request)
     */
    @Override
    public Command getCommand(Request request) {
        return super.getCommand(request);
    }

}
