/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.manager.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.LifeLineNode;
import nexcore.tool.uml.model.umldiagram.Line;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.model.umldiagram.RelationType;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.BehaviorExecutionSpecification;
import org.eclipse.uml2.uml.ExecutionEvent;
import org.eclipse.uml2.uml.ExecutionOccurrenceSpecification;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageEvent;
import org.eclipse.uml2.uml.MessageOccurrenceSpecification;
import org.eclipse.uml2.uml.NamedElement;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.manager</li>
 * <li>서브 업무명 : nexcore.tool.uml.manager.util</li>
 * <li>설  명 : SequenceUtilManager</li>
 * <li>작성일 : 2010. 3. 24.</li>
 * <li>작성자 : ytchoi </li>
 * </ul>
 */
public class SequenceUtilManager {

    /**
     * rearrangeConnections
     * 
     * @param orgList
     * @return List<AbstractConnection>
     */
    public static List<AbstractConnection> rearrangeConnections(EList<AbstractConnection> orgList) {
        List<AbstractConnection> result = new ArrayList<AbstractConnection>();
        Map<String, String> tempMap = new HashMap<String, String>();

        AbstractConnection edge = null;
        AbstractConnection nextEdge = null;

        Lifeline srcLifeline = null;
        Lifeline tgtLifeline = null;
        Lifeline nextSrcLifeline = null;
        Lifeline nextTgtLifeline = null;

        Lifeline startLifeLine = null;
        int startIndex = 0;

        if (orgList == null)
            return result;

        result.addAll(orgList);

        // 분석모델의 전체 Edge에서 하나씩 추출한다.
        Object edgeArray[] = orgList.toArray();

        // 한번 수행을 위해서 임시로 입력
        tempMap.put(String.valueOf(0), "start"); //$NON-NLS-1$
        int preIndex = 0;
        int nextIndex = 0;

        for (int i = 0; i < edgeArray.length; i++) {
            edge = (AbstractConnection) edgeArray[i];
            if (i < edgeArray.length - 1) {
                nextEdge = (AbstractConnection) edgeArray[i + 1];
            } else {
                nextEdge = null;
            }

            // 현재 라이프라인을 구한다.
            if (edge.getSource().getUmlModel() instanceof Lifeline) {
                srcLifeline = (Lifeline) edge.getSource().getUmlModel();
            } else {
                srcLifeline = null;
            }

            if (edge.getTarget().getUmlModel() instanceof Lifeline) {
                tgtLifeline = (Lifeline) edge.getTarget().getUmlModel();
            } else {
                tgtLifeline = null;
            }

            // 다음 라이프라인을 구한다.
            if (nextEdge != null) {
                if (nextEdge.getSource().getUmlModel() instanceof Lifeline) {
                    nextSrcLifeline = (Lifeline) nextEdge.getSource().getUmlModel();
                } else {
                    nextSrcLifeline = null;
                }

                if (nextEdge.getTarget().getUmlModel() instanceof Lifeline) {
                    nextTgtLifeline = (Lifeline) nextEdge.getTarget().getUmlModel();
                } else {
                    nextTgtLifeline = null;
                }
            } else {
                nextSrcLifeline = null;
                nextTgtLifeline = null;
            }

            // 소스와 타겟이 다른 시작 Edge를 찾는다.
            if (startLifeLine == null && srcLifeline != null && tgtLifeline != null
                && !srcLifeline.getName().equals(tgtLifeline.getName())) {
                startLifeLine = srcLifeline;
                startIndex = i;
            }

            // 시작 Edge의 target과 다음 Edge의 source lifelene이 다르면 잘못된 순서인지 판단 후 순서
            // 교정
            if (startLifeLine != null && tgtLifeline != null && nextSrcLifeline != null
                && !tgtLifeline.getName().equals(nextSrcLifeline.getName())) {
                // nextSrcLifeline 가 시작 Lifeline과 동일하면 새롭게 시작하는 시퀀스임.
                // startIndex보다 하나 큰 경우와 붙어 있는 경우
                preIndex = startIndex + 1;
                nextIndex = i + 1;
                // if( nextSrcLifeline.getName().equals(startLifeLine.getName())
                // && (preIndex == nextIndex)) {
                if (nextSrcLifeline.getName().equals(startLifeLine.getName())) {
                    startIndex = nextIndex;
                    tempMap.put(String.valueOf(nextIndex), nextSrcLifeline.getName());
                }
            }
        }

        // 순서 교정
        if (tempMap.size() > 1) {
            result = exchangePosition(edgeArray, tempMap);
        }

        return result;
    }

