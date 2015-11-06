/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.manager.transaction;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.manager</li>
 * <li>서브 업무명 : nexcore.tool.uml.manager.transaction</li>
 * <li>설 명 : UMLResourceSetListener</li>
 * <li>작성일 : 2009. 12. 8.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class UMLResourceSetListener implements ResourceSetListener {

    /**
     * @see org.eclipse.emf.transaction.ResourceSetListener#getFilter()
     */
    public NotificationFilter getFilter() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @see org.eclipse.emf.transaction.ResourceSetListener#isAggregatePrecommitListener()
     */
    public boolean isAggregatePrecommitListener() {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * @see org.eclipse.emf.transaction.ResourceSetListener#isPostcommitOnly()
     */
    public boolean isPostcommitOnly() {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * @see org.eclipse.emf.transaction.ResourceSetListener#isPrecommitOnly()
     */
    public boolean isPrecommitOnly() {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * @see org.eclipse.emf.transaction.ResourceSetListener#resourceSetChanged(org.eclipse.emf.transaction.ResourceSetChangeEvent)
     */
    public void resourceSetChanged(ResourceSetChangeEvent event) {
        //        System.out.println("A change has been made with "+event.getNotifications().size()+" notifications produced."); //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * @see org.eclipse.emf.transaction.ResourceSetListener#transactionAboutToCommit(org.eclipse.emf.transaction.ResourceSetChangeEvent)
     */
    @SuppressWarnings("unchecked")
    public Command transactionAboutToCommit(ResourceSetChangeEvent event) throws RollbackException {

        List notifications = event.getNotifications();
        CompoundCommand compoundCommand = new CompoundCommand();
        Set handledModels = new HashSet();
        Notification notification = null;
        EObject notifier = null;

        for (Iterator i = notifications.iterator(); i.hasNext();) {
            notification = (Notification) i.next();

            if (notification.getNotifier() instanceof EObject && !handledModels.contains(notification)) {
                notifier = (EObject) notification.getNotifier();
                if (notifier.eClass() == UMLPackage.eINSTANCE.getModel()) {
                    final Model model = (Model) notifier;
                    String name = model.getName();

                    // Model should have some name
                    if (name == null || name.equals("")) { //$NON-NLS-1$
                        // We can use any EMF command here
                        compoundCommand.append(new SetCommand(event.getEditingDomain(),
                            model,
                            (EStructuralFeature) UMLPackage.eINSTANCE.getModel(),
                            "SomeName")); //$NON-NLS-1$
                    }

                    handledModels.add(model);
                }
            }
        }

        // It is important to return null if we have nothing to
        // contribute to this transaction.
        return compoundCommand.isEmpty() ? null : compoundCommand;

    }

}
