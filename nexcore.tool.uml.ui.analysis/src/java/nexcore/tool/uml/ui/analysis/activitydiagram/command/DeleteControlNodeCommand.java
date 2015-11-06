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
import nexcore.tool.uml.manager.util.DiagramUtil;
import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.LabelNode;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.model.umldiagram.Relation;

import org.eclipse.gef.commands.Command;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityNode;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.analysis</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.analysis.activitydiagram.command</li>
 * <li>설 명 : DeleteControlNodeCommand</li>
 * <li>작성일 : 2011. 1. 31.</li>
 * <li>작성자 : SKCCADMIN</li>
 * </ul>
 */
public class DeleteControlNodeCommand extends Command {

    /**
     * parent
     */
    private AbstractNode parent;

    /**
     * 삭제할 노드
     */
    private NotationNode node;

    /**
     * 함께 삭제할 connectionList
     */
    private List<AbstractConnection> removedConnectionList = new ArrayList<AbstractConnection>();

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {

        List<AbstractConnection> connectionList = new ArrayList<AbstractConnection>();
        if (parent instanceof Diagram) {
            Diagram diagram = (Diagram) parent;
            diagram.getNodeList().remove(node);
            if (!node.getLabels().isEmpty()) {
                diagram.getNodeList().remove(node.getLabels().get(0));
            }

            AbstractConnection connection;
            for (int index = 0; index < node.getIncomingConnectionList().size(); index++) {
                connection = node.getIncomingConnectionList().get(index);
                DiagramUtil.detachSourceOfConnection(connection);
                diagram.getConnectionList().remove(connection);
                connectionList.add(connection);
            }
            for (int index = 0; index < node.getOutgoingConnectionList().size(); index++) {
                connection = node.getOutgoingConnectionList().get(index);
                DiagramUtil.detachTargetOfConnection(connection);
                diagram.getConnectionList().remove(connection);
                connectionList.add(connection);
            }
        } else if (parent instanceof ContainerNode) {
            ContainerNode containerNode = (ContainerNode) parent;
            containerNode.getNodeList().remove(node);
            if (!node.getLabels().isEmpty()) {
                containerNode.getNodeList().remove(node.getLabels().get(0));
            }

            AbstractConnection connection;
            for (int index = 0; index < node.getIncomingConnectionList().size(); index++) {
                connection = node.getIncomingConnectionList().get(index);
                DiagramUtil.detachSourceOfConnection(connection);
                containerNode.getConnectionList().remove(connection);
                connectionList.add(connection);
            }
            for (int index = 0; index < node.getOutgoingConnectionList().size(); index++) {
                connection = node.getOutgoingConnectionList().get(index);
                DiagramUtil.detachTargetOfConnection(connection);
                containerNode.getConnectionList().remove(connection);
                connectionList.add(connection);
            }
        }
        deleteActivityUMLModel();

        UMLManager.deleteElement(node);
        removedConnectionList = connectionList;
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
            node = (NotationNode) model;
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
            diagram.getNodeList().add(node.getLabels().get(0));

            AbstractConnection connection;
            for (int index = 0; index < removedConnectionList.size(); index++) {
                connection = removedConnectionList.get(index);
                diagram.getConnectionList().add(connection);
                DiagramUtil.attachSourceOfConnection(connection);
                DiagramUtil.attachTargetOfConnection(connection);
            }
        } else if (parent instanceof ContainerNode) {
            ContainerNode containerNode = (ContainerNode) parent;
            containerNode.getNodeList().add(node);
            containerNode.getNodeList().add(node.getLabels().get(0));

            AbstractConnection connection;
            for (int index = 0; index < removedConnectionList.size(); index++) {
                connection = removedConnectionList.get(index);
                containerNode.getConnectionList().add(connection);
                DiagramUtil.attachSourceOfConnection(connection);
                DiagramUtil.attachTargetOfConnection(connection);
            }
        }
    }

    /**
     * 
     * void
     */
    public void deleteActivityUMLModel() {
        if (null == this.node.getUmlModel().eContainer()) {
            return;
        }
        for (ActivityEdge edge : ((ActivityNode) node.getUmlModel()).getIncomings()) {
            edge.getSource().getOutgoings().remove(edge);
            deleteViewConnection(edge, false);
            UMLManager.deleteElement(edge);
        }

        for (ActivityEdge edge : ((ActivityNode) node.getUmlModel()).getOutgoings()) {
            edge.getTarget().getIncomings().remove(edge);
            deleteViewConnection(edge, true);
            UMLManager.deleteElement(edge);
        }

        deleteViewNode();
        UMLManager.deleteElement(this.node.getUmlModel());
    }

    /**
     * 
     * void
     */
    private void deleteViewNode() {
        List<AbstractView> list = UMLManager.getRelatedViewModel(this.node.getUmlModel());
        Diagram diagram = null;
        for (AbstractView view : list) {
            if (null == view.eContainer()) {
                continue;
            }
            if (view instanceof LabelNode) {
                continue;
            }
            if (view.eContainer() instanceof Diagram) {
                diagram = (Diagram) view.eContainer();
            }
            if (diagram != null) {
                if (view instanceof Relation) {
                    diagram.getConnectionList().remove(view);
                } else if (view instanceof AbstractNode) {
                    diagram.getNodeList().remove(view);
                }
            }
            UMLManager.deleteElement(view);
        }
    }

    /**
     * 
     * 
     * @param edge
     * @param isSource
     *            void
     */
    private void deleteViewConnection(ActivityEdge edge, boolean isSource) {
        Diagram diagram;
        Relation relation;
        List<AbstractView> list = UMLManager.getRelatedViewModel(edge);
        for (AbstractView view : list) {
            if (null == view.eContainer() || view.eContainer() instanceof Relation) {
                continue;
            }
            diagram = (Diagram) view.eContainer();
            if (view instanceof Relation) {
                relation = (Relation) view;
                if (isSource) {
                    ((AbstractNode) relation.getTarget()).getIncomingConnectionList().remove(relation);
                } else {
                    ((AbstractNode) relation.getSource()).getOutgoingConnectionList().remove(relation);
                }
                diagram.getConnectionList().remove(view);
            } else if (view instanceof AbstractNode) {
                diagram.getNodeList().remove(view);
            }
            UMLManager.deleteElement(view);
        }
    }

}
