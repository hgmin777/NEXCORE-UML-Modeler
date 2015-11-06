/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.core.diagram.edit.part;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.core.diagram.figure.ComponentFigure;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Color;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.edit.part</li>
 * <li>설  명 : ComponentEditPart</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class ComponentEditPart extends AbstractNotationNodeEditPart {

    /**
     * componentFigure
     */
    private ComponentFigure componentFigure;
    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    @Override
    protected IFigure createFigure() {
        NotationNode notationNode = (NotationNode) getModel();

        EObject umlModel = notationNode.getUmlModel();
        if(isValid(umlModel)){
            componentFigure = new ComponentFigure(false);
        } else {
            componentFigure = new ComponentFigure();
        }
        
        componentFigure.setSize(notationNode.getWidth(), notationNode.getHeight());
        componentFigure.setLocation(new Point(notationNode.getX(), notationNode.getY()));

        GridLayout gridLayout = new GridLayout();
        gridLayout.verticalSpacing = 0;
        gridLayout.numColumns = 1;
        componentFigure.setLayoutManager(gridLayout);

        return componentFigure;
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

            getFigure().setSize(notationNode.getWidth(), totalHeight);// notationNode.getHeight());
            getFigure().setLocation(new Point(notationNode.getX(), notationNode.getY()));
            setLayoutConstraint(this, getFigure(), bounds);

            getFigure().setBackgroundColor(new Color(null, getFillColor()));
        } catch (Exception e) {
            Log.error(UMLMessage.MESSAGE_DATA_STORE_NODE_EDIT_PART_REFRESH_VISUALS_ERROR + e);
        }
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractNotationNodeEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, createComponentEditPolicy());
        installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, createGraphicalNodeEditPolicy());
        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, createDirectEditorPolicy());
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractNotationNodeEditPart#activate()
     */
    @Override
    public void activate() {
        // TODO Auto-generated method stub
        super.activate();
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractNotationNodeEditPart#deactivate()
     */
    @Override
    public void deactivate() {
        // TODO Auto-generated method stub
        super.deactivate();
    }
}
