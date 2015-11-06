/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.realization.sequencediagram.edit.policy;

import nexcore.tool.uml.ui.realization.sequencediagram.edit.part.LifeLineNameEditPart;
import nexcore.tool.uml.ui.realization.sequencediagram.edit.part.LineEditPart;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.edit.policy</li>
 * <li>설  명 : LifeLineResizeableEditPolicy</li>
 * <li>작성일 : 2011. 4. 25.</li>
 * <li>작성자 : zerotae</li>
 * </ul>
 */
public class LifeLineResizeableEditPolicy extends ResizableEditPolicy {

    /**
     * @see org.eclipse.gef.editpolicies.ResizableEditPolicy#getResizeCommand(org.eclipse.gef.requests.ChangeBoundsRequest)
     */
    @Override
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
}
