/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.property.type;

import nexcore.tool.uml.ui.core.UICoreConstant;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.type</li>
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
                return UICoreConstant.UMLSECTION_CONSTANTS__MULTIPLICITIES[1];
            case ZERO_TO_UNIQUE:
                return UICoreConstant.UMLSECTION_CONSTANTS__MULTIPLICITIES[2];
            case UNIQUE:
                return UICoreConstant.UMLSECTION_CONSTANTS__MULTIPLICITIES[3];
            case UNIQUE_TO_SINGLE_STAR:
                return UICoreConstant.UMLSECTION_CONSTANTS__MULTIPLICITIES[4];
            case NONE:
                return UICoreConstant.UMLSECTION_CONSTANTS__MULTIPLICITIES[0];
            default:
                return UICoreConstant.EMPTY_STRING;
        }
    }
}
