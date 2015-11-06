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
import nexcore.tool.mdd.core.extension.IDomainModelHandler;
import nexcore.tool.mdd.core.registry.container.IDomainModelHandlerContainer;
import nexcore.tool.mdd.core.registry.container.impl.DomainModelHandlerContainerImpl;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.mdd.core</li>
 * <li>서브 업무명 : nexcore.tool.mdd.core.registry</li>
 * <li>설 명 : DomainModelHandlerRegistry</li>
 * <li>작성일 : 2010. 11. 19.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class DomainModelHandlerRegistry {

    /** 도메인 모델 핸들러 레지스트리 */
    private static DomainModelHandlerRegistry instance;

    /** 도메인 모델 핸들러 컨테이너 목록 */
    private static List<IDomainModelHandlerContainer> domainModelHandlerContainerList;

    /** 도메인 모델 핸들러 컨테이너 */
    private static IDomainModelHandlerContainer domainModelHandlerContainer;

    /**
     * 생성자
     */
    private DomainModelHandlerRegistry() {
    }

    /**
     * 도메인 모델 핸들러 레지스트리 반환
     * 
     * @return DomainModelHandlerRegistry
     */
    public static DomainModelHandlerRegistry getInstance() {
        if (instance == null) {
            instance = new DomainModelHandlerRegistry();

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
        IConfigurationElement config[] = registry.getConfigurationElementsFor(MDDCoreConstant.NEXCORE_TOOL_MDD_CORE_DOMAIN_MODEL_HANDLER);

        if (config != null) {
            domainModelHandlerContainerList = new ArrayList<IDomainModelHandlerContainer>();

            for (IConfigurationElement element : config) {
                domainModelHandlerContainer = new DomainModelHandlerContainerImpl();

                try {
                    domainModelHandlerContainer.setId(element.getAttribute(MDDCoreConstant.EXTENSION_ELEMENT_ID));
                    domainModelHandlerContainer.setLabel(element.getAttribute(MDDCoreConstant.EXTENSION_ELEMENT_LABEL));
                    domainModelHandlerContainer.setModelHandler((IDomainModelHandler) element.createExecutableExtension(MDDCoreConstant.EXTENSION_ELEMENT_IMPLEMENT_INTERFACE));

                    domainModelHandlerContainerList.add(domainModelHandlerContainer);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * ID에 해당하는 도메인 모델 핸들러 반환
     * 
     * @param id
     * @return IDomainModelHandler
     */
    public IDomainModelHandler getHandlerInstance(String id) {
        if (domainModelHandlerContainerList != null && !domainModelHandlerContainerList.isEmpty()) {
            for (IDomainModelHandlerContainer domainModelHandlerContainer : domainModelHandlerContainerList) {
                if (domainModelHandlerContainer.getId().equals(id)
                    && domainModelHandlerContainer.getModelHandler() != null) {
                    return domainModelHandlerContainer.getModelHandler();
                }
            }
        }

        return null;
    }

}
