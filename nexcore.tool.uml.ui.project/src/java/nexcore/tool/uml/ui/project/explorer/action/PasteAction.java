/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.project.explorer.action;

import java.util.Collections;

import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.ui.core.registry.ProjectRegistry.ViewerRegistry;
import nexcore.tool.uml.ui.core.util.ProjectUtil;
import nexcore.tool.uml.ui.project.explorer.command.PasteCommand;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.transaction.TransactionalCommandStack;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.IAction;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.action</li>
 * <li>설 명 : PasteAction</li>
 * <li>작성일 : 2009. 12. 18.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
public class PasteAction extends CommonActionDelegate {

    /**
     * 'Ctrl+V' key action.
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        selectionChanged(null, ViewerRegistry.getViewer().getSelection());
        run(null);
    }

    /**
     * @see nexcore.tool.uml.ui.project.explorer.action.CommonActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    @Override
    public void run(IAction action) {
        // PasteCommand command = new PasteCommand(eobject);
        final PasteCommand command = new PasteCommand(selectedEObject);
        // DomainRegistry.getUMLDomain().getCommandStack().execute(command);
//        DomainRegistry.getEditingDomain()
//            .getCommandStack()
//            .execute(new RecordingCommand(DomainRegistry.getEditingDomain()) {
//                @Override
//                protected void doExecute() {
//                    command.execute();
//                }
//            });
        TransactionalEditingDomain editingDomain = DomainRegistry.getEditingDomain();
        TransactionalCommandStack tstack = (TransactionalCommandStack) editingDomain.getCommandStack();
        try {
			tstack.execute(new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {
					command.execute();
				}
			}, Collections.singletonMap(Transaction.OPTION_NO_NOTIFICATIONS,
					Boolean.TRUE));
            	
            // tstack.execute(command,
            // Collections.emptyMap());
        } catch (RollbackException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
			e.printStackTrace();
		}
        
        ProjectUtil.refreshNodeInExplorer(selectedEObject);
    }

}
