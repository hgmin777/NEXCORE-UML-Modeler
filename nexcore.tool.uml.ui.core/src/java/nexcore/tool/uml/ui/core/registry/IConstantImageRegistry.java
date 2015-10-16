/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.ui.core.registry;

import org.eclipse.swt.graphics.Image;

/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.ui.core</li>
 * <li>서브 업무명 : nexcore.tool.uml.ui.core.registry</li>
 * <li>설  명 : IConstantImageRegistry</li>
 * <li>작성일 : 2009. 11. 5.</li>
 * <li>작성자 : 강경구</li>
 * </ul>
 */
public interface IConstantImageRegistry {

    /**
     * LOCATION_SAMLLICON_PATH
     */
    public static final String LOCATION_SAMLLICON_PATH = "icons/full/obj16/"; //$NON-NLS-1$

    /**
     * LOCATION_NORMALICON_PATH
     */
    public static final String LOCATION_NORMALICON_PATH = "icons/full/obj16/"; //$NON-NLS-1$

    /**
     * LOCATION_LARGEICON_PATH
     */
    public static final String LOCATION_LARGEICON_PATH = "icons/full/obj16/"; //$NON-NLS-1$

    /**
     * ICON_EXTENTION_NAME
     */
    public static final String ICON_EXTENTION_NAME = ".gif"; //$NON-NLS-1$

    /**
     * ICON_LARGE
     */
    public static final String ICON_LARGE = "Large"; //$NON-NLS-1$

    /**
     * ICON_NORMAL
     */
    public static final String ICON_NORMAL = "Normal"; //$NON-NLS-1$

    /**
     * ICON_SMALL
     */
    public static final String ICON_SMALL = "Small"; //$NON-NLS-1$

    /**
     * getLargeIconKey
     *  
     * @param id
     * @return String
     */
    String getLargeIconKey(String id);

    /**
     * getNormalIconKey
     *  
     * @param id
     * @return String
     */
    String getNormalIconKey(String id);

    /**
     * getSmallIconKey
     *  
     * @param id
     * @return String
     */
    String getSmallIconKey(String id);

    /**
     * getLocationForSmall
     *  
     * @param id
     * @return String
     */
    String getLocationForSmall(String id);

    /**
     * getLocationForNormal
     *  
     * @param id
     * @return String
     */
    String getLocationForNormal(String id);

    /**
     * getLocationForLarge
     *  
     * @param id
     * @return String
     */
    String getLocationForLarge(String id);

    /**
     * getLargeIcon
     *  
     * @param imageKey
     * @return Image
     */
    Image getLargeIcon(String imageKey);

    /**
     * getNomralIcon
     *  
     * @param imageKey
     * @return Image
     */
    Image getNomralIcon(String imageKey);

    /**
     * getSmallIcon
     *  
     * @param imageKey
     * @return Image
     */
    Image getSmallIcon(String imageKey);

    /**
     * Overlay ICON ImageRegistry for project explorer decorator.
     */
    public static final String LOCATION_OVER_DECORATOR = "icons/full/ovr16/UMLDecorator.gif"; //$NON-NLS-1$

//    public static final String LOCATION_PROJECT_EXPLORER = "icons/ProjectExplorer.gif"; //$NON-NLS-1$

    /**
     * LOCATION_ACTIVITY_DIAGRAM
     */
    public static final String LOCATION_ACTIVITY_DIAGRAM = "icons/nodes/ActivityDiagram.gif"; //$NON-NLS-1$

    /**
     * LOCATION_CLASS_DIAGRAM
     */
    public static final String LOCATION_CLASS_DIAGRAM = "icons/nodes/ClassDiagram.gif"; //$NON-NLS-1$

    /**
     * LOCATION_COMPONENT_DIAGRAM
     */
    public static final String LOCATION_COMPONENT_DIAGRAM = "icons/nodes/ComponentDiagram.gif"; //$NON-NLS-1$

    /**
     * LOCATION_SEQUENCE_DIAGRAM
     */
    public static final String LOCATION_SEQUENCE_DIAGRAM = "icons/nodes/SequenceDiagram.gif"; //$NON-NLS-1$

    /**
     * LOCATION_USECASE_DIAGRAM
     */
    public static final String LOCATION_USECASE_DIAGRAM = "icons/nodes/UsecaseDiagram.gif"; //$NON-NLS-1$

    // icon Location
//    public static final String LOCATION_PACKAGE = "icons/full/obj16/Package.gif"; //$NON-NLS-1$ 
//
//    public static final String LOCATION_ATTRIBUTE = "icons/attribute.gif"; //$NON-NLS-1$ 
//
//    public static final String LOCATION_CLASS = "icons/full/obj16/Class.gif"; //$NON-NLS-1$ 
//
//    public static final String LOCATION_METHOD = "icons/method.gif"; //$NON-NLS-1$ 
//
//    public static final String LOCATION_ASSOCIATION = "icons/full/obj16/Association.gif"; //$NON-NLS-1$ 
//
//    public static final String LOCATION_NONE_ASSOCIATION = "icons/full/obj16/Association_none.gif"; //$NON-NLS-1$ 
//
//    public static final String LOCATION_SHARED_ASSOCIATION = "icons/full/obj16/Association_shared.gif"; //$NON-NLS-1$ 
//
//    public static final String LOCATION_COMPOSITE_ASSOCIATION = "icons/full/obj16/Association_composite.gif"; //$NON-NLS-1$ 
//
//    public static final String LOCATION_GENERALIZATION = "icons/full/obj16/Generalization.gif"; //$NON-NLS-1$ 
//
//    public static final String LOCATION_REALIZATION = "icons/full/obj16/Realization.gif"; //$NON-NLS-1$ 
//
//    public static final String LOCATION_PROJECT_WIZARD = "icons/full/obj16/NewProjectWizard.gif"; //$NON-NLS-1$ 

    // icon Name
//    public static final String NAME_PACKAGE = "package"; //$NON-NLS-1$ 
//
//    public static final String NAME_ATTRIBUTE = "attribute"; //$NON-NLS-1$ 
//
//    public static final String NAME_CLASS = "class"; //$NON-NLS-1$ 
//
//    public static final String NAME_METHOD = "method"; //$NON-NLS-1$ 
//
//    public static final String NAME_ASSOCIATION = "association"; //$NON-NLS-1$ 
//
//    public static final String NAME_NONE_ASSOCIATION = "association"; //$NON-NLS-1$ 
//
//    public static final String NAME_SHARED_ASSOCIATION = "shared association"; //$NON-NLS-1$ 
//
//    public static final String NAME_COMPOSITE_ASSOCIATION = "composite association"; //$NON-NLS-1$ 
//
//    public static final String NAME_GENERALIZATION = "generalization"; //$NON-NLS-1$ 
//
//    public static final String NAME_REALIZATION = "realization"; //$NON-NLS-1$ 
//
//    public static final String NAME_PROJECT_WIZARD = "newProjectWizard"; //$NON-NLS-1$

