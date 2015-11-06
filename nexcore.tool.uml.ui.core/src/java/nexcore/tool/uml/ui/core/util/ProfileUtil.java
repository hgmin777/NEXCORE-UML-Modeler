/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.core.util;

import java.util.ArrayList;
import java.util.List;

import nexcore.tool.uml.core.util.StringUtil;
import nexcore.tool.uml.ui.core.UICoreConstant;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.util.UMLUtil;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.util</li>
 * <li>설 명 : ProfileUtil</li>
 * <li>작성일 : 2009. 12. 29.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class ProfileUtil {

    /**
     * 해당 프로퍼티에 객체형 단일 Value를 설정하는 메소드
     * 
     * @param parentElement
     * @param property
     * @param value
     *            void
     */
    public static void setValueOfProperty(NamedElement parentElement, Object property, Object valueObject) {
        parentElement.setValue((Stereotype) ((Property) property).eContainer(),
            ((Property) property).getName(),
            valueObject);
    }

    /**
     * 해당 프로퍼티에 객체형 단일 Value를 설정하는 메소드
     * 
     * @param parentElement
     * @param property
     * @param valueObject
     *            void
     */
    public static void setValueOfProperty(NamedElement parentElement, Object property, int currentIndex,
                                          Object valueObject) {
        parentElement.setValue((Stereotype) ((Property) property).eContainer(), ((Property) property).getName() + "["
            + currentIndex + "]", valueObject);
    }

    /**
     * 해당 프로퍼티의 값을 String 으로 반환하는 메소드 - 넘겨진 boolean 값에 따라 프로퍼티의 값이 없을 경우 default
     * value 가져오도록 구현
     * 
     * @param parentElement
     * @param property
     * @param wantDefaultValue
     * @return String
     */
    public static String getStringValueOfProperty(NamedElement parentElement, Object property, boolean wantDefaultValue) {
        String propertyValue = null;

        try {
            propertyValue = parentElement.getValue((Stereotype) ((Property) property).eContainer(),
                ((Property) property).getName()).toString();
        } catch (NullPointerException npe) {
            if (wantDefaultValue) {
                propertyValue = ((Property) property).isSetDefault() ? ((Property) property).getDefault() : ""; //$NON-NLS-1$
            } else {
                propertyValue = ""; //$NON-NLS-1$
            }
        }

        return propertyValue;
    }

    /**
     * 해당 프로퍼티의 값을 Object 로 반환하는 메소드 - 넘겨진 boolean 값에 따라 프로퍼티의 값이 없을 경우 default
     * value 가져오도록 구현
     * 
     * @param parentElement
     * @param property
     * @param wantDefaultValue
     * @return Object
     */
    public static Object getObjectValueOfProperty(NamedElement parentElement, Object property, boolean wantDefaultValue) {
        Object propertyValue = null;

        try {
            propertyValue = parentElement.getValue((Stereotype) ((Property) property).eContainer(),
                ((Property) property).getName());
        } catch (NullPointerException npe) {
            if (wantDefaultValue) {
                propertyValue = ((Property) property).isSetDefault() ? ((Property) property).getDefault() : ""; //$NON-NLS-1$
            } else {
                propertyValue = ""; //$NON-NLS-1$
            }
        }

        return propertyValue;
    }

    /**
     * 해당 프로퍼티의 다수개 값을 목록으로 반환하는 메소드
     * 
     * @param parentElement
     * @param property
     * @return List<Object>
     */
    @SuppressWarnings("unchecked")
    public static List<Object> getValueListOfProperty(NamedElement parentElement, Object property) {
        List<Object> propertyValueList = new ArrayList<Object>();

        propertyValueList = (List<Object>) parentElement.getValue((Stereotype) ((Property) property).eContainer(),
            ((Property) property).getName());

        return propertyValueList;
    }
    
    /**
     * 스테레오타입의 언어정보 설정
     * 
     * @param appliedStereotype
     * @param element
     * @param koreanPropertyName
     * @param englishPropertyName
     * @param englishName
     * @param firstCharacterCaseType 첫글자의 대소문자 설정
     */
    public static void setLanguageInformation(Stereotype appliedStereotype, Element element, String koreanPropertyName,
                                        String englishPropertyName, String englishName, String firstCharacterCaseType) {
        if (koreanPropertyName != null && koreanPropertyName.length() > 0) {
            UMLUtil.setTaggedValue(element, appliedStereotype, koreanPropertyName, ((NamedElement) element).getName());
        }

        if (englishPropertyName != null && englishPropertyName.length() > 0 && englishName != null
            && englishName.length() > 0) {
            UMLUtil.setTaggedValue(element, appliedStereotype, englishPropertyName, getProperName(englishName,
                firstCharacterCaseType));
        }
    }
    
    /**
     * 요소명에 적합한 형태로 변경
     * 
     * @param elementName
     * @param caseType
     * @return
     */
    private static String getProperName(String elementName, String firstCharacterCaseType) {
        if (elementName == null) {
            return StringUtil.nullStringCheck(elementName);
        } else {
            // 첫글자 설정값에 따라 대문자,소문자로 변경
            if (UICoreConstant.LOWER_CASE.equals(firstCharacterCaseType)) {
                elementName = StringUtil.toLowerCaseAtFirstChar(elementName);
            } else if (UICoreConstant.UPPER_CASE.equals(firstCharacterCaseType)) {
                elementName = StringUtil.toUpperCaseAtFirstChar(elementName);
            }

            // 공백 및 특수 문자를 제외
            elementName = StringUtil.getGlossaryTranslationName(elementName, UICoreConstant.EMPTY_STRING);
        }
        return elementName;
    }
    
}
