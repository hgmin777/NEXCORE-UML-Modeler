/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.project.explorer.provider;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nexcore.alm.common.ui.util.ALMLogger;
import nexcore.tool.mdd.core.extension.IDomainModelHandler;
import nexcore.tool.uml.core.UMLDebug;
import nexcore.tool.uml.core.util.UMLLoginController;
import nexcore.tool.uml.core.util.UMLNexcoreLoginEvent;
import nexcore.tool.uml.core.util.UMLNexcoreLoginListener;
import nexcore.tool.uml.manager.utility.DomainModelHandlerUtil;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.project.ClosedTreeNode;
import nexcore.tool.uml.ui.core.project.ITreeNode;
import nexcore.tool.uml.ui.core.registry.ProjectRegistry;
import nexcore.tool.uml.ui.core.registry.ResourceManager;
import nexcore.tool.uml.ui.core.util.ProjectUtil;
import nexcore.tool.uml.ui.project.ProjectExplorerPlugin;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.Saveable;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.navigator.SaveablesProvider;
import org.eclipse.ui.progress.IJobRunnable;
import org.eclipse.uml2.common.util.CacheAdapter;
import org.eclipse.uml2.uml.NamedElement;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.provider</li>
 * <li>설 명 : ExplorerSaveablesProvider</li>
 * <li>작성일 : 2009. 12. 1.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
public class ExplorerSaveablesProvider extends SaveablesProvider {

    /**
     * ExplorerSaveablesProvider
     */
    public ExplorerSaveablesProvider() {
        saveablesMap = new Hashtable<String, ExplorerSaveable>();
        
        UMLLoginController.addLoginListener(new UMLNexcoreLoginListener() {

            @Override
            public void changed(UMLNexcoreLoginEvent event) {
                synchronized (this) {
                    if (event.getType() == UMLNexcoreLoginEvent.LOG_IN) {
                        // do nothing
                    } else if (event.getType() == UMLNexcoreLoginEvent.LOG_OUT) {
                        if (saveablesMap != null) {
                            saveablesMap.clear();
                        }
                        fireSaveablesClosed(getSaveables());
                    }
                }
            }
        });
        
        EList<Adapter> cacheAdapters = DomainRegistry.getUMLDomain().getResourceSet().eAdapters();

        cacheAdapters.add(new CacheAdapter(){
            @Override
            public void notifyChanged(Notification msg) {
                super.notifyChanged(msg);

                final Object notifier = msg.getNotifier();               
                
                if (notifier instanceof Resource) {
                    Resource r = (Resource) notifier;
                    switch (msg.getFeatureID(Resource.class)) {
                        // resourceset.getResources().remove(r) 될 때 실행됨.
                        case Resource.RESOURCE__RESOURCE_SET: {
                            if (saveablesMap != null) {
                                if (saveablesMap.containsKey(r.getURI().toString())) {
                                    ExplorerSaveable saveable = saveablesMap.get(r.getURI().toString());
                                    fireSaveablesClosed(new Saveable[] { saveable });
                                    saveablesMap.remove(r.getURI().toString());
                                }
                            }
                            break;
                        }
                    }
                }
            }
        });
    }

    /** Explorer saveable */
    // private ExplorerSaveable saveable;
    private Hashtable<String, ExplorerSaveable> saveablesMap;

    /**
     * calls from {@link ExplorerSaveable}.selectionChanged().
     * 
     * @param selection
     *            void
     */
    public void changeSelection(Object selection) {

        if (selection != null && saveablesMap.size() != 0) {
            fireSaveablesOpened(getSaveables());
        }
    }

    /**
     * @see org.eclipse.ui.navigator.SaveablesProvider#getElements(org.eclipse.ui.Saveable)
     */
    @Override
    public Object[] getElements(Saveable pSaveable) {

        if (pSaveable == null) {
            return new Object[] {};
        }

        CommonViewer viewer = ProjectRegistry.ViewerRegistry.getViewer();
        ITreeContentProvider contentProvider = (ITreeContentProvider) viewer.getContentProvider();
        Object viewerInput = viewer.getInput();

        if (contentProvider == null) {
            return null;
        }

        return contentProvider.getElements(viewerInput);

    }

