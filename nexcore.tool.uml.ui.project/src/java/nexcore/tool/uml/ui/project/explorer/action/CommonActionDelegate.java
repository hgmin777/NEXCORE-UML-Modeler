/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.project.explorer.action;

import nexcore.tool.uml.ui.core.project.ITreeNode;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.action</li>
 * <li>설 명 : CommonActionDelegate</li>
 * <li>작성일 : 2009. 12. 3.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
public abstract class CommonActionDelegate extends Action implements IObjectActionDelegate {

    /** Selected EObject */
    protected EObject selectedEObject;

    /**
     * selection
     */
    protected ISelection selection = null;
    
    /**
     * targetPart
     */
    protected IWorkbenchPart targetPart = null;

    /**
     * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction, org.eclipse.ui.IWorkbenchPart)
     */
    public void setActivePart(IAction action, IWorkbenchPart targetPart) {
        this.targetPart = targetPart;
    }

    /**
     * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    abstract public void run(IAction action);

    /**
     * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
     *      org.eclipse.jface.viewers.ISelection)
     */
    public void selectionChanged(IAction action, ISelection selection) {
        if (!(selection instanceof IStructuredSelection))
            return;

        this.selection = selection;

        Object object = ((IStructuredSelection) selection).getFirstElement();
        if (object instanceof ITreeNode) {
            ITreeNode node = (ITreeNode) object;
            selectedEObject = node.getEObject();
            
//            System.out.println(EcoreUtil.getURI(selectedEObject).fragment());
        }
    }

}
