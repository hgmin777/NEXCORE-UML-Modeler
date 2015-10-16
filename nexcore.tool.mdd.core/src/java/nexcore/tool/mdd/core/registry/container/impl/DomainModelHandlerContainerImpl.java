/*
 * Copyright (c) 2010 SK C&C Co., Ltd. All rights reserved.
 * 
 * This software is the confidential and proprietary information of SK C&C. You
 * shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK
 * C&C.
 */

package nexcore.tool.mdd.core.registry.container.impl;

import nexcore.tool.mdd.core.extension.IDomainModelHandler;
import nexcore.tool.mdd.core.registry.container.IDomainModelHandlerContainer;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.mdd.core</li>
 * <li>서브 업무명 : nexcore.tool.mdd.core.registry.container.impl</li>
 * <li>설 명 : DomainModelHandlerContainerImpl</li>
 * <li>작성일 : 2010. 11. 19.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class DomainModelHandlerContainerImpl implements IDomainModelHandlerContainer {

    /** 아이디 */
    private String id;

    /** 라벨 */
    private String label;

    /** 도메인 모델 핸들러 */
    private IDomainModelHandler domainModelHandler;

    /**
     * @see nexcore.tool.mdd.core.registry.container.IDomainModelHandlerContainer#setId(java.lang.String)
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @see nexcore.tool.mdd.core.registry.container.IDomainModelHandlerContainer#getId()
     */
    public String getId() {
        return id;
    }

    /**
     * @see nexcore.tool.mdd.core.registry.container.IDomainModelHandlerContainer#setLabel(java.lang.String)
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @see nexcore.tool.mdd.core.registry.container.IDomainModelHandlerContainer#getLabel()
     */
    public String getLabel() {
        return label;
    }

    /**
     * @see nexcore.tool.mdd.core.registry.container.IDomainModelHandlerContainer#setModelHandler(nexcore.tool.mdd.core.extension.IDomainModelHandler)
     */
    public void setModelHandler(IDomainModelHandler domainModelHandler) {
        this.domainModelHandler = domainModelHandler;
    }

    /**
     * @see nexcore.tool.mdd.core.registry.container.IDomainModelHandlerContainer#getModelHandler()
     */
    public IDomainModelHandler getModelHandler() {
        return domainModelHandler;
    }

}
