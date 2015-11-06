/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.project.report.control.impl;

import java.util.List;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.project.report.control.ReportHandler;
import nexcore.tool.uml.ui.project.report.manager.impl.ComponentArchitectureContentManager;

import org.eclipse.core.resources.IFile;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;

/**
 * 컴포넌트 설계서
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.report.control.impl</li>
 * <li>설 명 : ComponentArchitectureReportHandler</li>
 * <li>작성일 : 2011. 8. 4.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
public class ComponentArchitectureReportHandler extends ReportHandler {

    /**
     * @param reportLocation
     * @param templateFile
     * @param selectedPackageList
     * @param projectName
     */
    public ComponentArchitectureReportHandler(String reportLocation, IFile templateFile,
    List<Element> selectedPackageList, String projectName) {
        super(reportLocation, templateFile, selectedPackageList, projectName);
    }

    /**
     * @see nexcore.tool.uml.ui.project.report.control.ReportHandler#createReportData(org.eclipse.uml2.uml.Model)
     */
    @Override
    protected void createReportData(Model model) {
        jobMonitor.startTask(UMLMessage.LABEL_CREATE_COMPONENT_ARCHITECTURE, selectedPackageList.size());

        if (jobMonitor.isCanceled()) {
            return;
        }
        if (null != model) {
            navigatePackage(new ComponentArchitectureContentManager(), rootModel, model);
        }

        jobMonitor.endTask();
    }

    /**
     * @see nexcore.tool.uml.ui.project.report.control.ReportHandler#getDefaultTemplateFileName()
     */
    @Override
    protected String getDefaultTemplateFileName() {
        return UICoreConstant.TEMPLATE_XML_COMPONENT_ARCHITECTURE_XML;
    }

}
