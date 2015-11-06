/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.manager.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.manager.utility.UMLModelerNotationModelHandlerUtil;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.DiagramType;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.query.conditions.eobjects.EObjectCondition;
import org.eclipse.emf.query.conditions.eobjects.EObjectTypeRelationCondition;
import org.eclipse.emf.query.statements.FROM;
import org.eclipse.emf.query.statements.IQueryResult;
import org.eclipse.emf.query.statements.SELECT;
import org.eclipse.emf.query.statements.WHERE;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;


/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.manager</li>
 * <li>서브 업무명 : nexcore.tool.uml.manager.util</li>
 * <li>설  명 : ModelManager</li>
 * <li>작성일 : 2010. 5. 11.</li>
 * <li>작성자 : 신동명 </li>
 * </ul>
 */
public class ModelManager {

    /** 설계모델 컬레보레이션 맵 */
    private Map<String, Collaboration> analysisCollaborationMap = null;

    /** 설계모델 클래스 맵 */
    private Map<String, List<String>> analysisClassMap = null;

    /** 분석모델 컬레보레이션 맵 */
    private Map<String, Collaboration> designCollaborationMap = null;

    /** 분석모델 클래스 맵 */
    private Map<String, List<String>> designClassMap = null;

    /** 제외 클래스 맵 */
    private Map<String, Object> exceptClassMap = null;

    /** 모델선택 다이얼로그에서 선택한 분석 모델 */
    private Model selectedAnalysisModel;

    /** 모델선택 다이얼로그에서 선택한 설계 모델 */
    private Model selectedDesignModel;

    /** 부모 쉘 */
    private Shell parentShell;

    /** 진행 모니터 */
    private IProgressMonitor monitor;

    /**
     * 생성자
     */
    public ModelManager() {
        analysisCollaborationMap = new HashMap<String, Collaboration>();
        analysisClassMap = new HashMap<String, List<String>>();
        designCollaborationMap = new HashMap<String, Collaboration>();
        designClassMap = new HashMap<String, List<String>>();
        exceptClassMap = new HashMap<String, Object>();
    }

    /**
     * 생성자
     * 
     * @param selectedAnalysisModel
     * @param selectedDesignModel
     */
    public ModelManager(Model selectedAnalysisModel, Model selectedDesignModel) {
        this();

        this.selectedAnalysisModel = selectedAnalysisModel;
        this.selectedDesignModel = selectedDesignModel;
    }

    /**
     * 생성자
     * 
     * @param selectedAnalysisModel
     * @param selectedDesignModel
     * @param parentShell
     * @param monitor
     */
    public ModelManager(Model selectedAnalysisModel, Model selectedDesignModel, Shell parentShell,
    IProgressMonitor monitor) {
        this(selectedAnalysisModel, selectedDesignModel);

        this.parentShell = parentShell;
        this.monitor = monitor;
    }

