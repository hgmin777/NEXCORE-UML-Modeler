/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.diagram.figure;

import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.registry.IConstantColorRegistry;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Cursors;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.Polygon;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.text.FlowPage;
import org.eclipse.draw2d.text.ParagraphTextLayout;
import org.eclipse.draw2d.text.TextFlow;
import org.eclipse.swt.graphics.Color;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.figure</li>
 * <li>설 명 : NoteFigure</li>
 * <li>작성일 : 2009. 11. 20.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class NoteFigure extends Figure {

    /** name */
    private String name;

    /** textFlow */
    private TextFlow textFlow;

    /** rectangleFigure */
    private RectangleFigure rectangleFigure = new RectangleFigure();

    /** bigRectangleFigure */
    private RectangleFigure bigRectangleFigure = new RectangleFigure();

    /** triangle */
    private Polygon triangle = new Polygon();

    /** foldRatio */
    private double foldRatio = 0.1;

    /**
     * NoteFigure
     */
    public NoteFigure() {
        super();
        textFlow = createTextFlow();
        setCursor(Cursors.IBEAM);
    }

    /**
     * @see org.eclipse.draw2d.Figure#paintFigure(org.eclipse.draw2d.Graphics)
     */
    protected void paintFigure(Graphics graphics) {
        //setBorder(new MarginBorder(getTextBorderInsets()));

        Rectangle ractangle = getBounds().getCopy();
        Rectangle copyRactangle = ractangle.getCopy();
        copyRactangle.translate((int) (copyRactangle.width * (1.0 - foldRatio)), 0);
        int width = (int) (copyRactangle.width * foldRatio);
        copyRactangle.width = copyRactangle.height = width;

        rectangleFigure.setBounds(ractangle);
        // rectangleFigure.setBackgroundColor(UiCorePlugin.getDefault().getColor(IConstantColorRegistry.LightGoldenrod));
        // rectangleFigure.setFill(true);
        rectangleFigure.setForegroundColor(UiCorePlugin.getDefault().getColor(IConstantColorRegistry.Black));
        rectangleFigure.paint(graphics);

        // triangle.setBackgroundColor(UiCorePlugin.getDefault().getColor(IConstantColorRegistry.LightGoldenrod));
        // triangle.setFill(true);
        triangle.setForegroundColor(UiCorePlugin.getDefault().getColor(IConstantColorRegistry.Black));

        rectangleFigure.setLineStyle(SWTGraphics.LINE_SOLID);
        rectangleFigure.setLineWidth(1);
        rectangleFigure.setOutline(true);
        bigRectangleFigure.setLineStyle(SWTGraphics.LINE_SOLID);
        bigRectangleFigure.setLineWidth(1);
        bigRectangleFigure.setOutline(true);
        triangle.setLineStyle(SWTGraphics.LINE_SOLID);
        triangle.setLineWidth(1);
        triangle.setOutline(true);

        bigRectangleFigure.setBounds(copyRactangle.getExpanded(1, 1));
        bigRectangleFigure.setBackgroundColor(ColorConstants.white);
        bigRectangleFigure.setForegroundColor(ColorConstants.white);
        bigRectangleFigure.paint(graphics);

        triangle.removeAllPoints();
        triangle.addPoint(copyRactangle.getTopLeft());
        triangle.addPoint(copyRactangle.getBottomLeft());
        triangle.addPoint(copyRactangle.getBottomRight());
        triangle.paint(graphics);
    }

    /**
     * flowPage
     */
    private FlowPage flowPage;

    /**
     * flow
     */
    private TextFlow flow;

    /**
     * createTextFlow
     * 
     * @return TextFlow
     */
    public TextFlow createTextFlow() {
        flowPage = new FlowPage();

        flow = new TextFlow();

        flow.setLayoutManager(new ParagraphTextLayout(flow, ParagraphTextLayout.WORD_WRAP_SOFT));
        flow.setForegroundColor(new Color(null, 0, 0, 0));

        if (getName() != null)
            flow.setText(getName());
        else
            flow.setText(""); //$NON-NLS-1$

        flowPage.add(flow);
        flowPage.setHorizontalAligment(PositionConstants.LEFT);
        flowPage.setBorder(new MarginBorder(new Insets(15, 7, 7, 7)));
        setLayoutManager(new StackLayout());
        add(flowPage);

        return flow;
    }

    /**
     * @return Returns the foldRatio.
     */
    public double getFoldRatio() {
        return foldRatio;
    }

    /**
     * @param foldRatio
     *            The foldRatio to set.
     */
    public void setFoldRatio(double foldRatio) {
        if (foldRatio > 0.0) {
            this.foldRatio = foldRatio;
        } else {
            this.foldRatio = 0.1;
        }
    }

    /**
     * getTextBorderInsets
     *  
     * @return Insets
     */
    Insets getTextBorderInsets() {
        Rectangle bounds = getBounds();
        int top = (int) (bounds.width * getFoldRatio()) - 4;
        return new Insets(top, 7, 7, 7);
    }

    /**
     * getName
     *  
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * setName
     *  
     * @param name void
     */
    public void setName(String name) {
        this.name = (name == null) ? "" : name; //$NON-NLS-1$
        textFlow.setText(this.name);
    }

    /**
     * setColor
     *  
     * @param color void
     */
    public void setColor(Color color) {
        rectangleFigure.setBackgroundColor(color);
        rectangleFigure.setFill(true);
        rectangleFigure.setForegroundColor(color);
        // bigRectangleFigure.setBackgroundColor(color);
        // bigRectangleFigure.setFill(true);
        // bigRectangleFigure.setForegroundColor(color);
        triangle.setBackgroundColor(color);
        triangle.setFill(true);
        triangle.setForegroundColor(color);
    }

    /**
     * setFontColor
     *  
     * @param color void
     */
    public void setFontColor(Color color) {
        flow.setForegroundColor(color);
    }

}
