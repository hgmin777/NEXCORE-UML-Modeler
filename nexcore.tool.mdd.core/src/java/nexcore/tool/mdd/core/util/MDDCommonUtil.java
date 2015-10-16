/*
 * Copyright (c) 2010 SK C&C Co., Ltd. All rights reserved.
 * 
 * This software is the confidential and proprietary information of SK C&C. You
 * shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK
 * C&C.
 */

package nexcore.tool.mdd.core.util;

import nexcore.tool.mdd.core.MDDCoreConstant;
import nexcore.tool.mdd.core.MDDCorePlugin;

import org.eclipse.jface.preference.IPreferenceStore;

/**
 * 
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.mdd.core</li>
 * <li>서브 업무명 : nexcore.tool.mdd.core.util</li>
 * <li>설 명 : MDDCommonUtil</li>
 * <li>작성일 : 2011. 3. 2.</li>
 * <li>작성자 : hyun</li>
 * </ul>
 * 공통적으로 쓰일 Util 모음
 * 
 * 
 */
public class MDDCommonUtil {
    /**
     * 유스케이스모델 패키지 명 반환 환경설정에 값이 들어가 있으면 환경설정값을, 없으면 default 값을 반환한다.
     * 
     * @return String
     */
    public static String getUsecasesPackageName() {
        IPreferenceStore store = MDDCorePlugin.getDefault().getPreferenceStore();
        if (store.getString(MDDCoreConstant.PREFERENCE_KEY_USECASES_PACKAGE_NAME) == null
            || MDDCoreConstant.EMPTY_STRING.equals(store.getString(MDDCoreConstant.PREFERENCE_KEY_USECASES_PACKAGE_NAME))) {
            return MDDCoreConstant.USECASES_PACKAGE_DEFAULT_NAME;
        } else {
            return store.getString(MDDCoreConstant.PREFERENCE_KEY_USECASES_PACKAGE_NAME);
        }

    }

    /**
     * 분석모델 패키지명 반환 환경설정에 값이 들어가 있으면 환경설정값을, 없으면 default 값을 반환한다.
     * 
     * @return String
     */
    public static String getUsecaseRealizationPackageName() {
        IPreferenceStore store = MDDCorePlugin.getDefault().getPreferenceStore();

        if (store.getString(MDDCoreConstant.PREFERENCE_KEY_USECASE_REALIZATION_PACKAGE_NAME) == null
            || MDDCoreConstant.EMPTY_STRING.equals(store.getString(MDDCoreConstant.PREFERENCE_KEY_USECASE_REALIZATION_PACKAGE_NAME))) {
            return MDDCoreConstant.USECASE_REALIZATION_PACKAGE_DEFAULT_NAME;
        } else {
            return store.getString(MDDCoreConstant.PREFERENCE_KEY_USECASE_REALIZATION_PACKAGE_NAME);
        }
    }

    /**
     * 특정한 Keyword 를 입력 받았을때 replaceText 로 치환함 ex)
     * getKeywordAppliedString("[operation]", "My[operation]Test", "Print") =
     * MyPrintTest
     * 
     * @param keyWord
     * @param name
     * @param replaceText
     * @return String
     */
    public static String getKeywordAppliedString(String keyWord, String name, String replaceText) {
        if (keyWord == null || MDDCoreConstant.EMPTY_STRING.equals(keyWord) || name == null
            || MDDCoreConstant.EMPTY_STRING.equals(name)) {
            return name;
        }
        StringBuffer result = new StringBuffer();

        // 키워드가 시작되는 index 구함.
        int indexKeyword = name.toUpperCase().indexOf(keyWord.toUpperCase());
        if (indexKeyword == -1) { // 키워드 미포함시
            return name;
        } else { // 키워드 포함시
            int len = keyWord.length(); // 키워드의 길이
            String prefix = name.substring(0, indexKeyword); // 접두어
            String postfix = name.substring(indexKeyword + len, name.length()); // 접미어

            result.append(prefix);
            result.append(replaceText);
            result.append(postfix);

        }

        return result.toString();
    }

    /**
     * get a packaged name from qualifiedName
     * 
     * @param qualifiedName
     * @return
     */
    public static String getMappedName(String qualifiedName) {
        if (qualifiedName == null) {
            return null;
        }
        int index = qualifiedName.indexOf(MDDCoreConstant.QUALIFIED_SEGMENTATION_STRING);
        if (index != -1) {
            return qualifiedName.substring(index + 2).replace(MDDCoreConstant.QUALIFIED_SEGMENTATION_STRING,
                MDDCoreConstant.DOT).trim();
        } else {
            return qualifiedName.trim();
        }
    }

}
