/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.diagram.edit.policy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ContainerEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.GroupRequest;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.edit.policy</li>
 * <li>설 명 : DiagramContainerEditPolicy</li>
 * <li>작성일 : 2011. 1. 25.</li>
 * <li>작성자 : zerotae</li>
 * </ul>
 */
public class DiagramContainerEditPolicy extends ContainerEditPolicy {

    
    /**
     * @see org.eclipse.gef.editpolicies.ContainerEditPolicy#getCreateCommand(org.eclipse.gef.requests.CreateRequest)
     */
    protected Command getCreateCommand(CreateRequest request) {
        return null;
    }

    /**
     * @see org.eclipse.gef.editpolicies.ContainerEditPolicy#getOrphanChildrenCommand(org.eclipse.gef.requests.GroupRequest)
     */
    public Command getOrphanChildrenCommand(GroupRequest request) {
//        List parts = request.getEditParts();
//        CompoundCommand result = new CompoundCommand("Command.label.orphanChildren");
//        for (int i = 0; i < parts.size(); i++) {
//            OrphanChildCommand orphan = new OrphanChildCommand("Command.label.orphanChildren");
//            orphan.setChild((NotationNode) ((EditPart) parts.get(i)).getModel());
//            orphan.setParent((ContainerNode) getHost().getModel());
//            result.add(orphan);
//        }
//        return result.unwrap();
        return null;
    }
}
