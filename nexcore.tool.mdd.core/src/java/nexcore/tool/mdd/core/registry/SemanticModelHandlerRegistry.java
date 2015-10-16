/*
 * Copyright (c) 2010 SK C&C Co., Ltd. All rights reserved.
 * 
 * This software is the confidential and proprietary information of SK C&C. You
 * shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK
 * C&C.
 */

package nexcore.tool.mdd.core.registry;

import java.util.ArrayList;
import java.util.List;

import nexcore.tool.mdd.core.MDDCoreConstant;
import nexcore.tool.mdd.core.extension.ISemanticModelHandler;
import nexcore.tool.mdd.core.registry.container.ISemanticModelHandlerContainer;
import nexcore.tool.mdd.core.registry.container.impl.SemanticModelHandlerContainerImpl;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.mdd.core</li>
 * <li>서브 업무명 : nexcore.tool.mdd.core.registry</li>
 * <li>설 명 : SemanticModelHandlerRegistry</li>
 * <li>작성일 : 2010. 11. 22.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class SemanticModelHandlerRegistry {

    /** 시맨틱 모델 핸들러 레지스트리 */
    private static SemanticModelHandlerRegistry instance;

    /** 시맨틱 모델 핸들러 컨테이너 목록 */
    private static List<ISemanticModelHandlerContainer> semanticModelHandlerContainerList;

    /** 시맨틱 모델 핸들러 컨테이너 */
    private static ISemanticModelHandlerContainer semanticModelHandlerContainer;

    /**
     * 생성자
     */
    private SemanticModelHandlerRegistry() {
    }

    /**
     * 시맨틱 모델 핸들러 레지스트리 반환
     * 
     * @return SemanticModelHandlerRegistry
     */
    public static SemanticModelHandlerRegistry getInstance() {
        if (instance == null) {
            instance = new SemanticModelHandlerRegistry();

            instance.init();
        }

        return instance;
    }

    /**
     * 초기화
     * 
     * void
     */
    private void init() {
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        IConfigurationElement config[] = registry.getConfigurationElementsFor(MDDCoreConstant.NEXCORE_TOOL_MDD_CORE_SEMANTIC_MODEL_HANDLER);

        if (config != null) {
            semanticModelHandlerContainerList = new ArrayList<ISemanticModelHandlerContainer>();

            for (IConfigurationElement element : config) {
                semanticModelHandlerContainer = new SemanticModelHandlerContainerImpl();

                try {
                    semanticModelHandlerContainer.setId(element.getAttribute(MDDCoreConstant.EXTENSION_ELEMENT_ID));
                    semanticModelHandlerContainer.setLabel(element.getAttribute(MDDCoreConstant.EXTENSION_ELEMENT_LABEL));
                    semanticModelHandlerContainer.setModelHandler((ISemanticModelHandler) element.createExecutableExtension(MDDCoreConstant.EXTENSION_ELEMENT_IMPLEMENT_INTERFACE));

                    semanticModelHandlerContainerList.add(semanticModelHandlerContainer);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * ID에 해당하는 시맨틱 모델 핸들러 반환
     * 
     * @param id
     * @return ISemanticModelHandler
     */
    public ISemanticModelHandler getHandlerInstance(String id) {
        if (semanticModelHandlerContainerList != null && !semanticModelHandlerContainerList.isEmpty()) {
            for (ISemanticModelHandlerContainer semanticModelHandlerContainer : semanticModelHandlerContainerList) {
                if (semanticModelHandlerContainer.getId().equals(id)
                    && semanticModelHandlerContainer.getModelHandler() != null) {
                    return semanticModelHandlerContainer.getModelHandler();
                }
            }
        }

        return null;
    }

}
