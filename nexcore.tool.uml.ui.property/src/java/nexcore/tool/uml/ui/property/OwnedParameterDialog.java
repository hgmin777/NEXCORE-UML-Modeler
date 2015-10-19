/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.property;

import java.util.Iterator;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.core.util.StringUMLNameUtil;
import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.manager.transaction.TransactionalAction;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.registry.IConstantImageRegistry;
import nexcore.tool.uml.ui.property.command.DefaultValueTrasactionAction;
import nexcore.tool.uml.ui.property.provider.ParameterSectionContentProvider;
import nexcore.tool.uml.ui.property.provider.ParameterSectionLabelProvider;
import nexcore.tool.uml.ui.property.type.MultiplicityType;
import nexcore.tool.uml.ui.property.type.ParameterDirectionType;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ICellEditorListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TableViewerEditor;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
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
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.TypedElement;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property</li>
 * <li>설  명 : OwnedParameterDialog</li>
 * <li>작성일 : 2010. 1. 5.</li>
 * <li>작성자 : 변영민</li>
 * </ul>
 */
public class OwnedParameterDialog extends TitleAreaDialog {

    /** COLUMN_NAMES */
    private static final String[] COLUMN_NAMES = new String[] { UICoreConstant.EMPTY_STRING,
        UMLMessage.getMessage(UMLMessage.LABEL_DIRECTION), UMLMessage.getMessage(UMLMessage.LABEL_NAME),
        UMLMessage.getMessage(UMLMessage.LABEL_DEFAULT_VALUE), UMLMessage.getMessage(UMLMessage.LABEL_TYPE),
        UMLMessage.getMessage(UMLMessage.LABEL_ORDERED), UMLMessage.getMessage(UMLMessage.LABEL_IS_UNIQUE),
        UMLMessage.getMessage(UMLMessage.LABEL_MULTIPLICITY) };


    /**
     * COLUMN_WIDTHS
     */
    private static final int[] COLUMN_WIDTHS = new int[] {30, 50, 120, 130, 90, 90, 90, 90};
    
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

    /** NUMBER_ONE */
    protected static final String NUMBER_ONE = "1"; //$NON-NLS-1$

    /** DEFAULT_VALUES */
    // protected static final String[] DEFAULT_VALUES = { "새로 작성 모호한 표현식",
    // "새로 작성 표현식", "새로 작성 인스턴스 값", "새로 작성 리터럴 부울",
    // "새로 작성 리터럴 정수", "새로 작성 리터럴 널(null)", "새로 작성 리터럴 문자열", "새로 작성 리터럴 무제한 자연수"
    // };
    /** IS_BOOLEANS */
    private static final String[] IS_BOOLEANS = { Boolean.toString(true), Boolean.toString(false) };

    /** cellEdotor */
    private CellEditor cellEditor;

    /** sectionComposite */
    private Composite sectionComposite;

    /** sectionLayout */
    private GridLayout sectionLayout;

    /** tableViewerComposite */
    private Composite tableViewerComposite;

    /** gridData */
    private GridData gridData;

    /** tableViewer */
    private TableViewer tableViewer;

    /** table */
    private Table table;

    /** viewerColumn */
    private TableViewerColumn viewerColumn;

    /** buttonComposite */
    private Composite buttonComposite;

    /** selectedModel */
    private Element selectedModel;

    /** uniqueName */
    private String uniqueName;

    /** PREFIX */
    private static final String PREFIX = UMLMessage.getMessage(UMLMessage.LABEL_PARAMETER);

    /** DIRECTIONS */
    private static final String[] DIRECTIONS = { UMLMessage.getMessage(UMLMessage.LABEL_IN),
        UMLMessage.getMessage(UMLMessage.LABEL_IN_OUT), UMLMessage.getMessage(UMLMessage.LABEL_OUT),
        UMLMessage.getMessage(UMLMessage.LABEL_RETURN) };