    /**
     * Simple Class List 메인 호출 함수
     * 
     * bComponentScope 가 true 인 경우에는 component부분의 유스케이스 bComponentScope 가 false
     * 인 경우에는 component부분을 제외한 유스케이스
     * 
     * @param model
     * @param bComponentScope
     * @return boolean
     */
    public boolean getUsecaseTraceInfo(Model model, boolean bComponentScope) {
        // 분석모델 가져오기
        // Model analysisModel = getAnalysisModelInfo(model);
        /**
         * Modified by yschoi. 20100615 현상 : 전개될 모델명을 변환규칙에 정의된 기본 이름을 쓰는 대신에
         * 사용자에게 입력받는 방식으로 변경함에 따라, 해당하는 이름의 분석모델을 찾지 못하는 문제 발생 해결 : 사용자에게 분석모델과
         * 설계모델을 선택하게 해서 그 정보를 생성자에서 설정하도록 변경하고 생성자에서 넘겨 받은 분석모델로 설정되도록 수정
         */
        Model analysisModel = selectedAnalysisModel;
        if (analysisModel == null) {
            MessageDialog.openInformation(parentShell,
                UMLMessage.LABEL_NOTIFICATION,
                UMLMessage.getMessage(UMLMessage.MESSAGE_ERROR_CANT_FIND, UMLMessage.LABEL_ANALYSIS_MODEL));

            return false;
        }

        // 설계모델 가져오기
        // Model designModel = getDesignModelInfo(model);
        /**
         * Modified by yschoi. 20100615 현상 : 전개될 모델명을 변환규칙에 정의된 기본 이름을 쓰는 대신에
         * 사용자에게 입력받는 방식으로 변경함에 따라, 해당하는 이름의 설계모델을 찾지 못하는 문제 발생 해결 : 사용자에게 분석모델과
         * 설계모델을 선택하게 해서 그 정보를 생성자에서 설정하도록 변경하고 생성자에서 넘겨 받은 설계모델로 설정되도록 수정
         */
        Model designModel = selectedDesignModel;
        if (designModel == null) {
            MessageDialog.openInformation(parentShell,
                UMLMessage.LABEL_NOTIFICATION,
                UMLMessage.getMessage(UMLMessage.MESSAGE_ERROR_CANT_FIND, UMLMessage.LABEL_DESIGN_MODEL));

            return false;
        }

        // 제외 클래스 인덱스맵 생성
        org.eclipse.uml2.uml.Package nexcorePkg = ModelUtility.getPackageByName(designModel, "nexcore"); //$NON-NLS-1$
        if (nexcorePkg != null) {
            EList<Element> elmtList = nexcorePkg.allOwnedElements();
            Object obj = null;
            String keyName = null;

            for (Iterator<Element> itr = elmtList.iterator(); itr.hasNext();) {
                obj = itr.next();

                if (obj instanceof Class) {
                    keyName = ((Class) obj).getName();
                } else if (obj instanceof Interface) {
                    keyName = ((Interface) obj).getName();
                } else {
                    keyName = null;
                }

                if (keyName != null && !exceptClassMap.containsKey(keyName)) {
                    exceptClassMap.put(keyName, null);
                }
            }
        }

        // 클래스 다이어그램이 존재하는 지 여부
        boolean existClassDiagram = false;

        CreateDiagramList readSeq = null;
        List<String> resultList = null;

        // 분석모델 클래스 다이어그램 생성 ( 없을 경우 생성 )
        List<EObject> elements = new ArrayList<EObject>();
        elements.add(analysisModel);

        // 분석모델 유스케이스(Collaboration) 리스트.
        List<Element> anlCollaboList = ModelUtility.getCollaborationList(analysisModel);

        /**
         * Modified by yschoi. 20100615 현상 : 분석모델 클래스 다이어그램을 무조건 생성시키는 문제 해결 :
         * 컬레보레이션 목록을 먼저 가져와서 컬레보레이션 내부에 'Required Classes' 패키지가 있을 경우엔 클래스
         * 다이어그램 생성 안하도록 변경
         */
        for (Element collaboration : anlCollaboList) {
            existClassDiagram = ClassDiagramUtil.isAlreadyExistClassDiagram((Collaboration) collaboration);

            if (existClassDiagram) {
                break;
            }
        }

        if (!existClassDiagram) {
            // readSeq = new CreateDiagramList();
            readSeq = new CreateDiagramList(monitor);
            resultList = readSeq.createVOPCs(elements);

            if (resultList == null) {
                MessageDialog.openInformation(parentShell,
                    UMLMessage.LABEL_NOTIFICATION,
                    UMLMessage.getMessage(UMLMessage.MESSAGE_ERROR_CANT_CREATE_CLASS_DIAGRAM_FOR,
                        UMLMessage.LABEL_ANALYSIS_MODEL));

                return false;
            }
        }

        // 설계모델 클래스 다이어그램 생성 (없을 경우 생성)
        elements = new ArrayList<EObject>();
        elements.add(designModel);

        // 설계모델 유스케이스(Collaboration) 가져오기
        List<Element> dsnCollaboList = ModelUtility.getCollaborationList(designModel);

        /**
         * Modified by yschoi. 20100615 현상 : 설계모델 클래스 다이어그램을 무조건 생성시키는 문제 해결 :
         * 컬레보레이션 목록을 먼저 가져와서 컬레보레이션 내부에 'Required Classes' 패키지가 있을 경우엔 클래스
         * 다이어그램 생성 안하도록 변경
         */
        for (Element collaboration : dsnCollaboList) {
            existClassDiagram = ClassDiagramUtil.isAlreadyExistClassDiagram((Collaboration) collaboration);

            if (existClassDiagram) {
                break;
            }
        }

        if (!existClassDiagram) {
            // readSeq = new CreateDiagramList();
            readSeq = new CreateDiagramList(monitor);
            resultList = readSeq.createVOPCs(elements);

            if (resultList == null) {
                MessageDialog.openInformation(parentShell,
                    UMLMessage.LABEL_NOTIFICATION,
                    UMLMessage.getMessage(UMLMessage.MESSAGE_ERROR_CANT_CREATE_CLASS_DIAGRAM_FOR,
                        UMLMessage.LABEL_DESIGN_MODEL));

                return false;
            }
        }

        // 분석모델 클래스 리스트 설정
        Collaboration collabo = null;
        boolean isComponentBranch = false;
        // NamedElement nearstComponent = null;
        String hrchName = null;
        List<String> classList = null;
        for (Iterator<Element> itr = anlCollaboList.iterator(); itr.hasNext();) {
            collabo = (Collaboration) itr.next();
            isComponentBranch = ModelUtility.isComponentBranch(collabo.getNearestPackage());

            if (isComponentBranch == bComponentScope) {
                hrchName = ModelUtility.getHierarchyName(collabo);
                analysisCollaborationMap.put(hrchName, collabo);
                classList = getClassList(collabo, isComponentBranch);
                analysisClassMap.put(hrchName, classList);
            }
        }

        // 설계모델 클래스 리스트.
        for (Iterator<Element> itr = dsnCollaboList.iterator(); itr.hasNext();) {
            collabo = (Collaboration) itr.next();
            isComponentBranch = ModelUtility.isComponentBranch(collabo.getNearestPackage());

            if (isComponentBranch == bComponentScope) {
                hrchName = ModelUtility.getHierarchyName(collabo);
                // designCollaborationMap.put(hrchName, collabo);
                classList = getClassList(collabo, isComponentBranch);
                designClassMap.put(hrchName, classList);
            }
        }

        // 출력형태 테스트(패키지명/유스케이스명/실현 클래스 목록(분석)/실현 클래스 목록(설계))
        Set<String> keyset = analysisCollaborationMap.keySet();
        Collaboration anlCollabo = null;
        // Collaboration dsnCollabo = null;
        Component comp = null;
        String collaboName = null;
        String pkgName = null;
        String usecaseName = null;
        String anlClassList = null;
        String dsnClassList = null;

//        // ExcelManager excelMngr = new ExcelManager();
//        ExcelManager excelMngr = new ExcelManager(parentShell);
//
//        // boolean bWrite = excelMngr.beginWrite(null,null);
//        boolean bWrite = excelMngr.beginWrite(null, UMLMessage.LABEL_USECASE_TRACE_MATRIX_DEFAULT_FILE_NAME);
//        if (!bWrite) {
//            return false;
//        }
//
//        Color color = new Color().GRAY;
//
//        // 타이틀 입력
//        excelMngr.addColumn(0, 0, UMLMessage.LABEL_USECASE_TRACE_MATRIX_PACKAGE_TITLE);
//        excelMngr.setBackground(0, 0, 0, color);
//        excelMngr.setColWidth(0, 0, 50);
//
//        if (bComponentScope) {
//            excelMngr.addColumn(1, 0, UMLMessage.LABEL_USECASE_TRACE_MATRIX_COMPONENT_TITLE);
//        } else {
//            excelMngr.addColumn(1, 0, UMLMessage.LABEL_USECASE_TRACE_MATRIX_USECASE_TITLE);
//        }
//        excelMngr.setBackground(0, 1, 0, color);
//        excelMngr.setColWidth(0, 1, 25);
//
//        excelMngr.addColumn(2, 0, UMLMessage.LABEL_USECASE_TRACE_MATRIX_ANALYSIS_MODEL_CLASS_LIST_TITLE);
//        excelMngr.setBackground(0, 2, 0, color);
//        excelMngr.setColWidth(0, 2, 35);
//        excelMngr.addColumn(3, 0, UMLMessage.LABEL_USECASE_TRACE_MATRIX_DESIGN_MODEL_CLASS_LIST_TITLE);
//        excelMngr.setBackground(0, 3, 0, color);
//        excelMngr.setColWidth(0, 3, 70);
//
//        int rowNo = 1;
//        for (Iterator<String> itr = keyset.iterator(); itr.hasNext();) {
//            collaboName = (String) itr.next();
//            anlCollabo = (Collaboration) analysisCollaborationMap.get(collaboName);
//            // dsnCollabo = (Collaboration)
//            // designCollaborationMap.get(collaboName);
//
//            // 콤포넌트 목록을 가져오는 경우
//            if (bComponentScope) {
//                comp = (Component) ModelUtility.getNearstComponent(anlCollabo.getNearestPackage());
//                pkgName = ModelUtility.getFullPackageName(comp.getNearestPackage());
//                usecaseName = comp.getName();
//            } else {
//                pkgName = ModelUtility.getFullPackageName(anlCollabo.getNearestPackage());
//                usecaseName = ModelUtility.extractNameFromHierarchyName(collaboName);
//            }
//
//            monitor.subTask(UMLMessage.getMessage(UMLMessage.LABEL_ING, UMLMessage.LABEL_USECASE_TRACE_MATRIX_CREATION)
//                + pkgName);
//
//            anlClassList = ModelUtility.listToString((List<String>) analysisClassMap.get(collaboName), ",\n"); //$NON-NLS-1$
//            dsnClassList = ModelUtility.listToString((List<String>) designClassMap.get(collaboName), ",\n"); //$NON-NLS-1$
//
//            excelMngr.addColumn(0, rowNo, pkgName);
//            excelMngr.addColumn(1, rowNo, usecaseName);
//            excelMngr.addColumn(2, rowNo, anlClassList);
//            excelMngr.addColumn(3, rowNo, dsnClassList);
//
//            Log.info(pkgName + ManagerConstant.SHARP + usecaseName + ManagerConstant.SHARP + anlClassList
//                + ManagerConstant.SHARP + dsnClassList);
//            rowNo++;
//
//            monitor.worked(1);
//        }
//
//        try {
//            excelMngr.endWrite();
//        } catch (Exception e) {
//            ALMLogger.getLog(UMLEditorPlugin.PLUGIN_ID).error(e.getMessage(), e);
//            return false;
//        }

        return true;
    }

