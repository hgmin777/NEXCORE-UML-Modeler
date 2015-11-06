/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.project.report.manager.impl;

import java.util.ArrayList;
import java.util.List;

import nexcore.alm.common.word.DataModel;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.model.umldiagram.DiagramType;
import nexcore.tool.uml.model.usecasedetail.FlowObject;
import nexcore.tool.uml.model.usecasedetail.UseCaseDetail;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.util.PreferenceUtil;
import nexcore.tool.uml.ui.project.report.manager.ReportContentManager;
import nexcore.tool.uml.ui.project.report.util.UMLManipulation;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.UseCase;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.report.manager.impl</li>
 * <li>설  명 : UsecaseContentManager</li>
 * <li>작성일 : 2010. 10. 13.</li>
 * <li>작성자 : 한승일</li>
 * </ul>
 */
public class UsecaseContentManager extends ReportContentManager {

    /**
     * @return
     * @see nexcore.tool.uml.ui.project.report.manager.ReportContentManager#createReportContent(org.eclipse.uml2.uml.Element)
     */
    public void createReportContent(DataModel targetModel, Package pkg) {
        DataModel dataModel = null;
        // boolean isEmpty = true;
        List<DataModel> useCaseList = new ArrayList<DataModel>();
        for (UseCase useCase : UMLManipulation.getChildUseCaseList(pkg)) {
            dataModel = createUseCaseDataModel(useCase);
            if (null != dataModel) {
                useCaseList.add(dataModel);
                // isEmpty = false;
            }
        }
        // if (isEmpty) {
        // // TODO: 해당사항없음 처리
        // }
        setDataModel(targetModel, UICoreConstant.REPORT__USECASE_LIST, useCaseList);
        // 유스케이스 다이어그램.
        List<DataModel> list = createDiagramDataModelList(pkg, DiagramType.USE_CASE_DIAGRAM);
        if (0 < list.size()) {
            setDataModel(targetModel, UICoreConstant.REPORT__DIAGRAM_LIST, list);
        }
        // 패키지 다이어그램.
        List<DataModel> packageDiagramList = createDiagramDataModelList(pkg, DiagramType.CLASS_DIAGRAM);
        if (0 < packageDiagramList.size()) {
            setDataModel(targetModel, UICoreConstant.REPORT__CLASSDIAGRAM_LIST, packageDiagramList);
        }

    }

