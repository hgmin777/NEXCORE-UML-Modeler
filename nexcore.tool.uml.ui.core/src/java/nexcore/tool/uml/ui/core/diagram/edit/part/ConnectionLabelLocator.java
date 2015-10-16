/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.diagram.edit.part;

import nexcore.tool.uml.model.umldiagram.LabelType;

import org.eclipse.draw2d.ConnectionEndpointLocator;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.edit.part</li>
 * <li>설 명 : ConnectionLabelLocator</li>
 * <li>작성일 : 2009. 12. 7.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */

public class ConnectionLabelLocator extends ConnectionEndpointLocator {

    /** connection */
    private PolylineConnection connection;

    /** type */
    private LabelType type;

    /** figureBounds */
    private static Rectangle figureBounds = new Rectangle();

    /**
     * ConnectionLabelLocator
     * @param connection
     * @param type
     * @param isEnd
     */
    public ConnectionLabelLocator(PolylineConnection connection, LabelType type, boolean isEnd) {
        super(connection, isEnd);
        this.connection = connection;
        this.type = type;

    }

    /**
     * @see org.eclipse.draw2d.ConnectionEndpointLocator#relocate(org.eclipse.draw2d.IFigure)
     */
    public void relocate(IFigure label) {

        PointList pointList = connection.getPoints();

        if (LabelType.LABEL.equals(type)) {
            labelRelocate(label, pointList);
        }

    }

    /**
     * labelRelocate
     * 
     * @param label
     * @param pointList
     *            void
     */
    private void labelRelocate(IFigure label, PointList pointList) {
        int pointSize = pointList.size();
        int centerPoint = (pointSize - 1) / 2;
        Point startP = pointList.getPoint(centerPoint);
        Point endP = pointList.getPoint(centerPoint + 1);

        if (Math.abs(endP.x - startP.x) >= Math.abs(endP.y - startP.y)) {
            figureBounds.x = startP.x + (endP.x - startP.x) / 2 - label.getPreferredSize().width / 2;
            figureBounds.y = startP.y + (endP.y - startP.y) / 2 - (label.getPreferredSize().height + 2);
        } else {
            figureBounds.x = startP.x + (endP.x - startP.x) / 2 - label.getPreferredSize().width / 2;
            figureBounds.y = startP.y + (endP.y - startP.y) / 2 - label.getPreferredSize().height;
        }
        figureBounds.width = label.getPreferredSize().width;
        figureBounds.height = label.getPreferredSize().height;
        label.setBounds(figureBounds);
    }
}
