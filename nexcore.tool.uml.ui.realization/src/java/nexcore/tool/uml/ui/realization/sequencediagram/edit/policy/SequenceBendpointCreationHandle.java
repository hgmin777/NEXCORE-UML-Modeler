/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.realization.sequencediagram.edit.policy;

import org.eclipse.draw2d.Locator;
import org.eclipse.draw2d.MidpointLocator;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.SharedCursors;
import org.eclipse.gef.handles.BendpointHandle;
import org.eclipse.gef.tools.ConnectionBendpointTracker;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.edit.policy</li>
 * <li>설  명 : SequenceBendpointCreationHandle</li>
 * <li>작성일 : 2011. 6. 9.</li>
 * <li>작성자 : zerotae</li>
 * </ul>
 */
public class SequenceBendpointCreationHandle extends BendpointHandle {

    {
        //북남방향 커서를 설정한다.
        setCursor(SharedCursors.SIZENS);
        setPreferredSize(new Dimension(DEFAULT_HANDLE_SIZE - 2, DEFAULT_HANDLE_SIZE - 2));
    }

    /**
     * Creates a new BendpointCreationHandle.
     */
    public SequenceBendpointCreationHandle() { }

    /**
     * Creates a new BendpointCreationHandle, sets its owner to <code>owner</code>
     * and its index to <code>index</code>, and sets its locator to a new
     * {@link MidpointLocator}.
     * @param owner the ConnectionEditPart owner
     * @param index the index
     */
    public SequenceBendpointCreationHandle(ConnectionEditPart owner, int index) {
        setOwner(owner);
        setIndex(index);
        setLocator(new MidpointLocator(getConnection(), index));
    }

    /**
     * Creates a new BendpointCreationHandle, sets its owner to <code>owner</code>
     * and its index to <code>index</code>, and sets its locator to a new
     * {@link MidpointLocator} with the given <code>locatorIndex</code>.
     * @param owner the ConnectionEditPart owner
     * @param index the index
     * @param locatorIndex the locator index
     */
    public SequenceBendpointCreationHandle(ConnectionEditPart owner, int index, int locatorIndex) {
        setOwner(owner);
        setIndex(index);
        setLocator(new MidpointLocator(getConnection(), locatorIndex));
    }

    /**
     * Creates a new BendpointCreationHandle and sets its owner to 
     * <code>owner</code>, sets its index to <code>index</code>, and
     * sets its locator to <code>locator</code>.
     * @param owner the ConnectionEditPart owner
     * @param index the index
     * @param locator the Locator
     */
    public SequenceBendpointCreationHandle(ConnectionEditPart owner, int index, Locator locator) {
        setOwner(owner);
        setIndex(index);
        setLocator(locator);
    }

    /**
     * Creates and returns a new {@link ConnectionBendpointTracker}.
     * @return the new ConnectionBendpointTracker
     */
    protected DragTracker createDragTracker() {
        ConnectionBendpointTracker tracker;
        tracker = new ConnectionBendpointTracker(
            (ConnectionEditPart)getOwner(),
            getIndex());
        tracker.setType(RequestConstants.REQ_CREATE_BENDPOINT);
        tracker.setDefaultCursor(getCursor());
        //DisabledCursor를 설정해준다.
        tracker.setDisabledCursor(SharedCursors.NO);
        return tracker;
    }
}
