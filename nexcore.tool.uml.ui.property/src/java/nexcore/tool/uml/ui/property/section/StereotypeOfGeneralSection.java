/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.property.section;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.property.command.HandleStereotypeCommand;
import nexcore.tool.uml.ui.property.dialog.SelectApplicableStereotypeDialog;
import nexcore.tool.uml.ui.property.provider.AppliedStereotypeColumnLabelProvider;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.section</li>
 * <li>설  명 : StereotypeOfGeneralSection</li>
 * <li>작성일 : 2009. 12. 23.</li>
 * <li>작성자 : 변영민</li>
 * </ul>
 */
public class StereotypeOfGeneralSection extends AbstractPropertyCommonSection {

    /** COLUMN_NAMES */
    protected static final String[] COLUMN_NAMES = new String[] { UMLMessage.getMessage(UMLMessage.LABEL_STEREOTYPE),
        UMLMessage.getMessage(UMLMessage.LABEL_PROFILE), UMLMessage.getMessage(UMLMessage.LABEL_ESSENTIALITY) };

    /**
     * COLUMN_WIDTHS
     */
    private static final int[] COLUMN_WIDTHS = new int[] {150, 300, 150};
    
    /** appliedStereotypeLabel */
    private CLabel appliedStereotypeLabel;

    /** appliedStereotypeTable */
    private Table appliedStereotypeTable;

    /** applyStereotypeButton */
    private Button applyStereotypeButton;

    /** unapplyStereotypeButton */
    private Button unapplyStereotypeButton;

    /** stereotypePropertyLabel */
    private CLabel stereotypePropertyLabel;

    /** stereotypePropertyComposite */
    private StereotypePropertyComposite stereotypePropertyComposite;

