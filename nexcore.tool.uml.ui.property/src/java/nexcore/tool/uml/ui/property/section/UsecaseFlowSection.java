/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.property.section;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.transaction.TransactionalAction;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.model.usecasedetail.FlowObject;
import nexcore.tool.uml.model.usecasedetail.UseCaseDetail;
import nexcore.tool.uml.ui.core.UICoreConstant;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ICellEditorListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TableViewerEditor;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.uml2.uml.UseCase;


/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.section</li>
 * <li>설  명 : UsecaseFlowSection</li>
 * <li>작성일 : 2010. 5. 3.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
public abstract class UsecaseFlowSection extends AbstractPropertyCommonSection {

    /**
     * 
     * 
     * @return UseCase
     */
    protected UseCase getData() {
        return (UseCase) this.getSelectedModel();
    }

    /** COLUMN_NAMES_OF_OPERATION */
    private static final String[] COLUMN_NAMES_OF_OPERATION = new String[] { UMLMessage.LABEL_NUMBER,
        UMLMessage.LABEL_FLOW_ID, UMLMessage.LABEL_FLOW_NAME,
    // UMLMessage.LABEL_FLOW_OVERVIEW, UMLMessage.LABEL_FLOW_DESCRIPTION };
    };

    /** COLUMN_WIDTH_OF_OPERATION */
    private static final int[] COLUMN_WIDTH_OF_OPERATION = new int[] { 55, 100, 180 };

    /**
     * 
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertySectionWithAdapter#createMainComposite()
     */
    @Override
    protected void createMainComposite() {

        gridData = new GridData(GridData.FILL_BOTH);
        parent.setLayoutData(gridData);
        sectionComposite = getWidgetFactory().createComposite(parent);
        sectionLayout = new GridLayout(1, false);
        sectionComposite.setLayout(sectionLayout);

        // 버튼
        buttonComposite = getWidgetFactory().createComposite(sectionComposite);
        sectionLayout = new GridLayout(4, true);
        buttonComposite.setLayout(sectionLayout);

        gridData = new GridData();
        gridData.verticalAlignment = GridData.HORIZONTAL_ALIGN_BEGINNING;
        buttonComposite.setLayoutData(gridData);

        createAddButton();
        createDeleteButton();
        createMoveUpButton();
        createMoveDownButton();
        createTableViewer();
        createTable();
        settingColumns();

        createDescriptionText();

        refreshChildren();
    }

    /**
     * createDescriptionText
     *   void
     */
    private void createDescriptionText() {

        Group group = getWidgetFactory().createGroup(tableViewerComposite, "");
        GridData gridData = new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL);
        group.setLayoutData(gridData);
        group.setBackgroundMode(SWT.INHERIT_FORCE);

        GridLayout gridLayout = new GridLayout(2, false);
        group.setLayout(gridLayout);

        idLabel = getWidgetFactory().createLabel(group, UMLMessage.LABEL_FLOW_ID);
        idText = getWidgetFactory().createText(group, "", SWT.BORDER);
        gridData = new GridData(GridData.FILL_HORIZONTAL);
        idText.setLayoutData(gridData);
        // idText.addModifyListener(new ModifyListener() {
        //
        // public void modifyText(ModifyEvent e) {
        // TableItem[] tableItem = tableViewer.getTable().getSelection();
        // if (tableItem.length > 0) {
        // if (tableItem[0].getData() instanceof FlowObject) {
        //						 
        // }
        // }
        //
        // }
        //
        // });
        idText.addFocusListener(new FocusListener() {

            public void focusGained(FocusEvent e) {
                // TODO Auto-generated method stub

            }

            public void focusLost(FocusEvent e) {
                // TODO Auto-generated method stub
                DomainUtil.run(new TransactionalAction() {
                    /**
                     * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                     */
                    @Override
                    public void doExecute() {
                        FlowObject flowObject = null;
                        TableItem[] tableItem = tableViewer.getTable().getSelection();
                        if (tableItem.length > 0) {
                            if (tableItem[0].getData() instanceof FlowObject) {
                                flowObject = (FlowObject) tableItem[0].getData();
                            }
                        }
                        if (flowObject != null) {
                            flowObject.setFlowId(idText.getText());
                            tableViewer.refresh();
                        }

                    }
                });
            }

        });

