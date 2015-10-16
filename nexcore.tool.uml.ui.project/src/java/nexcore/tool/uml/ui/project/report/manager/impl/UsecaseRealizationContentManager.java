/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.project.report.manager.impl;

import java.util.ArrayList;
import java.util.List;

import nexcore.alm.common.word.DataModel;
import nexcore.tool.mdd.core.util.MDDCommonUtil;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.DiagramType;
import nexcore.tool.uml.model.umldiagram.UMLDiagramPackage;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.project.report.manager.ReportContentManager;
import nexcore.tool.uml.ui.project.report.util.UMLManipulation;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.report.manager.impl</li>
 * <li>설  명 : UsecaseRealizationContentManager</li>
 * <li>작성일 : 2010. 11. 5.</li>
 * <li>작성자 : 오은주</li>
 * </ul>
 */
public class UsecaseRealizationContentManager extends ReportContentManager {

    /**
     * @see nexcore.tool.uml.ui.project.report.manager.ReportContentManager#createReportContent(nexcore.tool.core.word.DataModel,
     *      org.eclipse.uml2.uml.Package)
     */
    @Override
    public void createReportContent(DataModel targetModel, Package pkg) {

        List<Collaboration> collaborationList = UMLManipulation.getChildCollaborationList(pkg);

        if (0 == collaborationList.size()) {
            return;
        }

        List<DataModel> classDiagramModelList = new ArrayList<DataModel>();
        List<DataModel> sequenceDiagramModelList = new ArrayList<DataModel>();
        List<DataModel> classifierModelList = new ArrayList<DataModel>();
        List<DataModel> tempList;
        List<Classifier> classifierList = new ArrayList<Classifier>();
        List<Diagram> diagramList = new ArrayList<Diagram>();

        for (Collaboration collaboration : collaborationList) {
            setDataModel(targetModel, UICoreConstant.REPORT__USECASE_KEYWORD, getKeywords(collaboration));
            tempList = this.createClassdiagramModelList(collaboration, diagramList);
            if (0 != tempList.size()) {
                classDiagramModelList.addAll(tempList);
            }
        }
        if (0 != classDiagramModelList.size()) {
            setDataModel(targetModel, UICoreConstant.REPORT__CLASSDIAGRAM_LIST, classDiagramModelList);
        }

        for (Collaboration collaboration : collaborationList) {
            tempList = this.createSequencediagramModelList(collaboration);
            if (0 != tempList.size()) {
                sequenceDiagramModelList.addAll(tempList);
            }
        }
        if (0 != sequenceDiagramModelList.size()) {
            setDataModel(targetModel, UICoreConstant.REPORT__SEQUENCEDIAGRAM_LIST, sequenceDiagramModelList);
        }

        for (Diagram diagram : diagramList) {
            UMLManipulation.getClassList(diagram, classifierList);
        }
        ClassifierContentManager classifierContentManager = new ClassifierContentManager();
        DataModel classifierModel = null;
        for (Classifier classifier : classifierList) {
            classifierModel = classifierContentManager.createClassifierDataModel(classifier);
            if (null != classifierModel) {
                classifierModelList.add(classifierModel);

            }
        }

        if (0 != sequenceDiagramModelList.size()) {
            setDataModel(targetModel, UICoreConstant.REPORT__CLASS_LIST, classifierModelList);
        }
        // this.diagramList = new ArrayList<Diagram>();

        // 1.Usecase Realization Loop
        // 2. Class diagram Loop
        // 2.1 class Loop for table
        // 2.2 class Loop for detail
        // 3. Sequence diagram Loop

        // TODO
        // 1. 클래스 다이어그램
        // 2. 클래스 목록
        // 3. 클래스별 상세 명세
        // 4. 시퀀스 다이어그램

        //       
        // setDataModel(targetModel, UICoreConstant.REPORT__CLASSDIAGRAM_LIST,
        // createClassdiagramList(pkg));
        //            
        // setDataModel(targetModel, UICoreConstant.REPORT__CLASS_LIST,
        // createClassDataModelList());
        //       
        //
    }

