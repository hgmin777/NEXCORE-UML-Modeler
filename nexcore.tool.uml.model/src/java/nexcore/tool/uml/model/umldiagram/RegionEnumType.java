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
 * <em><b>Region Enum Type</b></em>', and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getRegionEnumType()
 * @model
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.umldiagram</li>
 * <li>설  명 : RegionEnumType</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public enum RegionEnumType implements Enumerator {
    /**
     * The '<em><b>Attribute</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #ATTRIBUTE_VALUE
     * @generated
     * @ordered
     */
    ATTRIBUTE(0, "Attribute", "Attribute"),

    /**
     * The '<em><b>Operation</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #OPERATION_VALUE
     * @generated
     * @ordered
     */
    OPERATION(1, "Operation", "Operation"),

    /**
     * The '<em><b>Required Interface</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #REQUIRED_INTERFACE_VALUE
     * @generated
     * @ordered
     */
    REQUIRED_INTERFACE(2, "RequiredInterface", "RequiredInterface"),

    /**
     * The '<em><b>Provided Interface</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #PROVIDED_INTERFACE_VALUE
     * @generated
     * @ordered
     */
    PROVIDED_INTERFACE(3, "ProvidedInterface", "ProvidedInterface");

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
    public static final int ATTRIBUTE_VALUE = 0;

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
    public static final int OPERATION_VALUE = 1;

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
    public static final int REQUIRED_INTERFACE_VALUE = 2;

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
    public static final int PROVIDED_INTERFACE_VALUE = 3;

    /**
     * An array of all the '<em><b>Region Enum Type</b></em>' enumerators. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private static final RegionEnumType[] VALUES_ARRAY = new RegionEnumType[] {
            ATTRIBUTE,
            OPERATION,
            REQUIRED_INTERFACE,
            PROVIDED_INTERFACE,
        };

    /**
     * A public read-only list of all the '<em><b>Region Enum Type</b></em>' enumerators.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static final List<RegionEnumType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Region Enum Type</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static RegionEnumType get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            RegionEnumType result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Region Enum Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static RegionEnumType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            RegionEnumType result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Region Enum Type</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static RegionEnumType get(int value) {
        switch (value) {
            case ATTRIBUTE_VALUE: return ATTRIBUTE;
            case OPERATION_VALUE: return OPERATION;
            case REQUIRED_INTERFACE_VALUE: return REQUIRED_INTERFACE;
            case PROVIDED_INTERFACE_VALUE: return PROVIDED_INTERFACE;
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
    private RegionEnumType(int value, String name, String literal) {
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

} // RegionEnumType
