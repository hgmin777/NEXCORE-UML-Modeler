/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.preferences.pages;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.preferences</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.preferences.pages</li>
 * <li>설  명 : ConnectorsPreferencePage</li>
 * <li>작성일 : 2010. 1. 11.</li>
 * <li>작성자 : 변영민</li>
 * </ul>
 */
public class ConnectorsPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

    /**
     * 
     */
    public ConnectorsPreferencePage() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param title
     */
    public ConnectorsPreferencePage(String title) {
        super(title);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param title
     * @param image
     */
    public ConnectorsPreferencePage(String title, ImageDescriptor image) {
        super(title, image);
        // TODO Auto-generated constructor stub
    }

    /**
     * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createContents(Composite parent) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
     */
    public void init(IWorkbench workbench) {
        // TODO Auto-generated method stub

    }

}
