/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.analysis.activitydiagram.command;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.util.DiagramUtil;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.ui.analysis.SWTResourceManager;

import org.eclipse.draw2d.Label;
import org.eclipse.gef.commands.Command;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.uml2.uml.NamedElement;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.analysis</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.analysis.activitydiagram.command</li>
 * <li>설 명 : LabelNodeDirectEditCommand</li>
 * <li>작성일 : 2011. 1. 31.</li>
 * <li>작성자 : zerotae</li>
 * </ul>
 */
public class NodeLabelDirectEditCommand extends Command {
    /** oldText, newText */
    private String oldText, newText;

    /** object */
    private Object object;

    /** node */
    private AbstractNode node;

    /** font */
    Font font = SWTResourceManager.getFont(UMLMessage.LABEL_COURIER, 10, SWT.BOLD);

    /** notationNode */
    private Label figure;

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        oldText = figure.getText();

        redo();
    }

    /**
     * setUmlModel
     *  
     * @param umlModel void
     */
    public void setUmlModel(Object umlModel) {
        object = umlModel;
    }

    /**
     * setNode
     *  
     * @param node void
     */
    public void setNode(AbstractNode node) {
        this.node = node;
    }

    /**
     * setFigure
     *  
     * @param object void
     */
    public void setFigure(Label object) {
        figure = object;
    }

    /**
     * setText
     *  
     * @param text void
     */
    public void setText(String text) {
        newText = text;
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        ((NamedElement) object).setName(oldText);
        figure.setText(oldText);
        node.setName(oldText);

        if ((DiagramUtil.getWidthSize(newText, font) + 20) > node.getWidth())
            node.setWidth(DiagramUtil.getWidthSize(oldText, font) + 20);
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    @Override
    public void redo() {
        ((NamedElement) object).setName(newText);
        figure.setText(newText);
        node.setName(newText);
        if ((DiagramUtil.getWidthSize(newText, font) + 20) > node.getWidth())
            node.setWidth(DiagramUtil.getWidthSize(newText, font) + 20);
    }
}
