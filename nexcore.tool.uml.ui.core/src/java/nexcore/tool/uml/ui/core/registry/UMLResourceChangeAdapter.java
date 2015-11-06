/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.core.registry;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceChangeEvent;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.registry</li>
 * <li>설 명 : UMLResourceChangeAdapter</li>
 * <li>작성일 : 2011. 10. 18.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public class UMLResourceChangeAdapter implements IUMLResourceChangeListener {

    /**
     * @see nexcore.tool.uml.ui.core.registry.IUMLResourceChangeListener#preProjectClose(org.eclipse.core.resources.IProject)
     */
    public void preProjectClose(IResourceChangeEvent event) {

    }

    /**
     * @see nexcore.tool.uml.ui.core.registry.IUMLResourceChangeListener#preProjectDelete(org.eclipse.core.resources.IProject)
     */
    public void preProjectDelete(IResourceChangeEvent event) {

    }

    /**
     * @see nexcore.tool.uml.ui.core.registry.IUMLResourceChangeListener#projectOpened(org.eclipse.core.resources.IProject)
     */
    public void projectOpened(IProject[] project) {

    }

}
