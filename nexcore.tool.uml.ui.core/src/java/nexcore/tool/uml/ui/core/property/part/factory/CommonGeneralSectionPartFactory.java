/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.property.part.factory;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.property.part.factory</li>
 * <li>설 명 : CommonGeneralSectionPartFactory</li>
 * <li>작성일 : 2010. 2. 9.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class CommonGeneralSectionPartFactory extends SectionPartFactoryAdapter {

    /**
     * @see nexcore.tool.uml.ui.core.property.part.factory.SectionPartFactoryAdapter#createParts(org.eclipse.swt.widgets.Composite,
     *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
     */
    @Override
    public void createParts(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        // TODO Auto-generated method stub
        super.createParts(parent, tabbedPropertySheetPage);

        createNamePart(parent);
        createVisibilityPart(parent);
    }

    /**
     * @see nexcore.tool.uml.ui.core.property.part.factory.SectionPartFactoryAdapter#createNamePart(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createNamePart(Composite parent) {
        // TODO Auto-generated method stub
        super.createNamePart(parent);
    }

    /**
     * @see nexcore.tool.uml.ui.core.property.part.factory.SectionPartFactoryAdapter#createVisibilityPart(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createVisibilityPart(Composite parent) {
        // TODO Auto-generated method stub
        super.createVisibilityPart(parent);
    }

}
