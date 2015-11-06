/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.property.section;

import java.util.Iterator;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.UMLHelper;
import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.manager.transaction.TransactionalAction;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.property.UMLOwnedParameterCellEditor;
import nexcore.tool.uml.ui.property.provider.OperationSectionContentProvider;
import nexcore.tool.uml.ui.property.provider.OperationSectionLabelProvider;
import nexcore.tool.uml.ui.property.type.VisibilityType;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
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
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.uml2.uml.BehavioralFeature;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Feature;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.VisibilityKind;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.section</li>
 * <li>설 명 : OperationSection</li>
 * <li>작성일 : 2009. 12. 30.</li>
 * <li>작성자 : 변영민</li>
 * </ul>
 */
public class OperationSection extends AbstractPropertyCommonSection implements FocusListener {

    /** _IMAGE_COLUMN */
    private static final int _IMAGE_COLUMN = 0;

    /** _NAME_COLUMN */
    private static final int _NAME_COLUMN = 1;

    /** _VISIBILITY_COLUMN */
    private static final int _VISIBILITY_COLUMN = 2;

    /** _STATIC_COLUMN */
    private static final int _STATIC_COLUMN = 3;

    /** _ABSTRACT_COLUMN */
    private static final int _ABSTRACT_COLUMN = 4;

    /** _OWNED_PARAMETER_COLUMN */
    private static final int _OWNED_PARAMETER_COLUMN = 5;

    /** COLUMN_NAMES_OF_OPERATION */
    private static final String[] COLUMN_NAMES_OF_OPERATION = new String[] {
        UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING, UMLMessage.LABEL_NAME, UMLMessage.LABEL_VISIBILITY,
        UMLMessage.LABEL_STATIC, UMLMessage.LABEL_ABSTRACT, UMLMessage.LABEL_OWNED_PARAMETER };

    /**
     * COLUMN_WIDTHS
     */
    private static final int[] COLUMN_WIDTHS = new int[] {50, 150, 100,100,100, 200};
    
    /** PREFIX */
    private static final String PREFIX = UMLMessage.getMessage(UMLMessage.LABEL_OPERATION);

    /** cellEdotor */
    private CellEditor cellEdotor;

