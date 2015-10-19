/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.property.section;

import java.util.ArrayList;
import java.util.List;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.UMLHelper;
import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.manager.utility.SequenceManagerUtil;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.realization.RealizationPlugin;
import nexcore.tool.uml.ui.realization.sequencediagram.dialog.CreateOperationDialog;
import nexcore.tool.uml.ui.realization.sequencediagram.util.SequenceUtil;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageOccurrenceSpecification;
import org.eclipse.uml2.uml.MessageSort;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.SendOperationEvent;
import org.eclipse.uml2.uml.Type;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.section</li>
 * <li>설  명 : SignatureGeneralSection</li>
 * <li>작성일 : 2010. 1. 19.</li>
 * <li>작성자 : 변영민</li>
 * </ul>
 */
public class SignatureGeneralSection extends AbstractPropertyCommonSection implements SelectionListener {

    /** signatureLabel */
    private CLabel signatureLabel;

    /** signatueCombo */
    private CCombo signatureCombo;

    /** create operation button */
    private Button createButton;

    /** 해당 메시지가 선택할 수 있는 Operations. */
    private List<Operation> operations;

    /** Lifeline에 지정되어 있는 Type */
    private Type type = null;

    /**
     * TypedElement 모델을 리턴.
     * 
     * @return TypedElement
     */
    private Message getData() {
        return (Message) this.getSelectedModel();
    }

    /**
     * @see nexcore.tool.uml.ui.realization.sheet.section.AbstractPropertyCommonSection#createMainComposite()
     */
    @Override
    public void createMainComposite() {
        operations = new ArrayList<Operation>();
        sectionComposite = getWidgetFactory().createComposite(parent);
        sectionLayout = new GridLayout(3, false);
        sectionComposite.setLayout(sectionLayout);

        signatureLabel = getWidgetFactory().createCLabel(sectionComposite,
            UMLMessage.LABEL_SIGNATURE + UICoreConstant.UMLSECTION_CONSTANTS__COLON_TEXT);
        gridData = new GridData();
        gridData.widthHint = UICoreConstant.UMLSECTION_CONSTANTS___LABEL_WIDTH_HINT_FOR_RECTANGLE;
        signatureLabel.setLayoutData(gridData);
        signatureCombo = getWidgetFactory().createCCombo(sectionComposite, SWT.READ_ONLY);

        createButton = getWidgetFactory().createButton(sectionComposite, UMLMessage.LABEL_ADD, SWT.PUSH);
    }

    /**
     * @see nexcore.tool.uml.ui.realization.sheet.section.AbstractPropertyCommonSection#refreshChildren()
     */
    @Override
    protected void refreshChildren() {
        if (!signatureCombo.isDisposed()) {
            signatureCombo.removeAll();
            signatureCombo.add(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING);
            if (operations != null)
                operations.clear();
            operations = getAllOperations();
            if (operations != null) {
                for (Operation operation : operations) {
                    signatureCombo.add(SequenceUtil.getOperationVisibility(operation)
                        + UICoreConstant.PROJECT_CONSTANTS__BLANK + ((NamedElement) operation.eContainer()).getName()
                        + UICoreConstant.PROJECT_CONSTANTS__DOUBLE_COLON + operation.getName() + " ()");
                }
                signatureCombo.select(getAssignedOperation());
            }
        }
    }

    /**
     * 설정된 Operation을 가져와서 콤보박스에서 설정된 값을 보여준다.
     * 
     * @return int signatureCombo의 index
     */
    private int getAssignedOperation() {
        MessageOccurrenceSpecification mos = (MessageOccurrenceSpecification) getData().getSendEvent();
        SendOperationEvent soe = (SendOperationEvent) mos.getEvent();

        if (mos != null && soe != null) {
            Operation operation = soe.getOperation();
            for (int i = 0; i < operations.size(); i++) {
                if (operations.get(i).equals(operation)) {
                    return i + 1;
                }
            }
        }
        return 0;
    }

    /**
     * @see nexcore.tool.uml.ui.realization.sheet.section.AbstractPropertyCommonSection#setListener()
     */
    @Override
    protected void setListener() {
        if (!signatureCombo.isDisposed()) {
            this.signatureCombo.addSelectionListener(this);
        }
        if (!createButton.isDisposed()) {
            this.createButton.addSelectionListener(this);
        }

    }

    /**
     * @see nexcore.tool.uml.ui.realization.sheet.section.AbstractPropertyCommonSection#unsetListener()
     */
    @Override
    protected void unsetListener() {
        if (!signatureCombo.isDisposed()) {
            this.signatureCombo.removeSelectionListener(this);
        }
        if (!createButton.isDisposed()) {
            this.createButton.removeSelectionListener(this);
        }
    }

