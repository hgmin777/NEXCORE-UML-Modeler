/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.manager.command;

import java.util.Iterator;
import java.util.List;

import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.Relation;

import org.eclipse.uml2.uml.Element;
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.manager</li>
 * <li>서브 업무명 : nexcore.tool.uml.manager.command</li>
 * <li>설  명 : DeleteUMLDirectedRelationshipCommand</li>
 * <li>작성일 : 2009. 12. 21.</li>
 * <li>작성자 : 한승일 </li>
 * </ul>
 */
public class DeleteUMLDirectedRelationshipCommand extends DeleteUMLStereotypeCommand {

    /** Supplier for dependecny */
    protected Element target;

    /** client for dependency */
    protected Element source;

    /** related relationviewmodel to delete */
    // protected List<Relation> relatedRelationViewModel;
    /**
     * @param targetElement
     */
    public DeleteUMLDirectedRelationshipCommand(Element targetElement) {
        super(targetElement);
    }

    /**
     * @see nexcore.tool.uml.manager.command.DeleteUMLStereotypeCommand#init()
     */
    @Override
    protected void init() {
        // this.relatedRelationViewModel = new ArrayList<Relation>();
    }

    /**
     * @see nexcore.tool.uml.manager.command.DeleteUMLStereotypeCommand#releaseResource()
     */
    @Override
    protected void releaseResource() {
        // this.relatedRelationViewModel.clear();
    }

    /**
     * @see nexcore.tool.uml.manager.command.DeleteUMLStereotypeCommand#redoOwnElement()
     */
    @Override
    protected void redoOwnElement() {

        List<AbstractView> relatedList = UMLManager.getRelatedViewModel(this.targetElement);
        AbstractView abstractView;
        Relation relation;
        Diagram diagram = null;
        AbstractNode sourceNode, targetNode;
        for (Iterator<AbstractView> iter = relatedList.iterator(); iter.hasNext();) {
            abstractView = iter.next();
            if (abstractView instanceof Relation) {
                relation = (Relation) abstractView;
                
                // 2011-06-20 modified by nspark.
                // relation.eContainer() 가 null 인 경우 classcasting Exception 발생하여 아래와 같이
                // 인스턴스 체크 후 casting.
                if( relation.eContainer() instanceof Diagram){
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
                this.delete(relation);
            }
        }

        this.deleteDirectedAttributes();
        super.redoOwnElement();
    }

    /**
     * delete Directed Relationship's own attributes void
     */
    protected void deleteDirectedAttributes() {

    }

    /**
     * restore directed relationship's own attributes void
     */
    protected void restoreDirectedAttributes() {

    }

    /**
     * @see nexcore.tool.uml.manager.command.DeleteUMLStereotypeCommand#undoOwnElement()
     */
    @Override
    protected void undoOwnElement() {
        // this.undoDiretedRelationshipElement();
        //        
        // this.restoreDirectedAttributes();
        // Relation relation;
        // Diagram diagram;
        // AbstractNode sourceNode, targetNode;
        // for (Iterator<Relation> iter =
        // this.relatedRelationViewModel.iterator(); iter.hasNext();) {
        // relation = iter.next();
        // diagram = this.viewModelContainer.get(relation);
        // diagram.getConnectionList().add(relation);
        // sourceNode = (AbstractNode) relation.getSource();
        // sourceNode.getOutgoingConnectionList().add(relation);
        // targetNode = (AbstractNode) relation.getTarget();
        // targetNode.getIncomingConnectionList().add(relation);
        // }
    }

    /**
     * undoDiretedRelationshipElement
     *   void
     */
    protected void undoDiretedRelationshipElement() {

    }

}
