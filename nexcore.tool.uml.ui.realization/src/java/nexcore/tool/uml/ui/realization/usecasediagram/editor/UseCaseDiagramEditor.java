/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.realization.usecasediagram.editor;

import nexcore.tool.uml.model.umldiagram.DiagramType;
import nexcore.tool.uml.ui.core.diagram.action.SaveToImageAction;
import nexcore.tool.uml.ui.core.diagram.editor.AbstractDiagramEditor;
import nexcore.tool.uml.ui.core.diagram.editor.palette.DiagramPaletteFactory;
import nexcore.tool.uml.ui.realization.usecasediagram.edit.part.UseCaseDiagramEditPartFactory;

import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.DirectEditAction;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IWorkbenchPart;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.usecasediagram.editor</li>
 * <li>설 명 : UseCaseDiagramEditor</li>
 * <li>작성일 : 2009. 11. 19.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class UseCaseDiagramEditor extends AbstractDiagramEditor {

    // /**
    // * @see
    // nexcore.tool.uml.ui.core.diagram.editor.AbstractDiagramEditor#getDefaultTool()
    // */
    // @Override
    // protected Tool getDefaultTool() {
    // // TODO Auto-generated method stub
    // return new UseCaseDiagramSelectionTool();
    // }

    /** ID */
    public static final String ID = "nexcore.tool.uml.ui.realization.usecasediagrameditor"; //$NON-NLS-1$

    /**
     * @see nexcore.tool.uml.ui.core.diagram.editor.AbstractDiagramEditor#createDiagramEditPartFactory()
     */
    @Override
    protected EditPartFactory createDiagramEditPartFactory() {
        return new UseCaseDiagramEditPartFactory();
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

    }

    /**
     * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette#getPaletteRoot()
     */
    @Override
    protected PaletteRoot getPaletteRoot() {
        return DiagramPaletteFactory.createPalette(DiagramType.USE_CASE_DIAGRAM);
    }

}
