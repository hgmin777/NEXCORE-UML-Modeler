/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.property;

import nexcore.tool.uml.ui.property.util.PropertyModelHandler;

import org.eclipse.ui.views.properties.tabbed.ITypeMapper;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property</li>
 * <li>설 명 : UMLModelTypeMapper</li>
 * <li>작성일 : 2010. 3. 4.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class UMLModelTypeMapper implements ITypeMapper {

    /**
     * @see org.eclipse.ui.views.properties.tabbed.ITypeMapper#mapType(java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    public Class mapType(Object object) {
        return PropertyModelHandler.getInstance().getProperInstanceClass(object);
    }

}
