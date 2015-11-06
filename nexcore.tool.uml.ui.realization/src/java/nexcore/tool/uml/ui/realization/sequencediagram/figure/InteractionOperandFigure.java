/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.realization.sequencediagram.figure;

import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.registry.IConstantColorRegistry;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.figure</li>
 * <li>설  명 : InteractionOperandFigure</li>
 * <li>작성일 : 2011. 4. 20.</li>
 * <li>작성자 : zerotae</li>
 * </ul>
 */
public class InteractionOperandFigure extends Figure {

    /**
     * operandBoundary
     */
    private Polyline operandUnderBoundary;

    /**
     * InteractionOperandFigure
     */
    public InteractionOperandFigure() {
        createContents();
    }
    
    /**
     * createContents
     *   void
     */
    private void createContents() {

        setLayoutManager(new InteractionOperandLayout());
                
        operandUnderBoundary = new Polyline();
        operandUnderBoundary.setFill(true);
        operandUnderBoundary.setOpaque(true);
        operandUnderBoundary.setLineWidth(1);
        operandUnderBoundary.setLineStyle(Graphics.LINE_DASH);
        operandUnderBoundary.setForegroundColor(UiCorePlugin.getDefault().getColor(IConstantColorRegistry.DimGray));
        this.add(operandUnderBoundary);
    }
    
    /**
     * getInitializedPointList
     *  
     * @return PointList
     */
    private PointList getInitializedPointList() {
        Rectangle ractangle = getBounds().getCopy();
        PointList points = new PointList(2);
        points.addPoint(ractangle.x, ractangle.y + ractangle.height-1);
        points.addPoint(ractangle.x + ractangle.width,  ractangle.y + ractangle.height-1);
        return points;
    }
    
    /**
     * refreshOperandUnderBoundary
     *   void
     */
    public void refreshOperandUnderBoundary() {
        operandUnderBoundary.setPoints(getInitializedPointList());
    }
}
