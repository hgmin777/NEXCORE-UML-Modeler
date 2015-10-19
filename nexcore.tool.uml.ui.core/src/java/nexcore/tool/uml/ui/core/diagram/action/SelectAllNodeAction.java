/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */


package nexcore.tool.uml.ui.core.diagram.action;

import java.util.ArrayList;
import java.util.List;

import nexcore.tool.uml.ui.core.diagram.edit.part.LabelNodeEditPart;

import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.ui.actions.SelectAllAction;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchPart;

public class SelectAllNodeAction extends SelectAllAction {

    /**
     * part
     */
    private IWorkbenchPart part;

    /**
     * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /** SELECT_ALL_NODE_ACTION */
    public static final String SELECT_ALL_NODE_ACTION = "Select All Node";

    /** SELECT_ALL_NODE_ACTIONID */
    public static final String SELECT_ALL_NODE_ACTIONID = "SelectAllNode";

    /**
     * @param part
     */
    public SelectAllNodeAction(IWorkbenchPart part) {
        super(part);
        this.part = part;
//        setImageDescriptor(UiCorePlugin.getImageDescriptor(IConstantImageRegistry.ICONNAME_DELETE_FROM_MODEL_ACTION));
    }

    /**
     * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#init()
     */
    protected void init() {
        setId(SELECT_ALL_NODE_ACTIONID);
        setText(SELECT_ALL_NODE_ACTION);
    }

    /**
     * @see org.eclipse.jface.action.Action#run()
     */
    public void run() {
        GraphicalViewer viewer = (GraphicalViewer)part.getAdapter(GraphicalViewer.class);

        List<Object> viewerChildren = viewer.getContents().getChildren();
        List<Object> newChildren = new ArrayList<Object>();
        
        for( Object obj : viewerChildren ) {
            if( obj instanceof LabelNodeEditPart ) {
                continue;
            }
            newChildren.add(obj);
        }
        
        if (viewer != null) {
            viewer.setSelection(new StructuredSelection(newChildren));
        }
    }

}
