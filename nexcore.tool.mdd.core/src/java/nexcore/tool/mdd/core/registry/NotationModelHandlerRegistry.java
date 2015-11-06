/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */


package nexcore.tool.mdd.core.registry;

import java.util.ArrayList;
import java.util.List;

import nexcore.tool.mdd.core.MDDCoreConstant;
import nexcore.tool.mdd.core.extension.INotationModelHandler;
import nexcore.tool.mdd.core.registry.container.INotationModelHandlerContainer;
import nexcore.tool.mdd.core.registry.container.impl.NotationModelHandlerContainerImpl;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.mdd.core</li>
 * <li>서브 업무명 : nexcore.tool.mdd.core.registry</li>
 * <li>설 명 : NotationModelHandlerRegistry</li>
 * <li>작성일 : 2010. 11. 22.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class NotationModelHandlerRegistry {

    /** 노테이션 모델 핸들러 레지스트리 */
    private static NotationModelHandlerRegistry instance;

    /** 노테이션 모델 핸들러 컨테이너 목록 */
    private static List<INotationModelHandlerContainer> notationModelHandlerContainerList;

    /** 노테이션 모델 핸들러 컨테이너 */
    private static INotationModelHandlerContainer notationModelHandlerContainer;

    /**
     * 생성자
     */
    private NotationModelHandlerRegistry() {
    }

    /**
     * 노테이션 모델 핸들러 레지스트리 반환
     * 
     * @return NotationModelHandlerRegistry
     */
    public static NotationModelHandlerRegistry getInstance() {
        if (instance == null) {
            instance = new NotationModelHandlerRegistry();

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
        IConfigurationElement config[] = registry.getConfigurationElementsFor(MDDCoreConstant.NEXCORE_TOOL_MDD_CORE_NOTATION_MODEL_HANDLER);

        if (config != null) {
            notationModelHandlerContainerList = new ArrayList<INotationModelHandlerContainer>();

            for (IConfigurationElement element : config) {
                notationModelHandlerContainer = new NotationModelHandlerContainerImpl();

                try {
                    notationModelHandlerContainer.setId(element.getAttribute(MDDCoreConstant.EXTENSION_ELEMENT_ID));
                    notationModelHandlerContainer.setLabel(element.getAttribute(MDDCoreConstant.EXTENSION_ELEMENT_LABEL));
                    notationModelHandlerContainer.setModelHandler((INotationModelHandler) element.createExecutableExtension(MDDCoreConstant.EXTENSION_ELEMENT_IMPLEMENT_INTERFACE));

                    notationModelHandlerContainerList.add(notationModelHandlerContainer);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * ID에 해당하는 노테이션 모델 핸들러 반환
     * 
     * @param id
     * @return INotationModelHandler
     */
    public INotationModelHandler getHandlerInstance(String id) {
        if (notationModelHandlerContainerList != null && !notationModelHandlerContainerList.isEmpty()) {
            for (INotationModelHandlerContainer notationModelHandlerContainer : notationModelHandlerContainerList) {
                if (notationModelHandlerContainer.getId().equals(id)
                    && notationModelHandlerContainer.getModelHandler() != null) {
                    return notationModelHandlerContainer.getModelHandler();
                }
            }
        }

        return null;
    }

}
