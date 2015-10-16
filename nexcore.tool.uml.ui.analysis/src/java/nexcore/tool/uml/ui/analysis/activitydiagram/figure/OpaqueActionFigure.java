/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.analysis.activitydiagram.figure;

import nexcore.tool.uml.ui.core.registry.IConstantImageRegistry;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.analysis</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.analysis.activitydiagram.figure</li>
 * <li>설 명 : OpaqueAction</li>
 * <li>작성일 : 2009. 12. 1.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class OpaqueActionFigure extends Shape {

    /**
     * color
     */
    Color color;

    /**
     * OpaqueActionFigure
     * @param name
     * @param image
     */
    public OpaqueActionFigure(String name, Image image) {
        // ToolbarLayout layout = new ToolbarLayout();
        // setLayoutManager(layout);
    }

    /**
     * @param name
     */
    public OpaqueActionFigure(String name) {
        this(name, IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_OPAQUEACTION));
    }

    /**
     * @see Shape#fillShape(Graphics)
     */
    protected void fillShape(Graphics graphics) {
        graphics.pushState();
        Rectangle bound = this.getBounds().getCopy();

        graphics.fillRoundRectangle(bound, 30, 30);
        graphics.popState();
    }

    /**
     * @see org.eclipse.draw2d.Shape#outlineShape(org.eclipse.draw2d.Graphics)
     */
    protected void outlineShape(Graphics graphics) {
        Rectangle r = getBounds().getCopy();
        r.x = r.x + lineWidth / 2;
        r.y = r.y + lineWidth / 2;
        r.width = r.width - Math.max(1, lineWidth);
        r.height = r.height - Math.max(1, lineWidth);
        graphics.drawRoundRectangle(r, 30, 30);
    }

    /**
     * setColor
     *  
     * @param color void
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @see org.eclipse.draw2d.Shape#paintFigure(org.eclipse.draw2d.Graphics)
     */
    @Override
    public void paintFigure(Graphics graphics) {
        graphics.setAntialias(SWT.ON);
        super.paintFigure(graphics);
    }
}
