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
import nexcore.tool.uml.manager.util.ModelManager;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.DiagramType;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.util.ProjectUtil;
import nexcore.tool.uml.ui.project.report.util.UMLManipulation;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * 컴포넌트 설계서
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.report.manager.impl</li>
 * <li>설 명 : ComponentArchitectureContentManager</li>
 * <li>작성일 : 2011. 8. 4.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
public class ComponentArchitectureContentManager extends ComponentContentManager {

    /** 다이어그램 리스트(클래스 다이어그램 리스트를 이용하기 위함) */
    private List<Diagram> diagramList = new ArrayList<Diagram>();

    /**
     * @see nexcore.tool.uml.ui.project.report.manager.impl.ComponentContentManager#createReportContent(nexcore.tool.core.word.DataModel,
     *      org.eclipse.uml2.uml.Package)
     */
    @Override
    public void createReportContent(DataModel parentDataModel, Package pkg) {

        DataModel dataModel;
        Component component;

        // 컴포넌트 리스트
        List<DataModel> componentList = new ArrayList<DataModel>();
        List<Component> components = UMLManipulation.getChildComponentList(pkg);
        for (int i = 0; i < components.size(); i++) {
            component = components.get(i);
            dataModel = new DataModel();
            setDataModel(dataModel, UICoreConstant.REPORT__COMPONENT_NAME, component.getName());
            setDataModel(dataModel,
                UICoreConstant.REPORT__COMPONENT_DOCUMENTATION,
                getCommentToString(component.getOwnedComments()));
            setDataModel(dataModel,
                UICoreConstant.REPORT__COMPONENT_STEREOTYPE,
                ProjectUtil.getStereotypeText(component));
            setDataModel(dataModel, UICoreConstant.REPORT__INTERFACE_LIST, createInterfaceList(component));
            setDataModel(dataModel, UICoreConstant.REPORT__SEQUENCEDIAGRAM_LIST, getDiagramList(component,
                DiagramType.SEQUENCE_DIAGRAM));
            setDataModel(dataModel, UICoreConstant.REPORT__CLASSDIAGRAM_LIST, getDiagramList(component,
                DiagramType.CLASS_DIAGRAM));
            setDataModel(dataModel, UICoreConstant.REPORT__CLASS_LIST, createClassList(diagramList));

            if (null != dataModel) {
                componentList.add(dataModel);
            }
        }
        setDataModel(parentDataModel, UICoreConstant.REPORT__COMPONENT_LIST, componentList);

    }

    /**
     * 컴포넌트 안에 있는 내부 클래스/시퀀스 다이어그램 리스트
     * 
     * @param component
     * @param diagramType
     * @return List<DataModel>
     */
    public List<DataModel> getDiagramList(Component component, DiagramType diagramType) {
        DataModel dataModel = new DataModel();
        List<DataModel> diagramDataModelList = new ArrayList<DataModel>();
        diagramList = new ArrayList<Diagram>();
        if (component != null) {
            diagramList = ModelManager.getAllDiagramList(component, diagramType);
            for (Diagram diagram : diagramList) {
                dataModel = createDiagramModel(diagram);
                if (null != dataModel) {
                    diagramDataModelList.add(dataModel);
                }
            }
        }
        return diagramDataModelList;
    }

    /**
     * 컴포넌트 하위에 있는 클래스 다이어그램에 작성되어 있는 클래스 리스트를 반환한다.
     * 
     * @param classDataModelList
     * @param component
     * @param componentType
     * @return
     */
    private List<DataModel> createClassList(List<Diagram> classdiagramList) {
        DataModel dataModel;
        List<DataModel> dataModelList = new ArrayList<DataModel>();
        List<Classifier> classifierList = new ArrayList<Classifier>();

        for (Diagram diagram : classdiagramList) {
            UMLManipulation.getClassList(diagram, classifierList);
        }

        for (Classifier classifier : classifierList) {
            if (UMLPackage.Literals.CLASS == classifier.eClass()) {
                dataModel = new DataModel();
                setDataModel(dataModel, UICoreConstant.REPORT__CLASS_NAME, classifier.getName());
                setDataModel(dataModel,
                    UICoreConstant.REPORT__CLASS_DOCUMENTATION,
                    applyPreference(getCommentToString(classifier.getOwnedComments())));
                setDataModel(dataModel,
                    UICoreConstant.REPORT__CLASS_STEREOTYPE,
                    ProjectUtil.getStereotypeText(classifier));
                setDataModel(dataModel, UICoreConstant.REPORT__OPERATION_LIST, createOperationList(classifier));
                if (null != dataModel) {
                    dataModelList.add(dataModel);
                }

            }
        }
        return dataModelList;
    }

}
