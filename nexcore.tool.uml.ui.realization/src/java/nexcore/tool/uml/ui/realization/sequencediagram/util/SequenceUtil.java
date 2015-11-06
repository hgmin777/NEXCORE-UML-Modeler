/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.ui.realization.sequencediagram.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.manager.UMLHelper;
import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.manager.util.DiagramUtil;
import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.LifeLineNode;
import nexcore.tool.uml.model.umldiagram.Line;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.model.umldiagram.RelationType;
import nexcore.tool.uml.ui.core.UICoreConstant;
import nexcore.tool.uml.ui.core.UiCorePlugin;
import nexcore.tool.uml.ui.core.util.PreferenceUtil;
import nexcore.tool.uml.ui.realization.RealizationPlugin;
import nexcore.tool.uml.ui.realization.sequencediagram.dialog.CreateClassDialog;
import nexcore.tool.uml.ui.realization.sequencediagram.dialog.CreateOperationDialog;
import nexcore.tool.uml.ui.realization.sequencediagram.edit.part.LifeLineBehaviorEditPart;
import nexcore.tool.uml.ui.realization.sequencediagram.edit.part.LifeLineEditPart;
import nexcore.tool.uml.ui.realization.sequencediagram.edit.part.LineEditPart;
import nexcore.tool.uml.ui.realization.sequencediagram.edit.part.SequenceDiagramEditPart;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.uml2.uml.BehaviorExecutionSpecification;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.CombinedFragment;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ExecutionEvent;
import org.eclipse.uml2.uml.ExecutionOccurrenceSpecification;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.InteractionConstraint;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.InteractionOperand;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageEvent;
import org.eclipse.uml2.uml.MessageOccurrenceSpecification;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.ReceiveOperationEvent;
import org.eclipse.uml2.uml.SendOperationEvent;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.VisibilityKind;
import org.eclipse.uml2.uml.internal.impl.MessageOccurrenceSpecificationImpl;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.realization</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.realization.sequencediagram.util</li>
 * <li>설 명 : SequenceUtil</li>
 * <li>작성일 : 2010. 1. 19.</li>
 * <li>작성자 : ytchoi</li>
 * </ul>
 */
@SuppressWarnings("restriction")
public class SequenceUtil {

    /**
     * 해당 editpart의 diagram을 찾아서 리턴한다.
     * 
     * @param editPart
     * @return Diagram
     */
    public static Diagram getDiagram(EditPart editPart) {
        Diagram diagram = null;

        if (!(editPart instanceof LifeLineEditPart) && !(editPart instanceof LineEditPart)
            && !(editPart instanceof LifeLineBehaviorEditPart)) {
            return null;
        }

        if (editPart.getParent() instanceof SequenceDiagramEditPart) {
            diagram = (Diagram) editPart.getParent().getModel();
        } else {
            diagram = getDiagram(editPart.getParent());
        }

        return diagram;
    }
    
    /**
     * 해당 node의 diagram을 찾아서 리턴한다.
     * 
     * @param editPart
     * @return Diagram
     */
    public static Diagram getDiagram(AbstractNode node) {
        Diagram diagram = null;

        if(node.getParent() instanceof Diagram) {
            diagram = (Diagram) node.getParent();
        } else if(node.getParent() instanceof AbstractNode){
            diagram = getDiagram((AbstractNode) node.getParent());
        }
        
        return diagram;
    }
    
    /**
     * 해당 node의 diagram을 찾아서 리턴한다.
     * 
     * @param editPart
     * @return Diagram
     */
    public static Diagram getDiagramByEContainer(AbstractNode node) {
        Diagram diagram = null;

        if(node.eContainer() instanceof Diagram) {
            diagram = (Diagram) node.eContainer();
        } else if(node.eContainer() instanceof AbstractNode){
            diagram = getDiagramByEContainer((AbstractNode) node.eContainer());
        }
        
        return diagram;
    }

    /**
     * 해당 노드(Linfe or Behavior)의 LifeLineNode를 찾아서 리턴한다.
     * 
     * @param node
     * @return LifeLineNode
     */
    public static LifeLineNode getLifeLineNode(AbstractNode node) {
        LifeLineNode lifeLineNode = null;

        if (null == node) {
            MessageDialog.openWarning(UiCorePlugin.getShell(),
                UMLMessage.MESSAGE_WARNNING_MODEL,
                UMLMessage.MESSAGE_WARNNING_OF_WRONGMODEL);
            return null;
        }

        if (node instanceof LifeLineNode) {
            return (LifeLineNode) node;
        }

        if (!(node instanceof Line) && !(NodeType.LIFE_LINE_BEHAVIOR.equals(node.getNodeType()))) {
            return null;
        }

        EObject eParent;
        if (null == node.getParent()) {
            eParent = node.eContainer();
            if (eParent instanceof LifeLineNode) {
                lifeLineNode = (LifeLineNode) eParent;
            }
            MessageDialog.openWarning(UiCorePlugin.getShell(),
                UMLMessage.MESSAGE_WARNNING_MODEL,
                UMLMessage.MESSAGE_WARNNING_OF_WRONGMODEL);
        } else if (node.getParent() instanceof LifeLineNode) {
            lifeLineNode = (LifeLineNode) node.getParent();
        } else {
            lifeLineNode = getLifeLineNode((AbstractNode) node.getParent());
        }

        return lifeLineNode;
    }
    
    /**
     * 해당 노드(Behavior)의 LineNode를 찾아서 리턴한다.
     * 
     * @param node
     * @return LifeLineNode
     */
    public static Line getLine(AbstractNode node) {
        Line line = null;

        if (node instanceof Line) {
            return (Line) node;
        }

        if (!(NodeType.LIFE_LINE_BEHAVIOR.equals(node.getNodeType()))) {
            return null;
        }

        if (node.getParent() instanceof Line) {
            line = (Line) node.getParent();
        } else {
            line = getLine((AbstractNode) node.getParent());
        }

        return line;
    }

    /**
     * 바로 위의 BehaviorNode를 가져온다.
     * 
     * @param message
     * @param behaviorList
     * @return NotationNode
     */
    public static NotationNode getAboveNearBehaviorBySource(AbstractConnection message, List<NotationNode> behaviorList) {

        NotationNode nearBehavior = null;

        NotationNode behavior;
        for (int i = 0; i < behaviorList.size(); i++) {
            behavior = behaviorList.get(i);

            if (nearBehavior == null && !message.getSource().equals(behavior) && behavior.getY() < message.getY()) {
                nearBehavior = behavior;
                continue;
            }

            if (nearBehavior != null) {
                if (behavior.getY() < message.getY()
                    && behavior.getY() + behavior.getHeight() > nearBehavior.getY() + nearBehavior.getHeight()) {
                    nearBehavior = behavior;
                }
            }
        }

        return nearBehavior;
    }

    /**
     * 바로 위의 BehaviorNode를 가져온다.
     * 
     * @param message
     * @param behaviorList
     * @return NotationNode
     */
    public static NotationNode getAboveNearIncludedBehaviorBySource(AbstractConnection message,
                                                                    List<NotationNode> behaviorList) {

        NotationNode nearBehavior = null;

        NotationNode behavior;
        for (int i = 0; i < behaviorList.size(); i++) {
            behavior = behaviorList.get(i);

            if (nearBehavior == null && !message.getSource().equals(behavior) && behavior.getY() < message.getY()
                && behavior.getY() + behavior.getHeight() < message.getY()) {
                nearBehavior = behavior;
                continue;
            }

            if (nearBehavior != null) {
                if (behavior.getY() < message.getY() && behavior.getY() + behavior.getHeight() < message.getY()
                    && behavior.getY() + behavior.getHeight() > nearBehavior.getY() + nearBehavior.getHeight()) {
                    nearBehavior = behavior;
                }
            }
        }

        return nearBehavior;
    }

    /**
     * 바로 위의 BehaviorNode를 가져온다.
     * 
     * @param message
     * @param behaviorList
     * @return NotationNode
     */
    public static NotationNode getAboveNearBehaviorByTarget(AbstractConnection message, List<NotationNode> behaviorList) {

        NotationNode nearBehavior = null;

        NotationNode behavior;
        for (int i = 0; i < behaviorList.size(); i++) {
            behavior = behaviorList.get(i);

            if (nearBehavior == null && !message.getSource().equals(behavior) && behavior.getY() < message.getY()) {
                nearBehavior = behavior;
                continue;
            }

            if (nearBehavior != null) {
                if (behavior.getY() < message.getY()
                    && behavior.getY() + behavior.getHeight() > nearBehavior.getY() + nearBehavior.getHeight()) {
                    nearBehavior = behavior;
                }
            }
        }

        return nearBehavior;
    }

    /**
     * 바로 위의 BehaviorNode를 가져온다.
     * 
     * @param message
     * @param behaviorList
     * @return NotationNode
     */
    public static NotationNode getAboveNearIncludedBehaviorByTarget(AbstractConnection message,
                                                                    List<NotationNode> behaviorList) {

        NotationNode nearBehavior = null;

        NotationNode behavior;
        for (int i = 0; i < behaviorList.size(); i++) {
            behavior = behaviorList.get(i);

            if (nearBehavior == null && !message.getSource().equals(behavior) && behavior.getY() < message.getY()
                && behavior.getY() + behavior.getHeight() < message.getY()) {
                nearBehavior = behavior;
                continue;
            }

            if (nearBehavior != null) {
                if (behavior.getY() < message.getY() && behavior.getY() + behavior.getHeight() < message.getY()
                    && behavior.getY() + behavior.getHeight() > nearBehavior.getY() + nearBehavior.getHeight()) {
                    nearBehavior = behavior;
                }
            }
        }

        return nearBehavior;
    }

    /**
     * 해당 behavior의 자식들 중에서 가작 낮은 위치 값.
     * 
     * @param behavior
     * @return int
     */
    public static int getBehaviorLowYValueByChildren(NotationNode behavior) {

        int lowYValue = 0;
        int childLowYValue = 0;

        List<NotationNode> children = getChildrenBehavior(behavior);
        NotationNode child;
        for (int i = 0; i < children.size(); i++) {
            child = children.get(i);
            if (lowYValue < child.getY() + child.getHeight()) {
                lowYValue = child.getY() + child.getHeight();
            }
            if (getChildrenBehavior(child).size() > 0) {
                childLowYValue = getBehaviorLowYValueByChildren(child);
                if (lowYValue < childLowYValue) {
                    lowYValue = childLowYValue;
                }
            }
        }

        return lowYValue;
    }

    /**
     * 해당 message 의 자식들 중에서 가작 낮은 위치(behavior 위치 포함) 값.
     * 
     * @param behavior
     * @return int
     */
    public static int getLowYValueByMessage(AbstractConnection message) {

        if (RelationType.REPLY_MESSAGE.equals(message.getRelationType())) {
            return message.getY();
        }

        NotationNode behavior = (NotationNode) message.getTarget();
        int lowYValue = behavior.getY() + behavior.getHeight();

        List<AbstractConnection> list = new ArrayList<AbstractConnection>();
        list.addAll(getChildrenMessage(message));

        AbstractConnection childMessage;
        int childLowYValue = 0;
        for (int i = 0; i < list.size(); i++) {
            childMessage = list.get(i);
            childLowYValue = getLowYValueByMessage(childMessage);
            if (lowYValue < childLowYValue) {
                lowYValue = childLowYValue;
            }
        }

        return lowYValue;
    }

    /**
     * 해당 behavior 의 incomming, outgoing 메시지중 가작 낮은 위치 값.
     * 
     * @param behavior
     * @return int
     */
    public static int getMessageLowYValueByChildren(NotationNode behavior) {
        int lowYValue = 0;

        List<AbstractConnection> list = new ArrayList<AbstractConnection>();

        if (behavior.getIncomingConnectionList().size() > 0) {
            list.addAll(behavior.getIncomingConnectionList());
        }

        if (behavior.getOutgoingConnectionList().size() > 0) {
            AbstractConnection outMessage;
            for (int i = 0; i < behavior.getOutgoingConnectionList().size(); i++) {
                outMessage = behavior.getOutgoingConnectionList().get(i);
                if (outMessage.getY() != behavior.getY() + behavior.getHeight()) {
                    list.add(outMessage);
                }
            }
        }

        AbstractConnection message;
        for (int i = 0; i < list.size(); i++) {
            message = list.get(i);
            if (lowYValue < message.getY()) {
                lowYValue = message.getY();
            }
        }

        return lowYValue;
    }

    /**
     * 해당 behavior의 자식 behavior 리스트를 리턴한다.
     * 
     * @param behavior
     * @return List<NotationNode>
     */
    public static List<NotationNode> getChildrenBehavior(NotationNode behavior) {
        List<NotationNode> childrenList = new ArrayList<NotationNode>();
        LifeLineNode lifeLineNode = SequenceUtil.getLifeLineNode(behavior);
        NotationNode child;
        for (int i = 0; i < lifeLineNode.getBehaviorList().size(); i++) {
            child = lifeLineNode.getBehaviorList().get(i);
            if (behavior.equals(child.getParent())) {
                childrenList.add(child);
            }
        }
        return childrenList;
    }

    /**
     * 해당 Message의 자식 message 리스트를 리턴한다.
     * 
     * @param behavior
     * @return List<NotationNode>
     */
    public static List<AbstractConnection> getChildrenMessage(AbstractConnection message) {
        List<AbstractConnection> childrenList = new ArrayList<AbstractConnection>();

        if (RelationType.REPLY_MESSAGE.equals(message.getRelationType())) {
            return childrenList;
        }

        NotationNode behavior = (NotationNode) message.getTarget();

        if (behavior.getIncomingConnectionList().size() > 0) {
            AbstractConnection inMessage;
            for (int i = 0; i < behavior.getIncomingConnectionList().size(); i++) {
                inMessage = behavior.getIncomingConnectionList().get(i);
                if (inMessage.getY() != behavior.getY()) {
                    childrenList.add(inMessage);
                }
            }
        }

        if (behavior.getOutgoingConnectionList().size() > 0) {
            childrenList.addAll(behavior.getOutgoingConnectionList());
        }

        for (int i = 0; i < childrenList.size(); i++) {
            childrenList.addAll(getChildrenMessage(childrenList.get(i)));
        }

        List<NotationNode> childrenBehaviorList = getChildrenBehavior(behavior);
        for (int i = 0; i < childrenBehaviorList.size(); i++) {
            childrenList.addAll(getChildrenMessageListByBehavior(childrenBehaviorList.get(i)));
        }

        return childrenList;
    }

    /**
     * getChildrenMessageListByBehavior
     * 
     * @param behavior
     * @return List<AbstractConnection>
     */
    public static List<AbstractConnection> getChildrenMessageListByBehavior(NotationNode behavior) {

        List<AbstractConnection> childrenList = new ArrayList<AbstractConnection>();

        if( behavior == null ) {
            return childrenList;
        }
        
        if (behavior.getIncomingConnectionList().size() > 0) {
            childrenList.addAll(behavior.getIncomingConnectionList());
        }

        if (behavior.getOutgoingConnectionList().size() > 0) {
            childrenList.addAll(behavior.getOutgoingConnectionList());
        }

        List<AbstractConnection> childrenMessageList;
        AbstractConnection childMessage;
        for (int i = 0; i < childrenList.size(); i++) {
            childMessage = childrenList.get(i);
            if (childMessage.getY() != behavior.getY()) {
                childrenMessageList = getChildrenMessage(childMessage);
                if (childrenMessageList.size() > 0) {
                    childrenList.addAll(childrenMessageList);
                }
            }
        }

        List<NotationNode> childrenBehaviorList = getChildrenBehavior(behavior);
        for (int i = 0; i < childrenBehaviorList.size(); i++) {
            childrenList.addAll(getChildrenMessageListByBehavior(childrenBehaviorList.get(i)));
        }

        return childrenList;
    }

    /**
     * 해당 behaviorEditPart의 자식 behaviorEditPart 리스트를 리턴한다.
     * 
     * @param behavior
     * @return List<NotationNode>
     */
    public static List<GraphicalEditPart> getChildrenBehaviorEditPart(LifeLineBehaviorEditPart behaviorEditPart) {
        List<GraphicalEditPart> childrenList = new ArrayList<GraphicalEditPart>();
        LifeLineEditPart lifeLineEditPart = (LifeLineEditPart) behaviorEditPart.getParent();
        GraphicalEditPart child;
        for (int i = 0; i < lifeLineEditPart.getChildren().size(); i++) {
            child = (GraphicalEditPart) lifeLineEditPart.getChildren().get(i);
            if (child.getModel() instanceof NotationNode
                && NodeType.LIFE_LINE_BEHAVIOR.equals(((NotationNode) child.getModel()).getNodeType())
                && behaviorEditPart.getModel().equals(((NotationNode) child.getModel()).getParent())) {
                childrenList.add(child);
            }
        }
        return childrenList;
    }

    /**
     * 해당 message에서 인접한 상 위의 Message를 리턴한다.
     * 
     * @param message
     * @param messageList
     * @return AbstractConnection
     */
    public static AbstractConnection getAboveNearMessage(AbstractConnection message,
                                                         List<AbstractConnection> messageList) {

        AbstractConnection messageConnection;
        AbstractConnection upperConnection = null;

        for (int i = 0; i < messageList.size(); i++) {
            messageConnection = (AbstractConnection) messageList.get(i);
            if (message.equals(messageConnection) && i > 0) {
                upperConnection = (AbstractConnection) messageList.get(i - 1);
                break;
            }
        }

        return upperConnection;
    }

    /**
     * Behavior의 높이를 늘린다.
     * 
     * @param targetNode
     *            void
     */
    public static void addBehaviorHeight(Diagram diagram, AbstractConnection connection, NotationNode behaviorNode,
                                         List<AbstractConnection> messageList, int addValue, int margin) {

        if (getBehaviorLowYValueByChildren(behaviorNode) > behaviorNode.getY() + behaviorNode.getHeight() + addValue) {
            return;
        }
        if (getMessageLowYValueByChildren(behaviorNode) > behaviorNode.getY() + behaviorNode.getHeight() + addValue) {
            return;
        }

        // addValue 해주면 behavior 밑의 메시지들을 포함하게 되는 Message 들에게 shifht를 해준다.
        AbstractConnection message;
        for (int i = 0; i < messageList.size(); i++) {
            message = messageList.get(i);
            if (!connection.equals(message) && message.getY() >= behaviorNode.getY() + behaviorNode.getHeight()
                && message.getY() < behaviorNode.getY() + behaviorNode.getHeight() + addValue) {
                message.setY(message.getY() + addValue);
                if (!RelationType.REPLY_MESSAGE.equals(message.getRelationType())) {
                    setOtherBehaviorNodeLayout(diagram, messageList, addValue, margin, message);
                }

                break;
            }
        }

        behaviorNode.setHeight(behaviorNode.getHeight() + addValue);

        NotationNode parent = (NotationNode) behaviorNode.getParent();
        if (!(parent instanceof Line)) {
            addBehaviorHeight(diagram, connection, parent, messageList, addValue, margin);
        }

    }

