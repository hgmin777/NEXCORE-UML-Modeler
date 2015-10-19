/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.project.explorer.command.impl;

import java.util.HashMap;
import java.util.List;

import nexcore.tool.mdd.core.extension.ISemanticModelHandler;
import nexcore.tool.mdd.core.registry.SemanticModelHandlerRegistry;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.util.PreferenceUtil;
import nexcore.tool.uml.ui.project.explorer.command.UMLExplorerExtendedCommand;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Element;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.command.impl</li>
 * <li>설 명 : GlossaryDictionaryCreationCommand</li>
 * <li>작성일 : 2010. 12. 6.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class GlossaryDictionaryCreationCommand extends UMLExplorerExtendedCommand {

    /** 시맨틱 모델 핸들러 */
    private ISemanticModelHandler semanticModelHandler;

    /**
     * 생성자
     * 
     * @param domain
     * @param selectedElementList
     * @param parentShell
     * @param monitor
     */
    public GlossaryDictionaryCreationCommand(TransactionalEditingDomain domain, List<Element> selectedElementList, String fileName,
    Shell parentShell, IProgressMonitor monitor) {
        super(domain, selectedElementList, parentShell, monitor);
        semanticModelHandler.setSharingObject(fileName);
    }

    /**
     * @see nexcore.tool.uml.ui.project.explorer.command.UMLExplorerExtendedCommand#initialize()
     */
    @Override
    public void initialize() {
        if (SemanticModelHandlerRegistry.getInstance() != null) {
            semanticModelHandler = SemanticModelHandlerRegistry.getInstance()
                .getHandlerInstance(UICoreConstant.NEXCORE_TOOL_UML_UI_PROJECT_SEMANTIC_MODEL_HANDLER);

            if (semanticModelHandler != null && selectedElementList != null) {
                semanticModelHandler.setElementList(selectedElementList);
                semanticModelHandler.setParentShell(parentShell);
                semanticModelHandler.setProgressMonitor(monitor);
            }
        }
    }

    /**
     * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
     */
    @Override
    protected void doExecute() {
        boolean result = false;

        semanticModelHandler.initializeGlossaryManagement();
        HashMap<String, Boolean> list = new HashMap<String, Boolean>();
        
        list.put(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_ONLY_KOREAN
            , PreferenceUtil.INSTANCE.getValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_ONLY_KOREAN));        
        list.put(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_PACKAGE
            , PreferenceUtil.INSTANCE.getValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_PACKAGE));
        list.put(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_CLASS
            , PreferenceUtil.INSTANCE.getValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_CLASS));
        list.put(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_INTERFACE
            , PreferenceUtil.INSTANCE.getValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_INTERFACE));
        list.put(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_OPERATION
            , PreferenceUtil.INSTANCE.getValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_OPERATION));
        list.put(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_ATTRIBUTE
            , PreferenceUtil.INSTANCE.getValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_ATTRIBUTE));
        list.put(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_COMPONENT
            , PreferenceUtil.INSTANCE.getValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_COMPONENT));
        list.put(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_COLLABORATION
            , PreferenceUtil.INSTANCE.getValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_COLLABORATION));
        list.put(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_INTERACTION
            , PreferenceUtil.INSTANCE.getValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_INTERACTION));
        list.put(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_DIAGRAM
            , PreferenceUtil.INSTANCE.getValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_DIAGRAM));
        list.put(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_PARAMETER
            , PreferenceUtil.INSTANCE.getValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_GLOSSARY_USE_PARAMETER));
        semanticModelHandler.setGenerateList(list);
        result = semanticModelHandler.createGlossaryDictionary();

        if (result) {
            MessageDialog.openInformation(parentShell,
                UMLMessage.LABEL_NOTIFICATION,
                UMLMessage.MESSAGE_GLOSSARY_DICTIONARY_GENERATION_HAS_BEEN_COMPLETED_SUCCESSFULLY); // Messages.getString("GenerateGlossaryCommand_DIALOG_MSG_COMPLETE"));
        } else {
            MessageDialog.openWarning(parentShell,
                UMLMessage.LABEL_NOTIFICATION,
                UMLMessage.MESSAGE_ERROR_GLOSSARY_DICTIONARY_GENERATION_FAILED); // Messages.getString("GenerateGlossaryCommand_DIALOG_MSG_FAILURE"));
        }
    }

}