    /**
     * 
     * void
     */
    private void createStereoTypeComposite() {
        appliedStereotypeLabel = getWidgetFactory().createCLabel(this.sectionComposite,
            UMLMessage.getMessage(UMLMessage.LABEL_APPLIED_STEREOTYPE) + UICoreConstant.PROJECT_CONSTANTS__COLON);
        gridData = new GridData();
        gridData.horizontalSpan = 2;
        appliedStereotypeLabel.setLayoutData(gridData);

        // 적용된 스테레오타입 테이블 뷰어
        tableViewer = new TableViewer(this.sectionComposite, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION | SWT.V_SCROLL);
        appliedStereotypeTable = tableViewer.getTable();
        gridData = new GridData(GridData.FILL_HORIZONTAL);
        gridData.heightHint = UICoreConstant.UMLSECTION_CONSTANTS___TABLE_OF_APPLIED_STEREOTYPE_HEIGHT;
        gridData.horizontalSpan = 2;
        appliedStereotypeTable.setLayoutData(gridData);

        appliedStereotypeTable.setLinesVisible(true);
        appliedStereotypeTable.setHeaderVisible(true);
        tableViewer.setUseHashlookup(true);
        tableViewer.setColumnProperties(COLUMN_NAMES);
        tableViewer.setContentProvider(new ArrayContentProvider());

        TableViewerColumn appliedStereotypeViewerColumn;
        for (int columnIdx = 0; columnIdx < COLUMN_NAMES.length; columnIdx++) {
            appliedStereotypeViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
            appliedStereotypeViewerColumn.getColumn().setText(COLUMN_NAMES[columnIdx]);
            appliedStereotypeViewerColumn.getColumn().setWidth(COLUMN_WIDTHS[columnIdx]);
            appliedStereotypeViewerColumn.getColumn().setAlignment(SWT.LEFT);
            appliedStereotypeViewerColumn.getColumn().setMoveable(false);
            appliedStereotypeViewerColumn.setLabelProvider(new AppliedStereotypeColumnLabelProvider(columnIdx));
        }

        // 버튼
        applyStereotypeButton = getWidgetFactory().createButton(this.sectionComposite,
            UMLMessage.getMessage(UMLMessage.LABEL_APPLY_STEREOTYPE)
                + UICoreConstant.UMLSECTION_CONSTANTS__TRIPLE_DOT_TEXT,
            SWT.NONE);
        gridData = new GridData();
        gridData.widthHint = 140;
        applyStereotypeButton.setLayoutData(gridData);
        applyStereotypeButton.addListener(SWT.MouseDown, new Listener() {
            /**
             * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
             */
            public void handleEvent(Event event) {
                SelectApplicableStereotypeDialog selectApplicableStereotypeDialog = new SelectApplicableStereotypeDialog(applyStereotypeButton.getShell(),
                    getSelectedModel().getApplicableStereotypes(),
                    getSelectedModel().getAppliedStereotypes());

                if (selectApplicableStereotypeDialog.open() == Window.OK) {
                    RecordingCommand command = new HandleStereotypeCommand(DomainRegistry.getEditingDomain(),
                        getSelectedModel(),
                        selectApplicableStereotypeDialog.getSelectedStereotypeList(),
                        true);
                    DomainUtil.executeCommand(command);
                    tableViewer.setInput(getSelectedModel().getAppliedStereotypes().toArray());
                    tableViewer.refresh();
                    packColumns();
                    refreshChildren();

                }
            }
        });
        unapplyStereotypeButton = getWidgetFactory().createButton(this.sectionComposite,
            UMLMessage.getMessage(UMLMessage.LABEL_UNAPPLY_STEREOTYPE),
            SWT.NONE);
        gridData = new GridData();
        gridData.widthHint = 140;
        unapplyStereotypeButton.setLayoutData(gridData);
        unapplyStereotypeButton.addListener(SWT.MouseDown, new Listener() {
            /**
             * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
             */
            @SuppressWarnings("unchecked")
            public void handleEvent(Event event) {
                ISelection tableViewerSelection = tableViewer.getSelection();
                if(tableViewerSelection != null && !tableViewerSelection.isEmpty()) {
                    MessageBox messageBox = new MessageBox(unapplyStereotypeButton.getShell(), SWT.YES | SWT.NO
                        | SWT.ICON_QUESTION);
                    messageBox.setMessage(UMLMessage.MESSAGE_UNAPPLY_STEREOTYPE);
                    messageBox.setText(UMLMessage.LABEL_UNAPPLY_STEREOTYPE);
                    if (messageBox.open() == SWT.YES) {
                        IStructuredSelection selections = (IStructuredSelection) tableViewer.getSelection();
                        RecordingCommand command = new HandleStereotypeCommand(DomainRegistry.getEditingDomain(),
                            getSelectedModel(),
                            selections.iterator(),
                            false);
                        DomainUtil.executeCommand(command);
                        tableViewer.setInput(getSelectedModel().getAppliedStereotypes().toArray());
                        tableViewer.refresh();
                        packColumns();
                        refreshChildren();
                    }
                }
            }
        });
        stereotypePropertyLabel = getWidgetFactory().createCLabel(this.sectionComposite,
            UMLMessage.getMessage(UMLMessage.LABEL_STEREOTYPE_PROPERTY) + UICoreConstant.PROJECT_CONSTANTS__COLON);
        gridData = new GridData();
        gridData.horizontalSpan = 2;
        stereotypePropertyLabel.setLayoutData(gridData);

        stereotypePropertyComposite = new StereotypePropertyComposite(this.sectionComposite);
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#createMainComposite()
     */
    @Override
    public void createMainComposite() {
        gridData = new GridData(GridData.FILL_BOTH);
        parent.setLayoutData(gridData);
        sectionComposite = getWidgetFactory().createComposite(parent);
        sectionLayout = new GridLayout(2, false);
        sectionComposite.setLayout(sectionLayout);
        this.createStereoTypeComposite();
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#unsetListener()
     */
    @Override
    protected void unsetListener() {

    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#refreshChildren()
     */
    @Override
    public void refreshChildren() {
        if (tableViewer.getContentProvider() != null) {
            tableViewer.setInput(getSelectedModel().getAppliedStereotypes().toArray());
            stereotypePropertyComposite.setInput(this.getSelectedModel());
            packColumns();
        }
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#refresh()
     */
    @Override
    public void refresh() {
        // TODO Auto-generated method stub
        super.refresh();
        refreshChildren();
    }

    /**
     * @see nexcore.tool.uml.ui.property.section.AbstractPropertyCommonSection#setListener()
     */
    @Override
    protected void setListener() {

    }
}
