/**
 * Copyright (c) 2012 SK C&C Co., Ltd. All rights reserved. 
 * 
 * This software is the confidential and proprietary information of SK C&C. 
 * You shall not disclose such confidential information and shall use it 
 * only in accordance with the terms of the license agreement you entered into 
 * with SK C&C.
 */
package nexcore.tool.mda.model.developer.reverseTransformation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Source Project Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see nexcore.tool.mda.model.developer.reverseTransformation.ReverseTransformationPackage#getSourceProjectType()
 * @model
 * @generated
 */
public enum SourceProjectType implements Enumerator {
    /**
     * The '<em><b>Java</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #JAVA_VALUE
     * @generated
     * @ordered
     */
    JAVA(0, "Java", "Java"),

    /**
     * The '<em><b>EGOV</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #EGOV_VALUE
     * @generated
     * @ordered
     */
    EGOV(1, "EGOV", "EGOV"),

    /**
     * The '<em><b>NEXCOREJ2EE</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #NEXCOREJ2EE_VALUE
     * @generated
     * @ordered
     */
    NEXCOREJ2EE(2, "NEXCOREJ2EE", "NEXCOREJ2EE"),

    /**
     * The '<em><b>NEXCOREC</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #NEXCOREC_VALUE
     * @generated
     * @ordered
     */
    NEXCOREC(3, "NEXCOREC", "NEXCOREC");

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2012 SK C&C Co., Ltd. All rights reserved. \r\n\r\nThis software is the confidential and proprietary information of SK C&C. \r\nYou shall not disclose such confidential information and shall use it \r\nonly in accordance with the terms of the license agreement you entered into \r\nwith SK C&C.";

    /**
     * The '<em><b>Java</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Java</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #JAVA
     * @model name="Java"
     * @generated
     * @ordered
     */
    public static final int JAVA_VALUE = 0;

    /**
     * The '<em><b>EGOV</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>EGOV</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #EGOV
     * @model
     * @generated
     * @ordered
     */
    public static final int EGOV_VALUE = 1;

    /**
     * The '<em><b>NEXCOREJ2EE</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>NEXCOREJ2EE</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #NEXCOREJ2EE
     * @model
     * @generated
     * @ordered
     */
    public static final int NEXCOREJ2EE_VALUE = 2;

    /**
     * The '<em><b>NEXCOREC</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>NEXCOREC</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #NEXCOREC
     * @model
     * @generated
     * @ordered
     */
    public static final int NEXCOREC_VALUE = 3;

    /**
     * An array of all the '<em><b>Source Project Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final SourceProjectType[] VALUES_ARRAY =
        new SourceProjectType[] {
            JAVA,
            EGOV,
            NEXCOREJ2EE,
            NEXCOREC,
        };

    /**
     * A public read-only list of all the '<em><b>Source Project Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List<SourceProjectType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Source Project Type</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static SourceProjectType get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            SourceProjectType result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Source Project Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static SourceProjectType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            SourceProjectType result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Source Project Type</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static SourceProjectType get(int value) {
        switch (value) {
            case JAVA_VALUE: return JAVA;
            case EGOV_VALUE: return EGOV;
            case NEXCOREJ2EE_VALUE: return NEXCOREJ2EE;
            case NEXCOREC_VALUE: return NEXCOREC;
        }
        return null;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private final int value;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private final String name;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private final String literal;

    /**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private SourceProjectType(int value, String name, String literal) {
        this.value = value;
        this.name = name;
        this.literal = literal;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getValue() {
      return value;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getName() {
      return name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getLiteral() {
      return literal;
    }

    /**
     * Returns the literal value of the enumerator, which is its string representation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        return literal;
    }
    
} //SourceProjectType