        nameLabel = getWidgetFactory().createLabel(group, UMLMessage.LABEL_FLOW_NAME);
        nameText = getWidgetFactory().createText(group, "", SWT.BORDER);
        gridData = new GridData(GridData.FILL_HORIZONTAL);
        nameText.setLayoutData(gridData);
        nameText.addFocusListener(new FocusListener() {

            public void focusGained(FocusEvent e) {
                // TODO Auto-generated method stub

            }

            public void focusLost(FocusEvent e) {
                // TODO Auto-generated method stub
                DomainUtil.run(new TransactionalAction() {
                    /**
                     * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                     */
                    @Override
                    public void doExecute() {
                        FlowObject flowObject = null;
                        TableItem[] tableItem = tableViewer.getTable().getSelection();
                        if (tableItem.length > 0) {
                            if (tableItem[0].getData() instanceof FlowObject) {
                                flowObject = (FlowObject) tableItem[0].getData();
                            }
                        }
                        if (flowObject != null) {
                            flowObject.setFlowName(nameText.getText());
                            tableViewer.refresh();
                        }
                    }
                });
            }

        });

        // overviewLabel = getWidgetFactory().createLabel(group,
        // UMLMessage.LABEL_FLOW_OVERVIEW);
        // overViewText = getWidgetFactory().createText(group, "",
        // SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
        // gridData = new GridData(GridData.FILL_HORIZONTAL);
        // // gridData.grabExcessVerticalSpace = true;
        // overViewText.setLayoutData(gridData);
        // overViewText.addFocusListener(new FocusListener() {
        //
        // public void focusGained(FocusEvent e) {
        // // TODO Auto-generated method stub
        //
        // }
        //
        // public void focusLost(FocusEvent e) {
        // // TODO Auto-generated method stub
        // DomainUtil.run(new TransactionalAction() {
        // /**
        // * @see
        // nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
        // */
        // @Override
        // public void doExecute() {
        // FlowObject flowObject = null;
        // TableItem[] tableItem = tableViewer.getTable()
        // .getSelection();
        // if (tableItem.length > 0) {
        // if (tableItem[0].getData() instanceof FlowObject) {
        // flowObject = (FlowObject) tableItem[0]
        // .getData();
        // }
        // }
        // if (flowObject != null) {
        // flowObject.setFlowOverview(overViewText.getText());
        // }
        // }
        // });
        // }
        //
        // });
        // overViewText.addModifyListener(new ModifyListener() {
        // public void modifyText(final ModifyEvent e) {
        //				
        // }
        // });

        descriptionLabel = getWidgetFactory().createLabel(group, UMLMessage.LABEL_FLOW_DESCRIPTION);
        descText = getWidgetFactory().createText(group, "", SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
        gridData = new GridData(GridData.FILL_VERTICAL | GridData.FILL_HORIZONTAL);
        gridData.grabExcessVerticalSpace = true;
        descText.setLayoutData(gridData);
        descText.addFocusListener(new FocusListener() {

            public void focusGained(FocusEvent e) {
                // TODO Auto-generated method stub

            }

            public void focusLost(FocusEvent e) {
                DomainUtil.run(new TransactionalAction() {
                    /**
                     * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                     */
                    @Override
                    public void doExecute() {
                        FlowObject flowObject = null;
                        TableItem[] tableItem = tableViewer.getTable().getSelection();
                        if (tableItem.length > 0) {
                            if (tableItem[0].getData() instanceof FlowObject) {
                                flowObject = (FlowObject) tableItem[0].getData();
                            }
                        }
                        if (flowObject != null) {
                            flowObject.setFlowDescription(descText.getText());
                        }
                    }
                });

            }

        });
        // descText.addModifyListener(new ModifyListener() {
        // public void modifyText(final ModifyEvent e) {
        //				
        // }
        // });

    }

