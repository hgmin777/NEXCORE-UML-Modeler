/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.core.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.core.util</li>
 * <li>설 명 : OCLExpressionUtil</li>
 * <li>작성일 : 2009. 12. 24.</li>
 * <li>작성자 : 한승일</li>
 * </ul>
 */
public class OCLExpressionUtil {

    /** KEYWORD_DELIMITER */
    private static final String KEYWORD_DELIMITER = ", "; //$NON-NLS-1$

    /** KEYWORD_SPLITTER */
    private static final String KEYWORD_SPLITTER_REGEX = ",\\s*"; //$NON-NLS-1$

    /** UNLIMITED_EMPTY_REGEX */
    private static final String UNLIMITED_EMPTY_REGEX = "\\s*"; //$NON-NLS-1$

    /**
     * 
     * 
     * @param text
     * @return List<String>
     */
    public static List<String> parseStringToKeywordList(String text) {
        ArrayList<String> list = new ArrayList<String>();

        String[] keywordList = text.split(KEYWORD_SPLITTER_REGEX);
        Pattern pattern;
        Matcher matcher;

        pattern = Pattern.compile(UNLIMITED_EMPTY_REGEX);
        for (String keyword : keywordList) {
            matcher = pattern.matcher(keyword);
            if (!matcher.matches()) {
                list.add(keyword.trim());
            }
        }
        return list;

    }

    /**
     * 
     * 
     * @param keywordList
     * @return String
     */
    public static String parseKeywordListToString(List<String> keywordList) {
        StringBuilder builder = new StringBuilder();
        String temp;
        for (Iterator<String> iter = keywordList.iterator(); iter.hasNext();) {
            temp = iter.next();
            if (0 != temp.trim().length()) {
                builder.append(temp);
                if (iter.hasNext()) {
                    builder.append(KEYWORD_DELIMITER);
                }
            }
        }
        return builder.toString();

    }
}
