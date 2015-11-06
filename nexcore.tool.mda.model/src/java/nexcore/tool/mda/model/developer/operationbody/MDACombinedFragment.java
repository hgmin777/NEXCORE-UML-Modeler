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
 * A representation of the model object '<em><b>MDA Combined Fragment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link nexcore.tool.mda.model.developer.operationbody.MDACombinedFragment#getInteractionOperator <em>Interaction Operator</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.operationbody.MDACombinedFragment#getOperands <em>Operands</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.operationbody.MDACombinedFragment#getAllMessageCnt <em>All Message Cnt</em>}</li>
 * </ul>
 * </p>
 *
 * @see nexcore.tool.mda.model.developer.operationbody.OperationbodyPackage#getMDACombinedFragment()
 * @model
 * @generated
 */
public interface MDACombinedFragment extends IMDAOperation {
	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String copyright = "";

	/**
     * Returns the value of the '<em><b>Interaction Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Interaction Operator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Interaction Operator</em>' attribute.
     * @see #setInteractionOperator(String)
     * @see nexcore.tool.mda.model.developer.operationbody.OperationbodyPackage#getMDACombinedFragment_InteractionOperator()
     * @model required="true"
     * @generated
     */
	String getInteractionOperator();

	/**
     * Sets the value of the '{@link nexcore.tool.mda.model.developer.operationbody.MDACombinedFragment#getInteractionOperator <em>Interaction Operator</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Interaction Operator</em>' attribute.
     * @see #getInteractionOperator()
     * @generated
     */
	void setInteractionOperator(String value);

	/**
     * Returns the value of the '<em><b>Operands</b></em>' containment reference list.
     * The list contents are of type {@link nexcore.tool.mda.model.developer.operationbody.MDAOperand}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operands</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Operands</em>' containment reference list.
     * @see nexcore.tool.mda.model.developer.operationbody.OperationbodyPackage#getMDACombinedFragment_Operands()
     * @model containment="true" required="true"
     * @generated
     */
	EList<MDAOperand> getOperands();

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
     * @see nexcore.tool.mda.model.developer.operationbody.OperationbodyPackage#getMDACombinedFragment_AllMessageCnt()
     * @model required="true"
     * @generated
     */
    int getAllMessageCnt();

    /**
     * Sets the value of the '{@link nexcore.tool.mda.model.developer.operationbody.MDACombinedFragment#getAllMessageCnt <em>All Message Cnt</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>All Message Cnt</em>' attribute.
     * @see #getAllMessageCnt()
     * @generated
     */
    void setAllMessageCnt(int value);

} // MDACombinedFragment
