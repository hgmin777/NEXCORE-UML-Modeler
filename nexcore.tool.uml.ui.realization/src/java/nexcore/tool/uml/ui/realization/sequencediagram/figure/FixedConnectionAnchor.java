/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.realization.sequencediagram.figure;

import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ScalableFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.figure</li>
 * <li>설 명 : FixedConnectionAnchor</li>
 * <li>작성일 : 2009. 12. 24.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class FixedConnectionAnchor extends AbstractConnectionAnchor {

    /**
     * leftToRight
     */
    public boolean leftToRight = true;

    /**
     * offsetH
     */
    public int offsetH;

    /**
     * offsetV
     */
    public int offsetV;

    /**
     * topDown
     */
    public boolean topDown = true;

    /**
     * FixedConnectionAnchor
     * @param owner
     */
    public FixedConnectionAnchor(IFigure owner) {
        super(owner);
    }

    /**
     * point
     */
    private Point point = null;

    /**
     * FixedConnectionAnchor
     * @param figure
     * @param point
     */
    public FixedConnectionAnchor(IFigure figure, Point point) {
        super(figure);
        this.point = point;
    }

    /**
     * @see org.eclipse.draw2d.AbstractConnectionAnchor#ancestorMoved(IFigure)
     */
    public void ancestorMoved(IFigure figure) {
        if (figure instanceof ScalableFigure)
            return;
        super.ancestorMoved(figure);
    }

    /**
     * @see org.eclipse.draw2d.ConnectionAnchor#getLocation(org.eclipse.draw2d.geometry.Point)
     */
    public Point getLocation(Point reference) {
        Rectangle rectangle = Rectangle.SINGLETON;
        rectangle.setBounds(getOwner().getClientArea());

        getOwner().translateToAbsolute(rectangle);

        int x, y;
        if (topDown)
            y = rectangle.y + offsetV;
        else
            y = rectangle.bottom() - 1 - offsetV;

        if (leftToRight)
            x = rectangle.x + offsetH;
        else
            x = rectangle.right() - 1 - offsetH;
        Point p = null;
        if (reference != null) {
            p = new PrecisionPoint(x, reference.y);
        } else {
            p = new PrecisionPoint(x, y);
        }
        getOwner().translateToAbsolute(p);
        if (point != null) {
            return point;
        } else {
            return p;
        }
    }

    /**
     * @see org.eclipse.draw2d.AbstractConnectionAnchor#getReferencePoint()
     */
    public Point getReferencePoint() {
        return getLocation(null);
    }

    /**
     * @param offsetH
     *            The offsetH to set.
     */
    public void setOffsetH(int offsetH) {
        this.offsetH = offsetH;
        fireAnchorMoved();
    }

    /**
     * @param offsetV
     *            The offsetV to set.
     */
    public void setOffsetV(int offsetV) {
        this.offsetV = offsetV;
        fireAnchorMoved();
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object o) {
        if (o instanceof FixedConnectionAnchor) {
            FixedConnectionAnchor fa = (FixedConnectionAnchor) o;

            if (fa.leftToRight == this.leftToRight && fa.topDown == this.topDown && fa.offsetH == this.offsetH
                && fa.offsetV == this.offsetV && fa.getOwner() == this.getOwner()) {
                return true;
            }
        }

        return false;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return ((this.leftToRight ? 31 : 0) + (this.topDown ? 37 : 0) + this.offsetH * 43 + this.offsetV * 47)
            ^ this.getOwner().hashCode();
    }

}
