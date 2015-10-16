/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.realization.sequencediagram.figure;

import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.registry.IConstantColorRegistry;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.figure</li>
 * <li>설  명 : LineFigure</li>
 * <li>작성일 : 2011. 5. 3.</li>
 * <li>작성자 : SKCCADMIN</li>
 * </ul>
 */
public class LineFigure extends Figure {
    
    /**
     * LINE_WIDTH
     */
    public static final int LINE_WIDTH = 3;

    /**
     * line
     */
    private Polyline line = new Polyline();
    
    /**
     * start_point
     */
    private Point start_point = new Point(0,0);
    /**
     * end_point
     */
    private Point end_point = new Point(0,0);
    
    /**
     * @see org.eclipse.draw2d.Figure#paintFigure(org.eclipse.draw2d.Graphics)
     */
    @Override
    protected void paintFigure(Graphics graphics) {
        super.paintFigure(graphics);
        Rectangle bound = getBounds().getCopy();      
        
        int x = bound.x + bound.width / 2;
        int y1 = bound.y;
        int y2 = bound.y + bound.height;
        if( start_point.x != x || start_point.y != y1 || end_point.y != y2) {
            start_point = new Point(bound.x + bound.width / 2, bound.y);
            end_point = new Point(bound.x + bound.width / 2, bound.y + bound.height);
            line.removeAllPoints();
            line.addPoint(start_point);
            line.addPoint(end_point);
        } 
    }
    
    /**
     * LineFigure
     */
    public LineFigure() {
        // LifeLine
        line.setOpaque(false);
        line.setLineWidth(LINE_WIDTH);
        line.setForegroundColor(UiCorePlugin.getDefault().getColor(IConstantColorRegistry.DimGray));
        line.setLineStyle(Graphics.LINE_DASH);

        this.add(line);
    }
    
    /**
     * getLine
     *  
     * @return Polyline
     */
    public Polyline getLine() {
        return line;
    }
}
