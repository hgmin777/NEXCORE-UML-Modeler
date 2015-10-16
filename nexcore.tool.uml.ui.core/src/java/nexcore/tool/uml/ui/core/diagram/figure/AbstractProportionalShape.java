/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.core.diagram.figure;

import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.figure</li>
 * <li>설  명 : AbstractProportionalShape</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public abstract class AbstractProportionalShape extends Shape {

    /**
     * AbstractProportionalShape
     */
    public AbstractProportionalShape() {
    }

    /**
     * setW2HRatio
     *  
     * @param w2hRatio void
     */
    protected void setW2HRatio(float w2hRatio) {
        myW2HRatio = w2hRatio;
        myIsKeepingProportions = true;
    }

    /**
     * setKeepingProportions
     *  
     * @param newValue void
     */
    protected void setKeepingProportions(boolean newValue) {
        myIsKeepingProportions = newValue;
    }

    /**
     * isKeepingProportions
     *  
     * @return boolean
     */
    protected boolean isKeepingProportions() {
        return myIsKeepingProportions;
    }

    /**
     * If keeping proportions, returns the maximum rectangle that is fully
     * inside the bounds and has the specified proportions. Otherwise, returns
     * the bounds.
     */
    public Rectangle getProportionalBounds() {
        Rectangle area = getClientArea();
        if (!myIsKeepingProportions || myW2HRatio == 0.0f || area.height == 0) {
            return new Rectangle(area);
        }

        int newX = area.x;
        int newY = area.y;
        int newW = area.width;
        int newH = area.height;

        float currentRatio = ((float) newW) / ((float) newH);
        float discrepancy = currentRatio / myW2HRatio;

        if (discrepancy < 1) {
            // we are too high.
            newH = Math.round(newH * discrepancy);
            newY += (area.height - newH) / 2;
        } else {
            // we are too wide
            newW = Math.round(newW / discrepancy);
            newX += (area.width - newW) / 2;
        }
        return new Rectangle(newX, newY, newW, newH);
    }

    /**
     * If keeping proportions, returns the dimension tweaked by the same factor
     * as the proportional bounds relate to the original bounds. Otherwise,
     * returns the original dimension.
     */
    public Dimension adjustDimension(Dimension d) {
        Rectangle area = getClientArea();
        if (!myIsKeepingProportions || myW2HRatio == 0.0f || area.height == 0) {
            return new Dimension(d);
        }

        float currentRatio = ((float) area.width) / ((float) area.height);
        float discrepancy = currentRatio / myW2HRatio;

        if (discrepancy < 1) {
            return new Dimension(d.width, (int) (d.height * discrepancy));
        } else {
            return new Dimension((int) (d.width / discrepancy), d.height);
        }
    }

    /**
     * myW2HRatio
     */
    private float myW2HRatio = 1.0f;

    /**
     * myIsKeepingProportions
     */
    private boolean myIsKeepingProportions;

}
