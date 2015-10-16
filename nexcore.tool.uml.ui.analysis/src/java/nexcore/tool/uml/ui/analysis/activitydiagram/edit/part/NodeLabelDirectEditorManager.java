/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.analysis.activitydiagram.edit.part;

import nexcore.tool.uml.model.umldiagram.AbstractNode;

import org.eclipse.draw2d.Label;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.swt.widgets.Text;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.analysis</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.analysis.activitydiagram.edit.part</li>
 * <li>설 명 : NodeLabelDirectEditorManager</li>
 * <li>작성일 : 2011. 1. 31.</li>
 * <li>작성자 : zeortae</li>
 * </ul>
 */
public class NodeLabelDirectEditorManager extends DirectEditManager {

    /** Node */
    @SuppressWarnings("unused")
    private AbstractNode model;

    /**
     * @param source
     * @param editorType
     * @param locator
     */
    @SuppressWarnings("unchecked")
    public NodeLabelDirectEditorManager(GraphicalEditPart source, Class editorType, CellEditorLocator locator) {
        super(source, editorType, locator);
        model = (AbstractNode) source.getModel();
    }

    /**
     * @see org.eclipse.gef.tools.DirectEditManager#initCellEditor()
     */
    protected void initCellEditor() {
        getCellEditor().setValue(((Label) (getEditPart().getFigure())).getText());

        Text text = (Text) getCellEditor().getControl();
        text.selectAll();
    }

}
