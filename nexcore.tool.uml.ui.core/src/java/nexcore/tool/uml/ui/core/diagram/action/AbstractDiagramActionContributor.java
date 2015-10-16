/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.core.diagram.action;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.ui.actions.ActionBarContributor;
import org.eclipse.gef.ui.actions.AlignmentRetargetAction;
import org.eclipse.gef.ui.actions.DeleteRetargetAction;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.MatchHeightRetargetAction;
import org.eclipse.gef.ui.actions.MatchWidthRetargetAction;
import org.eclipse.gef.ui.actions.RedoRetargetAction;
import org.eclipse.gef.ui.actions.UndoRetargetAction;
import org.eclipse.gef.ui.actions.ZoomInRetargetAction;
import org.eclipse.gef.ui.actions.ZoomOutRetargetAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.actions.ActionFactory;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.action</li>
 * <li>설  명 : AbstractDiagramActionContributor</li>
 * <li>작성일 : 2009. 11. 17.</li>
 * <li>작성자 : ytchoi </li>
 * </ul>
 */
public abstract class AbstractDiagramActionContributor extends ActionBarContributor {

    /**
     * @see org.eclipse.gef.ui.actions.ActionBarContributor#buildActions()
     */
    protected void buildActions() {
        addRetargetAction(new UndoRetargetAction());
        addRetargetAction(new RedoRetargetAction());
        addRetargetAction(new DeleteRetargetAction());
        addRetargetAction(new ZoomInRetargetAction());
        addRetargetAction(new ZoomOutRetargetAction());
        
        addRetargetAction(new AlignmentRetargetAction(PositionConstants.LEFT));
        addRetargetAction(new AlignmentRetargetAction(PositionConstants.CENTER));
        addRetargetAction(new AlignmentRetargetAction(PositionConstants.RIGHT));
        addRetargetAction(new AlignmentRetargetAction(PositionConstants.TOP));
        addRetargetAction(new AlignmentRetargetAction(PositionConstants.MIDDLE));
        addRetargetAction(new AlignmentRetargetAction(PositionConstants.BOTTOM));
        
        addRetargetAction(new MatchWidthRetargetAction());
        addRetargetAction(new MatchHeightRetargetAction());
    }

    /**
     * @see org.eclipse.ui.part.EditorActionBarContributor#contributeToToolBar(org.eclipse.jface.action.IToolBarManager)
     */
    public void contributeToToolBar(IToolBarManager toolBarManager) {
        toolBarManager.add(getAction(ActionFactory.UNDO.getId()));
        toolBarManager.add(getAction(ActionFactory.REDO.getId()));
        toolBarManager.add(new Separator());
        toolBarManager.add(getActionRegistry().getAction(GEFActionConstants.ZOOM_IN));
        toolBarManager.add(getActionRegistry().getAction(GEFActionConstants.ZOOM_OUT));

        toolBarManager.add(new Separator());
        // 수평 방향의 정렬 액션의 추가
        toolBarManager.add(getActionRegistry().getAction(GEFActionConstants.ALIGN_LEFT));
        toolBarManager.add(getActionRegistry().getAction(GEFActionConstants.ALIGN_CENTER));
        toolBarManager.add(getActionRegistry().getAction(GEFActionConstants.ALIGN_RIGHT));
        toolBarManager.add(new Separator());
        // 수직 방향의 정렬 액션의 추가
        toolBarManager.add(getActionRegistry().getAction(GEFActionConstants.ALIGN_TOP));
        toolBarManager.add(getActionRegistry().getAction(GEFActionConstants.ALIGN_MIDDLE));
        toolBarManager.add(getActionRegistry().getAction(GEFActionConstants.ALIGN_BOTTOM));
        
        toolBarManager.add(new Separator());
        toolBarManager.add(getActionRegistry().getAction(GEFActionConstants.MATCH_WIDTH));
        toolBarManager.add(getActionRegistry().getAction(GEFActionConstants.MATCH_HEIGHT));
    }

    /**
     * @see org.eclipse.gef.ui.actions.ActionBarContributor#declareGlobalActionKeys()
     */
    @Override
    protected void declareGlobalActionKeys() {
        addGlobalActionKey(ActionFactory.PRINT.getId());
        addGlobalActionKey(ActionFactory.SELECT_ALL.getId());
        addGlobalActionKey(ActionFactory.PASTE.getId());
    }
}
