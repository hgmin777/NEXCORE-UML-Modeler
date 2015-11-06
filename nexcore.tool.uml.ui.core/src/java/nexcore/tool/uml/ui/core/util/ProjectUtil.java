/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.core.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import nexcore.alm.common.ui.util.ALMLogger;
import nexcore.tool.mdd.core.extension.IDomainModelHandler;
import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.core.util.StringUtil;
import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.manager.utility.DomainModelHandlerUtil;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.model.projectinformation.ProjectElement;
import nexcore.tool.uml.model.projectinformation.ProjectInformationFactory;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.DiagramType;
import nexcore.tool.uml.model.umldiagram.LifeLineNode;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.model.usecasedisplayId.UseCaseDisplayId;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.diagram.editor.AbstractDiagramEditor;
import nexcore.tool.uml.ui.core.diagram.editor.DiagramEditorInput;
import nexcore.tool.uml.ui.core.editor.input.UMLEditorInput;
import nexcore.tool.uml.ui.core.project.ITreeNode;
import nexcore.tool.uml.ui.core.project.UMLFileTreeNode;
import nexcore.tool.uml.ui.core.project.UMLTreeNode;
import nexcore.tool.uml.ui.core.registry.ProjectRegistry;
import nexcore.tool.uml.ui.core.registry.ProjectRegistry.UMLTreeNodeRegistry;
import nexcore.tool.uml.ui.core.registry.ProjectRegistry.ViewerRegistry;

import org.eclipse.core.internal.resources.Workspace;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.change.util.ChangeRecorder;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.query.conditions.eobjects.EObjectTypeRelationCondition;
import org.eclipse.emf.query.statements.FROM;
import org.eclipse.emf.query.statements.IQueryResult;
import org.eclipse.emf.query.statements.SELECT;
import org.eclipse.emf.query.statements.WHERE;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.RootEditPart;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.IBasicPropertyConstants;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ActivityPartition;
import org.eclipse.uml2.uml.Artifact;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.CollaborationUse;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.Extension;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.MessageOccurrenceSpecification;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ProfileApplication;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.ReceiveOperationEvent;
import org.eclipse.uml2.uml.Reception;
import org.eclipse.uml2.uml.SendOperationEvent;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.UseCase;
import org.eclipse.uml2.uml.util.UMLUtil;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.util</li>
 * <li>설 명 : ProjectUtil</li>
 * <li>작성일 : 2010. 1. 4.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
@SuppressWarnings("restriction")
public class ProjectUtil {

    /**
     * setSelectonNodeInExplorer
     *  
     * @param target void
     */
    public static void setSelectonNodeInExplorer(EObject target) {
        if (null == target) {
            return;
        }
        if (target instanceof EAnnotation) {
            return;
        }
        CommonViewer commonViewer = ViewerRegistry.getViewer();
        if (commonViewer.getControl().isDisposed()) {
            return;
        }
        ITreeNode targetNode = null;
        targetNode = UMLTreeNodeRegistry.getTreeNode(target);
        if (null != targetNode) {
            commonViewer.setSelection(new StructuredSelection(targetNode), true);
        }
    }

    /**
     * refreshNodeInExplorer
     *  
     * @param target void
     */
    public static void refreshNodeInExplorer(EObject target) {
        if (target instanceof EAnnotation) {
            return;
        }
        if (null == target) {
            return;
        }
        CommonViewer commonViewer = ViewerRegistry.getViewer();
        if (commonViewer.getControl().isDisposed()) {
            return;
        }

        ISelection selection = commonViewer.getSelection();
        TreePath[] expanedTreePaths = TreeItemUtil.getExpandTreePaths(commonViewer.getTree()).clone();

        /*
         * stereotype인 경우 전달 객체가 DynamicEobjectImpl이므로 할당된 클래스를 계산하여 처리해야 함.
         */
        if (target instanceof DynamicEObjectImpl) {
            target = UMLUtil.getBaseElement(target);
        }
        ITreeNode targetNode = null;
        targetNode = UMLTreeNodeRegistry.getTreeNode(target);

        if (null != targetNode) {
            commonViewer.refresh(targetNode);
            TreeItemUtil.expandTreePath(expanedTreePaths, selection);
        }
    }

    /**
     * updateParentInExplorer
     *  
     * @param target void
     */
    public static void updateParentInExplorer(EObject target) {
        if (null == target) {
            return;
        }
        CommonViewer commonViewer = ViewerRegistry.getViewer();
        if (commonViewer.getControl().isDisposed()) {
            return;
        }
        if (target instanceof DynamicEObjectImpl) {
            target = UMLUtil.getBaseElement(target);
        }
        ITreeNode targetNode = null;
        EObject parent = UMLManager.getParent((Element) target);
        targetNode = UMLTreeNodeRegistry.getTreeNode(parent);
        String[] flags = null;
        flags = new String[] { IBasicPropertyConstants.P_IMAGE, IBasicPropertyConstants.P_TEXT,
            IBasicPropertyConstants.P_CHILDREN };
        commonViewer.update(targetNode, flags);
    }

    /**
     * updateExplorer
     *  
     * @param target
     * @param childIncluded void
     */
    public static void updateExplorer(EObject target, boolean childIncluded) {
        if (null == target) {
            return;
        }
        if (target instanceof EAnnotation) {
            return;
        }
        CommonViewer commonViewer = ViewerRegistry.getViewer();
        if (commonViewer.getControl().isDisposed()) {
            return;
        }
        if (target instanceof DynamicEObjectImpl) {
            target = UMLUtil.getBaseElement(target);
        }
        ITreeNode targetNode = null;
        targetNode = UMLTreeNodeRegistry.getTreeNode(target);
        if (null == targetNode) {
            return;
        }
        String[] flags = null;
        if (childIncluded) {
            flags = new String[] { IBasicPropertyConstants.P_IMAGE, IBasicPropertyConstants.P_TEXT,
                IBasicPropertyConstants.P_CHILDREN };
        } else {
            flags = new String[] { IBasicPropertyConstants.P_IMAGE, IBasicPropertyConstants.P_TEXT };
        }
        try {
            commonViewer.update(targetNode, flags);
            commonViewer.refresh(targetNode);
        } catch (Exception ex) {
            Log.error(ex);
        }
    }

    /**
     * refreshExplorer
     *   void
     */
    public static void refreshExplorer() {
        CommonViewer commonViewer = ViewerRegistry.getViewer();
        if (commonViewer.getControl().isDisposed()) {
            return;
        }

        ISelection selection = commonViewer.getSelection();
        if ((null == selection) || (!(selection instanceof IStructuredSelection))) {
            return;
        }

        Object object = ((IStructuredSelection) selection).getFirstElement();
        if (object instanceof ITreeNode) {
            ITreeNode node = (ITreeNode) object;
            if (node instanceof UMLFileTreeNode) {
                ((UMLFileTreeNode) node).refreshNode();
            }
            commonViewer.refresh(node);
        }

        // TreeSelection treeSelection = (TreeSelection) selection;
        // for (TreePath treePath : treeSelection.getPaths()) {
        // commonViewer.refresh(treePath);
        // }

    }

    /**
     * refreshWholeExplorer
     *   void
     */
    public static synchronized void refreshWholeExplorer() {
        try {
            ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
        } catch (CoreException e) {
            Log.error(e);
        }

        CommonViewer commonViewer = ViewerRegistry.getViewer();
        if (commonViewer != null && !commonViewer.isBusy()) {
            commonViewer.refresh();
        }
    }

    /**
     * refreshSelectionNode
     *   void
     */
    public static void refreshSelectionNode() {
        CommonViewer commonViewer = ViewerRegistry.getViewer();
        if (commonViewer != null) {
            ISelection selection = commonViewer.getSelection();

            try {
                if (selection != null && selection instanceof TreeSelection) {
                    TreePath[] treePaths = ((TreeSelection) selection).getPaths();

                    if (treePaths != null && treePaths.length > 0) {

                        try {
                            for (TreePath treePath : treePaths) {
                                int cnt = treePath.getSegmentCount();
                                if (cnt > 1) {
                                    Object o = treePath.getSegment(cnt - 1);
                                    commonViewer.refresh(o, true);
                                }
                            }
                        } catch (Exception e) {
                            Log.error(e);
                        }
                    }
                }
            } catch (Exception e) {
                Log.error(e);
            }
        }
    }

    /**
     * refresh project explorer. If the first parameter and the second parameter
     * is the same thing, do not expand the tree.
     * 
     * @param eobject
     * @param eobject2
     */
    // public static void refreshExplorer(EObject parent, EObject child) {
    //
    // CommonViewer commonViewer = ViewerRegistry.getViewer();
    // if (commonViewer.getControl().isDisposed()) {
    // return;
    // }
    // // // Object[] arr = commonViewer.getExpandedElements();
    // // Object childNode = UMLTreeNodeRegistry.getTreeNode(child);
    // // Object parentNode = UMLTreeNodeRegistry.getTreeNode(parent);
    // // commonViewer.update(childNode,new
    // //
    // String[]{IBasicPropertyConstants.P_IMAGE,IBasicPropertyConstants.P_TEXT,IBasicPropertyConstants.P_CHILDREN,IBasicPropertyConstants.P_CHILDREN});
    // // if(child instanceof Model){
    // // commonViewer.update(childNode,new
    // //
    // String[]{IBasicPropertyConstants.P_IMAGE,IBasicPropertyConstants.P_TEXT,IBasicPropertyConstants.P_CHILDREN,IBasicPropertyConstants.P_CHILDREN});
    // // }else{
    // // commonViewer.refresh();
    // // }
    // // commonViewer.update(parentNode,new
    // //
    // String[]{IBasicPropertyConstants.P_IMAGE,IBasicPropertyConstants.P_TEXT});
    // // commonViewer.update(childNode,new
    // //
    // String[]{IBasicPropertyConstants.P_IMAGE,IBasicPropertyConstants.P_TEXT,IBasicPropertyConstants.P_CHILDREN,IBasicPropertyConstants.P_PARENT});
    // // commonViewer.refresh(childNode);
    // // commonViewer.setExpandedElements(arr);
    // // if (parentNode != null && !parent.equals(child)) {
    // // expandTree(commonViewer, getParentsOfChild(parent, child));
    // // }
    //
    // // ITreeNode childNode = UMLTreeNodeRegistry.getTreeNode(child);
    // // if (childNode != null) {
    // // commonViewer.setSelection(new StructuredSelection(childNode));
    // // }
    // // commonViewer.setSelection(new
    // //
    // StructuredSelection(UMLTreeNodeRegistry.getTreeNode(FocusRegistry.getFocused())));
    // // System.out.println("refresh");
    // }
    public static void expandTree(EObject target) {
        if (null == target) {
            return;
        }
        CommonViewer viewer = ViewerRegistry.getViewer();
        ITreeNode targetNode = null;
        targetNode = UMLTreeNodeRegistry.getTreeNode(target);
        if (null != targetNode) {
            viewer.expandToLevel(targetNode, 1);
        }
    }

    /**
     * getUniqueFileName
     *  
     * @param containerFullPath
     * @param fileName
     * @param extension
     * @return String
     */
    public static String getUniqueFileName(IPath containerFullPath, String fileName, String extension) {
        if (containerFullPath == null) {
            containerFullPath = new Path(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING);
        }
        if (fileName == null || fileName.trim().length() == 0) {
            fileName = UMLMessage.LABEL_CREATE_UML_MODEL_DEFAULTNAME;
        }
        IPath filePath = containerFullPath.append(fileName);
        if (extension != null && !extension.equals(filePath.getFileExtension())) {
            filePath = filePath.addFileExtension(extension);
        }
        extension = filePath.getFileExtension();
        fileName = filePath.removeFileExtension().lastSegment();
        int i = 1;
        while (ResourcesPlugin.getWorkspace().getRoot().exists(filePath)) {
            i++;
            filePath = containerFullPath.append(fileName + i);
            if (extension != null) {
                filePath = filePath.addFileExtension(extension);
            }
        }
        return filePath.lastSegment();
    }
    
    /**
     * getUniqueFileName
     *  
     * @param filePath
     * @return String
     */
    public static String getUniqueFileName(IPath filePath) {
        String extension = filePath.getFileExtension();
        String fileName = filePath.removeFileExtension().lastSegment();
        IPath parentPath = filePath.removeLastSegments(1);
        int i = 1;
        while (ResourcesPlugin.getWorkspace().getRoot().exists(filePath)) {
            i++;
            filePath = parentPath.append(fileName + i);
            if (extension != null) {
                filePath = filePath.addFileExtension(extension);
            }
        }
        return filePath.toString();
    }

