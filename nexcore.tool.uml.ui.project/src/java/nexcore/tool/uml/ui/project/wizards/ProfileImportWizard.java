/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.project.wizards;

import nexcore.tool.uml.core.message.UMLMessage;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.wizards</li>
 * <li>설  명 : ProfileImportWizard</li>
 * <li>작성일 : 2009. 12. 10.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class ProfileImportWizard extends Wizard implements IImportWizard {

    /** 위자드 페이지 */
    ProfileImportWizardPage mainPage;

    /**
     * 생성자
     */
    public ProfileImportWizard() {
        super();
    }

    /**
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    public boolean performFinish() {
        IFile file = mainPage.createNewFile();
        if (file == null)
            return false;
        return true;
    }

    /**
     * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
     *      org.eclipse.jface.viewers.IStructuredSelection)
     */
    public void init(IWorkbench workbench, IStructuredSelection selection) {
        setWindowTitle(UMLMessage.LABEL_IMPORT_UMLPROFILE);
        setNeedsProgressMonitor(true);
        mainPage = new ProfileImportWizardPage(UMLMessage.LABEL_UMLPROFILE, selection); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.jface.wizard.Wizard#addPages()
     */
    public void addPages() {
        super.addPages();
        addPage(mainPage);
    }

}