    /**
     * setTextID
     *   void
     */
    private void setTextID() {
        FlowObject flowObject = null;
        TableItem[] tableItem = tableViewer.getTable().getSelection();
        if (tableItem.length > 0) {
            if (tableItem[0].getData() instanceof FlowObject) {
                flowObject = (FlowObject) tableItem[0].getData();
            }
        }
        if (flowObject != null) {

            if (idText != null && !idText.isDisposed()) {
                idLabel.setEnabled(true);
                idText.setEnabled(true);
                if (flowObject.getFlowId() != null) {
                    idText.setText(flowObject.getFlowId());
                } else {
                    idText.setText("");
                }
            }
        } else {
            idText.setText("");
            idLabel.setEnabled(false);
            idText.setEnabled(false);
        }
    }

    /**
     * setTextName
     *   void
     */
    private void setTextName() {
        FlowObject flowObject = null;
        TableItem[] tableItem = tableViewer.getTable().getSelection();
        if (tableItem.length > 0) {
            if (tableItem[0].getData() instanceof FlowObject) {
                flowObject = (FlowObject) tableItem[0].getData();
            }
        }
        if (flowObject != null) {
            nameLabel.setEnabled(true);
            if (nameText != null && !nameText.isDisposed()) {
                nameText.setEnabled(true);
                if (flowObject.getFlowName() != null) {
                    nameText.setText(flowObject.getFlowName());
                } else {
                    nameText.setText("");
                }
            }
        } else {
            nameText.setText("");
            nameLabel.setEnabled(false);
            nameText.setEnabled(false);
        }
    }

    // private void setTextOverView() {
    // FlowObject flowObject = null;
    // TableItem[] tableItem = tableViewer.getTable().getSelection();
    // if (tableItem.length > 0) {
    // if (tableItem[0].getData() instanceof FlowObject) {
    // flowObject = (FlowObject) tableItem[0].getData();
    // }
    // }
    // if (flowObject != null) {
    // overviewLabel.setEnabled(true);
    // if (overViewText != null && !overViewText.isDisposed()) {
    // overViewText.setEnabled(true);
    // if (flowObject.getFlowOverview() != null) {
    // overViewText.setText(flowObject.getFlowOverview());
    // } else {
    // overViewText.setText("");
    // }
    // }
    // } else {
    // overViewText.setText("");
    // overViewText.setEnabled(false);
    // overviewLabel.setEnabled(false);
    // }
    // }

    /**
     * setTextDescription
     *   void
     */
    private void setTextDescription() {
        FlowObject flowObject = null;
        TableItem[] tableItem = tableViewer.getTable().getSelection();
        if (tableItem.length > 0) {
            if (tableItem[0].getData() instanceof FlowObject) {
                flowObject = (FlowObject) tableItem[0].getData();
            }
        }
        if (flowObject != null) {
            descText.setEnabled(true);
            descriptionLabel.setEnabled(true);
            if (descText != null && !descText.isDisposed()) {

                if (flowObject.getFlowDescription() != null) {
                    descText.setText(flowObject.getFlowDescription());
                } else {
                    descText.setText("");
                }
            }
        } else {
            descText.setText("");
            descriptionLabel.setEnabled(false);
            descText.setEnabled(false);
        }
    }

    /**
     * descText
     */
    private Text descText;

    // private Text overViewText;
    /**
     * nameText
     */
    private Text nameText;

    /**
     * idText
     */
    private Text idText;

    /**
     * nameLabel
     */
    private Label nameLabel;

    /**
     * idLabel
     */
    private Label idLabel;

    /**
     * overviewLabel
     */
    private Label overviewLabel;

    /**
     * descriptionLabel
     */
    private Label descriptionLabel;

