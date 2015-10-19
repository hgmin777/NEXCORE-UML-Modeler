/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.realization.sequencediagram.model;

import nexcore.tool.uml.model.umldiagram.LifeLineNode;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.model</li>
 * <li>설 명 : LifeLineNameHeader</li>
 * <li>작성일 : 2010. 3. 12.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class LifeLineNameHeader {

    /** visible */
    private boolean visible;

    /** lifeLine */
    private LifeLineNode lifeLine;

    /**
     * LifeLineNameHeader
     * @param lifeLine
     */
    public LifeLineNameHeader(LifeLineNode lifeLine) {
        this.lifeLine = lifeLine;
    }

    /**
     * getLifeLine
     *  
     * @return LifeLineNode
     */
    public LifeLineNode getLifeLine() {
        return lifeLine;
    }

    /**
     * setLifeLine
     *  
     * @param lifeLine void
     */
    public void setLifeLine(LifeLineNode lifeLine) {
        this.lifeLine = lifeLine;
    }

    /**
     * isVisible
     *  
     * @return boolean
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * setVisible
     *  
     * @param visible void
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
