/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.realization.sequencediagram.figure;

import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.registry.IConstantColorRegistry;

import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.ToolbarLayout;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.figure</li>
 * <li>설 명 : LifeLineNameHeaderFigure</li>
 * <li>작성일 : 2009. 12. 17.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class LifeLineNameHeaderFigure extends LifeLineNameFigure {

    // /** label */
    // private Label label;

    /**
     * LifeLineNameHeaderFigure
     * @param name
     */
    public LifeLineNameHeaderFigure(String name) {
        super();
        this.stereptypeLabel = new Label("");
        this.add(this.stereptypeLabel);

        nameLabel = new CollapsedLabel(name);
        this.nameLabel2 = new Label("");
        this.nameLabel.setChildLabel(this.nameLabel2);
        // nameLabel.setIcon(IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_LIFELINE));

        ToolbarLayout layout = new ToolbarLayout();
        layout.setSpacing(2);
        layout.setMinorAlignment(ToolbarLayout.ALIGN_CENTER);
        setLayoutManager(layout);

        setOpaque(true);
        setBackgroundColor(UiCorePlugin.getDefault().getColor(IConstantColorRegistry.White));
        setBorder(new MarginBorder(6));
        add(nameLabel);
        this.add(this.nameLabel2);

    }

    // public String getName() {
    // return label.getText();
    // }
    //
    // public void setName(String name) {
    // label.setText(name);
    // }
    //
    // public Label getLabel() {
    // return label;
    // }

}
