/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.diagram.action;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.DiagramType;
import nexcore.tool.uml.ui.core.util.PreferenceUtil;
import nexcore.tool.uml.ui.core.util.ProjectUtil;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.action</li>
 * <li>설 명 : ShowParametersAction</li>
 * <li>작성일 : 2011. 5. 27.</li>
 * <li>작성자 : Kang</li>
 * </ul>
 */
public class ShowParametersAction extends SelectionAction {

    /**
     * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#isEnabled()
     */
    @Override
    public boolean isEnabled() {

        if (PreferenceUtil.INSTANCE.getPreferenceStore()
            .getBoolean(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COMPARTMENT_VISIBILITY_SHOW_PARAMETERS)) {
            setChecked(true);
        } else {
            setChecked(false);
        }

        Object obj = getSelectedObjects().get(0);
        Object element = ((EditPart) obj).getModel();

        AbstractView selectedView = null;
        if (element instanceof AbstractView) {
            selectedView = (AbstractView) element;
        }
        if (selectedView == null) {
            return false;
        }

        if(selectedView instanceof Diagram){
            Diagram diagram = (Diagram) selectedView;
            if( DiagramType.CLASS_DIAGRAM.equals(diagram.getType()) || DiagramType.COMPONENT_DIAGRAM.equals(diagram.getType()) ){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /** Add Attribute Action ID */
    public static final String SHOW_PARAMETERS_ACTIONID = "Show Parameters";//$NON-NLS-1$

    /**
     * @param part
     */
    public ShowParametersAction(IWorkbenchPart part) {
        super(part);
    }

    /**
     * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#calculateEnabled()
     */
    @Override
    protected boolean calculateEnabled() {
        return true;
    }

    /**
     * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#init()
     */
    protected void init() {
        setId(SHOW_PARAMETERS_ACTIONID);
        setText(UMLMessage.LABEL_SHOW_PARAMETER);

        if (PreferenceUtil.INSTANCE.getPreferenceStore()
            .getBoolean(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COMPARTMENT_VISIBILITY_SHOW_PARAMETERS)) {
            setChecked(true);
        } else {
            setChecked(false);
        }
    }

    /**
     * @see org.eclipse.jface.action.Action#run()
     */
    public void run() {
        /*
         * DomainRegistry.getUMLDomain().getCommandStack().execute(new Command()
         * {
         * 
         * @Override public void execute() { createAttribute(); } });
         */
        DomainRegistry.getEditingDomain()
            .getCommandStack()
            .execute(new RecordingCommand(DomainRegistry.getEditingDomain()) {
                @Override
                protected void doExecute() {
                    showParameters();

                }
            });
    }

    /**
     * showParameters
     *   void
     */
    private void showParameters() {
        if (this.isChecked()) {
            PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COMPARTMENT_VISIBILITY_SHOW_PARAMETERS,
                Boolean.toString(false));
        } else {
            PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COMPARTMENT_VISIBILITY_SHOW_PARAMETERS,
                Boolean.toString(true));
            PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_COMPARTMENT_VISIBILITY_SHOW_NAME,
                Boolean.toString(false));
        }
        ProjectUtil.refreshChildEditPart(((EditPart) getSelectedObjects().get(0)).getRoot());
    }
}
