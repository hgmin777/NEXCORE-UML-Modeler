/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */

package nexcore.tool.uml.manager;

import java.util.UUID;

import nexcore.tool.uml.core.message.UMLMessage;
import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.DiagramType;
import nexcore.tool.uml.model.umldiagram.NotationNode;
import nexcore.tool.uml.model.umldiagram.UMLDiagramFactory;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.uml2.uml.Abstraction;
import org.eclipse.uml2.uml.AcceptCallAction;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.AddStructuralFeatureValueAction;
import org.eclipse.uml2.uml.AddVariableValueAction;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.BroadcastSignalAction;
import org.eclipse.uml2.uml.ClearAssociationAction;
import org.eclipse.uml2.uml.ClearStructuralFeatureAction;
import org.eclipse.uml2.uml.ClearVariableAction;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.ConditionalNode;
import org.eclipse.uml2.uml.ControlFlow;
import org.eclipse.uml2.uml.CreateLinkAction;
import org.eclipse.uml2.uml.CreateLinkObjectAction;
import org.eclipse.uml2.uml.CreateObjectAction;
import org.eclipse.uml2.uml.DestroyLinkAction;
import org.eclipse.uml2.uml.DestroyObjectAction;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.Extend;
import org.eclipse.uml2.uml.Include;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.LoopNode;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageOccurrenceSpecification;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.ObjectFlow;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PartDecomposition;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.ReadExtentAction;
import org.eclipse.uml2.uml.ReadIsClassifiedObjectAction;
import org.eclipse.uml2.uml.ReadLinkAction;
import org.eclipse.uml2.uml.ReadLinkObjectEndAction;
import org.eclipse.uml2.uml.ReadLinkObjectEndQualifierAction;
import org.eclipse.uml2.uml.ReadSelfAction;
import org.eclipse.uml2.uml.ReadStructuralFeatureAction;
import org.eclipse.uml2.uml.ReadVariableAction;
import org.eclipse.uml2.uml.ReceiveOperationEvent;
import org.eclipse.uml2.uml.ReceiveSignalEvent;
import org.eclipse.uml2.uml.Reception;
import org.eclipse.uml2.uml.ReclassifyObjectAction;
import org.eclipse.uml2.uml.RemoveStructuralFeatureValueAction;
import org.eclipse.uml2.uml.RemoveVariableValueAction;
import org.eclipse.uml2.uml.ReplyAction;
import org.eclipse.uml2.uml.SendObjectAction;
import org.eclipse.uml2.uml.SendOperationEvent;
import org.eclipse.uml2.uml.StartClassifierBehaviorAction;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.TestIdentityAction;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.Usage;
import org.eclipse.uml2.uml.VisibilityKind;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.manager</li>
 * <li>서브 업무명 : nexcore.tool.uml.manager</li>
 * <li>설 명 : UMLHelper</li>
 * <li>작성일 : 2009. 12. 1.</li>
 * <li>작성자 : 한승일</li>
 * </ul>
 */
public class UMLHelper {

    /**
     * xml에서 eAnnotation으로 다이어그램이 지정되는데 eAnnotation의 source특성이 Diagram으로 되어 있는
     * 부분에 다이어그램이 저장됨
     */
    public final static String DIAGRAM_SOURCE_NAME = "Diagram";

    /** umlFactory */
    private final static UMLFactory FACTORY = UMLPackage.eINSTANCE.getUMLFactory();

    /** sourceAssociationEndIndex */
    public static final int SUPPLIER_ASSOCIATION_END_INDEX = 0;

    /** targetAssociationEndIndex */
    public static final int CLIENT_ASSOCIATION_END_INDEX = 1;

    /** ㅣㄴ이름을 위한 변수 */
    private static final String EMPTY_NAME = "";

    /**
     * <ul>
     * <li>업무 그룹명 : nexcore.tool.uml.manager</li>
     * <li>서브 업무명 : nexcore.tool.uml.manager</li>
     * <li>설 명 : DirectedType</li>
     * <li>작성일 : 2009. 12. 1.</li>
     * <li>작성자 : 한승일</li>
     * </ul>
     */
    public enum DirectedType {
        BINARY, DIRECTED, NONE
    }

    /**
     * container node생성과 ID설정
     * 
     * @return ContainerNode
     */
    public static ContainerNode createContainerNode() {
        ContainerNode containerNode = UMLDiagramFactory.eINSTANCE.createContainerNode();
        containerNode.setId(UUID.randomUUID().toString());
        return containerNode;
    }

    /**
     * notation node생성과 ID설정
     * 
     * @return NotationNode
     */
    public static NotationNode ceateNotationNode() {
        NotationNode notationNode = UMLDiagramFactory.eINSTANCE.createNotationNode();
        notationNode.setId(UUID.randomUUID().toString());
        return notationNode;
    }

