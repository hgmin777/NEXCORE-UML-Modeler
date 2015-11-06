/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.project.explorer;

import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.project.ClosedTreeNode;
import nexcore.tool.uml.ui.core.project.ITreeNode;
import nexcore.tool.uml.ui.core.util.ProjectUtil;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.TreePathViewerSorter;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UseCase;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer</li>
 * <li>설 명 : ExplorerSorter</li>
 * <li>작성일 : 2010. 2. 3.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
public class ExplorerSorter extends TreePathViewerSorter {

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
            } else if (eobject instanceof Property) {
                return base + 80;
            } else if (eobject instanceof Operation) {
                return base + 90;
            } else {
                return 100;
            }

        } else if (object instanceof ClosedTreeNode) {
            return 100;
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

            // 2011-040-21 modified by nspark
            // 형상관리 서버에 연결된 경우 수정된 모델의 경우 ">" 가 붙어 sorting 시 문제 발생하여 아래와 같이 수정함.
            if (e1 instanceof ITreeNode && e2 instanceof ITreeNode) {

                String name1 = getText(e1).toLowerCase();
                String name2 = getText(e2).toLowerCase();

                return name1.compareTo(name2);
            }
            return super.compare(viewer, e1, e2);
        }
        return categoryDelta;

    }

    /**
     * getText
     *  
     * @param element
     * @return String
     */
    public String getText(Object element) {
        // don't change the order!
        if (element instanceof ITreeNode) {
            EObject eobject = ((ITreeNode) element).getEObject();

            if (eobject instanceof NamedElement) {
                NamedElement namedElement = (NamedElement) eobject;
                return ProjectUtil.getStereotypeLabel(namedElement) + namedElement.getName();

            } else if (eobject instanceof Diagram) {
                return ((Diagram) eobject).getName();

            } else if (eobject instanceof EAnnotation) {
                String source = ((EAnnotation) eobject).getSource();
                if (UICoreConstant.PROJECT_CONSTANTS__DIAGRAM.equals(source)) {
                    return UICoreConstant.PROJECT_CONSTANTS__DIAGRAM;
                }
            }

            String label = UiCorePlugin.getDefault().getAdapterFactoryLabelProvider().getText(eobject);
            int index = label.indexOf(UICoreConstant.PROJECT_CONSTANTS__FIRST_BRACKET);
            if (index < 0) {
                return label.substring(label.indexOf(UICoreConstant.PROJECT_CONSTANTS__RIGHT_ANGLE_BRACKET) + 1);
            } else {
                int first = label.indexOf(UICoreConstant.PROJECT_CONSTANTS__LAST_BRACKET);
                int last = label.lastIndexOf(UICoreConstant.PROJECT_CONSTANTS__RIGHT_ANGLE_BRACKET);
                String removed = label.substring(first + 2, last + 1);
                return label.replaceFirst(removed, UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING);
            }

        } else if (element instanceof IFile) {
            IFile file = (IFile) element;
            if (UICoreConstant.PROJECT_CONSTANTS__FILE_EXTENSION.equals(file.getFileExtension())
                || UICoreConstant.PROJECT_CONSTANTS__FRAGMENT_EXTENSION.equals(file.getFileExtension())) {
                URI uri = URI.createURI(file.getFullPath().toString());
                Resource resource = DomainRegistry.getUMLDomain().getResource(uri, true);
                if (resource == null) {
					return file.getName();
				}
                NamedElement namedElement = (NamedElement) resource.getContents().get(0);
                return ProjectUtil.getStereotypeLabel(namedElement) + namedElement.getName();
            } else {
                return file.getName();
            }

        }

        return UICoreConstant.PROJECT_CONSTANTS__BLANK;
    }
}
