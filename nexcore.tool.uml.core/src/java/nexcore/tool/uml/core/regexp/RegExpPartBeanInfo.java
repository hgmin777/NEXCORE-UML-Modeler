/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.core.regexp;

import java.util.ArrayList;
import java.util.List;

import nexcore.tool.uml.core.util.StringUtil;

/**
 * 현재는 사용하지 않는 클래스. 차후 구현에 따라 사용될 여지가 있어 제거하지 않고 있음.
 * 
 * <ul>
 * <li>업무 그룹명 : nexbuild.foundation.core</li>
 * <li>서브 업무명 : nexbuild.foundation.core.regexp</li>
 * <li>설 명 : RegExpPartBeanInfo</li>
 * <li>작성일 : 2008. 04. 09</li>
 * <li>작성자 : 05531</li>
 * </ul>
 */
public class RegExpPartBeanInfo {

    /**
     * 명명법 타입
     */
    public static final String NAMING_TYPE = "naming"; //$NON-NLS-1$

    /**
     * 주석 타입
     */
    public static final String COMMENT_TYPE = "comment"; //$NON-NLS-1$

    /**
     * 클래스 타입
     */
    public static final String CLASS_TYPE = "class"; //$NON-NLS-1$

    /**
     * 메소드 타입
     */
    public static final String METHOD_TYPE = "method"; //$NON-NLS-1$

    /**
     * 리소스 타입
     */
    public static final String RESOURCE_TYPE = "resource"; //$NON-NLS-1$

    /**
     * 템플릿 타입
     */
    public static final String TEMPLATE_TYPE = "template"; //$NON-NLS-1$

    /**
     * 정규식 설정 종류
     */
    private String type;

    /**
     * 정규식 설정 명
     */
    private String name;

    /**
     * 최소 길이
     */
    private int minLength = -1;

    /**
     * 최대 길이
     */
    private int maxLength = -1;

    /**
     * 대소문자 Type
     */
    private int letterType;

    /**
     * 첫글자 대소문자 Type
     */
    private int firstLetterType;

    /**
     * 한글 허용 여부
     */
    private boolean allowKorean;

    /**
     * 숫자 허용 여부
     */
    private boolean allowNumber;

    /**
     * 허용 특수 문자 : ~, !, @, #, $, %, ^, &, * 등
     */
    private List<String> allowSpecialCharList;

    /**
     * 접두어 목록
     */
    private List<String> prefixList;

    /**
     * 접미어 목록
     */
    private List<String> postfixList;

    /**
     * 용어 목록
     */
    private List<String> termList;

    /**
     * Javadoc 형
     * 
     * @문자 : author 등
     */
    private List<String> javadocAnnotationList;

    /**
     * 정규식에서 사용할 클래스 명
     */
    private String className;

    /**
     * 정규식에서 사용할 메서드 명
     */
    private String methodName;

    /**
     * 리소스 할당 메서드 명
     */
    private String openMethodName;

    /**
     * 리소스 해제 메서드 명
     */
    private String closeMethodName;

    /**
     * 점검 대상 문장 종류
     */
    private int statementType;

    /**
     * Check Item Id;
     */
    private String checkItemId;

    /**
     * 점검 Template
     */
    private String template;

    /**
     * 리소스 할당 클래스 명
     */
    private String openClassName;

    /**
     * 리소스 해제 클래스
     */
    private String closeClassName;

    /**
     * 정규식 설정을 위한 모델인 RegExpPartBeanInfo를 생성하고 초기화한다.<br/>
     */
    public RegExpPartBeanInfo() {
        allowSpecialCharList = new ArrayList<String>();
        prefixList = new ArrayList<String>();
        postfixList = new ArrayList<String>();
        termList = new ArrayList<String>();
        javadocAnnotationList = new ArrayList<String>();
    }

    /**
     * 정규식 내에 한글 허용 여부를 리턴한다. 허용하면 true, 아니면 false
     * 
     * @return boolean
     */
    public boolean isAllowKorean() {
        return allowKorean;
    }

