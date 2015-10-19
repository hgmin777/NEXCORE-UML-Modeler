/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.property.section;

import java.util.Iterator;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.UMLHelper;
import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.manager.transaction.TransactionalAction;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.property.provider.LiteralSectionContentProvider;
import nexcore.tool.uml.ui.property.provider.LiteralSectionLabelProvider;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TableViewerEditor;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.LiteralBoolean;
import org.eclipse.uml2.uml.LiteralInteger;
import org.eclipse.uml2.uml.LiteralString;
import org.eclipse.uml2.uml.LiteralUnlimitedNatural;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.ValueSpecification;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.section</li>
 * <li>설 명 : LiteralSection</li>
 * <li>작성일 : 2011. 5. 26.</li>
 * <li>작성자 : Kang</li>
 * </ul>
 */
public class LiteralSection extends AbstractPropertyCommonSection {

    /** _IMAGE_COLUMN */
    private static final int _IMAGE_COLUMN = 0;

    /** _NAME_COLUMN */
    private static final int _NAME_COLUMN = 1;

    /** _DEFAULT_VALUE_COLUMN */
    private static final int _DEFAULT_VALUE_COLUMN = 2;

    /** _SPECIFICATION_COLUMN */
    private static final int _SPECIFICATION_COLUMN = 3;

    /** COLUMN_NAMES */
    private static final String[] COLUMN_NAMES = new String[] { UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING,
        UMLMessage.LABEL_NAME, UMLMessage.LABEL_DEFAULT_VALUE, UMLMessage.LABEL_LITERAL_SPEC };

    /**
     * COLUMN_WIDTHS
     */
    private static final int[] COLUMN_WIDTHS = new int[] {50, 150, 200, 200};
    
    /** cellEdotor */
    private CellEditor cellEditor = null;

    /**
     * 
     * 
     * @return Classifier
     */
    private Classifier getData() {
        return (Classifier) this.getSelectedModel();
    }

    /**
     * partSite
     */
    private IWorkbenchPartSite partSite;

