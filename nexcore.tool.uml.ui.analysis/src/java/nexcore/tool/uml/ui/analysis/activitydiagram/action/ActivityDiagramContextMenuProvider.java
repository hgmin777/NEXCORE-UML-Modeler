/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.analysis.activitydiagram.action;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.ui.core.diagram.action.DiagramContextMenuProvider;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.analysis</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.analysis.activitydiagram.action</li>
 * <li>설  명 : ActivityDiagramContextMenuProvider</li>
 * <li>작성일 : 2011. 7. 20.</li>
 * <li>작성자 : Kang</li>
 * </ul>
 */
public class ActivityDiagramContextMenuProvider extends DiagramContextMenuProvider {

    /** actionRegistry */
    private ActionRegistry actionRegistry;

    /**
     * ActivityDiagramContextMenuProvider
     * @param viewer
     * @param registry
     */
    public ActivityDiagramContextMenuProvider(EditPartViewer viewer, ActionRegistry registry) {
        super(viewer, registry);
        setActionRegistry(registry);
    }

    /**
     * @see org.eclipse.gef.ContextMenuProvider#buildContextMenu(org.eclipse.jface.action.IMenuManager)
     */
    public void buildContextMenu(IMenuManager manager) {
        GEFActionConstants.addStandardActionGroups(manager);

        IAction action;

        action = getActionRegistry().getAction(ChangeForkAndJoinAction.CHANGE_ACTION_ID);
        if (action != null) {
            action.setText(UMLMessage.LABEL_CHANGE);
            if (action.isEnabled())
                manager.appendToGroup(GEFActionConstants.GROUP_REST, action);
        }
        IMenuManager submenuManager = new MenuManager("Activity Partition Direction");
        manager.add(submenuManager);//.insertAfter(GEFActionConstants.GROUP_COPY, submenuManager);
        submenuManager.add(new Separator("Partition Direction"));
        
        action = getActionRegistry().getAction(ChangeHorizontalDirectionAction.CHANGE_HORIZONTAL_DIRECTION_ACTION_ID);
        if (action != null) {
            action.setText(ChangeHorizontalDirectionAction.CHANGE_HORIZONTAL_DIRECTION_ACTION);
            if (action.isEnabled())
                submenuManager.appendToGroup("Partition Direction", action);
        }
        action = getActionRegistry().getAction(ChangeVerticalDirectionAction.CHANGE_VERTICAL_DIRECTION_ACTION_ID);
        if (action != null) {
            action.setText(ChangeVerticalDirectionAction.CHANGE_VERTICAL_DIRECTION_ACTION);
            if (action.isEnabled())
                submenuManager.appendToGroup("Partition Direction", action);
        }

        super.buildContextMenu(manager);
    }

    /**
     * getActionRegistry
     * 
     * @return ActionRegistry
     */
    public ActionRegistry getActionRegistry() {
        return actionRegistry;
    }

    /**
     * setActionRegistry
     * 
     * @param actionRegistry
     *            void
     */
    public void setActionRegistry(ActionRegistry actionRegistry) {
        this.actionRegistry = actionRegistry;
    }
}
