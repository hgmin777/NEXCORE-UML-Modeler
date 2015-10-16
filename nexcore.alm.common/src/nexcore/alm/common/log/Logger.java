/*
 * Copyright (c) 2006-2009 SK C&C. All rights reserved.
 * 
 * This software is the confidential and proprietary information of SK C&C. You
 * shall not disclose such Confidential Information and shall use it only in
 * accordance wiht the terms of the license agreement you entered into with SK
 * C&C.
 */

package nexcore.alm.common.log;

import java.io.IOException;
import java.net.URL;

import nexcore.alm.common.Activator;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.alm.common</li>
 * <li>서브 업무명 : nexcore.alm.common.log</li>
 * <li>설 명 : Logger</li>
 * <li>작성일 : 2011. 5. 23.</li>
 * <li>작성자 : 06372</li>
 * </ul>
 */
public class Logger {

    private static final int LEVEL_FATAL = 1;

    private static final int LEVEL_ERROR = 2;

    private static final int LEVEL_WARN = 3;

    private static final int LEVEL_INFO = 4;

    private static final int LEVEL_DEBUG = 5;

    private static final int LEVEL_TRACE = 6;

    // private Log log;

    private org.apache.commons.logging.Log log;

    public Logger(Class clazz) {
        log = org.apache.commons.logging.LogFactory.getLog(clazz);
    }

    public void trace(String message) {
        logTrace(message, LEVEL_TRACE, null);
    }

    public void trace(Throwable throwable) {
        if (throwable != null)
            logTrace(throwable.getMessage(), LEVEL_TRACE, throwable);
        else
            logTrace("throwable is null", LEVEL_WARN, null);
    }

    public void trace(String message, Throwable throwable) {
        logTrace(message, LEVEL_TRACE, throwable);
    }

    public void debug(String message) {
        logTrace(message, LEVEL_DEBUG, null);
    }

    public void debug(Throwable throwable) {
        if (throwable != null)
            logTrace(throwable.getMessage(), LEVEL_DEBUG, throwable);
        else
            logTrace("throwable is null", LEVEL_WARN, null);
    }

    public void debug(String message, Throwable throwable) {
        logTrace(message, LEVEL_DEBUG, throwable);
    }

    public void info(String message) {
        logTrace(message, LEVEL_INFO, null);
    }

    public void info(Throwable throwable) {
        if (throwable != null)
            logTrace(throwable.getMessage(), LEVEL_INFO, throwable);
        else
            logTrace("throwable is null", LEVEL_WARN, null);
    }

    public void info(String message, Throwable throwable) {
        logTrace(message, LEVEL_INFO, throwable);
    }

    public void warn(String message) {
        logTrace(message, LEVEL_WARN, null);
    }

    public void warn(Throwable throwable) {
        if (throwable != null)
            logTrace(throwable.getMessage(), LEVEL_WARN, throwable);
        else
            logTrace("throwable is null", LEVEL_WARN, null);
    }

    public void warn(String message, Throwable throwable) {
        logTrace(message, LEVEL_WARN, throwable);
    }

    public void error(String message) {
        logTrace(message, LEVEL_ERROR, null);
    }

    public void error(Throwable throwable) {
        if (throwable != null)
            logTrace(throwable.getMessage(), LEVEL_ERROR, throwable);
        else
            logTrace("throwable is null", LEVEL_WARN, null);
    }

    public void error(String message, Throwable throwable) {
        logTrace(message, LEVEL_ERROR, throwable);
    }

    public void fatal(String message) {
        logTrace(message, LEVEL_FATAL, null);
    }

    public void fatal(Throwable throwable) {
        if (throwable != null)
            logTrace(throwable.getMessage(), LEVEL_FATAL, throwable);
        else
            logTrace("throwable is null", LEVEL_WARN, null);
    }

    public void fatal(String message, Throwable throwable) {
        logTrace(message, LEVEL_FATAL, throwable);
    }

    private void logTrace(String message, int level, Throwable throwable) {
        try {
            // if (message != null) {
            switch (level) {
                case LEVEL_FATAL:
                    if (throwable != null)
                        log.fatal(message, throwable);
                    else
                        log.fatal(message);
                    break;
                case LEVEL_ERROR:
                    if (throwable != null)
                        log.error(message, throwable);
                    else
                        log.error(message);
                    break;
                case LEVEL_WARN:
                    if (throwable != null)
                        log.warn(message, throwable);
                    else
                        log.warn(message);
                    break;
                case LEVEL_INFO:
                    if (throwable != null)
                        log.info(message, throwable);
                    else
                        log.info(message);
                    break;
                case LEVEL_DEBUG:
                    if (throwable != null)
                        log.debug(message, throwable);
                    else
                        log.debug(message);
                    break;
                case LEVEL_TRACE:
                default:
                    if (throwable != null)
                        log.trace(message, throwable);
                    else
                        log.trace(message);
                    break;
            }
            // }
        } catch (org.apache.commons.logging.LogConfigurationException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String findFile(String file) {
        String result;
        try {
            URL url = FileLocator.find(Activator.getBundle(), new Path(file), null);
            result = FileLocator.toFileURL(url).getPath().toString();
        } catch (IOException ex) {
            result = ""; //$NON-NLS-1$
            ex.printStackTrace();
        }
        return result;
    }
}
