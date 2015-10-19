/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.alm.common.ui.constant;

import org.eclipse.osgi.util.NLS;

/**
 * 
 * <ul>
 * <li>업무 그룹명 : nexcore.alm.common.ui</li>
 * <li>서브 업무명 : nexcore.alm.common.ui.constant</li>
 * <li>설 명 : Messages</li>
 * <li>작성일 : 2012. 3. 30.</li>
 * <li>작성자 : 신용일</li>
 * </ul>
 */
public class ALMCommonMessages extends NLS {
    private static final String BUNDLE_NAME = "nexcore.alm.common.ui.constant.messages"; //$NON-NLS-1$

    /** 추적 메트릭스 리포트 */
    public static String LABEL_TRACKING_MATRIX_REPORT;

    
    
    static {
        // initialize resource bundle
        NLS.initializeMessages(BUNDLE_NAME, ALMCommonMessages.class);
    }

    private ALMCommonMessages() {
    }
}
