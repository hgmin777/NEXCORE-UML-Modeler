/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
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
 * <li>설 명 : ReportPreferencePage</li>
 * <li>작성일 : 2011. 4. 26.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
public class ReportPreferencePage extends AbstractPreferencePage implements IWorkbenchPreferencePage {

    /** (해당사항 없음) 출력 유무 선택 버튼 */
    private Button noContentButton;

    /** 오퍼레이션의 매개변수에 순서 번호 출력 유무 선택 버튼 */
    private Button printParamNoButton;

    /** 속성의 초기값에 내용이 없을 시, 'N/A' 출력 유무 선택 버튼 */
    private Button printNADefaultValueButton;

    /** 오퍼레이션의 리턴 유형이 없을 때 'N/A' 출력 유무 선택 버튼 */
    private Button printNAReturnButton;

    /** 다이어그램 이미지 테두리 출력 */
    private Button printImageOutline;

    /** 속성의 유형 표시 방법 - 경로 모두 표시 */
    private Button showTypePathAll;

    /** 속성의 유형 표시 방법 - 이름만 표시 */
    private Button showTypeNameOnly;

    // /** 컴포넌트 명세서 기준 패키지명 */
    // private Text componentBasePackage;

    private String strBoolean;

    private static String TRUE = "true"; //$NON-NLS-1$

    private static String FALSE = "false"; //$NON-NLS-1$

