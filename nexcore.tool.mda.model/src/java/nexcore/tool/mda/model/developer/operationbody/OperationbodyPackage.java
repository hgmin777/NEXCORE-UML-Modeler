/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.mda.model.developer.operationbody;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see nexcore.tool.mda.model.developer.operationbody.OperationbodyFactory
 * @model kind="package"
 * @generated
 */
public interface OperationbodyPackage extends EPackage {
	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String copyright = "";

	/**
     * The package name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNAME = "operationbody";

	/**
     * The package namespace URI.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_URI = "http://nexcore.skcc.com/tools/mdac/operationbody";

	/**
     * The package namespace name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_PREFIX = "op";

	/**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	OperationbodyPackage eINSTANCE = nexcore.tool.mda.model.developer.operationbody.impl.OperationbodyPackageImpl.init();

	/**
     * The meta object id for the '{@link nexcore.tool.mda.model.developer.operationbody.IMDAOperation <em>IMDA Operation</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see nexcore.tool.mda.model.developer.operationbody.IMDAOperation
     * @see nexcore.tool.mda.model.developer.operationbody.impl.OperationbodyPackageImpl#getIMDAOperation()
     * @generated
     */
	int IMDA_OPERATION = 4;

	/**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMDA_OPERATION__ID = 0;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMDA_OPERATION__NAME = 1;

	/**
     * The number of structural features of the '<em>IMDA Operation</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMDA_OPERATION_FEATURE_COUNT = 2;

	/**
     * The meta object id for the '{@link nexcore.tool.mda.model.developer.operationbody.impl.MDAOperationBodyImpl <em>MDA Operation Body</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see nexcore.tool.mda.model.developer.operationbody.impl.MDAOperationBodyImpl
     * @see nexcore.tool.mda.model.developer.operationbody.impl.OperationbodyPackageImpl#getMDAOperationBody()
     * @generated
     */
	int MDA_OPERATION_BODY = 0;

	/**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MDA_OPERATION_BODY__ID = IMDA_OPERATION__ID;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MDA_OPERATION_BODY__NAME = IMDA_OPERATION__NAME;

	/**
     * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MDA_OPERATION_BODY__QUALIFIED_NAME = IMDA_OPERATION_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Fragments</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MDA_OPERATION_BODY__FRAGMENTS = IMDA_OPERATION_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Messages</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MDA_OPERATION_BODY__MESSAGES = IMDA_OPERATION_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Order</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MDA_OPERATION_BODY__ORDER = IMDA_OPERATION_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>MDA Operation Body</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MDA_OPERATION_BODY_FEATURE_COUNT = IMDA_OPERATION_FEATURE_COUNT + 4;

	/**
     * The meta object id for the '{@link nexcore.tool.mda.model.developer.operationbody.impl.MDACombinedFragmentImpl <em>MDA Combined Fragment</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see nexcore.tool.mda.model.developer.operationbody.impl.MDACombinedFragmentImpl
     * @see nexcore.tool.mda.model.developer.operationbody.impl.OperationbodyPackageImpl#getMDACombinedFragment()
     * @generated
     */
	int MDA_COMBINED_FRAGMENT = 1;

	/**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MDA_COMBINED_FRAGMENT__ID = IMDA_OPERATION__ID;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MDA_COMBINED_FRAGMENT__NAME = IMDA_OPERATION__NAME;

	/**
     * The feature id for the '<em><b>Interaction Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MDA_COMBINED_FRAGMENT__INTERACTION_OPERATOR = IMDA_OPERATION_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Operands</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MDA_COMBINED_FRAGMENT__OPERANDS = IMDA_OPERATION_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>All Message Cnt</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MDA_COMBINED_FRAGMENT__ALL_MESSAGE_CNT = IMDA_OPERATION_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>MDA Combined Fragment</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MDA_COMBINED_FRAGMENT_FEATURE_COUNT = IMDA_OPERATION_FEATURE_COUNT + 3;

	/**
     * The meta object id for the '{@link nexcore.tool.mda.model.developer.operationbody.impl.MDAOperandImpl <em>MDA Operand</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see nexcore.tool.mda.model.developer.operationbody.impl.MDAOperandImpl
     * @see nexcore.tool.mda.model.developer.operationbody.impl.OperationbodyPackageImpl#getMDAOperand()
     * @generated
     */
	int MDA_OPERAND = 2;

