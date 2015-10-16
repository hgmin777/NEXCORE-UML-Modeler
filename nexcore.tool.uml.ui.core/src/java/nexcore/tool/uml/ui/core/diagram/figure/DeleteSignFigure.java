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
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * @author 강경구
 *
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.figure</li>
 * <li>설  명 : DeleteSignFigure</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class DeleteSignFigure extends Figure {
    /**
     * DeleteSignFigure
     */
    public DeleteSignFigure() {
        this("");
    }
    
    /**
     * DeleteSignFigure
     * @param string
     */
    public DeleteSignFigure(String string) {
        setBorder(new LineBorder(ColorConstants.black));
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
        
        Label nameLabel = new Label(string);
        add(nameLabel);
    }

    /**
     * @see org.eclipse.draw2d.Figure#paintFigure(org.eclipse.draw2d.Graphics)
     */
    @Override
    protected void paintFigure(Graphics graphics) {
         
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

             graphics.setForegroundColor(ColorConstants.lightGray);
             graphics.setLineWidth(bounds.width / 4);

             graphics.drawLine(bounds.x, bounds.y, bounds.x + bounds.width, bounds.y + bounds.height);
             graphics.drawLine(bounds.x + bounds.width, bounds.y, bounds.x, bounds.y + bounds.height);
         
         
        super.paintFigure(graphics);
    }
}
