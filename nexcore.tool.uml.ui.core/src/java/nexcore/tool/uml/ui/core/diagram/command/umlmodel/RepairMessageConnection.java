/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.core.diagram.command.umlmodel;

import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.Relation;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.commands.Command;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.command.umlmodel</li>
 * <li>설  명 : RepairMessageConnection</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class RepairMessageConnection extends Command {

    /**
     * relation
     */
    private Relation relation;

    /**
     * @param relation
     *            the relation to set
     */
    public void setRelation(Relation relation) {
        this.relation = relation;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {
        Diagram diagram = (Diagram) relation.eContainer();

        AbstractNode source = (AbstractNode) relation.getSource();
        source.getOutgoingConnectionList().remove(relation);
        AbstractNode target = (AbstractNode) relation.getTarget();
        target.getIncomingConnectionList().remove(relation);
        diagram.getConnectionList().remove(relation);
        EcoreUtil.remove(relation);
    }
}
