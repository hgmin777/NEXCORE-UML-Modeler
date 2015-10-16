/**
 * Copyright (c) 2012 SK C&C Co., Ltd. All rights reserved. 
 * 
 * This software is the confidential and proprietary information of SK C&C. 
 * You shall not disclose such confidential information and shall use it 
 * only in accordance with the terms of the license agreement you entered into 
 * with SK C&C.
 */
package nexcore.tool.mda.model.developer.operationbody.util;

import nexcore.tool.mda.model.developer.operationbody.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see nexcore.tool.mda.model.developer.operationbody.OperationbodyPackage
 * @generated
 */
public class OperationbodyAdapterFactory extends AdapterFactoryImpl {
	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static final String copyright = "Copyright (c) 2012 SK C&C Co., Ltd. All rights reserved. \r\n\r\nThis software is the confidential and proprietary information of SK C&C. \r\nYou shall not disclose such confidential information and shall use it \r\nonly in accordance with the terms of the license agreement you entered into \r\nwith SK C&C.";

	/**
     * The cached model package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected static OperationbodyPackage modelPackage;

	/**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public OperationbodyAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = OperationbodyPackage.eINSTANCE;
        }
    }

	/**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
	@Override
	public boolean isFactoryForType(Object object) {
        if (object == modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject)object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

	/**
     * The switch that delegates to the <code>createXXX</code> methods.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected OperationbodySwitch<Adapter> modelSwitch =
		new OperationbodySwitch<Adapter>() {
            @Override
            public Adapter caseMDAOperationBody(MDAOperationBody object) {
                return createMDAOperationBodyAdapter();
            }
            @Override
            public Adapter caseMDACombinedFragment(MDACombinedFragment object) {
                return createMDACombinedFragmentAdapter();
            }
            @Override
            public Adapter caseMDAOperand(MDAOperand object) {
                return createMDAOperandAdapter();
            }
            @Override
            public Adapter caseMDAMessage(MDAMessage object) {
                return createMDAMessageAdapter();
            }
            @Override
            public Adapter caseIMDAOperation(IMDAOperation object) {
                return createIMDAOperationAdapter();
            }
            @Override
            public Adapter defaultCase(EObject object) {
                return createEObjectAdapter();
            }
        };

	/**
     * Creates an adapter for the <code>target</code>.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param target the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
	@Override
	public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject)target);
    }


	/**
     * Creates a new adapter for an object of class '{@link nexcore.tool.mda.model.developer.operationbody.MDAOperationBody <em>MDA Operation Body</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see nexcore.tool.mda.model.developer.operationbody.MDAOperationBody
     * @generated
     */
	public Adapter createMDAOperationBodyAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link nexcore.tool.mda.model.developer.operationbody.MDACombinedFragment <em>MDA Combined Fragment</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see nexcore.tool.mda.model.developer.operationbody.MDACombinedFragment
     * @generated
     */
	public Adapter createMDACombinedFragmentAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link nexcore.tool.mda.model.developer.operationbody.MDAOperand <em>MDA Operand</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see nexcore.tool.mda.model.developer.operationbody.MDAOperand
     * @generated
     */
	public Adapter createMDAOperandAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link nexcore.tool.mda.model.developer.operationbody.MDAMessage <em>MDA Message</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see nexcore.tool.mda.model.developer.operationbody.MDAMessage
     * @generated
     */
	public Adapter createMDAMessageAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link nexcore.tool.mda.model.developer.operationbody.IMDAOperation <em>IMDA Operation</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see nexcore.tool.mda.model.developer.operationbody.IMDAOperation
     * @generated
     */
	public Adapter createIMDAOperationAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for the default case.
     * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     */
	public Adapter createEObjectAdapter() {
        return null;
    }

} //OperationbodyAdapterFactory
