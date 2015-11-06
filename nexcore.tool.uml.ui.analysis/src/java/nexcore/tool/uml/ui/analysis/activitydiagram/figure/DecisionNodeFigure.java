/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.analysis.activitydiagram.figure;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.ui.analysis.SWTResourceManager;
import nexcore.tool.uml.ui.core.diagram.figure.AbstractNotationNodeFigure;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.analysis</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.analysis.activitydiagram.figure</li>
 * <li>설 명 : OpaqueAction</li>
 * <li>작성일 : 2009. 12. 1.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class DecisionNodeFigure extends AbstractNotationNodeFigure {

    /** width */
    private static int width = 20;

    /** height */
    private static int height = 30;

    /** rectangle */
    private Rectangle r = new Rectangle();

    /** font */
    Font font = SWTResourceManager.getFont(UMLMessage.LABEL_COURIER, 8, SWT.NORMAL);

    /** color */
    private Color color;

    /**
     * DecisionNodeFigure
     */
    public DecisionNodeFigure() {
        setSize(width, height);
    }

    /**
     * @see org.eclipse.draw2d.Figure#paintFigure(org.eclipse.draw2d.Graphics)
     */
    @Override
    protected void paintFigure(Graphics graphics) {

        // Rectangle r = new Rectangle();
        r.setBounds(getBounds());
        r.crop(getInsets());

        int pixelWidth = 2;
        PointList diamond = new PointList(4);
        // 4 points of the diamond.
        Point top, right, bottom, left;
        top = new Point(r.x + r.width / 2, r.y);
        right = new Point((r.x + r.width - pixelWidth), r.y + r.height / 2);
        bottom = new Point((r.x + r.width / 2 - pixelWidth), r.y + r.height - 1); // The
                                                                                  // -1
                                                                                  // fixes
                                                                                  // the
                                                                                  // look
        left = new Point(r.x, r.y + r.height / 2);

        diamond.removeAllPoints();
        diamond.addPoint(top);
        diamond.addPoint(right);
        diamond.addPoint(bottom);
        diamond.addPoint(left);

        // graphics.setBackgroundColor(ColorConstants.lightGray);
        graphics.setAntialias(SWT.ON);

        graphics.fillPolygon(diamond);
        graphics.drawPolygon(diamond);
        super.paintFigure(graphics);
    }

    /**
     * @see org.eclipse.draw2d.Figure#setSize(int, int)
     */
    public void setSize(int x, int y) {
        // Get the current bounds, so we know our x and y positions
        r = getBounds();
        // Replace the width and height with our arguements
        r.height = y;
        r.width = x;
        setBounds(r);
        super.setSize(x, y);
    }

    /**
     * setColor
     *  
     * @param color void
     */
    public void setColor(Color color) {
        this.color = color;
    }
}
