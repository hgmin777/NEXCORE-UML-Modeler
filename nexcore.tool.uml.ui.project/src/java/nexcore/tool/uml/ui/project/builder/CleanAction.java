/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
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
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.ISaveablePart2;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.internal.WorkbenchMessages;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.PackageableElement;

/**
 * 
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.builder</li>
 * <li>설 명 : CleanAction</li>
 * <li>작성일 : 2011. 5. 17.</li>
 * <li>작성자 : pns</li>
 * </ul>
 */
public class CleanAction implements IObjectActionDelegate {

    /** 선택 객체 */
    private ISelection selection;

    /**
     * findProject
     *  
     * @param obj
     * @return Object
     */
    private Object findProject(Object obj) {
        Object ret = null;
        if (obj instanceof ITreeNode) {
            ITreeNode node = (ITreeNode) obj;
            return findProject(node.getParentNode());
        } else if (obj instanceof IProject) {
            return (IProject) obj;
        } else if (obj instanceof IFolder) {
            return ((IFolder) obj).getProject();
        } else if (obj instanceof IFile) {
            return ((IFile) obj).getProject();
        }

        return ret;
    }

    /**
     * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    @SuppressWarnings("unchecked")
    public void run(final IAction action) {
        if ((null == selection) || (!(selection instanceof IStructuredSelection))) {
            return;
        }

        final List<IProject> projectList = new ArrayList<IProject>();

        for (Iterator iterator = ((IStructuredSelection) selection).iterator(); iterator.hasNext();) {
            Object obj = (Object) iterator.next();
            IProject project = ProjectUtil.findProject(obj);
            if (ProjectUtil.isActiveUMLProject(project) && !projectList.contains(project)) {
                projectList.add(project);
            }
        }
        
        Display.getDefault().syncExec(new Runnable() {
            public void run() {
                
                CommonViewer commonViewer = ViewerRegistry.getViewer();
                TreePath[] expanedTreePaths = TreeItemUtil.getExpandTreePaths(commonViewer.getTree()).clone();
                
                List<Resource> saveableList = ResourceManager.hasModifiedResource(projectList.toArray(new IResource[] {}));
                boolean isSave = false;
                int choice = IDialogConstants.NO_ID;
                if (saveableList.size() > 0) {
                    final boolean canCancel = true;
                    String[] buttons;
                    buttons = new String[] { IDialogConstants.YES_LABEL, IDialogConstants.NO_LABEL,
                        IDialogConstants.CANCEL_LABEL };

                    String message = NLS.bind(WorkbenchMessages.EditorManager_saveChangesQuestion, saveableList.get(0)
                        .getURI());
                    MessageDialog dialog = new MessageDialog(ViewerRegistry.getViewer().getTree().getShell(),
                        WorkbenchMessages.Save_Resource,
                        null,
                        message,
                        MessageDialog.QUESTION,
                        buttons,
                        0) {
                        protected int getShellStyle() {
                            return (canCancel ? SWT.CLOSE : SWT.NONE) | SWT.TITLE | SWT.BORDER | SWT.APPLICATION_MODAL
                                | getDefaultOrientation();
                        }
                    };

                    choice = dialog.open();

                    switch (choice) {
                        case ISaveablePart2.YES:
                            isSave = true;
                            break;
                        case ISaveablePart2.NO:
                            isSave = false;
                            break;
                        case ISaveablePart2.CANCEL:
                            // 
                            return;
                        default:
                            break;
                    }
                } 
                
                ResourceManager.cleanResource(new NullProgressMonitor(), projectList.toArray(new IResource[] {}), isSave);
                cleanTreeNode(projectList);
                
                TreeItemUtil.expandTreePath(expanedTreePaths, selection);
            }

            private void cleanTreeNode(final List<IProject> projectList) {
                Map<String, ITreeNode> treeNodes = ProjectRegistry.UMLTreeNodeRegistry.getTreeNodes();
                List<Object> removeTarget = new ArrayList<Object>();
                for (Iterator<String> iterator = treeNodes.keySet().iterator(); iterator.hasNext();) {
                    String type = (String) iterator.next();
                    for (IResource resource : projectList) {
                        URI uri = URI.createURI(resource.getFullPath().toString());
                        if (type.indexOf(uri.toString()) > -1) {
                            removeTarget.add(type);
                            
                            treeNodes.get(type).refresh();
                        }
                    }
                }
                
                // UMLTreeNodeRegistry.getTreeNodes() 에서 관련된 키 삭제
                for (Object o : removeTarget) {
//                    ProjectRegistry.UMLTreeNodeRegistry.removeTreeNode(o);
                }

                for (IProject project : projectList) {
                    ViewerRegistry.getViewer().refresh(project);
                }
                
                ResourceManager.getInstance().initializeModelingData(projectList.toArray(new IProject[] {}), true);
            }
        });
        

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