    /**
     * 
     * void
     */
    private void settingColumns() {
        ColumnViewerEditorActivationStrategy actSupport = new ColumnViewerEditorActivationStrategy(tableViewer) {
            /**
             * @see org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy#isEditorActivationEvent(org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent)
             */
            protected boolean isEditorActivationEvent(ColumnViewerEditorActivationEvent event) {
                return event.eventType == ColumnViewerEditorActivationEvent.MOUSE_CLICK_SELECTION;
            }
        };

        TableViewerEditor.create(tableViewer, actSupport, ColumnViewerEditor.KEYBOARD_ACTIVATION);

        for (int i = 0; i < COLUMN_NAMES_OF_OPERATION.length; i++) {
            viewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
            viewerColumn.getColumn().setText(COLUMN_NAMES_OF_OPERATION[i]);
            viewerColumn.getColumn().setWidth(COLUMN_WIDTH_OF_OPERATION[i]);

            viewerColumn.getColumn().setAlignment(SWT.LEFT);
            viewerColumn.getColumn().setMoveable(false);
            final int columnNumber = i;
            viewerColumn.setLabelProvider(new ColumnLabelProvider() {

                @Override
                public String getText(Object element) {
                    if (columnNumber == 0) {

                        for (int i = 0; i < tableViewer.getTable().getItems().length; i++) {
                            Object obj = tableViewer.getTable().getItems()[i].getData();
                            if (obj != null && tableViewer.getTable().getItems()[i].getData().equals(element)) {
                                return String.valueOf(i + 1);
                            }
                        }
                        return String.valueOf(1);

                    } else if (columnNumber == 1) {
                        return ((FlowObject) element).getFlowId();
                    } else if (columnNumber == 2) {
                        return ((FlowObject) element).getFlowName();
                        // } else if (columnNumber == 3) {
                        // return ((FlowObject) element).getFlowOverview();
                        // } else if (columnNumber == 4) {
                        // return ((FlowObject) element).getFlowDescription();
                    }
                    return super.getText(element);
                }

            });
            viewerColumn.setEditingSupport(new EditingSupport(tableViewer) {

                /**
                 * @see org.eclipse.jface.viewers.EditingSupport#canEdit(java.lang.Object)
                 */
                protected boolean canEdit(Object element) {

                    return false;

                }

                /**
                 * @see org.eclipse.jface.viewers.EditingSupport#getCellEditor(java.lang.Object)
                 */
                protected CellEditor getCellEditor(Object element) {
                    return getCellEditors(columnNumber);
                }

                /**
                 * @see org.eclipse.jface.viewers.EditingSupport#getValue(java.lang.Object)
                 */
                protected Object getValue(Object element) {

                    if (element == null)
                        return null;
                    switch (columnNumber) {
                        case 0:

                            for (int i = 0; i < tableViewer.getTable().getItems().length; i++) {
                                if (tableViewer.getTable().getItems()[i].getData().equals(element)) {
                                    return String.valueOf(i + 1);
                                }
                            }
                            return String.valueOf(1);
                        case 1:
                            String flowId = ((FlowObject) element).getFlowId();
                            return flowId == null ? "" : flowId;
                        case 2:
                            String flowName = ((FlowObject) element).getFlowName();

                            return flowName == null ? "" : flowName;
                            // case 3:
                            // return ((FlowObject) element).getFlowOverview();
                            // case 4:
                            // return ((FlowObject)
                            // element).getFlowDescription();
                        default:
                            break;
                    }
                    return null;
                }

                /**
                 * @see org.eclipse.jface.viewers.EditingSupport#setValue(java.lang.Object,
                 *      java.lang.Object)
                 */
                protected void setValue(final Object element, final Object value) {

                    if (null == value || element == null) {
                        return;
                    }
                    if (value != null) {
                        switch (columnNumber) {
                            case 0:
                                break;
                            case 1:
                                DomainUtil.run(new TransactionalAction() {
                                    /**
                                     * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                                     */
                                    @Override
                                    public void doExecute() {
                                        ((FlowObject) element).setFlowId((String) value);
                                        refreshText();
                                    }
                                });
                                break;
                            case 2:
                                DomainUtil.run(new TransactionalAction() {
                                    /**
                                     * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                                     */
                                    @Override
                                    public void doExecute() {
                                        ((FlowObject) element).setFlowName((String) value);
                                        refreshText();
                                    }
                                });
                                break;
                            // case 3:
                            // DomainUtil.run(new TransactionalAction() {
                            // /**
                            // * @see
                            // nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                            // */
                            // @Override
                            // public void doExecute() {
                            // ((FlowObject) element).setFlowOverview((String)
                            // value);
                            // }
                            // });
                            // break;
                            // case 4:
                            // DomainUtil.run(new TransactionalAction() {
                            // /**
                            // * @see
                            // nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                            // */
                            // @Override
                            // public void doExecute() {
                            // ((FlowObject)
                            // element).setFlowDescription((String)
                            // value);
                            // descText.setText((String) value);
                            // }
                            // });
                            // break;
                            default:
                                break;
                        }
                    }
                    tableViewer.refresh();
                }

            });
        }
    }

