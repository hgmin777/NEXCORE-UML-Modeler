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
import org.eclipse.uml2.uml.CombinedFragment;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.InteractionOperand;
import org.eclipse.uml2.uml.Message;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.command</li>
 * <li>설 명 : ReSizeOnlyHeightBehaviorNodeCommand</li>
 * <li>작성일 : 2010. 1. 19.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class ReSizeOnlyHeightBehaviorNodeCommand extends Command {

    /** Height Delta */
    private int delta;

    /** viewModel */
    private NotationNode behavior;

    /** diagram */
    private Diagram diagram;

    /** margin */
    private int margin = 10;

    /**
     * ReSizeOnlyHeightBehaviorNodeCommand
     * @param editPart
     * @param delta
     */
    public ReSizeOnlyHeightBehaviorNodeCommand(EditPart editPart, int delta) {
        this.diagram = (Diagram) editPart.getParent().getParent().getModel();
        this.behavior = (NotationNode) editPart.getModel();
        this.delta = delta;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {
        
        NotationNode node = null;
        behavior.setHeight(behavior.getHeight() + delta);
        SequenceUtil.checkLifeLinesHeight(diagram, behavior, margin);
        
        
        // Message, BehaviorNode, Fragments 이동 시 정책.
        // 1. Message
        // Message는 위아래 어느 방향으로 움직이건 다른 Fragment 영역을 침범할 수 없다.
        // SynchMessage가 이동한 위치 delta 값 만큼, 대응하는 Reply도 같은 delta 값 만큼 이동한다.
        // ReplyMessage가 Behavior 영역을 벗어난 위치로 이동하면 그 만큼 Behavior의 영역도 커진다.
        
        // 2. BehaviorNode 
        // 위 방향으로 사이즈를 조절할 수 없다.
        // 아래방향으로 조절한 사이즈 만큼 Fragments들은 시프트 이동한다. (프래그먼트 내부 메시지, 내부 프래그먼트, 비헤이비어도 같이 시프트)
        
        // 3. Fragments
        // a.윗 방향으로 리사이즈 시
        // a.1. 사이즈를 줄일 때
                // 1. 자식 프래그먼트 영역에 침범할 수 없다. 
                // 2. 내부 메시지 영역에 침범할 수 없다.
        // a.2. 사이즈를 늘릴 때
                // 1. 부모 프래그먼트의 영역을 넘는지. (ok)
                // 2. 같은 레벨에 존재하는 다른 프래그먼트의 영역에 닿는지 확인. (ok)
        // b. 아래방향으로 리사이즈 시
        // b.1. 사이즈를 줄일 때
                // 1. 자식 프래그먼트, 비헤이비어 영역에 침범할 수 없다.
        
        
        
        // 프래그먼트 시프트
        // 1. 다이어그램 레벨 프래그먼트 시프트
        List<NotationNode> diagramLevelFragments = SequenceUtil.getOnlyCombindFragmentNodeList(diagram.getNodeList());
        for (NotationNode abstractNode : diagramLevelFragments) {
            if (behavior.getY() < abstractNode.getY() && abstractNode.getY() < behavior.getY() + behavior.getHeight()) {
                if( delta > 0 ) {
                    if( abstractNode.getY() + abstractNode.getHeight() > behavior.getY() + behavior.getHeight() ) {
                        abstractNode.setY(abstractNode.getY() + delta);
                    }
                } else {
                    if( behavior.getY() < abstractNode.getY() && abstractNode.getY() + abstractNode.getHeight() < behavior.getY() + behavior.getHeight() ) {
                    } else if( abstractNode.getY() < behavior.getY() + behavior.getHeight() ) {
                        abstractNode.setY(abstractNode.getY() + delta);
                    }
                }
            } else if (behavior.getY() + behavior.getHeight() < abstractNode.getY()) {
                if( delta > 0 ) {
                    if( abstractNode.getY() + abstractNode.getHeight() > behavior.getY() + behavior.getHeight() ) {
                        abstractNode.setY(abstractNode.getY() + delta);
                    }
                } else {
                    if( abstractNode.getY() < behavior.getY() + behavior.getHeight() ) {
                        abstractNode.setY(abstractNode.getY() + delta);
                    }
                }
            }
        }
        NotationNode currentFragmentNode = null;
        NotationNode currentAncestorNode = null;
        // 현재 선택된 ContainerNode
        ContainerNode currentInteractionOperandNode = null;
        
        // 2. Behavior와 같은 프래그먼트에 소속된 동일 레벨의 프래그먼트만 시프트
        List<NotationNode> allFragments = SequenceUtil.getAllCombinedFragmentNodeInDiagram(diagram);
        // Behavior의 메시지를 가지고 Behavior가 속한 프래그먼트를 찾는다.
        AbstractConnection behaviorMessage = behavior.getIncomingConnectionList().get(0);
        List<InteractionFragment> interactionFragments = SequenceUtil.getFragmentListByMessage((Message) behaviorMessage.getUmlModel());
       
        InteractionFragment firstFragment = null;
        if( !interactionFragments.isEmpty() ) {
            firstFragment = interactionFragments.get(0);
        }
        if( firstFragment != null && firstFragment.eContainer() instanceof InteractionOperand ) {
            
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
            
            // 중복 시프트를 하지 않도록 프래그먼트 리스트를 검사하여 부모-자식 관계의 프래그먼트는 자식을 리스트에서 삭제한다.
            SequenceUtil.checkDuplicatedNode(allFragments);
            
            // Behavior와 같은 프래그먼트에 소속된 동일 레벨의 프래그먼트만 시프트
            for (NotationNode abstractNode : allFragments) {
                // 프래그먼트 하위요소가 아니면 넘어감.
                if( !(abstractNode.getParent() instanceof ContainerNode) ) {
                    continue;
                } 
                // 같은 부모를 가지고 있는지 확인. 
                if( !currentAncestorNode.equals(SequenceUtil.getAncestor(abstractNode))) {
                    continue;
                }
                int absoluteNodeY = SequenceUtil.translateToAbsoluteYByParentNode(0, (ContainerNode) abstractNode.getParent(), abstractNode);
                if (behavior.getY() < absoluteNodeY && absoluteNodeY < behavior.getY() + behavior.getHeight() - delta) {
                    abstractNode.setY(abstractNode.getY() + delta);
                } else if (behavior.getY() + behavior.getHeight() - delta < absoluteNodeY && absoluteNodeY < behavior.getY() + behavior.getHeight() ) {
//                    if( SequenceUtil.checkDuplicatedParentNode(currentFragmentNode, abstractNode) ) {
                        abstractNode.setY(abstractNode.getY() + delta);
//                    }
                }
            }
        }
        
     // 다른 Behavior 노드들 시프트
        EList<AbstractNode> diagramNodeList = diagram.getNodeList();
        List<NotationNode> behaviorList = new ArrayList<NotationNode>();
        for(AbstractNode childNode : diagramNodeList ) {
            if( childNode instanceof LifeLineNode ) {
                LifeLineNode lifeLineNode = (LifeLineNode) childNode;
                for( NotationNode behaviorNode : lifeLineNode.getBehaviorList() ) {
                    if( behavior.equals(behaviorNode) ) {
                        continue;
                    }
                    if( this.behavior.getY() + behavior.getHeight() > behaviorNode.getY() ) {
                        continue;
                    }
                    if (behaviorNode.getY() < behavior.getY() + behavior.getHeight() - delta
                        && behavior.getY() + behavior.getHeight() - delta < behaviorNode.getY() + behaviorNode.getHeight()) {
//                        behaviorNode.setHeight(behaviorNode.getHeight() + delta);
                        
                    } else if( behavior.getY() + behavior.getHeight() - delta < behaviorNode.getY() ) {
                        if( delta > 0 ) {
                            if( behaviorNode.getY() + behaviorNode.getHeight() > behavior.getY() + behavior.getHeight() ) {
                                behaviorNode.setY(behaviorNode.getY() + delta);
                            }
                        } else {
                            if( behavior.getY() < behaviorNode.getY() && behaviorNode.getY() + behaviorNode.getHeight() < behavior.getY() + behavior.getHeight() ) {
                            } else if( behaviorNode.getY() < behavior.getY() + behavior.getHeight() ) {
                                behaviorNode.setY(behaviorNode.getY() + delta);
                            }
                        }
//                        behaviorNode.setY(behaviorNode.getY() + delta);
                    }
                }
            }
        }
        
        EList<AbstractConnection> allConnection = this.diagram.getConnectionList();
        for( AbstractConnection connection : allConnection ) {
            if (this.behavior.getOutgoingConnectionList().contains(connection)) {
                continue;
            }
            if( delta > 0 ) {
                if( this.behavior.getY() + behavior.getHeight() > connection.getY() ) {
                    continue;
                }
            } else {
                if( behavior.getY() < connection.getY() && connection.getY() < behavior.getY() + behavior.getHeight() ) {
                } else if( this.behavior.getY() + behavior.getHeight() < connection.getY() ) {
                    continue;
                }
            }
            if( this.behavior.getY() + behavior.getHeight() > connection.getY() ) {
                continue;
            }
            if( behavior.getY() + behavior.getHeight() < connection.getY() || 
            ( behavior.getY() + behavior.getHeight() - delta < connection.getY() &&  behavior.getY() + behavior.getHeight() > connection.getY())) {
                connection.setY(connection.getY() + delta);
                if(RelationType.REPLY_MESSAGE.equals(connection.getRelationType())) {
                } else {
                    if( behavior.getParent() instanceof Diagram ) {
                        AbstractView target = connection.getTarget();
                        target.setY(target.getY() + delta);     
                    }
                }
            }
        }
        // behavior 크기 조절 시에 reply message가 있다면 behavior 사이즈와 같이 이동해 준다.
        EList<AbstractConnection> replyList = this.behavior.getOutgoingConnectionList();
        for( AbstractConnection reply : replyList ) {
            reply.setY( reply.getY() + delta );
        }

        if( currentInteractionOperandNode != null ) {
            if( delta > 0 )
                currentInteractionOperandNode.setHeight(currentInteractionOperandNode.getHeight() + delta);
        }
        if( currentFragmentNode != null ) { 
            if( delta > 0 ) { 
                currentFragmentNode.setHeight(currentFragmentNode.getHeight() + delta);
                SequenceUtil.setParentNodeSize(delta, currentFragmentNode);
            }
        }
        if( delta > 0 )
            SequenceUtil.setAllLifeLineHeight(diagram);
    }

}
