/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.project.wizards;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.core.util.UMLLoginController;
import nexcore.tool.uml.core.util.UMLNexcoreLoginEvent;
import nexcore.tool.uml.manager.utility.DomainModelHandlerUtil;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.model.projectinformation.ProjectElement;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.registry.IConstantImageRegistry;
import nexcore.tool.uml.ui.core.registry.ResourceManager;
import nexcore.tool.uml.ui.core.util.ProjectUtil;
import nexcore.tool.uml.ui.project.ProjectExplorerPlugin;
import nexcore.tool.uml.ui.project.editor.command.HandleProfileCommand;
import nexcore.tool.uml.ui.project.editor.command.HandleProjectCommand;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.ProfileApplication;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.wizards</li>
 * <li>설 명 : UMLModelWizard</li>
 * <li>작성일 : 2009. 11. 26.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
public class UMLModelWizard extends Wizard implements INewWizard {

    /** Workbench */
    private IWorkbench workbench;

    /** StructuredSelection */
    protected IStructuredSelection selection;

    /** UML Model creation wizard page */
    protected UMLModelWizardPage modelFilePage;
    
    /**
     * profilePage
     */
    protected ApplyProfileWizardPage profilePage;

    /** resource that will create */
    protected Resource resource;

    /**
     * getWorkbench
     *  
     * @return IWorkbench
     */
    public IWorkbench getWorkbench() {
        return workbench;
    }

    /**
     * getSelection
     *  
     * @return IStructuredSelection
     */
    public IStructuredSelection getSelection() {
        return selection;
    }

    /**
     * getResource
     *  
     * @return Resource
     */
    public final Resource getResource() {
        return resource;
    }

    /**
     * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
     *      org.eclipse.jface.viewers.IStructuredSelection)
     */
    public void init(IWorkbench workbench, IStructuredSelection selection) {
        this.workbench = workbench;
        this.selection = selection;
        setWindowTitle(UMLMessage.LABEL_NEW_UML_MODEL);
        // setDefaultPageImageDescriptor(null);
        setNeedsProgressMonitor(true);
        ImageDescriptor smallImage = UiCorePlugin.getImageDescriptor(IConstantImageRegistry.ICONNAME_MODEL_WIZARD);
        setDefaultPageImageDescriptor(smallImage);
    }

    /**
     * @see org.eclipse.jface.wizard.Wizard#addPages()
     */
    public void addPages() {
        modelFilePage = new UMLModelWizardPage(UMLMessage.LABEL_UML_MODEL_FILE,
            getSelection(),
            UICoreConstant.PROJECT_CONSTANTS__FILE_EXTENSION); //$NON-NLS-1$

        addPage(modelFilePage);

        profilePage = new ApplyProfileWizardPage(UMLMessage.LABEL_UML_MODEL_FILE); //$NON-NLS-1$

        addPage(profilePage);
    }
    
