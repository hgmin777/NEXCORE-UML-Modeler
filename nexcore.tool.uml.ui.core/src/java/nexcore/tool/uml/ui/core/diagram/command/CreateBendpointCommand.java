/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.core.diagram.command;

import nexcore.tool.uml.model.umldiagram.BendPoint;
import nexcore.tool.uml.model.umldiagram.UMLDiagramFactory;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.command</li>
 * <li>설 명 : CreateBendpointCommand</li>
 * <li>작성일 : 2009. 12. 22.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class CreateBendpointCommand extends BendpointCommand {

    /** bendPoint */
    private BendPoint bendPoint;

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {

        bendPoint = UMLDiagramFactory.eINSTANCE.createBendPoint();
        bendPoint.setFirstPosition(getFirstRelativeDimension());
        bendPoint.setSecondPosition(getSecondRelativeDimension());
        relation.getBendPointList().add(getIndex(), bendPoint);
        super.execute();
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        super.undo();
        relation.getBendPointList().remove(bendPoint);
    }
}
