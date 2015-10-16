/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.diagram.figure;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.widgets.Display;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.figure</li>
 * <li>설 명 : TitleBarBorder</li>
 * <li>작성일 : 2011. 1. 25.</li>
 * <li>작성자 : zerotae</li>
 * 
 * Draw2D의 TitleBarBoarder를 확장하여 가로인 경우 타이틀 바의 텍스트가 돌아가도록 한다. 방향에 따라 가로, 세로로 그릴
 * 수 있다.
 * 
 * </ul>
 */
public class TitleBarBorder extends org.eclipse.draw2d.TitleBarBorder {
    /**
     * ADJUST_NONE
     */
    public static final int ADJUST_NONE = 0;

    /**
     * ADJUST_UPWARD
     */
    public static final int ADJUST_UPWARD = 1;

    /**
     * ADJUST_DOWNWARD
     */
    public static final int ADJUST_DOWNWARD = 2;

    /**
     * horizontal
     */
    private boolean horizontal = true;

    /**
     * boundaryVisible
     */
    private boolean boundaryVisible = true;

    /**
     * barWidth
     */
    private int barWidth = 50;

    /**
     * barVisible
     */
    private boolean barVisible = true;

    /**
     * lineColor
     */
    private Color lineColor;

    /**
     * lineStyle
     */
    private int lineStyle = Graphics.LINE_SOLID;

    /**
     * lineWidth
     */
    private int lineWidth = 1;

    /**
     * lineVisible
     */
    private boolean lineVisible = true;

    /**
     * textVisible
     */
    private boolean textVisible;

    /**
     * textHorizontal
     */
    private boolean textHorizontal;

    /**
     * fontName
     */
    private String fontName;

    /**
     * fontSize
     */
    private int fontSize;

    /**
     * textStyle
     */
    private int textStyle;

    /**
     * boundaryAdjust
     */
    private int boundaryAdjust = ADJUST_NONE;

    /**
     *  
     */
    public TitleBarBorder() {
        super();
    }

    /**
     * @param s
     */
    public TitleBarBorder(String s) {
        super(s);
    }

    /**
     * TitleBarBorder
     * @param s
     * @param horizontal
     * @param align
     */
    public TitleBarBorder(String s, boolean horizontal, int align) {
        super(s);
        this.horizontal = horizontal;
        setTextAlignment(align);
    }
    
    /**
     * getBounds
     *  
     * @return Rectangle
     */
    public Rectangle getBounds() {
        return tempRect;
    }

    /**
     * @see org.eclipse.draw2d.Border#paint(org.eclipse.draw2d.IFigure,
     *      org.eclipse.draw2d.Graphics, org.eclipse.draw2d.geometry.Insets)
     */
    public void paint(IFigure figure, Graphics g, Insets insets) {
        tempRect.setBounds(getPaintRectangle(figure, insets));
        Rectangle rect = tempRect.getCopy();

        // 바를 그린다.
        if (this.barVisible) {
            if (isHorizontal()) {
                rect.width = getBarWidth();
                paintHorizontalBar(figure, g, rect);
            } else {
                rect.height = getBarWidth();
                paintVerticalBar(figure, g, rect);
            }
        }

        // 라인을 그린다.
        if (this.lineVisible) {
            // 바운더리 보기가 거짓이면 도트라인을 그린다.
            if (!this.boundaryVisible) {
                this.lineStyle = Graphics.LINE_DOT;
            }
            g.setLineWidth(this.lineWidth);
            if (this.lineColor != null) {
                g.setForegroundColor(this.lineColor);
            }
            int dw = this.lineWidth / 2;

            // 바 둘레를 그린다.
            doAdjustBoundary(rect, dw);
            g.drawRectangle(rect);

            // 전체 둘레를 그린다.
            rect = tempRect.getCopy();
            doAdjustBoundary(rect, dw);
            g.drawRectangle(rect);
        }
    }

    /**
     * doAdjustBoundary
     *  
     * @param rect
     * @param dw
     * @return Rectangle
     */
    private Rectangle doAdjustBoundary(Rectangle rect, int dw) {
        if (this.lineWidth % 2 == 1) {
            rect.width--;
            rect.height--;
        }
        rect.shrink(dw, dw);
        if ((this.boundaryAdjust & ADJUST_DOWNWARD) == ADJUST_DOWNWARD) {
            if (isHorizontal()) {
                rect.height += dw;
            } else {
                rect.width += dw;
            }
        }
        if ((this.boundaryAdjust & ADJUST_UPWARD) == ADJUST_UPWARD) {
            if (isHorizontal()) {
                rect.y -= dw;
                rect.height += dw;
            } else {
                rect.x -= dw;
                rect.width += dw;
            }
        }
        return rect;
    }

