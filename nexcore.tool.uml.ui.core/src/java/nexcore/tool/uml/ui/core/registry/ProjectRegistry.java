/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.core.registry;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.manager.utility.DomainRegistry.ResourceStampRegistry;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.project.ClosedTreeNode;
import nexcore.tool.uml.ui.core.project.IResourceSetListener;
import nexcore.tool.uml.ui.core.project.ITreeNode;
import nexcore.tool.uml.ui.core.project.UMLFileTreeNode;
import nexcore.tool.uml.ui.core.project.UMLTreeNode;
import nexcore.tool.uml.ui.core.util.ProjectUtil;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.navigator.CommonViewer;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.registry</li>
 * <li>설 명 : ProjectRegistry</li>
 * <li>작성일 : 2010. 1. 4.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
public class ProjectRegistry {

    public static int WAIT_TIME = 0;
    
    public static class ViewerRegistry {

        /** Project Explorer for UML Modeler */
        private static CommonViewer viewer;

        public static CommonViewer getViewer() {
            if (viewer == null) {
                IViewPart part = null;
                if (UiCorePlugin.getDefault() != null && UiCorePlugin.getActivePage() != null) {
                    part = UiCorePlugin.getActivePage().findView(UICoreConstant.PROJECT_CONSTANTS__PROJECT_EXPLORER_ID);
                }
                if (part instanceof CommonNavigator) {
                    CommonNavigator navigator = (CommonNavigator) part;
                    viewer = navigator.getCommonViewer();
                }
                
                try {
                    TimerTask mouseMoveTask = new TimerTask() {
                        @Override
                        public void run() {
                            WAIT_TIME += 1000*10;
                            //System.out.println(WAIT_TIME);
                        }
                    };
                    
                    Timer timer = new Timer();
                    timer.schedule(mouseMoveTask, 1000 * 5, 1000 * 10);
                } catch (Exception e) {
                }
                
                Display.getDefault().asyncExec(new Runnable() {

                    @Override
                    public void run() {
                        viewer.getTree().addMouseMoveListener(new MouseMoveListener(){

                            @Override
                            public void mouseMove(MouseEvent e) {
                                WAIT_TIME = 0;
                            }
                        });
                    }

                });
            }
            return viewer;
        }

        /**
         * remove viewer from explorer registry.
         */
        public static void removeViewer() {
            viewer = null;
        }
    }

    public static class ContentProviderRegistry {

        /** UMLContentProvider */
        private static IResourceSetListener contentProvider = null;

        public static IResourceSetListener getContentProvider() {
            return contentProvider;
        }

        public static void setContentProvider(IResourceSetListener pContentProvider) {
            contentProvider = pContentProvider;
        }
    }

    public static class UMLTreeNodeRegistry {

        /**
         * UML tree node map.
         */
        private static Map<String, ITreeNode> map = new HashMap<String, ITreeNode>();

        public static synchronized ITreeNode getTreeNode(Object obj) {
            URI newId = null;
            if (obj instanceof EObject) {
                EObject eObject = (EObject) obj;
                newId = EcoreUtil.getURI(eObject);
                eObject = reload(newId, eObject);
                return map.get(newId.toString());
            } else if (obj instanceof URI) {
                return map.get(((URI) obj).toString());
            } else if (obj instanceof String) {
                return map.get((String) obj);
            }
            return null;
        }

        public static Map<String, ITreeNode> getTreeNodes() {
            return map;
        }

        /**
         * Remove all tree nodes.
         */
        public static synchronized void removeAllTreeNodes() {
            ResourceStampRegistry.removeAllResourceStamp();
            map.clear();
        }