    /**
     * 정규식 내에 한글 허용 여부를 세팅한다.
     * 
     * @param allowKorean
     *            한글을 허용하면 true, 아니면 false
     */
    public void setAllowKorean(boolean allowKorean) {
        this.allowKorean = allowKorean;
    }

    /**
     * 정규식 내에 한글 허용 여부를 세팅한다.
     * 
     * @param allowKorean
     *            null이 아니며 "true"인 경우 true, 아니면 false
     */
    public void setAllowKorean(String allowKorean) {
        this.allowKorean = Boolean.parseBoolean(allowKorean);
    }

    /**
     * 정규식 내에 숫자 허용 여부를 리턴한다. 허용하면 true, 아니면 false
     * 
     * @return 허용하면 true, 아니면 false
     */
    public boolean isAllowNumber() {
        return allowNumber;
    }

    /**
     * 정규식 내에 숫자 허용 여부를 세팅한다.
     * 
     * @param allowNumber
     *            숫자를 허용하면 true, 아니면 false
     */
    public void setAllowNumber(boolean allowNumber) {
        this.allowNumber = allowNumber;
    }

    /**
     * 정규식 내에 숫자 허용 여부를 세팅한다.
     * 
     * @param allowNumber
     *            null이 아니며 "true"인 경우 true, 아니면 false
     */
    public void setAllowNumber(String allowNumber) {
        this.allowNumber = Boolean.parseBoolean(allowNumber);
    }

    /**
     * 정규식 내에 ~, !, @, #, $, %, ^, &, * 등의 특수문자를 허용하는지 여부를 리턴한다.
     * 
     * @return boolean 특수문자를 허용하면 true, 아니면 false
     */
    public List<String> getAllowSpecialCharList() {
        return allowSpecialCharList;
    }

    /**
     * 정규식에서 사용할 클래스 명을 리턴한다. null인 경우 ""를 리턴한다.
     * 
     * @return String 클래스 명
     */
    public String getClassName() {
        return StringUtil.nullStringCheck(className);
    }

    /**
     * 정규식에서 사용할 클래스 명을 세팅한다.
     * 
     * @param className
     *            클래스 명
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * 리소스 해제 메소드 명을 리턴한다.
     * 
     * @return String
     */
    public String getCloseMethodName() {
        return StringUtil.nullStringCheck(closeMethodName);
    }

    /**
     * 리소스 해제 메소드 명을 세팅한다.
     * 
     * @param closeMethodName
     *            리소스 해제 메소드 명
     */
    public void setCloseMethodName(String closeMethodName) {
        this.closeMethodName = closeMethodName;
    }

    /**
     * JavaDoc 주석 목록을 리턴한다.
     * 
     * @return
     */
    public List<String> getJavadocAnnotationList() {
        return javadocAnnotationList;
    }

    /**
     * 대소문자 타입을 리턴한다.
     * <p>
     * <ul>
     * <li>대문자인 경우 2(1 << 1)</li>
     * <li>소문자인 경우 4(1 << 2)</li>
     * <li>둘 다인 경우 8(1 << 3)</li>
     * </p>
     * 
     * @return int
     */
    public int getLetterType() {
        return letterType;
    }

    /**
     * 대소문자 타입을 세팅한다.
     * <p>
     * <ul>
     * <li>대문자인 경우 2(1 << 1)</li>
     * <li>소문자인 경우 4(1 << 2)</li>
     * <li>둘 다인 경우 8(1 << 3)</li>
     * </p>
     * 
     * @param letterType
     *            대소문자 타입
     */
    public void setLetterType(int letterType) {
        this.letterType = letterType;
    }

    /**
     * 정규식의 최대 길이를 리턴한다.
     * 
     * @return int
     */
    public int getMaxLength() {
        return maxLength;
    }

    /**
     * 정규식의 최대 길이를 세팅한다.
     * 
     * @param maxLength
     *            최대 길이
     */
    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    /**
     * 문자열을 받아 정수화하여 정규식의 최대 길이를 세팅한다.
     * 
     * @param maxLength
     */
    public void setMaxLength(String maxLength) {
        this.maxLength = Integer.parseInt(maxLength);
    }

