/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.core.exception;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.rm.core</li>
 * <li>서브 업무명 : nexcore.tool.rm.core.exception</li>
 * <li>설 명 : UMLRuntimeException</li>
 * <li>작성일 : Jun 4, 2009</li>
 * <li>작성자 : minhokim</li>
 * </ul>
 */
public class UMLRuntimeException extends RuntimeException {
    /**
     * auto generated serialVersionUID
     */
    private static final long serialVersionUID = -7694865755694714934L;

    /**
     * UMLRuntimeException
     * @param message
     */
    public UMLRuntimeException(String message) {
        super(message);
        this.printStackTrace();
    }

    /**
     * UMLRuntimeException
     * @param e
     */
    public UMLRuntimeException(Throwable e) {
        super(e);
        e.printStackTrace();
    }

}
