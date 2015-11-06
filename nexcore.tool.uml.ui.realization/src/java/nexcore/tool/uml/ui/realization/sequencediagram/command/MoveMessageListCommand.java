/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.realization.sequencediagram.command;

import java.util.List;

import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.ui.realization.sequencediagram.edit.part.MessageEditPart;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.command</li>
 * <li>설  명 : MoveMessageListCommand</li>
 * <li>작성일 : 2010. 3. 23.</li>
 * <li>작성자 : 한승일</li>
 * </ul>
 */
public class MoveMessageListCommand extends Command {

    /**
     * moveYValue
     */
    private int diffY;

    /**
     * location
     */
    @SuppressWarnings("unused")
    private Point location;

    /** viewModel */
    private List<MessageEditPart> messageEditPartList;

    /**
     * MoveMessageListCommand
     * @param messageEditPartList
     * @param location
     * @param diff
     */
    public MoveMessageListCommand(List<MessageEditPart> messageEditPartList, Point location, int diff) {
        this.messageEditPartList = messageEditPartList;
        this.location = location;
        this.diffY = diff;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {
        MoveMessageCommand command;
        Point point;
        for (MessageEditPart editPart : this.messageEditPartList) {
            point = this.getLocationOfEditPart(editPart);
            point.y += this.diffY;
            command = new MoveMessageCommand(editPart, point);
            command.execute();
        }

        // SequenceUtil.refreshMessageOrder(diagram);

    }

    /**
     * getLocationOfEditPart
     *  
     * @param editPart
     * @return Point
     */
    private Point getLocationOfEditPart(MessageEditPart editPart) {
        AbstractConnection connection = (AbstractConnection) editPart.getModel();
        Point point = new Point();
        point.x = connection.getX();
        point.y = connection.getY();
        return point;
    }
}
