/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.project.explorer.provider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nexcore.alm.common.ui.util.ALMLogger;
import nexcore.tool.mdd.core.extension.IDomainModelHandler;
import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.core.util.UMLLoginController;
import nexcore.tool.uml.core.util.UMLNexcoreLoginEvent;
import nexcore.tool.uml.core.util.UMLNexcoreLoginListener;
import nexcore.tool.uml.manager.transaction.TransactionalAction;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.manager.utility.DomainRegistry.ResourceStampRegistry;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.diagram.editor.AbstractDiagramEditor;
import nexcore.tool.uml.ui.core.project.ClosedTreeNode;
import nexcore.tool.uml.ui.core.project.IResourceSetListener;
import nexcore.tool.uml.ui.core.project.ITreeNode;
import nexcore.tool.uml.ui.core.project.UMLAbstractTreeNode;
import nexcore.tool.uml.ui.core.project.UMLFileTreeNode;
import nexcore.tool.uml.ui.core.project.UMLTreeNode;
import nexcore.tool.uml.ui.core.registry.ProjectRegistry;
import nexcore.tool.uml.ui.core.registry.ProjectRegistry.ContentProviderRegistry;
import nexcore.tool.uml.ui.core.registry.ProjectRegistry.ProjectResourceSetListenerRegistry;
import nexcore.tool.uml.ui.core.registry.ProjectRegistry.UMLTreeNodeRegistry;
import nexcore.tool.uml.ui.core.registry.ProjectRegistry.ViewerRegistry;
import nexcore.tool.uml.ui.core.registry.ResourceManager;
import nexcore.tool.uml.ui.core.registry.ResourceUnloader;
import nexcore.tool.uml.ui.core.util.PreferenceUtil;
import nexcore.tool.uml.ui.core.util.ProjectUtil;
import nexcore.tool.uml.ui.project.ProjectExplorerPlugin;
import nexcore.tool.uml.ui.project.editor.ModelMultiPageEditor;
import nexcore.tool.uml.ui.project.explorer.ExplorerElementFilter;

import org.eclipse.core.internal.resources.Workspace;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonContentProvider;
import org.eclipse.ui.navigator.SaveablesProvider;
import org.eclipse.uml2.common.util.CacheAdapter;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.ProfileApplication;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.provider</li>
 * <li>설 명 : UMLContentProvider</li>
 * <li>작성일 : 2009. 11. 24.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
