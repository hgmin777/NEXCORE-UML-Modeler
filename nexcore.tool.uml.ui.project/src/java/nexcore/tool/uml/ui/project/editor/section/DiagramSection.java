/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.project.editor.section;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.ui.core.editor.section.AbstractSection;
import nexcore.tool.uml.ui.project.editor.ModelMultiPageEditor;
import nexcore.tool.uml.ui.project.editor.composite.DiagramComposite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.uml2.uml.Model;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.editor.section</li>
 * <li>설  명 : DiagramSection</li>
 * <li>작성일 : 2012. 1. 13.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public class DiagramSection extends AbstractSection {

    /** 모델 인스턴스 */
    private Model model;

    /** 모델 라이브러리 컴포짓 */
    private DiagramComposite diagramComposite;

    /**
     * 생성자
     * 
     * @param fragmentDiagramPage
     * @param parent
     */
    public DiagramSection(FormPage page, Composite parent) {
        super(page, parent, Section.TWISTIE | Section.EXPANDED | Section.DESCRIPTION);
    }

    /**
     * @see nexcore.tool.uml.ui.core.editor.section.AbstractSection#createClient(org.eclipse.ui.forms.widgets.Section,
     *      org.eclipse.ui.forms.widgets.FormToolkit)
     */
    @Override
    protected void createClient(Section section, FormToolkit toolkit) {
        section.setText(UMLMessage.LABEL_DIAGRAM_LIST);//"다이어그램 목록");
        section.setDescription(UMLMessage.MESSAGE_DIAGRAM_LIST);//"이 섹션은 이 모델에 존재하는 다이어그램을 나열합니다.");

        Composite client = toolkit.createComposite(section);
        FillLayout layout = new FillLayout(SWT.VERTICAL);
        layout.marginWidth = toolkit.getBorderStyle() != SWT.NULL ? 0 : 2;
        client.setLayout(layout);

        Composite composite = toolkit.createComposite(client);
        GridLayout compositeLayout = new GridLayout();
        compositeLayout.numColumns = 2;
        composite.setLayout(compositeLayout);

        initializeSection();

        createDiagramControl(toolkit, composite);

        section.setClient(client);
    }

    /**
     * 다이어그램 컨트롤러 생성
     * 
     * @param toolkit
     * @param composite
     *            void
     */
    private void createDiagramControl(FormToolkit toolkit, Composite composite) {
        diagramComposite = new DiagramComposite(this, composite, model);

        GridData gridData = new GridData();
        gridData.horizontalAlignment = GridData.FILL;
        gridData.grabExcessHorizontalSpace = true;
        gridData.verticalAlignment = GridData.FILL;
        gridData.grabExcessVerticalSpace = true;

        diagramComposite.setLayoutData(gridData);
    }

    /**
     * 섹션 초기화 void
     */
    private void initializeSection() {
        model = ((ModelMultiPageEditor) getPage().getEditor()).getModel();
    }

    /**
     * @see org.eclipse.ui.forms.AbstractFormPart#commit(boolean)
     */
    @Override
    public void commit(boolean onSave) {
        if (onSave) {
            super.commit(onSave);
        }
    }

}
