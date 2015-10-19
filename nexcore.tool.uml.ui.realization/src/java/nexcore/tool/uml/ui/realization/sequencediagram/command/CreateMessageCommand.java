/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.ui.realization.sequencediagram.command;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.manager.util.DiagramUtil;
import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.BendPoint;
import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.Dimension;
import nexcore.tool.uml.model.umldiagram.LifeLineNode;
import nexcore.tool.uml.model.umldiagram.Line;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.model.umldiagram.RelationType;
import nexcore.tool.uml.model.umldiagram.UMLDiagramFactory;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.diagram.edit.part.AbstractNotationNodeEditPart;
import nexcore.tool.uml.ui.core.diagram.figure.FigureConstant;
import nexcore.tool.uml.ui.realization.sequencediagram.dialog.AddMissedCoveredLifelineDialog;
import nexcore.tool.uml.ui.realization.sequencediagram.util.SequenceUtil;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.window.Window;
import org.eclipse.uml2.uml.BehaviorExecutionSpecification;
import org.eclipse.uml2.uml.CombinedFragment;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ExecutionEvent;
import org.eclipse.uml2.uml.ExecutionOccurrenceSpecification;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.InteractionOperand;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageEvent;
import org.eclipse.uml2.uml.MessageOccurrenceSpecification;
import org.eclipse.uml2.uml.MessageSort;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.command</li>
 * <li>설 명 : CreateMessageCommand</li>
 * <li>작성일 : 2009. 12. 21.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
public class CreateMessageCommand extends Command {

    /** diagram */
    private Diagram diagram;

    /**
     * 연결점 시작 노드
     */
    private AbstractNode sourceNode;

    /**
     * 연결선 끝 노드
     */
    private AbstractNode targetNode;

    /**
     * 연결선
     */
    private AbstractConnection sendConnection;

    /** editPart */
    @SuppressWarnings("unused")
    private AbstractNotationNodeEditPart editPart;

    /** margin */
    private final int margin = 10;

    /** reply 연결선 */
    private AbstractConnection replyConnection;

    /**
     * sendMessage
     */
    private Message sendMessage;

    /**
     * replyMessage
     */
    private Message replyMessage;

    /**
     * behaviorNode
     */
    private NotationNode behaviorNode;

    /**
     * sendMessageOccurrenceSpecificationForSend
     */
    private MessageOccurrenceSpecification sendMessageOccurrenceSpecificationForSend;

    /**
     * receiveMessageOccurrenceSpecificationForSend
     */
    private MessageOccurrenceSpecification receiveMessageOccurrenceSpecificationForSend;

    /**
     * behaviorExecutionSpecification
     */
    private BehaviorExecutionSpecification behaviorExecutionSpecification;

    /**
     * sendEventForSend
     */
    private MessageEvent sendEventForSend;

    /**
     * receiveEventForSend
     */
    private MessageEvent receiveEventForSend;

    /**
     * receiveMessageOccurrenceSpecificationForReply
     */
    private MessageOccurrenceSpecification receiveMessageOccurrenceSpecificationForReply;

    /**
     * sendMessageOccurrenceSpecificationForReply
     */
    private MessageOccurrenceSpecification sendMessageOccurrenceSpecificationForReply;

    /**
     * sendEventForReply
     */
    private MessageEvent sendEventForReply;

    /**
     * receiveEventForReply
     */
    private MessageEvent receiveEventForReply;

    /**
     * executionOccurrenceSpecification
     */
    private ExecutionOccurrenceSpecification executionOccurrenceSpecification;

    /**
     * executionEvent
     */
    private ExecutionEvent executionEvent;

    /**
     * location
     */
    private Point location;
    
    /**
     * operandNode
     */
    private ContainerNode operandNode;

    /**
     * originalLocation
     */
    @SuppressWarnings("unused")
    private Point originalLocation;

    /**
     * operation
     */
    private Operation operation;

    /** operation을 새로 생성했을 시의 operation의 부모. */
    private Type type;

    /**
     * CreateMessageCommand
     * @param editPart
     * @param point
     */
    public CreateMessageCommand(AbstractNotationNodeEditPart editPart, Point point) {
        this.editPart = editPart;
        this.originalLocation = point;
        this.sendMessage = null;
        this.replyMessage = null;
        this.operation = null;
    }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {

        // 1.소스나 타겟이null의 경우는 실행 불가
        if (sourceNode == null && targetNode == null)
            return false;

        return true;
    }

    /**
     * setLocation
     *  
     * @param location void
     */
    public void setLocation(Point location) {
        this.location = location;
    }

