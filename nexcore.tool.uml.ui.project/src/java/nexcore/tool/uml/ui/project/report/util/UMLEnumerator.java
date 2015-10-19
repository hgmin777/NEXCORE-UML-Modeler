/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.project.report.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.util.ProjectUtil;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.newReport.manager.biz</li>
 * <li>설 명 : UMLEnumerator</li>
 * <li>작성일 : 2010. 10. 14.</li>
 * <li>작성자 : 한승일</li>
 * </ul>
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.report.util</li>
 * <li>설  명 : UMLEnumerator</li>
 * <li>작성일 : 2010. 10. 14.</li>
 * <li>작성자 : 한승일</li>
 * </ul>
 * @param <T>
 */
public class UMLEnumerator<T> {

    /**
     * getAllChildrenList
     *  
     * @param parent
     * @param key
     * @return List<T>
     */
    @SuppressWarnings("unchecked")
    public List<T> getAllChildrenList(Package parent, EClass key) {
        List<T> list = new ArrayList<T>();
        EObject eObject;
        for (TreeIterator<EObject> iter = parent.eAllContents(); iter.hasNext();) {
            eObject = iter.next();
            if (eObject.eClass().equals(key)) {
                list.add((T) eObject);
            }
        }

        return list;
    }

    /**
     * getSortedAllChildrenList
     *  
     * @param parent
     * @param key
     * @return List<T>
     */
    public List<T> getSortedAllChildrenList(Package parent, EClass key) {
        List<T> source = getAllChildrenList(parent, key);
        sorting(source);
        return source;
    }

    /**
     * getSortedAllPackageChildrenList
     *  
     * @param parent
     * @return List<T>
     */
    @SuppressWarnings("unchecked")
    public List<T> getSortedAllPackageChildrenList(Package parent) {
        List<PackageableElement> packagedElements = parent.getPackagedElements();
        List<T> list = new ArrayList<T>();
        for (PackageableElement packagedElement : packagedElements) {
            if (packagedElement.eClass().equals(UMLPackage.Literals.PACKAGE)) {
                list.add((T) packagedElement);
            }
        }
        sorting(list);

        if (list.size() > 0) {
            Object arrayList[] = list.toArray();
            for (int i = 0; i < arrayList.length; i++) {
                Package packageElement = (Package) arrayList[i];
                List<T> childrenList = getSortedAllPackageChildrenList(packageElement);
                if (childrenList.size() > 0) {
                    list.addAll(childrenList);
                }
            }
        }

        return list;
    }

    /**
     * getChildrenList
     *  
     * @param parent
     * @param key
     * @return List<T>
     */
    @SuppressWarnings("unchecked")
    public List<T> getChildrenList(Package parent, EClass key) {
        List<T> list = new ArrayList<T>();
        for (NamedElement element : parent.getOwnedMembers()) {
            if (element.eClass() == key) {
                list.add((T) element);
            }
        }
        return list;
    }

    /**
     * sorting
     *  
     * @param source void
     */
    public void sorting(List<T> source) {
        Comparator<T> comparator = new Comparator<T>() {
            public int compare(T o1, T o2) {
                String o1s = ProjectUtil.getStereotypeText((NamedElement) o1) + UICoreConstant.PROJECT_CONSTANTS__BLANK
                    + ((NamedElement) o1).getName();
                String o2s = ProjectUtil.getStereotypeText((NamedElement) o2) + UICoreConstant.PROJECT_CONSTANTS__BLANK
                    + ((NamedElement) o2).getName();

                return o1s.compareTo(o2s);
            }
        };

        Collections.sort(source, comparator);
    }

    /**
     * getSortedChildren
     *  
     * @param parent
     * @param key
     * @return List<T>
     */
    public List<T> getSortedChildren(Package parent, EClass key) {
        List<T> source = getChildrenList(parent, key);
        sorting(source);
        return source;
    }

}