    /**
     * 모델파일의 URI 반환
     * 
     * @param model
     * @param modelType
     * @return URI
     */
    /*
     * protected URI getModelUri(Model model, int modelType) { String
     * modelFileName = null; String modelName = null;
     * 
     * // 분석 모델인 경우 if (modelType == ModelConstant.ANALYSIS_MODEL_TYPE) {
     * modelFileName = ModelPropertyManager.getInstance().getModelFileNameOfAM()
     * + MDACoreConstant.DOT + MDACoreConstant.UML_MODELER_FILE_EXTENSION;
     * modelName = ModelPropertyManager.getInstance().getModelNameOfAM(); } //
     * 설계 모델인 경우 else if (modelType == ModelConstant.DESIGN_MODEL_TYPE) {
     * modelFileName = ModelPropertyManager.getInstance().getModelFileNameOfDM()
     * + MDACoreConstant.DOT + MDACoreConstant.UML_MODELER_FILE_EXTENSION;
     * modelName = ModelPropertyManager.getInstance().getModelNameOfDM(); }
     * 
     * URI modelUri = null; String newModelFile = null; String modelFilePath =
     * null;
     * 
     * // 모델파일 생성경로를 현재 디렉토리(.)로 설정 한 경우
     * if(ModelPropertyManager.getInstance().getMdaModelLocation
     * ().replaceAll(MDACoreConstant.BLANK_STRING,
     * MDACoreConstant.EMPTY_STRING).equals(MDACoreConstant.DOT)) { URI
     * ucModelUri = model.eResource().getURI();
     * 
     * // ucModelUriStr : platform:/resource/RoseModel_EJB/EJB_AnalysisModel.emx
     * // String ucModelUriStr = ucModelUri.toString(); String ucModelUriStr =
     * ucModelUri.toPlatformString(true);
     * 
     * String[] ucModelUriStrArray = ucModelUriStr.split(MDACoreConstant.SLASH);
     * 
     * // modelPathStr : platform:/resource/RoseModel_EJB/ String modelPathStr =
     * ucModelUriStr.replaceAll(ucModelUriStrArray[ucModelUriStrArray.length -
     * 1], MDACoreConstant.EMPTY_STRING);
     * 
     * // modelUri : platform:/resource/RoseModel_EJB/DesignModel.emx modelUri =
     * URI.createURI(modelPathStr + modelFileName);
     * 
     * // modelFilePath : D:/workspace/RoseModel_EJB/ modelFilePath =
     * CreateModel.getModelFilePath(ucModelUriStr) + MDACoreConstant.SLASH;
     * 
     * // newModelFile : D:/workspace/RoseModel_EJB/DesignModel.emx //
     * newModelFile = modelFilePath + modelFileName; newModelFile =
     * modelFilePath + MDAMessage.LABEL_MODEL + MDACoreConstant.SLASH +
     * modelFileName; } else { modelFilePath =
     * ModelPropertyManager.getInstance().getMdaModelLocation();
     * 
     * if(!modelFilePath.endsWith(MDACoreConstant.SLASH)){ modelFilePath =
     * modelFilePath + MDACoreConstant.SLASH; } newModelFile = modelFilePath +
     * modelFileName;
     * 
     * // modelUri : file:/D:/workspace/RoseModel_EJB/DesignModel.emx modelUri =
     * URI.createFileURI(newModelFile);
     * 
     * }
     * 
     * // 실제 모델파일 존재여부 확인 File mFile = new File(newModelFile);
     * 
     * // 모델파일이 존재하지 않는 경우 if (!mFile.exists()) { return null; } else { return
     * modelUri; } }
     */