    /**
     * getConnection
     *  
     * @return AbstractConnection
     */
    public AbstractConnection getConnection() {
        return sendConnection;
    }

    /**
     * setConnection
     *  
     * @param model void
     */
    public void setConnection(AbstractConnection model) {
        sendConnection = (AbstractConnection) model;
    }

    /**
     * getSource
     *  
     * @return AbstractView
     */
    public AbstractView getSource() {
        return sourceNode;
    }

    /**
     * setSource
     *  
     * @param model void
     */
    public void setSource(AbstractNode model) {
        sourceNode = model;
        sendConnection.setSource(sourceNode);
    }

    /**
     * setTarget
     *  
     * @param model void
     */
    public void setTarget(AbstractNode model) {
        targetNode = model;
        sendConnection.setTarget(targetNode);
    }

    /**
     * getTarget
     *  
     * @return AbstractNode
     */
    public AbstractNode getTarget() {
        return targetNode;
    }

    /**
     * setDiagram
     *  
     * @param diagram void
     */
    public void setDiagram(Diagram diagram) {
        this.diagram = diagram;
    }
    
    /**
     * setOperandNode
     *  
     * @param operandNode void
     */
    public void setOperandNode(ContainerNode operandNode) {
        this.operandNode = operandNode;
    }
    
