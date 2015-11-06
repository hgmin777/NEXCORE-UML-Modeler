/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.core.dialog.provider;

import java.util.ArrayList;
import java.util.List;

import nexcore.tool.mdd.core.extension.IDomainModelHandler;
import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.dialog.ProjectItemSelectionDialog.ContainerElement;
import nexcore.tool.uml.ui.core.util.ProjectUtil;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.dialog.provider</li>
 * <li>설 명 : ProjectItemTreeContentProvider</li>
 * <li>작성일 : 2010. 3. 19.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class ProjectItemTreeContentProvider implements ITreeContentProvider {

    /**
     * objectType
     */
    private int objectType = -1;

    /**
     * projectObject
     */
    private int projectObject = 0;

    /**
     * modelObject
     */
    private int modelObject = 1;

    /**
     * object
     */
    private int object = 2;

    /**
     * ProjectItemTreeContentProvider
     * @param type
     */
    public ProjectItemTreeContentProvider(int type) {
        objectType = type;
    }

    /**
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
     */
    public Object[] getChildren(Object parentElement) {
        if (objectType == modelObject) {
            return getChildrenForModelObject(parentElement);
        } else {
            return getChildrenForDefaultObject(parentElement);
        }
    }

    /**
     * getChildrenForModelObject
     *  
     * @param parentElement
     * @return Object[]
     */
    private Object[] getChildrenForModelObject(Object parentElement) {
        if (parentElement instanceof IWorkspaceRoot) {
            return ((IWorkspaceRoot) parentElement).getProjects();
        } else if (parentElement instanceof IProject) {
            try {
                List<IResource> resources = new ArrayList<IResource>();
                for (IResource res : ((IProject) parentElement).members()) {
                    resources.add(res);
                }
                List<IResource> removeResource = new ArrayList<IResource>();
                for (IResource res : resources) {
                    if (res instanceof IFolder) {
                        if (!hasChildren(res)) {
                            removeResource.add(res);
                        }
                    } else {
                        removeResource.add(res);
                    }
                }
                for (IResource res : removeResource) {
                    resources.remove(res);
                }

                return resources.toArray();
            } catch (CoreException e) {
                return null;
            }
        } else if (parentElement instanceof IFolder) {
            try {
                IFolder folder = (IFolder) parentElement;
                if (folder.getProject().hasNature(UICoreConstant.PROJECT_CONSTANTS__NATURE_ID)) {
                    return getModelNodes(folder);
                }
            } catch (CoreException e) {
                Log.error(e);
            }
        }

        return null;
    }

    /**
     * getChildrenForDefaultObject
     *  
     * @param parentElement
     * @return Object[]
     */
    private Object[] getChildrenForDefaultObject(Object parentElement) {
        // 부모 요소가 ContainerElement인 경우 그 자식은
        // 탐색기의 최상위인 Model 이 자식에 설정되어 주기 때문에
        // ContainerElement는 다른 Element들처럼 자식들을 반환하는
        // 메소드의 구현이 필요
        if (parentElement instanceof ContainerElement) {
            return ((ContainerElement) parentElement).getChildren().toArray();
        } else if (parentElement instanceof Element) {
            return ((Element) parentElement).getOwnedElements().toArray();
        } else if (parentElement instanceof IWorkspaceRoot) {
            return ((IWorkspaceRoot) parentElement).getProjects();
        } else if (parentElement instanceof IProject) {
            try {
                return ((IProject) parentElement).members();
            } catch (CoreException e) {
                return null;
            }
        } else if (parentElement instanceof IFolder) {
            try {
                IFolder folder = (IFolder) parentElement;
                if (folder.getProject().hasNature(UICoreConstant.PROJECT_CONSTANTS__NATURE_ID)) {
                    return getModelNodes(folder);
                }
            } catch (CoreException e) {
                Log.error(e);
            }

        }

        return null;
    }

    /**
     * getModelNodes
     *  
     * @param folder
     * @return
     * @throws CoreException Object[]
     */
    private Object[] getModelNodes(IFolder folder) throws CoreException {

        List<IFile> list = ProjectUtil.getModelFiles(folder);
        List<Object> result = new ArrayList<Object>();

        IFile file;
        URI uri;
        Resource resource;

        // UMLDomain umlDomain = DomainRegistry.getUMLDomain();
        IDomainModelHandler umlDomain = DomainRegistry.getUMLDomain();
        for (int i = 0; i < list.size(); i++) {
            file = list.get(i);
            uri = URI.createURI(file.getFullPath().toString());
            resource = umlDomain.getResourceSet().getResource(uri, true);
            for (EObject obj : resource.getContents()) {
                if (obj instanceof Model) {
                    result.add(obj);
                    continue;
                }
            }
        }
        return result.toArray();
    }

    /**
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
     */
    public Object getParent(Object element) {
        if (element instanceof Element) {
            return ((Element) element).getOwner();
        }

        return null;
    }

    /**
     * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
     */
    public boolean hasChildren(Object element) {
        if (objectType == modelObject) {
            return hasChildrenForModelObject(element);
        } else if (objectType == projectObject) {
            return hasChildrenForProjectObject(element);
        } else {
            return hasChildrenForDefaultObject(element);
        }

    }

    /**
     * hasChildrenForProjectObject
     *  
     * @param element
     * @return boolean
     */
    private boolean hasChildrenForProjectObject(Object element) {
        return false;
    }

    /**
     * hasChildrenForDefaultObject
     *  
     * @param element
     * @return boolean
     */
    private boolean hasChildrenForDefaultObject(Object element) {
        // 부모 요소가 ContainerElement인 경우 탐색기의
        // 최상위인 Model이 자식으로 으로 들어가 있으므로
        // 고정되어 있으므로 true로 설정
        if (element instanceof ContainerElement) {
            return true;
        } else if (element instanceof Element) {
            return ((Element) element).getOwnedElements().size() > 0 ? true : false;
        } else if (element instanceof IProject) {
            try {
                return ((IProject) element).members().length > 0 ? true : false;
            } catch (CoreException e) {
                // TODO Auto-generated catch block
                return false;
            }
        } else if (element instanceof IFolder) {
            try {
                if (((IFolder) element).members().length > 0) {
                    return true;
                }
            } catch (CoreException e) {
                Log.error(e);
            }

        }

        return false;
    }

    /**
     * hasChildrenForModelObject
     *  
     * @param element
     * @return boolean
     */
    private boolean hasChildrenForModelObject(Object element) {
        if (element instanceof IProject) {
            try {
                for (IResource resource : ((IProject) element).members()) {
                    if (resource instanceof IFolder) {
                        if (hasChildrenForModelObject(resource)) {
                            return true;
                        }
                    }
                }
                return false;
            } catch (CoreException e) {
                // TODO Auto-generated catch block
                return false;
            }
        } else if (element instanceof IFolder) {
            IFolder folder = (IFolder) element;
            try {
                for (IResource resource : folder.members()) {
                    if (getModelNodes(folder).length > 0) {
                        return true;
                    } else if (resource instanceof IFolder) {
                        return hasChildrenForModelObject(resource);
                    }
                }
                return false;
            } catch (CoreException e) {
                Log.error(e);
            }

        }

        return false;
    }

    /**
     * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
     */
    public Object[] getElements(Object inputElement) {
        Object[] children = getChildren(inputElement);
        return children;
    }

    /**
     * @see org.eclipse.jface.viewers.IContentProvider#dispose()
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
