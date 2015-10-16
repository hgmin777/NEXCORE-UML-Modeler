/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.project.editor.page;

import nexcore.tool.uml.ui.project.editor.section.OverviewDocumentSection;
import nexcore.tool.uml.ui.project.editor.section.OverviewGeneralSection;
import nexcore.tool.uml.ui.project.editor.section.OverviewModelDetailSection;
import nexcore.tool.uml.ui.project.editor.section.OverviewProjectInformationSection;

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
 * <li>설 명 : OverviewPage</li>
 * <li>작성일 : 2009. 12. 11.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class OverviewPage extends FormPage implements IFormPage {

    /** IManagedForm 인스턴스 */
    private IManagedForm managedForm;

    /** 개요 - 일반 정보 섹션 인스턴스 */
    private OverviewGeneralSection generalSection = null;

    /** 개요 - 모델 상세 정보 섹션 */
    private OverviewModelDetailSection modelDetailSection = null;

    /** 개요 - 프로젝트 정보 섹션 */
    private OverviewProjectInformationSection projectInformation = null;

    /** 개요 - 문서 섹션 인스턴스 */
    private OverviewDocumentSection documentSection = null;

    /**
     * 생성자
     * 
     * @param id
     * @param title
     */
    public OverviewPage(String id, String title) {
        super(id, title);
    }

    /**
     * 생성자
     * 
     * @param editor
     * @param id
     * @param title
     */
    public OverviewPage(FormEditor editor, String id, String title) {
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

        createOverviewGeneralSection(body);
        // createOverviewModelDetailSection(body);
        // createProjectInformationSection(body);
        createOverviewDocumentSection(body);

        generalSection.setEnabled(false);
    }

    /**
     * 개요 - 일반 정보 섹션 생성
     * 
     * @param parent
     *            void
     */
    private void createOverviewGeneralSection(Composite parent) {
        generalSection = new OverviewGeneralSection(this, parent);
        managedForm.addPart(generalSection);
        TableWrapData td = new TableWrapData(TableWrapData.FILL_GRAB);
        td.grabHorizontal = true;
        td.grabVertical = true;
        td.maxWidth = 50;
        generalSection.getSection().setLayoutData(td);
    }

    /**
     * 개요 - 모델 상세 정보 섹션 생성
     * 
     * @param parent
     *            void
     */
    @SuppressWarnings("unused")
    private void createOverviewModelDetailSection(Composite parent) {
        modelDetailSection = new OverviewModelDetailSection(this, parent);
        managedForm.addPart(modelDetailSection);
        TableWrapData td = new TableWrapData(TableWrapData.FILL_GRAB);
        td.grabHorizontal = true;
        td.grabVertical = true;
        td.maxWidth = 50;
        modelDetailSection.getSection().setLayoutData(td);

    }

    /**
     * 개요 - 프로젝트 정보 섹션 생성
     * 
     * @param parent
     *            void
     */
    @SuppressWarnings("unused")
    private void createProjectInformationSection(Composite parent) {
        projectInformation = new OverviewProjectInformationSection(this, parent);
        managedForm.addPart(projectInformation);
        TableWrapData tableWrapData = new TableWrapData(TableWrapData.FILL_GRAB);
        tableWrapData.grabHorizontal = true;
        tableWrapData.grabVertical = true;
        tableWrapData.maxWidth = 50;
        projectInformation.getSection().setLayoutData(tableWrapData);
    }

    /**
     * 개요 - 문서 섹션 생성
     * 
     * @param parent
     *            void
     */
    private void createOverviewDocumentSection(Composite parent) {
        documentSection = new OverviewDocumentSection(this, parent);
        managedForm.addPart(documentSection);
        TableWrapData td = new TableWrapData(TableWrapData.FILL_GRAB);
        td.grabHorizontal = true;
        td.grabVertical = true;
        td.maxWidth = 50;
        documentSection.getSection().setLayoutData(td);
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
