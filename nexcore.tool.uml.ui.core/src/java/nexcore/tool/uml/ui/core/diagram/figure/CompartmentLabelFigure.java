/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.core.diagram.figure;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.ToolbarLayout;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.figure</li>
 * <li>설 명 : CompartmentLabelFigure</li>
 * <li>작성일 : 2009. 12. 7.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class CompartmentLabelFigure extends AbstractNotationNodeFigure {

    /**
     * CompartmentLabelFigure
     */
    public CompartmentLabelFigure() {
        ToolbarLayout layout = new ToolbarLayout();
        setLayoutManager(layout);
        setOpaque(true);
    }

    /**
     * @see org.eclipse.draw2d.Figure#add(org.eclipse.draw2d.IFigure, java.lang.Object, int)
     */
    @Override
    public void add(IFigure figure, Object constraint, int index) {
        if (((Label) figure).getTextAlignment() == PositionConstants.CENTER) {
            super.add(figure, constraint, 0);
        } else {
            super.add(figure, constraint, index);
        }
    }
}
