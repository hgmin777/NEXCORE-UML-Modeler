/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.diagram.extension;

import org.eclipse.gef.EditPartFactory;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.extension</li>
 * <li>설  명 : IUMLDiagramApplication</li>
 * <li>작성일 : 2010. 1. 14.</li>
 * <li>작성자 : 허원진</li>
 * </ul>
 */
public interface IUMLDiagramApplication {

    /**
     * @return the factory
     */
    public EditPartFactory getFactory();

    /**
     * @return the id
     */
    public String getId();

    /**
     * @return the name
     */
    public String getName();

}
