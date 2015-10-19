/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.alm.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import nexcore.alm.common.exception.BaseRuntimeException;



/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.core</li>
 * <li>서브 업무명 : nexcore.tool.core.util</li>
 * <li>설 명 : DateTime</li>
 * <li>작성일 : 2007. 04. 12</li>
 * <li>작성자 : SKC&amp;C 기술전략팀 표준 업무 담당자</li>
 * </ul>
 * Copyright ⓒ SK C&amp;C. All Right Reserved.
 * 
 * <pre>
 * 시스템 내에서 유통되는 데이터의 포맷은 Layer 별로 그 형태가 달라질 수 있다. 예로,
 * Presentation Layer와 Business Layer에서의 일자,시간 데이터의 포맷이 서로 다르다.
 * 
 * DataTime 클래스는 일자나 시간 관련 데이터의 포맷을 손쉽게 상호 변환하는 기능, Application 내에서 규정하고 있는 포맷을
 * 잘 따르고 있는지 검사하는 기능 등 다양한 유틸리티를 제공하고 있다. 일자 데이터 포맷 검사 및 변환에 사용하는 패턴은
 * com.skcc.se.app.foundation.global. Global에서 지정한다.
 * </pre> # 일자/시간 패턴
 * <ul>
 * <li> DATE_TIME_PATTERN : &quot;yyyyMMddHHmmss&quot; (일자 시간 패턴 1)</li>
 * <li> FORMATTED_DATE_TIME_PATTERN : &quot;yyyy-MM-dd HH:mm:ss&quot; (일자 시간 패턴
 * 2)</li>
 * <li> DATE_PATTERN : &quot;yyyyMMdd&quot; (일자 패턴 1)</li>
 * <li> FORMATTED_DATE_PATTERN : &quot;yyyy-MM-dd&quot; (일자 패턴 2)</li>
 * <li> TIME_PATTERN : &quot;HHmmss&quot; (시간 패턴 1)</li>
 * <li> FORMATTED_TIME_PATTERN : &quot;HH:mm:ss&quot; (시간 패턴 2)</li>
 * <li> YEAR_MONTH_PATTERN : &quot;yyyyMM&quot; (년월 패턴 1)</li>
 * <li> FORMATTED_YEAR_MONTH_PATTERN : &quot;yyyy-MM&quot; (년월 패턴 2)</li>
 * <li> MONTH_DAY_PATTERN : &quot;MMdd&quot; (월일 패턴 1)</li>
 * <li> FORMATTED_MONTH_DAY_PATTERN : &quot;MM-dd&quot; (월일 패턴 2)</li>
 * <li> YEAR_PATTERN : &quot;yyyy&quot; (년도 패턴)</li>
 * <li> MONTH_PATTERN : &quot;MM&quot; (월 패턴)</li>
 * <li> DAY_PATTERN : &quot;dd&quot; (일 패턴)</li>
 * <li> HOUR_MINUTE_PATTERN : &quot;HHmm&quot; (시분 패턴)</li>
 * <li> MINUTE_SECOND_PATTERN : &quot;mmss&quot; (분초 패턴)</li>
 * <li> HOUR_PATTERN : &quot;HH&quot; (시간 패턴)</li>
 * <li> MINUTE_PATTERN : &quot;mm&quot; (분 패턴)</li>
 * <li> SECOND_PATTERN : &quot;ss&quot; (초 패턴)</li>
 * </ul>
 * 이미 지정되어 있는 패턴 이외에도 새로운 패턴을 지정할 수 있으며,<br>
 * 패턴 지정시 사용하는 일자/시간 패턴 문자들은<br>
 * java.text.SimpleDateFormat의 API를 참조한다.
 */
public class DateTime {

    /** 일자 시간 포맷 1 */
    public static String DATE_TIME_PATTERN            = "yyyyMMddHHmmss"; //$NON-NLS-1$

    /** 일자 시간 포맷 2 */
    public static String FORMATTED_DATE_TIME_PATTERN  = "yyyy-MM-dd HH:mm:ss"; //$NON-NLS-1$
    
    /** 일자 시간 포맷 3 */
    public static String FORMATTED_DATE_TIME_PATTERN_DASH  = "yyyy/MM/dd HH:mm:ss"; //$NON-NLS-1$

    /** 일자 포맷 1 */
    public static String DATE_PATTERN                 = "yyyyMMdd"; //$NON-NLS-1$

    /** 일자 포맷 2 */
    public static String FORMATTED_DATE_PATTERN       = "yyyy-MM-dd"; //$NON-NLS-1$

    /** 시간 포맷 1 */
    public static String TIME_PATTERN                 = "HHmmss"; //$NON-NLS-1$

    /** 시간 포맷 2 */
    public static String FORMATTED_TIME_PATTERN       = "HH:mm:ss"; //$NON-NLS-1$

    /** 년월 포맷 1 */
    public static String YEAR_MONTH_PATTERN           = "yyyyMM"; //$NON-NLS-1$

    /** 년월 포맷 2 */
    public static String FORMATTED_YEAR_MONTH_PATTERN = "yyyy-MM"; //$NON-NLS-1$

    /** 월일 포맷 1 */
    public static String MONTH_DAY_PATTERN            = "MMdd"; //$NON-NLS-1$

    /** 월일 포맷 2 */
    public static String FORMATTED_MONTH_DAY_PATTERN  = "MM-dd"; //$NON-NLS-1$

    /** 년도 포맷 */
    public static String YEAR_PATTERN                 = "yyyy"; //$NON-NLS-1$

    /** 월 포맷 */
    public static String MONTH_PATTERN                = "MM"; //$NON-NLS-1$

    /** 일 포맷 */
    public static String DAY_PATTERN                  = "dd"; //$NON-NLS-1$

    /** 시분 포맷 */
    public static String HOUR_MINUTE_PATTERN          = "HHmm"; //$NON-NLS-1$

    /** 분초 포맷 */
    public static String MINUTE_SECOND_PATTERN        = "mmss"; //$NON-NLS-1$

    /** 시간 포맷 */
    public static String HOUR_PATTERN                 = "HH"; //$NON-NLS-1$

    /** 분 포맷 */
    public static String MINUTE_PATTERN               = "mm"; //$NON-NLS-1$

    /** 초 포맷 */
    public static String SECOND_PATTERN               = "ss"; //$NON-NLS-1$

    /**
     * <pre>
     *  일자 문자열의 공백을 제거한 후, 빈 문자열(&quot;&quot;)인지를 검사한다. 
     *  
     *  [사용법]
     *  boolean empty = DateTime.isEmptyAfterTrim(&quot; 20050921 &quot;);
     *  
     *  //  결과 : empty = false
     * </pre>
     * 
     * @param data
     *            String : 일자 문자열
     * @return boolean : 빈 문자열 여부 (true."" false.!"")
     * @throws BaseRuntimeException :
     *             data가 null 발생
     */
    public static boolean isEmptyAfterTrim(String data) {
        if (data == null)
            throw new BaseRuntimeException("Input is null",DateTime.class.getName());

        try {
            StringUtil.verifyStringParameter(data);
        } catch (BaseRuntimeException e) {
            if(e.getMessage().equals("String 파라메터가 빈 문자열입니다.")){
                return true;
            }
        }
        return (data.trim().equals("")) ? true : false; //$NON-NLS-1$
    }

    /**
     * <pre>
     *  시간 정보의 포맷을 변환한다.
     *  (밀리세컨드 값 -&gt; 'yyyyMMddHHmmss'(Global.DATE_TIME_PATTERN 에 정의))
     *  
     *  [사용법]
     *  long current = 1127265880682L;
     *  String currentDateTime = DateTime.toDateTime(current);  
     *  
     *  // 결과 : currentDateTime = &quot;20050921102440&quot;  
     * </pre>
     * 
     * @param milliseconds
     *            long : 밀리세컨드
     * @return String : 일자 정보 ('yyyyMMddHHmmss')
     */
    public static String toDateTime(long milliseconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);