    /**
     * 환경파일에 정의되어 있는 정보로 분석모델파일을 가져온다.
     * 
     * @return Model
     */
    /*
     * protected Model getAnalysisModelInfo(Model model) { URI modelUri =
     * getModelUri(model, ModelConstant.ANALYSIS_MODEL_TYPE);
     * 
     * if(modelUri == null) { return null; }
     * 
     * return CreateModel.openModel(modelUri); }
     * 
     * /** 환경파일에 정의되어 있는 정보로 설계모델파일을 가져온다.
     * 
     * @return Model
     */
    /*
     * protected Model getDesignModelInfo(Model model) { URI modelUri =
     * getModelUri(model, ModelConstant.DESIGN_MODEL_TYPE);
     * 
     * if(modelUri == null) { return null; }
     * 
     * return CreateModel.openModel(modelUri); }
     */

    /**
     * 분석모델 클래스 다이어그램 생성
     * 
     * @return boolean
     */
    protected boolean createAnalysisClassDiagram() {
        return false;
    }

    /**
     * 설계모델 클래스 다이어그램 생성
     * 
     * @return boolean
     */
    protected boolean createDesignClassDiagram() {
        return false;
    }

    /**
     * 분석모델 유스케이스(Collaboration) 리스트 반환
     * 
     * @return Map<String,Collaboration>
     */
    protected Map<String, Collaboration> getAnalysisUsecaseList() {
        return analysisCollaborationMap;
    }

