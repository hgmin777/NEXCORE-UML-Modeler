/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.core.util.paste;

import nexcore.tool.uml.core.message.UMLMessage;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.pattern.ui</li>
 * <li>서브 업무명 : nexcore.tool.ui.core.util.paste</li>
 * <li>설 명 : PasteDialog</li>
 * <li>작성일 : 2012. 1. 5.</li>
 * <li>작성자 : 김영훈</li>
 * </ul>
 */
public class PasteDialog extends Dialog {
    
    /**
     * COPY
     */
    public static final int COPY = 10;
    /**
     * REFERENCE
     */
    public static final int REFERENCE = 11;

    /** 참조 복사 라디오박스 **/
    private Button btnSelected = null;

    /**
     * PasteDialog
     * @param parentShell
     */
    public PasteDialog(Shell parentShell) {
        super(parentShell);
    }

    /**
     * @see org.eclipse.jface.dialogs.Dialog#initializeBounds()
     */
    @Override
    protected void initializeBounds() {
        super.initializeBounds();
        
        Shell shell = getShell();
        shell.setText(UMLMessage.MESSAGE_DIALOG_TITLE);
        shell.setSize(400, 200);
    }
    
    /**
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        
        Composite container = (Composite) super.createDialogArea(parent);
        container.setLayout(new GridLayout());
        
        createContentsControl(container);
        
        return container;
    }

    /**
     * 
     *  
     * @param container void
     */
    private void createContentsControl(Composite parent) {
        
        Composite composite = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.marginHeight = 10;
        layout.marginLeft = 10;
        composite.setLayout(layout);
        composite.setLayoutData(new GridData(GridData.FILL_BOTH));
        
        Label label = new Label(composite, SWT.NULL);
        label.setText(UMLMessage.MESSAGE_CHOOSE_COPY_TYPE);
        GridData gridData = new GridData(GridData.FILL);
        label.setLayoutData(gridData);

        Composite c1 = new Composite(composite, SWT.NO_RADIO_GROUP);
        c1.setLayout(layout);
        c1.setLayoutData(new GridData(GridData.CENTER));

        final Composite[] composites = new Composite[] { c1 };
        Listener radioGroup = new Listener() {
            public void handleEvent(Event event) {
                for (int i = 0; i < composites.length; i++) {
                    Composite composite = composites[i];
                    Control[] children = composite.getChildren();
                    for (int j = 0; j < children.length; j++) {
                        Control child = children[j];
                        if (child instanceof Button) {
                            Button button = (Button) child;
                            if ((button.getStyle() & SWT.RADIO) != 0)
                                button.setSelection(false);
                        }
                    }
                }
                Button button = (Button) event.widget;
                button.setSelection(true);
                btnSelected = button;
            }
        };

        Button button = new Button(c1, SWT.RADIO);
        button.setText(UMLMessage.LABEL_COPY);
        button.setData(COPY);
        button.addListener(SWT.Selection, radioGroup);

        button = new Button(c1, SWT.RADIO);
        button.setText(UMLMessage.LABEL_REFERENCE);
        button.setData(REFERENCE);
        button.addListener(SWT.Selection, radioGroup);
    }
    
    /**
     * @see org.eclipse.jface.dialogs.Dialog#okPressed()
     */
    @Override
    protected void okPressed() {
        setReturnCode((Integer)btnSelected.getData());
        close();
    }

}
