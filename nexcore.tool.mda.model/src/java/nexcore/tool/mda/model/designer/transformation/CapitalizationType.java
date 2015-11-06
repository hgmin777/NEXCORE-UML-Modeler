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
 * <em><b>Capitalization Type</b></em>', and utility methods for working with
 * them. <!-- end-user-doc -->
 * 
 * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getCapitalizationType()
 * @model
 * @generated
 */
public enum CapitalizationType implements Enumerator {
    /**
     * The '<em><b>None</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #NONE_VALUE
     * @generated
     * @ordered
     */
    NONE(0, "none", ""),

    /**
     * The '<em><b>First</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #FIRST_VALUE
     * @generated
     * @ordered
     */
    FIRST(1, "first", "first"),

    /**
     * The '<em><b>All</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #ALL_VALUE
     * @generated
     * @ordered
     */
    ALL(2, "all", "all");

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
     * @model name="none" literal=""
     * @generated
     * @ordered
     */
    public static final int NONE_VALUE = 0;

    /**
     * The '<em><b>First</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>First</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #FIRST
     * @model name="first"
     * @generated
     * @ordered
     */
    public static final int FIRST_VALUE = 1;

    /**
     * The '<em><b>All</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>All</b></em>' literal object isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #ALL
     * @model name="all"
     * @generated
     * @ordered
     */
    public static final int ALL_VALUE = 2;

    /**
     * An array of all the '<em><b>Capitalization Type</b></em>' enumerators.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private static final CapitalizationType[] VALUES_ARRAY = new CapitalizationType[] { NONE, FIRST, ALL, };

    /**
     * A public read-only list of all the '<em><b>Capitalization Type</b></em>'
     * enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final List<CapitalizationType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Capitalization Type</b></em>' literal with the
     * specified literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static CapitalizationType get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            CapitalizationType result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Capitalization Type</b></em>' literal with the
     * specified name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static CapitalizationType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            CapitalizationType result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Capitalization Type</b></em>' literal with the
     * specified integer value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static CapitalizationType get(int value) {
        switch (value) {
            case NONE_VALUE:
                return NONE;
            case FIRST_VALUE:
                return FIRST;
            case ALL_VALUE:
                return ALL;
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
    private CapitalizationType(int value, String name, String literal) {
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

} // CapitalizationType