    /**
     * @see org.eclipse.jface.wizard.Wizard#getNextPage(org.eclipse.jface.wizard.IWizardPage)
     */
    @Override
    public IWizardPage getNextPage(IWizardPage page) {
        if( page instanceof UMLModelWizardPage ) {
            profilePage.setUri(modelFilePage.getURI());
            return profilePage;
        }
        return super.getNextPage(page);
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
    public boolean performFinish() {
        String temp = modelFilePage.getFileName();
        StringBuilder builder = new StringBuilder();
        builder.append(temp);
        final String strFileName = builder.substring(0, temp.length()
            - UICoreConstant.PROJECT_CONSTANTS__FILE_EXTENSION.length() - 1);

        IRunnableWithProgress op = new WorkspaceModifyOperation(null) {

            /**
             * @see org.eclipse.ui.actions.WorkspaceModifyOperation#execute(org.eclipse.core.runtime.IProgressMonitor)
             */
            protected void execute(IProgressMonitor monitor) throws CoreException, InterruptedException {

                try {
                    resource = ProjectUtil.createModel(strFileName, modelFilePage.getURI(), monitor);
                    ResourceManager.getInstance().setActivation(resource.getURI(), true);
                } catch (Exception e) {
                    resource = null;
                    IStatus status = new Status(Status.ERROR, ProjectExplorerPlugin.PLUGIN_ID, Status.ERROR, e.getMessage(), e);
                    throw new CoreException(status);
                }
                
                Model umlModel = null;
                umlModel = (Model) resource.getContents().get(0);

                // ProjectElement에 uml 버전 추가 후 모델에 입력
                ProjectElement projectInfo = ProjectUtil.createProjectInfo();

                RecordingCommand command = new HandleProjectCommand(DomainRegistry.getEditingDomain(),
                    umlModel,
                    projectInfo,
                    true);
                DomainUtil.executeCommand(command);
                
                applyProfileList(umlModel);
                
                DomainModelHandlerUtil.save(umlModel);

                // Diagram diagram =
                // UMLDiagramFactory.eINSTANCE.createDiagram();
                // diagram.setId(UUID.randomUUID().toString());
                // diagram.setType(diagramType);
                // diagram.setName(diagramName);
                // diagram.setParent(parent);
                // eAnnotation.getContents().add(diagram);
                // return diagram;

            }
        };
        try {
            getContainer().run(false, true, op);
        } catch (InterruptedException e) {
            return false;
        } catch (InvocationTargetException e) {
            if (e.getTargetException() instanceof CoreException) {
                ErrorDialog.openError(getContainer().getShell(),
                    UMLMessage.MESSAGE_CREATION_PROBLEM,
                    null,
                    ((CoreException) e.getTargetException()).getStatus()); //$NON-NLS-1$
            } else {
                Log.error(UMLMessage.MESSAGE_ERROR_CREATING_UML_MODEL);
            }
            return false;
        } finally {
            //ProjectUtil.refreshWholeExplorer();
        }
        return resource != null;
    }

    /**
     * applyProfileList
     *  
     * @param model void
     */
    private void applyProfileList(Model model) {

        CompoundCommand commands = new CompoundCommand();
        RecordingCommand command = null;

        List<Profile> appliedProfileList = new ArrayList<Profile>();
        List<Profile> displayedProfileList = getApplyingProfileList();
        for(ProfileApplication pApp : model.getAllProfileApplications()){
            appliedProfileList.add(pApp.getAppliedProfile());
        }
        //추가할 프로파일 리스트 생성
        List<Profile> addList = new ArrayList<Profile>();
        for(Profile pro : displayedProfileList){
            addList.add(pro);
        }
        //삭제할 프로파일 리스트 생성
        List<Profile> removeList = new ArrayList<Profile>();
        for(Profile pro : appliedProfileList){
            removeList.add(pro);
        }
        //모델편집기에는 표시되나 실제 모델에는 적용되지 않은 프로파일 리스트를 추려낸다.
        for(Profile profile : appliedProfileList){
            if( addList.contains(profile) ){
                addList.remove(profile);
            }
        }
        //프로파일들을 모델에 추가
        for (Profile profile : addList) {
            command = new HandleProfileCommand(DomainRegistry.getEditingDomain(),
            model,
            profile,
            true);
            commands.append(command);
        }
        //모델편집기에는 표시되지 않고 실제 모델에는 적용되어 있는 프로파일 리스트를 추려낸다.
        for(Profile profile : displayedProfileList){
            if( removeList.contains(profile) ){
                removeList.remove(profile);
            }
        }
        //프로파일들을 모델에서 삭제
        for (Profile profile : removeList) {
            command = new HandleProfileCommand(DomainRegistry.getEditingDomain(),
            model,
            profile,
            false);
            commands.append(command);
        }

        DomainModelHandlerUtil.getEditingDomain().getCommandStack().execute(commands);
    
    }

    /**
     * getApplyingProfileList
     *  
     * @return List<Profile>
     */
    private List<Profile> getApplyingProfileList() {
        return profilePage.getProfileList();
    }
}
