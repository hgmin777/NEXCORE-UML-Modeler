/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.project.util;


import nexcore.tool.uml.ui.project.ProjectExplorerPlugin;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
 * 
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.util</li>
 * <li>설  명 : Logger</li>
 * <li>작성일 : 2012. 2. 7.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public class Logger {

    /**
     * logger
     */
    public static Logger logger;

    /**
     * pluginId
     */
    private static String pluginId = ProjectExplorerPlugin.PLUGIN_ID;

    /**
     * Logger
     */
    private Logger() {
    }

    /**
     * getLog
     *  
     * @return Logger
     */
    public static Logger getLog() {
        if (logger == null) {
            logger = new Logger();
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
    public void info(String message, Throwable e) {
        ProjectExplorerPlugin.getDefault().getLog().log(new Status(IStatus.INFO, pluginId, message, e));
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
        ProjectExplorerPlugin.getDefault().getLog().log(new Status(IStatus.WARNING, pluginId, message, e));
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
        ProjectExplorerPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, pluginId, message, e));
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
        ProjectExplorerPlugin.getDefault().getLog().log(new Status(IStatus.CANCEL, pluginId, message, e));
    }

    /**
     * 
     * 
     * 
     * @param message
     * @param e
     *            void
     */
    public void ok(String message, Throwable e) {
        ProjectExplorerPlugin.getDefault().getLog().log(new Status(IStatus.OK, pluginId, message, e));
    }
}
