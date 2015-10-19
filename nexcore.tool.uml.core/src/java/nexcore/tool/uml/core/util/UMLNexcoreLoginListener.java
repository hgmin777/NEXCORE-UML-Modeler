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
 * <li>설 명 : UMLNexcoreLoginListener</li>
 * <li>작성일 : 2011. 6. 28.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */

public interface UMLNexcoreLoginListener {
    
    /**
     * changed
     *  
     * @param event void
     */
    public void changed(UMLNexcoreLoginEvent event);
}