	/**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MDA_OPERAND__ID = IMDA_OPERATION__ID;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MDA_OPERAND__NAME = IMDA_OPERATION__NAME;

	/**
     * The feature id for the '<em><b>Guard</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MDA_OPERAND__GUARD = IMDA_OPERATION_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Messages</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MDA_OPERAND__MESSAGES = IMDA_OPERATION_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Fragments</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MDA_OPERAND__FRAGMENTS = IMDA_OPERATION_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Order</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MDA_OPERAND__ORDER = IMDA_OPERATION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>All Message Cnt</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MDA_OPERAND__ALL_MESSAGE_CNT = IMDA_OPERATION_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '<em>MDA Operand</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MDA_OPERAND_FEATURE_COUNT = IMDA_OPERATION_FEATURE_COUNT + 5;

	/**
     * The meta object id for the '{@link nexcore.tool.mda.model.developer.operationbody.impl.MDAMessageImpl <em>MDA Message</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see nexcore.tool.mda.model.developer.operationbody.impl.MDAMessageImpl
     * @see nexcore.tool.mda.model.developer.operationbody.impl.OperationbodyPackageImpl#getMDAMessage()
     * @generated
     */
	int MDA_MESSAGE = 3;

	/**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MDA_MESSAGE__ID = IMDA_OPERATION__ID;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MDA_MESSAGE__NAME = IMDA_OPERATION__NAME;

	/**
     * The feature id for the '<em><b>Message Sort</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MDA_MESSAGE__MESSAGE_SORT = IMDA_OPERATION_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Operation QName</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MDA_MESSAGE__OPERATION_QNAME = IMDA_OPERATION_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>MDA Message</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MDA_MESSAGE_FEATURE_COUNT = IMDA_OPERATION_FEATURE_COUNT + 2;


	/**
     * Returns the meta object for class '{@link nexcore.tool.mda.model.developer.operationbody.MDAOperationBody <em>MDA Operation Body</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>MDA Operation Body</em>'.
     * @see nexcore.tool.mda.model.developer.operationbody.MDAOperationBody
     * @generated
     */
	EClass getMDAOperationBody();

	/**
     * Returns the meta object for the attribute '{@link nexcore.tool.mda.model.developer.operationbody.MDAOperationBody#getQualifiedName <em>Qualified Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Qualified Name</em>'.
     * @see nexcore.tool.mda.model.developer.operationbody.MDAOperationBody#getQualifiedName()
     * @see #getMDAOperationBody()
     * @generated
     */
	EAttribute getMDAOperationBody_QualifiedName();

	/**
     * Returns the meta object for the containment reference list '{@link nexcore.tool.mda.model.developer.operationbody.MDAOperationBody#getFragments <em>Fragments</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Fragments</em>'.
     * @see nexcore.tool.mda.model.developer.operationbody.MDAOperationBody#getFragments()
     * @see #getMDAOperationBody()
     * @generated
     */
	EReference getMDAOperationBody_Fragments();

	/**
     * Returns the meta object for the containment reference list '{@link nexcore.tool.mda.model.developer.operationbody.MDAOperationBody#getMessages <em>Messages</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Messages</em>'.
     * @see nexcore.tool.mda.model.developer.operationbody.MDAOperationBody#getMessages()
     * @see #getMDAOperationBody()
     * @generated
     */
	EReference getMDAOperationBody_Messages();

