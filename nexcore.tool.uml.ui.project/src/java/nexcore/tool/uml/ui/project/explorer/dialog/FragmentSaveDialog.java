/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.project.explorer.dialog;

import java.util.Iterator;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.core.util.StringUtil;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.util.ProjectUtil;
import nexcore.tool.uml.ui.project.ProjectExplorerPlugin;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.ide.IDEInternalWorkbenchImages;
import org.eclipse.ui.internal.ide.IDEWorkbenchMessages;
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;
import org.eclipse.ui.internal.ide.IIDEHelpContextIds;
import org.eclipse.uml2.uml.NamedElement;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.dialog</li>
 * <li>설  명 : FragmentSaveDialog</li>
 * <li>작성일 : 2010. 6. 3.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
public class FragmentSaveDialog extends TitleAreaDialog {

    /**
     * DIALOG_SETTINGS_SECTION
     */
    private static final String DIALOG_SETTINGS_SECTION = "SaveAsDialogSettings"; //$NON-NLS-1$

    /**
     * FRAGMENT
     */
    public static final int FRAGMENT = 1;

    /**
     * MODEL_FRAGMENT
     */
    public static final int MODEL_FRAGMENT = 2;

    /**
     * dialogType
     */
    private int dialogType; // FRAGMENT = 1, MODEL_FRAGMENT = 2

    /**
     * title
     */
    private String title;

    /**
     * message
     */
    private String message;

    /**
     * text
     */
    private String text;

    // private String projectName;

    /**
     * path
     */
    private String path;

    /**
     * eObj
     */
    private EObject eObj;

    /**
     * originalFile
     */
    private IFile originalFile = null;

    /**
     * originalName
     */
    private String originalName = null;

    /**
     * result
     */
    private IPath result;

    // widgets
    /**
     * resourceGroup
     */
    private ContainerGroup resourceGroup;

    /**
     * okButton
     */
    private Button okButton;

    /**
     * Image for title area
     */
    private Image dlgTitleImage = null;

    /**
     * Creates a new Save As dialog for no specific file.
     * 
     * @param parentShell
     *            the parent shell
     */
    public FragmentSaveDialog(Shell parentShell, EObject eobject, int flag) {
        super(parentShell);
        setShellStyle(getShellStyle() | SWT.RESIZE);
        eObj = eobject;
        dialogType = flag;
        if (flag == 1) {
            // Fragment
            title = UMLMessage.LABEL_FILE_FRAGMENT_SAVE;
            text = title;
            message = UMLMessage.MESSAGE_FRAGMENT_SAVEAS;
            
            String fileName = StringUtil.getProperFileName(((NamedElement) eObj).getName(),
                UICoreConstant.PROJECT_CONSTANTS__UNDER_BAR)
                + UICoreConstant.PROJECT_CONSTANTS__DOT + UICoreConstant.PROJECT_CONSTANTS__FRAGMENT_EXTENSION;


            // folderPath = "Test0517/모델/". "{프로젝트명}/{리소스 파일이 있는 폴더명}/"
            String folderPath = eObj.eResource().getURI().trimSegments(1).toString().substring(1);
            path = folderPath
                + UICoreConstant.PROJECT_CONSTANTS__SLASH
                + ProjectUtil.getUniqueFileName(Path.fromOSString(folderPath),
                    fileName,
                    UICoreConstant.PROJECT_CONSTANTS__FRAGMENT_EXTENSION);
        }
    }

    /*
     * (non-Javadoc) Method declared in Window.
     */
    /**
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText(text);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(shell, IIDEHelpContextIds.SAVE_AS_DIALOG);

        IPath path2 = Path.fromOSString(path);
        IFile file;
        file = ResourcesPlugin.getWorkspace().getRoot().getFile(path2);

        setOriginalFile(file);
    }

    /*
     * (non-Javadoc) Method declared in Window.
     */
    /**
     * @see org.eclipse.jface.dialogs.TitleAreaDialog#createContents(org.eclipse.swt.widgets.Composite)
     */
    protected Control createContents(Composite parent) {

        Control contents = super.createContents(parent);

        initializeControls();
        resourceGroup.setFocus();
        setTitle(title);
        dlgTitleImage = IDEInternalWorkbenchImages.getImageDescriptor(IDEInternalWorkbenchImages.IMG_DLGBAN_SAVEAS_DLG)
            .createImage();
        setTitleImage(dlgTitleImage);
        setMessage(message);

        return contents;
    }

