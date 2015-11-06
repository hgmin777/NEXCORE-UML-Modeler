/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.project.wizards;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.core.util.UMLLoginController;
import nexcore.tool.uml.core.util.UMLNexcoreLoginEvent;
import nexcore.tool.uml.ui.core.UICoreConstant;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.wizards</li>
 * <li>설 명 : UMLProjectWizardPage</li>
 * <li>작성일 : 2009. 11. 27.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
public class UMLProjectWizardPage extends UMLWizardNewProjectCreationPage implements KeyListener, FocusListener {

    /**
     * @see org.eclipse.ui.dialogs.WizardNewProjectCreationPage#validatePage()
     */
    @Override
    protected boolean validatePage() {

        String projectFieldContents = getProjectName();
        if (UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING.equals(projectFieldContents)) {
            setErrorMessage(null);
            setMessage(UMLMessage.MESSAGE_PROJECT_NAME_EMPTY);
            return false;
        }

        org.eclipse.core.resources.IProject handle = getProjectHandle();
        if (handle.exists()) {
            setErrorMessage(UMLMessage.MESSAGE_PROJECT_NAME_EXIST);
            return false;
        }

        if (!super.validatePage()) {
            return false;
        }
        boolean result = false;
        if (modelBtn.getSelection()) {
            if (0 == this.modelName.getText().length()) {
                result = false;
            } else {
                result = true;
            }
        } else {
            result = true;
        }
        if (!result) {
            this.setErrorMessage(UMLMessage.MESSAGE_PROJECT_WIZARD_ERROR);
        }
        this.setPageComplete(result);
        return result;
    }

    /** Check button to determine to create new model file. */
    private Button modelBtn;

    /** Project Name Text Input */
    private Text modelName;
    
    /** Project Code Text Input */
    private Text projectCode;

    /** Find Project Code Button */
    private Button projectFindBtn;

    // /** KEYCODE_ENTER */
    //    private static final int KEYCODE_ENTER = '\r'; //$NON-NLS-1$
    //
    // /** KEYCODE_ENTER2 */
    // private static final int KEYCODE_ENTER2 = 16777296;

    /**
     * UMLProjectWizardPage
     * @param pageName
     */
    public UMLProjectWizardPage(String pageName) {
        super(pageName);
    }
    
    // constants
    /**
     * SIZING_TEXT_FIELD_WIDTH
     */
    private static final int SIZING_TEXT_FIELD_WIDTH = 250;

