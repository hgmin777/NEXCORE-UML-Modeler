/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.realization.sequencediagram.command;

import nexcore.tool.uml.ui.realization.sequencediagram.figure.LifeLineNameFigure;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Text;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.command</li>
 * <li>설 명 : LifeLineDirectEditCellEditorLocator</li>
 * <li>작성일 : 2009. 12. 24.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class LifeLineDirectEditCellEditorLocator implements CellEditorLocator {

    /** figure */
    private IFigure figure;

    /**
     * LifeLineDirectEditCellEditorLocator
     * @param f
     */
    public LifeLineDirectEditCellEditorLocator(IFigure f) {
        figure = f;
    }

    /**
     * @see org.eclipse.gef.tools.CellEditorLocator#relocate(org.eclipse.jface.viewers.CellEditor)
     */
    public void relocate(CellEditor celleditor) {
        Text text = (Text) celleditor.getControl();
        @SuppressWarnings("unused")
        Point pref = text.computeSize(-1, -1);

        Rectangle rectangle = new Rectangle(((LifeLineNameFigure) figure).getBounds());
        Rectangle cellLocation = new Rectangle(figure.getBounds().x,
            rectangle.y,
            figure.getSize().width,
            rectangle.height);

        figure.translateToAbsolute(cellLocation);
        text.setBounds(cellLocation.x, cellLocation.y, cellLocation.width, cellLocation.height);
    }
}
