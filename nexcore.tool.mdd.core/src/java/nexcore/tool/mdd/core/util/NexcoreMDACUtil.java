/* 
 * Copyright (c) 2011 SK C&C Co., Ltd. All rights reserved. 
 * 
 * This software is the confidential and proprietary information of SK C&C. 
 * You shall not disclose such confidential information and shall use it 
 * only in accordance with the terms of the license agreement you entered into 
 * with SK C&C. 
 */
package nexcore.tool.mdd.core.util;

import java.util.ArrayList;
import java.util.List;

import nexcore.tool.mdd.core.MDDCoreConstant;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.util.UMLUtil;

/**
 * Nexcore용 MDA Developer에서 사용하는 Util.
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.mdd.core</li>
 * <li>서브 업무명 : nexcore.tool.mdd.core.util</li>
 * <li>설 명 : NexcoreMDACUtil</li>
 * <li>작성일 : 2011. 11. 8.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
public class NexcoreMDACUtil {
    
    /**
     * 선택된 요소 전체목록 중에서 component 메타데이터 생성에 쓰이는 스테레오타입이 적용된 요소의 목록을 반환
     * 
     * @param selectedElementList
     * @return List<Element>
     */
    public static List<Element> getComponentElementList(List<Element> selectedElementList) {
        List<Element> componentElementList = null;

        for (Element element : selectedElementList) {
            for (Stereotype appliedStereotype : element.getAppliedStereotypes()) {
                if (appliedStereotype.getLabel().equals(
                        MDDCoreConstant.COMPONENT_METADATA_STEREOTYPE_NAME)) {
                    if (componentElementList == null) {
                        componentElementList = new ArrayList<Element>();
                    }

                    componentElementList.add(element);
                }
            }
        }

        return componentElementList;
    }
    
    /**
     * 요소로부터 component id 찾는 메소드
     * 
     * @param element BizUnit
     * @param selectedElementList
     * @return String
     */
    public static String findComponentId(Element element, List<Element> selectedElementList) {
        String methodLowerCaseName = null;
        List<Element> componentChildList = null;
        String componentChildLowerCaseName = null;

        List<Element> componentList = getComponentElementList(selectedElementList);
        if (componentList.size() == 1) {
            return getComponentId(componentList.get(0));
        }
        
        for (Element componentElement : componentList) {
            if (!componentElement.getOwnedElements().isEmpty()
                    && componentElement.getOwnedElements().size() > 0) {
                componentChildList = componentElement.getOwnedElements();

                for (Element componentChild : componentChildList) {
                    if (componentChild instanceof Operation) {
                        componentChildLowerCaseName = ((NamedElement) componentChild)
                                .getName().toLowerCase();
                        
                        List<Element> methodElementList = getMethodElementList(element);
                        if (null != methodElementList) {
                            for (Element methodElement : methodElementList) {
                                methodLowerCaseName = ((NamedElement) methodElement)
                                        .getName().toLowerCase();
    
                                if (methodLowerCaseName
                                        .startsWith(componentChildLowerCaseName)) {
                                    return getComponentId(componentElement);
                                }
                            }
                        }
                    }
                }
            }
        }

        return null;
    }
    
    /**
     * component 요소로부터 id 가져오기
     * 
     * @param componentElement
     * @return String
     */
    public static String getComponentId(Element componentElement) {
        // 참조할 스테레오타입 정보 가져오기
        Stereotype sourceStereotype = null;

        for (Stereotype appliedStereotype : componentElement
                .getAppliedStereotypes()) {
            if (appliedStereotype.getLabel().equals(
                    MDDCoreConstant.COMPONENT_METADATA_STEREOTYPE_NAME)) {
                sourceStereotype = appliedStereotype;
                break;
            }
        }

        String taggedValue = MDDCoreConstant.EMPTY_STRING;

        // fqId 값 가져오기
        taggedValue = (String) UMLUtil.getTaggedValue(componentElement,
                sourceStereotype.getQualifiedName(),
                MDDCoreConstant.FQ_ID_KEY_IN_COMPONENT_STEREOTYPE);
        
        // 스테레오타입 속성에 값이 없을 때는 만들어서 리턴한다.
        if (taggedValue == null || taggedValue.length() == 0) {
            return createComponentFqId(componentElement);
        }

        return taggedValue;
    }
    
    /**
     * Component의 FqId값을 만들어준다.
     * 모델 바로 하위의 패키지를 제외하고 컴포넌트 클래스가 위치한 패키지까지의 경로 + 컴포넌트명
     * 
     * @param component
     * @return
     */
    public static String createComponentFqId(Element component) {
        
        if (null == component) {
            return MDDCoreConstant.EMPTY_STRING;
        }

        String fqId = ((NamedElement) component.getOwner().getOwner()).getQualifiedName();
        String modelName = component.getModel().getName() + MDDCoreConstant.QUALIFIED_SEGMENTATION_STRING;
        if (fqId.length() < modelName.length()) {
            return fqId;
        }
        fqId = fqId.substring(modelName.length());
//        String[] fqIdArray = fqId.split(MDDCoreConstant.QUALIFIED_SEGMENTATION_STRING);
        fqId = fqId.substring(fqId.indexOf(MDDCoreConstant.QUALIFIED_SEGMENTATION_STRING) + 2) 
            + MDDCoreConstant.DOT + ((NamedElement) component).getName();
        fqId.replaceAll(MDDCoreConstant.QUALIFIED_SEGMENTATION_STRING, MDDCoreConstant.DOT);
        return fqId;
    }
    
    /**
     * 선택된 BizUnit 요소 하위목록 중에서 method 스테레오타입이 적용된 요소의 목록을 반환
     * 
     * @param bizUnitElement
     * @return List<Element>
     */
    public static List<Element> getMethodElementList(Element bizUnitElement) {
        List<Element> allElementChildList = null;
        List<Element> methodChildElementList = null;

        if (!bizUnitElement.getOwnedElements().isEmpty()
                && bizUnitElement.getOwnedElements().size() > 0) {
            allElementChildList = bizUnitElement.getOwnedElements();
        }
        
        if (null != allElementChildList) {
            for (Element childElement : allElementChildList) {
                for (Stereotype appliedStereotype : childElement
                        .getAppliedStereotypes()) {
                    if (appliedStereotype
                            .getLabel()
                            .equals(
                                    MDDCoreConstant.BIZUNIT_METADATA_METHOD_STEREOTYPE_NAME)) {
                        if (methodChildElementList == null) {
                            methodChildElementList = new ArrayList<Element>();
                        }
    
                        methodChildElementList.add(childElement);
                    }
                }
            }
        }
        return methodChildElementList;
    }

}
