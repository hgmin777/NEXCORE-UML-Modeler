/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.project.explorer.command.impl;

import java.util.List;

import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.project.extension.UMLCommonCommand;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Element;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.command.impl</li>
 * <li>설 명 : GlossaryTranslationCommand</li>
 * <li>작성일 : 2010. 12. 6.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class GlossaryTranslationCommand extends UMLCommonCommand {

    /**
     * 생성자
     * 
     * @param domain
     * @param selectedElementList
     * @param parentShell
     * @param taskName
     * @param monitor
     */
    public GlossaryTranslationCommand(TransactionalEditingDomain domain, List<Element> selectedElementList, Shell parentShell, String taskName, IProgressMonitor monitor) {
        super(domain, selectedElementList, parentShell, taskName, monitor);
    }

    /**
     * 생성자
     * 
     * @param domain
     * @param selectedElementList
     * @param fileName
     * @param parentShell
     * @param taskName
     * @param monitor
     */
    public GlossaryTranslationCommand(TransactionalEditingDomain domain, List<Element> selectedElementList,
    String fileName, Shell parentShell, String taskName, IProgressMonitor monitor) {
        this(domain, selectedElementList, parentShell, taskName, monitor);
        this.sharingObject = fileName;
    }
    
    /**
     * @see nexcore.tool.uml.ui.core.project.extension.UMLCommonCommand#setExecutionUnitId()
     */
    @Override
    public void setExecutionUnitId() {
        this.executionUnitId = UICoreConstant.NEXCORE_TOOL_UML_GLOSSARY_TRANSLATION_COMMAND;
    }

}
