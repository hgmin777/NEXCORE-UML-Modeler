/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.core.diagram.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.figure</li>
 * <li>설  명 : ActorCompartmentFigure</li>
 * <li>작성일 : 2009. 11. 13.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class ActorCompartmentFigure extends Figure {

    /** actorFigure */
    private StickMan stickMan;

    /**
     * isDangling
     */
    private boolean isDangling = false;

    /**
     * ActorCompartmentFigure
     * 
     * @param actorName
     */
    public ActorCompartmentFigure(String actorName) {
        this(actorName, true);
    }

    /**
     * ActorCompartmentFigure
     */
    public ActorCompartmentFigure() {
        this("", true);
    }

    /**
     * ActorCompartmentFigure
     * 
     * @param isDangling
     */
    public ActorCompartmentFigure(boolean isDangling) {
        this("", isDangling);
    }

    /**
     * ActorCompartmentFigure
     * 
     * @param string
     * @param isDangling
     */
    public ActorCompartmentFigure(String string, boolean isDangling) {
        this.isDangling = isDangling;

        stickMan = new StickMan();
        stickMan.setSize(getSize());
        stickMan.setLocation(new Point(0, 0));
        // setBorder(new LineBorder(ColorConstants.black));

        add(stickMan);
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

        if (isDangling) {
            graphics.setForegroundColor(ColorConstants.red);
            graphics.drawOval(5, 5, 12, 12);
            graphics.drawLine(7, 7, 5 + 10, 5 + 10);
            graphics.drawLine(7 + 8, 7, 7, 7 + 8);
        }

        super.paintFigure(graphics);
    }

    /**
     * @see org.eclipse.draw2d.Figure#useLocalCoordinates()
     */
    @Override
    protected boolean useLocalCoordinates() {
        return true;
    }

    /**
     * @see org.eclipse.draw2d.Figure#setSize(int, int)
     */
    @Override
    public void setSize(int width, int height) {
        stickMan.setSize(width, height);
        super.setSize(width, height);
    }

    /**
     * setColor
     * 
     * @param bg
     *            void
     */
    public void setColor(Color bg) {
        stickMan.setForegroundColor(bg);
        stickMan.setBackgroundColor(bg);
    }

}
