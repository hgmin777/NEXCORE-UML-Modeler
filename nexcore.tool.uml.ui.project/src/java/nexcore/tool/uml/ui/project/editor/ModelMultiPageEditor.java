/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.project.editor;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import nexcore.alm.common.ui.util.ALMLogger;
import nexcore.tool.mdd.core.extension.IDomainModelHandler;
import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.utility.DomainModelHandlerUtil;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.editor.AbstractMultiPageFormEditor;
import nexcore.tool.uml.ui.core.editor.input.UMLEditorInput;
import nexcore.tool.uml.ui.core.registry.ProjectRegistry.ViewerRegistry;
import nexcore.tool.uml.ui.core.util.ProjectUtil;
import nexcore.tool.uml.ui.project.editor.page.DetailsPage;
import nexcore.tool.uml.ui.project.editor.page.FragmentDiagramPage;
import nexcore.tool.uml.ui.project.editor.page.OverviewPage;
import nexcore.tool.uml.ui.project.explorer.provider.ExplorerSaveablesProvider.ExplorerSaveable;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.change.util.ChangeRecorder;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.transaction.TransactionChangeDescription;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.ISaveablesLifecycleListener;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.Saveable;
import org.eclipse.ui.internal.SaveablesList;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.editor</li>
 * <li>설 명 : ModelMultiPageEditor</li>
 * <li>작성일 : 2009. 12. 11.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class ModelMultiPageEditor extends AbstractMultiPageFormEditor {

    /** 개요 페이지 */
    private OverviewPage overviewPage;

    /** 상세 페이지 */
    private DetailsPage detailsPage;
    
    /**
     * fragmentDiagramPage
     */
    private FragmentDiagramPage fragmentDiagramPage;

    /** UML 모델 요소 */
    private Model model;
    
    /**
     * recorder
     */
    private ChangeRecorder recorder;

    /** 리소스셋 리스너 */
    private ResourceSetListener resourceSetListener;

    /**
     * @see nexcore.tool.uml.ui.core.editor.AbstractMultiPageFormEditor#init(org.eclipse.ui.IEditorSite,
     *      org.eclipse.ui.IEditorInput)
     */
    @Override
    public void init(IEditorSite site, IEditorInput input) throws PartInitException {
        super.init(site, input);
        
    }

    /**
     * @see nexcore.tool.uml.ui.core.editor.AbstractMultiPageFormEditor#createModel(org.eclipse.ui.IEditorInput)
     */
    @Override
    protected void createModel(IEditorInput input) throws PartInitException {
        if (input instanceof UMLEditorInput) {
            this.model = (Model) ((UMLEditorInput) getEditorInput()).getElement();
            this.recorder = new ChangeRecorder(model);
            registerResourceSetListener();
        }
    }

    /**
     * @see nexcore.tool.uml.ui.core.editor.AbstractMultiPageFormEditor#addPages()
     */
    @Override
    protected void addPages() {
        try {
            overviewPage = new OverviewPage(this,
                UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING,
                UMLMessage.LABEL_OVERVIEW);
            detailsPage = new DetailsPage(this,
                UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING,
                UMLMessage.LABEL_DETAIL_INFO);//"상세-1");
            
            fragmentDiagramPage = new FragmentDiagramPage(this,
                UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING,
                UMLMessage.LABEL_FRAGMENT_DIAGRAM);//"상세-2");

            addPage(overviewPage);
            addPage(detailsPage);
            addPage(fragmentDiagramPage);
            
            
            setActivePage(0);
        } catch (PartInitException pie) {
            Log.error(pie);
        }
    }

    /**
     * @see org.eclipse.ui.forms.editor.SharedHeaderFormEditor#isDirty()
     */
    @Override
    public boolean isDirty() {
        try {
            if (model.eResource() == null) {
                URI uri = EcoreUtil.getURI(model);
                model = (Model) DomainRegistry.getUMLDomain().getResourceSet().getEObject(uri, true);
            }
            return super.isDirty() || model.eResource().isModified();
        } catch (Exception e) {
            return super.isDirty();
        }
    }

    /**
     * @see nexcore.tool.uml.ui.core.editor.AbstractMultiPageFormEditor#doSave(org.eclipse.core.runtime.IProgressMonitor)
     */
    @SuppressWarnings("restriction")
    @Override
    public void doSave(IProgressMonitor monitor) {
//        commitFormPages();
        final IDomainModelHandler umlDomain = DomainRegistry.getUMLDomain();
        if (umlDomain == null) {
            Log.error("IDomainModelHandler is null.");
            return;
        }

        if (model.eResource() == null) {
            URI uri = EcoreUtil.getURI(model);
            model = (Model) DomainRegistry.getUMLDomain().getResourceSet().getEObject(uri, true);
        }
        
        Set<Resource> resourceList = new HashSet<Resource>();
        resourceList.add(model.eResource());
        
        TreeMap<String, Package> fragmentedPackageList = ProjectUtil.getFragmentedPackageList(model);
        for (Iterator<Package> iterator = fragmentedPackageList.values().iterator(); iterator.hasNext();) {
            Package fragmentedPackage = (Package) iterator.next();
            if(fragmentedPackage.eResource().isModified()) {
                resourceList.add(fragmentedPackage.eResource());
            }
        }
                
        final Set<Resource> modifiedResource = resourceList;
        
        Display.getDefault().syncExec(new Runnable() {
            
            @Override
            public void run() {
                ProgressMonitorDialog dialog = new ProgressMonitorDialog(getEditorSite().getShell());
                try {
                    dialog.run(true, false, new IRunnableWithProgress() {
                        
                        @Override
                        public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                            monitor.beginTask("Save", modifiedResource.size());
                            for (Resource r : modifiedResource) {
                                monitor.worked(1);

                                try {
                                    monitor.subTask(r.getURI().toString());
                                    DomainModelHandlerUtil.save(r, false);
                                } catch (Exception e) {
                                    ALMLogger.getLog(UiCorePlugin.PLUGIN_ID).error(e.getMessage(), e);
                                }
                            }
                            
                            monitor.done();
                        }
                        
                    });
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } 
            }
        });
        
        editorDirtyStateChanged();
        
        ProjectUtil.beginRecording(recorder, model);

        SaveablesList modelManager = null;
        modelManager = (SaveablesList) getSite().getWorkbenchWindow().getService(ISaveablesLifecycleListener.class);

        Saveable[] openModels = modelManager.getOpenModels();

        for (Saveable saveable : openModels) {
            if (saveable instanceof ExplorerSaveable) {
                ExplorerSaveable explorerSaveable = (ExplorerSaveable) saveable;
                explorerSaveable.dirtyStateChanged();
            }
        }
    }

    /**
     * @return the model
     */
    public Model getModel() {
        return model;
    }

    /**
     * @see org.eclipse.ui.forms.editor.SharedHeaderFormEditor#dispose()
     */
    @Override
    public void dispose() {
        if (model != null && model.eResource() != null) {
            if (isDirty()) {
                ProjectUtil.rollbackWithSave(recorder, model);
                
//                TreeMap<String, Package> fragmentedPackageList = ProjectUtil.getFragmentedPackageList(model);
//                for (Iterator<Package> iterator = fragmentedPackageList.values().iterator(); iterator.hasNext();) {
//                    Package pkg = (Package) iterator.next();
//                    
//                    ProjectUtil.rollbackWithSave(recorder, pkg);
//                }
            }
        }
        
        try {
            SaveablesList modelManager = null;
            modelManager = (SaveablesList) getSite().getWorkbenchWindow().getService(ISaveablesLifecycleListener.class);

            Saveable[] openModels = modelManager.getOpenModels();

            for (Saveable saveable : openModels) {
                if (saveable instanceof ExplorerSaveable) {
                    ExplorerSaveable explorerSaveable = (ExplorerSaveable) saveable;
                    explorerSaveable.dirtyStateChanged();
                }
            }
        } catch (Exception e) {
            // ignore
        }
        
        unregisterResourceSetListener();

        super.dispose();
    }

    /**
     * 리소스셋 리스너 등록
     * 
     * void
     */
    private void registerResourceSetListener() {
        resourceSetListener = new ModelResourceSetListener();

        DomainRegistry.getEditingDomain().addResourceSetListener(resourceSetListener);
    }

    /**
     * 리소스셋 리스너 등록 해제
     * 
     * void
     */
    private void unregisterResourceSetListener() {
        DomainRegistry.getEditingDomain().removeResourceSetListener(resourceSetListener);
    }

    /**
     * <ul>
     * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
     * <li>서브 업무명 : nexcore.tool.uml.ui.project.editor</li>
     * <li>설 명 : ModelResourceSetListener</li>
     * <li>작성일 : 2010. 12. 23.</li>
     * <li>작성자 : 최윤석</li>
     * </ul>
     */
    class ModelResourceSetListener extends ResourceSetListenerImpl {

        /**
         * @see org.eclipse.emf.transaction.ResourceSetListenerImpl#resourceSetChanged(org.eclipse.emf.transaction.ResourceSetChangeEvent)
         */
        @Override
        public void resourceSetChanged(ResourceSetChangeEvent event) {

        	if (null == model) {
        		close(true);
        		return;
        	}
        	
        	if (DomainUtil.isProxy(model)) {
                URI uri = EcoreUtil.getURI(model);
                URI resourceUri = uri.trimFragment();
                if (null != DomainRegistry.getUMLDomain().getResource(resourceUri, false)) {
                	model = (Model) DomainRegistry.getUMLDomain().getResourceSet().getEObject(uri, true);
                } else {
                	model = null;
                	close(true);
                	return;
                }
            }

            Transaction transaction = event.getTransaction();
            if (transaction == null) {
                return;
            }

            TransactionChangeDescription desc = transaction.getChangeDescription();
            if (desc == null) {
                return;
            }

            List<Notification> notificationList = event.getNotifications();
            Notification notification = null;
            int eventType = -1;

            for (Iterator<Notification> notificationIterator = notificationList.iterator(); notificationIterator.hasNext();) {
                notification = notificationIterator.next();
                eventType = notification.getEventType();

                if (eventType != Notification.SET) {
                    // EList<ResourceChange> resourceChanges =
                    // desc.getResourceChanges();
                    // for(ResourceChange rChange : resourceChanges){
                    // if(rChange.getResourceURI().contains(UICoreConstant.PROJECT_CONSTANTS__FRAGMENT_EXTENSION)){
                    // close(true);
                    // }
                    // }
                }

                switch (eventType) {
                    case Notification.ADD:
                    case Notification.ADD_MANY:
                    case Notification.SET:
                    case Notification.UNSET:
                    case Notification.REMOVE:
                    case Notification.REMOVE_MANY:
                        ViewerRegistry.getViewer().getControl().getDisplay().asyncExec(new Runnable() {
                            /**
                             * @see java.lang.Runnable#run()
                             */
                            public void run() {
                                firePropertyChange(PROP_DIRTY);
                            }
                        });
                        break;
                    default:
                        break;
                }
            }
        }

    }

}
