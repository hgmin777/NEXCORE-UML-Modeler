/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.search.provider;

import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.search.match.UMLModelElement;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.NamedElement;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.search</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.search.provider</li>
 * <li>설  명 : UMLModelLabelProvider</li>
 * <li>작성일 : 2010. 1. 13.</li>
 * <li>작성자 : 신용일</li>
 * </ul>
 */
public class UMLModelLabelProvider extends LabelProvider {

    /**
     * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
     */
    @Override
    public Image getImage(Object element) {
        if (element instanceof UMLModelElement) {
            UMLModelElement umlElement = (UMLModelElement) element;

            if (umlElement.getParent() == null) {
                Image projectImage = PlatformUI.getWorkbench()
                    .getSharedImages()
                    .getImage(org.eclipse.ui.ide.IDE.SharedImages.IMG_OBJ_PROJECT);
                return projectImage;
                
            } else if (umlElement.getType() != null) {

                Object obj = umlElement.getOrgObject();
                if (obj instanceof NamedElement) {
                	return UiCorePlugin.getDefault().getImageForUMLElement((EObject) obj);
                }
            }
        }
        return null;
//                    image = IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_MODEL);
//                } else if (NodeType.CLASS.getName().equals(umlElement.getType())) {
//                    image = IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_CLASS);
//                } else if (NodeType.PACKAGE.getName().equals(umlElement.getType()) ) {
//                    image = IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_PACKAGE);
//                } else if (NodeType.ARTIFACT.getName().equals(umlElement.getType()) ) {
//                    image = IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_ARTIFACT);
//                } else if (NodeType.COLLABORATION.getName().equals(umlElement.getType()) ) {
//                    image = IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_COLLABORATION);
//                } else if (NodeType.DATA_TYPE.getName().equals(umlElement.getType()) ) {
//                    image = IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_DATATYPE);
//                } else if (NodeType.ENUMERATION.getName().equals(umlElement.getType()) ) {
//                    image = IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_ENUMERATION);
//                } else if (NodeType.INTERFACE.getName().equals(umlElement.getType()) ) {
//                    image = IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_INTERFACE);
//                } else if (NodeType.USE_CASE.getName().equals(umlElement.getType()) ) {
//                    image = IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_USECASE);
//                } else if (NodeType.LIFELINE.getName().equals(umlElement.getType()) ) {
//                    image = IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_LIFELINE);
//                } else if (NodeType.OPAQUE_ACTION.getName().equals(umlElement.getType()) ) {
//                    image = IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_OPAQUEACTION);
//                } else if (NodeType.DATA_STORE_NODE.getName().equals(umlElement.getType()) ) {
//                    image = IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_DATASTORENODE);
//                } else if (NodeType.CENTRAL_BUFFER_NODE.getName().equals(umlElement.getType()) ) {
//                    image = IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_CENTRALBUFFERNODE);
//                } else if (NodeType.COMPONENT.getName().equals(umlElement.getType()) ) {
//                    image = IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_COMPONENT);
//                }
//                // Image umImage =
//                // Activator.getDefault().getImage(umlElement.getType());
//                return image != null ? image : Activator.getDefault()
//                    .getImage(UICoreConstant.MODELSEARCH__TYPE_DEFAULT);
//            } else {
//                return Activator.getDefault().getImage(UICoreConstant.MODELSEARCH__TYPE_DEFAULT);
//            }
//        }
//
//        return Activator.getDefault().getImage(UICoreConstant.MODELSEARCH__TYPE_DEFAULT);
    }

    /**
     * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
     */
    @Override
    public String getText(Object element) {
        if (element instanceof UMLModelElement) {
            return ((UMLModelElement) element).getDisplayName();
        }

        return UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;
    }

}
