/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.core.diagram.figure;

import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Polygon;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.figure</li>
 * <li>설  명 : PackageFigure</li>
 * <li>작성일 : 2009. 11. 5.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class PackageFigure extends AbstractNotationNodeFigure {

    /**
     * vRectangle
     */
    private Polygon vRectangle = new Polygon();

    /**
     * hRectangle
     */
    private Polygon hRectangle = new Polygon();

    /**
     * color
     */
    @SuppressWarnings("unused")
    private Color color;

    /**
     * PackageFigure
     */
    public PackageFigure() {
        this(true);
    }
    /**
     * PackageFigure
     * @param isDangling
     */
    public PackageFigure(boolean isDangling) {
        setDangling(isDangling);
        
        setLayoutManager(new StackLayout() {
            @SuppressWarnings("unchecked")
            public void layout(IFigure figure) {
                Rectangle rectangle = figure.getClientArea();
                List children = figure.getChildren();
                IFigure child;
                org.eclipse.draw2d.geometry.Dimension dimension;
                int sizeValue = children.size();
                for (int i = 0; i < children.size(); i++) {
                    if (!(children.get(i) instanceof RectangleFigure)) {
                        child = (IFigure) children.get(i);
                        dimension = child.getPreferredSize(rectangle.width, rectangle.height);
                        dimension.width = Math.min(dimension.width, rectangle.width);
                        dimension.height = Math.min(dimension.height, rectangle.height);
                        Rectangle childRect = new Rectangle(rectangle.x + (rectangle.width - dimension.width) / 2,
                            rectangle.y + (rectangle.height - dimension.height) / 2 + (int) (i * dimension.height)
                                - (sizeValue * 3),
                            dimension.width,
                            dimension.height);
                        child.setBounds(childRect);
                    }
                }
            }
        });

        setOpaque(true);

    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.figure.AbstractNotationNodeFigure#paintFigure(org.eclipse.draw2d.Graphics)
     */
    @Override
    protected void paintFigure(Graphics graphics) {
        super.paintFigure(graphics);
        graphics.setAntialias(SWT.ON);
        
        Point lt = new Point(getLocation().x, getLocation().y);
        Point rt = new Point(getLocation().x + getSize().width - 1, getLocation().y + ((int) getSize().height / 5) - 1);
        Point ld = new Point(getLocation().x, getLocation().y + getSize().height - 1);
        Point rd = new Point(getLocation().x + getSize().width - 1, getLocation().y + ((int) getSize().height - 1));
        Point mt = new Point(getLocation().x + ((int) getSize().width / 3) - 1, getLocation().y);
        Point lm = new Point(getLocation().x, getLocation().y + ((int) getSize().height / 5) - 1);
        // Point mm = new Point(getLocation().x + ((int) getSize().width / 3) -
        // 1, getLocation().y
        // + ((int) getSize().height / 5) - 1);
        Point md = new Point(getLocation().x + ((int) getSize().width / 3) - 1, getLocation().y + getSize().height - 1);

        vRectangle.removeAllPoints();
        vRectangle.addPoint(lt);
        vRectangle.addPoint(mt);
        vRectangle.addPoint(md);
        vRectangle.addPoint(ld);
        vRectangle.addPoint(lt);
        vRectangle.paint(graphics);

        hRectangle.removeAllPoints();
        hRectangle.addPoint(lm);
        hRectangle.addPoint(rt);
        hRectangle.addPoint(rd);
        hRectangle.addPoint(ld);
        hRectangle.addPoint(lm);
        hRectangle.paint(graphics);
        
        Rectangle bounds = getBounds();
        if(isDangling()){
            graphics.setForegroundColor(ColorConstants.red);
            graphics.drawOval(bounds.x, bounds.y, 12, 12);
            graphics.drawLine(bounds.x + 2, bounds.y + 2, bounds.x + 10, bounds.y + 10);
            graphics.drawLine(bounds.x + 10, bounds.y + 2, bounds.x + 2, bounds.y + 10);
        }

        // Point lt = new Point(getLocation().x, getLocation().y);
        // Point rt = new Point(getLocation().x + getSize().width - 1,
        // getLocation().y + ((int) getSize().height / 5) - 1);
        // Point ld = new Point(getLocation().x, getLocation().y +
        // getSize().height - 1);
        // Point rd = new Point(getLocation().x + getSize().width - 1,
        // getLocation().y + ((int) getSize().height - 1));
        //
        // Point mt = new Point(getLocation().x + ((int) getSize().width / 3) -
        // 1, getLocation().y);
        // Point lm = new Point(getLocation().x, getLocation().y + ((int)
        // getSize().height / 5) - 1);
        // Point rm = new Point(getLocation().x + ((int) getSize().width / 3) -
        // 1, getLocation().y
        // + ((int) getSize().height / 5) - 1);
        //
        // PointList pl = new PointList();
        // pl.addPoint(lt);
        // pl.addPoint(mt);
        // pl.addPoint(rm);
        // pl.addPoint(rt);
        //        
        // pl.addPoint(rd);
        // pl.addPoint(ld);
        // pl.addPoint(lm);
        // pl.addPoint(rm);
        // pl.addPoint(lm);
        // pl.addPoint(lt);
        //        
        // graphics.drawPolygon(pl);
        //        
        // graphics.drawLine(lt, mt);
        // graphics.drawLine(mt, rm);
        // graphics.drawLine(lm, rt);
        // graphics.drawLine(rt, rd);
        // graphics.drawLine(lt, ld);
        // graphics.drawLine(ld, rd);

    }

    /**
     * @see org.eclipse.draw2d.Figure#useLocalCoordinates()
     */
    @Override
    protected boolean useLocalCoordinates() {
        return true;
    }

    /**
     * setColor
     *  
     * @param color void
     */
    public void setColor(Color color) {
        this.color = color;
        vRectangle.setBackgroundColor(color);
        vRectangle.setFill(true);
        hRectangle.setBackgroundColor(color);
        hRectangle.setFill(true);
    }
}
