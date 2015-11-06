/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.project.editor.command;

import nexcore.tool.uml.model.projectinformation.ProjectElement;
import nexcore.tool.uml.ui.core.editor.command.HandleProjectRecordingCommand;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.uml2.uml.Package;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.editor.command</li>
 * <li>설 명 : HandleProjectCommand</li>
 * <li>작성일 : 2010. 1. 18.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class HandleProjectCommand extends HandleProjectRecordingCommand {

    /**
     * @param domain
     * @param element
     * @param projectElement
     * @param applyProjectInformation
     */
    public HandleProjectCommand(TransactionalEditingDomain domain, Package element, ProjectElement projectElement,
    boolean applyProjectInformation) {
        super(domain, element, projectElement, applyProjectInformation);
    }
    
}
