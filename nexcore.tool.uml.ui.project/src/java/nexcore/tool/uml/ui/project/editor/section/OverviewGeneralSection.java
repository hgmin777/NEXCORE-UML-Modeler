/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.project.editor.section;

import java.io.File;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.core.util.DateUtil;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.editor.section.AbstractSection;
import nexcore.tool.uml.ui.project.editor.ModelMultiPageEditor;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.uml2.uml.Model;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.editor.section</li>
 * <li>설 명 : OverviewGeneralSection</li>
 * <li>작성일 : 2009. 12. 14.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class OverviewGeneralSection extends AbstractSection {

    /** " " */
    private static final String BLANK_TEXT = UICoreConstant.PROJECT_CONSTANTS__BLANK;

    /** 모델 인스턴스 */
    private Model model;

    /** 모델 이름 */
    private Text modelNameText;

    /** 모델 위치 */
    private Text modelLocationText;

    /** 모델 크기 */
    private Text modelSizeText;

    /** 모델 마지막 수정 날짜 */
    private Text modelLastModifiedDateText;

    /**
     * 생성자
     * 
     * @param page
     * @param parent
     */
    public OverviewGeneralSection(FormPage page, Composite parent) {
        super(page, parent, Section.TWISTIE | Section.EXPANDED | Section.DESCRIPTION);
    }

    /**
     * @see nexcore.tool.uml.ui.core.editor.section.AbstractSection#createClient(org.eclipse.ui.forms.widgets.Section,
     *      org.eclipse.ui.forms.widgets.FormToolkit)
     */
    @Override
    protected void createClient(Section section, FormToolkit toolkit) {
        section.setText(UMLMessage.LABEL_GENERAL_INFORMATION);
        section.setDescription(UMLMessage.MESSAGE_OVERVIEW);

        TableWrapData td = new TableWrapData(TableWrapData.FILL, TableWrapData.TOP);
        td.grabHorizontal = true;

        Composite client = toolkit.createComposite(section);
        FillLayout layout = new FillLayout(SWT.VERTICAL);
        layout.marginWidth = toolkit.getBorderStyle() != SWT.NULL ? 0 : 2;
        client.setLayout(layout);

        Composite composite = toolkit.createComposite(client);
        GridLayout compositeLayout = new GridLayout();
        compositeLayout.numColumns = 3;
        composite.setLayout(compositeLayout);

        createModelNameControl(toolkit, composite);
        createModelLocationControl(toolkit, composite);
        createModelSizeControl(toolkit, composite);
        createModelLastModifiedDateControl(toolkit, composite);

        initializeSection();

        section.setClient(client);
    }

    /**
     * 모델 이름 컨트롤 생성
     * 
     * @param toolkit
     * @param composite
     *            void
     */
    private void createModelNameControl(FormToolkit toolkit, Composite composite) {
        toolkit.createLabel(composite, UMLMessage.LABEL_NAME + UICoreConstant.PROJECT_CONSTANTS__COLON, SWT.NONE);
        modelNameText = toolkit.createText(composite, "", SWT.SINGLE | SWT.BORDER); //$NON-NLS-1$
        GridData modelNameTextData = new GridData(GridData.FILL_HORIZONTAL);
        modelNameTextData.horizontalSpan = 2;
        modelNameText.setLayoutData(modelNameTextData);
    }

    /**
     * 모델 위치 컨트롤 생성
     * 
     * @param toolkit
     * @param composite
     *            void
     */
    private void createModelLocationControl(FormToolkit toolkit, Composite composite) {
        toolkit.createLabel(composite, UMLMessage.LABEL_FILE_LOCATION, SWT.NONE);
        modelLocationText = toolkit.createText(composite, "", SWT.SINGLE | SWT.BORDER); //$NON-NLS-1$
        GridData modelLocationTextData = new GridData(GridData.FILL_HORIZONTAL);
        modelLocationTextData.horizontalSpan = 2;
        modelLocationText.setLayoutData(modelLocationTextData);
    }

    /**
     * 모델 크기 컨트롤 생성
     * 
     * @param toolkit
     * @param composite
     *            void
     */
    private void createModelSizeControl(FormToolkit toolkit, Composite composite) {
        toolkit.createLabel(composite, UMLMessage.LABEL_FILE_SIZE, SWT.NONE);
        modelSizeText = toolkit.createText(composite, "", SWT.SINGLE | SWT.BORDER); //$NON-NLS-1$
        GridData modelSizeTextData = new GridData(GridData.FILL_HORIZONTAL);
        modelSizeTextData.horizontalSpan = 2;
        modelSizeText.setLayoutData(modelSizeTextData);
    }

    /**
     * 모델 마지막 수정 날짜 생성
     * 
     * @param toolkit
     * @param composite
     *            void
     */
    private void createModelLastModifiedDateControl(FormToolkit toolkit, Composite composite) {
        toolkit.createLabel(composite, UMLMessage.LABEL_FILE_LASTMODIFIEDDATE, SWT.NONE);
        modelLastModifiedDateText = toolkit.createText(composite, "", SWT.SINGLE | SWT.BORDER); //$NON-NLS-1$
        GridData modelLastModifiedDateTextData = new GridData(GridData.FILL_HORIZONTAL);
        modelLastModifiedDateTextData.horizontalSpan = 2;
        modelLastModifiedDateText.setLayoutData(modelLastModifiedDateTextData);
    }

    /**
     * 섹션 초기화
     * 
     * void
     */
    private void initializeSection() {
        model = ((ModelMultiPageEditor) getPage().getEditor()).getModel();

        modelNameText.setText(model.getName());

        IFile modelFile = WorkspaceSynchronizer.getFile(model.eResource());
        modelLocationText.setText(modelFile.getFullPath().toPortableString());

        IPath rootLocation = ResourcesPlugin.getWorkspace().getRoot().getLocation();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(rootLocation.toString());
        stringBuilder.append(modelFile.getFullPath().toString());
        File file = new File(stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append(String.valueOf(file.length()));
        stringBuilder.append(BLANK_TEXT);
        stringBuilder.append(UMLMessage.getMessage(UMLMessage.LABEL_BYTE));
        modelSizeText.setText(stringBuilder.toString());
        modelLastModifiedDateText.setText(DateUtil.convertLongTimeStampToString(modelFile.getLocalTimeStamp()));
    }

    /**
     * 섹션의 위젯들의 Enable 여부 설정
     * 
     * @param enable
     *            void
     */
    public void setEnabled(boolean enabled) {
        // 모델 이름 Enable 여부 설정
        modelNameText.setEnabled(enabled);
        // 모델 위치 Enable 여부 설정
        modelLocationText.setEnabled(enabled);
        // 모델 크기 Enable 여부 설정
        modelSizeText.setEnabled(enabled);
        // 모델 마지막 수정 날짜 Enable 여부 설정
        modelLastModifiedDateText.setEnabled(enabled);
    }

}
