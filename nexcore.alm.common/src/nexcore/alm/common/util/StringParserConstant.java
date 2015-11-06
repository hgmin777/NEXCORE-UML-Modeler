/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.alm.common.util;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.core</li>
 * <li>서브 업무명 : nexcore.tool.core.util</li>
 * <li>설 명 : StringUtilConstant</li>
 * <li>작성일 : 2007. 04. 02</li>
 * <li>작성자 : SKC&amp;C 기술전략팀 표준 업무 담당자</li>
 * </ul>
 * 
 * StringParser에서 사용하는 상수들의 모음
 */
public class StringParserConstant {

    /** KeyStr 형식 문자열에서 Key 시작 구분자 */
    public final static String KEY_STR_BEGIN = "|_";

    /** KeyStr 형식 문자열에서 value 시작 구분자 */
    public final static String KEY_STR_EQUAL = "=";

    /** KeyStr 형식 문자열에서 value 간의 구분자 */
    public final static String KEY_STR_VALUE_END = "._";

    /** SLASH 문자 (여러 개의 데이터를 연결하여 사용하는 경우의 데이터 구분자) */
    public final static String SLASH = "/";

    /** TAB 문자 */
    public final static String TAB = "\t";

    /** new line 문자 */
    public final static String NEW_LINE = "\n";

    /** carrige return 문자 */
    public static final String CARRIAGE_RETURN = "\r";
}
