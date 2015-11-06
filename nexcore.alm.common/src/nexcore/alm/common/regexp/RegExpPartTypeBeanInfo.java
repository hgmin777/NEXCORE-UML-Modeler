/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.alm.common.regexp;

/**
 * 현재는 사용하지 않는 클래스. 차후 구현에 따라 사용될 여지가 있어 제거하지 않고 있음.
 * 
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.core</li>
 * <li>서브 업무명 : nexcore.tool.core.regexp</li>
 * <li>설 명 : RegExpPartType</li>
 * <li>작성일 : 2008. 04. 12</li>
 * <li>작성자 : 05531</li>
 * </ul>
 */
public class RegExpPartTypeBeanInfo {

    /**
     * 타입 ID
     */
    private String id;

    /**
     * 타입 Display Name
     */
    private String name;

    public RegExpPartTypeBeanInfo(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public RegExpPartTypeBeanInfo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
