/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.property.section;

import java.util.ArrayList;
import java.util.List;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.model.usecasedetail.FlowObject;
import nexcore.tool.uml.model.usecasedetail.UseCaseDetail;
import nexcore.tool.uml.model.usecasedetail.UseCaseDetailFactory;
import nexcore.tool.uml.ui.core.UICoreConstant;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.uml2.uml.UseCase;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.section</li>
 * <li>설  명 : UsecaseSubFlowSection</li>
 * <li>작성일 : 2010. 5. 20.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
public class UsecaseSubFlowSection extends UsecaseFlowSection {

    /**
     * @see nexcore.tool.uml.ui.property.section.UsecaseFlowSection#deleteFlow(nexcore.tool.uml.model.usecasedetail.UseCaseDetail,
     *      java.lang.Object)
     */
    @Override
    protected void deleteFlow(UseCaseDetail usecaseDetail, Object selectedElement) {
        List<FlowObject> list = usecaseDetail.getSubFlowList();
        if (list.contains(selectedElement)) {
            list.remove(selectedElement);
        }
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.UsecaseFlowSection#getFlowList(org.eclipse.uml2.uml.UseCase)
     */
    @Override
    protected Object[] getFlowList(UseCase useCase) {
        List<FlowObject> flowList = new ArrayList<FlowObject>();
        EAnnotation annotation = useCase.getEAnnotation(UICoreConstant.PROJECT_CONSTANTS__USECASE_DETAIL);
        if (null != annotation && annotation instanceof UseCaseDetail) {
            UseCaseDetail detail = (UseCaseDetail) annotation;
            List<FlowObject> subFlows = detail.getSubFlowList();
            if (!subFlows.isEmpty()) {
                flowList.addAll(subFlows);
            }
        }
        return flowList.toArray();
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.UsecaseFlowSection#moveDown(nexcore.tool.uml.model.usecasedetail.UseCaseDetail,
     *      java.lang.Object)
     */
    @Override
    protected void moveDown(UseCaseDetail usecaseDetail, Object selectedElement) {
        List<FlowObject> list = usecaseDetail.getSubFlowList();
        if (list.contains(selectedElement)) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals(selectedElement)) {
                    if (i == list.size()) {
                        return;
                    }
                    list.remove(i);
                    list.add(i + 1, (FlowObject) selectedElement);
                    break;
                }
            }
        }
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.UsecaseFlowSection#moveUp(nexcore.tool.uml.model.usecasedetail.UseCaseDetail,
     *      java.lang.Object)
     */
    @Override
    protected void moveUp(UseCaseDetail usecaseDetail, Object selectedElement) {
        List<FlowObject> list = usecaseDetail.getSubFlowList();
        if (list.contains(selectedElement)) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals(selectedElement)) {
                    if (i == 0) {
                        return;
                    }
                    list.remove(i);
                    list.add(i - 1, (FlowObject) selectedElement);
                }
            }
        }
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.UsecaseFlowSection#setFlowList()
     */
    @Override
    protected void setFlowList() {
        EAnnotation annotation = getData().getEAnnotation(UICoreConstant.PROJECT_CONSTANTS__USECASE_DETAIL);
        if (null != annotation) {
            UseCaseDetail detail = (UseCaseDetail) annotation;
            FlowObject flowObject = UseCaseDetailFactory.eINSTANCE.createFlowObject();
            flowObject.setFlowId(UMLMessage.LABEL_FLOW_ID);
            flowObject.setFlowName(UMLMessage.LABEL_FLOW_NAME);
            detail.getSubFlowList().add(flowObject);
            detail.setSource(UICoreConstant.PROJECT_CONSTANTS__USECASE_DETAIL);
            getData().getEAnnotations().add(detail);

        } else {
            UseCaseDetail detail = UseCaseDetailFactory.eINSTANCE.createUseCaseDetail();
            FlowObject flowObject = UseCaseDetailFactory.eINSTANCE.createFlowObject();
            flowObject.setFlowId(UMLMessage.LABEL_FLOW_ID);
            flowObject.setFlowName(UMLMessage.LABEL_FLOW_NAME);
            detail.setSource(UICoreConstant.PROJECT_CONSTANTS__USECASE_DETAIL);
            detail.getSubFlowList().add(flowObject);
            getData().getEAnnotations().add(detail);
        }
    }

}
