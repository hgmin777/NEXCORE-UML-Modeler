/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.realization.sequencediagram.edit.policy;

import java.util.List;

import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.LifeLineNode;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.model.umldiagram.RelationType;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.realization.sequencediagram.command.ChangeOperandNorthConstraintCommand;
import nexcore.tool.uml.ui.realization.sequencediagram.command.ChangeOperandSouthConstraintCommand;
import nexcore.tool.uml.ui.realization.sequencediagram.edit.part.InteractionOperandEditPart;
import nexcore.tool.uml.ui.realization.sequencediagram.util.SequenceUtil;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.edit.policy</li>
 * <li>설  명 : OperandResizableEditPolicy</li>
 * <li>작성일 : 2011. 5. 18.</li>
 * <li>작성자 : SKCCADMIN</li>
 * </ul>
 */
public class OperandResizableEditPolicy extends ResizableEditPolicy {

    
    /**
     * @see org.eclipse.gef.editpolicies.ResizableEditPolicy#getResizeCommand(org.eclipse.gef.requests.ChangeBoundsRequest)
     */
    @Override
    protected Command getResizeCommand(ChangeBoundsRequest request) {
        
        Point location = new Point(request.getLocation());
        getHostFigure().translateToRelative(location);
        
        InteractionOperandEditPart editPart = (InteractionOperandEditPart) getHost();
        ContainerNode operandNode = (ContainerNode) editPart.getModel();
        
        if(operandNode.getHeight() <= UICoreConstant.OPERAND_MIN_HEIGHT
            && request.getSizeDelta().height < 0) {
            return null;
        }

        NotationNode parentNode = (NotationNode) editPart.getParent().getModel();
        int indexOf = parentNode.getCompartmentList().indexOf(operandNode);
        int limitedY = parentNode.getY() + UICoreConstant.FRAGMENT_LABEL_HEIGHT;
        if(parentNode.getParent() instanceof ContainerNode) {
            ContainerNode containerNode = (ContainerNode) parentNode.getParent();
            limitedY = SequenceUtil.translateToAbsoluteYByParentNode(limitedY, containerNode, (NotationNode) containerNode.getParent());
        }
        for(int i = 0; i < indexOf-1; i++ ) {
            AbstractNode abstractNode = parentNode.getCompartmentList().get(i);
            limitedY += abstractNode.getHeight();
        }
        
        if(PositionConstants.NORTH  == request.getResizeDirection()) {
            if(operandNode.equals(parentNode.getCompartmentList().get(0))) {
                return null;
            }
            //위로 리사이즈 할 때 상위 체크
            int upLimitedY = limitedY;
            ContainerNode upperOperand = (ContainerNode) parentNode.getCompartmentList().get(indexOf-1);
            upLimitedY += UICoreConstant.OPERAND_MIN_HEIGHT;
            upLimitedY = getCheckUpLimitedY(upperOperand, upLimitedY);
            if(location.y < upLimitedY) {
                return null;
            }
            
            //아래로 리사이즈 할 때 하위 체크
            int downLimitedY = limitedY + parentNode.getHeight();
            downLimitedY = getCheckDownLimitedY(operandNode, downLimitedY);
            if(location.y > downLimitedY) {
                return null;
            }
            
            return new ChangeOperandNorthConstraintCommand(editPart, request.getSizeDelta().height);            
        }
        
        if(PositionConstants.SOUTH  == request.getResizeDirection()) {
            
            //위로 리사이즈 할 때 상위 체크
            limitedY += UICoreConstant.OPERAND_MIN_HEIGHT;
            limitedY = getCheckUpLimitedY(operandNode, limitedY);
            
            Diagram diagram = SequenceUtil.getDiagram(operandNode);
            List<NotationNode> allFragments = SequenceUtil.getAllCombinedFragmentNodeInDiagram(diagram);

            for (NotationNode abstractNode : allFragments) {
                if( !(abstractNode.getParent() instanceof ContainerNode) ) {
                    continue;
                } 
                int absoluteNodeY = SequenceUtil.translateToAbsoluteYByParentNode(0, (ContainerNode) abstractNode.getParent(), abstractNode);
                int nodeY = SequenceUtil.translateToAbsoluteYByParentNode(0, operandNode, (NotationNode) operandNode.getParent());
                int nodeEndY = nodeY + operandNode.getHeight();
                
                if( absoluteNodeY + abstractNode.getHeight() < nodeEndY  &&  nodeEndY + request.getSizeDelta().height < absoluteNodeY + abstractNode.getHeight() ) {
                    return null;
                }
            }
            
            for( AbstractNode abstractNode : diagram.getNodeList() ) {
                if( abstractNode.getNodeType() == NodeType.LIFELINE ) {
                    LifeLineNode lifeline = (LifeLineNode) abstractNode;
                    for( NotationNode behavior : lifeline.getBehaviorList() ) {
                        int nodeY = SequenceUtil.translateToAbsoluteYByParentNode(0, operandNode, (NotationNode) operandNode.getParent());
                        int nodeEndY = nodeY + operandNode.getHeight() - 10;
                        
                        if( behavior.getY() + behavior.getHeight() < nodeEndY  &&  nodeEndY + request.getSizeDelta().height < behavior.getY() + behavior.getHeight() ) {
                            return null;
                        }
                    }
                }
            }
            return new ChangeOperandSouthConstraintCommand(editPart, request.getSizeDelta().height);            
        }
        
        return null;
    }
    