    /**
     * Container 타입인지 구분
     * 
     * @param classType
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    public static final boolean isContainerNode(Class classType) {
        if (classType.equals(org.eclipse.uml2.uml.Activity.class)
            || classType.equals(org.eclipse.uml2.uml.StateMachine.class)
            || classType.equals(org.eclipse.uml2.uml.Interaction.class)
            || classType.equals(org.eclipse.uml2.uml.ActivityPartition.class)
            || classType.equals(org.eclipse.uml2.uml.StructuredActivityNode.class)
            || classType.equals(org.eclipse.uml2.uml.CombinedFragment.class)
            || classType.equals(org.eclipse.uml2.uml.Region.class)
            || classType.equals(org.eclipse.uml2.uml.Component.class)
            || classType.equals(org.eclipse.uml2.uml.State.class)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * NotationNode 타입인지 구분
     * 
     * @param classType
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    public static final boolean isNotationNode(Class classType) {
        if (classType.equals(org.eclipse.uml2.uml.AcceptEventAction.class)
            || classType.equals(org.eclipse.uml2.uml.ActivityFinalNode.class)
            || classType.equals(org.eclipse.uml2.uml.ActivityParameterNode.class)
            || classType.equals(org.eclipse.uml2.uml.CallBehaviorAction.class)
            || classType.equals(org.eclipse.uml2.uml.CallOperationAction.class)
            || classType.equals(org.eclipse.uml2.uml.CentralBufferNode.class)
            || classType.equals(org.eclipse.uml2.uml.DataStoreNode.class)
            || classType.equals(org.eclipse.uml2.uml.DecisionNode.class)
            || classType.equals(org.eclipse.uml2.uml.ExpansionNode.class)
            || classType.equals(org.eclipse.uml2.uml.FlowFinalNode.class)
            || classType.equals(org.eclipse.uml2.uml.ForkNode.class)
            || classType.equals(org.eclipse.uml2.uml.InitialNode.class)
            || classType.equals(org.eclipse.uml2.uml.InputPin.class)
            || classType.equals(org.eclipse.uml2.uml.JoinNode.class)
            || classType.equals(org.eclipse.uml2.uml.MergeNode.class)
            || classType.equals(org.eclipse.uml2.uml.OpaqueAction.class)
            || classType.equals(org.eclipse.uml2.uml.OutputPin.class)
            || classType.equals(org.eclipse.uml2.uml.SendSignalAction.class)
            || classType.equals(org.eclipse.uml2.uml.ValuePin.class)
            || classType.equals(org.eclipse.uml2.uml.Artifact.class)
            || classType.equals(org.eclipse.uml2.uml.Class.class)
            || classType.equals(org.eclipse.uml2.uml.Collaboration.class)
            || classType.equals(org.eclipse.uml2.uml.DataType.class)
            || classType.equals(org.eclipse.uml2.uml.Enumeration.class)
            || classType.equals(org.eclipse.uml2.uml.Interface.class)
            || classType.equals(org.eclipse.uml2.uml.Package.class)
            || classType.equals(org.eclipse.uml2.uml.Signal.class)
            || classType.equals(org.eclipse.uml2.uml.DestructionEvent.class)
            || classType.equals(org.eclipse.uml2.uml.InteractionUse.class)
            || classType.equals(org.eclipse.uml2.uml.Lifeline.class)
            || classType.equals(org.eclipse.uml2.uml.Message.class)
            || classType.equals(org.eclipse.uml2.uml.Pseudostate.class)
            || classType.equals(org.eclipse.uml2.uml.FinalState.class)
            || classType.equals(org.eclipse.uml2.uml.Region.class)
            || classType.equals(org.eclipse.uml2.uml.StateInvariant.class)
            || classType.equals(org.eclipse.uml2.uml.Transition.class)
            || classType.equals(org.eclipse.uml2.uml.CollaborationUse.class)
            || classType.equals(org.eclipse.uml2.uml.Property.class)
            || classType.equals(org.eclipse.uml2.uml.Port.class) || classType.equals(org.eclipse.uml2.uml.Actor.class)
            || classType.equals(org.eclipse.uml2.uml.ElementImport.class)
            || classType.equals(org.eclipse.uml2.uml.Generalization.class)
            || classType.equals(org.eclipse.uml2.uml.UseCase.class)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * createProfile
     *   void
     */
    public static void createProfile() {
        // Profile profile = FACTORY.createPrimitiveType();
    }

    /**
     * 
     * 
     * @param parent
     *            : 다이어그램이 위치해야할 상위 Element
     * @param diagramType
     *            : 생성할 다이어그램 타입
     * @param diagramName
     *            : 다이어그램 이름 명
     * @return Diagram
     */
    public static Diagram createDiagram(org.eclipse.uml2.uml.PackageableElement parent, DiagramType diagramType,
                                           String diagramName) {
        EAnnotation eAnnotation = parent.getEAnnotation(DIAGRAM_SOURCE_NAME);
        if (null == eAnnotation) {
            eAnnotation = parent.createEAnnotation(DIAGRAM_SOURCE_NAME);
            parent.getEAnnotations().add(eAnnotation);
        }
        Diagram diagram = UMLDiagramFactory.eINSTANCE.createDiagram();
        diagram.setId(UUID.randomUUID().toString());
        diagram.setType(diagramType);
        diagram.setName(diagramName);
        diagram.setParent(parent);

        eAnnotation.getContents().add(diagram);
        return diagram;
    }

    /**
     * setDiagram
     *  
     * @param parent
     * @param diagram void
     */
    public static void setDiagram(org.eclipse.uml2.uml.PackageableElement parent, Diagram diagram) {
        EAnnotation eAnnotation = parent.getEAnnotation(DIAGRAM_SOURCE_NAME);
        if (null == eAnnotation) {
            eAnnotation = parent.createEAnnotation(DIAGRAM_SOURCE_NAME);
            parent.getEAnnotations().add(eAnnotation);
        }
        eAnnotation.getContents().add(diagram);
    }

    /**
     * 다이어그램이 위치할 부모 타입이 Activity타입인경우 다이어그램 생성 그렇지 않은 경우 Activity를 부모에 생성하고 그
     * 하위에 다이어그램 생성
     * 
     * @param parent
     * @param diagramName
     * @return Diagram
     */
    public static Diagram createActivityDiagram(org.eclipse.uml2.uml.PackageableElement parent, String diagramName) {
        Diagram diagram;

        Activity activity = null;
        if (parent instanceof Activity) {
            activity = (org.eclipse.uml2.uml.Activity) parent;
        } else {
            if (parent instanceof Package) {
                activity = createActivity();
                ((Package) parent).getPackagedElements().add(activity);
            }
        }
        diagram = createDiagram(activity, DiagramType.ACTIVITY_DIAGRAM, diagramName);
        return diagram;
    }

    /**
     * 
     * 다이어그램이 위치할 부모 타입이 Collaboration이나 Interaction이 아닐 경우 상위
     * Element들(Collaboration, Interaction)을 생성하고 그 하위에 다이어그램 생성.
     * 
     * @param parent
     * @param diagramName
     * @return Diagram
     */
    public static Diagram createSequenceDiagram(org.eclipse.uml2.uml.PackageableElement parent, String diagramName) {
        Diagram diagram;

        Collaboration collaboration = null;
        Interaction interaction = null;
        Package pack = null;
        if (parent instanceof Interaction) {
            interaction = (Interaction) parent;
        } else if (parent instanceof Collaboration) {
            collaboration = (Collaboration) parent;
            interaction = createInteraction(getSequenceDiagramUniqueName(parent, parent.getName()));
            collaboration.getOwnedBehaviors().add(interaction);
        } else if (parent instanceof Package) {
            pack = (Package) parent;
            interaction = createInteraction(getSequenceDiagramUniqueName(parent, parent.getName()));
            collaboration = createCollaboration(getSequenceDiagramUniqueName(parent, parent.getName()));
            pack.getPackagedElements().add(collaboration);
            collaboration.getOwnedBehaviors().add(interaction);
        } else if (parent instanceof Component) {
            Component component = (Component) parent;
            interaction = createInteraction(getSequenceDiagramUniqueName(parent, parent.getName()));
            collaboration = createCollaboration(getSequenceDiagramUniqueName(parent, parent.getName()));
            component.getPackagedElements().add(collaboration);
            collaboration.getOwnedBehaviors().add(interaction);
        }
        diagram = createDiagram(interaction, DiagramType.SEQUENCE_DIAGRAM, interaction.getName());
        return diagram;
    }

