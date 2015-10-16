/*
 * Copyright (c) 2010 SK C&C Co., Ltd. All rights reserved.
 * 
 * This software is the confidential and proprietary information of SK C&C. You
 * shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK
 * C&C.
 */

package nexcore.tool.mdd.core.registry.container;

import nexcore.tool.mdd.core.extension.ISemanticModelHandler;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.mdd.core</li>
 * <li>서브 업무명 : nexcore.tool.mdd.core.registry.container</li>
 * <li>설 명 : ISemanticModelHandlerContainer</li>
 * <li>작성일 : 2010. 11. 19.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public interface ISemanticModelHandlerContainer {

    /**
     * 아이디 설정
     * 
     * @param id
     *            void
     */
    public void setId(String id);

    /**
     * 아이디 반환
     * 
     * @return String
     */
    public String getId();

    /**
     * 라벨 설정
     * 
     * @param label
     *            void
     */
    public void setLabel(String label);

    /**
     * 라벨 반환
     * 
     * @return String
     */
    public String getLabel();

    /**
     * 시맨틱 모델 핸들러 설정
     * 
     * @param semanticModelHandler
     *            void
     */
    public void setModelHandler(ISemanticModelHandler semanticModelHandler);

    /**
     * 시맨틱 모델 핸들러 반환
     * 
     * @return ISemanticModelHandler
     */
    public ISemanticModelHandler getModelHandler();

}