    /**
     * getOperandNode
     *  
     * @return ContainerNode
     */
    public ContainerNode getOperandNode() {
        return operandNode;
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        try {
            NamedElement client;
            NamedElement supplier;

            // LifeLine 모델
            client = (NamedElement) (SequenceUtil.getLifeLineNode(sourceNode)).getUmlModel();
            supplier = (NamedElement) (SequenceUtil.getLifeLineNode(targetNode)).getUmlModel();
            
            if(operandNode != null) {
                //라이프라인이 결합단편에 포함 안되어 있으면 메시지를 생성 할 수 없다.
                if(!checkLifelineCoveredBys((Lifeline)client, (Lifeline)supplier)) {
                    return;
                }
            }

            RelationType relationType = sendConnection.getRelationType();
            sendMessage = null;
            replyMessage = null;
            replyConnection = null;
            Interaction interaction = (Interaction) client.eContainer();

            setSendMessage(interaction, relationType, (Lifeline) client, (Lifeline) supplier);

            if (RelationType.SYNCHRONOUS_MESSAGE.equals(relationType) && SequenceUtil.checkOptionalReplyMessage()) {
                setReplyMessage(interaction, (Lifeline) client, (Lifeline) supplier);
            }


            SequenceUtil.shiftByNewInputBounds(diagram, behaviorNode, sendConnection, replyConnection);
//            shiftFragmentNode();
            
            SequenceUtil.refreshMessageOrder(diagram);
            SequenceUtil.refreshUmlLifelineCoveredBysOrder(sendConnection, (Message) sendConnection.getUmlModel());
            
            SequenceUtil.setAllLifeLineHeight(diagram);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     *   void
     */
    private void shiftFragmentNode() {
        List<AbstractNode> nodeList = diagram.getNodeList();
        
        AbstractNode newObj = behaviorNode;
        
        for (AbstractNode abstractNode : nodeList) {
            // UML 모델이 CombinedFragment 이고 그래픽모델이 NotationNode 인 경우.
            if (abstractNode.getUmlModel() instanceof CombinedFragment && abstractNode instanceof NotationNode) {
                NotationNode node = (NotationNode) abstractNode;
                if( node.getY() > newObj.getY() ) {
                    node.setY( node.getY() + newObj.getHeight() ) ;
                }
            }
        }
    }

    /**
     * 소스와 타겟의 라이프라인이 결합단편에 covered되었는지 검사한다.
     *  
     * @param client
     * @param supplier void
     */
    private boolean checkLifelineCoveredBys(Lifeline client, Lifeline supplier) {

        List<Lifeline> lifelineList = new  ArrayList<Lifeline>();
        
        if(!client.getCoveredBys().contains(operandNode.getUmlModel().eContainer())) {
            lifelineList.add((Lifeline) client);
        }
        if(!supplier.getCoveredBys().contains(operandNode.getUmlModel().eContainer())) {
            lifelineList.add((Lifeline) supplier);
        }

        if(lifelineList.size() > 0) {
            AddMissedCoveredLifelineDialog dialog = new AddMissedCoveredLifelineDialog(UiCorePlugin.getShell(), lifelineList, true);
            if (dialog.open() == Window.OK) {
                return addMissedCoveredLifeline(lifelineList, dialog);
            } else {
                return false;
            }
        } else {
            return true;
        }
        
    }

    /**
     * 누락된 lifeline  coveredby 추가
     *  
     * @param lifelineList
     * @param dialog
     * @return boolean
     */
    private boolean addMissedCoveredLifeline(List<Lifeline> lifelineList, AddMissedCoveredLifelineDialog dialog) {
        List<Lifeline> addedLifelinList;
        addedLifelinList = dialog.getAddedLifelineList();
        if(addedLifelinList.size() == lifelineList.size()) {
            for(Lifeline lifeline : addedLifelinList) {
                CombinedFragment combinedFragment = (CombinedFragment) operandNode.getUmlModel().eContainer();
                lifeline.getCoveredBys().add(combinedFragment);
                combinedFragment.getCovereds().add(lifeline);                         
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * setSendMessage
     *  
     * @param interaction
     * @param relationType
     * @param client
     * @param supplier void
     */
    private void setSendMessage(Interaction interaction, RelationType relationType, Lifeline client, Lifeline supplier) {

        this.sendMessage = UMLFactory.eINSTANCE.createMessage();

        switch (relationType) {
            case SYNCHRONOUS_MESSAGE:
                sendMessage.setMessageSort(MessageSort.SYNCH_CALL_LITERAL);
                break;
            case ASYNCHRONOUS_MESSAGE:
                sendMessage.setMessageSort(MessageSort.ASYNCH_CALL_LITERAL);
                break;
            case REPLY_MESSAGE:
                sendMessage.setMessageSort(MessageSort.REPLY_LITERAL);
                break;
            default:
                break;
        }
            
        setSendMessageFragment(interaction, client, supplier);            

        if (type != null) {
            SequenceUtil.setOperationToType(type, operation);
        }
        sendMessage = SequenceUtil.setOperationToMessage(operation, sendMessage);

        setSendViewModel();
    }

    /**
     * setSendMessageFragment
     *  
     * @param interaction
     * @param client
     * @param supplier void
     */
    private void setSendMessageFragment(Interaction interaction, Lifeline client, Lifeline supplier) {
        sendMessageOccurrenceSpecificationForSend = UMLFactory.eINSTANCE.createMessageOccurrenceSpecification();
        receiveMessageOccurrenceSpecificationForSend = UMLFactory.eINSTANCE.createMessageOccurrenceSpecification();

        executionOccurrenceSpecification = null;
        executionEvent = null;

        sendEventForSend = UMLFactory.eINSTANCE.createSendOperationEvent();
        receiveEventForSend = UMLFactory.eINSTANCE.createReceiveOperationEvent();

        String uniqueName = UMLManager.getPackagedUniqueName(interaction.getNearestPackage(),
            UMLMessage.getMessage(UMLMessage.UML_SENDOPERATIONEVENT));
        sendEventForSend.setName(uniqueName);
        uniqueName = UMLManager.getPackagedUniqueName(interaction.getNearestPackage(),
            UMLMessage.getMessage(UMLMessage.UML_RECEIVEOPERATIONEVENT));
        receiveEventForSend.setName(uniqueName);

        interaction.getNearestPackage().getPackagedElements().add(sendEventForSend);
        interaction.getNearestPackage().getPackagedElements().add(receiveEventForSend);

        sendMessageOccurrenceSpecificationForSend.getCovereds().add(client);
        receiveMessageOccurrenceSpecificationForSend.getCovereds().add(supplier);

        sendMessageOccurrenceSpecificationForSend.setEvent(sendEventForSend);
        receiveMessageOccurrenceSpecificationForSend.setEvent(receiveEventForSend);

        sendMessageOccurrenceSpecificationForSend.setMessage(sendMessage);
        receiveMessageOccurrenceSpecificationForSend.setMessage(sendMessage);

        interaction.getMessages().add(this.sendMessage);
        if(operandNode == null) {
            interaction.getFragments().add(sendMessageOccurrenceSpecificationForSend);
            interaction.getFragments().add(receiveMessageOccurrenceSpecificationForSend);
        } else {
            InteractionOperand operand = (InteractionOperand) operandNode.getUmlModel();
            operand.getFragments().add(sendMessageOccurrenceSpecificationForSend);
            operand.getFragments().add(receiveMessageOccurrenceSpecificationForSend);
        }

        if (!RelationType.REPLY_MESSAGE.equals(sendConnection.getRelationType())) {
            behaviorExecutionSpecification = UMLFactory.eINSTANCE.createBehaviorExecutionSpecification();
            behaviorExecutionSpecification.getCovereds().add(supplier);
            // Behavior ExecutionSpecification 은 Start와 Finish가 같아야 함.
            behaviorExecutionSpecification.setStart(receiveMessageOccurrenceSpecificationForSend);
            behaviorExecutionSpecification.setFinish(receiveMessageOccurrenceSpecificationForSend);
            if(operandNode == null) {
                interaction.getFragments().add(behaviorExecutionSpecification);
            } else {
                InteractionOperand operand = (InteractionOperand) operandNode.getUmlModel();
                operand.getFragments().add(behaviorExecutionSpecification);
            }
        }

        this.sendMessage.setSendEvent(sendMessageOccurrenceSpecificationForSend);
        this.sendMessage.setReceiveEvent(receiveMessageOccurrenceSpecificationForSend);
    }

    /**
     * setSendViewModel
     *   void
     */
    private void setSendViewModel() {
        LifeLineNode targetLifeLineNode = SequenceUtil.getLifeLineNode(targetNode);

        boolean specialCondition = false;
        if ( sendConnection.getSource() instanceof Line ) {
            if( targetNode instanceof NotationNode ) {
                NotationNode targetNotation = (NotationNode) targetNode;
                if( NodeType.LIFE_LINE_BEHAVIOR == targetNotation.getNodeType() ) {
                    Line lineNode = SequenceUtil.getLine(targetNotation);
                    if( lineNode.equals(sendConnection.getSource())) {
                        specialCondition = true;
                    }
                }
            }
        } else if( sendConnection.getSource() instanceof NotationNode ) {
            NotationNode sourceNotation = (NotationNode) sendConnection.getSource();
            if( NodeType.LIFE_LINE_BEHAVIOR == sourceNotation.getNodeType() ) {
                Line lineNode = SequenceUtil.getLine(sourceNotation);
                
                if( targetNode instanceof Line ) {
                    if( targetNode.equals(lineNode)) {
                        specialCondition = true;
                    }
                } else if ( targetNode instanceof NotationNode ) {
                    NotationNode targetNotation = (NotationNode) targetNode;
                    if( NodeType.LIFE_LINE_BEHAVIOR == targetNotation.getNodeType() ) {
                        Line targetLineNode = SequenceUtil.getLine(targetNotation);
                        if( targetLineNode.equals(lineNode)) {
                            specialCondition = true;
                        }
                    }
                }
            }
        }
        // Self Connection의 BendPoint 생성
        if (sendConnection.getSource().equals(targetNode) || specialCondition ) {
            Dimension sourceAnchorDimension = UMLDiagramFactory.eINSTANCE.createDimension();
            sourceAnchorDimension.setWidth(location.x);
            sourceAnchorDimension.setHeight(location.y);
            sendConnection.setSourceAnchor(sourceAnchorDimension);
            Dimension targetAnchorDimension = UMLDiagramFactory.eINSTANCE.createDimension();
            targetAnchorDimension.setWidth(location.x);
            targetAnchorDimension.setHeight(location.y + margin);
            sendConnection.setTargetAnchor(targetAnchorDimension);

            Point sourcePoint = new Point(sendConnection.getSourceAnchor().getWidth(), sendConnection.getSourceAnchor()
                .getHeight());
            Point targetPoint = new Point(sendConnection.getTargetAnchor().getWidth(), sendConnection.getTargetAnchor()
                .getHeight());

            Point firstPosition = new Point(sourceAnchorDimension.getWidth() + margin * 4,
                sourceAnchorDimension.getHeight());
            BendPoint firstBendPoint = UMLDiagramFactory.eINSTANCE.createBendPoint();
            Dimension firstPositionFirstDimension = UMLDiagramFactory.eINSTANCE.createDimension();
            Dimension firstPositionSecondDimension = UMLDiagramFactory.eINSTANCE.createDimension();
            firstPositionFirstDimension.setWidth(firstPosition.getDifference(sourcePoint).width);
            firstPositionFirstDimension.setHeight(firstPosition.getDifference(sourcePoint).height);
            firstBendPoint.setFirstPosition(firstPositionFirstDimension);
            firstPositionSecondDimension.setWidth(firstPosition.getDifference(targetPoint).width);
            firstPositionSecondDimension.setHeight(firstPosition.getDifference(targetPoint).height);
            firstBendPoint.setSecondPosition(firstPositionSecondDimension);
            sendConnection.getBendPointList().add(0, firstBendPoint);

            Point secondPosition = new Point(firstPosition.x, targetAnchorDimension.getHeight());
            BendPoint secondBendPoint = UMLDiagramFactory.eINSTANCE.createBendPoint();
            Dimension secondPositionFirstDimension = UMLDiagramFactory.eINSTANCE.createDimension();
            Dimension secondPositionSecondDimension = UMLDiagramFactory.eINSTANCE.createDimension();
            secondPositionFirstDimension.setWidth(secondPosition.getDifference(sourcePoint).width);
            secondPositionFirstDimension.setHeight(secondPosition.getDifference(sourcePoint).height);
            secondBendPoint.setFirstPosition(secondPositionFirstDimension);
            secondPositionSecondDimension.setWidth(secondPosition.getDifference(targetPoint).width);
            secondPositionSecondDimension.setHeight(secondPosition.getDifference(targetPoint).height);
            secondBendPoint.setSecondPosition(secondPositionSecondDimension);
            sendConnection.getBendPointList().add(1, secondBendPoint);
        }

        if (!RelationType.REPLY_MESSAGE.equals(sendConnection.getRelationType())) {
            setBehaviorViewModel(targetLifeLineNode);
            targetLifeLineNode.getBehaviorList().add(this.behaviorNode);
            sendConnection.setTarget(this.behaviorNode);
            sendConnection.setSource(SequenceUtil.getLine(sourceNode));
        } else {
            sendConnection.setRelationType(RelationType.REPLY_MESSAGE);
            sendConnection.setTarget(this.targetNode);
            sendConnection.setSource(sourceNode);
        }

        sendConnection.setUmlModel(sendMessage);
        sendConnection.setName(sendMessage.getName());

        diagram.getConnectionList().add(sendConnection);

        DiagramUtil.attachSourceOfConnection(sendConnection);
        DiagramUtil.attachTargetOfConnection(sendConnection);

        if (behaviorNode != null) {
            SequenceUtil.checkLifeLinesHeight(diagram, sendConnection, behaviorNode, margin);
        } else {
            SequenceUtil.checkLifeLinesHeight(diagram, sendConnection, margin);
        }

        // List<AbstractConnection> messageList =
        // SequenceUtil.ascSortedMessageListByYValue(diagram.getConnectionList());
        // if
        // (!RelationType.REPLY_MESSAGE.equals(sendConnection.getRelationType()))
        // {
        // if (!(sourceNode instanceof Line)) {
        // setSourceBehaviorHeight(messageList);
        // }
        // if (this.behaviorNode != null) {
        // SequenceUtil.setMessageLayout(diagram, sendConnection, messageList,
        // behaviorNode.getHeight(), margin);
        // }
        // }

    }

    /**
     * source가 behavior인 경우 behavior의 높이를 조절한다. void
     * 
     * @param messageList
     */
    @SuppressWarnings("unused")
    private void setSourceBehaviorHeight(List<AbstractConnection> messageList) {

        if (this.behaviorNode == null) {
            return;
        }

        SequenceUtil.addBehaviorHeight(diagram,
            (NotationNode) sourceNode,
            messageList,
            behaviorNode.getHeight(),
            margin);

        LifeLineNode lifeLineNode = SequenceUtil.getLifeLineNode(sourceNode);
        if (lifeLineNode.getY() + lifeLineNode.getHeight() < sourceNode.getY() + sourceNode.getHeight()) {
            int shiftHeight = (sourceNode.getY() + sourceNode.getHeight())
                - (lifeLineNode.getY() + lifeLineNode.getHeight()) + margin;
            // 모든 라이프라인의 높이를 생성된 Behavior의 높이만클 늘린다.
            List<LifeLineNode> nodeList = SequenceUtil.getOnlyLifeLineNodeList(diagram.getNodeList());
            for (AbstractNode node : nodeList) {
                node.setHeight(node.getHeight() + shiftHeight);
            }
        }
    }

    /**
     * setBehaviorViewModel
     *  
     * @param targetLifeLineNode void
     */
    private void setBehaviorViewModel(LifeLineNode targetLifeLineNode) {
        this.behaviorNode = UMLDiagramFactory.eINSTANCE.createNotationNode();
        // Behavior를 만들어 주고 타겟 노드의 Line에다 add 한다.
        this.behaviorNode.setNodeType(NodeType.LIFE_LINE_BEHAVIOR);
        this.behaviorNode.setId(UUID.randomUUID().toString());
        this.behaviorNode.setUmlModel(behaviorExecutionSpecification);

        if (targetNode.getNodeType().equals(NodeType.LIFE_LINE_BEHAVIOR)) {
            Line parentLine = null;
            if (targetNode.getParent() instanceof Line) {
                parentLine = (Line) targetNode.getParent();
            }
            if (parentLine != null) {
                this.behaviorNode.setParent(parentLine);
            } else {
                this.behaviorNode.setParent(targetNode);
            }

        } else {
            this.behaviorNode.setParent(targetNode);
        }
        this.behaviorNode.setX(targetLifeLineNode.getX() + targetLifeLineNode.getWidth() / 2
            - FigureConstant.FIGURE_BEHAVIOR_WIDTH / 2);
        this.behaviorNode.setY(sendConnection.getY());
        this.behaviorNode.setWidth(FigureConstant.FIGURE_BEHAVIOR_WIDTH);
        this.behaviorNode.setHeight(FigureConstant.FIGURE_BEHAVIOR_HEIGHT);
        // if (NodeType.LIFE_LINE_BEHAVIOR.equals(targetNode.getNodeType())) {
        // List<AbstractConnection> messageList =
        // SequenceUtil.ascSortedMessageListByYValue(diagram.getConnectionList());
        // SequenceUtil.addBehaviorHeight(diagram, (NotationNode) targetNode,
        // messageList, this.behaviorNode.getHeight(), margin);
        // }
    }

    /**
     * setReplyMessage
     *  
     * @param interaction
     * @param client
     * @param supplier void
     */
    protected void setReplyMessage(Interaction interaction, Lifeline client, Lifeline supplier) {
        sendMessageOccurrenceSpecificationForReply = null;
        receiveMessageOccurrenceSpecificationForReply = null;

        sendEventForReply = null;
        receiveEventForReply = null;
        
        replyMessage = UMLFactory.eINSTANCE.createMessage();
        replyMessage.setMessageSort(MessageSort.REPLY_LITERAL);

        sendMessageOccurrenceSpecificationForReply = UMLFactory.eINSTANCE.createMessageOccurrenceSpecification();
        receiveMessageOccurrenceSpecificationForReply = UMLFactory.eINSTANCE.createMessageOccurrenceSpecification();
        // Reply Block
        if (null != sendMessageOccurrenceSpecificationForReply) {
            setReplyMessageFragment(interaction, client, supplier);
            setReplyViewModel();
        }

        replyMessage = SequenceUtil.setOperationToMessage(operation, replyMessage);

    }

    /**
     * setReplyMessageFragment
     *  
     * @param interaction
     * @param client
     * @param supplier void
     */
    private void setReplyMessageFragment(Interaction interaction, Lifeline client, Lifeline supplier) {
        String uniqueName;
        sendEventForReply = UMLFactory.eINSTANCE.createSendOperationEvent();
        receiveEventForReply = UMLFactory.eINSTANCE.createReceiveOperationEvent();

        uniqueName = UMLManager.getPackagedUniqueName(interaction.getNearestPackage(),
            UMLMessage.getMessage(UMLMessage.UML_SENDOPERATIONEVENT));
        sendEventForReply.setName(uniqueName);
        uniqueName = UMLManager.getPackagedUniqueName(interaction.getNearestPackage(),
            UMLMessage.getMessage(UMLMessage.UML_RECEIVEOPERATIONEVENT));
        receiveEventForReply.setName(uniqueName);

        interaction.getNearestPackage().getPackagedElements().add(sendEventForReply);
        interaction.getNearestPackage().getPackagedElements().add(receiveEventForReply);

        sendMessageOccurrenceSpecificationForReply.getCovereds().add(supplier);
        receiveMessageOccurrenceSpecificationForReply.getCovereds().add(client);

        sendMessageOccurrenceSpecificationForReply.setEvent(sendEventForReply);
        receiveMessageOccurrenceSpecificationForReply.setEvent(receiveEventForReply);

//        sendMessageOccurrenceSpecificationForReply.setMessage(sendMessage);
//        receiveMessageOccurrenceSpecificationForReply.setMessage(sendMessage);
        sendMessageOccurrenceSpecificationForReply.setMessage(replyMessage);
        receiveMessageOccurrenceSpecificationForReply.setMessage(replyMessage);

        interaction.getMessages().add(this.replyMessage);
        if(operandNode == null) {
            interaction.getFragments().add(sendMessageOccurrenceSpecificationForReply);
            interaction.getFragments().add(receiveMessageOccurrenceSpecificationForReply);
        } else {
            InteractionOperand operand = (InteractionOperand) operandNode.getUmlModel();
            operand.getFragments().add(sendMessageOccurrenceSpecificationForSend);
            operand.getFragments().add(receiveMessageOccurrenceSpecificationForSend);
        }
        this.replyMessage.setSendEvent(sendMessageOccurrenceSpecificationForReply);
        this.replyMessage.setReceiveEvent(receiveMessageOccurrenceSpecificationForReply);
    }

    /**
     * setReplyViewModel
     *   void
     */
    private void setReplyViewModel() {
        this.replyConnection = UMLDiagramFactory.eINSTANCE.createRelation();

        boolean specialCondition = false;
        if ( sendConnection.getSource() instanceof Line ) {
            if( targetNode instanceof NotationNode ) {
                NotationNode targetNotation = (NotationNode) targetNode;
                if( NodeType.LIFE_LINE_BEHAVIOR == targetNotation.getNodeType() ) {
                    Line lineNode = SequenceUtil.getLine(targetNotation);
                    if( lineNode.equals(sendConnection.getSource())) {
                        specialCondition = true;
                    }
                }
            }
        } else if( sendConnection.getSource() instanceof NotationNode ) {
            NotationNode sourceNotation = (NotationNode) sendConnection.getSource();
            if( NodeType.LIFE_LINE_BEHAVIOR == sourceNotation.getNodeType() ) {
                Line lineNode = SequenceUtil.getLine(sourceNotation);
                
                if( targetNode instanceof Line ) {
                    if( targetNode.equals(lineNode)) {
                        specialCondition = true;
                    }
                } else if ( targetNode instanceof NotationNode ) {
                    NotationNode targetNotation = (NotationNode) targetNode;
                    if( NodeType.LIFE_LINE_BEHAVIOR == targetNotation.getNodeType() ) {
                        Line targetLineNode = SequenceUtil.getLine(targetNotation);
                        if( targetLineNode.equals(lineNode)) {
                            specialCondition = true;
                        }
                    }
                }
            }
        }
        // Self Connection의 BendPoint 생성
        if (sendConnection.getSource().equals(targetNode) || specialCondition) {
            // Self Connection을 위한 Anchor Point 생성
            Dimension sourceAnchorDimension = UMLDiagramFactory.eINSTANCE.createDimension();
            sourceAnchorDimension.setWidth(location.x);
            sourceAnchorDimension.setHeight(location.y + this.behaviorNode.getHeight());
            replyConnection.setSourceAnchor(sourceAnchorDimension);
            Dimension targetAnchorDimension = UMLDiagramFactory.eINSTANCE.createDimension();
            targetAnchorDimension.setWidth(location.x);
            targetAnchorDimension.setHeight(location.y + this.behaviorNode.getHeight() + margin);
            replyConnection.setTargetAnchor(targetAnchorDimension);

            Point sourcePoint = new Point(replyConnection.getSourceAnchor().getWidth(),
                replyConnection.getSourceAnchor().getHeight());
            Point targetPoint = new Point(replyConnection.getTargetAnchor().getWidth(),
                replyConnection.getTargetAnchor().getHeight());

            Point firstPosition = new Point(sourceAnchorDimension.getWidth() + margin * 4,
                sourceAnchorDimension.getHeight());
            BendPoint firstBendPoint = UMLDiagramFactory.eINSTANCE.createBendPoint();
            Dimension firstPositionFirstDimension = UMLDiagramFactory.eINSTANCE.createDimension();
            Dimension firstPositionSecondDimension = UMLDiagramFactory.eINSTANCE.createDimension();
            firstPositionFirstDimension.setWidth(firstPosition.getDifference(sourcePoint).width);
            firstPositionFirstDimension.setHeight(firstPosition.getDifference(sourcePoint).height);
            firstBendPoint.setFirstPosition(firstPositionFirstDimension);
            firstPositionSecondDimension.setWidth(firstPosition.getDifference(targetPoint).width);
            firstPositionSecondDimension.setHeight(firstPosition.getDifference(targetPoint).height);
            firstBendPoint.setSecondPosition(firstPositionSecondDimension);
            replyConnection.getBendPointList().add(0, firstBendPoint);

            Point secondPosition = new Point(firstPosition.x, targetAnchorDimension.getHeight());
            BendPoint secondBendPoint = UMLDiagramFactory.eINSTANCE.createBendPoint();
            Dimension secondPositionFirstDimension = UMLDiagramFactory.eINSTANCE.createDimension();
            Dimension secondPositionSecondDimension = UMLDiagramFactory.eINSTANCE.createDimension();
            secondPositionFirstDimension.setWidth(secondPosition.getDifference(sourcePoint).width);
            secondPositionFirstDimension.setHeight(secondPosition.getDifference(sourcePoint).height);
            secondBendPoint.setFirstPosition(secondPositionFirstDimension);
            secondPositionSecondDimension.setWidth(secondPosition.getDifference(targetPoint).width);
            secondPositionSecondDimension.setHeight(secondPosition.getDifference(targetPoint).height);
            secondBendPoint.setSecondPosition(secondPositionSecondDimension);
            replyConnection.getBendPointList().add(1, secondBendPoint);
        }

        
        
        // 20120608 by kang
        // 프래그먼트 내부에 synch message 생성 시 replyMessage가 프래그먼트 밖으로 생성되는 것을 방지.
        List<InteractionFragment> fragmentList = new ArrayList<InteractionFragment>();
        for (InteractionFragment fragment : SequenceUtil.getFragmentListByMessage((Message) sendConnection.getUmlModel())) {
            if (!fragmentList.contains(fragment)) {
                fragmentList.add(fragment);
            }
        }
        List<Element> resizedFragments = new ArrayList<Element>();
        for (InteractionFragment fragment : fragmentList) {

            Element parent = fragment.getOwner();
            if (!resizedFragments.contains(parent)) {
                resizedFragments.add(parent);
            } else {
                continue;
            }

            if (parent instanceof InteractionOperand) {
                InteractionOperand operand = (InteractionOperand) parent;
                ContainerNode container = SequenceUtil.getInteractionOperandNode(diagram, (InteractionOperand) operand);

                Element operandParent = operand.getOwner();
                if (operandParent instanceof CombinedFragment) {
                    NotationNode node = SequenceUtil.getCombinedFragmentNodeInDiagram(diagram,
                        (CombinedFragment) operandParent);
//                    if (this.sendConnection.getY() + this.behaviorNode.getHeight() - (node.getY() + node.getHeight()) > 0) {
//                        shiftCombinedFragment(container, this.sendConnection.getY() + this.behaviorNode.getHeight()
//                            - (node.getY() + node.getHeight()) + 20);
//                    }
                }
            }
        }
        //
        
        
        
        this.replyConnection.setY(this.sendConnection.getY() + this.behaviorNode.getHeight());
        if (!(sourceNode instanceof Line)) {
            this.replyConnection.setTarget(SequenceUtil.getLine(sourceNode));
        } else {
            this.replyConnection.setTarget(this.sourceNode);
        }
        this.replyConnection.setSource(this.behaviorNode);
        this.replyConnection.setRelationType(RelationType.REPLY_MESSAGE);

        this.replyConnection.setUmlModel(this.replyMessage);
        this.replyConnection.setName(this.replyMessage.getName());

        diagram.getConnectionList().add(this.replyConnection);

        DiagramUtil.attachSourceOfConnection(this.replyConnection);
        DiagramUtil.attachTargetOfConnection(this.replyConnection);
        
        
        
    }
    
    /**
     * shiftCombinedFragment
     *  
     * @param node
     * @param delta void
     */
    private void shiftCombinedFragment(AbstractNode node, int delta) {
        ContainerNode operandNode = (ContainerNode) node;
        int oldHeight = operandNode.getHeight();
        int heightDelta = delta;
        AbstractNode parentNode = (AbstractNode) node.getParent();
        
        int operandY = SequenceUtil.getOperandY((NotationNode) parentNode, operandNode) + operandNode.getHeight();
        Diagram diagram = SequenceUtil.getDiagram(parentNode);
        if(operandNode.getHeight() + heightDelta >= UICoreConstant.OPERAND_MIN_HEIGHT) {
            operandNode.setHeight(operandNode.getHeight() + heightDelta);
            parentNode.setHeight(parentNode.getHeight() + heightDelta);           
            SequenceUtil.shiftByResizeInteractionOperand(diagram, operandNode,  (NotationNode) parentNode, operandY, heightDelta);
        } else {
            operandNode.setHeight(UICoreConstant.OPERAND_MIN_HEIGHT);
            parentNode.setHeight(parentNode.getHeight() + heightDelta + (UICoreConstant.OPERAND_MIN_HEIGHT - (oldHeight + heightDelta)));              
            SequenceUtil.shiftByResizeInteractionOperand(diagram, operandNode, (NotationNode) parentNode, operandY, heightDelta + (UICoreConstant.OPERAND_MIN_HEIGHT - (oldHeight + heightDelta)));
        }
        
        if(heightDelta > 0) { //결합단편 높이가 늘어나면 라이프라인의 높이도 늘린다.
            SequenceUtil.shiftLifeLinesHeight(diagram, heightDelta);
        }
    }
    

    /**
     * 
     * 
     * @param object
     *            if null, undefined.
     */
    public void setOperationAndType(Operation operation, Type type) {
        this.operation = operation;
        this.type = type;
    }

}