    /** MULTIPLICITIES */
    protected static final String[] MULTIPLICITIES = { MultiplicityType.NONE.toString(),
        MultiplicityType.SINGLE_STAR.toString(), MultiplicityType.ZERO_TO_UNIQUE.toString(),
        MultiplicityType.UNIQUE.toString(), MultiplicityType.UNIQUE_TO_SINGLE_STAR.toString() };

    /** _UNIQUE */
    protected static final String _UNIQUE = String.valueOf(1);

    /** _ZERO */
    protected static final String _ZERO = String.valueOf(0);

    /**
     * getData
     *  
     * @return Operation
     */
    private Operation getData() {
        return (Operation) this.selectedModel;
    }
    
    /** selection */
    private ISelection selection;

    /**
     * @param parent
     * @param selection
     */
    public OwnedParameterDialog(Shell parent, ISelection selection) {
        super(parent);
        this.selection = selection;
        this.selectedModel = (Operation) ((IStructuredSelection) this.selection).getFirstElement();
        
        setTitleImage(UiCorePlugin.getImageDescriptor(IConstantImageRegistry.ICONNAME_WIZARD_BANNER_GRAPHIC).createImage());
    }
    
    /**
     * @see org.eclipse.jface.dialogs.TitleAreaDialog#createContents(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createContents(Composite parent) {
        Control content = super.createContents(parent);
        setTitle(UMLMessage.LABEL_PARAMETER_TEXT);
        setMessage(UMLMessage.MESSAGE_PARAMETER_TEXT);

        return content;
    }

    /**
     * @see org.eclipse.jface.dialogs.Dialog#getInitialSize()
     */
    @Override
    protected Point getInitialSize() {
        return new Point(800, 500);
    }
    