    /**
     * eInstance
     */
    public static final IConstantImageRegistry eInstance = ConstantImageRegistryImpl.getInstance();

    /**
     * ICON ImageRegistry
     */
    /** Icon Abstraction */
    public static final String ICONNAME_ABSTRACTION = "Abstraction"; //$NON-NLS-1$

    /** Icon AcceptCallAction */
    public static final String ICONNAME_ACCEPTCALLACTION = "AcceptCallAction"; //$NON-NLS-1$

    /** Icon AcceptEventAction */
    public static final String ICONNAME_ACCEPTEVENTACTION = "AcceptEventAction"; //$NON-NLS-1$

    /** Icon ActionExecutionSpecification */
    public static final String ICONNAME_ACTIONEXECUTIONSPECIFICATION = "ActionExecutionSpecification"; //$NON-NLS-1$

    /** Icon ActionInputPin */
    public static final String ICONNAME_ACTIONINPUTPIN = "ActionInputPin"; //$NON-NLS-1$

    /** Icon Activity */
    public static final String ICONNAME_ACTIVITY = "Activity"; //$NON-NLS-1$

    /** Icon ActivityFinalNode */
    public static final String ICONNAME_ACTIVITYFINALNODE = "ActivityFinalNode"; //$NON-NLS-1$

    /** Icon ActivityParameterNode */
    public static final String ICONNAME_ACTIVITYPARAMETERNODE = "ActivityParameterNode"; //$NON-NLS-1$

    /** Icon ActivityPartition */
    public static final String ICONNAME_ACTIVITYPARTITION = "ActivityPartition"; //$NON-NLS-1$

    /** Icon Actor */
    public static final String ICONNAME_ACTOR = "Actor"; //$NON-NLS-1$

    /** Icon AddStructuralFeatureValueAction */
    public static final String ICONNAME_ADDSTRUCTURALFEATUREVALUEACTION = "AddStructuralFeatureValueAction"; //$NON-NLS-1$

    /** Icon AddVariableValueAction */
    public static final String ICONNAME_ADDVARIABLEVALUEACTION = "AddVariableValueAction"; //$NON-NLS-1$

    /** Icon AnyReceiveEvent */
    public static final String ICONNAME_ANYRECEIVEEVENT = "AnyReceiveEvent"; //$NON-NLS-1$

    /** Icon Artifact */
    public static final String ICONNAME_ARTIFACT = "Artifact"; //$NON-NLS-1$

    /** Icon Association */
    public static final String ICONNAME_ASSOCIATION = "Association"; //$NON-NLS-1$

    /** Icon AssociationClass */
    public static final String ICONNAME_ASSOCIATIONCLASS = "AssociationClass"; //$NON-NLS-1$

    /** Icon Association_composite */
    public static final String ICONNAME_ASSOCIATION_COMPOSITE = "Association_composite"; //$NON-NLS-1$

    /** Icon Association_none */
    public static final String ICONNAME_ASSOCIATION_NONE = "Association_none"; //$NON-NLS-1$

    /** Icon Association_shared */
    public static final String ICONNAME_ASSOCIATION_SHARED = "Association_shared"; //$NON-NLS-1$

    /** Icon BehaviorExecutionSpecification */
    public static final String ICONNAME_BEHAVIOREXECUTIONSPECIFICATION = "BehaviorExecutionSpecification"; //$NON-NLS-1$

    /** Icon BroadcastSignalAction */
    public static final String ICONNAME_BROADCASTSIGNALACTION = "BroadcastSignalAction"; //$NON-NLS-1$

    /** Icon CallBehaviorAction */
    public static final String ICONNAME_CALLBEHAVIORACTION = "CallBehaviorAction"; //$NON-NLS-1$

    /** Icon CallEvent */
    public static final String ICONNAME_CALLEVENT = "CallEvent"; //$NON-NLS-1$

    /** Icon CallOperationAction */
    public static final String ICONNAME_CALLOPERATIONACTION = "CallOperationAction"; //$NON-NLS-1$

    /** Icon CentralBufferNode */
    public static final String ICONNAME_CENTRALBUFFERNODE = "CentralBufferNode"; //$NON-NLS-1$

    /** Icon ChangeEvent */
    public static final String ICONNAME_CHANGEEVENT = "ChangeEvent"; //$NON-NLS-1$

    /** Icon Class */
    public static final String ICONNAME_CLASS = "Class"; //$NON-NLS-1$

    /** Icon ClassifierTemplateParameter */
    public static final String ICONNAME_CLASSIFIERTEMPLATEPARAMETER = "ClassifierTemplateParameter"; //$NON-NLS-1$

    /** Icon Clause */
    public static final String ICONNAME_CLAUSE = "Clause"; //$NON-NLS-1$

    /** Icon ClearAssociationAction */
    public static final String ICONNAME_CLEARASSOCIATIONACTION = "ClearAssociationAction"; //$NON-NLS-1$

    /** Icon ClearStructuralFeatureAction */
    public static final String ICONNAME_CLEARSTRUCTURALFEATUREACTION = "ClearStructuralFeatureAction"; //$NON-NLS-1$

    /** Icon ClearVariableAction */
    public static final String ICONNAME_CLEARVARIABLEACTION = "ClearVariableAction"; //$NON-NLS-1$

    /** Icon Collaboration */
    public static final String ICONNAME_COLLABORATION = "Collaboration"; //$NON-NLS-1$

    /** Icon CollaborationUse */
    public static final String ICONNAME_COLLABORATIONUSE = "CollaborationUse"; //$NON-NLS-1$

    /** Icon CombinedFragment */
    public static final String ICONNAME_COMBINEDFRAGMENT = "CombinedFragment"; //$NON-NLS-1$

    /** Icon Comment */
    public static final String ICONNAME_COMMENT = "Comment"; //$NON-NLS-1$

    /** Icon CommunicationPath */
    public static final String ICONNAME_COMMUNICATIONPATH = "CommunicationPath"; //$NON-NLS-1$

    /** Icon Component */
    public static final String ICONNAME_COMPONENT = "Component"; //$NON-NLS-1$

    /** Icon ComponentRealization */
    public static final String ICONNAME_COMPONENTREALIZATION = "ComponentRealization"; //$NON-NLS-1$

    /** Icon ConditionalNode */
    public static final String ICONNAME_CONDITIONALNODE = "ConditionalNode"; //$NON-NLS-1$

    /** Icon ConnectableElementTemplateParameter */
    public static final String ICONNAME_CONNECTABLEELEMENTTEMPLATEPARAMETER = "ConnectableElementTemplateParameter"; //$NON-NLS-1$

    /** Icon ConnectionPointReference */
    public static final String ICONNAME_CONNECTIONPOINTREFERENCE = "ConnectionPointReference"; //$NON-NLS-1$

    /** Icon Connector */
    public static final String ICONNAME_CONNECTOR = "Connector"; //$NON-NLS-1$

