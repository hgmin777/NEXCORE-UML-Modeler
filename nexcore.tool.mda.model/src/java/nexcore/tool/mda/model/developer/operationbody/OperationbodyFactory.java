/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.mda.model.developer.operationbody;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see nexcore.tool.mda.model.developer.operationbody.OperationbodyPackage
 * @generated
 */
public interface OperationbodyFactory extends EFactory {
	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String copyright = "";

	/**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	OperationbodyFactory eINSTANCE = nexcore.tool.mda.model.developer.operationbody.impl.OperationbodyFactoryImpl.init();

	/**
     * Returns a new object of class '<em>MDA Operation Body</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>MDA Operation Body</em>'.
     * @generated
     */
	MDAOperationBody createMDAOperationBody();

	/**
     * Returns a new object of class '<em>MDA Combined Fragment</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>MDA Combined Fragment</em>'.
     * @generated
     */
	MDACombinedFragment createMDACombinedFragment();

	/**
     * Returns a new object of class '<em>MDA Operand</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>MDA Operand</em>'.
     * @generated
     */
	MDAOperand createMDAOperand();

	/**
     * Returns a new object of class '<em>MDA Message</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>MDA Message</em>'.
     * @generated
     */
	MDAMessage createMDAMessage();

	/**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
	OperationbodyPackage getOperationbodyPackage();

} //OperationbodyFactory
