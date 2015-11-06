/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.mda.model.developer.operationbody;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IMDA Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link nexcore.tool.mda.model.developer.operationbody.IMDAOperation#getId <em>Id</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.operationbody.IMDAOperation#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see nexcore.tool.mda.model.developer.operationbody.OperationbodyPackage#getIMDAOperation()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface IMDAOperation extends EObject {
	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String copyright = "";

	/**
     * Returns the value of the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Id</em>' attribute.
     * @see #setId(String)
     * @see nexcore.tool.mda.model.developer.operationbody.OperationbodyPackage#getIMDAOperation_Id()
     * @model required="true"
     * @generated
     */
	String getId();

	/**
     * Sets the value of the '{@link nexcore.tool.mda.model.developer.operationbody.IMDAOperation#getId <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
	void setId(String value);

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
     * @see nexcore.tool.mda.model.developer.operationbody.OperationbodyPackage#getIMDAOperation_Name()
     * @model required="true"
     * @generated
     */
	String getName();

	/**
     * Sets the value of the '{@link nexcore.tool.mda.model.developer.operationbody.IMDAOperation#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
	void setName(String value);

} // IMDAOperation
