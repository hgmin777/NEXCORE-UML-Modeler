/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.core.log;

import java.net.URL;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.core_v1.0</li>
 * <li>서브 업무명 : nexcore.tool.uml.core.log</li>
 * <li>설 명 : Log</li>
 * <li>작성일 : 2010. 4. 6.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class Log {

    /** 로그레벨 에러 */
    public static final String ERROR = "ERROR"; //$NON-NLS-1$

    /** 로그레벨 경고 */
    public static final String WARN = "WARN"; //$NON-NLS-1$

    /** 로그레벨 알림 */
    public static final String INFO = "INFO"; //$NON-NLS-1$

    /** 로그레벨 디버그 */
    public static final String DEBUG = "DEBUG"; //$NON-NLS-1$

    static {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        URL url = classloader.getResource("log4j.properties");
        PropertyConfigurator.configure(url);
    }
    /**
     * 기본 알림 정보에 해당하는 로그를 반환하는 메소드
     * 
     * @return Logger
     */
    public static Logger getLog() {
        return Log.getLog(Log.INFO);
    }

    /**
     * 로그 레벨에 해당하는 로그를 반환하는 메소드
     * 
     * @param logLevel
     * @return Logger
     */
    public static Logger getLog(String logLevel) {
        return Logger.getLogger(logLevel);
    }

    /**
     * 기본 알림 정보를 로그 파일에 기록하는 메소드
     * 
     * @param log
     *            void
     */
    public static void info(Object log) {
        Logger.getLogger(Log.INFO).info(log);
    }

    /**
     * 디버그 정보를 로그파일에 기록하는 메소드
     * 
     * @param log
     *            void
     */
    public static void debug(Object log) {
        Logger.getLogger(Log.DEBUG).debug(log);
    }

    /**
     * 경고 정보를 로그파일에 기록하는 메소드
     * 
     * @param log
     *            void
     */
    public static void warn(Object log) {
        Logger.getLogger(Log.WARN).warn(log);
    }

    /**
     * 에러 정보를 로그 파일에 기록하는 메소드
     * 
     * @param log
     *            void
     */
    public static void error(Object log) {
        Logger.getLogger(Log.ERROR).error(log);
    }

    /**
     * 기본 알림 정보를 들여쓰기를 적용하여 로그파일에 기록하는 메소드
     * 
     * @param object
     * @param indent
     *            void
     */
    public static void logObject(Object object, String indent) {
        if (null != object) {
            info(indent + object.toString());
        } else {
            info(indent + "<null>"); //$NON-NLS-1$
        }
    }

}
