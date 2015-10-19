/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.core.util;

/**
 * 
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.core.util</li>
 * <li>설 명 : UMLNexcoreLoginEvent</li>
 * <li>작성일 : 2011. 6. 28.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public class UMLNexcoreLoginEvent {
    /**
     * 로그인 함
     */
    public final static int LOG_IN = 1;

    /**
     * 로그아웃 함
     */
    public final static int LOG_OUT = 2;

    /**
     * type
     */
    int type;

    /**
     * @param logIn
     */
    public UMLNexcoreLoginEvent(int type) {
        this.type = type;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(int type) {
        this.type = type;
    }
}