    /**
     * 설계모델 유스케이스(Collaboration) 리스트 반환
     * 
     * @return Map<String,Collaboration>
     */
    protected Map<String, Collaboration> getDesignUsecaseList() {
        return designCollaborationMap;
    }

    /**
     * 클래스 리스트 반환
     * 
     * @param collabo
     * @param isComponentBranch
     * @return List<String>
     */
    protected List<String> getClassList(Collaboration collabo, boolean isComponentBranch) {
        List<String> resultList = null;

        List<Diagram> diagramList = null;

        // Component인 경우에 Collaboration상단에서 클래스다이어그램을 찾는다.
        if (isComponentBranch) {
            // diagramList =
            // UMLModeler.getUMLDiagramHelper().getDiagrams(collabo.getNearestPackage(),
            // UMLDiagramKind.CLASS_LITERAL);
            diagramList = UMLModelerNotationModelHandlerUtil.getInstance().getDiagramList(collabo.getNearestPackage(),
                DiagramType.CLASS_DIAGRAM);
        } else {
            // diagramList =
            // UMLModeler.getUMLDiagramHelper().getDiagrams(collabo,
            // UMLDiagramKind.CLASS_LITERAL);
            diagramList = UMLModelerNotationModelHandlerUtil.getInstance().getDiagramList(collabo,
                DiagramType.CLASS_DIAGRAM);
        }

        // List diagramList =
        // UMLModeler.getUMLDiagramHelper().getDiagrams(collabo,
        // UMLDiagramKind.CLASS_LITERAL);

        if (diagramList != null) {
            resultList = new ArrayList<String>();

            Diagram diagram = null;

            List<AbstractNode> nodeList = null;
            AbstractNode node = null;
            Element umlElement = null;
            String className = null;

            for (Iterator<Diagram> diagramIterator = diagramList.iterator(); diagramIterator.hasNext();) {
                diagram = diagramIterator.next();

                // 클래스 다이어그램 명 비교(Required Classes)
                // if(diagram.getName().equals(ModelPropertyManager.getInstance().getClassDiagramName()))
                // {
                if (diagram.getName().equals(UMLMessage.LABEL_VOPC_DIAGRAM_NAME)) {
                    nodeList = diagram.getNodeList();

                    for (Iterator<AbstractNode> nodeIterator = nodeList.iterator(); nodeIterator.hasNext();) {
                        node = nodeIterator.next();

                        umlElement = node.getUmlModel();

                        if (umlElement != null) {
                            if (umlElement instanceof Class) {
                                className = ((Class) umlElement).getName();
                            } else if (umlElement instanceof Interface) {
                                className = ((Interface) umlElement).getName();
                            }

                            if (!exceptClassMap.containsKey(className)) {
                                resultList.add(className);
                            }
                        }
                    }
                }
            }
        }

        return resultList;
    }

