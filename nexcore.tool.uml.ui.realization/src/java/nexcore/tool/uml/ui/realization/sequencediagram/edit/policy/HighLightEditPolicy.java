/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.realization.sequencediagram.edit.policy;

import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.registry.IConstantColorRegistry;
import nexcore.tool.uml.ui.realization.sequencediagram.figure.LineFigure;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;
import org.eclipse.swt.graphics.Color;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.edit.policy</li>
 * <li>설 명 : HighLightEditPolicy</li>
 * <li>작성일 : 2009. 12. 9.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class HighLightEditPolicy extends GraphicalEditPolicy {

    /** reservedColor */
    private Color reservedColor;

    /** highLightColor */
    private Color highLightColor = UiCorePlugin.getDefault().getColor(IConstantColorRegistry.Gray);

    /**
     * @param highLightColor
     */
    public HighLightEditPolicy(Color highLightColor) {
        super();
        if (highLightColor != null)
            this.highLightColor = highLightColor;
    }

    /**
     * @see org.eclipse.gef.EditPolicy#eraseTargetFeedback(org.eclipse.gef.Request)
     */
    public void eraseTargetFeedback(Request request) {
        if (reservedColor != null) {
            setContainerForeground(reservedColor);
            reservedColor = null;
        }
    }

    /**
     * getContainerForeground
     *  
     * @return Color
     */
    private Color getContainerForeground() {
        return getContainerFigure().getForegroundColor();
    }

    /**
     * getContainerFigure
     *  
     * @return IFigure
     */
    private IFigure getContainerFigure() {
        if(((GraphicalEditPart) getHost()).getFigure() instanceof LineFigure) {
            LineFigure lineFigure = (LineFigure) ((GraphicalEditPart) getHost()).getFigure();
            return lineFigure.getLine();
        } else {
            return ((GraphicalEditPart) getHost()).getFigure();            
        }
    }

    /**
     * @see org.eclipse.gef.EditPolicy#getTargetEditPart(org.eclipse.gef.Request)
     */
    public EditPart getTargetEditPart(Request request) {
        return request.getType().equals(RequestConstants.REQ_SELECTION_HOVER) ? getHost() : null;
    }

    /**
     * setContainerForeground
     *  
     * @param c void
     */
    private void setContainerForeground(Color c) {
        getContainerFigure().setForegroundColor(c);
    }

    /**
     * Changes the background color of the container to the highlight color
     */
    protected void showHighlight() {
        if (reservedColor == null) {
            reservedColor = getContainerForeground();
            setContainerForeground(highLightColor);
        }
    }

    /**
     * @see org.eclipse.gef.EditPolicy#showTargetFeedback(org.eclipse.gef.Request)
     */
    public void showTargetFeedback(Request request) {
        if (request.getType().equals(RequestConstants.REQ_CONNECTION_START)
            || request.getType().equals(RequestConstants.REQ_CONNECTION_END))
            showHighlight();
    }

    /**
     * @see org.eclipse.gef.editpolicies.GraphicalEditPolicy#removeFeedback(org.eclipse.draw2d.IFigure)
     */
    @Override
    protected void removeFeedback(IFigure figure) {
        if( getFeedbackLayer().getChildren().isEmpty() ){
            return;
        } 
        if( !getFeedbackLayer().getChildren().contains(figure) ){
            return;
        }
        super.removeFeedback(figure);
    }
}