    /**
     * 
     * void
     */
    private void createTable() {

        /*
         * NOTE: MeasureItem, PaintItem and EraseItem are called repeatedly.
         * Therefore, it is critical for performance that these methods be as
         * efficient as possible.
         */
        Listener paintListener = new Listener() {
            public void handleEvent(Event event) {

                switch (event.type) {
                    case SWT.MeasureItem: {
                        TableItem item = (TableItem) event.item;
                        String text = item.getText(event.index);
                        Point size = event.gc.textExtent(text);
                        event.width = size.x;
                        event.height = Math.max(event.height, size.y);
                        break;
                    }
                    case SWT.PaintItem: {
                        TableItem item = (TableItem) event.item;
                        String text = item.getText(event.index);
                        Point size = event.gc.textExtent(text);
                        int offset2 = event.index == 0 ? Math.max(0, (event.height - size.y) / 2) : 0;
                        event.gc.drawText(text, event.x, event.y + offset2, true);
                        break;
                    }
                    case SWT.EraseItem: {
                        event.detail &= ~SWT.FOREGROUND;
                        break;
                    }
                }
            }

        };

        table.addListener(SWT.MeasureItem, paintListener);
        table.addListener(SWT.PaintItem, paintListener);
        table.addListener(SWT.EraseItem, paintListener);
    }

    /**
     * 
     * void
     */
    private void createTableViewer() {
        tableViewerComposite = getWidgetFactory().createComposite(sectionComposite);
        sectionLayout = new GridLayout(2, false);

        tableViewerComposite.setLayout(sectionLayout);
        gridData = new GridData(GridData.FILL_BOTH);
        tableViewerComposite.setLayoutData(gridData);

        tableViewer = new TableViewer(tableViewerComposite, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);

        tableViewer.getTable().addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent e) {
                refreshChildren();

            }

