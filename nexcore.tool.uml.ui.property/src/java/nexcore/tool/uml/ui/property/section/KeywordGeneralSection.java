/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.property.section;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.core.util.OCLExpressionUtil;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.ui.core.UICoreConstant;

import org.eclipse.emf.common.util.EList;
import org.eclipse.swt.layout.GridData;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.section</li>
 * <li>설  명 : KeywordGeneralSection</li>
 * <li>작성일 : 2009. 12. 23.</li>
 * <li>작성자 : 한승일</li>
 * </ul>
 */
public class KeywordGeneralSection extends TemplateTextSection {

    /** keywordString */
    private String keywordString;

    /**
     * 
     * 
     * @return NamedElement
     */
    private Element getData() {
        return (Element) this.getSelectedModel();
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.TemplateTextSection#get()
     */
    @Override
    protected String get() {

        if (this.getData().getKeywords().isEmpty()) {
            return keywordString = UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;
        }
        EList<String> list = this.getData().getKeywords();
        if (0 == list.size()) {
            return keywordString = UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;
        }
        List<String> keywordList = new ArrayList<String>();
        for (Iterator<String> iter = list.iterator(); iter.hasNext();) {
            keywordList.add(iter.next());
        }
        keywordString = OCLExpressionUtil.parseKeywordListToString(keywordList);

        return keywordString;
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.TemplateTextSection#getLabelText()
     */
    @Override
    protected String getLabelText() {
        return UMLMessage.getMessage(UMLMessage.LABEL_KEYWORD);
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.TemplateTextSection#set(java.lang.String)
     */
    @Override
    protected void set(String value) {
        try {
            if (keywordString.equals(value))
                return;

            List<String> keywordList = OCLExpressionUtil.parseStringToKeywordList(this.keywordString);

            for (String keyword : keywordList) {
                this.getData().removeKeyword(keyword.trim());
            }

            if (null == value) {
                keywordString = UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;
                return;
            } else {
                keywordString = value;
            }
            keywordList = OCLExpressionUtil.parseStringToKeywordList(this.keywordString);

            for (final String keyword : keywordList) {
                getData().addKeyword(keyword.trim());
            }

            // 키워드에 해당하는 notify가 발생하지 않아, 강제로 이름을 다시 set하여 발생시킴
            if (getData() instanceof NamedElement) {
                ((NamedElement) getData()).setName(((NamedElement) getData()).getName());
            }

            this.unsetListener();
            if (this.get() == null) {
                this.text.setText(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING);
            } else {
                this.text.setText(this.get());
            }
            this.setListener();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.TemplateTextSection#createTextControl()
     */
    @Override
    protected void createTextControl() {
        if (this.getData() instanceof Diagram) {
            return;
        }
        text = getWidgetFactory().createText(sectionComposite, UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING);
        gridData = new GridData(GridData.FILL_HORIZONTAL);
        text.setLayoutData(gridData);
    }
}
