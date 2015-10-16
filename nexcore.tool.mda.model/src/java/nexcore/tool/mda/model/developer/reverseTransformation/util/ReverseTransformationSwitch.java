/**
 * Copyright (c) 2012 SK C&C Co., Ltd. All rights reserved. 
 * 
 * This software is the confidential and proprietary information of SK C&C. 
 * You shall not disclose such confidential information and shall use it 
 * only in accordance with the terms of the license agreement you entered into 
 * with SK C&C.
 */
package nexcore.tool.mda.model.developer.reverseTransformation.util;

import java.util.List;

import nexcore.tool.mda.model.developer.reverseTransformation.*;

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
 * @see nexcore.tool.mda.model.developer.reverseTransformation.ReverseTransformationPackage
 * @generated
 */
public class ReverseTransformationSwitch<T> {
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
    protected static ReverseTransformationPackage modelPackage;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ReverseTransformationSwitch() {
        if (modelPackage == null) {
            modelPackage = ReverseTransformationPackage.eINSTANCE;
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
            case ReverseTransformationPackage.MDA_REVERSE_RULE_SET: {
                MDAReverseRuleSet mdaReverseRuleSet = (MDAReverseRuleSet)theEObject;
                T result = caseMDAReverseRuleSet(mdaReverseRuleSet);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ReverseTransformationPackage.CLASS_RULE: {
                ClassRule classRule = (ClassRule)theEObject;
                T result = caseClassRule(classRule);
                if (result == null) result = caseElementRule(classRule);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ReverseTransformationPackage.DIAGRAM_RULE: {
                DiagramRule diagramRule = (DiagramRule)theEObject;
                T result = caseDiagramRule(diagramRule);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ReverseTransformationPackage.CLASS_DIAGRAM_RULE: {
                ClassDiagramRule classDiagramRule = (ClassDiagramRule)theEObject;
                T result = caseClassDiagramRule(classDiagramRule);
                if (result == null) result = caseDiagramRule(classDiagramRule);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ReverseTransformationPackage.ELEMENT_RULE: {
                ElementRule elementRule = (ElementRule)theEObject;
                T result = caseElementRule(elementRule);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ReverseTransformationPackage.SEQUENCE_DIAGRAM_RULE: {
                SequenceDiagramRule sequenceDiagramRule = (SequenceDiagramRule)theEObject;
                T result = caseSequenceDiagramRule(sequenceDiagramRule);
                if (result == null) result = caseDiagramRule(sequenceDiagramRule);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>MDA Reverse Rule Set</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>MDA Reverse Rule Set</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMDAReverseRuleSet(MDAReverseRuleSet object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Class Rule</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Class Rule</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseClassRule(ClassRule object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Diagram Rule</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Diagram Rule</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDiagramRule(DiagramRule object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Class Diagram Rule</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Class Diagram Rule</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseClassDiagramRule(ClassDiagramRule object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Element Rule</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Element Rule</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseElementRule(ElementRule object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Sequence Diagram Rule</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sequence Diagram Rule</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSequenceDiagramRule(SequenceDiagramRule object) {
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

} //ReverseTransformationSwitch
