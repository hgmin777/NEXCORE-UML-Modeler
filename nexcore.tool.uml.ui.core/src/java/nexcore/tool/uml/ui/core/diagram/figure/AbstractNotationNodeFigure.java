/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.core.diagram.figure;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.figure</li>
 * <li>설 명 : AbstractNotationNodeFigure</li>
 * <li>작성일 : 2009. 12. 4.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public abstract class AbstractNotationNodeFigure extends Figure implements IUMLFigure {

    /** label */
    protected Label nameLabel = null;

    /**
     * AbstractNotationNodeFigure
     */
    public AbstractNotationNodeFigure() {
    }

    public boolean isDangling = true;
    
    public boolean isDangling() {
        return isDangling;
    }

    public void setDangling(boolean isDangling) {
        this.isDangling = isDangling;
    }
    
    public void refresh() {
        layout();
    }
    
    /**
     * @see org.eclipse.draw2d.Figure#paintFigure(org.eclipse.draw2d.Graphics)
     */
    @Override
    protected void paintFigure(Graphics graphics) {
        graphics.setAntialias(SWT.ON);
        super.paintFigure(graphics);
    }

    /**
     * getNameLabelLocation
     *  
     * @return Rectangle
     */
    public Rectangle getNameLabelLocation() {
        return this.nameLabel.getBounds();
    }

    /**
     * getName
     *  
     * @return String
     */
    public String getName() {
        return this.nameLabel.getText();
    }

    /**
     * setName
     *  
     * @param text void
     */
    public void setName(String text) {
        if (null == this.nameLabel) {
            this.nameLabel = new Label(text);
            nameLabel.setTextAlignment(PositionConstants.CENTER);
        }
        this.nameLabel.setText(text);

    }

    /**
     * getNameLabel
     *  
     * @return Label
     */
    public Label getNameLabel() {
        return this.nameLabel;
    }
}
