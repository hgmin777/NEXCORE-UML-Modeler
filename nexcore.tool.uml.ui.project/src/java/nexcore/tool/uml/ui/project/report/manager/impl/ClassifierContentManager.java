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
import nexcore.tool.mdd.core.util.MDDCommonUtil;
import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.util.PreferenceUtil;
import nexcore.tool.uml.ui.core.util.ProjectUtil;
import nexcore.tool.uml.ui.project.report.manager.ReportContentManager;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.LiteralString;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.Property;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.newReport.manager.biz</li>
 * <li>설 명 : ActorContentManager</li>
 * <li>작성일 : 2010. 10. 13.</li>
 * <li>작성자 : 한승일</li>
 * </ul>
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.report.manager.impl</li>
 * <li>설  명 : ClassifierContentManager</li>
 * <li>작성일 : 2010. 10. 13.</li>
 * <li>작성자 : 한승일</li>
 * </ul>
 */
public class ClassifierContentManager extends ReportContentManager {

    /**
     * @see nexcore.tool.uml.ui.project.report.manager.ReportContentManager#createReportContent(org.eclipse.uml2.uml.Element)
     */
    public void createReportContent(DataModel targetModel, Package pkg) {

    }

    /**
     * createClassifierDataModel
     *  
     * @param objClassifier
     * @return DataModel
     */
    public DataModel createClassifierDataModel(org.eclipse.uml2.uml.Classifier objClassifier) {
        List<DataModel> classList = new ArrayList<DataModel>();

        DataModel classModel = new DataModel();
        setDataModel(classModel, UICoreConstant.REPORT__CLASS_NAME, objClassifier.getName());
        setDataModel(classModel, UICoreConstant.REPORT__CLASS_STEREOTYPE, ProjectUtil.getStereotypeLabel(objClassifier));
        setDataModel(classModel,
            UICoreConstant.REPORT__CLASS_DOCUMENTATION,
            applyPreference(getCommentToString(objClassifier.getOwnedComments())));
        setDataModel(classModel, UICoreConstant.REPORT__ATTRIBUTE_LIST, createAttributeDataModelList(objClassifier));
        setDataModel(classModel, UICoreConstant.REPORT__OPERATION_LIST, createOperationDataModelList(objClassifier));

        classList.add(classModel);
        ProjectUtil.getSortedList(classList);

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

        return classModel;
    }

