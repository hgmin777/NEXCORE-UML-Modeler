/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.project.explorer.action;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import nexcore.alm.common.ui.util.ALMLogger;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.core.util.StringUtil;
import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.manager.utility.DomainModelHandlerUtil;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.model.projectinformation.ProjectElement;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.registry.ProjectRegistry.UMLTreeNodeRegistry;
import nexcore.tool.uml.ui.core.registry.ProjectRegistry.ViewerRegistry;
import nexcore.tool.uml.ui.core.registry.ResourceManager;
import nexcore.tool.uml.ui.core.util.PreferenceUtil;
import nexcore.tool.uml.ui.core.util.ProjectUtil;
import nexcore.tool.uml.ui.core.util.TreeItemUtil;
import nexcore.tool.uml.ui.project.ProjectExplorerPlugin;
import nexcore.tool.uml.ui.project.editor.command.HandleProfileCommand;
import nexcore.tool.uml.ui.project.editor.command.HandleProjectCommand;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.transaction.TransactionalCommandStack;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.jdt.internal.ui.wizards.buildpaths.FolderSelectionDialog;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.ProfileApplication;

/**
 * 
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.action</li>
 * <li>설 명 : MultiCreateFragmentAction</li>
 * <li>작성일 : 2011. 9. 19.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public class MultiCreateFragmentAction extends BaseMultiFragmentAction {
    /**
     * parentObjectList
     */
    List<EObject> parentObjectList = null;

    /**
     * uriMap
     */
    Map<String, EObject> uriMap = null;

    /**
     * selectedURI
     */
    String selectedURI = null;

    /**
     * @see nexcore.tool.uml.ui.project.explorer.action.BaseMultiFragmentAction#run(org.eclipse.jface.action.IAction)
     */
    public void run(IAction action) {
        selectedURI = null;
        isContinue = true;
        parentObjectList = new ArrayList<EObject>();
        CommonViewer commonViewer = ViewerRegistry.getViewer();

        if (!hierarchyCheck()) {
            return;
        }

        try {
            uriMap = new HashMap<String, EObject>();
            dialog = new ProgressMonitorDialog(targetPart.getSite().getShell());

            WorkspaceModifyOperation workspaceModifyOperation = new WorkspaceModifyOperation() {
                /**
                 * @see org.eclipse.ui.actions.WorkspaceModifyOperation#execute(org.eclipse.core.runtime.IProgressMonitor)
                 */
                @Override
                protected void execute(final IProgressMonitor monitor) throws CoreException, InvocationTargetException,
                InterruptedException {
                    monitor.beginTask(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING, objects.size());
                    for (final EObject fragmentObject : objects) {
                        monitor.worked(1);
                        if (fragmentObject == null || !(fragmentObject instanceof Package)) {
                            continue;
                        }

                        Resource rs = fragmentObject.eResource();
                        if (rs == null) {
                            EcoreUtil.resolveAll(fragmentObject);
                            // EcoreUtil.UnresolvedProxyCrossReferencer.find(fragmentObject);
                            rs = fragmentObject.eResource();
                            if (rs == null) {
                                continue;
                            }
                        }
                        String name = ((Package) fragmentObject).getName();
                        String key = String.format("%s_%s", name, EcoreUtil.generateUUID());
                        uriMap.put(key, fragmentObject);

                        monitor.subTask(name);
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
            }

            if (uriMap.size() < 1) {
                dialog.close();
                return;
            }

            // 중복체크
            List<String> duplicateCheckList = new ArrayList<String>();

            for (Iterator<EObject> iterator = uriMap.values().iterator(); iterator.hasNext();) {
                EObject eObj = (EObject) iterator.next();

                String qualifiedName = ((Package) eObj).getQualifiedName();

                if (duplicateCheckList.contains(qualifiedName)) {
                    // 패키지명 중복 체크
                    MessageDialog.openError(targetPart.getSite().getShell(),
                        UMLMessage.TITLE_ERROR,
                        "한 패키지에 중복된 이름의 패키지가 존재 합니다. 패키지명을 변경하세요.");
                    return;

                } else {
                    duplicateCheckList.add(qualifiedName);
                }
            }
        } catch (Exception e) {
            MessageDialog.openError(targetPart.getSite().getShell(), UMLMessage.TITLE_ERROR, e.getMessage());

            ALMLogger.getLog(ProjectExplorerPlugin.PLUGIN_ID).error(e.getMessage(), e);
            return;
        }

        // 프로젝트의 .fragment 하위에 패키지 구조로 폴더와 단편파일 생성 여부
        final boolean toggle_state = PreferenceUtil.INSTANCE.getValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PROJECTEXPLORER_FRAGMENT_BASE_PACKAGE);

        // 묻지않고 항상 실행
        boolean always_run = PreferenceUtil.INSTANCE.getValueOfBooleanFieldEditor(ManagerConstant.ALWAYS_RUN);

        if (toggle_state) {
            if (!always_run) {
                // 패키지 구조로 단편 파일 생성
                MessageDialogWithToggle m = MessageDialogWithToggle.openOkCancelConfirm(UiCorePlugin.getShell(),
                    UMLMessage.MESSAGE_FILE_FRAGMENTATION,
                    UMLMessage.getMessage(UMLMessage.MESSAGE_CONFIRM_CREATE_STRUCTURED_FRAGMENT),
                    UMLMessage.MESSAGE_CREATE_WITHOUT_PROMPT,
                    always_run,
                    PreferenceUtil.INSTANCE.getPreferenceStore(),
                    ManagerConstant.ALWAYS_RUN);

                if (m.getReturnCode() != Window.OK) {
                    return;
                }

                if (m.getToggleState() == true) {
                    UiCorePlugin.getDefault()
                        .getPreferenceStore()
                        .setValue(ManagerConstant.ALWAYS_RUN, m.getToggleState());
                }
            }
        } else {
            // 사용자 지정 위치에 단편 파일 생성
            FolderSelectionDialog dialog = new FolderSelectionDialog(UiCorePlugin.getShell(),
                new WorkbenchLabelProvider(),
                new WorkbenchContentProvider() {
                    @Override
                    public Object[] getChildren(Object element) {
                        Object[] children = super.getChildren(element);
                        List<Object> list = new ArrayList<Object>();
                        for (Object obj : children) {
                            if (obj instanceof IProject) {
                                IProject pjt = (IProject) obj;
                                if (ProjectUtil.isActiveUMLProject(pjt)) {
                                    list.add(pjt);
                                }
                            } else if (obj instanceof IFile) {
                                continue;
                            } else if (obj instanceof IFolder) {
                                IFolder folder = (IFolder) obj;
                                list.add(folder);
                            }
                        }
                        return list.toArray();
                    }
                }) {

                @Override
                public void create() {
                    super.create();
                    getButton(IDialogConstants.OK_ID).setEnabled(false);
                }

                @Override
                public void selectionChanged(SelectionChangedEvent event) {
                    super.selectionChanged(event);
                    ISelection sel = event.getSelection();
                    Object firstElement = ((StructuredSelection) sel).getFirstElement();
                    if (!(firstElement instanceof IFolder)) {
                        getButton(IDialogConstants.OK_ID).setEnabled(false);
                    } else {
                        getButton(IDialogConstants.OK_ID).setEnabled(true);
                    }
                }
            };

            dialog.setInput(ResourcesPlugin.getWorkspace().getRoot());
            if (dialog.open() != Dialog.OK) {
                return;
            }
            Object firstResult = dialog.getFirstResult();
            if (firstResult != null) {
                selectedURI = ((IFolder) firstResult).getFullPath().toString();
            }

        }

        expanedTreePaths = TreeItemUtil.getExpandTreePaths(commonViewer.getTree()).clone();

        try {
            // 모든 에디터 닫기
            closeEditor();

            start();

            // 단편화 생성
            WorkspaceModifyOperation workspaceModifyOperation = new WorkspaceModifyOperation() {
                /**
                 * @see org.eclipse.ui.actions.WorkspaceModifyOperation#execute(org.eclipse.core.runtime.IProgressMonitor)
                 */
                @Override
                protected void execute(final IProgressMonitor monitor) throws CoreException, InvocationTargetException,
                InterruptedException {

                    RecordingCommand command = new RecordingCommand(getEditingDomain()) {

                        @Override
                        protected void doExecute() {
                            EList<Resource> resources = DomainRegistry.getUMLDomain().getResourceSet().getResources();
                            monitor.beginTask(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING,
                                uriMap.size() + resources.size());

                            List<URI> uriList = new ArrayList<URI>();

                            try {
                                for (Iterator<String> iterator = uriMap.keySet().iterator(); iterator.hasNext();) {
                                    monitor.worked(1);
                                    final String key = iterator.next();
                                    final EObject sourceObject = uriMap.get(key);

                                    if (sourceObject == null) {
                                        continue;
                                    }

                                    URI srcUri = EcoreUtil.getURI(sourceObject);
                                    EObject eObject = getEditingDomain().getResourceSet().getEObject(srcUri, true);

                                    String packageName = ((Package) sourceObject).getName();
                                    String qualifiedName = ((Package) sourceObject).getQualifiedName();
                                    monitor.setTaskName(UMLMessage.LABEL_FILE_FRAGMENTATION + " : " + packageName);

//                                    Display.getDefault().syncExec(new Runnable() {
//                                        public void run() {
//                                            URI sourceResourceURI = sourceObject.eResource().getURI();
//                                            Resource resource = getEditingDomain().getResourceSet()
//                                                .getResource(sourceResourceURI, true);
//                                            if (!resource.getErrors().isEmpty()) {
//                                                resource.unload();
//                                                resource = DomainRegistry.getUMLDomain()
//                                                    .getResourceSet()
//                                                    .getResource(sourceResourceURI, true);
//
//                                                System.out.println("");
//                                            }
//                                        }
//                                    });

                                    monitor.subTask(String.format("%s: %s",
                                        UMLMessage.LABEL_PACKAGE_QUALIFIEDNAME,
                                        qualifiedName)); // 패키지

                                    String uriPath = null;
                                    if (toggle_state) {
                                        uriPath = ProjectUtil.generatePackageUriPath(selectedURI,
                                            (Package) sourceObject);
                                    } else {
                                        String uri = StringUtil.getProperFileName(((Package) sourceObject).getName(),
                                            UICoreConstant.PROJECT_CONSTANTS__UNDER_BAR)
                                            + ManagerConstant.DOT
                                            + ManagerConstant.UMLDOMAIN_CONSTANT__FRAGMENT_FILE_EXTENSION;
                                        uriPath = selectedURI + UICoreConstant.PROJECT_CONSTANTS__SLASH + uri;
                                        uriPath = ProjectUtil.getUniqueFileName(new Path(uriPath));
                                    }

                                    if (uriPath == null || uriPath.length() == 0) {
                                        String name = ((Package) sourceObject).getName();
                                        if (sourceObject.eResource() == null) {
                                            return;
                                        }
                                        final IFile file = WorkspaceSynchronizer.getFile(sourceObject.eResource());

                                        uriPath = String.format("%s/.fragment/%s/%s_%s.umf", file.getProject()
                                            .getFullPath(), name, name, EcoreUtil.generateUUID());
                                    }

                                    Resource sourceResource = sourceObject.eResource();
                                    URI uri = URI.createURI(uriPath);

                                    Resource targetResource = DomainModelHandlerUtil.createResource(uri);
                                    if (targetResource == null) {
                                        targetResource = DomainRegistry.getEditingDomain()
                                            .getResourceSet()
                                            .createResource(uri);
                                    }

                                    if (((ResourceImpl) targetResource).getIntrinsicIDToEObjectMap() == null) {
                                        ((ResourceImpl) targetResource).setIntrinsicIDToEObjectMap(new HashMap<String, EObject>());
                                    }
                                    if (!targetResource.isTrackingModification()) {
                                        targetResource.setTrackingModification(true);
                                    }

                                    targetResource.getContents().add(sourceObject);
                                    
                                    // 단편 패키지에 모델에 적용된 프로파일 적용.
                                    applyProfile((Package)sourceObject);
                                    
                                    // 적용된 stereoType 정보 옮겨주기
                                    targetResource.getContents()
                                        .addAll(ProjectUtil.getStereotypesForFragment(sourceResource,
                                            sourceObject,
                                            false));

                                    if (targetResource != null && !(targetResource.getContents().isEmpty())
                                        && !saveResource.contains(targetResource)) {
                                        saveResource.add(targetResource);
                                    }

                                    if (sourceResource != null && !(sourceResource.getContents().isEmpty())
                                        && !saveResource.contains(sourceResource)) {
                                        saveResource.add(sourceResource);
                                    }
                                    org.eclipse.uml2.uml.Package parentContainer = (org.eclipse.uml2.uml.Package) sourceObject.eContainer();

                                    EAnnotation sourceAnno = ProjectUtil.getFragmentAnnotation(parentContainer);
                                    if (sourceAnno.getReferences() != null) {
                                        sourceAnno.getReferences().add(sourceObject);
                                    }

                                    org.eclipse.uml2.uml.Package target = (org.eclipse.uml2.uml.Package) sourceObject;
                                    EAnnotation targetAnno = ProjectUtil.getFragmentContainerAnnotation(target);
                                    if (targetAnno.getReferences() != null) {
                                        targetAnno.getReferences().add(sourceObject.eContainer());
                                    }

                                    // 단편화 파일에 프로젝트 버전 추가
                                    boolean hasVersionInfo = false;
                                    Package pObject = (Package) sourceObject;
                                    for (EAnnotation eAnno : pObject.getEAnnotations()) {
                                        if (eAnno instanceof ProjectElement) {
                                            hasVersionInfo = true;
                                            break;
                                        }
                                    }
                                    if (!hasVersionInfo) {
                                        ProjectElement projectInfo = ProjectUtil.createProjectInfo();

                                        RecordingCommand command = new HandleProjectCommand(getEditingDomain(),
                                            (Package) sourceObject,
                                            projectInfo,
                                            true);

                                        TransactionalEditingDomain editingDomain = DomainModelHandlerUtil.getEditingDomain();
                                        TransactionalCommandStack tstack = (TransactionalCommandStack) editingDomain.getCommandStack();
                                        try {
                                            tstack.execute(command,
                                                Collections.singletonMap(Transaction.OPTION_NO_NOTIFICATIONS,
                                                    Boolean.TRUE));
                                            // tstack.execute(command,
                                            // Collections.emptyMap());
                                        } catch (RollbackException e) {
                                            e.printStackTrace();
                                        }
                                        // DomainUtil.executeCommand(command);
                                    }

                                    Resource eResource = sourceObject.eContainer().eResource();
                                    URI containerResourceURI = eResource.getURI();
                                    uriList.add(containerResourceURI);

                                    EcoreUtil.resolveAll(targetResource);
                                    // EcoreUtil.UnresolvedProxyCrossReferencer.find(targetResource.getContents());
                                    if (targetResource != null && !(targetResource.getContents().isEmpty())
                                        && !saveResource.contains(targetResource)) {
                                        saveResource.add(targetResource);
                                    }

                                    // 선택한 노드의 부모 리소스 저장.
                                    if (eResource != null && !(eResource.getContents().isEmpty())
                                        && !saveResource.contains(eResource)) {
                                        saveResource.add(eResource);
                                    }

                                    EObject parentObject = sourceObject.eContainer();

                                    if (parentObject != null) {
                                        if (!parentObjectList.contains(parentObject)) {
                                            parentObjectList.add(parentObject);
                                        }
                                    }

                                    List<Resource> referenceResource = ResourceManager.getInstance()
                                        .inverseReferences(sourceObject);
                                    saveResource.addAll(referenceResource);
                                }

                                monitor.setTaskName(UMLMessage.LABEL_FILE_FRAGMENTATION);

                                // List<String> checkedFileList = new
                                // ArrayList<String>();
                                // // 리소스 파일의 텍스트 비교를 통해 참고관계를 찾는 로직
                                // for (Iterator<Resource> ir =
                                // resources.iterator(); ir.hasNext();) {
                                // Resource rs = ir.next();
                                //
                                // monitor.worked(1);
                                // if (rs != null &&
                                // ProjectUtil.isModelFile(rs)) {
                                //
                                // String uriString = rs.getURI().toString();
                                //
                                // final IFile file =
                                // WorkspaceSynchronizer.getFile(rs);
                                // if (file != null && file.exists()) {
                                // if
                                // (checkedFileList.contains(file.getFullPath().toString()))
                                // {
                                // continue;
                                // }
                                // checkedFileList.add(file.getFullPath().toString());
                                // if (!rs.isLoaded()) {
                                // try {
                                // rs.load(DomainModelHandlerUtil.getLoadOptions());
                                // EcoreUtil.UnresolvedProxyCrossReferencer.find(rs);
                                // } catch (IOException e) {
                                // e.printStackTrace();
                                // }
                                // }
                                // if
                                // (ResourceManager.getInstance().isRelated(rs,
                                // uriList.toArray(new URI[uriList.size()]))) {
                                // monitor.subTask(uriString);
                                //
                                // if (!saveResource.contains(rs)) {
                                // saveResource.add(rs);
                                // }
                                // }
                                // }
                                // }
                                // }

                                for (Iterator<Resource> ir = saveResource.iterator(); ir.hasNext();) {
                                    Resource rs = ir.next();
                                    EcoreUtil.resolveAll(rs);
                                    // EcoreUtil.UnresolvedProxyCrossReferencer.find(rs.getContents());
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                                monitor.done();
                                rollBack();
                                dialog.close();
                                done();
                            }
                        }
                    };

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

            if (!isContinue) {
                MessageDialog.openError(targetPart.getSite().getShell(),
                    UMLMessage.TITLE_ERROR/* "에러" */,
                    UMLMessage.MESSAGE_FILE_FRAGMENT_ERROR_MESSAGE/* "단편화 중 에러가 발생했습니다." */);
                return;
            }

            // 리소스 저장
            workspaceModifyOperation = new WorkspaceModifyOperation() {
                /**
                 * @see org.eclipse.ui.actions.WorkspaceModifyOperation#execute(org.eclipse.core.runtime.IProgressMonitor)
                 */
                @Override
                protected void execute(final IProgressMonitor monitor) throws CoreException, InvocationTargetException,
                InterruptedException {
                    monitor.beginTask(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING,
                        saveResource.size() + uriMap.size());
                    monitor.setTaskName(UMLMessage.TITLE_SAVE/* "리소스 저장" */);
                    monitor.subTask(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING);

                    for (Resource resource : saveResource) {
                        EcoreUtil.resolveAll(resource);
                        // EcoreUtil.UnresolvedProxyCrossReferencer.find(resource.getContents());
                        monitor.worked(1);
                        monitor.subTask(String.format("%s", resource.getURI()));
                        try {
                            DomainModelHandlerUtil.save(resource, true);
                        } catch (Exception e) {
                            e.printStackTrace();
                            done();
                            MessageDialog.openError(ProjectExplorerPlugin.getShell(),
                                UMLMessage.LABEL_FILE_FRAGMENTATION,
                                UMLMessage.MESSAGE_SAVE_FAIL + " : " + resource.getURI().toString());
                        }
                    }

                    monitor.subTask("");
                    for (URI uri : removeTreeNode) {
                        UMLTreeNodeRegistry.removeTreeNode(uri);
                    }

                    monitor.setTaskName(UMLMessage.TITLE_REFRESH/* "새로고침" */);
                    for (Iterator<String> iterator = uriMap.keySet().iterator(); iterator.hasNext();) {
                        monitor.worked(1);
                        final String uri = iterator.next();
                        final EObject sourceObject = uriMap.get(uri);
                        Display.getDefault().syncExec(new Runnable() {
                            public void run() {
                                TreeIterator<EObject> allContents = sourceObject.eAllContents();
                                while (allContents.hasNext()) {
                                    EObject content = allContents.next();
                                    UMLTreeNodeRegistry.removeTreeNode(content);
                                }

                                UMLTreeNodeRegistry.removeTreeNode(sourceObject);
                            }
                        });
                    }

                    for (EObject parentObject : parentObjectList) {
                        try {
                            ProjectUtil.refreshNodeInExplorer(parentObject);
                        } catch (Exception e) {
                            // ignore
                        }
                    }

                }
            };

            try {
                // 진행 모니터 다이얼로그 실행
                dialog.run(false, false, workspaceModifyOperation);
            } catch (InvocationTargetException ite) {
                ite.printStackTrace();
                ALMLogger.getLog(ProjectExplorerPlugin.PLUGIN_ID).error(ite.getMessage(), ite);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
                ALMLogger.getLog(ProjectExplorerPlugin.PLUGIN_ID).error(ie.getMessage(), ie);
            } finally {
                dialog.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            ALMLogger.getLog(ProjectExplorerPlugin.PLUGIN_ID).error(e.getMessage(), e);
        } finally {
            saveResource.clear();
            parentObjectList.clear();
            done();
        }
    }
    
    private void applyProfile(Package targetPackage) {
        CompoundCommand commands = new CompoundCommand();
        RecordingCommand command = null;

        EObject rootContainer = EcoreUtil.getRootContainer(targetPackage);
        
        if(rootContainer == null || !(rootContainer instanceof Model)) {
            return;
        }
        
        if(rootContainer instanceof Model) {
            EList<ProfileApplication> allProfileApplications = ((Model)rootContainer).getAllProfileApplications();
            for (ProfileApplication app : allProfileApplications) {
                command = new HandleProfileCommand(DomainRegistry.getEditingDomain(),
                    targetPackage,
                    app.getAppliedProfile(),
                    true);
                commands.append(command);
            }
        }
        
        DomainModelHandlerUtil.getEditingDomain().getCommandStack().execute(commands);
    }
}