    /**
     * 
     * 
     * @param edgeArray
     * @param tempMap
     * @return List<AbstractConnection>
     */
    public static List<AbstractConnection> exchangePosition(Object edgeArray[], Map<String, String> tempMap) {
        List<AbstractConnection> newResult = new ArrayList<AbstractConnection>();
        Set<String> set = tempMap.keySet();
        Map<String, String> foundIndex = new HashMap<String, String>();

        int length = tempMap.size();
        int index = 0;

        for (Iterator<String> iter = set.iterator(); iter.hasNext();) {
            index = Integer.parseInt(iter.next());

            newResult = exchange(newResult, edgeArray, length, index, foundIndex);
        }

        return newResult;

    }

    /**
     * 
     * 
     * @param newResult
     * @param edgeArray
     * @param length
     * @param index
     * @param foundIndex
     * @return List<AbstractConnection>
     */
    public static List<AbstractConnection> exchange(List<AbstractConnection> newResult, Object edgeArray[], int length,
                                                    int index, Map<String, String> foundIndex) {

        AbstractConnection edge = null;
        AbstractConnection nextEdge = null;
        AbstractConnection srcEdge = null;
        AbstractConnection tgtEdge = null;

        Lifeline srcLifeline = null;
        Lifeline tgtLifeline = null;
        Lifeline nextSrcLifeline = null;
        Lifeline nextTgtLifeline = null;

        for (int i = index; i < edgeArray.length; i++) {
            edge = (AbstractConnection) edgeArray[i];

            if (i == index) {
                srcEdge = edge;
            }

            if (i == index) {
                newResult.add(srcEdge);
            }

            if (srcEdge != null && tgtEdge != null) {
                srcEdge = tgtEdge;
                newResult.add(srcEdge);
                tgtEdge = null;
            }

            if (i < edgeArray.length - 1) {
                nextEdge = (AbstractConnection) edgeArray[i + 1];
            } else {
                nextEdge = null;
            }

            // 현재 라이프라인을 구한다.
            if (srcEdge.getSource().getUmlModel() instanceof Lifeline) {
                srcLifeline = (Lifeline) srcEdge.getSource().getUmlModel();
            } else {
                srcLifeline = null;
            }

            if (srcEdge.getTarget().getUmlModel() instanceof Lifeline) {
                tgtLifeline = (Lifeline) srcEdge.getTarget().getUmlModel();
            } else {
                tgtLifeline = null;
            }

            // 다음 라이프라인을 구한다.
            if (nextEdge != null) {
                if (nextEdge.getSource().getUmlModel() instanceof Lifeline) {
                    nextSrcLifeline = (Lifeline) nextEdge.getSource().getUmlModel();
                } else {
                    nextSrcLifeline = null;
                }

                if (nextEdge.getTarget().getUmlModel() instanceof Lifeline) {
                    nextTgtLifeline = (Lifeline) nextEdge.getTarget().getUmlModel();
                } else {
                    nextTgtLifeline = null;
                }
            } else {
                nextSrcLifeline = null;
                nextTgtLifeline = null;
            }

            // 타겟을 구한다.
            if (tgtLifeline != null && nextSrcLifeline != null
                && tgtLifeline.getName().equals(nextSrcLifeline.getName())) {
                if (!foundIndex.containsKey(String.valueOf(i + 1))) {
                    tgtEdge = nextEdge;
                    foundIndex.put(String.valueOf(i + 1), String.valueOf(i + 1));
                }
            }
        }

        return newResult;
    }

