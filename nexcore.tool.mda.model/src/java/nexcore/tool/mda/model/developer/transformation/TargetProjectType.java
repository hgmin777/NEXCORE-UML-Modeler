/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.mda.model.developer.transformation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '
 * <em><b>Target Project Type</b></em>', and utility methods for working with
 * them. <!-- end-user-doc -->
 * 
 * @see nexcore.tool.mda.model.developer.transformation.TransformationPackage#getTargetProjectType()
 * @model
 * @generated
 */
public enum TargetProjectType implements Enumerator {
    /**
     * The '<em><b>Java</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #JAVA_VALUE
     * @generated
     * @ordered
     */
    JAVA(0, "Java", "Java"),

    /**
     * The '<em><b>EGOV</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #EGOV_VALUE
     * @generated
     * @ordered
     */
    EGOV(1, "EGOV", "EGOV"), /**
     * The '<em><b>NEXCOREJ2EE</b></em>' literal
     * object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #NEXCOREJ2EE_VALUE
     * @generated
     * @ordered
     */
    NEXCOREJ2EE(2, "NEXCOREJ2EE", "NEXCOREJ2EE"), /**
     * The '
     * <em><b>NEXCOREC</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #NEXCOREC_VALUE
     * @generated
     * @ordered
     */
    NEXCOREC(3, "NEXCOREC", "NEXCOREC");

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = "";

    /**
     * The '<em><b>Java</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Java</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #JAVA
     * @model name="Java"
     * @generated
     * @ordered
     */
    public static final int JAVA_VALUE = 0;

    /**
     * The '<em><b>EGOV</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>EGOV</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #EGOV
     * @model
     * @generated
     * @ordered
     */
    public static final int EGOV_VALUE = 1;

    /**
     * The '<em><b>NEXCOREJ2EE</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>NEXCOREJ2EE</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #NEXCOREJ2EE
     * @model
     * @generated
     * @ordered
     */
    public static final int NEXCOREJ2EE_VALUE = 2;

    /**
     * The '<em><b>NEXCOREC</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>NEXCOREC</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #NEXCOREC
     * @model
     * @generated
     * @ordered
     */
    public static final int NEXCOREC_VALUE = 3;

    /**
     * An array of all the '<em><b>Target Project Type</b></em>' enumerators.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private static final TargetProjectType[] VALUES_ARRAY = new TargetProjectType[] { JAVA, EGOV, NEXCOREJ2EE,
        NEXCOREC, };

    /**
     * A public read-only list of all the '<em><b>Target Project Type</b></em>'
     * enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final List<TargetProjectType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Target Project Type</b></em>' literal with the
     * specified literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static TargetProjectType get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            TargetProjectType result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Target Project Type</b></em>' literal with the
     * specified name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static TargetProjectType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            TargetProjectType result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Target Project Type</b></em>' literal with the
     * specified integer value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static TargetProjectType get(int value) {
        switch (value) {
            case JAVA_VALUE:
                return JAVA;
            case EGOV_VALUE:
                return EGOV;
            case NEXCOREJ2EE_VALUE:
                return NEXCOREJ2EE;
            case NEXCOREC_VALUE:
                return NEXCOREC;
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
    private TargetProjectType(int value, String name, String literal) {
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

} // TargetProjectType
