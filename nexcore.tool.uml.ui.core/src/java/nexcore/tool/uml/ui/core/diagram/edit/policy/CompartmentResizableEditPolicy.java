/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.diagram.edit.policy;

import java.util.List;

import nexcore.tool.uml.manager.DiagramEditDomain;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.ui.core.diagram.command.ChangeNodeConstraintCommand;
import nexcore.tool.uml.ui.core.diagram.edit.part.AbstractChildCompartmentEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.AbstractDiagramEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.AttributeEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.AttributesEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.LabelNodeEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.NotationNameEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.OperationEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.OperationsEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.ScrollableEditPart;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.UseCase;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.edit.policy</li>
 * <li>설  명 : CompartmentResizableEditPolicy</li>
 * <li>작성일 :  2009. 11. 16.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class CompartmentResizableEditPolicy extends ResizableEditPolicy {

    /** DiagramEditDomain */
    protected DiagramEditDomain diagramEditDomain;

    /**
     * @see org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy#createChangeConstraintCommand(org.eclipse.gef.EditPart,
     *      java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    protected Command createChangeConstraintCommand(EditPart child, int position, Object constraint) {
        ChangeNodeConstraintCommand command = new ChangeNodeConstraintCommand(child, position, constraint);

        List<EditPartViewer> editPartList = child.getViewer().getSelectedEditParts();
        EditPart editPart;
        boolean isLabel = false;
        boolean isNotLabel = false;
        for (int i = 0; i < editPartList.size(); i++) {
            editPart = (EditPart) editPartList.get(i);
            if (editPart instanceof LabelNodeEditPart) {
                isLabel = true;
            } else if (!(editPart instanceof LabelNodeEditPart)) {
                isNotLabel = true;
            }

            if (isLabel && isNotLabel) {
                command.setCommandIsFalse(false);
            }
        }

        // 2010.02.11 강경구
        // Actor의 최소 사이즈를 주기 위해.. 다른 피겨나 제한 방법을 다시 고려해 봐야 한다.
        if (((AbstractNode) child.getModel()).getUmlModel() instanceof Actor) {
            if (((Rectangle) constraint).width < 70 && ((Rectangle) constraint).height < 90) {
                command.setConstraint(new Rectangle(((Rectangle) constraint).x, ((Rectangle) constraint).y, 60, 80));
            } else if (((Rectangle) constraint).width < 60) {
                command.setConstraint(new Rectangle(((Rectangle) constraint).x,
                    ((Rectangle) constraint).y,
                    60,
                    ((Rectangle) constraint).height));
            } else if (((Rectangle) constraint).height < 80) {
                command.setConstraint(new Rectangle(((Rectangle) constraint).x,
                    ((Rectangle) constraint).y,
                    ((Rectangle) constraint).height,
                    80));
            }
        }

        if (((AbstractNode) child.getModel()).getUmlModel() instanceof UseCase) {
            if (((Rectangle) constraint).width < 100 && ((Rectangle) constraint).height < 60) {
                command.setConstraint(new Rectangle(((Rectangle) constraint).x, ((Rectangle) constraint).y, 100, 60));
            } else if (((Rectangle) constraint).width < 100) {
                command.setConstraint(new Rectangle(((Rectangle) constraint).x,
                    ((Rectangle) constraint).y,
                    100,
                    ((Rectangle) constraint).height));
            } else if (((Rectangle) constraint).height < 60) {
                command.setConstraint(new Rectangle(((Rectangle) constraint).x,
                    ((Rectangle) constraint).y,
                    ((Rectangle) constraint).height,
                    60));
            }
        }
        return command;
    }

    /**
     * isDragging
     */
    private static boolean isDragging = false;

    /**
     * @see org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy#getCommand(org.eclipse.gef.Request)
     */
    public Command getCommand(Request request) {
        try {
            if (!(getHost() instanceof AbstractDiagramEditPart)) {
                if (REQ_RESIZE.equals(request.getType())) {
                    if (request instanceof ChangeBoundsRequest) {
                        CompoundCommand resize = new CompoundCommand();
                        List<EditPart> children = ((ChangeBoundsRequest) request).getEditParts();
                        for (EditPart child : children) {
                            resize.add(createChangeConstraintCommand(child,
                                ((ChangeBoundsRequest) request).getResizeDirection(),
                                ((ChangeBoundsRequest) request).getSizeDelta().preciseHeight()));
                        }
                        return resize;
                    }
                } else {
                    return new EmptyCommand();
                }
            }
            return super.getCommand(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public class EmptyCommand extends Command {

    }

    // protected boolean isInState(int state) {
    // return ((getState() & state) != 0);
    // }

    /**
     * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#eraseTargetFeedback(org.eclipse.gef.Request)
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eraseTargetFeedback(Request request) {
        super.eraseTargetFeedback(request);

        if (getHost() instanceof OperationsEditPart || getHost() instanceof AttributesEditPart
            || getHost() instanceof NotationNameEditPart) {}
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

    /**
     * @see org.eclipse.gef.editpolicies.SelectionEditPolicy#setSelectedState(int)
     */
    @Override
    protected void setSelectedState(int type) {
        if (getHost().getParent().getSelected() == EditPart.SELECTED
            || getHost().getParent().getSelected() == EditPart.SELECTED_PRIMARY) {
            super.setSelectedState(EditPart.SELECTED_NONE);

            List list = getHost().getParent().getChildren();
            for (int i = 0; i < list.size(); i++) {
                Object obj = list.get(i);
                if (obj instanceof OperationsEditPart || obj instanceof AttributesEditPart
                    || obj instanceof NotationNameEditPart) {

                    CompartmentResizableEditPolicy policy = (CompartmentResizableEditPolicy) ((ScrollableEditPart) obj).getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
                    policy.getHost().setSelected(EditPart.SELECTED_NONE);
                }
            }

            list = getHost().getChildren();
            for (int i = 0; i < list.size(); i++) {
                Object obj = list.get(i);

                if (obj instanceof OperationEditPart || obj instanceof AttributeEditPart) {
                    NonResizableEditPolicy policy = (NonResizableEditPolicy) ((AbstractChildCompartmentEditPart) obj).getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
                    policy.getHost().setSelected(EditPart.SELECTED_NONE);
                }
            }
        }

        if (getHost().getParent().getSelected() == EditPart.SELECTED
            || getHost().getParent().getSelected() == EditPart.SELECTED_PRIMARY
            && (getHost() instanceof OperationEditPart || getHost() instanceof AttributeEditPart
                || getHost() instanceof NotationNameEditPart || getHost() instanceof OperationsEditPart
                || getHost() instanceof AttributesEditPart || getHost() instanceof NotationNameEditPart)) {

        } else {
            super.setSelectedState(type);
        }
    }
}