    /**
     * Matrix for Entity and Class 메인 호출 함수
     * 
     * void
     */
    /*
     * public void getEntityClassListInfo(Model analysisModel) {
     * 
     * // Entity 클래스 데이터 맵을 구한다. HashMap entityMap =
     * getEntityClassList(analysisModel);
     * 
     * // DB테이블 정보 엑셀 데이터 읽기 (데이터를 읽으면서 Entity 클래스와 비교하며 엑셀 Matrix작성)
     * ExcelManager excelMngr = new ExcelManager();
     * //excelMngr.beginWrite("c:/aaa.xls"); boolean bRead =
     * excelMngr.beginRead(null,null); if(!bRead) { return; } Sheet columnsSheet
     * =excelMngr.getWorkBook().getSheet(MDAMessage.
     * ModelManager_LABEL_TABLEINFO_SHEETNAME
     * );//Messages.getString("ModelManager_LABEL_TABLEINFO_SHEETNAME")); int
     * colCnt = columnsSheet.getColumns();
     * 
     * // 테이블명 컬럼 위치 (TABLE_NAME) int tableNameColIdx = -1; // 컬럼명 컬럼 위치
     * (COLUMN_NAME) int columnNameColIdx = -1;
     * 
     * Cell tempCell = null; String cellContent = null; for(int i=0; i<colCnt;
     * i++) { tempCell = columnsSheet.getCell(i, 0); cellContent =
     * tempCell.getContents();
     * 
     * if(cellContent != null &&
     * cellContent.equals(MDAMessage.ModelManager_LABEL_TABLEINFO_TABLENAME_TITLE
     * )){//Messages.getString("ModelManager_LABEL_TABLEINFO_TABLENAME_TITLE")))
     * { tableNameColIdx = i; } else if(cellContent != null &&
     * cellContent.equals
     * (MDAMessage.ModelManager_LABEL_TABLEINFO_COLUMNNAME_TITLE
     * )){//Messages.getString
     * ("ModelManager_LABEL_TABLEINFO_COLUMNNAME_TITLE"))) { columnNameColIdx =
     * i; } }
     * 
     * Cell[] cellArray = null; String tableName = null; String columnName =
     * null; HashMap tableMap = new HashMap(); StringBuffer columnCheckBuf =
     * null;
     * 
     * for(int j=1; j<columnsSheet.getRows(); j++) { cellArray =
     * columnsSheet.getRow(j);
     * 
     * if(cellArray.length > tableNameColIdx) { tableName =
     * (cellArray[tableNameColIdx]).getContents(); } else { tableName = null; }
     * 
     * if(cellArray.length > columnNameColIdx) { columnName =
     * (cellArray[columnNameColIdx]).getContents(); } else { columnName = null;
     * }
     * 
     * if(tableName != null && !tableMap.containsKey(tableName)) {
     * tableMap.put(tableName, new StringBuffer()); }
     * 
     * if(columnName != null && tableMap.containsKey(tableName)) {
     * columnCheckBuf = (StringBuffer)tableMap.get(tableName);
     * if(columnCheckBuf.length() > 0) {
     * columnCheckBuf.append(MDACoreConstant.SHARP); }
     * columnCheckBuf.append(columnName); }
     * 
     * }
     * 
     * excelMngr.endRead();
     * 
     * boolean bEntitySamename =
     * ModelPropertyManager.getInstance().getPrefMatrixEntitySamenameYn();
     * boolean bEntitySameattr =
     * ModelPropertyManager.getInstance().getPrefMatrixEntitySameattrYn();
     * 
     * // 엑셀 MATRIX 작성 excelMngr = new ExcelManager(); boolean bWrite =
     * excelMngr.beginWrite(null,null); if(!bWrite) { return; }
     * 
     * Set entitySet = entityMap.keySet(); Set tableSet = tableMap.keySet();
     * String entityName = null;
     * 
     * // X축 Entity 명 int x = 1; excelMngr.addColumn(0, 0,
     * MDAMessage.ModelManager_LABEL_TABLEENTITY_TITLE
     * );//Messages.getString("ModelManager_LABEL_TABLEENTITY_TITLE")); for
     * (Iterator itr = entitySet.iterator(); itr.hasNext();) { entityName =
     * (String)itr.next(); int y = 0;
     * 
     * // X축 헤더 작성 (Entity명) if(y == 0) { excelMngr.addColumn(x, y, entityName);
     * y++; }
     * 
     * // Y축 테이블 명 for (Iterator itr2 = tableSet.iterator(); itr2.hasNext();) {
     * tableName = (String)itr2.next();
     * 
     * // Y축 헤더 작성 (테이블명) if(x == 1) { excelMngr.addColumn(0, y, tableName); }
     * 
     * boolean bSameNameCheck = true; boolean bSameAttrCheck = true;
     * 
     * // Entity와 테이블명 비교 if(bEntitySamename) {
     * if(tableName.toUpperCase().indexOf(entityName.toUpperCase()) < 0) {
     * bSameNameCheck = false; } }
     * 
     * // Entity속성과 테이블 컬럼명 비교 if(bEntitySameattr) { bSameAttrCheck =
     * compareAttributeName((Vector)entityMap.get(entityName),
     * ((StringBuffer)tableMap.get(tableName)).toString()); }
     * 
     * if(bSameNameCheck && bSameAttrCheck) { excelMngr.addColumn(x, y,
     * MDAMessage.ModelManager_LABEL_TABLEENTITY_CHECK);//Messages.getString(
     * "ModelManager_LABEL_TABLEENTITY_CHECK")); } y++; } x++; }
     * 
     * excelMngr.endWrite(); }
     */

