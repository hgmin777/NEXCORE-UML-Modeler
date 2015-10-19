/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.realization;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization</li>
 * <li>설 명 : RealizationPlugin</li>
 * <li>작성일 : 2009. 11. 12.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class RealizationPlugin extends AbstractUIPlugin {

    /** PLUGIN_ID */
    public static final String PLUGIN_ID = "nexcore.tool.uml.ui.realization"; //$NON-NLS-1$

    /** plugin */
    private static RealizationPlugin plugin;

    /**
     * The constructor
     */
    public RealizationPlugin() {
    }

    /**
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
     */
    public void start(BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
    }

    /**
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
     */
    public void stop(BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
    }

    /**
     * Returns the shared instance
     * 
     * @return the shared instance
     */
    public static RealizationPlugin getDefault() {
        return plugin;
    }

    /**
     * getShell
     *  
     * @return Shell
     */
    public static Shell getShell() {
        return getActiveWorkbench().getActiveWorkbenchWindow().getShell();
    }

    /**
     * getActiveWorkbench
     *  
     * @return IWorkbench
     */
    public static IWorkbench getActiveWorkbench() {
        return getDefault().getWorkbench();
    }

}
