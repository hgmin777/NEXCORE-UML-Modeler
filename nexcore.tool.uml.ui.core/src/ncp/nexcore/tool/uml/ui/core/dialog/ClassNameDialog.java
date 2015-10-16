/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.core.dialog;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.ui.core.UICoreConstant;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.dialog</li>
 * <li>설  명 : ClassNameDialog</li>
 * <li>작성일 : 2012. 1. 9.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public class ClassNameDialog extends org.eclipse.jface.dialogs.Dialog {

    /** name */
    private String name;

    /** className */
    private Text className;

    /** IME Mode */
    // private int nIMEInputMode;
    /**
     * @param parentShell
     * @param type
     */
    public ClassNameDialog(Shell parentShell) {

        super(parentShell);

        /*
         * 부모 쉘의 IME(한/영 키 모드) 다이얼로그에 유지
         */
        // nIMEInputMode = parentShell.getImeInputMode();
    }

    /**
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    protected Control createDialogArea(Composite parent) {
        // 부모 쉘의 IME(한/영 키 모드) 다이얼로그에 유지
        // parent.getShell().setImeInputMode(nIMEInputMode);

        Composite composite = (Composite) super.createDialogArea(parent);
        FormToolkit toolkit = new FormToolkit(parent.getDisplay());

        composite.getShell().setText(UMLMessage.LABEL_CREATE_CLASS);

        GridLayout layout = (GridLayout) composite.getLayout();
        layout.numColumns = 1;

        Label operationLabel = toolkit.createLabel(composite, UMLMessage.LABEL_CLASSNAME);
        operationLabel.setBackground(ColorConstants.menuBackground);
        GridData gd = new GridData(GridData.VERTICAL_ALIGN_CENTER);
        operationLabel.setLayoutData(gd);

        className = toolkit.createText(composite, UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING);
        gd = new GridData(GridData.VERTICAL_ALIGN_CENTER);
        gd.widthHint = 200;
        className.setLayoutData(gd);
        className.setText(name);

        return composite;

    }

    /**
     * @see org.eclipse.jface.dialogs.Dialog#okPressed()
     */
    @Override
    protected void okPressed() {
        name = className.getText();
        super.okPressed();
    }

    /**
     * getClassName
     *  
     * @return String
     */
    public String getClassName() {
        return name;
    }

    /**
     * 
     *  
     * @param name2 void
     */
    public void setClassName(String name) {
        this.name = name;
    }

}
