/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.project.explorer.action;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.utility.DomainModelHandlerUtil;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.registry.ProjectRegistry.UMLTreeNodeRegistry;
import nexcore.tool.uml.ui.core.registry.ProjectRegistry.ViewerRegistry;
import nexcore.tool.uml.ui.core.registry.ResourceManager;
import nexcore.tool.uml.ui.core.registry.ResourceUnloader;
import nexcore.tool.uml.ui.core.util.ProjectUtil;
import nexcore.tool.uml.ui.core.util.TreeItemUtil;
import nexcore.tool.uml.ui.project.ProjectExplorerPlugin;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.transaction.TransactionalCommandStack;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.uml2.uml.Profile;

/**
 * 
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.action</li>
 * <li>설 명 : MultiMergeFragmentAction</li>
 * <li>작성일 : 2011. 9. 19.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public class MultiMergeFragmentAction extends BaseMultiFragmentAction {
    /**
     * parentObjectList
     */
    List<EObject> parentObjectList = null;

    /**
     * removeResourceList
     */
    Set<Resource> removeResourceList = null;

    /**
     * deleteFileList
     */
    Set<Resource> deleteFileList = null;

    /**
     * @see nexcore.tool.uml.ui.project.explorer.action.BaseMultiFragmentAction#run(org.eclipse.jface.action.IAction)
     */
    public void run(IAction action) {
        isContinue = true;
        parentObjectList = new ArrayList<EObject>();
        removeResourceList = new HashSet<Resource>();
        deleteFileList = new HashSet<Resource>();
        CommonViewer commonViewer = ViewerRegistry.getViewer();
        if (!hierarchyCheck()) {
            return;
        }

        if (!MessageDialog.openConfirm(UiCorePlugin.getShell(),
            UMLMessage.getMessage(UMLMessage.LABEL_FILE_DEFRAGMENTATION),
            UMLMessage.getMessage(UMLMessage.MESSAGE_CONFIRM_MERGE))) {
            return;
        }

        expanedTreePaths = TreeItemUtil.getExpandTreePaths(commonViewer.getTree()).clone();

        start();

        dialog = new ProgressMonitorDialog(targetPart.getSite().getShell());

        WorkspaceModifyOperation workspaceModifyOperation = new WorkspaceModifyOperation() {
            /**
             * @see org.eclipse.ui.actions.WorkspaceModifyOperation#execute(org.eclipse.core.runtime.IProgressMonitor)
             */
            @Override
            protected void execute(final IProgressMonitor monitor) throws CoreException, InvocationTargetException,
            InterruptedException {

                RecordingCommand command = new RecordingCommand(DomainModelHandlerUtil.getEditingDomain()) {

                    @Override
                    protected void doExecute() {
                        EList<Resource> resources = DomainRegistry.getUMLDomain().getResourceSet().getResources();
                        monitor.beginTask(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING, objects.size() + resources.size());
                        monitor.setTaskName(UMLMessage.LABEL_FILE_DEFRAGMENTATION);
                        for (final EObject eobject : objects) {
                            monitor.worked(1);
                            if (eobject == null) {
                                continue;
                            }
                            if (!(AdapterFactoryEditingDomain.isControlled(eobject))) {
                                continue;
                            }

                            Resource sourceResource = eobject.eResource();
                            org.eclipse.uml2.uml.Package source = (org.eclipse.uml2.uml.Package) eobject;
                            monitor.subTask(String.format("%s: %s",UMLMessage.LABEL_PACKAGE_QUALIFIEDNAME, source.getQualifiedName()));

                            // 단편된 파일에서 부모에 대한 Reference 정보 삭제.
                            EAnnotation sourceFragmentContainerAnnotation = ProjectUtil.getFragmentContainerAnnotation(source);
                            if (sourceFragmentContainerAnnotation.getReferences() != null) {
                                sourceFragmentContainerAnnotation.getReferences().remove(eobject.eContainer());
                            }

                            // 단편된 패키지에서 프로파일 정보 삭제
//                            unApplyProfile(source);
                            EList<Profile> appliedProfiles = source.getAppliedProfiles();
                            for (Profile profile : appliedProfiles) {
                                source.unapplyProfile(profile);
                            }
                            
                            
                            // 단편된 파일에서 FragmentContainer 정보 삭제
                            if (sourceFragmentContainerAnnotation != null) {
                                source.getEAnnotations().remove(sourceFragmentContainerAnnotation);
                            }
                            // 단편된 파일에서 ProjectInfo 정보 삭제
                            EAnnotation sourceProjectInfoAnnotation = ProjectUtil.getProjectInfoAnnotation(source);
                            if (sourceProjectInfoAnnotation != null) {
                                source.getEAnnotations().remove(sourceProjectInfoAnnotation);
                            }

                            // 부모 파일에서 Fragment Reference 정보 삭제
                            org.eclipse.uml2.uml.Package target = (org.eclipse.uml2.uml.Package) eobject.eContainer();
                            EAnnotation targetAnn = ProjectUtil.getFragmentAnnotation(target);
                            if (targetAnn.getReferences() != null) {
                                targetAnn.getReferences().remove(eobject);
                            }

                            

                            try {
                                
                                List<Resource> referenceResource = ResourceManager.getInstance().inverseReferences(eobject);
                                for (Resource rs : referenceResource) {
                                    if (rs != null && !(rs.getContents().isEmpty()) && !saveResource.contains(rs)) {
                                        saveResource.add(rs);
                                    }
                                }
                                
                                referenceResource = ResourceManager.getInstance().inverseReferences(eobject.eContainer());
                                for (Resource rs : referenceResource) {
                                    if (rs != null && !(rs.getContents().isEmpty()) && !saveResource.contains(rs)) {
                                        saveResource.add(rs);
                                    }
                                }

                                for (Iterator<Resource> ir = saveResource.iterator(); ir.hasNext();) {
                                    Resource rs = ir.next();
                                    EcoreUtil.resolveAll(rs);
                                }
                                
                                // 부모 파일에서 Fragment 정보 삭제
                                if (targetAnn != null) {
                                    if (targetAnn.getReferences() != null && targetAnn.getReferences().size() == 0) {
                                        target.getEAnnotations().remove(targetAnn);
                                    }
                                }

                                try {
                                    ResourceManager.getInstance().removeContentsOfResource(sourceResource, eobject);
                                } catch (Exception e) {
                                    // ignore
                                    e.printStackTrace();
                                }
                                
                                eobject.eResource().getContents().addAll(ProjectUtil.getStereotypesForFragment(sourceResource, eobject, true));
                                
                                EObject parentObject = eobject.eContainer();

                                if (parentObject != null) {
                                    if (!parentObjectList.contains(parentObject)) {
                                        parentObjectList.add(parentObject);
                                    }
                                }

                                deleteFileList.add(sourceResource);
                                removeResourceList.add(sourceResource);
                                
                            } catch (Exception e) {
                                e.printStackTrace();
                                done();
                            } 

                        }
                    }
                };
                // 컴파운드 커맨드 실행
                TransactionalEditingDomain editingDomain = DomainModelHandlerUtil.getEditingDomain();
                TransactionalCommandStack tstack = (TransactionalCommandStack) editingDomain.getCommandStack();
                try {
                    tstack.execute(command,
                        Collections.singletonMap(Transaction.OPTION_NO_NOTIFICATIONS, Boolean.TRUE));
                } catch (RollbackException e) {
                    e.printStackTrace();
                }
                
                monitor.done();
            }
        };

        try {
            // 진행 모니터 다이얼로그 실행
            dialog.run(false, false, workspaceModifyOperation);
        } catch (InvocationTargetException ite) {
            ite.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        
        if( !isContinue ) {
            MessageDialog.openError(targetPart.getSite().getShell(), UMLMessage.TITLE_ERROR, UMLMessage.MESSAGE_FILE_FRAGMENT_ERROR_MESSAGE/*"병합중 에러가 발생했습니다."*/);
            return;
        }

        workspaceModifyOperation = new WorkspaceModifyOperation() {
            /**
             * @see org.eclipse.ui.actions.WorkspaceModifyOperation#execute(org.eclipse.core.runtime.IProgressMonitor)
             */
            @Override
            protected void execute(final IProgressMonitor monitor) throws CoreException, InvocationTargetException,
            InterruptedException {
                monitor.beginTask(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING, saveResource.size());
                monitor.setTaskName(UMLMessage.TITLE_SAVE);
                monitor.subTask(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING);

                for (Resource resource : saveResource) {
                    monitor.worked(1);
                    monitor.subTask(String.format("%s", resource.getURI()));
                    try {
                        DomainModelHandlerUtil.save(resource, true);
                    } catch (Exception e) {
                        e.printStackTrace();
                        done();
                        MessageDialog.openError(ProjectExplorerPlugin.getShell(),
                            UMLMessage.LABEL_FILE_DEFRAGMENTATION,
                            UMLMessage.MESSAGE_SAVE_FAIL + " : " + resource.getURI().toString());
                    }
                }

                monitor.subTask("");

                for (URI uri : removeTreeNode) {
                    UMLTreeNodeRegistry.removeTreeNode(uri);
                }

                for (Resource sourceResource : removeResourceList) {
                    ResourceUnloader.getInstance().put(sourceResource);
                }
                
                monitor.setTaskName(UMLMessage.TITLE_DELETE_FILE/*"단편 파일 삭제"*/);
                ProjectExplorerPlugin.getDisplay().asyncExec(new Runnable() {
                    public void run() {
                        for (Resource resource : deleteFileList) {
                            try {
                                if (resource != null) {
                                    monitor.subTask(String.format("%s", resource.getURI().toString()));
                                    resource.delete(null);
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                });

                // 폴더 및 빈파일 정리 
                IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
                for (IProject project : root.getProjects()) {
                    try {
                        IFolder folder = project.getFolder(".fragment");
                        cleanFragment(folder);
                    } catch (Exception e) {

                    }
                }
                
                monitor.setTaskName(UMLMessage.TITLE_REFRESH);
                monitor.subTask(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING);
                for (EObject parentObject : parentObjectList) {
                    try {
                        ProjectUtil.refreshNodeInExplorer(parentObject);
                    } catch (Exception e) {
                        // ignore
                    }
                }
            }
            
            /**
             * 
             * 빈 폴더와 모델 파일 정리.
             *  
             * @param parent void
             */
            private void cleanFragment(IResource parent) {
                try {
                    if(parent instanceof IFolder) {
                        IFolder folder = (IFolder) parent;
                        
                        IResource[] members = folder.members();
                        if( members == null || members.length == 0) {
                            folder.delete(true, new NullProgressMonitor());
                        } else {
                            for(IResource resource : members) {
                                cleanFragment(resource);
                            }
                            members = folder.members();
                            if( members == null || members.length == 0) {
                                try {
                                folder.delete(true, new NullProgressMonitor());
                                } catch (Exception e) {
                                }
                            }
                        }
                    } else if (parent instanceof IFile) {
                        IFile file = (IFile) parent; 
                        if( !ProjectUtil.isModelFile(file)) {
                            return;
                        }
                        File f = new File(file.getLocation().toString());
                        if( f.length() > 150) {
                            return;
                        }
                        try {
                            URI uri = URI.createURI(((IFile) parent).getFullPath().toString());
                            Resource resource = DomainRegistry.getUMLDomain().getResourceSet().getResource(uri, true);
                            
                            if (resource != null && resource.getContents().size() == 0) {
                                resource.delete(null);
                            }
                        } catch (Exception e) {
                        }
                    }
                } catch (Exception e) {
                }
            }
        };

        try {
            // 진행 모니터 다이얼로그 실행
            dialog.run(false, false, workspaceModifyOperation);
        } catch (InvocationTargetException ite) {
            ite.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        } finally {
            done();
        }
    }
}
