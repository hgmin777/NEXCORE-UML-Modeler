/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.core.diagram.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.figure</li>
 * <li>설 명 : EnumerationFigure</li>
 * <li>작성일 : 2009. 12. 7.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class EnumerationFigure extends AbstractNotationNodeFigure {

    /**
     * EnumerationFigure
     */
    public EnumerationFigure(){
        this(true);
    }
    
    /**
     * EnumerationFigure
     * @param isDangling
     */
    public EnumerationFigure(boolean isDangling) {
        setDangling(isDangling);
        ToolbarLayout layout = new ToolbarLayout();
        setLayoutManager(layout);
        setOpaque(true);

        setBorder(new LineBorder(ColorConstants.black));
    }
    
    /**
     * @see nexcore.tool.uml.ui.core.diagram.figure.AbstractNotationNodeFigure#paintFigure(org.eclipse.draw2d.Graphics)
     */
    @Override
    protected void paintFigure(Graphics graphics) {
        super.paintFigure(graphics);

        Rectangle bounds = getBounds();
        if(isDangling()){
            graphics.setForegroundColor(ColorConstants.red);
            graphics.drawOval(bounds.x, bounds.y, 12, 12);
            graphics.drawLine(bounds.x + 2, bounds.y + 2, bounds.x + 10, bounds.y + 10);
            graphics.drawLine(bounds.x + 10, bounds.y + 2, bounds.x + 2, bounds.y + 10);
        }
    }
}