    /**
     * 정규식에서 사용할 메소드 명을 리턴한다.
     * 
     * @return String 정규식 명
     */
    public String getMethodName() {
        return StringUtil.nullStringCheck(methodName);
    }

    /**
     * 정규식에서 사용할 메소드 명을 세팅한다.
     * 
     * @param methodName
     *            정규식 명
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    /**
     * 정규식의 최소 길이를 리턴한다.
     * 
     * @return int 최소 길이
     */
    public int getMinLength() {
        return minLength;
    }

    /**
     * 정규식의 최소 길이를 세팅한다.
     * 
     * @param minLength
     *            정규식 최소 길이
     */
    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    /**
     * 문자열을 받아 정규식의 최소길이로 세팅한다.
     * 
     * @param minLength
     *            정규식 최소 길이
     */
    public void setMinLength(String minLength) {
        this.minLength = Integer.parseInt(minLength);
    }

    /**
     * 정규식 설정 명을 리턴한다. null일 경우 ""를 리턴한다.
     * 
     * @return String 정규식 설정 명
     */
    public String getName() {
        return StringUtil.nullStringCheck(name);
    }

    /**
     * 정규식 설정 명을 세팅한다.
     * 
     * @param name
     *            정규식 설정 명
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 리소스 할당 메서드 명을 리턴한다. null일 경우 ""를 리턴한다.
     * 
     * @return String 리소스 할당 메소드 명
     */
    public String getOpenMethodName() {
        return StringUtil.nullStringCheck(openMethodName);
    }

    /**
     * 리소스 할당 메서드 명을 세팅한다.
     * 
     * @param openMethodName
     *            리소스 할당 메소드 명
     */
    public void setOpenMethodName(String openMethodName) {
        this.openMethodName = openMethodName;
    }

    /**
     * 정규식의 접미어 목록 리스트를 리턴한다.
     * 
     * @return List<String> 접미어 목록
     */
    public List<String> getPostfixList() {
        return postfixList;
    }

    /**
     * 정규식의 접두어 목록 리스트를 리턴한다.
     * 
     * @return List<String> 접두어 목록
     */
    public List<String> getPrefixList() {
        return prefixList;
    }

    /**
     * 정규식의 용어 목록을 리턴한다.
     * 
     * @return List<String> 용어 목록
     */
    public List<String> getTermList() {
        return termList;
    }

    /**
     * 정규식의 설정 종류를 리턴한다. null일 경우 ""를 리턴한다.
     * 
     * @return String 설정 타입
     */
    public String getType() {
        return StringUtil.nullStringCheck(type);
    }

    /**
     * 정규식의 설정 종류를 세팅한다.
     * <p>
     * <ul>
     * <li>NAMING_TYPE</li>
     * <li>COMMENT_TYPE</li>
     * <li>CLASS_TYPE</li>
     * <li>METHOD_TYPE</li>
     * <li>RESOURCE_TYPE,</li>
     * <li>TEMPLATE_TYPE</li>
     * </p>
     * 
     * @param type
     *            설정 타입
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 점검 대상 문장의 종류를 리턴한다.
     * 
     * @return int 문장 종류
     */
    public int getStatementType() {
        return statementType;
    }

    /**
     * 점검 대상 문장의 종류를 세팅한다.
     * 
     * @param statementType
     */
    public void setStatementType(int statementType) {
        this.statementType = statementType;
    }

    /**
     * 점검 대상 문장의 종류를 세팅한다.
     * 
     * @param statementType
     */
    public void setStatementType(String statementType) {
        this.statementType = Integer.parseInt(statementType);
    }

    /**
     * 특수 문자 리스트에 value를 추가한다. 허용 특수 문자 예 : ~, !, @, #, $, %, ^, &, * 등
     * 
     * @param value
     *            허용할 특수 문자
     */
    public void addAllowSpecialChar(String value) {
        this.allowSpecialCharList.add(value);
    }

    /**
     * 특수 문자 리스트에서 value를 제거한다.
     * 
     * @param value
     *            제거할 특수 문자
     */
    public void removeAllowSpecialChar(String value) {
        this.allowSpecialCharList.remove(value);
    }