    /**
     * @see nexcore.tool.uml.ui.preferences.pages.AbstractPreferencePage#doDefaultStore()
     */
    @Override
    protected void doDefaultStore() {
        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_REPORT_PRINT_NOCONTENTS,
            TRUE);
        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_REPORT_PRINT_PARAM_NO,
            TRUE);
        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_REPORT_PRINT_NA_DEFAULT_VALUE,
            TRUE);
        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_REPORT_PRINT_NA_RETURNTYPE,
            TRUE);
        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_REPORT_PRINT_IMAGEOUTLINE,
            TRUE);
        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_REPORT_PRINT_PATH_ALL,
            TRUE);

        UiCorePlugin.getDefault().savePluginPreferences();
        // PreferenceUtil.INSTANCE.setIsDefaultPresented(true);
    }

    /**
     * @see nexcore.tool.uml.ui.preferences.pages.AbstractPreferencePage#doLoad()
     */
    @Override
    protected void doLoad() {
        strBoolean = PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_REPORT_PRINT_NOCONTENTS);
        if (strBoolean.equals(TRUE))
            noContentButton.setSelection(true);
        else
            noContentButton.setSelection(false);

        strBoolean = PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_REPORT_PRINT_PARAM_NO);
        if (strBoolean.equals(TRUE))
            printParamNoButton.setSelection(true);
        else
            printParamNoButton.setSelection(false);

        strBoolean = PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_REPORT_PRINT_NA_DEFAULT_VALUE);
        if (strBoolean.equals(TRUE))
            printNADefaultValueButton.setSelection(true);
        else
            printNADefaultValueButton.setSelection(false);

        strBoolean = PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_REPORT_PRINT_NA_RETURNTYPE);
        if (strBoolean.equals(TRUE))
            printNAReturnButton.setSelection(true);
        else
            printNAReturnButton.setSelection(false);

        strBoolean = PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_REPORT_PRINT_IMAGEOUTLINE);
        if (strBoolean.equals(TRUE))
            printImageOutline.setSelection(true);
        else
            printImageOutline.setSelection(false);

        strBoolean = PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_REPORT_PRINT_PATH_ALL);
        if (strBoolean.equals(TRUE))
            showTypePathAll.setSelection(true);
        else
            showTypeNameOnly.setSelection(true);

        // componentBasePackage.setText(PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_REPORT_COMPONENT_BASE_PACKAGE));
    }

    /**
     * @see nexcore.tool.uml.ui.preferences.pages.AbstractPreferencePage#doStore()
     */
    @Override
    protected void doStore() {
        if (noContentButton.getSelection())
            strBoolean = TRUE;
        else
            strBoolean = FALSE;
        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_REPORT_PRINT_NOCONTENTS,
            strBoolean);

        if (printParamNoButton.getSelection())
            strBoolean = TRUE;
        else
            strBoolean = FALSE;
        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_REPORT_PRINT_PARAM_NO,
            strBoolean);

        if (printNADefaultValueButton.getSelection())
            strBoolean = TRUE;
        else
            strBoolean = FALSE;
        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_REPORT_PRINT_NA_DEFAULT_VALUE,
            strBoolean);

        if (printNAReturnButton.getSelection())
            strBoolean = TRUE;
        else
            strBoolean = FALSE;
        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_REPORT_PRINT_NA_RETURNTYPE,
            strBoolean);

        if (printImageOutline.getSelection())
            strBoolean = TRUE;
        else
            strBoolean = FALSE;
        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_REPORT_PRINT_IMAGEOUTLINE,
            strBoolean);

        if (showTypePathAll.getSelection())
            strBoolean = TRUE;
        else
            strBoolean = FALSE;
        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_REPORT_PRINT_PATH_ALL,
            strBoolean);

        // PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_REPORT_COMPONENT_BASE_PACKAGE,
        // componentBasePackage.getText());

        UiCorePlugin.getDefault().savePluginPreferences();
    }

    /**
     * @see nexcore.tool.uml.ui.preferences.pages.AbstractPreferencePage#initDefault()
     */
    @Override
    protected void initDefault() {
        noContentButton.setSelection(true);
        printParamNoButton.setSelection(true);
        printNADefaultValueButton.setSelection(true);
        printNAReturnButton.setSelection(true);
        printImageOutline.setSelection(false);
        showTypePathAll.setSelection(true);
        showTypeNameOnly.setSelection(false);

        // PreferenceUtil.INSTANCE.setIsDefaultPresented(true);
    }

    /**
     * @see nexcore.tool.uml.ui.preferences.pages.AbstractPreferencePage#initializer()
     */
    @Override
    protected void initializer() {
        // PreferenceUtil.INSTANCE.getPreferenceStore()
        // .setDefault(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_REPORT_PRINT_NOCONTENTS,
        // true);
        // PreferenceUtil.INSTANCE.getPreferenceStore()
        // .setDefault(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_REPORT_PRINT_PARAM_NO,
        // true);
        // PreferenceUtil.INSTANCE.getPreferenceStore()
        // .setDefault(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_REPORT_PRINT_NA,
        // true);
    }

    /**
     * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createContents(Composite parent) {
        Composite container = new Composite(parent, SWT.NONE);
        container.setLayout(new GridLayout(1, false));

        createCommonGroup(container);
        // createComponentGroup(container);

        return container;
    }

    /**
     * 문서 산출물 공통 환경설정
     * 
     * @param container
     *            void
     */
    private void createCommonGroup(Composite container) {

        noContentButton = new Button(container, SWT.CHECK);
        noContentButton.setText(UMLMessage.LABEL_PREFERENCE_REPORT_PRINT_NO_CONTENTS);

        printNADefaultValueButton = new Button(container, SWT.CHECK);
        printNADefaultValueButton.setText(UMLMessage.LABEL_PREFERENCE_REPORT_PRINT_NA);

        printNAReturnButton = new Button(container, SWT.CHECK);
        printNAReturnButton.setText(UMLMessage.LABEL_PREFERENCE_REPORT_PRINT_NA_RETURNTYPE);

        printParamNoButton = new Button(container, SWT.CHECK);
        printParamNoButton.setText(UMLMessage.LABEL_PREFERENCE_REPORT_PRINT_PARAM_NO);

        printImageOutline = new Button(container, SWT.CHECK);
        printImageOutline.setText(UMLMessage.LABEL_PREFERENCE_REPORT_PRINT_IMAGE_OUTLINE);

        createTypePath(container);

    }

    /**
     * 속성의 경로 정보를 어떻게 보여줄 것인지 설정.
     * 
     * @param container
     *            void
     */
    private void createTypePath(Composite container) {

        Group group = new Group(container, SWT.NONE);
        group.setLayout(new GridLayout(2, false));
        group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        group.setText(UMLMessage.LABEL_PREFERENCE_REPORT_TYPEPATH);

        showTypePathAll = new Button(group, SWT.RADIO);
        showTypePathAll.setText(UMLMessage.LABEL_PREFERENCE_REPORT_SHOW_TYPE_PATH_ALL);

        showTypeNameOnly = new Button(group, SWT.RADIO);
        showTypeNameOnly.setText(UMLMessage.LABEL_PREFERENCE_REPORT_SHOW_TYPE_NAME_ONLY);

    }

    // /**
    // * 컴포넌트 관련 환경설정 그룹 생성
    // *
    // * @param container
    // * void
    // */
    // private void createComponentGroup(Composite container) {
    //
    // Group group = new Group(container, SWT.NONE);
    // group.setLayout(new GridLayout(2, false));
    // GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    // group.setLayoutData(gd);
    // group.setText(UMLMessage.LABEL_PREFERENCE_REPORT_COMPONENT); //
    // "컴포넌트 관련");
    //
    // Label componentBasePackageLabel = new Label(group, SWT.NONE);
    // componentBasePackageLabel.setText(UMLMessage.LABEL_PREFERENCE_REPORT_COMPONENT_BASE_PACKAGE);
    // // componentBasePackageLabel.setLayoutData(gd);
    //
    // componentBasePackage = new Text(group, SWT.BORDER);
    // gd = new GridData(GridData.FILL_HORIZONTAL, GridData.BEGINNING, true,
    // false);
    // gd.widthHint = 170;
    // componentBasePackage.setLayoutData(gd);
    //
    // }

    /**
     * @see nexcore.tool.uml.ui.preferences.pages.AbstractPreferencePage#doCancel()
     */
    @Override
    protected void doCancel() {
        // TODO Auto-generated method stub

    }

}
