/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.manager.extension;

import java.util.List;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.manager</li>
 * <li>서브 업무명 : nexcore.tool.uml.manager.extension</li>
 * <li>설 명 : AbstractPrecedingInitializer</li>
 * <li>작성일 : 2010. 4. 5.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public abstract class AbstractPrecedingInitializer {

    /**
     * 초기화 메소드 void
     */
    public void initialize() {
        setInitialInformation();
    }

    /**
     * 선행 초기화에서 해야 하는 작업을 정의 void
     */
    public abstract void setInitialInformation();

    /**
     * 선행 초기자가 피드백 하는 단일 객체를 반환하도록 정의
     * 
     * @return Object
     */
    public abstract Object getFeedback();

    /**
     * 선행 초기자가 피드백 하는 복수 객체를 반환하도록 정의
     * 
     * @return List<Object>
     */
    public abstract List<Object> getFeedbacks();

}
