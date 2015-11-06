/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.realization.sequencediagram.command;

import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.ui.realization.sequencediagram.util.SequenceUtil;

import org.eclipse.gef.commands.Command;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.command</li>
 * <li>설 명 : DeleteAbstractConnectionCommand</li>
 * <li>작성일 : 2010. 1. 14.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class DeleteMessageConnectionCommand extends Command {
    /**
     * diagram
     */
    private Diagram diagram;

    /**
     * connection
     */
    private AbstractConnection connection;

    /**
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {

        SequenceUtil.deleteMessage(connection, diagram);
        SequenceUtil.refreshMessageOrder(diagram);

    }

    /**
     * set connection
     * 
     * @param model
     *            void
     */
    public void setConnection(Object model) {
        connection = (AbstractConnection) model;
    }

    /**
     * @param diagram
     *            the diagram to set
     */
    public void setDiagram(Diagram diagram) {
        this.diagram = diagram;
    }

}
