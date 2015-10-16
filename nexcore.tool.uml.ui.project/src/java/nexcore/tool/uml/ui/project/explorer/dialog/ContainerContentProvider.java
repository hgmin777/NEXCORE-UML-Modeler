/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.project.explorer.dialog;

import java.util.ArrayList;
import java.util.List;

import nexcore.tool.uml.ui.core.UICoreConstant;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * Provides content for a tree viewer that shows only containers.
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.dialog</li>
 * <li>설  명 : ContainerContentProvider</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class ContainerContentProvider implements ITreeContentProvider {

    /**
     * showClosedProjects
     */
    private boolean showClosedProjects = true;

    /**
     * dialogType
     */
    private int dialogType;

    /**
     * Creates a new ContainerContentProvider.
     */
    public ContainerContentProvider() {
    }

    /**
     * ContainerContentProvider
     * @param type
     */
    public ContainerContentProvider(int type) {
        dialogType = type;
    }

    /**
     * The visual part that is using this content provider is about to be
     * disposed. Deallocate all allocated SWT resources.
     */
    public void dispose() {
    }

    /*
     * @see
     * org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.
     * Object)
     */
    /**
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
     */
    public Object[] getChildren(Object element) {
        if (element instanceof IWorkspace) {
            // check if closed projects should be shown
            IProject[] allProjects = ((IWorkspace) element).getRoot().getProjects();
            if (showClosedProjects) {
                return allProjects;
            }

            ArrayList accessibleProjects = new ArrayList();
            for (int i = 0; i < allProjects.length; i++) {
                try {
                    if (allProjects[i].isOpen()
                        && allProjects[i].hasNature(UICoreConstant.PROJECT_CONSTANTS__NATURE_ID)) {
                        accessibleProjects.add(allProjects[i]);
                    }
                } catch (CoreException e) {
                    e.printStackTrace();
                }
            }
            return accessibleProjects.toArray();
        } else if (element instanceof IContainer) {
            IContainer container = (IContainer) element;
            if (container.isAccessible()) {
                try {
                    List children = new ArrayList();
                    IResource[] members = container.members();
                    if (dialogType == FragmentSaveDialog.FRAGMENT) {
                        // if
                        // (UMLMessage.LABEL_MODEL.equals(container.getName()))
                        // {
                        for (int i = 0; i < members.length; i++) {
                            if (!children.contains(members[i]) && !members[i].getName().endsWith(".settings")
                                && !members[i].getName().endsWith(".project")) { //$NON-NLS-1$
                                children.add(members[i]);
                            }
                        }
                        // }
                    }

                    for (int i = 0; i < members.length; i++) {
                        int resourceType = members[i].getType();

                        if (members[i].getName().startsWith(UICoreConstant.PROJECT_CONSTANTS__DOT)
                            && resourceType == IResource.FOLDER && dialogType == FragmentSaveDialog.MODEL_FRAGMENT) {
                            // do not thing
                        } else if (members[i].getType() != IResource.FILE) {
                            if (!members[i].getName().startsWith(UICoreConstant.PROJECT_CONSTANTS__DOT)
                                && !children.contains(members[i])) {
                                children.add(members[i]);
                            }
                        }
                    }
                    return children.toArray();
                } catch (CoreException e) {
                    // this should never happen because we call #isAccessible
                    // before invoking #members
                }
            }
        }
        return new Object[0];
    }

    /*
     * @see
     * org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java
     * .lang.Object)
     */
    /**
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getElements(java.lang.Object)
     */
    public Object[] getElements(Object element) {
        return getChildren(element);
    }

    /*
     * @see
     * org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object
     * )
     */
    /**
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
     */
    public Object getParent(Object element) {
        if (element instanceof IResource) {
            return ((IResource) element).getParent();
        }
        return null;
    }

    /*
     * @see
     * org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.
     * Object)
     */
    /**
     * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
     */
    public boolean hasChildren(Object element) {
        return getChildren(element).length > 0;
    }

    /*
     * @see org.eclipse.jface.viewers.IContentProvider#inputChanged
     */
    /**
     * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
     */
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    }

    /**
     * Specify whether or not to show closed projects in the tree viewer.
     * Default is to show closed projects.
     * 
     * @param show
     *            boolean if false, do not show closed projects in the tree
     */
    public void showClosedProjects(boolean show) {
        showClosedProjects = show;
    }

}
