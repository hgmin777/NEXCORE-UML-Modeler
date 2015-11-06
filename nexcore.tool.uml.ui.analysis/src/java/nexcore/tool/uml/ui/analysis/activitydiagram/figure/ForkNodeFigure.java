/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.analysis.activitydiagram.figure;

import nexcore.tool.uml.ui.core.diagram.figure.AbstractNotationNodeFigure;

import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.swt.graphics.Color;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.analysis</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.analysis.activitydiagram.figure</li>
 * <li>설 명 : OpaqueAction</li>
 * <li>작성일 : 2009. 12. 1.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class ForkNodeFigure extends AbstractNotationNodeFigure {

    /** rectangle */
    private RectangleFigure rectangle;

    /** width */
    private static int width = 150;

    /** height */
    private static int height = 20;

    /**
     * ForkNodeFigure
     */
    public ForkNodeFigure() {

        rectangle = new RectangleFigure();
        rectangle.setSize(width, height);
        // rectangle.setBackgroundColor(FigureUtil.getFigureImage());

        add(rectangle);
    }

    /**
     * @see org.eclipse.draw2d.Figure#setSize(int, int)
     */
    @Override
    public void setSize(int w, int h) {
        rectangle.setSize(w, h);
        super.setSize(w, h);
    }

    /**
     * setColor
     *  
     * @param color void
     */
    public void setColor(Color color) {
        // rectangle.setBackgroundColor(color);
    }

}
