/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.project.wizards;

import java.net.URI;

import nexcore.tool.uml.core.message.UMLMessage;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkingSet;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.WorkingSetGroup;
import org.eclipse.ui.internal.ide.IDEWorkbenchMessages;
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;
import org.eclipse.ui.internal.ide.IIDEHelpContextIds;
import org.eclipse.ui.internal.ide.dialogs.ProjectContentsLocationArea;
import org.eclipse.ui.internal.ide.dialogs.ProjectContentsLocationArea.IErrorMessageReporter;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.wizards</li>
 * <li>설  명 : UMLWizardNewProjectCreationPage</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class UMLWizardNewProjectCreationPage extends WizardPage implements KeyListener, FocusListener {

       // initial value stores
    /**
     * initialProjectFieldValue
     */
    private String initialProjectFieldValue;

    // widgets
    /**
     * projectNameField
     */
    public Text projectNameField;

    /**
     * nameModifyListener
     */
    private Listener nameModifyListener = new Listener() {
        public void handleEvent(Event e) {
            setLocationForSelection();
            boolean valid = validatePage();
            setPageComplete(valid);
                
        }
    };
    
    /** selected Project */
//    public nexcore.platform.foundation.core.Project selectedProject;

    /**
     * locationArea
     */
    private ProjectContentsLocationArea locationArea;

    /**
     * workingSetGroup
     */
    private WorkingSetGroup workingSetGroup;

    /**
     * projectCode
     */
//    public Text projectCode;

    /**
     * description
     */
    public Text description;
    
    /**
     * projectFindBtn
     */
    private Button projectFindBtn;

    // constants
    /**
     * SIZING_TEXT_FIELD_WIDTH
     */
    private static final int SIZING_TEXT_FIELD_WIDTH = 250;

    /**
     * Creates a new project creation wizard page.
     *
     * @param pageName the name of this page
     */
    public UMLWizardNewProjectCreationPage(String pageName) {
        super(pageName);
        setPageComplete(false);
    }

    /**
     * Creates a new project creation wizard page.
     * 
     * @param pageName
     * @param selection
     * @param workingSetTypes
     * 
     * @deprecated default placement of the working set group has been removed.
     *             If you wish to use the working set block please call
     *             {@link #createWorkingSetGroup(Composite, IStructuredSelection, String[])}
     *             in your overridden {@link #createControl(Composite)}
     *             implementation.
     * @since 3.4
     */
    public UMLWizardNewProjectCreationPage(String pageName,
            IStructuredSelection selection, String[] workingSetTypes) {
        this(pageName);
    }

    /** (non-Javadoc)
     * Method declared on IDialogPage.
     */
    public void createControl(Composite parent) {
        Composite composite = new Composite(parent, SWT.NULL);

        initializeDialogUnits(parent);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(composite,
                IIDEHelpContextIds.NEW_PROJECT_WIZARD_PAGE);

        composite.setLayout(new GridLayout());
        composite.setLayoutData(new GridData(GridData.FILL_BOTH));

        createProjectNameGroup(composite);
        locationArea = new ProjectContentsLocationArea(getErrorReporter(), composite);
        if(initialProjectFieldValue != null) {
            locationArea.updateProjectName(initialProjectFieldValue);
        }
        
        // Scale the button based on the rest of the dialog
        setButtonLayoutData(locationArea.getBrowseButton());
        
        setPageComplete(validatePage());
        // Show description on opening
        setErrorMessage(null);
        setMessage(null);
        setControl(composite);
        Dialog.applyDialogFont(composite);
    }
    
    /**
     * Create a working set group for this page. This method can only be called
     * once.
     * 
     * @param composite
     *            the composite in which to create the group
     * @param selection
     *            the current workbench selection
     * @param supportedWorkingSetTypes
     *            an array of working set type IDs that will restrict what types
     *            of working sets can be chosen in this group
     * @return the created group. If this method has been called previously the
     *         original group will be returned.
     * @since 3.4
     */
    public WorkingSetGroup createWorkingSetGroup(Composite composite,
            IStructuredSelection selection, String[] supportedWorkingSetTypes) {
        if (workingSetGroup != null)
            return workingSetGroup;
        workingSetGroup = new WorkingSetGroup(composite, selection,
                supportedWorkingSetTypes);
        return workingSetGroup;
    }
    
    /**
     * Get an error reporter for the receiver.
     * @return IErrorMessageReporter
     */
    private IErrorMessageReporter getErrorReporter() {
        return new IErrorMessageReporter(){
            /* (non-Javadoc)
             * @see org.eclipse.ui.internal.ide.dialogs.ProjectContentsLocationArea.IErrorMessageReporter#reportError(java.lang.String)
             */
            public void reportError(String errorMessage, boolean infoOnly) {
                if (infoOnly) {
                    setMessage(errorMessage, IStatus.INFO);
                    setErrorMessage(null);
                }
                else
                    setErrorMessage(errorMessage);
                boolean valid = errorMessage == null;
                if(valid) {
                    valid = validatePage();
                }
                
                setPageComplete(valid);
            }
        };
    }

    /**
     * Creates the project name specification controls.
     *
     * @param parent the parent composite
     */
    public void createProjectNameGroup(Composite parent) {
        // project specification group
        Composite projectGroup = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.numColumns = 3;
        projectGroup.setLayout(layout);
        projectGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        
        GridData data;
//        createProjectCode(parent, projectGroup);
        createProjectName(parent, projectGroup);
    }

    /**
     * createProjectName
     *  
     * @param parent
     * @param projectGroup void
     */
    private void createProjectName(Composite parent, Composite projectGroup) {
        GridData data;
        // new project label
        Label projectLabel = new Label(projectGroup, SWT.NONE);
        projectLabel.setText(UMLMessage.LABEL_PROJECT_NAME);
        projectLabel.setFont(parent.getFont());

        // new project name entry field
        projectNameField = new Text(projectGroup, SWT.BORDER);
        data = new GridData(GridData.FILL_HORIZONTAL);
        data.widthHint = SIZING_TEXT_FIELD_WIDTH;
        projectNameField.setLayoutData(data);
        projectNameField.setFont(parent.getFont());

        // Set the initial value first before listener
        // to avoid handling an event during the creation.
        if (initialProjectFieldValue != null) {
            projectNameField.setText(initialProjectFieldValue);
        }
        projectNameField.addListener(SWT.Modify, nameModifyListener);
    }

    /**
     * createProjectCode
     *  
     * @param parent
     * @param projectGroup void
     */
//    private void createProjectCode(Composite parent, Composite projectGroup) {
//        Label projectCodelabel = new Label(projectGroup, SWT.NONE);
//        projectCodelabel.setText(UMLMessage.LABEL_PROJECT_CODE_LABEL);//"Project code: ");
//        projectCodelabel.setFont(parent.getFont());
//        
//        this.projectCode = new Text(projectGroup, SWT.BORDER);
//        this.projectCode.setEditable(false);
//        GridData data = new GridData(GridData.FILL_HORIZONTAL);
//        data.grabExcessHorizontalSpace = true;
//        data.widthHint = SIZING_TEXT_FIELD_WIDTH;
//        this.projectCode.setLayoutData(data);
//        this.projectCode.setFont(parent.getFont());
//        
//        projectFindBtn = new Button(projectGroup, SWT.NONE);
//        projectFindBtn.setText(UMLMessage.LABEL_FIND_PROJECT);//"찾기");
//        projectFindBtn.addSelectionListener(new UMLProjectSelectionListener(this));
//        
//        projectFindBtn.setVisible(false);
//        projectCode.setVisible(false);
//        projectCodelabel.setVisible(false);
//    }


    /**
     * Returns the current project location path as entered by 
     * the user, or its anticipated initial value.
     * Note that if the default has been returned the path
     * in a project description used to create a project
     * should not be set.
     *
     * @return the project location path or its anticipated initial value.
     */
    public IPath getLocationPath() {
        return new Path(locationArea.getProjectLocation());
    }
    
    /**
    /**
     * Returns the current project location URI as entered by 
     * the user, or <code>null</code> if a valid project location
     * has not been entered.
     *
     * @return the project location URI, or <code>null</code>
     * @since 3.2
     */
    public URI getLocationURI() {
        return locationArea.getProjectLocationURI();
    }

    /**
     * Creates a project resource handle for the current project name field
     * value. The project handle is created relative to the workspace root.
     * <p>
     * This method does not create the project resource; this is the
     * responsibility of <code>IProject::create</code> invoked by the new
     * project resource wizard.
     * </p>
     * 
     * @return the new project resource handle
     */
    public IProject getProjectHandle() {
        return ResourcesPlugin.getWorkspace().getRoot().getProject(
                getProjectName());
    }

    /**
     * Returns the current project name as entered by the user, or its anticipated
     * initial value.
     *
     * @return the project name, its anticipated initial value, or <code>null</code>
     *   if no project name is known
     */
    public String getProjectName() {
        if (projectNameField == null) {
            return initialProjectFieldValue;
        }

        return getProjectNameFieldValue();
    }

    /**
     * Returns the value of the project name field
     * with leading and trailing spaces removed.
     * 
     * @return the project name in the field
     */
    private String getProjectNameFieldValue() {
        if (projectNameField == null) {
            return ""; //$NON-NLS-1$
        }

        return projectNameField.getText().trim();
    }
    
    /**
     * getProjectCode
     *  
     * @return String
     */
//    public String getProjectCode() {
//        if (projectCode == null) {
//            return ""; //$NON-NLS-1$
//        }
//        return projectCode.getText().trim();
//    }
    
    /**
     * getProjectDescription
     *  
     * @return String
     */
    public String getProjectDescription() {
        if (description == null) {
            return ""; //$NON-NLS-1$
        }
        return description.getText().trim();
    }

    /**
     * Sets the initial project name that this page will use when
     * created. The name is ignored if the createControl(Composite)
     * method has already been called. Leading and trailing spaces
     * in the name are ignored.
     * Providing the name of an existing project will not necessarily 
     * cause the wizard to warn the user.  Callers of this method 
     * should first check if the project name passed already exists 
     * in the workspace.
     * 
     * @param name initial project name for this page
     * 
     * @see IWorkspace#validateName(String, int)
     * 
     */
    public void setInitialProjectName(String name) {
        if (name == null) {
            initialProjectFieldValue = null;
        } else {
            initialProjectFieldValue = name.trim();
            if(locationArea != null) {
                locationArea.updateProjectName(name.trim());
            }
        }
    }

    /**
     * Set the location to the default location if we are set to useDefaults.
     */
    void setLocationForSelection() {
        locationArea.updateProjectName(getProjectNameFieldValue());
    }

  
    /**
     * Returns whether this page's controls currently all contain valid 
     * values.
     *
     * @return <code>true</code> if all controls are valid, and
     *   <code>false</code> if at least one is invalid
     */
    protected boolean validatePage() {
        IWorkspace workspace = IDEWorkbenchPlugin.getPluginWorkspace();

        String projectFieldContents = getProjectNameFieldValue();
        if (projectFieldContents.equals("")) { //$NON-NLS-1$
            setErrorMessage(null);
            setMessage(IDEWorkbenchMessages.WizardNewProjectCreationPage_projectNameEmpty);
            return false;
        }

        IStatus nameStatus = workspace.validateName(projectFieldContents,
                IResource.PROJECT);
        if (!nameStatus.isOK()) {
            setErrorMessage(nameStatus.getMessage());
            return false;
        }

        IProject handle = getProjectHandle();
        if (handle.exists()) {
            setErrorMessage(IDEWorkbenchMessages.WizardNewProjectCreationPage_projectExistsMessage);
            return false;
        }
                
        IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(
                getProjectNameFieldValue());
        locationArea.setExistingProject(project);
        
        String validLocationMessage = locationArea.checkValidLocation();
        if (validLocationMessage != null) { // there is no destination location given
            setErrorMessage(validLocationMessage);
            return false;
        }

        setErrorMessage(null);
        setMessage(null);
        return true;
    }

    /*
     * see @DialogPage.setVisible(boolean)
     */
    /**
     * @see org.eclipse.jface.dialogs.DialogPage#setVisible(boolean)
     */
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            projectNameField.setFocus();
        }
    }

    /**
     * Returns the useDefaults.
     * @return boolean
     */
    public boolean useDefaults() {
        return locationArea.isDefault();
    }

    /**
     * Return the selected working sets, if any. If this page is not configured
     * to interact with working sets this will be an empty array.
     * 
     * @return the selected working sets
     * @since 3.4
     */
    public IWorkingSet[] getSelectedWorkingSets() {
        return workingSetGroup == null ? new IWorkingSet[0] : workingSetGroup
                .getSelectedWorkingSets();
    }

    /**
     * @see org.eclipse.swt.events.FocusListener#focusGained(org.eclipse.swt.events.FocusEvent)
     */
    @Override
    public void focusGained(FocusEvent e) {
        // TODO Auto-generated method stub
        
    }

    /**
     * @see org.eclipse.swt.events.FocusListener#focusLost(org.eclipse.swt.events.FocusEvent)
     */
    @Override
    public void focusLost(FocusEvent e) {
        // TODO Auto-generated method stub
        
    }

    /**
     * @see org.eclipse.swt.events.KeyListener#keyPressed(org.eclipse.swt.events.KeyEvent)
     */
    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    /**
     * @see org.eclipse.swt.events.KeyListener#keyReleased(org.eclipse.swt.events.KeyEvent)
     */
    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
}
