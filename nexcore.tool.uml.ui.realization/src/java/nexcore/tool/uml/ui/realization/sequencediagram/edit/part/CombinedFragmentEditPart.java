/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.realization.sequencediagram.edit.part;

import java.util.List;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.core.diagram.edit.part.AbstractNotationNodeEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.policy.DirectEditorPolicy;
import nexcore.tool.uml.ui.realization.sequencediagram.figure.CombinedFragmentFigure;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.Request;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.edit.part</li>
 * <li>설  명 : CombinedFragmentEditPart</li>
 * <li>작성일 : 2011. 4. 7.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class CombinedFragmentEditPart extends AbstractNotationNodeEditPart {

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractNotationNodeEditPart#createGraphicalNodeEditPolicy()
     */
    @Override
    protected GraphicalNodeEditPolicy createGraphicalNodeEditPolicy() {
        return null;
    }
    
    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractNotationNodeEditPart#createDirectEditorPolicy()
     */
    @Override
    protected DirectEditorPolicy createDirectEditorPolicy() {
        return null;
    }
    
    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    @Override
    protected IFigure createFigure() {

        NotationNode notationNode = (NotationNode) getModel();

        CombinedFragmentFigure combinedFragmentFigure = new CombinedFragmentFigure();
        combinedFragmentFigure.setSize(notationNode.getWidth(), notationNode.getHeight());
        combinedFragmentFigure.setLocation(new Point(notationNode.getX(), notationNode.getY()));
        if (NodeType.OPTION_IF.equals(notationNode.getNodeType())) {
            combinedFragmentFigure.setText("Opt(if)");
        }
        if (NodeType.ALTERNATIVE_IF_ELSE.equals(notationNode.getNodeType())) {
            combinedFragmentFigure.setText("Alt(if else)");
        }
        if (NodeType.ALTERNATIVE_SWITCH.equals(notationNode.getNodeType())) {
            combinedFragmentFigure.setText("Alt(switch)");
        }
        if (NodeType.LOOP_WHILE.equals(notationNode.getNodeType())) {
            combinedFragmentFigure.setText("Loop(while)");
        }
        if (NodeType.LOOP_FOR.equals(notationNode.getNodeType())) {
            combinedFragmentFigure.setText("Loop(for)");
        }
        return combinedFragmentFigure;
    }


    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
     */
    @Override
    protected void refreshVisuals() {
    	try{
    	    super.refreshVisuals();
    	    
    		NotationNode notationNode = (NotationNode) getModel();
    		Rectangle bounds = new Rectangle(notationNode.getX(),
    				notationNode.getY(),
    				notationNode.getWidth(),
    				notationNode.getHeight());
    		
    		getFigure().setSize(notationNode.getWidth(), notationNode.getHeight());
    		getFigure().setLocation(new Point(notationNode.getX(), notationNode.getY()));
            getFigure().getLayoutManager().layout(getFigure());    		
    		setLayoutConstraint(this, getFigure(), bounds);
    		    		
    	} catch (Exception e) {
    		Log.error("CombinedFragmentEditPart refreshVisuals() Error " + e);
		}
    }
    
    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractNotationNodeEditPart#getModelChildren()
     */
    @SuppressWarnings("unchecked")
    @Override
    protected List getModelChildren() {
        return ((NotationNode) getModel()).getCompartmentList();
    }
        
    
    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.part.AbstractNotationNodeEditPart#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    @Override
    public void notifyChanged(Notification notification) {
        int eventType = notification.getEventType();
        
        switch (eventType) {
            case Notification.ADD:
            case Notification.ADD_MANY:
                if (notification.getNewValue() instanceof ContainerNode) {
                    refreshChildren();
                }                 
                break;
            case Notification.REMOVING_ADAPTER:
            case Notification.REMOVE:
                refreshVisuals();
                refreshChildren();
                break;
            case Notification.REMOVE_MANY:
                refreshVisuals();
                refreshSourceConnections();
                refreshTargetConnections();
                break;
            case Notification.MOVE:
            case Notification.UNSET:
                refreshVisuals();
                refreshChildren();
                break;
            case Notification.SET:
                
                if (notification.getNewValue() instanceof EAnnotation) {
                    ((EAnnotation) notification.getNewValue()).eAdapters().add(this);
                }
                refreshChildren();
                refreshVisuals();
                refreshSourceConnections();
                refreshTargetConnections();
                break;
            default:
                break;
        }
    }
    
    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshChildren()
     */
    @Override
    protected void refreshChildren() {
        try {
            super.refreshChildren();
        } catch (Exception e) {
        }
        List list = getChildren();
        for(Object object : list) {
            if(object instanceof InteractionOperandEditPart) {
                ((InteractionOperandEditPart) object).refresh();
            }
        }
    }
    
    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#eraseSourceFeedback(org.eclipse.gef.Request)
     */
    public void eraseSourceFeedback(Request request) {
        if (isActive()) {
            EditPolicyIterator iter = getEditPolicyIterator();
            while (iter.hasNext()) {
                EditPolicy nextEditPolicy = iter.next();
                if(nextEditPolicy instanceof ResizableEditPolicy) {
                    List<Object> feedbackList = getLayer(LayerConstants.FEEDBACK_LAYER).getChildren();
                    if(null == feedbackList || feedbackList.isEmpty()){
                        continue;
                    }
                }
                nextEditPolicy.eraseSourceFeedback(request);
            }
        }
    }
    
    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#deactivateEditPolicies()
     */
    protected void deactivateEditPolicies() {
        EditPolicyIterator iter = getEditPolicyIterator();
        while (iter.hasNext()) {
            EditPolicy nextEditPolicy = iter.next();
            if(nextEditPolicy instanceof ResizableEditPolicy) {
                List<Object> feedbackList = getLayer(LayerConstants.FEEDBACK_LAYER).getChildren();
                if(null == feedbackList || feedbackList.isEmpty()){
                    continue;
                }
            }
            nextEditPolicy.deactivate();
        }
    }
}
