/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.project.wizards;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
 * 
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.wizards</li>
 * <li>설 명 : UMLProjectSelectionListener</li>
 * <li>작성일 : 2011. 10. 14.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class UMLProjectSelectionListener extends SelectionAdapter {

    /**
     * wizardPage
     */
    private UMLWizardNewProjectCreationPage wizardPage;

    /**
     * UMLProjectSelectionListener
     * @param umlWizardNewProjectCreationPage
     */
    public UMLProjectSelectionListener(UMLWizardNewProjectCreationPage umlWizardNewProjectCreationPage) {
        this.wizardPage = umlWizardNewProjectCreationPage;
    }

    /**
     * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
     */
    @Override
    public void widgetSelected(SelectionEvent e) {
//        if (!ALMPreference.useCollaboration()) {
//            MessageDialog.openInformation(wizardPage.getShell(), UMLMessage.LABEL_NOTIFICATION, Messages.MESSAGE_COLLABORATION_ERROR);
//            return;
//        }
//        List<Project> projects = null;
//        try {
//            projects = MetaContentUtil.getProjects("-");
//        } catch (Throwable e1) {
//            ALMLogger.getLog(ProjectExplorerPlugin.PLUGIN_ID).error(e1.getMessage(), e1);
//            MessageDialog.openError(wizardPage.getShell(), UMLMessage.TITLE_ERROR, e1.getMessage());
//        } 
//
//        if (null == projects) {
//            return;
//        }
//
//        ALMProjectSelectionDialog dialog = new ALMProjectSelectionDialog(wizardPage.getShell(), projects);
//
//        if (dialog.open() == Dialog.OK) {
//            Project result = dialog.getSelectedContens();
//            if (result != null) {
//                wizardPage.selectedProject = result;
//                wizardPage.projectNameField.setText(wizardPage.selectedProject.getName());
//                wizardPage.projectCode.setText(wizardPage.selectedProject.getProjectCode());
//                wizardPage.description.setText(wizardPage.selectedProject.getDescription());
//            }
//        }
    }
}
