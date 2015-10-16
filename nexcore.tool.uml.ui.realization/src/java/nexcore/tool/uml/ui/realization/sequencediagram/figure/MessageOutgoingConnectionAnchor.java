/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.realization.sequencediagram.figure;

import nexcore.tool.uml.model.umldiagram.AbstractView;

import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.figure</li>
 * <li>설 명 : MessageOutgoingConnectionAnchor</li>
 * <li>작성일 : 2009. 12. 17.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class MessageOutgoingConnectionAnchor extends AbstractConnectionAnchor {
    /** connection */
    private ConnectionEditPart connection;

    /** result */
    Point result = new Point();

    /**
     * @see org.eclipse.draw2d.AbstractConnectionAnchor#getReferencePoint()
     */
    @Override
    public Point getReferencePoint() {
        result.y = ((AbstractView) connection.getModel()).getY();
        return new Point(this.getOwner().getClientArea().x, result.y);
    }

    /**
     * MessageOutgoingConnectionAnchor
     * @param owner
     * @param connection
     */
    public MessageOutgoingConnectionAnchor(IFigure owner, ConnectionEditPart connection) {
        super(owner);
        this.connection = connection;
    }

    /**
     * setFigureAndConnection
     *  
     * @param owner
     * @param connection void
     */
    public void setFigureAndConnection(IFigure owner, ConnectionEditPart connection) {
        this.setOwner(owner);
        this.connection = connection;
    }

    /**
     * @see org.eclipse.draw2d.ConnectionAnchor#getLocation(org.eclipse.draw2d.geometry.Point)
     */
    public Point getLocation(Point reference) {
        Rectangle rectangle = Rectangle.SINGLETON;
        rectangle.setBounds(getOwner().getClientArea());
        getOwner().translateToAbsolute(rectangle);

        AbstractView viewModel = (AbstractView) connection.getModel();
        Point location = new Point(viewModel.getX(), viewModel.getY());
        getOwner().translateToAbsolute(location);
        result.y = location.y;

        // if (reference.x < rectangle.x) {
        // result.x = rectangle.x;
        // } else {
        // result.x = rectangle.x + rectangle.width;
        // }
        result.x = rectangle.x + rectangle.width / 2;

        return result;
    }

}
