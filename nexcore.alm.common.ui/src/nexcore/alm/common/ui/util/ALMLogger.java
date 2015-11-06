/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.alm.common.ui.util;

import nexcore.alm.common.ui.Activator;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.alm.common.ui</li>
 * <li>서브 업무명 : nexcore.alm.common.ui.util</li>
 * <li>설 명 : Logger</li>
 * <li>작성일 : 2011. 11. 18.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public class ALMLogger {

    public static ALMLogger logger;

    public String pluginId = null;

    private ALMLogger() {
    }

    private ALMLogger(String pluginId) {
        this.pluginId = pluginId;
    }

    public static ALMLogger getLog(String pluginId) {
        if (logger == null) {
            logger = new ALMLogger(pluginId);
        } else {
            logger.pluginId = pluginId;
        }
        return logger;
    }

    /**
     * 
     * 
     * 
     * @param message
     * @param e
     *            void
     */
    public void info(String message) {
        Activator.getDefault().getLog().log(new Status(IStatus.INFO, pluginId, message, null));
    }

    /**
     * 
     * 
     * 
     * @param message
     * @param e
     *            void
     */
    public void warn(String message, Throwable e) {
        Activator.getDefault().getLog().log(new Status(IStatus.WARNING, pluginId, message, e));
    }

    /**
     * 
     * 
     * 
     * @param message
     * @param e
     *            void
     */
    public void error(String message, Throwable e) {
//        if (e instanceof ResourceException) {
//            ResourceException re = (ResourceException) e;
//            int status = re.getResponse().getStatus();
//
//            message = String.format("Error Code : %d - ", status);
//            switch (status) {
//                case ResourceException.NOT_FOUND:
//                    message += "NOT_FOUND";
//                    break;
//                case ResourceException.UNAUTHORIZE:
//                    message += "UNAUTHORIZE";
//                    break;
//                case ResourceException.BAD_REQUEST:
//                    message += "BAD_REQUEST";
//                    break;
//                case ResourceException.INTERNAL_SERVER_ERROR:
//                    message += "INTERNAL_SERVER_ERROR";
//                    break;
//                case ResourceException.SERVICE_UNAVAILABLE:
//                    message += "SERVICE_UNAVAILABLE";
//                    break;
//            }
//        }
        Activator.getDefault().getLog().log(new Status(IStatus.ERROR, pluginId, message, e));
    }

    /**
     * 
     * 
     * 
     * @param message
     * @param e
     *            void
     */
    public void cancel(String message, Throwable e) {
        Activator.getDefault().getLog().log(new Status(IStatus.CANCEL, pluginId, message, e));
    }

    /**
     * 
     * 
     * 
     * @param message
     * @param e
     *            void
     */
    public void ok(String message) {
        Activator.getDefault().getLog().log(new Status(IStatus.OK, pluginId, message, null));
    }
}
