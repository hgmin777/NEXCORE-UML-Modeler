/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.project.wizards;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;

import nexcore.alm.common.exception.BaseException;
import nexcore.alm.common.ui.util.ALMLogger;
import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.core.util.FileUtil;
import nexcore.tool.uml.core.util.UMLLoginController;
import nexcore.tool.uml.core.util.UMLNexcoreLoginEvent;
import nexcore.tool.uml.manager.utility.DomainModelHandlerUtil;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.model.projectinformation.ProjectElement;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.registry.IConstantImageRegistry;
import nexcore.tool.uml.ui.core.registry.ProjectRegistry.ViewerRegistry;
import nexcore.tool.uml.ui.core.registry.ResourceManager;
import nexcore.tool.uml.ui.core.util.ProjectUtil;
import nexcore.tool.uml.ui.project.ProjectExplorerPlugin;
import nexcore.tool.uml.ui.project.editor.command.HandleProjectCommand;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkingSet;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;
import org.eclipse.uml2.uml.Model;
import org.osgi.framework.Bundle;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.wizards</li>
 * <li>설 명 : UMLProjectWizard</li>
 * <li>작성일 : 2009. 11. 24.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
public class UMLProjectWizard extends BasicNewResourceWizard implements INewWizard, IExecutableExtension {

    /** New Project Creation Wizard Page */
    private UMLProjectWizardPage wizardPage;

    /** Configuration */
    private IConfigurationElement config;

    /** Need to update perspective or not */
    private boolean needToUpdatePerspective = true;

    /** Project to create */
    private IProject project;

    /**
     * UMLProjectWizard
     */
    public UMLProjectWizard() {
        super();
        setDialogSettings(ProjectExplorerPlugin.getDefault().getDialogSettings());
    }

    /**
     * @see org.eclipse.jface.wizard.Wizard#addPages()
     */
    @Override
    public void addPages() {
        wizardPage = new UMLProjectWizardPage("umlProjectPage"); //$NON-NLS-1$
        wizardPage.setTitle(UMLMessage.LABEL_NEW_UML_PROJECT);
        wizardPage.setDescription(UMLMessage.LABEL_CREATE_UML_PROJECT);
        addPage(wizardPage);
    }

    /**
     * @see org.eclipse.jface.wizard.Wizard#canFinish()
     */
    @Override
    public boolean canFinish() {
        if (UMLLoginController.getInstance().getLoginState() != UMLNexcoreLoginEvent.LOG_IN) {
            return false;
        }
        return super.canFinish();
    }

