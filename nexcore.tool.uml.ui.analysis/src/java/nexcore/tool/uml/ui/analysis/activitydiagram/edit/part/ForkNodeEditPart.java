/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.analysis.activitydiagram.edit.part;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.analysis.activitydiagram.edit.policy.JorkAndJoinNodeChangePolicy;
import nexcore.tool.uml.ui.analysis.activitydiagram.figure.ForkNodeFigure;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.analysis</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.analysis.activitydiagram.edit.part</li>
 * <li>설 명 : ForkNodeEditPart</li>
 * <li>작성일 : 2009. 11. 30.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class ForkNodeEditPart extends AbstractControlNodeEditPart {

    /**
     * @see nexcore.tool.uml.ui.analysis.activitydiagram.edit.part.AbstractControlNodeEditPart#createEditPolicies()
     */
    @Override
    protected void createEditPolicies() {
        installEditPolicy(JorkAndJoinNodeChangePolicy.CHANGE_SHAPE_ROLE, new JorkAndJoinNodeChangePolicy());
        super.createEditPolicies();
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    @Override
    protected IFigure createFigure() {
        NotationNode notationNode = (NotationNode) getModel();
        ForkNodeFigure figure = new ForkNodeFigure();
        figure.setSize(notationNode.getWidth(), notationNode.getHeight());
        figure.setLocation(new Point(notationNode.getX(), notationNode.getY()));
        return figure;
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
     */
    @Override
    protected void refreshVisuals() {
        try {
            super.refreshVisuals();
            
            NotationNode notationNode = (NotationNode) getModel();
            Rectangle bounds = new Rectangle(notationNode.getX(),
                notationNode.getY(),
                notationNode.getWidth(),
                notationNode.getHeight());
            getFigure().setSize(notationNode.getWidth(), notationNode.getHeight());
            getFigure().setLocation(new Point(notationNode.getX(), notationNode.getY()));
            setLayoutConstraint(this, getFigure(), bounds);

            getFigure().setBackgroundColor(new Color(null, getFillColor()));

            nodeMoved();

        } catch (Exception e) {
            Log.error(UMLMessage.MESSAGE_CENTRAL_BUFFER_NODE_EDIT_PART_REFRESH_VISUALS + e);
        }
    }

}
