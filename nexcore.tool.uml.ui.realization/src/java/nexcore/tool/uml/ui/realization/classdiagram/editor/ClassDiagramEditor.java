/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.realization.classdiagram.editor;

import nexcore.tool.uml.model.umldiagram.DiagramType;
import nexcore.tool.uml.ui.core.diagram.editor.AbstractDiagramEditor;
import nexcore.tool.uml.ui.core.diagram.editor.palette.DiagramPaletteFactory;
import nexcore.tool.uml.ui.realization.classdiagram.action.ClassDiagramContextMenuProvider;
import nexcore.tool.uml.ui.realization.classdiagram.edit.part.ClassDiagramEditPartFactory;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.actions.ActionRegistry;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.classdiagram.editor</li>
 * <li>설 명 : ClassDiagramEditor</li>
 * <li>작성일 : 2009. 11. 16.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class ClassDiagramEditor extends AbstractDiagramEditor {

    /** ID */
    public static final String ID = "nexcore.tool.uml.ui.realization.classdiagrameditor"; //$NON-NLS-1$

    /**
     * @see nexcore.tool.uml.ui.core.diagram.editor.AbstractDiagramEditor#createDiagramEditPartFactory()
     */
    @Override
    protected EditPartFactory createDiagramEditPartFactory() {
        return new ClassDiagramEditPartFactory();
    }

    /**
     * @see org.eclipse.gef.ui.parts.GraphicalEditor#createActions()
     */
    // @Override
    // protected void createActions() {
    // super.createActions();
    // ActionRegistry registry = getActionRegistry();
    // IAction action;
    //        
    // action = new SaveToImageAction(this);
    // registry.registerAction(action);
    // getSelectionActions().add(action.getId());
    //        
    // }
    /**
     * @see nexcore.tool.uml.ui.core.diagram.editor.AbstractDiagramEditor#createContextMenuProvider(org.eclipse.gef.EditPartViewer,
     *      org.eclipse.gef.ui.actions.ActionRegistry)
     */
    @Override
    protected ContextMenuProvider createContextMenuProvider(EditPartViewer viewer, ActionRegistry registry) {
        return new ClassDiagramContextMenuProvider(viewer, registry);
    }

    /**
     * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette#getPaletteRoot()
     */
    @Override
    protected PaletteRoot getPaletteRoot() {
        return DiagramPaletteFactory.createPalette(DiagramType.CLASS_DIAGRAM);

    }

}
