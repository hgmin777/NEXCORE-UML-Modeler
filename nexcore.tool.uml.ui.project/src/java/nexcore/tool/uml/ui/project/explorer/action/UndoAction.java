/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.project.explorer.action;

import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.ui.core.util.ProjectUtil;

import org.eclipse.gef.commands.CommandStack;
import org.eclipse.ui.IWorkbenchPart;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.action</li>
 * <li>설 명 : UndoAction</li>
 * <li>작성일 : 2009. 12. 21.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
public class UndoAction extends org.eclipse.gef.ui.actions.UndoAction {

    /** command stack for UMLDomain. */
    private CommandStack commandStack;

    /**
     * constructor.
     * 
     * @param workbenchPart
     */
    public UndoAction(IWorkbenchPart workbenchPart) {
        super(workbenchPart);
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

    /**
     * @see org.eclipse.gef.ui.actions.UndoAction#run()
     */
    public void run() {
        super.run();

        // 2011-02-16 nspark
        // undo 시 모델이 갱신되지 않은 상태에서 트리 refresh를 할 경우 기존 값이 null 로 나오는 문제 발생
        // 해당 노드를 강제로 refresh 처리를 해준다.
        ProjectUtil.refreshSelectionNode();

    }
}
