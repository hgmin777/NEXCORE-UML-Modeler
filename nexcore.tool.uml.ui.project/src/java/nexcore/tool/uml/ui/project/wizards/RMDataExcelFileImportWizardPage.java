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

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.wizards</li>
 * <li>설  명 : RMDataExcelFileImportWizardPage</li>
 * <li>작성일 : 2010. 5. 11.</li>
 * <li>작성자 : 최윤석</li>>
 * </ul>
 */
public class RMDataExcelFileImportWizardPage extends WizardNewFileCreationPage {

    /** 파일 필드 에디터 */
    protected FileFieldEditor editor;

    /**
     * 생성자
     * 
     * @param pageName
     * @param selection
     */
    public RMDataExcelFileImportWizardPage(String pageName, IStructuredSelection selection) {
        super(pageName, selection);
        setTitle(pageName);
        setDescription(UMLMessage.MESSAGE_IMPORT_RM_DATA_EXCEL_FILE_INTO_WORKSPACE);

        /**
         * 일감번호 #6695 - RM 데이터 엑셀 파일 가져오기 기능 개선
         * http://203.235.211.94/redmine/issues/6695
         * 
         * Modified by yschoi. 20100805
         * 
         * 현상 : RM 데이터 엑셀 파일은 실제 경로에 가져와서, 중복 검사시 화일이 존재해서 새 RMData.rmd 로 정보를
         * 갱신하지 않는 문제 발생 해결방법 : RM 데이터 엑셀 파일은 실제 경로에 가져오지 않고, RMData.rmd 파일
         * 생성시에만 참조하도록 수정
         */
        setAllowExistingResources(true);
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
                IPath path = new Path(RMDataExcelFileImportWizardPage.this.editor.getStringValue());
                setFileName(path.lastSegment());
            }
        });

        editor.setFileExtensions(UICoreConstant.PROJECT_CONSTANTS__IMPORT_RM_DATA_EXCEL_FILE_EXTENSIONS);
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
        return UMLMessage.LABEL_SELECTED_RM_DATA_EXCEL_FILE;
    }

    /**
     * @see org.eclipse.ui.dialogs.WizardNewFileCreationPage#validateLinkedResource()
     */
    protected IStatus validateLinkedResource() {
        return new Status(IStatus.OK, "nexcore.tool.uml.ui.project", IStatus.OK, "", null); //$NON-NLS-1$ //$NON-NLS-2$
    }
}
