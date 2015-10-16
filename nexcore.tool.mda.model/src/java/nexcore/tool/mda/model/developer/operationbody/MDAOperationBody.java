/**
 * Copyright (c) 2012 SK C&C Co., Ltd. All rights reserved. 
 * 
 * This software is the confidential and proprietary information of SK C&C. 
 * You shall not disclose such confidential information and shall use it 
 * only in accordance with the terms of the license agreement you entered into 
 * with SK C&C.
 */
package nexcore.tool.mda.model.developer.operationbody;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>MDA Operation Body</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link nexcore.tool.mda.model.developer.operationbody.MDAOperationBody#getQualifiedName <em>Qualified Name</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.operationbody.MDAOperationBody#getFragments <em>Fragments</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.operationbody.MDAOperationBody#getMessages <em>Messages</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.operationbody.MDAOperationBody#getOrder <em>Order</em>}</li>
 * </ul>
 * </p>
 *
 * @see nexcore.tool.mda.model.developer.operationbody.OperationbodyPackage#getMDAOperationBody()
 * @model
 * @generated
 */
public interface MDAOperationBody extends IMDAOperation {
	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String copyright = "Copyright (c) 2012 SK C&C Co., Ltd. All rights reserved. \r\n\r\nThis software is the confidential and proprietary information of SK C&C. \r\nYou shall not disclose such confidential information and shall use it \r\nonly in accordance with the terms of the license agreement you entered into \r\nwith SK C&C.";

	/**
     * Returns the value of the '<em><b>Qualified Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Qualified Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Qualified Name</em>' attribute.
     * @see #setQualifiedName(String)
     * @see nexcore.tool.mda.model.developer.operationbody.OperationbodyPackage#getMDAOperationBody_QualifiedName()
     * @model required="true"
     * @generated
     */
	String getQualifiedName();

	/**
     * Sets the value of the '{@link nexcore.tool.mda.model.developer.operationbody.MDAOperationBody#getQualifiedName <em>Qualified Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Qualified Name</em>' attribute.
     * @see #getQualifiedName()
     * @generated
     */
	void setQualifiedName(String value);

	/**
     * Returns the value of the '<em><b>Fragments</b></em>' containment reference list.
     * The list contents are of type {@link nexcore.tool.mda.model.developer.operationbody.MDACombinedFragment}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fragments</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Fragments</em>' containment reference list.
     * @see nexcore.tool.mda.model.developer.operationbody.OperationbodyPackage#getMDAOperationBody_Fragments()
     * @model containment="true"
     * @generated
     */
	EList<MDACombinedFragment> getFragments();

	/**
     * Returns the value of the '<em><b>Messages</b></em>' containment reference list.
     * The list contents are of type {@link nexcore.tool.mda.model.developer.operationbody.MDAMessage}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Messages</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Messages</em>' containment reference list.
     * @see nexcore.tool.mda.model.developer.operationbody.OperationbodyPackage#getMDAOperationBody_Messages()
     * @model containment="true"
     * @generated
     */
	EList<MDAMessage> getMessages();

    /**
     * Returns the value of the '<em><b>Order</b></em>' attribute list.
     * The list contents are of type {@link java.lang.String}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Order</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Order</em>' attribute list.
     * @see nexcore.tool.mda.model.developer.operationbody.OperationbodyPackage#getMDAOperationBody_Order()
     * @model
     * @generated
     */
    EList<String> getOrder();

} // MDAOperationBody
