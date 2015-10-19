/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.core.regexp;
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.core.regexp</li>
 * <li>설  명 : PatternConstants</li>
 * <li>작성일 : 2008. 12. 18</li>
 * <li>작성자 : 박수용 </li>
 * </ul>
 *  * 정규식 관련 정규식 상수와 타입 정보를 정의한다. 특정 플러그인에서만 사용되는 정보는 되도록 첨가하지 않는다.
 */
public interface PatternConstants {

    /**
     * java convention 명명법 관련
     */
    public static final String[] SPECIAL_CHAR_LABELS = { "~", "!", "@", "#", "$", "%", "^", "&&", "*", ":" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$

    /**
     * abstract class
     */
    public static final String ABSTRACT_CLASS_NAME = "^Abstract.*$|^.*Factory$"; //$NON-NLS-1$

    /**
     * constants (static, final fields)
     */
    public static final String CONSTANT_NAME = "^[A-Z][A-Z0-9]*(_[A-Z0-9]+)*$"; //$NON-NLS-1$

    /**
     * local, non-final variables
     */
    public static final String FILED_NAME = "^[a-z][a-zA-Z0-9]*$"; //$NON-NLS-1$

    /**
     * methods
     */
    public static final String METHOD_NAME = "^[a-z][a-zA-Z0-9]*$"; //$NON-NLS-1$

    /**
     * packages
     */
    public static final String PACKAGE_NAME = "^[a-z]+(/.[a-zA-Z_][a-zA-Z0-9_]*)*$"; //$NON-NLS-1$

    /**
     * parameters
     */
    public static final String PRAMETER_NAME = "^[a-z][a-zA-Z0-9]*$"; //$NON-NLS-1$

    /**
     * static, non-final fields
     */
    public static final String STATIC_VARIABLE_NAME = "^[a-z][a-zA-Z0-9]*$"; //$NON-NLS-1$

    /**
     * classes and interfaces
     */
    public static final String TYPE_NAME = "^[A-Z][a-zA-Z0-9]*$"; //$NON-NLS-1$

    /**
     * 주석
     */
    public static final String COMMENT = "<li>\\s*.+\\s*:\\s*\\S+\\s*</li>"; //$NON-NLS-1$

    /**
     * block is empty
     */
    public static final String CONTAIN_BLANK = "\\{\\s*\\S+\\s*\\}"; //$NON-NLS-1$

    /**
     * for,while 문에서 new 구문을 가지고 있는지 체크
     */
    public static final String CONTAIN_NEW = "[for|while]+\\(.+\\)\\{.*\\s[new]+\\s.*\\}"; //$NON-NLS-1$

    /**
     * 공통 코드 패턴
     */
    public static final String CODE = "[a-zA-Z]{3}[0-9]{4}"; //$NON-NLS-1$

    /**
     * EJB에서 사용할 메소드 패턴
     */
    public static final String EJB_METHOD_SIGNATURE = "[\\w]*public\\s+~return\\s+[\\w0-9]*\\s*\\(\\s*~firstParameter\\s*,\\s*~secondParameter\\s*\\)[\\s\\S]+"; //$NON-NLS-1$

    /**
     * IBATIS용 SQL에서 # # 패턴
     */
    public static final String SQL_SHARP_PARAMETER = "#[^#]+#"; //$NON-NLS-1$

    /**
     * IBATIS용 SQL에서 $ $ 패턴
     */
    public static final String SQL_DOLLAR_PARAMETER = "\\$[^\\$]+\\$"; //$NON-NLS-1$

    /**
     * IBATIS용 SQL에서 ? 패턴
     */
    public static final String SQL_QUESTION_PARAMETER = "\\?"; //$NON-NLS-1$

    /**
     * 정규표현식에서 사용되는 키워드 모음
     */
    public static final String[] REG_EXP_KEYWORD = {
        "*", ".", "{", "}", "(", ")", "^", "[", "]", "-", "|", "&", "\\d", "\\D", "\\s", "\\S", "\\w", "\\W", "\\b", "\\B", "\\A", "\\z", "\\Z", "+", "?" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$ //$NON-NLS-13$ //$NON-NLS-14$ //$NON-NLS-15$ //$NON-NLS-16$ //$NON-NLS-17$ //$NON-NLS-18$ //$NON-NLS-19$ //$NON-NLS-20$ //$NON-NLS-21$ //$NON-NLS-22$ //$NON-NLS-23$ //$NON-NLS-24$ //$NON-NLS-25$

    //    
    // /**
    // * SELECT 문.
    // */
    // public static final int SQLTYPE_SELECT = 1;
    // /**
    // * Insert 문.
    // */
    // public static final int SQLTYPE_INSERT = 2;
    // /**
    // * Update 문.
    // */
    // public static final int SQLTYPE_UPDATE = 3;
    // /**
    // * Delete 문.
    // */
    // public static final int SQLTYPE_DELETE = 4;
    // /**
    // * Function call 문.
    // */
    // public static final int SQLTYPE_FUNCTION = 5;

    // /**
    // * Select문의 시작 정규식 패턴
    // */
    //    public static final String SELECT_START_PATTERN = "[\\s\\S]*<select.+id=.+>[\\s\\S]*"; //$NON-NLS-1$
    // /**
    // *
    // */
    //    public static final String SELECT_END_PATTERN = "[\\s\\S]*</select>[\\s\\S]*"; //$NON-NLS-1$
    //     
    //     public static final String INSERT_START_PATTERN = "[\\s\\S]*<insert.+id=.+>[\\s\\S]*"; //$NON-NLS-1$
    //     public static final String INSERT_END_PATTERN = "[\\s\\S]*</insert>[\\s\\S]*"; //$NON-NLS-1$
    //     
    //     public static final String UPDATE_START_PATTERN = "[\\s\\S]*<update.+id=.+>[\\s\\S]*"; //$NON-NLS-1$
    //     public static final String UPDATE_END_PATTERN = "[\\s\\S]*</update>[\\s\\S]*"; //$NON-NLS-1$
    //     
    //     public static final String DELETE_START_PATTERN = "[\\s\\S]*<delete.+id=.+>[\\s\\S]*"; //$NON-NLS-1$
    //     public static final String DELETE_END_PATTERN = "[\\s\\S]*</delete>[\\s\\S]*"; //$NON-NLS-1$
    //     
    //     
    //     public static final String PROCEDURE_START_PATTERN = "[\\s\\S]*<procedure.+id=.+>[\\s\\S]*"; //$NON-NLS-1$
    //     public static final String PROCEDURE_END_PATTERN = "[\\s\\S]*</procedure>[\\s\\S]*"; //$NON-NLS-1$
    //     
    //     public static final String SQL_FRAGMENT_START_PATTERN = "[\\s\\S]*<sql.+id=.+>[\\s\\S]*"; //$NON-NLS-1$
    //     public static final String SQL_FRAGMENT_END_PATTERN = "[\\s\\S]*</sql>[\\s\\S]*"; //$NON-NLS-1$
    //     
    //     
    //     public static final String SELECT_PATTERN = "<select.+id=.+>[^(</select>)]*</select>";  //$NON-NLS-1$
    //     public static final String INSERT_PATTERN = "<insert.+id=.+>[^(insert)]*</insert>";  //$NON-NLS-1$
    //     public static final String UPDATE_PATTERN = "<update.+id=.+>[^(update)]*</update>";  //$NON-NLS-1$
    //     public static final String DELETE_PATTERN = "<delete.+id=.+>[^(delete)]*</delete>";  //$NON-NLS-1$
    //     public static final String PROCEDURE_PATTERN = "<procedure.+id=.+>[^(procedure)]*</procedure>"; //$NON-NLS-1$
    //     
    // // 향후 function 도 포함 하는 pattern으로 변경
    //     public static final String PROCEDURE_CALL_STATEMENT = "[\\s]*\\{[\\s]*call.*\\}[\\s]*"; //$NON-NLS-1$
    // // SQL 주석 처리에 대한 패턴
    // public static final String COMMENT_PATTERN =
    // "(/\\*([^*^+]|[\r\n]|(\\*+([^*/]|[\r\n])))*\\*+/)|(//.*)|(--^+.*)";
    //     

    /**
     * 대문자 타입
     */
    public static final int LETTER_TYPE_UPPERCASE = 1 << 1;

    /**
     * 소문자 타입
     */
    public static final int LETTER_TYPE_LOWERCASE = 1 << 2;

    /**
     * 대소문자 타입
     */
    public static final int LETTER_TYPE_BOTHCASE = 1 << 3;
}
