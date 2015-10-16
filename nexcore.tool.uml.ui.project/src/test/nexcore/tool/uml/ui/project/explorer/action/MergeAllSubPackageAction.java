/*
 * Copyright (c) 2012 SK C&C Co., Ltd. All rights reserved.
 * 
 * This software is the confidential and proprietary information of SK C&C. You
 * shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK
 * C&C.
 */
package nexcore.tool.uml.ui.project.explorer.action;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.utility.DomainModelHandlerUtil;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.model.projectinformation.ProjectElement;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.project.ITreeNode;
import nexcore.tool.uml.ui.core.registry.ProjectRegistry.UMLTreeNodeRegistry;
import nexcore.tool.uml.ui.core.registry.ProjectRegistry.ViewerRegistry;
import nexcore.tool.uml.ui.core.registry.ResourceManager;
import nexcore.tool.uml.ui.core.registry.ResourceUnloader;
import nexcore.tool.uml.ui.core.util.ProjectUtil;
import nexcore.tool.uml.ui.core.util.TreeItemUtil;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.query.conditions.eobjects.EObjectTypeRelationCondition;
import org.eclipse.emf.query.statements.FROM;
import org.eclipse.emf.query.statements.IQueryResult;
import org.eclipse.emf.query.statements.SELECT;
import org.eclipse.emf.query.statements.WHERE;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.transaction.TransactionalCommandStack;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.ProfileApplication;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.action</li>
 * <li>설 명 : CreateFragmentAllSubPackageAction</li>
 * <li>작성일 : 2012. 8. 21.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public class MergeAllSubPackageAction extends BaseMultiFragmentAction {
    /**
     * saveResource
     */
    Set<Resource> saveResource = new HashSet<Resource>();

    /**
     * createFragment
     * 
     * @param parent
     * @param monitor
     *            void
     */
    public void createFragment(final Package parent, final IProgressMonitor monitor) {
        RecordingCommand command = new RecordingCommand(DomainRegistry.getEditingDomain()) {
            @Override
            protected void doExecute() {
                try {
                    final EObject sourceObject = parent;

                    if (sourceObject == null) {
                        return;
                    }

                    String qualifiedName = ((Package) sourceObject).getQualifiedName();
                    monitor.subTask(String.format("%s: %s", UMLMessage.LABEL_PACKAGE_QUALIFIEDNAME, qualifiedName));

                    String uriPath = null;
                    uriPath = ProjectUtil.generatePackageUriPath(null, (Package) sourceObject);

                    URI uri = URI.createURI(uriPath);
                    Resource sourceResource = sourceObject.eResource();

                    Resource targetResource = null;
                    try {
                        targetResource = DomainModelHandlerUtil.createResource(uri);
                    } catch (Exception e1) {
                        if (!new Path(uri.toString()).toFile().exists()) {
                            ((ResourceSetImpl) DomainRegistry.getEditingDomain().getResourceSet()).getURIResourceMap()
                                .remove(uri);
                            targetResource = DomainModelHandlerUtil.createResource(uri);
                        }

                        targetResource = DomainRegistry.getEditingDomain().getResourceSet().getResource(uri, true);
                        targetResource.getContents().clear();
                    }
                    if (targetResource == null) {
                        targetResource = DomainRegistry.getEditingDomain().getResourceSet().createResource(uri);
                    }

                    if (((ResourceImpl) targetResource).getIntrinsicIDToEObjectMap() == null) {
                        ((ResourceImpl) targetResource).setIntrinsicIDToEObjectMap(new HashMap<String, EObject>());
                    }
                    if (!targetResource.isTrackingModification()) {
                        targetResource.setTrackingModification(true);
                    }

                    targetResource.getContents().add(sourceObject);

                    // 단편 패키지에 모델에 적용된 프로파일 적용.
                    EObject rootContainer = EcoreUtil.getRootContainer((Package) sourceObject);

                    if (rootContainer != null && rootContainer instanceof Model) {
                        EList<ProfileApplication> allProfileApplications = ((Model) rootContainer).getAllProfileApplications();
                        for (ProfileApplication app : allProfileApplications) {
                            ((Package) sourceObject).applyProfile(app.getAppliedProfile());
                        }
                    }

                    // 적용된 stereoType 정보 옮겨주기
                    targetResource.getContents().addAll(ProjectUtil.getStereotypesForFragment(sourceResource,
                        sourceObject,
                        false));

                    if (targetResource != null && !(targetResource.getContents().isEmpty())) {
                        saveResource.add(targetResource);
                    }

                    if (sourceResource != null && !(sourceResource.getContents().isEmpty())) {
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

                        if (projectInfo != null) {
                            ((Package) sourceObject).getEAnnotations()
                                .remove(((Package) sourceObject).getEAnnotation("Project"));
                            ((Package) sourceObject).getEAnnotations().add(0, projectInfo);
                        }
                    }

                    Resource eResource = sourceObject.eContainer().eResource();

                    // 선택한 노드의 부모 리소스 저장.
                    if (eResource != null && !(eResource.getContents().isEmpty())) {
                        saveResource.add(eResource);
                    }

                    List<Resource> referenceResource = ResourceManager.getInstance().inverseReferences(sourceObject);
                    saveResource.addAll(referenceResource);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        TransactionalEditingDomain editingDomain = DomainModelHandlerUtil.getEditingDomain();
        TransactionalCommandStack tstack = (TransactionalCommandStack) editingDomain.getCommandStack();
        try {
            tstack.execute(command, Collections.singletonMap(Transaction.OPTION_NO_NOTIFICATIONS, Boolean.TRUE));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * parentObjectList
     */
    List<EObject> parentObjectList = null;

    /**
     * removeResourceList
     */
    List<Resource> removeResourceList = null;

    /**
     * deleteFileList
     */
    List<Resource> deleteFileList = null;

    private void mergeFragment(final Package eobject, final IProgressMonitor monitor) {
        RecordingCommand command = new RecordingCommand(DomainModelHandlerUtil.getEditingDomain()) {

            @Override
            protected void doExecute() {
//                EList<Resource> resources = DomainRegistry.getUMLDomain().getResourceSet().getResources();
//                monitor.beginTask(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING, objects.size() + resources.size());
                monitor.setTaskName(UMLMessage.LABEL_FILE_DEFRAGMENTATION);

                Resource sourceResource = eobject.eResource();
                org.eclipse.uml2.uml.Package source = (org.eclipse.uml2.uml.Package) eobject;
                monitor.subTask(String.format("%s: %s",
                    UMLMessage.LABEL_PACKAGE_QUALIFIEDNAME,
                    source.getQualifiedName()));

                // 단편된 파일에서 부모에 대한 Reference 정보 삭제.
                EAnnotation sourceFragmentContainerAnnotation = ProjectUtil.getFragmentContainerAnnotation(source);
                if (sourceFragmentContainerAnnotation.getReferences() != null) {
                    sourceFragmentContainerAnnotation.getReferences().remove(eobject.eContainer());
                }

                // 단편된 패키지에서 프로파일 정보 삭제
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
                    saveResource.addAll(referenceResource);

                    referenceResource = ResourceManager.getInstance().inverseReferences(eobject.eContainer());
                    saveResource.addAll(referenceResource);

                    // for (Iterator<Resource> ir = saveResource.iterator();
                    // ir.hasNext();) {
                    // Resource rs = ir.next();
                    // EcoreUtil.resolveAll(rs);
                    // }

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

                    eobject.eResource()
                        .getContents()
                        .addAll(ProjectUtil.getStereotypesForFragment(sourceResource, eobject, true));

                    EObject parentObject = eobject.eContainer();

                    if (parentObject != null) {
                        if (!parentObjectList.contains(parentObject)) {
                            parentObjectList.add(parentObject);
                        }
                    }

                    IFile file = WorkspaceSynchronizer.getFile(sourceResource);
                    if (!deleteFileList.contains(file)) {
                        deleteFileList.add(sourceResource);
                    }

                    if (!removeResourceList.contains(sourceResource)) {
                        removeResourceList.add(sourceResource);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    done();
                }
            }
        };

        TransactionalEditingDomain editingDomain = DomainModelHandlerUtil.getEditingDomain();
        TransactionalCommandStack tstack = (TransactionalCommandStack) editingDomain.getCommandStack();
        try {
            tstack.execute(command, Collections.singletonMap(Transaction.OPTION_NO_NOTIFICATIONS, Boolean.TRUE));
        } catch (Exception e) {
            e.printStackTrace();
        }

        monitor.done();

    }

    @Override
    public void run(IAction action) {
        final StructuredSelection ss = ((StructuredSelection) selection);
        if (ss.isEmpty()) {
            return;
        }

        if (!MessageDialog.openConfirm(UiCorePlugin.getShell(),
            UMLMessage.getMessage(UMLMessage.LABEL_FILE_DEFRAGMENTATION),
            UMLMessage.getMessage(UMLMessage.MESSAGE_CONFIRM_MERGE))) {
            return;
        }

        parentObjectList = new ArrayList<EObject>();
        removeResourceList = new ArrayList<Resource>();
        deleteFileList = new ArrayList<Resource>();
        saveResource = new HashSet<Resource>();

        CommonViewer commonViewer = ViewerRegistry.getViewer();

        expanedTreePaths = TreeItemUtil.getExpandTreePaths(commonViewer.getTree()).clone();

        Display.getDefault().syncExec(new Runnable() {

            @Override
            public void run() {
                dialog = new ProgressMonitorDialog(targetPart.getSite().getShell());
                try {

                    WorkspaceModifyOperation workspaceModifyOperation = new WorkspaceModifyOperation() {
                        /**
                         * @see org.eclipse.ui.actions.WorkspaceModifyOperation#execute(org.eclipse.core.runtime.IProgressMonitor)
                         */
                        @Override
                        protected void execute(final IProgressMonitor monitor) throws CoreException,
                        InvocationTargetException, InterruptedException {
                            monitor.beginTask("Merge fragment", 140);

                            TreeMap<String, Package> packageList = new TreeMap<String, Package>();
                            for (Iterator<?> iterator = ss.iterator(); iterator.hasNext();) {
                                Object obj = (Object) iterator.next();

                                if (obj instanceof ITreeNode) {
                                    ITreeNode node = (ITreeNode) obj;
                                    EObject eObject = node.getEObject();

                                    Package selectedPackage = null;
                                    if (eObject instanceof Model) {
                                        selectedPackage = (Model) eObject;
                                    } else if (eObject instanceof Package) {
                                        selectedPackage = (Package) eObject;
                                    }

                                    if (packageList.containsKey(selectedPackage.getQualifiedName())) {
                                        continue;
                                    }

                                    SELECT statement = new SELECT(new FROM(selectedPackage),
                                        new WHERE(new EObjectTypeRelationCondition(UMLPackage.eINSTANCE.getPackage())));
                                    IQueryResult result = statement.execute();

                                    for (Iterator<?> ir = result.iterator(); ir.hasNext();) {
                                        Package pkg = (Package) ir.next();

                                        if (pkg == null || pkg.getQualifiedName() == null) {
                                            continue;
                                        }

                                        if (AdapterFactoryEditingDomain.isControlled(pkg)) {
                                            packageList.put(pkg.getQualifiedName(), pkg);
                                        }
                                    }

                                    // model 이 Model 이 아닌 경우 즉 Package 인 경우
                                    // model 도 병합 대상이므로 리스트에 추가
                                    if (!(selectedPackage instanceof Model)) {
                                        packageList.put(selectedPackage.getQualifiedName(), selectedPackage);
                                    }
                                }
                            }

                            SubProgressMonitor subMonitor = new SubProgressMonitor(monitor, 100);
                            subMonitor.beginTask(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING, packageList.size());
                            for (Iterator<String> ir = packageList.descendingKeySet().iterator(); ir.hasNext();) {
                                subMonitor.worked(1);
                                String qualifiedName = (String) ir.next();

                                subMonitor.subTask(qualifiedName);
                                
                                Package eobject = packageList.get(qualifiedName);
                                if (!(AdapterFactoryEditingDomain.isControlled(eobject))) {
                                    subMonitor.worked(1);
                                    continue;
                                }
                                
                                mergeFragment(eobject, subMonitor);
                            }

                            subMonitor.done();

                            subMonitor = new SubProgressMonitor(monitor, 20);
                            subMonitor.beginTask(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING, saveResource.size());
                            subMonitor.setTaskName(UMLMessage.TITLE_SAVE);
                            for (Resource resource : saveResource) {
                                subMonitor.worked(1);
                                subMonitor.subTask(String.format("%s", resource.getURI()));
                                try {
                                    IFile file = WorkspaceSynchronizer.getFile(resource);
                                    if(file == null || !file.exists()) {
                                        continue;
                                    }
                                    DomainModelHandlerUtil.save(resource, true);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                } finally {
                                    System.out.println(String.format("saved : %s", resource.getURI()));
                                }
                            }

                            for (URI uri : removeTreeNode) {
                                UMLTreeNodeRegistry.removeTreeNode(uri);
                            }

                            for (Resource sourceResource : removeResourceList) {
                                ResourceUnloader.getInstance().put(sourceResource);
                            }

                            subMonitor = new SubProgressMonitor(monitor, 10);
                            subMonitor.beginTask(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING, deleteFileList.size());
                            subMonitor.setTaskName(UMLMessage.TITLE_DELETE_FILE/* "단편 파일 삭제" */);

                            try {
                                for (Resource resource : deleteFileList) {
                                    IFile file = WorkspaceSynchronizer.getFile(resource);
                                    if( file == null) {
                                        continue;
                                    }
                                    subMonitor.subTask(String.format("%s", file.getFullPath().toString()));

                                    URI uri = URI.createURI(file.getFullPath().toString());
                                    Map<URI, Resource> uriResourceMap = ((ResourceSetImpl) DomainRegistry.getUMLDomain()
                                        .getResourceSet()).getURIResourceMap();
                                    if (uriResourceMap.containsKey(uri)) {
                                        uriResourceMap.remove(uri);
                                    }

//                                    file.delete(false, new NullProgressMonitor());
                                    resource.delete(Collections.emptyMap());
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }

                            subMonitor = new SubProgressMonitor(monitor, 10);
                            subMonitor.beginTask(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING, parentObjectList.size());
                            subMonitor.setTaskName(UMLMessage.TITLE_REFRESH);
                            subMonitor.subTask(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING);
                            for (EObject parentObject : parentObjectList) {
                                subMonitor.worked(1);
                                try {
                                    ProjectUtil.refreshNodeInExplorer(parentObject);
                                } catch (Exception e) {
                                    // ignore
                                }
                            }

                            subMonitor.done();

                            monitor.done();
                        }

                    };

                    dialog.run(true, false, workspaceModifyOperation);
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