	/**
     * Returns the meta object for the attribute list '{@link nexcore.tool.mda.model.developer.operationbody.MDAOperationBody#getOrder <em>Order</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Order</em>'.
     * @see nexcore.tool.mda.model.developer.operationbody.MDAOperationBody#getOrder()
     * @see #getMDAOperationBody()
     * @generated
     */
    EAttribute getMDAOperationBody_Order();

    /**
     * Returns the meta object for class '{@link nexcore.tool.mda.model.developer.operationbody.MDACombinedFragment <em>MDA Combined Fragment</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>MDA Combined Fragment</em>'.
     * @see nexcore.tool.mda.model.developer.operationbody.MDACombinedFragment
     * @generated
     */
	EClass getMDACombinedFragment();

	/**
     * Returns the meta object for the attribute '{@link nexcore.tool.mda.model.developer.operationbody.MDACombinedFragment#getInteractionOperator <em>Interaction Operator</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Interaction Operator</em>'.
     * @see nexcore.tool.mda.model.developer.operationbody.MDACombinedFragment#getInteractionOperator()
     * @see #getMDACombinedFragment()
     * @generated
     */
	EAttribute getMDACombinedFragment_InteractionOperator();

	/**
     * Returns the meta object for the containment reference list '{@link nexcore.tool.mda.model.developer.operationbody.MDACombinedFragment#getOperands <em>Operands</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Operands</em>'.
     * @see nexcore.tool.mda.model.developer.operationbody.MDACombinedFragment#getOperands()
     * @see #getMDACombinedFragment()
     * @generated
     */
	EReference getMDACombinedFragment_Operands();

	/**
     * Returns the meta object for the attribute '{@link nexcore.tool.mda.model.developer.operationbody.MDACombinedFragment#getAllMessageCnt <em>All Message Cnt</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>All Message Cnt</em>'.
     * @see nexcore.tool.mda.model.developer.operationbody.MDACombinedFragment#getAllMessageCnt()
     * @see #getMDACombinedFragment()
     * @generated
     */
    EAttribute getMDACombinedFragment_AllMessageCnt();

    /**
     * Returns the meta object for class '{@link nexcore.tool.mda.model.developer.operationbody.MDAOperand <em>MDA Operand</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>MDA Operand</em>'.
     * @see nexcore.tool.mda.model.developer.operationbody.MDAOperand
     * @generated
     */
	EClass getMDAOperand();

	/**
     * Returns the meta object for the attribute '{@link nexcore.tool.mda.model.developer.operationbody.MDAOperand#getGuard <em>Guard</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Guard</em>'.
     * @see nexcore.tool.mda.model.developer.operationbody.MDAOperand#getGuard()
     * @see #getMDAOperand()
     * @generated
     */
	EAttribute getMDAOperand_Guard();

	/**
     * Returns the meta object for the containment reference list '{@link nexcore.tool.mda.model.developer.operationbody.MDAOperand#getMessages <em>Messages</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Messages</em>'.
     * @see nexcore.tool.mda.model.developer.operationbody.MDAOperand#getMessages()
     * @see #getMDAOperand()
     * @generated
     */
	EReference getMDAOperand_Messages();

	/**
     * Returns the meta object for the containment reference list '{@link nexcore.tool.mda.model.developer.operationbody.MDAOperand#getFragments <em>Fragments</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Fragments</em>'.
     * @see nexcore.tool.mda.model.developer.operationbody.MDAOperand#getFragments()
     * @see #getMDAOperand()
     * @generated
     */
	EReference getMDAOperand_Fragments();

	/**
     * Returns the meta object for the attribute list '{@link nexcore.tool.mda.model.developer.operationbody.MDAOperand#getOrder <em>Order</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Order</em>'.
     * @see nexcore.tool.mda.model.developer.operationbody.MDAOperand#getOrder()
     * @see #getMDAOperand()
     * @generated
     */
    EAttribute getMDAOperand_Order();

