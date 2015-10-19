/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.project.report.manager.impl;

import java.util.ArrayList;
import java.util.List;

import nexcore.alm.common.word.DataModel;
import nexcore.tool.mdd.core.util.MDDCommonUtil;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.project.report.manager.ReportContentManager;
import nexcore.tool.uml.ui.project.report.util.UMLManipulation;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.report.manager.impl</li>
 * <li>설  명 : PackageContentManager</li>
 * <li>작성일 : 2010. 10. 13.</li>
 * <li>작성자 : 한승일</li>
 * </ul>
 */
public class PackageContentManager extends ReportContentManager {

    /**
     * selectedPackageList
     */
    List<Element> selectedPackageList = null;

    /**
     * @return the selectedPackageList
     */
    public List<Element> getSelectedPackageList() {
        return selectedPackageList;
    }

    /**
     * @param selectedPackageList
     *            the selectedPackageList to set
     */
    public void setSelectedPackageList(List<Element> selectedPackageList) {
        this.selectedPackageList = selectedPackageList;
    }

    /**
     * @see nexcore.tool.uml.ui.project.report.manager.ReportContentManager#createReportContent(org.eclipse.uml2.uml.Element)
     */
    public void createReportContent(DataModel parentDataModel, Package pkg) {
        List<DataModel> packageListModel = new ArrayList<DataModel>();
        DataModel packageModel;
        List<Package> list = UMLManipulation.getAllChildPackageList(pkg);
        for (Package child : list) {
            if (child.getName() == null) {
                continue;
            }
            if (child.eClass().equals(UMLPackage.Literals.MODEL)) {
                continue;
            } else {
                if (UICoreConstant.MODELSEARCH__TYPE_ACTOR.toLowerCase().equals(child.getName().toLowerCase())
                    || MDDCommonUtil.getUsecasesPackageName().toLowerCase().equals(child.getName().toLowerCase())
                    || MDDCommonUtil.getUsecaseRealizationPackageName().toLowerCase().equals(child.getName()
                        .toLowerCase()) || isActorsPackage(child)) {
                    continue;
                }
            }
            if (this.selectedPackageList.contains(child)) {
                packageModel = new DataModel();
                setDataModel(packageModel, UICoreConstant.REPORT__PACKAGE_NAME, child.getName());
                setDataModel(packageModel, UICoreConstant.REPORT__ELEMENT_QUALIFIED_NAME, child.getQualifiedName());
                setDataModel(packageModel,
                    UICoreConstant.REPORT__ELEMENT_DOCUMENTATION,
                    getCommentToString(child.getOwnedComments()));
                // setDataModel(packageModel,
                // UICoreConstant.REPORT__DIAGRAM_LIST, getDiagramList(child));
                packageListModel.add(packageModel);
            }
        }
        if (0 == packageListModel.size()) {
            packageModel = new DataModel();
            // "해당사항 없음" 출력
            setDataModel(packageModel,
                UICoreConstant.REPORT__PACKAGE_NAME,
                applyPreference(UICoreConstant.EMPTY_STRING));
            packageListModel.add(packageModel);
        }
        setDataModel(parentDataModel, UICoreConstant.REPORT__PACKAGE_LIST, packageListModel);
    }

    /**
     * 해당 패키지가 Actors 패키지 하위 패키지인지를 검사한다.
     * 
     * @param child
     * @return boolean
     */
    private boolean isActorsPackage(Package child) {
        if (UMLPackage.Literals.PACKAGE.equals(child.eContainer().eClass())) {
            Package parent = (Package) child.eContainer();
            if (UICoreConstant.MODELSEARCH__TYPE_ACTOR.toLowerCase().equals(parent.getName().toLowerCase())) {
                return true;
            }
            if (UMLPackage.Literals.PACKAGE.equals(parent.eContainer().eClass())) {
                return isActorsPackage(parent);
            }
        }
        return false;
    }

    // /**
    // * 패키지 다이어그램
    // *
    // * @param pkg
    // * @return List<DataModel>
    // */
    // public List<DataModel> getDiagramList(Package pkg) {
    // DataModel dataModel = null;
    // List<DataModel> diagramDataModelList = new ArrayList<DataModel>();
    // List<Diagram> diagramList = UMLManipulation.getDiagramList(pkg,
    // DiagramType.CLASS_DIAGRAM);
    // for (Diagram diagram : diagramList) {
    // dataModel = createDiagramModel(diagram);
    // if (null != dataModel) {
    // diagramDataModelList.add(dataModel);
    // }
    // }
    // return diagramDataModelList;
    // }

}
