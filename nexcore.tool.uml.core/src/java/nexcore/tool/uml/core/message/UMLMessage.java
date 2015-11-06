/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.core.message;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.message</li>
 * <li>설 명 : UML도구에서 사용하는 모든 공통 메시시를 정의한 클래스</li>
 * <li>작성일 : 2009. 11. 12.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.core.message</li>
 * <li>설  명 : UMLMessage</li>
 * <li>작성일 : 2011. 9. 30.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public class UMLMessage {

    /********************************** LABEL *******************************************/
    /** 추가 */
    public static final String LABEL_ADD = Messages.getString("LABEL.ADD");//$NON-NLS-1$

    /** 전체 **/
    public static final String LABEL_ALL = Messages.getString("LABEL.ALL"); //$NON-NLS-1$

    /** 속성 */
    public static final String LABEL_ATTRIBUTE = Messages.getString("LABEL.ATTRIBUTE"); //$NON-NLS-1$

    /** 합계 : ${0} */
    public static final String LABEL_ATTRIBUTE_SUMMARY = Messages.getString("LABEL.ATTRIBUTE.SUMMARY"); //$NON-NLS-1$

    /** 취소 */
    public static final String LABEL_CANCEL = Messages.getString("LABEL.CANCEL"); //$NON-NLS-1$

    /** 일 **/
    public static final String LABEL_DAY = Messages.getString("LABEL.DAY"); //$NON-NLS-1$

    /** 기본값 */
    public static final String LABEL_DEFAULT_VALUE = Messages.getString("LABEL.DEFAULT.VALUE");//$NON-NLS-1$

    /** 삭제 */
    public static final String LABEL_DELETE = Messages.getString("LABEL.DELETE"); //$NON-NLS-1$
    
    /** 삭제 (&D) */
    public static final String LABEL_DELETE_WITH_SHORTCUT = Messages.getString("LABEL.DELETE.WITH.SHORTCUT"); //$NON-NLS-1$

    /** 저장(&S) */
    public static final String LABEL_SAVE = Messages.getString("LABEL.SAVE"); //$NON-NLS-1$
    
    /** 속성 추가 */
    public static final String LABEL_ADD_PROPERTY = Messages.getString("LABEL.ADD.PROPERTY"); //$NON-NLS-1$
    
    /** 오퍼레이션 추가 */
    public static final String LABEL_ADD_OPERATION = Messages.getString("LABEL.ADD.OPERATION"); //$NON-NLS-1$
    
    /** 열거 리터럴 추가 */
    public static final String LABEL_ADD_ENUMERATION_LITERAL = Messages.getString("LABEL.ADD.ENUMERATION.LITERAL"); //$NON-NLS-1$
    
    /** 탐색기에서 찾기 */
    public static final String LABEL_FIND_ELEMENT = Messages.getString("LABEL.FIND.ELEMENT"); //$NON-NLS-1$
    
    /** 위로 */
    public static final String LABEL_UPPER = Messages.getString("LABEL.UPPER"); //$NON-NLS-1$

    /** 아래로 */
    public static final String LABEL_DOWN = Messages.getString("LABEL.DOWN"); //$NON-NLS-1$

    /** 이름 */
    public static final String LABEL_NAME = Messages.getString("LABEL.NAME");//$NON-NLS-1$

    /** 제약조건 */
    public static final String LABEL_CONSTRAINT = Messages.getString("LABEL.CONSTRAINT");//$NON-NLS-1$

    /** 가시성 */
    public static final String LABEL_VISIBILITY = Messages.getString("LABEL.VISIBILITY");//$NON-NLS-1$

    /** 기준 */
    public static final String LABEL_STANDARD = Messages.getString("LABEL.STANDARD");//$NON-NLS-1$

    /** 추상 */
    public static final String LABEL_ABSTRACT = Messages.getString("LABEL.ABSTRACT");//$NON-NLS-1$

    /** 리프 */
    public static final String LABEL_LEAF = Messages.getString("LABEL.LEAF");//$NON-NLS-1$

    /** public */
    public static final String LABEL_PUBLIC = Messages.getString("LABEL.PUBLIC"); //$NON-NLS-1$

    /** private */
    public static final String LABEL_PRIVATE = Messages.getString("LABEL.PRIVATE"); //$NON-NLS-1$

    /** protected */
    public static final String LABEL_PROTECTED = Messages.getString("LABEL.PROTECTED"); //$NON-NLS-1$

    /** package */
    public static final String LABEL_PACKAGE = Messages.getString("LABEL.PACKAGE"); //$NON-NLS-1$

    /** New */
    public static final String LABEL_NEW = Messages.getString("LABEL.NEW"); //$NON-NLS-1$

    /** Nexcore UML 모델 생성 */
    public static final String LABEL_CREATE_UML_MODEL = Messages.getString("LABEL.CREATEUMLMODEL"); //$NON-NLS-1$

    /** Nexcore UML 모델 */
    public static final String LABEL_CREATE_UML_MODEL_DEFAULTNAME = Messages.getString("LABEL.CREATEUMLMODELDEFAULTNAME"); //$NON-NLS-1$

    /** Nexcore UML 모델명 */
    public static final String LABEL_CREATE_UML_MODEL_NAME = Messages.getString("LABEL.CREATEUMLMODELNAME"); //$NON-NLS-1$

    /** 새 Nexcore UML 프로젝트를 생성합니다. */
    public static final String LABEL_CREATE_UML_PROJECT = Messages.getString("LABEL.CREATEUMLPROJECT"); //$NON-NLS-1$

    /** 새 UML 모델 */
    public static final String LABEL_NEW_UML_MODEL = Messages.getString("LABEL.NEWUMLMODEL"); //$NON-NLS-1$

    /** 새 UML 모델링 프로젝트 */
    public static final String LABEL_NEW_UML_PROJECT = Messages.getString("LABEL.NEWUMLPROJECT"); //$NON-NLS-1$

    /** 프로젝트 탐색기 */
    public static final String LABEL_PROJECT_EXPLORER = Messages.getString("LABEL.PROJECT_EXPLORER"); //$NON-NLS-1$

    /** 모델 */
    public static final String LABEL_MODEL = Messages.getString("LABEL.MODEL"); //$NON-NLS-1$

    /** 다이어그램 */
    public static final String LABEL_DIAGRAM = Messages.getString("LABEL.DIAGRAM"); //$NON-NLS-1$

    /** 부모-자식 관계 */
    public static final String LABEL_CHILD_PARENT = Messages.getString("LABEL.CHILD.PARENT"); //$NON-NLS-1$

    /** 용어집 */
    public static final String LABEL_GLOSSARY = Messages.getString("LABEL.GLOSSARY"); //$NON-NLS-1$

    /** 새 속성 삽입 */
    public static final String LABEL_ADD_NEW_ATTRIBUTE = Messages.getString("LABEL.ADD_NEW_ATTRIBUTE");//$NON-NLS-1$

    /** 새 오퍼레이션 삽입 */
    public static final String LABEL_ADD_NEW_OPERATION = Messages.getString("LABEL.ADD_NEW_OPERATION");//$NON-NLS-1$

    /** 모델에서 삭제 */
    public static final String LABEL_DELETE_FROM_MODEL = Messages.getString("LABEL.DELETE.FROM.MODEL");//$NON-NLS-1$

    /** 유형 */
    public static final String LABEL_TYPE = Messages.getString("LABEL.TYPE");//$NON-NLS-1$

    /** 유형설정 */
    public static final String LABEL_TYPE_SET = Messages.getString("LABEL.TYPESETTING");//$NON-NLS-1$

    /** 정적 */
    public static final String LABEL_STATIC = Messages.getString("LABEL.STATIC");//$NON-NLS-1$

    /** 다중성 */
    public static final String LABEL_MULTIPLICITY = Messages.getString("LABEL.MULTIPLICITY");//$NON-NLS-1$

    /** 소유한매개변수 */
    public static final String LABEL_OWNED_PARAMETER = Messages.getString("LABEL.OWNED_PARAMETER");//$NON-NLS-1$
     
    /** 한글명 prefix 적용 */
    public static final String LABEL_APPLY_KOREAN_PREFIX = Messages.getString("LABEL.APPLY.KOREAN.PREFIX");//$NON-NLS-1$
    
    /** UML 프로파일 가져오기 */
    public static final String LABEL_IMPORT_UMLPROFILE = Messages.getString("LABEL.IMPORT.UMLPROFILE");//$NON-NLS-1$
    
    /** IO를 가져올 패키지명의 Prefix */
    public static final String LABEL_IO_IMPORT_PACKAGE_PREFIX = Messages.getString("LABEL.IO.IMPORT.PACKAGE.PREFIX"); //$NON-NLS-1$;

    /** IO 가져오기 */
    public static final String LABEL_IO_IMPORT = Messages.getString("LABEL.IO.IMPORT");//$NON-NLS-1$

    /** IO 가져오기 성공 */
    public static final String LABEL_IO_IMPORT_SUCCESS = Messages.getString("LABEL.IO.IMPORT.SUCCESS");//$NON-NLS-1$

    /** IO 가져오기 에러 */
    public static final String LABEL_IO_IMPORT_ERROR = Messages.getString("LABEL.IO.IMPORT.ERROR");//$NON-NLS-1$
    
    /** IO 가져오기 에러 파일명 */
    public static final String LABEL_IO_IMPORT_ERROR_FILENAME = Messages.getString("LABEL.IO.IMPORT.ERROR.FILENAME");//$NON-NLS-1$

    /** UML 프로파일 */
    public static final String LABEL_UMLPROFILE = Messages.getString("LABEL.UMLPROFILE");//$NON-NLS-1$

    /** 파일 선택: */
    public static final String LABEL_SELECT_FILE = Messages.getString("LABEL.SELECT.FILE");//$NON-NLS-1$

    /** 선택한 UML 프로파일명: */
    public static final String LABEL_SELECTED_UMLPROFILE = Messages.getString("LABEL.SELECTED.UMLPROFILE");//$NON-NLS-1$

    /** 오퍼레이션 */
    public static final String LABEL_OPERATION = Messages.getString("LABEL.OPERATION");//$NON-NLS-1$

    /** 키워드 */
    public static final String LABEL_KEYWORD = Messages.getString("LABEL.KEYWORD");//$NON-NLS-1$

    /** 적용된 스테레오타입 */
    public static final String LABEL_APPLIED_STEREOTYPE = Messages.getString("LABEL.APPLIED.STEREOTYPE");//$NON-NLS-1$

    /** 일반 정보 */
    public static final String LABEL_GENERAL_INFORMATION = Messages.getString("LABEL.GENERAL.INFORMATION");//$NON-NLS-1$

    /** 위치 : */
    public static final String LABEL_FILE_SIZE = Messages.getString("LABEL.FILE.SIZE");//$NON-NLS-1$

    /** 크기 : */
    public static final String LABEL_FILE_LOCATION = Messages.getString("LABEL.FILE.LOCATION");//$NON-NLS-1$

    /** 마지막 수정 날짜 : */
    public static final String LABEL_FILE_LASTMODIFIEDDATE = Messages.getString("LABEL.FILE.LASTMODIFIEDDATE");//$NON-NLS-1$

    /** 개요 */
    public static final String LABEL_OVERVIEW = Messages.getString("LABEL.OVERVIEW");//$NON-NLS-1$

    /** 상세 */
    public static final String LABEL_DETAILS = Messages.getString("LABEL.DETAILS");//$NON-NLS-1$

    /** 적용된 프로파일 목록 */
    public static final String LABEL_UMLPROFILE_LIST = Messages.getString("LABEL.UMLPROFILE.LIST");//$NON-NLS-1$

    /** 프로파일명 */
    public static final String LABEL_UMLPROFILE_NAME = Messages.getString("LABEL.UMLPROFILE.NAME");//$NON-NLS-1$

    /** 프로파일 경로 */
    public static final String LABEL_UMLPROFILE_PATH = Messages.getString("LABEL.UMLPROFILE.PATH");//$NON-NLS-1$

    /** 프로파일 추가 */
    public static final String LABEL_ADD_UMLPROFILE = Messages.getString("LABEL.ADD.UMLPROFILE");//$NON-NLS-1$

    /** 설명 */
    public static final String LABEL_DESCRIPTION = Messages.getString("LABEL.DESCRIPTION");//$NON-NLS-1$

    /** 스테레오타입 적용 */
    public static final String LABEL_APPLY_STEREOTYPE = Messages.getString("LABEL.APPLY.STEREOTYPE");//$NON-NLS-1$

    /** 스테레오타입 적용 해제 */
    public static final String LABEL_UNAPPLY_STEREOTYPE = Messages.getString("LABEL.UNAPPLY.STEREOTYPE");//$NON-NLS-1$

    /** 스테레오타입 특성 */
    public static final String LABEL_STEREOTYPE_PROPERTY = Messages.getString("LABEL.STEREOTYPE.PROPERTY");//$NON-NLS-1$

    /** 선택된 항목 */
    public static final String LABEL_SELECTED_ITEM = Messages.getString("LABEL.SELECTED.ITEM");//$NON-NLS-1$

    /** 특성 */
    public static final String LABEL_PROPERTY = Messages.getString("LABEL.PROPERTY");//$NON-NLS-1$

    /** 값 */
    public static final String LABEL_VALUE = Messages.getString("LABEL.VALUE");//$NON-NLS-1$

    /** 대체 */
    public static final String LABEL_SUBSTITUTION = Messages.getString("LABEL.SUBSTITUTION");//$NON-NLS-1$

    /** 규정자 */
    public static final String LABEL_QUALIFIER = Messages.getString("LABEL.QUALIFIER");//$NON-NLS-1$

    /** 정렬됨 */
    public static final String LABEL_ORDERED = Messages.getString("LABEL.ORDERED");//$NON-NLS-1$

    /** 고유 */
    public static final String LABEL_UNIQUE = Messages.getString("LABEL.UNIQUE");//$NON-NLS-1$

    /** 조회 */
    public static final String LABEL_QUERY = Messages.getString("LABEL.QUERY");//$NON-NLS-1$

    /** 값 입력 */
    public static final String LABEL_INPUT_VALUE = Messages.getString("LABEL.INPUT.VALUE");//$NON-NLS-1$

    /** 색인 */
    public static final String LABEL_INDEX = Messages.getString("LABEL.INDEX");//$NON-NLS-1$

    /** 파생 */
    public static final String LABEL_DERIVED = Messages.getString("LABEL.DERIVED");//$NON-NLS-1$

    /** 파생된 결합 */
    public static final String LABEL_DERIVED_UNION = Messages.getString("LABEL.DERIVED.UNION");//$NON-NLS-1$

    /** 읽기 전용 */
    public static final String LABEL_READ_ONLY = Messages.getString("LABEL.READ.ONLY");//$NON-NLS-1$

    /** 스테레오타입 */
    public static final String LABEL_STEREOTYPE = Messages.getString("LABEL.STEREOTYPE");//$NON-NLS-1$

    /** 프로파일 */
    public static final String LABEL_PROFILE = Messages.getString("LABEL.PROFILE");//$NON-NLS-1$

    /** 필수 */
    public static final String LABEL_ESSENTIALITY = Messages.getString("LABEL.ESSENTIALITY");//$NON-NLS-1$

    /** 유형 선택 */
    public static final String LABEL_SELECT_TYPE = Messages.getString("LABEL.SELECT.TYPE");//$NON-NLS-1$

    /** 리턴 유형 */
    public static final String LABEL_RETURNTYPE = Messages.getString("LABEL.RETURNTYPE");//$NON-NLS-1$

    /** 리턴 유형 설정 */
    public static final String LABEL_SETRETURNTYPE = Messages.getString("LABEL.SET.RETURNTYPE");//$NON-NLS-1$

    /** 요소 선택(? = 임의 문자, * = 임의 문자열): */
    public static final String LABEL_SEARCH_CONTENTS = Messages.getString("LABEL.SEARCH.CONTENTS");//$NON-NLS-1$

    /** 유형의 요소 선택 */
    public static final String LABEL_TYPESEARCH = Messages.getString("LABEL.TYPESEARCH");//$NON-NLS-1$

    /** 유형 선택 해제 */
    public static final String LABEL_TYPE_CANCEL = Messages.getString("LABEL.TYPE.CANCEL");//$NON-NLS-1$

    /** 방향 */
    public static final String LABEL_DIRECTION = Messages.getString("LABEL.DIRECTION");//$NON-NLS-1$

    /** 고유함 */
    public static final String LABEL_IS_UNIQUE = Messages.getString("LABEL.IS.UNIQUE");//$NON-NLS-1$

    /** 매개변수 */
    public static final String LABEL_PARAMETER = Messages.getString("LABEL.PARAMETER");//$NON-NLS-1$

    /** 매개변수 */
    public static final String LABEL_PARAMETER_TEXT = Messages.getString("LABEL.PARAMETER_TEXT");//$NON-NLS-1$

    /** 입력 */
    public static final String LABEL_IN = Messages.getString("LABEL.IN");//$NON-NLS-1$

    /** 입출력 */
    public static final String LABEL_IN_OUT = Messages.getString("LABEL.IN.OUT");//$NON-NLS-1$

    /** 출력 */
    public static final String LABEL_OUT = Messages.getString("LABEL.OUT");//$NON-NLS-1$

    /** 리턴 */
    public static final String LABEL_RETURN = Messages.getString("LABEL.RETURN");//$NON-NLS-1$

    /** 규정된 이름 */
    public static final String LABEL_QUALIFIED_NAME = Messages.getString("LABEL.QUALIFIED.NAME");//$NON-NLS-1$

    /** 탐색 */
    public static final String LABEL_NAVIGATE = Messages.getString("LABEL.NAVIGATE");//$NON-NLS-1$

    /** 제거 */
    public static final String LABEL_REMOVE = Messages.getString("LABEL.REMOVE");//$NON-NLS-1$

    /** 리터럴 */
    public static final String LABEL_LITERAL = Messages.getString("LABEL.LITERAL");//$NON-NLS-1$

    /** 항목 선택 */
    public static final String LABEL_CHECK_ITEM = Messages.getString("LABEL.CHECK.ITEM");//$NON-NLS-1$

    /** 문서 */
    public static final String LABEL_DOCUMENT_INFORMATION = Messages.getString("LABEL.DOCUMENT.INFORMATION");//$NON-NLS-1$

    /** 프로젝트 단계 */
    public static final String LABEL_PROJECT_STEPS = Messages.getString("LABEL.PROJECT.STEPS");//$NON-NLS-1$

    /** 프로젝트 코드 */
    public static final String LABEL_PROJECT_CODE = Messages.getString("LABEL.PROJECT.CODE");//$NON-NLS-1$

    /** 프로젝트 명 */
    public static final String LABEL_PROJECT_NAME = Messages.getString("LABEL.PROJECT.NAME");//$NON-NLS-1$

    /** 프로젝트 정보 */
    public static final String LABEL_PROJECT_INFORMATION = Messages.getString("LABEL.PROJECT.INFORMATION");//$NON-NLS-1$

    /** 모델 라이브러리 */
    public static final String LABEL_MODEL_LIBRARY = Messages.getString("LABEL.MODEL.LIBRARY");//$NON-NLS-1$

    /** 포함된 엘레먼트 수 */
    public static final String LABEL_COUNT_ELEMENT_INCLUDE = Messages.getString("LABEL.COUNT.ELEMENT.INCLUDE");//$NON-NLS-1$

    /** 기타 */
    public static final String LABEL_ETC = Messages.getString("LABEL.ETC");//$NON-NLS-1$

    /** 총 합 */
    public static final String LABEL_TOTAL = Messages.getString("LABEL.TOTAL");//$NON-NLS-1$

    /** 레벨 */
    public static final String LABEL_LEVEL = Messages.getString("LABEL.LEVEL");//$NON-NLS-1$

    /** 필터 */
    public static final String LABEL_PREFERENCE_PROJECT_FILTER = Messages.getString("LABEL.PREFERENCE.PROJECT.FILTER"); //$NON-NLS-1$

    /** 사용가능한 UML 요소 */
    public static final String LABEL_PREFERENCE_PROJECT_ALLELEMENT = Messages.getString("LABEL.PREFERENCE.PROJECT.ALLELEMENT"); //$NON-NLS-1$

    /** 필터된 UML 요소 */
    public static final String LABEL_PREFERENCE_PROJECT_FILTERED_ELEMENT = Messages.getString("LABEL.PREFERENCE.PROJECT.FILTEREDELEMENT"); //$NON-NLS-1$

    /** 로즈 클래식 */
    public static final String LABEL_PREFERENCE_APPEARANCE_ROSE_CLASSIC = Messages.getString("LABEL.PREFERENCE.APPEARANCE.ROSE.CLASSIC");//$NON-NLS-1$

    /** 모양 스타일 */
    public static final String LABEL_PREFERENCE_APPEARANCE_APPEARANCE_STYLE = Messages.getString("LABEL.PREFERENCE.APPEARANCE.APPEARANCE.STYLE"); //$NON-NLS-1$

    /** 색상 및 글꼴 */
    public static final String LABEL_PREFERENCE_APPEARANCE_COLORS_AND_FONTS = Messages.getString("LABEL.PREFERENCE.APPEARANCE.COLORS.AND.FONTS"); //$NON-NLS-1$

    /** 스타일 */
    public static final String LABEL_PREFERENCE_APPEARANCE_STYLE = Messages.getString("LABEL.PREFERENCE.APPEARANCE.STYLE"); //$NON-NLS-1$

    /** 변경 */
    public static final String LABEL_PREFERENCE_APPEARANC_CHANGE = Messages.getString("LABEL.PREFERENCE.APPEARANCE.CHANGE"); //$NON-NLS-1$

    /** 기본 글꼴 */
    public static final String LABEL_PREFERENCE_APPEARANCE_DEFAULT_FONT = Messages.getString("LABEL.PREFERENCE.APPEARANCE.DEFAULT.FONT"); //$NON-NLS-1$

    /** 글꼴 색상 */
    public static final String LABEL_PREFERENCE_APPEARANCE_FONT_COLOR = Messages.getString("LABEL.PREFERENCE.APPEARANCE.FONT.COLOR"); //$NON-NLS-1$

    /** 채우기 색상 */
    public static final String LABEL_PREFERENCE_APPEARANCE_FILL_COLOR = Messages.getString("LABEL.PREFERENCE.APPEARANCE.FILL.COLOR"); //$NON-NLS-1$

    /** 선 색상 */
    public static final String LABEL_PREFERENCE_APPEARANCE_LINE_COLOR = Messages.getString("LABEL.PREFERENCE.APPEARANCE.LINE.COLOR"); //$NON-NLS-1$

    /** 노트 채우기 색상 */
    public static final String LABEL_PREFERENCE_APPEARANCE_NOTE_FILL_COLOR = Messages.getString("LABEL.PREFERENCE.APPEARANCE.NOTE.FILL.COLOR"); //$NON-NLS-1$

    /** 노트 선 색상 */
    public static final String LABEL_PREFERENCE_APPEARANCE_NOTE_LINE_COLOR = Messages.getString("LABEL.PREFERENCE.APPEARANCE.NOTE.LINE.COLOR"); //$NON-NLS-1$

    /** 주석 채우기 색상 */
    public static final String LABEL_PREFERENCE_APPEARANCE_COMMENT_FILL_COLOR = Messages.getString("LABEL.PREFERENCE.APPEARANCE.COMMENT.FILL.COLOR"); //$NON-NLS-1$

    /** 주석 행 색상 */
    public static final String LABEL_PREFERENCE_APPEARANCE_COMMENT_LINE_COLOR = Messages.getString("LABEL.PREFERENCE.APPEARANCE.COMMENT.LINE.COLOR"); //$NON-NLS-1$

    /** 제한조건 채우기 색상 */
    public static final String LABEL_PREFERENCE_APPEARANCE_CONSTRAINT_FILL_COLOR = Messages.getString("LABEL.PREFERENCE.APPEARANCE.CONSTRAINT.FILL.COLOR"); //$NON-NLS-1$

    /** 제한조건 행 색상 */
    public static final String LABEL_PREFERENCE_APPEARANCE_CONSTRAINT_LINE_COLOR = Messages.getString("LABEL.PREFERENCE.APPEARANCE.CONSTRAINT.LINE.COLOR"); //$NON-NLS-1$

    /** 경고 */
    public static final String LABEL_WARNING = Messages.getString("LABEL.WARNING"); //$NON-NLS-1$

    /** 확인 */
    public static final String LABEL_OK = Messages.getString("LABEL.OK"); //$NON-NLS-1$

    /** 분류 */
    public static final String LABEL_CATEGORY = Messages.getString("LABEL.CATEGORY"); //$NON-NLS-1$

    /** 분류 설정 */
    public static final String LABEL_CATEGORY_CONFIG = Messages.getString("LABEL.CATEGORY.CONFIG"); //$NON-NLS-1$

    /** 분류 추가 */
    public static final String LABEL_CATEGORY_ADD = Messages.getString("LABEL.CATEGORY.ADD"); //$NON-NLS-1$

    /** 분류 설정 */
    public static final String LABEL_CATEGORY_EDIT = Messages.getString("LABEL.CATEGORY.EDIT"); //$NON-NLS-1$

    /** 라이브러리 명 */
    public static final String LABEL_LIBRARY_NAME = Messages.getString("LABEL.LIBRARY.NAME"); //$NON-NLS-1$

    /** 라이브러리 경로 */
    public static final String LABEL_LIBRARY_PATH = Messages.getString("LABEL.LIBRARY.PATH"); //$NON-NLS-1$

    /** 바이트 */
    public static final String LABEL_BYTE = Messages.getString("LABEL.BYTE"); //$NON-NLS-1$

    /** 서명 */
    public static final String LABEL_SIGNATURE = Messages.getString("LABEL.SIGNATURE"); //$NON-NLS-1$

    /** 성공 */
    public static final String LABEL_SUCCESS = Messages.getString("LABEL.SUCCESS"); //$NON-NLS-1$

    /** 에러 */
    public static final String LABEL_ERROR = Messages.getString("LABEL.ERROR"); //$NON-NLS-1$

    /** 확인 */
    public static final String LABEL_CONFIRM = Messages.getString("LABEL.CONFIRM"); //$NON-NLS-1$

    /** 레이블 */
    public static final String LABEL_LABEL = Messages.getString("LABEL.LABEL");//$NON-NLS-1$

    /** 집계 */
    public static final String LABEL_AGGREGATION = Messages.getString("LABEL.AGGREGATION");//$NON-NLS-1$

    /** 역할 */
    public static final String LABEL_ROLE = Messages.getString("LABEL.ROLE");//$NON-NLS-1$

    /** 탐색 가능 여부 */
    public static final String LABEL_NAVIGABLE = Messages.getString("LABEL.NAVIGABLE");//$NON-NLS-1$

    /** 유형 지정하지 않음 */
    public static final String LABEL_NO_TYPE = Messages.getString("LABEL.NO.TYPE");//$NON-NLS-1$

    /** 클래스 작성 */
    public static final String LABEL_CREATE_CLASS = Messages.getString("LABEL.CREATE.CLASS");//$NON-NLS-1$

    /** 라이프라인 추가 */
    public static final String LABEL_ADD_COVERED_LIFELINE = Messages.getString("LABEL.ADD.COVERED.LIFELFE");//$NON-NLS-1$

    /** 라이프라인 제거 */
    public static final String LABEL_REMOVE_COVERED_LIFELINE = Messages.getString("LABEL.REMOVE.CORVERED.LIFELINE");//$NON-NLS-1$

    /** 누락된 라이프라인 추가 */
    public static final String LABEL_INSERT_COVERED_LIFELINE = Messages.getString("LABEL.INSERT.COVERED.LIFELFE");//$NON-NLS-1$    

    /** 추가 할 라이프라인이 없습니다. */
    public static final String LABEL_NO_ADD_COVERED_LIFELINE = Messages.getString("LABEL.NO.ADD.COVERED.LIFELINE");//$NON-NLS-1$    

    /** 관련 메시지 */
    public static final String LABEL_RELATED_MESSAGE = Messages.getString("LABEL.RELATED.MESSAGE");//$NON-NLS-1$

    /** 복사_ */
    public static final String LABEL_COPYOF = Messages.getString("LABEL.COPYOF"); //$NON-NLS-1$

    /** 복사 */
    public static final String LABEL_COPY = Messages.getString("LABEL.COPY"); //$NON-NLS-1$
    
    /** 잘라내기 */
    public static final String LABEL_CUT = Messages.getString("LABEL.CUT"); //$NON-NLS-1$
    
    /** 붙여넣기 */
    public static final String LABEL_PASTE = Messages.getString("LABEL.PASTE"); //$NON-NLS-1$
    
    /** 항목 표시  */
    public static final String LABEL_COMPARTMENT_VISIBILITY = Messages.getString("LABEL.COMPARTMENT.VISIBILITY"); //$NON-NLS-1$
    
    /** 이름만 표시 */
    public static final String LABEL_SHOW_NAME_ONLY = Messages.getString("LABEL.SHOW.NAME.ONLY"); //$NON-NLS-1$
    
    /** 유형 표시 */
    public static final String LABEL_SHOW_TYPE = Messages.getString("LABEL.SHOW.TYPE"); //$NON-NLS-1$
    
    /** 매개변수 표시 */
    public static final String LABEL_SHOW_PARAMETER = Messages.getString("LABEL.SHOW.PARAMETER"); //$NON-NLS-1$
    
    /** 특성 기본 값 */
    public static final String LABEL_PROPERTY_DEFAULT_VALUE = Messages.getString("LABEL.PROPERTY.DEFAULT.VALUE"); //$NON-NLS-1$

    /** 특성 기본 유형 */
    public static final String LABEL_PROPERTY_DEFAULT_TYPE = Messages.getString("LABEL.PROPERTY.DEFAULT.TYPE"); //$NON-NLS-1$

    /** 연결 라벨 표시 유무 */
    public static final String LABEL_VISIBILITY_ASSOCIATION_LABEL = Messages.getString("LABEL.VISIBILITY.ASSOCIATION.LABEL"); //$NON-NLS-1$

    /** 연결 라벨 */
    public static final String LABEL_ASSOCIATION_LABEL = Messages.getString("LABEL.ASSOCIATION.LABEL");//$NON-NLS-1$

    /** 역할 이름 라벨 */
    public static final String LABEL_ROLE_NAME_LABEL = Messages.getString("LABEL.ROLE.NAME.LABEL");//$NON-NLS-1$

    /** 연결선 이름 표시 유무 */
    public static final String LABEL_VISIBILITY_CONNECTION_NODE_NAME = Messages.getString("LABEL.VISIBILITY.CONNECTION.NODE.NAME"); //$NON-NLS-1$

    /** 이름 표시 방법 */
    public static final String LABEL_NAME_DISPLAY_METHOD = Messages.getString("LABEL.NAME.DISPLAY.METHOD"); //$NON-NLS-1$

    /** 아이콘 */
    public static final String LABEL_ICON = Messages.getString("LABEL.ICON"); //$NON-NLS-1$

    /** 텍스트 */
    public static final String LABEL_TEXT = Messages.getString("LABEL.TEXT"); //$NON-NLS-1$

    /** DND시 연결 표시 */
    public static final String LABEL_DISPLAY_CONNECTION_WHILE_DND = Messages.getString("LABEL.DISPLAY.CONNECTION.WHILE.DND"); //$NON-NLS-1$

    /** Compartment 크기 */
    public static final String LABEL_COMPARTMENT_SIZE = Messages.getString("LABEL.COMPARTMENT.SIZE"); //$NON-NLS-1$

    /** FIGURE 크기 */
    public static final String LABEL_FIGURE_SIZE = Messages.getString("LABEL.FIGURE.SIZE"); //$NON-NLS-1$

    /** 내보내기할 ${0} 선택 */
    public static final String LABEL_SELECT_TO_EXPORT_TO = Messages.getString("LABEL.SELECT.TO.EXPORT.TO"); //$NON-NLS-1$

    /** 내보낼 파일명 */
    public static final String LABEL_FILENAME_TO_EXPORT_TO = Messages.getString("LABEL.FILENAME.TO.EXPORT.TO"); //$NON-NLS-1$

    /** 경로 찾기 */
    public static final String LABEL_PATH_FIND = Messages.getString("LABEL.PATH.FIND"); //$NON-NLS-1$

    /** 클래스 리스트를 엑셀로 내보내기 */
    public static final String LABEL_EXPORT_CLASS_LIST_TO_XLS = Messages.getString("LABEL.EXPORT.CLASS.LIST.TO.XLS"); //$NON-NLS-1$

    /** 패키지 리스트를 엑셀로 내보내기 */
    public static final String LABEL_EXPORT_PACKAGE_LIST_TO_XLS = Messages.getString("LABEL.EXPORT.PACKAGE.LIST.TO.XLS"); //$NON-NLS-1$

    /** 유즈케이스 리스트를 엑셀로 내보내기 */
    public static final String LABEL_EXPORT_USECASE_LIST_TO_XLS = Messages.getString("LABEL.EXPORT.USECASE.LIST.TO.XLS"); //$NON-NLS-1$

    /** 클래스 명세서를 워드로 내보내기 */
    public static final String LABEL_EXPORT_CLASS_DEFINITION_TO_DOC = Messages.getString("LABEL.EXPORT.CLASS.DEFINITION.TO.DOC"); //$NON-NLS-1$

    /** 컴포넌트 명세서를 워드로 내보내기 */
    public static final String LABEL_EXPORT_COMPONENT_DEFINITION_TO_DOC = Messages.getString("LABEL.EXPORT.COMPONENT.DEFINITION.TO.DOC"); //$NON-NLS-1$

    /** 트랜잭션 설계서를 워드로 내보내기 */
    public static final String LABEL_EXPORT_TRANSACTION_ARCHITECTURE_TO_DOC = Messages.getString("LABEL.EXPORT.TRANSACTION.ARCHITECTURE.TO.DOC"); //$NON-NLS-1$

    /** 인터페이스 상호작용 명세서를 워드로 내보내기 */
    public static final String LABEL_EXPORT_INTERFACE_INTERACTION_TO_DOC = Messages.getString("LABEL.EXPORT.INTERFACE.INTERACTION.TO.DOC"); //$NON-NLS-1$

    /** 컴포넌트 아키텍처 정의서를 워드로 내보내기 */
    public static final String LABEL_EXPORT_COMPONENT_ARCHITECTURE_DEFINITION_TO_DOC = Messages.getString("LABEL.EXPORT.COMPONENT.ARCHITECTURE.DEFINITION.TO.DOC"); //$NON-NLS-1$

    /** 컴포넌트 설계서를 워드로 내보내기 */
    public static final String LABEL_EXPORT_COMPONENT_ARCHITECTURE_TO_DOC = Messages.getString("LABEL.EXPORT.COMPONENT.ARCHITECTURE.TO.DOC"); //$NON-NLS-1$

    /** 사용자 인터페이스 명세서를 워드로 내보내기 */
    public static final String LABEL_EXPORT_UI_DEFINITION_TO_DOC = Messages.getString("LABEL.EXPORT.UI.DEFINITION.TO.DOC"); //$NON-NLS-1$
    
    /** 사용자 인터페이스 설계서를 워드로 내보내기 */
    public static final String LABEL_EXPORT_UI_ARCHITECTURE_TO_DOC = Messages.getString("LABEL.EXPORT.UI.ARCHITECTURE.TO.DOC"); //$NON-NLS-1$

    /** 유스케이스 모형 기술서를 워드로 내보내기 */
    public static final String LABEL_EXPORT_USECASE_DEFINITION_TO_DOC = Messages.getString("LABEL.EXPORT.USECASE.DEFINITION.TO.DOC"); //$NON-NLS-1$

    /** 유스케이스 설계서를 워드로 내보내기 */
    public static final String LABEL_EXPORT_USECASE_ARCHITECTURE_TO_DOC = Messages.getString("LABEL.EXPORT.USECASE.ARCHITECTURE.TO.DOC"); //$NON-NLS-1$

    /** 유스케이스 분석서를 워드로 내보내기 */
    public static final String LABEL_EXPORT_USECASE_ANALYSIS_SPECIFICATION_TO_DOC = Messages.getString("LABEL.EXPORT.USECASE.ANALYSIS.SPECIFICATION.TO.DOC"); //$NON-NLS-1$

    /** RM 유즈케이스 추적표를 엑셀로 내보내기 */
    public static final String LABEL_EXPORT_RM_USECASE_TRACE_MATRIX_TO_XLS = Messages.getString("LABEL.EXPORT.RM.USECASE.TRACE.MATRIX.TO.XLS"); //$NON-NLS-1$

    /** 유즈케이스 모형 기술서.xml */
    public static final String LABEL_USECASE_DEFINITION_XML = Messages.getString("LABEL.USECASE.DEFINITION.XML"); //$NON-NLS-1$

    /** 클래스 명세서.xml */
    public static final String LABEL_CLASS_DEFINITION_XML = Messages.getString("LABEL.CLASS.DEFINITION.XML"); //$NON-NLS-1$

    /** 유즈케이스 설계서.xml */
    public static final String LABEL_USECASE_ARCHITECTURE_XML = Messages.getString("LABEL.USECASE.ARCHITECTURE.XML"); //$NON-NLS-1$

    /** 유스케이스 분석서.xml */
    public static final String LABEL_USECASE_ANALYSIS_SPECIFICATION_XML = Messages.getString("LABEL.USECASE.ANALYSIS.SPECIFICATION.XML"); //$NON-NLS-1$

    /** 패키지 리스트.xls */
    public static final String LABEL_PACKAGE_LIST_XLS = Messages.getString("LABEL.PACKAGE.LIST.XLS"); //$NON-NLS-1$

    /** 클래스 리스트.xls */
    public static final String LABEL_CLASS_LIST_XLS = Messages.getString("LABEL.CLASS.LIST.XLS"); //$NON-NLS-1$

    /** 유즈케이스 리스트.xls */
    public static final String LABEL_USECASE_LIST_XLS = Messages.getString("LABEL.USECASE.LIST.XLS"); //$NON-NLS-1$

    /** RM 유즈케이스 추적표.xls */
    public static final String LABEL_RM_USECASE_TRACE_MATRIX_XLS = Messages.getString("LABEL.RM.USECASE.TRACE.MATRIX.XLS"); //$NON-NLS-1$

    /** 사용할 템플릿 */
    public static final String LABEL_USE_TEMPLATE = Messages.getString("LABEL.USE.TEMPLATE"); //$NON-NLS-1$

    /** 경로 찾기 */
    public static final String LABEL_FIND_PATH = Messages.getString("LABEL.FIND.PATH"); //$NON-NLS-1$

    /** 사용할 템플릿 찾기 */
    public static final String LABEL_FIND_TEMPLATE = Messages.getString("LABEL.FIND.TEMPLATE"); //$NON-NLS-1$

    /** UML 단편화 파일 디폴트 명 */
    public static final String LABEL_UML_FRAGMENT_DEFAULT_NAME = Messages.getString("LABEL.UML.FRAGMENT.DEFAULT.NAME"); //$NON-NLS-1$

    /** 파일 단편화 */
    public static final String LABEL_FILE_FRAGMENTATION = Messages.getString("LABEL.FILE.FRAGMENTATION"); //$NON-NLS-1$

    /** 단편화된 요소 */
    public static final String LABEL_FRAGMENTED_ELEMENT = Messages.getString("LABEL.ELEMENT.FRAGMENTED"); //$NON-NLS-1$

    /** 파일 단편화 설명 */
    public static final String LABEL_FILE_FRAGMENTATION_DESCRIPTION = Messages.getString("LABEL.FILE.FRAGMENTATION.DESCRIPTION"); //$NON-NLS-1$

    /** 파일 병합 */
    public static final String LABEL_FILE_DEFRAGMENTATION = Messages.getString("LABEL.FILE.DEFRAGMENTATION"); //$NON-NLS-1$

    /** 파일 병합 설명 */
    public static final String LABEL_FILE_DEFRAGMENTATION_DESCRIPTION = Messages.getString("LABEL.FILE.DEFRAGMENTATION.DESCRIPTION"); //$NON-NLS-1$

    /** 단편 파일 저장 */
    public static final String LABEL_FILE_FRAGMENT_SAVE = Messages.getString("LABEL.FILE.FRAGMENT.SAVE"); //$NON-NLS-1$

    /** 잘못된 URI */
    public static final String LABEL_ERROR_INVALID_URI = Messages.getString("LABEL.ERROR.INVALID.URI"); //$NON-NLS-1$

    /** 이미 존재하는 파일 */
    public static final String LABEL_ERROR_EXISTING_FILE = Messages.getString("LABEL.ERROR.EXISTING.FILE"); //$NON-NLS-1$

    /** 단편화 파일 경로: */
    public static final String LABEL_FILE_FRAGMENTATION_LOCATION = Messages.getString("LABEL.FILE.FRAGMENTATION.LOCATION"); //$NON-NLS-1$

    /** 로컬 경로에서 찾아보기... */
    public static final String LABEL_BROWSE_FILESYSTEM = Messages.getString("LABEL.BROWSE.FILESYSTEM"); //$NON-NLS-1$

    /** 작업공간에서 찾아보기... */
    public static final String LABEL_BROWSE_WORKSPACE = Messages.getString("LABEL.BROWSE.WORKSPACE"); //$NON-NLS-1$

    /** 컴포지트 */
    public static final String LABEL_COMPOSITE = Messages.getString("LABEL.COMPOSITE");//$NON-NLS-1$

    /** 공유 */
    public static final String LABEL_SHARED = Messages.getString("LABEL.SHARED");//$NON-NLS-1$

    /** 없음 */
    public static final String LABEL_NONE = Messages.getString("LABEL.NONE");//$NON-NLS-1$

    /** 오퍼레이션 이름 및 소유자 입력 */
    public static final String LABEL_CREATE_OPERATION = Messages.getString("LABEL.CREATE.OPERATION");//$NON-NLS-1$

    /** 오퍼레이션 이름 */
    public static final String LABEL_OPERATION_NAME = Messages.getString("LABEL.OPERATIONNAME");//$NON-NLS-1$

    /** 오퍼레이션 소유자 */
    public static final String LABEL_OPERATION_OWNER = Messages.getString("LABEL.OPERATIONOWNER");//$NON-NLS-1$

    /** 클래스 이름 */
    public static final String LABEL_CLASSNAME = Messages.getString("LABEL.CLASSNAME");//$NON-NLS-1$

    // 용어집 부분
    /** 용어 개수 : */
    public static final String LABEL_WORD_COUNT = Messages.getString("LABEL.WORD.COUNT"); //$NON-NLS-1$

    /** 영문 약어 : */
    public static final String LABEL_WORD_ABBREVIATION = Messages.getString("LABEL.WORD.ABBREVIATION"); //$NON-NLS-1$

    /** 영문명 : */
    public static final String LABEL_WORD_ENGLISH_NAME = Messages.getString("LABEL.WORD.ENGLISH.NAME"); //$NON-NLS-1$

    /** 용어명 */
    public static final String LABEL_WORD_NAME = Messages.getString("LABEL.WORD.NAME"); //$NON-NLS-1$

    /** 번호(NO.) : */
    public static final String LABEL_WORD_SECTION_NUMBER = Messages.getString("LABEL_WORD_SECTION_NUMBER"); //$NON-NLS-1$

    /** 영문 약어 */
    public static final String LABEL_ABBREVIATIONCOLUMN = Messages.getString("LABEL.ABBREVIATIONCOLUMN"); //$NON-NLS-1$

    /** 분류 */
    public static final String LABEL_CATEGORYCOLUMN = Messages.getString("LABEL.CATEGORYCOLUMN"); //$NON-NLS-1$

    /** 자식 용어 추가 */
    public static final String LABEL_CREATECHILD_MENU_ITEM = Messages.getString("LABEL.CREATECHILD.MENU.ITEM"); //$NON-NLS-1$

    /** 형제 용어 추가 */
    public static final String LABEL_CREATESIBLING_MENU_ITEM = Messages.getString("LABEL.CREATESIBLING.MENU.ITEM"); //$NON-NLS-1$

    /** 영문명 */
    public static final String LABEL_ENGLISHNAMECOLUMN = Messages.getString("LABEL.ENGLISHNAMECOLUMN"); //$NON-NLS-1$

    /** 내보내기 */
    public static final String LABEL_EXPORT = Messages.getString("LABEL.EXPORT"); //$NON-NLS-1$

    /** 용어추가 방식 */
    public static final String LABEL_GLOSSARY_IMPORT_OPTION = Messages.getString("LABEL.GLOSSARY.IMPORT.OPTION"); //$NON-NLS-1$

    /** 기존용어를 유지 */
    public static final String LABEL_GLOSSARY_IMPORT_OPTION_NONOVERWRITE = Messages.getString("LABEL.GLOSSARY.IMPORT.OPTION.NONOVERWRITE"); //$NON-NLS-1$

    /** 전체변경(Overwrite All) */
    public static final String LABEL_GLOSSARY_IMPORT_OPTION_OVERWRITEALL = Messages.getString("LABEL.GLOSSARY.IMPORT.OPTION.OVERWRITEALL"); //$NON-NLS-1$

    /** 가져오기 */
    public static final String LABEL_IMPORT = Messages.getString("LABEL.IMPORT"); //$NON-NLS-1$

    /** 값 입력 : */
    public static final String LABEL_ENTER_VALUE = Messages.getString("LABEL_ENTER_VALUE"); //$NON-NLS-1$

    /** 파일 충돌 */
    public static final String LABEL_FILE_CONFLICT = Messages.getString("LABEL.FILE.CONFLICT"); //$NON-NLS-1$

    /** 선택된 {0} 대상들 */
    public static final String LABEL_MULTIOBJECT_SELECTED = Messages.getString("LABEL.MULTIOBJECT.SELECTED"); //$NON-NLS-1$

    /** 선택된것이 없음 */
    public static final String LABEL_NOOBJECT_SELECTED = Messages.getString("LABEL_NOOBJECT_SELECTED"); //$NON-NLS-1$

    /** &새로 고침 */
    public static final String LABEL_REFRESH_VIEWER = Messages.getString("LABEL.REFRESH.VIEWER"); //$NON-NLS-1$

    /** 번호(No.) */
    public static final String LABEL_SECTION_COLUMN = Messages.getString("LABEL.SECTION.COLUMN"); //$NON-NLS-1$

    /** 선택 */
    public static final String LABEL_SELECTION = Messages.getString("LABEL.SELECTION"); //$NON-NLS-1$

    /** 모두 선택 */
    public static final String LABEL_SELECT_ALL = Messages.getString("LABEL.SELECT.ALL"); //$NON-NLS-1$

    /** 모두 선택 해제 */
    public static final String LABEL_UNSELECT_ALL = Messages.getString("LABEL.UNSELECT.ALL"); //$NON-NLS-1$

    /** 속성 뷰 보이기 */
    public static final String LABEL_SHOW_PROPERTY_VIEW = Messages.getString("LABEL.SHOW.PROPERTY.VIEW"); //$NON-NLS-1$

    /** 선택된 대상: {0} */
    public static final String LABEL_SINGLEOBJECT_SELECTED = Messages.getString("LABEL.SINGLEOBJECT.SELECTED"); //$NON-NLS-1$

    /** 파일 "{0}"에서 발생된 문제들 */
    public static final String LABEL_MODELERRORS_FROM_0 = Messages.getString("LABEL.MODELERRORS.FROM_0"); //$NON-NLS-1$

    /** 테이블 */
    public static final String LABEL_TABLE_PAGE = Messages.getString("LABEL.TABLE.PAGE"); //$NON-NLS-1$

    /** nexcore.tool.dm.glossary.editor */
    public static final String LABEL_PLUGIN_ID = Messages.getString("LABEL.PLUGIN.ID"); //$NON-NLS-1$

    /** LABEL.PREFERENCE.DIAGRAM.SEQUENCESEDIAGMRA.REPLY */
    public static final String LABEL_PREFERENCE_DIAGRAM_SEQUENCESEDIAGMRA_REPLY = Messages.getString("LABEL.PREFERENCE.DIAGRAM.SEQUENCESEDIAGMRA.REPLY"); //$NON-NLS-1$

    /** LABEL.PREFERENCE.DIAGRAM.SEQUENCESEDIAGMRA.LIFELINENAME */
    public static final String LABEL_PREFERENCE_DIAGRAM_SEQUENCESEDIAGMRA_LIFELINENAME = Messages.getString("LABEL.PREFERENCE.DIAGRAM.SEQUENCESEDIAGMRA.LIFELINENAME"); //$NON-NLS-1$

    /** LABEL.PREFERENCE.DIAGRAM.ACITIVTYDIAGMRA.CONNECTIONROUTER */
    public static final String LABEL_PREFERENCE_DIAGRAM_ACITIVTYDIAGMRAM_CONNECTIONROUTER = Messages.getString("LABEL.PREFERENCE.DIAGRAM.ACITIVTYDIAGMRAM.CONNECTIONROUTER"); //$NON-NLS-1$

    /** 환경설정에서 컴포넌트 명세서 기준 패키지명을 입력한 후 실행해주세요. */
    public static final String MESSAGE_REPORT_NO_COMPONENT_BASE_PACKAGE = Messages.getString("MESSAGE.REPORT.NO.COMPONENT.BASE.PACKAGE"); //$NON-NLS-1$

    /** Preference Page of 패키지 구조로 폴더와 단편파일 생성 여부 */
    public static final String LABEL_PREFERENCE_PROJECTEXPLORER_FRAGMENT_BASE_PACKAGE = Messages.getString("LABEL.PREFERENCE.PROJECTEXPLORER.FRAGMENT.BASE.PACKAGE"); //$NON-NLS-1$

    /** Preference Page of 묻지않고 항상 실행 */
    public static final String LABEL_PREFERENCE_PROJECTEXPLORER_FRAGMENT_ALWAYS_RUN = Messages.getString("LABEL.PREFERENCE.PROJECTEXPLORER.FRAGMENT.ALWAYS.RUN"); //$NON-NLS-1$

    /** 내용이 없을 때 "(해당사항 없음)" 표시 */
    public static final String LABEL_PREFERENCE_REPORT_PRINT_NO_CONTENTS = Messages.getString("LABEL.PREFERENCE.REPORT.PRINT.NO.CONTENTS"); //$NON-NLS-1$

    /** 속성의 초기값이 없을 때 "N/A" 표시 */
    public static final String LABEL_PREFERENCE_REPORT_PRINT_NA = Messages.getString("LABEL.PREFERENCE.REPORT.PRINT.NA"); //$NON-NLS-1$

    /** 오퍼레이션의 리턴 유형이 없을 때 "N/A" 표시 */
    public static final String LABEL_PREFERENCE_REPORT_PRINT_NA_RETURNTYPE = Messages.getString("LABEL.PREFERENCE.REPORT.PRINT.NA.RETURNTYPE"); //$NON-NLS-1$

    /** 오퍼레이션의 매개변수 번호 표시 */
    public static final String LABEL_PREFERENCE_REPORT_PRINT_PARAM_NO = Messages.getString("LABEL.PREFERENCE.REPORT.PRINT.PARAMETER.NUMBER"); //$NON-NLS-1$

    /** 다이어그램 이미지 테두리 출력 */
    public static final String LABEL_PREFERENCE_REPORT_PRINT_IMAGE_OUTLINE = Messages.getString("LABEL.PREFERENCE.REPORT.PRINT.IMAGE.OUTLINE"); //$NON-NLS-1$

    /** 문서 산출물 공통 */
    public static final String LABEL_PREFERENCE_REPORT_COMMON = Messages.getString("LABEL.PREFERENCE.REPORT.COMMON"); //$NON-NLS-1$

    /** 속성의 유형 표시 */
    public static final String LABEL_PREFERENCE_REPORT_TYPEPATH = Messages.getString("LABEL.PREFERENCE.REPORT.TYPEPATH"); //$NON-NLS-1$

    /** 속성의 유형 표시 - 경로 모두 표시 */
    public static final String LABEL_PREFERENCE_REPORT_SHOW_TYPE_PATH_ALL = Messages.getString("LABEL.PREFERENCE.REPORT.TYPE.PATH.ALL"); //$NON-NLS-1$

    /** 속성의 유형 표시 - 이름만 표시 */
    public static final String LABEL_PREFERENCE_REPORT_SHOW_TYPE_NAME_ONLY = Messages.getString("LABEL.PREFERENCE.REPORT.TYPE.NAME.ONLY"); //$NON-NLS-1$

    /** 컴포넌트 관련 */
    public static final String LABEL_PREFERENCE_REPORT_COMPONENT = Messages.getString("LABEL.PREFERENCE.REPORT.COMPONENT"); //$NON-NLS-1$

    /** 컴포넌트 명세서 기준 패키지명 */
    public static final String LABEL_PREFERENCE_REPORT_COMPONENT_BASE_PACKAGE = Messages.getString("LABEL.PREFERENCE.REPORT.COMPONENT.BASE.PACKAGE"); //$NON-NLS-1$

    /** 유스케이스 이름 */
    public static final String LABEL_USECASENAME = Messages.getString("LABEL.USECASENAME");//$NON-NLS-1$

    /** RM 유즈케이스 추적표 산출물 명 - 요구사항 */
    public static final String LABEL_RM_USECASE_TRACE_MATRIX_REQUIREMENT_NAME = Messages.getString("LABEL.RM.USECASE.TRACE.MATRIX.REQUIREMENT.NAME"); //$NON-NLS-1$

    /** RM 유즈케이스 추적표 산출물 명 - 요구사항 명세 */
    public static final String LABEL_RM_USECASE_TRACE_MATRIX_USECASE_NAME = Messages.getString("LABEL.RM.USECASE.TRACE.MATRIX.USECASE.NAME"); //$NON-NLS-1$

    /** 유스케이스 추적 매트릭스 생성 */
    public static final String LABEL_USECASE_TRACE_MATRIX_CREATION = Messages.getString("LABEL.USECASE.TRACE.MATRIX.CREATION"); //$NON-NLS-1$

    /********************************** LABEL_MODELSEARCH *******************************************/

    /** 액터 */
    public static final String LABEL_MODELSEARCH_ACTOR = Messages.getString("LABEL.MODELSEARCH.ACTOR");//$NON-NLS-1$

    /** 클래스 */
    public static final String LABEL_MODELSEARCH_CLASS = Messages.getString("LABEL.MODELSEARCH.CLASS");//$NON-NLS-1$

    /** 컴포넌트 */
    public static final String LABEL_MODELSEARCH_COMPONENT = Messages.getString("LABEL.MODELSEARCH.COMPONENT");//$NON-NLS-1$

    /** 인터페이스 */
    public static final String LABEL_MODELSEARCH_INTERFACE = Messages.getString("LABEL.MODELSEARCH.INTERFACE");//$NON-NLS-1$

    /** 패키지 */
    public static final String LABEL_MODELSEARCH_PACKAGE = Messages.getString("LABEL.MODELSEARCH.PACKAGE");//$NON-NLS-1$

    /** 유즈케이스 */
    public static final String LABEL_MODELSEARCH_USECASE = Messages.getString("LABEL.MODELSEARCH.USECASE");//$NON-NLS-1$

    /** 검색 문자열 입력 */
    public static final String LABEL_MODELSEARCH_LABELTEXT = Messages.getString("LABEL.MODELSEARCH.LABELTEXT");//$NON-NLS-1$

    /** 대소문자 구분 */
    public static final String LABEL_MODELSEARCH_CASESENSITIVE = Messages.getString("LABEL.MODELSEARCH.CASESENSITIVE");//$NON-NLS-1$

    /** 검색문자열=(* = 임의 문자열, ? = 임의 문자, \\ = 리터럴 이스케이프 : * ? \\ */
    public static final String LABEL_MODELSEARCH_STATUSLABEL = Messages.getString("LABEL.MODELSEARCH.STATUSLABEL");//$NON-NLS-1$

    /** 검색결과 */
    public static final String LABEL_MODELSEARCH_SEARCHRESULT = Messages.getString("LABEL.MODELSEARCH.SEARCHRESULT");//$NON-NLS-1$

    /** 대상선택 */
    public static final String LABEL_MODELSEARCH_SELECTOBJECT = Messages.getString("LABEL.MODELSEARCH.SELECTOBJECT");//$NON-NLS-1$

    /** 발생된 예외 */
    public static final String LABEL_OCCOURED_EXEPTION = Messages.getString("LABEL.OCCOURED.EXEPTION"); //$NON-NLS-1$

    /** UML 프로파일 적용 */
    public static final String LABEL_UML_PROFILE_APPLICATION = Messages.getString("LABEL.UML.PROFILE.APPLICATION"); //$NON-NLS-1$

    /** 문서 템플릿 */
    public static final String LABEL_DOCUMENT_TEMPLATE = Messages.getString("LABEL.DOCUMENT.TEMPLATE"); //$NON-NLS-1$

    /** RM 데이터 엑셀 파일 */
    public static final String LABEL_IMPORT_RM_DATA_EXCEL_FILE = Messages.getString("LABEL.IMPORT.RM_DATA.EXCEL.FILE"); //$NON-NLS-1$

    /** 선택한 RM 데이터 엑셀 파일명: */
    public static final String LABEL_SELECTED_RM_DATA_EXCEL_FILE = Messages.getString("LABEL.SELECTED.RM.DATA.EXCEL.FILE"); //$NON-NLS-1$

    /** RM 데이터 */
    public static final String LABEL_RMDATA = Messages.getString("LABEL.RMDATA"); //$NON-NLS-1$

    /** 아이디 */
    public static final String LABEL_ID = Messages.getString("LABEL.ID"); //$NON-NLS-1$

    /** 요구사항 선택 */
    public static final String LABEL_SELECT_REQUIREMENT = Messages.getString("LABEL.SELECT.REQUIREMENT"); //$NON-NLS-1$

    /** 요구사항 정보 없음 */
    public static final String LABEL_NO_REQUIREMENT_INFORMATION = Messages.getString("LABEL.NO.REQUIREMENT.INFORMATION"); //$NON-NLS-1$

    /** 위로 이동 */
    public static final String LABEL_MOVEUP = Messages.getString("LABEL.MOVEUP"); //$NON-NLS-1$

    /** 아래로 이동 */
    public static final String LABEL_MOVEDOWN = Messages.getString("LABEL.MOVEDOWN"); //$NON-NLS-1$

    /** 사전 조건 */
    public static final String LABEL_PRECONDITION = Messages.getString("LABEL.PRECONDITION"); //$NON-NLS-1$

    /** 사후 조건 */
    public static final String LABEL_POSTCONDITION = Messages.getString("LABEL.POSTCONDITION"); //$NON-NLS-1$

    /** 흐름 순서 */
    public static final String LABEL_FLOW_NUMBER = Messages.getString("LABEL.FLOW.NUMBER"); //$NON-NLS-1$

    /** 흐름 ID */
    public static final String LABEL_FLOW_ID = Messages.getString("LABEL.FLOW.ID"); //$NON-NLS-1$

    /** 흐름명 */
    public static final String LABEL_FLOW_NAME = Messages.getString("LABEL.FLOW.NAME"); //$NON-NLS-1$

    /** 흐름 개요 */
    public static final String LABEL_FLOW_OVERVIEW = Messages.getString("LABEL.FLOW.OVERVIEW"); //$NON-NLS-1$

    /** 흐름 설명 */
    public static final String LABEL_FLOW_DESCRIPTION = Messages.getString("LABEL.FLOW.DESCRIPTION"); //$NON-NLS-1$

    /** 서브 흐름 */
    public static final String LABEL_SUB_FLOW = Messages.getString("LABEL.SUB.FLOW"); //$NON-NLS-1$

    /** 예외 흐름 */
    public static final String LABEL_EXEPTION_FLOW = Messages.getString("LABEL.EXEPTION.FLOW"); //$NON-NLS-1$

    /** 기본 흐름 */
    public static final String LABEL_BASIC_FLOW = Messages.getString("LABEL.BASIC.FLOW"); //$NON-NLS-1$

    /** LABEL_CREATE_GLOSSARY_FOLDER */
    public static final String LABEL_CREATE_GLOSSARY_FOLDER = Messages.getString("LABEL.CREATE.FOLDER.GLOSSARY"); //$NON-NLS-1$

    /** LABEL_CREATE_PROFILE_FOLDER */
    public static final String LABEL_CREATE_PROFILE_FOLDER = Messages.getString("LABEL.CREATE.FOLDER.PROFILE"); //$NON-NLS-1$

    /** LABEL_CREATE_TEMPLATE_FOLDER */
    public static final String LABEL_CREATE_TEMPLATE_FOLDER = Messages.getString("LABEL.CREATE.FOLDER.TEMPLATE"); //$NON-NLS-1$

    /** LABEL_CREATE_RMDATA_FOLDER */
    public static final String LABEL_CREATE_RMDATA_FOLDER = Messages.getString("LABEL.CREATE.FOLDER.RMDATA"); //$NON-NLS-1$

    /** RM 요구사항 계층구조 시트명 - 요구사항 계층구조 */
    public static final String LABEL_RM_HIERARCHY_SHEETNAME = Messages.getString("LABEL.RM.HIERARCHY.SHEETNAME"); //$NON-NLS-1$

    /** RM 요구사항 목록 시트명 - 요구사항 목록 */
    public static final String LABEL_RM_REQUIREMENT_SHEETNAME = Messages.getString("LABEL.RM.REQUIREMENT.SHEETNAME"); //$NON-NLS-1$

    /** RM 요구사항 명세 목록 시트명 - 요구사항 명세 목록 */
    public static final String LABEL_RM_USECASE_SHEETNAME = Messages.getString("LABEL.RM.USECASE.SHEETNAME"); //$NON-NLS-1$

    /** RM 프로젝트 타입명 - 프로젝트 */
    public static final String LABEL_RM_PROJECT_TYPENAME = Messages.getString("LABEL.RM.PROJECT.TYPENAME"); //$NON-NLS-1$

    /** RM 데이터 없음 */
    public static final String LABEL_NO_RM_DATA = Messages.getString("LABEL.NO.RM.DATA"); //$NON-NLS-1$

    /** 연관된 RM 프로젝트 정보 */
    public static final String LABEL_RELATED_RM_PROJECT_INFORMATION = Messages.getString("LABEL.RELATED.RM.PROJECT.INFORMATION"); //$NON-NLS-1$

    /** 연관된 RM 프로젝트 명 */
    public static final String LABEL_RALATED_RM_PROJECT_NAME = Messages.getString("LABEL.RALATED.RM.PROJECT.NAME"); //$NON-NLS-1$

    /** 연관된 RM 프로젝트 ID */
    public static final String LABEL_RELATED_RM_PROJECT_ID = Messages.getString("LABEL.RELATED.RM.PROJECT.ID"); //$NON-NLS-1$

    /** 연관된 RM 프로젝트 선택 */
    public static final String LABEL_SELECT_THE_RELATED_RM_PROJECT = Messages.getString("LABEL.SELECT.THE.RELATED.RM.PROJECT"); //$NON-NLS-1$

    /** 모델 상세 - 모델 종류 : 일반 */
    public static final String LABEL_MODEL_TYPE_GENERAL = Messages.getString("LABEL.MODEL.TYPE.GENERAL"); //$NON-NLS-1$

    /** 모델 상세 - 모델 종류 : 유스케이스 */
    public static final String LABEL_MODEL_TYPE_USECASE = Messages.getString("LABEL.MODEL.TYPE.USECASE"); //$NON-NLS-1$

    /** 모델 상세 - 모델 종류 : 분석 */
    public static final String LABEL_MODEL_TYPE_ANALYSIS = Messages.getString("LABEL.MODEL.TYPE.ANALYSIS"); //$NON-NLS-1$

    /** 모델 상세 - 모델 종류 : 설계 */
    public static final String LABEL_MODEL_TYPE_DESIGN = Messages.getString("LABEL.MODEL.TYPE.DESIGN"); //$NON-NLS-1$

    /** 모델 상세 정보 */
    public static final String LABEL_MODEL_DETAIL_INFORMATION = Messages.getString("LABEL.MODEL.DETAIL.INFORMATION"); //$NON-NLS-1$

    /** 검색어 입력 : */
    public static final String LABEL_SEARCH_FILTER = Messages.getString("LABEL.SEARCH.FILTER"); //$NON-NLS-1$

    /** 프로젝트 정보 없음 */
    public static final String LABEL_NO_PROJECT_INFORMATION = Messages.getString("LABEL.NO.PROJECT.INFORMATION"); //$NON-NLS-1$

    /** 라이브러리 적용 */
    public static final String LABEL_LIBRARY_APPLICATION = Messages.getString("LABEL.LIBRARY.APPLICATION"); //$NON-NLS-1$

    /** 해당사항 없음 */
    public static final String LABEL_NO_APPLICABLE = Messages.getString("LABEL.NO.APPLICABLE"); //$NON-NLS-1$

    /** N/A */
    public static final String LABEL_NO_APPLICABLE_SHORT = Messages.getString("LABEL.NO.APPLICABLE.SHORT"); //$NON-NLS-1$

    /** 모델 종류 */
    public static final String LABEL_MODEL_TYPE = Messages.getString("LABEL.MODEL.TYPE"); //$NON-NLS-1$

    /** Boolean */
    public static final String LABEL_UPPER_BOOLEAN = Messages.getString("LABEL.UPPER.BOOLEAN"); //$NON-NLS-1$

    /** Integer */
    public static final String LABEL_UPPER_INTEGER = Messages.getString("LABEL.UPPER.INTEGER"); //$NON-NLS-1$

    /** string */
    public static final String LABEL_LOWWER_STRING = Messages.getString("LABEL.LOWWER.STRING"); //$NON-NLS-1$

    /** UnlimitedNatural */
    public static final String LABEL_UPPER_UNLIMITEDNATURAL = Messages.getString("LABEL.UPPER.UNLIMITEDNATURAL"); //$NON-NLS-1$

    /** 메세지의 리턴타입 표시 여부 */
    public static final String LABEL_PREFERENCE_DIAGRAM_SEQUENCESEDIAGMRA_MESSAGE_TYPE = Messages.getString("LABEL.PREFERENCE.DIAGRAM.SEQUENCESEDIAGMRA.MESSAGE.TYPE"); //$NON-NLS-1$

    /** 메세지의 파라미터 표시 여부 */
    public static final String LABEL_PREFERENCE_DIAGRAM_SEQUENCESEDIAGMRA_MESSAGE_PARAMETER = Messages.getString("LABEL.PREFERENCE.DIAGRAM.SEQUENCESEDIAGMRA.MESSAGE.PARAMETER"); //$NON-NLS-1$

    /** Reply 메시지 이름 표시 */
    public static final String LABEL_PREFERENCE_DIAGRAM_SEQUENCESEDIAGMRA_REPLY_MESSAGENAME = Messages.getString("LABEL.PREFERENCE.DIAGRAM.SEQUENCESEDIAGMRA.REPLY.MESSAGENAME"); //$NON-NLS-1$

    /** 유스케이스 모형 기술서 */
    public static final String LABEL_USECASE_DEFINITION = Messages.getString("LABEL.USECASE.DEFINITION"); //$NON-NLS-1$

    /** 유스케이스 설계서 */
    public static final String LABEL_USECASE_ARCHITECTURE = Messages.getString("LABEL.USECASE.ARCHITECTURE"); //$NON-NLS-1$

    /** 컴포넌트 명세서 */
    public static final String LABEL_COMPONENT_DEFINITION = Messages.getString("LABEL.COMPONENT.DEFINITION"); //$NON-NLS-1$

    /** 인터페이스 상호작용 명세서 */
    public static final String LABEL_INTERFACE_INTERACTION_DEFINITION = Messages.getString("LABEL.INTERFACE.INTERACTION.DEFINITION"); //$NON-NLS-1$

    /** 컴포넌트 아키텍처 정의서 */
    public static final String LABEL_COMPONENT_ARCHITECTURE_DEFINITION = Messages.getString("LABEL.COMPONENT.ARCHITECTURE.DEFINITION"); //$NON-NLS-1$

    /** 컴포넌트 설계서 */
    public static final String LABEL_COMPONENT_ARCHITECTURE = Messages.getString("LABEL.COMPONENT.ARCHITECTURE"); //$NON-NLS-1$

    /** 트랜잭션 설계서 */
    public static final String LABEL_TRANSACTION_ARCHITECTURE = Messages.getString("LABEL.TRANSACTION.ARCHITECTURE"); //$NON-NLS-1$
    
    /** 사용자 인터페이스 명세서 */
    public static final String LABEL_USER_INTERFACE_DEFINITION = Messages.getString("LABEL.USER.INTERFACE.DEFINITION"); //$NON-NLS-1$

    /** 사용자 인터페이스 설계서 */
    public static final String LABEL_USER_INTERFACE_ARCHITECTURE = Messages.getString("LABEL.USER.INTERFACE.ARCHITECTURE"); //$NON-NLS-1$

    /** .xml */
    public static final String LABEL_XML = Messages.getString("LABEL.XML");//$NON-NLS-1$

    /** 유스케이스 분석서 */
    public static final String LABEL_USECASE_ANALYSIS_SPECIFICATION = Messages.getString("LABEL.USECASE.ANALYSIS.SPECIFICATION"); //$NON-NLS-1$

    /** 기본 템플릿 */
    public static final String LABEL_DEFAULT_TEMPLATE = Messages.getString("LABEL.DEFAULT.TEMPLATE");//$NON-NLS-1$

    /** 유스케이스 모형 기술서 생성 */
    public static final String LABEL_CREATE_USECASE_DEFINITION = Messages.getString("LABEL.CREATE.USECASE.DEFINITION");//$NON-NLS-1$

    /** 유스케이스 분석서 생성 */
    public static final String LABEL_CREATE_USECASE_ANALYSIS = Messages.getString("LABEL.CREATE.USECASE.ANALYSIS");//$NON-NLS-1$

    /** 유스케이스 설계서 생성 */
    public static final String LABEL_CREATE_USECASE_DESIGN = Messages.getString("LABEL.CREATE.USECASE.DESIGN");//$NON-NLS-1$

    /** 컴포넌트 명세서 생성 */
    public static final String LABEL_CREATE_COMPONENT_DEFINITION = Messages.getString("LABEL.CREATE.COMPONENT.DEFINITION");//$NON-NLS-1$

    /** 컴포넌트 설계서 생성 */
    public static final String LABEL_CREATE_COMPONENT_ARCHITECTURE = Messages.getString("LABEL.CREATE.COMPONENT.ARCHITECTURE");//$NON-NLS-1$

    /** 컴포넌트 아키텍처 정의서 생성 */
    public static final String LABEL_CREATE_COMPONENT_ARCHITECTURE_DEFINITION = Messages.getString("LABEL.CREATE.COMPONENT.ARCHITECTURE.DEFINITION"); //$NON-NLS-1$

    /** 사용자 인터페이스 명세서 생성 */
    public static final String LABEL_CREATE_USER_INTERFACE_DEFINITION = Messages.getString("LABEL.CREATE.USER.INTERFACE.DEFINITION"); //$NON-NLS-1$

    /** 사용자 인터페이스 설계서 생성 */
    public static final String LABEL_CREATE_USER_INTERFACE_ARCHITECTURE = Messages.getString("LABEL.CREATE.USER.INTERFACE.ARCHITECTURE"); //$NON-NLS-1$

    /** 처리중 */
    public static final String LABEL_PROCESSING = Messages.getString("LABEL.PROCESSING"); //$NON-NLS-1$

    /** LABEL_JPEG_FILES */
    public static final String LABEL_JPEG_FILES = Messages.getString("LABEL.JPEG.FILES"); //$NON-NLS-1$

    /** LABEL_BITMAP_FILES */
    public static final String LABEL_BITMAP_FILES = Messages.getString("LABEL.BITMAP.FILES"); //$NON-NLS-1$

    /** LABEL_COURIER */
    public static final String LABEL_COURIER = Messages.getString("LABEL.COURIER"); //$NON-NLS-1$

    /** LABEL_INITIAL_NODE */
    public static final String LABEL_INITIAL_NODE = Messages.getString("LABEL.INITIAL.NODE"); //$NON-NLS-1$

    /** LABEL_FINAL_NODE */
    public static final String LABEL_FINAL_NODE = Messages.getString("LABEL.FINAL.NODE"); //$NON-NLS-1$

    /** LABEL_FORK_NODE */
    public static final String LABEL_FORK_NODE = Messages.getString("LABEL.FORK.NODE"); //$NON-NLS-1$

    /** LABEL_JOIN_NODE */
    public static final String LABEL_JOIN_NODE = Messages.getString("LABEL.JOIN.NODE"); //$NON-NLS-1$

    /** LABEL_ACTIVITY_FINAL_NODE_EDIT_PART_REFRESH_BISUALS_ERROR */
    public static final String LABEL_ACTIVITY_FINAL_NODE_EDIT_PART_REFRESH_BISUALS_ERROR = Messages.getString("LABEL_ACTIVITY_FINAL_NODE_EDIT_PART_REFRESH_BISUALS_ERROR"); //$NON-NLS-1$

    /** LABEL_X */
    public static final String LABEL_X = Messages.getString("LABEL_X"); //$NON-NLS-1$

    /** LABEL_Y */
    public static final String LABEL_Y = Messages.getString("LABEL_Y"); //$NON-NLS-1$

    /** LABEL_CENTRAL_BUFFER_NODE */
    public static final String LABEL_CENTRAL_BUFFER_NODE = Messages.getString("LABEL_CENTRAL_BUFFER_NODE"); //$NON-NLS-1$

    /** LABEL_MALGUN_GOTHIC */
    public static final String LABEL_MALGUN_GOTHIC = Messages.getString("LABEL_MALGUN_GOTHIC"); //$NON-NLS-1$

    /** LABEL_ADD_DROP */
    public static final String LABEL_ADD_DROP = Messages.getString("LABEL_ADD_DROP"); //$NON-NLS-1$

    /** LABEL_COMPONENT_DIAGRAM_EDIT_PART_REFRESH_VISUALS_ERROR */
    public static final String LABEL_COMPONENT_DIAGRAM_EDIT_PART_REFRESH_VISUALS_ERROR = Messages.getString("LABEL_COMPONENT_DIAGRAM_EDIT_PART_REFRESH_VISUALS_ERROR"); //$NON-NLS-1$

    /** LABEL_ADD_CHILD_COMMAND */
    public static final String LABEL_ADD_CHILD_COMMAND = Messages.getString("LABEL_ADD_CHILD_COMMAND"); //$NON-NLS-1$

    /** LABEL_JPEG */
    public static final String LABEL_JPEG = Messages.getString("LABEL_JPEG"); //$NON-NLS-1$

    /** LABEL_JPG_EXTENSION */
    public static final String LABEL_JPG_EXTENSION = Messages.getString("LABEL_JPG_EXTENSION"); //$NON-NLS-1$

    /** LABEL_BMP_EXTENSION */
    public static final String LABEL_BMP_EXTENSION = Messages.getString("LABEL_BMP_EXTENSION"); //$NON-NLS-1$

    /** LABEL_SAVE_TO_IMAGE_ACTION */
    public static final String LABEL_SAVE_TO_IMAGE_ACTION = Messages.getString("LABEL_SAVE_TO_IMAGE_ACTION"); //$NON-NLS-1$

    /** LABEL_CHANGE_SHAPE */
    public static final String LABEL_CHANGE_SHAPE = Messages.getString("LABEL_CHANGE_SHAPE"); //$NON-NLS-1$

    /** LABEL_OPAQUE_ACTION_NAME */
    public static final String LABEL_OPAQUE_ACTION_NAME = Messages.getString("LABEL_OPAQUE_ACTION_NAME"); //$NON-NLS-1$

    /** LABEL_DECISION */
    public static final String LABEL_DECISION = Messages.getString("LABEL_DECISION"); //$NON-NLS-1$

    /** LABEL_DATA_STROE */
    public static final String LABEL_DATA_STROE = Messages.getString("LABEL_DATA_STROE"); //$NON-NLS-1$

    /** LABEL_CENTRAL_BUFFER */
    public static final String LABEL_CENTRAL_BUFFER = Messages.getString("LABEL_CENTRAL_BUFFER"); //$NON-NLS-1$

    /** LABEL_CHANGE */
    public static final String LABEL_CHANGE = Messages.getString("LABEL_CHANGE"); //$NON-NLS-1$

    /** LABEL_CHANGE_ACTION */
    public static final String LABEL_CHANGE_ACTION = Messages.getString("LABEL_CHANGE_ACTION"); //$NON-NLS-1$

    /** LABEL_JPG */
    public static final String LABEL_JPG = Messages.getString("LABEL_JPG"); //$NON-NLS-1$

    /** LABEL_MERGE_NODE */
    public static final String LABEL_MERGE_NODE = Messages.getString("LABEL_MERGE_NODE"); //$NON-NLS-1$

    /** LABEL_UML_NOTATION */
    public static final String LABEL_UML_NOTATION = Messages.getString("LABEL_UML_NOTATION"); //$NON-NLS-1$

    /** LABEL_NULL */
    public static final String LABEL_NULL = Messages.getString("LABEL_NULL"); //$NON-NLS-1$

    /** LABEL_BOOLEAN */
    public static final String LABEL_BOOLEAN = Messages.getString("LABEL_BOOLEAN"); //$NON-NLS-1$

    /** LABEL_INTEGER */
    public static final String LABEL_INTEGER = Messages.getString("LABEL_INTEGER"); //$NON-NLS-1$

    /** LABEL_STRING */
    public static final String LABEL_STRING = Messages.getString("LABEL_STRING"); //$NON-NLS-1$

    /** LABEL_RELATED */
    public static final String LABEL_RELATED = Messages.getString("LABEL_RELATED"); //$NON-NLS-1$

    /** LABEL_XLS_EXTENSION */
    public static final String LABEL_XLS_EXTENSION = Messages.getString("LABEL_XLS_EXTENSION"); //$NON-NLS-1$

    /** LABEL_UML_MODEL_FILE */
    public static final String LABEL_UML_MODEL_FILE = Messages.getString("LABEL_UML_MODEL_FILE"); //$NON-NLS-1$

    /** LABEL_REPORTING */
    public static final String LABEL_REPORTING = Messages.getString("LABEL_REPORTING"); //$NON-NLS-1$

    /** LABEL_CREATE_USECASE_LIST */
    public static final String LABEL_CREATE_USECASE_LIST = Messages.getString("LABEL_CREATE_USECASE_LIST"); //$NON-NLS-1$

    /** LABEL_NUMBER */
    public static final String LABEL_NUMBER = Messages.getString("LABEL_NUMBER"); //$NON-NLS-1$

    /** LABEL_CREATE_PACKAGE_LIST */
    public static final String LABEL_CREATE_PACKAGE_LIST = Messages.getString("LABEL_CREATE_PACKAGE_LIST"); //$NON-NLS-1$

    /** LABEL_CREATE_CLASS_LIST */
    public static final String LABEL_CREATE_CLASS_LIST = Messages.getString("LABEL_CREATE_CLASS_LIST"); //$NON-NLS-1$

    /** LABEL_FILE_NAME */
    public static final String LABEL_FILE_NAME = Messages.getString("LABEL_FILE_NAME"); //$NON-NLS-1$

    /** LABEL_SELECT_ELEMENT */
    public static final String LABEL_SELECT_ELEMENT = Messages.getString("LABEL_SELECT_ELEMENT"); //$NON-NLS-1$

    /** LABEL_TARGET */
    public static final String LABEL_TARGET = Messages.getString("LABEL_TARGET"); //$NON-NLS-1$

    /** LABEL_SOURCE */
    public static final String LABEL_SOURCE = Messages.getString("LABEL_SOURCE"); //$NON-NLS-1$

    /** LABEL_USECASE_EDIT_PART_REFRESH_VISUALS_ERROR */
    public static final String LABEL_USECASE_EDIT_PART_REFRESH_VISUALS_ERROR = Messages.getString("LABEL_USECASE_EDIT_PART_REFRESH_VISUALS_ERROR"); //$NON-NLS-1$

    /** LABEL_ARTIFACT_EDIT_PART_REFRESH_VISUALS_ERROR */
    public static final String LABEL_ARTIFACT_EDIT_PART_REFRESH_VISUALS_ERROR = Messages.getString("LABEL_ARTIFACT_EDIT_PART_REFRESH_VISUALS_ERROR"); //$NON-NLS-1$

    /** LABEL_ACTOR_EDIT_PART_REFRESH_VISUALS_ERROR */
    public static final String LABEL_ACTOR_EDIT_PART_REFRESH_VISUALS_ERROR = Messages.getString("LABEL_ACTOR_EDIT_PART_REFRESH_VISUALS_ERROR"); //$NON-NLS-1$

    /** LABEL_SEQUENCE_DIAGRAM */
    public static final String LABEL_SEQUENCE_DIAGRAM = Messages.getString("LABEL_SEQUENCE_DIAGRAM"); //$NON-NLS-1$

    /** LABEL_CREATE_A_NOTE_ATTACHMENT */
    public static final String LABEL_CREATE_A_NOTE_ATTACHMENT = Messages.getString("LABEL_CREATE_A_NOTE_ATTACHMENT"); //$NON-NLS-1$

    /** LABEL_NOTE_ATTACHEMENT */
    public static final String LABEL_NOTE_ATTACHEMENT = Messages.getString("LABEL_NOTE_ATTACHEMENT"); //$NON-NLS-1$

    /** LABEL_CREATE_A_TEXT */
    public static final String LABEL_CREATE_A_TEXT = Messages.getString("LABEL_CREATE_A_TEXT"); //$NON-NLS-1$

    /** LABEL_CREATE_A_NOTE */
    public static final String LABEL_CREATE_A_NOTE = Messages.getString("LABEL_CREATE_A_NOTE"); //$NON-NLS-1$

    /** LABEL_NOTE */
    public static final String LABEL_NOTE = Messages.getString("LABEL_NOTE"); //$NON-NLS-1$

    /** LABEL_TOOLS */
    public static final String LABEL_TOOLS = Messages.getString("LABEL_TOOLS"); //$NON-NLS-1$

    /** LABEL_NO_EDITPART_ASSIGNED_FOR */
    public static final String LABEL_NO_EDITPART_ASSIGNED_FOR = Messages.getString("LABEL_NO_EDITPART_ASSIGNED_FOR"); //$NON-NLS-1$

    /** LABEL_SEQUENCE_DIAGRAM_EDIT_PART_REFRESH_NAME_HEADERS_ERROR */
    public static final String LABEL_SEQUENCE_DIAGRAM_EDIT_PART_REFRESH_NAME_HEADERS_ERROR = Messages.getString("LABEL_SEQUENCE_DIAGRAM_EDIT_PART_REFRESH_NAME_HEADERS_ERROR"); //$NON-NLS-1$

    /** LABEL_CREATE_BENDPOINT */
    public static final String LABEL_CREATE_BENDPOINT = Messages.getString("LABEL_CREATE_BENDPOINT"); //$NON-NLS-1$

    /** LABEL_NAMES_MATCHING */
    public static final String LABEL_NAMES_MATCHING = Messages.getString("LABEL_NAMES_MATCHING"); //$NON-NLS-1$

    /** LABEL_TEST_PATTERN */
    public static final String LABEL_TEST_PATTERN = Messages.getString("LABEL_TEST_PATTERN"); //$NON-NLS-1$

    /** 다음 버전에서 구현 예정 */
    public static final String LABEL_TO_BE_DETERMINED = Messages.getString("LABEL.TO.BE.DETERMINED"); //$NON-NLS-1$

    /** 알림 */
    public static final String LABEL_NOTIFICATION = Messages.getString("LABEL.NOTIFICATION"); //$NON-NLS-1$    

    /** 분석 모델 */
    public static final String LABEL_ANALYSIS_MODEL = Messages.getString("LABEL.ANALYSIS.MODEL"); //$NON-NLS-1$

    /** 설계 모델 */
    public static final String LABEL_DESIGN_MODEL = Messages.getString("LABEL.DESIGN.MODEL"); //$NON-NLS-1$

    /** 실현 클래스 목록(분석) */
    public static final String LABEL_USECASE_TRACE_MATRIX_ANALYSIS_MODEL_CLASS_LIST_TITLE = Messages.getString("LABEL.USECASE.TRACE.MATRIX.ANALYSIS.MODEL.CLASS.LIST.TITLE"); //$NON-NLS-1$

    /** 실현 클래스 목록(설계) */
    public static final String LABEL_USECASE_TRACE_MATRIX_DESIGN_MODEL_CLASS_LIST_TITLE = Messages.getString("LABEL.USECASE.TRACE.MATRIX.DESIGN.MODEL.CLASS.LIST.TITLE"); //$NON-NLS-1$

    /** 패키지명 */
    public static final String LABEL_USECASE_TRACE_MATRIX_PACKAGE_TITLE = Messages.getString("LABEL.USECASE.TRACE.MATRIX.PACKAGE.TITLE"); //$NON-NLS-1$

    /** 컴포넌트명 */
    public static final String LABEL_USECASE_TRACE_MATRIX_COMPONENT_TITLE = Messages.getString("LABEL.USECASE.TRACE.MATRIX.COMPONENT.TITLE"); //$NON-NLS-1$

    /** 유스케이스명 */
    public static final String LABEL_USECASE_TRACE_MATRIX_USECASE_TITLE = Messages.getString("LABEL.USECASE.TRACE.MATRIX.USECASE.TITLE"); //$NON-NLS-1$

    /** VOPC 다이어그램 이름 */
    public static final String LABEL_VOPC_DIAGRAM_NAME = Messages.getString("LABEL.VOPC.DIAGRAM.NAME"); //$NON-NLS-1$

    /** sampleFont */
    public static final String LABEL_SAMPLEFONT = Messages.getString("LABEL.SAMPLEFONT"); //$NON-NLS-1$

    /** ${0} 중... */
    public static final String LABEL_ING = Messages.getString("LABEL.ING"); //$NON-NLS-1$

    /** 외 %d건 */
    public static final String LABEL_ETC_CNT = Messages.getString("LABEL.LABEL_ETC_CNT"); //$NON-NLS-1$

    /** 시퀀스 다이어그램 생성 */
    public static final String LABEL_SEQUENCE_DIAGRAM_CREATION = Messages.getString("LABEL.SEQUENCE.DIAGRAM.CREATION"); //$NON-NLS-1$

    /** VOPC 다이어그램 생성 */
    public static final String LABEL_VOPC_DIAGRAM_CREATION = Messages.getString("LABEL.VOPC.DIAGRAM.CREATION"); //$NON-NLS-1$

    /** 결과: */
    public static final String LABEL_RESULT = Messages.getString("LABEL.RESULT"); //$NON-NLS-1$

    /** 구조 생성 */
    public static final String LABEL_STRUCTURE_CREATION = Messages.getString("LABEL.STRUCTURE.CREATION"); //$NON-NLS-1$

    /** 라이프라인 생성 */
    public static final String LABEL_LIFELINE_CREATION = Messages.getString("LABEL.LIFELINE.CREATION"); //$NON-NLS-1$

    /** 메시지 생성 */
    public static final String LABEL_MESSAGE_CREATION = Messages.getString("LABEL.MESSAGE.CREATION"); //$NON-NLS-1$

    /** Usecases */
    public static final String LABEL_USECASE_PACKAGE = Messages.getString("LABEL.USECASE.PACKAGE"); //$NON-NLS-1$

    /** Usecase Realization */
    public static final String LABEL_USECASE_REALIZATION_PACKAGE = Messages.getString("LABEL.USECASE.REALIZATION.PACKAGE"); //$NON-NLS-1$

    /** 키워드의 스테레오타입 변환 */
    public static final String LABEL_KEYWORD_CONVERSION = Messages.getString("LABEL.KEYWORD.CONVERSION"); //$NON-NLS-1$

    /** 클래스 병합 */
    public static final String LABEL_CLASSES_MERGER = Messages.getString("LABEL.CLASSES.MERGER"); //$NON-NLS-1$

    /** 병합할 클래스 이름 : MergedClass */
    public static final String LABEL_MERGED_CLASS_NAME = Messages.getString("LABEL.MERGED.CLASS.NAME"); //$NON-NLS-1$

    /** 병합할 유스케이스 이름 : MergedUsecase */
    public static final String LABEL_MERGED_USECASE_NAME = Messages.getString("LABEL.MERGED.USECASE.NAME"); //$NON-NLS-1$

    /** 병합 후 기존 클래스 삭제하기 */
    public static final String LABEL_ORIGINAL_CLASSES_DELETION = Messages.getString("LABEL.ORIGINAL.CLASSES.DELETION"); //$NON-NLS-1$

    /** 용어 사전 생성 */
    public static final String LABEL_GLOSSARY_DICTIONARY_CREATION = Messages.getString("LABEL.GLOSSARY.DICTIONARY.CREATION"); //$NON-NLS-1$

    /** 용어 변환 */
    public static final String LABEL_GLOSSARY_TRANSLATION = Messages.getString("LABEL.GLOSSARY.TRANSLATION"); //$NON-NLS-1$

    /** 백업 화일 확장자 이름 : '.backup' */
    public static final String LABEL_BACKUP_FILE_EXTENSION_NAME = Messages.getString("LABEL.BACKUP.FILE.EXTENSION.NAME"); //$NON-NLS-1$

    /** 용어 사전 원본 요소 이름 : '원본 이름' */
    public static final String LABEL_GLOSSARY_DICTIONARY_ORIGINAL_ELEMENT_NAME = Messages.getString("LABEL.GLOSSARY.DICTIONARY.ORIGINAL.ELEMENT.NAME"); //$NON-NLS-1$

    /** 용어 사전 변역할 요소 이름 : '변역할 이름' */
    public static final String LABEL_GLOSSARY_DICTIONARY_TRANSLATED_ELEMENT_NAME = Messages.getString("LABEL.GLOSSARY.DICTIONARY.TRANSLATED.ELEMENT.NAME"); //$NON-NLS-1$

    /** 용어 사전 유형 이름 : '유형' */
    public static final String LABEL_GLOSSARY_DICTIONARY_TYPE_NAME = Messages.getString("LABEL.GLOSSARY.DICTIONARY.TYPE.NAME"); //$NON-NLS-1$

    /** 용어 사전 위치 정보 이름 : '위치정보' */
    public static final String LABEL_GLOSSARY_DICTIONARY_LOCATION_INFORMATION_NAME = Messages.getString("LABEL.GLOSSARY.DICTIONARY.LOCATION.INFORMATION.NAME"); //$NON-NLS-1$

    /** 용어 사전 기본 파일명 : '기본' */
    public static final String LABEL_GLOSSARY_DICTIONARY_DEFAULT_FILE_NAME = Messages.getString("LABEL.GLOSSARY.DICTIONARY.DEFAULT.FILE.NAME"); //$NON-NLS-1$

    /** 용어사전 파일 접미사 : '_용어사전' */
    public static final String LABEL_GLOSSARY_DICTIONARY_FILE_POSTFIX = Messages.getString("LABEL.GLOSSARY.DICTIONARY.FILE.POSTFIX"); //$NON-NLS-1$

    /** 유스케이스 병합 */
    public static final String LABEL_USECASES_MERGER = Messages.getString("LABEL.USECASES.MERGER"); //$NON-NLS-1$

    /** 유스케이스 추적 매트릭스 기본 파일명 : '유스케이스추적매트릭스' */
    public static final String LABEL_USECASE_TRACE_MATRIX_DEFAULT_FILE_NAME = Messages.getString("LABEL.USECASE.TRACE.MATRIX.DEFAULT.FILE.NAME"); //$NON-NLS-1$

    /** Load reference model */
    public static final String LABEL_LOAD_RESOURCE = Messages.getString("LABEL.LOAD_RESOURCE"); //$NON-NLS-1$

    /** 모델버전체크 */
    public static final String LABEL_MODEL_VERSION_CHECK = Messages.getString("LABEL.MODEL.VERSION_CHECK"); //$NON-NLS-1$

    /** RSA모델을 UML모델로 변환 */
    public static final String LABEL_TRANSFORM_RSA_MODEL_INTO_UML_MODEL = Messages.getString("LABEL.TRANSFORM.RSA.MODEL.INTO.UML.MODEL"); //$NON-NLS-1$

    /** 선택한 RSA 모델: */
    public static final String LABEL_SELECTED_RSA_MODEL = Messages.getString("LABEL.SELECTED.RSA.MODEL"); //$NON-NLS-1$

    /** 선택한 RSA 프로파일: */
    public static final String LABEL_SELECTED_RSA_PROFILE = Messages.getString("LABEL.SELECTED.RSA.PROFILE"); //$NON-NLS-1$

    /** 유스케이스 생성 */
    public static final String LABEL_USECASE_CREATION_FROM_NCP_META_CONTENT = Messages.getString("LABEL.USECASE.CREATION.FROM.NCP.META.CONTENT"); //$NON-NLS-1$
    
    /** 유스케이스 메타 컨텐츠 생성 */
    public static final String LABEL_USECASE_META_CONTENT_CREATION = Messages.getString("LABEL.USECASE.META.CONTENT.CREATION"); //$NON-NLS-1$

    /** UML 유스케이스와 RM 요구사항 명세간의 관계 생성 */
    public static final String LABEL_RELATION_CREATION_BETWEEN_UML_USECASE_AND_RM_USECASE = Messages.getString("LABEL.RELATION.CREATION.BETWEEN.UML.USECASE.AND.RM.USECASE"); //$NON-NLS-1$
    
    /** RSA 모델 선택 */
    public static final String LABEL_RSA_MODEL_SELECTION = Messages.getString("LABEL.RSA.MODEL.SELECTION"); //$NON-NLS-1$;

    /** RSA 프로파일 선택 */
    public static final String LABEL_RSA_PROFILE_SELECTION = Messages.getString("LABEL.RSA.PROFILE.SELECTION"); //$NON-NLS-1$;

    /** UML 다이어그램 생성 */
    public static final String LABEL_UML_DIAGRAM_CREATION = Messages.getString("LABEL.UML.DIAGRAM.CREATION"); //$NON-NLS-1$;

    /** RSA 다이어그램 삭제 */
    public static final String LABEL_RSA_DIAGRAM_DELETION = Messages.getString("LABEL.RSA.DIAGRAM.DELETION"); //$NON-NLS-1$;

    /** 모델정보 요약 */
    public static final String LABEL_MODEL_INFORMATION_SUMMARY = Messages.getString("LABEL.MODEL.INFORMATION.SUMMARY"); //$NON-NLS-1$;

    /** 모델정보 요약 - 컴포넌트 갯수 : */
    public static final String LABEL_MODEL_INFORMATION_COMPONENT_COUNT = Messages.getString("LABEL.MODEL.INFORMATION.COMPONENT.COUNT"); //$NON-NLS-1$;

    /** 모델정보 요약 - 단위업무 갯수 : */
    public static final String LABEL_MODEL_INFORMATION_BIZUNIT_COUNT = Messages.getString("LABEL.MODEL.INFORMATION.BIZUNIT.COUNT"); //$NON-NLS-1$;

    /** 모델정보 요약 - 메소드 갯수 : */
    public static final String LABEL_MODEL_INFORMATION_METHOD_COUNT = Messages.getString("LABEL.MODEL.INFORMATION.METHOD.COUNT"); //$NON-NLS-1$;

    /** 프린트 - 미리보기 HEADERFOOTER */
    public static final String LABEL_PRINT_HEADERFOOTER = Messages.getString("LABEL.PRINT.HEADERFOOTER"); //$NON-NLS-1$;

    /** 프린트 - 미리보기 HEADER */
    public static final String LABEL_PRINT_HEADER = Messages.getString("LABEL.PRINT.HEADER"); //$NON-NLS-1$;

    /** 프린트 - 미리보기 HEADER TEXT (머리글 내용 :)*/
    public static final String LABEL_PRINT_HEADER_TEXT = Messages.getString("LABEL.PRINT.HEADER.TEXT"); //$NON-NLS-1$;

    /** 프린트 - 미리보기 FOOTER */
    public static final String LABEL_PRINT_FOOTER = Messages.getString("LABEL.PRINT.FOOTER"); //$NON-NLS-1$;

    /** 프린트 - 미리보기 ALL */
    public static final String LABEL_PRINT_ALL = Messages.getString("LABEL.PRINT.ALL"); //$NON-NLS-1$;

    /** 프린트 - 미리보기 */
    public static final String LABEL_PRINT_NONE = Messages.getString("LABEL.PRINT.NONE"); //$NON-NLS-1$;
    
    /** 프린트 - 머리글에 다이어그램 이름만 표시 */
    public static final String LABEL_PRINT_HEADER_DIAGRAMNAME = Messages.getString("LABEL.PRINT.HEADER.DIAGRAMNAME"); //$NON-NLS-1$;
   
    /** 프린트 용지크기 */
    public static final String LABEL_PRINT_PAGESIZE = Messages.getString("LABEL.PRINT.PAGESIZE"); //$NON-NLS-1$;

    /** 프린트 용지크기 - A3 */
    public static final String LABEL_PRINT_A3 = Messages.getString("LABEL.PRINT.A3"); //$NON-NLS-1$;

    /** 프린트 용지크기 - A4 */
    public static final String LABEL_PRINT_A4 = Messages.getString("LABEL.PRINT.A4"); //$NON-NLS-1$;

    /** 프린트 용지크기 - B4 */
    public static final String LABEL_PRINT_B4 = Messages.getString("LABEL.PRINT.B4"); //$NON-NLS-1$;

    /** 프린트 용지크기 - B5 */
    public static final String LABEL_PRINT_B5 = Messages.getString("LABEL.PRINT.B5"); //$NON-NLS-1$;
    
    /** 프린트 용지방향 */
    public static final String LABEL_PRINT_PAGEORIENTATION = Messages.getString("LABEL.PRINT.PAGEORIENTATION"); //$NON-NLS-1$;

    /** 프린트 용지방향 - PORTRAIT */
    public static final String LABEL_PRINT_PORTRAIT = Messages.getString("LABEL.PRINT.PORTRAIT"); //$NON-NLS-1$;

    /** 프린트 용지방향 - LANDSCAPE */
    public static final String LABEL_PRINT_LANDSCAPE = Messages.getString("LABEL.PRINT.LANDSCAPE"); //$NON-NLS-1$;
    
    /** 프린트 - 페이지 여백 */
    public static final String LABEL_PRINT_MARGIN_PAGE = Messages.getString("LABEL.PRINT.MARGIN.PAGE"); //$NON-NLS-1$;
    
    /** 프린트 - Milimeter 단위 사용 */
    public static final String LABEL_PRINT_MARGIN_USE_MM = Messages.getString("LABEL.PRINT.MARGIN.USE.MM"); //$NON-NLS-1$;

    /** 프린트 - Inch 단위 사용 */
    public static final String LABEL_PRINT_MARGIN_USE_INCHES = Messages.getString("LABEL.PRINT.MARGIN.USE.INCHES"); //$NON-NLS-1$;
    
    /** 프린트 - in mm */
    public static final String LABEL_PRINT_MARGIN_IN_MM = Messages.getString("LABEL.PRINT.MARGIN.IN.MM"); //$NON-NLS-1$;

    /** 프린트 - in inches */
    public static final String LABEL_PRINT_MARGIN_IN_INCHES = Messages.getString("LABEL.PRINT.MARGIN.IN.INCHES"); //$NON-NLS-1$;
    
    /** 프린트 - 왼쪽 */
    public static final String LABEL_PRINT_MARGIN_LEFT = Messages.getString("LABEL.PRINT.MARGIN.LEFT"); //$NON-NLS-1$;

    /** 프린트 - 오른쪽 */
    public static final String LABEL_PRINT_MARGIN_RIGHT = Messages.getString("LABEL.PRINT.MARGIN.RIGHT"); //$NON-NLS-1$;
    
    /** 프린트 - 위 */
    public static final String LABEL_PRINT_MARGIN_TOP = Messages.getString("LABEL.PRINT.MARGIN.TOP"); //$NON-NLS-1$;

    /** 프린트 - 아래 */
    public static final String LABEL_PRINT_MARGIN_BOTTOM = Messages.getString("LABEL.PRINT.MARGIN.BOTTOM"); //$NON-NLS-1$;
    
    /** 프린트 - 머리글 */
    public static final String LABEL_PRINT_MARGIN_HEADER = Messages.getString("LABEL.PRINT.MARGIN.HEADER"); //$NON-NLS-1$;

    /** 프린트 - 꼬리글 */
    public static final String LABEL_PRINT_MARGIN_FOOTER = Messages.getString("LABEL.PRINT.MARGIN.FOOTER"); //$NON-NLS-1$;

    /** 미사용 요소 제거 */
    public static final String LABEL_DELETE_UNUSED_ELEMENT = Messages.getString("LABEL.DELETE.UNUSED.ELEMENT"); //$NON-NLS-1$;
    
    /** 연계 생성 */
    public static final String LABEL_CREATE_RELATION = Messages.getString("LABEL.CREATE.RELATION");//$NON-NLS-1$
    
    /********************************** MESSAGE *******************************************/

    /** Dialog Title - NEXCORE UML Modeler */
    public static final String MESSAGE_DIALOG_TITLE = Messages.getString("MESSAGE.DIALOG.TITLE");//$NON-NLS-1$

    /** 모델 이름을 입력하세요. */
    public static final String MESSAGE_PROJECT_WIZARD_ERROR = Messages.getString("MESSAGE.PROJECT.WIZARD.ERROR");//$NON-NLS-1$

    /** 같은 이름이 존재합니다. */
    public static final String MESSAGE_DIALOG_WARNING_SAMENAME = Messages.getString("MESSAGE.DIALOG.WARNING.SAMENAME");//$NON-NLS-1$

    /** 삭제하시겠습니까? : ${0} */
    public static final String MESSAGE_DELETE = Messages.getString("MESSAGE.DELETE"); //$NON-NLS-1$

    /** 삭제가 불가능한 요소를 제외하고 삭제처리 됩니다. 삭제하시겠습니까? : ${0} */
    public static final String MESSAGE_DELETE_WITHOUT_IMPOSSIBLE = Messages.getString("MESSAGE.DELETE.WITHOUT.IMPOSSIBLE"); //$NON-NLS-1$
    
    /** ${0} 하위의 미사용 요소(라이프라인, 메세지 등)를 삭제하시겠습니까? */
    public static final String MESSAGE_DELETE_UNUSED_ELEMENT = Messages.getString("MESSAGE.DELETE.UNUSED.ELEMENT"); //$NON-NLS-1$
    
    /** 단편화된 패키지는 삭제 할 수 없습니다. 병합 후 삭제하세요. */
    public static final String MESSAGE_FRAGMENT_DELETE = Messages.getString("MESSAGE.FRAGMENT_DELETE"); //$NON-NLS-1$
    
    /** 프로파일과 라이브러리는 바로 삭제할 수 없습니다. 모델 편집기를 이용하세요. */
    public static final String MESSAGE_PROFILE_DELETE = Messages.getString("MESSAGE.PROFILE_DELETE"); //$NON-NLS-1$

    /** 파일명은 확장자가 ${0}이어야 합니다. */
    public static final String MESSAGE_WRONG_FILE_EXTENSION = Messages.getString("MESSAGE.WRONGFILEEXTENSION"); //$NON-NLS-1$

    /** Project Explorer의 UML 요소 필터를 적용해주십시오. */
    public static final String MESSAGE_PROJECT_APPLY_FILTER = Messages.getString("MESSAGE.PROJECT.APPLYFILTER"); //$NON-NLS-1$

    /** 파일이 존재합니다. 덮어 씌울까요? */
    public static final String MESSAGE_CONFIRM_OVERWRITE_FILE = Messages.getString("MESSAGE.CONFIRM.OVERWRITE.FILE"); //$NON-NLS-1$

    /** 명령 실행 중에 익셉션이 발생했습니다. */
    public static final String MESSAGE_COMMAND_EXCEPTION = Messages.getString("MESSAGE.COMMAND.EXCEPTION"); //$NON-NLS-1$

    /** 명령이 실패했습니다. */
    public static final String MESSAGE_COMMAND_FAILED = Messages.getString("MESSAGE.COMMAND.FAILED"); //$NON-NLS-1$

    /** 변경이 밸리데이션 실패로 인해 자동적으로 복원되었습니다. */
    public static final String MESSAGE_COMMAND_ROLLBACK = Messages.getString("MESSAGE.COMMAND.ROLLBACK"); //$NON-NLS-1$

    /** Activity 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_ACTIVITY = Messages.getString("MESSAGE.PALETTE.ACTIVITY"); //$NON-NLS-1$

    /** ActivityParameterNode 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_ACTIVITYPARAMETERNODE = Messages.getString("MESSAGE.PALETTE.ACTIVITYPARAMETERNODE"); //$NON-NLS-1$

    /** ActivityPartition 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_ACTIVITYPARTITION = Messages.getString("MESSAGE.PALETTE.ACTIVITYPARTITION"); //$NON-NLS-1$

    /** AcceptEventAction 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_ACCEPTEVENTACTION = Messages.getString("MESSAGE.PALETTE.ACCEPTEVENTACTION"); //$NON-NLS-1$

    /** AddStructuralFeatureValueAction 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_ADDSTRUCTURALFEATUREVALUEACTION = Messages.getString("MESSAGE.PALETTE.ADDSTRUCTURALFEATUREVALUEACTION"); //$NON-NLS-1$

    /** CallBehaviorAction 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_CALLBEHAVIORACTION = Messages.getString("MESSAGE.PALETTE.CALLBEHAVIORACTION"); //$NON-NLS-1$

    /** CallOperationAction 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_CALLOPERATIONACTION = Messages.getString("MESSAGE.PALETTE.CALLOPERATIONACTION"); //$NON-NLS-1$

    /** CreateObjectAction 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_CREATEOBJECTACTION = Messages.getString("MESSAGE.PALETTE.CREATEOBJECTACTION"); //$NON-NLS-1$

    /** OpaqueAction 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_OPAQUEACTION = Messages.getString("MESSAGE.PALETTE.OPAQUEACTION"); //$NON-NLS-1$

    /** SendSignalAction 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_SENDSIGNALACTION = Messages.getString("MESSAGE.PALETTE.SENDSIGNALACTION"); //$NON-NLS-1$

    /** ActivityFinalNode 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_ACTIVITYFINALNODE = Messages.getString("MESSAGE.PALETTE.ACTIVITYFINALNODE"); //$NON-NLS-1$

    /** FlowFinalNode 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_FLOWFINALNODE = Messages.getString("MESSAGE.PALETTE.FLOWFINALNODE"); //$NON-NLS-1$

    /** MergeNode 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_MERGENODE = Messages.getString("MESSAGE.PALETTE.MERGENODE"); //$NON-NLS-1$

    /** DecisionNode 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_DECISIONNODE = Messages.getString("MESSAGE.PALETTE.DECISIONNODE"); //$NON-NLS-1$

    /** ForkNode 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_FORKNODE = Messages.getString("MESSAGE.PALETTE.FORKNODE"); //$NON-NLS-1$

    /** JoinNode 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_JOINNODE = Messages.getString("MESSAGE.PALETTE.JOINNODE"); //$NON-NLS-1$

    /** ConditionalNode 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_CONDITIONALNODE = Messages.getString("MESSAGE.PALETTE.CONDITIONALNODE"); //$NON-NLS-1$

    /** ExpansionRegion 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_EXPANSIONREGION = Messages.getString("MESSAGE.PALETTE.EXPANSIONREGION"); //$NON-NLS-1$

    /** LoopNode 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_LOOPNODE = Messages.getString("MESSAGE.PALETTE.LOOPNODE"); //$NON-NLS-1$

    /** StructuredActivityNode 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_STRUCTUREDACTIVITYNODE = Messages.getString("MESSAGE.PALETTE.STRUCTUREDACTIVITYNODE"); //$NON-NLS-1$

    /** CentralBufferNode 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_CENTRALBUFFERNODE = Messages.getString("MESSAGE.PALETTE.CENTRALBUFFERNODE"); //$NON-NLS-1$

    /** DataStoreNode 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_DATASTORENODE = Messages.getString("MESSAGE.PALETTE.DATASTORENODE"); //$NON-NLS-1$

    /** Pin 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_PIN = Messages.getString("MESSAGE.PALETTE.PIN"); //$NON-NLS-1$

    /** InputPin 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_INPUTPIN = Messages.getString("MESSAGE.PALETTE.INPUTPIN"); //$NON-NLS-1$

    /** OutputPin 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_OUTPUTPIN = Messages.getString("MESSAGE.PALETTE.OUTPUTPIN"); //$NON-NLS-1$

    /** ControlFlow 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_CONTROLFLOW = Messages.getString("MESSAGE.PALETTE.CONTROLFLOW"); //$NON-NLS-1$

    /** ObjectFlow 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_OBJECTFLOW = Messages.getString("MESSAGE.PALETTE.OBJECTFLOW"); //$NON-NLS-1$

    /** ExceptionHandler 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_EXCEPTIONHANDLER = Messages.getString("MESSAGE.PALETTE.EXCEPTIONHANDLER"); //$NON-NLS-1$

    /** Component 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_COMPONENT = Messages.getString("MESSAGE.PALETTE.COMPONENT"); //$NON-NLS-1$

    /** Artifact 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_ARTIFACT = Messages.getString("MESSAGE.PALETTE.ARTIFACT"); //$NON-NLS-1$

    /** Interface 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_INTERFACE = Messages.getString("MESSAGE.PALETTE.INTERFACE"); //$NON-NLS-1$

    /** Class 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_CLASS = Messages.getString("MESSAGE.PALETTE.CLASS"); //$NON-NLS-1$

    /** Package 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_PACKAGE = Messages.getString("MESSAGE.PALETTE.PACKAGE"); //$NON-NLS-1$

    /** Dependency 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_DEPENDENCY = Messages.getString("MESSAGE.PALETTE.DEPENDENCY"); //$NON-NLS-1$

    /** Association 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_ASSOCIATION = Messages.getString("MESSAGE.PALETTE.ASSOCIATION"); //$NON-NLS-1$

    /** NoneAssociation 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_NONEASSOCIATION = Messages.getString("MESSAGE.PALETTE.NONEASSOCIATION"); //$NON-NLS-1$

    /** SharedAssociation 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_SHAREDASSOCIATION = Messages.getString("MESSAGE.PALETTE.SHAREDASSOCIATION"); //$NON-NLS-1$

    /** CompositeAssociation 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_COMPOSITEASSOCIATION = Messages.getString("MESSAGE.PALETTE.COMPOSITEASSOCIATION"); //$NON-NLS-1$

    /** ElementImport 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_ELEMENTIMPORT = Messages.getString("MESSAGE.PALETTE.ELEMENTIMPORT"); //$NON-NLS-1$

    /** Property 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_PROPERTY = Messages.getString("MESSAGE.PALETTE.PROPERTY"); //$NON-NLS-1$

    /** Operation 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_OPERATION = Messages.getString("MESSAGE.PALETTE.OPERATION"); //$NON-NLS-1$

    /** Port 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_PORT = Messages.getString("MESSAGE.PALETTE.PORT"); //$NON-NLS-1$

    /** Attachement 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_ATTACHEMENT = Messages.getString("MESSAGE.PALETTE.ATTACHEMENT"); //$NON-NLS-1$

    /** Generalization 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_GENERALIZATION = Messages.getString("MESSAGE.PALETTE.GENERALIZATION"); //$NON-NLS-1$

    /** Realization 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_REALIZATION = Messages.getString("MESSAGE.PALETTE.REALIZATION"); //$NON-NLS-1$

    /** Enumeration 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_ENUMERATION = Messages.getString("MESSAGE.PALETTE.ENUMERATION"); //$NON-NLS-1$

    /** PrimitiveType 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_PRIMITIVETYPE = Messages.getString("MESSAGE.PALETTE.PRIMITIVETYPE"); //$NON-NLS-1$

    /** Constraint 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_CONSTRAINT = Messages.getString("MESSAGE.PALETTE.CONSTRAINT"); //$NON-NLS-1$

    /** AssociationClass 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_ASSOCIATIONCLASS = Messages.getString("MESSAGE.PALETTE.ASSOCIATIONCLASS"); //$NON-NLS-1$

    /** EnumerationLiteral 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_ENUMERATIONLITERAL = Messages.getString("MESSAGE.PALETTE.ENUMERATIONLITERAL"); //$NON-NLS-1$

    /** TemplateSignature 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_TEMPLATESIGNATURE = Messages.getString("MESSAGE.PALETTE.TEMPLATESIGNATURE"); //$NON-NLS-1$

    /** Abstraction 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_ABSTRACTION = Messages.getString("MESSAGE.PALETTE.ABSTRACTION"); //$NON-NLS-1$

    /** Usage 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_USAGE = Messages.getString("MESSAGE.PALETTE.USAGE"); //$NON-NLS-1$

    /** Substitution 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_SUBSTITUTION = Messages.getString("MESSAGE.PALETTE.SUBSTITUTION"); //$NON-NLS-1$

    /** TemplateBinding 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_TEMPLATEBINDING = Messages.getString("MESSAGE.PALETTE.TEMPLATEBINDING"); //$NON-NLS-1$

    /** InstanceSpecification 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_INSTANCESPECIFICATION = Messages.getString("MESSAGE.PALETTE.INSTANCESPECIFICATION"); //$NON-NLS-1$

    /** Slot 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_SLOT = Messages.getString("MESSAGE.PALETTE.SLOT"); //$NON-NLS-1$

    /** LiteralInteger 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_LITERALINTEGER = Messages.getString("MESSAGE.PALETTE.LITERALINTEGER"); //$NON-NLS-1$

    /** LiteralString 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_LITERALSTRING = Messages.getString("MESSAGE.PALETTE.LITERALSTRING"); //$NON-NLS-1$

    /** Expression 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_EXPRESSION = Messages.getString("MESSAGE.PALETTE.EXPRESSION"); //$NON-NLS-1$

    /** Lifeline 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_LIFELINE = Messages.getString("MESSAGE.PALETTE.LIFELINE"); //$NON-NLS-1$

    /** CombinedFragment 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_COMBINEDFRAGMENT = Messages.getString("MESSAGE.PALETTE.COMBINEDFRAGMENT"); //$NON-NLS-1$

    /** InteractionUse 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_INTERACTIONUSE = Messages.getString("MESSAGE.PALETTE.INTERACTIONUSE"); //$NON-NLS-1$

    /** CompleteMessage 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_COMPLETEMESSAGE = Messages.getString("MESSAGE.PALETTE.COMPLETEMESSAGE"); //$NON-NLS-1$

    /** LostMessage 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_LOSTMESSAGE = Messages.getString("MESSAGE.PALETTE.LOSTMESSAGE"); //$NON-NLS-1$

    /** FoundMessage 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_FOUNDMESSAGE = Messages.getString("MESSAGE.PALETTE.FOUNDMESSAGE"); //$NON-NLS-1$

    /** Usecase 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_USECASE = Messages.getString("MESSAGE.PALETTE.USECASE"); //$NON-NLS-1$

    /** Actor 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_ACTOR = Messages.getString("MESSAGE.PALETTE.ACTOR"); //$NON-NLS-1$

    /** Extend 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_EXTEND = Messages.getString("MESSAGE.PALETTE.EXTEND"); //$NON-NLS-1$

    /** Include 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_INCLUDE = Messages.getString("MESSAGE.PALETTE.INCLUDE"); //$NON-NLS-1$

    /** ComponentRealization 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_COMPONENTREALIZATION = Messages.getString("MESSAGE.PALETTE.COMPONENTREALIZATION"); //$NON-NLS-1$

    /** InterfaceRealization 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_INTERFACEREALIZATION = Messages.getString("MESSAGE.PALETTE.INTERFACEREALIZATION"); //$NON-NLS-1$

    /** ProvidedInterface 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_PROVIDEDINTERFACE = Messages.getString("MESSAGE.PALETTE.PROVIDEDINTERFACE"); //$NON-NLS-1$

    /** RequiredInterface 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_REQUIREDINTERFACE = Messages.getString("MESSAGE.PALETTE.REQUIREDINTERFACE"); //$NON-NLS-1$

    /** Connector 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_CONNECTOR = Messages.getString("MESSAGE.PALETTE.CONNECTOR"); //$NON-NLS-1$

    /** CollaborationUse 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_COLLABORATIONUSE = Messages.getString("MESSAGE.PALETTE.COLLABORATIONUSE"); //$NON-NLS-1$

    /** Part 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_PART = Messages.getString("MESSAGE.PALETTE.PART"); //$NON-NLS-1$

    /** InitialNodel 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_INITIALNODE = Messages.getString("MESSAGE.PALETTE.INITIALNODE"); //$NON-NLS-1$

    /** Action 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_ACTION = Messages.getString("MESSAGE.PALETTE.ACTION"); //$NON-NLS-1$

    /** ValuePin 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_VALUEPIN = Messages.getString("MESSAGE.PALETTE.VALUEPIN"); //$NON-NLS-1$

    /** PackageImport 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_PACKAGEIMPORT = Messages.getString("MESSAGE.PALETTE.PACKAGEIMPORT"); //$NON-NLS-1$

    /** PackageMerge 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_PACKAGEMERGE = Messages.getString("MESSAGE.PALETTE.PACKAGEMERGE"); //$NON-NLS-1$

    /** Collaboration 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_COLLABORATION = Messages.getString("MESSAGE.PALETTE.COLLABORATION"); //$NON-NLS-1$

    /** DataType 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_DATATYPE = Messages.getString("MESSAGE.PALETTE.DATATYPE"); //$NON-NLS-1$

    /** Signal 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_SIGNAL = Messages.getString("MESSAGE.PALETTE.SIGNAL"); //$NON-NLS-1$

    /** DestructionEvent 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_DESTRUCTIONEVENT = Messages.getString("MESSAGE.PALETTE.DESTRUCTIONEVENT"); //$NON-NLS-1$    

    /** Asynch_Call 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_ASYNCH_CALL = Messages.getString("MESSAGE.PALETTE.ASYNCH_CALL"); //$NON-NLS-1$

    /** Synch_Call 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_SYNCH_CALL = Messages.getString("MESSAGE.PALETTE.SYNCH_CALL"); //$NON-NLS-1$

    /** Create_Message 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_CREATE_MESSAGE = Messages.getString("MESSAGE.PALETTE.CREATE_MESSAGE"); //$NON-NLS-1$

    /** Delete_Message 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_DELETE_MESSAGE = Messages.getString("MESSAGE.PALETTE.DELETE_MESSAGE"); //$NON-NLS-1$

    /** Reply 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_REPLY = Messages.getString("MESSAGE.PALETTE.REPLY"); //$NON-NLS-1$

    /** Interaction_ALT 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_INTERACTION_ALT = Messages.getString("MESSAGE.PALETTE.INTERACTION_ALT"); //$NON-NLS-1$

    /** Interaction_ASSERT 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_INTERACTION_ASSERT = Messages.getString("MESSAGE.PALETTE.INTERACTION_ASSERT"); //$NON-NLS-1$

    /** Interaction_BREAK 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_INTERACTION_BREAK = Messages.getString("MESSAGE.PALETTE.INTERACTION_BREAK"); //$NON-NLS-1$

    /** Interaction_CONSIDER 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_INTERACTION_CONSIDER = Messages.getString("MESSAGE.PALETTE.INTERACTION_CONSIDER"); //$NON-NLS-1$

    /** Interaction_CRITICAL 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_INTERACTION_CRITICAL = Messages.getString("MESSAGE.PALETTE.INTERACTION_CRITICAL"); //$NON-NLS-1$

    /** Interaction_IGNORE 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_INTERACTION_IGNORE = Messages.getString("MESSAGE.PALETTE.INTERACTION_IGNORE"); //$NON-NLS-1$

    /** Interaction_LOOP 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_INTERACTION_LOOP = Messages.getString("MESSAGE.PALETTE.INTERACTION_LOOP"); //$NON-NLS-1$

    /** Interaction_NEG 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_INTERACTION_NEG = Messages.getString("MESSAGE.PALETTE.INTERACTION_NEG"); //$NON-NLS-1$

    /** Interaction_OPT 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_INTERACTION_OPT = Messages.getString("MESSAGE.PALETTE.INTERACTION_OPT"); //$NON-NLS-1$

    /** Interaction_PAR 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_INTERACTION_PAR = Messages.getString("MESSAGE.PALETTE.INTERACTION_PAR"); //$NON-NLS-1$

    /** Interaction_SEQ 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_INTERACTION_SEQ = Messages.getString("MESSAGE.PALETTE.INTERACTION_SEQ"); //$NON-NLS-1$

    /** Interaction_STRICT 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_INTERACTION_STRICT = Messages.getString("MESSAGE.PALETTE.INTERACTION_STRICT"); //$NON-NLS-1$

    /** MESSAGE_PALETTE_OPTION_IF 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_OPTION_IF = Messages.getString("MESSAGE_PALETTE_OPTION_IF"); //$NON-NLS-1$

    /** MESSAGE_PALETTE_ALTERNATIVE_IFELSE 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_ALTERNATIVE_IFELSE = Messages.getString("MESSAGE_PALETTE_ALTERNATIVE_IFELSE"); //$NON-NLS-1$

    /** MESSAGE_PALETTE_ALTERNATIVE_SWITCH 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_ALTERNATIVE_SWITCH = Messages.getString("MESSAGE_PALETTE_ALTERNATIVE_SWITCH"); //$NON-NLS-1$

    /** MESSAGE_PALETTE_LOOT_WHILE 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_LOOT_WHILE = Messages.getString("MESSAGE_PALETTE_LOOT_WHILE"); //$NON-NLS-1$

    /** MESSAGE_PALETTE_LOOT_FOR 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_LOOT_FOR = Messages.getString("MESSAGE_PALETTE_LOOT_FOR"); //$NON-NLS-1$

    /** Binary_Association 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_BINARY_ASSOCIATION = Messages.getString("MESSAGE.PALETTE.BINARY_ASSOCIATION"); //$NON-NLS-1$

    /** Directed_Association 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_DIRECTED_ASSOCIATION = Messages.getString("MESSAGE.PALETTE.DIRECTED_ASSOCIATION"); //$NON-NLS-1$

    /** Shared_Binary_Association 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_SHARED_BINARY_ASSOCIATION = Messages.getString("MESSAGE.PALETTE.SHARED_BINARY_ASSOCIATION"); //$NON-NLS-1$

    /** Shared_Directed_Association 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_SHARED_DIRECTED_ASSOCIATION = Messages.getString("MESSAGE.PALETTE.SHARED_DIRECTED_ASSOCIATION"); //$NON-NLS-1$

    /** Composite_Binary_Association 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_COMPOSITE_BINARY_ASSOCIATION = Messages.getString("MESSAGE.PALETTE.COMPOSITE_BINARY_ASSOCIATION"); //$NON-NLS-1$

    /** Composite_Directed_Association 팔레트 메시지 스트링 */
    public static final String MESSAGE_PALETTE_COMPOSITE_DIRECTED_ASSOCIATION = Messages.getString("MESSAGE.PALETTE.COMPOSITE_DIRECTED_ASSOCIATION"); //$NON-NLS-1$

    /** UML 프로파일을 로컬파일 시스템으로부터 작업공간으로 가져온다 */
    public static final String MESSAGE_IMPORT_UMLPROFILE_INTO_WORKSPACE = Messages.getString("MESSAGE.IMPORT.UMLPROFILE.INTO.WORKSPACE"); //$NON-NLS-1$

    /** 이 섹션은 이 모델에 대한 일반 정보를 설명합니다. */
    public static final String MESSAGE_OVERVIEW = Messages.getString("MESSAGE.OVERVIEW"); //$NON-NLS-1$

    /** 이 섹션에서는 이 모델에 적용되는 UML 프로파일을 나열합니다. */
    public static final String MESSAGE_PROFILE_LIST = Messages.getString("MESSAGE.PROFILE.LIST"); //$NON-NLS-1$

    /** 적용된 스테레오타입을 해제하시겠습니까? */
    public static final String MESSAGE_UNAPPLY_STEREOTYPE = Messages.getString("MESSAGE.UNAPPLY.STEREOTYPE"); //$NON-NLS-1$

    /** 선택된 항목이 없습니다. */
    public static final String MESSAGE_NO_SELECTION = Messages.getString("MESSAGE.NO.SELECTION"); //$NON-NLS-1$

    /** 값을 입력하세요. */
    public static final String MESSAGE_INPUT_VALUE = Messages.getString("MESSAGE.INPUT.VALUE");//$NON-NLS-1$

    /** 이 섹션은 이 모델에 대한 일반 문서를 나열합니다. */
    public static final String MESSAGE_DOCUMENT_OVERVIEW = Messages.getString("MESSAGE.DOCUMENT.OVERVIEW"); //$NON-NLS-1$

    /** 이 섹션에서는 이 모델에 대한 프로젝트 정보를 설명합니다. */
    public static final String MESSAGE_PROJECT_INFORMATION_OVERVIEW = Messages.getString("MESSAGE.PROJECT.INFORMATION.OVERVIEW"); //$NON-NLS-1$

    /** 이 섹션은 이 모델이 가져온 모델 라이브러리를 나열합니다. */
    public static final String MESSAGE_MODEL_LIBRARY_OVERVIEW = Messages.getString("MESSAGE.MODEL.LIBRARY.OVERVIEW");//$NON-NLS-1$

    /** 내보내기에 성공하였습니다. */
    public static final String MESSAGE_SUCCESS_EXPORT = Messages.getString("MESSAGE.SUCCESS.EXPORT");//$NON-NLS-1$

    /** 내보내기 과정에서 에러가 발생하였습니다. */
    public static final String MESSAGE_ERROR_EXPORT = Messages.getString("MESSAGE.ERROR.EXPORT");//$NON-NLS-1$

    /** 이미 존재하는 파일입니다. */
    public static final String MESSAGE_ERROR_EXIST_FILE = Messages.getString("MESSAGE.ERROR.EXIST.FILE");//$NON-NLS-1$

    /** 라이프라인의 유형이 정해지지 않았습니다. */
    public static final String MESSAGE_UNDEFINED_LIFELINETYPE = Messages.getString("MESSAGE.LIFELINE.UNDEFINED");//$NON-NLS-1$

    /** Object 파라메터가 Null입니다. */
    public static final String MESSAGE_PARAMETER_OBJECT_NULL = Messages.getString("MESSAGE.PARAMETER.OBJECT.NULL"); //$NON-NLS-1$

    /** 매개변수를 추가/삭제 합니다. */
    public static final String MESSAGE_PARAMETER_TEXT = Messages.getString("MESSAGE.PARAMETER_TEXT"); //$NON-NLS-1$

    /** String 파라메터가 빈 문자열입니다. */
    public static final String MESSAGE_PARAMETER_STRING_EMPTY = Messages.getString("MESSAGE.PARAMETER.STRING.EMPTY"); //$NON-NLS-1$

    /** String 파라메터가 Null입니다. */
    public static final String MESSAGE_PARAMETER_STRING_NULL = Messages.getString("MESSAGE.PARAMETER.STRING.NULL"); //$NON-NLS-1$

    /** 한글 */
    public static final String MESSAGE_KOREAN_REG_EXP = Messages.getString("MESSAGE.KOREAN_REG_EXP"); //$NON-NLS-1$

    /** 내보내기할 항목이 선택되지 않았습니다. */
    public static final String MESSAGE_ERROR_CHECKED_ITEM = Messages.getString("MESSAGE.ERROR.CHECKED.ITEM"); //$NON-NLS-1$

    /** 내보낼 파일명이 입력되지 않았습니다. */
    public static final String MESSAGE_ERROR_EMPTY_FILENAME = Messages.getString("MESSAGE.ERROR.EMPTY.FILENAME"); //$NON-NLS-1$

    /** 해당 메시지에 오퍼레이션이 지정되어 있지 않습니다. */
    public static final String MESSAGE_NO_ASSIGNED_OPERATION = Messages.getString("MESSAGE.MESSAGE.OPERATION.NULL"); //$NON-NLS-1$

    /** 선택한 패키지가 이미 단편화된 화일 안에 존재합니다. */
    public static final String MESSAGE_WARN_ALREADYINRESOURCE = Messages.getString("MESSAGE.WARN.ALREADYINRESOURCE"); //$NON-NLS-1$

    /** 선택한 패키지를 단편화할 화일이 읽기 전용입니다. */
    public static final String MESSAGE_WARN_READONLYRESOURCE = Messages.getString("MESSAGE.WARN.READONLYRESOURCE"); //$NON-NLS-1$

    /** 경로에 해당하는 단편화 화일을 생성할 수 없습니다. */
    public static final String MESSAGE_WARN_CANNOTCREATERESOURCE = Messages.getString("MESSAGE.WARN.CANNOTCREATERESOURCE"); //$NON-NLS-1$

    /** 단편화 화일이 이미 존재하지만, 읽을 수가 없습니다. 선택된 패키지로 단편화 화일의 내용을 대체하시겠습니까? */
    public static final String MESSAGE_WARN_REPLACERESOURCE = Messages.getString("MESSAGE.WARN.REPLACERESOURCE"); //$NON-NLS-1$

    /** 단편화 화일이 이미 존재합니다. 이 단편화 화일에 선택한 패키지를 단편화 하시겠습니까? */
    public static final String MESSAGE_WARN_ADDTORESOURCE = Messages.getString("MESSAGE.WARN.ADDTORESOURCE"); //$NON-NLS-1$

    /** 파일 단편화... */
    public static final String MESSAGE_FILE_FRAGMENTATION = Messages.getString("MESSAGE.FILE.FRAGMENTATION"); //$NON-NLS-1$

    /** 파일 단편화 중... */
    public static final String MESSAGE_FILE_FRAGMENTING = Messages.getString("MESSAGE.FILE.FRAGMENTING"); //$NON-NLS-1$

    /** 파일 병합 중... */
    public static final String MESSAGE_FILE_DEFRAGMENTING = Messages.getString("MESSAGE.FILE.DEFRAGMENTING"); //$NON-NLS-1$

    /** 선택한 패키지를 단편화할 파일을 작업공간에서 찾아보기 */
    public static final String MESSAGE_SELECT_FRAGMENTATION_FILE_FROM_WORKSPACE = Messages.getString("MESSAGE.SELECT.FRAGMENTATION.FILE.FROM.WORKSPACE"); //$NON-NLS-1$

    /** 단편화 파일을 저장합니다. */
    public static final String MESSAGE_FRAGMENT_SAVEAS = Messages.getString("MESSAGE.FRAGMENT.SAVEAS"); //$NON-NLS-1$

    /** 해당 요소는 이미 단편화 되어 있습니다. */
    public static final String MESSAGE_FRAGMENT_FRAGMENTED = Messages.getString("MESSAGE.FRAGMENT.FRAGEMENTED"); //$NON-NLS-1$

    /** 리소스 정보가 없습니다. 새로고침 후 다시 실행하세요. */
    public static final String MESSAGE_RESOURCE_IS_NULL = Messages.getString("MESSAGE.RESOURCE.NULL"); //$NON-NLS-1$

    /** 단편화된 요소가 아닙니다. */
    public static final String MESSAGE_FRAGMENT_NOT_FRAGMENTED = Messages.getString("MESSAGE.FRAGMENT.NOT.FRAGEMENTED"); //$NON-NLS-1$

    /** 같은 이름의 파일이 존재합니다. */
    public static final String MESSAGE_FRAGMENT_SAMENAME = Messages.getString("MESSAGE.FRAGMENT.SAMENAME"); //$NON-NLS-1$

    /** 단편화 파일의 확장자는 umf가 되어야 합니다. */
    public static final String MESSAGE_FRAGMENT_EXTENSION_CONSTRAINT = Messages.getString("MESSAGE.FRAGMENT.EXTENSION.CONSTRAINT"); //$NON-NLS-1$

    /** 저장할 수 없습니다. */
    public static final String MESSAGE_SAVE_FAIL = Messages.getString("MESSAGE.SAVE.FAIL"); //$NON-NLS-1$

    /** 병합 후에는 단편 파일이 삭제됩니다. 병합하시겠습니까? */
    public static final String MESSAGE_CONFIRM_MERGE = Messages.getString("MESSAGE.CONFIRM.MERGE"); //$NON-NLS-1$
    
    /** 각 프로젝트 하위의 ".fragment" 폴더에 패키지 구조로 폴더와 단편 파일이 생성됩니다. 계속 진행하시겠습니까? */
    public static final String MESSAGE_CONFIRM_CREATE_STRUCTURED_FRAGMENT = Messages.getString("MESSAGE.CONFIRM.CREATE.FRAGMENT.STRUCTURED"); //$NON-NLS-1$

    /** 이전 실행에 열었던 리소스를 다시 여시겠습니까? / Previous run, opened the resource you want to open again? */
    public static final String MESSAGE_CONFIRM_PERSISTANCE_RESOURCE = Messages.getString("MESSAGE.CONFIRM.PERSISTANCE.RESOURCE"); //$NON-NLS-1$

    /** 에 단편 파일이 생성됩니다. 계속 진행하겠습니까? */
    public static final String MESSAGE_CONFIRM_CREATE_FRAGMENT = Messages.getString("MESSAGE.CONFIRM.CREATE.FRAGMENT"); //$NON-NLS-1$

    /** 묻지않고 항상 생성 */
    public static final String MESSAGE_CREATE_WITHOUT_PROMPT = Messages.getString("MESSAGE.CREATE.WITHOUT.PROMPT"); //$NON-NLS-1$    
    
    /** 묻지않고 항상 Stand Alone 으로 실행 */
    public static final String MESSAGE_ALWAYS_RUN_WITHOUT_PROMPT_STAND_ALONE = Messages.getString("MESSAGE.ALWAYSRUN.WITHOUT.PROMPT"); //$NON-NLS-1$ 

    /** 묻지않고 항상 열기 */
    public static final String MESSAGE_ALWAYS_RUN_WITHOUT_PROMPT_PERSISTANCE_RESOURCE = Messages.getString("MESSAGE.ALWAYSRUN.WITHOUT.PROMPT.PERSISTANCE.RESOURCE"); //$NON-NLS-1$ 

    /** UML 모델 닫기 사용   */
    public static final String MESSAGE_USE_MODEL_CLOSE = Messages.getString("MESSAGE.USE_MODEL_CLOSE"); //$NON-NLS-1$ 

    /** 다음 다이어그램에 닫을 자원으로부터 정보를 의존하고 있는 요소가 있습니다. */
    public static final String MESSAGE_DEPENDENT_INFORMATION = Messages.getString("MESSAGE.DEPENDENT_INFORMATION"); //$NON-NLS-1$ 

    /** 종속 자원 및 다이어그램이 닫힙니다. 계속하시겠습니까? */
    public static final String MESSAGE_DEPENDENT_INFORMATION2 = Messages.getString("MESSAGE.DEPENDENT_INFORMATION2"); //$NON-NLS-1$ 

    /** 용어―한글명(영문명_약어)이(가) 이미 존재합니다. */
    public static final String MESSAGE_ALREADY_EXIST_WORD = Messages.getString("MESSAGE.ALREADY.EXIST.WORD"); //$NON-NLS-1$

    /** 편집기 외부에서 만들어진 변경과 충돌하는 변경은 보존하지 않습니다.이 편집기의 변경을 삭제하시겠습니까? */
    public static final String MESSAGE_WARN_FILECONFLIT = Messages.getString("MESSAGE.WARN.FILECONFLIT"); //$NON-NLS-1$

    /** RM 데이터 엑셀 파일을 작업공간으로 가져와 데이터로 변환한다 */
    public static final String MESSAGE_IMPORT_RM_DATA_EXCEL_FILE_INTO_WORKSPACE = Messages.getString("MESSAGE.IMPORT.RM.DATA.EXCEL.FILE.INTO.WORKSPACE"); //$NON-NLS-1$

    /** Requirements Manager에서 가져온 데이터가 존재하지 않습니다. */
    public static final String MESSAGE_ERROR_NO_IMPORTED_RM_DATA = Messages.getString("MESSAGE.ERROR.NO.IMPORTED.RM.DATA"); //$NON-NLS-1$

    /** 'Import > RM 데이터 엑셀 파일' 기능을 이용하셔서 RM 데이터를 임포트 하시기 바랍니다. */
    public static final String MESSAGE_IMPORT_RM_DATA_EXCEL_FILE = Messages.getString("MESSAGE.IMPORT.RM.DATA.EXCEL.FILE"); //$NON-NLS-1$

    /** Requirements Manager와 연동하기 위해서는 UseCase의 연관된 요구사항ID는 필수 입력사항입니다. */
    public static final String MESSAGE_REQUIREMENT_ID_IS_MANDATORY = Messages.getString("MESSAGE.REQUIREMENT.ID.IS.MANDATORY"); //$NON-NLS-1$

    /** UseCase에 적용된 스테레오타입의 requirementId 값을 설정하세요. */
    public static final String MESSAGE_SET_RELATED_REQUIREMENT_ID_OF_USECASE = Messages.getString("MESSAGE.SET.RELATED.REQUIREMENT.ID.OF.USECASE"); //$NON-NLS-1$

    /** Model 경고 */
    public static final String MESSAGE_WARNNING_MODEL = Messages.getString("MESSAGE.WARNNING.MODEL"); //$NON-NLS-1$

    /** Model 경고 메시지 */
    public static final String MESSAGE_WARNNING_OF_WRONGMODEL = Messages.getString("MESSAGE.WARNNING.OF.WRONGMODEL"); //$NON-NLS-1$

    /** 이 섹션에서는 이 모델의 상세 정보를 설명합니다. */
    public static final String MESSAGE_MODEL_DETAIL_INFORMATION_OVERVIEW = Messages.getString("MESSAGE.MODEL.DETAIL.INFORMATION.OVERVIEW"); //$NON-NLS-1$

    /** RM 데이터가 없습니다. */
    public static final String MESSAGE_NO_RM_DATA = Messages.getString("MESSAGE.NO.RM.DATA"); //$NON-NLS-1$

    /** 이미지로 저장 */
    public static final String LABEL_SAVE_TO_IMAGE = Messages.getString("LABEL.SAVE.TO.IMAGE"); //$NON-NLS-1$
    
    /** 실행 취소 */
    public static final String LABEL_UNDO = Messages.getString("LABEL.UNDO"); //$NON-NLS-1$
    
    /** 다시 실행 */
    public static final String LABEL_REDO = Messages.getString("LABEL.REDO"); //$NON-NLS-1$
    
    /** 프린트 미리보기 */
    public static final String LABEL_PRINT_PREVIEW = Messages.getString("LABEL.PRINT.PREVIEW"); //$NON-NLS-1$
    
    /** 여백이 프린트 용지의 크기를 벗어났습니다 */
    public static final String MESSAGE_PRINT_PREVIEW_ERROR_MARGIN = Messages.getString("MESSAGE.PRINT.PREVIEW.ERROR.MARGIN"); //$NON-NLS-1$

    /** Message - cannot paste element. */
    public static final String MESSAGE_CANNOT_PASTE = Messages.getString("MESSAGE.CANNOT_PASTE"); //$NON-NLS-1$

    /** Message - diagram cannot be pasted. */
    public static final String MESSAGE_DIAGRAM_CANNOT_PASTE = Messages.getString("MESSAGE.DIAGRAM.CANNOT_PASTE"); //$NON-NLS-1$

    /** Message - cannot paste element. */
    public static final String MESSAGE_CANNOT_MOVE = Messages.getString("MESSAGE.CANNOT_MOVE"); //$NON-NLS-1$

    /** Message - 하위에 다이어그램이 있는 요소는 이동할 수 없습니다. : ${0} */
    public static final String MESSAGE_CANNOT_MOVE_ELEMENT_DIAGRAM = Messages.getString("MESSAGE.CANNOT_MOVE.ELEMENT.DIAGRAM"); //$NON-NLS-1$

    /** SaveToImage Action */
    public static final String MESSAGE_SAVE_TO_IMAGE_ACTION = Messages.getString("MESSAGE.SAVE.TO.IMAGE.ACTION"); //$NON-NLS-1$

    /** MESSAGE_ACTIVITY_DIRECT_EDIT_CELL_EDITOR_LOCATOR_RELOCATE_ERROR */
    public static final String MESSAGE_ACTIVITY_DIRECT_EDIT_CELL_EDITOR_LOCATOR_RELOCATE_ERROR = Messages.getString("MESSAGE_ACTIVITY_DIRECT_EDIT_CELL_EDITOR_LOCATOR_RELOCATE_ERROR"); //$NON-NLS-1$

    /** MESSAGE_LOLLY_LABEL_DIAGRAM_EDIT_PART_REFRESH_VISUALS_ERROR */
    public static final String MESSAGE_LOLLY_LABEL_DIAGRAM_EDIT_PART_REFRESH_VISUALS_ERROR = Messages.getString("MESSAGE_LOLLY_LABEL_DIAGRAM_EDIT_PART_REFRESH_VISUALS_ERROR"); //$NON-NLS-1$

    /** MESSAGE_HALF_LOLLY_DIAGRAM_EDIT_PART_REFRESH_VISUALS_ERROR */
    public static final String MESSAGE_HALF_LOLLY_DIAGRAM_EDIT_PART_REFRESH_VISUALS_ERROR = Messages.getString("MESSAGE_HALF_LOLLY_DIAGRAM_EDIT_PART_REFRESH_VISUALS_ERROR"); //$NON-NLS-1$

    /** MESSAGE_ARTIFACT_EDIT_PART_REFRESH_VISUALS_ERROR */
    public static final String MESSAGE_ARTIFACT_EDIT_PART_REFRESH_VISUALS_ERROR = Messages.getString("MESSAGE_ARTIFACT_EDIT_PART_REFRESH_VISUALS_ERROR"); //$NON-NLS-1$

    /** MESSAGE_NOTATION_LABEL_EDIT_PART_REFRESH_VISUALS_ERROR */
    public static final String MESSAGE_NOTATION_LABEL_EDIT_PART_REFRESH_VISUALS_ERROR = Messages.getString("MESSAGE_NOTATION_LABEL_EDIT_PART_REFRESH_VISUALS_ERROR"); //$NON-NLS-1$

    /** MESSAGE_MERGE_NODE_EDIT_PART_REFRESH_VISUALS_ERROR */
    public static final String MESSAGE_MERGE_NODE_EDIT_PART_REFRESH_VISUALS_ERROR = Messages.getString("MESSAGE_MERGE_NODE_EDIT_PART_REFRESH_VISUALS_ERROR"); //$NON-NLS-1$

    /** MESSAGE_JOIN_NODE_EDIT_PART_REFRESH_VISUALS_ERROR */
    public static final String MESSAGE_JOIN_NODE_EDIT_PART_REFRESH_VISUALS_ERROR = Messages.getString("MESSAGE_JOIN_NODE_EDIT_PART_REFRESH_VISUALS_ERROR"); //$NON-NLS-1$

    /** MESSAGE_INITIAL_NODE_EDIT_PART_REFREAH_VISUALS_ERROR */
    public static final String MESSAGE_INITIAL_NODE_EDIT_PART_REFREAH_VISUALS_ERROR = Messages.getString("MESSAGE_INITIAL_NODE_EDIT_PART_REFREAH_VISUALS_ERROR"); //$NON-NLS-1$

    /** MESSAGE_FORK_NODE_EDIT_PART_REFRESH_VISUALS_ERROR */
    public static final String MESSAGE_FORK_NODE_EDIT_PART_REFRESH_VISUALS_ERROR = Messages.getString("MESSAGE_FORK_NODE_EDIT_PART_REFRESH_VISUALS_ERROR"); //$NON-NLS-1$

    /** MESSAGE_DECISION_NODE_EDIT_PART_REFRESH_VISUALS_ERROR */
    public static final String MESSAGE_DECISION_NODE_EDIT_PART_REFRESH_VISUALS_ERROR = Messages.getString("Message_DECISION_NODE_EDIT_PART_REFRESH_VISUALS_ERROR"); //$NON-NLS-1$

    /** MESSAGE_DATA_STORE_NODE_EDIT_PART_REFRESH_VISUALS_ERROR */
    public static final String MESSAGE_DATA_STORE_NODE_EDIT_PART_REFRESH_VISUALS_ERROR = Messages.getString("Message_DATA_STORE_NODE_EDIT_PART_REFRESH_VISUALS_ERROR"); //$NON-NLS-1$

    /** MESSAGE_CENTRAL_BUFFER_NODE_EDIT_PART_REFRESH_VISUALS */
    public static final String MESSAGE_CENTRAL_BUFFER_NODE_EDIT_PART_REFRESH_VISUALS = Messages.getString("MESSAGE_CENTRAL_BUFFER_NODE_EDIT_PART_REFRESH_VISUALS"); //$NON-NLS-1$

    /** MESSAGE_ACTIVITY_DIAGRAM_EDIT_PART_NOTIFY_CHANGED_ERROR */
    public static final String MESSAGE_ACTIVITY_DIAGRAM_EDIT_PART_NOTIFY_CHANGED_ERROR = Messages.getString("MESSAGE_ACTIVITY_DIAGRAM_EDIT_PART_NOTIFY_CHANGED_ERROR"); //$NON-NLS-1$

    /** MESSAGE_ERROR_CREATING_UML_MODEL */
    public static final String MESSAGE_ERROR_CREATING_UML_MODEL = Messages.getString("MESSAGE_ERROR_CREATING_UML_MODEL"); //$NON-NLS-1$

    /** MESSAGE_CREATION_PROBLEM */
    public static final String MESSAGE_CREATION_PROBLEM = Messages.getString("MESSAGE_CREATION_PROBLEM"); //$NON-NLS-1$

    /** MESSAGE_MESSAGE_EDIT_PART_REFRESH_VISUALS_ERROR */
    public static final String MESSAGE_MESSAGE_EDIT_PART_REFRESH_VISUALS_ERROR = Messages.getString("MESSAGE_MESSAGE_EDIT_PART_REFRESH_VISUALS_ERROR"); //$NON-NLS-1$

    /** MESSAGE_LINE_EDIT_PART_REFRESH_VISUALS_ERROR */
    public static final String MESSAGE_LINE_EDIT_PART_REFRESH_VISUALS_ERROR = Messages.getString("MESSAGE_LINE_EDIT_PART_REFRESH_VISUALS_ERROR"); //$NON-NLS-1$

    /** MESSAGE_LIFELINE_NAME_HEADER_EDIT_PART_REFRESH_VISUALS_ERROR */
    public static final String MESSAGE_LIFELINE_NAME_HEADER_EDIT_PART_REFRESH_VISUALS_ERROR = Messages.getString("MESSAGE_LIFELINE_NAME_HEADER_EDIT_PART_REFRESH_VISUALS_ERROR"); //$NON-NLS-1$

    /** MESSAGE_LIFELINE_NAME_EDIT_PART_REFRESH_VISUALS_ERROR */
    public static final String MESSAGE_LIFELINE_NAME_EDIT_PART_REFRESH_VISUALS_ERROR = Messages.getString("MESSAGE_LIFELINE_NAME_EDIT_PART_REFRESH_VISUALS_ERROR"); //$NON-NLS-1$

    /** MESSAGE_LIFELINE_EDIT_PART_REFRESH_VISUALS_ERROR */
    public static final String MESSAGE_LIFELINE_EDIT_PART_REFRESH_VISUALS_ERROR = Messages.getString("MESSAGE_LIFELINE_EDIT_PART_REFRESH_VISUALS_ERROR"); //$NON-NLS-1$

    /** MESSAGE_LIFELINE_BEHAVIOR_EDIT_PART_REFRESH_VISUALS_ERROR */
    public static final String MESSAGE_LIFELINE_BEHAVIOR_EDIT_PART_REFRESH_VISUALS_ERROR = Messages.getString("MESSAGE_LIFELINE_BEHAVIOR_EDIT_PART_REFRESH_VISUALS_ERROR"); //$NON-NLS-1$

    /** MESSAGE_INTERACTION_USE_EDIT_PART_REFRESH_VISUALS_ERROR */
    public static final String MESSAGE_INTERACTION_USE_EDIT_PART_REFRESH_VISUALS_ERROR = Messages.getString("MESSAGE_INTERACTION_USE_EDIT_PART_REFRESH_VISUALS_ERROR"); //$NON-NLS-1$

    /** MESSAGE_DESTRUCTION_EVENT_EDIT_PART_REFRESH_VISUALS_ERROR */
    public static final String MESSAGE_DESTRUCTION_EVENT_EDIT_PART_REFRESH_VISUALS_ERROR = Messages.getString("MESSAGE_DESTRUCTION_EVENT_EDIT_PART_REFRESH_VISUALS_ERROR"); //$NON-NLS-1$

    /** MESSAGE_ENUMERATION_EDIT_PART_REFRESH_VISUALS_ERROR */
    public static final String MESSAGE_ENUMERATION_EDIT_PART_REFRESH_VISUALS_ERROR = Messages.getString("MESSAGE_ENUMERATION_EDIT_PART_REFRESH_VISUALS_ERROR"); //$NON-NLS-1$

    /** MESSAGE_DATA_TYPE_EDIT_PART_REFRESH_VISUALS_ERROR */
    public static final String MESSAGE_DATA_TYPE_EDIT_PART_REFRESH_VISUALS_ERROR = Messages.getString("MESSAGE_DATA_TYPE_EDIT_PART_REFRESH_VISUALS_ERROR"); //$NON-NLS-1$

    /** MESSAGE_COLLABORATION_EDIT_PART_REFRESH_VISUALS_ERROR */
    public static final String MESSAGE_COLLABORATION_EDIT_PART_REFRESH_VISUALS_ERROR = Messages.getString("MESSAGE_COLLABORATION_EDIT_PART_REFRESH_VISUALS_ERROR"); //$NON-NLS-1$

    /** MESSAGE_CLASS_EDIT_PART_REFRESH_VISUAL_ERROR */
    public static final String MESSAGE_CLASS_EDIT_PART_REFRESH_VISUAL_ERROR = Messages.getString("MESSAGE_CLASS_EDIT_PART_REFRESH_VISUAL_ERROR"); //$NON-NLS-1$

    /** MESSAGE_ASSOCIATION_EDIT_PART_REFRESH_VISUALS_ERROR */
    public static final String MESSAGE_ASSOCIATION_EDIT_PART_REFRESH_VISUALS_ERROR = Messages.getString("MESSAGE_ASSOCIATION_EDIT_PART_REFRESH_VISUALS_ERROR"); //$NON-NLS-1$

    /** MESSAGE_ABSTRACT_DIAGRAM_CONNECTION_EDIT_PART_ERROR */
    public static final String MESSAGE_ABSTRACT_DIAGRAM_CONNECTION_EDIT_PART_ERROR = Messages.getString("MESSAGE_ABSTRACT_DIAGRAM_CONNECTION_EDIT_PART_ERROR"); //$NON-NLS-1$

    /** 모델 찾기 */
    public static final String MESSAGE_SEARCH = Messages.getString("MESSAGE.SEARCH"); //$NON-NLS-1$

    /** 유스케이스 추적 매트릭스를 생성할 분석모델을 선택하세요. */
    public static final String MESSAGE_SELECT_ANALYSIS_MODEL_FOR_CREATE_USECASE_TRACE_MATRIX = Messages.getString("MESSAGE.SELECT.ANALYSIS.MODEL.FOR.CREATE.USECASE.TRACE.MATRIX"); //$NON-NLS-1$

    /** 분석모델을 선택하세요 */
    public static final String MESSAGE_SELECT_ANALYSISMODEL = Messages.getString("MESSAGE.SELECT.ANALYSISMODEL"); //$NON-NLS-1$

    /** 분석모델을 선택하지 않았습니다. */
    public static final String MESSAGE_DID_NOT_SELECT_ANALYSISMODEL = Messages.getString("MESSAGE.DID.NOT.SELECT.ANALYSISMODEL"); //$NON-NLS-1$

    /** 유스케이스 추적 매트릭스를 생성할 설계모델을 선택하세요. */
    public static final String MESSAGE_SELECT_DESIGN_MODEL_FOR_CREATE_USECASE_TRACE_MATRIX = Messages.getString("MESSAGE.SELECT.DESIGN.MODEL.FOR.CREATE.USECASE.TRACE.MATRIX"); //$NON-NLS-1$

    /** 설계모델을 선택하세요 */
    public static final String MESSAGE_SELECT_DESIGNMODEL = Messages.getString("MESSAGE.SELECT.DESIGNMODEL"); //$NON-NLS-1$

    /** 설계모델을 선택하지 않았습니다. */
    public static final String MESSAGE_DID_NOT_SELECT_DESIGNMODEL = Messages.getString("MESSAGE.DID.NOT.SELECT.DESIGNMODEL"); //$NON-NLS-1$

    /** 분석모델과 설계모델을 같은 모델로 선택할 수 없습니다. */
    public static final String MESSAGE_WARNING_DO_NOT_SELECT_SAME_MODEL = Messages.getString("MESSAGE.WARNING.DO.NOT.SELECT.SAME.MODEL"); //$NON-NLS-1$

    /** 다음 버전에서 구현될 예정입니다. */
    public static final String MESSAGE_TO_BE_DETERMINED = Messages.getString("MESSAGE.TO.BE.DETERMINED"); //$NON-NLS-1$

    /** ${0} 클래스 다이어그램을 생성할 수 없습니다! */
    public static final String MESSAGE_ERROR_CANT_CREATE_CLASS_DIAGRAM_FOR = Messages.getString("MESSAGE.ERROR.CANT.CREATE.CLASS.DIAGRAM.FOR"); //$NON-NLS-1$

    /** ${0}을 찾을 수 없습니다! */
    public static final String MESSAGE_ERROR_CANT_FIND = Messages.getString("MESSAGE.ERROR.CANT.FIND"); //$NON-NLS-1$
    
    /** 계속 진행합니다. */
    public static final String MESSAGE_CONTINUE = Messages.getString("MESSAGE.CONTINUE"); //$NON-NLS-1$

    /** 용어 사전을 읽을 수 없습니다. */
    public static final String MESSAGE_ERROR_CANT_READ_THE_GLOSSARY_DICTIONARY = Messages.getString("MESSAGE.ERROR.CANT.READ.THE.GLOSSARY.DICTIONARY"); //$NON-NLS-1$
    
    /** 용어 변환 미적용 */
    public static final String MESSAGE_GLOSSARY_TRANSLATION_UNAPPLIED = Messages.getString("MESSAGE.GLOSSARY.TRANSLATION.UNAPPLIED"); //$NON-NLS-1$

    /** 복사할 원본 설계모델파일이 존재하지 않습니다. (원본파일 : ${0}) */
    public static final String MESSAGE_ERROR_ORIGINAL_DESIGN_MODEL_FILE_DOES_NOT_EXIST_FOR_COPYING = Messages.getString("MESSAGE.ERROR.ORIGINAL.DESIGN.MODEL.FILE.DOES.NOT.EXIST.FOR.COPYING"); //$NON-NLS-1$
    
    /** 같은 이름의 용어 사전 파일이 이미 존재합니다. */
    public static final String MESSAGE_GLOSSARY_DICTIONARY_FILE_ALREADY_EXISTS = Messages.getString("MESSAGE.GLOSSARY.DICTIONARY.FILE.ALREADY.EXISTS"); //$NON-NLS-1$

    /** 용어 사전의 생성이 정상적으로 완료되었습니다. */
    public static final String MESSAGE_GLOSSARY_DICTIONARY_GENERATION_HAS_BEEN_COMPLETED_SUCCESSFULLY = Messages.getString("MESSAGE.GLOSSARY.DICTIONARY.GENERATION.HAS.BEEN.COMPLETED.SUCCESSFULLY"); //$NON-NLS-1$

    /** 용어 사전의 생성이 정상적으로 완료되지 않았습니다. */
    public static final String MESSAGE_ERROR_GLOSSARY_DICTIONARY_GENERATION_FAILED = Messages.getString("MESSAGE.ERROR.GLOSSARY.DICTIONARY.GENERATION.FAILED"); //$NON-NLS-1$
    
    /** 용어 사전의 생성이 정상적으로 완료되었습니다. */
    public static final String MESSAGE_GLOSSARY_DICTIONARY_HAS_BEEN_CHANGED = Messages.getString("MESSAGE.GLOSSARY.DICTIONARY.HAS.BEEN.CHANGED"); //$NON-NLS-1$

    /** 컴포넌트 생성 */
    public static final String MESSAGE_CREATING_COMPONENT = Messages.getString("MESSAGE.CREATING.COMPONENT"); //$NON-NLS-1$

    /** 패키지 생성 */
    public static final String MESSAGE_CREATING_PACKAGE = Messages.getString("MESSAGE.CREATING.PACKAGE"); //$NON-NLS-1$

    /** 클래스 생성 */
    public static final String MESSAGE_CREATING_CLASS = Messages.getString("MESSAGE.CREATING.CLASS"); //$NON-NLS-1$

    /** 인터페이스 생성 */
    public static final String MESSAGE_CREATING_INTERFACE = Messages.getString("MESSAGE.CREATING.INTERFACE"); //$NON-NLS-1$

    /** 컬레보레이션 생성 */
    public static final String MESSAGE_CREATING_COLLABORATION = Messages.getString("MESSAGE.CREATING.COLLABORATION"); //$NON-NLS-1$

    /** 인터랙션 생성 */
    public static final String MESSAGE_CREATING_INTERACTION = Messages.getString("MESSAGE.CREATING.INTERACTION"); //$NON-NLS-1$

    /** 어트리뷰트 생성 */
    public static final String MESSAGE_CREATING_PRORERTY = Messages.getString("MESSAGE.CREATING.PRORERTY"); //$NON-NLS-1$

    /** 오퍼레이션 생성 */
    public static final String MESSAGE_CREATING_OPERATION = Messages.getString("MESSAGE.CREATING.OPERATION"); //$NON-NLS-1$

    /** 파라미터 생성 */
    public static final String MESSAGE_CREATING_PARAMETER = Messages.getString("MESSAGE.CREATING.PARAMETER"); //$NON-NLS-1$

    /** 클래스 다이어그램 생성 */
    public static final String MESSAGE_CREATING_CLASS_DIAGRAM = Messages.getString("MESSAGE.CREATING.CLASS.DIAGRAM"); //$NON-NLS-1$

    /** 시퀀스 다이어그램 생성 */
    public static final String MESSAGE_CREATING_SEQUENCE_DIAGRAM = Messages.getString("MESSAGE.CREATING.SEQUENCE.DIAGRAM"); //$NON-NLS-1$

    /** 선택된 모델의 변경이 정상적으로 완료되었습니다. */
    public static final String MESSAGE_TRANSLATION_OF_SELECTED_MODEL_HAS_BEEN_COMPLETED_SUCCESSFULLY = Messages.getString("MESSAGE.TRANSLATION.OF.SELECTED.MODEL.HAS.BEEN.COMPLETED.SUCCESSFULLY"); //$NON-NLS-1$

    /** 선택된 모델의 변경이 정상적으로 완료되지 않았습니다. */
    public static final String MESSAGE_ERROR_TRANSLATION_OF_SELECTED_MODEL_FAILED = Messages.getString("MESSAGE.ERROR.TRANSLATION.OF.SELECTED.MODEL.FAILED"); //$NON-NLS-1$

    /** 컴포넌트 변환 */
    public static final String MESSAGE_TRANSLATING_COMPONENT = Messages.getString("MESSAGE.TRANSLATING.COMPONENT"); //$NON-NLS-1$

    /** 패키지 변환 */
    public static final String MESSAGE_TRANSLATING_PACKAGE = Messages.getString("MESSAGE.TRANSLATING.PACKAGE"); //$NON-NLS-1$

    /** 클래스 변환 */
    public static final String MESSAGE_TRANSLATING_CLASS = Messages.getString("MESSAGE.TRANSLATING.CLASS"); //$NON-NLS-1$

    /** 인터페이스 변환 */
    public static final String MESSAGE_TRANSLATING_INTERFACE = Messages.getString("MESSAGE.TRANSLATING.INTERFACE"); //$NON-NLS-1$

    /** 컬레보레이션 변환 */
    public static final String MESSAGE_TRANSLATING_COLLABORATION = Messages.getString("MESSAGE.TRANSLATING.COLLABORATION"); //$NON-NLS-1$

    /** 인터랙션 변환 */
    public static final String MESSAGE_TRANSLATING_INTERACTION = Messages.getString("MESSAGE.TRANSLATING.INTERACTION"); //$NON-NLS-1$

    /** 어트리뷰트 변환 */
    public static final String MESSAGE_TRANSLATING_PRORERTY = Messages.getString("MESSAGE.TRANSLATING.PRORERTY"); //$NON-NLS-1$

    /** 오퍼레이션 변환 */
    public static final String MESSAGE_TRANSLATING_OPERATION = Messages.getString("MESSAGE.TRANSLATING.OPERATION"); //$NON-NLS-1$

    /** 파라미터 변환 */
    public static final String MESSAGE_TRANSLATING_PARAMETER = Messages.getString("MESSAGE.TRANSLATING.PARAMETER"); //$NON-NLS-1$

    /** 클래스 다이어그램 변환 */
    public static final String MESSAGE_TRANSLATING_CLASS_DIAGRAM = Messages.getString("MESSAGE.TRANSLATING.CLASS.DIAGRAM"); //$NON-NLS-1$

    /** 시퀀스 다이어그램 변환 */
    public static final String MESSAGE_TRANSLATING_SEQUENCE_DIAGRAM = Messages.getString("MESSAGE.TRANSLATING.SEQUENCE.DIAGRAM"); //$NON-NLS-1$

    /** 유스케이스 추적 매트릭스의 생성이 정상적으로 완료되었습니다. */
    public static final String MESSAGE_USECASE_TRACE_MATRIX_GENERATION_HAS_BEEN_COMPLETED_SUCCESSFULLY = Messages.getString("MESSAGE.USECASE.TRACE.MATRIX.GENERATION.HAS.BEEN.COMPLETED.SUCCESSFULLY"); //$NON-NLS-1$

    /** 유스케이스 추적 매트릭스의 생성이 정상적으로 완료되지 않았습니다. */
    public static final String MESSAGE_ERROR_USECASE_TRACE_MATRIX_GENERATION_FAILED = Messages.getString("MESSAGE.ERROR.USECASE.TRACE.MATRIX.GENERATION.FAILED"); //$NON-NLS-1$

    /** RSA모델을 UML모델로 변환하여 작업공간으로 가져옵니다. */
    public static final String MESSAGE_TRANSFORM_RSA_MODEL_INTO_UML_MODEL = Messages.getString("MESSAGE.TRANSFORM.RSA.MODEL.INTO.UML.MODEL"); //$NON-NLS-1$
    
    /** ~개의 Prefix 적용이 성공하였습니다. */
    public static final String MESSAGE_APPLY_KOREAN_PREFIX_SUCCESS = Messages.getString("MESSAGE.APPLY.KOREAN.PREFIX.SUCCESS");//$NON-NLS-1$    
    
    /** ${0}개의 오퍼레이션이 생성되었습니다. */
    public static final String MESSAGE_APPLY_CREATE_OPERATION = Messages.getString("MESSAGE.APPLY.CREATE.OPERATION");//$NON-NLS-1$
    
    /** Please check the preferences. */
    public static final String MESSAGE_CHECH_PREFERENCE = Messages.getString("MESSAGE.CHECH_PREFERENCE");//$NON-NLS-1$
    
    /** %s 과(와) %s의 연계 정보를 생성 하시겠습니까? */
//    public static final String MESSAGE_CREATE_RELATION = Messages.getString("MESSAGE.CREATE.RELATION");//$NON-NLS-1$

    /********************************** UML *******************************************/

    /** 클래스 */
    public static final String UML_CLASS = Messages.getString("UML.CLASS"); //$NON-NLS-1$

    /** 클래스다이어그램 */
    public static final String UML_CLASSDIAGRAM = Messages.getString("UML.CLASSDIAGRAM"); //$NON-NLS-1$

    /** 패키지 */
    public static final String UML_PACKAGE = Messages.getString("UML.PACKAGE"); //$NON-NLS-1$

    /** 노트 */
    public static final String UML_NOTE = Messages.getString("UML.NOTE"); //$NON-NLS-1$

    /** 텍스트 */
    public static final String UML_TEXT = Messages.getString("UML.TEXT"); //$NON-NLS-1$

    /** 유즈케이스 */
    public static final String UML_USECASE = Messages.getString("UML.USECASE"); //$NON-NLS-1$

    /** 인터페이스 */
    public static final String UML_INTERFACE = Messages.getString("UML.INTERFACE"); //$NON-NLS-1$

    /** 상태 */
    public static final String UML_STATE = Messages.getString("UML.STATE "); //$NON-NLS-1$

    /** 액션 */
    public static final String UML_ACTION = Messages.getString("UML.ACTION"); //$NON-NLS-1$

    /** PrimitiveType */
    public static final String UML_PRIMITIVETYPE = Messages.getString("UML.PRIMITIVETYPE"); //$NON-NLS-1$

    /** Realization */
    public static final String UML_REALIZATION = Messages.getString("UML.REALIZATION"); //$NON-NLS-1$

    /** Generalization */
    public static final String UML_GENERALIZATION = Messages.getString("UML.GENERALIZATION"); //$NON-NLS-1$

    /** CompositeAssociation */
    public static final String UML_COMPOSITEASSOCIATION = Messages.getString("UML.COMPOSITEASSOCIATION"); //$NON-NLS-1$

    /** SharedAssociation */
    public static final String UML_SHAREDASSOCIATION = Messages.getString("UML.SHAREDASSOCIATION"); //$NON-NLS-1$

    /** NoneAssociation */
    public static final String UML_NONEASSOCIATION = Messages.getString("UML.NONEASSOCIATION"); //$NON-NLS-1$

    /** Association */
    public static final String UML_ASSOCIATION = Messages.getString("UML.ASSOCIATION"); //$NON-NLS-1$

    /** Attachement */
    public static final String UML_ATTACHEMENT = Messages.getString("UML.ATTACHEMENT"); //$NON-NLS-1$

    /** Actor */
    public static final String UML_ACTOR = Messages.getString("UML.ACTOR"); //$NON-NLS-1$

    /** Extend */
    public static final String UML_EXTEND = Messages.getString("UML.EXTEND"); //$NON-NLS-1$

    /** Include */
    public static final String UML_INCLUDE = Messages.getString("UML.INCLUDE"); //$NON-NLS-1$

    /** Dependency */
    public static final String UML_DEPENDENCY = Messages.getString("UML.DEPENDENCY"); //$NON-NLS-1$

    /** Activity */
    public static final String UML_ACTIVITY = Messages.getString("UML.ACTIVITY"); //$NON-NLS-1$

    /** ActivityParameterNode */
    public static final String UML_ACTIVITYPARAMETERNODE = Messages.getString("UML.ACTIVITYPARAMETERNODE"); //$NON-NLS-1$

    /** ActivityPartition */
    public static final String UML_ACTIVITYPARTITION = Messages.getString("UML.ACTIVITYPARTITION"); //$NON-NLS-1$

    /** AcceptEventAction */
    public static final String UML_ACCEPTEVENTACTION = Messages.getString("UML.ACCEPTEVENTACTION"); //$NON-NLS-1$

    /** AddStructuralFeatureValueAction */
    public static final String UML_ADDSTRUCTURALFEATUREVALUEACTION = Messages.getString("UML.ADDSTRUCTURALFEATUREVALUEACTION"); //$NON-NLS-1$

    /** CallBehaviorAction */
    public static final String UML_CALLBEHAVIORACTION = Messages.getString("UML.CALLBEHAVIORACTION"); //$NON-NLS-1$

    /** CallOperationAction */
    public static final String UML_CALLOPERATIONACTION = Messages.getString("UML.CALLOPERATIONACTION"); //$NON-NLS-1$

    /** SendObjectAction */
    public static final String UML_SENDOBJECTACTION = Messages.getString("UML.SENDOBJECTACTION"); //$NON-NLS-1$

    /** CreateObjectAction */
    public static final String UML_CREATEOBJECTACTION = Messages.getString("UML.CREATEOBJECTACTION"); //$NON-NLS-1$

    /** DestroyObjectAction */
    public static final String UML_DESTROYOBJECTACTION = Messages.getString("UML.DESTROYOBJECTACTION"); //$NON-NLS-1$

    /** ReadExtentAction */
    public static final String UML_READEXTENTACTION = Messages.getString("UML.READEXTENTACTION"); //$NON-NLS-1$

    /** OpaqueAction */
    public static final String UML_OPAQUEACTION = Messages.getString("UML.OPAQUEACTION"); //$NON-NLS-1$

    /** SendSignalAction */
    public static final String UML_SENDSIGNALACTION = Messages.getString("UML.SENDSIGNALACTION"); //$NON-NLS-1$

    /** ActivityFinalNode */
    public static final String UML_ACTIVITYFINALNODE = Messages.getString("UML.ACTIVITYFINALNODE"); //$NON-NLS-1$

    /** Extension Point */
    public static final String UML_EXTENSIONPOINT = Messages.getString("UML.EXTENSIONPOINT"); //$NON-NLS-1$

    /** FlowFinalNode */
    public static final String UML_FLOWFINALNODE = Messages.getString("UML.FLOWFINALNODE"); //$NON-NLS-1$

    /** MergeNode */
    public static final String UML_MERGENODE = Messages.getString("UML.MERGENODE"); //$NON-NLS-1$

    /** DecisionNode */
    public static final String UML_DECISIONNODE = Messages.getString("UML.DECISIONNODE"); //$NON-NLS-1$

    /** ForkNode */
    public static final String UML_FORKNODE = Messages.getString("UML.FORKNODE"); //$NON-NLS-1$

    /** JoinNode */
    public static final String UML_JOINNODE = Messages.getString("UML.JOINNODE"); //$NON-NLS-1$

    /** ConditionalNode */
    public static final String UML_CONDITIONALNODE = Messages.getString("UML.CONDITIONALNODE"); //$NON-NLS-1$

    /** ExpansionRegion */
    public static final String UML_EXPANSIONREGION = Messages.getString("UML.EXPANSIONREGION"); //$NON-NLS-1$

    /** LoopNode */
    public static final String UML_LOOPNODE = Messages.getString("UML.LOOPNODE"); //$NON-NLS-1$

    /** StructuredActivityNode */
    public static final String UML_STRUCTUREDACTIVITYNODE = Messages.getString("UML.STRUCTUREDACTIVITYNODE"); //$NON-NLS-1$

    /** CentralBufferNode */
    public static final String UML_CENTRALBUFFERNODE = Messages.getString("UML.CENTRALBUFFERNODE"); //$NON-NLS-1$

    /** DataStoreNode */
    public static final String UML_DATASTORENODE = Messages.getString("UML.DATASTORENODE"); //$NON-NLS-1$

    /** Pin */
    public static final String UML_PIN = Messages.getString("UML.PIN"); //$NON-NLS-1$

    /** InputPin */
    public static final String UML_INPUTPIN = Messages.getString("UML.INPUTPIN"); //$NON-NLS-1$

    /** OutputPin */
    public static final String UML_OUTPUTPIN = Messages.getString("UML.OUTPUTPIN"); //$NON-NLS-1$

    /** ObjectFlow */
    public static final String UML_OBJECTFLOW = Messages.getString("UML.OBJECTFLOW"); //$NON-NLS-1$

    /** ExceptionHandler */
    public static final String UML_EXCEPTIONHANDLER = Messages.getString("UML.EXCEPTIONHANDLER"); //$NON-NLS-1$

    /** Component */
    public static final String UML_COMPONENT = Messages.getString("UML.COMPONENT"); //$NON-NLS-1$

    /** Artifact */
    public static final String UML_ARTIFACT = Messages.getString("UML.ARTIFACT"); //$NON-NLS-1$

    /** ElementImport */
    public static final String UML_ELEMENTIMPORT = Messages.getString("UML.ELEMENTIMPORT"); //$NON-NLS-1$

    /** Property */
    public static final String UML_PROPERTY = Messages.getString("UML.PROPERTY"); //$NON-NLS-1$

    /** Operation */
    public static final String UML_OPERATION = Messages.getString("UML.OPERATION"); //$NON-NLS-1$

    /** Port */
    public static final String UML_PORT = Messages.getString("UML.PORT"); //$NON-NLS-1$

    /** Enumeration */
    public static final String UML_ENUMERATION = Messages.getString("UML.ENUMERATION"); //$NON-NLS-1$

    /** DataType */
    public static final String UML_DATATYPE = Messages.getString("UML.DATATYPE"); //$NON-NLS-1$

    /** Constraint */
    public static final String UML_CONSTRAINT = Messages.getString("UML.CONSTRAINT"); //$NON-NLS-1$

    /** AssociationClass */
    public static final String UML_ASSOCIATIONCLASS = Messages.getString("UML.ASSOCIATIONCLASS"); //$NON-NLS-1$

    /** EnumerationLiteral */
    public static final String UML_ENUMERATIONLITERAL = Messages.getString("UML.ENUMERATIONLITERAL"); //$NON-NLS-1$

    /** TemplateSignature */
    public static final String UML_TEMPLATESIGNATURE = Messages.getString("UML.TEMPLATESIGNATURE"); //$NON-NLS-1$

    /** Abstraction */
    public static final String UML_ABSTRACTION = Messages.getString("UML.ABSTRACTION"); //$NON-NLS-1$

    /** Usage */
    public static final String UML_USAGE = Messages.getString("UML.USAGE"); //$NON-NLS-1$

    /** Substitution */
    public static final String UML_SUBSTITUTION = Messages.getString("UML.SUBSTITUTION"); //$NON-NLS-1$

    /** TemplateBinding */
    public static final String UML_TEMPLATEBINDING = Messages.getString("UML.TEMPLATEBINDING"); //$NON-NLS-1$

    /** InstanceSpecification */
    public static final String UML_INSTANCESPECIFICATION = Messages.getString("UML.INSTANCESPECIFICATION"); //$NON-NLS-1$

    /** Slot */
    public static final String UML_SLOT = Messages.getString("UML.SLOT"); //$NON-NLS-1$

    /** LiteralInteger */
    public static final String UML_LITERALINTEGER = Messages.getString("UML.LITERALINTEGER"); //$NON-NLS-1$

    /** LiteralString */
    public static final String UML_LITERALSTRING = Messages.getString("UML.LITERALSTRING"); //$NON-NLS-1$

    /** Expression */
    public static final String UML_EXPRESSION = Messages.getString("UML.EXPRESSION"); //$NON-NLS-1$

    /** Lifeline */
    public static final String UML_LIFELINE = Messages.getString("UML.LIFELINE"); //$NON-NLS-1$

    /** CombinedFragment */
    public static final String UML_COMBINEDFRAGMENT = Messages.getString("UML.COMBINEDFRAGMENT"); //$NON-NLS-1$

    /** InteractionUse */
    public static final String UML_INTERACTIONUSE = Messages.getString("UML.INTERACTIONUSE"); //$NON-NLS-1$

    /** Interaction */
    public static final String UML_INTERACTION = Messages.getString("UML.INTERACTION"); //$NON-NLS-1$

    /** CompleteMessage */
    public static final String UML_COMPLETEMESSAGE = Messages.getString("UML.COMPLETEMESSAGE"); //$NON-NLS-1$

    /** LostMessage */
    public static final String UML_LOSTMESSAGE = Messages.getString("UML.LOSTMESSAGE"); //$NON-NLS-1$

    /** FoundMessage */
    public static final String UML_FOUNDMESSAGE = Messages.getString("UML.FOUNDMESSAGE"); //$NON-NLS-1$

    /** ControlFlow */
    public static final String UML_CONTROLFLOW = Messages.getString("UML.CONTROLFLOW"); //$NON-NLS-1$

    /** Component Diagram */
    public static final String UML_COMPONENTDIAGRAM = Messages.getString("UML.COMPONENTDIAGRAM"); //$NON-NLS-1$

    /** ComponentRealization */
    public static final String UML_COMPONENTREALIZATION = Messages.getString("UML.COMPONENTREALIZATION"); //$NON-NLS-1$

    /** InterfaceRealization */
    public static final String UML_INTERFACEREALIZATION = Messages.getString("UML.INTERFACEREALIZATION"); //$NON-NLS-1$

    /** ProvidedInterface */
    public static final String UML_PROVIDEDINTERFACE = Messages.getString("UML.PROVIDEDINTERFACE"); //$NON-NLS-1$

    /** RequiredInterface */
    public static final String UML_REQUIREDINTERFACE = Messages.getString("UML.REQUIREDINTERFACE"); //$NON-NLS-1$

    /** Connector */
    public static final String UML_CONNECTOR = Messages.getString("UML.CONNECTOR"); //$NON-NLS-1$

    /** CollaborationUse */
    public static final String UML_COLLABORATIONUSE = Messages.getString("UML.COLLABORATIONUSE"); //$NON-NLS-1$

    /** Part */
    public static final String UML_PART = Messages.getString("UML.PART"); //$NON-NLS-1$

    /** InitialNode */
    public static final String UML_INITIALNODE = Messages.getString("UML.INITIALNODE"); //$NON-NLS-1$

    /** ValuePin */
    public static final String UML_VALUEPIN = Messages.getString("UML.INITIALNODE"); //$NON-NLS-1$

    /** Activity Diagram */
    public static final String UML_ACTIVITYDIAGRAM = Messages.getString("UML.ACTIVITYDIAGRAM"); //$NON-NLS-1$

    /** Usecase Diagram */
    public static final String UML_USECASEDIAGRAM = Messages.getString("UML.USECASEDIAGRAM"); //$NON-NLS-1$

    /** Sequence Diagram */
    public static final String UML_SEQUENCEDIAGRAM = Messages.getString("UML.SEQUENCEDIAGRAM"); //$NON-NLS-1$

    /** PackageImport */
    public static final String UML_PACKAGEIMPORT = Messages.getString("UML.PACKAGEIMPORT"); //$NON-NLS-1$

    /** PackageMerge */
    public static final String UML_PACKAGEMERGE = Messages.getString("UML.PACKAGEMERGE"); //$NON-NLS-1$

    /** Collaboration */
    public static final String UML_COLLABORATION = Messages.getString("UML.COLLABORATION"); //$NON-NLS-1$

    /** Signal */
    public static final String UML_SIGNAL = Messages.getString("UML.SIGNAL"); //$NON-NLS-1$

    /** DestructionEvent */
    public static final String UML_DESTRUCTIONEVENT = Messages.getString("UML.DESTRUCTIONEVENT"); //$NON-NLS-1$

    /** Asynch_Call */
    public static final String UML_ASYNCH_CALL = Messages.getString("UML.ASYNCH_CALL"); //$NON-NLS-1$

    /** Synch_Call */
    public static final String UML_SYNCH_CALL = Messages.getString("UML.SYNCH_CALL"); //$NON-NLS-1$

    /** Create_Message */
    public static final String UML_CREATE_MESSAGE = Messages.getString("UML.CREATE_MESSAGE"); //$NON-NLS-1$

    /** Delete_Message */
    public static final String UML_DELETE_MESSAGE = Messages.getString("UML.DELETE_MESSAGE"); //$NON-NLS-1$

    /** Reply */
    public static final String UML_REPLY = Messages.getString("UML.REPLY"); //$NON-NLS-1$

    /** INTERACTION_ALT */
    public static final String UML_INTERACTION_ALT = Messages.getString("UML.INTERACTION_ALT"); //$NON-NLS-1$

    /** INTERACTION_ASSERT */
    public static final String UML_INTERACTION_ASSERT = Messages.getString("UML.INTERACTION_ASSERT"); //$NON-NLS-1$

    /** INTERACTION_BREAK */
    public static final String UML_INTERACTION_BREAK = Messages.getString("UML.INTERACTION_BREAK"); //$NON-NLS-1$

    /** INTERACTION_CONSIDER */
    public static final String UML_INTERACTION_CONSIDER = Messages.getString("UML.INTERACTION_CONSIDER"); //$NON-NLS-1$

    /** INTERACTION_CRITICAL */
    public static final String UML_INTERACTION_CRITICAL = Messages.getString("UML.INTERACTION_CRITICAL"); //$NON-NLS-1$

    /** INTERACTION_IGNORE */
    public static final String UML_INTERACTION_IGNORE = Messages.getString("UML.INTERACTION_IGNORE"); //$NON-NLS-1$

    /** INTERACTION_LOOP */
    public static final String UML_INTERACTION_LOOP = Messages.getString("UML.INTERACTION_LOOP"); //$NON-NLS-1$

    /** INTERACTION_NEG */
    public static final String UML_INTERACTION_NEG = Messages.getString("UML.INTERACTION_NEG"); //$NON-NLS-1$

    /** INTERACTION_OPTION */
    public static final String UML_INTERACTION_OPT = Messages.getString("UML.INTERACTION_OPT"); //$NON-NLS-1$

    /** INTERACTION_PARALLEL */
    public static final String UML_INTERACTION_PAR = Messages.getString("UML.INTERACTION_PAR"); //$NON-NLS-1$

    /** INTERACTION_SEQ */
    public static final String UML_INTERACTION_SEQ = Messages.getString("UML.INTERACTION_SEQ"); //$NON-NLS-1$

    /** INTERACTION_STRICT */
    public static final String UML_INTERACTION_STRICT = Messages.getString("UML.INTERACTION_STRICT"); //$NON-NLS-1$

    /** Option(if) */
    public static final String UML_OPTION_IF = Messages.getString("UML.OPTION.IF"); //$NON-NLS-1$

    /** Alternative(if else) */
    public static final String UML_ALTERNATIVE_IFELSE = Messages.getString("UML.ALTERNATIVE.IFELSE"); //$NON-NLS-1$

    /** Alternative(switch) */
    public static final String UML_ALTERNATIVE_SWITCH = Messages.getString("UML.ALTERNATIVE.SWITCH"); //$NON-NLS-1$

    /** Loop(while) */
    public static final String UML_LOOP_WHILE = Messages.getString("UML.LOOP.WHILE"); //$NON-NLS-1$

    /** Loop(for) */
    public static final String UML_LOOP_FOR = Messages.getString("UML.LOOP.FOR"); //$NON-NLS-1$

    /** Binary_Association */
    public static final String UML_BINARY_ASSOCIATION = Messages.getString("UML.BINARY_ASSOCIATION"); //$NON-NLS-1$

    /** Directed_Association */
    public static final String UML_DIRECTED_ASSOCIATION = Messages.getString("UML.DIRECTED_ASSOCIATION"); //$NON-NLS-1$

    /** Shared_Binary_Association */
    public static final String UML_SHARED_BINARY_ASSOCIATION = Messages.getString("UML.SHARED_BINARY_ASSOCIATION"); //$NON-NLS-1$

    /** Shared_Directed_Association */
    public static final String UML_SHARED_DIRECTED_ASSOCIATION = Messages.getString("UML.SHARED_DIRECTED_ASSOCIATION"); //$NON-NLS-1$

    /** Composite_Binary_Association */
    public static final String UML_COMPOSITE_BINARY_ASSOCIATION = Messages.getString("UML.COMPOSITE_BINARY_ASSOCIATION"); //$NON-NLS-1$

    /** Composite_Directed_Association */
    public static final String UML_COMPOSITE_DIRECTED_ASSOCIATION = Messages.getString("UML.COMPOSITE_DIRECTED_ASSOCIATION"); //$NON-NLS-1$

    /** State Invariant */
    public static final String UML_STATEINVARIANT = Messages.getString("UML.STATEINVARIANT");//$NON-NLS-1$

    /** State Send operation Event */
    public static final String UML_SENDOPERATIONEVENT = Messages.getString("UML.SENDOPERATIONEVENT");//$NON-NLS-1$

    /** State Receive operation Event */
    public static final String UML_RECEIVEOPERATIONEVENT = Messages.getString("UML.RECEIVEOPERATIONEVENT");//$NON-NLS-1$

    /** State Execution Event */
    public static final String UML_EXECUTIONEVENT = Messages.getString("UML.EXECUTIONEVENT");//$NON-NLS-1$

    /** Transition */
    public static final String UML_TRANSITION = Messages.getString("UML.TRANSITION");//$NON-NLS-1$

    /** FinalState */
    public static final String UML_FINALSTATE = Messages.getString("UML.FINALSTATE"); //$NON-NLS-1$

    /** Message */
    public static final String UML_MESSAGE = Messages.getString("UML.MESSAGE");//$NON-NLS-1$

    /** Message Undefined Name */
    public static final String UML_MESSAGE_UNDEFINEDNAME = Messages.getString("UML.MESSAGE.UNDEFINEDNAME");//$NON-NLS-1$

    /** UML Model Message */
    public static final String UML_MODEL = Messages.getString("UML.MODEL");//$NON-NLS-1$

    /** UML_ADDVARIABLEVALUEACTION */
    public static final String UML_ADDVARIABLEVALUEACTION = Messages.getString("UML.ADDVARIABLEVALUEACTION"); //$NON-NLS-1$

    /** UML_CLEARVARIABLEACTION */
    public static final String UML_CLEARVARIABLEACTION = Messages.getString("UML.CLEARVARIABLEACTION"); //$NON-NLS-1$

    /** UML_READVARIABLE */
    public static final String UML_READVARIABLE = Messages.getString("UML.READVARIABLE"); //$NON-NLS-1$

    /** UML_REMOVEVARIABLEACTION */
    public static final String UML_REMOVEVARIABLEACTION = Messages.getString("UML.REMOVEVARIABLEACTION"); //$NON-NLS-1$

    /** UML_CLEARSTRUCTURALFEATUREACTION */
    public static final String UML_CLEARSTRUCTURALFEATUREACTION = Messages.getString("UML.CLEARSTRUCTURALFEATUREACTION"); //$NON-NLS-1$

    /** UML_READSTRUCTURALFEATUREACTION */
    public static final String UML_READSTRUCTURALFEATUREACTION = Messages.getString("UML.READSTRUCTURALFEATUREACTION"); //$NON-NLS-1$

    /** UML_REMOVESTRUCTURALFEATUREACTION */
    public static final String UML_REMOVESTRUCTURALFEATUREACTION = Messages.getString("UML.REMOVESTRUCTURALFEATUREACTION"); //$NON-NLS-1$

    /** UML_READCLASSIFIEDOBJECTACTION */
    public static final String UML_READCLASSIFIEDOBJECTACTION = Messages.getString("UML.READCLASSIFIEDOBJECTACTION"); //$NON-NLS-1$

    /** UML_READSELFACTION */
    public static final String UML_READSELFACTION = Messages.getString("UML.READSELFACTION"); //$NON-NLS-1$

    /** UML_RECLASSIFYOBJECTACTION */
    public static final String UML_RECLASSIFYOBJECTACTION = Messages.getString("UML.RECLASSIFYOBJECTACTION"); //$NON-NLS-1$

    /** UML_STARTCLASSIFIERBEHAVIORACTION */
    public static final String UML_STARTCLASSIFIERBEHAVIORACTION = Messages.getString("UML.STARTCLASSIFIERBEHAVIORACTION"); //$NON-NLS-1$

    /** UML_TESTIDENTITYACTION */
    public static final String UML_TESTIDENTITYACTION = Messages.getString("UML.TESTIDENTITYACTION"); //$NON-NLS-1$

    /** UML_CLEARASSOCIATIONACTION */
    public static final String UML_CLEARASSOCIATIONACTION = Messages.getString("UML.CLEARASSOCIATIONACTION"); //$NON-NLS-1$

    /** UML_CREATELINKACTION */
    public static final String UML_CREATELINKACTION = Messages.getString("UML.CREATELINKACTION"); //$NON-NLS-1$

    /** UML_CREATELINKOBJECTACTION */
    public static final String UML_CREATELINKOBJECTACTION = Messages.getString("UML.CREATELINKOBJECTACTION"); //$NON-NLS-1$

    /** UML_DESTROYLINKACTION */
    public static final String UML_DESTROYLINKACTION = Messages.getString("UML.DESTROYLINKACTION"); //$NON-NLS-1$

    /** UML_READLINKACTION */
    public static final String UML_READLINKACTION = Messages.getString("UML.READLINKACTION"); //$NON-NLS-1$

    /** UML_READLINKOBJECTENDACTION */
    public static final String UML_READLINKOBJECTENDACTION = Messages.getString("UML.READLINKOBJECTENDACTION"); //$NON-NLS-1$

    /** UML_BROADCASTSIGNALACTION */
    public static final String UML_BROADCASTSIGNALACTION = Messages.getString("UML.BROADCASTSIGNALACTION"); //$NON-NLS-1$

    /** UML_RECEIVESIGNALEVENT */
    public static final String UML_RECEIVESIGNALEVENT = Messages.getString("UML.RECEIVESIGNALEVENT"); //$NON-NLS-1$

    /** UML_ACCEPTCALLACTION */
    public static final String UML_ACCEPTCALLACTION = Messages.getString("UML.ACCEPTCALLACTION"); //$NON-NLS-1$

    /** UML_EVENT */
    public static final String UML_EVENTACTION = Messages.getString("UML.EVENTACTION"); //$NON-NLS-1$

    /** UML_REPLYACTION */
    public static final String UML_REPLYACTION = Messages.getString("UML.REPLYACTION"); //$NON-NLS-1$

    /** UML_READLINKOBJECTENDQUALIFIERACTION */
    public static final String UML_READLINKOBJECTENDQUALIFIERACTION = Messages.getString("UML.READLINKOBJECTENDQUALIFIERACTION"); //$NON-NLS-1$

    /** Reception */
    public static final String UML_RECEPTION = Messages.getString("UML.RECEPTION"); //$NON-NLS-1$

    /** Role */
    public static final String UML_ROLE = Messages.getString("UML.ROLE"); //$NON-NLS-1$

    /** UML Notation */
    public static final String UML_NOTATION = Messages.getString("UML.NOTATION"); //$NON-NLS-1$

    /** 주석 */
    public static final String UML_ANNOTATION = Messages.getString("UML.ANNOTATION"); //$NON-NLS-1$

    /** UML_ANNOTATION */
    public static final String UML_MODELER = Messages.getString("UML_MODELER"); //$NON-NLS-1$

    // 아이콘
    /** /icons/Import.gif */
    public static final String _UI_Import_icon = Messages.getString("UI.Import.icon"); //$NON-NLS-1$

    /** /icons/add.gif */
    public static final String _UI_Add_icon = Messages.getString("UI.Add.icon"); //$NON-NLS-1$

    /** /icons/Export.gif */
    public static final String _UI_Export_icon = Messages.getString("UI.Export.icon"); //$NON-NLS-1$

    /** /icons/remove.gif */
    public static final String _UI_Delete_icon = Messages.getString("UI.Delete.icon"); //$NON-NLS-1$

    /** LABEL_TRANSFORMATION_FILE */
    public static final String LABEL_TRANSFORMATION_FILE = Messages.getString("LABEL.TRANSFORMATION.FILE"); //$NON-NLS-1$

    /** LABEL_CREATE_TRANSFORMATION_FILE */
    public static final String LABEL_CREATE_TRANSFORMATION_FILE = Messages.getString("LABEL.CREATE.TRANSFORMATION.FILE"); //$NON-NLS-1$

    /** MESSAGE_LOGIN_ERROR */
    public static final String MESSAGE_LOGIN_ERROR = Messages.getString("MESSAGE.LOGIN.ERROR"); //$NON-NLS-1$;

    /** LABEL_NEXCORE_UML_MODELER */
    public static final String LABEL_NEXCORE_UML_MODELER = Messages.getString("LABEL.NEXCORE.UML.MODELER"); //$NON-NLS-1$;

    /** LABEL_DUPLICATE_ERROR */
    public static final String LABEL_DUPLICATE_ERROR = Messages.getString("LABEL.DUPLICATE_ERROR"); //$NON-NLS-1$;

    /** MESSAGE_DUPLICATE_ERROR */
    public static final String MESSAGE_DUPLICATE_ERROR = Messages.getString("MESSAGE.DUPLICATE_ERROR"); //$NON-NLS-1$;

    /** MESSAGE_SELECT_PROFILE_DESCRIPTION */
    public static final String MESSAGE_SELECT_PROFILE_DESCRIPTION = Messages.getString("MESSAGE.SELECT.PROFILE.DESCRIPTION"); //$NON-NLS-1$;

    /** MESSAGE_UPDATE_MODEL_VERSION_MESSAGE */
    public static final String MESSAGE_UPDATE_MODEL_VERSION_MESSAGE = Messages.getString("MESSAGE.UPDATE.MODEL.VERSION.MESSAGE"); //$NON-NLS-1$;

    /** MESSAGE_MODEL_VERSION_MISSMATCH */
    public static final String MESSAGE_MODEL_VERSION_MISSMATCH = Messages.getString("MESSAGE.MODEL.VERSION.MISSMATCH"); //$NON-NLS-1$;

    /** LABEL_PROVIDED_INTERFACE */
    public static final String LABEL_PROVIDED_INTERFACE = Messages.getString("LABEL.PROVIDED.INTERFACE"); //$NON-NLS-1$;

    /** LABEL_REQUIRED_INTERFACE */
    public static final String LABEL_REQUIRED_INTERFACE = Messages.getString("LABEL.REQUIRED.INTERFACE"); //$NON-NLS-1$;

    /** LABEL_PREFERENCE_DIAGRAM_COMPONENTDIAGRAM_SHOWOPERATION */
    public static final String LABEL_PREFERENCE_DIAGRAM_COMPONENTDIAGRAM_SHOWOPERATION = Messages.getString("LABEL.PREFERENCE.DIAGRAM.COMPONENTDIAGRAM.SHOWOPERATION"); //$NON-NLS-1$;

    /** LABEL_ATTRIBUTES */
    public static final String LABEL_ATTRIBUTES = Messages.getString("LABEL.ATTRIBUTES"); //$NON-NLS-1$;

    /** LABEL_OPERATIONS */
    public static final String LABEL_OPERATIONS = Messages.getString("LABEL.OPERATIONS"); //$NON-NLS-1$;

    /** LABEL_PREFERENCE_DIAGRAM_COMPONENTDIAGRAM_SHOWINNERELEMENT */
    public static final String LABEL_PREFERENCE_DIAGRAM_COMPONENTDIAGRAM_SHOWINNERELEMENT = Messages.getString("LABEL.PREFERENCE.DIAGRAM.COMPONENTDIAGRAM.SHOWINNERELEMENT"); //$NON-NLS-1$;

    /** @부가사항 */
    public static final String LABEL_ADDITIONAL_INFO = Messages.getString("LABEL.ADDITIONAL.INFO"); //$NON-NLS-1$;

    /** 알고리즘 */
    public static final String LABEL_KEYWORD_ALGORITHM = Messages.getString("LABEL.KEYWORD.ALGORITHM"); //$NON-NLS-1$;

    /** 사전조건 */
    public static final String LABEL_KEYWORD_PRECONDITION = Messages.getString("LABEL.KEYWORD.PRECONDITION"); //$NON-NLS-1$;

    /** 사후조건 */
    public static final String LABEL_KEYWORD_POSTCONDITION = Messages.getString("LABEL.KEYWORD.POSTCONDITION"); //$NON-NLS-1$;

    /** 폴더를 선택해야 합니다. */
    public static final String MESSAGE_WRONG_SELECTION = Messages.getString("MESSAGE.WRONG.SELECTION"); //$NON-NLS-1$;

    /** 존재하지 않는 폴더입니다. */
    public static final String MESSAGE_NOT_EXISTING_FOLDER = Messages.getString("MESSAGE.NOT.EXISTING.FOLDER"); //$NON-NLS-1$;

    /** 숨김 폴더를 선택하셨습니다. UML 모델러에서 사용할 수 있는 다른 폴더를 선택해주세요. */
    public static final String MESSAGE_HIDDEN_FOLDER = Messages.getString("MESSAGE.HIDDEN.FOLDER"); //$NON-NLS-1$;

    /** .settings */
    public static final Object LABEL_SETTINGFOLDER = Messages.getString("LABEL.SETTINGFOLDER"); //$NON-NLS-1$;

    /** 한글명만 용어 생성 */
    public static final String LABEL_GLOSSARY_ONLY_KOREAN = Messages.getString("LABEL.GLOSSARY.ONLY.KOREAN"); //$NON-NLS-1$;

    /** 용어사전 생성시 포함시킬 요소 */
    public static final String LABEL_GLOSSARY_INCLUDED_COMPONENTS = Messages.getString("LABEL.GLOSSARY.INCLUDED.COMPONENTS"); //$NON-NLS-1$;   
    
    /** MESSAGE_UML_PREFERENCE_DESCRIPTION */
    public static final String MESSAGE_UML_PREFERENCE_DESCRIPTION = Messages.getString("MESSAGE.UML.PREFERENCE.DESCRIPTION"); //$NON-NLS-1$;

    /** MESSAGE_DIAGRAM_PREFERENCE_DESCRIPTION */
    public static final String MESSAGE_DIAGRAM_PREFERENCE_DESCRIPTION = Messages.getString("MESSAGE.DIAGRAM.PREFERENCE.DESCRIPTION"); //$NON-NLS-1$;

    /** MESSAGE_CLASSDIAGRAM_PREFERENCE_DESCRIPTION */
    public static final String MESSAGE_CLASSDIAGRAM_PREFERENCE_DESCRIPTION = Messages.getString("MESSAGE.CLASSDIAGRAM.PREFERENCE.DESCRIPTION"); //$NON-NLS-1$;

    /** MESSAGE_UML_PREFERENCE_DESCRIPTION */
    public static final String MESSAGE_UML_MODEL_REFRESH = Messages.getString("MESSAGE.UML.MODEL.REFRESH"); //$NON-NLS-1$;

    /** MESSAGE_SELECT_LIBRARY_DESCRIPTION */
    public static final String MESSAGE_SELECT_LIBRARY_DESCRIPTION = Messages.getString("MESSAGE.SELECT.LIBRARY.DESCRIPTION"); //$NON-NLS-1$;

    /**
     * MESSAGE_MODEL_VERSION_MATCH
     */
    public static final String MESSAGE_MODEL_VERSION_MATCH = Messages.getString("MESSAGE.MODEL.VERSION.MATCH"); //$NON-NLS-1$;

    /**
     * MESSAGE_MODEL_VERSION_MATCH_MESSAGE
     */
    public static final String MESSAGE_MODEL_VERSION_MATCH_MESSAGE = Messages.getString("MESSAGE.MODEL.VERSION.MATCH.MESSAGE"); //$NON-NLS-1$;

    /**
     * LABEL_LITERAL_SPEC
     */
    public static final String LABEL_LITERAL_SPEC = Messages.getString("LABEL.LITERAL.SPEC"); //$NON-NLS-1$;

    /**
     * LABEL_APPLICABLE_ELEMENT_TYPE
     */
    public static final String LABEL_APPLICABLE_ELEMENT_TYPE = Messages.getString("LABEL.APPLICABLE.ELEMENT.TYPE"); //$NON-NLS-1$;

    /**
     * LABEL_MANAGEMENT_STEREOTYPE
     */
    public static final String LABEL_MANAGEMENT_STEREOTYPE = Messages.getString("LABEL.MANAGEMENT.STEREOTYPE"); //$NON-NLS-1$;

    /**
     * MESSAGE_APPLICABLE_STEREOTYPE_IS_EMPTY
     */
    public static final String MESSAGE_APPLICABLE_STEREOTYPE_IS_EMPTY = Messages.getString("MESSAGE.APPLICABLE.STEREOTYPE.IS.EMPTY"); //$NON-NLS-1$;

    /**
     * MESSAGE_SELECT_PARTITION_DIRECTION_WARNING
     */
    public static final String MESSAGE_SELECT_PARTITION_DIRECTION_WARNING = Messages.getString("MESSAGE.SELECT.PARTITION.DIRECTION.WARNING"); //$NON-NLS-1$;

    /**
     * MESSAGE_CANNOT_BE_EMPTY_NAME
     */
    public static final String MESSAGE_CANNOT_BE_EMPTY_NAME = Messages.getString("MESSAGE.CANNOT.BE.EMPTY.NAME"); //$NON-NLS-1$;
    
    /** projectId */
    public static final String LABEL_PROJECTID = Messages.getString("LABEL.PROJECTID"); //$NON-NLS-1$;
    
    /** projectCode */
    public static final String LABEL_PROJECTCODE = Messages.getString("LABEL.PROJECTCODE"); //$NON-NLS-1$;

    /** projectName */
    public static final String LABEL_PROJECTNAME = Messages.getString("LABEL.PROJECTNAME"); //$NON-NLS-1$;

    /** createDate */
    public static final String LABEL_PROJECTCREATEDATE = Messages.getString("LABEL.PROJECTCREATEDATE"); //$NON-NLS-1$;

    /** modifyDate */
    public static final String LABEL_PROJECTMODIFYDATE = Messages.getString("LABEL.PROJECTMODIFYDATE"); //$NON-NLS-1$;

    /** manager */
    public static final String LABEL_PROJECTMANAGER = Messages.getString("LABEL.PROJECTMANAGER"); //$NON-NLS-1$;

    /** description */
    public static final String LABEL_PROJECTDESCIPTION = Messages.getString("LABEL.PROJECTDESCIPTION"); //$NON-NLS-1$;
    
    /** NCP-Server Project List */
    public static final String LABEL_PROJECTLIST = Messages.getString("LABEL.PROJECTLIST"); //$NON-NLS-1$;

    /** project code : project name */
    public static final String LABEL_CODENAME = Messages.getString("LABEL.CODENAME"); //$NON-NLS-1$;

    /** Project code: */
    public static final String LABEL_PROJECT_CODE_LABEL = Messages.getString("LABEL.PROJECT.CODE.LABEL"); //$NON-NLS-1$;

    /** 찾기 */
    public static final String LABEL_FIND_PROJECT = Messages.getString("LABEL.FIND_.ROJECT"); //$NON-NLS-1$;

    /** Project description:  */
    public static final String LABEL_PROJECT_DESCRIPTION = Messages.getString("LABEL.PROJECT.DESCRIPTION"); //$NON-NLS-1$;

    /** 참조 주소값이 올바르지 않습니다.(%20)*/
    public static final String MESSAGE_ERROR_REF_ADDR_BLANK = Messages.getString("MESSAGE_ERROR_REF_ADDR_BLANK"); //$NON-NLS-1$;
    
    /** 파일이 존재하지 않습니다.*/
    public static final String MESSAGE_ERROR_FILE_NOT_EXIST = Messages.getString("MESSAGE_ERROR_FILE_NOT_EXIST"); //$NON-NLS-1$;
    
    /** 참조 주소값이 올바르지 않습니다.(platform:/resource) */
    public static final String MESSAGE_ERROR_REF_ADDR_PLATFORM_RES = Messages.getString("MESSAGE_ERROR_REF_ADDR_PLATFORM_RES"); //$NON-NLS-1$;
    
    /** 프로젝트 이름을 입력하세요. */
    public static final String MESSAGE_PROJECT_NAME_EMPTY = Messages.getString("MESSAGE.PROJECT.NAME.EMPTY"); //$NON-NLS-1$;

    /** 같은 프로젝트 이름이 이미 존재합니다. */
    public static final String MESSAGE_PROJECT_NAME_EXIST = Messages.getString("MESSAGE.PROJECT.NAME.EXIST"); //$NON-NLS-1$;
    
    /** 복사할 오퍼레이션이 포함된 클래스의 Prefix */
    public static final String LABEL_TARGET_PREFIX_FOR_COPING_OPERATION = Messages.getString("LABEL.TARGET.PREFIX.FOR.COPING.OPERATION"); //$NON-NLS-1$;

    /** Operation 자동 생성 */
    public static final String LABEL_OPERATION_AUTO_GENERATE = Messages.getString("LABEL.OPERATION.AUTO.GENERATE"); //$NON-NLS-1$;

    /** 선택한 엘리먼트의 하위에 컴포넌트가 존재하지 않습니다. */
    public static final String MESSAGE_SELECTED_ELEMENT_HAS_NO_COMPONENT = Messages.getString("MESSAGE.SELECTED.ELEMENT.HAS.NO.COMPONENT"); //$NON-NLS-1$;

    /** bizUnit의 오퍼레이션을 컴포넌트로 복사합니다. */
    public static final String MESSAGE_COPY_BIZUNIT_OPERATION_TO_COMPONENT = Messages.getString("MESSAGE.COPY.BIZUNIT.OPERATION.TO.COMPONENT"); //$NON-NLS-1$;

    /** NCP 메타컨텐츠 관리 */
    public static final String LABEL_NCP_METACONTENTS_MANAGEMENT = Messages.getString("LABEL.NCP.METACONTENTS.MANAGEMENT"); //$NON-NLS-1$;

    /** 추가 / 갱신  */
    public static final String LABEL_ADD_AND_UPDATE = Messages.getString("LABEL.ADD.AND.UPDATE"); //$NON-NLS-1$;

    /** NCP 메타컨텐츠 */
    public static final String LABEL_NCP_METACONTENTS = Messages.getString("LABEL.NCP.METACONTENTS"); //$NON-NLS-1$;

    /** 메타컨테츠 전체 동기화 */
    public static final String LABEL_NCP_METACONTENTS_SYNCHRONIZE_ALL = Messages.getString("LABEL.NCP.METACONTENTS.SYNCHRONIZE.ALL"); //$NON-NLS-1$;

    /** 로컬 컨텐츠 */
    public static final String LABEL_LOCAL_CONTENS = Messages.getString("LABEL.LOCAL.CONTENS"); //$NON-NLS-1$;

    /** 맵핑정보생성 */
    public static final String LABEL_CREATE_MAPPING_INFO = Messages.getString("LABEL.CREATE.MAPPING.INFO"); //$NON-NLS-1$;

    /** 맵핑정보삭제 */
    public static final String LABEL_DELETE_MAPPING_INFO = Messages.getString("LABEL.DELETE.MAPPING.INFO"); //$NON-NLS-1$;

    /** 상세정보조회 */
    public static final String LABEL_SEARCH_DETAIL_INFO = Messages.getString("LABEL.SEARCH.DETAIL.INFO"); //$NON-NLS-1$;
    /** 패키지 */
    public static final String LABEL_PACKAGE_QUALIFIEDNAME  = Messages.getString("MESSAGE.PACKAGE_QUALIFIEDNAME"); //$NON-NLS-1$;
    
    /** 에러 */
    public static final String TITLE_ERROR  = Messages.getString("TITLE_ERROR"); //$NON-NLS-1$;
    
    /** 단편화 중 에러가 발생했습니다. */
    public static final String MESSAGE_FILE_FRAGMENT_ERROR_MESSAGE  = Messages.getString("MESSAGE.FILE_FRAGMENT_ERROR_MESSAGE"); //$NON-NLS-1$;
    
    /** 병합 중 에러가 발생했습니다. */
    public static final String MESSAGE_FILE_DEFRAGMENT_ERROR_MESSAGE  = Messages.getString("MESSAGE.FILE_DEFRAGMENT_ERROR_MESSAGE"); //$NON-NLS-1$;
    
    /** 리소스 저장 */
    public static final String TITLE_SAVE  = Messages.getString("TITLE_SAVE"); //$NON-NLS-1$;
    
    /** 새로고침 */
    public static final String TITLE_REFRESH  = Messages.getString("TITLE_REFRESH"); //$NON-NLS-1$;
    
    /** 파일삭제 */
    public static final String TITLE_DELETE_FILE  = Messages.getString("TITLE_DELETE_FILE"); //$NON-NLS-1$;
    
    /** 패키지 간 상하 관계가 포함된 경우 일괄 단편화/병합을 실행할 수 없습니다. */
    public static final String MESSAGE_FRAGMENT_DEFRAGMENT_ERROR_MESSAGE  = Messages.getString("MESSAGE.FRAGMENT_DEFRAGMENT_ERROR_MESSAGE"); //$NON-NLS-1$;
    
    /** 참조된 프로젝트를 오픈합니다. */
    public static final String MESSAGE_REFERENCED_PROJECTS  = Messages.getString("MESSAGE.REFERENCED_PROJECTS"); //$NON-NLS-1$;

    /**
     * LABEL_APPLY_PROFILE
     */
    public static final String LABEL_APPLY_PROFILE = Messages.getString("LABEL.APPLY.PROFILE"); //$NON-NLS-1$;

    /**
     * LABEL_SEARCH_SCOPE
     */
    public static final String LABEL_SEARCH_SCOPE = Messages.getString("LABEL.SEARCH.SCOPE"); //$NON-NLS-1$;

    /**
     * LABEL_WORKSPACE
     */
    public static final String LABEL_WORKSPACE = Messages.getString("LABEL.WORKSPACE"); //$NON-NLS-1$;

    /**
     * LABEL_PROJECT
     */
    public static final String LABEL_PROJECT = Messages.getString("LABEL.PROJECT"); //$NON-NLS-1$;

    /**
     * 문서산출물 생성완료
     */
    public static final String MESSAGE_COMPLETE_DOCUMENT_CREATION = Messages.getString("MESSAGE.COMPLETE.DOCUMENT.CREATION"); //$NON-NLS-1$;

    /**
     * 문서 산출물 생성이 실패하였습니다.
     */
    public static final String MESSAGE_FAIL_DOCUMENT_CREATION = Messages.getString("MESSAGE.FAIL.DOCUMENT.CREATION"); //$NON-NLS-1$;

    /**
     * MESSAGE_DOCUMENT_HAS_BEEN_CREATED
     */
    public static final String MESSAGE_DOCUMENT_HAS_BEEN_CREATED = Messages.getString("MESSAGE.DOCUMENT.HAS.BEEN.CREATED"); //$NON-NLS-1$;

    /**
     * LABEL_SEARCH_METACONTEINTS_DETAIL_INFO
     */
    public static final String LABEL_SEARCH_METACONTEINTS_DETAIL_INFO = Messages.getString("LABEL.SEARCH.METACONTEINTS.DETAIL.INFO"); //$NON-NLS-1$;

    /**
     * LABEL_REQUIRMENT
     */
    public static final String LABEL_REQUIRMENT = Messages.getString("LABEL.REQUIRMENT"); //$NON-NLS-1$;

    /**
     * LABEL_DETAIL_INFO
     */
    public static final String LABEL_DETAIL_INFO = Messages.getString("LABEL.DETAIL.INFO"); //$NON-NLS-1$;

    
    /** 미사용 요소 제거 완료 */
    public static final String LABEL_DELETE_UNUSED_ELEMENT_COMPLETED = Messages.getString("LABEL.DELETE.UNUSED.ELEMENT.COMPLETED"); //$NON-NLS-1$;
    
    /** ${0}개의 미사용 요소들이 제거되었습니다 */
    public static final String MESSAGE_DELETE_UNUSED_ELEMENT_COMPLETED = Messages.getString("MESSAGE.DELETE.UNUSED.ELEMENT.COMPLETED"); //$NON-NLS-1$;
    
    /**
     * LABEL_EXPAND_ALL
     */
    public static final String LABEL_EXPAND_ALL = Messages.getString("LABEL.EXPAND.ALL"); //$NON-NLS-1$;

    /**
     * LABEL_CALLAPSE_ALL
     */
    public static final String LABEL_CALLAPSE_ALL = Messages.getString("LABEL.CALLAPSE.ALL"); //$NON-NLS-1$;

    /**
     * LABEL_CHECK_ALL
     */
    public static final String LABEL_CHECK_ALL = Messages.getString("LABEL.CHECK.ALL"); //$NON-NLS-1$;

    /**
     * LABEL_UNCHECK_ALL
     */
    public static final String LABEL_UNCHECK_ALL = Messages.getString("LABEL.UNCHECK.ALL"); //$NON-NLS-1$;

    /**
     * LABEL_SEARCH
     */
    public static final String LABEL_SEARCH = Messages.getString("LABEL.SEARCH"); //$NON-NLS-1$;

    /**
     * LABEL_DELETE_METACONTENT
     */
    public static final String LABEL_DELETE_METACONTENT = Messages.getString("LABEL.DELETE.METACONTENT"); //$NON-NLS-1$;

    /**
     * MESSAGE_SELECT_TREE_LOCATION
     */
    public static final String MESSAGE_SELECT_TREE_LOCATION = Messages.getString("MESSAGE.SELECT.TREE.LOCATION"); //$NON-NLS-1$;

    /**
     * MESSAGE_SERVICE_ERROR
     */
    public static final String MESSAGE_SERVICE_ERROR = Messages.getString("MESSAGE.SERVICE_NOT_EXIST_ERROR"); //$NON-NLS-1$;

    /**
     * MESSAGE_NO_NAME_ELEMENT_WARNING
     */
    public static final String MESSAGE_NO_NAME_ELEMENT_WARNING = Messages.getString("MESSAGE.NO_NAME_ELEMENT_WARNING"); //$NON-NLS-1$;

    /**
     * MESSAGE_IS_NOT_METACONTENTS
     */
    public static final String MESSAGE_IS_NOT_METACONTENTS = Messages.getString("MESSAGE_IS_NOT_METACONTENTS"); //$NON-NLS-1$;

    /**
     * MESSAGE_HAS_NO_NCP_PROJECT
     */
    public static final String MESSAGE_HAS_NO_NCP_PROJECT = Messages.getString("MESSAGE_HAS_NO_NCP_PROJECT"); //$NON-NLS-1$;

    /**
     * MESSAGE_COULD_NOT_CREATE_RELATION_INFOMATION
     */
    public static final String MESSAGE_COULD_NOT_CREATE_RELATION_INFOMATION = Messages.getString("MESSAGE_COULD_NOT_CREATE_RELATION_INFOMATION"); //$NON-NLS-1$;

    
    
    
    /**
     * LABEL_FRAGMENTED_PACKAGE_LIST
     */
    public static final String LABEL_FRAGMENTED_PACKAGE_LIST = Messages.getString("LABEL_FRAGMENTED_PACKAGE_LIST"); //$NON-NLS-1$;

    /**
     * MESSAGE_FRAGMENTED_PACKAGE_LIST
     */
    public static final String MESSAGE_FRAGMENTED_PACKAGE_LIST = Messages.getString("MESSAGE_FRAGMENTED_PACKAGE_LIST"); //$NON-NLS-1$;

    /**
     * LABEL_FRAGMENTED_ELEMENT_NAME
     */
    public static final String LABEL_FRAGMENTED_ELEMENT_NAME = Messages.getString("LABEL_FRAGMENTED_ELEMENT_NAME"); //$NON-NLS-1$;

    /**
     * LABEL_FRAGMENTED_ELEMENT_FILE_PATH
     */
    public static final String LABEL_FRAGMENTED_ELEMENT_FILE_PATH = Messages.getString("LABEL_FRAGMENTED_ELEMENT_FILE_PATH"); //$NON-NLS-1$;

    /**
     * LABEL_DIAGRAM_LIST
     */
    public static final String LABEL_DIAGRAM_LIST = Messages.getString("LABEL_DIAGRAM_LIST"); //$NON-NLS-1$;

    /**
     * MESSAGE_DIAGRAM_LIST
     */
    public static final String MESSAGE_DIAGRAM_LIST = Messages.getString("MESSAGE_DIAGRAM_LIST"); //$NON-NLS-1$;

    /**
     * LABEL_OPEN
     */
    public static final String LABEL_OPEN = Messages.getString("LABEL_OPEN"); //$NON-NLS-1$;

    /**
     * LABEL_DIAGRAM_TYPE
     */
    public static final String LABEL_DIAGRAM_TYPE = Messages.getString("LABEL_DIAGRAM_TYPE"); //$NON-NLS-1$;

    /**
     * LABEL_DIAGRAM_FILE_PATH
     */
    public static final String LABEL_DIAGRAM_FILE_PATH = Messages.getString("LABEL_DIAGRAM_FILE_PATH"); //$NON-NLS-1$;

    /**
     * LABEL_DIAGRAM_NAME
     */
    public static final String LABEL_DIAGRAM_NAME = Messages.getString("LABEL_DIAGRAM_NAME"); //$NON-NLS-1$;

    /**
     * LABEL_FRAGMENT_DIAGRAM
     */
    public static final String LABEL_FRAGMENT_DIAGRAM = Messages.getString("LABEL_FRAGMENT_DIAGRAM"); //$NON-NLS-1$;
    
    /**
     * MESSAGE_CREATE_NCP_RELATION
     */
    public static final String MESSAGE_CREATE_NCP_RELATION = Messages.getString("MESSAGE.CREATE.NCP.RELATION"); //$NON-NLS-1$;
    
    /**
     * LABEL_REFERENCE
     */
    public static final String LABEL_REFERENCE = Messages.getString("LABEL.REFERENCE"); //$NON-NLS-1$;
    
    /** 조건절 추가 */
    public static final String LABEL_ADD_INTERACTIONOPERAND = Messages.getString("LABEL.ADD.INTERACTIONOPERAND"); //$NON-NLS-1$;
    
    /** 유효한 프로파일이 아닙니다. */
    public static final String MESSAGE_NOT_VALID_PROFILE_NAME = Messages.getString("MESSAGE.NOT.VALID.PROFILE.NAME"); //$NON-NLS-1$;

    /**
     * MESSAGE_CHOOSE_COPY_TYPE
     */
    public static String MESSAGE_CHOOSE_COPY_TYPE = Messages.getString("MESSAGE.CHOOSE.COPY.TYPE"); //$NON-NLS-1$;
    
    /** 프로젝트 탐색기에서 찾을 수 있는 요소가 아닙니다. */
    public static String MESSAGE_CANNOT_FIND_ELEMENT = Messages.getString("MESSAGE.CANNOT.FIND.ELEMENT"); //$NON-NLS-1$;
        
    /**
     * 메시지를 조합하여 리턴한다. 파라메터로는 여러 개의 String을 넘길 수 있다. 첫 번째부터 ${0}, ${1}, ${2},
     * ... , ${n} 의 형태로 문자열 치환이 일어난다.
     * 
     * @param baseMessage
     * @param replaces
     * @return String
     */
    public static String getMessage(String baseMessage, String... replaces) {
        StringBuilder message = new StringBuilder(baseMessage);
        String indexString = ""; //$NON-NLS-1$
        int start, end;

        for (int i = 0; i < replaces.length; i++) {
            indexString = "${" + i + "}"; //$NON-NLS-1$ //$NON-NLS-2$
            while ((start = message.indexOf(indexString)) != -1) {
                end = start + indexString.length();
                message.replace(start, end, replaces[i]);
            }
        }

        return message.toString();
    }

}
