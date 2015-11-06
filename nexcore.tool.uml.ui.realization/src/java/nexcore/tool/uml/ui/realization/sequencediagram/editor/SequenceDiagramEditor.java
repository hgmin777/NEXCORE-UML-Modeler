/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.realization.sequencediagram.editor;

import nexcore.tool.uml.manager.utility.DomainModelHandlerUtil;
import nexcore.tool.uml.model.umldiagram.DiagramType;
import nexcore.tool.uml.ui.core.diagram.editor.AbstractDiagramEditor;
import nexcore.tool.uml.ui.core.diagram.editor.DiagramDropTargetListener;
import nexcore.tool.uml.ui.core.diagram.editor.DiagramEditorInput;
import nexcore.tool.uml.ui.realization.sequencediagram.action.AddCoveredLifelineAction;
import nexcore.tool.uml.ui.realization.sequencediagram.action.AddInteractionOperandAction;
import nexcore.tool.uml.ui.realization.sequencediagram.action.DeleteSequenceUMLElementAction;
import nexcore.tool.uml.ui.realization.sequencediagram.action.RemoveCoveredLifelineAction;
import nexcore.tool.uml.ui.realization.sequencediagram.action.SequeceDiagramContextMenuProvider;
import nexcore.tool.uml.ui.realization.sequencediagram.edit.part.SequenceDiagramEditPartFactory;
import nexcore.tool.uml.ui.realization.sequencediagram.editor.palette.SequenceDiagramPaletteFactory;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.DeleteAction;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPart;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.editor</li>
 * <li>설 명 : SequenceDiagramEditor</li>
 * <li>작성일 : 2009. 12. 2.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class SequenceDiagramEditor extends AbstractDiagramEditor {

    /** ID */
    public static final String ID = "nexcore.tool.uml.ui.realization.sequencediagrameditor"; //$NON-NLS-1$

    /**
     * @see nexcore.tool.uml.ui.core.diagram.editor.AbstractDiagramEditor#createDiagramEditPartFactory()
     */
    @Override
    protected EditPartFactory createDiagramEditPartFactory() {
        return new SequenceDiagramEditPartFactory();
    }
    
    /**
     * 입력 설정
     * 
     * @see org.eclipse.ui.part.EditorPart#setInput(org.eclipse.ui.IEditorInput)
     */
    @Override
    protected void setInput(IEditorInput input) {
        if (input instanceof DiagramEditorInput) {
            super.setInput(input);
            DiagramEditorInput editorInput = (DiagramEditorInput) input;
            updateTitleAndToolTip();
            diagramEditDomain = DomainModelHandlerUtil.createDiagramEditDomain(this, editorInput.getDiagram());
            diagramEditDomain.setDefaultTool(this.getDefaultTool());
            setEditDomain(diagramEditDomain);
            this.setHook();
        }
    }
    
    /**
     * 기본 툴 반환
     * 
     * @return Tool
     */
    @Override
    protected Tool getDefaultTool() {
        return new SequenceSelectionTool();
    }
    
    /**
     * @see org.eclipse.gef.ui.parts.GraphicalEditor#createActions()
     */
    @SuppressWarnings("unchecked")
    @Override
    protected void createActions() {
        super.createActions();
        ActionRegistry registry = getActionRegistry();

        IAction action = new AddInteractionOperandAction(this);
        registry.registerAction(action);
        getSelectionActions().add(action.getId());
        
         action = new AddCoveredLifelineAction(this);
        registry.registerAction(action);
        getSelectionActions().add(action.getId());
        
         action = new RemoveCoveredLifelineAction(this);
        registry.registerAction(action);
        getSelectionActions().add(action.getId());

        // 기존에 등록된 'Delete(다이어그램에서 삭제)' 액션을 빼고,
        // delete를 키로 DeleteUMLElementAction를 등록한다.
        IAction deleteAction = new DeleteAction((IWorkbenchPart) this);
        registry.removeAction(deleteAction);
        getSelectionActions().remove(deleteAction.getId());

        action = new DeleteSequenceUMLElementAction(this, false);
        registry.removeAction(action);
        getSelectionActions().remove(action.getId());

        action.setId(deleteAction.getId());
        registry.registerAction(action);
        getSelectionActions().add(deleteAction.getId());
        
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.editor.AbstractDiagramEditor#createContextMenuProvider(org.eclipse.gef.EditPartViewer,
     *      org.eclipse.gef.ui.actions.ActionRegistry)
     */
    @Override
    protected ContextMenuProvider createContextMenuProvider(EditPartViewer viewer, ActionRegistry registry) {
        return new SequeceDiagramContextMenuProvider(viewer, registry);
    }

    /**
     * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette#getPaletteRoot()
     */
    @Override
    protected PaletteRoot getPaletteRoot() {
        return SequenceDiagramPaletteFactory.createPalette(DiagramType.SEQUENCE_DIAGRAM);

    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.editor.AbstractDiagramEditor#createDropTagertListener()
     */
    @Override
    protected DiagramDropTargetListener createDropTagertListener() {
        return new SequenceDiagramDropTargetListener(this);
    }

}
