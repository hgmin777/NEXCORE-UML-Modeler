/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.search.match;

import java.util.Hashtable;

import nexcore.tool.uml.core.message.UMLMessage;

import org.eclipse.jface.dialogs.IDialogSettings;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.search</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.search.match</li>
 * <li>설 명 : UMLModelSearchPattern</li>
 * <li>작성일 : 2010. 1. 14.</li>
 * <li>작성자 : 신용일</li>
 * </ul>
 */
public class UMLModelSearchPattern {

    /** 검색 문자열 */
    private String searchText = null;

    /** 대소문자 구분 여부 */
    private boolean isCaseSensitive = false;

    /** scopeObjects */
    private Hashtable<String, Object> scopeObjects = null;

    /**
     * 생성자
     * 
     * @param searchText
     * @param isCaseSensitive
     */
    public UMLModelSearchPattern(String searchText, boolean isCaseSensitive) {
        this.searchText = searchText;
        this.isCaseSensitive = isCaseSensitive;
    }

    /**
     * 검색대상 object 체크 checkedElements를 Hash테이블에 저장한다.
     * 
     * @param checkedElements
     *            void
     */
    public void setObjectScope(Object[] checkedElements) {
        scopeObjects = new Hashtable<String, Object>();
        for (int i = 0; i < checkedElements.length; i++) {
            if (checkedElements[i] instanceof UMLModelElement) {
                String key = ((UMLModelElement) checkedElements[i]).getType();
                scopeObjects.put(key, checkedElements[i]);
            }
        }
    }

    /**
     * 선택된 objectType를 반환해준다.
     * 
     * @param objectType
     * @return boolean
     */
    public boolean containScope(String objectType) {
        if (scopeObjects != null)
            return scopeObjects.containsKey(objectType);
        return false;
    }

    /**
     * create
     *  
     * @param histSettings
     * @return UMLModelSearchPattern
     */
    public static UMLModelSearchPattern create(IDialogSettings histSettings) {
        String searchText = histSettings.get(UMLMessage.LABEL_TEST_PATTERN);
        boolean isCaseSensitive = !histSettings.getBoolean("ignoreCase");
        return new UMLModelSearchPattern(searchText, isCaseSensitive);
    }

    /**
     * store
     *  
     * @param histSettings void
     */
    public void store(IDialogSettings histSettings) {
        histSettings.put("ignoreCase", !isCaseSensitive); //$NON-NLS-1$
        //        histSettings.put("isRegExSearch", isRegExSearch); //$NON-NLS-1$
        histSettings.put("textPattern", searchText); //$NON-NLS-1$
    }

    /**
     * 검색 문자열 반환
     * 
     * @return String
     */
    public String getSearchText() {
        return searchText;
    }

    /**
     * 검색 문자열 설정
     * 
     * @param searchText
     *            void
     */
    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    /**
     * 대소문자 구분 여부 반환
     * 
     * @return boolean
     */
    public boolean isCaseSensitive() {
        return isCaseSensitive;
    }

    /**
     * 대소문자 구분 여부 설정
     * 
     * @param isCaseSensitive
     *            void
     */
    public void setCaseSensitive(boolean isCaseSensitive) {
        this.isCaseSensitive = isCaseSensitive;
    }
}
