/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.property.provider;

import nexcore.tool.uml.ui.core.util.ProfileUtil;
import nexcore.tool.uml.ui.property.section.StereotypePropertyComposite;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.provider</li>
 * <li>설  명 : StereotypePropertyTableColumnLabelProvider</li>
 * <li>작성일 : 2009. 12. 22.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class StereotypePropertyTableColumnLabelProvider extends LabelProvider implements ITableLabelProvider {

    /** 부모 */
    private StereotypePropertyComposite parent;

    /**
     * 생성자
     * 
     * @param parent
     */
    public StereotypePropertyTableColumnLabelProvider(StereotypePropertyComposite parent) {
        this.parent = parent;
    }

    /**
     * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object,
     *      int)
     */
    public Image getColumnImage(Object element, int columnIndex) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object,
     *      int)
     */
    public String getColumnText(Object element, int columnIndex) {
        if (element instanceof Stereotype) {
            switch (columnIndex) {
                case 0:
                    return ((Stereotype) element).getName();
                default:
                    return "";
            }
        } else if (element instanceof Property) {
            switch (columnIndex) {
                case 0:
                    return ((Property) element).getName();
                case 1:
                    return ProfileUtil.getStringValueOfProperty((NamedElement) parent.getInput(),
                        ((Property) element),
                        true);
                default:
                    return ""; //$NON-NLS-1$
            }
        } else {
            return ""; //$NON-NLS-1$
        }
    }

}
