/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.realization.sequencediagram.edit.part;

import java.util.List;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.manager.util.DiagramUtil;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.core.diagram.edit.part.AbstractChildCompartmentEditPart;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.Request;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.uml2.uml.InteractionConstraint;
import org.eclipse.uml2.uml.OpaqueExpression;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.edit.part</li>
 * <li>설  명 : GuardEditPart</li>
 * <li>작성일 : 2011. 4. 19.</li>
 * <li>작성자 : zerotae</li>
 * </ul>
 */
public class GuardEditPart extends AbstractChildCompartmentEditPart {

    /**
     * GUARD_MARGIN
     */
    private static final int GUARD_MARGIN = 10;
            
    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    protected IFigure createFigure() {
        NotationNode notationNode = (NotationNode) getModel();
        InteractionConstraint guard = (InteractionConstraint) notationNode.getUmlModel();
        Label figure = new Label();        
        figure.setText(((OpaqueExpression) guard.getSpecification()).getBodies().get(0));
        int with = DiagramUtil.getWidthSize(figure.getText(), figure.getFont()) + GUARD_MARGIN;
        figure.setSize(with, notationNode.getHeight());
        return figure;
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
     */
    @Override
    protected void refreshVisuals() {
        try {
            NotationNode notationNode = (NotationNode) getModel();
            InteractionConstraint guard = (InteractionConstraint) notationNode.getUmlModel();
            Label figure = (Label) getFigure();
            figure.setText(((OpaqueExpression) guard.getSpecification()).getBodies().get(0));
            figure.setToolTip(new Label(((OpaqueExpression) guard.getSpecification()).getBodies().get(0)));
            figure.setLocation(new Point(notationNode.getX(), notationNode.getY()));
            int with = DiagramUtil.getWidthSize(figure.getText(), figure.getFont()) + GUARD_MARGIN;
            figure.setSize(with, notationNode.getHeight());            
            Rectangle bounds = new Rectangle(notationNode.getX(),
                notationNode.getY(),
                with,
                notationNode.getHeight());
            setLayoutConstraint(this, figure, bounds);

        } catch (Exception e) {
            Log.error("GuardEditPart refreshVisuals() Error " + e);
        }
    }
    
    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#activate()
     */
    @Override
    public void activate() {
        if (!isActive()) {
            super.activate();
            InteractionConstraint guard = (InteractionConstraint) ((AbstractView) getModel()).getUmlModel();
            if(guard != null && guard.getSpecification() != null) {
                guard.getSpecification().eAdapters().add(this);
            }
        }
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#deactivate()
     */
    @Override
    public void deactivate() {
        if (isActive()) {
            super.deactivate();
            InteractionConstraint guard = (InteractionConstraint) ((AbstractView) getModel()).getUmlModel();
            if(guard != null && guard.getSpecification() != null) {
                guard.getSpecification().eAdapters().remove(this);
            }
        }
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#eraseSourceFeedback(org.eclipse.gef.Request)
     */
    public void eraseSourceFeedback(Request request) {
        if (isActive()) {
            EditPolicyIterator iter = getEditPolicyIterator();
            while (iter.hasNext()) {
                EditPolicy nextEditPolicy = iter.next();
                if(nextEditPolicy instanceof ResizableEditPolicy) {
                    List<Object> feedbackList = getLayer(LayerConstants.FEEDBACK_LAYER).getChildren();
                    if(null == feedbackList || feedbackList.isEmpty()){
                        continue;
                    }
                }
                nextEditPolicy.eraseSourceFeedback(request);
            }
        }
    }
    

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#deactivateEditPolicies()
     */
    protected void deactivateEditPolicies() {
        EditPolicyIterator iter = getEditPolicyIterator();
        while (iter.hasNext()) {
            EditPolicy nextEditPolicy = iter.next();
            if(nextEditPolicy instanceof ResizableEditPolicy) {
                List<Object> feedbackList = getLayer(LayerConstants.FEEDBACK_LAYER).getChildren();
                if(null == feedbackList || feedbackList.isEmpty()){
                    continue;
                }
            }
            nextEditPolicy.deactivate();
        }
    }
}
