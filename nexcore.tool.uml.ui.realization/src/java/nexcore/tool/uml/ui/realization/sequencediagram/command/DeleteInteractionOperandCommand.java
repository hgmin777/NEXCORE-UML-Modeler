/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.realization.sequencediagram.command;

import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.realization.sequencediagram.util.SequenceUtil;

import org.eclipse.gef.commands.Command;
import org.eclipse.uml2.uml.CombinedFragment;
import org.eclipse.uml2.uml.InteractionOperand;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.command</li>
 * <li>설  명 : DeleteInteractionOperandCommand</li>
 * <li>작성일 : 2011. 4. 26.</li>
 * <li>작성자 : zerotae</li>
 * </ul>
 */
public class DeleteInteractionOperandCommand extends Command {

    /**
     * ParentNode
     */
    private NotationNode parentNode;

    /**
     * 삭제할 노드
     */
    private ContainerNode operandNode;
    
    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        
        try {

            int oldFragmentLastY = parentNode.getY();
            if(parentNode.getParent() instanceof ContainerNode) {
                ContainerNode containerNode = (ContainerNode) parentNode.getParent();
                oldFragmentLastY = SequenceUtil.translateToAbsoluteYByParentNode(oldFragmentLastY, containerNode, (NotationNode) containerNode.getParent());    
            }
            oldFragmentLastY += parentNode.getHeight();
            
            SequenceUtil.deleteOperandChild(operandNode);
            
            InteractionOperand interactionOperand  = (InteractionOperand) operandNode.getUmlModel();
            CombinedFragment combinedFragment = (CombinedFragment) parentNode.getUmlModel();
            combinedFragment.getOperands().remove(interactionOperand);
            
            parentNode.setHeight(parentNode.getHeight() - operandNode.getHeight());
            //맨 마지막 오퍼랜드가 아니면 지워지는 오퍼랜드에 영향을 받는 부분을 쉬프트한다.
            if(parentNode.getCompartmentList().size()-1 != parentNode.getCompartmentList().indexOf(operandNode)) {
                SequenceUtil.shiftByDeletedOperand(parentNode, operandNode, oldFragmentLastY);
            }
            parentNode.getCompartmentList().remove(operandNode);        
            UMLManager.deleteElement(operandNode);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
        
    /**
     * getNode
     *  
     * @return ContainerNode
     */
    public ContainerNode getNode() {
        return operandNode;
    }

    /**
     * setNode
     *  
     * @param node void
     */
    public void setNode(ContainerNode node) {
        this.operandNode = node;
    }

    /**
     * getParentNode
     *  
     * @return AbstractNode
     */
    public AbstractNode getParentNode() {
        return parentNode;
    }

    /**
     * setParentNode
     *  
     * @param parentNode void
     */
    public void setParentNode(NotationNode parentNode) {
        this.parentNode = parentNode;
    }
}
