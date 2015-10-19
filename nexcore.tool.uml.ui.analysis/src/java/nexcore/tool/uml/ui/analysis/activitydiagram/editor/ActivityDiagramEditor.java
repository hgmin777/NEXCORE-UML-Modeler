/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.analysis.activitydiagram.editor;

import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.model.umldiagram.DiagramType;
import nexcore.tool.uml.ui.analysis.activitydiagram.action.ActivityDiagramContextMenuProvider;
import nexcore.tool.uml.ui.analysis.activitydiagram.action.ChangeForkAndJoinAction;
import nexcore.tool.uml.ui.analysis.activitydiagram.action.ChangeHorizontalDirectionAction;
import nexcore.tool.uml.ui.analysis.activitydiagram.action.ChangeVerticalDirectionAction;
import nexcore.tool.uml.ui.analysis.activitydiagram.action.DeleteActivityUMLElementAction;
import nexcore.tool.uml.ui.analysis.activitydiagram.edit.part.ActivityDiagramEditPartFactory;
import nexcore.tool.uml.ui.core.diagram.action.SaveToImageAction;
import nexcore.tool.uml.ui.core.diagram.editor.AbstractDiagramEditor;
import nexcore.tool.uml.ui.core.diagram.editor.palette.DiagramPaletteFactory;
import nexcore.tool.uml.ui.core.util.PreferenceUtil;

import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.ManhattanConnectionRouter;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.DirectEditAction;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IWorkbenchPart;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.analysis</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.analysis.activitydiagram.editor</li>
 * <li>설 명 : ActivityDiagramEditor</li>
 * <li>작성일 : 2011. 1. 31.</li>
 * <li>작성자 : zerotae</li>
 * </ul>
 */
public class ActivityDiagramEditor extends AbstractDiagramEditor {

    /** ID */
    public static final String ID = "nexcore.tool.uml.ui.analysis.activitydiagrameditor"; //$NON-NLS-1$

    /**
     * @see nexcore.tool.uml.ui.core.diagram.editor.AbstractDiagramEditor#createDiagramEditPartFactory()
     */
    @Override
    protected EditPartFactory createDiagramEditPartFactory() {
        return new ActivityDiagramEditPartFactory();
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.editor.AbstractDiagramEditor#initializeGraphicalViewer()
     */
    @Override
    protected void initializeGraphicalViewer() {
        super.initializeGraphicalViewer();
        GraphicalViewer viewer = getGraphicalViewer();
        // ManhattanConnectionRouter 설정
        if ("true".equals(PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_ACTIVITYDIAGRAM_CONNECTION_ROUTER_USE))) {
            ScalableFreeformRootEditPart root = (ScalableFreeformRootEditPart) viewer.getRootEditPart();
            ConnectionLayer connLayer = (ConnectionLayer) root.getLayer(LayerConstants.CONNECTION_LAYER);
            connLayer.setConnectionRouter(new ManhattanConnectionRouter());
        }
    }

    /**
     * @see org.eclipse.gef.ui.parts.GraphicalEditor#createActions()
     */
    @SuppressWarnings("unchecked")
    @Override
    protected void createActions() {
        super.createActions();

        ActionRegistry registry = getActionRegistry();
        IAction action;

        action = new SaveToImageAction(this);
        registry.registerAction(action);
        getSelectionActions().add(action.getId());

        action = new DirectEditAction((IWorkbenchPart) this);
        registry.registerAction(action);
        getSelectionActions().add(action.getId());

        action = new ChangeForkAndJoinAction(this);
        registry.registerAction(action);
        getSelectionActions().add(action.getId());
        
        action = new ChangeHorizontalDirectionAction(this);
        registry.registerAction(action);
        getSelectionActions().add(action.getId());
        
        action = new ChangeVerticalDirectionAction(this);
        registry.registerAction(action);
        getSelectionActions().add(action.getId());
        
        action = new DeleteActivityUMLElementAction(this);
        registry.registerAction(action);
        getSelectionActions().add(action.getId());

    }

    /**
     * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette#getPaletteRoot()
     */
    @Override
    protected PaletteRoot getPaletteRoot() {
        return DiagramPaletteFactory.createPalette(DiagramType.ACTIVITY_DIAGRAM);
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.editor.AbstractDiagramEditor#createContextMenuProvider(org.eclipse.gef.EditPartViewer,
     *      org.eclipse.gef.ui.actions.ActionRegistry)
     */
    @Override
    protected ContextMenuProvider createContextMenuProvider(EditPartViewer viewer, ActionRegistry registry) {
        return new ActivityDiagramContextMenuProvider(viewer, registry);
    }

}
