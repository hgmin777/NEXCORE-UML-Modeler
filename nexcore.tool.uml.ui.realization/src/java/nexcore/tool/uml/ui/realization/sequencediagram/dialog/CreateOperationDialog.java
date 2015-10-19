/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.realization.sequencediagram.dialog;

import java.util.ArrayList;
import java.util.List;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.ui.core.UICoreConstant;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Type;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.dialog</li>
 * <li>설  명 : CreateOperationDialog</li>
 * <li>작성일 : 2010. 1. 26.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
public class CreateOperationDialog extends Dialog {

    /**
     * pType
     */
    private Type pType;

    /**
     * typeList
     */
    private List<Type> typeList;

    /**
     * typeCombo
     */
    private Combo typeCombo;

    /**
     * selectedType
     */
    private Type selectedType;

    /**
     * operation
     */
    private String operation;

    /**
     * operationName
     */
    private Text operationName;

    /** IME Mode */
    // private int nIMEInputMode;

    /**
     * @param parentShell
     * @param type
     */
    public CreateOperationDialog(Shell parentShell, Type type) {
        super(parentShell);
        this.pType = type;
        this.typeList = new ArrayList<Type>();
        this.operation = UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;
        this.selectedType = null;
        /*
         * 부모 쉘의 IME(한/영 키 모드) 다이얼로그에 유지
         */
        // nIMEInputMode = parentShell.getImeInputMode();
    }

    /**
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        // 부모 쉘의 IME(한/영 키 모드) 다이얼로그에 유지
        // parent.getShell().setImeInputMode(nIMEInputMode);

        Composite composite = (Composite) super.createDialogArea(parent);
        FormToolkit toolkit = new FormToolkit(parent.getDisplay());

        composite.getShell().setText(UMLMessage.LABEL_CREATE_OPERATION);

        GridLayout layout = (GridLayout) composite.getLayout();
        layout.numColumns = 2;

        Label operationLabel = toolkit.createLabel(composite, UMLMessage.LABEL_OPERATION_NAME);
        operationLabel.setBackground(ColorConstants.menuBackground);
        GridData gd = new GridData(GridData.VERTICAL_ALIGN_CENTER);
        operationLabel.setLayoutData(gd);

        operationName = toolkit.createText(composite, UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING);
        gd = new GridData(GridData.VERTICAL_ALIGN_CENTER);
        gd.widthHint = 200;
        operationName.setLayoutData(gd);

        Label ownerLabel = toolkit.createLabel(composite, UMLMessage.LABEL_OPERATION_OWNER);
        ownerLabel.setBackground(ColorConstants.menuBackground);
        gd = new GridData(GridData.VERTICAL_ALIGN_CENTER);
        ownerLabel.setLayoutData(gd);

        typeCombo = new Combo(composite, SWT.READ_ONLY);
        toolkit.adapt(typeCombo);
        gd = new GridData(GridData.VERTICAL_ALIGN_CENTER);
        gd.widthHint = 180;
        typeCombo.setLayoutData(gd);

        getTypeList();

        return composite;

    }

    /**
     * 
     * void
     */
    private void getTypeList() {

        typeList.clear();
        typeList.add(pType);

        if (pType instanceof Class) {
            Class clazz = (Class) pType;
            List<Interface> interfaces = clazz.getAllImplementedInterfaces();
            typeList.addAll(interfaces);
        }

        typeCombo.removeAll();
        for (Type t : typeList) {
            typeCombo.add(t.getName());
        }
        typeCombo.select(0);
    }

    /**
     * @see org.eclipse.jface.dialogs.Dialog#okPressed()
     */
    @Override
    protected void okPressed() {
        selectedType = typeList.get(typeCombo.getSelectionIndex());
        operation = operationName.getText();
        super.okPressed();
    }

    /**
     * getSelectedType
     *  
     * @return Type
     */
    public Type getSelectedType() {
        return selectedType;
    }

    /**
     * getOperationName
     *  
     * @return String
     */
    public String getOperationName() {
        return operation;
    }

}
