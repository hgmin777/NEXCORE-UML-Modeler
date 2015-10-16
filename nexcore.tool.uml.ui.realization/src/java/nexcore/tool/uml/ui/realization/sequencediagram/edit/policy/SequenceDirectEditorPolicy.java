/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.realization.sequencediagram.edit.policy;

import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.realization.sequencediagram.command.LifeLineDirectEditCommand;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.edit.policy</li>
 * <li>설 명 : SequenceDirectEditorPolicy</li>
 * <li>작성일 : 2009. 12. 24.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class SequenceDirectEditorPolicy extends DirectEditPolicy {

    /**
     * @see org.eclipse.gef.editpolicies.DirectEditPolicy#getDirectEditCommand(org.eclipse.gef.requests.DirectEditRequest)
     */
    protected Command getDirectEditCommand(DirectEditRequest request) {

        LifeLineDirectEditCommand command = new LifeLineDirectEditCommand();
        NotationNode nodeName = null;

        if (getHost().getParent().getModel() instanceof NotationNode) {
            nodeName = (NotationNode) getHost().getParent().getModel();
        }
        command.setUmlModel(nodeName.getUmlModel());
        command.setText((String) request.getCellEditor().getValue());
        return command;
    }

    /**
     * @see org.eclipse.gef.editpolicies.DirectEditPolicy#showCurrentEditValue(org.eclipse.gef.requests.DirectEditRequest)
     */
    protected void showCurrentEditValue(DirectEditRequest request) {
    }
}
