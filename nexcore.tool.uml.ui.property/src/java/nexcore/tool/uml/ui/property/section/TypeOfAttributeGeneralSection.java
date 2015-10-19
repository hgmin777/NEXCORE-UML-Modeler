/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.property.section;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.core.util.TypeSelectDialogType;
import nexcore.tool.uml.manager.transaction.TransactionalAction;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.dialog.TypeSelectDialog;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.ValueSpecification;


/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.section</li>
 * <li>설  명 : TypeOfAttributeGeneralSection</li>
 * <li>작성일 : 2009. 12. 29.</li>
 * <li>작성자 : 변영민</li>
 * </ul>
 */
public class TypeOfAttributeGeneralSection extends AbstractPropertyCommonSection implements SelectionListener {

    /** typeLabel */
    private CLabel typeLabel;

    /** propertyNameLabel */
    private CLabel typeNameLabel;

    /** selectTypeButton */
    private Button selectTypeButton;

    /** stringBuffer */
    private StringBuffer stringBuffer;

    /**
     * TypedElement 모델을 리턴.
     * 
     * @return TypedElement
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
        sectionLayout = new GridLayout(4, false);
        sectionComposite.setLayout(sectionLayout);

        typeLabel = getWidgetFactory().createCLabel(sectionComposite,
            UMLMessage.LABEL_TYPE + UICoreConstant.UMLSECTION_CONSTANTS__COLON_TEXT); //$NON-NLS-1$
        gridData = new GridData();
        gridData.widthHint = UICoreConstant.UMLSECTION_CONSTANTS___LABEL_WIDTH_HINT_FOR_RECTANGLE;
        typeLabel.setLayoutData(gridData);
        typeNameLabel = getWidgetFactory().createCLabel(sectionComposite,
            UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING);
        selectTypeButton = getWidgetFactory().createButton(sectionComposite,
            UMLMessage.LABEL_SELECT_TYPE + UICoreConstant.UMLSECTION_CONSTANTS__TRIPLE_DOT_TEXT,
            SWT.PUSH);
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#refreshChildren()
     */
    @Override
    public void refreshChildren() {
        if (this.getData().getType() != null) {
            settingTypeToLabelOfType();
        } else {
            if (!typeNameLabel.isDisposed()) {
                typeNameLabel.setImage(null);
                typeNameLabel.setText(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING);
                typeNameLabel.getParent().pack();
            }
        }
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#setListener()
     */
    @Override
    protected void setListener() {
        if (!selectTypeButton.isDisposed()) {
            this.selectTypeButton.addSelectionListener(this);
        }
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#unsetListener()
     */
    @Override
    protected void unsetListener() {
        if (!selectTypeButton.isDisposed()) {
            this.selectTypeButton.removeSelectionListener(this);
        }
    }

    /**
     * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
     */
    public void widgetDefaultSelected(SelectionEvent e) {

    }

    /**
     * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
     */
    public void widgetSelected(SelectionEvent e) {

        TypeSelectDialog typeSelectDialog = new TypeSelectDialog(selectTypeButton.getShell(),
            TypeSelectDialogType.PROPERTY, getData());
        int dlgReturn = typeSelectDialog.open();
        if (dlgReturn == TypeSelectDialog.OK) {
            if (typeSelectDialog.getResult() != null) {
                final Element selectedElement = (Element) typeSelectDialog.getFirstResult();
                DomainUtil.run(new TransactionalAction() {
                    /**
                     * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                     */
                    @Override
                    public void doExecute() {
                        getData().setType((Type) selectedElement);
                        getData().setDefaultValue((ValueSpecification) null);
                        getData().setDefault("");
                    }
                });
                settingTypeToLabelOfType();
            }
        } else if (dlgReturn == IDialogConstants.FINISH_ID) {
            DomainUtil.run(new TransactionalAction() {
                /**
                 * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                 */
                @Override
                public void doExecute() {
                    getData().setType((Type) null);
                    getData().setDefaultValue((ValueSpecification) null);
                }
            });
        }
    }

    /**
     * 유형을 표시하는 레이블에 해당 속성 모델의 유형을 세팅함.
     * 
     * @param selectedElement
     *            void
     */
    private void settingTypeToLabelOfType() {
        if (this.getData().getType() != null) {
            stringBuffer = new StringBuffer();
            stringBuffer.append(this.getData().getType().getName());
            stringBuffer.append(UICoreConstant.UMLSECTION_CONSTANTS__PARENTHESIS_LEFT);
            stringBuffer.append(this.getData().getType().getQualifiedName());
            stringBuffer.append(UICoreConstant.UMLSECTION_CONSTANTS__PARENTHESIS_RIGHT);

            if (!typeNameLabel.isDisposed()) {
                typeNameLabel.setImage(UICoreConstant.UMLSECTION_CONSTANTS__ADAPTER_FACTORY_LABEL_PROVIDER.getImage(this.getData()
                    .getType()));
                typeNameLabel.setText(stringBuffer.toString());
                typeNameLabel.getParent().pack();

            }

        }
    }
}
