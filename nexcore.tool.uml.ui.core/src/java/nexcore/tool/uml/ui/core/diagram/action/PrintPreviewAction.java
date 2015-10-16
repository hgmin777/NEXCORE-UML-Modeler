/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.diagram.action;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.diagram.edit.part.DiagramRootEditPart;
import nexcore.tool.uml.ui.core.diagram.editor.AbstractDiagramEditor;

import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbenchPart;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.action</li>
 * <li>설 명 : PrintPreviewAction</li>
 * <li>작성일 : 2011. 09. 27.</li>
 * <li>작성자 : 강석찬</li>
 * </ul>
 */
public class PrintPreviewAction extends SelectionAction {

    /** PRINT_PREVIEW_ACTION_ID */
    public static final String PRINT_PREVIEW_ACTION_ID = "PRINT_PREVIEW_ACTION_ID";

    /**
     * PrintPreviewAction
     * @param part
     */
    public PrintPreviewAction(IWorkbenchPart part) {
        super(part);
        // TODO: 이미지 변경
        // ImageRegistry imageRegistry =
        // UiCorePlugin.getDefault().getImageRegistry();
        // setImageDescriptor(UiCorePlugin.getImageDescriptor(IConstantImageRegistry.ICONNAME_PRINT_PREVIEW_ACTION));
    }

    /**
     * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#init()
     */
    protected void init() {
        setId(PRINT_PREVIEW_ACTION_ID);
        setText(UMLMessage.LABEL_PRINT_PREVIEW);
    }

    /**
     * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#calculateEnabled()
     */
    protected boolean calculateEnabled() {
        return true;
    }

    /**
     * Sets the action style to AS_CHECK_BOX
     */
    public int getStyle() {
        return AS_CHECK_BOX;
    }

    /**
     * @see org.eclipse.jface.action.Action#run()
     */
    public void run() {
        IPreferenceStore store = UiCorePlugin.getDefault().getPreferenceStore();
        if (!store.contains(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_TOP)) {
            store.setValue(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_HEADERFOOTER, UMLMessage.LABEL_PRINT_NONE);
            store.setValue(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_HEADERFOOTER_ONLYDIAGRAM, false);
            store.setValue(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_PAGE_SIZE, UMLMessage.LABEL_PRINT_A4);
            store.setValue(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_PAGE_ORIENTATION, UMLMessage.LABEL_PRINT_PORTRAIT);
            
            store.setValue(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_TOP, UICoreConstant.PROJECT_CONSTANTS__ZERO);
            store.setValue(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_LEFT, UICoreConstant.PROJECT_CONSTANTS__ZERO);
            store.setValue(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_RIGHT, UICoreConstant.PROJECT_CONSTANTS__ZERO);
            store.setValue(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_MARGIN_BOTTOM, UICoreConstant.PROJECT_CONSTANTS__ZERO);
            store.setValue(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PRINT_PREVIEW_HEADER_TEXT, UICoreConstant.EMPTY_STRING);         
        }
        
        DiagramRootEditPart rootPart = (DiagramRootEditPart)((((AbstractDiagramEditor)getWorkbenchPart()).getDiagramGraphicalViewer()).getRootEditPart());

        rootPart.togglePreviewFigure();                      
              
    }
}
