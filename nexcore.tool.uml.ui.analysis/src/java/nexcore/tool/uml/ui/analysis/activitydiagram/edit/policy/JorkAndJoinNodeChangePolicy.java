/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.analysis.activitydiagram.edit.policy;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.ui.analysis.activitydiagram.command.ChangeForkAndJoinCommand;
import nexcore.tool.uml.ui.analysis.activitydiagram.request.ActivityDiagramRequest;

import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.analysis</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.analysis.activitydiagram.edit.policy</li>
 * <li>설 명 : JorkAndJoinNodeChangePolicy</li>
 * <li>작성일 : 2009-12-28</li>
 * <li>작성자 : Bojun</li>
 * </ul>
 */
public class JorkAndJoinNodeChangePolicy extends AbstractEditPolicy {
    /** ADD_ATTRIBUTES_ROLE */
    public static final String CHANGE_SHAPE_ROLE = UMLMessage.LABEL_CHANGE_SHAPE;

    /**
     * @see org.eclipse.gef.editpolicies.DirectEditPolicy#getCommand(org.eclipse.gef.Request)
     */
    @Override
    public Command getCommand(Request request) {
        // Judge whether this request is intersting by its type
        if (ActivityDiagramRequest.CHANGE_SHAPE == request.getType()) {
            ActivityDiagramRequest theRequest = (ActivityDiagramRequest) request;

            // Get information from request
            Object object = theRequest.getObject();

            // Create corresponding command and return it
            ChangeForkAndJoinCommand command = new ChangeForkAndJoinCommand();
            command.setObj(object);

            return command;
        }

        return null;
    }
}
