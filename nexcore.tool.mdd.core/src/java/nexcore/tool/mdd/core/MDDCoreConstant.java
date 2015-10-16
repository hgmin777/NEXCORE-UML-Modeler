/*
 * Copyright (c) 2010 SK C&C Co., Ltd. All rights reserved.
 * 
 * This software is the confidential and proprietary information of SK C&C. You
 * shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK
 * C&C.
 */

package nexcore.tool.mdd.core;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.mdd.core</li>
 * <li>서브 업무명 : nexcore.tool.mdd.core</li>
 * <li>설 명 : MDDCoreConstant</li>
 * <li>작성일 : 2010. 11. 18.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class MDDCoreConstant {

    /** 확장점 - 도메인 모델 핸들러 */
    public static final String NEXCORE_TOOL_MDD_CORE_DOMAIN_MODEL_HANDLER = "nexcore.tool.mdd.core.domain.model.handler"; //$NON-NLS-1$

    /** 확장점 - 시맨틱 모델 핸들러 */
    public static final String NEXCORE_TOOL_MDD_CORE_SEMANTIC_MODEL_HANDLER = "nexcore.tool.mdd.core.semantic.model.handler"; //$NON-NLS-1$

    /** 확장점 - 노테이션 모델 핸들러 */
    public static final String NEXCORE_TOOL_MDD_CORE_NOTATION_MODEL_HANDLER = "nexcore.tool.mdd.core.notation.model.handler"; //$NON-NLS-1$

    /** 확장점 - 요소 아이디 */
    public static final String EXTENSION_ELEMENT_ID = "id"; //$NON-NLS-1$

    /** 확장점 - 요소 라벨 */
    public static final String EXTENSION_ELEMENT_LABEL = "label"; //$NON-NLS-1$

    /** 확장점 - 요소 구현 인터페이스 */
    public static final String EXTENSION_ELEMENT_IMPLEMENT_INTERFACE = "implementInterface"; //$NON-NLS-1$

    /** .dst */
    public static final String MDA_DESIGNER_RULE_FILE_EXTENTION = ".dst"; //$NON-NLS-1$

    /** UML 모델러 도메인 모델 핸들러 확장점 */
    public static final String NEXCORE_TOOL_UML_MANAGER_DOMAIN_MODEL_HANDLER = "nexcore.tool.uml.manager.domainModelHandler"; //$NON-NLS-1$

    /** UML 모델러 시맨틱 모델 핸들러 확장점 */
    public static final String NEXCORE_TOOL_UML_MANAGER_SEMANTIC_MODEL_HANDLER = "nexcore.tool.uml.manager.semanticModelHandler"; //$NON-NLS-1$

    /** MDA 전개를 위한 TransactionalEditingDomain ID */
    public static final String MDA_TRANSACTIONAL_EDITING_DOMAIN_ID = "nexcore.tool.mda.core.transactionalEditingDomainID"; //$NON-NLS-1$

    /** 유스케이스모델 전개 패키지명 환경설정 키 */
    public static final String PREFERENCE_KEY_USECASES_PACKAGE_NAME = "usecasesPackageNamePreference";

    /** 분석모델 전개 패키지명 환경설정 키 */
    public static final String PREFERENCE_KEY_USECASE_REALIZATION_PACKAGE_NAME = "usecaseRealizationPackageNamePreference";

    /** 유스케이스 기본 패키지명 */
    public static final String USECASES_PACKAGE_DEFAULT_NAME = "Usecases";

    /** 분석모델 전개 기본 패키지명 */
    public static final String USECASE_REALIZATION_PACKAGE_DEFAULT_NAME = "Usecase Realization";

    /** Class 타입 명 */
    public static final String UML_CLASS_LITERAL = "Class"; //$NON-NLS-1$
    
    /** Component 타입 명 */
    public static final String UML_COMPONENT_LITERAL = "Component"; //$NON-NLS-1$
    
    /** Interface 타입 명 */
    public static final String UML_INTERFACE_LITERAL = "Interface"; //$NON-NLS-1$
    

    /** Operation 타입 명 */
    public static final String UML_OPERATION_LITERAL = "Operation"; //$NON-NLS-1$

    /** Property 타입 명 */
    public static final String UML_PROPERTY_LITERAL = "Property"; //$NON-NLS-1$

    /** base_Class */
    public static final String BASE_CLASS = "base_Class";//$NON-NLS-1$

    /** base_Operation */
    public static final String BASE_OPERATION = "base_Operation";//$NON-NLS-1$

    /** base_Property */
    public static final String BASE_PROPERTY = "base_Property";//$NON-NLS-1$

    /**
     * MDAD의 룰에디터에서 지원하는 stereotype 의 적용가능한 타입명 목록 2011.02.17 현재 클래스, 오퍼레이션,
     * 프로퍼티 3개만 지원함. 만일, 목록이 늘어나면 추가할 것.
     */
    public static final String[] APPLICABLE_TYPE_NAMES = { BASE_CLASS, BASE_OPERATION, BASE_PROPERTY }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

    /** Location [base] */
    public static final String BASE_QUALIFIER = "[base]"; //$NON-NLS-1$

    /** Location [self] */
    public static final String SELF_QUALIFIER = "[self]"; //$NON-NLS-1$

    /** '/' */
    public static final String LABEL_SLASH = "/"; //$NON-NLS-1$

    /** Location [Base] */
    public static final String BASE_QUALIFIER_M = "[Base]"; //$NON-NLS-1$

    /** Location [Self] */
    public static final String SELF_QUALIFIER_M = "[Self]"; //$NON-NLS-1$

    /** Location [BASE] */
    public static final String BASE_QUALIFIER_B = "[BASE]"; //$NON-NLS-1$

    /** Location [SELF] */
    public static final String SELF_QUALIFIER_B = "[SELF]"; //$NON-NLS-1$

    /** 오퍼레이션 */
    public static final String OPERATION_QULIFIER_B = "[OPERATION]"; //$NON-NLS-1$

    /** [ */
    public static final String OPEN_BRACKET = "["; //$NON-NLS-1$

    /** ] */
    public static final String CLOSE_BRACKET = "]"; //$NON-NLS-1$

    /** '' */
    public static final String EMPTY_STRING = ""; //$NON-NLS-1$

    /** . */
    public static final String DOT = "."; //$NON-NLS-1$

    /** ',' */
    public static final String COMMA = ","; //$NON-NLS-1$
    
    /** :: */
    public static final String QUALIFIED_SEGMENTATION_STRING = "::"; //$NON-NLS-1$

    /** Actor */
    public static final String ACTOR = "Actor"; //$NON-NLS-1$

    /** Type */
    public static final String TYPE = "Type"; //$NON-NLS-1$

    /** 노테이션 모델 핸들러 아이디 */
    public static final String NEXCORE_TOOL_MDA_DESIGNER_NOTATION_MODEL_HANDLER = "nexcore.tool.uml.manager.notationModelHandler"; //$NON-NLS-1$

    
    
    // NEXCORE용 MDAC를 위한 상수들
    /** Component 메타데이터 생성시에 사용하는 스테레오타입 이름 */
    public static final String COMPONENT_METADATA_STEREOTYPE_NAME = "Component"; //$NON-NLS-1$
    /** BizUnit 메타데이터 생성시 bizunit 정보 가져올 때 사용하는 스테레오타입 이름 */
    public static final String BIZUNIT_METADATA_BIZUNIT_STEREOTYPE_NAME = "BizUnit"; //$NON-NLS-1$
    /** Component 스테레오타입에서 fq Id 키 : fqId */
    public static final String FQ_ID_KEY_IN_COMPONENT_STEREOTYPE = "fqId"; //$NON-NLS-1$
    /** BizUnit 메타데이터 생성시 method 정보 가져올 때 사용하는 스테레오타입 이름 */
    public static final String BIZUNIT_METADATA_METHOD_STEREOTYPE_NAME = "service"; //$NON-NLS-1$
    /** field를 FixedLength로 생성해야 하고, method의 transactionId에 오퍼레이션명을 써야 하는 BizUnit의 Prefix */
    public static final String BIZUNIT_METADATA_FIELD_FIXED_LENGTH_PREFIX = "P"; //$NON-NLS-1$
    
    /** BizUnit 스테레오타입에서 bizunit name 키 : korean */
    public static final String BIZUNIT_NAME_KEY_IN_BIZUNIT_STEREOTYPE = "korean"; //$NON-NLS-1$
    /** BizUnit 스테레오타입에서 bizunit eng name 키 : english */
    public static final String BIZUNIT_ENG_NAME_KEY_IN_BIZUNIT_STEREOTYPE = "english"; //$NON-NLS-1$
    /** Method 스테레오타입에서 method name 키 : koName */
    public static final String METHOD_NAME_KEY_IN_METHOD_STEREOTYPE = "koName"; //$NON-NLS-1$
    /** Method 스테레오타입에서 method eng name 키 : enName */
    public static final String METHOD_ENG_NAME_KEY_IN_METHOD_STEREOTYPE = "enName"; //$NON-NLS-1$
    
    /** MDA 프로파일명 */
    public static final String[] MDA_PROFILES_NAMES = {
        "SKCC_MDA_Analysis_Model", "SKCC_MDA_Design_Model", "SKCC_MDA_EGov_Design_Model" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    
}
