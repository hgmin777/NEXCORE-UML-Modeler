/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.project.editor.provider;

import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.uml2.uml.NamedElement;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.editor.provider</li>
 * <li>설  명 : DiagramTableViewerLabelProvider</li>
 * <li>작성일 : 2012. 1. 13.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public class DiagramTableViewerLabelProvider extends LabelProvider implements ITableLabelProvider {

    /**
     * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object,
     *      int)
     */
    public Image getColumnImage(Object element, int columnIndex) {
        
        switch (columnIndex) { 
            case 0: 
                return UiCorePlugin.getDefault().getImageForUMLElement((EObject) element);
        }
        return null;
    }

    /**
     * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object,
     *      int)
     */
    public String getColumnText(Object element, int columnIndex) {

        Diagram diagram = (Diagram) element;

        switch (columnIndex) {
//            case 0:
//                return diagram.getType().getName();
            case 0:
                return diagram.getName();
            case 1:
                if( diagram.getParent() instanceof NamedElement ) {
                    return ((NamedElement)diagram.getParent()).getQualifiedName();
                }
                return diagram.getParent().toString();
        }

        return UICoreConstant.EMPTY_STRING;
    }
}
