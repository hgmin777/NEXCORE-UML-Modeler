/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.core;

import nexcore.tool.uml.core.util.UMLLoginController;
import nexcore.tool.uml.core.util.UMLNexcoreLoginEvent;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core</li>
 * <li>설  명 : LoginChecker</li>
 * <li>작성일 : 2012. 7. 20.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public class LoginChecker {

    /**
     * instance
     */
    private static LoginChecker instance;
    
    /**
     * LoginChecker
     */
    private LoginChecker(){
        //do nothing
    }
    
    /**
     * getInstance
     *  
     * @return LoginChecker
     */
    public static LoginChecker getInstance() {
        if( instance == null ) {
            instance = new LoginChecker();
        }
        
        return instance;
    }
    
    /**
     * 로그인 메세지 창이 중복 실행 되지 않기 위한 flag
     */
    boolean alertFlag = true;
    
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
    
    public void doCheck() {
        // Stand-Alone 모드
//        System.setProperty(DefinitionConstant.USE_COLLABORATION, "false"); //$NON-NLS-1$ //$NON-NLS-2$
        UMLLoginController.fireEvent(new UMLNexcoreLoginEvent(UMLNexcoreLoginEvent.LOG_IN));
        
//        alertFlag = true;
//        Runnable startUMLRunnable = new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    ProgressMonitorDialog dialog = new ProgressMonitorDialog(getShell());
//                    
//                    dialog.run(true, false, new IRunnableWithProgress() {
//                        
//                        @Override
//                        public void run(IProgressMonitor monitor) throws InvocationTargetException,
//                        InterruptedException {
//                            monitor.beginTask("", 4); //$NON-NLS-1$
//                            monitor.setTaskName("Login check..."); //$NON-NLS-1$
//                            monitor.worked(1);
//                            String sessionKey = NCPSession.getInstance().getSessionKey();
//                            
//                            monitor.setTaskName("Service initialize..."); //$NON-NLS-1$
//                            if (ServiceMonitor.isAliveAuthenticationService()) {
//                                monitor.setTaskName("Session check..."); //$NON-NLS-1$
//                                boolean validSession = UMLLoginController.getInstance().isValidSession(sessionKey);
//                                monitor.worked(1);
//                                if (validSession) {
//                                    monitor.setTaskName("Session OK"); //$NON-NLS-1$
//                                    monitor.worked(1);
//                                    if (UMLLoginController.getInstance().isValidLogin()) {
//                                        monitor.setTaskName("Login OK"); //$NON-NLS-1$
//                                        UMLLoginController.fireEvent(new UMLNexcoreLoginEvent(UMLNexcoreLoginEvent.LOG_IN));
//                                    }
//                                }
//                            }
//                            monitor.worked(1);
//                            monitor.done();
//                        }
//                    });
//                } catch (InvocationTargetException e) {
//                    ALMLogger.getLog(UiCorePlugin.PLUGIN_ID).error(e.getMessage(), e);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                    ALMLogger.getLog(UiCorePlugin.PLUGIN_ID).error(e.getMessage(), e);
//                }
//            }
//        };
        

        
//        if (ALMCommon.isAliveAgent()) {
//            // Stand-Alone , 협업 모두 가능
//            if (ALMPreference.useCollaboration()) {
//                // 협업기능 사용 모드
//                Display.getDefault().syncExec(startUMLRunnable);
//            } else {
//                // Stand-Alone 모드
//                try {
//                    UMLLoginController.fireEvent(new UMLNexcoreLoginEvent(UMLNexcoreLoginEvent.LOG_IN));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    ALMLogger.getLog(UiCorePlugin.PLUGIN_ID).error(e.getMessage(), e);
//                }
//            }
//        } else {
//            // 라이선스 인증된 경우 // Stand-Alone 모드 , 협업 사용 불가
//            IStatus status = ALMCommon.checkLicense(IUMLIdentifier.PRODUCTCODE_UML, DefinitionConstant.UML_VERSION);
//            if (status.isOK()) {
//                // Stand-Alone 모드
//                Display.getDefault().syncExec(new Runnable() {
//                    @Override
//                    public void run() {
//                        // 묻지않고 항상 실행
//                        boolean always_run = PreferenceUtil.INSTANCE.getValueOfBooleanFieldEditor(ManagerConstant.ALWAYS_RUN_STAND_ALONE);
//                        
//                        if (!always_run) {
//                            MessageDialogWithToggle m = MessageDialogWithToggle.openOkCancelConfirm(UiCorePlugin.getShell(),
//                                UMLMessage.LABEL_NEXCORE_UML_MODELER,
//                                Messages.UMLStartupCore_AGENT_ERROR_MESSAGE,
//                                UMLMessage.MESSAGE_ALWAYS_RUN_WITHOUT_PROMPT_STAND_ALONE,
//                                always_run,
//                                PreferenceUtil.INSTANCE.getPreferenceStore(),
//                                ManagerConstant.ALWAYS_RUN_STAND_ALONE);
//                            if (m.getReturnCode() == Window.OK) {
//                                System.setProperty(DefinitionConstant.USE_COLLABORATION, "false"); //$NON-NLS-1$ //$NON-NLS-2$
//                                UMLLoginController.fireEvent(new UMLNexcoreLoginEvent(UMLNexcoreLoginEvent.LOG_IN));
//                            }
//                            
//                            if (m.getToggleState()) {
//                                UiCorePlugin.getDefault()
//                                .getPreferenceStore()
//                                .setValue(ManagerConstant.ALWAYS_RUN_STAND_ALONE, m.getToggleState());
//                            }
//                        } else {
//                            // 묻지않고 항상 실행 이므로
//                            System.setProperty(DefinitionConstant.USE_COLLABORATION, "false"); //$NON-NLS-1$ //$NON-NLS-2$
//                            UMLLoginController.fireEvent(new UMLNexcoreLoginEvent(UMLNexcoreLoginEvent.LOG_IN));
//                        }
//                        
//                    }
//                });
//            } else {
//                alertFlag = false;
//                if (status.getException() instanceof FileNotFoundException) {
//                    ALMLogger.getLog(UiCorePlugin.PLUGIN_ID).error(status.getException().getMessage(),
//                        status.getException());
//                    Display.getDefault().syncExec(new Runnable() {
//                        @Override
//                        public void run() {// License is not registered.
//                            MessageDialog.openWarning(getShell(),
//                                UMLMessage.LABEL_NEXCORE_UML_MODELER,
//                                Messages.UMLStartupCore_LICENSE_ERROR_MESSAGE);
//                        }
//                    });
//                } else {
//                    final String metssage = status.getException().getMessage();
//                    ALMLogger.getLog(UiCorePlugin.PLUGIN_ID).error(metssage, status.getException());
//                    
//                    Display.getDefault().syncExec(new Runnable() {
//                        @Override
//                        public void run() {
//                            MessageDialog.openWarning(getShell(), UMLMessage.LABEL_NEXCORE_UML_MODELER, Messages.UMLStartupCore_LOGIN_ERROR_MESSAGE);
//                        }
//                    });
//                }
//            }
//        }
//        if (alertFlag) { // 위에서 로그인 메세지창으 실행 된 경우 여기서는 실행하지 않는다.
//            if (UMLLoginController.getInstance().getLoginState() != UMLNexcoreLoginEvent.LOG_IN) {
//                Display.getDefault().syncExec(new Runnable() {
//                    @Override
//                    public void run() {
//                        IPerspectiveDescriptor perspective = PlatformUI.getWorkbench()
//                            .getActiveWorkbenchWindow()
//                            .getActivePage()
//                            .getPerspective();
//
//                        if (UICoreConstant.PROJECT_CONSTANTS__UmlPerspectiveID.equals(perspective.getId())) {
//                            MessageDialog.openWarning(getShell(),
//                                UMLMessage.LABEL_NEXCORE_UML_MODELER,
//                                UMLMessage.MESSAGE_LOGIN_ERROR);
//                        }
//                    }
//                });
//            }
//        }
    }

}