    /**
     * Package 하위에 있는 Collaboration의 키워드를 리턴한다.(Usecase ID로 사용하기 위한 용도)
     * 
     * @param collaboration
     * @return String
     */
    private String getKeywords(Collaboration collaboration) {
        String result = UICoreConstant.EMPTY_STRING;
        EList<String> keywords = collaboration.getKeywords();
        for (int i = 0; i < keywords.size(); i++) {
            if (i == 0) {
                result = keywords.get(i);
            } else {
                result += UICoreConstant.PROJECT_CONSTANTS__COMMA + keywords.get(i);
            }
        }
        return result;
    }

    // 1. 클래스 다이어그램.
    /**
     * 클래스 다이어그램 데이터 모델 리스트를 생성합니다.
     * 
     * @param pkg
     * @return List<DataModel>
     */
    private List<DataModel> createClassdiagramModelList(Collaboration collaboration, List<Diagram> diagramList) {
        List<DataModel> diagramModelList = new ArrayList<DataModel>();
        EAnnotation annotation;
        Diagram diagram;

        annotation = collaboration.getEAnnotation(UICoreConstant.PROJECT_CONSTANTS__DIAGRAM);
        if (annotation != null) {
            for (EObject eobj : annotation.getContents()) {
                if (eobj.eClass() == UMLDiagramPackage.Literals.DIAGRAM) {
                    diagram = (Diagram) eobj;
                    if (diagram.getType() == DiagramType.CLASS_DIAGRAM) {
                        diagramList.add(diagram);
                        diagramModelList.add(createDiagramModel(diagram));
                    }
                }
            }
        }

        return diagramModelList;
    }

    /**
     * createSequencediagramModelList
     *  
     * @param collaboration
     * @return List<DataModel>
     */
    private List<DataModel> createSequencediagramModelList(Collaboration collaboration) {
        List<DataModel> diagramModelList = new ArrayList<DataModel>();
        EAnnotation annotation;
        Diagram diagram;

        for (Behavior behavior : collaboration.getOwnedBehaviors()) {
            annotation = behavior.getEAnnotation(UICoreConstant.PROJECT_CONSTANTS__DIAGRAM);
            if (annotation != null) {
                for (EObject eobj : annotation.getContents()) {
                    if (eobj.eClass() == UMLDiagramPackage.Literals.DIAGRAM) {
                        diagram = (Diagram) eobj;
                        if (diagram.getType() == DiagramType.SEQUENCE_DIAGRAM) {
                            diagramModelList.add(createDiagramModel(diagram));
                        }
                    }
                }
            }
        }

        return diagramModelList;
    }

