/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.core.diagram.edit.policy;

import java.util.List;

import nexcore.tool.uml.manager.DiagramEditDomain;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.model.umldiagram.DiagramType;
import nexcore.tool.uml.ui.core.diagram.command.ChangeNodeConstraintCommand;
import nexcore.tool.uml.ui.core.diagram.command.CreateNotationNodeCommand;
import nexcore.tool.uml.ui.core.diagram.edit.part.LabelNodeEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.NoteEditPart;
import nexcore.tool.uml.ui.core.util.UiUtil;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.UseCase;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.edit.policy</li>
 * <li>설  명 : DiagramLayoutEditPolicy</li>
 * <li>작성일 : 2009. 11. 16.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class DiagramLayoutEditPolicy extends XYLayoutEditPolicy {

    /** DiagramEditDomain */
    protected DiagramEditDomain diagramEditDomain;

    /**
     * @see org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy#createChangeConstraintCommand(org.eclipse.gef.EditPart,
     *      java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    protected Command createChangeConstraintCommand(EditPart child, Object constraint) {
        diagramEditDomain = (DiagramEditDomain) getHost().getViewer().getEditDomain();
        if( DiagramType.SEQUENCE_DIAGRAM.equals(diagramEditDomain.getDiagram().getType()) && getHost().getModel() instanceof ContainerNode && child instanceof NoteEditPart ) {
            UiUtil.eraseFeedback(getHost());
            return null;
        }
        ChangeNodeConstraintCommand command = new ChangeNodeConstraintCommand(child, constraint);

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

        int xPoint = ((Rectangle) constraint).x;// +
        // horizontalBar.getSelection();
        int yPoint = ((Rectangle) constraint).y;// + verticalBar.getSelection();

        // 2010.02.11 강경구
        // Actor의 최소 사이즈를 주기 위해.. 다른 피겨나 제한 방법을 다시 고려해 봐야 한다.
        if (((AbstractNode) child.getModel()).getUmlModel() instanceof Actor) {
            if (((Rectangle) constraint).width < 70 && ((Rectangle) constraint).height < 90) {
                command.setConstraint(new Rectangle(xPoint, yPoint, 60, 80));
            } else if (((Rectangle) constraint).width < 60) {
                command.setConstraint(new Rectangle(xPoint, yPoint, 60, ((Rectangle) constraint).height));
            } else if (((Rectangle) constraint).height < 80) {
                command.setConstraint(new Rectangle(xPoint, yPoint, ((Rectangle) constraint).height, 80));
            }
        }

        if (((AbstractNode) child.getModel()).getUmlModel() instanceof UseCase) {
            if (((Rectangle) constraint).width < 100 && ((Rectangle) constraint).height < 60) {
                command.setConstraint(new Rectangle(xPoint, yPoint, 100, 60));
            } else if (((Rectangle) constraint).width < 100) {
                command.setConstraint(new Rectangle(xPoint, yPoint, 100, ((Rectangle) constraint).height));
            } else if (((Rectangle) constraint).height < 60) {
                command.setConstraint(new Rectangle(xPoint, yPoint, ((Rectangle) constraint).height, 60));
            }
        }
        return command;
    }

    /**
     * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#getCreateCommand(org.eclipse.gef.requests.CreateRequest)
     */
    @Override
    protected Command getCreateCommand(CreateRequest request) {
        diagramEditDomain = (DiagramEditDomain) getHost().getViewer().getEditDomain();
        Point location = new Point(request.getLocation());
        getHostFigure().translateToRelative(location);

        if( DiagramType.SEQUENCE_DIAGRAM.equals(diagramEditDomain.getDiagram().getType()) && getHost().getModel() instanceof ContainerNode ) {
            UiUtil.eraseFeedback(getHost());
            return null;
        }
        return new CreateNotationNodeCommand(diagramEditDomain, getHost(), request, location);
    }

    /**
     * @see org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy#getCommand(org.eclipse.gef.Request)
     */
    public Command getCommand(Request request) {
        return super.getCommand(request);
    }

    /**
     * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#eraseTargetFeedback(org.eclipse.gef.Request)
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eraseTargetFeedback(Request request) {
//      List<IFigure> list = this.getFeedbackLayer().getChildren();
//      int feddbackSize = list.size();
//      if (list != null) {
//          for (int i = feddbackSize; i > 0; i--) {
//
//              this.removeFeedback(list.get(i - 1));
//          }
//      }
      super.eraseTargetFeedback(request);
    }
    
    /**
     * @see org.eclipse.gef.editpolicies.GraphicalEditPolicy#removeFeedback(org.eclipse.draw2d.IFigure)
     */
    @Override
    protected void removeFeedback(IFigure figure) {
//      if( getFeedbackLayer().getChildren().isEmpty() ){
//      return;
//  } 
//  if( !getFeedbackLayer().getChildren().contains(figure) ){
//      return;
//  } else {
      super.removeFeedback(figure);
//  }
    }
    
    /**
     * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#showSourceFeedback(org.eclipse.gef.Request)
     */
    @Override
    public void showSourceFeedback(Request request) {
        super.showSourceFeedback(request);
    }
    
    /**
     * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#showTargetFeedback(org.eclipse.gef.Request)
     */
    @Override
    public void showTargetFeedback(Request request) {
        super.showTargetFeedback(request);
    }
    
    /**
     * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#showLayoutTargetFeedback(org.eclipse.gef.Request)
     */
    @Override
    protected void showLayoutTargetFeedback(Request request) {
        super.showLayoutTargetFeedback(request);
    }
    
    /**
     * @see org.eclipse.gef.editpolicies.XYLayoutEditPolicy#showSizeOnDropFeedback(org.eclipse.gef.requests.CreateRequest)
     */
    @Override
    protected void showSizeOnDropFeedback(CreateRequest request) {
        Point p = new Point(request.getLocation().getCopy());
        IFigure feedback = getSizeOnDropFeedback(request);
        feedback.translateToRelative(p);
        Dimension size = request.getSize().getCopy();
        feedback.translateToRelative(size);
        feedback.setBounds(new Rectangle(p, size).expand(getCreationFeedbackOffset(request)));
        
        this.getFeedbackLayer().add(feedback);
    }
}
