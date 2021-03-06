/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.analysis.activitydiagram.action;

import java.util.List;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.ui.analysis.AnalysisConstant;
import nexcore.tool.uml.ui.analysis.AnalysisPlugin;
import nexcore.tool.uml.ui.analysis.activitydiagram.edit.part.ActivityDiagramEditPart;
import nexcore.tool.uml.ui.analysis.activitydiagram.util.ActivityDiagramUtil;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchPart;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.analysis</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.analysis.activitydiagram.action</li>
 * <li>설  명 : ChangeVerticalDirectionAction</li>
 * <li>작성일 : 2011. 7. 18.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class ChangeVerticalDirectionAction extends SelectionAction {

    /** CHANGE_CREATE_PARTITION_RULE_ACTION */
    public static final String CHANGE_VERTICAL_DIRECTION_ACTION = "Vertical";

    /** CHANGE_CREATE_PARTITION_RULE_ACTION_ID */
    public static final String CHANGE_VERTICAL_DIRECTION_ACTION_ID = "CHANGE_VERTICAL_DIRECTION_ACTION_ID";

    /** dialogSetting */
    private IDialogSettings dialogSetting;
    
    /**
     * ChangeVerticalDirectionAction
     * @param part
     */
    public ChangeVerticalDirectionAction(IWorkbenchPart part) {
        super(part);

    }
    
    /**
     * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        
        setChecked();
        
        if (getSelectedObjects().size() < 1) {
            return false;
        }

        Object obj = getSelectedObjects().get(0);

        if (!(obj instanceof EditPart)) {
            return false;
        }
        
        if ( obj instanceof EditPart ) {
            EditPart selectedEditPart = (EditPart) obj;
            if( !(selectedEditPart instanceof ActivityDiagramEditPart) ) {
                return false;
            }
        }
        
        return true;
    }

    /**
     * setChecked
     */
    private void setChecked() {
        String direction = UICoreConstant.EMPTY_STRING;
        
        this.dialogSetting = ActivityDiagramUtil.getDialogSetting();
        if( null != this.dialogSetting ) {
            direction = this.dialogSetting.get(AnalysisConstant.DIRECTION);
        }
        if( null == direction ) {
            this.dialogSetting.put(AnalysisConstant.DIRECTION, AnalysisConstant.VERTICAL);
        }

        if( AnalysisConstant.VERTICAL.equals(direction) ) {
            setChecked(true);
        } else {
            setChecked(false);
        }
    }

    /**
     * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#init()
     */
    protected void init() {
        setId(CHANGE_VERTICAL_DIRECTION_ACTION_ID);
        setText(CHANGE_VERTICAL_DIRECTION_ACTION);
    }

    /**
     * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#calculateEnabled()
     */
    protected boolean calculateEnabled() {
        if (getSelectedObjects().size() < 1) {
            return false;
        }

        Object obj = getSelectedObjects().get(0);

        if (!(obj instanceof EditPart)) {
            return false;
        }
        
        if ( obj instanceof EditPart ) {
            EditPart selectedEditPart = (EditPart) obj;
            if( !(selectedEditPart instanceof ActivityDiagramEditPart) ) {
                return false;
            }
        }
        
        return true;
    }

    /**
     * @see org.eclipse.jface.action.Action#run()
     */
    public void run() {
        if (getSelectedObjects().size() < 1) {
            return;
        }

        Object obj = getSelectedObjects().get(0);
        ActivityDiagramEditPart diagramEditPart = null;
        
        if ( obj instanceof ActivityDiagramEditPart ) {
            diagramEditPart = (ActivityDiagramEditPart) obj;
        }
        if( null == diagramEditPart ) {
            return;
        }
        
        
        this.dialogSetting = ActivityDiagramUtil.getDialogSetting();
        if( null != this.dialogSetting ) {
            
            
            if( AnalysisConstant.HORIZONTAL.equals(this.dialogSetting.get(AnalysisConstant.DIRECTION)) ) {
                Diagram activityDiagram = (Diagram) diagramEditPart.getModel();
                List<AbstractNode> nodeList = activityDiagram.getNodeList();
                
                for( AbstractNode node : nodeList ) { 
                    if( NodeType.ACTIVITY_PARTITION.equals(node.getNodeType()) ) {
                        
                        MessageDialog.openWarning(UiCorePlugin.getShell(),
                            null,
                            UMLMessage.MESSAGE_SELECT_PARTITION_DIRECTION_WARNING );
                        
                        return;
                    }
                }
            }
            
            this.dialogSetting.put(AnalysisConstant.DIRECTION, AnalysisConstant.VERTICAL);
            setChecked(true);
        }
    }
}
