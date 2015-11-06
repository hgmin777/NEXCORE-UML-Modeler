/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.analysis.activitydiagram.action;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.transaction.UMLDiagramCommandStack;
import nexcore.tool.uml.manager.utility.DomainRegistry;
import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.Attachement;
import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.model.umldiagram.Relation;
import nexcore.tool.uml.ui.analysis.activitydiagram.command.DeleteActivityConnectionCommand;
import nexcore.tool.uml.ui.analysis.activitydiagram.command.DeleteActivityPartitionNodeCommand;
import nexcore.tool.uml.ui.analysis.activitydiagram.command.DeleteControlNodeCommand;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.diagram.action.DeleteUMLElementAction;
import nexcore.tool.uml.ui.core.diagram.command.DeleteNodeCommand;
import nexcore.tool.uml.ui.core.diagram.edit.part.NoteEditPart;
import nexcore.tool.uml.ui.core.diagram.edit.part.TextEditPart;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Relationship;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.analysis</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.analysis.activitydiagram.action</li>
 * <li>설  명 : DeleteActivityUMLElementAction</li>
 * <li>작성일 : 2011. 7. 13.</li>
 * <li>작성자 : Kang</li>
 * </ul>
 */
public class DeleteActivityUMLElementAction extends DeleteUMLElementAction {

    /**
     * @param part
     */
    public DeleteActivityUMLElementAction(IWorkbenchPart part) {
        super(part);
    }

    /**
     * @see org.eclipse.jface.action.Action#run()
     */
    public void run() {
        Object element;
        Element uml;
        Command deleteCommand;
        CompoundCommand deleteCommandList = new CompoundCommand();
        String elementID;
        Object model;
        if (0 == getSelectedObjects().size()) {
            return;
        }
        if (showMessage) {
            if (1 == getSelectedObjects().size()) {
                element = getSelectedObjects().get(0);
                elementID = UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;
                if (element instanceof NamedElement) {
                    elementID = ((NamedElement) element).getName();
                    if (null == elementID) {
                        elementID = ((NamedElement) element).eClass().getName();
                    }
                } else if (element instanceof Diagram) {
                    elementID = ((Diagram) element).getName();

                } else if (element instanceof EditPart) {
                    model = ((EditPart) element).getModel();
                    if (model instanceof AbstractView) {
                        uml = ((AbstractView) ((EditPart) element).getModel()).getUmlModel();
                    } else {
                        uml = (Element) model;
                    }
                    if (uml instanceof Relationship) {
                        elementID = "Relationship";
                    } else if (uml instanceof NamedElement) {
                        elementID = ((NamedElement) uml).eClass().getName();
                    } else if ((element instanceof NoteEditPart) || (element instanceof TextEditPart)) {
                        elementID = getSelectedObjects().size() + " elements";
                    }
                }

            } else {
                elementID = getSelectedObjects().size() + " elements";
            }

            if (!MessageDialog.openConfirm(UiCorePlugin.getShell(),
                UMLMessage.getMessage(UMLMessage.LABEL_DELETE_FROM_MODEL),
                UMLMessage.getMessage(UMLMessage.MESSAGE_DELETE, elementID))) {
                return;
            }
        }
        
        for (Object obj : getSelectedObjects()) {
            if (!(obj instanceof EditPart)) {
                continue;
            }
            element = ((EditPart) obj).getModel();
            if (!(element instanceof Element)) {
                continue;
            }
            
            // 삭제할 대상이 액티비티 파티션일때 일반 노드의 삭제와는 다른면이 많아서 따로 처리한다.
            if ( element instanceof ContainerNode ) {
                ContainerNode containerNode = (ContainerNode) element;
                if( NodeType.ACTIVITY_PARTITION.equals(containerNode.getNodeType()) ) {
                    DeleteActivityPartitionNodeCommand command = new DeleteActivityPartitionNodeCommand();
                    command.setNode(containerNode);
                    if( null != containerNode.eContainer() ) {
                        command.setParent(containerNode.eContainer());
                    }
                    
                    deleteCommandList.add(command);
                }
            
            // 일반 Element 삭제
            } else {
                if ( element instanceof NotationNode ) {
                    NotationNode notationNode = (NotationNode) element;
                    DeleteControlNodeCommand command = new DeleteControlNodeCommand();
                    command.setNode( notationNode );
                    command.setParent( notationNode.eContainer() );
                    
                    deleteCommandList.add( command );
                
                } else if ( element instanceof Relation ) {
                    Relation relation = (Relation) element;
                    DeleteActivityConnectionCommand command = new DeleteActivityConnectionCommand();
                    command.setConnection( relation );
                    if( relation.eContainer() instanceof Diagram ) {
                        command.setDiagram( (Diagram) relation.eContainer() );
                    }
                    
                    deleteCommandList.add( command );
                }
                
                // Attachement에 연결된 UML 요소 [모델에서 삭제] 실행 시 Attachement를 지워준다.
                // 기존의 [모델에서 삭제] 실행 시에는 Attachement가 삭제 되지 않았다.
                if ( element instanceof AbstractNode ) {
                    EList<AbstractConnection> incomingList = ((AbstractNode)element).getIncomingConnectionList();
                    EList<AbstractConnection> outgoingList = ((AbstractNode)element).getOutgoingConnectionList();
                    
                    boolean hasAttachement = false;
                    for( AbstractConnection connection : incomingList ) {
                        if( connection instanceof Attachement ) {
                            hasAttachement = true;
                        }
                    }
                    for( AbstractConnection connection : outgoingList ) {
                        if( connection instanceof Attachement ) {
                            hasAttachement = true;
                        }
                    }
                    if(hasAttachement){
                        deleteCommand = new DeleteNodeCommand();
                        ((DeleteNodeCommand) deleteCommand).setParent(((EditPart) obj).getParent().getModel());
                        ((DeleteNodeCommand) deleteCommand).setNode(((EditPart) obj).getModel());
                        deleteCommandList.add(deleteCommand);
                    }
                }
                //
                //
            }

            }

        
        if (0 != deleteCommandList.getCommands().size()) {
            ((UMLDiagramCommandStack) DomainRegistry.getUMLDomain().getCommandStackListener()).execute(deleteCommandList);
        }

    }
}