        return format(calendar.getTime(), DateTime.DATE_TIME_PATTERN);
    }

    /**
     * <pre>
     *  시간 정보의 포맷을 변환한다.
     *  (밀리세컨드 값 -&gt; 'yyyy-MM-dd HH:mm:ss'(DateTime.FORMATTED_DATE_TIME_PATTERN 
     *                                         에 정의))
     *   
     *  [사용법]
     *  long current = 1127265880682L;
     *  String currentFormattedDateTime = DateTime.toFormattedDateTime(current);
     *  
     *  // 결과 : currentDateTime = &quot;2005-09-21 10:24:40&quot;     
     * </pre>
     * 
     * @param milliseconds
     *            long : 밀리세컨드
     * @return String : 일자 정보 ('yyyy-MM-dd HH:mm:ss')
     */
    public static String toFormattedDateTime(long milliseconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);

        return format(calendar.getTime(), DateTime.FORMATTED_DATE_TIME_PATTERN);
    }

    /**
     * <pre>
     *  일자 정보가 'yyyyMMddHHmmss' 형태 (DateTime.DATE_TIME_PATTERN 에 정의)인지를 
     *  검사한다.
     *  
     *  [사용법]
     *  String dateTime = &quot;20050701120000&quot;
     *  boolean isDateTime = DateTime.isDateTime(dateTime); 
     *  
     *  // 결과 : isDateTime = true
     * </pre>
     * 
     * @param dateTime
     *            String : 일자 정보
     * @return boolean : 'yyyyMMddHHmmss' 형태 여부 (true."yyyyMMddHHmmss"
     *         false.!"yyyyMMddHHmmss")
     */
    public static boolean isDateTime(String dateTime) {
        try {
            parse(dateTime, DateTime.DATE_TIME_PATTERN);
            return true;
        } catch (BaseRuntimeException e) {
            return false;
        }
    }

    /**
     * <pre>
     *  일자 정보가 'yyyy-MM-dd HH:mm:ss' 형태 (DateTime.FORMATTED_DATE_TIME_PATTERN 
     *  에 정의)인지를 검사한다.      
     *  
     *  [사용법]
     *  String formattedDateTime = &quot;2005-07-01 12:00:00&quot;
     *  boolean isFormattedDateTime = DateTime.isFormattedDateTime(formattedDateTime); 
     *  
     *  // 결과 : isFormattedDateTime = true
     * </pre>
     * 
     * @param formattedDateTime
     *            String : 일자 정보
     * @return boolean : 'yyyy-MM-dd HH:mm:ss' 형태 여부 (true."yyyy-MM-dd HH:mm:ss"
     *         false.!"yyyy-MM-dd HH:mm:ss")
     */
    public static boolean isFormattedDateTime(String formattedDateTime) {
        try {
            parse(formattedDateTime, DateTime.FORMATTED_DATE_TIME_PATTERN);
            return true;
        } catch (BaseRuntimeException e) {
            return false;
        }
    }

    /**
     * <pre>
     *  년,월,일,시,분,초 값을 'yyyyMMddHHmmss' (DateTime.DATE_TIME_PATTERN 에 정의)
     *  의 형태로 변환한다.
     *  
     *  [사용법]
     *  int year   = 2005;
     *  int month  = 9;
     *  int day    = 1;
     *  int hour   = 12;
     *  int minute = 0;
     *  int second = 0;
     *  String dateTime = 
     *            DateTime.toDateTime(year, month, day, hour, minute, second); 
     *  
     *  // 결과 : dateTime = &quot;20050901120000&quot;
     * </pre>
     * 
     * @param year
     *            int : 년
     * @param month
     *            int : 월
     * @param day
     *            int : 일
     * @param hour
     *            int : 시
     * @param minute
     *            int : 분
     * @param second
     *            int : 초
     * @return String : 일자 정보 ('yyyyMMddHHmmss')
     */
    public static String toDateTime(int year, int month, int day, int hour,
                                    int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day, hour, minute, second);

        return format(calendar.getTime(), DateTime.DATE_TIME_PATTERN);
    }

    /**
     * <pre>
     *  일자 정보의 포맷을 변환한다.
     *  'yyyy-MM-dd HH:mm:ss' (DateTime.FORMATTED_DATE_TIME_PATTERN 에 정의) 
     *    -&gt; 'yyyyMMddHHmmss' (DateTime.DATE_TIME_PATTERN 에 정의)
     *  
     *  [사용법]
     *  String formattedDateTime = &quot;2005-07-01 12:00:00&quot;
     *  String dateTime = DateTime.toDateTime(formattedDateTime);
     *  
     *  // 결과 : dateTime = &quot;20050701120000&quot;
     * </pre>
     * 
     * @param formattedDateTime
     *            String : 일자 정보 ('yyyy-MM-dd HH:mm:ss')
     * @return String : 일자 정보 ('yyyyMMddHHmmss')
     */
    public static String toDateTime(String formattedDateTime) {
        return format(parse(formattedDateTime,
                DateTime.FORMATTED_DATE_TIME_PATTERN),
                DateTime.DATE_TIME_PATTERN);
    }

    /**
     * <pre>
     *  java.util.Date 형을 'yyyyMMddHHmmss' (DateTime.DATE_TIME_PATTERN 에 정의) 의
     *  형태로 변환한다.
     *  
     *  [사용법]
     *  Date current = new Date();
     *  String dateTime = DateTime.toDateTime(current);
     *  
     *  // 결과 : dateTime = &quot;20050928172735&quot;
     * </pre>
     * 
     * @param date
     *            Date : java.util.Date 형 데이터
     * @return String : 일자 정보 ('yyyyMMddHHmmss')
     */
    public static String toDateTime(Date date) {
        return format(date, DateTime.DATE_TIME_PATTERN);
    }

    /**
     * <pre>
     *  현재 시각을 'yyyyMMddHHmmss' (DateTime.DATE_TIME_PATTERN 에 정의) 형태로 구한다.
     *  
     *  [사용법] 
     *  String current = DateTime.getCurrentDateTime();
     *  
     *  // 결과 : current = &quot;20050928172818&quot;
     * </pre>
     * 
     * @return String : 현재 시각 ('yyyyMMddHHmmss')
     */
    public static String getCurrentDateTime() {
        return format(new Date(), DateTime.DATE_TIME_PATTERN);
    }
    
    /**
     * <pre>
     *  현재 시각을 'yyyyMMddHHmmss' (DateTime.FORMATTED_DATE_TIME_PATTERN_T 에 정의) 형태로 구한다.
     *  
     *  [사용법] 
     *  String current = DateTime.getCurrentDateTimeT();
     *  
     *  // 결과 : current = &quot;20050928172818&quot;
     * </pre>
     * 
     * @return String : 현재 시각 ('yyyy-MM-ddTHH:mm:ss')
     */
    public static String getCurrentDateTimeT() {
        return DateTime.getFormattedCurrentDate()+"T"+DateTime.getFormattedCurrentTime(); //$NON-NLS-1$
    }
    
    /**
     * <pre>
     *  현재 시각을 'yyyy/MM/dd HH:mm:ss' (DateTime.FORMATTED_DATE_TIME_PATTERN_DASH 에 정의) 형태로 구한다.
     *  
     *  [사용법] 
     *  String current = DateTime.getCurrentDateTimeDash();
     *  
     *  // 결과 : current = &quot;20050928172818&quot;
     * </pre>
     * 
     * @return String : 현재 시각 ('yyyy/MM/dd/ HH:mm:ss')
     */
    public static String getCurrentDateTimeDash() {
        return format(new Date(), DateTime.FORMATTED_DATE_TIME_PATTERN_DASH);
    }
    

    /**
     * <pre>
     *  년,월,일,시,분,초 값을 'yyyy-MM-dd HH:mm:ss' (DateTime.FORMATTED_DATE_TIME_PATTERN 
     *  에 정의) 형태로 변환한다.
     *  
     *  [사용법]
     *  int year   = 2005;
     *  int month  = 9;
     *  int day    = 1;
     *  int hour   = 12;
     *  int minute = 0;
     *  int second = 0;
     *  String formattedDateTime = 
     *      DateTime.toFormattedDateTime(year, month, day, hour, minute, second); 
     *  
     *  // 결과 : formattedDateTime = &quot;2005-09-01 12:00:00&quot;
     * </pre>
     * 
     * @param year
     *            int : 년
     * @param month
     *            int : 월
     * @param day
     *            int : 일
     * @param hour
     *            int : 시
     * @param minute
     *            int : 분
     * @param second
     *            int : 초
     * @return String : 일자 정보 ('yyyy-MM-dd HH:mm:ss')
     */
    public static String toFormattedDateTime(int year, int month, int day,
                                             int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day, hour, minute, second);

        return format(calendar.getTime(), DateTime.FORMATTED_DATE_TIME_PATTERN);
    }

    /**
     * <pre>
     *  일자 정보의 포맷을 변환한다.
     *  ( 'yyyyMMddHHmmss' (DateTime.DATE_TIME_PATTERN 에 정의)
     *      -&gt; 'yyyy-MM-dd HH:mm:ss' (DateTime.FORMATTED_DATE_TIME_PATTERN 에 정의))
     *  
     *  [사용법]
     *  String dateTime = &quot;20050701120000&quot;
     *  String formattedDateTime = DateTime.toFormattedDateTime(dateTime);
     *  
     *  // 결과 : formattedDateTime = &quot;2005-07-01 12:00:00&quot;
     * </pre>
     * 
     * @param dateTime
     *            String : 일자 정보 ('yyyyMMddHHmmss')
     * @return String : 일자 정보 ('yyyy-MM-dd HH:mm:ss')
     */
    public static String toFormattedDateTime(String dateTime) {
        return format(parse(dateTime, DateTime.DATE_TIME_PATTERN),
                DateTime.FORMATTED_DATE_TIME_PATTERN);
    }

    /**
     * <pre>
     *  java.util.Date 형을 'yyyy-MM-dd HH:mm:ss' (DateTime.FORMATTED_DATE_TIME_PATTERN 
     *  에 정의) 형태로 변환한다.
     *  
     *  [사용법]
     *  Date current = new Date();
     *  String formattedDateTime = DateTime.toFormattedDateTime(current);
     *  
     *  // 결과 : formattedDateTime = &quot;2005-09-28 17:30:36&quot;
     * </pre>
     * 
     * @param date
     *            Date : java.util.Date 형 데이터
     * @return String : 일자 정보 ('yyyy-MM-dd HH:mm:ss')
     */
    public static String toFormattedDateTime(Date date) {
        return format(date, DateTime.FORMATTED_DATE_TIME_PATTERN);
    }

    /**
     * <pre>
     *  현재 시각을 'yyyy-MM-dd HH:mm:ss' (DateTime.FORMATTED_DATE_TIME_PATTERN 에 정의)
     *  형태로 구한다.
     *  
     *  [사용법] 
     *  String current = DateTime.getFormattedCurrentDateTime();
     *  
     *  // 결과 : current = &quot;2005-09-28 17:31:32&quot;
     * </pre>
     * 
     * @return String : 현재 시각 ('yyyy-MM-dd HH:mm:ss')
     */
    public static String getFormattedCurrentDateTime() {
        return format(new Date(), DateTime.FORMATTED_DATE_TIME_PATTERN);
    }

    /**
     * <pre>
     *  일자 정보의 포맷을 변환한다.
     *  ('yyyyMMddHHmmss' (DateTime.DATE_TIME_PATTERN 에 정의)
     *      -&gt; 'yyyyMMdd' (DateTime.DATE_PATTERN 에 정의))
     *  
     *  [사용법] 
     *  String dateTime = &quot;20050701120000&quot;
     *  String date = DateTime.toDateFromDateTime(dateTime);
     *  
     *  // 결과 : current = &quot;20050701&quot;
     * </pre>
     * 
     * @param dateTime
     *            String : 일자 정보 ('yyyyMMddHHmmss')
     * @return String : 일자 정보 ('yyyyMMdd')
     */
    public static String toDateFromDateTime(String dateTime) {
        return format(parse(dateTime, DateTime.DATE_TIME_PATTERN),
                DateTime.DATE_PATTERN);
    }

    /**
     * <pre>
     *  일자 정보의 형태를 변환한다. 
     *  ( 'yyyyMMddHHmmss' (DateTime.DATE_TIME_PATTERN 에 정의)
     *         -&gt; 'HHmmss' (DateTime.TIME_PATTERN 에 정의))
     *  
     *  [사용법] 
     *  String dateTime = &quot;20050701120000&quot;
     *  String time = DateTime.toTimeFromDateTime(dateTime);
     *  
     *  // 결과 : current = &quot;120000&quot;
     * </pre>
     * 
     * @param dateTime
     *            String : 일자 정보 ('yyyyMMddHHmmss')
     * @return String : 시간 정보 ('HHmmss')
     */
    public static String toTimeFromDateTime(String dateTime) {
        return format(parse(dateTime, DateTime.DATE_TIME_PATTERN),
                DateTime.TIME_PATTERN);
    }

    /**
     * <pre>
     *  특정 일자에 년, 월, 일, 시, 분, 초 값을 더한다.
     *  
     *  [사용법] 
     *  String dateTime = &quot;20050701120000&quot;
     *  int year   = 1;
     *  int month  = 1;
     *  int day    = 1;
     *  int hour   = 0;
     *  int minute = 0;
     *  int second = 0;
     *  String result = DateTime.addDateTime(dateTime, year, month, day, hour,
     *                                       minute, second);
     *  
     *  // 결과 : result = &quot;20060802120000&quot;
     * </pre>
     * 
     * @param dateTime
     *            String : 특정 일자 ('yyyyMMddHHmmss')
     * @param year
     *            int : 년
     * @param month
     *            int : 월
     * @param day
     *            int : 일
     * @param hour
     *            int : 시
     * @param minute
     *            int : 분
     * @param second
     *            int : 초
     * @return String : 특정 일자에 parameter 시간 값을 더한 일자 ('yyyyMMddHHmmss')
     */
    public static String addDateTime(String dateTime, int year, int month,
                                     int day, int hour, int minute, int second) {
        Date d = parse(dateTime, DateTime.DATE_TIME_PATTERN);
        GregorianCalendar calendar = new GregorianCalendar();

        calendar.setTime(d);
        calendar.add(Calendar.SECOND, second);
        calendar.add(Calendar.MINUTE, minute);
        calendar.add(Calendar.HOUR_OF_DAY, hour);
        calendar.add(Calendar.DAY_OF_MONTH, day);
        calendar.add(Calendar.MONTH, month);
        calendar.add(Calendar.YEAR, year);

        return format(calendar.getTime(), DateTime.DATE_TIME_PATTERN);
    }

    /**
     * <pre>
     *  특정 일자에서 년, 월, 일, 시, 분, 초 값을 뺀다.
     *  
     *  [사용법] 
     *  String dateTime = &quot;20050701120000&quot;
     *  int year   = 1;
     *  int month  = 1;
     *  int day    = 1;
     *  int hour   = 0;
     *  int minute = 0;
     *  int second = 0;
     *  String result = DateTime.substractDateTime(dateTime, year, month, day,
     *                                             hour, minute, second);
     *  
     *  // 결과 : result = &quot;20040530120000&quot;
     * </pre>
     * 
     * @param dateTime
     *            String : 특정 일자 ('yyyyMMddHHmmss')
     * @param year
     *            int : 년
     * @param month
     *            int : 월
     * @param day
     *            int : 일
     * @param hour
     *            int : 시
     * @param minute
     *            int : 분
     * @param second
     *            int : 초
     * @return String : 특정 일자에서 parameter 시간 값을 뺀 일자 ('yyyyMMddHHmmss')
     */
    public static String substractDateTime(String dateTime, int year,
                                           int month, int day, int hour,
                                           int minute, int second) {
        Date d = parse(dateTime, DateTime.DATE_TIME_PATTERN);
        GregorianCalendar calendar = new GregorianCalendar();

        calendar.setTime(d);
        calendar.add(Calendar.SECOND, (-1 * second));
        calendar.add(Calendar.MINUTE, (-1 * minute));
        calendar.add(Calendar.HOUR_OF_DAY, (-1 * hour));
        calendar.add(Calendar.DAY_OF_MONTH, (-1 * day));
        calendar.add(Calendar.MONTH, (-1 * month));
        calendar.add(Calendar.YEAR, (-1 * year));

        return format(calendar.getTime(), DateTime.DATE_TIME_PATTERN);
    }

    /**
     * <pre>
     *  일자 정보가 'yyyyMMdd' 형태 (DateTime.DATE_PATTERN 에 정의)인지를 검사한다.      
     *  
     *  [사용법]
     *  String date = &quot;20050701&quot;
     *  boolean isDate = DateTime.isDate(date); 
     *  
     *  // 결과 : isDate = true
     * </pre>
     * 
     * @param date
     *            String : 일자 정보
     * @return boolean : 'yyyy-MM-dd' 형태 여부 (true."'yyyyMMdd'"
     *         false.!"'yyyyMMdd'")
     */
    public static boolean isDate(String date) {
        try {
            parse(date, DateTime.DATE_PATTERN);
            return true;
        } catch (BaseRuntimeException e) {
            return false;
        }
    }

    /**
     * <pre>
     *  일자 정보가 'yyyy-MM-dd' 형태 (DateTime.FORMATTED_DATE_PATTERN 에 정의)인지를 
     *  검사한다.      
     *  
     *  [사용법]
     *  String formattedDate = &quot;2005-07-01&quot;
     *  boolean isFormattedDate = DateTime.isFormattedDate(formattedDate); 
     *  
     *  // 결과 : isFormattedDate = true
     * </pre>
     * 
     * @param formattedDate
     *            String : 일자 정보
     * @return boolean : 'yyyy-MM-dd' 형태 여부 (true."'yyyy-MM-dd'"
     *         false.!"'yyyy-MM-dd'")
     */
    public static boolean isFormattedDate(String formattedDate) {
        try {
            parse(formattedDate, DateTime.FORMATTED_DATE_PATTERN);
            return true;
        } catch (BaseRuntimeException e) {
            return false;
        }
    }

    /**
     * <pre>
     *  년,월,일 값을 'yyyyMMdd' (DateTime.DATE_PATTERN 에 정의) 의 형태로 변환한다.
     *  
     *  [사용법]
     *  int year   = 2005;
     *  int month  = 9;
     *  int day    = 1;     
     *  String dateTime = DateTime.toDate(year, month, day); 
     *  
     *  // 결과 : dateTime = &quot;20050901&quot;
     * </pre>
     * 
     * @param year
     *            int : 년
     * @param month
     *            int : 월
     * @param day
     *            int : 일
     * @return String : 일자 정보 ('yyyyMMdd')
     */
    public static String toDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);

        return format(calendar.getTime(), DateTime.DATE_PATTERN);
    }

    /**
     * <pre>
     *  일자 정보의 포맷을 변환한다.
     *  'yyyy-MM-dd' (DateTime.FORMATTED_DATE_PATTERN 에 정의) 
     *    -&gt; 'yyyyMMdd' (DateTime.DATE_PATTERN 에 정의)
     *  
     *  [사용법]
     *  String formattedDate = &quot;2005-07-01&quot;
     *  String date = DateTime.toDate(formattedDate);
     *  
     *  // 결과 : date = &quot;20050701&quot;
     * </pre>
     * 
     * @param formattedDate
     *            String : 일자 정보 ('yyyy-MM-dd')
     * @return String : 일자 정보 ('yyyyMMdd')
     */
    public static String toDate(String formattedDate) {
        return format(parse(formattedDate, DateTime.FORMATTED_DATE_PATTERN),
                DateTime.DATE_PATTERN);
    }

    /**
     * <pre>
     *  현재 시각을 'yyyyMMdd' (DateTime.DATE_PATTERN 에 정의) 형태로 구한다.
     *  
     *  [사용법] 
     *  String current = DateTime.getCurrentDate();
     *  
     *  // 결과 : current = &quot;20050928&quot;
     * </pre>
     * 
     * @return String : 현재 시각 ('yyyyMMdd')
     */
    public static String getCurrentDate() {
        return format(new Date(), DateTime.DATE_PATTERN);
    }

    /**
     * <pre>
     *  [개요]
     *  java.util.Date 형을 'yyyyMMdd' (DateTime.DATE_PATTERN 에 정의) 형태로 변환한다.
     *  
     *  [사용법]
     *  Date current = new Date();
     *  String date = DateTime.toDate(current);
     *  
     *  // 결과 : date = &quot;20050928&quot;
     * </pre>
     * 
     * @param date
     *            Date : java.util.Date 형 데이터
     * @return String : 일자 정보 ('yyyyMMdd')
     */
    public static String toDate(Date date) {
        return format(date, DateTime.DATE_PATTERN);
    }

    /**
     * <pre>
     *  년,월,일 값을 'yyyy-MM-dd' (DateTime.FORMATTED_DATE_PATTERN 에 정의) 
     *  형태로 변환한다.
     *  
     *  [사용법]
     *  int year   = 2005;
     *  int month  = 9;
     *  int day    = 1;
     *  String date = DateTime.toFormattedDate(year, month, day);
     *  
     *  // 결과 : date = &quot;2005-09-28&quot;
     * </pre>
     * 
     * @param year
     *            int : 년
     * @param month
     *            int : 월
     * @param day
     *            int : 일
     * @return String : 일자 정보 ('yyyy-MM-dd')
     */
    public static String toFormattedDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);

        return format(calendar.getTime(), DateTime.FORMATTED_DATE_PATTERN);
    }

    /**
     * <pre>
     *  일자 정보의 포맷을 변환한다.
     *  ( 'yyyyMMdd' (DateTime.DATE_PATTERN 에 정의)
     *      -&gt; 'yyyy-MM-dd' (DateTime.FORMATTED_DATE_PATTERN 에 정의))
     *  
     *  [사용법]
     *  String date = &quot;20050701&quot;
     *  String formattedDate = DateTime.toFormattedDate(date);
     *  
     *  // 결과 : formattedDate = &quot;2005-07-01&quot;
     * </pre>
     * 
     * @param date
     *            String : 일자 정보 ('yyyyMMdd')
     * @return String : 일자 정보 ('yyyy-MM-dd')
     */
    public static String toFormattedDate(String date) {
        return format(parse(date, DateTime.DATE_PATTERN),
                DateTime.FORMATTED_DATE_PATTERN);
    }

    /**
     * <pre>
     *  일자 정보의 포맷을 변환한다.
     *  ( 'yyyy-MM-dd' (DateTime.FORMATTED_DATE_PATTERN 에 정의)
     *      -&gt; 'yyyyMMdd' (DateTime.DATE_PATTERN 에 정의))
     *  
     *  [사용법]
     *  String formattedDate = &quot;2005-07-01&quot;
     *  String date = DateTime.toUnformattedDate(formattedDate);
     *  
     *  // 결과 : date = &quot;20050701&quot;
     * </pre>
     * 
     * @param date
     *            String : 일자 정보 ('yyyy-MM-dd')
     * @return String : 일자 정보 ('yyyyMMdd')
     */
    public static String toUnformattedDate(String date) {
        StringUtil.verifyStringParameter(date);

        return date.replaceAll("[^0-9]", ""); //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * <pre>
     *  java.util.Date 형을 'yyyy-MM-dd' (DateTime.FORMATTED_DATE_PATTERN 에 정의) 
     *  형태로 변환한다.
     *  
     *  [사용법]
     *  Date current = new Date();
     *  String formattedDate = DateTime.toFormattedDate(current);
     *  
     *  // 결과 : formattedDate = &quot;2005-09-28&quot;
     * </pre>
     * 
     * @param date
     *            Date : java.util.Date 형 데이터
     * @return String : 일자 정보 ('yyyy-MM-dd')
     */
    public static String toFormattedDate(Date date) {
        return format(date, DateTime.FORMATTED_DATE_PATTERN);
    }

    /**
     * <pre>
     *  현재 시각을 'yyyy-MM-dd' (DateTime.FORMATTED_DATE_PATTERN 에 정의) 형태로 구한다.
     *  
     *  [사용법] 
     *  String current = DateTime.getFormattedCurrentDate();
     *  
     *  // 결과 : current = &quot;2005-09-28&quot;
     * </pre>
     * 
     * @return String : 현재 시각 ('yyyy-MM-dd')
     */
    public static String getFormattedCurrentDate() {
        return format(new Date(), DateTime.FORMATTED_DATE_PATTERN);
    }

    /**
     * <pre>
     *  현재 시각을 원하는 포맷으로 구한다.
     *  
     *  [사용법] 
     *  String pattern = &quot;yyyy/MM/dd&quot;
     *  String current = DateTime.getFormattedCurrentTime(pattern);
     *  
     *  // 결과 : current = &quot;2005/09/28&quot;
     * </pre>
     * 
     * @param pattern
     *            String : 원하는 포맷
     * @return String : 현재 시각 (원하는 포맷)
     */
    public static String getFormattedCurrentTime(String pattern) {
        return format(new Date(), pattern);
    }

    /**
     * <pre>
     *  일자 정보에서 '년월' 을 구한다.
     *  ( 'yyyyMMdd' (DateTime.DATE_PATTERN 에 정의)
     *      -&gt; 'yyyyMM' (DateTime.YEAR_MONTH_PATTERN 에 정의))
     *  
     *  [사용법] 
     *  String date = &quot;20050901&quot;
     *  String yearMonth = DateTime.getYearMonth(date);
     *  
     *  // 결과 : yearMonth = &quot;200509&quot;
     * </pre>
     * 
     * @param date
     *            String : 일자 정보 ('yyyyMMdd')
     * @return String : 년월 정보 ('yyyyMM')
     */
    public static String getYearMonth(String date) {
        return format(parse(date, DateTime.DATE_PATTERN),
                DateTime.YEAR_MONTH_PATTERN);
    }

    /**
     * <pre>
     *  일자 정보에서 '월일' 을 구한다.
     *  ( 'yyyyMMdd' (DateTime.DATE_PATTERN 에 정의)
     *      -&gt; 'MMdd' (DateTime.MONTH_DAY_PATTERN 에 정의))
     *  
     *  [사용법] 
     *  String date = &quot;20050901&quot;
     *  String monthDay = DateTime.getMonthDay(date);
     *  
     *  // 결과 : monthDay = &quot;0901&quot; 
     * </pre>
     * 
     * @param date
     *            String : 일자 정보 ('yyyyMMdd')
     * @return String : 월일 정보 ('MMdd')
     */
    public static String getMonthDay(String date) {
        return format(parse(date, DateTime.DATE_PATTERN),
                DateTime.MONTH_DAY_PATTERN);

    }

    /**
     * <pre>
     *  일자 정보에서 '연도'를 구한다.
     *  ( 'yyyyMMdd' (DateTime.DATE_PATTERN 에 정의)
     *      -&gt; 'yyyy' (DateTime.YEAR_PATTERN 에 정의))
     *  
     *  [사용법] 
     *  String date = &quot;20050901&quot;
     *  String year = DateTime.getYear(date);
     *  
     *  // 결과 : year = &quot;2005&quot; 
     * </pre>
     * 
     * @param date
     *            String : 일자 정보 ('yyyyMMdd')
     * @return String : 연도 정보 ('yyyy')
     */
    public static String getYear(String date) {
        return format(parse(date, DateTime.DATE_PATTERN), DateTime.YEAR_PATTERN);
    }

    /**
     * <pre>
     *  일자 정보에서 '월'을 구한다.
     *  ( 'yyyyMMdd' (DateTime.DATE_PATTERN 에 정의)
     *      -&gt; 'MM' (DateTime.MONTH_PATTERN 에 정의))
     *  
     *  [사용법] 
     *  String date = &quot;20050901&quot;
     *  String month = DateTime.getMonth(date);
     *  
     *  // 결과 : month = &quot;09&quot; 
     * </pre>
     * 
     * @param date
     *            String : 일자 정보 ('yyyyMMdd')
     * @return String : 월 정보 ('MM')
     */
    public static String getMonth(String date) {
        return format(parse(date, DateTime.DATE_PATTERN),
                DateTime.MONTH_PATTERN);
    }

    /**
     * <pre>
     *  일자 정보에서 '일'을 구한다.
     *  ( 'yyyyMMdd' (DateTime.DATE_PATTERN 에 정의)
     *      -&gt; 'dd' (DateTime.DAY_PATTERN 에 정의))
     *  
     *  [사용법] 
     *  String date = &quot;20050901&quot;
     *  String day = DateTime.getDay(date);
     *  
     *  // 결과 : day = &quot;01&quot; 
     * </pre>
     * 
     * @param date
     *            String : 일자 정보 ('yyyyMMdd')
     * @return String : 일 정보 ('dd')
     */
    public static String getDay(String date) {
        return format(parse(date, DateTime.DATE_PATTERN), DateTime.DAY_PATTERN);
    }

    /**
     * <pre>
     *  일자 정보에서 '연도' (정수형)를 구한다.
     *  
     *  [사용법] 
     *  String date = &quot;20050901&quot;
     *  int year = DateTime.getYearInt(date);
     *  
     *  // 결과 : year = 2005 
     * </pre>
     * 
     * @param date
     *            String : 일자 정보 ('yyyyMMdd')
     * @return int : '년'
     */
    public static int getYearInt(String date) {
        Date d = parse(date, DateTime.DATE_PATTERN);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);

        return calendar.get(Calendar.YEAR);

    }

    /**
     * <pre>
     *  일자 정보에서 '월' (정수형)를 구한다.
     *  
     *  [사용법] 
     *  String date = &quot;20050901&quot;
     *  int month = DateTime.getMonthInt(date);
     *  
     *  // 결과 : month = 9
     * </pre>
     * 
     * @param date
     *            String : 일자 정보 ('yyyyMMdd')
     * @return int : '월'
     */
    public static int getMonthInt(String date) {
        Date d = parse(date, DateTime.DATE_PATTERN);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);

        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * <pre>
     *  일자 정보에서 '일' (정수형)를 구한다.
     *  
     *  [사용법] 
     *  String date = &quot;20050901&quot;
     *  int day = DateTime.getDayInt(date);
     *  
     *  // 결과 : day = 1
     * </pre>
     * 
     * @param date
     *            String : 일자 정보 ('yyyyMMdd')
     * @return int : '일'
     */
    public static int getDayInt(String date) {
        Date d = parse(date, DateTime.DATE_PATTERN);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);

        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * <pre>
     *  특정 일자가 속해 있는 주간(week)에서 몇 번째 날인지를 구한다. (일요일이 첫 번째임)
     *  
     *  [사용법] 
     *  String date = &quot;20050901&quot;
     *  int dayOfWeek = DateTime.getDayOfWeek(date);
     *  
     *  // 결과 : dayOfWeek = 5
     * </pre>
     * 
     * @param date
     *            String : 일자 정보 ('yyyyMMdd')
     * @return int : 순서
     */
    public static int getDayOfWeek(String date) {
        Date d = parse(date, DateTime.DATE_PATTERN);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);

        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * <pre>
     *  특정 일자가 속해 있는 해(year)에서 몇 번째 날인지를 구한다. 
     *  
     *  [사용법] 
     *  String date = &quot;20050901&quot;
     *  int dayOfYear = DateTime.getDayOfYear(date);
     *  
     *  // 결과 : dayOfYear = 244
     * </pre>
     * 
     * @param date
     *            String : 일자 정보 ('yyyyMMdd')
     * @return int : 순서
     */
    public static int getDayOfYear(String date) {
        Date d = parse(date, DateTime.DATE_PATTERN);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);

        return calendar.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * <pre>
     *  특정 일자가 속해 있는 달(month)에서 몇 번째 주(week)인지를 구한다.
     *  
     *  [사용법] 
     *  String date = &quot;20050901&quot;
     *  int weekOfMonth = DateTime.getWeekOfMonth(date);
     *  
     *  // 결과 : weekOfMonth = 1
     * </pre>
     * 
     * @param date
     *            String : 일자 정보 ('yyyyMMdd')
     * @return int : 순서
     */
    public static int getWeekOfMonth(String date) {
        Date d = parse(date, DateTime.DATE_PATTERN);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);

        return calendar.get(Calendar.WEEK_OF_MONTH);
    }

    /**
     * <pre>
     *  특정 일자가 속해 있는 해(year)에서 몇 번째 주(week)인지를 구한다.
     *  
     *  [사용법] 
     *  String date = &quot;20050901&quot;
     *  int weekOfYear = DateTime.getWeekOfYear(date);
     *  
     *  // 결과 : weekOfMonth = 36
     * </pre>
     * 
     * @param date
     *            String : 일자 정보 ('yyyyMMdd')
     * @return int : 순서
     */
    public static int getWeekOfYear(String date) {
        Date d = parse(date, DateTime.DATE_PATTERN);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);

        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * <pre>
     *  특정 일자가 속해 있는 달(month)에서 첫째 날의 일자 정보 ('yyyyMMdd' 
     *  (DateTime.DATE_PATTERN 에 정의))를 구한다.
     *  
     *  [사용법] 
     *  String date = &quot;20050901&quot;
     *  String firstDate = DateTime.getFirstDate(date);
     *  
     *  // 결과 : firstDate = &quot;20050901&quot;
     * </pre>
     * 
     * @param date
     *            String : 일자 정보 ('yyyyMMdd')
     * @return String : 첫째 날의 일자 정보 ('yyyyMMdd')
     */
    public static String getFirstDate(String date) {
        Date d = parse(date, DateTime.DATE_PATTERN);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;

        return toDate(year, month, 1);
    }

    /**
     * <pre>
     *  특정 일자가 속해 있는 달(month)에서 첫째 날의 일자 정보 ('yyyy-MM-dd' 
     *  (DateTime.FORMATTED_DATE_PATTERN 에 정의))를 구한다.
     *  
     *  [사용법] 
     *  String date = &quot;20050901&quot;
     *  String formattedFirstDate = DateTime.getFormattedFirstDate(date);
     *  
     *  // 결과 : firstDate = &quot;2005-09-01&quot;
     * </pre>
     * 
     * @param date
     *            String : 일자 정보 ('yyyyMMdd')
     * @return String : 첫째 날의 일자 정보 ('yyyy-MM-dd')
     */
    public static String getFormattedFirstDate(String date) {
        Date d = parse(date, DateTime.DATE_PATTERN);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;

        return toFormattedDate(year, month, 1);
    }

    /**
     * <pre>
     *  특정 일자가 속해 있는 달(month)에서 마지막 날의 일자 정보 ('yyyyMMdd' 
     *  (DateTime.DATE_PATTERN 에 정의))를 구한다.
     *  
     *  [사용법] 
     *  String date = &quot;20050901&quot;
     *  String lastDate = DateTime.getLastDate(date);
     *  
     *  // 결과 : lastDate = &quot;2005-09-30&quot;
     * </pre>
     * 
     * @param date
     *            String : 일자 정보 ('yyyyMMdd')
     * @return String : 마지막 날의 일자 정보 ('yyyyMMdd')
     */
    public static String getLastDate(String date) {
        Date d = parse(date, DateTime.DATE_PATTERN);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = getLastDay(year, month);

        return toDate(year, month, day);
    }

    /**
     * <pre>
     *  특정 일자가 속해 있는 달(month)에서 마지막 날의 일자 정보 ('yyyy-MM-dd' 
     *  (DateTime.FORMATTED_DATE_PATTERN 에 정의))를 구한다.
     *  
     *  [사용법] 
     *  String date = &quot;20050901&quot;
     *  String formattedLastDate = DateTime.getFormattedLastDate(date);
     *  
     *  // 결과 : formattedLastDate = &quot;2005-09-30&quot;
     * </pre>
     * 
     * @param date
     *            String : 일자 정보 ('yyyyMMdd')
     * @return String : 마지막 날의 일자 정보 ('yyyy-MM-dd')
     */
    public static String getFormattedLastDate(String date) {
        Date d = parse(date, DateTime.DATE_PATTERN);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = getLastDay(year, month);

        return toFormattedDate(year, month, day);
    }

    /**
     * <pre>
     *  특정 일자에 년, 월, 일 값을 더한다.
     *  
     *  [사용법] 
     *  String date = &quot;20050701&quot;
     *  int year   = 1;
     *  int month  = 1;
     *  int day    = 1;
     *  String result = DateTime.addDate(date, year, month, day);
     *  
     *  // 결과 : result = &quot;20060802&quot;
     * </pre>
     * 
     * @param date
     *            String : 특정 일자 ('yyyyMMdd')
     * @param year
     *            int : 년
     * @param month
     *            int : 월
     * @param day
     *            int : 일
     * @return String : 특정 일자에 parameter 시간 값을 더한 일자 ('yyyyMMdd')
     */
    public static String addDate(String date, int year, int month, int day) {
        Date d = parse(date, DateTime.DATE_PATTERN);
        GregorianCalendar calendar = new GregorianCalendar();

        calendar.setTime(d);
        calendar.add(Calendar.DAY_OF_MONTH, day);
        calendar.add(Calendar.MONTH, month);
        calendar.add(Calendar.YEAR, year);

        return format(calendar.getTime(), DateTime.DATE_PATTERN);
    }

    /**
     * <pre>
     *  특정 일자에서 년, 월, 일 값을 뺀다.
     *  
     *  [사용법] 
     *  String date = &quot;20050701&quot;
     *  int year   = 1;
     *  int month  = 1;
     *  int day    = 1;
     *  String result = DateTime.subtractDate(date, year, month, day);
     *  
     *  // 결과 : result = &quot;20040530&quot;
     * </pre>
     * 
     * @param date
     *            String : 특정 일자 ('yyyyMMdd')
     * @param year
     *            int : 년
     * @param month
     *            int : 월
     * @param day
     *            int : 일
     * @return String : 특정 일자에서 parameter 시간 값을 뺀 일자 ('yyyyMMdd')
     */
    public static String subtractDate(String date, int year, int month, int day) {
        Date d = parse(date, DateTime.DATE_PATTERN);
        GregorianCalendar calendar = new GregorianCalendar();

        calendar.setTime(d);
        calendar.add(Calendar.DAY_OF_MONTH, (-1 * day));
        calendar.add(Calendar.MONTH, (-1 * month));
        calendar.add(Calendar.YEAR, (-1 * year));

        return format(calendar.getTime(), DateTime.DATE_PATTERN);
    }

    /**
     * <pre>
     *  두 일자간의 년 수를 구한다.
     *  
     *  [사용법] 
     *  String oneDate = &quot;20050701&quot;
     *  String anotherDate = &quot;20060701&quot;
     *  long years = DateTime.getYearsBetweenDates(oneDate, anotherDate);
     *  
     *  // 결과 : years = 1
     * </pre>
     * 
     * @param oneDate
     *            String : 특정 일자 ('yyyyMMdd')
     * @param anotherDate
     *            String : 특정 일자 ('yyyyMMdd')
     * @return long : 두 일자 간의 년 수
     */
    public static long getYearsBetweenDates(String oneDate, String anotherDate) {
        Date one = parse(oneDate, DateTime.DATE_PATTERN);
        Date another = parse(anotherDate, DateTime.DATE_PATTERN);

        long milliseconds = Math.abs(one.getTime() - another.getTime());
        long years = milliseconds / 1000 / 60 / 60 / 24 / 365;

        return years;
    }

    /**
     * <pre>
     *  두 일자간의 월 수를 구한다.
     *  
     *  [사용법] 
     *  String oneDate = &quot;20050701&quot;
     *  String anotherDate = &quot;20060701&quot;
     *  long months = DateTime.getMonthsBetweenDates(oneDate, anotherDate);
     *  
     *  // 결과 : months = 12
     * </pre>
     * 
     * @param oneDate
     *            String : 특정 일자 ('yyyyMMdd')
     * @param anotherDate
     *            String : 특정 일자 ('yyyyMMdd')
     * @return long : 두 일자 간의 월 수
     */
    public static long getMonthsBetweenDates(String oneDate, String anotherDate) {
        Date one = parse(oneDate, DateTime.DATE_PATTERN);
        Date another = parse(anotherDate, DateTime.DATE_PATTERN);

        Calendar oneCalendar = Calendar.getInstance();
        oneCalendar.setTime(one);

        Calendar anotherCalendar = Calendar.getInstance();
        anotherCalendar.setTime(another);

        if (anotherCalendar.before(oneCalendar)) {
            oneCalendar.setTime(another);
            anotherCalendar.setTime(one);

            String temp = oneDate;
            oneDate = anotherDate;
            anotherDate = temp;
        }

        String tempDate = oneDate;
        Date temp = null;
        Calendar tempCalendar = null;

        long months = 0L;

        do {
            tempDate = addDate(tempDate, 0, 1, 0);

            temp = parse(tempDate, DateTime.DATE_PATTERN);

            tempCalendar = Calendar.getInstance();
            tempCalendar.setTime(temp);

            months++;
        } while (!anotherCalendar.before(tempCalendar));

        return months - 1;
    }

    /**
     * <pre>
     *  두 일자간의 일 수를 구한다.
     *  
     *  [사용법] 
     *  String oneDate = &quot;20050701&quot;
     *  String anotherDate = &quot;20060701&quot;
     *  long days = DateTime.getDaysBetweenDates(oneDate, anotherDate);
     *  
     *  // 결과 : days = 365
     * </pre>
     * 
     * @param oneDate
     *            String : 특정 일자 ('yyyyMMdd')
     * @param anotherDate
     *            String : 특정 일자 ('yyyyMMdd')
     * @return long : 두 일자 간의 일 수
     */
    public static long getDaysBetweenDates(String oneDate, String anotherDate) {
        Date one = parse(oneDate, DateTime.DATE_PATTERN);
        Date another = parse(anotherDate, DateTime.DATE_PATTERN);

        long milliseconds = Math.abs(one.getTime() - another.getTime());
        long days = (milliseconds / (1000 * 60 * 60 * 24));

        return days;
    }

    /**
     * <pre>
     *  시간 정보가 'HHmmss' 형태 (DateTime.TIME_PATTERN 에 정의)인지를 검사한다.      
     *  
     *  [사용법]
     *  String time = &quot;120000&quot;
     *  boolean isTime = DateTime.isTime(time); 
     *  
     *  // 결과 : isTime = true
     * </pre>
     * 
     * @param time
     *            String : 시간 정보
     * @return boolean : 'HHmmss' 형태 여부 (true.'HHmmss' false.!'HHmmss')
     */
    public static boolean isTime(String time) {
        try {
            parse(time, DateTime.TIME_PATTERN);
            return true;
        } catch (BaseRuntimeException e) {
            return false;
        }
    }

    /**
     * <pre>
     *  시간 정보가 'HH:mm:ss' 형태 (DateTime.FORMATTED_TIME_PATTERN 에 정의)인지를 
     *  검사한다.      
     *  
     *  [사용법]
     *  String formattedTime = &quot;12:00:00&quot;
     *  boolean isFormattedTime = DateTime.isFormattedTime(formattedTime); 
     *  
     *  // 결과 : isFormattedTime = true
     * </pre>
     * 
     * @param formattedTime
     *            String : 시간 정보
     * @return boolean : 'HH:mm:ss' 형태 여부 (true.'HH:mm:ss' false.!'HH:mm:ss')
     */
    public static boolean isFormattedTime(String formattedTime) {
        try {
            parse(formattedTime, DateTime.FORMATTED_TIME_PATTERN);
            return true;
        } catch (BaseRuntimeException e) {
            return false;
        }
    }

    /**
     * <pre>
     *  시,분,초 값을 'HHmmss' (DateTime.TIME_PATTERN 에 정의) 의 형태로 변환한다.
     *  
     *  [사용법]
     *  int hour   = 12;
     *  int minute = 0;
     *  int second = 0;
     *  String time = DateTime.toTime(hour, minute, second); 
     *  
     *  // 결과 : time = &quot;120000&quot;
     * </pre>
     * 
     * @param hour
     *            int : 시
     * @param minute
     *            int : 분
     * @param second
     *            int : 초
     * @return String : 시간 정보 ('HHmmss')
     */
    public static String toTime(int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);

        return format(calendar.getTime(), DateTime.TIME_PATTERN);
    }

    /**
     * <pre>
     *  시간 정보의 포맷을 변환한다.
     *  'HH:mm:ss' (DateTime.FORMATTED_TIME_PATTERN 에 정의) 
     *    -&gt; 'HHmmss' (DateTime.TIME_PATTERN 에 정의)
     *  
     *  [사용법]
     *  String formattedTime = &quot;12:00:00&quot;
     *  String time = DateTime.toTime(formattedTime);
     *  
     *  // 결과 : time = &quot;120000&quot;
     * </pre>
     * 
     * @param formattedTime
     *            String : 시간 정보 ('HH:mm:ss')
     * @return String : 시간 정보 ('HHmmss')
     */
    public static String toTime(String formattedTime) {
        return format(parse(formattedTime, DateTime.FORMATTED_TIME_PATTERN),
                DateTime.TIME_PATTERN);
    }

    /**
     * <pre>
     *  java.util.Date 형을 'HHmmss' (DateTime.TIME_PATTERN 에 정의) 의 형태로 변환한다.
     *  
     *  [사용법]
     *  Date current = new Date();
     *  String time = DateTime.toTime(current);
     *  
     *  // 결과 : time = &quot;175346&quot;
     * </pre>
     * 
     * @param date
     *            Date : java.util.Date 형 데이터
     * @return String : 시간 정보 ('HHmmss')
     */
    public static String toTime(Date date) {
        return format(date, DateTime.TIME_PATTERN);
    }

    /**
     * <pre>
     *  현재 시각을 'HHmmss' (DateTime.TIME_PATTERN 에 정의) 형태로 구한다.
     *  
     *  [사용법] 
     *  String current = DateTime.getCurrentTime();
     *  
     *  // 결과 : current = &quot;175346&quot;
     * </pre>
     * 
     * @return String : 현재 시각 ('HHmmss')
     */
    public static String getCurrentTime() {
        return format(new Date(), DateTime.TIME_PATTERN);
    }

    /**
     * <pre>
     *  시,분,초 값을 'HH:mm:ss' (DateTime.FORMATTED_TIME_PATTERN 에 정의) 형태로 
     *  변환한다.
     *  
     *  [사용법]
     *  int hour   = 12;
     *  int minute = 0;
     *  int second = 0;
     *  String formattedTime = DateTime.toFormattedTime(hour, minute, second); 
     *  
     *  // 결과 : formattedTime = &quot;12:00:00&quot;
     * </pre>
     * 
     * @param hour
     *            int : 시
     * @param minute
     *            int : 분
     * @param second
     *            int : 초
     * @return String : 시간 정보 ('HH:mm:ss')
     */
    public static String toFormattedTime(int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);

        return format(calendar.getTime(), DateTime.FORMATTED_TIME_PATTERN);
    }

    /**
     * <pre>
     *  시간 정보의 포맷을 변환한다.
     *  ( 'HHmmss' (DateTime.TIME_PATTERN 에 정의)
     *      -&gt; 'HH:mm:ss' (DateTime.FORMATTED_TIME_PATTERN 에 정의))
     *  
     *  [사용법]
     *  String time = &quot;120000&quot;
     *  String formattedTime = DateTime.toFormattedTime(time);
     *  
     *  // 결과 : formattedTime = &quot;12:00:00&quot;
     * </pre>
     * 
     * @param time
     *            String : 시간 정보 ('HHmmss')
     * @return String : 시간 정보 ('HH:mm:ss')
     */
    public static String toFormattedTime(String time) {
        return format(parse(time, DateTime.TIME_PATTERN),
                DateTime.FORMATTED_TIME_PATTERN);
    }

    /**
     * <pre>
     *  java.util.Date 형을 'HH:mm:ss' (DateTime.FORMATTED_TIME_PATTERN 에 정의) 
     *  형태로 변환한다.
     *  
     *  [사용법]
     *  Date current = new Date();
     *  String formattedTime = DateTime.toFormattedTime(current);
     *  
     *  // 결과 : formattedTime = &quot;17:55:03&quot;
     * </pre>
     * 
     * @param date
     *            Date : java.util.Date 형 데이터
     * @return String : 시간 정보 ('HH:mm:ss')
     */
    public static String toFormattedTime(Date date) {
        return format(date, DateTime.FORMATTED_TIME_PATTERN);
    }

    /**
     * <pre>
     *  현재 시각을 'HH:mm:ss' (DateTime.FORMATTED_TIME_PATTERN 에 정의) 형태로 구한다.
     *  
     *  [사용법] 
     *  String current = DateTime.getFormattedCurrentTime();
     *  
     *  // 결과 : current = &quot;17:55:03&quot;
     * </pre>
     * 
     * @return String : 현재 시각 ('HH:mm:ss')
     */
    public static String getFormattedCurrentTime() {
        return format(new Date(), DateTime.FORMATTED_TIME_PATTERN);
    }

    /**
     * <pre>
     *  시간 정보에서 '시분' 을 구한다.
     *  ( 'HHmmss' (DateTime.TIME_PATTERN 에 정의)
     *      -&gt; 'HHmm' (DateTime.HOUR_MINUTE_PATTERN 에 정의))
     *  
     *  [사용법] 
     *  String time = &quot;120000&quot;
     *  String hourMinute = DateTime.getHourMinute(time);
     *  
     *  // 결과 : hourMinute = &quot;1200&quot;
     * </pre>
     * 
     * @param time
     *            String : 시간 정보 ('HHmmss')
     * @return String : 시분 정보 ('HHmm')
     */
    public static String getHourMinute(String time) {
        return format(parse(time, DateTime.TIME_PATTERN),
                DateTime.HOUR_MINUTE_PATTERN);
    }

    /**
     * <pre>
     *  시간 정보에서 '분초' 를 구한다.
     *  ( 'HHmmss' (DateTime.TIME_PATTERN 에 정의)
     *      -&gt; 'mmss' (DateTime.MINUTE_SECOND_PATTERN 에 정의))
     *  
     *  [사용법] 
     *  String time = &quot;120000&quot;
     *  String minuteSecond = DateTime.getMinuteSecond(time);
     *  
     *  // 결과 : minuteSecond = &quot;0000&quot;
     * </pre>
     * 
     * @param time
     *            String : 시간 정보 ('HHmmss')
     * @return String : 분초 정보 ('mmss')
     */
    public static String getMinuteSecond(String time) {
        return format(parse(time, DateTime.TIME_PATTERN),
                DateTime.MINUTE_SECOND_PATTERN);
    }

    /**
     * <pre>
     *  시간 정보에서 '시' 를 구한다.
     *  ( 'HHmmss' (DateTime.TIME_PATTERN 에 정의)
     *      -&gt; 'HH' (DateTime.HOUR_PATTERN 에 정의))
     *  
     *  [사용법] 
     *  String time = &quot;120000&quot;
     *  String hour = DateTime.getHour(time);
     *  
     *  // 결과 : hour = &quot;12&quot;
     * </pre>
     * 
     * @param time
     *            String : 시간 정보 ('HHmmss')
     * @return String : 시 정보 ('HH')
     */
    public static String getHour(String time) {
        return format(parse(time, DateTime.TIME_PATTERN), DateTime.HOUR_PATTERN);
    }

    /**
     * <pre>
     *  시간 정보에서 '분' 을 구한다.
     *  ( 'HHmmss' (DateTime.TIME_PATTERN 에 정의)
     *      -&gt; 'mm' (DateTime.MINUTE_PATTERN 에 정의))
     *  
     *  [사용법] 
     *  String time = &quot;120000&quot;
     *  String minute = DateTime.getMinute(time);
     *  
     *  // 결과 : minute = &quot;00&quot;
     * </pre>
     * 
     * @param time
     *            String : 시간 정보 ('HHmmss')
     * @return String : 분 정보 ('mm')
     */
    public static String getMinute(String time) {
        return format(parse(time, DateTime.TIME_PATTERN),
                DateTime.MINUTE_PATTERN);
    }

    /**
     * <pre>
     *  시간 정보에서 '초' 을 구한다.
     *  ( 'HHmmss' (DateTime.TIME_PATTERN 에 정의)
     *      -&gt; 'ss' (DateTime.SECOND_PATTERN 에 정의))
     *  
     *  [사용법] 
     *  String time = &quot;120000&quot;
     *  String second = DateTime.getSecond(time);
     *  
     *  // 결과 : second = &quot;00&quot;
     * </pre>
     * 
     * @param time
     *            String : 시간 정보 ('HHmmss')
     * @return String : 초 정보 ('ss')
     */
    public static String getSecond(String time) {
        return format(parse(time, DateTime.TIME_PATTERN),
                DateTime.SECOND_PATTERN);
    }

    /**
     * <pre>
     *  시간 정보에서 '시' (정수형)를 구한다.
     *  
     *  [사용법] 
     *  String time = &quot;120000&quot;
     *  int hour = DateTime.getHourInt(time);
     *  
     *  // 결과 : hour = 12
     * </pre>
     * 
     * @param time
     *            String : 시간 정보 ('HHmmss')
     * @return int : '시'
     */
    public static int getHourInt(String time) {
        Date d = parse(time, DateTime.TIME_PATTERN);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);

        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * <pre>
     *  시간 정보에서 '분' (정수형)를 구한다.
     *  
     *  [사용법] 
     *  String time = &quot;120000&quot;
     *  int minute = DateTime.getMinuteInt(time);
     *  
     *  // 결과 : minute = 0
     * </pre>
     * 
     * @param time
     *            String : 시간 정보 ('HHmmss')
     * @return int : '분'
     */
    public static int getMinuteInt(String time) {
        Date d = parse(time, DateTime.TIME_PATTERN);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);

        return calendar.get(Calendar.MINUTE);
    }

    /**
     * <pre>
     *  시간 정보에서 '초' (정수형)를 구한다.
     *  
     *  [사용법] 
     *  String time = &quot;120000&quot;
     *  int second = DateTime.getSecondInt(time);
     *  
     *  // 결과 : second = 0
     * </pre>
     * 
     * @param time
     *            String : 시간 정보 ('HHmmss')
     * @return int : '초'
     */
    public static int getSecondInt(String time) {
        Date d = parse(time, DateTime.TIME_PATTERN);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);

        return calendar.get(Calendar.SECOND);
    }

    /**
     * <pre>
     *  특정 시간이 하루 중(day)에서 몇 초(second)인지를 구한다.
     *  
     *  [사용법] 
     *  String time = &quot;120000&quot;
     *  int secondOfDay = DateTime.getSecondOfDay(time);
     *  
     *  // 결과 : secondOfDay = 43200
     * </pre>
     * 
     * @param time
     *            String : 일자 정보 ('HHmmss')
     * @return int : 초(seconds)
     */
    public static int getSecondOfDay(String time) {
        Date d = parse(time, DateTime.TIME_PATTERN);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        return ((hour * 60 * 60) + (minute * 60) + second);
    }

    /**
     * <pre>
     *  특정 시간이 하루 중(day)에서 몇 분(minute)인지를 구한다.
     *  
     *  [사용법] 
     *  String time = &quot;120000&quot;
     *  int minuteOfDay = DateTime.getMinuteOfDay(time);
     *  
     *  // 결과 : minuteOfDay = 720
     * </pre>
     * 
     * @param time
     *            String : 일자 정보 ('HHmmss')
     * @return int : 분(minutes)
     */
    public static int getMinuteOfDay(String time) {
        Date d = parse(time, DateTime.TIME_PATTERN);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        return ((hour * 60) + minute);
    }

    /**
     * <pre>
     *  특정 시간에 시, 분, 초 값을 더한다.
     *  
     *  [사용법] 
     *  String time = &quot;120000&quot;
     *  int hour   = 1;
     *  int minute = 1;
     *  int second = 1;
     *  String result = DateTime.addTime(time, hour, minute, second);
     *  
     *  // 결과 : result = &quot;130101&quot;
     * </pre>
     * 
     * @param time
     *            String : 특정 시간 ('HHmmss')
     * @param hour
     *            int : 시
     * @param minute
     *            int : 분
     * @param second
     *            int : 초
     * @return String : 특정 시간에 parameter 시간 값을 더한 시간 ('HHmmss')
     */
    public static String addTime(String time, int hour, int minute, int second) {
        Date d = parse(time, DateTime.TIME_PATTERN);

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(d);
        calendar.add(Calendar.SECOND, second);
        calendar.add(Calendar.MINUTE, minute);
        calendar.add(Calendar.HOUR_OF_DAY, hour);

        return format(calendar.getTime(), DateTime.TIME_PATTERN);
    }

    /**
     * <pre>
     *  특정 시간에 시, 분, 초 값을 뺀다.
     *  
     *  [사용법] 
     *  String time = &quot;120000&quot;
     *  int hour   = 1;
     *  int minute = 1;
     *  int second = 1;
     *  String result = DateTime.subtractTime(time, hour, minute, second);
     *  
     *  // 결과 : result = &quot;105859&quot; 
     * </pre>
     * 
     * @param time
     *            String : 특정 시간 ('HHmmss')
     * @param hour
     *            int : 시
     * @param minute
     *            int : 분
     * @param second
     *            int : 초
     * @return String : 특정 시간에 parameter 시간 값을 뺀 시간 ('HHmmss')
     */
    public static String subtractTime(String time, int hour, int minute,
                                      int second) {
        Date d = parse(time, DateTime.TIME_PATTERN);

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(d);
        calendar.add(Calendar.SECOND, (-1 * second));
        calendar.add(Calendar.MINUTE, (-1 * minute));
        calendar.add(Calendar.HOUR_OF_DAY, (-1 * hour));

        return format(calendar.getTime(), DateTime.TIME_PATTERN);
    }

    /**
     * <pre>
     *  두 시간 정보 간의 시간을 구한다.
     *  
     *  [사용법] 
     *  String oneTime = &quot;120000&quot;
     *  String anotherTime = &quot;140000&quot;
     *  long hours = DateTime.getHoursBetweenTimes(oneTime, anotherTime);
     *  
     *  // 결과 : hours = 2
     * </pre>
     * 
     * @param oneTime
     *            String : 특정 시간 ('HHmmss')
     * @param anotherTime
     *            String : 특정 시간 ('HHmmss')
     * @return long : 시간
     */
    public static long getHoursBetweenTimes(String oneTime, String anotherTime) {
        Date one = parse(oneTime, DateTime.TIME_PATTERN);
        Date another = parse(anotherTime, DateTime.TIME_PATTERN);

        long milliseconds = Math.abs(one.getTime() - another.getTime());
        long hours = milliseconds / (1000 * 60 * 60);

        return hours;
    }

    /**
     * <pre>
     *  두 시간 정보 간의 분을 구한다.
     *  
     *  [사용법] 
     *  String oneTime = &quot;120000&quot;
     *  String anotherTime = &quot;140000&quot;
     *  long minutes = DateTime.getMinutesBetweenTimes(oneTime, anotherTime);
     *  
     *  // 결과 : minutes = 120
     * </pre>
     * 
     * @param oneTime
     *            String : 특정 시간 ('HHmmss')
     * @param anotherTime
     *            String : 특정 시간 ('HHmmss')
     * @return long : 분
     */
    public static long getMinutesBetweenTimes(String oneTime, String anotherTime) {
        Date one = parse(oneTime, DateTime.TIME_PATTERN);
        Date another = parse(anotherTime, DateTime.TIME_PATTERN);

        long milliseconds = Math.abs(one.getTime() - another.getTime());
        long minutes = milliseconds / (1000 * 60);

        return minutes;
    }

    /**
     * <pre>
     *  두 시간 정보 간의 초를 구한다.
     *  
     *  [사용법] 
     *  String oneTime = &quot;120000&quot;
     *  String anotherTime = &quot;140000&quot;
     *  long seconds = DateTime.getSecondsBetweenTimes(oneTime, anotherTime);
     *  
     *  // 결과 : seconds = 7200
     * </pre>
     * 
     * @param oneTime
     *            String : 특정 시간 ('HHmmss')
     * @param anotherTime
     *            String : 특정 시간 ('HHmmss')
     * @return long : 초
     */
    public static long getSecondsBetweenTimes(String oneTime, String anotherTime) {
        Date one = parse(oneTime, DateTime.TIME_PATTERN);
        Date another = parse(anotherTime, DateTime.TIME_PATTERN);

        long milliseconds = Math.abs(one.getTime() - another.getTime());
        long seconds = milliseconds / 1000;

        return seconds;
    }

    /**
     * <pre>
     *  년월 정보가 'yyyyMM' 형태 (DateTime.YEAR_MONTH_PATTERN 에 정의)인지를 검사한다.      
     *  
     *  [사용법]
     *  String yearMonth = &quot;200507&quot;
     *  boolean isYearMonth = DateTime.isYearMonth(yearMonth); 
     *  
     *  // 결과 : isYearMonth = true
     * </pre>
     * 
     * @param yearMonth
     *            String : 년월 정보
     * @return boolean : 'yyyyMM' 형태 여부(true.'yyyyMM' false.!'yyyyMM')
     */
    public static boolean isYearMonth(String yearMonth) {
        try {
            parse(yearMonth, DateTime.YEAR_MONTH_PATTERN);
            return true;
        } catch (BaseRuntimeException e) {
            return false;
        }
    }

    /**
     * <pre>
     *  년월 정보가 'yyyy-MM' 형태 (DateTime.FORMATTED_YEAR_MONTH_PATTERN 에 정의)인지를 
     *  검사한다.      
     *  
     *  [사용법]
     *  String formattedYearMonth = &quot;2005-07&quot;
     *  boolean isFormattedYearMonth = 
     *      DateTime.isFormattedYearMonth(formattedYearMonth); 
     *  
     *  // 결과 : isFormattedYearMonth = true
     * </pre>
     * 
     * @param formattedYearMonth
     *            String : 년월 정보
     * @return boolean : 'yyyy-MM' 형태 여부 (true.'yyyy-MM' false.!'yyyy-MM')
     */
    public static boolean isFormattedYearMonth(String formattedYearMonth) {
        try {
            parse(formattedYearMonth, DateTime.FORMATTED_YEAR_MONTH_PATTERN);
            return true;
        } catch (BaseRuntimeException e) {
            return false;
        }
    }

    /**
     * <pre>
     *  현재 시각을 'yyyyMM' (DateTime.YEAR_MONTH_PATTERN 에 정의) 형태로 구한다.
     *  
     *  [사용법] 
     *  String current = DateTime.getCurrentYearMonth();
     *  
     *  // 결과 : current = &quot;200509&quot;
     * </pre>
     * 
     * @return String : 현재 시각 ('yyyyMM')
     */
    public static String getCurrentYearMonth() {
        return format(new Date(), DateTime.YEAR_MONTH_PATTERN);
    }

    /**
     * <pre>
     *  현재 시각을 'yyyy-MM' (DateTime.FORMATTED_YEAR_MONTH_PATTERN 에 정의) 형태로 
     *  구한다.
     *  
     *  [사용법] 
     *  String current = DateTime.getFormattedCurrentYearMonth();
     *  
     *  // 결과 : current = &quot;2005-09&quot;
     * </pre>
     * 
     * @return String : 현재 시각 ('yyyy-MM')
     */
    public static String getFormattedCurrentYearMonth() {
        return format(new Date(), DateTime.FORMATTED_YEAR_MONTH_PATTERN);
    }

    /**
     * <pre>
     *  년,월 값을 'yyyyMM' (DateTime.YEAR_MONTH_PATTERN 에 정의) 의 형태로 변환한다.
     *  
     *  [사용법]
     *  int year   = 2005;
     *  int month  = 9;
     *  String yearMonth = DateTime.toYearMonth(year, month); 
     *  
     *  // 결과 : yearMonth = &quot;200509&quot;
     * </pre>
     * 
     * @param year
     *            int : 년
     * @param month
     *            int : 월
     * @return String : 년월 정보 ('yyyyMM')
     */
    public static String toYearMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);

        return format(calendar.getTime(), DateTime.YEAR_MONTH_PATTERN);
    }

    /**
     * <pre>
     *  년월 정보의 포맷을 변환한다.
     *  'yyyy-MM' (DateTime.FORMATTED_YEAR_MONTH_PATTERN 에 정의) 
     *    -&gt; 'yyyyMM' (DateTime.YEAR_MONTH_PATTERN 에 정의)
     *  
     *  [사용법]
     *  String formattedYearMonth = &quot;2005-07&quot;
     *  String yearMonth = DateTime.toYearMonth(formattedYearMonth);
     *  
     *  // 결과 : yearMonth = &quot;200507&quot;
     * </pre>
     * 
     * @param formattedYearMonth
     *            String : 년월 정보 ('yyyy-MM')
     * @return String : 년월 정보 ('yyyyMM')
     */
    public static String toYearMonth(String formattedYearMonth) {
        return format(parse(formattedYearMonth,
                DateTime.FORMATTED_YEAR_MONTH_PATTERN),
                DateTime.YEAR_MONTH_PATTERN);
    }

    /**
     * <pre>
     *  java.util.Date 형을 'yyyyMM' (DateTime.YEAR_MONTH_PATTERN 에 정의) 의
     *  형태로 변환한다.
     *  
     *  [사용법]
     *  Date current = new Date();
     *  String dateTime = DateTime.toYearMonth(current);
     *  
     *  // 결과 : dateTime = &quot;200509&quot;
     * </pre>
     * 
     * @param date
     *            Date : java.util.Date 형 데이터
     * @return String : 년월 정보 ('yyyyMM')
     */
    public static String toYearMonth(Date date) {
        return format(date, DateTime.YEAR_MONTH_PATTERN);
    }

    /**
     * <pre>
     *  년,월 값을 'yyyy-MM' (DateTime.FORMATTED_YEAR_MONTH_PATTERN 에 정의) 형태로 
     *  변환한다.
     *  
     *  [사용법]
     *  int year   = 2005;
     *  int month  = 9;
     *  String formattedYearMonth = 
     *      DateTime.toFormattedYearMonth(year, month); 
     *  
     *  // 결과 : formattedYearMonth = &quot;2005-09&quot;
     * </pre>
     * 
     * @param year
     *            int : 년
     * @param month
     *            int : 월
     * @return String : 년월 정보 ('yyyy-MM')
     */
    public static String toFormattedYearMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);

        return format(calendar.getTime(), DateTime.FORMATTED_YEAR_MONTH_PATTERN);
    }

    /**
     * <pre>
     *  년월 정보의 포맷을 변환한다.
     *  ( 'yyyyMM' (DateTime.YEAR_MONTH_PATTERN 에 정의)
     *      -&gt; 'yyyy-MM' (DateTime.FORMATTED_YEAR_MONTH_PATTERN 에 정의))
     *  
     *  [사용법]
     *  String yearMonth = &quot;200507&quot;
     *  String formattedYearMonth = DateTime.toFormattedYearMonth(yearMonth);
     *  
     *  // 결과 : formattedYearMonth = &quot;2005-07&quot;
     * </pre>
     * 
     * @param yearMonth
     *            String : 년월 정보 ('yyyyMM')
     * @return String : 년월 정보 ('yyyy-MM')
     */
    public static String toFormattedYearMonth(String yearMonth) {
        return format(parse(yearMonth, DateTime.YEAR_MONTH_PATTERN),
                DateTime.FORMATTED_YEAR_MONTH_PATTERN);
    }

    /**
     * <pre>
     *  java.util.Date 형을 'yyyy-MM' (DateTime.FORMATTED_YEAR_MONTH_PATTERN 에 정의) 
     *  형태로 변환한다.
     *  
     *  [사용법]
     *  Date current = new Date();
     *  String formattedYearMonth = DateTime.toFormattedYearMonth(current);
     *  
     *  // 결과 : formattedYearMonth = &quot;2005-09&quot;
     * </pre>
     * 
     * @param date
     *            Date : java.util.Date 형 데이터
     * @return String : 년월 정보 ('yyyy-MM')
     */
    public static String toFormattedYearMonth(Date date) {
        return format(date, DateTime.FORMATTED_YEAR_MONTH_PATTERN);
    }

    /**
     * <pre>
     *  특정 년월에 년, 월 값을 더한다.
     *  
     *  [사용법] 
     *  String yearMonth = &quot;200507&quot;
     *  int year   = 1;
     *  int month  = 1;
     *  String result = DateTime.addYearMonth(yearMonth, year, month);
     *  
     *  // 결과 : result = &quot;200608&quot;
     * </pre>
     * 
     * @param yearMonth
     *            String : 특정 년월 ('yyyyMM')
     * @param year
     *            int : 년
     * @param month
     *            int : 월
     * @return String : 특정 년월에 parameter 시간 값을 더한 년월 정보 ('yyyyMM')
     */
    public static String addYearMonth(String yearMonth, int year, int month) {
        Date d = parse(yearMonth, DateTime.YEAR_MONTH_PATTERN);
        GregorianCalendar calendar = new GregorianCalendar();

        calendar.setTime(d);
        calendar.add(Calendar.MONTH, month);
        calendar.add(Calendar.YEAR, year);

        return format(calendar.getTime(), DateTime.YEAR_MONTH_PATTERN);
    }

    /**
     * <pre>
     *  두 년월 정보 간의 년 수를 구한다.
     *  
     *  [사용법] 
     *  String oneYearMonth = &quot;200507&quot;
     *  String anotherYearMonth = &quot;200607&quot;
     *  long years = 
     *      DateTime.getYearsBetweenYearMonths(oneYearMonth, anotherYearMonth);
     *  
     *  // 결과 : years = 1
     * </pre>
     * 
     * @param oneYearMonth
     *            String : 특정 년월 ('yyyyMM')
     * @param anotherYearMonth
     *            String : 특정 년월 ('yyyyMM')
     * @return long : 두 년월 정보 간의 년 수
     */
    public static long getYearsBetweenYearMonths(String oneYearMonth,
                                                 String anotherYearMonth) {
        Date one = parse(oneYearMonth, DateTime.YEAR_MONTH_PATTERN);
        Date another = parse(anotherYearMonth, DateTime.YEAR_MONTH_PATTERN);

        long milliseconds = Math.abs(one.getTime() - another.getTime());
        long years = milliseconds / 1000 / 60 / 60 / 24 / 365;

        return years;
    }

    /**
     * <pre>
     *  두 년월 정보 간의 월 수를 구한다.
     *  
     *  [사용법] 
     *  String oneYearMonth = &quot;200507&quot;
     *  String anotherYearMonth = &quot;200607&quot;
     *  long months = 
     *      DateTime.getMonthsBetweenYearMonths(oneYearMonth, anotherYearMonth);
     *  
     *  // 결과 : months = 12
     * </pre>
     * 
     * @param oneYearMonth
     *            String : 특정 년월 ('yyyyMM')
     * @param anotherYearMonth
     *            String : 특정 년월 ('yyyyMM')
     * @return long : 두 년월 정보 간의 월 수
     */
    public static long getMonthsBetweenYearMonths(String oneYearMonth,
                                                  String anotherYearMonth) {
        Date one = parse(oneYearMonth, DateTime.YEAR_MONTH_PATTERN);
        Date another = parse(anotherYearMonth, DateTime.YEAR_MONTH_PATTERN);

        Calendar oneCalendar = Calendar.getInstance();
        oneCalendar.setTime(one);

        Calendar anotherCalendar = Calendar.getInstance();
        anotherCalendar.setTime(another);

        if (anotherCalendar.before(oneCalendar)) {
            oneCalendar.setTime(another);
            anotherCalendar.setTime(one);

            String temp = oneYearMonth;
            oneYearMonth = anotherYearMonth;
            anotherYearMonth = temp;
        }

        String tempYearMonth = oneYearMonth;
        Date temp = null;
        Calendar tempCalendar = null;

        long months = 0L;

        do {
            tempYearMonth = addYearMonth(tempYearMonth, 0, 1);

            temp = parse(tempYearMonth, DateTime.YEAR_MONTH_PATTERN);

            tempCalendar = Calendar.getInstance();
            tempCalendar.setTime(temp);

            months++;
        } while (!anotherCalendar.before(tempCalendar));

        return months - 1;
    }

    /**
     * <pre>
     *  특정 년월의  마지막 날짜를 구한다.
     * </pre>
     * 
     * @param year
     *            int : 년
     * @param month
     *            int : 월
     * @return int : 마지막 날짜
     */
    private static int getLastDay(int year, int month) {
        int[] daysOfMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

        if (new GregorianCalendar().isLeapYear(year)) {
            daysOfMonth[1] = 29;
        }

        return daysOfMonth[month - 1];
    }

    /**
     * <pre>
     *  java.util.Date 형을 원하는 형태의 문자열로 포맷을 변환한다.
     *  
     *  [사용법] 
     *  String pattern = &quot;yyyyMMddHHmmss&quot;
     *  String result = DateTime.format(new Date(), pattern);
     *  
     *  // 결과 : result = &quot;20050928180557&quot;
     * </pre>
     * 
     * @param date
     *            Date : java.util.Date 형의 일자 정보
     * @param pattern
     *            String : 포맷 변환에 적용할 패턴
     * @return String : 포맷 변환된 일자/시간 정보
     * @throws BaseRuntimeException :
     *             date 또는 pattern이 null인 경우 발생
     */
    public static String format(Date date, String pattern) {
        StringUtil.verifyObjectParameter(date);
        StringUtil.verifyStringParameter(pattern);

        return new SimpleDateFormat(pattern).format(date).toString();
    }

    /**
     * <pre>
     *  특정 포맷을 갖는 일자/시간 정보를 java.util.Date 형으로 변환한다.
     *  
     *  [사용법] 
     *  String pattern = &quot;yyyyMMddHHmmss&quot;
     *  Date date = DateTime.parse(&quot;20050520174455&quot;, pattern);
     *  
     *  // 결과 : date.toString() : Fri May 20 17:44:55 KST 2005
     * </pre>
     * 
     * @param date
     *            String : 일자/시간 정보
     * @param pattern
     *            String : 일자/시간 정보의 포맷
     * @return Date : java.util.Date 형
     * @throws BaseRuntimeException :
     *             date또는 pattern이 null인 경우, date의 포맷이 pattern과 다른 경우, 파싱하는 동안
     *             ParseException이 발생한 경우
     */
    public static Date parse(String date, String pattern) {
        StringUtil.verifyStringParameter(date, pattern);
        
        if (date.length() != pattern.length()) {
            throw new BaseRuntimeException("DateTime_DATE_PARSE_EXCEPTION_E", DateTime.class.getName()); //$NON-NLS-1$
        }

        try {
            Date d = new SimpleDateFormat(pattern).parse(date);
            return d;
        } catch (ParseException e) {
            throw new BaseRuntimeException(e, "DateTime_DATE_PARSE_EXCEPTION_E", DateTime.class.getName()); //$NON-NLS-1$
        }
    }

    /**
     * <pre>
     *  월일 정보가 'MMdd' 형태 (DateTime.MONTH_DAY_PATTERN 에 정의)인지를 검사한다.      
     *  
     *  [사용법]
     *  String monthDay = &quot;0701&quot;
     *  boolean isMonthDay = DateTime.isMonthDay(monthDay); 
     *  
     *  // 결과 : isMonthDay =  true
     * </pre>
     * 
     * @param monthDay
     *            String : 월일 정보
     * @return boolean : 'MMdd' 형태 여부(true.'MMdd' false.!'MMdd')
     */
    public static boolean isMonthDay(String monthDay) {
        try {
            parse(monthDay, DateTime.MONTH_DAY_PATTERN);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * <pre>
     *  월일 정보가 'MM-dd' 형태 (DateTime.FORMATTED_MONTH_DAY_PATTERN 에 정의)인지를 검사한다.      
     *  
     *  [사용법]
     *  String formattedMonthDay = &quot;07-01&quot;
     *  boolean isFormattedMonthDay = 
     *      DateTime.isFormattedMonthDay(formattedMonthDay); 
     *  
     *  // 결과 : isFormattedMonthDay =  true
     * </pre>
     * 
     * @param formattedMonthDay
     *            String : 월일 정보
     * @return boolean : 'MM-dd' 형태 여부 (true.'MM-dd' false.!'MM-dd')
     */
    public static boolean isFormattedMonthDay(String formattedMonthDay) {
        try {
            parse(formattedMonthDay, DateTime.FORMATTED_MONTH_DAY_PATTERN);
            return true;
        } catch (BaseRuntimeException e) {
            return false;
        }
    }

    /**
     * <pre>
     *  특정 일자의 요일 심볼을 타입에 따라 구한다. 
     *  타입 : 0.영문약자 1.영문 2.한국어약자 3.한국어
     *  
     *  [사용법]
     *  String date = &quot;20050701&quot;
     *  String day = DateTime.getDaySymbol(date, 0); 
     *  
     *  // 결과 : day = &quot;Fri&quot;
     * </pre>
     * 
     * @param date
     *            String : 일자 정보 ('yyyyMMdd')
     * @param type
     *            int : 요일 심볼 타입 (개요 참조)
     * @return String : 요일 심볼
     * @throws BaseRuntimeException :
     *             type이 0~3을 벗어난 경우
     */
    public static String getDaySymbol(String date, int type) {
        String[][] day = {
                { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" }, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
                { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
                        "Friday", "Saturday" }, //$NON-NLS-1$ //$NON-NLS-2$
                { Messages.getString("DateTime.sun"), Messages.getString("DateTime.mon"), Messages.getString("DateTime.tue"), Messages.getString("DateTime.wed"), Messages.getString("DateTime.thu"), Messages.getString("DateTime.fri"), Messages.getString("DateTime.sat") }, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
                { Messages.getString("DateTime.sunday"), Messages.getString("DateTime.monday"), Messages.getString("DateTime.tuesday"), Messages.getString("DateTime.wednesday"), Messages.getString("DateTime.thursday"), Messages.getString("DateTime.friday"), Messages.getString("DateTime.saturday") } }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$

        try {
            return day[type][getDayOfWeek(date) - 1];
        } catch (IndexOutOfBoundsException e) {
            throw new BaseRuntimeException(e, "DateTime_NONE_DAY_SYMBOL_E", DateTime.class.getName()); //$NON-NLS-1$
        }
    }

    /**
     * <pre>
     *  현재 시각을 밀리세컨드 형으로 구한다.
     *  
     *  [사용법]
     *  long current = DateTime.getCurrentTimeMillis();  
     *  
     *  // 결과 : current = 1127898800610 
     * </pre>
     * 
     * @return long : 현재 시각 (밀리세컨드)
     */
    public static long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }
}
