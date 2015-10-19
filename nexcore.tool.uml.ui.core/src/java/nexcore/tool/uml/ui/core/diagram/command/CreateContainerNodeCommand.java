/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.core.diagram.command;

import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.ui.core.diagram.editor.AbstractDiagramEditor;
import nexcore.tool.uml.ui.core.diagram.editor.palette.NewSelectionTool;
import nexcore.tool.uml.ui.core.diagram.figure.FigureConstant;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ActivityPartition;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.PackageableElement;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.command</li>
 * <li>설 명 : CreateContainerNodeCommand</li>
 * <li>작성일 : 2011. 1. 25.</li>
 * <li>작성자 : zerotae</li>
 * </ul>
 */
public class CreateContainerNodeCommand extends Command {

    /** WIDTH_MINIMUM_SIZE */
    public static final int WIDTH_MINIMUM_SIZE = 500;

    /** HEIGHT_MINIMUM_SIZE */
    public static final int HEIGHT_MINIMUM_SIZE = 700;

    /** request */
    public CreateRequest request;

    /** parentNodeModel */
    public AbstractNode parentNodeModel;

    /** ContainerNode */
    public ContainerNode containerNode;

    /** location */
    public Point location;

    /**
     * @param editDomain
     * @param parentNodeModel
     * @param request
     */
    public CreateContainerNodeCommand(EditDomain editDomain, Object parentNodeModel, CreateRequest request,
    Point location) {
        this.parentNodeModel = (AbstractNode) parentNodeModel;
        this.request = request;
        this.location = location;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {
        
        if( request.getNewObject() instanceof ContainerNode ) {
            containerNode = (ContainerNode) request.getNewObject();
        }
        if( null == containerNode ) {
            return;
        }

        setLocationAndSize();

        Element umlModel = containerNode.getUmlModel();
        PackageableElement parentElement = null;

        if (umlModel instanceof NamedElement) {
            if (umlModel instanceof org.eclipse.uml2.uml.ActivityPartition
                || umlModel instanceof org.eclipse.uml2.uml.ActivityNode
                || umlModel instanceof org.eclipse.uml2.uml.Action) {
                parentElement = UMLManager.getParent(this.parentNodeModel);

            } else if (umlModel instanceof Lifeline) {
                parentElement = UMLManager.getParent(this.parentNodeModel);

            } else if (false == request.getNewObjectType().equals(NodeType.TEXT)
                && false == request.getNewObjectType().equals(NodeType.NOTE) && null != request.getNewObjectType()) {
                parentElement = UMLManager.getParentPackage(this.parentNodeModel);
                ((NamedElement) umlModel).setName(UMLManager.getPackagedUniqueName((org.eclipse.uml2.uml.Namespace) parentElement,
                    ((NamedElement) umlModel).getName()));
            }

            containerNode.setName(((NamedElement) umlModel).getName());
        }
        redo();

        ((AbstractDiagramEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor()).getDiagramGraphicalViewer()
            .getEditDomain()
            .setDefaultTool(new NewSelectionTool());

    }

    /**
     * 노드의 위치와 사이즈 설정 void
     */
    private void setLocationAndSize() {
        containerNode.setX(location.x);
        containerNode.setY(location.y);

        Dimension size = request.getSize();

        if (size != null) {
            if (size.width > WIDTH_MINIMUM_SIZE) {
                containerNode.setWidth(size.width);
            } else {
                containerNode.setWidth(WIDTH_MINIMUM_SIZE);
            }
            if (size.height > HEIGHT_MINIMUM_SIZE) {
                containerNode.setHeight(size.height);
            } else {
                containerNode.setHeight(HEIGHT_MINIMUM_SIZE);
            }
        } else {
            Dimension dimension = FigureConstant.getFigureDimension(containerNode.getNodeType());
            containerNode.setWidth(dimension.width);
            containerNode.setHeight(dimension.height);
        }
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    @Override
    public void redo() {
        // Element diagramModel = this.parentNodeModel.getUmlModel();
        Element umlModel = containerNode.getUmlModel();
        PackageableElement parentElement = null;
        if (umlModel instanceof org.eclipse.uml2.uml.ActivityPartition) {
            parentElement = UMLManager.getParent(this.parentNodeModel);
            Activity activity = (Activity) parentElement;
            activity.getPartitions().add((ActivityPartition) umlModel);
        } else if (umlModel instanceof org.eclipse.uml2.uml.ActivityNode
            || umlModel instanceof org.eclipse.uml2.uml.Action) {
            parentElement = UMLManager.getParent(this.parentNodeModel);
            Activity activity = (Activity) parentElement;
            activity.getNodes().add((ActivityNode) umlModel);
        } else if (umlModel instanceof Lifeline) {
            parentElement = UMLManager.getParent(this.parentNodeModel);
            Interaction interaction = (Interaction) parentElement;
            interaction.getLifelines().add((Lifeline) umlModel);
        } else if (false == request.getNewObjectType().equals(NodeType.TEXT)
            && false == request.getNewObjectType().equals(NodeType.NOTE) && null != request.getNewObjectType()) {
            parentElement = UMLManager.getParentPackage(this.parentNodeModel);
            ((org.eclipse.uml2.uml.Package) parentElement).getPackagedElements().add((PackageableElement) umlModel);

        }
        containerNode.setParent(parentElement);
        if (parentNodeModel instanceof Diagram) {
            ((Diagram) parentNodeModel).getNodeList().add(containerNode);
        } else if (parentNodeModel instanceof ContainerNode) {
            ((ContainerNode) parentNodeModel).getNodeList().add(containerNode);
        }

    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    @Override
    public void undo() {
        Element umlModel = containerNode.getUmlModel();

        if (umlModel instanceof org.eclipse.uml2.uml.ActivityNode || umlModel instanceof org.eclipse.uml2.uml.Action) {
            Activity activity = (Activity) containerNode.getParent();
            activity.getNodes().remove(umlModel);
        } else if (umlModel instanceof Lifeline) {
            Interaction interaction = (Interaction) containerNode.getParent();
            interaction.getLifelines().remove((Lifeline) umlModel);
        } else if (false == request.getNewObjectType().equals(NodeType.TEXT)
            && false == request.getNewObjectType().equals(NodeType.NOTE) && null != request.getNewObjectType()) {
            org.eclipse.uml2.uml.Package parentElement = (org.eclipse.uml2.uml.Package) containerNode.getParent();
            parentElement.getPackagedElements().remove(umlModel);
        }

        if (parentNodeModel instanceof Diagram) {
            ((Diagram) parentNodeModel).getNodeList().remove(containerNode);
        } else if (parentNodeModel instanceof ContainerNode) {
            ((ContainerNode) parentNodeModel).getNodeList().remove(containerNode);
        }
    }

}
