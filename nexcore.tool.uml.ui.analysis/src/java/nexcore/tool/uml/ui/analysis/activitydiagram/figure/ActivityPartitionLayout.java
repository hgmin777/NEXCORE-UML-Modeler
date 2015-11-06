/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.analysis.activitydiagram.figure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nexcore.tool.uml.ui.core.diagram.figure.TitleBarBorder;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.analysis</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.analysis.activitydiagram.figure</li>
 * <li>설 명 : ActivityPartitionLayout</li>
 * <li>작성일 : 2011. 1. 25.</li>
 * <li>작성자 : zerotae</li>
 * </ul>
 */
public class ActivityPartitionLayout extends DiagramLayout {
    /**
     * @todo Provide description for the constructor
     * 
     */
    public ActivityPartitionLayout(boolean horizontal) {
        super(horizontal);
    }

    /**
     * @see nexcore.tool.uml.ui.analysis.activitydiagram.figure.DiagramLayout#layout(org.eclipse.draw2d.IFigure)
     */
    public void layout(IFigure parent) {
        Iterator children = parent.getChildren().iterator();
        Point offset = getOrigin(parent);
        List partitionList = new ArrayList();
        while (children.hasNext()) {
            IFigure f = (IFigure) children.next();
            Rectangle bounds = (Rectangle) getConstraint(f);
            if (bounds == null)
                continue;
            if (f instanceof ActivityPartitionFigure) {
                partitionList.add(f);
            } else {
                layoutNode(f, offset, bounds);
            }
        }

        int h = 0;
        Rectangle clientArea = parent.getClientArea();
        for (Iterator laneIter = partitionList.iterator(); laneIter.hasNext();) {
            ActivityPartitionFigure f = (ActivityPartitionFigure) laneIter.next();
            Rectangle bounds = (Rectangle) getConstraint(f);

            TitleBarBorder border = f.getTitleBarBorder();
            bounds = bounds.getCopy();

            if (laneIter.hasNext() || f.getParent() instanceof ActivityPartitionFigure) {
                border.setBoundaryAdjust(border.getBoundaryAdjust() | TitleBarBorder.ADJUST_DOWNWARD);
            }
            if (h > 0 || !isFirstPartition(f)) {
                border.setBoundaryAdjust(border.getBoundaryAdjust() | TitleBarBorder.ADJUST_UPWARD);
            }

            if (isHorizontal()) {
                bounds.setSize(clientArea.width, bounds.height);
                bounds.setLocation(clientArea.x, clientArea.y + h);
                h += bounds.height;
            } else {
                bounds.setSize(bounds.width, clientArea.height);
                bounds.setLocation(clientArea.x + h, clientArea.y);
                h += bounds.width;
            }
            bounds.translate(offset);
            f.setBounds(bounds);
        }
        partitionList.clear();
        partitionList = null;
    }

    /**
     * isFirstPartition
     * 
     * @param firstChild
     * @return boolean
     */
    private boolean isFirstPartition(ActivityPartitionFigure firstChild) {
        IFigure parent = firstChild.getParent();
        if (parent instanceof ActivityPartitionFigure) {
            if (parent.getParent().getChildren().indexOf(parent) == 0) {
                isFirstPartition((ActivityPartitionFigure) parent);
            }
            return false;
        }
        return true;
    }
}
