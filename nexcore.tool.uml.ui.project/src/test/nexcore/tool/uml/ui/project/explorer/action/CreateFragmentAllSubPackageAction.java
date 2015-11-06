/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.project.explorer.action;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import nexcore.alm.common.ui.util.ALMLogger;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.utility.DomainModelHandlerUtil;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.model.projectinformation.ProjectElement;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.project.ITreeNode;
import nexcore.tool.uml.ui.core.registry.ResourceManager;
import nexcore.tool.uml.ui.core.util.ProjectUtil;
import nexcore.tool.uml.ui.project.ProjectExplorerPlugin;

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
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
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
public class CreateFragmentAllSubPackageAction extends BaseMultiFragmentAction {
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
    
    public synchronized void doSave(IProgressMonitor monitor) throws CoreException {
        ResourceSetImpl resourceSet = (ResourceSetImpl) DomainRegistry.getUMLDomain().getResourceSet();
        Map<URI, Resource> uriResourceMap = resourceSet.getURIResourceMap();
        
        Set<Resource> resourceList = new HashSet<Resource>();
        
        for (Iterator<?> iterator = uriResourceMap.values().iterator(); iterator.hasNext();) {
            Resource resource = (Resource) iterator.next();
            
            if(ProjectUtil.isModelFile(resource)) {
                if(resource.isModified()) {
                    resourceList.add(resource);
                }
            }
        }
        
        final Set<Resource> modifiedResource = resourceList;
        
        Display.getDefault().syncExec(new Runnable() {
            
            @Override
            public void run() {
                ProgressMonitorDialog dialog = new ProgressMonitorDialog(targetPart.getSite().getShell());
                try {
                    dialog.run(true, false, new IRunnableWithProgress() {
                        
                        @Override
                        public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                            monitor.beginTask("Save", modifiedResource.size());
                            for (Resource r : modifiedResource) {
                                monitor.worked(1);

                                try {
                                    monitor.subTask(r.getURI().toString());
                                    DomainModelHandlerUtil.save(r, false);
                                } catch (Exception e) {
                                    ALMLogger.getLog(ProjectExplorerPlugin.PLUGIN_ID).error(e.getMessage(), e);
                                }
                            }
                            
                            monitor.done();
                        }
                        
                    });
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } 
            }
        });
    }

    @Override
    public void run(IAction action) {
        final StructuredSelection ss = ((StructuredSelection) selection);
        if (ss.isEmpty()) {
            return;
        }
        
        start();
        
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
                            monitor.beginTask("Create fragment", 150);

                            saveResource = new HashSet<Resource>();
                            
                            ////////////////
                            ResourceSetImpl resourceSet = (ResourceSetImpl) DomainRegistry.getUMLDomain().getResourceSet();
                            Map<URI, Resource> uriResourceMap = resourceSet.getURIResourceMap();
                            
                            Set<Resource> modifiedResource = new HashSet<Resource>();
                            
                            for (Iterator<?> iterator = uriResourceMap.values().iterator(); iterator.hasNext();) {
                                Resource resource = (Resource) iterator.next();
                                
                                if(ProjectUtil.isModelFile(resource)) {
                                    if(resource.isModified()) {
                                        modifiedResource.add(resource);
                                    }
                                }
                            }
                            
                            SubProgressMonitor subMonitor = new SubProgressMonitor(monitor, 20);
                            subMonitor.beginTask("Save", modifiedResource.size());
                            for (Resource r : modifiedResource) {
                                subMonitor.worked(1);

                                try {
                                    subMonitor.subTask(r.getURI().toString());
                                    System.out.println("------------------------>"+r.getURI().toString());
                                    DomainModelHandlerUtil.save(r, false);
                                } catch (Exception e) {
                                    ALMLogger.getLog(ProjectExplorerPlugin.PLUGIN_ID).error(e.getMessage(), e);
                                }
                            }
                            
                            subMonitor.done();
                            
                            ///////////////
                            closeEditor();
                            ///////////////

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
                                            continue;
                                        }
                                        packageList.put(pkg.getQualifiedName(), pkg);
                                    }

                                    // model 이 Model 이 아닌 경우 즉 Package 인 경우
                                    // model 도 단편 대상이므로 리스트에 추가
                                    if (!(selectedPackage instanceof Model)) {
                                        packageList.put(selectedPackage.getQualifiedName(), selectedPackage);
                                    }
                                }
                            }

                            subMonitor = new SubProgressMonitor(monitor, 100);
                            subMonitor.beginTask(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING, packageList.size());
                            for (Iterator<String> ir = packageList.descendingKeySet().iterator(); ir.hasNext();) {
                                subMonitor.worked(1);
                                String qualifiedName = (String) ir.next();

                                subMonitor.subTask(qualifiedName);
                                createFragment(packageList.get(qualifiedName), subMonitor);
                            }

                            subMonitor.done();

                            subMonitor = new SubProgressMonitor(monitor, 10);
                            subMonitor.beginTask("Resolve all resource", saveResource.size());
                            for (Iterator<Resource> ir = saveResource.iterator(); ir.hasNext();) {
                                subMonitor.worked(1);
                                Resource resource = ir.next();
                                subMonitor.subTask(String.format("save : %s", resource.getURI()));

                                EcoreUtil.resolveAll(resource);
                            }

                            subMonitor.done();

                            subMonitor = new SubProgressMonitor(monitor, 20);
                            subMonitor.beginTask(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING, saveResource.size());
                            subMonitor.setTaskName(UMLMessage.TITLE_SAVE);
                            for (Resource resource : saveResource) {
                                subMonitor.worked(1);
                                subMonitor.subTask(String.format("%s", resource.getURI()));
                                try {
                                    DomainModelHandlerUtil.save(resource, true);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                } finally {
                                    System.out.println(String.format("saved : %s", resource.getURI()));
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
                } finally {
                    saveResource.clear();
                    done();
                }
            }
        });
    }
}
