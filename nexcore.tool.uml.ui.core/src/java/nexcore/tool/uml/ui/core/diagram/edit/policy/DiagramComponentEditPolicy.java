/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.diagram.edit.policy;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.UMLHelper;
import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.manager.transaction.UMLDiagramCommandStack;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.core.diagram.command.DeleteNodeCommand;
import nexcore.tool.uml.ui.core.diagram.edit.part.AbstractChildCompartmentEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.AbstractDiagramEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.AttributeEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.OperationEditPart;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gef.requests.SelectionRequest;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Property;


/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.edit.policy</li>
 * <li>설  명 : DiagramComponentEditPolicy</li>
 * <li>작성일 : 2009. 11. 16.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class DiagramComponentEditPolicy extends ComponentEditPolicy {

    /**
     * @see org.eclipse.gef.editpolicies.ComponentEditPolicy#getDeleteCommand(org.eclipse.gef.requests.GroupRequest)
     */
    @Override
    protected Command getDeleteCommand(GroupRequest request) {
        Command command;

        if (getHost() instanceof AbstractChildCompartmentEditPart) {
            if (!(getHost() instanceof AttributeEditPart) && !(getHost() instanceof OperationEditPart)) {
                return null;
            }
        }

        command = new DeleteNodeCommand();
        ((DeleteNodeCommand) command).setParent(getHost().getParent().getModel());
        ((DeleteNodeCommand) command).setNode(getHost().getModel());
        return command;

    }

    /**
     * targetEditPart
     */
    private EditPart targetEditPart;

    /**
     * request
     */
    private SelectionRequest request;

    /**
     * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#getTargetEditPart(org.eclipse.gef.Request)
     */
    @Override
    public EditPart getTargetEditPart(Request request) {
        // TODO Auto-generated method stub
       if (super.getTargetEditPart(request) != null)
            this.targetEditPart = super.getTargetEditPart(request);
        if (request instanceof SelectionRequest)
            this.request = (SelectionRequest) request;
        return super.getTargetEditPart(request);
    }

    /**
     * @see org.eclipse.gef.editpolicies.ComponentEditPolicy#getCommand(org.eclipse.gef.Request)
     */
    @Override
    public Command getCommand(Request request) {        
        return super.getCommand(request);
    }

    /**
     * diagramEditPart
     */
    private EditPart diagramEditPart;

    /**
     * getDiagramEditPart
     *  
     * @param host void
     */
    private void getDiagramEditPart(EditPart host) {
        if (host instanceof AbstractDiagramEditPart) {
            diagramEditPart = (AbstractDiagramEditPart) host;
        } else {
            getDiagramEditPart(host.getParent());
        }
    }

    /**
     * @see org.eclipse.gef.editpolicies.ComponentEditPolicy#getOrphanCommand()
     */
    @Override
    protected Command getOrphanCommand() {
        return super.getOrphanCommand();
    }

    /**
     * excuteCommand
     *  
     * @param sourceEditPart
     * @param diagramEditPart
     * @param targetNode void
     */
    private void excuteCommand(EditPart sourceEditPart, EditPart diagramEditPart, AbstractNode targetNode) {
        DragAndDropCommand dndCommand = new DragAndDropCommand(sourceEditPart, diagramEditPart, targetNode);
        ((UMLDiagramCommandStack) DomainRegistry.getUMLDomain().getCommandStackListener()).execute(dndCommand);
    }

    public class DragAndDropCommand extends Command {

        private EditPart sourceEditPart;

        private EditPart diagramEditPart;

        private AbstractNode targetNode;

        public DragAndDropCommand(EditPart sourceEditPart, EditPart diagramEditPart, AbstractNode targetNode) {
            this.sourceEditPart = sourceEditPart;
            this.diagramEditPart = diagramEditPart;
            this.targetNode = targetNode;
        }

        @Override
        public void execute() {
            // super.execute();
            AbstractNode sourceNode;
            Diagram diagram;
            Element element;
            Class classNode;
            DataType dataType;
            Enumeration enumeration;

            sourceNode = (AbstractNode) sourceEditPart.getModel();
            diagram = (Diagram) diagramEditPart.getModel();
            element = targetNode.getUmlModel();

            if (((NotationNode) element).getUmlModel() instanceof org.eclipse.uml2.uml.Class) {
                classNode = (Class) ((NotationNode) element).getUmlModel();
                Property property = UMLHelper.createProperty();
                property.setName((UMLManager.getPackagedUniqueName(classNode,
                    UMLMessage.getMessage(UMLMessage.UML_PROPERTY))));
                classNode.getOwnedAttributes().add(property);
            }

            else if (((NotationNode) element).getUmlModel() instanceof org.eclipse.uml2.uml.DataType) {
                dataType = (DataType) ((NotationNode) element).getUmlModel();
                Property property = UMLHelper.createProperty();
                property.setName((UMLManager.getPackagedUniqueName(dataType,
                    UMLMessage.getMessage(UMLMessage.UML_PROPERTY))));
                dataType.getOwnedAttributes().add(property);
            }

            else if (((NotationNode) element).getUmlModel() instanceof org.eclipse.uml2.uml.Enumeration) {
                enumeration = (Enumeration) ((NotationNode) element).getUmlModel();
                Property property = UMLHelper.createProperty();
                property.setName((UMLManager.getPackagedUniqueName(enumeration,
                    UMLMessage.getMessage(UMLMessage.UML_PROPERTY))));
                enumeration.getOwnedAttributes().add(property);
            }
        }
    }
}