        /**
         * @param obj
         *            EObject
         */
        public static synchronized void removeTreeNode(Object obj) {
            URI uri = null;
            String uuid = null;
            if (obj instanceof EObject) {
                EObject eObject = (EObject) obj;
                uri = EcoreUtil.getURI(eObject);
                eObject = reload(uri, eObject);
                if( eObject != null && eObject.eResource() != null ){
                    uuid = (eObject.eResource().getURIFragment(eObject));
                }
            } else if (obj instanceof URI) {
                uri = (URI) obj;
            } else if (obj instanceof String) {
                String uriPath = (String) obj;
                try {
                    uuid = URI.createURI(uriPath).fragment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            removeChildTreeNode(obj);
            if (uri != null) {
                map.remove(uri.toString());
            }
            
            if (obj instanceof String) {
                map.remove(obj);
            }

            if (uuid != null) {
                for (Iterator<?> iterator = map.keySet().iterator(); iterator.hasNext();) {
                    String uriStr = (String) iterator.next();
                    if (uriStr.endsWith(uuid)) {
                        iterator.remove();
                    }
                }
            }
        }

        private static EObject reload(URI uri, EObject eObject) {
            if (eObject != null && DomainUtil.isProxy(eObject)) {
                try {
                    if (new org.eclipse.core.runtime.Path(eObject.eResource().getURI().toString()).toFile().exists()) {
                        EObject newObject = DomainRegistry.getUMLDomain().getResourceSet().getEObject(uri, true);
                        if (newObject != null) {
                            return newObject;
                        }
                    }
                } catch (Exception e) {
                    // e.printStackTrace();
                }
            }
            return eObject;
        }

        public static synchronized void removeChildTreeNode(Object obj) {
            ITreeNode treeNode = getTreeNode(obj);
            if (treeNode == null)
                return;

            Object[] children = ProjectUtil.getChildEObjects(ProjectUtil.getAdapterFactoryContentProvider()
                .getChildren(treeNode.getEObject()), obj, false);

            for (Object child : children) {
                if (child instanceof UMLTreeNode) {
                    UMLTreeNode node = (UMLTreeNode) child;
                    removeTreeNode(node.getEObject());
                } else if (child instanceof UMLFileTreeNode) {
                    UMLFileTreeNode node = (UMLFileTreeNode) child;
                    removeTreeNode(node.getEObject());
                }
            }
        }

        public static synchronized void setTreeNode(Object obj, ITreeNode info) {
            if( info instanceof ClosedTreeNode) {
                return;
            }
            URI newId = null;
            if (obj instanceof EObject) {
                EObject eObject = (EObject) obj;
                newId = EcoreUtil.getURI(eObject);
                eObject = reload(newId, eObject);
                map.put(newId.toString(), info);
//                EObjectURIMap.setURI(id, newId);
            } else if (obj instanceof URI) {
                map.put(((URI)obj).toString(), info);
            } else if (obj instanceof String) {
                map.put((String)obj, info);
            }
        }
    }

    // public static class ModelFileRegistry {
    //
    // /** map to save model files.
    // * key = path(project name + folder name...), value = list of model files.
    // */
    // private static Map<String, List<IFile>> map = new HashMap<String,
    // List<IFile>>();
    //        
    // public static void setFile(String path, IFile file) {
    // if(!(map.containsKey(path))) {
    // List<IFile> list = new ArrayList<IFile>();
    // list.add(file);
    // map.put(path, list);
    // } else {
    // List<IFile> list = map.get(path);
    // if(!list.contains(file)) {
    // list.add(file);
    // map.put(path, list);
    // }
    // }
    // }
    //
    // public static List<IFile> getFileList(String path) {
    // return map.get(path);
    // }
    //        
    // /**
    // * @param prjName
    // */
    // public static void removeAllFiles() {
    // map.clear();
    // }
    //        
    // /**
    // * @param prjName
    // * @param file
    // */
    // public static void removeFile(String path, IFile file) {
    // List<IFile> list = map.get(path);
    // list.remove(file);
    // map.put(path, list);
    // }
    // }

//    public static class EObjectURIMap {
//        /**
//         * EObject URI Map
//         */
//        private static Map<Object, Object> map = new HashMap<Object, Object>();
//
//        public static synchronized Object getURI(Object id) {
//            return map.get(id);
//        }
//
//        /**
//         * Remove all
//         */
//        public static void removeAll() {
//            map.clear();
//        }
//
//        /**
//         * @param id
//         */
//        public static synchronized void removeURI(Object id) {
//            map.remove(id);
//        }
//
//        public static void setURI(Object id, Object info) {
//            map.put(id, info);
//        }
//    }

//    public static class CommonRegistry {
//
//        private static Map<String, Integer> map = new HashMap<String, Integer>();
//
//        public static synchronized int getValue(String id) {
//            if (!map.containsKey(id)) {
//                return -1;
//            }
//            return map.get(id);
//        }
//
//        /**
//         * Remove all
//         */
//        public static void removeAll() {
//            map.clear();
//        }
//
//        public static synchronized void setValue(String id, int value) {
//            map.put(id, value);
//        }
//    }

    // public static class FocusRegistry {
    //        
    // /** 트리에서 현재 선택되어 있는 EObject */
    // private static EObject focusedEObject;
    //        
    // public static EObject getFocused() {
    // return focusedEObject;
    // }
    //        
    // public static void setFocused(EObject eobject) {
    // focusedEObject = eobject;
    // }
    //        
    // public static void clear() {
    // focusedEObject = null;
    // }
    //        
    // }

    public static class ProjectResourceSetListenerRegistry {
        private static ResourceSetListenerImpl projectResourceSetListener = null;

        public static ResourceSetListenerImpl getResourceSetListener() {
            return projectResourceSetListener;
        }

        public static void setResourceSetListener(ResourceSetListenerImpl resourceSetListener) {
            projectResourceSetListener = resourceSetListener;
        }
    }
}