    // // 2. 클래스 목록
    /**
     * 다이어그램에 포함된 클래스의 데이터 모델 리스트를 생성합니다.
     * 
     * @return List<DataModel>
     */
    // private List<DataModel> createClassDataModelList() {
    // List<DataModel> classList = new ArrayList<DataModel>();
    // DataModel classModel;
    //
    // List<Classifier> clasifiers = new ArrayList<Classifier>();
    // for (Diagram diagram : diagramList) {
    //
    // EList<AbstractNode> nodeList = diagram.getNodeList();
    // for (AbstractNode abstractNode : nodeList) {
    // Element element = abstractNode.getUmlModel();
    // if (element != null) {
    // if (element.eClass() == UMLPackage.Literals.CLASS
    // || element.eClass() == UMLPackage.Literals.INTERFACE) {
    // clasifiers.add((Classifier) element);
    // }
    // }
    // }
    // }
    // ProjectUtil.getSortedList(clasifiers);
    //
    // if (!clasifiers.isEmpty()) {
    // for (Classifier clazz : clasifiers) {
    // classModel = new DataModel();
    // setDataModel(classModel, UICoreConstant.REPORT__CLASS_NAME,
    // clazz.getName());
    // setDataModel(classModel,
    // UICoreConstant.REPORT__CLASS_STEREOTYPE, ProjectUtil
    // .getStereotypeLabel(clazz));
    // setDataModel(classModel,
    // UICoreConstant.REPORT__CLASS_DOCUMENTATION,
    // getCommentToString(clazz.getOwnedComments()));
    // setDataModel(classModel, UICoreConstant.REPORT__ATTRIBUTE_LIST,
    // createAttributeDataModelList(clazz));
    // setDataModel(classModel, UICoreConstant.REPORT__OPERATION_LIST,
    // createOperationDataModelList(clazz));
    //
    // classList.add(classModel);
    // }
    // }
    // else {
    //            
    // classModel = new DataModel();
    // setDataModel(classModel, UICoreConstant.REPORT__CLASS_NAME,
    // UMLMessage.LABEL_NO_APPLICABLE);
    // setDataModel(classModel, UICoreConstant.REPORT__ATTRIBUTE_NAME,
    // UMLMessage.LABEL_NO_APPLICABLE);
    // setDataModel(classModel, UICoreConstant.REPORT__OPERATION_NAME,
    // UMLMessage.LABEL_NO_APPLICABLE);
    //            
    // classList.add(classModel);
    //            
    // }
    //
    // return null;
    // }
    // 3. 클래스별 상세 명세 (속성 정보 -[속성명, 타입, 초기값, 설명], 연산 정보 -[연산명, 매개변수, 리턴타입, 설명])
    /**
     * 1) Attribute 데이터 모델 리스트를 생성합니다.
     * 
     * @param cls
     * @return List<DataModel>
     */
    public List<DataModel> createAttributeDataModelList(Classifier cls) {
        List<DataModel> attributeModelList = new ArrayList<DataModel>();
        DataModel attributeModel;
        String qualifiedTypeName = UICoreConstant.EMPTY_STRING;

        if (!cls.getAttributes().isEmpty()) {
            for (Property property : cls.getAttributes()) {
                attributeModel = new DataModel();

                setDataModel(attributeModel, UICoreConstant.REPORT__ATTRIBUTE_NAME, property.getName());
                setDataModel(attributeModel,
                    UICoreConstant.REPORT__ATTRIBUTE_DOCUMENTATION,
                    getCommentToString(property.getOwnedComments()));
                setDataModel(attributeModel, UICoreConstant.REPORT__ATTRIBUTE_VISIBILITY, property.getVisibility()
                    .toString());

                if (property.getType() != null) {
                    StringBuffer stringBuffer = new StringBuffer();

                    boolean isPrimitives = false;
                    qualifiedTypeName = property.getType().getQualifiedName();
                    if (null != qualifiedTypeName) {
                        for (int i = 0; i < UICoreConstant.PROJECT_CONSTANTS__CORE_LIBRARY_NAMES.length; i++) {
                            if (qualifiedTypeName.startsWith(UICoreConstant.PROJECT_CONSTANTS__CORE_LIBRARY_NAMES[i])) {
                                isPrimitives = true;
                                stringBuffer.append(property.getType().getName());
                                break;
                            }
                        }
                        if (!isPrimitives) {
                            // stringBuffer.append(UICoreConstant.REPORT__OPEN_PARENTHESIS);
                            stringBuffer.append(MDDCommonUtil.getMappedName(qualifiedTypeName));
                            // stringBuffer.append(UICoreConstant.REPORT__CLOSE_PARENTHESIS);
                        }
                        setDataModel(attributeModel, UICoreConstant.REPORT__ATTRIBUTE_TYPE, stringBuffer.toString());
                    } else {
                        setDataModel(attributeModel,
                            UICoreConstant.REPORT__ATTRIBUTE_TYPE,
                            UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING);
                    }
                } else {
                    setDataModel(attributeModel,
                        UICoreConstant.REPORT__ATTRIBUTE_TYPE,
                        UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING);
                }

                setDataModel(attributeModel, UICoreConstant.REPORT__ATTRIBUTE_DEFAULT_VALUE, property.getDefault());

                attributeModelList.add(attributeModel);
            }
        }
        // else {
        // attributeModel = new DataModel();
        // setDataModel(attributeModel, UICoreConstant.REPORT__ATTRIBUTE_NAME,
        // UMLMessage.LABEL_NO_APPLICABLE);
        //           
        // attributeModelList.add(attributeModel);
        // }

        return attributeModelList;
    }

}