    /**
     * NamedElement에 적용된 스테레오 타입이나 키워드 텍스트를 리턴한다.
     * 
     * @param namedElement
     * @return String
     */
    public static String getStereotypeText(Element element) {

        String text = UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;
        if (element == null) {
            return text;
        }

        EList<String> keywordsList = element.getKeywords();
        EList<Stereotype> stereotypeList = element.getAppliedStereotypes();
        ArrayList<String> typeName = new ArrayList<String>();

        for (int i = 0; i < keywordsList.size(); i++)
            typeName.add(keywordsList.get(i));
        for (int i = 0; i < stereotypeList.size(); i++)
            typeName.add(stereotypeList.get(i).getName());

        if (typeName.size() != 0) {
            for (int i = 0; i < typeName.size(); i++) {
                if (i != 0)
                    text = text + UICoreConstant.PROJECT_CONSTANTS__COMMA;
                text = text + typeName.get(i);
            }
        }

        return text;
    }
    
    /**
     * Element에 적용된 스테레오타입을 텍스트로 반환한다.
     * 
     * @param element
     * @return String
     */
    public static String getAppliedStereotypes(Element element) {
        if (element == null) {
            return null;
        }
        EList<Stereotype> appliedStereotypes = element.getAppliedStereotypes();
        Stereotype stereotype = null;
        if (appliedStereotypes != null) {
            StringBuffer stringBuffer = new StringBuffer();

            int sequence = 0;
            for (Iterator<Stereotype> iter = appliedStereotypes.iterator(); iter.hasNext();) {
                stereotype = iter.next();
                if (sequence > 0) {
                    stringBuffer.append(UICoreConstant.PROJECT_CONSTANTS__COMMA);
                }
                stringBuffer.append(stereotype.getName().trim());
                sequence++;

            }
            return stringBuffer.toString();
        }
        return UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;

    }

    /**
     * NamedElement에 적용된 스테레오 타입이나 키워드의 Label을 리턴한다.
     * 
     * @param namedElement
     * @return String
     */
    public static String getStereotypeLabel(Element element) {
        String stereotype = getStereotypeText(element);
        if (UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING.equals(stereotype)) {
            return stereotype;
        }

        String text = UICoreConstant.PROJECT_CONSTANTS__FIRST_BRACKET + getStereotypeText(element)
            + UICoreConstant.PROJECT_CONSTANTS__LAST_BRACKET + UICoreConstant.PROJECT_CONSTANTS__BLANK;
        return text;
    }

    /**
     * Initialize UML Domain.
     * 
     * @return UMLDomain
     */
    // public static UMLDomain createDomain() {
    public static IDomainModelHandler createDomain() {
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        IProject projects[] = root.getProjects();
        // UMLDomain umlDomain = new UMLDomain();
        IDomainModelHandler umlDomain = DomainModelHandlerUtil.getUMLDomain();
        try {
            for (int i = 0; i < projects.length; i++) {
                // if the project has a UML nature, register the project to
                // resource.
                if (projects[i].isOpen()) {
                    if (projects[i].hasNature(UICoreConstant.PROJECT_CONSTANTS__NATURE_ID)) {
                        loadFile(umlDomain, projects[i].members());
                    }
                }
            }
            DomainRegistry.setUMLDomain(umlDomain);
        } catch (CoreException e) {
            Log.error(e);
        }

        // 선행 초기화 작업 실행
        // PrecedingInitializerRegistry.getInstance().executeInitializer();

        return umlDomain;

    }

    /**
     * isUMLPerspective
     *  
     * @return boolean
     */
    public static boolean isUMLPerspective() {
        IWorkbench workbench = PlatformUI.getWorkbench();

        IWorkbenchPage activePage = null;
        try {
            activePage = workbench.getActiveWorkbenchWindow().getActivePage();

            if (activePage != null) {
                if (UICoreConstant.PROJECT_CONSTANTS__UmlPerspectiveID.equals(activePage.getPerspective().getId())) {
                    return true;
                }
            }
        } catch (Exception e1) {}

        return false;
        // for (IWorkbenchWindow window : workbench.getWorkbenchWindows()) {
        // for (IWorkbenchPage page : window.getPages()) {
        // if( activePage != null){
        // page = activePage;
        // }
        // }
        // }
    }

    /**
     * load model files to UMLDomain.
     * 
     * @param umlDomain
     * @param file
     *            void
     */
    // private static void loadFile(UMLDomain umlDomain, IResource[] resources)
    // {
    private static void loadFile(IDomainModelHandler umlDomain, IResource[] resources) {
        for (int i = 0; i < resources.length; i++) {
            if (resources[i] instanceof IFolder) {
                try {
                    loadFile(umlDomain, ((IFolder) resources[i]).members());
                } catch (CoreException e) {
                    Log.error(e);
                }

            } else if (resources[i] instanceof IFile) {
                IFile file = (IFile) resources[i];
                if (UICoreConstant.PROJECT_CONSTANTS__FILE_EXTENSION.equals(file.getFileExtension())) {
                    DomainModelHandlerUtil.load(URI.createURI(file.getFullPath().toString()));
                }
            }
        }

    }

    /**
     * Initialize UML Domain.
     * 
     * @param addTracker
     *            if true, adds modification tracker to all resolved resources
     *            in UMLDomain.
     * @return UMLDomain
     */
    // public static UMLDomain createDomain(boolean addTracker) {
    /**
     * Initialize UML Domain.
     * 
     * @param addTracker
     *            if true, adds modification tracker to all resolved resources
     *            in UMLDomain.
     * @return IDomainModelHandler
     */
    public static IDomainModelHandler createDomain(boolean addTracker) {
        IDomainModelHandler umlDomain = createDomain();

        if (addTracker) {
            addResourceTracker(umlDomain);
        }

        return umlDomain;
    }

    /**
     * adds modification tracker to all the resources in UMLDomain.
     * 
     * @param umlDomain
     *            void
     */
    // private static void addResourceTracker(UMLDomain umlDomain) {
    /**
     * adds modification tracker to all the resources in UMLDomain.
     * 
     * @param umlDomain
     *            void
     */
    private static void addResourceTracker(IDomainModelHandler umlDomain) {
        // ResourceSet rs = umlDomain.getResourceSet();
        // EcoreUtil.resolveAll(rs);
        // EList<Resource> list = rs.getResources();
        EList<Resource> list = umlDomain.getResourceSet().getResources();

        for (Resource resource : list) {
            if(!resource.isTrackingModification()){
                resource.setTrackingModification(true);
            }
        }
    }

    /**
     * Create glossary file
     * 
     * @param uri
     * @param monitor
     * @return Resource
     */
    public static Resource createGlossary(URI uri, IProgressMonitor monitor) {
        monitor.beginTask("CreateGlossary", 3); //$NON-NLS-1$

        // UMLDomain umlDomain = DomainRegistry.getUMLDomain();
        IDomainModelHandler umlDomain = DomainRegistry.getUMLDomain();
        if (umlDomain == null) {
            umlDomain = ProjectUtil.createDomain(true);
        }

        IFile file = null;
        Resource resource = null;
        try {
            resource = DomainModelHandlerUtil.createUMLGlossary(uri);
            resource.save(DomainUtil.getSaveOptions());
            file = WorkspaceSynchronizer.getFile(resource);
            file.setCharset(UICoreConstant.PROJECT_CONSTANTS__ENCODING_UTF8, new NullProgressMonitor());
        } catch (Exception e) {
            Log.error(e);
            ALMLogger.getLog(UiCorePlugin.PLUGIN_ID).error(e.getMessage(), e);
        }

        return resource;
    }

    /**
     * Create model file.
     * 
     * @param uri
     * @param monitor
     * @return Resource
     */
    public static Resource createModel(String modelName, URI uri, IProgressMonitor monitor)  throws Exception {
        monitor.beginTask("CreateModel", 3); //$NON-NLS-1$

        // UMLDomain umlDomain = DomainRegistry.getUMLDomain();
        IDomainModelHandler umlDomain = DomainRegistry.getUMLDomain();
        if (umlDomain == null) {
            umlDomain = ProjectUtil.createDomain(true);
        }

        // Resource resource = umlDomain.getResourceSet().createResource(uri);
        // Model model = UMLHelper.createModel(UMLMessage.LABEL_MODEL);
        // resource.getContents().add(model);

        // Resource resource = umlDomain.createUMLModelRoot(uri, modelName);
        Resource resource = null;
        IFile file = null;
        try {
            resource = DomainModelHandlerUtil.createUMLModelRoot(uri, modelName);
            resource.save(DomainUtil.getSaveOptions());
            file = WorkspaceSynchronizer.getFile(resource);
            file.setCharset(UICoreConstant.PROJECT_CONSTANTS__ENCODING_UTF8, monitor);
            if(!resource.isTrackingModification()){
                resource.setTrackingModification(true);
            }
        } catch (Exception e) {
            Log.error(e);
            ALMLogger.getLog(UiCorePlugin.PLUGIN_ID).error(e.getMessage(), e);
            throw e;
        }

        // String path = StringUtil.remove(file.getFullPath().toString(),
        // UICoreConstant.PROJECT_CONSTANTS__SLASH + file.getName());
        // ProjectRegistry.ModelFileRegistry.setFile(path, file);
        return resource;
    }

    // /**
    // * Create model file.
    // *
    // * @param uri
    // * @param monitor
    // * @return Resource
    // */
    // public static Resource createModel(URI uri, IProgressMonitor monitor) {
    // return createModel(UMLMessage.LABEL_MODEL, uri, monitor);
    // }

    /**
     * 
     * 
     * @param eobject
     *            void
     */
    public static void openDiagram(EObject eobject) {
        try {
            if (eobject instanceof Diagram) {
                Diagram diagram = (Diagram) eobject;

                // proxy resolving
                if (DomainUtil.isProxy(diagram)) {

                    URI uri = EcoreUtil.getURI(eobject);
//                    diagram = (Diagram) DomainRegistry.getUMLDomain().getResourceSet().getEObject(EcoreUtil.getURI(eobject), true);

                     diagram = (Diagram) EcoreUtil.resolve(diagram,
                     DomainRegistry.getEditingDomain().getResourceSet());
                     EcoreUtil.resolveAll(diagram);
                }

                Resource res = diagram.eResource();
                URI uri = res.getURI();
                DiagramEditorInput editorInput = new DiagramEditorInput(uri);
                editorInput.setDiagram(diagram);

                IWorkbenchPage workbenchPage = UiCorePlugin.getActivePage();
                if (DiagramType.USE_CASE_DIAGRAM.equals(diagram.getType())) {
                    workbenchPage.openEditor(editorInput, UICoreConstant.PROJECT_CONSTANTS__UsecaseEditorID, true);
                } else if (DiagramType.CLASS_DIAGRAM.equals(diagram.getType())) {
                    workbenchPage.openEditor(editorInput, UICoreConstant.PROJECT_CONSTANTS__ClassEditorID, true);
                } else if (DiagramType.SEQUENCE_DIAGRAM.equals(diagram.getType())) {
                    workbenchPage.openEditor(editorInput, UICoreConstant.PROJECT_CONSTANTS__SequenceEditorID, true);
                } else if (DiagramType.ACTIVITY_DIAGRAM.equals(diagram.getType())) {
                    workbenchPage.openEditor(editorInput, UICoreConstant.PROJECT_CONSTANTS__ActivityEditorID, true);
                } else if (DiagramType.COMPONENT_DIAGRAM.equals(diagram.getType())) {
                    workbenchPage.openEditor(editorInput, UICoreConstant.PROJECT_CONSTANTS__ComponentEditorID, true);
                }
            }
        } catch (PartInitException e) {
            Log.error(e);
        }
    }

    /**
     * returns selected EObject.
     * 
     * @param selection
     * @return EObject
     */
    public static EObject getSelectedEObject(ISelection selection) {
        EObject eobject = null;

        if (!(selection instanceof IStructuredSelection)) {
            return null;
        }

        Object object = ((IStructuredSelection) selection).getFirstElement();
        if (object instanceof ITreeNode) {
            ITreeNode node = (ITreeNode) object;
            eobject = node.getEObject();
        }

        return eobject;
    }

    /**
     * returns selected EObject list.
     * 
     * @param selection
     * @return List<EObject>
     */
    @SuppressWarnings("unchecked")
    public static List<EObject> getSelectedEObjects(ISelection selection) {
        List<EObject> result = new ArrayList<EObject>();

        if (!(selection instanceof IStructuredSelection)) {
            return result;
        }

        result.clear();
        IStructuredSelection sel = (IStructuredSelection) selection;
        Object obj = null;
        ITreeNode node = null;

        for (Iterator<Object> it = sel.iterator(); it.hasNext();) {
            obj = it.next();

            if (obj instanceof ITreeNode) {
                node = (ITreeNode) obj;
                result.add(node.getEObject());
            }
        }

        return result;
    }

