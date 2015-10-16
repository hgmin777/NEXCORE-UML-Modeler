/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.project;

import nexcore.tool.uml.core.util.UMLLoginController;
import nexcore.tool.uml.core.util.UMLNexcoreLoginEvent;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.project.explorer.action.RefreshAction;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IExecutionListener;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.jface.action.IAction;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project</li>
 * <li>설 명 : ProjectExplorerPlugin</li>
 * <li>작성일 : 2009. 11. 26.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
public class ProjectExplorerPlugin extends AbstractUIPlugin {

    // The plug-in ID
    /**
     * PLUGIN_ID
     */
    public static final String PLUGIN_ID = "nexcore.tool.uml.ui.project"; //$NON-NLS-1$

    // The shared instance
    /**
     * plugin
     */
    private static ProjectExplorerPlugin plugin;

    /**
     * The constructor
     */
    public ProjectExplorerPlugin() {
    }

    /**
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
     */
    public void start(BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
//        ExcelDocumentBuilderFactory.newInstance();
        
        System.setProperty("sun.jnu.encoding", UICoreConstant.PROJECT_CONSTANTS__ENCODING_UTF8); //$NON-NLS-1$
        System.setProperty("file.encoding", UICoreConstant.PROJECT_CONSTANTS__ENCODING_UTF8); //$NON-NLS-1$
        
        Object adapter = PlatformUI.getWorkbench().getAdapter(ICommandService.class);

        if (adapter instanceof ICommandService) {
            ((ICommandService) adapter).addExecutionListener(new IExecutionListener() {
                public void postExecuteSuccess(String commandId, Object returnValue) {
                    if ("org.eclipse.ui.file.save".equals(commandId)) {
                        // isSaveEvent = true;
                    }
                }

                public void notHandled(String commandId, NotHandledException exception) {
                    // System.out.println("notHandled : " +commandId);
                }

                public void postExecuteFailure(String commandId, ExecutionException exception) {
                    // System.out.println("postExecuteFailure : " +commandId);
                }

                public void preExecute(String commandId, ExecutionEvent event) {
                    if ("org.eclipse.ui.file.refresh".equals(commandId)) {
                        if (UMLLoginController.getInstance().getLoginState() != UMLNexcoreLoginEvent.LOG_IN) {
                            return;
                        }
                        RefreshAction refreshAction = new RefreshAction() {
                            /**
                             * @see nexcore.tool.uml.ui.project.explorer.action.RefreshAction#run(org.eclipse.jface.action.IAction)
                             */
                            @Override
                            public void run(IAction action) {
                                execute();
                            }
                        };
                        try {
                            IWorkbenchPart activePart = PlatformUI.getWorkbench()
                                .getActiveWorkbenchWindow()
                                .getActivePage()
                                .getActivePart();
                            if (activePart instanceof CommonNavigator) {
                                refreshAction.setActivePart(null, activePart);
                                refreshAction.run(null);
                            }
                        } catch (Exception e) {
                            // ignore
                        }
                    } else if ("org.eclipse.ui.file.save".equals(commandId)) {}
                    // System.out.println("preExecute : "+commandId);
                }
            });
        }
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
    public static ProjectExplorerPlugin getDefault() {
        return plugin;
    }

    /**
     * getActivePage
     *  
     * @return IWorkbenchPage
     */
    public static IWorkbenchPage getActivePage() {

        IWorkbenchWindow window = getActiveWorkbench().getActiveWorkbenchWindow();
        if (window == null)
            return null;

        return window.getActivePage();

    }

    /**
     * getActiveWorkbench
     *  
     * @return IWorkbench
     */
    public static IWorkbench getActiveWorkbench() {
        return getDefault().getWorkbench();
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
     * getDisplay
     *  
     * @return Display
     */
    public static Display getDisplay() {
        Display display = Display.getCurrent();
        if (display == null)
            display = Display.getDefault();

        if (display == null)
            display = new Display();

        return display;
    }

}

