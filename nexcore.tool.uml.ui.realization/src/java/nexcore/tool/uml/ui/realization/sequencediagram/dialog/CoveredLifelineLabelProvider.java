/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.realization.sequencediagram.dialog;

import nexcore.tool.uml.ui.realization.sequencediagram.util.SequenceUtil;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.Type;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.dialog</li>
 * <li>설  명 : CoveredLifelineLabelProvider</li>
 * <li>작성일 : 2011. 5. 25.</li>
 * <li>작성자 : zerotae</li>
 * </ul>
 */
public class CoveredLifelineLabelProvider implements ITableLabelProvider {

    /**
     * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object, int)
     */
    public Image getColumnImage(Object element, int columnIndex) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
     */
    public String getColumnText(Object element, int columnIndex) {
        if(columnIndex == 0) {
            if (element instanceof Lifeline) {
                String name = "";
                Lifeline lifeline = (Lifeline) element;
                name = lifeline.getName();
                if (lifeline.getRepresents() != null) {
                    Type type = lifeline.getRepresents().getType();
                    if (type != null) {
                        String typeName = type.getName();    
                        if (!SequenceUtil.checkLifelineVisibility()) {
                            name = "";
                        }
                        name = name + ":" + typeName;
                    }
                }
                return name;
            }
            return "";
        }
        
        return "";
    }

    /**
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
     */
    public void addListener(ILabelProviderListener listener) {
        // TODO Auto-generated method stub
        
    }

    /**
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
     */
    public void dispose() {
        // TODO Auto-generated method stub
        
    }

    /**
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object, java.lang.String)
     */
    public boolean isLabelProperty(Object element, String property) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
     */
    public void removeListener(ILabelProviderListener listener) {
        // TODO Auto-generated method stub
        
    }
}
