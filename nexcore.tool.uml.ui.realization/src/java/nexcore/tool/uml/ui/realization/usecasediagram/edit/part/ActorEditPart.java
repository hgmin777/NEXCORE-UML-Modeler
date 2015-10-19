/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.realization.usecasediagram.edit.part;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.core.diagram.edit.part.AbstractNotationNodeEditPart;
import nexcore.tool.uml.ui.core.diagram.figure.ActorFigure;
import nexcore.tool.uml.ui.core.diagram.figure.DeleteSignFigure;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.swt.graphics.Color;
import org.eclipse.uml2.uml.NamedElement;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.usecasediagram.edit.part</li>
 * <li>설  명 : ActorEditPart</li>
 * <li>작성일 : 2009. 11. 12.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class ActorEditPart extends AbstractNotationNodeEditPart {

    /** figure */
    private ActorFigure figure;

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    @Override
    protected IFigure createFigure() {

        NotationNode notationNode = (NotationNode) getModel();
        EObject umlModel = notationNode.getUmlModel();

        if(isValid(umlModel)){
            figure = new ActorFigure(false);
        } else {
            figure = new ActorFigure();
        }

        GridLayout gridLayout = new GridLayout();
        gridLayout.verticalSpacing = 0;
        gridLayout.numColumns = 1;
        figure.setLayoutManager(gridLayout);

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
            ((ActorFigure) getFigure()).setSize(notationNode.getWidth(), notationNode.getHeight());
            ((ActorFigure) getFigure()).setLocation(new Point(notationNode.getX(), notationNode.getY()));

            ((ActorFigure) getFigure()).setColor(new Color(null, getFillColor()));

        } catch (Exception e) {
            Log.error(UMLMessage.LABEL_ACTOR_EDIT_PART_REFRESH_VISUALS_ERROR + e);
        }
    }

}
