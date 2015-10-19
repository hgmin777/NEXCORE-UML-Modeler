/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.project.editor.provider;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.uml2.uml.Package;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.editor.provider</li>
 * <li>설 명 : LibraryTableViewerLabelProvider</li>
 * <li>작성일 : 2010. 1. 14.</li>
 * <li>작성자 : 오은주</li>
 * </ul>
 */
public class LibraryTableViewerLabelProvider extends LabelProvider implements ITableLabelProvider {

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
        Package package_ = ((Package) element);

        switch (columnIndex) {
            case 0:
                return package_.getName();
            case 1:
                return package_.eResource().getURI().toString();
            default:
                return ""; //$NON-NLS-1$

        }
    }

}
