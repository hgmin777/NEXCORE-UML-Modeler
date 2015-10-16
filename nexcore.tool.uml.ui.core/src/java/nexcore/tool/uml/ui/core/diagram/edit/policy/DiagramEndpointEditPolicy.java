/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.diagram.edit.policy;

import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.edit.policy</li>
 * <li>설 명 : DiagramEndpointEditPolicy</li>
 * <li>작성일 : 2009. 11. 18.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class DiagramEndpointEditPolicy extends ConnectionEndpointEditPolicy {

    /**
     * @see org.eclipse.gef.editpolicies.SelectionHandlesEditPolicy#addSelectionHandles()
     */
    protected void addSelectionHandles() {
        super.addSelectionHandles();
        getConnectionFigure().setLineWidth(2);
    }

    /**
     * @see org.eclipse.gef.editpolicies.SelectionHandlesEditPolicy#removeSelectionHandles()
     */
    protected void removeSelectionHandles() {
        super.removeSelectionHandles();
        getConnectionFigure().setLineWidth(0);
    }

    /**
     * PolylineConnection
     * 
     * @return PolylineConnection
     */
    protected PolylineConnection getConnectionFigure() {
        return (PolylineConnection) ((GraphicalEditPart) getHost()).getFigure();
    }

    // @Override
    // protected List createSelectionHandles() {
    // List handles = super.createSelectionHandles();
    // List<IFigure> children = (List<IFigure>) getHostFigure().getChildren();
    // for (IFigure figure : children) {
    // if (figure instanceof Label)
    // handles.add(new MoveHandle((GraphicalEditPart) getHost(),
    // new MoveHandleLocator(figure)));
    // }
    // return handles;
    // }
}
