/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.analysis.activitydiagram.command;

import java.util.List;

import nexcore.tool.uml.ui.analysis.activitydiagram.edit.part.ActivityPartitionEditPart;
import nexcore.tool.uml.ui.analysis.activitydiagram.figure.ActivityPartitionFigure;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editparts.LayerManager;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.swt.widgets.Text;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.analysis</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.analysis.activitydiagram.command</li>
 * <li>설  명 : ActivityPartitionDirectEditorManager</li>
 * <li>작성일 : 2009. 12. 7.</li>
 * <li>작성자 : 강경구 </li>
 * </ul>
 */
public class ActivityPartitionDirectEditorManager extends DirectEditManager {

    /** EMPTY_TEXT */
    private static String EMPTY_TEXT = "";//$NON-NLS-1$

    /** string */
    private String string = null;

    /**
     * @param source
     * @param editorType
     * @param locator
     */
    @SuppressWarnings("unchecked")
    public ActivityPartitionDirectEditorManager(GraphicalEditPart source, java.lang.Class editorType, CellEditorLocator locator,
    String stringValue) {
        super(source, editorType, locator);
    }

    /**
     * ActivityPartitionDirectEditorManager
     * @param source
     * @param editorType
     * @param locator
     */
    @SuppressWarnings("unchecked")
    public ActivityPartitionDirectEditorManager(GraphicalEditPart source, java.lang.Class editorType, CellEditorLocator locator) {
        this(source, editorType, locator, EMPTY_TEXT);
    }

    /**
     * @see org.eclipse.gef.tools.DirectEditManager#initCellEditor()
     */
    protected void initCellEditor() {

        string = null;

        if( getEditPart() instanceof ActivityPartitionEditPart ) {
            IFigure figure = ((ActivityPartitionEditPart)getEditPart()).getFigure();
            if( figure instanceof ActivityPartitionFigure ) {
                string = ((ActivityPartitionFigure)figure).getName();
            }
        }
        
        if (string != null) {
            getCellEditor().setValue(string);

            Text text = (Text) getCellEditor().getControl();
            text.selectAll();
        }
    }
    
    /**
     * @see org.eclipse.gef.tools.DirectEditManager#eraseFeedback()
     */
    @Override
    protected void eraseFeedback() {
//        super.eraseFeedback();
        
        List<Object> feedbackList = LayerManager.Helper.find(getEditPart()).getLayer(LayerConstants.FEEDBACK_LAYER).getChildren();
        if(null != feedbackList && !feedbackList.isEmpty()){
            super.eraseFeedback();
        }
    }
}
