/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.project.wizards;

import nexcore.tool.uml.core.message.UMLMessage;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.wizards</li>
 * <li>설  명 : RMDataExcelFileImportWizard</li>
 * <li>작성일 : 2010. 5. 11.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class RMDataExcelFileImportWizard extends Wizard implements IImportWizard {

    /** 위자드 페이지 */
    RMDataExcelFileImportWizardPage mainPage;

    /**
     * 생성자
     */
    public RMDataExcelFileImportWizard() {
        super();
    }

    /**
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
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
        /*
         * 엑셀 파일 생성 로직 주석 처리 IFile file = mainPage.createNewFile();
         * 
         * if(file == null) { return false; }
         */

        // RM 데이터를 처리하기 위한 핸들러 생성
//        RMDataExcelFileHandler rmDataExcelFileHandler = new RMDataExcelFileHandler(mainPage.getInitialContents(),
//            mainPage.getContainerFullPath().toString());
//        // RM 데이터 핸들러 실행
//        rmDataExcelFileHandler.execute();

        return true;
    }

    /**
     * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
     *      org.eclipse.jface.viewers.IStructuredSelection)
     */
    public void init(IWorkbench workbench, IStructuredSelection selection) {
        setWindowTitle(UMLMessage.LABEL_IMPORT_RM_DATA_EXCEL_FILE);
        setNeedsProgressMonitor(true);
        mainPage = new RMDataExcelFileImportWizardPage(UMLMessage.LABEL_IMPORT_RM_DATA_EXCEL_FILE, selection); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.jface.wizard.Wizard#addPages()
     */
    public void addPages() {
        super.addPages();
        addPage(mainPage);
    }

}