    /**
     * Returns the meta object for the attribute '{@link nexcore.tool.mda.model.developer.operationbody.MDAOperand#getAllMessageCnt <em>All Message Cnt</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>All Message Cnt</em>'.
     * @see nexcore.tool.mda.model.developer.operationbody.MDAOperand#getAllMessageCnt()
     * @see #getMDAOperand()
     * @generated
     */
    EAttribute getMDAOperand_AllMessageCnt();

    /**
     * Returns the meta object for class '{@link nexcore.tool.mda.model.developer.operationbody.MDAMessage <em>MDA Message</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>MDA Message</em>'.
     * @see nexcore.tool.mda.model.developer.operationbody.MDAMessage
     * @generated
     */
	EClass getMDAMessage();

	/**
     * Returns the meta object for the attribute '{@link nexcore.tool.mda.model.developer.operationbody.MDAMessage#getMessageSort <em>Message Sort</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Message Sort</em>'.
     * @see nexcore.tool.mda.model.developer.operationbody.MDAMessage#getMessageSort()
     * @see #getMDAMessage()
     * @generated
     */
	EAttribute getMDAMessage_MessageSort();

	/**
     * Returns the meta object for the attribute '{@link nexcore.tool.mda.model.developer.operationbody.MDAMessage#getOperationQName <em>Operation QName</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Operation QName</em>'.
     * @see nexcore.tool.mda.model.developer.operationbody.MDAMessage#getOperationQName()
     * @see #getMDAMessage()
     * @generated
     */
	EAttribute getMDAMessage_OperationQName();

	/**
     * Returns the meta object for class '{@link nexcore.tool.mda.model.developer.operationbody.IMDAOperation <em>IMDA Operation</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>IMDA Operation</em>'.
     * @see nexcore.tool.mda.model.developer.operationbody.IMDAOperation
     * @generated
     */
	EClass getIMDAOperation();

	/**
     * Returns the meta object for the attribute '{@link nexcore.tool.mda.model.developer.operationbody.IMDAOperation#getId <em>Id</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see nexcore.tool.mda.model.developer.operationbody.IMDAOperation#getId()
     * @see #getIMDAOperation()
     * @generated
     */
	EAttribute getIMDAOperation_Id();

	/**
     * Returns the meta object for the attribute '{@link nexcore.tool.mda.model.developer.operationbody.IMDAOperation#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see nexcore.tool.mda.model.developer.operationbody.IMDAOperation#getName()
     * @see #getIMDAOperation()
     * @generated
     */
	EAttribute getIMDAOperation_Name();

	/**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
	OperationbodyFactory getOperationbodyFactory();

	/**
     * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
     * @generated
     */
	interface Literals {
		/**
         * The meta object literal for the '{@link nexcore.tool.mda.model.developer.operationbody.impl.MDAOperationBodyImpl <em>MDA Operation Body</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see nexcore.tool.mda.model.developer.operationbody.impl.MDAOperationBodyImpl
         * @see nexcore.tool.mda.model.developer.operationbody.impl.OperationbodyPackageImpl#getMDAOperationBody()
         * @generated
         */
		EClass MDA_OPERATION_BODY = eINSTANCE.getMDAOperationBody();

		/**
         * The meta object literal for the '<em><b>Qualified Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute MDA_OPERATION_BODY__QUALIFIED_NAME = eINSTANCE.getMDAOperationBody_QualifiedName();

		/**
         * The meta object literal for the '<em><b>Fragments</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference MDA_OPERATION_BODY__FRAGMENTS = eINSTANCE.getMDAOperationBody_Fragments();

		/**
         * The meta object literal for the '<em><b>Messages</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference MDA_OPERATION_BODY__MESSAGES = eINSTANCE.getMDAOperationBody_Messages();

		/**
         * The meta object literal for the '<em><b>Order</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute MDA_OPERATION_BODY__ORDER = eINSTANCE.getMDAOperationBody_Order();

        /**
         * The meta object literal for the '{@link nexcore.tool.mda.model.developer.operationbody.impl.MDACombinedFragmentImpl <em>MDA Combined Fragment</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see nexcore.tool.mda.model.developer.operationbody.impl.MDACombinedFragmentImpl
         * @see nexcore.tool.mda.model.developer.operationbody.impl.OperationbodyPackageImpl#getMDACombinedFragment()
         * @generated
         */
		EClass MDA_COMBINED_FRAGMENT = eINSTANCE.getMDACombinedFragment();

