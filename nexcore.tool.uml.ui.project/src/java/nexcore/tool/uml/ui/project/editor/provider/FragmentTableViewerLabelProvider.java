/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.project.editor.provider;

import nexcore.tool.uml.ui.core.UICoreConstant;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.uml2.uml.NamedElement;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.editor.provider</li>
 * <li>설  명 : FragmentTableViewerLabelProvider</li>
 * <li>작성일 : 2012. 1. 13.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public class FragmentTableViewerLabelProvider extends LabelProvider implements ITableLabelProvider {

    /**
     * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object,
     *      int)
     */
    public Image getColumnImage(Object element, int columnIndex) {
        return null;
    }

    /**
     * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object,
     *      int)
     */
    public String getColumnText(Object element, int columnIndex) {

        Resource umlResource = (Resource) element;
        EObject eObject = umlResource.getContents().get(0);

        switch (columnIndex) {
            case 0:
                if( eObject instanceof NamedElement ) {
                    return ((NamedElement)eObject).getQualifiedName();
                }
                return umlResource.toString();
            case 1:
                return umlResource.getURI().toFileString();
        }

        return UICoreConstant.EMPTY_STRING;
    }
}
