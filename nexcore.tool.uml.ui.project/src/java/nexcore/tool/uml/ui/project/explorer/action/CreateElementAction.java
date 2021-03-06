/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.project.explorer.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.ui.core.project.ITreeNode;
import nexcore.tool.uml.ui.project.explorer.command.CreateElementCommand;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.jface.action.IAction;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.action</li>
 * <li>설 명 : CreateElementAction</li>
 * <li>작성일 : 2009. 11. 24.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
public class CreateElementAction extends CommonActionDelegate implements ResourceSetListener {
    /**
	 * 
	 */
    public CreateElementAction() {
        this.filter = NotificationFilter.NOT_TOUCH;
    }

    /**
     * filter
     */
    private final NotificationFilter filter;

    /**
     * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    public void run(IAction action) {

        if (selectedEObject == null)
            return;

        String actionId = action.getId();
        DomainRegistry.getUMLDomain().getTransactionalEditingDomain().addResourceSetListener(this);
        // CreateElementCommand command = new CreateElementCommand(eobject,
        // actionId);
        final CreateElementCommand command = new CreateElementCommand(selectedEObject, actionId);
        // DomainRegistry.getUMLDomain().getCommandStack().execute(command);
        DomainRegistry.getEditingDomain()
            .getCommandStack()
            .execute(new RecordingCommand(DomainRegistry.getEditingDomain()) {
                @Override
                protected void doExecute() {
                    command.execute();
                }
            });
    }

    /**
     * @see org.eclipse.emf.transaction.ResourceSetListener#resourceSetChanged(org.eclipse.emf.transaction.ResourceSetChangeEvent)
     */
    public void resourceSetChanged(ResourceSetChangeEvent event) {
        List<Notification> list = event.getNotifications();
        Notification notification = null;
        EObject eobject = null;
        Object newValue = null;

        List<EObject> targets = new ArrayList<EObject>();
        ITreeNode node = null;
        DomainRegistry.getUMLDomain().getTransactionalEditingDomain().removeResourceSetListener(this);
        int type = -1;
        for (Iterator<Notification> it = list.iterator(); it.hasNext();) {
            newValue = null;
            notification = it.next();
            if (notification.isTouch()) {
                continue;
            }
            type = notification.getEventType();

            if (notification.getNotifier() instanceof EObject) {
                eobject = (EObject) notification.getNotifier();

                switch (type) {

                    case Notification.ADD:
                        if (eobject instanceof EAnnotation) {
                            eobject = UMLManager.getParent((EAnnotation) eobject);
                        }

                        newValue = notification.getNewValue();
                        if (!(newValue instanceof EAnnotation)) {

                            RenameAction renameAction = new RenameAction((EObject) newValue);
                            renameAction.run(null);

                        }

                        break;
                    default:
                        break;
                }
            }

        }

    }

    /**
     * @see org.eclipse.emf.transaction.ResourceSetListener#getFilter()
     */
    public NotificationFilter getFilter() {
        return filter;
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
     * @see org.eclipse.emf.transaction.ResourceSetListener#transactionAboutToCommit(org.eclipse.emf.transaction.ResourceSetChangeEvent)
     */
    public Command transactionAboutToCommit(ResourceSetChangeEvent event) throws RollbackException {
        // TODO Auto-generated method stub
        return null;
    }

}
