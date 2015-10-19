/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
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
import nexcore.tool.uml.ui.core.diagram.edit.policy.DiagramLayoutEditPolicy;
import nexcore.tool.uml.ui.core.util.UiUtil;
import nexcore.tool.uml.ui.realization.sequencediagram.command.ChangeCombinedFragmentConstraintCommand;
import nexcore.tool.uml.ui.realization.sequencediagram.command.CreateCombinedFragmentNodeCommand;
import nexcore.tool.uml.ui.realization.sequencediagram.command.CreateLifeLineNodeCommand;
import nexcore.tool.uml.ui.realization.sequencediagram.command.InsertLifeLineNodeCommand;
import nexcore.tool.uml.ui.realization.sequencediagram.command.ReSizeLifeLineNodeCommand;
import nexcore.tool.uml.ui.realization.sequencediagram.command.ReSizeOnlyHeightLifeLineNodeCommand;
import nexcore.tool.uml.ui.realization.sequencediagram.command.ShiftLifeLineNodeCommand;
import nexcore.tool.uml.ui.realization.sequencediagram.edit.part.CombinedFragmentEditPart;
import nexcore.tool.uml.ui.realization.sequencediagram.edit.part.LifeLineEditPart;
import nexcore.tool.uml.ui.realization.sequencediagram.edit.part.LifeLineNameHeaderEditPart;
import nexcore.tool.uml.ui.realization.sequencediagram.util.SequenceUtil;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.uml2.uml.CombinedFragment;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.edit.policy</li>
 * <li>설 명 : SequenceDiagramLayoutEditPolicy</li>
 * <li>작성일 : 2009. 12. 22.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class SequenceDiagramLayoutEditPolicy extends DiagramLayoutEditPolicy {
  
    private ChangeBoundsRequest boundsRequest;
    private Point moveDelta;
    private Dimension sizeDelta;
    
    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.policy.DiagramLayoutEditPolicy#getCommand(org.eclipse.gef.Request)
     */
    @Override
    public Command getCommand(Request request) {
        if( request instanceof ChangeBoundsRequest ) {
            boundsRequest = (ChangeBoundsRequest) request;
            moveDelta = boundsRequest.getMoveDelta();
            sizeDelta = boundsRequest.getSizeDelta();
        }
        return super.getCommand(request);
    }
    
    /**
     * @see org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy#createChangeConstraintCommand(org.eclipse.gef.EditPart,
     *      java.lang.Object)
     */
    protected Command createChangeConstraintCommand(EditPart child, Object constraint) {
        
        if( child.getModel() instanceof NotationNode) {
            NotationNode combinedFragmentNode = (NotationNode) child.getModel();
            Diagram diagram = SequenceUtil.getDiagram(combinedFragmentNode);
        }
        
        if (child instanceof LifeLineNameHeaderEditPart) {
            return null;
        }
  
        if (child instanceof LifeLineEditPart && constraint instanceof Rectangle) {
            return changeLifeLineConstraint(child, (Rectangle) constraint);
        } else if (child instanceof CombinedFragmentEditPart && constraint instanceof Rectangle) {
            return changeCombindedConstraint((CombinedFragmentEditPart) child,(Rectangle) constraint);
        }
        return super.createChangeConstraintCommand(child, constraint);
    }

    /**
     * LifeLine 이동 및 리사이즈를 처리한다.
     * 
     * @param child
     * @param constraint
     * @return Command
     */
    private Command changeCombindedConstraint(CombinedFragmentEditPart child, Rectangle constraint) {
        Rectangle bounds = child.getFigure().getBounds();
        if(bounds.width != constraint.width) {
            return null;
        }
                
        NotationNode combinedFragmentNode = (NotationNode) child.getModel();
        Diagram diagram = SequenceUtil.getDiagram(combinedFragmentNode);
        if(diagram == null) {
            return null;
        }
        
        // 부모가 다이어그램인 프래그먼트들 사이에서 영역을 침범하는지 판단.
        if(combinedFragmentNode.getParent() instanceof Diagram) {
            if(!checkConstraintForParentDiagram(constraint, bounds, combinedFragmentNode, diagram)) {
                return null;
            }
            
        // 프래그먼트 내부 처리
        } else {
            //결합단편 하위 서브 결합단편의 제약조건을 검사한다.
            if(!checkConstraintForParentFragment(constraint, bounds, combinedFragmentNode, diagram)) {
                return null;
            }
        }
        return new ChangeCombinedFragmentConstraintCommand(child, constraint);

    }

    /**
     * 다이어그램 하위 결합단편의 제약조건을 검사한다.
     *  
     * @param constraint
     * @param bounds
     * @param combinedFragmentNode
     * @param diagram void
     */
    private boolean checkConstraintForParentDiagram(Rectangle constraint, Rectangle bounds,
                                                 NotationNode combinedFragmentNode, Diagram diagram) {

        // 20120607 by kang
        if( constraint.y < 100 ) {
            return false;
        }
        
        //결합단편 South방향에서 사이즈 조절시 메시지 영역 침범 검사
        if(combinedFragmentNode.getY() == constraint.y && bounds.height != constraint.height ) {
            if(checkTopConnection(diagram, combinedFragmentNode, constraint.y + constraint.height)) {
                return false;
            }
        }
        
        //결합단편 위로 이동시메시지 영역 침범 검사
        if(checkOverConnection(diagram, combinedFragmentNode, constraint.y)) {
            return false;
        }
        
        //결합단편 위로 이동시메시지 다른 결합단편 영역 침범 검사
        if(SequenceUtil.isDiagramHasCombinedFragement(diagram)) {
            if(isThereCombinedFragment(diagram, combinedFragmentNode, constraint.y)) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * 결합단편 하위 서브 결합단편의 제약조건을 검사한다.
     *  
     * @param constraint
     * @param bounds
     * @param combinedFragmentNode
     * @param diagram void
     */
    private boolean checkConstraintForParentFragment(Rectangle constraint, Rectangle bounds,
                                                 NotationNode combinedFragmentNode, Diagram diagram) {
        int absoluteY = SequenceUtil.translateToAbsoluteYByParentNode(0, (ContainerNode) combinedFragmentNode.getParent(), combinedFragmentNode) + moveDelta.y;
        // 윗 방향으로 리사이즈
        if( PositionConstants.NORTH == boundsRequest.getResizeDirection() ) {
            // 사이즈를 줄일 때
            if( boundsRequest.getSizeDelta().height < 0 ) {
                // 사이즈를 위에서 아래로 줄일 때 
                // 1. 자식 프래그먼트에 닿는지, (ok) 
                if( checkChildFragmentArea(combinedFragmentNode, absoluteY) ) {
                    return false;
                }
                // 2. 내부 메시지에 닿는지 확인. (ok)
                if(checkUnderConnection(diagram, combinedFragmentNode, absoluteY )) {
                    return false;
                }
                
            // 사이즈를 늘릴 때
            } else {
                // 사이즈를 위로 늘릴 때 
                // 1. 부모 프래그먼트의 영역을 넘는지. (ok)
                if( constraint.y < 0 ) {
                    return false;
                }
                // 2. 같은 레벨에 존재하는 다른 프래그먼트의 영역에 닿는지 확인. (ok)
                if(SequenceUtil.isDiagramHasCombinedFragement(diagram)) {
                    if(isThereCombinedFragment(diagram, combinedFragmentNode, constraint.y)) {
                        return false;
                    }
                }
                
            }
        // 아래 방향으로 리사이즈 ==> 아래 방향으로 사이즈 조절 시에는 모든 요청이 허용됨.
        } else if ( PositionConstants.SOUTH == boundsRequest.getResizeDirection() ) {
//            // 사이즈를 줄일 때
//            if( boundsRequest.getSizeDelta().height < 0 ) {
//                // 사이즈를 밑에서 위로 줄일 때
//                // 1. 자식 프래그먼트의 영역에 닿는지, 
//                // 2. 내부 메시지 or 비헤이비어 노드 영역에 닿는지 확인.
//                if(checkTopConnection(diagram, combinedFragmentNode, constraint.y + constraint.height -20)) {
//                    return false;
//                }
//                
//            // 사이즈를 늘릴 때
//            } else {
//                // 사이즈를 아래 방향으로 늘릴 때
//                // 1. 부모 프래그먼트의 영역을 넘어서는지, 
//                // 2. 같은 레벨에 존재하는 다른 프래그먼트의 영역에 닿는지 확인.
//            }
        }
//        // 20120608 by kang
//        // CombinedFragment의 윗쪽 라인이 CombinedFragment 내부의 요소의 아래로 resize되지 않도록 처리.
//        // bounds.height != constraint.height 일 경우 리사이즈가 아닌 무브 이므로 허용.
//        if( bounds.height != constraint.height ) {
//            List<AbstractConnection> containConnectionList = SequenceUtil.getConnectionListInCombinedFragment(diagram, combinedFragmentNode);
//            for( AbstractConnection connection : containConnectionList ) {
////                int absoluteY = SequenceUtil.translateToAbsoluteYByParentNode(0, (ContainerNode) combinedFragmentNode.getParent(), combinedFragmentNode);
//            }
//        }
        
        //결합단편 위로 이동시메시지 영역 침범 검사
        if(checkOverConnection(diagram, combinedFragmentNode, constraint.y)) {
            return false;
        }
        
        //결합단편 위로 이동시메시지 다른 결합단편 영역 침범 검사
        if(SequenceUtil.isDiagramHasCombinedFragement(diagram)) {
            if(isThereCombinedFragment(diagram, combinedFragmentNode, constraint.y)) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * 자식 프래그먼트의 영역을 침범하는지 확인한다.
     * 침범하면 true
     *  
     * @param combinedFragmentNode
     * @param absoluteY
     * @return boolean
     */
    private boolean checkChildFragmentArea(NotationNode combinedFragmentNode, int absoluteY) {
        List<AbstractNode> childFragment = combinedFragmentNode.getCompartmentList();
        for( AbstractNode child : childFragment ) {
            if( child instanceof ContainerNode ) {
                ContainerNode childContainerNode = (ContainerNode) child;
                EList<AbstractNode> childNotationNodes = childContainerNode.getNodeList();
                for(int i = 0; i < childNotationNodes.size(); i++ ) {
                    if( i == 0 ) {
                        continue;
                    }
                    AbstractNode notationNode = childNotationNodes.get(i);
                    if( notationNode instanceof NotationNode ) {
                        int nodeYpoint = SequenceUtil.translateToAbsoluteYByParentNode(0, (ContainerNode) notationNode.getParent(), (NotationNode) notationNode);
                        if( nodeYpoint - 20 < absoluteY ) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * 결합단편 resize시 결합단편 첫번째 operand에 포함된 topConnection보다 아래로 resize시 막는다.
     *  
     * @param diagram
     * @param combinedFragmentNode
     * @param y
     * @return boolean
     */
    private boolean checkUnderConnection(Diagram diagram, NotationNode combinedFragmentNode, int y) {
        ContainerNode topOperandNode = (ContainerNode) combinedFragmentNode.getCompartmentList().get(0);
        List<AbstractConnection> connectionListInOperand = 
            SequenceUtil.ascSortedMessageListByYValue(SequenceUtil.getConnectionListInOperand(diagram, topOperandNode));
        AbstractConnection topConnection = null;
        
        if(connectionListInOperand.size() == 0) {
            return false;
        }
        
        topConnection = connectionListInOperand.get(0);
        
        if(topConnection == null) {
            return false;
        }
        
        //메시지의 이름 영역 추가
        int topY = topConnection.getY();// - UICoreConstant.FRAGMENT_LABEL_HEIGHT;
        if(topY < y) {
            return true;
        } 
        
        return false;
    }

    /**
     * 결합단편 resize시 결합단편 마지막 operand의 lastConnection보다 위로 resize시 막는다.
     *  
     * @param diagram
     * @param combinedFragmentNode
     * @param y
     * @return boolean
     */
    private boolean checkTopConnection(Diagram diagram, NotationNode combinedFragmentNode, int y) {
        int operandLastIndex = combinedFragmentNode.getCompartmentList().size()-1;
        ContainerNode lastOperandNode = (ContainerNode) combinedFragmentNode.getCompartmentList().get(operandLastIndex);
        List<AbstractConnection> connectionListInOperand = 
            SequenceUtil.ascSortedMessageListByYValue(SequenceUtil.getConnectionListInOperand(diagram, lastOperandNode));
        AbstractConnection lastConnection = null;
        
        if(connectionListInOperand.size() == 0) {
            return false;
        }
        
        lastConnection = connectionListInOperand.get(connectionListInOperand.size()-1);
        
        if(lastConnection == null) {
            return false;
        }
        
        //메시지의 이름 영역 추가
        int lastY = lastConnection.getY();
        
        //메시지의 behavior 검사
        if(!RelationType.REPLY_MESSAGE.equals(lastConnection.getRelationType())) {
            NotationNode behaviorNode = (NotationNode) lastConnection.getTarget();
             lastY = lastY + behaviorNode.getHeight();
        }
        
        if(combinedFragmentNode.getParent() instanceof ContainerNode) {
            ContainerNode containerNode = (ContainerNode) combinedFragmentNode.getParent();
            y = SequenceUtil.translateToAbsoluteYByParentNode(y, containerNode, (NotationNode) containerNode.getParent());
        }
        if(lastY > y) {
            return true;
        } 
        
        return false;
    }
    
    /**
     * 결합단편 상위의 메시지 보다 위로 이동을 막을려고 검사한다.
     *  
     * @param diagram
     * @param combinedFragmentNode
     * @param y
     * @return boolean
     */
    private boolean checkOverConnection(Diagram diagram, NotationNode combinedFragmentNode, int y) {
        List<AbstractConnection> sortedConnectionList = SequenceUtil.ascSortedMessageListByYValue(diagram.getConnectionList());
        AbstractConnection nearConnection = null;
        
        int fragmentY = combinedFragmentNode.getY();
        if(combinedFragmentNode.getParent() instanceof ContainerNode) {
            ContainerNode containerNode = (ContainerNode) combinedFragmentNode.getParent();
            y = SequenceUtil.translateToAbsoluteYByParentNode(y, containerNode, (NotationNode) containerNode.getParent());
            fragmentY = SequenceUtil.translateToAbsoluteYByParentNode(fragmentY, containerNode, (NotationNode) containerNode.getParent());
        }
        
        for(AbstractConnection connection : sortedConnectionList) {
            if(fragmentY > connection.getY()) {
                nearConnection = connection;
            } else {
                break;
            }
        }
        
        if(nearConnection == null) {
            return false;
        }
        
        //메시지의 behavior 검사
        if(RelationType.REPLY_MESSAGE.equals(nearConnection.getRelationType())) {
            if(nearConnection.getY() > y) {
                return true;
            } else {
                return false;
            }
        } else {
            NotationNode behaviorNode = (NotationNode) nearConnection.getTarget();
            int lastY = behaviorNode.getY() + behaviorNode.getHeight();
            if(behaviorNode.getY() < fragmentY &&  lastY > fragmentY) {
                if(behaviorNode.getY() < y) {
                    return false;
                } else {
                    return true;
                }
            }
            if(lastY > y) {
                return true;
            } else {
                return false;
            }
        }
        
    }

    /**
     * 이동하려는 위치에 CombinedFragment가 존재하는지 검사
     *  
     * @param diagram
     * @param messageConnection
     * @param location
     * @return boolean
     */
    private boolean isThereCombinedFragment(Diagram diagram, NotationNode combinedFragmentNode, int y) {
        if(combinedFragmentNode.getParent() instanceof ContainerNode) {
            ContainerNode parentNode = (ContainerNode) combinedFragmentNode.getParent();
            List<NotationNode> combindFragmentNodeList = SequenceUtil.getOnlyCombindFragmentNodeList(parentNode.getNodeList());
            if(combindFragmentNodeList.size() <= 0) {
                return false;
            }
            for(NotationNode notationNode : combindFragmentNodeList) {
                if(!combinedFragmentNode.equals(notationNode) && 
                    y >= notationNode.getY() && y <= notationNode.getY() + notationNode.getHeight()) {
                    return true;
                }
            }
            return false;
        } else {
            List<NotationNode> combindFragmentNodeList = SequenceUtil.getOnlyCombindFragmentNodeList(diagram.getNodeList());
            for(NotationNode notationNode : combindFragmentNodeList) {
                if(!combinedFragmentNode.equals(notationNode) && 
                    y >= notationNode.getY() && y <= notationNode.getY() + notationNode.getHeight()) {
                    return true;
                }
            }
            return false;
        }
    }    
    
    
    /**
     * LifeLine관련 이동 및 리사이즈를 처리한다.
     * 
     * @param child
     * @param constraint
     * @return Command
     */
    private Command changeLifeLineConstraint(EditPart child, Rectangle constraint) {
        LifeLineNode lifeLineNode = (LifeLineNode) ((LifeLineEditPart) child).getModel();
        constraint.y = 20; // 높이는 항상 고정

        if (lifeLineNode.getX() != constraint.x) { // 이동
            if (getHost().getParent().getViewer().getSelectedEditParts().size() > 1) {
                // 2개 이상을 선택하고 한번에 이동 할 수 없다.
                return null;
            }
            if (constraint.x < 20) { // -좌표로는 이동 못함
                return null;
            }
            
            List<LifeLineNode> lifeLineNodeList = SequenceUtil.ascSortedLifeLineNodeListByXValue(SequenceUtil.getOnlyLifeLineNodeList(((Diagram) child.getParent()
                .getModel()).getNodeList()));
            if (lifeLineNodeList.size() >= 2 && !checkLeftMove(lifeLineNode, constraint, lifeLineNodeList)) {
                return null;
            }
            
            if (isInsert(lifeLineNode, constraint, lifeLineNodeList)) {
                return insertLifeLineNode(child, constraint, lifeLineNode, lifeLineNodeList);
            } else {
                return shiftLifeLineNode(child, constraint, lifeLineNode, lifeLineNodeList);
            }
        } else if (lifeLineNode.getWidth() == constraint.width && lifeLineNode.getHeight() != constraint.height) {
            return new ReSizeOnlyHeightLifeLineNodeCommand(child, constraint.height);
        } else if (lifeLineNode.getWidth() != constraint.width) {
            return new ReSizeLifeLineNodeCommand(child, constraint);
        } else {
            return null;
        }
    }


    /**
     * 라이프라인 라이프라인 사이에 insert처리
     *  
     * @param child
     * @param constraint
     * @param lifeLineNode
     * @param lifeLineNodeList
     * @return Command
     */
    private Command insertLifeLineNode(EditPart child, Rectangle constraint, LifeLineNode lifeLineNode,
                                       List<LifeLineNode> lifeLineNodeList) {
        if(!hasCombindFragment(lifeLineNode, (Diagram) child.getParent().getModel())) {
            if(!isThereCombinedFragmentAboutInsert(constraint, lifeLineNode, (Diagram) child.getParent().getModel())) {                        
                return new InsertLifeLineNodeCommand(child, constraint, lifeLineNodeList, (Diagram) child.getParent().getModel());                        
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
    

    /**
     * 라이프라인 sifht이동처리
     *  
     * @param child
     * @param constraint
     * @param lifeLineNode
     * @param lifeLineNodeList
     * @return Command
     */
    private Command shiftLifeLineNode(EditPart child, Rectangle constraint, LifeLineNode lifeLineNode,
                                      List<LifeLineNode> lifeLineNodeList) {
        if(!hasCombindFragment(lifeLineNode, (Diagram) child.getParent().getModel())) {
            if(!isThereCombinedFragmentAboutShift(constraint, lifeLineNode, (Diagram) child.getParent().getModel())) {     
                return new ShiftLifeLineNodeCommand(child, constraint, lifeLineNodeList, (Diagram) child.getParent().getModel());                    
            } else {
                return null;
            }
        } else {
            return new ShiftLifeLineNodeCommand(child, constraint, lifeLineNodeList, (Diagram) child.getParent().getModel());         
        }
    }

    /**
     * 이동하려는 위치에 fragment가 있는지 확인
     *  
     * @param constraint
     * @param lifeLineNode
     * @param diagram
     * @return boolean
     */
    private boolean isThereCombinedFragmentAboutInsert(Rectangle constraint, LifeLineNode lifeLineNode, Diagram diagram) {
        List<NotationNode> fragmentNodeList = SequenceUtil.getOnlyCombindFragmentNodeList(diagram.getNodeList());
        if(fragmentNodeList.size() <= 0) {
            return false;
        }        

        NotationNode fragmentNode;
        for (int i = 0; i < fragmentNodeList.size(); i++) {
            fragmentNode =  fragmentNodeList.get(i);
            if(fragmentNode.getX() < constraint.x && constraint.x < fragmentNode.getX() + fragmentNode.getWidth()) {
                return true;
            }
            if(fragmentNode.getX() < constraint.x + constraint.width && constraint.x + constraint.width < fragmentNode.getX() + fragmentNode.getWidth()) {
                return true;
            }
        }
        
        return false;
    }

    /**
     * 이동하려는 위치에 fragment가 있는지 확인
     *  
     * @param constraint
     * @param lifeLineNode
     * @param diagram
     * @return boolean
     */
    private boolean isThereCombinedFragmentAboutShift(Rectangle constraint, LifeLineNode lifeLineNode, Diagram diagram) {
        List<NotationNode> fragmentNodeList = SequenceUtil.getOnlyCombindFragmentNodeList(diagram.getNodeList());
        if(fragmentNodeList.size() <= 0) {
            return false;
        }        

        NotationNode fragmentNode;
        for (int i = 0; i < fragmentNodeList.size(); i++) {
            fragmentNode =  fragmentNodeList.get(i);
            if(fragmentNode.getX() < constraint.x && constraint.x < fragmentNode.getX() + fragmentNode.getWidth()) {
                return true;
            }
        }
        
        return false;
    }
    /**
     * 이동하려는 라이프라인이 fragment에 포함되어 있는지 확인
     *  
     * @param lifeLineNode
     * @param diagram
     * @return boolean
     */
    private boolean hasCombindFragment(LifeLineNode lifeLineNode, Diagram diagram) {
        List<NotationNode> fragmentNodeList = SequenceUtil.getOnlyCombindFragmentNodeList(diagram.getNodeList());
        if(fragmentNodeList.size() <= 0) {
            return false;
        }        

        NotationNode fragmentNode;
        CombinedFragment combindFragment;
        for (int i = 0; i < fragmentNodeList.size(); i++) {
            fragmentNode =  fragmentNodeList.get(i);
            combindFragment = (CombinedFragment) fragmentNode.getUmlModel();
            if(combindFragment.getCovereds().contains(lifeLineNode.getUmlModel())) {
                return true;
            }            
        }
        
        return false;
    }

    /**
     * 왼쪽 이동시 왼쪽에 LifeLine보다 더 왼쪽으로 갈 수 없다.
     * 
     * @param child
     * @param bounds
     * @return boolean
     */
    private boolean checkLeftMove(LifeLineNode lifeLineNode, Rectangle bounds, List<LifeLineNode> lifeLineNodeList) {

        AbstractNode abstractNode;
        AbstractNode leftNode;
        for (int i = 0; i < lifeLineNodeList.size(); i++) {
            abstractNode = lifeLineNodeList.get(i);
            if (lifeLineNode.equals(abstractNode) && i > 0) {
                leftNode = lifeLineNodeList.get(i - 1);
                if (leftNode.getX() + leftNode.getWidth() > bounds.x && leftNode.getX() < bounds.x) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 이동이 라이프라인 사이에 Insert되는 이동인지 확인한다.
     * 
     * @param child
     * @param bounds
     * @param lifeLineNodeList
     * @return boolean
     */
    private boolean isInsert(LifeLineNode lifeLineNode, Rectangle bounds, List<LifeLineNode> lifeLineNodeList) {

        // 좌측 이동을 검사한다.
        if (lifeLineNode.getX() > bounds.x) {
            return checkLeftInsert(lifeLineNode, bounds, lifeLineNodeList);
        } else { // 우측 이동을 검사한다.
            return checkRightInsert(lifeLineNode, bounds, lifeLineNodeList);
        }
    }

    /**
     * checkLeftInsert
     * 
     * @param lifeLineNode
     * @param bounds
     * @param lifeLineNodeList
     * @return boolean
     */
    private boolean checkLeftInsert(LifeLineNode lifeLineNode, Rectangle bounds, List<LifeLineNode> lifeLineNodeList) {
        AbstractNode abstractNode;
        AbstractNode nearNode;
        for (int i = 0; i < lifeLineNodeList.size(); i++) {
            abstractNode = lifeLineNodeList.get(i);
            if (lifeLineNode.equals(abstractNode)) {
                if (i == 0) {
                    return false;
                } else {
                    nearNode = lifeLineNodeList.get(i - 1);
                    if (nearNode.getX() > bounds.x) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * checkRightInsert
     * 
     * @param lifeLineNode
     * @param bounds
     * @param lifeLineNodeList
     * @return boolean
     */
    private boolean checkRightInsert(LifeLineNode lifeLineNode, Rectangle bounds, List<LifeLineNode> lifeLineNodeList) {
        AbstractNode abstractNode;
        AbstractNode nearNode;
        for (int i = 0; i < lifeLineNodeList.size(); i++) {
            abstractNode = lifeLineNodeList.get(i);
            if (lifeLineNode.equals(abstractNode)) {
                if (i == lifeLineNodeList.size() - 1) {
                    return false;
                } else {
                    nearNode = lifeLineNodeList.get(i + 1);
                    if (nearNode.getX() < bounds.x) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    /**
     * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#getCreateCommand(org.eclipse.gef.requests.CreateRequest)
     */
    @Override
    protected Command getCreateCommand(CreateRequest request) {
        Point location = new Point(request.getLocation());
        getHostFigure().translateToRelative(location);
        Rectangle createBounds = getSizeOnDropFeedback().getBounds();
        
        // 프래그먼트가 리플라이에만 적용됨을 방지. 리플라이의 Behavior가 포함되지 않는다면 생성하지 않는다.
        Diagram diagram = null;
        if( getHost().getModel() instanceof Diagram ) {
            diagram = (Diagram) getHost().getModel();
        } else if( getHost().getModel() instanceof AbstractNode ) {
            AbstractNode node = (AbstractNode) getHost().getModel();
            diagram = SequenceUtil.getDiagram(node);
        }
        
        if (NodeType.LIFELINE.equals(request.getNewObjectType())) {
            if(!(getHost().getModel() instanceof Diagram)) {
                //라이프라인은 다이어그램에만 생성 가능하다.
                UiUtil.eraseFeedback(getHost());
                return null;
            }
            return new CreateLifeLineNodeCommand(getHost(), request, location);
        } else if (NodeType.COMBINED_FRAGMENT.equals(request.getNewObjectType())) {
            if(!checkAvailableCreateCombinedFragment(request, location)) {
                UiUtil.eraseFeedback(getHost());
                return null;
            }
            
            if( request.getSize() == null ) {
                return new CreateCombinedFragmentNodeCommand(getHost(), request, createBounds);
            }
            
            
            boolean checkBehaviorLocation = true;
            if (diagram != null) {
                List<AbstractConnection> connections = diagram.getConnectionList();
                if (request.getLocation() != null && request.getSize() != null) {
                    for (AbstractConnection conn : connections) {

                        AbstractNode node = null;
                        if (((AbstractNode) conn.getTarget()).getNodeType() == NodeType.LIFE_LINE_BEHAVIOR) {
                            node = (AbstractNode) conn.getTarget();
                        } else if (((AbstractNode) conn.getSource()).getNodeType() == NodeType.LIFE_LINE_BEHAVIOR) {
                            node = (AbstractNode) conn.getSource();
                        }

                        Rectangle behaviorBounds = new Rectangle(node.getX(),
                            node.getY(),
                            node.getWidth(),
                            node.getHeight());

//                        if( createBounds.y < behaviorBounds.y && behaviorBounds.y + behaviorBounds.height < createBounds.y + createBounds.height) {
                        if( createBounds.y < behaviorBounds.y && behaviorBounds.y < createBounds.y + createBounds.height) {
                            if( !createBounds.contains(new Point(behaviorBounds.x, behaviorBounds.y)) ) {
                                checkBehaviorLocation = false;
                                break;
                            }
                        }
                        
                        if( behaviorBounds.y < createBounds.y + createBounds.height && createBounds.y + createBounds.height < behaviorBounds.y + behaviorBounds.height ) {
                            checkBehaviorLocation = false;
                            break;
                        }
                        
                        
//                        if (createBounds.y < node.getY() && node.getY() < createBounds.y + createBounds.height) {
//                            if (createBounds.y < node.getY() + node.getHeight()
//                                && node.getY() + node.getHeight() < createBounds.y + createBounds.height) {
//                                AbstractNode connNode = null;
//                                int realXPoint = -1;
//                                if (conn.getSource().equals(node)) {
//                                    connNode = (AbstractNode) conn.getTarget();
//                                } else {
//                                    connNode = (AbstractNode) conn.getSource();
//                                }
//                                if (connNode == null) {
//                                    return null;
//                                }
//                                realXPoint = connNode.getX();
//
//                                if (connNode.getNodeType() == NodeType.LINE) {
//                                    connNode = (AbstractNode) connNode.eContainer();
//                                    realXPoint = connNode.getX() + (connNode.getWidth() / 2);
//                                }
//
//                                if (createBounds.x < realXPoint && realXPoint < createBounds.x + createBounds.width) {
//                                    return new CreateCombinedFragmentNodeCommand(getHost(), request, createBounds);
//                                } else {
//                                    return null;
//                                }
//                            } else {
//                                return null;
//                            }
//                        } else {
//                            return null;
//                        }
                    }
                }
            }
            if( checkBehaviorLocation ) {
                for( AbstractNode abstractNode : diagram.getNodeList() ) {
                    if( abstractNode instanceof LifeLineNode ) {
                        LifeLineNode lifeLine = (LifeLineNode) abstractNode;
                        for( NotationNode behavior : lifeLine.getBehaviorList() ) {
                            
                            Point startPoint = new Point(behavior.getX(), behavior.getY());
                            Point endPoint = new Point(behavior.getX() + behavior.getWidth(),
                                behavior.getY() + behavior.getHeight());
                            if( createBounds.contains(endPoint) ) {
                                if( !(createBounds.contains(startPoint) && createBounds.contains(endPoint)) ) {
                                    return null;
                                }
                            }
                        }
                    }
                }
                return new CreateCombinedFragmentNodeCommand(getHost(), request, createBounds);
            } else {
                return null;
            }
            
        }else {
            UiUtil.eraseFeedback(getHost());
            return super.getCreateCommand(request);
        }

    }

    /**
     * combinedFragment를 생성 할 수 있는지 검사한다.
     *  
     * @param location
     * @return boolean
     */
    private boolean checkAvailableCreateCombinedFragment(CreateRequest request, Point location) {
        
        if(isThereOtherCombinedFragment(location)) {
            return false;
        }
        
        AbstractNode parentNode = (AbstractNode) getHost().getModel();
        if(parentNode instanceof ContainerNode) {
            ContainerNode containerNode = (ContainerNode) parentNode;
            if(isOverParentOperandSize(containerNode, location, request.getSize())) {
               return false;
            }
        }
        
        return true;
    }
    
    /**
     * 생성하려는 위치에 다른 CombinedFragment가 존재하는지 검사
     *  
     * @return boolean
     */
    private boolean isThereOtherCombinedFragment(Point location) {
        AbstractNode parentNode = (AbstractNode) getHost().getModel();
        if(parentNode instanceof ContainerNode) {
            ContainerNode containerNode = (ContainerNode) parentNode;
            List<NotationNode> combindFragmentNodeList = SequenceUtil.getOnlyCombindFragmentNodeList(containerNode.getNodeList());
            if(combindFragmentNodeList.size() <= 0) {
                return false;
            }
            
            for(NotationNode notationNode : combindFragmentNodeList) {
                int fragmentY = SequenceUtil.translateToAbsoluteYByParentNode(notationNode.getY(), containerNode, (NotationNode) containerNode.getParent());
                if(location.y >= fragmentY && location.y <= fragmentY + notationNode.getHeight()) {
                    return true;
                }
            }
            return false;
        } else {
            Diagram diagram = (Diagram) parentNode;
            List<NotationNode> combindFragmentNodeList = SequenceUtil.getOnlyCombindFragmentNodeList(diagram.getNodeList());
            for(NotationNode notationNode : combindFragmentNodeList) {
                if(location.y >= notationNode.getY() && location.y <= notationNode.getY() + notationNode.getHeight()) {
                    return true;
                }
            }
            return false;
        }
    }
    

    /**
     * 생성하려는 위치에 부모 Operand size를 오버하는지 검사
     *  
     * @return boolean
     */
    private boolean isOverParentOperandSize(ContainerNode operandNode, Point location, Dimension size) {
        
        NotationNode parentFragmentNode = (NotationNode) operandNode.getParent();
        int operandY = SequenceUtil.getOperandY((NotationNode) operandNode.getParent(), operandNode);
        int operandX = SequenceUtil.translateToAbsoluteXByParentNode(operandNode.getX(), parentFragmentNode);

        if(location.y < operandY) {
            return true;
        }        
        
        if(location.x < operandX) {
            return true;
        }

        if(size != null) {
            if(location.y + size.height > operandY + operandNode.getHeight()) {
                return true;
            }
            
            //operand는 width가 없다, 부모의 width로 계산한다.
            if(location.x + size.width > operandX + parentFragmentNode.getWidth()) {
                return true;
            }
        }
        
        return false;
    }
}
