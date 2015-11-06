/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.realization.sequencediagram.command;

import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.realization.sequencediagram.util.SequenceUtil;

import org.eclipse.gef.commands.Command;
import org.eclipse.uml2.uml.CombinedFragment;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.InteractionOperand;
import org.eclipse.uml2.uml.Lifeline;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.command</li>
 * <li>설  명 : DeleteCombinedFragmentCommand</li>
 * <li>작성일 : 2011. 4. 26.</li>
 * <li>작성자 : zerotae</li>
 * </ul>
 */
public class DeleteCombinedFragmentCommand extends Command {

    /**
     * ParentNode
     */
    private AbstractNode parentNode;

    /**
     * 삭제할 노드
     */
    private NotationNode node;
    
    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        
        try {
            SequenceUtil.deleteOperandList(node);
            CombinedFragment combinedFragment = (CombinedFragment) node.getUmlModel();

            if(parentNode instanceof Diagram) {
                Interaction interaction = (Interaction) combinedFragment.eContainer();
                interaction.getFragments().remove(combinedFragment);
                ((Diagram) parentNode).getNodeList().remove(node);
            } else if (parentNode instanceof ContainerNode) {
                InteractionOperand interaction = (InteractionOperand) ((ContainerNode) parentNode).getUmlModel();
                interaction.getFragments().remove(combinedFragment);
                ((ContainerNode) parentNode).getNodeList().remove(node);       
            }
            
            UMLManager.deleteElement(node);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
   
        
    /**
     * getNode
     *  
     * @return NotationNode
     */
    public NotationNode getNode() {
        return node;
    }

    /**
     * setNode
     *  
     * @param node void
     */
    public void setNode(NotationNode node) {
        this.node = node;
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
    public void setParentNode(AbstractNode parentNode) {
        this.parentNode = parentNode;
    }
}
