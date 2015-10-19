/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.core.diagram.edit.policy;

import nexcore.tool.uml.manager.UMLPolicyManager;
import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.Attachement;
import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.Relation;
import nexcore.tool.uml.ui.core.diagram.command.CreateConnectionCommand;
import nexcore.tool.uml.ui.core.diagram.command.ReconnectConnectionCommand;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.UseCase;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.edit.policy</li>
 * <li>설 명 : DiagramGraphicalNodeEditPolicy</li>
 * <li>작성일 : 2009. 11. 17.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class DiagramGraphicalNodeEditPolicy extends GraphicalNodeEditPolicy {

    /**
     * @see org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#getConnectionCompleteCommand(org.eclipse.gef.requests.CreateConnectionRequest)
     */
    @Override
    protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
        
        CreateConnectionCommand startCommand = (CreateConnectionCommand) request.getStartCommand();
        AbstractNode source;
        AbstractNode target;
        AbstractView objNode = (AbstractNode) (getHost().getModel());

        if (objNode instanceof AbstractConnection) {
            return null;
        }

        source = (AbstractNode) startCommand.getSource();
        target = (AbstractNode) objNode;

        if (!UMLPolicyManager.isConnectableToTarget(startCommand.getConnection().getRelationType(),
            source.getNodeType(),
            target.getNodeType())) {
            return null;
        }

        startCommand.setTarget(target);
        if (!(getHost().getParent().getModel() instanceof Diagram)
            && !(getHost().getParent().getModel() instanceof ContainerNode)) {
            startCommand.setParent((AbstractNode) getHost().getParent().getParent().getModel());
        } else {
            startCommand.setParent((AbstractNode) getHost().getParent().getModel());
        }

        ConnectionAnchor targetAnchor = ((NodeEditPart) getHost()).getTargetConnectionAnchor(request);
        startCommand.setTargetAnchorPoint(targetAnchor.getLocation(request.getLocation()));

//        Relation relation = null;
        Element sourceElement = ((AbstractNode) request.getSourceEditPart().getModel()).getUmlModel();
        Element targetElement = ((AbstractNode) request.getTargetEditPart().getModel()).getUmlModel();
        if (sourceElement instanceof Actor || sourceElement instanceof UseCase) {
            if (sourceElement.equals(targetElement)) {
                return null;
            }
        }

        if (startCommand.canExecute()) {
            return startCommand;
        }
        return null;

    }

    /**
     * @see org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#getConnectionCreateCommand(org.eclipse.gef.requests.CreateConnectionRequest)
     */
    @Override
    protected Command getConnectionCreateCommand(CreateConnectionRequest request) {

        CreateConnectionCommand command = new CreateConnectionCommand();
        AbstractConnection connection = (AbstractConnection) request.getNewObject();

        AbstractView objNode = (AbstractNode) (getHost().getModel());

        if (objNode instanceof AbstractConnection) {
            return null;
        }
        AbstractNode abstractNode = (AbstractNode) objNode;

        if (!UMLPolicyManager.isConnectableFromSource(connection.getRelationType(), abstractNode.getNodeType())) {
            return null;
        }

        ConnectionAnchor sourceAnchor = ((NodeEditPart) getHost()).getSourceConnectionAnchor(request);
        command.setSourceAnchorPoint(sourceAnchor.getLocation(request.getLocation()));

        command.setConnection(connection);
        command.setSource((AbstractNode) getHost().getModel());
        request.setStartCommand(command);
        request.setSourceEditPart(getHost());

        return command;

    }

    /**
     * @see org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#getReconnectSourceCommand(org.eclipse.gef.requests.ReconnectRequest)
     */
    @Override
    protected Command getReconnectSourceCommand(ReconnectRequest request) {
        
        ReconnectConnectionCommand command = new ReconnectConnectionCommand();
        command.setConnectionModel(request.getConnectionEditPart().getModel());
        command.setNewSource(getHost().getModel());
        command.setDiagram((Diagram) (getHost().getParent()).getModel());

        ConnectionAnchor sourceAnchor = ((NodeEditPart) getHost()).getSourceConnectionAnchor(request);
        command.setSourceAnchorPoint(sourceAnchor.getLocation(request.getLocation()));

        Relation relation = null;
        Attachement attachement = null;
        if (request.getConnectionEditPart().getModel() instanceof Relation) {
            relation = (Relation) request.getConnectionEditPart().getModel();
            
            AbstractNode source, target;
            source = (AbstractNode) command.getNewSource();
            target = (AbstractNode) relation.getTarget();
            if (!UMLPolicyManager.isConnectableToTarget(relation.getRelationType(),
                source.getNodeType(),
                target.getNodeType())) {
                return null;
            }
        } else if (request.getConnectionEditPart().getModel() instanceof Attachement) {
            attachement = (Attachement) request.getConnectionEditPart().getModel();
            
            AbstractNode source, target;
            source = (AbstractNode) command.getNewSource();
            target = (AbstractNode) attachement.getTarget();
            if (!UMLPolicyManager.isConnectableToTarget(attachement.getRelationType(),
                source.getNodeType(),
                target.getNodeType())) {
                return null;
            }
        }



        // if (relation != null) {
        // if (relation.getTarget() instanceof AbstractNode) {
        // if (!((AbstractNode)
        // relation.getTarget()).getNodeType().equals(command.getNewSource().getNodeType()))
        // {
        // return null;
        // }
        // }
        // }
        //
        // if (relation != null) {
        // if (relation.getTarget() instanceof AbstractNode) {
        // if (((AbstractNode)
        // relation.getTarget()).getNodeType().equals(NodeType.ACTOR)
        // || ((AbstractNode)
        // relation.getTarget()).getNodeType().equals(NodeType.USE_CASE)) {
        // if
        // ((relation.getTarget().getUmlModel()).equals(command.getNewSource().getUmlModel()))
        // {
        // return null;
        // }
        // }
        // }
        // }

        return command;
    }

    /**
     * @see org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy#getReconnectTargetCommand(org.eclipse.gef.requests.ReconnectRequest)
     */
    @Override
    protected Command getReconnectTargetCommand(ReconnectRequest request) {
        
        ReconnectConnectionCommand command = new ReconnectConnectionCommand();
        command.setConnectionModel(request.getConnectionEditPart().getModel());
        command.setNewTarget(getHost().getModel());
        command.setDiagram((Diagram) (getHost().getParent()).getModel());

        ConnectionAnchor targetAnchor = ((NodeEditPart) getHost()).getTargetConnectionAnchor(request);
        command.setTargetAnchorPoint(targetAnchor.getLocation(request.getLocation()));

        Relation relation = null;
        Attachement attachement = null;
        if (request.getConnectionEditPart().getModel() instanceof Relation) {
            relation = (Relation) request.getConnectionEditPart().getModel();

            AbstractNode source, target;
            source = (AbstractNode) relation.getSource();
            target = (AbstractNode) command.getNewTarget();
            if (!UMLPolicyManager.isConnectableToTarget(relation.getRelationType(),
                source.getNodeType(),
                target.getNodeType())) {
                return null;
            }
        } else if (request.getConnectionEditPart().getModel() instanceof Attachement) {
            attachement = (Attachement) request.getConnectionEditPart().getModel();
            
            AbstractNode source, target;
            source = (AbstractNode) attachement.getSource();
            target = (AbstractNode) command.getNewTarget();
            if (!UMLPolicyManager.isConnectableToTarget(attachement.getRelationType(),
                source.getNodeType(),
                target.getNodeType())) {
                return null;
            }
        }


        
        // if (relation != null) {
        // if (relation.getSource() instanceof AbstractNode) {
        // if (!((AbstractNode)
        // relation.getSource()).getNodeType().equals(command.getNewTarget().getNodeType()))
        // {
        // return null;
        // }
        // }
        // }

        // if (relation != null) {
        // if (relation.getTarget() instanceof AbstractNode) {
        // if (((AbstractNode)
        // relation.getTarget()).getNodeType().equals(NodeType.ACTOR)
        // || ((AbstractNode)
        // relation.getTarget()).getNodeType().equals(NodeType.USE_CASE)) {
        // if
        // ((relation.getSource().getUmlModel()).equals(command.getNewTarget().getUmlModel()))
        // {
        // return null;
        // }
        // }
        // }
        // }

        return command;
    }

    
    /**
     * @see org.eclipse.gef.editpolicies.GraphicalEditPolicy#removeFeedback(org.eclipse.draw2d.IFigure)
     */
    @Override
    protected void removeFeedback(IFigure figure) {
        if( getFeedbackLayer().getChildren().isEmpty() ){
            return;
        } 
        if( !getFeedbackLayer().getChildren().contains(figure) ){
            return;
        }
        super.removeFeedback(figure);
    }
}