    /** Icon ConnectorEnd */
    public static final String ICONNAME_CONNECTOREND = "ConnectorEnd"; //$NON-NLS-1$

    /** Icon Connector_assembly */
    public static final String ICONNAME_CONNECTOR_ASSEMBLY = "Connector_assembly"; //$NON-NLS-1$

    /** Icon Connector_delegation */
    public static final String ICONNAME_CONNECTOR_DELEGATION = "Connector_delegation"; //$NON-NLS-1$

    /** Icon ConsiderIgnoreFragment */
    public static final String ICONNAME_CONSIDERIGNOREFRAGMENT = "ConsiderIgnoreFragment"; //$NON-NLS-1$

    /** Icon Constraint */
    public static final String ICONNAME_CONSTRAINT = "Constraint"; //$NON-NLS-1$

    /** Icon Continuation */
    public static final String ICONNAME_CONTINUATION = "Continuation"; //$NON-NLS-1$

    /** Icon ControlFlow */
    public static final String ICONNAME_CONTROLFLOW = "ControlFlow"; //$NON-NLS-1$

    /** Icon CreateLinkAction */
    public static final String ICONNAME_CREATELINKACTION = "CreateLinkAction"; //$NON-NLS-1$

    /** Icon CreateLinkObjectAction */
    public static final String ICONNAME_CREATELINKOBJECTACTION = "CreateLinkObjectAction"; //$NON-NLS-1$

    /** Icon CreateObjectAction */
    public static final String ICONNAME_CREATEOBJECTACTION = "CreateObjectAction"; //$NON-NLS-1$

    /** Icon CreationEvent */
    public static final String ICONNAME_CREATIONEVENT = "CreationEvent"; //$NON-NLS-1$

    /** Icon DataStoreNode */
    public static final String ICONNAME_DATASTORENODE = "DataStoreNode"; //$NON-NLS-1$

    /** Icon DataType */
    public static final String ICONNAME_DATATYPE = "DataType"; //$NON-NLS-1$

    /** Icon DecisionNode */
    public static final String ICONNAME_DECISIONNODE = "DecisionNode"; //$NON-NLS-1$

    /** Icon Dependency */
    public static final String ICONNAME_DEPENDENCY = "Dependency"; //$NON-NLS-1$

    /** Icon Deployment */
    public static final String ICONNAME_DEPLOYMENT = "Deployment"; //$NON-NLS-1$

    /** Icon DeploymentSpecification */
    public static final String ICONNAME_DEPLOYMENTSPECIFICATION = "DeploymentSpecification"; //$NON-NLS-1$

    /** Icon DestroyLinkAction */
    public static final String ICONNAME_DESTROYLINKACTION = "DestroyLinkAction"; //$NON-NLS-1$

    /** Icon DestroyObjectAction */
    public static final String ICONNAME_DESTROYOBJECTACTION = "DestroyObjectAction"; //$NON-NLS-1$

    /** Icon DestructionEvent */
    public static final String ICONNAME_DESTRUCTIONEVENT = "DestructionEvent"; //$NON-NLS-1$

    /** Icon Device */
    public static final String ICONNAME_DEVICE = "Device"; //$NON-NLS-1$

    /** Icon Duration */
    public static final String ICONNAME_DURATION = "Duration"; //$NON-NLS-1$

    /** Icon DurationConstraint */
    public static final String ICONNAME_DURATIONCONSTRAINT = "DurationConstraint"; //$NON-NLS-1$

    /** Icon DurationInterval */
    public static final String ICONNAME_DURATIONINTERVAL = "DurationInterval"; //$NON-NLS-1$

    /** Icon DurationObservation */
    public static final String ICONNAME_DURATIONOBSERVATION = "DurationObservation"; //$NON-NLS-1$

    /** Icon ElementImport */
    public static final String ICONNAME_ELEMENTIMPORT = "ElementImport"; //$NON-NLS-1$

    /** Icon Enumeration */
    public static final String ICONNAME_ENUMERATION = "Enumeration"; //$NON-NLS-1$

    /** Icon EnumerationLiteral */
    public static final String ICONNAME_ENUMERATIONLITERAL = "EnumerationLiteral"; //$NON-NLS-1$

    /** Icon ExceptionHandler */
    public static final String ICONNAME_EXCEPTIONHANDLER = "ExceptionHandler"; //$NON-NLS-1$

    /** Icon ExecutionEnvironment */
    public static final String ICONNAME_EXECUTIONENVIRONMENT = "ExecutionEnvironment"; //$NON-NLS-1$

    /** Icon ExecutionEvent */
    public static final String ICONNAME_EXECUTIONEVENT = "ExecutionEvent"; //$NON-NLS-1$

    /** Icon ExecutionOccurrenceSpecification */
    public static final String ICONNAME_EXECUTIONOCCURRENCESPECIFICATION = "ExecutionOccurrenceSpecification"; //$NON-NLS-1$

    /** Icon ExpansionNode */
    public static final String ICONNAME_EXPANSIONNODE = "ExpansionNode"; //$NON-NLS-1$

    /** Icon ExpansionRegion */
    public static final String ICONNAME_EXPANSIONREGION = "ExpansionRegion"; //$NON-NLS-1$

    /** Icon Expression */
    public static final String ICONNAME_EXPRESSION = "Expression"; //$NON-NLS-1$

    /** Icon Extend */
    public static final String ICONNAME_EXTEND = "Extend"; //$NON-NLS-1$

    /** Icon Extension */
    public static final String ICONNAME_EXTENSION = "Extension"; //$NON-NLS-1$

    /** Icon ExtensionEnd */
    public static final String ICONNAME_EXTENSIONEND = "ExtensionEnd"; //$NON-NLS-1$

    /** Icon ExtensionPoint */
    public static final String ICONNAME_EXTENSIONPOINT = "ExtensionPoint"; //$NON-NLS-1$

    /** Icon FinalState */
    public static final String ICONNAME_FINALSTATE = "FinalState"; //$NON-NLS-1$

    /** Icon FlowFinalNode */
    public static final String ICONNAME_FLOWFINALNODE = "FlowFinalNode"; //$NON-NLS-1$

    /** Icon ForkNode */
    public static final String ICONNAME_FORKNODE = "ForkNode"; //$NON-NLS-1$

    /** Icon FunctionBehavior */
    public static final String ICONNAME_FUNCTIONBEHAVIOR = "FunctionBehavior"; //$NON-NLS-1$

    /** Icon Gate */
    public static final String ICONNAME_GATE = "Gate"; //$NON-NLS-1$

    /** Icon Generalization */
    public static final String ICONNAME_GENERALIZATION = "Generalization"; //$NON-NLS-1$

    /** Icon GeneralizationSet */
    public static final String ICONNAME_GENERALIZATIONSET = "GeneralizationSet"; //$NON-NLS-1$

    /** Icon GeneralOrdering */
    public static final String ICONNAME_GENERALORDERING = "GeneralOrdering"; //$NON-NLS-1$

    /** Icon Image */
    public static final String ICONNAME_IMAGE = "Image"; //$NON-NLS-1$

