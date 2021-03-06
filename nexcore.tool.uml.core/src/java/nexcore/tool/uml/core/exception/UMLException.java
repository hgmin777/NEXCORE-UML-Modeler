/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.core.exception;

import nexcore.tool.uml.core.log.Log;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.rm.core</li>
 * <li>서브 업무명 : nexcore.tool.rm.core.exception</li>
 * <li>설 명 : UMLException</li>
 * <li>작성일 : 2009. 06. 09</li>
 * <li>작성자 : minhokim</li>
 * </ul>
 */
public class UMLException extends Exception {

    /**
     * default serialVersionUID
     */
    private static final long serialVersionUID = -7007363780423547499L;

    /**
     * UMLException
     * @param e
     */
    public UMLException(Throwable e) {
        super(e);
        Log.debug(this);
    }

    /**
     * UMLException
     * @param message
     */
    public UMLException(String message) {
        super(message);
    }

}
