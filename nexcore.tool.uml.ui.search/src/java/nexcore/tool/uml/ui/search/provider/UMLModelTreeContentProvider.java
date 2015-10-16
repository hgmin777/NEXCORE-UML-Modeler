/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.search.provider;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import nexcore.tool.uml.ui.search.match.UMLModelElement;
import nexcore.tool.uml.ui.search.ui.UMLModelSearchResult;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.search.ui.text.Match;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.search</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.search.provider</li>
 * <li>설  명 : UMLModelTreeContentProvider</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class UMLModelTreeContentProvider implements ITreeContentProvider, IUMLModelContentProvider {
    /**
     * EMPTY_ARR
     */
    private final Object[] EMPTY_ARR = new Object[0];

    /**
     * result
     */
    private UMLModelSearchResult result;

    /**
     * childrenMap
     */
    @SuppressWarnings("unchecked")
    private HashMap childrenMap;

    /**
     * viewer
     */
    private TreeViewer viewer;

    /**
     * UMLModelTreeContentProvider
     * @param viewer
     */
    public UMLModelTreeContentProvider(TreeViewer viewer) {
        this.viewer = viewer;
    }

    /**
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    public Object[] getChildren(Object parentElement) {
        Set children = (Set) childrenMap.get(parentElement);
        if (children == null)
            return EMPTY_ARR;
        return children.toArray();
    }

    /**
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
     */
    public Object getParent(Object element) {
        if (element instanceof UMLModelElement) {
            return ((UMLModelElement) element).getParent();
        }
        return null;
    }

    /**
     * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
     */
    public boolean hasChildren(Object element) {
        return getChildren(element).length > 0;
    }

    /**
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getElements(java.lang.Object)
     */
    public Object[] getElements(Object inputElement) {
        Object[] children = getChildren(inputElement);

        return children;
    }

    /**
     * @see org.eclipse.jface.viewers.IContentProvider#dispose()
     */
    public void dispose() {
        // TODO Auto-generated method stub

    }

    /**
     * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        if (newInput instanceof UMLModelSearchResult) {
            this.result = (UMLModelSearchResult) newInput;
            this.childrenMap = new HashMap();
            initialize();
        }
    }

    /**
     * initialize
     *   void
     */
    private void initialize() {

        Object[] elements = this.result.getElements();
        for (int i = 0; i < elements.length; i++) {
            Match match[] = result.getMatches(elements[i]);
            if (match.length > 0)
                insert(elements[i], false);
        }

    }

    /**
     * insert
     *  
     * @param child
     * @param refreshViewer void
     */
    private void insert(Object child, boolean refreshViewer) {
        Object parent = getParent(child);

        while (parent != null) {
            if (insertChild(parent, child)) {
                if (refreshViewer)
                    viewer.add(parent, child);
            } else {
                if (refreshViewer)
                    viewer.refresh(parent);
                return;
            }
            child = parent;
            parent = getParent(child);
        }

        if (insertChild(result, child)) {
            viewer.add(result, child);
        }
    }

    /**
     * insertChild
     *  
     * @param parent
     * @param child
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    private boolean insertChild(Object parent, Object child) {
        Set children = (Set) childrenMap.get(parent);
        if (children == null) {
            children = new HashSet();
            childrenMap.put(parent, children);
        }
        return children.add(child);
    }

    /**
     * @see nexcore.tool.uml.ui.search.provider.IUMLModelContentProvider#clear()
     */
    public void clear() {
        // TODO Auto-generated method stub

    }

    /**
     * @see nexcore.tool.uml.ui.search.provider.IUMLModelContentProvider#elementsChanged(java.lang.Object[])
     */
    public void elementsChanged(Object[] updatedElements) {
        for (int i = 0; i < updatedElements.length; i++) {
            Match match[] = result.getMatches(updatedElements[i]);
            if (match.length > 0)
                insert(updatedElements[i], false);

        }

    }

}
