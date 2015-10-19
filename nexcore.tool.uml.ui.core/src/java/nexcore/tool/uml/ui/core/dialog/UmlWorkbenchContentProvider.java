/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.core.dialog;

import java.util.ArrayList;
import java.util.List;

import nexcore.tool.uml.core.log.Log;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.uml2.uml.Element;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.dialog</li>
 * <li>설  명 : UmlWorkbenchContentProvider</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class UmlWorkbenchContentProvider extends WorkbenchContentProvider implements ITreeContentProvider {

    /**
     * @see org.eclipse.ui.model.BaseWorkbenchContentProvider#getParent(java.lang.Object)
     */
    public Object getParent(Object element) {
        if (element instanceof Element) {
            return ((Element) element).getOwner();
        }

        return null;
    }

    /**
     * @see org.eclipse.ui.model.BaseWorkbenchContentProvider#hasChildren(java.lang.Object)
     */
    public boolean hasChildren(Object element) {
        if (element instanceof IProject) {
            try {
                return ((IProject) element).members().length > 0 ? true : false;
            } catch (CoreException e) {
                // TODO Auto-generated catch block
                return false;
            }
        } else if (element instanceof IFolder) {
            try {
                if (((IFolder) element).members().length > 0) {
                    return hasTemplateFile((IFolder) element);
                }
            } catch (CoreException e) {
                Log.error(e);
            }

        }

        return false;
    }

    /**
     * hasTemplateFile
     *  
     * @param element
     * @return boolean
     */
    private boolean hasTemplateFile(IFolder element) {
        try {
            for (Object child : element.members()) {
                if (child instanceof IFile) {
                    if ("doc".equals(((IFile) child).getFileExtension())
                        || "xls".equals(((IFile) child).getFileExtension())) {
                        return true;
                    }
                } else if (child instanceof IFolder) {
                    return hasTemplateFile((IFolder) child);
                } else {
                    return false;
                }
            }
        } catch (CoreException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @see org.eclipse.ui.model.BaseWorkbenchContentProvider#getChildren(java.lang.Object)
     */
    @Override
    public Object[] getChildren(Object element) {
        Object[] children = super.getChildren(element);
        List<Object> childList = new ArrayList<Object>();
        List<Object> removeList = new ArrayList<Object>();
        for (Object obj : children) {
            childList.add(obj);
        }
        for (Object obj : children) {
            if (obj instanceof IProject) {

                try {
                    IProject iProject = (IProject) obj;
                    if (!iProject.isOpen()) {
                        removeList.add(obj);
                    }

                    String[] natureIDs = iProject.getDescription().getNatureIds();
                    boolean hasUMLNature = false;
                    for (String natureID : natureIDs) {
                        if (natureID.equals("nexcore.tool.uml.ui.modelerNature")) {
                            hasUMLNature = true;
                        }
                    }
                    if (!hasUMLNature) {
                        if (!removeList.contains(obj)) {
                            removeList.add(obj);
                        }
                    }

                } catch (Exception e) {}

            } else if (obj instanceof IFolder) {
                if (!hasTemplateFile((IFolder) obj)) {
                    removeList.add(obj);
                }

            } else if (obj instanceof IFile) {
                if (!"doc".equals(((IFile) obj).getFileExtension()) && !"xls".equals(((IFile) obj).getFileExtension())) {
                    removeList.add(obj);
                }
            }
        }
        for (Object obj : removeList) {
            childList.remove(obj);
        }
        return childList.toArray();
    }

    /**
     * @see org.eclipse.ui.model.BaseWorkbenchContentProvider#getElements(java.lang.Object)
     */
    @Override
    public Object[] getElements(Object inputElement) {
        Object[] children = getChildren(inputElement);
        return children;
    }

    /**
     * @see org.eclipse.ui.model.WorkbenchContentProvider#dispose()
     */
    public void dispose() {
    }

    /**
     * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
     *      java.lang.Object, java.lang.Object)
     */
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    }

}
