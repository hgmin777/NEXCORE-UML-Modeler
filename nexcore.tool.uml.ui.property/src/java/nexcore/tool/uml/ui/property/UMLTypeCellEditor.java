/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.property;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.core.util.TypeSelectDialogType;
import nexcore.tool.uml.manager.transaction.TransactionalAction;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.ui.core.dialog.TypeSelectDialog;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property</li>
 * <li>설  명 : UMLTypeCellEditor</li>
 * <li>작성일 : 2009. 12. 7.</li>
 * <li>작성자 : 변영민</li>
 * </ul>
 */
public class UMLTypeCellEditor extends DialogCellEditor {

    /** TRIPLE_DOT */
    @SuppressWarnings("unused")
    private static final String TRIPLE_DOT = "..."; //$NON-NLS-1$

    /** 셀에디터가 가지는 값의 타입 */
    private Object type;

    /**
     * table
     */
    private Table table;

    /**
     * element
     */
    private Element element;

    KeyListener keyListener = null;

    /**
     * @param table
     * @param element
     */
    public UMLTypeCellEditor(Table table, Type type, Element element) {
        super(table);
        this.table = table;
        this.type = type;

        this.element = element;

        getDefaultLabel().setText(UMLMessage.LABEL_NEW);
    }

    /** model */
    @SuppressWarnings("unused")
    private org.eclipse.uml2.uml.Model model;

    /** firstResult */
    private Object firstResult;

    /**
     * 
     * 
     * @param model
     *            void
     */
    public void setUMLModel(org.eclipse.uml2.uml.Model model) {
        this.model = model;
    }
    Button createButton = null;
    /**
     * @see org.eclipse.jface.viewers.DialogCellEditor#createButton(org.eclipse.swt.widgets.Composite)
     */
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
                    if (data instanceof Parameter) {
                        final Parameter parameter = (Parameter) data;
                        if (parameter.getType() != null) {
                            DomainUtil.run(new TransactionalAction() {
                                /**
                                 * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                                 */
                                @Override
                                public void doExecute() {
                                    parameter.setType(null);
                                }
                            });
                            
                            fireEditorValueChanged(true, true);
                        }
                    } else if (data instanceof Property) {
                        final Property property = (Property) data;
                        if (property.getType() != null) {
                            DomainUtil.run(new TransactionalAction() {
                                /**
                                 * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                                 */
                                @Override
                                public void doExecute() {
                                    property.setType(null);
                                }
                            });
                            
                            fireEditorValueChanged(true, true);
                        }
                    }
                    
                }
            }
        };
        
        createButton.addKeyListener(keyListener);
        return createButton;
    }

    /**
     * @see org.eclipse.jface.viewers.DialogCellEditor#openDialogBox(org.eclipse.swt.widgets.Control)
     */
    @Override
    protected Object openDialogBox(Control cellEditorWindow) {
        TypeSelectDialog dialog = new TypeSelectDialog(table.getShell(), TypeSelectDialogType.PROPERTY, element);
        int returnCode = dialog.open();

        if (keyListener != null) {
            table.removeKeyListener(keyListener);
        }
        switch (returnCode) {
            case Window.OK:
                firstResult = dialog.getFirstResult();
                return dialog.getFirstResult();
            case Window.CANCEL:
                return this.type;
            case IDialogConstants.FINISH_ID:
                return false;
            default:
                return null;
        }
    }

    @Override
    protected void focusLost() {
        if (createButton != null && !createButton.isDisposed()) {
            createButton.removeKeyListener(keyListener);
        }
        
        super.focusLost();
    }

    /**
     * 다이얼로그에서 선택된 객체.
     * 
     * @return Object
     */
    public Object getFirstResult() {
        return this.firstResult;
    }
}
