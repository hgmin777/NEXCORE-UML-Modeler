/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
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
import nexcore.tool.uml.model.umldiagram.Relation;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.uml2.uml.BehaviorExecutionSpecification;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageOccurrenceSpecification;
import org.eclipse.uml2.uml.NamedElement;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.manager</li>
 * <li>서브 업무명 : nexcore.tool.uml.manager.command</li>
 * <li>설  명 : DeleteUMLSequenceElementCommand</li>
 * <li>작성일 : 2011. 11. 28.</li>
 * <li>작성자 : 강석찬 </li>
 *  <li>
 * 삭제대상: Lifeline</li>
 * </ul>
 */
public class DeleteUMLSequenceElementCommand extends CompoundCommand {

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
    public DeleteUMLSequenceElementCommand(NamedElement namedElement) {
        this.targetElement = namedElement;
        this.parentElement = this.targetElement.getOwner();
        deleteModelList = new ArrayList<Element>();
        deleteViewList = new ArrayList<AbstractView>();
    }

    /** behavioredClassifier */
    private NamedElement targetElement;

    /** parentElement */
    protected Element parentElement;

    /**
     * deleteModelList
     */
    ArrayList<Element> deleteModelList;
    /**
     * deleteViewList
     */
    ArrayList<AbstractView> deleteViewList;

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute() {
        this.redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    @Override
    public void redo() {

        deleteRelatedUMLElement();
        deleteModelList.add(targetElement);
        deleteViewModel();

        for (int i = 0; i < deleteViewList.size(); i++) {
            UMLManager.deleteElement(deleteViewList.get(i));
        }
        for (int i = 0; i < deleteModelList.size(); i++) {
            UMLManager.deleteElement(deleteModelList.get(i));
        }
    }

    /**
     * deleteRelatedUMLElement
     *   void
     */
    private void deleteRelatedUMLElement() {
        if (targetElement instanceof Lifeline) {
            Lifeline lifeline = (Lifeline) targetElement;
            deleteModelList = new ArrayList<Element>();

            if (lifeline.getRepresents() != null) {
                // 1. represent attribute 삭제
                deleteModelList.add(lifeline.getRepresents());
            }

            Interaction interaction = (Interaction) lifeline.eContainer();

            if (interaction != null) {

                EList<InteractionFragment> fragList = lifeline.getCoveredBys();
                EList<Message> messages = interaction.getMessages();

                for (InteractionFragment frag : fragList) {
                    for (Message mes : messages) {
                        if (mes.getReceiveEvent().equals(frag) || mes.getSendEvent().equals(frag)) {
                            // 2. 관련된 message 삭제
                            deleteModelList.add(mes);
                        }
                    }
                }

                fragList = interaction.getFragments();
                for (InteractionFragment frag : fragList) {
                    if (frag instanceof MessageOccurrenceSpecification) {
                        if (deleteModelList.contains(((MessageOccurrenceSpecification) frag).getMessage())) {
                            // 3-1. fragment의 message가 2에서 삭제될 message에 포함된 경우
                            deleteModelList.add(frag);
                            
                            // 4.MessageOccurrenceSpecification의 event에 해당하는 operation을 삭제함
                            Event event = ((MessageOccurrenceSpecification) frag).getEvent();
                            if (event != null) {
                                deleteModelList.add(event);
                            }
                        }
                    }
                }
                for (InteractionFragment frag : fragList) {
                    if (frag instanceof BehaviorExecutionSpecification) {
                        if (deleteModelList.contains(((BehaviorExecutionSpecification) frag).getStart())
                            || deleteModelList.contains(((BehaviorExecutionSpecification) frag).getFinish())) {
                            // 3-2. BehaviorExecutionSpecification의 start나
                            // finish가
                            // 3-1에서 삭제한 MessageOccurrenceSpecification에 포함된 경우
                            deleteModelList.add(frag);
                        }
                    }
                }
            }
        } else if (targetElement instanceof Message) {

            Message message = (Message) targetElement;
            Interaction interaction = (Interaction) message.eContainer();
            deleteModelList = new ArrayList<Element>();
            deleteModelList.add(targetElement);

            if (interaction != null) {
                EList<InteractionFragment> fragList = interaction.getFragments();
                for (InteractionFragment frag : fragList) {
                    if (frag instanceof MessageOccurrenceSpecification) {
                        if (message.equals(((MessageOccurrenceSpecification) frag).getMessage())) {
                            // 3-1. fragment의 message가 2에서 삭제될 message에 포함된 경우
                            deleteModelList.add(frag);
                            
                            // 4.MessageOccurrenceSpecification의 event에 해당하는 operation을 삭제함
                            Event event = ((MessageOccurrenceSpecification) frag).getEvent();
                            if (event != null) {
                                deleteModelList.add(event);
                            }
                        }
                    }
                }
                for (InteractionFragment frag : fragList) {
                    if (frag instanceof BehaviorExecutionSpecification) {
                        if (deleteModelList.contains(((BehaviorExecutionSpecification) frag).getStart())
                            || deleteModelList.contains(((BehaviorExecutionSpecification) frag).getFinish())) {
                            // 3-2. BehaviorExecutionSpecification의 start나
                            // finish가
                            // 3-1에서 삭제한 MessageOccurrenceSpecification에 포함된 경우
                            deleteModelList.add(frag);
                        }
                    }
                }
            }
        }

    }

    /**
     * deleteViewModel
     *   void
     */
    private void deleteViewModel() {

        deleteViewList = new ArrayList<AbstractView>();
        for (Element target : deleteModelList) {
            List<AbstractView> relatedList = UMLManager.getRelatedViewModel(target);
            AbstractView abstractView;
            AbstractNode notation = null;
            Relation relation = null;
            Diagram diagram = null;
            EObject eObject;
            for (Iterator<AbstractView> iter = relatedList.iterator(); iter.hasNext();) {
                abstractView = iter.next();
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

                        deleteViewList.add(notation);
                    }
                    else if (abstractView instanceof Relation) {
                        relation = (Relation) abstractView;
                        ((AbstractNode)relation.getSource()).getOutgoingConnectionList().remove(relation);                        
                        ((AbstractNode)relation.getTarget()).getIncomingConnectionList().remove(relation);
                        if (null != diagram) {
                            diagram.getConnectionList().remove(relation);
                        }

                        deleteViewList.add(notation);
                    }
                } catch (Exception error) {
                    Log.error("Delete error" + error);
                }
            }
        }
    }

}
