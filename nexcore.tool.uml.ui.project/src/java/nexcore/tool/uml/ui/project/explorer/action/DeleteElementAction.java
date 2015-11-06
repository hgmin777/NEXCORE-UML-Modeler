/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.project.explorer.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.manager.command.DeleteUMLElementCommandFactory;
import nexcore.tool.uml.manager.transaction.UMLDiagramCommandStack;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.DiagramType;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.diagram.editor.AbstractDiagramEditor;
import nexcore.tool.uml.ui.core.diagram.editor.DiagramEditorInput;
import nexcore.tool.uml.ui.core.project.ITreeNode;
import nexcore.tool.uml.ui.core.project.UMLFileTreeNode;
import nexcore.tool.uml.ui.core.registry.ProjectRegistry;
import nexcore.tool.uml.ui.core.registry.ProjectRegistry.ViewerRegistry;
import nexcore.tool.uml.ui.core.registry.ResourceManager;
import nexcore.tool.uml.ui.core.util.ProjectUtil;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.ide.actions.LTKLauncher;
import org.eclipse.uml2.uml.Abstraction;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.BehaviorExecutionSpecification;
import org.eclipse.uml2.uml.CombinedFragment;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.ConnectableElement;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.ControlFlow;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.ExecutionEvent;
import org.eclipse.uml2.uml.ExecutionOccurrenceSpecification;
import org.eclipse.uml2.uml.Extend;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Include;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.InteractionOperand;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.MessageOccurrenceSpecification;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.ObjectFlow;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageImport;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.ProfileApplication;
import org.eclipse.uml2.uml.Realization;
import org.eclipse.uml2.uml.ReceiveOperationEvent;
import org.eclipse.uml2.uml.Relationship;
import org.eclipse.uml2.uml.SendOperationEvent;
import org.eclipse.uml2.uml.Usage;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.action</li>
 * <li>설 명 : DeleteElementAction</li>
 * <li>작성일 : 2009. 12. 3.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
public class DeleteElementAction extends Action implements IObjectActionDelegate {

    /** List to delete. */
    private List<EObject> objList;

    /** 파일 삭제 시 사용 */
    private IStructuredSelection selection;
    
    /** 삭제 불가 요소 포함 여부 */
    private boolean hasNotDeleted = false;

    /**
     * DeleteElementAction
     */
    public DeleteElementAction() {
        objList = new ArrayList<EObject>();
    }

