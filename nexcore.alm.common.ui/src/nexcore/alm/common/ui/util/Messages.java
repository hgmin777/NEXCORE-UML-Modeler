/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.alm.common.ui.util;

import org.eclipse.osgi.util.NLS;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.alm.common.ui</li>
 * <li>서브 업무명 : nexcore.alm.common.ui.util</li>
 * <li>설  명 : Messages</li>
 * <li>작성일 : 2011. 12. 7.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public class Messages extends NLS {
    private static final String BUNDLE_NAME = "nexcore.alm.common.ui.util.messages"; //$NON-NLS-1$


    static {
        // initialize resource bundle
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    private Messages() {
    }

    public static String PropertyCompositeUtil_CONTENTS_CREATE;
    public static String PropertyCompositeUtil_CONTENTS_DELETE;
    public static String PropertyCompositeUtil_CONTENTS_DESCRIPTION;
    public static String PropertyCompositeUtil_CONTENTS_DETAIL_INFORMATION;
    public static String PropertyCompositeUtil_CONTENTS_ID;
    public static String PropertyCompositeUtil_CONTENTS_NAME;

}
