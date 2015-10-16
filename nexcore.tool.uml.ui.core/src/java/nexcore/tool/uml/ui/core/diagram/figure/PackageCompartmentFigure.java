/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.core.diagram.figure;

import org.eclipse.draw2d.AbstractBorder;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Insets;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.figure</li>
 * <li>설  명 : PackageCompartmentFigure</li>
 * <li>작성일 : 2009. 11. 5.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class PackageCompartmentFigure extends Figure {

    /**
     * Constructor
     */
    public PackageCompartmentFigure() {
        ToolbarLayout layout = new ToolbarLayout();
        layout.setMinorAlignment(ToolbarLayout.ALIGN_TOPLEFT);
        layout.setStretchMinorAxis(false);
        layout.setSpacing(2);
        setLayoutManager(layout);
        setBackgroundColor(ColorConstants.red);
        // setBorder(new CompartmentFigureBorder());
    }

    /**
     * <ul>
     * <li>업무 그룹명 : nexcore.modeler.uml.ui.core</li>
     * <li>서브 업무명 : nexcore.modeler.uml.ui.core.figure</li>
     * <li>설 명 : CompartmentFigureBorder</li>
     * <li>작성일 : 2009. 11. 5.</li>
     * <li>작성자 : 강경구</li>
     * </ul>
     */
    public class CompartmentFigureBorder extends AbstractBorder {

        /**
         * @see org.eclipse.draw2d.Border#getInsets(org.eclipse.draw2d.IFigure)
         */
        public Insets getInsets(IFigure figure) {
            return new Insets(1, 0, 0, 0);
        }

        /**
         * @see org.eclipse.draw2d.Border#paint(org.eclipse.draw2d.IFigure,
         *      org.eclipse.draw2d.Graphics, org.eclipse.draw2d.geometry.Insets)
         */
        public void paint(IFigure figure, Graphics graphics, Insets insets) {
            graphics.drawLine(getPaintRectangle(figure, insets).getTopLeft(), tempRect.getTopRight());
        }
    }
}
