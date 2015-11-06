/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.core.diagram.command;

import java.util.List;

import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.LabelNode;
import nexcore.tool.uml.model.umldiagram.LabelType;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.core.diagram.edit.part.LabelNodeEditPart;
import nexcore.tool.uml.ui.core.util.UiUtil;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.uml2.uml.ActivityNode;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.daigram.command</li>
 * <li>설 명 : ChangeNodeConstraintCommand</li>
 * <li>작성일 : 2009. 11. 16.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class ChangeNodeConstraintCommand extends Command {

    /**
     * 현재의 constraint
     */
    private Rectangle newConstraint;

    /**
     * 이전의 constraint
     */
    private Rectangle oldConstraint;

    /** viewModel */
    private AbstractNode node;

    /** editPart */
    private EditPart editPart;

    /** changedSize */
    private Double changedSize;

    /**
     * direction
     */
    private int direction;

    /**
     * ChangeNodeConstraintCommand
     * @param editPart
     * @param constraint
     */
    public ChangeNodeConstraintCommand(EditPart editPart, Object constraint) {
        this.node = (AbstractNode) editPart.getModel();
        this.editPart = editPart;
        oldConstraint = new Rectangle(node.getX(), node.getY(), node.getWidth(), node.getHeight());
        if (constraint instanceof Rectangle) {
            this.newConstraint = (Rectangle) constraint;
        } else {
            this.changedSize = (Double) constraint;
        }
    }

    /**
     * ChangeNodeConstraintCommand
     * @param editPart
     * @param direction
     * @param constraint
     */
    public ChangeNodeConstraintCommand(EditPart editPart, int direction, Object constraint) {
        this.node = (AbstractNode) editPart.getModel();
        this.editPart = editPart;
        oldConstraint = new Rectangle(node.getX(), node.getY(), node.getWidth(), node.getHeight());
        if (constraint instanceof Rectangle) {
            this.newConstraint = (Rectangle) constraint;
        } else {
            this.changedSize = (Double) constraint;
            this.direction = direction;
        }
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {
        UiUtil.eraseFeedback(editPart);
        
        if (newConstraint == null) {
            EditPart currentEditPart = this.editPart;
            EditPart parentEditPart = this.editPart.getParent();

            List<EditPart> children = parentEditPart.getChildren();
            int currentEditPartIndex = 0;
            for (int index = 0; index < children.size(); index++) {
                if (children.get(index).equals(currentEditPart)) {
                    currentEditPartIndex = index;
                    break;
                }
            }

            switch (direction) {
                case PositionConstants.NORTH:
                    if (node.getNodeType().equals(NodeType.NAME) || node.getNodeType().equals(NodeType.STEREOTYPE)) {
                        break;
                    }
                    if (node.equals(children.get(0).getModel())) {
                        break;
                    }
                    if (node.getNodeType().equals(NodeType.ATTRIBUTES)) {
                        break;
                    }
                    if (node.getNodeType().equals(NodeType.PROVIDED_INTERFACES)) {
                        if (node instanceof NotationNode) {
                            if (hasNodeType((NotationNode) node.getParent(), NodeType.ATTRIBUTES)) {
                                break;
                            }
                        }
                    }

                    if (changedSize < 0) {
                        AbstractNode cNode = (AbstractNode) children.get(currentEditPartIndex - 1).getModel();

                        if (node.getHeight() + changedSize < 25) {
                            cNode.setHeight(cNode.getHeight() + node.getHeight() - 25);
                            node.setHeight(25);
                        } else {
                            cNode.setHeight((int) (cNode.getHeight() + Math.abs(changedSize)));
                            node.setHeight((int) (node.getHeight() + changedSize));
                        }
                    } else {
                        AbstractNode cNode = (AbstractNode) children.get(currentEditPartIndex - 1).getModel();

                        if (cNode.getHeight() - Math.abs(changedSize) < 25) {
                            node.setHeight((int) (node.getHeight() + cNode.getHeight() - 25));
                            cNode.setHeight(25);
                        } else {
                            cNode.setHeight((int) (cNode.getHeight() - Math.abs(changedSize)));
                            node.setHeight((int) (node.getHeight() + changedSize));
                        }
                    }
                    break;
                case PositionConstants.SOUTH:
                    if (node.getNodeType().equals(NodeType.NAME) || node.getNodeType().equals(NodeType.STEREOTYPE)) {
                        break;
                    }
                    if (node.equals(children.get(0).getModel())) {
                        break;
                    }
                    if (changedSize < 0) {
                        if (currentEditPartIndex == children.size() - 1) {
                            AbstractNode parent = (AbstractNode) node.getParent();

                            if (node.getHeight() + changedSize < 25) {
                                parent.setHeight((int) (parent.getHeight() - (node.getHeight() - 25)));
                                node.setHeight(25);
                            } else {
                                parent.setHeight((int) (parent.getHeight() + changedSize));
                                node.setHeight((int) (node.getHeight() + changedSize));
                            }
                        } else {
                            AbstractNode cNode = (AbstractNode) children.get(currentEditPartIndex + 1).getModel();
                            if (node.getHeight() + changedSize < 25) {
                                cNode.setHeight(cNode.getHeight() + node.getHeight() - 25);
                                node.setHeight(25);
                            } else {
                                cNode.setHeight((int) (cNode.getHeight() + Math.abs(changedSize)));
                                node.setHeight((int) (node.getHeight() + changedSize));
                            }
                        }
                    } else {

                        if (currentEditPartIndex == children.size() - 1) {
                            AbstractNode parent = (AbstractNode) node.getParent();
                            parent.setHeight((int) (parent.getHeight() + changedSize));
                            node.setHeight((int) (node.getHeight() + changedSize));
                        } else {
                            AbstractNode cNode = (AbstractNode) children.get(currentEditPartIndex + 1).getModel();

                            if (cNode.getHeight() - Math.abs(changedSize) < 25) {
                                node.setHeight(node.getHeight() + cNode.getHeight() - 25);
                                cNode.setHeight(25);
                            } else {
                                cNode.setHeight((int) (cNode.getHeight() - Math.abs(changedSize)));
                                node.setHeight((int) (node.getHeight() + changedSize));
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
            ((AbstractNode) node.getParent()).setHeight(((AbstractNode) node.getParent()).getHeight());

        } else {
            if (!((editPart instanceof LabelNodeEditPart) && !commandIsFalse)) {

                if (node instanceof LabelNode) {
                    Point currentLabelLocation = null;

                    if (node.getParent() instanceof AbstractNode) {
                        NotationNode notationNode = (NotationNode) node.getParent();
                        Point notationNodeLocation = new Point(notationNode.getX(), notationNode.getY());
                        node.setX(newConstraint.x - notationNodeLocation.x);
                        node.setY(newConstraint.y - notationNodeLocation.y);
                    } else {
                        if (((LabelNode) node).getType().equals(LabelType.COMPARTMENT)) {
                            Point centerPoint = getCenterPoint((LabelNode) node);
                            node.setX(newConstraint.x - centerPoint.x);
                            node.setY(newConstraint.y - centerPoint.y);

                        } else {
                            Point figureLocation = ((LabelNodeEditPart) editPart).getCurrentLocation();
                            Point standardPoint = new Point(figureLocation.x - oldConstraint.x, figureLocation.y
                                - oldConstraint.y);
                            currentLabelLocation = new Point(newConstraint.x - standardPoint.x, newConstraint.y
                                - standardPoint.y);

                            node.setX(currentLabelLocation.x);
                            node.setY(currentLabelLocation.y);
                        }
                    }

                } else {
                    node.setX(newConstraint.x);
                    node.setY(newConstraint.y);
                }

                if (newConstraint.height != oldConstraint.height) {
                    if (node.getUmlModel() != null) {
                        if (node.getUmlModel() instanceof ActivityNode) {
                            return;
                        }
                    }
                    if (!node.getNodeType().equals(NodeType.NOTE) && !node.getNodeType().equals(NodeType.TEXT)) {
                        AbstractNode childNode = (AbstractNode) ((EditPart) editPart.getChildren()
                            .get(editPart.getChildren().size() - 1)).getModel();
                        if (childNode.getHeight() + newConstraint.height - oldConstraint.height < 20) {
                            return;
                        }
                        childNode.setHeight(childNode.getHeight() + newConstraint.height - oldConstraint.height);
                    }
                }
                if ( !(node instanceof LabelNode) ) {
                    node.setWidth(newConstraint.width);
                    node.setHeight(newConstraint.height);
                }
            }
        }
    }

    /**
     * 
     * 노드의 뷰모델이 존재하는지 확인
     * 
     * @param labelType
     * @return boolean
     */
    private boolean hasNodeType(NotationNode node, NodeType nodeType) {
        for (AbstractNode child : node.getCompartmentList()) {
            if (child.getNodeType().equals(nodeType)) {
                return false;
            }
        }
        return true;
    }

    /**
     * getCenterPoint
     *  
     * @param nodeLabel
     * @return Point
     */
    private Point getCenterPoint(LabelNode nodeLabel) {
        AbstractConnection abstractConnection = (AbstractConnection) nodeLabel.getOwner();

        AbstractNode sourceNode = (AbstractNode) abstractConnection.getSource();
        AbstractNode targetNode = (AbstractNode) abstractConnection.getTarget();

        RectangleFigure sourceFigure = new RectangleFigure();
        sourceFigure.setSize(sourceNode.getWidth(), sourceNode.getHeight());
        sourceFigure.setLocation(new Point(sourceNode.getX(), sourceNode.getY()));
        RectangleFigure targetFigure = new RectangleFigure();
        targetFigure.setSize(targetNode.getWidth(), targetNode.getHeight());
        targetFigure.setLocation(new Point(targetNode.getX(), targetNode.getY()));

        ChopboxAnchor sourceAnchor = new ChopboxAnchor(sourceFigure);
        ChopboxAnchor targetAnchor = new ChopboxAnchor(targetFigure);

        Point source = new Point(sourceAnchor.getReferencePoint().x, sourceAnchor.getReferencePoint().y);
        Point target = new Point(targetAnchor.getReferencePoint().x, targetAnchor.getReferencePoint().y);

        Point centerPoint = new Point((source.x + target.x) / 2, (source.y + target.y) / 2);
        return centerPoint;
    }

    /**
     * commandIsFalse
     */
    private boolean commandIsFalse = true;

    /**
     * setCommandIsFalse
     *  
     * @param commandIsFalse void
     */
    public void setCommandIsFalse(boolean commandIsFalse) {
        this.commandIsFalse = commandIsFalse;
    }

    /**
     * setConstraint
     *  
     * @param rectangle void
     */
    public void setConstraint(Rectangle rectangle) {
        newConstraint = rectangle;
    }
}
