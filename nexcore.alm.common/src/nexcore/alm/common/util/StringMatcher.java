/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 ******************************************************************************/
package nexcore.alm.common.util;

import java.util.Vector;

/**
 * StringMatcher 클래스는 "*" 과 "?" 의 와일드카드를 지원하는 문자열 매칭 클래스로 이클립스의 클래스를 그대로 가져왔다. 
 */

public class StringMatcher {
    protected String fPattern;

    protected int fLength; // pattern length

    protected boolean fIgnoreWildCards;

    protected boolean fIgnoreCase;

    protected boolean fHasLeadingStar;

    protected boolean fHasTrailingStar;

    /* the given pattern is split into separated segments */
    protected String fSegments[];

    /* boundary value beyond which we don't need to search in the text */
    protected int fBound = 0;

    protected static final char fSingleWildCard = '\u0000';

    public static class Position {
        int start; // inclusive

        int end; // exclusive

        public Position(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }
    }

    /**
     * StringMatcher 생성자는 '*'(여러 문자)나 '?'(한 문자)와 같은 와일드 카드 문자를 포함하는 문자열 패턴을 받을 수
     * 있다. 이 때 패턴의 '*'나 '?'는 literal로 사용 시 반드시 "\*"나 "\?" 와 같이 escape 문자를 사용하여
     * 표시되어야 한다.
     * 
     * <pre>
     *   String pattern = &quot;\*&quot; 
     *   StringMatcher matcher = new StringMatcher(pattern, true, false);
     * </pre>
     * 
     * @param pattern
     *            텍스트와 매치시킬 패턴
     * @param ignoreCase
     *            대소문자 구별을 무시할 지 여부 (true면 대소문자 무시, false면 대소문자 구별)
     * @param ignoreWildCards
     *            와일드 카드 문자를 무시할 지 여부 (true면 와일드 카드를 무시하고 모두 literal로 취급, false면
     *            와일드 카드 문자 인식)
     */
    public StringMatcher(String pattern, boolean ignoreCase, boolean ignoreWildCards) {
        if (pattern == null) {
            throw new IllegalArgumentException();
        }
        fIgnoreCase = ignoreCase;
        fIgnoreWildCards = ignoreWildCards;
        fPattern = pattern;
        fLength = pattern.length();

        if (fIgnoreWildCards) {
            parseNoWildCards();
        } else {
            parseWildCards();
        }
    }

    /**
     * start(포함)와 end(포함하지 않음) 사이에서 패턴과 첫번째로 매치되는 항목을 찾는다.
     * 
     * @param text
     *            매치를 수행할 문자열 객체
     * @param start
     *            매치 검색 범위 시작점(시작점은 검색에 포함됨)
     * @param end
     *            매치 검색 범위 끝점 (끝점은 검색에 포함되지 않음)
     * @return boolean StringMatcher.Position 문자열에서 패턴과 첫번째 매치가 발생한 항목의 시작점과 끝점을
     *         포함하는 객체. 매치된 항목이 없거나 start와 end가 같으면 null을 리턴.
     */
    public StringMatcher.Position find(String text, int start, int end) {
        if (text == null) {
            throw new IllegalArgumentException();
        }

        int tlen = text.length();
        if (start < 0) {
            start = 0;
        }
        if (end > tlen) {
            end = tlen;
        }
        if (end < 0 || start >= end) {
            return null;
        }
        if (fLength == 0) {
            return new Position(start, start);
        }
        if (fIgnoreWildCards) {
            int x = posIn(text, start, end);
            if (x < 0) {
                return null;
            }
            return new Position(x, x + fLength);
        }

        int segCount = fSegments.length;
        if (segCount == 0) {
            return new Position(start, end);
        }

        int curPos = start;
        int matchStart = -1;
        int i;
        for (i = 0; i < segCount && curPos < end; ++i) {
            String current = fSegments[i];
            int nextMatch = regExpPosIn(text, curPos, end, current);
            if (nextMatch < 0) {
                return null;
            }
            if (i == 0) {
                matchStart = nextMatch;
            }
            curPos = nextMatch + current.length();
        }
        if (i < segCount) {
            return null;
        }
        return new Position(matchStart, curPos);
    }

    /**
     * 주어진 text 문자열을 패턴과 매칭한다.
     * 
     * @param text
     *            매칭할 문자열
     * @return 매치가 된 경우 true, 아닌 경우에는 false
     */
    public boolean match(String text) {
        if (text == null) {
            return false;
        }
        return match(text, 0, text.length());
    }

