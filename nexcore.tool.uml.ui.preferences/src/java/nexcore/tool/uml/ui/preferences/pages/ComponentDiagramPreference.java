/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.preferences.pages;

import java.util.List;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.DiagramType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.diagram.editor.AbstractDiagramEditor;
import nexcore.tool.uml.ui.core.util.PreferenceUtil;
import nexcore.tool.uml.ui.core.util.ViewModelUtil;

import org.eclipse.core.runtime.Preferences.IPropertyChangeListener;
import org.eclipse.core.runtime.Preferences.PropertyChangeEvent;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.preferences</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.preferences.pages</li>
 * <li>설 명 : ComponentDiagramPreference</li>
 * <li>작성일 : 2011. 3. 8.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class ComponentDiagramPreference extends AbstractPreferencePage implements IWorkbenchPreferencePage,
IPropertyChangeListener {

    /** router */
    private Button showOperationButton;

    /** strBoolean */
    private String strBooleanForShowOperation;

    private Button showInnerElementButton;

    private String strBooleanForShowInnerElement;

    /**
     * @see nexcore.tool.uml.ui.preferences.pages.AbstractPreferencePage#doCancel()
     */
    @Override
    protected void doCancel() {
    }

    @Override
    protected void doDefaultStore() {
        strBooleanForShowOperation = String.valueOf(showOperationButton.getSelection());// "false";
        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_COMPONENTDIAGRAM_SHOW_OPERATION_IN_COMPONENT,// ACTIVITYDIAGRAM_CONNECTION_ROUTER_USE,
            strBooleanForShowOperation);

        strBooleanForShowInnerElement = String.valueOf(showInnerElementButton.getSelection());// "false";
        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_COMPONENTDIAGRAM_SHOW_INNER_ELEMENT_IN_COMPONENT,// ACTIVITYDIAGRAM_CONNECTION_ROUTER_USE,
            strBooleanForShowInnerElement);
        // PreferenceUtil.INSTANCE.setIsDefaultPresented(false);
        UiCorePlugin.getDefault().savePluginPreferences();
    }

    /**
     * @see nexcore.tool.uml.ui.preferences.pages.AbstractPreferencePage#doLoad()
     */
    @Override
    protected void doLoad() {
        strBooleanForShowOperation = PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_COMPONENTDIAGRAM_SHOW_OPERATION_IN_COMPONENT);
        if (strBooleanForShowOperation.equals("true"))
            showOperationButton.setSelection(true);
        else if (strBooleanForShowOperation.equals("false"))
            showOperationButton.setSelection(false);

        strBooleanForShowInnerElement = PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_COMPONENTDIAGRAM_SHOW_INNER_ELEMENT_IN_COMPONENT);
        if (strBooleanForShowInnerElement.equals("true"))
            showInnerElementButton.setSelection(true);
        else if (strBooleanForShowInnerElement.equals("false"))
            showInnerElementButton.setSelection(false);
    }

    /**
     * @see nexcore.tool.uml.ui.preferences.pages.AbstractPreferencePage#doStore()
     */
    @Override
    protected void doStore() {
        if (showOperationButton.getSelection()) {
            strBooleanForShowOperation = "true";
            IEditorPart[] editors = UiCorePlugin.getActiveWorkbench()
                .getActiveWorkbenchWindow()
                .getActivePage()
                .getEditors();
            for (IEditorPart editorPart : editors) {
                if (editorPart instanceof AbstractDiagramEditor
                    && DiagramType.COMPONENT_DIAGRAM.equals(((AbstractDiagramEditor) editorPart).getDiagram().getType())) {
                    AbstractDiagramEditor editor = (AbstractDiagramEditor) editorPart;
                    GraphicalViewer viewer = editor.getDiagramGraphicalViewer();
                    ScalableFreeformRootEditPart root = (ScalableFreeformRootEditPart) viewer.getRootEditPart();

                }
            }
        } else {
            strBooleanForShowOperation = "false";
            IEditorPart[] editors = UiCorePlugin.getActiveWorkbench()
                .getActiveWorkbenchWindow()
                .getActivePage()
                .getEditors();
            for (IEditorPart editorPart : editors) {
                if (editorPart instanceof AbstractDiagramEditor
                    && DiagramType.COMPONENT_DIAGRAM.equals(((AbstractDiagramEditor) editorPart).getDiagram().getType())) {
                    AbstractDiagramEditor editor = (AbstractDiagramEditor) editorPart;
                    GraphicalViewer viewer = editor.getDiagramGraphicalViewer();
                    ScalableFreeformRootEditPart root = (ScalableFreeformRootEditPart) viewer.getRootEditPart();
                    List<EditPart> diagramEditParts = root.getChildren();

                    Diagram diagram = editor.getDiagram();
                    for (AbstractNode node : diagram.getNodeList()) {

                        if (node instanceof NotationNode) {
                            ViewModelUtil.setModelInfo((NotationNode) node);
                        }
                    }
                    // for(EditPart diagramEditPart : diagramEditParts){
                    // Diagram diagram = diagramEditPart.getModel();
                    // }
                }
            }
        }
        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_COMPONENTDIAGRAM_SHOW_OPERATION_IN_COMPONENT,
            strBooleanForShowOperation);

        if (showInnerElementButton.getSelection()) {
            strBooleanForShowInnerElement = "true";
            IEditorPart[] editors = UiCorePlugin.getActiveWorkbench()
                .getActiveWorkbenchWindow()
                .getActivePage()
                .getEditors();
            for (IEditorPart editorPart : editors) {
                if (editorPart instanceof AbstractDiagramEditor
                    && DiagramType.COMPONENT_DIAGRAM.equals(((AbstractDiagramEditor) editorPart).getDiagram().getType())) {
                    AbstractDiagramEditor editor = (AbstractDiagramEditor) editorPart;
                    GraphicalViewer viewer = editor.getDiagramGraphicalViewer();
                    ScalableFreeformRootEditPart root = (ScalableFreeformRootEditPart) viewer.getRootEditPart();
                }
            }
        } else {
            strBooleanForShowInnerElement = "false";
            IEditorPart[] editors = UiCorePlugin.getActiveWorkbench()
                .getActiveWorkbenchWindow()
                .getActivePage()
                .getEditors();
            for (IEditorPart editorPart : editors) {
                if (editorPart instanceof AbstractDiagramEditor
                    && DiagramType.COMPONENT_DIAGRAM.equals(((AbstractDiagramEditor) editorPart).getDiagram().getType())) {
                    AbstractDiagramEditor editor = (AbstractDiagramEditor) editorPart;
                    GraphicalViewer viewer = editor.getDiagramGraphicalViewer();
                    ScalableFreeformRootEditPart root = (ScalableFreeformRootEditPart) viewer.getRootEditPart();
                }
            }
        }
        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_COMPONENTDIAGRAM_SHOW_INNER_ELEMENT_IN_COMPONENT,
            strBooleanForShowInnerElement);

        UiCorePlugin.getDefault().savePluginPreferences();

        IEditorPart[] editors = UiCorePlugin.getActiveWorkbench()
            .getActiveWorkbenchWindow()
            .getActivePage()
            .getEditors();
        for (IEditorPart editorPart : editors) {
            if (editorPart instanceof AbstractDiagramEditor) {
                if (DiagramType.COMPONENT_DIAGRAM.equals(((AbstractDiagramEditor) editorPart).getDiagram().getType())
                    || DiagramType.CLASS_DIAGRAM.equals(((AbstractDiagramEditor) editorPart).getDiagram().getType())) {
                    AbstractDiagramEditor editor = (AbstractDiagramEditor) editorPart;
                    GraphicalViewer viewer = editor.getDiagramGraphicalViewer();
                    ScalableFreeformRootEditPart root = (ScalableFreeformRootEditPart) viewer.getRootEditPart();
                    List<EditPart> diagramEditParts = root.getChildren();

                    Diagram diagram = editor.getDiagram();
                    for (AbstractNode node : diagram.getNodeList()) {
                        if (node instanceof NotationNode) {
                            ViewModelUtil.setModelInfo((NotationNode) node);
                        }
                    }
                }
            }
        }
        refreshDiagram();
    }

    /**
     * @see nexcore.tool.uml.ui.preferences.pages.AbstractPreferencePage#initDefault()
     */
    @Override
    protected void initDefault() {
        showOperationButton.setSelection(true);
        showInnerElementButton.setSelection(false);
        // PreferenceUtil.INSTANCE.setIsDefaultPresented(true);
    }

    /**
     * @see nexcore.tool.uml.ui.preferences.pages.AbstractPreferencePage#initializer()
     */
    @Override
    protected void initializer() {
    }

    /**
     * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createContents(Composite parent) {
        Composite container = new Composite(parent, SWT.NULL);
        container.setLayout(new GridLayout(1, false));
        showOperationButton = new Button(container, SWT.CHECK);
        showOperationButton.setText(UMLMessage.LABEL_PREFERENCE_DIAGRAM_COMPONENTDIAGRAM_SHOWOPERATION);
        showOperationButton.setSelection(true);

        showInnerElementButton = new Button(container, SWT.CHECK);
        showInnerElementButton.setText(UMLMessage.LABEL_PREFERENCE_DIAGRAM_COMPONENTDIAGRAM_SHOWINNERELEMENT);
        showInnerElementButton.setSelection(false);

        return container;
    }

    public void propertyChange(PropertyChangeEvent event) {
        // TODO Auto-generated method stub

    }

}
