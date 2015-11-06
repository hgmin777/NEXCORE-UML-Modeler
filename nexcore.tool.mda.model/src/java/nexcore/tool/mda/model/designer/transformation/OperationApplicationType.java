/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.mda.model.designer.transformation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '
 * <em><b>Operation Application Type</b></em>', and utility methods for working
 * with them. <!-- end-user-doc -->
 * 
 * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getOperationApplicationType()
 * @model
 * @generated
 */
public enum OperationApplicationType implements Enumerator {
    /**
     * The '<em><b>None</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #NONE_VALUE
     * @generated
     * @ordered
     */
    NONE(0, "none", "none"),

    /**
     * The '<em><b>Same type</b></em>' literal object. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #SAME_TYPE_VALUE
     * @generated
     * @ordered
     */
    SAME_TYPE(1, "same_type", "same_type"),

    /**
     * The '<em><b>User defined</b></em>' literal object. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #USER_DEFINED_VALUE
     * @generated
     * @ordered
     */
    USER_DEFINED(2, "user_defined", "user_defined");

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = "";

    /**
     * The '<em><b>None</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>None</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #NONE
     * @model name="none"
     * @generated
     * @ordered
     */
    public static final int NONE_VALUE = 0;

    /**
     * The '<em><b>Same type</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Same type</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #SAME_TYPE
     * @model name="same_type"
     * @generated
     * @ordered
     */
    public static final int SAME_TYPE_VALUE = 1;

    /**
     * The '<em><b>User defined</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>User defined</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #USER_DEFINED
     * @model name="user_defined"
     * @generated
     * @ordered
     */
    public static final int USER_DEFINED_VALUE = 2;

    /**
     * An array of all the '<em><b>Operation Application Type</b></em>'
     * enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private static final OperationApplicationType[] VALUES_ARRAY = new OperationApplicationType[] { NONE, SAME_TYPE,
        USER_DEFINED, };

    /**
     * A public read-only list of all the '
     * <em><b>Operation Application Type</b></em>' enumerators. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final List<OperationApplicationType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Operation Application Type</b></em>' literal with the
     * specified literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static OperationApplicationType get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            OperationApplicationType result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Operation Application Type</b></em>' literal with the
     * specified name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static OperationApplicationType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            OperationApplicationType result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Operation Application Type</b></em>' literal with the
     * specified integer value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static OperationApplicationType get(int value) {
        switch (value) {
            case NONE_VALUE:
                return NONE;
            case SAME_TYPE_VALUE:
                return SAME_TYPE;
            case USER_DEFINED_VALUE:
                return USER_DEFINED;
        }
        return null;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private final int value;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private final String name;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private final String literal;

    /**
     * Only this class can construct instances. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    private OperationApplicationType(int value, String name, String literal) {
        this.value = value;
        this.name = name;
        this.literal = literal;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public int getValue() {
        return value;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getLiteral() {
        return literal;
    }

    /**
     * Returns the literal value of the enumerator, which is its string
     * representation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String toString() {
        return literal;
    }

} // OperationApplicationType
