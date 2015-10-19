/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.alm.common.ui.internal.action;

import java.util.ArrayList;
import java.util.List;

import nexcore.alm.common.ui.action.ISessionListener;

/**
 * 
 * <ul>
 * <li>업무 그룹명 : nexcore.alm.common.ui</li>
 * <li>서브 업무명 : nexcore.alm.common.ui.action</li>
 * <li>설 명 : SessionRefreshManager</li>
 * <li>작성일 : 2012. 4. 3.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public class RefreshSessionManager {

    private static RefreshSessionManager instance;

    public static RefreshSessionManager getInstance() {
        if (instance == null) {
            instance = new RefreshSessionManager();
        }

        return instance;
    }

    private List<ISessionListener> listeners = new ArrayList<ISessionListener>();

    public void addRefreshListener(ISessionListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    public void removeRefreshListener(ISessionListener listener) {
        listeners.remove(listener);
    }

    public void fireExecute() {
        for (ISessionListener listener : listeners) {
            listener.execute();
        }
    }
}
