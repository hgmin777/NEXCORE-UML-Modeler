/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.realization.sequencediagram.figure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nexcore.tool.uml.ui.core.UICoreConstant;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.figure</li>
 * <li>설  명 : CombinedFragmentLayout</li>
 * <li>작성일 : 2011. 4. 20.</li>
 * <li>작성자 : zerotae</li>
 * </ul>
 */
public class CombinedFragmentLayout extends XYLayout {

    /**
     * @see org.eclipse.draw2d.XYLayout#layout(org.eclipse.draw2d.IFigure)
     */
    @SuppressWarnings("unchecked")
    @Override
    public void layout(IFigure parent) {
        Iterator children = parent.getChildren().iterator();
        Point offset = getOrigin(parent);
        List operanFiguredList = new ArrayList();
        while (children.hasNext()) {
            IFigure childFigure = (IFigure) children.next();
            Rectangle bounds = (Rectangle) getConstraint(childFigure);
            if (bounds == null)
                continue;
            if (childFigure instanceof InteractionOperandFigure) {
                operanFiguredList.add(childFigure);
            } else {
                layoutNode(childFigure, offset, bounds);
            }
        }

        int marginY = UICoreConstant.FRAGMENT_LABEL_HEIGHT;
        Rectangle clientArea = parent.getClientArea();
        for (Iterator laneIter = operanFiguredList.iterator(); laneIter.hasNext();) {
            InteractionOperandFigure figure = (InteractionOperandFigure) laneIter.next();
            Rectangle bounds = (Rectangle) getConstraint(figure);

            bounds = bounds.getCopy();
            bounds.setLocation(clientArea.x, clientArea.y + marginY);
            //bounds.setSize(clientArea.width, figure.getSize().height);
            bounds.setSize(clientArea.width, bounds.height);
           //marginY += figure.getSize().height;
            marginY += bounds.height;
            figure.setBounds(bounds);
            figure.refreshOperandUnderBoundary();
                
        }
        operanFiguredList.clear();
        operanFiguredList = null;
    }
    
    /**
     * layoutNode
     *  
     * @param f
     * @param offset
     * @param bounds void
     */
    protected void layoutNode(IFigure f, Point offset, Rectangle bounds) {
        if (bounds == null) {
            return;
        }
        if (bounds.width == -1 || bounds.height == -1) {
            Dimension preferredSize = f.getPreferredSize(bounds.width, bounds.height);
            bounds = bounds.getCopy();
            if (bounds.width == -1)
                bounds.width = preferredSize.width;
            if (bounds.height == -1)
                bounds.height = preferredSize.height;
        }
        bounds = bounds.getTranslated(offset);
        f.setBounds(bounds);
    }
}
