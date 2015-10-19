/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.manager.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import nexcore.tool.mda.model.designer.transformation.RuleSet;
import nexcore.tool.mdd.core.extension.INotationModelHandler;
import nexcore.tool.uml.core.log.Log;
import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.manager.ManagerConstant;
import nexcore.tool.uml.manager.UMLManager;
import nexcore.tool.uml.manager.util.ClassDiagramUtil;
import nexcore.tool.uml.manager.util.DiagramUtil;
import nexcore.tool.uml.manager.util.SequenceUtilManager;
import nexcore.tool.uml.manager.utility.SemanticModelHandlerUtil;
import nexcore.tool.uml.manager.utility.UMLModelerNotationModelHandlerUtil;
import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.AbstractView;
import nexcore.tool.uml.model.umldiagram.BendPoint;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.DiagramType;
import nexcore.tool.uml.model.umldiagram.Dimension;
import nexcore.tool.uml.model.umldiagram.LabelNode;
import nexcore.tool.uml.model.umldiagram.LabelType;
import nexcore.tool.uml.model.umldiagram.LifeLineNode;
import nexcore.tool.uml.model.umldiagram.Line;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.model.umldiagram.Relation;
import nexcore.tool.uml.model.umldiagram.RelationType;
import nexcore.tool.uml.model.umldiagram.UMLDiagramFactory;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehaviorExecutionSpecification;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageOccurrenceSpecification;
import org.eclipse.uml2.uml.MessageSort;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Relationship;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.manager</li>
 * <li>서브 업무명 : nexcore.tool.uml.manager.handler</li>
 * <li>설 명 : UMLModelerNotationModelHandler</li>
 * <li>작성일 : 2010. 11. 22.</li>
 * <li>작성자 : 최윤석</li>
 * </ul>
 */
public class UMLModelerNotationModelHandler implements INotationModelHandler {

    /** 선택한 요소 목록 */
    private List<Element> selectedElementList;

    /** 변환규칙 집합 */
    private RuleSet ruleSet;

    /** 진행 모니터 */
    private IProgressMonitor monitor;

    /**
     * 시퀀스 다이어그램 생성하는 메소드
     * 
     * @see nexcore.tool.mda.core.infrastructure.handler.INotationModelHandler#handleSequenceDiagram()
     */
    public void handleSequenceDiagram() {
        // 타겟 인터랙션 목록 찾아오기
        List<Interaction> targetInteractionList = this.getTargetInteraction();

        for (Interaction interaction : targetInteractionList) {
            monitor.subTask(UMLMessage.getMessage(UMLMessage.LABEL_ING, UMLMessage.LABEL_SEQUENCE_DIAGRAM_CREATION)
                + interaction.getName());

            this.createDiagram(interaction);

            monitor.worked(1);
        }
    }

