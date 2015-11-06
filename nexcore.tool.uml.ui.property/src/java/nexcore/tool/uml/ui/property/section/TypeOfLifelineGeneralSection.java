/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
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
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.uml2.uml.ConnectableElement;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;


/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.section</li>
 * <li>설  명 : TypeOfLifelineGeneralSection</li>
 * <li>작성일 : 2010. 1. 19.</li>
 * <li>작성자 : 변영민</li>
 * </ul>
 */
public class TypeOfLifelineGeneralSection extends AbstractPropertyCommonSection implements SelectionListener {

    /** typeLabel */
    private CLabel typeLabel;

    /** propertyNameLabel */
    private CLabel typeNameLabel;

    /** selectTypeButton */
    private Button selectTypeButton;

    /** removeTypeButton */
//    private Button removeTypeButton;

    /** selectedType */
    private Type selectedType;

    /**
     * TypedElement 모델을 리턴.
     * 
     * @return TypedElement
     */
    private Lifeline getData() {
        return (Lifeline) this.getSelectedModel();
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
            UMLMessage.getMessage(UMLMessage.LABEL_TYPE) + UICoreConstant.UMLSECTION_CONSTANTS__COLON_TEXT);
        gridData = new GridData();
        gridData.widthHint = UICoreConstant.UMLSECTION_CONSTANTS___LABEL_WIDTH_HINT_FOR_RECTANGLE;
        typeLabel.setLayoutData(gridData);
        typeNameLabel = getWidgetFactory().createCLabel(sectionComposite,
            UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING);

//        removeTypeButton = getWidgetFactory().createButton(sectionComposite,
//            UMLMessage.getMessage(UMLMessage.LABEL_REMOVE),
//            SWT.PUSH);
        selectTypeButton = getWidgetFactory().createButton(sectionComposite,
            UMLMessage.getMessage(UMLMessage.LABEL_SELECT_TYPE) + UICoreConstant.UMLSECTION_CONSTANTS__TRIPLE_DOT_TEXT,
            SWT.PUSH);
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#refreshChildren()
     */
    @Override
    public void refreshChildren() {
        if (this.getData().getNearestPackage() != null) {
            if (!this.getData().getNearestPackage().getOwnedTypes().isEmpty()) {
                settingTypeToLabelOfType();
            } else {
                if (!typeNameLabel.isDisposed()) {
                    typeNameLabel.setImage(null);
                    typeNameLabel.setText(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING);
                    typeNameLabel.getParent().pack();
                }
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
//        if (!removeTypeButton.isDisposed()) {
//            this.removeTypeButton.addSelectionListener(this);
//        }
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#unsetListener()
     */
    @Override
    protected void unsetListener() {
        if (!selectTypeButton.isDisposed()) {
            this.selectTypeButton.removeSelectionListener(this);
        }
//        if (!removeTypeButton.isDisposed()) {
//            this.removeTypeButton.removeSelectionListener(this);
//        }
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
        if (!(e.widget instanceof Button)) {
            return;
        }
        String buttonText = ((Button) e.widget).getText();
        if (buttonText.equals(UMLMessage.LABEL_REMOVE)) {
            selectedType = null;
            executeSetting();
        } else if (buttonText.startsWith(UMLMessage.LABEL_SELECT_TYPE)) {
            TypeSelectDialog typeSelectDialog = new TypeSelectDialog(selectTypeButton.getShell(),
                TypeSelectDialogType.LIFELINE, getData());
            
            int returnResult = typeSelectDialog.open();
            if (returnResult == TypeSelectDialog.OK) {
                if (typeSelectDialog.getResult() != null) {
                    selectedType = (Type) typeSelectDialog.getFirstResult();
                    executeSetting();
                }
            } else if (returnResult == IDialogConstants.FINISH_ID) {
                DomainUtil.run(new TransactionalAction() {
                    /**
                     * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                     */
                    @Override
                    public void doExecute() {
                        getData().getRepresents().setType(null);
                        settingTypeToLabelOfType();
                    }
                });
            }
        }
    }

    /**
     * 
     * void
     */
    private void executeSetting() {
        DomainUtil.run(new TransactionalAction() {
            /**
             * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
             */
            @Override
            public void doExecute() {
                getData().getRepresents().setType(selectedType);
            }
        });
        settingTypeToLabelOfType();
    }

    /**
     * 유형을 표시하는 레이블에 해당 속성 모델의 유형을 세팅함.
     * 
     * @param selectedType
     *            void
     */
    private void settingTypeToLabelOfType() {
        ConnectableElement represents = getData().getRepresents();
        String name = UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;
        Image image = null;
        if (represents != null && represents instanceof Property) {
            Property property = (Property) represents;
            Type type = property.getType();
            if (type != null) {
                name = type.getName();
                image = UICoreConstant.UMLSECTION_CONSTANTS__ADAPTER_FACTORY_LABEL_PROVIDER.getImage(type);
            }
        }
        if (!typeNameLabel.isDisposed()) {
            typeNameLabel.setImage(image);
            typeNameLabel.setText(name);
            typeNameLabel.getParent().pack();
        }
    }
}
