/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.analysis.componentdiagram.editor;

import nexcore.tool.uml.model.umldiagram.DiagramType;
import nexcore.tool.uml.ui.analysis.componentdiagram.edit.part.ComponentDiagramEditPartFactory;
import nexcore.tool.uml.ui.core.diagram.action.DiagramContextMenuProvider;
import nexcore.tool.uml.ui.core.diagram.editor.AbstractDiagramEditor;
import nexcore.tool.uml.ui.core.diagram.editor.palette.DiagramPaletteFactory;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.actions.ActionRegistry;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.analysis</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.analysis.componentdiagram.editor</li>
 * <li>설 명 : ComponentDiagramEditor</li>
 * <li>작성일 : 2011. 3. 3.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class ComponentDiagramEditor extends AbstractDiagramEditor {

    /** ID */
    public static final String ID = "nexcore.tool.uml.ui.analysis.componentdiagrameditor"; //$NON-NLS-1$

    /**
     * @see nexcore.tool.uml.ui.core.diagram.editor.AbstractDiagramEditor#createDiagramEditPartFactory()
     */
    @Override
    protected EditPartFactory createDiagramEditPartFactory() {
        return new ComponentDiagramEditPartFactory();
    }

    /**
     * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette#getPaletteRoot()
     */
    @Override
    protected PaletteRoot getPaletteRoot() {
        return DiagramPaletteFactory.createPalette(DiagramType.COMPONENT_DIAGRAM);
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.editor.AbstractDiagramEditor#createContextMenuProvider(org.eclipse.gef.EditPartViewer,
     *      org.eclipse.gef.ui.actions.ActionRegistry)
     */
    @Override
    protected ContextMenuProvider createContextMenuProvider(EditPartViewer viewer, ActionRegistry registry) {
        return new DiagramContextMenuProvider(viewer, registry);
    }
}
