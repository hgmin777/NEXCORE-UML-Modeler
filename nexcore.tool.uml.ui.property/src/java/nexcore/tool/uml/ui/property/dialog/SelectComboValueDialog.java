/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.property.dialog;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.ui.property.provider.LiteralValueLabelProvider;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.dialog</li>
 * <li>설  명 : SelectComboValueDialog</li>
 * <li>작성일 :  2009. 12. 28.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class SelectComboValueDialog extends Dialog {

    /** 다이얼로그 제목 */
    private String title;

    /** 열거형 목록 */
    private Object[] enumerationList;

    /** 선택된 열거형 객체 */
    private Object selectedEnumerationLiteral;

    /** 테이블 뷰어 */
    private TableViewer tableViewer;

    /**
     * 생성자
     * 
     * @param parent
     */
    protected SelectComboValueDialog(Shell parent) {
        super(parent);
    }

    /**
     * 생성자
     * 
     * @param parent
     * @param name
     * @param object
     */
    public SelectComboValueDialog(Shell parent, String title, Object[] enumerationList) {
        this(parent);
        this.title = title;
        this.enumerationList = enumerationList;
    }

    /**
     * 생성자
     * 
     * @param parent
     * @param name
     * @param object
     */
    public SelectComboValueDialog(Shell parent, String title, Object[] enumerationList, Object selectedItem) {
        this(parent);
        this.title = title;
        this.enumerationList = enumerationList;
        this.selectedEnumerationLiteral = selectedItem;
    }

    /**
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        Composite composite = (Composite) super.createDialogArea(parent);
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 1;
        composite.setLayout(gridLayout);

        createValueTableComposite(composite);

        return composite;
    }

    /**
     * 값 입력 테이블 컴포짓 생성
     * 
     * @param composite
     *            void
     */
    private void createValueTableComposite(final Composite composite) {
        tableViewer = new TableViewer(composite, SWT.CHECK | SWT.FULL_SELECTION | SWT.SINGLE | SWT.BORDER);

        Table table = tableViewer.getTable();

        GridData gridData = new GridData(GridData.FILL_BOTH);
        gridData.heightHint = 200;
        table.setLayoutData(gridData);
        table.setLinesVisible(true);
        table.setHeaderVisible(true);

        TableViewerColumn indexColumn = new TableViewerColumn(tableViewer, SWT.NONE);
        indexColumn.getColumn().setText(UMLMessage.LABEL_CHECK_ITEM);
        indexColumn.getColumn().setWidth(50);
        indexColumn.getColumn().setAlignment(SWT.LEFT);

        TableViewerColumn valueColumn = new TableViewerColumn(tableViewer, SWT.NONE);
        valueColumn.getColumn().setText(UMLMessage.LABEL_LITERAL);
        valueColumn.getColumn().setWidth(120);
        valueColumn.getColumn().setAlignment(SWT.LEFT);

        tableViewer.setContentProvider(new ArrayContentProvider());
        tableViewer.setLabelProvider(new LiteralValueLabelProvider());

        tableViewer.getTable().addListener(SWT.Selection, new Listener() {
            /**
             * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
             */
            public void handleEvent(Event event) {
                setCheckedItemInformation(event);
            }
        });

        tableViewer.setInput(enumerationList);
        // check
        if (selectedEnumerationLiteral != null) {
            for (TableItem item : tableViewer.getTable().getItems()) {
                if (item.getData().equals(selectedEnumerationLiteral)) {
                    item.setChecked(true);
                }
            }
        }
    }

    /**
     * 선택된 항목 체크해서 값 설정
     * 
     * @param event
     *            void
     */
    protected void setCheckedItemInformation(Event event) {
        if (event.detail == SWT.CHECK) {
            selectedEnumerationLiteral = (Object) event.item.getData();
        }

        for (TableItem item : tableViewer.getTable().getItems()) {
            if (!item.getData().equals(selectedEnumerationLiteral) && item.getChecked()) {
                item.setChecked(false);
            }
        }
    }

    /**
     * @see org.eclipse.jface.dialogs.Dialog#initializeBounds()
     */
    @Override
    protected void initializeBounds() {
        super.initializeBounds();
        Shell shell = this.getShell();

        shell.setText(title);
        shell.setSize(300, 400);
    }

    /**
     * 선택된 리터럴 반환
     * 
     * @return Object
     */
    public Object getSelectedEnumerationLiteral() {
        return selectedEnumerationLiteral;
    }

}
