/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.realization.sequencediagram.edit.policy;

import nexcore.tool.uml.ui.realization.sequencediagram.figure.LineFigure;

import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.handles.MoveHandle;
import org.eclipse.gef.handles.MoveHandleLocator;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.edit.policy</li>
 * <li>설  명 : SequenceLineMoveHandle</li>
 * <li>작성일 : 2011. 5. 3.</li>
 * <li>작성자 : SKCCADMIN</li>
 * </ul>
 */
public class SequenceLineMoveHandle extends MoveHandle {

    /**
     * SequenceLineMoveHandle
     * @param owner
     */
    public SequenceLineMoveHandle(GraphicalEditPart owner) {
        super(owner);
        if(owner.getFigure() instanceof LineFigure){
            setLocator(new MoveHandleLocator(((LineFigure)owner.getFigure()).getLine()));
        }
    }

}
