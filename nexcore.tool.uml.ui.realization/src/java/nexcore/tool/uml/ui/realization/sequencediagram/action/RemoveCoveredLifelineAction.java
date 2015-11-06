/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.realization.sequencediagram.action;

import java.util.ArrayList;
import java.util.List;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.registry.IConstantImageRegistry;
import nexcore.tool.uml.ui.realization.sequencediagram.dialog.RemoveCoveredLifelineDialog;
import nexcore.tool.uml.ui.realization.sequencediagram.util.SequenceUtil;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.CombinedFragment;
import org.eclipse.uml2.uml.InteractionOperand;
import org.eclipse.uml2.uml.Lifeline;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.action</li>
 * <li>설  명 : RemoveCoveredLifelineAction</li>
 * <li>작성일 : 2011. 5. 24.</li>
 * <li>작성자 : zerotae</li>
 * </ul>
 */
public class RemoveCoveredLifelineAction extends SelectionAction {

    /** Remove CoveredLifeline Action ID */
    public static final String REMOVE_COVERED_LIFELINE_ACTIONID = "RemoveCoveredLifeline";

    /**
     * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#isEnabled()
     */
    @Override
    public boolean isEnabled() {

        Object obj = getSelectedObjects().get(0);
        Object element = ((EditPart) obj).getModel();

        AbstractNode selectedView = null;
        
        if (element instanceof NotationNode) {
            selectedView = (NotationNode) element;
            if (selectedView.getUmlModel() instanceof CombinedFragment) {
                return true;
            }
        }

        if (element instanceof ContainerNode) {
            selectedView = (ContainerNode) element;
            if (selectedView.getUmlModel() instanceof InteractionOperand) {
                return true;                
            }
        }
        
        return false;
    }

  
    /**
     * @param part
     */
    public RemoveCoveredLifelineAction(IWorkbenchPart part) {
        super(part);
        setImageDescriptor(UiCorePlugin.getImageDescriptor(IConstantImageRegistry.ICONNAME_REMOVE_COVERED_LIFELINE));
    }

    /**
     * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#calculateEnabled()
     */
    @Override
    protected boolean calculateEnabled() {
        return true;
    }

    /**
     * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#init()
     */
    protected void init() {
        setId(REMOVE_COVERED_LIFELINE_ACTIONID);
        setText(UMLMessage.LABEL_REMOVE_COVERED_LIFELINE);
    }

    /**
     * @see org.eclipse.jface.action.Action#run()
     */
    public void run() {
        /*
         * DomainRegistry.getUMLDomain().getCommandStack().execute(new Command()
         * {
         * 
         * @Override public void execute() { createAttribute(); } });
         */
        DomainRegistry.getEditingDomain()
            .getCommandStack()
            .execute(new RecordingCommand(DomainRegistry.getEditingDomain()) {
                @Override
                protected void doExecute() {
                    removeCoveredLifeline();
                }
            });
    }
   
    /**
     * removeCoveredLifeline
     *   void
     */
    private void removeCoveredLifeline() {
        Object obj = getSelectedObjects().get(0);

        if (!(obj instanceof EditPart))
            return;
        AbstractNode node = (AbstractNode) ((EditPart) obj).getModel();

        NotationNode selectedCombinedFragmentNode = null;
        if (node instanceof NotationNode) {
            selectedCombinedFragmentNode = (NotationNode) node;
        } else if(node instanceof ContainerNode) {
            selectedCombinedFragmentNode = (NotationNode) ((ContainerNode) node).getParent();
        }
        
        Diagram diagram = SequenceUtil.getDiagram(selectedCombinedFragmentNode);
        CombinedFragment combinedFragment = (CombinedFragment) selectedCombinedFragmentNode.getUmlModel();

        EList<Lifeline> covereds = combinedFragment.getCovereds();
        List<Lifeline> lifelineList = new ArrayList<Lifeline>();

        for(Lifeline lifeline : covereds) {
            lifelineList.add(lifeline);
        }
        
        RemoveCoveredLifelineDialog dialog = new RemoveCoveredLifelineDialog(UiCorePlugin.getShell(), lifelineList);
        List<Lifeline> removeLifelineList = null;
        if (dialog.open() == Window.OK) {
            removeLifelineList = dialog.getRemoveLifelineList();
        }
        
        if(removeLifelineList != null && removeLifelineList.size() > 0) {
            SequenceUtil.removeCoveredLifelineList(diagram, selectedCombinedFragmentNode, removeLifelineList);     
        } else {
            return;
        }
        
        SequenceUtil.calculateCoverdsLocationAndSize(diagram, selectedCombinedFragmentNode, combinedFragment);
                
    }
    
}
