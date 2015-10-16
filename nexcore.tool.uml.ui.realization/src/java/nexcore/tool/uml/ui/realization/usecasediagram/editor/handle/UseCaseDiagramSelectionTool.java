/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.realization.usecasediagram.editor.handle;

import nexcore.tool.uml.ui.core.diagram.editor.palette.NewSelectionTool;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.swt.events.MouseEvent;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.usecasediagram.editor.handle</li>
 * <li>설 명 : UseCaseDiagramSelectionTool</li>
 * <li>작성일 : 2010. 1. 19.</li>
 * <li>작성자 : 한승일</li>
 * </ul>
 */
public class UseCaseDiagramSelectionTool extends NewSelectionTool {

    /**
     * @see org.eclipse.gef.tools.SelectionTool#mouseDrag(org.eclipse.swt.events.MouseEvent,
     *      org.eclipse.gef.EditPartViewer)
     */
    @Override
    public void mouseDrag(MouseEvent e, EditPartViewer viewer) {
        // System.out.println(this.getTargetEditPart());
        // DragTracker dragTacker = this.getDragTracker();
        // if(getTargetEditPart() instanceof UseCaseEditPart){
        // int i=0;
        // }
        // if (dragTacker instanceof ConnectionEndpointTracker) {
        // ConnectionEndpointTracker c = (ConnectionEndpointTracker)dragTacker;
        // Object obj =
        // ((StructuredSelection)this.getCurrentViewer().getSelection()).getFirstElement();
        // Command command = getTargetEditPart().getCommand(getTargetRequest());
        // if (!(command instanceof UnexecutableCommand)) {
        // int i = 0;
        // }
        // }
        super.mouseDrag(e, viewer);
    }

    /**
     * @see org.eclipse.gef.tools.SelectionTool#handleButtonDown(int)
     */
    @Override
    protected boolean handleButtonDown(int button) {
        boolean result = super.handleButtonDown(button);
        this.getTargetEditPart();
        // this.setDragTracker(newDragTracker)
        return result;
    }

}
