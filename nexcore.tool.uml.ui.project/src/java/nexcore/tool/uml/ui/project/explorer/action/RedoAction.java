/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.project.explorer.action;

import nexcore.tool.uml.manager.utility.DomainRegistry;

import org.eclipse.gef.commands.CommandStack;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.action</li>
 * <li>설 명 : RedoAction</li>
 * <li>작성일 : 2009. 12. 28.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
public class RedoAction extends org.eclipse.gef.ui.actions.RedoAction {

    /** command stack for UMLDomain. */
    private CommandStack commandStack;

    /**
     * 
     */
    public RedoAction() {
        super(null);
        // commandStack = DomainRegistry.getUMLDomain().getCommandStack();
        commandStack = (CommandStack) DomainRegistry.getUMLDomain().getCommandStackListener();
    }

    /**
     * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#getCommandStack()
     */
    @Override
    protected CommandStack getCommandStack() {
        return commandStack;
    }

}
