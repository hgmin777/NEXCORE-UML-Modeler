/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.preferences.pages;

import java.util.ArrayList;
import java.util.List;

import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.diagram.edit.part.AbstractNotationNodeEditPart;
import nexcore.tool.uml.ui.core.diagram.editor.AbstractDiagramEditor;
import nexcore.tool.uml.ui.core.diagram.editor.DiagramEditorInput;
import nexcore.tool.uml.ui.core.util.PreferenceUtil;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.preferences</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.preferences.pages</li>
 * <li>설  명 : AbstractPreferencePage</li>
 * <li>작성일 : 2010. 1. 15.</li>
 * <li>작성자 : 변영민</li>
 * </ul>
 */
public abstract class AbstractPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

    /** preferenceStore */
    protected static final IPreferenceStore PREFERENCE_STORE = UiCorePlugin.getDefault().getPreferenceStore();

    /** gridData */
    protected GridData gridData;

    /** layout */
    protected GridLayout layout;

    /**
     * 
     * void
     */
    protected abstract void doStore();

    /**
     * 
     * void
     */
    protected abstract void doDefaultStore();

    /**
     * 
     * void
     */
    protected abstract void doLoad();

    /**
     * 
     * void
     */
    protected abstract void initDefault();

    /**
     * 
     * void
     */
    protected abstract void doCancel();

    /**
     * 
     * void
     */
    protected abstract void initializer();

    /**
     * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
     */
    public void init(IWorkbench workbench) {
        PreferenceUtil.INSTANCE.setPreferenceStore(PREFERENCE_STORE);
        this.initializer();
    }

    /**
     * @see org.eclipse.jface.preference.PreferencePage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(Composite parent) {
        super.createControl(parent);
        gridData = new GridData();
        gridData.horizontalSpan = 3;
        gridData.horizontalAlignment = GridData.END;
        getDefaultsButton().getParent().setLayoutData(gridData);
        this.doLoad();
    }

    /**
     * @see org.eclipse.jface.preference.PreferencePage#performOk()
     */
    @Override
    public boolean performOk() {
        // if (PreferenceUtil.INSTANCE.getIsDefaultPresented()) {
        // this.doDefaultStore();
        // } else {
        this.doStore();
        // }

        refreshDiagram();
        return super.performOk();
    }

    /**
     * @see org.eclipse.jface.preference.PreferencePage#performApply()
     */
    @Override
    protected void performApply() {
        // if (PreferenceUtil.INSTANCE.getIsDefaultPresented()) {
        // this.doDefaultStore();
        // } else {
        doStore();
        // }

        super.performApply();
        refreshDiagram();
    }

    /**
     * @see org.eclipse.jface.preference.PreferencePage#performCancel()
     */
    @Override
    public boolean performCancel() {
        this.doCancel();
        return super.performCancel();
    }

    /**
     * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
     */
    @Override
    protected void performDefaults() {
        // PreferenceUtil.INSTANCE.setIsDefaultPresented(true);
        this.initDefault();
        super.performDefaults();
    }

    @SuppressWarnings( { "deprecation", "unchecked" })
    protected void refreshDiagram() {
        IEditorPart[] iEditors = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditors();
        List<IEditorPart> editors = new ArrayList<IEditorPart>();
        EditPart root;
        List<EditPart> diagramEditPart = new ArrayList<EditPart>();
        List<EditPart> sourceConnectionEditParts = new ArrayList<EditPart>();
        List<EditPart> targetConnectionEditParts = new ArrayList<EditPart>();

        for (int i = 0; i < iEditors.length; i++) {
            editors.add(iEditors[i]);
        }
        for (IEditorPart editorPart : editors) {
            if (editorPart.getEditorInput() instanceof DiagramEditorInput) {
                ((AbstractDiagramEditor) editorPart).getDiagramGraphicalViewer().getContents().refresh();
                root = ((AbstractDiagramEditor) editorPart).getDiagramGraphicalViewer().getContents();
                diagramEditPart = root.getChildren();
                for (EditPart editpart : diagramEditPart) {
                    if (editpart instanceof AbstractNotationNodeEditPart) {
                        sourceConnectionEditParts = ((AbstractNotationNodeEditPart) editpart).getSourceConnections();
                        for (EditPart connectionEditPart : sourceConnectionEditParts) {
                            connectionEditPart.refresh();
                        }
                        targetConnectionEditParts = ((AbstractNotationNodeEditPart) editpart).getSourceConnections();
                        for (EditPart connectionEditPart : targetConnectionEditParts) {
                            connectionEditPart.refresh();
                        }
                    }
                    if (editpart.getChildren() != null && editpart.getChildren().size() != 0) {
                        List<EditPart> compartmentEditParts = editpart.getChildren();
                        for (EditPart compartmentEditPart : compartmentEditParts) {
                            compartmentEditPart.refresh();
                        }
                    }
                }
            }
        }
    }
}
