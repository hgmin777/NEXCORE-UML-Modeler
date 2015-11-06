/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.alm.common.ui.preference;

import nexcore.alm.common.ui.Activator;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.core.ui</li>
 * <li>서브 업무명 : nexcore.tool.core.ui.preference</li>
 * <li>설 명 : NexcoreCommonPreference</li>
 * <li>작성일 : 2010. 3. 12.</li>
 * <li>작성자 : 05690</li>
 * </ul>
 */
public class NexcoreCommonPreference extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    private IWorkbench workbench;

    private Composite nexcoreComposite;

    public NexcoreCommonPreference() {
        super(GRID);
        setPreferenceStore(Activator.getDefault().getPreferenceStore());
    }

    @Override
    protected Control createContents(Composite parent) {

        nexcoreComposite = getFieldEditorParent();
        nexcoreComposite = new Composite(parent, SWT.NONE);
        nexcoreComposite.setLayout(new GridLayout());
        nexcoreComposite.setFont(parent.getFont());

        GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
        nexcoreComposite.setLayoutData(data);

        // createField();

        initialize();
        checkState();
        noDefaultAndApplyButton();
        return nexcoreComposite;
    }

    /**
     * NEXCORE category 안에 채워넣을 공간.
     */
    private void createField() {

        Composite bodyComposite = new Composite(nexcoreComposite, SWT.NONE);
        bodyComposite.setLayout(new GridLayout());

        GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
        bodyComposite.setLayoutData(data);

    }

    @Override
    protected void createFieldEditors() {
    }

    public void init(IWorkbench workbench) {
        this.workbench = workbench;
        this.setDescription("");

    }

    @Override
    protected void noDefaultAndApplyButton() {
        super.noDefaultAndApplyButton();
    }

    @Override
    public void performHelp() {

        MessageDialog.openInformation(getShell(), "", "");

    }

    @Override
    public boolean performOk() {
        return super.performOk();
    }

}
