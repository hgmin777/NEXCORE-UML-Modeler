/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.property.provider;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.uml2.uml.Stereotype;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.provider</li>
 * <li>설  명 : AppliedStereotypeColumnLabelProvider</li>
 * <li>작성일 : 2009. 12. 18.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class AppliedStereotypeColumnLabelProvider extends ColumnLabelProvider {

    /** 컬럼 인덱스 */
    private int columnIdx;

    /**
     * 생성자
     * 
     * @param columnIdx
     */
    public AppliedStereotypeColumnLabelProvider(int columnIdx) {
        this.columnIdx = columnIdx;
    }

    /**
     * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
     */
    @Override
    public String getText(Object element) {
        String result = ""; //$NON-NLS-1$

        if (element != null) {
            Stereotype stereotype = (Stereotype) element;

            switch (columnIdx) {
                case 0:
                    result = stereotype.getName();
                    break;
                case 1:
                    result = stereotype.getProfile().getName();
                    break;
                case 2:
                    result = String.valueOf(stereotype.isStereotypeRequired(stereotype));
                    break;
                default:
                    break;
            }
        }

        return result;
    }

}