    /**
     * 
     * 
     * @return Classifier
     */
    private Classifier getData() {
        return (Classifier) this.getSelectedModel();
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#createMainComposite()
     */
    @Override
    public void createMainComposite() {
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
                        createOperation();
                    }
                });
                tableViewer.refresh();
                packColumns();
            }

        });
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
                        removeOperation();
                    }
                });
                tableViewer.refresh();
                packColumns();
            }
        });

        Button upperButton = getWidgetFactory().createButton(buttonComposite,
            UMLMessage.getMessage(UMLMessage.LABEL_UPPER),
            SWT.PUSH);
        gridData = new GridData();
        gridData.horizontalAlignment = GridData.FILL;
        upperButton.setLayoutData(gridData);
        upperButton.addListener(SWT.MouseDown, new Listener() {
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
                        upperOperation();
                    }
                });
                tableViewer.refresh();
                packColumns();
            }
        });

        Button downButton = getWidgetFactory().createButton(buttonComposite,
            UMLMessage.getMessage(UMLMessage.LABEL_DOWN),
            SWT.PUSH);
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
                        downOperation();
                    }
                });
                tableViewer.refresh();
                packColumns();
            }
        });

        tableViewerComposite = getWidgetFactory().createComposite(sectionComposite);
        sectionLayout = new GridLayout();
        tableViewerComposite.setLayout(sectionLayout);
        gridData = new GridData(GridData.FILL_BOTH);
        tableViewerComposite.setLayoutData(gridData);

        tableViewer = new TableViewer(tableViewerComposite, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
        table = tableViewer.getTable();
        gridData = new GridData(GridData.FILL_BOTH);
        table.setLayoutData(gridData);
        table = tableViewer.getTable();
        table.setLinesVisible(true);
        table.setHeaderVisible(true);
        tableViewer.setUseHashlookup(true);
        tableViewer.setColumnProperties(COLUMN_NAMES_OF_OPERATION);
        tableViewer.setContentProvider(new OperationSectionContentProvider());

        // 2012-09-17
        // 더블클릭으로 Operation 추가 편의 기능(추가 버튼이 불편하다고하여 반영)
        tableViewer.getTable().addMouseListener(new MouseAdapter(){

            @Override
            public void mouseDoubleClick(MouseEvent e) {
                TableItem item = tableViewer.getTable().getItem(new Point(e.x, e.y));
                if (item == null) {
                    DomainUtil.run(new TransactionalAction() {
                        /**
                         * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                         */
                        @Override
                        public void doExecute() {
                            createOperation();
                        }
                    });
                    tableViewer.refresh();
                    packColumns();
                }
            }
        });
        
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
            viewerColumn.getColumn().setWidth(COLUMN_WIDTHS[i]);
            viewerColumn.getColumn().setAlignment(SWT.LEFT);
            viewerColumn.getColumn().setMoveable(false);
            final int columnNumber = i;
            viewerColumn.setLabelProvider(new OperationSectionLabelProvider(i));
            viewerColumn.setEditingSupport(new EditingSupport(tableViewer) {

                /**
                 * @see org.eclipse.jface.viewers.EditingSupport#canEdit(java.lang.Object)
                 */
                protected boolean canEdit(Object element) {
                    return true;
                }

                /**
                 * @see org.eclipse.jface.viewers.EditingSupport#getCellEditor(java.lang.Object)
                 */
                protected CellEditor getCellEditor(Object element) {
                    return getCellEditors(columnNumber, element);
                }

                /**
                 * @see org.eclipse.jface.viewers.EditingSupport#getValue(java.lang.Object)
                 */
                protected Object getValue(Object element) {
                    switch (columnNumber) {
                        case _NAME_COLUMN:
                            return ((NamedElement) element).getName();
                        case _VISIBILITY_COLUMN:
                            return ((org.eclipse.uml2.uml.Operation) element).getVisibility().toString();
                        case _STATIC_COLUMN:
                            return Boolean.toString(((org.eclipse.uml2.uml.Operation) element).isStatic());
                        case _ABSTRACT_COLUMN:
                            return Boolean.toString(((org.eclipse.uml2.uml.Operation) element).isAbstract());
                        case _OWNED_PARAMETER_COLUMN:
                            return getParameterString((org.eclipse.uml2.uml.Operation) element);
                        default:
                            break;
                    }
                    return null;
                }

                private String getParameterString(Operation operation) {
                    StringBuffer sb = new StringBuffer();
                    EList list = operation.getOwnedParameters();
                    if (list != null) {
                        for (Iterator iter = list.iterator(); iter.hasNext();) {
                            Parameter p = (Parameter) iter.next();
                            if (sb.length() > 0)
                                sb.append(",");
                            sb.append(p.getName());
                        }
                    }
                    return sb.toString();

                }

                /**
                 * @see org.eclipse.jface.viewers.EditingSupport#setValue(java.lang.Object,
                 *      java.lang.Object)
                 */
                protected void setValue(Object element, Object value) {
                    if (null == value) {
                        return;
                    }
                    if (value != null) {
                        switch (columnNumber) {
                            case _NAME_COLUMN:
                                setNameToElement((Operation) element, (String) value);
                                break;
                            case _VISIBILITY_COLUMN:
                                setVisibilityToElement((Operation) element, (String) value);
                                break;
                            case _STATIC_COLUMN:
                                setIsStaticToElement((Operation) element, (String) value);
                                break;
                            case _ABSTRACT_COLUMN:
                                setIsAbstractToElement((Operation) element, (String) value);
                                break;
                            case _OWNED_PARAMETER_COLUMN:
                                break;
                            default:
                                return;
                        }
                    }
                    tableViewer.refresh();
                }

                /**
                 * 모델에 이름을 세팅.
                 * 
                 * @param element
                 * @param value
                 *            void
                 */
                private void setNameToElement(final NamedElement element, final String value) {
                    if (UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING.equals(value)) {
                        // TODO 이름이 입력되지 않았다는 경고 다이얼로그를 연다.
                        return;
                    } else {
                        DomainUtil.run(new TransactionalAction() {
                            /**
                             * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                             */
                            @Override
                            public void doExecute() {
                                element.setName(value);
                            }
                        });
                    }
                }

                /**
                 * 모델에 추상을 세팅.
                 * 
                 * @param element
                 * @param value
                 *            void
                 */
                private void setIsAbstractToElement(final BehavioralFeature element, final String value) {
                    DomainUtil.run(new TransactionalAction() {
                        /**
                         * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                         */
                        @Override
                        public void doExecute() {
                            if (value.equals(Boolean.toString(true))) {
                                ((org.eclipse.uml2.uml.Operation) element).setIsAbstract(true);
                            } else if (value.equals(Boolean.toString(false))) {
                                ((org.eclipse.uml2.uml.Operation) element).setIsAbstract(false);
                            }
                        }
                    });
                }

                /**
                 * 모델에 가시성을 세팅.
                 * 
                 * @param element
                 * @param value
                 *            void
                 */
                private void setVisibilityToElement(final NamedElement element, final String value) {
                    DomainUtil.run(new TransactionalAction() {
                        /**
                         * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                         */
                        @Override
                        public void doExecute() {
                            if (value.equals(VisibilityType.PUBLIC.toString()))
                                ((org.eclipse.uml2.uml.Operation) element).setVisibility(VisibilityKind.PUBLIC_LITERAL);
                            else if (value.equals(VisibilityType.PRIVATE.toString()))
                                ((org.eclipse.uml2.uml.Operation) element).setVisibility(VisibilityKind.PRIVATE_LITERAL);
                            else if (value.equals(VisibilityType.PROTECTED.toString()))
                                ((org.eclipse.uml2.uml.Operation) element).setVisibility(VisibilityKind.PROTECTED_LITERAL);
                            else if (value.equals(VisibilityType.PACKAGE.toString()))
                                ((org.eclipse.uml2.uml.Operation) element).setVisibility(VisibilityKind.PACKAGE_LITERAL);
                        }
                    });
                }

                /**
                 * 모델에 정적을 세팅.
                 * 
                 * @param element
                 * @param value
                 *            void
                 */
                private void setIsStaticToElement(final Feature element, final String value) {
                    DomainUtil.run(new TransactionalAction() {
                        /**
                         * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                         */
                        @Override
                        public void doExecute() {
                            if (value.equals(Boolean.toString(true))) {
                                ((org.eclipse.uml2.uml.Operation) element).setIsStatic(true);
                            } else if (value.equals(Boolean.toString(false))) {
                                ((org.eclipse.uml2.uml.Operation) element).setIsStatic(false);
                            }
                        }
                    });
                }
            });
        }

        // 팝업메뉴
        Menu menu = new Menu(table);
        MenuItem addItem = new MenuItem(menu, SWT.CASCADE);
        addItem.addSelectionListener(new SelectionAdapter() {
            /**
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetSelected(SelectionEvent e) {
                DomainUtil.run(new TransactionalAction() {
                    /**
                     * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                     */
                    @Override
                    public void doExecute() {
                        createOperation();
                    }
                });
                tableViewer.refresh();
                packColumns();
            }
        });
        addItem.setText(UMLMessage.getMessage(UMLMessage.LABEL_ADD_NEW_OPERATION));
        MenuItem deleteItem = new MenuItem(menu, SWT.CASCADE);
        deleteItem.addSelectionListener(new SelectionAdapter() {
            /**
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetSelected(SelectionEvent e) {
                DomainUtil.run(new TransactionalAction() {
                    /**
                     * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                     */
                    @Override
                    public void doExecute() {
                        removeOperation();
                    }
                });
                tableViewer.refresh();
                packColumns();
            }
        });
        deleteItem.setText(UMLMessage.getMessage(UMLMessage.LABEL_DELETE_FROM_MODEL));
        table.setMenu(menu);

    }

    /**
     * 테이블 뷰어에서 선택된 하나 이상의 오퍼레이션을 삭제.
     */
    @SuppressWarnings("unchecked")
    private void removeOperation() {
        IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
        EList<Operation> operationList = null;
        if (getData() instanceof org.eclipse.uml2.uml.Class) {
            operationList = ((org.eclipse.uml2.uml.Class) getData()).getOwnedOperations();
        } else if (getData() instanceof Interface) {
            operationList = ((Interface) getData()).getOwnedOperations();
        } else if (getData() instanceof DataType) {
            operationList = ((DataType) getData()).getOwnedOperations();
        }
        for (Iterator<Operation> iterator = selection.iterator(); iterator.hasNext();) {
            operationList.remove(iterator.next());
        }
    }

    /**
     * 테이블 뷰어에서 선택된 하나 이상의 오퍼레이션을 위로 이동.
     */
    @SuppressWarnings("unchecked")
    private void upperOperation() {
        IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
        EList<Operation> operationList = null;
        if (getData() instanceof org.eclipse.uml2.uml.Class) {
            operationList = ((org.eclipse.uml2.uml.Class) getData()).getOwnedOperations();
        } else if (getData() instanceof Interface) {
            operationList = ((Interface) getData()).getOwnedOperations();
        } else if (getData() instanceof DataType) {
            operationList = ((DataType) getData()).getOwnedOperations();
        }
        int index = 0;
        for (Iterator<Operation> iterator = selection.iterator(); iterator.hasNext();) {
            Operation next = iterator.next();
            index = operationList.indexOf(next);
            if (index > 0)
                operationList.move(index - 1, next);
        }
    }

    /**
     * 테이블 뷰어에서 선택된 하나 이상의 오퍼레이션을 아래로 이동.
     */
    @SuppressWarnings("unchecked")
    private void downOperation() {
        IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
        EList<Operation> operationList = null;
        if (getData() instanceof org.eclipse.uml2.uml.Class) {
            operationList = ((org.eclipse.uml2.uml.Class) getData()).getOwnedOperations();
        } else if (getData() instanceof Interface) {
            operationList = ((Interface) getData()).getOwnedOperations();
        } else if (getData() instanceof DataType) {
            operationList = ((DataType) getData()).getOwnedOperations();
        }
        int index = 0;
        for (Iterator<Operation> iterator = selection.iterator(); iterator.hasNext();) {
            Operation next = iterator.next();
            index = operationList.indexOf(next);
            if (index < operationList.size() - 1)
                operationList.move(index + 1, next);
        }
    }

    /**
     * 하나의 오퍼레이션을 생성함.
     */
    private void createOperation() {
        uniqueName = (UMLManager.getPackagedUniqueName(this.getData(), UMLMessage.getMessage(UMLMessage.UML_OPERATION)));
        Operation operation = UMLHelper.createOperation();
        operation.setName(uniqueName);
        if (getData() instanceof org.eclipse.uml2.uml.Class) {
            ((org.eclipse.uml2.uml.Class) getData()).getOwnedOperations().add(operation);
        } else if (getData() instanceof Interface) {
            ((Interface) getData()).getOwnedOperations().add(operation);
        } else if (getData() instanceof DataType) {
            ((DataType) getData()).getOwnedOperations().add(operation);
        }
    }

    /**
     * 테이블 뷰어에서 선택된 셀에 해당하는 에디터를 생성.
     * 
     * @param columnNumber
     * @return CellEditor
     */
    public CellEditor getCellEditors(int columnNumber, final Object element) {
        switch (columnNumber) {
            case _IMAGE_COLUMN:
                return null;
            case _NAME_COLUMN:
                cellEdotor = new TextCellEditor(tableViewer.getTable());
                return cellEdotor;
            case _VISIBILITY_COLUMN:
                cellEdotor = new ComboBoxViewerCellEditor(tableViewer.getTable(), SWT.READ_ONLY);
                ((ComboBoxViewerCellEditor) cellEdotor).setContenProvider(new IStructuredContentProvider() {
                    /**
                     * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
                     *      java.lang.Object, java.lang.Object)
                     */
                    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
                    }

                    /**
                     * @see org.eclipse.jface.viewers.IContentProvider#dispose()
                     */
                    public void dispose() {
                    }

                    public Object[] getElements(Object inputElement) {
                        return (Object[]) inputElement;
                    }
                });

                ((ComboBoxViewerCellEditor) cellEdotor).setInput(UICoreConstant.UMLSECTION_CONSTANTS__VISIBILITIES);
                return cellEdotor;
            case _STATIC_COLUMN:
                cellEdotor = new ComboBoxViewerCellEditor(tableViewer.getTable(), SWT.READ_ONLY);
                ((ComboBoxViewerCellEditor) cellEdotor).setContenProvider(new IStructuredContentProvider() {
                    /**
                     * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
                     *      java.lang.Object, java.lang.Object)
                     */
                    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
                    }

                    /**
                     * @see org.eclipse.jface.viewers.IContentProvider#dispose()
                     */
                    public void dispose() {
                    }

                    /**
                     * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
                     */
                    public Object[] getElements(Object inputElement) {
                        return (Object[]) inputElement;
                    }
                });
                ((ComboBoxViewerCellEditor) cellEdotor).setInput(UICoreConstant.UMLSECTION_CONSTANTS__IS_BOOLEANS);
                return cellEdotor;
            case _ABSTRACT_COLUMN:
                cellEdotor = new ComboBoxViewerCellEditor(tableViewer.getTable(), SWT.READ_ONLY);
                ((ComboBoxViewerCellEditor) cellEdotor).setContenProvider(new IStructuredContentProvider() {
                    /**
                     * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
                     *      java.lang.Object, java.lang.Object)
                     */
                    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
                    }

                    /**
                     * @see org.eclipse.jface.viewers.IContentProvider#dispose()
                     */
                    public void dispose() {
                    }

                    /**
                     * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
                     */
                    public Object[] getElements(Object inputElement) {
                        return (Object[]) inputElement;
                    }
                });
                ((ComboBoxViewerCellEditor) cellEdotor).setInput(UICoreConstant.UMLSECTION_CONSTANTS__IS_BOOLEANS);
                return cellEdotor;
            case _OWNED_PARAMETER_COLUMN:
                cellEdotor = new UMLOwnedParameterCellEditor(tableViewer.getTable(), tableViewer.getSelection());
                
                cellEdotor.addListener(new ICellEditorListener() {
                    
                    @Override
                    public void editorValueChanged(boolean oldValidState, boolean newValidState) {
                        tableViewer.refresh(element);
                    }
                    
                    @Override
                    public void cancelEditor() {
                    }
                    
                    @Override
                    public void applyEditorValue() {
                    }
                });
                return cellEdotor;
            default:
                return null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * nexcore.tool.uml.ui.realization.sheet.section.AbstractPropertyGeneralSection
     * #refreshChildren()
     */
    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertySectionWithAdapter#refreshChildren()
     */
    @Override
    public void refreshChildren() {
        if (tableViewer.getContentProvider() != null) {
            tableViewer.setInput(this.getData());
            packColumns();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * nexcore.tool.uml.ui.realization.sheet.section.AbstractPropertyGeneralSection
     * #setListener()
     */
    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertySectionWithAdapter#setListener()
     */
    @Override
    protected void setListener() {

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * nexcore.tool.uml.ui.realization.sheet.section.AbstractPropertyGeneralSection
     * #unsetListener()
     */
    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertySectionWithAdapter#unsetListener()
     */
    @Override
    protected void unsetListener() {

    }

    /**
     * @see org.eclipse.swt.events.FocusListener#focusGained(org.eclipse.swt.events.FocusEvent)
     */
    public void focusGained(FocusEvent e) {
        // TODO Auto-generated method stub
        refreshChildren();
    }

    /**
     * @see org.eclipse.swt.events.FocusListener#focusLost(org.eclipse.swt.events.FocusEvent)
     */
    public void focusLost(FocusEvent e) {
        // TODO Auto-generated method stub

    }

}
