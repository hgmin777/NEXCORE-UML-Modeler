/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.realization.sequencediagram.dialog;


import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.dialog</li>
 * <li>설  명 : CoveredLifelineByMessageContentProvider</li>
 * <li>작성일 : 2011. 5. 25.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class CoveredLifelineByMessageContentProvider implements IStructuredContentProvider  {

    /**
     * @see org.eclipse.jface.viewers.IContentProvider#dispose()
     */
    public void dispose() {
    }

    /**
     * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
     *      java.lang.Object, java.lang.Object)
     */
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    }

    /**
     * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
     */
    public Object[] getElements(Object inputElement) {
        if(inputElement instanceof HashMap) {
            HashMap lifelineMap = (HashMap) inputElement;
            Iterator iterator = lifelineMap.entrySet().iterator();
            Object[] elements = new Object[lifelineMap.size()];
            int index = 0;
            while(iterator.hasNext()) {
                elements[index] = iterator.next();
                index++;
            }
           return elements;
        }
        return null;
    }

}
