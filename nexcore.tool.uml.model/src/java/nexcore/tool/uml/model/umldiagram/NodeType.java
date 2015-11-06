/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.model.umldiagram;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '
 * <em><b>Node Type</b></em>', and utility methods for working with them. <!--
 * end-user-doc -->
 * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getNodeType()
 * @model
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.umldiagram</li>
 * <li>설  명 : NodeType</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public enum NodeType implements Enumerator {
    /**
     * The '<em><b>Note</b></em>' literal object.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #NOTE_VALUE
     * @generated
     * @ordered
     */
    NOTE(0, "Note", "Note"),

    /**
     * The '<em><b>Text</b></em>' literal object.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #TEXT_VALUE
     * @generated
     * @ordered
     */
    TEXT(1, "Text", "Text"),

    /**
     * The '<em><b>Activity</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #ACTIVITY_VALUE
     * @generated
     * @ordered
     */
    ACTIVITY(1100, "Activity", "Activity"),

    /**
     * The '<em><b>Activity Partition</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #ACTIVITY_PARTITION_VALUE
     * @generated
     * @ordered
     */
    ACTIVITY_PARTITION(1200, "ActivityPartition", "ActivityPartition"),

    /**
     * The '<em><b>Structured Activity Node</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #STRUCTURED_ACTIVITY_NODE_VALUE
     * @generated
     * @ordered
     */
    STRUCTURED_ACTIVITY_NODE(1300, "StructuredActivityNode", "StructuredActivityNode"),

    /**
     * The '<em><b>Component Structure Compartment</b></em>' literal object.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #COMPONENT_STRUCTURE_COMPARTMENT_VALUE
     * @generated
     * @ordered
     */
    COMPONENT_STRUCTURE_COMPARTMENT(1400, "ComponentStructureCompartment", "ComponentStructureCompartment"),

    /**
     * The '<em><b>Combined Fragment</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #COMBINED_FRAGMENT_VALUE
     * @generated
     * @ordered
     */
    COMBINED_FRAGMENT(1500, "CombinedFragment", "CombinedFragment"),

    /**
     * The '<em><b>Alternative Combined Fragment</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #ALTERNATIVE_COMBINED_FRAGMENT_VALUE
     * @generated
     * @ordered
     */
    ALTERNATIVE_COMBINED_FRAGMENT(1501, "AlternativeCombinedFragment", "AlternativeCombinedFragment"),

    /**
     * The '<em><b>Break Combined Fragment</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #BREAK_COMBINED_FRAGMENT_VALUE
     * @generated
     * @ordered
     */
    BREAK_COMBINED_FRAGMENT(1502, "BreakCombinedFragment", "BreakCombinedFragment"), /**
     * The '<em><b>Loop Combined Fragment</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #LOOP_COMBINED_FRAGMENT_VALUE
     * @generated
     * @ordered
     */
    LOOP_COMBINED_FRAGMENT(1503, "LoopCombinedFragment", "LoopCombinedFragment"),

    /**
     * The '<em><b>Sequence Combined Fragment</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #SEQUENCE_COMBINED_FRAGMENT_VALUE
     * @generated
     * @ordered
     */
    SEQUENCE_COMBINED_FRAGMENT(1504, "SequenceCombinedFragment", "SequenceCombinedFragment"), /**
     * The '<em><b>Strict Combined Fragment</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #STRICT_COMBINED_FRAGMENT_VALUE
     * @generated
     * @ordered
     */
    STRICT_COMBINED_FRAGMENT(1505, "StrictCombinedFragment", "StrictCombinedFragment"), /**
     * The '<em><b>Parallel Combined Fragment</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #PARALLEL_COMBINED_FRAGMENT_VALUE
     * @generated
     * @ordered
     */
    PARALLEL_COMBINED_FRAGMENT(1506, "ParallelCombinedFragment", "ParallelCombinedFragment"),

    /**
     * The '<em><b>Critical Combined Fragment</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #CRITICAL_COMBINED_FRAGMENT_VALUE
     * @generated
     * @ordered
     */
    CRITICAL_COMBINED_FRAGMENT(1507, "CriticalCombinedFragment", "CriticalCombinedFragment"), /**
     * The '<em><b>Negative Combined Fragment</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #NEGATIVE_COMBINED_FRAGMENT_VALUE
     * @generated
     * @ordered
     */
    NEGATIVE_COMBINED_FRAGMENT(1508, "NegativeCombinedFragment", "NegativeCombinedFragment"),

    /**
     * The '<em><b>Assert Combined Fragment</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #ASSERT_COMBINED_FRAGMENT_VALUE
     * @generated
     * @ordered
     */
    ASSERT_COMBINED_FRAGMENT(1509, "AssertCombinedFragment", "AssertCombinedFragment"),

    /**
     * The '<em><b>Ignore Combined Fragment</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #IGNORE_COMBINED_FRAGMENT_VALUE
     * @generated
     * @ordered
     */
    IGNORE_COMBINED_FRAGMENT(1510, "IgnoreCombinedFragment", "IgnoreCombinedFragment"),

    /**
     * The '<em><b>Consider Combined Fragment</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #CONSIDER_COMBINED_FRAGMENT_VALUE
     * @generated
     * @ordered
     */
    CONSIDER_COMBINED_FRAGMENT(1511, "ConsiderCombinedFragment", "ConsiderCombinedFragment"), /**
     * The '<em><b>Option Combined Fragment</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #OPTION_COMBINED_FRAGMENT_VALUE
     * @generated
     * @ordered
     */
    OPTION_COMBINED_FRAGMENT(1512, "OptionCombinedFragment", "OptionCombinedFragment"),

    /**
     * The '<em><b>Combined Fragment Name</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #COMBINED_FRAGMENT_NAME_VALUE
     * @generated
     * @ordered
     */
    COMBINED_FRAGMENT_NAME(1513, "CombinedFragmentName", "CombinedFragmentName"), /**
     * The '<em><b>Interaction Operand</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #INTERACTION_OPERAND_VALUE
     * @generated
     * @ordered
     */
    INTERACTION_OPERAND(1520, "InteractionOperand", "InteractionOperand"), /**
     * The '<em><b>Guard</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #GUARD_VALUE
     * @generated
     * @ordered
     */
    GUARD(1521, "Guard", "Guard"), /**
     * The '<em><b>Option If</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #OPTION_IF_VALUE
     * @generated
     * @ordered
     */
    OPTION_IF(1530, "OptionIf", "OptionIf"), /**
     * The '<em><b>Alternative Switch</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #ALTERNATIVE_SWITCH_VALUE
     * @generated
     * @ordered
     */
    ALTERNATIVE_SWITCH(1532, "AlternativeSwitch", "AlternativeSwitch"), /**
     * The '<em><b>Alternative If Else</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #ALTERNATIVE_IF_ELSE_VALUE
     * @generated
     * @ordered
     */
    ALTERNATIVE_IF_ELSE(1531, "AlternativeIfElse", "AlternativeIfElse"), /**
     * The '<em><b>Loop While</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #LOOP_WHILE_VALUE
     * @generated
     * @ordered
     */
    LOOP_WHILE(1533, "LoopWhile", "LoopWhile"), /**
     * The '<em><b>Loop For</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #LOOP_FOR_VALUE
     * @generated
     * @ordered
     */
    LOOP_FOR(1534, "LoopFor", "LoopFor"), /**
     * The '<em><b>Region</b></em>' literal object.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #REGION_VALUE
     * @generated
     * @ordered
     */
    REGION(1600, "Region", "Region"),

    /**
     * The '<em><b>State</b></em>' literal object.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #STATE_VALUE
     * @generated
     * @ordered
     */
    STATE(1700, "State", "State"),

    /**
     * The '<em><b>Composite Structure</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #COMPOSITE_STRUCTURE_VALUE
     * @generated
     * @ordered
     */
    COMPOSITE_STRUCTURE(1800, "CompositeStructure", "CompositeStructure"),

    /**
     * The '<em><b>Accept Event Action</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #ACCEPT_EVENT_ACTION_VALUE
     * @generated
     * @ordered
     */
    ACCEPT_EVENT_ACTION(5010, "AcceptEventAction", "AcceptEventAction"),

    /**
     * The '<em><b>Activity Final Node</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #ACTIVITY_FINAL_NODE_VALUE
     * @generated
     * @ordered
     */
    ACTIVITY_FINAL_NODE(5020, "ActivityFinalNode", "ActivityFinalNode"),

    /**
     * The '<em><b>Activity Parameter Node</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #ACTIVITY_PARAMETER_NODE_VALUE
     * @generated
     * @ordered
     */
    ACTIVITY_PARAMETER_NODE(5030, "ActivityParameterNode", "ActivityParameterNode"),

    /**
     * The '<em><b>Call Behavior Action</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #CALL_BEHAVIOR_ACTION_VALUE
     * @generated
     * @ordered
     */
    CALL_BEHAVIOR_ACTION(5040, "CallBehaviorAction", "CallBehaviorAction"),

    /**
     * The '<em><b>Call Operation Action</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #CALL_OPERATION_ACTION_VALUE
     * @generated
     * @ordered
     */
    CALL_OPERATION_ACTION(5050, "CallOperationAction", "CallOperationAction"),

    /**
     * The '<em><b>Central Buffer Node</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #CENTRAL_BUFFER_NODE_VALUE
     * @generated
     * @ordered
     */
    CENTRAL_BUFFER_NODE(5060, "CentralBufferNode", "CentralBufferNode"),

    /**
     * The '<em><b>Data Store Node</b></em>' literal object.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #DATA_STORE_NODE_VALUE
     * @generated
     * @ordered
     */
    DATA_STORE_NODE(5070, "DataStoreNode", "DataStoreNode"),

    /**
     * The '<em><b>Decision Node</b></em>' literal object.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #DECISION_NODE_VALUE
     * @generated
     * @ordered
     */
    DECISION_NODE(5080, "DecisionNode", "DecisionNode"),

    /**
     * The '<em><b>Expansion Node</b></em>' literal object.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #EXPANSION_NODE_VALUE
     * @generated
     * @ordered
     */
    EXPANSION_NODE(5090, "ExpansionNode", "ExpansionNode"),

    /**
     * The '<em><b>Flow Final Node</b></em>' literal object.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #FLOW_FINAL_NODE_VALUE
     * @generated
     * @ordered
     */
    FLOW_FINAL_NODE(5100, "FlowFinalNode", "FlowFinalNode"),

    /**
     * The '<em><b>Fork Node</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #FORK_NODE_VALUE
     * @generated
     * @ordered
     */
    FORK_NODE(5110, "ForkNode", "ForkNode"),

    /**
     * The '<em><b>Initial Node</b></em>' literal object.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #INITIAL_NODE_VALUE
     * @generated
     * @ordered
     */
    INITIAL_NODE(5120, "InitialNode", "InitialNode"),

    /**
     * The '<em><b>Input Pin</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #INPUT_PIN_VALUE
     * @generated
     * @ordered
     */
    INPUT_PIN(5130, "InputPin", "InputPin"),

    /**
     * The '<em><b>Join Node</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #JOIN_NODE_VALUE
     * @generated
     * @ordered
     */
    JOIN_NODE(5140, "JoinNode", "JoinNode"),

    /**
     * The '<em><b>Merge Node</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #MERGE_NODE_VALUE
     * @generated
     * @ordered
     */
    MERGE_NODE(5150, "MergeNode", "MergeNode"),

    /**
     * The '<em><b>Opaque Action</b></em>' literal object.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #OPAQUE_ACTION_VALUE
     * @generated
     * @ordered
     */
    OPAQUE_ACTION(5160, "OpaqueAction", "OpaqueAction"),

    /**
     * The '<em><b>Output Pin</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #OUTPUT_PIN_VALUE
     * @generated
     * @ordered
     */
    OUTPUT_PIN(5170, "OutputPin", "OutputPin"),

    /**
     * The '<em><b>Send Signal Action</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #SEND_SIGNAL_ACTION_VALUE
     * @generated
     * @ordered
     */
    SEND_SIGNAL_ACTION(5180, "SendSignalAction", "SendSignalAction"),

    /**
     * The '<em><b>Value Pin</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #VALUE_PIN_VALUE
     * @generated
     * @ordered
     */
    VALUE_PIN(5190, "ValuePin", "ValuePin"),

    /**
     * The '<em><b>Artifact</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #ARTIFACT_VALUE
     * @generated
     * @ordered
     */
    ARTIFACT(5200, "Artifact", "Artifact"),

    /**
     * The '<em><b>Class</b></em>' literal object.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #CLASS_VALUE
     * @generated
     * @ordered
     */
    CLASS(5210, "Class", "Class"),

    /**
     * The '<em><b>Collaboration</b></em>' literal object.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #COLLABORATION_VALUE
     * @generated
     * @ordered
     */
    COLLABORATION(5220, "Collaboration", "Collaboration"),

    /**
     * The '<em><b>Data Type</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #DATA_TYPE_VALUE
     * @generated
     * @ordered
     */
    DATA_TYPE(5230, "DataType", "DataType"),

    /**
     * The '<em><b>Enumeration</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #ENUMERATION_VALUE
     * @generated
     * @ordered
     */
    ENUMERATION(5240, "Enumeration", "Enumeration"),

    /**
     * The '<em><b>Interface</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #INTERFACE_VALUE
     * @generated
     * @ordered
     */
    INTERFACE(5250, "Interface", "Interface"),

    /**
     * The '<em><b>Package</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #PACKAGE_VALUE
     * @generated
     * @ordered
     */
    PACKAGE(5260, "Package", "Package"),

    /**
     * The '<em><b>Signal</b></em>' literal object.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #SIGNAL_VALUE
     * @generated
     * @ordered
     */
    SIGNAL(5270, "Signal", "Signal"),

    /**
     * The '<em><b>Component</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #COMPONENT_VALUE
     * @generated
     * @ordered
     */
    COMPONENT(5290, "Component", "Component"),

    /**
     * The '<em><b>Destruction Event</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #DESTRUCTION_EVENT_VALUE
     * @generated
     * @ordered
     */
    DESTRUCTION_EVENT(5310, "DestructionEvent", "DestructionEvent"),

    /**
     * The '<em><b>Interaction Use</b></em>' literal object.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #INTERACTION_USE_VALUE
     * @generated
     * @ordered
     */
    INTERACTION_USE(5320, "InteractionUse", "InteractionUse"),

    /**
     * The '<em><b>Lifeline</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #LIFELINE_VALUE
     * @generated
     * @ordered
     */
    LIFELINE(5330, "Lifeline", "Lifeline"),

    /**
     * The '<em><b>Message</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #MESSAGE_VALUE
     * @generated
     * @ordered
     */
    MESSAGE(5340, "Message", "Message"),

    /**
     * The '<em><b>Synchronous Message</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #SYNCHRONOUS_MESSAGE_VALUE
     * @generated
     * @ordered
     */
    SYNCHRONOUS_MESSAGE(5341, "SynchronousMessage", "SynchronousMessage"),

    /**
     * The '<em><b>ASynchronous Message</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #ASYNCHRONOUS_MESSAGE_VALUE
     * @generated
     * @ordered
     */
    ASYNCHRONOUS_MESSAGE(5342, "ASynchronousMessage", "ASynchronousMessage"),

    /**
     * The '<em><b>Create Message</b></em>' literal object.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #CREATE_MESSAGE_VALUE
     * @generated
     * @ordered
     */
    CREATE_MESSAGE(5343, "CreateMessage", "CreateMessage"),

    /**
     * The '<em><b>Destroy Message</b></em>' literal object.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #DESTROY_MESSAGE_VALUE
     * @generated
     * @ordered
     */
    DESTROY_MESSAGE(5344, "DestroyMessage", "DestroyMessage"),

    /**
     * The '<em><b>Pseudostate</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #PSEUDOSTATE_VALUE
     * @generated
     * @ordered
     */
    PSEUDOSTATE(5350, "Pseudostate", "Pseudostate"),

    /**
     * The '<em><b>Choice Point</b></em>' literal object.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #CHOICE_POINT_VALUE
     * @generated
     * @ordered
     */
    CHOICE_POINT(5351, "ChoicePoint", "ChoicePoint"),

    /**
     * The '<em><b>Junction Point</b></em>' literal object.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #JUNCTION_POINT_VALUE
     * @generated
     * @ordered
     */
    JUNCTION_POINT(5352, "JunctionPoint", "JunctionPoint"),

    /**
     * The '<em><b>Deep History</b></em>' literal object.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #DEEP_HISTORY_VALUE
     * @generated
     * @ordered
     */
    DEEP_HISTORY(5353, "DeepHistory", "DeepHistory"),

    /**
     * The '<em><b>Shallow History</b></em>' literal object.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #SHALLOW_HISTORY_VALUE
     * @generated
     * @ordered
     */
    SHALLOW_HISTORY(5354, "ShallowHistory", "ShallowHistory"),

    /**
     * The '<em><b>Join</b></em>' literal object.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #JOIN_VALUE
     * @generated
     * @ordered
     */
    JOIN(5355, "Join", "Join"),

    /**
     * The '<em><b>Fork</b></em>' literal object.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #FORK_VALUE
     * @generated
     * @ordered
     */
    FORK(5356, "Fork", "Fork"),

    /**
     * The '<em><b>Entry Point</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #ENTRY_POINT_VALUE
     * @generated
     * @ordered
     */
    ENTRY_POINT(5357, "EntryPoint", "EntryPoint"),

    /**
     * The '<em><b>Exit Point</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #EXIT_POINT_VALUE
     * @generated
     * @ordered
     */
    EXIT_POINT(5358, "ExitPoint", "ExitPoint"),

    /**
     * The '<em><b>Terminate</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #TERMINATE_VALUE
     * @generated
     * @ordered
     */
    TERMINATE(5359, "Terminate", "Terminate"),

    /**
     * The '<em><b>Final State</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #FINAL_STATE_VALUE
     * @generated
     * @ordered
     */
    FINAL_STATE(5370, "FinalState", "FinalState"),

    /**
     * The '<em><b>State Invariant</b></em>' literal object.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #STATE_INVARIANT_VALUE
     * @generated
     * @ordered
     */
    STATE_INVARIANT(5390, "StateInvariant", "StateInvariant"),

    /**
     * The '<em><b>Transition</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #TRANSITION_VALUE
     * @generated
     * @ordered
     */
    TRANSITION(5400, "Transition", "Transition"),

    /**
     * The '<em><b>Collaboration Use</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #COLLABORATION_USE_VALUE
     * @generated
     * @ordered
     */
    COLLABORATION_USE(5410, "CollaborationUse", "CollaborationUse"),

    /**
     * The '<em><b>Property</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #PROPERTY_VALUE
     * @generated
     * @ordered
     */
    PROPERTY(5420, "Property", "Property"),

    /**
     * The '<em><b>Port</b></em>' literal object.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #PORT_VALUE
     * @generated
     * @ordered
     */
    PORT(5430, "Port", "Port"),

    /**
     * The '<em><b>Actor</b></em>' literal object.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #ACTOR_VALUE
     * @generated
     * @ordered
     */
    ACTOR(5440, "Actor", "Actor"),

    /**
     * The '<em><b>Element Import</b></em>' literal object.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #ELEMENT_IMPORT_VALUE
     * @generated
     * @ordered
     */
    ELEMENT_IMPORT(5450, "ElementImport", "ElementImport"),

    /**
     * The '<em><b>Generalization</b></em>' literal object.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #GENERALIZATION_VALUE
     * @generated
     * @ordered
     */
    GENERALIZATION(5460, "Generalization", "Generalization"),

    /**
     * The '<em><b>Use Case</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #USE_CASE_VALUE
     * @generated
     * @ordered
     */
    USE_CASE(5480, "UseCase", "UseCase"),

    /**
     * The '<em><b>Line</b></em>' literal object.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #LINE_VALUE
     * @generated
     * @ordered
     */
    LINE(5531, "Line", "Line"),

    /**
     * The '<em><b>Life Line Behavior</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #LIFE_LINE_BEHAVIOR_VALUE
     * @generated
     * @ordered
     */
    LIFE_LINE_BEHAVIOR(5532, "LifeLineBehavior", "LifeLineBehavior"),

    /**
     * The '<em><b>Lolly</b></em>' literal object.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #LOLLY_VALUE
     * @generated
     * @ordered
     */
    LOLLY(6001, "Lolly", "Lolly"),

    /**
     * The '<em><b>Half Lolly</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #HALF_LOLLY_VALUE
     * @generated
     * @ordered
     */
    HALF_LOLLY(6002, "HalfLolly", "HalfLolly"),

    /**
     * The '<em><b>Name</b></em>' literal object.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #NAME_VALUE
     * @generated
     * @ordered
     */
    NAME(7000, "Name", "Name"),

    /**
     * The '<em><b>Stereotype</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #STEREOTYPE_VALUE
     * @generated
     * @ordered
     */
    STEREOTYPE(8000, "Stereotype", "Stereotype"),

    /**
     * The '<em><b>Attributes</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #ATTRIBUTES_VALUE
     * @generated
     * @ordered
     */
    ATTRIBUTES(9000, "Attributes", "Attributes"),

    /**
     * The '<em><b>Attribute</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #ATTRIBUTE_VALUE
     * @generated
     * @ordered
     */
    ATTRIBUTE(9001, "Attribute", "Attribute"),

    /**
     * The '<em><b>Operations</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #OPERATIONS_VALUE
     * @generated
     * @ordered
     */
    OPERATIONS(9002, "Operations", "Operations"),

    /**
     * The '<em><b>Operation</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #OPERATION_VALUE
     * @generated
     * @ordered
     */
    OPERATION(9003, "Operation", "Operation"),

    /**
     * The '<em><b>Provided Interfaces</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #PROVIDED_INTERFACES_VALUE
     * @generated
     * @ordered
     */
    PROVIDED_INTERFACES(9004, "ProvidedInterfaces", "ProvidedInterfaces"),

    /**
     * The '<em><b>Provided Interface</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #PROVIDED_INTERFACE_VALUE
     * @generated
     * @ordered
     */
    PROVIDED_INTERFACE(9005, "ProvidedInterface", "ProvidedInterface"),

    /**
     * The '<em><b>Required Interfaces</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #REQUIRED_INTERFACES_VALUE
     * @generated
     * @ordered
     */
    REQUIRED_INTERFACES(9006, "RequiredInterfaces", "RequiredInterfaces"),

    /**
     * The '<em><b>Required Interface</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #REQUIRED_INTERFACE_VALUE
     * @generated
     * @ordered
     */
    REQUIRED_INTERFACE(9007, "RequiredInterface", "RequiredInterface"),

    /**
     * The '<em><b>Required Interface Operation</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #REQUIRED_INTERFACE_OPERATION_VALUE
     * @generated
     * @ordered
     */
    REQUIRED_INTERFACE_OPERATION(9008, "RequiredInterfaceOperation", "RequiredInterfaceOperation"),

    /**
     * The '<em><b>Provided Interface Operation</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #PROVIDED_INTERFACE_OPERATION_VALUE
     * @generated
     * @ordered
     */
    PROVIDED_INTERFACE_OPERATION(9009, "ProvidedInterfaceOperation", "ProvidedInterfaceOperation"), /**
     * The '<em><b>Enumeration Literals</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #ENUMERATION_LITERALS_VALUE
     * @generated
     * @ordered
     */
    ENUMERATION_LITERALS(9010, "EnumerationLiterals", "EnumerationLiterals"), /**
     * The '<em><b>Enumeration Literal</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #ENUMERATION_LITERAL_VALUE
     * @generated
     * @ordered
     */
    ENUMERATION_LITERAL(9011, "EnumerationLiteral", "EnumerationLiteral");

    /**
     * The '<em><b>Note</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Note</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #NOTE
     * @model name="Note"
     * @generated
     * @ordered
     */
    public static final int NOTE_VALUE = 0;

    /**
     * The '<em><b>Text</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Text</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #TEXT
     * @model name="Text"
     * @generated
     * @ordered
     */
    public static final int TEXT_VALUE = 1;

    /**
     * The '<em><b>Activity</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Activity</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #ACTIVITY
     * @model name="Activity"
     * @generated
     * @ordered
     */
    public static final int ACTIVITY_VALUE = 1100;

    /**
     * The '<em><b>Activity Partition</b></em>' literal value. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Activity Partition</b></em>' literal object
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #ACTIVITY_PARTITION
     * @model name="ActivityPartition"
     * @generated
     * @ordered
     */
    public static final int ACTIVITY_PARTITION_VALUE = 1200;

    /**
     * The '<em><b>Structured Activity Node</b></em>' literal value. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Structured Activity Node</b></em>' literal
     * object isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #STRUCTURED_ACTIVITY_NODE
     * @model name="StructuredActivityNode"
     * @generated
     * @ordered
     */
    public static final int STRUCTURED_ACTIVITY_NODE_VALUE = 1300;

    /**
     * The '<em><b>Component Structure Compartment</b></em>' literal value. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Component Structure Compartment</b></em>'
     * literal object isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #COMPONENT_STRUCTURE_COMPARTMENT
     * @model name="ComponentStructureCompartment"
     * @generated
     * @ordered
     */
    public static final int COMPONENT_STRUCTURE_COMPARTMENT_VALUE = 1400;

    /**
     * The '<em><b>Combined Fragment</b></em>' literal value. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Combined Fragment</b></em>' literal object
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #COMBINED_FRAGMENT
     * @model name="CombinedFragment"
     * @generated
     * @ordered
     */
    public static final int COMBINED_FRAGMENT_VALUE = 1500;

    /**
     * The '<em><b>Alternative Combined Fragment</b></em>' literal value. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Alternative Combined Fragment</b></em>' literal
     * object isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #ALTERNATIVE_COMBINED_FRAGMENT
     * @model name="AlternativeCombinedFragment"
     * @generated
     * @ordered
     */
    public static final int ALTERNATIVE_COMBINED_FRAGMENT_VALUE = 1501;

    /**
     * The '<em><b>Break Combined Fragment</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Break Combined Fragment</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #BREAK_COMBINED_FRAGMENT
     * @model name="BreakCombinedFragment"
     * @generated
     * @ordered
     */
    public static final int BREAK_COMBINED_FRAGMENT_VALUE = 1502;

    /**
     * The '<em><b>Loop Combined Fragment</b></em>' literal value. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Loop Combined Fragment</b></em>' literal object
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #LOOP_COMBINED_FRAGMENT
     * @model name="LoopCombinedFragment"
     * @generated
     * @ordered
     */
    public static final int LOOP_COMBINED_FRAGMENT_VALUE = 1503;

    /**
     * The '<em><b>Sequence Combined Fragment</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Sequence Combined Fragment</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #SEQUENCE_COMBINED_FRAGMENT
     * @model name="SequenceCombinedFragment"
     * @generated
     * @ordered
     */
    public static final int SEQUENCE_COMBINED_FRAGMENT_VALUE = 1504;

    /**
     * The '<em><b>Strict Combined Fragment</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Strict Combined Fragment</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #STRICT_COMBINED_FRAGMENT
     * @model name="StrictCombinedFragment"
     * @generated
     * @ordered
     */
    public static final int STRICT_COMBINED_FRAGMENT_VALUE = 1505;

    /**
     * The '<em><b>Parallel Combined Fragment</b></em>' literal value. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Parallel Combined Fragment</b></em>' literal
     * object isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #PARALLEL_COMBINED_FRAGMENT
     * @model name="ParallelCombinedFragment"
     * @generated
     * @ordered
     */
    public static final int PARALLEL_COMBINED_FRAGMENT_VALUE = 1506;

    /**
     * The '<em><b>Critical Combined Fragment</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Critical Combined Fragment</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #CRITICAL_COMBINED_FRAGMENT
     * @model name="CriticalCombinedFragment"
     * @generated
     * @ordered
     */
    public static final int CRITICAL_COMBINED_FRAGMENT_VALUE = 1507;

    /**
     * The '<em><b>Negative Combined Fragment</b></em>' literal value. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Negative Combined Fragment</b></em>' literal
     * object isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #NEGATIVE_COMBINED_FRAGMENT
     * @model name="NegativeCombinedFragment"
     * @generated
     * @ordered
     */
    public static final int NEGATIVE_COMBINED_FRAGMENT_VALUE = 1508;

    /**
     * The '<em><b>Assert Combined Fragment</b></em>' literal value. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Assert Combined Fragment</b></em>' literal
     * object isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #ASSERT_COMBINED_FRAGMENT
     * @model name="AssertCombinedFragment"
     * @generated
     * @ordered
     */
    public static final int ASSERT_COMBINED_FRAGMENT_VALUE = 1509;

    /**
     * The '<em><b>Ignore Combined Fragment</b></em>' literal value. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Ignore Combined Fragment</b></em>' literal
     * object isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #IGNORE_COMBINED_FRAGMENT
     * @model name="IgnoreCombinedFragment"
     * @generated
     * @ordered
     */
    public static final int IGNORE_COMBINED_FRAGMENT_VALUE = 1510;

    /**
     * The '<em><b>Consider Combined Fragment</b></em>' literal value. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Consider Combined Fragment</b></em>' literal
     * object isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #CONSIDER_COMBINED_FRAGMENT
     * @model name="ConsiderCombinedFragment"
     * @generated
     * @ordered
     */
    public static final int CONSIDER_COMBINED_FRAGMENT_VALUE = 1511;

    /**
     * The '<em><b>Option Combined Fragment</b></em>' literal value. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Option Combined Fragment</b></em>' literal
     * object isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #OPTION_COMBINED_FRAGMENT
     * @model name="OptionCombinedFragment"
     * @generated
     * @ordered
     */
    public static final int OPTION_COMBINED_FRAGMENT_VALUE = 1512;

    /**
     * The '<em><b>Combined Fragment Name</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Combined Fragment Name</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #COMBINED_FRAGMENT_NAME
     * @model name="CombinedFragmentName"
     * @generated
     * @ordered
     */
    public static final int COMBINED_FRAGMENT_NAME_VALUE = 1513;

    /**
     * The '<em><b>Interaction Operand</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Interaction Operand</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #INTERACTION_OPERAND
     * @model name="InteractionOperand"
     * @generated
     * @ordered
     */
    public static final int INTERACTION_OPERAND_VALUE = 1520;

    /**
     * The '<em><b>Guard</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Guard</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #GUARD
     * @model name="Guard"
     * @generated
     * @ordered
     */
    public static final int GUARD_VALUE = 1521;

    /**
     * The '<em><b>Option If</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Option If</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #OPTION_IF
     * @model name="OptionIf"
     * @generated
     * @ordered
     */
    public static final int OPTION_IF_VALUE = 1530;

    /**
     * The '<em><b>Alternative Switch</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Alternative Switch</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #ALTERNATIVE_SWITCH
     * @model name="AlternativeSwitch"
     * @generated
     * @ordered
     */
    public static final int ALTERNATIVE_SWITCH_VALUE = 1532;

    /**
     * The '<em><b>Alternative If Else</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Alternative If Else</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #ALTERNATIVE_IF_ELSE
     * @model name="AlternativeIfElse"
     * @generated
     * @ordered
     */
    public static final int ALTERNATIVE_IF_ELSE_VALUE = 1531;

    /**
     * The '<em><b>Loop While</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Loop While</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #LOOP_WHILE
     * @model name="LoopWhile"
     * @generated
     * @ordered
     */
    public static final int LOOP_WHILE_VALUE = 1533;

    /**
     * The '<em><b>Loop For</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Loop For</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #LOOP_FOR
     * @model name="LoopFor"
     * @generated
     * @ordered
     */
    public static final int LOOP_FOR_VALUE = 1534;

    /**
     * The '<em><b>Region</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Region</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #REGION
     * @model name="Region"
     * @generated
     * @ordered
     */
    public static final int REGION_VALUE = 1600;

    /**
     * The '<em><b>State</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>State</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #STATE
     * @model name="State"
     * @generated
     * @ordered
     */
    public static final int STATE_VALUE = 1700;

    /**
     * The '<em><b>Composite Structure</b></em>' literal value. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Composite Structure</b></em>' literal object
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #COMPOSITE_STRUCTURE
     * @model name="CompositeStructure"
     * @generated
     * @ordered
     */
    public static final int COMPOSITE_STRUCTURE_VALUE = 1800;

    /**
     * The '<em><b>Accept Event Action</b></em>' literal value. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Accept Event Action</b></em>' literal object
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #ACCEPT_EVENT_ACTION
     * @model name="AcceptEventAction"
     * @generated
     * @ordered
     */
    public static final int ACCEPT_EVENT_ACTION_VALUE = 5010;

    /**
     * The '<em><b>Activity Final Node</b></em>' literal value. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Activity Final Node</b></em>' literal object
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #ACTIVITY_FINAL_NODE
     * @model name="ActivityFinalNode"
     * @generated
     * @ordered
     */
    public static final int ACTIVITY_FINAL_NODE_VALUE = 5020;

    /**
     * The '<em><b>Activity Parameter Node</b></em>' literal value. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Activity Parameter Node</b></em>' literal
     * object isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #ACTIVITY_PARAMETER_NODE
     * @model name="ActivityParameterNode"
     * @generated
     * @ordered
     */
    public static final int ACTIVITY_PARAMETER_NODE_VALUE = 5030;

    /**
     * The '<em><b>Call Behavior Action</b></em>' literal value. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Call Behavior Action</b></em>' literal object
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #CALL_BEHAVIOR_ACTION
     * @model name="CallBehaviorAction"
     * @generated
     * @ordered
     */
    public static final int CALL_BEHAVIOR_ACTION_VALUE = 5040;

    /**
     * The '<em><b>Call Operation Action</b></em>' literal value. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Call Operation Action</b></em>' literal object
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #CALL_OPERATION_ACTION
     * @model name="CallOperationAction"
     * @generated
     * @ordered
     */
    public static final int CALL_OPERATION_ACTION_VALUE = 5050;

    /**
     * The '<em><b>Central Buffer Node</b></em>' literal value. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Central Buffer Node</b></em>' literal object
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #CENTRAL_BUFFER_NODE
     * @model name="CentralBufferNode"
     * @generated
     * @ordered
     */
    public static final int CENTRAL_BUFFER_NODE_VALUE = 5060;

    /**
     * The '<em><b>Data Store Node</b></em>' literal value.
     * <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of '<em><b>Data Store Node</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #DATA_STORE_NODE
     * @model name="DataStoreNode"
     * @generated
     * @ordered
     */
    public static final int DATA_STORE_NODE_VALUE = 5070;

    /**
     * The '<em><b>Decision Node</b></em>' literal value.
     * <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of '<em><b>Decision Node</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #DECISION_NODE
     * @model name="DecisionNode"
     * @generated
     * @ordered
     */
    public static final int DECISION_NODE_VALUE = 5080;

    /**
     * The '<em><b>Expansion Node</b></em>' literal value.
     * <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of '<em><b>Expansion Node</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #EXPANSION_NODE
     * @model name="ExpansionNode"
     * @generated
     * @ordered
     */
    public static final int EXPANSION_NODE_VALUE = 5090;

    /**
     * The '<em><b>Flow Final Node</b></em>' literal value.
     * <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of '<em><b>Flow Final Node</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #FLOW_FINAL_NODE
     * @model name="FlowFinalNode"
     * @generated
     * @ordered
     */
    public static final int FLOW_FINAL_NODE_VALUE = 5100;

    /**
     * The '<em><b>Fork Node</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Fork Node</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #FORK_NODE
     * @model name="ForkNode"
     * @generated
     * @ordered
     */
    public static final int FORK_NODE_VALUE = 5110;

    /**
     * The '<em><b>Initial Node</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Initial Node</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #INITIAL_NODE
     * @model name="InitialNode"
     * @generated
     * @ordered
     */
    public static final int INITIAL_NODE_VALUE = 5120;

    /**
     * The '<em><b>Input Pin</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Input Pin</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #INPUT_PIN
     * @model name="InputPin"
     * @generated
     * @ordered
     */
    public static final int INPUT_PIN_VALUE = 5130;

    /**
     * The '<em><b>Join Node</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Join Node</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #JOIN_NODE
     * @model name="JoinNode"
     * @generated
     * @ordered
     */
    public static final int JOIN_NODE_VALUE = 5140;

    /**
     * The '<em><b>Merge Node</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Merge Node</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #MERGE_NODE
     * @model name="MergeNode"
     * @generated
     * @ordered
     */
    public static final int MERGE_NODE_VALUE = 5150;

    /**
     * The '<em><b>Opaque Action</b></em>' literal value.
     * <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of '<em><b>Opaque Action</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #OPAQUE_ACTION
     * @model name="OpaqueAction"
     * @generated
     * @ordered
     */
    public static final int OPAQUE_ACTION_VALUE = 5160;

    /**
     * The '<em><b>Output Pin</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Output Pin</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #OUTPUT_PIN
     * @model name="OutputPin"
     * @generated
     * @ordered
     */
    public static final int OUTPUT_PIN_VALUE = 5170;

    /**
     * The '<em><b>Send Signal Action</b></em>' literal value. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Send Signal Action</b></em>' literal object
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #SEND_SIGNAL_ACTION
     * @model name="SendSignalAction"
     * @generated
     * @ordered
     */
    public static final int SEND_SIGNAL_ACTION_VALUE = 5180;

    /**
     * The '<em><b>Value Pin</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Value Pin</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #VALUE_PIN
     * @model name="ValuePin"
     * @generated
     * @ordered
     */
    public static final int VALUE_PIN_VALUE = 5190;

    /**
     * The '<em><b>Artifact</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Artifact</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #ARTIFACT
     * @model name="Artifact"
     * @generated
     * @ordered
     */
    public static final int ARTIFACT_VALUE = 5200;

    /**
     * The '<em><b>Class</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Class</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #CLASS
     * @model name="Class"
     * @generated
     * @ordered
     */
    public static final int CLASS_VALUE = 5210;

    /**
     * The '<em><b>Collaboration</b></em>' literal value.
     * <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of '<em><b>Collaboration</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #COLLABORATION
     * @model name="Collaboration"
     * @generated
     * @ordered
     */
    public static final int COLLABORATION_VALUE = 5220;

    /**
     * The '<em><b>Data Type</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Data Type</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #DATA_TYPE
     * @model name="DataType"
     * @generated
     * @ordered
     */
    public static final int DATA_TYPE_VALUE = 5230;

    /**
     * The '<em><b>Enumeration</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Enumeration</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #ENUMERATION
     * @model name="Enumeration"
     * @generated
     * @ordered
     */
    public static final int ENUMERATION_VALUE = 5240;

    /**
     * The '<em><b>Interface</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Interface</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #INTERFACE
     * @model name="Interface"
     * @generated
     * @ordered
     */
    public static final int INTERFACE_VALUE = 5250;

    /**
     * The '<em><b>Package</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Package</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #PACKAGE
     * @model name="Package"
     * @generated
     * @ordered
     */
    public static final int PACKAGE_VALUE = 5260;

    /**
     * The '<em><b>Signal</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Signal</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #SIGNAL
     * @model name="Signal"
     * @generated
     * @ordered
     */
    public static final int SIGNAL_VALUE = 5270;

    /**
     * The '<em><b>Component</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Component</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #COMPONENT
     * @model name="Component"
     * @generated
     * @ordered
     */
    public static final int COMPONENT_VALUE = 5290;

    /**
     * The '<em><b>Destruction Event</b></em>' literal value. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Destruction Event</b></em>' literal object
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #DESTRUCTION_EVENT
     * @model name="DestructionEvent"
     * @generated
     * @ordered
     */
    public static final int DESTRUCTION_EVENT_VALUE = 5310;

    /**
     * The '<em><b>Interaction Use</b></em>' literal value.
     * <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of '<em><b>Interaction Use</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #INTERACTION_USE
     * @model name="InteractionUse"
     * @generated
     * @ordered
     */
    public static final int INTERACTION_USE_VALUE = 5320;

    /**
     * The '<em><b>Lifeline</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Lifeline</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #LIFELINE
     * @model name="Lifeline"
     * @generated
     * @ordered
     */
    public static final int LIFELINE_VALUE = 5330;

    /**
     * The '<em><b>Message</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Message</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #MESSAGE
     * @model name="Message"
     * @generated
     * @ordered
     */
    public static final int MESSAGE_VALUE = 5340;

    /**
     * The '<em><b>Synchronous Message</b></em>' literal value. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Synchronous Message</b></em>' literal object
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #SYNCHRONOUS_MESSAGE
     * @model name="SynchronousMessage"
     * @generated
     * @ordered
     */
    public static final int SYNCHRONOUS_MESSAGE_VALUE = 5341;

    /**
     * The '<em><b>ASynchronous Message</b></em>' literal value. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>ASynchronous Message</b></em>' literal object
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #ASYNCHRONOUS_MESSAGE
     * @model name="ASynchronousMessage"
     * @generated
     * @ordered
     */
    public static final int ASYNCHRONOUS_MESSAGE_VALUE = 5342;

    /**
     * The '<em><b>Create Message</b></em>' literal value.
     * <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of '<em><b>Create Message</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #CREATE_MESSAGE
     * @model name="CreateMessage"
     * @generated
     * @ordered
     */
    public static final int CREATE_MESSAGE_VALUE = 5343;

    /**
     * The '<em><b>Destroy Message</b></em>' literal value.
     * <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of '<em><b>Destroy Message</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #DESTROY_MESSAGE
     * @model name="DestroyMessage"
     * @generated
     * @ordered
     */
    public static final int DESTROY_MESSAGE_VALUE = 5344;

    /**
     * The '<em><b>Pseudostate</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Pseudostate</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #PSEUDOSTATE
     * @model name="Pseudostate"
     * @generated
     * @ordered
     */
    public static final int PSEUDOSTATE_VALUE = 5350;

    /**
     * The '<em><b>Choice Point</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Choice Point</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #CHOICE_POINT
     * @model name="ChoicePoint"
     * @generated
     * @ordered
     */
    public static final int CHOICE_POINT_VALUE = 5351;

    /**
     * The '<em><b>Junction Point</b></em>' literal value.
     * <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of '<em><b>Junction Point</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #JUNCTION_POINT
     * @model name="JunctionPoint"
     * @generated
     * @ordered
     */
    public static final int JUNCTION_POINT_VALUE = 5352;

    /**
     * The '<em><b>Deep History</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Deep History</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #DEEP_HISTORY
     * @model name="DeepHistory"
     * @generated
     * @ordered
     */
    public static final int DEEP_HISTORY_VALUE = 5353;

    /**
     * The '<em><b>Shallow History</b></em>' literal value.
     * <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of '<em><b>Shallow History</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #SHALLOW_HISTORY
     * @model name="ShallowHistory"
     * @generated
     * @ordered
     */
    public static final int SHALLOW_HISTORY_VALUE = 5354;

    /**
     * The '<em><b>Join</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Join</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #JOIN
     * @model name="Join"
     * @generated
     * @ordered
     */
    public static final int JOIN_VALUE = 5355;

    /**
     * The '<em><b>Fork</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Fork</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #FORK
     * @model name="Fork"
     * @generated
     * @ordered
     */
    public static final int FORK_VALUE = 5356;

    /**
     * The '<em><b>Entry Point</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Entry Point</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #ENTRY_POINT
     * @model name="EntryPoint"
     * @generated
     * @ordered
     */
    public static final int ENTRY_POINT_VALUE = 5357;

    /**
     * The '<em><b>Exit Point</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Exit Point</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #EXIT_POINT
     * @model name="ExitPoint"
     * @generated
     * @ordered
     */
    public static final int EXIT_POINT_VALUE = 5358;

    /**
     * The '<em><b>Terminate</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Terminate</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #TERMINATE
     * @model name="Terminate"
     * @generated
     * @ordered
     */
    public static final int TERMINATE_VALUE = 5359;

    /**
     * The '<em><b>Final State</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Final State</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #FINAL_STATE
     * @model name="FinalState"
     * @generated
     * @ordered
     */
    public static final int FINAL_STATE_VALUE = 5370;

    /**
     * The '<em><b>State Invariant</b></em>' literal value.
     * <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of '<em><b>State Invariant</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #STATE_INVARIANT
     * @model name="StateInvariant"
     * @generated
     * @ordered
     */
    public static final int STATE_INVARIANT_VALUE = 5390;

    /**
     * The '<em><b>Transition</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Transition</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #TRANSITION
     * @model name="Transition"
     * @generated
     * @ordered
     */
    public static final int TRANSITION_VALUE = 5400;

    /**
     * The '<em><b>Collaboration Use</b></em>' literal value. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Collaboration Use</b></em>' literal object
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #COLLABORATION_USE
     * @model name="CollaborationUse"
     * @generated
     * @ordered
     */
    public static final int COLLABORATION_USE_VALUE = 5410;

    /**
     * The '<em><b>Property</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Property</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #PROPERTY
     * @model name="Property"
     * @generated
     * @ordered
     */
    public static final int PROPERTY_VALUE = 5420;

    /**
     * The '<em><b>Port</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Port</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #PORT
     * @model name="Port"
     * @generated
     * @ordered
     */
    public static final int PORT_VALUE = 5430;

    /**
     * The '<em><b>Actor</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Actor</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #ACTOR
     * @model name="Actor"
     * @generated
     * @ordered
     */
    public static final int ACTOR_VALUE = 5440;

    /**
     * The '<em><b>Element Import</b></em>' literal value.
     * <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of '<em><b>Element Import</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #ELEMENT_IMPORT
     * @model name="ElementImport"
     * @generated
     * @ordered
     */
    public static final int ELEMENT_IMPORT_VALUE = 5450;

    /**
     * The '<em><b>Generalization</b></em>' literal value.
     * <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of '<em><b>Generalization</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #GENERALIZATION
     * @model name="Generalization"
     * @generated
     * @ordered
     */
    public static final int GENERALIZATION_VALUE = 5460;

    /**
     * The '<em><b>Use Case</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Use Case</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #USE_CASE
     * @model name="UseCase"
     * @generated
     * @ordered
     */
    public static final int USE_CASE_VALUE = 5480;

    /**
     * The '<em><b>Line</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Line</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #LINE
     * @model name="Line"
     * @generated
     * @ordered
     */
    public static final int LINE_VALUE = 5531;

    /**
     * The '<em><b>Life Line Behavior</b></em>' literal value. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Life Line Behavior</b></em>' literal object
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #LIFE_LINE_BEHAVIOR
     * @model name="LifeLineBehavior"
     * @generated
     * @ordered
     */
    public static final int LIFE_LINE_BEHAVIOR_VALUE = 5532;

    /**
     * The '<em><b>Lolly</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Lolly</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #LOLLY
     * @model name="Lolly"
     * @generated
     * @ordered
     */
    public static final int LOLLY_VALUE = 6001;

    /**
     * The '<em><b>Half Lolly</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Half Lolly</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #HALF_LOLLY
     * @model name="HalfLolly"
     * @generated
     * @ordered
     */
    public static final int HALF_LOLLY_VALUE = 6002;

    /**
     * The '<em><b>Name</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Name</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #NAME
     * @model name="Name"
     * @generated
     * @ordered
     */
    public static final int NAME_VALUE = 7000;

    /**
     * The '<em><b>Stereotype</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Stereotype</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #STEREOTYPE
     * @model name="Stereotype"
     * @generated
     * @ordered
     */
    public static final int STEREOTYPE_VALUE = 8000;

    /**
     * The '<em><b>Attributes</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Attributes</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #ATTRIBUTES
     * @model name="Attributes"
     * @generated
     * @ordered
     */
    public static final int ATTRIBUTES_VALUE = 9000;

    /**
     * The '<em><b>Attribute</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Attribute</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #ATTRIBUTE
     * @model name="Attribute"
     * @generated
     * @ordered
     */
    public static final int ATTRIBUTE_VALUE = 9001;

    /**
     * The '<em><b>Operations</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Operations</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #OPERATIONS
     * @model name="Operations"
     * @generated
     * @ordered
     */
    public static final int OPERATIONS_VALUE = 9002;

    /**
     * The '<em><b>Operation</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Operation</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #OPERATION
     * @model name="Operation"
     * @generated
     * @ordered
     */
    public static final int OPERATION_VALUE = 9003;

    /**
     * The '<em><b>Provided Interfaces</b></em>' literal value. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Provided Interfaces</b></em>' literal object
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #PROVIDED_INTERFACES
     * @model name="ProvidedInterfaces"
     * @generated
     * @ordered
     */
    public static final int PROVIDED_INTERFACES_VALUE = 9004;

    /**
     * The '<em><b>Provided Interface</b></em>' literal value. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Provided Interface</b></em>' literal object
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #PROVIDED_INTERFACE
     * @model name="ProvidedInterface"
     * @generated
     * @ordered
     */
    public static final int PROVIDED_INTERFACE_VALUE = 9005;

    /**
     * The '<em><b>Required Interfaces</b></em>' literal value. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Required Interfaces</b></em>' literal object
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #REQUIRED_INTERFACES
     * @model name="RequiredInterfaces"
     * @generated
     * @ordered
     */
    public static final int REQUIRED_INTERFACES_VALUE = 9006;

    /**
     * The '<em><b>Required Interface</b></em>' literal value. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Required Interface</b></em>' literal object
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #REQUIRED_INTERFACE
     * @model name="RequiredInterface"
     * @generated
     * @ordered
     */
    public static final int REQUIRED_INTERFACE_VALUE = 9007;

    /**
     * The '<em><b>Required Interface Operation</b></em>' literal value. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Required Interface Operation</b></em>' literal
     * object isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #REQUIRED_INTERFACE_OPERATION
     * @model name="RequiredInterfaceOperation"
     * @generated
     * @ordered
     */
    public static final int REQUIRED_INTERFACE_OPERATION_VALUE = 9008;

    /**
     * The '<em><b>Provided Interface Operation</b></em>' literal value. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Provided Interface Operation</b></em>' literal
     * object isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #PROVIDED_INTERFACE_OPERATION
     * @model name="ProvidedInterfaceOperation"
     * @generated
     * @ordered
     */
    public static final int PROVIDED_INTERFACE_OPERATION_VALUE = 9009;

    /**
     * The '<em><b>Enumeration Literals</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Enumeration Literals</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #ENUMERATION_LITERALS
     * @model name="EnumerationLiterals"
     * @generated
     * @ordered
     */
    public static final int ENUMERATION_LITERALS_VALUE = 9010;

    /**
     * The '<em><b>Enumeration Literal</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Enumeration Literal</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #ENUMERATION_LITERAL
     * @model name="EnumerationLiteral"
     * @generated
     * @ordered
     */
    public static final int ENUMERATION_LITERAL_VALUE = 9011;

    /**
     * An array of all the '<em><b>Node Type</b></em>' enumerators. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private static final NodeType[] VALUES_ARRAY = new NodeType[] {
            NOTE,
            TEXT,
            ACTIVITY,
            ACTIVITY_PARTITION,
            STRUCTURED_ACTIVITY_NODE,
            COMPONENT_STRUCTURE_COMPARTMENT,
            COMBINED_FRAGMENT,
            ALTERNATIVE_COMBINED_FRAGMENT,
            BREAK_COMBINED_FRAGMENT,
            LOOP_COMBINED_FRAGMENT,
            SEQUENCE_COMBINED_FRAGMENT,
            STRICT_COMBINED_FRAGMENT,
            PARALLEL_COMBINED_FRAGMENT,
            CRITICAL_COMBINED_FRAGMENT,
            NEGATIVE_COMBINED_FRAGMENT,
            ASSERT_COMBINED_FRAGMENT,
            IGNORE_COMBINED_FRAGMENT,
            CONSIDER_COMBINED_FRAGMENT,
            OPTION_COMBINED_FRAGMENT,
            COMBINED_FRAGMENT_NAME,
            INTERACTION_OPERAND,
            GUARD,
            OPTION_IF,
            ALTERNATIVE_SWITCH,
            ALTERNATIVE_IF_ELSE,
            LOOP_WHILE,
            LOOP_FOR,
            REGION,
            STATE,
            COMPOSITE_STRUCTURE,
            ACCEPT_EVENT_ACTION,
            ACTIVITY_FINAL_NODE,
            ACTIVITY_PARAMETER_NODE,
            CALL_BEHAVIOR_ACTION,
            CALL_OPERATION_ACTION,
            CENTRAL_BUFFER_NODE,
            DATA_STORE_NODE,
            DECISION_NODE,
            EXPANSION_NODE,
            FLOW_FINAL_NODE,
            FORK_NODE,
            INITIAL_NODE,
            INPUT_PIN,
            JOIN_NODE,
            MERGE_NODE,
            OPAQUE_ACTION,
            OUTPUT_PIN,
            SEND_SIGNAL_ACTION,
            VALUE_PIN,
            ARTIFACT,
            CLASS,
            COLLABORATION,
            DATA_TYPE,
            ENUMERATION,
            INTERFACE,
            PACKAGE,
            SIGNAL,
            COMPONENT,
            DESTRUCTION_EVENT,
            INTERACTION_USE,
            LIFELINE,
            MESSAGE,
            SYNCHRONOUS_MESSAGE,
            ASYNCHRONOUS_MESSAGE,
            CREATE_MESSAGE,
            DESTROY_MESSAGE,
            PSEUDOSTATE,
            CHOICE_POINT,
            JUNCTION_POINT,
            DEEP_HISTORY,
            SHALLOW_HISTORY,
            JOIN,
            FORK,
            ENTRY_POINT,
            EXIT_POINT,
            TERMINATE,
            FINAL_STATE,
            STATE_INVARIANT,
            TRANSITION,
            COLLABORATION_USE,
            PROPERTY,
            PORT,
            ACTOR,
            ELEMENT_IMPORT,
            GENERALIZATION,
            USE_CASE,
            LINE,
            LIFE_LINE_BEHAVIOR,
            LOLLY,
            HALF_LOLLY,
            NAME,
            STEREOTYPE,
            ATTRIBUTES,
            ATTRIBUTE,
            OPERATIONS,
            OPERATION,
            PROVIDED_INTERFACES,
            PROVIDED_INTERFACE,
            REQUIRED_INTERFACES,
            REQUIRED_INTERFACE,
            REQUIRED_INTERFACE_OPERATION,
            PROVIDED_INTERFACE_OPERATION,
            ENUMERATION_LITERALS,
            ENUMERATION_LITERAL,
        };

    /**
     * A public read-only list of all the '<em><b>Node Type</b></em>' enumerators.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static final List<NodeType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Node Type</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static NodeType get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            NodeType result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Node Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static NodeType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            NodeType result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Node Type</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static NodeType get(int value) {
        switch (value) {
            case NOTE_VALUE: return NOTE;
            case TEXT_VALUE: return TEXT;
            case ACTIVITY_VALUE: return ACTIVITY;
            case ACTIVITY_PARTITION_VALUE: return ACTIVITY_PARTITION;
            case STRUCTURED_ACTIVITY_NODE_VALUE: return STRUCTURED_ACTIVITY_NODE;
            case COMPONENT_STRUCTURE_COMPARTMENT_VALUE: return COMPONENT_STRUCTURE_COMPARTMENT;
            case COMBINED_FRAGMENT_VALUE: return COMBINED_FRAGMENT;
            case ALTERNATIVE_COMBINED_FRAGMENT_VALUE: return ALTERNATIVE_COMBINED_FRAGMENT;
            case BREAK_COMBINED_FRAGMENT_VALUE: return BREAK_COMBINED_FRAGMENT;
            case LOOP_COMBINED_FRAGMENT_VALUE: return LOOP_COMBINED_FRAGMENT;
            case SEQUENCE_COMBINED_FRAGMENT_VALUE: return SEQUENCE_COMBINED_FRAGMENT;
            case STRICT_COMBINED_FRAGMENT_VALUE: return STRICT_COMBINED_FRAGMENT;
            case PARALLEL_COMBINED_FRAGMENT_VALUE: return PARALLEL_COMBINED_FRAGMENT;
            case CRITICAL_COMBINED_FRAGMENT_VALUE: return CRITICAL_COMBINED_FRAGMENT;
            case NEGATIVE_COMBINED_FRAGMENT_VALUE: return NEGATIVE_COMBINED_FRAGMENT;
            case ASSERT_COMBINED_FRAGMENT_VALUE: return ASSERT_COMBINED_FRAGMENT;
            case IGNORE_COMBINED_FRAGMENT_VALUE: return IGNORE_COMBINED_FRAGMENT;
            case CONSIDER_COMBINED_FRAGMENT_VALUE: return CONSIDER_COMBINED_FRAGMENT;
            case OPTION_COMBINED_FRAGMENT_VALUE: return OPTION_COMBINED_FRAGMENT;
            case COMBINED_FRAGMENT_NAME_VALUE: return COMBINED_FRAGMENT_NAME;
            case INTERACTION_OPERAND_VALUE: return INTERACTION_OPERAND;
            case GUARD_VALUE: return GUARD;
            case OPTION_IF_VALUE: return OPTION_IF;
            case ALTERNATIVE_SWITCH_VALUE: return ALTERNATIVE_SWITCH;
            case ALTERNATIVE_IF_ELSE_VALUE: return ALTERNATIVE_IF_ELSE;
            case LOOP_WHILE_VALUE: return LOOP_WHILE;
            case LOOP_FOR_VALUE: return LOOP_FOR;
            case REGION_VALUE: return REGION;
            case STATE_VALUE: return STATE;
            case COMPOSITE_STRUCTURE_VALUE: return COMPOSITE_STRUCTURE;
            case ACCEPT_EVENT_ACTION_VALUE: return ACCEPT_EVENT_ACTION;
            case ACTIVITY_FINAL_NODE_VALUE: return ACTIVITY_FINAL_NODE;
            case ACTIVITY_PARAMETER_NODE_VALUE: return ACTIVITY_PARAMETER_NODE;
            case CALL_BEHAVIOR_ACTION_VALUE: return CALL_BEHAVIOR_ACTION;
            case CALL_OPERATION_ACTION_VALUE: return CALL_OPERATION_ACTION;
            case CENTRAL_BUFFER_NODE_VALUE: return CENTRAL_BUFFER_NODE;
            case DATA_STORE_NODE_VALUE: return DATA_STORE_NODE;
            case DECISION_NODE_VALUE: return DECISION_NODE;
            case EXPANSION_NODE_VALUE: return EXPANSION_NODE;
            case FLOW_FINAL_NODE_VALUE: return FLOW_FINAL_NODE;
            case FORK_NODE_VALUE: return FORK_NODE;
            case INITIAL_NODE_VALUE: return INITIAL_NODE;
            case INPUT_PIN_VALUE: return INPUT_PIN;
            case JOIN_NODE_VALUE: return JOIN_NODE;
            case MERGE_NODE_VALUE: return MERGE_NODE;
            case OPAQUE_ACTION_VALUE: return OPAQUE_ACTION;
            case OUTPUT_PIN_VALUE: return OUTPUT_PIN;
            case SEND_SIGNAL_ACTION_VALUE: return SEND_SIGNAL_ACTION;
            case VALUE_PIN_VALUE: return VALUE_PIN;
            case ARTIFACT_VALUE: return ARTIFACT;
            case CLASS_VALUE: return CLASS;
            case COLLABORATION_VALUE: return COLLABORATION;
            case DATA_TYPE_VALUE: return DATA_TYPE;
            case ENUMERATION_VALUE: return ENUMERATION;
            case INTERFACE_VALUE: return INTERFACE;
            case PACKAGE_VALUE: return PACKAGE;
            case SIGNAL_VALUE: return SIGNAL;
            case COMPONENT_VALUE: return COMPONENT;
            case DESTRUCTION_EVENT_VALUE: return DESTRUCTION_EVENT;
            case INTERACTION_USE_VALUE: return INTERACTION_USE;
            case LIFELINE_VALUE: return LIFELINE;
            case MESSAGE_VALUE: return MESSAGE;
            case SYNCHRONOUS_MESSAGE_VALUE: return SYNCHRONOUS_MESSAGE;
            case ASYNCHRONOUS_MESSAGE_VALUE: return ASYNCHRONOUS_MESSAGE;
            case CREATE_MESSAGE_VALUE: return CREATE_MESSAGE;
            case DESTROY_MESSAGE_VALUE: return DESTROY_MESSAGE;
            case PSEUDOSTATE_VALUE: return PSEUDOSTATE;
            case CHOICE_POINT_VALUE: return CHOICE_POINT;
            case JUNCTION_POINT_VALUE: return JUNCTION_POINT;
            case DEEP_HISTORY_VALUE: return DEEP_HISTORY;
            case SHALLOW_HISTORY_VALUE: return SHALLOW_HISTORY;
            case JOIN_VALUE: return JOIN;
            case FORK_VALUE: return FORK;
            case ENTRY_POINT_VALUE: return ENTRY_POINT;
            case EXIT_POINT_VALUE: return EXIT_POINT;
            case TERMINATE_VALUE: return TERMINATE;
            case FINAL_STATE_VALUE: return FINAL_STATE;
            case STATE_INVARIANT_VALUE: return STATE_INVARIANT;
            case TRANSITION_VALUE: return TRANSITION;
            case COLLABORATION_USE_VALUE: return COLLABORATION_USE;
            case PROPERTY_VALUE: return PROPERTY;
            case PORT_VALUE: return PORT;
            case ACTOR_VALUE: return ACTOR;
            case ELEMENT_IMPORT_VALUE: return ELEMENT_IMPORT;
            case GENERALIZATION_VALUE: return GENERALIZATION;
            case USE_CASE_VALUE: return USE_CASE;
            case LINE_VALUE: return LINE;
            case LIFE_LINE_BEHAVIOR_VALUE: return LIFE_LINE_BEHAVIOR;
            case LOLLY_VALUE: return LOLLY;
            case HALF_LOLLY_VALUE: return HALF_LOLLY;
            case NAME_VALUE: return NAME;
            case STEREOTYPE_VALUE: return STEREOTYPE;
            case ATTRIBUTES_VALUE: return ATTRIBUTES;
            case ATTRIBUTE_VALUE: return ATTRIBUTE;
            case OPERATIONS_VALUE: return OPERATIONS;
            case OPERATION_VALUE: return OPERATION;
            case PROVIDED_INTERFACES_VALUE: return PROVIDED_INTERFACES;
            case PROVIDED_INTERFACE_VALUE: return PROVIDED_INTERFACE;
            case REQUIRED_INTERFACES_VALUE: return REQUIRED_INTERFACES;
            case REQUIRED_INTERFACE_VALUE: return REQUIRED_INTERFACE;
            case REQUIRED_INTERFACE_OPERATION_VALUE: return REQUIRED_INTERFACE_OPERATION;
            case PROVIDED_INTERFACE_OPERATION_VALUE: return PROVIDED_INTERFACE_OPERATION;
            case ENUMERATION_LITERALS_VALUE: return ENUMERATION_LITERALS;
            case ENUMERATION_LITERAL_VALUE: return ENUMERATION_LITERAL;
        }
        return null;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private final int value;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private final String name;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private final String literal;

    /**
     * Only this class can construct instances.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     */
    private NodeType(int value, String name, String literal) {
        this.value = value;
        this.name = name;
        this.literal = literal;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public int getValue() {
      return value;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public String getName() {
      return name;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public String getLiteral() {
      return literal;
    }

    /**
     * Returns the literal value of the enumerator, which is its string representation.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        return literal;
    }

} // NodeType
