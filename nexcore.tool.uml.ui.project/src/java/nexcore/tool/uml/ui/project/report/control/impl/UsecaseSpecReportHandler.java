/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.project.report.control.impl;

import java.util.List;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.project.report.control.ReportHandler;
import nexcore.tool.uml.ui.project.report.manager.impl.ActorContentManager;
import nexcore.tool.uml.ui.project.report.manager.impl.PackageContentManager;
import nexcore.tool.uml.ui.project.report.manager.impl.UsecaseContentManager;
import nexcore.tool.uml.ui.project.report.util.UMLManipulation;

import org.eclipse.core.resources.IFile;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.report.control.impl</li>
 * <li>설  명 : UsecaseSpecReportHandler</li>
 * <li>작성일 : 2010. 10. 12.</li>
 * <li>작성자 : 오은주</li>
 * </ul>
 */
public class UsecaseSpecReportHandler extends ReportHandler {

    /**
     * @param reportLocation
     * @param templateFile
     * @param selectedPackage
     * @param projectName
     */
    public UsecaseSpecReportHandler(String reportLocation, IFile templateFile, List<Element> selectedPackage,
    String projectName) {
        super(reportLocation, templateFile, selectedPackage, projectName);
    }

    /**
     * @see nexcore.tool.uml.ui.project.report.control.ReportHandler#createReport()
     */
    @Override
    protected void createReportData(Model model) {

        try {
            jobMonitor.startTask(UMLMessage.LABEL_CREATE_USECASE_DEFINITION, this.selectedPackageList.size());

            ActorContentManager actorContentManager = new ActorContentManager();
            PackageContentManager packageContentManager = new PackageContentManager();
            UsecaseContentManager usecaseContentManager = new UsecaseContentManager();

            Package packageActor, packageUsecase;

            // 1. Actor 패키지 가져와서 Actor정보 생성
            packageActor = UMLManipulation.findActorPackage(model);
            if (jobMonitor.isCanceled()) {
                return;
            }
            if (null != packageActor) {
                actorContentManager.setSelectedPackageList(this.selectedPackageList);
                actorContentManager.createReportContentFromActorPackage(this.rootModel, packageActor);
                // navigatePackage(actorContentManager, this.rootModel,
                // packageActor);
            }

            // 2. 패키지 구조 정보 생성
            packageContentManager.setSelectedPackageList(this.selectedPackageList);
            packageContentManager.createReportContent(this.rootModel, model);

            // 3. 유스케이스 정보 생성
            packageUsecase = UMLManipulation.findUsecasePackage(model);
            if (jobMonitor.isCanceled()) {
                return;
            }
            if (null != packageUsecase) {
                navigatePackage(usecaseContentManager, this.rootModel, packageUsecase);
            }

            jobMonitor.endTask();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @see nexcore.tool.uml.ui.project.report.control.ReportHandler#getDefaultTemplateFileName()
     */
    @Override
    protected String getDefaultTemplateFileName() {
        return UICoreConstant.TEMPLATE_XML_USECASE_DEFINITION_XML;
    }

}
