/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.diagram.figure;

import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.registry.IConstantColorRegistry;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RectangleFigure;
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
 * <li>설 명 : TextFigure</li>
 * <li>작성일 : 2009. 11. 20.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class TextFigure extends Figure {

    /** name */
    private String name;

    /** textFlow */
    private TextFlow textFlow;

    /** insets */
    protected static Insets insets = new Insets(7, 7, 7, 7);

    /** foldRatio */
    private double foldRatio = 0.1;

    /**
     * TextFigure
     */
    public TextFigure() {
        setBorder(new MarginBorder(insets));
        textFlow = createTextFlow();
    }

    /** rectangleFigure */
    private RectangleFigure rectangleFigure = new RectangleFigure();

    /**
     * @see org.eclipse.draw2d.Figure#paintFigure(org.eclipse.draw2d.Graphics)
     */
    protected void paintFigure(Graphics graphics) {
        // this.setBorder(new MarginBorder(insets));
        // graphics.translate(getLocation());
        // graphics.setLineWidth(1);
        // graphics.setLineStyle(SWTGraphics.LINE_SOLID);
        // graphics.setForegroundColor(UiCorePlugin.getDefault().getColor(IConstantColorRegistry.Black));
        // graphics.setBackgroundColor(UiCorePlugin.getDefault().getColor(IConstantColorRegistry.LightGoldenrod));
        // graphics.translate(getLocation().getNegated());

        this.setBorder(new MarginBorder(insets));
        Rectangle ractangle = getBounds().getCopy();
        Rectangle copyRactangle = ractangle.getCopy();
        copyRactangle.translate((int) (copyRactangle.width * (1.0 - foldRatio)), 0);
        int width = (int) (copyRactangle.width * foldRatio);
        copyRactangle.width = copyRactangle.height = width;

        rectangleFigure.setBounds(ractangle);
        rectangleFigure.setForegroundColor(UiCorePlugin.getDefault().getColor(IConstantColorRegistry.Black));
        rectangleFigure.paint(graphics);
        rectangleFigure.setBorder(new LineBorder(ColorConstants.white));

    }

    /**
     * flowPage
     */
    private FlowPage flowPage = new FlowPage();

    /**
     * flow
     */
    private TextFlow flow = new TextFlow();

    /**
     * createTextFlow
     * 
     * @return TextFlow
     */
    public TextFlow createTextFlow() {
        // FlowPage flowPage = new FlowPage();
        // TextFlow flow = new TextFlow();

        flow.setLayoutManager(new ParagraphTextLayout(flow, ParagraphTextLayout.WORD_WRAP_SOFT));
        flow.setForegroundColor(new Color(null, 0, 0, 0));

        if (getName() != null)
            flow.setText(getName());
        else
            flow.setText(""); //$NON-NLS-1$

        flowPage.add(flow);
        flowPage.setHorizontalAligment(PositionConstants.LEFT);

        setLayoutManager(new StackLayout());
        add(flowPage);

        return flow;
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
        this.name = (name == null) ? "" : name;
        textFlow.setText(this.name);
    }

    /**
     * setColor
     *  
     * @param color void
     */
    public void setColor(Color color) {
        rectangleFigure.setBackgroundColor(color);
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
