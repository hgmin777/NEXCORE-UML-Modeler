/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.property;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.property</li>
 * <li>설  명 : MultiplicityType</li>
 * <li>작성일 : 2009. 12. 8.</li>
 * <li>작성자 : 변영민</li>
 * </ul>
 */
public enum MultiplicityType {

    /**
     * SINGLE_STAR
     */
    SINGLE_STAR, /**
     * ZERO_TO_UNIQUE
     */
    ZERO_TO_UNIQUE, /**
     * UNIQUE
     */
    UNIQUE, /**
     * UNIQUE_TO_SINGLE_STAR
     */
    UNIQUE_TO_SINGLE_STAR, /**
     * NONE
     */
    NONE;

    /**
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        switch (this) {
            case SINGLE_STAR:
                return "*"; //$NON-NLS-1$
            case ZERO_TO_UNIQUE:
                return "0..1"; //$NON-NLS-1$
            case UNIQUE:
                return "1"; //$NON-NLS-1$
            case UNIQUE_TO_SINGLE_STAR:
                return "1..*"; //$NON-NLS-1$
            case NONE:
                return "(none)"; //$NON-NLS-1$
            default:
                return ""; //$NON-NLS-1$
        }
    }
}