    /**
     * getLifeLifeNode
     * 
     * @param diagram
     * @param lifeline
     * @return LifeLineNode
     */
    public static LifeLineNode getLifeLifeNode(Diagram diagram, Lifeline lifeline) {
        LifeLineNode lifeLineNode = null;

        EList<AbstractNode> nodeList = diagram.getNodeList();
        for (AbstractNode node : nodeList) {
            if (lifeline.equals(node.getUmlModel())) {
                lifeLineNode = (LifeLineNode) node;
                break;
            }
        }

        return lifeLineNode;
    }

    /**
     * ascSortListByXValue
     * 
     * @param nodeList
     * @return List<AbstractNode>
     */
    public static List<AbstractNode> ascSortedLifeLineNodeListByXValue(List<AbstractNode> nodeList) {
        List<AbstractNode> list = new ArrayList<AbstractNode>();

        List<AbstractNode> lifeLineNodeList = getOnlyLifeLineNodeList(nodeList);

        AbstractNode node;
        boolean added;

        for (int i = 0; i < lifeLineNodeList.size(); i++) {
            node = lifeLineNodeList.get(i);
            added = false;
            if (list.size() == 0) {
                list.add(node);
            } else {

                for (int k = 0; k < list.size(); k++) {
                    if (list.get(k).getX() >= node.getX()) {
                        list.add(k, node);
                        added = true;
                        break;
                    }
                }
                if (!added) {
                    list.add(node);
                }
            }
        }

        return list;
    }

    /**
     * getOnlyLifeLineNodeList
     * 
     * @param nodeList
     * @return List<AbstractNode>
     */
    private static List<AbstractNode> getOnlyLifeLineNodeList(List<AbstractNode> nodeList) {
        List<AbstractNode> list = new ArrayList<AbstractNode>();

        for (int i = 0; i < nodeList.size(); i++) {
            if (nodeList.get(i) instanceof LifeLineNode) {
                list.add(nodeList.get(i));
            }
        }

        return list;
    }

    /**
     * ascSortListByYValue
     * 
     * @param nodeList
     * @return List<AbstractNode>
     */
    public static EList<AbstractConnection> ascSortedMessageListByYValue(EList<AbstractConnection> messageList) {
        EList<AbstractConnection> list = new BasicEList<AbstractConnection>();

        EList<AbstractConnection> connectoinList = getOnlyMessageList(messageList);
        AbstractConnection connection;
        boolean added;

        for (int i = 0; i < connectoinList.size(); i++) {
            connection = connectoinList.get(i);
            added = false;
            if (list.size() == 0) {
                list.add(connection);
            } else {

                for (int k = 0; k < list.size(); k++) {
                    if (list.get(k).getY() >= connection.getY()) {
                        list.add(k, connection);
                        added = true;
                        break;
                    }
                }
                if (!added) {
                    list.add(connection);
                }
            }
        }

        return list;
    }

    /**
     * getOnlyMessageList
     * 
     * @param messageList
     * @return List<AbstractConnection>
     */
    public static EList<AbstractConnection> getOnlyMessageList(EList<AbstractConnection> messageList) {
        EList<AbstractConnection> list = new BasicEList<AbstractConnection>();

        for (int i = 0; i < messageList.size(); i++) {
            if (!RelationType.ATTACHEMENT.equals(messageList.get(i).getRelationType())) {
                list.add(messageList.get(i));
            }
        }

        return list;
    }

