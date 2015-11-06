/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.manager.transaction;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.manager</li>
 * <li>서브 업무명 : nexcore.tool.uml.manager.transaction</li>
 * <li>설 명 : TransactionalAction</li>
 * <li>작성일 : 2010. 1. 8.</li>
 * <li>작성자 : 한승일</li>
 * </ul>
 */
public abstract class TransactionalAction {
    /**
     * doExecute
     *   void
     */
    public abstract void doExecute();
}
