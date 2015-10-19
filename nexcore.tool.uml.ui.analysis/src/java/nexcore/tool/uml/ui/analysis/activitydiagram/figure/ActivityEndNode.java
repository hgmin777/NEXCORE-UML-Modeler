/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.analysis.activitydiagram.figure;

import nexcore.tool.uml.ui.core.diagram.figure.InitialNode;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.analysis</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.analysis.activitydiagram.figure</li>
 * <li>설 명 : ActivityEndNode</li>
 * <li>작성일 : 2009-12-21</li>
 * <li>작성자 : Bojun</li>
 * </ul>
 */
public class ActivityEndNode extends Ellipse {
    /**
     * initialNode
     */
    private InitialNode initialNode;

    /**
     * ActivityEndNode
     */
    public ActivityEndNode() {

        initialNode = new InitialNode();
        initialNode.setLocation(new Point(getLocation().x + 5, getLocation().y + 5));
        initialNode.setBackgroundColor(ColorConstants.black);
        add(initialNode);

    }

    /**
     * @see org.eclipse.draw2d.Figure#setSize(int, int)
     */
    @Override
    public void setSize(int w, int h) {
        initialNode.setSize(w * 3 / 4, h * 3 / 4);
        initialNode.setLocation(new Point(getLocation().x + w / 7, getLocation().y + h / 7));
        super.setSize(w, h);
    }

    /**
     * setColor
     *  
     * @param color void
     */
    public void setColor(Color color) {
        initialNode.setBackgroundColor(color);
    }

}
