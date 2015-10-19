/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.core.regexp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.core.util.StringUtil;

/**
 * 현재는 사용하지 않는 클래스. 차후 구현에 따라 사용될 여지가 있어 제거하지 않고 있음.
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.core.regexp</li>
 * <li>설  명 : RegExpBeanInfo</li>
 * <li>작성일 : 2007. 04. 25</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class RegExpBeanInfo {

    /**
     * RegExpPart를 받아 String 형태의 자동 정규식을 생성한다. </br>
     * 
     * @param regExpPart
     *            정규식 모델값
     * @return String pattern
     */
    public static synchronized String generateRegExp(RegExpPart regExpPart) {
        String result = ""; //$NON-NLS-1$
        String prefixString = ""; //$NON-NLS-1$
        String postfixString = ""; //$NON-NLS-1$
        String termString = ""; //$NON-NLS-1$
        String middleString = ""; //$NON-NLS-1$

        if (RegExpPart.NAMING_TYPE.equals(regExpPart.getType())) {
            StringBuffer sb = new StringBuffer();

            // prefix List
            sb.setLength(0);
            List<String> prefixList = regExpPart.getPrefixList();
            String prefix = ""; //$NON-NLS-1$
            for (int i = 0; i < prefixList.size(); i++) {
                if (i == 0) {
                    sb.append("^("); //$NON-NLS-1$
                }

                prefix = prefixList.get(i);
                sb.append(prefix);
                if (i < (prefixList.size() - 1)) {
                    sb.append("|"); //$NON-NLS-1$
                }

                if (i == (prefixList.size() - 1)) {
                    sb.append(")"); //$NON-NLS-1$
                }
            }
            prefixString += sb.toString();

            // postfix List
            sb.setLength(0);
            List<String> postfixList = regExpPart.getPostfixList();
            String postfix = ""; //$NON-NLS-1$
            for (int i = 0; i < postfixList.size(); i++) {
                if (i == 0) {
                    sb.append("("); //$NON-NLS-1$
                }

                postfix = postfixList.get(i);
                sb.append(postfix);
                if (i < (postfixList.size() - 1)) {
                    sb.append("|"); //$NON-NLS-1$
                }

                if (i == (postfixList.size() - 1)) {
                    sb.append(")$"); //$NON-NLS-1$
                }
            }
            postfixString += sb.toString();

            // term List
            sb.setLength(0);
            List<String> termList = regExpPart.getTermList();
            String term = ""; //$NON-NLS-1$
            for (int i = 0; i < termList.size(); i++) {
                term = termList.get(i);
                sb.append(term);
                if (i < (termList.size() - 1)) {
                    sb.append("|"); //$NON-NLS-1$
                }
            }
            termString += sb.toString();

            if (regExpPart.isAllowNumber()) {
                middleString += "0-9"; //$NON-NLS-1$
            }
            if (regExpPart.getLetterType() == PatternConstants.LETTER_TYPE_LOWERCASE) {
                middleString += "a-z"; //$NON-NLS-1$
            }
            if (regExpPart.getLetterType() == PatternConstants.LETTER_TYPE_UPPERCASE) {
                middleString += "A-Z"; //$NON-NLS-1$
            }
            if (regExpPart.getLetterType() == PatternConstants.LETTER_TYPE_BOTHCASE) {
                middleString += "a-zA-Z"; //$NON-NLS-1$
            }
            if (regExpPart.isAllowKorean()) {
                middleString += ""; //$NON-NLS-1$
            }

            // specialCharList
            sb.setLength(0);
            List<String> specialCharList = regExpPart.getAllowSpecialCharList();
            for (String specialChar : specialCharList) {
                if ("&&".equals(specialChar)) { //$NON-NLS-1$
                    sb.append("&"); //$NON-NLS-1$
                } else if ("-".equals(specialChar)) { //$NON-NLS-1$		// '-' SpecialCharacter 변환
                    sb.append("\\-"); //$NON-NLS-1$
                } else {
                    sb.append(specialChar);
                }
            }
            middleString += sb.toString();
            middleString = "[" + middleString + "]*"; //$NON-NLS-1$ //$NON-NLS-2$

            if (!"".equals(termString)) { //$NON-NLS-1$
                // middleString = "(" + middleString + "|" + termString + ")";
                // //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

                StringBuilder temp = new StringBuilder();
                temp.append(middleString);
                temp.append("("); //$NON-NLS-1$
                temp.append(termString);
                temp.append(")+"); //$NON-NLS-1$
                temp.append(middleString);
                middleString = temp.toString(); // 용어 //$NON-NLS-1$ //$NON-NLS-2$
                // 목록
                // 포함
                // 정규식
                // 수정
                // 2008.07.09
                // 06372
            }

            result = prefixString + middleString + postfixString;
        }

        return result;
    }

    /**
     * 주어진 문자열이 주어진 정규식 패턴과 동일한지 매칭한 후 boolean 결과값을 리턴한다. 동일한 경우 true, 아닌 경우
     * false 리턴.
     * 
     * @param shortName
     *            비교할 문자열
     * @param pattern
     *            비교할 정규식 패턴
     * @return boolean
     */
    public static synchronized boolean checkPattern(String shortName, String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher match = p.matcher(shortName);
        if (match.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 주어진 문자열이 주어진 정규식 패턴과 동일한지 매칭한 후 boolean 결과값을 리턴한다. 유니코드 기반의 복수행 모드 패턴 체킹을
     * 수행한다. 동일한 경우 true, 아닌 경우 false 리턴.
     * 
     * @param shortName
     *            비교할 문자열
     * @param pattern
     *            비교할 정규식 패턴
     * @return boolean
     */
    public static synchronized boolean checkPatternEx(String shortName, String pattern) {
        Pattern p = Pattern.compile(pattern, Pattern.UNICODE_CASE | Pattern.MULTILINE);
        Matcher match = p.matcher(shortName);
        if (match.find()) {
            return true;
        }
        return false;
    }

    /**
     * 여러 행의 주어진 문자열을 읽어 주어진 정규식 패턴과 동일한 지 매칭한 후 boolean 결과값을 리턴한다. 유니코드 기반의 복수행
     * 모드 패턴 체킹을 수행한다. 동일한 경우 true, 아닌 경우 false 리턴
     * 
     * @param source
     *            패턴을 체크할 복수행 문자열
     * @param pattern
     *            비교할 정규식 패턴
     * @return boolean
     */
    public static synchronized boolean checkPatternLineByLine(String source, String pattern) {
        Pattern p = Pattern.compile(pattern, Pattern.UNICODE_CASE | Pattern.MULTILINE);

        Scanner token = new Scanner(source);

        if (token == null) {
            return false;
        }

        String sourceToken;

        Matcher match = null;
        while (token.hasNextLine()) {
            sourceToken = token.nextLine();
            match = p.matcher(sourceToken);
            if (match.find()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 주어진 문자열이 주어진 정규식 패턴과 동일한지 매칭한 후 boolean 결과값을 리턴한다. 동일한 경우 true, 아닌 경우
     * false 리턴.
     * 
     * @param shortName
     *            비교할 문자열
     * @param pattern
     *            비교할 정규식 패턴
     * @param flag
     *            CASE_INSENSITIVE ,MULTILINE ,DOTALL ,UNICODE_CASE ,CANON_EQ
     *            ,UNIX_LINES ,LITERAL , 및 COMMENTS 를 포함할 수 있는 비트 마스크
     */
    public static synchronized boolean checkPattern(String shortName, String pattern, int flag) {
        Pattern p = Pattern.compile(pattern, flag);
        Matcher match = p.matcher(shortName);
        if (match.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 클래스 주석 작성 여부를 체크. 주석이 작성된 경우 true, 아니면 false 리턴
     * 
     * @param comment
     *            주석 문자열
     * @return boolean
     */
    public static synchronized boolean checkCommentPattern(String comment) {
        boolean result = false;

        Pattern p = Pattern.compile("<li>\\s*[^<>]*\\s*</li>"); //$NON-NLS-1$
        Matcher match = p.matcher(comment);
        Pattern commentPattern = Pattern.compile(PatternConstants.COMMENT);
        Matcher commentMatch = null;

        String temp = null;

        while (match.find()) {

            result = true;
            temp = match.group();
            commentMatch = commentPattern.matcher(temp);
            if (!commentMatch.matches()) {
                result = result & false;
            }
        }
        return result;
    }

    /**
     * 문자열 source에서 pattern과 동일한 패턴을 전부 검색하여 List 형태로 리턴한다.
     * 
     * @param source
     *            패턴을 찾을 문자열
     * @param pattern
     *            검색할 정규식 패턴
     * @return List<RegExpParameter>
     */
    public static synchronized List<RegExpParameter> findPatternList(String source, String pattern) {
        List<RegExpParameter> parameters = new ArrayList<RegExpParameter>();
        Pattern p = Pattern.compile(pattern);
        Matcher match = p.matcher(source);

        RegExpParameter regExpParameter = null;

        while (match.find()) {
            regExpParameter = new RegExpParameter();

            if (match.start() >= match.end()) {
                continue;
            }

            regExpParameter.setStartPos(match.start());
            regExpParameter.setEndPos(match.end());
            regExpParameter.setSourceName(source);
            regExpParameter.setMatchString(source.substring(match.start(), match.end()));

            if (!parameters.contains(regExpParameter)) {
                parameters.add(regExpParameter);
            }
        }

        return parameters;
    }

    /**
     * 문자열 source에서 pattern과 동일한 패턴을 전부 검색하여 그 중 첫번째 값만 리턴한다.
     * 
     * @param source
     *            패턴을 찾을 문자열
     * @param pattern
     *            검색할 정규식 패턴
     * @return RegExpParameter
     */
    public static synchronized RegExpParameter findPattern(String source, String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher match = p.matcher(source);
        if (match.find()) {
            RegExpParameter temp = new RegExpParameter(match.start(), match.end());
            temp.setMatchString(source.substring(match.start(), match.end()));
            return temp;
        }
        return null;
    }

    /**
     * file 에서 pattern과 동일한 패턴을 전부 검색하여 List 형태로 리턴한다.
     * 
     * @param file
     *            패턴을 찾을 입력 파일
     * @param pattern
     *            검색할 정규식 패턴
     * @return List<RegExpParameter>
     * @exception java.io.FileNotFoundException
     */
    public static synchronized List<RegExpParameter> findPattern(File file, String pattern) {
        List<RegExpParameter> parameters = new ArrayList<RegExpParameter>();
        int fileSize = (int) file.length();
        FileInputStream stream;
        try {
            stream = new FileInputStream(file);
            parameters = findPattern(stream, fileSize, pattern);
        } catch (FileNotFoundException e) {
            Log.error(e);
        }
        return parameters;
    }

    /**
     * 
     * stream 에서 동일 패턴을 전부 검색하여 List 형태로 리턴한다.
     * 
     * @param stream
     *            패턴을 찾을 스트림
     * @param inputSize
     *            스트림 크기
     * @param pattern
     *            검색할 정규식 패턴
     * @return List<RegExpParameter>
     * @exception java.io.IOException
     */
    public static synchronized List<RegExpParameter> findPattern(InputStream stream, int inputSize, String pattern) {
        byte[] temp = new byte[inputSize];
        String source = new String(temp);
        try {
            stream.read(temp);
        } catch (IOException e) {
            Log.error(e);
        }
        List<RegExpParameter> parameters = new ArrayList<RegExpParameter>();
        Pattern p = Pattern.compile(pattern);
        Matcher match = p.matcher(new String(temp));
        RegExpParameter parameter = null;
        while (match.find()) {
            parameter = new RegExpParameter(match.start(), match.end());
            parameter.setMatchString(source.substring(match.start(), match.end()));
            parameters.add(parameter);
        }

        return parameters;
    }

    /**
     * source에서 기존의 pattern 정규식 패턴들을 replaceString 정규식 패턴으로 모두 변경
     * 
     * @param source
     *            변경할 대상 문자열
     * @param pattern
     *            기존 정규식 패턴
     * @param replaceString
     *            변경할 정규식 패턴
     * @return StringBuffer
     */
    public static synchronized StringBuffer replacePattern(String source, String pattern, String replaceString) {
        Pattern p = Pattern.compile(pattern);
        Matcher match = p.matcher(source);
        StringBuffer newString = new StringBuffer();

        while (match.find()) {
            match.appendReplacement(newString, replaceString);
        }
        match.appendTail(newString);
        return newString;
    }

    /**
     * source에서 기존의 pattern 정규식 패턴을 replaceStr 정규식 패턴으로 index 번째부터 변경
     * 
     * @param source
     *            변경할 대상 문자열
     * @param pattern
     *            기존 정규식 패턴
     * @param replaceString
     *            변경할 정규식 패턴
     * @param index
     *            인덱스
     * @return StringBuffer
     */
    public static synchronized StringBuffer replacePattern(String source, String pattern, String replaceString,
                                                           int index) {
        Pattern p = Pattern.compile(pattern);
        Matcher match = p.matcher(source);
        StringBuffer newString = new StringBuffer();
        int count = 1;

        while (match.find()) {
            if (count == index) {
                match.appendReplacement(newString, replaceString);
                break;
            }

            count++;
        }

        match.appendTail(newString);
        return newString;
    }

    /**
     * 
     * file에서 기존의 pattern 정규식 패턴을 replaceStr 정규식 패턴으로 변경
     * 
     * @param file
     *            변경할 파일 스트림
     * @param pattern
     *            기존 정규식 패턴
     * @param replaceString
     *            변경할 정규식 패턴
     * @return StringBuffer
     */
    public static synchronized StringBuffer replacePattern(File file, String pattern, String replaceString) {

        int fileSize = (int) file.length();
        FileInputStream stream;
        StringBuffer newString = new StringBuffer();
        try {
            stream = new FileInputStream(file);
            newString = replacePattern(stream, fileSize, pattern, replaceString);
        } catch (FileNotFoundException e) {
            Log.error(e);
        }
        return newString;

        // FileReader fr;

        // try {
        // fr = new FileReader(file);
        // try {
        // fr.read(temp);
        // fr.close();
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        // } catch (FileNotFoundException e) {
        //
        // e.printStackTrace();
        // }
        //
        // Pattern p = Pattern.compile(pattern);
        // Matcher match = p.matcher(new String(temp));
        // StringBuffer newString = new StringBuffer();
        //
        // while (match.find()) {
        // match.appendReplacement(newString, replaceString);
        // }
        // match.appendTail(newString);
        // return newString;
    }

    /**
     * 
     * inpustream에서 기존의 pattern 정규식 패턴을 replaceStr 정규식 패턴으로 변경
     * 
     * @param stream
     *            변경할 입력스트림
     * @param inputSize
     *            입력 크기
     * @param pattern
     *            기존 정규식 패턴
     * @param replaceString
     *            변경할 정규식 패턴
     * @return StringBuffer
     */
    public static synchronized StringBuffer replacePattern(InputStream stream, int inputSize, String pattern,
                                                           String replaceString) {
        byte[] temp = new byte[inputSize];
        try {
            stream.read(temp);
        } catch (IOException e) {
            Log.error(e);
        }

        Pattern p = Pattern.compile(pattern);
        Matcher match = p.matcher(new String(temp));
        StringBuffer newString = new StringBuffer();

        while (match.find()) {
            match.appendReplacement(newString, replaceString);
        }
        match.appendTail(newString);
        return newString;
    }

    /**
     * SOURCE 문장 속에 RegExp에서 사용하는 키워드가 있는 경우 앞에 \를 붙여 변환해준다.
     * 
     * @param source
     *            변환 대상 문자열
     * @return String
     */
    public static synchronized String convertRegExpKeyword(String source) {
        String keyword = ""; //$NON-NLS-1$
        String replaceKeyword = ""; //$NON-NLS-1$
        for (int i = 0; i < PatternConstants.REG_EXP_KEYWORD.length; i++) {
            keyword = PatternConstants.REG_EXP_KEYWORD[i];
            replaceKeyword = "\\" + keyword; //$NON-NLS-1$
            source = replaceAll(source, keyword, replaceKeyword, ""); //$NON-NLS-1$
        }

        return source;
    }

    /**
     * input 문자열에 들어있는 oldStr 부분 문자열을 exceptStr 부분 문자열을 제외하고 newStr로 변환한다.
     * 
     * @param input
     *            변환 대상 문자열
     * @param oldStr
     *            변환해야할 소스 부분 문자열
     * @param newStr
     *            변환해야할 목표 부분 문자열
     * @param exceptStr
     *            제외해야할 부분 문자열
     * @return String
     */
    private static String replaceAll(String input, String oldStr, String newStr, String exceptStr) {
        int startIdx = 0;
        int idx = 0;
        int length = oldStr.length();

        StringUtil.verifyStringParameter(input, oldStr);

        StringBuffer sb = new StringBuffer((int) (input.length() * 1.2));
        while ((idx = input.indexOf(oldStr, startIdx)) >= 0) {
            sb.append(input.substring(startIdx, idx));
            sb.append(newStr);
            startIdx = idx + length;
        }

        sb.append(input.substring(startIdx));
        return sb.toString();
    }
}
