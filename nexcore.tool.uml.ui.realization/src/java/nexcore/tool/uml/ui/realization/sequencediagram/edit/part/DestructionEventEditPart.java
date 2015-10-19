/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.realization.sequencediagram.edit.part;

import java.util.Collections;
import java.util.List;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.core.diagram.edit.part.AbstractNotationNodeEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.policy.DirectEditorPolicy;
import nexcore.tool.uml.ui.core.diagram.figure.FigureConstant;
import nexcore.tool.uml.ui.realization.sequencediagram.figure.DestructionEventFigure;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.edit.part</li>
 * <li>설 명 : DestructionEventEditPart</li>
 * <li>작성일 : 2010. 1. 19.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class DestructionEventEditPart extends AbstractNotationNodeEditPart {

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    @Override
    protected IFigure createFigure() {

        NotationNode notationNode = (NotationNode) getModel();

        DestructionEventFigure figure = new DestructionEventFigure();
        figure.setSize(notationNode.getWidth(), notationNode.getHeight());

        return figure;
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractNotationNodeEditPart#createEditPolicies()
     */
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, new NonResizableEditPolicy());
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractNotationNodeEditPart#createGraphicalNodeEditPolicy()
     */
    @Override
    protected GraphicalNodeEditPolicy createGraphicalNodeEditPolicy() {
        return null;
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractNotationNodeEditPart#createDirectEditorPolicy()
     */
    @Override
    protected DirectEditorPolicy createDirectEditorPolicy() {
        return null;
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractNotationNodeEditPart#getModelChildren()
     */
    @SuppressWarnings("unchecked")
    @Override
    protected List getModelChildren() {
        return Collections.EMPTY_LIST;
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
     */
    @Override
    protected void refreshVisuals() {
        try {
            super.refreshVisuals();
            
            NotationNode lifeLineNode = (NotationNode) getParent().getModel();
            getFigure().setLocation(new Point(lifeLineNode.getX() + lifeLineNode.getWidth() / 2
                - FigureConstant.FIGURE_DESTRUCTION_EVENT_SIZE / 2, lifeLineNode.getY() + lifeLineNode.getHeight()
                - FigureConstant.FIGURE_DESTRUCTION_EVENT_SIZE));
        } catch (Exception e) {
            Log.error(UMLMessage.MESSAGE_DESTRUCTION_EVENT_EDIT_PART_REFRESH_VISUALS_ERROR + e);
        }

    }

}
