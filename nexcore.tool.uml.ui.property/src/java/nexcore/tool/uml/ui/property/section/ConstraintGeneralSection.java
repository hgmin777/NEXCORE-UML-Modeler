/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.property.section;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.ui.core.UICoreConstant;

import org.eclipse.swt.layout.GridData;
import org.eclipse.uml2.uml.InteractionConstraint;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.section</li>
 * <li>설  명 : ConstraintGeneralSection</li>
 * <li>작성일 : 2011. 6. 3.</li>
 * <li>작성자 : zerotae</li>
 * </ul>
 */
public class ConstraintGeneralSection extends TemplateTextSection {

    /**
     * 입력된 모델을 NamedElement 로 리턴함.
     * 
     * @return NamedElement
     */
    private NamedElement getData() {
        return (NamedElement) this.getSelectedModel();
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.TemplateTextSection#get()
     */
    @Override
    protected String get() {
        InteractionConstraint constraint = (InteractionConstraint) this.getData();
        OpaqueExpression expression = (OpaqueExpression) constraint.getSpecification();
        return expression.getBodies().get(0);
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.TemplateTextSection#getLabelText()
     */
    @Override
    protected String getLabelText() {
        return UMLMessage.getMessage(UMLMessage.LABEL_CONSTRAINT);
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.TemplateTextSection#createTextControl()
     */
    @Override
    protected void createTextControl() {
        text = getWidgetFactory().createText(sectionComposite, UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING);
        gridData = new GridData(GridData.FILL_HORIZONTAL);
        text.setLayoutData(gridData);
    }
    
    /**
     * @see nexcore.tool.uml.ui.property.section.TemplateTextSection#set(java.lang.String)
     */
    @Override
    protected void set(final String value) {
        try {            
            InteractionConstraint constraint = (InteractionConstraint) this.getData();
            OpaqueExpression expression = (OpaqueExpression) constraint.getSpecification();
            expression.getBodies().set(0, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /** EMPTY_TEXT */
    private String EMPTY_TEXT = "";
    
}
