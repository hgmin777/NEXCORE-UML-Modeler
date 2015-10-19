/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.property.section;

import nexcore.tool.uml.core.message.UMLMessage;

import org.eclipse.uml2.uml.Classifier;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.section</li>
 * <li>설  명 : AbstractGeneralSection</li>
 * <li>작성일 : 2009. 12. 23.</li>
 * <li>작성자 : 변영민</li>
 * </ul>
 */
public class AbstractGeneralSection extends TemplateCheckButtonSection {

    /**
     * @see nexcore.tool.uml.ui.property.section.TemplateCheckButtonSection#getLabelText()
     */
    protected String getLabelText() {
        return UMLMessage.getMessage(UMLMessage.LABEL_ABSTRACT);
    }

    /**
     * 
     * 
     * @return Classifier
     */
    private Classifier getData() {
        return (Classifier) this.getSelectedModel();
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.TemplateCheckButtonSection#get()
     */
    @Override
    protected boolean get() {
        return this.getData().isAbstract();
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.TemplateCheckButtonSection#set(boolean)
     */
    @Override
    protected void set(boolean value) {
        this.getData().setIsAbstract(value);
    }

}
