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

import org.eclipse.core.runtime.Preferences.IPropertyChangeListener;
import org.eclipse.core.runtime.Preferences.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.preferences</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.preferences.pages</li>
 * <li>설  명 : SequenceDiagramPreference</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : Bojun </li>
 * </ul>
 */
public class SequenceDiagramPreference extends AbstractPreferencePage implements IWorkbenchPreferencePage,
IPropertyChangeListener {

    /** btnReply */
    private Button btnReply;

    /** btnLifelinename */
    private Button btnLifelinename;

    /** btnMessageType */
    private Button btnMessageType;

    /** btnMessageParameter */
    private Button btnMessageParameter;
    
    /** btnReplyMessageName */
    private Button btnReplyMessageName;

    // /** btnFilter */
    // private Button btnFilter;

    /** strBoolean */
    private String strBoolean;

    /*
     * (non-Javadoc)
     * 
     * @see
     * nexcore.tool.uml.ui.preferences.pages.AbstractPreferencePage#doCancel()
     */
    @Override
    protected void doCancel() {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * nexcore.tool.uml.ui.preferences.pages.AbstractPreferencePage#doDefaultStore
     * ()
     */
    @Override
    protected void doDefaultStore() {
        strBoolean = "false";
        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_SEQUENCEDIAGRAM_VISIBILITY_REPLY,
            strBoolean);
        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_SEQUENCEDIAGRAM_VISIBILITY_LIFELINENAME,
            strBoolean);
        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_SEQUENCEDIAGRAM_VISIBILITY_MESSAGE_TYPE,
            strBoolean);
        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_SEQUENCEDIAGRAM_VISIBILITY_MESSAGE_PARAMETER,
            strBoolean);
        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_SEQUENCEDIAGRAM_VISIBILITY_MESSAGE_PARAMETER,
            strBoolean);
        // PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(
        // ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PROJECTEXPLORER_FILTER,
        // strBoolean);
        UiCorePlugin.getDefault().savePluginPreferences();
        // PreferenceUtil.INSTANCE.setIsDefaultPresented(false);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * nexcore.tool.uml.ui.preferences.pages.AbstractPreferencePage#doLoad()
     */
    @Override
    protected void doLoad() {
        strBoolean = PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_SEQUENCEDIAGRAM_VISIBILITY_REPLY);
        if (strBoolean.equals("true"))
            btnReply.setSelection(true);
        else
            btnReply.setSelection(false);

        strBoolean = PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_SEQUENCEDIAGRAM_VISIBILITY_LIFELINENAME);
        if (strBoolean.equals("true"))
            btnLifelinename.setSelection(true);
        else
            btnLifelinename.setSelection(false);

        strBoolean = PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_SEQUENCEDIAGRAM_VISIBILITY_MESSAGE_TYPE);
        if (strBoolean.equals("true"))
            btnMessageType.setSelection(true);
        else
            btnMessageType.setSelection(false);

        strBoolean = PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_SEQUENCEDIAGRAM_VISIBILITY_MESSAGE_PARAMETER);
        if (strBoolean.equals("true"))
            btnMessageParameter.setSelection(true);
        else
            btnMessageParameter.setSelection(false);

        strBoolean = PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_SEQUENCEDIAGRAM_VISIBILITY_REPLY_MESSAGENAME);
        if (strBoolean.equals("true"))
            btnReplyMessageName.setSelection(true);
        else
            btnReplyMessageName.setSelection(false);

        
        // strBoolean = PreferenceUtil.INSTANCE
        // .getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_SEQUENCEDIAGRAM_VISIBILITY_MESSAGEPARAMETER);
        // if (strBoolean.equals("true"))
        // btnFilter.setSelection(true);
        // else
        // btnFilter.setSelection(false);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * nexcore.tool.uml.ui.preferences.pages.AbstractPreferencePage#doStore()
     */
    @Override
    protected void doStore() {
        if (btnReply.getSelection())
            strBoolean = "true";
        else
            strBoolean = "false";
        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_SEQUENCEDIAGRAM_VISIBILITY_REPLY,
            strBoolean);
        if (btnLifelinename.getSelection())
            strBoolean = "true";
        else
            strBoolean = "false";
        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_SEQUENCEDIAGRAM_VISIBILITY_LIFELINENAME,
            strBoolean);
        if (btnMessageType.getSelection())
            strBoolean = "true";
        else
            strBoolean = "false";
        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_SEQUENCEDIAGRAM_VISIBILITY_MESSAGE_TYPE,
            strBoolean);
        if (btnMessageParameter.getSelection())
            strBoolean = "true";
        else
            strBoolean = "false";
        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_SEQUENCEDIAGRAM_VISIBILITY_MESSAGE_PARAMETER,
            strBoolean);
        
        if (btnReplyMessageName.getSelection())
            strBoolean = "true";
        else
            strBoolean = "false";
        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_SEQUENCEDIAGRAM_VISIBILITY_REPLY_MESSAGENAME,
            strBoolean);

        // if (btnFilter.getSelection())
        // strBoolean = "true";
        // else
        // strBoolean = "false";
        // PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(
        // ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_SEQUENCEDIAGRAM_VISIBILITY_MESSAGEPARAMETER,
        // strBoolean);
        UiCorePlugin.getDefault().savePluginPreferences();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * nexcore.tool.uml.ui.preferences.pages.AbstractPreferencePage#initDefault
     * ()
     */
    @Override
    protected void initDefault() {
        btnReply.setSelection(false);
        btnLifelinename.setSelection(false);
        // btnFilter.setSelection(false);
        btnMessageType.setSelection(false);
        btnMessageParameter.setSelection(false);
        btnReplyMessageName.setSelection(false);
        // PreferenceUtil.INSTANCE.setIsDefaultPresented(true);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * nexcore.tool.uml.ui.preferences.pages.AbstractPreferencePage#initializer
     * ()
     */
    @Override
    protected void initializer() {
        // TODO Auto-generated method stub

    }

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

        btnReply = new Button(container, SWT.CHECK);
        btnReply.setText(UMLMessage.LABEL_PREFERENCE_DIAGRAM_SEQUENCESEDIAGMRA_REPLY);

        btnLifelinename = new Button(container, SWT.CHECK);
        btnLifelinename.setText(UMLMessage.LABEL_PREFERENCE_DIAGRAM_SEQUENCESEDIAGMRA_LIFELINENAME);

        btnMessageType = new Button(container, SWT.CHECK);
        btnMessageType.setText(UMLMessage.LABEL_PREFERENCE_DIAGRAM_SEQUENCESEDIAGMRA_MESSAGE_TYPE);

        btnMessageParameter = new Button(container, SWT.CHECK);
        btnMessageParameter.setText(UMLMessage.LABEL_PREFERENCE_DIAGRAM_SEQUENCESEDIAGMRA_MESSAGE_PARAMETER);

        btnReplyMessageName = new Button(container, SWT.CHECK);
        btnReplyMessageName.setText(UMLMessage.LABEL_PREFERENCE_DIAGRAM_SEQUENCESEDIAGMRA_REPLY_MESSAGENAME);

        
        // btnFilter = new Button(container, SWT.CHECK);
        // btnFilter.setText(UMLMessage.LABEL_REMOVE_MESSAGEPARAMETER);

        return container;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.core.runtime.Preferences.IPropertyChangeListener#propertyChange
     * (org.eclipse.core.runtime.Preferences.PropertyChangeEvent)
     */
    public void propertyChange(PropertyChangeEvent event) {
        // TODO Auto-generated method stub

    }

}
