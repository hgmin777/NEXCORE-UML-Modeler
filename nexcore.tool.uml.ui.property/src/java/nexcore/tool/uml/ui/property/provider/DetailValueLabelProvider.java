/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.property.provider;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.provider</li>
 * <li>설  명 : DetailValueLabelProvider</li>
 * <li>작성일 : 2009. 12. 30.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class DetailValueLabelProvider extends LabelProvider implements ITableLabelProvider {

    /** 부모 객체 */
    private DynamicEObjectImpl parentObject;

    /**
     * 생성자
     * 
     * @param parentObject
     */
    public DetailValueLabelProvider(DynamicEObjectImpl parentObject) {
        this.parentObject = parentObject;
    }

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
        String result = ""; //$NON-NLS-1$

        if (element != null) {
            switch (columnIndex) {
                case 0:
                    result = ((EStructuralFeature) element).getName();
                    break;
                case 1:
                    try {
                        result = parentObject.eGet(((EStructuralFeature) element)).toString();
                    } catch (NullPointerException npe) {
                        result = ((EStructuralFeature) element).getDefaultValue() != null
                        ? ((EStructuralFeature) element).getDefaultValue().toString() : ""; //$NON-NLS-1$
                    }
                    break;
                default:
                    break;
            }
        }

        return result;
    }

}
