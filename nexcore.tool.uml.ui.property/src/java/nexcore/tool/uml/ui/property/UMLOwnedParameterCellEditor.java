/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.property;

import nexcore.tool.uml.manager.transaction.TransactionalAction;
import nexcore.tool.uml.manager.utility.DomainUtil;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property</li>
 * <li>설  명 : UMLOwnedParameterCellEditor</li>
 * <li>작성일 : 2010. 1. 5.</li>
 * <li>작성자 : 변영민</li>
 * </ul>
 */
@SuppressWarnings("restriction")
public class UMLOwnedParameterCellEditor extends DialogCellEditor {

    /** ID_ONE */
    private static final String ID_ONE = "1"; //$NON-NLS-1$

    /** selection */
    private ISelection selection;
    
    private Table table;

    /**
     * UMLOwnedParameterCellEditor
     * @param parent
     * @param selection
     */
    public UMLOwnedParameterCellEditor(Composite parent, ISelection selection) {
        super(parent);
        if( parent instanceof Table) {
            this.table = (Table) parent;
        }
        this.selection = selection;
    }
    
    Button createButton = null;
    
    KeyListener keyListener = null;

    @Override
    protected Button createButton(Composite parent) {

        createButton = super.createButton(parent);
        
        // 매개변수에 유형이 설정된 경우 DEL 키로 삭제 한다.
        keyListener = new KeyAdapter() {
            /* (non-Javadoc)
             * @see org.eclipse.swt.events.KeyListener#keyReleased(org.eclipse.swt.events.KeyEvent)
             */
            public void keyReleased(KeyEvent e) {
                if (e.keyCode == SWT.DEL) {
                    TableItem item = table.getItem(table.getSelectionIndex());
                    Object data = item.getData();
                    if (data instanceof Operation) {
                        final Operation operation = (Operation) data;
                        final EList<Parameter> ownedParameters = operation.getOwnedParameters();
                        if (ownedParameters != null && !ownedParameters.isEmpty()) {
                            DomainUtil.run(new TransactionalAction() {
                                /**
                                 * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                                 */
                                @Override
                                public void doExecute() {
                                    ownedParameters.clear();
                                }
                            });
                            
                            fireCancelEditor();
                        }
                    }
                    
                }
            }
        };
        
        createButton.addKeyListener(keyListener);
        return createButton;
    
    }
    
    @Override
    protected void focusLost() {
        if (createButton != null && !createButton.isDisposed()) {
            createButton.removeKeyListener(keyListener);
        }
        
        super.focusLost();
    }
    
    /**
     * @see org.eclipse.jface.viewers.DialogCellEditor#openDialogBox(org.eclipse.swt.widgets.Control)
     */
    @Override
    protected Object openDialogBox(Control cellEditorWindow) {
        OwnedParameterDialog dialog = new OwnedParameterDialog(cellEditorWindow.getShell(), selection);
        dialog.open();
        
        fireEditorValueChanged(true, true);
        
        return null;
    }

}
