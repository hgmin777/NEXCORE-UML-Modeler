/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.project.editor.section;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.utility.DomainModelHandlerUtil;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.model.projectinformation.ProjectElement;
import nexcore.tool.uml.model.projectinformation.ProjectInformationFactory;
import nexcore.tool.uml.model.projectinformation.ProjectPhaseType;
import nexcore.tool.uml.model.rmdata.RMData;
import nexcore.tool.uml.model.rmdata.RMObject;
import nexcore.tool.uml.ui.core.editor.section.AbstractSection;
import nexcore.tool.uml.ui.project.ProjectExplorerPlugin;
import nexcore.tool.uml.ui.project.editor.ModelMultiPageEditor;
import nexcore.tool.uml.ui.project.editor.command.HandleProjectCommand;
import nexcore.tool.uml.ui.project.editor.dialog.SelectRelatedRmProjectDialog;
import nexcore.tool.uml.ui.project.util.RMDataUtil;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.uml2.uml.Model;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.editor.section</li>
 * <li>설 명 : OverviewProjectInformationSection</li>
 * <li>작성일 : 2010. 1. 14.</li>
 * <li>작성자 : 송주경</li>
 * </ul>
 */
public class OverviewProjectInformationSection extends AbstractSection {

    /** Project */
    private static final String NAME_OF_PROJECT = "Project"; //$NON-NLS-1$

    /** PROJECT_PHASE_ITEMS */
    private static final String[] PROJECT_PHASE_ITEMS = new String[ProjectPhaseType.values().length];

    /** label */
    @SuppressWarnings("unused")
    private Label label;

    /** 프로젝트 코드 텍스트 */
    private Text codeText;

    /** 프로젝트 명 텍스트 */
    private Text nameText;

    /** GridData */
    private GridData gridData;

    /** model */
    private Model model;

    /** projectElemet */
    private ProjectElement projectElement;

    /** phaseCombo */
    private Combo phaseCombo;

    /** indexOfSelectedPhaseComboItem */
    @SuppressWarnings("unused")
    private int indexOfSelectedPhaseComboItem;

    /** RM Data */
    private RMData rmData;

    /**
     * @param page
     * @param parent
     * @param style
     */
    public OverviewProjectInformationSection(FormPage page, Composite parent) {
        super(page, parent, Section.TWISTIE | Section.EXPANDED | Section.DESCRIPTION);

        try {
            this.rmData = RMDataUtil.findRMData(model);
        } catch (Exception e) {
            this.rmData = null;
        }
    }

    /**
     * @see nexcore.tool.uml.ui.core.editor.section.AbstractSection#createClient(org.eclipse.ui.forms.widgets.Section,
     *      org.eclipse.ui.forms.widgets.FormToolkit)
     */
    @Override
    protected void createClient(Section section, FormToolkit toolkit) {
        // initProjectPhaseItems();
        section.setText(UMLMessage.LABEL_RELATED_RM_PROJECT_INFORMATION);
        section.setDescription(UMLMessage.MESSAGE_PROJECT_INFORMATION_OVERVIEW);

        createToolBarButton(section);

        Composite composite = toolkit.createComposite(section);
        composite.setLayout(new GridLayout(2, false));
        gridData = new GridData(GridData.FILL_HORIZONTAL);
        createProjectIdControl(toolkit, composite);
        createProjectNameControl(toolkit, composite);
        // createProjectPhase(toolkit, composite);

        setValueToWidget();
        section.setClient(composite);
    }