    /**
     * 'Delete' key action.
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        selectionChanged(null, ViewerRegistry.getViewer().getSelection());
        run(null);
    }

    /**
     * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    public void run(IAction action) {

        if (isFragment) {
            MessageDialog.openWarning(UiCorePlugin.getShell(),
                UMLMessage.getMessage(UMLMessage.LABEL_DELETE_FROM_MODEL),
                UMLMessage.getMessage(UMLMessage.MESSAGE_FRAGMENT_DELETE));
            return;
        }

        if (objList == null || objList.size() < 1) {
            List<IResource> list = selection.toList();
            
            final List<String> diagramURIList = new ArrayList<String>();
            
            for( IResource res : list ) {
                diagramURIList.add(res.getFullPath().toString());
            }
            
            if (!LTKLauncher.openDeleteWizard(selection)) {
                for (final IWorkbenchWindow window : PlatformUI.getWorkbench().getWorkbenchWindows()) {
                    IEditorReference[] editorRefs = window.getActivePage().getEditorReferences();

                    for (IEditorReference ref : editorRefs) {
                        IEditorPart editor = ref.getEditor(false);
                        if (editor instanceof AbstractDiagramEditor) {
                            if (editor instanceof AbstractDiagramEditor) {
                                AbstractDiagramEditor diagramEditor = (AbstractDiagramEditor) editor;
                                if (diagramEditor.getEditorInput() instanceof DiagramEditorInput) {
                                    DiagramEditorInput editorInput = (DiagramEditorInput) diagramEditor.getEditorInput();
                                    if (diagramURIList.contains(editorInput.getURI().toString())) {
                                        diagramEditor.closeEditor(false);
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                Map<URI, Resource> uriResourceMap = ((ResourceSetImpl) DomainRegistry.getUMLDomain().getResourceSet()).getURIResourceMap();
                
                for (IResource res : list) {
                    if (!res.exists()) {
                        try {
                            Resource resource = uriResourceMap.get(URI.createURI(res.getFullPath().toString()));
                            if (resource != null) {
                                resource.delete(null);
                            }
                        } catch (Exception e) {

                        }
                    }
                    for (final IWorkbenchWindow window : PlatformUI.getWorkbench().getWorkbenchWindows()) {
                        IEditorReference[] editorRefs = window.getActivePage().getEditorReferences();

                        for (IEditorReference ref : editorRefs) {
                            IEditorPart editor = ref.getEditor(false);
                            if (editor instanceof AbstractDiagramEditor) {
                                AbstractDiagramEditor diagramEditor = (AbstractDiagramEditor) editor;
                                if (diagramEditor.getEditorInput() instanceof DiagramEditorInput) {
                                    DiagramEditorInput editorInput = (DiagramEditorInput) diagramEditor.getEditorInput();
                                    if (!res.exists() && diagramURIList.contains(editorInput.getURI().toString())) {
                                        try {
                                            diagramEditor.closeEditor(false);
                                        } catch (Exception e) {}
                                    }
                                }
                            }

                        }
                    }
                }
            } 
            return;
        }

        String elementID;
        if (0 == objList.size()) {
            return;
        }

        Object element;
        if (1 == objList.size()) {
            element = objList.get(0);
            elementID = UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;
            if (hasNotDeleted) {
            	// 프로파일과 라이브러리는 삭제불가
            	MessageDialog.openWarning(UiCorePlugin.getShell(),
                        UMLMessage.getMessage(UMLMessage.LABEL_DELETE_FROM_MODEL),
                        UMLMessage.getMessage(UMLMessage.MESSAGE_PROFILE_DELETE));
            	return;
            } else if (element instanceof NamedElement) {
                elementID = ((NamedElement) element).getName();
                if (null == elementID) {
                    elementID = ((NamedElement) element).eClass().getName();
                }
            } else if (element instanceof Diagram) {
                elementID = ((Diagram) element).getName();

            } else if (element instanceof Relationship) {
                elementID = "Relationship"; //$NON-NLS-1$
            }
        } else {
            elementID = objList.size() + " elements"; //$NON-NLS-1$
        }
        
        if (hasNotDeleted) {
        	if (!MessageDialog.openConfirm(UiCorePlugin.getShell(),
        			UMLMessage.getMessage(UMLMessage.LABEL_DELETE_FROM_MODEL),
        			UMLMessage.getMessage(UMLMessage.MESSAGE_DELETE_WITHOUT_IMPOSSIBLE, elementID))) {
        		return;
        	}
        } else {
        	if (!MessageDialog.openConfirm(UiCorePlugin.getShell(),
        			UMLMessage.getMessage(UMLMessage.LABEL_DELETE_FROM_MODEL),
        			UMLMessage.getMessage(UMLMessage.MESSAGE_DELETE, elementID))) {
        		return;
        	}
        }


        // /////////////////////////////////////////////////////////////////////////////////////////////////////
        // 시퀀스다이어그램 삭제 다이어그램 내부 Element를 같이
        // 삭제한다.//////////////////////////////////////////////////
        final List<Diagram> diagramList = new ArrayList<Diagram>();
        for (EObject eobject : objList) {
            Element child = null;
            List<Element> ownedElementList = new ArrayList<Element>();
            if (eobject instanceof Diagram) {
                diagramList.add((Diagram) eobject);
                continue;
            }
            if (eobject instanceof Element) {
                child = (Element) eobject;
            }

            // 시퀀스 다이어그램 가져오기
            if (null != child) {
                List<Diagram> list = ProjectUtil.getDiagrams(child, DiagramType.SEQUENCE_DIAGRAM);
                if (null != list && !list.isEmpty()) {
                    for (Diagram dig : list) {
                        if (!diagramList.contains(dig)) {
                            diagramList.add(dig);
                        }
                    }
                }

                List<Element> ownedElements = child.getOwnedElements();
                for (Element ownedElement : ownedElements) {
                    if (!ownedElementList.contains(ownedElement)) {
                        ownedElementList.add(ownedElement);
                    }
                }
            }
            for (Element ownedElement : ownedElementList) {
                List<Diagram> list = ProjectUtil.getDiagrams(ownedElement, DiagramType.SEQUENCE_DIAGRAM);
                if (null != list && !list.isEmpty()) {
                    for (Diagram dig : list) {
                        if (!diagramList.contains(dig)) {
                            diagramList.add(dig);
                        }
                    }
                }
            }
            // 액티비티 다이어그램 가져오기
            // if (null != child) {
            // List<Diagram> list = ProjectUtil.getDiagrams(child,
            // DiagramType.ACTIVITY_DIAGRAM);
            // if (null != list && !list.isEmpty()) {
            // for(Diagram dig : list){
            // if(!diagramList.contains(dig)){
            // diagramList.add(dig);
            // }
            // }
            // }
            // ownedElementList.addAll(child.getOwnedElements());
            // }
            // for (Element ownedElement : ownedElementList) {
            // List<Diagram> list = ProjectUtil.getDiagrams(ownedElement,
            // DiagramType.ACTIVITY_DIAGRAM);
            // if (null != list && !list.isEmpty()) {
            // for(Diagram dig : list){
            // if(!diagramList.contains(dig)){
            // diagramList.add(dig);
            // }
            // }
            // }
            // }
        }

        DeleteInDiagramCommand diagramDelete = new DeleteInDiagramCommand(diagramList);

        // UML 요소 삭제
        Command deleteCommand;
        // EObject parent = null;
        CompoundCommand deleteCommandList = new CompoundCommand();
        deleteCommandList.add(diagramDelete);
        
        List<Object> uuidList = new ArrayList<Object>();
        
        for (EObject eobject : objList) {
            // UMLManager.clearStereotype((Element) eobject);
            // EcoreUtil.remove(eobject);
            deleteCommand = DeleteUMLElementCommandFactory.getCommand((Element) eobject);
            if (null == deleteCommand) {
                continue;
            } else {
                deleteCommandList.add(deleteCommand);
                ProjectRegistry.UMLTreeNodeRegistry.removeTreeNode(eobject);
            }
            uuidList.add(EcoreUtil.getURI((EObject) eobject).fragment());
        }
        if (0 == deleteCommandList.getCommands().size()) {
            return;
        }
        ((UMLDiagramCommandStack) DomainRegistry.getUMLDomain().getCommandStackListener()).execute(deleteCommandList);
        
        if (!LTKLauncher.openDeleteWizard(selection)) {
            IWorkbenchPage[] pages = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPages();
            for (IWorkbenchPage page : pages) {
                IEditorPart[] editors = page.getEditors();
                for (IEditorPart editor : editors) {
                    if (editor instanceof AbstractDiagramEditor) {
                        AbstractDiagramEditor diagramEditor = (AbstractDiagramEditor) editor;
                        if (diagramEditor.getEditorInput() instanceof DiagramEditorInput) {
                            DiagramEditorInput editorInput = (DiagramEditorInput) diagramEditor.getEditorInput();
                            Diagram diagram = editorInput.getDiagram();
                            if (diagramList.contains(diagram)) {
                                diagramEditor.closeEditor(false);
                            }
                        }
                    }
                }
            }
            return;
        }
        
        // TODO 연계 데이터 삭제 처리
        // 연계데이터 삭제하시겠습니까? 메세지 후 삭제
//        try {
//            NCPMetaContentServiceUtil.deleteResources(uuidList);
//        } catch (ConnectException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
    
    /**
     * isFragment
     */
    private boolean isFragment = false;

