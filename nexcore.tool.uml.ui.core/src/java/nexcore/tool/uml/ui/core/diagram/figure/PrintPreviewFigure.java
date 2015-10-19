/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.core.diagram.figure;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Font;
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.figure</li>
 * <li>설 명 : PrintPreviewFigure</li>
 * <li>작성일 : 2011. 10. 07.</li>
 * <li>작성자 : 강석찬</li>
 * </ul>
 */
public class PrintPreviewFigure extends Figure {

    /**
     * rows
     */
    private int rows = 1;
    /**
     * cols
     */
    private int cols = 1;

    /**
     * recList
     */
    private List recList;

    /**
     * PrintPreviewFigure
     */
    public PrintPreviewFigure() {
        super();
        recList = new ArrayList();
    }

    /**
     * PrintPreviewFigure 바깥 테두리 선의 두께를 반환한다
     * 
     * @int
     */
    private int getBorderLineWidth() {
        return 3;
    }

    /**
     * PrintPreviewFigure 안쪽 선들의 두께를 반환한다.
     * 
     * @return
     */
    private int getLineWidth() {
        return 3;
    }

    /**
     * PrintPreviewFigure에 표시될 프린트 용지의 갯수를 열,행으로 설정한다.
     * 
     * @return
     */
    public void setPageCount(int rows, int cols) {
        this.rows = Math.max(1, rows);
        this.cols = Math.max(1, cols);
    }

    /**
     * @see org.eclipse.draw2d.Figure#paintFigure(org.eclipse.draw2d.Graphics)
     */
    public void paintFigure(Graphics g) {

        super.paintFigure(g);
        recList.clear();
        Rectangle r = getBounds();

        g.setForegroundColor(ColorConstants.blue);
        g.setBackgroundColor(ColorConstants.blue);

        // Draw the border
        final int borderLineWidth = getBorderLineWidth();
        Rectangle top = new Rectangle(r.x, r.y, r.width, borderLineWidth);
        recList.add(top);

        Rectangle right = new Rectangle(r.x + r.width - borderLineWidth, r.y, borderLineWidth + 1, r.height);
        recList.add(right);

        Rectangle left = new Rectangle(r.x, r.y, borderLineWidth, r.height);
        recList.add(left);

        Rectangle bottom = new Rectangle(r.x, r.y + r.height - borderLineWidth, r.width, borderLineWidth + 1);
        recList.add(bottom);

        for (int i = 0; i < recList.size(); i++) {
            g.fillRectangle((Rectangle) recList.get(i));
        }

        Font f = getFont();
        // Draw the internal page division lines
        g.setLineWidth(getLineWidth());
        Point location = getLocation();

        int colSize = (int) Math.floor(r.width / cols);
        for (int i = 1; i < cols; i++) {
            int colsOffset = colSize * i;
            Rectangle rec = new Rectangle(location.x + colsOffset, location.y, getLineWidth(), r.height);
            recList.add(rec);
            g.fillRectangle(rec);
        }

        int rowSize = (int) Math.floor(r.height / rows);
        for (int i = 1; i < rows; i++) {
            int rowOffset = rowSize * i;
            Rectangle rec = new Rectangle(location.x, location.y + rowOffset, r.width, getLineWidth());
            recList.add(rec);
            g.fillRectangle(rec);
        }
    }
}
