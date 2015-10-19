/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.project.explorer.action;

import nexcore.alm.common.ui.action.AbstractRefreshSessionAction;
import nexcore.tool.uml.constant.DefinitionConstant;
import nexcore.tool.uml.core.util.UMLLoginController;
import nexcore.tool.uml.core.util.UMLNexcoreLoginEvent;
import nexcore.tool.uml.ui.core.registry.ResourceManager;

/**
 * 
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.action</li>
 * <li>설 명 : UMLSessionRefreshAction</li>
 * <li>작성일 : 2012. 4. 3.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public class UMLSessionRefreshAction extends AbstractRefreshSessionAction {
    String version = DefinitionConstant.UML_VERSION;
    
    /**
     * @see nexcore.alm.common.ui.action.AbstractRefreshSessionAction#execute()
     */
    @Override
    public void execute() {
        UMLLoginController.fireEvent(new UMLNexcoreLoginEvent(UMLNexcoreLoginEvent.LOG_OUT));
        ResourceManager.getInstance().clearStatusMap();
        ResourceManager.getInstance().openResource();
    }
}
