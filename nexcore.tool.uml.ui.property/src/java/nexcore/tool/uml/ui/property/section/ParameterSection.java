/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.property.section;

import java.util.Iterator;
import java.util.List;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.core.util.StringUMLNameUtil;
import nexcore.tool.uml.core.util.TypeSelectDialogType;
import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.manager.transaction.TransactionalAction;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.dialog.TypeSelectDialog;
import nexcore.tool.uml.ui.property.UMLTypeCellEditor;
import nexcore.tool.uml.ui.property.command.DefaultValueTrasactionAction;
import nexcore.tool.uml.ui.property.provider.ParameterSectionContentProvider;
import nexcore.tool.uml.ui.property.provider.ParameterSectionLabelProvider;
import nexcore.tool.uml.ui.property.type.MultiplicityType;
import nexcore.tool.uml.ui.property.type.ParameterDirectionType;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
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
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.LiteralBoolean;
import org.eclipse.uml2.uml.LiteralInteger;
import org.eclipse.uml2.uml.LiteralString;
import org.eclipse.uml2.uml.LiteralUnlimitedNatural;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.TypedElement;


/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.section</li>
 * <li>설  명 : ParameterSection</li>
 * <li>작성일 : 2009. 12. 18.</li>
 * <li>작성자 : 변영민</li>
 * </ul>
 */
public class ParameterSection extends AbstractPropertyCommonSection {

    /** COLUMN_NAMES */
    private static final String[] COLUMN_NAMES = new String[] { UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING,
        UMLMessage.LABEL_DIRECTION, UMLMessage.LABEL_NAME, UMLMessage.LABEL_DEFAULT_VALUE, UMLMessage.LABEL_TYPE,
        UMLMessage.LABEL_ORDERED, UMLMessage.LABEL_IS_UNIQUE, UMLMessage.LABEL_MULTIPLICITY };

    /**
     * COLUMN_WIDTHS
     */
    private static final int[] COLUMN_WIDTHS = new int[] {30, 50, 150, 90, 90, 90, 90, 90};

    /** _IMAGE_COLUMN */
    private static final int _IMAGE_COLUMN = 0;

    /** _DIRECTION_COLUMN */
    private static final int _DIRECTION_COLUMN = 1;

    /** _NAME_COLUMN */
    private static final int _NAME_COLUMN = 2;

    /** _DEFAULT_VALUE_COLUMN */
    private static final int _DEFAULT_VALUE_COLUMN = 3;

    /** _TYPE_COLUMN */
    private static final int _TYPE_COLUMN = 4;

    /** _IS_ORDERED_COLUMN */
    private static final int _IS_ORDERED_COLUMN = 5;

    /** _IS_UNIQUE_COLUMN */
    private static final int _IS_UNIQUE_COLUMN = 6;

    /** _MULTIPLICITY_COLUMN */
    private static final int _MULTIPLICITY_COLUMN = 7;

    /** PREFIX */
    private static final String PREFIX = UMLMessage.getMessage(UMLMessage.LABEL_PARAMETER);

    /** cellEdotor */
    private CellEditor cellEditor;

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
        gridData = new GridData(GridData.FILL_BOTH);
        parent.setLayoutData(gridData);
        sectionComposite = getWidgetFactory().createComposite(parent);
        sectionLayout = new GridLayout(1, false);
        sectionComposite.setLayout(sectionLayout);

        buttonComposite = getWidgetFactory().createComposite(sectionComposite);
        sectionLayout = new GridLayout(2, true);
        buttonComposite.setLayout(sectionLayout);

