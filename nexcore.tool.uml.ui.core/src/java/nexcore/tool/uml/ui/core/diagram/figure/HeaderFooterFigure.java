/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.core.diagram.figure;

import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.ui.core.print.PrintPage;
import nexcore.tool.uml.ui.core.util.PreferenceUtil;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.figure</li>
 * <li>설 명 : HeaderFooterFigure</li>
 * <li>작성일 : 2011. 10. 07.</li>
 * <li>작성자 : 강석찬</li>
 * </ul>
 */
public class HeaderFooterFigure extends Figure {

    /**
     * headerText
     */
    private String headerText;

    /**
     * footerText
     */
    private String footerText;

    /**
     * colsOffset
     */
    int colsOffset;

    /**
     * rowOffset
     */
    int rowOffset;

    /**
     * topCenter
     */
    int topCenter;

    /**
     * rowSize
     */
    int rowSize;

    /**
     * maxWidth
     */
    int maxWidth;

    /**
     * HeaderFooterFigure
     */
    public HeaderFooterFigure() {
        super();
    }

    /**
     * 머리글의 용지 위로부터의 여백 길이를 가져온다.
     * 
     * @return
     */
    public int getHeaderMargin() {
        boolean useMm = PreferenceUtil.INSTANCE.getValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_MM);
        double margin = Double.valueOf(PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_HEADER));
        if (useMm) {
            margin = PrintPage.mmToPixel(margin);
        } else {
            margin = PrintPage.inchesToPixel(margin);
        }
        return (int) Math.round(margin);
    }

    /**
     * 꼬리글의 용지 아래로부터의 여백 길이를 가져온다.
     * 
     * @return
     */
    public int getFooterMargin() {
        boolean useMm = PreferenceUtil.INSTANCE.getValueOfBooleanFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_MM);
        double margin = Double.valueOf(PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_FOOTER));
        if (useMm) {
            margin = PrintPage.mmToPixel(margin);
        } else {
            margin = PrintPage.inchesToPixel(margin);
        }
        return (int) Math.round(margin);
    }

    /**
     * 머리글/꼬리글의 최대 길이(글자수)를 설정한다.
     * 
     * @return
     */
    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    /**
     * setOptions
     *  
     * @param colsOffset
     * @param rowOffset
     * @param topCenter
     * @param rowSize void
     */
    public void setOptions(int colsOffset, int rowOffset, int topCenter, int rowSize) {
        this.colsOffset = colsOffset;
        this.rowOffset = rowOffset;
        this.topCenter = topCenter;
        this.rowSize = rowSize;
    }

    /**
     * setHeaderText
     *  
     * @param headerText void
     */
    public void setHeaderText(String headerText) {
        this.headerText = headerText;
    }

    /**
     * setFooterText
     *  
     * @param footerText void
     */
    public void setFooterText(String footerText) {
        this.footerText = footerText;
    }

    /**
     * @see org.eclipse.draw2d.Figure#paintFigure(org.eclipse.draw2d.Graphics)
     */
    public void paintFigure(Graphics g) {

        super.paintFigure(g);

        g.setForegroundColor(ColorConstants.blue);
        g.setBackgroundColor(ColorConstants.blue);

        if (null != headerText) {
            Dimension textSize = FigureUtilities.getTextExtents(headerText, getFont());
            int length = headerText.length();
            if (textSize.width > maxWidth / 2) {
                headerText = headerText.substring(length - length * maxWidth / 2 / textSize.width);
                textSize = FigureUtilities.getTextExtents(headerText, getFont());
            }
            setSize(textSize);
            setLocation(new Point(topCenter - textSize.width / 2, rowOffset - rowSize
                + (getHeaderMargin() + textSize.height)));

            g.drawString(headerText, getLocation().x, getLocation().y);
        }
        if (null != footerText) {
            Dimension textSize = FigureUtilities.getTextExtents(footerText, getFont());
            setLocation(new Point(topCenter - textSize.width / 2, rowOffset - (getFooterMargin() + textSize.height)));
            setSize(textSize);
            g.drawString(footerText, getLocation().x, getLocation().y);
        }
    }
}
