/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.alm.common.excel;

import nexcore.alm.common.exception.BaseException;

/**
 * Excel import/export 과정에서 발생할 수 있는 예외상황
 * 
 * @author indeday
 * 
 */
public class ExcelException extends BaseException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 5191191573910676820L;

    /**
     * @see BaseException#BaseException(String, String)
     */
    public ExcelException(String message, String logType) {
        super(message, logType);
    }

    /**
     * @see BaseException#BaseException(String, Throwable, String)
     */
    public ExcelException(Throwable cause, String message, String logType) {
        super(message, cause, logType);
    }

    /**
     * @see BaseException#BaseException(Throwable, String)
     */
    public ExcelException(Throwable cause, String message) {
        super(cause, message);
    }

    /**
     * @see BaseException#BaseException(Throwable, boolean)
     */
    public ExcelException(Throwable cause, boolean useLog) {
        super(cause, useLog);
    }
}