    /**
     * 
     * 
     * @param useCase
     * @return DataModel
     */
    private DataModel createUseCaseDataModel(UseCase useCase) {
        DataModel useCaseDataModel = new DataModel();
        IPreferenceStore store = PreferenceUtil.INSTANCE.getPreferenceStore();
        setDataModel(useCaseDataModel, UICoreConstant.REPORT__USECASE_NAME, useCase.getName());
        setDataModel(useCaseDataModel, UICoreConstant.REPORT__USECASE_PATH, useCase.getQualifiedName());

        String keywords = UICoreConstant.EMPTY_STRING;
        for (String keyword : useCase.getKeywords()) {
            if (!UICoreConstant.EMPTY_STRING.equals(keywords)) {
                keywords += UICoreConstant.PROJECT_CONSTANTS__COMMA;
            }
            keywords += keyword.trim();
        }
        setDataModel(useCaseDataModel, UICoreConstant.REPORT__USECASE_KEYWORD, keywords);

        setDataModel(useCaseDataModel, UICoreConstant.REPORT__USECASE_ADDITIONAL_LIST, createAdditionalInfo(useCase,
            useCaseDataModel));
        // JFos-K 요청으로 '문서' 탭 입력 내용 중 '@부가사항' 정보를 따로 출력해줌.(2011-03-30 sunwoods)
        if (UICoreConstant.EMPTY_STRING.equals((String) getDataModel(useCaseDataModel,
            UICoreConstant.REPORT__USECASE_DOCUMENTATION))) {

            String comment = getCommentToString(useCase.getOwnedComments());
            String document = UICoreConstant.EMPTY_STRING;
            String additional = UICoreConstant.EMPTY_STRING;
            if (!UICoreConstant.EMPTY_STRING.equals(comment)) {

                int idx = comment.indexOf(UMLMessage.LABEL_ADDITIONAL_INFO);
                if (idx == -1) {
                    document = comment;
                } else {
                    document = comment.substring(0, idx - 1);
                    additional = comment.substring(idx + 5);
                }
            }
            setDataModel(useCaseDataModel, UICoreConstant.REPORT__USECASE_DOCUMENTATION, document);
            setDataModel(useCaseDataModel, UICoreConstant.REPORT__USECASE_ADDITIONAL_INFO, applyPreference(additional));
        }
        // ////////////////////////

        // setDataModel(useCaseDataModel,
        // UICoreConstant.REPORT__USECASE_TABLE_NAME, useCase.getName());

        setDataModel(useCaseDataModel, UICoreConstant.REPORT__RM_ID, getRMStereotypeId(useCase,
            UICoreConstant.REPORT__STEREOTYPE_RMUSECASE_PROPERTY_NAME_REQUIREMENTID));
        setDataModel(useCaseDataModel, UICoreConstant.REPORT__USECASE_ID, getRMStereotypeId(useCase,
            UICoreConstant.REPORT__STEREOTYPE_RMUSECASE_PROPERTY_NAME_USECASEID));

        List<String> conditions = new ArrayList<String>();
        conditions = getConditions(useCase);
        if (null == conditions.get(0) || UICoreConstant.EMPTY_STRING.equals(conditions.get(0).trim())) {
            if (store.getBoolean(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_REPORT_PRINT_NOCONTENTS)) {
                setDataModel(useCaseDataModel, UICoreConstant.REPORT__PRE_CONDITION, UMLMessage.LABEL_NO_APPLICABLE);
            } else {
                setDataModel(useCaseDataModel, UICoreConstant.REPORT__PRE_CONDITION, UICoreConstant.EMPTY_STRING);
            }
        } else {
            setDataModel(useCaseDataModel, UICoreConstant.REPORT__PRE_CONDITION, conditions.get(0));
        }

        if (null == conditions.get(1) || UICoreConstant.EMPTY_STRING.equals(conditions.get(1).trim())) {
            if (store.getBoolean(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_REPORT_PRINT_NOCONTENTS)) {
                setDataModel(useCaseDataModel, UICoreConstant.REPORT__POST_CONDITION, UMLMessage.LABEL_NO_APPLICABLE);
            } else {
                setDataModel(useCaseDataModel, UICoreConstant.REPORT__POST_CONDITION, UICoreConstant.EMPTY_STRING);
            }
        } else {
            setDataModel(useCaseDataModel, UICoreConstant.REPORT__POST_CONDITION, conditions.get(1));
        }

        List<DataModel> usecaseModelList = new ArrayList<DataModel>();
        setDataModel(useCaseDataModel, UICoreConstant.REPORT__BASICFLOW_LIST, createFlowDataModelList(useCase,
            UICoreConstant.REPORT__BASICFLOW_LIST));
        setDataModel(useCaseDataModel, UICoreConstant.REPORT__EXCEPTIONFLOW_LIST, createFlowDataModelList(useCase,
            UICoreConstant.REPORT__EXCEPTIONFLOW_LIST));
        setDataModel(useCaseDataModel, UICoreConstant.REPORT__SUBFLOW_LIST, createFlowDataModelList(useCase,
            UICoreConstant.REPORT__SUBFLOW_LIST));
        setDataModel(useCaseDataModel, UICoreConstant.REPORT__ACTOR_LIST, createRelationActorDataModelList(useCase));
        usecaseModelList.add(useCaseDataModel);

        return useCaseDataModel;
    }

    /**
     * 
     * 
     * @param useCase
     * @param useCaseDataModel
     * @return List<DataModel>
     */
    private List<DataModel> createAdditionalInfo(UseCase useCase, DataModel useCaseDataModel) {
        List<DataModel> result = new ArrayList<DataModel>();
        DataModel dataModel;
        String comment = getCommentToString(useCase.getOwnedComments());

        String document = UICoreConstant.EMPTY_STRING;
        String tmpString = UICoreConstant.EMPTY_STRING;

        int initIndex = 0;
        while (comment.contains(UICoreConstant.REPORT_DOCUMENTATION_SEPARATOR)) {
            int startIndex = comment.indexOf(UICoreConstant.REPORT_DOCUMENTATION_SEPARATOR);
            if (startIndex > 0) {
                document += comment.substring(initIndex, startIndex);
            }
            comment = comment.substring(startIndex);
            if (comment.length() > 2) {
                comment = comment.substring(2);
            }
            startIndex = comment.indexOf(UICoreConstant.REPORT_DOCUMENTATION_SEPARATOR);
            dataModel = new DataModel();
            if (startIndex > 0) {
                tmpString = comment.substring(initIndex, startIndex);
                setDataModel(dataModel, UICoreConstant.REPORT__USECASE_ADDITIONAL_KEY, tmpString);
            }

            if (startIndex != -1) {
                comment = comment.substring(startIndex);
            }
            if (comment.length() > 2) {
                comment = comment.substring(2);
            }

            startIndex = comment.indexOf(UICoreConstant.REPORT_DOCUMENTATION_SEPARATOR);
            if (startIndex > 0) {
                tmpString = comment.substring(initIndex, startIndex);
                setDataModel(dataModel, UICoreConstant.REPORT__USECASE_ADDITIONAL_VALUE, tmpString);
            } else if (startIndex == -1) {
                tmpString = comment;
                setDataModel(dataModel, UICoreConstant.REPORT__USECASE_ADDITIONAL_VALUE, tmpString);
            }

            if (startIndex != -1) {
                comment = comment.substring(startIndex);
            }
            result.add(dataModel);
        }

        setDataModel(useCaseDataModel, UICoreConstant.REPORT__USECASE_DOCUMENTATION, document);
        return result;
    }

