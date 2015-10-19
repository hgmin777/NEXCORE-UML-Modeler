/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.manager.command;

import java.util.List;

import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.Relation;

import org.eclipse.gef.commands.Command;
import org.eclipse.uml2.uml.ActivityEdge;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.manager</li>
 * <li>서브 업무명 : nexcore.tool.uml.manager.command</li>
 * <li>설  명 : DeleteUMLActivityEdgeCommand</li>
 * <li>작성일 : 2010. 2. 25.</li>
 * <li>작성자 : 한승일 </li>
 * </ul>
 */
public class DeleteUMLActivityEdgeCommand extends Command {

    /** ActivityNode */
    private ActivityEdge activityEdge;

    /**
     * DeleteUMLActivityEdgeCommand
     * @param activityEdge
     */
    public DeleteUMLActivityEdgeCommand(ActivityEdge activityEdge) {
        this.activityEdge = activityEdge;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {
        if (null == this.activityEdge.eContainer()) {
            return;
        }

        if (null != this.activityEdge.getSource()) {
            this.activityEdge.getSource().getOutgoings().remove(this.activityEdge);
        }

        if (null != this.activityEdge.getTarget()) {
            this.activityEdge.getTarget().getIncomings().remove(this.activityEdge);
        }

        deleteViewConnection(this.activityEdge);

        UMLManager.deleteElement(this.activityEdge);

        deleteViewModel();
        UMLManager.deleteElement(this.activityEdge);
    }

    /**
     * 
     * void
     */
    private void deleteViewModel() {
        List<AbstractView> list = UMLManager.getRelatedViewModel(this.activityEdge);
        Diagram diagram;
        for (AbstractView view : list) {
            if (null == view.eContainer() || !(view.eContainer() instanceof Diagram)) {
                continue;
            }
            diagram = (Diagram) view.eContainer();
            if (view instanceof Relation) {
                diagram.getConnectionList().remove(view);
            } else if (view instanceof AbstractNode) {
                diagram.getNodeList().remove(view);
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
    private void deleteViewConnection(ActivityEdge edge) {
        Diagram diagram;
        Relation relation;
        List<AbstractView> list = UMLManager.getRelatedViewModel(edge);
        for (AbstractView view : list) {
            if (null == view.eContainer() || !(view.eContainer() instanceof Diagram)) {
                continue;
            }
            diagram = (Diagram) view.eContainer();
            if (view instanceof Relation) {
                relation = (Relation) view;
                ((AbstractNode) relation.getTarget()).getIncomingConnectionList().remove(relation);
                ((AbstractNode) relation.getSource()).getOutgoingConnectionList().remove(relation);

                diagram.getConnectionList().remove(view);
            }
            UMLManager.deleteElement(view);
        }
    }
}
