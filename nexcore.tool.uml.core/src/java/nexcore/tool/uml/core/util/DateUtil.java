/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.core.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import nexcore.tool.uml.core.exception.UMLRuntimeException;

/**
 * <ul>
 * <li>업무 그룹명 : nexbuild.foundation.core</li>
 * <li>서브 업무명 : nexbuild.foundation.core.util</li>
 * <li>설 명 : DateUtil</li>
 * <li>작성일 : 2007. 04. 12</li>
 * <li>작성자 : 기술전략팀 표준 업무 담당자</li>
 * </ul>
 * 음력과 양력 간 전환이 가능한 일자는 1881년에서 2043년도 까지이다. 이외의 범위에서는 RuntimeException이 발생한다.
 * 
 * 내부적으로 기준 포맷은 yyyyMMdd 표준을 따른다. <br>
 * 
 * <pre>
 *  &circ;\d{4}[.-\/]?\d{2}[.-\/]?\d{2}
 * </pre>
 * 
 * <br>
 * + Glossaries <br>
 * - DateString : 날짜를 표현한 String <br>
 * - delimiter : 년,월,일 사이의 구분자. <br>
 */
public class DateUtil {

    /** 년(Year)을 나타내는 상수 */
    public final static int yyyy = 1;

    /** 월(Month) 나타내는 상수 */
    public final static int MM = 2;

    /** 년월(Year&Month)을 나타내는 상수 */
    public final static int yyyyMM = 3;

    /** 년월일(Year&Month&Day)을 나타내는 상수 */
    public final static int yyyyMMdd = 4;

    /** 월일(Month&Day)을 나타내는 상수 */
    public final static int MMdd = 5;

    /** 일(Day)을 나타내는 상수 */
    public final static int dd = 6;

    /** 기본적인 DateFormat의 패턴은 "yyyyMMdd" 형식이다. */
    public final static String DEFAULT_PATTERN = "yyyyMMdd"; //$NON-NLS-1$

    /** 날짜타입 : 음력 */
    public final static String LUNAR = "lunar"; //$NON-NLS-1$

    /** 날짜타입 : 양력+ */
    public final static String SOLAR_PLUS = "solar+"; //$NON-NLS-1$

    /** 날짜타입 : 양력- */
    public final static String SOLAR_MINUS = "solar-"; //$NON-NLS-1$

    /**
     * 1초
     */
    private static final int SECOND = 1000;

    /**
     * 1분
     */
    private static final int MINUTE = 60 * SECOND;

    /**
     * 1시간
     */
    private static final int HOUR = 60 * MINUTE;

