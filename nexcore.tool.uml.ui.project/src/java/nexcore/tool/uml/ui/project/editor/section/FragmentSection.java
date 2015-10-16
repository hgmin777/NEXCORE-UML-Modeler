/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.project.editor.section;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.ui.core.editor.section.AbstractSection;
import nexcore.tool.uml.ui.project.editor.ModelMultiPageEditor;
import nexcore.tool.uml.ui.project.editor.composite.FragmentComposite;

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
 * <li>설  명 : FragmentSection</li>
 * <li>작성일 : 2012. 1. 13.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public class FragmentSection extends AbstractSection {

    /** 모델 인스턴스 */
    private Model model;

    /** 프래그먼트 컴포짓 */
    private FragmentComposite fragmentComposite;

    /**
     * 생성자
     * 
     * @param page
     * @param parent
     */
    public FragmentSection(FormPage page, Composite parent) {
        super(page, parent, Section.TWISTIE | Section.EXPANDED | Section.DESCRIPTION);
    }

    /**
     * @see nexcore.tool.uml.ui.core.editor.section.AbstractSection#createClient(org.eclipse.ui.forms.widgets.Section,
     *      org.eclipse.ui.forms.widgets.FormToolkit)
     */
    @Override
    protected void createClient(Section section, FormToolkit toolkit) {
        
        section.setText(UMLMessage.LABEL_FRAGMENTED_PACKAGE_LIST);//"단편화된 패키지 목록");
        section.setDescription(UMLMessage.MESSAGE_FRAGMENTED_PACKAGE_LIST);//"이 섹션에서는 이 모델에 존재하는 단편화된 패키지 리스트와 파일위치를 나열합니다.");

        Composite client = toolkit.createComposite(section);
        FillLayout layout = new FillLayout(SWT.VERTICAL);
        layout.marginWidth = toolkit.getBorderStyle() != SWT.NULL ? 0 : 2;
        client.setLayout(layout);

        Composite composite = toolkit.createComposite(client);
        GridLayout compositeLayout = new GridLayout();
        compositeLayout.numColumns = 2;
        composite.setLayout(compositeLayout);

        initializeSection();

        createFragmentControl(toolkit, composite);

        section.setClient(client);
    }

    /**
     * 프래그먼트 컨트롤러 생성
     * 
     * @param toolkit
     * @param composite
     *            void
     */
    private void createFragmentControl(FormToolkit toolkit, Composite composite) {
        fragmentComposite = new FragmentComposite(this, composite, model);

        GridData gridData = new GridData();
        gridData.horizontalAlignment = GridData.FILL;
        gridData.grabExcessHorizontalSpace = true;
        gridData.verticalAlignment = GridData.FILL;
        gridData.grabExcessVerticalSpace = true;
        
        fragmentComposite.setLayoutData(gridData);
    }

    /**
     * 섹션 초기화
     * 
     * void
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
            fragmentComposite.getFragmentTableViewer().refresh();
            super.commit(onSave);
        }
    }

}
