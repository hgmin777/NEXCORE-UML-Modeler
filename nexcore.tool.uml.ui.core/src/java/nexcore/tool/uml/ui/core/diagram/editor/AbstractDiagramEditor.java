/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.diagram.editor;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.EventObject;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nexcore.alm.common.ui.util.ALMLogger;
import nexcore.tool.uml.manager.DiagramEditDomain;
import nexcore.tool.uml.manager.utility.DomainModelHandlerUtil;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.diagram.action.AddAttributeAction;
import nexcore.tool.uml.ui.core.diagram.action.AddEnumerationLiteralAction;
import nexcore.tool.uml.ui.core.diagram.action.AddOperationAction;
import nexcore.tool.uml.ui.core.diagram.action.ApplyStereotypeAction;
import nexcore.tool.uml.ui.core.diagram.action.CopyDiagramNodeAction;
import nexcore.tool.uml.ui.core.diagram.action.CutDiagramNodeAction;
import nexcore.tool.uml.ui.core.diagram.action.DeleteUMLElementAction;
import nexcore.tool.uml.ui.core.diagram.action.DiagramContextMenuProvider;
import nexcore.tool.uml.ui.core.diagram.action.FindElementAction;
import nexcore.tool.uml.ui.core.diagram.action.PasteDiagramNodeAction;
import nexcore.tool.uml.ui.core.diagram.action.PrintPreviewAction;
import nexcore.tool.uml.ui.core.diagram.action.RefreshDiagramAction;
import nexcore.tool.uml.ui.core.diagram.action.SaveToImageAction;
import nexcore.tool.uml.ui.core.diagram.action.SelectAllNodeAction;
import nexcore.tool.uml.ui.core.diagram.action.ShowNameOnlyAction;
import nexcore.tool.uml.ui.core.diagram.action.ShowParametersAction;
import nexcore.tool.uml.ui.core.diagram.action.ShowTypeAction;
import nexcore.tool.uml.ui.core.diagram.edit.part.DiagramRootEditPart;
import nexcore.tool.uml.ui.core.diagram.editor.palette.NewSelectionTool;
import nexcore.tool.uml.ui.core.registry.ResourceManager;
import nexcore.tool.uml.ui.core.util.ProjectUtil;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.parts.ScrollableThumbnail;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.change.ResourceChange;
import org.eclipse.emf.ecore.change.util.ChangeRecorder;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.transaction.TransactionChangeDescription;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.KeyStroke;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.Tool;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.AlignmentAction;
import org.eclipse.gef.ui.actions.DirectEditAction;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.ZoomInAction;
import org.eclipse.gef.ui.actions.ZoomOutAction;
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite;
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite.FlyoutPreferences;
import org.eclipse.gef.ui.parts.ContentOutlinePage;
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;
import org.eclipse.gef.ui.parts.GraphicalViewerKeyHandler;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.uml2.uml.util.UMLSwitch;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.daigram.editor</li>
 * <li>설 명 : AbstractDiagramEditor</li>
 * <li>작성일 : 2009. 11. 16.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public abstract class AbstractDiagramEditor extends GraphicalEditorWithFlyoutPalette implements
