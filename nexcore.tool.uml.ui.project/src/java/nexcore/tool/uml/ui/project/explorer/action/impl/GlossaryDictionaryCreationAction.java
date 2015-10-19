/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.project.explorer.action.impl;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.ui.project.explorer.action.AbstractUMLExplorerExtendedAction;
import nexcore.tool.uml.ui.project.explorer.command.UMLExplorerExtendedCommand;
import nexcore.tool.uml.ui.project.explorer.command.impl.GlossaryDictionaryCreationCommand;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.action.impl</li>
 * <li>설 명 : GlossaryDictionaryCreationAction</li>
 * <li>작성일 : 2010. 12. 6.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class GlossaryDictionaryCreationAction extends AbstractUMLExplorerExtendedAction {

    /**
     * fileName
     */
    private String fileName = null;
        
    /**
     * @see nexcore.tool.uml.ui.project.explorer.action.AbstractUMLExplorerExtendedAction#getTaskName()
     */
    @Override
    public String getTaskName() {
        return UMLMessage.LABEL_GLOSSARY_DICTIONARY_CREATION;
    }

    /**
     * @see nexcore.tool.uml.ui.project.explorer.action.AbstractUMLExplorerExtendedAction#configureCommand(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public CompoundCommand configureCommand(IProgressMonitor monitor) {
        CompoundCommand compoundCommand = new CompoundCommand();

        UMLExplorerExtendedCommand command = null;
        if (getDomain() != null && getSelectedElementList() != null && getShell() != null && monitor != null) {
            command = new GlossaryDictionaryCreationCommand(getDomain(), getSelectedElementList(), fileName, getShell(), monitor);
        }

        compoundCommand.append(command);
        return compoundCommand;
    }

    /**
     * @see nexcore.tool.uml.ui.project.explorer.action.AbstractUMLExplorerExtendedAction#run()
     */
    @Override
    public void run() {
        if(getSelectedElementList() == null || getSelectedElementList().size() == 0) {
            return;
        }
        
        Element analysisObject = getSelectedElementList().get(0);
        
        StringBuffer glossaryDictionaryFileName = new StringBuffer();
        if(analysisObject instanceof NamedElement) {
            if(((NamedElement) analysisObject).getName().length() > 0) {
                glossaryDictionaryFileName.append(((NamedElement) analysisObject).getName());
            } else {
                glossaryDictionaryFileName.append(UMLMessage.LABEL_GLOSSARY_DICTIONARY_DEFAULT_FILE_NAME);
            }

            if(!glossaryDictionaryFileName.toString().endsWith(UMLMessage.LABEL_GLOSSARY_DICTIONARY_FILE_POSTFIX)) {
                glossaryDictionaryFileName.append(UMLMessage.LABEL_GLOSSARY_DICTIONARY_FILE_POSTFIX);
            }
        }
        
        FileDialog dialog = new FileDialog(getShell(), SWT.SAVE);

        dialog.setFilterNames(new String[] { "*.xls" }); //$NON-NLS-1$
        dialog.setFilterExtensions(new String[] { "*.xls" }); //$NON-NLS-1$

        if (glossaryDictionaryFileName.toString() != null) {
            dialog.setFileName(glossaryDictionaryFileName.append(".xls").toString()); //$NON-NLS-1$
        }

        fileName = dialog.open();
        
        if (fileName != null) {
            super.run();
        }
    }
}
