/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.property.type;

import nexcore.tool.uml.core.message.UMLMessage;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.type</li>
 * <li>설  명 : ParameterDirectionType</li>
 * <li>작성일 : 2010. 1. 5.</li>
 * <li>작성자 : 변영민</li>
 * </ul>
 */
public enum ParameterDirectionType {

    /**
     * IN
     */
    IN, /**
     * INOUT
     */
    INOUT, /**
     * OUT
     */
    OUT, /**
     * RETURN
     */
    RETURN;

    /**
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        switch (this) {
            case IN:
                return UMLMessage.LABEL_IN; //$NON-NLS-1$
            case INOUT:
                return UMLMessage.LABEL_IN_OUT; //$NON-NLS-1$
            case OUT:
                return UMLMessage.LABEL_OUT; //$NON-NLS-1$
            case RETURN:
                return UMLMessage.LABEL_RETURN; //$NON-NLS-1$
            default:
                return ""; //$NON-NLS-1$
        }
    }
}
