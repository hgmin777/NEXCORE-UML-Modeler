/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.project.explorer.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nexcore.tool.uml.manager.command.DeleteUMLElementCommandFactory;
import nexcore.tool.uml.manager.transaction.UMLDiagramCommandStack;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.dialog.MergeUsecaseDialog;
import nexcore.tool.uml.ui.core.dialog.provider.ProjectItemLabelProvider;
import nexcore.tool.uml.ui.core.dialog.provider.ProjectItemTreeContentProvider;
import nexcore.tool.uml.ui.core.project.ITreeNode;
import nexcore.tool.uml.ui.core.registry.ProjectRegistry;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.UseCase;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.action</li>
 * <li>설 명 : MergeUsecaseAction</li>
 * <li>작성일 : 2011. 08. 08.</li>
 * <li>작성자 : 강석찬</li>
 * </ul>
 */
public class MergeUsecaseAction extends CommonActionDelegate {

    /** 선택한 객체들 */
    List<Element> selectedElementList;

    /** 선택한 객체 */
    private EObject firstSelectedEObject;

    /**
     * 
     */
    public MergeUsecaseAction() {
    }

    /**
     * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    public void run(IAction action) {

        MergeUsecaseDialog dialog = new MergeUsecaseDialog(UiCorePlugin.getShell(),
            new ProjectItemLabelProvider(),
            new ProjectItemTreeContentProvider(2));

        if (dialog.open() != Window.OK) {
            return;
        }

        UseCase mainUsecase;
        String usecaseName;

        if (dialog.getFirstResult() != null && dialog.getUsecaseName() != null) {
            mainUsecase = (UseCase) dialog.getFirstResult();
            usecaseName = dialog.getUsecaseName();
            
        } else {
            return;
        }

        // final String actionId = action.getId();
        ArrayList<EObject> removeList = new ArrayList<EObject>();
        for (Object obj : selectedElementList) {
            if ((obj instanceof UseCase) && (!obj.equals(mainUsecase))) {
                removeList.add((UseCase) obj);
            }
        }

        if (removeList == null) {
            return;
        }

        Command deleteCommand;
        CompoundCommand deleteCommandList = new CompoundCommand();
        MergeUsecaseDetailCommand addCommand = new MergeUsecaseDetailCommand(mainUsecase, usecaseName, removeList);
        deleteCommandList.add(addCommand);
        for (Object eobject : removeList) {
            deleteCommand = DeleteUMLElementCommandFactory.getCommand((Element) eobject);
            if (null == deleteCommand) {
                continue;
            } else {
                deleteCommandList.add(deleteCommand);
                ProjectRegistry.UMLTreeNodeRegistry.removeTreeNode(eobject);
            }
        }
        if (0 == deleteCommandList.getCommands().size()) {
            return;
        }
        ((UMLDiagramCommandStack) DomainRegistry.getUMLDomain().getCommandStackListener()).execute(deleteCommandList);
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

        IStructuredSelection structureSelection = (IStructuredSelection) selection;
        Object pickedUpObject;
        ITreeNode node;

        selectedElementList = new ArrayList<Element>();

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

    }

    public class MergeUsecaseDetailCommand extends Command {

        /** 병합할 유스케이스 */
        private UseCase mainUsecase;

        private String usecaseName;

        private ArrayList<EObject> removeList;

        public MergeUsecaseDetailCommand(UseCase mainUsecase, String usecaseName, ArrayList<EObject> removeList) {
            this.mainUsecase = mainUsecase;
            this.usecaseName = usecaseName;
            this.removeList = removeList;
        }

        /**
         * @see org.eclipse.gef.commands.Command#execute()
         */
        @Override
        public void execute() {

        }
    }
}