    /**
     * 
     * 
     * @param objClassifier
     * @return Object
     */
    private Object createOperationDataModelList(Classifier objClassifier) {
        List<DataModel> operationModelList = new ArrayList<DataModel>();
        DataModel operationModel;
        IPreferenceStore store = PreferenceUtil.INSTANCE.getPreferenceStore();

        if (!objClassifier.getOperations().isEmpty()) {
            for (Operation operation : objClassifier.getOperations()) {
                operationModel = new DataModel();

                setDataModel(operationModel, UICoreConstant.REPORT__OPERATION_NAME, operation.getName());
                setDataModel(operationModel,
                    UICoreConstant.REPORT__OPERATION_DOCUMENTATION,
                    applyPreference(getCommentToString(operation.getOwnedComments())));
                setDataModel(operationModel, UICoreConstant.REPORT__OPERATION_VISIBILITY, operation.getVisibility()
                    .toString());

                // 리턴 유형
                Parameter returnType = operation.getReturnResult();
                String returnTypeName = UICoreConstant.PROJECT_CONSTANTS__VOID;
                if (returnType != null) {
                    if (returnType.getType() != null) {
                        if (returnType.getType().getName() != null) {
                            returnTypeName = returnType.getType().getName();
                        }
                    }
                }
                setDataModel(operationModel, UICoreConstant.REPORT__OPERATION_RETURNTYPE, returnTypeName);

                StringBuffer paramDocument = new StringBuffer();
                StringBuffer sb = new StringBuffer();
                String typeName = UICoreConstant.EMPTY_STRING;
                int cnt = 1;
                for (int i = 0; i < operation.getOwnedParameters().size(); i++) {
                    Parameter parameter = operation.getOwnedParameters().get(i);
                    if (!ParameterDirectionKind.RETURN_LITERAL.equals(parameter.getDirection())) {
                        typeName = UICoreConstant.EMPTY_STRING;

                        // 번호
                        int tempCnt = cnt++;
                        if (store.getBoolean(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_REPORT_PRINT_PARAM_NO)) {
                            paramDocument.append(tempCnt);
                            paramDocument.append(UICoreConstant.PROJECT_CONSTANTS__DOT);
                            paramDocument.append(UICoreConstant.PROJECT_CONSTANTS__BLANK);

                            sb.append(tempCnt);
                            sb.append(UICoreConstant.PROJECT_CONSTANTS__DOT);
                            sb.append(UICoreConstant.PROJECT_CONSTANTS__BLANK);
                        }

                        // 변수명.
                        if (parameter.getName() != null) {
                            sb.append(parameter.getName());
                        }
                        sb.append(UICoreConstant.PROJECT_CONSTANTS__BLANK);
                        sb.append(UICoreConstant.PROJECT_CONSTANTS__COLON);
                        sb.append(UICoreConstant.PROJECT_CONSTANTS__BLANK);

                        // 타입
                        if (parameter.getType() != null) {
                            if (parameter.getType().getName() != null) {
                                typeName = parameter.getType().getName();
                                sb.append(typeName);
                            }
                        }

                        // 기본값.
                        if (parameter.getDefaultValue() != null) {
                            if (parameter.getDefaultValue() instanceof LiteralString) {
                                if (((LiteralString) parameter.getDefaultValue()).getValue() != null) {
                                    sb.append(UICoreConstant.PROJECT_CONSTANTS__BLANK);
                                    sb.append(UICoreConstant.PROJECT_CONSTANTS__EQUAL);
                                    sb.append(UICoreConstant.PROJECT_CONSTANTS__BLANK);
                                    if (UICoreConstant.STRING_LITERAL.equals(typeName)) {
                                        sb.append("\""); //$NON-NLS-1$
                                        sb.append(((LiteralString) parameter.getDefaultValue()).getValue());
                                        sb.append("\""); //$NON-NLS-1$
                                    } else {
                                        sb.append(((LiteralString) parameter.getDefaultValue()).getValue());
                                    }
                                }
                            }
                        }
                        sb.append(UICoreConstant.PROJECT_CONSTANTS__NEW_LINE);

                        // 문서
                        paramDocument.append(applyPreference(getCommentToString(parameter.getOwnedComments())));
                        paramDocument.append(UICoreConstant.PROJECT_CONSTANTS__NEW_LINE);
                    }
                }
                setDataModel(operationModel, UICoreConstant.REPORT__OPERATION_PARAMETER, sb.toString());
                setDataModel(operationModel,
                    UICoreConstant.REPORT__OPERATION_PARAMETER_DOCUMENTATION,
                    paramDocument.toString());
                operationModelList.add(operationModel);
            }
        }
        // else {
        // operationModel = new DataModel();
        // setDataModel(operationModel, UICoreConstant.REPORT__OPERATION_NAME,
        // UMLMessage.LABEL_NO_APPLICABLE);
        //           
        // operationModelList.add(operationModel);
        // }

        return operationModelList;
    }

    /**
     * 
     * 
     * @param objClassifier
     * @return Object
     */
    private Object createAttributeDataModelList(Classifier objClassifier) {
        List<DataModel> attributeModelList = new ArrayList<DataModel>();
        DataModel attributeModel;
        String qualifiedTypeName = UICoreConstant.EMPTY_STRING;

        if (!objClassifier.getAttributes().isEmpty()) {
            for (Property property : objClassifier.getAttributes()) {
                attributeModel = new DataModel();

                setDataModel(attributeModel, UICoreConstant.REPORT__ATTRIBUTE_NAME, property.getName());
                setDataModel(attributeModel,
                    UICoreConstant.REPORT__ATTRIBUTE_DOCUMENTATION,
                    applyPreference(getCommentToString(property.getOwnedComments())));
                setDataModel(attributeModel, UICoreConstant.REPORT__ATTRIBUTE_VISIBILITY, property.getVisibility()
                    .toString());

                if (property.getType() != null) {
                    StringBuffer stringBuffer = new StringBuffer();

                    boolean isPrimitives = false;
                    qualifiedTypeName = property.getType().getQualifiedName();
                    if (null == qualifiedTypeName) {
                        stringBuffer.append(UICoreConstant.EMPTY_STRING);
                    } else {
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
                    }
                    setDataModel(attributeModel, UICoreConstant.REPORT__ATTRIBUTE_TYPE, stringBuffer.toString());
                } else {
                    setDataModel(attributeModel,
                        UICoreConstant.REPORT__ATTRIBUTE_TYPE,
                        UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING);
                }

                // 속성의 초기값이 없으면 'N/A' 출력
                String defaultValue = property.getDefault();
                if (defaultValue == null) {
                    defaultValue = UICoreConstant.EMPTY_STRING;
                } else {
                    defaultValue = defaultValue.trim();
                }
                setDataModel(attributeModel,
                    UICoreConstant.REPORT__ATTRIBUTE_DEFAULT_VALUE,
                    applyPreferenceNA(defaultValue));

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
