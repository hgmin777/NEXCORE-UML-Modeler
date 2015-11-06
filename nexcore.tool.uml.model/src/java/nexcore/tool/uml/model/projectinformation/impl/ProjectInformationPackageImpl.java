/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.model.projectinformation.impl;

import nexcore.tool.uml.model.projectinformation.ProjectElement;
import nexcore.tool.uml.model.projectinformation.ProjectInformationFactory;
import nexcore.tool.uml.model.projectinformation.ProjectInformationPackage;
import nexcore.tool.uml.model.projectinformation.ProjectPhaseType;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
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
 * <li>서브 업무명 : nexcore.tool.uml.model.projectinformation.impl</li>
 * <li>설  명 : ProjectInformationPackageImpl</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class ProjectInformationPackageImpl extends EPackageImpl implements ProjectInformationPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass projectElementEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum projectPhaseTypeEEnum = null;

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
     * @see nexcore.tool.uml.model.projectinformation.ProjectInformationPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private ProjectInformationPackageImpl() {
        super(eNS_URI, ProjectInformationFactory.eINSTANCE);
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
    public static ProjectInformationPackage init() {
        if (isInited)
            return (ProjectInformationPackage) EPackage.Registry.INSTANCE.getEPackage(ProjectInformationPackage.eNS_URI);

        // Obtain or create and register package
        ProjectInformationPackageImpl theProjectInformationPackage = (ProjectInformationPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof ProjectInformationPackageImpl
        ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new ProjectInformationPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        EcorePackage.eINSTANCE.eClass();

        // Create package meta-data objects
        theProjectInformationPackage.createPackageContents();

        // Initialize created meta-data
        theProjectInformationPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theProjectInformationPackage.freeze();

        return theProjectInformationPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getProjectElement() {
        return projectElementEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getProjectElement_ProjectName() {
        return (EAttribute) projectElementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getProjectElement_ProjectCode() {
        return (EAttribute) projectElementEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getProjectElement_ProjectPhase() {
        return (EAttribute) projectElementEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getProjectElement_RelatedRMProjectId() {
        return (EAttribute) projectElementEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getProjectElement_ModelVersion() {
        return (EAttribute) projectElementEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EEnum getProjectPhaseType() {
        return projectPhaseTypeEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ProjectInformationFactory getProjectInformationFactory() {
        return (ProjectInformationFactory) getEFactoryInstance();
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
        projectElementEClass = createEClass(PROJECT_ELEMENT);
        createEAttribute(projectElementEClass, PROJECT_ELEMENT__PROJECT_NAME);
        createEAttribute(projectElementEClass, PROJECT_ELEMENT__PROJECT_CODE);
        createEAttribute(projectElementEClass, PROJECT_ELEMENT__PROJECT_PHASE);
        createEAttribute(projectElementEClass, PROJECT_ELEMENT__RELATED_RM_PROJECT_ID);
        createEAttribute(projectElementEClass, PROJECT_ELEMENT__MODEL_VERSION);

        // Create enums
        projectPhaseTypeEEnum = createEEnum(PROJECT_PHASE_TYPE);
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
        projectElementEClass.getESuperTypes().add(theEcorePackage.getEAnnotation());

        // Initialize classes and features; add operations and parameters
        initEClass(projectElementEClass,
            ProjectElement.class,
            "ProjectElement",
            !IS_ABSTRACT,
            !IS_INTERFACE,
            IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getProjectElement_ProjectName(),
            ecorePackage.getEString(),
            "ProjectName",
            null,
            0,
            1,
            ProjectElement.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getProjectElement_ProjectCode(),
            ecorePackage.getEString(),
            "ProjectCode",
            null,
            0,
            1,
            ProjectElement.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getProjectElement_ProjectPhase(),
            this.getProjectPhaseType(),
            "ProjectPhase",
            "LogicalDesign",
            0,
            1,
            ProjectElement.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getProjectElement_RelatedRMProjectId(),
            ecorePackage.getEString(),
            "RelatedRMProjectId",
            null,
            0,
            1,
            ProjectElement.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getProjectElement_ModelVersion(),
            ecorePackage.getEString(),
            "ModelVersion",
            null,
            0,
            1,
            ProjectElement.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(projectPhaseTypeEEnum, ProjectPhaseType.class, "ProjectPhaseType");
        addEEnumLiteral(projectPhaseTypeEEnum, ProjectPhaseType.ANALYSIS);
        addEEnumLiteral(projectPhaseTypeEEnum, ProjectPhaseType.LOGICAL_DESIGN);
        addEEnumLiteral(projectPhaseTypeEEnum, ProjectPhaseType.PHYSICAL_DESIGN);
        addEEnumLiteral(projectPhaseTypeEEnum, ProjectPhaseType.DEVELOPMENT);
        addEEnumLiteral(projectPhaseTypeEEnum, ProjectPhaseType.UNIT_TEST);
        addEEnumLiteral(projectPhaseTypeEEnum, ProjectPhaseType.INTEGRATION_TEST);

        // Create resource
        createResource(eNS_URI);
    }

} // ProjectInformationPackageImpl
