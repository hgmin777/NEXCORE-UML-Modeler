/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.model.projectinformation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '
 * <em><b>Project Phase Type</b></em>', and utility methods for working with
 * them. <!-- end-user-doc -->
 * 
 * @see nexcore.tool.uml.model.projectinformation.ProjectInformationPackage#getProjectPhaseType()
 * @model
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.projectinformation</li>
 * <li>설  명 : ProjectPhaseType</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public enum ProjectPhaseType implements Enumerator {
    /**
     * The '<em><b>Analysis</b></em>' literal object. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #ANALYSIS_VALUE
     * @generated
     * @ordered
     */
    ANALYSIS(0, "Analysis", "Analysis"),

    /**
     * The '<em><b>Logical Design</b></em>' literal object. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #LOGICAL_DESIGN_VALUE
     * @generated
     * @ordered
     */
    LOGICAL_DESIGN(1, "LogicalDesign", "LogicalDesign"),

    /**
     * The '<em><b>Physical Design</b></em>' literal object. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #PHYSICAL_DESIGN_VALUE
     * @generated
     * @ordered
     */
    PHYSICAL_DESIGN(2, "PhysicalDesign", "PhysicalDesign"),

    /**
     * The '<em><b>Development</b></em>' literal object. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #DEVELOPMENT_VALUE
     * @generated
     * @ordered
     */
    DEVELOPMENT(3, "Development", "Development"),

    /**
     * The '<em><b>Unit Test</b></em>' literal object. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #UNIT_TEST_VALUE
     * @generated
     * @ordered
     */
    UNIT_TEST(4, "UnitTest", "UnitTest"),

    /**
     * The '<em><b>Integration Test</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #INTEGRATION_TEST_VALUE
     * @generated
     * @ordered
     */
    INTEGRATION_TEST(5, "IntegrationTest", "IntegrationTest");

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
    public static final int ANALYSIS_VALUE = 0;

    /**
     * The '<em><b>Logical Design</b></em>' literal value. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of '<em><b>Logical Design</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #LOGICAL_DESIGN
     * @model name="LogicalDesign"
     * @generated
     * @ordered
     */
    public static final int LOGICAL_DESIGN_VALUE = 1;

    /**
     * The '<em><b>Physical Design</b></em>' literal value. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of '<em><b>Physical Design</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #PHYSICAL_DESIGN
     * @model name="PhysicalDesign"
     * @generated
     * @ordered
     */
    public static final int PHYSICAL_DESIGN_VALUE = 2;

    /**
     * The '<em><b>Development</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Development</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #DEVELOPMENT
     * @model name="Development"
     * @generated
     * @ordered
     */
    public static final int DEVELOPMENT_VALUE = 3;

    /**
     * The '<em><b>Unit Test</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Unit Test</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #UNIT_TEST
     * @model name="UnitTest"
     * @generated
     * @ordered
     */
    public static final int UNIT_TEST_VALUE = 4;

    /**
     * The '<em><b>Integration Test</b></em>' literal value. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of '<em><b>Integration Test</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #INTEGRATION_TEST
     * @model name="IntegrationTest"
     * @generated
     * @ordered
     */
    public static final int INTEGRATION_TEST_VALUE = 5;

    /**
     * An array of all the '<em><b>Project Phase Type</b></em>' enumerators.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private static final ProjectPhaseType[] VALUES_ARRAY = new ProjectPhaseType[] { ANALYSIS, LOGICAL_DESIGN,
        PHYSICAL_DESIGN, DEVELOPMENT, UNIT_TEST, INTEGRATION_TEST, };

    /**
     * A public read-only list of all the '<em><b>Project Phase Type</b></em>'
     * enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final List<ProjectPhaseType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Project Phase Type</b></em>' literal with the
     * specified literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static ProjectPhaseType get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            ProjectPhaseType result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Project Phase Type</b></em>' literal with the
     * specified name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static ProjectPhaseType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            ProjectPhaseType result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Project Phase Type</b></em>' literal with the
     * specified integer value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static ProjectPhaseType get(int value) {
        switch (value) {
            case ANALYSIS_VALUE:
                return ANALYSIS;
            case LOGICAL_DESIGN_VALUE:
                return LOGICAL_DESIGN;
            case PHYSICAL_DESIGN_VALUE:
                return PHYSICAL_DESIGN;
            case DEVELOPMENT_VALUE:
                return DEVELOPMENT;
            case UNIT_TEST_VALUE:
                return UNIT_TEST;
            case INTEGRATION_TEST_VALUE:
                return INTEGRATION_TEST;
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
    private ProjectPhaseType(int value, String name, String literal) {
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

} // ProjectPhaseType
