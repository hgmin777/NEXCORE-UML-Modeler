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
import nexcore.tool.uml.ui.property.util.PropertyModelHandler;

import org.eclipse.swt.layout.GridData;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.section</li>
 * <li>설 명 : NameOfGeneralSection</li>
 * <li>작성일 : 2010. 3. 11.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class NameOfGeneralSection extends TemplateTextSection {

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
     * @see nexcore.tool.uml.ui.property.section.TemplateTextSection#get()
     */
    @Override
    protected String get() {
        return PropertyModelHandler.getInstance().getProperName(getData());
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.TemplateTextSection#getLabelText()
     */
    @Override
    protected String getLabelText() {
        return UMLMessage.getMessage(UMLMessage.LABEL_NAME);
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.TemplateTextSection#set(java.lang.String)
     */
    @Override
    protected void set(String value) {
        try {
            if (getData() instanceof Element) {
                ((NamedElement) this.getData()).setName(value);
            } else if (getData() instanceof Diagram) {
                ((Diagram) this.getData()).setName(value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * getData
     *  
     * @return Element
     */
    private Element getData() {
        return this.getSelectedModel();
    }

}
