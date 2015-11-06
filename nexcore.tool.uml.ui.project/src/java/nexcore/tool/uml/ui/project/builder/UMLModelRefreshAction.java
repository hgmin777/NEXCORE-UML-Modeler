/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.project.builder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import nexcore.tool.uml.ui.core.project.ITreeNode;
import nexcore.tool.uml.ui.core.project.UMLFileTreeNode;
import nexcore.tool.uml.ui.core.registry.ProjectRegistry;
import nexcore.tool.uml.ui.core.registry.ProjectRegistry.ViewerRegistry;
import nexcore.tool.uml.ui.core.registry.ResourceManager;
import nexcore.tool.uml.ui.core.util.ProjectUtil;
import nexcore.tool.uml.ui.core.util.TreeItemUtil;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.PackageableElement;

/**
 * 
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.builder</li>
 * <li>설 명 : UMLModelRefreshAction</li>
 * <li>작성일 : 2011. 5. 17.</li>
 * <li>작성자 : pns</li>
 * </ul>
 */
public class UMLModelRefreshAction implements IObjectActionDelegate {

    /** 선택 객체 */
    private ISelection selection;


    /**
     * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    @SuppressWarnings("unchecked")
    public void run(IAction action) {
        if ((null == selection) || (!(selection instanceof IStructuredSelection))) {
            return;
        }
        
        CommonViewer commonViewer = ViewerRegistry.getViewer();
        TreePath[] expanedTreePaths = TreeItemUtil.getExpandTreePaths(commonViewer.getTree()).clone();

        List<IProject> projectList = new ArrayList<IProject>();
        
        for (Iterator iterator = ((IStructuredSelection) selection).iterator(); iterator.hasNext();) {
            Object obj = (Object) iterator.next();
            IProject project = ProjectUtil.findProject(obj);
            if (ProjectUtil.isActiveUMLProject(project) && !projectList.contains(project)) {
                projectList.add(project);
            }
        }

        Map<String, ITreeNode> treeNodes = ProjectRegistry.UMLTreeNodeRegistry.getTreeNodes();
        List<Object> removeTarget = new ArrayList<Object>();
        for (Iterator<String> iterator = treeNodes.keySet().iterator(); iterator.hasNext();) {
            String type = (String) iterator.next();
            for (IResource resource : projectList) {
                URI uri = URI.createURI(resource.getFullPath().toString());
                if (type.indexOf(uri.toString()) > -1) {
                    removeTarget.add(type);
                }
            }
        }
        // UMLTreeNodeRegistry.getTreeNodes() 에서 관련된 키 삭제
        for (Object o : removeTarget) {
            ProjectRegistry.UMLTreeNodeRegistry.removeTreeNode(o);
        }
        
        for (IProject project : projectList) {
            commonViewer.refresh(project);
        }
        
        // 이전 상태의 트리 노드 펼침 상태로 복원.
        TreeItemUtil.expandTreePath(expanedTreePaths, selection);
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
     * clearlist
     *  
     * @return List<IResource>
     */
    public List<IResource> clearlist() {
        List<IResource> resourceList = new ArrayList<IResource>();

        IStructuredSelection select = (IStructuredSelection) selection;
        Object obj;
        for (Iterator iterator = select.iterator(); iterator.hasNext();) {
            obj = (Object) iterator.next();
            if (obj instanceof UMLFileTreeNode) {
                UMLFileTreeNode node = (UMLFileTreeNode) obj;

                IResource resource = node.getResource();
                resourceList.add(resource);
                // 단편화된 모델일 경우 단편화 파일까지 삭제 대상에 추가
                childFragment(resourceList, node.getEObject());

            } else if (obj instanceof ITreeNode) {
                ITreeNode node = (ITreeNode) obj;
                childFragment(resourceList, node.getEObject());
            }
            // StructuredSelection ss = new
            // StructuredSelection(resourceList.toArray());
        }

        return resourceList;
    }

    /**
     * childFragment
     *  
     * @param resourceList
     * @param eObject void
     */
    public void childFragment(List<IResource> resourceList, EObject eObject) {

        if (eObject instanceof Model) {
            EList<PackageableElement> packageableElements = ((Model) eObject).getPackagedElements();
            for (PackageableElement packageableElement : packageableElements) {
                if (packageableElement != null && packageableElement.eResource() != null) {
                    IFile fragmentResource = null;
                    try {
                        fragmentResource = WorkspaceSynchronizer.getFile(packageableElement.eResource());
                        if (fragmentResource != null && fragmentResource.exists()) {
                            if (!resourceList.contains(fragmentResource)) {
                                resourceList.add(fragmentResource);
                            }
                            childFragment(resourceList, packageableElement);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } else if (eObject instanceof Package) {
            if (eObject != null && eObject.eResource() != null) {
                IFile fragmentResource = null;
                try {
                    fragmentResource = WorkspaceSynchronizer.getFile(eObject.eResource());
                    if (fragmentResource != null && fragmentResource.exists()) {
                        if (!resourceList.contains(fragmentResource)) {
                            // resourceList.add(fragmentResource);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        EAnnotation fragmentAnnotation = ResourceManager.getInstance().getFragmentAnnotation(eObject);
        if (fragmentAnnotation == null)
            return;
        EList<EObject> references = fragmentAnnotation.getReferences();
        for (EObject reference : references) {
            if (reference != null && reference.eResource() != null) {
                IFile fragmentResource = null;
                try {
                    fragmentResource = WorkspaceSynchronizer.getFile(reference.eResource());
                    if (fragmentResource != null && fragmentResource.exists()) {
                        if (!resourceList.contains(fragmentResource)) {
                            resourceList.add(fragmentResource);
                        }

                        childFragment(resourceList, reference);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
