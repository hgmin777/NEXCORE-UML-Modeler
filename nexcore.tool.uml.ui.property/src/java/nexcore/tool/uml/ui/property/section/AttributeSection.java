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
import nexcore.tool.uml.core.util.TypeSelectDialogType;
import nexcore.tool.uml.manager.UMLHelper;
import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.manager.transaction.TransactionalAction;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.dialog.TypeSelectDialog;
import nexcore.tool.uml.ui.property.UMLTypeCellEditor;
import nexcore.tool.uml.ui.property.command.DefaultValueTrasactionAction;
import nexcore.tool.uml.ui.property.provider.AttributeSectionContentProvider;
import nexcore.tool.uml.ui.property.provider.AttributeSectionLabelProvider;
import nexcore.tool.uml.ui.property.type.MultiplicityType;
import nexcore.tool.uml.ui.property.type.VisibilityType;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.dialogs.IDialogConstants;
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
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Feature;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.LiteralBoolean;
import org.eclipse.uml2.uml.LiteralInteger;
import org.eclipse.uml2.uml.LiteralString;
import org.eclipse.uml2.uml.LiteralUnlimitedNatural;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.StructuredClassifier;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.ValueSpecification;
import org.eclipse.uml2.uml.VisibilityKind;


/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.section</li>
 * <li>설  명 : AttributeSection</li>
 * <li>작성일 : 2009. 12. 30.</li>
 * <li>작성자 : 변영민</li>
 * </ul>
 */
public class AttributeSection extends AbstractPropertyCommonSection {

    /** _IMAGE_COLUMN */
    private static final int _IMAGE_COLUMN = 0;

    /** _NAME_COLUMN */
    private static final int _NAME_COLUMN = 1;

    /** _TYPE_COLUMN */
    private static final int _TYPE_COLUMN = 2;

    /** _DEFAULT_VALUE_COLUMN */
    private static final int _DEFAULT_VALUE_COLUMN = 3;

    /** _VISIBILITY_COLUMN */
    private static final int _VISIBILITY_COLUMN = 4;

    /** _STATIC_COLUMN */
    private static final int _STATIC_COLUMN = 5;

    /** _MULTIPLICITY_COLUMN */
    private static final int _MULTIPLICITY_COLUMN = 6;

    /** COLUMN_NAMES */
    private static final String[] COLUMN_NAMES = new String[] { UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING,
        UMLMessage.LABEL_NAME, UMLMessage.LABEL_TYPE, UMLMessage.LABEL_DEFAULT_VALUE, UMLMessage.LABEL_VISIBILITY,
        UMLMessage.LABEL_STATIC, UMLMessage.LABEL_MULTIPLICITY };

    /**
     * COLUMN_WIDTHS
     */
    private static final int[] COLUMN_WIDTHS = new int[] {60, 100, 100, 80, 80, 80, 60};
//    /** PREFIX */
//    private static final String PREFIX = UMLMessage.getMessage(UMLMessage.UML_PROPERTY);

    /** VISIBILITIES */
    private static final String[] VISIBILITIES = { VisibilityType.PUBLIC.toString(), VisibilityType.PRIVATE.toString(),
        VisibilityType.PROTECTED.toString(), VisibilityType.PACKAGE.toString() };

    /** cellEdotor */
    private CellEditor cellEditor = null;

//    /** 툴킷 */
//    private FormToolkit toolkit;
//
//    private ScrolledForm form;

    /**
     * 
     * 
     * @return Classifier
     */
    private Classifier getData() {
        return (Classifier) this.getSelectedModel();
    }

//    private CTabFolder container;

//    private Composite getContainer() {
//        // TODO Auto-generated method stub
//        return container;
//    }

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

