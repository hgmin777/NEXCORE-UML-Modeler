/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.mdd.core.registry.container.impl;

import nexcore.tool.mdd.core.extension.ISemanticModelHandler;
import nexcore.tool.mdd.core.registry.container.ISemanticModelHandlerContainer;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.mdd.core</li>
 * <li>서브 업무명 : nexcore.tool.mdd.core.registry.container.impl</li>
 * <li>설 명 : SemanticModelHandlerContainerImpl</li>
 * <li>작성일 : 2010. 11. 19.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class SemanticModelHandlerContainerImpl implements ISemanticModelHandlerContainer {

    /** 아이디 */
    private String id;

    /** 라벨 */
    private String label;

    /** 시맨틱 모델 핸들러 */
    private ISemanticModelHandler semanticModelHandler;

    /**
     * @see nexcore.tool.mdd.core.registry.container.ISemanticModelHandlerContainer#setId(java.lang.String)
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @see nexcore.tool.mdd.core.registry.container.ISemanticModelHandlerContainer#getId()
     */
    public String getId() {
        return id;
    }

    /**
     * @see nexcore.tool.mdd.core.registry.container.ISemanticModelHandlerContainer#setLabel(java.lang.String)
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @see nexcore.tool.mdd.core.registry.container.ISemanticModelHandlerContainer#getLabel()
     */
    public String getLabel() {
        return label;
    }

    /**
     * @see nexcore.tool.mdd.core.registry.container.ISemanticModelHandlerContainer#setModelHandler(nexcore.tool.mdd.core.extension.ISemanticModelHandler)
     */
    public void setModelHandler(ISemanticModelHandler semanticModelHandler) {
        this.semanticModelHandler = semanticModelHandler;
    }

    /**
     * @see nexcore.tool.mdd.core.registry.container.ISemanticModelHandlerContainer#getModelHandler()
     */
    public ISemanticModelHandler getModelHandler() {
        return semanticModelHandler;
    }

}
