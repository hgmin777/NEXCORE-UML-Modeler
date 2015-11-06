/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.preferences.pages;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.util.PreferenceUtil;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.preferences</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.preferences.pages</li>
 * <li>설 명 : ProjectExplorerFragmentPreferencePage</li>
 * <li>작성일 : 2011. 8. 17.</li>
 * <li>작성자 : 강석찬</li>
 * </ul>
 */
public class ProjectExplorerFragmentPreferencePage extends AbstractPreferencePage implements IWorkbenchPreferencePage {

    /** 프로젝트 하위 ".fragment" 폴더에 패키지 구조로 폴더와 단편파일 생성 선택 버튼 */
    private Button basePackageButton;

    /** 묻지 않고 항상 실행 */
    private Button alwaysrunButton;

    private String strBoolean;

    private String alwaysrunBoolean;

    private static String TRUE = "true"; //$NON-NLS-1$

    private static String FALSE = "false"; //$NON-NLS-1$

    /**
     * @see nexcore.tool.uml.ui.preferences.pages.AbstractPreferencePage#doDefaultStore()
     */
    @Override
    protected void doDefaultStore() {
        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PROJECTEXPLORER_FRAGMENT_BASE_PACKAGE,
            TRUE);

        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.ALWAYS_RUN, TRUE);

        UiCorePlugin.getDefault().savePluginPreferences();
        // PreferenceUtil.INSTANCE.setIsDefaultPresented(true);
    }

    /**
     * @see nexcore.tool.uml.ui.preferences.pages.AbstractPreferencePage#doLoad()
     */
    @Override
    protected void doLoad() {
        strBoolean = PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PROJECTEXPLORER_FRAGMENT_BASE_PACKAGE);
        if (strBoolean.equals(TRUE))
            basePackageButton.setSelection(true);
        else
            basePackageButton.setSelection(false);
        
        alwaysrunBoolean = PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.ALWAYS_RUN);
        if (alwaysrunBoolean.equals(TRUE))
            alwaysrunButton.setSelection(true);
        else
            alwaysrunButton.setSelection(false);
        
        alwaysrunButton.setEnabled(basePackageButton.getSelection());
    }

    /**
     * @see nexcore.tool.uml.ui.preferences.pages.AbstractPreferencePage#doStore()
     */
    @Override
    protected void doStore() {
        if (basePackageButton.getSelection())
            strBoolean = TRUE;
        else
            strBoolean = FALSE;
        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PROJECTEXPLORER_FRAGMENT_BASE_PACKAGE,
            strBoolean);

        if (alwaysrunButton.getSelection())
            alwaysrunBoolean = TRUE;
        else
            alwaysrunBoolean = FALSE;
        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.ALWAYS_RUN, alwaysrunBoolean);

        UiCorePlugin.getDefault().savePluginPreferences();
    }

    /**
     * @see nexcore.tool.uml.ui.preferences.pages.AbstractPreferencePage#initDefault()
     */
    @Override
    protected void initDefault() {
        basePackageButton.setSelection(true);
        alwaysrunButton.setSelection(false);
    }

    /**
     * @see nexcore.tool.uml.ui.preferences.pages.AbstractPreferencePage#initializer()
     */
    @Override
    protected void initializer() {
    }

    /**
     * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createContents(Composite parent) {
        Composite container = new Composite(parent, SWT.NONE);
        container.setLayout(new GridLayout(1, false));

        createCommonGroup(container);

        return container;
    }

    /**
     * 문서 산출물 공통 환경설정
     * 
     * @param container
     *            void
     */
    private void createCommonGroup(Composite container) {

        basePackageButton = new Button(container, SWT.CHECK);
        basePackageButton.setText(UMLMessage.LABEL_PREFERENCE_PROJECTEXPLORER_FRAGMENT_BASE_PACKAGE);

        alwaysrunButton = new Button(container, SWT.CHECK);
        alwaysrunButton.setText(UMLMessage.LABEL_PREFERENCE_PROJECTEXPLORER_FRAGMENT_ALWAYS_RUN);
        
        basePackageButton.addSelectionListener(new SelectionAdapter() {
           @Override
            public void widgetSelected(SelectionEvent e) {
               alwaysrunButton.setEnabled(basePackageButton.getSelection());
            } 
        });

    }

    /**
     * @see nexcore.tool.uml.ui.preferences.pages.AbstractPreferencePage#doCancel()
     */
    @Override
    protected void doCancel() {
        // TODO Auto-generated method stub
    }

}