    /**
     * 입력 문자열 text에서 주어진 시작점 start와 끝점 end사이에 패턴과 매치되는 부분 문자열이 존재하는지 매칭을 수행한다.
     * 
     * @param text
     *            매칭을 수행할 문자열 객체
     * @param start
     *            매치를 수행할 부분 문자열의 시작점
     * @param end
     *            매치를 수행할 부분 문자열의 끝점
     * @return boolean 매치가 된 경우 true, 아닌 경우에는 false
     */
    public boolean match(String text, int start, int end) {
        if (null == text) {
            throw new IllegalArgumentException();
        }

        if (start > end) {
            return false;
        }

        if (fIgnoreWildCards) {
            return (end - start == fLength) && fPattern.regionMatches(fIgnoreCase, 0, text, start, fLength);
        }
        int segCount = fSegments.length;

        // System.out.println("segCount"+segCount);

        if (segCount == 0 && (fHasLeadingStar || fHasTrailingStar)) {
            return true;
        }

        if (start == end) {
            return fLength == 0;
        }
        // System.out.println("fLength"+fLength);

        if (fLength == 0) {
            return start == end;
        }

        int tlen = text.length();
        if (start < 0) {
            start = 0;
        }
        if (end > tlen) {
            end = tlen;
        }

        int tCurPos = start;
        int bound = end - fBound;
        if (bound < 0) {
            return false;
        }
        int i = 0;
        String current = fSegments[i];
        int segLength = current.length();

        /* process first segment */
        if (!fHasLeadingStar) {
            if (!regExpRegionMatches(text, start, current, 0, segLength)) {
                return false;
            } else {
                ++i;
                tCurPos = tCurPos + segLength;
            }
        }
        if ((fSegments.length == 1) && (!fHasLeadingStar) && (!fHasTrailingStar)) {
            // only one segment to match, no wildcards specified
            return tCurPos == end;
        }
        /* process middle segments */
        while (i < segCount) {
            current = fSegments[i];
            int currentMatch;
            int k = current.indexOf(fSingleWildCard);
            if (k < 0) {
                currentMatch = textPosIn(text, tCurPos, end, current);
                if (currentMatch < 0) {
                    return false;
                }
            } else {
                currentMatch = regExpPosIn(text, tCurPos, end, current);
                if (currentMatch < 0) {
                    return false;
                }
            }
            tCurPos = currentMatch + current.length();
            i++;
        }

        /* process final segment */
        if (!fHasTrailingStar && tCurPos != end) {
            int clen = current.length();
            return regExpRegionMatches(text, end - clen, current, 0, clen);
        }
        return i == segCount;
    }

    /**
     * 주어진 패턴을 '*'로 나누어진 세그먼트들로 파싱한다. 이 메소드는 와일드 카드 문자를 무시하므로 1개의 세그먼트로 파싱된다.
     */
    private void parseNoWildCards() {
        fSegments = new String[1];
        fSegments[0] = fPattern;
        fBound = fLength;
    }

    /**
     * 주어진 패턴을 '*'로 나누어진 세그먼트들로 파싱한다.
     */
    private void parseWildCards() {
        if (fPattern.startsWith("*")) { //$NON-NLS-1$
            fHasLeadingStar = true;
        }
        if (fPattern.endsWith("*")) {//$NON-NLS-1$
            /* make sure it's not an escaped wildcard */
            if (fLength > 1 && fPattern.charAt(fLength - 2) != '\\') {
                fHasTrailingStar = true;
            }
        }

        // System.out.println("fHasTrailingStar:"+fHasTrailingStar);
        Vector temp = new Vector();

        int pos = 0;
        StringBuffer buf = new StringBuffer();
        while (pos < fLength) {
            char c = fPattern.charAt(pos++);
            switch (c) {
                case '\\':
                    if (pos >= fLength) {
                        buf.append(c);
                    } else {
                        char next = fPattern.charAt(pos++);
                        /* if it's an escape sequence */
                        if (next == '*' || next == '?' || next == '\\') {
                            buf.append(next);
                        } else {
                            /* not an escape sequence, just insert literally */
                            buf.append(c);
                            buf.append(next);
                        }
                    }
                    break;
                case '*':
                    if (buf.length() > 0) {
                        /* new segment */
                        temp.addElement(buf.toString());
                        fBound += buf.length();
                        buf.setLength(0);
                    }
                    break;
                case '?':
                    /*
                     * append special character representing single match
                     * wildcard
                     */
                    buf.append(fSingleWildCard);
                    break;
                default:
                    buf.append(c);
            }
        }

        // System.out.println("buffer"+buf);

        /* add last buffer to segment list */
        if (buf.length() > 0) {
            temp.addElement(buf.toString());
            fBound += buf.length();
        }

        fSegments = new String[temp.size()];
        temp.copyInto(fSegments);
        // System.out.println("fBound:"+fBound);
        // System.out.println("fSegments;"+fSegments.toString());

    }