    /**
     * @see org.eclipse.ui.dialogs.WizardNewProjectCreationPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(Composite parent) {
        
        parent.setSize(DEFAULT_SIZE);
        
        Composite composite = new Composite(parent, SWT.NONE);
        GridLayout compositeGridLayout = new GridLayout();
        compositeGridLayout.numColumns = 1;
        composite.setLayout(compositeGridLayout);
        
        super.createControl(composite);
        
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;

        Composite modelComposite = new Composite(composite, SWT.NONE);
        GridData gdModelComposite = new GridData(GridData.FILL_BOTH);
        modelComposite.setLayoutData(gdModelComposite);
        modelComposite.setLayout(gridLayout);

        GridData gdModelBtn = new GridData(GridData.FILL_BOTH);
        gdModelBtn.horizontalSpan = 2;
        modelBtn = new Button(modelComposite, SWT.CHECK);
        modelBtn.setText(UMLMessage.LABEL_CREATE_UML_MODEL);
        modelBtn.setSelection(true);
        modelBtn.setLayoutData(gdModelBtn);
        modelBtn.addSelectionListener(new SelectionListener() {

            /**
             * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetDefaultSelected(SelectionEvent e) {
            }

            /**
             * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            public void widgetSelected(SelectionEvent e) {
                getContainer().updateButtons();
                modelName.setEnabled(modelBtn.getSelection());
                validatePage();
            }

        });

        CLabel label = new CLabel(modelComposite, SWT.NONE);
        label.setText(UMLMessage.LABEL_CREATE_UML_MODEL_NAME + UICoreConstant.PROJECT_CONSTANTS__COLON);
        GridData gdLabel = new GridData(GridData.BEGINNING);
        label.setLayoutData(gdLabel);

        this.modelName = new Text(modelComposite, SWT.SINGLE | SWT.BORDER);
        this.modelName.setText(UMLMessage.LABEL_CREATE_UML_MODEL_DEFAULTNAME);
        GridData gdModelName = new GridData(GridData.FILL_HORIZONTAL);
        gdModelName.horizontalSpan = 2;
        this.modelName.setLayoutData(gdModelName);
        this.modelName.addKeyListener(this);
        this.modelName.addFocusListener(this);

        Group group = new Group(modelComposite, SWT.NONE);
        GridData gd = new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL);
        gd.horizontalSpan = 2;
        group.setLayoutData(gd);
        GridLayout gLayout = new GridLayout(2, false);
        group.setLayout(gLayout);

        // this.glossaryButton = new Button(group, SWT.CHECK);
        // this.glossaryButton.setSelection(true);
        // this.glossaryButton.setText(UMLMessage.LABEL_CREATE_GLOSSARY_FOLDER);

        this.profileButton = new Button(group, SWT.CHECK);
        this.profileButton.setSelection(false);
        this.profileButton.setText(UMLMessage.LABEL_CREATE_PROFILE_FOLDER);

        this.transformationFileButton = new Button(group, SWT.CHECK);
        this.transformationFileButton.setSelection(false);
        this.transformationFileButton.setText(UMLMessage.LABEL_CREATE_TRANSFORMATION_FILE);

        this.rmDataButton = new Button(group, SWT.CHECK);
        this.rmDataButton.setSelection(false);
        this.rmDataButton.setText(UMLMessage.LABEL_CREATE_RMDATA_FOLDER);

        this.templateButton = new Button(group, SWT.CHECK);
        this.templateButton.setSelection(false);
        this.templateButton.setText(UMLMessage.LABEL_CREATE_TEMPLATE_FOLDER);
        
        group.setVisible(false);
        
        Label projectDescription = new Label(modelComposite, SWT.NONE);
        projectDescription.setText(UMLMessage.LABEL_PROJECT_DESCRIPTION);//"Project description: ");
        projectDescription.setFont(parent.getFont());
        
        description = new Text(modelComposite, SWT.MULTI | SWT.BORDER | SWT.V_SCROLL);
        description.addKeyListener(this);
        description.addFocusListener(this);
        GridData data = new GridData(GridData.FILL_HORIZONTAL);
        data.heightHint = 100;
        data.grabExcessHorizontalSpace = true;
        data.widthHint = SIZING_TEXT_FIELD_WIDTH;
        data.horizontalSpan = 2;
        description.setLayoutData(data);
        
        createWorkingSetGroup((Composite) getControl(), null, new String[] { "org.eclipse.jdt.ui.JavaWorkingSetPage", //$NON-NLS-1$
            "org.eclipse.pde.ui.pluginWorkingSet", "org.eclipse.ui.resourceWorkingSetPage" }); //$NON-NLS-1$ //$NON-NLS-2$
    }

    // private Button glossaryButton;
    /**
     * profileButton
     */
    private Button profileButton;

    /**
     * rmDataButton
     */
    private Button rmDataButton;

    /**
     * templateButton
     */
    private Button templateButton;

    /**
     * transformationFileButton
     */
    private Button transformationFileButton;

    /**
     * DEFAULT_SIZE
     */
    private Point DEFAULT_SIZE = new Point(440, 10);

    // public boolean getCheckGlossaryButton(){
    // return glossaryButton.getSelection();
    // }
    /**
     * getCheckprofileButton
     *  
     * @return boolean
     */
    public boolean getCheckprofileButton() {
        return profileButton.getSelection();
    }

    /**
     * getCheckrmDataButton
     *  
     * @return boolean
     */
    public boolean getCheckrmDataButton() {
        return rmDataButton.getSelection();
    }

    /**
     * getChecktemplateButton
     *  
     * @return boolean
     */
    public boolean getChecktemplateButton() {
        return templateButton.getSelection();
    }

    /**
     * getCheckTransformationFileButton
     *  
     * @return boolean
     */
    public boolean getCheckTransformationFileButton() {
        return transformationFileButton.getSelection();
    }

    /**
     * @return whether you want to create model with project.
     */
    public boolean createModel() {
        return modelBtn.getSelection();
    }

    /**
     * getModelName
     *  
     * @return String
     */
    public String getModelName() {
        return this.modelName.getText();
    }

    /**
     * @see org.eclipse.swt.events.KeyListener#keyPressed(org.eclipse.swt.events.KeyEvent)
     */
    public void keyPressed(KeyEvent e) {
    }

    /**
     * @see org.eclipse.swt.events.KeyListener#keyReleased(org.eclipse.swt.events.KeyEvent)
     */
    public void keyReleased(KeyEvent e) {
        this.validatePage();
    }

    /**
     * @see org.eclipse.swt.events.FocusListener#focusGained(org.eclipse.swt.events.FocusEvent)
     */
    public void focusGained(FocusEvent e) {
        // this.validatePage();
    }

    /**
     * @see org.eclipse.swt.events.FocusListener#focusLost(org.eclipse.swt.events.FocusEvent)
     */
    public void focusLost(FocusEvent e) {
        this.validatePage();
    }
    
    /**
     * getSelectedProject
     *  
     * @return Project
     */
//    public Project getSelectedProject() {
//        return selectedProject;
//    }
}

