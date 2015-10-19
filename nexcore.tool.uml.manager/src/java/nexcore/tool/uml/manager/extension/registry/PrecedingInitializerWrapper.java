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
 * <li>설 명 : PrecedingInitializerWrapper</li>
 * <li>작성일 : 2010. 4. 5.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class PrecedingInitializerWrapper implements IPrecedingInitializerWrapper {

    /** 선행 초기화 */
    private AbstractPrecedingInitializer precedingInitializer;

    /** 아이디 */
    private String id;

    /** 라벨 */
    private String label;

    /**
     * 선행 초기화 설정
     * 
     * @param precedingInitializer
     *            the precedingInitializer to set
     */
    public void setInitializer(AbstractPrecedingInitializer precedingInitializer) {
        this.precedingInitializer = precedingInitializer;
    }

    /**
     * 선행 초기화 반환
     * 
     * @see nexcore.tool.uml.manager.extension.registry.IPrecedingInitializerWrapper#getInitializer()
     */
    public AbstractPrecedingInitializer getInitializer() {
        return this.precedingInitializer;
    }

    /**
     * 아이디 설정
     * 
     * @param id
     *            the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 아이디 반환
     * 
     * @see nexcore.tool.uml.manager.extension.registry.IPrecedingInitializerWrapper.core.infrastructure.extension.IMDACoreActionWrapper#getId()
     */
    public String getId() {
        return this.id;
    }

    /**
     * 라벨 설정
     * 
     * @param label
     *            void
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * 라벨 반환
     * 
     * @see nexcore.tool.uml.manager.extension.registry.IPrecedingInitializerWrapper.core.infrastructure.extension.IMDACoreActionWrapper#getLabel()
     */
    public String getLabel() {
        return this.label;
    }

    /**
     * 선행 초기자가 피드백 하는 단일 객체 반환
     * 
     * @see nexcore.tool.uml.manager.extension.registry.IPrecedingInitializerWrapper#getFeedback()
     */
    public Object getFeedback() {
        return precedingInitializer.getFeedback();
    }

    /**
     * 선행 초기자가 피드백 하는 복수 객체 반환
     * 
     * @see nexcore.tool.uml.manager.extension.registry.IPrecedingInitializerWrapper#getFeedbacks()
     */
    public List<Object> getFeedbacks() {
        return precedingInitializer.getFeedbacks();
    }

}
