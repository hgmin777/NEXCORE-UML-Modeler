/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.project.wizards;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.core.util.UMLLoginController;
import nexcore.tool.uml.core.util.UMLNexcoreLoginEvent;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.util.ProjectUtil;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.wizards</li>
 * <li>설 명 : UMLModelWizardPage</li>
 * <li>작성일 : 2009. 11. 24.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
public class UMLModelWizardPage extends WizardNewFileCreationPage {

    /**
     * fileExtension
     */
    private final String fileExtension;

    /**
     * UMLModelWizardPage
     * @param pageName
     * @param selection
     * @param fileExtension
     */
    public UMLModelWizardPage(String pageName, IStructuredSelection selection, String fileExtension) {
        super(pageName, selection);
        setTitle(UMLMessage.LABEL_CREATE_UML_MODEL);
        // setDescription("Select file that will contain diagram and domain models.");
        this.fileExtension = fileExtension;
    }

    /**
     * Override to create files with this extension.
     */
    protected String getExtension() {
        return fileExtension;
    }

    /**
     * getURI
     *  
     * @return URI
     */
    public URI getURI() {
        return URI.createURI(getFilePath().toString());
    }

    /**
     * getFilePath
     *  
     * @return IPath
     */
    protected IPath getFilePath() {
        IPath path = getContainerFullPath();
        if (path == null) {
            path = new Path(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING);
        }
        String fileName = getFileName();
        if (fileName != null) {
            path = path.append(fileName);
        }
        return path;
    }

    /**
     * @see org.eclipse.ui.dialogs.WizardNewFileCreationPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    public void createControl(Composite parent) {
        super.createControl(parent);
        setFileName(ProjectUtil.getUniqueFileName(getContainerFullPath(), getFileName(), getExtension()));
        setPageComplete(validatePage());
    }

    /**
     * @see org.eclipse.ui.dialogs.WizardNewFileCreationPage#validatePage()
     */
    protected boolean validatePage() {

        if (!super.validatePage()) {
            return false;
        }

        if (getContainerFullPath().segmentCount() == 1) {
            setErrorMessage(UMLMessage.MESSAGE_WRONG_SELECTION);
            return false;
        }
        if (getContainerFullPath().lastSegment().equals(UMLMessage.LABEL_SETTINGFOLDER)) {
            setErrorMessage(UMLMessage.MESSAGE_WRONG_SELECTION);
            return false;
        }
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IFolder folder = workspace.getRoot().getFolder(getContainerFullPath());
        if (!folder.exists()) {
            setErrorMessage(UMLMessage.MESSAGE_WRONG_SELECTION);
            return false;
        }

        String extension = getExtension();
        if (extension != null && !getFilePath().toString().endsWith(UICoreConstant.PROJECT_CONSTANTS__DOT + extension)) {
            setErrorMessage(UMLMessage.getMessage(UMLMessage.MESSAGE_WRONG_FILE_EXTENSION, extension));
            return false;
        }
        return true;
    }

}
