/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.manager.extension.registry;

import java.util.List;

import nexcore.tool.uml.manager.extension.AbstractPrecedingInitializer;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.manager</li>
 * <li>서브 업무명 : nexcore.tool.uml.manager.extension.registry</li>
 * <li>설  명 : IPrecedingInitializerWrapper</li>
 * <li>작성일 : 2010. 4. 5.</li>
 * <li>작성자 : 최윤석 </li>
 * </ul>
 */
public interface IPrecedingInitializerWrapper {

    /**
     * 선행 초기화 반환
     * 
     * @return AbstractPrecedingInitializer
     */
    public AbstractPrecedingInitializer getInitializer();

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

    /**
     * 선행 초기자가 피드백 하는 단일 객체 반환
     * 
     * @return Object
     */
    public Object getFeedback();

    /**
     * 선행 초기자가 피드백 하는 복수 객체 반환
     * 
     * @return List<Object>
     */
    public List<Object> getFeedbacks();

}
