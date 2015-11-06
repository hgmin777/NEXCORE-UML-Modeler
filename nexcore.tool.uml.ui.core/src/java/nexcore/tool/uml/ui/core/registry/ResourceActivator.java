/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.core.registry;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import nexcore.tool.uml.manager.transaction.TransactionalAction;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.util.ProjectUtil;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.Model;

/**
 * 
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.registry</li>
 * <li>설 명 : ResourceActivator</li>
 * <li>작성일 : 2012. 7. 26.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public class ResourceActivator {

    /**
     * activateQueue
     */
    private BlockingQueue<URI> activateQueue;

    /**
     * instance
     */
    private static ResourceActivator instance;

    /**
     * getInstance
     * 
     * @return ResourceActivator
     */
    public static ResourceActivator getInstance() {
        if (instance == null) {
            instance = new ResourceActivator();
        }

        return instance;
    }

    /**
     * unloader
     */
    ResourceActivatorThread resourceActivator;

    /**
     * ResourceUnloader
     */
    private ResourceActivator() {
        activateQueue = new LinkedBlockingQueue<URI>(500);

        resourceActivator = new ResourceActivatorThread();
        resourceActivator.start();
    }

    class ResourceActivatorThread extends Thread {

        public ResourceActivatorThread() {
        }

        public void run() {
            while (true) {
                try {
                    // queue에 data가 없으면 알아서 wait하고 있음
                    URI uri = activateQueue.take();
                    Map<URI, Resource> uriResourceMap = ((ResourceSetImpl) DomainRegistry.getEditingDomain()
                        .getResourceSet()).getURIResourceMap();
                    Resource resource = uriResourceMap.get(uri);
                    if (resource == null) {
                        Thread.sleep(100);
                        resource = uriResourceMap.get(uri);
                    }
                    if (resource != null && !resource.getContents().isEmpty()) {
                        EObject rootContainer = EcoreUtil.getRootContainer(resource.getContents().get(0));
                        if (!ResourceManager.getInstance().isActive(rootContainer.eResource().getURI())) {
                            findRootContainerWithOpen(rootContainer);
                        }
                    }

                    // doLoad(uri);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (activateQueue.size() == 0) {
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

    public static EObject findRootContainerWithOpen(EObject eObject) {
        if (eObject == null) {
            return null;
        }
        EObject parentEObject = getFragmentContainerAnnotation(eObject);
        if (parentEObject != null) {
            // parent 가 Model 이면 root 를 찾은것임.
            // 해당 리소스를 active 상태로 변경.
            if (parentEObject instanceof Model) {
                if (parentEObject != null && parentEObject.eResource() != null) {
                    ResourceManager.getInstance().setActivation(parentEObject.eResource().getURI(), true);
                    return parentEObject;
                }
            } else if (parentEObject instanceof org.eclipse.uml2.uml.Package) {
                EObject rootContainer = EcoreUtil.getRootContainer(parentEObject);
                if (rootContainer != null && rootContainer.eResource() != null) {
                    if (ResourceManager.getInstance().isActive(rootContainer.eResource().getURI())) {
                        return rootContainer;
                    }
                }
                return findRootContainerWithOpen(parentEObject);
            }
        }

        return null;
    }

    public static EObject getFragmentContainerAnnotation(EObject eobject) {
        if (eobject instanceof org.eclipse.uml2.uml.Model) {
            return eobject;
        } else if (eobject instanceof org.eclipse.uml2.uml.Package) {
            org.eclipse.uml2.uml.Package myPackage = (org.eclipse.uml2.uml.Package) eobject;
            EAnnotation eAnnotation = myPackage.getEAnnotation(UICoreConstant.PROJECT_CONSTANTS__FRAGMENT_CONTAINER);

            if (eAnnotation != null && !eAnnotation.getReferences().isEmpty()) {
                return eAnnotation.getReferences().get(0);
            }

        }

        return null;
    }

    /**
     * doLoad
     * 
     * @param resource
     *            void
     */
    private void doLoad(URI uri) {
        if (uri != null) {
            IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
            IFile file = root.getFile(new Path(uri.toString()));
            if (file.exists()) {
                final Resource resource = DomainRegistry.getEditingDomain().getResourceSet().getResource(uri, true);
                if (resource != null) {
                    DomainUtil.run(new TransactionalAction() {
                        @Override
                        public void doExecute() {
                            EcoreUtil.resolveAll(resource);
                        }
                    });
                }
            }
        }
    }

    /**
     * put
     * 
     * @param resource
     *            void
     */
    public void put(URI uri) {
        try {
            if (resourceActivator == null || !resourceActivator.isAlive()) {
                resourceActivator = new ResourceActivatorThread();
                resourceActivator.start();

                Thread.sleep(200);
            }
            activateQueue.put(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
