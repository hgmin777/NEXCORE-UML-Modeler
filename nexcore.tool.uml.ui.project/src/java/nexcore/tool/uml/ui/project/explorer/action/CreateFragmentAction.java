/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.project.explorer.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.manager.utility.DomainModelHandlerUtil;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.model.projectinformation.ProjectElement;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.project.IResourceSetListener;
import nexcore.tool.uml.ui.core.registry.ProjectRegistry.ContentProviderRegistry;
import nexcore.tool.uml.ui.core.registry.ProjectRegistry.ViewerRegistry;
import nexcore.tool.uml.ui.core.registry.ResourceManager;
import nexcore.tool.uml.ui.core.util.ProjectUtil;
import nexcore.tool.uml.ui.core.util.TreeItemUtil;
import nexcore.tool.uml.ui.project.ProjectExplorerPlugin;
import nexcore.tool.uml.ui.project.editor.command.HandleProjectCommand;
import nexcore.tool.uml.ui.project.explorer.dialog.FragmentSaveDialog;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.change.util.ChangeRecorder;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.uml2.uml.Package;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.action</li>
 * <li>설  명 : CreateFragmentAction</li>
 * <li>작성일 : 2010. 6. 3.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
@Deprecated
public class CreateFragmentAction extends CommonActionDelegate {

    /**
     * expendedList
     */
    List<Object> expendedList = Collections.EMPTY_LIST;