    /**
     * 연관된 RM 프로젝트 정보 버튼
     * 
     * @param section
     *            void
     */
    private void createToolBarButton(Section section) {
        ToolBar toolbar = new ToolBar(section, SWT.FLAT | SWT.HORIZONTAL);
        ToolItem addItem = new ToolItem(toolbar, SWT.PUSH);
        addItem.setText(UMLMessage.LABEL_SELECT_THE_RELATED_RM_PROJECT);

        addItem.addSelectionListener(new SelectionAdapter() {
            private Model element;

            private OverviewProjectInformationSection parent;

            /** button 수행시 오류항목 제목 */
            private String errorTitle;

            /** button 수행시 오류항목 메시지 */
            private String errorMessage;

            /** 데이터 검증 boolean 값 */
            private boolean validateOK = true;

            /**
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected(SelectionEvent e) {
                super.widgetSelected(e);

                if (rmData == null) {

                    openErrorDialog(UMLMessage.LABEL_NO_PROJECT_INFORMATION,
                        UMLMessage.MESSAGE_ERROR_NO_IMPORTED_RM_DATA + "\n"
                            + UMLMessage.MESSAGE_IMPORT_RM_DATA_EXCEL_FILE);

                } else {

                    SelectRelatedRmProjectDialog relatedRmSelectionDialog = new SelectRelatedRmProjectDialog(getSection().getShell(),
                        rmData.getProjectList(),
                        codeText.getText());

                    if (relatedRmSelectionDialog.open() == Window.OK) {

                        RMObject project = relatedRmSelectionDialog.getSelectedProject();
                        codeText.setText(project.getRMObjectId());
                        nameText.setText(project.getRMObjectName());
                        RecordingCommand command = new HandleProjectCommand(DomainRegistry.getEditingDomain(),
                            element,
                            projectElement,
                            false);
                        DomainUtil.executeCommand(command);

                    }
                }

            }

            private void openErrorDialog(String labelError, String messageErrorExport) {
                MessageDialog.openInformation(ProjectExplorerPlugin.getShell(), labelError, messageErrorExport);

            }

        });

        section.setTextClient(toolbar);

    }

    /**
     * 
     * void
     */
    public void setValueToWidget() {
        model = ((ModelMultiPageEditor) getPage().getEditor()).getModel();

        ProjectElement project = (ProjectElement) model.getEAnnotation(NAME_OF_PROJECT);

        if (project != null) {
            if (project.getProjectName() == null) {
                nameText.setText(EMPTY_TEXT);
            } else {
                nameText.setText(project.getProjectName());
            }
            if (project.getProjectCode() == null) {
                codeText.setText(EMPTY_TEXT);
            } else {
                codeText.setText(project.getProjectCode());
            }
            // if (project.getProjectPhase().getName() == null) {
            // phaseCombo.setText(EMPTY_TEXT);
            // } else {
            // String projectPhase = project.getProjectPhase().getName();
            // for (int i = 0; i < PROJECT_PHASE_ITEMS.length; i++) {
            // if (PROJECT_PHASE_ITEMS[i].equals(projectPhase)) {
            // indexOfSelectedPhaseComboItem = i;
            // }
            // }
            // phaseCombo.select(indexOfSelectedPhaseComboItem);
            // }
        } else {
            nameText.setText(EMPTY_TEXT);
            codeText.setText(EMPTY_TEXT);
            // phaseCombo.setText(EMPTY_TEXT);
        }
    }

    /**
     * 
     * void
     */
    @SuppressWarnings("unused")
    private void initProjectPhaseItems() {
        int i = 0;
        for (ProjectPhaseType projectPhaseType : ProjectPhaseType.values()) {
            PROJECT_PHASE_ITEMS[i] = projectPhaseType.toString();
            i++;
        }
    }

    /**
     * 
     * 
     * @param toolkit
     * @param composite
     *            void
     */
    @SuppressWarnings("unused")
    private void createProjectPhase(FormToolkit toolkit, Composite composite) {
        label = toolkit.createLabel(composite, UMLMessage.LABEL_PROJECT_STEPS);
        phaseCombo = new Combo(composite, SWT.READ_ONLY);
        phaseCombo.setItems(PROJECT_PHASE_ITEMS);
        phaseCombo.addSelectionListener(new SelectionAdapter() {
            /**
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected(SelectionEvent e) {
                markDirty();
            }
        });
    }

    /**
     * 연관된 RM 프로젝트 ID
     * 
     * @param toolkit
     * @param composite
     *            void
     */
    private void createProjectIdControl(FormToolkit toolkit, Composite composite) {
        label = toolkit.createLabel(composite, UMLMessage.LABEL_RELATED_RM_PROJECT_ID);
        codeText = toolkit.createText(composite, EMPTY_TEXT, SWT.BORDER);
        codeText.setLayoutData(gridData);
        codeText.addModifyListener(new ModifyListener() {
            /**
             * @see org.eclipse.swt.events.ModifyListener#modifyText(org.eclipse.swt.events.ModifyEvent)
             */
            public void modifyText(ModifyEvent e) {
                markDirty();
            }
        });
    }

    /**
     * 연관된 RM 프로젝트 명
     * 
     * @param toolkit
     * @param composite
     *            void
     */
    private void createProjectNameControl(FormToolkit toolkit, Composite composite) {
        label = toolkit.createLabel(composite, UMLMessage.LABEL_RALATED_RM_PROJECT_NAME);
        nameText = toolkit.createText(composite, EMPTY_TEXT, SWT.BORDER);
        nameText.setLayoutData(gridData);
        nameText.addModifyListener(new ModifyListener() {
            /**
             * @see org.eclipse.swt.events.ModifyListener#modifyText(org.eclipse.swt.events.ModifyEvent)
             */
            public void modifyText(ModifyEvent e) {
                markDirty();
            }
        });
    }

    /**
     * @see org.eclipse.ui.forms.AbstractFormPart#commit(boolean)
     */
    public void commit(boolean onSave) {
        projectElement = ProjectInformationFactory.eINSTANCE.createProjectElement();

        projectElement.setSource(NAME_OF_PROJECT);
        projectElement.setProjectName(nameText.getText());
        projectElement.setProjectCode(codeText.getText());
        // projectElement.setProjectPhase(ProjectPhaseType.getByName(phaseCombo.getText()));

        RecordingCommand command = new HandleProjectCommand(DomainRegistry.getEditingDomain(),
            model,
            projectElement,
            true);
        DomainUtil.executeCommand(command);
        DomainModelHandlerUtil.save(model);
        super.commit(onSave);
    }
}
