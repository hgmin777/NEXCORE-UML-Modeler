/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.manager.transaction;

import java.util.Collection;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.transaction.TransactionalCommandStack;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain.DefaultOptions;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.emf.workspace.WorkspaceEditingDomainFactory;
import org.eclipse.emf.workspace.util.WorkspaceValidateEditSupport;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.manager</li>
 * <li>서브 업무명 : nexcore.tool.uml.manager.transaction</li>
 * <li>설 명 : UMLEditingDomainFactory</li>
 * <li>작성일 : 2009. 12. 8.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class UMLEditingDomainFactory implements TransactionalEditingDomain.Factory {

    /**
     * @see org.eclipse.emf.transaction.TransactionalEditingDomain.Factory#createEditingDomain()
     */
    public TransactionalEditingDomain createEditingDomain() {
        // create an editing domain with a default resource set implementation
        // and delegating command execution to the default (workbench)
        // operation history
        TransactionalEditingDomain result = WorkspaceEditingDomainFactory.INSTANCE.createEditingDomain();

        // add an exception handler to the editing domain's command stack
        ((TransactionalCommandStack) result.getCommandStack()).setExceptionHandler(new CommandStackExceptionHandler());

        DefaultOptions defaults = TransactionUtil.getAdapter(result, DefaultOptions.class);
        if (defaults != null) {
            Map<Object, Object> options = new java.util.HashMap<Object, Object>();

            options.put(Transaction.OPTION_VALIDATE_EDIT, new WorkspaceValidateEditSupport() {
                @Override
                protected IStatus doValidateEdit(Transaction transaction, Collection<? extends Resource> resources,
                                                 Object context) {
                    if ((context == null) && (Display.getCurrent() != null)) {
                        // get the active shell for the context
                        // when validating on the UI thread
                        IWorkbench wb = PlatformUI.getWorkbench();
                        if (wb.getActiveWorkbenchWindow() != null) {
                            context = wb.getActiveWorkbenchWindow().getShell();
                        }
                    }

                    return super.doValidateEdit(transaction, resources, context);
                }
            });

            defaults.setDefaultTransactionOptions(options);
        }

        return result;
    }

    /**
     * @see org.eclipse.emf.transaction.TransactionalEditingDomain.Factory#createEditingDomain(org.eclipse.emf.ecore.resource.ResourceSet)
     */
    public TransactionalEditingDomain createEditingDomain(ResourceSet rset) {
        // not used when initializing editing domain from extension point
        return null;
    }

    /**
     * @see org.eclipse.emf.transaction.TransactionalEditingDomain.Factory#getEditingDomain(org.eclipse.emf.ecore.resource.ResourceSet)
     */
    public TransactionalEditingDomain getEditingDomain(ResourceSet rset) {
        // not used when initializing editing domain from extension point
        return null;
    }

}
