/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.model.umldiagram;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '
 * <em><b>Label Type</b></em>', and utility methods for working with them. <!--
 * end-user-doc -->
 * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getLabelType()
 * @model
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.umldiagram</li>
 * <li>설  명 : LabelType</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public enum LabelType implements Enumerator {
    /**
     * The '<em><b>Label</b></em>' literal object.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #LABEL_VALUE
     * @generated
     * @ordered
     */
    LABEL(0, "Label", "Label"),

    /**
     * The '<em><b>Source Role</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #SOURCE_ROLE_VALUE
     * @generated
     * @ordered
     */
    SOURCE_ROLE(1, "SourceRole", "SourceRole"),

    /**
     * The '<em><b>Target Role</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #TARGET_ROLE_VALUE
     * @generated
     * @ordered
     */
    TARGET_ROLE(2, "TargetRole", "TargetRole"),

    /**
     * The '<em><b>Source Multiplex</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #SOURCE_MULTIPLEX_VALUE
     * @generated
     * @ordered
     */
    SOURCE_MULTIPLEX(3, "SourceMultiplex", "SourceMultiplex"),

    /**
     * The '<em><b>Target Multiplex</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #TARGET_MULTIPLEX_VALUE
     * @generated
     * @ordered
     */
    TARGET_MULTIPLEX(4, "TargetMultiplex", "TargetMultiplex"),

    /**
     * The '<em><b>Stereotype</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #STEREOTYPE_VALUE
     * @generated
     * @ordered
     */
    STEREOTYPE(5, "Stereotype", "Stereotype"),

    /**
     * The '<em><b>Compartment</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #COMPARTMENT_VALUE
     * @generated
     * @ordered
     */
    COMPARTMENT(6, "Compartment", "Compartment");

    /**
     * The '<em><b>Label</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Label</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #LABEL
     * @model name="Label"
     * @generated
     * @ordered
     */
    public static final int LABEL_VALUE = 0;

    /**
     * The '<em><b>Source Role</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Source Role</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #SOURCE_ROLE
     * @model name="SourceRole"
     * @generated
     * @ordered
     */
    public static final int SOURCE_ROLE_VALUE = 1;

    /**
     * The '<em><b>Target Role</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Target Role</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #TARGET_ROLE
     * @model name="TargetRole"
     * @generated
     * @ordered
     */
    public static final int TARGET_ROLE_VALUE = 2;

    /**
     * The '<em><b>Source Multiplex</b></em>' literal value.
     * <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of '<em><b>Source Multiplex</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #SOURCE_MULTIPLEX
     * @model name="SourceMultiplex"
     * @generated
     * @ordered
     */
    public static final int SOURCE_MULTIPLEX_VALUE = 3;

    /**
     * The '<em><b>Target Multiplex</b></em>' literal value.
     * <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of '<em><b>Target Multiplex</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #TARGET_MULTIPLEX
     * @model name="TargetMultiplex"
     * @generated
     * @ordered
     */
    public static final int TARGET_MULTIPLEX_VALUE = 4;

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
    public static final int STEREOTYPE_VALUE = 5;

    /**
     * The '<em><b>Compartment</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Compartment</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #COMPARTMENT
     * @model name="Compartment"
     * @generated
     * @ordered
     */
    public static final int COMPARTMENT_VALUE = 6;

    /**
     * An array of all the '<em><b>Label Type</b></em>' enumerators. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private static final LabelType[] VALUES_ARRAY = new LabelType[] {
            LABEL,
            SOURCE_ROLE,
            TARGET_ROLE,
            SOURCE_MULTIPLEX,
            TARGET_MULTIPLEX,
            STEREOTYPE,
            COMPARTMENT,
        };

    /**
     * A public read-only list of all the '<em><b>Label Type</b></em>' enumerators.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static final List<LabelType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Label Type</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static LabelType get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            LabelType result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Label Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static LabelType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            LabelType result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Label Type</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static LabelType get(int value) {
        switch (value) {
            case LABEL_VALUE: return LABEL;
            case SOURCE_ROLE_VALUE: return SOURCE_ROLE;
            case TARGET_ROLE_VALUE: return TARGET_ROLE;
            case SOURCE_MULTIPLEX_VALUE: return SOURCE_MULTIPLEX;
            case TARGET_MULTIPLEX_VALUE: return TARGET_MULTIPLEX;
            case STEREOTYPE_VALUE: return STEREOTYPE;
            case COMPARTMENT_VALUE: return COMPARTMENT;
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
    private LabelType(int value, String name, String literal) {
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

} // LabelType