@SuppressWarnings("restriction")
public class UMLContentProvider implements ICommonContentProvider, IAdaptable,
IResourceSetListener {

    /** Workspace 내의 파일과 리소스 상태를 동기화 */
    private WorkspaceSynchronizer workspaceSynchronizer;

    /** UML Domain */
    // private UMLDomain umlDomain;
    private IDomainModelHandler umlDomain;

    /** Adapter factory content provider. */
    private AdapterFactoryContentProvider adapterFactoryContentProvider;

    /** TransactionalEditingDomain */
    private TransactionalEditingDomain editingDomain;

    /** ResourceSetListener */
    private ProjectResourceSetListener rsListener = new ProjectResourceSetListener();

    /**
     * constructor.
     */
    public UMLContentProvider() {

        // adapterFactoryContentProvider = new
        // AdapterFactoryContentProvider(UiCorePlugin.getDefault()
        // .getItemProvidersAdapterFactory());
        adapterFactoryContentProvider = ProjectUtil.getAdapterFactoryContentProvider();

        createDomain();
        editingDomain = umlDomain.getTransactionalEditingDomain();
        startResourceListening();
        editingDomain.addResourceSetListener(rsListener);
        ContentProviderRegistry.setContentProvider(this);

        ProjectResourceSetListenerRegistry.setResourceSetListener(rsListener);

        // // resourceSetChanged 을 컨트롤 하기 위한 listener
        // ProjectResourceSetListenerController.getInstance().addControllerListener(rsListener);

        // Preference 값 init
        new PreferenceUtil();

//        UMLLoginController.addLoginListener(new INexcoreLoginListener() {
//            public void changed(INexcoreLoginEvent event) {
//                if (event.getType() == INexcoreLoginEvent.LOG_IN) {
//                    logined = true;
//                } else if (event.getType() == INexcoreLoginEvent.LOG_OUT) {
//                    logined = false;
//                }
//            }
//        });

//        UMLLoginController.addLoginListener(new UMLNexcoreLoginListener() {
//            
//            @Override
//            public void changed(UMLNexcoreLoginEvent event) {
//                synchronized (this) {
//                    if (event.getType() == UMLNexcoreLoginEvent.LOG_IN) {
//                        
//                    } else if (event.getType() == UMLNexcoreLoginEvent.LOG_OUT) {
//                        if (explorerSaveablesProvider != null) {
//                        }
//                    }
//                }
//            }
//        });
    }

    // private boolean logined = false;

    /**
     * 
     * void
     */
    public void startResourceListening() {
        workspaceSynchronizer = new WorkspaceSynchronizer(editingDomain, new WorkspaceSynchronizer.Delegate() {

            /**
             * @see org.eclipse.emf.workspace.util.WorkspaceSynchronizer.Delegate#dispose()
             */
            public void dispose() {
            }

            /**
             * @see org.eclipse.emf.workspace.util.WorkspaceSynchronizer.Delegate#handleResourceChanged(org.eclipse.emf.ecore.resource.Resource)
             */
            public boolean handleResourceChanged(Resource changedResource) {
                final IFile file = WorkspaceSynchronizer.getFile(changedResource);
                final String stampId = changedResource.getURI().toString();
                long stamp = ResourceStampRegistry.getResourceStamp(stampId);
                if (file == null) {
                    return true;
                }
                
                final boolean isModified = (file.getModificationStamp() != stamp);

                if (isModified) {
                    try {
                        try {
                            ResourceUnloader.getInstance().put(changedResource);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }

                        editingDomain.runExclusive(new Runnable() {
                            public void run() {
                                try {
                                    // svn 을 통해 update 받거나 혹은 텍스트 에디터 등으로 모델 정보를
                                    // 직접 수정한경우 처리.
                                    ViewerRegistry.getViewer().getControl().getDisplay().asyncExec(new Runnable() {
                                        /**
                                         * @see java.lang.Runnable#run()
                                         */
                                        public void run() {
                                            try {
                                                file.getParent().refreshLocal(IResource.DEPTH_INFINITE, null);
                                            } catch (CoreException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                // just returns if no readers waiting
                                editingDomain.yield(); 
                            }
                        });
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }

                return true;
            }

            /**
             * @see org.eclipse.emf.workspace.util.WorkspaceSynchronizer.Delegate#handleResourceDeleted(org.eclipse.emf.ecore.resource.Resource)
             */
            public boolean handleResourceDeleted(final Resource deletedResource) {
                
                if (deletedResource == null) {
                    return true;
                }
                
                try {
                    editingDomain.runExclusive(new Runnable() {
                        public void run() {
                            try {
                                removeFragmentResource(deletedResource);
                                
                                Map<String, ITreeNode> treeNodes = ProjectRegistry.UMLTreeNodeRegistry.getTreeNodes();
                                List<Object> cleanTarget = new ArrayList<Object>();
                                URI uri = deletedResource.getURI();

                                for (Iterator<String> iterator = treeNodes.keySet().iterator(); iterator.hasNext();) {
                                    String uriKey = (String) iterator.next();
                                    if (uriKey.indexOf(uri.toString()) > -1) {
                                        cleanTarget.add(uriKey);
                                        treeNodes.get(uriKey).refresh();
                                    }
                                }

                                // UMLTreeNodeRegistry.getTreeNodes() 에서 관련된 키 삭제
                                for (Object o : cleanTarget) {
                                    ProjectRegistry.UMLTreeNodeRegistry.removeTreeNode(o);
                                }

                                try {
                                    ResourceSet resourceSet = DomainRegistry.getUMLDomain().getResourceSet();

                                    uri = URI.createURI(uri.toString());
                                    ProjectRegistry.UMLTreeNodeRegistry.removeTreeNode(uri);

                                    resourceSet.getURIConverter().getURIMap().remove(uri);
                                    CacheAdapter.INSTANCE.clear(deletedResource);
                                    
                                    // 윈도우 탐색기에서 모델 삭제 후 새로고침 했을때 리소스가 남아 있는 문제 해결.
                                    resourceSet.getResources().remove(deletedResource);
                                } catch (Exception e) {
                                    ALMLogger.getLog(UiCorePlugin.PLUGIN_ID).error(e.getMessage(), e);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            
                            editingDomain.yield(); // just returns if no readers waiting 
                        }});
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }

                return true;
            }

            /**
             * 
             * 
             * 
             * @param parentResource
             *            void
             */
            private void removeFragmentResource(final Resource parentResource) {
                
                try {
                    editingDomain.runExclusive(new Runnable() {
                        public void run() {
                            EList<EObject> contents = parentResource.getContents();
                            if (contents != null && contents.size() > 0) {
                                EObject eobject = contents.get(0);

                                if (!AdapterFactoryEditingDomain.isControlled(eobject)) {
                                    EAnnotation eAnnotation = ResourceManager.getInstance().getFragmentAnnotation(eobject);
                                    if (eAnnotation == null)
                                        return;
                                    EList<EObject> references = eAnnotation.getReferences();
                                    for (EObject reference : references) {
                                        if (reference != null && reference.eResource() != null) {
                                            ResourceUnloader.getInstance().put(reference.eResource());
                                        }
                                    }
                                }
                            }
                            
                            editingDomain.yield(); // just returns if no readers waiting 
                        }});
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                
            }

            /**
             * @see org.eclipse.emf.workspace.util.WorkspaceSynchronizer.Delegate#handleResourceMoved(org.eclipse.emf.ecore.resource.Resource,
             *      org.eclipse.emf.common.util.URI)
             */
            public boolean handleResourceMoved(final Resource resource, final URI uri) {
                try {
                    editingDomain.runExclusive(new Runnable() {
                        public void run() {
                            ResourceManager.getInstance().setActivation(resource.getURI(), false);
                            ResourceUnloader.getInstance().put(resource);

                            if (DomainRegistry.getUMLDomain().getResource(uri, true) == null) {
                                umlDomain.getResourceSet().createResource(uri);
                            }
                            
                            editingDomain.yield(); // just returns if no readers waiting 
                        }});
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                
                return true;
            }

        });
    }

    /**
     * 
     * void
     */
    public void stopResourceListening() {

        if (workspaceSynchronizer != null) {
            workspaceSynchronizer.dispose();
            workspaceSynchronizer = null;
        }
    }

    /**
     * if there's no UMLDomain, creates UMLDomain.
     */
    private void createDomain() {
        umlDomain = DomainRegistry.getUMLDomain();

        if (umlDomain == null) {
            // umlDomain = new UMLDomain();
            // DomainRegistry.setUMLDomain(umlDomain);
            umlDomain = ProjectUtil.createDomain(true);
        }

        // 선행 초기화 작업 실행
        // modified pns 2012.04.16 ResourceManager 에서 처리한다.
        //PrecedingInitializerRegistry.getInstance().executeInitializer();
        
        DomainUtil.registerDefaultPathmaps();
    }

    /**
     * loginListener
     */
    UMLNexcoreLoginListener loginListener = null;
    	
    /**
     * @see org.eclipse.ui.navigator.ICommonContentProvider#init(org.eclipse.ui.navigator.ICommonContentExtensionSite)
     */
	public void init(ICommonContentExtensionSite config) {
		if (loginListener == null) {
			loginListener = new UMLNexcoreLoginListener() {
				/**
				 * 
				 * @see nexcore.tool.uml.core.util.UMLNexcoreLoginListener#changed(nexcore.tool.uml.core.util.UMLNexcoreLoginEvent)
				 */
				public void changed(UMLNexcoreLoginEvent arg0) {
					// 2011-05-16 modified by nspark
					// 프로젝트 탐색기 새로고침 처리는 ResourcManager#addLoginListener 에서
					// 처리한다.
					// ViewerRegistry.getViewer().refresh();
				    
				    for (final IWorkbenchWindow window : PlatformUI.getWorkbench().getWorkbenchWindows()) {
			            IEditorReference[] editorRefs = window.getActivePage().getEditorReferences();

			            for (IEditorReference ref : editorRefs) {
                            final IEditorPart editor = ref.getEditor(false);
                            if (editor instanceof AbstractDiagramEditor) {
                                ((AbstractDiagramEditor) editor).closeEditor(true);

                            } else if (editor instanceof ModelMultiPageEditor) {
                                ((ModelMultiPageEditor) editor).close(true);
                            }
                        }
				    }
//					IEditorPart[] editors = getActivePage().getEditors();
//					for (int i = 0; i < editors.length; i++) {
//
//						if (editors[i] instanceof AbstractDiagramEditor) {
//							((AbstractDiagramEditor) editors[i])
//									.closeEditor(true);
//						} else if (editors[i] instanceof ModelMultiPageEditor) {
//							((ModelMultiPageEditor) editors[i]).close(true);
//						}
//					}

					if (UMLLoginController.getInstance().getLoginState() != UMLNexcoreLoginEvent.LOG_IN) {
						ResourceManager.setChecked(true);
					} else {
						ResourceManager.setChecked(false);
					}
				}
			};

			UMLLoginController.addLoginListener(loginListener);
		}
	}

    /**
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
     */
    public Object[] getChildren(Object parentElement) {
        boolean isProxy = false;

        if (parentElement instanceof ClosedTreeNode) {
            return new Object[0];
        } else if (parentElement instanceof ITreeNode) {
            ITreeNode node = (ITreeNode) parentElement;
            return wrapEObjects(adapterFactoryContentProvider.getChildren(node.getEObject()), parentElement, isProxy);

        } else if (parentElement instanceof IFolder) {
            try {
                IFolder folder = (IFolder) parentElement;
                if (folder.getProject().hasNature(UICoreConstant.PROJECT_CONSTANTS__NATURE_ID)) {
                    return getModelNodes(folder);
                }
            } catch (CoreException e) {
                Log.error(e);
            }

        } else if (parentElement instanceof IFile) {
            IFile file = (IFile) parentElement;
            if (ProjectUtil.isModelFile(file)) {
                URI uri = URI.createURI(file.getFullPath().toString());

                boolean active = ResourceManager.getInstance().isActive(uri);
                if (active) {
                    return new Object[] { new ClosedTreeNode(file) };
                }
                Resource resource = getResource(uri);

                if (resource != null && !resource.getContents().isEmpty()) {
                    return getChildren(UMLTreeNodeRegistry.getTreeNode(resource.getContents().get(0)));
                }
            }
        }

        return null;
    }

    /**
     * resourceMap
     */
    private Map<URI, Resource> resourceMap;
    
    private Resource getResource(URI uri) {
        Resource resource = DomainRegistry.getUMLDomain().getResource(uri, true);
        if (resource != null && !resource.getContents().isEmpty()) {
            if (!resource.isTrackingModification()) {
                resource.setTrackingModification(true);
            }
            final Resource umlResource = resource;

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
        
        return resource;
    }

    /**
     * getModelNodes
     *  
     * @param folder
     * @return
     * @throws CoreException Object[]
     */
    private synchronized Object[] getModelNodes(final IFolder folder) throws CoreException {
        // 인증 췍.
        if (UMLLoginController.getInstance().getLoginState() != UMLNexcoreLoginEvent.LOG_IN) {

            // ResourceManager에서 isChecked를 관리하여 Refresh를 실행했을 때 다시 안내 메시지를 띄우도록
            // 한다.
            // 기존에는 ContentProvider에서 관리하여 로그인 안내메시지가 단 한번만 나타났었다.
            if (ResourceManager.isChecked()) {
//                MessageDialog.openInformation(ProjectExplorerPlugin.getShell(),
//                    UMLMessage.LABEL_NEXCORE_UML_MODELER,
//                    UMLMessage.MESSAGE_LOGIN_ERROR);// "NEXCORE UML Modeler",
                // "본 기능에 대한 권한이 없습니다. 로그인을 해주세요.");
                ResourceManager.setChecked(false);
            }
            
            return null;
        }
        List<IFile> list = ProjectUtil.getModelFiles(folder);
        Set<Object> result = new HashSet<Object>();
//        resourceMap = new HashMap<URI, Resource>();
        resourceMap = ((ResourceSetImpl) DomainRegistry.getUMLDomain().getResourceSet()).getURIResourceMap();
        for (final IFile iFile : list) {
            final URI uri = URI.createURI(iFile.getFullPath().toString());
            
            boolean active = ResourceManager.getInstance().isActive(uri);
            if( !active ) {
                continue;
            }
            
            if (!resourceMap.containsKey(uri)) {
                Display.getDefault().syncExec(new Runnable() {
                    public void run() {
                        Resource resource = getResource(uri);
                        
                        if (resource != null && resource.getContents().size() > 0) {
                            if (resource.getContents().get(0) instanceof DynamicEObjectImpl) {
                                DynamicEObjectImpl dObject = (DynamicEObjectImpl) resource.getContents().get(0);
                                if (dObject.dynamicGet(0) == null) {
                                    resource.unload();
                                    resource = DomainRegistry.getUMLDomain().getResource(uri, true);
                                    if(resource != null && !resource.isTrackingModification()){
                                        resource.setTrackingModification(true);
                                    }
                                }
                            }
                        }

                        if (resource != null && !resource.isLoaded()) {
                            try {
                                resource.load(DomainUtil.getLoadOptions());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        }

        IFile file = null;
        URI uri;
        Resource resource = null;
        UMLFileTreeNode node;

        // 2011-04-14 modified by nspark.
        for (int i = 0; i < list.size(); i++) {
            file = list.get(i);
            uri = URI.createURI(file.getFullPath().toString());
            
            boolean active = ResourceManager.getInstance().isActive(uri);
            if( !active ) {
                result.add(new ClosedTreeNode(file));
                continue;
            }
            
            resource = getResource(uri);
            if(resource == null) {
            	continue;
            }

            for (EObject obj : resource.getContents()) {
                if (obj instanceof Model) {
                    Object treeNode = UMLTreeNodeRegistry.getTreeNode(obj);
                    if (treeNode != null) {
                        node = (UMLFileTreeNode) treeNode;
                        node.refreshNode();
                        result.add(node);
                    } else {
                        node = new UMLFileTreeNode(file.getFullPath(),
                            (Workspace) file.getWorkspace(),
                            obj,
                            (Object) folder,
                            false,
                            adapterFactoryContentProvider);
                        result.add(node);
                        UMLTreeNodeRegistry.setTreeNode(obj, node);
                    }

                }
            }
        }
        return result.toArray();
    }

    /**
     * wrap EObject for project explorer to handle with ITreeNode.<br>
     * register nodes to {@link UMLTreeNodeRegistry}.
     * 
     * @param children
     * @param parentElement
     * @param isProxy
     * @return
     */
    private Object[] wrapEObjects(Object[] objects, Object parentElement, boolean isProxy) {

        Collection<Object> result = new ArrayList<Object>();
        ITreeNode node = null;
        if (parentElement instanceof UMLTreeNode) {
            EObject parentEobj = ((UMLTreeNode) parentElement).getEObject();
            if (parentEobj instanceof Diagram)
                return null;
        }

        EObject eobject;
        IFile fragmentedFile;
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] instanceof EObject) {
                eobject = (EObject) objects[i];
                node = null;

                // if( FilterRegistry.getFilteredList().contains(
                // eobject.eClass().getName()) ) {
                // continue;
                // }

                if (eobject instanceof EAnnotation) {
                    String annotation = ((EAnnotation) eobject).getSource();
                    if (!UICoreConstant.PROJECT_CONSTANTS__DIAGRAM.equals(annotation)) {
                        continue;
                    }
                    // if(ProjectConstants.FRAGMENT.equals(annotation) ||
                    // ProjectConstants.FRAGMENT_CONTAINER.equals(annotation))
                    // continue;
                }

                Resource resource = eobject.eResource();
                if (DomainUtil.isProxy(eobject)) {
                    if (resource != null){
                        eobject = EcoreUtil.resolve(eobject, resource.getResourceSet());
                    }
                }

                // 단편화된 UML element 처리
                if (AdapterFactoryEditingDomain.isControlled(eobject)) {
                    node = ProjectRegistry.UMLTreeNodeRegistry.getTreeNode(eobject);
                    if (!(node instanceof UMLFileTreeNode)) {
                        fragmentedFile = WorkspaceSynchronizer.getFile(resource);

                        if (fragmentedFile != null && fragmentedFile.exists()) {
                            ProjectRegistry.UMLTreeNodeRegistry.removeTreeNode(eobject);
                            node = ProjectRegistry.UMLTreeNodeRegistry.getTreeNode(eobject);

                            if (node == null) {
                                node = new UMLFileTreeNode(fragmentedFile.getFullPath(),
                                    (Workspace) fragmentedFile.getWorkspace(),
                                    eobject,
                                    parentElement,
                                    true,
                                    adapterFactoryContentProvider);
                            }
                        }
                    }

                } else {
                    if (!isProxy) {
                        node = ProjectRegistry.UMLTreeNodeRegistry.getTreeNode(eobject);
                        // 2011-04-14 modified by nspark
                        // ProjectRegistry.UMLTreeNodeRegistry.removeTreeNode(eobject);
                    }

                    if (node == null || node instanceof IFile) {
                        ProjectRegistry.UMLTreeNodeRegistry.removeTreeNode(eobject);

                        // EAnnotation을 제외한 Diagram 노드 추가
                        // } else if (eobject instanceof EAnnotation) {
                        if (eobject instanceof EAnnotation) {
                            EAnnotation eannotation = (EAnnotation) eobject;
                            if (UICoreConstant.PROJECT_CONSTANTS__DIAGRAM.equals(eannotation.getSource())) {
                                EList<EObject> elist = eannotation.getContents();
                                for (EObject object : elist) {
                                    node = ProjectRegistry.UMLTreeNodeRegistry.getTreeNode(eobject);
                                    if (node == null) {
                                        node = new UMLTreeNode(object, parentElement, adapterFactoryContentProvider);
                                        ProjectRegistry.UMLTreeNodeRegistry.setTreeNode(object, node);
                                    }
                                    result.add(node);
                                }
                                continue;
                            }
                        } else if (eobject instanceof ProfileApplication) {
                            // 단편 파일에 프로파일 정보는 표시하지 않는다.
                            if (parentElement instanceof UMLFileTreeNode) {
                                UMLFileTreeNode parentTreeNode = (UMLFileTreeNode) parentElement;
                                if (AdapterFactoryEditingDomain.isControlled(parentTreeNode.getEObject())) {
                                    continue;
                                }
                            }
                            
                            // Model에 포함된 프로파일만 표시하기 위함. 최상위의 프로파일 정보만 표시함.
                            ProfileApplication profileApplication = (ProfileApplication) eobject;
                            if(!(profileApplication.eContainer() instanceof Model)) {
                                continue;
                            }
                            
//                            ProfileApplication profileApplication = (ProfileApplication) eobject;
//
//                            try {
//                                Profile newProfile = profileApplication.getAppliedProfile();
//                                if (newProfile.eIsProxy()) {
//                                    if (parentElement instanceof ITreeNode) {
//                                        try {
//                                            EObject parentEobj = ((ITreeNode) parentElement).getEObject();
//                                            HandleProfileCommand command = new HandleProfileCommand(DomainRegistry.getEditingDomain(),
//                                                (Package) parentEobj,
//                                                newProfile,
//                                                false);
//
//                                            DomainModelHandlerUtil.getEditingDomain()
//                                                .getCommandStack()
//                                                .execute(command);
//
//                                            DomainModelHandlerUtil.save(parentEobj.eResource(), true);
//                                        } catch (Exception e) {
//                                            e.printStackTrace();
//                                        }
//                                    }
//
//                                } else {
//                                    node = new UMLTreeNode(eobject, parentElement, adapterFactoryContentProvider);
//                                    result.add(node);
//                                    ProjectRegistry.UMLTreeNodeRegistry.setTreeNode(eobject, node);
//                                }
//                            } catch (Exception e) {}
//                            continue;
                        }
                        node = new UMLTreeNode(eobject, parentElement, adapterFactoryContentProvider);
                    }
                }
                result.add(node);
                ProjectRegistry.UMLTreeNodeRegistry.setTreeNode(eobject, node);
            }
        }
        return result.toArray();
    }

    /**
     * eFilter
     */
    ExplorerElementFilter eFilter = null;

    /**
     * getExplorerFilter
     *  
     * @return ExplorerElementFilter
     */
    public ExplorerElementFilter getExplorerFilter() {
        if (eFilter == null) {
            CommonViewer viewer = ViewerRegistry.getViewer();

            if (viewer != null) {
                ViewerFilter[] filter = viewer.getFilters();
                for (int i = 0; i < filter.length; i++) {
                    if (filter[i] instanceof ExplorerElementFilter) {
                        eFilter = (ExplorerElementFilter) filter[i];
                        break;
                    }
                }
            }
        }
        return eFilter;
    }

    /**
     * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
     */
    public boolean hasChildren(Object element) {
        if( element instanceof ClosedTreeNode) {
            return false;
        } else if (element instanceof ITreeNode) {
            ITreeNode treeNode = (ITreeNode) element;
            
            TransactionalEditingDomain transactionalEditingDomain = DomainRegistry.getUMLDomain()
                .getTransactionalEditingDomain();

            synchronized (transactionalEditingDomain) {
                EObject eobject = treeNode.getEObject();
                if (eobject instanceof Diagram) {
                    return false;

                } else {
                    TreeIterator<EObject> iterator = eobject.eAllContents();
                    String annotation = null;
                    while (iterator.hasNext()) {
                        EObject object = (EObject) iterator.next();
                        if (object instanceof EAnnotation) {
                            annotation = ((EAnnotation) object).getSource();
                            if (UICoreConstant.PROJECT_CONSTANTS__DIAGRAM.equals(annotation)) {
                                return true;
                            } else if (UICoreConstant.PROJECT_CONSTANTS__PROJECT_INFO.equals(annotation)) {
                                continue;
                            }
                        } else {
                            // Parameter/Property 노드의 child가 필터 처리가 되어 있는지 여부에
                            // 따라 hasChildren 결과 반환
                            try {
                                ExplorerElementFilter filter = getExplorerFilter();
                                String name = eobject.eClass().getName();

                                if ("Parameter".equals(name) || "Property".equals(name)) {
                                    if (filter.getFilteredList().contains("LiteralBoolean")
                                        || filter.getFilteredList().contains("LiteralInteger")
                                        || filter.getFilteredList().contains("LiteralNull")
                                        || filter.getFilteredList().contains("LiteralString")
                                        || filter.getFilteredList().contains("LiteralUnlimitedNatural")) {
                                        return false;
                                    }
                                }
                            } catch (Exception e) {
                                return true;
                            }

                            return true;
                        }
                    }
                }
            }
        } else if (element instanceof IFolder) {
            try {
                if (((IFolder) element).members().length > 0) {
                    return true;
                }
            } catch (CoreException e) {
                Log.error(e);
            }

        }

        return false;
    }

    /**
     * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
     */
    public Object[] getElements(Object inputElement) {
        return getChildren(inputElement);
    }

    /**
     * @see org.eclipse.jface.viewers.IContentProvider#dispose()
     */
    public void dispose() {
        stopResourceListening();
        editingDomain.removeResourceSetListener(rsListener);
//        ProjectResourceSetListenerController.getInstance().removeControllerListener(rsListener);

        ProjectRegistry.ViewerRegistry.removeViewer();
        ProjectRegistry.UMLTreeNodeRegistry.removeAllTreeNodes();
        
        UMLLoginController.removeLoginListener(loginListener);
    }

    /**
     * @see org.eclipse.ui.navigator.IMementoAware#restoreState(org.eclipse.ui.IMemento)
     */
    public void restoreState(IMemento memento) {
    }

    /**
     * @see org.eclipse.ui.navigator.IMementoAware#saveState(org.eclipse.ui.IMemento)
     */
    public void saveState(IMemento memento) {
    }

    /**
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
     */
    public Object getParent(Object element) {
        if (element instanceof UMLAbstractTreeNode) {
            UMLAbstractTreeNode abstractTreeNode = (UMLAbstractTreeNode) element;
            return abstractTreeNode.getParent();
        }
        return null;
    }

    /**
     * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
     *      java.lang.Object, java.lang.Object)
     */
    public void inputChanged(Viewer pViewer, Object oldInput, Object newInput) {
        if (newInput == null)
            return;

        if (Thread.currentThread() != ProjectExplorerPlugin.getDisplay().getThread())
            return;

    }
    
    /**
     * explorerSaveablesProvider
     */
    ExplorerSaveablesProvider explorerSaveablesProvider = null;
    
    /**
     * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
     */
    @SuppressWarnings("unchecked")
    public Object getAdapter(Class adapter) {
        if (adapter.equals(SaveablesProvider.class)) {
            if (explorerSaveablesProvider == null) {
                explorerSaveablesProvider = new ExplorerSaveablesProvider();
            }
            return explorerSaveablesProvider;
        }
        return null;
    }

	
}

