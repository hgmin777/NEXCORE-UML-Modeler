/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.realization.sequencediagram.edit.part;

import nexcore.tool.uml.ui.realization.sequencediagram.editor.SequenceSelectionTool;

import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.tools.SelectEditPartTracker;
import org.eclipse.swt.events.MouseEvent;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.edit.part</li>
 * <li>설  명 : SequenceDelegatingDragEditPartsTracker</li>
 * <li>작성일 : 2011. 5. 23.</li>
 * <li>작성자 : zerotae</li>
 * </ul>
 */
public class SequenceDelegatingDragEditPartsTracker extends SequenceSelectionTool implements DragTracker {

    /**
     * The delegating editpart (the selectable)
     */
    private EditPart delegatingEditPart;

    /**
     * The delegate editpart (the dragable)
     */
    private EditPart delegateEditPart;

    /**
     * The initial mouse event upon dragging
     */
    private MouseEvent initialME;

    /**
     * Creates an instance of the delegating drag editparts tracker
     * 
     * @param delegatingEditPart
     *            the <code>EditPart</code> that the selection gets delegated to
     * @param delegateEditPart
     *            the <code>EditPart</code> that the drag gets delegated to.
     */
    public SequenceDelegatingDragEditPartsTracker(EditPart delegatingEditPart, EditPart delegateEditPart) {
        this.delegatingEditPart = delegatingEditPart;
        this.delegateEditPart = delegateEditPart;
    }

    /**
     * @see org.eclipse.gef.tools.SelectionTool#handleButtonDown(int)
     */
    protected boolean handleButtonDown(int button) {
        setDragTracker(new SelectEditPartTracker(delegatingEditPart));
        lockTargetEditPart(delegatingEditPart);
        return true;
    }

    /**
     * @see org.eclipse.gef.tools.SelectionTool#mouseDown(org.eclipse.swt.events.MouseEvent,
     *      org.eclipse.gef.EditPartViewer)
     */
    public void mouseDown(MouseEvent e, EditPartViewer viewer) {
        initialME = e;
        super.mouseDown(e, viewer);
    }

    /**
     * @see org.eclipse.gef.tools.AbstractTool#handleDragStarted()
     */
    protected boolean handleDragStarted() {
        DragTracker tracker = delegateEditPart.getDragTracker(getTargetRequest());
        if (tracker != null) {
            setDragTracker(tracker);
            lockTargetEditPart(delegateEditPart);
            tracker.mouseDown(initialME, getCurrentViewer());
        }
        return super.handleDragStarted();
    }

}
