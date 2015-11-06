/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.core.dialog;

import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.ui.core.project.ITreeNode;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.TreePathViewerSorter;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.UseCase;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.dialog</li>
 * <li>설  명 : NameSorter</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class NameSorter extends TreePathViewerSorter {
    /**
     * @see org.eclipse.jface.viewers.ViewerComparator#category(java.lang.Object)
     */
    @Override
    public int category(Object object) {
        int base = 0;
        if (object instanceof ITreeNode) {
            EObject eobject = ((ITreeNode) object).getEObject();
            if (eobject instanceof Diagram) {
                return base + 2;
            } else if (eobject instanceof Collaboration) {
                return base + 5;
            } else if (eobject instanceof org.eclipse.uml2.uml.Package) {
                return base + 10;
            } else if (eobject instanceof org.eclipse.uml2.uml.Class) {
                return base + 20;
            } else if (eobject instanceof Interface) {
                return base + 30;
            } else if (eobject instanceof DataType) {
                return base + 40;
            } else if (eobject instanceof Enumeration) {
                return base + 50;
            } else if (eobject instanceof Actor) {
                return base + 60;
            } else if (eobject instanceof UseCase) {
                return base + 70;
            } else {
                return 100;
            }

        } else {
            return super.category(object);
        }
    }

    /**
     * @see org.eclipse.jface.viewers.ViewerComparator#compare(org.eclipse.jface.viewers.Viewer,
     *      java.lang.Object, java.lang.Object)
     */
    @Override
    public int compare(Viewer viewer, Object e1, Object e2) {
        int categoryDelta = category(e1) - category(e2);
        if (categoryDelta == 0) {
            return super.compare(viewer, e1, e2);
        }
        return categoryDelta;

    }

}
