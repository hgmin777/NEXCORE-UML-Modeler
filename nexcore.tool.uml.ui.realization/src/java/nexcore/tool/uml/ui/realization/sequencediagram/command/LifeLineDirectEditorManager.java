/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.realization.sequencediagram.command;

import nexcore.tool.uml.model.umldiagram.NotationNode;

import org.eclipse.draw2d.Label;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.uml2.uml.Lifeline;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.command</li>
 * <li>설 명 : LifeLineDirectEditorManager</li>
 * <li>작성일 : 2009. 12. 24.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class LifeLineDirectEditorManager extends DirectEditManager {

    /** notationNode */
    @SuppressWarnings("unused")
    private NotationNode model;

    /**
     * @param source
     * @param editorType
     * @param locator
     */
    @SuppressWarnings("unchecked")
    public LifeLineDirectEditorManager(GraphicalEditPart source, Class editorType, CellEditorLocator locator) {
        super(source, editorType, locator);
        model = (NotationNode) source.getModel();

    }

    /**
     * @see org.eclipse.gef.tools.DirectEditManager#initCellEditor()
     */
    protected void initCellEditor() {

        String string = "";
        if (getEditPart().getModel() instanceof NotationNode) {
            string = ((Lifeline) ((NotationNode) getEditPart().getModel()).getUmlModel()).getName();
        } else {
            string = ((Label) (getEditPart().getFigure())).getText();
        }
        string = string.replaceAll("<<", "");
        string = string.replaceAll(">>", "");

        getCellEditor().setValue(string);

        Text text = (Text) getCellEditor().getControl();
        text.selectAll();
    }
}
