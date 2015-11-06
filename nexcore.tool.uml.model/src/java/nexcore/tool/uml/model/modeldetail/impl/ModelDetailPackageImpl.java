/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.model.modeldetail.impl;

import nexcore.tool.uml.model.modeldetail.ModelDetail;
import nexcore.tool.uml.model.modeldetail.ModelDetailFactory;
import nexcore.tool.uml.model.modeldetail.ModelDetailPackage;
import nexcore.tool.uml.model.modeldetail.ModelType;

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
 * <li>서브 업무명 : nexcore.tool.uml.model.modeldetail.impl</li>
 * <li>설  명 : ModelDetailPackageImpl</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class ModelDetailPackageImpl extends EPackageImpl implements ModelDetailPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass modelDetailEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum modelTypeEEnum = null;

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
     * @see nexcore.tool.uml.model.modeldetail.ModelDetailPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private ModelDetailPackageImpl() {
        super(eNS_URI, ModelDetailFactory.eINSTANCE);
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
    public static ModelDetailPackage init() {
        if (isInited)
            return (ModelDetailPackage) EPackage.Registry.INSTANCE.getEPackage(ModelDetailPackage.eNS_URI);

        // Obtain or create and register package
        ModelDetailPackageImpl theModelDetailPackage = (ModelDetailPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof ModelDetailPackageImpl
        ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new ModelDetailPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        EcorePackage.eINSTANCE.eClass();

        // Create package meta-data objects
        theModelDetailPackage.createPackageContents();

        // Initialize created meta-data
        theModelDetailPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theModelDetailPackage.freeze();

        return theModelDetailPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getModelDetail() {
        return modelDetailEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getModelDetail_ModelType() {
        return (EAttribute) modelDetailEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EEnum getModelType() {
        return modelTypeEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ModelDetailFactory getModelDetailFactory() {
        return (ModelDetailFactory) getEFactoryInstance();
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
        modelDetailEClass = createEClass(MODEL_DETAIL);
        createEAttribute(modelDetailEClass, MODEL_DETAIL__MODEL_TYPE);

        // Create enums
        modelTypeEEnum = createEEnum(MODEL_TYPE);
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
        modelDetailEClass.getESuperTypes().add(theEcorePackage.getEAnnotation());

        // Initialize classes and features; add operations and parameters
        initEClass(modelDetailEClass,
            ModelDetail.class,
            "ModelDetail",
            !IS_ABSTRACT,
            !IS_INTERFACE,
            IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getModelDetail_ModelType(),
            this.getModelType(),
            "ModelType",
            "General",
            1,
            1,
            ModelDetail.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(modelTypeEEnum, ModelType.class, "ModelType");
        addEEnumLiteral(modelTypeEEnum, ModelType.GENERAL);
        addEEnumLiteral(modelTypeEEnum, ModelType.USE_CASE);
        addEEnumLiteral(modelTypeEEnum, ModelType.ANALYSIS);
        addEEnumLiteral(modelTypeEEnum, ModelType.DESIGN);

        // Create resource
        createResource(eNS_URI);
    }

} // ModelDetailPackageImpl
