/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.core.util;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.core.util</li>
 * <li>설  명 : TypeSelectDialogType</li>
 * <li>작성일 : 2010. 1. 19.</li>
 * <li>작성자 : 변영민 </li>
 * </ul>
 */
public enum TypeSelectDialogType {

    /**
     * LIFELINE
     */
    LIFELINE, /**
     * RETURN_TYPE
     */
    RETURN_TYPE, /**
     * RAISED_EXCEPTION
     */
    RAISED_EXCEPTION, /**
     * PROPERTY
     */
    PROPERTY, /**
     * DEFAULT_VALUE
     */
    DEFAULT_VALUE;

    /**
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        switch (this) {
            case LIFELINE:
                return "";
            case RETURN_TYPE:
                return "";
            case RAISED_EXCEPTION:
                return "";
            case PROPERTY:
                return "";
            case DEFAULT_VALUE:
                return "";
            default:
                return ""; //$NON-NLS-1$
        }
    }
}
