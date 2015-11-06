/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.realization.sequencediagram.edit.policy;

import java.util.ArrayList;
import java.util.List;

import nexcore.tool.uml.ui.realization.sequencediagram.edit.part.LifeLineNameEditPart;
import nexcore.tool.uml.ui.realization.sequencediagram.edit.part.LineEditPart;

import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.SharedCursors;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.tools.SelectEditPartTracker;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.edit.policy</li>
 * <li>설  명 : SequenceLineResizableEditPolicy</li>
 * <li>작성일 : 2011. 5. 3.</li>
 * <li>작성자 : zerotae </li>
 * </ul>
 */
public class SequenceLineResizableEditPolicy extends NonResizableEditPolicy {

    /**
     * @see org.eclipse.gef.editpolicies.NonResizableEditPolicy#createSelectionHandles()
     */
    @Override
    protected List createSelectionHandles() {
        
        List list = new ArrayList();
        if (isDragAllowed())
            SequenceLineNonResizableHandleKit.addHandles((GraphicalEditPart)getHost(), list);
        else
            SequenceLineNonResizableHandleKit.addHandles((GraphicalEditPart)getHost(), list, 
                    new SelectEditPartTracker(getHost()), SharedCursors.ARROW);
        return list;
    }
    
    /**
     * @see org.eclipse.gef.editpolicies.ResizableEditPolicy#getResizeCommand(org.eclipse.gef.requests.ChangeBoundsRequest)
     */
    protected Command getResizeCommand(ChangeBoundsRequest request) {
        
        ChangeBoundsRequest req = new ChangeBoundsRequest(REQ_RESIZE_CHILDREN);
        req.setEditParts(getHost().getParent());
        
        if(getHost() instanceof LifeLineNameEditPart && request.getSizeDelta().height > 0) {
            request.getSizeDelta().height = 0;
        }
        
        if(getHost() instanceof LineEditPart && request.getSizeDelta().width > 0) {
            request.getSizeDelta().width = 0;
        }
        
        req.setMoveDelta(request.getMoveDelta());
        req.setSizeDelta(request.getSizeDelta());
        req.setLocation(request.getLocation());
        req.setExtendedData(request.getExtendedData());
        req.setResizeDirection(request.getResizeDirection());
        return getHost().getParent().getParent().getCommand(req);
        
    }
        
//    private int directions = -1;
//    
//    /**
//     * @see org.eclipse.gef.editpolicies.SelectionHandlesEditPolicy#createSelectionHandles()
//     */
//    protected List createSelectionHandles() {
//        List list = new ArrayList();
//        
//        if (directions == 0)
//            SequenceLineNonResizableHandleKit.addHandles((GraphicalEditPart)getHost(), list);
//        else if (directions != -1) {
//            SequenceLineResizableHandleKit.addMoveHandle((GraphicalEditPart)getHost(), list);
//            if ((directions & PositionConstants.EAST) != 0)
//                SequenceLineResizableHandleKit.addHandle((GraphicalEditPart)getHost(), list, 
//                        PositionConstants.EAST);
//            else
//                SequenceLineNonResizableHandleKit.addHandle((GraphicalEditPart)getHost(), list, 
//                        PositionConstants.EAST);
//            if ((directions & PositionConstants.SOUTH_EAST) == PositionConstants.SOUTH_EAST)
//                SequenceLineResizableHandleKit.addHandle((GraphicalEditPart)getHost(), list, 
//                        PositionConstants.SOUTH_EAST);
//            else
//                SequenceLineNonResizableHandleKit.addHandle((GraphicalEditPart)getHost(), list,
//                        PositionConstants.SOUTH_EAST);
//            if ((directions & PositionConstants.SOUTH) != 0)
//                SequenceLineResizableHandleKit.addHandle((GraphicalEditPart)getHost(), list, 
//                        PositionConstants.SOUTH);
//            else
//                SequenceLineNonResizableHandleKit.addHandle((GraphicalEditPart)getHost(), list, 
//                        PositionConstants.SOUTH);
//            if ((directions & PositionConstants.SOUTH_WEST) == PositionConstants.SOUTH_WEST)
//                SequenceLineResizableHandleKit.addHandle((GraphicalEditPart)getHost(), list, 
//                        PositionConstants.SOUTH_WEST);
//            else
//                SequenceLineNonResizableHandleKit.addHandle((GraphicalEditPart)getHost(), list, 
//                            PositionConstants.SOUTH_WEST);
//            if ((directions & PositionConstants.WEST) != 0)
//                SequenceLineResizableHandleKit.addHandle((GraphicalEditPart)getHost(), list, 
//                        PositionConstants.WEST);
//            else
//                SequenceLineNonResizableHandleKit.addHandle((GraphicalEditPart)getHost(), list, 
//                            PositionConstants.WEST);
//            if ((directions & PositionConstants.NORTH_WEST) == PositionConstants.NORTH_WEST)
//                SequenceLineResizableHandleKit.addHandle((GraphicalEditPart)getHost(), list, 
//                        PositionConstants.NORTH_WEST);
//            else
//                SequenceLineResizableHandleKit.addHandle((GraphicalEditPart)getHost(), list, 
//                        PositionConstants.NORTH_WEST);
//            if ((directions & PositionConstants.NORTH) != 0)
//                SequenceLineResizableHandleKit.addHandle((GraphicalEditPart)getHost(), list, 
//                        PositionConstants.NORTH);
//            else
//                SequenceLineNonResizableHandleKit.addHandle((GraphicalEditPart)getHost(), list, 
//                        PositionConstants.NORTH);
//            if ((directions & PositionConstants.NORTH_EAST) == PositionConstants.NORTH_EAST)
//                SequenceLineResizableHandleKit.addHandle((GraphicalEditPart)getHost(), list, 
//                        PositionConstants.NORTH_EAST);
//            else
//                SequenceLineNonResizableHandleKit.addHandle((GraphicalEditPart)getHost(), list, 
//                            PositionConstants.NORTH_EAST);  
//        } else
//            SequenceLineResizableHandleKit.addHandles((GraphicalEditPart)getHost(), list);
//        
//        return list;
//    }
//
//    /**
//     * Dispatches erase requests to more specific methods.
//     * @see org.eclipse.gef.EditPolicy#eraseSourceFeedback(org.eclipse.gef.Request)
//     */
//    public void eraseSourceFeedback(Request request) {
//        if (REQ_RESIZE.equals(request.getType()))
//            eraseChangeBoundsFeedback((ChangeBoundsRequest) request);
//        else
//            super.eraseSourceFeedback(request);
//    }
//
//    /**
//     * @see org.eclipse.gef.EditPolicy#getCommand(org.eclipse.gef.Request)
//     */
//    public Command getCommand(Request request) {
//        if (REQ_RESIZE.equals(request.getType()))
//            return getResizeCommand((ChangeBoundsRequest)request);
//
//        return super.getCommand(request);
//    }
//
//    /**
//     * Returns the current resize directions integer that depicts which handles
//     * can be resized on this object.
//     * 
//     * @return handle directions that can be resized
//     */
//    public int getResizeDirections() {
//        return directions;
//    }
//
//    /**
//     * Returns the command contribution for the given resize request. By default, the request
//     * is redispatched to the host's parent as a {@link
//     * org.eclipse.gef.RequestConstants#REQ_RESIZE_CHILDREN}.  The parent's editpolicies
//     * determine how to perform the resize based on the layout manager in use.
//     * @param request the resize request
//     * @return the command contribution obtained from the parent
//     */
//    protected Command getResizeCommand(ChangeBoundsRequest request) {
//        ChangeBoundsRequest req = new ChangeBoundsRequest(REQ_RESIZE_CHILDREN);
//        req.setEditParts(getHost().getParent());
//        
//        if(getHost() instanceof LineEditPart && request.getSizeDelta().width > 0) {
//            request.getSizeDelta().width = 0;
//        }
//        
//        if(getHost() instanceof LineEditPart && request.getMoveDelta().x != 0) {
//            request.getMoveDelta().x = 0;
//        }
//        
//        if(getHost() instanceof LineEditPart && request.getMoveDelta().y != 0) {
//            request.getMoveDelta().y = 0;
//        }
//      
//        req.setMoveDelta(request.getMoveDelta());
//        req.setSizeDelta(request.getSizeDelta());
//        req.setLocation(request.getLocation());
//        req.setExtendedData(request.getExtendedData());
//        req.setResizeDirection(request.getResizeDirection());
//        return getHost().getParent().getParent().getCommand(req);
//    }
//
//    /**
//     * Sets the directions in which handles should allow resizing. Valid values are bit-wise
//     * combinations of:
//     * <UL>
//     *   <LI>{@link PositionConstants#NORTH}
//     *   <LI>{@link PositionConstants#SOUTH}
//     *   <LI>{@link PositionConstants#EAST}
//     *   <LI>{@link PositionConstants#WEST}
//     * </UL>
//     * 
//     * @param newDirections the direction in which resizing is allowed
//     */
//    public void setResizeDirections(int newDirections) {
//        directions = newDirections;
//    }
//
//    /**
//     * @see org.eclipse.gef.EditPolicy#showSourceFeedback(org.eclipse.gef.Request)
//     */
//    public void showSourceFeedback(Request request) {
//        if (REQ_RESIZE.equals(request.getType()))
//            showChangeBoundsFeedback((ChangeBoundsRequest)request);
//        else
//            super.showSourceFeedback(request);
//    }
//    
//    /**
//     * @see org.eclipse.gef.editpolicies.NonResizableEditPolicy#getInitialFeedbackBounds()
//     */
//    @Override
//    protected Rectangle getInitialFeedbackBounds() {
//        if (((GraphicalEditPart)getHost()).getFigure() instanceof HandleBounds)
//            return ((HandleBounds)((GraphicalEditPart)getHost()).getFigure()).getHandleBounds();
//        
//        if(((GraphicalEditPart)getHost()).getFigure() instanceof LineFigure){
//           return ((LineFigure) ((GraphicalEditPart)getHost()).getFigure()).getLine().getBounds();
//        }
//        
//        return ((GraphicalEditPart)getHost()).getFigure().getBounds();
//    }
//    
//    /**
//     * @see org.eclipse.gef.EditPolicy#understandsRequest(org.eclipse.gef.Request)
//     */
//    public boolean understandsRequest(Request request) {
//        if (REQ_RESIZE.equals(request.getType()))
//            return true;
//        return super.understandsRequest(request);
//    }
    
    /**
     * @see org.eclipse.gef.editpolicies.NonResizableEditPolicy#showChangeBoundsFeedback(org.eclipse.gef.requests.ChangeBoundsRequest)
     */
    @Override
    protected void showChangeBoundsFeedback(ChangeBoundsRequest request) {
        if( null == getDragSourceFeedbackFigure()) {
            return;
        }
        super.showChangeBoundsFeedback(request);
    }
}
