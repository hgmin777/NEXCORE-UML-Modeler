/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.property.section;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.core.util.TypeSelectDialogType;
import nexcore.tool.uml.manager.transaction.TransactionalAction;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.dialog.TypeSelectDialog;
import nexcore.tool.uml.ui.property.provider.ExceptionSectionContentProvider;
import nexcore.tool.uml.ui.property.provider.ExceptionSectionLabelProvider;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Type;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.section</li>
 * <li>설  명 : ExceptionsSection</li>
 * <li>작성일 : 2009. 12. 18.</li>
 * <li>작성자 : 변영민</li>
 * </ul>
 */
public class ExceptionsSection extends AbstractPropertyCommonSection {

    /** COLUMN_NAMES */
    private static final String[] COLUMN_NAMES = new String[] { UMLMessage.getMessage(UMLMessage.LABEL_NAME),
        UMLMessage.getMessage(UMLMessage.LABEL_QUALIFIED_NAME) };

    /** addButton */
    private Button addButton;

    /** dialog */
    protected TypeSelectDialog dialog;

    /**
     * 
     * 
     * @return Operation
     */
    private Operation getData() {
        return (Operation) this.getSelectedModel();
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#createMainComposite()
     */
    @Override
    public void createMainComposite() {
        sectionComposite = getWidgetFactory().createComposite(parent);
        sectionLayout = new GridLayout(1, false);
        sectionComposite.setLayout(sectionLayout);

        @SuppressWarnings("unused")
        CLabel raisedExceptionsLabel = getWidgetFactory().createCLabel(sectionComposite,
            UMLMessage.LABEL_OCCOURED_EXEPTION + UICoreConstant.UMLSECTION_CONSTANTS__COLON_TEXT);

        tableViewerComposite = getWidgetFactory().createComposite(sectionComposite);
        sectionLayout = new GridLayout();
        tableViewerComposite.setLayout(sectionLayout);
        gridData = new GridData(GridData.FILL_BOTH);
        tableViewerComposite.setLayoutData(gridData);

        tableViewer = new TableViewer(tableViewerComposite, SWT.BORDER | SWT.FULL_SELECTION);
        table = tableViewer.getTable();
        gridData = new GridData(GridData.FILL_BOTH);
        gridData.heightHint = 100;
        table.setLayoutData(gridData);
        table.setLinesVisible(true);
        table.setHeaderVisible(true);
        tableViewer.setUseHashlookup(true);
        tableViewer.setColumnProperties(COLUMN_NAMES);
        tableViewer.setContentProvider(new ExceptionSectionContentProvider());

        for (int i = 0; i < COLUMN_NAMES.length; i++) {
            viewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
            viewerColumn.getColumn().setText(COLUMN_NAMES[i]);
            viewerColumn.getColumn().pack();
            viewerColumn.getColumn().setAlignment(SWT.LEFT);
            viewerColumn.getColumn().setMoveable(false);
            viewerColumn.setLabelProvider(new ExceptionSectionLabelProvider(i));
        }

        // 팝업메뉴
        Menu menu = new Menu(table);
        MenuItem addItem = new MenuItem(menu, SWT.CASCADE);
        addItem.addSelectionListener(new SelectionAdapter() {
            /**
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetSelected(SelectionEvent e) {
                addType();
                tableViewer.refresh();
                packColumns();
            }
        });
        addItem.setText(UMLMessage.getMessage(UMLMessage.LABEL_ADD)
            + UICoreConstant.UMLSECTION_CONSTANTS__TRIPLE_DOT_TEXT);
        MenuItem deleteItem = new MenuItem(menu, SWT.CASCADE);
        deleteItem.addSelectionListener(new SelectionAdapter() {
            /**
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetSelected(SelectionEvent e) {
                removeType();
                tableViewer.refresh();
                packColumns();
            }
        });
        deleteItem.setText(UMLMessage.getMessage(UMLMessage.LABEL_REMOVE));
        table.setMenu(menu);

        // 버튼
        buttonComposite = getWidgetFactory().createComposite(sectionComposite);
        sectionLayout = new GridLayout(3, true);
        buttonComposite.setLayout(sectionLayout);
        addButton = getWidgetFactory().createButton(buttonComposite,
            UMLMessage.getMessage(UMLMessage.LABEL_ADD) + UICoreConstant.UMLSECTION_CONSTANTS__TRIPLE_DOT_TEXT,
            SWT.NONE);
        gridData = new GridData();
        gridData.horizontalAlignment = GridData.FILL;
        addButton.setLayoutData(gridData);
        addButton.addListener(SWT.MouseDown, new Listener() {
            /**
             * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
             */
            public void handleEvent(Event event) {
                addType();
                tableViewer.refresh();
                packColumns();
            }
        });
        Button deleteButton = getWidgetFactory().createButton(buttonComposite,
            UMLMessage.getMessage(UMLMessage.LABEL_DELETE),
            SWT.NONE);
        gridData = new GridData();
        gridData.horizontalAlignment = GridData.FILL;
        deleteButton.setLayoutData(gridData);
        deleteButton.addListener(SWT.MouseDown, new Listener() {
            /**
             * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
             */
            public void handleEvent(Event event) {
                removeType();
                tableViewer.refresh();
                packColumns();
            }
        });
        Button searchButton = getWidgetFactory().createButton(buttonComposite,
            UMLMessage.getMessage(UMLMessage.LABEL_NAVIGATE),
            SWT.NONE);
        gridData = new GridData();
        gridData.horizontalAlignment = GridData.FILL;
        searchButton.setLayoutData(gridData);
        searchButton.addListener(SWT.MouseDown, new Listener() {
            /**
             * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
             */
            public void handleEvent(Event event) {
                // TODO 트리에서 탐색하는 기능
            }
        });
    }

    /**
     * 
     * void
     */
    private void addType() {
        dialog = new TypeSelectDialog(addButton.getShell(), TypeSelectDialogType.RAISED_EXCEPTION, getData());
        dialog.setTitle(UMLMessage.LABEL_SELECT_ELEMENT);
        if (dialog.open() == TypeSelectDialog.OK) {
            DomainUtil.run(new TransactionalAction() {
                /**
                 * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                 */
                @Override
                public void doExecute() {
                    getData().getRaisedExceptions().add((Type) dialog.getFirstResult());
                }
            });
        }
    }

    /**
     * 
     * void
     */
    private void removeType() {
        final IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
        DomainUtil.run(new TransactionalAction() {
            /**
             * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
             */
            @Override
            public void doExecute() {
                getData().getRaisedExceptions().remove((Type) selection.getFirstElement());
            }
        });
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#refreshChildren()
     */
    @Override
    public void refreshChildren() {
        tableViewer.setInput(this.getData());
        packColumns();
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#setListener()
     */
    @Override
    protected void setListener() {
        // TODO Auto-generated method stub

    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#unsetListener()
     */
    @Override
    protected void unsetListener() {
        // TODO Auto-generated method stub

    }

}
