/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.property.section;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.ui.core.UICoreConstant;

import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.section</li>
 * <li>설  명 : TypeOfDiagramGeneralSection</li>
 * <li>작성일 : 2009. 12. 23.</li>
 * <li>작성자 : 변영민</li>
 * </ul>
 */
public class TypeOfDiagramGeneralSection extends AbstractPropertyCommonSection {

    /** gridData */
    private GridData gridData;

    /** typeLabel */
    private CLabel typeLabel;

    /** typePropertyLabel */
    private CLabel typePropertyLabel;

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#createMainComposite()
     */
    @Override
    public void createMainComposite() {
        sectionComposite = getWidgetFactory().createComposite(parent);
        sectionLayout = new GridLayout(2, false);
        sectionComposite.setLayout(sectionLayout);

        typeLabel = getWidgetFactory().createCLabel(sectionComposite,
            UMLMessage.getMessage(UMLMessage.LABEL_TYPE) + UICoreConstant.UMLSECTION_CONSTANTS__COLON_TEXT); //$NON-NLS-1$
        gridData = new GridData();
        gridData.widthHint = UICoreConstant.UMLSECTION_CONSTANTS___LABEL_WIDTH_HINT_FOR_CHARACTER;
        typeLabel.setLayoutData(gridData);

        typePropertyLabel = getWidgetFactory().createCLabel(sectionComposite,
            UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING); //$NON-NLS-1$
        gridData = new GridData(GridData.FILL_HORIZONTAL);
        typePropertyLabel.setLayoutData(gridData);
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#unsetListener()
     */
    @Override
    protected void unsetListener() {

    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#refreshChildren()
     */
    @Override
    public void refreshChildren() {
        String typeName = UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;
        if (this.getSelectedModel() instanceof Diagram) {
            typeName = ((Diagram) this.getSelectedModel()).getType().name();

            if (!typePropertyLabel.isDisposed())
                typePropertyLabel.setText(typeName);
        }
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#setListener()
     */
    @Override
    protected void setListener() {

    }
}
