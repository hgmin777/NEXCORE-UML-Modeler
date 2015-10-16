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
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.figure</li>
 * <li>설 명 : DestructionEventFigure</li>
 * <li>작성일 : 2009. 12. 30.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class DestructionEventFigure extends Figure {

    /**
     * DestructionEventFigure
     */
    public DestructionEventFigure() {
        setOpaque(false);
    }

    /**
     * @see org.eclipse.draw2d.Figure#paintFigure(org.eclipse.draw2d.Graphics)
     */
    protected void paintFigure(Graphics graphics) {
        Rectangle rectangle = getBounds().getCopy();
        graphics.setForegroundColor(UiCorePlugin.getDefault().getColor(IConstantColorRegistry.DimGray));
        paintXSymbol(rectangle, graphics);

    }

    /**
     * paintXSymbol
     * 
     * @param rectangle
     * @param graphics
     */
    private void paintXSymbol(Rectangle rectangle, Graphics graphics) {
        Rectangle bounds = getBounds().getCopy();

        if (bounds.width > bounds.height) {
            int diff = bounds.width - bounds.height;
            int shrink = (int) (bounds.height / 5 + bounds.height * 0.112);
            bounds.shrink(diff / 2 + shrink, +shrink);
        } else if (bounds.width < bounds.height) {
            int diff = bounds.height - bounds.width;
            int shrink = (int) (bounds.width / 5 + bounds.width * 0.112);
            bounds.shrink(shrink, diff / 2 + shrink);
        }

        graphics.setLineWidth(bounds.width / 6);

        graphics.drawLine(bounds.x, bounds.y, bounds.x + bounds.width, bounds.y + bounds.height);
        graphics.drawLine(bounds.x + bounds.width, bounds.y, bounds.x, bounds.y + bounds.height);
    }
}
