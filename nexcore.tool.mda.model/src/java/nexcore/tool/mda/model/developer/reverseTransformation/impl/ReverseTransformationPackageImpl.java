/**
 * Copyright (c) 2012 SK C&C Co., Ltd. All rights reserved. 
 * 
 * This software is the confidential and proprietary information of SK C&C. 
 * You shall not disclose such confidential information and shall use it 
 * only in accordance with the terms of the license agreement you entered into 
 * with SK C&C.
 */
package nexcore.tool.mda.model.developer.reverseTransformation.impl;

import nexcore.tool.mda.model.developer.reverseTransformation.ClassDiagramRule;
import nexcore.tool.mda.model.developer.reverseTransformation.ClassRule;
import nexcore.tool.mda.model.developer.reverseTransformation.DiagramRule;
import nexcore.tool.mda.model.developer.reverseTransformation.ElementRule;
import nexcore.tool.mda.model.developer.reverseTransformation.MDAReverseRuleSet;
import nexcore.tool.mda.model.developer.reverseTransformation.ReverseTransformationFactory;
import nexcore.tool.mda.model.developer.reverseTransformation.ReverseTransformationPackage;
import nexcore.tool.mda.model.developer.reverseTransformation.SequenceDiagramRule;
import nexcore.tool.mda.model.developer.reverseTransformation.SourceProjectType;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ReverseTransformationPackageImpl extends EPackageImpl implements ReverseTransformationPackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2012 SK C&C Co., Ltd. All rights reserved. \r\n\r\nThis software is the confidential and proprietary information of SK C&C. \r\nYou shall not disclose such confidential information and shall use it \r\nonly in accordance with the terms of the license agreement you entered into \r\nwith SK C&C.";

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass mdaReverseRuleSetEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass classRuleEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass diagramRuleEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass classDiagramRuleEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass elementRuleEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass sequenceDiagramRuleEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum sourceProjectTypeEEnum = null;

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
     * @see nexcore.tool.mda.model.developer.reverseTransformation.ReverseTransformationPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private ReverseTransformationPackageImpl() {
        super(eNS_URI, ReverseTransformationFactory.eINSTANCE);
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
     * <p>This method is used to initialize {@link ReverseTransformationPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static ReverseTransformationPackage init() {
        if (isInited) return (ReverseTransformationPackage)EPackage.Registry.INSTANCE.getEPackage(ReverseTransformationPackage.eNS_URI);

        // Obtain or create and register package
        ReverseTransformationPackageImpl theReverseTransformationPackage = (ReverseTransformationPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ReverseTransformationPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ReverseTransformationPackageImpl());

        isInited = true;

        // Create package meta-data objects
        theReverseTransformationPackage.createPackageContents();

        // Initialize created meta-data
        theReverseTransformationPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theReverseTransformationPackage.freeze();

  
        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(ReverseTransformationPackage.eNS_URI, theReverseTransformationPackage);
        return theReverseTransformationPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getMDAReverseRuleSet() {
        return mdaReverseRuleSetEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getMDAReverseRuleSet_SourceProjectType() {
        return (EAttribute)mdaReverseRuleSetEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getMDAReverseRuleSet_SourceLanguage() {
        return (EAttribute)mdaReverseRuleSetEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getMDAReverseRuleSet_SourceProjects() {
        return (EAttribute)mdaReverseRuleSetEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getMDAReverseRuleSet_TargetModelURI() {
        return (EAttribute)mdaReverseRuleSetEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getMDAReverseRuleSet_MergeType() {
        return (EAttribute)mdaReverseRuleSetEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getMDAReverseRuleSet_ReferenceModelChange() {
        return (EAttribute)mdaReverseRuleSetEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getMDAReverseRuleSet_ProfileLocation() {
        return (EAttribute)mdaReverseRuleSetEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getMDAReverseRuleSet_ClassRules() {
        return (EReference)mdaReverseRuleSetEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getMDAReverseRuleSet_ClassDiagramRule() {
        return (EReference)mdaReverseRuleSetEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getMDAReverseRuleSet_SequenceDiagramRule() {
        return (EReference)mdaReverseRuleSetEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getClassRule() {
        return classRuleEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getClassRule_PackageName() {
        return (EAttribute)classRuleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getClassRule_GenerateGetterSetter() {
        return (EAttribute)classRuleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getClassRule_GenerateConstructor() {
        return (EAttribute)classRuleEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getClassRule_GenerateDestructor() {
        return (EAttribute)classRuleEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getClassRule_OperationRules() {
        return (EReference)classRuleEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getClassRule_AttributeRules() {
        return (EReference)classRuleEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDiagramRule() {
        return diagramRuleEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDiagramRule_BasePackage() {
        return (EAttribute)diagramRuleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDiagramRule_PackageDepth() {
        return (EAttribute)diagramRuleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDiagramRule_CreateYN() {
        return (EAttribute)diagramRuleEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getClassDiagramRule() {
        return classDiagramRuleEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getElementRule() {
        return elementRuleEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getElementRule_Name() {
        return (EAttribute)elementRuleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getElementRule_Prefix() {
        return (EAttribute)elementRuleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getElementRule_Postfix() {
        return (EAttribute)elementRuleEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getElementRule_CreateYN() {
        return (EAttribute)elementRuleEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getElementRule_Stereotype() {
        return (EAttribute)elementRuleEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getSequenceDiagramRule() {
        return sequenceDiagramRuleEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSequenceDiagramRule_CreateVOPCYN() {
        return (EAttribute)sequenceDiagramRuleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSequenceDiagramRule_MessageCallDepth() {
        return (EAttribute)sequenceDiagramRuleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getSourceProjectType() {
        return sourceProjectTypeEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ReverseTransformationFactory getReverseTransformationFactory() {
        return (ReverseTransformationFactory)getEFactoryInstance();
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
        mdaReverseRuleSetEClass = createEClass(MDA_REVERSE_RULE_SET);
        createEAttribute(mdaReverseRuleSetEClass, MDA_REVERSE_RULE_SET__SOURCE_PROJECT_TYPE);
        createEAttribute(mdaReverseRuleSetEClass, MDA_REVERSE_RULE_SET__SOURCE_LANGUAGE);
        createEAttribute(mdaReverseRuleSetEClass, MDA_REVERSE_RULE_SET__SOURCE_PROJECTS);
        createEAttribute(mdaReverseRuleSetEClass, MDA_REVERSE_RULE_SET__TARGET_MODEL_URI);
        createEAttribute(mdaReverseRuleSetEClass, MDA_REVERSE_RULE_SET__MERGE_TYPE);
        createEAttribute(mdaReverseRuleSetEClass, MDA_REVERSE_RULE_SET__REFERENCE_MODEL_CHANGE);
        createEAttribute(mdaReverseRuleSetEClass, MDA_REVERSE_RULE_SET__PROFILE_LOCATION);
        createEReference(mdaReverseRuleSetEClass, MDA_REVERSE_RULE_SET__CLASS_RULES);
        createEReference(mdaReverseRuleSetEClass, MDA_REVERSE_RULE_SET__CLASS_DIAGRAM_RULE);
        createEReference(mdaReverseRuleSetEClass, MDA_REVERSE_RULE_SET__SEQUENCE_DIAGRAM_RULE);

        classRuleEClass = createEClass(CLASS_RULE);
        createEAttribute(classRuleEClass, CLASS_RULE__PACKAGE_NAME);
        createEAttribute(classRuleEClass, CLASS_RULE__GENERATE_GETTER_SETTER);
        createEAttribute(classRuleEClass, CLASS_RULE__GENERATE_CONSTRUCTOR);
        createEAttribute(classRuleEClass, CLASS_RULE__GENERATE_DESTRUCTOR);
        createEReference(classRuleEClass, CLASS_RULE__OPERATION_RULES);
        createEReference(classRuleEClass, CLASS_RULE__ATTRIBUTE_RULES);

        diagramRuleEClass = createEClass(DIAGRAM_RULE);
        createEAttribute(diagramRuleEClass, DIAGRAM_RULE__BASE_PACKAGE);
        createEAttribute(diagramRuleEClass, DIAGRAM_RULE__PACKAGE_DEPTH);
        createEAttribute(diagramRuleEClass, DIAGRAM_RULE__CREATE_YN);

        classDiagramRuleEClass = createEClass(CLASS_DIAGRAM_RULE);

        elementRuleEClass = createEClass(ELEMENT_RULE);
        createEAttribute(elementRuleEClass, ELEMENT_RULE__NAME);
        createEAttribute(elementRuleEClass, ELEMENT_RULE__PREFIX);
        createEAttribute(elementRuleEClass, ELEMENT_RULE__POSTFIX);
        createEAttribute(elementRuleEClass, ELEMENT_RULE__CREATE_YN);
        createEAttribute(elementRuleEClass, ELEMENT_RULE__STEREOTYPE);

        sequenceDiagramRuleEClass = createEClass(SEQUENCE_DIAGRAM_RULE);
        createEAttribute(sequenceDiagramRuleEClass, SEQUENCE_DIAGRAM_RULE__CREATE_VOPCYN);
        createEAttribute(sequenceDiagramRuleEClass, SEQUENCE_DIAGRAM_RULE__MESSAGE_CALL_DEPTH);

        // Create enums
        sourceProjectTypeEEnum = createEEnum(SOURCE_PROJECT_TYPE);
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
        classRuleEClass.getESuperTypes().add(this.getElementRule());
        classDiagramRuleEClass.getESuperTypes().add(this.getDiagramRule());
        sequenceDiagramRuleEClass.getESuperTypes().add(this.getDiagramRule());

        // Initialize classes and features; add operations and parameters
        initEClass(mdaReverseRuleSetEClass, MDAReverseRuleSet.class, "MDAReverseRuleSet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getMDAReverseRuleSet_SourceProjectType(), this.getSourceProjectType(), "sourceProjectType", "", 1, 1, MDAReverseRuleSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getMDAReverseRuleSet_SourceLanguage(), ecorePackage.getEString(), "sourceLanguage", null, 1, 1, MDAReverseRuleSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getMDAReverseRuleSet_SourceProjects(), ecorePackage.getEString(), "sourceProjects", null, 1, -1, MDAReverseRuleSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getMDAReverseRuleSet_TargetModelURI(), ecorePackage.getEString(), "targetModelURI", null, 1, 1, MDAReverseRuleSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getMDAReverseRuleSet_MergeType(), ecorePackage.getEString(), "mergeType", null, 1, 1, MDAReverseRuleSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getMDAReverseRuleSet_ReferenceModelChange(), ecorePackage.getEBoolean(), "referenceModelChange", "false", 1, 1, MDAReverseRuleSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getMDAReverseRuleSet_ProfileLocation(), ecorePackage.getEString(), "profileLocation", null, 0, 1, MDAReverseRuleSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getMDAReverseRuleSet_ClassRules(), this.getClassRule(), null, "classRules", null, 0, -1, MDAReverseRuleSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getMDAReverseRuleSet_ClassDiagramRule(), this.getClassDiagramRule(), null, "classDiagramRule", null, 1, 1, MDAReverseRuleSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getMDAReverseRuleSet_SequenceDiagramRule(), this.getSequenceDiagramRule(), null, "sequenceDiagramRule", null, 1, 1, MDAReverseRuleSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(classRuleEClass, ClassRule.class, "ClassRule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getClassRule_PackageName(), ecorePackage.getEString(), "packageName", null, 0, 1, ClassRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getClassRule_GenerateGetterSetter(), ecorePackage.getEBoolean(), "generateGetterSetter", null, 1, 1, ClassRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getClassRule_GenerateConstructor(), ecorePackage.getEBoolean(), "generateConstructor", null, 1, 1, ClassRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getClassRule_GenerateDestructor(), ecorePackage.getEBoolean(), "generateDestructor", null, 1, 1, ClassRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getClassRule_OperationRules(), this.getElementRule(), null, "operationRules", null, 0, -1, ClassRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getClassRule_AttributeRules(), this.getElementRule(), null, "attributeRules", null, 0, -1, ClassRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(diagramRuleEClass, DiagramRule.class, "DiagramRule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDiagramRule_BasePackage(), ecorePackage.getEString(), "basePackage", "", 1, 1, DiagramRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDiagramRule_PackageDepth(), ecorePackage.getEInt(), "packageDepth", "1", 1, 1, DiagramRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDiagramRule_CreateYN(), ecorePackage.getEBoolean(), "createYN", "false", 1, 1, DiagramRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(classDiagramRuleEClass, ClassDiagramRule.class, "ClassDiagramRule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(elementRuleEClass, ElementRule.class, "ElementRule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getElementRule_Name(), ecorePackage.getEString(), "name", null, 1, 1, ElementRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getElementRule_Prefix(), ecorePackage.getEString(), "prefix", null, 0, 1, ElementRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getElementRule_Postfix(), ecorePackage.getEString(), "postfix", null, 0, 1, ElementRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getElementRule_CreateYN(), ecorePackage.getEBoolean(), "createYN", "true", 1, 1, ElementRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getElementRule_Stereotype(), ecorePackage.getEString(), "stereotype", null, 0, 1, ElementRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(sequenceDiagramRuleEClass, SequenceDiagramRule.class, "SequenceDiagramRule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getSequenceDiagramRule_CreateVOPCYN(), ecorePackage.getEBoolean(), "createVOPCYN", "false", 1, 1, SequenceDiagramRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSequenceDiagramRule_MessageCallDepth(), ecorePackage.getEInt(), "messageCallDepth", "1", 1, 1, SequenceDiagramRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(sourceProjectTypeEEnum, SourceProjectType.class, "SourceProjectType");
        addEEnumLiteral(sourceProjectTypeEEnum, SourceProjectType.JAVA);
        addEEnumLiteral(sourceProjectTypeEEnum, SourceProjectType.EGOV);
        addEEnumLiteral(sourceProjectTypeEEnum, SourceProjectType.NEXCOREJ2EE);
        addEEnumLiteral(sourceProjectTypeEEnum, SourceProjectType.NEXCOREC);

        // Create resource
        createResource(eNS_URI);
    }

} //ReverseTransformationPackageImpl
