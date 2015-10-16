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

import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;


/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.section</li>
 * <li>설  명 : DefaultValueGeneralSection</li>
 * <li>작성일 : 2009. 12. 29.</li>
 * <li>작성자 : 변영민</li>
 * </ul>
 */
public class DefaultValueGeneralSection extends TemplateTextSection {

    /** defaultValueLabel */
    private CLabel defaultValueLabel;

    /** defaultValueText */
    // private Text defaultValueText;
    /**
     * Property 모델을 리턴.
     * 
     * @return Property
     */
    private Property getData() {
        return (Property) this.getSelectedModel();
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#createMainComposite()
     */
    @Override
    public void createMainComposite() {
        sectionComposite = getWidgetFactory().createComposite(parent);
        sectionLayout = new GridLayout(3, false);
        sectionComposite.setLayout(sectionLayout);

        defaultValueLabel = getWidgetFactory().createCLabel(sectionComposite,
            UMLMessage.getMessage(UMLMessage.LABEL_DEFAULT_VALUE) + UICoreConstant.UMLSECTION_CONSTANTS__COLON_TEXT); //$NON-NLS-1$
        gridData = new GridData();
        gridData.widthHint = UICoreConstant.UMLSECTION_CONSTANTS___LABEL_WIDTH_HINT_FOR_RECTANGLE;
        defaultValueLabel.setLayoutData(gridData);
        text = getWidgetFactory().createText(sectionComposite, UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING); //$NON-NLS-1$
        gridData = new GridData(GridData.FILL_BOTH);
        gridData.horizontalSpan = 2;
        text.setLayoutData(gridData);
        // defaultValueText.setLayoutData(gridData);

    }
    
    /**
     * @see nexcore.tool.uml.ui.property.section.TemplateTextSection#refreshChildren()
     */
    @Override
    public void refreshChildren() {
        if (null == this.text || this.text.isDisposed()) {
            return;
        }
        String str = this.get();
        if (null != str && !str.equals(this.text.getText())) {
            this.text.setText(str);
        } else if (str == null) {
            this.text.setText("");
        }

        Property property = (Property) getData();
        Type type = property.getType();
        
        if(type instanceof org.eclipse.uml2.uml.Class) {
            this.text.setEnabled(false);
        } else {
            this.text.setEnabled(true);            
        }
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.TemplateTextSection#get()
     */
    @Override
    protected String get() {
        Property property = (Property) getData();
        if (property.getDefault() != null)
            return property.getDefault();
        else
            return "";

    }

    /**
     * 
     * 
     * @param value
     */
    @Override
    protected void set(String value) {
        Property property = (Property) getData();
        if (value != null) {
            property.setDefault(value);

        }

        else {
            property.setDefault("");
        }
    }

    /**
     * validate
     *  
     * @param type
     * @param value
     * @return boolean
     */
    public static boolean validate(String type, String value) {
        if ("Integer".equals(type) || "int".equals(type) || "short".equals(type)) {
            try {
                Integer.parseInt(value);
            } catch (NumberFormatException e) {
                return false;
            }

        }
        if (type != null && UICoreConstant.PROJECT_CONSTANTS__BOOLEAN.equals(type.toLowerCase())) {
            if (!("true".equals(value) || "false".equals(value) || "TRUE".equals(value) || "FALSE".equals(value)))
                return false;

        }
        return true;
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.TemplateTextSection#createTextControl()
     */
    @Override
    protected void createTextControl() {
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.TemplateTextSection#getLabelText()
     */
    @Override
    protected String getLabelText() {
        return null;
    }

}
