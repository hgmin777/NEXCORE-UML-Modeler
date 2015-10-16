/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.manager.transaction;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.UMLEditorPlugin;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.transaction.ExceptionHandler;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.manager</li>
 * <li>서브 업무명 : nexcore.tool.uml.manager.transaction</li>
 * <li>설 명 : CommandStackExceptionHandler</li>
 * <li>작성일 : 2009. 12. 8.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class CommandStackExceptionHandler implements ExceptionHandler {

    /**
     * Documentation copied from the inherited specification
     * 
     * @see org.eclipse.emf.transaction.ExceptionHandler#handleException(java.lang.Exception)
     */
    public void handleException(final Exception e) {
        PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
            /**
             * @see java.lang.Runnable#run()
             */
            public void run() {
                Shell shell = null;
                IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();

                if (window != null) {
                    shell = window.getShell();
                }

                if (e instanceof RollbackException) {
                    RollbackException rbe = (RollbackException) e;

                    ErrorDialog.openError(shell,
                        UMLMessage.MESSAGE_COMMAND_FAILED,
                        UMLMessage.MESSAGE_COMMAND_ROLLBACK,
                        rbe.getStatus());
                } else {
                    ErrorDialog.openError(shell,
                        UMLMessage.MESSAGE_COMMAND_FAILED,
                        UMLMessage.MESSAGE_COMMAND_ROLLBACK,
                        new Status(IStatus.ERROR,
                            UMLEditorPlugin.getPlugin().getSymbolicName(),
                            1,
                            e.getLocalizedMessage(),
                            e));
                }
            }
        });
    }

}
