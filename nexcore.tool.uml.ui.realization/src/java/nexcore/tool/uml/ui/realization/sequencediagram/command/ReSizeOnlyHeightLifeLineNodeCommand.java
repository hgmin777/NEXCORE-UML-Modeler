/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.realization.sequencediagram.command;

import nexcore.tool.uml.model.umldiagram.AbstractNode;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.command</li>
 * <li>설 명 : ReSizeOnlyHeightLifeLineNodeCommand</li>
 * <li>작성일 : 2009. 12. 22.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class ReSizeOnlyHeightLifeLineNodeCommand extends Command {

    /**
     * 현재의 newHeight
     */
    private int newHeight;

    /**
     * 이전의 oldHeight
     */
    private int oldHeight;

    /** viewModel */
    private AbstractNode node;

    /**
     * ReSizeOnlyHeightLifeLineNodeCommand
     * @param editPart
     * @param height
     */
    public ReSizeOnlyHeightLifeLineNodeCommand(EditPart editPart, int height) {
        this.node = (AbstractNode) editPart.getModel();
        oldHeight = node.getHeight();
        this.newHeight = height;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {
        node.setHeight(newHeight);
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    @Override
    public void undo() {
        node.setHeight(oldHeight);
    }

}
