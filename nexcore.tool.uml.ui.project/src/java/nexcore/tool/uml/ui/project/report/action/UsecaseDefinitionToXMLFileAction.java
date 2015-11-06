/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.project.report.action;

import java.util.List;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.project.report.control.ReportHandler;
import nexcore.tool.uml.ui.project.report.control.impl.UsecaseSpecReportHandler;

import org.eclipse.core.resources.IFile;
import org.eclipse.uml2.uml.Element;

/**
 * <ul>
 * <li>모델하위요소 선택</li>
 * <li>템플릿 선택: 기본 템플릿의 경우 내부에서 처리됨, 사용자 정의 템플릿은 IFile을 생성함</li>
 * <li>저장 위치 선택</li>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.report.action</li>
 * <li>설  명 : UsecaseDefinitionToXMLFileAction</li>
 * <li>작성일 : 2010. 10. 12.</li>
 * <li>작성자 : 오은주</li>
 * </ul>
 */
public class UsecaseDefinitionToXMLFileAction extends CommonReportAction {

    /**
     * @see nexcore.tool.uml.ui.project.report.action.CommonReportAction#getDocType()
     */
    @Override
    protected String getDocType() {
        return UICoreConstant.REPORT__DOCTYPE_DEFINITION;
    }

    /**
     * @see nexcore.tool.uml.ui.project.report.action.CommonReportAction#getTitle()
     */
    @Override
    protected String getTitle() {
        return UMLMessage.LABEL_EXPORT_USECASE_DEFINITION_TO_DOC;
    }

    /**
     * @see nexcore.tool.uml.ui.project.report.action.CommonReportAction#getReportHandler(java.lang.String,
     *      org.eclipse.core.resources.IFile, java.util.List, java.lang.String)
     */
    @Override
    protected ReportHandler getReportHandler(String reportLocation, IFile templateFile, List<Element> selectedPackage,
                                             String projectName) {
        return new UsecaseSpecReportHandler(reportLocation, templateFile, selectedPackage, projectName);
    }

}
