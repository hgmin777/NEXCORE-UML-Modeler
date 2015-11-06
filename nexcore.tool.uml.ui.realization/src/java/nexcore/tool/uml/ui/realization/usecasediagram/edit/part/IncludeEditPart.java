/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.realization.usecasediagram.edit.part;

import java.util.ArrayList;
import java.util.List;

import nexcore.tool.uml.model.umldiagram.LabelNode;
import nexcore.tool.uml.ui.core.diagram.edit.part.AbstractDiagramConnectionEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.LabelNodeEditPart;
import nexcore.tool.uml.ui.core.diagram.figure.ArrowDecoration;

import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.FigureListener;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.RoutingAnimator;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.RootEditPart;
import org.eclipse.swt.SWT;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.usecasediagram.edit.part</li>
 * <li>설  명 : IncludeEditPart</li>
 * <li>작성일 : 2009. 11. 27.</li>
 * <li>작성자 : Duyu</li>
 * </ul>
 */
public class IncludeEditPart extends AbstractDiagramConnectionEditPart {

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractDiagramConnectionEditPart#createFigure()
     */
    @Override
    protected IFigure createFigure() {
        PolylineConnection connection = new PolylineConnection() {
            @Override
            public void paintFigure(Graphics graphics) {
                graphics.setAntialias(SWT.ON);
                super.paintFigure(graphics);
            }
        };
        connection.addFigureListener(new FigureListener() {

            /**
             * @see org.eclipse.draw2d.FigureListener#figureMoved(org.eclipse.draw2d.IFigure)
             */
            @SuppressWarnings("unchecked")
            public void figureMoved(IFigure source) {
                Point sourcePoint = ((PolylineConnection) source).getStart();
                Point targetPoint = ((PolylineConnection) source).getEnd();

                RootEditPart rootEditPart = (RootEditPart) getParent();
                List<EditPart> diagramEditparts = new ArrayList<EditPart>();
                diagramEditparts = rootEditPart.getChildren();
                List<EditPart> editParts = new ArrayList<EditPart>();

                for (EditPart diagramEditPart : diagramEditparts) {
                    editParts = diagramEditPart.getChildren();
                    for (EditPart editpart : editParts) {
                        if (editpart.getModel() instanceof LabelNode) {
                            if (((LabelNode) editpart.getModel()).getOwner() == getModel()) {
                                if (editpart instanceof LabelNodeEditPart) {
                                    ((LabelNodeEditPart) editpart).setConnectionAnchorPoints(sourcePoint, targetPoint);
                                }
                            }
                        }
                    }
                }
            }
        });

        connection.setTargetDecoration(new ArrowDecoration());
        connection.setLineStyle(Graphics.LINE_DOT);
        connection.addRoutingListener(RoutingAnimator.getDefault());
        connection.setConnectionRouter(new BendpointConnectionRouter());
        return connection;
    }

}
