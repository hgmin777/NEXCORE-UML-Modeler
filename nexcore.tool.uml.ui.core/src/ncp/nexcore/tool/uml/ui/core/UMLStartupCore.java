/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.core.util.UMLLoginController;
import nexcore.tool.uml.core.util.UMLNexcoreLoginEvent;
import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.ui.core.registry.ProjectRegistry;
import nexcore.tool.uml.ui.core.registry.ResourceManager;
import nexcore.tool.uml.ui.core.util.PreferenceUtil;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PerspectiveAdapter;
import org.eclipse.ui.PlatformUI;
import org.osgi.framework.Bundle;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core</li>
 * <li>설 명 : UMLStartupCore</li>
 * <li>작성일 : 2011. 7. 19.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public class UMLStartupCore implements IStartup {

    /**
     * PLUGINS
     */
    private static final String[] PLUGINS = new String[] { "nexcore.tool.mdd.core", "nexcore.tool.uml.manager", //$NON-NLS-1$ //$NON-NLS-2$
        "nexcore.tool.uml.model", "nexcore.tool.uml.ui.analysis", "nexcore.tool.uml.ui.core", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        "nexcore.tool.uml.ui.preferences", "nexcore.tool.uml.ui.project", "nexcore.tool.uml.ui.realization", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        "nexcore.tool.uml.core", "nexcore.tool.uml.connector" }; //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * @see org.eclipse.ui.IStartup#earlyStartup()
     */
    public void earlyStartup() {
        final long start = System.currentTimeMillis();

        List<String> START_BUNDLES = new ArrayList<String>();

        Bundle[] bundles = ResourcesPlugin.getPlugin().getBundle().getBundleContext().getBundles();
        for (Bundle bundle : bundles) {
            if ("nexcore.tool.uml.connector".equals(bundle.getSymbolicName())) { //$NON-NLS-1$
                if (bundle.getState() != Bundle.ACTIVE) {
                    try {
                        bundle.start();
                        Thread.sleep(100);
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        }

        // 라이선스 및 에이전트 로그인 상태를 체크 한다.
        // 로그인 가능하면 UMLLoginController.getInstance().getLoginState() 값이 UMLNexcoreLoginEvent.LOG_IN 이고
        // 로그인 불가하면 상태값은 UMLNexcoreLoginEvent.LOG_OUT
        LoginChecker.getInstance().doCheck();

        if (UMLLoginController.getInstance().getLoginState() == UMLNexcoreLoginEvent.LOG_IN) {
            ResourceManager.getInstance().openResource();
        }

        IWorkbenchWindow[] workbenchWindows = PlatformUI.getWorkbench().getWorkbenchWindows();

        for (IWorkbenchWindow window : workbenchWindows) {
            if (window != null) {
                window.addPerspectiveListener(perspectiveAdapter);
                break;
            }
        }
        
//        if (workbenchWindows.length > 0) {
//            if (workbenchWindows[0] instanceof WorkbenchWindow) {
//                statusLineManager = ((WorkbenchWindow) workbenchWindows[0]).getStatusLineManager();
//
//                statusItem = new StatusLineContributionItem("Login Status"); //$NON-NLS-1$
//                statusLineManager.appendToGroup(StatusLineManager.BEGIN_GROUP, statusItem); .close()
//            }
//        }
        
        
//        try {
//            TimerTask task = new TimerTask() {
//                @Override
//                public void run() {
//                    if (UMLLoginController.getInstance().getLoginState() != UMLNexcoreLoginEvent.LOG_IN) {
//                        Display.getDefault().asyncExec(new Runnable() {
//                            @Override
//                            public void run() {
//                                try {
//                                    getShell().setText(UMLMessage.UML_MODELER);
//                                    statusLineManager.setErrorMessage(UMLMessage.MESSAGE_LOGIN_ERROR);
//                                } catch (Exception e) {}
//                            }
//                        });
//                    } else {
//                        Display.getDefault().asyncExec(new Runnable() {
//                            @Override
//                            public void run() {
//                                try {
//                                    statusLineManager.setErrorMessage(UICoreConstant.EMPTY_STRING);
//  
//                                    if(!ALMPreference.useCollaboration()) {
//                                        return;
//                                    }
//                                    User user = UMLLoginController.getInstance().getUser();
//                                    if (user != null) {
//                                        String loginId = user.getLoginId();
//                                        String loginName = user.getName();
//                                        getShell().setText(String.format("%s - %s | %s",
//                                            UMLMessage.UML_MODELER,
//                                            loginName,
//                                            loginId));
//                                    } else {
//                                        getShell().setText(UMLMessage.UML_MODELER);
//                                    }
//
//                                } catch (Exception e) {}
//                            }
//                        });
//                    }
//                }
//            };
//            
//            Timer timer = new Timer();
//            timer.schedule(task, 1000, 1000);
//        } catch (Exception e) {
//        }
        

        try {
            TimerTask projectBackupTask = new TimerTask() {
                @Override
                public void run() {
                    final boolean toggle_state = PreferenceUtil.INSTANCE.getValueOfBooleanFieldEditor(ManagerConstant.PROJECT_BACKUP);
                    if(!toggle_state) {
                        return;
                    }
                    Display.getDefault().asyncExec(new Runnable() {

                        @Override
                        public void run() {
                            // 모델러를 사용하지 않은 시간이 10분이 넘으면 저장 안함.
                            if (ProjectRegistry.WAIT_TIME < 1000 * 60 * 10) {
                                IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
                                ProjectArchiveUtil bak = new ProjectArchiveUtil(root.getProjects());
                                bak.run();
                            }
                        }

                    });
                }
            };
            
            Timer timer = new Timer();
            // 1분 뒤, 10분마다 백업
            timer.schedule(projectBackupTask, 1000 * 60 * 1, 1000 * 60 * 10);
        } catch (Exception e) {
        }
    }
    
    
    
//    /**
//     * statusLineManager
//     */
//    private StatusLineManager statusLineManager = null;
//
//    /**
//     * statusItem
//     */
//    private StatusLineContributionItem statusItem = null;

    // 로그인 메세지 창이 중복 실행 되지 않기 위한 flag
    /**
     * alertFlag
     */
    boolean alertFlag = true;

    /**
     * getShell
     *  
     * @return Shell
     */
    private Shell getShell() {
        Shell shell = null;
        IWorkbenchWindow[] workbenchWindows = PlatformUI.getWorkbench().getWorkbenchWindows();

        for (IWorkbenchWindow workbenchWindow : workbenchWindows) {
            shell = workbenchWindow.getShell();
            if (shell != null) {
                break;
            }
        }

        return shell;
    }

    /**
     * perspectiveAdapter
     */
    private PerspectiveAdapter perspectiveAdapter = new PerspectiveAdapter() {
        boolean flag = false;

        @Override
        public void perspectiveActivated(IWorkbenchPage page, IPerspectiveDescriptor perspective) {
            super.perspectiveActivated(page, perspective);
            if (UICoreConstant.PROJECT_CONSTANTS__UmlPerspectiveID.equals(perspective.getId())) {
                if (UMLLoginController.getInstance().getLoginState() != UMLNexcoreLoginEvent.LOG_IN) {
                    MessageDialog.openWarning(getShell(),
                        UMLMessage.LABEL_NEXCORE_UML_MODELER,
                        UMLMessage.MESSAGE_LOGIN_ERROR);
                }
            }

            flag = true;
        }
    };
}