    /**
     * Behavior의 높이를 늘린다.
     * 
     * @param targetNode
     *            void
     */
    public static void addBehaviorHeight(Diagram diagram, NotationNode behaviorNode,
                                         List<AbstractConnection> messageList, int addValue, int margin) {

        if (getBehaviorLowYValueByChildren(behaviorNode) > behaviorNode.getY() + behaviorNode.getHeight() + addValue) {
            return;
        }
        if (getMessageLowYValueByChildren(behaviorNode) > behaviorNode.getY() + behaviorNode.getHeight() + addValue) {
            return;
        }

        // addValue 해주면 behavior 밑의 메시지들을 포함하게 되는 Message 들에게 shifht를 해준다.
        AbstractConnection message;
        for (int i = 0; i < messageList.size(); i++) {
            message = messageList.get(i);
            if (message.getY() >= behaviorNode.getY() + behaviorNode.getHeight()
                && message.getY() < behaviorNode.getY() + behaviorNode.getHeight() + addValue) {
                message.setY(message.getY() + addValue);
                if (!RelationType.REPLY_MESSAGE.equals(message.getRelationType())) {
                    setOtherBehaviorNodeLayout(diagram, messageList, addValue, margin, message);
                }
                break;
            }
        }

        behaviorNode.setHeight(behaviorNode.getHeight() + addValue);

        NotationNode parent = (NotationNode) behaviorNode.getParent();
        if (!(parent instanceof Line)) {
            addBehaviorHeight(diagram, parent, messageList, addValue, margin);
        }
    }

    /**
     * setOtherBehaviorNodeLayout
     * 
     * @param diagram
     * @param messageList
     * @param addValue
     * @param margin
     * @param message
     *            void
     */
    private static void setOtherBehaviorNodeLayout(Diagram diagram, List<AbstractConnection> messageList, int addValue,
                                                   int margin, AbstractConnection message) {
        NotationNode otherBehaviorNode;
        NotationNode parentBehaviorNode;
        otherBehaviorNode = (NotationNode) message.getTarget();
        otherBehaviorNode.setY(otherBehaviorNode.getY() + addValue);
        if (!(otherBehaviorNode.getParent() instanceof Line)) {
            parentBehaviorNode = (NotationNode) otherBehaviorNode.getParent();
            if (parentBehaviorNode.getY() < message.getY()) {
                parentBehaviorNode.setHeight(parentBehaviorNode.getHeight() + addValue);
            }
        }
        if (!(message.getSource() instanceof Line)) {
            parentBehaviorNode = (NotationNode) message.getSource();
            if (parentBehaviorNode.getY() < message.getY()) {
                parentBehaviorNode.setHeight(parentBehaviorNode.getHeight() + addValue);
            }
        }
        setMessageLayout(diagram, message, messageList, addValue, margin);
    }

    /**
     * Behavior의 높이를 늘린다.
     * 
     * @param targetNode
     *            void
     */
    public static void addBehaviorHeight(NotationNode behaviorNode, int addValue, int margin) {

        if (getBehaviorLowYValueByChildren(behaviorNode) > behaviorNode.getY() + behaviorNode.getHeight() + addValue) {
            return;
        }
        if (getMessageLowYValueByChildren(behaviorNode) > behaviorNode.getY() + behaviorNode.getHeight() + addValue) {
            return;
        }

        behaviorNode.setHeight(behaviorNode.getHeight() + addValue + margin);

        NotationNode parent = (NotationNode) behaviorNode.getParent();
        if (!(parent instanceof Line)) {
            addBehaviorHeight(parent, addValue, margin);
        }

    }

    /**
     * 해당 메시지를 다이어그램에서 지운다.
     * 
     * @param message
     * @param diagram
     *            void
     */
    public static void deleteMessage(AbstractConnection message, Diagram diagram) {

        if (!RelationType.REPLY_MESSAGE.equals(message.getRelationType())) {
            NotationNode behaviorNode = (NotationNode) message.getTarget();
            if (behaviorNode != null) {
               
                AbstractConnection childMessage;
                for (int i = 0; i < behaviorNode.getOutgoingConnectionList().size(); i++) {
                    childMessage = behaviorNode.getOutgoingConnectionList().get(i);
                    if (RelationType.REPLY_MESSAGE.equals(childMessage.getRelationType())) {
                        deleteMessage(childMessage, diagram);
                    }
                }

                LifeLineNode lifeLineNode = SequenceUtil.getLifeLineNode(behaviorNode);

                for (NotationNode behaviorNotationNode : lifeLineNode.getBehaviorList()) {
                    behaviorNotationNode.setParent(lifeLineNode.getLine());
                }

                lifeLineNode.getBehaviorList().remove(behaviorNode);
            }
        }

        DiagramUtil.detachSourceOfConnection(message);
        DiagramUtil.detachTargetOfConnection(message);
        SequenceUtil.deleteUmlMessage(message);
        diagram.getConnectionList().remove(message);
        UMLManager.deleteElement(message);
    }