    /**
     * 상위 operand의 포함된 메시지 중 최하단 메시지 또는 결합단편 보다 위로 움직일 수 없다.
     *  
     * @param operandNode
     * @param limitedY
     * @return int
     */
    private int getCheckUpLimitedY(ContainerNode operandNode, int limitedY) {
        limitedY = getMessageCheckUpLimitedY(operandNode, limitedY);
        List<NotationNode> combindFragmentNodeList = SequenceUtil.getOnlyCombindFragmentNodeList(operandNode.getNodeList());
        if(combindFragmentNodeList.size() <= 0) {
            return limitedY;
        }
        
        int childFragmentY = 0;
        for(NotationNode combinedFragmentNode : combindFragmentNodeList) {
            childFragmentY = combinedFragmentNode.getY();
            if(combinedFragmentNode.getParent() instanceof ContainerNode) {
                ContainerNode containerNode = (ContainerNode) combinedFragmentNode.getParent();
                childFragmentY = SequenceUtil.translateToAbsoluteYByParentNode(childFragmentY, containerNode, (NotationNode) containerNode.getParent());
            }
            childFragmentY += combinedFragmentNode.getHeight();
            if(childFragmentY > limitedY) {
                limitedY =  childFragmentY;
            }
        }
        
        return limitedY;
    }

    /**
     * 상위 operand의 포함된 메시지 중 최하단 메시지보다 위로 움직일 수 없다.
     *  
     * @param operandNode
     * @param operandNode
     * @param limitedY
     * @return int
     */
    private int getMessageCheckUpLimitedY(ContainerNode operandNode, int limitedY) {

        Diagram diagram = SequenceUtil.getDiagram(operandNode);
        List<AbstractConnection> connectionListInOperand = SequenceUtil.getConnectionListInOperand(diagram, operandNode);
        if(connectionListInOperand.size() == 0) {
            return limitedY;
        }
        
        List<AbstractConnection> sortedMessageList = SequenceUtil.ascSortedMessageListByYValue(connectionListInOperand);
        
        if( sortedMessageList.size() == 0 ) {
            return limitedY;
        }
        AbstractConnection lastConnection = sortedMessageList.get(sortedMessageList.size()-1);
        //메시지의 behavior 검사
        if(RelationType.REPLY_MESSAGE.equals(lastConnection.getRelationType())) {
            if(limitedY >= lastConnection.getY()) {
                return limitedY;
            } else {
                return lastConnection.getY();
            }
        } else {
            NotationNode behaviorNode = (NotationNode) lastConnection.getTarget();
            int lastY = behaviorNode.getY() + behaviorNode.getHeight();
            if(limitedY >= lastY) {
                return limitedY;
            } else {
                return lastY;
            }
        }
    }
    
    /**
     * operand의 포함된 메시지 중 최상단 메시지 또는 결합단편 보다 아래로 움직일 수 없다.
     *  
     * @param operandNode
     * @param limitedY
     * @return int
     */
    private int getCheckDownLimitedY(ContainerNode operandNode, int limitedY) {
        limitedY = getMessageCheckDownLimitedY(operandNode, limitedY);
        List<NotationNode> combindFragmentNodeList = SequenceUtil.getOnlyCombindFragmentNodeList(operandNode.getNodeList());
        if(combindFragmentNodeList.size() <= 0) {
            return limitedY;
        }
        
        int childFragmentY = 0;
        for(NotationNode combinedFragmentNode : combindFragmentNodeList) {
            childFragmentY = combinedFragmentNode.getY();
            if(combinedFragmentNode.getParent() instanceof ContainerNode) {
                ContainerNode containerNode = (ContainerNode) combinedFragmentNode.getParent();
                childFragmentY = SequenceUtil.translateToAbsoluteYByParentNode(childFragmentY, containerNode, (NotationNode) containerNode.getParent());
            }
            if(childFragmentY < limitedY) {
                limitedY =  childFragmentY;
            }
        }
        
        return limitedY;
    }

    /**
     * operand의 포함된 메시지 중 최상단 메시지보다 아래로 움직일 수 없다.
     *  
     * @param operandNode
     * @param operandNode
     * @param limitedY
     * @return int
     */
    private int getMessageCheckDownLimitedY(ContainerNode operandNode, int limitedY) {

        Diagram diagram = SequenceUtil.getDiagram(operandNode);
        List<AbstractConnection> connectionListInOperand = SequenceUtil.getConnectionListInOperand(diagram, operandNode);
        if(connectionListInOperand.size() == 0) {
            return limitedY;
        }
        
        List<AbstractConnection> sortedMessageList = SequenceUtil.ascSortedMessageListByYValue(connectionListInOperand);
        
        AbstractConnection topConnection = sortedMessageList.get(0);
        
        if(limitedY > topConnection.getY()) {
            return topConnection.getY() - UICoreConstant.MESSAGENAME_MARGIN;
        } else {
            return limitedY;
        }
    }
    
    
    /**
     * @see org.eclipse.gef.editpolicies.GraphicalEditPolicy#removeFeedback(org.eclipse.draw2d.IFigure)
     */
    @Override
    protected void removeFeedback(IFigure figure) {
        if(figure.getParent() != getFeedbackLayer()) {
            return;
        }
        
        if( !getLayer(LayerConstants.FEEDBACK_LAYER).getChildren().isEmpty() )
            getFeedbackLayer().remove(figure);
    }
   
}
