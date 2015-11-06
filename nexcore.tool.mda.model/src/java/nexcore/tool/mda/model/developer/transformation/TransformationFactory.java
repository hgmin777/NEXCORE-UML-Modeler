/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.mda.model.developer.transformation;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a
 * create method for each non-abstract class of the model. <!-- end-user-doc -->
 * 
 * @see nexcore.tool.mda.model.developer.transformation.TransformationPackage
 * @generated
 */
public interface TransformationFactory extends EFactory {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "";

    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    TransformationFactory eINSTANCE = nexcore.tool.mda.model.developer.transformation.impl.TransformationFactoryImpl.init();

    /**
     * Returns a new object of class '<em>MDA Dev Rule Set</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>MDA Dev Rule Set</em>'.
     * @generated
     */
    MDADevRuleSet createMDADevRuleSet();

    /**
     * Returns a new object of class '<em>Class Rule</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Class Rule</em>'.
     * @generated
     */
    ClassRule createClassRule();

    /**
     * Returns a new object of class '<em>Operation Rule</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Operation Rule</em>'.
     * @generated
     */
    OperationRule createOperationRule();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the package supported by this factory.
     * @generated
     */
    TransformationPackage getTransformationPackage();

} // TransformationFactory
