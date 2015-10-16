/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.core.diagram.figure;

import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.geometry.PointList;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.figure</li>
 * <li>설 명 : RhombeArrowDecoration</li>
 * <li>작성일 : 2010. 1. 21.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class RhombeArrowDecoration extends PolygonDecoration {

    /**
     * RhombeArrowDecoration
     */
    public RhombeArrowDecoration() {
        PointList rhombeArrow = new PointList(new int[] { -2, 0, -1, -1, 0, 0, -1, 1, -2, 0, -3, 1, -2, 0, -3, -1, -2,
            0 });
        setTemplate(rhombeArrow);
        setScale(8, 4);
        setFill(true);
    }
}
