/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.core.diagram.figure;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.figure</li>
 * <li>설 명 : LabeledRoundedRectangle</li>
 * <li>작성일 : 2010. 1. 8.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class LabeledRoundedRectangle extends Figure {

    /** nameLabel */
    private Label nameLabel;

    /** stereoTypeLabel */
    private Label stereoTypeLabel;

    /**
     * LabeledRoundedRectangle
     */
    public LabeledRoundedRectangle() {

        GridLayout layout = new GridLayout(1, false);
        setLayoutManager(layout);

        nameLabel = new Label();
        stereoTypeLabel = new Label();

        // nameLabel.setBorder(new LineBorder(ColorConstants.black));
        // stereoTypeLabel.setBorder(new LineBorder(ColorConstants.black));

        add(stereoTypeLabel);
        add(nameLabel);
    }

    /**
     * setText
     *  
     * @param name
     * @param stereoType void
     */
    @SuppressWarnings("unchecked")
    public void setText(String name, String stereoType) {
        if (name == null || name == "") {
            if (getChildren().contains(nameLabel))
                getChildren().remove(nameLabel);
        } else {
            nameLabel.setText(name);
            if (!(getChildren().contains(nameLabel)))
                getChildren().add(nameLabel);
        }

        if (stereoType == null || stereoType == "") {
            if (getChildren().contains(stereoTypeLabel))
                getChildren().remove(stereoTypeLabel);
        } else {
            stereoTypeLabel.setText(stereoType);
            if (!(getChildren().contains(stereoTypeLabel)))
                getChildren().add(0, stereoTypeLabel);
        }
        reSize();
    }

    /**
     * reSize
     *   void
     */
    public void reSize() {
        // nameLabel.setSize(getSize().width, nameLabel.getSize().height);
        // stereoTypeLabel.setSize(getSize().width,
        // stereoTypeLabel.getSize().height);
        setSize(getSize().width, getChildren().size() * 23);
    }

    /**
     * setIcon
     *  
     * @param image void
     */
    public void setIcon(Image image) {
        stereoTypeLabel.setIcon(image);
    }

    /**
     * @see org.eclipse.draw2d.Figure#paintFigure(org.eclipse.draw2d.Graphics)
     */
    @Override
    protected void paintFigure(Graphics graphics) {
        graphics.setAntialias(SWT.ON);
        super.paintFigure(graphics);
    }
}
