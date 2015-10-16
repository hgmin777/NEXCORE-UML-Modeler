/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.core.diagram.figure;

import java.util.List;

import org.eclipse.draw2d.Border;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.figure</li>
 * <li>설  명 : ActorFigure</li>
 * <li>작성일 :2009. 11. 27.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class ActorFigure extends AbstractNotationNodeFigure {

    /** actorFigure */
    private ActorCompartmentFigure actorFigure;

    /**
     * ActorFigure
     */
    public ActorFigure() {
        this(true);
    }

    /**
     * ActorFigure
     * 
     * @param isDangling
     */
    public ActorFigure(boolean isDangling) {
        setDangling(isDangling);

        setOpaque(true);
        actorFigure = new ActorCompartmentFigure(isDangling);
        actorFigure.setLocation(new Point(0, 0));
        actorFigure.setSize(getSize());
        add(actorFigure);
    }

    @Override
    public void setDangling(boolean isDangling) {
        if (actorFigure != null) {
            actorFigure.setDangling(isDangling);
        }

    }

    @Override
    public void refresh() {
        if (actorFigure != null) {
            actorFigure.refresh();
        }
    }

    /**
     * @see org.eclipse.draw2d.Figure#useLocalCoordinates()
     */
    @Override
    protected boolean useLocalCoordinates() {
        return true;
    }

    /**
     * @see org.eclipse.draw2d.Figure#add(org.eclipse.draw2d.IFigure,
     *      java.lang.Object, int)
     */
    @Override
    public void add(IFigure figure, Object constraint, int index) {
        if (figure instanceof ActorCompartmentFigure) {
            super.add(figure, constraint, 0);
        } else {
            super.add(figure, constraint, 1);
        }
    }

    /**
     * @see org.eclipse.draw2d.Figure#setSize(int, int)
     */
    @SuppressWarnings("unchecked")
    @Override
    public void setSize(int w, int h) {
        super.setSize(w, h);

        int actorFigureHeight = 0;
        List<IFigure> iFigures = getChildren();
        for (int i = 0; i < iFigures.size(); i++) {
            if (!(iFigures.get(i) instanceof ActorCompartmentFigure)) {
                if (iFigures.get(i).getBounds().height == 0) {
                    actorFigureHeight += 23;
                }
                actorFigureHeight += iFigures.get(i).getBounds().height + 5;
            }
        }

        if (actorFigureHeight == 0) {
            actorFigure.setSize(w, h - (getChildren().size() * 23 + 5));
        } else {
            actorFigure.setSize(w, h - actorFigureHeight);
        }

    }

    /**
     * setColor
     * 
     * @param bg
     *            void
     */
    public void setColor(Color bg) {
        actorFigure.setColor(bg);
    }

    /**
     * @see org.eclipse.draw2d.Figure#setBorder(org.eclipse.draw2d.Border)
     */
    @Override
    public void setBorder(Border border) {
    }

    /**
     * @see org.eclipse.draw2d.Figure#setBackgroundColor(org.eclipse.swt.graphics.Color)
     */
    @Override
    public void setBackgroundColor(Color bg) {
    }

    // @Override
    // public void setLocation(Point p) {
    // super.setLocation(p);
    //
    // List<IFigure> iFigures = getChildren();
    // for(int i = 0; i < iFigures.size(); i++){
    // if(!(iFigures.get(i) instanceof StickMan)){
    // iFigures.get(i).setLocation(new Point(iFigures.get(i).getBounds().x,
    // iFigures.get(i - 1).getBounds().y + iFigures.get(i -
    // 1).getBounds().height));
    // }
    // }
    // }
}
