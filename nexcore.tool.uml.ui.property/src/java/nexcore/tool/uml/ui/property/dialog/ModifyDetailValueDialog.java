/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.property.dialog;

import java.util.List;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.ui.property.command.HandleDetailValueCommand;
import nexcore.tool.uml.ui.property.provider.DetailValueLabelProvider;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.dialog</li>
 * <li>설  명 : ModifyDetailValueDialog</li>
 * <li>작성일 : 2009. 12. 30.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class ModifyDetailValueDialog extends Dialog {

    /** 선택 객체의 부모 */
    private DynamicEObjectImpl parentObject;

    /** 다이얼로그 제목 */
    private String title;

    /** 상세 값 목록 */
    private List<EStructuralFeature> detailValueList;

    /** 테이블 뷰어 */
    private TableViewer tableViewer;

    /**
     * 생성자
     * 
     * @param parent
     */
    protected ModifyDetailValueDialog(Shell parent) {
        super(parent);
    }

    /**
     * 생성자
     * 
     * @param parent
     * @param parentObject
     * @param title
     * @param detailValueList
     */
    public ModifyDetailValueDialog(Shell parent, DynamicEObjectImpl parentObject, String title,
    EList<EStructuralFeature> detailValueList) {
        this(parent);

        this.parentObject = parentObject;
        this.title = title;
        this.detailValueList = detailValueList;
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
        gridLayout.numColumns = 1;
        composite.setLayout(gridLayout);

        createValueTableComposite(composite);

        return composite;
    }

    /**
     * 상세 값 수정 테이블 컴포짓 생성
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
        indexColumn.getColumn().setText(UMLMessage.LABEL_NAME);
        indexColumn.getColumn().setWidth(100);
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
                EStructuralFeature selectedObject = (EStructuralFeature) ((StructuredSelection) event.getSelection()).getFirstElement();

                EClassifier eType = selectedObject.getEType();

                if (eType instanceof EDataType) {
                    EDataType eDataType = (EDataType) eType;

                    if (eDataType instanceof EEnum) {
                        Object[] enumerationList = ((EEnum) eDataType).getELiterals().toArray();
                        SelectComboValueDialog selectComboValueDialog = new SelectComboValueDialog(composite.getShell(),
                            selectedObject.getName(),
                            enumerationList);

                        if (selectComboValueDialog.open() == SelectComboValueDialog.OK) {
                            RecordingCommand command = new HandleDetailValueCommand(DomainRegistry.getEditingDomain(),
                                parentObject,
                                selectedObject,
                                selectComboValueDialog.getSelectedEnumerationLiteral());
                            DomainUtil.executeCommand(command);
                        }
                    } else {
                        if (getFeatureValue(selectedObject) instanceof String) {
                            InputDialog inputValueDialog = new InputDialog(composite.getShell(),
                                UMLMessage.LABEL_INPUT_VALUE,
                                UMLMessage.MESSAGE_INPUT_VALUE,
                                getFeatureValue(selectedObject),
                                null);

                            if (inputValueDialog.open() == InputDialog.OK) {
                                RecordingCommand command = new HandleDetailValueCommand(DomainRegistry.getEditingDomain(),
                                    parentObject,
                                    selectedObject,
                                    inputValueDialog.getValue());
                                DomainUtil.executeCommand(command);
                            }
                        }
                    }

                    tableViewer.refresh();
                }
            }
        });

        tableViewer.setContentProvider(new ArrayContentProvider());
        tableViewer.setLabelProvider(new DetailValueLabelProvider(parentObject));

        tableViewer.setInput(detailValueList.toArray());

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
     * 피쳐 값 반환
     * 
     * @param selectedObject
     * @return String
     */
    private String getFeatureValue(EStructuralFeature selectedObject) {
        try {
            Object value = parentObject.eGet(selectedObject);
            return value.toString();
        } catch (NullPointerException npe) {
            return ""; //$NON-NLS-1$
        }
    }
}