    /**
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        
        try {
            IRunnableWithProgress operation = new WorkspaceModifyOperation() {

                /**
                 * @see org.eclipse.ui.actions.WorkspaceModifyOperation#execute(org.eclipse.core.runtime.IProgressMonitor)
                 */
                @Override
                protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException,
                InterruptedException {
                    
                    createProject(monitor);
                    addNature(project, UICoreConstant.PROJECT_CONSTANTS__NATURE_ID, monitor);

                    // 모델 폴더 생성
                    IFolder modelFolder = project.getFolder(UMLMessage.LABEL_MODEL);
                    if (modelFolder != null && !modelFolder.exists()) {
                        modelFolder.create(true, true, monitor);
                    }

                    if (wizardPage.getCheckprofileButton()) {
                        // 프로파일 폴더 생성
                        IFolder profileFolder = project.getFolder(UMLMessage.LABEL_PROFILE);
                        if (profileFolder != null && !profileFolder.exists()) {
                            profileFolder.create(true, true, monitor);
                        }
                    }

                    if (wizardPage.getCheckTransformationFileButton()) {
                        // 변환 파일 폴더 생성
                        IFolder transformationFileFolder = project.getFolder(UMLMessage.LABEL_TRANSFORMATION_FILE);
                        if (transformationFileFolder != null && !transformationFileFolder.exists()) {
                            transformationFileFolder.create(true, true, monitor);
                        }
                    }

                    if (wizardPage.getChecktemplateButton()) {
                        // 문서템플릿 폴더 생성
                        IFolder documentTemplateFolder = project.getFolder(UMLMessage.LABEL_DOCUMENT_TEMPLATE);
                        if (documentTemplateFolder != null && !documentTemplateFolder.exists()) {
                            documentTemplateFolder.create(true, true, monitor);

                        }

                        // 문서템플릿 생성
                        Bundle bundle = ProjectExplorerPlugin.getDefault().getBundle();
                        Path path = new Path(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING);
                        path = (Path) path.removeLastSegments(1);
                        URL fileUrl = FileLocator.find(bundle, path, null);

                        String oldPath = UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;
                        String newPath = documentTemplateFolder.getLocation().toOSString() + "\\";

                        try {
                            oldPath = FileLocator.toFileURL(fileUrl).getFile() + UICoreConstant.TEMPLATE_PATH;

                            // copyTemplateFile(oldPath, newPath,
                            // UICoreConstant.TEMPLATE_EXCEL_CLASSLIST_XLS);
                            // copyTemplateFile(oldPath, newPath,
                            // UICoreConstant.TEMPLATE_EXCEL_PACKAGELIST_XLS);
                            // copyTemplateFile(oldPath, newPath,
                            // UICoreConstant.TEMPLATE_EXCEL_RM_USECASE_TRACE_MATRIX_XLS);
                            // copyTemplateFile(oldPath, newPath,
                            // UICoreConstant.TEMPLATE_EXCEL_USECASELIST_XLS);
                            copyTemplateFile(oldPath, newPath, UICoreConstant.TEMPLATE_XML_CLASS_DEFINITION_XML);
                            copyTemplateFile(oldPath, newPath, UICoreConstant.TEMPLATE_XML_USECASE_ARCHITECTURE_XML);
                            copyTemplateFile(oldPath, newPath, UICoreConstant.TEMPLATE_XML_USECASE_DEFINITION_XML);
                            copyTemplateFile(oldPath, newPath, UICoreConstant.TEMPLATE_XML_USECASE_ANALYSIS_XML);
                            copyTemplateFile(oldPath, newPath, UICoreConstant.TEMPLATE_XML_CHEMA_PATH);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        for (IResource folder : project.members()) {
                            if (folder instanceof IFolder) {
                                ((IFolder) folder).refreshLocal(IResource.DEPTH_INFINITE, monitor);
                            }
                        }

                    }

                    if (wizardPage.getCheckrmDataButton()) {
                        // RM 데이터 폴더 생성
                        IFolder rmDataFolder = project.getFolder(UMLMessage.LABEL_RMDATA);
                        if (rmDataFolder != null && !rmDataFolder.exists()) {
                            rmDataFolder.create(true, true, monitor);
                        }
                    }

                    // 모델 생성
                    if (wizardPage.createModel()) {
                        String modeName = wizardPage.getModelName();
                        String filename = new StringBuffer().append(modelFolder.getFullPath())
                            .append(UICoreConstant.PROJECT_CONSTANTS__SLASH)
                            .append(ProjectUtil.getUniqueFileName(project.getFullPath(),
                                modeName,
                                UICoreConstant.PROJECT_CONSTANTS__FILE_EXTENSION))
                            .toString();

                        try {
                            Resource resource = ProjectUtil.createModel(modeName, URI.createURI(filename), monitor);
                            
                            ResourceManager.getInstance().setActivation(resource.getURI(), true);
                            Model umlModel = (Model) resource.getContents().get(0);
                            // ProjectElement에 uml 버전 추가 후 모델에 입력
                            ProjectElement projectInfo = ProjectUtil.createProjectInfo();

                            RecordingCommand command = new HandleProjectCommand(DomainRegistry.getEditingDomain(),
                                umlModel,
                                projectInfo,
                                true);
                            DomainUtil.executeCommand(command);
                            DomainModelHandlerUtil.save(umlModel);
                        } catch (Exception e) {
                            ALMLogger.getLog(ProjectExplorerPlugin.PLUGIN_ID).error(e.getMessage(), e);
                        }
                    }
                    
                    // WorkingSet 설정
                    IWorkingSet[] workingSets = wizardPage.getSelectedWorkingSets();
                    getWorkbench().getWorkingSetManager().addToWorkingSets(project, workingSets);
                }

                private void copyTemplateFile(String oldPath, String newPath, String fileName) throws BaseException {
                    File old_excel_classList = new File(oldPath + fileName);

                    FileUtil.copy(old_excel_classList, newPath, fileName, true);
                }

            };
            getContainer().run(false, true, operation);

        } catch (InvocationTargetException e) {
            Throwable realException = e.getTargetException();
            MessageDialog.openError(getShell(), UMLMessage.LABEL_ERROR, realException.getMessage());
            return false;
        } catch (InterruptedException e) {
            rollback();
        }

        if (project == null) {
            return false;
        }


        if (needToUpdatePerspective) {
            BasicNewProjectResourceWizard.updatePerspective(config);
        }

        selectAndReveal(project);
        if (ViewerRegistry.getViewer() != null)
            ViewerRegistry.getViewer().refresh();
        return true;

    }

    /**
     * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
     *      org.eclipse.jface.viewers.IStructuredSelection)
     */
    public void init(IWorkbench workbench, IStructuredSelection selection) {
        super.init(workbench, selection);
        setNeedsProgressMonitor(true);
        setWindowTitle(UMLMessage.LABEL_NEW_UML_PROJECT);
        ImageDescriptor smallImage = UiCorePlugin.getImageDescriptor(IConstantImageRegistry.ICONNAME_PROJECT_WIZARD);

        // ImageDescriptor.createFromImage(IConstantImageRegistry.eInstance.getSmallIcon(IConstantImageRegistry.ICONNAME_PROJECT_WIZARD));
        setDefaultPageImageDescriptor(smallImage);
    }

    /**
     * when the wizard fails, deletes the created project.
     */
    protected void rollback() {
        if (project == null) {
            return;
        }

        try {
            if (project.exists()) {
                project.delete(true, null);
            }
            project = null;
        } catch (CoreException e) {
            Log.warn(e.getMessage());
        }
    }

    /**
     * Add project nature for Nexcore UML Modeler.
     * 
     * @param project
     * @param natureId
     * @param monitor
     * @throws CoreException void
     */
    private void addNature(IProject project, String natureId, IProgressMonitor monitor) throws CoreException {
        monitor.beginTask(null, 1);
        try {
            if (!project.hasNature(natureId)) {
                IProjectDescription description = project.getDescription();

                String[] prevNatures = description.getNatureIds();
                String[] newNatures = new String[prevNatures.length + 1];
                System.arraycopy(prevNatures, 0, newNatures, 0, prevNatures.length);
                newNatures[prevNatures.length] = natureId;
                description.setNatureIds(newNatures);

                project.setDescription(description, monitor);

            } else {
                monitor.worked(1);
            }
        } finally {
            monitor.done();
        }

    }

    /**
     * Creates new project.
     * 
     * @param monitor
     * @return
     * @throws CoreException
     *             IProject
     */
    private IProject createProject(IProgressMonitor monitor) throws CoreException {

        if (project != null)
            return project;

        // get a project handle
        final IProject newProjectHandle = wizardPage.getProjectHandle();

        // get a project descriptor
        java.net.URI location = null;
        if (!wizardPage.useDefaults())
            location = wizardPage.getLocationURI();

        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        final IProjectDescription description = workspace.newProjectDescription(newProjectHandle.getName());
        description.setLocationURI(location);

        // create project
        newProjectHandle.create(description, monitor);

        if (!newProjectHandle.isOpen()) {
            newProjectHandle.open(null);
        }

        project = newProjectHandle;

        return project;
    }

    /**
     * @see org.eclipse.core.runtime.IExecutableExtension#setInitializationData(org.eclipse.core.runtime.IConfigurationElement,
     *      java.lang.String, java.lang.Object)
     */
    public void setInitializationData(IConfigurationElement configuration, String propertyName, Object data)
    throws CoreException {
        config = configuration;
    }

}
