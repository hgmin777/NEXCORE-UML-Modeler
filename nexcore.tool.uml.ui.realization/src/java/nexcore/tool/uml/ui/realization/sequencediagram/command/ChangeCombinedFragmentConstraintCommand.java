/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.realization.sequencediagram.command;

import java.util.List;

import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.realization.sequencediagram.util.SequenceUtil;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.command</li>
 * <li>설  명 : ChangeCombinedFragmentConstraintCommand</li>
 * <li>작성일 : 2011. 4. 15.</li>
 * <li>작성자 : zerotae</li>
 * </ul>
 */
public class ChangeCombinedFragmentConstraintCommand extends Command {

    /**
     * 현재의 constraint
     */
    private Rectangle newConstraint;

    /**
     * 이전의 constraint
     */
    private Rectangle oldConstraint;

    /** NotationNode */
    private NotationNode notationNode;
        
    /**
     * ChangeCombinedFragmentConstraintCommand
     * @param editPart
     * @param constraint
     */
    public ChangeCombinedFragmentConstraintCommand(EditPart editPart, Rectangle constraint) {
        this.notationNode = (NotationNode) editPart.getModel();
        this.oldConstraint = new Rectangle(notationNode.getX(), notationNode.getY(), notationNode.getWidth(), notationNode.getHeight());
        this.newConstraint = constraint;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {
        
        boolean resize = false;
        
        int delta = 0;
        
        if( newConstraint.height - oldConstraint.height != 0 && newConstraint.y - oldConstraint.y != 0  ) {
            if(  newConstraint.y - oldConstraint.y != 0 ) {
                notationNode.setY(newConstraint.y);      
                notationNode.setWidth(newConstraint.width);
                notationNode.setHeight(newConstraint.height);
                int lastNodeInedex = notationNode.getCompartmentList().size() -1;
                ContainerNode operandNode = (ContainerNode) notationNode.getCompartmentList().get(lastNodeInedex);
                operandNode.setHeight(newConstraint.height);  
                return;
            }
        }
        
        
        if( newConstraint.height - oldConstraint.height != 0 ) {
            resize = true;
            delta = newConstraint.height - oldConstraint.height;
            SequenceUtil.shiftByNewInputBounds(delta, notationNode, resize);
            
        } 
        if( newConstraint.y - oldConstraint.y != 0 ) {
            delta = newConstraint.y - oldConstraint.y;
            SequenceUtil.shiftByNewInputBounds(delta, notationNode, resize);
        }
        
        notationNode.setWidth(newConstraint.width);
        notationNode.setY(newConstraint.y);        

        if( notationNode.getParent() instanceof ContainerNode ) {
            int absoluteY = SequenceUtil.translateToAbsoluteYByParentNode(0, (ContainerNode) notationNode.getParent(), notationNode);
            Diagram diagram = SequenceUtil.getDiagram(notationNode);
            List<NotationNode> allFragments = SequenceUtil.getAllCombinedFragmentNodeInDiagram(diagram);
         
            // 중복 시프트를 하지 않도록 프래그먼트 리스트를 검사하여 부모-자식 관계의 프래그먼트는 자식을 리스트에서 삭제한다.
            SequenceUtil.checkDuplicatedNode(allFragments);
            
            for (NotationNode abstractNode : allFragments) {
                // 프래그먼트 하위요소가 아니면 넘어감.
                if( !(abstractNode.getParent() instanceof ContainerNode) ) {
                    continue;
                } 
                // 같은 부모를 가지고 있는지 확인. 
                if( !SequenceUtil.getAncestor(notationNode).equals(SequenceUtil.getAncestor(abstractNode))) {
                    continue;
                }
                if( notationNode.getCompartmentList().contains(abstractNode.getParent()) ) {
                    continue;
                }
                int absoluteNodeY = SequenceUtil.translateToAbsoluteYByParentNode(0, (ContainerNode) abstractNode.getParent(), abstractNode);
//                if (absoluteY < absoluteNodeY && absoluteY + notationNode.getHeight() < absoluteNodeY ) {
//                    abstractNode.setY(abstractNode.getY() + delta);
//                } else if (absoluteY + notationNode.getHeight() < absoluteNodeY) {
//                    abstractNode.setY(abstractNode.getY() + delta);
//                }
                if (notationNode.getY() + notationNode.getHeight() < absoluteNodeY && absoluteNodeY < notationNode.getY() + notationNode.getHeight() - delta) {
                    abstractNode.setY(abstractNode.getY() + delta);
                } else if (notationNode.getY() + notationNode.getHeight() < absoluteNodeY && absoluteNodeY < notationNode.getY() + notationNode.getHeight() ) {
                    abstractNode.setY(abstractNode.getY() + delta);
                } else if( notationNode.getY() < abstractNode.getY() ) {
                    abstractNode.setY(abstractNode.getY() + delta);
                }
            }
        }

        if( resize ) {
            notationNode.setHeight(newConstraint.height);
            //아래로 높이가 변경된 경우
            int fragmentHeightConstraint = newConstraint.height - oldConstraint.height;
            if(fragmentHeightConstraint != 0) { //높이가 변경된 경우 
                if(newConstraint.y == oldConstraint.y) {
                    int lastNodeInedex = notationNode.getCompartmentList().size() -1;
                    ContainerNode operandNode = (ContainerNode) notationNode.getCompartmentList().get(lastNodeInedex);
                    operandNode.setHeight(operandNode.getHeight() + fragmentHeightConstraint);     
                }
            }
        }

        
        // CombinedFragment의 사이즈 조절 시, 사이즈 조절이 되지 않고 move되던 부분 수정. 
//        if(fragmentHeightConstraint != 0 && newConstraint.y > oldConstraint.y) {
//            fragmentHeightConstraint = 0;
//        }
        
//        if(fragmentHeightConstraint != 0) { //높이가 변경된 경우       
//            notationNode.setHeight(notationNode.getHeight() + fragmentHeightConstraint);
//            //위로 높이가 변경된 경우(위로 늘림)
//            if(newConstraint.y != oldConstraint.y) {
//                //가장 위에 위치한 operand노드의 높이를 늘려준다.
//                ContainerNode operandNode = (ContainerNode) notationNode.getCompartmentList().get(0);
//                //Operand높이가 변경될 경우 자식 cominedFragment node의 절대값을 유지 시킨다.
//                SequenceUtil.setOperandHeight(operandNode, fragmentHeightConstraint);
//            } else {
//                //아래로 높이가 변경된 경우
//                int lastNodeInedex = notationNode.getCompartmentList().size() -1;
//                ContainerNode operandNode = (ContainerNode) notationNode.getCompartmentList().get(lastNodeInedex);
//                operandNode.setHeight(operandNode.getHeight() + fragmentHeightConstraint);       
//                
//                
//                // 라이프라인 크기 조정
//                Diagram diagram = SequenceUtil.getDiagram(notationNode);
//                SequenceUtil.setAllLifeLineHeight(diagram);
//            }
//        } else if( newConstraint.y - oldConstraint.y > 0 ) {
            // 라이프라인 크기 조정
            Diagram diagram = SequenceUtil.getDiagram(notationNode);
            SequenceUtil.setAllLifeLineHeight(diagram);
//        }
    }
}
