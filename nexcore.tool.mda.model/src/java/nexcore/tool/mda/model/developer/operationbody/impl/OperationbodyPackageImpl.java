/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.mda.model.developer.operationbody.impl;

import nexcore.tool.mda.model.developer.operationbody.IMDAOperation;
import nexcore.tool.mda.model.developer.operationbody.MDACombinedFragment;
import nexcore.tool.mda.model.developer.operationbody.MDAMessage;
import nexcore.tool.mda.model.developer.operationbody.MDAOperand;
import nexcore.tool.mda.model.developer.operationbody.MDAOperationBody;
import nexcore.tool.mda.model.developer.operationbody.OperationbodyFactory;
import nexcore.tool.mda.model.developer.operationbody.OperationbodyPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class OperationbodyPackageImpl extends EPackageImpl implements OperationbodyPackage {
	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static final String copyright = "";

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass mdaOperationBodyEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass mdaCombinedFragmentEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass mdaOperandEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass mdaMessageEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass imdaOperationEClass = null;

	/**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
     * package URI value.
     * <p>Note: the correct way to create the package is via the static
     * factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package,
     * if one already exists.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see nexcore.tool.mda.model.developer.operationbody.OperationbodyPackage#eNS_URI
     * @see #init()
     * @generated
     */
	private OperationbodyPackageImpl() {
        super(eNS_URI, OperationbodyFactory.eINSTANCE);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private static boolean isInited = false;

	/**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     * 
     * <p>This method is used to initialize {@link OperationbodyPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
	public static OperationbodyPackage init() {
        if (isInited) return (OperationbodyPackage)EPackage.Registry.INSTANCE.getEPackage(OperationbodyPackage.eNS_URI);

        // Obtain or create and register package
        OperationbodyPackageImpl theOperationbodyPackage = (OperationbodyPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof OperationbodyPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new OperationbodyPackageImpl());

        isInited = true;

        // Create package meta-data objects
        theOperationbodyPackage.createPackageContents();

        // Initialize created meta-data
        theOperationbodyPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theOperationbodyPackage.freeze();

  
        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(OperationbodyPackage.eNS_URI, theOperationbodyPackage);
        return theOperationbodyPackage;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getMDAOperationBody() {
        return mdaOperationBodyEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getMDAOperationBody_QualifiedName() {
        return (EAttribute)mdaOperationBodyEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getMDAOperationBody_Fragments() {
        return (EReference)mdaOperationBodyEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getMDAOperationBody_Messages() {
        return (EReference)mdaOperationBodyEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getMDAOperationBody_Order() {
        return (EAttribute)mdaOperationBodyEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getMDACombinedFragment() {
        return mdaCombinedFragmentEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getMDACombinedFragment_InteractionOperator() {
        return (EAttribute)mdaCombinedFragmentEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getMDACombinedFragment_Operands() {
        return (EReference)mdaCombinedFragmentEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getMDACombinedFragment_AllMessageCnt() {
        return (EAttribute)mdaCombinedFragmentEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getMDAOperand() {
        return mdaOperandEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getMDAOperand_Guard() {
        return (EAttribute)mdaOperandEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getMDAOperand_Messages() {
        return (EReference)mdaOperandEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getMDAOperand_Fragments() {
        return (EReference)mdaOperandEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getMDAOperand_Order() {
        return (EAttribute)mdaOperandEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getMDAOperand_AllMessageCnt() {
        return (EAttribute)mdaOperandEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getMDAMessage() {
        return mdaMessageEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getMDAMessage_MessageSort() {
        return (EAttribute)mdaMessageEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getMDAMessage_OperationQName() {
        return (EAttribute)mdaMessageEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getIMDAOperation() {
        return imdaOperationEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getIMDAOperation_Id() {
        return (EAttribute)imdaOperationEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getIMDAOperation_Name() {
        return (EAttribute)imdaOperationEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public OperationbodyFactory getOperationbodyFactory() {
        return (OperationbodyFactory)getEFactoryInstance();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private boolean isCreated = false;

	/**
     * Creates the meta-model objects for the package.  This method is
     * guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void createPackageContents() {
        if (isCreated) return;
        isCreated = true;

        // Create classes and their features
        mdaOperationBodyEClass = createEClass(MDA_OPERATION_BODY);
        createEAttribute(mdaOperationBodyEClass, MDA_OPERATION_BODY__QUALIFIED_NAME);
        createEReference(mdaOperationBodyEClass, MDA_OPERATION_BODY__FRAGMENTS);
        createEReference(mdaOperationBodyEClass, MDA_OPERATION_BODY__MESSAGES);
        createEAttribute(mdaOperationBodyEClass, MDA_OPERATION_BODY__ORDER);

        mdaCombinedFragmentEClass = createEClass(MDA_COMBINED_FRAGMENT);
        createEAttribute(mdaCombinedFragmentEClass, MDA_COMBINED_FRAGMENT__INTERACTION_OPERATOR);
        createEReference(mdaCombinedFragmentEClass, MDA_COMBINED_FRAGMENT__OPERANDS);
        createEAttribute(mdaCombinedFragmentEClass, MDA_COMBINED_FRAGMENT__ALL_MESSAGE_CNT);

        mdaOperandEClass = createEClass(MDA_OPERAND);
        createEAttribute(mdaOperandEClass, MDA_OPERAND__GUARD);
        createEReference(mdaOperandEClass, MDA_OPERAND__MESSAGES);
        createEReference(mdaOperandEClass, MDA_OPERAND__FRAGMENTS);
        createEAttribute(mdaOperandEClass, MDA_OPERAND__ORDER);
        createEAttribute(mdaOperandEClass, MDA_OPERAND__ALL_MESSAGE_CNT);

        mdaMessageEClass = createEClass(MDA_MESSAGE);
        createEAttribute(mdaMessageEClass, MDA_MESSAGE__MESSAGE_SORT);
        createEAttribute(mdaMessageEClass, MDA_MESSAGE__OPERATION_QNAME);

        imdaOperationEClass = createEClass(IMDA_OPERATION);
        createEAttribute(imdaOperationEClass, IMDA_OPERATION__ID);
        createEAttribute(imdaOperationEClass, IMDA_OPERATION__NAME);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private boolean isInitialized = false;

	/**
     * Complete the initialization of the package and its meta-model.  This
     * method is guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void initializePackageContents() {
        if (isInitialized) return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        mdaOperationBodyEClass.getESuperTypes().add(this.getIMDAOperation());
        mdaCombinedFragmentEClass.getESuperTypes().add(this.getIMDAOperation());
        mdaOperandEClass.getESuperTypes().add(this.getIMDAOperation());
        mdaMessageEClass.getESuperTypes().add(this.getIMDAOperation());

        // Initialize classes and features; add operations and parameters
        initEClass(mdaOperationBodyEClass, MDAOperationBody.class, "MDAOperationBody", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getMDAOperationBody_QualifiedName(), ecorePackage.getEString(), "qualifiedName", null, 1, 1, MDAOperationBody.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getMDAOperationBody_Fragments(), this.getMDACombinedFragment(), null, "fragments", null, 0, -1, MDAOperationBody.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getMDAOperationBody_Messages(), this.getMDAMessage(), null, "messages", null, 0, -1, MDAOperationBody.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getMDAOperationBody_Order(), ecorePackage.getEString(), "order", null, 0, -1, MDAOperationBody.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(mdaCombinedFragmentEClass, MDACombinedFragment.class, "MDACombinedFragment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getMDACombinedFragment_InteractionOperator(), ecorePackage.getEString(), "interactionOperator", null, 1, 1, MDACombinedFragment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getMDACombinedFragment_Operands(), this.getMDAOperand(), null, "operands", null, 1, -1, MDACombinedFragment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getMDACombinedFragment_AllMessageCnt(), ecorePackage.getEInt(), "allMessageCnt", null, 1, 1, MDACombinedFragment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(mdaOperandEClass, MDAOperand.class, "MDAOperand", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getMDAOperand_Guard(), ecorePackage.getEString(), "guard", null, 1, 1, MDAOperand.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getMDAOperand_Messages(), this.getMDAMessage(), null, "messages", null, 0, -1, MDAOperand.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getMDAOperand_Fragments(), this.getMDACombinedFragment(), null, "fragments", null, 0, -1, MDAOperand.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getMDAOperand_Order(), ecorePackage.getEString(), "order", null, 0, -1, MDAOperand.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getMDAOperand_AllMessageCnt(), ecorePackage.getEInt(), "allMessageCnt", null, 1, 1, MDAOperand.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(mdaMessageEClass, MDAMessage.class, "MDAMessage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getMDAMessage_MessageSort(), ecorePackage.getEString(), "messageSort", null, 1, 1, MDAMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getMDAMessage_OperationQName(), ecorePackage.getEString(), "operationQName", null, 1, 1, MDAMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(imdaOperationEClass, IMDAOperation.class, "IMDAOperation", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getIMDAOperation_Id(), ecorePackage.getEString(), "id", null, 1, 1, IMDAOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getIMDAOperation_Name(), ecorePackage.getEString(), "name", null, 1, 1, IMDAOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create resource
        createResource(eNS_URI);
    }

} //OperationbodyPackageImpl
