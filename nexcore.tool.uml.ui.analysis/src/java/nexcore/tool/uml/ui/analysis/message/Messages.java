/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.analysis.message;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.analysis</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.analysis.message</li>
 * <li>설  명 : Messages</li>
 * <li>작성일 : 2009. 11. 24.</li>
 * <li>작성자 : 최영태 </li>
 * </ul>
 */
public class Messages {
    /**
     * BUNDLE_NAME
     */
    private static final String BUNDLE_NAME = "nexcore.tool.uml.ui.analysis.message.messages"; //$NON-NLS-1$

    /**
     * RESOURCE_BUNDLE
     */
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

    /**
     * Messages
     */
    private Messages() {
    }

    /**
     * getString
     *  
     * @param key
     * @return String
     */
    public static String getString(String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }
}
