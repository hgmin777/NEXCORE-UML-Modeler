/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.analysis.activitydiagram.action;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.analysis.activitydiagram.command.ChangeForkAndJoinCommand;
import nexcore.tool.uml.ui.analysis.activitydiagram.edit.part.ForkNodeEditPart;
import nexcore.tool.uml.ui.analysis.activitydiagram.edit.part.JoinNodeEditPart;
import nexcore.tool.uml.ui.analysis.activitydiagram.request.ActivityDiagramRequest;
import nexcore.tool.uml.ui.core.diagram.edit.part.AbstractNotationNodeEditPart;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.analysis</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.analysis.activitydiagram.action</li>
 * <li>설 명 : SaveToImageAction</li>
 * <li>작성일 : 2009. 11. 27.</li>
 * <li>작성자 : Duyu</li>
 * </ul>
 */
public class ChangeForkAndJoinAction extends SelectionAction {

    /** CHANGE_ACTION */
    private static final String CHANGE_ACTION = UMLMessage.LABEL_CHANGE_ACTION;

    /** CHANGE_ACTION_ID */
    public static final String CHANGE_ACTION_ID = UMLMessage.LABEL_CHANGE_ACTION;

    /**
     * ChangeForkAndJoinAction
     * @param part
     */
    public ChangeForkAndJoinAction(IWorkbenchPart part) {
        super(part);

    }

    /**
     * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#init()
     */
    protected void init() {
        setId(CHANGE_ACTION_ID);
        setText(CHANGE_ACTION);
    }

    /**
     * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#calculateEnabled()
     */
    protected boolean calculateEnabled() {
        if (getSelectedObjects().size() < 1)
            return false;

        Object obj = getSelectedObjects().get(0);

        if (!(obj instanceof EditPart))
            return false;
        if (obj instanceof ForkNodeEditPart || obj instanceof JoinNodeEditPart) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @see org.eclipse.jface.action.Action#run()
     */
    public void run() {
        if (getSelectedObjects().size() < 1)
            return;

        Object obj = getSelectedObjects().get(0);

        if (!(obj instanceof EditPart))
            return;
        if (obj instanceof ForkNodeEditPart || obj instanceof JoinNodeEditPart) {
            NotationNode notaitonNode = (NotationNode) ((EditPart) obj).getModel();
            ActivityDiagramRequest request = new ActivityDiagramRequest(notaitonNode);

            ChangeForkAndJoinCommand command = (ChangeForkAndJoinCommand) ((AbstractNotationNodeEditPart) obj).getCommand(request);
            command.setFigure(((AbstractNotationNodeEditPart) obj).getFigure());
            getCommandStack().execute(command);
        }

    }

}