    /** Icon Include */
    public static final String ICONNAME_INCLUDE = "Include"; //$NON-NLS-1$

    /** Icon InformationFlow */
    public static final String ICONNAME_INFORMATIONFLOW = "InformationFlow"; //$NON-NLS-1$

    /** Icon InformationItem */
    public static final String ICONNAME_INFORMATIONITEM = "InformationItem"; //$NON-NLS-1$

    /** Icon InitialNode */
    public static final String ICONNAME_INITIALNODE = "InitialNode"; //$NON-NLS-1$

    /** Icon InputPin */
    public static final String ICONNAME_INPUTPIN = "InputPin"; //$NON-NLS-1$

    /** Icon InstanceSpecification */
    public static final String ICONNAME_INSTANCESPECIFICATION = "InstanceSpecification"; //$NON-NLS-1$

    /** Icon InstanceValue */
    public static final String ICONNAME_INSTANCEVALUE = "InstanceValue"; //$NON-NLS-1$

    /** Icon Interaction */
    public static final String ICONNAME_INTERACTION = "Interaction"; //$NON-NLS-1$

    /** Icon InteractionConstraint */
    public static final String ICONNAME_INTERACTIONCONSTRAINT = "InteractionConstraint"; //$NON-NLS-1$

    /** Icon InteractionOperand */
    public static final String ICONNAME_INTERACTIONOPERAND = "InteractionOperand"; //$NON-NLS-1$

    /** Icon InteractionUse */
    public static final String ICONNAME_INTERACTIONUSE = "InteractionUse"; //$NON-NLS-1$

    /** Icon Interface */
    public static final String ICONNAME_INTERFACE = "Interface"; //$NON-NLS-1$

    /** Icon InterfaceRealization */
    public static final String ICONNAME_INTERFACEREALIZATION = "InterfaceRealization"; //$NON-NLS-1$

    /** Icon InterruptibleActivityRegion */
    public static final String ICONNAME_INTERRUPTIBLEACTIVITYREGION = "InterruptibleActivityRegion"; //$NON-NLS-1$

    /** Icon Interval */
    public static final String ICONNAME_INTERVAL = "Interval"; //$NON-NLS-1$

    /** Icon IntervalConstraint */
    public static final String ICONNAME_INTERVALCONSTRAINT = "IntervalConstraint"; //$NON-NLS-1$

    /** Icon JoinNode */
    public static final String ICONNAME_JOINNODE = "JoinNode"; //$NON-NLS-1$

    /** Icon Lifeline */
    public static final String ICONNAME_LIFELINE = "Lifeline"; //$NON-NLS-1$

    /** Icon LinkEndCreationData */
    public static final String ICONNAME_LINKENDCREATIONDATA = "LinkEndCreationData"; //$NON-NLS-1$

    /** Icon LinkEndData */
    public static final String ICONNAME_LINKENDDATA = "LinkEndData"; //$NON-NLS-1$

    /** Icon LinkEndDestructionData */
    public static final String ICONNAME_LINKENDDESTRUCTIONDATA = "LinkEndDestructionData"; //$NON-NLS-1$

    /** Icon LiteralBoolean */
    public static final String ICONNAME_LITERALBOOLEAN = "LiteralBoolean"; //$NON-NLS-1$

    /** Icon LiteralInteger */
    public static final String ICONNAME_LITERALINTEGER = "LiteralInteger"; //$NON-NLS-1$

    /** Icon LiteralNull */
    public static final String ICONNAME_LITERALNULL = "LiteralNull"; //$NON-NLS-1$

    /** Icon LiteralString */
    public static final String ICONNAME_LITERALSTRING = "LiteralString"; //$NON-NLS-1$

    /** Icon LiteralUnlimitedNatural */
    public static final String ICONNAME_LITERALUNLIMITEDNATURAL = "LiteralUnlimitedNatural"; //$NON-NLS-1$

    /** Icon LoopNode */
    public static final String ICONNAME_LOOPNODE = "LoopNode"; //$NON-NLS-1$

    /** Icon Manifestation */
    public static final String ICONNAME_MANIFESTATION = "Manifestation"; //$NON-NLS-1$

    /** Icon MergeNode */
    public static final String ICONNAME_MERGENODE = "MergeNode"; //$NON-NLS-1$

    /** Icon Message */
    public static final String ICONNAME_MESSAGE = "Message"; //$NON-NLS-1$

    /** Icon MessageOccurrenceSpecification */
    public static final String ICONNAME_MESSAGEOCCURRENCESPECIFICATION = "MessageOccurrenceSpecification"; //$NON-NLS-1$

    /** Icon Message_asynchCall */
    public static final String ICONNAME_MESSAGE_ASYNCHCALL = "Message_asynchCall"; //$NON-NLS-1$

    /** Icon Message_asynchSignal */
    public static final String ICONNAME_MESSAGE_ASYNCHSIGNAL = "Message_asynchSignal"; //$NON-NLS-1$

    /** Icon Message_createMessage */
    public static final String ICONNAME_MESSAGE_CREATEMESSAGE = "Message_createMessage"; //$NON-NLS-1$

    /** Icon Message_deleteMessage */
    public static final String ICONNAME_MESSAGE_DELETEMESSAGE = "Message_deleteMessage"; //$NON-NLS-1$

    /** Icon Message_reply */
    public static final String ICONNAME_MESSAGE_REPLY = "Message_reply"; //$NON-NLS-1$

    /** Icon Message_synchCall */
    public static final String ICONNAME_MESSAGE_SYNCHCALL = "Message_synchCall"; //$NON-NLS-1$

    /** Icon Model */
    public static final String ICONNAME_MODEL = "Model"; //$NON-NLS-1$

    /** Icon Node */
    public static final String ICONNAME_NODE = "Node"; //$NON-NLS-1$

    /** Icon ObjectFlow */
    public static final String ICONNAME_OBJECTFLOW = "ObjectFlow"; //$NON-NLS-1$

    /** Icon OccurrenceSpecification */
    public static final String ICONNAME_OCCURRENCESPECIFICATION = "OccurrenceSpecification"; //$NON-NLS-1$

    /** Icon OpaqueAction */
    public static final String ICONNAME_OPAQUEACTION = "OpaqueAction"; //$NON-NLS-1$

    /** Icon OpaqueBehavior */
    public static final String ICONNAME_OPAQUEBEHAVIOR = "OpaqueBehavior"; //$NON-NLS-1$

    /** Icon OpaqueExpression */
    public static final String ICONNAME_OPAQUEEXPRESSION = "OpaqueExpression"; //$NON-NLS-1$

    /** Icon Operation */
    public static final String ICONNAME_OPERATION = "Operation"; //$NON-NLS-1$

    /** Icon OperationTemplateParameter */
    public static final String ICONNAME_OPERATIONTEMPLATEPARAMETER = "OperationTemplateParameter"; //$NON-NLS-1$

