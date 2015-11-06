/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.project.builder;

import nexcore.tool.mdd.core.extension.IDomainModelHandler;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.extension.registry.PrecedingInitializerRegistry;
import nexcore.tool.uml.manager.utility.DomainModelHandlerUtil;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.model.projectinformation.ProjectElement;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.registry.ModelUpdater;
import nexcore.tool.uml.ui.core.registry.UpdateViewModel;
import nexcore.tool.uml.ui.core.util.ProjectUtil;
import nexcore.tool.uml.ui.project.editor.command.HandleProjectCommand;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.Package;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.builder</li>
 * <li>설 명 : ModelUpdateAction</li>
 * <li>작성일 : 2011. 5. 26.</li>
 * <li>작성자 : Kang</li>
 * </ul>
 */
public class ModelUpdateAction implements IObjectActionDelegate {
    
    /** selection */
    private Object selection;

    /** umlDomain */
    private IDomainModelHandler umlDomain;

    /** targetPart */
    private IWorkbenchPart targetPart;

    /** shell */
    private static Shell shell;
    
    /** alreadyCheck */
    private static boolean alreadyCheck;
    /**
     * ModelUpdate
     */
    public ModelUpdateAction() {
    }

    /**
     * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    public void run(IAction action) {

        createDomain();

        if (selection instanceof StructuredSelection) {
            Object element = ((StructuredSelection) selection).getFirstElement();
            IProject project = null;
            if (element instanceof IProject) {
                project = (IProject) element;
            } else if (element instanceof IAdaptable) {
                project = (IProject) ((IAdaptable) element).getAdapter(IProject.class);
            }
            if (null == project) {
                return;
            }
            this.shell = targetPart.getSite().getShell();
            resourceLoadAndModelUpdate(project);
        }
    }


    /**
     * 
     *  
     * @param project void
     */
    private void resourceLoadAndModelUpdate(final IProject project) {
        try {
            alreadyCheck = false;
            
            ModelUpdater updater = new ModelUpdater();
            updater.updateReferenceAddress(project);

            for (IResource resource : project.members()) {
                loadChildResource(resource);
            }
        } catch (CoreException e) {
            e.printStackTrace();
        }

    }

    /**
     * 
     * 
     * 
     * @param resourceFile
     * @param monitor
     *            void
     */
    private void loadChildResource(IResource resourceFile) {
        try {
            if (resourceFile instanceof IFolder) {
                for (IResource member : ((IFolder) resourceFile).members()) {
                    loadChildResource(member);
                }
            } else if (resourceFile instanceof IFile) {
                try {
                    if (ProjectUtil.isModelFile((IFile) resourceFile)) {
                        URI uri = URI.createURI(((IFile) resourceFile).getFullPath().toString());
                        Resource res = DomainRegistry.getUMLDomain().getResource(uri, true);
                        if( res == null) {
                        	return;
                        }
                        for (int j = 0; j < res.getContents().size(); j++) {
                            EObject obj = res.getContents().get(j);
                            if (obj instanceof org.eclipse.uml2.uml.Package) {
                                obj = ProjectUtil.resolveResource(obj);
                                if (!checkViewModelVersion((org.eclipse.uml2.uml.Package) obj, (IFile) resourceFile)) {
                                    
                                    if(!alreadyCheck){
                                        MessageDialog.openInformation(shell ,
                                            UMLMessage.MESSAGE_MODEL_VERSION_MISSMATCH,
                                            UMLMessage.MESSAGE_UPDATE_MODEL_VERSION_MESSAGE);
                                        alreadyCheck = true;
                                    }
                                    UpdateViewModel.updateViewModel((org.eclipse.uml2.uml.Package) obj,
                                        (IFile) resourceFile);
                                    obj = ProjectUtil.resolveResource((Package) obj);
                                    addUMLModelVersion((org.eclipse.uml2.uml.Package) obj, (IFile) resourceFile);
                                    obj = ProjectUtil.resolveResource(obj);
                                } else if(!alreadyCheck){
                                    MessageDialog.openInformation(shell ,
                                        UMLMessage.MESSAGE_MODEL_VERSION_MATCH,
                                        UMLMessage.MESSAGE_MODEL_VERSION_MATCH_MESSAGE);
                                    alreadyCheck = true;
                                }
                            }
                        }
                        try {
                            DomainModelHandlerUtil.save(res, true);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    // ignore
                }
            }
        } catch (CoreException e) {
            e.printStackTrace();
        }
    }


    /**
     * 
     * 
     * @param file
     * @param obj
     *            void
     */
    private boolean checkViewModelVersion(org.eclipse.uml2.uml.Package package1, IFile file) {
        ProjectElement projectInfo = null;
        for (EAnnotation eAnnotation : package1.getEAnnotations()) {
            if (eAnnotation instanceof ProjectElement) {
                projectInfo = (ProjectElement) eAnnotation;
            }
        }

        if (projectInfo == null) {
            return false;
        } else if (!UICoreConstant.PROJECT_CONSTANTS__MODEL_VERSION.equals(projectInfo.getModelVersion())) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 
     *  
     * @param package1
     * @param file void
     */
    private void addUMLModelVersion(org.eclipse.uml2.uml.Package package1, IFile file) {

        package1 = (Package) ProjectUtil.resolveResource(package1);
        ProjectElement newProjectInfo = ProjectUtil.createProjectInfo();
        RecordingCommand command = new HandleProjectCommand(DomainRegistry.getEditingDomain(),
            package1,
            newProjectInfo,
            true);
        DomainUtil.executeCommand(command);
    }

    /**
     * if there's no UMLDomain, creates UMLDomain.
     */
    private void createDomain() {
        umlDomain = DomainRegistry.getUMLDomain();

        if (umlDomain == null) {
            umlDomain = ProjectUtil.createDomain(true);
        }

        // 선행 초기화 작업 실행
        PrecedingInitializerRegistry.getInstance().executeInitializer();
    }

    /**
     * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
     *      org.eclipse.jface.viewers.ISelection)
     */
    public void selectionChanged(IAction action, ISelection selection) {
        this.selection = selection;
    }

    /**
     * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction, org.eclipse.ui.IWorkbenchPart)
     */
    public void setActivePart(IAction action, IWorkbenchPart targetPart) {
        this.targetPart =  targetPart;
    }

}
