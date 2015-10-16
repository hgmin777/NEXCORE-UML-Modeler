/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.core.diagram.edit.policy;

import java.util.List;

import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.model.umldiagram.LabelNode;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.core.diagram.command.DirectEditCommand;
import nexcore.tool.uml.ui.core.diagram.edit.part.AbstractLabelCompartmentEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.NotationNameEditPart;
import nexcore.tool.uml.ui.core.diagram.figure.NoteFigure;
import nexcore.tool.uml.ui.core.diagram.figure.TextFigure;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.uml2.uml.NamedElement;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.edit.policy</li>
 * <li>설 명 : DirectEditorPolicy</li>
 * <li>작성일 : 2009. 12. 7.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class DirectEditorPolicy extends DirectEditPolicy {

    /**
     * @see org.eclipse.gef.editpolicies.DirectEditPolicy#getDirectEditCommand(org.eclipse.gef.requests.DirectEditRequest)
     */
    @SuppressWarnings("unchecked")
    protected Command getDirectEditCommand(DirectEditRequest request) {

        DirectEditCommand command = new DirectEditCommand();
        Object model = getHost().getModel();

        if (model instanceof NamedElement) {
            command.setUmlModel(model);
        } else if (model instanceof AbstractView) {

            if (model instanceof NotationNode) {
                NotationNode node = (NotationNode) model;
                if (node.getNodeType().equals(NodeType.NOTE) || node.getNodeType().equals(NodeType.TEXT)) {
                    command.setViewModel(node);
                } else if (node.getNodeType().equals(NodeType.STEREOTYPE)) {
                    command.setViewModel(node);
                    if (node.getUmlModel() != null) {
                        command.setUmlModel(node.getUmlModel());
                    } else {

                        if (node.getParent() instanceof NotationNode) {
                            NotationNode parent = (NotationNode) node.getParent();
                            if (parent.getUmlModel() != null) {
                                command.setUmlModel(parent.getUmlModel());
                            }
                        }
                    }
                } else {

                    if (node.getUmlModel() != null) {
                        command.setUmlModel(node.getUmlModel());
                    } else {
                        if (node.getParent() instanceof NotationNode) {
                            NotationNode parent = (NotationNode) node.getParent();
                            if (parent.getUmlModel() != null) {
                                command.setUmlModel(parent.getUmlModel());
                            }
                        }
                    }
                }
            } else if (model instanceof LabelNode) {
                LabelNode label = (LabelNode) model;
                command.setViewModel(label);
                command.setUmlModel(label.getUmlModel());
            } else {
                command.setUmlModel(((AbstractView) model).getUmlModel());
            }
        } else {
            command.setUmlModel(((AbstractView) model).getUmlModel());
        }

        // setFigure
        EditPart editPart = null;
        if (getHost() instanceof AbstractLabelCompartmentEditPart) {
            command.setLabelFigure(((AbstractLabelCompartmentEditPart) getHost()).getIFigure());
        } else {
            if (getHostFigure() instanceof Label) {
                command.setLabelFigure(getHostFigure());
            } else if (getHostFigure() instanceof NoteFigure) {
                command.setNoteFigure(getHostFigure());
            } else if (getHostFigure() instanceof TextFigure) {
                command.setTextFigure(getHostFigure());
            } else if (model instanceof NotationNode || model instanceof ContainerNode) {
                List<EditPart> editParts = getHost().getChildren();
                IFigure figure = null;
                for (EditPart editpart : editParts) {
                    if (editpart instanceof NotationNameEditPart) {
                        figure = ((NotationNameEditPart) editpart).getFigure();
                        editPart = editpart;
                    }
                }
                command.setLabelFigure(figure);
            }

        }

        // setEditPart
        if (model instanceof NotationNode) {
            // NameLabel을 직접 선택하지 않고 피겨에서 바로 다이렉트 에디팅을 실행.
            if( null == editPart) {
                command.setEditPart(getHost());
            } else {
                command.setEditPart(editPart);
            }
        } else {
            command.setEditPart(getHost());
        }

        // setText
        command.setText((String) request.getCellEditor().getValue());
        return command;
    }

    /**
     * @see org.eclipse.gef.editpolicies.DirectEditPolicy#showCurrentEditValue(org.eclipse.gef.requests.DirectEditRequest)
     */
    @Override
    protected void showCurrentEditValue(DirectEditRequest request) {
    }
}
