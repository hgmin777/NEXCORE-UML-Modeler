/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.realization.sequencediagram.dialog;

import java.util.List;
import java.util.Map.Entry;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.ui.realization.sequencediagram.util.SequenceUtil;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.Type;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.dialog</li>
 * <li>설  명 : CoveredLifelineByMessageLabelProvider</li>
 * <li>작성일 : 2011. 5. 25.</li>
 * <li>작성자 : zerotae</li>
 * </ul>
 */
public class CoveredLifelineByMessageLabelProvider implements ITableLabelProvider {
    
    /**
     * orderedAllConnectionList
     */
    List<AbstractConnection> orderedAllConnectionList;
        
    /**
     * CoveredLifelineByMessageLabelProvider
     * @param orderedAllConnectionList
     */
    public CoveredLifelineByMessageLabelProvider(List<AbstractConnection> orderedAllConnectionList) {
        this.orderedAllConnectionList = orderedAllConnectionList;
    }

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
            if (element instanceof Entry) {
                String name = "";
                Lifeline lifeline = (Lifeline) ((Entry) element).getKey();
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
                
                AbstractConnection connection = (AbstractConnection) ((Entry) element).getValue();
                
                if(connection != null) {
                    int order = orderedAllConnectionList.indexOf(connection) +1;
                    name = name +  " (" + UMLMessage.LABEL_RELATED_MESSAGE + ": " + order + ":"+ ((Message) connection.getUmlModel()).getName() + ")";
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
