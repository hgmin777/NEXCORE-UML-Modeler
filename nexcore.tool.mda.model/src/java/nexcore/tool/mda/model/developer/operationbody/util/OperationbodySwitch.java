/**
 * Copyright (c) 2012 SK C&C Co., Ltd. All rights reserved. 
 * 
 * This software is the confidential and proprietary information of SK C&C. 
 * You shall not disclose such confidential information and shall use it 
 * only in accordance with the terms of the license agreement you entered into 
 * with SK C&C.
 */
package nexcore.tool.mda.model.developer.operationbody.util;

import java.util.List;

import nexcore.tool.mda.model.developer.operationbody.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see nexcore.tool.mda.model.developer.operationbody.OperationbodyPackage
 * @generated
 */
public class OperationbodySwitch<T> {
	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static final String copyright = "Copyright (c) 2012 SK C&C Co., Ltd. All rights reserved. \r\n\r\nThis software is the confidential and proprietary information of SK C&C. \r\nYou shall not disclose such confidential information and shall use it \r\nonly in accordance with the terms of the license agreement you entered into \r\nwith SK C&C.";

	/**
     * The cached model package
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected static OperationbodyPackage modelPackage;

	/**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public OperationbodySwitch() {
        if (modelPackage == null) {
            modelPackage = OperationbodyPackage.eINSTANCE;
        }
    }

	/**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
	public T doSwitch(EObject theEObject) {
        return doSwitch(theEObject.eClass(), theEObject);
    }

	/**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
	protected T doSwitch(EClass theEClass, EObject theEObject) {
        if (theEClass.eContainer() == modelPackage) {
            return doSwitch(theEClass.getClassifierID(), theEObject);
        }
        else {
            List<EClass> eSuperTypes = theEClass.getESuperTypes();
            return
                eSuperTypes.isEmpty() ?
                    defaultCase(theEObject) :
                    doSwitch(eSuperTypes.get(0), theEObject);
        }
    }

	/**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
	protected T doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
            case OperationbodyPackage.MDA_OPERATION_BODY: {
                MDAOperationBody mdaOperationBody = (MDAOperationBody)theEObject;
                T result = caseMDAOperationBody(mdaOperationBody);
                if (result == null) result = caseIMDAOperation(mdaOperationBody);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OperationbodyPackage.MDA_COMBINED_FRAGMENT: {
                MDACombinedFragment mdaCombinedFragment = (MDACombinedFragment)theEObject;
                T result = caseMDACombinedFragment(mdaCombinedFragment);
                if (result == null) result = caseIMDAOperation(mdaCombinedFragment);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OperationbodyPackage.MDA_OPERAND: {
                MDAOperand mdaOperand = (MDAOperand)theEObject;
                T result = caseMDAOperand(mdaOperand);
                if (result == null) result = caseIMDAOperation(mdaOperand);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OperationbodyPackage.MDA_MESSAGE: {
                MDAMessage mdaMessage = (MDAMessage)theEObject;
                T result = caseMDAMessage(mdaMessage);
                if (result == null) result = caseIMDAOperation(mdaMessage);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OperationbodyPackage.IMDA_OPERATION: {
                IMDAOperation imdaOperation = (IMDAOperation)theEObject;
                T result = caseIMDAOperation(imdaOperation);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>MDA Operation Body</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>MDA Operation Body</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseMDAOperationBody(MDAOperationBody object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>MDA Combined Fragment</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>MDA Combined Fragment</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseMDACombinedFragment(MDACombinedFragment object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>MDA Operand</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>MDA Operand</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseMDAOperand(MDAOperand object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>MDA Message</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>MDA Message</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseMDAMessage(MDAMessage object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>IMDA Operation</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>IMDA Operation</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseIMDAOperation(IMDAOperation object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
	public T defaultCase(EObject object) {
        return null;
    }

} //OperationbodySwitch
