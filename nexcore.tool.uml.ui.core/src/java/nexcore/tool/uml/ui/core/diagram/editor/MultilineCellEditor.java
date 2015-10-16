/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.diagram.editor;

import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.editor</li>
 * <li>설 명 : MultilineCellEditor</li>
 * <li>작성일 : 2010. 2. 22.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
public class MultilineCellEditor extends TextCellEditor {

    /**
     * Constructor.
     */
    public MultilineCellEditor(Composite parent) {
        super(parent);
    }

    /**
     * @see org.eclipse.jface.viewers.TextCellEditor#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createControl(Composite parent) {
        setStyle(SWT.MULTI | SWT.V_SCROLL);
        return super.createControl(parent);
    }

    /**
     * @see org.eclipse.jface.viewers.TextCellEditor#keyReleaseOccured(org.eclipse.swt.events.KeyEvent)
     */
    @Override
    protected void keyReleaseOccured(KeyEvent keyEvent) {

        if (keyEvent.character == '\r') { // Return key
            if (text != null && !text.isDisposed() && (text.getStyle() & SWT.MULTI) != 0) {
                if ((keyEvent.stateMask & SWT.CTRL) != 0) {
                    if (keyEvent.character == '\u001b') { // Escape character
                        fireCancelEditor();
                    }
                }
            }
            return;
        }
        super.keyReleaseOccured(keyEvent);

    }

}