        // ToDo
        // ViewerPane viewerPane = new ViewerPane(getSite().getPage(),
        // getPart()){
        //
        // @Override
        // public Viewer createViewer(Composite parent) {
        // // TODO Auto-generated method stub
        // return null;
        // }
        //            
        // };
        //        
        // viewerPane.createControl(getContainer());
        // ToolBarManager manager = viewerPane.getToolBarManager();
        //        
        // Action addAction = new Action( ,
        // ImageDescriptor.createFromImage(IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_ADD));

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
        // Button addButton = getWidgetFactory().createButton(buttonComposite,
        // "", SWT.NONE);
        // addButton.setForeground(ColorConstants.white);
        // addButton.setImage(IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_ADD));
        // addButton.setImage(IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_ADD));
        // ImageDescriptor.createFromImage(IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_TEXT))
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
                        createAttribute();
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
                        removeAttribute();
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
                        upperAttribute();
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
                        downAttribute();
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
        tableViewer.setContentProvider(new AttributeSectionContentProvider());

     // 2012-09-13
        // 더블클릭으로 매개변수 추가 편의 기능(추가 버튼이 불편하다고하여 반영)
        tableViewer.getTable().addMouseListener(new MouseAdapter() {

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
                            createAttribute();
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
            viewerColumn = new TableViewerColumn(tableViewer, SWT.BORDER_DOT);
            viewerColumn.getColumn().setText(COLUMN_NAMES[i]);
            viewerColumn.getColumn().setWidth(COLUMN_WIDTHS[i]);
            viewerColumn.getColumn().setAlignment(SWT.LEFT);
            viewerColumn.getColumn().setMoveable(false);
            viewerColumn.getColumn().setResizable(true);
            final int columnNumber = i;
            viewerColumn.setLabelProvider(new AttributeSectionLabelProvider(i));
            viewerColumn.setEditingSupport(new EditingSupport(tableViewer) {
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
                        case _NAME_COLUMN:
                            return getTextOfName((Property) element);
                        case _TYPE_COLUMN:
                            return getTextOfType((Property) element);
                        case _DEFAULT_VALUE_COLUMN:
                            return getDefaultValue((Property) element);
                        case _VISIBILITY_COLUMN:
                            return ((Property) element).getVisibility().toString();
                        case _STATIC_COLUMN:
                            return Boolean.toString(((Property) element).isStatic());
                        case _MULTIPLICITY_COLUMN:
                            return getMultiplicity((Property) element);
                        default:
                            break;
                    }
                    return null;
                }

                private Object getDefaultValue(Property element) {
                    // if(element.getDefault()!=null) return
                    // element.getDefault();

                    if (element.getDefaultValue() != null) {
                        if (element.getDefaultValue() instanceof LiteralString) {

                            // return
                            // ((LiteralString)element.getDefaultValue()).getValue().toString();
                            return ((LiteralString) element.getDefaultValue()).getValue();
                        } else if (element.getDefaultValue() instanceof LiteralInteger) {
                            return String.valueOf(((LiteralInteger) element.getDefaultValue()).getValue());
                        } else if (element.getDefaultValue() instanceof LiteralUnlimitedNatural) {
                            return String.valueOf(((LiteralUnlimitedNatural) element.getDefaultValue()).getValue());
                        } else if (element.getDefaultValue() instanceof LiteralBoolean) {
                            return ((LiteralBoolean) element.getDefaultValue());
                        } else {
                            return UICoreConstant.UMLSECTION_CONSTANTS__DEFAULT_VALUES_NULL;
                        }
                    } else {
                        return UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;
                    }
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
                 * 
                 * @param element
                 * @return String
                 */
                private String getTextOfType(TypedElement element) {
                    if (element.getType() != null) {
                        stringBuffer = new StringBuffer();
                        stringBuffer.append(element.getType().getName());
                        stringBuffer.append(UICoreConstant.UMLSECTION_CONSTANTS__PARENTHESIS_LEFT);
                        stringBuffer.append(element.getType().getQualifiedName());
                        stringBuffer.append(UICoreConstant.UMLSECTION_CONSTANTS__PARENTHESIS_RIGHT);
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
                        } else {
                            // 숫자를 직접 입력한 경우
                            return multiplicityElement.getUpperValue().stringValue();
                        }
                    } else {
                        return MultiplicityType.UNIQUE.toString();
                    }
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
                protected void setValue(Object element, Object value) {
                    if (null == value) {
                        return;
                    }
                    if (value != null) {
                        switch (columnNumber) {
                            case _NAME_COLUMN:
                                setNameToElement((Property) element, (String) value);
                                break;
                            case _TYPE_COLUMN:
                                setTypeToElement((Property) element);
                                break;
                            case _DEFAULT_VALUE_COLUMN:
                                setDefaultValueToElement((Property) element, (String) value);
                                break;
                            case _VISIBILITY_COLUMN:
                                setVisibilityToElement((Property) element, (String) value);
                                break;
                            case _STATIC_COLUMN:
                                setIsStaticToElement((Property) element, (String) value);
                                break;
                            case _MULTIPLICITY_COLUMN:
                                setMultiplicityToElement((Property) element, (String) value);
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
                    if (((UMLTypeCellEditor) cellEditor).getFirstResult() != null) {
                        DomainUtil.run(new TransactionalAction() {
                            /**
                             * @see nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                             */
                            @Override
                            public void doExecute() {
                                ((Property) element).setType(((Type) ((UMLTypeCellEditor) cellEditor).getFirstResult()));
                                ((Property) element).setDefaultValue((ValueSpecification) null);
                            }
                        });
                    }

                    else {
                        // DomainUtil.run(new TransactionalAction() {
                        // /**
                        // * @see
                        // nexcore.tool.uml.manager.transaction.TransactionalAction#doExecute()
                        // */
                        // @Override
                        // public void doExecute() {
                        // if( ((Property) element).getType()!=null){
                        // ((Property) element).setType((Type)null);
                        // ((Property)
                        // element).setDefaultValue((ValueSpecification)null);
                        // }
                        // }
                        // });
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
                                element.setIsStatic(true);
                            } else if (value.equals(Boolean.toString(false))) {
                                element.setIsStatic(false);
                            }
                        }
                    });
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
                            } else {

                                try {
                                    final int intValue = new Integer(value).intValue();
                                    if (intValue > 0) {

                                        DomainUtil.run(new TransactionalAction() {
                                            @Override
                                            public void doExecute() {
                                                element.setLower(intValue);
                                                element.setUpper(intValue);
                                            }
                                        });
                                    }
                                } catch (Exception e2) {
                                    // TODO: handle exception
                                }
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
                                element.setVisibility(VisibilityKind.PUBLIC_LITERAL);
                            else if (value.equals(VisibilityType.PRIVATE.toString()))
                                element.setVisibility(VisibilityKind.PRIVATE_LITERAL);
                            else if (value.equals(VisibilityType.PROTECTED.toString()))
                                element.setVisibility(VisibilityKind.PROTECTED_LITERAL);
                            else if (value.equals(VisibilityType.PACKAGE.toString()))
                                element.setVisibility(VisibilityKind.PACKAGE_LITERAL);
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
                        createAttribute();
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
                        removeAttribute();
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
                        Object property = items[0].getData();
                        if (property instanceof Property) {
                            TypeSelectDialog dialog = new TypeSelectDialog(table.getShell(),
                                TypeSelectDialogType.PROPERTY,
                                (Property) property);
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
                                                Object property = item.getData();
                                                if (property instanceof Property) {
                                                    ((Property) property).setType((Type) firstResult);
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

    }

    /**
     * 테이블 뷰어에서 선택된 하나 이상의 속성을 삭제.
     */
    @SuppressWarnings("unchecked")
    private void removeAttribute() {
        IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
        EList<Property> attributeList = null;
        if (getData() instanceof StructuredClassifier) {
            attributeList = ((StructuredClassifier) getData()).getOwnedAttributes();
        } else if (getData() instanceof Interface) {
            attributeList = ((Interface) getData()).getOwnedAttributes();
        } else if (getData() instanceof DataType) {
            attributeList = ((DataType) getData()).getOwnedAttributes();
        } else if (getData() instanceof Signal) {
            attributeList = ((Signal) getData()).getOwnedAttributes();
        }
        for (Iterator<Property> iterator = selection.iterator(); iterator.hasNext();) {
            attributeList.remove(iterator.next());
        }
    }

    /**
     * 선택된 Attribute를 위로 이동
     */
    @SuppressWarnings("unchecked")
    private void upperAttribute() {
        IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
        EList<Property> attributeList = null;
        if (getData() instanceof StructuredClassifier) {
            attributeList = ((StructuredClassifier) getData()).getOwnedAttributes();
        } else if (getData() instanceof Interface) {
            attributeList = ((Interface) getData()).getOwnedAttributes();
        } else if (getData() instanceof DataType) {
            attributeList = ((DataType) getData()).getOwnedAttributes();
        } else if (getData() instanceof Signal) {
            attributeList = ((Signal) getData()).getOwnedAttributes();
        }
        int index = 0;
        for (Iterator<Property> iterator = selection.iterator(); iterator.hasNext();) {
            Property next = iterator.next();
            index = attributeList.indexOf(next);
            if (index > 0)
                attributeList.move(index - 1, next);
        }
    }

    /**
     * 선택된 Attribute를 아래로 이동
     */
    @SuppressWarnings("unchecked")
    private void downAttribute() {
        IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
        EList<Property> attributeList = null;
        if (getData() instanceof StructuredClassifier) {
            attributeList = ((StructuredClassifier) getData()).getOwnedAttributes();
        } else if (getData() instanceof Interface) {
            attributeList = ((Interface) getData()).getOwnedAttributes();
        } else if (getData() instanceof DataType) {
            attributeList = ((DataType) getData()).getOwnedAttributes();
        } else if (getData() instanceof Signal) {
            attributeList = ((Signal) getData()).getOwnedAttributes();
        }
        int index = 0;
        for (Iterator<Property> iterator = selection.iterator(); iterator.hasNext();) {
            Property next = iterator.next();
            index = attributeList.indexOf(next);
            if (index < attributeList.size() - 1)
                attributeList.move(index + 1, next);
        }
    }

    /**
     * 하나의 속성을 생성함.
     */
    private void createAttribute() {
        uniqueName = (UMLManager.getPackagedUniqueName(this.getData(), UMLMessage.getMessage(UMLMessage.UML_PROPERTY)));
        Property property = UMLHelper.createProperty();
        property.setName(uniqueName);
        if (getData() instanceof StructuredClassifier) {
            ((StructuredClassifier) getData()).getOwnedAttributes().add(property);
        } else if (getData() instanceof Interface) {
            ((Interface) getData()).getOwnedAttributes().add(property);
        } else if (getData() instanceof DataType) {
            ((DataType) getData()).getOwnedAttributes().add(property);
        } else if (getData() instanceof Signal) {
            ((Signal) getData()).getOwnedAttributes().add(property);
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
            case _NAME_COLUMN:
                cellEditor = new TextCellEditor(tableViewer.getTable());
                return cellEditor;
            case _TYPE_COLUMN:
                cellEditor = new UMLTypeCellEditor(tableViewer.getTable(), ((Property) element).getType(), (Element) element);
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
            case _DEFAULT_VALUE_COLUMN:
                Property property = (Property) element;
                if (property.getType() == null) {

                    return null;
                } else if (property.getType() instanceof Element && !(property.getType() instanceof PrimitiveType)) {
                    // cellEditor = new TextCellEditor(tableViewer.getTable());
                    // return cellEditor;
                    return null;
                } else if (property.getType().getName() != null && 
                		UICoreConstant.PROJECT_CONSTANTS__BOOLEAN.equals(property.getType().getName().toLowerCase())) {
                    createBooleanComboBox();
                    return cellEditor;
                } else {
                    return new TextCellEditor(tableViewer.getTable());
                }

            case _VISIBILITY_COLUMN:
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
                ((ComboBoxViewerCellEditor) cellEditor).setInput(VISIBILITIES);
                return cellEditor;
            case _STATIC_COLUMN:
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
                ((ComboBoxViewerCellEditor) cellEditor).setInput(UICoreConstant.UMLSECTION_CONSTANTS__IS_BOOLEANS);
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
