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

import nexcore.tool.uml.core.exception.UMLRuntimeException;
import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.core.util.StringUtil;

/**
 * RegExp 클래스는 정규식을 생성 및 처리하는데 활용된다. 모든 메소드들이 static 이므로 객체를 생성하지 않도록 한다. <br/>
 * 주로 입력된 문자열이 특정 패턴인지를 검사하거나 RegExpPart 모델이 설정한 대로 정규식을 생성하거나 문자열을 특정 패턴에 맞게
 * 변경한다.
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.core.regexp</li>
 * <li>설  명 : RegExp</li>
 * <li>작성일 : 2007. 04. 25</li>
 * <li>작성자 : 06046 </li>
 * </ul>
 */
public class RegExp {

    /**
     * 직접적으로 객체 생성을 방지 하기위하여 생성자 억세서를 변경 수정자: 한승일 수정일: 2009.11.10
     */
    protected RegExp() {

    }

    /**
     * RegExpPart를 받아 String 형태의 자동 정규식을 생성한다. <br/>
     * 정규식에 포함되는 내용은 미리 정의된 접두어, 접미어, 특정 용어 및 0-9, a-z, a-zA-Z, [가-힣]+, 특수 문자가
     * 있다. RegExpPart 객체에서 각각의 허용 여부 및 리스트를 세팅하면 해당 항목을 결과 정규식에 추가한다. 호출은 다음과 같이
     * 이루어진다.
     * 
     * <p>
     * String pattern = RegExp.generateRegExp(RegExpPart);
     * </p>
     * 
     * @param regExpPart
     *            정규식 생성을 위한 정규식 모델 객체
     * @return String prefixString + middleString+ postfixString 형태의 문자열을 리턴한다.
     *         <ul>
     *         <li>prefixString: 접두어</li>
     *         <li>postfixString: 접미어</li>
     *         <li>middleString: 용어</li>
     *         <li>middleString: 숫자 허용</li>
     *         <li>middleString: 대소문자 허용</li>
     *         <li>middleString: 한글 허용</li>
     *         <li>middleString: 특수문자</li>
     *         </ul>
     * 
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
            String prefix = null;
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
                middleString += UMLMessage.MESSAGE_KOREAN_REG_EXP;
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
                middleString = temp.toString(); // 용어
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
     * 주어진 문자열이 주어진 정규식 패턴과 동일한지 매칭한 후 boolean 결과값을 리턴한다. <br/>
     * 문자열이 패턴과 매치한 후 동일한 경우 true, 아닌 경우 false을 리턴한다.
     * 
     * @param shortName
     *            비교할 원본 문자열
     * @param pattern
     *            비교할 정규식 패턴 문자열
     * @return boolean 원본이 패턴과 매치되는 경우 true를 리턴하고 그 외의 경우에는 false 리턴.
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
     * 주어진 문자열이 주어진 정규식 패턴과 동일한지 매칭한 후 boolean 결과값을 리턴한다.<br/>
     * 유니코드 기반의 복수행 모드 패턴 체킹을 수행한다. 동일한 경우 true, 아닌 경우 false 리턴.
     * 
     * @param shortName
     *            비교할 문자열
     * @param pattern
     *            비교할 정규식 패턴
     * @return boolean 원본이 패턴과 매치되는 경우 true를 리턴하고 그 외의 경우에는 false 리턴.
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
     * 여러 행의 주어진 문자열을 읽어 각 행을 주어진 정규식 패턴과 동일한 지 매칭한 후 매칭되는 행이 있는지 boolean 결과값을
     * 리턴한다. 유니코드 기반의 복수행 모드 패턴 체킹을 수행한다. 매칭되는 행이 존재하는 경우 true, 아닌 경우 false를
     * 리턴한다.
     * 
     * @param source
     *            패턴을 체크할 복수행 문자열
     * @param pattern
     *            비교할 정규식 패턴
     * @return boolean 원본이 패턴과 매치되는 경우 true를 리턴하고 그 외의 경우에는 false 리턴.
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
     * 주어진 문자열이 주어진 정규식 패턴과 동일한지 매칭한 후 boolean 결과값을 리턴한다. shortName과 pattern이
     * 매칭되는 경우 true, 아닌 경우 false 리턴.
     * 
     * @param shortName
     *            비교할 원본 문자열
     * @param pattern
     *            비교할 정규식 패턴
     * @param flag
     *            <ul>
     *            <li>CASE_INSENSITIVE: 대문자와 소문자를 구별하지 않는 매칭</li>
     *            <li>MULTILINE: 복수행 모드</li>
     *            <li>DOTALL: DOTALL 모드</li>
     *            <li>UNICODE_CASE: Unicode 에 준거한 대문자와 소문자를 구별하지 않는 매칭</li>
     *            <li>CANON_EQ: 2 개의 문자의 완전한 정규 분해가 매치 했을 경우에 한정</li>
     *            <li>UNIX_LINES: Unix 라인 모드</li>
     *            <li>LITERAL: 패턴을 지정하는 입력 캐릭터 라인은, 리터럴 문자의 순서로서 처리</li>
     *            <li>COMMENTS: 패턴 내에서 공백과 코멘트를 사용</li>
     *            </ul>
     *            CASE_INSENSITIVE , 를 포함할 수 있는 비트 마스크
     * @return boolean 원본이 패턴과 매치되는 경우 true를 리턴하고 그 외의 경우에는 false 리턴.
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
     * 클래스 주석의 작성 여부를 체크할 때 사용된다.<br/>
     * 주석이 작성된 경우 true, 아니면 false 리턴 클래스 주석의 정규식은 NEXCORE Tools에서 사용하는 클래스 주석을
     * 기준으로 한다.
     * 
     * @param comment
     *            입력된 주석 문자열
     * @return boolean 원본이 패턴과 매치되는 경우 true를 리턴하고 그 외의 경우에는 false 리턴.
     */
    public static synchronized boolean checkCommentPattern(String comment) {
        boolean result = false;

        Pattern p = Pattern.compile("<li>\\s*[^<>]*\\s*</li>"); //$NON-NLS-1$
        Matcher match = p.matcher(comment);
        Pattern commentPattern = Pattern.compile(PatternConstants.COMMENT);
        Matcher commentMatch = null;

        String temp = ""; //$NON-NLS-1$
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
     * 문자열 source에서 pattern과 동일한 패턴을 전부 검색하여 RegExpParameter의 List 형태로 리턴한다.
     * 
     * @param source
     *            패턴을 찾을 입력 문자열
     * @param pattern
     *            검색할 문자열 패턴
     * @return List<RegExpParameter> 매치된 모든 패턴을 RegExpParameter 형태로 전환하여 리턴한다.
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
     * 문자열 source에서 pattern과 동일한 패턴을 전부 검색하여 RegExpParameter의 List 형태로 리턴한다.
     * 
     * @param source
     *            패턴을 찾을 입력 문자열
     * @param pattern
     *            검색할 문자열 패턴
     * @return List<RegExpParameter> 매치된 모든 패턴을 RegExpParameter 형태로 전환하여 리턴한다.
     */
    public static synchronized List<RegExpParameter> findPatternList(String source, String pattern, String filePath) {
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
            regExpParameter.setSourceName(filePath);
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
     * @return RegExpParameter 매치된 첫번째 패턴을 RegExpParameter 형태로 전환하여 리턴한다.
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
     * File 형태의 file 에서 pattern과 동일한 패턴을 전부 검색하여 RegExpParameter로 변환한 후 List 형태에
     * 첨가하여 리턴한다.
     * 
     * @param file
     *            패턴을 찾을 입력 파일
     * @param pattern
     *            검색할 정규식 패턴
     * @return List<RegExpParameter> 매치된 모든 패턴을 RegExpParameter 형태로 전환하여 리턴한다.
     * @exception java.io.FileNotFoundException
     */
    public static synchronized List<RegExpParameter> findPattern(File file, String pattern) {
        List<RegExpParameter> parameters = new ArrayList<RegExpParameter>();

        int fileSize = 0;

        if (file != null) {
            fileSize = (int) file.length();
        } else {
            throw new UMLRuntimeException("Null object!");
        }

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
     * stream 에서 pattern과 동일한 패턴을 전부 검색하여 RegExpParameter로 변환한 후 List 형태에 첨가하여
     * 리턴한다.
     * 
     * @param stream
     *            패턴을 찾을 입력스트림
     * @param inputSize
     *            입력스트림의 크기
     * @param pattern
     *            검색할 정규식 패턴
     * @return List<RegExpParameter> 매치된 모든 패턴을 RegExpParameter 형태로 전환하여 리턴한다.
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
     * stream 에서 pattern과 동일한 패턴을 전부 검색하여 RegExpParameter로 변환한 후 List 형태에 첨가하여
     * 리턴한다.
     * 
     * @param stream
     *            패턴을 찾을 입력스트림
     * @param inputSize
     *            입력스트림의 크기
     * @param pattern
     *            검색할 정규식 패턴
     * @return List<RegExpParameter> 매치된 모든 패턴을 RegExpParameter 형태로 전환하여 리턴한다.
     * @exception java.io.IOException
     */
    public static synchronized List<RegExpParameter> findRegExpParameterList(String source, String pattern) {
        List<RegExpParameter> parameters = new ArrayList<RegExpParameter>();
        Pattern p = Pattern.compile(pattern);
        Matcher match = p.matcher(source);

        RegExpParameter parameter = null;
        while (match.find()) {
            parameter = new RegExpParameter(match.start(), match.end());
            parameter.setMatchString(source.substring(match.start(), match.end()));
            parameters.add(parameter);
        }
        return parameters;
    }

    /**
     * source에서 기존의 pattern 정규식 패턴과 동일한 문자열을 전부 검색하여 replaceString과 같은 문자열로 모두
     * 변경한다.
     * 
     * @param source
     *            변경할 대상 문자열
     * @param pattern
     *            매칭을 수행할 정규식 패턴
     * @param replaceString
     *            치환할 문자열
     * @return StringBuffer replaceString으로 치환된 문자열
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
     * source에서 기존의 pattern 정규식 패턴과 동일한 문자열들을 전부 검색하여 index 번째 매치 항목을 replaceStr
     * 정규식 패턴으로 변경한다.
     * 
     * @param source
     *            변경할 대상 문자열
     * @param pattern
     *            매칭을 수행할 정규식 패턴
     * @param replaceString
     *            치환할 문자열
     * @param index
     *            변경한 매치 항목의 순서
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
     * file에서 기존의 pattern 정규식 패턴과 동일한 문자열을 전부 검색하여 replaceString과 같은 문자열로 모두
     * 변경한다.
     * 
     * @param file
     *            변경할 파일 스트림
     * @param pattern
     *            매칭을 수행할 정규식 패턴
     * @param replaceString
     *            치환할 문자열
     * @return StringBuffer replaceString으로 치환된 문자열
     */
    public static synchronized StringBuffer replacePattern(File file, String pattern, String replaceString) {
        if (file == null) {
            throw new UMLRuntimeException("Null object!");
        }

        int fileSize = (int) file.length();
        FileInputStream stream = null;
        StringBuffer newString = new StringBuffer();
        try {
            stream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            Log.error(e);
        }
        newString = replacePattern(stream, fileSize, pattern, replaceString);
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
     * 입력스트림에서 기존의 pattern 정규식 패턴과 동일한 문자열을 전부 검색하여 replaceString과 같은 문자열로 모두
     * 변경한다.
     * 
     * @param stream
     *            변경할 입력스트림
     * @param inputSize
     *            입력스트림의 크기
     * @param pattern
     *            매칭을 수행할 정규식 패턴
     * @param replaceString
     *            치환할 문자열
     * @return StringBuffer replaceString으로 치환된 문자열
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
     * SOURCE 문장 속에 RegExp에서 사용하는 다음과 같은 키워드가 있는 경우 앞에 '\'를 붙여 변환해준다. <br/>
     * <p>
     * "*", ".", "{", "}", "(", ")", "^", "[", "]", "-", "|", "&", "\\d", "\\D",
     * "\\s", "\\S", "\\w", "\\W", "\\b", "\\B", "\\A", "\\z", "\\Z", "+", "?"
     * </p>
     * 
     * @param source
     *            변환 대상 문자열
     * @return String 키워드들이 '/+키워드'로 변경된 문자열
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

    /**
     * 일반 규칙에 따라 입력된 문자열을 정규표현식으로 자동변환한다.
     * 
     * A → [A-Z] a → [a-z] # → [0-9] - → - (그대로) _ → _ (그대로) E → [a-zA-Z] L →
     * [0-9a-zA-Z] [문자] → 문자 (대괄호를 빼고 그대로) 한글 및 기타 문자 : 사용불가
     * 
     * @param idNaming
     * @return String
     */
    public static String convertIdNamingToReqExp(String idNaming) {
        StringBuilder regExp = new StringBuilder();
        StringBuilder inside = new StringBuilder();
        boolean isInside = false;

        char[] chars = idNaming.toCharArray();

        for (char ch : chars) {
            switch (ch) {
                case 'A':
                    if (isInside) {
                        inside.append(ch);
                    } else {
                        regExp.append("[A-Z]"); //$NON-NLS-1$
                    }
                    break;
                case 'a':
                    if (isInside) {
                        inside.append(ch);
                    } else {
                        regExp.append("[a-z]"); //$NON-NLS-1$
                    }
                    break;
                case '#':
                    if (isInside) {
                        inside.append(ch);
                    } else {
                        regExp.append("[0-9]"); //$NON-NLS-1$
                    }
                    break;
                case '-':
                    if (isInside) {
                        inside.append(ch);
                    } else {
                        regExp.append("\\-"); //$NON-NLS-1$
                    }
                    break;
                case '_':
                    if (isInside) {
                        inside.append(ch);
                    } else {
                        regExp.append("_"); //$NON-NLS-1$
                    }
                    break;
                case 'E':
                    if (isInside) {
                        inside.append(ch);
                    } else {
                        regExp.append("[a-zA-Z]"); //$NON-NLS-1$
                    }
                    break;
                case 'L':
                    if (isInside) {
                        inside.append(ch);
                    } else {
                        regExp.append("[0-9a-zA-Z]"); //$NON-NLS-1$
                    }
                    break;
                case '*':
                    if (isInside) {
                        inside.append(ch);
                    } else {
                        regExp.append("[\\S]+"); //$NON-NLS-1$
                    }
                    break;
                case '1':
                    if (isInside) {
                        inside.append(ch);
                    } else {
                        regExp.append("[0-9]+"); //$NON-NLS-1$
                    }
                    break;
                case '[':
                    isInside = true;
                    inside = new StringBuilder();
                    break;
                case ']':
                    isInside = false;
                    regExp.append(inside.toString());
                    break;
                case ';':
                    break;
                default:
                    if (isInside) {
                        inside.append(ch);
                    }
                    break;
            }
        }

        return regExp.toString();
    }
}