    /**
     * checkLifeLinesHeight
     * 
     * @param otherMessage
     * @param behaviorNode
     * @param k
     *            void
     */
    public static void checkLifeLinesHeight(Diagram diagram, AbstractConnection message, int margin) {
        NotationNode lifeLineNode;
        int behaviorHeight;
        lifeLineNode = SequenceUtilManager.getLifeLineNode((AbstractNode) message.getTarget());
        behaviorHeight = 0;
        if (lifeLineNode.getY() + lifeLineNode.getHeight() < message.getY() + behaviorHeight) {
            int shiftHeight = (message.getY() + behaviorHeight) - (lifeLineNode.getY() + lifeLineNode.getHeight())
                + margin;
            // 모든 라이프라인의 높이를 생성된 Behavior의 높이만클 늘린다.
            List<AbstractNode> nodeList = getOnlyLifeLineList(diagram.getNodeList());
            for (AbstractNode node : nodeList) {
                node.setHeight(node.getHeight() + shiftHeight);
            }
        }
    }

    /**
     * checkLifeLinesHeight
     * 
     * @param otherMessage
     * @param behaviorNode
     * @param k
     *            void
     */
    public static void checkLifeLinesHeight(Diagram diagram, AbstractConnection message, NotationNode behaviorNode,
                                            int margin) {
        NotationNode lifeLineNode;
        int behaviorHeight;
        lifeLineNode = SequenceUtilManager.getLifeLineNode(behaviorNode);
        behaviorHeight = 0;
        if (behaviorNode != null) {
            behaviorHeight = behaviorNode.getHeight();
        }
        if (lifeLineNode.getY() + lifeLineNode.getHeight() < message.getY() + behaviorHeight) {
            int shiftHeight = (message.getY() + behaviorHeight) - (lifeLineNode.getY() + lifeLineNode.getHeight())
                + margin;
            // 모든 라이프라인의 높이를 생성된 Behavior의 높이만클 늘린다.
            List<AbstractNode> nodeList = getOnlyLifeLineList(diagram.getNodeList());
            for (AbstractNode node : nodeList) {
                node.setHeight(node.getHeight() + shiftHeight);
            }
        }
    }

    /**
     * getOnlyLifeLineList
     * 
     * @param nodeList
     * @return List<NotationNode>
     */
    public static List<AbstractNode> getOnlyLifeLineList(EList<AbstractNode> nodeList) {
        List<AbstractNode> list = new ArrayList<AbstractNode>();

        for (int i = 0; i < nodeList.size(); i++) {
            if (nodeList.get(i) instanceof LifeLineNode) {
                list.add(nodeList.get(i));
            }
        }

        return list;
    }

    /**
     * 해당 노드(Linfe or Behavior)의 LifeLineNode를 찾아서 리턴한다.
     * 
     * @param node
     * @return LifeLineNode
     */
    public static LifeLineNode getLifeLineNode(AbstractNode node) {
        LifeLineNode lifeLineNode = null;

        if (node instanceof LifeLineNode) {
            return (LifeLineNode) node;
        }

        if (!(node instanceof Line) && !(NodeType.LIFE_LINE_BEHAVIOR.equals(node.getNodeType()))) {
            return null;
        }

        if (node.getParent() instanceof LifeLineNode) {
            lifeLineNode = (LifeLineNode) node.getParent();
        } else {
            lifeLineNode = getLifeLineNode((AbstractNode) node.getParent());
        }

        return lifeLineNode;
    }

    /**
     * deleteUmlMessage
     *  
     * @param connection void
     */
    public static void deleteUmlMessage(AbstractConnection connection) {
        NamedElement client;
        NamedElement supplier;

        client = (NamedElement) (SequenceUtilManager.getLifeLineNode((AbstractNode) connection.getSource())).getUmlModel();
        supplier = (NamedElement) (SequenceUtilManager.getLifeLineNode((AbstractNode) connection.getTarget())).getUmlModel();

        Interaction interaction = (Interaction) client.eContainer();
        Message message = (Message) connection.getUmlModel();
        if (null == message) {
            return;
        }
        MessageOccurrenceSpecification sendMessageOccurrenceSpecificationForSend = (MessageOccurrenceSpecification) message.getSendEvent();
        MessageOccurrenceSpecification receiveMessageOccurrenceSpecificationForSend = (MessageOccurrenceSpecification) message.getReceiveEvent();

        MessageEvent sendEventForSend = (MessageEvent) sendMessageOccurrenceSpecificationForSend.getEvent();
        MessageEvent receiveEventForSend = (MessageEvent) receiveMessageOccurrenceSpecificationForSend.getEvent();

        deleteFragment(client,
            supplier,
            interaction,
            sendMessageOccurrenceSpecificationForSend,
            receiveMessageOccurrenceSpecificationForSend,
            sendEventForSend,
            receiveEventForSend);

        interaction.getMessages().remove(message);
        UMLManager.deleteElement(message);

    }

