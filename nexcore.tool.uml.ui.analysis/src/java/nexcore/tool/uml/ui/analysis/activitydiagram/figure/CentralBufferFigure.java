/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.analysis.activitydiagram.figure;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.diagram.figure.AbstractNotationNodeFigure;
import nexcore.tool.uml.ui.core.registry.IConstantColorRegistry;
import nexcore.tool.uml.ui.core.registry.IConstantImageRegistry;

import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.swt.graphics.Image;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.analysis</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.analysis.activitydiagram.figure</li>
 * <li>설 명 : OpaqueAction</li>
 * <li>작성일 : 2009. 12. 1.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class CentralBufferFigure extends AbstractNotationNodeFigure {

    /** stereoTypelabel */
    // private Label stereoTypelabel;
    /** nameLabel */
    // private Label nameLabel;

    /** width */
    // private static int width = 200;
    /** height */
    // private static int height = 100;

    /**
     * @param stereotype
     * @param interfaceName
     * @param image
     */
    public CentralBufferFigure(String name, Image image) {
        //
        ToolbarLayout layout = new ToolbarLayout();
        setLayoutManager(layout);
        setBorder(new LineBorder(UiCorePlugin.getDefault().getColor(IConstantColorRegistry.Black)));
        setBackgroundColor(FigureUtil.getFigureImage());
        setOpaque(true);

    }

    /**
     * @param name
     */
    public CentralBufferFigure(String name) {
        this(name, IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_CENTRALBUFFERNODE));
    }

    /**
     * 
     */
    public CentralBufferFigure() {
        this(UMLMessage.LABEL_CENTRAL_BUFFER);
    }

}
