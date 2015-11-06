/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.project.wizards;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.project.ProjectExplorerPlugin;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.uml2.uml.resource.UMLResource;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.wizards</li>
 * <li>설 명 : ProfileImportWizardPage</li>
 * <li>작성일 : 2009. 12. 10.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class ProfileImportWizardPage extends WizardNewFileCreationPage {

    /** 파일 필드 에디터 */
    protected FileFieldEditor editor;
    
    /** 가져올 프로파일의 존재 여부 */
    boolean isSourceExist = false;

    /**
     * 생성자
     * 
     * @param pageName
     * @param selection
     */
    public ProfileImportWizardPage(String pageName, IStructuredSelection selection) {
        super(pageName, selection);
        setTitle(pageName);
        setDescription(UMLMessage.MESSAGE_IMPORT_UMLPROFILE_INTO_WORKSPACE);
    }

    /**
     * @see org.eclipse.ui.dialogs.WizardNewFileCreationPage#createAdvancedControls(org.eclipse.swt.widgets.Composite)
     */
    protected void createAdvancedControls(Composite parent) {
        Composite fileSelectionArea = new Composite(parent, SWT.NONE);
        GridData fileSelectionData = new GridData(GridData.GRAB_HORIZONTAL | GridData.FILL_HORIZONTAL);
        fileSelectionArea.setLayoutData(fileSelectionData);

        GridLayout fileSelectionLayout = new GridLayout();
        fileSelectionLayout.numColumns = 3;
        fileSelectionLayout.makeColumnsEqualWidth = false;
        fileSelectionLayout.marginWidth = 0;
        fileSelectionLayout.marginHeight = 0;
        fileSelectionArea.setLayout(fileSelectionLayout);

        editor = new FileFieldEditor("fileSelect", UMLMessage.LABEL_SELECT_FILE, fileSelectionArea); //$NON-NLS-1$
        editor.getTextControl(fileSelectionArea).addModifyListener(new ModifyListener() {
            /**
             * @see org.eclipse.swt.events.ModifyListener#modifyText(org.eclipse.swt.events.ModifyEvent)
             */
            public void modifyText(ModifyEvent e) {
                IPath path = new Path(ProfileImportWizardPage.this.editor.getStringValue());
                isSourceExist = true;
                setFileExtension(path.getFileExtension());
                setFileName(path.lastSegment());
            }
        });

        editor.setFileExtensions(UICoreConstant.PROJECT_CONSTANTS__UML_PROFILE_FILE_EXTENSIONS);
        fileSelectionArea.moveAbove(null);

    }

    /**
     * @see org.eclipse.ui.dialogs.WizardNewFileCreationPage#createLinkTarget()
     */
    protected void createLinkTarget() {
    }

    /**
     * @see org.eclipse.ui.dialogs.WizardNewFileCreationPage#getInitialContents()
     */
    protected InputStream getInitialContents() {
        try {
            return new FileInputStream(new File(editor.getStringValue()));
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    /**
     * @see org.eclipse.ui.dialogs.WizardNewFileCreationPage#getNewFileLabel()
     */
    protected String getNewFileLabel() {
        return UMLMessage.LABEL_SELECTED_UMLPROFILE;
    }

    /**
     * @see org.eclipse.ui.dialogs.WizardNewFileCreationPage#validateLinkedResource()
     */
    protected IStatus validateLinkedResource() {
    	
    	String filename = getFileName();
    	if (filename != null) {
	    	if (filename.endsWith(UICoreConstant.PROJECT_CONSTANTS__DOT + UICoreConstant.RSA_PROFILE_FILE_EXTENSION)
	    			|| filename.endsWith(UICoreConstant.PROJECT_CONSTANTS__DOT + UMLResource.PROFILE_FILE_EXTENSION)) {
	    		if (isSourceExist) {
	    			return new Status(IStatus.OK, ProjectExplorerPlugin.PLUGIN_ID, 
	    					IStatus.OK, UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING, null);
	    		}
	    	}
    	}
    	
		setErrorMessage(UMLMessage.MESSAGE_NOT_VALID_PROFILE_NAME);
		return new Status(IStatus.ERROR, ProjectExplorerPlugin.PLUGIN_ID,
				UMLMessage.MESSAGE_NOT_VALID_PROFILE_NAME);
    	
    }

}
