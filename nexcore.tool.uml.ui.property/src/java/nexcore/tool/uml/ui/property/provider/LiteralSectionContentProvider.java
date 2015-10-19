/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.property.provider;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.provider</li>
 * <li>설  명 : LiteralSectionContentProvider</li>
 * <li>작성일 : 2011. 5. 27.</li>
 * <li>작성자 : Kang</li>
 * </ul>
 */
public class LiteralSectionContentProvider implements IStructuredContentProvider {

    /**
     * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
     */
    public Object[] getElements(Object inputElement) {
        List<EnumerationLiteral> literalList;
        if (inputElement instanceof Enumeration) {
            literalList = ((Enumeration) inputElement).getOwnedLiterals();
            List<Object> objectList = new ArrayList<Object>();
            for (int i = 0; i < literalList.size(); i++) {
                objectList.add(literalList.get(i));
            }
            return objectList.toArray();
        }
        return null;
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
    }
}
