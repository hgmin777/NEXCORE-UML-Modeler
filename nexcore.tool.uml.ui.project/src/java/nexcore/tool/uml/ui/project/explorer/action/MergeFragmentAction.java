/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.project.explorer.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.utility.DomainModelHandlerUtil;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.project.IResourceSetListener;
import nexcore.tool.uml.ui.core.registry.ProjectRegistry.ContentProviderRegistry;
import nexcore.tool.uml.ui.core.registry.ProjectRegistry.UMLTreeNodeRegistry;
import nexcore.tool.uml.ui.core.registry.ResourceManager;
import nexcore.tool.uml.ui.core.registry.ResourceUnloader;
import nexcore.tool.uml.ui.core.util.ProjectUtil;
import nexcore.tool.uml.ui.project.ProjectExplorerPlugin;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.change.util.ChangeRecorder;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.action</li>
 * <li>설  명 : MergeFragmentAction</li>
 * <li>작성일 : 2010. 6. 7.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
@Deprecated
public class MergeFragmentAction extends CommonActionDelegate {

    /**
     * @see nexcore.tool.uml.ui.project.explorer.action.CommonActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    @Override
    public void run(IAction action) {
        if (selectedEObject == null) {
            return;
        }
        if (!(AdapterFactoryEditingDomain.isControlled(selectedEObject))) {
            MessageDialog.openInformation(ProjectExplorerPlugin.getShell(),
                UMLMessage.LABEL_FILE_DEFRAGMENTATION,
                UMLMessage.MESSAGE_FRAGMENT_NOT_FRAGMENTED);
            return;
        }

        if (!MessageDialog.openConfirm(UiCorePlugin.getShell(),
            UMLMessage.getMessage(UMLMessage.LABEL_FILE_DEFRAGMENTATION),
            UMLMessage.getMessage(UMLMessage.MESSAGE_CONFIRM_MERGE))) {
            return;
        }

        final ChangeRecorder recorder = new ChangeRecorder(DomainRegistry.getUMLDomain().getResourceSet());

        // TODO : close the opened windows of the fragment file.
        // CommandUtil.closeEditor(eobject, false);
        DomainRegistry.getEditingDomain()
            .getCommandStack()
            .execute(new RecordingCommand(DomainRegistry.getEditingDomain()) {
                /**
                 * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
                 */
                @Override
                public void doExecute() {
                    ProgressMonitorDialog dialog = new ProgressMonitorDialog(ProjectExplorerPlugin.getShell());
                    dialog.create();
                    EList<Resource> resources = DomainRegistry.getUMLDomain().getResourceSet().getResources();
                    dialog.getProgressMonitor().beginTask(UMLMessage.LABEL_FILE_DEFRAGMENTATION,
                        resources.size());
                    dialog.open();

                    IResourceSetListener rsListener = ContentProviderRegistry.getContentProvider();
                    try {
                        if (rsListener != null) {
                            rsListener.stopResourceListening();
                        }

                        Resource sourceResource = selectedEObject.eResource();
                        org.eclipse.uml2.uml.Package source = (org.eclipse.uml2.uml.Package) selectedEObject;

                        // 단편된 파일에서 부모에 대한 Reference 정보 삭제.
                        EAnnotation sourceFragmentContainerAnnotation = ProjectUtil.getFragmentContainerAnnotation(source);
                        if (sourceFragmentContainerAnnotation.getReferences() != null) {
                            sourceFragmentContainerAnnotation.getReferences().remove(selectedEObject.eContainer());
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
                        org.eclipse.uml2.uml.Package target = (org.eclipse.uml2.uml.Package) selectedEObject.eContainer();
                        EAnnotation targetAnn = ProjectUtil.getFragmentAnnotation(target);
                        if (targetAnn.getReferences() != null) {
                            targetAnn.getReferences().remove(selectedEObject);
                        }

                        // 리소스 파일의 텍스트 비교를 통해 참고관계를 찾는 로직
                        URI srcUri = sourceResource.getURI();
                        URI parentUri = selectedEObject.eContainer().eResource().getURI();
                        List<Resource> saveResourceList = new ArrayList<Resource>();
                        
                        for (Resource rs : resources) {
                            if (rs != null && ProjectUtil.isModelFile(rs)) {          
                                final IFile file = WorkspaceSynchronizer.getFile(rs);
                                if (file!= null && file.exists()) {
                                    if (!rs.isLoaded()) {
                                        rs.load(DomainModelHandlerUtil.getLoadOptions());
                                    }

                                    dialog.getProgressMonitor().worked(1);

                                    if (ResourceManager.getInstance().isRelated(rs, srcUri)) {
                                        dialog.getProgressMonitor().subTask(rs.getURI().toString());

                                        saveResourceList.add(rs);
                                        continue;
                                    }

                                    if (ResourceManager.getInstance().isRelated(rs, parentUri)) {
                                        dialog.getProgressMonitor().subTask(rs.getURI().toString());

                                        saveResourceList.add(rs);
                                        continue;
                                    }

                                    if (rs == selectedEObject.eResource()) {
                                        dialog.getProgressMonitor().subTask(rs.getURI().toString());

                                        saveResourceList.add(rs);
                                        continue;
                                    }

                                    if (rs == selectedEObject.eContainer().eResource()) {
                                        dialog.getProgressMonitor().subTask(rs.getURI().toString());

                                        saveResourceList.add(rs);
                                    }

                                    dialog.getProgressMonitor().subTask("");
                                }
                            }
                        }

                        // 부모 파일에서 Fragment 정보 삭제
                        if (targetAnn != null) {
                            if (targetAnn.getReferences() != null && targetAnn.getReferences().size() == 0) {
                                target.getEAnnotations().remove(targetAnn);
                            }
                        }

                        ResourceManager.getInstance().removeContentsOfResource(sourceResource, selectedEObject);

                        selectedEObject.eResource()
                            .getContents()
                            .addAll(ProjectUtil.getStereotypesForFragment(sourceResource, selectedEObject, true));

                        for (Resource saveResource : saveResourceList) {
                            DomainModelHandlerUtil.save(saveResource, true);
                        }
                        // have to remove source file.
                        final EObject parentObject = selectedEObject.eContainer();

                        final IFile file = WorkspaceSynchronizer.getFile(sourceResource);

                        TreeIterator<EObject> allContents = source.eAllContents();
                        while( allContents.hasNext() ){
                            EObject content = allContents.next();
                            UMLTreeNodeRegistry.removeTreeNode(content);
                        }
                        
                        UMLTreeNodeRegistry.removeTreeNode(source);
//                        ResourceManager.getInstance().removeResource(sourceResource);
                        ResourceUnloader.getInstance().put(sourceResource);
                        
                        ProjectExplorerPlugin.getDisplay().asyncExec(new Runnable() {
                            public void run() {
                                try { // 2011-08-11 강석찬
                                    // 단편파일이 포함된 .fragment하위의 비어있는 폴더를 삭제함
                                    boolean isPackageStructuredFragment = true;
                                    IFolder folder = ((IFolder) ResourcesPlugin.getWorkspace()
                                        .getRoot()
                                        .getFolder(file.getFullPath())
                                        .getParent());
                                    while (!folder.getName()
                                        .equals(UICoreConstant.PROJECT_CONSTANTS__FRAGMENT_FOLDER_NAME)) {
                                        Object parent = folder.getParent();
                                        if (!(parent instanceof IFolder)) {
                                            isPackageStructuredFragment = false;
                                            break;
                                        }
                                        folder = ((IFolder) parent);
                                    }

                                    if (isPackageStructuredFragment) {
                                        folder = ((IFolder) ResourcesPlugin.getWorkspace()
                                            .getRoot()
                                            .getFolder(file.getFullPath())
                                            .getParent());
                                        List<IFolder> removeList = new ArrayList<IFolder>();
                                        while ((folder.members().length == 1)
                                            && (!folder.getName()
                                                .equals(UICoreConstant.PROJECT_CONSTANTS__FRAGMENT_FOLDER_NAME))) {
                                            removeList.add(folder);
                                            folder = ((IFolder) folder.getParent());
                                        }
                                        file.delete(false, new NullProgressMonitor());
                                        for (Iterator<IFolder> it = removeList.iterator(); it.hasNext();) {
                                            folder = (IFolder) it.next();
                                            folder.delete(false, new NullProgressMonitor());
                                        }
                                    } else {
                                        file.delete(false, new NullProgressMonitor());
                                    }

                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                    Log.error(ex);
                                } finally {
                                    try {
                                        if (parentObject != null) {
                                            ProjectUtil.refreshNodeInExplorer(parentObject);
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        // ignore
                                    }
                                }
                            }
                        });


                    } catch (Exception e) {
                        e.printStackTrace();

                        // ResourceSet rollback 처리
                        ProjectUtil.rollbackResourceSet(recorder);

                        dialog.getProgressMonitor().done();
                        dialog.close();
                        MessageDialog.openError(ProjectExplorerPlugin.getShell(),
                            UMLMessage.LABEL_FILE_DEFRAGMENTATION,
                            UMLMessage.MESSAGE_SAVE_FAIL);
                    } finally {
                        if (rsListener != null) {
                            rsListener.startResourceListening();
                        }
                        try {
                            dialog.getProgressMonitor().done();
                            dialog.close();
                        } catch (Exception e) {
                            //ignore
                        }
                    }
                }
            });
        System.gc();
    }
}