    /**
     * 시퀀스 다이어그램 생성하는 메소드
     * 
     * @param designInteraction
     *            void
     */
    private void createDiagram(Interaction designInteraction) {
        // 인터랙션에 있는 라이프라인 수 만큼 라이프라인노드를 생성한다.
        EList<Lifeline> lifelines = designInteraction.getLifelines();
        Lifeline lifeline = null;

        Diagram diagram = null;
        List<Diagram> diagrams = UMLModelerNotationModelHandlerUtil.getDiagramList(designInteraction,
            DiagramType.SEQUENCE_DIAGRAM);

        // Interaction 밑에는 하나의 다이어그램만 존재
        if (diagrams == null || diagrams.size() == 0) {
            diagram = createDiagram(designInteraction, designInteraction.getName(), DiagramType.SEQUENCE_DIAGRAM);
            diagram.setId(UUID.randomUUID().toString());
        } else {
            diagram = (Diagram) diagrams.get(0);
        }

        for (Lifeline ll : lifelines) {
            lifeline = (Lifeline) ll;

            createLifeLineNode(designInteraction, lifeline, diagram);
        }

        // 인터랙션에 있는 메세지 수 만큼 메세지를 생성한다.
        EList<Message> messages = designInteraction.getMessages();
        Message message = null;
        Lifeline sourceLifeLine = null;
        Lifeline targetLifeLine = null;
        MessageOccurrenceSpecification mos = null;
        MessageOccurrenceSpecification smos = null;
        MessageOccurrenceSpecification tmos = null;
        boolean bool = true;

        for (Message ms : messages) {
            message = (Message) ms;
            if (message.getSendEvent() instanceof MessageOccurrenceSpecification) {
                mos = (MessageOccurrenceSpecification) message.getSendEvent();
                sourceLifeLine = mos.getCovereds().get(0);
            }

            if (message.getReceiveEvent() instanceof MessageOccurrenceSpecification) {
                mos = (MessageOccurrenceSpecification) message.getReceiveEvent();
                targetLifeLine = mos.getCovereds().get(0);
            }

            for (InteractionFragment fragment : designInteraction.getFragments()) {
                if (fragment instanceof BehaviorExecutionSpecification) {
                    smos = (MessageOccurrenceSpecification) ((((BehaviorExecutionSpecification) fragment).getStart()));
                    tmos = (MessageOccurrenceSpecification) ((((BehaviorExecutionSpecification) fragment).getFinish()));

                    if (message.equals(smos.getMessage()) || message.equals(tmos.getMessage())) {
                        bool = true;

                        for (AbstractConnection connection : diagram.getConnectionList()) {
                            if (connection.getUmlModel().equals(message)) {
                                bool = false;
                            }
                        }

                        if (bool) {
                            createMessageConnection(designInteraction,
                                sourceLifeLine,
                                targetLifeLine,
                                (BehaviorExecutionSpecification) fragment,
                                message,
                                diagram);
                        }
                    }
                }
            }
        }
    }

    /**
     * 라이프라인 생성하는 메소드
     * 
     * @param designInteraction
     * @param lifeLn
     * @param diagram
     *            void
     */
    private void createLifeLineNode(Interaction designInteraction, Lifeline lifeLn, Diagram diagram) {
        LifeLineNode lifeLineNode = UMLDiagramFactory.eINSTANCE.createLifeLineNode();
        lifeLineNode.setId(UUID.randomUUID().toString());
        lifeLineNode.setName(lifeLn.getName());
        lifeLineNode.setUmlModel(lifeLn);
        lifeLineNode.setNodeType(NodeType.LIFELINE);
        lifeLineNode.setWidth(150);
        lifeLineNode.setHeight(300);

        EList<Lifeline> lifelines = designInteraction.getLifelines();

        int lifeLineNodeYLocation = 0;

        for (int i = 0; i < lifelines.size(); i++) {
            if (lifeLn.equals(lifelines.get(i))) {
                lifeLineNodeYLocation = i;
            }
        }

        if (lifelines.size() > 1) {
            lifeLineNode.setY(20);
            lifeLineNode.setX(lifeLineNodeYLocation * 200 + 20);
        } else {
            lifeLineNode.setY(20);
            lifeLineNode.setX(20);
        }

        Line line = UMLDiagramFactory.eINSTANCE.createLine();
        line.setId(UUID.randomUUID().toString());
        line.setUmlModel(lifeLn);
        line.setNodeType(NodeType.LINE);
        line.setParent(lifeLineNode);
        lifeLineNode.setLine(line);

        if (diagram != null) {
            lifeLineNode.setParent(diagram);
            diagram.getNodeList().add(lifeLineNode);
        }
    }

