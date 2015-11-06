/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */


package nexcore.tool.uml.ui.project.explorer.command.impl;

import java.util.List;

import nexcore.tool.mdd.core.extension.INotationModelHandler;
import nexcore.tool.mdd.core.registry.NotationModelHandlerRegistry;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.project.explorer.command.UMLExplorerExtendedCommand;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Element;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.command.impl</li>
 * <li>설 명 : VOPCDiagramCreationCommand</li>
 * <li>작성일 : 2010. 12. 2.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class VOPCDiagramCreationCommand extends UMLExplorerExtendedCommand {

    /** 노테이션 모델 핸들러 */
    private INotationModelHandler notationModelHandler;

    /**
     * 생성자
     * 
     * @param domain
     * @param selectedElementList
     * @param parentShell
     * @param monitor
     */
    public VOPCDiagramCreationCommand(TransactionalEditingDomain domain, List<Element> selectedElementList,
    Shell parentShell, IProgressMonitor monitor) {
        super(domain, selectedElementList, parentShell, monitor);
    }

    /**
     * @see nexcore.tool.uml.ui.project.explorer.command.UMLExplorerExtendedCommand#initialize()
     */
    @Override
    public void initialize() {
        if (NotationModelHandlerRegistry.getInstance() != null) {
            notationModelHandler = NotationModelHandlerRegistry.getInstance()
                .getHandlerInstance(UICoreConstant.NEXCORE_TOOL_UML_UI_PROJECT_NOTATION_MODEL_HANDLER);

            if (notationModelHandler != null && selectedElementList != null) { // &&
                // ruleSet
                // !=
                // null
                notationModelHandler.setElementList(selectedElementList);

                notationModelHandler.setProgressMonitor(monitor);
            }
        }
    }

    /**
     * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
     */
    @Override
    protected void doExecute() {
        notationModelHandler.handleClassDiagram();
    }

}
