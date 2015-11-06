/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.mda.model.developer.operationbody.impl;

import nexcore.tool.mda.model.developer.operationbody.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class OperationbodyFactoryImpl extends EFactoryImpl implements OperationbodyFactory {
	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static final String copyright = "";

	/**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static OperationbodyFactory init() {
        try {
            OperationbodyFactory theOperationbodyFactory = (OperationbodyFactory)EPackage.Registry.INSTANCE.getEFactory("http://nexcore.skcc.com/tools/mdac/operationbody"); 
            if (theOperationbodyFactory != null) {
                return theOperationbodyFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new OperationbodyFactoryImpl();
    }

	/**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public OperationbodyFactoryImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case OperationbodyPackage.MDA_OPERATION_BODY: return createMDAOperationBody();
            case OperationbodyPackage.MDA_COMBINED_FRAGMENT: return createMDACombinedFragment();
            case OperationbodyPackage.MDA_OPERAND: return createMDAOperand();
            case OperationbodyPackage.MDA_MESSAGE: return createMDAMessage();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public MDAOperationBody createMDAOperationBody() {
        MDAOperationBodyImpl mdaOperationBody = new MDAOperationBodyImpl();
        return mdaOperationBody;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public MDACombinedFragment createMDACombinedFragment() {
        MDACombinedFragmentImpl mdaCombinedFragment = new MDACombinedFragmentImpl();
        return mdaCombinedFragment;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public MDAOperand createMDAOperand() {
        MDAOperandImpl mdaOperand = new MDAOperandImpl();
        return mdaOperand;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public MDAMessage createMDAMessage() {
        MDAMessageImpl mdaMessage = new MDAMessageImpl();
        return mdaMessage;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public OperationbodyPackage getOperationbodyPackage() {
        return (OperationbodyPackage)getEPackage();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
	@Deprecated
	public static OperationbodyPackage getPackage() {
        return OperationbodyPackage.eINSTANCE;
    }

} //OperationbodyFactoryImpl