    /** Icon OutputPin */
    public static final String ICONNAME_OUTPUTPIN = "OutputPin"; //$NON-NLS-1$

    /** Icon Package */
    public static final String ICONNAME_PACKAGE = "Package"; //$NON-NLS-1$

    /** Icon PackageImport */
    public static final String ICONNAME_PACKAGEIMPORT = "PackageImport"; //$NON-NLS-1$

    /** Icon PackageMerge */
    public static final String ICONNAME_PACKAGEMERGE = "PackageMerge"; //$NON-NLS-1$

    /** Icon Parameter */
    public static final String ICONNAME_PARAMETER = "Parameter"; //$NON-NLS-1$

    /** Icon ParameterSet */
    public static final String ICONNAME_PARAMETERSET = "ParameterSet"; //$NON-NLS-1$

    /** Icon Parameter_in */
    public static final String ICONNAME_PARAMETER_IN = "Parameter_in"; //$NON-NLS-1$

    /** Icon Parameter_inout */
    public static final String ICONNAME_PARAMETER_INOUT = "Parameter_inout"; //$NON-NLS-1$

    /** Icon Parameter_out */
    public static final String ICONNAME_PARAMETER_OUT = "Parameter_out"; //$NON-NLS-1$

    /** Icon Parameter_return */
    public static final String ICONNAME_PARAMETER_RETURN = "Parameter_return"; //$NON-NLS-1$

    /** Icon PartDecomposition */
    public static final String ICONNAME_PARTDECOMPOSITION = "PartDecomposition"; //$NON-NLS-1$

    /** Icon Pin */
    public static final String ICONNAME_PIN = "Pin"; //$NON-NLS-1$

    /** Icon Port */
    public static final String ICONNAME_PORT = "Port"; //$NON-NLS-1$

    /** Icon PrimitiveType */
    public static final String ICONNAME_PRIMITIVETYPE = "PrimitiveType"; //$NON-NLS-1$

    /** Icon Profile */
    public static final String ICONNAME_PROFILE = "Profile"; //$NON-NLS-1$

    /** Icon ProfileApplication */
    public static final String ICONNAME_PROFILEAPPLICATION = "ProfileApplication"; //$NON-NLS-1$

    /** Icon Property */
    public static final String ICONNAME_PROPERTY = "Property"; //$NON-NLS-1$

    /** Icon ProtocolConformance */
    public static final String ICONNAME_PROTOCOLCONFORMANCE = "ProtocolConformance"; //$NON-NLS-1$

    /** Icon ProtocolStateMachine */
    public static final String ICONNAME_PROTOCOLSTATEMACHINE = "ProtocolStateMachine"; //$NON-NLS-1$

    /** Icon ProtocolTransition */
    public static final String ICONNAME_PROTOCOLTRANSITION = "ProtocolTransition"; //$NON-NLS-1$

    /** Icon Pseudostate */
    public static final String ICONNAME_PSEUDOSTATE = "Pseudostate"; //$NON-NLS-1$

    /** Icon Pseudostate_choice */
    public static final String ICONNAME_PSEUDOSTATE_CHOICE = "Pseudostate_choice"; //$NON-NLS-1$

    /** Icon Pseudostate_deepHistory */
    public static final String ICONNAME_PSEUDOSTATE_DEEPHISTORY = "Pseudostate_deepHistory"; //$NON-NLS-1$

    /** Icon Pseudostate_entryPoint */
    public static final String ICONNAME_PSEUDOSTATE_ENTRYPOINT = "Pseudostate_entryPoint"; //$NON-NLS-1$

    /** Icon Pseudostate_exitPoint */
    public static final String ICONNAME_PSEUDOSTATE_EXITPOINT = "Pseudostate_exitPoint"; //$NON-NLS-1$

    /** Icon Pseudostate_fork */
    public static final String ICONNAME_PSEUDOSTATE_FORK = "Pseudostate_fork"; //$NON-NLS-1$

    /** Icon Pseudostate_initial */
    public static final String ICONNAME_PSEUDOSTATE_INITIAL = "Pseudostate_initial"; //$NON-NLS-1$

    /** Icon Pseudostate_join */
    public static final String ICONNAME_PSEUDOSTATE_JOIN = "Pseudostate_join"; //$NON-NLS-1$

    /** Icon Pseudostate_junction */
    public static final String ICONNAME_PSEUDOSTATE_JUNCTION = "Pseudostate_junction"; //$NON-NLS-1$

    /** Icon Pseudostate_shallowHistory */
    public static final String ICONNAME_PSEUDOSTATE_SHALLOWHISTORY = "Pseudostate_shallowHistory"; //$NON-NLS-1$

    /** Icon Pseudostate_terminate */
    public static final String ICONNAME_PSEUDOSTATE_TERMINATE = "Pseudostate_terminate"; //$NON-NLS-1$

    /** Icon QualifierValue */
    public static final String ICONNAME_QUALIFIERVALUE = "QualifierValue"; //$NON-NLS-1$

    /** Icon RaiseExceptionAction */
    public static final String ICONNAME_RAISEEXCEPTIONACTION = "RaiseExceptionAction"; //$NON-NLS-1$

    /** Icon ReadExtentAction */
    public static final String ICONNAME_READEXTENTACTION = "ReadExtentAction"; //$NON-NLS-1$

    /** Icon ReadIsClassifiedObjectAction */
    public static final String ICONNAME_READISCLASSIFIEDOBJECTACTION = "ReadIsClassifiedObjectAction"; //$NON-NLS-1$

    /** Icon ReadLinkAction */
    public static final String ICONNAME_READLINKACTION = "ReadLinkAction"; //$NON-NLS-1$

    /** Icon ReadLinkObjectEndAction */
    public static final String ICONNAME_READLINKOBJECTENDACTION = "ReadLinkObjectEndAction"; //$NON-NLS-1$

    /** Icon ReadLinkObjectEndQualifierAction */
    public static final String ICONNAME_READLINKOBJECTENDQUALIFIERACTION = "ReadLinkObjectEndQualifierAction"; //$NON-NLS-1$

    /** Icon ReadSelfAction */
    public static final String ICONNAME_READSELFACTION = "ReadSelfAction"; //$NON-NLS-1$

    /** Icon ReadStructuralFeatureAction */
    public static final String ICONNAME_READSTRUCTURALFEATUREACTION = "ReadStructuralFeatureAction"; //$NON-NLS-1$

    /** Icon ReadVariableAction */
    public static final String ICONNAME_READVARIABLEACTION = "ReadVariableAction"; //$NON-NLS-1$

    /** Icon Realization */
    public static final String ICONNAME_REALIZATION = "Realization"; //$NON-NLS-1$

    /** Icon ReceiveOperationEvent */
    public static final String ICONNAME_RECEIVEOPERATIONEVENT = "ReceiveOperationEvent"; //$NON-NLS-1$

    /** Icon ReceiveSignalEvent */
    public static final String ICONNAME_RECEIVESIGNALEVENT = "ReceiveSignalEvent"; //$NON-NLS-1$

