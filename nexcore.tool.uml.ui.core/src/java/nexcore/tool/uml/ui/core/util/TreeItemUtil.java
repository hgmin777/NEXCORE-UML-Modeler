/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.core.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import nexcore.tool.uml.ui.core.project.ITreeNode;
import nexcore.tool.uml.ui.core.project.UMLFileTreeNode;
import nexcore.tool.uml.ui.core.project.UMLTreeNode;
import nexcore.tool.uml.ui.core.registry.ProjectRegistry.UMLTreeNodeRegistry;
import nexcore.tool.uml.ui.core.registry.ProjectRegistry.ViewerRegistry;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.navigator.CommonViewer;

/**
 * 
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.util</li>
 * <li>설 명 : TreeItemUtil</li>
 * <li>작성일 : 2011. 4. 11.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public class TreeItemUtil {

    /**
     * 
     * 
     * 
     * @param item
     * @return Item
     */
    private static Item getParentItem(Item item) {
        return ((TreeItem) item).getParentItem();
    }

    /**
     * 
     * 
     * 
     * @param o
     * @return Item[]
     */
    private static Item[] getChildren(Widget o) {
        if (o instanceof TreeItem) {
            return ((TreeItem) o).getItems();
        }
        if (o instanceof Tree) {
            return ((Tree) o).getItems();
        }
        return null;
    }

    /**
     * 
     * @param item
     * @return TreePath
     */
    private static TreePath getTreePathFromItem(Item item) {
        LinkedList<Object> segments = new LinkedList<Object>();
        while (item != null) {
            Object segment = item.getData();
            if (segment == null) {
                return null;
            }
            Assert.isNotNull(segment);
            segments.addFirst(segment);
            item = getParentItem(item);
        }
        return new TreePath(segments.toArray());
    }

    /**
     * 
     * @param tree
     * @return TreePath[]
     */
    public static TreePath[] getUMLModelTreePaths(Tree tree) {
        ArrayList<Object> items = new ArrayList<Object>();
        internalCollectTreeItems(items, tree);
        ArrayList<Object> result = new ArrayList<Object>();
        for (Iterator<Object> it = items.iterator(); it.hasNext();) {
            Item item = (Item) it.next();
            TreePath treePath = getTreePathFromItem(item);
            if (treePath != null) {
                result.add(treePath);
            }
        }

        return (TreePath[]) result.toArray(new TreePath[items.size()]);
    }

    /**
     * 
     * @param tree
     * @return TreePath[]
     */
    public static TreePath[] getUMLModelExpandTreePaths(Tree tree) {
        ArrayList<Object> items = new ArrayList<Object>();
        internalExpandTreeItems(items, tree);
        ArrayList<Object> result = new ArrayList<Object>();
        for (Iterator<Object> it = items.iterator(); it.hasNext();) {
            Item item = (Item) it.next();
            TreePath treePath = getTreePathFromItem(item);
            if (treePath != null) {
                result.add(treePath);
            }
        }

        return (TreePath[]) result.toArray(new TreePath[items.size()]);
    }

    /**
     * getExpandTreePaths
     *  
     * @param tree
     * @return TreePath[]
     */
    public static TreePath[] getExpandTreePaths(Tree tree) {
        ArrayList<Object> items = new ArrayList<Object>();
        internalExpandTreeItems(items, tree);
        ArrayList<Object> result = new ArrayList<Object>();
        for (Iterator<Object> it = items.iterator(); it.hasNext();) {
            Item item = (Item) it.next();
            TreePath treePath = getTreePathFromItem(item);
            if (treePath != null) {
                result.add(treePath);
            }
        }

        return (TreePath[]) result.toArray(new TreePath[items.size()]);
    }

    /**
     * getExpandTreePaths
     *  
     * @param tree
     * @param selectedItem
     * @return TreePath[]
     */
    public static TreePath[] getExpandTreePaths(Tree tree, TreeItem selectedItem) {
        ArrayList<Object> items = new ArrayList<Object>();
        internalCollectTreeItems(items, tree);
        ArrayList<Object> result = new ArrayList<Object>();

        for (Iterator<Object> it = items.iterator(); it.hasNext();) {
            Item item = (Item) it.next();
            // if (item == selectedItem) {
            TreePath treePath = getTreePathFromItem(item);
            if (treePath != null) {
                result.add(treePath);
            }
            // }
        }

        return (TreePath[]) result.toArray(new TreePath[items.size()]);
    }

    /**
     * 
     * @param result
     * @param o
     *            void
     */
    private static void internalCollectTreeItems(List<Object> result, Widget o) {
        Item[] items = getChildren(o);
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];
            Object obj = item.getData();
            if (obj instanceof UMLTreeNode || obj instanceof UMLFileTreeNode || obj instanceof IFolder) {
                result.add(item);
            }
            internalCollectTreeItems(result, item);
        }
    }

    /**
     * internalExpandTreeItems
     *  
     * @param result
     * @param o void
     */
    private static void internalExpandTreeItems(List<Object> result, Widget o) {
        Item[] items = getChildren(o);
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];
            if (item instanceof TreeItem) {
                if (((TreeItem) item).getExpanded()) {
                    // Object obj = item.getData();
                    // if (obj instanceof ITreeNode || obj instanceof IFolder) {
                    // result.add(item);
                    // }
                    result.add(item);
                    internalExpandTreeItems(result, item);
                }
            }
        }
    }

    /**
     * 병합 후 트리 상태 유지를 위한 메소드
     * 
     * 
     * @param parentItem
     * @param list
     *            void
     */
    public static synchronized void expandTreeItem(TreeItem parentItem, List<Object> list) {
        CommonViewer viewer = ViewerRegistry.getViewer();

        TreeItem[] children = parentItem.getItems();
        for (TreeItem child : children) {
            Object obj = child.getData();
            if (list.contains(((ITreeNode) obj).getEObject())) {
                viewer.expandToLevel(obj, 1);
                expandTreeItem(child, list);
            }
        }
    }

    /**
     * 트리에서 expand된 상태의 node 의 EObject 를 list에 담는다.
     * 
     * 
     * @param list
     * @param parent
     *            void
     */
    public static void expanedItem(List<Object> list, TreeItem parent) {
        TreeItem[] children = parent.getItems();
        for (TreeItem item : children) {
            if (item.getExpanded()) {
                if (item.getData() != null) {
                    if (item.getData() instanceof ITreeNode) {
                        list.add(((ITreeNode) item.getData()).getEObject());
                    }
                    expanedItem(list, item);
                }
            }
        }
    }
    
    /**
     *  트리 상태를 이전 상태로 펼쳐주기위한 메소드
     * 
     *  
     * @param expanedTreePaths void
     */
    public static void expandTreePath(TreePath[] expanedTreePaths, ISelection selection){
        CommonViewer commonViewer = ViewerRegistry.getViewer();
        if (commonViewer.getControl().isDisposed() || expanedTreePaths == null) {
            return;
        }
        
        
        for (TreePath treePath : expanedTreePaths) {
            if (treePath.getLastSegment() instanceof ITreeNode) {
                if (((ITreeNode) treePath.getLastSegment()).getParentNode() instanceof ITreeNode) {
                    ITreeNode clonedParentNode = (ITreeNode) ((ITreeNode) treePath.getLastSegment()).getParentNode();
                    ITreeNode parentNode = (ITreeNode) UMLTreeNodeRegistry.getTreeNode(clonedParentNode.getEObject());
                    
                    expandTreeNode(commonViewer, clonedParentNode);
                    expandTreeNode(commonViewer, parentNode);
                }
                
                ITreeNode clonedNode = (ITreeNode) ((ITreeNode) treePath.getLastSegment());
                ITreeNode node = (ITreeNode) UMLTreeNodeRegistry.getTreeNode(clonedNode.getEObject());
                if(node != null) {
                	expandTreeNode(commonViewer, node);
                }
            }
        }
        
        List<Object> selectedItem = new ArrayList<Object>();
        
        if (selection != null && selection instanceof IStructuredSelection) {
            for (Iterator<?> iterator = ((IStructuredSelection) selection).iterator(); iterator.hasNext();) {
                Object obj = (Object) iterator.next();
                if (obj instanceof ITreeNode) {
                    ITreeNode treeNode = (ITreeNode) UMLTreeNodeRegistry.getTreeNode(((ITreeNode) obj).getEObject());
                    if (treeNode != null) {
                        selectedItem.add(treeNode);
                    }
                } else {
                    selectedItem.add(obj);
                }
            }

            if (!selectedItem.isEmpty()) {
                IStructuredSelection ss = new StructuredSelection(selectedItem);
                commonViewer.setSelection(ss, false);
            }
        }
    }
    
    /**
     * expandTreeNode
     *  
     * @param commonViewer
     * @param node void
     */
    private static void expandTreeNode(CommonViewer commonViewer, ITreeNode node) {
        if (node != null && !commonViewer.getExpandedState(node)) {
            try {
				commonViewer.expandToLevel(node, 1);
			} catch (Exception e) {
			}
        }
    }
}
