/**
 * Copyright (c) 2012 SK C&C Co., Ltd. All rights reserved. 
 * 
 * This software is the confidential and proprietary information of SK C&C. 
 * You shall not disclose such confidential information and shall use it 
 * only in accordance with the terms of the license agreement you entered into 
 * with SK C&C.
 */
package nexcore.tool.mda.model.developer.reverseTransformation;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see nexcore.tool.mda.model.developer.reverseTransformation.ReverseTransformationPackage
 * @generated
 */
public interface ReverseTransformationFactory extends EFactory {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2012 SK C&C Co., Ltd. All rights reserved. \r\n\r\nThis software is the confidential and proprietary information of SK C&C. \r\nYou shall not disclose such confidential information and shall use it \r\nonly in accordance with the terms of the license agreement you entered into \r\nwith SK C&C.";

    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    ReverseTransformationFactory eINSTANCE = nexcore.tool.mda.model.developer.reverseTransformation.impl.ReverseTransformationFactoryImpl.init();

    /**
     * Returns a new object of class '<em>MDA Reverse Rule Set</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>MDA Reverse Rule Set</em>'.
     * @generated
     */
    MDAReverseRuleSet createMDAReverseRuleSet();

    /**
     * Returns a new object of class '<em>Class Rule</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Class Rule</em>'.
     * @generated
     */
    ClassRule createClassRule();

    /**
     * Returns a new object of class '<em>Diagram Rule</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Diagram Rule</em>'.
     * @generated
     */
    DiagramRule createDiagramRule();

    /**
     * Returns a new object of class '<em>Class Diagram Rule</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Class Diagram Rule</em>'.
     * @generated
     */
    ClassDiagramRule createClassDiagramRule();

    /**
     * Returns a new object of class '<em>Element Rule</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Element Rule</em>'.
     * @generated
     */
    ElementRule createElementRule();

    /**
     * Returns a new object of class '<em>Sequence Diagram Rule</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Sequence Diagram Rule</em>'.
     * @generated
     */
    SequenceDiagramRule createSequenceDiagramRule();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    ReverseTransformationPackage getReverseTransformationPackage();

} //ReverseTransformationFactory