    /**
     * The <code>SaveAsDialog</code> implementation of this <code>Window</code>
     * method disposes of the banner image when the dialog is closed.
     */
    public boolean close() {
        if (dlgTitleImage != null) {
            dlgTitleImage.dispose();
        }
        return super.close();
    }

    /*
     * (non-Javadoc) Method declared on Dialog.
     */
    /**
     * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
     */
    protected void createButtonsForButtonBar(Composite parent) {
        okButton = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
        createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
    }

    /*
     * (non-Javadoc) Method declared on Dialog.
     */
    /**
     * @see org.eclipse.jface.dialogs.TitleAreaDialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    protected Control createDialogArea(Composite parent) {
        // top level composite
        Composite parentComposite = (Composite) super.createDialogArea(parent);

        // create a composite with standard margins and spacing
        Composite composite = new Composite(parentComposite, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.marginHeight = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
        layout.marginWidth = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
        layout.verticalSpacing = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
        layout.horizontalSpacing = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
        composite.setLayout(layout);
        composite.setLayoutData(new GridData(GridData.FILL_BOTH));
        composite.setFont(parentComposite.getFont());

        Listener listener = new Listener() {
            public void handleEvent(Event event) {
                setDialogComplete(validatePage());
            }
        };

        resourceGroup = new ContainerGroup(composite, dialogType);
        resourceGroup.setAllowExistingResources(true);

        return parentComposite;
    }

    /**
     * Returns the full path entered by the user.
     * <p>
     * Note that the file and container might not exist and would need to be
     * created. See the <code>IFile.create</code> method and the
     * <code>ContainerGenerator</code> class.
     * </p>
     * 
     * @return the path, or <code>null</code> if Cancel was pressed
     */
    public IPath getResult() {
        return result;
    }

    /**
     * Initializes the controls of this dialog.
     */
    private void initializeControls() {
        if (originalFile != null) {
            resourceGroup.setContainerFullPath(originalFile.getParent().getFullPath());
            resourceGroup.setResource(originalFile.getName());
        } else if (originalName != null) {
            resourceGroup.setResource(originalName);
        }
        setDialogComplete(validatePage());
    }

    /*
     * (non-Javadoc) Method declared on Dialog.
     */
    /**
     * @see org.eclipse.jface.dialogs.Dialog#okPressed()
     */
    protected void okPressed() {
        // Get new path.
        IPath path = resourceGroup.getContainerFullPath().append(resourceGroup.getResource());

        // StringUtil.getProperFileName(resourceGroup.getResource(),
        // UICoreConstant.PROJECT_CONSTANTS__UNDER_BAR)
        // If the user does not supply a file extension and if the save
        // as dialog was provided a default file name append the extension
        // of the default filename to the new name
        if (path.getFileExtension() == null) {
            path = path.addFileExtension(UICoreConstant.PROJECT_CONSTANTS__FRAGMENT_EXTENSION);
        } else if (!UICoreConstant.PROJECT_CONSTANTS__FRAGMENT_EXTENSION.equals(path.getFileExtension())) {
            MessageDialog.openError(ProjectExplorerPlugin.getShell(),
                UMLMessage.LABEL_FILE_FRAGMENTATION,
                UMLMessage.MESSAGE_FRAGMENT_EXTENSION_CONSTRAINT);
            return;
        }

        // 특수문자를 '_'로 replace한 파일명 생성
        String filename = resourceGroup.getResource();
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IStatus status = workspace.validateName(filename, IResource.FILE);
        
        if (!status.isOK()) {
            setErrorMessage(status.getMessage());
            return;
        }
        filename = filename.substring(0, filename.length() - path.getFileExtension().length() - 1);
        filename = StringUtil.getProperFileName(filename, UICoreConstant.PROJECT_CONSTANTS__UNDER_BAR)
            + UICoreConstant.PROJECT_CONSTANTS__DOT + path.getFileExtension();
        path = resourceGroup.getContainerFullPath().append(filename);
        
        
        ResourceSet rs = DomainRegistry.getEditingDomain().getResourceSet();
        Resource resource;
        IFile file;
        originalFile = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
        String extension = UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;
        for (Iterator<Resource> it = rs.getResources().iterator(); it.hasNext();) {
            resource = (Resource) it.next();
            extension = resource.getURI().fileExtension();
            if (!UICoreConstant.PROJECT_CONSTANTS__FRAGMENT_EXTENSION.equals(extension)) {
                continue;
            }
            if (resource.isLoaded()) {
                file = WorkspaceSynchronizer.getFile(resource);
                if (file.getFullPath().toString().equals(originalFile.getFullPath().toString())) {
                    MessageDialog.openError(ProjectExplorerPlugin.getShell(),
                        UMLMessage.LABEL_FILE_FRAGMENTATION,
                        UMLMessage.MESSAGE_FRAGMENT_SAMENAME);
                    return;
                }
            }
        }

        // Store path and close.
        result = path;
        close();
    }

