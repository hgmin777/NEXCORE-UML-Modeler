/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.mda.model.developer.reverseTransformation;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Element Rule</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.ElementRule#getName <em>Name</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.ElementRule#getPrefix <em>Prefix</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.ElementRule#getPostfix <em>Postfix</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.ElementRule#isCreateYN <em>Create YN</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.ElementRule#getStereotype <em>Stereotype</em>}</li>
 * </ul>
 * </p>
 *
 * @see nexcore.tool.mda.model.developer.reverseTransformation.ReverseTransformationPackage#getElementRule()
 * @model
 * @generated
 */
public interface ElementRule extends EObject {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "";

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see nexcore.tool.mda.model.developer.reverseTransformation.ReverseTransformationPackage#getElementRule_Name()
     * @model required="true"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link nexcore.tool.mda.model.developer.reverseTransformation.ElementRule#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Prefix</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Prefix</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Prefix</em>' attribute.
     * @see #setPrefix(String)
     * @see nexcore.tool.mda.model.developer.reverseTransformation.ReverseTransformationPackage#getElementRule_Prefix()
     * @model
     * @generated
     */
    String getPrefix();

    /**
     * Sets the value of the '{@link nexcore.tool.mda.model.developer.reverseTransformation.ElementRule#getPrefix <em>Prefix</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Prefix</em>' attribute.
     * @see #getPrefix()
     * @generated
     */
    void setPrefix(String value);

    /**
     * Returns the value of the '<em><b>Postfix</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Postfix</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Postfix</em>' attribute.
     * @see #setPostfix(String)
     * @see nexcore.tool.mda.model.developer.reverseTransformation.ReverseTransformationPackage#getElementRule_Postfix()
     * @model
     * @generated
     */
    String getPostfix();

    /**
     * Sets the value of the '{@link nexcore.tool.mda.model.developer.reverseTransformation.ElementRule#getPostfix <em>Postfix</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Postfix</em>' attribute.
     * @see #getPostfix()
     * @generated
     */
    void setPostfix(String value);

    /**
     * Returns the value of the '<em><b>Create YN</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Create YN</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Create YN</em>' attribute.
     * @see #setCreateYN(boolean)
     * @see nexcore.tool.mda.model.developer.reverseTransformation.ReverseTransformationPackage#getElementRule_CreateYN()
     * @model default="true" required="true"
     * @generated
     */
    boolean isCreateYN();

    /**
     * Sets the value of the '{@link nexcore.tool.mda.model.developer.reverseTransformation.ElementRule#isCreateYN <em>Create YN</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Create YN</em>' attribute.
     * @see #isCreateYN()
     * @generated
     */
    void setCreateYN(boolean value);

    /**
     * Returns the value of the '<em><b>Stereotype</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Stereotype</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Stereotype</em>' attribute.
     * @see #setStereotype(String)
     * @see nexcore.tool.mda.model.developer.reverseTransformation.ReverseTransformationPackage#getElementRule_Stereotype()
     * @model
     * @generated
     */
    String getStereotype();

    /**
     * Sets the value of the '{@link nexcore.tool.mda.model.developer.reverseTransformation.ElementRule#getStereotype <em>Stereotype</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Stereotype</em>' attribute.
     * @see #getStereotype()
     * @generated
     */
    void setStereotype(String value);

} // ElementRule
