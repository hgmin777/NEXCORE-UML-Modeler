/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.property.section;

import nexcore.tool.uml.ui.core.UICoreConstant;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.UMLFactory;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.section</li>
 * <li>설  명 : DocumentSection</li>
 * <li>작성일 : 2009. 12. 28.</li>
 * <li>작성자 : 변영민</li>
 * </ul>
 */
public class DocumentSection extends TemplateTextSection {

    /** comment */
    protected Comment comment;

    /**
     * 입력된 모델을 Element 로 리턴함.
     * 
     * @return Element
     */
    private Element getData() {
        return (Element) this.getSelectedModel();
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#createMainComposite()
     */
    @Override
    public void createMainComposite() {
        gridData = new GridData(GridData.FILL_BOTH);
        parent.setLayoutData(gridData);
        sectionComposite = getWidgetFactory().createComposite(parent);
        sectionLayout = new GridLayout();
        sectionComposite.setLayout(sectionLayout);
        this.createTextControl();
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.TemplateTextSection#createTextControl()
     */
    @Override
    protected void createTextControl() {
        text = getWidgetFactory().createText(sectionComposite,
            UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING,
            SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
        gridData = new GridData(GridData.FILL_BOTH);
        text.setLayoutData(gridData);
        activatedSection = this.getClass();
        text.setMessage(activatedSection.getSimpleName());

//        text.addModifyListener(new ModifyListener() {
//            @Override
//            public void modifyText(ModifyEvent e) {
//                DomainUtil.run(new TransactionalAction() {
//                    /**
//                     * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
//                     */
//                    @Override
//                    public void doExecute() {
//                        try {
//                            set(text.getText());
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//            }
//        });
        
    }
    

    /**
     * @see nexcore.tool.uml.ui.property.section.TemplateTextSection#get()
     */
    @Override
    protected String get() {
        if (this.getData().getOwnedComments().isEmpty()) {
            return UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;
        } else {
            return this.getData().getOwnedComments().get(0).getBody();
        }
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.TemplateTextSection#getLabelText()
     */
    @Override
    protected String getLabelText() {
        return null;
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.TemplateTextSection#set(java.lang.String)
     */
    @Override
    protected void set(final String value) {
        try {
            if (UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING.equals(value)) {
                addComment(value);
            } else {
                addComment(value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * 
     * @param value
     *            void
     */
    protected void addComment(final String value) {
        if (value != null) {
            if( this.getData() != null && this.getData().getOwnedComments() != null && !this.getData().getOwnedComments().isEmpty() ) {
                comment = this.getData().getOwnedComments().get(0);
                comment.setBody(value);
            } else {
                comment = UMLFactory.eINSTANCE.createComment();
                comment.setBody(value);
                
                this.getData().getOwnedComments().clear();
                this.getData().getOwnedComments().add(comment);
            }
        }
    }

}
