/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.project.editor.page;

import nexcore.tool.uml.ui.project.editor.section.DetailsModelLibrarySection;
import nexcore.tool.uml.ui.project.editor.section.DetailsProfileSection;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IFormPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.editor.IFormPage;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.editor.page</li>
 * <li>설 명 : DetailsPage</li>
 * <li>작성일 : 2009. 12. 11.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class DetailsPage extends FormPage implements IFormPage {

    /** IManagedForm 인스턴스 */
    private IManagedForm managedForm;

    /** 상세 - 프로파일 정보 섹션 인스턴스 */
    private DetailsProfileSection profileSection = null;

    /** 상세 - 모델 라이브러리 섹션 인스턴스 */
    private DetailsModelLibrarySection librarySection = null;

    /**
     * 생성자
     * 
     * @param id
     * @param title
     */
    public DetailsPage(String id, String title) {
        super(id, title);
    }

    /**
     * 생성자
     * 
     * @param editor
     * @param id
     * @param title
     */
    public DetailsPage(FormEditor editor, String id, String title) {
        super(editor, id, title);
    }

    /**
     * @see org.eclipse.ui.forms.editor.FormPage#createFormContent(org.eclipse.ui.forms.IManagedForm)
     */
    @Override
    protected void createFormContent(IManagedForm managedForm) {
        this.managedForm = managedForm;

        super.createFormContent(managedForm);

        createSectionComposite();

        // managedForm.refresh();
    }

    /**
     * 섹션 컴포짓 생성
     * 
     * void
     */
    private void createSectionComposite() {
        Composite body = managedForm.getForm().getBody();
        TableWrapLayout layout = new TableWrapLayout();
        layout.topMargin = 10;
        layout.bottomMargin = 10;
        layout.leftMargin = 10;
        layout.rightMargin = 10;
        layout.verticalSpacing = 10;
        layout.horizontalSpacing = 10;
        body.setLayout(layout);

        createDetailsProfileSection(body);
        createDetailsModelLibrarySection(body);
    }

    /**
     * 상세 - 프로파일 정보 섹션 생성
     * 
     * @param managedForm2
     * @param parent
     *            void
     */
    private void createDetailsProfileSection(Composite parent) {
        profileSection = new DetailsProfileSection(this, parent);
        managedForm.addPart(profileSection);

        TableWrapData td = new TableWrapData(TableWrapData.FILL_GRAB);
        td.grabHorizontal = true;
        td.grabVertical = true;
        td.maxWidth = 50;

        profileSection.getSection().setLayoutData(td);
    }

    /**
     * 상세 - 모델 라이브러리 섹션 생성
     * 
     * @param parent
     *            void
     */
    private void createDetailsModelLibrarySection(Composite parent) {
        librarySection = new DetailsModelLibrarySection(this, parent);
        managedForm.addPart(librarySection);

        TableWrapData td = new TableWrapData(TableWrapData.FILL_GRAB);
        td.grabHorizontal = true;
        td.grabVertical = true;
        td.maxWidth = 50;

        librarySection.getSection().setLayoutData(td);
    }

    /**
     * Page에 추가된 섹션들 반환
     * 
     * @return IFormPart[]
     */
    public IFormPart[] getSections() {
        if (managedForm != null) {
            return managedForm.getParts();
        }

        return null;
    }

}
