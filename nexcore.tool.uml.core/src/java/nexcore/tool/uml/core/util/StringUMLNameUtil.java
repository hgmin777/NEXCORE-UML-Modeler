/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.core.util;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.core.util</li>
 * <li>설 명 : StringUMLNameUtil</li>
 * <li>작성일 : 2009. 12. 8.</li>
 * <li>작성자 : 한승일</li>
 * </ul>
 */
public class StringUMLNameUtil {

    /** 인터페이스 구현 접미어 */
    private static final String IMPL_SUFFIX = "Impl"; //$NON-NLS-1$

    /** 클래스 명 구분자 */
    private static final String NAME_SEPERATOR = "."; //$NON-NLS-1$

    /** EMPTY_TEXT */
    private static final String EMPTY_TEXT = ""; //$NON-NLS-1$

    /**
     * 
     * 
     * @param expression
     * @return String
     */
    public static String getUMLNotationNameFromClass(String expression) {
        String name = expression.substring(expression.lastIndexOf(NAME_SEPERATOR) + 1);
        return name.replace(IMPL_SUFFIX, EMPTY_TEXT);
    }

    /**
     * 클래스 이름을 리턴함.
     * 
     * @param expression
     * @return String
     */
    public static String getUMLNotationName(String expression) {
        String name = expression.substring(expression.lastIndexOf(NAME_SEPERATOR) + 1);
        return name;
    }
}
