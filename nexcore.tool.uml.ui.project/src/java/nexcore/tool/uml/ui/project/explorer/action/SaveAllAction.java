/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.project.explorer.action;

import nexcore.tool.mdd.core.extension.IDomainModelHandler;
import nexcore.tool.uml.manager.utility.DomainModelHandlerUtil;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.ui.core.UICoreConstant;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.action.IAction;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.action</li>
 * <li>설 명 : SaveAllAction</li>
 * <li>작성일 : 2009. 12. 11.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
public class SaveAllAction extends CommonActionDelegate {

    /**
     * @see nexcore.tool.uml.ui.project.explorer.action.CommonActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    @Override
    public void run(IAction action) {
        // UMLDomain umlDomain = DomainRegistry.getUMLDomain();
        IDomainModelHandler umlDomain = DomainRegistry.getUMLDomain();

        EList<Resource> list = umlDomain.getResourceSet().getResources();
        for (Resource resource : list) {
            if (resource.getURI().toString().endsWith(UICoreConstant.PROJECT_CONSTANTS__RELATION)) {
                DomainModelHandlerUtil.save(resource, true);
            } else {
                DomainModelHandlerUtil.save(resource, false);
            }

        }
        // RND:refresh()
        // ViewerRegistry.getViewer().refresh();
        // ViewerRegistry.getViewer().setSelection(
        // new StructuredSelection(FocusRegistry.getFocused()));
        
//        RelationManager.getInstance().save();
    }

}
