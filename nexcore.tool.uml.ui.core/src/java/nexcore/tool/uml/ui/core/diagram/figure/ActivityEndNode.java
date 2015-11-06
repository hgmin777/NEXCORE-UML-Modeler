/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.core.diagram.figure;

import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.geometry.Point;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.figure</li>
 * <li>설 명 : ActivityEndNode</li>
 * <li>작성일 : 2009. 11. 26.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class ActivityEndNode extends Ellipse {

    /**
     * ActivityEndNode
     */
    public ActivityEndNode() {

        InitialNode initialNode = new InitialNode();
        initialNode.setLocation(new Point(getLocation().x + 5, getLocation().y + 5));
        add(initialNode);

        setSize(40, 40);
    }
}
