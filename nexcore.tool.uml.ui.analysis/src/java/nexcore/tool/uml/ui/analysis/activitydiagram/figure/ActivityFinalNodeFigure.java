/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.analysis.activitydiagram.figure;

import nexcore.tool.uml.ui.core.diagram.figure.AbstractNotationNodeFigure;

import org.eclipse.swt.graphics.Color;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.analysis</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.analysis.activitydiagram.figure</li>
 * <li>설 명 : OpaqueAction</li>
 * <li>작성일 : 2009. 12. 1.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class ActivityFinalNodeFigure extends AbstractNotationNodeFigure {

    /** node */
    private ActivityEndNode node;

    /**
     * ActivityFinalNodeFigure
     */
    public ActivityFinalNodeFigure() {

        node = new ActivityEndNode();
        node.setBackgroundColor(FigureUtil.getFigureImage());
        add(node);
    }

    /**
     * @see org.eclipse.draw2d.Figure#setSize(int, int)
     */
    @Override
    public void setSize(int w, int h) {
        node.setSize(w, h);
        super.setSize(w, h);
    }

    /**
     * setColor
     *  
     * @param color void
     */
    public void setColor(Color color) {
        node.setColor(color);
    }
}
