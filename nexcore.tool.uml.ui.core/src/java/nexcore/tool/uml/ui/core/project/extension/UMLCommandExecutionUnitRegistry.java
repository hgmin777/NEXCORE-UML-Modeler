/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.core.project.extension;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import nexcore.tool.uml.ui.core.UICoreConstant;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.project.extension</li>
 * <li>설  명 : UMLCommandExecutionUnitRegistry</li>
 * <li>작성일 : 2011. 6. 3.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class UMLCommandExecutionUnitRegistry {

    /** UML Command 실행단위 레지스트리 */
    private static UMLCommandExecutionUnitRegistry instance;

    /** UML Command 실행단위 래퍼 목록 */
    private static List<IUMLCommandExecutionUnitWrapper> executionUnitWrapperList;

    /**
     * 생성자
     */
    private UMLCommandExecutionUnitRegistry() {
    }

    /**
     * 인스턴스 반환
     *  
     * @return UMLCommonCommandRegistry
     */
    public static UMLCommandExecutionUnitRegistry getInstance() {
        if(instance == null) {
            instance = new UMLCommandExecutionUnitRegistry();

            instance.init();
        }

        return instance;
    }

    /**
     * 초기화
     * 
     *   void
     */
    private void init() {
        executionUnitWrapperList = new ArrayList<IUMLCommandExecutionUnitWrapper>();

        IExtensionRegistry registry = Platform.getExtensionRegistry();
        IConfigurationElement config[] = registry.getConfigurationElementsFor(UICoreConstant.NEXCORE_TOOL_UML_COMMAND_EXECUTION_UNIT);

        UMLCommandExecutionUnitWrapper executionUnitWrapper = null;
        UMLCommandExecutionUnit executionUnit = null;

        if(config != null) {
            for(IConfigurationElement element : config) {
                executionUnitWrapper = new UMLCommandExecutionUnitWrapper();

                try {
                    executionUnitWrapper.setId(element.getAttribute(UICoreConstant.EXTENSION_ELEMENT_ID));
                    executionUnitWrapper.setLabel(element.getAttribute(UICoreConstant.EXTENSION_ELEMENT_LABEL));

                    executionUnitWrapper.setPriority(element.getAttribute(UICoreConstant.EXTENSION_ELEMENT_PRIORITY));

                    if(element.createExecutableExtension(UICoreConstant.EXTENSION_ELEMENT_TARGET_CLASS) != null) {
                        executionUnit = (UMLCommandExecutionUnit) element.createExecutableExtension(UICoreConstant.EXTENSION_ELEMENT_TARGET_CLASS);

                        executionUnitWrapper.setExecutionUnit(executionUnit);
                    }

                    executionUnitWrapperList.add(executionUnitWrapper);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * id에 해당하는 Command 실행단위 스택 반환
     *  
     * @param id
     * @return Stack<UMLCommandExecutionUnit>
     */
    public Stack<UMLCommandExecutionUnit> getExecutionUnitStack(String id) {
        Stack<UMLCommandExecutionUnit> executionUnitStack = null;

        if(executionUnitWrapperList != null && executionUnitWrapperList.isEmpty() == false) {
            executionUnitStack = new Stack<UMLCommandExecutionUnit>();

            for(IUMLCommandExecutionUnitWrapper executionUnitWrapper : executionUnitWrapperList) {
                if(id.equals(executionUnitWrapper.getId()) && UICoreConstant.PRIORITY_LOW.equals(executionUnitWrapper.getPriority())) {
                    executionUnitStack.push(executionUnitWrapper.getExecutionUnit());
                }
            }

            for(IUMLCommandExecutionUnitWrapper executionUnitWrapper : executionUnitWrapperList) {
                if(id.equals(executionUnitWrapper.getId()) && UICoreConstant.PRIORITY_NORMAL.equals(executionUnitWrapper.getPriority())) {
                    executionUnitStack.push(executionUnitWrapper.getExecutionUnit());
                }
            }

            for(IUMLCommandExecutionUnitWrapper executionUnitWrapper : executionUnitWrapperList) {
                if(id.equals(executionUnitWrapper.getId()) && UICoreConstant.PRIORITY_HIGH.equals(executionUnitWrapper.getPriority())) {
                    executionUnitStack.push(executionUnitWrapper.getExecutionUnit());
                }
            }
        }

        return executionUnitStack;
    }

    /**
     * Command 실행단위 목록 반환
     *  
     * @return List<IUMLCommandExecutionUnitWrapper>
     */
    public static List<IUMLCommandExecutionUnitWrapper> getExecutionUnitWrapperList() {
        return executionUnitWrapperList;
    }

}