    /**
     * @see nexcore.tool.uml.ui.project.explorer.action.CommonActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    @Override
    public void run(IAction action) {

        if (selectedEObject == null || selectedEObject.eResource() == null) {
            MessageDialog.openError(ProjectExplorerPlugin.getShell(),
                UMLMessage.LABEL_FILE_FRAGMENTATION,
                UMLMessage.MESSAGE_RESOURCE_IS_NULL);
            return;
        }

        if (selectedEObject.eResource().isModified()) {
            SafeRunnable.run(new ISafeRunnable() {
                public void handleException(Throwable e) {
                    e.printStackTrace();
                }

                public void run() throws Exception {
                    DomainModelHandlerUtil.save(selectedEObject);
                }

            });
        }

        if (AdapterFactoryEditingDomain.isControlled(selectedEObject)) {
            MessageDialog.openInformation(ProjectExplorerPlugin.getShell(),
                UMLMessage.LABEL_FILE_FRAGMENTATION,
                UMLMessage.MESSAGE_FRAGMENT_FRAGMENTED);
            return;
        }

        final ChangeRecorder recorder = new ChangeRecorder(DomainRegistry.getUMLDomain().getResourceSet());

        CommonViewer commonViewer = ViewerRegistry.getViewer();
        TreePath[] expanedTreePaths = TreeItemUtil.getExpandTreePaths(commonViewer.getTree()).clone();

        String uriPath = null;
        boolean strBoolean = UiCorePlugin.getDefault().getPreferenceStore().getBoolean(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PROJECTEXPLORER_FRAGMENT_BASE_PACKAGE);
        if (!strBoolean) {

            FragmentSaveDialog dialog = new FragmentSaveDialog(ProjectExplorerPlugin.getShell(),
                selectedEObject,
                FragmentSaveDialog.FRAGMENT);

            if (dialog.open() == Dialog.CANCEL) {
                return;
            }

            uriPath = dialog.getResult().toString();
        }

        else {

            // 2011-08-09 강석찬
            // .fragment folder 하위에 패키지구조로 폴더 생성
            // ProjectUtil.generatePackageUriPath(xxx,xxx) 로 이동.    
            
            uriPath = ProjectUtil.generatePackageUriPath((Package) selectedEObject, true);
            if (uriPath == null) {
                return;
            }
        }

        final URI uri = URI.createURI(uriPath);

        DomainRegistry.getEditingDomain()
            .getCommandStack()
            .execute(new RecordingCommand(DomainRegistry.getEditingDomain()) {
                /**
                 * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
                 */
                @Override
                public void doExecute() {
                    ProgressMonitorDialog dialog = new ProgressMonitorDialog(PlatformUI.getWorkbench()
                        .getActiveWorkbenchWindow()
                        .getShell());
                    IResourceSetListener rsListener = ContentProviderRegistry.getContentProvider();
                    IProgressMonitor monitor = dialog.getProgressMonitor();
                    try {
                        dialog.create();

                        EList<Resource> resources = DomainRegistry.getUMLDomain().getResourceSet().getResources();
                        monitor.beginTask(UMLMessage.LABEL_FILE_FRAGMENTATION, resources
                            .size());
                        dialog.open();

                        if (rsListener != null) {
                            rsListener.stopResourceListening();
                        }

                        Resource sourceResource = selectedEObject.eResource();
                        Resource targetResource = DomainRegistry.getUMLDomain().getResourceSet().createResource(uri);
                        if (!targetResource.isTrackingModification()) {
                            targetResource.setTrackingModification(true);
                        }
                        targetResource.getContents().add(selectedEObject);
                        // 적용된 stereoType 정보 옮겨주기
                        targetResource.getContents().addAll(ProjectUtil.getStereotypesForFragment(sourceResource,
                            selectedEObject,
                            false));

                        org.eclipse.uml2.uml.Package source = (org.eclipse.uml2.uml.Package) selectedEObject.eContainer();
                        EAnnotation sourceAnno = ProjectUtil.getFragmentAnnotation(source);
                        if (sourceAnno.getReferences() != null) {
                            sourceAnno.getReferences().add(selectedEObject);
                        }

                        org.eclipse.uml2.uml.Package target = (org.eclipse.uml2.uml.Package) selectedEObject;
                        EAnnotation targetAnno = ProjectUtil.getFragmentContainerAnnotation(target);
                        if (targetAnno.getReferences() != null) {
                            targetAnno.getReferences().add(selectedEObject.eContainer());
                        }

                        // 단편화 파일에 프로젝트 버전 추가
                        boolean hasVersionInfo = false;
                        Package pObject = (Package) selectedEObject;
                        for (EAnnotation eAnno : pObject.getEAnnotations()) {
                            if (eAnno instanceof ProjectElement) {
                                hasVersionInfo = true;
                                break;
                            }
                        }
                        if (!hasVersionInfo) {
                            ProjectElement projectInfo = ProjectUtil.createProjectInfo();

                            RecordingCommand command = new HandleProjectCommand(DomainRegistry.getEditingDomain(),
                                (Package) selectedEObject,
                                projectInfo,
                                true);
                            DomainUtil.executeCommand(command);
                        }

                        URI uri = selectedEObject.eContainer().eResource().getURI();

                        
                        //EcoreUtil.UnresolvedProxyCrossReferencer.find(targetResource);
                        
                        List<String> checkedFileList = new ArrayList<String>();
                        // 리소스 파일의 텍스트 비교를 통해 참고관계를 찾는 로직
                        for (Resource rs : resources) {
                            if (rs != null &&  ProjectUtil.isModelFile(rs)) {
                                final IFile file = WorkspaceSynchronizer.getFile(rs);
                                monitor.worked(1);

                                if (file != null && file.exists()) {
                                    
                                    if (checkedFileList.contains(file.getFullPath().toString())) {
                                        continue;
                                    }
                                    checkedFileList.add(file.getFullPath().toString());
                                    
                                    if (!rs.isLoaded()) {
                                        rs.load(DomainModelHandlerUtil.getLoadOptions());
                                    }
                                    if (ResourceManager.getInstance().isRelated(rs, uri)) {
                                        monitor.subTask(rs.getURI().toString());
                                        DomainModelHandlerUtil.save(rs, true);
                                        continue;
                                    }
                                }

                                if (rs == targetResource) {
                                    monitor.subTask(rs.getURI().toString());

                                    IFile fragmentFile = null;
                                    try {
                                        fragmentFile = WorkspaceSynchronizer.getFile(targetResource);
                                        fragmentFile.setCharset(UICoreConstant.PROJECT_CONSTANTS__ENCODING_UTF8, monitor);
                                    } catch (CoreException e) {
                                        Log.error(e);
                                    } finally {
                                        DomainModelHandlerUtil.save(rs, true);
                                    }
                                }

                                monitor.subTask(UICoreConstant.EMPTY_STRING);
                            }
                        }

                        // 선택한 노드의 부모 리소스 저장.
                        DomainModelHandlerUtil.save(selectedEObject.eContainer().eResource(), true);

                    } catch (Exception e) {
                        e.printStackTrace();
                        // ResourceSet rollback 처리
                        ProjectUtil.rollbackResourceSet(recorder);
                        monitor.done();
                        dialog.close();
                        
                        MessageDialog.openError(ProjectExplorerPlugin.getShell(),
                            UMLMessage.LABEL_FILE_FRAGMENTATION,
                            UMLMessage.MESSAGE_SAVE_FAIL + " : " + e.getMessage());

                    } finally {
                        if (rsListener != null) {
                            rsListener.startResourceListening();
                        }
                        if (monitor != null)
                            monitor.done();
                        if (dialog != null)
                            dialog.close();
                    }

                }

            });
        TreeItemUtil.expandTreePath(expanedTreePaths, selection);

        System.gc();
    }
}
