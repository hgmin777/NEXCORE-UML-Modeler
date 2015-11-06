/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.project.explorer.action;

import nexcore.tool.uml.ui.core.project.ITreeNode;
import nexcore.tool.uml.ui.core.registry.ProjectRegistry.ViewerRegistry;
import nexcore.tool.uml.ui.core.registry.ResourceManager;
import nexcore.tool.uml.ui.core.util.ProjectUtil;
import nexcore.tool.uml.ui.project.builder.UMLModelRefreshAction;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.navigator.CommonViewer;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.action</li>
 * <li>설 명 : RefreshAction</li>
 * <li>작성일 : 2009. 12. 8.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
public class RefreshAction extends CommonActionDelegate {

    /**
     * 'F5' key action.
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        selectionChanged(null, ViewerRegistry.getViewer().getSelection());
    }

    /**
     * @see nexcore.tool.uml.ui.project.explorer.action.CommonActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    @Override
    public void run(IAction action) {
        // to nothing
    }

    /**
     * execute
     *   void
     */
    public void execute() {
        CommonViewer commonViewer = ViewerRegistry.getViewer();
        if (commonViewer.getControl().isDisposed()) {
            return;
        }

        selectionChanged(null, ViewerRegistry.getViewer().getSelection());

        ISelection selection = commonViewer.getSelection();
        if ((null == selection) || (!(selection instanceof IStructuredSelection))) {
            return;
        }

        Object object = ((IStructuredSelection) selection).getFirstElement();
        if (object instanceof IResource || object instanceof ITreeNode) {
            UMLModelRefreshAction clearAction = new UMLModelRefreshAction();
            clearAction.selectionChanged(null, selection);
            clearAction.run(null);
        } else {
            ProjectUtil.refreshExplorer();
        }

        // 로그인 안내 메시지를 다시 활성화 시킨다. 기존에는 단 한번만 안내했었음.
        ResourceManager.setChecked(true);
    }

}
