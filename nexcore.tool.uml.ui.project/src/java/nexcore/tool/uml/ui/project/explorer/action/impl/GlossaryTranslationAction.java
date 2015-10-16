/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.project.explorer.action.impl;

import java.io.File;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.ui.core.project.extension.UMLCommonCommand;
import nexcore.tool.uml.ui.project.explorer.action.AbstractUMLExplorerExtendedAction;
import nexcore.tool.uml.ui.project.explorer.command.impl.GlossaryTranslationCommand;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.action.impl</li>
 * <li>설 명 : GlossaryTranslationAction</li>
 * <li>작성일 : 2010. 12. 6.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class GlossaryTranslationAction extends AbstractUMLExplorerExtendedAction {
    
    /**
     * fileName
     */
    private String fileName = null;
    
    /**
     * @see nexcore.tool.uml.ui.project.explorer.action.AbstractUMLExplorerExtendedAction#getTaskName()
     */
    @Override
    public String getTaskName() {
        return UMLMessage.LABEL_GLOSSARY_TRANSLATION;
    }

    /**
     * @see nexcore.tool.uml.ui.project.explorer.action.AbstractUMLExplorerExtendedAction#configureCommand(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public CompoundCommand configureCommand(IProgressMonitor monitor) {
        CompoundCommand compoundCommand = new CompoundCommand();

        UMLCommonCommand command = null;
        if (getDomain() != null && getSelectedElementList() != null && getShell() != null && monitor != null) {
            command = new GlossaryTranslationCommand(getDomain(), getSelectedElementList(), fileName, getShell(), getTaskName(), monitor);
        }

        compoundCommand.append(command);
        return compoundCommand;
    }

    /**
     * @see nexcore.tool.uml.ui.project.explorer.action.AbstractUMLExplorerExtendedAction#run()
     */
    @Override
    public void run() {

        FileDialog dialog = new FileDialog(getShell(), SWT.OPEN);

        dialog.setFilterNames(new String[] { "*.xls" }); //$NON-NLS-1$
        dialog.setFilterExtensions(new String[] { "*.xls" }); //$NON-NLS-1$


        fileName = dialog.open();
        if (null == fileName) {
        	return;
        }
        
        File file = new File(fileName);
        if (!file.exists()) {
            MessageDialog.openWarning(getShell(),
                UMLMessage.LABEL_GLOSSARY_TRANSLATION,
                UMLMessage.MESSAGE_ERROR_CANT_READ_THE_GLOSSARY_DICTIONARY);
            return;
        }

        
        if (fileName != null) {
            super.run();
        }
    }
}
