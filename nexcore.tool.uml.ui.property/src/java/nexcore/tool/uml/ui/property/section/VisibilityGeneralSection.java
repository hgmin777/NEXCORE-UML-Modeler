/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.property.section;

import nexcore.tool.uml.core.message.UMLMessage;

import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.VisibilityKind;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.section</li>
 * <li>설  명 : VisibilityGeneralSection</li>
 * <li>작성일 : 2009. 12. 23.</li>
 * <li>작성자 : 변영민</li>
 * </ul>
 */
public class VisibilityGeneralSection extends TemplateRadioButtonSection {

    /**
     * 
     * 
     * @return NamedElement
     */
    private NamedElement getData() {
        return (NamedElement) this.getSelectedModel();
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.TemplateRadioButtonSection#get()
     */
    @Override
    protected VisibilityKind get() {
        return this.getData().getVisibility();
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.TemplateRadioButtonSection#set(org.eclipse.uml2.uml.VisibilityKind)
     */
    @Override
    protected void set(final VisibilityKind value) {
        if (value.equals(VisibilityKind.PUBLIC_LITERAL)) {
            getData().setVisibility(VisibilityKind.PUBLIC_LITERAL);
        } else if (value.equals(VisibilityKind.PRIVATE_LITERAL)) {
            getData().setVisibility(VisibilityKind.PRIVATE_LITERAL);
        } else if (value.equals(VisibilityKind.PROTECTED_LITERAL)) {
            getData().setVisibility(VisibilityKind.PROTECTED_LITERAL);
        } else if (value.equals(VisibilityKind.PACKAGE_LITERAL)) {
            getData().setVisibility(VisibilityKind.PACKAGE_LITERAL);
        }
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.TemplateRadioButtonSection#getLabelText()
     */
    @Override
    protected String getLabelText() {
        return UMLMessage.getMessage(UMLMessage.LABEL_VISIBILITY);
    }
}