    /** Icon Reception */
    public static final String ICONNAME_RECEPTION = "Reception"; //$NON-NLS-1$

    /** Icon ReclassifyObjectAction */
    public static final String ICONNAME_RECLASSIFYOBJECTACTION = "ReclassifyObjectAction"; //$NON-NLS-1$

    /** Icon RedefinableTemplateSignature */
    public static final String ICONNAME_REDEFINABLETEMPLATESIGNATURE = "RedefinableTemplateSignature"; //$NON-NLS-1$

    /** Icon ReduceAction */
    public static final String ICONNAME_REDUCEACTION = "ReduceAction"; //$NON-NLS-1$

    /** Icon Region */
    public static final String ICONNAME_REGION = "Region"; //$NON-NLS-1$

    /** Icon RemoveStructuralFeatureValueAction */
    public static final String ICONNAME_REMOVESTRUCTURALFEATUREVALUEACTION = "RemoveStructuralFeatureValueAction"; //$NON-NLS-1$

    /** Icon RemoveVariableValueAction */
    public static final String ICONNAME_REMOVEVARIABLEVALUEACTION = "RemoveVariableValueAction"; //$NON-NLS-1$

    /** Icon ReplyAction */
    public static final String ICONNAME_REPLYACTION = "ReplyAction"; //$NON-NLS-1$

    /** Icon SendObjectAction */
    public static final String ICONNAME_SENDOBJECTACTION = "SendObjectAction"; //$NON-NLS-1$

    /** Icon SendOperationEvent */
    public static final String ICONNAME_SENDOPERATIONEVENT = "SendOperationEvent"; //$NON-NLS-1$

    /** Icon SendSignalAction */
    public static final String ICONNAME_SENDSIGNALACTION = "SendSignalAction"; //$NON-NLS-1$

    /** Icon SendSignalEvent */
    public static final String ICONNAME_SENDSIGNALEVENT = "SendSignalEvent"; //$NON-NLS-1$

    /** Icon SequenceNode */
    public static final String ICONNAME_SEQUENCENODE = "SequenceNode"; //$NON-NLS-1$

    /** Icon Signal */
    public static final String ICONNAME_SIGNAL = "Signal"; //$NON-NLS-1$

    /** Icon SignalEvent */
    public static final String ICONNAME_SIGNALEVENT = "SignalEvent"; //$NON-NLS-1$

    /** Icon Slot */
    public static final String ICONNAME_SLOT = "Slot"; //$NON-NLS-1$

    /** Icon StartClassifierBehaviorAction */
    public static final String ICONNAME_STARTCLASSIFIERBEHAVIORACTION = "StartClassifierBehaviorAction"; //$NON-NLS-1$

    /** Icon State */
    public static final String ICONNAME_STATE = "State"; //$NON-NLS-1$

    /** Icon StateInvariant */
    public static final String ICONNAME_STATEINVARIANT = "StateInvariant"; //$NON-NLS-1$

    /** Icon StateMachine */
    public static final String ICONNAME_STATEMACHINE = "StateMachine"; //$NON-NLS-1$

    /** Icon Stereotype */
    public static final String ICONNAME_STEREOTYPE = "Stereotype"; //$NON-NLS-1$

    /** Icon StringExpression */
    public static final String ICONNAME_STRINGEXPRESSION = "StringExpression"; //$NON-NLS-1$

    /** Icon StructuredActivityNode */
    public static final String ICONNAME_STRUCTUREDACTIVITYNODE = "StructuredActivityNode"; //$NON-NLS-1$

    /** Icon Substitution */
    public static final String ICONNAME_SUBSTITUTION = "Substitution"; //$NON-NLS-1$

    /** Icon TemplateBinding */
    public static final String ICONNAME_TEMPLATEBINDING = "TemplateBinding"; //$NON-NLS-1$

    /** Icon TemplateParameter */
    public static final String ICONNAME_TEMPLATEPARAMETER = "TemplateParameter"; //$NON-NLS-1$

    /** Icon TemplateParameterSubstitution */
    public static final String ICONNAME_TEMPLATEPARAMETERSUBSTITUTION = "TemplateParameterSubstitution"; //$NON-NLS-1$

    /** Icon TemplateSignature */
    public static final String ICONNAME_TEMPLATESIGNATURE = "TemplateSignature"; //$NON-NLS-1$

    /** Icon TestIdentityAction */
    public static final String ICONNAME_TESTIDENTITYACTION = "TestIdentityAction"; //$NON-NLS-1$

    /** Icon TimeConstraint */
    public static final String ICONNAME_TIMECONSTRAINT = "TimeConstraint"; //$NON-NLS-1$

    /** Icon TimeEvent */
    public static final String ICONNAME_TIMEEVENT = "TimeEvent"; //$NON-NLS-1$

    /** Icon TimeExpression */
    public static final String ICONNAME_TIMEEXPRESSION = "TimeExpression"; //$NON-NLS-1$

    /** Icon TimeInterval */
    public static final String ICONNAME_TIMEINTERVAL = "TimeInterval"; //$NON-NLS-1$

    /** Icon TimeObservation */
    public static final String ICONNAME_TIMEOBSERVATION = "TimeObservation"; //$NON-NLS-1$

    /** Icon Transition */
    public static final String ICONNAME_TRANSITION = "Transition"; //$NON-NLS-1$

    /** Icon Transition_external */
    public static final String ICONNAME_TRANSITION_EXTERNAL = "Transition_external"; //$NON-NLS-1$

    /** Icon Transition_internal */
    public static final String ICONNAME_TRANSITION_INTERNAL = "Transition_internal"; //$NON-NLS-1$

    /** Icon Transition_local */
    public static final String ICONNAME_TRANSITION_LOCAL = "Transition_local"; //$NON-NLS-1$

    /** Icon Trigger */
    public static final String ICONNAME_TRIGGER = "Trigger"; //$NON-NLS-1$

    /** Icon UnmarshallAction */
    public static final String ICONNAME_UNMARSHALLACTION = "UnmarshallAction"; //$NON-NLS-1$

    /** Icon Usage */
    public static final String ICONNAME_USAGE = "Usage"; //$NON-NLS-1$

    /** Icon UseCase */
    public static final String ICONNAME_USECASE = "UseCase"; //$NON-NLS-1$

    /** Icon ValuePin */
    public static final String ICONNAME_VALUEPIN = "ValuePin"; //$NON-NLS-1$

    /** Icon ValueSpecificationAction */
    public static final String ICONNAME_VALUESPECIFICATIONACTION = "ValueSpecificationAction"; //$NON-NLS-1$

    /** Icon Variable */
    public static final String ICONNAME_VARIABLE = "Variable"; //$NON-NLS-1$

    /** Icon ProvidedInterface */
    public static final String ICONNAME_PROVIDEDINTERFACE = "ProvidedInterface"; //$NON-NLS-1$

