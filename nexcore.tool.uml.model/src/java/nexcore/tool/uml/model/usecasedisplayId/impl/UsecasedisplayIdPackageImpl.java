/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.model.usecasedisplayId.impl;

import nexcore.tool.uml.model.usecasedisplayId.UseCaseDisplayId;
import nexcore.tool.uml.model.usecasedisplayId.UsecasedisplayIdFactory;
import nexcore.tool.uml.model.usecasedisplayId.UsecasedisplayIdPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.usecasedisplayId.impl</li>
 * <li>설  명 : UsecasedisplayIdPackageImpl</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class UsecasedisplayIdPackageImpl extends EPackageImpl implements UsecasedisplayIdPackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass useCaseDisplayIdEClass = null;

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
     * @see nexcore.tool.uml.model.usecasedisplayId.UsecasedisplayIdPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private UsecasedisplayIdPackageImpl() {
        super(eNS_URI, UsecasedisplayIdFactory.eINSTANCE);
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
     * <p>This method is used to initialize {@link UsecasedisplayIdPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static UsecasedisplayIdPackage init() {
        if (isInited) return (UsecasedisplayIdPackage)EPackage.Registry.INSTANCE.getEPackage(UsecasedisplayIdPackage.eNS_URI);

        // Obtain or create and register package
        UsecasedisplayIdPackageImpl theUsecasedisplayIdPackage = (UsecasedisplayIdPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof UsecasedisplayIdPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new UsecasedisplayIdPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        EcorePackage.eINSTANCE.eClass();

        // Create package meta-data objects
        theUsecasedisplayIdPackage.createPackageContents();

        // Initialize created meta-data
        theUsecasedisplayIdPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theUsecasedisplayIdPackage.freeze();

  
        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(UsecasedisplayIdPackage.eNS_URI, theUsecasedisplayIdPackage);
        return theUsecasedisplayIdPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getUseCaseDisplayId() {
        return useCaseDisplayIdEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getUseCaseDisplayId_DisplayId() {
        return (EAttribute)useCaseDisplayIdEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public UsecasedisplayIdFactory getUsecasedisplayIdFactory() {
        return (UsecasedisplayIdFactory)getEFactoryInstance();
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
        useCaseDisplayIdEClass = createEClass(USE_CASE_DISPLAY_ID);
        createEAttribute(useCaseDisplayIdEClass, USE_CASE_DISPLAY_ID__DISPLAY_ID);
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

        // Obtain other dependent packages
        EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        useCaseDisplayIdEClass.getESuperTypes().add(theEcorePackage.getEAnnotation());

        // Initialize classes and features; add operations and parameters
        initEClass(useCaseDisplayIdEClass, UseCaseDisplayId.class, "UseCaseDisplayId", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getUseCaseDisplayId_DisplayId(), ecorePackage.getEString(), "DisplayId", null, 0, 1, UseCaseDisplayId.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create resource
        createResource(eNS_URI);
    }

} //UsecasedisplayIdPackageImpl