    /**
     * 메시지 커넥션 생성하는 메소드
     * 
     * @param interaction
     * @param lifelineSend
     * @param lifelineRecv
     * @param behavior
     * @param createMessage
     * @param diagram
     *            void
     */
    private void createMessageConnection(Interaction interaction, Lifeline lifelineSend, Lifeline lifelineRecv,
                                         BehaviorExecutionSpecification behavior, Message createMessage, Diagram diagram) {
        Relation connection = UMLDiagramFactory.eINSTANCE.createRelation();
        connection.setId(UUID.randomUUID().toString());
        connection.setName(createMessage.getName());
        connection.setUmlModel(createMessage);

        if (MessageSort.SYNCH_CALL_LITERAL.equals(createMessage.getMessageSort())) {
            connection.setRelationType(RelationType.SYNCHRONOUS_MESSAGE);
        } else if (MessageSort.REPLY_LITERAL.equals(createMessage.getMessageSort())) {
            connection.setRelationType(RelationType.REPLY_MESSAGE);
        } else {
            connection.setRelationType(RelationType.ASYNCHRONOUS_MESSAGE);
        }

        NotationNode behaviorNode = null;
        if (diagram != null) {
            setConnection(lifelineSend, lifelineRecv, behavior, connection, diagram, behaviorNode);
        }
    }

    /**
     * 커넥션 설정하는 메소드
     * 
     * @param lifelineSend
     * @param lifelineRecv
     * @param behavior
     * @param connection
     * @param diagram
     * @param behaviorNode
     *            void
     */
    private void setConnection(Lifeline lifelineSend, Lifeline lifelineRecv, BehaviorExecutionSpecification behavior,
                               Relation connection, Diagram diagram, NotationNode behaviorNode) {
        LifeLineNode sourceNode = SequenceUtilManager.getLifeLifeNode(diagram, lifelineSend);
        LifeLineNode targetNode = SequenceUtilManager.getLifeLifeNode(diagram, lifelineRecv);

        if (diagram.getConnectionList().size() > 0) {
            connection.setY(diagram.getConnectionList().size() * ManagerConstant.FIGURE_BEHAVIOR_HEIGHT + 100);
        } else {
            connection.setY(100);
        }

        // Self Connection의 BendPoint 생성
        if (sourceNode.equals(targetNode)) {
            setSelfMessage(connection, sourceNode);
        }

        if (!RelationType.REPLY_MESSAGE.equals(connection.getRelationType())) {
            behaviorNode = setBehaviorNode(behavior, connection, targetNode);
        } else {
            // 2012.12.12 황선림 수정
//            connection.setTarget(targetNode);
            connection.setTarget(targetNode.getLine());
        }
        connection.setSource(sourceNode.getLine());

        diagram.getConnectionList().add(connection);
        DiagramUtil.attachSourceOfConnection(connection);
        DiagramUtil.attachTargetOfConnection(connection);

        if (behaviorNode != null) {
            SequenceUtilManager.checkLifeLinesHeight(diagram, connection, behaviorNode, 10);
        } else {
            SequenceUtilManager.checkLifeLinesHeight(diagram, connection, 10);
        }
    }

    /**
     * 셀프 메시지 설정하는 메소드
     * 
     * @param connection
     * @param sourceNode
     *            void
     */
    private void setSelfMessage(Relation connection, LifeLineNode sourceNode) {
        int margin = 10;
        Dimension sourceAnchorDimension = UMLDiagramFactory.eINSTANCE.createDimension();
        sourceAnchorDimension.setWidth(sourceNode.getX() + sourceNode.getWidth() / 2);
        sourceAnchorDimension.setHeight(connection.getY());
        connection.setSourceAnchor(sourceAnchorDimension);
        Dimension targetAnchorDimension = UMLDiagramFactory.eINSTANCE.createDimension();
        targetAnchorDimension.setWidth(sourceNode.getX() + sourceNode.getWidth() / 2);
        targetAnchorDimension.setHeight(connection.getY() + margin);
        connection.setTargetAnchor(targetAnchorDimension);

        Point sourcePoint = new Point(connection.getSourceAnchor().getWidth(), connection.getSourceAnchor().getHeight());
        Point targetPoint = new Point(connection.getTargetAnchor().getWidth(), connection.getTargetAnchor().getHeight());

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

        connection.getBendPointList().add(0, firstBendPoint);

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

        connection.getBendPointList().add(1, secondBendPoint);
    }

