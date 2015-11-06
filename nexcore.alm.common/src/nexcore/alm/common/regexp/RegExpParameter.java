/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.alm.common.regexp;

/**
 * RegExpPart 모델에서 사용하는 정규식의 시작점, 끝점, 줄 수, 소스명, 매치 문자열 정보를 파라미터로 처리한다.<br/>
 * 정규식 문자열 및 패턴을 위한 모델이며 그 외 특별한 기타 기능은 없다. 끝점이 시작점보다 크지 않도록 사용자가 생성 및 처리 시에
 * 주의하여야 한다.
 * 
 * <ui> <li>업무 그룹명 : nexcore.tool.core.regexp</li> <li>서브 업무명 :
 * RegExpParameter.java</li> <li>작성자 :</li> <li>작성일 :</li> <li>설 명 :
 * RegExpParameter</li> </ui>
 */
public class RegExpParameter {

    private int startPos;

    private int endPos;

    private int lineNumber;

    private String sourceName;

    private String matchString;

    /**
     * RegExpParameter의 모든 필드를 초기화한다.
     */
    public void clear() {
        this.startPos = -1;
        this.endPos = -1;
        this.lineNumber = -1;
        this.sourceName = "";
        this.matchString = "";
    }

    public RegExpParameter() {
    }

    /**
     * 정규표현식의 정보를 넘겨주는 파라미터 객체를 생성한다.
     * 
     * @param startPos
     *            시작점
     * @param endPos
     *            끝점
     * @param lineNumber
     *            줄 정보
     * @param sourceName
     *            입력 문자열
     */
    public RegExpParameter(int startPos, int endPos, int lineNumber, String sourceName) {
        this.startPos = startPos;
        this.endPos = endPos;
        this.lineNumber = lineNumber;
        this.sourceName = sourceName;
    }

    /**
     * 정규표현식 정보를 넘겨주는 파라미터 객체를 생성한다.
     * 
     * @param startPos
     *            시작점
     * @param endPos
     *            끝점
     */
    public RegExpParameter(int startPos, int endPos) {
        this.startPos = startPos;
        this.endPos = endPos;
    }

    /**
     * 정규표현식 정보를 넘겨주는 파라미터 객체를 생성한다.
     * 
     * @param startPos
     *            시작점
     * @param endPos
     *            끝점
     * @param sourceName
     *            입력 문자열
     */
    public RegExpParameter(int startPos, int endPos, String sourceName) {
        this.startPos = startPos;
        this.endPos = endPos;
        this.sourceName = sourceName;
    }

    /**
     * 정규표현식의 끝점을 리턴한다.
     * 
     * @return int 정규식 끝
     */
    public int getEndPos() {
        return endPos;
    }

    /**
     * 정규표현식의 줄 수 정보를 리턴한다.
     * 
     * @return int 정규식 줄 수
     */
    public int getLineNumber() {
        return lineNumber;
    }

    /**
     * 정규 표현식 입력 문자열의 이름을 리턴한다.
     * 
     * @return String 소스 문자열
     */
    public String getSourceName() {
        return sourceName;
    }

    /**
     * 정규표현식의 시작 점을 리턴한다.
     * 
     * @return int 정규식 시작점
     */
    public int getStartPos() {
        return startPos;
    }

    /**
     * 패턴을 매치할 문자열을 리턴한다.
     * 
     * @return String 매칭할 문자열
     */
    public String getMatchString() {
        return matchString;
    }

    /**
     * 패턴을 매치할 문자열을 세팅한다.
     * 
     * @param source
     *            소스 문자열
     */
    public void setMatchString(String source) {
        this.matchString = source;
    }

    /**
     * 정규표현식 시작점을 세팅한다.
     * 
     * @param startPos
     *            정규식 시작점
     * 
     */
    public void setStartPos(int startPos) {
        this.startPos = startPos;
    }

    /**
     * 정규표현식 끝점을 세팅한다.
     * 
     * @param endPos
     *            정규식 끝 점
     * 
     */
    public void setEndPos(int endPos) {
        this.endPos = endPos;
    }

    /**
     * 정규표현식 줄 수를 세팅한다.
     * 
     * @param lineNumber
     *            정규식 라인 수
     * 
     */
    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    /**
     * 정규표현식 입력 문자열의 이름을 세팅한다.
     * 
     * @param sourceName
     *            입력 문자열 이름
     * 
     */
    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

}
