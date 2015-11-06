/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.realization.sequencediagram.edit.policy;

import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.ui.realization.sequencediagram.command.DeleteMessageConnectionCommand;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.editpolicies.ConnectionEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.edit.policy</li>
 * <li>설 명 : MessageConnectionEditPolicy</li>
 * <li>작성일 : 2010. 1. 14.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class MessageConnectionEditPolicy extends ConnectionEditPolicy {

    /**
     * @see org.eclipse.gef.editpolicies.ConnectionEditPolicy#getDeleteCommand(org.eclipse.gef.requests.GroupRequest)
     */
    @Override
    protected Command getDeleteCommand(GroupRequest request) {
        DeleteMessageConnectionCommand command = new DeleteMessageConnectionCommand();
        command.setConnection(getHost().getModel());
        command.setDiagram((Diagram) ((ScalableFreeformRootEditPart) getHost().getParent()).getContents().getModel());
        return command;
    }

}
