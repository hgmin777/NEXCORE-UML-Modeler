/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.core.regexp;

/**
 * 현재는 사용하지 않는 클래스. 차후 구현에 따라 사용될 여지가 있어 제거하지 않고 있음.
 * 
 * <ul>
 * <li>업무 그룹명 : nexbuild.foundation.core</li>
 * <li>서브 업무명 : nexbuild.foundation.core.regexp</li>
 * <li>설 명 : RegExpPartType</li>
 * <li>작성일 : 2008. 04. 12</li>
 * <li>작성자 : 05531</li>
 * </ul>
 */

public class RegExpPartType {

    /**
     * 타입 ID
     */
    private String id;

    /**
     * 타입 Display Name
     */
    private String name;

    /**
     * RegExpPartType
     * @param id
     * @param name
     */
    public RegExpPartType(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * RegExpPartType
     */
    public RegExpPartType() {
    }

    /**
     * getId
     *  
     * @return String
     */
    public String getId() {
        return id;
    }

    /**
     * setId
     *  
     * @param id void
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * getName
     *  
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * setName
     *  
     * @param name void
     */
    public void setName(String name) {
        this.name = name;
    }
}
