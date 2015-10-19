/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.property.section;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.ui.core.UICoreConstant;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.section</li>
 * <li>설  명 : DescriptionOfDiagramGeneralSection</li>
 * <li>작성일 : 2009. 12. 24.</li>
 * <li>작성자 : 한승일</li>
 * </ul>
 */
public class DescriptionOfDiagramGeneralSection extends TemplateTextSection {

    /**
     * 
     * 
     * @return Diagram
     */
    private Diagram getData() {
        return (Diagram) this.getSelectedModel();
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.TemplateTextSection#createTextControl()
     */
    protected void createTextControl() {
        this.text = getWidgetFactory().createText(this.sectionComposite,
            UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING,
            SWT.MULTI | SWT.BORDER | SWT.V_SCROLL);
        gridData = new GridData(GridData.FILL_BOTH);
        gridData.heightHint = UICoreConstant.UMLSECTION_CONSTANTS___TEXT_OF_DESCRIPTION_HEIGHT;
        this.text.setLayoutData(gridData);

        activatedSection = this.getClass();
        text.setMessage(activatedSection.getSimpleName());
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.TemplateTextSection#getLabelText()
     */
    @Override
    protected String getLabelText() {
        return UMLMessage.getMessage(UMLMessage.getMessage(UMLMessage.LABEL_DESCRIPTION));
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.TemplateTextSection#get()
     */
    @Override
    protected String get() {
        return this.getData().getDescription();
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.TemplateTextSection#set(java.lang.String)
     */
    @Override
    protected void set(final String value) {
        try {
            if (value != null)
                getData().setDescription(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
