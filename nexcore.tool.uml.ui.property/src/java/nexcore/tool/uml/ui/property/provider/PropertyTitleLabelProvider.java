/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.property.provider;

import nexcore.tool.uml.ui.property.util.PropertyModelHandler;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.uml2.uml.Element;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.provider</li>
 * <li>설 명 : PropertyTitleLabelProvider</li>
 * <li>작성일 : 2010. 3. 11.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class PropertyTitleLabelProvider extends LabelProvider {

    /**
     * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
     */
    @Override
    public String getText(Object element) {
        Object object = ((IStructuredSelection) element).getFirstElement();

        Element propertyModel = (Element) PropertyModelHandler.getInstance().getProperModel(object);

        return PropertyModelHandler.getInstance().getProperTitle(propertyModel);
    }

}
