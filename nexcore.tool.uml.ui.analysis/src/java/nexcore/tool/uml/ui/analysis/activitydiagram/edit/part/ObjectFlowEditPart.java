/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.analysis.activitydiagram.edit.part;

import java.util.ArrayList;
import java.util.List;

import nexcore.tool.uml.model.umldiagram.LabelNode;
import nexcore.tool.uml.ui.core.diagram.edit.part.AbstractDiagramConnectionEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.LabelNodeEditPart;

import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FigureListener;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.RoutingAnimator;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.RootEditPart;
import org.eclipse.swt.SWT;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.analysis</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.analysis.activitydiagram.edit.part</li>
 * <li>설 명 : ControlFlowEditPart</li>
 * <li>작성일 : 2009. 11. 30.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class ObjectFlowEditPart extends ActivityDiagramConnectionEditPart {

    /**
     * 
     * @see org.eclipse.gef.editparts.AbstractConnectionEditPart#createFigure()
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
        PolylineDecoration poly = new PolylineDecoration() {
            @Override
            public void paintFigure(Graphics graphics) {
                graphics.setAntialias(SWT.ON);
                super.paintFigure(graphics);
            }
        };
        PointList p = new PointList();
        p.addPoint(0, 0);
        p.addPoint(-2, -2);
        p.addPoint(-2, 2);
        poly.setPoints(p);
        connection.setTargetDecoration(poly);
        connection.setForegroundColor(ColorConstants.gray);
        connection.addRoutingListener(RoutingAnimator.getDefault());
        connection.setConnectionRouter(new BendpointConnectionRouter());
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
                            if (((LabelNode) editpart.getModel()).getOwner() == getModel())
                                ((LabelNodeEditPart) editpart).setConnectionAnchorPoints(sourcePoint, targetPoint);
                        }
                    }
                }
            }
        });
        return connection;
    }
}

