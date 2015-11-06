/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.realization.sequencediagram.command;

import java.util.List;

import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.LifeLineNode;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.core.diagram.figure.FigureConstant;
import nexcore.tool.uml.ui.realization.sequencediagram.util.SequenceUtil;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.PackageableElement;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.command</li>
 * <li>설 명 : CreateDestructionEventNodeCommand</li>
 * <li>작성일 : 2009. 12. 22.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class CreateDestructionEventNodeCommand extends Command {

    /** WIDTH_MINIMUM_SIZE */
    private static final int WIDTH_MINIMUM_SIZE = 30;

    /** HEIGHT_MINIMUM_SIZE */
    private static final int HEIGHT_MINIMUM_SIZE = 30;

    /** request */
    private CreateRequest request;

    /** parentNodeModel */
    private AbstractNode parentNodeModel;

    /** notationNode */
    private NotationNode notationNode;

    /** location */
    private Point location;

    /**
     * @param editDomain
     * @param parentNodeModel
     * @param request
     */
    public CreateDestructionEventNodeCommand(Object parentNodeModel, CreateRequest request, Point location) {
        this.parentNodeModel = (AbstractNode) parentNodeModel;
        this.request = request;
        this.location = location;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {
        notationNode = (NotationNode) request.getNewObject();

        notationNode.setX(location.x);
        notationNode.setY(location.y);
        if (NodeType.LIFELINE.equals(notationNode.getNodeType())) {
            notationNode.setX(20);
            notationNode.setY(20);
        }
        Dimension size = request.getSize();

        if (size != null) {
            if (size.width > WIDTH_MINIMUM_SIZE) {
                notationNode.setWidth(size.width);
            } else {
                notationNode.setWidth(WIDTH_MINIMUM_SIZE);
            }
            if (size.height > HEIGHT_MINIMUM_SIZE) {
                notationNode.setHeight(size.height);
            } else {
                notationNode.setHeight(HEIGHT_MINIMUM_SIZE);
            }
        } else {
            Dimension dimension = FigureConstant.getFigureDimension(notationNode.getNodeType());
            notationNode.setWidth(dimension.width);
            notationNode.setHeight(dimension.height);
        }
        Element umlModel = notationNode.getUmlModel();
        PackageableElement parentElement = null;

        if (umlModel instanceof NamedElement) {
            if (false == request.getNewObjectType().equals(NodeType.TEXT)
                && false == request.getNewObjectType().equals(NodeType.NOTE) && null != request.getNewObjectType()) {
                parentElement = UMLManager.getParent(this.parentNodeModel);
                ((NamedElement) umlModel).setName(UMLManager.getPackagedUniqueName((org.eclipse.uml2.uml.Namespace) parentElement,
                    ((NamedElement) umlModel).getName()));
            }

            notationNode.setName(((NamedElement) umlModel).getName());
        }
        redo();

    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    @Override
    public void redo() {
        // Element diagramModel = this.parentNodeModel.getUmlModel();
        Element umlModel = notationNode.getUmlModel();
        PackageableElement parentElement = null;
        if (umlModel instanceof Lifeline) {
            parentElement = UMLManager.getParent(this.parentNodeModel);
            Interaction interaction = (Interaction) parentElement;
            interaction.getLifelines().add((Lifeline) umlModel);
        } else if (false == request.getNewObjectType().equals(NodeType.TEXT)
            && false == request.getNewObjectType().equals(NodeType.NOTE) && null != request.getNewObjectType()) {
            parentElement = UMLManager.getParentPackage(this.parentNodeModel);
            ((org.eclipse.uml2.uml.Package) parentElement).getPackagedElements().add((PackageableElement) umlModel);

        }
        notationNode.setParent(parentElement);

        if (parentNodeModel instanceof Diagram) {
            ((Diagram) parentNodeModel).getNodeList().add(notationNode);
        } else if (parentNodeModel instanceof ContainerNode) {
            ((ContainerNode) parentNodeModel).getNodeList().add(notationNode);
        }

    }

    /**
     * 생성되는 위치에 따라서 다이어그램 내의 다른 LifeLine의 위치를 조정한다. void
     */
    @SuppressWarnings("unused")
    private void setLifeLineLayout() {
        List<LifeLineNode> lifeLineNodeList = SequenceUtil.ascSortedLifeLineNodeListByXValue(SequenceUtil.getOnlyLifeLineNodeList(((Diagram) parentNodeModel).getNodeList()));

        LifeLineNode lifeLineNode;
        LifeLineNode othersLifeLineNode;

        AbstractNode lastNode = lifeLineNodeList.get(lifeLineNodeList.size() - 1);
        notationNode.setX(lastNode.getX() + lastNode.getWidth() + 20);

        for (int i = 0; i < lifeLineNodeList.size(); i++) {
            lifeLineNode = (LifeLineNode) lifeLineNodeList.get(i);
            if (((lifeLineNode.getX() + lifeLineNode.getWidth() / 2)) > request.getLocation().x) {

                notationNode.setX(lifeLineNode.getX());

                for (int k = i; k < lifeLineNodeList.size(); k++) {
                    othersLifeLineNode = (LifeLineNode) lifeLineNodeList.get(k);
                    othersLifeLineNode.setX(othersLifeLineNode.getX() + notationNode.getWidth() + 20);
                }

                break;
            }
        }

        if (parentNodeModel instanceof Diagram) {
            ((Diagram) parentNodeModel).getNodeList().add(notationNode);
        } else if (parentNodeModel instanceof ContainerNode) {
            ((ContainerNode) parentNodeModel).getNodeList().add(notationNode);
        }

    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    @Override
    public void undo() {
        Element umlModel = notationNode.getUmlModel();

        if (umlModel instanceof Lifeline) {
            Interaction interaction = (Interaction) notationNode.getParent();
            interaction.getLifelines().remove((Lifeline) umlModel);
        } else if (false == request.getNewObjectType().equals(NodeType.TEXT)
            && false == request.getNewObjectType().equals(NodeType.NOTE) && null != request.getNewObjectType()) {
            org.eclipse.uml2.uml.Package parentElement = (org.eclipse.uml2.uml.Package) notationNode.getParent();
            parentElement.getPackagedElements().remove(umlModel);
        }

        if (parentNodeModel instanceof Diagram) {
            ((Diagram) parentNodeModel).getNodeList().remove(notationNode);
        } else if (parentNodeModel instanceof ContainerNode) {
            ((ContainerNode) parentNodeModel).getNodeList().remove(notationNode);
        }
    }

}
