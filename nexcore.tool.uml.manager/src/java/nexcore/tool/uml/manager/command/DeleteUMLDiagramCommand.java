/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.manager.command;

import nexcore.tool.uml.manager.UMLHelper;
import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.model.umldiagram.Diagram;

import org.eclipse.gef.commands.Command;
import org.eclipse.uml2.uml.PackageableElement;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.manager</li>
 * <li>서브 업무명 : nexcore.tool.uml.manager.command</li>
 * <li>설  명 : DeleteUMLDiagramCommand</li>
 * <li>작성일 : 2009. 12. 28</li>
 * <li>작성자 : 한승일 </li>
 * </ul>
 */
public class DeleteUMLDiagramCommand extends Command {

    /** selected diagram */
    private Diagram diagram;

    /** parent */
    private PackageableElement parent;

    /**
     * @param diagram
     */
    public DeleteUMLDiagramCommand(Diagram diagram) {
        this.diagram = diagram;
        this.parent = UMLManager.getParent(this.diagram);
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {
        this.redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    @Override
    public void redo() {
        UMLManager.deleteElement(this.diagram);
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    @Override
    public void undo() {
        UMLHelper.setDiagram(parent, diagram);
    }

}
