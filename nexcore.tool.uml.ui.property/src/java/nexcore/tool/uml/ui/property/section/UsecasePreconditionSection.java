/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.property.section;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.model.usecasedetail.UseCaseDetail;
import nexcore.tool.uml.model.usecasedetail.UseCaseDetailFactory;
import nexcore.tool.uml.ui.core.UICoreConstant;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.uml2.uml.UseCase;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.section</li>
 * <li>설  명 : UsecasePreconditionSection</li>
 * <li>작성일 : 2010. 5. 19.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
public class UsecasePreconditionSection extends TemplateTextSection {

    /**
     * @see nexcore.tool.uml.ui.property.section.TemplateTextSection#createTextControl()
     */
    @Override
    protected void createTextControl() {
        this.text = getWidgetFactory().createText(this.sectionComposite,
            UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING,
            SWT.MULTI | SWT.BORDER | SWT.V_SCROLL);
        gridData = new GridData(GridData.FILL_BOTH);
        gridData.heightHint = UICoreConstant.UMLSECTION_CONSTANTS___TEXT_OF_DESCRIPTION_HEIGHT;
        this.text.setLayoutData(gridData);
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.TemplateTextSection#get()
     */
    @Override
    protected String get() {
        EAnnotation annotation = getData().getEAnnotation(UICoreConstant.PROJECT_CONSTANTS__USECASE_DETAIL);
        if (annotation != null && annotation instanceof UseCaseDetail) {
            return ((UseCaseDetail) annotation).getPreCondition();
        }
        return UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;
    }

    /**
     * getData
     *  
     * @return UseCase
     */
    private UseCase getData() {
        return (UseCase) this.getSelectedModel();
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.TemplateTextSection#getLabelText()
     */
    @Override
    protected String getLabelText() {
        return UMLMessage.LABEL_PRECONDITION;
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.TemplateTextSection#set(java.lang.String)
     */
    @Override
    protected void set(String value) {
        try {
            if (value == null)
                return;
            EAnnotation annotation = getData().getEAnnotation(UICoreConstant.PROJECT_CONSTANTS__USECASE_DETAIL);
            if (null == annotation) {
                UseCaseDetail detail = UseCaseDetailFactory.eINSTANCE.createUseCaseDetail();
                detail.setSource(UICoreConstant.PROJECT_CONSTANTS__USECASE_DETAIL);
                detail.setPreCondition(value);
                getData().getEAnnotations().add(detail);

            } else {
                if (annotation instanceof UseCaseDetail) {
                    ((UseCaseDetail) annotation).setPreCondition(value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
