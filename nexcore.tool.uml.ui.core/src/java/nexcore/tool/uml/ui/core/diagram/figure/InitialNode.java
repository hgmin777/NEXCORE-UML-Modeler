/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.core.diagram.figure;

import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.registry.IConstantColorRegistry;

import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.Graphics;
import org.eclipse.swt.SWT;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.figure</li>
 * <li>설 명 : InitialNode</li>
 * <li>작성일 : 2009. 11. 24.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class InitialNode extends Ellipse {

    /**
     * InitialNode
     */
    public InitialNode() {
        setBackgroundColor(UiCorePlugin.getDefault().getColor(IConstantColorRegistry.AntiqueWhite));
        setSize(30, 30);
    }

    /**
     * @see org.eclipse.draw2d.Shape#paintFigure(org.eclipse.draw2d.Graphics)
     */
    @Override
    public void paintFigure(Graphics graphics) {
        graphics.setAntialias(SWT.ON);
        super.paintFigure(graphics);
    }
}
