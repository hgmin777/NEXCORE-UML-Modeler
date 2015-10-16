/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.property.dialog;

import java.util.List;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.ui.core.util.ProfileUtil;
import nexcore.tool.uml.ui.property.command.HandlePropertyCommand;
import nexcore.tool.uml.ui.property.provider.MultiValueLabelProvider;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.dialog</li>
 * <li>설  명 : InputMultiValueDialog</li>
 * <li>작성일 : 2009. 12. 28.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class InputMultiValueDialog extends Dialog {

    /** 요소 */
    private Element element;

    /** 프로퍼티 */
    private Property property;

    /** 다이얼로그 제목 */
    private String title;

    /** 추가된 값 목록 */
    private List<Object> propertyValueList;

    /** 테이블 뷰어 */
    private TableViewer tableViewer;

    /** 값 추가 버튼 */
    private Button addValueButton;

    /** 값 삭제 버튼 */
    private Button deleteValueButton;

    /**
     * 생성자
     * 
     * @param parent
     */
    protected InputMultiValueDialog(Shell parent) {
        super(parent);
    }

    /**
     * 생성자
     * 
     * @param parent
     * @param property
     * @param title
     * @param propertyValueList
     */
    public InputMultiValueDialog(Shell parent, Property property, String title, List<Object> propertyValueList) {
        this(parent);
        this.property = property;
        this.title = title;

        this.propertyValueList = propertyValueList;
    }

    /**
     * 생성자
     * 
     * @param parent
     * @param element
     * @param property
     * @param title
     * @param propertyValueList
     */
    public InputMultiValueDialog(Shell parent, Element element, Property property, String title,
    List<Object> propertyValueList) {
        this(parent);
        this.element = element;
        this.property = property;
        this.title = title;

        this.propertyValueList = propertyValueList;
    }

    /**
     * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        super.createButtonsForButtonBar(parent);
    }

    /**
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        Composite composite = (Composite) super.createDialogArea(parent);
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        composite.setLayout(gridLayout);

        createValueTableComposite(composite);
        createButtonComposite(composite);

        return composite;
    }

    /**
     * 값 입력 테이블 컴포짓 생성
     * 
     * @param composite
     *            void
     */
    private void createValueTableComposite(final Composite composite) {
        tableViewer = new TableViewer(composite, SWT.SINGLE | SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.BORDER);

        Table table = tableViewer.getTable();

        GridData gridData = new GridData(GridData.FILL_BOTH);
        gridData.heightHint = 200;
        table.setLayoutData(gridData);
        table.setLinesVisible(true);
        table.setHeaderVisible(true);

        TableViewerColumn indexColumn = new TableViewerColumn(tableViewer, SWT.NONE);
        indexColumn.getColumn().setText(UMLMessage.LABEL_INDEX);
        indexColumn.getColumn().setWidth(50);
        indexColumn.getColumn().setAlignment(SWT.LEFT);

        TableViewerColumn valueColumn = new TableViewerColumn(tableViewer, SWT.NONE);
        valueColumn.getColumn().setText(UMLMessage.LABEL_VALUE);
        valueColumn.getColumn().setWidth(120);
        valueColumn.getColumn().setAlignment(SWT.LEFT);
        tableViewer.addDoubleClickListener(new IDoubleClickListener() {
            /**
             * @see org.eclipse.jface.viewers.IDoubleClickListener#doubleClick(org.eclipse.jface.viewers.DoubleClickEvent)
             */
            public void doubleClick(DoubleClickEvent event) {
                DynamicEObjectImpl selectedObject = null;

                if (((StructuredSelection) event.getSelection()).getFirstElement() instanceof DynamicEObjectImpl) {
                    selectedObject = (DynamicEObjectImpl) ((StructuredSelection) event.getSelection()).getFirstElement();
                }

                ModifyDetailValueDialog modifyDetailValueDialog = new ModifyDetailValueDialog(composite.getShell(),
                    selectedObject,
                    selectedObject.eClass().getName(),
                    selectedObject.eClass().getEStructuralFeatures()); //$NON-NLS-1$

                if (modifyDetailValueDialog.open() == ModifyDetailValueDialog.OK) {}
            }
        });

        tableViewer.setContentProvider(new ArrayContentProvider());
        tableViewer.setLabelProvider(new MultiValueLabelProvider());

        tableViewer.setInput(propertyValueList.toArray());
    }

    /**
     * 버튼 컴포짓 생성
     * 
     * @param composite
     *            void
     */
    private void createButtonComposite(Composite composite) {
        final Composite buttonComposite = new Composite(composite, SWT.NONE);

        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 1;
        buttonComposite.setLayout(gridLayout);

        GridData gridData = new GridData(GridData.FILL_VERTICAL);
        buttonComposite.setLayoutData(gridData);

        addValueButton = new Button(buttonComposite, SWT.PUSH);
        addValueButton.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_CENTER));
        addValueButton.setText(UMLMessage.LABEL_ADD);
        addValueButton.addSelectionListener(new SelectionAdapter() {
            /**
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected(SelectionEvent e) {
                if (property.getType() instanceof PrimitiveType) {
                    InputDialog inputValueDialog = new InputDialog(buttonComposite.getShell(),
                        UMLMessage.LABEL_INPUT_VALUE,
                        UMLMessage.MESSAGE_INPUT_VALUE,
                        "", //$NON-NLS-1$
                        null);

                    if (inputValueDialog.open() == InputDialog.OK) {
                        int valueCount = ProfileUtil.getValueListOfProperty((NamedElement) element,
                            ((Property) property)).size();

                        RecordingCommand command = new HandlePropertyCommand(DomainRegistry.getEditingDomain(),
                            (NamedElement) element,
                            ((Property) property),
                            valueCount,
                            inputValueDialog.getValue());
                        DomainUtil.executeCommand(command);

                        tableViewer.setInput(ProfileUtil.getValueListOfProperty((NamedElement) element,
                            ((Property) property)));
                        tableViewer.refresh();
                    }
                } else {
                    Stereotype parentStereotype = (Stereotype) ((Property) property).eContainer();
                    EObject metaParentClass = element.getStereotypeApplication(parentStereotype);

                    EClassifier metaClass = metaParentClass.eClass().getEPackage().getEClassifier(property.getType()
                        .getName());

                    if (metaClass instanceof EClass) {
                        EClass eClass = (EClass) metaClass;

                        EFactory instanceFactory = metaParentClass.eClass().getEPackage().getEFactoryInstance();
                        EObject instanceClass = instanceFactory.create(eClass);

                        int valueCount = ProfileUtil.getValueListOfProperty((NamedElement) element,
                            ((Property) property)).size();

                        RecordingCommand command = new HandlePropertyCommand(DomainRegistry.getEditingDomain(),
                            (NamedElement) element,
                            ((Property) property),
                            valueCount,
                            instanceClass);
                        DomainUtil.executeCommand(command);

                        tableViewer.setInput(ProfileUtil.getValueListOfProperty((NamedElement) element,
                            ((Property) property)));
                        tableViewer.refresh();
                    }
                }
            }
        });

        deleteValueButton = new Button(buttonComposite, SWT.PUSH);
        deleteValueButton.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_CENTER));
        deleteValueButton.setText(UMLMessage.LABEL_DELETE);
        deleteValueButton.addSelectionListener(new SelectionAdapter() {
            /**
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected(SelectionEvent e) {
                Object selectedValue = ((IStructuredSelection) tableViewer.getSelection()).getFirstElement();

                propertyValueList.remove(selectedValue);

                tableViewer.setInput(propertyValueList.toArray());
                tableViewer.refresh();
            }
        });
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
     * 프로퍼티 값 목록 반환
     * 
     * @return List<Object>
     */
    public List<Object> getPropertyValueList() {
        return propertyValueList;
    }

}