    /**
     * paintVerticalBar
     *  
     * @param figure
     * @param g
     * @param rect void
     */
    private void paintVerticalBar(IFigure figure, Graphics g, Rectangle rect) {
        drawTitleBar(figure, g, rect);
    }

    /**
     * paintHorizontalBar
     *  
     * @param figure
     * @param g
     * @param rect void
     */
    private void paintHorizontalBar(IFigure figure, Graphics g, Rectangle rect) {
        Image image = getRotatedLabelImage(figure, rect);
        g.drawImage(image, rect.x, rect.y);
        image.dispose();
        
//        drawHorizontalTitleBar(figure, g, rect);
    }
    
    /**
     * rect는 타이틀 바를 그릴 사각형이다. 여기서는 이 사각형에 타이틀 바를 그린다.
     * 타이틀 바를 가로로 생성한 파티션의 왼쪽에 세로로 그린다.
     * 
     * @param figure
     * @param g
     * @param rect
     *            void
     */
    private void drawHorizontalTitleBar(IFigure figure, Graphics g, Rectangle rect) {
        g.setBackgroundColor(getBackgroundColor());
        g.fillRectangle(rect);

        Insets padding = getPadding();
        int x = rect.x + padding.left;
        int y = rect.y + padding.top + (rect.height - getTextExtents(figure).height) / 2;

        int textWidth = getTextExtents(figure).width;
        int freeSpace = rect.width - padding.getWidth() - textWidth;

        if (getTextAlignment() == PositionConstants.CENTER)
            freeSpace /= 2;
        if (getTextAlignment() != PositionConstants.LEFT)
            x += freeSpace;

        Font f = getFont(figure);
        FontData fData = f.getFontData()[0];
        fData.setName(this.getFontName());
        fData.setStyle(this.getTextStyle());
        fData.setHeight(this.getFontSize());
        g.setFont(f);
        g.setForegroundColor(this.getTextColor());
        g.drawString(getLabel(), x, y);
    }
    
    /**
     * rect는 타이틀 바를 그릴 사각형이다. 여기서는 이 사각형에 타이틀 바를 그린다.
     * 
     * @param figure
     * @param g
     * @param rect
     *            void
     */
    private void drawTitleBar(IFigure figure, Graphics g, Rectangle rect) {
        g.setBackgroundColor(getBackgroundColor());
        g.fillRectangle(rect);

        Insets padding = getPadding();
        int x = rect.x + padding.left;
        int y = rect.y + padding.top + (rect.height - getTextExtents(figure).height) / 2;

        int textWidth = getTextExtents(figure).width;
        int freeSpace = rect.width - padding.getWidth() - textWidth;

        if (getTextAlignment() == PositionConstants.CENTER)
            freeSpace /= 2;
        if (getTextAlignment() != PositionConstants.LEFT)
            x += freeSpace;

        Font f = getFont(figure);
        FontData fData = f.getFontData()[0];
        fData.setName(this.getFontName());
        fData.setStyle(this.getTextStyle());
        fData.setHeight(this.getFontSize());
        g.setFont(f);
        g.setForegroundColor(this.getTextColor());
        g.drawString(getLabel(), x, y);
    }

    /**
     * getRotatedLabelImage
     *  
     * @param figure
     * @param rect
     * @return Image
     */
    private Image getRotatedLabelImage(IFigure figure, Rectangle rect) {
        // 가로로 된 타이틀 사각형
        Rectangle temp = rect.getCopy().transpose();
        temp.x = temp.y = 0;

        // 이미지를 생성하고 이미지에 대한 GC를 생성한다.
        PaletteData palette = new PaletteData(0xFF, 0xFF00, 0xFF0000);

        ImageData imageData = new ImageData(temp.width, temp.height, 24, palette);
        Image image = new Image(Display.getCurrent(), imageData);
        GC gc = new GC(image);
        Graphics g = new SWTGraphics(gc);
        drawTitleBar(figure, g, temp);
        gc.dispose();

        ImageData data1 = image.getImageData();
        ImageData data2 = new ImageData(data1.height, data1.width, data1.depth, data1.palette);
        for (int x1 = 0; x1 < data1.width; x1++) {
            for (int y1 = 0; y1 < data1.height; y1++) {
                int x2 = y1;
                int y2 = -x1 + data1.width - 1;
                int pixel = data1.getPixel(x1, y1);
                data2.setPixel(x2, y2, pixel);
            }
        }

        Image result = new Image(Display.getCurrent(), data2);
        image.dispose();
        return result;
    }

