/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.project.explorer.action;

import java.util.Iterator;

import nexcore.tool.uml.ui.core.project.ClosedTreeNode;
import nexcore.tool.uml.ui.core.registry.ResourceManager;
import nexcore.tool.uml.ui.core.util.ProjectUtil;

import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;

/**
 * 
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.action</li>
 * <li>설  명 : ClosedTreeOpenAction</li>
 * <li>작성일 : 2012. 7. 5.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public class ClosedTreeOpenAction extends CommonActionDelegate {

    /**
     * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    public void run(IAction action) {
        if (selection instanceof IStructuredSelection) {
            IStructuredSelection structuredSelection = (IStructuredSelection) selection;
            
            ResourceManager.getInstance().addResourceLoadedAdapter();
            for (Iterator<?> iterator = structuredSelection.iterator(); iterator.hasNext();) {
                Object object = iterator.next();
                
                if (object instanceof ClosedTreeNode) {
                    final IResource resource = ((ClosedTreeNode) object).getResource();
                    URI uri = URI.createURI(resource.getFullPath().toString());
                    ResourceManager.getInstance().setActivation(uri, true);
                    
                }
            }
            Display.getDefault().syncExec(new Runnable() {
                
                @Override
                public void run() {
                    ProjectUtil.refreshWholeExplorer();
                }
            });
            
            ResourceManager.getInstance().removeResourceLoadedAdapter();
        }
    }
}
