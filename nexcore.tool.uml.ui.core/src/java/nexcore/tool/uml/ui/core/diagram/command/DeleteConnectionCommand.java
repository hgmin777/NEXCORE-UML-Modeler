/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.diagram.command;

import nexcore.tool.uml.manager.util.DiagramUtil;
import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.Diagram;

import org.eclipse.gef.commands.Command;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.command</li>
 * <li>설 명 : DeleteConnectionCommand</li>
 * <li>작성일 : 2009. 11. 18.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class DeleteConnectionCommand extends Command {
    /**
     * diagram
     */
    private Diagram diagram;

    /**
     * connection
     */
    private AbstractConnection connection;

    /**
     * 연결점 시작 노드
     */
    private AbstractNode sourceNode;

    /**
     * 연결선 끝 노드
     */
    private AbstractNode targetNode;

    /**
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        DiagramUtil.detachSourceOfConnection(connection);
        DiagramUtil.detachTargetOfConnection(connection);
        diagram.getConnectionList().remove(connection);
    }

    /**
     * set connection
     * 
     * @param model
     *            void
     */
    public void setConnection(Object model) {
        connection = (AbstractConnection) model;
        sourceNode = (AbstractNode) connection.getSource();
        targetNode = (AbstractNode) connection.getTarget();
    }

    /**
     * @param diagram
     *            the diagram to set
     */
    public void setDiagram(Diagram diagram) {
        this.diagram = diagram;
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        DiagramUtil.attachSourceOfConnection(connection);
        DiagramUtil.attachTargetOfConnection(connection);
        if (!sourceNode.getOutgoingConnectionList().contains(connection)) {
            sourceNode.getOutgoingConnectionList().add(connection);
        }
        if (!targetNode.getIncomingConnectionList().contains(connection)) {
            targetNode.getIncomingConnectionList().add(connection);
        }
        diagram.getConnectionList().add(connection);
    }

}
