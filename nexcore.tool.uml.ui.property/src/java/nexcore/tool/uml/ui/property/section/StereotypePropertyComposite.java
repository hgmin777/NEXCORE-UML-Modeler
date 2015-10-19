/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.property.section;

import java.util.List;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.utility.DomainModelHandlerUtil;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.model.rmdata.RMData;
import nexcore.tool.uml.model.rmdata.RMObject;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.util.ProfileUtil;
import nexcore.tool.uml.ui.property.command.HandlePropertyCommand;
import nexcore.tool.uml.ui.property.dialog.InputMultiValueDialog;
import nexcore.tool.uml.ui.property.dialog.SelectComboValueDialog;
import nexcore.tool.uml.ui.property.dialog.SelectRequirementDialog;
import nexcore.tool.uml.ui.property.provider.StereotypePropertyTableColumnLabelProvider;
import nexcore.tool.uml.ui.property.provider.StereotypePropertyTreeContentProvider;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.property</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.property.section</li>
 * <li>설  명 : StereotypePropertyComposite</li>
 * <li>작성일 : 2009. 12. 24.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class StereotypePropertyComposite {

    /** 뷰어 객체 */
    protected StructuredViewer viewer;

    /** 뷰어 입력 */
    protected Object input;

    /**
     * 생성자
     * 
     * @param parent
     */
    public StereotypePropertyComposite(Composite parent) {
        createComposite(parent);
    }

    /**
     * 뷰어가 담길 컴포지트 생성
     * 
     * @param parent
     * @return Composite
     */
    public Composite createComposite(final Composite parent) {
        Tree tree = new Tree(parent, SWT.V_SCROLL | SWT.MULTI | SWT.FULL_SELECTION);

        tree.setLinesVisible(true);
        tree.setHeaderVisible(true);

        String[] strHeadings = { UMLMessage.LABEL_PROPERTY, UMLMessage.LABEL_VALUE };

        TreeColumn propertyTreeColumn = new TreeColumn(tree, SWT.NONE);
        propertyTreeColumn.setWidth(240);
        propertyTreeColumn.setText(strHeadings[0]);
        propertyTreeColumn.setAlignment(SWT.LEFT);
        propertyTreeColumn.setResizable(true);

        TreeColumn valueTreeColumn = new TreeColumn(tree, SWT.NONE);
        valueTreeColumn.setWidth(300);
        valueTreeColumn.setText(strHeadings[1]);
        valueTreeColumn.setAlignment(SWT.LEFT);
        valueTreeColumn.setResizable(true);

        GridData gridData = new GridData(GridData.FILL_BOTH);
        gridData.heightHint = 150;
        gridData.horizontalSpan = 2;
        tree.setLayoutData(gridData);

        viewer = new TreeViewer(tree);
        tree.setItemCount(20);

        viewer.setContentProvider(new StereotypePropertyTreeContentProvider());
        viewer.setLabelProvider(new StereotypePropertyTableColumnLabelProvider(this));

        // viewer.setInput(input);
        viewer.addSelectionChangedListener(new StereotypePropertySelectionChangedListener(parent, this));

        return parent;
    }

    /**
     * Input 반환
     * 
     * @return the input
     */
    public Object getInput() {
        return input;
    }

    /**
     * Input 설정
     * 
     * @param input
     *            void
     */
    public void setInput(Object input) {
        this.input = input;
        viewer.setInput(input);

        viewer.refresh();
    }

    /**
     * <ul>
     * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
     * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sheet.viewer</li>
     * <li>설 명 : StereotypePropertySelectionChangedListener</li>
     * <li>작성일 : 2009. 12. 28.</li>
     * <li>작성자 : 최윤석</li>
     * </ul>
     */
    class StereotypePropertySelectionChangedListener implements ISelectionChangedListener {

        /** 부모 컴포지트 */
        private Composite parentComposite;

        /** 부모 */
        private StereotypePropertyComposite parent;

        /**
         * 생성자
         * 
         * @param parentComposite
         * @param parentElement
         */
        public StereotypePropertySelectionChangedListener(Composite parentComposite, StereotypePropertyComposite parent) {
            this.parentComposite = parentComposite;
            this.parent = parent;
        }

        /**
         * 선택 변경시 처리하는 메소드
         * 
         * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
         */
        public void selectionChanged(SelectionChangedEvent event) {
            if (!event.getSelection().isEmpty()) {
                Object property = ((IStructuredSelection) event.getSelection()).getFirstElement();

                if (property instanceof Property) {
                    // Property가 Primitive타입이고
                    if (((Property) property).getType() instanceof PrimitiveType) {
                        // 다중 값이 들어가는 경우
                        if (((Property) property).isMultivalued()) {
                            if (((Property) property).getName()
                                .equals(UICoreConstant.REPORT__STEREOTYPE_RMUSECASE_PROPERTY_NAME_REQUIREMENTID)) {
                                String tempURI = ((Element) parent.getInput()).eResource().getURI().toString();

                                StringBuffer strResourceURI = new StringBuffer(tempURI.substring(0,
                                    tempURI.lastIndexOf(UICoreConstant.PROJECT_CONSTANTS__SLASH
                                        + UMLMessage.LABEL_MODEL)));
                                strResourceURI.append(UICoreConstant.PROJECT_CONSTANTS__SLASH)
                                    .append(UMLMessage.LABEL_RMDATA);
                                strResourceURI.append(UICoreConstant.PROJECT_CONSTANTS__SLASH)
                                    .append(UICoreConstant.PROJECT_CONSTANTS__RMDATA_DEFAULT_MODEL_NAME);

                                URI rmDataURI = URI.createURI(strResourceURI.toString());
                                // Resource rmResource =
                                // DomainRegistry.getUMLDomain().loadRMResource(rmDataURI);
                                Resource rmResource = null;
                                try {
                                    rmResource = DomainModelHandlerUtil.loadRMResource(rmDataURI);
                                    List<RMObject> requirementList = null;

                                    if (rmResource != null) {
                                        RMData rmData = (RMData) rmResource.getContents().get(0);

                                        requirementList = rmData.getRequirementList();

                                        SelectRequirementDialog requirementSelectionDialog = new SelectRequirementDialog(parentComposite.getShell(),
                                            requirementList,
                                            ProfileUtil.getValueListOfProperty((NamedElement) parent.getInput(),
                                                ((Property) property)));

                                        if (requirementSelectionDialog.open() == Window.OK) {
                                            RecordingCommand command = new HandlePropertyCommand(DomainRegistry.getEditingDomain(),
                                                (NamedElement) parent.getInput(),
                                                ((Property) property),
                                                requirementSelectionDialog.getSelectedRequirementList());
                                            DomainUtil.executeCommand(command);
                                        }
                                    }
                                } catch (Exception e) {
                                    MessageDialog.openInformation(parentComposite.getShell(),
                                        UMLMessage.LABEL_NO_REQUIREMENT_INFORMATION,
                                        UMLMessage.MESSAGE_ERROR_NO_IMPORTED_RM_DATA
                                            + "\n" + UMLMessage.MESSAGE_IMPORT_RM_DATA_EXCEL_FILE); //$NON-NLS-1$
                                }
                            } else {
                                InputMultiValueDialog inputMultiValueDialog = new InputMultiValueDialog(parentComposite.getShell(),
                                    (NamedElement) parent.getInput(),
                                    (Property) property,
                                    ((Property) property).getName(),
                                    ProfileUtil.getValueListOfProperty((NamedElement) parent.getInput(),
                                        ((Property) property)));

                                if (inputMultiValueDialog.open() == SWT.OK) {}
                            }
                            // 단일 값이 들어가는 경우
                        } else {
                            InputDialog inputValueDialog = new InputDialog(parentComposite.getShell(),
                                UMLMessage.LABEL_INPUT_VALUE,
                                UMLMessage.MESSAGE_INPUT_VALUE,
                                ProfileUtil.getStringValueOfProperty((NamedElement) parent.getInput(),
                                    ((Property) property),
                                    true),
                                null);

                            if (inputValueDialog.open() == InputDialog.OK) {
                                RecordingCommand command = new HandlePropertyCommand(DomainRegistry.getEditingDomain(),
                                    (NamedElement) parent.getInput(),
                                    ((Property) property),
                                    inputValueDialog.getValue());
                                DomainUtil.executeCommand(command);
                            }
                        }
                        // Property가 Enumeration타입일 경우
                    } else if (((Property) property).getType() instanceof Enumeration) {
                        Object[] enumerationList = ((Enumeration) ((Property) property).getType()).getOwnedLiterals()
                            .toArray();

                        Object selectedItem = ProfileUtil.getObjectValueOfProperty((NamedElement)parent.getInput(), property, true);
                        SelectComboValueDialog selectComboValueDialog = new SelectComboValueDialog(parentComposite.getShell(),
                            ((Property) property).getName(),
                            enumerationList, selectedItem);

                        if (selectComboValueDialog.open() == SelectComboValueDialog.OK) {
                            RecordingCommand command = new HandlePropertyCommand(DomainRegistry.getEditingDomain(),
                                (NamedElement) parent.getInput(),
                                ((Property) property),
                                selectComboValueDialog.getSelectedEnumerationLiteral());
                            DomainUtil.executeCommand(command);
                        }
                        // Property가 Class타입일 경우
                        // TODO yschoi 다양한 UML타입을 받을 수 있도록 변경 필요
                    } else if (((Property) property).getType() instanceof org.eclipse.uml2.uml.Class) {
                        // 다중 값이 들어가는 경우
                        if (((Property) property).isMultivalued()) {
                            InputMultiValueDialog inputMultiValueDialog = new InputMultiValueDialog(parentComposite.getShell(),
                                (NamedElement) parent.getInput(),
                                (Property) property,
                                ((Property) property).getName(),
                                ProfileUtil.getValueListOfProperty((NamedElement) parent.getInput(),
                                    ((Property) property)));

                            if (inputMultiValueDialog.open() == InputMultiValueDialog.OK) {}
                            // 단일 값이 들어가는 경우
                        } else {
                            InputDialog inputValueDialog = new InputDialog(parentComposite.getShell(),
                                UMLMessage.LABEL_INPUT_VALUE,
                                UMLMessage.MESSAGE_INPUT_VALUE,
                                ProfileUtil.getStringValueOfProperty((NamedElement) parent.getInput(),
                                    ((Property) property),
                                    true),
                                null);

                            if (inputValueDialog.open() == InputDialog.OK) {
                                RecordingCommand command = new HandlePropertyCommand(DomainRegistry.getEditingDomain(),
                                    (NamedElement) parent.getInput(),
                                    ((Property) property),
                                    inputValueDialog.getValue());
                                DomainUtil.executeCommand(command);
                            }
                        }
                        // 그 외의 경우
                    } else {
                        InputDialog inputValueDialog = new InputDialog(parentComposite.getShell(),
                            UMLMessage.LABEL_INPUT_VALUE,
                            UMLMessage.MESSAGE_INPUT_VALUE,
                            ProfileUtil.getStringValueOfProperty((NamedElement) parent.getInput(),
                                ((Property) property),
                                true),
                            null);

                        if (inputValueDialog.open() == InputDialog.OK) {
                            RecordingCommand command = new HandlePropertyCommand(DomainRegistry.getEditingDomain(),
                                (NamedElement) parent.getInput(),
                                ((Property) property),
                                inputValueDialog.getValue());
                            DomainUtil.executeCommand(command);
                        }
                    }
                }
            }

            viewer.setInput(input);
            viewer.refresh();
        }
    }

}