    /**
     * 행위 노드 설정하는 메소드
     * 
     * @param behavior
     * @param connection
     * @param targetNode
     * @return NotationNode
     */
    private NotationNode setBehaviorNode(BehaviorExecutionSpecification behavior, Relation connection,
                                         LifeLineNode targetNode) {
        NotationNode behaviorNode;

        behaviorNode = UMLDiagramFactory.eINSTANCE.createNotationNode();
        // Behavior를 만들어 주고 타겟 노드의 Line에다 add 한다.
        behaviorNode.setNodeType(NodeType.LIFE_LINE_BEHAVIOR);
        behaviorNode.setId(UUID.randomUUID().toString());
        behaviorNode.setUmlModel(behavior);
        behaviorNode.setParent(targetNode.getLine());
        behaviorNode.setX(targetNode.getX() + targetNode.getWidth() / 2 - ManagerConstant.FIGURE_BEHAVIOR_WIDTH / 2);
        behaviorNode.setY(connection.getY());
        behaviorNode.setWidth(ManagerConstant.FIGURE_BEHAVIOR_WIDTH);
        behaviorNode.setHeight(ManagerConstant.FIGURE_BEHAVIOR_HEIGHT);

        targetNode.getBehaviorList().add(behaviorNode);
        connection.setTarget(behaviorNode);

        return behaviorNode;
    }

    /**
     * 타겟 인터랙션 가져오는 메소드
     * 
     * @return List<Interaction>
     */
    private List<Interaction> getTargetInteraction() {
        List<Collaboration> collaborationList = new ArrayList<Collaboration>();
        List<Interaction> interactionList = new ArrayList<Interaction>();
        Interaction targetInteraction = null;

        String targetModelUri = this.ruleSet.getTargetModelLocation();

        for (Element element : this.selectedElementList) {
            if (element instanceof Collaboration) {
                collaborationList.add((Collaboration) element);
            }
        }

        for (Collaboration collaboration : collaborationList) {
            for (Behavior behavior : collaboration.getOwnedBehaviors()) {
                if (behavior instanceof Interaction) {
                    targetInteraction = SemanticModelHandlerUtil.findTargetInteraction(targetModelUri,
                        (Interaction) behavior);

                    if (targetInteraction != null) {
                        interactionList.add(targetInteraction);
                    }
                }
            }
        }

        return interactionList;
    }

