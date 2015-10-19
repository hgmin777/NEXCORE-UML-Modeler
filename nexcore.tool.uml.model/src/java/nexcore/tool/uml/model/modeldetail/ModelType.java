/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.model.modeldetail;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '
 * <em><b>Model Type</b></em>', and utility methods for working with them. <!--
 * end-user-doc -->
 * 
 * @see nexcore.tool.uml.model.modeldetail.ModelDetailPackage#getModelType()
 * @model
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.modeldetail</li>
 * <li>설  명 : ModelType</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public enum ModelType implements Enumerator {
    /**
     * The '<em><b>General</b></em>' literal object. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #GENERAL_VALUE
     * @generated
     * @ordered
     */
    GENERAL(0, "General", "General"),

    /**
     * The '<em><b>Use Case</b></em>' literal object. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #USE_CASE_VALUE
     * @generated
     * @ordered
     */
    USE_CASE(1, "UseCase", "UseCase"),

    /**
     * The '<em><b>Analysis</b></em>' literal object. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #ANALYSIS_VALUE
     * @generated
     * @ordered
     */
    ANALYSIS(2, "Analysis", "Analysis"),

    /**
     * The '<em><b>Design</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #DESIGN_VALUE
     * @generated
     * @ordered
     */
    DESIGN(3, "Design", "Design");

    /**
     * The '<em><b>General</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>General</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #GENERAL
     * @model name="General"
     * @generated
     * @ordered
     */
    public static final int GENERAL_VALUE = 0;

    /**
     * The '<em><b>Use Case</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Use Case</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #USE_CASE
     * @model name="UseCase"
     * @generated
     * @ordered
     */
    public static final int USE_CASE_VALUE = 1;

    /**
     * The '<em><b>Analysis</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Analysis</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #ANALYSIS
     * @model name="Analysis"
     * @generated
     * @ordered
     */
    public static final int ANALYSIS_VALUE = 2;

    /**
     * The '<em><b>Design</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Design</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #DESIGN
     * @model name="Design"
     * @generated
     * @ordered
     */
    public static final int DESIGN_VALUE = 3;

    /**
     * An array of all the '<em><b>Model Type</b></em>' enumerators. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private static final ModelType[] VALUES_ARRAY = new ModelType[] { GENERAL, USE_CASE, ANALYSIS, DESIGN, };

    /**
     * A public read-only list of all the '<em><b>Model Type</b></em>'
     * enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final List<ModelType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Model Type</b></em>' literal with the specified
     * literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static ModelType get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            ModelType result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Model Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static ModelType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            ModelType result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Model Type</b></em>' literal with the specified
     * integer value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static ModelType get(int value) {
        switch (value) {
            case GENERAL_VALUE:
                return GENERAL;
            case USE_CASE_VALUE:
                return USE_CASE;
            case ANALYSIS_VALUE:
                return ANALYSIS;
            case DESIGN_VALUE:
                return DESIGN;
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
    private ModelType(int value, String name, String literal) {
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

} // ModelType
