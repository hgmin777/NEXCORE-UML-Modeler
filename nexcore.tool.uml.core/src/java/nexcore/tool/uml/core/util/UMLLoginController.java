/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.core.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.core.util</li>
 * <li>설 명 : UMLLoginController</li>
 * <li>작성일 : 2011. 6. 27.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public class UMLLoginController {

    /**
     * instance
     */
    public static UMLLoginController instance;

    /**
     * listenerList
     */
    private static List<UMLNexcoreLoginListener> listenerList = Collections.synchronizedList(new ArrayList<UMLNexcoreLoginListener>());

    /**
     * listener
     */
    private UMLNexcoreLoginListener listener = null;

//    /**
//     * user
//     */
//    private User user = null;

    /**
     * loginState
     */
    public int loginState = UMLNexcoreLoginEvent.LOG_OUT;

    /**
     * setLoginState
     *  
     * @param state void
     */
    public void setLoginState(int state) {
        this.loginState = state;
    }

    /**
     * getLoginState
     *  
     * @return int
     */
    public int getLoginState() {
        return loginState;
    }
    
    /**
     * isLogin
     *  
     * @return boolean
     */
    public boolean isLogin() {
        return getLoginState() == UMLNexcoreLoginEvent.LOG_IN;
    }

    /**
     * UMLLoginController
     */
    private UMLLoginController() {
//        LoginManager loginManager = LoginManager.getInstance();
//        loginManager.addLoginStatusListener(new ILoginStatusListener() {
//
//            @Override
//            public void loginStatusModified(LoginEvent event) {
//
//                int type = event.isLogin() ? UMLNexcoreLoginEvent.LOG_IN : UMLNexcoreLoginEvent.LOG_OUT;
//                setLoginState(type);
//
//                if (!event.isLogin()) {
//                    setUser(null);
//                }
//            }
//
//        });
        

        if (listener == null) {
            listener = new UMLNexcoreLoginListener() {

                public void changed(UMLNexcoreLoginEvent event) {
//                    if (ALMPreference.useCollaboration()) {
//                        if (!ALMCommon.isAliveAgent()) {
//                            return;
//                        }
//
//                        if (event.getType() == UMLNexcoreLoginEvent.LOG_IN) {
//                            NCPSession.getInstance().refreshSession();
//
//                            if (ServiceMonitor.isAliveAuthenticationService()) {
//                                setLoginState(UMLNexcoreLoginEvent.LOG_IN);
//                                // user reset
//                                setUser(null);
//                            }
//                        } else {
//                            setUser(null);
//                            UMLCacheManager.getInstance().clearAll();
//                            ServiceMonitor.stop();
//                            setLoginState(UMLNexcoreLoginEvent.LOG_OUT);
//                            
//                            // ServiceTracker close 처리
//                            UMLConnectorActivator.getDefault().getServiceManager().resetService();
//                        }
//                    } else {
//                        if (event.getType() == UMLNexcoreLoginEvent.LOG_IN) {
//                        } else {
//                            setUser(null);
//                            UMLCacheManager.getInstance().clearAll();
//                            setLoginState(UMLNexcoreLoginEvent.LOG_OUT);
//                        }
//                    }
                    setLoginState(UMLNexcoreLoginEvent.LOG_IN);
                    
//                    setUser(null); // user reset
                }

            };
            UMLLoginController.addLoginListener(listener);
        }
        
        UMLLoginController.fireEvent(new UMLNexcoreLoginEvent(UMLNexcoreLoginEvent.LOG_IN));
    }

    /**
     * getInstance
     *  
     * @return UMLLoginController
     */
    public static UMLLoginController getInstance() {
        if (instance == null) {
            instance = new UMLLoginController();
        }
        return instance;
    }

    /**
     * sessionKey 가 유효한지 체크
     * 
     * @param sessionKey
     * @return
     */
//    public boolean isValidSession(String sessionKey) {
//        String ignoreMode = System.getProperty(LauncherArgumentConstants.SYSTEM_PROP_ALL_ACCESS_MODE);
//
//        if (ignoreMode != null && "true".equals(ignoreMode)) {
//            return true;
//        }
//        
//        if (sessionKey == null || sessionKey.length() == 0) {
//            return false;
//        }
//        Session session;
//        try {
//            session = getSession(sessionKey);
//        } catch (Exception e) {
//            return false;
//        }
//        return session == null || session.getUserURI() == null ? false : true;
//    }

    /**
     * 
     * @param sessionKey
     * @return
     */
//    public Session getSession(String sessionKey) throws ConnectException, Exception {
//        IAuthenticationService authService = ServiceLocator.getInstance().getAuthService();
//
//        List<Session> ss = null;
//        try {
//            ss = RetrieveDelegator.retrieveResource(authService, sessionKey, null, null);
//        } catch (ConnectException e) {
//            ALMLogger.getLog("nexcore.tool.uml.core").error(e.getMessage(), e);
//            throw e;
//        } catch (Exception e) {
//            ALMLogger.getLog("nexcore.tool.uml.core").error(e.getMessage(), e);
//            throw e;
//        }
//
//        if (ss != null && ss.size() > 0) {
//            return ss.get(0);
//        }
//        
//        Exception exception = new Exception("There is no session information");
//        ALMLogger.getLog("nexcore.tool.uml.core").error(exception.getMessage(), exception);
//        throw exception;
//    }

//    /**
//     * setUser
//     *  
//     * @param user void
//     */
//    public void setUser(User user) {
//        this.user = user;
//    }
    
    /**
     * getUserId
     *  
     * @return String
     */
    public String getUserId() {
//        User user = getUser();
//        if (user != null) {
//            return user.getLoginId();
//        }
        return null;
    }

    /**
     * 
     * @return
     */
//    public User getUser() {
//        if( !ALMPreference.useCollaboration() ) {
//            return null;
//        }
//        
//        if (user == null) {
//            String sessionKey = NCPSession.getInstance().getSessionKey();
//            if(!ServiceMonitor.isAliveAuthenticationService()) {
//                return null;
//            }
//            if (sessionKey != null && sessionKey.length() > 0) {
//                IUserService userService = null;
//                try {
//                    userService = ServiceLocator.getInstance().getUserService();
//                } catch (Exception e1) {
//                    ALMLogger.getLog("nexcore.tool.uml.core").error(e1.getMessage(), e1);
//                }
//
//                if( userService == null) {
//                    return null;
//                }
//                Session session = null;
//                try {
//                    session = getSession(sessionKey);
//                } catch (Exception e) {
//                    return null;
//                }
//                if (session != null) {
//                    try {
//                        List<User> list = RetrieveDelegator.retrieveResource(userService, sessionKey,
//                            ResourceURIParser.getResourceLocationFromURI(session.getUserURI()),
//                            null,
//                            null,
//                            null);
//                        if (list != null && list.size() > 0) {
//                            return user = ((User) list.get(0));
//                        } else {
//                            System.err.println("user is empty.");
//                        }
//                    } catch (Throwable e) {
//                        ALMLogger.getLog("nexcore.tool.uml.core").error(e.getMessage(), e);
//                    }
//                }
//            }
//        }
//
//        return user;
//    }
    
    /**
     * getWorkAreaId
     *  
     * @return
     * @throws Exception long
     */
//    public long getWorkAreaId() throws Exception {
//        return NCPSession.getInstance().getLoginWorkAreaId();
//    }
    
    /**
     * isValidLogin
     *  
     * @return boolean
     */
//    public boolean isValidLogin() {
//        if( ALMPreference.useCollaboration() ) {
//            Session session = null;
//            try {
//                
//                if(!ALMCommon.isAliveAgent()){
//                    return false;
//                }
//                
//                NCPSession.getInstance().refreshSession();
//                String loginIp = NCPSession.getInstance().getLoginIp();
//                String sessionKey = NCPSession.getInstance().getSessionKey();
//                String userId = null;
//                User user = UMLLoginController.getInstance().getUser();
//                if( user != null) {
//                    userId = user.getLoginId();
//                } 
//                session = getSession(sessionKey);
//                if (session != null) {
//                    if( !StringUtil.isEmpty(userId) && !StringUtil.isEmpty(loginIp) && !StringUtil.isEmpty(sessionKey)) {
//                        return true;
//                    }
//                }
//            } catch (Exception e) {
//                
//            }
//            
//            return false;
//        } else {
//            return true;
//        }
//    }

    /**
     * 로그인/아웃을 알 수 있는 리스너 등록
     * 
     * @param listener
     *            void
     */
    public static synchronized void addLoginListener(UMLNexcoreLoginListener listener) {
        synchronized (listenerList) {
            listenerList.add(listener);
        }
    }

    /**
     * 로그인/아웃을 알 수 있는 리스너 해제
     * 
     * @param listener
     *            void
     */
    public static synchronized void removeLoginListener(UMLNexcoreLoginListener listener) {
        synchronized (listenerList) {
            listenerList.remove(listener);
        }
    }

    /**
     * fireEvent
     *  
     * @param event void
     */
    public static synchronized void fireEvent(UMLNexcoreLoginEvent event) {
        synchronized (listenerList) {
            for (UMLNexcoreLoginListener listener : listenerList) {
                listener.changed(event);
            }
        }
    }
}
