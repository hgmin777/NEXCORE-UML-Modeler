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
 * <li>설 명 : GlossaryPreferencePage</li>
 * <li>작성일 : 2011. 10. 11.</li>
 * <li>작성자 : 강석찬</li>
 * </ul>
 */
public class GlossaryPreferencePage extends AbstractPreferencePage implements IWorkbenchPreferencePage {

    private Button fButtonPackage;

    private Button fButtonClass;

    private Button fButtonInterface;

    private Button fButtonOperation;

    private Button fButtonAttribute;

    private Button fButtonComponent;

    private Button fButtonCollaboration;

    private Button fButtonInteraction;

    private Button fButtonDiagram;
    
    private Button fButtonParameter;

    private Button fButtonOnlyKorean;

    /**
     * @see nexcore.tool.uml.ui.preferences.pages.AbstractPreferencePage#doDefaultStore()
     */
    @Override
    protected void doDefaultStore() {
        PreferenceUtil.INSTANCE.setValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_PACKAGE,
            true);
        PreferenceUtil.INSTANCE.setValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_CLASS,
            true);
        PreferenceUtil.INSTANCE.setValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_INTERFACE,
            true);
        PreferenceUtil.INSTANCE.setValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_OPERATION,
            true);
        PreferenceUtil.INSTANCE.setValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_ATTRIBUTE,
            true);
        PreferenceUtil.INSTANCE.setValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_COMPONENT,
            true);
        PreferenceUtil.INSTANCE.setValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_COLLABORATION,
            true);
        PreferenceUtil.INSTANCE.setValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_INTERACTION,
            true);
        PreferenceUtil.INSTANCE.setValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_DIAGRAM,
            true);
        PreferenceUtil.INSTANCE.setValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_PARAMETER,
            true);
        PreferenceUtil.INSTANCE.setValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_ONLY_KOREAN,
            false);        

        UiCorePlugin.getDefault().savePluginPreferences();
        // PreferenceUtil.INSTANCE.setIsDefaultPresented(true);
    }

    /**
     * @see nexcore.tool.uml.ui.preferences.pages.AbstractPreferencePage#doLoad()
     */
    @Override
    protected void doLoad() {
        fButtonPackage.setSelection(PreferenceUtil.INSTANCE.getValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_PACKAGE));
        fButtonClass.setSelection(PreferenceUtil.INSTANCE.getValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_CLASS));
        fButtonInterface.setSelection(PreferenceUtil.INSTANCE.getValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_INTERFACE));
        fButtonOperation.setSelection(PreferenceUtil.INSTANCE.getValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_OPERATION));
        fButtonAttribute.setSelection(PreferenceUtil.INSTANCE.getValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_ATTRIBUTE));
        fButtonComponent.setSelection(PreferenceUtil.INSTANCE.getValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_COMPONENT));
        fButtonCollaboration.setSelection(PreferenceUtil.INSTANCE.getValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_COLLABORATION));
        fButtonInteraction.setSelection(PreferenceUtil.INSTANCE.getValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_INTERACTION));
        fButtonDiagram.setSelection(PreferenceUtil.INSTANCE.getValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_DIAGRAM));
        fButtonParameter.setSelection(PreferenceUtil.INSTANCE.getValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_PARAMETER));
        fButtonOnlyKorean.setSelection(PreferenceUtil.INSTANCE.getValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_ONLY_KOREAN));
         }

    /**
     * @see nexcore.tool.uml.ui.preferences.pages.AbstractPreferencePage#doStore()
     */
    @Override
    protected void doStore() {
        PreferenceUtil.INSTANCE.setValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_PACKAGE,
            fButtonPackage.getSelection());
        PreferenceUtil.INSTANCE.setValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_CLASS,
            fButtonClass.getSelection());
        PreferenceUtil.INSTANCE.setValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_INTERFACE,
            fButtonInterface.getSelection());
        PreferenceUtil.INSTANCE.setValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_OPERATION,
            fButtonOperation.getSelection());
        PreferenceUtil.INSTANCE.setValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_ATTRIBUTE,
            fButtonAttribute.getSelection());
        PreferenceUtil.INSTANCE.setValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_COMPONENT,
            fButtonComponent.getSelection());
        PreferenceUtil.INSTANCE.setValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_COLLABORATION,
            fButtonCollaboration.getSelection());
        PreferenceUtil.INSTANCE.setValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_INTERACTION,
            fButtonInteraction.getSelection());
        PreferenceUtil.INSTANCE.setValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_DIAGRAM,
            fButtonDiagram.getSelection());
        PreferenceUtil.INSTANCE.setValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_PARAMETER,
            fButtonParameter.getSelection());
        PreferenceUtil.INSTANCE.setValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_ONLY_KOREAN,
            fButtonOnlyKorean.getSelection());
     }

    /**
     * @see nexcore.tool.uml.ui.preferences.pages.AbstractPreferencePage#initDefault()
     */
    @Override
    protected void initDefault() {
        fButtonPackage.setSelection(true);
        fButtonClass.setSelection(true);
        fButtonInterface.setSelection(true);
        fButtonOperation.setSelection(true);
        fButtonAttribute.setSelection(true);
        fButtonComponent.setSelection(true);
        fButtonCollaboration.setSelection(true);
        fButtonInteraction.setSelection(true);
        fButtonDiagram.setSelection(true);
        fButtonParameter.setSelection(true);
        fButtonOnlyKorean.setSelection(false);
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

        fButtonOnlyKorean = new Button(container, SWT.CHECK);
//        fButtonOnlyKorean.setLayoutData(groupGridData);
        fButtonOnlyKorean.setText(UMLMessage.LABEL_GLOSSARY_ONLY_KOREAN);
        
        Group group = new Group(container, SWT.NULL);
        group.setText(UMLMessage.LABEL_GLOSSARY_INCLUDED_COMPONENTS);
        GridLayout layout = new GridLayout(1, true);
        group.setLayout(layout);

        GridData groupGridData = new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_HORIZONTAL);
