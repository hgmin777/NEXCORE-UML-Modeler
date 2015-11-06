/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */


package nexcore.tool.uml.ui.project.explorer.command;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.UMLHelper;
import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.util.ProjectUtil;

import org.eclipse.gef.commands.Command;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.PackageableElement;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.newproject</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.command</li>
 * <li>설 명 : CreateDiagramCommand</li>
 * <li>작성일 : 2009. 12. 1.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class CreateDiagramCommand extends Command {

    /** 생성을 지시한 부모 객체 */
    private Element parent;

    /** 액션 ID */
    private String actionId;

    /**
     * 생성자
     */
    public CreateDiagramCommand(Element parent, String actionId) {
        this.parent = parent;
        this.actionId = actionId;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {

        Diagram diagram = null;
        if (UICoreConstant.PROJECT_CONSTANTS__ClassDiagramID.equals(actionId)) {
            diagram = UMLHelper.createClassDiagram((PackageableElement) parent,
                UMLManager.getDiagramUniqueName(UMLMessage.UML_CLASSDIAGRAM, parent));
        } else if (UICoreConstant.PROJECT_CONSTANTS__ComponentDiagramID.equals(actionId)) {
            diagram = UMLHelper.createComponentDiagram((PackageableElement) parent,
                UMLManager.getDiagramUniqueName(UMLMessage.UML_COMPONENTDIAGRAM, parent));
        } else if (UICoreConstant.PROJECT_CONSTANTS__UsecaseDiagramID.equals(actionId)) {
            diagram = UMLHelper.createUseCaseDiagram((PackageableElement) parent,
                UMLManager.getDiagramUniqueName(UMLMessage.UML_USECASEDIAGRAM, parent));
        } else if (UICoreConstant.PROJECT_CONSTANTS__ActivityDiagramID.equals(actionId)) {
            diagram = UMLHelper.createActivityDiagram((PackageableElement) parent,
                UMLManager.getDiagramUniqueName(UMLMessage.UML_ACTIVITYDIAGRAM, parent));
        } else if (UICoreConstant.PROJECT_CONSTANTS__SequenceDiagramID.equals(actionId)) {
            diagram = UMLHelper.createSequenceDiagram((PackageableElement) parent,
                UMLManager.getDiagramUniqueName(UMLMessage.UML_SEQUENCEDIAGRAM, parent));
        }
        // FocusRegistry.setFocused(diagram);
        // ProjectUtil.refreshExplorer(parent, diagram);
        ProjectUtil.openDiagram(diagram);

        // RenameAction renameAction = new RenameAction(diagram);
        // renameAction.run(null);

    }

}
