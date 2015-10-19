/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.project.report.action;

import java.util.List;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.dialog.ProjectItemSelectionDialog;
import nexcore.tool.uml.ui.project.ProjectExplorerPlugin;
import nexcore.tool.uml.ui.project.report.job.CreateClassDefinitionToXMLFileJob;

import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.window.Window;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * 클래스 명세서 액션
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.report.action</li>
 * <li>설 명 : CreateClassDefinitionToXMLFileAction</li>
 * <li>작성일 : 2010. 1. 18.</li>
 * <li>작성자 : 송정훈</li>
 * </ul>
 */
public class CreateClassDefinitionToXMLFileAction extends CommonReportAction {

    /**
     * @see nexcore.tool.uml.ui.project.explorer.action.CommonActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    @Override
    public void run(IAction action) {
        if (!(selectedEObject instanceof Package)) {
            return;
        }

        Package rootElement = (Package) selectedEObject;
        EObject rootModel = EcoreUtil.getRootContainer(selectedEObject);

        ProjectItemSelectionDialog dialog = new ProjectItemSelectionDialog(ProjectExplorerPlugin.getShell(),
            UMLMessage.LABEL_EXPORT_CLASS_DEFINITION_TO_DOC,
            rootElement,
            UMLPackage.Literals.PACKAGE,
            UICoreConstant.REPORT__DOCTYPE_DEFINITION);

        if (dialog.open() == Window.OK) {
            List<Element> selectedElementList = dialog.getSelectedElementList();

            String classDefinitionFileURI = getFileLocation(dialog);

            CreateClassDefinitionToXMLFileJob job = new CreateClassDefinitionToXMLFileJob(rootElement,
                rootModel,
                selectedElementList,
                classDefinitionFileURI,
                getProjectName(),
                templateFile);
            addJobChangeListener(job);
            try {

                job.setUser(true);
                job.setPriority(Job.SHORT);
                job.schedule();
                // job.join();
                
            } catch (Exception e1) {
                super.openErrorDialog();
                Log.error(e1);

            } finally {
                if (job != null) {
                    job = null;
                }
            }
        }
    }

}
