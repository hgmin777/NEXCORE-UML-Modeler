/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.core.diagram.command;

import nexcore.tool.uml.model.umldiagram.BendPoint;
import nexcore.tool.uml.model.umldiagram.Relation;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.command</li>
 * <li>설 명 : DeleteBendpointCommand</li>
 * <li>작성일 : 2009. 12. 22.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class DeleteBendpointCommand extends BendpointCommand {

    /** tempBendpoint */
    @SuppressWarnings("unused")
    private BendPoint tempBendpoint;

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        tempBendpoint = ((nexcore.tool.uml.model.umldiagram.Relation) relation).getBendPointList().get(getIndex());
        ((Relation) relation).getBendPointList().remove(getIndex());
    }
}
