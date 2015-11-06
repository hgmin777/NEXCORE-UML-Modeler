/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.core.diagram.command;

import java.util.ArrayList;
import java.util.List;

import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.manager.command.DeleteUMLElementCommandFactory;
import nexcore.tool.uml.manager.util.DiagramUtil;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.NotationNode;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.uml2.uml.Element;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.daigram.command</li>
 * <li>설 명 : DeleteNodeCommand</li>
 * <li>작성일 : 2009. 11. 16.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class DeleteNodeCommand extends Command {

    /**
     * parent
     */
    private AbstractNode parent;

    /**
     * 삭제할 노드
     */
    private AbstractNode node;

    /**
     * 함께 삭제할 connectionList
     */
    private List<AbstractConnection> removedConnectionList = new ArrayList<AbstractConnection>();

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        try {

            List<AbstractConnection> connectionList = new ArrayList<AbstractConnection>();
            List<AbstractConnection> connectionListForDeleteUMLELement = new ArrayList<AbstractConnection>();
            if (parent instanceof Diagram) {
                Diagram diagram = (Diagram) parent;
                diagram.getNodeList().remove(node);
                if (node instanceof NotationNode) {
                    diagram.getNodeList().removeAll(((NotationNode) node).getLabels());
                }

                AbstractConnection connection;
                for (int i = 0; i < node.getIncomingConnectionList().size(); i++) {
                    connection = node.getIncomingConnectionList().get(i);
                    DiagramUtil.detachSourceOfConnection(connection);
                    diagram.getConnectionList().remove(connection);
                    connectionList.add(connection);
                    
                    if(node.getUmlModel() == null || DomainUtil.isProxy(node.getUmlModel())){
                        connectionListForDeleteUMLELement.add(connection);
                    }
                }
                for (int i = 0; i < node.getOutgoingConnectionList().size(); i++) {
                    connection = node.getOutgoingConnectionList().get(i);
                    DiagramUtil.detachTargetOfConnection(connection);
                    diagram.getConnectionList().remove(connection);
                    connectionList.add(connection);
                    
                    if(node.getUmlModel() == null || DomainUtil.isProxy(node.getUmlModel())){
                        connectionListForDeleteUMLELement.add(connection);
                    }
                }
            } else if (parent instanceof ContainerNode) {
                ContainerNode containerNode = (ContainerNode) parent;
                containerNode.getNodeList().remove(node);

                AbstractConnection connection;
                for (int i = 0; i < node.getIncomingConnectionList().size(); i++) {
                    connection = node.getIncomingConnectionList().get(i);
                    DiagramUtil.detachSourceOfConnection(connection);
                    containerNode.getConnectionList().remove(connection);
                    connectionList.add(connection);
                    
                    if(node.getUmlModel() == null || DomainUtil.isProxy(node.getUmlModel())){
                        connectionListForDeleteUMLELement.add(connection);
                    }
                }
                for (int i = 0; i < node.getOutgoingConnectionList().size(); i++) {
                    connection = node.getOutgoingConnectionList().get(i);
                    DiagramUtil.detachTargetOfConnection(connection);
                    containerNode.getConnectionList().remove(connection);
                    connectionList.add(connection);
                    
                    if(node.getUmlModel() == null || DomainUtil.isProxy(node.getUmlModel())){
                        connectionListForDeleteUMLELement.add(connection);
                    }
                }
            }
            
            if(!NodeType.NOTE.equals(node.getNodeType()) && !NodeType.TEXT.equals(node.getNodeType())){
                CompoundCommand commandList = new CompoundCommand();
                for(AbstractConnection child : connectionListForDeleteUMLELement){
                    commandList.add( DeleteUMLElementCommandFactory.getCommand(child.getUmlModel()) );
                }
                commandList.execute();
            }
            
            if (node.getNodeType().equals(NodeType.ATTRIBUTE) || node.getNodeType().equals(NodeType.OPERATION)) {
                Command deleteCommand = DeleteUMLElementCommandFactory.getCommand((Element) node.getUmlModel());
                deleteCommand.execute();
            }
            UMLManager.deleteElement(node);
            removedConnectionList = connectionList;

        } catch (Exception e) {
            e.printStackTrace();
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
            node = (AbstractNode) model;
        } else {
            node = null;
        }
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        if (parent instanceof Diagram) {
            Diagram diagram = (Diagram) parent;
            diagram.getNodeList().add(node);

            AbstractConnection connection;
            for (int i = 0; i < removedConnectionList.size(); i++) {
                connection = removedConnectionList.get(i);
                diagram.getConnectionList().add(connection);
                DiagramUtil.attachSourceOfConnection(connection);
                DiagramUtil.attachTargetOfConnection(connection);
            }
        } else if (parent instanceof ContainerNode) {
            ContainerNode containerNode = (ContainerNode) parent;
            containerNode.getNodeList().add(node);

            AbstractConnection connection;
            for (int i = 0; i < removedConnectionList.size(); i++) {
                connection = removedConnectionList.get(i);
                containerNode.getConnectionList().add(connection);
                DiagramUtil.attachSourceOfConnection(connection);
                DiagramUtil.attachTargetOfConnection(connection);
            }
        }
    }
}
