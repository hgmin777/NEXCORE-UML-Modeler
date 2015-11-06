/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.core.util;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.core.util</li>
 * <li>설 명 : IDelegate</li>
 * <li>작성일 : 2009. 12. 3.</li>
 * <li>작성자 : 한승일</li>
 * Delegate 처리를 위한 메서드 Delegate인터페이스
 * </ul>
 */
public interface IDelegate {
    /**
     * Thin wrapper in invoke
     * 
     * @param args
     *            possibly null array or args - null says none
     * @return possibly null return - primitive types are wrapped
     */
    public Object invoke(Object[] args);

    /**
     * convenience call for 1 arg case
     * 
     * @param arg
     *            possibly null argument
     * @return possibly null return - primitive types are wrapped
     */
    public Object invoke(Object arg);

    /**
     * convenience call for 2 arg case
     * 
     * @param arg1
     *            possibly null argument
     * @param arg2
     *            possibly null argument
     * @return possibly null return - primitive types are wrapped
     */
    public Object invoke(Object arg1, Object arg2);

    /**
     * convenience call for no arg case
     * 
     * @return possibly null return - primitive types are wrapped
     */
    public Object invoke();
}
