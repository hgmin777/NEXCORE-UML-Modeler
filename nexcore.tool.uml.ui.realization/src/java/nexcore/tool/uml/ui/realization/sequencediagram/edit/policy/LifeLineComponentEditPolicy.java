/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.realization.sequencediagram.edit.policy;

import nexcore.tool.uml.model.umldiagram.LifeLineNode;
import nexcore.tool.uml.ui.core.diagram.edit.part.AbstractChildCompartmentEditPart;
import nexcore.tool.uml.ui.realization.sequencediagram.command.DeleteLifeLineCommand;
import nexcore.tool.uml.ui.realization.sequencediagram.edit.part.LifeLineNameHeaderEditPart;
import nexcore.tool.uml.ui.realization.sequencediagram.util.SequenceUtil;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.edit.policy</li>
 * <li>설 명 : LifeLineComponentEditPolicy</li>
 * <li>작성일 : 2010. 1. 14.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class LifeLineComponentEditPolicy extends ComponentEditPolicy {

    /**
     * @see org.eclipse.gef.editpolicies.ComponentEditPolicy#getDeleteCommand(org.eclipse.gef.requests.GroupRequest)
     */
    @Override
    protected Command getDeleteCommand(GroupRequest request) {
       
        if (getHost() instanceof LifeLineNameHeaderEditPart) {
            return null;
        }

        if (getHost() instanceof AbstractChildCompartmentEditPart) {
            return null;
        } else {
            DeleteLifeLineCommand command = new DeleteLifeLineCommand();
            ((DeleteLifeLineCommand) command).setDiagram(SequenceUtil.getDiagram(getHost()));
            ((DeleteLifeLineCommand) command).setLifeLineNode((LifeLineNode) getHost().getModel());
            return command;
        }

    }

}
