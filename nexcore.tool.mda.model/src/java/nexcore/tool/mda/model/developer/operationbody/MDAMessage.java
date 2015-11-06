/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.mda.model.developer.operationbody;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>MDA Message</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link nexcore.tool.mda.model.developer.operationbody.MDAMessage#getMessageSort <em>Message Sort</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.operationbody.MDAMessage#getOperationQName <em>Operation QName</em>}</li>
 * </ul>
 * </p>
 *
 * @see nexcore.tool.mda.model.developer.operationbody.OperationbodyPackage#getMDAMessage()
 * @model
 * @generated
 */
public interface MDAMessage extends IMDAOperation {
	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String copyright = "";

	/**
     * Returns the value of the '<em><b>Message Sort</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Message Sort</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Message Sort</em>' attribute.
     * @see #setMessageSort(String)
     * @see nexcore.tool.mda.model.developer.operationbody.OperationbodyPackage#getMDAMessage_MessageSort()
     * @model required="true"
     * @generated
     */
	String getMessageSort();

	/**
     * Sets the value of the '{@link nexcore.tool.mda.model.developer.operationbody.MDAMessage#getMessageSort <em>Message Sort</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Message Sort</em>' attribute.
     * @see #getMessageSort()
     * @generated
     */
	void setMessageSort(String value);

	/**
     * Returns the value of the '<em><b>Operation QName</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operation QName</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Operation QName</em>' attribute.
     * @see #setOperationQName(String)
     * @see nexcore.tool.mda.model.developer.operationbody.OperationbodyPackage#getMDAMessage_OperationQName()
     * @model required="true"
     * @generated
     */
	String getOperationQName();

	/**
     * Sets the value of the '{@link nexcore.tool.mda.model.developer.operationbody.MDAMessage#getOperationQName <em>Operation QName</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Operation QName</em>' attribute.
     * @see #getOperationQName()
     * @generated
     */
	void setOperationQName(String value);

} // MDAMessage
