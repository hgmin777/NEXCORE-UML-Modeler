/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.core.navigator;

import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.ui.core.diagram.editor.AbstractDiagramEditor;
import nexcore.tool.uml.ui.core.diagram.editor.DiagramEditorInput;
import nexcore.tool.uml.ui.core.editor.input.UMLEditorInput;
import nexcore.tool.uml.ui.core.project.ITreeNode;
import nexcore.tool.uml.ui.core.project.UMLFileTreeNode;
import nexcore.tool.uml.ui.core.project.UMLTreeNode;
import nexcore.tool.uml.ui.core.registry.ProjectRegistry.UMLTreeNodeRegistry;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.navigator.ILinkHelper;
import org.eclipse.uml2.uml.Model;

/**
 * 
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.navigator</li>
 * <li>설  명 : LinkHelper</li>
 * <li>작성일 : 2012. 4. 9.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public class LinkHelper implements ILinkHelper {

    /**
     * @see org.eclipse.ui.navigator.ILinkHelper#findSelection(org.eclipse.ui.IEditorInput)
     */
    @Override
    public IStructuredSelection findSelection(IEditorInput anInput) {

        if (anInput instanceof DiagramEditorInput) {

            DiagramEditorInput editorInput = (DiagramEditorInput) anInput;
            EObject eobject = editorInput.getDiagram();

            ITreeNode node = UMLTreeNodeRegistry.getTreeNode(eobject);

            if (null != node) {
                return new StructuredSelection(node);
            }

        } else if (anInput instanceof UMLEditorInput) {

            UMLEditorInput editorInput = (UMLEditorInput) anInput;
            EObject eobject = editorInput.getElement();

            ITreeNode node = UMLTreeNodeRegistry.getTreeNode(eobject);

            if (null != node) {
                return new StructuredSelection(node);
            }
        }

        return StructuredSelection.EMPTY;
    }

    /**
     * @see org.eclipse.ui.navigator.ILinkHelper#activateEditor(org.eclipse.ui.IWorkbenchPage,
     *      org.eclipse.jface.viewers.IStructuredSelection)
     */
    @Override
    public void activateEditor(IWorkbenchPage page, IStructuredSelection selection) {

        if (selection == null || selection.isEmpty()) {
            return;
        }

        if (selection.getFirstElement() instanceof AbstractDiagramEditor) {

            IEditorPart editor = null;
            DiagramEditorInput editorInput = null;

            if ((editor = page.findEditor(editorInput)) != null) {
                page.bringToTop(editor);
            }

        } else if (selection.getFirstElement() instanceof UMLTreeNode) {
            UMLTreeNode treeNode = (UMLTreeNode) selection.getFirstElement();
            EObject eObject = treeNode.getEObject();

            if (eObject instanceof Diagram) {
                Diagram diagram = (Diagram) eObject;
                if (DomainUtil.isProxy(diagram)) {
                    URI uri = EcoreUtil.getURI(eObject);
                    diagram = (Diagram) DomainRegistry.getUMLDomain().getResourceSet().getEObject(uri, true);
                }
                Resource res = diagram.eResource();
                URI uri = res.getURI();
                DiagramEditorInput editorInput = new DiagramEditorInput(uri);
                editorInput.setDiagram(diagram);

                IEditorPart editor = null;
                if ((editor = page.findEditor(editorInput)) != null) {
                    page.bringToTop(editor);
                }

            }
        } else if (selection.getFirstElement() instanceof UMLFileTreeNode) {
            UMLFileTreeNode treeNode = (UMLFileTreeNode) selection.getFirstElement();
            if (treeNode.getEObject() instanceof Model) {

                Model model = (Model) treeNode.getEObject();
                UMLEditorInput editorInput = new UMLEditorInput(model);

                IEditorPart editor = null;
                if ((editor = page.findEditor(editorInput)) != null) {
                    page.bringToTop(editor);
                }
            }
        }
    }
}
