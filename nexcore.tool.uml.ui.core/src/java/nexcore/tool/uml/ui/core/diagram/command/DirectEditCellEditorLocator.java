/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.core.diagram.command;

import nexcore.tool.uml.ui.core.diagram.figure.NoteFigure;
import nexcore.tool.uml.ui.core.diagram.figure.TextFigure;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Text;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.command</li>
 * <li>설 명 : DirectEditCellEditorLocator</li>
 * <li>작성일 : 2009. 12. 7.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class DirectEditCellEditorLocator implements CellEditorLocator {

    /** figure */
    private IFigure figure;

    /**
     * DirectEditCellEditorLocator
     * @param f
     */
    public DirectEditCellEditorLocator(IFigure f) {
        figure = f;
    }

    /**
     * @see org.eclipse.gef.tools.CellEditorLocator#relocate(org.eclipse.jface.viewers.CellEditor)
     */
    public void relocate(CellEditor celleditor) {
        Text text = (Text) celleditor.getControl();

        if (figure instanceof Label) {

            Rectangle rectangle = new Rectangle(((Label) figure).getBounds());
            Rectangle cellLocation = new Rectangle(figure.getBounds().x,
                rectangle.y,
                figure.getSize().width,
                rectangle.height);

            figure.translateToAbsolute(cellLocation);
            text.setBounds(cellLocation.x, cellLocation.y, cellLocation.width, cellLocation.height);
        } else if (figure instanceof NoteFigure) {
            Rectangle rectangle = new Rectangle(((NoteFigure) figure).getBounds());
            Rectangle cellLocation = new Rectangle(figure.getBounds().x,
                rectangle.y,
                figure.getSize().width,
                rectangle.height);

            figure.translateToAbsolute(cellLocation);
            text.setBounds(cellLocation.x, cellLocation.y, cellLocation.width, cellLocation.height);
        } else if (figure instanceof TextFigure) {
            Rectangle rectangle = new Rectangle(((TextFigure) figure).getBounds());
            Rectangle cellLocation = new Rectangle(figure.getBounds().x,
                rectangle.y,
                figure.getSize().width,
                rectangle.height);

            figure.translateToAbsolute(cellLocation);
            text.setBounds(cellLocation.x, cellLocation.y, cellLocation.width, cellLocation.height);
        }
    }
    
    /**
     * getFigure
     *  
     * @return IFigure
     */
    public IFigure getFigure() {
        return figure;
    }
}
