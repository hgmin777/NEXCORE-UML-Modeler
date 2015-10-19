/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.model.umldiagram;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '
 * <em><b>Relation Type</b></em>', and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getRelationType()
 * @model
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.umldiagram</li>
 * <li>설  명 : RelationType</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public enum RelationType implements Enumerator {
    /**
     * The '<em><b>Attachement</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #ATTACHEMENT_VALUE
     * @generated
     * @ordered
     */
    ATTACHEMENT(2, "Attachement", "Attachement"),

    /**
     * The '<em><b>Association</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #ASSOCIATION_VALUE
     * @generated
     * @ordered
     */
    ASSOCIATION(9010, "Association", "Association"),

    /**
     * The '<em><b>Aggregation</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #AGGREGATION_VALUE
     * @generated
     * @ordered
     */
    AGGREGATION(9011, "Aggregation", "Aggregation"),

    /**
     * The '<em><b>Composition</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #COMPOSITION_VALUE
     * @generated
     * @ordered
     */
    COMPOSITION(9012, "Composition", "Composition"),

    /**
     * The '<em><b>Directed Aggregation</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #DIRECTED_AGGREGATION_VALUE
     * @generated
     * @ordered
     */
    DIRECTED_AGGREGATION(9013, "DirectedAggregation", "DirectedAggregation"),

    /**
     * The '<em><b>Directed Association</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #DIRECTED_ASSOCIATION_VALUE
     * @generated
     * @ordered
     */
    DIRECTED_ASSOCIATION(9014, "DirectedAssociation", "DirectedAssociation"),

    /**
     * The '<em><b>Directed Composition</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #DIRECTED_COMPOSITION_VALUE
     * @generated
     * @ordered
     */
    DIRECTED_COMPOSITION(9015, "DirectedComposition", "DirectedComposition"),

    /**
     * The '<em><b>Connector</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #CONNECTOR_VALUE
     * @generated
     * @ordered
     */
    CONNECTOR(9020, "Connector", "Connector"),

    /**
     * The '<em><b>Control Flow</b></em>' literal object.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #CONTROL_FLOW_VALUE
     * @generated
     * @ordered
     */
    CONTROL_FLOW(9030, "ControlFlow", "ControlFlow"),

    /**
     * The '<em><b>Dependency</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #DEPENDENCY_VALUE
     * @generated
     * @ordered
     */
    DEPENDENCY(9040, "Dependency", "Dependency"),

    /**
     * The '<em><b>Abstraction</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #ABSTRACTION_VALUE
     * @generated
     * @ordered
     */
    ABSTRACTION(9041, "Abstraction", "Abstraction"),

    /**
     * The '<em><b>Extend</b></em>' literal object.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #EXTEND_VALUE
     * @generated
     * @ordered
     */
    EXTEND(9042, "Extend", "Extend"),

    /**
     * The '<em><b>Include</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #INCLUDE_VALUE
     * @generated
     * @ordered
     */
    INCLUDE(9043, "Include", "Include"),

    /**
     * The '<em><b>Package Import</b></em>' literal object.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #PACKAGE_IMPORT_VALUE
     * @generated
     * @ordered
     */
    PACKAGE_IMPORT(9044, "PackageImport", "PackageImport"),

    /**
     * The '<em><b>Package Merge</b></em>' literal object.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #PACKAGE_MERGE_VALUE
     * @generated
     * @ordered
     */
    PACKAGE_MERGE(9045, "PackageMerge", "PackageMerge"),

    /**
     * The '<em><b>Usage</b></em>' literal object.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #USAGE_VALUE
     * @generated
     * @ordered
     */
    USAGE(9046, "Usage", "Usage"),

    /**
     * The '<em><b>Generalization</b></em>' literal object.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #GENERALIZATION_VALUE
     * @generated
     * @ordered
     */
    GENERALIZATION(9050, "Generalization", "Generalization"),

    /**
     * The '<em><b>Object Flow</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #OBJECT_FLOW_VALUE
     * @generated
     * @ordered
     */
    OBJECT_FLOW(9060, "ObjectFlow", "ObjectFlow"),

    /**
     * The '<em><b>Realization</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #REALIZATION_VALUE
     * @generated
     * @ordered
     */
    REALIZATION(9070, "Realization", "Realization"),

    /**
     * The '<em><b>Component Realization</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #COMPONENT_REALIZATION_VALUE
     * @generated
     * @ordered
     */
    COMPONENT_REALIZATION(9080, "ComponentRealization", "ComponentRealization"),

    /**
     * The '<em><b>Interface Realization</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #INTERFACE_REALIZATION_VALUE
     * @generated
     * @ordered
     */
    INTERFACE_REALIZATION(9090, "InterfaceRealization", "InterfaceRealization"),

    /**
     * The '<em><b>Message</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #MESSAGE_VALUE
     * @generated
     * @ordered
     */
    MESSAGE(9100, "Message", "Message"),

    /**
     * The '<em><b>Synchronous Message</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #SYNCHRONOUS_MESSAGE_VALUE
     * @generated
     * @ordered
     */
    SYNCHRONOUS_MESSAGE(9101, "SynchronousMessage", "SynchronousMessage"),

    /**
     * The '<em><b>ASynchronous Message</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #ASYNCHRONOUS_MESSAGE_VALUE
     * @generated
     * @ordered
     */
    ASYNCHRONOUS_MESSAGE(9102, "ASynchronousMessage", "ASynchronousMessage"),

    /**
     * The '<em><b>Create Message</b></em>' literal object.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #CREATE_MESSAGE_VALUE
     * @generated
     * @ordered
     */
    CREATE_MESSAGE(9103, "CreateMessage", "CreateMessage"),

    /**
     * The '<em><b>Destroy Message</b></em>' literal object.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #DESTROY_MESSAGE_VALUE
     * @generated
     * @ordered
     */
    DESTROY_MESSAGE(9104, "DestroyMessage", "DestroyMessage"),

    /**
     * The '<em><b>Reply Message</b></em>' literal object.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #REPLY_MESSAGE_VALUE
     * @generated
     * @ordered
     */
    REPLY_MESSAGE(9105, "ReplyMessage", "ReplyMessage");

    /**
     * The '<em><b>Attachement</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Attachement</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #ATTACHEMENT
     * @model name="Attachement"
     * @generated
     * @ordered
     */
    public static final int ATTACHEMENT_VALUE = 2;

    /**
     * The '<em><b>Association</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Association</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #ASSOCIATION
     * @model name="Association"
     * @generated
     * @ordered
     */
    public static final int ASSOCIATION_VALUE = 9010;

    /**
     * The '<em><b>Aggregation</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Aggregation</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #AGGREGATION
     * @model name="Aggregation"
     * @generated
     * @ordered
     */
    public static final int AGGREGATION_VALUE = 9011;

    /**
     * The '<em><b>Composition</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Composition</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #COMPOSITION
     * @model name="Composition"
     * @generated
     * @ordered
     */
    public static final int COMPOSITION_VALUE = 9012;

    /**
     * The '<em><b>Directed Aggregation</b></em>' literal value. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Directed Aggregation</b></em>' literal object
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #DIRECTED_AGGREGATION
     * @model name="DirectedAggregation"
     * @generated
     * @ordered
     */
    public static final int DIRECTED_AGGREGATION_VALUE = 9013;

    /**
     * The '<em><b>Directed Association</b></em>' literal value. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Directed Association</b></em>' literal object
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #DIRECTED_ASSOCIATION
     * @model name="DirectedAssociation"
     * @generated
     * @ordered
     */
    public static final int DIRECTED_ASSOCIATION_VALUE = 9014;

    /**
     * The '<em><b>Directed Composition</b></em>' literal value. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Directed Composition</b></em>' literal object
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #DIRECTED_COMPOSITION
     * @model name="DirectedComposition"
     * @generated
     * @ordered
     */
    public static final int DIRECTED_COMPOSITION_VALUE = 9015;

    /**
     * The '<em><b>Connector</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Connector</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #CONNECTOR
     * @model name="Connector"
     * @generated
     * @ordered
     */
    public static final int CONNECTOR_VALUE = 9020;

    /**
     * The '<em><b>Control Flow</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Control Flow</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #CONTROL_FLOW
     * @model name="ControlFlow"
     * @generated
     * @ordered
     */
    public static final int CONTROL_FLOW_VALUE = 9030;

    /**
     * The '<em><b>Dependency</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Dependency</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #DEPENDENCY
     * @model name="Dependency"
     * @generated
     * @ordered
     */
    public static final int DEPENDENCY_VALUE = 9040;

    /**
     * The '<em><b>Abstraction</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Abstraction</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #ABSTRACTION
     * @model name="Abstraction"
     * @generated
     * @ordered
     */
    public static final int ABSTRACTION_VALUE = 9041;

    /**
     * The '<em><b>Extend</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Extend</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #EXTEND
     * @model name="Extend"
     * @generated
     * @ordered
     */
    public static final int EXTEND_VALUE = 9042;

    /**
     * The '<em><b>Include</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Include</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #INCLUDE
     * @model name="Include"
     * @generated
     * @ordered
     */
    public static final int INCLUDE_VALUE = 9043;

    /**
     * The '<em><b>Package Import</b></em>' literal value.
     * <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of '<em><b>Package Import</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #PACKAGE_IMPORT
     * @model name="PackageImport"
     * @generated
     * @ordered
     */
    public static final int PACKAGE_IMPORT_VALUE = 9044;

    /**
     * The '<em><b>Package Merge</b></em>' literal value.
     * <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of '<em><b>Package Merge</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #PACKAGE_MERGE
     * @model name="PackageMerge"
     * @generated
     * @ordered
     */
    public static final int PACKAGE_MERGE_VALUE = 9045;

    /**
     * The '<em><b>Usage</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Usage</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #USAGE
     * @model name="Usage"
     * @generated
     * @ordered
     */
    public static final int USAGE_VALUE = 9046;

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
    public static final int GENERALIZATION_VALUE = 9050;

    /**
     * The '<em><b>Object Flow</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Object Flow</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #OBJECT_FLOW
     * @model name="ObjectFlow"
     * @generated
     * @ordered
     */
    public static final int OBJECT_FLOW_VALUE = 9060;

    /**
     * The '<em><b>Realization</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Realization</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #REALIZATION
     * @model name="Realization"
     * @generated
     * @ordered
     */
    public static final int REALIZATION_VALUE = 9070;

    /**
     * The '<em><b>Component Realization</b></em>' literal value. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Component Realization</b></em>' literal object
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #COMPONENT_REALIZATION
     * @model name="ComponentRealization"
     * @generated
     * @ordered
     */
    public static final int COMPONENT_REALIZATION_VALUE = 9080;

    /**
     * The '<em><b>Interface Realization</b></em>' literal value. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Interface Realization</b></em>' literal object
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #INTERFACE_REALIZATION
     * @model name="InterfaceRealization"
     * @generated
     * @ordered
     */
    public static final int INTERFACE_REALIZATION_VALUE = 9090;

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
    public static final int MESSAGE_VALUE = 9100;

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
    public static final int SYNCHRONOUS_MESSAGE_VALUE = 9101;

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
    public static final int ASYNCHRONOUS_MESSAGE_VALUE = 9102;

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
    public static final int CREATE_MESSAGE_VALUE = 9103;

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
    public static final int DESTROY_MESSAGE_VALUE = 9104;

    /**
     * The '<em><b>Reply Message</b></em>' literal value.
     * <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of '<em><b>Reply Message</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #REPLY_MESSAGE
     * @model name="ReplyMessage"
     * @generated
     * @ordered
     */
    public static final int REPLY_MESSAGE_VALUE = 9105;

    /**
     * An array of all the '<em><b>Relation Type</b></em>' enumerators. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private static final RelationType[] VALUES_ARRAY = new RelationType[] {
            ATTACHEMENT,
            ASSOCIATION,
            AGGREGATION,
            COMPOSITION,
            DIRECTED_AGGREGATION,
            DIRECTED_ASSOCIATION,
            DIRECTED_COMPOSITION,
            CONNECTOR,
            CONTROL_FLOW,
            DEPENDENCY,
            ABSTRACTION,
            EXTEND,
            INCLUDE,
            PACKAGE_IMPORT,
            PACKAGE_MERGE,
            USAGE,
            GENERALIZATION,
            OBJECT_FLOW,
            REALIZATION,
            COMPONENT_REALIZATION,
            INTERFACE_REALIZATION,
            MESSAGE,
            SYNCHRONOUS_MESSAGE,
            ASYNCHRONOUS_MESSAGE,
            CREATE_MESSAGE,
            DESTROY_MESSAGE,
            REPLY_MESSAGE,
        };

    /**
     * A public read-only list of all the '<em><b>Relation Type</b></em>' enumerators.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static final List<RelationType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Relation Type</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static RelationType get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            RelationType result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Relation Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static RelationType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            RelationType result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Relation Type</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static RelationType get(int value) {
        switch (value) {
            case ATTACHEMENT_VALUE: return ATTACHEMENT;
            case ASSOCIATION_VALUE: return ASSOCIATION;
            case AGGREGATION_VALUE: return AGGREGATION;
            case COMPOSITION_VALUE: return COMPOSITION;
            case DIRECTED_AGGREGATION_VALUE: return DIRECTED_AGGREGATION;
            case DIRECTED_ASSOCIATION_VALUE: return DIRECTED_ASSOCIATION;
            case DIRECTED_COMPOSITION_VALUE: return DIRECTED_COMPOSITION;
            case CONNECTOR_VALUE: return CONNECTOR;
            case CONTROL_FLOW_VALUE: return CONTROL_FLOW;
            case DEPENDENCY_VALUE: return DEPENDENCY;
            case ABSTRACTION_VALUE: return ABSTRACTION;
            case EXTEND_VALUE: return EXTEND;
            case INCLUDE_VALUE: return INCLUDE;
            case PACKAGE_IMPORT_VALUE: return PACKAGE_IMPORT;
            case PACKAGE_MERGE_VALUE: return PACKAGE_MERGE;
            case USAGE_VALUE: return USAGE;
            case GENERALIZATION_VALUE: return GENERALIZATION;
            case OBJECT_FLOW_VALUE: return OBJECT_FLOW;
            case REALIZATION_VALUE: return REALIZATION;
            case COMPONENT_REALIZATION_VALUE: return COMPONENT_REALIZATION;
            case INTERFACE_REALIZATION_VALUE: return INTERFACE_REALIZATION;
            case MESSAGE_VALUE: return MESSAGE;
            case SYNCHRONOUS_MESSAGE_VALUE: return SYNCHRONOUS_MESSAGE;
            case ASYNCHRONOUS_MESSAGE_VALUE: return ASYNCHRONOUS_MESSAGE;
            case CREATE_MESSAGE_VALUE: return CREATE_MESSAGE;
            case DESTROY_MESSAGE_VALUE: return DESTROY_MESSAGE;
            case REPLY_MESSAGE_VALUE: return REPLY_MESSAGE;
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
    private RelationType(int value, String name, String literal) {
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

} // RelationType
