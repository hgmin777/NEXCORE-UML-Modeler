/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.analysis.activitydiagram.edit.policy;

import nexcore.tool.uml.ui.analysis.activitydiagram.command.DeleteActivityPartitionNodeCommand;
import nexcore.tool.uml.ui.analysis.activitydiagram.command.DeleteControlNodeCommand;
import nexcore.tool.uml.ui.analysis.activitydiagram.edit.part.AbstractControlNodeEditPart;
import nexcore.tool.uml.ui.analysis.activitydiagram.edit.part.ActivityPartitionEditPart;
import nexcore.tool.uml.ui.analysis.activitydiagram.edit.part.CentralBufferNodeEditPart;
import nexcore.tool.uml.ui.analysis.activitydiagram.edit.part.DataStoreNodeEditPart;
import nexcore.tool.uml.ui.analysis.activitydiagram.edit.part.OpaqueActionEditPart;
import nexcore.tool.uml.ui.core.diagram.command.DeleteNodeCommand;
import nexcore.tool.uml.ui.core.diagram.edit.part.CompartmentLabelNodeEditPart;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.analysis</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.analysis.activitydiagram.edit.policy</li>
 * <li>설 명 : ActivityDiagramComponentEditPolicy</li>
 * <li>작성일 : 2011. 1. 31.</li>
 * <li>작성자 : zerotae</li>
 * </ul>
 */
public class ActivityDiagramComponentEditPolicy extends ComponentEditPolicy {

    /**
     * @see org.eclipse.gef.editpolicies.ComponentEditPolicy#getDeleteCommand(org.eclipse.gef.requests.GroupRequest)
     */
    @Override
    protected Command getDeleteCommand(GroupRequest request) {
        Command command;

        if (getHost() instanceof CompartmentLabelNodeEditPart) {
            return null;
        } else {
            if (getHost() instanceof AbstractControlNodeEditPart || getHost() instanceof OpaqueActionEditPart
                || getHost() instanceof CentralBufferNodeEditPart || getHost() instanceof DataStoreNodeEditPart) {
                
                command = new DeleteControlNodeCommand();
                ((DeleteControlNodeCommand) command).setParent(getHost().getParent().getModel());
                ((DeleteControlNodeCommand) command).setNode(getHost().getModel());
                return command;
            
            } else if ( getHost() instanceof ActivityPartitionEditPart ) {
            
                command = new DeleteActivityPartitionNodeCommand();
                ((DeleteActivityPartitionNodeCommand) command).setParent(getHost().getParent().getModel());
                ((DeleteActivityPartitionNodeCommand) command).setNode(getHost().getModel());
                return command;
                
            }else {
            
                command = new DeleteNodeCommand();
                ((DeleteNodeCommand) command).setParent(getHost().getParent().getModel());
                ((DeleteNodeCommand) command).setNode(getHost().getModel());
                return command;
            
            }
        }
    }

}
