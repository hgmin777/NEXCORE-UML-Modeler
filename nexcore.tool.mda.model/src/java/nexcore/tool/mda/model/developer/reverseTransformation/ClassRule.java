/**
 * Copyright (c) 2012 SK C&C Co., Ltd. All rights reserved. 
 * 
 * This software is the confidential and proprietary information of SK C&C. 
 * You shall not disclose such confidential information and shall use it 
 * only in accordance with the terms of the license agreement you entered into 
 * with SK C&C.
 */
package nexcore.tool.mda.model.developer.reverseTransformation;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Class Rule</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.ClassRule#getPackageName <em>Package Name</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.ClassRule#isGenerateGetterSetter <em>Generate Getter Setter</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.ClassRule#isGenerateConstructor <em>Generate Constructor</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.ClassRule#isGenerateDestructor <em>Generate Destructor</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.ClassRule#getOperationRules <em>Operation Rules</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.ClassRule#getAttributeRules <em>Attribute Rules</em>}</li>
 * </ul>
 * </p>
 *
 * @see nexcore.tool.mda.model.developer.reverseTransformation.ReverseTransformationPackage#getClassRule()
 * @model
 * @generated
 */
public interface ClassRule extends ElementRule {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2012 SK C&C Co., Ltd. All rights reserved. \r\n\r\nThis software is the confidential and proprietary information of SK C&C. \r\nYou shall not disclose such confidential information and shall use it \r\nonly in accordance with the terms of the license agreement you entered into \r\nwith SK C&C.";

    /**
     * Returns the value of the '<em><b>Package Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Package Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Package Name</em>' attribute.
     * @see #setPackageName(String)
     * @see nexcore.tool.mda.model.developer.reverseTransformation.ReverseTransformationPackage#getClassRule_PackageName()
     * @model
     * @generated
     */
    String getPackageName();

    /**
     * Sets the value of the '{@link nexcore.tool.mda.model.developer.reverseTransformation.ClassRule#getPackageName <em>Package Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Package Name</em>' attribute.
     * @see #getPackageName()
     * @generated
     */
    void setPackageName(String value);

    /**
     * Returns the value of the '<em><b>Generate Getter Setter</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Generate Getter Setter</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Generate Getter Setter</em>' attribute.
     * @see #setGenerateGetterSetter(boolean)
     * @see nexcore.tool.mda.model.developer.reverseTransformation.ReverseTransformationPackage#getClassRule_GenerateGetterSetter()
     * @model required="true"
     * @generated
     */
    boolean isGenerateGetterSetter();

    /**
     * Sets the value of the '{@link nexcore.tool.mda.model.developer.reverseTransformation.ClassRule#isGenerateGetterSetter <em>Generate Getter Setter</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Generate Getter Setter</em>' attribute.
     * @see #isGenerateGetterSetter()
     * @generated
     */
    void setGenerateGetterSetter(boolean value);

    /**
     * Returns the value of the '<em><b>Generate Constructor</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Generate Constructor</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Generate Constructor</em>' attribute.
     * @see #setGenerateConstructor(boolean)
     * @see nexcore.tool.mda.model.developer.reverseTransformation.ReverseTransformationPackage#getClassRule_GenerateConstructor()
     * @model required="true"
     * @generated
     */
    boolean isGenerateConstructor();

    /**
     * Sets the value of the '{@link nexcore.tool.mda.model.developer.reverseTransformation.ClassRule#isGenerateConstructor <em>Generate Constructor</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Generate Constructor</em>' attribute.
     * @see #isGenerateConstructor()
     * @generated
     */
    void setGenerateConstructor(boolean value);

    /**
     * Returns the value of the '<em><b>Generate Destructor</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Generate Destructor</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Generate Destructor</em>' attribute.
     * @see #setGenerateDestructor(boolean)
     * @see nexcore.tool.mda.model.developer.reverseTransformation.ReverseTransformationPackage#getClassRule_GenerateDestructor()
     * @model required="true"
     * @generated
     */
    boolean isGenerateDestructor();

    /**
     * Sets the value of the '{@link nexcore.tool.mda.model.developer.reverseTransformation.ClassRule#isGenerateDestructor <em>Generate Destructor</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Generate Destructor</em>' attribute.
     * @see #isGenerateDestructor()
     * @generated
     */
    void setGenerateDestructor(boolean value);

    /**
     * Returns the value of the '<em><b>Operation Rules</b></em>' containment reference list.
     * The list contents are of type {@link nexcore.tool.mda.model.developer.reverseTransformation.ElementRule}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Operation Rules</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Operation Rules</em>' containment reference list.
     * @see nexcore.tool.mda.model.developer.reverseTransformation.ReverseTransformationPackage#getClassRule_OperationRules()
     * @model containment="true"
     * @generated
     */
    EList<ElementRule> getOperationRules();

    /**
     * Returns the value of the '<em><b>Attribute Rules</b></em>' containment reference list.
     * The list contents are of type {@link nexcore.tool.mda.model.developer.reverseTransformation.ElementRule}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Attribute Rules</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Attribute Rules</em>' containment reference list.
     * @see nexcore.tool.mda.model.developer.reverseTransformation.ReverseTransformationPackage#getClassRule_AttributeRules()
     * @model containment="true"
     * @generated
     */
    EList<ElementRule> getAttributeRules();

} // ClassRule
