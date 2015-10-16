/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.realization.sequencediagram.command;

import java.util.ArrayList;
import java.util.List;

import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.LifeLineNode;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.model.umldiagram.RelationType;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.realization.sequencediagram.util.SequenceUtil;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.command</li>
 * <li>설  명 : ChangeOperandSouthConstraintCommand</li>
 * <li>작성일 : 2011. 4. 15.</li>
 * <li>작성자 : zerotae</li>
 * </ul>
 */
public class ChangeOperandSouthConstraintCommand extends Command {

    /**
     * 현재의 constraint
     */
    private int delta = 0;

    /**
     * 이전의 constraint
     */
    private int oldHeight = 0;;

    /** ParentNode */
    private AbstractNode parentNode;

    /** NotationNode */
    private ContainerNode operandNode;

    private Diagram diagram;
    
    
    /**
     * ChangeOperandSouthConstraintCommand
     * @param editPart
     * @param delta
     */
    public ChangeOperandSouthConstraintCommand(EditPart editPart, int delta) {
        this.operandNode = (ContainerNode) editPart.getModel();
        this.oldHeight = operandNode.getHeight();
        this.delta = delta;
        this.parentNode = (AbstractNode) editPart.getParent().getModel();
        this.diagram = SequenceUtil.getDiagram(operandNode);
    }
    
