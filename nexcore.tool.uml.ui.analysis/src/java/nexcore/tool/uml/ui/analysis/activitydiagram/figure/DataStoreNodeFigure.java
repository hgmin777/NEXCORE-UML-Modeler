/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.analysis.activitydiagram.figure;

import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.diagram.figure.AbstractNotationNodeFigure;
import nexcore.tool.uml.ui.core.registry.IConstantColorRegistry;

import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.ToolbarLayout;
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
public class DataStoreNodeFigure extends AbstractNotationNodeFigure {

    /** 다이어그램 바탕 색상 */
    public static Color classColor = UiCorePlugin.getDefault().getColor(IConstantColorRegistry.LightGoldenrod);

    /**
     * @param stereotype
     * @param interfaceName
     * @param image
     */
    public DataStoreNodeFigure() {

        ToolbarLayout layout = new ToolbarLayout();
        setLayoutManager(layout);
        setBorder(new LineBorder(UiCorePlugin.getDefault().getColor(IConstantColorRegistry.Black)));
        setBackgroundColor(FigureUtil.getFigureImage());
        setOpaque(true);
    }

}
