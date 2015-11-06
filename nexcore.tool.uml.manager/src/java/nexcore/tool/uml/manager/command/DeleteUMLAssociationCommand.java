/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.manager.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.Relation;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.commands.Command;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.StructuredClassifier;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.manager</li>
 * <li>서브 업무명 : nexcore.tool.uml.manager.command</li>
 * <li>설  명 : DeleteUMLAssociationCommand</li>
 * <li>작성일 : 2009. 12. 18.</li>
 * <li>작성자 : 한승일 </li>
 * </ul>
 */
public class DeleteUMLAssociationCommand extends Command {

    /** owner of properties */
    private HashMap<Property, NamedElement> elementList;

    /** related propertyList */
    private List<Property> propertyList;

    /** related relation viewmodel to delete */
    // private List<Relation> relatedRelationViewModel;
    protected Association association;

    /** viewModelContainer */
    // protected HashMap<AbstractView, Diagram> viewModelContainer;
    protected PackageableElement parentElement;

    /**
     * @see nexcore.tool.uml.manager.command.DeleteUMLStereotypeCommand#releaseResource()
     */
    protected void releaseResource() {
        this.elementList.clear();
        this.propertyList.clear();
    }

    /**
     * @param targetElement
     */
    public DeleteUMLAssociationCommand(Association association) {
        this.association = association;
        if (null != this.association) {
            this.parentElement = (org.eclipse.uml2.uml.PackageableElement) this.association.getOwner();
        }

    }

    /**
     * 
     * void
     */
    private void init() {

        this.elementList = new HashMap<Property, NamedElement>();
        EList<Property> propertyList = ((Association) this.association).getMemberEnds();
        this.propertyList = new ArrayList<Property>();

        for (Iterator<Property> iter = propertyList.iterator(); iter.hasNext();) {
            this.propertyList.add(iter.next());
        }

    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {
        this.redo();
    }

    /**
     * @see nexcore.tool.uml.manager.command.DeleteUMLStereotypeCommand#redoOwnElement()
     */
    @Override
    public void redo() {
        Property property;
        Element element;
        if (null == this.association) {
            return;
        }
        init();

        for (Iterator<Property> iter = propertyList.iterator(); iter.hasNext();) {
            property = iter.next();
            element = property.getOwner();
            if (!(this.association.equals(element))) {
                if (element instanceof StructuredClassifier) {
                    elementList.put(property, (NamedElement) element);
                    ((StructuredClassifier) element).getOwnedAttributes().remove(property);
                }
                UMLManager.deleteElement(property);
            }
        }
        List<AbstractView> relatedList = UMLManager.getRelatedViewModel(this.association);
        AbstractView abstractView;
        Relation relation;
        Diagram diagram = null;
        AbstractNode sourceNode, targetNode;
        for (Iterator<AbstractView> iter = relatedList.iterator(); iter.hasNext();) {
            abstractView = iter.next();
            if (abstractView instanceof Relation) {
                relation = (Relation) abstractView;
                if (relation.eContainer() instanceof Diagram) {
                    diagram = (Diagram) relation.eContainer();
                }
                if (null == diagram) {
                    continue;
                }
                sourceNode = (AbstractNode) relation.getSource();
                sourceNode.getOutgoingConnectionList().remove(relation);
                targetNode = (AbstractNode) relation.getTarget();
                targetNode.getIncomingConnectionList().remove(relation);
                diagram.getConnectionList().remove(relation);
                UMLManager.deleteElement(relation);
            }
        }
        UMLManager.deleteElement(this.association);
    }

    /**
     * @see nexcore.tool.uml.manager.command.DeleteUMLStereotypeCommand#undoOwnElement()
     */
    public void undo() {
        // Property property;
        // NamedElement element;
        // if(null == this.association){
        // return;
        // }
        //        
        // for (Iterator<Property> iter = propertyList.iterator();
        // iter.hasNext();) {
        // property = iter.next();
        // element = this.elementList.get(property);
        // if(null != element){
        // ((StructuredClassifier) element).getOwnedAttributes().add(property);
        // property.setAssociation(this.association);
        // }else{
        // association.getMemberEnds().add(property);
        // }
        // }
        // Relation relation;
        // Diagram diagram;
        // AbstractNode sourceNode,targetNode;
        // for(Iterator<Relation> iter =
        // this.relatedRelationViewModel.iterator();iter.hasNext();){
        // relation = iter.next();
        // diagram = this.viewModelContainer.get(relation);
        // diagram.getConnectionList().add(relation);
        // sourceNode = (AbstractNode)relation.getSource();
        // sourceNode.getOutgoingConnectionList().add(relation);
        // targetNode = (AbstractNode)relation.getTarget();
        // targetNode.getIncomingConnectionList().add(relation);
        // }
        // ((org.eclipse.uml2.uml.Package)
        // this.parentElement).getPackagedElements()
        // .add((PackageableElement) this.association);
        // this.releaseResource();
    }

}
