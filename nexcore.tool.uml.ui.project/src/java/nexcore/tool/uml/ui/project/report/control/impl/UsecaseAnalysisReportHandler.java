/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.project.report.control.impl;

import java.util.List;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.project.report.control.ReportHandler;
import nexcore.tool.uml.ui.project.report.manager.impl.UsecaseRealizationContentManager;
import nexcore.tool.uml.ui.project.report.util.UMLManipulation;

import org.eclipse.core.resources.IFile;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.report.control.impl</li>
 * <li>설  명 : UsecaseAnalysisReportHandler</li>
 * <li>작성일 : 2010. 11. 3.</li>
 * <li>작성자 : 오은주</li>
 * </ul>
 */
public class UsecaseAnalysisReportHandler extends ReportHandler {

    /**
     * @param reportLocation
     * @param templateFile
     * @param selectedPackageList
     * @param projectName
     */
    public UsecaseAnalysisReportHandler(String reportLocation, IFile templateFile, List<Element> selectedPackageList,
    String projectName) {
        super(reportLocation, templateFile, selectedPackageList, projectName);
        // TODO Auto-generated constructor stub
    }

    /**
     * @see nexcore.tool.uml.ui.project.report.control.ReportHandler#createReportData(org.eclipse.uml2.uml.Model)
     */
    @Override
    protected void createReportData(Model model) {
        this.jobMonitor.startTask(UMLMessage.LABEL_CREATE_USECASE_ANALYSIS, this.selectedPackageList.size());

        // TODO 유스케이스 설계서에 관련된 내용 채우기
        // PackageContentManager packageContentManager = new
        // PackageContentManager();
        UsecaseRealizationContentManager usecaseRealizationContentManager = new UsecaseRealizationContentManager();

        Package packageUsecaseRealization;

        // 1. 패키지 구조 정보 생성
        // packageContentManager.setSelectedPackageList(this.selectedPackageList);
        // packageContentManager.createReportContent(this.rootModel, model);

        // //2. 유스케이스 리얼라이제이션 정보 생성
        // usecaseRealizationContentManager.createReportContent(this.rootModel,
        // model);

        packageUsecaseRealization = UMLManipulation.findUsecaseRealizationPackage(model);
        if (this.jobMonitor.isCanceled()) {
            return;
        }
        if (null != packageUsecaseRealization) {
            this.navigatePackage(usecaseRealizationContentManager, this.rootModel, packageUsecaseRealization);
        }
        this.jobMonitor.endTask();
    }

    /**
     * @see nexcore.tool.uml.ui.project.report.control.ReportHandler#getDefaultTemplateFileName()
     */
    @Override
    protected String getDefaultTemplateFileName() {
        return UICoreConstant.TEMPLATE_XML_USECASE_ANALYSIS_XML;
    }

}
