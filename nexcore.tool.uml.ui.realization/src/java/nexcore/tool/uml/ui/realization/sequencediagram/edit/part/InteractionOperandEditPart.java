/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.realization.sequencediagram.edit.part;

import java.util.List;

import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.ui.core.diagram.edit.part.AbstractContainerNodeEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.DelegatingDragEditPartsTracker;
import nexcore.tool.uml.ui.core.diagram.edit.policy.DirectEditorPolicy;
import nexcore.tool.uml.ui.realization.sequencediagram.edit.policy.OperandResizableEditPolicy;
import nexcore.tool.uml.ui.realization.sequencediagram.edit.policy.SequenceDiagramLayoutEditPolicy;
import nexcore.tool.uml.ui.realization.sequencediagram.figure.InteractionOperandFigure;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.edit.part</li>
 * <li>설  명 : InteractionOperandEditPart</li>
 * <li>작성일 : 2011. 4. 18.</li>
 * <li>작성자 : zerotae</li>
 * </ul>
 */
public class InteractionOperandEditPart extends AbstractContainerNodeEditPart {

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractContainerNodeEditPart#createEditPolicies()
     */
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, new OperandResizableEditPolicy());
    }
    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractContainerNodeEditPart#createGraphicalNodeEditPolicy()
     */
    @Override
    protected GraphicalNodeEditPolicy createGraphicalNodeEditPolicy() {
        //operand는 연결선이 필요없다.
        return null;
    }

    
    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractContainerNodeEditPart#createDirectEditorPolicy()
     */
    @Override
    protected DirectEditorPolicy createDirectEditorPolicy() {
        //opernad는 다이렉트에딧팅일 필요없다.
        return null;
    }
        
    
    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractContainerNodeEditPart#createLayoutEditPolicy()
     */
    @Override
    protected LayoutEditPolicy createLayoutEditPolicy() {
        return new SequenceDiagramLayoutEditPolicy();
    }
    
    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.ScrollableEditPart#createFigure()
     */
    @Override
    protected IFigure createFigure() {
        ContainerNode node = (ContainerNode) getModel();
        InteractionOperandFigure figure = new InteractionOperandFigure();
        figure.setSize(node.getWidth(), node.getHeight());
        
        return figure;
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
     */
    @Override
    protected void refreshVisuals() {
        ContainerNode node = (ContainerNode) this.getModel();
        InteractionOperandFigure figure = new InteractionOperandFigure();
        
        Rectangle bounds = new Rectangle(node.getX(),
            node.getY(),
            node.getWidth(),
            node.getHeight());
        figure.setSize(node.getWidth(), node.getHeight());
        figure.setLocation(new Point(node.getX(), node.getY()));
        setLayoutConstraint(this, getFigure(), bounds);
        figure.refreshOperandUnderBoundary();
        
    }
    
    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshChildren()
     */
    @Override
    protected void refreshChildren() {
        // TODO Auto-generated method stub
        super.refreshChildren();
        List list = getChildren();
        for(Object object : list) {
            if(object instanceof GraphicalEditPart) {
                ((GraphicalEditPart) object).refresh();
            }
        }
    }
    
    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getDragTracker(org.eclipse.gef.Request)
     */
    @Override
    public DragTracker getDragTracker(Request request) {
        // return super.getDragTracker(request);
        return new DelegatingDragEditPartsTracker(this, getParent());
    }
}
