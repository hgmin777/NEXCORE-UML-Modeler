/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.core.registry;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nexcore.alm.common.ui.util.ALMLogger;
import nexcore.tool.uml.connector.UMLCacheManager;
import nexcore.tool.uml.core.UMLDebug;
import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.core.util.DateUtil;
import nexcore.tool.uml.core.util.UMLLoginController;
import nexcore.tool.uml.core.util.UMLNexcoreLoginEvent;
import nexcore.tool.uml.core.util.UMLNexcoreLoginListener;
import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.manager.extension.registry.PrecedingInitializerRegistry;
import nexcore.tool.uml.manager.transaction.TransactionalAction;
import nexcore.tool.uml.manager.utility.DomainModelHandlerUtil;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.project.ITreeNode;
import nexcore.tool.uml.ui.core.property.util.ModelHandler;
import nexcore.tool.uml.ui.core.registry.ProjectRegistry.UMLTreeNodeRegistry;
import nexcore.tool.uml.ui.core.registry.ProjectRegistry.ViewerRegistry;
import nexcore.tool.uml.ui.core.util.PreferenceUtil;
import nexcore.tool.uml.ui.core.util.ProjectUtil;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IExecutionListener;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.ISaveablePart2;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.XMLMemento;
import org.eclipse.ui.actions.OpenResourceAction;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.uml2.common.util.CacheAdapter;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;

/**
 * 
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.registry</li>
 * <li>설 명 : ResourceManager</li>
 * <li>작성일 : 2011. 4. 6.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public class ResourceManager {

    /**
     * resourceManager
     */
    private static ResourceManager resourceManager;

    /**
     * allElementList
     */
    static Set<Element> allElementList = Collections.synchronizedSet(new HashSet<Element>());

    /**
     * openProject
     */
    static Set<IProject> openProject = Collections.synchronizedSet(new HashSet<IProject>());

