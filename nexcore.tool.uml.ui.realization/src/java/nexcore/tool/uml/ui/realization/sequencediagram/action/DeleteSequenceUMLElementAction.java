/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.realization.sequencediagram.action;

import java.util.List;

import nexcore.tool.uml.manager.transaction.UMLDiagramCommandStack;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.manager.utility.DomainUtil;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.Attachement;
import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.LifeLineNode;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.ui.core.diagram.action.DeleteUMLElementAction;
import nexcore.tool.uml.ui.core.diagram.command.DeleteConnectionCommand;
import nexcore.tool.uml.ui.core.diagram.command.DeleteNodeCommand;
import nexcore.tool.uml.ui.core.diagram.edit.part.AbstractChildCompartmentEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.AbstractDiagramEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.AbstractParentCompartmentEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.AttachementEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.NoteEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.TextEditPart;
import nexcore.tool.uml.ui.realization.sequencediagram.command.DeleteCombinedFragmentCommand;
import nexcore.tool.uml.ui.realization.sequencediagram.command.DeleteInteractionOperandCommand;
import nexcore.tool.uml.ui.realization.sequencediagram.command.DeleteLifeLineCommand;
import nexcore.tool.uml.ui.realization.sequencediagram.command.DeleteMessageConnectionCommand;
import nexcore.tool.uml.ui.realization.sequencediagram.edit.part.CombinedFragmentEditPart;
import nexcore.tool.uml.ui.realization.sequencediagram.edit.part.InteractionOperandEditPart;
import nexcore.tool.uml.ui.realization.sequencediagram.edit.part.LifeLineEditPart;
import nexcore.tool.uml.ui.realization.sequencediagram.edit.part.LifeLineNameEditPart;
import nexcore.tool.uml.ui.realization.sequencediagram.edit.part.MessageEditPart;
import nexcore.tool.uml.ui.realization.sequencediagram.util.SequenceUtil;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.CombinedFragment;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.action</li>
 * <li>설 명 : DeleteSequenceUMLElementAction</li>
 * <li>작성일 : 2010. 2. 22.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class DeleteSequenceUMLElementAction extends DeleteUMLElementAction {

    /**
     * @param part
     */
    public DeleteSequenceUMLElementAction(IWorkbenchPart part) {
        super(part);
    }

    /**
     * @param part
     * @param messageFlag
     *            확인 메세지박스를 보여줄 것인지 결정하는 플래그.
     */
    public DeleteSequenceUMLElementAction(IWorkbenchPart part, boolean messageFlag) {
        this(part);
        this.showMessage = messageFlag;
    }

    /**
     * @see nexcore.tool.uml.ui.core.diagram.action.DeleteUMLElementAction#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        int count = this.getSelectedObjects().size();
        if (0 == count) {
            return false;
        } else if (1 == count) {
            Object object = this.getSelectedObjects().get(0);
            if ((object instanceof AbstractDiagramEditPart) || (object instanceof AbstractParentCompartmentEditPart)) {
                return false;
            }
        }
        
        Object obj = this.getSelectedObjects().get(0);
        if(obj instanceof EditPart){
            Object model = ((EditPart)obj).getModel();
            if(model instanceof AbstractView){
                if( model instanceof AbstractNode ) {
                    if( NodeType.NOTE.equals( ((AbstractNode)model).getNodeType()) ||  NodeType.TEXT.equals( ((AbstractNode)model).getNodeType()) ) {
                        return true;
                    }
                }
                if ( null == ((AbstractView)model).getUmlModel() ) {
                    if( model instanceof Attachement ) {
                        return true;
                    }
                    return false;
                }
                if( DomainUtil.isProxy(((AbstractView)model).getUmlModel()) ){
                    return false;
                }
            }
        }
        
        return true;
    }
    
    /**
     * @see org.eclipse.jface.action.Action#run()
     */
    @SuppressWarnings("unchecked")
    public void run() {
        try {
            List<EditPart> editParts = (List<EditPart>) getSelectedObjects();
            CompoundCommand compoundCommand = new CompoundCommand();
            Command deleteCommand = null;
            for (EditPart editPart : editParts) {

                if (editPart instanceof AbstractChildCompartmentEditPart) {
                    continue;
                } else if (editPart instanceof LifeLineEditPart) {
                    deleteCommand = new DeleteLifeLineCommand();
                    ((DeleteLifeLineCommand) deleteCommand).setDiagram(SequenceUtil.getDiagram(editPart));
                    ((DeleteLifeLineCommand) deleteCommand).setLifeLineNode((LifeLineNode) editPart.getModel());
                    compoundCommand.add(deleteCommand);
                } else if (editPart instanceof LifeLineNameEditPart) {
                    LifeLineEditPart lifeLineEditPart = (LifeLineEditPart) editPart.getParent();
                    deleteCommand = new DeleteLifeLineCommand();
                    ((DeleteLifeLineCommand) deleteCommand).setDiagram(SequenceUtil.getDiagram(lifeLineEditPart));
                    ((DeleteLifeLineCommand) deleteCommand).setLifeLineNode((LifeLineNode) lifeLineEditPart.getModel());
                    compoundCommand.add(deleteCommand);
                } else if (editPart instanceof CombinedFragmentEditPart) {
                        deleteCommand = new DeleteCombinedFragmentCommand();
                        ((DeleteCombinedFragmentCommand) deleteCommand).setParentNode((AbstractNode) editPart.getParent().getModel());
                        ((DeleteCombinedFragmentCommand) deleteCommand).setNode((NotationNode) editPart.getModel());
                        compoundCommand.add(deleteCommand);                    
                } else if (editPart instanceof InteractionOperandEditPart) {
                    NotationNode parentNode = (NotationNode) editPart.getParent().getModel();
                    CombinedFragment combinedFragment = (CombinedFragment) parentNode.getUmlModel();
                    if(combinedFragment.getOperands().size() > 1) {
                        deleteCommand = new DeleteInteractionOperandCommand();
                        ((DeleteInteractionOperandCommand) deleteCommand).setParentNode(parentNode);
                        ((DeleteInteractionOperandCommand) deleteCommand).setNode((ContainerNode) editPart.getModel());
                        compoundCommand.add(deleteCommand);  
                    }
                } else if (editPart instanceof MessageEditPart) {
                    deleteCommand = new DeleteMessageConnectionCommand();
                    ((DeleteMessageConnectionCommand) deleteCommand).setConnection(editPart.getModel());
                    ((DeleteMessageConnectionCommand) deleteCommand).setDiagram((Diagram) ((ScalableFreeformRootEditPart) editPart.getParent()).getContents()
                        .getModel());
                    compoundCommand.add(deleteCommand);
                } else if (editPart instanceof AttachementEditPart) {
                    deleteCommand = new DeleteConnectionCommand();
                    ((DeleteConnectionCommand) deleteCommand).setConnection(editPart.getModel());
                    ((DeleteConnectionCommand) deleteCommand).setDiagram((Diagram) ((ScalableFreeformRootEditPart) editPart.getParent()).getContents()
                        .getModel());
                    compoundCommand.add(deleteCommand);
                } else if ((editPart instanceof NoteEditPart) || (editPart instanceof TextEditPart)) {
                    deleteCommand = new DeleteNodeCommand();
                    ((DeleteNodeCommand) deleteCommand).setParent(editPart.getParent().getModel());
                    ((DeleteNodeCommand) deleteCommand).setNode(editPart.getModel());
                    compoundCommand.add(deleteCommand);
                }
            }

            if (null != compoundCommand) {
                // DomainRegistry.getUMLDomain().getCommandStack().execute(compoundCommand);

                ((UMLDiagramCommandStack) DomainRegistry.getUMLDomain().getCommandStackListener()).execute(compoundCommand);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
