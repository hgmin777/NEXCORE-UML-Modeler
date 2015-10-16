/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.project.explorer.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.ui.core.diagram.editor.DiagramEditorInput;
import nexcore.tool.uml.ui.core.editor.input.UMLEditorInput;
import nexcore.tool.uml.ui.core.project.IResourceSetListener;
import nexcore.tool.uml.ui.core.project.ITreeNode;
import nexcore.tool.uml.ui.core.registry.ProjectRegistry.ContentProviderRegistry;
import nexcore.tool.uml.ui.core.registry.ProjectRegistry.ViewerRegistry;
import nexcore.tool.uml.ui.core.util.ProjectUtil;
import nexcore.tool.uml.ui.core.util.TreeItemUtil;
import nexcore.tool.uml.ui.project.ProjectExplorerPlugin;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.change.util.ChangeRecorder;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonViewer;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.action</li>
 * <li>설 명 : BaseMultiFragmentAction</li>
 * <li>작성일 : 2011. 9. 26.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public abstract class BaseMultiFragmentAction extends CommonActionDelegate {

    /**
     * objects
     */
    protected List<EObject> objects = new ArrayList<EObject>();

    /**
     * removeTreeNode
     */
    protected List<URI> removeTreeNode = null;
    
    /**
     * saveResource
     */
    protected List<Resource> saveResource = null;

    /**
     * recorder
     */
    protected ChangeRecorder recorder = null;
    
    /**
     * dialog
     */
    ProgressMonitorDialog dialog = null;
    
    /**
     * rsListener
     */
    IResourceSetListener rsListener = null;
    
    /**
     * expanedTreePaths
     */
    TreePath[] expanedTreePaths = null;
    
    /**
     * resourceSet
     */
    private ResourceSet resourceSet;

    /**
     * editingDomain
     */
    private TransactionalEditingDomain editingDomain;
    
    /**
     * isContinue
     */
    boolean isContinue = true;
        
    /**
     * @see nexcore.tool.uml.ui.project.explorer.action.CommonActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    @Override
    public abstract void run(IAction action);

    /**
     * start
     *   void
     */
    public void start() {
        resourceSetinitialize();
        saveResource = new ArrayList<Resource>();

        recorder = new ChangeRecorder(DomainRegistry.getUMLDomain().getResourceSet());

//        skipOn();

        rsListener = ContentProviderRegistry.getContentProvider();
        if (rsListener != null) {
            rsListener.stopResourceListening();
        }
    }
    
    /**
     * done
     *   void
     */
    public void done() {
        isContinue = false;
        if (rsListener != null) {
            rsListener.startResourceListening();
        }

        dialog.getProgressMonitor().done();
        dialog.close();
//        skipOff();

        TreeItemUtil.expandTreePath(expanedTreePaths, selection);

        System.gc();
    }
    
    /**
     * resourceSetinitialize
     *   void
     */
    public void resourceSetinitialize() {
        resourceSet = DomainRegistry.getUMLDomain().getResourceSet();
        editingDomain = DomainRegistry.getUMLDomain().getTransactionalEditingDomain();
    }
    
    /**
     * rollBack
     *   void
     */
    protected void rollBack() {
        ProjectUtil.rollbackResourceSet(recorder);
    }
    
//    protected void skipOn() {
//        ProjectResourceSetListenerController.getInstance().fireSkipChangeEvent(true);
//    }
//
//    protected void skipOff() {
//        ProjectResourceSetListenerController.getInstance().fireSkipChangeEvent(false);
//    }

    /**
     * hierarchyCheck
     *  
     * @return boolean
     */
    protected boolean hierarchyCheck() {
        CommonViewer commonViewer = ViewerRegistry.getViewer();
        TreeSelection sel = (TreeSelection) commonViewer.getSelection();

        List<TreePath> pathList = Arrays.asList(sel.getPaths());

        for (TreePath path : pathList) {
            if (isContains(pathList, path.getParentPath())) {
                MessageDialog.openError(ProjectExplorerPlugin.getShell(),
                    UMLMessage.LABEL_FILE_FRAGMENTATION,
                    UMLMessage.MESSAGE_FRAGMENT_DEFRAGMENT_ERROR_MESSAGE/*"패키지 간 상하 관계가 포함된 경우 일괄 단편화/병합을 실행할 수 없습니다."*/);
                return false;
            }
        }

        return true;
    }

    /**
     * 선택한 패키지간의 parent - child 관계가 있는지 체크하는 로직
     * 
     * 
     * @param pathList
     * @param treePath
     * @return boolean
     */
    protected boolean isContains(List<TreePath> pathList, TreePath treePath) {
        if (treePath == null) {
            return false;
        }
        if (pathList.contains(treePath)) {
            return true;
        } else {
            if (treePath.getParentPath() != null) {
                return isContains(pathList, treePath.getParentPath());
            }
        }
        return false;
    }

    /**
     * @see nexcore.tool.uml.ui.project.explorer.action.CommonActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
     */
    public void selectionChanged(IAction action, ISelection selection) {
        if (!(selection instanceof IStructuredSelection))
            return;

        if (removeTreeNode == null) {
            removeTreeNode = new ArrayList<URI>();
        } else {
            removeTreeNode.clear();
        }

        objects.clear();
        this.selection = selection;

        for (Iterator<?> iterator = ((IStructuredSelection) selection).iterator(); iterator.hasNext();) {
            Object object = (Object) iterator.next();

            if (object instanceof ITreeNode) {
                ITreeNode node = (ITreeNode) object;
                if (!DomainUtil.isProxy(node.getEObject())) {
                    objects.add(node.getEObject());
                    removeTreeNode.add((EcoreUtil.getURI(node.getEObject())));
                } else {
                    System.err.println(node.getEObject());
                }
            }
        }
    }
    
    /**
     * closeEditor
     *   void
     */
    public void closeEditor() {
        for (final IWorkbenchWindow window : PlatformUI.getWorkbench().getWorkbenchWindows()) {
            IEditorReference[] editorRefs = window.getActivePage().getEditorReferences();

            for (IEditorReference ref : editorRefs) {
                final IEditorPart editor = ref.getEditor(false);
                if (editor != null && editor.getEditorInput() instanceof UMLEditorInput) {
                    ViewerRegistry.getViewer().getControl().getDisplay().syncExec(new Runnable() {
                        public void run() {
                            window.getActivePage().closeEditor(editor, false);
                        }
                    });
                } else if (editor != null && editor.getEditorInput() instanceof DiagramEditorInput) {
                    ViewerRegistry.getViewer().getControl().getDisplay().syncExec(new Runnable() {
                        public void run() {
                            window.getActivePage().closeEditor(editor, false);
                        }
                    });
                }
            }
        }
    }
    
    /**
     * @return the editingDomain
     */
    public TransactionalEditingDomain getEditingDomain() {
        return editingDomain;
    }

    /**
     * @return the resourceSet
     */
    public ResourceSet getResourceSet() {
        return resourceSet;
    }
}
