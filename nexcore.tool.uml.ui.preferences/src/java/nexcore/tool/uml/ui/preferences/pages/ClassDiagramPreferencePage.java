/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.preferences.pages;

import nexcore.tool.uml.core.message.UMLMessage;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;


/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.preferences</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.preferences.pages</li>
 * <li>설  명 : ClassDiagramPreferencePage</li>
 * <li>작성일 : 2011. 4. 12.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class ClassDiagramPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

    /**
     * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
     */
    protected Control createContents(Composite parent) {
        Composite container = new Composite(parent, SWT.NULL);
        container.setLayout(new GridLayout(1, false));
        {
            Label label = new Label(container, SWT.NONE);
            label.setText( UMLMessage.MESSAGE_CLASSDIAGRAM_PREFERENCE_DESCRIPTION );
        }
        return container;
    }

    /**
     * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
     */
    public void init(IWorkbench workbench) {
    }
}
