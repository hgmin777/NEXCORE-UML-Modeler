/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.model.umlglossary.impl;

import nexcore.tool.uml.model.umlglossary.Category;
import nexcore.tool.uml.model.umlglossary.Glossary;
import nexcore.tool.uml.model.umlglossary.ModelElement;
import nexcore.tool.uml.model.umlglossary.UMLGlossaryFactory;
import nexcore.tool.uml.model.umlglossary.UMLGlossaryPackage;
import nexcore.tool.uml.model.umlglossary.Word;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
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
 * <li>서브 업무명 : nexcore.tool.uml.model.umlglossary.impl</li>
 * <li>설  명 : UMLGlossaryPackageImpl</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class UMLGlossaryPackageImpl extends EPackageImpl implements UMLGlossaryPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass wordEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass glossaryEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass modelElementEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass categoryEClass = null;

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
     * @see nexcore.tool.uml.model.umlglossary.UMLGlossaryPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private UMLGlossaryPackageImpl() {
        super(eNS_URI, UMLGlossaryFactory.eINSTANCE);
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
    public static UMLGlossaryPackage init() {
        if (isInited)
            return (UMLGlossaryPackage) EPackage.Registry.INSTANCE.getEPackage(UMLGlossaryPackage.eNS_URI);

        // Obtain or create and register package
        UMLGlossaryPackageImpl theUMLGlossaryPackage = (UMLGlossaryPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof UMLGlossaryPackageImpl
        ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new UMLGlossaryPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        EcorePackage.eINSTANCE.eClass();

        // Create package meta-data objects
        theUMLGlossaryPackage.createPackageContents();

        // Initialize created meta-data
        theUMLGlossaryPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theUMLGlossaryPackage.freeze();

        return theUMLGlossaryPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getWord() {
        return wordEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getWord_Section() {
        return (EAttribute) wordEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getWord_KoreanName() {
        return (EAttribute) wordEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getWord_EnglishFullName() {
        return (EAttribute) wordEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getWord_Abbreviation() {
        return (EAttribute) wordEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getWord_Description() {
        return (EAttribute) wordEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getWord_Id() {
        return (EAttribute) wordEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getWord_Category() {
        return (EReference) wordEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getGlossary() {
        return glossaryEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getGlossary_Words() {
        return (EReference) glossaryEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getGlossary_Categories() {
        return (EReference) glossaryEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getModelElement() {
        return modelElementEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getCategory() {
        return categoryEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getCategory_Words() {
        return (EReference) categoryEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getCategory_Name() {
        return (EAttribute) categoryEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getCategory_Description() {
        return (EAttribute) categoryEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public UMLGlossaryFactory getUMLGlossaryFactory() {
        return (UMLGlossaryFactory) getEFactoryInstance();
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
        wordEClass = createEClass(WORD);
        createEAttribute(wordEClass, WORD__SECTION);
        createEReference(wordEClass, WORD__CATEGORY);
        createEAttribute(wordEClass, WORD__KOREAN_NAME);
        createEAttribute(wordEClass, WORD__ENGLISH_FULL_NAME);
        createEAttribute(wordEClass, WORD__ABBREVIATION);
        createEAttribute(wordEClass, WORD__DESCRIPTION);
        createEAttribute(wordEClass, WORD__ID);

        glossaryEClass = createEClass(GLOSSARY);
        createEReference(glossaryEClass, GLOSSARY__WORDS);
        createEReference(glossaryEClass, GLOSSARY__CATEGORIES);

        modelElementEClass = createEClass(MODEL_ELEMENT);

        categoryEClass = createEClass(CATEGORY);
        createEAttribute(categoryEClass, CATEGORY__NAME);
        createEAttribute(categoryEClass, CATEGORY__DESCRIPTION);
        createEReference(categoryEClass, CATEGORY__WORDS);
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
        glossaryEClass.getESuperTypes().add(this.getModelElement());
        modelElementEClass.getESuperTypes().add(theEcorePackage.getENamedElement());

        // Initialize classes and features; add operations and parameters
        initEClass(wordEClass, Word.class, "Word", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getWord_Section(),
            ecorePackage.getEString(),
            "section",
            null,
            0,
            1,
            Word.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEReference(getWord_Category(),
            this.getCategory(),
            null,
            "category",
            null,
            0,
            1,
            Word.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_COMPOSITE,
            IS_RESOLVE_PROXIES,
            !IS_UNSETTABLE,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getWord_KoreanName(),
            ecorePackage.getEString(),
            "koreanName",
            null,
            0,
            1,
            Word.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getWord_EnglishFullName(),
            ecorePackage.getEString(),
            "englishFullName",
            null,
            0,
            1,
            Word.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getWord_Abbreviation(),
            ecorePackage.getEString(),
            "abbreviation",
            null,
            0,
            1,
            Word.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getWord_Description(),
            ecorePackage.getEString(),
            "description",
            null,
            0,
            1,
            Word.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getWord_Id(),
            ecorePackage.getEString(),
            "id",
            null,
            0,
            1,
            Word.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);

        initEClass(glossaryEClass, Glossary.class, "Glossary", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getGlossary_Words(),
            this.getWord(),
            null,
            "words",
            null,
            0,
            -1,
            Glossary.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            IS_COMPOSITE,
            !IS_RESOLVE_PROXIES,
            !IS_UNSETTABLE,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEReference(getGlossary_Categories(),
            this.getCategory(),
            null,
            "categories",
            null,
            0,
            -1,
            Glossary.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            IS_COMPOSITE,
            !IS_RESOLVE_PROXIES,
            !IS_UNSETTABLE,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);

        EOperation op = addEOperation(glossaryEClass, null, "add", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, this.getWord(), "data", 1, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(glossaryEClass, null, "remove", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, this.getWord(), "data", 1, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(glossaryEClass, null, "add", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, this.getCategory(), "data", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(glossaryEClass, null, "remove", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, this.getCategory(), "data", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(modelElementEClass,
            ModelElement.class,
            "ModelElement",
            IS_ABSTRACT,
            !IS_INTERFACE,
            IS_GENERATED_INSTANCE_CLASS);

        op = addEOperation(modelElementEClass,
            theEcorePackage.getEAnnotation(),
            "addEAnnotation",
            0,
            1,
            IS_UNIQUE,
            !IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "source", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(modelElementEClass, null, "addEAnnotationDetail", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEAnnotation(), "eAnnotation", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "key", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "value", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(modelElementEClass,
            ecorePackage.getEString(),
            "getEAnnotationDetail",
            0,
            1,
            IS_UNIQUE,
            IS_ORDERED);
        addEParameter(op, theEcorePackage.getEAnnotation(), "eAnnotation", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "key", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(modelElementEClass, null, "setAnnotationDetail", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEAnnotation(), "eAnnotation", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "key", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "value", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(modelElementEClass, null, "removeEAnnotationDetail", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theEcorePackage.getEAnnotation(), "eAnnotation", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "key", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(modelElementEClass,
            theEcorePackage.getEAnnotation(),
            "getEAnnotation",
            0,
            1,
            IS_UNIQUE,
            IS_ORDERED);
        addEParameter(op, theEcorePackage.getEStructuralFeature(), "source", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(categoryEClass, Category.class, "Category", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getCategory_Name(),
            ecorePackage.getEString(),
            "name",
            null,
            0,
            1,
            Category.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getCategory_Description(),
            theEcorePackage.getEString(),
            "description",
            null,
            0,
            1,
            Category.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEReference(getCategory_Words(),
            this.getWord(),
            null,
            "words",
            null,
            0,
            -1,
            Category.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_COMPOSITE,
            IS_RESOLVE_PROXIES,
            !IS_UNSETTABLE,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);

        op = addEOperation(categoryEClass, null, "add", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, this.getWord(), "data", 1, 1, IS_UNIQUE, IS_ORDERED);

        op = addEOperation(categoryEClass, null, "remove", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, this.getWord(), "data", 1, 1, IS_UNIQUE, IS_ORDERED);

        // Create resource
        createResource(eNS_URI);
    }

} // UMLGlossaryPackageImpl
