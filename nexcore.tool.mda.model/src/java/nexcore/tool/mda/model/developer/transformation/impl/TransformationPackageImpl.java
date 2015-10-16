/*
 * Copyright (c) 2010 SK C&C Co., Ltd. All rights reserved.
 * 
 * This software is the confidential and proprietary information of SK C&C. You
 * shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK
 * C&C.
 */

package nexcore.tool.mda.model.developer.transformation.impl;

import nexcore.tool.mda.model.developer.transformation.ClassRule;
import nexcore.tool.mda.model.developer.transformation.MDADevRuleSet;
import nexcore.tool.mda.model.developer.transformation.OperationRule;
import nexcore.tool.mda.model.developer.transformation.TargetObjectNameType;
import nexcore.tool.mda.model.developer.transformation.TargetProjectType;
import nexcore.tool.mda.model.developer.transformation.TransformationFactory;
import nexcore.tool.mda.model.developer.transformation.TransformationPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class TransformationPackageImpl extends EPackageImpl implements TransformationPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = "Copyright (c) 2010 SK C&C Co., Ltd. All rights reserved.\r\n\r\nThis software is the confidential and proprietary information of SK C&C.\r\nYou shall not disclose such confidential information and shall use it\r\nonly in accordance with the terms of the license agreement you entered into\r\nwith SK C&C.";

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass mdaDevRuleSetEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass classRuleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass operationRuleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum targetObjectNameTypeEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum targetProjectTypeEEnum = null;

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
     * @see nexcore.tool.mda.model.developer.transformation.TransformationPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private TransformationPackageImpl() {
        super(eNS_URI, TransformationFactory.eINSTANCE);
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
    public static TransformationPackage init() {
        if (isInited)
            return (TransformationPackage) EPackage.Registry.INSTANCE.getEPackage(TransformationPackage.eNS_URI);

        // Obtain or create and register package
        TransformationPackageImpl theTransformationPackage = (TransformationPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof TransformationPackageImpl
        ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new TransformationPackageImpl());

        isInited = true;

        // Create package meta-data objects
        theTransformationPackage.createPackageContents();

        // Initialize created meta-data
        theTransformationPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theTransformationPackage.freeze();

        return theTransformationPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getMDADevRuleSet() {
        return mdaDevRuleSetEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getMDADevRuleSet_ProfileLocation() {
        return (EAttribute) mdaDevRuleSetEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getMDADevRuleSet_ClassRule() {
        return (EReference) mdaDevRuleSetEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getMDADevRuleSet_OperationRule() {
        return (EReference) mdaDevRuleSetEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getMDADevRuleSet_ExternalTemplate() {
        return (EAttribute) mdaDevRuleSetEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getMDADevRuleSet_TemplateType() {
        return (EAttribute) mdaDevRuleSetEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getMDADevRuleSet_TargetProjectLocation() {
        return (EAttribute) mdaDevRuleSetEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getMDADevRuleSet_DefaultTargetSourceFolder() {
        return (EAttribute) mdaDevRuleSetEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getMDADevRuleSet_DefaultFileExtension() {
        return (EAttribute) mdaDevRuleSetEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getMDADevRuleSet_TemplatePluginId() {
        return (EAttribute) mdaDevRuleSetEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getMDADevRuleSet_TemplatePluginPath() {
        return (EAttribute) mdaDevRuleSetEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getMDADevRuleSet_TemplateName() {
        return (EAttribute) mdaDevRuleSetEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getMDADevRuleSet_TargetObjectNaming() {
        return (EAttribute) mdaDevRuleSetEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getMDADevRuleSet_TargetProjectType() {
        return (EAttribute) mdaDevRuleSetEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getMDADevRuleSet_MakeSelfOperation() {
        return (EAttribute) mdaDevRuleSetEClass.getEStructuralFeatures().get(13);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getMDADevRuleSet_ReplyMessageUse() {
        return (EAttribute) mdaDevRuleSetEClass.getEStructuralFeatures().get(14);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getClassRule() {
        return classRuleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getClassRule_Name() {
        return (EAttribute) classRuleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getClassRule_Stereotype() {
        return (EAttribute) classRuleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getClassRule_GenerateGetterSetter() {
        return (EAttribute) classRuleEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getClassRule_Extension() {
        return (EAttribute) classRuleEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getClassRule_Version() {
        return (EAttribute) classRuleEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getClassRule_TargetFolder() {
        return (EAttribute) classRuleEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getClassRule_Prefix() {
        return (EAttribute) classRuleEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getClassRule_Postfix() {
        return (EAttribute) classRuleEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getClassRule_Template() {
        return (EAttribute) classRuleEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getClassRule_Implements() {
        return (EAttribute) classRuleEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getClassRule_Extends() {
        return (EAttribute) classRuleEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getOperationRule() {
        return operationRuleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getOperationRule_Name() {
        return (EAttribute) operationRuleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getOperationRule_Stereotype() {
        return (EAttribute) operationRuleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getOperationRule_Template() {
        return (EAttribute) operationRuleEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getOperationRule_Prefix() {
        return (EAttribute) operationRuleEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getOperationRule_Postfix() {
        return (EAttribute) operationRuleEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getOperationRule_Version() {
        return (EAttribute) operationRuleEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EEnum getTargetObjectNameType() {
        return targetObjectNameTypeEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EEnum getTargetProjectType() {
        return targetProjectTypeEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public TransformationFactory getTransformationFactory() {
        return (TransformationFactory) getEFactoryInstance();
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
        mdaDevRuleSetEClass = createEClass(MDA_DEV_RULE_SET);
        createEAttribute(mdaDevRuleSetEClass, MDA_DEV_RULE_SET__PROFILE_LOCATION);
        createEReference(mdaDevRuleSetEClass, MDA_DEV_RULE_SET__CLASS_RULE);
        createEReference(mdaDevRuleSetEClass, MDA_DEV_RULE_SET__OPERATION_RULE);
        createEAttribute(mdaDevRuleSetEClass, MDA_DEV_RULE_SET__EXTERNAL_TEMPLATE);
        createEAttribute(mdaDevRuleSetEClass, MDA_DEV_RULE_SET__TEMPLATE_TYPE);
        createEAttribute(mdaDevRuleSetEClass, MDA_DEV_RULE_SET__TARGET_PROJECT_LOCATION);
        createEAttribute(mdaDevRuleSetEClass, MDA_DEV_RULE_SET__DEFAULT_TARGET_SOURCE_FOLDER);
        createEAttribute(mdaDevRuleSetEClass, MDA_DEV_RULE_SET__DEFAULT_FILE_EXTENSION);
        createEAttribute(mdaDevRuleSetEClass, MDA_DEV_RULE_SET__TEMPLATE_PLUGIN_ID);
        createEAttribute(mdaDevRuleSetEClass, MDA_DEV_RULE_SET__TEMPLATE_PLUGIN_PATH);
        createEAttribute(mdaDevRuleSetEClass, MDA_DEV_RULE_SET__TEMPLATE_NAME);
        createEAttribute(mdaDevRuleSetEClass, MDA_DEV_RULE_SET__TARGET_OBJECT_NAMING);
        createEAttribute(mdaDevRuleSetEClass, MDA_DEV_RULE_SET__TARGET_PROJECT_TYPE);
        createEAttribute(mdaDevRuleSetEClass, MDA_DEV_RULE_SET__MAKE_SELF_OPERATION);
        createEAttribute(mdaDevRuleSetEClass, MDA_DEV_RULE_SET__REPLY_MESSAGE_USE);

        classRuleEClass = createEClass(CLASS_RULE);
        createEAttribute(classRuleEClass, CLASS_RULE__NAME);
        createEAttribute(classRuleEClass, CLASS_RULE__STEREOTYPE);
        createEAttribute(classRuleEClass, CLASS_RULE__GENERATE_GETTER_SETTER);
        createEAttribute(classRuleEClass, CLASS_RULE__EXTENSION);
        createEAttribute(classRuleEClass, CLASS_RULE__VERSION);
        createEAttribute(classRuleEClass, CLASS_RULE__TARGET_FOLDER);
        createEAttribute(classRuleEClass, CLASS_RULE__PREFIX);
        createEAttribute(classRuleEClass, CLASS_RULE__POSTFIX);
        createEAttribute(classRuleEClass, CLASS_RULE__TEMPLATE);
        createEAttribute(classRuleEClass, CLASS_RULE__IMPLEMENTS);
        createEAttribute(classRuleEClass, CLASS_RULE__EXTENDS);

        operationRuleEClass = createEClass(OPERATION_RULE);
        createEAttribute(operationRuleEClass, OPERATION_RULE__NAME);
        createEAttribute(operationRuleEClass, OPERATION_RULE__STEREOTYPE);
        createEAttribute(operationRuleEClass, OPERATION_RULE__TEMPLATE);
        createEAttribute(operationRuleEClass, OPERATION_RULE__PREFIX);
        createEAttribute(operationRuleEClass, OPERATION_RULE__POSTFIX);
        createEAttribute(operationRuleEClass, OPERATION_RULE__VERSION);

        // Create enums
        targetObjectNameTypeEEnum = createEEnum(TARGET_OBJECT_NAME_TYPE);
        targetProjectTypeEEnum = createEEnum(TARGET_PROJECT_TYPE);
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

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes

        // Initialize classes and features; add operations and parameters
        initEClass(mdaDevRuleSetEClass,
            MDADevRuleSet.class,
            "MDADevRuleSet",
            !IS_ABSTRACT,
            !IS_INTERFACE,
            IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getMDADevRuleSet_ProfileLocation(),
            ecorePackage.getEString(),
            "profileLocation",
            null,
            1,
            1,
            MDADevRuleSet.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEReference(getMDADevRuleSet_ClassRule(),
            this.getClassRule(),
            null,
            "classRule",
            null,
            0,
            -1,
            MDADevRuleSet.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            IS_COMPOSITE,
            !IS_RESOLVE_PROXIES,
            !IS_UNSETTABLE,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEReference(getMDADevRuleSet_OperationRule(),
            this.getOperationRule(),
            null,
            "operationRule",
            null,
            0,
            -1,
            MDADevRuleSet.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            IS_COMPOSITE,
            !IS_RESOLVE_PROXIES,
            !IS_UNSETTABLE,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getMDADevRuleSet_ExternalTemplate(),
            ecorePackage.getEString(),
            "externalTemplate",
            null,
            1,
            1,
            MDADevRuleSet.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getMDADevRuleSet_TemplateType(),
            ecorePackage.getEString(),
            "templateType",
            null,
            1,
            1,
            MDADevRuleSet.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getMDADevRuleSet_TargetProjectLocation(),
            ecorePackage.getEString(),
            "targetProjectLocation",
            null,
            1,
            1,
            MDADevRuleSet.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getMDADevRuleSet_DefaultTargetSourceFolder(),
            ecorePackage.getEString(),
            "defaultTargetSourceFolder",
            null,
            1,
            1,
            MDADevRuleSet.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getMDADevRuleSet_DefaultFileExtension(),
            ecorePackage.getEString(),
            "defaultFileExtension",
            null,
            1,
            1,
            MDADevRuleSet.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getMDADevRuleSet_TemplatePluginId(),
            ecorePackage.getEString(),
            "templatePluginId",
            null,
            1,
            1,
            MDADevRuleSet.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getMDADevRuleSet_TemplatePluginPath(),
            ecorePackage.getEString(),
            "templatePluginPath",
            null,
            1,
            1,
            MDADevRuleSet.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getMDADevRuleSet_TemplateName(),
            ecorePackage.getEString(),
            "templateName",
            null,
            1,
            1,
            MDADevRuleSet.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getMDADevRuleSet_TargetObjectNaming(),
            this.getTargetObjectNameType(),
            "targetObjectNaming",
            "",
            1,
            1,
            MDADevRuleSet.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getMDADevRuleSet_TargetProjectType(),
            this.getTargetProjectType(),
            "targetProjectType",
            null,
            1,
            1,
            MDADevRuleSet.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getMDADevRuleSet_MakeSelfOperation(),
            ecorePackage.getEBoolean(),
            "makeSelfOperation",
            "true",
            1,
            1,
            MDADevRuleSet.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getMDADevRuleSet_ReplyMessageUse(),
            ecorePackage.getEInt(),
            "replyMessageUse",
            "0",
            1,
            1,
            MDADevRuleSet.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);

        initEClass(classRuleEClass,
            ClassRule.class,
            "ClassRule",
            !IS_ABSTRACT,
            !IS_INTERFACE,
            IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getClassRule_Name(),
            ecorePackage.getEString(),
            "name",
            null,
            1,
            1,
            ClassRule.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getClassRule_Stereotype(),
            ecorePackage.getEString(),
            "stereotype",
            null,
            1,
            1,
            ClassRule.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getClassRule_GenerateGetterSetter(),
            ecorePackage.getEBoolean(),
            "generateGetterSetter",
            null,
            1,
            1,
            ClassRule.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getClassRule_Extension(),
            ecorePackage.getEString(),
            "extension",
            null,
            1,
            1,
            ClassRule.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getClassRule_Version(),
            ecorePackage.getEString(),
            "version",
            null,
            1,
            1,
            ClassRule.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getClassRule_TargetFolder(),
            ecorePackage.getEString(),
            "targetFolder",
            null,
            1,
            1,
            ClassRule.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getClassRule_Prefix(),
            ecorePackage.getEString(),
            "prefix",
            null,
            1,
            1,
            ClassRule.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getClassRule_Postfix(),
            ecorePackage.getEString(),
            "postfix",
            null,
            1,
            1,
            ClassRule.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getClassRule_Template(),
            ecorePackage.getEString(),
            "template",
            null,
            1,
            1,
            ClassRule.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getClassRule_Implements(),
            ecorePackage.getEString(),
            "implements",
            null,
            0,
            1,
            ClassRule.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getClassRule_Extends(),
            ecorePackage.getEString(),
            "extends",
            null,
            0,
            1,
            ClassRule.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);

        initEClass(operationRuleEClass,
            OperationRule.class,
            "OperationRule",
            !IS_ABSTRACT,
            !IS_INTERFACE,
            IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getOperationRule_Name(),
            ecorePackage.getEString(),
            "name",
            null,
            1,
            1,
            OperationRule.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getOperationRule_Stereotype(),
            ecorePackage.getEString(),
            "stereotype",
            null,
            1,
            1,
            OperationRule.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getOperationRule_Template(),
            ecorePackage.getEString(),
            "template",
            null,
            1,
            1,
            OperationRule.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getOperationRule_Prefix(),
            ecorePackage.getEString(),
            "prefix",
            null,
            1,
            1,
            OperationRule.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getOperationRule_Postfix(),
            ecorePackage.getEString(),
            "postfix",
            null,
            1,
            1,
            OperationRule.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getOperationRule_Version(),
            ecorePackage.getEString(),
            "version",
            null,
            1,
            1,
            OperationRule.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(targetObjectNameTypeEEnum, TargetObjectNameType.class, "TargetObjectNameType");
        addEEnumLiteral(targetObjectNameTypeEEnum, TargetObjectNameType.CLASS_NAME);
        addEEnumLiteral(targetObjectNameTypeEEnum, TargetObjectNameType.LIFELINE_NAME);
        addEEnumLiteral(targetObjectNameTypeEEnum, TargetObjectNameType.GLOSSARY);

        initEEnum(targetProjectTypeEEnum, TargetProjectType.class, "TargetProjectType");
        addEEnumLiteral(targetProjectTypeEEnum, TargetProjectType.JAVA);
        addEEnumLiteral(targetProjectTypeEEnum, TargetProjectType.EGOV);
        addEEnumLiteral(targetProjectTypeEEnum, TargetProjectType.NEXCOREJ2EE);
        addEEnumLiteral(targetProjectTypeEEnum, TargetProjectType.NEXCOREC);

        // Create resource
        createResource(eNS_URI);
    }

} // TransformationPackageImpl
