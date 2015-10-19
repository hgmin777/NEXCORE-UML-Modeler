/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.core.diagram.figure;

import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.figure</li>
 * <li>설 명 : CollaborationFigure</li>
 * <li>작성일 : 2009. 11. 24.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class CollaborationFigure extends Ellipse implements IUMLFigure {

    /**
     * CollaborationFigure
     */
    public CollaborationFigure() {
        this(true);
    }

    /**
     * CollaborationFigure
     * 
     * @param isDangling
     */
    public CollaborationFigure(boolean isDangling) {
        this.isDangling = isDangling;

        this.setLineStyle(SWT.BORDER_DASH);
        setLayoutManager(new StackLayout() {
            @SuppressWarnings("unchecked")
            public void layout(IFigure figure) {
                Rectangle rectangle = figure.getClientArea();
                List children = figure.getChildren();
                IFigure child;
                org.eclipse.draw2d.geometry.Dimension dimension;
                for (int i = 0; i < children.size(); i++) {
                    child = (IFigure) children.get(i);
                    dimension = child.getPreferredSize(rectangle.width, rectangle.height);
                    dimension.width = Math.min(dimension.width, rectangle.width);
                    dimension.height = Math.min(dimension.height, rectangle.height);
                    Rectangle childRect = new Rectangle(rectangle.x + (rectangle.width - dimension.width) / 2,
                        rectangle.y + (rectangle.height - dimension.height) / 2 + (int) (i * dimension.height)
                            - (10 * (children.size() - 1)),
                        dimension.width,
                        dimension.height);
                    child.setBounds(childRect);
                }
            }
        });

        setOpaque(true);
    }

    /**
     * @see org.eclipse.draw2d.Fxxxxxigure#useLocalCoordinates()
     */
    @Override
    protected boolean useLocalCoordinates() {
        return true;
    }

    /**
     * isDangling
     */
    public boolean isDangling = true;

    /**
     * @see nexcore.tool.uml.ui.core.diagram.figure.IUMLFigure#isDangling()
     */
    public boolean isDangling() {
        return isDangling;
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.figure.IUMLFigure#setDangling(boolean)
     */
    public void setDangling(boolean isDangling) {
        this.isDangling = isDangling;
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.figure.IUMLFigure#refresh()
     */
    @Override
    public void refresh() {
        layout();
    }

    /**
     * @see org.eclipse.draw2d.Shape#paintFigure(org.eclipse.draw2d.Graphics)
     */
    @Override
    public void paintFigure(Graphics graphics) {
        super.paintFigure(graphics);

        graphics.setAntialias(SWT.ON);
        Rectangle bounds = getBounds();
        if (isDangling()) {
            graphics.setForegroundColor(ColorConstants.red);
            graphics.drawOval(bounds.x, bounds.y, 12, 12);
            graphics.drawLine(bounds.x + 2, bounds.y + 2, bounds.x + 10, bounds.y + 10);
            graphics.drawLine(bounds.x + 10, bounds.y + 2, bounds.x + 2, bounds.y + 10);
        }

    }
}