    /**
     * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
     *      org.eclipse.jface.viewers.ISelection)
     */
    public void selectionChanged(IAction action, ISelection sel) {

        objList.clear();
        selection = null;
        if (!(sel instanceof IStructuredSelection)) {
            return;
        }

        isFragment = false;

        List<ITreeNode> nodeList = new ArrayList<ITreeNode>();
        List<IResource> resourceList = new ArrayList<IResource>();

        IStructuredSelection select = (IStructuredSelection) sel;
        Object obj;
        for (Iterator<?> iterator = select.iterator(); iterator.hasNext();) {
            obj = (Object) iterator.next();
            if (obj instanceof UMLFileTreeNode) {
                UMLFileTreeNode node = (UMLFileTreeNode) obj;

                IResource resource = node.getResource();
                resourceList.add(resource);
                if (node.isFragmented()) {
                    isFragment = true;
                }
                // 2011-04-19 add nspark
                // 단편화된 모델일 경우 단편화 파일까지 삭제 대상에 추가
                childFragment(resourceList, node.getEObject());

            } else if (obj instanceof ITreeNode) {
                ITreeNode node = (ITreeNode) obj;
                EObject eobject = node.getEObject();
                // 프로파일과 라이브러리는 삭제불가
                if (eobject instanceof ProfileApplication || eobject instanceof PackageImport) {
                	hasNotDeleted = true;
				}
                childFragment(resourceList, node.getEObject());
                nodeList.add((ITreeNode) obj);
            }
        }
        // for(Object o : resourceList){
        // System.out.println(o);
        // }
        selection = new StructuredSelection(resourceList.toArray());
        objList = ProjectUtil.getSelectedEObjects(new StructuredSelection(nodeList.toArray()));

        if (objList == null || objList.size() < 1) {
            return;
        }

        for (EObject eobject : objList) {
            if (eobject instanceof Abstraction || eobject instanceof Association
                || eobject instanceof BehaviorExecutionSpecification || eobject instanceof CombinedFragment
                || eobject instanceof Comment || eobject instanceof Connector || eobject instanceof ControlFlow
                || eobject instanceof Dependency || eobject instanceof ExecutionEvent
                || eobject instanceof ExecutionOccurrenceSpecification || eobject instanceof Extend
                || eobject instanceof Generalization || eobject instanceof Include
                || eobject instanceof InteractionOperand || eobject instanceof InterfaceRealization
                || eobject instanceof MessageOccurrenceSpecification
                || eobject instanceof ObjectFlow || eobject instanceof Realization
                || eobject instanceof ReceiveOperationEvent || eobject instanceof SendOperationEvent
                || eobject instanceof Usage) {
                objList.clear();
                selection = null;
                return;
            }
        }

        // EObject eobj = objList.get(0);
        // EObject parent = null;
        // if( eobj instanceof Diagram ) {
        // parent = ((Diagram)eobj).getParent();
        // } else {
        // parent = eobj.eContainer();
        // }
        // FocusRegistry.setFocused(parent);
    }

