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

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * 
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.preferences</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.preferences.pages</li>
 * <li>설  명 : UMLPreferencePage</li>
 * <li>작성일 : 2012. 5. 11.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public class UMLPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

    private boolean alwaysrunPersistableResourceBoolean;
    
    
    /** 모델 닫기 기능 사용 */
    private Button useModelCloseButton;
    
    private boolean useModelCloseBoolean;
    
    /** 프로젝트 백업 */
    private Button projectBackupButton;
    
    private boolean projectBackupBoolean;
    
    
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
        container.setLayout(new GridLayout());
        
        useModelCloseButton = new Button(container, SWT.CHECK);
        useModelCloseButton.setText(UMLMessage.MESSAGE_USE_MODEL_CLOSE);
        
        useModelCloseBoolean = PreferenceUtil.INSTANCE.getValueOfBooleanFieldEditor(ManagerConstant.USE_MODEL_CLOSE);
        if (useModelCloseBoolean)
            useModelCloseButton.setSelection(true);
        else
            useModelCloseButton.setSelection(false);
        
        projectBackupButton = new Button(container, SWT.CHECK);
        projectBackupButton.setText("프로젝트 백업");
        
        projectBackupBoolean = PreferenceUtil.INSTANCE.getValueOfBooleanFieldEditor(ManagerConstant.PROJECT_BACKUP);
        if (projectBackupBoolean)
            projectBackupButton.setSelection(true);
        else
            projectBackupButton.setSelection(false);
        
        return container;
    }

    public void init(IWorkbench workbench) {

    }
    
    protected void doDefaultStore() {
        PreferenceUtil.INSTANCE.setValueOfBooleanFieldEditor(ManagerConstant.USE_MODEL_CLOSE, false);
        PreferenceUtil.INSTANCE.setValueOfBooleanFieldEditor(ManagerConstant.PROJECT_BACKUP, true);
        UiCorePlugin.getDefault().savePluginPreferences();
    }
    
    @Override
    protected void performApply() {
        super.performApply();
        
        useModelCloseBoolean = useModelCloseButton.getSelection();
        PreferenceUtil.INSTANCE.setValueOfBooleanFieldEditor(ManagerConstant.USE_MODEL_CLOSE, useModelCloseBoolean);
        
        projectBackupBoolean = projectBackupButton.getSelection();
        PreferenceUtil.INSTANCE.setValueOfBooleanFieldEditor(ManagerConstant.USE_MODEL_CLOSE, projectBackupBoolean);
        UiCorePlugin.getDefault().savePluginPreferences();
    }
    
    @Override
    public boolean performCancel() {
        return super.performCancel();
    }
    
    @Override
    protected void performDefaults() {
        super.performDefaults();
        
        useModelCloseButton.setSelection(false);
        PreferenceUtil.INSTANCE.setValueOfBooleanFieldEditor(ManagerConstant.USE_MODEL_CLOSE, false);
        
        projectBackupButton.setSelection(false);
        PreferenceUtil.INSTANCE.setValueOfBooleanFieldEditor(ManagerConstant.PROJECT_BACKUP, true);
        UiCorePlugin.getDefault().savePluginPreferences();
    }
    
    @Override
    public boolean performOk() {
//        alwaysrunStandaloneBoolean = alwaysrunStandaloneButton.getSelection();
//        PreferenceUtil.INSTANCE.setValueOfBooleanFieldEditor(ManagerConstant.ALWAYS_RUN_STAND_ALONE, alwaysrunStandaloneBoolean);
//
//        alwaysrunPersistableResourceBoolean = alwaysrunPersistableResourceButton.getSelection();
//        PreferenceUtil.INSTANCE.setValueOfBooleanFieldEditor(ManagerConstant.ALWAYS_RUN_PERSISTABLE_RESOURCE, alwaysrunPersistableResourceBoolean);

        useModelCloseBoolean = useModelCloseButton.getSelection();
        PreferenceUtil.INSTANCE.setValueOfBooleanFieldEditor(ManagerConstant.USE_MODEL_CLOSE, useModelCloseBoolean);
        
        projectBackupBoolean = projectBackupButton.getSelection();
        PreferenceUtil.INSTANCE.setValueOfBooleanFieldEditor(ManagerConstant.PROJECT_BACKUP, projectBackupBoolean);
        UiCorePlugin.getDefault().savePluginPreferences();
        
        return super.performOk();
    }
}
