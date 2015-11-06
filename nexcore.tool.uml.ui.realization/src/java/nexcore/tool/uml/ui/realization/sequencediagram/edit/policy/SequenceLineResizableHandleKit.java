/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.realization.sequencediagram.edit.policy;

import java.util.List;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Handle;
import org.eclipse.gef.handles.MoveHandle;
import org.eclipse.gef.handles.ResizableHandleKit;
import org.eclipse.swt.graphics.Cursor;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.edit.policy</li>
 * <li>설  명 : SequenceLineResizableHandleKit</li>
 * <li>작성일 : 2011. 5. 16.</li>
 * <li>작성자 : zerotae</li>
 * </ul>
 */
public class SequenceLineResizableHandleKit extends ResizableHandleKit {

    /**
     * Adds a single handle in the given direction to the given List.
     * 
     * @param part the owner GraphicalEditPart of the handle
     * @param handles the List to add the handle to
     * @param direction the integer constant from PositionConstants that refers to the handle
     * direction
     */
    public static void addHandle(GraphicalEditPart part, List handles, int direction) {
        handles.add(createHandle(part, direction)); 
    }

    /**
     * Adds a single handle in the given direction to the given List with the given
     * DragTracker
     * 
     * @param tracker the DragTracker to assign to this handle
     * @param part the owner GraphicalEditPart of the handle
     * @param handles the List to add the handle to
     * @param direction the integer constant from PositionConstants that refers to the handle
     * direction
     * @param cursor the Cursor to use when hovering over this handle
     */
    public static void addHandle(GraphicalEditPart part, List handles, int direction, 
                                 DragTracker tracker, Cursor cursor) {
        handles.add(createHandle(part, direction, tracker, cursor));    
    }
        
    /**
     * Fills the given List with handles at each corner
     * and the north, south, east, and west of the GraphicalEditPart.
     * @param part the owner GraphicalEditPart of the handles
     * @param handles the List to add the handles to
     */
    public static void addHandles(GraphicalEditPart part, List handles) {
        addMoveHandle(part, handles);
        handles.add(createHandle(part, PositionConstants.EAST));
        handles.add(createHandle(part, PositionConstants.SOUTH_EAST));
        handles.add(createHandle(part, PositionConstants.SOUTH));
        handles.add(createHandle(part, PositionConstants.SOUTH_WEST));
        handles.add(createHandle(part, PositionConstants.WEST));
        handles.add(createHandle(part, PositionConstants.NORTH_WEST));
        handles.add(createHandle(part, PositionConstants.NORTH));
        handles.add(createHandle(part, PositionConstants.NORTH_EAST));
    }

    /**
     * Fills the given List with move borders at each side of a
     * figure.
     * @param f the GraphicalEditPart thatis the owner of the handles
     * @param handles the List to add the handles to
     */
    public static void addMoveHandle(GraphicalEditPart f, List handles) {
        handles.add(moveHandle(f));
    }

    /**
     * Fills the given List with move borders with the given DragTracker at each side of a
     * figure.
     * @param tracker the DragTracker to assign to this handle
     * @param f the GraphicalEditPart thatis the owner of the handles
     * @param handles the List to add the handles to
     * @param cursor the Cursor to use when hovering over this handle
     */
    public static void addMoveHandle(GraphicalEditPart f, List handles, DragTracker tracker,
                                     Cursor cursor) {
        handles.add(moveHandle(f, tracker, cursor));
    }

    /**
     * createHandle
     *  
     * @param owner
     * @param direction
     * @return Handle
     */
    static Handle createHandle(GraphicalEditPart owner, int direction) {
        SequenceLineResizeHandle handle = new SequenceLineResizeHandle(owner, direction);
    //  handle.setDragTracker(new ResizeTracker(direction));
        return handle;
    }

    /**
     * createHandle
     *  
     * @param owner
     * @param direction
     * @param tracker
     * @param cursor
     * @return Handle
     */
    static Handle createHandle(GraphicalEditPart owner, int direction, DragTracker tracker,
                               Cursor cursor) {
        SequenceLineResizeHandle handle = new SequenceLineResizeHandle(owner, direction);
        handle.setDragTracker(tracker);
        handle.setCursor(cursor);
        return handle;
    }

    /**
     * Returns a new {@link MoveHandle} with the given owner.
     * @param owner the GraphicalEditPart that is the owner of the new MoveHandle 
     * @return the new MoveHandle
     */
    public static Handle moveHandle(GraphicalEditPart owner) {
        return new SequenceLineMoveHandle(owner);
    }

    /**
     * Returns a new {@link MoveHandle} with the given owner and DragTracker.
     * 
     * @param tracker the DragTracker to assign to this handle
     * @param owner the GraphicalEditPart that is the owner of the new MoveHandle 
     * @return the new MoveHandle
     * @param cursor the Cursor to use when hovering over this handle
     */
    public static Handle moveHandle(GraphicalEditPart owner, DragTracker tracker, 
                                    Cursor cursor) {
        SequenceLineMoveHandle moveHandle = new SequenceLineMoveHandle(owner);
        moveHandle.setDragTracker(tracker);
        moveHandle.setCursor(cursor);
        return moveHandle;      
    }
}
