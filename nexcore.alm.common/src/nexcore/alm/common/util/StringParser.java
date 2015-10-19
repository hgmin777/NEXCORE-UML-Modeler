/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.alm.common.util;

import java.util.StringTokenizer;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.core</li>
 * <li>서브 업무명 : nexcore.tool.core.util</li>
 * <li>설 명 : Key=value 형식의 문자열 처리</li>
 * <li>작성일 : 2007. 04. 02</li>
 * <li>작성자 : SKC&amp;C 기술전략팀 표준 업무 담당자</li>
 * </ul>
 * Copyright ⓒ SK C&C. All Right Reserved.
 * 
 * <b>관련 정보</b> StringParser 클래스는 키=값 문자열의 조합, 분해 및 기타 조작 기능을 제공한다.
 * 
 * 키와 값을 구분하기 위해 Global의 KEY_STR_BEGIN, KEY_STR_EQUAL, KEY_STR_VALUE_END 상수가
 * 정의하고 있는 구분자를 사용한다. KEY_STR_BEGIN 과 KEY_STR_EQUAL 사이의 문자열은 '키'를 의미하고,
 * KEY_STR_EQUAL 와 KEY_STR_VALUE_END 사이 또는 KEY_STR_VALUE_END 과 KEY_STR_VALUE_END
 * 사이의 문자열은 '값'을 의미한다.
 */
public class StringParser {

    /**
     * 복수개의 키=값 형식의 문자열에서 키의 갯수를 구한다.
     * 
     * <pre>
     * int count = StringParser.getKeyCount(&quot;|_key1=value1._value2._|_key2=value3._&quot;);
     * // count =&gt; 2
     * </pre>
     * 
     * @param packKeyStringList
     *            복수개의 키=값 형식의 문자열
     * @return int 키 갯수
     */
    public static int getKeyCount(String packKeyStringList) {
        int count = 0;
        int startPos = 0;
        int pos = 0;

        while (pos >= 0) {
            pos = packKeyStringList.indexOf(StringParserConstant.KEY_STR_BEGIN, startPos);

            if (pos >= 0) {
                count++;
                startPos = pos + StringParserConstant.KEY_STR_BEGIN.length();
            }
        }

        return count;
    }

    /**
     * 하나의 키=값 형식의 문자열에서 값의 갯수를 구한다.
     * 
     * <pre>
     * int count = StringParser.getValueCount(&quot;|_key1=value1._value2._value3._&quot;);
     * // count =&gt; 3
     * </pre>
     * 
     * @param packKeyString
     *            하나의 키=값 형식의 문자열
     * @return int 값 갯수
     */
    public static int getValueCount(String packKeyString) {
        int count = 0;
        int startPos = 0;
        int pos = 0;

        while (pos >= 0) {
            pos = packKeyString.indexOf(StringParserConstant.KEY_STR_VALUE_END, startPos);

            if (pos >= 0) {
                count++;
                startPos = pos + StringParserConstant.KEY_STR_EQUAL.length();
            }
        }

        return count;
    }

    /**
     * 하나의 키=값 문자열에서 키를 구한다.
     * 
     * <pre>
     * String key = StringParser.getKeyId(&quot;|_key1=value1._value2._&quot;);
     * // key =&gt; &quot;key1&quot;
     * </pre>
     * 
     * @param packKeyString
     *            하나의 키=값 형식의 문자열
     * @return String 키
     */
    public static String getKeyId(String packKeyString) {
        int delimeter1Pos = packKeyString.indexOf(StringParserConstant.KEY_STR_BEGIN);
        int delimeter2Pos = packKeyString.indexOf(StringParserConstant.KEY_STR_EQUAL);

        return packKeyString.substring(delimeter1Pos + StringParserConstant.KEY_STR_BEGIN.length(), delimeter2Pos);
    }

    /**
     * 하나의 키=값들 문자열에서 값 배열을 구한다.
     * 
     * <pre>
     * String[] values = StringParser.getValues(&quot;|_key1=value1._value2._value3._&quot;);
     * // values =&gt; {&quot;value1&quot;, &quot;value2&quot;, &quot;value3&quot;}
     * </pre>
     * 
     * @param packKeyString
     *            하나의 키=값 형식의 문자열
     * @return String[] 값 배열
     */
    public static String[] getValues(String packKeyString) {
        if (packKeyString == null) {
            return null;
        }

        int valueCount = getValueCount(packKeyString);

        if (valueCount == 0) {
            return null;
        }

        String[] values = new String[valueCount];

        int startPos = packKeyString.indexOf(StringParserConstant.KEY_STR_EQUAL)
            + StringParserConstant.KEY_STR_EQUAL.length();
        int nextDelimeter3Pos = packKeyString.indexOf(StringParserConstant.KEY_STR_VALUE_END);

        for (int i = 0; i < valueCount; i++) {
            values[i] = packKeyString.substring(startPos, nextDelimeter3Pos);

            startPos = nextDelimeter3Pos + StringParserConstant.KEY_STR_VALUE_END.length();
            nextDelimeter3Pos = packKeyString.indexOf(StringParserConstant.KEY_STR_VALUE_END, startPos);
        }

        return values;
    }

