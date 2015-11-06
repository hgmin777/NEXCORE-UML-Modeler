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

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.ui.core.project.ITreeNode;
import nexcore.tool.uml.ui.project.explorer.action.registry.UMLExplorerExtendedActionRegistry;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.uml2.uml.Element;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.action</li>
 * <li>설 명 : UMLExplorerExtendedActionDelegate</li>
 * <li>작성일 : 2010. 11. 16.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class UMLExplorerExtendedActionDelegate implements IObjectActionDelegate {

    /** 워크벤치파트 사이트 */
    private IWorkbenchPartSite site;

    /** 부모 쉘 */
    private Shell parentShell;

    /** 실행할 UML Explorer 확장 액션 */
    private AbstractUMLExplorerExtendedAction umlExplorerExtendedAction;

    /** 선택한 객체 */
    private EObject firstSelectedEObject;

    /**
     * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction,
     *      org.eclipse.ui.IWorkbenchPart)
     */
    public void setActivePart(IAction action, IWorkbenchPart targetPart) {
        // 워크벤치파트 사이트 가져오기
        this.site = targetPart.getSite();
        // 부모 쉘 가져오기
        if (site != null) {
            this.parentShell = site.getShell();
        }

        // ID에 해당하는 액션 인스턴스 가져오기
        if (UMLExplorerExtendedActionRegistry.getInstance() != null) {
            this.umlExplorerExtendedAction = UMLExplorerExtendedActionRegistry.getInstance()
                .getProperAction(action.getId());
        }

        if (umlExplorerExtendedAction != null) {
            // 액션의 라벨 설정
            umlExplorerExtendedAction.setLabel(action.getText());
            // 진행 모니터 창에서 사용할 쉘 설정
            umlExplorerExtendedAction.setShell(parentShell);
        }
    }

    /**
     * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    public void run(IAction action) {
        if (umlExplorerExtendedAction != null) {
            umlExplorerExtendedAction.run();
        } else {
            MessageDialog.openInformation(parentShell,
                UMLMessage.LABEL_TO_BE_DETERMINED,
                UMLMessage.MESSAGE_TO_BE_DETERMINED);
        }
    }

    /**
     * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
     *      org.eclipse.jface.viewers.ISelection)
     */
    @SuppressWarnings("unchecked")
    public void selectionChanged(IAction action, ISelection selection) {

        if (!(selection instanceof IStructuredSelection)) {
            return;
        }

        // 액션의 선택 객체 설정
        IStructuredSelection structureSelection = (IStructuredSelection) selection;
        Object pickedUpObject;
        ITreeNode node;

        List<Element> selectedElementList = new ArrayList<Element>();

        for (Iterator<Object> structureSelectionIterator = structureSelection.iterator(); structureSelectionIterator.hasNext();) {
            pickedUpObject = structureSelectionIterator.next();

            if (pickedUpObject instanceof ITreeNode) {
                node = (ITreeNode) pickedUpObject;

                selectedElementList.add((Element) node.getEObject());

                if (selectedElementList.size() == 1) {
                    firstSelectedEObject = (Element) node.getEObject();
                }
            }
        }

        if (!selectedElementList.isEmpty()) {
            umlExplorerExtendedAction.setSelectedElementList(selectedElementList);
        }
        /*
         * Object object = ((IStructuredSelection) selection).getFirstElement();
         * if(object instanceof ITreeNode) { ITreeNode node = (ITreeNode)
         * object;
         * 
         * firstSelectedEObject = node.getEObject();
         * 
         * if(firstSelectedEObject != null) { List<Element> selectedElementList
         * = new ArrayList<Element>(); selectedElementList.add((Element)
         * firstSelectedEObject);
         * 
         * 
         * 
         * umlExplorerExtendedAction.setSelectedElementList(selectedElementList);
         * } }
         */
    }

}