ITabbedPropertySheetPageContributor {

    /** DiagramEditDomain */
    protected DiagramEditDomain diagramEditDomain;

    /** 트랜잭셔널 리소스 리스너 */
    private TransactionalResourceListener transactionalResourceListener;

    /** 키 핸들러 */
    private KeyHandler keyHandler;
    
    /**
     * recorder
     */
    protected ChangeRecorder recorder;

    /**
     * 생성자
     */
    public AbstractDiagramEditor() {
        super();
        transactionalResourceListener = new TransactionalResourceListener(this);
    }

    /**
     * 입력 설정
     * 
     * @see org.eclipse.ui.part.EditorPart#setInput(org.eclipse.ui.IEditorInput)
     */
    protected void setInput(IEditorInput input) {
        if (input instanceof DiagramEditorInput) {
            super.setInput(input);
            DiagramEditorInput editorInput = (DiagramEditorInput) input;
            updateTitleAndToolTip();
            diagramEditDomain = DomainModelHandlerUtil.createDiagramEditDomain(this, editorInput.getDiagram());
            diagramEditDomain.setDefaultTool(this.getDefaultTool());
            setEditDomain(diagramEditDomain);
            this.setHook();
        }
    }

    /**
     * 기본 툴 반환
     * 
     * @return Tool
     */
    protected Tool getDefaultTool() {
        return new NewSelectionTool();
    }

    /**
     * @see org.eclipse.gef.ui.parts.GraphicalEditor#createActions()
     */
    @SuppressWarnings("unchecked")
    @Override
    protected void createActions() {
        super.createActions();
        ActionRegistry registry = getActionRegistry();
        IAction action;

        action = new SaveToImageAction(this);
        registry.registerAction(action);
        getSelectionActions().add(action.getId());

        action = new PrintPreviewAction(this);
        registry.registerAction(action);
        getSelectionActions().add(action.getId());        

        registry.registerAction(new nexcore.tool.uml.ui.core.diagram.action.PrintAction(this));

        action = new DirectEditAction((IWorkbenchPart) this);
        registry.registerAction(action);
        getSelectionActions().add(action.getId());

        action = new DeleteUMLElementAction(this);
        registry.registerAction(action);
        getSelectionActions().add(action.getId());

        action = new AddAttributeAction(this);
        registry.registerAction(action);
        getSelectionActions().add(action.getId());
        action = new AddOperationAction(this);
        registry.registerAction(action);
        getSelectionActions().add(action.getId());
        action = new FindElementAction(this);
        registry.registerAction(action);
        getSelectionActions().add(action.getId());

        action = new RefreshDiagramAction(this);
        registry.registerAction(action);
        getSelectionActions().add(action.getId());

        action = new CopyDiagramNodeAction(this);
        registry.registerAction(action);
        getSelectionActions().add(action.getId());
        action = new PasteDiagramNodeAction(this);
        registry.registerAction(action);
        getSelectionActions().add(action.getId());
        action = new CutDiagramNodeAction(this);
        registry.registerAction(action);
        getSelectionActions().add(action.getId());
        
        action = new ShowNameOnlyAction(this);
        registry.registerAction(action);
        getSelectionActions().add(action.getId());
        action = new ShowTypeAction(this);
        registry.registerAction(action);
        getSelectionActions().add(action.getId());
        action = new ShowParametersAction(this);
        registry.registerAction(action);
        getSelectionActions().add(action.getId());
        
        
        action = new AddEnumerationLiteralAction(this);
        registry.registerAction(action);
        getSelectionActions().add(action.getId());
        
        action = new ApplyStereotypeAction(this);
        registry.registerAction(action);
        getSelectionActions().add(action.getId());
        
        action = new SelectAllNodeAction(this);
        registry.registerAction(action);
        getSelectionActions().add(action.getId());
    }

    /**
     * updateTitleAndToolTip void
     */
    protected void updateTitleAndToolTip() {
        setPartName(((DiagramEditorInput) getEditorInput()).getName());
        setTitleToolTip(((DiagramEditorInput) getEditorInput()).getToolTipText());
    }

    /**
     * UML 모델에서 프로퍼티 제목에 사용할 특정 요소의 이름을 반환할 때 사용하는 메소드
     */
    private static UMLSwitch<Object> umlNameSwitch = new UMLSwitch<Object>() {
        /**
         * @see org.eclipse.uml2.uml.util.UMLSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
         */
        @Override
        public Object defaultCase(EObject object) {
            return ((Diagram) object).getName();
        }
    };
    
    /**
     * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette#initializeGraphicalViewer()
     */
    @Override
    protected void initializeGraphicalViewer() {
        super.initializeGraphicalViewer();
        GraphicalViewer viewer = getGraphicalViewer();
        viewer.setContents(diagramEditDomain.getDiagram());
        viewer.addDropTargetListener(createDropTagertListener());
        
        this.recorder = new ChangeRecorder(getDiagram());
    }

    /**
     * 
     * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette#getPalettePreferences()
     */
    protected FlyoutPreferences getPalettePreferences() {
        return createPalettePreferences();
    }

    /** Preference ID used to persist the palette location. */
    private static final String PALETTE_DOCK_LOCATION = UiCorePlugin.PLUGIN_ID + ".Location"; //$NON-NLS-1$

    /** Preference ID used to persist the palette size. */
    private static final String PALETTE_SIZE = UiCorePlugin.PLUGIN_ID + ".Size"; //$NON-NLS-1$

    /** Preference ID used to persist the flyout palette's state. */
    private static final String PALETTE_STATE = UiCorePlugin.PLUGIN_ID + ".State"; //$NON-NLS-1$

    /**
     * createPalettePreferences
     *  
     * @return FlyoutPreferences
     */
    public FlyoutPreferences createPalettePreferences() {
        return new FlyoutPreferences() {

            private IPreferenceStore getPreferenceStore() {
                return UiCorePlugin.getDefault().getPreferenceStore();
            }

            public int getDockLocation() {
                int location = getPreferenceStore().getInt(PALETTE_DOCK_LOCATION);
                if (location == 0) {
                    setDockLocation(PositionConstants.EAST);
                }
                return getPreferenceStore().getInt(PALETTE_DOCK_LOCATION);
            }

            public int getPaletteState() {
                int location = getPreferenceStore().getInt(PALETTE_STATE);
                if (location == 0) {
                    setPaletteState(FlyoutPaletteComposite.STATE_PINNED_OPEN);
                }

                return getPreferenceStore().getInt(PALETTE_STATE);
            }

            public int getPaletteWidth() {
                return getPreferenceStore().getInt(PALETTE_SIZE);
            }

            public void setDockLocation(int location) {
                getPreferenceStore().setValue(PALETTE_DOCK_LOCATION, location);
            }

            public void setPaletteState(int state) {
                getPreferenceStore().setValue(PALETTE_STATE, state);
            }

            public void setPaletteWidth(int width) {
                getPreferenceStore().setValue(PALETTE_SIZE, width);
            }
        };
    }

    /**
     * createDropTagertListener
     * 
     * @return TransferDropTargetListener
     */
    protected DiagramDropTargetListener createDropTagertListener() {
        return new DiagramDropTargetListener(this);
    }

    /**
     * @see org.eclipse.gef.ui.parts.GraphicalEditor#configureGraphicalViewer()
     */
    protected void configureGraphicalViewer() {
        super.configureGraphicalViewer();
        GraphicalViewer viewer = getGraphicalViewer();
        viewer.setEditPartFactory(createDiagramEditPartFactory());
        viewer.setRootEditPart(createRootEditPart());
        viewer.setKeyHandler(createKeyHandler(viewer));
        viewer.setContextMenu(createContextMenuProvider(viewer, getActionRegistry()));

        // viewer.getKeyHandler().put(KeyStroke.getPressed(SWT.DEL, 127, 0),
        // getActionRegistry().getAction(GEFActionConstants.DELETE));
    }

    /**
     * 에디트 파트 팩토리를 생성한다.
     * 
     * @return
     */
    protected abstract EditPartFactory createDiagramEditPartFactory();

    /**
     * 루트 에디트 파트를 생성한다.
     * 
     * @return
     */
    protected RootEditPart createRootEditPart() {
        DiagramRootEditPart rootEditPart = new DiagramRootEditPart();
        // zoomManager
        ZoomManager manager = rootEditPart.getZoomManager();
        // ZoomIn 액션의 작성과 등록
        IAction action = new ZoomInAction(manager);
        getActionRegistry().registerAction(action);
        // ZoomOut 액션의 작성과 등록
        action = new ZoomOutAction(manager);
        getActionRegistry().registerAction(action);

        action = new AlignmentAction((IWorkbenchPart) this, PositionConstants.LEFT);
        getActionRegistry().registerAction(action);
        getSelectionActions().add(action.getId());

        action = new AlignmentAction((IWorkbenchPart) this, PositionConstants.CENTER);
        getActionRegistry().registerAction(action);
        getSelectionActions().add(action.getId());

        action = new AlignmentAction((IWorkbenchPart) this, PositionConstants.RIGHT);
        getActionRegistry().registerAction(action);
        getSelectionActions().add(action.getId());

        action = new AlignmentAction((IWorkbenchPart) this, PositionConstants.TOP);
        getActionRegistry().registerAction(action);
        getSelectionActions().add(action.getId());

        action = new AlignmentAction((IWorkbenchPart) this, PositionConstants.MIDDLE);
        getActionRegistry().registerAction(action);
        getSelectionActions().add(action.getId());

        action = new AlignmentAction((IWorkbenchPart) this, PositionConstants.BOTTOM);
        getActionRegistry().registerAction(action);
        getSelectionActions().add(action.getId());

        return rootEditPart;
    }

    /**
     * KeyHandler를 생성한다.
     * 
     * @return
     */
    protected KeyHandler createKeyHandler(GraphicalViewer viewer) {
        return new GraphicalViewerKeyHandler(viewer).setParent(getKeyHandler());
    }

    /**
     * 컨텍스트 메뉴 제공자를 생성한다.
     * 
     * @param viewer
     * @param registry
     * @return ContextMenuProvider
     */
    protected ContextMenuProvider createContextMenuProvider(EditPartViewer viewer, ActionRegistry registry) {
        return new DiagramContextMenuProvider(viewer, registry);
    }

    /**
     * getGraphicalViewer()를 public으로..
     * 
     * @return GraphicalViewer
     */
    public GraphicalViewer getDiagramGraphicalViewer() {
        return getGraphicalViewer();
    }

    /**
     * getDiagram
     * 
     * @return Diagram
     */
    public Diagram getDiagram() {
        return diagramEditDomain.getDiagram();
    }

    /**
     * <ul>
     * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
     * <li>서브 업무명 : nexcore.tool.uml.ui.core.diagram.editor</li>
     * <li>설 명 : TransactionalResourceListener</li>
     * <li>작성일 : 2010. 1. 14.</li>
     * <li>작성자 : 한승일</li>
     * 다이어그램이 삭제될 때 다이어그램을 닫기 위한 처리를 위해 Resource변경을 Listen함.
     * </ul>
     */
    class TransactionalResourceListener extends ResourceSetListenerImpl {
        /** 다이어그램 에디터 인스턴스 */
        AbstractDiagramEditor diagramEditor;

        /**
         * 생성자
         * 
         * @param diagramEditor
         */
        TransactionalResourceListener(AbstractDiagramEditor diagramEditor) {
            this.diagramEditor = diagramEditor;
        }

        /**
         * @see org.eclipse.emf.transaction.ResourceSetListenerImpl#resourceSetChanged(org.eclipse.emf.transaction.ResourceSetChangeEvent)
         */
        @Override
        public void resourceSetChanged(ResourceSetChangeEvent event) {
            {
                List<Notification> notificationList = event.getNotifications();
                Notification notification = null;
                for (Iterator<Notification> it = notificationList.iterator(); it.hasNext();) {
                    notification = it.next();
                    int eventType = notification.getEventType();
                    switch (eventType) {
                        case Notification.ADD:
                        case Notification.ADD_MANY:
                            
                            boolean resourceExists = false;
                            try {
                                if (notification.getNewValue() instanceof EObject) {
                                    URI uri = EcoreUtil.getURI((EObject) notification.getNewValue());
                                    ResourceSet resourceSet = DomainRegistry.getUMLDomain().getResourceSet();
                                    resourceExists = resourceSet.getURIConverter().exists(uri, null);
                                    if (resourceExists) {
                                        diagramEditor.getGraphicalViewer().getContents().refresh();
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                    }
                }
                
            }
            
            Diagram dig = this.diagramEditor.getDiagram();
            if (DomainUtil.isProxy(dig)) {
                this.diagramEditor.closeEditor(false);
                return;
                
//                URI uri = EcoreUtil.getURI(dig);
//                URI resourceURI = uri.trimFragment();
//                if(null != DomainRegistry.getUMLDomain().getResource(resourceURI, false)){
//                    dig = (Diagram) DomainRegistry.getUMLDomain().getResourceSet().getEObject(uri, true);
//                    //diagramEditor.getDiagram()은 여전히 proxy임.
//                }
            }

//            if(null == dig || null == dig.eResource()){
//                this.diagramEditor.closeEditor(false);
//                return;
//            }

            Transaction transaction = event.getTransaction();
            if (transaction == null) {
                return;
            }

            TransactionChangeDescription desc = transaction.getChangeDescription();
            if (desc == null) {
                return;
            }

            this.diagramEditor.updateTitleAndToolTip();
            EList<ResourceChange> resourceChanges = desc.getResourceChanges();
            
         // ///////////2011.08.11 황선림.
            // 단편화한 요소와 관련된 다이어그램 에디터를 모두 닫는 문제가 있어서 주석
            // 처리. 다이어그램 삭제 시에는 DeleteAction에서 에디터 닫기를 수행하고 있음.
            // for (ResourceChange resourceChange : resourceChanges) {
//                if (UICoreConstant.PROJECT_CONSTANTS__FRAGMENT_EXTENSION.equals(resourceChange.getResource()
//                    .getURI()
//                    .fileExtension())) {
//
//                    if (this.diagramEditor.getDiagram() instanceof Diagram) {
//                        Diagram diagram = this.diagramEditor.getDiagram();
//                        for (AbstractNode abstractNode : diagram.getNodeList()) {
//                            EList<EObject> eList = (EList<EObject>) resourceChange.getValue();
//                            for (EObject eObj : eList) {
//                                for (EObject childEObj : eObj.eContents()) {
//                                    if (childEObj.equals(abstractNode.getUmlModel())) {
//                                        this.diagramEditor.closeEditor(true);
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
         // /////////////////////////////////////////////////
            
            // for(ResourceChange rChange : resourceChanges){
            // if(rChange.getResourceURI().contains(UICoreConstant.PROJECT_CONSTANTS__FRAGMENT_EXTENSION)){
            // this.diagramEditor.closeEditor(true);
            // }
            // }

            if (resourceChanges.size() != 0) {
                List<Notification> notificationList = event.getNotifications();
                Notification notification = null;
                for (Iterator<Notification> it = notificationList.iterator(); it.hasNext();) {
                    notification = it.next();
                    int eventType = notification.getEventType();
                    switch (eventType) {
                        case Notification.ADD:
                        case Notification.ADD_MANY:
                            break;
                        case Notification.SET:
                        case Notification.UNSET:
                            this.diagramEditor.updateTitleAndToolTip();
                            break;
                        case Notification.REMOVE:
                        case Notification.REMOVE_MANY:
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    /**
     * 리소스 변경 리스너 세팅 void
     */
    protected void setHook() {
        // Resource 변경 Listener
        DomainRegistry.getUMLDomain()
            .getTransactionalEditingDomain()
            .addResourceSetListener(this.transactionalResourceListener);
    }

    /**
     * 리소스 변경 리스너 해제 void
     */
    protected void unsetHook() {
        DomainRegistry.getUMLDomain()
            .getTransactionalEditingDomain()
            .removeResourceSetListener(this.transactionalResourceListener);
    }

    /**
     * getCommandStack()를 public으로..
     * 
     * @return CommandStack
     */
    public CommandStack getDiagramCommandStack() {
        return getCommandStack();
    }

    /**
     * @see org.eclipse.gef.ui.parts.GraphicalEditor#commandStackChanged(java.util.EventObject)
     */
    @Override
    public void commandStackChanged(EventObject event) {
        Display.getDefault().syncExec(new Runnable() {
            @Override
            public void run() {
                firePropertyChange(IEditorPart.PROP_DIRTY);
            }
        });
        super.commandStackChanged(event);
    }

    /**
     * @see org.eclipse.gef.ui.parts.GraphicalEditor#isDirty()
     */
    public boolean isDirty() {
        org.eclipse.emf.ecore.resource.Resource resource = this.getDiagram().eResource();
        if (null == resource) {
            return false;
        }

        // crossReference 모델의 dirty 상태 체크
        if (resource.isModified() || ResourceManager.getInstance().isDirtyOfCrossReference(this.getDiagram())) {
            return true;
        }

        return false;
    }

    /**
     * @see org.eclipse.ui.part.EditorPart#doSave(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void doSave(IProgressMonitor monitor) {
        
        DomainModelHandlerUtil.save(getDiagram());
        
        // 2012-07-11 pns
        // 저장시 관련된 리소스까지 같이 저장해야함...
        // crossreference 정보도 같이 저장하는 로직 추가
        
        Set<Resource> resourceList = new HashSet<Resource>();
        
        Map<EObject, Collection<Setting>> find = EcoreUtil.CrossReferencer.find(getDiagram().eContents());
        for (Iterator<?> iterator = find.keySet().iterator(); iterator.hasNext();) {
            EObject eObject = (EObject) iterator.next();
            Resource refResource = eObject.eResource();
            if (ProjectUtil.isModelFile(refResource) && getDiagram().eResource() != refResource) {
                if (refResource.isModified()) {
                    resourceList.add(refResource);
                }
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
        ProjectUtil.beginRecording(recorder, getDiagram());
    }

    class UMLContentOutlinePage extends ContentOutlinePage {

        private Composite control;

        private ScrollableThumbnail thumbnail;

        private DisposeListener disposeListener;

        public UMLContentOutlinePage() {
            super(new TreeViewer());
        }

        public void init(IPageSite pageSite) {
            super.init(pageSite);
        }

        public void createControl(Composite parent) {
            control = new SashForm(parent, SWT.NONE);

            getViewer().createControl(parent);

            getViewer().setEditDomain(getEditDomain());
            getSelectionSynchronizer().addViewer(getViewer());

            Canvas canvas = new Canvas(control, SWT.BORDER);
            LightweightSystem lws = new LightweightSystem(canvas);

            thumbnail = new ScrollableThumbnail((Viewport) ((ScalableFreeformRootEditPart) getGraphicalViewer().getRootEditPart()).getFigure());
            thumbnail.setSource(((ScalableFreeformRootEditPart) getGraphicalViewer().getRootEditPart()).getLayer(LayerConstants.PRINTABLE_LAYERS));

            lws.setContents(thumbnail);

            disposeListener = new DisposeListener() {
                public void widgetDisposed(DisposeEvent e) {
                    if (thumbnail != null) {
                        thumbnail.deactivate();
                        thumbnail = null;
                    }
                }
            };
            getGraphicalViewer().getControl().addDisposeListener(disposeListener);

        }

        public Control getControl() {
            return control;
        }

        public void dispose() {
            getSelectionSynchronizer().removeViewer(getViewer());

            if (getGraphicalViewer().getControl() != null && !getGraphicalViewer().getControl().isDisposed())
                getGraphicalViewer().getControl().removeDisposeListener(disposeListener);

            super.dispose();
        }

    }

    /**
     * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette#getAdapter(java.lang.Class)
     */
    @SuppressWarnings("unchecked")
    public Object getAdapter(Class adapter) {
        if (adapter == IPropertySheetPage.class)
            return new TabbedPropertySheetPage(this);

        if (adapter == IContentOutlinePage.class) {
            return new UMLContentOutlinePage();
        }

        return super.getAdapter(adapter);
    }

    /**
     * 에디터 닫기
     * 
     * @param save
     */
    public void closeEditor(final boolean save) {
        this.unsetHook();
        getSite().getShell().getDisplay().syncExec(new Runnable() {
            /**
             * @see java.lang.Runnable#run()
             */
            public void run() {
                getSite().getPage().closeEditor(AbstractDiagramEditor.this, save);
            }
        });
    }

    /**
     * @see org.eclipse.gef.ui.parts.GraphicalEditor#dispose()
     */
    @Override
    public void dispose() {
        this.unsetHook();
        
        if (isDirty()) {
            ProjectUtil.rollbackWithSave(recorder, getDiagram());
        }
        
        super.dispose();
    }

    /**
     * 에디터에서 직접 편집 및 삭제 단축키 처리
     */
    protected KeyHandler getKeyHandler() {
        if (keyHandler == null) {
            keyHandler = new KeyHandler();

            // F2키와 DirectEditAction를 묶는다.
            keyHandler.put(KeyStroke.getPressed(SWT.F2, 0),
                getActionRegistry().getAction(GEFActionConstants.DIRECT_EDIT));

            // Ctrl+D로 '모델에서 삭제'.
            keyHandler.put(KeyStroke.getPressed((char) 04, 'd', SWT.CTRL),
                getActionRegistry().getAction(DeleteUMLElementAction.DELETE_UMLELEMENT_ACTIONID));
            keyHandler.put(KeyStroke.getPressed((char) 04, 'D', SWT.CTRL),
                getActionRegistry().getAction(DeleteUMLElementAction.DELETE_UMLELEMENT_ACTIONID));
            
            
            // Ctrl+A로 'Select All'.
               keyHandler.put(KeyStroke.getPressed((char) 01, 'a', SWT.CTRL),
                   getActionRegistry().getAction(SelectAllNodeAction.SELECT_ALL_NODE_ACTIONID));
               keyHandler.put(KeyStroke.getPressed((char) 01, 'A', SWT.CTRL),
                   getActionRegistry().getAction(SelectAllNodeAction.SELECT_ALL_NODE_ACTIONID));
               
               

            // Ctrl+C로 '다이어그램에서 노드 복사'.
            keyHandler.put(KeyStroke.getPressed((char) 03, 'c', SWT.CTRL),
                getActionRegistry().getAction(CopyDiagramNodeAction.COPY_DIAGRAMNODE_ACTIONID));
            keyHandler.put(KeyStroke.getPressed((char) 03, 'C', SWT.CTRL),
                getActionRegistry().getAction(CopyDiagramNodeAction.COPY_DIAGRAMNODE_ACTIONID));
            // Ctrl+V로 '다이어그램에서 노드 붙여넣기'.
            keyHandler.put(KeyStroke.getPressed((char) 22, 'v', SWT.CTRL),
                getActionRegistry().getAction(PasteDiagramNodeAction.PASTE_DIAGRAMNODE_ACTIONID));
            keyHandler.put(KeyStroke.getPressed((char) 22, 'V', SWT.CTRL),
                getActionRegistry().getAction(PasteDiagramNodeAction.PASTE_DIAGRAMNODE_ACTIONID));
            // Ctrl+X로 '다이어그램에서 노드 잘라내기'.
            keyHandler.put(KeyStroke.getPressed((char) 24, 'x', SWT.CTRL),
                getActionRegistry().getAction(CutDiagramNodeAction.CUT_DIAGRAMNODE_ACTIONID));
            keyHandler.put(KeyStroke.getPressed((char) 24, 'X', SWT.CTRL),
                getActionRegistry().getAction(CutDiagramNodeAction.CUT_DIAGRAMNODE_ACTIONID));

            // F3 = Add Attribute
            keyHandler.put(KeyStroke.getPressed(SWT.F3, 0),
                getActionRegistry().getAction(AddAttributeAction.ADD_ATTRIBUTE_ACTIONID));
            // F4 = Add Operation
            keyHandler.put(KeyStroke.getPressed(SWT.F4, 0),
                getActionRegistry().getAction(AddOperationAction.ADD_OPERATION_ACTIONID));
            // F5 = refresh diagram
            keyHandler.put(KeyStroke.getPressed(SWT.F5, 0),
                getActionRegistry().getAction(RefreshDiagramAction.REFRESH_DIAGRAM_ACTIONID));
        }
        return keyHandler;
    }

    /**
     * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor#getContributorId()
     */
    public String getContributorId() {
        return UICoreConstant.PROJECT_CONSTANTS__PROPERTY_CONTRIBUTOR_ID;
    }

}
