/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.preferences.part;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.preferences</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.preferences.part</li>
 * <li>설  명 : FontStyleType</li>
 * <li>작성일 : 2010. 1. 18.</li>
 * <li>작성자 : 변영민</li>
 * </ul>
 */
public enum FontStyleType {

    REGULAR, ITALIC, BOLD, BOLD_ITALIC;

    /**
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        switch (this) {
            case REGULAR:
                return "regular"; //$NON-NLS-1$
            case ITALIC:
                return "italic"; //$NON-NLS-1$
            case BOLD:
                return "bold"; //$NON-NLS-1$
            case BOLD_ITALIC:
                return "bold italic"; //$NON-NLS-1$
            default:
                return ""; //$NON-NLS-1$
        }
    }
}
