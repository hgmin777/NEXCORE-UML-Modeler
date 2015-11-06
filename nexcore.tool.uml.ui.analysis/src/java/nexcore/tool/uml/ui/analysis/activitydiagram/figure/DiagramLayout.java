/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.analysis.activitydiagram.figure;

import java.util.Iterator;

import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.analysis</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.analysis.activitydiagram.figure</li>
 * <li>설 명 : DiagramLayout</li>
 * <li>작성일 : 2011. 1. 25.</li>
 * <li>작성자 : zerotae</li>
 * </ul>
 */
public class DiagramLayout extends FreeformLayout {
    /**
     * partitionSpacing
     */
    private int partitionSpacing;

    /**
     * horizontal
     */
    private boolean horizontal;

    /**
     * partitionMargin
     */
    private int partitionMargin;

    /**
     * DiagramLayout
     * @param horizontal
     */
    public DiagramLayout(boolean horizontal) {
        this.horizontal = horizontal;
    }

    /**
     * DiagramLayout
     * @param horizontal
     * @param partitionSpacing
     * @param partitionMargin
     */
    public DiagramLayout(boolean horizontal, int partitionSpacing, int partitionMargin) {
        this.horizontal = horizontal;
        this.partitionSpacing = partitionSpacing;
        this.partitionMargin = partitionMargin;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.draw2d.LayoutManager#layout(org.eclipse.draw2d.IFigure)
     */
    /**
     * @see org.eclipse.draw2d.XYLayout#layout(org.eclipse.draw2d.IFigure)
     */
    public void layout(IFigure parent) {
        Iterator children = parent.getChildren().iterator();
        Point offset = getOrigin(parent);
        IFigure f;
        int lastPos = getPartitionlMargin();
        while (children.hasNext()) {
            f = (IFigure) children.next();
            Rectangle bounds = (Rectangle) getConstraint(f);
            if (bounds == null)
                continue;
            if (f instanceof ActivityPartitionFigure) {
                bounds = bounds.getCopy();
                if (this.horizontal) {
                    int y = lastPos;
                    lastPos += bounds.height + getPartitionSpacing();
                    bounds.setLocation(getPartitionlMargin(), y);
                } else {
                    int x = lastPos;
                    lastPos += f.getSize().width + getPartitionSpacing();
                    bounds.setLocation(x, getPartitionlMargin());
                }
                f.setBounds(bounds);
            } else {
                layoutNode(f, offset, bounds);
            }
        }
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

    /**
     * @return Returns the horizontal.
     */
    public boolean isHorizontal() {
        return horizontal;
    }

    /**
     * @param horizontal
     *            The horizontal to set.
     */
    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    /**
     * @return int
     */
    public int getPartitionSpacing() {
        return partitionSpacing;
    }

    /**
     * @param partitionSpacing
     *            void
     */
    public void setPartitionSpacing(int partitionSpacing) {
        this.partitionSpacing = partitionSpacing;
    }

    /**
     * @return Returns the partitionMargin.
     */
    public int getPartitionlMargin() {
        return partitionMargin;
    }

    /**
     * @param poolMargin
     *            The setPartitionMargin to set.
     */
    public void setPartitionMargin(int setPartitionMargin) {
        this.partitionMargin = setPartitionMargin;
    }
}
