/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.search.action;

import nexcore.tool.uml.ui.core.project.UMLTreeNode;
import nexcore.tool.uml.ui.project.explorer.action.CommonActionDelegate;
import nexcore.tool.uml.ui.search.match.UMLInverseReferenceModelSearchPattern;
import nexcore.tool.uml.ui.search.match.UMLInverseReferenceModelSearchQuery;
import nexcore.tool.uml.ui.search.util.UMLInverseReferenceModelSearch;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.search.ui.ISearchQuery;
import org.eclipse.search.ui.NewSearchUI;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.NamedElement;

/**
 * 
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.search</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.search.action</li>
 * <li>설  명 : SearchOfInverseReference</li>
 * <li>작성일 : 2012. 8. 27.</li>
 * <li>작성자 : nspark</li>
 * </ul>
 */
public class SearchOfInverseReference extends CommonActionDelegate {

//    private NamedElement namedElement;
    
    protected EObject namedElement;

    @Override
    public void run(IAction action) {
        if(namedElement == null) {
            return;
        }
        UMLInverseReferenceModelSearch searcher = new UMLInverseReferenceModelSearch();

        UMLInverseReferenceModelSearchPattern pattern = new UMLInverseReferenceModelSearchPattern((NamedElement)namedElement);

        ISearchQuery query = new UMLInverseReferenceModelSearchQuery(searcher, pattern);

        NewSearchUI.runQueryInBackground(query);
    }

    ISelection selection;

    @Override
    public void selectionChanged(IAction action, ISelection selection) {
        this.selection = selection;

        if (selection instanceof StructuredSelection) {
            StructuredSelection ss = (StructuredSelection) selection;

            Object obj = ss.getFirstElement();

            if (obj instanceof UMLTreeNode) {
                UMLTreeNode treeNode = (UMLTreeNode) obj;
                EObject element = treeNode.getEObject();
                if (element instanceof NamedElement) {
                    namedElement = (NamedElement) element;
                } else {
                    namedElement = null;
                }
            } else {
                namedElement = null;
            }
        }
        
//        super.selectionChanged(action, selection);
    }

    @Override
    public void setActivePart(IAction action, IWorkbenchPart targetPart) {

    }

}
