/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.project.explorer.action;

import java.util.ArrayList;
import java.util.List;

import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.ui.core.registry.ProjectRegistry.ViewerRegistry;
import nexcore.tool.uml.ui.core.util.ProjectUtil;
import nexcore.tool.uml.ui.project.explorer.command.CopyCommand;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.Abstraction;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.BehaviorExecutionSpecification;
import org.eclipse.uml2.uml.CombinedFragment;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.ControlFlow;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.ExecutionEvent;
import org.eclipse.uml2.uml.ExecutionOccurrenceSpecification;
import org.eclipse.uml2.uml.Extend;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Include;
import org.eclipse.uml2.uml.InteractionOperand;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageOccurrenceSpecification;
import org.eclipse.uml2.uml.ObjectFlow;
import org.eclipse.uml2.uml.Realization;
import org.eclipse.uml2.uml.ReceiveOperationEvent;
import org.eclipse.uml2.uml.SendOperationEvent;
import org.eclipse.uml2.uml.Usage;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.project</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.project.explorer.action</li>
 * <li>설 명 : CopyAction</li>
 * <li>작성일 : 2009. 12. 18.</li>
 * <li>작성자 : 황선림</li>
 * </ul>
 */
public class CopyAction extends Action implements IObjectActionDelegate {

    /** List to copy. */
    private List<EObject> objList;

    /**
     * 
     */
    public CopyAction() {
        objList = new ArrayList<EObject>();
    }

    /**
     * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction,
     *      org.eclipse.ui.IWorkbenchPart)
     */
    public void setActivePart(IAction action, IWorkbenchPart targetPart) {
        // TODO Auto-generated method stub

    }

    /**
     * 'Ctrl+C' key action.
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

        if (objList == null || objList.size() < 1) {
            return;
        }

        final CopyCommand copyCommand = new CopyCommand(objList);
        // DomainRegistry.getUMLDomain().getCommandStack().execute(copyCommand);
        DomainRegistry.getEditingDomain()
            .getCommandStack()
            .execute(new RecordingCommand(DomainRegistry.getEditingDomain()) {
                @Override
                protected void doExecute() {
                    copyCommand.execute();
                }
            });
    }

    /**
     * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
     *      org.eclipse.jface.viewers.ISelection)
     */
    public void selectionChanged(IAction action, ISelection selection) {
        objList.clear();
        objList = ProjectUtil.getSelectedEObjects(selection);

        if (objList == null || objList.size() < 1) {
            return;
        }

        for (EObject eobject : objList) {
            if (eobject instanceof Lifeline || eobject instanceof Abstraction || eobject instanceof Association
                || eobject instanceof BehaviorExecutionSpecification || eobject instanceof CombinedFragment
                || eobject instanceof Comment || eobject instanceof Connector || eobject instanceof ControlFlow
                || eobject instanceof Dependency || eobject instanceof ExecutionEvent
                || eobject instanceof ExecutionOccurrenceSpecification || eobject instanceof Extend
                || eobject instanceof Generalization || eobject instanceof Include
                || eobject instanceof InteractionOperand || eobject instanceof InterfaceRealization
                || eobject instanceof Message || eobject instanceof MessageOccurrenceSpecification
                || eobject instanceof ObjectFlow || eobject instanceof Realization
                || eobject instanceof ReceiveOperationEvent || eobject instanceof SendOperationEvent
                || eobject instanceof Usage) {
                objList.clear();
                return;
            }
        }
    }

}
