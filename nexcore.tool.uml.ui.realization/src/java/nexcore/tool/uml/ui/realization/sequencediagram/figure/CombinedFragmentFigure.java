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

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.Polygon;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.figure</li>
 * <li>설  명 : CombinedFragmentFigure</li>
 * <li>작성일 : 2011. 4. 7.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class CombinedFragmentFigure extends RectangleFigure {
    
    /**
     * LABEL_HEIGHT
     */
    public static final int LABEL_HEIGHT = 20;
    /**
     * LABEL_WEDGE
     */
    public static final int LABEL_WEDGE = 13;
    /**
     * LABEL_WIDTH
     */
    public static final int LABEL_WIDTH = 90;
    
    /**
     * fragmentBoundary
     */
    private Polygon fragmentBoundary;
    /**
     * fragmentLabel
     */
    private Label fragmentLabel;
    
    /**
     * CombinedFragmentFigure
     */
    public CombinedFragmentFigure() {
        createContents();
    }
    
    /**
     * @see org.eclipse.draw2d.Shape#paintFigure(org.eclipse.draw2d.Graphics)
     */
    @Override
    public void paintFigure(Graphics graphics) {
        graphics.setAntialias(SWT.ON);
        super.paintFigure(graphics);
        fragmentBoundary.setPoints(getInitializedPointList());
        fragmentLabel.setLocation(getBounds().getCopy().getLocation());
        fragmentLabel.setSize(LABEL_WIDTH, LABEL_HEIGHT);
        
    }
    
    /**
     * createContents
     *   void
     */
    private void createContents() {
        
        setLayoutManager(new CombinedFragmentLayout());
        setBorder(new LineBorder(0));
        setFill(false);
        setXOR(false);
        
        fragmentBoundary = new Polygon();
        fragmentBoundary.setFill(true);
        fragmentBoundary.setBackgroundColor(UiCorePlugin.getDefault().getColor(IConstantColorRegistry.LightGray));
        this.add(fragmentBoundary);
        
        fragmentLabel = new Label("None");
        this.add(fragmentLabel);

    }
    
    
    
    /**
     * getInitializedPointList
     *  
     * @return PointList
     */
    private PointList getInitializedPointList() {
        Rectangle ractangle = getBounds().getCopy();
        PointList points = new PointList(5);
        points.addPoint(ractangle.x, ractangle.y);
        points.addPoint(ractangle.x +LABEL_WIDTH, ractangle.y);
        points.addPoint(ractangle.x +LABEL_WIDTH, ractangle.y + LABEL_WEDGE);
        points.addPoint(ractangle.x +LABEL_WIDTH-LABEL_WEDGE, ractangle.y + LABEL_HEIGHT);
        points.addPoint(ractangle.x +0, ractangle.y + LABEL_HEIGHT);
        return points;
    }
       
    /**
     * setText
     *  
     * @param name void
     */
    public void setText(String name) {
        fragmentLabel.setText(name);
    }

    
}