    /**
     * 음력 계산 인자
     */
    private static final int lunarFactor[] = { 1, 2, 1, 2, 1, 2, 2, 3, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 1, 2, 2, 1, 2,
        2, 0, 1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 1, 2, 0, 2, 1, 1, 2, 1, 3, 2, 1, 2, 2, 1, 2, 2, 2, 1, 1, 2, 1, 1, 2, 1, 2,
        1, 2, 2, 0, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 0, 2, 2, 1, 2, 3, 2, 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 1,
        1, 2, 1, 2, 1, 0, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 0, 1, 2, 3, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1,
        2, 1, 2, 2, 1, 2, 2, 0, 1, 1, 2, 1, 1, 2, 3, 2, 2, 1, 2, 2, 2, 1, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 0, 1, 2, 1,
        2, 1, 1, 2, 1, 2, 1, 2, 2, 0, 2, 1, 2, 1, 2, 3, 1, 2, 1, 2, 1, 2, 1, 2, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 0, 1,
        2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 0, 2, 1, 2, 3, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2,
        0, 1, 2, 1, 1, 2, 1, 2, 2, 3, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1, 0, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2,
        2, 2, 0, 1, 2, 1, 2, 1, 3, 2, 1, 1, 2, 2, 1, 2, 2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 0, 2, 2, 1, 2, 2, 1, 1, 2,
        1, 2, 1, 2, 0, 1, 2, 2, 1, 4, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 0, 2, 1, 1, 2, 2, 1,
        2, 1, 2, 2, 1, 2, 0, 1, 2, 3, 1, 2, 1, 2, 1, 2, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1, 0, 2, 1, 2, 1,
        1, 2, 3, 1, 2, 2, 1, 2, 2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 0, 2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 0, 2, 2,
        1, 2, 2, 3, 1, 2, 1, 2, 1, 1, 2, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 0, 1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 0,
        2, 1, 3, 2, 1, 2, 2, 1, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1, 2, 0, 1, 2, 1, 1, 2, 1, 2, 3, 2, 2, 1,
        2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 2, 0, 2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 0, 2, 1, 2, 2, 1, 3, 2, 1, 1,
        2, 1, 2, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 1, 2, 0, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 1, 0, 2, 1, 2, 2, 3, 2, 1,
        2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 0, 2, 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 0, 1, 2, 3, 1, 2,
        1, 1, 2, 2, 1, 2, 2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 0, 1, 2, 2, 1, 1, 2, 3, 1, 2, 1, 2, 2, 1, 2, 2, 2,
        1, 1, 2, 1, 1, 2, 1, 2, 1, 0, 2, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 0, 1, 2, 2, 1, 2, 4, 1, 2, 1, 2, 1, 1, 2, 1,
        2, 1, 2, 2, 1, 2, 2, 1, 2, 1, 2, 0, 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 0, 2, 1, 1, 4, 1, 2, 1, 2, 1, 2, 2, 2,
        1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 1, 0, 2, 2, 1, 1, 2, 1, 1, 4, 1, 2, 2, 1, 2, 2, 2, 1, 1, 2, 1, 1, 2, 1, 2,
        1, 2, 0, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 0, 2, 2, 1, 2, 2, 1, 4, 1, 1, 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1,
        2, 1, 1, 2, 0, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 0, 1, 1, 2, 1, 4, 1, 2, 1, 2, 2, 1, 2, 2, 1, 1, 2, 1, 1, 2,
        1, 2, 2, 2, 1, 2, 0, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 0, 2, 2, 3, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1, 2, 1,
        2, 1, 1, 2, 1, 2, 1, 2, 0, 2, 2, 1, 2, 1, 2, 1, 3, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1, 0, 2, 1,
        2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 0, 1, 2, 1, 2, 1, 4, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 1, 2, 2, 1, 2, 2, 1, 2, 2, 0,
        1, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 0, 2, 1, 1, 4, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2,
        2, 0, 2, 1, 2, 1, 2, 1, 1, 2, 3, 2, 1, 2, 2, 1, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 0, 1, 2, 2, 1, 2, 1, 2, 1, 2,
        1, 2, 1, 0, 2, 1, 2, 1, 2, 2, 3, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 0, 1, 2, 1, 1, 2, 1, 2,
        2, 1, 2, 2, 1, 0, 2, 1, 2, 1, 3, 2, 1, 2, 1, 2, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 0, 1, 2, 1, 2, 1,
        1, 2, 1, 1, 2, 2, 1, 0, 2, 2, 2, 3, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 2, 1, 2, 2, 1, 1, 2, 1, 2, 1, 2, 0, 1, 2, 2,
        1, 2, 1, 2, 3, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 0, 2, 1, 1, 2, 2, 1, 2, 1, 2, 2, 1, 2, 0, 1,
        2, 1, 1, 2, 3, 2, 1, 2, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1, 0, 2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 2, 1,
        0, 2, 2, 1, 2, 3, 1, 2, 1, 1, 2, 2, 1, 2, 2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 0, 2, 2, 1, 2, 1, 2, 1, 2, 3, 2,
        1, 1, 2, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 1, 0, 2, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 0, 2, 1, 1, 2, 1, 2, 4, 1,
        2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 0, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 2, 0, 2, 1, 2, 1, 3, 2,
        1, 1, 2, 2, 1, 2, 2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 0, 2, 1, 2, 2, 1, 1, 2, 1, 1, 2, 3, 2, 2, 1, 2, 2, 1,
        2, 1, 2, 1, 1, 2, 1, 2, 0, 1, 2, 2, 1, 2, 2, 1, 2, 1, 2, 1, 1, 0, 2, 1, 2, 2, 1, 2, 3, 2, 2, 1, 2, 1, 2, 1, 1,
        2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 0, 2, 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 0, 1, 2, 1, 1, 2, 3, 1, 2, 1, 2, 2, 2, 2,
        1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 0, 1, 2, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 0, 1, 2, 2, 3, 2, 1, 2, 1, 1, 2, 1,
        2, 1, 2, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 0, 1, 2, 2, 1, 2, 2, 1, 2, 3, 2, 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2,
        2, 1, 2, 0, 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 0, 2, 1, 1, 2, 1, 3, 2, 2, 1, 2, 2, 2, 1, 2, 1, 1, 2, 1, 1, 2,
        1, 2, 2, 2, 1, 0, 2, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 1, 0, 2, 2, 2, 1, 3, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1, 2, 1,
        2, 1, 1, 2, 1, 2, 1, 0, 2, 2, 1, 2, 2, 1, 2, 1, 1, 2, 1, 2, 0, 1, 2, 3, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1,
        2, 1, 2, 2, 1, 2, 2, 1, 1, 0, 2, 1, 2, 1, 2, 1, 2, 3, 2, 2, 1, 2, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 1, 2, 0, 2,
        1, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 0, 2, 2, 1, 1, 2, 3, 1, 2, 1, 2, 1, 2, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2,
        0, 2, 1, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1, 0, 2, 1, 2, 4, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2,
        1, 2, 0, 1, 2, 1, 2, 1, 2, 1, 2, 2, 3, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 2, 2, 1, 2, 2, 0, 1, 1, 2, 1, 1, 2, 1, 2,
        2, 1, 2, 2, 0, 2, 1, 1, 2, 1, 3, 2, 1, 2, 1, 2, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 0, 2, 1, 2, 1, 2, 1,
        1, 2, 1, 2, 1, 2, 0, 2, 1, 2, 2, 3, 2, 1, 1, 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 0, 2, 1, 2, 1,
        2, 2, 1, 2, 1, 2, 1, 2, 0, 1, 2, 3, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1, 0, 2, 1,
        2, 1, 1, 2, 3, 2, 1, 2, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 0, 1, 2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 2, 0,
        1, 2, 2, 1, 2, 3, 1, 2, 1, 1, 2, 2, 1, 2, 2, 1, 2, 2, 1, 1, 2, 1, 1, 2, 2, 0, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2,
        1, 0, 2, 1, 2, 3, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 0, 1, 2, 1, 1, 2, 1, 2, 3, 2,
        2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1, 0, 2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 0, 2, 2, 1, 2, 1, 1, 4,
        1, 1, 2, 1, 2, 2, 2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 0, 2, 2, 1, 2, 1, 2, 1, 2, 1, 1, 2, 1, 0, 2, 2, 1, 2, 2,
        3, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 1, 2, 1, 0, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 0, 1, 2, 3,
        1, 2, 1, 2, 1, 2, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 2, 0, };

