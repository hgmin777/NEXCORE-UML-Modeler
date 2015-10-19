/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.diagram.edit.part;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.core.diagram.figure.PackageFigure;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.Color;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.edit.part</li>
 * <li>설  명 : PackageEditPart</li>
 * <li>작성일 : 2009. 11. 12.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class PackageEditPart extends AbstractNotationNodeEditPart {

    /**
     * packageFigure
     */
    private PackageFigure packageFigure;
    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    @Override
    protected IFigure createFigure() {
        NotationNode notationNode = (NotationNode) getModel();

        EObject umlModel = notationNode.getUmlModel();
        if(isValid(umlModel)){
            packageFigure = new PackageFigure(false);
        } else {
            packageFigure = new PackageFigure();
        }
        
        packageFigure.setSize(notationNode.getWidth(), notationNode.getHeight());
        packageFigure.setLocation(new Point(notationNode.getX(), notationNode.getY()));

        return packageFigure;
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

            setLayoutConstraint(this, getFigure(), bounds);

            ((PackageFigure) getFigure()).setColor(new Color(null, getFillColor()));

        } catch (Exception e) {
            Log.error("PackageEditPart refreshVisuals() Error " + e);
        }

    }
}