    /**
     * doSave 일때 만 true 상태임.
     * 
     * 저장시 getSavable(Object) 호출되어 Object 와 관련된 리소스를 강제로 setModified(true) 하지 못하게 하기 위함.
     */
    boolean isSaveMode = false;
    /**
     * if the selected element is changed, always called.
     * 
     * @see org.eclipse.ui.navigator.SaveablesProvider#getSaveable(java.lang.Object)
     */
    @Override
    public Saveable getSaveable(Object element) {
        EObject eobject;
        Resource resource;
        if( element instanceof ClosedTreeNode) {
            final IResource file = ((ClosedTreeNode) element).getResource();
            URI uri = URI.createURI(file.getFullPath().toString());
            
            if (saveablesMap.containsKey(uri.toString())) {
                fireSaveablesClosed(new Saveable[]{saveablesMap.get(uri.toString())});
            }
            return null;
        } else if (element instanceof ITreeNode) {
            ITreeNode treeNode = (ITreeNode) element;
            eobject = treeNode.getEObject();
            if (eobject != null) {
                resource = eobject.eResource();
                if (resource == null) {
                    return null;
                }
                if (resource.isModified() && !isSaveMode) {
                    if (treeNode.isReferenceURIChanged()) {
                        Set<EObject> referenceElements = ResourceManager.getInstance().inverseReferencesAllElement(eobject);
                        for (Iterator<?> iterator = referenceElements.iterator(); iterator.hasNext();) {
                            EObject refObj = (EObject) iterator.next();

                            Resource r = refObj.eResource();
                            if (r == null || r.getContents().size() == 0) {
                                continue;
                            }
                            if (!saveablesMap.containsKey(r.getURI().toString())) {
                                ExplorerSaveable saveable = new ExplorerSaveable(refObj);
                                this.saveablesMap.put(r.getURI().toString(), saveable);
                            }
                            if (!r.isModified()) {
                                r.setModified(true);
                            }
                        }
                    }
                }

            } else {
                return null;
            }
        } else {
            return null;
        }
        ExplorerSaveable saveable = saveablesMap.get(resource.getURI().toString());
        if (!saveablesMap.containsKey(resource.getURI().toString())) {
            saveable = new ExplorerSaveable(element);
            
            this.saveablesMap.put(resource.getURI().toString(), saveable);
        } else {
            // 기존의 saveable 리소스와 현재의 리소스가 같이 않은 경우 기존의 리소스는 close 처리 후 open.
            if (saveable.getResource() != resource) {
                fireSaveablesClosed(new Saveable[]{saveable});
                saveable = new ExplorerSaveable(element);
                this.saveablesMap.put(resource.getURI().toString(), saveable);
            }
        }
        
        fireSaveablesOpened(new Saveable[]{saveable});

//        ProjectExplorerPlugin.getDisplay().syncExec(new Runnable() {
//            /**
//             * @see java.lang.Runnable#run()
//             */
//            public void run() {
//                fireSaveablesOpened(getSaveables());
//            }
//        });
        
        return saveable;
    }

    /**
     * @see org.eclipse.ui.navigator.SaveablesProvider#getSaveables()
     */
    @Override
    public Saveable[] getSaveables() {
        if (0 == saveablesMap.size()) {
            return new Saveable[] {};
        }
        List<Saveable> list = new ArrayList<Saveable>();
        for (Iterator<String> iterator = saveablesMap.keySet().iterator(); iterator.hasNext();) {
            String uriStr = (String) iterator.next();

            ExplorerSaveable saveable = saveablesMap.get(uriStr);
            Resource resource = saveable.getResource();
            if (resource == null) {
                iterator.remove();
                continue;
            }

            if (DomainUtil.isUMXFile(resource)) {
                boolean active = ResourceManager.getInstance().isActive(resource.getURI());
                if (!active) {
                    iterator.remove();
                    continue;
                }
            }

            ResourceSetImpl resourceSet = (ResourceSetImpl) DomainRegistry.getUMLDomain().getResourceSet();
            if (ProjectUtil.isModelFile(resource) && resourceSet.getURIResourceMap().containsKey(resource.getURI())) {
                list.add((Saveable) saveable);
            }
        }

        return list.isEmpty() ? new Saveable[0] : list.toArray(new Saveable[list.size()]);
    
    }

    /**
     * <ul>
     * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
     * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.provider</li>
     * <li>설 명 : ExplorerSaveable</li>
     * <li>작성일 : 2009. 12. 10.</li>
     * <li>작성자 : 황선림</li>
     * </ul>
     */
    public class ExplorerSaveable extends Saveable implements ISelectionChangedListener {

        /** selected EObject */
        private EObject eobject;

        /** resource of the selected EObject */
        private Resource resource;

        /**
         * treeNode
         */
        private ITreeNode treeNode = null;
        
        /**
        * @return
        */
        public ITreeNode getTreeNode() {
            return treeNode;
        }

        /**
         * @param element
         */
        public ExplorerSaveable(Object element) {
            if (element instanceof ITreeNode) {
                treeNode = (ITreeNode) element;
                eobject = ((ITreeNode) element).getEObject();
                if (eobject != null) {
                    resource = eobject.eResource();
                }
            } else if (element instanceof EObject) {
                eobject = (EObject) element;
                resource = ((EObject) element).eResource();
            } else {
                System.err.println(element);
            }
        }

