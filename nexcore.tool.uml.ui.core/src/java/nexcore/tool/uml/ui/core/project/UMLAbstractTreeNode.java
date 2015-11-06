/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.core.project;

import nexcore.tool.uml.ui.core.UICoreConstant;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.project</li>
 * <li>설  명 : UMLAbstractTreeNode</li>
 * <li>작성일 : 2009. 11. 30.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
@SuppressWarnings("unchecked")
public abstract class UMLAbstractTreeNode extends PlatformObject {

    /**
     * 
     */
    public UMLAbstractTreeNode() {
        super();
    }

    static {
        final Class[] supportedTypes = new Class[] { ITabbedPropertySheetPageContributor.class };
        final ITabbedPropertySheetPageContributor propertySheetPageContributor = new ITabbedPropertySheetPageContributor() {
            public String getContributorId() {
                return UICoreConstant.PROJECT_CONSTANTS__CONTRIBUTOR_ID;
            }
        };
        Platform.getAdapterManager().registerAdapters(new IAdapterFactory() {

            public Object getAdapter(Object adaptableObject, Class adapterType) {
                if (adaptableObject instanceof UMLAbstractTreeNode
                    && adapterType == ITabbedPropertySheetPageContributor.class) {
                    return propertySheetPageContributor;
                }
                return null;
            }

            public Class[] getAdapterList() {
                return supportedTypes;
            }
        },
            UMLAbstractTreeNode.class);
    }

    /** selected object */
    private Object myParent;

    /**
     * UMLAbstractTreeNode
     * @param parent
     */
    protected UMLAbstractTreeNode(Object parent) {
        myParent = parent;
    }

    /**
     * getParent
     *  
     * @return Object
     */
    public Object getParent() {
        return myParent;
    }

}
