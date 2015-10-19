/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.core.diagram.figure;

import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.geometry.PointList;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.figure</li>
 * <li>설 명 : ArrowDecoration</li>
 * <li>작성일 : 2010. 1. 21.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class ArrowDecoration extends PolylineDecoration {

    /**
     * ArrowDecoration
     */
    public ArrowDecoration() {
        PointList p = new PointList();
        p.addPoint(0, 0);
        p.addPoint(-2, -2);
        p.addPoint(-2, 2);
        setPoints(p);
        setScale(10, 5);
    }
}
