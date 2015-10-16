/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.project.explorer.action.registry;

import java.util.ArrayList;
import java.util.List;

import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.project.explorer.action.AbstractUMLExplorerExtendedAction;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.action.registry</li>
 * <li>설 명 : UMLExplorerExtendedActionRegistry</li>
 * <li>작성일 : 2010. 11. 16.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class UMLExplorerExtendedActionRegistry {

    /** UML Explorer 확장 액션 레지스트리 */
    private static UMLExplorerExtendedActionRegistry instance;

    /** UML Explorer 확장 액션 래퍼 목록 */
    private static List<IUMLExplorerExtendedActionWrapper> actionWrapperList;

    /**
     * 생성자
     */
    private UMLExplorerExtendedActionRegistry() {
    }

    /**
     * 인스턴스 반환
     * 
     * @return UMLExplorerExtendedActionRegistry
     */
    public static UMLExplorerExtendedActionRegistry getInstance() {
        if (instance == null) {
            instance = new UMLExplorerExtendedActionRegistry();

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
        actionWrapperList = new ArrayList<IUMLExplorerExtendedActionWrapper>();

        IExtensionRegistry registry = Platform.getExtensionRegistry();
        IConfigurationElement config[] = registry.getConfigurationElementsFor(UICoreConstant.NEXCORE_TOOL_UML_EXPLORER_EXTENDED_ACTION);

        UMLExplorerExtendedActionWrapper actionWrapper = null;
        if (config != null) {
            for (IConfigurationElement element : config) {
                actionWrapper = new UMLExplorerExtendedActionWrapper();

                try {
                    actionWrapper.setAction((AbstractUMLExplorerExtendedAction) element.createExecutableExtension(UICoreConstant.EXTENSION_ELEMENT_TARGET_CLASS));
                    actionWrapper.setId(element.getAttribute(UICoreConstant.EXTENSION_ELEMENT_ID)); //$NON-NLS-1$
                    actionWrapper.setLabel(element.getAttribute(UICoreConstant.EXTENSION_ELEMENT_LABEL)); //$NON-NLS-1$

                    actionWrapperList.add(actionWrapper);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 액션 래퍼 목록 반환
     * 
     * @return List<IUMLExplorerExtendedActionWrapper>
     */
    public List<IUMLExplorerExtendedActionWrapper> getActionWrapperList() {
        return actionWrapperList;
    }

    /**
     * ID에 해당하는 액션 반환하는 메소드
     * 
     * @param id
     * @return AbstractUMLExplorerExtendedAction
     */
    public AbstractUMLExplorerExtendedAction getProperAction(String id) {
        if (actionWrapperList != null && actionWrapperList.isEmpty() == false) {
            for (IUMLExplorerExtendedActionWrapper actionWrapper : actionWrapperList) {
                if (actionWrapper.getId().equals(id)) {
                    return actionWrapper.getAction();
                }
            }
        }

        return null;
    }

}