    /**
     * ChangeOperandSouthConstraintCommand
     * @param node
     * @param delta
     */
    public ChangeOperandSouthConstraintCommand(AbstractNode node, int delta) {
        this.operandNode = (ContainerNode) node;
        this.oldHeight = operandNode.getHeight();
        this.delta = delta;
        this.parentNode = (AbstractNode) node.getParent();
        this.diagram = SequenceUtil.getDiagram(operandNode);
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {
        int operandY = SequenceUtil.getOperandY((NotationNode) parentNode, operandNode) + operandNode.getHeight();


        NotationNode currentFragmentNode = (NotationNode) operandNode.getParent();
        NotationNode currentAncestorNode = SequenceUtil.getAncestor(currentFragmentNode);
        int absoluteY = SequenceUtil.translateToAbsoluteYByParentNode(0, operandNode, (NotationNode) operandNode.getParent());
     // 프래그먼트 시프트
        // 1. 다이어그램 레벨 프래그먼트 시프트
        List<NotationNode> diagramLevelFragments = SequenceUtil.getOnlyCombindFragmentNodeList(diagram.getNodeList());
        for (NotationNode abstractNode : diagramLevelFragments) {
            if (absoluteY < abstractNode.getY() && abstractNode.getY() < absoluteY + operandNode.getHeight()) {
                abstractNode.setY(abstractNode.getY() + delta);
            } else if (absoluteY + operandNode.getHeight() < abstractNode.getY()) {
                abstractNode.setY(abstractNode.getY() + delta);
            }
        }
        // 현재 선택된 ContainerNode
        ContainerNode currentInteractionOperandNode = null;
        
        // 2. operandNode와 같은 프래그먼트에 소속된 동일 레벨의 프래그먼트만 시프트
        List<NotationNode> allFragments = SequenceUtil.getAllCombinedFragmentNodeInDiagram(diagram);
        // operandNode의 메시지를 가지고 operandNode가 속한 프래그먼트를 찾는다.
//        AbstractConnection operandNodeMessage = operandNode.getIncomingConnectionList().get(0);
//        List<InteractionFragment> interactionFragments = SequenceUtil.getFragmentListByMessage((Message) operandNodeMessage.getUmlModel());
//        InteractionFragment firstFragment = interactionFragments.get(0);
//        if( firstFragment.eContainer() instanceof InteractionOperand ) {
//            
//            InteractionOperand firstOperand = (InteractionOperand) firstFragment.eContainer();
//            for (NotationNode notationNode : allFragments) {
//                if( notationNode.getUmlModel() instanceof CombinedFragment ) {
//                    for( AbstractNode notationChild : notationNode.getCompartmentList() ) {
//                        if( notationChild instanceof ContainerNode ) {
//                            ContainerNode container = (ContainerNode) notationChild;
//                            if( container.getUmlModel().equals(firstOperand) ) {
//                                currentInteractionOperandNode = container;
//                            }
//                        }
//                    }
//                    CombinedFragment combinedFragment = (CombinedFragment) notationNode.getUmlModel();
//                    if (combinedFragment.getOperands().contains(firstOperand)) {
//                        currentFragmentNode = notationNode;
//                        currentAncestorNode = SequenceUtil.getAncestor(currentFragmentNode);
//                        break;
//                    }
//                }
//            }
            
            
            // operandNode와 같은 프래그먼트에 소속된 동일 레벨의 프래그먼트만 시프트
            for (NotationNode abstractNode : allFragments) {
                // 프래그먼트 하위요소가 아니면 넘어감.
                if( !(abstractNode.getParent() instanceof ContainerNode) ) {
                    continue;
                } 
                // 같은 부모를 가지고 있는지 확인. 
                if( !currentAncestorNode.equals(SequenceUtil.getAncestor(abstractNode))) {
                    continue;
                }
                if( currentFragmentNode.getCompartmentList().contains(abstractNode.getParent()) ) {
                    continue;
                }
                int absoluteNodeY = SequenceUtil.translateToAbsoluteYByParentNode(0, (ContainerNode) abstractNode.getParent(), abstractNode);
                if (absoluteY < absoluteNodeY && absoluteY + operandNode.getHeight() < absoluteNodeY ) {
                    abstractNode.setY(abstractNode.getY() + delta);
                } else if (absoluteY + operandNode.getHeight() < absoluteNodeY) {
                    abstractNode.setY(abstractNode.getY() + delta);
                }
            }
//        }
        
     // 다른 operandNode 노드들 시프트
        EList<AbstractNode> diagramNodeList = diagram.getNodeList();
        List<NotationNode> operandNodeList = new ArrayList<NotationNode>();
        for(AbstractNode childNode : diagramNodeList ) {
            if( childNode instanceof LifeLineNode ) {
                LifeLineNode lifeLineNode = (LifeLineNode) childNode;
                for( NotationNode behavior : lifeLineNode.getBehaviorList() ) {
                    if( operandNode.equals(behavior) ) {
                        continue;
                    }
                    if( absoluteY < behavior.getY() && behavior.getY() < absoluteY + operandNode.getHeight() ){
//                        operandNodeNode.setY(operandNodeNode.getY() + delta);
                    } else if( absoluteY + operandNode.getHeight() < behavior.getY() ) {
                        behavior.setY(behavior.getY() + delta);
                    }
                }
            }
        }
        
        EList<AbstractConnection> allConnection = this.diagram.getConnectionList();
        for( AbstractConnection connection : allConnection ) {
            if (this.operandNode.getOutgoingConnectionList().contains(connection)) {
                continue;
            }
            if( absoluteY + operandNode.getHeight() < connection.getY() || 
            ( absoluteY + operandNode.getHeight() < connection.getY() &&  absoluteY + operandNode.getHeight() > connection.getY())) {
                connection.setY(connection.getY() + delta);
                if(RelationType.REPLY_MESSAGE.equals(connection.getRelationType())) {
                } else {
                    if( operandNode.getParent() instanceof Diagram ) {
                        AbstractView target = connection.getTarget();
                        target.setY(target.getY() + delta);     
                    }
                }
            }
        }
        // operandNode 크기 조절 시에 reply message가 있다면 operandNode 사이즈와 같이 이동해 준다.
        EList<AbstractConnection> replyList = this.operandNode.getOutgoingConnectionList();
        for( AbstractConnection reply : replyList ) {
            reply.setY( reply.getY() + delta );
        }

//        if( currentInteractionOperandNode != null ) {
//            currentInteractionOperandNode.setHeight(currentInteractionOperandNode.getHeight() + delta);
//        }
        if( currentFragmentNode != null ) { 
//            currentFragmentNode.setHeight(currentFragmentNode.getHeight() + delta);
            SequenceUtil.setParentNodeSize(delta, currentFragmentNode);
        }
//        SequenceUtil.setAllLifeLineHeight(diagram);
        
        if(operandNode.getHeight() + delta >= UICoreConstant.OPERAND_MIN_HEIGHT) {
            operandNode.setHeight(operandNode.getHeight() + delta);
            parentNode.setHeight(parentNode.getHeight() + delta);           
//            SequenceUtil.shiftByResizeInteractionOperand(diagram, operandNode,  (NotationNode) parentNode, operandY, delta);
        } else {
            operandNode.setHeight(UICoreConstant.OPERAND_MIN_HEIGHT);
            parentNode.setHeight(parentNode.getHeight() + delta + (UICoreConstant.OPERAND_MIN_HEIGHT - (oldHeight + delta)));              
//            SequenceUtil.shiftByResizeInteractionOperand(diagram, operandNode, (NotationNode) parentNode, operandY, delta + (UICoreConstant.OPERAND_MIN_HEIGHT - (oldHeight + delta)));
        }
        
        if(delta > 0) { //결합단편 높이가 늘어나면 라이프라인의 높이도 늘린다.
            SequenceUtil.shiftLifeLinesHeight(diagram, delta);
        }
    }

}
