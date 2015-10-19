/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.project.explorer.action;

import nexcore.tool.uml.manager.utility.DomainModelHandlerUtil;

import org.eclipse.jface.action.IAction;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.action</li>
 * <li>설 명 : SaveAction</li>
 * <li>작성일 : 2009. 12. 11.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
public class SaveAction extends CommonActionDelegate {

    /**
     * @see nexcore.tool.uml.ui.project.explorer.action.CommonActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    @Override
    public void run(IAction action) {
        // DomainRegistry.getUMLDomain().save(eobject);
        DomainModelHandlerUtil.save(selectedEObject);

        // ViewerRegistry.getViewer().refresh();
        // ViewerRegistry.getViewer().setSelection(
        // new StructuredSelection(FocusRegistry.getFocused()));

        // ProjectUtil.refreshExplorer();
    }

}