    /**
     * 월별 일수 계산 인자
     */
    private static final int daysOfMonth[] = { 31, 0, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    /**
     * <code>DateUtil.getCurrentDate(DateUtils.yyyyMMdd,"")</code> 와 동일하다.
     * 
     * <pre>
     * [사용법]
     * String currentDate = DateUtil.getCurrentDate();
     * //  결과 : currentDate =&gt; 20070412
     * </pre>
     * 
     * @return String 현재 날짜 기본문자열
     */
    public static String getCurrentDate() {
        return getCurrentDate(DateUtil.yyyyMMdd, ""); //$NON-NLS-1$
    }

    /**
     * <code>DateUtil.getCurrentDate(int type,"")</code> 와 동일
     * 
     * <pre>
     * [사용법]
     * String currentDate = DateUtil.getCurrentDate(DateUtil.yyyyMMdd);
     * //  결과 : currentDate =&gt; 20070412
     * </pre>
     * 
     * @param type
     *            날짜 타입 (DateUtil.yyyy, DateUtil.MM, DateUtil.yyyyMM ,
     *            DateUtil.yyyyMMdd, DateUtil.dd)
     * @return String 현재 날짜
     */
    public static String getCurrentDate(int type) {
        return getCurrentDate(type, ""); //$NON-NLS-1$
    }

    /**
     * 현재 날짜를 구한다.
     * 
     * <pre>
     * [사용법]
     * String currentDate = DateUtil.getCurrentDate(DateUtil.yyyyMMdd, &quot;/&quot;);
     * // 결과 : currentDate =&gt; 2007/04/12
     * </pre>
     * 
     * @param type
     *            날짜 타입 (DateUtil.yyyy, DateUtil.MM, DateUtil.yyyyMM ,
     *            DateUtil.yyyyMMdd, DateUtil.dd) type이 알 수 없는 값인 경우 default는
     *            DateUtil.yyyyMMdd형
     * @return String 현재 날짜
     */
    public static String getCurrentDate(int type, String delimiter) {

        Calendar now = Calendar.getInstance();

        String curYear = String.valueOf(now.get(Calendar.YEAR));

        int month = now.get(Calendar.MONTH) + 1;
        String curMonth = month < 10 ? "0" + month : String.valueOf(month); //$NON-NLS-1$

        int day = now.get(Calendar.DATE);
        String curDay = day < 10 ? "0" + day : String.valueOf(day); //$NON-NLS-1$

        StringBuffer rtnDate = new StringBuffer(16);
        switch (type) {
            case DateUtil.yyyy:
                rtnDate.append(curYear);
                break;
            case DateUtil.MM:
                rtnDate.append(curMonth);
                break;
            case DateUtil.yyyyMM:
                rtnDate.append(curYear);
                rtnDate.append(delimiter);
                rtnDate.append(curMonth);
                break;
            case DateUtil.yyyyMMdd:
                rtnDate.append(curYear);
                rtnDate.append(delimiter);
                rtnDate.append(curMonth);
                rtnDate.append(delimiter);
                rtnDate.append(curDay);
                break;
            case DateUtil.MMdd:
                rtnDate.append(curMonth);
                rtnDate.append(delimiter);
                rtnDate.append(curDay);
                break;
            case DateUtil.dd:
                rtnDate.append(curDay);
                break;
            default:
                rtnDate.append(curYear);
                rtnDate.append(delimiter);
                rtnDate.append(curMonth);
                rtnDate.append(delimiter);
                rtnDate.append(curDay);
                break;

        }

        return rtnDate.toString();
    }

    /**
     * 특정 패턴에 맞게 현재시간을 출력한다. <br>
     * 
     * <pre>
     * [사용법]
     * String currentDate = DateUtil.getCurrentDate(&quot;yyyy년 MM월 dd일&quot;);
     * // 결과 : currentDate =&gt; 2007년 04월 12일
     * </pre>
     * 
     * @param pattern
     *            일자패턴
     * @return String 패턴 적용된 일자
     */
    public static String getCurrentDate(String pattern) {
        DateFormat format = new SimpleDateFormat(pattern);
        return format.format(new Date());
    }

    /**
     * trimMiliSeconds
     *  
     * @param time
     * @return String
     */
    public static String trimMiliSeconds(String time) {
        if (time.matches("[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9] [0-9][0-9]:[0-9][0-9]:[0-9][0-9]\\.[0-9]")
            || time.matches("[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9] [0-9][0-9]:[0-9][0-9]:[0-9][0-9]\\.[0-9][0-9]")
            || time.matches("[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9] [0-9][0-9]:[0-9][0-9]:[0-9][0-9]\\.[0-9][0-9][0-9]")) {
            int index = time.indexOf('.');
            return time.substring(0, index);
        }

        return time;
    }

    /**
     * 포맷을 yyyyMMdd,yyyy-MM-dd, yyyy/MM/dd 등의 형식을 상호 변경시켜준다. yyyyMM, yyyy-MM,
     * yyyy/MM 형식도 상호 변경된다.
     * 
     * <pre>
     * [사용법]
     * String formatDate = DateUtil.formatDate(&quot;2007-04-12&quot;, &quot;/&quot;);
     * // 결과 : formatDate =&gt; 2007/04/12
     * </pre>
     * 
     * @param dateString
     *            날짜를 표현한 문자열
     * @param delimiter
     *            구분자
     * @return String 구분자와 조합된 날짜
     */
    public static String formatDate(String dateString, String delimiter) {
        if (dateString == null)
            return ""; //$NON-NLS-1$
        String output = dateString.replaceAll("[^0-9]", ""); //$NON-NLS-1$ //$NON-NLS-2$

        if (output.length() == 8) {
            return (output.substring(0, 4) + delimiter + output.substring(4, 6) + delimiter + output.substring(6, 8));
        } else if (output.length() == 6) {
            return (output.substring(0, 4) + delimiter + output.substring(4, 6));
        } else {
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * Date 값을 "yyyyMMdd"형식으로 "String"으로 변환
     * 
     * <pre>
     * [사용법]
     * String formatDate = DateUtil.dateToString(new Date());
     * // 결과 : formatDate =&gt; 20070412
     * </pre>
     * 
     * @param date
     *            날짜객체
     * @return String 날짜 문자
     */
    public static String dateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(DateUtil.DEFAULT_PATTERN);
        return formatter.format(date);
    }

    /**
     * Date 값을 원하는 형식(Pattern)으로 스트링으로 변환
     * 
     * <pre>
     * [사용법]
     * String formatDate = DateUtil.dateToString(new Date(), DateTime.DATE_TIME_PATTERN);
     * // 결과 : formatDate =&gt; 20070412141354
     * </pre>
     * 
     * @param date
     *            날짜객체
     * @param pattern
     *            날짜 패턴
     * @return String 패턴 적용된 날짜 문자열
     * @see java.text.SimpleDateFormat
     */
    public static String dateToString(Date date, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    /**
     * String 값을 format 형식의 Date 값으로 Parseing 한다.
     * 
     * <pre>
     * [사용법]
     * Date parseDate = DateUtil.parseDate(&quot;20070412141354&quot;, DateTime.DATE_TIME_PATTERN);
     * // 결과 : parseDate.toString() =&gt; Thu Apr 12 14:13:54 KST 2007
     * </pre>
     * 
     * @param strDate
     *            날짜문자열
     * @param pattern
     *            날짜 패턴
     * @return Date 날짜객체
     */
    public static Date parseDate(String strDate, String pattern) {
        try {
            DateFormat format = new SimpleDateFormat(pattern);
            return format.parse(strDate);
        } catch (ParseException pe) {
            throw new UMLRuntimeException(pe);
        }
    }

    /**
     * 기본 날짜 패턴(yyyyMMdd)의 String 값을 format 형식의 Date 값으로 Parseing 한다.
     * 
     * <pre>
     * [사용법]
     * Date parseDate = DateUtil.parseDate(&quot;20070412&quot;);
     * // 결과 : parseDate.toString() =&gt; Thu Apr 12 00:00:00 KST 2007
     * </pre>
     * 
     * @param strDate
     *            날짜문자열
     * @param pattern
     *            날짜 패턴
     * @return Date 날짜객체
     */
    public static Date parseDate(String strDate) {
        return parseDate(strDate, DateUtil.DEFAULT_PATTERN);
    }

    /**
     * 기본 날짜 패턴(yyyyMMdd)의 String 값을 format 형식의 Calendar 값으로 Parseing 한다.
     * 
     * <pre>
     * [사용법]
     * Calendar parseCalendar = DateUtil.parseCalendar(&quot;20070412&quot;);
     * // 결과 : parseCalendar.getTime().toString() =&gt; Thu Apr 12 00:00:00 KST 2007
     * </pre>
     * 
     * @param strDate
     *            날짜문자열
     * @return Calendar 칼린더 객체
     */
    public static Calendar parseCalendar(String strDate) {
        return parseCalendar(strDate, DateUtil.DEFAULT_PATTERN);
    }

    /**
     * String 값을 format 형식의 Calendar 값으로 Parseing 한다.
     * 
     * <pre>
     * [사용법]
     * Calendar parseCalendar = DateUtil.parseCalendar(&quot;2007-04-12&quot;, DateTime.FORMATTED_DATE_PATTERN);
     * // 결과 : parseCalendar.getTime().toString() =&gt; Thu Apr 12 00:00:00 KST 2007
     * </pre>
     * 
     * @param strDate
     * @param pattern
     * @return Calendar
     */
    public static Calendar parseCalendar(String strDate, String pattern) {
        Date date = parseDate(strDate, pattern);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 문자열 형식의 일자를 에서 달을 연산할때 사용 <br>
     * 의미상의 달수를 계산한다. 즉, 2005/12/31 + 2 Month 의 결과값이 2006/03/03 일이 아닌 2006/02/28
     * 로 된다. <br>
     * 
     * <pre>
     * [사용법]
     * String addMonth = DateUtil.addMonth(&quot;20070131&quot;, 1);
     * // 결과 : addMonth =&gt; 20070228
     * </pre>
     * 
     * @param dateString
     *            문자열 날짜 "yyyyMMdd"형식이어야 함
     * @param value
     *            달을 연산을 할 값
     * @return "yyyyMMdd"형식으로 return
     */
    public static String addMonth(String dateString, int value) {

        SimpleDateFormat format = new SimpleDateFormat("yyyyMM"); //$NON-NLS-1$
        ParsePosition pos = new ParsePosition(0);
        Date date = format.parse(dateString.substring(0, 6), pos);

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int day, daysOfMonth, suffix;
        cal.add(Calendar.MONTH, value);
        day = Integer.parseInt(dateString.substring(6, 8));
        daysOfMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        StringBuffer result = new StringBuffer(format.format(cal.getTime()).toString());
        suffix = Math.min(day, daysOfMonth);
        if (suffix >= 10) {
            result.append(suffix);
        } else {
            result.append("0"); //$NON-NLS-1$
            result.append(suffix);
        }

        return result.toString();
    }

    /**
     * 문자열 형식(yyyyMMdd)의 일자를 에서 날짜를 연산할때 사용,
     * 
     * <pre>
     * [사용법]
     * DateUtil.addDay(&quot;20070131&quot;, 28);  // 결과 =&gt; 20070228
     * DateUtil.addDay(&quot;20070131&quot;, 29);  // 결과 =&gt; 20070301
     * </pre>
     * 
     * @param dateString
     *            문자열 날짜 "yyyyMMdd"형식이어야 함
     * @param value
     *            날짜를 연산을 할 값
     * @return "yyyyMMdd"형식으로 return
     */
    public static String addDay(String dateString, int value) {
        SimpleDateFormat format = new SimpleDateFormat(DateUtil.DEFAULT_PATTERN);
        ParsePosition pos = new ParsePosition(0);
        Date date = format.parse(dateString, pos);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, value);
        return format.format(cal.getTime()).toString();
    }

    /**
     * 입력된 달의 마지막일자를 반환.
     * 
     * <pre>
     * [사용법]
     * DateUtil.getDaysOfMonth(&quot;20070131&quot;);  // 결과 =&gt; 31
     * DateUtil.getDaysOfMonth(&quot;20070202&quot;);  // 결과 =&gt; 28
     * DateUtil.getDaysOfMonth(&quot;20040221&quot;);  // 결과 =&gt; 29 (윤달)
     * </pre>
     * 
     * @param month
     *            "yyyyMMdd" 형식의 Date String
     * @return int 해당 달의 날 수
     */
    public static int getDaysOfMonth(String month) {
        try {
            Calendar cal = Calendar.getInstance();
            Date date = new SimpleDateFormat(DateUtil.DEFAULT_PATTERN).parse(month);
            cal.setTime(date);
            return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        } catch (ParseException pe) {
            throw new UMLRuntimeException(pe);
        }
    }

    /**
     * 날짜사이의 간격을 구한다.
     * 
     * <pre>
     * [사용법]
     * DateUtil.getDaysBetween(&quot;20070228&quot;, &quot;20070305&quot;);  // 결과 =&gt; 5
     * DateUtil.getDaysBetween(&quot;20040228&quot;, &quot;20040305&quot;);  // 결과 =&gt; 6 (윤달)
     * </pre>
     * 
     * @param fromDateStr
     *            'yyyyMMdd' 패턴
     * @param toDateStr
     *            'yyyyMMdd' 패턴
     * @return int 날짜 간격
     */
    public static int getDaysBetween(String fromDateStr, String toDateStr) {
        Date fromDate = parseDate(fromDateStr);
        Date toDate = parseDate(toDateStr);
        long gap = toDate.getTime() - fromDate.getTime();
        return (int) (gap / (24 * 60 * 60 * 1000));

    }

    /**
     * 음력을 양력으로 전환한다.
     * 
     * <pre>
     * [사용법]
     * DateUtil.toSolarDate(&quot;20070228&quot;, false); // 결과 =&gt; 20070415
     * DateUtil.toSolarDate(&quot;20040228&quot;, true);  // 결과 =&gt; 20040417 (윤달)
     * </pre>
     * 
     * @param transeDay
     *            음력일('yyyyMMdd')
     * @param isLeapMonth
     *            윤달 여부
     * @return String 처리결과 양력일 엔티티
     * @throws java.lang.Exception
     */
    public static String toSolarDate(String transeDay, boolean isLeapMonth) {
        int lyear = Integer.parseInt(transeDay.substring(0, 4));
        int lmonth = Integer.parseInt(transeDay.substring(4, 6));
        int lday = Integer.parseInt(transeDay.substring(6, 8));

        if (!isLeapMonth && !verifyDate(lyear, lmonth, lday, "solar-")) { //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
        if (isLeapMonth && !verifyDate(lyear, lmonth, lday, "solar+")) { //$NON-NLS-1$
            return ""; //$NON-NLS-1$

        }
        int firstMonth = -1;
        long td = 0L;
        if (lyear != 1881) {
            firstMonth = lyear - 1882;
            for (int i = 0; i <= firstMonth; i++) {
                for (int j = 0; j < 13; j++) {
                    td = td + lunarFactor[i * 13 + j];
                }

                if (lunarFactor[i * 13 + 12] == 0) {
                    td = td + 336L;
                } else {
                    td = td + 362L;
                }
            }

        }
        firstMonth++;
        int monthTemp = lmonth - 1;
        int secondMonth = -1;
        do {
            secondMonth++;
            if (lunarFactor[firstMonth * 13 + secondMonth] > 2) {
                td = td + 26L + lunarFactor[firstMonth * 13 + secondMonth];
                monthTemp++;
                continue;
            }
            if (secondMonth == monthTemp) {
                break;
            }
            td = td + 28L + lunarFactor[firstMonth * 13 + secondMonth];
        } while (true);
        if (isLeapMonth) {
            td = td + 28L + lunarFactor[firstMonth * 13 + secondMonth];
        }
        td = td + lday + 29L;
        firstMonth = 1880;
        boolean leap = false;

        do {
            firstMonth++;
            leap = firstMonth % 400 == 0 || firstMonth % 100 != 0 && firstMonth % 4 == 0;
            if (leap) {
                secondMonth = 366;
            } else {
                secondMonth = 365;
            }
            if (td < secondMonth) {
                break;
            }
            td = td - secondMonth;
        } while (true);

        int syear = firstMonth;
        daysOfMonth[1] = secondMonth - 337;
        firstMonth = 0;

        do {
            firstMonth++;
            if (td <= daysOfMonth[firstMonth - 1]) {
                break;
            }
            td = td - daysOfMonth[firstMonth - 1];
        } while (true);
        int smonth = firstMonth;
        int sday = (int) td;
        long y = syear - 1L;
        td = ((y * 365L + y / 4L) - y / 100L) + y / 400L;
        leap = syear % 400 == 0 || syear % 100 != 0 && syear % 4 == 0;
        if (leap) {
            daysOfMonth[1] = 29;
        } else {
            daysOfMonth[1] = 28;
        }
        for (int i = 0; i < smonth - 1; i++) {
            td = td + daysOfMonth[i];
        }

        td = td + sday;
        int i = (int) (td % 10L);
        i = (i + 4) % 10;
        int j = (int) (td % 12L);
        j = (j + 2) % 12;

        String sValue = String.valueOf(syear);

        if (smonth < 10) {
            sValue += "0"; //$NON-NLS-1$
        }
        sValue += String.valueOf(smonth);
        if (sday < 10) {
            sValue += "0"; //$NON-NLS-1$
        }
        sValue += String.valueOf(sday);

        return sValue;
    }

    /**
     * 양력을 음력으로 변환한다.
     * 
     * <pre>
     * [사용법]
     * DateUtil.toLunarDate(&quot;20070415&quot;); // 결과 =&gt; 20070228
     * DateUtil.toLunarDate(&quot;20040417&quot;);  // 결과 =&gt; 20040228 (윤달)
     * </pre>
     * 
     * @param transeDay
     *            양력일('yyyyMMdd')
     * @return String 처리결과 음력일
     * @throws java.lang.Exception
     */
    public static String toLunarDate(String transeDay) {
        int dt[] = new int[163];
        for (int i = 0; i < 163; i++) {
            dt[i] = 0;
            for (int j = 0; j < 12; j++)
                switch (lunarFactor[i * 13 + j]) {
                    case 1: // '\001'
                    case 3: // '\003'
                        dt[i] = dt[i] + 29;
                        break;

                    case 2: // '\002'
                    case 4: // '\004'
                        dt[i] = dt[i] + 30;
                        break;
                }

            switch (lunarFactor[i * 13 + 12])

            {
                case 1: // '\001'
                case 3: // '\003'
                    dt[i] = dt[i] + 29;
                    break;

                case 2: // '\002'
                case 4: // '\004'
                    dt[i] = dt[i] + 30;
                    break;
            }
        }

        int syear = Integer.parseInt(transeDay.substring(0, 4));
        int smonth = Integer.parseInt(transeDay.substring(4, 6));
        int sday = Integer.parseInt(transeDay.substring(6, 8));

        if (!verifyDate(syear, smonth, sday, "lunar")) { //$NON-NLS-1$
            throw new UMLRuntimeException("Input Date is invalid (only 1881~2043 supported)- " + transeDay); //$NON-NLS-1$
        }
        long november = syear - 1;
        long secondToday = ((november * 365L + november / 4L) - november / 100L) + november / 400L;
        boolean ll = syear % 400 == 0 || syear % 100 != 0 && syear % 4 == 0;
        if (ll) {
            daysOfMonth[1] = 29;
        } else {
            daysOfMonth[1] = 28;
        }
        for (int i = 0; i < smonth - 1; i++) {
            secondToday = secondToday + daysOfMonth[i];
        }

        secondToday = secondToday + sday;

        long td = (secondToday - 0xa7a5eL) + 1L;
        long todayTemp = dt[0];
        int i = 0;
        for (i = 0; i < 163; i++) {
            if (td <= todayTemp) {
                break;
            }
            todayTemp = todayTemp + dt[i + 1];
        }

        int lyear = i + 1881;
        todayTemp = todayTemp - dt[i];
        td = td - todayTemp;
        int jcount = 0;
        if (lunarFactor[i * 13 + 12] != 0) {
            jcount = 13;
        } else {
            jcount = 12;
        }
        int secondMonth = 0;
        int j = 0;
        int firstMonth;
        for (j = 0; j < jcount; j++) {
            if (lunarFactor[i * 13 + j] <= 2) {
                secondMonth++;
            }
            if (lunarFactor[i * 13 + j] <= 2) {
                firstMonth = lunarFactor[i * 13 + j] + 28;
            } else {
                firstMonth = lunarFactor[i * 13 + j] + 26;
            }
            if (td <= firstMonth) {
                break;
            }
            td = td - firstMonth;
        }

        long lmonth = secondMonth;
        int lday = (int) td;

        i = (int) ((secondToday + 4L) % 10L);
        j = (int) ((secondToday + 2L) % 12L);

        String sValue = String.valueOf(lyear);

        if (lmonth < 10) {
            sValue += "0"; //$NON-NLS-1$
        }
        sValue += String.valueOf(lmonth);
        if (lday < 10) {
            sValue += "0"; //$NON-NLS-1$
        }
        sValue += String.valueOf(lday);

        return sValue;
    }

    /**
     * 양/음력 전환이 가능한지 점검한다.
     * 
     * <pre>
     * [사용법]
     * DateUtil.verifyDate(2004, 4, 17, DateUtil.LUNAR);            // 결과 =&gt; true
     * DateUtil.verifyDate(2004, 4, 17, DateUtil.SOLAR_PLUS);       // 결과 =&gt; false 
     * DateUtil.verifyDate(2004, 4, 17, DateUtil.SOLAR_MINUS);      // 결과 =&gt; true
     * </pre>
     * 
     * @param year
     *            년도
     * @param month
     *            월
     * @param day
     *            일
     * @param type
     *            변경하려는 방향.
     * @return
     */
    private static boolean verifyDate(int year, int month, int day, String type) {
        if (year < 1881 || year > 2043 || month < 1 || month > 12)
            return false;
        if (type.equals(DateUtil.LUNAR) && day > daysOfMonth[month - 1]) {
            return false;
        }
        if (type.equals(DateUtil.SOLAR_PLUS)) {
            if (lunarFactor[(year - 1881) * 13 + 12] < 1) {
                return false;
            }
            if (lunarFactor[(year - 1881) * 13 + month] < 3) {
                return false;
            }
            if (lunarFactor[(year - 1881) * 13 + month] + 26 < day) {
                return false;
            }
        }
        if (type.equals(DateUtil.SOLAR_MINUS)) {
            int j = month - 1;
            for (int i = 1; i <= 12; i++) {
                if (lunarFactor[((year - 1881) * 13 + i) - 1] > 2) {
                    j++;
                }
            }

            if (day > lunarFactor[(year - 1881) * 13 + j] + 28)
                return false;
        }
        return true;
    }

    /**
     * 현재의 Timestamp 객체를 가져온다.
     * 
     * <pre>
     * [사용법]
     * DateUtil.getCurrentTimeStamp(); // 결과 =&gt; 2007-04-12 15:14:13.138
     * </pre>
     * 
     * @return Timestamp 타임스템프 객체
     */
    public static Timestamp getCurrentTimeStamp() {
        Calendar now = Calendar.getInstance();
        return new Timestamp(now.getTimeInMillis());
    }

    /**
     * 입력된 타임스템프에 원하는 포멧을 적용하여 날짜 문자열로 반환한다.
     * 
     * <pre>
     * [사용법]
     *  DateUtil.timestampToString(DateUtil.getCurrentTimeStamp(), DateTime.FORMATTED_DATE_PATTERN);
     *  // 결과 =&gt; 2007-04-12
     * </pre>
     * 
     * @param now
     *            타임 스템프
     * @param pattern
     *            날짜포멧
     * @return String 날짜 문자열
     */
    public static String timestampToString(Timestamp now, String pattern) {
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        return sf.format(now);
    }

    /**
     * 날짜 문자열을 타임스템프 객체로 변환한다.
     * 
     * <pre>
     * [사용법]
     *  Timestamp timestamp = DateUtil.stringToDate(&quot;2007-04-12&quot;, DateTime.FORMATTED_DATE_PATTERN);
     *  // 결과 timestamp =&gt; 2007-04-12 00:00:00.0
     * </pre>
     * 
     * @param now
     *            날짜 문자열
     * @param pattern
     *            날짜 문자열 포멧
     * @return Timestamp 타임스템프 객체
     */
    public static Timestamp stringToDate(String now, String pattern) {
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        java.util.Date dt = null;

        try {
            dt = sf.parse(now);
        } catch (Exception e) {
            throw new UMLRuntimeException(e);
        }
        return new Timestamp(dt.getTime());
    }

    /**
     * 타임 스템프 객체에 시간을 추가한다.
     * 
     * <pre>
     * [사용법]
     *  Timestamp timestamp = DateUtil.getCurrentTimeStamp();
     *  System.out.println(timestamp);
     *  System.out.println(DateUtil.addHour(timestamp, 2));
     *  // 결과 =&gt; 2007-04-12 15:42:08.614
     *  // 결과 =&gt; 2007-04-12 17:42:08.614
     * </pre>
     * 
     * @param now
     *            타임스템프
     * @param hour
     *            추가시간
     * @return Timestamp 신간 추가된 타임스템프
     */
    public static Timestamp addHour(Timestamp now, int hour) {
        return new Timestamp(now.getTime() + (hour * HOUR));
    }

    /**
     * 타임 스템프 객체에 분을 추가한다.
     * 
     * <pre>
     * [사용법]
     *  Timestamp timestamp = DateUtil.getCurrentTimeStamp();
     *  System.out.println(timestamp);
     *  System.out.println(DateUtil.addMinute(timestamp, 2));
     *  // 결과 =&gt; 2007-04-12 15:44:23.098
     *  // 결과 =&gt; 2007-04-12 15:46:23.098
     * </pre>
     * 
     * @param now
     *            타임 스템프
     * @param minute
     *            추가 분
     * @return Timestamp 분이 추가된 타임스템프
     */
    public static Timestamp addMinute(Timestamp now, int minute) {
        return new Timestamp(now.getTime() + (minute * MINUTE));
    }

    /**
     * 타임 스템프 객체에 초를 추가한다.
     * 
     * <pre>
     * [사용법]
     *  Timestamp timestamp = DateUtil.getCurrentTimeStamp();
     *  System.out.println(timestamp);
     *  System.out.println(DateUtil.addSecond(timestamp, 2));
     *  // 결과 =&gt; 2007-04-12 15:44:23.098
     *  // 결과 =&gt; 2007-04-12 15:44:25.098
     * </pre>
     * 
     * @param now
     *            타임스템프
     * @param second
     *            추가 초
     * @return Timestamp 초가 추가된 타임스템프
     */
    public static Timestamp addSecond(Timestamp now, int second) {
        return new Timestamp(now.getTime() + (second * SECOND));
    }

    /**
     * 입력된 타임스템프에서 1초를 뺀다.
     * 
     * <pre>
     * [사용법]
     *  Timestamp timestamp = DateUtil.getCurrentTimeStamp();
     *  System.out.println(timestamp);
     *  System.out.println(DateUtil.minusOneSecond(timestamp));
     *  // 결과 =&gt; 2007-04-12 15:14:12.138
     *  // 결과 =&gt; 2007-04-12 15:14:11.138
     * </pre>
     * 
     * @param now
     *            타임스템프
     * @return Timestamp 타임스템프 - 1초
     */
    public static Timestamp minusOneSecond(Timestamp now) {
        return new Timestamp(now.getTime() - 1000);
    }

    /**
     * Date 객체를 타임스템프 객체로 변환한다.
     * 
     * <pre>
     * [사용법]
     *  Timestamp timestamp = DateUtil.convertDateToTimestamp(new Date());
     *  System.out.println(timestamp);
     *  // 결과 =&gt; 2007-04-12 15:14:12.138
     * </pre>
     * 
     * @param dt
     *            Date
     * @return Timestamp 타임스템프
     */
    public static Timestamp convertDateToTimestamp(java.util.Date dt) {
        return new Timestamp(dt.getTime());
    }

    /**
     * 타임스템프 객체를 Date 객체로 변환한다.
     * 
     * <pre>
     * [사용법]
     *  Date date = DateUtil.convertTimestampToDate(DateUtil.getCurrentTimeStamp());
     *  System.out.println(date);
     *  // 결과 =&gt; Thu Apr 12 15:48:25 KST 2007
     * </pre>
     * 
     * @param ts
     *            타임스템프
     * @return java.util.Date
     */
    public static java.util.Date convertTimestampToDate(Timestamp ts) {
        return new java.util.Date(ts.getTime());
    }

    /**
     * long형 타임스탬프 객체를 String 객체로 변환한다.
     * 
     * @return String
     */
    public static String convertLongTimeStampToString(long localTimeStamp) {
        Date dateTimeStamp = new Date(localTimeStamp);

        String tempPattern = "yyyy-MM-dd a hh:mm"; //$NON-NLS-1$
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(tempPattern);

        String stringTimeStamp = simpleDateFormat.format(dateTimeStamp);

        return stringTimeStamp;
    }
}