    /**
     * 하나의 키=값 형식의 문자열(&quot;|_key=value1._&quot;)을 생성한다.
     * <ul>
     * <li>(&quot;|_&quot;: StringParserConstant.KEY_STR_BEGIN 에 정의</li>
     * <li>&quot;=&quot;: StringParserConstant.KEY_STR_EQUAL 에 정의</li>
     * <li>&quot;._&quot;: StringParserConstant.KEY_STR_VALUE_END 에 정의</li>
     * </ul>
     * 
     * <pre>
     * String result = StringParser.packKeyString(&quot;key&quot;, &quot;value&quot;);
     * // result =&gt; &quot;|_key=value1._&quot;
     * </pre>
     * 
     * @param key
     *            키
     * @param value
     *            값
     * @return String 키/값 형식의 문자열
     */
    public static String packKeyString(String key, String value) {
        if (key == null || key == "")
            return "key is missing";
        if (value == null || value == "")
            return "value is missing";

        StringBuffer buffer = new StringBuffer();

        buffer.append(StringParserConstant.KEY_STR_BEGIN);
        buffer.append(key);
        buffer.append(StringParserConstant.KEY_STR_EQUAL);
        buffer.append(value);
        buffer.append(StringParserConstant.KEY_STR_VALUE_END);

        return buffer.toString();
    }

    /**
     * 하나의 키=값들 문자열(&quot;|_key=value1._value2._value3._&quot;)을 생성한다.
     * <ul>
     * <li>(&quot;|_&quot;: StringParserConstant.KEY_STR_BEGIN 에 정의</li>
     * <li>&quot;=&quot;: StringParserConstant.KEY_STR_EQUAL 에 정의</li>
     * <li>&quot;._&quot;: StringParserConstant.KEY_STR_VALUE_END 에 정의</li>
     * </ul>
     * 
     * <pre>
     * String[] values = {&quot;value1&quot;, &quot;value2&quot;, &quot;value3&quot;};
     * String result = StringParser.packKeyString(&quot;key&quot;, values);
     * result =&gt; &quot;|_key=value1._value2._value3._&quot;
     * </pre>
     * 
     * @param key
     *            키
     * @param values
     *            값 배열
     * @return String 키/값 형식의 문자열
     */
    public static String packKeyString(String key, String[] values) {
        if (key == null || key == "")
            return "key is missing";
        if (values == null || values.length == 0)
            return "value is missing";

        StringBuffer buffer = new StringBuffer();

        buffer.append(StringParserConstant.KEY_STR_BEGIN);
        buffer.append(key);
        buffer.append(StringParserConstant.KEY_STR_EQUAL);

        for (int i = 0; i < values.length; i++) {
            buffer.append(values[i]);
            buffer.append(StringParserConstant.KEY_STR_VALUE_END);
        }

        return buffer.toString();
    }

    /**
     * 특정 구분자로 연결된 문자열을 입력받아서 해당 문자열을 String[]형태로 리턴한다. 옛날 메소드로 사용을 권장하지는 않는다.
     * 
     * <pre>
     * String sourceStr = &quot;data1/data2/data3/&quot;
     * 
     * String[] strArray = StringParser.unpackStringArray(sourceStr, &quot;/&quot;);
     * // strArray =&gt; {&quot;data1&quot;, &quot;data2&quot;, &quot;data3&quot;}
     * 
     * </pre>
     * 
     * @param sourceStr
     *            분해하고자하는 문자열.
     * @param delimiter
     *            분해 구분자.
     * @return String[] 분해된 문자열 배열.
     */
    public static String[] unpackStringToArray(String sourceStr, String delimiter) {
        StringTokenizer st = new StringTokenizer(sourceStr, delimiter);
        String[] codeList = new String[st.countTokens()];

        int i = 0;
        while (st.hasMoreTokens()) {
            codeList[i++] = st.nextToken();
        }

        return codeList;
    }
}
