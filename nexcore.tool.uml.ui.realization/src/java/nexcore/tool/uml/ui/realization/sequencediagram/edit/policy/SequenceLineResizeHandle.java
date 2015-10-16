/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.realization.sequencediagram.edit.policy;

import nexcore.tool.uml.ui.realization.sequencediagram.figure.LineFigure;

import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.handles.RelativeHandleLocator;
import org.eclipse.gef.handles.ResizeHandle;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.edit.policy</li>
 * <li>설  명 : SequceLineResizeHandle</li>
 * <li>작성일 : 2011. 5. 3.</li>
 * <li>작성자 : zerotae</li>
 * </ul>
 */
public class SequenceLineResizeHandle extends ResizeHandle {

    /**
     * @param owner
     * @param direction
     */
    public SequenceLineResizeHandle(GraphicalEditPart owner, int direction) {
        super(owner, direction);
        if(owner.getFigure() instanceof LineFigure){
            setLocator(new RelativeHandleLocator(((LineFigure)owner.getFigure()).getLine(), direction));
        }        
    }
    
}