    /**
     * 요구사항ID, 유스케이스ID 등 RM Stereotype과 관련된 값들을 리턴한다.
     * 
     * @param usecase
     * @param key
     * @return String
     */
    private String getRMStereotypeId(UseCase usecase, String key) {

        String noContents = applyPreference(UICoreConstant.EMPTY_STRING);

        Object obj;
        List<Stereotype> stereoTypes = usecase.getAppliedStereotypes();
        StringBuffer requirementIds = null;
        EDataTypeUniqueEList<String> requirementIdList = null;

        for (Stereotype stereotype : stereoTypes) {
            if (UICoreConstant.REPORT__RM_USECASE_TRACE_MATRIX_APPLIED_STEREOTYPE_NAME.equals(stereotype.getName())) {
                obj = usecase.getValue(stereotype, key);

                if (obj != null) {
                    if (key.equals(UICoreConstant.REPORT__STEREOTYPE_RMUSECASE_PROPERTY_NAME_REQUIREMENTID)) {
                        requirementIdList = (EDataTypeUniqueEList<String>) obj;

                        requirementIds = new StringBuffer();
                        for (String requirementId : requirementIdList) {
                            requirementIds.append(requirementId);

                            if (requirementIdList.indexOf(requirementId) != requirementIdList.size() - 1) {
                                requirementIds.append(UICoreConstant.PROJECT_CONSTANTS__COMMA)
                                    .append(UICoreConstant.PROJECT_CONSTANTS__NEW_LINE);
                            }
                        }

                        return requirementIds.toString().length() == 0 ? noContents : requirementIds.toString();
                    } else {
                        return obj.toString();
                    }
                } else {
                    return noContents;
                }
            }
        }

        return noContents;
    }

    /**
     * 해당 유스케이스의 사전 조건, 사후 조건을 가져온다.
     * 
     * @param uc
     * @return List<String>
     */
    private List<String> getConditions(UseCase uc) {
        List<String> conditions = new ArrayList<String>();

        EAnnotation annotation = uc.getEAnnotation(UICoreConstant.PROJECT_CONSTANTS__USECASE_DETAIL);
        String preCondition = null;
        String postCondition = null;
        if (annotation != null && annotation instanceof UseCaseDetail) {
            preCondition = ((UseCaseDetail) annotation).getPreCondition();
            postCondition = ((UseCaseDetail) annotation).getPostCondition();
        }

        conditions.add(preCondition);
        conditions.add(postCondition);

        return conditions;
    }

