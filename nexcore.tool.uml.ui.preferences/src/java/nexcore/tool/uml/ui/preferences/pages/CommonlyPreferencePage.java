/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.preferences.pages;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.util.PreferenceUtil;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.preferences</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.preferences.pages</li>
 * <li>설  명 : CommonlyPreferencePage</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : Anhong </li>
 * </ul>
 */
public class CommonlyPreferencePage extends AbstractPreferencePage implements IWorkbenchPreferencePage {
    /** btn_association */
    private Button btnAssociation;

    /** strBoolean */
    private String strBoolean;

    /** btnRolename */
    private Button btnRolename;

    /** btnConnection */
    private Button btnConnection;

    // /** btnIcon */
    // private Button btnIcon;
    //
    // /** btnText */
    // private Button btnText;

    // /** strIconOrText */
    // private String strIconOrText = "";
    //
    // /** btnGetAllConnectionInfo */
    // private Button btnGetAllConnectionInfo;

    // /** txtCompartment */
    // private Text txtCompartment;
    //
    // /** txtFigure */
    // private Text txtFigure;

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse
     * .swt.widgets.Composite)
     */
    @Override
    protected Control createContents(Composite parent) {
        Composite container = new Composite(parent, SWT.NULL);
        container.setLayout(new GridLayout(1, false));

        Group labelDisplayGrp = new Group(container, SWT.NONE);
        labelDisplayGrp.setLayout(new GridLayout());
        labelDisplayGrp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        labelDisplayGrp.setText(UMLMessage.LABEL_VISIBILITY_ASSOCIATION_LABEL);

        GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        btnAssociation = new Button(labelDisplayGrp, SWT.CHECK);
        btnAssociation.setText(UMLMessage.LABEL_ASSOCIATION_LABEL);
        btnAssociation.setLayoutData(gd);

        gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        btnRolename = new Button(labelDisplayGrp, SWT.CHECK);
        btnRolename.setText(UMLMessage.LABEL_ROLE_NAME_LABEL);
        btnRolename.setLayoutData(gd);

        gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        btnConnection = new Button(container, SWT.CHECK);
        btnConnection.setText(UMLMessage.LABEL_VISIBILITY_CONNECTION_NODE_NAME);
        btnConnection.setLayoutData(gd);

        // Label label05 = new Label(container, SWT.NONE);
        // label05.setLayoutData(gd_label2);
        // label05.setText(UMLMessage.LABEL_NAME_DISPLAY_METHOD +":");

        // btnIcon = new Button(container, SWT.RADIO);
        // GridData gd_btn1 = new GridData(SWT.LEFT, SWT.CENTER, false, false);
        // btnIcon.setLayoutData(gd_btn1);
        //		btnIcon.setText(UMLMessage.LABEL_ICON); //$NON-NLS-1$
        // btnIcon.addSelectionListener(new SelectionAdapter() {
        // public void widgetSelected(SelectionEvent e) {
        // setIconOrText();
        // }
        // });
        //
        // btnText = new Button(container, SWT.RADIO);
        // btnText.setLayoutData(gd_btn1);
        //		btnText.setText(UMLMessage.LABEL_TEXT); //$NON-NLS-1$
        // btnText.addSelectionListener(new SelectionAdapter() {
        // public void widgetSelected(SelectionEvent e) {
        // setIconOrText();
        // }
        // });

        // Label label07 = new Label(container, SWT.NONE);
        // label07.setLayoutData(gd_label2);
        //		label07.setText(UMLMessage.LABEL_DISPLAY_CONNECTION_WHILE_DND +":"); //$NON-NLS-1$
        //
        // btnGetAllConnectionInfo = new Button(container, SWT.CHECK);
        // btnGetAllConnectionInfo.setLayoutData(gd_btn);

        // Label label08 = new Label(container, SWT.NONE);
        // label08.setLayoutData(gd_label2);
        //		label08.setText(UMLMessage.LABEL_COMPARTMENT_SIZE +":"); //$NON-NLS-1$

        // txtCompartment = new Text(container, SWT.BORDER);
        // gd_btn.widthHint = 50;
        // txtCompartment.setLayoutData(gd_btn);

        // Label label09 = new Label(container, SWT.NONE);
        // label09.setLayoutData(gd_label2);
        //		label09.setText(UMLMessage.LABEL_FIGURE_SIZE +":"); //$NON-NLS-1$

        // txtFigure = new Text(container, SWT.BORDER);
        // txtFigure.setLayoutData(gd_btn);

        return container;
    }

    // private void setIconOrText(){
    // if (btnIcon.getSelection()) {
    // strIconOrText = "icon";
    // btnText.setSelection(false);
    // } else if (btnText.getSelection()) {
    // strIconOrText = "text";
    // btnIcon.setSelection(false);
    // }
    // }

    @Override
    protected void doDefaultStore() {
        strBoolean = "false";
        // strIconOrText = "";
        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_DIAGRAM_GENERAL_VISIBILITY_ASSOCIATIONLABEL,
            strBoolean);

        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_DIAGRAM_GENERAL_VISIBILITY_ROLENAMELABEL,
            strBoolean);

        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_DIAGRAM_GENERAL_VISIBILITY_CONNECTIONLABEL,
            strBoolean);

        // strIconOrText = "text";
        // PreferenceUtil.INSTANCE
        // .setValueOfStringFieldEditor(
        // ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_DIAGRAM_GENERAL_NAMEINDICATIONTYPE,
        // strIconOrText);
        // PreferenceUtil.INSTANCE
        // .setValueOfStringFieldEditor(
        // ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_DIAGRAM_GENERAL_DND_GETALLCONNECTIONINFORMATION,
        // strBoolean);
        //
        // PreferenceUtil.INSTANCE
        // .setValueOfStringFieldEditor(
        // ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_DIAGRAM_GENERAL_COMPARTMENT_DEFAULTSIZE,
        // "");
        // PreferenceUtil.INSTANCE
        // .setValueOfStringFieldEditor(
        // ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_DIAGRAM_GENERAL_FIGURE_DEFAULTSIZE,
        // "");
        UiCorePlugin.getDefault().savePluginPreferences();
        // PreferenceUtil.INSTANCE.setIsDefaultPresented(false);

    }

    @Override
    protected void doLoad() {
        strBoolean = PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_DIAGRAM_GENERAL_VISIBILITY_ASSOCIATIONLABEL);
        if (strBoolean.equals("true"))
            btnAssociation.setSelection(true);
        else
            btnAssociation.setSelection(false);

        strBoolean = PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_DIAGRAM_GENERAL_VISIBILITY_ROLENAMELABEL);
        if (strBoolean.equals("true"))
            btnRolename.setSelection(true);
        else
            btnRolename.setSelection(false);

        strBoolean = PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_DIAGRAM_GENERAL_VISIBILITY_CONNECTIONLABEL);
        if (strBoolean.equals("true"))
            btnConnection.setSelection(true);
        else
            btnConnection.setSelection(false);
        // strIconOrText = PreferenceUtil.INSTANCE
        // .getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_DIAGRAM_GENERAL_NAMEINDICATIONTYPE);
        // if (strIconOrText.equals("icon")) {
        // btnIcon.setSelection(true);
        // btnText.setSelection(false);
        // } else if (strIconOrText.equals("text")) {
        // btnIcon.setSelection(false);
        // btnText.setSelection(true);
        // }
        // strBoolean = PreferenceUtil.INSTANCE
        // .getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_DIAGRAM_GENERAL_DND_GETALLCONNECTIONINFORMATION);
        // if (strBoolean.equals("true"))
        // btnGetAllConnectionInfo.setSelection(true);
        // else
        // btnGetAllConnectionInfo.setSelection(false);
        // txtCompartment
        // .setText(PreferenceUtil.INSTANCE
        // .getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_DIAGRAM_GENERAL_COMPARTMENT_DEFAULTSIZE));
        // txtFigure
        // .setText(PreferenceUtil.INSTANCE
        // .getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_DIAGRAM_GENERAL_FIGURE_DEFAULTSIZE));
    }

    @Override
    protected void doStore() {
        if (btnAssociation.getSelection())
            strBoolean = "true";
        else
            strBoolean = "false";
        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_DIAGRAM_GENERAL_VISIBILITY_ASSOCIATIONLABEL,
            strBoolean);

        if (btnRolename.getSelection())
            strBoolean = "true";
        else
            strBoolean = "false";
        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_DIAGRAM_GENERAL_VISIBILITY_ROLENAMELABEL,
            strBoolean);

        if (btnConnection.getSelection())
            strBoolean = "true";
        else
            strBoolean = "false";
        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_DIAGRAM_GENERAL_VISIBILITY_CONNECTIONLABEL,
            strBoolean);
        // PreferenceUtil.INSTANCE
        // .setValueOfStringFieldEditor(
        // ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_DIAGRAM_GENERAL_NAMEINDICATIONTYPE,
        // strIconOrText);

        // if (btnGetAllConnectionInfo.getSelection())
        // strBoolean = "true";
        // else
        // strBoolean = "false";
        // PreferenceUtil.INSTANCE
        // .setValueOfStringFieldEditor(
        // ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_DIAGRAM_GENERAL_DND_GETALLCONNECTIONINFORMATION,
        // strBoolean);
        //
        // PreferenceUtil.INSTANCE
        // .setValueOfStringFieldEditor(
        // ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_DIAGRAM_GENERAL_COMPARTMENT_DEFAULTSIZE,
        // txtCompartment.getText());
        // PreferenceUtil.INSTANCE
        // .setValueOfStringFieldEditor(
        // ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_DIAGRAM_GENERAL_FIGURE_DEFAULTSIZE,
        // txtFigure.getText());
        UiCorePlugin.getDefault().savePluginPreferences();
    }

    @Override
    protected void initDefault() {
        btnAssociation.setSelection(false);
        btnRolename.setSelection(false);
        btnConnection.setSelection(false);
        // btnIcon.setSelection(false);
        // btnText.setSelection(false);

        // btnGetAllConnectionInfo.setSelection(true);
        // txtCompartment.setText("");
        // txtFigure.setText("");
        // PreferenceUtil.INSTANCE.setIsDefaultPresented(true);
    }

    @Override
    protected void initializer() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void doCancel() {
        // TODO Auto-generated method stub

    }

}