    /**
     * 
     * 
     * @param umlClassdiagram
     * @return String
     */
    private static String getSequenceDiagramUniqueName(Element parent, String parentName) {
        int uniqueIndex = 0;
        uniqueIndex = getSequenceUniqueIndex(uniqueIndex, parent, parentName);
        if (uniqueIndex == 0) {
            return parentName;
        } else {
            return parentName + uniqueIndex;
        }
    }

    /**
     * 
     * 
     * @param uniqueIndex
     * @return int
     */
    private static int getSequenceUniqueIndex(int uniqueIndex, Element parent, String parentName) {

        for (Element child : parent.getOwnedElements()) {

            if (child instanceof NamedElement) {
                NamedElement namedChild = (NamedElement) child;

                if (uniqueIndex == 0) {
                    if (parentName.equals(namedChild.getName())) {
                        uniqueIndex++;
                        uniqueIndex = getSequenceUniqueIndex(uniqueIndex, parent, parentName);
                    }
                } else {
                    if ((parentName + uniqueIndex).equals(namedChild.getName())) {
                        uniqueIndex++;
                        uniqueIndex = getSequenceUniqueIndex(uniqueIndex, parent, parentName);
                    }
                }
            }
        }
        return uniqueIndex;
    }

    /**
     * 
     * 
     * @param parent
     * @param diagramName
     * @return Diagram
     */
    public static Diagram createComponentDiagram(org.eclipse.uml2.uml.PackageableElement parent, String diagramName) {
        return createDiagram(parent, DiagramType.COMPONENT_DIAGRAM, diagramName);
    }

    /**
     * 
     * 
     * @param parent
     * @param diagramName
     * @return Diagram
     */
    public static Diagram createClassDiagram(org.eclipse.uml2.uml.PackageableElement parent, String diagramName) {
        return createDiagram(parent, DiagramType.CLASS_DIAGRAM, diagramName);
    }

    /**
     * 
     * 
     * @param parent
     * @param diagramName
     * @return Diagram
     */
    public static Diagram createUseCaseDiagram(org.eclipse.uml2.uml.PackageableElement parent, String diagramName) {

        return createDiagram(parent, DiagramType.USE_CASE_DIAGRAM, diagramName);
    }

    /*
     * Container Type Creation
     */