    /**
     * Entity의 속성명과 테이블의 컬럼 전체 문자열과 비교
     * 
     * @param attrVct
     * @param tableMap
     * @return boolean
     */
    public boolean compareAttributeName(Vector attrVct, String columnStr) {

        if (attrVct == null || columnStr == null) {
            return false;
        }

        String attrName = null;
        boolean result = true;

        for (int i = 0; i < attrVct.size(); i++) {
            attrName = (String) attrVct.get(i);
            if (columnStr.toUpperCase().indexOf(attrName.toUpperCase()) < 0) {
                return false;
            }
        }

        return result;
    }

    /**
     * namespace 내에 있는 diagramType이 같은 모든 다이어그램의 리스트를 리턴한다.
     * 
     * @param namespace
     * @param type
     *            null이면 모든 다이어그램타입으로 처리.
     * @return
     */
    public static List<Diagram> getAllDiagramList(Namespace namespace, DiagramType type) {
        List<Diagram> diagramList = new ArrayList<Diagram>();

        EObjectCondition condition = new EObjectTypeRelationCondition(EcorePackage.eINSTANCE.getEAnnotation());
        SELECT statement = new SELECT(new FROM(namespace), new WHERE(condition));
        IQueryResult result = statement.execute();

        if (result.getException() != null) {
            Log.error(result.getException());
            return diagramList;
        } else {
            Diagram diagram;
            EAnnotation annotation;
            for (EObject obj : result) {
                if (obj instanceof EAnnotation) {
                    annotation = (EAnnotation) obj;
                    if (ManagerConstant.UMLDOMAIN_CONSTANT__DIAGRAM_ANNOTATION_NAME.equals(annotation.getSource())) {
                        for (EObject eobj : annotation.getContents()) {
                            diagram = (Diagram) eobj;

                            if (type == null) {
                                diagramList.add(diagram);
                            } else if (type == diagram.getType()) {
                                diagramList.add(diagram);
                            }
                        }
                    }
                }
            }
        }
        return sortDiagramList(diagramList);

    }

