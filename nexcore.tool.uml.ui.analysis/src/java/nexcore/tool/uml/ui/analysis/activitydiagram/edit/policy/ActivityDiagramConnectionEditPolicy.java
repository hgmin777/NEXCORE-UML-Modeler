/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.analysis.activitydiagram.edit.policy;

import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.ui.analysis.activitydiagram.command.DeleteActivityConnectionCommand;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.editpolicies.ConnectionEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.analysis</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.analysis.activitydiagram.edit.policy</li>
 * <li>설  명 : ActivityDiagramConnectionEditPolicy</li>
 * <li>작성일 : 2011. 7. 13.</li>
 * <li>작성자 : 강경구 </li>
 * </ul>
 */
public class ActivityDiagramConnectionEditPolicy extends ConnectionEditPolicy {

    /**
     * @see org.eclipse.gef.editpolicies.ConnectionEditPolicy#getDeleteCommand(org.eclipse.gef.requests.GroupRequest)
     */
    @Override
    protected Command getDeleteCommand(GroupRequest request) {
        DeleteActivityConnectionCommand command = new DeleteActivityConnectionCommand();
        command.setConnection(getHost().getModel());
        command.setDiagram((Diagram) ((ScalableFreeformRootEditPart) getHost().getParent()).getContents().getModel());
        return command;
    }

}
