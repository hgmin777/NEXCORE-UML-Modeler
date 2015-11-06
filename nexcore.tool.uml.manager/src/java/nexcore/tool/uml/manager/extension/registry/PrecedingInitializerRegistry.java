/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.manager.extension.registry;

import java.util.ArrayList;

import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.manager.extension.AbstractPrecedingInitializer;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.manager</li>
 * <li>서브 업무명 : nexcore.tool.uml.manager.extension.registry</li>
 * <li>설 명 : PrecedingInitializerRegistry</li>
 * <li>작성일 : 2010. 4. 5.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class PrecedingInitializerRegistry {

    /** 선행 초기화 레지스트리 */
    private static PrecedingInitializerRegistry instance;

    /** 선행 초기화 Wrapper 목록 */
    private static ArrayList<IPrecedingInitializerWrapper> precedingInitializerWrapperList;

    /**
     * 생성자
     */
    private PrecedingInitializerRegistry() {
    }

    /**
     * 인스턴스 반환
     * 
     * @return MDACoreActionRegistry
     */
    public static PrecedingInitializerRegistry getInstance() {
        if (instance == null) {
            instance = new PrecedingInitializerRegistry();

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
        precedingInitializerWrapperList = new ArrayList<IPrecedingInitializerWrapper>();

        IExtensionRegistry registry = Platform.getExtensionRegistry();
        IConfigurationElement config[] = registry.getConfigurationElementsFor(ManagerConstant.NEXCORE_TOOL_PRECEDING_INITIALIZER_EXTENSION_POINT);

        PrecedingInitializerWrapper precedingIntializerWrapper = null;
        AbstractPrecedingInitializer precedingInitializer = null;

        if (config != null) {
            for (IConfigurationElement element : config) {
                precedingIntializerWrapper = new PrecedingInitializerWrapper();

                try {
                    if ((AbstractPrecedingInitializer) element.createExecutableExtension(ManagerConstant.EXTENSION_INITIALIZER_TARGET_CLASS) != null) {
                        precedingInitializer = (AbstractPrecedingInitializer) element.createExecutableExtension(ManagerConstant.EXTENSION_INITIALIZER_TARGET_CLASS);

                        precedingInitializer.initialize();

                        precedingIntializerWrapper.setInitializer(precedingInitializer);
                    }

                    precedingIntializerWrapper.setId(element.getAttribute(ManagerConstant.EXTENSION_INITIALIZER_ID));
                    precedingIntializerWrapper.setLabel(element.getAttribute(ManagerConstant.EXTENSION_INITIALIZER_LABEL));

                    precedingInitializerWrapperList.add(precedingIntializerWrapper);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 선행 초기화 목록 반환
     * 
     * @return ArrayList<IPrecedingInitializerWrapper>
     */
    public ArrayList<IPrecedingInitializerWrapper> getInitializerWrapperList() {
        return precedingInitializerWrapperList;
    }

    /**
     * 선행 초기화 실행하는 메소드 void
     */
    public void executeInitializer() {
        if (precedingInitializerWrapperList != null && precedingInitializerWrapperList.isEmpty() == false) {
            for (IPrecedingInitializerWrapper precedingInitializerWrapper : precedingInitializerWrapperList) {
                precedingInitializerWrapper.getInitializer().initialize();
            }
        }
    }

    /**
     * 프로젝트가 제품에 포함되어 있는지를 검사해서 boolean 을 반환하는 메소드
     * 
     * @param id
     * @return boolean
     */
    public boolean isProjectExist(String id) {
        if (precedingInitializerWrapperList != null && precedingInitializerWrapperList.isEmpty() == false) {
            for (IPrecedingInitializerWrapper precedingInitializerWrapper : precedingInitializerWrapperList) {
                if (precedingInitializerWrapper.getId().equals(id)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 특정 선행 초기화 인스턴스 반환하는 메소드
     * 
     * @param id
     * @return AbstractPrecedingInitializer
     */
    public AbstractPrecedingInitializer getProperInitializer(String id) {
        if (precedingInitializerWrapperList != null && precedingInitializerWrapperList.isEmpty() == false) {
            for (IPrecedingInitializerWrapper precedingInitializerWrapper : precedingInitializerWrapperList) {
                if (precedingInitializerWrapper.getId().equals(id)) {
                    return precedingInitializerWrapper.getInitializer();
                }
            }
        }

        return null;
    }
}
