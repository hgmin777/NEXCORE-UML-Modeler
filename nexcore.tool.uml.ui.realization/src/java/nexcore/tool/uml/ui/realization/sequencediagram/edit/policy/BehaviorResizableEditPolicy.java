/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.realization.sequencediagram.edit.policy;

import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.realization.sequencediagram.command.ReSizeOnlyHeightBehaviorNodeCommand;
import nexcore.tool.uml.ui.realization.sequencediagram.util.SequenceUtil;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.uml2.uml.CombinedFragment;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.edit.policy</li>
 * <li>설 명 : BehaviorResizableEditPolicy</li>
 * <li>작성일 : 2010. 1. 15.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class BehaviorResizableEditPolicy extends ResizableEditPolicy {

    int resizeRequestDirection = -1;
    
    /**
     * @see org.eclipse.gef.editpolicies.ResizableEditPolicy#getResizeCommand(org.eclipse.gef.requests.ChangeBoundsRequest)
     */
    @Override
    protected Command getResizeCommand(ChangeBoundsRequest request) {
        
    	NotationNode behaviorNode = (NotationNode) getHost().getModel();
        if (behaviorNode.getHeight() + request.getSizeDelta().height < 10) {
            // 10보다 작게 리사이즈 할 수 없다.
            return null;
        }
        
        Diagram diagram = SequenceUtil.getDiagram(getHost());
        for( AbstractNode node : diagram.getNodeList() ) {
            
            Point resizeLocation = request.getLocation();//new Point(request.getLocation().x, request.getLocation().y + behaviorNode.getHeight() + request.getSizeDelta().height);
            if( node.getUmlModel() instanceof CombinedFragment ) {
                if( node.getY() < resizeLocation.y && resizeLocation.y < node.getY() + node.getHeight()) {
                    if(( node.getY() < behaviorNode.getY() && behaviorNode.getY() + behaviorNode.getHeight() < node.getY() + node.getHeight() )) {
                        return null;
                    }
                }
                
                if(( node.getY() < behaviorNode.getY() && behaviorNode.getY() + behaviorNode.getHeight() < node.getY() + node.getHeight() )) {
                    if( node.getY() + node.getHeight() < behaviorNode.getY() + behaviorNode.getHeight() + request.getSizeDelta().height ) {
                        return null;
                    }
                }
                
                if( behaviorNode.getY() < node.getY() && node.getY() < behaviorNode.getY() + behaviorNode.getHeight() + request.getSizeDelta().height && behaviorNode.getY() + behaviorNode.getHeight() + request.getSizeDelta().height < node.getY() + node.getHeight() ) { 
                    return null;
                }
            }

            if( node.getParent() instanceof Diagram ) {
                continue;
            }
            
            if( !(node.getUmlModel() instanceof CombinedFragment) ) {
                continue;
            }
            
            
            Rectangle rect = new Rectangle(node.getX(), node.getY(), node.getWidth(), node.getHeight()); 

            
            if( rect.contains(new Point(behaviorNode.getX(), behaviorNode.getY())) && rect.contains(resizeLocation) ) {
                break;
            }
            
            if(resizeLocation.y > node.getY()) {
                return null;
            }
        }
        
        // 위 방향으로 리사이즈 못하도록 제한. 20120605 by Kang
        // TODO 위 방향으로 리사이즈 했을 때 synch, asynch meassage도 같이 이동하도록 한다.
        resizeRequestDirection = request.getResizeDirection();
        if (resizeRequestDirection != PositionConstants.SOUTH && resizeRequestDirection != PositionConstants.SOUTH_EAST && resizeRequestDirection != PositionConstants.SOUTH_WEST ) { 
            return null;
        }
        
        
        NotationNode sourceNode = (NotationNode) getHost().getModel();
        for( AbstractConnection asynch : sourceNode.getIncomingConnectionList() ) {
            if( asynch.getY() + request.getSizeDelta().height < sourceNode.getY() ) {
            }
        }
        for( AbstractConnection reply : sourceNode.getOutgoingConnectionList() ) {
            if( reply.getY() + request.getSizeDelta().height < sourceNode.getY() ) {
                return null;
            }
        }
        
        if ( request.getSizeDelta().height != 0 ) {
    		return new ReSizeOnlyHeightBehaviorNodeCommand(getHost(), request.getSizeDelta().height);
        } else {
            return null;
        }
    }

    
    /**
     * @see org.eclipse.gef.editpolicies.NonResizableEditPolicy#getDragSourceFeedbackFigure()
     */
    @Override
    protected IFigure getDragSourceFeedbackFigure() {
        
        if( resizeRequestDirection == PositionConstants.SOUTH || resizeRequestDirection == PositionConstants.SOUTH_EAST || resizeRequestDirection == PositionConstants.SOUTH_WEST ) {
            return super.getDragSourceFeedbackFigure();
        } else {
            return new Figure();
        }
    }
    
    protected void showChangeBoundsFeedback(ChangeBoundsRequest request) {
        IFigure feedback = getDragSourceFeedbackFigure();

        PrecisionRectangle rect = new PrecisionRectangle(getInitialFeedbackBounds().getCopy());
        getHostFigure().translateToAbsolute(rect);
        rect.translate(request.getMoveDelta());
        rect.resize(request.getSizeDelta());

        feedback.translateToRelative(rect);
        
        Rectangle oldConstraint = ((GraphicalEditPart)getHost()).getFigure().getBounds();
        feedback.setBounds(new Rectangle(oldConstraint.x, oldConstraint.y, oldConstraint.width, rect.height));
    }
}
