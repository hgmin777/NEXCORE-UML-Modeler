/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.constant;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.connector</li>
 * <li>서브 업무명 : nexcore.tool.uml.constant</li>
 * <li>설 명 : DefinitionConstant</li>
 * <li>작성일 : 2011. 7. 28.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public class DefinitionConstant {

    public static final String UML_VERSION = "2.0.0"; //$NON-NLS-1$
    
    /**
     * PD_CD_RM
     */
    public static final String PD_CD_RM = "RM"; // RM PRODUCT CODE

    /**
     * PD_RES_CD_RM_PRJ
     */
    public static final String PD_RES_CD_RM_PRJ = "prj"; // RESOURCE CODE - PROJECT 프로젝트

    /**
     * PD_RES_CD_RM_BIZ
     */
    public static final String PD_RES_CD_RM_BIZ = "biz"; // RESOURCE CODE - BIZGROUP 업무그룹

    /**
     * PD_RES_CD_RM_REQ
     */
    public static final String PD_RES_CD_RM_REQ = "req"; // RESOURCE CODE - REQUIREMENT 요구사항

    /**
     * PD_RES_CD_RM_REQD
     */
    public static final String PD_RES_CD_RM_REQD = "reqD"; // RESOURCE CODE - USECASE 요구사항명세

    /**
     * PD_RES_PROP_CD_RM_BF
     */
    public static final String PD_RES_PROP_CD_RM_BF = "BF"; // 기본흐름

    /**
     * PD_RES_PROP_CD_RM_SF
     */
    public static final String PD_RES_PROP_CD_RM_SF = "SF"; // 서브흐름

    /**
     * PD_RES_PROP_CD_RM_EF
     */
    public static final String PD_RES_PROP_CD_RM_EF = "EF"; // 예외흐름
    
    /** UML 프로젝트 코드 */
    public static final String PD_CD_UML = "uml"; //$NON-NLS-1$

    /** UML 리소스 코드 : 유스케이스 모델 - 유스케이스 */
    public static final String PD_RES_CD_UML_UM_USECASE = "UMUseCase"; //$NON-NLS-1$
    /** UML 리소스 코드 : 분석 모델 - Boundary 요소 */
    public static final String PD_RES_CD_UML_AM_BOUNDARY = "AMBoundary"; //$NON-NLS-1$
    /** UML 리소스 코드 : 분석 모델 - Control 요소 */
    public static final String PD_RES_CD_UML_AM_CONTROL = "AMControl"; //$NON-NLS-1$
    /** UML 리소스 코드 - 분석 모델 - Entity 요소 */
    public static final String PD_RES_CD_UML_AM_ENTITY = "AMEntity"; //$NON-NLS-1$
    
    /**
     * BASIC_FLOW
     */
    public static final String BASIC_FLOW = "BASIC_FLOW"; //$NON-NLS-1$
    /**
     * SUB_FLOW
     */
    public static final String SUB_FLOW = "SUB_FLOW"; //$NON-NLS-1$
    /**
     * EXCEPT_FLOW
     */
    public static final String EXCEPT_FLOW = "EXCEPT_FLOW"; //$NON-NLS-1$
    
    /**
     * OPERATION
     */
    public static final String OPERATION = "OPERATION"; //$NON-NLS-1$
    /**
     * ATTRIBUTE
     */
    public static final String ATTRIBUTE = "ATTRIBUTE"; //$NON-NLS-1$

    /**
     * USE_COLLABORATION
     */
    public static final String USE_COLLABORATION = "useCollaboration"; //$NON-NLS-1$
    
}
