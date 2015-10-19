/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.project.explorer.provider;

import nexcore.tool.uml.ui.core.project.explorer.provider.BaseUMLTreeActionProvider;
import nexcore.tool.uml.ui.project.explorer.action.CopyAction;
import nexcore.tool.uml.ui.project.explorer.action.DeleteElementAction;
import nexcore.tool.uml.ui.project.explorer.action.PasteAction;
import nexcore.tool.uml.ui.project.explorer.action.RedoAction;
import nexcore.tool.uml.ui.project.explorer.action.RefreshAction;
import nexcore.tool.uml.ui.project.explorer.action.RenameAction;
import nexcore.tool.uml.ui.project.explorer.action.UndoAction;

import org.eclipse.ui.IActionBars;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.provider</li>
 * <li>설 명 : UMLTreeActionProvider</li>
 * <li>작성일 : 2009. 12. 7.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
public class UMLTreeActionProvider extends BaseUMLTreeActionProvider {

    /**
     * @see org.eclipse.ui.actions.ActionGroup#fillActionBars(org.eclipse.ui.IActionBars)
     */
    @Override
    public void fillActionBars(IActionBars actionBars) {

        actionBars.setGlobalActionHandler(ActionFactory.DELETE.getId(), new DeleteElementAction());
        actionBars.setGlobalActionHandler(ActionFactory.REFRESH.getId(), new RefreshAction());
        actionBars.setGlobalActionHandler(ActionFactory.RENAME.getId(), new RenameAction());
        actionBars.setGlobalActionHandler(ActionFactory.UNDO.getId(),
            new UndoAction(((ICommonViewerWorkbenchSite) getActionSite().getViewSite()).getPart()));
        actionBars.setGlobalActionHandler(ActionFactory.REDO.getId(), new RedoAction());
        
        // rename 모드일 경우 일반적인 ctrl+c,v 의 액션이고
        // rename 모드가 아닌 경우 모델에 대한 ctrl+c, v
        if(RenameAction.IS_RENAME_MODE){
            actionBars.setGlobalActionHandler(ActionFactory.COPY.getId(), ActionFactory.COPY.create(((ICommonViewerWorkbenchSite) getActionSite().getViewSite()).getWorkbenchWindow()));
            actionBars.setGlobalActionHandler(ActionFactory.PASTE.getId(), ActionFactory.PASTE.create(((ICommonViewerWorkbenchSite) getActionSite().getViewSite()).getWorkbenchWindow()));
        } else {
            actionBars.setGlobalActionHandler(ActionFactory.COPY.getId(), new CopyAction());
            actionBars.setGlobalActionHandler(ActionFactory.PASTE.getId(), new PasteAction());
        }
        
        super.fillActionBars(actionBars);
    }
}
