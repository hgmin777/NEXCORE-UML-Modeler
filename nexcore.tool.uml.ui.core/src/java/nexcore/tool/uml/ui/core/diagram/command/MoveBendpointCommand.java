/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.core.diagram.command;

import nexcore.tool.uml.model.umldiagram.BendPoint;
import nexcore.tool.uml.model.umldiagram.UMLDiagramFactory;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.command</li>
 * <li>설 명 : MoveBendpointCommand</li>
 * <li>작성일 : 2009. 12. 22.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class MoveBendpointCommand extends BendpointCommand {

    /** oldBendpoint */
    private BendPoint oldBendpoint;

    /** bendPoint */
    private BendPoint bendPoint;

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        setOldBendpoint((BendPoint) relation.getBendPointList().get(getIndex()));
        bendPoint = UMLDiagramFactory.eINSTANCE.createBendPoint();
        bendPoint.setFirstPosition(getFirstRelativeDimension());
        bendPoint.setSecondPosition(getSecondRelativeDimension());
        relation.getBendPointList().remove(getIndex());
        relation.getBendPointList().add(getIndex(), bendPoint);
        super.execute();
    }

    /**
     * getOldBendpoint
     *  
     * @return BendPoint
     */
    protected BendPoint getOldBendpoint() {
        return oldBendpoint;
    }

    /**
     * setOldBendpoint
     *  
     * @param bp void
     */
    public void setOldBendpoint(BendPoint bp) {
        oldBendpoint = bp;
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        super.undo();
        relation.getBendPointList().add(getIndex(), (BendPoint) getOldBendpoint());
    }
}