    /**
     * 해당 UML 메시지를 지운다.
     * 
     * @param message
     * @param diagram
     *            void
     */
    public static void deleteUmlMessage(AbstractConnection connection) {
        NamedElement client;
        NamedElement supplier;

        client = (NamedElement) (SequenceUtil.getLifeLineNode((AbstractNode) connection.getSource())).getUmlModel();
        supplier = (NamedElement) (SequenceUtil.getLifeLineNode((AbstractNode) connection.getTarget())).getUmlModel();

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
        
        if(sendMessageOccurrenceSpecificationForSend.eContainer() instanceof InteractionOperand) {
            deleteBehaviorExecutionSpecification(client,
                supplier,
                (InteractionOperand) sendMessageOccurrenceSpecificationForSend.eContainer(),
                receiveMessageOccurrenceSpecificationForSend);
        } else {
            deleteBehaviorExecutionSpecification(client,
                supplier,
                interaction,
                receiveMessageOccurrenceSpecificationForSend);
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
     * Interation에서 BehaviorExecutionSpecification을 찾아서 지워준다.
     *  
     * @param client
     * @param supplier
     * @param interaction
     * @param receiveMessageOccurrenceSpecificationForSend void
     */
    private static void deleteBehaviorExecutionSpecification(
                                                            NamedElement client,
                                                            NamedElement supplier,
                                                            Interaction interaction,
                                                            MessageOccurrenceSpecification receiveMessageOccurrenceSpecificationForSend) {
        InteractionFragment interactionFragment;
        BehaviorExecutionSpecification behaviorExecutionSpecification;
        ExecutionOccurrenceSpecification executionOccurrenceSpecification;
        ExecutionEvent executionEvent;
        for (int i = 0; i < interaction.getFragments().size(); i++) {
            interactionFragment = interaction.getFragments().get(i);
            if (interactionFragment instanceof BehaviorExecutionSpecification) {
                behaviorExecutionSpecification = (BehaviorExecutionSpecification) interactionFragment;
                if (behaviorExecutionSpecification.getStart() != null
                    && behaviorExecutionSpecification.getFinish() != null) {
                    if (behaviorExecutionSpecification.getStart().equals(receiveMessageOccurrenceSpecificationForSend)
                        && behaviorExecutionSpecification.getFinish()
                            .equals(receiveMessageOccurrenceSpecificationForSend)) {
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
        }
    }
    
    /**
     * InterationOperand에서 deleteBehaviorExecutionSpecification을 찾아서 지워준다.
     *  
     * @param client
     * @param supplier
     * @param interactionOperand
     * @param receiveMessageOccurrenceSpecificationForSend void
     */
    private static void deleteBehaviorExecutionSpecification(
                                                            NamedElement client,
                                                            NamedElement supplier,
                                                            InteractionOperand interactionOperand,
                                                            MessageOccurrenceSpecification receiveMessageOccurrenceSpecificationForSend) {
        InteractionFragment interactionFragment;
        BehaviorExecutionSpecification behaviorExecutionSpecification;
        ExecutionOccurrenceSpecification executionOccurrenceSpecification;
        ExecutionEvent executionEvent;
        for (int i = 0; i < interactionOperand.getFragments().size(); i++) {
            interactionFragment = interactionOperand.getFragments().get(i);
            if (interactionFragment instanceof BehaviorExecutionSpecification) {
                behaviorExecutionSpecification = (BehaviorExecutionSpecification) interactionFragment;
                if (behaviorExecutionSpecification.getStart() != null
                    && behaviorExecutionSpecification.getFinish() != null) {
                    if (behaviorExecutionSpecification.getStart().equals(receiveMessageOccurrenceSpecificationForSend)
                        && behaviorExecutionSpecification.getFinish()
                            .equals(receiveMessageOccurrenceSpecificationForSend)) {
                        executionOccurrenceSpecification = findexEcutionOccurrenceSpecification(interactionOperand,
                            behaviorExecutionSpecification);
                        if (executionOccurrenceSpecification != null) {
                            executionEvent = (ExecutionEvent) executionOccurrenceSpecification.getEvent();
                            interactionOperand.getNearestPackage().getPackagedElements().remove(executionEvent);
                            interactionOperand.getFragments().remove(executionOccurrenceSpecification);
                            UMLManager.deleteElement(executionEvent);
                            UMLManager.deleteElement(executionOccurrenceSpecification);
                        }
                        ((Lifeline) client).getCoveredBys().remove(behaviorExecutionSpecification);
                        ((Lifeline) supplier).getCoveredBys().remove(behaviorExecutionSpecification);
                        interactionOperand.getFragments().remove(behaviorExecutionSpecification);
                        UMLManager.deleteElement(behaviorExecutionSpecification);
                        break;
                    }
                }
            }
        }
    }

    /**
     * findexEcutionOccurrenceSpecification
     * 
     * @param interactionOperand
     * @param behaviorExecutionSpecification
     * @return ExecutionOccurrenceSpecification
     */
    private static ExecutionOccurrenceSpecification findexEcutionOccurrenceSpecification(
                                                                                         InteractionOperand interactionOperand,
                                                                                         BehaviorExecutionSpecification behaviorExecutionSpecification) {
        InteractionFragment interactionFragment;
        ExecutionOccurrenceSpecification executionOccurrenceSpecification;
        for (int i = 0; i < interactionOperand.getFragments().size(); i++) {
            interactionFragment = interactionOperand.getFragments().get(i);
            if (interactionFragment instanceof ExecutionOccurrenceSpecification) {
                executionOccurrenceSpecification = (ExecutionOccurrenceSpecification) interactionFragment;
                if (executionOccurrenceSpecification.getExecution().equals(behaviorExecutionSpecification)) {
                    return executionOccurrenceSpecification;
                }
            }
        }

        return null;
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

    /**
     * ascSortListByXValue
     * 
     * @param nodeList
     * @return List<AbstractNode>
     */
    public static List<LifeLineNode> ascSortedLifeLineNodeListByXValue(List<LifeLineNode> nodeList) {
        List<LifeLineNode> list = new ArrayList<LifeLineNode>();

        LifeLineNode node;
        boolean added;

        for (int i = 0; i < nodeList.size(); i++) {
            node = nodeList.get(i);
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
     * ascSortListByYValue
     * 
     * @param nodeList
     * @return List<AbstractNode>
     */
    public static List<AbstractConnection> ascSortedMessageListByYValue(EList<AbstractConnection> messageList) {
        List<AbstractConnection> list = new ArrayList<AbstractConnection>();

        List<AbstractConnection> connectoinList = getOnlyMessageList(messageList);
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
     * ascSortListByYValue
     * 
     * @param nodeList
     * @return List<AbstractNode>
     */
    public static List<AbstractConnection> ascSortedMessageListByYValue(List<AbstractConnection> messageList) {
        List<AbstractConnection> list = new ArrayList<AbstractConnection>();

        List<AbstractConnection> connectoinList = getOnlyMessageList(messageList);
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
    public static List<AbstractConnection> getOnlyMessageList(EList<AbstractConnection> messageList) {
        List<AbstractConnection> list = new ArrayList<AbstractConnection>();

        for (int i = 0; i < messageList.size(); i++) {
            if (!RelationType.ATTACHEMENT.equals(messageList.get(i).getRelationType())) {
                list.add(messageList.get(i));
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
    public static List<AbstractConnection> getOnlyMessageList(List<AbstractConnection> messageList) {
        List<AbstractConnection> list = new ArrayList<AbstractConnection>();

        for (int i = 0; i < messageList.size(); i++) {
            if( null == messageList.get(i) ) {
                continue;
            }
            if (!RelationType.ATTACHEMENT.equals(messageList.get(i).getRelationType())) {
                list.add(messageList.get(i));
            }
        }

        return list;
    }

    /**
     * getOnlyLifeLineNodeList
     * 
     * @param nodeList
     * @return List<LifeLineNode>
     */
    public static List<LifeLineNode> getOnlyLifeLineNodeList(List<AbstractNode> nodeList) {
        List<LifeLineNode> list = new ArrayList<LifeLineNode>();

        for (int i = 0; i < nodeList.size(); i++) {
            if (nodeList.get(i) instanceof LifeLineNode) {
                list.add((LifeLineNode) nodeList.get(i));
            }
        }

        return list;
    }
    
    /**
     * getCombindFragmentNodeList
     * 
     * @param nodeList
     * @return List<LifeLineNode>
     */
    public static List<NotationNode> getOnlyCombindFragmentNodeList(List<AbstractNode> nodeList) {
        List<NotationNode> list = new ArrayList<NotationNode>();

        for (int i = 0; i < nodeList.size(); i++) {
            if (nodeList.get(i) instanceof NotationNode && nodeList.get(i).getUmlModel() instanceof CombinedFragment ) {
                list.add((NotationNode) nodeList.get(i));
            }
        }

        return list;
    }

    /**
     * 자식이 있는 message 인지 리턴한다.
     * 
     * @param message
     * @return boolean
     */
    public static boolean hasChildren(AbstractConnection message) {

        if (!RelationType.REPLY_MESSAGE.equals(message.getRelationType())) {
            NotationNode behaviorNode = (NotationNode) message.getTarget();

            List<NotationNode> childrenBehaviorList = getChildrenBehavior(behaviorNode);
            if (childrenBehaviorList.size() > 0) {
                return true;
            }

            List<AbstractConnection> childrenMessageList = getChildrenMessage(message);
            if (childrenMessageList.size() > 0) {
                return true;
            }
            return false;

        } else {
            return false;
        }

    }

    /**
     * 다이어그램의 connection List를 강제적으로 이름을 다시 셋팅해서 MessageEditPart의
     * refreshLabel()를 호출 시켜서 Order를 다시 셋팅하도록한다.
     * 
     * @param diagram
     *            void
     */
    public static void refreshMessageOrder(Diagram diagram) {

        Interaction interaction = null;
        ;
        if (diagram.getParent() instanceof Interaction) {
            interaction = (Interaction) diagram.getParent();
        }
        refreshMessageViewModelOrder(diagram);
        refreshMessageUmlModelOrder(diagram, interaction);

        AbstractConnection abstractConnection;
        for (int i = 0; i < diagram.getConnectionList().size(); i++) {
            abstractConnection = diagram.getConnectionList().get(i);
            abstractConnection.setName(abstractConnection.getName());
        }
    }

    /**
     * Insert 되는 위치에 따라서 다이어그램 내의 다른 Message의 위치를 조정한다. void
     */
    public static void setInsertMessageLayout(Diagram diagram, AbstractConnection connection,
                                              List<AbstractConnection> messageList,
                                              List<AbstractConnection> childrenMessageList, int addValue, int margin) {

        AbstractConnection message;
        NotationNode messageBehaviorNode;

        for (int i = 0; i < messageList.size(); i++) {
            message = (AbstractConnection) messageList.get(i);
            messageBehaviorNode = (NotationNode) message.getTarget();
            if (connection.equals(message)) {
                setChildrenMessageLayout(diagram,
                    message,
                    messageBehaviorNode,
                    i,
                    messageList,
                    childrenMessageList,
                    addValue,
                    margin);
                break;
            }
        }
    }

    /**
     * setChildrenMessageLayout
     * 
     * @param message
     * @param messageBehaviorNode
     * @param i
     *            void
     */
    private static void setChildrenMessageLayout(Diagram diagram, AbstractConnection message,
                                                 NotationNode messageBehaviorNode, int i,
                                                 List<AbstractConnection> messageList,
                                                 List<AbstractConnection> childrenMessageList, int addValue, int margin) {
        AbstractConnection childMessage;
        NotationNode childBehaviorNode;
        SequenceUtil.checkLifeLinesHeight(diagram, message, messageBehaviorNode, i, messageList, margin);
        for (int k = i + 1; k < messageList.size(); k++) {
            childMessage = (AbstractConnection) messageList.get(k);
            if (childrenMessageList.contains(childMessage)) {
                childMessage.setY(childMessage.getY() + addValue);
                if (!(childMessage.getTarget() instanceof Line)
                    && !RelationType.REPLY_MESSAGE.equals(childMessage.getRelationType())) {
                    childBehaviorNode = (NotationNode) childMessage.getTarget();
                    childBehaviorNode.setY(childBehaviorNode.getY() + addValue);
                }
            }
        }
    }

    /**
     * Move 되는 위치에 따라서 다이어그램 내의 다른 Message의 위치를 조정한다. void
     */
    public static void setMessageLayout(Diagram diagram, AbstractConnection connection,
                                        List<AbstractConnection> messageList, int addValue, int margin) {

        AbstractConnection message;
        NotationNode messageBehaviorNode;

        for (int i = 0; i < messageList.size(); i++) {
            message = (AbstractConnection) messageList.get(i);
            messageBehaviorNode = (NotationNode) message.getTarget();
            if (connection.equals(message)) {
                setOtherMessageLayout(diagram, message, messageBehaviorNode, i, messageList, addValue, margin);
                break;
            }
        }
    }

    /**
     * setOtherMessageLayout
     * 
     * @param message
     * @param messageBehaviorNode
     * @param i
     *            void
     */
    private static void setOtherMessageLayout(Diagram diagram, AbstractConnection message,
                                              NotationNode messageBehaviorNode, int i,
                                              List<AbstractConnection> messageList, int addValue, int margin) {
        AbstractConnection otherMessage;
        NotationNode otherBehaviorNode;
        NotationNode parentBehaviorNode;
        SequenceUtil.checkLifeLinesHeight(diagram, message, messageBehaviorNode, i, messageList, margin);
        for (int k = i + 1; k < messageList.size(); k++) {
            otherMessage = (AbstractConnection) messageList.get(k);
            otherMessage.setY(otherMessage.getY() + addValue);
            if (!(otherMessage.getTarget() instanceof Line)
                && !RelationType.REPLY_MESSAGE.equals(otherMessage.getRelationType())) {
                otherBehaviorNode = (NotationNode) otherMessage.getTarget();
                otherBehaviorNode.setY(otherBehaviorNode.getY() + addValue);
                SequenceUtil.checkLifeLinesHeight(diagram, otherMessage, otherBehaviorNode, k, messageList, margin);
                shiftTargetAndSourceBehavor(diagram,
                    message,
                    otherMessage,
                    otherBehaviorNode,
                    k,
                    messageList,
                    addValue,
                    margin);
            } else {
                if (!(otherMessage.getSource() instanceof Line)) {
                    parentBehaviorNode = (NotationNode) otherMessage.getSource();
                    if (parentBehaviorNode.getY() + parentBehaviorNode.getHeight() < otherMessage.getY()) {
                        addValue = otherMessage.getY() - (parentBehaviorNode.getY() + parentBehaviorNode.getHeight());
                        SequenceUtil.addBehaviorHeight(parentBehaviorNode, addValue, margin);
                    }
                }
            }
        }
    }

    /**
     * shiftTargetAndSourceBehavor
     * 
     * @param otherMessage
     * @param otherBehaviorNode
     * @param k
     *            void
     */
    private static void shiftTargetAndSourceBehavor(Diagram diagram, AbstractConnection message,
                                                    AbstractConnection otherMessage, NotationNode otherBehaviorNode,
                                                    int k, List<AbstractConnection> messageList, int addValue,
                                                    int margin) {
        NotationNode parentBehaviorNode;
        if (!(otherBehaviorNode.getParent() instanceof Line)) {
            parentBehaviorNode = (NotationNode) otherBehaviorNode.getParent();
            if (parentBehaviorNode.getY() < message.getY()) {
                SequenceUtil.addBehaviorHeight(diagram, parentBehaviorNode, messageList, addValue, margin);
                SequenceUtil.checkLifeLinesHeight(diagram, otherMessage, parentBehaviorNode, k, messageList, margin);
            }
        }
        if (!(otherMessage.getSource() instanceof Line)) {
            parentBehaviorNode = (NotationNode) otherMessage.getSource();
            if (parentBehaviorNode.getY() < message.getY()) {
                SequenceUtil.addBehaviorHeight(diagram, parentBehaviorNode, messageList, addValue, margin);
                SequenceUtil.checkLifeLinesHeight(diagram, otherMessage, parentBehaviorNode, k, messageList, margin);
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
                                            int k, List<AbstractConnection> messageList, int margin) {
        NotationNode lifeLineNode;
        int behaviorHeight;
        if (k == messageList.size() - 1) {
            lifeLineNode = SequenceUtil.getLifeLineNode(behaviorNode);
            behaviorHeight = 0;
            if (behaviorNode != null) {
                behaviorHeight = behaviorNode.getHeight();
            }
            if (lifeLineNode.getY() + lifeLineNode.getHeight() < message.getY() + behaviorHeight) {
                int shiftHeight = (message.getY() + behaviorHeight) - (lifeLineNode.getY() + lifeLineNode.getHeight())
                    + margin;
                // 모든 라이프라인의 높이를 생성된 Behavior의 높이만클 늘린다.
                List<LifeLineNode> nodeList = SequenceUtil.getOnlyLifeLineNodeList(diagram.getNodeList());
                for (AbstractNode node : nodeList) {
                    node.setHeight(node.getHeight() + shiftHeight);
                }
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
        lifeLineNode = SequenceUtil.getLifeLineNode(behaviorNode);
        behaviorHeight = 0;
        if (behaviorNode != null) {
            behaviorHeight = behaviorNode.getHeight();
        }
        if (lifeLineNode.getY() + lifeLineNode.getHeight() < message.getY() + behaviorHeight) {
            int shiftHeight = (message.getY() + behaviorHeight) - (lifeLineNode.getY() + lifeLineNode.getHeight())
                + margin;
            // 모든 라이프라인의 높이를 생성된 Behavior의 높이만클 늘린다.
            List<LifeLineNode> nodeList = SequenceUtil.getOnlyLifeLineNodeList(diagram.getNodeList());
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
    public static void checkLifeLinesHeight(Diagram diagram, AbstractConnection message, int margin) {
        NotationNode lifeLineNode;
        int behaviorHeight;
        lifeLineNode = SequenceUtil.getLifeLineNode((AbstractNode) message.getTarget());
        behaviorHeight = 0;
        if (lifeLineNode.getY() + lifeLineNode.getHeight() < message.getY() + behaviorHeight) {
            int shiftHeight = (message.getY() + behaviorHeight) - (lifeLineNode.getY() + lifeLineNode.getHeight())
                + margin;
            // 모든 라이프라인의 높이를 생성된 Behavior의 높이만클 늘린다.
            List<LifeLineNode> nodeList = SequenceUtil.getOnlyLifeLineNodeList(diagram.getNodeList());
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
    public static void checkLifeLinesHeight(Diagram diagram, NotationNode behaviorNode, int margin) {
        NotationNode lifeLineNode;
        int behaviorHeight;
        lifeLineNode = SequenceUtil.getLifeLineNode(behaviorNode);
        behaviorHeight = 0;
        if (behaviorNode != null) {
            behaviorHeight = behaviorNode.getHeight();
        }
        if (lifeLineNode.getY() + lifeLineNode.getHeight() < behaviorNode.getY() + behaviorHeight) {
            int shiftHeight = (behaviorNode.getY() + behaviorHeight) - (lifeLineNode.getY() + lifeLineNode.getHeight())
                + margin;
            // 모든 라이프라인의 높이를 생성된 Behavior의 높이만클 늘린다.
            List<LifeLineNode> nodeList = SequenceUtil.getOnlyLifeLineNodeList(diagram.getNodeList());
            for (AbstractNode node : nodeList) {
                node.setHeight(node.getHeight() + shiftHeight);
            }
        }
    }
    
    /**
     * shiftLifeLinesHeight
     * 
     * @param diagram
     * @param shiftHeight
     *            void
     */
    public static void shiftLifeLinesHeight(Diagram diagram, int shiftHeight) {
        
        // 모든 라이프라인의 높이를 shiftHeight 높이만클 늘린다.
        List<LifeLineNode> nodeList = SequenceUtil.getOnlyLifeLineNodeList(diagram.getNodeList());
        for (AbstractNode node : nodeList) {
            node.setHeight(node.getHeight() + shiftHeight);
        }
    }
   
    /**
     * sifhtMessageList
     * 
     * @param diagram
     * 
     * @param insertSifhtValue
     * @param childrenMessageList
     * @param margin
     * @param childrenMessage
     *            void
     */
    public static void sifhtMessageList(Diagram diagram, int insertSifhtValue, List<AbstractConnection> messageList,
                                        List<AbstractConnection> childrenMessageList, int margin) {
        AbstractConnection message;
        NotationNode parentBehaviorNode;
        int addValue;
        for (int i = 0; i < messageList.size(); i++) {
            message = messageList.get(i);
            if (!childrenMessageList.contains(message)) {
                message.setY(message.getY() + insertSifhtValue);
                if (!(message.getTarget() instanceof Line)
                    && !RelationType.REPLY_MESSAGE.equals(message.getRelationType())) {
                    message.getTarget().setY(message.getTarget().getY() + insertSifhtValue);
                } else if (!(message.getTarget() instanceof Line)
                    && RelationType.REPLY_MESSAGE.equals(message.getRelationType())) {
                    parentBehaviorNode = (NotationNode) message.getTarget();
                    if (parentBehaviorNode.getY() + parentBehaviorNode.getHeight() < message.getY()) {
                        addValue = message.getY() - (parentBehaviorNode.getY() + parentBehaviorNode.getHeight());
                        SequenceUtil.addBehaviorHeight(parentBehaviorNode, addValue, margin);
                    }
                }
                if (!(message.getSource() instanceof Line)) {
                    parentBehaviorNode = (NotationNode) message.getSource();
                    if (parentBehaviorNode.getY() + parentBehaviorNode.getHeight() < message.getY()) {
                        addValue = message.getY() - (parentBehaviorNode.getY() + parentBehaviorNode.getHeight());
                        SequenceUtil.addBehaviorHeight(parentBehaviorNode, addValue, margin);
                    }
                }
                checkLifeLinesHeight(diagram, message, (NotationNode) message.getTarget(), margin);
            }
        }
    }

    /**
     * 새로 생성된 Operation을 Type에 넣어준다.
     */
    public static Type setOperationToType(Type type, Operation operation) {

        if (null == operation.eContainer()) {
            if (type instanceof org.eclipse.uml2.uml.Class) {
                ((Class) type).getOwnedOperations().add(operation);
            } else if (type instanceof Interface) {
                ((Interface) type).getOwnedOperations().add(operation);
            }
        }
        return type;
    }

    /**
     * 선택한 Operation을 Message에 지정해준다.
     */
    public static Message setOperationToMessage(Operation operation, Message message) {

        SendOperationEvent soe = (SendOperationEvent) ((MessageOccurrenceSpecification) message.getSendEvent()).getEvent();
        ReceiveOperationEvent roe = (ReceiveOperationEvent) ((MessageOccurrenceSpecification) message.getReceiveEvent()).getEvent();
        if(soe == null) {
            Interaction interaction = (Interaction) message.eContainer();
            soe = UMLFactory.eINSTANCE.createSendOperationEvent();
            String uniqueName = UMLManager.getPackagedUniqueName(interaction.getNearestPackage(),
                UMLMessage.getMessage(UMLMessage.UML_SENDOPERATIONEVENT));
            soe.setName(uniqueName);
            interaction.getNearestPackage().getPackagedElements().add(soe);
            ((MessageOccurrenceSpecification) message.getSendEvent()).setEvent(soe);
        }
        if(roe == null) {
            Interaction interaction = (Interaction) message.eContainer();
            roe = UMLFactory.eINSTANCE.createReceiveOperationEvent();
            String uniqueName = UMLManager.getPackagedUniqueName(interaction.getNearestPackage(),
                UMLMessage.getMessage(UMLMessage.UML_RECEIVEOPERATIONEVENT));
            roe.setName(uniqueName);
            interaction.getNearestPackage().getPackagedElements().add(roe);
            ((MessageOccurrenceSpecification) message.getReceiveEvent()).setEvent(roe);
        }
        soe.setOperation(operation);
        roe.setOperation(operation);
        if (operation == null) {
            message.setName(UMLMessage.getMessage(UMLMessage.UML_MESSAGE_UNDEFINEDNAME));
        } else {
            message.setName(operation.getName());
        }
        return message;
    }

    /**
     * Lifeline의 Type에 새 Operation을 생성. (생성된 operation을 Type에 붙이는 것은 transaction
     * 때문에 execute()에서 처리) void
     * 
     * @param type
     */
    public static Operation createOperation(Type pType) {

        Operation operation = null;
        CreateOperationDialog dialog = new CreateOperationDialog(RealizationPlugin.getShell(), pType);
        if (dialog.open() == Window.OK) {

            String operationName = dialog.getOperationName();
            Type type = dialog.getSelectedType();

            operation = UMLHelper.createOperation();
            if (operationName.equals(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING)) {
                NamedElement namedElement = (NamedElement) operation;
                namedElement.setName(UMLManager.getPackagedUniqueName((Namespace) type, namedElement.getName()));
            } else {
                operation.setName(operationName);
            }
        }
        return operation;
    }

    /**
     * Lifeline의 Type에 새 클래스 생성. (생성된 클래스를 Type에 붙이는 것은 transaction 때문에
     * execute()에서 처리) void
     * 
     * @param parentNodeModel
     */
    public static org.eclipse.uml2.uml.Class createClass(AbstractNode parentNodeModel) {

        PackageableElement parentElement = UMLManager.getParentPackage(parentNodeModel);
        org.eclipse.uml2.uml.Class umlClass = null;
        CreateClassDialog dialog = new CreateClassDialog(RealizationPlugin.getShell());
        if (dialog.open() == Window.OK) {

            String className = dialog.getClassName();

            umlClass = UMLHelper.createClass();
            if (className.equals(UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING)) {
                umlClass.setName(UMLManager.getPackagedUniqueName((Namespace) parentElement, umlClass.getName()));
            } else {
                umlClass.setName(className);
            }
        }
        return umlClass;
    }

    /**
     * operation의 visibility를 UML 표기법에 따라 String으로 리턴한다.
     * 
     * @param operation
     * @return String
     */
    public static String getOperationVisibility(Operation operation) {

        String visibility = UICoreConstant.PROJECT_CONSTANTS__EMPTY_STRING;
        VisibilityKind kind = operation.getVisibility();
        if (VisibilityKind.PUBLIC_LITERAL.equals(kind)) {
            visibility = "+";
        } else if (VisibilityKind.PROTECTED_LITERAL.equals(kind)) {
            visibility = "#";
        } else if (VisibilityKind.PRIVATE_LITERAL.equals(kind)) {
            visibility = "-";
        } else if (VisibilityKind.PACKAGE_LITERAL.equals(kind)) {
            visibility = "~";
        }
        return visibility;
    }

    /**
     * 시퀀스 다이어그램에서 동기 메시지의 Reply 옵션을 체크한다 true면 동기 메시지에서 Reply를 생성 false이면 생성하지
     * 않는다.
     * 
     * @param diagram
     *            void
     */
    public static boolean checkOptionalReplyMessage() {
        boolean option = true;
        if ("true".equals(PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_SEQUENCEDIAGRAM_VISIBILITY_REPLY))) {
            option = false;
        }
        return option;
    }

    /**
     * checkLifelineVisibility
     *  
     * @return boolean
     */
    public static boolean checkLifelineVisibility() {
        boolean option = true;
        if ("true".equals(PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_SEQUENCEDIAGRAM_VISIBILITY_LIFELINENAME))) {
            option = false;
        }
        return option;
    }

    /**
     * showMessageType
     *  
     * @return boolean
     */
    public static boolean showMessageType() {
    	return "true".equals(PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(
    			ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_SEQUENCEDIAGRAM_VISIBILITY_MESSAGE_TYPE));
    }

    /**
     * showMessageParameter
     *  
     * @return boolean
     */
    public static boolean showMessageParameter() {
        return "true".equals(PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(
        		ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_SEQUENCEDIAGRAM_VISIBILITY_MESSAGE_PARAMETER));
    }
    
    /**
     * checkReplyMessageVisibility
     *  
     * @return boolean
     */
    public static boolean checkReplyMessageVisibility() {
        boolean option = true;
        if ("true".equals(PreferenceUtil.INSTANCE.getValueOfStringFieldEditor(ManagerConstant.PREFERENCE_CONSTANT_KEY__NEXCORE_TOOL_UML_SEQUENCEDIAGRAM_VISIBILITY_REPLY_MESSAGENAME))) {
            option = false;
        }
        return option;
    }

    /**
     * refreshMessageViewModelOrder
     *  
     * @param dgm void
     */
    public static void refreshMessageViewModelOrder(Diagram dgm) {
        List<AbstractConnection> connectioList = SequenceUtil.ascSortedMessageListByYValue(dgm.getConnectionList());
        for (AbstractConnection sortedConnection : connectioList) {
            int connectionPosition = 0;
            if (connectioList.contains(sortedConnection)) {
                connectionPosition = connectioList.indexOf(sortedConnection);
            }
            dgm.getConnectionList().move(connectionPosition, sortedConnection);
        }
    }

    /**
     * refreshMessageUmlModelOrder
     *  
     * @param dgm
     * @param interaction void
     */
    public static void refreshMessageUmlModelOrder(Diagram dgm, Interaction interaction) {
        if (interaction == null) {
            return;
        }
        for (AbstractConnection sortedConnection : dgm.getConnectionList()) {
            Message umlModel = null;
            int connectionPosition = 0;
            if (dgm.getConnectionList().contains(sortedConnection)) {
                connectionPosition = dgm.getConnectionList().indexOf(sortedConnection);
            }
            if (sortedConnection.getUmlModel() instanceof Message) {
                umlModel = (Message) sortedConnection.getUmlModel();
            }
            if (umlModel != null) {
                interaction.getMessages().move(connectionPosition, umlModel);
            }
        }
    }
    
    /**
     * combinedFragment 해당 라이프라인의 coveredBys의 순서를 정렬한다.
     * @param addedLifelineList 
     *  
     * @param interaction
     * @param connection
     * @param message void
     */
    public static void refreshUmlLifelineCoveredBysOrder(List<Lifeline> addedLifelineList, NotationNode combinedFragmentNode, CombinedFragment combinedFragment) {
                    
        for(Lifeline lifeline : addedLifelineList) {
            EList<InteractionFragment> coveredBys = lifeline.getCoveredBys();
            InteractionFragment upperFragment = null;
            //combinedFragment가 생성된 위치의 상위 fragment를 찾는다
            upperFragment = getUpperFragmentOfCombinedFragmentNode(coveredBys, combinedFragmentNode);
            if (upperFragment != null) {
                int indexOf = coveredBys.indexOf(upperFragment);
                moveCoveredBys(coveredBys, indexOf+1, combinedFragment);
            } else {
                moveCoveredBys(coveredBys, 0, combinedFragment);
            }     
        }
        
    }

    /**
     * 메시지의 순서가 바뀌면 해당 라이프라인의 coveredBys의 순서를 정렬한다.
     *  
     * @param interaction
     * @param connection
     * @param message void
     */
    public static void refreshUmlLifelineCoveredBysOrder(AbstractConnection connection,
                                                         Message message) {
                    
        refreshUmlSendLifelineCoveredBysOrder(connection, message);
        refreshUmlReceiveLifelineCoveredBysOrder(connection, message);      
    }

    /**
     * 메시지의 소스 라이프라인의 coveredBys의 순서를 정렬한다.
     * 
     * @param connection
     * @param connectionPosition
     * @param umlModel
     *            void
     */
    private static void refreshUmlSendLifelineCoveredBysOrder(AbstractConnection connection, Message message) {
        // sendEvent의 Lifelfe의 coveredBy 순서 변경
        MessageOccurrenceSpecificationImpl sendEvent = (MessageOccurrenceSpecificationImpl) message.getSendEvent();
        if (null == sendEvent) return;
        
        EList<Lifeline> covereds = sendEvent.getCovereds();
        if (covereds.size() == 1) {
            Lifeline lifeline = covereds.get(0);
            EList<InteractionFragment> coveredBys = lifeline.getCoveredBys();
            InteractionFragment upperFragment = null;
            //메시지가 이동된 위치의 상위 fragment를 찾는다
            upperFragment = getUpperFragmentOfConnection(coveredBys, connection, (NotationNode) connection.getSource());
            if (upperFragment != null) {
                int indexOf = coveredBys.indexOf(upperFragment);
                moveCoveredBys(coveredBys, indexOf+1, sendEvent);
            } else {
                moveCoveredBys(coveredBys, 0, sendEvent);
            }     
        }
    }

    /**
     * 메시지의 타겟 라이프라인의 coveredBys의 순서를 정렬한다.
     * 
     * @param connection
     * @param connectionPosition
     * @param umlModel
     *            void
     */
    private static void refreshUmlReceiveLifelineCoveredBysOrder(AbstractConnection connection, Message message) {
        // ReceiveEvent의 Lifelfe의 coveredBy 순서 변경
        MessageOccurrenceSpecificationImpl receiveEvent = (MessageOccurrenceSpecificationImpl) message.getReceiveEvent();
        if (null == receiveEvent) return;
        
        EList<Lifeline> covereds = receiveEvent.getCovereds();
        if (covereds.size() == 1) {
            Lifeline lifeline = covereds.get(0);
            EList<InteractionFragment> coveredBys = lifeline.getCoveredBys();
            InteractionFragment upperFragment = null;
            if (connection.getSource().equals(connection.getTarget().getParent())) {
                upperFragment = (InteractionFragment) message.getSendEvent();
                int indexOf = coveredBys.indexOf(upperFragment);
                moveCoveredBys(coveredBys, indexOf+1, receiveEvent);
                if (!connection.getRelationType().equals(RelationType.REPLY_MESSAGE)) {
                  moveCoveredBys(coveredBys, indexOf+2, (InteractionFragment) connection.getTarget()
                      .getUmlModel());
                }
            } else {  
                upperFragment = getUpperFragmentOfConnection(coveredBys, connection, (NotationNode) connection.getTarget());
                if (upperFragment != null) {
                    int indexOf = coveredBys.indexOf(upperFragment);
                    moveCoveredBys(coveredBys, indexOf+1, receiveEvent);
                    if (!connection.getRelationType().equals(RelationType.REPLY_MESSAGE)) {
                      moveCoveredBys(coveredBys, indexOf+2, (InteractionFragment) connection.getTarget()
                          .getUmlModel());
                    }
                } else {
                    moveCoveredBys(coveredBys, 0, receiveEvent);
                    if (!connection.getRelationType().equals(RelationType.REPLY_MESSAGE)) {
                        moveCoveredBys(coveredBys, 1, (InteractionFragment) connection.getTarget()
                            .getUmlModel());
                    }
                }     
            }
        }
    }
    
    /**
     * combinedFragment가 이동하려는 위치의 상위 fragment를 찾아서 리턴한다.
     *  
     * @param coveredBys
     * @param connection
     * @param node
     * @return InteractionFragment
     */
    private static InteractionFragment getUpperFragmentOfCombinedFragmentNode(EList<InteractionFragment> coveredBys, NotationNode combinedFragmentNode) {
        
        Diagram diagram = getDiagram(combinedFragmentNode);
        
        List<NotationNode> combinedFragmentList = getSameLevelCombinedFragmentNodeListByCoveredBys(diagram, coveredBys, combinedFragmentNode);
        List<AbstractConnection> connectionList = getMessagaConnectionListByCoveredBys(diagram, coveredBys);
        List<AbstractView> viewList = new ArrayList<AbstractView>();
        
        viewList.addAll(connectionList);
        if(combinedFragmentList.size() > 0) {
            viewList.addAll(combinedFragmentList);
        }
        
        AbstractView upperViewNode = null;
        int gap = -1;
        int fragmentY = combinedFragmentNode.getY();
        if(combinedFragmentNode.getParent() instanceof ContainerNode) {
            ContainerNode parentNode = (ContainerNode) combinedFragmentNode.getParent();
            fragmentY = translateToAbsoluteYByParentNode(fragmentY, parentNode, (NotationNode) parentNode.getParent());
        }
        for (AbstractView viewNode : viewList) {
            int viewY = viewNode.getY();
            if(viewNode instanceof NotationNode && viewNode.getUmlModel() instanceof CombinedFragment) {
                NotationNode coveredFragmentNode = (NotationNode) viewNode;
                if(coveredFragmentNode.getParent() instanceof ContainerNode) {
                    ContainerNode parentNode = (ContainerNode) coveredFragmentNode.getParent();
                    viewY = translateToAbsoluteYByParentNode(viewY, parentNode, (NotationNode) parentNode.getParent());
                }
            }
            if (!combinedFragmentNode.equals(viewNode) && fragmentY > viewY) {
                if (gap < 0) {
                    upperViewNode = viewNode;
                    gap = fragmentY - viewY;
                } else {
                    if (gap > fragmentY - viewY) {
                        upperViewNode = viewNode;
                        gap = fragmentY - viewY;
                    }
                }

            }
        }
        
        if(upperViewNode == null) {
            return null;
        }
        
        if(upperViewNode instanceof AbstractConnection) {
            AbstractConnection abstractConnection = (AbstractConnection) upperViewNode;
            Message upperMessage = (Message) abstractConnection.getUmlModel();
            MessageOccurrenceSpecificationImpl messageEvent = (MessageOccurrenceSpecificationImpl) upperMessage.getSendEvent();
            if(coveredBys.contains(messageEvent)) {
                return messageEvent;
            } else {
                if(RelationType.REPLY_MESSAGE.equals(abstractConnection.getRelationType())) {
                    messageEvent = (MessageOccurrenceSpecificationImpl) upperMessage.getReceiveEvent();
                    if(coveredBys.contains(messageEvent)) {
                        return messageEvent;
                    }
                } else {
                    if(coveredBys.contains(abstractConnection.getTarget().getUmlModel())) {
                        return (InteractionFragment) abstractConnection.getTarget().getUmlModel();
                    }
                }
                
            }
        } else if(upperViewNode instanceof NotationNode) {
            CombinedFragment combinedFragment = (CombinedFragment) upperViewNode.getUmlModel();
            return combinedFragment;
        }
        
        return null;
  
        
    }
    
   

    /**
     * connection이 이동하려는 위치의 상위 fragment를 찾아서 리턴한다.
     *  
     * @param coveredBys
     * @param connection
     * @param node
     * @return InteractionFragment
     */
    private static InteractionFragment getUpperFragmentOfConnection(EList<InteractionFragment> coveredBys, AbstractConnection connection, NotationNode node) {
        
        LifeLineNode lifelineNode = null;
        Line line = null;

        List<AbstractConnection> connectionList = new ArrayList<AbstractConnection>();
        if (node instanceof Line) {
            line = (Line) node;
        } else if (NodeType.LIFE_LINE_BEHAVIOR.equals(node.getNodeType())) {
            line = (Line) node.getParent();
        } else {
            line = ((LifeLineNode) node).getLine();
        }
        connectionList.addAll(line.getIncomingConnectionList());
        connectionList.addAll(line.getOutgoingConnectionList());

        lifelineNode = (LifeLineNode) line.getParent();
        EList<NotationNode> behaviorList = lifelineNode.getBehaviorList();
        for (NotationNode behaviorNode : behaviorList) {
            connectionList.addAll(behaviorNode.getIncomingConnectionList());
            connectionList.addAll(behaviorNode.getOutgoingConnectionList());
        }
        
        //coveredBys combinedFragment가 있는지 검사(성능을 위해서...)
        if(coveredBysHasCombinedFragment(coveredBys)) {
            Diagram diagram = (Diagram) connection.eContainer();
            return getUpperFragmentOfAllFragments(diagram, coveredBys, connection, connectionList);
        } else {
            return getUpperFragmentOfOnlyMessageFragments(coveredBys, connection, connectionList);
        }
        
    }

    /**
     * coveredBys에 CombinedFragment가 포함되었는지 검사한다.
     *  
     * @param coveredBys
     * @return boolean
     */
    public static boolean coveredBysHasCombinedFragment(EList<InteractionFragment> coveredBys) {
        for(InteractionFragment fragment : coveredBys) {
            if(fragment instanceof CombinedFragment) {
                return true;
            }
        }
        return false;
    }

    /**
     * coveredBys에 CombinedFragment들을 포함하고 있을때 메시지의 상위 fragment를 찾아서 리턴한다.
     * 
     * @param diagram 
     * @param coveredBys
     * @param connection
     * @param connectionList
     * @return InteractionFragment
     */
    private static InteractionFragment getUpperFragmentOfAllFragments(Diagram diagram, EList<InteractionFragment> coveredBys,
                                                                            AbstractConnection connection,
                                                                            List<AbstractConnection> connectionList) {

        
        List<NotationNode> combinedFragmentList = getSameLevelCombinedFragmentNodeListByCoveredBys(diagram, coveredBys, connection);
        
        List<AbstractView> viewList = new ArrayList<AbstractView>();
        
        viewList.addAll(connectionList);
        if(combinedFragmentList.size() > 0) {
            viewList.addAll(combinedFragmentList);
        }
        
        AbstractView upperViewNode = null;
        int gap = -1;
        for (AbstractView viewNode : viewList) {
            int viewY = viewNode.getY();
            if(viewNode instanceof NotationNode && viewNode.getUmlModel() instanceof CombinedFragment) {
                NotationNode combinedFragmentNode = (NotationNode) viewNode;
                if(combinedFragmentNode.getParent() instanceof ContainerNode) {
                    ContainerNode parentNode = (ContainerNode) combinedFragmentNode.getParent();
                    viewY = translateToAbsoluteYByParentNode(viewY, parentNode, (NotationNode) parentNode.getParent());
                }
            }
            if (!connection.equals(viewNode) && connection.getY() > viewY) {
                if (gap < 0) {
                    upperViewNode = viewNode;
                    gap = connection.getY() - viewY;
                } else {
                    if (gap > connection.getY() - viewY) {
                        upperViewNode = viewNode;
                        gap = connection.getY() - viewY;
                    }
                }

            }
        }
        
        if(upperViewNode == null) {
            return null;
        }
        
        if(upperViewNode instanceof AbstractConnection) {
            AbstractConnection abstractConnection = (AbstractConnection) upperViewNode;
            Message upperMessage = (Message) abstractConnection.getUmlModel();
            MessageOccurrenceSpecificationImpl messageEvent = (MessageOccurrenceSpecificationImpl) upperMessage.getSendEvent();
            if(coveredBys.contains(messageEvent)) {
                return messageEvent;
            } else {
                if(RelationType.REPLY_MESSAGE.equals(abstractConnection.getRelationType())) {
                    messageEvent = (MessageOccurrenceSpecificationImpl) upperMessage.getReceiveEvent();
                    if(coveredBys.contains(messageEvent)) {
                        return messageEvent;
                    }
                } else {
                    if(coveredBys.contains(abstractConnection.getTarget().getUmlModel())) {
                        return (InteractionFragment) abstractConnection.getTarget().getUmlModel();
                    }
                }
                
            }
        } else if(upperViewNode instanceof NotationNode) {
            CombinedFragment combinedFragment = (CombinedFragment) upperViewNode.getUmlModel();
            return combinedFragment;
        }
        
        return null;
    }
    
    /**
     * coveredBys에서 connection과 같은 레벨의 combinedFragment 리스트를 찾아서 리턴한다.
     *  
     * @param diagram
     * @param coveredBys
     * @param connection 
     * @return List<NotationNode>
     */
    private static List<NotationNode> getSameLevelCombinedFragmentNodeListByCoveredBys(Diagram diagram,
                                                                              EList<InteractionFragment> coveredBys, AbstractConnection connection) {
        List<NotationNode> list = new ArrayList<NotationNode>();
                
        if(!isIncludedOperand(connection)) {
            NotationNode combinedFragmentNode;
            for(InteractionFragment fragment : coveredBys) {
                if(fragment instanceof CombinedFragment) {
                    combinedFragmentNode = getCombinedFragmentNodeInDiagram(diagram, (CombinedFragment) fragment);
                    if(combinedFragmentNode != null) {
                        list.add(combinedFragmentNode);
                    }
                }
            }
        } else {
            Message message = (Message) connection.getUmlModel();
            MessageOccurrenceSpecification sendEvent = (MessageOccurrenceSpecification) message.getSendEvent();
            InteractionOperand operand = (InteractionOperand) sendEvent.eContainer();
            ContainerNode interactionOperandNode = getInteractionOperandNode(diagram, operand);
            if( interactionOperandNode != null ) {
                list.add((NotationNode) interactionOperandNode.getParent());
                List<NotationNode> combindFragmentNodeList = getOnlyCombindFragmentNodeList(interactionOperandNode.getNodeList());
                if(combindFragmentNodeList.size() > 0) {
                    for(NotationNode node : combindFragmentNodeList) {
                        CombinedFragment combinedFragment = (CombinedFragment) node.getUmlModel();
                        if(coveredBys.contains(combinedFragment)) {
                            list.add(node);
                        }
                    }
                }
            }
        }
        
        return list;
    }
    
    /**
     * coveredBys에 포함된 ConnectionList를 찾아서 리턴한다.
     *  
     * @param diagram
     * @param coveredBys
     * @return List<AbstractConnection>
     */
    private static List<AbstractConnection> getMessagaConnectionListByCoveredBys(Diagram diagram, EList<InteractionFragment> coveredBys) {
        List<AbstractConnection> list = new ArrayList<AbstractConnection>();
        HashMap<Message, AbstractConnection> messageMap = new HashMap<Message, AbstractConnection>();
        List<AbstractConnection> messageList = getOnlyMessageList(diagram.getConnectionList());
        for(AbstractConnection connection : messageList) {
            messageMap.put((Message) connection.getUmlModel(), connection);
        }
   
        for(InteractionFragment fragment :coveredBys) {
            if(fragment instanceof MessageOccurrenceSpecification) {
                Message message = ((MessageOccurrenceSpecification) fragment).getMessage();
                AbstractConnection abstractConnection = messageMap.get(message);
                if(abstractConnection != null) {
                    list.add(abstractConnection);
                }
            }
        }
        
        
        return list;
    }
    
    /**
     * coveredBys에서 combinedFragmentNode과 같은 레벨의 combinedFragment 리스트를 찾아서 리턴한다.
     *  
     * @param diagram
     * @param coveredBys
     * @param connection 
     * @return List<NotationNode>
     */
    private static List<NotationNode> getSameLevelCombinedFragmentNodeListByCoveredBys(Diagram diagram,
                                                                              EList<InteractionFragment> coveredBys, NotationNode combinedFragmentNode) {
        List<NotationNode> list = new ArrayList<NotationNode>();
                
        if(combinedFragmentNode.getParent() instanceof Diagram) {
            NotationNode coveredFragmentNode;
            for(InteractionFragment fragment : coveredBys) {
                if(fragment instanceof CombinedFragment) {
                    coveredFragmentNode = getCombinedFragmentNodeInDiagram(diagram, (CombinedFragment) fragment);
                    if(coveredFragmentNode != null) {
                        list.add(coveredFragmentNode);
                    }
                }
            }
        } else {
            ContainerNode interactionOperandNode = (ContainerNode) combinedFragmentNode.getParent();
            list.add((NotationNode) interactionOperandNode.getParent());
            List<NotationNode> combindFragmentNodeList = getOnlyCombindFragmentNodeList(interactionOperandNode.getNodeList());
            if(combindFragmentNodeList.size() > 0) {
                for(NotationNode node : combindFragmentNodeList) {
                    CombinedFragment combinedFragment = (CombinedFragment) node.getUmlModel();
                    if(coveredBys.contains(combinedFragment)) {
                        list.add(node);
                    }
                }
            }
        }
        
        return list;
    }

    /**
     * coveredBys에 MessageFragment들만 있을때 메시지의 상위 fragment를 찾아서 리턴한다.
     *  
     * @param coveredBys
     * @param connection
     * @param connectionList
     * @return InteractionFragment
     */
    private static InteractionFragment getUpperFragmentOfOnlyMessageFragments(EList<InteractionFragment> coveredBys,
                                                                            AbstractConnection connection,
                                                                            List<AbstractConnection> connectionList) {
        AbstractConnection upperConnection = null;
        int gap = -1;
        for (AbstractConnection messageConnection : connectionList) {
            if (!connection.equals(messageConnection) && connection.getY() > messageConnection.getY()) {
                if (gap < 0) {
                    upperConnection = messageConnection;
                    gap = connection.getY() - messageConnection.getY();
                } else {
                    if (gap > connection.getY() - messageConnection.getY()) {
                        upperConnection = messageConnection;
                        gap = connection.getY() - messageConnection.getY();
                    }
                }

            }
        }
        
        if(upperConnection == null) {
            return null;
        }
        
        Message upperMessage = (Message) upperConnection.getUmlModel();
        MessageOccurrenceSpecificationImpl messageEvent = (MessageOccurrenceSpecificationImpl) upperMessage.getSendEvent();
        if(coveredBys.contains(messageEvent)) {
            return messageEvent;
        } else {
            if(RelationType.REPLY_MESSAGE.equals(upperConnection.getRelationType())) {
                messageEvent = (MessageOccurrenceSpecificationImpl) upperMessage.getReceiveEvent();
                if(coveredBys.contains(messageEvent)) {
                    return messageEvent;
                }
            } else {
                return (InteractionFragment) upperConnection.getTarget().getUmlModel();
            }
        }
        
        return null;
    }

    

    /**
     * 상하 이동관계를 고려하여 insert를 구현 함.
     * 
     * @param coveredBys
     * @param newPosition
     * @param sendEvent
     *            void
     */
    private static void moveCoveredBys(EList<InteractionFragment> coveredBys, int newPosition,
                                       InteractionFragment fragment) {
                
        int oldPosition = coveredBys.indexOf(fragment);
        if(newPosition == oldPosition) {
            return;
        }
        if (newPosition > oldPosition) {
            newPosition = newPosition - 1;
        }    
        if (newPosition == coveredBys.size()) {
            newPosition = newPosition - 1;
        } 
        coveredBys.remove(oldPosition);
        coveredBys.add(newPosition, fragment);

    }

   
    /**
     * 라이프라인에 관련된 순서들을 정렬한다.
     *  
     * @param diagram void
     */
    public static void refreshLifeLineOrder(Diagram diagram) {
        if (diagram != null) {
            refreshLifelineViewModelOrder(diagram);
        }
        Interaction interaction = null;
        if (diagram != null) {
            if (diagram.getParent() instanceof Interaction) {
                interaction = (Interaction) diagram.getParent();
            }
        }
        if (interaction != null) {
            refreshLifelineUmlModelOrder(diagram, interaction);
        }
    }

    /**
     * refreshLifelineViewModelOrder
     *  
     * @param diagram void
     */
    public static void refreshLifelineViewModelOrder(Diagram diagram) {
        List<LifeLineNode> lifelineNodeList = SequenceUtil.ascSortedLifeLineNodeListByXValue(getOnlyLifeLineNodeList(diagram.getNodeList()));
        for (AbstractNode sortedNode : lifelineNodeList) {
            int nodePosition = 0;
            if (lifelineNodeList.contains(sortedNode)) {
                nodePosition = lifelineNodeList.indexOf(sortedNode);
            }
            diagram.getNodeList().move(nodePosition, sortedNode);
        }
    }

    /**
     * refreshLifelineUmlModelOrder
     *  
     * @param diagram
     * @param interaction void
     */
    public static void refreshLifelineUmlModelOrder(Diagram diagram, Interaction interaction) {
        for (AbstractNode sortedNode : getOnlyLifeLineNodeList(diagram.getNodeList())) {

            Lifeline lifeLine = null;
            int nodePosition = 0;
            if (diagram.getNodeList().contains(sortedNode)) {
                nodePosition = diagram.getNodeList().indexOf(sortedNode);
            }
            if (sortedNode.getUmlModel() instanceof Lifeline) {
                lifeLine = (Lifeline) sortedNode.getUmlModel();
            }
            if (lifeLine != null) {
                interaction.getLifelines().move(nodePosition, lifeLine);
            }
        }
    }
    
    /**
     * deleteOperandList
     * void
     * @param node 
     */
    @SuppressWarnings("unchecked")
    public static void deleteOperandList(NotationNode node) {
        
        EList<AbstractNode> compartmentList = node.getCompartmentList();
        CombinedFragment combinedFragment = (CombinedFragment) node.getUmlModel();
        List list = new ArrayList();
        list.addAll(compartmentList);
        
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i) instanceof ContainerNode) {
                ContainerNode operandNode = (ContainerNode) list.get(i);
                deleteOperandChild(operandNode);
                combinedFragment.getOperands().remove(operandNode.getUmlModel());
            }            
            compartmentList.remove(list.get(i));           
            UMLManager.deleteElement((EObject) list.get(i));
        }                
        
    }
    
    /**
     * deleteOperandChild
     * void
     * 
     * @param connectionList
     */
    @SuppressWarnings("unchecked")
    public static void deleteOperandChild(ContainerNode operandNode) {
        EList<AbstractNode> nodeList = operandNode.getNodeList();
        InteractionOperand interactionOperand  = (InteractionOperand) operandNode.getUmlModel();
        
        deleteMessageByOperand(operandNode, interactionOperand);
        
        List list = new ArrayList();
        list.addAll(nodeList);
        
        for(int i = 0; i < list.size(); i++) {
            AbstractNode node = (AbstractNode) list.get(i);
            if(node.getUmlModel() instanceof InteractionConstraint) {
                InteractionConstraint guard = (InteractionConstraint) node.getUmlModel();
                interactionOperand.getFragments().remove(guard);
            } else if(node.getUmlModel() instanceof CombinedFragment) {
                deleteOperandList((NotationNode) node);
                CombinedFragment combinedFragment = (CombinedFragment) node.getUmlModel();
                interactionOperand.getFragments().remove(combinedFragment);                
            }
            nodeList.remove(node);
            UMLManager.deleteElement(node);
        }        
    }

    /**
     * deleteMessageByOperand
     *  
     * @param operandNode
     * @param interactionOperand void
     */
    private static void deleteMessageByOperand(ContainerNode operandNode, InteractionOperand interactionOperand) {
        Diagram diagram = SequenceUtil.getDiagram((AbstractNode) operandNode.getParent());
        EList<InteractionFragment> fragments = interactionOperand.getFragments();
        List<InteractionFragment> fragmentList = new ArrayList<InteractionFragment>();
        for(InteractionFragment fragment : fragments) {
            fragmentList.add(fragment);
        }
        for(InteractionFragment fragment : fragmentList) {
            if(fragment instanceof MessageOccurrenceSpecification) {
                Message message = ((MessageOccurrenceSpecification) fragment).getMessage();
                AbstractConnection connection = SequenceUtil.getMessageConnection(message, diagram);
                if(connection != null) {
                    SequenceUtil.deleteMessage(connection, diagram);
                }
            }
        }
    }

    
    /**
     * CombinedFragment의 라이프라인 리스트를 제거
     * @param combinedFragmentNode 
     *  
     * @param combinedFragment
     * @param removeLifelineList void
     */
    public static void removeCoveredLifelineList(Diagram diagram, NotationNode combinedFragmentNode, List<Lifeline> removeLifelineList) {
        for(Lifeline lifeline : removeLifelineList) {
            removeCoveredLifeline(diagram, combinedFragmentNode, lifeline);
        }
        
    }
    
    /**
     * CombinedFragment의 라이프라인 리스트를 제거
     * @param combinedFragmentNode 
     *  
     * @param combinedFragment
     * @param lifeline void
     */
    public static void removeCoveredLifeline(Diagram diagram, NotationNode combinedFragmentNode, Lifeline lifeline) {
        CombinedFragment combinedFragment = (CombinedFragment) combinedFragmentNode.getUmlModel();
        combinedFragment.getCovereds().remove(lifeline);        
        
        for(AbstractNode childNode : combinedFragmentNode.getCompartmentList()) {
            if(childNode instanceof ContainerNode) {
                //제거되는 라이프라인에 연결된 메시지를 찾아서 삭제.
                ContainerNode containerNode = (ContainerNode) childNode;
                InteractionOperand childOperand = (InteractionOperand) containerNode.getUmlModel();
                EList<InteractionFragment> fragments = childOperand.getFragments();
                List<InteractionFragment> fragmentList = new ArrayList<InteractionFragment>();
                for(InteractionFragment fragment : fragments) {
                    fragmentList.add(fragment);
                }
                for(int i = 0; i < fragmentList.size(); i++) {
                    InteractionFragment fragment = fragmentList.get(i);
                    if(fragment instanceof MessageOccurrenceSpecification 
                        && ((MessageOccurrenceSpecification) fragment).getCovereds().contains(lifeline)) {
                        Message message = ((MessageOccurrenceSpecification) fragment).getMessage();
                        AbstractConnection messageConnection = getMessageConnection(message, diagram);
                        deleteMessage(messageConnection, diagram);
                    }
                }
                //자식 CombinedFragment 삭제
                List<NotationNode> childFragmentNodeList = SequenceUtil.getOnlyCombindFragmentNodeList(containerNode.getNodeList());
                for(NotationNode childFragmentNode : childFragmentNodeList) {
                    removeCoveredLifeline(diagram, childFragmentNode, lifeline);
                }
            }
        }
       
    }

    /**
     * 메시지의 fragment들을 찾아서 리스트로 리턴한다.
     *  
     * @param message
     * @return List<InteractionFragment>
     */
    public static List<InteractionFragment> getFragmentListByMessage(Message message) {
        List<InteractionFragment> fragmentList = new ArrayList<InteractionFragment>();
        MessageOccurrenceSpecification sendFragment = (MessageOccurrenceSpecification) message.getSendEvent();
        MessageOccurrenceSpecification receiveFragment = (MessageOccurrenceSpecification) message.getReceiveEvent();

        if( null != message.getSendEvent() ) {
            fragmentList.add((MessageOccurrenceSpecification) message.getSendEvent());
        }
        if( null != message.getReceiveEvent() ) {
            fragmentList.add(receiveFragment);
        }
        
        if(isIncludedOperand(message)) {
            if( null == message.getSendEvent() ) {
                return fragmentList;
            }
            InteractionOperand operand = (InteractionOperand) sendFragment.eContainer();
            EList<InteractionFragment> fragments = operand.getFragments();
            BehaviorExecutionSpecification behaviorFragment = findBehaviorFragment(fragments, receiveFragment);
            if(behaviorFragment != null) {
                fragmentList.add(behaviorFragment);       
            }
        } else {
            if( null == message.getReceiveEvent() ) {
                return fragmentList;
            }
            Interaction interaction = (Interaction) message.getOwner();
            EList<InteractionFragment> fragments = interaction.getFragments();
            BehaviorExecutionSpecification behaviorFragment = findBehaviorFragment(fragments, receiveFragment);
            if(behaviorFragment != null) {
                fragmentList.add(behaviorFragment);       
            }
        }
        
        return fragmentList;
    }

    /**
     * receiveFragment의 BehaviorExecutionSpecification를 찾아서 리턴한다.
     *  
     * @param fragments
     * @param receiveFragment
     * @return BehaviorExecutionSpecification
     */
    private static BehaviorExecutionSpecification findBehaviorFragment(EList<InteractionFragment> fragments, MessageOccurrenceSpecification receiveFragment) {
        for(InteractionFragment fragment : fragments) {
            if(fragment instanceof BehaviorExecutionSpecification) {
                if(receiveFragment.equals(((BehaviorExecutionSpecification) fragment).getStart())
                    && receiveFragment.equals(((BehaviorExecutionSpecification) fragment).getFinish())) {
                    return (BehaviorExecutionSpecification) fragment;
                }
            }
        }
        return null;
    }

    /**
     * 새로 생성된 CombinedFragment에 따라서 메시지와 다른 CombinedFragment들을 shift시킨다.
     *  
     * @param diagram
     * @param combinedFragementNode
     * @param addedConnectionList void
     */
    public static void shiftByCreatedCombinedFragment(Diagram diagram, NotationNode combinedFragementNode, List<AbstractConnection> addedConnectionList) {
        int shiftMargin = UICoreConstant.FRAGMENT_LABEL_HEIGHT;
        
        for(AbstractConnection connection : addedConnectionList) {
            shiftMessageConnection(connection, shiftMargin);
        }
        
        List<AbstractConnection> allConnectionList = getOnlyMessageList(diagram.getConnectionList());
        for(AbstractConnection connection : allConnectionList) {
            if(!addedConnectionList.contains(connection)) {
                if(connection.getY() > combinedFragementNode.getY() + combinedFragementNode.getHeight()) {
                    //shiftMessageConnection(connection, shiftMargin);
                    shiftMessageConnection(connection,  combinedFragementNode.getHeight());
                } else if (connection.getY() > combinedFragementNode.getY() 
                    && connection.getY() < combinedFragementNode.getY() + combinedFragementNode.getHeight()) {
                    //int shiftValue = Math.abs(connection.getY() - (combinedFragementNode.getY() + combinedFragementNode.getHeight())) + shiftMargin;
                    //shiftMessageConnection(connection, shiftValue);
                    shiftMessageConnection(connection,  combinedFragementNode.getHeight());
                }
            }
        }
        
        List<NotationNode> combinedFragmentNodeList = getOnlyCombindFragmentNodeList(diagram.getNodeList());
        for(NotationNode node : combinedFragmentNodeList) {
            if(!combinedFragementNode.equals(node)) {
                if(node.getY() > combinedFragementNode.getY() + combinedFragementNode.getHeight()) {
                    //shiftCombinedFragmentNode(node, shiftMargin);
                    shiftCombinedFragmentNode(node, combinedFragementNode.getHeight());
                } else if (node.getY() > combinedFragementNode.getY() 
                    && node.getY() < combinedFragementNode.getY() + combinedFragementNode.getHeight()) {
                    //int shiftValue = Math.abs(node.getY() - (combinedFragementNode.getY() + combinedFragementNode.getHeight())) + shiftMargin;
                    //shiftCombinedFragmentNode(node, shiftValue);
                    shiftCombinedFragmentNode(node, combinedFragementNode.getHeight());
                }
            }
        }
        
        refreshMessageOrder(diagram);
        
    }
    
    /**
     * CombinedFragment안에 새로 생성된 CombinedFragment에 따라서 메시지와 다른 CombinedFragment들을 sifht시킨다.
     *  
     * @param diagram
     * @param combinedFragementNode
     * @param addedConnectionList void
     */
    public static void shiftByCreatedInnerCombinedFragment(Diagram diagram, NotationNode combinedFragementNode, List<AbstractConnection> addedConnectionList) {
        int shiftMargin = UICoreConstant.FRAGMENT_LABEL_HEIGHT;

        for(AbstractConnection connection : addedConnectionList) {
            shiftMessageConnection(connection, shiftMargin);
        }
                
        refreshMessageOrder(diagram);        
        
    }
        
    /**
     * CombinedFragment 이동에 따른 메시지 및 다른 CombinedFragment들을 shift한다.
     *  
     * @param diagram
     * @param combinedFragmentNode 
     * @param combinedFragementNode
     * @param shiftValue void
     */
    public static void shiftByMoveCombinedFragment(Diagram diagram, NotationNode combinedFragmentNode, int oldNodeY, int shiftValue) {
        if(combinedFragmentNode.getParent() instanceof ContainerNode) { //결합단편 내부의 결합단편인 경우
            ContainerNode parentNode = (ContainerNode)combinedFragmentNode.getParent();
            if(shiftValue > 0) {
                setParentCombinedFragmentHieght(shiftValue, parentNode);
                
                List<AbstractConnection> allConnectionList = getOnlyMessageList(diagram.getConnectionList());
                int y = translateToAbsoluteYByParentNode(oldNodeY, parentNode, (NotationNode) parentNode.getParent());
                for(AbstractConnection connection : allConnectionList) {
                    if(connection.getY() > oldNodeY) {
                        shiftMessageConnection(connection, shiftValue);
                    }  
                }
                        
                List<NotationNode> combinedFragmentNodeList = getOnlyCombindFragmentNodeList(parentNode.getNodeList());
                for(NotationNode friendNode : combinedFragmentNodeList) {
                    int friendNodeY = friendNode.getY();
                    friendNodeY = translateToAbsoluteYByParentNode(friendNodeY, parentNode, (NotationNode) parentNode.getParent());                    
                    if(!combinedFragmentNode.equals(friendNode) && friendNodeY > y) {
                        shiftCombinedFragmentNode(friendNode, shiftValue);
                    }      
                }
            } else {
                List<AbstractConnection> connectionList = getConnectionListInCombinedFragment(diagram, combinedFragmentNode);
                for(AbstractConnection connection : connectionList) {
                    shiftMessageConnection(connection, shiftValue);
                }
            }            
        } else {
            List<AbstractConnection> allConnectionList = getOnlyMessageList(diagram.getConnectionList());
            for(AbstractConnection connection : allConnectionList) {
                if(connection.getY() > oldNodeY) {
                    shiftMessageConnection(connection, shiftValue);
                }  
            }
            
            List<NotationNode> combinedFragmentNodeList = getOnlyCombindFragmentNodeList(diagram.getNodeList());
            for(NotationNode node : combinedFragmentNodeList) {
                if(node.getY() > oldNodeY && !combinedFragmentNode.equals(node)) {
                    shiftCombinedFragmentNode(node, shiftValue);
                }      
            }
        }
        
        
        refreshMessageOrder(diagram);    
        
    }

    /**
     * setParentCombinedFragmentHieght
     *  
     * @param shiftValue
     * @param parentNode void
     */
    private static void setParentCombinedFragmentHieght(int shiftValue, ContainerNode parentNode) {
        NotationNode parentFragmentNode = (NotationNode) parentNode.getParent();
        parentFragmentNode.setHeight(parentFragmentNode.getHeight() + shiftValue);
        parentNode.setHeight(parentNode.getHeight() + shiftValue);
        if(parentFragmentNode.getParent() instanceof ContainerNode) {
            ContainerNode containerNode = (ContainerNode) parentFragmentNode.getParent();
            setParentCombinedFragmentHieght(shiftValue, containerNode);
        }
    }
    
    /**
     * Operand resize에 따른 메시지 및 다른 CombinedFragment들을 shift한다.
     *  
     * @param diagram
     * @param operandNode 
     * @param combinedFragementNode
     * @param shiftValue void
     */
    public static void shiftByResizeInteractionOperand(Diagram diagram, ContainerNode operandNode, NotationNode combinedFragmentNode, int oldNodeY, int shiftValue) {
        if(combinedFragmentNode.getParent() instanceof ContainerNode) { //결합단편 내부의 결합단편인 경우
            ContainerNode parentNode = (ContainerNode)combinedFragmentNode.getParent();
            if(shiftValue > 0) {
                setParentCombinedFragmentHieght(shiftValue, parentNode);
                List<AbstractConnection> allConnectionList = getOnlyMessageList(diagram.getConnectionList());
                
                int y = oldNodeY;
                for(AbstractConnection connection : allConnectionList) {
                    if(connection.getY() > y) {
                        shiftMessageConnection(connection, shiftValue);
                    }  
                }
                
                AbstractNode ancestor = SequenceUtil.getAncestor(combinedFragmentNode);
                // 다이어그램 내의 다른 최상위 프래그먼트들 시프트 ( inner fragment 제외 )
                List<NotationNode> combinedFragmentNodeList = getOnlyCombindFragmentNodeList(diagram.getNodeList());//getAllCombinedFragmentNodeInDiagram(diagram);//getOnlyCombindFragmentNodeList(parentNode.getNodeList());
                for(NotationNode friendNode : combinedFragmentNodeList) {
                    if( friendNode.equals(ancestor) ) {
                        continue;
                    }
                    int friendNodeY = translateToAbsoluteYByParentNode(friendNode.getY(), parentNode, (NotationNode) parentNode.getParent());      
                    if(!combinedFragmentNode.equals(friendNode) && friendNodeY > (y + shiftValue)) {
                        shiftCombinedFragmentNode(friendNode, shiftValue);
                    }      
                }            
                // 동일 레벨의 형제 프래그먼트 시프트
                List<NotationNode> brotherFragment = getSameLevelFragment(combinedFragmentNode);
                for(NotationNode friendNode : brotherFragment) {
                    if( friendNode.equals(ancestor) ) {
                        continue;
                    }
                    System.out.println(friendNode.getY() + " : " + (combinedFragmentNode.getY() + combinedFragmentNode.getHeight()));
                    if(!combinedFragmentNode.equals(friendNode) && friendNode.getY() < combinedFragmentNode.getY() + combinedFragmentNode.getHeight()) {
                        shiftCombinedFragmentNode(friendNode, shiftValue);
                    }      
                }            
                
            } else {
                List<AbstractConnection> connectionList = getConnectionListInCombinedFragment(diagram, operandNode, combinedFragmentNode);
                for(AbstractConnection connection : connectionList) {
                    shiftMessageConnection(connection, shiftValue);
                }
            }            
        } else {
            List<AbstractConnection> allConnectionList = getOnlyMessageList(diagram.getConnectionList());
            for(AbstractConnection connection : allConnectionList) {
                if(connection.getY() > oldNodeY) {
                    shiftMessageConnection(connection, shiftValue);
                }  
            }
            
            List<NotationNode> combinedFragmentNodeList = getOnlyCombindFragmentNodeList(diagram.getNodeList());
            for(NotationNode node : combinedFragmentNodeList) {
                if(node.getY() > oldNodeY && !combinedFragmentNode.equals(node)) {
                    shiftCombinedFragmentNode(node, shiftValue);
                }      
            }
        }
        refreshMessageOrder(diagram);    
        
    }
        
    /**
     * 동일 레벨의 형제 프래그먼트를 조회한다.
     *  
     * @param combinedFragmentNode
     * @return List<NotationNode>
     */
    private static List<NotationNode> getSameLevelFragment(NotationNode combinedFragmentNode) {
        List<NotationNode> fragments = new ArrayList<NotationNode>();
        
        Object parent = combinedFragmentNode.getParent();
        if( parent instanceof ContainerNode ) {
            ContainerNode container = (ContainerNode) parent;
            for( AbstractNode brother : container.getNodeList() ) {
                if( brother instanceof NotationNode && !brother.equals(combinedFragmentNode)) {
                    NotationNode node = (NotationNode) brother;
                    if( node.getUmlModel() instanceof CombinedFragment ) {
                        fragments.add(node);
                    }
                }
            }
        }
        return fragments;
    }

    /**
     * shiftMessageConnection
     *  
     * @param connection void
     * @param shifrValue 
     */
    private static void shiftMessageConnection(AbstractConnection connection, int shiftValue) {
        connection.setY(connection.getY() + shiftValue);
        
        if(RelationType.REPLY_MESSAGE.equals(connection.getRelationType())) {
            NotationNode sourceBehaviorNode = (NotationNode) connection.getSource();
            if(sourceBehaviorNode.getY() + sourceBehaviorNode.getHeight() < connection.getY()) {
                sourceBehaviorNode.setHeight(sourceBehaviorNode.getHeight() + shiftValue);            
            }
        } else {
            AbstractView target = connection.getTarget();
            target.setY(target.getY() + shiftValue);     
        }
    }
  
    /**
     * shiftCombinedFragmentNode
     *  
     * @param node
     * @param shiftMargin void
     */
    private static void shiftCombinedFragmentNode(NotationNode node, int shiftMargin) {
        node.setY(node.getY() + shiftMargin);
        
    }

    /**
     * message로 Connection을 찾아서 리턴
     *  
     * @param message
     * @param diagram void
     */
    public static AbstractConnection getMessageConnection(Message message, Diagram diagram) {
       
        List<AbstractConnection> onlyMessageList = getOnlyMessageList(diagram.getConnectionList());
        for(AbstractConnection messageConnection : onlyMessageList) {
            if(message.equals(messageConnection.getUmlModel())) {
                return messageConnection;
            }
        }
        
        return null;
    }

    /**
     * 결합단편에서 operand의 y위치를 계산함.
     *  
     * @param combinedFragmentNode
     * @param operandNode
     * @return int
     */
    public static int getOperandY(NotationNode combinedFragmentNode, ContainerNode operandNode) {
        int operandY = combinedFragmentNode.getY() + UICoreConstant.FRAGMENT_LABEL_HEIGHT;
        if(combinedFragmentNode.getParent() instanceof ContainerNode) {
            ContainerNode parentOperandNode = (ContainerNode) combinedFragmentNode.getParent();
            operandY = translateToAbsoluteYByParentNode(operandY, parentOperandNode, (NotationNode) parentOperandNode.getParent());
        } 
        EList<AbstractNode> compartmentList = combinedFragmentNode.getCompartmentList();
        int indexOf = compartmentList.indexOf(operandNode);
        for(int i = 0; i < indexOf; i++) {
            AbstractNode abstractNode = compartmentList.get(i);
            operandY +=  abstractNode.getHeight();
        }
        return operandY;
    }

    /**
     * 다이어그램에서 해당 combinedFragment의 노드를 찾는다.
     *  
     * @param diagram
     * @param combinedFragment
     * @return NotationNode
     */
    public static NotationNode getCombinedFragmentNodeInDiagram(Diagram diagram, CombinedFragment combinedFragment) {
        List<NotationNode> list = getOnlyCombindFragmentNodeList(diagram.getNodeList());
        if(list != null && list.size() > 0) {
            for(NotationNode notationNode : list) {
                if(combinedFragment.equals(notationNode.getUmlModel())) {
                    return notationNode;
                }
            }
        }
        return null;
    }
    
    /**
     * combinedFragment의 노드를 찾는다.
     *  
     * @param diagram
     * @param combinedFragment
     * @return NotationNode
     */
    public static NotationNode getCombinedFragmentNode( List<NotationNode> combinedFragmentNodeList, CombinedFragment combinedFragment) {
        if(combinedFragmentNodeList != null && combinedFragmentNodeList.size() > 0) {
            for(NotationNode notationNode : combinedFragmentNodeList) {
                if(combinedFragment.equals(notationNode.getUmlModel())) {
                    return notationNode;
                } else {
                    for(AbstractNode abstractNode : notationNode.getCompartmentList()) {
                        List<NotationNode> chlldNodeList = getOnlyCombindFragmentNodeList(((ContainerNode) abstractNode).getNodeList());
                        if(chlldNodeList.size() > 0) {
                            return getCombinedFragmentNode(chlldNodeList, combinedFragment);
                        }
                        
                    }
                }
            }
        }
        return null;
    }
    
    /**
     * 다이어그램내에 CombinedFragment가 존재하는지 검사
     *  
     * @param diagram
     * @return boolean
     */
    public static boolean isDiagramHasCombinedFragement(Diagram diagram) {
        List<NotationNode> onlyCombindFragmentNodeList = SequenceUtil.getOnlyCombindFragmentNodeList(diagram.getNodeList());
        if(onlyCombindFragmentNodeList.size() > 0) {
            return true;
        }
        return false;
    }
    
    /**
     * CombinedFragment 노드안에 포함된 메시지들을 찾아서 리턴한다.
     *  
     * @param operandNode
     * @return List<AbstractConnection>
     */
    public static List<AbstractConnection> getConnectionListInCombinedFragment(Diagram diagram, NotationNode fragmentNode) {
        List<AbstractConnection> list = new ArrayList<AbstractConnection>();
        EList<AbstractNode> operandNodeList = fragmentNode.getCompartmentList();
        for(AbstractNode node : operandNodeList) {
            List<AbstractConnection> connectionListInOperand = getConnectionListInOperand(diagram, (ContainerNode) node);
            list.addAll(connectionListInOperand);
            List<NotationNode> combindFragmentNodeList = SequenceUtil.getOnlyCombindFragmentNodeList(((ContainerNode) node).getNodeList());
            if(combindFragmentNodeList.size() > 0) {
                for(NotationNode childNode : combindFragmentNodeList) {
                    List<AbstractConnection> connectionList = getConnectionListInCombinedFragment(diagram,  childNode);
                    list.addAll(connectionList);
                }
            }
        }
        return list;
    }
    
    /**
     * operandNode를 제외한 CombinedFragment 노드안에 포함된 메시지들을 찾아서 리턴한다.
     *  
     * @param operandNode
     * @return List<AbstractConnection>
     */
    public static List<AbstractConnection> getConnectionListInCombinedFragment(Diagram diagram, ContainerNode operandNode, NotationNode fragmentNode) {
        List<AbstractConnection> list = new ArrayList<AbstractConnection>();
        EList<AbstractNode> operandNodeList = fragmentNode.getCompartmentList();
        int indexOf = operandNodeList.indexOf(operandNode);
        for(int i = indexOf+1; i < operandNodeList.size(); i++) {
            List<AbstractConnection> connectionListInOperand = getConnectionListInOperand(diagram, (ContainerNode) operandNodeList.get(i));
            list.addAll(connectionListInOperand);
            List<NotationNode> combindFragmentNodeList = SequenceUtil.getOnlyCombindFragmentNodeList(((ContainerNode) operandNodeList.get(i)).getNodeList());
            if(combindFragmentNodeList.size() > 0) {
                for(NotationNode childNode : combindFragmentNodeList) {
                    List<AbstractConnection> connectionList = getConnectionListInCombinedFragment(diagram,  childNode);
                    list.addAll(connectionList);
                }
            }
        }
        return list;
    }
    
    /**
     * Operand 노드안에 포함된 메시지들을 찾아서 리턴한다.
     *  
     * @param operandNode
     * @return List<AbstractConnection>
     */
    public static List<AbstractConnection> getConnectionListInOperand(Diagram diagram, ContainerNode operandNode) {
        List<AbstractConnection> list = new ArrayList<AbstractConnection>();
        InteractionOperand operand = (InteractionOperand) operandNode.getUmlModel();
        EList<InteractionFragment> fragments = operand.getFragments();
        for(InteractionFragment fragment : fragments) {
            if(fragment instanceof MessageOccurrenceSpecification) {
                Message message = ((MessageOccurrenceSpecification) fragment).getMessage();
                AbstractConnection messageConnection = getMessageConnection(message, diagram);
                if(!list.contains(messageConnection)) {
                    list.add(messageConnection);
                }
            }
        }
        
        return list;
    }
    
    /**
     * message가 Operand안 포함되었는지 확인한다.
     *  
     * @param message
     * @return boolean
     */
    public static boolean isIncludedOperand(AbstractConnection messageConnection) {
        Message message = (Message) messageConnection.getUmlModel();
        MessageOccurrenceSpecification sendEvent = (MessageOccurrenceSpecification) message.getSendEvent();
        //메시지의 fragment의 eContainer가 InteractionOperand이면 결합단편에 포함되어 있다.
        //반면 Interation이면 포함 안되어 있다.
        if(sendEvent != null && sendEvent.eContainer() instanceof InteractionOperand) {
            return true;
        }
        
        return false;
    }
    
    /**
     * message가 Operand안 포함되었는지 확인한다.
     *  
     * @param message
     * @return boolean
     */
    public static boolean isIncludedOperand(Message message) {
        MessageOccurrenceSpecification sendEvent = (MessageOccurrenceSpecification) message.getSendEvent();
        //메시지의 fragment의 eContainer가 InteractionOperand이면 결합단편에 포함되어 있다.
        //반면 Interation이면 포함 안되어 있다.
        if(sendEvent != null && sendEvent.eContainer() instanceof InteractionOperand) {
            return true;
        }
        
        return false;
    }
    
    /**
     * message 이동시 operand 영역 밖으로 이동 체크
     * @param diagram 
     * @param messageConnection
     * @param location 
     * @return boolean
     */
    public static boolean checkOutofRangeOperandMoveMessage(Diagram diagram, AbstractConnection messageConnection, Point location) {
        Message message = (Message) messageConnection.getUmlModel();
        MessageOccurrenceSpecification sendEvent = (MessageOccurrenceSpecification) message.getSendEvent();
        if(sendEvent == null || !(sendEvent.eContainer() instanceof InteractionOperand)) {
            return false;
        }
        
        InteractionOperand interactionOperand = (InteractionOperand) sendEvent.eContainer();
        CombinedFragment combinedFragment = (CombinedFragment) interactionOperand.eContainer();

        NotationNode combinedFragmentNode = getCombinedFragmentNode(getOnlyCombindFragmentNodeList(diagram.getNodeList()), combinedFragment);
        if(combinedFragmentNode == null) {
            return false;
        }
        
        ContainerNode operandNode = null;
        for(int i = 0; i < combinedFragmentNode.getCompartmentList().size(); i++) {
            AbstractNode abstractNode = combinedFragmentNode.getCompartmentList().get(i);
            if(interactionOperand.equals(abstractNode.getUmlModel())) {
                operandNode = (ContainerNode) abstractNode;
                break;
            }
        }
        
        if(operandNode == null) {
            return false;
        }
        
        int operandY = getOperandY(combinedFragmentNode, operandNode);
        
        int lastY = location.y;
        //하단으로 이동하면 메시지의 Behavior노드를 검사해야한다.
        if(messageConnection.getY() < lastY) {
            //메시지의 behavior 검사
            if(!RelationType.REPLY_MESSAGE.equals(messageConnection.getRelationType())) {
                NotationNode behaviorNode = (NotationNode) messageConnection.getTarget();
                lastY = lastY + behaviorNode.getHeight();
            }
        }
        
        if(lastY < operandY ||lastY > operandY + operandNode.getHeight()) {
            return true;
        }
        
        if(isThereCombinedFragment(operandNode, messageConnection, location)) {
            return true;
        }
        
        return false;
    }
    
    /**
     * 이동하려는 위치에 CombinedFragment가 존재하는지 검사
     *  
     * @param diagram
     * @param messageConnection
     * @param location
     * @return boolean
     */
    public static boolean isThereCombinedFragment(ContainerNode operandNode, AbstractConnection messageConnection, Point location) {
        int startY = location.y;
        int lastY = location.y;
        // 밖에서 안으로는 Behavior노드의 영역은 검사하지 않는다.    
//        if(messageConnection.getY() < lastY) {
//            //메시지의 behavior 검사
//            if(!RelationType.REPLY_MESSAGE.equals(messageConnection.getRelationType())) {
//                NotationNode behaviorNode = (NotationNode) messageConnection.getTarget();
//                lastY = startY + behaviorNode.getHeight();
//            }
//        }
        List<NotationNode> onlyCombindFragmentNodeList = SequenceUtil.getOnlyCombindFragmentNodeList(operandNode.getNodeList());
        for(NotationNode notationNode : onlyCombindFragmentNodeList) {
            int fragmentY = translateToAbsoluteYByParentNode(notationNode.getY(), operandNode, (NotationNode) operandNode.getParent());
            if(lastY >= fragmentY && startY <= fragmentY + notationNode.getHeight()) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 이동하려는 위치에 CombinedFragment가 존재하는지 검사
     *  
     * @param diagram
     * @param messageConnection
     * @param location
     * @return boolean
     */
    public static boolean isThereCombinedFragment(Diagram diagram, AbstractConnection messageConnection, Point location) {
        int startY = location.y;
        int lastY = location.y;
        // 밖에서 안으로는 Behavior노드의 영역은 검사하지 않는다.    
//        if(messageConnection.getY() < lastY) {
//            //메시지의 behavior 검사
//            if(!RelationType.REPLY_MESSAGE.equals(messageConnection.getRelationType())) {
//                NotationNode behaviorNode = (NotationNode) messageConnection.getTarget();
//                lastY = startY + behaviorNode.getHeight();
//            }
//        }
        List<NotationNode> onlyCombindFragmentNodeList = SequenceUtil.getOnlyCombindFragmentNodeList(diagram.getNodeList());
        for(NotationNode notationNode : onlyCombindFragmentNodeList) {
            if(lastY >= notationNode.getY() && startY <= notationNode.getY() + notationNode.getHeight()) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * message Reconnect시 operand 영역 밖으로 이동 체크
     * @param diagram 
     * @param messageConnection
     * @param location 
     * @return boolean
     */
    public static boolean checkOutofRangeOperandReconnectMessage(Diagram diagram, AbstractConnection messageConnection, LifeLineNode lifeLineNode) {
        Message message = (Message) messageConnection.getUmlModel();
        MessageOccurrenceSpecification sendEvent = (MessageOccurrenceSpecification) message.getSendEvent();
        if(sendEvent == null || !(sendEvent.eContainer() instanceof InteractionOperand)) {
            return false;
        }
        
        InteractionOperand interactionOperand = (InteractionOperand) sendEvent.eContainer();
        CombinedFragment combinedFragment = (CombinedFragment) interactionOperand.eContainer();
        
        Lifeline lifeline = (Lifeline) lifeLineNode.getUmlModel();
        
        if(!lifeline.getCoveredBys().contains(combinedFragment)) {
            return true;
        }
        
        return false;
    }
    
    /**
     * diagram에서 해당 OperandNode를 찾아서 리턴한다.
     *  
     * @param combinedFragementNode
     * @return ContainerNode
     */
    public static ContainerNode getInteractionOperandNode(Diagram diagram, InteractionOperand interactionOperand) {
        List<NotationNode> combindFragmentNodeList = getOnlyCombindFragmentNodeList(diagram.getNodeList());
        for(NotationNode combinedFragementNode : combindFragmentNodeList) {
            ContainerNode interactionOperandNode = getInteractionOperandNode(combinedFragementNode, interactionOperand);
            if(interactionOperandNode != null) {
                return interactionOperandNode;
            }
            
        }
        return null;
    }
    
    /**
     * combinedFragementNode에서 해당 OperandNode를 찾아서 리턴한다.
     *  
     * @param combinedFragementNode
     * @return ContainerNode
     */
    public static ContainerNode getInteractionOperandNode(NotationNode combinedFragementNode, InteractionOperand interactionOperand) {
        for(AbstractNode compartmentNode : combinedFragementNode.getCompartmentList()) {
            if(compartmentNode instanceof ContainerNode && compartmentNode.getUmlModel() instanceof InteractionOperand) {
                InteractionOperand operand =  (InteractionOperand) compartmentNode.getUmlModel();
                if(interactionOperand.equals(operand)) {
                    return (ContainerNode) compartmentNode;
                }
                List<NotationNode> subCombindFragmentNodeList = getOnlyCombindFragmentNodeList(((ContainerNode) compartmentNode).getNodeList());
                for(NotationNode subNode : subCombindFragmentNodeList) {
                    ContainerNode interactionOperandNode = getInteractionOperandNode(subNode, interactionOperand);
                    if(interactionOperandNode != null) {
                        return interactionOperandNode;
                    }
                }
            }
        }        
        return null;
    }
    
    /**
     * Operand높이가 변경될 경우 자식 cominedFragment node의 절대값을 유지 시킨다.
     *  
     * @param operandNode
     * @param heightValue void
     */
    public static void setOperandHeight(ContainerNode operandNode, int heightValue) {
        operandNode.setHeight(operandNode.getHeight() + heightValue);
        List<NotationNode> fragmentNodeList = getOnlyCombindFragmentNodeList(operandNode.getNodeList());
        for(NotationNode fragmentNode : fragmentNodeList) {
            fragmentNode.setY(fragmentNode.getY() + heightValue);
        }
    }
    
    /**
     * //맨 마지막 오퍼랜드가 아니면 지워지는 오퍼랜드에 영향을 받는 오퍼랜드, 메시지를 쉬프트한다.
     *  
     * @param parentNode
     * @param node void
     */
    public static void shiftByDeletedOperand(NotationNode combinedFragmentNode, ContainerNode operandNode, int oldFragmentLastY) {
        
        Diagram diagram = getDiagram(combinedFragmentNode);
        
        int operandY = getOperandY(combinedFragmentNode, operandNode);

        List<AbstractConnection> allConnectionList = getOnlyMessageList(diagram.getConnectionList());
        for(AbstractConnection connection : allConnectionList) {
            if(connection.getY() > operandY && connection.getY() < oldFragmentLastY) {
                shiftMessageConnection(connection, -operandNode.getHeight());
            }  
        }
        
        List<NotationNode> combinedFragmentNodeList = getOnlyCombindFragmentNodeList(diagram.getNodeList());
        for(NotationNode node : combinedFragmentNodeList) {
            if(node.getY() > operandY && node.getY() < oldFragmentLastY && !combinedFragmentNode.equals(node)) {
                shiftCombinedFragmentNode(node, -operandNode.getHeight());
            }      
        }
        
    }
            
    /**
     * x위치 값을 다이어그램의 절대 값으로 변환한다.
     *  
     * @param x
     * @param parentFragmentNode
     * @return int
     */
    public static int translateToAbsoluteXByParentNode(int x, NotationNode parentFragmentNode) {
        x += parentFragmentNode.getX();
        
        if(parentFragmentNode.getParent() instanceof ContainerNode) {
            ContainerNode containerNode = (ContainerNode) parentFragmentNode.getParent();
            x = translateToAbsoluteXByParentNode(x, (NotationNode) containerNode.getParent());
        }
        
        return x;
    }
    
    /**
     * y위치 값을 다이어그램의 절대 값으로 변환한다.
     *  
     * @param y
     * @param parentFragmentNode
     * @return int
     */
    public static int translateToAbsoluteYByParentNode(int y, ContainerNode parentNode, NotationNode combinedFragmentNode) {
        if(combinedFragmentNode.getParent() instanceof ContainerNode) {
            ContainerNode containerNode = (ContainerNode) combinedFragmentNode.getParent();
            y += translateToAbsoluteYByParentNode(combinedFragmentNode.getY(), containerNode, (NotationNode) containerNode.getParent());
        } else {
            y += combinedFragmentNode.getY();
        }
        y += UICoreConstant.FRAGMENT_LABEL_HEIGHT;
        EList<AbstractNode> compartmentList = combinedFragmentNode.getCompartmentList();
        int indexOf = compartmentList.indexOf(parentNode);
        for(int i = 0; i < indexOf; i++) {
            AbstractNode abstractNode = compartmentList.get(i);
            y +=  abstractNode.getHeight();
        }
        
        
        return y;
    }
    
    /**
     * x 값을 부모 CmbinedFragment 노드의 상대값으로 변환한다.
     *  
     * @param x
     * @param parentCombinedFragmentNode
     * @return void
     */
    public static int translateToRelativeXByParentNode(int x, ContainerNode parentNode, NotationNode parentCombinedFragmentNode) {
        x -=  parentCombinedFragmentNode.getX() - UICoreConstant.INNER_MARGIN; //위치 계층을 주기위해 마진을 준다.

        if(parentCombinedFragmentNode.getParent() instanceof ContainerNode) {
            ContainerNode containerNode = (ContainerNode) parentCombinedFragmentNode.getParent();
            x = translateToRelativeXByParentNode(x, containerNode, (NotationNode) containerNode.getParent());
        }
        
        return x;
    }
  
    /**
     * 삭제된 라이프라인 covereds로 CombinedFragment의 위치와 사이즈를 조절 한다.
     *  
     * @param combinedFragmentNode
     * @param combinedFragment void
     */
    public static void calculateCoverdsLocationAndSize(Diagram diagram, NotationNode combinedFragmentNode,
                                                 CombinedFragment combinedFragment) {
        EList<Lifeline> covereds;
        covereds = combinedFragment.getCovereds();        
        List<LifeLineNode> allLifeLineNodeList = SequenceUtil.getOnlyLifeLineNodeList(diagram.getNodeList());
        List<LifeLineNode> lifeLineNodeList = new ArrayList<LifeLineNode>();
        for(Lifeline lifeline : covereds) {
            for(LifeLineNode lifeLineNode : allLifeLineNodeList) {
                if(lifeline.equals(lifeLineNode.getUmlModel())) {
                    lifeLineNodeList.add(lifeLineNode);
                    break;
                }
            }
        }
        
        if(lifeLineNodeList.size() <= 0) {
            return;
        }
        
        lifeLineNodeList = SequenceUtil.ascSortedLifeLineNodeListByXValue(lifeLineNodeList);    
        LifeLineNode firstNode = lifeLineNodeList.get(0);
        LifeLineNode lastNode =  lifeLineNodeList.get(lifeLineNodeList.size()-1);    
        int startX = firstNode.getX();  
        int lastX = lastNode.getX();
        if(combinedFragmentNode.getParent() instanceof ContainerNode) {
            ContainerNode containerNode = (ContainerNode) combinedFragmentNode.getParent();
            startX = SequenceUtil.translateToRelativeXByParentNode(startX, containerNode, (NotationNode) containerNode.getParent());
            lastX = SequenceUtil.translateToRelativeXByParentNode(lastX, containerNode, (NotationNode) containerNode.getParent());
            //삭제할때는 마진을 두번줘야한다.
            lastX = calculateInnerMargin(lastX, (NotationNode) containerNode.getParent());
        } 
        combinedFragmentNode.setX(startX);
        combinedFragmentNode.setWidth(lastX - combinedFragmentNode.getX() + lastNode.getWidth());
        for(AbstractNode childNode : combinedFragmentNode.getCompartmentList()) {
            if(childNode instanceof ContainerNode) {
                ContainerNode containerNode = (ContainerNode) childNode;
                List<NotationNode> childFragmentNodeList = SequenceUtil.getOnlyCombindFragmentNodeList(containerNode.getNodeList());
                for(NotationNode childFragmentNode : childFragmentNodeList) {
                    calculateCoverdsLocationAndSize(diagram, childFragmentNode, (CombinedFragment) childFragmentNode.getUmlModel());
                }
            }
        }
    }
    
    /**
     * 결합단편은 안쪽에 생기는 결합단편의 마진을 계산한다.
     *  
     * @param minX
     * @param containerNod
     * @param parent
     * @return int
     */
    public static int calculateInnerMargin(int x, NotationNode parentCombinedFragmentNode) {
        x = x - UICoreConstant.INNER_MARGIN * 2;
        if(parentCombinedFragmentNode.getParent() instanceof ContainerNode) {
            ContainerNode containerNod = (ContainerNode)parentCombinedFragmentNode.getParent();
            x = calculateInnerMargin(x, (NotationNode) containerNod.getParent());
        }
        return x;
    }

    
    
    /**
     * 
     * 
     * CreateMessageCommand 에서 Message 생성 후 다른 Message & Fragment 시프트 이동
     *  
     * @param sendConnection void
     * @param replyConnection 
     */
    public static void shiftByNewInputBounds(Diagram diagram, NotationNode behaviorNode, AbstractConnection sendConnection, AbstractConnection replyConnection) {

        NotationNode node = null;
        
        List<AbstractConnection> connectionList = SequenceUtil.getChildrenMessageListByBehavior(behaviorNode);
        List<InteractionFragment> fragmentList = new ArrayList<InteractionFragment>();
        for (AbstractConnection connection : connectionList) {
            if (connection.getUmlModel() instanceof Message) {
                for (InteractionFragment fragment : SequenceUtil.getFragmentListByMessage((Message) connection.getUmlModel())) {
                    if (!fragmentList.contains(fragment)) {
                        fragmentList.add(fragment);
                    }
                }
            }
        }
        for (InteractionFragment fragment : fragmentList) {
            
            Element parent = fragment.getOwner();
            
            if( parent instanceof InteractionOperand ) {
                InteractionOperand operand = (InteractionOperand) parent;
                
                Element operandParent = operand.getOwner();
                if( operandParent instanceof CombinedFragment ) {
                    List<NotationNode> nodeList = SequenceUtil.getAllCombinedFragmentNodeInDiagram(diagram);
                    for( NotationNode child : nodeList ) {
                        if( child.getUmlModel().equals(operandParent) ) {
                            node = child;
                            break;
                        }
                    }
                }
            }
        }
        
        if( behaviorNode == null ) {
            return;
        }
        
        if( node == null || node.getParent() == null ) {
            shiftFragmentAndMessage(diagram, behaviorNode, sendConnection, replyConnection);
        } else {
            setParentNodeSize(sendConnection, behaviorNode, node);
            
            connectionList = diagram.getConnectionList();
            for( AbstractConnection connection : connectionList ) {
                // 더 높은 곳에 있으면 시프트 하지 않는다.
                if( sendConnection.getY() > connection.getY() ) {
                    continue;
                }
                if( connection.equals(sendConnection) ) {
                    continue;
                }
                if( replyConnection != null && connection.equals(replyConnection) ) {
                    continue;
                }
                shiftMessageConnection(connection, behaviorNode.getHeight());
            }
            
            List<NotationNode> combinedFragmentList = new ArrayList<NotationNode>();
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //////////////////////////////////////////////프래그먼트 노드 시프트////////////////////////////////////////////////////////////////
            // 노드 시프트 하는 케이스를 두 가지로 구분.
            // 1. 부모가 다이어그램인 경우.
            // 2. 부모가 프래그먼트인 경우. 이 경우 다시 두 가지로 구분하여 처리.
            //// 2.1 같은 부모를 가진 프래그먼트들의 처리.
            //// 2.2 다른 부모를 가진 프래그먼트들의 처리.
            
            // 1. 부모가 다이어그램인 경우.
            if( node.getParent() instanceof Diagram ) {
                for (AbstractNode abstractNode : diagram.getNodeList()) {
                    // UML 모델이 CombinedFragment 이고 그래픽모델이 NotationNode 인 경우.
                    if (abstractNode.getUmlModel() instanceof CombinedFragment && abstractNode instanceof NotationNode) {
                        NotationNode notation = (NotationNode) abstractNode;
                        if( notation.getY() > node.getY() ) {
                            combinedFragmentList.add((NotationNode) notation);
                        }
                    }
                }
            // 2. 부모가 프래그먼트인 경우.
            } else {
                // 2.1 같은 부모를 가진 프래그먼트 
                List<NotationNode> family = new ArrayList<NotationNode>();
//                family = getFamilyFragment(family, node, node);
//                family = getFamilyWithoutParent(node, family);
//                for( NotationNode notation : family ) {
//                    if( notation.getParent().equals(node.getParent()) ) {
//                        notation.setY(notation.getY() + behaviorNode.getHeight());  
//                    }
//                }
                setFamilyLocationShift(node, family);
                
                // 2.2 다른 부모를 가진 프래그먼트
                NotationNode ancestor = getAncestor(node);
                for (AbstractNode abstractNode : diagram.getNodeList()) {
                    if (abstractNode.getUmlModel() instanceof CombinedFragment && abstractNode instanceof NotationNode) {
                        NotationNode notation = (NotationNode) abstractNode;
                        if( notation.getY() > ancestor.getY() ) {
                            combinedFragmentList.add((NotationNode) notation);
                        }
                    }
                }
            }
            
            // 같은 레벨에 존재하는 프래그먼트에 대한 처리.
            for( AbstractNode child : node.getCompartmentList() ) {
                if( child instanceof ContainerNode ) {
                    ContainerNode container = (ContainerNode) child;
                    for( AbstractNode childNode : container.getNodeList() ) {
                        if( !(childNode instanceof NotationNode && childNode.getUmlModel() instanceof CombinedFragment) ) {
                            continue;
                        }
                        Rectangle childBounds = new Rectangle();
                        getNewBounds(childBounds, childNode);
                        if( childBounds.y > sendConnection.getY() ) {
                            if( !combinedFragmentList.contains(childNode) ) {
                                combinedFragmentList.add((NotationNode) childNode);
                            }
                        }
                    }
                }
            }
            
            List<NotationNode> allFragments = SequenceUtil.getAllCombinedFragmentNodeInDiagram(diagram);
            List<NotationNode> removeList = new ArrayList<NotationNode>();
            for( NotationNode fragment : allFragments ) {
                for( NotationNode childNode : allFragments ) {
                    if( fragment.getParent() instanceof ContainerNode ) {
                        ContainerNode containerNode = (ContainerNode) fragment.getParent();
                        if( containerNode.getParent() instanceof NotationNode ) {
                            NotationNode parentNotaionNode = (NotationNode) containerNode.getParent();
                            if( parentNotaionNode.equals(childNode) ) {
                                removeList.add(fragment);
                            }
                        }
                    }
                }
            }
            combinedFragmentList.removeAll(removeList);
            // 시프트 처리.
            for( NotationNode fragmentNode : combinedFragmentList ) {
                // newObj는 제외하고
                if( fragmentNode.equals(node) ) {
                    continue;
                }
                fragmentNode.setY(fragmentNode.getY() + behaviorNode.getHeight());  
            }
            //////////////////////////////////////////////프래그먼트 노드 시프트////////////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        }
    }
    
    /**
     * 다이어그램에 메세지 생성 시(프래그먼트와 상관없는) 
     * 
     * 프래그먼트와 메시지 시프트
     * 
     *   void
     * @param sendConnection 
     * @param behaviorNode 
     * @param replyConnection 
     * @param node 
     */
    private static void shiftFragmentAndMessage(Diagram diagram, NotationNode behaviorNode, AbstractConnection sendConnection, AbstractConnection replyConnection) {
        
        List<NotationNode> combinedFragmentList = new ArrayList<NotationNode>();
        
        for (AbstractNode abstractNode : diagram.getNodeList()) {
            // UML 모델이 CombinedFragment 이고 그래픽모델이 NotationNode 인 경우.
            if (abstractNode.getUmlModel() instanceof CombinedFragment && abstractNode instanceof NotationNode) {
                NotationNode node = (NotationNode) abstractNode;
                if( node.getY() > sendConnection.getY() ) {
                    combinedFragmentList.add((NotationNode) node);
                }
            }
        }
        
        // 시프트 처리.
        for( NotationNode fragmentNode : combinedFragmentList ) {
            fragmentNode.setY(fragmentNode.getY() + behaviorNode.getHeight());  
        }
        List<AbstractConnection> connectionList = diagram.getConnectionList();
        for( AbstractConnection connection : connectionList ) {
            // 더 높은 곳에 있으면 시프트 하지 않는다.
            if( behaviorNode.getY() > connection.getY() ) {
                continue;
            }
            if( connection.equals(sendConnection) ) {
                continue;
            }
            if( replyConnection != null && connection.equals(replyConnection) ) {
                continue;
            }
            shiftMessageConnection(connection, behaviorNode.getHeight());
        }
        
    }

    public static void setParentNodeSize(int delta, NotationNode notationNode) {
        List<AbstractNode> parentnodeList = new ArrayList<AbstractNode>();

        parentnodeList = getParentNode(parentnodeList, (AbstractNode) notationNode.getParent());
        
        for( int i = parentnodeList.size() - 1; i >= 0; i--) {
            
            AbstractNode nNode = (AbstractNode) parentnodeList.get(i);
            nNode.setHeight(nNode.getHeight() + delta);
        }
//        AbstractNode parent = (AbstractNode) notationNode.getParent();
//        parent.setHeight(parent.getHeight() + delta);
    }

    private static void setParentNodeSize(AbstractConnection sendConnection, NotationNode behavior, NotationNode notationNode) {
        List<AbstractNode> parentnodeList = new ArrayList<AbstractNode>();

        parentnodeList = getParentNode(parentnodeList, (AbstractNode) notationNode.getParent());
        parentnodeList.add(notationNode);
        
        for( int i = parentnodeList.size() - 1; i >= 0; i--) {
            
            AbstractNode nNode = (AbstractNode) parentnodeList.get(i);
            nNode.setHeight(nNode.getHeight() + behavior.getHeight());
            
        }
        
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
                ContainerNode container = SequenceUtil.getInteractionOperandNode(getDiagram(notationNode), (InteractionOperand) operand);
                container.setHeight(container.getHeight() + behavior.getHeight());
            }
        }
    }

    
    public static List<AbstractNode> getParentNode(List<AbstractNode> parentnodeList, AbstractNode parentNode) {

        if (parentNode instanceof NotationNode) {
            NotationNode nNode = ((NotationNode) parentNode);

            if( !parentnodeList.contains(nNode) ) {
                parentnodeList.add(nNode);
            }

            if ((nNode.getParent() instanceof Diagram)) {
                return parentnodeList;
            }
            if (null != nNode.getParent() && nNode.getParent() instanceof ContainerNode) {
                getParentNode(parentnodeList, (ContainerNode) parentNode.getParent());
            }

        } else if (parentNode instanceof ContainerNode) {
            ContainerNode cNode = (ContainerNode) parentNode;
            
            if( !parentnodeList.contains(cNode) ) {
                parentnodeList.add(cNode);
            }
            
            EObject parent = cNode.getParent();
            if (parent instanceof NotationNode && ((NotationNode) parent).getUmlModel() instanceof CombinedFragment) {
                getParentNode(parentnodeList, (AbstractNode) parent);
            }
        }

        if ((parentNode.getParent() instanceof Diagram)) {
            return parentnodeList;
        }
        if (null != parentNode.getParent() && parentNode.getParent() instanceof NotationNode) {
            getParentNode(parentnodeList, (NotationNode) parentNode.getParent());
        }

        return parentnodeList;
    }
    
    /**
     * 생성되거나 위치를 옮긴 프래그먼트 또는 메시지에 대해
     * 나머지 요소들의 위치를 시프트 처리.
     * 
     * 기존에 사용되는 알아보기 힘든 시프트 로직들을 모두 대체
     * 
     *  
     * @param newObj void
     */
    public static void shiftByNewInputBounds(NotationNode newObj) {
        
        Diagram diagram = null;
        diagram = getDiagram(newObj);
        
        // 시프트 메세지
        shiftMessage(newObj, diagram);

        List<AbstractNode> nodeList = diagram.getNodeList();
        List<NotationNode> combinedFragmentList = new ArrayList<NotationNode>();
     
        // 시프트 프래그먼트 리스트
        shiftFragment(newObj, nodeList, combinedFragmentList);

        // 시프트 처리.
        for( NotationNode fragmentNode : combinedFragmentList ) {
            // newObj는 제외하고
            if( fragmentNode.equals(newObj) ) {
                continue;
            }
            
//            if( newObj.getParent() instanceof Diagram ) {
//                int newHeight = newObj.getHeight()>FRAGMENT_HEIGHT_MINIMUM_SIZE?newObj.getHeight():FRAGMENT_HEIGHT_MINIMUM_SIZE;
//                fragmentNode.setY(fragmentNode.getY() + newHeight);  
//            } else {
                fragmentNode.setY(fragmentNode.getY() + newObj.getHeight());  
//            }
//            int newHeight = newObj.getHeight()>FRAGMENT_HEIGHT_MINIMUM_SIZE?newObj.getHeight():FRAGMENT_HEIGHT_MINIMUM_SIZE;
//            fragmentNode.setY(fragmentNode.getY() + newHeight);  
        }
    }
    
    private static int FRAGMENT_HEIGHT_MINIMUM_SIZE = 150;
    
    /**
     * 
     * 
     * CreateMessageCommand 에서 Message 생성 후 다른 Message & Fragment 시프트 이동
     * @param resize 
     *  
     * @param sendConnection void
     */
    public static void shiftByNewInputBounds(int delta, NotationNode notationNode, boolean resize) {

        Diagram diagram = getDiagram(notationNode);

        setParentNodeSize(delta, notationNode);

        List<AbstractConnection> connectionList = diagram.getConnectionList();
        for (AbstractConnection connection : connectionList) {
            // 더 높은 곳에 있으면 시프트 하지 않는다.
            Rectangle rect = new Rectangle();
            rect = getNewBounds(rect, notationNode);
            
            if( rect.y  > connection.getY() ) {
                continue;
            }
            
            if( rect.y  < connection.getY() && connection.getY() < rect.y + notationNode.getHeight() && resize) {
                continue;
            }
            shiftMessageConnection(connection, delta);
        }

        List<NotationNode> combinedFragmentList = new ArrayList<NotationNode>();
        // /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // ////////////////////////////////////////////프래그먼트 노드
        // 시프트////////////////////////////////////////////////////////////////
        // 노드 시프트 하는 케이스를 두 가지로 구분.
        // 1. 부모가 다이어그램인 경우.
        // 2. 부모가 프래그먼트인 경우. 이 경우 다시 두 가지로 구분하여 처리.
        // // 2.1 같은 부모를 가진 프래그먼트들의 처리.
        // // 2.2 다른 부모를 가진 프래그먼트들의 처리.

        // 1. 부모가 다이어그램인 경우.
        if (notationNode.getParent() instanceof Diagram) {
            for (AbstractNode abstractNode : diagram.getNodeList()) {
                // UML 모델이 CombinedFragment 이고 그래픽모델이 NotationNode 인 경우.
                if (abstractNode.getUmlModel() instanceof CombinedFragment && abstractNode instanceof NotationNode) {
                    NotationNode notation = (NotationNode) abstractNode;
                    if (notation.getY() > notationNode.getY()) {
                        combinedFragmentList.add((NotationNode) notation);
                    }
                }
            }
            // 2. 부모가 프래그먼트인 경우.
        } else {
            // 2.1 같은 부모를 가진 프래그먼트
            List<NotationNode> family = new ArrayList<NotationNode>();
            family = getFamilyFragment(family, notationNode, notationNode);
            family = getFamilyWithoutParent(notationNode, family);
            for (NotationNode notation : family) {
                
                // if-else 와 같이 한부모를 사용하지만 다른 파트일때.
                if( notation.getParent().equals(notationNode.getParent()) ) {
                    notation.setY(notation.getY() + delta);
                }
            }
            // 2.2 다른 부모를 가진 프래그먼트
            NotationNode ancestor = getAncestor(notationNode);
            for (AbstractNode abstractNode : diagram.getNodeList()) {
                if (abstractNode.getUmlModel() instanceof CombinedFragment && abstractNode instanceof NotationNode) {
                    NotationNode notation = (NotationNode) abstractNode;
                    if (notation.getY() > ancestor.getY()) {
                        combinedFragmentList.add((NotationNode) notation);
                    }
                }
            }
        }
        // 시프트 처리.
        for (NotationNode fragmentNode : combinedFragmentList) {
            // newObj는 제외하고
            if (fragmentNode.equals(notationNode)) {
                continue;
            }
            fragmentNode.setY(fragmentNode.getY() + delta);
        }
        // ////////////////////////////////////////////프래그먼트 노드
        // 시프트////////////////////////////////////////////////////////////////
        // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }
    
    
    /**
     * 
     *  
     * @param newObj
     * @param nodeList
     * @param combinedFragmentList void
     */
    private static void shiftFragment(NotationNode newObj, List<AbstractNode> nodeList,
                                      List<NotationNode> combinedFragmentList) {
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////프래그먼트 노드 시프트////////////////////////////////////////////////////////////////
        // 노드 시프트 하는 케이스를 두 가지로 구분.
        // 1. 부모가 다이어그램인 경우.
        // 2. 부모가 프래그먼트인 경우. 이 경우 다시 두 가지로 구분하여 처리.
        //// 2.1 같은 부모를 가진 프래그먼트들의 처리.
        //// 2.2 다른 부모를 가진 프래그먼트들의 처리.
        
        // 1. 부모가 다이어그램인 경우.
        if( newObj.getParent() instanceof Diagram ) {
            for (AbstractNode abstractNode : nodeList) {
                // UML 모델이 CombinedFragment 이고 그래픽모델이 NotationNode 인 경우.
                if (abstractNode.getUmlModel() instanceof CombinedFragment && abstractNode instanceof NotationNode) {
                    NotationNode node = (NotationNode) abstractNode;
                    if( node.getY() > newObj.getY() ) {
                        
                        //프래그먼트 생성 시에 영역에 포함되는 프래그먼트는 시프트 하지 않는다.
//                        if( newObj.getY() < node.getY() && node.getY() + node.getHeight() < newObj.getY() + newObj.getHeight() ) {
//                            continue;
//                        }
                        combinedFragmentList.add((NotationNode) node);
                    }
                }
            }
        // 2. 부모가 프래그먼트인 경우.
        } else {
            // 2.1 같은 부모를 가진 프래그먼트 
            List<NotationNode> family = new ArrayList<NotationNode>();
            setFamilyLocationShift(newObj, family);
            // 2.2 다른 부모를 가진 프래그먼트
            NotationNode ancestor = getAncestor(newObj);
            for (AbstractNode abstractNode : nodeList) {
                if (abstractNode.getUmlModel() instanceof CombinedFragment && abstractNode instanceof NotationNode) {
                    NotationNode node = (NotationNode) abstractNode;
                    if( node.getY() > ancestor.getY() ) {
                        combinedFragmentList.add((NotationNode) node);
                    }
                }
            }
        }
        //////////////////////////////////////////////프래그먼트 노드 시프트////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    /**
     * 
     *  
     * @param newObj
     * @param diagram void
     */
    private static void shiftMessage(NotationNode newObj, Diagram diagram) {
        
        // inner Fragment인 경우 좌표가 상대좌표 이므로 부모의 좌표값을 모두 더해서 좌표값을 구하도록 한다.
        Rectangle newBounds = new Rectangle();
        newBounds = getNewBounds(newBounds, newObj);
        newBounds.height = newObj.getHeight();
        
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////메세지 시프트//////////////////////////////////////////////////////////////
        List<AbstractConnection> connectionList = diagram.getConnectionList();
        for( AbstractConnection connection : connectionList ) {
            
            // 더 높은 곳에 있으면 시프트 하지 않는다.
            if( newBounds.y > connection.getY() ) {
                continue;
            }
            // newObj가 Fragment일 때, 자식 메시지는 시프트하지 않도록 한다.
            if( newBounds.y < connection.getY() && connection.getY() < newBounds.y + newBounds.height ) {
                if( connection.getRelationType() == RelationType.REPLY_MESSAGE ) {
                    NotationNode sourceNode = (NotationNode) connection.getSource();
                    if( newBounds.y < sourceNode.getY() && sourceNode.getY() < newBounds.y + newBounds.height ) {
//                        continue;
                    }
                } else {
                    NotationNode targetNode = (NotationNode) connection.getTarget();
                    if( newBounds.y < targetNode.getY() && targetNode.getY() + targetNode.getHeight() < newBounds.y + newBounds.height ) {
//                        continue;
                    }
                }
            }
            if( newBounds.y < connection.getY() && connection.getY() < newBounds.y + newBounds.height ) {
                NotationNode sourceNode = (NotationNode) connection.getSource();
                if( newObj.getY() < sourceNode.getY() && sourceNode.getY() < newObj.getY() + newObj.getHeight() ) {
//                    continue;
                }
            }
            
            if( newObj.getParent() instanceof Diagram ) {
                shiftMessageConnection(connection, newObj.getHeight());//>FRAGMENT_HEIGHT_MINIMUM_SIZE?newObj.getHeight():FRAGMENT_HEIGHT_MINIMUM_SIZE);
            } else {
                shiftMessageConnection(connection, newObj.getHeight());
            }
        }
        //////////////////////////////////////////////메세지 시프트///////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }
    
    

    /**
     * 
     *  
     * @param newBounds
     * @param newObj
     * @return Rectangle
     */
    public static Rectangle getNewBounds(Rectangle newBounds, AbstractNode newObj) {
      
        EObject parent = newObj.getParent();
        if( newObj instanceof NotationNode && newObj.getUmlModel() instanceof CombinedFragment ) {
            newBounds.x += newObj.getX();
            newBounds.y += newObj.getY();
        }
        if( newObj instanceof ContainerNode && parent instanceof NotationNode ) {
            NotationNode parentNode = (NotationNode) parent;
            for( AbstractNode child : parentNode.getCompartmentList() ) {
                if( !child.equals(newObj) ) {
                    newBounds.y += child.getHeight();
                } else {
                    break;
                }
            }
        }
        if( parent instanceof Diagram && newObj instanceof NotationNode) {
            return newBounds;
        } else {
            return getNewBounds(newBounds, (AbstractNode) parent);
        }
    }

    /**
     * 
     *  
     * @param newObj
     * @param family void
     */
    private static void setFamilyLocationShift(NotationNode newObj, List<NotationNode> family) {
//        family = getFamilyWithoutParent(newObj, family);
//        for( NotationNode node : family ) {
//            node.setY(node.getY() + newObj.getHeight());  
//        }
        family = getFamilyFragment(family, newObj, newObj);
        family = getFamilyWithoutParent(newObj, family);
        for( NotationNode notation : family ) {
            if( notation.getParent().equals(newObj.getParent()) ) {
                notation.setY(notation.getY() + newObj.getHeight());  
            }
        }
    }
    
    /**
     * 
     *  
     * @param newObj
     * @return NotationNode
     */
    private static List<NotationNode> getFamilyWithoutParent(AbstractNode newObj, List<NotationNode> family) {
        
        EObject parent = newObj.getParent();
        if( newObj instanceof NotationNode && newObj.getUmlModel() instanceof CombinedFragment ) {
            family.remove(newObj);
        }
        if( parent instanceof Diagram && newObj instanceof NotationNode) {
            return family;
        } else {
            return getFamilyWithoutParent((AbstractNode) parent, family);
        }
    }

    /**
     * 
     *  
     * @param newHeight 
     * @param newObj void
     */
    private static List<NotationNode> getFamilyFragment(List<NotationNode> family, AbstractNode newInput, AbstractNode node) {
        
        EObject parentObj = node.getParent();
        if( parentObj instanceof Diagram && node instanceof NotationNode) {
            return family;
        } else if( parentObj instanceof ContainerNode ) {
            getFamilyFragment(family, newInput, (ContainerNode) parentObj);
        } else if( parentObj instanceof NotationNode && ((AbstractNode)parentObj).getUmlModel() instanceof CombinedFragment ) {
            
            NotationNode parent = (NotationNode) parentObj;
            for( AbstractNode childContainer : parent.getCompartmentList() ) {
                if( childContainer instanceof ContainerNode ) {
                    ContainerNode cNode = (ContainerNode) childContainer;
                    for( AbstractNode childNode : cNode.getNodeList()) {
                        if( childNode instanceof NotationNode && childNode.getUmlModel() instanceof CombinedFragment ) {
                            NotationNode nNode = (NotationNode) childNode;
                            
                            NotationNode newInputParent = null;
                            if( newInput.getParent() instanceof ContainerNode ) {
                                if( ((ContainerNode)newInput.getParent()).getParent() instanceof NotationNode ) {
                                    newInputParent = (NotationNode) ((ContainerNode)newInput.getParent()).getParent();
                                }
                            }
                            if( nNode.equals(node) || nNode.equals(newInput) || nNode.equals(newInputParent) ) {
//                                continue;
                            }
                            Rectangle source = new Rectangle();
                            Rectangle target = new Rectangle();
                            getNewBounds(source, nNode);
                            getNewBounds(target, newInput);
                            if( source.y > target.y ) {
                                if( !family.contains(nNode) ) {
                                    family.add(nNode);
                                }
                            }
                        }
                    }
                }
            }
            getFamilyFragment(family, newInput, (NotationNode) parent);
        }
        return family;
    }

    /**
     * 
     *  
     * @param newObj
     * @return NotationNode
     */
    public static NotationNode getAncestor(AbstractNode newObj) {
        
        EObject parent = newObj.getParent();
        if( parent instanceof Diagram && newObj instanceof NotationNode) {
            return (NotationNode) newObj;
        } else {
            return getAncestor((AbstractNode) parent);
        }
    }

    /**
     * 다이어그램에서 모든 combinedFragment의 노드를 찾는다.
     *  
     * @param diagram
     * @param combinedFragment
     * @return List<NotationNode>
     */
    public static List<NotationNode> getAllCombinedFragmentNodeInDiagram(Diagram diagram) {
        
        List<NotationNode> allFragment = new ArrayList<NotationNode>();
        
        List<NotationNode> list = getOnlyCombindFragmentNodeList(diagram.getNodeList());
        for( NotationNode node : list ) {
            addFragment(allFragment, node);
        }
        return allFragment;
    }

    /**
     * 
     *  
     * @param allFragment
     * @param node void
     */
    private static List<NotationNode> addFragment(List<NotationNode> allFragment, AbstractNode node) {
        
        if( node instanceof ContainerNode ) {
            ContainerNode container = (ContainerNode) node;
            for( AbstractNode child : container.getNodeList() ) {
                addFragment(allFragment, child);
            }
        } else if( node instanceof NotationNode && node.getUmlModel() instanceof CombinedFragment ) {
            NotationNode notation = (NotationNode) node;
            if(!allFragment.contains(notation)) {
                allFragment.add(notation);
            }
            for( AbstractNode child : notation.getCompartmentList() ) {
                addFragment(allFragment, child);
            }
        }
        
        return allFragment;
    }

    /**
     * 
     *   void
     */
    public static void setAllLifeLineHeight(Diagram diagram) {
        
        // 라이프라인 크기 조정
        List<AbstractNode> allBehaviorNodes = new ArrayList<AbstractNode>();
        
        int sameHeight = 0;
        List<AbstractNode> lifeLines = new ArrayList<AbstractNode>();
        for( AbstractNode child : diagram.getNodeList() ) {
            if( child instanceof LifeLineNode ) {
                lifeLines.add(child);
                if( sameHeight == 0 ) {
                    child.setHeight( child.getHeight() );
                    sameHeight = child.getHeight();
                } else {
                    child.setHeight(sameHeight);
                }
                allBehaviorNodes.addAll(((LifeLineNode) child).getBehaviorList());
            }
        }
        int changedHeight = 0;
        List<AbstractNode> allNodeList = new ArrayList<AbstractNode>();
        allNodeList.addAll(allBehaviorNodes);
        for( AbstractNode node : diagram.getNodeList() ) {
            if( !allNodeList.contains(node) ) {
                allNodeList.add(node);
            }
        }
        for( AbstractNode child : allNodeList ) {
            if( !(child instanceof LifeLineNode) ) {
                if( child.getY() + child.getHeight() > lifeLines.get(0).getY() + sameHeight ) {
                    int diffrentSize = (child.getY() + child.getHeight()) - (lifeLines.get(0).getY() + sameHeight);
                    if( changedHeight < diffrentSize ) {
                        changedHeight = diffrentSize;
                    }
                }
            }
        }
        int lifeLineHeight = 0;
        for( AbstractNode child : lifeLines ) {
            if( lifeLineHeight == 0 ) {
                child.setHeight( child.getHeight() + changedHeight );
                lifeLineHeight = child.getHeight();
            } else {
                child.setHeight(lifeLineHeight);
            }
        }
    }
    
    /**
     * 
     *  
     * @param allFragments void
     */
    public static List<NotationNode> checkDuplicatedNode(List<NotationNode> allFragments) {
        List<NotationNode> removeList = new ArrayList<NotationNode>();
        for( NotationNode fragment : allFragments ) {
            for( NotationNode node : allFragments ) {
                if( fragment.getParent() instanceof Diagram ) {
                    continue;
                }
                if( node.getParent() instanceof Diagram ) {
                    continue;
                }
                if( fragment.getParent() instanceof ContainerNode ) {
                    ContainerNode containerNode = (ContainerNode) fragment.getParent();
                    if( containerNode.getParent() instanceof NotationNode ) {
                        NotationNode parentNotaionNode = (NotationNode) containerNode.getParent();
                        if( parentNotaionNode.equals(node) ) {
                            removeList.add(fragment);
                        }
                    }
                }
            }
        }
        allFragments.removeAll(removeList);
        return allFragments;
    }

    /**
     * 
     *  
     * @param currentFragmentNode
     * @param abstractNode
     * @return boolean
     */
    public static boolean checkDuplicatedParentNode(NotationNode currentFragmentNode, NotationNode abstractNode) {
        NotationNode notationNode = currentFragmentNode;
        if( notationNode != null ) {
            for( AbstractNode childNode : notationNode.getCompartmentList() ) {
                if( childNode instanceof ContainerNode ) {
                    ContainerNode container = (ContainerNode) childNode;
                    if( container.getNodeList().contains(abstractNode) ) {
                        return false;
                    }
                }
            }
        }
        while ( notationNode != null ) {
            notationNode = getParentNotationNode(currentFragmentNode);
            if( notationNode != null ) {
                for( AbstractNode childNode : notationNode.getCompartmentList() ) {
                    if( childNode instanceof ContainerNode ) {
                        ContainerNode container = (ContainerNode) childNode;
                        if( container.getNodeList().contains(abstractNode) ) {
                            return false;
                        }
                    }
                }
            }
        }
        
        return true;
    }

    /**
     * 
     *  
     * @param currentFragmentNode
     * @return NotationNode
     */
    private static NotationNode getParentNotationNode(NotationNode currentFragmentNode) {
        if( null != currentFragmentNode.getParent() ) {
            if( !(currentFragmentNode.getParent() instanceof Diagram) )
                return (NotationNode) ((AbstractNode)currentFragmentNode.getParent()).getParent();
        }
        return null;
    }
}