    /** Icon RequiredInterface */
    public static final String ICONNAME_REQUIREDINTERFACE = "RequiredInterface"; //$NON-NLS-1$

    /** Icon Action */
    public static final String ICONNAME_ACTION = "Action"; //$NON-NLS-1$

    /** Icon Asynch_Call */
    public static final String ICONNAME_ASYNCH_CALL = "Asynch_Call"; //$NON-NLS-1$

    /** Icon Synch_Call */
    public static final String ICONNAME_SYNCH_CALL = "Synch_Call"; //$NON-NLS-1$

    /** Icon Create_Message */
    public static final String ICONNAME_CREATE_MESSAGE = "Create_Message"; //$NON-NLS-1$

    /** Icon Delete_Message */
    public static final String ICONNAME_DELETE_MESSAGE = "Delete_Message"; //$NON-NLS-1$

    /** Icon Reply */
    public static final String ICONNAME_REPLY = "Reply"; //$NON-NLS-1$

    /** Icon Interaction_ALT */
    public static final String ICONNAME_INTERACTION_ALT = "Interaction_ALT"; //$NON-NLS-1$

    /** Icon Interaction_ASSERT */
    public static final String ICONNAME_INTERACTION_ASSERT = "Interaction_ASSERT"; //$NON-NLS-1$

    /** Icon Interaction_BREAK */
    public static final String ICONNAME_INTERACTION_BREAK = "Interaction_BREAK"; //$NON-NLS-1$

    /** Icon Interaction_CONSIDER */
    public static final String ICONNAME_INTERACTION_CONSIDER = "Interaction_CONSIDER"; //$NON-NLS-1$

    /** Icon Interaction_CRITICAL */
    public static final String ICONNAME_INTERACTION_CRITICAL = "Interaction_CRITICAL"; //$NON-NLS-1$

    /** Icon Interaction_IGNORE */
    public static final String ICONNAME_INTERACTION_IGNORE = "Interaction_IGNORE"; //$NON-NLS-1$

    /** Icon Interaction_LOOP */
    public static final String ICONNAME_INTERACTION_LOOP = "Interaction_LOOP"; //$NON-NLS-1$

    /** Icon Interaction_NEG */
    public static final String ICONNAME_INTERACTION_NEG = "Interaction_NEG"; //$NON-NLS-1$

    /** Icon Interaction_OPT */
    public static final String ICONNAME_INTERACTION_OPT = "Interaction_OPT"; //$NON-NLS-1$

    /** Icon Interaction_PAR */
    public static final String ICONNAME_INTERACTION_PAR = "Interaction_PAR"; //$NON-NLS-1$

    /** Icon Interaction_SEQ */
    public static final String ICONNAME_INTERACTION_SEQ = "Interaction_SEQ"; //$NON-NLS-1$

    /** Icon Interaction_STRICT */
    public static final String ICONNAME_INTERACTION_STRICT = "Interaction_STRICT"; //$NON-NLS-1$

    /** Icon Binary Association */
    public static final String ICONNAME_BINARY_ASSOCIATION = "Binary_Association";//$NON-NLS-1$

    /** Icon Directed Association */
    public static final String ICONNAME_DIRECTED_ASSOCIATION = "Directed_Association";//$NON-NLS-1$

    /** Icon Shared Binary Association */
    public static final String ICONNAME_SHARED_BINARY_ASSOCIATION = "Shared_Binary_Assocaiton";//$NON-NLS-1$

    /** Icon Shared Directed Association */
    public static final String ICONNAME_SHARED_DIRECTED_ASSOCIATIONASSOCIATION = "Shared_Directed_Association";//$NON-NLS-1$

    /** Icon Composite Binary Association */
    public static final String ICONNAME_COMPOSITE_BINARY_ASSOCIATION = "Composite_Binary_Association";//$NON-NLS-1$

    /** Icon Composite Directed Association */
    public static final String ICONNAME_COMPOSITE_DIRECTED_ASSOCIATION = "Composite_Directed_Association";//$NON-NLS-1$

    /** Icon Note */
    public static final String ICONNAME_NOTE = "Note";

    /** Icon Text */
    public static final String ICONNAME_TEXT = "Text";

    /** Icon Attachment */
    public static final String ICONNAME_ATTACHMENT = "Attachment";

    /**
     * ICONNAME_PROJECT_WIZARD
     */
    public static final String ICONNAME_PROJECT_WIZARD = "icons/full/obj16/NewProjectWizard.gif";

    /**
     * ICONNAME_MODEL_WIZARD
     */
    public static final String ICONNAME_MODEL_WIZARD = "icons/full/obj16/UmlModelingProjectWizard.gif";
    
    /** Icon ADD_INTERACTIONOPERAND */
    public static final String ICONNAME_ADD_INTERACTIONOPERAND = "icons/addInteractionOperand.png"; //$NON-NLS-1$
    
    /** Icon ADD_COVERED_LIFELINE */
    public static final String ICONNAME_ADD_COVERED_LIFELINE = "icons/addCoveredLifeline.png"; //$NON-NLS-1$
    
    /** Icon REMOVE_COVERED_LIFELINE */
    public static final String ICONNAME_REMOVE_COVERED_LIFELINE = "icons/removeCoveredLifeline.png"; //$NON-NLS-1$
    
    
    
    
    // Action Icon
    /** Icon ICONNAME_ADD_ATTRIBUTE_ACTION */
    public static final String ICONNAME_ADD_ATTRIBUTE_ACTION = "icons/addAttribute.png"; //$NON-NLS-1$
    
    /** Icon ICONNAME_ADD_COVERED_LIFELINE_ACTION */
    public static final String ICONNAME_ADD_COVERED_LIFELINE_ACTION = "icons/addCoveredLifeline.png"; //$NON-NLS-1$
    
    /** Icon ICONNAME_ADD_INTERACTION_OPERAND_ACTION */
    public static final String ICONNAME_ADD_INTERACTION_OPERAND_ACTION = "icons/addInteractionOperand.png"; //$NON-NLS-1$
    
    /** Icon iCONNAME_ADD_OPERATION_ACTION */
    public static final String iCONNAME_ADD_OPERATION_ACTION = "icons/addOperation.png"; //$NON-NLS-1$
    
    /** Icon ICONNAME_APPLY_STEREOTYPE_ACTION */
    public static final String ICONNAME_APPLY_STEREOTYPE_ACTION = "icons/applyStereotype.png"; //$NON-NLS-1$
    
    /** Icon ICONNAME_COMPARTMENT_VISIBILITY_ACTION */
    public static final String ICONNAME_COMPARTMENT_VISIBILITY_ACTION = "icons/compartmentVisibility.png"; //$NON-NLS-1$
    
    /** Icon ICONNAME_CREATE_DICTIONARY_ACTION */
    public static final String ICONNAME_CREATE_DICTIONARY_ACTION = "icons/createDictionary.png"; //$NON-NLS-1$
    
