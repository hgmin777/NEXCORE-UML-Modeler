/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.manager.command;

import java.util.List;

import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.LabelNode;
import nexcore.tool.uml.model.umldiagram.Relation;

import org.eclipse.gef.commands.Command;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityNode;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.manager</li>
 * <li>서브 업무명 : nexcore.tool.uml.manager.command</li>
 * <li>설  명 : DeleteUMLActivityNodeCommand</li>
 * <li>작성일 : 2009. 12. 28.</li>
 * <li>작성자 : 한승일 </li>
 * </ul>
 */
public class DeleteUMLActivityNodeCommand extends Command {

    /** ActivityNode */
    private ActivityNode activityNode;

    /**
     * DeleteUMLActivityNodeCommand
     * @param activityNode
     */
    public DeleteUMLActivityNodeCommand(ActivityNode activityNode) {
        this.activityNode = activityNode;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {
        if (null == this.activityNode.eContainer()) {
            return;
        }
        for (ActivityEdge edge : this.activityNode.getIncomings()) {
            edge.getSource().getOutgoings().remove(edge);
            deleteViewConnection(edge, false);
            UMLManager.deleteElement(edge);
        }

        for (ActivityEdge edge : this.activityNode.getOutgoings()) {
            edge.getTarget().getIncomings().remove(edge);
            deleteViewConnection(edge, true);
            UMLManager.deleteElement(edge);
        }

        deleteViewNode();
        UMLManager.deleteElement(this.activityNode);
    }

    /**
     * 
     * void
     */
    private void deleteViewNode() {
        List<AbstractView> list = UMLManager.getRelatedViewModel(this.activityNode);
        Diagram diagram = null;
        for (AbstractView view : list) {
            if (null == view.eContainer()) {
                continue;
            }
            if (view instanceof LabelNode) {
                UMLManager.deleteElement(view);
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