    /**
     * 유스케이스별 흐름 정보
     * 
     * @param usecase
     * @param flowType
     * @return List<DataModel>
     */
    private List<DataModel> createFlowDataModelList(UseCase usecase, String flowType) {

        List<DataModel> flowModelList = new ArrayList<DataModel>();
        List<FlowObject> flowList = new ArrayList<FlowObject>();
        DataModel usecaseModel;

        EAnnotation annotation = usecase.getEAnnotation(UICoreConstant.PROJECT_CONSTANTS__USECASE_DETAIL);
        if (annotation != null && annotation instanceof UseCaseDetail) {
            UseCaseDetail detail = (UseCaseDetail) annotation;
            if (UICoreConstant.REPORT__BASICFLOW_LIST.equals(flowType)) {
                flowList = detail.getBasicFlowList();
            } else if (UICoreConstant.REPORT__EXCEPTIONFLOW_LIST.equals(flowType)) {
                flowList = detail.getExceptionFlowList();
            } else if (UICoreConstant.REPORT__SUBFLOW_LIST.equals(flowType)) {
                flowList = detail.getSubFlowList();
            }
        }

        if (!flowList.isEmpty()) {
            String flowId = UICoreConstant.EMPTY_STRING;
            String flowName = UICoreConstant.EMPTY_STRING;
            String flowDesc = UICoreConstant.EMPTY_STRING;
            String flowOverview = UICoreConstant.EMPTY_STRING;
            for (FlowObject fObj : flowList) {
                usecaseModel = new DataModel();
                if (UICoreConstant.REPORT__BASICFLOW_LIST.equals(flowType)) {
                    setDataModel(usecaseModel, UICoreConstant.REPORT__FLOW_TYPE, UMLMessage.LABEL_BASIC_FLOW);
                    // setDataModel(usecaseModel,
                    // UICoreConstant.REPORT__BASIC_FLOW_NAME,
                    // fObj.getFlowName());
                } else if (UICoreConstant.REPORT__EXCEPTIONFLOW_LIST.equals(flowType)) {
                    setDataModel(usecaseModel, UICoreConstant.REPORT__FLOW_TYPE, UMLMessage.LABEL_EXEPTION_FLOW);
                    // setDataModel(usecaseModel,
                    // UICoreConstant.REPORT__SUB_FLOW_NAME,
                    // fObj.getFlowName());
                } else if (UICoreConstant.REPORT__SUBFLOW_LIST.equals(flowType)) {
                    setDataModel(usecaseModel, UICoreConstant.REPORT__FLOW_TYPE, UMLMessage.LABEL_SUB_FLOW);
                    // setDataModel(usecaseModel,
                    // UICoreConstant.REPORT__EXCEP_FLOW_NAME,
                    // fObj.getFlowName());
                }

                flowId = fObj.getFlowId();
                flowName = fObj.getFlowName();
                flowDesc = fObj.getFlowDescription();
                flowOverview = fObj.getFlowOverview();
                if (UMLMessage.LABEL_FLOW_ID.equals(flowId) && UMLMessage.LABEL_FLOW_NAME.equals(flowName)
                    && (flowDesc == null || UICoreConstant.EMPTY_STRING.equals(flowDesc.trim()))) {
                    continue;
                }
                if (UMLMessage.LABEL_FLOW_ID.equals(flowId)) {
                    flowId = UICoreConstant.EMPTY_STRING;
                }
                if (UMLMessage.LABEL_FLOW_NAME.equals(flowName)) {
                    flowName = UICoreConstant.EMPTY_STRING;
                }
                if (UMLMessage.LABEL_FLOW_DESCRIPTION.equals(flowDesc)) {
                    flowDesc = UICoreConstant.EMPTY_STRING;
                }
                if (UMLMessage.LABEL_FLOW_OVERVIEW.equals(flowOverview)) {
                    flowOverview = UICoreConstant.EMPTY_STRING;
                }
                setDataModel(usecaseModel, UICoreConstant.REPORT__FLOW_ID, flowId);
                setDataModel(usecaseModel, UICoreConstant.REPORT__FLOW_NAME, flowName);
                setDataModel(usecaseModel, UICoreConstant.REPORT__FLOW_OUTLINE, flowOverview);
                setDataModel(usecaseModel, UICoreConstant.REPORT__FLOW_DOCUMENTATION, flowDesc);
                flowModelList.add(usecaseModel);
            }
        } else {
            // "(해당사항 없음)" 출력
            IPreferenceStore store = PreferenceUtil.INSTANCE.getPreferenceStore();
            if (store.getBoolean(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_REPORT_PRINT_NOCONTENTS)) {
                usecaseModel = new DataModel();
                setDataModel(usecaseModel, UICoreConstant.REPORT__FLOW_NAME, UMLMessage.LABEL_NO_APPLICABLE);
                flowModelList.add(usecaseModel);
            }
        }

        return flowModelList;

    }

    /**
     * 각 유스케이스별 연관된 Actor 데이터 모델 리스트를 생성합니다.
     * 
     * @param usecase
     * @return List<DataModel>
     */
    private List<DataModel> createRelationActorDataModelList(UseCase usecase) {
        List<DataModel> actorModelList = new ArrayList<DataModel>();
        DataModel actorModel;

        List<Actor> actorlist = UMLManager.getRelatedActors(usecase);
        if (!actorlist.isEmpty()) {
            for (Actor actor : actorlist) {
                actorModel = new DataModel();
                setDataModel(actorModel, UICoreConstant.REPORT__ACTOR_NAME, actor.getName());
                setDataModel(actorModel,
                    UICoreConstant.REPORT__ACTOR_DOCUMENTATION,
                    getCommentToString(actor.getOwnedComments()));
                actorModelList.add(actorModel);
            }

        } else {
            actorModel = new DataModel();

            // "(해당사항 없음)" 출력
            setDataModel(actorModel, UICoreConstant.REPORT__ACTOR_NAME, applyPreference(UICoreConstant.EMPTY_STRING));
            actorModelList.add(actorModel);

        }
        return actorModelList;
    }
}
