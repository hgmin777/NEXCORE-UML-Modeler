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
 * <em><b>Diagram Type</b></em>', and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getDiagramType()
 * @model
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.umldiagram</li>
 * <li>설  명 : DiagramType</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public enum DiagramType implements Enumerator {
    /**
     * The '<em><b>Use Case Diagram</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #USE_CASE_DIAGRAM_VALUE
     * @generated
     * @ordered
     */
    USE_CASE_DIAGRAM(0, "UseCaseDiagram", "UseCaseDiagram"),

    /**
     * The '<em><b>Class Diagram</b></em>' literal object.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #CLASS_DIAGRAM_VALUE
     * @generated
     * @ordered
     */
    CLASS_DIAGRAM(1, "ClassDiagram", "ClassDiagram"),

    /**
     * The '<em><b>Sequence Diagram</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #SEQUENCE_DIAGRAM_VALUE
     * @generated
     * @ordered
     */
    SEQUENCE_DIAGRAM(2, "SequenceDiagram", "SequenceDiagram"),

    /**
     * The '<em><b>Component Diagram</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #COMPONENT_DIAGRAM_VALUE
     * @generated
     * @ordered
     */
    COMPONENT_DIAGRAM(3, "ComponentDiagram", "ComponentDiagram"),

    /**
     * The '<em><b>Activity Diagram</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #ACTIVITY_DIAGRAM_VALUE
     * @generated
     * @ordered
     */
    ACTIVITY_DIAGRAM(4, "ActivityDiagram", "ActivityDiagram"),

    /**
     * The '<em><b>State Machine Diagram</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #STATE_MACHINE_DIAGRAM_VALUE
     * @generated
     * @ordered
     */
    STATE_MACHINE_DIAGRAM(5, "StateMachineDiagram", "StateMachineDiagram"),

    /**
     * The '<em><b>Deployment Diagram</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #DEPLOYMENT_DIAGRAM_VALUE
     * @generated
     * @ordered
     */
    DEPLOYMENT_DIAGRAM(6, "DeploymentDiagram", "DeploymentDiagram"),

    /**
     * The '<em><b>Composite Structure Diagram</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #COMPOSITE_STRUCTURE_DIAGRAM_VALUE
     * @generated
     * @ordered
     */
    COMPOSITE_STRUCTURE_DIAGRAM(7, "CompositeStructureDiagram", "CompositeStructureDiagram"),

    /**
     * The '<em><b>Communication Diagram</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #COMMUNICATION_DIAGRAM_VALUE
     * @generated
     * @ordered
     */
    COMMUNICATION_DIAGRAM(8, "CommunicationDiagram", "CommunicationDiagram"),

    /**
     * The '<em><b>Free Form Diagram</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #FREE_FORM_DIAGRAM_VALUE
     * @generated
     * @ordered
     */
    FREE_FORM_DIAGRAM(9, "FreeFormDiagram", "FreeFormDiagram");

    /**
     * The '<em><b>Use Case Diagram</b></em>' literal value.
     * <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of '<em><b>Use Case Diagram</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #USE_CASE_DIAGRAM
     * @model name="UseCaseDiagram"
     * @generated
     * @ordered
     */
    public static final int USE_CASE_DIAGRAM_VALUE = 0;

    /**
     * The '<em><b>Class Diagram</b></em>' literal value.
     * <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of '<em><b>Class Diagram</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #CLASS_DIAGRAM
     * @model name="ClassDiagram"
     * @generated
     * @ordered
     */
    public static final int CLASS_DIAGRAM_VALUE = 1;

    /**
     * The '<em><b>Sequence Diagram</b></em>' literal value.
     * <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of '<em><b>Sequence Diagram</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #SEQUENCE_DIAGRAM
     * @model name="SequenceDiagram"
     * @generated
     * @ordered
     */
    public static final int SEQUENCE_DIAGRAM_VALUE = 2;

    /**
     * The '<em><b>Component Diagram</b></em>' literal value. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Component Diagram</b></em>' literal object
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #COMPONENT_DIAGRAM
     * @model name="ComponentDiagram"
     * @generated
     * @ordered
     */
    public static final int COMPONENT_DIAGRAM_VALUE = 3;

    /**
     * The '<em><b>Activity Diagram</b></em>' literal value.
     * <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of '<em><b>Activity Diagram</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #ACTIVITY_DIAGRAM
     * @model name="ActivityDiagram"
     * @generated
     * @ordered
     */
    public static final int ACTIVITY_DIAGRAM_VALUE = 4;

    /**
     * The '<em><b>State Machine Diagram</b></em>' literal value. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>State Machine Diagram</b></em>' literal object
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #STATE_MACHINE_DIAGRAM
     * @model name="StateMachineDiagram"
     * @generated
     * @ordered
     */
    public static final int STATE_MACHINE_DIAGRAM_VALUE = 5;

    /**
     * The '<em><b>Deployment Diagram</b></em>' literal value. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Deployment Diagram</b></em>' literal object
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #DEPLOYMENT_DIAGRAM
     * @model name="DeploymentDiagram"
     * @generated
     * @ordered
     */
    public static final int DEPLOYMENT_DIAGRAM_VALUE = 6;

    /**
     * The '<em><b>Composite Structure Diagram</b></em>' literal value. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Composite Structure Diagram</b></em>' literal
     * object isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #COMPOSITE_STRUCTURE_DIAGRAM
     * @model name="CompositeStructureDiagram"
     * @generated
     * @ordered
     */
    public static final int COMPOSITE_STRUCTURE_DIAGRAM_VALUE = 7;

    /**
     * The '<em><b>Communication Diagram</b></em>' literal value. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Communication Diagram</b></em>' literal object
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #COMMUNICATION_DIAGRAM
     * @model name="CommunicationDiagram"
     * @generated
     * @ordered
     */
    public static final int COMMUNICATION_DIAGRAM_VALUE = 8;

    /**
     * The '<em><b>Free Form Diagram</b></em>' literal value. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Free Form Diagram</b></em>' literal object
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #FREE_FORM_DIAGRAM
     * @model name="FreeFormDiagram"
     * @generated
     * @ordered
     */
    public static final int FREE_FORM_DIAGRAM_VALUE = 9;

    /**
     * An array of all the '<em><b>Diagram Type</b></em>' enumerators. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private static final DiagramType[] VALUES_ARRAY = new DiagramType[] {
            USE_CASE_DIAGRAM,
            CLASS_DIAGRAM,
            SEQUENCE_DIAGRAM,
            COMPONENT_DIAGRAM,
            ACTIVITY_DIAGRAM,
            STATE_MACHINE_DIAGRAM,
            DEPLOYMENT_DIAGRAM,
            COMPOSITE_STRUCTURE_DIAGRAM,
            COMMUNICATION_DIAGRAM,
            FREE_FORM_DIAGRAM,
        };

    /**
     * A public read-only list of all the '<em><b>Diagram Type</b></em>' enumerators.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static final List<DiagramType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Diagram Type</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static DiagramType get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            DiagramType result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Diagram Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static DiagramType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            DiagramType result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Diagram Type</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static DiagramType get(int value) {
        switch (value) {
            case USE_CASE_DIAGRAM_VALUE: return USE_CASE_DIAGRAM;
            case CLASS_DIAGRAM_VALUE: return CLASS_DIAGRAM;
            case SEQUENCE_DIAGRAM_VALUE: return SEQUENCE_DIAGRAM;
            case COMPONENT_DIAGRAM_VALUE: return COMPONENT_DIAGRAM;
            case ACTIVITY_DIAGRAM_VALUE: return ACTIVITY_DIAGRAM;
            case STATE_MACHINE_DIAGRAM_VALUE: return STATE_MACHINE_DIAGRAM;
            case DEPLOYMENT_DIAGRAM_VALUE: return DEPLOYMENT_DIAGRAM;
            case COMPOSITE_STRUCTURE_DIAGRAM_VALUE: return COMPOSITE_STRUCTURE_DIAGRAM;
            case COMMUNICATION_DIAGRAM_VALUE: return COMMUNICATION_DIAGRAM;
            case FREE_FORM_DIAGRAM_VALUE: return FREE_FORM_DIAGRAM;
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
    private DiagramType(int value, String name, String literal) {
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

} // DiagramType
