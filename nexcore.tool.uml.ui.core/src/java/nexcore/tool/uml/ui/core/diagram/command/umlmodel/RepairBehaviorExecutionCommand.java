/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.core.diagram.command.umlmodel;

import org.eclipse.gef.commands.Command;
import org.eclipse.uml2.uml.BehaviorExecutionSpecification;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.command.umlmodel</li>
 * <li>설  명 : RepairBehaviorExecutionCommand</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class RepairBehaviorExecutionCommand extends Command {

    /**
     * behaviorExecutionSpecification
     */
    private BehaviorExecutionSpecification behaviorExecutionSpecification;

    /**
     * @param relation
     *            the relation to set
     */
    public void setBehaviorExecutionSpecification(BehaviorExecutionSpecification behaviorExecutionSpecification) {
        this.behaviorExecutionSpecification = behaviorExecutionSpecification;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {
        if (!(behaviorExecutionSpecification.getStart().equals(behaviorExecutionSpecification.getFinish()))) {
            behaviorExecutionSpecification.setStart(behaviorExecutionSpecification.getFinish());
        }
    }
}