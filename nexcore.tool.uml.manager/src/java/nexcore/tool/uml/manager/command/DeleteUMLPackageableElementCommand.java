/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.manager.command;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.Diagram;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.ReceiveOperationEvent;
import org.eclipse.uml2.uml.Relationship;
import org.eclipse.uml2.uml.SendOperationEvent;
import org.eclipse.uml2.uml.Type;


/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.manager</li>
 * <li>서브 업무명 : nexcore.tool.uml.manager.command</li>
 * <li>설  명 : DeleteUMLPackageableElementCommand</li>
 * <li>작성일 : 2009. 12. 28.</li>
 * <li>작성자 : 한승일 </li>
 * <li>
 * 삭제대상: Actor, UseCase, Class, Interface, Component, DataType, etc</li>
 * </ul>
 */
public class DeleteUMLPackageableElementCommand extends CompoundCommand {

    /**
     * @see org.eclipse.gef.commands.CompoundCommand#canExecute()
     */
    @Override
    public boolean canExecute() {
        // TODO Auto-generated method stub
        return true;
    }

    /**
     * @see org.eclipse.gef.commands.CompoundCommand#canUndo()
     */
    @Override
    public boolean canUndo() {
        // TODO Auto-generated method stub
        return true;
    }

    /**
     * 
     */
    public DeleteUMLPackageableElementCommand(NamedElement namedElement) {
        this.targetElement = namedElement;
        this.parentElement = this.targetElement.getOwner();
        this.relationshipList = null;
        this.relationshipList = UMLManager.getRelatedRelationshipModel(this.targetElement);
    }

    /** Relation Delete command */
    private List<Command> commandListForRelation = new ArrayList<Command>();

    /** behavioredClassifier */
    private NamedElement targetElement;

    /** parentElement */
    protected Element parentElement;

    /**
     * relationshipList
     */
    private List<Relationship> relationshipList;

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {
        this.redo();
    }

    /**
     * clearType
     *   void
     */
    protected void clearType() {
        if (!(this.targetElement instanceof Type)) {
            return;
        }
        List<Element> list = UMLManager.getRelatedUMLModel(this.targetElement);
        Property property;
        for (Element element : list) {
            if (!(element instanceof Property)) {
                continue;
            }
            property = (Property) element;
            property.setType(null);
            // UMLManager.deleteElement(this.targetElement);
        }
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    @Override
    public void redo() {

        deleteRelatedUMLElement();
        deleteViewModel();
        UMLManager.deleteElement(this.targetElement);
    }

    /**
     * deleteRelatedUMLElement
     *   void
     */
    private void deleteRelatedUMLElement() {
        Command deleteCommand = null;
        Command deleteRelationCommand = null;
        CompoundCommand deleteCompoundCommand = new CompoundCommand();
        Element element;
        this.getCommands().clear();
        this.commandListForRelation.clear();

        clearType();

        Relationship relationship;
        for (Iterator<Relationship> iter = this.relationshipList.iterator(); iter.hasNext();) {
            relationship = iter.next();
            deleteRelationCommand = DeleteUMLElementCommandFactory.getCommand(relationship);
            if (null != deleteRelationCommand) {
                this.commandListForRelation.add(deleteRelationCommand);
            }
        }
        for (Iterator<Command> iter = this.commandListForRelation.iterator(); iter.hasNext();) {
            iter.next().redo();
        }

        for (int i = 0; i < targetElement.getOwnedElements().size(); i++) {
            element = targetElement.getOwnedElements().get(i);
            if (element instanceof NamedElement) {
                deleteCommand = DeleteUMLElementCommandFactory.getCommand(element);
                deleteCompoundCommand.add(deleteCommand);
            }
        }

        deleteCompoundCommand.execute();
    }

    /**
     * deleteViewModel
     *   void
     */
    private void deleteViewModel() {
        List<AbstractView> relatedList = UMLManager.getRelatedViewModel(this.targetElement);
        AbstractView abstractView;
        AbstractNode notation = null;
        Diagram diagram = null;
        EObject eObject;
        for (Iterator<AbstractView> iter = relatedList.iterator(); iter.hasNext();) {
            abstractView = iter.next();
            // for(int i = relatedList.size() - 1; i >= 0; i--){
            // abstractView = relatedList.get(i);

            eObject = abstractView.eContainer();
            if (null == eObject) {
                continue;
            }
            if (eObject instanceof Diagram) {
                diagram = (Diagram) eObject;
            }
            try {
                if (abstractView instanceof AbstractNode) {
                    notation = (AbstractNode) abstractView;
                    if (null != diagram) {
                        diagram.getNodeList().remove(notation);
                    }
                    // if(notation instanceof NotationNode){
                    // ((NotationNode)notation).getCompartmentList().clear();
                    // }

                    UMLManager.deleteElement(notation);
                }
            } catch (Exception error) {
                Log.error("Delete error" + error);
            }
        }
        if (this.targetElement instanceof Operation) {
            List<Element> relatedUMLModel = UMLManager.getRelatedUMLModel(this.targetElement);
            for (Element obj : relatedUMLModel) {
                if (obj instanceof SendOperationEvent) {
                    ((SendOperationEvent) obj).setOperation(null);
                } else if (obj instanceof ReceiveOperationEvent) {
                    ((ReceiveOperationEvent) obj).setOperation(null);
                }
            }
        }
    }

//    private void removeChildren(AbstractNode notation) {
        //        
        // if(notation instanceof NotationNode){
        // NotationNode node = (NotationNode) notation;
        // for(int i = 0; i < node.getCompartmentList().size(); i++){
        // removeChildren(node.getCompartmentList().get(i));
        // }
        // System.out.println(node.getNodeType());
        // UMLManager.deleteElement(node);
        // }
//    }
}
