/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.analysis.activitydiagram.edit.policy;

import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.ui.analysis.activitydiagram.command.ActivityPartitionDirectEditCommand;
import nexcore.tool.uml.ui.analysis.activitydiagram.edit.part.ActivityPartitionEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.policy.DirectEditorPolicy;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.uml2.uml.NamedElement;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.analysis</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.analysis.activitydiagram.edit.policy</li>
 * <li>설  명 : ActivityPartitionDirectEditorPolicy</li>
 * <li>작성일 : 2009. 12. 7.</li>
 * <li>작성자 : 강경구 </li>
 * </ul>
 */
public class ActivityPartitionDirectEditorPolicy extends DirectEditorPolicy {

    /**
     * @see nexcore.tool.uml.ui.core.diagram.edit.policy.DirectEditorPolicy#getDirectEditCommand(org.eclipse.gef.requests.DirectEditRequest)
     */
    protected Command getDirectEditCommand(DirectEditRequest request) {

        ActivityPartitionDirectEditCommand command = new ActivityPartitionDirectEditCommand();
        Object model = getHost().getModel();

        if (model instanceof NamedElement) {
            command.setUmlModel(model);
        } else if (model instanceof AbstractView) {

            if ( model instanceof ContainerNode ) {
                ContainerNode node = (ContainerNode) model;
                command.setViewModel(node);
                command.setUmlModel(node.getUmlModel());
            }
        }

        // setFigure
        EditPart editPart = null;
        if (getHost() instanceof ActivityPartitionEditPart) {
            command.setLabelFigure(((ActivityPartitionEditPart) getHost()).getFigure());
        }

        // setEditPart
        if (model instanceof ContainerNode) {
            // NameLabel을 직접 선택하지 않고 피겨에서 바로 다이렉트 에디팅을 실행.
            command.setEditPart(editPart);
        } else {
            command.setEditPart(getHost());
        }

        // setText
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
