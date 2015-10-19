/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.dialog.provider;

import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.util.ProjectUtil;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.NamedElement;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.dialog.provider</li>
 * <li>설 명 : ProjectItemLabelProvider</li>
 * <li>작성일 : 2010. 3. 19.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class ProjectItemLabelProvider extends LabelProvider {

    /** 어댑터 팩토리 라벨 프로바이더 */
    private AdapterFactoryLabelProvider adapterFactoryLabelProvider = new AdapterFactoryLabelProvider(UiCorePlugin.getDefault()
        .getItemProvidersAdapterFactory());

    /**
     * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
     */
    @Override
    public String getText(Object element) {
        if (element instanceof NamedElement) {
            NamedElement namedElement = (NamedElement) element;

            return ProjectUtil.getStereotypeLabel(namedElement) + namedElement.getName();
        } else if (element instanceof IProject) {
            return ((IProject) element).getName();
        } else if (element instanceof IFolder) {
            return ((IFolder) element).getName();
        }

        String label = adapterFactoryLabelProvider.getText(element);
        int index = label.indexOf(UICoreConstant.PROJECT_CONSTANTS__FIRST_BRACKET);

        if (index < 0) {
            return label.substring(label.indexOf(UICoreConstant.PROJECT_CONSTANTS__RIGHT_ANGLE_BRACKET) + 1);
        } else {
            int first = label.indexOf(UICoreConstant.PROJECT_CONSTANTS__LAST_BRACKET);
            int last = label.lastIndexOf(UICoreConstant.PROJECT_CONSTANTS__RIGHT_ANGLE_BRACKET);
            String removed = label.substring(first + 2, last + 1);
            return label.replaceFirst(removed, UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING);
        }
    }

    /**
     * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
     */
    @Override
    public Image getImage(Object element) {
        if (element instanceof NamedElement) {
            return UiCorePlugin.getDefault().getImageForUMLElement((EObject) element);
        } else if (element instanceof IProject) {
            return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_PROJECT);
        } else if (element instanceof IFolder) {
            return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FOLDER);
        }

        return null;
    }

}
