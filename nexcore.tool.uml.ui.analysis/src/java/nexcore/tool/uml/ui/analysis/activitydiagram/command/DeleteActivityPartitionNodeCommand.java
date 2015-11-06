/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.analysis.activitydiagram.command;

import java.util.ArrayList;
import java.util.List;

import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.NodeType;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.analysis</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.analysis.activitydiagram.command</li>
 * <li>설  명 : DeleteActivityPartitionNodeCommand</li>
 * <li>작성일 : 2011. 7. 12.</li>
 * <li>작성자 : Kang</li>
 * </ul>
 */
public class DeleteActivityPartitionNodeCommand extends Command {

    /**
     * parent
     */
    private AbstractNode parent;

    /**
     * 삭제할 노드
     */
    private ContainerNode node;

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {

        Diagram diagram = null;
        if( parent instanceof Diagram ) {
            diagram = (Diagram) parent;
        }
        if( null == diagram ) {
            return;
        }
        
        List<AbstractNode> nodeList = new ArrayList<AbstractNode>();
        nodeList = node.getNodeList();
        
        List<AbstractConnection> incomingList = new ArrayList<AbstractConnection>();
        List<AbstractConnection> outgoingList = new ArrayList<AbstractConnection>();
        List<ContainerNode> partitionList = new ArrayList<ContainerNode>();

        for( AbstractNode abstractNode : nodeList ) { 
            incomingList.addAll( abstractNode.getIncomingConnectionList() );
            outgoingList.addAll( abstractNode.getOutgoingConnectionList() );
        }
        for( AbstractConnection connection : incomingList ) {
            diagram.getConnectionList().remove(connection);
            node.getConnectionList().remove(connection);
            UMLManager.deleteElement(connection.getUmlModel());
            UMLManager.deleteElement(connection);

            AbstractNode sourceNode = (AbstractNode) connection.getSource();
            sourceNode.getOutgoingConnectionList().remove(connection);
        }
        for( AbstractConnection connection : outgoingList ) {
            diagram.getConnectionList().remove(connection);
            node.getConnectionList().remove(connection);
            UMLManager.deleteElement(connection.getUmlModel());
            UMLManager.deleteElement(connection);
            
            AbstractNode targetNode = (AbstractNode) connection.getTarget();
            targetNode.getIncomingConnectionList().remove(connection);
        }
        
        for( int i = nodeList.size() - 1; i >= 0; i-- ) { 
            AbstractNode abstractNode = nodeList.get(i);
            diagram.getNodeList().remove(abstractNode);
            node.getNodeList().remove(abstractNode);
            UMLManager.deleteElement(abstractNode.getUmlModel());
            UMLManager.deleteElement(abstractNode);
        }
        
        
        
        for( AbstractNode abstractNode : diagram.getNodeList() ) {
            if( abstractNode instanceof ContainerNode ) {
                ContainerNode containerNode = (ContainerNode) abstractNode;
                if( NodeType.ACTIVITY_PARTITION.equals(containerNode.getNodeType()) ) {
                    partitionList.add(containerNode);
                }
            }
        }
        
        Rectangle bounds = new Rectangle();
        bounds.setLocation( node.getX(), node.getY() );
        bounds.setSize( node.getWidth(), node.getHeight() );
        int indexOfCurrentNode = partitionList.indexOf(node);
        
        UMLManager.deleteElement(node.getUmlModel());
        UMLManager.deleteElement(node);
        
        if( partitionList.size() - 1 != indexOfCurrentNode ) {
            ContainerNode nextContainerNode = partitionList.get( indexOfCurrentNode + 1 );
            nextContainerNode.setX(bounds.x);
            nextContainerNode.setWidth( nextContainerNode.getWidth() + bounds.width );
        }
    }

    /**
     * @return the parent
     */
    public AbstractNode getParent() {
        return parent;
    }

    /**
     * 삭제 액션 Diagram 설정
     * 
     * @param model
     *            void
     */
    public void setParent(Object model) {
        if (model instanceof AbstractNode) {
            parent = (AbstractNode) model;
        } else {
            parent = null;
        }
    }

    /**
     * 삭제할 node 반환
     * 
     * @return the node
     */
    public AbstractNode getNode() {
        return node;
    }

    /**
     * 삭제될 node 저장.
     * 
     * @param model
     *            void
     */
    public void setNode(Object model) {
        if (model instanceof AbstractNode) {
            node = (ContainerNode) model;
        } else {
            node = null;
        }
    }
}