    /**
     * diagramList를 정렬한다.
     * 
     * @param diagramList
     * @return
     */
    public static List<Diagram> sortDiagramList(List<Diagram> diagramList) {

        Comparator<Diagram> comparator = new Comparator<Diagram>() {
            public int compare(Diagram o1, Diagram o2) {
                String o1s = o1.getName();
                String o2s = o2.getName();

                return o1s.compareTo(o2s);
            }
        };

        Collections.sort(diagramList, comparator);
        return diagramList;

    }

    /**
     * NamedElementList를 정렬한다.
     * 
     * @param elementList
     * @return
     */
    public static List<NamedElement> sortNamedElementList(List<NamedElement> elementList) {

        Comparator<NamedElement> comparator = new Comparator<NamedElement>() {
            public int compare(NamedElement o1, NamedElement o2) {
                String o1s = o1.getName();
                String o2s = o2.getName();

                return o1s.compareTo(o2s);
            }
        };

        Collections.sort(elementList, comparator);
        return elementList;

    }
    
    /**
     * Entity 클래스 데이터 맵을 반환(Entity 클래스 데이터 맵에는 Attribute맵이 들어감)
     * 
     * @return HashMap
     */
    /*
     * public HashMap getEntityClassList(Model analysisModel) {
     * 
     * EList elmtList = analysisModel.allOwnedElements(); HashMap entityMap =
     * new HashMap(); Vector attributeVct = null; Object obj = null; Object obj2
     * = null; String keyName = null; Class classImpl = null; EList attrList =
     * null; Property attr = null; EList stList = null; Stereotype stereotype =
     * null; String attrName = null;
     * 
     * for(Iterator itr = elmtList.iterator(); itr.hasNext();) { obj =
     * itr.next();
     * 
     * if(obj instanceof Class) { classImpl = (Class)obj;
     * 
     * stList = classImpl.getAppliedStereotypes();
     * 
     * if(stList.size() > 0) { stereotype = (Stereotype)stList.get(0); } else {
     * stereotype = null; }
     * 
     * // 분석모델의 Entity 스테레오 타입인 경우 entityMap에 저장한다. if(stereotype != null &&
     * stereotype.getName().equals(ModelPropertyManager.getInstance().
     * getAnalysisEntityStereotype())) {
     * 
     * keyName = classImpl.getName(); attributeVct = new Vector();
     * 
     * attrList = classImpl.getOwnedAttributes();
     * 
     * for (Iterator itr2 = attrList.iterator(); itr2.hasNext();) { obj2 =
     * itr2.next();
     * 
     * if(obj2 != null && obj2 instanceof Property) { attr = (Property)obj2;
     * attrName = attr.getName();
     * if(!attrName.trim().equals(MDACoreConstant.EMPTY_STRING)) {
     * attributeVct.add(attrName); } } }
     * 
     * if(keyName != null) { entityMap.put(keyName, attributeVct); } } } }
     * return entityMap; }
     */
}