    /**
     * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
    }
    
    /**
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText(UMLMessage.LABEL_PARAMETER_TEXT);

    }
    
    /**
     * @see org.eclipse.jface.dialogs.TitleAreaDialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite) super.createDialogArea(parent);
        
        sectionComposite = new Composite(container, SWT.NONE);
        sectionLayout = new GridLayout(2, false);
        sectionComposite.setLayout(sectionLayout);
        gridData = new GridData(GridData.FILL_BOTH);
        sectionComposite.setLayoutData(gridData);

        tableViewerComposite = new Composite(sectionComposite, SWT.NONE);
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
                    if (ParameterDirectionKind.IN_LITERAL.equals(element.getDirection())) {
                        return ParameterDirectionType.IN.toString();
                    } else if (ParameterDirectionKind.INOUT_LITERAL.equals(element.getDirection())) {
                        return ParameterDirectionType.INOUT.toString();
                    } else if (ParameterDirectionKind.OUT_LITERAL.equals(element.getDirection())) {
                        return ParameterDirectionType.OUT.toString();
                    } else if (ParameterDirectionKind.RETURN_LITERAL.equals(element.getDirection())) {
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
                        return UICoreConstant.EMPTY_STRING;
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
                        stringBuffer.append(UICoreConstant.PROJECT_CONSTANTS__LEFT_ANGLE_BRACKET);
                        stringBuffer.append(StringUMLNameUtil.getUMLNotationName(instanceClassName));
                        stringBuffer.append(UICoreConstant.PROJECT_CONSTANTS__RIGHT_ANGLE_BRACKET);
                        stringBuffer.append(UICoreConstant.PROJECT_CONSTANTS__BLANK);
                        stringBuffer.append(typedElement.getType().getName());
                        return stringBuffer.toString();
                    }
                    return UICoreConstant.EMPTY_STRING;
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
                    return UICoreConstant.EMPTY_STRING;
                }

                /**
                 * 다중성이 "*" 인지 체크.
                 * 
                 * @param property
                 * @return boolean
                 */
                private boolean isSingleStar(MultiplicityElement multiplicityElement) {
                    if (_ZERO.equals(multiplicityElement.getLowerValue().stringValue())) {
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
                    if (_UNIQUE.equals(multiplicityElement.getLowerValue().stringValue())) {
                        if (_UNIQUE.equals(multiplicityElement.getUpperValue().stringValue())) {
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
                    if (_UNIQUE.equals(multiplicityElement.getLowerValue().stringValue())) {
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
                    if (_ZERO.equals(multiplicityElement.getLowerValue().stringValue())) {
                        if (_UNIQUE.equals(multiplicityElement.getUpperValue().stringValue())) {
                            return true;
                        }
                    }
                    return false;
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
                            case _DIRECTION_COLUMN:
                                setDirectionToElemet((Parameter) element, (String) value);
                                break;
                            case _NAME_COLUMN:
                                setNameToElement((Parameter) element, (String) value);
                                break;
                            case _DEFAULT_VALUE_COLUMN:
                                setDefaultValueToElement((Parameter) element, (String) value);
                                break;
                            case _TYPE_COLUMN:
                                setTypeToElement((Parameter) element);
                                break;
                            case _IS_ORDERED_COLUMN:
                                setIsOrderedToElement((Parameter) element, (String) value);
                                break;
                            case _IS_UNIQUE_COLUMN:
                                setIsUniqueToElement((Parameter) element, (String) value);
                                break;
                            case _MULTIPLICITY_COLUMN:
                                setMultiplicityToElement((Parameter) element, (String) value);
                                break;
                            default:
                                return;
                        }
                    }
                    tableViewer.refresh();
                }

                /**
                 * 
                 * 
                 * @param element
                 *            void
                 */
                private void setTypeToElement(final TypedElement element) {
                    DomainUtil.run(new TransactionalAction() {
                        /**
                         * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                         */
                        @Override
                        public void doExecute() {
                            if (((UMLTypeCellEditor) cellEditor).getFirstResult() != null) {
                                if (element instanceof Parameter) {
                                    if (((Parameter) element).getDirection()
                                        .equals(ParameterDirectionKind.RETURN_LITERAL)) {
                                        Operation operation = ((Operation) element.getOwner());
                                        for (Parameter para : operation.returnResult()) {
                                            para.destroy();
                                        }
                                        operation.createReturnResult(((Type) ((UMLTypeCellEditor) cellEditor).getFirstResult()).getName(),
                                            ((Type) ((UMLTypeCellEditor) cellEditor).getFirstResult()));
                                    } else {
                                        element.setType(((Type) ((UMLTypeCellEditor) cellEditor).getFirstResult()));
                                    }
                                }
                            }
                        }
                    });
                }

                /**
                 * 
                 * 
                 * @param element
                 * @param value
                 *            void
                 */
                private void setIsUniqueToElement(final MultiplicityElement element, final String value) {
                    DomainUtil.run(new TransactionalAction() {
                        /**
                         * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                         */
                        @Override
                        public void doExecute() {
                            if (value.equals(Boolean.toString(true))) {
                                element.setIsUnique(true);
                            } else if (value.equals(Boolean.toString(false))) {
                                element.setIsUnique(false);
                            }
                        }
                    });
                }

                /**
                 * 
                 * 
                 * @param element
                 * @param value
                 *            void
                 */
                private void setIsOrderedToElement(final MultiplicityElement element, final String value) {
                    DomainUtil.run(new TransactionalAction() {
                        /**
                         * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                         */
                        @Override
                        public void doExecute() {
                            if (value.equals(Boolean.toString(true))) {
                                element.setIsOrdered(true);
                            } else if (value.equals(Boolean.toString(false))) {
                                element.setIsOrdered(false);
                            }
                        }
                    });
                }

                /**
                 * 모델에 방향을 세팅.
                 * 
                 * @param element
                 * @param value
                 *            void
                 */
                private void setDirectionToElemet(final Parameter element, final String value) {
                    DomainUtil.run(new TransactionalAction() {
                        /**
                         * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                         */
                        @Override
                        public void doExecute() {
                            if (value.equals(ParameterDirectionType.IN.toString())) {
                                element.setDirection(ParameterDirectionKind.IN_LITERAL);
                            } else if (value.equals(ParameterDirectionType.INOUT.toString())) {
                                element.setDirection(ParameterDirectionKind.INOUT_LITERAL);
                            } else if (value.equals(ParameterDirectionType.OUT.toString())) {
                                element.setDirection(ParameterDirectionKind.OUT_LITERAL);
                            } else if (value.equals(ParameterDirectionType.RETURN.toString())) {

                                Operation operation = (Operation) element.getOwner();
                                if (operation.getReturnResult() == null) {
                                    element.setDirection(ParameterDirectionKind.RETURN_LITERAL);
                                }
                            }
                        }
                    });
                }

                /**
                 * 모델에 기본값을 세팅.
                 * 
                 * @param value
                 *            void
                 */
                private void setDefaultValueToElement(Object element, String value) {
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
                private void setMultiplicityToElement(final MultiplicityElement element, final String value) {
                    DomainUtil.run(new TransactionalAction() {
                        /**
                         * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                         */
                        @Override
                        public void doExecute() {
                            if (value.equals(MultiplicityType.SINGLE_STAR.toString())) {
                                // *
                                element.setLower(0);
                                element.setUpper(LiteralUnlimitedNatural.UNLIMITED);
                            } else if (value.equals(MultiplicityType.ZERO_TO_UNIQUE.toString())) {
                                // 0.. 1
                                element.setLower(0);
                                element.setUpper(1);
                            } else if (value.equals(MultiplicityType.UNIQUE.toString())) {
                                // 1
                                element.setLower(1);
                                element.setUpper(1);
                            } else if (value.equals(MultiplicityType.UNIQUE_TO_SINGLE_STAR.toString())) {
                                // 1.. *
                                element.setLower(1);
                                element.setUpper(LiteralUnlimitedNatural.UNLIMITED);
                            } else if (value.equals(MultiplicityType.NONE.toString())) {
                                // 1
                                element.setLower(1);
                                element.setUpper(1);
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
                private void setNameToElement(final NamedElement element, final String value) {
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
        table.setMenu(menu);

        // 버튼
        buttonComposite = new Composite(sectionComposite, SWT.NONE);
        sectionLayout = new GridLayout();
        buttonComposite.setLayout(sectionLayout);
        Button addButton = new Button(buttonComposite, SWT.NONE);
        addButton.setText(UMLMessage.getMessage(UMLMessage.LABEL_ADD));
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
                        createParameter();
                    }
                });
                tableViewer.refresh();
                packColumns();
            }
        });
        Button deleteButton = new Button(buttonComposite, SWT.NONE);
        deleteButton.setText(UMLMessage.getMessage(UMLMessage.LABEL_DELETE));
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
                        removeParameter();
                    }
                });
                tableViewer.refresh();
                packColumns();
            }
        });
        tableViewer.setInput(this.getData());
        packColumns();
        
        return container;
    }

    /**
     * @see org.eclipse.jface.dialogs.Dialog#okPressed()
     */
    @Override
    protected void okPressed() {
        super.okPressed();
    }
    
    /**
     * 
     * void
     */
    private void createParameter() {
        uniqueName = UMLManager.getPackagedUniqueName(this.getData(), PREFIX);
        this.getData().createOwnedParameter(uniqueName, null);
    }

    /**
     * 
     * void
     */
    @SuppressWarnings("unchecked")
    private void removeParameter() {
        IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
        for (Iterator<Property> iterator = selection.iterator(); iterator.hasNext();) {
            this.getData().getOwnedParameters().remove(iterator.next());
        }
    }

    /**
     * 테이블 뷰어에서 선택된 셀에 해당하는 에디터를 생성.
     * 
     * @param columnNumber
     * @return CellEditor 해당 컬럼에서 사용할 에디터.
     */
    public CellEditor getCellEditors(int columnNumber, final Object element) {
        switch (columnNumber) {
            case _IMAGE_COLUMN:
                return null;
            case _DIRECTION_COLUMN:
                cellEditor = new ComboBoxViewerCellEditor(tableViewer.getTable(), SWT.READ_ONLY);
                ((ComboBoxViewerCellEditor) cellEditor).setContenProvider(new IStructuredContentProvider() {
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
                });
                ((ComboBoxViewerCellEditor) cellEditor).setInput(DIRECTIONS);
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
                // return (Object[]) inputElement;
                // }
                //
                // /**
                // * @see
                // org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
                // * java.lang.Object, java.lang.Object)
                // */
                // public void inputChanged(Viewer viewer, Object oldInput,
                // Object newInput) {
                // }
                // });
                // ((ComboBoxViewerCellEditor)
                // cellEditor).setInput(DEFAULT_VALUES);
                // return cellEditor;
                Parameter parameter = (Parameter) element;
                if (parameter.getType() == null) {
                    return null;
                } else if (parameter.getType() instanceof Element && !(parameter.getType() instanceof PrimitiveType)) {
                    // cellEditor = new TextCellEditor(tableViewer.getTable());
                    // return cellEditor;
                    return null;
                } else if (UICoreConstant.STRING_LITERAL.equals(parameter.getType().getName())) {
                    return new TextCellEditor(tableViewer.getTable());

                } else if ("Integer".equals(parameter.getType().getName())) { //$NON-NLS-1$
                    return new TextCellEditor(tableViewer.getTable());
                } else if ("Boolean".equals(parameter.getType().getName())) { //$NON-NLS-1$
                    createBooleanComboBox();
                    return cellEditor;
                } else if ("UnlimitedNatural".equals(parameter.getType().getName())) { //$NON-NLS-1$
                    return new TextCellEditor(tableViewer.getTable());
                } else {
                    Log.info("None Operation ,None Operation ,None Operation ,None Operation ,None Operation ,None Operation ,None Operation ,None Operation ,None Operation ,None Operation ,None Operation ,None Operation ,None Operation ,None Operation ,None Operation ,None Operation ,None Operation ,None Operation");
                }
                cellEditor = new TextCellEditor(tableViewer.getTable());

            case _TYPE_COLUMN:
                cellEditor = new UMLTypeCellEditor(tableViewer.getTable(), ((Parameter) element).getType(), (Element) element);
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
                ((ComboBoxViewerCellEditor) cellEditor).setContenProvider(new IStructuredContentProvider() {
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
                });
                ((ComboBoxViewerCellEditor) cellEditor).setInput(IS_BOOLEANS);
                return cellEditor;
            case _IS_UNIQUE_COLUMN:
                cellEditor = new ComboBoxViewerCellEditor(tableViewer.getTable(), SWT.READ_ONLY);
                ((ComboBoxViewerCellEditor) cellEditor).setContenProvider(new IStructuredContentProvider() {
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
                });
                ((ComboBoxViewerCellEditor) cellEditor).setInput(IS_BOOLEANS);
                return cellEditor;
            case _MULTIPLICITY_COLUMN:
                cellEditor = new ComboBoxViewerCellEditor(tableViewer.getTable());
                ((ComboBoxViewerCellEditor) cellEditor).setContenProvider(new IStructuredContentProvider() {
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
                });
                ((ComboBoxViewerCellEditor) cellEditor).setInput(MULTIPLICITIES);
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
        ((ComboBoxViewerCellEditor) cellEditor).setContenProvider(new IStructuredContentProvider() {
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
        });
        ((ComboBoxViewerCellEditor) cellEditor).setInput(UICoreConstant.UMLSECTION_CONSTANTS__DEFAULT_VALUES_BOOLEAN);

    }

    /**
     * 테이블 뷰어의 각 컬럼의 폭을 조정함. void
     */
    protected void packColumns() {
        // 2012-02-24 nspark 수정.
        //컬럼 사이즈는 기본 값으로 설정하여 매번 resize 되지 않도록 수정
//        TableColumn[] tableColumns = tableViewer.getTable().getColumns();
//        for (TableColumn tableColumn : tableColumns) {
//            tableColumn.pack();
//        }
    }
}