		/**
         * The meta object literal for the '<em><b>Interaction Operator</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute MDA_COMBINED_FRAGMENT__INTERACTION_OPERATOR = eINSTANCE.getMDACombinedFragment_InteractionOperator();

		/**
         * The meta object literal for the '<em><b>Operands</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference MDA_COMBINED_FRAGMENT__OPERANDS = eINSTANCE.getMDACombinedFragment_Operands();

		/**
         * The meta object literal for the '<em><b>All Message Cnt</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute MDA_COMBINED_FRAGMENT__ALL_MESSAGE_CNT = eINSTANCE.getMDACombinedFragment_AllMessageCnt();

        /**
         * The meta object literal for the '{@link nexcore.tool.mda.model.developer.operationbody.impl.MDAOperandImpl <em>MDA Operand</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see nexcore.tool.mda.model.developer.operationbody.impl.MDAOperandImpl
         * @see nexcore.tool.mda.model.developer.operationbody.impl.OperationbodyPackageImpl#getMDAOperand()
         * @generated
         */
		EClass MDA_OPERAND = eINSTANCE.getMDAOperand();

		/**
         * The meta object literal for the '<em><b>Guard</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute MDA_OPERAND__GUARD = eINSTANCE.getMDAOperand_Guard();

		/**
         * The meta object literal for the '<em><b>Messages</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference MDA_OPERAND__MESSAGES = eINSTANCE.getMDAOperand_Messages();

		/**
         * The meta object literal for the '<em><b>Fragments</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference MDA_OPERAND__FRAGMENTS = eINSTANCE.getMDAOperand_Fragments();

		/**
         * The meta object literal for the '<em><b>Order</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute MDA_OPERAND__ORDER = eINSTANCE.getMDAOperand_Order();

        /**
         * The meta object literal for the '<em><b>All Message Cnt</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute MDA_OPERAND__ALL_MESSAGE_CNT = eINSTANCE.getMDAOperand_AllMessageCnt();

        /**
         * The meta object literal for the '{@link nexcore.tool.mda.model.developer.operationbody.impl.MDAMessageImpl <em>MDA Message</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see nexcore.tool.mda.model.developer.operationbody.impl.MDAMessageImpl
         * @see nexcore.tool.mda.model.developer.operationbody.impl.OperationbodyPackageImpl#getMDAMessage()
         * @generated
         */
		EClass MDA_MESSAGE = eINSTANCE.getMDAMessage();

		/**
         * The meta object literal for the '<em><b>Message Sort</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute MDA_MESSAGE__MESSAGE_SORT = eINSTANCE.getMDAMessage_MessageSort();

		/**
         * The meta object literal for the '<em><b>Operation QName</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute MDA_MESSAGE__OPERATION_QNAME = eINSTANCE.getMDAMessage_OperationQName();

		/**
         * The meta object literal for the '{@link nexcore.tool.mda.model.developer.operationbody.IMDAOperation <em>IMDA Operation</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see nexcore.tool.mda.model.developer.operationbody.IMDAOperation
         * @see nexcore.tool.mda.model.developer.operationbody.impl.OperationbodyPackageImpl#getIMDAOperation()
         * @generated
         */
		EClass IMDA_OPERATION = eINSTANCE.getIMDAOperation();

		/**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute IMDA_OPERATION__ID = eINSTANCE.getIMDAOperation_Id();

		/**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute IMDA_OPERATION__NAME = eINSTANCE.getIMDAOperation_Name();

	}

} //OperationbodyPackage
