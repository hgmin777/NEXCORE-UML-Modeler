/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.project.builder;

import java.util.Iterator;

import nexcore.tool.uml.ui.core.util.ProjectUtil;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.builder</li>
 * <li>설  명 : ToggleNatureAction</li>
 * <li>작성일 : 2009. 11. 30.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class ToggleNatureAction implements IObjectActionDelegate {

    /** 선택 객체 */
    private ISelection selection;

    /**
     * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    @SuppressWarnings("unchecked")
    public void run(IAction action) {
        if (selection instanceof IStructuredSelection) {
            for (Iterator it = ((IStructuredSelection) selection).iterator(); it.hasNext();) {
                Object element = it.next();
                IProject project = null;
                if (element instanceof IProject) {
                    project = (IProject) element;
                } else if (element instanceof IAdaptable) {
                    project = (IProject) ((IAdaptable) element).getAdapter(IProject.class);
                }
                if (project != null) {
                    toggleNature(project);
                }
            }
        }
    }

    /**
     * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
     *      org.eclipse.jface.viewers.ISelection)
     */
    public void selectionChanged(IAction action, ISelection selection) {
        this.selection = selection;
    }

    /**
     * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction,
     *      org.eclipse.ui.IWorkbenchPart)
     */
    public void setActivePart(IAction action, IWorkbenchPart targetPart) {
    }

    /**
     * Toggles sample nature on a project
     * 
     * @param project
     *            to have sample nature added or removed
     */
    private void toggleNature(IProject project) {
        try {
            IProjectDescription description = project.getDescription();
            String[] natures = description.getNatureIds();

            for (int i = 0; i < natures.length; ++i) {
                if (UMLNature.NATURE_ID.equals(natures[i])) {
                    // Remove the nature
                    String[] newNatures = new String[natures.length - 1];
                    System.arraycopy(natures, 0, newNatures, 0, i);
                    System.arraycopy(natures, i + 1, newNatures, i, natures.length - i - 1);
                    description.setNatureIds(newNatures);
                    project.setDescription(description, null);
                    
                    ProjectUtil.closeEditor(project);
                    
                    return;
                }
            }

            // Add the nature
            String[] newNatures = new String[natures.length + 1];
            System.arraycopy(natures, 0, newNatures, 0, natures.length);
            newNatures[natures.length] = UMLNature.NATURE_ID;
            description.setNatureIds(newNatures);
            project.setDescription(description, null);
        } catch (CoreException e) {}
    }

}
