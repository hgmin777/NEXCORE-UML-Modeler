/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.analysis.activitydiagram.figure;

import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.diagram.figure.TitleBarBorder;
import nexcore.tool.uml.ui.core.registry.IConstantColorRegistry;

import org.eclipse.draw2d.Figure;
import org.eclipse.swt.graphics.Color;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.analysis</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.analysis.activitydiagram.figure</li>
 * <li>설 명 : ActivityPartitionFigure</li>
 * <li>작성일 : 2011. 1. 25.</li>
 * <li>작성자 : zerotae</li>
 * </ul>
 */
public class ActivityPartitionFigure extends Figure {

    /** 다이어그램 바탕 색상 */
    public static Color classColor = UiCorePlugin.getDefault().getColor(IConstantColorRegistry.LightGoldenrod);

    /**
     * @param label
     * @param horizontal
     * @param textAlign
     */
    public ActivityPartitionFigure(String label, boolean horizontal, int textAlign) {
        TitleBarBorder border = new TitleBarBorder(label, horizontal, textAlign);
        setBorder(border);
    }

    /**
     *  
     * @return TitleBarBorder
     */
    public TitleBarBorder getTitleBarBorder() {
        return (TitleBarBorder) getBorder();
    }

    /**
     *  
     * @return String
     */
    public String getName() {
        return getTitleBarBorder().getLabel();
    }

    /**
     *  
     * @param name void
     */
    public void setName(String name) {
        getTitleBarBorder().setLabel(name);
    }

}
