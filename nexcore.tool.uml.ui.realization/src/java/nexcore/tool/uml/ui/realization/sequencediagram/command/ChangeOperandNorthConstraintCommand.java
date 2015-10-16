/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.realization.sequencediagram.command;

import java.util.List;

import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.realization.sequencediagram.util.SequenceUtil;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.command</li>
 * <li>설  명 : ChangeOperandNorthConstraintCommand</li>
 * <li>작성일 : 2011. 4. 15.</li>
 * <li>작성자 : zerotae</li>
 * </ul>
 */
public class ChangeOperandNorthConstraintCommand extends Command {

    /**
     * 현재의 constraint
     */
    private int heightDelta = 0;

    /**
     * 이전의 constraint
     */
    private int oldHeight = 0;;

    /** ParentNode */
    private NotationNode parentNode;

    /** NotationNode */
    private ContainerNode operandNode;
    
    
    /**
     * ChangeOperandNorthConstraintCommand
     * @param editPart
     * @param heightDelta
     */
    public ChangeOperandNorthConstraintCommand(EditPart editPart, int heightDelta) {
        this.operandNode = (ContainerNode) editPart.getModel();
        this.oldHeight = operandNode.getHeight();
        this.heightDelta = heightDelta;
        this.parentNode = (NotationNode) editPart.getParent().getModel();
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {

        int indexOf = parentNode.getCompartmentList().indexOf(operandNode);
        if(indexOf > 0) {
            ContainerNode upperOperandNode = (ContainerNode) parentNode.getCompartmentList().get(indexOf -1);
            if(operandNode.getHeight() + heightDelta >= UICoreConstant.OPERAND_MIN_HEIGHT) {
                operandNode.setHeight(operandNode.getHeight() + heightDelta);
                upperOperandNode.setHeight(upperOperandNode.getHeight() - heightDelta);
                setChildCombinedFragmentY(operandNode, heightDelta);
            } else {
                operandNode.setHeight(UICoreConstant.OPERAND_MIN_HEIGHT);
                upperOperandNode.setHeight(upperOperandNode.getHeight() - heightDelta - (UICoreConstant.OPERAND_MIN_HEIGHT - (oldHeight + heightDelta)));   
            }
        }
    }

    /**
     * setChildCombinedFragmentY
     *  
     * @param operandNode
     * @param i void
     */
    private void setChildCombinedFragmentY(ContainerNode operandNode, int heightDelta) {
        List<NotationNode> combindFragmentNodeList = SequenceUtil.getOnlyCombindFragmentNodeList(operandNode.getNodeList());
        if(combindFragmentNodeList.size() <= 0 ) {
            return;
        }
        
        for(NotationNode childNode : combindFragmentNodeList) {
            childNode.setY(childNode.getY() + heightDelta);
        }
        
    }

}
