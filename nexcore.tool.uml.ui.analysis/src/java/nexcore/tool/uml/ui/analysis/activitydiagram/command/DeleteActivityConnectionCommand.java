/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.analysis.activitydiagram.command;

import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.manager.util.DiagramUtil;
import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.Diagram;

import org.eclipse.gef.commands.Command;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.analysis</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.analysis.activitydiagram.command</li>
 * <li>설  명 : DeleteActivityConnectionCommand</li>
 * <li>작성일 : 2011. 7. 13.</li>
 * <li>작성자 : 강경구 </li>
 * </ul>
 */
public class DeleteActivityConnectionCommand extends Command {
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
        if( null != diagram ) { 
            diagram.getConnectionList().remove(connection);
        }
        UMLManager.deleteElement( connection );
        UMLManager.deleteElement( connection.getUmlModel() );
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
