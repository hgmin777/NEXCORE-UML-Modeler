/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.core.registry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import nexcore.alm.common.ui.util.ALMLogger;
import nexcore.tool.uml.core.UMLDebug;
import nexcore.tool.uml.core.util.UMLLoginController;
import nexcore.tool.uml.core.util.UMLNexcoreLoginEvent;
import nexcore.tool.uml.manager.utility.DomainModelHandlerUtil;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.diagram.editor.DiagramEditorInput;
import nexcore.tool.uml.ui.core.editor.input.UMLEditorInput;
import nexcore.tool.uml.ui.core.project.ITreeNode;
import nexcore.tool.uml.ui.core.util.ProjectUtil;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.transaction.TransactionalCommandStack;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.common.util.CacheAdapter;
import org.eclipse.uml2.uml.Element;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.registry</li>
 * <li>설  명 : ResourceUnloader</li>
 * <li>작성일 : 2012. 7. 4.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public class ResourceUnloader {
    
    /**
     * unloadQueue
     */
    private BlockingQueue<Resource> unloadQueue;

    /**
     * instance
     */
    private static ResourceUnloader instance;

    /**
     * getInstance
     *  
     * @return ResourceUnloader
     */
    public static ResourceUnloader getInstance() {
        if (instance == null) {
            instance = new ResourceUnloader();
        }

        return instance;
    }
    

    
    /**
     * unloader
     */
    ResourceUnloaderThread unloader;

    /**
     * ResourceUnloader
     */
    private ResourceUnloader() {
        unloadQueue = new LinkedBlockingQueue<Resource>(500);
        
//        addResourceLoadedAdapter();
        
        unloader = new ResourceUnloaderThread();
        unloader.start();
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
                                        System.out.println("RESOURCE__IS_LOADED : "+r.getURI().toString());
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
    
    class ResourceUnloaderThread extends Thread {

        public ResourceUnloaderThread() {
        }

        public void run() {
            while (true) {
                try {
                    // queue에 data가 없으면 알아서 wait하고 있음
                    Resource resource = unloadQueue.take();

                    ResourceManager.getInstance().setActivation(resource.getURI(), false);
                    doUnload(resource);
                    ResourceManager.getInstance().setActivation(resource.getURI(), false);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (unloadQueue.size() == 0) {
                        Display.getDefault().syncExec(new Runnable() {

                            @Override
                            public void run() {
                                ProjectUtil.refreshWholeExplorer();
                            }

                        });
                    }
                }
            }
        }
    }
    
    /**
     * doUnload
     *  
     * @param resource void
     */
    private void doUnload(Resource resource) {
        if (resource != null && resource.isLoaded()) {
            
            List<Element> dependentElementList = new ArrayList<Element>();
            
            Set<Element> inverseReferenceList = ResourceManager.getInstance().inverseReferenceElementList(resource);

            if (!inverseReferenceList.isEmpty()) {
                dependentElementList.addAll(inverseReferenceList);
            }
            
            Set<URI> closeEditorURISet = new LinkedHashSet<URI>();
            closeEditorURISet.add(resource.getURI());
            
            
            for (int i = 0; i < dependentElementList.size(); i++) {
                EObject rootContainer = EcoreUtil.getRootContainer(dependentElementList.get(i));
                if (rootContainer != null && rootContainer.eResource() != null) {
                    closeEditorURISet.add(rootContainer.eResource().getURI());
                }
            }
            // 에디터 닫기
            closeEditor(closeEditorURISet);
            
            closeModel(resource);
        }
    }
    
    /**
     * put
     *  
     * @param resource void
     */
    public void put(Resource resource) {
        try {
            if(unloader == null || !unloader.isAlive()) {
                unloader = new ResourceUnloaderThread();
                unloader.start();
                
                Thread.sleep(100);
            }
            unloadQueue.put(resource);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    

    /**
     * closeModel
     *  
     * @param resource void
     */
    private void closeModel(Resource resource) {
        Set<Resource> unloadResourceList = new LinkedHashSet<Resource>();
//System.out.println("closeModel -----------------------------------> "+resource.getURI().toString());
        unloadResourceList.add(resource);
        
//        EcoreUtil.ExternalCrossReferencer
        
        tempSet.clear();
        Set<Resource> referenceResource = findCrossReferenceResource(resource);
        if (!referenceResource.isEmpty()) {
            unloadResourceList.addAll(referenceResource);
        }
        
        Map<URI, Resource> uriResourceMap = ((ResourceSetImpl) DomainRegistry.getEditingDomain().getResourceSet()).getURIResourceMap();
        
        Set<Resource> resourceHashSet = new HashSet<Resource>();

//        for (Iterator<URI> iterator = uriResourceMap.keySet().iterator(); iterator.hasNext();) {
//            URI uri = (URI) iterator.next();
//            resourceHashSet.add(uriResourceMap.get(uri));
//        }
        
        resourceHashSet.addAll(Arrays.asList(uriResourceMap.values().toArray(new Resource[0])));
        
        for (Iterator<Resource> iterator = resourceHashSet.iterator(); iterator.hasNext();) {
            Resource res = iterator.next();

            if (res != null && res.getContents() != null && !res.getContents().isEmpty()) {
                EObject rootContainer = EcoreUtil.getRootContainer(res.getContents().get(0));
                if (rootContainer != null && rootContainer.eResource() != null) {
                    URI rootURI = rootContainer.eResource().getURI();

                    if (resource.getURI() == rootURI) {
                        unloadResourceList.add(res);
                    } else if (ProjectUtil.isModelFile(res)) {
                        // res 의 rootContainer 와 res 가 같은 경우 해당 모델은 닫혀 있는 경우이다.
                        // 따라서 해당 리소스도 unload.
                        if (rootContainer.eResource() == res) {
                            // res 가 닫으려고하는 모델 이외의 모델에서 참조되고 있는지 체크
                            if (ProjectUtil.isFragmentFile(res)) {
                                Set<Element> inverseReferenceElementList = ResourceManager.getInstance()
                                    .inverseReferenceElementList(res);
                                boolean isContains = false;
                                for (Iterator<?> ir = inverseReferenceElementList.iterator(); ir.hasNext();) {
                                    Element element = (Element) ir.next();
                                    if (element != null && element.eResource() != null && resource != null
                                        && resource.getURI() != null) {
                                        if (element.eResource().getURI() == resource.getURI()) {
                                            continue;
                                        } else {
                                            isContains = true; // 닫으려고하는 모델 이외의
                                                               // 모델에서
                                                               // 참조 되고 있으므로
                                                               // unload
                                                               // 에서 제외.
                                        }
                                    }
                                }

                                if (!isContains) {
                                    unloadResourceList.add(res);
                                }
                            } else {
                                boolean active = ResourceManager.getInstance().isActive(rootURI);
                                if (!active) {
                                    unloadResourceList.add(res);
                                }
                            }
                        }
                    }
                } else {
                    if (UMLDebug.DEBUG) {
                        System.err.println("rootContainer is null. --------> " + res.getURI().toString());
                    }
                }
            }
        }
        
        for (Iterator<Resource> ir = unloadResourceList.iterator(); ir.hasNext();) {
            Resource unloadResource = (Resource) ir.next();
            IFile file = WorkspaceSynchronizer.getFile(unloadResource);
            URI uri = unloadResource.getURI();

            if(file != null && file.exists()) {
                // resource 저장이 필요한지 체크 
                if (unloadResource.isModified()) {
                    DomainModelHandlerUtil.save(unloadResource, false);
                }
            }
            
            Map<String, ITreeNode> treeNodes = ProjectRegistry.UMLTreeNodeRegistry.getTreeNodes();
            List<Object> cleanTarget = new ArrayList<Object>();
            
            for (Iterator<String> iterator = treeNodes.keySet().iterator(); iterator.hasNext();) {
                String uriKey = (String) iterator.next();
                if (uriKey.indexOf(uri.toString()) > -1) {
                    cleanTarget.add(uriKey);
                    //treeNodes.get(uriKey).refresh();
                }
            }
            
            // UMLTreeNodeRegistry.getTreeNodes() 에서 관련된 키 삭제
            for (Object o : cleanTarget) {
                ProjectRegistry.UMLTreeNodeRegistry.removeTreeNode(o);
            }
            
            try {
                uri = unloadResource.getURI();
                ProjectRegistry.UMLTreeNodeRegistry.removeTreeNode(uri);
            } catch (Exception e) {
                ALMLogger.getLog(UiCorePlugin.PLUGIN_ID).error(e.getMessage(), e);
            }
        }

        final Set<Resource> unloadList = unloadResourceList;

        TransactionalEditingDomain editingDomain = DomainModelHandlerUtil.getEditingDomain();

        TransactionalCommandStack commandStack = (TransactionalCommandStack) editingDomain.getCommandStack();
        try {
            RecordingCommand command = new RecordingCommand(DomainRegistry.getEditingDomain()) {
                @Override
                protected void doExecute() {
                    for (Iterator<Resource> ir = unloadList.iterator(); ir.hasNext();) {
                        final Resource unloadResource = (Resource) ir.next();
                        try {
                            final ResourceSet resourceSet = DomainRegistry.getUMLDomain().getResourceSet();
                            if (UMLDebug.DEBUG) {
                                System.out.println("unload ----------->>>>" + unloadResource.getURI().toString());
                            }

                            CacheAdapter.INSTANCE.clear(unloadResource);
                            if (resourceSet != null) {
                                resourceSet.getResources().remove(unloadResource);
                            }
                            if (unloadResource.isLoaded()) {
                                unloadResource.unload();
                            }
                        } catch (Exception e) {
                            ALMLogger.getLog(UiCorePlugin.PLUGIN_ID).error(e.getMessage(), e);
                        }

                    }
                }
            };

            commandStack.execute(command,
                Collections.singletonMap(Transaction.OPTION_NO_NOTIFICATIONS, Boolean.TRUE));
        } catch (Exception ee) {
            ee.printStackTrace();
        }

                
            
    }
    
    private Set<Resource> tempSet = new HashSet<Resource>();

    private Set<Resource> findCrossReferenceResource(Resource resource) {
        if (tempSet.contains(resource)) {
            return Collections.emptySet();
        }
        tempSet.add(resource);
        
        Set<Resource> unloadResourceList = new LinkedHashSet<Resource>();

        Map<EObject, Collection<Setting>> find = EcoreUtil.CrossReferencer.find(resource.getContents());

        for (Iterator<?> iterator = find.keySet().iterator(); iterator.hasNext();) {
            EObject type = (EObject) iterator.next();
            if (ProjectUtil.isModelFile(type.eResource())) {
                unloadResourceList.add(type.eResource());
                
                Set<Resource> referenceResource = findCrossReferenceResource(resource);
                if(!referenceResource.isEmpty()) {
                    unloadResourceList.addAll(referenceResource);
                }
            }
        }

        return unloadResourceList;
    }
    
    /**
     * closeEditor
     *  
     * @param closeEditorURI void
     */
    private void closeEditor(Set<URI> closeEditorURI) {
        for (final IWorkbenchWindow window : PlatformUI.getWorkbench().getWorkbenchWindows()) {
            IEditorReference[] editorRefs = window.getActivePage().getEditorReferences();

            for (IEditorReference ref : editorRefs) {
                final IEditorPart editor = ref.getEditor(false);
                URI editorUri = null;
                if (editor != null && editor.getEditorInput() instanceof UMLEditorInput) {
                    try {
                        Element element = ((UMLEditorInput) editor.getEditorInput()).getElement();
                        editorUri = element.eResource().getURI();
                    } catch (Exception e) {
                        // ignore
                    }
                } else if (editor != null && editor.getEditorInput() instanceof DiagramEditorInput) {
                    editorUri = ((DiagramEditorInput) editor.getEditorInput()).getURI();
                } else {
                    if (UMLDebug.DEBUG) {
                        System.out.println(editor.getEditorInput());
                    }
                }
                
                if( editorUri == null) {
                    continue;
                }
                
                Resource resource = DomainRegistry.getEditingDomain().getResourceSet().getResource(editorUri, false);
                if (resource != null && resource.getContents() != null && !resource.getContents().isEmpty()) {
                    EObject rootContainer = EcoreUtil.getRootContainer(resource.getContents().get(0));
                    if (rootContainer != null) {
                        URI fragmentOfrootURI = rootContainer.eResource().getURI();
                        if (closeEditorURI.contains(fragmentOfrootURI)) {
                            Display.getDefault().asyncExec(new Runnable() {
                                public void run() {
                                    window.getActivePage().closeEditor(editor, true);
                                }
                            });
                        }
                    }
                }

            }
        }
    }
}
