/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.core.diagram.command.umlmodel;

import nexcore.tool.uml.core.log.Log;

import org.eclipse.gef.commands.Command;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.Property;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.command.umlmodel</li>
 * <li>설  명 : RepairPropertyCommand</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class RepairPropertyCommand extends Command {

    /**
     * property
     */
    private Property property;

    /**
     * @param relation
     *            the relation to set
     */
    public void setProperty(Property property) {
        this.property = property;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {
        if (null == this.property) {
            return;
        }
        if (!(this.property.eContainer() instanceof Collaboration)) {
            return;
        }
        try {
            this.property.setName(this.property.getType().getName());
        } catch (Exception error) {
            Log.error(error);
        }
    }
}
