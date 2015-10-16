/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.alm.common.ui.action;

import nexcore.alm.common.ui.internal.action.RefreshSessionManager;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

/**
 * 
 * <ul>
 * <li>업무 그룹명 : nexcore.alm.common.ui</li>
 * <li>서브 업무명 : nexcore.alm.common.ui.action</li>
 * <li>설 명 : AbstractSessionRefreshAction</li>
 * <li>작성일 : 2012. 4. 3.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public abstract class AbstractRefreshSessionAction implements IWorkbenchWindowActionDelegate, ISessionListener {

    protected IWorkbenchWindow window = null;

    protected ISelection selection = null;

    public AbstractRefreshSessionAction() {
        addRefreshListener(this);
    }

    public void addRefreshListener(ISessionListener listener) {
        RefreshSessionManager.getInstance().addRefreshListener(listener);
    }

    public void removeRefreshListener(ISessionListener listener) {
        RefreshSessionManager.getInstance().removeRefreshListener(listener);
    }

    @Override
    public final void run(IAction action) {
        RefreshSessionManager.getInstance().fireExecute();
    }

    @Override
    public void selectionChanged(IAction action, ISelection selection) {
        this.selection = selection;
    }

    @Override
    public void dispose() {
        removeRefreshListener(this);
    }

    @Override
    public void init(IWorkbenchWindow window) {
        this.window = window;
    }

    public abstract void execute();
}
