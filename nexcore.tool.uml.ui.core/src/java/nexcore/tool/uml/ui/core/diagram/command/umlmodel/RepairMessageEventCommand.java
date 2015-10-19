/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.core.diagram.command.umlmodel;

import nexcore.tool.uml.core.log.Log;

import org.eclipse.gef.commands.Command;
import org.eclipse.uml2.uml.MessageEvent;
import org.eclipse.uml2.uml.ReceiveOperationEvent;
import org.eclipse.uml2.uml.SendOperationEvent;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.command.umlmodel</li>
 * <li>설  명 : RepairMessageEventCommand</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class RepairMessageEventCommand extends Command {

    /**
     * msesageEvent
     */
    private MessageEvent msesageEvent;

    /**
     * @param relation
     *            the relation to set
     */
    public void setMessageEvent(MessageEvent msesageEvent) {
        this.msesageEvent = msesageEvent;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {
        if (null == this.msesageEvent) {
            return;
        }
        String strName = "";
        try {
            if (this.msesageEvent instanceof SendOperationEvent) {
                if (null == ((SendOperationEvent) this.msesageEvent).getOperation()) {
                    strName = "undefined";
                } else {
                    strName = ((SendOperationEvent) this.msesageEvent).getOperation().getName();
                }
            } else if (this.msesageEvent instanceof ReceiveOperationEvent) {
                if (null == ((ReceiveOperationEvent) this.msesageEvent).getOperation()) {
                    strName = "undefined";
                } else {
                    strName = ((ReceiveOperationEvent) this.msesageEvent).getOperation().getName();
                }
            }
            this.msesageEvent.setName(strName);
        } catch (Exception error) {
            Log.error(error);
        }
    }
}