            public void widgetDefaultSelected(SelectionEvent e) {
            }
        });

        table = tableViewer.getTable();
        gridData = new GridData(GridData.FILL_VERTICAL);

        table.setLayoutData(gridData);
        table = tableViewer.getTable();
        table.setLinesVisible(true);
        table.setHeaderVisible(true);

        tableViewer.setUseHashlookup(true);
        tableViewer.setColumnProperties(COLUMN_NAMES_OF_OPERATION);
        tableViewer.setContentProvider(new IStructuredContentProvider() {

            public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
            }

            public void dispose() {
            }

            public Object[] getElements(Object inputElement) {
                return getFlowList((UseCase) inputElement);
            }
        });
    }

    /**
     * '아래로' 버튼 void
     */
    private void createMoveDownButton() {

        Button downButton = getWidgetFactory().createButton(buttonComposite,
            UMLMessage.getMessage(UMLMessage.LABEL_MOVEDOWN),
            SWT.PUSH); //$NON-NLS-1$
        gridData = new GridData();
        gridData.horizontalAlignment = GridData.FILL;
        downButton.setLayoutData(gridData);
        downButton.addListener(SWT.MouseDown, new Listener() {
            /**
             * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
             */
            public void handleEvent(Event event) {
                DomainUtil.run(new TransactionalAction() {
                    /**
                     * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                     */
                    @Override
                    public void doExecute() {
                        IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
                        EAnnotation annotation = getData().getEAnnotation(UICoreConstant.PROJECT_CONSTANTS__USECASE_DETAIL);
                        if (null != annotation && annotation instanceof UseCaseDetail) {
                            UseCaseDetail detail = (UseCaseDetail) annotation;
                            moveDown(detail, selection.getFirstElement());
                        }
                    }

                });
                tableViewer.refresh();
                refreshText();
                packColumns();
            }
        });
    }

    /**
     * refreshText
     *   void
     */
    private void refreshText() {
        tableViewer.refresh();
        setTextID();
        setTextName();
        // setTextOverView();
        setTextDescription();
    }

    /**
     * '위로' 버튼 void
     */
    private void createMoveUpButton() {

        Button upButton = getWidgetFactory().createButton(buttonComposite,
            UMLMessage.getMessage(UMLMessage.LABEL_MOVEUP),
            SWT.PUSH); //$NON-NLS-1$
        gridData = new GridData();
        gridData.horizontalAlignment = GridData.FILL;
        upButton.setLayoutData(gridData);
        upButton.addListener(SWT.MouseDown, new Listener() {
            /**
             * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
             */
            public void handleEvent(Event event) {
                DomainUtil.run(new TransactionalAction() {
                    /**
                     * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                     */
                    @Override
                    public void doExecute() {
                        IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
                        EAnnotation annotation = getData().getEAnnotation(UICoreConstant.PROJECT_CONSTANTS__USECASE_DETAIL);
                        if (null != annotation && annotation instanceof UseCaseDetail) {
                            UseCaseDetail detail = (UseCaseDetail) annotation;
                            moveUp(detail, selection.getFirstElement());
                        }
                    }
                });
                tableViewer.refresh();
                refreshText();
                packColumns();
            }
        });
    }

    /**
     * '삭제' 버튼 void
     */
    private void createDeleteButton() {

        Button deleteButton = getWidgetFactory().createButton(buttonComposite,
            UMLMessage.getMessage(UMLMessage.LABEL_DELETE),
            SWT.PUSH);
        gridData = new GridData();
        gridData.horizontalAlignment = GridData.FILL;
        deleteButton.setLayoutData(gridData);
        deleteButton.addListener(SWT.MouseDown, new Listener() {
            /**
             * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
             */
            public void handleEvent(Event event) {
                DomainUtil.run(new TransactionalAction() {
                    /**
                     * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                     */
                    @Override
                    public void doExecute() {
                        IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
                        EAnnotation annotation = getData().getEAnnotation(UICoreConstant.PROJECT_CONSTANTS__USECASE_DETAIL);
                        if (null != annotation && annotation instanceof UseCaseDetail) {
                            UseCaseDetail detail = (UseCaseDetail) annotation;
                            deleteFlow(detail, selection.getFirstElement());
                        }
                    }
                });
                tableViewer.refresh();
                refreshText();
                packColumns();
            }
        });
    }

    /**
     * '추가' 버튼 void
     */
    private void createAddButton() {

        Button addButton = getWidgetFactory().createButton(buttonComposite,
            UMLMessage.getMessage(UMLMessage.LABEL_ADD),
            SWT.PUSH);
        gridData = new GridData();
        gridData.horizontalAlignment = GridData.FILL;
        addButton.setLayoutData(gridData);
        addButton.addListener(SWT.MouseDown, new Listener() {
            /**
             * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
             */
            public void handleEvent(Event event) {
                DomainUtil.run(new TransactionalAction() {
                    /**
                     * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                     */
                    @Override
                    public void doExecute() {
                        setFlowList();
                    }
                });
                tableViewer.refresh();
                refreshText();
                packColumns();
            }

        });
    }

    /** cellEdotor */
    private CellEditor cellEditor;

    public class CellListener implements ICellEditorListener, KeyListener {

        /**
         * @see org.eclipse.jface.viewers.ICellEditorListener#applyEditorValue()
         */
        public void applyEditorValue() {
            // TODO Auto-generated method stub
        }

        /**
         * @see org.eclipse.jface.viewers.ICellEditorListener#cancelEditor()
         */
        public void cancelEditor() {
            // TODO Auto-generated method stub
        }

        /**
         * @see org.eclipse.jface.viewers.ICellEditorListener#editorValueChanged(boolean,
         *      boolean)
         */
        public void editorValueChanged(boolean oldValidState, boolean newValidState) {
            // TODO Auto-generated method stub
        }

        /**
         * @see org.eclipse.swt.events.KeyListener#keyPressed(org.eclipse.swt.events.KeyEvent)
         */
        public void keyPressed(KeyEvent e) {
            if (13 == e.keyCode) {}
        }

        /**
         * @see org.eclipse.swt.events.KeyListener#keyReleased(org.eclipse.swt.events.KeyEvent)
         */
        public void keyReleased(KeyEvent e) {
            // TODO Auto-generated method stub
        }

    }

    /**
     * 테이블 뷰어에서 선택된 셀에 해당하는 에디터를 생성.
     * 
     * @param columnNumber
     * @return CellEditor
     */
    public CellEditor getCellEditors(int columnNumber) {
        // setTextOverView();
        setTextDescription();
        setTextID();
        setTextName();
        switch (columnNumber) {
            case 0:
                cellEditor = new TextCellEditor(tableViewer.getTable(), SWT.READ_ONLY);
                return cellEditor;
            case 1:
            case 2:
                cellEditor = new TextCellEditor(tableViewer.getTable(), SWT.MULTI | SWT.WRAP);
                return cellEditor;
                // case 3:
                // case 4:
                // cellEditor = new TextCellEditor(tableViewer.getTable(),
                // SWT.MULTI
                // | SWT.WRAP | SWT.V_SCROLL);
                // return cellEditor;
            default:
                return null;
        }

    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertySectionWithAdapter#refreshChildren()
     */
    @Override
    protected void refreshChildren() {

        if (tableViewer.getContentProvider() != null) {
            tableViewer.setInput(this.getData());
//            packColumns();
        }

        if (!idText.isDisposed()) {
            setTextID();
        }
        if (!nameText.isDisposed()) {
            setTextName();
        }
        // if (!overViewText.isDisposed()) {
        // setTextOverView();
        // }
        if (!descText.isDisposed()) {
            setTextDescription();
        }

    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertySectionWithAdapter#setListener()
     */
    @Override
    protected void setListener() {
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertySectionWithAdapter#unsetListener()
     */
    @Override
    protected void unsetListener() {
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#packColumns()
     */
    @Override
    protected void packColumns() {
        super.packColumns();
    }

    /**
     * setFlowList
     *   void
     */
    protected abstract void setFlowList();

    /**
     * getFlowList
     *  
     * @param useCase
     * @return Object[]
     */
    protected abstract Object[] getFlowList(UseCase useCase);

    /**
     * moveDown
     *  
     * @param usecaseDetail
     * @param selectedElement void
     */
    protected abstract void moveDown(UseCaseDetail usecaseDetail, Object selectedElement);

    /**
     * moveUp
     *  
     * @param usecaseDetail
     * @param selectedElement void
     */
    protected abstract void moveUp(UseCaseDetail usecaseDetail, Object selectedElement);

    /**
     * deleteFlow
     *  
     * @param usecaseDetail
     * @param selectedElement void
     */
    protected abstract void deleteFlow(UseCaseDetail usecaseDetail, Object selectedElement);
}
