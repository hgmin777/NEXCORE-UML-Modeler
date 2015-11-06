/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.realization.sequencediagram.edit.part;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.diagram.edit.part.AbstractNotationNodeEditPart;
import nexcore.tool.uml.ui.core.registry.IConstantColorRegistry;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.edit.part</li>
 * <li>설 명 : InteractionUseEditPart</li>
 * <li>작성일 : 2010. 1. 19.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class InteractionUseEditPart extends AbstractNotationNodeEditPart {

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    @Override
    protected IFigure createFigure() {
        NotationNode notationNode = (NotationNode) getModel();
        Polyline line = new Polyline();
        line.setOpaque(false);
        line.addPoint(new Point(notationNode.getX(), notationNode.getY()));
        line.addPoint(new Point(notationNode.getX(), notationNode.getY() + 100));
        line.setLineWidth(2);
        line.setForegroundColor(UiCorePlugin.getDefault().getColor(IConstantColorRegistry.DimGray));
        line.setLineStyle(Graphics.LINE_DASH);
        return line;
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
     */
    @Override
    protected void refreshVisuals() {
        try {
            super.refreshVisuals();
            
            NotationNode notationNode = (NotationNode) getModel();
            Polyline line = (Polyline) this.getFigure();
            line.removeAllPoints();
            line.addPoint(new Point(notationNode.getX(), notationNode.getY()));
            line.addPoint(new Point(notationNode.getX(), notationNode.getY() + notationNode.getHeight()));

            Rectangle bounds = new Rectangle(notationNode.getX(),
                notationNode.getY(),
                notationNode.getWidth(),
                notationNode.getHeight());
            ((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), bounds);
        } catch (Exception e) {
            Log.error(UMLMessage.MESSAGE_INTERACTION_USE_EDIT_PART_REFRESH_VISUALS_ERROR + e);
        }
    }

}