        Button addButton = getWidgetFactory().createButton(buttonComposite,
            UMLMessage.getMessage(UMLMessage.LABEL_ADD),
            SWT.NONE);
        gridData = new GridData();
        gridData.horizontalAlignment = GridData.FILL;
        addButton.setLayoutData(gridData);
        addButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                DomainUtil.run(new TransactionalAction() {
                    /**
                     * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                     */
                    @Override
                    public void doExecute() {
                        createParameter();
                    }
                });
                tableViewer.refresh();
                packColumns();
            }
        });
        // addButton.addListener(SWT.MouseDown, new Listener() {
        // /**
        // * @see
        // org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
        // */
        // public void handleEvent(Event event) {
        // DomainUtil.run(new TransactionalAction() {
        // /**
        // * @see
        // nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
        // */
        // @Override
        // public void doExecute() {
        // createParameter();
        // }
        // });
        // tableViewer.refresh();
        // packColumns();
        // }
        // });
        Button deleteButton = getWidgetFactory().createButton(buttonComposite,
            UMLMessage.getMessage(UMLMessage.LABEL_DELETE),
            SWT.NONE);
        gridData = new GridData();
        gridData.horizontalAlignment = GridData.FILL;
        deleteButton.setLayoutData(gridData);
        deleteButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                DomainUtil.run(new TransactionalAction() {
                    /**
                     * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                     */
                    @Override
                    public void doExecute() {
                        removeParameter();
                    }
                });
                tableViewer.refresh();
                packColumns();
            }
        });
        // deleteButton.addListener(SWT.MouseDown, new Listener() {
        // /**
        // * @see
        // org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
        // */
        // public void handleEvent(Event event) {
        // DomainUtil.run(new TransactionalAction() {
        // /**
        // * @see
        // nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
        // */
        // @Override
        // public void doExecute() {
        // removeParameter();
        // }
        // });
        // tableViewer.refresh();
        // packColumns();
        // }
        // });

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
        tableViewer.setContentProvider(new ParameterSectionContentProvider());
        
        // 2012-09-13
        // 더블클릭으로 매개변수 추가 편의 기능(추가 버튼이 불편하다고하여 반영)
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
                            createParameter();
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

        for (int i = 0; i < COLUMN_NAMES.length; i++) {
            viewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
            viewerColumn.getColumn().setText(COLUMN_NAMES[i]);
            viewerColumn.getColumn().setWidth(COLUMN_WIDTHS[i]);
            viewerColumn.getColumn().setAlignment(SWT.LEFT);
            viewerColumn.getColumn().setMoveable(false);
            final int columnNumber = i;
            viewerColumn.setLabelProvider(new ParameterSectionLabelProvider(i));
            viewerColumn.setEditingSupport(new EditingSupport(tableViewer) {

                /** instanceClassName */
                String instanceClassName;

                /** stringBuffer */
                StringBuffer stringBuffer;

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
                        case _DIRECTION_COLUMN:
                            return getDirection((Parameter) element);
                        case _NAME_COLUMN:
                            return getName((Parameter) element);
                        case _DEFAULT_VALUE_COLUMN:
                            return getDefaultValue((Parameter) element);
                        case _TYPE_COLUMN:
                            return getTypeText((Parameter) element);
                        case _IS_ORDERED_COLUMN:
                            return Boolean.toString(((Parameter) element).isOrdered());
                        case _IS_UNIQUE_COLUMN:
                            return Boolean.toString(((Parameter) element).isUnique());
                        case _MULTIPLICITY_COLUMN:
                            return getMultiplicity((Parameter) element);
                        default:
                            break;
                    }
                    return null;
                }

                private Object getDefaultValue(Parameter element) {
                    if (element.getDefaultValue() != null) {
                        if (element.getDefaultValue() instanceof LiteralString) {
                            return ((LiteralString) element.getDefaultValue()).getValue().toString();
                        } else if (element.getDefaultValue() instanceof LiteralInteger) {
                            return String.valueOf(((LiteralInteger) element.getDefaultValue()).getValue());

                        } else if (element.getDefaultValue() instanceof LiteralUnlimitedNatural) {
                            return String.valueOf(((LiteralUnlimitedNatural) element.getDefaultValue()).getValue());

                        } else if (element.getDefaultValue() instanceof LiteralBoolean) {
                            return String.valueOf((LiteralBoolean) element.getDefaultValue());
                        } else {
                            return UICoreConstant.UMLSECTION_CONSTANTS__DEFAULT_VALUES_NULL;
                        }
                    } else {
                        return UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;
                    }
                }

                /**
                 * 
                 * 
                 * @param element
                 * @return Object
                 */
                private String getDirection(Parameter element) {
                    if (element.getDirection().equals(ParameterDirectionKind.IN_LITERAL)) {
                        return ParameterDirectionType.IN.toString();
                    } else if (element.getDirection().equals(ParameterDirectionKind.INOUT_LITERAL)) {
                        return ParameterDirectionType.INOUT.toString();
                    } else if (element.getDirection().equals(ParameterDirectionKind.OUT_LITERAL)) {
                        return ParameterDirectionType.OUT.toString();
                    } else if (element.getDirection().equals(ParameterDirectionKind.RETURN_LITERAL)) {
                        return ParameterDirectionType.RETURN.toString();
                    }
                    return null;
                }

                /**
                 * 이름을 리턴.
                 * 
                 * @param property
                 * @return String
                 */
                private String getName(NamedElement element) {
                    if (element.getName() != null)
                        return element.getName().toString();
                    else
                        return UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;
                }

                /**
                 * 
                 * 
                 * @param element
                 * @return String
                 */
                private String getTypeText(TypedElement typedElement) {
                    if (typedElement.getType() != null) {
                        instanceClassName = typedElement.getType().eClass().getInstanceClassName();
                        stringBuffer = new StringBuffer();
                        stringBuffer.append(UICoreConstant.UMLSECTION_CONSTANTS__SQUARE_BRAKET_LEFT);
                        stringBuffer.append(StringUMLNameUtil.getUMLNotationName(instanceClassName));
                        stringBuffer.append(UICoreConstant.UMLSECTION_CONSTANTS__SQUARE_BRAKET_RIGHT);
                        stringBuffer.append(UICoreConstant.PROJECT_CONSTANTS__BLANK);
                        stringBuffer.append(typedElement.getType().getName());
                        return stringBuffer.toString();
                    }
                    return UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;
                }

                /**
                 * 다중성 유형을 리턴.
                 * 
                 * @param property
                 * @return String
                 */
                private String getMultiplicity(MultiplicityElement multiplicityElement) {
                    if (multiplicityElement.getUpperValue() != null && multiplicityElement.getLowerValue() != null) {
                        if (isSingleStar(multiplicityElement)) {
                            return MultiplicityType.SINGLE_STAR.toString();
                        } else if (isZeroToUnique(multiplicityElement)) {
                            return MultiplicityType.ZERO_TO_UNIQUE.toString();
                        } else if (isUnique(multiplicityElement)) {
                            return MultiplicityType.UNIQUE.toString();
                        } else if (isUniqueToSingleStar(multiplicityElement)) {
                            return MultiplicityType.UNIQUE_TO_SINGLE_STAR.toString();
                        }
                    } else {
                        return MultiplicityType.UNIQUE.toString();
                    }
                    return UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;
                }

                /**
                 * 다중성이 "*" 인지 체크.
                 * 
                 * @param property
                 * @return boolean
                 */
                private boolean isSingleStar(MultiplicityElement multiplicityElement) {
                    if (UICoreConstant.UMLSECTION_CONSTANTS___ZERO.equals(multiplicityElement.getLowerValue()
                        .stringValue())) {
                        if (MultiplicityType.SINGLE_STAR.toString().equals(multiplicityElement.getUpperValue()
                            .stringValue())) {
                            return true;
                        }
                    }
                    return false;
                }

                /**
                 * 다중성이 "1" 인지 체크.
                 * 
                 * @param property
                 * @return boolean
                 */
                private boolean isUnique(MultiplicityElement multiplicityElement) {
                    if (UICoreConstant.UMLSECTION_CONSTANTS___UNIQUE.equals(multiplicityElement.getLowerValue()
                        .stringValue())) {
                        if (UICoreConstant.UMLSECTION_CONSTANTS___UNIQUE.equals(multiplicityElement.getUpperValue()
                            .stringValue())) {
                            return true;
                        }
                    }
                    return false;
                }

                /**
                 * 다중성이 "1.. *" 인지 체크.
                 * 
                 * @param property
                 * @return boolean
                 */
                private boolean isUniqueToSingleStar(MultiplicityElement multiplicityElement) {
                    if (UICoreConstant.UMLSECTION_CONSTANTS___UNIQUE.equals(multiplicityElement.getLowerValue()
                        .stringValue())) {
                        if (MultiplicityType.SINGLE_STAR.toString().equals(multiplicityElement.getUpperValue()
                            .stringValue())) {
                            return true;
                        }
                    }
                    return false;
                }

                /**
                 * 다중성이 "0.. 1" 인지 체크.
                 * 
                 * @param property
                 * @return boolean
                 */
                private boolean isZeroToUnique(MultiplicityElement multiplicityElement) {
                    if (UICoreConstant.UMLSECTION_CONSTANTS___ZERO.equals(multiplicityElement.getLowerValue()
                        .stringValue())) {
                        if (UICoreConstant.UMLSECTION_CONSTANTS___UNIQUE.equals(multiplicityElement.getUpperValue()
                            .stringValue())) {
                            return true;
                        }
                    }
                    return false;
                }

                /**
                 * @see org.eclipse.jface.viewers.EditingSupport#setValue(java.lang.Object,
                 *      java.lang.Object)
                 */
                protected void setValue(final Object element, final Object value) {
                    if (null == value) {
                        return;
                    }
                    List<Parameter> parameters = getData().getOwnedParameters();
                    switch (columnNumber) {
                        case _NAME_COLUMN:
                            for (Parameter p : parameters) {
                                if (value != null) {
                                    if (value instanceof String) {
                                        if (element != p && ((String) value).equals(p.getName())) {
                                            MessageDialog.openWarning(getPart().getSite().getShell(),
                                                UMLMessage.LABEL_DUPLICATE_ERROR,
                                                UMLMessage.MESSAGE_DUPLICATE_ERROR);
                                            return;
                                        }
                                    }
                                }
                            }
                            break;
                    }

                    DomainUtil.run(new TransactionalAction() {
                        /**
                         * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                         */
                        @Override
                        public void doExecute() {
                            if (value != null) {
                                switch (columnNumber) {
                                    case _DIRECTION_COLUMN:
                                        setDirectionToElemet((Parameter) element, value);
                                        break;
                                    case _NAME_COLUMN:
                                        setNameToElement((Parameter) element, value);
                                        break;
                                    case _DEFAULT_VALUE_COLUMN:
                                        setDefaultValueToElement((Parameter) element, (String) value);
                                        break;
                                    case _TYPE_COLUMN:
                                        if (((UMLTypeCellEditor) cellEditor).getFirstResult() != null) {
                                            if (element instanceof Parameter) {
                                                // 2012-09-13
                                                // 방향이 리턴 타입이고 이름이 "parameter" 로 시작하는 디폴트 이름의 경우 리턴타입 유형으로 이름을 설정 해준다.
                                                if (((Parameter) element).getDirection().equals(ParameterDirectionKind.RETURN_LITERAL)) {
                                                    if (((Parameter) element).getName().startsWith(PREFIX)) {
                                                        Operation operation = ((Operation) ((Parameter) element).getOwner());
                                                        for (Parameter para : operation.returnResult()) {
                                                            para.destroy();
                                                        }
                                                        operation.createReturnResult(((Type) ((UMLTypeCellEditor) cellEditor).getFirstResult()).getName(),
                                                            ((Type) ((UMLTypeCellEditor) cellEditor).getFirstResult()));
                                                    }
                                                }
                                                ((Parameter) element).setType(((Type) ((UMLTypeCellEditor) cellEditor).getFirstResult()));
                                            }
                                        }
                                        break;
                                    case _IS_ORDERED_COLUMN:
                                        setIsOrderedToElement((Parameter) element, value);
                                        break;
                                    case _IS_UNIQUE_COLUMN:
                                        setIsUniqueToElement((Parameter) element, value);
                                        break;
                                    case _MULTIPLICITY_COLUMN:
                                        setMultiplicityToElement((Parameter) element, value);
                                        break;
                                    default:
                                        return;
                                }
                            }
                        }
                    });
                    tableViewer.refresh();
                }

                /**
                 * 
                 * 
                 * @param element
                 * @param value
                 *            void
                 */
                private void setIsUniqueToElement(MultiplicityElement element, Object value) {
                    if (((String) value).equals(Boolean.toString(true))) {
                        element.setIsUnique(true);
                    } else if (((String) value).equals(Boolean.toString(false))) {
                        element.setIsUnique(false);
                    }
                }

                /**
                 * 
                 * 
                 * @param element
                 * @param value
                 *            void
                 */
                private void setIsOrderedToElement(MultiplicityElement element, Object value) {
                    if (((String) value).equals(Boolean.toString(true))) {
                        element.setIsOrdered(true);
                    } else if (((String) value).equals(Boolean.toString(false))) {
                        element.setIsOrdered(false);
                    }
                }

                /**
                 * 모델에 방향을 세팅.
                 * 
                 * @param element
                 * @param value
                 *            void
                 */
                private void setDirectionToElemet(Parameter element, Object value) {
                    if (((String) value).equals(ParameterDirectionType.IN.toString())) {
                        element.setDirection(ParameterDirectionKind.IN_LITERAL);
                    } else if (((String) value).equals(ParameterDirectionType.INOUT.toString())) {
                        element.setDirection(ParameterDirectionKind.INOUT_LITERAL);
                    } else if (((String) value).equals(ParameterDirectionType.OUT.toString())) {
                        element.setDirection(ParameterDirectionKind.OUT_LITERAL);
                    } else if (((String) value).equals(ParameterDirectionType.RETURN.toString())) {
                        Operation operation = (Operation) element.getOwner();
                        if (operation.getReturnResult() == null) {
                            element.setDirection(ParameterDirectionKind.RETURN_LITERAL);
                        }
                    }
                }

                /**
                 * 모델에 기본값을 세팅.
                 * 
                 * @param value
                 *            void
                 */
                private void setDefaultValueToElement(Object element, Object value) {
                    DomainUtil.run(new DefaultValueTrasactionAction(element, value));
                    // defaultValue = ((Property) element).getDefaultValue();
                    // Property property = (Property) element);
                    // if (defaultValue != null) {
                    // ValueSpecificationOperations.setValue(defaultValue,
                    // (String)value);
                    //
                    // } else {
                    // ((Property) element).setStringDefaultValue(((String)
                    // value));
                    // }
                    //                    
                    // if (((String) value).equals(defaultValues[3])) { // 리터럴
                    // 부울
                    // ((Property) element).setDefaultValue(value);
                    // } else if (((String) value).equals(defaultValues[4])) {
                    // // 리터럴 정수
                    //                        
                    // }

                }

                /**
                 * 모델에 다중성을 세팅.
                 * 
                 * @param element
                 * @param value
                 *            void
                 */
                private void setMultiplicityToElement(MultiplicityElement element, Object value) {
                    if (((String) value).equals(MultiplicityType.SINGLE_STAR.toString())) {
                        // *
                        element.setLower(0);
                        element.setUpper(LiteralUnlimitedNatural.UNLIMITED);
                    } else if (((String) value).equals(MultiplicityType.ZERO_TO_UNIQUE.toString())) {
                        // 0.. 1
                        element.setLower(0);
                        element.setUpper(1);
                    } else if (((String) value).equals(MultiplicityType.UNIQUE.toString())) {
                        // 1
                        element.setLower(1);
                        element.setUpper(1);
                    } else if (((String) value).equals(MultiplicityType.UNIQUE_TO_SINGLE_STAR.toString())) {
                        // 1.. *
                        element.setLower(1);
                        element.setUpper(LiteralUnlimitedNatural.UNLIMITED);
                    } else if (((String) value).equals(MultiplicityType.NONE.toString())) {
                        // 1
                        element.setLower(1);
                        element.setUpper(1);
                    }
                }

                /**
                 * 모델에 이름을 세팅.
                 * 
                 * @param element
                 * @param value
                 *            void
                 */
                private void setNameToElement(NamedElement element, Object value) {
                    element.setName((String) value);
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
                        createParameter();
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
                        removeParameter();
                    }
                });
                tableViewer.refresh();
                packColumns();
            }
        });
        deleteItem.setText(UMLMessage.getMessage(UMLMessage.LABEL_DELETE_FROM_MODEL));
        
        MenuItem typeAllItem= new MenuItem(menu, SWT.CASCADE);
        typeAllItem.addSelectionListener(new SelectionAdapter() {
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
                        final TableItem[] items = tableViewer.getTable().getSelection();
                        if(items == null || items.length < 1) {
                            return;
                        }
                        Object data = items[0].getData();
                        if (data instanceof Parameter) {
                            TypeSelectDialog dialog = new TypeSelectDialog(table.getShell(),
                                TypeSelectDialogType.PROPERTY,
                                (Parameter) data);
                            int returnCode = dialog.open();

                            switch (returnCode) {
                                case Window.OK:
                                    final Object firstResult = dialog.getFirstResult();
                                    DomainUtil.run(new TransactionalAction() {
                                        /**
                                         * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                                         */
                                        @Override
                                        public void doExecute() {
                                            for(TableItem item : items) {
                                                Object param = item.getData();
                                                if (param instanceof Parameter) {
                                                    ((Parameter) param).setType((Type) firstResult);
                                                }
                                            }
                                        }
                                    });
                                    tableViewer.refresh();
                                    return;
                                case Window.CANCEL:
                                case IDialogConstants.FINISH_ID:
                                default:
                                    return;
                            }
                        }
                        
                    }
                });
                tableViewer.refresh();
                packColumns();
            }
        });
        typeAllItem.setText(UMLMessage.LABEL_TYPE_SET);
        
        table.setMenu(menu);

        // 버튼

    }

    /**
     * 
     * void
     */
    private void createParameter() {
        try {
            uniqueName = UMLManager.getPackagedUniqueName(this.getData(), PREFIX);
            this.getData().createOwnedParameter(uniqueName, null);
        } catch (Exception error) {
            Log.error(error);
        }
    }

    /**
     * 테이블 뷰어에서 선택된 하나 이상의 파라미터를 삭제. void
     */
    @SuppressWarnings("unchecked")
    private void removeParameter() {
        IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
        EList<Parameter> parameterList = this.getData().getOwnedParameters();
        for (Iterator<Parameter> iterator = selection.iterator(); iterator.hasNext();) {
            parameterList.remove(iterator.next());
        }
    }

    /**
     * 테이블 뷰어에서 선택된 셀에 해당하는 에디터를 생성.
     * 
     * @param columnNumber
     * @return CellEditor 해당 컬럼에서 사용할 에디터.
     */
    public CellEditor getCellEditors(int columnNumber, final Object element) {
        Parameter parameter = null;
        switch (columnNumber) {
            case _IMAGE_COLUMN:
                return null;
            case _DIRECTION_COLUMN:
                parameter = (Parameter) element;
                if (parameter.getDirection().equals(ParameterDirectionKind.RETURN_LITERAL)) {
                    return null;
                }
                cellEditor = new ComboBoxViewerCellEditor(tableViewer.getTable(), SWT.READ_ONLY);
                ((ComboBoxViewerCellEditor) cellEditor).setContenProvider(getContentProvider());
                ((ComboBoxViewerCellEditor) cellEditor).setInput(UICoreConstant.UMLSECTION_CONSTANTS__DIRECTIONS);
                return cellEditor;
            case _NAME_COLUMN:
                cellEditor = new TextCellEditor(tableViewer.getTable());
                return cellEditor;
            case _DEFAULT_VALUE_COLUMN:
                // cellEditor = new
                // ComboBoxViewerCellEditor(tableViewer.getTable(),
                // SWT.READ_ONLY);
                // ((ComboBoxViewerCellEditor) cellEditor).setContenProvider(new
                // IStructuredContentProvider() {
                // /**
                // * @see org.eclipse.jface.viewers.IContentProvider#dispose()
                // */
                // public void dispose() {
                // }
                //
                // /**
                // * @see
                // org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
                // */
                // public Object[] getElements(Object inputElement) {
                // // TODO Auto-generated method stub
                // return (Object[]) inputElement;
                // }
                //
                // /**
                // * @see
                // org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
                // * java.lang.Object, java.lang.Object)
                // */
                // public void inputChanged(Viewer viewer, Object oldInput,
                // Object
                // newInput) {
                // }
                // });
                // ((ComboBoxViewerCellEditor)
                // cellEditor).setInput(UICoreConstant.UMLSECTION_CONSTANTS__DEFAULT_VALUES_BOOLEAN);
                // return cellEditor;
                parameter = (Parameter) element;
                if (parameter.getType() == null) {
                    return null;
                } else if (parameter.getType() instanceof Element && !(parameter.getType() instanceof PrimitiveType)) {
                    // cellEditor = new TextCellEditor(tableViewer.getTable());
                    // return cellEditor;
                    return null;
                } else if (parameter.getType().getName() != null && "boolean".equals(parameter.getType().getName().toLowerCase())) {
                    createBooleanComboBox();
                    return cellEditor;
                } else {

                    return new TextCellEditor(tableViewer.getTable());
                }

            case _TYPE_COLUMN:
                cellEditor = new UMLTypeCellEditor(tableViewer.getTable(), ((Parameter) element).getType(), getData());
                ((UMLTypeCellEditor) cellEditor).setUMLModel(this.getData().getModel());
                cellEditor.addListener(new ICellEditorListener() {
                    
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
                return cellEditor;
            case _IS_ORDERED_COLUMN:
                cellEditor = new ComboBoxViewerCellEditor(tableViewer.getTable(), SWT.READ_ONLY);
                ((ComboBoxViewerCellEditor) cellEditor).setContenProvider(getContentProvider());
                ((ComboBoxViewerCellEditor) cellEditor).setInput(UICoreConstant.UMLSECTION_CONSTANTS__IS_BOOLEANS);
                return cellEditor;
            case _IS_UNIQUE_COLUMN:
                cellEditor = new ComboBoxViewerCellEditor(tableViewer.getTable(), SWT.READ_ONLY);
                ((ComboBoxViewerCellEditor) cellEditor).setContenProvider(getContentProvider());
                ((ComboBoxViewerCellEditor) cellEditor).setInput(UICoreConstant.UMLSECTION_CONSTANTS__IS_BOOLEANS);
                return cellEditor;
            case _MULTIPLICITY_COLUMN:
                cellEditor = new ComboBoxViewerCellEditor(tableViewer.getTable());
                ((ComboBoxViewerCellEditor) cellEditor).setContenProvider(getContentProvider());
                ((ComboBoxViewerCellEditor) cellEditor).setInput(UICoreConstant.UMLSECTION_CONSTANTS__MULTIPLICITIES);
                return cellEditor;
            default:
                return null;
        }
    }

    /**
     * createBooleanComboBox
     *   void
     */
    private void createBooleanComboBox() {
        cellEditor = new ComboBoxViewerCellEditor(tableViewer.getTable(), SWT.READ_ONLY);
        ((ComboBoxViewerCellEditor) cellEditor).setContenProvider(getContentProvider());
        ((ComboBoxViewerCellEditor) cellEditor).setInput(UICoreConstant.UMLSECTION_CONSTANTS__DEFAULT_VALUES_BOOLEAN);
    }

    /**
     * getContentProvider
     *  
     * @return IStructuredContentProvider
     */
    private IStructuredContentProvider getContentProvider() {
        return new IStructuredContentProvider() {
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

            /**
             * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
             *      java.lang.Object, java.lang.Object)
             */
            public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
            }
        };
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#refreshChildren()
     */
    @Override
    public void refreshChildren() {
        if (tableViewer.getContentProvider() != null) {
            try {
                tableViewer.setInput(this.getData());
                packColumns();
            } catch (Exception e) {
                e.printStackTrace();
            }
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
