/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.search.provider;

import nexcore.tool.uml.ui.search.ui.UMLInverseReferenceModelSearchResult;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * 
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.search</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.search.provider</li>
 * <li>설 명 : UMLInverseReferenceModelTableContentProvider</li>
 * <li>작성일 : 2012. 8. 24.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public class UMLInverseReferenceModelTableContentProvider implements IStructuredContentProvider,
IUMLInverseReferenceModelContentProvider {
    /**
     * EMPTY_ARR
     */
    private final Object[] EMPTY_ARR = new Object[0];

    /**
     * result
     */
    @SuppressWarnings("unused")
    private UMLInverseReferenceModelSearchResult result;

    /**
     * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
     */
    public Object[] getElements(Object inputElement) {
        return EMPTY_ARR;
    }

    /**
     * @see org.eclipse.jface.viewers.IContentProvider#dispose()
     */
    public void dispose() {

    }

    /**
     * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
     *      java.lang.Object, java.lang.Object)
     */
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        if (newInput instanceof UMLInverseReferenceModelSearchResult) {
            this.result = (UMLInverseReferenceModelSearchResult) newInput;
        }
    }

    /**
     * @see nexcore.tool.uml.ui.search.provider.IUMLInverseReferenceModelContentProvider#clear()
     */
    public void clear() {

    }

    /**
     * @see nexcore.tool.uml.ui.search.provider.IUMLInverseReferenceModelContentProvider#elementsChanged(java.lang.Object[])
     */
    public void elementsChanged(Object[] updatedElements) {

    }

}
