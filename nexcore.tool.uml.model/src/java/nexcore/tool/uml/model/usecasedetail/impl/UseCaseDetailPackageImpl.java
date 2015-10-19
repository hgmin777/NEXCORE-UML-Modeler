/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.model.usecasedetail.impl;

import nexcore.tool.uml.model.usecasedetail.FlowObject;
import nexcore.tool.uml.model.usecasedetail.UseCaseDetail;
import nexcore.tool.uml.model.usecasedetail.UseCaseDetailFactory;
import nexcore.tool.uml.model.usecasedetail.UseCaseDetailPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.usecasedetail.impl</li>
 * <li>설  명 : UseCaseDetailPackageImpl</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class UseCaseDetailPackageImpl extends EPackageImpl implements UseCaseDetailPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass useCaseDetailEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass flowObjectEClass = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the
     * package package URI value.
     * <p>
     * Note: the correct way to create the package is via the static factory
     * method {@link #init init()}, which also performs initialization of the
     * package, or returns the registered package, if one already exists. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see nexcore.tool.uml.model.usecasedetail.UseCaseDetailPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private UseCaseDetailPackageImpl() {
        super(eNS_URI, UseCaseDetailFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model,
     * and for any others upon which it depends. Simple dependencies are
     * satisfied by calling this method on all dependent packages before doing
     * anything else. This method drives initialization for interdependent
     * packages directly, in parallel with this package, itself.
     * <p>
     * Of this package and its interdependencies, all packages which have not
     * yet been registered by their URI values are first created and registered.
     * The packages are then initialized in two steps: meta-model objects for
     * all of the packages are created before any are initialized, since one
     * package's meta-model objects may refer to those of another.
     * <p>
     * Invocation of this method will not affect any packages that have already
     * been initialized. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static UseCaseDetailPackage init() {
        if (isInited)
            return (UseCaseDetailPackage) EPackage.Registry.INSTANCE.getEPackage(UseCaseDetailPackage.eNS_URI);

        // Obtain or create and register package
        UseCaseDetailPackageImpl theUseCaseDetailPackage = (UseCaseDetailPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof UseCaseDetailPackageImpl
        ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new UseCaseDetailPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        EcorePackage.eINSTANCE.eClass();

        // Create package meta-data objects
        theUseCaseDetailPackage.createPackageContents();

        // Initialize created meta-data
        theUseCaseDetailPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theUseCaseDetailPackage.freeze();

        return theUseCaseDetailPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getUseCaseDetail() {
        return useCaseDetailEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getUseCaseDetail_PreCondition() {
        return (EAttribute) useCaseDetailEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getUseCaseDetail_PostCondition() {
        return (EAttribute) useCaseDetailEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getUseCaseDetail_BasicFlowList() {
        return (EReference) useCaseDetailEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getUseCaseDetail_SubFlowList() {
        return (EReference) useCaseDetailEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getUseCaseDetail_ExceptionFlowList() {
        return (EReference) useCaseDetailEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getFlowObject() {
        return flowObjectEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getFlowObject_FlowId() {
        return (EAttribute) flowObjectEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getFlowObject_FlowName() {
        return (EAttribute) flowObjectEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getFlowObject_FlowOverview() {
        return (EAttribute) flowObjectEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getFlowObject_FlowDescription() {
        return (EAttribute) flowObjectEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public UseCaseDetailFactory getUseCaseDetailFactory() {
        return (UseCaseDetailFactory) getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package. This method is guarded to
     * have no affect on any invocation but its first. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void createPackageContents() {
        if (isCreated)
            return;
        isCreated = true;

        // Create classes and their features
        useCaseDetailEClass = createEClass(USE_CASE_DETAIL);
        createEAttribute(useCaseDetailEClass, USE_CASE_DETAIL__PRE_CONDITION);
        createEAttribute(useCaseDetailEClass, USE_CASE_DETAIL__POST_CONDITION);
        createEReference(useCaseDetailEClass, USE_CASE_DETAIL__BASIC_FLOW_LIST);
        createEReference(useCaseDetailEClass, USE_CASE_DETAIL__SUB_FLOW_LIST);
        createEReference(useCaseDetailEClass, USE_CASE_DETAIL__EXCEPTION_FLOW_LIST);

        flowObjectEClass = createEClass(FLOW_OBJECT);
        createEAttribute(flowObjectEClass, FLOW_OBJECT__FLOW_ID);
        createEAttribute(flowObjectEClass, FLOW_OBJECT__FLOW_NAME);
        createEAttribute(flowObjectEClass, FLOW_OBJECT__FLOW_OVERVIEW);
        createEAttribute(flowObjectEClass, FLOW_OBJECT__FLOW_DESCRIPTION);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model. This
     * method is guarded to have no affect on any invocation but its first. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void initializePackageContents() {
        if (isInitialized)
            return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Obtain other dependent packages
        EcorePackage theEcorePackage = (EcorePackage) EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        useCaseDetailEClass.getESuperTypes().add(theEcorePackage.getEAnnotation());

        // Initialize classes and features; add operations and parameters
        initEClass(useCaseDetailEClass,
            UseCaseDetail.class,
            "UseCaseDetail",
            !IS_ABSTRACT,
            !IS_INTERFACE,
            IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getUseCaseDetail_PreCondition(),
            ecorePackage.getEString(),
            "PreCondition",
            null,
            0,
            1,
            UseCaseDetail.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getUseCaseDetail_PostCondition(),
            ecorePackage.getEString(),
            "PostCondition",
            null,
            0,
            1,
            UseCaseDetail.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEReference(getUseCaseDetail_BasicFlowList(),
            this.getFlowObject(),
            null,
            "BasicFlowList",
            null,
            0,
            -1,
            UseCaseDetail.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            IS_COMPOSITE,
            !IS_RESOLVE_PROXIES,
            !IS_UNSETTABLE,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEReference(getUseCaseDetail_SubFlowList(),
            this.getFlowObject(),
            null,
            "SubFlowList",
            null,
            0,
            -1,
            UseCaseDetail.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            IS_COMPOSITE,
            !IS_RESOLVE_PROXIES,
            !IS_UNSETTABLE,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEReference(getUseCaseDetail_ExceptionFlowList(),
            this.getFlowObject(),
            null,
            "ExceptionFlowList",
            null,
            0,
            -1,
            UseCaseDetail.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            IS_COMPOSITE,
            !IS_RESOLVE_PROXIES,
            !IS_UNSETTABLE,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);

        initEClass(flowObjectEClass,
            FlowObject.class,
            "FlowObject",
            !IS_ABSTRACT,
            !IS_INTERFACE,
            IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getFlowObject_FlowId(),
            ecorePackage.getEString(),
            "FlowId",
            null,
            1,
            1,
            FlowObject.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getFlowObject_FlowName(),
            ecorePackage.getEString(),
            "FlowName",
            null,
            1,
            1,
            FlowObject.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getFlowObject_FlowOverview(),
            ecorePackage.getEString(),
            "FlowOverview",
            null,
            0,
            1,
            FlowObject.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getFlowObject_FlowDescription(),
            ecorePackage.getEString(),
            "FlowDescription",
            null,
            0,
            1,
            FlowObject.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);

        // Create resource
        createResource(eNS_URI);
    }

} // UseCaseDetailPackageImpl
