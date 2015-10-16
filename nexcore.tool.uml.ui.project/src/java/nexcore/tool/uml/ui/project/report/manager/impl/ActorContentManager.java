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
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.DiagramType;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.project.report.manager.ReportContentManager;
import nexcore.tool.uml.ui.project.report.util.UMLManipulation;

import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Package;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.report.manager.impl</li>
 * <li>설  명 : ActorContentManager</li>
 * <li>작성일 : 2010. 10. 13.</li>
 * <li>작성자 : 한승일</li>
 * </ul>
 */
public class ActorContentManager extends ReportContentManager {

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
    public void createReportContent(DataModel targetModel, Package pkg) {

        setDataModel(targetModel, UICoreConstant.REPORT__ACTOR_LIST, getActorList(pkg));

        List<DataModel> diagramListDataModel = getDiagramList(pkg);
        if (0 != diagramListDataModel.size()) {
            setDataModel(targetModel, UICoreConstant.REPORT__DIAGRAM_LIST, diagramListDataModel);
        }

    }

    /**
     * 유스케이스 모형 기술서에서 사용
     * 
     * @param targetModel
     * @param pkg
     *            void
     */
    public void createReportContentFromActorPackage(DataModel targetModel, Package pkg) {

        List<DataModel> actorList = new ArrayList<DataModel>();
        List<DataModel> diagramList = new ArrayList<DataModel>();

        actorList.addAll(getActorList(pkg));
        diagramList.addAll(getDiagramList(pkg));

        List<Package> list = UMLManipulation.getAllChildPackageList(pkg);
        for (Package pack : list) {
            if (selectedPackageList.contains(pack)) {
                actorList.addAll(getActorList(pack));
                diagramList.addAll(getDiagramList(pack));
            }
        }
        setDataModel(targetModel, UICoreConstant.REPORT__ACTOR_LIST, actorList);
        setDataModel(targetModel, UICoreConstant.REPORT__DIAGRAM_LIST, diagramList);

    }

    /**
     * 
     * 
     * @param pkg
     * @return List<DataModel>
     */
    public List<DataModel> getActorList(Package pkg) {
        DataModel dataModel = null;
        List<DataModel> actorDataModelList = new ArrayList<DataModel>();
        List<Actor> actorList = UMLManipulation.getChildActorList(pkg);
        for (Actor actor : actorList) {
            dataModel = createActorDataModel(actor);
            if (null != dataModel) {
                actorDataModelList.add(dataModel);
            }
        }
        return actorDataModelList;
    }

    /**
     * 
     * 
     * @param pkg
     * @return List<DataModel>
     */
    public List<DataModel> getDiagramList(Package pkg) {
        DataModel dataModel = null;
        List<DataModel> diagramDataModelList = new ArrayList<DataModel>();
        List<Diagram> diagramList = UMLManipulation.getDiagramList(pkg, DiagramType.USE_CASE_DIAGRAM);
        for (Diagram diagram : diagramList) {
            dataModel = createDiagramModel(diagram);
            if (null != dataModel) {
                diagramDataModelList.add(dataModel);
            }
        }
        return diagramDataModelList;
    }

    /**
     * 
     * 
     * @param actor
     * @return DataModel
     */
    public DataModel createActorDataModel(Actor actor) {
        if (null == actor) {
            return null;
        }

        DataModel actorDataModel = new DataModel();
        setDataModel(actorDataModel, UICoreConstant.REPORT__ACTOR_NAME, actor.getName());
        setDataModel(actorDataModel,
            UICoreConstant.REPORT__ACTOR_DOCUMENTATION,
            getCommentToString(actor.getOwnedComments()));
        return actorDataModel;
    }

}