//        groupGridData.horizontalSpan = 4;
        // groupGridData.verticalSpan = 2;
        group.setLayoutData(groupGridData);

        fButtonPackage = new Button(group, SWT.CHECK);
        fButtonPackage.setLayoutData(groupGridData);
        fButtonPackage.setText(UMLMessage.UML_PACKAGE);

        fButtonClass = new Button(group, SWT.CHECK);
        fButtonClass.setLayoutData(groupGridData);
        fButtonClass.setText(UMLMessage.UML_CLASS);

        fButtonInterface = new Button(group, SWT.CHECK);
        fButtonInterface.setLayoutData(groupGridData);
        fButtonInterface.setText(UMLMessage.UML_INTERFACE);

        fButtonOperation = new Button(group, SWT.CHECK);
        fButtonOperation.setLayoutData(groupGridData);
        fButtonOperation.setText(UMLMessage.UML_OPERATION);

        fButtonAttribute = new Button(group, SWT.CHECK);
        fButtonAttribute.setLayoutData(groupGridData);
        fButtonAttribute.setText(UMLMessage.UML_PROPERTY);

        fButtonComponent = new Button(group, SWT.CHECK);
        fButtonComponent.setLayoutData(groupGridData);
        fButtonComponent.setText(UMLMessage.UML_COMPONENT);

        fButtonCollaboration = new Button(group, SWT.CHECK);
        fButtonCollaboration.setLayoutData(groupGridData);
        fButtonCollaboration.setText(UMLMessage.UML_COLLABORATION);

        fButtonInteraction = new Button(group, SWT.CHECK);
        fButtonInteraction.setLayoutData(groupGridData);
        fButtonInteraction.setText(UMLMessage.UML_INTERACTION);

        fButtonDiagram = new Button(group, SWT.CHECK);
        fButtonDiagram.setLayoutData(groupGridData);
        fButtonDiagram.setText(UMLMessage.LABEL_DIAGRAM);

        fButtonParameter = new Button(group, SWT.CHECK);
        fButtonParameter.setLayoutData(groupGridData);
        fButtonParameter.setText(UMLMessage.LABEL_PARAMETER);
        
    }

    /**
     * @see nexcore.tool.uml.ui.preferences.pages.AbstractPreferencePage#initializer()
     */
    @Override
    protected void initializer() {
    }

    /**
     * @see nexcore.tool.uml.ui.preferences.pages.AbstractPreferencePage#doCancel()
     */
    @Override
    protected void doCancel() {
        // TODO Auto-generated method stub
    }
}
