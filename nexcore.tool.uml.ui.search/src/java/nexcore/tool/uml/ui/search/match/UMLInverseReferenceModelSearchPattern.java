/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.search.match;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.utility.DomainRegistry;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.uml2.uml.Element;

/**
 * 
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.search</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.search.match</li>
 * <li>설 명 : UMLInverseReferenceModelSearchPattern</li>
 * <li>작성일 : 2012. 8. 23.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public class UMLInverseReferenceModelSearchPattern {

    /** 검색 요소 */
    private Element searchElement = null;

    /**
     * 생성자
     * 
     * @param searchText
     * @param isCaseSensitive
     */
    public UMLInverseReferenceModelSearchPattern(Element searchElement) {
        this.searchElement = searchElement;
    }

    /**
     * create
     * 
     * @param histSettings
     * @return UMLInverseReferenceModelSearchPattern
     */
    public static UMLInverseReferenceModelSearchPattern create(IDialogSettings histSettings) {
        String searchElementURI = histSettings.get(UMLMessage.LABEL_TEST_PATTERN);

        EObject eObject = DomainRegistry.getEditingDomain()
            .getResourceSet()
            .getEObject(URI.createURI(searchElementURI), false);
        if (eObject instanceof Element) {
            Element element = (Element) eObject;

            return new UMLInverseReferenceModelSearchPattern(element);
        }

        return null;
    }

    /**
     * store
     * 
     * @param histSettings
     *            void
     */
    public void store(IDialogSettings histSettings) {
        if (searchElement != null) {
            histSettings.put("textPattern", EcoreUtil.getURI(searchElement).toString()); //$NON-NLS-1$
        }
    }

    /**
     * 검색 Element 반환
     * 
     * @return String
     */
    public Element getSearchElement() {
        return searchElement;
    }

    /**
     * 검색 Element 설정
     * 
     * @param searchText
     *            void
     */
    public void setSearchElement(Element searchElement) {
        this.searchElement = searchElement;
    }
}