    /**
     * getSite
     *  
     * @return IWorkbenchPartSite
     */
    public IWorkbenchPartSite getSite() {
        return partSite;
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
        gridData.verticalAlignment = GridData.HORIZONTAL_ALIGN_BEGINNING;
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
                        createLiteral();
                    }
                });
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
                DomainUtil.run(new TransactionalAction() {
                    /**
                     * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                     */
                    @Override
                    public void doExecute() {
                        removeLiteral();
                    }
                });
                tableViewer.refresh();
                packColumns();
            }
        });

        Button upperButton = getWidgetFactory().createButton(buttonComposite,
            UMLMessage.getMessage(UMLMessage.LABEL_UPPER),
            SWT.NONE);
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
                        upperLiteral();
                    }
                });
                tableViewer.refresh();
                packColumns();
            }
        });

        Button downButton = getWidgetFactory().createButton(buttonComposite,
            UMLMessage.getMessage(UMLMessage.LABEL_DOWN),
            SWT.NONE);
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
                        downLiteral();
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
        table.setLinesVisible(true);
        table.setHeaderVisible(true);
        tableViewer.setUseHashlookup(true);
        tableViewer.setColumnProperties(COLUMN_NAMES);
        tableViewer.setContentProvider(new LiteralSectionContentProvider());

        ColumnViewerEditorActivationStrategy actSupport = new ColumnViewerEditorActivationStrategy(tableViewer) {
            /**
             * @see org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy#isEditorActivationEvent(org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent)
             */
            protected boolean isEditorActivationEvent(ColumnViewerEditorActivationEvent event) {
                return event.eventType == ColumnViewerEditorActivationEvent.MOUSE_CLICK_SELECTION;
            }
        };
        TableViewerEditor.create(tableViewer, actSupport, ColumnViewerEditor.KEYBOARD_ACTIVATION);

        for (int i = 0; i < COLUMN_NAMES.length; i++) {
            viewerColumn = new TableViewerColumn(tableViewer, SWT.BORDER_DOT);
            viewerColumn.getColumn().setText(COLUMN_NAMES[i]);
            viewerColumn.getColumn().setWidth(COLUMN_WIDTHS[i]);
            viewerColumn.getColumn().setAlignment(SWT.LEFT);
            viewerColumn.getColumn().setMoveable(false);
            viewerColumn.getColumn().setResizable(true);
            final int columnNumber = i;
            viewerColumn.setLabelProvider(new LiteralSectionLabelProvider(i));
            viewerColumn.setEditingSupport(new EditingSupport(tableViewer) {

                /**
                 * @see org.eclipse.jface.viewers.EditingSupport#canEdit(java.lang.Object)
                 */
                protected boolean canEdit(Object element) {
                    if (columnNumber != 0)
                        return true;
                    return false;
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
                            return getTextOfName((EnumerationLiteral) element);
                        case _DEFAULT_VALUE_COLUMN:
                            return getTextOfDefaultValue((EnumerationLiteral) element);
                        case _SPECIFICATION_COLUMN:
                            return getTextOfSpecification((EnumerationLiteral) element);
                        default:
                            break;
                    }
                    return null;
                }

                /**
                 * 이름을 리턴.
                 * 
                 * @param property
                 * @return String
                 */
                private String getTextOfName(NamedElement element) {
                    if (element.getName() != null) {
                        return element.getName().toString();
                    } else {
                        return UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;
                    }
                }

                /**
                 * 
                 * @param property
                 * @return String
                 */
                private String getTextOfDefaultValue(final NamedElement element) {

                    EnumerationLiteral literal = null;
                    ValueSpecification spec = null;

                    if (element instanceof EnumerationLiteral) {
                        literal = (EnumerationLiteral) element;
                    }

                    spec = literal.getSpecification();
                    if (null == spec) {
                        return UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;
                    }

                    if (spec instanceof LiteralBoolean) {
                        LiteralBoolean literalBoolean = (LiteralBoolean) spec;
                        return String.valueOf(literalBoolean.isValue());

                    } else if (spec instanceof LiteralInteger) {
                        LiteralInteger literalInteger = (LiteralInteger) spec;
                        return String.valueOf(literalInteger.getValue());

                    } else if (spec instanceof LiteralString) {
                        LiteralString literalString = (LiteralString) spec;
                        return String.valueOf(literalString.getValue());

                    } else if (spec instanceof LiteralUnlimitedNatural) {
                        LiteralUnlimitedNatural literalUnlimitedNatural = (LiteralUnlimitedNatural) spec;
                        return String.valueOf(literalUnlimitedNatural.getValue());

                    } else {
                        return UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;

                    }
                }
                
                /**
                 * 
                 * @param property
                 * @return String
                 */
                private String getTextOfSpecification(final NamedElement element) {

                    EnumerationLiteral literal = null;
                    ValueSpecification spec = null;

                    if (element instanceof EnumerationLiteral) {
                        literal = (EnumerationLiteral) element;
                    }

                    spec = literal.getSpecification();
                    if (null == spec) {
                        return UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;
                    }
                    if (spec instanceof LiteralBoolean) {
                        return "Boolean";

                    } else if (spec instanceof LiteralInteger) {
                        return "Integer";

                    } else if (spec instanceof LiteralString) {
                        return "String";

                    } else if (spec instanceof LiteralUnlimitedNatural) {
                        return "UnlimitedNatural";

                    } else {
                        return UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;

                    }
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
                                setNameToElement((EnumerationLiteral) element, (String) value);
                                break;
                            case _DEFAULT_VALUE_COLUMN:
                                setDefaultValueToElement((EnumerationLiteral) element, (String) value);
                                break;
                            case _SPECIFICATION_COLUMN:
                                setSpecificationToElement((EnumerationLiteral) element, (String) value);
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
                 * 모델에 이름을 세팅.
                 * 
                 * @param element
                 * @param value
                 *            void
                 */
                private void setDefaultValueToElement(final NamedElement element, final String value) {
                    DomainUtil.run(new TransactionalAction() {
                        /**
                         * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                         */
                        @Override
                        public void doExecute() {
                            EnumerationLiteral literal = null;
                            ValueSpecification spec = null;

                            if (element instanceof EnumerationLiteral) {
                                literal = (EnumerationLiteral) element;
                            }

                            spec = literal.getSpecification();
                            if (null == spec) {
                                return;
                            }

                            if (spec instanceof LiteralBoolean) {
                                LiteralBoolean literalBoolean = (LiteralBoolean) spec;
                                if( "TRUE".equals(value.toUpperCase()) ){
                                    literalBoolean.setValue( true );
                                } else {
                                    literalBoolean.setValue( false );
                                }

                            } else if (spec instanceof LiteralInteger) {
                                LiteralInteger literalInteger = (LiteralInteger) spec;
                                literalInteger.setValue( Integer.parseInt(value) );

                            } else if (spec instanceof LiteralString) {
                                LiteralString literalString = (LiteralString) spec;
                                literalString.setValue(value);

                            } else if (spec instanceof LiteralUnlimitedNatural) {
                                LiteralUnlimitedNatural literalUnlimitedNatural = (LiteralUnlimitedNatural) spec;
                                literalUnlimitedNatural.setValue( Integer.parseInt(value) );

                            }
                        }
                    });
                }

                /**
                 * 모델에 이름을 세팅.
                 * 
                 * @param element
                 * @param value
                 *            void
                 */
                private void setSpecificationToElement(final NamedElement element, final String value) {
                    DomainUtil.run(new TransactionalAction() {
                        /**
                         * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                         */
                        @Override
                        public void doExecute() {
                            EnumerationLiteral literal = null;
                            ValueSpecification spec = null;

                            if (element instanceof EnumerationLiteral) {
                                literal = (EnumerationLiteral) element;
                            }
                            
                            spec = literal.getSpecification();
                            Object oldValue = null;
                            if (null != spec) {
                                if (spec instanceof LiteralBoolean) {
                                    oldValue = spec.booleanValue();
                                } else if (spec instanceof LiteralInteger) {
                                    oldValue = spec.integerValue();
                                } else if (spec instanceof LiteralString) {
                                    oldValue = spec.stringValue();
                                } else if (spec instanceof LiteralUnlimitedNatural) {
                                    oldValue = spec.unlimitedValue();
                                }
                            }
                            
                            if( "Boolean".equals(value) ){
                                spec = UMLFactory.eINSTANCE.createLiteralBoolean();
                                if(null != oldValue && oldValue instanceof Boolean ){
                                    ((LiteralBoolean)spec).setValue((Boolean) oldValue);
                                }
                                
                                literal.setSpecification(spec);
                                
                            } else if("String".equals(value) ){
                                spec = UMLFactory.eINSTANCE.createLiteralString();
                                if(null != oldValue && oldValue instanceof String ){
                                    ((LiteralString)spec).setValue((String) oldValue);
                                }
                                
                                literal.setSpecification(spec);
                                
                            } else if("Integer".equals(value) ){
                                spec = UMLFactory.eINSTANCE.createLiteralInteger();
                                if(null != oldValue && oldValue instanceof Integer ){
                                    ((LiteralInteger)spec).setValue((Integer) oldValue);
                                }
                                
                                literal.setSpecification(spec);
                                
                            } else if("UnlimitedNatural".equals(value) ){
                                spec = UMLFactory.eINSTANCE.createLiteralUnlimitedNatural();
                                if(null != oldValue && oldValue instanceof Integer ){
                                    ((LiteralUnlimitedNatural)spec).setValue( (Integer) oldValue);
                                }
                                
                                literal.setSpecification(spec);
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
                        createLiteral();
                    }
                });
                tableViewer.refresh();
                packColumns();
            }
        });
        addItem.setText(UMLMessage.getMessage(UMLMessage.LABEL_ADD_NEW_ATTRIBUTE));
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
                        removeLiteral();
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
     * 테이블 뷰어에서 선택된 하나 이상의 리터럴을 삭제.
     */
    @SuppressWarnings("unchecked")
    private void removeLiteral() {
        IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
        EList<EnumerationLiteral> literalList = null;
        if (getData() instanceof Enumeration) {
            literalList = ((Enumeration) getData()).getOwnedLiterals();
        }
        for (Iterator<EnumerationLiteral> iterator = selection.iterator(); iterator.hasNext();) {
            literalList.remove(iterator.next());
        }
    }

    /**
     * 선택된 Attribute를 위로 이동
     */
    @SuppressWarnings("unchecked")
    private void upperLiteral() {
        IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
        EList<EnumerationLiteral> literalList = null;
        if (getData() instanceof Enumeration) {
            literalList = ((Enumeration) getData()).getOwnedLiterals();
        }
        int index = 0;
        for (Iterator<EnumerationLiteral> iterator = selection.iterator(); iterator.hasNext();) {
            EnumerationLiteral next = iterator.next();
            index = literalList.indexOf(next);
            if (index > 0)
                literalList.move(index - 1, next);
        }
    }

    /**
     * 선택된 Attribute를 아래로 이동
     */
    @SuppressWarnings("unchecked")
    private void downLiteral() {
        IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
        EList<EnumerationLiteral> literalList = null;
        if (getData() instanceof Enumeration) {
            literalList = ((Enumeration) getData()).getOwnedLiterals();
        }
        int index = 0;
        for (Iterator<EnumerationLiteral> iterator = selection.iterator(); iterator.hasNext();) {
            EnumerationLiteral next = iterator.next();
            index = literalList.indexOf(next);
            if (index < literalList.size() - 1)
                literalList.move(index + 1, next);
        }
    }

    /**
     * 하나의 리터럴을 생성함.
     */
    private void createLiteral() {
        uniqueName = (UMLManager.getPackagedUniqueName(this.getData(),
            UMLMessage.getMessage(UMLMessage.UML_ENUMERATIONLITERAL)));
        EnumerationLiteral literal = UMLHelper.createEnumerationLiteral();
        literal.setName(uniqueName);
        if (getData() instanceof Enumeration) {
            ((Enumeration) getData()).getOwnedLiterals().add(literal);
        }
    }

    /**
     * 테이블 뷰어에서 선택된 셀에 해당하는 에디터를 생성.
     * 
     * @param columnNumber
     * @return CellEditor 해당 컬럼에서 사용할 에디터.
     */
    public CellEditor getCellEditors(int columnNumber, Object element) {
        switch (columnNumber) {
            case _IMAGE_COLUMN:
                return null;
            case _NAME_COLUMN:
                cellEditor = new TextCellEditor(tableViewer.getTable());
                return cellEditor;
            case _DEFAULT_VALUE_COLUMN:
                cellEditor = new TextCellEditor(tableViewer.getTable());
                return cellEditor;
            case _SPECIFICATION_COLUMN:
                cellEditor = new ComboBoxViewerCellEditor(tableViewer.getTable(), SWT.READ_ONLY);
                ((ComboBoxViewerCellEditor) cellEditor).setContenProvider(new IStructuredContentProvider() {
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
                ((ComboBoxViewerCellEditor) cellEditor).setInput( new String[]{"Boolean","String","Integer","UnlimitedNatural"} );
                return cellEditor;
            default:
                return null;
        }
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#refreshChildren()
     */
    @Override
    public void refreshChildren() {
        if (tableViewer.getContentProvider() != null) {
            tableViewer.setInput(this.getData());
            packColumns();
        }
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#setListener()
     */
    @Override
    protected void setListener() {
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#unsetListener()
     */
    @Override
    protected void unsetListener() {
    }

}