    /**
     * Sets the completion state of this dialog and adjusts the enable state of
     * the Ok button accordingly.
     * 
     * @param value
     *            <code>true</code> if this dialog is compelete, and
     *            <code>false</code> otherwise
     */
    protected void setDialogComplete(boolean value) {
        okButton.setEnabled(value);
    }

    /**
     * Sets the original file to use.
     * 
     * @param originalFile
     *            the original file
     */
    public void setOriginalFile(IFile originalFile) {
        this.originalFile = originalFile;
    }

    /**
     * Set the original file name to use. Used instead of
     * <code>setOriginalFile</code> when the original resource is not an IFile.
     * Must be called before <code>create</code>.
     * 
     * @param originalName
     *            default file name
     */
    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    /**
     * Returns whether this page's visual components all contain valid values.
     * 
     * @return <code>true</code> if valid, and <code>false</code> otherwise
     */
    private boolean validatePage() {
        if (!resourceGroup.areAllValuesValid()) {
            if (!resourceGroup.getResource().equals(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING)) {
                setErrorMessage(resourceGroup.getProblemMessage());
            } else {
                setErrorMessage(null);
            }
            return false;
        }

        String resourceName = resourceGroup.getResource();
        IWorkspace workspace = ResourcesPlugin.getWorkspace();

        // Do not allow a closed project to be selected
        IPath fullPath = resourceGroup.getContainerFullPath();
        if (fullPath != null) {
            String projectName = fullPath.segment(0);
            IStatus isValidProjectName = workspace.validateName(projectName, IResource.PROJECT);
            if (isValidProjectName.isOK()) {
                IProject project = workspace.getRoot().getProject(projectName);
                if (!project.isOpen()) {
                    setErrorMessage(IDEWorkbenchMessages.SaveAsDialog_closedProjectMessage);
                    return false;
                }
            }
        }

        IStatus result = workspace.validateName(resourceName, IResource.FILE);
        if (!result.isOK()) {
            setErrorMessage(result.getMessage());
            return false;
        }

        setErrorMessage(null);
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.window.Dialog#getDialogBoundsSettings()
     * 
     * @since 3.2
     */
    /**
     * @see org.eclipse.jface.dialogs.Dialog#getDialogBoundsSettings()
     */
    protected IDialogSettings getDialogBoundsSettings() {
        IDialogSettings settings = IDEWorkbenchPlugin.getDefault().getDialogSettings();
        IDialogSettings section = settings.getSection(DIALOG_SETTINGS_SECTION);
        if (section == null) {
            section = settings.addNewSection(DIALOG_SETTINGS_SECTION);
        }
        return section;
    }

}
