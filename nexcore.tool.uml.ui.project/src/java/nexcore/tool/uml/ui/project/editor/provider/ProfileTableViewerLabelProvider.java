/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.project.editor.provider;

import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.manager.extension.registry.PrecedingInitializerRegistry;
import nexcore.tool.uml.ui.core.UICoreConstant;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.uml2.uml.Profile;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.editor.provider</li>
 * <li>설 명 : ProfileTableViewerLabelProvider</li>
 * <li>작성일 : 2009. 12. 15.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class ProfileTableViewerLabelProvider extends LabelProvider implements ITableLabelProvider {

    /**
     * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object,
     *      int)
     */
    public Image getColumnImage(Object element, int columnIndex) {
        return null;
    }

    /**
     * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object,
     *      int)
     */
    public String getColumnText(Object element, int columnIndex) {
        Profile appliedProfile = (Profile) element;

        if (PrecedingInitializerRegistry.getInstance()
            .isProjectExist(ManagerConstant.NEXCORE_TOOL_MDA_CORE_PRECEDING_INITIALIZER_ID)) {
            switch (columnIndex) {
                case 0:
                    if (appliedProfile.eIsProxy()) {
                        return "unknown";
                    } else {
                        return appliedProfile.getName();
                    }
                case 1:
                    if (appliedProfile.eIsProxy()) {
                        String uriString = EcoreUtil.getURI(appliedProfile).toString();
                        String[] split = uriString.split("#");
                        return split != null && split.length == 2 ? split[0] : uriString;
                    } else {
                        return appliedProfile.eResource() != null ? appliedProfile.eResource().getURI().toString()
                        : UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;
                    }

                default:
                    return UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;
            }
        } else {
            switch (columnIndex) {
                default:
                    return UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;
            }
        }
    }

}
