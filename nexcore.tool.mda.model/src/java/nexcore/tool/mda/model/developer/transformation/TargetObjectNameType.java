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
 * <em><b>Target Object Name Type</b></em>', and utility methods for working
 * with them. <!-- end-user-doc -->
 * 
 * @see nexcore.tool.mda.model.developer.transformation.TransformationPackage#getTargetObjectNameType()
 * @model
 * @generated
 */
public enum TargetObjectNameType implements Enumerator {
    /**
     * The '<em><b>Class Name</b></em>' literal object. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #CLASS_NAME_VALUE
     * @generated
     * @ordered
     */
    CLASS_NAME(0, "ClassName", ""),

    /**
     * The '<em><b>Lifeline Name</b></em>' literal object. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #LIFELINE_NAME_VALUE
     * @generated
     * @ordered
     */
    LIFELINE_NAME(1, "LifelineName", "LifelineName"),

    /**
     * The '<em><b>Glossary</b></em>' literal object. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #GLOSSARY_VALUE
     * @generated
     * @ordered
     */
    GLOSSARY(2, "Glossary", "Glossary");

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = "";

    /**
     * The '<em><b>Class Name</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Class Name</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #CLASS_NAME
     * @model name="ClassName" literal=""
     * @generated
     * @ordered
     */
    public static final int CLASS_NAME_VALUE = 0;

    /**
     * The '<em><b>Lifeline Name</b></em>' literal value. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of '<em><b>Lifeline Name</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #LIFELINE_NAME
     * @model name="LifelineName"
     * @generated
     * @ordered
     */
    public static final int LIFELINE_NAME_VALUE = 1;

    /**
     * The '<em><b>Glossary</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Glossary</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #GLOSSARY
     * @model name="Glossary"
     * @generated
     * @ordered
     */
    public static final int GLOSSARY_VALUE = 2;

    /**
     * An array of all the '<em><b>Target Object Name Type</b></em>'
     * enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private static final TargetObjectNameType[] VALUES_ARRAY = new TargetObjectNameType[] { CLASS_NAME, LIFELINE_NAME,
        GLOSSARY, };

    /**
     * A public read-only list of all the '
     * <em><b>Target Object Name Type</b></em>' enumerators. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final List<TargetObjectNameType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Target Object Name Type</b></em>' literal with the
     * specified literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static TargetObjectNameType get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            TargetObjectNameType result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Target Object Name Type</b></em>' literal with the
     * specified name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static TargetObjectNameType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            TargetObjectNameType result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Target Object Name Type</b></em>' literal with the
     * specified integer value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static TargetObjectNameType get(int value) {
        switch (value) {
            case CLASS_NAME_VALUE:
                return CLASS_NAME;
            case LIFELINE_NAME_VALUE:
                return LIFELINE_NAME;
            case GLOSSARY_VALUE:
                return GLOSSARY;
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
    private TargetObjectNameType(int value, String name, String literal) {
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

} // TargetObjectNameType