//    static Set<IProject> referencedProject = Collections.synchronizedSet(new HashSet<IProject>());
    /**
     * referencedProject
     */
    static Map<IProject, Boolean> referencedProject = Collections.synchronizedMap(new HashMap<IProject, Boolean>());

    /**
     * isChecked
     */
    private static boolean isChecked = true;

    /**
     * PROJECT_OPEN_IMPORT
     */
    private static boolean PROJECT_OPEN_IMPORT = false;

    /**
     * getInstance
     *  
     * @return ResourceManager
     */
    public static ResourceManager getInstance() {
        if (resourceManager == null) {
            resourceManager = new ResourceManager();

            resourceManager.addUMLResourceChangeListener();
            
            resourceManager.loadOpenedResources();
        }

        return resourceManager;
    }
    
    /**
     * distroy
     *   void
     */
    public void distroy() {
        saveOpenedResources();
    }
    
    /**
     * saveOpenedResources
     *   void
     */
    private void saveOpenedResources() {

        // memento save
        XMLMemento rootMemento = XMLMemento.createWriteRoot(ROOT_ELEMENT);
        
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        IProject[] projects = root.getProjects();
        
        Set<URI> uriSet = new HashSet<URI>();
        for(IProject project : projects) {
            try {
                if (!ProjectUtil.isActiveUMLProject(project)) {
                    continue;
                }
                uriSet = new HashSet<URI>();
                for (IResource resource : project.members()) {
                    findUMXResource(resource, uriSet);
                }
                if (!uriSet.isEmpty()) {
                    IMemento projectmem = rootMemento.createChild(PROJECT_ELEMENT);
                    projectmem.putString(NAME_ATTRIBUTE, project.getName());
                    for (Iterator<URI> iterator = uriSet.iterator(); iterator.hasNext();) {
                        URI uri = (URI) iterator.next();
                        IMemento resourcemem = projectmem.createChild(RESOURCE_ELEMENT);
                        resourcemem.putString(URI_ATTRIBUTE, uri.toString());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // file save
        FileWriter writer = null;
        try {
            File file = new File(MEMENTO_PATH);
            writer = new FileWriter(file);
            rootMemento.save(writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void findUMXResource(IResource resource, Set<URI> uriSet) {
        try {
            if (resource instanceof IFolder) {
                for (IResource member : ((IFolder) resource).members()) {
                    findUMXResource(member, uriSet);
                }
            } else if (resource instanceof IFile) {
                if (DomainUtil.isUMXFile((IFile) resource)) {
                    URI uri = URI.createURI(((IFile) resource).getFullPath().toString());
                    boolean active = isActive(uri);
                    if (active) {
                        uriSet.add(uri);
                    }
                }
            }
        } catch (CoreException e) {
            e.printStackTrace();
        }
    }

    private static final String MEMENTO_PATH = System.getProperty("user.home")+"/.uml";
    private static final String RESOURCE_ELEMENT = "resource";
    private static final String ROOT_ELEMENT = "resources";
    private static final String URI_ATTRIBUTE = "uri";
    private static final String PROJECT_ELEMENT = "project";
    private static final String NAME_ATTRIBUTE = "name";
    
    private static Map<String, List<String>> RESOURCE_MEMENTO = new HashMap<String, List<String>>();
    
    private void loadOpenedResources() {
        XMLMemento rootMemento = null;
        FileReader reader = null;
        try {
            // Preference에서 저장한 Server 정보 로딩
            File file = new File(MEMENTO_PATH);

            if (file.exists()) {
                reader = new FileReader(file);
                try {
                    rootMemento = XMLMemento.createReadRoot(reader);
                } catch (WorkbenchException e) {
                    e.printStackTrace();
                }

                IMemento[] projectMementos = rootMemento.getChildren(PROJECT_ELEMENT);
                
                for(IMemento projectMemento : projectMementos) {
                    IMemento[] resourceMemento = projectMemento.getChildren(RESOURCE_ELEMENT);
                    ArrayList<String> resourceList = new ArrayList<String>();
                    for (IMemento mem : resourceMemento) {
                        resourceList.add(mem.getString(URI_ATTRIBUTE));
                    }
                    RESOURCE_MEMENTO.put(projectMemento.getString(NAME_ATTRIBUTE), resourceList);
                }
                
            } else {
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
    
    /**
     * ResourceManager
     */
    private ResourceManager() {

        final UMLLoginController loginController = UMLLoginController.getInstance();

        addLoginListener();

        addExecutionListener(loginController);

        String auto = System.getProperty("uml.all.access.mode");
        
//        if (auto != null && Boolean.parseBoolean(auto)) {
//            Job job = new Job("login") {
//
//                @Override
//                protected IStatus run(IProgressMonitor monitor) {
//                    LoginEvent event = new LoginEvent(true);
//                    LoginManager.getInstance().fireLoginModified(event);
//                    return Status.OK_STATUS;
//                }
//
//            };
//
//            job.schedule(7000);
//        }
    }

    /**
     * Command 실행 전/후 처리
     * 
     *  
     * @param loginController void
     */
    private void addExecutionListener(final UMLLoginController loginController) {
        Object adapter = PlatformUI.getWorkbench().getAdapter(ICommandService.class);

        if (adapter instanceof ICommandService) {
            ((ICommandService) adapter).addExecutionListener(new IExecutionListener() {
                public void postExecuteSuccess(String commandId, Object returnValue) {
                    if ("org.eclipse.ui.file.import".equals(commandId)) {
                        if (!loginController.isLogin()) {
                            return;
                        }
                        if (returnValue instanceof Boolean && ((Boolean) returnValue).booleanValue()) {
                            // import success
                            if (openProject.size() > 0) {
                                try {
                                    // import 후 리소스가 올라오는 시간 텀이 생겨 대기시간 강제 설정
                                    Thread.sleep(2000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                initializeModelingData(openProject.toArray(new IProject[] {}), true);
                            }
                            PROJECT_OPEN_IMPORT = false;
                            openProject.clear();
                        }
                    }
                }

                public void preExecute(String commandId, ExecutionEvent event) {
                    if ("org.eclipse.ui.file.import".equals(commandId)) {
                        openProject.clear();
                        PROJECT_OPEN_IMPORT = true;
                    }
                }

                public void notHandled(String commandId, NotHandledException exception) {

                }

                public void postExecuteFailure(String commandId, ExecutionException exception) {

                }

            });
        }
    }
    
    CacheAdapter resourceLoadedAdapter = null;
    
    public void addResourceLoadedAdapter() {
        EList<Adapter> cacheAdapters = DomainRegistry.getUMLDomain().getResourceSet().eAdapters();
        if (resourceLoadedAdapter == null) {
            resourceLoadedAdapter = new CacheAdapter() {
                @Override
                public void notifyChanged(Notification msg) {
                    super.notifyChanged(msg);
                    final Object notifier = msg.getNotifier();
                    if (notifier instanceof Resource) {
                        Resource r = (Resource) notifier;
                        switch (msg.getFeatureID(Resource.class)) {
                            case Resource.RESOURCE__IS_LOADED: {
                                if (UMLLoginController.getInstance().getLoginState() == UMLNexcoreLoginEvent.LOG_IN) {
                                    if (ProjectUtil.isModelFile(r)) {
                                        if (!ResourceManager.getInstance().isActive(r.getURI()) && r.isLoaded()) {
                                            ResourceActivator.getInstance().put(r.getURI());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            };
        }
        
        if (!cacheAdapters.contains(resourceLoadedAdapter)) {
            cacheAdapters.add(resourceLoadedAdapter);
        }
    }
    
    public void removeResourceLoadedAdapter() {
        EList<Adapter> cacheAdapters = DomainRegistry.getUMLDomain().getResourceSet().eAdapters();
        cacheAdapters.remove(resourceLoadedAdapter);
    }

    /**
     * 
     * 
     *   void
     */
    private void addUMLResourceChangeListener() {
        UMLResourceChangeManager rcm = UMLResourceChangeManager.getInstance();

        rcm.addResourceChangeListener(new UMLResourceChangeAdapter() {
            public void preProjectClose(IResourceChangeEvent event) {
                preProjectDelete(event);
            }

            public void preProjectDelete(final IResourceChangeEvent event) {
                // 프로젝트가 닫힌 경우
                Display.getDefault().syncExec(new Runnable() {
                    public void run() {
                        try {
                            ProjectUtil.closeEditor((IProject) event.getResource());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        List<Resource> saveableList = hasModifiedResource(new IResource[] { (IProject) event.getResource() });
                        boolean isSave = false;
                        int choice = IDialogConstants.NO_ID;
                        if (saveableList.size() > 0) {
                            final boolean canCancel = true;
                            String[] buttons;
                            buttons = new String[] { IDialogConstants.YES_LABEL, IDialogConstants.NO_LABEL,
                                IDialogConstants.CANCEL_LABEL };

                            String message = String.format("'%s' has been modified. Save changes?", saveableList.get(0)
                                .getURI());
                            ;
                            MessageDialog dialog = new MessageDialog(getShell(),
                                "Save Resource",
                                null,
                                message,
                                MessageDialog.QUESTION,
                                buttons,
                                0) {
                                protected int getShellStyle() {
                                    return (canCancel ? SWT.CLOSE : SWT.NONE) | SWT.TITLE | SWT.BORDER
                                        | SWT.APPLICATION_MODAL | getDefaultOrientation();
                                }
                            };

                            choice = dialog.open();

                            switch (choice) {
                                case ISaveablePart2.YES:
                                    isSave = true;
                                    cleanResource(new NullProgressMonitor(),
                                        new IResource[] { (IProject) event.getResource() },
                                        isSave);
                                    break;
                                case ISaveablePart2.NO:
                                    isSave = false;
                                    cleanResource(new NullProgressMonitor(),
                                        new IResource[] { (IProject) event.getResource() },
                                        isSave);
                                    break;
                                case ISaveablePart2.CANCEL:
                                    break;
                                default:
                                    break;
                            }
                        } else {
                            cleanResource(new NullProgressMonitor(),
                                new IResource[] { (IProject) event.getResource() },
                                isSave);
                        }
                        ProjectUtil.refreshExplorer();
//                        IProject[] activeUMLProjects = ProjectUtil.getActiveUMLProjects();
//                        
//                        for(IProject project : activeUMLProjects) {
//                            try {
//                                project.refreshLocal(IResource.DEPTH_ZERO, new NullProgressMonitor());
//                            } catch (CoreException e) {
//                                e.printStackTrace();
//                            }
//                        }
                    }
                });
                
            }

            public void projectOpened(IProject[] projects) {
                if (PROJECT_OPEN_IMPORT) { // import 로 프로젝트 오픈 될 때
                    for(IProject project : projects) {
                        openProject.add(project);
                    }
                    return;
                }

                for(IProject project : projects) {
                    if (referencedProject.containsKey(project)) {
                        referencedProject.put(project, Boolean.TRUE);
                        
                        IProject[] refProjects = referencedProject.keySet().toArray(new IProject[0]);
                        boolean isOpened = allOpened();
                        if( isOpened ) {
                            initializeModelingData(refProjects, false);
                            referencedProject.clear();
                        }
                        return;
                    }
                }
                initializeModelingData(projects, false);
            }
            
            public boolean allOpened() {
                for (Iterator<IProject> iterator = referencedProject.keySet().iterator(); iterator.hasNext();) {
                    IProject project = (IProject) iterator.next();
                    if( !referencedProject.get(project) ){
                        return false;
                    }
                }
                
                return true;
            }

        });
        
//        UMLResourceChangeAdapter listener = new UMLResourceChangeAdapter() {
//            @Override
//            public void projectOpened(IProject[] projects) {
//                for (IProject project : projects) {
////                    referencedProject.remove(project);
//                }
//            }
//        };
//        rcm.addResourceChangeListener(listener);
    }

    /**
     * 
     * 
     *   void
     */
    public void clearUnusedResource() {
        Resource nextResource;
        ResourceSet resourceSet = DomainRegistry.getUMLDomain().getResourceSet();

        EList<Resource> list = resourceSet.getResources();
        List<Resource> hostList = new ArrayList<Resource>();

        for (int i = 0; i < list.size(); i++) {
            nextResource = list.get(i);
            try {
                if (ProjectUtil.isModelFile(nextResource)) {
                    hostList.add(nextResource);

                    IFile file = WorkspaceSynchronizer.getFile(nextResource);
                    if (file != null && !file.exists()) {
//                        removeResource(nextResource);
                        ResourceUnloader.getInstance().put(nextResource);
                    }
                }
            } catch (Exception e) {
                // ignore
            }
        }
    }

    /**
     * 
     * 
     *  
     * @param file
     * @param obj
     * @return org.eclipse.uml2.uml.Package
     */
    public org.eclipse.uml2.uml.Package resolveResource(IFile file, EObject obj) {
        if (DomainUtil.isProxy(obj)) {
            URI resUri = URI.createURI(file.getFullPath().toString());
            Resource resource = DomainRegistry.getUMLDomain().getResource(resUri, true);
            if (resource!= null && resource.getContents().size() > 0) {
                obj = (org.eclipse.uml2.uml.Package) resource.getContents().get(0);
            }
        }

        return (Package) obj;
    }

    /**
     * 
     * 
     *  
     * @param eobject
     * @return EAnnotation
     */
    public EAnnotation getFragmentAnnotation(EObject eobject) {
        if (eobject instanceof org.eclipse.uml2.uml.Package) {
            org.eclipse.uml2.uml.Package myPackage = (org.eclipse.uml2.uml.Package) eobject;
            EAnnotation eAnnotation = myPackage.getEAnnotation(UICoreConstant.PROJECT_CONSTANTS__FRAGMENT);
            return eAnnotation;
        }
        return null;
    }

    /**
     * 
     * 
     *  
     * @param parentResource void
     */
    public void removeFragmentResource(Resource parentResource) {
        EList<EObject> contents = parentResource.getContents();
        if (contents != null && contents.size() > 0) {
            EObject eobject = contents.get(0);

            if (!AdapterFactoryEditingDomain.isControlled(eobject)) {
                EAnnotation eAnnotation = getFragmentAnnotation(eobject);
                if (eAnnotation == null)
                    return;
                EList<EObject> references = eAnnotation.getReferences();
                for (EObject reference : references) {
                    if (reference != null && reference.eResource() != null) {
//                        removeResource(reference.eResource());
                        ResourceUnloader.getInstance().put(reference.eResource());
                    }
                }
            }
        }
    }

    /**
     * 
     * 
     *  
     * @param resource void
     */
    public void removeResource(final Resource resource) {
        if (resource == null)
            return;

        Map<String, ITreeNode> treeNodes = ProjectRegistry.UMLTreeNodeRegistry.getTreeNodes();
        List<Object> cleanTarget = new ArrayList<Object>();
        URI uri = resource.getURI();

        for (Iterator<String> iterator = treeNodes.keySet().iterator(); iterator.hasNext();) {
            String uriKey = (String) iterator.next();
            if (uriKey.indexOf(uri.toString()) > -1) {
                cleanTarget.add(uriKey);
            }
        }

        // UMLTreeNodeRegistry.getTreeNodes() 에서 관련된 키 삭제
        for (Object o : cleanTarget) {
            ProjectRegistry.UMLTreeNodeRegistry.removeTreeNode(o);
        }

        try {
            if (resource.isModified()) {
                DomainModelHandlerUtil.save(resource, false);
            }

//            ResourceSet resourceSet = DomainRegistry.getUMLDomain().getResourceSet();

            uri = resource.getURI();
            ProjectRegistry.UMLTreeNodeRegistry.removeTreeNode(uri);

//            CacheAdapter.INSTANCE.clear(resource);
//            resource.unload();
//            if (resourceSet != null) {
//                resourceSet.getResources().remove(resource);
//            }
            
            DomainUtil.run(new TransactionalAction() {
                
                @Override
                public void doExecute() {
                    try {
                        ResourceSet resourceSet = DomainRegistry.getUMLDomain().getResourceSet();
                        if (resourceSet != null) {
                            resourceSet.getResources().remove(resource);
                        }
                        CacheAdapter.INSTANCE.clear(resource);
                        resource.unload();
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                        ALMLogger.getLog(UiCorePlugin.PLUGIN_ID).error(e.getMessage(), e);
                    }
                }
                
            });
        } catch (Exception e) {
            e.printStackTrace();
            ALMLogger.getLog(UiCorePlugin.PLUGIN_ID).error(e.getMessage(), e);
        }

    }

    /**
     * 리소스의 컨텐츠를 삭제시 트리노드에서도 키값과 함께 삭제처리한다.
     * 
     * 
     * @param srcResource
     * @param contents
     * @throws Exception void
     */
    public void removeContentsOfResource(Resource srcResource, EObject contents) throws Exception {
        UMLTreeNodeRegistry.removeTreeNode(contents);

        srcResource.getContents().remove(contents);
    }

    /**
     * 
     * 
     *  
     * @param eobject
     * @return EAnnotation
     */
    public EAnnotation getFragmentContainerAnnotation(EObject eobject) {
        if (eobject instanceof org.eclipse.uml2.uml.Package) {
            org.eclipse.uml2.uml.Package myPackage = (org.eclipse.uml2.uml.Package) eobject;
            EAnnotation eAnnotation = myPackage.getEAnnotation(UICoreConstant.PROJECT_CONSTANTS__FRAGMENT_CONTAINER);
            return eAnnotation;
        }
        return null;
    }

    /**
     * 
     * 
     *  
     * @param mustSaveResources
     * @param eobject void
     */
    public void getRelatedResource(Map<String, Resource> mustSaveResources, EObject eobject) {
        Resource nextResource;
        TreeIterator<EObject> iterator = eobject.eAllContents();
        while (iterator.hasNext()) {
            EObject object = (EObject) iterator.next();
            if (object instanceof Element) {
                List<Element> list = UMLManager.getRelatedElement((Element) object);
                for (Element element : list) {
                    nextResource = element.eResource();
                    addResource(mustSaveResources, nextResource);
                }

                addResource(mustSaveResources, object.eResource());
            }
        }
        if (eobject instanceof Element) {
            List<Element> list = UMLManager.getRelatedElement((Element) eobject);
            for (Element element : list) {
                nextResource = element.eResource();
                addResource(mustSaveResources, nextResource);
            }
        }
    }

    /**
     * 
     * 
     *  
     * @param mustSaveResources
     * @param resource void
     */
    public void addResource(Map<String, Resource> mustSaveResources, Resource resource) {
        if (resource == null || resource.getURI() == null) {
            return;
        }
        String key = resource.getURI().toString();
        if (resource != null && !mustSaveResources.containsKey(key)) {
            mustSaveResources.put(key, resource);
        }
    }

    /**
     * 
     * 
     *  
     * @param changedList
     * @param elements void
     */
    public void findChildPackagedElements(List changedList, List<PackageableElement> elements) {

        for (PackageableElement element : elements) {
            if (element instanceof Package) {
                changedList.addAll(((Package) element).getPackagedElements());
                findChildPackagedElements(changedList, ((Package) element).getPackagedElements());
            } else {
                changedList.add(element);
            }
        }
    }

    /**
     * 
     * 
     * 
     * @param resource
     * @param uri
     * @return boolean
     */
    public boolean isRelated(Resource resource, URI uri) {
        IFile file = WorkspaceSynchronizer.getFile(resource);
        return isRelated(file, uri);
    }

    /**
     * 
     * 
     *  
     * @param resource
     * @param uriArray
     * @return boolean
     */
    public boolean isRelated(Resource resource, URI[] uriArray) {
        IFile file = WorkspaceSynchronizer.getFile(resource);
        
        if( file == null || !file.exists()) {
            return false;
        }
        for(URI uri : uriArray){
            if(isRelated(file, uri)){
                return true;
            }
        }
        
        return false;
    }

    /**
     * inverseReferences
     *  
     * @param eObj
     * @return List<Resource>
     */
    public List<Resource> inverseReferences(EObject eObj) {
        List<Resource> referenceResources = new ArrayList<Resource>();

        TreeIterator<EObject> eAllContents = eObj.eAllContents();
        while (eAllContents.hasNext()) {
            EObject next = eAllContents.next();
            Collection<Setting> inverseReferences = CacheAdapter.INSTANCE.getNonNavigableInverseReferences(next, true);
            for (Setting r : inverseReferences) {
                Resource eResource = r.getEObject().eResource();
                if (eResource != null && !referenceResources.contains(eResource)) {
                    referenceResources.add(eResource);
                }
            }
        }
        
        Collection<Setting> inverseReferences = CacheAdapter.INSTANCE.getNonNavigableInverseReferences(eObj, true);
        for (Setting r : inverseReferences) {
            Resource eResource = r.getEObject().eResource();
            if (eResource != null && !referenceResources.contains(eResource)) {
                referenceResources.add(eResource);
            }
        }
        return referenceResources;
    }
    
    /**
     * 
     * eObj 에 포함된 요소를 참조하고있는 모든 요소를 반환한다.
     * 자신이 포함된 리소스도 해당됨.
     *  
     * @param eObj
     * @return List<EObject>
     */
    public Set<EObject> inverseReferencesAllElement(EObject eObj) {
        Set<EObject> referenceResources = new HashSet<EObject>();
        
        TreeIterator<EObject> eAllContents = eObj.eAllContents();
        while( eAllContents.hasNext() ) {
            EObject next = eAllContents.next();
            Collection<Setting> inverseReferences = CacheAdapter.INSTANCE.getNonNavigableInverseReferences(next, true);
            for (Setting r : inverseReferences) {
                if (r.getEObject() != null) {
                    referenceResources.add(r.getEObject());
                }
            }
        }
        
        Collection<Setting> inverseReferences = CacheAdapter.INSTANCE.getNonNavigableInverseReferences(eObj, true);
        for (Setting r : inverseReferences) {
            if (r.getEObject() != null) {
                referenceResources.add(r.getEObject());
            }
        }
        return referenceResources;
    }
    
    /**
     * 
     * 
     *  
     * @param file
     * @param uri
     * @return boolean
     */
    public boolean isRelated(IFile file, URI uri) {
            Resource resource = null;
            EObject eobject = null;
            
            try {
                resource = DomainRegistry.getUMLDomain().getResource(URI.createURI(file.getFullPath().toString()), true);
                eobject = DomainRegistry.getUMLDomain().getResourceSet().getEObject(uri, true);
            } catch (Exception e) {
                
            }
            
            if( resource == null || eobject == null) {
                return false;
            }
            try {
                // 값이 존재하면 참조되고 있는 리소스로 판단하여 true 반환
                return !inverseReferences(eobject).isEmpty(); 
            } catch (Exception e) { 
                return false;
            }
            /*StringBuffer sb = new StringBuffer();
            InputStream contents = null;

            char[] b = null;
            Reader reader = null;
            try {
                b = new char[1024];
                contents = file.getContents();
                reader = new BufferedReader(new InputStreamReader(contents,
                    UICoreConstant.PROJECT_CONSTANTS__ENCODING_UTF8));
                for (int n; (n = reader.read(b)) != -1;) {
                    sb.append(new String(b, 0, n));
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (CoreException e) {
                e.printStackTrace();
            } finally {
                if (contents != null) {
                    try {
                        contents.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (sb.toString().contains(uri.toString())) {
                return true;
            }

        return false;*/
    }

    /**
     * 
     * 
     *  
     * @param targetResource
     * @param mustSaveResources void
     */
    public void findRelatedElement(Resource targetResource, Map<String, Resource> mustSaveResources) {
        // 1. 패키지 하위 뒤져서 Element들 전부 가져오는 부분.
        List<EObject> changedList = new ArrayList<EObject>();

        Package fragmentPackage = null;
        if (!targetResource.getContents().isEmpty()) {
            if (targetResource.getContents().get(0) instanceof Package) {
                fragmentPackage = (Package) targetResource.getContents().get(0);
            }
        }
        if (fragmentPackage != null) {
            changedList.addAll(fragmentPackage.getPackagedElements());
            findChildPackagedElements(changedList, fragmentPackage.getPackagedElements());

        }

        ResourceSet rSet = DomainModelHandlerUtil.getUMLDomain().getResourceSet();
        List<Resource> resources = rSet.getResources();
        // 2. 리소스 셋에 들어있는 전체 다이어그램 가져오는 부분.
        List<Diagram> diagramList = new ArrayList<Diagram>();
        for (Resource res : resources) {
            if (res.getContents().isEmpty()) {
                continue;
            }
            EObject eobj = res.getContents().get(0);
            if (eobj instanceof Package) {
                Package packageObject = (Package) eobj;
                for (EAnnotation eAnnotation : packageObject.getEAnnotations()) {
                    if (UICoreConstant.PROJECT_CONSTANTS__DIAGRAM.equals(eAnnotation.getSource())) {
                        if (!eAnnotation.getContents().isEmpty()) {
                            if (eAnnotation.getContents().get(0) instanceof Diagram) {
                                Diagram diagram = (Diagram) eAnnotation.getContents().get(0);
                                diagramList.add(diagram);
                            }
                        }
                    }
                }
            }
        }

        // 1.과 2.를 비교해서 동일한 Element는
        // element.eResource()를 하여 Resource를 얻어 낸다.
        // 얻어낸 리소스를 저장!
        for (Diagram dig : diagramList) {
            EList<AbstractNode> nodeList = dig.getNodeList();
            for (AbstractNode node : nodeList) {

                Element element = node.getUmlModel();
                for (EObject eChild : changedList) {
                    if (eChild.equals(element)) {
                        ResourceManager.getInstance().addResource(mustSaveResources, dig.eResource());
                        // dig.eResource().save(DomainUtil.getSaveOptions());
                    }
                }
            }
        }
    }
    /**
     * cacheAdapter
     */
    CacheAdapter cacheAdapter = null;
    /**
     * 
     * 
     *   void
     */
    private void addLoginListener() {
        // 로그인 모듈 수정
        UMLLoginController.addLoginListener(new UMLNexcoreLoginListener() {
            
            /**
             * 
             * @see nexcore.tool.uml.core.util.UMLNexcoreLoginListener#changed(nexcore.tool.uml.core.util.UMLNexcoreLoginEvent)
             */
            public void changed(UMLNexcoreLoginEvent event) {

//                switch (event.getType()) {
//                    case UMLNexcoreLoginEvent.LOG_IN:
//                        openResource();
//                        break;
//                    case UMLNexcoreLoginEvent.LOG_OUT:
//                        closeResource();
//                        break;
//                }
            }
        });
    }
    
    /**
     * 리소스 전체 열기
     * 
     *   void
     */
    public void openResource() {
        Display.getDefault().asyncExec(new Runnable() {
            @Override
            public void run() {
                //ProjectUtil.closeAllEditor();
            }
        });
        IProject[] activeUMLProjects = ProjectUtil.getActiveUMLProjects();
        initializeModelingData(activeUMLProjects, false);
        EList<Adapter> cacheAdapters = DomainRegistry.getUMLDomain().getResourceSet().eAdapters();
        
        if (cacheAdapter == null) {
            cacheAdapter = new UMLCacheAdapter() {
                @Override
                public void notifyChanged(Notification msg) {
                    if( monitor == null) {
                        setMonitor(new NullProgressMonitor());
                    }
                    if( uriList == null || uriList.isEmpty()) {
                        setUriList(new ArrayList<String>());
                    }
                    super.notifyChanged(msg);
                }

                @Override
                protected void openReferenceProject(final IProject project) {
                    setMonitor(new NullProgressMonitor());
                    setUriList(new ArrayList<String>());
                    if (referencedProject.containsKey(project)) {
                        return;
                    }
                    openProject(new IProject[]{project});
                }

            };
        }
        cacheAdapters.add(cacheAdapter);
        
        final CommonViewer viewer = ViewerRegistry.getViewer();
        if (viewer != null && !viewer.getControl().isDisposed()) {
            Display.getDefault().asyncExec(new Runnable() {
                
                @Override
                public void run() {
                    viewer.refresh();
                }
            });
        }
    }
    
    /**
     * 리소스 전체 닫기
     * 
     *   void
     */
    public void closeResource() {
        Display.getDefault().asyncExec(new Runnable() {
            @Override
            public void run() {
                ProjectUtil.closeAllEditor();
            }
        });
        
        UMLCacheManager.getInstance().shutdown();
        
        ProgressMonitorDialog dialog = new ProgressMonitorDialog(getShell());
        
        try {
            dialog.run(true, false, new IRunnableWithProgress() {
                public void run(IProgressMonitor monitor) throws InvocationTargetException,
                InterruptedException {
                    IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
                    IProject[] projects = root.getProjects();
                    cleanResource(monitor, projects, false);
                }
            });
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            dialog.close();
            allElementList.clear();
            ResourceSet resourceSet = DomainRegistry.getUMLDomain().getResourceSet();
            EList<Adapter> cacheAdapters = resourceSet.eAdapters();
            cacheAdapters.remove(cacheAdapter);

            Map<URI, Resource> uriResourceMap = ((ResourceSetImpl) resourceSet).getURIResourceMap();

            List<Resource> resourceList = new ArrayList<Resource>();

            for (Iterator iterator = uriResourceMap.values().iterator(); iterator.hasNext();) {
                Resource resource = (Resource) iterator.next();

                if (resource.getURI().toString().endsWith("uml")) {
                    resourceList.add(resource);
                }
            }

            resourceSet.getResources().clear();

            uriResourceMap.clear();

            resourceSet.getResources().addAll(resourceList);

            cacheAdapter = null;
            System.gc();
        }
        
        final CommonViewer viewer = ViewerRegistry.getViewer();
        if (viewer != null && !viewer.getControl().isDisposed()) {
            Display.getDefault().asyncExec(new Runnable() {
                
                @Override
                public void run() {
                    viewer.refresh();
                }
            });
        }
        
        
    }

    /**
     * 리소스셋에서 리소스를 remove하기전 수정된 사항이 있는 지 체크하여 수정된 리소스가 있으면 true 반환
     * 
     * @param resources
     * @return boolean
     */
    public static List<Resource> hasModifiedResource(IResource[] resources) {
        List<Resource> saveableList = new ArrayList<Resource>();
        List<Resource> resourceList = DomainRegistry.getUMLDomain().getResourceSet().getResources();
        for (Resource resource : resourceList) {
            URI type = resource.getURI();
            for (IResource r : resources) {
                URI uri = URI.createURI(r.getFullPath().toString());
                if (type.toString().indexOf(uri.toString()) > -1) {
                    if (resource.isModified()) {
                        if (!saveableList.contains(resource)) {
                            saveableList.add(resource);
                        }
                    }
                }
            }
        }
        return saveableList;
    }

    /**
     * 
     * 
     * 
     * @param resources
     * @param forceSave
     *            void
     */
    public static void cleanResource(IProgressMonitor monitor, IResource[] resources, boolean save) {
        Map<String, ITreeNode> treeNodes = ProjectRegistry.UMLTreeNodeRegistry.getTreeNodes();
        List<Object> cleanTarget = new ArrayList<Object>();
        for (Iterator<String> iterator = treeNodes.keySet().iterator(); iterator.hasNext();) {
            String uriKey = (String) iterator.next();
            for (IResource resource : resources) {
                URI uri = URI.createURI(resource.getFullPath().toString());
                if (uriKey.indexOf(uri.toString()) > -1) {
                    cleanTarget.add(uriKey);
                    treeNodes.get(uriKey).refresh();
                }
            }
        }
        // UMLTreeNodeRegistry.getTreeNodes() 에서 관련된 키 삭제
        for (Object uriKey : cleanTarget) {
            ProjectRegistry.UMLTreeNodeRegistry.removeTreeNode(uriKey);
        }

        List<Resource> resourceList = DomainRegistry.getUMLDomain().getResourceSet().getResources();
        List<Resource> removeResourceList = new ArrayList<Resource>();
        for (Resource resource : resourceList) {
            URI resourceUri = resource.getURI();
            for (IResource r : resources) {
                URI uri = URI.createURI(r.getFullPath().toString());
                if (resourceUri.toString().indexOf(uri.toString()) > -1) {
                    if (!removeResourceList.contains(resource)) {
                        removeResourceList.add(resource);
                    }
                }
            }
        }
        monitor.beginTask("Close resource", resourceList.size());
        
        for (Iterator<Resource> iterator = removeResourceList.iterator(); iterator.hasNext();) {
            final Resource resource = iterator.next();

            try {
                monitor.worked(1);
                if (resource.isModified()) {
                    DomainModelHandlerUtil.save(resource, save);
                }

                URI uri = URI.createURI(resource.getURI().toString());
                ProjectRegistry.UMLTreeNodeRegistry.removeTreeNode(uri);

                ResourceManager.getInstance().setActivation(uri, false);
                
                DomainUtil.run(new TransactionalAction() {
                    
                    @Override
                    public void doExecute() {
                        try {
                            CacheAdapter.INSTANCE.clear(resource);
                            resource.unload();
                            
                            ResourceSet resourceSet = DomainRegistry.getUMLDomain().getResourceSet();
                            if (resourceSet != null) {
                                resourceSet.getResources().remove(resource);
                            }
                        } catch (Exception e) {
                            ALMLogger.getLog(UiCorePlugin.PLUGIN_ID).error(e.getMessage(), e);
                        }
                    }
                    
                });
            } catch (Exception e) {
                ALMLogger.getLog(UiCorePlugin.PLUGIN_ID).error(e.getMessage(), e);
            }
        }
        
    }

    /**
     * resourceSetListener
     */
    ResourceSetListenerImpl resourceSetListener = null;
        
    /**
     * 
     * synchronize false : asyncExec
     * 
     * @param projects
     * @param synchronize
     *            void
     */
    public synchronized void initializeModelingData(final IProject[] projects, boolean synchronize) {
        if (projects != null && projects.length > 0) {
            Display.getDefault().syncExec(initializeRunnable(projects));
        }
//        if (resourceSetListener == null) {
//            resourceSetListener = new ResourceSetListenerImpl() {
//                @Override
//                public void resourceSetChanged(ResourceSetChangeEvent event) {
//                    List<Notification> notifications = event.getNotifications();
//                }
//            };
//            DomainRegistry.getUMLDomain().getTransactionalEditingDomain().addResourceSetListener(resourceSetListener);
//        }
    }
    
    /**
     * initializeRunnable
     *  
     * @param projects
     * @return RunnableWithResult<Object>
     */
    private RunnableWithResult<Object> initializeRunnable(final IProject[] projects) {
        return /**
         * <ul>
         * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
         * <li>서브 업무명 : nexcore.tool.uml.ui.core.registry</li>
         * <li>설  명 : 1</li>
         * <li>작성일 : 2012. 7. 10.</li>
         * <li>작성자 : nspark</li>
         * </ul>
         */
        new RunnableWithResult<Object>() {

            IStatus status = null;
            
            public Object getResult() {
                return status;
            }

            public IStatus getStatus() {
                return status;
            }

            public void setStatus(IStatus status) {

            }

            IProject[] projectArray;

            public IProject[] getProjects() {
                return projectArray;
            }

            public void setProjects(IProject[] projects) {
                this.projectArray = projects;
            }

            int count = 0;

            ModelUpdater modelUpdate = null;

            public void run() {
                referencedProject.clear();

                setProjects(projects);
                executeProgressDialog();
                
                // 이전 열려 있던 모델 목록 clear
                RESOURCE_MEMENTO.clear();
                
                status = Status.OK_STATUS;
            }

            private void executeProgressDialog() {
                // prompt 이전 실행에서 열려 있는 모델 오픈 할지 여부 확인 창.
                boolean persistableResource = false;
                
                // RESOURCE_MEMENTO 에 이전에 열었던 리소스 목록이 있으면 로드 할 지 여부를 묻는다.
                if(!RESOURCE_MEMENTO.isEmpty()){
                    persistableResource = promptToPersistableResource();
                }
                
                // 로드하지 않을 경우 RESOURCE_MEMENTO을 clear 하여 모든 리소스를 오픈하지 않은 상태로 한다.
                if(!persistableResource) {
                    RESOURCE_MEMENTO.clear();
                }
                
                ProgressMonitorDialog dialog = null;
                try {
                    Shell shell = getShell();
                    count = 0;
                    modelCount(getProjects());

                    dialog = new ProgressMonitorDialog(shell);
                    dialog.setOpenOnRun(true);
                    dialog.run(true, false, new IRunnableWithProgress() {

                        private List<String> uriList = new ArrayList<String>();

                        private void worked(IProgressMonitor monitor, String uri) {
                            try {
                                if (!uriList.contains(uri)) {
                                    monitor.worked(1);
                                    uriList.add(uri);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        public void run(final IProgressMonitor monitor) throws InvocationTargetException,
                        InterruptedException {
                            monitor.beginTask(UMLMessage.LABEL_LOAD_RESOURCE, count);

                            Display.getDefault().syncExec(new Runnable() {
                                
                                @Override
                                public void run() {
                                    for (IProject project : getProjects()) {
                                        if (!ProjectUtil.isActiveUMLProject(project)) {
                                            continue;
                                        }
                                        try {
                                            IMarker[] findMarkers = project.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_ONE);
                                            if(findMarkers != null) {
                                                for (IMarker marker : findMarkers) {
                                                    marker.delete();   
                                                }
                                            }
//                                            project.deleteMarkers(IMarker.PROBLEM, true, IResource.DEPTH_ONE);
                                        } catch (CoreException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            });
                            resourceManager.addResourceLoadedAdapter();
                            
                            // 프로파일 초기화
                            PrecedingInitializerRegistry.getInstance().executeInitializer();
                            // ///////////////////////////////////////////////////////
                            // 모델업데이트 실행
                            // ///////////////////////////////////////////////////////
                            String UML_MODEL_UPDATE = "UML_MODEL_UPDATE_%s_%s";

                            for (IProject project : getProjects()) {
                                if (!ProjectUtil.isActiveUMLProject(project)) {
                                    continue;
                                }
                                
                                monitor.setTaskName(String.format("%s : %s",
                                    project.getName(),
                                    UMLMessage.LABEL_MODEL_VERSION_CHECK));

                                String key = String.format(UML_MODEL_UPDATE,
                                    project.getName(),
                                    DateUtil.getCurrentDate());

                                // 로딩 속도 문제로 모델업데이트 기능 실행하지 않음.
                                String model_update = System.getProperty("model.update");

                                if (model_update != null && Boolean.parseBoolean(model_update)) {
                                    if (modelUpdate == null) {
                                        modelUpdate = new ModelUpdater();
                                    }

                                    try {
                                        UiCorePlugin.getDefault().getPreferenceStore().setValue(key, true);
                                        modelUpdate.modelUpdate(project, monitor);
                                        monitor.subTask("");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    } finally {
                                        if (modelUpdate != null) {
                                            modelUpdate.dispose();
                                        }

                                        modelUpdate = null;
                                        UiCorePlugin.getDefault().getPreferenceStore().needsSaving();
                                    }
                                }
                            }

                            initUMLPrimitiveTypes();
                            initJavaPrimitiveTypes();
                            initXMLPrimitiveTypes();

                            UMLCacheAdapter crossReferenceAdapter = new UMLCacheAdapter();

                            crossReferenceAdapter.setMonitor(monitor);
                            crossReferenceAdapter.setUriList(uriList);

                            EList<Adapter> cacheAdapter = DomainRegistry.getUMLDomain().getResourceSet().eAdapters();
                            if (!cacheAdapter.contains(crossReferenceAdapter)) {
                                cacheAdapter.add(crossReferenceAdapter);
                            }                           
                            
                            long s = System.currentTimeMillis();
                            // ///////////////////////////////////////////////////////
                            for (IProject project : getProjects()) {
                                try {
                                    if (!ProjectUtil.isActiveUMLProject(project)) {
                                        continue;
                                    }

                                    monitor.setTaskName(String.format("%s : %s", project.getName(), UMLMessage.LABEL_LOAD_RESOURCE));
                                    
                                    for (IResource resource : project.members()) {
                                        loadUMXResource(resource, monitor, count);
                                    }
                                    monitor.subTask("");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            
                            if(UMLDebug.DEBUG) {
                                System.err.println("---------------" + (System.currentTimeMillis()  - s));
                            }
                            monitor.subTask("");
                            
                            monitor.done();

                            cacheAdapter.remove(crossReferenceAdapter);
                        }

//                        private void loadUMFResource(IResource resource, IProgressMonitor monitor, int total) {
//                            try {
//                                if (resource instanceof IFolder) {
//                                    for (IResource member : ((IFolder) resource).members()) {
//                                        loadUMFResource(member, monitor, total);
//                                    }
//                                } else if (resource instanceof IFile) {
//                                    try {
//                                        if (ProjectUtil.isFragmentFile((IFile) resource)) {
//                                            monitor.worked(1);
//                                            String subTask = String.format(" %s", ((IFile) resource).getFullPath()
//                                                .toString());
//                                            monitor.subTask(subTask);
//
//                                            URI uri = URI.createURI(((IFile) resource).getFullPath().toString());
//                                            Resource res = DomainRegistry.getUMLDomain()
//                                                .getResourceSet()
//                                                .getResource(uri, true);
//                                            res.setTrackingModification(true);
//                                            
//                                            ((ResourceImpl)res).setIntrinsicIDToEObjectMap(new HashMap<String, EObject>());
//                                            // 유형설정 등의 기능에서 프로젝트 내의 UML 요소들을
//                                            // 검색하는 시간을 단축하기 위해 요소들을
//                                            // 리스트에 동적으로 관리.
//                                            EObject eObject;
//                                            if (!res.getContents().isEmpty()) {
////                                                EcoreUtil.UnresolvedProxyCrossReferencer.find(res);
//                                            }
//                                        }
//                                    } catch (Exception e) {
//                                        // ignore
//                                    }
//                                }
//                            } catch (CoreException e) {
//                                e.printStackTrace();
//                            }
//                        }
                        
                        /**
                         * 
                         * @param resource
                         * @param monitor
                         * @param total
                         *            void
                         */
                        private synchronized void loadUMXResource(IResource resource, IProgressMonitor monitor, int total) {
                            try {
                                if (resource instanceof IFolder) {
                                    for (IResource member : ((IFolder) resource).members()) {
                                        loadUMXResource(member, monitor, total);
                                    }
                                } else if (resource instanceof IFile) {
                                    try {
                                        if (DomainUtil.isUMXFile((IFile) resource)) {
                                            if (monitor != null) {
                                                String path = ((IFile) resource).getFullPath().toString();
                                                worked(monitor, path);
                                                String subTask = String.format("Loading : %s", path);
                                                monitor.subTask(subTask);
                                            }
                                            URI uri = URI.createURI(((IFile) resource).getFullPath().toString());
                                            
                                            boolean active = isActive(uri);
                                            if(!active) {
                                                return;
                                            }
                                            
                                            Resource res = DomainRegistry.getUMLDomain().getResource(uri, true);
                                            
                                            // 참조된 프로젝트 오픈시 해당 리소스는 does not exist. 에러 메세지를 갖는다.
                                            // 리소스셋에는 등록이 되어 있는 상태이고 로딩까지 되어 있지만 잘못 로딩된 리소스 이므로 unload 후 다시 load 해야한다.
                                            // 참조된 프로젝트오픈시 리소스가 오픈되지 않는 이유이다.
                                            if (res != null && (!res.getErrors().isEmpty() || !res.getWarnings().isEmpty())) {
                                                res.unload();
                                                res = DomainRegistry.getUMLDomain()
                                                    .getResourceSet()
                                                    .getResource(uri, true);
                                            }
                                            
                                            if (!res.getContents().isEmpty()) {
                                                if(!res.isTrackingModification()){
                                                    res.setTrackingModification(true);
                                                }
                                                final Resource umlResource = res;

                                                DomainUtil.run(new TransactionalAction() {
                                                    /**
                                                     * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                                                     */
                                                    @Override
                                                    public void doExecute() {
                                                        EcoreUtil.resolveAll(umlResource);
                                                    }
                                                });
                                            }
                                        }
                                    } catch (Exception e) {
                                        // ignore
                                        e.printStackTrace();
                                    }
                                }
                            } catch (CoreException e) {
                                e.printStackTrace();
                            }
                        }
                        /**
                         * 
                         * void
                         */
                        private void initJavaPrimitiveTypes() {
                            URI uri = URI.createURI(UMLResource.JAVA_PRIMITIVE_TYPES_LIBRARY_URI);
                            Resource resource = DomainRegistry.getUMLDomain().getResource(uri, true);
                            if (resource != null) {
            					addElement(resource);
            				}
                        }

                        /**
                         * 
                         * void
                         */
                        private void initUMLPrimitiveTypes() {
                            URI uri = URI.createURI(UMLResource.UML_PRIMITIVE_TYPES_LIBRARY_URI);
                            Resource resource = DomainRegistry.getUMLDomain().getResource(uri, true);
                            if (resource != null) {
            					addElement(resource);
            				}
                        }

                        /**
                         * 
                         * void
                         */
                        private void initXMLPrimitiveTypes() {
                            URI uri = URI.createURI(UMLResource.XML_PRIMITIVE_TYPES_LIBRARY_URI);
                            Resource resource = DomainRegistry.getUMLDomain().getResource(uri, true);
                            if (resource != null) {
            					addElement(resource);
            				}
                        }
                    });

                } catch (InvocationTargetException e1) {
                    e1.printStackTrace();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                } finally {
                    dialog.close();
                    System.gc();

                    if (referencedProject.size() > 0) {
                        boolean openQuestion = promptToOpenWithReferences();
                        if (!openQuestion) {
                            referencedProject.clear();
                            return;
                        }
                        
                        setProjects(referencedProject.keySet().toArray(new IProject[] {}));

                        openProject(referencedProject.keySet().toArray(new IProject[] {}));

                        resourceManager.removeResourceLoadedAdapter();
                    }
                }
            }

            private int modelCount(IProject[] projects) {
                for (IProject project : projects) {
                    if (!ProjectUtil.isActiveUMLProject(project)) {
                        continue;
                    }

                    try {
                        for (IResource r : project.members()) {
                            count(r);
                        }
                    } catch (CoreException e) {
                        e.printStackTrace();
                    }
                }

                return count;
            }
            
            
            /**
             * promptToOpenWithReferences
             *  
             * @return boolean
             */
            private boolean promptToOpenWithReferences() {
                IPreferenceStore store = UiCorePlugin.getDefault().getPreferenceStore();
                String key = "OPEN_REQUIRED_PROJECTS";
                String value = store.getString(key);
                if (MessageDialogWithToggle.ALWAYS.equals(value)) {
                    return true;
                }
                if (MessageDialogWithToggle.NEVER.equals(value)) {
                    return false;
                }
                
                String message = UMLMessage.MESSAGE_REFERENCED_PROJECTS;//"참조된 프로젝트를 오픈합니다.";
                
                MessageDialogWithToggle dialog = MessageDialogWithToggle.openInformation(getShell(), "Information", message, null, false, store, key);//MessageDialogWithToggle.openYesNoCancelQuestion(getShell(), "Question", message, null, false, store, key);
                int result = dialog.getReturnCode();
                // the result is equal to SWT.DEFAULT if the user uses the 'esc' key to close the dialog
                return dialog.getReturnCode() == IDialogConstants.OK_ID || result == SWT.DEFAULT;
            }
            
            /**
             * promptToPersistableResource
             *  
             * @return boolean
             */
            private boolean promptToPersistableResource() {
               
                return true;
            }

            private void count(IResource resource) {
                if (resource instanceof IFolder) {
                    try {
                        for (IResource member : ((IFolder) resource).members()) {
                            count(member);
                        }
                    } catch (CoreException e) {

                    }
                } else if (resource instanceof IFile) {
                    if (ProjectUtil.isModelFile((IFile) resource)) {
                        count++;

                        URI uri = URI.createURI(resource.getFullPath().toString());

                        IProject project = resource.getProject();
                        List<String> list = RESOURCE_MEMENTO.get(project.getName());
                        if (list != null && list.contains(uri.toString())) {
                            ResourceManager.getInstance().setActivation(uri, true);
                        } else {
                            ResourceManager.getInstance().setActivation(uri, false);
                        }
                    }
                }
            }
        };
    }
    

    /**
     * addElement
     *  
     * @param object void
     */
    private void addElement(Object object) {
        if (object instanceof Element) {
            if (object instanceof NamedElement) {
                NamedElement element = (NamedElement) object;
                allElementList.add(element);
            }
            for (Element child : ((Element) object).allOwnedElements()) {
                if (child instanceof NamedElement) {
                    NamedElement element = (NamedElement) child;
                    allElementList.add(element);
                }
            }
        } else if (object instanceof Resource) {
            Resource resource = (Resource) object;
            Model model = (Model) EcoreUtil.getObjectByType(resource.getContents(), UMLPackage.Literals.MODEL);
            if (model != null) {
                for (Element child : model.allOwnedElements()) {
                    if (child instanceof NamedElement) {
                        NamedElement element = (NamedElement) child;
                        allElementList.add(element);
                    }
                }
            }
        }
    }
    
    /**
     * 
     * 
     *  
     * @param object void
     */
    private static void removeElement(Object object) {
        if (object instanceof Model) {
            for (Element child : ((Model) object).allOwnedElements()) {
                if (child instanceof NamedElement) {
                    NamedElement element = (NamedElement) child;
                    allElementList.remove(element);
                }
            }
        } else if (object instanceof NamedElement) {
            NamedElement namedElement = (NamedElement) object;
            allElementList.remove(namedElement.getQualifiedName());

            for (Element child : namedElement.allOwnedElements()) {
                if (child instanceof NamedElement) {
                    NamedElement element = (NamedElement) child;
                    allElementList.remove(element);
                }
            }
        } else if (object instanceof Resource) {
            Resource resource = (Resource) object;
            Model model = (Model) EcoreUtil.getObjectByType(resource.getContents(), UMLPackage.Literals.MODEL);
            if (model != null) {
                for (Element child : model.allOwnedElements()) {
                    if (child instanceof NamedElement) {
                        NamedElement element = (NamedElement) child;
                        allElementList.remove(element);
                    }
                }
            }
        }
    }
    /**
     * 
     * 
     *  
     * @param projects void
     */
    private void openProject(final IProject[] projects) {
        IShellProvider shellProvider = new IShellProvider() {
            public Shell getShell() {
                return ResourceManager.this.getShell();
            }
        };
        
        OpenResourceAction action = new OpenResourceAction(shellProvider){
            protected List getSelectedResources() {
                return Arrays.asList(projects);
            }
            
        };
        action.run();
    }
    
    /**
     * 
     * 
     *  
     * @return Shell
     */
    private Shell getShell() {
        Shell shell = null;
        IWorkbenchWindow[] workbenchWindows = PlatformUI.getWorkbench().getWorkbenchWindows();
        for (IWorkbenchWindow workbenchWindow : workbenchWindows) {
            if (workbenchWindow.getShell() != null) {
                shell = workbenchWindow.getShell();
                break;
            }
        }
        return shell;
    }
    
    /**
     * getAllElements
     *  
     * @return Set<Element>
     */
    public static Set<Element> getAllElements() {
        ResourceSet resourceSet = DomainRegistry.getUMLDomain().getResourceSet();
        Iterator<Resource> ir = resourceSet.getResources().iterator();
        while (ir.hasNext()) {
            Resource resource = ir.next();
            if (ProjectUtil.isModelFile(resource)) {
                ResourceManager.getInstance().addElement(resource);
            }
        }
        return allElementList;
    }

    /**
     * findReferencedProjects
     *  
     * @param project
     * @return
     * @throws Exception IProject[]
     */
    public IProject[] findReferencedProjects(IProject project) throws Exception {
        return project.getReferencedProjects();
    }

    /**
     * @return the isChecked
     */
    public static boolean isChecked() {
        return isChecked;
    }

    /**
     * @param isChecked
     *            the isChecked to set
     */
    public static void setChecked(boolean isChecked) {
        ResourceManager.isChecked = isChecked;
    }

    /**
     * <ul>
     * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
     * <li>서브 업무명 : nexcore.tool.uml.ui.core.registry</li>
     * <li>설  명 : UMLCacheAdapter</li>
     * <li>작성일 : 2012. 8. 21.</li>
     * <li>작성자 : nspark</li>
     * </ul>
     */
    class UMLCacheAdapter extends CacheAdapter {

        /**
         * monitor
         */
        IProgressMonitor monitor;

        /**
         * uriList
         */
        List<String> uriList = Collections.emptyList();

        /**
         * setUriList
         *  
         * @param uriList void
         */
        public void setUriList(List<String> uriList) {
            this.uriList = uriList;
        }

        /**
         * setMonitor
         *  
         * @param monitor void
         */
        public void setMonitor(IProgressMonitor monitor) {
            this.monitor = monitor;
        }
        
        /**
         * openReferenceProject
         *  
         * @param project void
         */
        protected void openReferenceProject(IProject project) {
            referencedProject.put(project, Boolean.FALSE);
        }

        /**
         * @see org.eclipse.uml2.common.util.CacheAdapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
         */
        @Override
        public void notifyChanged(Notification msg) {
            super.notifyChanged(msg);

            final Object notifier = msg.getNotifier();
            
            if (notifier instanceof Resource) {
                Resource r = (Resource) notifier;
                String path = r.getURI().toString();
                switch (msg.getFeatureID(Resource.class)) {
                    case Resource.RESOURCE__ERRORS: {
                        try {
                            URI uri = r.getURI();
                            
                            if( uri.toString().endsWith(UICoreConstant.PROJECT_CONSTANTS__RELATION)){
                                return;
                            }
                            
                            if (msg.getNewValue() instanceof Exception) {
                                Exception e = (Exception) msg.getNewValue();
                                Log.error(e);

                                final Throwable cause = e.getCause();
                                
                                if (cause != null && cause.getMessage() != null && cause.getMessage().endsWith("does not exist.")) {
                                    String projectName = uri.segment(0);
                                    final IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
                                    final IProject project = root.getProject(projectName);
                                    if (project != null && project.exists()) {
                                        if (!project.isOpen()) {
                                            getShell().getDisplay().asyncExec(new Runnable() {
                                                public void run() {
                                                    openReferenceProject(project);
                                                }
                                            });
                                        } else {
                                            createMarker(cause, project);
                                        }
                                    } else {
                                        createMarker(cause, root);
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.error(e);
                        }

                        break;
                    }
                    case Resource.RESOURCE__IS_LOADED: {
                        try {
                            if (ProjectUtil.isModelFile(r)) {
                                worked(path);
                                String subTask = String.format("Loaded : %s", path);
                                if(!r.isTrackingModification()) {
                                    r.setTrackingModification(true);
                                }
                                monitor.subTask(subTask);
                            }
                        
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                    default : {
                        String subTask = String.format("Loading : %s", path);
                        monitor.subTask(subTask);
                    }
                }
                
                

            }
        }

        /**
         * createMarker
         *  
         * @param cause
         * @param root void
         */
        private void createMarker(final Throwable cause, final IResource root) {
            getShell().getDisplay().asyncExec(new Runnable() {
                public void run() {
                    try {
                        IMarker createMarker = root.createMarker(IMarker.PROBLEM);
                        createMarker.setAttribute(IMarker.MESSAGE, cause.getMessage());
                        createMarker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
                    } catch (CoreException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        /**
         * worked
         *  
         * @param uri void
         */
        private void worked(String uri) {
            try {
                if (!uriList.contains(uri)) {
                    uriList.add(uri);
                    monitor.worked(1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    
    /**
     * resource 에 속한 요소를 참조하고 있는 다이어그램
     * 
     *  
     * @param resource
     * @return List<Element>
     */
    public Set<Element> inverseReferenceElementList(Resource resource) {
        if (resource != null && resource.isLoaded() && !resource.getContents().isEmpty()) {
            return inverseReferenceElementList(resource.getContents().get(0));
        }

        return Collections.emptySet();
    }
    
    /**
     * eObject 에 속한 요소를 참조하고 있는 다이어그램
     * 
     *  
     * @param eObject
     * @return List<Element>
     */
    public Set<Element> inverseReferenceElementList(EObject eObject) {
        Set<Element> elementList = new HashSet<Element>();
        Set<EObject> referenceEObject = inverseReferencesAllElement(eObject);

        for (Iterator<EObject> iterator = referenceEObject.iterator(); iterator.hasNext();) {
            EObject eObj = (EObject) iterator.next();

            EObject rootContainer = EcoreUtil.getRootContainer(eObj);
            if (eObject.eResource() == rootContainer.eResource()) {
                continue;
            }

            EObject eContainer = eObj.eContainer();

            if (eContainer == null || eContainer instanceof NotationNode) {
                continue;
            }
            Object obj = ModelHandler.getInstance().getProperModel(eContainer);
            if (obj instanceof Element) {
                elementList.add((Element) obj);
            }
        }
        
        return elementList;
    }
    
    /**
     * inverseReferenceElementNameList
     *  
     * @param eObject
     * @return List<String>
     */
    public Set<String> inverseReferenceElementNameList(EObject eObject) {
        Set<Element> elementList = inverseReferenceElementList(eObject);
        Set<String> elementNameList = new HashSet<String>();

        for (Iterator<Element> iterator = elementList.iterator(); iterator.hasNext();) {
            Element element = (Element) iterator.next();
            String name = ModelHandler.getInstance().getProperTitle(element);
            elementNameList.add(name);
        }

        return elementNameList;
    }
    
    /**
     * crossReference 모델의 dirty 상태 체크
     * 참조된 리소스가 열려 있는 상태(active) 가 아니고 isModified 가 true인 
     * 경우 참조된 모델을 사용하는 모델에 dirty 표시를 해야한다. (숨어있는 모델을 저장하기 위해서)
     *  
     * @param resource
     * @return boolean
     */
    public boolean isDirtyOfCrossReference(EObject eObject) {
        // 모델 닫기 기능을 사용하지 않을 경우에는 이 로직이 실행되지 않아도 됨.
        Boolean useModelClose = PreferenceUtil.INSTANCE.getValueOfBooleanFieldEditor(ManagerConstant.USE_MODEL_CLOSE);
        if(!useModelClose) {
            return false;
        }
        // crossReference 모델의 dirty 상태 체크

        Resource resource = eObject.eResource();
        Map<EObject, Collection<Setting>> find = Collections.emptyMap();
        try {
            find = EcoreUtil.CrossReferencer.find(eObject.eContents());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        for (Iterator<?> iterator = find.keySet().iterator(); iterator.hasNext();) {
            EObject refEObject = (EObject) iterator.next();
            Resource refResource = refEObject.eResource();
            if (ProjectUtil.isModelFile(refResource) && resource != refResource) {
                if (refResource.isModified()) {
                    if (ProjectUtil.isFragmentFile(refResource)) {
                        EObject rootContainer = EcoreUtil.getRootContainer(refEObject);
                        if (rootContainer.eResource() == refEObject.eResource()) {
                            // umx 가 닫혀 있는 상태로 판단.
                            return true;
                        } else {
                            // rootContainer 가 umx 인 경우 active 인지 체크하여 active가 아니면 proxy 로 열려있는 상태임.
                            if (rootContainer != null && rootContainer.eResource() != null) {
                                boolean active = isActive(rootContainer.eResource().getURI());
                                if (!active) {
                                    return true;
                                }
                            }
                        }
                    } else if (DomainUtil.isUMXFile(refResource)) {
                        boolean active = isActive(refResource.getURI());
                        if (!active) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }
    
    /**
     * statusMap
     */
    private Map<URI, Boolean> resourceStatusMap = Collections.synchronizedMap(new HashMap<URI, Boolean>());
    
    /**
     * isActive
     *  
     * @param uri
     * @return boolean
     */
    public boolean isActive(URI uri) {
        Boolean useModelClose = PreferenceUtil.INSTANCE.getValueOfBooleanFieldEditor(ManagerConstant.USE_MODEL_CLOSE);
        if(useModelClose) {
            return resourceStatusMap.containsKey(uri) ? resourceStatusMap.get(uri) : true;
        } else {
            return true;
        }
    }
    
    /**
     * setActive
     *  
     * @param uri void
     */
    public void setActivation(URI uri, boolean activation) {
        resourceStatusMap.put(uri, activation);
    }
    
    /**
     * clearStatusMap
     *   void
     */
    public void clearStatusMap() {
        resourceStatusMap.clear();
    }
}
