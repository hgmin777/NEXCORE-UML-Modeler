/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.realization.sequencediagram.command;

import java.util.ArrayList;
import java.util.List;

import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.Line;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.realization.sequencediagram.edit.part.MessageEditPart;
import nexcore.tool.uml.ui.realization.sequencediagram.util.SequenceUtil;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.Message;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.command</li>
 * <li>설 명 : MoveIncludedMessageCommand</li>
 * <li>작성일 : 2010. 1. 16.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class MoveIncludedMessageCommand extends Command {

    /** diagram */
    private Diagram diagram;

    /**
     * moveYValue
     */
    private int newY;

    /**
     * 이전의 oldY
     */
    private int oldY;

    /** viewModel */
    private AbstractConnection connection;

    /** messageList */
    private List<AbstractConnection> messageList;

    /** margin */
    private final int margin = 10;

    /** insertIndex */
    private int insertIndex = -1;

    /**
     * MoveIncludedMessageCommand
     * @param editPart
     * @param location
     * @param messageList
     */
    public MoveIncludedMessageCommand(MessageEditPart editPart, Point location, List<AbstractConnection> messageList) {
        this.diagram = (Diagram) ((ScalableFreeformRootEditPart) editPart.getParent()).getContents().getModel();
        this.connection = (AbstractConnection) editPart.getModel();
        this.oldY = connection.getY();
        this.newY = location.y;
        this.messageList = messageList;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {

        try {
            connection.setY(newY);
            NotationNode behaviorNode = (NotationNode) connection.getTarget();
            behaviorNode.setY(newY);

            if (!(connection.getSource() instanceof Line)) {
                NotationNode sourceBehavior = (NotationNode) connection.getSource();
                SequenceUtil.addBehaviorHeight(diagram, sourceBehavior, messageList, (newY - oldY), margin);
            }

            if (!(behaviorNode.getParent() instanceof Line)) {
                NotationNode targetParentBehavior = (NotationNode) behaviorNode.getParent();
                SequenceUtil.addBehaviorHeight(diagram, targetParentBehavior, messageList, (newY - oldY), margin);
            }

            if (newY < oldY && isInsert()) {
                List<AbstractConnection> childrenMessageList = SequenceUtil.getChildrenMessage(connection);
                SequenceUtil.setInsertMessageLayout(diagram,
                    connection,
                    messageList,
                    childrenMessageList,
                    newY - oldY,
                    margin);

                int insertSifhtValue = SequenceUtil.getLowYValueByMessage(connection) - connection.getY();
                List<AbstractConnection> shiftList = new ArrayList<AbstractConnection>();
                for (int i = insertIndex; i < messageList.size(); i++) {
                    shiftList.add(messageList.get(i));
                }
                childrenMessageList.add(connection);
                SequenceUtil.sifhtMessageList(diagram, insertSifhtValue, shiftList, childrenMessageList, margin);

            } else {
                SequenceUtil.setMessageLayout(diagram, connection, messageList, newY - oldY, margin);
            }
            SequenceUtil.refreshMessageOrder(diagram);
            SequenceUtil.refreshUmlLifelineCoveredBysOrder(connection, (Message) connection.getUmlModel());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * isInsert
     * 
     * @return boolean
     */
    private boolean isInsert() {

        int index = 0;

        AbstractConnection message;
        for (int i = 0; i < messageList.size(); i++) {
            message = messageList.get(i);
            if (message.equals(connection)) {
                index = i;
                break;
            }
        }

        List<AbstractConnection> newMessageList = SequenceUtil.ascSortedMessageListByYValue(diagram.getConnectionList());
        for (int i = 0; i < newMessageList.size(); i++) {
            message = newMessageList.get(i);
            if (message.equals(connection)) {
                insertIndex = i;
                if (index == i) {
                    return false;
                } else {
                    return true;
                }
            }
        }

        return false;
    }

}
