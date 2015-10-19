/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.realization.classdiagram.edit.part;

import nexcore.tool.uml.ui.core.diagram.edit.part.AbstractDiagramConnectionEditPart;

import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.RoutingAnimator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.classdiagram.edit.part</li>
 * <li>설 명 : GeneralizationEditPart</li>
 * <li>작성일 : 2009. 11. 19.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class GeneralizationEditPart extends AbstractDiagramConnectionEditPart {
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
        connection.setForegroundColor(Display.getCurrent().getSystemColor(SWT.COLOR_BLACK));
        PolygonDecoration polygonDecoreation = new PolygonDecoration();
        polygonDecoreation.setBackgroundColor(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        polygonDecoreation.setScale(10, 5);
        connection.setTargetDecoration(polygonDecoreation);
        connection.addRoutingListener(RoutingAnimator.getDefault());
        connection.setConnectionRouter(new BendpointConnectionRouter());

        return connection;
    }

}
