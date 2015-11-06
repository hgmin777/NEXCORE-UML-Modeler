/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.core.project.explorer.provider;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionConstants;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonViewerSite;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.project.explorer.provider</li>
 * <li>설  명 : BaseUMLTreeActionProvider</li>
 * <li>작성일 : 2009. 12. 7.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
public class BaseUMLTreeActionProvider extends CommonActionProvider {


    /**
     * openAction
     */
    protected OpenAction openAction = null;

    /**
     * @see org.eclipse.ui.navigator.CommonActionProvider#init(org.eclipse.ui.navigator.ICommonActionExtensionSite)
     */
    public void init(ICommonActionExtensionSite aSite) {
        super.init(aSite);

        ICommonViewerSite viewSite = aSite.getViewSite();
        if (viewSite instanceof ICommonViewerWorkbenchSite) {
            ICommonViewerWorkbenchSite workbenchSite = (ICommonViewerWorkbenchSite) viewSite;

            openAction = new OpenAction(workbenchSite.getSelectionProvider().getSelection());
        }
    }

    /**
     * @see org.eclipse.ui.actions.ActionGroup#fillActionBars(org.eclipse.ui.IActionBars)
     */
    @Override
    public void fillActionBars(IActionBars actionBars) {

        if (openAction.isEnabled()) {
            actionBars.setGlobalActionHandler(ICommonActionConstants.OPEN, openAction);
        }
    }
    
    /**
     * @see org.eclipse.ui.actions.ActionGroup#updateActionBars()
     */
    @Override
    public void updateActionBars() {
        IStructuredSelection selection = (IStructuredSelection) getContext().getSelection();
        openAction.selectionChanged(selection);
    }
}