    /** Icon ICONNAME_CREATE_MDAD_ANALYSIS_STRUCTURE_ACTION */
    public static final String ICONNAME_CREATE_MDAD_ANALYSIS_STRUCTURE_ACTION = "icons/createMDADAnalysisStructure.png"; //$NON-NLS-1$
    
    /** Icon ICONNAME_CREATE_MDAD_BEHAVIOR_ACTION */
    public static final String ICONNAME_CREATE_MDAD_BEHAVIOR_ACTION = "icons/createMDADBehavior.png"; //$NON-NLS-1$
    
    /** Icon ICONNAME_CREATE_MDAD_DESIGN_MODEL_ACTION */
    public static final String ICONNAME_CREATE_MDAD_DESIGN_MODEL_ACTION = "icons/createMDADDesignModel.png"; //$NON-NLS-1$
    
    /** Icon ICONNAME_CREATE_MDAD_SEQUENCE_DIAGRAM_ACTION */
    public static final String ICONNAME_CREATE_MDAD_SEQUENCE_DIAGRAM_ACTION = "icons/createMDADSequenceDiagram.png"; //$NON-NLS-1$
    
    /** Icon ICONNAME_CREATE_MDAD_STRUCTURE_ACTION */
    public static final String ICONNAME_CREATE_MDAD_STRUCTURE_ACTION = "icons/createMDADStructure.png"; //$NON-NLS-1$
    
    /** Icon ICONNAME_CREATE_REPORT_COMPONENT_ARCHITECTURE_DEFINITION_ACTION */
    public static final String ICONNAME_CREATE_REPORT_COMPONENT_ARCHITECTURE_DEFINITION_ACTION = "icons/createReportComponentArchitectureDefinition.png"; //$NON-NLS-1$
    
    /** Icon ICONNAME_CREATE_REPORT_COMPONENT_DEFINITION_ACTION */
    public static final String ICONNAME_CREATE_REPORT_COMPONENT_DEFINITION_ACTION = "icons/createReportComponentDefinition.png"; //$NON-NLS-1$
    
    /** Icon ICONNAME_CREATE_REPORT_INTERFACE_INTERACTION_DEFINITION_ACTION */
    public static final String ICONNAME_CREATE_REPORT_INTERFACE_INTERACTION_DEFINITION_ACTION = "icons/createReportInterfaceInteractionDefinition.png"; //$NON-NLS-1$
    
    /** Icon ICONNAME_ADD_CREATE_REPORT_UI_DEFINITION */
    public static final String ICONNAME_ADD_CREATE_REPORT_UI_DEFINITION = "icons/createReportUiDefinition.png"; //$NON-NLS-1$
    
    /** Icon ICONNAME_CUT_ACTION */
    public static final String ICONNAME_CUT_ACTION = "icons/cut.png"; //$NON-NLS-1$
    
    /** Icon ICONNAME_DELETE_FROM_MODEL_ACTION */
    public static final String ICONNAME_DELETE_FROM_MODEL_ACTION = "icons/deleteFormModel.png"; //$NON-NLS-1$
    
    /** Icon ICONNAME_FIND_ELEMENT_ACTION */
    public static final String ICONNAME_FIND_ELEMENT_ACTION = "icons/findElement.png"; //$NON-NLS-1$
    
    /** Icon ICONNAME_GENERATE_SOURCE_CODE_ACTION */
    public static final String ICONNAME_GENERATE_SOURCE_CODE_ACTION = "icons/generateSourceCode.png"; //$NON-NLS-1$
    
    /** Icon ICONNAME_MDA_DESIGNER */
    public static final String ICONNAME_MDA_DESIGNER = "icons/mdaDesigner.png"; //$NON-NLS-1$
    
    /** Icon ICONNAME_MDA_DEVELOPER */
    public static final String ICONNAME_MDA_DEVELOPER = "icons/mdaDeveloper.png"; //$NON-NLS-1$
    
    /** Icon ICONNAME_MERGE_CLASS_ACTION */
    public static final String ICONNAME_MERGE_CLASS_ACTION = "icons/mergeClass.png"; //$NON-NLS-1$d
    
    /** Icon ICONNAME_REMOVE_COVERED_LIFELINE_ACTION */
    public static final String ICONNAME_REMOVE_COVERED_LIFELINE_ACTION = "icons/removeCoveredLifeline.png"; //$NON-NLS-1$
    
    /** Icon ICONNAME_SAVE_TO_IMAGE_ACTION */
    public static final String ICONNAME_SAVE_TO_IMAGE_ACTION = "icons/saveToImage.png"; //$NON-NLS-1$
    
    /** Icon ICONNAME_TRANSFORMATION_KEYWORD_ACTION */
    public static final String ICONNAME_TRANSFORMATION_KEYWORD_ACTION = "icons/transformationKeyword.png"; //$NON-NLS-1$
    
    /** Icon ICONNAME_TRANSFORMATION_LANGUAGE_ACTION */
    public static final String ICONNAME_TRANSFORMATION_LANGUAGE_ACTION = "icons/transformationLanguage.png"; //$NON-NLS-1$

    /** Icon ICONNAME_COPY_ACTION */
    public static final String ICONNAME_COPY_ACTION = "icons/Copy.gif"; //$NON-NLS-1$

    /** Icon ICONNAME_SAVE_ACTION */
    public static final String ICONNAME_SAVE_ACTION = "icons/Save.gif"; //$NON-NLS-1$

    /** Icon ICONNAME_PASTE_ACTION */
    public static final String ICONNAME_PASTE_ACTION = "icons/paste.gif"; //$NON-NLS-1$
    
    /**
     * ICONNAME_WARNING_OVER
     */
    public static final String ICONNAME_WARNING_OVER = "icons/warning_ovr.gif"; //$NON-NLS-1$

    /**
     * ICONNAME_CLOSE_MODEL
     */
    public static final String ICONNAME_CLOSE_MODEL = "icons/Close_model.gif"; //$NON-NLS-1$

    /**
     * ICONNAME_ERROR_OVER
     */
    public static final String ICONNAME_ERROR_OVER = "icons/error_ovr.gif"; //$NON-NLS-1$

    /**
     * ICONNAME_NCP_DECORATOR
     */
    public static final String ICONNAME_NCP_DECORATOR = "icons/new_ncp_decorator.png"; //$NON-NLS-1$
    
    /**
     * ICONNAME_WIZARD_BANNER_GRAPHIC
     */
    public static final String ICONNAME_WIZARD_BANNER_GRAPHIC = "icons/wizard_banner_graphic.png"; //$NON-NLS-1$

    /**
     * ICONNAME_CLEAR
     */
    public static final String ICONNAME_CLEAR = "icons/clear.gif"; //$NON-NLS-1$

    /**
     * ICONNAME_REVERSE_EXEC
     */
    public static final String ICONNAME_REVERSE_EXEC = "icons/mdac_revers_exec.gif"; //$NON-NLS-1$

    /**
     * ICONNAME_REFRESH
     */
    public static final String ICONNAME_REFRESH = "icons/refresh.gif"; //$NON-NLS-1$
}