    /**
     * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
     */
    public void widgetDefaultSelected(SelectionEvent e) {

    }

    /**
     * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
     */
    public void widgetSelected(SelectionEvent e) {

        if (e.widget instanceof Button) {
            if (type == null) {
                MessageDialog.openInformation(RealizationPlugin.getShell(),
                    UMLMessage.MESSAGE_DIALOG_TITLE,
                    UMLMessage.MESSAGE_UNDEFINED_LIFELINETYPE);
                return;
            }

            CreateOperationDialog dialog = new CreateOperationDialog(RealizationPlugin.getShell(), type);
            if (dialog.open() == Window.OK) {

                final String operationName = dialog.getOperationName();
                final Type selectedType = dialog.getSelectedType();

                // DomainRegistry.getUMLDomain().getCommandStack().execute(new
                // Command() {
                // /**
                // * @see org.eclipse.gef.commands.Command#execute()
                // */
                // @Override
                // public void execute() {
                // Operation operation = UMLHelper.createOperation();
                // if
                // (operationName.equals(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING))
                // {
                // NamedElement namedElement = (NamedElement) operation;
                // namedElement.setName(UMLManager.getPackagedUniqueName((Namespace)
                // selectedType,
                // namedElement.getName()));
                // } else {
                // operation.setName(operationName);
                // }
                //
                // if (selectedType != null) {
                // SequenceUtil.setOperationToType(selectedType, operation);
                // }
                // SequenceUtil.setOperationToMessage(operation, getData());
                // }
                //
                // });
                DomainRegistry.getEditingDomain()
                    .getCommandStack()
                    .execute(new RecordingCommand(DomainRegistry.getEditingDomain()) {
                        /**
                         * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
                         */
                        @Override
                        public void doExecute() {
                            Operation operation = UMLHelper.createOperation();
                            if (operationName.equals(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING)) {
                                NamedElement namedElement = (NamedElement) operation;
                                namedElement.setName(UMLManager.getPackagedUniqueName((Namespace) selectedType,
                                    namedElement.getName()));
                            } else {
                                operation.setName(operationName);
                            }

                            if (selectedType != null) {
                                SequenceUtil.setOperationToType(selectedType, operation);
                            }
                            SequenceUtil.setOperationToMessage(operation, getData());
                        }
                    });

            }

        } else {
            int index = signatureCombo.getSelectionIndex();
            if (index != 0) {
                Operation operation = operations.get(index - 1);
                setMessageOperation(operation, index);
            } else {
                setMessageOperation(null, -1);
            }
        }
        refreshChildren();
    }

    /**
     * Message에 Operation을 셋팅한다.
     * 
     * @param operation
     *            void
     * @param index
     *            signatureCombo의 index
     */
    private void setMessageOperation(final Operation operation, int index) {
        if (index != getAssignedOperation()) {
            // DomainRegistry.getUMLDomain().getCommandStack().execute(new
            // Command() {
            // /**
            // * @see org.eclipse.gef.commands.Command#execute()
            // */
            // @Override
            // public void execute() {
            // SequenceUtil.setOperationToMessage(operation, getData());
            // }
            // });
            DomainRegistry.getEditingDomain()
                .getCommandStack()
                .execute(new RecordingCommand(DomainRegistry.getEditingDomain()) {
                    /**
                     * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
                     */
                    @Override
                    public void doExecute() {
                        SequenceUtil.setOperationToMessage(operation, getData());
                    }
                });
        }
    }

    /**
     * Message를 생성하려는 TargetLifeline에 유형이 지정되어 있을 때, 해당 유형의 Operation을 리스트로
     * 반환한다.
     * 
     * @return List<Operation>
     */
    private List<Operation> getAllOperations() {
        MessageSort sort = getData().getMessageSort();
        MessageOccurrenceSpecification mos = null;
        if (sort == MessageSort.REPLY_LITERAL) {
            mos = (MessageOccurrenceSpecification) getData().getSendEvent();
        } else if (sort == MessageSort.SYNCH_CALL_LITERAL || sort == MessageSort.ASYNCH_CALL_LITERAL) {
            mos = (MessageOccurrenceSpecification) getData().getReceiveEvent();
        }
        if (mos == null)
            return null;
        Lifeline lifeline = mos.getCovered(null);
        if (lifeline == null || lifeline.getRepresents() == null)
            return null;
        type = lifeline.getRepresents().getType();

        return SequenceManagerUtil.getTypeOperations(type);
    }
}