    /**
     * refreshMessageOrder
     *  
     * @param diagram void
     */
    public static void refreshMessageOrder(Diagram diagram) {

        AbstractConnection abstractConnection;
        for (int i = 0; i < diagram.getConnectionList().size(); i++) {
            abstractConnection = diagram.getConnectionList().get(i);
            abstractConnection.setName(abstractConnection.getName());
        }
    }

    /**
     * deleteMessage
     *  
     * @param message
     * @param diagram void
     */
    public static void deleteMessage(AbstractConnection message, Diagram diagram) {

        if (!RelationType.REPLY_MESSAGE.equals(message.getRelationType())) {

            NotationNode behaviorNode = (NotationNode) message.getTarget();
            if (behaviorNode != null) {
                // List<AbstractConnection> list = new
                // ArrayList<AbstractConnection>();
                //
                // if (behaviorNode.getIncomingConnectionList().size() > 0) {
                // list.addAll(behaviorNode.getIncomingConnectionList());
                // }
                //
                // if (behaviorNode.getOutgoingConnectionList().size() > 0) {
                // list.addAll(behaviorNode.getOutgoingConnectionList());
                // }
                //
                // AbstractConnection childMessage;
                // for (int i = 0; i < list.size(); i++) {
                // childMessage = list.get(i);
                // if (!message.equals(childMessage)) {
                // deleteMessage(childMessage, diagram);
                // }
                // }
                //
                // List<NotationNode> childrenBehaviorList =
                // getChildrenBehavior(behaviorNode);
                // if (childrenBehaviorList != null &&
                // childrenBehaviorList.size() > 0) {
                // NotationNode childBehavior = null;
                // for (int i = 0; i < childrenBehaviorList.size(); i++) {
                // childBehavior = childrenBehaviorList.get(i);
                // childBehavior.setParent(behaviorNode.getParent());
                // }
                // }

                AbstractConnection childMessage;
                for (int i = 0; i < behaviorNode.getOutgoingConnectionList().size(); i++) {
                    childMessage = behaviorNode.getOutgoingConnectionList().get(i);
                    if (RelationType.REPLY_MESSAGE.equals(childMessage.getRelationType())) {
                        deleteMessage(childMessage, diagram);
                    }
                }

                LifeLineNode lifeLineNode = SequenceUtilManager.getLifeLineNode(behaviorNode);

                for (NotationNode behaviorNotationNode : lifeLineNode.getBehaviorList()) {
                    // if(lifeLineNode.getLine().equals(behaviorNotationNode.getParent())){
                    // System.out.println("true");
                    // } else {
                    // System.out.println("false");
                    // }
                    behaviorNotationNode.setParent(lifeLineNode.getLine());
                }

                lifeLineNode.getBehaviorList().remove(behaviorNode);
            }
        }

        DiagramUtil.detachSourceOfConnection(message);
        DiagramUtil.detachTargetOfConnection(message);
        deleteUmlMessage(message);

        diagram.getConnectionList().remove(message);

        UMLManager.deleteElement(message);
    }

