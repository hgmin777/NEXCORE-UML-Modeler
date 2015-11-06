/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.alm.common.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;


/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.core</li>
 * <li>서브 업무명 : nexcore.tool.core.message</li>
 * <li>설 명 : Messages</li>
 * <li>작성일 : 2007. 04. 15</li>
 * <li>작성자 : 05690</li>
 * </ul>
 */
public class Messages {
    private static final String         BUNDLE_NAME     = "nexcore.tool.core.util.messages";

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
                                                                .getBundle(BUNDLE_NAME);

    /**
     * 프러퍼티 파일에 있는 키를 이용하여 메시지을 가져온다.
     * 
     * <pre>
     * [사용법]
     * Messages.getString(&quot;NEXCORE_MSG_SAVE&quot;);
     * messages.properties    : NEXCORE_MSG_SAVE = Data is successfully saved.
     * messages_kr.properties : NEXCORE_MSG_SAVE = 데이터가 성공적으로 저장되었습니다.
     * 영문 : Data is successfully saved.
     * 한글 : 성공적으로 저장되었습니다.
     * </pre>
     * 
     * @param key
     *            메시지 키
     * @return String 다국어 메시지
     */
    public static String getString(String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }
}
//import org.eclipse.osgi.util.NLS;
//
//public class Messages extends NLS {
//    private static final String BUNDLE_NAME = "nexcore.tool.core.util.messages"; //$NON-NLS-1$
//
//    public static String CryptoUtil_ALLOW_ONLY_ENGLISH_AND_NUMBER_FOR_PASSWORD;
//
//    public static String CryptoUtil_CANNOT_ENCODE_STRING;
//
//    public static String CryptoUtil_CRYPTO_ALGORITHM;
//
//    public static String CryptoUtil_CRYPTO_CIPHER;
//
//    public static String CryptoUtil_ENCODING_ERROR;
//
//    public static String CryptoUtil_FAIL_TO_DECODE_STRING;
//
//    public static String CryptoUtil_FAIL_TO_ENCODE_STRING;
//
//    public static String CryptoUtil_FAIL_TO_ENCODE_WITH_SECRET_KEY;
//
//    public static String CryptoUtil_FAIL_TO_INIT_CRYPTO_CLASS;
//
//    public static String CryptoUtil_FAIL_TO_INIT_DECODE_MODE;
//
//    public static String CryptoUtil_FAIL_TO_INIT_WITH_SECRET_KEY;
//
//    public static String CryptoUtil_FAILE_TO_DECODE_STRING;
//
//    public static String CryptoUtil_KEY_ERROR;
//
//    public static String CryptoUtil_KEY_FILE;
//
//    public static String CryptoUtil_NOT_SUPPORT_ENCODING;
//
//    public static String CryptoUtil_SECRET_KEY_ERROR;
//
//    public static String CryptoUtil_SECRET_KEY_FACTORY;
//
//    public static String FileUtil_FILE_LOCKING_ERROR;
//    static {
//        // initialize resource bundle
//        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
//    }
//
//    private Messages() {
//    }
//}
