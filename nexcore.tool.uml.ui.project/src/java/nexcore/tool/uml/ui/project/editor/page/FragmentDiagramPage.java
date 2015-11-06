/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.project.editor.page;

import nexcore.tool.uml.ui.project.editor.section.DiagramSection;
import nexcore.tool.uml.ui.project.editor.section.FragmentSection;

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
 * <li>설  명 : FragmentDiagramPage</li>
 * <li>작성일 : 2012. 1. 13.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public class FragmentDiagramPage extends FormPage implements IFormPage {

    /** IManagedForm 인스턴스 */
    private IManagedForm managedForm;
    
    /**
     * fragmentSection
     */
    private FragmentSection fragmentSection = null;
    
    /**
     * diagramSection
     */
    private DiagramSection diagramSection = null;

    
    /**
     * 생성자
     * 
     * @param id
     * @param title
     */
    public FragmentDiagramPage(String id, String title) {
        super(id, title);
    }

    /**
     * 생성자
     * 
     * @param editor
     * @param id
     * @param title
     */
    public FragmentDiagramPage(FormEditor editor, String id, String title) {
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
        
        createFragmentSection(body);
        createDiagramSection(body);
    }

    
    /**
     * 
     *  
     * @param body void
     */
    private void createFragmentSection(Composite parent) {
        
        fragmentSection = new FragmentSection(this, parent);
        managedForm.addPart(fragmentSection);

        TableWrapData td = new TableWrapData(TableWrapData.FILL_GRAB);
        td.grabHorizontal = true;
        td.grabVertical = true;
        td.maxWidth = 50;

        fragmentSection.getSection().setLayoutData(td);
        
    }
    
    
    /**
     * 
     *  
     * @param body void
     */
    private void createDiagramSection(Composite parent) {

        diagramSection = new DiagramSection(this, parent);
        managedForm.addPart(diagramSection);

        TableWrapData td = new TableWrapData(TableWrapData.FILL_GRAB);
        td.grabHorizontal = true;
        td.grabVertical = true;
        td.maxWidth = 50;

        diagramSection.getSection().setLayoutData(td);
        
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
