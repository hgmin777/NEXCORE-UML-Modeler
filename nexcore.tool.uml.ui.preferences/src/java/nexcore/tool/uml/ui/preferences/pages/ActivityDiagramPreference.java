/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.preferences.pages;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.model.umldiagram.DiagramType;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.diagram.editor.AbstractDiagramEditor;
import nexcore.tool.uml.ui.core.util.PreferenceUtil;

import org.eclipse.core.runtime.Preferences.IPropertyChangeListener;
import org.eclipse.core.runtime.Preferences.PropertyChangeEvent;
import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.ManhattanConnectionRouter;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.LayerConstants;
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
 * <li>설 명 : ActivityDiagramPreference</li>
 * <li>작성일 : 2011. 2. 18.</li>
 * <li>작성자 : zerotae</li>
 * </ul>
 */
public class ActivityDiagramPreference extends AbstractPreferencePage implements IWorkbenchPreferencePage,
IPropertyChangeListener {

    /** router */
    private Button routerButtion;

    /** strBoolean */
    private String strBoolean;

    /**
     * @see nexcore.tool.uml.ui.preferences.pages.AbstractPreferencePage#doCancel()
     */
    @Override
    protected void doCancel() {

    }

    @Override
    protected void doDefaultStore() {
        strBoolean = "false";
        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_ACTIVITYDIAGRAM_CONNECTION_ROUTER_USE,
            strBoolean);
        UiCorePlugin.getDefault().savePluginPreferences();
        // PreferenceUtil.INSTANCE.setIsDefaultPresented(false);
    }

    /**
     * @see nexcore.tool.uml.ui.preferences.pages.AbstractPreferencePage#doLoad()
     */
    @Override
    protected void doLoad() {
        strBoolean = PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_ACTIVITYDIAGRAM_CONNECTION_ROUTER_USE);
        if (strBoolean.equals("true"))
            routerButtion.setSelection(true);
        else
            routerButtion.setSelection(false);
    }

    /**
     * @see nexcore.tool.uml.ui.preferences.pages.AbstractPreferencePage#doStore()
     */
    @Override
    protected void doStore() {
        if (routerButtion.getSelection()) {
            strBoolean = "true";
            IEditorPart[] editors = UiCorePlugin.getActiveWorkbench()
                .getActiveWorkbenchWindow()
                .getActivePage()
                .getEditors();
            for (IEditorPart editorPart : editors) {
                if (editorPart instanceof AbstractDiagramEditor
                    && DiagramType.ACTIVITY_DIAGRAM.equals(((AbstractDiagramEditor) editorPart).getDiagram().getType())) {
                    AbstractDiagramEditor editor = (AbstractDiagramEditor) editorPart;
                    GraphicalViewer viewer = editor.getDiagramGraphicalViewer();
                    ScalableFreeformRootEditPart root = (ScalableFreeformRootEditPart) viewer.getRootEditPart();
                    ConnectionLayer connLayer = (ConnectionLayer) root.getLayer(LayerConstants.CONNECTION_LAYER);
                    if (!(connLayer.getConnectionRouter() instanceof ManhattanConnectionRouter)) {
                        connLayer.setConnectionRouter(new ManhattanConnectionRouter());
                    }
                }
            }
        } else {
            strBoolean = "false";
            IEditorPart[] editors = UiCorePlugin.getActiveWorkbench()
                .getActiveWorkbenchWindow()
                .getActivePage()
                .getEditors();
            for (IEditorPart editorPart : editors) {
                if (editorPart instanceof AbstractDiagramEditor
                    && DiagramType.ACTIVITY_DIAGRAM.equals(((AbstractDiagramEditor) editorPart).getDiagram().getType())) {
                    AbstractDiagramEditor editor = (AbstractDiagramEditor) editorPart;
                    GraphicalViewer viewer = editor.getDiagramGraphicalViewer();
                    ScalableFreeformRootEditPart root = (ScalableFreeformRootEditPart) viewer.getRootEditPart();
                    ConnectionLayer connLayer = (ConnectionLayer) root.getLayer(LayerConstants.CONNECTION_LAYER);
                    connLayer.setConnectionRouter(null);
                }
            }
        }
        PreferenceUtil.INSTANCE.setValueOfStringFieldEditor(ManagerConstant.PREFERENCE_ACTIVITYDIAGRAM_CONNECTION_ROUTER_USE,
            strBoolean);

        UiCorePlugin.getDefault().savePluginPreferences();
    }

    /**
     * @see nexcore.tool.uml.ui.preferences.pages.AbstractPreferencePage#initDefault()
     */
    @Override
    protected void initDefault() {
        routerButtion.setSelection(false);
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
        routerButtion = new Button(container, SWT.CHECK);
        routerButtion.setText(UMLMessage.LABEL_PREFERENCE_DIAGRAM_ACITIVTYDIAGMRAM_CONNECTIONROUTER);

        return container;
    }

    public void propertyChange(PropertyChangeEvent event) {
        // TODO Auto-generated method stub

    }

}