        /**
         * getEObject
         *  
         * @return EObject
         */
        public EObject getEObject() {
            return eobject;
        }

        /**
         * getResource
         *  
         * @return Resource
         */
        public Resource getResource() {

            if (UMLLoginController.getInstance().isLogin() && resource != null) {
                try {
//                    URI uri = resource.getURI();
//                    if (!resource.getErrors().isEmpty()) {
//                        List<Diagnostic> errors = resource.getErrors();
//                        for (Diagnostic diagnostic : errors) {
//                            if( diagnostic instanceof DanglingHREFException) {
////                                System.err.println("1------>"+diagnostic.getMessage());
//                            }
//                        }
//                    }
                } catch (Exception e) {
                    // 재로딩시 에러 발생시 해당 리소스는 null 처리.
                    resource = null;
                    if(UMLDebug.DEBUG){
                        System.err.println(e.getMessage());
                    }
                }
            }
            return resource;
        }

        /**
         * @see org.eclipse.ui.Saveable#doSave(org.eclipse.core.runtime.IProgressMonitor,
         *      org.eclipse.jface.window.IShellProvider)
         */
        @Override
        public synchronized IJobRunnable doSave(IProgressMonitor monitor, IShellProvider shellProvider) throws CoreException {
            if (shellProvider instanceof IWorkbenchWindow) {
                IWorkbenchWindow window = ((IWorkbenchWindow) shellProvider);
                if (window.getWorkbench().isClosing()) {
                    DomainModelHandlerUtil.save();
                } else {
                    return super.doSave(monitor, shellProvider);
                }
            }
            return null;
        }

        private Shell getShell() {
            Shell shell = null;
            IWorkbenchWindow[] workbenchWindows = PlatformUI.getWorkbench().getWorkbenchWindows();

            for (IWorkbenchWindow workbenchWindow : workbenchWindows) {
                shell = workbenchWindow.getShell();
                if (shell != null) {
                    break;
                }
            }

            return shell;
        }
        
        @Override
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
                    ProgressMonitorDialog dialog = new ProgressMonitorDialog(getShell());
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
        