    /**
     * VOPC 다이어그램 생성하는 메소드
     * 
     * @see nexcore.tool.mda.core.infrastructure.handler.INotationModelHandler#handleClassDiagram()
     */
    public void handleClassDiagram() {
        // 결과 문자열 담는 목록
        List<String> result = new ArrayList<String>();
        // 컬레보레이션 목록
        List<Element> collaborationList = new ArrayList<Element>();

        for (Element element : selectedElementList) {
            monitor.subTask(UMLMessage.getMessage(UMLMessage.LABEL_ING, UMLMessage.LABEL_VOPC_DIAGRAM_CREATION)
                + ((NamedElement) element).getName());

            // 요소가 null이 아니고,
            // Model 혹은 Package 혹은 Component 혹은 Collaboration 일 경우에만 추출해서
            // 클래스 다이어그램 생성하는 메소드 호출시에 넘겨 준다.
            if (element != null
                && (element instanceof Model || element instanceof Package || element instanceof Component || element instanceof Collaboration)) {
                try {
                    extractElementForClassDiagram(element, result, collaborationList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            monitor.worked(1);
        }

        collaborationList.clear();
    }

    /**
     * Collaboration하위에 클래스 다이어그램이 생성되어야 하므로, Collaboration을 만날 때까지 하위 요소를 찾아가는
     * 메소드
     * 
     * @param element
     * @param result
     * @param collaborationList
     * @return List<String>
     */
    private List<String> extractElementForClassDiagram(Element element, List<String> result,
                                                       List<Element> collaborationList) {
        if (element instanceof Collaboration) {
            // 요소가 Collaboration일 경우,
            // 클래스 다이어그램 생성하는 메소드 호출
            Collaboration collaboration = (Collaboration) element;
            result = createClassDiagram(collaboration, result, collaborationList);
            
        } else if (element instanceof Package || element instanceof Component) {
            // 요소가 Package 혹은 Component일 경우,
            // Package 혹은 Component 내의 Collaboration을 얻어서
            // 클래스 다이어그램 생성하는 메소드 호출하고
            // 그 외의 경우, 이 메소드를 재귀 호출한다.
            EList<Element> elements = element.getOwnedElements();
            Collaboration collaboration = null;

            for (int i = 0; i < elements.size(); i++) {
                if (elements.get(i) instanceof Collaboration) {
                    collaboration = (Collaboration) elements.get(i);

                    result = createClassDiagram(collaboration, result, collaborationList);
                } else {
                    extractElementForClassDiagram(elements.get(i), result, collaborationList);
                }
            }
        }

        return result;
    }

    /**
     * 클래스 다이어그램 생성하는 메소드
     * 
     * @param collaboration
     * @param sequenceList
     * @param collaborationList
     * @return List<String>
     */
    private static List<String> createClassDiagram(Collaboration collaboration, List<String> sequenceList,
                                                   List<Element> collaborationList) {
        // 클래스 다이어그램 요소 목록
        List<Element> classDiagramElementList = new ArrayList<Element>();
        // 클래스 다이어그램 관계 목록
        List<Message> classDiagramRelationList = new ArrayList<Message>();

        // 컬레보레이션 목록에 해당 컬레보레이션이 없을 경우,
        // 컬레보레이션 목록에 추가
        if (!collaborationList.contains(collaboration)) {
            collaborationList.add(collaboration);
        } else {
            return null;
        }

        if (collaboration != null && sequenceList != null) {
            List<Behavior> collaborationBehaviorList = collaboration.getOwnedBehaviors();

            // 컬레보레이션에 다이어그램 추가
            Diagram classDiagram = addClassDiagram(collaboration);
            // 다이어그램에 ID값 설정
            classDiagram.setId(UUID.randomUUID().toString());

            // 동일한 클래스 다이어그램이 안 생기도록 처리
            List<Diagram> classDiagramList = UMLModelerNotationModelHandlerUtil.getDiagramList(collaboration,
                DiagramType.CLASS_DIAGRAM);
            int sameDiagramNameCount = -1;
            for (Diagram diagram : classDiagramList) {
                if (diagram.getName().contains(classDiagramList.get(0).getName())) {
                    sameDiagramNameCount++;
                }
            }
            if (sameDiagramNameCount != -1 && sameDiagramNameCount != 0) {
                classDiagram.setName(classDiagram.getName() + ManagerConstant.UNDER_BAR + sameDiagramNameCount);
            }

            Interaction interaction = null;
            Behavior collaborationBehaviorObj = null;

            classDiagramElementList.clear();
            classDiagramRelationList.clear();

            for (Iterator<Behavior> collaborationBehaviorIterator = collaborationBehaviorList.iterator(); collaborationBehaviorIterator.hasNext();) {
                collaborationBehaviorObj = collaborationBehaviorIterator.next();

                // 컬레보레이션 Behavior 객체가 Interaction일 경우
                // Interaction으로부터 LifeLine, Message를 찾아서
                // 클래스 다이어그램 생성시에 사용하도록 찾아 온다.
                if (collaborationBehaviorObj != null && collaborationBehaviorObj instanceof Interaction) {
                    interaction = (Interaction) collaborationBehaviorObj;

                    // Interaction에서 클래스 다이어그램 요소 목록을 찾아온다.
                    findClassDiagramElements(interaction, classDiagramElementList);
                    // Interaction에서 클래스 다이어그램 관계 목록을 찾아온다.
                    findClassDiagramRelations(interaction, classDiagramRelationList);

                    sequenceList.add(interaction.getName());
                }
            }

            // 클래스 다이어그램 요소 목록이 없을 경우, null 반환
            if (classDiagramElementList.isEmpty()) {
                return null;
            }

            // 클래스 다이어그램 생성을 위한 노드 목록 생성
            ClassDiagramUtil.createNodeListOfClassDiagram(classDiagramElementList, classDiagram);
            // 클래스 다이어그램 생성을 위한 관계 목록 생성
            createRelationListOfClassDiagram(classDiagramRelationList, classDiagram);
            // 클래스 다이어그램 레이아웃 구성
            ClassDiagramUtil.composeDiagramLayout(classDiagram);
        }

        return sequenceList;
    }

    /**
     * 클래스 다이어그램 추가하는 메소드
     * 
     * @param collaboration
     * @return Diagram
     */
    private static Diagram addClassDiagram(Collaboration collaboration) {
        Diagram diagram = createDiagram(collaboration, UMLMessage.LABEL_VOPC_DIAGRAM_NAME, DiagramType.CLASS_DIAGRAM);

        return diagram;
    }

    /**
     * Interaction LifeLine들의 유형들을 찾아 클래스 다이어그램 생성시 사용하는 클래스 다이어그램 요소 목록에 추가하는
     * 메소드
     * 
     * @param classDgm
     * @param interaction
     *            void
     */
    private static void findClassDiagramElements(Interaction interaction, List<Element> classDiagramElementList) {
        // Interaction이 가지는 LifeLine 요소 목록 가져오기
        EList<Lifeline> lifeLines = interaction.getLifelines();
        Element element = null;

        // LifeLine의 유형을 클래스 다이어그램 요소 목록에 추가
        for (Lifeline lifeLine : lifeLines) {
            if (lifeLine != null && lifeLine.getRepresents() != null && lifeLine.getRepresents().getType() != null) {
                element = lifeLine.getRepresents().getType();

                if (element instanceof Actor) {
                    continue;
                }

                if (!classDiagramElementList.contains(element)) {
                    classDiagramElementList.add(element);
                }
            }
        }
    }

    /**
     * Interaction Message들을 클래스 다이어그램 생성시 사용하는 클래스 다이어그램 관계 목록에 추가하는 메소드
     * 
     * @param interaction
     * @param classDiagramRelationList
     *            void
     */
    private static void findClassDiagramRelations(Interaction interaction, List<Message> classDiagramRelationList) {
        for (Message message : interaction.getMessages()) {
            if (!classDiagramRelationList.contains(message)) {
                classDiagramRelationList.add(message);
            }
        }
    }

    /**
     * 클래스 다이어그램 관계 목록으로부터 클래스 다이어그램에 사용할 관계 목록을 생성하는 메소드
     * 
     * @param classDiagramRelationList
     * @param classDiagram
     *            void
     */
    private static void createRelationListOfClassDiagram(List<Message> classDiagramRelationList, Diagram classDiagram) {
        AbstractNode source = null;
        AbstractNode target = null;
        NamedElement sourceElement = null;
        NamedElement targetElement = null;
        Map<AbstractNode, List<AbstractNode>> sourceTargetList = new HashMap<AbstractNode, List<AbstractNode>>();
        List<AbstractNode> targetList = null;
        Dependency dependency = null;
        boolean isPossibleConnect = true;
        AbstractConnection relation = null;
        LabelNode labelNode = null;

        for (Message message : classDiagramRelationList) {
            try {
                
                isPossibleConnect = true;
                // Message가 Reply일 경우, 계속 진행
                if (message.getMessageSort().equals(MessageSort.REPLY_LITERAL)) {
                    continue;
                }

                // Source 요소 가져오기
                sourceElement = ((MessageOccurrenceSpecification) message.getSendEvent()).getCovereds()
                    .get(0)
                    .getRepresents()
                    .getType();
                // Target 요소 가져오기
                targetElement = ((MessageOccurrenceSpecification) message.getReceiveEvent()).getCovereds()
                    .get(0)
                    .getRepresents()
                    .getType();
                // Source 요소 가져오기
                if (sourceElement == null || targetElement == null) {
                    continue;
                }

                if (sourceElement == targetElement) {
                    continue;
                }

                // 중복된 Relation 체크
                // if(hasRelation(sourceElement, targetElement)) {
                // continue;
                // }

                if (sourceElement instanceof org.eclipse.uml2.uml.Actor
                    || targetElement instanceof org.eclipse.uml2.uml.Actor) {
                    continue;
                }

                for (AbstractView node : classDiagram.getNodeList()) {
                    if (node.getUmlModel().equals(sourceElement)) {
                        source = (AbstractNode) node;
                    }

                    if (node.getUmlModel().equals(targetElement)) {
                        target = (AbstractNode) node;
                    }
                }

                if (sourceTargetList.get(source) == null) {
                    targetList = new ArrayList<AbstractNode>();

                    targetList.add(target);

                    sourceTargetList.put(source, targetList);
                } else {
                    targetList = sourceTargetList.get(source);

                    if (targetList.contains(target)) {
                        continue;
                    }
                }

                // 기존에 Dependecy가 있는지 확인한다.
                dependency = getDependency(sourceElement, targetElement);
                if (dependency == null) {
                    dependency = UMLFactory.eINSTANCE.createDependency();
                    UMLManager.setDependency(dependency, targetElement, sourceElement);
                }

                for (AbstractConnection connection : classDiagram.getConnectionList()) {
                    if (connection.getUmlModel() instanceof Dependency) {
                        if (connection.getSource().getUmlModel().equals(sourceElement)
                            && connection.getTarget().getUmlModel().equals(targetElement)) {
                            isPossibleConnect = false;
                        }
                    }
                }

                if (isPossibleConnect) {
                    relation = UMLDiagramFactory.eINSTANCE.createRelation();
                    relation.setUmlModel(dependency);
                    relation.setRelationType(RelationType.DEPENDENCY);
                    relation.setSource((AbstractView) source);
                    relation.setTarget((AbstractView) target);
                    
                    //Label 달아주기.
                    // Label Compartment
                    labelNode = UMLDiagramFactory.eINSTANCE.createLabelNode();
                    labelNode.setType(LabelType.COMPARTMENT);
                    labelNode.setUmlModel(dependency);

                    labelNode.setParent(relation);
                    labelNode.setX(0);
                    labelNode.setY(0);
                    labelNode.setWidth(0);
                    
                    relation.getLabels().add(labelNode);

                    classDiagram.getConnectionList().add(relation);
                    DiagramUtil.attachSourceOfConnection(relation);
                    DiagramUtil.attachTargetOfConnection(relation);
                }
            } catch (Exception e) {
                Log.error(e);
            }
        }
    }

    /**
     * 기존에 존재하는 Dependency를 가져온다.
     * 
     * @param sourceElement
     * @param targetElement
     * @return Dependency
     */
    private static Dependency getDependency(NamedElement sourceElement, NamedElement targetElement) {
        EList<Dependency> sourceDependencies = sourceElement.getClientDependencies();
        for (Dependency sourceDependency : sourceDependencies) {
            EList<NamedElement> suppliers = sourceDependency.getSuppliers();
            for (NamedElement element : suppliers) {
                if (targetElement.equals(element)) {
                    return sourceDependency;
                }
            }
        }
        EList<Dependency> targetDependencies = targetElement.getClientDependencies();
        for (Dependency targetDependency : targetDependencies) {
            EList<NamedElement> clients = targetDependency.getClients();
            for (NamedElement element : clients) {
                if (targetElement.equals(element)) {
                    return targetDependency;
                }
            }
        }
        return null;
    }

    /**
     * relation을 가지고 있는지 검사
     * 
     * @param sourceElement
     * @param targetElement
     * @return boolean
     */
    private static boolean hasRelation(NamedElement sourceElement, NamedElement targetElement) {
        EList<Relationship> sourceRelationships = sourceElement.getRelationships();
        for (Relationship sourceRelationship : sourceRelationships) {
            if (checkRelationTarget(sourceRelationship, targetElement)) {
                return true;
            }
        }
        EList<Relationship> targetRelationships = targetElement.getRelationships();
        for (Relationship targetRelationship : targetRelationships) {
            if (checkRelationTarget(targetRelationship, sourceElement)) {
                return true;
            }
        }
        return false;
    }

    /**
     * relation의 source와 target이 같은지 검사
     * 
     * @param relationship
     * @param sourceElement
     * @param targetElement
     * @return boolean
     */
    private static boolean checkRelationTarget(Relationship relationship, NamedElement targetElement) {
        if (relationship instanceof Dependency) {
            Dependency dependency = (Dependency) relationship;
            EList<NamedElement> suppliers = dependency.getSuppliers();
            for (NamedElement element : suppliers) {
                if (targetElement.equals(element)) {
                    return true;
                }
            }

        } else if (relationship instanceof Generalization) {
            Generalization generalization = (Generalization) relationship;
            if (targetElement.equals(generalization.getGeneral())) {
                return true;
            }

        } else if (relationship instanceof Association) {
            Association association = (Association) relationship;
            EList<Type> endTypes = association.getEndTypes();
            for (Type type : endTypes) {
                if (targetElement.equals(type)) {
                    return true;
                }
            }

        }

        return false;
    }

    /**
     * @see nexcore.tool.mda.core.infrastructure.handler.INotationModelHandler#setElementList(java.util.List)
     */
    public void setElementList(List<Element> selectedElementList) {
        this.selectedElementList = selectedElementList;
    }

    /**
     * @see nexcore.tool.mda.core.infrastructure.handler.INotationModelHandler#setRuleSet(nexcore.tool.mda.core.infrastructure.model.designer.transformation.RuleSet)
     */
    public void setRuleSet(RuleSet ruleSet) {
        this.ruleSet = ruleSet;
    }

    /**
     * @see nexcore.tool.mda.core.infrastructure.handler.INotationModelHandler#setProgressMonitor(org.eclipse.core.runtime.IProgressMonitor)
     */
    public void setProgressMonitor(IProgressMonitor monitor) {
        this.monitor = monitor;
    }

    /**
     * 생성자
     */
    public UMLModelerNotationModelHandler() {
        // TODO Auto-generated constructor stub
    }

    /**
     * 다이어그램 생성하는 메소드
     * 
     * @param collabo
     * @param diagramName
     * @param diagramType
     * @return Diagram
     */
    /*
     * public Diagram createDiagram(Collaboration collabo, String diagramName,
     * DiagramType diagramType) { // TODO Auto-generated method stub return
     * null; }
     */

    /**
     * 다이어그램 생성하는 메소드
     * 
     * @param intact
     * @param name
     * @param sequence_diagram
     *            void
     */
    /*
     * public void createDiagram(Interaction intact, String name, DiagramType
     * sequence_diagram) { // TODO Auto-generated method stub
     * 
     * }
     */

    /**
     * createDiagram
     * 
     * @param parent
     * @param diagramName
     * @param diagramType
     * @return EObject
     */
    public static Diagram createDiagram(Element parent, String diagramName, DiagramType diagramType) {
        EAnnotation diagramAnnotation = ((Element) parent).getEAnnotation(ManagerConstant.UMLDOMAIN_CONSTANT__DIAGRAM_ANNOTATION_NAME);

        if (diagramAnnotation == null) {
            diagramAnnotation = ((Element) parent).createEAnnotation(ManagerConstant.UMLDOMAIN_CONSTANT__DIAGRAM_ANNOTATION_NAME);
            ((Element) parent).getEAnnotations().add(diagramAnnotation);
        }

        Diagram diagram = UMLDiagramFactory.eINSTANCE.createDiagram();

        diagram.setName(diagramName);
        diagram.setType(diagramType);
        diagram.setId(UUID.randomUUID().toString());

        if (diagramAnnotation.getContents().contains(diagram)) {
            diagramAnnotation.getContents().remove(diagram);
        }

        diagramAnnotation.getContents().add(diagram);
        diagram.setParent(parent);

        return diagram;
    }

    /**
     * @see nexcore.tool.mdd.core.extension.INotationModelHandler#createDiagram(org.eclipse.uml2.uml.Interaction,
     *      java.lang.String, nexcore.tool.uml.model.umldiagram.DiagramType)
     */
    public Diagram createDiagram(Interaction intact, String diagramName, DiagramType diagramType) {
        return createDiagram((Element) intact, diagramName, diagramType);
    }

}
