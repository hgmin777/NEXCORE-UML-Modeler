/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.core.diagram.figure;

import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.geometry.Insets;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.figure</li>
 * <li>설 명 : MarginBorders</li>
 * <li>작성일 : 2010. 1. 21.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class MarginBorders extends MarginBorder {

    /**
     * MarginBorders
     * @param top
     * @param left
     * @param bottom
     * @param right
     */
    public MarginBorders(int top, int left, int bottom, int right) {
        super(new Insets(top, left, bottom, right));
    }

    /**
     * MarginBorders
     */
    public MarginBorders() {
        this(0, 0, 3, 0);
    }
}