    /**
     * 접두어 목록에 value를 추가한다.
     * 
     * @param value
     *            추가할 접두어
     */
    public void addPrefix(String value) {
        this.prefixList.add(value);
    }

    /**
     * 접두어 목록에 value를 제거한다.
     * 
     * @param value
     *            제거할 접두어
     */
    public void removePrefix(String value) {
        this.prefixList.remove(value);
    }

    /**
     * 접미어 목록에 value를 추가한다.
     * 
     * @param value
     *            추가할 접미어
     */
    public void addPostfix(String value) {
        this.postfixList.add(value);
    }

    /**
     * 접미어 목록에 value를 제거한다.
     * 
     * @param value
     *            제거할 접미어
     */
    public void removePostfix(String value) {
        this.postfixList.remove(value);
    }

    /**
     * 용어 목록에 value를 추가한다.
     * 
     * @param value
     *            추가할 용어
     */
    public void addTerm(String value) {
        this.termList.add(value);
    }

    /**
     * 용어 목록에 value를 제거한다.
     * 
     * @param value
     *            제거할 용어
     */
    public void removeTerm(String value) {
        this.termList.remove(value);
    }

    /**
     * JavaDoc 리스트에 value를 추가한다.
     * 
     * @param value
     *            추가할 JavaDoc 요소
     */
    public void addJavacocAnnotation(String value) {
        this.javadocAnnotationList.add(value);
    }

    /**
     * JavaDoc 리스트에 value를 제거한다.
     * 
     * @param value
     *            제거할 JavaDoc 요소
     */
    public void removeJavacocAnnotation(String value) {
        this.javadocAnnotationList.remove(value);
    }

    /**
     * 첫 글자가 대소문자인지 리턴한다.
     * <p>
     * <ul>
     * <li>대문자인 경우 2(1 << 1)</li>
     * <li>소문자인 경우 4(1 << 2)</li>
     * <li>둘 다인 경우 8(1 << 3)</li>
     * </p>
     * 
     * @return
     */
    public int getFirstLetterType() {
        return firstLetterType;
    }

    /**
     * 첫 글자의 대소문자 여부를 설정한다.
     * <p>
     * <ul>
     * <li>대문자인 경우 2(1 << 1)</li>
     * <li>소문자인 경우 4(1 << 2)</li>
     * <li>둘 다인 경우 8(1 << 3)</li>
     * </p>
     * 
     * @param firstLetterType
     */
    public void setFirstLetterType(int firstLetterType) {
        this.firstLetterType = firstLetterType;
    }

    /**
     * 체크 아이템 아이디를 리턴한다.
     * 
     * @return String 체크 아이템 아이디
     */
    public String getCheckItemId() {
        return checkItemId;
    }

    /**
     * 체크 아이템 아이디를 세팅한다.
     * 
     * @param checkItemId
     *            체크 아이템 아이디
     */
    public void setCheckItemId(String checkItemId) {
        this.checkItemId = checkItemId;
    }

    /**
     * 점검 Template 명을 리턴한다.
     * 
     * @return the template
     */
    public String getTemplate() {
        return template;
    }

    /**
     * 점검 Template 명을 세팅한다.
     * 
     * @param template
     *            the template to set
     */
    public void setTemplate(String template) {
        this.template = template;
    }

    /**
     * 리소스 할당 클래스 명을 리턴한다.
     * 
     * @return String
     */
    public String getOpenClassName() {
        return openClassName;
    }

    /**
     * 리소스 할당 클래스 명을 세팅한다.
     * 
     * @param openClassName
     *            the openClassName to set
     */
    public void setOpenClassName(String openClassName) {
        this.openClassName = openClassName;
    }

    /**
     * 리소스 해제 클래스 명을 리턴한다.
     * 
     * @return the closeClassName
     */
    public String getCloseClassName() {
        return closeClassName;
    }

    /**
     * 리소스 해제 클래스 명을 세팅한다.
     * 
     * @param closeClassName
     *            the closeClassName to set
     */
    public void setCloseClassName(String closeClassName) {
        this.closeClassName = closeClassName;
    }
}
