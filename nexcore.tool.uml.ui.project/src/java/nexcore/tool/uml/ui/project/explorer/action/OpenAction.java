/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.project.explorer.action;

import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.ui.core.util.ProjectUtil;

import org.eclipse.jface.action.IAction;
import org.eclipse.uml2.uml.Model;

/**
 * '열기' 액션을 Context Menu에서 선택하여 실행했을 때. 
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.action</li>
 * <li>설 명 : OpenAction</li>
 * <li>작성일 : 2009. 12. 3.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
public class OpenAction extends CommonActionDelegate {

    /**
     * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    public void run(IAction action) {
        if (selectedEObject instanceof Diagram) {
            ProjectUtil.openDiagram(selectedEObject);
        } else if (selectedEObject instanceof Model) {
            ProjectUtil.openEditor(selectedEObject);
        }
    }
}
