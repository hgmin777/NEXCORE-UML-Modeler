/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.project.explorer.action.registry;

import nexcore.tool.uml.ui.project.explorer.action.AbstractUMLExplorerExtendedAction;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.action.registry</li>
 * <li>설 명 : IUMLExplorerExtendedActionWrapper</li>
 * <li>작성일 : 2010. 11. 16.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public interface IUMLExplorerExtendedActionWrapper {

    /**
     * 액션 반환
     * 
     * @return AbstractUMLExplorerExtendedAction
     */
    public AbstractUMLExplorerExtendedAction getAction();

    /**
     * 아이디 반환
     * 
     * @return String
     */
    public String getId();

    /**
     * 라벨 반환
     * 
     * @return String
     */
    public String getLabel();

}