    /**
     * deleteFragment
     * 
     * @param supplier
     * @param client
     * 
     * @param interaction
     * @param sendMessageOccurrenceSpecificationForSend
     * @param receiveMessageOccurrenceSpecificationForSend
     * @param sendEventForSend
     * @param receiveEventForSend
     *            void
     */
    private static void deleteFragment(NamedElement client, NamedElement supplier, Interaction interaction,
                                       MessageOccurrenceSpecification sendMessageOccurrenceSpecificationForSend,
                                       MessageOccurrenceSpecification receiveMessageOccurrenceSpecificationForSend,
                                       MessageEvent sendEventForSend, MessageEvent receiveEventForSend) {

        interaction.getNearestPackage().getPackagedElements().remove(sendEventForSend);
        interaction.getNearestPackage().getPackagedElements().remove(receiveEventForSend);
        UMLManager.deleteElement(sendEventForSend);
        UMLManager.deleteElement(receiveEventForSend);

        InteractionFragment interactionFragment;
        BehaviorExecutionSpecification behaviorExecutionSpecification;
        ExecutionOccurrenceSpecification executionOccurrenceSpecification;
        ExecutionEvent executionEvent;
        for (int i = 0; i < interaction.getFragments().size(); i++) {
            interactionFragment = interaction.getFragments().get(i);
            if (interactionFragment instanceof BehaviorExecutionSpecification) {
                behaviorExecutionSpecification = (BehaviorExecutionSpecification) interactionFragment;
                if (behaviorExecutionSpecification.getStart().equals(receiveMessageOccurrenceSpecificationForSend)
                    && behaviorExecutionSpecification.getFinish().equals(receiveMessageOccurrenceSpecificationForSend)) {
                    executionOccurrenceSpecification = findexEcutionOccurrenceSpecification(interaction,
                        behaviorExecutionSpecification);
                    if (executionOccurrenceSpecification != null) {
                        executionEvent = (ExecutionEvent) executionOccurrenceSpecification.getEvent();
                        interaction.getNearestPackage().getPackagedElements().remove(executionEvent);
                        interaction.getFragments().remove(executionOccurrenceSpecification);
                        UMLManager.deleteElement(executionEvent);
                        UMLManager.deleteElement(executionOccurrenceSpecification);
                    }
                    ((Lifeline) client).getCoveredBys().remove(behaviorExecutionSpecification);
                    ((Lifeline) supplier).getCoveredBys().remove(behaviorExecutionSpecification);
                    interaction.getFragments().remove(behaviorExecutionSpecification);
                    UMLManager.deleteElement(behaviorExecutionSpecification);
                    break;
                }

            }
        }

        ((Lifeline) client).getCoveredBys().remove(sendMessageOccurrenceSpecificationForSend);
        ((Lifeline) client).getCoveredBys().remove(receiveMessageOccurrenceSpecificationForSend);
        ((Lifeline) supplier).getCoveredBys().remove(receiveMessageOccurrenceSpecificationForSend);
        ((Lifeline) supplier).getCoveredBys().remove(sendMessageOccurrenceSpecificationForSend);
        interaction.getFragments().remove(sendMessageOccurrenceSpecificationForSend);
        interaction.getFragments().remove(receiveMessageOccurrenceSpecificationForSend);
        UMLManager.deleteElement(sendMessageOccurrenceSpecificationForSend);
        UMLManager.deleteElement(receiveMessageOccurrenceSpecificationForSend);
    }

    /**
     * findexEcutionOccurrenceSpecification
     *  
     * @param interaction
     * @param behaviorExecutionSpecification
     * @return ExecutionOccurrenceSpecification
     */
    private static ExecutionOccurrenceSpecification findexEcutionOccurrenceSpecification(
                                                                                         Interaction interaction,
                                                                                         BehaviorExecutionSpecification behaviorExecutionSpecification) {
        InteractionFragment interactionFragment;
        ExecutionOccurrenceSpecification executionOccurrenceSpecification;
        for (int i = 0; i < interaction.getFragments().size(); i++) {
            interactionFragment = interaction.getFragments().get(i);
            if (interactionFragment instanceof ExecutionOccurrenceSpecification) {
                executionOccurrenceSpecification = (ExecutionOccurrenceSpecification) interactionFragment;
                if (executionOccurrenceSpecification.getExecution().equals(behaviorExecutionSpecification)) {
                    return executionOccurrenceSpecification;
                }
            }
        }

        return null;
    }
}
