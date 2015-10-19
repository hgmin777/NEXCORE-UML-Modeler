/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.realization.sequencediagram.editor;

import nexcore.tool.uml.ui.core.diagram.edit.part.AbstractDiagramConnectionEditPart;
import nexcore.tool.uml.ui.core.diagram.editor.palette.NewSelectionTool;
import nexcore.tool.uml.ui.core.diagram.figure.FigureConstant;
import nexcore.tool.uml.ui.realization.sequencediagram.edit.part.LifeLineEditPart;
import nexcore.tool.uml.ui.realization.sequencediagram.edit.part.LineEditPart;
import nexcore.tool.uml.ui.realization.sequencediagram.edit.part.MessageEditPart;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.EditPartViewer.Conditional;
import org.eclipse.swt.events.MouseEvent;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.editor</li>
 * <li>설  명 : SequenceSelectionTool</li>
 * <li>작성일 : 2011. 5. 23.</li>
 * <li>작성자 : zerotae</li>
 * </ul>
 */
public class SequenceSelectionTool extends NewSelectionTool {

    /**
     * @see org.eclipse.gef.tools.SelectionTool#getTargetingConditional()
     */
    @Override
    protected Conditional getTargetingConditional() {
        return new EditPartViewer.Conditional() {
            public boolean evaluate(EditPart editpart) {
                if(editpart instanceof LifeLineEditPart) {
                    if(getLocation().y >= FigureConstant.FIGURE_LIFELINE_TOP_MARGIN + FigureConstant.FIGURE_LIFELINE_HEAD_HEIGHT) {
                        Rectangle bounds = ((LifeLineEditPart) editpart).getFigure().getBounds();
                        if(bounds.x + bounds.width/2 - LineEditPart.LINE_SELECTABLE_WIDTH/2 <= getLocation().x
                            && bounds.x + bounds.width/2 + LineEditPart.LINE_SELECTABLE_WIDTH/2 >= getLocation().x) {
                            return editpart.isSelectable();  
                        } else {
                            return false;                            
                        }
                    } else {
                        return editpart.isSelectable();                                   
                    }         
                } else {
                    return editpart.isSelectable();
                }
            }
        };
    }
   
}
