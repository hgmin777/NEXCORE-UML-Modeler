/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.property.provider;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.provider</li>
 * <li>설  명 : MultiValueLabelProvider</li>
 * <li>작성일 :2009. 12. 28.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class MultiValueLabelProvider extends LabelProvider implements ITableLabelProvider {

    /** 색인 값 */
    private int index = 0;

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
            if (element instanceof String) {
                switch (columnIndex) {
                    case 0:
                        result = String.valueOf(index);
                        break;
                    case 1:
                        result = (String) element;
                        index++;
                        break;
                    default:
                        break;
                }
            } else if (element instanceof DynamicEObjectImpl) {
                switch (columnIndex) {
                    case 0:
                        result = String.valueOf(index);
                        break;
                    case 1:
                        result = ((DynamicEObjectImpl) element).eClass().getName();
                        index++;
                        break;
                    default:
                        break;
                }
            } else if (element instanceof EClass) {
                switch (columnIndex) {
                    case 0:
                        result = String.valueOf(index);
                        break;
                    case 1:
                        result = ((EClass) element).toString();
                        index++;
                        break;
                    default:
                        break;
                }
            }
        }

        return result;
    }

}