    /**
     * 인자로 넘어온 UML 요소를 편집하는 편집기 열기
     * 
     * @param eobject
     *            void
     */
    public static void openEditor(EObject eobject) {
        try {
            if (eobject instanceof Model) {
                Model model = (Model) eobject;

                // 만약 프록시 객체라면 리졸빙해서 실 객체를 넘기기.
                if (DomainUtil.isProxy(model)) {
                    URI uri = EcoreUtil.getURI(model);
                    model = (Model) DomainRegistry.getUMLDomain().getResourceSet().getEObject(uri, true);
                    // model = (Model) EcoreUtil.resolve(model,
                    // model.eResource().getResourceSet());
                }

                UMLEditorInput editorInput = new UMLEditorInput(model);
                IWorkbenchPage workbenchPage = UiCorePlugin.getActivePage();

                workbenchPage.openEditor(editorInput, UICoreConstant.PROJECT_CONSTANTS__MODEL_EDITOR_ID, true);
            }
        } catch (PartInitException e) {
            Log.error(e);
        }
    }

    /**
     * MDA Designer, MDA Developer 룰 에디터를 연다.
     * 
     * @param obj
     * @param editorID
     *            void
     */
    public static void openMDAEditor(Object obj, String editorID) {
        MDAEditorInput editorInput = new MDAEditorInput((IFile) obj);
        IWorkbenchPage workbenchPage = UiCorePlugin.getActivePage();
        try {
            workbenchPage.openEditor(editorInput, editorID, false);
        } catch (PartInitException e) {
            Log.error(e);
        }
    }

    public static class MDAEditorInput extends FileEditorInput {
        private IFile iFile;

        public IFile getIFile() {
            return iFile;
        }

        public MDAEditorInput(IFile file) {
            super(file);
            iFile = file;
        }

        @Override
        public boolean equals(Object obj) {
            IFile file = null;
            if (obj instanceof MDAEditorInput) {
                file = ((MDAEditorInput) obj).getFile();
            } else if (obj instanceof FileEditorInput) {
                file = ((FileEditorInput) obj).getFile();
            }
            if (file != null) {
                if (iFile.equals(file)) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }
    }