    /**
     * UML_FILE_TREE_NODE_TYPE
     */
    public static final int UML_FILE_TREE_NODE_TYPE = 0;

    /**
     * UML_TREE_NODE_TYPE
     */
    public static final int UML_TREE_NODE_TYPE = 1;

    /**
     * childFragment
     *  
     * @param resourceList
     * @param eObject void
     */
    public void childFragment(List<IResource> resourceList, EObject eObject) {

        if (eObject instanceof Model) {
            EList<PackageableElement> packageableElements = ((Model) eObject).getPackagedElements();
            for (PackageableElement packageableElement : packageableElements) {
                if (packageableElement != null && packageableElement.eResource() != null) {
                    IFile fragmentResource = null;
                    try {
                        fragmentResource = WorkspaceSynchronizer.getFile(packageableElement.eResource());
                        if (fragmentResource != null && fragmentResource.exists()) {
                            if (!resourceList.contains(fragmentResource)) {
                                resourceList.add(fragmentResource);
                            }
                            childFragment(resourceList, packageableElement);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } else if (eObject instanceof Package) {
            if (eObject != null && eObject.eResource() != null) {
                IFile fragmentResource = null;
                try {
                    fragmentResource = WorkspaceSynchronizer.getFile(eObject.eResource());
                    if (fragmentResource != null && fragmentResource.exists()) {
                        if (!resourceList.contains(fragmentResource)) {
                            // resourceList.add(fragmentResource);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        EAnnotation fragmentAnnotation = ResourceManager.getInstance().getFragmentAnnotation(eObject);
        if (fragmentAnnotation == null)
            return;
        EList<EObject> references = fragmentAnnotation.getReferences();
        for (EObject reference : references) {
            if (reference != null && reference.eResource() != null) {
                IFile fragmentResource = null;
                try {
                    fragmentResource = WorkspaceSynchronizer.getFile(reference.eResource());
                    if (fragmentResource != null && fragmentResource.exists()) {
                        if (!resourceList.contains(fragmentResource)) {
                            resourceList.add(fragmentResource);
                        }

                        childFragment(resourceList, reference);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction,
     *      org.eclipse.ui.IWorkbenchPart)
     */
    public void setActivePart(IAction action, IWorkbenchPart targetPart) {
        // TODO Auto-generated method stub

    }

    /**
     * 경구씨가 한 거 따로 뺐음. 시퀀스 다이어그램 내부의 Element를 삭제하는 듯?
     * <ul>
     * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
     * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.action</li>
     * <li>설 명 : DeleteInDiagramCommand</li>
     * <li>작성일 : 2011. 8. 10.</li>
     * <li>작성자 : 황선림</li>
     * </ul>
     */
    public class DeleteInDiagramCommand extends Command {

        private List<Diagram> diagramList;

        /**
         * @param diagramList
         */
        public DeleteInDiagramCommand(List<Diagram> diagramList) {
            this.diagramList = diagramList;
        }

        /**
         * @see org.eclipse.gef.commands.Command#execute()
         */
        @Override
        public void execute() {
            List<Element> deleteList = new ArrayList<Element>();
            for (Diagram eobject : diagramList) {
                if (eobject instanceof Diagram) {
                    Diagram dig = (Diagram) eobject;

                    if (dig.getParent() instanceof Interaction) {
                        Interaction interaction = (Interaction) dig.getParent();

                        List<Lifeline> lifeLineList = interaction.getLifelines();

                        List<Element> ownedElements = interaction.getOwnedElements();
                        for (Element ownedElement : ownedElements) {
                            if (!deleteList.contains(ownedElement)) {
                                deleteList.add(ownedElement);
                            }
                        }

                        ConnectableElement connectableElement;
                        for (Lifeline lifeLine : lifeLineList) {
                            connectableElement = lifeLine.getRepresents();
                            deleteList.add(connectableElement);
                        }

                        List<InteractionFragment> fragmentList = interaction.getFragments();
                        for (InteractionFragment interactionFragment : fragmentList) {
                            if (!deleteList.contains(interactionFragment)) {
                                deleteList.add(interactionFragment);
                            }
                        }

                        Event messageEvent;
                        for (InteractionFragment fragment : fragmentList) {
                            if (fragment instanceof MessageOccurrenceSpecification) {
                                messageEvent = ((MessageOccurrenceSpecification) fragment).getEvent();
                                deleteList.add(messageEvent);
                            }
                        }
                    }

                    if (dig.getParent() instanceof Activity) {
                        EList<AbstractNode> childNodeList = dig.getNodeList();
                        EList<AbstractConnection> childConnectionList = dig.getConnectionList();
                        for (AbstractConnection connection : childConnectionList) {
                            if (!deleteList.contains(connection.getUmlModel())) {
                                deleteList.add(connection.getUmlModel());
                            }
                        }
                        for (AbstractNode node : childNodeList) {
                            if (!deleteList.contains(node.getUmlModel())) {
                                deleteList.add(node.getUmlModel());
                            }
                        }
                    }
                }
            }
            // objList.addAll(deleteList);

            List<AbstractView> viewList = new ArrayList<AbstractView>();
            for (EObject eobject : deleteList) {
                if (eobject instanceof Element) {
                    Element node = (Element) eobject;
                    for (AbstractView view : UMLManager.getRelatedViewModel(node)) {
                        if (!viewList.contains(view)) {
                            viewList.add(view);
                        }
                    }
                }
            }

            // ///////////
            for (AbstractView view : viewList) {
                EcoreUtil.remove(view);
            }
            for (EObject eobject : deleteList) {
            	UMLManager.deleteElement(eobject);
            }
            // 시퀀스다이어그램 삭제 다이어그램 내부 Element를 같이
            // 삭제한다.//////////////////////////////////////////////////
            // /////////////////////////////////////////////////////////////////////////////////////////////////////

        }
    }

}