    /**
     * @see org.eclipse.draw2d.AbstractLabeledBorder#calculateInsets(org.eclipse.draw2d.IFigure)
     */
    protected Insets calculateInsets(IFigure figure) {
        if (isHorizontal()) {
            return new Insets(0, (isBarVisible() ? getBarWidth() : 0), 0, 0);
        }
        return new Insets((isBarVisible() ? getBarWidth() : 0), 0, 0, 0);
    }

    // Getter/Setter

    /**
     * @return Returns the barFillColor.
     */
    public Color getBarFillColor() {
        return getBackgroundColor();
    }

    /**
     * @param barFillColor
     *            The barFillColor to set.
     */
    public void setBarFillColor(Color barFillColor) {
        setBackgroundColor(barFillColor);
    }

    /**
     * @return Returns the barLineColor.
     */
    public Color getLineColor() {
        return lineColor;
    }

    /**
     * @param barLineColor
     *            The barLineColor to set.
     */
    public void setLineColor(Color barLineColor) {
        this.lineColor = barLineColor;
    }

    /**
     * @return Returns the barLineStyle.
     */
    public int getLineStyle() {
        return lineStyle;
    }

    /**
     * @param barLineStyle
     *            The barLineStyle to set.
     */
    public void setLineStyle(int barLineStyle) {
        this.lineStyle = barLineStyle;
    }

    /**
     * @return Returns the barLineVisible.
     */
    public boolean isLineVisible() {
        return lineVisible;
    }

    /**
     * @param barLineVisible
     *            The barLineVisible to set.
     */
    public void setLineVisible(boolean barLineVisible) {
        this.lineVisible = barLineVisible;
    }

    /**
     * @return Returns the barLineWidth.
     */
    public int getLineWidth() {
        return lineWidth;
    }

    /**
     * @param barLineWidth
     *            The barLineWidth to set.
     */
    public void setLineWidth(int barLineWidth) {
        this.lineWidth = barLineWidth;
    }

    /**
     * @return Returns the barVisible.
     */
    public boolean isBarVisible() {
        return barVisible;
    }

    /**
     * @param barVisible
     *            The barVisible to set.
     */
    public void setBarVisible(boolean barVisible) {
        this.barVisible = barVisible;
        invalidate();
    }

    /**
     * @return Returns the barWidth.
     */
    public int getBarWidth() {
        return barWidth;
    }

    /**
     * @param barWidth
     *            The barWidth to set.
     */
    public void setBarWidth(int barWidth) {
        this.barWidth = barWidth;
    }

    /**
     * @return Returns the fontName.
     */
    public String getFontName() {
        return fontName;
    }

    /**
     * @param fontName
     *            The fontName to set.
     */
    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    /**
     * @return Returns the fontSize.
     */
    public int getFontSize() {
        return fontSize;
    }

    /**
     * @param fontSize
     *            The fontSize to set.
     */
    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
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
     * @return Returns the textHorizontal.
     */
    public boolean isTextHorizontal() {
        return textHorizontal;
    }

    /**
     * @param textHorizontal
     *            The textHorizontal to set.
     */
    public void setTextHorizontal(boolean textHorizontal) {
        this.textHorizontal = textHorizontal;
    }

    /**
     * @return Returns the textStyle.
     */
    public int getTextStyle() {
        return textStyle;
    }

    /**
     * @param textStyle
     *            The textStyle to set.
     */
    public void setTextStyle(int textStyle) {
        this.textStyle = textStyle;
    }

    /**
     * @return Returns the textVisible.
     */
    public boolean isTextVisible() {
        return textVisible;
    }

    /**
     * @param textVisible
     *            The textVisible to set.
     */
    public void setTextVisible(boolean textVisible) {
        this.textVisible = textVisible;
    }

    /**
     * @return Returns the boundaryVisible.
     */
    public boolean isBoundaryVisible() {
        return boundaryVisible;
    }

    /**
     * @param boundaryVisible
     *            The boundaryVisible to set.
     */
    public void setBoundaryVisible(boolean boundaryVisible) {
        this.boundaryVisible = boundaryVisible;
    }

    /**
     * @return Returns the boundaryAdjust.
     */
    public int getBoundaryAdjust() {
        return boundaryAdjust;
    }

    /**
     * @param boundaryAdjust
     *            The boundaryAdjust to set.
     */
    public void setBoundaryAdjust(int boundaryAdjust) {
        this.boundaryAdjust = boundaryAdjust;
    }
}
