/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.uml.ui.realization.sequencediagram.command;

import java.util.List;

import nexcore.tool.uml.manager.util.DiagramUtil;
import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.LifeLineNode;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.model.umldiagram.RelationType;
import nexcore.tool.uml.ui.realization.sequencediagram.util.SequenceUtil;

import org.eclipse.gef.commands.Command;
import org.eclipse.uml2.uml.BehaviorExecutionSpecification;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.InteractionOperand;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageOccurrenceSpecification;
import org.eclipse.uml2.uml.ReceiveOperationEvent;
import org.eclipse.uml2.uml.SendOperationEvent;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.command</li>
 * <li>설 명 : ReconnectMessageCommand</li>
 * <li>작성일 : 2010. 1. 15.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class ReconnectMessageCommand extends Command {

    /** 연결선 */
    private AbstractConnection connection;

    /** diagram */
    private Diagram diagram;

    /** new Source ViewModel */
    private AbstractNode newSource = null;

    /** new Target ViewModel */
    private AbstractNode newTarget = null;

    /** old Source ViewModel */
    private AbstractNode oldSource = null;

    /** old Target ViewModel */
    private AbstractNode oldTarget = null;

    /**
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {

        try {

            if (newSource != null) {
                handleNewSource();
            }
            if (newTarget != null) {
                handleNewTarget();
            }

            SequenceUtil.refreshMessageOrder(diagram);
            SequenceUtil.refreshUmlLifelineCoveredBysOrder(connection, (Message) connection.getUmlModel());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 소스의 변경 처리. void
     */
    private void handleNewSource() {
        oldSource = (AbstractNode) connection.getSource();
        if(newSource.equals(oldSource)) { //변경 필요 없음.
            return;
        }

        if (RelationType.REPLY_MESSAGE.equals(connection.getRelationType())) {
            LifeLineNode lifeLineNode;
            NotationNode behaviorNode;
            behaviorNode = (NotationNode) connection.getSource();
            lifeLineNode = SequenceUtil.getLifeLineNode(behaviorNode);
            lifeLineNode.getBehaviorList().remove(behaviorNode);

            List<NotationNode> childrenBehaviorList = SequenceUtil.getChildrenBehavior(behaviorNode);
            if (childrenBehaviorList != null && childrenBehaviorList.size() > 0) {
                NotationNode childBehavior = null;
                for (int i = 0; i < childrenBehaviorList.size(); i++) {
                    childBehavior = childrenBehaviorList.get(i);
                    childBehavior.setParent(behaviorNode.getParent());
                }
            }

            lifeLineNode = SequenceUtil.getLifeLineNode(newSource);
            behaviorNode.setParent(lifeLineNode.getLine());
            lifeLineNode.getBehaviorList().add(behaviorNode);

            diagram.getConnectionList().remove(connection);
            reconnectSource(behaviorNode);
            diagram.getConnectionList().add(connection);
        } else {
            diagram.getConnectionList().remove(connection);
            reconnectSource(newSource);
            diagram.getConnectionList().add(connection);
        }

        Lifeline sourceLifeLifeline = (Lifeline) (SequenceUtil.getLifeLineNode((AbstractNode) connection.getSource())).getUmlModel();
        MessageOccurrenceSpecification sendMessageOccurrenceSpecificationForSend = null;
        if(SequenceUtil.isIncludedOperand(connection)) { //해당 메시지가 Operand에 포함되어 있는지 검사
            Message message = (Message) connection.getUmlModel();
            sendMessageOccurrenceSpecificationForSend = (MessageOccurrenceSpecification) message.getSendEvent();
            InteractionOperand operand = (InteractionOperand) sendMessageOccurrenceSpecificationForSend.eContainer();
            for (InteractionFragment interactionFragment : operand.getFragments()) {
                if (interactionFragment instanceof MessageOccurrenceSpecification) {
                    MessageOccurrenceSpecification messageOccurrenceSpecification = (MessageOccurrenceSpecification) interactionFragment;
                    if (messageOccurrenceSpecification.getMessage().equals(connection.getUmlModel())
                        && messageOccurrenceSpecification.getEvent() instanceof SendOperationEvent) {
                        sendMessageOccurrenceSpecificationForSend = messageOccurrenceSpecification;
                        sendMessageOccurrenceSpecificationForSend.getCovereds().clear();
                        sendMessageOccurrenceSpecificationForSend.getCovereds().add((Lifeline) newSource.getUmlModel());
                        break;
                    }
                }
            }
        } else {
            Interaction interaction = (Interaction) sourceLifeLifeline.eContainer();
            for (InteractionFragment interactionFragment : interaction.getFragments()) {
                if (interactionFragment instanceof MessageOccurrenceSpecification) {
                    MessageOccurrenceSpecification messageOccurrenceSpecification = (MessageOccurrenceSpecification) interactionFragment;

                    if (messageOccurrenceSpecification != null && messageOccurrenceSpecification.getMessage() != null) {
                        if (messageOccurrenceSpecification.getMessage().equals(connection.getUmlModel())
                            && messageOccurrenceSpecification.getEvent() instanceof SendOperationEvent) {
                            sendMessageOccurrenceSpecificationForSend = messageOccurrenceSpecification;
                            sendMessageOccurrenceSpecificationForSend.getCovereds().clear();
                            sendMessageOccurrenceSpecificationForSend.getCovereds().add((Lifeline) newSource.getUmlModel());
                            break;
                        }
                    }
                }
            }
        }      
    }

    /**
     * 타켓의 변경 처리 void
     */
    private void handleNewTarget() {
        oldTarget = (AbstractNode) connection.getTarget();
        if(newTarget.equals(oldTarget)) {  //변경 필요 없음.
            return;
        }
        
        if (!RelationType.REPLY_MESSAGE.equals(connection.getRelationType())) {
            LifeLineNode lifeLineNode;
            NotationNode behaviorNode;
            behaviorNode = (NotationNode) connection.getTarget();
            lifeLineNode = SequenceUtil.getLifeLineNode(behaviorNode);
            lifeLineNode.getBehaviorList().remove(behaviorNode);

            List<NotationNode> childrenBehaviorList = SequenceUtil.getChildrenBehavior(behaviorNode);
            if (childrenBehaviorList != null && childrenBehaviorList.size() > 0) {
                NotationNode childBehavior = null;
                for (int i = 0; i < childrenBehaviorList.size(); i++) {
                    childBehavior = childrenBehaviorList.get(i);
                    childBehavior.setParent(behaviorNode.getParent());
                }
            }

            lifeLineNode = SequenceUtil.getLifeLineNode(newTarget);
            behaviorNode.setParent(lifeLineNode.getLine());
            lifeLineNode.getBehaviorList().add(behaviorNode);

            diagram.getConnectionList().remove(connection);
            reconnectTarget(behaviorNode);
            diagram.getConnectionList().add(connection);
        } else {
            diagram.getConnectionList().remove(connection);
            reconnectTarget(newTarget);
            diagram.getConnectionList().add(connection);
        }

        Lifeline targetLifeLifeline = (Lifeline) (SequenceUtil.getLifeLineNode((AbstractNode) connection.getTarget())).getUmlModel();
        ReceiveOperationEvent receiveOperationEvent = null;
        MessageOccurrenceSpecification receiveMessageOccurrenceSpecificationForSend = null;

        Lifeline sourceLifeLifeline = (Lifeline) (SequenceUtil.getLifeLineNode((AbstractNode) connection.getSource())).getUmlModel();
        SendOperationEvent sendOperationEvent = null;
        MessageOccurrenceSpecification sendMessageOccurrenceSpecificationForSend = null;

        if(SequenceUtil.isIncludedOperand(connection)) { //해당 메시지가 Operand에 포함되어 있는지 검사
            Message message = (Message) connection.getUmlModel();
            receiveMessageOccurrenceSpecificationForSend = (MessageOccurrenceSpecification) message.getReceiveEvent();
            InteractionOperand targetOperand = (InteractionOperand) receiveMessageOccurrenceSpecificationForSend.eContainer();            
            for (InteractionFragment interactionFragment : targetOperand.getFragments()) {
                if (interactionFragment instanceof MessageOccurrenceSpecification) {
                    MessageOccurrenceSpecification messageOccurrenceSpecification = (MessageOccurrenceSpecification) interactionFragment;
                    
                    if(messageOccurrenceSpecification != null && messageOccurrenceSpecification.getMessage() != null) {
                        if (messageOccurrenceSpecification.getMessage().equals(connection.getUmlModel())
                        && messageOccurrenceSpecification.getEvent() instanceof ReceiveOperationEvent) {
                            receiveMessageOccurrenceSpecificationForSend = messageOccurrenceSpecification;
                            receiveMessageOccurrenceSpecificationForSend.getCovereds().clear();
                            receiveMessageOccurrenceSpecificationForSend.getCovereds().add((Lifeline) newTarget.getUmlModel());
                            receiveOperationEvent = (ReceiveOperationEvent) receiveMessageOccurrenceSpecificationForSend.getEvent();
                            receiveOperationEvent.setOperation(null);
                            break;
                        }
                    }
                }
            }
            sendMessageOccurrenceSpecificationForSend = (MessageOccurrenceSpecification) message.getSendEvent();
            InteractionOperand sourceOperand = (InteractionOperand) sendMessageOccurrenceSpecificationForSend.eContainer();   
            for (InteractionFragment interactionFragment : sourceOperand.getFragments()) {
                if (interactionFragment instanceof MessageOccurrenceSpecification) {
                    MessageOccurrenceSpecification messageOccurrenceSpecification = (MessageOccurrenceSpecification) interactionFragment;
                    
                    if(messageOccurrenceSpecification != null && messageOccurrenceSpecification.getMessage() != null) {
                        if (messageOccurrenceSpecification.getMessage().equals(connection.getUmlModel())
                            && messageOccurrenceSpecification.getEvent() instanceof SendOperationEvent) {
                            sendMessageOccurrenceSpecificationForSend = messageOccurrenceSpecification;
                            sendOperationEvent = (SendOperationEvent) sendMessageOccurrenceSpecificationForSend.getEvent();
                            sendOperationEvent.setOperation(null);
                            break;
                        }
                    }
                }
            }
            if (!RelationType.REPLY_MESSAGE.equals(connection.getRelationType())) {
                BehaviorExecutionSpecification behaviorExecutionSpecification = null;
                for (InteractionFragment interactionFragment : sourceOperand.getFragments()) {
                    if (interactionFragment instanceof BehaviorExecutionSpecification) {
                        BehaviorExecutionSpecification executionSpecification = (BehaviorExecutionSpecification) interactionFragment;
                        if(executionSpecification != null && executionSpecification.getStart() != null) {
                            if (executionSpecification.getStart().equals(receiveMessageOccurrenceSpecificationForSend)) {
                                behaviorExecutionSpecification = executionSpecification;
                                behaviorExecutionSpecification.getCovereds().clear();
                                behaviorExecutionSpecification.getCovereds().add((Lifeline) newTarget.getUmlModel());
                                break;
                            }
                        }
                    }
                }
            }
        } else {
            Interaction targetInteraction = (Interaction) targetLifeLifeline.eContainer();
            if(targetInteraction != null) {
                for (InteractionFragment interactionFragment : targetInteraction.getFragments()) {
                    if (interactionFragment instanceof MessageOccurrenceSpecification) {
                        MessageOccurrenceSpecification messageOccurrenceSpecification = (MessageOccurrenceSpecification) interactionFragment;
                        if(messageOccurrenceSpecification != null && messageOccurrenceSpecification.getMessage() != null) {
                            if (messageOccurrenceSpecification.getMessage().equals(connection.getUmlModel()) && messageOccurrenceSpecification.getEvent() instanceof ReceiveOperationEvent) {
                                receiveMessageOccurrenceSpecificationForSend = messageOccurrenceSpecification;
                                receiveMessageOccurrenceSpecificationForSend.getCovereds().clear();
                                receiveMessageOccurrenceSpecificationForSend.getCovereds().add((Lifeline) newTarget.getUmlModel());
                                receiveOperationEvent = (ReceiveOperationEvent) receiveMessageOccurrenceSpecificationForSend.getEvent();
                                receiveOperationEvent.setOperation(null);
                                break;
                            }
                        }
                    }
                }
            } 
            Interaction sourceInteraction = (Interaction) sourceLifeLifeline.eContainer();
            for (InteractionFragment interactionFragment : sourceInteraction.getFragments()) {
                if (interactionFragment instanceof MessageOccurrenceSpecification) {
                    MessageOccurrenceSpecification messageOccurrenceSpecification = (MessageOccurrenceSpecification) interactionFragment;
                    
                    if(messageOccurrenceSpecification != null && messageOccurrenceSpecification.getMessage() != null) {
                        if (messageOccurrenceSpecification.getMessage().equals(connection.getUmlModel())
                            && messageOccurrenceSpecification.getEvent() instanceof SendOperationEvent) {
                            sendMessageOccurrenceSpecificationForSend = messageOccurrenceSpecification;
                            sendOperationEvent = (SendOperationEvent) sendMessageOccurrenceSpecificationForSend.getEvent();
                            sendOperationEvent.setOperation(null);
                            break;
                        }
                    }
                }
            }
            if (!RelationType.REPLY_MESSAGE.equals(connection.getRelationType())) {
                BehaviorExecutionSpecification behaviorExecutionSpecification = null;
                for (InteractionFragment interactionFragment : sourceInteraction.getFragments()) {
                    if (interactionFragment instanceof BehaviorExecutionSpecification) {
                        BehaviorExecutionSpecification executionSpecification = (BehaviorExecutionSpecification) interactionFragment;
                        if(executionSpecification != null && executionSpecification.getStart() != null) {
                            if (executionSpecification.getStart().equals(receiveMessageOccurrenceSpecificationForSend)) {
                                behaviorExecutionSpecification = executionSpecification;
                                behaviorExecutionSpecification.getCovereds().clear();
                                behaviorExecutionSpecification.getCovereds().add((Lifeline) newTarget.getUmlModel());
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * reconnectSource
     * 
     * @param source
     *            void
     */
    private void reconnectSource(AbstractNode source) {
        DiagramUtil.detachSourceOfConnection(connection);
        connection.setSource(source);
        DiagramUtil.attachSourceOfConnection(connection);
    }

    /**
     * reconnectTarget
     * 
     * @param target
     *            void
     */
    private void reconnectTarget(AbstractNode target) {
        DiagramUtil.detachTargetOfConnection(connection);

        connection.setTarget(target);
        DiagramUtil.attachTargetOfConnection(connection);
    }

    /**
     * 
     * @param model
     *            void
     */
    public void setConnectionModel(Object model) {
        connection = (AbstractConnection) model;
    }

    /**
     * 
     * @param model
     *            void
     */
    public void setNewSource(Object model) {
        newTarget = null;
        newSource = (AbstractNode) model;
    }

    /**
     * 
     * @param model
     *            void
     */
    public void setNewTarget(Object model) {
        newSource = null;
        newTarget = (AbstractNode) model;
    }

    /**
     * @param diagram
     *            the diagram to set
     */
    public void setDiagram(Diagram diagram) {
        this.diagram = diagram;
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        if (oldSource != null) {
            reconnectSource(oldSource);
        }
        if (oldTarget != null) {
            reconnectTarget(oldTarget);
        }

        oldSource = null;
        oldTarget = null;
    }
}
