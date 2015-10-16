/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.search.match;

import org.eclipse.search.ui.text.Match;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.search</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.search.match</li>
 * <li>설  명 : UMLModelMatch</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class UMLModelMatch extends Match {

    /**
     * UMLModelMatch
     * @param element
     */
    public UMLModelMatch(Object element) {
        super(element, 0, element.toString().length());
    }

    /**
     * UMLModelMatch
     * @param element
     * @param offset
     * @param length
     */
    public UMLModelMatch(Object element, int offset, int length) {
        super(element, offset, length);
    }

}
