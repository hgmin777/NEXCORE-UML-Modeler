/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.core.project.explorer.provider;

import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.project.ClosedTreeNode;
import nexcore.tool.uml.ui.core.project.ITreeNode;
import nexcore.tool.uml.ui.core.registry.ResourceManager;
import nexcore.tool.uml.ui.core.util.ProjectUtil;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.Model;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.project.explorer.provider</li>
 * <li>설  명 : OpenAction</li>
 * <li>작성일 : 2011. 12. 9.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public class OpenAction extends Action {
    /**
     * selection
     */
    ISelection selection;

    /**
     * getSelection
     *  
     * @return ISelection
     */
    public ISelection getSelection() {
        return selection;
    }

    /**
     * selectionChanged
     *  
     * @param selection void
     */
    public void selectionChanged(ISelection selection) {
        this.selection = selection;
    }

    /**
     * 
     */
    public OpenAction(ISelection selection) {
        this.selection = selection;
    }

    /**
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        try {
            ISelection sel = selection;
            if (sel instanceof TreeSelection) {
                TreeSelection tSel = (TreeSelection) sel;
                Object obj = tSel.getFirstElement();
                if (obj instanceof ITreeNode) {
                    EObject eobject = ((ITreeNode) obj).getEObject();
                    if (eobject instanceof Diagram) {
                        ProjectUtil.openDiagram(eobject);
                    } else if (eobject instanceof Model) {
                        ProjectUtil.openEditor(eobject);
                    }
                } else if (obj instanceof IFile) {
                    IFile file = (IFile) obj;
                    String extension = file.getFileExtension();
                    if (UICoreConstant.DEVELOPER_FILE_EXTENSION.equals(extension)) {
                        ProjectUtil.openMDAEditor(obj, UICoreConstant.DEVELOPER_RULE_EDITOR_ID);

                    } else if (UICoreConstant.DESIGNER_FILE_EXTENSION.equals(extension)) {
                        ProjectUtil.openMDAEditor(obj, UICoreConstant.DESIGNER_RULE_EDITOR_ID);
                    
                    } else if (UICoreConstant.DEVELOPER_REVERSE_FILE_EXTENSION.equals(extension)) {
                        ProjectUtil.openMDAEditor(obj, UICoreConstant.DEVELOPER_REVERSE_RULE_EDITOR_ID);
                    }
                    
                } else if( obj instanceof ClosedTreeNode) {
                    ResourceManager.getInstance().addResourceLoadedAdapter();
                    
                    final IResource resource = ((ClosedTreeNode) obj).getResource();
                    URI uri = URI.createURI(resource.getFullPath().toString());
                    ResourceManager.getInstance().setActivation(uri, true);
                    
                    Display.getDefault().syncExec(new Runnable() {
                        
                        @Override
                        public void run() {
                            ProjectUtil.refreshWholeExplorer();
                        }
                    });
                    
                    ResourceManager.getInstance().removeResourceLoadedAdapter();
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @see org.eclipse.jface.action.Action#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        ISelection sel = selection;
        if (sel instanceof TreeSelection) {
            TreeSelection tSel = (TreeSelection) sel;
            Object obj = tSel.getFirstElement();
            if( obj instanceof ClosedTreeNode) {
                return true;
            } else if (obj instanceof ITreeNode) {
                EObject eobject = ((ITreeNode) obj).getEObject();
                if (eobject instanceof Diagram || eobject instanceof Model) {
                    return true;
                } else {
                    return false;
                }
            } else if (obj instanceof IFile) {
                IFile file = (IFile) obj;
                String extension = file.getFileExtension();
                if (UICoreConstant.DEVELOPER_FILE_EXTENSION.equals(extension)
                    || UICoreConstant.DESIGNER_FILE_EXTENSION.equals(extension)
                    || UICoreConstant.DEVELOPER_REVERSE_FILE_EXTENSION.equals(extension)) {
                    return true;
                }
            } else {
                return false;
            }
        }

        return super.isEnabled();
    }
}
