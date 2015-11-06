/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.property.provider;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Type;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.provider</li>
 * <li>설  명 : ExceptionSectionLabelProvider</li>
 * <li>작성일 : 2010. 1. 5.</li>
 * <li>작성자 : 변영민</li>
 * </ul>
 */
public class ExceptionSectionLabelProvider extends ColumnLabelProvider {

    /** EMPTY_TEXT */
    private static final String EMPTY_TEXT = ""; //$NON-NLS-1$

    /** columnNumber */
    private int columnNumber;

    /** _IMAGE_COLUMN */
    private static final int _NAME_COLUMN = 0;

    /** _DIRECTION_COLUMN */
    private static final int _QUALIFIED_NAME_COLUMN = 1;

    /**
     * @param columnNumber
     */
    public ExceptionSectionLabelProvider(int columnNumber) {
        this.columnNumber = columnNumber;
    }

    /**
     * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
     */
    @Override
    public String getText(Object element) {
        if (element instanceof Type) {
            switch (columnNumber) {
                case _NAME_COLUMN:
                    return getName((Type) element);
                case _QUALIFIED_NAME_COLUMN:
                    return getQualifiedName((Type) element);
                default:
                    return EMPTY_TEXT;
            }
        }
        return null;
    }

    /**
     * 
     * 
     * @param element
     * @return String
     */
    private String getQualifiedName(NamedElement element) {
        return element.getQualifiedName();
    }

    /**
     * 이름을 리턴.
     * 
     * @param property
     * @return String
     */
    private String getName(NamedElement element) {
        if (element.getName() != null)
            return element.getName().toString();
        else
            return EMPTY_TEXT;
    }
}
