/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.analysis.activitydiagram.edit.policy;

import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.ui.analysis.activitydiagram.command.NodeLabelDirectEditCommand;

import org.eclipse.draw2d.Label;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.analysis</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.analysis.activitydiagram.edit.policy</li>
 * <li>설  명 : NodeLabelDirectEditorPolicy</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class NodeLabelDirectEditorPolicy extends DirectEditPolicy {

    /**
     * @see org.eclipse.gef.editpolicies.DirectEditPolicy#getDirectEditCommand(org.eclipse.gef.requests.DirectEditRequest)
     */
    @Override
    protected Command getDirectEditCommand(DirectEditRequest request) {

        NodeLabelDirectEditCommand command = new NodeLabelDirectEditCommand();
        command.setUmlModel(((AbstractNode) ((EditPart) getHost()).getModel()).getUmlModel());
        command.setNode((AbstractNode) ((EditPart) getHost()).getModel());

        command.setFigure((Label) getHostFigure());
        command.setText((String) request.getCellEditor().getValue());
        return command;
    }

    /**
     * @see org.eclipse.gef.editpolicies.DirectEditPolicy#showCurrentEditValue(org.eclipse.gef.requests.DirectEditRequest)
     */
    @Override
    protected void showCurrentEditValue(DirectEditRequest request) {

    }

}
