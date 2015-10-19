/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.ui.core.property.VisibilityType;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core</li>
 * <li>설 명 : UICoreConstant</li>
 * <li>작성일 : 2010. 3. 8.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class UICoreConstant {

    /** nexcore.tool.uml.validation에서 사용하는 MarkerType */
    public static final String VALIDATION_MARKER_TYPE = "nexcore.tool.uml.validation.validationProblem"; //$NON-NLS-1$

    /** nexcore.tool.uml.ui.project.createActivityDiagram */
    public static final String PROJECT_CONSTANTS__ActivityDiagramID = "nexcore.tool.uml.ui.project.createActivityDiagram"; //$NON-NLS-1$

    /** nexcore.tool.uml.ui.analysis.activitydiagrameditor */
    public static final String PROJECT_CONSTANTS__ActivityEditorID = "nexcore.tool.uml.ui.analysis.activitydiagrameditor"; //$NON-NLS-1$

    /** ' ' */
    public static final String PROJECT_CONSTANTS__BLANK = " "; //$NON-NLS-1$
    
    /** 0 */
    public static final String PROJECT_CONSTANTS__ZERO = "0"; //$NON-NLS-1$

    /** @@ */
    public static final String REPORT_DOCUMENTATION_SEPARATOR = "@@"; //$NON-NLS-1$

    /** Perspective - bottom */
    public static final String PROJECT_CONSTANTS__BOTTOM = "bottom"; //$NON-NLS-1$

    /** nexcore.tool.uml.ui.project.createClassDiagram */
    public static final String PROJECT_CONSTANTS__ClassDiagramID = "nexcore.tool.uml.ui.project.createClassDiagram"; //$NON-NLS-1$

    /** nexcore.tool.uml.ui.realization.classdiagrameditor */
    public static final String PROJECT_CONSTANTS__ClassEditorID = "nexcore.tool.uml.ui.realization.classdiagrameditor"; //$NON-NLS-1$

    /** : */
    public static final String PROJECT_CONSTANTS__COLON = ":"; //$NON-NLS-1$
    
    /**
     * PROJECT_CONSTANTS__HYPHEN
     */
    public static final String PROJECT_CONSTANTS__HYPHEN = "-"; //$NON-NLS-1$

    /**
     * PROJECT_CONSTANTS__DELIMETER
     */
    public static final String PROJECT_CONSTANTS__DELIMETER = "|"; //$NON-NLS-1$

    /** ', ' */
    public static final String PROJECT_CONSTANTS__COMMA = ", "; //$NON-NLS-1$

    /** nexcore.tool.uml.ui.project.createCompoDiagram */
    public static final String PROJECT_CONSTANTS__ComponentDiagramID = "nexcore.tool.uml.ui.project.createComponentDiagram"; //$NON-NLS-1$

    /** nexcore.tool.uml.ui.analysis.componentdiagrameditor */
    public static final String PROJECT_CONSTANTS__ComponentEditorID = "nexcore.tool.uml.ui.analysis.componentdiagrameditor"; //$NON-NLS-1$

    /** nexcore.tool.uml.ui.project.contributorId */
    public static final String PROJECT_CONSTANTS__CONTRIBUTOR_ID = "org.eclipse.ui.navigator.ProjectExplorer"; //$NON-NLS-1$

    /** nexcore.tool.uml.ui.project.decorator */
    public static final String PROJECT_CONSTANTS__DECORATOR_ID = "nexcore.tool.uml.ui.project.decorator"; //$NON-NLS-1$

    /** umlmodel */
    public static final String PROJECT_CONSTANTS__DEFAULT_FILENAME = "umlmodel"; //$NON-NLS-1$

    /** Diagram */
    public static final String PROJECT_CONSTANTS__DIAGRAM = "Diagram"; //$NON-NLS-1$

    /** Fragment */
    public static final String PROJECT_CONSTANTS__FRAGMENTANNOTATION = "Fragment"; //$NON-NLS-1$

    /** Fragment Folder Name */
    public static final String PROJECT_CONSTANTS__FRAGMENT_FOLDER_NAME = ".fragment"; //$NON-NLS-1$

    /** Fragment Folder Name */
    public static final String PROJECT_CONSTANTS__RELATION = ".relation"; //$NON-NLS-1$
    /** . */
    public static final String PROJECT_CONSTANTS__DOT = "."; //$NON-NLS-1$

    /** :: */
    public static final String PROJECT_CONSTANTS__DOUBLE_COLON = "::"; //$NON-NLS-1$

    /** ::: */
    public static final String PROJECT_CONSTANTS__TRIPLE_COLON = ":::"; //$NON-NLS-1$

    /** \\ */
    public static final String PROJECT_CONSTANTS__SEPARATOR = "\\"; //$NON-NLS-1$

    /** TransactionalEditingDomain ID */
    public static final String PROJECT_CONSTANTS__EditingDomainID = "nexcore.tool.uml.manager.editingDomain"; //$NON-NLS-1$

    /** '' */
    public static final String PROJECT_CONSTANTS__EMPTY_STRING = ""; //$NON-NLS-1$

    /** void */
    public static final String PROJECT_CONSTANTS__VOID = "void"; //$NON-NLS-1$
    
    /** boolean */
    public static final String PROJECT_CONSTANTS__BOOLEAN = "boolean"; //$NON-NLS-1$

    /** 행 바꿈 */
    public static final String PROJECT_CONSTANTS__NEW_LINE = "\n"; //$NON-NLS-1$

    /** UTF-8 */
    public static final String PROJECT_CONSTANTS__ENCODING_UTF8 = "UTF-8"; //$NON-NLS-1$

    /** umx */
    public static final String PROJECT_CONSTANTS__FILE_EXTENSION = ManagerConstant.UMLDOMAIN_CONSTANT__MODEL_FILE_EXTENSION;

    /** << */
    public static final String PROJECT_CONSTANTS__FIRST_BRACKET = String.valueOf((char) Integer.parseInt("00AB", 16)); //$NON-NLS-1$

    /** Fragment */
    public static final String PROJECT_CONSTANTS__FRAGMENT = ManagerConstant.UMLDOMAIN_CONSTANT__FRAGMENT_ANNOTATION_NAME;

    /** FragmentContainer */
    public static final String PROJECT_CONSTANTS__FRAGMENT_CONTAINER = ManagerConstant.UMLDOMAIN_CONSTANT__FRAGMENT_CONTAINER_ANNOTATION_NAME;

    /** model version */
    public static final String PROJECT_CONSTANTS__MODEL_VERSION = "1.1.2";

    /** ProjectInfo */
    public static final String PROJECT_CONSTANTS__PROJECT_INFO = "ProjectInfo"; //$NON-NLS-1$

    /** umf */
    public static final String PROJECT_CONSTANTS__FRAGMENT_EXTENSION = ManagerConstant.UMLDOMAIN_CONSTANT__FRAGMENT_FILE_EXTENSION;

    /** umg */
    public static final String PROJECT_CONSTANTS__GLOSSARY_EXTENSION = "umg"; //$NON-NLS-1$

    /** >> */
    public static final String PROJECT_CONSTANTS__LAST_BRACKET = String.valueOf((char) Integer.parseInt("00BB", 16)); //$NON-NLS-1$

    /** Perspective - left */
    public static final String PROJECT_CONSTANTS__LEFT = "left"; //$NON-NLS-1$

    /** < */
    public static final String PROJECT_CONSTANTS__LEFT_ANGLE_BRACKET = "<"; //$NON-NLS-1$

    /** Model 에디터 ID */
    public static final String PROJECT_CONSTANTS__MODEL_EDITOR_ID = "nexcore.tool.uml.ui.project.modeleditor"; //$NON-NLS-1$

    /** nexcore.tool.uml.ui.modelerNature */
    public static final String PROJECT_CONSTANTS__NATURE_ID = "nexcore.tool.uml.ui.modelerNature"; //$NON-NLS-1$

    /** org.eclipse.jdt.ui.wizards.NewClassCreationWizard */
    public static final String PROJECT_CONSTANTS__NewClassCreationWizardID = "org.eclipse.jdt.ui.wizards.NewClassCreationWizard"; //$NON-NLS-1$

    /** org.eclipse.jdt.ui.wizards.NewInterfaceCreationWizard */
    public static final String PROJECT_CONSTANTS__NewInterfaceCreationWizardID = "org.eclipse.jdt.ui.wizards.NewInterfaceCreationWizard"; //$NON-NLS-1$

    /** org.eclipse.jdt.ui.wizards.NewPackageCreationWizard */
    public static final String PROJECT_CONSTANTS__NewPackageCreationWizardID = "org.eclipse.jdt.ui.wizards.NewPackageCreationWizard"; //$NON-NLS-1$

    /** nexcore.tool.uml.ui.project.modelWizard */
    public static final String PROJECT_CONSTANTS__NewUmlID = "nexcore.tool.uml.ui.project.modelWizard"; //$NON-NLS-1$

    /** nexcore.tool.uml.ui.project.projectWizard */
    public static final String PROJECT_CONSTANTS__NewUmlProjectID = "nexcore.tool.uml.ui.project.projectWizard"; //$NON-NLS-1$

    /** 프로파일을 위한 XML메멘토 파일 */
    public static final String PROJECT_CONSTANTS__PROFILE_MEMENTO = "ProfileMemento.xml"; //$NON-NLS-1$

    /** org.eclipse.ui.navigator.ProjectExplorer */
    public static final String PROJECT_CONSTANTS__PROJECT_EXPLORER_ID = "org.eclipse.ui.navigator.ProjectExplorer"; //$NON-NLS-1$

    /** 프로퍼티 컨트리뷰터 Id */
    public static final String PROJECT_CONSTANTS__PROPERTY_CONTRIBUTOR_ID = "nexcore.tool.uml.ui.Property"; //$NON-NLS-1$

    /** org.eclipse.ui.resourcePerspective */
    public static final String PROJECT_CONSTANTS__ResourcePerspectiveID = "org.eclipse.ui.resourcePerspective"; //$NON-NLS-1$

    /** Perspective - right */
    public static final String PROJECT_CONSTANTS__RIGHT = "right"; //$NON-NLS-1$

    /** > */
    public static final String PROJECT_CONSTANTS__RIGHT_ANGLE_BRACKET = ">"; //$NON-NLS-1$

    /** ; */
    public static final String PROJECT_CONSTANTS__SEMICOLON = ";"; //$NON-NLS-1$

    /** nexcore.tool.uml.ui.project.createSequenceDiagram */
    public static final String PROJECT_CONSTANTS__SequenceDiagramID = "nexcore.tool.uml.ui.project.createSequenceDiagram"; //$NON-NLS-1$

    /** nexcore.tool.uml.ui.realization.sequencediagrameditor */
    public static final String PROJECT_CONSTANTS__SequenceEditorID = "nexcore.tool.uml.ui.realization.sequencediagrameditor"; //$NON-NLS-1$

    /** '/' */
    public static final String PROJECT_CONSTANTS__SLASH = "/"; //$NON-NLS-1$

    /** '*' */
    public static final String PROJECT_CONSTANTS__STAR = "*"; //$NON-NLS-1$

    /** 프로파일을 가져왔던 마지막 경로 XML메멘토 태그 */
    public static final String PROJECT_CONSTANTS__TAG_LAST_PROFILE_PATH = "LastProfilePath"; //$NON-NLS-1$

    /** 프로파일을 위한 XML메멘토 태그 */
    public static final String PROJECT_CONSTANTS__TAG_PROFILE = "Profile"; //$NON-NLS-1$

    /** UML 프로파일 확장자들 */
    public static final String[] PROJECT_CONSTANTS__UML_PROFILE_FILE_EXTENSIONS = { "*.uml", "*.epx" }; //$NON-NLS-1$ //$NON-NLS-2$

    /** nexcore.tool.uml.ui.project.perspective */
    public static final String PROJECT_CONSTANTS__UmlPerspectiveID = "nexcore.tool.uml.ui.project.perspective"; //$NON-NLS-1$

    /** _ */
    public static final String PROJECT_CONSTANTS__UNDER_BAR = "_"; //$NON-NLS-1$

    /** = */
    public static final String PROJECT_CONSTANTS__EQUAL = "="; //$NON-NLS-1$

    /** nexcore.tool.uml.ui.project.createUsecaseDiagram */
    public static final String PROJECT_CONSTANTS__UsecaseDiagramID = "nexcore.tool.uml.ui.project.createUsecaseDiagram"; //$NON-NLS-1$

    /** nexcore.tool.uml.ui.realization.usecasediagrameditor */
    public static final String PROJECT_CONSTANTS__UsecaseEditorID = "nexcore.tool.uml.ui.realization.usecasediagrameditor"; //$NON-NLS-1$

    /** org.eclipse.ui.wizards.new.file */
    public static final String PROJECT_CONSTANTS__WizardFileID = "org.eclipse.ui.wizards.new.file"; //$NON-NLS-1$

    /** org.eclipse.ui.wizards.new.folder */
    public static final String PROJECT_CONSTANTS__WizardFolderID = "org.eclipse.ui.wizards.new.folder"; //$NON-NLS-1$

    /** UsecaseDetail */
    public static final String PROJECT_CONSTANTS__USECASE_DETAIL = "UsecaseDetail"; //$NON-NLS-1$

    /** 임포트할 RM 데이터 엑셀 파일 확장자들 */
    public static final String[] PROJECT_CONSTANTS__IMPORT_RM_DATA_EXCEL_FILE_EXTENSIONS = { "*.xls", "*.xlsx" }; //$NON-NLS-1$ //$NON-NLS-2$

    /** 기본 라이브러리 이름들 */
    public static final String[] PROJECT_CONSTANTS__CORE_LIBRARY_NAMES = {
        "JavaPrimitiveTypes", "UMLPrimitiveTypes", "XMLPrimitiveTypes" }; //$NON-NLS-1$ //$NON-NLS-2$

    /** 요구사항 계층구조 시트 데이터 첫 Row 인덱스 값 */
    public static final int PROJECT_CONSTANTS__HIERARCHY_FIRST_ROW_INDEX = 1;

    /** 요구사항 계층구조 시트 종류 Column 인덱스 값 */
    public static final int PROJECT_CONSTANTS__HIERARCHY_TYPE_COLUMN_INDEX = 0;

    /** 요구사항 계층구조 시트 아이디 Column 인덱스 값 */
    public static final int PROJECT_CONSTANTS__HIERARCHY_ID_COLUMN_INDEX = 1;

    /** 요구사항 계층구조 시트 이름 Column 인덱스 값 */
    public static final int PROJECT_CONSTANTS__HIERARCHY_NAME_COLUMN_INDEX = 2;

    /** 요구사항 목록(Requirement) 시트 데이터 첫 Row 인덱스 값 */
    public static final int PROJECT_CONSTANTS__REQUIREMENT_FIRST_ROW_INDEX = 2;

    /** 요구사항 목록(Requirement) 시트 아이디 Column 인덱스 값 */
    public static final int PROJECT_CONSTANTS__REQUIREMENT_ID_COLUMN_INDEX = 1;

    /** 요구사항 목록(Requirement) 시트 이름 Column 인덱스 값 */
    public static final int PROJECT_CONSTANTS__REQUIREMENT_NAME_COLUMN_INDEX = 2;

    /** 요구사항 명세 목록(UseCase) 시트 데이터 첫 Row 인덱스 값 */
    public static final int PROJECT_CONSTANTS__USECASE_FIRST_ROW_INDEX = 2;

    /** 요구사항 명세 목록(UseCase) 시트 부모 아이디 Column 인덱스 값 */
    public static final int PROJECT_CONSTANTS__USECASE_PARENTID_COLUMN_INDEX = 0;

    /** 요구사항 명세 목록(UseCase) 시트 아이디 Column 인덱스 값 */
    public static final int PROJECT_CONSTANTS__USECASE_ID_COLUMN_INDEX = 1;

    /** 요구사항 명세 목록(UseCase) 시트 이름 Column 인덱스 값 */
    public static final int PROJECT_CONSTANTS__USECASE_NAME_COLUMN_INDEX = 2;

    /** RM 데이터 기본 모델명 */
    public static final String PROJECT_CONSTANTS__RMDATA_DEFAULT_MODEL_NAME = "RMData.rmd"; //$NON-NLS-1$

    /** 모델 상세 어노테이션 소스명 */
    public static final String PROJECT_CONSTANTS__MODEL_DETAIL_EANNOTATION_SOURCE_NAME = "ModelDetail"; //$NON-NLS-1$

    /** # */
    public static final String PROJECT_CONSTANTS__SHARP = "#"; //$NON-NLS-1$

    /** Adapter factory label provider. */
    public static final AdapterFactoryLabelProvider UMLSECTION_CONSTANTS__ADAPTER_FACTORY_LABEL_PROVIDER = new AdapterFactoryLabelProvider(UiCorePlugin.getDefault()
        .getItemProvidersAdapterFactory());

    /** _LABEL_WIDTH_HINT_FOR_RECTANGLE */
    public static final int UMLSECTION_CONSTANTS___LABEL_WIDTH_HINT_FOR_RECTANGLE = 80;

    /** _LABEL_WIDTH_HINT_FOR_RADIO */
    public static final int UMLSECTION_CONSTANTS___LABEL_WIDTH_HINT_FOR_RADIO = 75;

    /** _LABEL_WIDTH_HINT_FOR_CHARACTER */
    public static final int UMLSECTION_CONSTANTS___LABEL_WIDTH_HINT_FOR_CHARACTER = 80;

    /** 체크 버튼 위젯 앞의 공백 */
    public static final int UMLSECTION_CONSTANTS___LABEL_WIDTH_HINT_FOR_CHECK = 80;

    /** _TEXT_OF_DESCRIPTION_HEIGHT */
    public static final int UMLSECTION_CONSTANTS___TEXT_OF_DESCRIPTION_HEIGHT = 100;

    /** _TEXT_OF_DESCRIPTION_HEIGHT */
    public static final int UMLSECTION_CONSTANTS___TABLE_OF_APPLIED_STEREOTYPE_HEIGHT = 50;

    /** ":" */
    public static final String UMLSECTION_CONSTANTS__COLON_TEXT = ":"; //$NON-NLS-1$

    /** "..." */
    public static final String UMLSECTION_CONSTANTS__TRIPLE_DOT_TEXT = "..."; //$NON-NLS-1$

    /** SQUARE_BRAKET_LEFT */
    public static final String UMLSECTION_CONSTANTS__SQUARE_BRAKET_LEFT = "<"; //$NON-NLS-1$

    /** SQUARE_BRAKET_RIGHT */
    public static final String UMLSECTION_CONSTANTS__SQUARE_BRAKET_RIGHT = ">"; //$NON-NLS-1$

    /** SQUARE_BRAKET_LEFT_IN_DOUBLE */
    public static final String UMLSECTION_CONSTANTS__SQUARE_BRAKET_LEFT_IN_DOUBLE = "<<"; //$NON-NLS-1$

    /** SQUARE_BRAKET_RIGHT_IN_DOUBLE */
    public static final String UMLSECTION_CONSTANTS__SQUARE_BRAKET_RIGHT_IN_DOUBLE = ">>"; //$NON-NLS-1$

    /** PARENTHESIS_LEFT */
    public static final String UMLSECTION_CONSTANTS__PARENTHESIS_LEFT = "("; //$NON-NLS-1$

    /** PARENTHESIS_RIGHT */
    public static final String UMLSECTION_CONSTANTS__PARENTHESIS_RIGHT = ")"; //$NON-NLS-1$

    /** SQUARE_BRACKETS_LEFT */
    public static final String UMLSECTION_CONSTANTS__SQUARE_BRACKETS_LEFT = "["; //$NON-NLS-1$

    /** SQUARE_BRACKETS_RIGHT */
    public static final String UMLSECTION_CONSTANTS__SQUARE_BRACKETS_RIGHT = "]"; //$NON-NLS-1$

    /** KEYCODE_ENTER */
    public static final int UMLSECTION_CONSTANTS__KEYCODE_ENTER = '\r'; //$NON-NLS-1$

    /** KEYCODE_ENTER_SECOND */
    public static final int UMLSECTION_CONSTANTS__KEYCODE_ENTER_SECOND = 16777296;

    /** INDEX_ZERO */
    public static final int UMLSECTION_CONSTANTS__INDEX_ZERO = 0;

    /** NUMBER_ONE */
    public static final String UMLSECTION_CONSTANTS__NUMBER_ONE = "1"; //$NON-NLS-1$

    /** _UNIQUE */
    public static final String UMLSECTION_CONSTANTS___UNIQUE = String.valueOf(1);

    /** _ZERO */
    public static final String UMLSECTION_CONSTANTS___ZERO = String.valueOf(0);

    /** UMLSECTION_CONSTANTS__DEFAULT_VALUES_BOOLEAN */
    public static final String[] UMLSECTION_CONSTANTS__DEFAULT_VALUES_BOOLEAN = { "true", "false" }; //$NON-NLS-1$ //$NON-NLS-2$

    /** NAMEINDICATIONTYPE_BOOLEAN */
    public static final String[] NAMEINDICATIONTYPE_BOOLEAN = { "icon", "text" }; //$NON-NLS-1$ //$NON-NLS-2$

    /** UMLSECTION_CONSTANTS__DEFAULT_VALUES_NULL */
    public static final String UMLSECTION_CONSTANTS__DEFAULT_VALUES_NULL = PROJECT_CONSTANTS__BLANK;//"null"; //$NON-NLS-1$

    /** IS_BOOLEANS */
    public static final String[] UMLSECTION_CONSTANTS__IS_BOOLEANS = { Boolean.toString(true), Boolean.toString(false) };

    /** MULTIPLICITIES */
    public static final String[] UMLSECTION_CONSTANTS__MULTIPLICITIES = { 
        "(none)", "*", "0..1", "1", "1..*"
    };                

    /** VISIBILITIES */
    public static final String[] UMLSECTION_CONSTANTS__VISIBILITIES = { VisibilityType.PUBLIC.toString(),
        VisibilityType.PRIVATE.toString(), VisibilityType.PROTECTED.toString(), VisibilityType.PACKAGE.toString() };

    /** Visibility */
    public static final String VISIBILITY = "Visibility"; //$NON-NLS-1$
    
    /** DIRECTIONS */
    public static final String[] UMLSECTION_CONSTANTS__DIRECTIONS = { UMLMessage.LABEL_IN, UMLMessage.LABEL_IN_OUT,
        UMLMessage.LABEL_OUT, UMLMessage.LABEL_RETURN };

    /** UMLUSER */
    public static final String UML_USER = "UMLUSER"; //$NON-NLS-1$

    // 모델 검색 constants
    /** MODELSEARCH__PAGE_NAME */
    public static final String MODELSEARCH__PAGE_NAME = "UMLModelSearchPage"; //$NON-NLS-1$

    /** MODELSEARCH__STORE_CASE_SENSITIVE */
    public static final String MODELSEARCH__STORE_CASE_SENSITIVE = "CASE_SENSITIVE"; //$NON-NLS-1$

    /** MODELSEARCH__STORE_HISTORY_SIZE */
    public static final String MODELSEARCH__STORE_HISTORY_SIZE = "HISTORY_SIZE"; //$NON-NLS-1$

    /** MODELSEARCH__STORE_HISTORY */
    public static final String MODELSEARCH__STORE_HISTORY = "HISTORY"; //$NON-NLS-1$

    /** MODELSEARCH__HISTORY_SIZE */
    public static final int MODELSEARCH__HISTORY_SIZE = 12;

    /** MODELSEARCH__TYPE_DEFAULT */
    public static final String MODELSEARCH__TYPE_DEFAULT = "Default"; //$NON-NLS-1$

    /** MODELSEARCH__TYPE_PROJECT */
    public static final String MODELSEARCH__TYPE_PROJECT = "Project"; //$NON-NLS-1$

    /** MODELSEARCH__TYPE_MODEL */
    public static final String MODELSEARCH__TYPE_MODEL = "Model"; //$NON-NLS-1$

    /** MODELSEARCH__TYPE_NAMED_ELEMENT */
    public static final String MODELSEARCH__TYPE_NAMED_ELEMENT = "NamedElement"; //$NON-NLS-1$

    /** MODELSEARCH__TYPE_GLOSSARY */
    public static final String MODELSEARCH__TYPE_GLOSSARY = "Glossary"; //$NON-NLS-1$

    /** MODELSEARCH__TYPE_PACKAGE */
    public static final String MODELSEARCH__TYPE_PACKAGE = "Package"; //$NON-NLS-1$

    /** MODELSEARCH__TYPE_CLASS */
    public static final String MODELSEARCH__TYPE_CLASS = "Class"; //$NON-NLS-1$

    /** MODELSEARCH__TYPE_COMPONENT */
    public static final String MODELSEARCH__TYPE_COMPONENT = "Component"; //$NON-NLS-1$

    /** MODELSEARCH__TYPE_ACTOR */
    public static final String MODELSEARCH__TYPE_ACTOR = "Actor"; //$NON-NLS-1$

    /** MODELSEARCH__TYPE_USECASE */
    public static final String MODELSEARCH__TYPE_USECASE = "UseCase"; //$NON-NLS-1$

    /** MODELSEARCH__TYPE_INTERFACE */
    public static final String MODELSEARCH__TYPE_INTERFACE = "Interface"; //$NON-NLS-1$

    /** MODELSEARCH__SCHEME */
    public static final String MODELSEARCH__SCHEME = "platform"; //$NON-NLS-1$

    /** 템플릿파일 기준 경로 */
    public static final String XML_TEMPLATE_FILE_ROOT_PATH = "/nexcore/tool/uml/ui/project/report/templates/"; //$NON-NLS-1$

    /** Template들이 있는 경로 */
    public static final String TEMPLATE_PATH = "src/java" + XML_TEMPLATE_FILE_ROOT_PATH; //$NON-NLS-1$

    /** 클래스 리스트 템플릿 (xls) */
    public static final String TEMPLATE_EXCEL_CLASSLIST_XLS = "excel_classList.xls"; //$NON-NLS-1$

    /** 패키지 리스트 템플릿 (xls) */
    public static final String TEMPLATE_EXCEL_PACKAGELIST_XLS = "excel_packageList.xls"; //$NON-NLS-1$

    /** RM 유스케이스 추적 매트릭스 템플릿 (xls) */
    public static final String TEMPLATE_EXCEL_RM_USECASE_TRACE_MATRIX_XLS = "excel_rmUseCaseTraceMatrix.xls"; //$NON-NLS-1$

    /** 유스케이스 리스트 템플릿 (xls) */
    public static final String TEMPLATE_EXCEL_USECASELIST_XLS = "excel_usecaseList.xls"; //$NON-NLS-1$

    /** 클래스 명세서 템플릿 이름(사용자용) */
    public static final String TEMPLATE_XML_CLASS_DEFINITION_XML = "ClassDefinition.doc"; //$NON-NLS-1$

    /** 유스케이스 설계서 템플릿 이름(사용자용) */
    public static final String TEMPLATE_XML_USECASE_ARCHITECTURE_XML = "UsecaseDesignSpecification.doc"; //$NON-NLS-1$

    /** 유스케이스 분석서 템플릿 이름(사용자용) */
    public static final String TEMPLATE_XML_USECASE_ANALYSIS_XML = "UsecaseAnalysisSpecification.doc"; //$NON-NLS-1$

    /** 유스케이스 모형 기술서 템플릿 이름(사용자용) */
    public static final String TEMPLATE_XML_USECASE_DEFINITION_XML = "UsecaseSpecification.doc"; //$NON-NLS-1$

    /** 컴포넌트 명세서 템플릿 이름(사용자용) */
    public static final String TEMPLATE_XML_COMPONENT_DEFINITION_XML = "ComponentDefinition.doc"; //$NON-NLS-1$

    /** 컴포넌트 아키텍처 정의서 템플릿 이름(사용자용) */
    public static final String TEMPLATE_XML_COMPONENT_ARCHITECTURE_DEFINITION_XML = "ComponentArchitectureDefinition.doc"; //$NON-NLS-1$

    /** 컴포넌트 설계서 템플릿 이름(사용자용) */
    public static final String TEMPLATE_XML_COMPONENT_ARCHITECTURE_XML = "ComponentArchitecture.doc"; //$NON-NLS-1$

    /** 인터페이스 상호작용 명세서 템플릿 이름(사용자용) */
    public static final String TEMPLATE_XML_INTERFACE_INTERACTION_XML = "InterfaceInteractionDefinition.doc"; //$NON-NLS-1$

    /** 사용자 인터페이스 명세서 템플릿 이름(사용자용) */
    public static final String TEMPLATE_XML_UI_DEFINITION_XML = "UIDefinition.doc"; //$NON-NLS-1$

    /** 사용자 인터페이스 설계서 템플릿 이름(사용자용) */
    public static final String TEMPLATE_XML_UI_ARCHITECTURE_XML = "UIArchitecture.doc"; //$NON-NLS-1$

    /** 문서 산출물 템플릿 XML 스키마 이름 */
    public static final String TEMPLATE_XML_CHEMA_PATH = "wordtemplate.xsd";//$NON-NLS-1$

    /** 패키지 리스트 (XLS) 기본 파일 이름 */
    public static final String REPORT__PACKAGE_LIST_TO_XLS_DEFAULT_FILE_NAME = "PackageList.xls"; //$NON-NLS-1$

    /** 클래스 리스트 (XLS) 기본 파일 이름 */
    public static final String REPORT__CLASS_LIST_TO_XLS_DEFAULT_FILE_NAME = "ClassList.xls"; //$NON-NLS-1$

    /** 유즈케이스 리스트 (XLS) 기본 파일 이름 */
    public static final String REPORT__USECASE_LIST_TO_XLS_DEFAULT_FILE_NAME = "UsecaseList.xls"; //$NON-NLS-1$

    /** 유즈케이스 모형 기술서 (XML) 기본 파일 이름 */
    public static final String REPORT__USECASE_DEFINITION_TO_XML_DEFAULT_FILE_NAME = UMLMessage.LABEL_USECASE_DEFINITION;

    /** 유즈케이스 설계서 (XML) 기본 파일 이름 */
    public static final String REPORT__USECASE_ARCHITECTURE_TO_XML_DEFAULT_FILE_NAME = UMLMessage.LABEL_USECASE_ARCHITECTURE;

    /** 유스케이스 분석서 (XML) 기본 파일 이름 */
    public static final String REPORT__USECASE_ANALYSIS_SPECIFICATION_TO_XML_DEFAULT_FILE_NAME = UMLMessage.LABEL_USECASE_ANALYSIS_SPECIFICATION;

    /** 컴포넌트 명세서 (XML) 기본 파일 이름 */
    public static final String REPORT__COMPONENT_DEFINITION_TO_XML_DEFAULT_FILE_NAME = UMLMessage.LABEL_COMPONENT_DEFINITION;

    /** 인터페이스 상호작용 명세서 (XML) 기본 파일 이름 */
    public static final String REPORT__INTERFACE_INTERACTION_TO_XML_DEFAULT_FILE_NAME = UMLMessage.LABEL_INTERFACE_INTERACTION_DEFINITION;

    /** 컴포넌트 아키텍처 정의서 (XML) 기본 파일 이름 */
    public static final String REPORT__COMPONENT_ARCHITECTURE_TO_XML_DEFAULT_FILE_NAME = UMLMessage.LABEL_COMPONENT_ARCHITECTURE_DEFINITION;

    /** 컴포넌트 설계서 (XML) 기본 파일 이름 */
    public static final String REPORT__COMPONENT_ARCHITECTURE1_TO_XML_DEFAULT_FILE_NAME = UMLMessage.LABEL_COMPONENT_ARCHITECTURE;

    /** 트랜잭션 설계서 (XML) 기본 파일 이름 */
    public static final String REPORT__TRANSACTION_ARCHITECTURE_TO_XML_DEFAULT_FILE_NAME = UMLMessage.LABEL_TRANSACTION_ARCHITECTURE;
    
    /** 사용자 인터페이스 명세서 (XML) 기본 파일 이름 */
    public static final String REPORT__USER_INTERFACE_TO_XML_DEFAULT_FILE_NAME = UMLMessage.LABEL_USER_INTERFACE_DEFINITION;

    /** 사용자 인터페이스 설계서 (XML) 기본 파일 이름 */
    public static final String REPORT__UI_ARCHITECTURE_TO_XML_DEFAULT_FILE_NAME = UMLMessage.LABEL_USER_INTERFACE_ARCHITECTURE;

    /** RM 유즈케이스 추적표 (XLS) 기본 파일 이름 */
    public static final String REPORT__RM_USECASE_TRACE_MATRIX_TO_XLS_DEFAULT_FILE_NAME = "RMUseCaseTraceMatrix.xls"; //$NON-NLS-1$

    /** 문서 타입 - 리스트 */
    public static final String REPORT__DOCTYPE_LIST = "List"; //$NON-NLS-1$

    /** 문서 타입 - 명세서 */
    public static final String REPORT__DOCTYPE_DEFINITION = "Definition"; //$NON-NLS-1$

    /** 문서 타입 - 추적표 */
    public static final String REPORT__DOCTYPE_TRACE_MATRIX = "TraceMatrix"; //$NON-NLS-1$

    /** 문서 타입 - 리스트 파일 확장자 */
    public static final String REPORT__DOCTYPE_LIST_FILE_EXTENSION = "*.xls"; //$NON-NLS-1$

    /** 문서 타입 - 명세서 파일 확장자 */
    public static final String REPORT__DOCTYPE_DEFINITION_FILE_EXTENSION = "*.doc"; //$NON-NLS-1$

    /** 문서 타입 - 추적표 파일 확장자 */
    public static final String REPORT__DOCTYPE_TRACE_MATRIX_FILE_EXTENSION = "*.xls"; //$NON-NLS-1$

    /** 패키지 리스트 (XLS) 템플릿 파일 경로 */
    public static final String PACKAGE_LIST_TO_XLS_TEMPLATE_FILE_PATH = XML_TEMPLATE_FILE_ROOT_PATH
        + TEMPLATE_EXCEL_PACKAGELIST_XLS;

    /** 클래스 리스트 (XLS) 템플릿 파일 경로 */
    public static final String CLASS_LIST_TO_XLS_TEMPLATE_FILE_PATH = XML_TEMPLATE_FILE_ROOT_PATH
        + TEMPLATE_EXCEL_CLASSLIST_XLS;

    /** 유즈케이스 리스트 (XLS) 템플릿 파일 경로 */
    public static final String USECASE_LIST_TO_XLS_TEMPLATE_FILE_PATH = XML_TEMPLATE_FILE_ROOT_PATH
        + TEMPLATE_EXCEL_USECASELIST_XLS;

    /** 클래스 명세서 (XML) 템플릿 파일 경로 */
    public static final String CLASS_DEFINITION_TO_XML_TEMPLATE_FILE_PATH = XML_TEMPLATE_FILE_ROOT_PATH
        + TEMPLATE_XML_CLASS_DEFINITION_XML;

    /** 컴포넌트 명세서 (XML) 템플릿 파일 경로 */
    public static final String COMPONENT_DEFINITION_TO_XML_TEMPLATE_FILE_PATH = XML_TEMPLATE_FILE_ROOT_PATH
        + TEMPLATE_XML_COMPONENT_DEFINITION_XML;

    /** 유즈케이스 모형 기술서 (XML) 템플릿 파일 경로 */
    public static final String USECASE_DEFINITION_TO_XML_TEMPLATE_FILE_PATH = XML_TEMPLATE_FILE_ROOT_PATH
        + TEMPLATE_XML_USECASE_DEFINITION_XML;

    /** 유즈케이스 설계서 템플릿 파일 경로 */
    public static final String USECASE_ARCHITECTURE_TEMPLATE_FILE_PATH = XML_TEMPLATE_FILE_ROOT_PATH
        + TEMPLATE_XML_USECASE_ARCHITECTURE_XML;

    /** 유스케이스 분석서 템플릿 파일 경로 */
    public static final String USECASE_ANALYSIS_SPECIFICATION_TEMPLATE_FILE_PATH = XML_TEMPLATE_FILE_ROOT_PATH
        + TEMPLATE_XML_USECASE_ANALYSIS_XML;

    /** RM 유즈케이스 추적표 (XLS) 템플릿 파일 경로 */
    public static final String RM_USECASE_TRACE_MATRIX_TO_XLS_TEMPLATE_FILE_PATH = XML_TEMPLATE_FILE_ROOT_PATH
        + TEMPLATE_EXCEL_RM_USECASE_TRACE_MATRIX_XLS;

    /** Project Name */
    public static final String REPORT__PROJECT_NAME = "PROJECT_NAME"; //$NON-NLS-1$

    /** System Name */
    public static final String REPORT__SYSTEM_NAME = "SYSTEM_NAME"; //$NON-NLS-1$

    /** Author */
    public static final String REPORT__AUTHOR = "AUTHOR"; //$NON-NLS-1$

    /** Date */
    public static final String REPORT__DATE = "DATE"; //$NON-NLS-1$

    /** Model Name */
    public static final String REPORT__MODEL_NAME = "MODEL_NAME"; //$NON-NLS-1$

    /** 보고서 목차용 스키마이름 */
    public static final String REPORT__PKG_INDEX_LIST = "PKG_INDEX_LIST";//$NON-NLS-1$

    /** 보고서 목차 [이름]용 스키마 이름 */
    public static final String REPORT__PKG_INDEX_NAME = "PKG_INDEX_NAME";//$NON-NLS-1$

    /** Package List */
    public static final String REPORT__PACKAGE_LIST = "PACKAGE_LIST"; //$NON-NLS-1$

    /** UC Package List */
    public static final String REPORT__UC_PACKAGE_LIST = "UC_PACKAGE_LIST"; //$NON-NLS-1$    

    /** Package Name */
    public static final String REPORT__PACKAGE_NAME = "PACKAGE_NAME"; //$NON-NLS-1$

    /** Package Namespace */
    public static final String REPORT__PACKAGE_NAMESPACE = "PACKAGE_NAMESPACE"; //$NON-NLS-1$

    /** Package Documentation */
    public static final String REPORT__PACKAGE_DOCUMENTATION = "PACKAGE_DOCUMENTATION"; //$NON-NLS-1$

    /** UC Package Name */
    public static final String REPORT__UC_PACKAGE_NAME = "UC_PACKAGE_NAME"; //$NON-NLS-1$

    /** Package Namespace */
    public static final String REPORT__UC_PACKAGE_NAMESPACE = "UC_PACKAGE_NAMESPACE"; //$NON-NLS-1$

    /** Package Documentation */
    public static final String REPORT__UC_PACKAGE_DOCUMENTATION = "UC_PACKAGE_DOCUMENTATION"; //$NON-NLS-1$

    /** Package Documentation */
    public static final String REPORT__ELEMENT_DOCUMENTATION = "ELEMENT_DOCUMENTATION"; //$NON-NLS-1$

    /** Element Qualified String */
    public static final String REPORT__ELEMENT_QUALIFIED_NAME = "ELEMENT_QUALIFIED_NAME"; //$NON-NLS-1$

    /** Diagram List */
    public static final String REPORT__DIAGRAM_LIST = "DIAGRAM_LIST"; //$NON-NLS-1$

    /** Diagram Name */
    public static final String REPORT__DIAGRAM_NAME = "DIAGRAM_NAME"; //$NON-NLS-1$

    /** Diagram Documentation */
    public static final String REPORT__DIAGRAM_DOCUMENTATION = "DIAGRAM_DOCUMENTATION"; //$NON-NLS-1$

    /** DIAGRAM_WIDTH */
    public static final String REPORT__DIAGRAM_WIDTH = "DIAGRAM_WIDTH"; //$NON-NLS-1$

    /** DIAGRAM_HIEGHT */
    public static final String REPORT__DIAGRAM_HEIGHT = "DIAGRAM_HEIGHT"; //$NON-NLS-1$

    /** Diagram Img */
    public static final String REPORT__DIAGRAM_IMG = "DIAGRAM_IMG"; //$NON-NLS-1$

    /** Class List */
    public static final String REPORT__CLASS_LIST = "CLASS_LIST"; //$NON-NLS-1$

    /** Class Diagram List */
    public static final String REPORT__CLASSDIAGRAM_LIST = "CLASSDIAGRAM_LIST"; //$NON-NLS-1$

    /** Package FullName */
    public static final String REPORT__PACKAGE_FULLNAME = "PACKAGE_FULLNAME"; //$NON-NLS-1$

    /** Class Name */
    public static final String REPORT__CLASS_NAME = "CLASS_NAME"; //$NON-NLS-1$

    /** Class Stereotype */
    public static final String REPORT__CLASS_STEREOTYPE = "CLASS_STEREOTYPE"; //$NON-NLS-1$

    /** Class Documentation */
    public static final String REPORT__CLASS_DOCUMENTATION = "CLASS_DOCUMENTATION"; //$NON-NLS-1$

    /** Attribute List */
    public static final String REPORT__ATTRIBUTE_LIST = "ATTRIBUTE_LIST"; //$NON-NLS-1$

    /** Attribute Name */
    public static final String REPORT__ATTRIBUTE_NAME = "ATTRIBUTE_NAME"; //$NON-NLS-1$

    /** Attribute Documentation */
    public static final String REPORT__ATTRIBUTE_DOCUMENTATION = "ATTRIBUTE_DOCUMENTATION"; //$NON-NLS-1$

    /** Attribute Visibility */
    public static final String REPORT__ATTRIBUTE_VISIBILITY = "ATTRIBUTE_VISIBILITY"; //$NON-NLS-1$

    /** Attribute Type */
    public static final String REPORT__ATTRIBUTE_TYPE = "ATTRIBUTE_TYPE"; //$NON-NLS-1$

    /** Attribute Default Value */
    public static final String REPORT__ATTRIBUTE_DEFAULT_VALUE = "ATTRIBUTE_DEFAULT_VALUE"; //$NON-NLS-1$

    /** Operation List */
    public static final String REPORT__OPERATION_LIST = "OPERATION_LIST"; //$NON-NLS-1$

    /** Operation Name */
    public static final String REPORT__OPERATION_NAME = "OPERATION_NAME"; //$NON-NLS-1$

    /** Operation Documentation */
    public static final String REPORT__OPERATION_DOCUMENTATION = "OPERATION_DOCUMENTATION"; //$NON-NLS-1$

    /** Operation Visibility */
    public static final String REPORT__OPERATION_VISIBILITY = "OPERATION_VISIBILITY"; //$NON-NLS-1$

    /** Operation Parameter */
    public static final String REPORT__OPERATION_PARAMETER = "OPERATION_PARAMETER"; //$NON-NLS-1$

    /** Operation Parameter Documentation */
    public static final String REPORT__OPERATION_PARAMETER_DOCUMENTATION = "OPERATION_PARAMETER_DOCUMENTATION"; //$NON-NLS-1$

    /** Operation Return Type */
    public static final String REPORT__OPERATION_RETURNTYPE = "OPERATION_RETURNTYPE"; //$NON-NLS-1$

    /** Open Parenthesis */
    public static final String REPORT__OPEN_PARENTHESIS = "("; //$NON-NLS-1$

    /** Close Parenthesis */
    public static final String REPORT__CLOSE_PARENTHESIS = ")"; //$NON-NLS-1$

    /** jpg */
    public static final String REPORT__IMG_JPG = "jpg"; //$NON-NLS-1$

    /** gif */
    public static final String REPORT__IMG_GIF = "gif"; //$NON-NLS-1$

    /** Temporary directory for Report images */
    public static final String REPORT__TMP_DIR = "umlTemp"; //$NON-NLS-1$

    /** Actor Documentation */
    public static final String REPORT__ACTOR_DOCUMENTATION = "ACTOR_DOCUMENTATION"; //$NON-NLS-1$

    /** Actor List */
    public static final String REPORT__ACTOR_LIST = "ACTOR_LIST"; //$NON-NLS-1$

    /** Actor Name */
    public static final String REPORT__ACTOR_NAME = "ACTOR_NAME"; //$NON-NLS-1$

    /** Actor Visibility */
    public static final String REPORT__ACTOR_VISIBILITY = "ACTOR_VISIBILITY"; //$NON-NLS-1$

    /** Extend Documentation */
    public static final String REPORT__EXTEND_DOCUMENTATION = "EXTEND_DOCUMENTATION"; //$NON-NLS-1$

    /** Extend List */
    public static final String REPORT__EXTEND_LIST = "EXTEND_LIST"; //$NON-NLS-1$

    /** Extend Name */
    public static final String REPORT__EXTEND_NAME = "EXTEND_NAME"; //$NON-NLS-1$

    /** Extend Visibility */
    public static final String REPORT__EXTEND_VISIBILITY = "EXTEND_VISIBILITY"; //$NON-NLS-1$

    /** Include Documentation */
    public static final String REPORT__INCLUDE_DOCUMENTATION = "INCLUDE_DOCUMENTATION"; //$NON-NLS-1$

    /** Include List */
    public static final String REPORT__INCLUDE_LIST = "INCLUDE_LIST"; //$NON-NLS-1$

    /** Include Name */
    public static final String REPORT__INCLUDE_NAME = "INCLUDE_NAME"; //$NON-NLS-1$

    /** Include Visibility */
    public static final String REPORT__INCLUDE_VISIBILITY = "INCLUDE_VISIBILITY"; //$NON-NLS-1$

    /** Usecase Documentation */
    public static final String REPORT__USECASE_DOCUMENTATION = "USECASE_DOCUMENTATION"; //$NON-NLS-1$

    /** Usecase Additional Information */
    public static final String REPORT__USECASE_ADDITIONAL_INFO = "USECASE_ADDITIONAL_INFO"; //$NON-NLS-1$

    /** Usecase Additional Information List */
    public static final String REPORT__USECASE_ADDITIONAL_LIST = "USECASE_ADDITIONAL_LIST"; //$NON-NLS-1$

    /** Usecase Additional Information Key */
    public static final String REPORT__USECASE_ADDITIONAL_KEY = "USECASE_ADDITIONAL_KEY"; //$NON-NLS-1$

    /** Usecase Additional Information Value key */
    public static final String REPORT__USECASE_ADDITIONAL_VALUE = "USECASE_ADDITIONAL_VALUE"; //$NON-NLS-1$

    /** Usecase List */
    public static final String REPORT__USECASE_LIST = "USECASE_LIST"; //$NON-NLS-1$

    /** Usecase Name */
    public static final String REPORT__USECASE_NAME = "USECASE_NAME"; //$NON-NLS-1$

    /** Usecase Path */
    public static final String REPORT__USECASE_PATH = "USECASE_PATH"; //$NON-NLS-1$

    /** Usecase keyword */
    public static final String REPORT__USECASE_KEYWORD = "USECASE_KEYWORD"; //$NON-NLS-1$

    /** Sequence Diagram List */
    public static final String REPORT__SEQUENCEDIAGRAM_LIST = "SEQUENCEDIAGRAM_LIST"; //$NON-NLS-1$

    /** Component Diagram List */
    public static final String REPORT__COMPONENTDIAGRAM_LIST = "COMPONENTDIAGRAM_LIST"; //$NON-NLS-1$

    /** Component List */
    public static final String REPORT__COMPONENT_LIST = "COMPONENT_LIST"; //$NON-NLS-1$

    /** Component Number */
    public static final String REPORT__COMPONENT_NO = "COMPONENT_NO"; //$NON-NLS-1$

    /** Component Name */
    public static final String REPORT__COMPONENT_NAME = "COMPONENT_NAME"; //$NON-NLS-1$

    /** Component Documentation */
    public static final String REPORT__COMPONENT_DOCUMENTATION = "COMPONENT_DOCUMENTATION"; //$NON-NLS-1$

    /** Component Stereotype */
    public static final String REPORT__COMPONENT_STEREOTYPE = "COMPONENT_STEREOTYPE"; //$NON-NLS-1$

    /** Related Page List */
    public static final String REPORT__RELATED_PAGE_LIST = "RELATED_PAGE_LIST"; //$NON-NLS-1$

    /** Web Contents List */
    public static final String REPORT__WEB_CONTENTS_LIST = "WEB_CONTENTS_LIST"; //$NON-NLS-1$

    /** Web Contents Name */
    public static final String REPORT__WEB_CONTENTS_NAME = "WEB_CONTENTS_NAME"; //$NON-NLS-1$

    /** Related Page Name */
    public static final String REPORT__PAGE_NAME = "PAGE_NAME"; //$NON-NLS-1$

    /** Related Page Relation */
    public static final String REPORT__PAGE_RELATION = "PAGE_RELATION"; //$NON-NLS-1$

    /** Server List */
    public static final String REPORT__SERVER_LIST = "SERVER_LIST"; //$NON-NLS-1$

    /** Server Name */
    public static final String REPORT__SERVER_NAME = "SERVER_NAME"; //$NON-NLS-1$

    /** Interface List */
    public static final String REPORT__INTERFACE_LIST = "INTERFACE_LIST"; //$NON-NLS-1$

    /** Interface Name */
    public static final String REPORT__INTERFACE_NAME = "INTERFACE_NAME"; //$NON-NLS-1$

    /** Interface Documentation */
    public static final String REPORT__INTERFACE_DOCUMENTATION = "INTERFACE_DOCUMENTATION"; //$NON-NLS-1$

    /** Interface Operation Name */
    public static final String REPORT__INTERFACE_OPERATION_NAME = "INTERFACE_OPERATION_NAME"; //$NON-NLS-1$

    /** Interface Operation Documentation */
    public static final String REPORT__INTERFACE_OPERATION_DOCUMENTATION = "INTERFACE_OPERATION_DOCUMENTATION"; //$NON-NLS-1$

    /** Class Algorithm */
    public static final String REPORT__CLASS_ALGORITHM = "CLASS_ALGORITHM"; //$NON-NLS-1$

    /** Operation 사전조건 */
    public static final String REPORT__OPERATION_PRECONDITION = "OPERATION_PRECONDITION"; //$NON-NLS-1$

    /** Operation 사후조건 */
    public static final String REPORT__OPERATION_POSTCONDITION = "OPERATION_POSTCONDITION"; //$NON-NLS-1$

    /** Operation 정의클래스명 */
    public static final String REPORT__OPERATION_IMPL_CLASS_NAME = "IMPL_CLASS_NAME"; //$NON-NLS-1$

    /** RM 유즈케이스 추적표 산출물 종류 - 요구사항 */
    public static final String REPORT__REQUIREMENT_TYPE = "R"; //$NON-NLS-1$

    /** RM 유즈케이스 추적표 산출물 종류 - 요구사항 명세 */
    public static final String REPORT__USECASE_TYPE = "U"; //$NON-NLS-1$

    /** RM 유즈케이스 추적표에서 적용된 스테레오타입 명 */
    public static final String REPORT__RM_USECASE_TRACE_MATRIX_APPLIED_STEREOTYPE_NAME = "RMUseCase"; //$NON-NLS-1$

    /** 스테레오 타입 RMUseCase의 요구사항 Id */
    public static final String REPORT__STEREOTYPE_RMUSECASE_PROPERTY_NAME_REQUIREMENTID = "requirementIds"; //$NON-NLS-1$

    /** 스테레오 타입 RMUseCase의 유즈케이스 Id */
    public static final String REPORT__STEREOTYPE_RMUSECASE_PROPERTY_NAME_USECASEID = "useCaseId"; //$NON-NLS-1$

    /** UML 코어 프로파일 상대 경로 */
    public static final String UML_CORE_PROFILE_RELATIVE_PATH = "/profiles/"; //$NON-NLS-1$

    /** Basic Flow List */
    public static final String REPORT__BASICFLOW_LIST = "BASICFLOW_LIST"; //$NON-NLS-1$

    /** Exception Flow List */
    public static final String REPORT__EXCEPTIONFLOW_LIST = "EXCEPTIONFLOW_LIST"; //$NON-NLS-1$

    /** Sub Flow List */
    public static final String REPORT__SUBFLOW_LIST = "SUBFLOW_LIST"; //$NON-NLS-1$

    /** Flow Type */
    public static final String REPORT__FLOW_TYPE = "FLOW_TYPE"; //$NON-NLS-1$

    /** Flow ID */
    public static final String REPORT__FLOW_ID = "FLOW_ID"; //$NON-NLS-1$

    /** Flow Name */
    public static final String REPORT__FLOW_NAME = "FLOW_NAME"; //$NON-NLS-1$

    /** Flow Outline */
    public static final String REPORT__FLOW_OUTLINE = "FLOW_OUTLINE"; //$NON-NLS-1$

    /** Flow Documentation */
    public static final String REPORT__FLOW_DOCUMENTATION = "FLOW_DOCUMENTATION"; //$NON-NLS-1$

    /** Pre Condition */
    public static final String REPORT__PRE_CONDITION = "PRE_CONDITION"; //$NON-NLS-1$

    /** Post Condition */
    public static final String REPORT__POST_CONDITION = "POST_CONDITION"; //$NON-NLS-1$

    /** Usecase RM ID */
    public static final String REPORT__RM_ID = "RM_ID"; //$NON-NLS-1$

    /** Usecase ID */
    public static final String REPORT__USECASE_ID = "USECASE_ID"; //$NON-NLS-1$

    /** 테이블에서 사용되는 유스케이스 */
    public static final String REPORT__USECASE_TABLE_NAME = "USECASE_TABLE_NAME"; //$NON-NLS-1$

    /**
     * EMPTY_STRING
     */
    public static final String EMPTY_STRING = ""; //$NON-NLS-1$

    /**
     * PROJECT_CONSTANTS__ICONS
     */
    public static final String PROJECT_CONSTANTS__ICONS = "icons/"; //$NON-NLS-1$

    /**
     * PROJECT_CONSTANTS__GIF
     */
    public static final String PROJECT_CONSTANTS__GIF = ".gif"; //$NON-NLS-1$

    /** UML Explorer 확장 액션 확장점 */
    public static final String NEXCORE_TOOL_UML_EXPLORER_EXTENDED_ACTION = "nexcore.tool.uml.explorer.extended.action"; //$NON-NLS-1$

    /** 확장점 - 요소 아이디 */
    public static final String EXTENSION_ELEMENT_ID = "id"; //$NON-NLS-1$

    /** 확장점 - 요소 라벨 */
    public static final String EXTENSION_ELEMENT_LABEL = "label"; //$NON-NLS-1$

    /** 확장점 - 요소 타겟 클래스 */
    public static final String EXTENSION_ELEMENT_TARGET_CLASS = "targetClass"; //$NON-NLS-1$

    /** 시맨틱 모델 핸들러 확장점 */
    public static final String NEXCORE_TOOL_UML_UI_PROJECT_SEMANTIC_MODEL_HANDLER = "nexcore.tool.uml.manager.semanticModelHandler"; //$NON-NLS-1$

    /** 노테이션 모델 핸들러 확장점 */
    public static final String NEXCORE_TOOL_UML_UI_PROJECT_NOTATION_MODEL_HANDLER = "nexcore.tool.uml.manager.notationModelHandler"; //$NON-NLS-1$

    /** MDA Developer Rule Editor ID */
    public static final String DEVELOPER_RULE_EDITOR_ID = "nexcore.tool.mda.developer.ui.developeFile"; //$NON-NLS-1$

    /** MDA Developer Reverse Rule Editor ID */
    public static final String DEVELOPER_REVERSE_RULE_EDITOR_ID = "nexcore.tool.mda.developer.reverse.ui.reverseFile"; //$NON-NLS-1$
    
    /** MDA Designer Rule Editor ID */
    public static final String DESIGNER_RULE_EDITOR_ID = "nexcore.tool.mda.core.transferFileEditor"; //$NON-NLS-1$

    /** MDA Developer rule file extension (dvt) */
    public static final String DEVELOPER_FILE_EXTENSION = "dvt"; //$NON-NLS-1$
    
    /** MDA Developer Reverse rule file extension (dvr) */
    public static final String DEVELOPER_REVERSE_FILE_EXTENSION = "dvr"; //$NON-NLS-1$

    /** MDA Designer rule file extension (dst) */
    public static final String DESIGNER_FILE_EXTENSION = "dst"; //$NON-NLS-1$

    /** String */
    public static final String STRING_LITERAL = "String"; //$NON-NLS-1$

    /** OPTION_IF */
    public final static String OPTION_IF = "OPTION_IF";

    /** ALTERNATIVE_IFELSE */
    public final static String ALTERNATIVE_IFELSE = "ALTERNATIVE_IFELSE";

    /** ALTERNATIVE_SWITCH */
    public final static String ALTERNATIVE_SWITCH = "ALTERNATIVE_SWITCH";

    /** LOOP_WHILE */
    public final static String LOOP_WHILE = "LOOP_WHILE";

    /** LOOP_FOR */
    public final static String LOOP_FOR = "LOOP_FOR";

    /** x, y가드의 위치 */
    public static final int GUARD_LOCATION = 10;

    /** CombinedFragment의 라벨 높이 */
    public static final int FRAGMENT_LABEL_HEIGHT = 20;

    /** 가드의 높이 */
    public static final int GUARD_HEIGHT = 20;

    /** 오퍼랜드의 최소 높이 */
    public static final int OPERAND_MIN_HEIGHT = 40;

    /** Inner combinedfragment의 부모와의 마진 넓이 */
    public static final int INNER_MARGIN = 10;

    /** 메시지 이름의 마진 높이 */
    public static final int MESSAGENAME_MARGIN = 20;

    /** UML Command 실행단위 확장점 */
    public static final String NEXCORE_TOOL_UML_COMMAND_EXECUTION_UNIT = "nexcore.tool.uml.command.execution.unit"; //$NON-NLS-1$

    /** 확장점 - 요소 우선순위 */
    public static final String EXTENSION_ELEMENT_PRIORITY = "priority"; //$NON-NLS-1$

    /** 우선순위 하 */
    public static final String PRIORITY_LOW = "low"; //$NON-NLS-1$

    /** 우선순위 중 */
    public static final String PRIORITY_NORMAL = "normal"; //$NON-NLS-1$

    /** 우선순위 상 */
    public static final String PRIORITY_HIGH = "high"; //$NON-NLS-1$

    /** UML 용어 변환 Command Id */
    public static final String NEXCORE_TOOL_UML_GLOSSARY_TRANSLATION_COMMAND = "nexcore.tool.uml.glossary.translation.command"; //$NON-NLS-1$

    /** 실행결과 표시시점(안함) : none */
    public static final String EXECUTION_RESULT_DISPLAY_TIME_TYPE_NONE = "none"; //$NON-NLS-1$;

    /** 실행결과 표시시점(즉시) : immediately */
    public static final String EXECUTION_RESULT_DISPLAY_TIME_TYPE_IMMEDIATELY = "immediately"; //$NON-NLS-1$;

    /** 실행결과 표시시점(마지막) : last */
    public static final String EXECUTION_RESULT_DISPLAY_TIME_TYPE_LAST = "last"; //$NON-NLS-1$;

    /** RSA 모델 확장자들 */
    public static final String[] RSA_MODEL_FILE_EXTENSIONS = { "*.emx" }; //$NON-NLS-1$

    /** RSA 프로파일 확장자들 */
    public static final String[] RSA_PROFILE_FILE_EXTENSIONS = { "*.epx" }; //$NON-NLS-1$

    /** RSA 모델 확장자 */
    public static final String RSA_MODEL_FILE_EXTENSION = "emx"; //$NON-NLS-1$

    /** RSA 프로파일 확장자 */
    public static final String RSA_PROFILE_FILE_EXTENSION = "epx"; //$NON-NLS-1$

    /** 제외시킬 RSA Authority */
    public static final String RSA_PROFILE_EXCEPTION_AUTHORIRY = "UML2_MSL_PROFILES"; //$NON-NLS-1$

    /** RSA 다이어그램 Annotation 소스명 : uml2.diagrams */
    public static final String RSA_DIAGRAM_ANNOTATION_SOURCE_NAME = "uml2.diagrams"; //$NON-NLS-1$

    /** RSA 다이어그램 종류 : Sequence */
    public static final String RSA_DIAGRAM_TYPE__SEQUENCE = "Sequence"; //$NON-NLS-1$

    /** RSA 다이어그램 종류 : Usecase */
    public static final String RSA_DIAGRAM_TYPE__USECASE = "Usecase"; //$NON-NLS-1$

    /** RSA 다이어그램 종류 : Class */
    public static final String RSA_DIAGRAM_TYPE__CLASS = "Class"; //$NON-NLS-1$

    /** RSA 다이어그램 종류 : Component */
    public static final String RSA_DIAGRAM_TYPE__COMPONENT = "Component"; //$NON-NLS-1$

    /** RSA 다이어그램 종류 : Activity */
    public static final String RSA_DIAGRAM_TYPE__ACTIVITY = "Activity"; //$NON-NLS-1$

    /** 컴포넌트 스테레오타입명 : comp */
    public static final String COMPONENT_STEREOTYPE_NAME = "comp"; //$NON-NLS-1$

    /** 단위업무 스테레오타입명 : bizunit */
    public static final String BIZUNIT_STEREOTYPE_NAME = "bizunit"; //$NON-NLS-1$

    /** 메소드 스테레오타입명 : method */
    public static final String METHOD_STEREOTYPE_NAME = "method"; //$NON-NLS-1$
    
    /** 엑셀 파일로 IO Import : Excel 확장자 */
    public static final String EXCEL_IO_IMPORT_BIZ = "biz"; //$NON-NLS-1$
    
    /** 엑셀 파일로 IO Import : 인터페이스 정의서 */
    public static final String EXCEL_IO_INTERFACE_DEFINITION = "인터페이스 정의서"; 
    
    /** 엑셀 파일로 IO Import : Excel 확장자 */
    public static final String EXCEL_IO_IMPORT_XLS = "*.xls"; //$NON-NLS-1$
     
    /** 엑셀 파일로 IO Import : 컴포넌트ID */
    public static final String EXCEL_IO_IMPORT_COMPONENT_ID = "컴포넌트ID"; 
     
    /** 엑셀 파일로 IO Import : 메소드/전문 Layout ID */
    public static final String EXCEL_IO_IMPORT_METHOD_ID = "메소드/전문 Layout ID"; 
     
    /** 엑셀 파일로 IO Import : Seq */
    public static final String EXCEL_IO_IMPORT_SEQ = "Seq"; //$NON-NLS-1$
     
    /** 엑셀 파일로 IO Import : IO구분 */
    public static final String EXCEL_IO_IMPORT_IO_IDX = "IO구분"; 
     
    /** 엑셀 파일로 IO Import : 구간구분 */
    public static final String EXCEL_IO_IMPORT_RECORD_ENG = "구간구분"; 
     
    /** 엑셀 파일로 IO Import : RecordSet 명 (한글) */
    public static final String EXCEL_IO_IMPORT_RECORD_KOR = "RecordSet 명 (한글)"; 
     
    /** 엑셀 파일로 IO Import : 구간반복횟수 */
    public static final String EXCEL_IO_IMPORT_MULTIPLICITY = "구간반복횟수"; 
     
    /** 엑셀 파일로 IO Import : Field ID (영문) */
    public static final String EXCEL_IO_IMPORT_FIELD_ENG = "Field ID (영문)"; 
     
    /** 엑셀 파일로 IO Import : Field 명 (한글) */
    public static final String EXCEL_IO_IMPORT_FIELD_KOR = "Field 명 (한글)"; 
     
    /** 엑셀 파일로 IO Import : Type */
    public static final String EXCEL_IO_IMPORT_TYPE = "Type"; //$NON-NLS-1$
     
    /** 엑셀 파일로 IO Import : 길이 */
    public static final String EXCEL_IO_IMPORT_LENGTH = "길이"; 
     
    /** 엑셀 파일로 IO Import : 처리 방법 */
    public static final String EXCEL_IO_IMPORT_DATA = "처리 방법"; 
     
    /** 엑셀 파일로 IO Import : 암호화 */
    public static final String EXCEL_IO_IMPORT_ENCRYPT = "암호화"; 
     
    /** 엑셀 파일로 IO Import : io */
    public static final String EXCEL_IO_IMPORT_IO = "io"; //$NON-NLS-1$
     
    /** 엑셀 파일로 IO Import : Req */
    public static final String EXCEL_IO_IMPORT_REQ = "Req"; //$NON-NLS-1$
     
    /** 엑셀 파일로 IO Import : Res */
    public static final String EXCEL_IO_IMPORT_RES = "Res"; //$NON-NLS-1$
     
    /** 엑셀 파일로 IO Import : In */
    public static final String EXCEL_IO_IMPORT_IN = "In"; //$NON-NLS-1$
     
    /** 엑셀 파일로 IO Import : Out */
    public static final String EXCEL_IO_IMPORT_OUT = "Out"; //$NON-NLS-1$
     
    /** 엑셀 파일로 IO Import : I */
    public static final String EXCEL_IO_IMPORT_I = "I"; //$NON-NLS-1$
     
    /** 엑셀 파일로 IO Import : O */
    public static final String EXCEL_IO_IMPORT_O = "O"; //$NON-NLS-1$
     
    /** 엑셀 파일로 IO Import : ref */
    public static final String EXCEL_IO_IMPORT_REF = "ref"; //$NON-NLS-1$
     
    /** 엑셀 파일로 IO Import : koName */
    public static final String EXCEL_IO_IMPORT_KONAME = "koName"; //$NON-NLS-1$
     
    /** 엑셀 파일로 IO Import : enName */
    public static final String EXCEL_IO_IMPORT_ENNAME = "enName"; //$NON-NLS-1$
     
    /** 엑셀 파일로 IO Import : length */
    public static final String EXCEL_IO_IMPORT_LENGTH_ENG = "length"; //$NON-NLS-1$
     
    /** 엑셀 파일로 IO Import : data */
    public static final String EXCEL_IO_IMPORT_DATA_ENG = "data"; //$NON-NLS-1$
     
    /** 엑셀 파일로 IO Import : dataType */
    public static final String EXCEL_IO_IMPORT_DATA_TYPE = "dataType"; //$NON-NLS-1$
    
    /** 엑셀 파일로 IO Import : encrypted */
    public static final String EXCEL_IO_IMPORT_ENCRYPT_ENG = "encrypted"; //$NON-NLS-1$
    
    /** 엑셀 파일로 IO Import : int */
    public static final String EXCEL_IO_IMPORT_TYPE_INT = "int"; //$NON-NLS-1$
    
    /** 엑셀 파일로 IO Import : int16 */
    public static final String EXCEL_IO_IMPORT_TYPE_INT16 = "int16"; //$NON-NLS-1$
    
    /** 엑셀 파일로 IO Import : integer */
    public static final String EXCEL_IO_IMPORT_TYPE_INTEGER = "integer"; //$NON-NLS-1$
    
    /** 엑셀 파일로 IO Import : int16 */
    public static final String EXCEL_IO_IMPORT_TYPE_DECIMAL = "decimal"; //$NON-NLS-1$
    
    /** 엑셀 파일로 IO Import : integer */
    public static final String EXCEL_IO_IMPORT_TYPE_BIG_DECIMAL = "BigDecimal"; //$NON-NLS-1$
    
    /** 엑셀 파일로 IO Import : int16 */
    public static final String EXCEL_IO_IMPORT_TYPE_BYTE_ARRAY = "byteArray"; //$NON-NLS-1$
    
    /** 엑셀 파일로 IO Import : integer */
    public static final String EXCEL_IO_IMPORT_TYPE_BINARY = "binary"; //$NON-NLS-1$
    
    /** 소문자 */
    public static final String LOWER_CASE = "LowerCase"; //$NON-NLS-1$;
    /** 대문자 */
    public static final String UPPER_CASE = "UpperCase"; //$NON-NLS-1$;
     
    /** Analysis Class */
    public static final String ANALYSIS_CLASS_FOLDER = "ANALYSIS_CLASS_FOLDER";
    
    /** Design Class */
    public static final String DESIGN_CLASS_FOLDER = "DESIGN_CLASS_FOLDER";
    
    /** UseCase */
    public static final String USECASE_FOLDER = "USECASE_FOLDER";
    
    /** Analysis Class */
    public static final String ANALYSIS_CLASS = "ANALYSIS_CLASSR";
    
    /** Design Class */
    public static final String DESIGN_CLASS = "DESIGN_CLASS";
    
    /** UseCase */
    public static final String USECASE = "USECASE";
    
    /** Analysis Class */
    public static final String ANALYSIS_CLASS_NAME = "Analysis Class";
    
    /** Design Class */
    public static final String DESIGN_CLASS_NAME = "Design Class";
    
    /** UseCase */
    public static final String USECASE_NAME = "UseCase";
    
    /**
     * PROJECT
     */
    public static final String PROJECT = "PROJECT";
}