    /**
     * 
     * @return org.eclipse.uml2.uml.Activity
     */
    public static org.eclipse.uml2.uml.Activity createActivity() {
        org.eclipse.uml2.uml.Activity element = FACTORY.createActivity();
        element.setName(UMLMessage.getMessage(UMLMessage.UML_ACTIVITY));
        return element;
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.ActivityPartition
     */
    public static org.eclipse.uml2.uml.ActivityPartition createActivityPartition() {
        org.eclipse.uml2.uml.ActivityPartition activityPartition = FACTORY.createActivityPartition();
        activityPartition.setName(UMLMessage.getMessage(UMLMessage.UML_ACTIVITYPARTITION));
        return activityPartition;
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.CombinedFragment
     */
    public static org.eclipse.uml2.uml.CombinedFragment createCombinedFragment() {
        org.eclipse.uml2.uml.CombinedFragment combinedFragment = FACTORY.createCombinedFragment();
        combinedFragment.setName(UMLMessage.getMessage(UMLMessage.UML_COMBINEDFRAGMENT));
        return combinedFragment;
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.State
     */
    public static org.eclipse.uml2.uml.State createState() {
        org.eclipse.uml2.uml.State state = FACTORY.createState();
        state.setName(UMLMessage.getMessage(UMLMessage.UML_STATE));
        return state;
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.StructuredActivityNode
     */
    public static org.eclipse.uml2.uml.StructuredActivityNode createStructuredActivityNode() {
        org.eclipse.uml2.uml.StructuredActivityNode structuredActivityNode = FACTORY.createStructuredActivityNode();
        structuredActivityNode.setName(UMLMessage.getMessage(UMLMessage.UML_STRUCTUREDACTIVITYNODE));
        return structuredActivityNode;
    }

    /*
     * Notation Type Creation
     */

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.AcceptEventAction
     */
    public static org.eclipse.uml2.uml.AcceptEventAction createAcceptEventAction() {
        org.eclipse.uml2.uml.AcceptEventAction acceptEventAction = FACTORY.createAcceptEventAction();
        acceptEventAction.setName(UMLMessage.getMessage(UMLMessage.UML_ACCEPTEVENTACTION));
        return acceptEventAction;
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.ActivityFinalNode
     */
    public static org.eclipse.uml2.uml.ActivityFinalNode createActivityFinalNode() {
        org.eclipse.uml2.uml.ActivityFinalNode activityFinalNode = FACTORY.createActivityFinalNode();
        // activityFinalNode.setName(UMLMessage.getMessage(UMLMessage.UML_ACTIVITYFINALNODE));
        activityFinalNode.setName(UMLMessage.getMessage(EMPTY_NAME));
        return activityFinalNode;
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.ActivityParameterNode
     */
    public static org.eclipse.uml2.uml.ActivityParameterNode createActivityParameterNode() {
        org.eclipse.uml2.uml.ActivityParameterNode activityParameterNode = FACTORY.createActivityParameterNode();
        // activityParameterNode.setName(UMLMessage.getMessage(UMLMessage.UML_ACTIVITYPARAMETERNODE));
        activityParameterNode.setName(UMLMessage.getMessage(EMPTY_NAME));
        return activityParameterNode;
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.Actor
     */
    public static org.eclipse.uml2.uml.Actor createActor() {
        org.eclipse.uml2.uml.Actor actor = FACTORY.createActor();
        actor.setName(UMLMessage.getMessage(UMLMessage.UML_ACTOR));
        return actor;
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.Artifact
     */
    public static org.eclipse.uml2.uml.Artifact createArtifact() {
        org.eclipse.uml2.uml.Artifact artifact = FACTORY.createArtifact();
        artifact.setName(UMLMessage.getMessage(UMLMessage.UML_ARTIFACT));
        return artifact;
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.CallBehaviorAction
     */
    public static org.eclipse.uml2.uml.CallBehaviorAction createCallBehaviorAction() {
        org.eclipse.uml2.uml.CallBehaviorAction callBehaviorAction = FACTORY.createCallBehaviorAction();
        callBehaviorAction.setName(UMLMessage.getMessage(UMLMessage.UML_CALLBEHAVIORACTION));
        return callBehaviorAction;
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.CallOperationAction
     */
    public static org.eclipse.uml2.uml.CallOperationAction createCallOperationAction() {
        org.eclipse.uml2.uml.CallOperationAction callOperationAction = FACTORY.createCallOperationAction();
        callOperationAction.setName(UMLMessage.getMessage(UMLMessage.UML_CALLOPERATIONACTION));
        return callOperationAction;
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.CentralBufferNode
     */
    public static org.eclipse.uml2.uml.CentralBufferNode createCentralBufferNode() {
        org.eclipse.uml2.uml.CentralBufferNode centralBufferNode = FACTORY.createCentralBufferNode();
        centralBufferNode.setName(UMLMessage.getMessage(UMLMessage.UML_CENTRALBUFFERNODE));
        return centralBufferNode;
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.Class
     */
    public static org.eclipse.uml2.uml.Class createClass() {
        org.eclipse.uml2.uml.Class classElement = FACTORY.createClass();
        classElement.setName(UMLMessage.getMessage(UMLMessage.UML_CLASS));
        return classElement;
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.Collaboration
     */
    public static org.eclipse.uml2.uml.Collaboration createCollaboration() {
        org.eclipse.uml2.uml.Collaboration collaboration = FACTORY.createCollaboration();
        collaboration.setName(UMLMessage.getMessage(UMLMessage.UML_COLLABORATION));
        return collaboration;
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.Collaboration
     */
    public static org.eclipse.uml2.uml.Collaboration createCollaboration(String name) {
        org.eclipse.uml2.uml.Collaboration collaboration = FACTORY.createCollaboration();
        collaboration.setName(name);
        return collaboration;
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.CollaborationUse
     */
    public static org.eclipse.uml2.uml.CollaborationUse createCollaborationUse() {
        org.eclipse.uml2.uml.CollaborationUse collaborationUse = FACTORY.createCollaborationUse();
        collaborationUse.setName(UMLMessage.getMessage(UMLMessage.UML_COLLABORATIONUSE));
        return collaborationUse;
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.Component
     */
    public static org.eclipse.uml2.uml.Component createComponent() {
        org.eclipse.uml2.uml.Component component = FACTORY.createComponent();
        component.setName(UMLMessage.getMessage(UMLMessage.UML_COMPONENT));
        return component;
    }
    
    /**
     * @param name
     * @return org.eclipse.uml2.uml.Component
     */
    public static Component createComponent(String name) {
    	Component component = FACTORY.createComponent();
    	component.setName(name);
    	return component;
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.DataStoreNode
     */
    public static org.eclipse.uml2.uml.DataStoreNode createDataStoreNode() {
        org.eclipse.uml2.uml.DataStoreNode dataStoreNode = FACTORY.createDataStoreNode();
        dataStoreNode.setName(UMLMessage.getMessage(UMLMessage.UML_DATASTORENODE));
        return dataStoreNode;
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.DataType
     */
    public static org.eclipse.uml2.uml.DataType createDataType() {
        org.eclipse.uml2.uml.DataType dataType = FACTORY.createDataType();
        dataType.setName(UMLMessage.getMessage(UMLMessage.UML_DATATYPE));
        return dataType;
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.DecisionNode
     */
    public static org.eclipse.uml2.uml.DecisionNode createDecisionNode() {
        org.eclipse.uml2.uml.DecisionNode decisionNode = FACTORY.createDecisionNode();
        // decisionNode.setName(UMLMessage.getMessage(UMLMessage.UML_DECISIONNODE));
        decisionNode.setName(UMLMessage.getMessage(EMPTY_NAME));
        return decisionNode;
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.DestructionEvent
     */
    public static org.eclipse.uml2.uml.DestructionEvent createDestructionEvent() {
        org.eclipse.uml2.uml.DestructionEvent destructionEvent = FACTORY.createDestructionEvent();
        destructionEvent.setName(UMLMessage.getMessage(UMLMessage.UML_DESTRUCTIONEVENT));
        return destructionEvent;
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.ElementImport
     */
    public static org.eclipse.uml2.uml.ElementImport createElementImport() {
        return FACTORY.createElementImport();
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.Enumeration
     */
    public static org.eclipse.uml2.uml.Enumeration createEnumeration() {
        org.eclipse.uml2.uml.Enumeration enumeration = FACTORY.createEnumeration();
        enumeration.setName(UMLMessage.getMessage(UMLMessage.UML_ENUMERATION));
        return enumeration;
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.ExpansionNode
     */
    public static org.eclipse.uml2.uml.ExpansionNode createExpansionNode() {
        return FACTORY.createExpansionNode();
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.FlowFinalNode
     */
    public static org.eclipse.uml2.uml.FlowFinalNode createFlowFinalNode() {
        org.eclipse.uml2.uml.FlowFinalNode flowFinalNode = FACTORY.createFlowFinalNode();
        flowFinalNode.setName(UMLMessage.getMessage(UMLMessage.UML_FLOWFINALNODE));
        return flowFinalNode;
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.ForkNode
     */
    public static org.eclipse.uml2.uml.ForkNode createForkNode() {
        org.eclipse.uml2.uml.ForkNode forkNode = FACTORY.createForkNode();
        // forkNode.setName(UMLMessage.getMessage(UMLMessage.UML_FORKNODE));
        forkNode.setName(UMLMessage.getMessage(EMPTY_NAME));
        return forkNode;
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.Generalization
     */
    public static org.eclipse.uml2.uml.Generalization createGeneralization() {
        return FACTORY.createGeneralization();
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.InitialNode
     */
    public static org.eclipse.uml2.uml.InitialNode createInitialNode() {
        org.eclipse.uml2.uml.InitialNode initialNode = FACTORY.createInitialNode();
        // initialNode.setName(UMLMessage.getMessage(UMLMessage.UML_INITIALNODE));
        initialNode.setName(UMLMessage.getMessage(EMPTY_NAME));
        return initialNode;
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.InputPin
     */
    public static org.eclipse.uml2.uml.InputPin createInputPin() {
        return FACTORY.createInputPin();
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.Interaction
     */
    public static org.eclipse.uml2.uml.Interaction createInteraction() {
        org.eclipse.uml2.uml.Interaction interaction = FACTORY.createInteraction();
        interaction.setName(UMLMessage.getMessage(UMLMessage.UML_INTERACTION));
        return interaction;
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.Interaction
     */
    public static org.eclipse.uml2.uml.Interaction createInteraction(String name) {
        org.eclipse.uml2.uml.Interaction interaction = FACTORY.createInteraction();
        interaction.setName(name);
        return interaction;
    }
    
    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.InteractionUse
     */
    public static org.eclipse.uml2.uml.InteractionUse createInteractionUse() {
        org.eclipse.uml2.uml.InteractionUse interactionUse = FACTORY.createInteractionUse();
        interactionUse.setName(UMLMessage.getMessage(UMLMessage.UML_INTERACTIONUSE));
        return interactionUse;
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.Interface
     */
    public static org.eclipse.uml2.uml.Interface createInterface() {
        org.eclipse.uml2.uml.Interface interfaceElement = FACTORY.createInterface();
        interfaceElement.setName(UMLMessage.getMessage(UMLMessage.UML_INTERFACE));
        return interfaceElement;
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.JoinNode
     */
    public static org.eclipse.uml2.uml.JoinNode createJoinNode() {
        org.eclipse.uml2.uml.JoinNode joinNode = FACTORY.createJoinNode();
        // joinNode.setName(UMLMessage.getMessage(UMLMessage.UML_JOINNODE));
        joinNode.setName(UMLMessage.getMessage(EMPTY_NAME));
        return joinNode;
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.Lifeline
     */
    public static org.eclipse.uml2.uml.Lifeline createLifeline() {
        org.eclipse.uml2.uml.Lifeline lifeline = FACTORY.createLifeline();
        lifeline.setName(UMLMessage.getMessage(UMLMessage.UML_LIFELINE));

        return lifeline;
    }

    /**
     * UML Model 생성
     * 
     * @return org.eclipse.uml2.uml.Model
     */
    public static org.eclipse.uml2.uml.Model createModel() {
        return createModel(UMLMessage.getMessage(UMLMessage.UML_MODEL));
    }

    /**
     * 
     * 
     * @param modelName
     * @return org.eclipse.uml2.uml.Model
     */
    public static org.eclipse.uml2.uml.Model createModel(String modelName) {
        org.eclipse.uml2.uml.Model model = FACTORY.createModel();
        model.setName(modelName);
        return model;
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.MergeNode
     */
    public static org.eclipse.uml2.uml.MergeNode createMergeNode() {
        org.eclipse.uml2.uml.MergeNode mergeNode = FACTORY.createMergeNode();
        // mergeNode.setName(UMLMessage.getMessage(UMLMessage.UML_MERGENODE));
        mergeNode.setName(UMLMessage.getMessage(EMPTY_NAME));
        return mergeNode;
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.Message
     */
    public static org.eclipse.uml2.uml.Message createMessage() {
        Message message = FACTORY.createMessage();

        SendOperationEvent sendOperatonEvent = FACTORY.createSendOperationEvent();

        MessageOccurrenceSpecification senderMessageOccurrenceSpecification = FACTORY.createMessageOccurrenceSpecification();
        senderMessageOccurrenceSpecification.setMessage(message);
        senderMessageOccurrenceSpecification.setEvent(sendOperatonEvent);

        ReceiveOperationEvent receiveOperatonEvent = FACTORY.createReceiveOperationEvent();

        MessageOccurrenceSpecification receiveMessageOccurrenceSpecification = FACTORY.createMessageOccurrenceSpecification();
        receiveMessageOccurrenceSpecification.setMessage(message);
        receiveMessageOccurrenceSpecification.setEvent(receiveOperatonEvent);

        message.setName(UMLMessage.getMessage(UMLMessage.UML_MESSAGE));

        return message;
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.OpaqueAction
     */
    public static org.eclipse.uml2.uml.OpaqueAction createOpaqueAction() {
        org.eclipse.uml2.uml.OpaqueAction opaqueAction = FACTORY.createOpaqueAction();
        opaqueAction.setName(UMLMessage.getMessage(UMLMessage.UML_OPAQUEACTION));
        return opaqueAction;
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.OutputPin
     */
    public static org.eclipse.uml2.uml.OutputPin createOutputPin() {
        return FACTORY.createOutputPin();
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.Package
     */
    public static org.eclipse.uml2.uml.Package createPackage() {
        org.eclipse.uml2.uml.Package element = FACTORY.createPackage();
        element.setName(UMLMessage.getMessage(UMLMessage.UML_PACKAGE));
        return element;
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.Property
     */
    public static org.eclipse.uml2.uml.Property createProperty() {
        return createProperty(EMPTY_NAME);
    }

    /**
     * 
     * Propertuy 생성
     * 
     * @param name
     * @return org.eclipse.uml2.uml.Property
     */
    public static org.eclipse.uml2.uml.Property createProperty(String name) {
        org.eclipse.uml2.uml.Property property = FACTORY.createProperty();
        property.setName(name);
        property.setVisibility(VisibilityKind.PRIVATE_LITERAL);
        property.setLower(1);
        property.setUpper(1);
        property.setAggregation(AggregationKind.NONE_LITERAL);
        return property;
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.Pseudostate
     */
    public static org.eclipse.uml2.uml.Pseudostate createPseudostate() {
        return FACTORY.createPseudostate();
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.Region
     */
    public static org.eclipse.uml2.uml.Region createRegion() {
        return FACTORY.createRegion();
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.SendSignalAction
     */
    public static org.eclipse.uml2.uml.SendSignalAction createSendSignalAction() {
        org.eclipse.uml2.uml.SendSignalAction sendSignalAction = FACTORY.createSendSignalAction();
        sendSignalAction.setName(UMLMessage.getMessage(UMLMessage.UML_SENDSIGNALACTION));
        return sendSignalAction;
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.Signal
     */
    public static org.eclipse.uml2.uml.Signal createSignal() {
        org.eclipse.uml2.uml.Signal signal = FACTORY.createSignal();
        signal.setName(UMLMessage.getMessage(UMLMessage.UML_SIGNAL));
        return signal;
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.StateInvariant
     */
    public static org.eclipse.uml2.uml.StateInvariant createStateInvariant() {
        org.eclipse.uml2.uml.StateInvariant stateInvariant = FACTORY.createStateInvariant();
        stateInvariant.setName(UMLMessage.getMessage(UMLMessage.UML_STATEINVARIANT));
        return stateInvariant;
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.Transition
     */
    public static org.eclipse.uml2.uml.Transition createTransition() {
        org.eclipse.uml2.uml.Transition transition = FACTORY.createTransition();
        transition.setName(UMLMessage.getMessage(UMLMessage.UML_TRANSITION));
        return transition;
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.UseCase
     */
    public static org.eclipse.uml2.uml.UseCase createUseCase() {
        org.eclipse.uml2.uml.UseCase useCase = FACTORY.createUseCase();
        useCase.setName(UMLMessage.getMessage(UMLMessage.UML_USECASE));
        return useCase;
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.ValuePin
     */
    public static org.eclipse.uml2.uml.ValuePin createValuePin() {
        return FACTORY.createValuePin();
    }

    /*
     * Relation type
     */

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.Abstraction
     */
    public static org.eclipse.uml2.uml.Abstraction createAbstraction() {
        Abstraction abstraction = FACTORY.createAbstraction();
        abstraction.addKeyword("abstraction");
        return abstraction;
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.Association
     */
    public static org.eclipse.uml2.uml.Association createAssociation() {
        Association association = FACTORY.createAssociation();
        association.setName(EMPTY_NAME);
        association.setVisibility(VisibilityKind.PRIVATE_LITERAL);
        return association;
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.ComponentRealization
     */
    public static org.eclipse.uml2.uml.ComponentRealization createComponentRealization() {
        return FACTORY.createComponentRealization();
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.Connector
     */
    public static org.eclipse.uml2.uml.Connector createConnector() {
        return FACTORY.createConnector();
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.ControlFlow
     */
    public static org.eclipse.uml2.uml.ControlFlow createControlFlow() {
        ControlFlow controlFlow = FACTORY.createControlFlow();
        controlFlow.setName(EMPTY_NAME);
        return controlFlow;
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.Dependency
     */
    public static org.eclipse.uml2.uml.Dependency createDependency() {
        org.eclipse.uml2.uml.Dependency dependency = FACTORY.createDependency();
        dependency.setName(EMPTY_NAME);
        dependency.setVisibility(VisibilityKind.PUBLIC_LITERAL);
        return dependency;
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.Extend
     */
    public static org.eclipse.uml2.uml.Extend createExtend() {
        Extend extend = FACTORY.createExtend();
        extend.addKeyword("extend");
        return extend;
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.FinalState
     */
    public static org.eclipse.uml2.uml.FinalState createFinalState() {
        org.eclipse.uml2.uml.FinalState finalState = FACTORY.createFinalState();
        finalState.setName(UMLMessage.getMessage(UMLMessage.UML_FINALSTATE));
        return finalState;
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.Include
     */
    public static org.eclipse.uml2.uml.Include createInclude() {
        Include include = FACTORY.createInclude();
        include.addKeyword("include");
        return include;
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.InterfaceRealization
     */
    public static org.eclipse.uml2.uml.InterfaceRealization createInterfaceRealization() {
        return FACTORY.createInterfaceRealization();
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.ObjectFlow
     */
    public static org.eclipse.uml2.uml.ObjectFlow createObjectFlow() {
        ObjectFlow objectFlow = FACTORY.createObjectFlow();
        objectFlow.setName(EMPTY_NAME);
        return objectFlow;
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.PackageImport
     */
    public static org.eclipse.uml2.uml.PackageImport createPackageImport() {
        return FACTORY.createPackageImport();
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.PackageMerge
     */
    public static org.eclipse.uml2.uml.PackageMerge createPackageMerge() {
        return FACTORY.createPackageMerge();
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.Port
     */
    public static org.eclipse.uml2.uml.Port createPort() {
        org.eclipse.uml2.uml.Port port = FACTORY.createPort();
        port.setName(UMLMessage.getMessage(UMLMessage.UML_PORT));
        return port;
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.Realization
     */
    public static org.eclipse.uml2.uml.Realization createRealization() {
        return FACTORY.createRealization();
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.Substitution
     */
    public static org.eclipse.uml2.uml.Substitution createSubstitution() {
        return FACTORY.createSubstitution();
    }

    /**
     * 
     * 
     * @return org.eclipse.uml2.uml.Usage
     */
    public static org.eclipse.uml2.uml.Usage createUsage() {
        Usage usage = FACTORY.createUsage();
        usage.addKeyword("usage");
        return usage;
    }

    /**
     * 
     * 
     * @return Operation
     */
    public static Operation createOperation() {
        org.eclipse.uml2.uml.Operation operation = FACTORY.createOperation();
        operation.setName(UMLMessage.getMessage(UMLMessage.UML_OPERATION));
        operation.setVisibility(VisibilityKind.PUBLIC_LITERAL);
        return operation;
    }

    /**
     * 
     * 
     * @return PartDecomposition
     */
    public static PartDecomposition createPart() {

        return FACTORY.createPartDecomposition();
    }

    /**
     * 
     * 
     * @return Reception
     */
    public static Reception createReception() {
        org.eclipse.uml2.uml.Reception reception = FACTORY.createReception();
        reception.setName(UMLMessage.getMessage(UMLMessage.UML_RECEPTION));
        return reception;
    }

    /**
     * 
     * 
     * @return PrimitiveType
     */
    public static PrimitiveType createPrimitiveType() {
        org.eclipse.uml2.uml.PrimitiveType primitiveType = FACTORY.createPrimitiveType();
        primitiveType.setName(UMLMessage.getMessage(UMLMessage.UML_PRIMITIVETYPE));
        return primitiveType;
    }

    /**
     * createStateMachine
     *  
     * @return StateMachine
     */
    public static StateMachine createStateMachine() {

        return FACTORY.createStateMachine();
    }

    /**
     * createLoopNode
     *  
     * @return LoopNode
     */
    public static LoopNode createLoopNode() {
        org.eclipse.uml2.uml.LoopNode loopNode = FACTORY.createLoopNode();
        loopNode.setName(UMLMessage.getMessage(UMLMessage.UML_LOOPNODE));
        return loopNode;
    }

    /**
     * createAddVariableValueAction
     *  
     * @return AddVariableValueAction
     */
    public static AddVariableValueAction createAddVariableValueAction() {
        org.eclipse.uml2.uml.AddVariableValueAction addVariableValueAction = FACTORY.createAddVariableValueAction();
        addVariableValueAction.setName(UMLMessage.getMessage(UMLMessage.UML_ADDVARIABLEVALUEACTION));
        return addVariableValueAction;
    }

    /**
     * createClearVariableAction
     *  
     * @return ClearVariableAction
     */
    public static ClearVariableAction createClearVariableAction() {
        org.eclipse.uml2.uml.ClearVariableAction clearVariableAction = FACTORY.createClearVariableAction();
        clearVariableAction.setName(UMLMessage.getMessage(UMLMessage.UML_CLEARVARIABLEACTION));
        return clearVariableAction;
    }

    /**
     * 
     * 
     * @return ReadVariableAction
     */
    public static ReadVariableAction createReadVariableAction() {
        org.eclipse.uml2.uml.ReadVariableAction readVariableAction = FACTORY.createReadVariableAction();
        readVariableAction.setName(UMLMessage.getMessage(UMLMessage.UML_READVARIABLE));
        return readVariableAction;
    }

    /**
     * 
     * 
     * @return RemoveVariableValueAction
     */
    public static RemoveVariableValueAction createRemoveVariableValueAction() {
        org.eclipse.uml2.uml.RemoveVariableValueAction removeVariableValueAction = FACTORY.createRemoveVariableValueAction();
        removeVariableValueAction.setName(UMLMessage.getMessage(UMLMessage.UML_REMOVEVARIABLEACTION));
        return removeVariableValueAction;
    }

    /**
     * 
     * 
     * @return AddStructuralFeatureValueAction
     */
    public static AddStructuralFeatureValueAction createAddStructuralFeatureValueAction() {
        org.eclipse.uml2.uml.AddStructuralFeatureValueAction addStructuralFeatureValueAction = FACTORY.createAddStructuralFeatureValueAction();
        addStructuralFeatureValueAction.setName(UMLMessage.getMessage(UMLMessage.UML_ADDSTRUCTURALFEATUREVALUEACTION));
        return addStructuralFeatureValueAction;
    }

    /**
     * 
     * 
     * @return ClearStructuralFeatureAction
     */
    public static ClearStructuralFeatureAction createClearStructuralFeatureAction() {
        org.eclipse.uml2.uml.ClearStructuralFeatureAction clearStructuralFeatureAction = FACTORY.createClearStructuralFeatureAction();
        clearStructuralFeatureAction.setName(UMLMessage.getMessage(UMLMessage.UML_CLEARSTRUCTURALFEATUREACTION));
        return clearStructuralFeatureAction;
    }

    /**
     * 
     * 
     * @return RemoveStructuralFeatureValueAction
     */
    public static RemoveStructuralFeatureValueAction createRemoveStructuralFeatureValueAction() {
        org.eclipse.uml2.uml.RemoveStructuralFeatureValueAction removeStructuralFeatureValueAction = FACTORY.createRemoveStructuralFeatureValueAction();
        removeStructuralFeatureValueAction.setName(UMLMessage.getMessage(UMLMessage.UML_REMOVESTRUCTURALFEATUREACTION));
        return removeStructuralFeatureValueAction;
    }

    /**
     * 
     * 
     * @return ReadStructuralFeatureAction
     */
    public static ReadStructuralFeatureAction createReadStructuralFeatureAction() {
        org.eclipse.uml2.uml.ReadStructuralFeatureAction readStructuralFeatureAction = FACTORY.createReadStructuralFeatureAction();
        readStructuralFeatureAction.setName(UMLMessage.getMessage(UMLMessage.UML_READSTRUCTURALFEATUREACTION));
        return readStructuralFeatureAction;
    }

    /**
     * 
     * 
     * @return CreateObjectAction
     */
    public static CreateObjectAction createCreateObjectAction() {
        org.eclipse.uml2.uml.CreateObjectAction createObjectAction = FACTORY.createCreateObjectAction();
        createObjectAction.setName(UMLMessage.getMessage(UMLMessage.UML_CREATEOBJECTACTION));
        return createObjectAction;
    }

    /**
     * 
     * 
     * @return DestroyObjectAction
     */
    public static DestroyObjectAction createDestroyObjectAction() {
        org.eclipse.uml2.uml.DestroyObjectAction destroyObjectAction = FACTORY.createDestroyObjectAction();
        destroyObjectAction.setName(UMLMessage.getMessage(UMLMessage.UML_DESTROYOBJECTACTION));
        return destroyObjectAction;
    }

    /**
     * 
     * 
     * @return ReadExtentAction
     */
    public static ReadExtentAction createReadExtentAction() {
        org.eclipse.uml2.uml.ReadExtentAction readExtentAction = FACTORY.createReadExtentAction();
        readExtentAction.setName(UMLMessage.getMessage(UMLMessage.UML_READEXTENTACTION));
        return readExtentAction;
    }

    /**
     * 
     * 
     * @return ReadIsClassifiedObjectAction
     */
    public static ReadIsClassifiedObjectAction createReadIsClassifiedObjectAction() {
        org.eclipse.uml2.uml.ReadIsClassifiedObjectAction readIsClassifiedObjectAction = FACTORY.createReadIsClassifiedObjectAction();
        readIsClassifiedObjectAction.setName(UMLMessage.getMessage(UMLMessage.UML_READCLASSIFIEDOBJECTACTION));
        return readIsClassifiedObjectAction;
    }

    /**
     * 
     * 
     * @return ReadSelfAction
     */
    public static ReadSelfAction createReadSelfAction() {
        org.eclipse.uml2.uml.ReadSelfAction readSelfAction = FACTORY.createReadSelfAction();
        readSelfAction.setName(UMLMessage.getMessage(UMLMessage.UML_READSELFACTION));
        return readSelfAction;
    }

    /**
     * 
     * 
     * @return ReclassifyObjectAction
     */
    public static ReclassifyObjectAction createReclassifyObjectAction() {
        org.eclipse.uml2.uml.ReclassifyObjectAction reclassifyObjectAction = FACTORY.createReclassifyObjectAction();
        reclassifyObjectAction.setName(UMLMessage.getMessage(UMLMessage.UML_RECLASSIFYOBJECTACTION));
        return reclassifyObjectAction;
    }

    /**
     * 
     * 
     * @return ConditionalNode
     */
    public static ConditionalNode creatConditionalNode() {
        org.eclipse.uml2.uml.ConditionalNode conditionalNode = FACTORY.createConditionalNode();
        conditionalNode.setName(UMLMessage.getMessage(UMLMessage.UML_CONDITIONALNODE));
        return conditionalNode;
    }

    /**
     * 
     * 
     * @return ReplyAction
     */
    public static ReplyAction createReplyAction() {
        org.eclipse.uml2.uml.ReplyAction replyAction = FACTORY.createReplyAction();
        replyAction.setName(UMLMessage.getMessage(UMLMessage.UML_REPLYACTION));
        return replyAction;
    }

    /**
     * 
     * 
     * @return Event
     */
    public static Event createEvent() {
        return FACTORY.createExecutionEvent();
    }

    /**
     * 
     * 
     * @return StartClassifierBehaviorAction
     */
    public static StartClassifierBehaviorAction createStartClassifierBehaviorAction() {
        org.eclipse.uml2.uml.StartClassifierBehaviorAction startClassifierBehaviorAction = FACTORY.createStartClassifierBehaviorAction();
        startClassifierBehaviorAction.setName(UMLMessage.getMessage(UMLMessage.UML_STARTCLASSIFIERBEHAVIORACTION));
        return startClassifierBehaviorAction;
    }

    /**
     * 
     * 
     * @return TestIdentityAction
     */
    public static TestIdentityAction createTestIdentityAction() {
        org.eclipse.uml2.uml.TestIdentityAction testIdentityAction = FACTORY.createTestIdentityAction();
        testIdentityAction.setName(UMLMessage.getMessage(UMLMessage.UML_TESTIDENTITYACTION));
        return testIdentityAction;
    }

    /**
     * 
     * 
     * @return ClearAssociationAction
     */
    public static ClearAssociationAction createClearAssociationAction() {
        org.eclipse.uml2.uml.ClearAssociationAction clearAssociationAction = FACTORY.createClearAssociationAction();
        clearAssociationAction.setName(UMLMessage.getMessage(UMLMessage.UML_CLEARASSOCIATIONACTION));
        return clearAssociationAction;
    }

    /**
     * 
     * 
     * @return CreateLinkAction
     */
    public static CreateLinkAction createCreateLinkAction() {
        org.eclipse.uml2.uml.CreateLinkAction createLinkAction = FACTORY.createCreateLinkAction();
        createLinkAction.setName(UMLMessage.getMessage(UMLMessage.UML_CREATELINKACTION));
        return createLinkAction;
    }

    /**
     * 
     * 
     * @return CreateLinkObjectAction
     */
    public static CreateLinkObjectAction createCreateLinkObjectAction() {
        org.eclipse.uml2.uml.CreateLinkObjectAction createLinkObjectAction = FACTORY.createCreateLinkObjectAction();
        createLinkObjectAction.setName(UMLMessage.getMessage(UMLMessage.UML_CREATELINKOBJECTACTION));
        return createLinkObjectAction;
    }

    /**
     * 
     * 
     * @return DestroyLinkAction
     */
    public static DestroyLinkAction createDestroyLinkAction() {
        org.eclipse.uml2.uml.DestroyLinkAction destroyLinkAction = FACTORY.createDestroyLinkAction();
        destroyLinkAction.setName(UMLMessage.getMessage(UMLMessage.UML_DESTROYLINKACTION));
        return destroyLinkAction;
    }

    /**
     * 
     * 
     * @return ReadLinkAction
     */
    public static ReadLinkAction createReadLinkAction() {
        org.eclipse.uml2.uml.ReadLinkAction readLinkAction = FACTORY.createReadLinkAction();
        readLinkAction.setName(UMLMessage.getMessage(UMLMessage.UML_READLINKACTION));
        return readLinkAction;
    }

    /**
     * 
     * 
     * @return ReadLinkObjectEndAction
     */
    public static ReadLinkObjectEndAction createReadLinkObjectEndAction() {
        org.eclipse.uml2.uml.ReadLinkObjectEndAction readLinkObjectEndAction = FACTORY.createReadLinkObjectEndAction();
        readLinkObjectEndAction.setName(UMLMessage.getMessage(UMLMessage.UML_READLINKOBJECTENDACTION));
        return readLinkObjectEndAction;
    }

    /**
     * 
     * 
     * @return ReadLinkObjectEndQualifierAction
     */
    public static ReadLinkObjectEndQualifierAction createReadLinkObjectEndQualifierAction() {
        org.eclipse.uml2.uml.ReadLinkObjectEndQualifierAction readLinkObjectEndQualifierAction = FACTORY.createReadLinkObjectEndQualifierAction();
        readLinkObjectEndQualifierAction.setName(UMLMessage.getMessage(UMLMessage.UML_READLINKOBJECTENDQUALIFIERACTION));
        return readLinkObjectEndQualifierAction;
    }

    /**
     * 
     * 
     * @return BroadcastSignalAction
     */
    public static BroadcastSignalAction createBroadcastSignalAction() {
        org.eclipse.uml2.uml.BroadcastSignalAction broadcastSignalAction = FACTORY.createBroadcastSignalAction();
        broadcastSignalAction.setName(UMLMessage.getMessage(UMLMessage.UML_BROADCASTSIGNALACTION));
        return broadcastSignalAction;
    }

    /**
     * 
     * 
     * @return ReceiveSignalEvent
     */
    public static ReceiveSignalEvent createReceiveSignalEvent() {
        return FACTORY.createReceiveSignalEvent();
    }

    /**
     * 
     * 
     * @return AcceptCallAction
     */
    public static AcceptCallAction createAcceptCallAction() {
        org.eclipse.uml2.uml.AcceptCallAction acceptCallAction = FACTORY.createAcceptCallAction();
        acceptCallAction.setName(UMLMessage.getMessage(UMLMessage.UML_ACCEPTCALLACTION));
        return acceptCallAction;
    }

    /**
     * 
     * 
     * @return SendObjectAction
     */
    public static SendObjectAction createSendObjectAction() {
        org.eclipse.uml2.uml.SendObjectAction sendObjectAction = FACTORY.createSendObjectAction();
        sendObjectAction.setName(UMLMessage.getMessage(UMLMessage.UML_SENDOBJECTACTION));
        return sendObjectAction;
    }

    /**
     * 
     * 
     * @return EnumerationLiteral
     */
    public static EnumerationLiteral createEnumerationLiteral() {
        org.eclipse.uml2.uml.EnumerationLiteral enumerationLiteral = FACTORY.createEnumerationLiteral();
        enumerationLiteral.setName(UMLMessage.getMessage(UMLMessage.UML_ENUMERATIONLITERAL));
        return enumerationLiteral;
    }
}
