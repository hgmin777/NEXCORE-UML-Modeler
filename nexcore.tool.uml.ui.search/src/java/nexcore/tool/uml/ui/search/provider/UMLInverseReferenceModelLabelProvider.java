/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.search.provider;

import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.search.match.UMLInverseReferenceModelElement;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.NamedElement;

/**
 * 
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.search</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.search.provider</li>
 * <li>설 명 : UMLInverseReferenceModelLabelProvider</li>
 * <li>작성일 : 2012. 8. 24.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public class UMLInverseReferenceModelLabelProvider extends LabelProvider {

    /**
     * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
     */
    @Override
    public Image getImage(Object element) {
        if (element instanceof UMLInverseReferenceModelElement) {
            UMLInverseReferenceModelElement umlElement = (UMLInverseReferenceModelElement) element;

            if (umlElement.getParent() == null) {
                Image projectImage = PlatformUI.getWorkbench()
                    .getSharedImages()
                    .getImage(org.eclipse.ui.ide.IDE.SharedImages.IMG_OBJ_PROJECT);
                return projectImage;

            } else if (umlElement.getType() != null) {

                Object obj = umlElement.getOrgObject();
                if (obj instanceof EObject) {
                    return UiCorePlugin.getDefault().getImageForUMLElement((EObject) obj);
                }
            }
        }
        return null;

    }

    /**
     * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
     */
    @Override
    public String getText(Object element) {
        if (element instanceof UMLInverseReferenceModelElement) {
            if (((UMLInverseReferenceModelElement) element).getOrgObject() instanceof NamedElement) {
                return ((NamedElement) ((UMLInverseReferenceModelElement) element).getOrgObject()).getName();
            } else if (((UMLInverseReferenceModelElement) element).getOrgObject() instanceof Diagram) {
                return ((Diagram) ((UMLInverseReferenceModelElement) element).getOrgObject()).getName();
            }
            return ((UMLInverseReferenceModelElement) element).getDisplayName();
        }

        return UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;
    }

}
