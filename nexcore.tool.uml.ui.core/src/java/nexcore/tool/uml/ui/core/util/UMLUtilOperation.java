/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.core.util;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.util.UMLUtil;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.util</li>
 * <li>설  명 : UMLUtilOperation</li>
 * <li>작성일 : 2011. 4. 25.</li>
 * <li>작성자 : 강경구 </li>
 * </ul>
 */
public class UMLUtilOperation extends UMLUtil {

    /**
     * 
     *  
     * @param element
     * @param definition
     * @return EObject
     */
    public static EObject applyEStereotype(Element element, EClass definition) {
        return applyStereotype(element, definition);
    }
}
