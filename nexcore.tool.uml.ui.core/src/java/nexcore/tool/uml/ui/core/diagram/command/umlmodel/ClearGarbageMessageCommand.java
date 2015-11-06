/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.core.diagram.command.umlmodel;

import nexcore.tool.uml.manager.UMLManager;

import org.eclipse.gef.commands.Command;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageOccurrenceSpecification;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.command.umlmodel</li>
 * <li>설 명 : ClearGarbageMessageCommand</li>
 * <li>작성일 : 2009. 12. 28.</li>
 * <li>작성자 : 한승일</li>
 * </ul>
 */
public class ClearGarbageMessageCommand extends Command {

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {
        MessageOccurrenceSpecification sendEventSpec = null;
        MessageOccurrenceSpecification receiveEventSpec = null;
        Event sendEvent = null;
        Event receiveEvent = null;

        sendEventSpec = (MessageOccurrenceSpecification) message.getSendEvent();
        receiveEventSpec = (MessageOccurrenceSpecification) message.getReceiveEvent();

        if (null != sendEventSpec) {
            for (Lifeline lifeline : sendEventSpec.getCovereds()) {
                // lifeline.getCoveredBys().remove(sendEventSpec);
            }
            sendEvent = sendEventSpec.getEvent();
        }

        if (null != receiveEventSpec) {
            for (Lifeline lifeline : receiveEventSpec.getCovereds()) {
                // lifeline.getCoveredBys().remove(receiveEventSpec);
            }
            receiveEvent = receiveEventSpec.getEvent();
        }

        UMLManager.deleteElement(sendEvent);
        UMLManager.deleteElement(receiveEvent);
        UMLManager.deleteElement(sendEventSpec);
        UMLManager.deleteElement(receiveEventSpec);

        UMLManager.deleteElement(message);

        // Log.error(EcoreUtil.getIdentification(message) + ":" +
        // message.getSendEvent() + ":" + message.getReceiveEvent()
        // + ":" + ((MessageOccurrenceSpecification)
        // message.getSendEvent()).getEvent() + ":"
        // + ((MessageOccurrenceSpecification)
        // message.getReceiveEvent()).getEvent());
    }

    /**
     * message
     */
    private Message message;

    /**
     * ClearGarbageMessageCommand
     * @param message
     */
    public ClearGarbageMessageCommand(Message message) {

        this.message = message;

    }

}
