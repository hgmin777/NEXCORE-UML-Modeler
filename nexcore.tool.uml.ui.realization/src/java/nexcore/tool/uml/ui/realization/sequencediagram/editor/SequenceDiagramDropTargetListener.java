/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.realization.sequencediagram.editor;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.ui.core.diagram.editor.AbstractDiagramEditor;
import nexcore.tool.uml.ui.core.diagram.editor.DiagramDropTargetListener;
import nexcore.tool.uml.ui.core.project.ITreeNode;
import nexcore.tool.uml.ui.realization.sequencediagram.command.DropLifeLineNotationCommand;

import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.uml2.uml.Element;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.editor</li>
 * <li>설  명 : SequenceDiagramDropTargetListener</li>
 * <li>작성일 : 2009. 12. 21.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class SequenceDiagramDropTargetListener extends DiagramDropTargetListener {

    /**
     * SequenceDiagramDropTargetListener
     * @param diagramEditor
     */
    public SequenceDiagramDropTargetListener(AbstractDiagramEditor diagramEditor) {
        super(diagramEditor);
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.editor.DiagramDropTargetListener#dropCommandForNode(org.eclipse.swt.dnd.DropTargetEvent,
     *      org.eclipse.gef.commands.CompoundCommand,
     *      nexcore.tool.uml.ui.core.project.ITreeNode, int)
     */
    @Override
    protected void dropCommandForNode(DropTargetEvent event, CompoundCommand commpoundCommand, ITreeNode next, int count) {

        Element treeElement = (Element) next.getEObject();
        int horizontalScrollPoint = 0;
        int verticalScrollPoint = 0;
        Point eventPoint = null;
        Command command = null;
        org.eclipse.draw2d.geometry.Point where = null;

        horizontalScrollPoint = ((FigureCanvas) diagramEditor.getDiagramGraphicalViewer().getControl()).getHorizontalBar()
            .getSelection();
        verticalScrollPoint = ((FigureCanvas) diagramEditor.getDiagramGraphicalViewer().getControl()).getVerticalBar()
            .getSelection();
        eventPoint = diagramEditor.getDiagramGraphicalViewer().getControl().toControl(event.x + horizontalScrollPoint,
            event.y + verticalScrollPoint);

        // y좌표에 +100은 여러개를 Dorp했을때 대각선 아래로 위치하도록 한다.
        where = new org.eclipse.draw2d.geometry.Point(eventPoint.x + (count * 160), eventPoint.y);//20);
        command = new DropLifeLineNotationCommand(UMLMessage.LABEL_ADD_DROP, diagramEditor, treeElement, where);
        ((DropLifeLineNotationCommand) command).setDiagram(this.diagramEditor.getDiagram());
        commpoundCommand.add(command);

    }

}
