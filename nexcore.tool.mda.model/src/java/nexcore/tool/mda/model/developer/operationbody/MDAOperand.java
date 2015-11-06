/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.mda.model.developer.operationbody;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>MDA Operand</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link nexcore.tool.mda.model.developer.operationbody.MDAOperand#getGuard <em>Guard</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.operationbody.MDAOperand#getMessages <em>Messages</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.operationbody.MDAOperand#getFragments <em>Fragments</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.operationbody.MDAOperand#getOrder <em>Order</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.operationbody.MDAOperand#getAllMessageCnt <em>All Message Cnt</em>}</li>
 * </ul>
 * </p>
 *
 * @see nexcore.tool.mda.model.developer.operationbody.OperationbodyPackage#getMDAOperand()
 * @model
 * @generated
 */
public interface MDAOperand extends IMDAOperation {
	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String copyright = "";

	/**
     * Returns the value of the '<em><b>Guard</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Guard</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Guard</em>' attribute.
     * @see #setGuard(String)
     * @see nexcore.tool.mda.model.developer.operationbody.OperationbodyPackage#getMDAOperand_Guard()
     * @model required="true"
     * @generated
     */
	String getGuard();

	/**
     * Sets the value of the '{@link nexcore.tool.mda.model.developer.operationbody.MDAOperand#getGuard <em>Guard</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Guard</em>' attribute.
     * @see #getGuard()
     * @generated
     */
	void setGuard(String value);

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
     * @see nexcore.tool.mda.model.developer.operationbody.OperationbodyPackage#getMDAOperand_Messages()
     * @model containment="true"
     * @generated
     */
	EList<MDAMessage> getMessages();

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
     * @see nexcore.tool.mda.model.developer.operationbody.OperationbodyPackage#getMDAOperand_Fragments()
     * @model containment="true"
     * @generated
     */
	EList<MDACombinedFragment> getFragments();

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
     * @see nexcore.tool.mda.model.developer.operationbody.OperationbodyPackage#getMDAOperand_Order()
     * @model
     * @generated
     */
    EList<String> getOrder();

    /**
     * Returns the value of the '<em><b>All Message Cnt</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>All Message Cnt</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>All Message Cnt</em>' attribute.
     * @see #setAllMessageCnt(int)
     * @see nexcore.tool.mda.model.developer.operationbody.OperationbodyPackage#getMDAOperand_AllMessageCnt()
     * @model required="true"
     * @generated
     */
    int getAllMessageCnt();

    /**
     * Sets the value of the '{@link nexcore.tool.mda.model.developer.operationbody.MDAOperand#getAllMessageCnt <em>All Message Cnt</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>All Message Cnt</em>' attribute.
     * @see #getAllMessageCnt()
     * @generated
     */
    void setAllMessageCnt(int value);

} // MDAOperand