        /**
         * @see org.eclipse.ui.Saveable#doSave(org.eclipse.core.runtime.IProgressMonitor)
         */
//        @Override
        public synchronized void doSave2(IProgressMonitor monitor) throws CoreException {
            final IDomainModelHandler umlDomain = DomainRegistry.getUMLDomain();
            Resource resource = getResource();
            if (umlDomain == null || resource == null || resource.getContents().size() == 0) {
                return;
            }
            isSaveMode = true;
            
            // 연계 매핑 파일 저장
            DomainModelHandlerUtil.save(resource, false);
            
            // 저장하고자하는 리소스에 참조되어 있는 proxy 리소스의 정보가 변경 된 경우 함께 저장한다.
            Map<EObject, Collection<Setting>> find = EcoreUtil.CrossReferencer.find(resource.getContents());
            for (Iterator<?> iterator = find.keySet().iterator(); iterator.hasNext();) {
                EObject eObject = (EObject) iterator.next();
                Resource rr = eObject.eResource();
                if (ProjectUtil.isModelFile(rr) && resource != rr) {
                    if (rr.isModified()) {
                        DomainModelHandlerUtil.save(rr, false);
                    }
                }
            }
            
            if (getTreeNode() != null && getTreeNode().isReferenceURIChanged()) {
                Set<EObject> referenceElement = ResourceManager.getInstance()
                    .inverseReferencesAllElement(resource.getContents().get(0));
                List<String> savedResource = new ArrayList<String>();
                synchronized (referenceElement) {
                    for (Iterator<?> iterator = referenceElement.iterator(); iterator.hasNext();) {
                        EObject eObject = (EObject) iterator.next();
                        try {
                            if (!savedResource.contains(eObject.eResource().getURI().toString())) {
                                DomainModelHandlerUtil.save(eObject.eResource(), false);
                                savedResource.add(eObject.eResource().getURI().toString());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            
            try {
                IProject project = WorkspaceSynchronizer.getFile(resource).getProject();
                IPath relationPath = project.getFullPath().append(UICoreConstant.PROJECT_CONSTANTS__RELATION);

                IFile file = project.getFile(UICoreConstant.PROJECT_CONSTANTS__RELATION);
                if (file != null && file.exists()) {
                    Resource relationResource = DomainRegistry.getUMLDomain()
                        .getResourceSet()
                        .getResource(URI.createURI(relationPath.toString()), true);
                    
                    DomainModelHandlerUtil.save(relationResource, true);
                }
            } catch (Exception e) {
                ALMLogger.getLog(ProjectExplorerPlugin.PLUGIN_ID).error(e.getMessage(), e);
            }
            isSaveMode = false;
            
            dirtyStateChanged();
        }
        
        /**
         * 
         *   void
         */
        public void dirtyStateChanged() {
            ProjectExplorerPlugin.getDisplay().syncExec(new Runnable() {

                /**
                 * @see java.lang.Runnable#run()
                 */
                public void run() {
                    // SaveablesProvider 의 fireSaveablesDirtyChanged 의 listener 가 null 의.
                    // 경우 에러 발생하여 아래 코드를 예외처리 하였음.
                    try {
                        fireSaveablesDirtyChanged(getSaveables());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        /**
         * @see org.eclipse.ui.Saveable#equals(java.lang.Object)
         */
        @Override
        public boolean equals(Object object) {
            if (object instanceof ExplorerSaveable) {
                Resource paramResource = ((ExplorerSaveable) object).getResource();
                Resource currentResource = getResource();
                if( paramResource == null || currentResource == null ) {
                    return false;
                }
                return paramResource.equals(currentResource);
            } else if (object instanceof List) {
//                List list = (List) object;
                
//                System.out.println(list);
                return true;
            }
            return false;
        }

        /**
         * @see org.eclipse.ui.Saveable#getImageDescriptor()
         */
        @Override
        public ImageDescriptor getImageDescriptor() {
            if (getResource() != null && getResource().getContents().size() > 0) {
                return ImageDescriptor.createFromImage(UiCorePlugin.getDefault()
                    .getImageForUMLElement(getResource().getContents().get(0)));
            }

            return null;
        }

        /**
         * @see org.eclipse.ui.Saveable#getName()
         */
        @Override
        public String getName() {
            String name = UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;
            if (getResource() != null && getResource().getContents().size() > 0) {
                EObject eobj = getResource().getContents().get(0);
                if (eobj instanceof NamedElement) {
                    name = ((NamedElement) eobj).getName();
                } else {
                    name = getResource().getURI().lastSegment();
                }
            }
            return name;
        }

        /**
         * @see org.eclipse.ui.Saveable#getToolTipText()
         */
        @Override
        public String getToolTipText() {
            String tooltip = UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;
            String path = URI.decode(getResource().getURI().toString());
            if (path.endsWith(UICoreConstant.PROJECT_CONSTANTS__FILE_EXTENSION)) {
                tooltip = path.replaceAll("platform:/resource/", UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING); //$NON-NLS-1$
            } else if (path.endsWith(UICoreConstant.PROJECT_CONSTANTS__FRAGMENT_EXTENSION)) {
                tooltip = path.substring(1);
            }
            return tooltip;
        }

        /**
         * @see org.eclipse.ui.Saveable#hashCode()
         */
        @Override
        public int hashCode() {
            // 0으로 return하지 않으면, 다른 리소스 클릭시마다 save창이 뜨네요.
            // if(
            // !DomainRegistry.getUMLDomain().getResourceSet().getResources().contains(resource)
            // ) {
            // return 0;
            // }
            if (getResource() != null) {
                int hashcode = getResource().hashCode();
                return hashcode;
            } 
            return 0;
        }

        /**
         * @see org.eclipse.ui.Saveable#isDirty()
         */
        @Override
        public boolean isDirty() {
            Resource resource = getResource();
            if (resource != null && !resource.getContents().isEmpty()) {
                // crossReference 모델의 dirty 상태 체크
                EObject eObject = resource.getContents().get(0);
                if (resource.isModified() || ResourceManager.getInstance().isDirtyOfCrossReference(eObject)) {
                    return true;
                }
                
                if (ProjectUtil.isModelFile(resource)
                    && ((ResourceSetImpl) DomainRegistry.getUMLDomain().getResourceSet()).getURIResourceMap()
                        .containsKey(resource.getURI())) {
                    if (DomainUtil.isUMXFile(resource)) {
                        boolean active = ResourceManager.getInstance().isActive(resource.getURI());
                        if (!active) {
                            return false;
                        }
                    }
                    return resource.isModified();
                }
            }
            return false;
        }

        /**
         * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
         */
        public void selectionChanged(SelectionChangedEvent event) {
            if (!(event.getSelection() instanceof IStructuredSelection))
                return;

            Object object = ((IStructuredSelection) event.getSelection()).getFirstElement();
            if (object instanceof ITreeNode) {
                ITreeNode item = (ITreeNode) object;
                eobject = item.getEObject();
                CommonViewer viewer = ProjectRegistry.ViewerRegistry.getViewer();
                ISelection selection = viewer.getSelection();
                changeSelection(selection);

            }
        }

    }

}
