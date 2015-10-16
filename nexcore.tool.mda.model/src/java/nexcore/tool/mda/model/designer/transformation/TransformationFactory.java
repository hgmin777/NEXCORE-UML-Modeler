/*
 * Copyright (c) 2010 SK C&C Co., Ltd. All rights reserved.
 * 
 * This software is the confidential and proprietary information of SK C&C. You
 * shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK
 * C&C.
 */

package nexcore.tool.mda.model.designer.transformation;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a
 * create method for each non-abstract class of the model. <!-- end-user-doc -->
 * 
 * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage
 * @generated
 */
public interface TransformationFactory extends EFactory {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "Copyright (c) 2010 SK C&C Co., Ltd. All rights reserved.\r\n\r\nThis software is the confidential and proprietary information of SK C&C.\r\nYou shall not disclose such confidential information and shall use it\r\nonly in accordance with the terms of the license agreement you entered into\r\nwith SK C&C.";

    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    TransformationFactory eINSTANCE = nexcore.tool.mda.model.designer.transformation.impl.TransformationFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Rule Set</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Rule Set</em>'.
     * @generated
     */
    RuleSet createRuleSet();

    /**
     * Returns a new object of class '
     * <em>Source Structure Transformation Data</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '
     *         <em>Source Structure Transformation Data</em>'.
     * @generated
     */
    SourceStructureTransformationData createSourceStructureTransformationData();

    /**
     * Returns a new object of class '<em>Source Type</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Source Type</em>'.
     * @generated
     */
    SourceType createSourceType();

    /**
     * Returns a new object of class '
     * <em>Target Structure Transformation Data</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '
     *         <em>Target Structure Transformation Data</em>'.
     * @generated
     */
    TargetStructureTransformationData createTargetStructureTransformationData();

    /**
     * Returns a new object of class '<em>Data Name Type</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Data Name Type</em>'.
     * @generated
     */
    DataNameType createDataNameType();

    /**
     * Returns a new object of class '<em>Location Type</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Location Type</em>'.
     * @generated
     */
    LocationType createLocationType();

    /**
     * Returns a new object of class '<em>Location Segment</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Location Segment</em>'.
     * @generated
     */
    LocationSegment createLocationSegment();

    /**
     * Returns a new object of class '<em>Name Type</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Name Type</em>'.
     * @generated
     */
    NameType createNameType();

    /**
     * Returns a new object of class '<em>Name Part Type</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Name Part Type</em>'.
     * @generated
     */
    NamePartType createNamePartType();

    /**
     * Returns a new object of class '<em>Behavior Transformation Data</em>'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Behavior Transformation Data</em>'.
     * @generated
     */
    BehaviorTransformationData createBehaviorTransformationData();

    /**
     * Returns a new object of class '<em>Source Relation Type</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Source Relation Type</em>'.
     * @generated
     */
    SourceRelationType createSourceRelationType();

    /**
     * Returns a new object of class '
     * <em>Behavior Transformation Detail Data</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '
     *         <em>Behavior Transformation Detail Data</em>'.
     * @generated
     */
    BehaviorTransformationDetailData createBehaviorTransformationDetailData();

    /**
     * Returns a new object of class '<em>Target Relation Type</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Target Relation Type</em>'.
     * @generated
     */
    TargetRelationType createTargetRelationType();

    /**
     * Returns a new object of class '<em>Operation Type</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Operation Type</em>'.
     * @generated
     */
    OperationType createOperationType();

    /**
     * Returns a new object of class '<em>Parent Type</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Parent Type</em>'.
     * @generated
     */
    ParentType createParentType();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the package supported by this factory.
     * @generated
     */
    TransformationPackage getTransformationPackage();

} // TransformationFactory
