/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.realization.sequencediagram.action;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.model.umldiagram.UMLDiagramFactory;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.registry.IConstantImageRegistry;
import nexcore.tool.uml.ui.realization.sequencediagram.util.SequenceUtil;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.CombinedFragment;
import org.eclipse.uml2.uml.InteractionConstraint;
import org.eclipse.uml2.uml.InteractionOperand;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.action</li>
 * <li>설  명 : AddInteractionOperandAction</li>
 * <li>작성일 : 2011. 4. 28.</li>
 * <li>작성자 : zerotae</li>
 * </ul>
 */
public class AddInteractionOperandAction extends SelectionAction {

    /** Add INTERACTIONOPERAND Action ID */
    public static final String ADD_INTERACTIONOPERAND_ACTIONID = "AddInteractionOperand";

    /**
     * GUARD_LOCATION
     */
    private static final int GUARD_LOCATION = 10;

    /**
     * GUARD_HEIGHT
     */
    private static final int GUARD_HEIGHT = 20;
    
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
                if(NodeType.LOOP_WHILE.equals(selectedView.getNodeType())
                    || NodeType.LOOP_FOR.equals(selectedView.getNodeType())) {
                    return false;
                } else {
                    return true;
                }
            }
        }

        if (element instanceof ContainerNode) {
            selectedView = (ContainerNode) element;
            if (selectedView.getUmlModel() instanceof InteractionOperand) {
                if(NodeType.LOOP_WHILE.equals(((NotationNode) selectedView.getParent()).getNodeType())
                    || NodeType.LOOP_FOR.equals(((NotationNode) selectedView.getParent()).getNodeType())) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        
        return false;
    }

  
    /**
     * @param part
     */
    public AddInteractionOperandAction(IWorkbenchPart part) {
        super(part);
        setImageDescriptor(UiCorePlugin.getImageDescriptor(IConstantImageRegistry.ICONNAME_ADD_INTERACTIONOPERAND));
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
        setId(ADD_INTERACTIONOPERAND_ACTIONID);
        setText(UMLMessage.LABEL_ADD_INTERACTIONOPERAND);
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
                    createInteractionOperand();
                }
            });
    }
    
    /** if(...) */
    private static final String CONSTRAINT_IF = "if(...)"; //$NON-NLS-1$
    
    /** if(...) */
    private static final String CONSTRAINT_ELSEIF = "else if(...)"; //$NON-NLS-1$
    
    /** case */
    private static final String CONSTRAINT_CASE = "case :"; //$NON-NLS-1$
    
    /**
     * createInteractionOperand
     *   void
     */
    private void createInteractionOperand() {
        Object obj = getSelectedObjects().get(0);

        if (!(obj instanceof EditPart))
            return;
        Object element = ((EditPart) obj).getModel();

        NotationNode selectedView = null;
        if (element instanceof NotationNode) {
            selectedView = (NotationNode) element;
        } else if(element instanceof ContainerNode) {
            selectedView = (NotationNode) ((ContainerNode) element).getParent();
        }
        
        if(NodeType.OPTION_IF.equals(selectedView.getNodeType())) {
            insertInteractionOperandNode(selectedView, CONSTRAINT_IF);
        } else  if(NodeType.ALTERNATIVE_IF_ELSE.equals(selectedView.getNodeType())) {
            insertInteractionOperandNode(selectedView, CONSTRAINT_ELSEIF);
        } else  if(NodeType.ALTERNATIVE_SWITCH.equals(selectedView.getNodeType())) {
            insertInteractionOperandNode(selectedView, CONSTRAINT_CASE);
        }                  

    }
    
    
    /**
     * insertInteractionOperandNode
     *  
     * @param combinedFragementNode void
     */
    private void insertInteractionOperandNode(NotationNode combinedFragementNode, String constraintString){
        
        CombinedFragment combinedFragment = (CombinedFragment) combinedFragementNode.getUmlModel();
        
        InteractionOperand operand = combinedFragment.createOperand(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING);                    
        ContainerNode operandNode = UMLDiagramFactory.eINSTANCE.createContainerNode();
        operandNode.setNodeType(NodeType.INTERACTION_OPERAND);
        operandNode.setParent(combinedFragementNode);
        operandNode.setUmlModel(operand);
        operandNode.setHeight(UICoreConstant.OPERAND_MIN_HEIGHT);
        if(NodeType.ALTERNATIVE_IF_ELSE.equals(combinedFragementNode.getNodeType())
            ||NodeType.ALTERNATIVE_SWITCH.equals(combinedFragementNode.getNodeType())) {
            combinedFragment.getOperands().move(combinedFragment.getOperands().size()-1, operand);            
        }
        
        InteractionConstraint guard = operand.createGuard(null);
        guard.createSpecification(null, null, UMLPackage.Literals.OPAQUE_EXPRESSION);
        ((OpaqueExpression) guard.getSpecification()).getBodies().add(constraintString);
        NotationNode guardNode = UMLDiagramFactory.eINSTANCE.createNotationNode();
        guardNode.setNodeType(NodeType.GUARD);
        guardNode.setParent(operandNode);
        guardNode.setUmlModel(guard);   
        guardNode.setX(GUARD_LOCATION);
        guardNode.setY(GUARD_LOCATION);
        guardNode.setHeight(GUARD_HEIGHT);
        
        operandNode.getNodeList().add(guardNode);
        
        if(NodeType.ALTERNATIVE_IF_ELSE.equals(combinedFragementNode.getNodeType())
            ||NodeType.ALTERNATIVE_SWITCH.equals(combinedFragementNode.getNodeType())) {
            combinedFragementNode.getCompartmentList().add(combinedFragementNode.getCompartmentList().size()-1, operandNode);
        } else {
            combinedFragementNode.getCompartmentList().add(combinedFragementNode.getCompartmentList().size(), operandNode);            
        }
        combinedFragementNode.setHeight(combinedFragementNode.getHeight() + operandNode.getHeight());
        int operandY = SequenceUtil.getOperandY(combinedFragementNode, operandNode);
        SequenceUtil.shiftByMoveCombinedFragment(SequenceUtil.getDiagram(combinedFragementNode), combinedFragementNode, operandY, operandNode.getHeight());
    }

}
