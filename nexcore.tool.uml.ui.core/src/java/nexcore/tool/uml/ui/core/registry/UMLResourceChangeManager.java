/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.registry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import nexcore.tool.uml.core.util.UMLLoginController;
import nexcore.tool.uml.ui.core.util.ProjectUtil;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.registry</li>
 * <li>설 명 : UMLResourceChangeManager</li>
 * <li>작성일 : 2011. 10. 18.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public class UMLResourceChangeManager {
    
    /**
     * listenerList
     */
    private static List<IUMLResourceChangeListener> listenerList = Collections.synchronizedList(new ArrayList<IUMLResourceChangeListener>());

    /**
     * instance
     */
    private static UMLResourceChangeManager instance;

    /**
     * getInstance
     *  
     * @return UMLResourceChangeManager
     */
    public static UMLResourceChangeManager getInstance() {
        if (instance == null) {
            instance = new UMLResourceChangeManager();
        }

        return instance;
    }

    /**
     * UMLResourceChangeManager
     */
    private UMLResourceChangeManager() {
        ResourcesPlugin.getWorkspace().addResourceChangeListener(new IResourceChangeListener() {
            public void resourceChanged(final IResourceChangeEvent event) {
                if (!UMLLoginController.getInstance().isLogin()) {
                    return;
                }

                IResource resource = event.getResource();
                IResourceDelta delta = event.getDelta();

                if (event.getSource() instanceof IWorkspace) {
                    switch (event.getType()) {
                        case IResourceChangeEvent.POST_CHANGE:
                            try {
                                delta.accept(new IResourceDeltaVisitor() {

                                    public boolean visit(IResourceDelta delta) throws CoreException {
                                        List<IProject> openedProject = new ArrayList<IProject>();
                                        if (delta != null) {
                                            final IResource resource = delta.getResource();
                                            
                                            if (!resource.isDerived()) {
                                                switch (resource.getType()) {
                                                    case IResource.PROJECT: {
                                                        if (delta.getKind() == IResourceDelta.ADDED
                                                            || (delta.getFlags() & IResourceDelta.OPEN) != 0) {
                                                            IProject project = (IProject) delta.getResource();
                                                            if (!ProjectUtil.isActiveUMLProject(project)) {
                                                                return false;
                                                            }
                                                            if (project.isOpen()) {
                                                                openedProject.add(project);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            
//                                            IResourceDelta[] projDeltas = delta.getAffectedChildren(IResourceDelta.CHANGED);
//                                            for (int i = 0; i < projDeltas.length; ++i) {
//                                                IResourceDelta projDelta = projDeltas[i];
//                                                System.err.println(projDelta.getFlags() +" / "+(projDelta.getFlags() & IResourceDelta.OPEN));
//                                                if (delta.getKind() == IResourceDelta.ADDED || (projDelta.getFlags() & IResourceDelta.OPEN) != 0) {
//                                                    IProject project = (IProject) projDelta.getResource();
//                                                    if (!ProjectUtil.isActiveUMLProject(project)) {
//                                                        return false;
//                                                    }
//                                                    if (project.isOpen()) {
//                                                        openedProject.add(project);
//                                                    }
//                                                }
//                                            }
                                        }
                                        if (openedProject.size() > 0) {
                                            fireProjectOpened(openedProject.toArray(new IProject[] {}));
                                        }
                                        return true;
                                    }

                                });
                            } catch (CoreException e1) {
                                e1.printStackTrace();
                            }
                            break;
                        case IResourceChangeEvent.PRE_DELETE:
                            if (resource.getType() == IResource.PROJECT) {
                                firePreProjectDelete(event);
                            }
                        case IResourceChangeEvent.PRE_CLOSE:
                            if (resource.getType() == IResource.PROJECT) {
                                firePreProjectClose(event);
                            }
                            break;
                        case IResourceChangeEvent.PRE_REFRESH:
                            break;
                        case IResourceChangeEvent.PRE_BUILD:
                            break;
                        case IResourceChangeEvent.POST_BUILD:
                            break;
                    }
                }
            }
        });
    }

    /**
     * 로그인/아웃을 알 수 있는 리스너 등록
     * 
     * @param listener
     *            void
     */
    public synchronized void addResourceChangeListener(IUMLResourceChangeListener listener) {
        listenerList.add(listener);
    }

    /**
     * 로그인/아웃을 알 수 있는 리스너 해제
     * 
     * @param listener
     *            void
     */
    public synchronized void removeResourceChangeListener(IUMLResourceChangeListener listener) {
        listenerList.remove(listener);
    }

    /**
     * fireProjectOpened
     *  
     * @param projects void
     */
    private synchronized void fireProjectOpened(IProject[] projects) {
        synchronized (listenerList) {
            for (IUMLResourceChangeListener listener : listenerList) {
                listener.projectOpened(projects);
            }
        }
    }

    /**
     * firePreProjectClose
     *  
     * @param event void
     */
    private synchronized void firePreProjectClose(IResourceChangeEvent event) {
        synchronized (listenerList) {
            for (IUMLResourceChangeListener listener : listenerList) {
                listener.preProjectClose(event);
            }
        }
    }

    /**
     * firePreProjectDelete
     *  
     * @param event void
     */
    private synchronized void firePreProjectDelete(IResourceChangeEvent event) {
        synchronized (listenerList) {
            for (IUMLResourceChangeListener listener : listenerList) {
                listener.preProjectDelete(event);
            }
        }
    }
}
