/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.core.diagram.command.umlmodel;

import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.RelationType;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.command.umlmodel</li>
 * <li>설  명 : RepairSequenceDiagramCommand</li>
 * <li>작성일 : 2010. 3. 29.</li>
 * <li>작성자 : 한승일 </li>
 * </ul>
 */
public class RepairSequenceDiagramCommand extends Command {

    /**
     * diagram
     */
    private Diagram diagram;

    /**
     * @param diagramList
     *            the diagramList to set
     */
    public void setDiagram(Diagram diagram) {
        this.diagram = diagram;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {
        reqpairDiagram(this.diagram);
    }

    /**
     * reqpairDiagram
     *  
     * @param diagram void
     */
    private void reqpairDiagram(Diagram diagram) {
        for (AbstractConnection connection : diagram.getConnectionList()) {
            if (connection.getRelationType().equals(RelationType.ASYNCHRONOUS_MESSAGE)
                || connection.getRelationType().equals(RelationType.SYNCHRONOUS_MESSAGE)
                || connection.getRelationType().equals(RelationType.REPLY_MESSAGE)
                || connection.getRelationType().equals(RelationType.MESSAGE)) {
                repairConnection(connection);
            }
        }
    }

    /**
     * repairConnection
     *  
     * @param connection void
     */
    private void repairConnection(AbstractConnection connection) {
        AbstractView node = connection.getSource();
        AbstractNode abstractNode;
        // System.out.println("source1:" + connection.getSource().toString());
        if (node instanceof AbstractNode) {
            abstractNode = (AbstractNode) node;
            if (abstractNode.getNodeType().equals(NodeType.LIFE_LINE_BEHAVIOR)) {
                repairLineBehavior(abstractNode, connection);
            }
        } else {
            // System.out.println("***"+node.getParent().toString());
        }
    }

    /**
     * repairLineBehavior
     *  
     * @param abstractNode
     * @param connection void
     */
    private void repairLineBehavior(AbstractNode abstractNode, AbstractConnection connection) {
        AbstractNode parentNode = abstractNode;
        AbstractNode parentTemp;

        EObject temp;
        while (!parentNode.getNodeType().equals(NodeType.LINE)) {
            temp = parentNode.getParent();
            if (!(temp instanceof AbstractNode)) {
                break;
            }
            parentNode = (AbstractNode) temp;
        }
        if (!parentNode.getNodeType().equals(NodeType.LINE)) {
            // System.out.println(parentNode.getNodeType().toString());
        } else {
            parentTemp = (AbstractNode) connection.getSource();
            parentTemp.getOutgoingConnectionList().remove(connection);
            parentNode.getOutgoingConnectionList().add(connection);
            connection.setSource(parentNode);
        }
        // System.out.println("source2:" + connection.getSource().toString());
    }

}
