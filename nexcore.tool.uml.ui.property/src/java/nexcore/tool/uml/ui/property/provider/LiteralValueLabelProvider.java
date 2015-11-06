/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.property.provider;

import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.uml2.uml.EnumerationLiteral;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.provider</li>
 * <li>설  명 : LiteralValueLabelProvider</li>
 * <li>작성일 : 2010. 1. 5.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class LiteralValueLabelProvider extends LabelProvider implements ITableLabelProvider {

    /**
     * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object,
     *      int)
     */
    public Image getColumnImage(Object element, int columnIndex) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object,
     *      int)
     */
    public String getColumnText(Object element, int columnIndex) {
        String result = ""; //$NON-NLS-1$

        if (element != null) {
            if (element instanceof EnumerationLiteral) {
                switch (columnIndex) {
                    case 0:
                        break;
                    case 1:
                        result = ((EnumerationLiteral) element).getName();
                        break;
                    default:
                        break;
                }
            } else if (element instanceof EEnumLiteral) {
                switch (columnIndex) {
                    case 0:
                        break;
                    case 1:
                        result = ((EEnumLiteral) element).getName();
                        break;
                    default:
                        break;
                }
            }
        }

        return result;
    }

}
