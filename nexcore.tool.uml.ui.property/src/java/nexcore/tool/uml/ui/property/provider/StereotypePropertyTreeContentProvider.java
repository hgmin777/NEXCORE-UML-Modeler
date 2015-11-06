/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.property.provider;

import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Control;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.provider</li>
 * <li>설  명 : StereotypePropertyTreeContentProvider</li>
 * <li>작성일 : 2009. 12. 22.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class StereotypePropertyTreeContentProvider implements ITreeContentProvider, IPropertyChangeListener {

    /** 트리 뷰어 */
    private AbstractTreeViewer viewer;

    /** 트리 입력 */
    @SuppressWarnings("unused")
    private Element input;

    /**
     * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
     *      java.lang.Object, java.lang.Object)
     */
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        this.viewer = (AbstractTreeViewer) viewer;

        if (newInput != null) {
            this.input = (Element) newInput;
        }
    }

    /**
     * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
     */
    public Object[] getElements(Object inputElement) {
        if (inputElement instanceof Element) {
            return ((Element) inputElement).getAppliedStereotypes().toArray();
        }
        // } else if(inputElement instanceof Stereotype) {
        // return ((Stereotype)inputElement).getAllAttributes().toArray();
        // }

        return null;
    }

    /**
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
     */
    public Object[] getChildren(Object parentElement) {
        if (parentElement instanceof Stereotype) {
            return ((Stereotype) parentElement).getAllAttributes().toArray();
        }

        return null;
    }

    /**
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
     */
    public Object getParent(Object element) {
        if (element instanceof Stereotype) {
            return ((Stereotype) element).getOwner();
        } else if (element instanceof Property) {
            return ((Property) element).getOwner();
        }

        return null;
    }

    /**
     * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
     */
    public boolean hasChildren(Object element) {
        if (element instanceof Property) {
            return false;
        }

        return true;
    }

    /**
     * @see org.eclipse.jface.viewers.IContentProvider#dispose()
     */
    public void dispose() {
        viewer = null;
        input = null;
    }

    /**
     * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
     */
    public void propertyChange(final PropertyChangeEvent event) {
        // Make sure control exists
        // No sense telling a disposed widget to react
        Control ctrl = viewer.getControl();
        if (ctrl != null && !ctrl.isDisposed()) {
            // Use an asyncExec to run this code on UI thread
            ctrl.getDisplay().asyncExec(new Runnable() {
                /**
                 * @see java.lang.Runnable#run()
                 */
                public void run() {
                    String[] propChange = new String[] { event.getProperty() };
                    viewer.update(event.getNewValue(), propChange);
                }
            });
        }
    }

}
