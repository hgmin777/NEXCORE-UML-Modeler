/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.diagram.action;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.registry.IConstantImageRegistry;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.actions.ActionFactory;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.action</li>
 * <li>설 명 : DiagramContextMenuProvider</li>
 * <li>작성일 : 2009. 11. 18.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class DiagramContextMenuProvider extends ContextMenuProvider {

    /** actionRegistry */
    private ActionRegistry actionRegistry;

    /**
     * DiagramContextMenuProvider
     * @param viewer
     * @param registry
     */
    public DiagramContextMenuProvider(EditPartViewer viewer, ActionRegistry registry) {
        super(viewer);
        setActionRegistry(registry);
    }

    /**
     * @see org.eclipse.gef.ContextMenuProvider#buildContextMenu(org.eclipse.jface.action.IMenuManager)
     */
    public void buildContextMenu(IMenuManager manager) {
        GEFActionConstants.addStandardActionGroups(manager);

        IAction action;

        action = getActionRegistry().getAction(SaveToImageAction.SAVE_TO_IMAGE_ACTIONID);
        if (action != null) {
            if (action.isEnabled())
                manager.appendToGroup(GEFActionConstants.GROUP_REST, action);
        }
        
        action = getActionRegistry().getAction(PrintPreviewAction.PRINT_PREVIEW_ACTION_ID);
        if (action != null) {
            if (action.isEnabled())
                manager.appendToGroup(GEFActionConstants.GROUP_REST, action);
        }

        action = getActionRegistry().getAction(ActionFactory.UNDO.getId());
        action.setActionDefinitionId("org.eclipse.ui.edit.undo"); //$NON-NLS-1$
        action.setText(UMLMessage.LABEL_UNDO);
        manager.appendToGroup(GEFActionConstants.GROUP_UNDO, action);

        action = getActionRegistry().getAction(ActionFactory.REDO.getId());
        action.setActionDefinitionId("org.eclipse.ui.edit.redo"); //$NON-NLS-1$
        action.setText(UMLMessage.LABEL_REDO);
        manager.appendToGroup(GEFActionConstants.GROUP_UNDO, action);

        action = getActionRegistry().getAction(ActionFactory.DELETE.getId());
        action.setActionDefinitionId("org.eclipse.ui.edit.delete"); //$NON-NLS-1$
        action.setText(UMLMessage.LABEL_DELETE_WITH_SHORTCUT);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_EDIT, action);

        action = getActionRegistry().getAction(DeleteUMLElementAction.DELETE_UMLELEMENT_ACTIONID);
        if (action != null && action.isEnabled()) {
            action.setActionDefinitionId("org.eclipse.ui.edit.text.delete.line"); //$NON-NLS-1$
            manager.appendToGroup(GEFActionConstants.GROUP_EDIT, action);
        }

        action = getActionRegistry().getAction(ActionFactory.SAVE.getId());
        action.setActionDefinitionId("org.eclipse.ui.file.save"); //$NON-NLS-1$
        action.setText(UMLMessage.LABEL_SAVE);
        action.setImageDescriptor(UiCorePlugin.getImageDescriptor(IConstantImageRegistry.ICONNAME_SAVE_ACTION));
        manager.appendToGroup(GEFActionConstants.GROUP_SAVE, action);

        action = getActionRegistry().getAction(AddAttributeAction.ADD_ATTRIBUTE_ACTIONID);
        if (action.isEnabled()) {
            action.setActionDefinitionId("org.eclipse.jdt.ui.edit.text.java.open.editor"); //$NON-NLS-1$
            manager.appendToGroup(GEFActionConstants.GROUP_ADD, action);
        }
        action = getActionRegistry().getAction(AddOperationAction.ADD_OPERATION_ACTIONID);
        if (action.isEnabled()) {
            action.setActionDefinitionId("org.eclipse.jdt.ui.edit.text.java.open.type.hierarchy"); //$NON-NLS-1$
            manager.appendToGroup(GEFActionConstants.GROUP_ADD, action);
        }
        action = getActionRegistry().getAction(AddEnumerationLiteralAction.ADD_ENUMERATION_LITERAL_ACTIONID);
        if (action.isEnabled()) {
            manager.appendToGroup(GEFActionConstants.GROUP_ADD, action);
        }

        
        action = getActionRegistry().getAction(FindElementAction.FIND_ELEMENT_ACTIONID);
        if (action.isEnabled()) {
            manager.appendToGroup(GEFActionConstants.GROUP_FIND, action);
        }
        action = getActionRegistry().getAction(ApplyStereotypeAction.APPLY_STEREOTYPE_ACTIONID);
        if (action.isEnabled()) {
            manager.appendToGroup(GEFActionConstants.GROUP_FIND, action);
        }

        action = getActionRegistry().getAction(CopyDiagramNodeAction.COPY_DIAGRAMNODE_ACTIONID);
        if (action.isEnabled()) {
            manager.appendToGroup(GEFActionConstants.GROUP_COPY, action);
        }
        action = getActionRegistry().getAction(PasteDiagramNodeAction.PASTE_DIAGRAMNODE_ACTIONID);
        if (action.isEnabled()) {
            manager.appendToGroup(GEFActionConstants.GROUP_COPY, action);
        }
        action = getActionRegistry().getAction(CutDiagramNodeAction.CUT_DIAGRAMNODE_ACTIONID);
        if (action.isEnabled()) {
            manager.appendToGroup(GEFActionConstants.GROUP_COPY, action);
        }

        
        IMenuManager submenuManager = new MenuManager(UMLMessage.LABEL_COMPARTMENT_VISIBILITY, 
        		UiCorePlugin.getImageDescriptor(IConstantImageRegistry.ICONNAME_COMPARTMENT_VISIBILITY_ACTION), 
        		"COMPARTMENT_VISIBILITY"); //$NON-NLS-1$
        manager.add(submenuManager);//.insertAfter(GEFActionConstants.GROUP_COPY, submenuManager);
        submenuManager.add(new Separator(UICoreConstant.VISIBILITY));
        
        action = getActionRegistry().getAction(ShowNameOnlyAction.SHOW_NAME_ONLYID);
        if (action.isEnabled()) {
            submenuManager.appendToGroup(UICoreConstant.VISIBILITY, action);
        }
        action = getActionRegistry().getAction(ShowTypeAction.SHOW_TYPEID);
        if (action.isEnabled()) {
            submenuManager.appendToGroup(UICoreConstant.VISIBILITY, action);
        }
        action = getActionRegistry().getAction(ShowParametersAction.SHOW_PARAMETERS_ACTIONID);
        if (action.isEnabled()) {
            submenuManager.appendToGroup(UICoreConstant.VISIBILITY, action);
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