    /**
     * elements를 parent의 하위로 이동
     * 
     * @param parent
     * @param elements
     *            EObject
     */
    public static void moveElements(EObject parent, Collection<Object> elements) {
        EObject eobject;
//        EAnnotation eAnnotation;
        Object object;
        Package _package;
        Component component;
        Class clazz;
        List<Element> relatedUMLModel;
        Activity activity;
        Collaboration collaboration;
        Artifact artifact;
        Enumeration enumeration;
        Interface interfaze;
        Signal signal;
        StringBuffer exceptedElementsStr = new StringBuffer(UICoreConstant.EMPTY_STRING);
        String exceptedDiagramStr = UICoreConstant.EMPTY_STRING;
        
        for (Iterator<Object> iterator = elements.iterator(); iterator.hasNext();) {
            object = iterator.next();
            if (object instanceof EObject) {
                eobject = (EObject) object;

                if (eobject == parent) {
                    continue;
                } else if (eobject instanceof Model) {
                    continue;
                } else if (eobject instanceof Diagram) {
                    continue;
                }

                if (parent instanceof Package) {
                    _package = (Package) parent;

                    if (eobject instanceof PackageableElement) {
                        if(eobject instanceof Interaction) {
                            if(!(_package instanceof Collaboration)) {
                                exceptedDiagramStr += ((PackageableElement)eobject).getName() +UICoreConstant.PROJECT_CONSTANTS__NEW_LINE +"Interaction 은 Collaboration 으로만 이동 할 수 있습니다.";
                                continue;
                            }
                        } else {
                            
                        }
                        continue;
                    }
                }
            }
        }
        
        if(exceptedDiagramStr == null) {
            // 다이어그램 move (다건의 다이어그램을 한꺼번에 이동 처리하기 위해 for 문에서 별도로 처리)
            exceptedDiagramStr = DiagramModeUtil.moveDiagram(elements, parent);
        }

        for (Iterator<Object> iterator = elements.iterator(); iterator.hasNext();) {
            object = iterator.next();
            if (object instanceof EObject) {
                eobject = (EObject) object;

                if (eobject == parent) {
                    continue;
                } else if (eobject instanceof Model) {
                    continue;
                } else if (eobject instanceof Diagram) {
                    continue;
                }

                // parent에 따른 분기
                if (parent instanceof Package) {
                    _package = (Package) parent;

                    if (eobject instanceof PackageableElement) {
                        try {
                            if(eobject instanceof Interaction) {
                                if(!(_package instanceof Collaboration)) {
                                    continue;
                                }
                            } else {
                                // 적용된 stereoType 정보 옮겨주기
                                EList<EObject> stereotypeApplications = ((PackageableElement) eobject).getStereotypeApplications();
                                _package.eResource().getContents().addAll(stereotypeApplications);
                                _package.getPackagedElements().add((PackageableElement) eobject);
                                
                                if(eobject instanceof Collaboration) {
                                    // 시퀀스 다이어그램의 event 도 함께 이동해야함.
                                    Collaboration cb = (Collaboration) eobject;
                                    EList<Element> ownedElements = cb.allOwnedElements();
                                    Set<Event> eventList = new HashSet<Event>();
                                    for (Element element : ownedElements) {
                                        if (element instanceof MessageOccurrenceSpecification) {
                                            MessageOccurrenceSpecification mos = (MessageOccurrenceSpecification) element;
                                            Event event = mos.getEvent();
                                            eventList.add(event);
                                        }
                                    }
                                    
                                    for (Event event : eventList) {
                                        _package.getPackagedElements().add(event);
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        continue;
                    }
                } else if (parent instanceof Component) {
                    component = (Component) parent;

                    if (eobject instanceof Package) {
                        component.getPackagedElements().add((Package) eobject);

                        continue;
                    } else if (eobject instanceof Property) { // Property, Port
                        component.getOwnedAttributes().add((Property) eobject);

                        continue;
                    } else if (eobject instanceof Operation) {
                        component.getOwnedOperations().add((Operation) eobject);

                        continue;
                    } else if (eobject instanceof Class) { // Class, Component
                        component.getPackagedElements().add((Class) eobject);

                        continue;
                    } else if (eobject instanceof Enumeration) {
                        component.getPackagedElements().add((Enumeration) eobject);

                        continue;
                    } else if (eobject instanceof Interface) {
                        component.getPackagedElements().add((Interface) eobject);

                        continue;
                    } else if (eobject instanceof DataType) {// DataType,
                        // PrimitiveType
                        component.getPackagedElements().add((DataType) eobject);

                        continue;
                    } else if (eobject instanceof Activity) {
                        component.getOwnedBehaviors().add((Activity) eobject);

                        continue;
                    } else if (eobject instanceof Collaboration) {
                        component.getPackagedElements().add((Collaboration) eobject);

                        continue;
                    } else if (eobject instanceof CollaborationUse) {
                        component.getCollaborationUses().add((CollaborationUse) eobject);

                        continue;
                    } else if (eobject instanceof Interaction) {
                        component.getOwnedBehaviors().add((Interaction) eobject);

                        continue;
                    }
                } else if (parent instanceof Class) {
                    clazz = (Class) parent;

                    if (eobject instanceof Property) { // Property, Port
                        clazz.getOwnedAttributes().add((Property) eobject);

                        continue;
                    } else if (eobject instanceof Operation) {
                        relatedUMLModel = UMLManager.getRelatedUMLModel((Element) eobject);

                        for (Element element : relatedUMLModel) {
                            if (element instanceof SendOperationEvent) {
                                ((SendOperationEvent) element).setOperation(null);
                            } else if (element instanceof ReceiveOperationEvent) {
                                ((ReceiveOperationEvent) element).setOperation(null);
                            }
                        }

                        clazz.getOwnedOperations().add((Operation) eobject);

                        continue;
                    } else if (eobject instanceof Reception) {
                        clazz.getOwnedReceptions().add((Reception) eobject);

                        continue;
                    } else if (eobject instanceof Class) {
                        clazz.getNestedClassifiers().add((Class) eobject);

                        continue;
                    } else if (eobject instanceof Enumeration) {
                        clazz.getNestedClassifiers().add((Enumeration) eobject);

                        continue;
                    } else if (eobject instanceof Interface) {
                        clazz.getNestedClassifiers().add((Interface) eobject);

                        continue;
                    } else if (eobject instanceof Activity) {
                        clazz.getOwnedBehaviors().add((Activity) eobject);

                        continue;
                    } else if (eobject instanceof CollaborationUse) {
                        clazz.getCollaborationUses().add((CollaborationUse) eobject);

                        continue;
                    } else if (eobject instanceof Interaction) {
                        clazz.getNestedClassifiers().add((Interaction) eobject);

                        continue;
                    }
                } else if (parent instanceof Activity) {
                    activity = (Activity) parent;

                    if (eobject instanceof ActivityPartition) {
                        activity.getGroups().add((ActivityPartition) eobject);

                        continue;
                    } else if (eobject instanceof Action) {
                        activity.getNodes().add((Action) eobject);

                        continue;
                    } else if (eobject instanceof ActivityNode) {
                        activity.getNodes().add((ActivityNode) eobject);

                        continue;
                    }
                } else if (parent instanceof Collaboration) {
                    collaboration = (Collaboration) parent;

                    if (eobject instanceof Property) {
                        collaboration.getOwnedAttributes().add((Property) eobject);

                        continue;
                    } else if (eobject instanceof CollaborationUse) {
                        collaboration.getCollaborationUses().add((CollaborationUse) eobject);

                        continue;
                    } else if (eobject instanceof Interaction) {
                        collaboration.getOwnedBehaviors().add((Interaction) eobject);

                        continue;
                    }
                } else if (parent instanceof Artifact) {
                    artifact = (Artifact) parent;

                    if (eobject instanceof Property) {
                        artifact.getOwnedAttributes().add((Property) eobject);

                        continue;
                    } else if (eobject instanceof Operation) {
                        artifact.getOwnedOperations().add((Operation) eobject);

                        continue;
                    } else if (eobject instanceof Artifact) {
                        artifact.getNestedArtifacts().add((Artifact) eobject);

                        continue;
                    }
                } else if (parent instanceof Enumeration) {
                    enumeration = (Enumeration) parent;

                    if (eobject instanceof EnumerationLiteral) {
                        enumeration.getOwnedLiterals().add((EnumerationLiteral) eobject);

                        continue;
                    } else if (eobject instanceof Property) {
                        enumeration.getOwnedAttributes().add((Property) eobject);

                        continue;
                    } else if (eobject instanceof Operation) {
                        enumeration.getOwnedOperations().add((Operation) eobject);

                        continue;
                    }
                } else if (parent instanceof Interface) {
                    interfaze = (Interface) parent;

                    if (eobject instanceof Property) {
                        interfaze.getOwnedAttributes().add((Property) eobject);

                        continue;
                    } else if (eobject instanceof Operation) {
                        interfaze.getOwnedOperations().add((Operation) eobject);

                        continue;
                    } else if (eobject instanceof Reception) {
                        interfaze.getOwnedReceptions().add((Reception) eobject);

                        continue;
                    } else if (eobject instanceof Class) {
                        interfaze.getNestedClassifiers().add((Class) eobject);

                        continue;
                    } else if (eobject instanceof Enumeration) {
                        interfaze.getNestedClassifiers().add((Enumeration) eobject);

                        continue;
                    } else if (eobject instanceof Collaboration) {
                        interfaze.getNestedClassifiers().add((Collaboration) eobject);

                        continue;
                    } else if (eobject instanceof CollaborationUse) {
                        interfaze.getCollaborationUses().add((CollaborationUse) eobject);

                        continue;
                    }

                } else if (parent instanceof Signal) {
                    signal = (Signal) parent;

                    if (eobject instanceof Property) {
                        signal.getOwnedAttributes().add((Property) eobject);

                        continue;
                    }
                } else if ( parent instanceof UseCase ) {
                    if(eobject instanceof Collaboration || eobject instanceof Interaction || eobject instanceof Diagram){
                        List<EObject> elementList = new ArrayList<EObject>();
                        
                        if( eobject instanceof Collaboration ) {
                            elementList.add(eobject);
                            
                            SELECT statement = new SELECT(new FROM(eobject), new WHERE(new EObjectTypeRelationCondition(UMLPackage.eINSTANCE.getInteraction())));
                            IQueryResult classList = statement.execute();

                            for (Iterator<?> ir = classList.iterator(); ir.hasNext();) {
                                Interaction eObject = (Interaction) ir.next();

                                if (!elementList.contains(eObject)) {
                                    elementList.add(eObject);
                                }
                            }
                            
                            statement = new SELECT(new FROM(eobject), new WHERE(new EObjectTypeRelationCondition(EcorePackage.eINSTANCE.getEAnnotation())));
                            classList = statement.execute();

                            for (Iterator<?> ir = classList.iterator(); ir.hasNext();) {
                                EAnnotation annotation = (EAnnotation) ir.next();
                                
                                if (ManagerConstant.UMLDOMAIN_CONSTANT__DIAGRAM_ANNOTATION_NAME.equals(annotation.getSource())) {
                                    for (EObject eobj : annotation.getContents()) {
                                        Diagram diagram = (Diagram) eobj;
                                        if (!elementList.contains(diagram)) {
                                            elementList.add(diagram);
                                        }
                                    }
                                }
                            }
                        } else if( eobject instanceof Interaction ) {
                            Interaction interaction = (Interaction ) eobject;
                            elementList.add(interaction);
                            
                            EObject eContainer = interaction.eContainer();
                            if( eContainer instanceof Collaboration ) {
                                elementList.add(eContainer);
                            }
                            
                            SELECT statement = new SELECT(new FROM(eobject), new WHERE(new EObjectTypeRelationCondition(EcorePackage.eINSTANCE.getEAnnotation())));
                            IQueryResult classList = statement.execute();

                            for (Iterator<?> ir = classList.iterator(); ir.hasNext();) {
                                EAnnotation annotation = (EAnnotation) ir.next();
                                
                                if (ManagerConstant.UMLDOMAIN_CONSTANT__DIAGRAM_ANNOTATION_NAME.equals(annotation.getSource())) {
                                    for (EObject eobj : annotation.getContents()) {
                                        Diagram diagram = (Diagram) eobj;
                                        if (!elementList.contains(diagram)) {
                                            elementList.add(diagram);
                                        }
                                    }
                                }
                            }
                        } else if (eobject instanceof Diagram) {
                            elementList.add(eobject);
                            EObject parentContainer = eobject.eContainer();
                            if (parentContainer instanceof EAnnotation) {
                                EObject interaction = parentContainer.eContainer();
                                if (interaction instanceof Interaction) {
                                    elementList.add(interaction);

                                    EObject collaboration2 = interaction.eContainer();
                                    if (collaboration2 instanceof Collaboration) {
                                        elementList.add(collaboration2);
                                    }
                                }
                            }
                        } else {
                            
                        }
//                        try {
//                            RelationManager.getInstance()
//                                .createRelationUsecaseToAnalysisClass((org.eclipse.uml2.uml.UseCase) parent,
//                                    elementList.toArray(new EObject[] {}));
//                        } catch (Exception e) {
//                            ALMLogger.getLog(UiCorePlugin.PLUGIN_ID).error(e.getMessage(), e);
//                            e.printStackTrace();
//                        }
                    } else if (eobject instanceof Class) {
//                        String message = String.format(UMLMessage.getMessage(UMLMessage.MESSAGE_CREATE_RELATION),((NamedElement)parent).getName(), ((NamedElement)eobject).getName());
//                        
//                        boolean result = MessageDialog.openConfirm(Display.getDefault().getActiveShell(), UMLMessage.getMessage(UMLMessage.LABEL_CREATE_RELATION)/*"연계 생성"*/, message);
                        
                        String source = elementInstanceName(parent);
                        String target = elementInstanceName(eobject);
                        
                        boolean result = MessageDialog.openConfirm(UiCorePlugin.getShell(), UMLMessage.MESSAGE_DIALOG_TITLE, 
                        		UMLMessage.getMessage(UMLMessage.MESSAGE_CREATE_NCP_RELATION, 
                        				((NamedElement)parent).getName(), ((NamedElement)eobject).getName(), source, target));
                        
                    	if( !result ) {
                            return;
                        }
                        
                        continue;
                    } 
                    continue;
                }

                // 위의 어느 경우에도 해당되지 않을 때, 메세지를 보여준다.
                if (eobject instanceof NamedElement) {
                    if (UICoreConstant.EMPTY_STRING.equals(exceptedElementsStr.toString())) {
                        exceptedElementsStr.append(((NamedElement) eobject).getName());
                    } else {
                        exceptedElementsStr.append(UICoreConstant.PROJECT_CONSTANTS__COMMA).append(((NamedElement) eobject).getName());
                    }
                }
            }
        }

        // 이동하지 못하는 요소에 대한 메세지를 보여준다.
        if (!UICoreConstant.EMPTY_STRING.equals(exceptedDiagramStr)
            || !UICoreConstant.EMPTY_STRING.equals(exceptedElementsStr.toString())) {
            if (!UICoreConstant.EMPTY_STRING.equals(exceptedDiagramStr)) {
            	exceptedDiagramStr = UMLMessage.getMessage(UMLMessage.MESSAGE_CANNOT_MOVE,
            			exceptedDiagramStr);
            }
            
            String errMessage = UICoreConstant.EMPTY_STRING;
            if (!UICoreConstant.EMPTY_STRING.equals(exceptedElementsStr.toString())) {
                errMessage = UMLMessage.getMessage(UMLMessage.MESSAGE_CANNOT_MOVE, exceptedElementsStr.toString());
                
                if (!UICoreConstant.EMPTY_STRING.equals(exceptedDiagramStr)) {
                    errMessage += UICoreConstant.PROJECT_CONSTANTS__NEW_LINE + exceptedDiagramStr;
                }
            }
            MessageDialog.openWarning(UiCorePlugin.getShell(),
                UMLMessage.LABEL_NEXCORE_UML_MODELER,
                errMessage + exceptedDiagramStr);
        }

    }

    /**
     * elementInstanceName
     *  
     * @param parent
     * @return String
     */
    public static String elementInstanceName(Object parent) {
        if(parent instanceof UseCase) {
            return UMLMessage.UML_USECASE;//"유스케이스";
        } else if(parent instanceof Component) {
            return UMLMessage.UML_COMPONENT;//"컴포넌트";
        } else if(parent instanceof Interface) {
            return UMLMessage.UML_INTERFACE;//"인터페이스";
        } else if(parent instanceof Class) {
            return UMLMessage.UML_CLASS;//"클래스";
        } else {
            return "";
        }
    }

    /**
     * elements를 parent의 하위로 붙여넣는다.
     * 
     * @param parent
     * @param elements
     *            EObject
     */
    public static void pasteElements(EObject parent, Collection<Object> elements) {
        // if( !(parent instanceof Package) ) {
        // return;
        // }

        EObject eobject = null;
        // EAnnotation eAnnotation;
        Object object = null;
        boolean hasDiagram = false;
        String exceptedElementsStr = UICoreConstant.EMPTY_STRING;

//        EObject tempEObject;
        for (Iterator<Object> iterator = elements.iterator(); iterator.hasNext();) {
            object = iterator.next();
            if (object instanceof EObject) {
                // //////////////////////////////////////////////////////
                // Association 연결에 따라 생성된 Property는 복사하지 않는다.
                // 추후에 연결선 복사 기능은 완성 시 삭제하도록 한다.
                List<Property> tempProperty = new ArrayList<Property>();
                if (object instanceof Class) {
                    for (Property childProperty : ((Class) object).getOwnedAttributes()) {
                        if (childProperty.getAssociation() != null) {
                            tempProperty.add(childProperty);
                        }
                    }
                    ((Class) object).getOwnedAttributes().removeAll(tempProperty);
                }
                // ///////////////////////////////////////////////////////
                eobject = EcoreUtil.copy((EObject) object);
                // //////////////////////////////////////////////////////
                // 노드 복사가 끝난 후엔 다시 Property를 추가해 준다.
                if (object instanceof Class) {
                    ((Class) object).getOwnedAttributes().addAll(tempProperty);
                }
                // //////////////////////////////////////////////////////
                // eobject = EcoreUtil.copy((EObject) object);

                // /////////////////////////////////////////////////////////////////////////
                // 스테레오 타입 복사
                // /////////////////////////////////////////////////////////
                // copyStereotype(parent, eobject, object);
                // /////////////////////////////////////////////////////////////////////////

                // 다이어그램은 복사되지 않도록 한다.
//                for (TreeIterator<EObject> iter = eobject.eAllContents(); iter.hasNext();) {
//                    tempEObject = iter.next();
//                    if (tempEObject instanceof Diagram) {
//                        hasDiagram = true;
//                        EcoreUtil.remove(tempEObject);
//                        // ((Diagram)
//                        // tempEObject).setId(UUID.randomUUID().toString());
//                    } else if (tempEObject instanceof EAnnotation) {
//                        EAnnotation annotation = (EAnnotation) tempEObject;
//                        if (UICoreConstant.PROJECT_CONSTANTS__FRAGMENTANNOTATION.equals(annotation.getSource())
//                            || ManagerConstant.UMLDOMAIN_CONSTANT__FRAGMENT_ANNOTATION_NAME.equals(annotation.getSource())
//                            || ManagerConstant.UMLDOMAIN_CONSTANT__FRAGMENT_CONTAINER_ANNOTATION_NAME.equals(annotation.getSource())) {
//                            EcoreUtil.remove(tempEObject);
//                        }
//                    }
//                }

                if (eobject == parent) {
                    continue;

                } else if (eobject instanceof Model) {
                    continue;

                } else if (eobject instanceof Diagram) {
                    hasDiagram = true;
                    continue;
                }

                for (EObject childObj : parent.eContents()) {
                    if (eobject instanceof NamedElement && childObj instanceof NamedElement) {
                        if (((NamedElement) eobject).getName().equals(((NamedElement) childObj).getName())) {
                            if (eobject.getClass().equals(childObj.getClass())) {
                                ((NamedElement) eobject).setName(getCopiedUniqueName((NamedElement) eobject,
                                    (Namespace) parent,
                                    ((NamedElement) eobject).getName()));
                            }
                        }
                    }

                }

                // parent에 따른 분기
                if (parent instanceof Package) {
                    Package _package = (Package) parent;
                    if (eobject instanceof PackageableElement) {
                        _package.getPackagedElements().add((PackageableElement) eobject);
                        copyStereotype(parent, eobject, object);
                        continue;

                        // } else if (eobject instanceof Diagram) {
                        // eAnnotation =
                        // _package.getEAnnotation(UICoreConstant.PROJECT_CONSTANTS__DIAGRAM);
                        // if (eAnnotation == null)
                        // eAnnotation =
                        // _package.createEAnnotation(UICoreConstant.PROJECT_CONSTANTS__DIAGRAM);
                        // ((Diagram)
                        // eobject).setId(UUID.randomUUID().toString());
                        // eAnnotation.getContents().add(eobject);
                        // ProjectUtil.refreshExplorer(_package, eobject);
                        // continue;

                        // } else if (eobject instanceof EAnnotation) {
                        // eAnnotation =
                        // _package.getEAnnotation(UICoreConstant.PROJECT_CONSTANTS__DIAGRAM);
                        // if (eAnnotation == null)
                        // _package.getEAnnotations().add((EAnnotation)
                        // eobject);
                        // // ProjectUtil.refreshExplorer(_package, eobject);
                        // continue;
                    }

                } else if (parent instanceof Operation) {
                    Operation operation = (Operation) parent;
                    if (eobject instanceof Parameter) {
                        operation.getOwnedParameters().add((Parameter) eobject);
                        copyStereotype(parent, eobject, object);
                        continue;
                    }

                } else if (parent instanceof Component) {
                    Component component = (Component) parent;
                    if (eobject instanceof Package) {
                        component.getPackagedElements().add((Package) eobject);
                        copyStereotype(parent, eobject, object);
                        continue;
                    } else if (eobject instanceof Property) { // Property, Port
                        component.getOwnedAttributes().add((Property) eobject);
                        copyStereotype(parent, eobject, object);
                        continue;
                    } else if (eobject instanceof Operation) {
                        component.getOwnedOperations().add((Operation) eobject);
                        copyStereotype(parent, eobject, object);
                        continue;
                    } else if (eobject instanceof Class) { // Class, Component
                        component.getPackagedElements().add((Class) eobject);
                        copyStereotype(parent, eobject, object);
                        continue;
                    } else if (eobject instanceof Enumeration) {
                        component.getPackagedElements().add((Enumeration) eobject);
                        copyStereotype(parent, eobject, object);
                        continue;
                    } else if (eobject instanceof Interface) {
                        component.getPackagedElements().add((Interface) eobject);
                        copyStereotype(parent, eobject, object);
                        continue;
                    } else if (eobject instanceof DataType) {// DataType,
                        // PrimitiveType
                        component.getPackagedElements().add((DataType) eobject);
                        copyStereotype(parent, eobject, object);
                        continue;
                    } else if (eobject instanceof Activity) {
                        component.getOwnedBehaviors().add((Activity) eobject);
                        copyStereotype(parent, eobject, object);
                        continue;
                    } else if (eobject instanceof Collaboration) {
                        component.getPackagedElements().add((Collaboration) eobject);
                        copyStereotype(parent, eobject, object);
                        continue;
                    } else if (eobject instanceof CollaborationUse) {
                        component.getCollaborationUses().add((CollaborationUse) eobject);
                        copyStereotype(parent, eobject, object);
                        continue;
                    } else if (eobject instanceof Interaction) {
                        component.getOwnedBehaviors().add((Interaction) eobject);
                        copyStereotype(parent, eobject, object);
                        continue;
                    }

                } else if (parent instanceof Class) {
                    Class clazz = (Class) parent;
                    if (eobject instanceof Property) { // Property, Port
                        clazz.getOwnedAttributes().add((Property) eobject);
                        copyStereotype(parent, eobject, object);
                        continue;
                    } else if (eobject instanceof Operation) {
                        clazz.getOwnedOperations().add((Operation) eobject);
                        copyStereotype(parent, eobject, object);
                        continue;
                    } else if (eobject instanceof Reception) {
                        clazz.getOwnedReceptions().add((Reception) eobject);
                        copyStereotype(parent, eobject, object);
                        continue;
                    } else if (eobject instanceof Class) {
                        clazz.getNestedClassifiers().add((Class) eobject);
                        copyStereotype(parent, eobject, object);
                        continue;
                    } else if (eobject instanceof Enumeration) {
                        clazz.getNestedClassifiers().add((Enumeration) eobject);
                        copyStereotype(parent, eobject, object);
                        continue;
                    } else if (eobject instanceof Interface) {
                        clazz.getNestedClassifiers().add((Interface) eobject);
                        copyStereotype(parent, eobject, object);
                        continue;
                    } else if (eobject instanceof Activity) {
                        clazz.getOwnedBehaviors().add((Activity) eobject);
                        copyStereotype(parent, eobject, object);
                        continue;
                    } else if (eobject instanceof CollaborationUse) {
                        clazz.getCollaborationUses().add((CollaborationUse) eobject);
                        copyStereotype(parent, eobject, object);
                        continue;
                    } else if (eobject instanceof Interaction) {
                        clazz.getNestedClassifiers().add((Interaction) eobject);
                        copyStereotype(parent, eobject, object);
                        continue;
                    }

                } else if (parent instanceof Activity) {
                    Activity activity = (Activity) parent;
                    if (eobject instanceof ActivityPartition) {
                        activity.getGroups().add((ActivityPartition) eobject);
                        copyStereotype(parent, eobject, object);
                        continue;
                    } else if (eobject instanceof Action) {
                        activity.getNodes().add((Action) eobject);
                        copyStereotype(parent, eobject, object);
                        continue;
                    } else if (eobject instanceof ActivityNode) {
                        activity.getNodes().add((ActivityNode) eobject);
                        copyStereotype(parent, eobject, object);
                        continue;
                    }

                } else if (parent instanceof Collaboration) {
                    Collaboration collaboration = (Collaboration) parent;
                    if (eobject instanceof Property) {
                        collaboration.getOwnedAttributes().add((Property) eobject);
                        copyStereotype(parent, eobject, object);
                        continue;
                    } else if (eobject instanceof CollaborationUse) {
                        collaboration.getCollaborationUses().add((CollaborationUse) eobject);
                        copyStereotype(parent, eobject, object);
                        continue;
                    }

                } else if (parent instanceof Artifact) {
                    Artifact artifact = (Artifact) parent;
                    if (eobject instanceof Property) {
                        artifact.getOwnedAttributes().add((Property) eobject);
                        copyStereotype(parent, eobject, object);
                        continue;
                    } else if (eobject instanceof Operation) {
                        artifact.getOwnedOperations().add((Operation) eobject);
                        copyStereotype(parent, eobject, object);
                        continue;
                    } else if (eobject instanceof Artifact) {
                        artifact.getNestedArtifacts().add((Artifact) eobject);
                        copyStereotype(parent, eobject, object);
                        continue;
                    }

                } else if (parent instanceof Enumeration) {
                    Enumeration enumeration = (Enumeration) parent;
                    if (eobject instanceof EnumerationLiteral) {
                        enumeration.getOwnedLiterals().add((EnumerationLiteral) eobject);
                        copyStereotype(parent, eobject, object);
                        continue;
                    } else if (eobject instanceof Property) {
                        enumeration.getOwnedAttributes().add((Property) eobject);
                        copyStereotype(parent, eobject, object);
                        continue;
                    } else if (eobject instanceof Operation) {
                        enumeration.getOwnedOperations().add((Operation) eobject);
                        copyStereotype(parent, eobject, object);
                        continue;
                    }

                } else if (parent instanceof Interface) {
                    Interface interfaze = (Interface) parent;
                    if (eobject instanceof Property) {
                        interfaze.getOwnedAttributes().add((Property) eobject);
                        copyStereotype(parent, eobject, object);
                        continue;
                    } else if (eobject instanceof Operation) {
                        interfaze.getOwnedOperations().add((Operation) eobject);
                        copyStereotype(parent, eobject, object);
                        continue;
                    } else if (eobject instanceof Reception) {
                        interfaze.getOwnedReceptions().add((Reception) eobject);
                        copyStereotype(parent, eobject, object);
                        continue;
                    } else if (eobject instanceof Class) {
                        interfaze.getNestedClassifiers().add((Class) eobject);
                        copyStereotype(parent, eobject, object);
                        continue;
                    } else if (eobject instanceof Enumeration) {
                        interfaze.getNestedClassifiers().add((Enumeration) eobject);
                        copyStereotype(parent, eobject, object);
                        continue;
                    } else if (eobject instanceof Collaboration) {
                        interfaze.getNestedClassifiers().add((Collaboration) eobject);
                        copyStereotype(parent, eobject, object);
                        continue;
                    } else if (eobject instanceof CollaborationUse) {
                        interfaze.getCollaborationUses().add((CollaborationUse) eobject);
                        copyStereotype(parent, eobject, object);
                        continue;
                    }

                } else if (parent instanceof Signal) {
                    Signal signal = (Signal) parent;
                    if (eobject instanceof Property) {
                        signal.getOwnedAttributes().add((Property) eobject);
                        copyStereotype(parent, eobject, object);
                        continue;
                    }
                }

                // 위의 어느 경우에도 해당되지 않을 때, 메세지를 보여준다.
                if (eobject instanceof NamedElement) {
                    if (UICoreConstant.EMPTY_STRING.equals(exceptedElementsStr)) {
                        exceptedElementsStr = ((NamedElement) eobject).getName();
                    } else {
                        exceptedElementsStr += UICoreConstant.PROJECT_CONSTANTS__COMMA
                            + ((NamedElement) eobject).getName();
                    }
                }

            }
        }
        if (null != eobject && null != object) {
            copyChildrenStereotype(parent, eobject, object);
        }

        // 붙이려고 하는 요소 중 다이어그램이 있을 경우에 메세지를 보여준다.
        String message = UICoreConstant.EMPTY_STRING;
        if (hasDiagram) {
            message = UMLMessage.MESSAGE_DIAGRAM_CANNOT_PASTE;
        }
        if (!UICoreConstant.EMPTY_STRING.equals(exceptedElementsStr)) {
            if (UICoreConstant.EMPTY_STRING.equals(message)) {
                message = UMLMessage.getMessage(UMLMessage.MESSAGE_CANNOT_PASTE, exceptedElementsStr);
            } else {
                message += UICoreConstant.PROJECT_CONSTANTS__NEW_LINE
                    + UMLMessage.getMessage(UMLMessage.MESSAGE_CANNOT_PASTE, message);
            }
        }

        if (!UICoreConstant.EMPTY_STRING.equals(message)) {
            MessageDialog.openWarning(UiCorePlugin.getShell(), UMLMessage.LABEL_NEXCORE_UML_MODELER, message);
        }

    }

    /**
     * copyChildrenStereotype
     *  
     * @param parent
     * @param target
     * @param source void
     */
    private static void copyChildrenStereotype(EObject parent, EObject target, Object source) {

        Element sourceElement = (Element) source;
        Element targetElement = (Element) target;

        for (Element sourceChild : sourceElement.allOwnedElements()) {
            for (Element targetChild : targetElement.allOwnedElements()) {

                if (sourceChild instanceof NamedElement && targetChild instanceof NamedElement) {
                    if (null != ((NamedElement) sourceChild).getName()) {
                        if (((NamedElement) sourceChild).getName().equals(((NamedElement) targetChild).getName())) {
                            copyStereotype(targetElement, targetChild, sourceChild);
                        }
                    }
                }
            }
        }
    }

    /**
     * @param parent
     *            붙여넣을 대상 부모.
     * @param eobject
     *            Target. 새로 생성해 줄 EObject.
     * @param object
     *            Source. 복사할 소스 EObject.
     */
    public static void copyStereotype(EObject parent, EObject eobject, Object object) {
        // 스테레오타입 복사 추가 시작.
        Element sourceElement = null;
        Element targetElement = null;
        if (object instanceof Element) {
            sourceElement = (Element) object;
        }
        if (eobject instanceof Element) {
            targetElement = (Element) eobject;
        }
        List<Stereotype> sList = new ArrayList<Stereotype>();
        if (sourceElement != null) {
            sList = sourceElement.getAppliedStereotypes();
        }
        if (targetElement != null) {
            for (Stereotype stereo : sList) {

                EClass eClass = null;
                if (stereo.getProfile() != null) {
                    ProfileApplication profileApplication = getPackage(parent).getProfileApplication(stereo.getProfile(),
                        true);
                    if (profileApplication != null) {
                        ENamedElement appliedDefinition = profileApplication.getAppliedDefinition(stereo);

                        if (appliedDefinition instanceof EClass) {
                            eClass = (EClass) appliedDefinition;
                            if (eClass != null && !eClass.isAbstract()) {
                                UMLUtilOperation.applyEStereotype(targetElement, eClass);
                                // Stereotype 특성값 복사
                                Stereotype sType = targetElement.getAppliedStereotype(stereo.getQualifiedName());
                                EList<Property> attributes = stereo.getAllAttributes();
                                for (Property property : attributes) {
                                    String propertyName = property.getName();
                                    Object obj = sourceElement.getValue(stereo, propertyName);
                                    if (null != obj && !propertyName.startsWith(Extension.METACLASS_ROLE_PREFIX)) {
                                        targetElement.setValue(sType, propertyName, obj);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        // 스테레오타입 복사 추가 끝.
    }

    /**
     * getPackage
     *  
     * @param parent
     * @return Package
     */
    private static Package getPackage(EObject parent) {
        if (parent instanceof Package) {
            return (Package) parent;
        } else {
            return getPackage(((Element) parent).getOwner());
        }
    }

    /**
     * 부모가 같고 이름이 같은 요소를 붙여넣었을 때 '사본_#_이름'의 형태로 만들어준다.
     * 
     * @param eobject
     *            void
     * @param parent
     */
    public static String getCopiedUniqueName(NamedElement element, Namespace parent, String name) {

        int index = 1;
        String newName;
        while (true) {
            newName = UMLMessage.getMessage(UMLMessage.LABEL_COPYOF) + Integer.toString(index++)
                + UICoreConstant.PROJECT_CONSTANTS__UNDER_BAR + name;
            if (null == parent.getOwnedMember(newName)) {
                return newName;
            }

            if (10000 <= index) {
                return name + Integer.toString(UMLManager.NAME_INDEX++);
            }
        }
    }

    /**
     * 해당 폴더 하위에 있는 모델 파일들을 List로 리턴한다.
     * 
     * @param folder
     * @return List<IFile>
     * @throws CoreException
     */
    public static List<IFile> getModelFiles(IFolder folder) throws CoreException {

        List<IFile> list = new ArrayList<IFile>();
        IResource[] resources = folder.members();
        IFile file;
        for (int i = 0; i < resources.length; i++) {
            if (resources[i] instanceof IFile) {
                file = (IFile) resources[i];
                if (UICoreConstant.PROJECT_CONSTANTS__FILE_EXTENSION.equals(file.getFileExtension())) {
                    // ||
                    // UICoreConstant.PROJECT_CONSTANTS__FRAGMENT_EXTENSION.equals(file.getFileExtension()))
                    // {
                    list.add(file);
                }
            }
        }
        return list;
    }

    /**
     * Finds the tree node of project explorer for the selected element in
     * diagram.
     * 
     * @param eobject
     *            void
     */
    public static void findElement(EObject eobject) {

        CommonViewer viewer = ViewerRegistry.getViewer();
        if (eobject == null || eobject.eResource() == null || eobject.eResource().getResourceSet() == null) {
            return;
        }
        
        eobject = resolveResource(eobject);
        String fileExtension = eobject.eResource().getURI().fileExtension();
        if (!ManagerConstant.UMLDOMAIN_CONSTANT__MODEL_FILE_EXTENSION.equals(fileExtension) 
        		&& !ManagerConstant.UMLDOMAIN_CONSTANT__FRAGMENT_FILE_EXTENSION.equals(fileExtension)) {
        	MessageDialog.openWarning(UiCorePlugin.getShell(), UMLMessage.LABEL_FIND_ELEMENT, 
        			UMLMessage.MESSAGE_CANNOT_FIND_ELEMENT);
        	return;
        }

        Object[] objects = viewer.getExpandedElements();
        List<Object> expandList = new ArrayList<Object>();
        for (Object object : objects) {
            if (!(expandList.contains(object))) {
                expandList.add(object);
            }
        }

        ITreeNode node = createExplorerTree(eobject, expandList);
        objects = expandList.toArray();
        if (node != null) {
            viewer.getControl().setFocus();

            if (objects != null) {
                viewer.setExpandedElements(objects);
            }

            viewer.setSelection(new StructuredSelection(node), true);
            viewer.reveal(node);
        }

    }

    /**
     * createExplorerTree()에서 사용. TreeNode를 만들어 주기 위한
     * AdapterFactoryContentProvider.
     */
    private static AdapterFactoryContentProvider adapterFactoryContentProvider = null;

    /**
     * getAdapterFactoryContentProvider
     *  
     * @return AdapterFactoryContentProvider
     */
    public static AdapterFactoryContentProvider getAdapterFactoryContentProvider() {
        if (adapterFactoryContentProvider == null) {
            adapterFactoryContentProvider = new AdapterFactoryContentProvider(UiCorePlugin.getDefault()
                .getItemProvidersAdapterFactory());
        }
        return adapterFactoryContentProvider;
    }

    /**
     * getChildEObjects
     *  
     * @param objects
     * @param parentElement
     * @param isProxy
     * @return Object[]
     */
    public static Object[] getChildEObjects(Object[] objects, Object parentElement, boolean isProxy) {

        Collection<Object> result = new ArrayList<Object>();
        ITreeNode node = null;
        if (parentElement instanceof UMLTreeNode) {
            EObject parentEobj = ((UMLTreeNode) parentElement).getEObject();
            if (parentEobj instanceof Diagram)
                return null;
        }

        EObject eobject;
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] instanceof EObject) {
                eobject = (EObject) objects[i];
                node = null;

                if (eobject instanceof EAnnotation) {
                    String annotation = ((EAnnotation) eobject).getSource();
                    if (!UICoreConstant.PROJECT_CONSTANTS__DIAGRAM.equals(annotation)) {
                        continue;
                    }
                }

                Resource resource = eobject.eResource();
                if (resource != null) {
                    try {
                        eobject = resolveResource(eobject);
                    } catch (Exception e) {
                        e.printStackTrace();
                        continue;
                    }
                }

                // 단편화된 UML element 처리
                if (AdapterFactoryEditingDomain.isControlled(eobject)) {
                    node = ProjectRegistry.UMLTreeNodeRegistry.getTreeNode(eobject);
                    if (!(node instanceof UMLFileTreeNode)) {
                        node = ProjectRegistry.UMLTreeNodeRegistry.getTreeNode(eobject);
                    }

                } else {
                    if (!isProxy) {
                        node = ProjectRegistry.UMLTreeNodeRegistry.getTreeNode(eobject);
                    }

                    if (node == null || node instanceof IFile) {

                        // EAnnotation을 제외한 Diagram 노드 추가
                        // } else if (eobject instanceof EAnnotation) {
                        if (eobject instanceof EAnnotation) {
                            EAnnotation eannotation = (EAnnotation) eobject;
                            if (UICoreConstant.PROJECT_CONSTANTS__DIAGRAM.equals(eannotation.getSource())) {
                                EList<EObject> elist = eannotation.getContents();
                                for (EObject object : elist) {
                                    node = ProjectRegistry.UMLTreeNodeRegistry.getTreeNode(object);
                                    if (node == null) {
                                        // node =
                                        // ProjectRegistry.UMLTreeNodeRegistry.getTreeNode(eobject);

                                        continue;
                                    }
                                    result.add(node);
                                }

                            }
                        }

                        //node = new UMLTreeNode(eobject, parentElement, adapterFactoryContentProvider);
                    }
                }
                if (node != null && !result.contains(node)) {
                    result.add(node);
                }
            }
        }
        return result.toArray();
    }

    /**
     * resolveResource
     *  
     * @param obj
     * @return EObject
     */
    public static EObject resolveResource(EObject obj) {
        if (DomainUtil.isProxy(obj)) {
            try {
                URI uri = EcoreUtil.getURI(obj);
                if (WorkspaceSynchronizer.getFile(obj.eResource()).exists()) {
                    obj = DomainRegistry.getUMLDomain().getResourceSet().getEObject(uri, true);
                }
            } catch (Exception e) {
                // e.printStackTrace();
            }
        }
        return obj;
    }

    /**
     * Creates project explorer tree, when Link-With function is activated.
     * 
     * @param eobject
     * @param expandList
     * @return ITreeNode
     */
    private static ITreeNode createExplorerTree(EObject eobject, List<Object> expandList) {

        EObject parent = eobject.eContainer();
        Resource resource = eobject.eResource();
        ITreeNode parentNode = null;

        /*
         * parent node가 생성되어 있어야 하위 노드가 생성이 가능하므로 parent node 먼저 만들기 위해서
         * eobject가 RootModel이 아니면, proxy resolve해주면서 모델을 찾을 때까지 들어간다.
         */
        if (parent != null) {
            parent = resolveResource(parent);
            parentNode = createExplorerTree(parent, expandList);

        } else {
            /*
             * Folder와 Project 노드 처리
             */
            if (resource != null) {
                IFile file = WorkspaceSynchronizer.getFile(resource);
                addAllParents(file, expandList);
            }

        }

        ITreeNode node = UMLTreeNodeRegistry.getTreeNode(eobject);
        if (node == null) {
            // Fragmented Element 노드 처리
            if (AdapterFactoryEditingDomain.isControlled(eobject)) {
                IFile file = WorkspaceSynchronizer.getFile(resource);
                node = new UMLFileTreeNode(file.getFullPath(),
                    (Workspace) file.getWorkspace(),
                    eobject,
                    parentNode,
                    true,
                    adapterFactoryContentProvider);

                // 일반 노드 처리
            } else {
                if (eobject instanceof Model) {
                    IFile file = WorkspaceSynchronizer.getFile(resource);
                    node = new UMLFileTreeNode(file.getFullPath(),
                        (Workspace) file.getWorkspace(),
                        eobject,
                        (Object) file.getParent(),
                        false,
                        adapterFactoryContentProvider);

                } else {
                    node = new UMLTreeNode(eobject, parentNode, adapterFactoryContentProvider);
                }
            }

            UMLTreeNodeRegistry.setTreeNode(eobject, node);
        }

        if (!(expandList.contains(node))) {
            expandList.add(node);
        }

        return node;
    }

    /**
     * 해당 모델 파일이 속해 있는 Folder와 Project들을 expandList에 추가한다.
     * 
     * @param file
     * @param expandList
     *            void
     */
    private static void addAllParents(IResource resource, List<Object> expandList) {
        IContainer container = resource.getParent();
        if (!(container instanceof IProject)) {
            addAllParents(container, expandList);
        }
        if (!expandList.contains(container)) {
            expandList.add(container);
        }
    }

    /**
     * 해당 Element 하위에 있는 타입별 다이어그램을 리스트로 반환.
     * 
     * @param eobject
     * @param diagramType
     * @return List<Diagram>
     */
    public static List<Diagram> getDiagrams(Element element, DiagramType diagramType) {

        List<Diagram> list = new ArrayList<Diagram>();
        EAnnotation annotation = element.getEAnnotation(UICoreConstant.PROJECT_CONSTANTS__DIAGRAM);

        if (annotation != null) {

            EList<EObject> contents = annotation.getContents();
            Diagram diagram;
            for (EObject eobject : contents) {
                if (eobject instanceof Diagram) {
                    diagram = (Diagram) eobject;
                    if (diagramType.equals(diagram.getType())) {
                        list.add(diagram);
                    }
                }
            }

        }

        return list;

    }

    /**
     * getFragmentAnnotation
     *  
     * @param eobject
     * @return EAnnotation
     */
    public static EAnnotation getFragmentAnnotation(EObject eobject) {
        if (eobject instanceof org.eclipse.uml2.uml.Package) {
            org.eclipse.uml2.uml.Package myPackage = (org.eclipse.uml2.uml.Package) eobject;
            EAnnotation eAnnotation = myPackage.getEAnnotation(UICoreConstant.PROJECT_CONSTANTS__FRAGMENT);
            if (eAnnotation == null)
                eAnnotation = myPackage.createEAnnotation(UICoreConstant.PROJECT_CONSTANTS__FRAGMENT);
            return eAnnotation;
        }
        return null;
    }

    /**
     * getFragmentContainerAnnotation
     *  
     * @param eobject
     * @return EAnnotation
     */
    public static EAnnotation getFragmentContainerAnnotation(EObject eobject) {
        if (eobject instanceof org.eclipse.uml2.uml.Package) {
            org.eclipse.uml2.uml.Package myPackage = (org.eclipse.uml2.uml.Package) eobject;
            EAnnotation eAnnotation = myPackage.getEAnnotation(UICoreConstant.PROJECT_CONSTANTS__FRAGMENT_CONTAINER);
            if (eAnnotation == null)
                eAnnotation = myPackage.createEAnnotation(UICoreConstant.PROJECT_CONSTANTS__FRAGMENT_CONTAINER);

            // FragmentContainer 가 첫번째 배열오 오도록 한다.
            // 이유 : 단편화된 패키지의 스테레오 타입이 사라지는 문제 발생 때문에 추가함.
            int size = myPackage.getEAnnotations().size();
            if (size > 1 && myPackage.getEAnnotations().indexOf(eAnnotation) != 0) {
                List<EAnnotation> tempList = new ArrayList<EAnnotation>();
                tempList.addAll(myPackage.getEAnnotations());
                Collections.sort(tempList, EANNOTATION_COMPARATOR);
                int i = 0;
                for (EAnnotation e : tempList) {
                    myPackage.getEAnnotations().remove(e);
                    myPackage.getEAnnotations().add(i++, e);
                }
            }

            return eAnnotation;
        }
        return null;
    }

    /**
     * EANNOTATION_COMPARATOR
     */
    public static EAnnotationComparator EANNOTATION_COMPARATOR = new EAnnotationComparator();

    /**
     * FragmentContainer EAnnotation 이 배열에서 첫번째로 오도록 하기 위한 comparator.
     */
    private static class EAnnotationComparator implements Comparator<EAnnotation>, java.io.Serializable {

        private static final long serialVersionUID = 7964638470634065257L;

        public int compare(EAnnotation s1, EAnnotation s2) {
            if (UICoreConstant.PROJECT_CONSTANTS__FRAGMENT_CONTAINER.equals(s1.getSource())) {
                return 0;
            } else
                return 1;
        }
    }

    /**
     * getProjectInfoAnnotation
     *  
     * @param eobject
     * @return EAnnotation
     */
    public static EAnnotation getProjectInfoAnnotation(EObject eobject) {
        if (eobject instanceof org.eclipse.uml2.uml.Package) {
            org.eclipse.uml2.uml.Package myPackage = (org.eclipse.uml2.uml.Package) eobject;
            EAnnotation eAnnotation = myPackage.getEAnnotation(UICoreConstant.PROJECT_CONSTANTS__PROJECT_INFO);
            return eAnnotation;
        }
        return null;
    }

    /**
     * createProjectInfoAnnotation
     *  
     * @param eobject
     * @return EAnnotation
     */
    public static EAnnotation createProjectInfoAnnotation(EObject eobject) {
        if (eobject instanceof org.eclipse.uml2.uml.Package) {
            org.eclipse.uml2.uml.Package myPackage = (org.eclipse.uml2.uml.Package) eobject;
            EAnnotation eAnnotation = myPackage.createEAnnotation(UICoreConstant.PROJECT_CONSTANTS__PROJECT_INFO);
            return eAnnotation;
        }
        return null;
    }

    /**
     * Profile 정보를 fragment 간에 이동한다.
     * 
     * @param resource
     * @param eobject
     *            현재 선택된 EObject
     * @param merge
     *            merge이면 true, fragmentation이면 false.
     * @return List<EObject>
     */
    public static List<EObject> getStereotypesForFragment(Resource resource, EObject eobject, boolean merge) {
        List<EObject> result = new ArrayList<EObject>();
        List<EObject> list = resource.getContents();

        // Merge할 때는 fragment 파일에 있는 모든 profile 정보를 원 모델에 넘겨준다.
        if (merge) {
            for (EObject eobj : list) {
                if (eobj instanceof DynamicEObjectImpl) {
                    result.add(eobj);
                }
            }

            // fragment하는 패키지와 관계있는 profile 정보만 fragment 파일로 이동시킨다.
        } else {
            List<Element> allElements = ((Element) eobject).allOwnedElements();
            Element element;
            for (EObject eobj : list) {
                if (eobj instanceof DynamicEObjectImpl) {
                    element = UMLUtil.getBaseElement((DynamicEObjectImpl) eobj);
                    if (allElements.contains(element)) {
                        result.add(eobj);
                    }
                }
            }
        }
        return result;
    }

    /**
     * 
     * 
     * @param elementList
     */
    public static List getSortedList(List elementList) {
        Comparator<NamedElement> comparator = new Comparator<NamedElement>() {
            public int compare(NamedElement o1, NamedElement o2) {
                String o1s = ProjectUtil.getStereotypeText(o1) + UICoreConstant.PROJECT_CONSTANTS__BLANK + o1.getName();
                String o2s = ProjectUtil.getStereotypeText(o2) + UICoreConstant.PROJECT_CONSTANTS__BLANK + o2.getName();

                return o1s.compareTo(o2s);
            }
        };
        Collections.sort(elementList, comparator);
        return elementList;
    }

    /**
     * isModelFile
     *  
     * @param resource
     * @return boolean
     */
    public static boolean isModelFile(Resource resource) {
        if (resource == null)
            return false;
        return resource.getURI().toString().contains(UICoreConstant.PROJECT_CONSTANTS__FILE_EXTENSION)
            || resource.getURI().toString().contains(UICoreConstant.PROJECT_CONSTANTS__FRAGMENT_EXTENSION);
    }

    /**
     * isModelFile
     *  
     * @param file
     * @return boolean
     */
    public static boolean isModelFile(IFile file) {
        return file.exists()
            && (UICoreConstant.PROJECT_CONSTANTS__FILE_EXTENSION.equals(file.getFileExtension()) || UICoreConstant.PROJECT_CONSTANTS__FRAGMENT_EXTENSION.equals(file.getFileExtension()));
    }

    /**
     * isFragmentFile
     *  
     * @param resource
     * @return boolean
     */
    public static boolean isFragmentFile(Resource resource) {
        return resource.getURI().toString().contains(UICoreConstant.PROJECT_CONSTANTS__FRAGMENT_EXTENSION);
    }

    /**
     * isFragmentFile
     *  
     * @param file
     * @return boolean
     */
    public static boolean isFragmentFile(IFile file) {
        return UICoreConstant.PROJECT_CONSTANTS__FRAGMENT_EXTENSION.equals(file.getFileExtension());
    }

    /**
     * createProjectInfo
     *  
     * @return ProjectElement
     */
    public static ProjectElement createProjectInfo() {
        ProjectElement newProjectInfo = ProjectInformationFactory.eINSTANCE.createProjectElement();
        newProjectInfo.setModelVersion(UICoreConstant.PROJECT_CONSTANTS__MODEL_VERSION);
        newProjectInfo.setSource(UICoreConstant.PROJECT_CONSTANTS__PROJECT_INFO);

        return newProjectInfo;
    }

    /**
     * findProject
     *  
     * @param obj
     * @return IProject
     */
    public static IProject findProject(Object obj) {
        IProject project = null;
        if (obj instanceof ITreeNode) {
            ITreeNode node = (ITreeNode) obj;
            project = findProject(node.getParentNode());
        } else if (obj instanceof IProject) {
            project = (IProject) obj;
        } else if (obj instanceof IFolder) {
            project = ((IFolder) obj).getProject();
        } else if (obj instanceof IFile) {
            project = ((IFile) obj).getProject();
        }

        if (project != null && isActiveUMLProject(project)) {
            return project;
        }

        return null;
    }

    /**
     * isActiveUMLProject
     *  
     * @param project
     * @return boolean
     */
    public static boolean isActiveUMLProject(IProject project) {
        try {
            return project != null && project.isOpen()
                && project.hasNature(UICoreConstant.PROJECT_CONSTANTS__NATURE_ID);
        } catch (CoreException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 에디터 닫기.
     * 
     * 
     * @param eObject
     *            void
     */
    public static void closeEditor(EObject eObject) {
        for (final IWorkbenchWindow window : PlatformUI.getWorkbench().getWorkbenchWindows()) {
            IEditorReference[] editorRefs = window.getActivePage().getEditorReferences();

            for (IEditorReference ref : editorRefs) {
                final IEditorPart editor = ref.getEditor(false);
                if (editor != null && editor.getEditorInput() instanceof UMLEditorInput) {
                    if (eObject == ((UMLEditorInput) editor.getEditorInput()).getElement()) {
                        ViewerRegistry.getViewer().getControl().getDisplay().asyncExec(new Runnable() {
                            public void run() {
                                window.getActivePage().closeEditor(editor, true);
                            }
                        });
                    }
                } else if (editor != null && editor.getEditorInput() instanceof DiagramEditorInput) {
                    URI editorUri = ((DiagramEditorInput) editor.getEditorInput()).getURI();
                    URI resourceUri = eObject.eResource().getURI();
                    if (resourceUri.toString().equals(editorUri.toString())) {
                        ViewerRegistry.getViewer().getControl().getDisplay().asyncExec(new Runnable() {
                            public void run() {
                                window.getActivePage().closeEditor(editor, true);
                            }
                        });
                    }
                }
            }
        }
    }

    /**
     * 프로젝트에 속한 에디터를 닫는다.
     * 
     * 
     * @param project
     *            void
     */
    public static void closeEditor(IProject project) {
        String projectName = project.getName();

        for (final IWorkbenchWindow window : PlatformUI.getWorkbench().getWorkbenchWindows()) {
            IEditorReference[] editorRefs = window.getActivePage().getEditorReferences();

            for (IEditorReference ref : editorRefs) {
                final IEditorPart editor = ref.getEditor(false);
                String pn = null;
                URI editorUri = null;
                if (editor != null && editor.getEditorInput() instanceof UMLEditorInput) {
                    try {
                        editorUri = ((UMLEditorInput) editor.getEditorInput()).getElement()
                            .eContainer()
                            .eResource()
                            .getURI();
                    } catch (Exception e) {
                        try {
                            editorUri = ((UMLEditorInput) editor.getEditorInput()).getElement()
                                .eResource()
                                .getURI();
                        } catch (Exception e1) {
                            // ignore
                        }
                    }
                } else if (editor != null && editor.getEditorInput() instanceof DiagramEditorInput) {
                    editorUri = ((DiagramEditorInput) editor.getEditorInput()).getURI();
                }  else if (editorList.contains(editor.getClass().getName())) {
                    pn = ((FileEditorInput)editor.getEditorInput()).getFile().getProject().getName();
                }  

                
                if (editorUri != null) {
                    if (editorUri.isPlatform()) {
                        pn = editorUri.segment(1);
                    } else {
                        pn = editorUri.segment(0);
                    }
                }
                if (projectName.equals(pn)) {
                    ViewerRegistry.getViewer().getControl().getDisplay().asyncExec(new Runnable() {
                        public void run() {
                            window.getActivePage().closeEditor(editor, true);
                        }
                    });
                }
            }
        }
    }
    
    /**
     * editorList
     */
    static List<String> editorList = new ArrayList<String>();
    static{
        editorList.add("nexcore.tool.mda.developer.ui.editor.DeveloperRuleEditor");
        editorList.add("nexcore.tool.mda.designer.ui.editor.TransferFileEditor");
        editorList.add("nexcore.tool.mda.developer.reverse.ui.editor.ReverseRuleEditor");
    }
    
    /**
     * closeAllEditor
     *   void
     */
    public static void closeAllEditor() {

        for (final IWorkbenchWindow window : PlatformUI.getWorkbench().getWorkbenchWindows()) {
            IEditorReference[] editorRefs = window.getActivePage().getEditorReferences();

            for (IEditorReference ref : editorRefs) {
                final IEditorPart editor = ref.getEditor(true);

                if (editor != null) {
                    if ( editorList.contains(editor.getClass().getName()) || editor.getEditorInput() instanceof UMLEditorInput
                        || editor.getEditorInput() instanceof DiagramEditorInput) {
                        ViewerRegistry.getViewer().getControl().getDisplay().asyncExec(new Runnable() {
                            public void run() {
                                window.getActivePage().closeEditor(editor, true);
                            }
                        });
                    }
                }
            }
        }
    }

    /**
     * 
     * Editpart를 받아서 해당 EditPart에서 하위 계층까지 전체 리프레쉬를 실행 DiagramEditPart를 인자로 넘겨주면
     * 다이어그램 전체 리프레쉬 실행가능.
     * 
     * @param object
     *            void
     */
    public static void refreshChildEditPart(EditPart editPart) {

        if (null == editPart) {
            return;
        }

        editPart.refresh();
        if (editPart.getModel() instanceof Diagram) {
            Diagram diagram = (Diagram) editPart.getModel();
            for (AbstractView view : diagram.getNodeList()) {
                if (view instanceof NotationNode) {
                    ViewModelUtil.setModelInfo((NotationNode) view);
                }
            }
        }

        if (editPart instanceof GraphicalEditPart) {

            List<EditPart> sConnectionList = ((GraphicalEditPart) editPart).getSourceConnections();
            List<EditPart> tConnectionList = ((GraphicalEditPart) editPart).getTargetConnections();

            for (EditPart sConnectionEditPart : sConnectionList) {
                sConnectionEditPart.refresh();
            }
            for (EditPart tConnectionEditPart : tConnectionList) {
                tConnectionEditPart.refresh();
            }

            if (editPart.getModel() instanceof LifeLineNode) {
                List<EditPart> childrenEditPart = editPart.getChildren();
                for (EditPart child : childrenEditPart) {
                    List<EditPart> csConnectionList = ((GraphicalEditPart) child).getSourceConnections();
                    List<EditPart> ctConnectionList = ((GraphicalEditPart) child).getTargetConnections();

                    for (EditPart sConnectionEditPart : csConnectionList) {
                        sConnectionEditPart.refresh();
                    }
                    for (EditPart tConnectionEditPart : ctConnectionList) {
                        tConnectionEditPart.refresh();
                    }
                }
            }
        }
        List<EditPart> editparts = editPart.getChildren();
        for (EditPart child : editparts) {
            refreshChildEditPart(child);
        }
    }

    /**
     * 
     * platformUi에서 다이어그램 EditPart를 가져온다.
     * 
     * @return EditPart
     */
    public static EditPart getDiagramEditPart() {
        IWorkbench workbench = PlatformUI.getWorkbench();
        if (!checkNull(workbench)) {
            return null;
        }
        IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();
        if (!checkNull(workbenchWindow)) {
            return null;
        }
        IWorkbenchPage workbenchPage = workbenchWindow.getActivePage();
        if (!checkNull(workbenchPage)) {
            return null;
        }
        IEditorPart editorPart = workbenchPage.getActiveEditor();
        if (!checkNull(editorPart)) {
            return null;
        }
        if (!(editorPart instanceof AbstractDiagramEditor)) {
            return null;
        }
        GraphicalViewer graphicalViewer = ((AbstractDiagramEditor) editorPart).getDiagramGraphicalViewer();
        if (!checkNull(graphicalViewer)) {
            return null;
        }
        RootEditPart rootEditPart = graphicalViewer.getRootEditPart();
        if (!checkNull(rootEditPart)) {
            return null;
        }
        EditPart editPart = rootEditPart.getContents();
        if (!checkNull(editPart)) {
            return null;
        }

        return editPart;
    }

    /**
     * 
     * 파라미터가 널이면 리턴 false
     * 
     * @param object
     * @return boolean
     */
    private static boolean checkNull(Object object) {
        if (null == object) {
            return false;
        }
        return true;
    }

    /**
     * rollbackResourceSet
     *  
     * @param recorder void
     */
    public static void rollbackResourceSet(final ChangeRecorder recorder) {
        if (recorder == null || !recorder.isRecording()) {
            return;
        }
        DomainRegistry.getEditingDomain().getCommandStack().execute(new CompoundCommand() {
            /**
             * @see org.eclipse.emf.common.command.CompoundCommand#execute()
             */
            @Override
            public void execute() {
                try {
                    ChangeDescription endRecording = recorder.endRecording();
                    if (endRecording != null) {
                        endRecording.apply();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            /**
             * @see org.eclipse.emf.common.command.AbstractCommand#canExecute()
             */
            @Override
            public boolean canExecute() {
                return true;
            }
        });
    }

    /**
     * 모델 rollback 후 모델 저장
     * 
     * 
     * @param recorder
     * @param eObject
     *            void
     */
    public static void rollbackWithSave(final ChangeRecorder recorder, final EObject eObject) {
        if (recorder == null || !recorder.isRecording() || eObject == null) {
            return;
        }
        DomainRegistry.getEditingDomain().getCommandStack().execute(new CompoundCommand() {
            /**
             * @see org.eclipse.emf.common.command.CompoundCommand#execute()
             */
            @Override
            public void execute() {
                try {
                    if (recorder.isRecording()) {
                        ChangeDescription endRecording = recorder.endRecording();
                        if (endRecording != null) {
                            endRecording.apply();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    DomainModelHandlerUtil.save(eObject);
                    
                    if( eObject instanceof Package) {
                        TreeMap<String, Package> fragmentedPackageList = ProjectUtil.getFragmentedPackageList((Package) eObject);
                        for (Iterator<Package> iterator = fragmentedPackageList.values().iterator(); iterator.hasNext();) {
                            Package pkg = (Package) iterator.next();
                            DomainModelHandlerUtil.save(pkg);
                        }
                    }
                }
            }

            /**
             * @see org.eclipse.emf.common.command.AbstractCommand#canExecute()
             */
            @Override
            public boolean canExecute() {
                return true;
            }
        });
    }

    /**
     * 모델 rollback 처리
     * 
     * 
     * @param recorder
     * @param eObject
     *            void
     */
    public static void beginRecording(final ChangeRecorder recorder, final EObject eObject) {
        if (recorder == null || !recorder.isRecording() || eObject == null) {
            return;
        }
        DomainRegistry.getEditingDomain().getCommandStack().execute(new CompoundCommand() {
            /**
             * @see org.eclipse.emf.common.command.CompoundCommand#execute()
             */
            @Override
            public void execute() {
                try {
                    if (recorder.isRecording()) {
                        recorder.endRecording();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (eObject instanceof Package) {
                        List<EObject> list = new ArrayList<EObject>();
                        TreeMap<String, Package> fragmentedPackageList = ProjectUtil.getFragmentedPackageList((Package) eObject);
                        list.add(eObject);
                        list.addAll(fragmentedPackageList.values());
                        
                        recorder.beginRecording(list);

                    } else {
                        recorder.beginRecording(Collections.singleton(eObject));
                    }
                }
            }

            /**
             * @see org.eclipse.emf.common.command.AbstractCommand#canExecute()
             */
            @Override
            public boolean canExecute() {
                return true;
            }
        });
    }
    
    /**
     * generatePackageUriPath
     *  
     * @param selectedURI
     * @param selectedPackage
     * @return String
     */
    public static String generatePackageUriPath(String selectedURI, Package selectedPackage) {
        String uriPath = "";

        IPreferenceStore preferenceStore = UiCorePlugin.getDefault().getPreferenceStore();
//        boolean strBoolean = preferenceStore.getBoolean(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PROJECTEXPLORER_FRAGMENT_BASE_PACKAGE);

        // 2011-08-09 강석찬
        // .fragment folder 하위에 패키지구조로 폴더 생성
        String uri = StringUtil.getProperFileName(selectedPackage.getName(), UICoreConstant.PROJECT_CONSTANTS__UNDER_BAR) + ManagerConstant.DOT
            + ManagerConstant.UMLDOMAIN_CONSTANT__FRAGMENT_FILE_EXTENSION;
        Package parent = selectedPackage;
        
        while (!(parent instanceof Model)) {
            parent = (Package) parent.eContainer();
            String name = parent.getName();
            // 폴더명에 부적합한 특수문자 변환 
            name = StringUtil.getProperFileName(name, UICoreConstant.PROJECT_CONSTANTS__UNDER_BAR);
            uri = name + UICoreConstant.PROJECT_CONSTANTS__SLASH + uri;
        }

        if( selectedURI == null) {
            uri = UICoreConstant.PROJECT_CONSTANTS__FRAGMENT_FOLDER_NAME + UICoreConstant.PROJECT_CONSTANTS__SLASH + uri;
            String projectUri = UICoreConstant.PROJECT_CONSTANTS__SLASH + selectedPackage.eResource().getURI().segment(0);
            uriPath = projectUri + UICoreConstant.PROJECT_CONSTANTS__SLASH + uri;
        } else {
            uriPath = selectedURI + UICoreConstant.PROJECT_CONSTANTS__SLASH + uri;
        }
        
        uriPath = getUniqueFileName(new Path(uriPath));

        return uriPath;
    }
    
//    \ /:*?"<>|
    /**
     * generatePackageUriPath
     *  
     * @param selectedPackage
     * @param confirm
     * @return String
     */
    public static String generatePackageUriPath(Package selectedPackage, boolean confirm) {
        String uriPath = "";

        IPreferenceStore preferenceStore = UiCorePlugin.getDefault().getPreferenceStore();
//        boolean strBoolean = preferenceStore.getBoolean(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PROJECTEXPLORER_FRAGMENT_BASE_PACKAGE);

        // 2011-08-09 강석찬
        // .fragment folder 하위에 패키지구조로 폴더 생성
        String uri = StringUtil.getProperFileName(selectedPackage.getName(), UICoreConstant.PROJECT_CONSTANTS__UNDER_BAR) + ManagerConstant.DOT
            + ManagerConstant.UMLDOMAIN_CONSTANT__FRAGMENT_FILE_EXTENSION;
        Package parent = selectedPackage;
        
        while (!(parent instanceof Model)) {
            parent = (Package) parent.eContainer();
            String name = parent.getName();
            // 폴더명에 부적합한 특수문자 변환 
            name = StringUtil.getProperFileName(name, UICoreConstant.PROJECT_CONSTANTS__UNDER_BAR);
            uri = name + UICoreConstant.PROJECT_CONSTANTS__SLASH + uri;
        }

        uri = UICoreConstant.PROJECT_CONSTANTS__FRAGMENT_FOLDER_NAME + UICoreConstant.PROJECT_CONSTANTS__SLASH + uri;
        String projectUri = UICoreConstant.PROJECT_CONSTANTS__SLASH + selectedPackage.eResource().getURI().segment(0);
        uriPath = projectUri + UICoreConstant.PROJECT_CONSTANTS__SLASH + uri;

        String prefConst = ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_PROJECTEXPLORER_FRAGMENT_TOGGLE_STATE;
        boolean toggle_state = preferenceStore.getBoolean(prefConst);
        if (!toggle_state && confirm) {

            MessageDialogWithToggle m = MessageDialogWithToggle.openOkCancelConfirm(UiCorePlugin.getShell(),
                UMLMessage.MESSAGE_FILE_FRAGMENTATION,
                uriPath + UMLMessage.MESSAGE_CONFIRM_CREATE_FRAGMENT,
                UMLMessage.MESSAGE_CREATE_WITHOUT_PROMPT,
                toggle_state,
                PreferenceUtil.INSTANCE.getPreferenceStore(),
                prefConst);

            if (m.getReturnCode() != Window.OK) {
                return "";
            }

            if (m.getToggleState() == true) {
                preferenceStore.setValue(prefConst, m.getToggleState());
            }
        }

        return uriPath;
    }

    /**
     * file 하위에 존재하는 단편파일 리스트를 가져온다.
     * 
     * @param resource
     *            void
     */
    public static Set<Resource> getFragmentedFileList(Resource resource) {
//        List<Resource> resourceList = new ArrayList<Resource>();
//
//        org.eclipse.uml2.uml.Package pack = (org.eclipse.uml2.uml.Package) resource.getContents().get(0);
//        EObjectCondition condition = new EObjectTypeRelationCondition(EcorePackage.eINSTANCE.getEAnnotation());
//        SELECT statement = new SELECT(new FROM(pack), new WHERE(condition));
//        IQueryResult result = statement.execute();
//
//        EAnnotation annotation;
//        for (Iterator<EObject> it = result.iterator(); it.hasNext();) {
//            Object obj;
//            obj = it.next();
//
//            if (obj instanceof EAnnotation) {
//                annotation = (EAnnotation) obj;
//                if (!(ManagerConstant.UMLDOMAIN_CONSTANT__FRAGMENT_CONTAINER_ANNOTATION_NAME.equals(annotation.getSource()))
//                    || annotation.eResource() != resource) {
//                    if (!resourceList.contains(annotation.eResource())) {
//                        resourceList.add(annotation.eResource());
//                    }
//                }
//            }
//        }

        Set<Resource> resourceList = new HashSet<Resource>();

        SELECT statement = new SELECT(new FROM(resource.getContents().get(0)),
            new WHERE(new EObjectTypeRelationCondition(UMLPackage.eINSTANCE.getPackage())));
        IQueryResult result = statement.execute();

        for (Iterator<?> ir = result.iterator(); ir.hasNext();) {
            Package pkg = (Package) ir.next();

            if (pkg == null || pkg.getQualifiedName() == null) {
                continue;
            }
            if (AdapterFactoryEditingDomain.isControlled(pkg)) {
                resourceList.add(pkg.eResource());
                continue;
            }
        }

        return resourceList;
    }
    
    /**
     * 
     * 모델에 포함된 단편화 패키지 목록을 반환 한다.
     *  
     * @param model
     * @return TreeMap<String,Package>
     */
    public static TreeMap<String, Package> getFragmentedPackageList(Package model) {

        TreeMap<String, Package> packageList = new TreeMap<String, Package>();

        SELECT statement = new SELECT(new FROM(model),
            new WHERE(new EObjectTypeRelationCondition(UMLPackage.eINSTANCE.getPackage())));
        IQueryResult result = statement.execute();

        for (Iterator<?> ir = result.iterator(); ir.hasNext();) {
            Package pkg = (Package) ir.next();

            if (pkg == null || pkg.getQualifiedName() == null) {
                continue;
            }
            if (AdapterFactoryEditingDomain.isControlled(pkg)) {
                packageList.put(pkg.getQualifiedName(), pkg);
                continue;
            }
        }

        return packageList;
     }
    
    
    
    /**
     * getUseCaseDisplayId
     *  
     * @param useCase
     * @return String
     */
    public static String getUseCaseDisplayId(UseCase useCase) {
        Element element = useCase;
        EAnnotation eAnnotation = element.getEAnnotation(ManagerConstant.USECASE_DISPLAY_ID_EANNOTATION_SOURCE_NAME);
        UseCaseDisplayId displayId = null;

        if (eAnnotation != null) {
            displayId = (UseCaseDisplayId) eAnnotation;
            String usecaseId = displayId.getDisplayId();
            return usecaseId;
        } else {
            return UICoreConstant.EMPTY_STRING;
        }
    }
    
    /**
     * useCollaboration
     *  
     * @param object
     * @return boolean
     */
    public static boolean useCollaboration(EObject object) {
//        try {
//            Resource rs = object.eResource();
//            IFile file = WorkspaceSynchronizer.getFile(rs);
//            IProject project = file.getProject();
//
//            boolean useProject = nexcore.alm.common.ui.util.ProjectUtil.useProject(project.getLocation());
//
//            return useProject;
//        } catch (Exception e) {}

        return false;
    }
    
    /**
     * getActiveUMLProjects
     *  
     * @return IProject[]
     */
    public static IProject[] getActiveUMLProjects() {
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        IProject[] projects = root.getProjects();
        List<IProject> activeUMLProject = new ArrayList<IProject>();
        for (IProject project : projects) {
            if (isActiveUMLProject(project)) {
                activeUMLProject.add(project);
            }
        }

        return activeUMLProject.toArray(new IProject[] {});
    }
}
