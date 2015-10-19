/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.diagram.edit.part;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.core.diagram.figure.ArtifactFigure;

import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.Color;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.usecasediagram.edit.part</li>
 * <li>설 명 : ArtifactEditPart</li>
 * <li>작성일 : 2009. 12. 7.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class ArtifactEditPart extends AbstractNotationNodeEditPart {

    /**
     * figure
     */
    private ArtifactFigure figure;
    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    @Override
    protected IFigure createFigure() {

        NotationNode notationNode = (NotationNode) getModel();
        EObject umlModel = notationNode.getUmlModel();
        
        if(isValid(umlModel)){
            figure = new ArtifactFigure(false);
        } else {
            figure = new ArtifactFigure();
        }
        
        figure.setLocation(new Point(notationNode.getX(), notationNode.getY()));
        figure.setSize(notationNode.getWidth(), notationNode.getHeight());

        GridLayout gridLayout = new GridLayout();
        gridLayout.verticalSpacing = 0;
        gridLayout.numColumns = 1;
        figure.setLayoutManager(gridLayout);

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

        } catch (Exception e) {
            Log.error(UMLMessage.LABEL_ARTIFACT_EDIT_PART_REFRESH_VISUALS_ERROR + e);
        }
    }
}