    /**
     * 와일드 카드 문자를 포함하지 않은 문자열을 패턴과 매칭하여 매칭이 된 부분 문자열의 시작점을 리턴한다. 만일 입력 문자열에서 패턴과
     * 매칭되는 부분이 존재하지 않는 경우에는 -1을 리턴한다.
     * 
     * @param text
     *            와일드 카드 문자를 포함하지 않은 문자열
     * @param start
     *            매칭을 수행할 부분 문자열의 시작점(매칭에 포함)
     * @param end
     *            매칭을 수행할 부분 문자열의 끝점(매칭에 포함 안됨)
     * @return int 패턴과 매치가 된 문자열의 시작점을 리턴한다. 매치 되는 항목이 존재하지 않은 경우엔 -1을 리턴한다.
     */
    protected int posIn(String text, int start, int end) {// no wild card in
        // pattern
        int max = end - fLength;

        if (!fIgnoreCase) {
            int i = text.indexOf(fPattern, start);
            if (i == -1 || i > max) {
                return -1;
            }
            return i;
        }

        for (int i = start; i <= max; ++i) {
            if (text.regionMatches(true, i, fPattern, 0, fLength)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * @param text
     *            '?'을 포함할 수 있는 정규 표현식
     * @param start
     *            매칭을 수행할 부분 문자열의 시작점
     * @param end
     *            매칭을 수행할 부분 문자열의 끝점
     * @param p
     *            '?'을 포함할 수 있는 정규 표현식
     * @param <code>caseIgnored</code>, wether the pattern is not casesensitive
     * @return the starting index in the text of the pattern , or -1 if not
     *         found
     */
    protected int regExpPosIn(String text, int start, int end, String p) {
        int plen = p.length();

        int max = end - plen;
        for (int i = start; i <= max; ++i) {
            if (regExpRegionMatches(text, i, p, 0, plen)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @param text 매칭을 수행할 문자열 
     * 
     * @param start 매칭을 시작할 시작점 (매칭시 포함) 
     * @param end 매칭을 종료할 끝점 (매칭시 비포함)
     * @param p '?'을 포함할 수 있는 정규 표현식 
     * @param <code>ignoreCase</code>, boolean indicating wether code>p</code>
     *            is case sensitive
     * @return boolean
     */
    protected boolean regExpRegionMatches(String text, int tStart, String p, int pStart, int plen) {
        while (plen-- > 0) {
            char tchar = text.charAt(tStart++);
            char pchar = p.charAt(pStart++);

            /* process wild cards */
            if (!fIgnoreWildCards) {
                /* skip single wild cards */
                if (pchar == fSingleWildCard) {
                    continue;
                }
            }
            if (pchar == tchar) {
                continue;
            }
            if (fIgnoreCase) {
                if (Character.toUpperCase(tchar) == Character.toUpperCase(pchar)) {
                    continue;
                }
                // comparing after converting to upper case doesn't handle all
                // cases;
                // also compare after converting to lower case
                if (Character.toLowerCase(tchar) == Character.toLowerCase(pchar)) {
                    continue;
                }
            }
            return false;
        }
        return true;
    }

    /**
     * @param <code>text</code>, the string to match
     * @param <code>start</code>, the starting index in the text for search,
     *            inclusive
     * @param <code>end</code>, the stopping point of search, exclusive
     * @param code>p
     *            </code>, a string that has no wildcard
     * @param <code>
     *            ignoreCase</code>, boolean indicating wether code>p</code>
     *            is case sensitive
     * @return the starting index in the text of the pattern , or -1 if not
     *         found
     */
    protected int textPosIn(String text, int start, int end, String p) {

        int plen = p.length();
        int max = end - plen;

        if (!fIgnoreCase) {
            int i = text.indexOf(p, start);
            if (i == -1 || i > max) {
                return -1;
            }
            return i;
        }

        for (int i = start; i <= max; ++i) {
            if (text.regionMatches(true, i, p, 0, plen)) {
                return i;
            }
        }

        return -1;
    }
}
