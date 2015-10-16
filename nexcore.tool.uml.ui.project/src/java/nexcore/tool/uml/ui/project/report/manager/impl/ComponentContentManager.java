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
import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.DiagramType;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.util.PreferenceUtil;
import nexcore.tool.uml.ui.core.util.ProjectUtil;
import nexcore.tool.uml.ui.project.report.manager.ReportContentManager;
import nexcore.tool.uml.ui.project.report.util.UMLManipulation;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;

/**
 * 컴포넌트 명세서
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.report.manager.impl</li>
 * <li>설 명 : ComponentContentManager</li>
 * <li>작성일 : 2011. 8. 2.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
public class ComponentContentManager extends ReportContentManager {

    /**
     * @see nexcore.tool.uml.ui.project.report.manager.ReportContentManager#createReportContent(nexcore.tool.core.word.DataModel,
     *      org.eclipse.uml2.uml.Package)
     */
    @Override
    public void createReportContent(DataModel parentDataModel, Package pkg) {
        DataModel dataModel;
        if (!(pkg instanceof Model)) {
            setDataModel(parentDataModel, UICoreConstant.REPORT__COMPONENTDIAGRAM_LIST, createComponentDiagramList(pkg));
        }
        // 컴포넌트 리스트
        List<DataModel> componentList = new ArrayList<DataModel>();
        List<Component> components = UMLManipulation.getChildComponentList(pkg);
        for (int i = 0; i < components.size(); i++) {
            dataModel = new DataModel();
            setDataModel(dataModel, UICoreConstant.REPORT__COMPONENT_NO, i + 1);
            setDataModel(dataModel, UICoreConstant.REPORT__COMPONENT_NAME, components.get(i).getName());
            setDataModel(dataModel,
                UICoreConstant.REPORT__COMPONENT_DOCUMENTATION,
                getCommentToString(components.get(i).getOwnedComments()));
            setDataModel(dataModel,
                UICoreConstant.REPORT__COMPONENT_STEREOTYPE,
                ProjectUtil.getStereotypeText(components.get(i)));
            setDataModel(dataModel, UICoreConstant.REPORT__INTERFACE_LIST, createInterfaceList(components.get(i)));
            if (null != dataModel) {
                componentList.add(dataModel);
            }
        }
        setDataModel(parentDataModel, UICoreConstant.REPORT__COMPONENT_LIST, componentList);

    }

    /**
     * 컴포넌트의 Provided Interface 리스트.
     * 
     * @param component
     * @return List<DataModel>
     */
    protected List<DataModel> createInterfaceList(Component component) {
        List<DataModel> interfaceDataModelList = new ArrayList<DataModel>();
        DataModel dataModel;
        EList<Interface> interfaces = component.getProvideds();
        for (Interface interfaze : interfaces) {
            dataModel = new DataModel();
            setDataModel(dataModel, UICoreConstant.REPORT__INTERFACE_NAME, interfaze.getName());

            // 설명
            setDataModel(dataModel,
                UICoreConstant.REPORT__INTERFACE_DOCUMENTATION,
                applyPreference(getCommentToString(interfaze.getOwnedComments())));

            setDataModel(dataModel, UICoreConstant.REPORT__OPERATION_LIST, createOperationList(interfaze));
            if (null != dataModel) {
                interfaceDataModelList.add(dataModel);
            }

        }
        return interfaceDataModelList;

    }

    /**
     * 인터페이스의 오퍼레이션 리스트.
     * 
     * @param classifier
     * @return List<DataModel>
     */
    protected List<DataModel> createOperationList(Classifier classifier) {
        List<DataModel> operationDataModelList = new ArrayList<DataModel>();
        DataModel dataModel;
        IPreferenceStore store = PreferenceUtil.INSTANCE.getPreferenceStore();
        EList<Operation> operations = classifier.getOperations();
        for (Operation operation : operations) {
            dataModel = new DataModel();
            setDataModel(dataModel, UICoreConstant.REPORT__OPERATION_NAME, operation.getName());
            setDataModel(dataModel, UICoreConstant.REPORT__OPERATION_VISIBILITY, operation.getVisibility().toString());

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
            setDataModel(dataModel, UICoreConstant.REPORT__OPERATION_RETURNTYPE, returnTypeName);

            // 설명
            setDataModel(dataModel,
                UICoreConstant.REPORT__OPERATION_DOCUMENTATION,
                applyPreference(getCommentToString(operation.getOwnedComments())));

            // 파라미터
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

                    sb.append(UICoreConstant.PROJECT_CONSTANTS__NEW_LINE);

                    // 문서
                    paramDocument.append(applyPreference(getCommentToString(parameter.getOwnedComments())));
                    paramDocument.append(UICoreConstant.PROJECT_CONSTANTS__NEW_LINE);
                }
            }
            setDataModel(dataModel, UICoreConstant.REPORT__OPERATION_PARAMETER, applyPreference(sb.toString()));
            setDataModel(dataModel,
                UICoreConstant.REPORT__OPERATION_PARAMETER_DOCUMENTATION,
                applyPreference(paramDocument.toString()));

            if (null != dataModel) {
                operationDataModelList.add(dataModel);
            }
        }
        return operationDataModelList;
    }

    /**
     * 컴포넌트 다이어그램 리스트를 데이터모델의 리스트로 리턴한다.
     * 
     * @param pkg
     * @return
     */
    protected List<DataModel> createComponentDiagramList(Package pkg) {

        List<DataModel> componentDiagramList = new ArrayList<DataModel>();
        DataModel dataModel = new DataModel();
        List<Diagram> diagramList = UMLManipulation.getDiagramList(pkg, DiagramType.COMPONENT_DIAGRAM);
        for (Diagram diagram : diagramList) {
            dataModel = createDiagramModel(diagram);
            if (null != dataModel) {
                componentDiagramList.add(dataModel);
            }
        }
        return componentDiagramList;
    }

    /**
     * rootModel에 컴포넌트 리스트를 set해준다.
     * 
     * @param rootModel
     * @param pkg
     */
    public void createComponentDiagramList(DataModel rootModel, Package pkg) {
        setDataModel(rootModel, UICoreConstant.REPORT__COMPONENTDIAGRAM_LIST, createComponentDiagramList(pkg));
    }

}
