/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.core.diagram.figure;

import org.eclipse.draw2d.AbstractBorder;
import org.eclipse.draw2d.Border;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.figure</li>
 * <li>설  명 : ShadowShape</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public abstract class ShadowShape extends AbstractProportionalShape {
    /**
     * SHADOW_COLOR
     */
    private static final Color SHADOW_COLOR = new Color(null, 150, 150, 150);

    /**
     * SHADOW_SIZE
     */
    private static final int SHADOW_SIZE = 3;

    /**
     * SHADOW_INSETS
     */
    private static final Insets SHADOW_INSETS = new Insets(0, 0, SHADOW_SIZE, SHADOW_SIZE);

    /**
     * ShadowShape
     * @param is3D
     * @param backgroundColor
     * @param foregroundColor
     */
    public ShadowShape(boolean is3D, Color backgroundColor, Color foregroundColor) {
        setForegroundColor(foregroundColor);
        setBackgroundColor(backgroundColor);
        myBorder = new ShadowBorder();
        setBorder(myBorder);
        set3D(is3D);
    }

    /**
     * is3D
     *  
     * @return boolean
     */
    protected boolean is3D() {
        return my3D;
    }

    /**
     * set3D
     *  
     * @param is3D void
     */
    public void set3D(boolean is3D) {
        if (my3D == is3D) {
            return;
        }
        my3D = is3D;
        repaint();
    }

    /**
     * @see org.eclipse.draw2d.Figure#getPreferredSize(int, int)
     */
    public Dimension getPreferredSize(int wHint, int hHint) {
        Dimension original = super.getPreferredSize(wHint, hHint);
        if (is3D()) {
            original.expand(SHADOW_SIZE, SHADOW_SIZE);
        }
        return original;
    }

    /**
     * Paints this figure, including its border and children. Border is painted
     * first.
     */
    public void paint(Graphics graphics) {
        paintBorder(graphics);
        if (getBackgroundColor() != null)
            graphics.setBackgroundColor(getBackgroundColor());
        if (getForegroundColor() != null)
            graphics.setForegroundColor(getForegroundColor());
        if (getFont() != null)
            graphics.setFont(getFont());
        paintFigure(graphics);
        paintClientArea(graphics);
    }

    /**
     * @see org.eclipse.draw2d.Shape#fillShape(org.eclipse.draw2d.Graphics)
     */
    protected void fillShape(Graphics graphics) {
        fillShape(graphics, getProportionalBounds());
    }

    /**
     * fillShape
     *  
     * @param graphics
     * @param bounds void
     */
    protected abstract void fillShape(Graphics graphics, Rectangle bounds);

    /**
     * @see org.eclipse.draw2d.Shape#outlineShape(org.eclipse.draw2d.Graphics)
     */
    protected void outlineShape(Graphics graphics) {
        outlineShape(graphics, getProportionalBounds());
    }

    /**
     * outlineShape
     *  
     * @param graphics
     * @param bounds void
     */
    protected abstract void outlineShape(Graphics graphics, Rectangle bounds);

    private class ShadowBorder extends AbstractBorder {

        public Insets getInsets(IFigure figure) {
            return is3D() ? SHADOW_INSETS : IFigure.NO_INSETS;
        }

        public boolean isOpaque() {
            return is3D();
        }

        public void paint(IFigure figure, Graphics graphics, Insets insets) {
            if (!is3D()) {
                return;
            }
            graphics.setBackgroundColor(SHADOW_COLOR);
            Rectangle rec = getProportionalBounds().getTranslated(SHADOW_SIZE, SHADOW_SIZE);
            graphics.pushState();
            graphics.clipRect(rec);
            // graphics.setClip(new Rectangle(rec.x, rec.y + rec.height -
            // getShift(), rec.width, getShift()));
            fillShape(graphics, rec);
            graphics.popState();
            // graphics.setClip(new Rectangle(rec.x + rec.width - getShift(),
            // rec.y, getShift(), rec.height));
            // fillShape(graphics, rec);
        }

    }

    /**
     * my3D
     */
    private boolean my3D;

    /**
     * myBorder
     */
    private Border myBorder;

}
