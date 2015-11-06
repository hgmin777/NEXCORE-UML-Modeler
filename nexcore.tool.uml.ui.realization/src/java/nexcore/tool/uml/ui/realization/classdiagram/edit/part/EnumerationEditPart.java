/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.realization.classdiagram.edit.part;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.core.diagram.edit.part.AbstractNotationNodeEditPart;
import nexcore.tool.uml.ui.core.diagram.figure.EnumerationFigure;
import nexcore.tool.uml.ui.core.diagram.figure.InterfaceFigure;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.Color;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.classdiagram.edit.editpart</li>
 * <li>설 명 : EnumerationEditPart</li>
 * <li>작성일 : 2009. 11. 16.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class EnumerationEditPart extends AbstractNotationNodeEditPart {

    /**
     * EnumerationFigure
     */
    private EnumerationFigure EnumerationFigure;
    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    protected IFigure createFigure() {
        NotationNode notationNode = (NotationNode) getModel();

        EObject umlModel = notationNode.getUmlModel();
        if(isValid(umlModel)){
            EnumerationFigure = new EnumerationFigure(false);
        } else {
            EnumerationFigure = new EnumerationFigure();
        }
        

        EnumerationFigure.setName(notationNode.getName());
        EnumerationFigure.setSize(notationNode.getWidth(), notationNode.getHeight());
        EnumerationFigure.setLocation(new Point(notationNode.getX(), notationNode.getY()));

        GridLayout gridLayout = new GridLayout();
        gridLayout.verticalSpacing = 0;
        gridLayout.numColumns = 1;
        EnumerationFigure.setLayoutManager(gridLayout);

        return EnumerationFigure;
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
     */
    @Override
    protected void refreshVisuals() {
        try {
            super.refreshVisuals();
            
            int totalHeight = 0;
            for (int i = 0; i < getFigure().getChildren().size(); i++) {
                totalHeight += ((Figure) getFigure().getChildren().get(i)).getSize().height;
            }

            NotationNode notationNode = (NotationNode) getModel();
            Rectangle bounds = new Rectangle(notationNode.getX(),
                notationNode.getY(),
                notationNode.getWidth(),
                notationNode.getHeight());
            ((EnumerationFigure) getFigure()).setName(notationNode.getName());

            getFigure().setSize(notationNode.getWidth(), totalHeight);// notationNode.getHeight());
            getFigure().setLocation(new Point(notationNode.getX(), notationNode.getY()));
            setLayoutConstraint(this, getFigure(), bounds);

            getFigure().setBackgroundColor(new Color(null, getFillColor()));

        } catch (Exception e) {
            Log.error(UMLMessage.MESSAGE_ENUMERATION_EDIT_PART_REFRESH_VISUALS_ERROR + e);
        }
    }

}
