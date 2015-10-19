/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.alm.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.core.ui.propertieseditor</li>
 * <li>서브 업무명 : nexcore.tool.core.ui.propertieseditor.util</li>
 * <li>설 명 : EncodeChanger</li>
 * <li>작성일 : Aug 14, 2008</li>
 * <li>작성자 : Zhang Nan</li>
 * </ul>
 */
public class EncodeChanger {

    public static String unicode2UnicodeEsc(String uniStr) {
        StringBuffer ret = new StringBuffer();
        if (uniStr == null)
            return null;
        int maxLoop = uniStr.length();
        for (int i = 0; i < maxLoop; i++) {
            char character = uniStr.charAt(i);
            if (character <= '\177') {
                ret.append(character);
            } else {
                ret.append("\\u");
                String hexStr = Integer.toHexString(character);
                int zeroCount = 4 - hexStr.length();
                for (int j = 0; j < zeroCount; j++)
                    ret.append('0');

                ret.append(hexStr);
            }
        }

        return ret.toString();
    }

    public static String unicodeEsc2Unicode(String unicodeStr) {
        if (unicodeStr == null)
            return null;
        StringBuffer retBuf = new StringBuffer();
        int maxLoop = unicodeStr.length();
        for (int i = 0; i < maxLoop; i++)
            if (unicodeStr.charAt(i) == '\\') {
                if (i < maxLoop - 5 && (unicodeStr.charAt(i + 1) == 'u' || unicodeStr.charAt(i + 1) == 'U'))
                    try {
                        retBuf.append((char) Integer.parseInt(unicodeStr.substring(i + 2, i + 6), 16));
                        i += 5;
                    } catch (NumberFormatException _ex) {
                        retBuf.append(unicodeStr.charAt(i));
                    }
                else
                    retBuf.append(unicodeStr.charAt(i));
            } else {
                retBuf.append(unicodeStr.charAt(i));
            }

        return retBuf.toString();
    }

    public static String unicode2UnicodeEscWithoutComment(String uniStr) throws IOException {
        StringBuffer buf = new StringBuffer();
        BufferedReader reader = new BufferedReader(new StringReader(uniStr));
        boolean continueFlg = false;
        for (String line = null; (line = reader.readLine()) != null;) {
            if ((line.trim().startsWith("#") || line.trim().startsWith("!")) && !continueFlg) {
                buf.append(line);
            } else {
                if (line.endsWith("\\"))
                    continueFlg = true;
                else
                    continueFlg = false;
                buf.append(unicode2UnicodeEsc(line));
            }
            buf.append("\n");
        }

        if (!uniStr.endsWith("\n"))
            buf.deleteCharAt(buf.length() - 1);
        return buf.toString();
    }

}
