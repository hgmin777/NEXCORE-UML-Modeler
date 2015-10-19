/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.realization.sequencediagram.action;

import nexcore.tool.uml.ui.core.diagram.action.DiagramContextMenuProvider;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.action</li>
 * <li>설 명 : SequeceDiagramContextMenuProvider</li>
 * <li>작성일 : 2009. 12. 2.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class SequeceDiagramContextMenuProvider extends DiagramContextMenuProvider {

    /** actionRegistry */
    private ActionRegistry actionRegistry;

    /**
     * SequeceDiagramContextMenuProvider
     * @param viewer
     * @param registry
     */
    public SequeceDiagramContextMenuProvider(EditPartViewer viewer, ActionRegistry registry) {
        super(viewer, registry);
        setActionRegistry(registry);
    }

    /**
     * @see org.eclipse.gef.ContextMenuProvider#buildContextMenu(org.eclipse.jface.action.IMenuManager)
     */
    public void buildContextMenu(IMenuManager manager) {
        GEFActionConstants.addStandardActionGroups(manager);
        super.buildContextMenu(manager);
        IAction action = getActionRegistry().getAction(AddInteractionOperandAction.ADD_INTERACTIONOPERAND_ACTIONID);
        if (action.isEnabled()) {
            manager.appendToGroup(GEFActionConstants.GROUP_ADD, action);
        }
         action = getActionRegistry().getAction(AddCoveredLifelineAction.ADD_COVERED_LIFELINE_ACTIONID);
        if (action.isEnabled()) {
            manager.appendToGroup(GEFActionConstants.GROUP_ADD, action);
        }
         action = getActionRegistry().getAction(RemoveCoveredLifelineAction.REMOVE_COVERED_LIFELINE_ACTIONID);
        if (action.isEnabled()) {
            manager.appendToGroup(GEFActionConstants.GROUP_ADD, action);
        }
        
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
