/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.model.umlglossary;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains
 * accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * 
 * @see nexcore.tool.uml.model.umlglossary.UMLGlossaryFactory
 * @model kind="package"
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.umlglossary</li>
 * <li>설  명 : UMLGlossaryPackage</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public interface UMLGlossaryPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNAME = "umlglossary";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_URI = "http://nexcore.skcc.com/tools/uml/umlglossary";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_PREFIX = "umg";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    UMLGlossaryPackage eINSTANCE = nexcore.tool.uml.model.umlglossary.impl.UMLGlossaryPackageImpl.init();

    /**
     * The meta object id for the '
     * {@link nexcore.tool.uml.model.umlglossary.impl.WordImpl <em>Word</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see nexcore.tool.uml.model.umlglossary.impl.WordImpl
     * @see nexcore.tool.uml.model.umlglossary.impl.UMLGlossaryPackageImpl#getWord()
     * @generated
     */
    int WORD = 0;

    /**
     * The feature id for the '<em><b>Section</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORD__SECTION = 0;

    /**
     * The feature id for the '<em><b>Category</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORD__CATEGORY = 1;

    /**
     * The feature id for the '<em><b>Korean Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORD__KOREAN_NAME = 2;

    /**
     * The feature id for the '<em><b>English Full Name</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORD__ENGLISH_FULL_NAME = 3;

    /**
     * The feature id for the '<em><b>Abbreviation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORD__ABBREVIATION = 4;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORD__DESCRIPTION = 5;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORD__ID = 6;

    /**
     * The number of structural features of the '<em>Word</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORD_FEATURE_COUNT = 7;

    /**
     * The meta object id for the '
     * {@link nexcore.tool.uml.model.umlglossary.impl.GlossaryImpl
     * <em>Glossary</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see nexcore.tool.uml.model.umlglossary.impl.GlossaryImpl
     * @see nexcore.tool.uml.model.umlglossary.impl.UMLGlossaryPackageImpl#getGlossary()
     * @generated
     */
    int GLOSSARY = 1;

    /**
     * The meta object id for the '
     * {@link nexcore.tool.uml.model.umlglossary.impl.CategoryImpl
     * <em>Category</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see nexcore.tool.uml.model.umlglossary.impl.CategoryImpl
     * @see nexcore.tool.uml.model.umlglossary.impl.UMLGlossaryPackageImpl#getCategory()
     * @generated
     */
    int CATEGORY = 3;

    /**
     * The meta object id for the '
     * {@link nexcore.tool.uml.model.umlglossary.impl.ModelElementImpl
     * <em>Model Element</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see nexcore.tool.uml.model.umlglossary.impl.ModelElementImpl
     * @see nexcore.tool.uml.model.umlglossary.impl.UMLGlossaryPackageImpl#getModelElement()
     * @generated
     */
    int MODEL_ELEMENT = 2;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MODEL_ELEMENT__EANNOTATIONS = EcorePackage.ENAMED_ELEMENT__EANNOTATIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MODEL_ELEMENT__NAME = EcorePackage.ENAMED_ELEMENT__NAME;

    /**
     * The number of structural features of the '<em>Model Element</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MODEL_ELEMENT_FEATURE_COUNT = EcorePackage.ENAMED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GLOSSARY__EANNOTATIONS = MODEL_ELEMENT__EANNOTATIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GLOSSARY__NAME = MODEL_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Words</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GLOSSARY__WORDS = MODEL_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Categories</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GLOSSARY__CATEGORIES = MODEL_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Glossary</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GLOSSARY_FEATURE_COUNT = MODEL_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CATEGORY__NAME = 0;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CATEGORY__DESCRIPTION = 1;

    /**
     * The feature id for the '<em><b>Words</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CATEGORY__WORDS = 2;

    /**
     * The number of structural features of the '<em>Category</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CATEGORY_FEATURE_COUNT = 3;

    /**
     * Returns the meta object for class '
     * {@link nexcore.tool.uml.model.umlglossary.Word <em>Word</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Word</em>'.
     * @see nexcore.tool.uml.model.umlglossary.Word
     * @generated
     */
    EClass getWord();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.uml.model.umlglossary.Word#getSection
     * <em>Section</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Section</em>'.
     * @see nexcore.tool.uml.model.umlglossary.Word#getSection()
     * @see #getWord()
     * @generated
     */
    EAttribute getWord_Section();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.uml.model.umlglossary.Word#getKoreanName
     * <em>Korean Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Korean Name</em>'.
     * @see nexcore.tool.uml.model.umlglossary.Word#getKoreanName()
     * @see #getWord()
     * @generated
     */
    EAttribute getWord_KoreanName();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.uml.model.umlglossary.Word#getEnglishFullName
     * <em>English Full Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>English Full Name</em>'.
     * @see nexcore.tool.uml.model.umlglossary.Word#getEnglishFullName()
     * @see #getWord()
     * @generated
     */
    EAttribute getWord_EnglishFullName();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.uml.model.umlglossary.Word#getAbbreviation
     * <em>Abbreviation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Abbreviation</em>'.
     * @see nexcore.tool.uml.model.umlglossary.Word#getAbbreviation()
     * @see #getWord()
     * @generated
     */
    EAttribute getWord_Abbreviation();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.uml.model.umlglossary.Word#getDescription
     * <em>Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Description</em>'.
     * @see nexcore.tool.uml.model.umlglossary.Word#getDescription()
     * @see #getWord()
     * @generated
     */
    EAttribute getWord_Description();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.uml.model.umlglossary.Word#getId <em>Id</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see nexcore.tool.uml.model.umlglossary.Word#getId()
     * @see #getWord()
     * @generated
     */
    EAttribute getWord_Id();

    /**
     * Returns the meta object for the reference '
     * {@link nexcore.tool.uml.model.umlglossary.Word#getCategory
     * <em>Category</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Category</em>'.
     * @see nexcore.tool.uml.model.umlglossary.Word#getCategory()
     * @see #getWord()
     * @generated
     */
    EReference getWord_Category();

    /**
     * Returns the meta object for class '
     * {@link nexcore.tool.uml.model.umlglossary.Glossary <em>Glossary</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Glossary</em>'.
     * @see nexcore.tool.uml.model.umlglossary.Glossary
     * @generated
     */
    EClass getGlossary();

    /**
     * Returns the meta object for the containment reference list '
     * {@link nexcore.tool.uml.model.umlglossary.Glossary#getWords
     * <em>Words</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Words</em>'.
     * @see nexcore.tool.uml.model.umlglossary.Glossary#getWords()
     * @see #getGlossary()
     * @generated
     */
    EReference getGlossary_Words();

    /**
     * Returns the meta object for the containment reference list '
     * {@link nexcore.tool.uml.model.umlglossary.Glossary#getCategories
     * <em>Categories</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Categories</em>'.
     * @see nexcore.tool.uml.model.umlglossary.Glossary#getCategories()
     * @see #getGlossary()
     * @generated
     */
    EReference getGlossary_Categories();

    /**
     * Returns the meta object for class '
     * {@link nexcore.tool.uml.model.umlglossary.ModelElement
     * <em>Model Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Model Element</em>'.
     * @see nexcore.tool.uml.model.umlglossary.ModelElement
     * @generated
     */
    EClass getModelElement();

    /**
     * Returns the meta object for class '
     * {@link nexcore.tool.uml.model.umlglossary.Category <em>Category</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Category</em>'.
     * @see nexcore.tool.uml.model.umlglossary.Category
     * @generated
     */
    EClass getCategory();

    /**
     * Returns the meta object for the reference list '
     * {@link nexcore.tool.uml.model.umlglossary.Category#getWords
     * <em>Words</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Words</em>'.
     * @see nexcore.tool.uml.model.umlglossary.Category#getWords()
     * @see #getCategory()
     * @generated
     */
    EReference getCategory_Words();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.uml.model.umlglossary.Category#getName <em>Name</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see nexcore.tool.uml.model.umlglossary.Category#getName()
     * @see #getCategory()
     * @generated
     */
    EAttribute getCategory_Name();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.uml.model.umlglossary.Category#getDescription
     * <em>Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Description</em>'.
     * @see nexcore.tool.uml.model.umlglossary.Category#getDescription()
     * @see #getCategory()
     * @generated
     */
    EAttribute getCategory_Description();

    /**
     * Returns the factory that creates the instances of the model. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the factory that creates the instances of the model.
     * @generated
     */
    UMLGlossaryFactory getUMLGlossaryFactory();

    /**
     * <!-- begin-user-doc --> Defines literals for the meta objects that
     * represent
     * <ul>
     * <li>each class,</li>
     * <li>each feature of each class,</li>
     * <li>each enum,</li>
     * <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '
         * {@link nexcore.tool.uml.model.umlglossary.impl.WordImpl
         * <em>Word</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see nexcore.tool.uml.model.umlglossary.impl.WordImpl
         * @see nexcore.tool.uml.model.umlglossary.impl.UMLGlossaryPackageImpl#getWord()
         * @generated
         */
        EClass WORD = eINSTANCE.getWord();

        /**
         * The meta object literal for the '<em><b>Section</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute WORD__SECTION = eINSTANCE.getWord_Section();

        /**
         * The meta object literal for the '<em><b>Korean Name</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute WORD__KOREAN_NAME = eINSTANCE.getWord_KoreanName();

        /**
         * The meta object literal for the '<em><b>English Full Name</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute WORD__ENGLISH_FULL_NAME = eINSTANCE.getWord_EnglishFullName();

        /**
         * The meta object literal for the '<em><b>Abbreviation</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute WORD__ABBREVIATION = eINSTANCE.getWord_Abbreviation();

        /**
         * The meta object literal for the '<em><b>Description</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute WORD__DESCRIPTION = eINSTANCE.getWord_Description();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute WORD__ID = eINSTANCE.getWord_Id();

        /**
         * The meta object literal for the '<em><b>Category</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference WORD__CATEGORY = eINSTANCE.getWord_Category();

        /**
         * The meta object literal for the '
         * {@link nexcore.tool.uml.model.umlglossary.impl.GlossaryImpl
         * <em>Glossary</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @see nexcore.tool.uml.model.umlglossary.impl.GlossaryImpl
         * @see nexcore.tool.uml.model.umlglossary.impl.UMLGlossaryPackageImpl#getGlossary()
         * @generated
         */
        EClass GLOSSARY = eINSTANCE.getGlossary();

        /**
         * The meta object literal for the '<em><b>Words</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference GLOSSARY__WORDS = eINSTANCE.getGlossary_Words();

        /**
         * The meta object literal for the '<em><b>Categories</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference GLOSSARY__CATEGORIES = eINSTANCE.getGlossary_Categories();

        /**
         * The meta object literal for the '
         * {@link nexcore.tool.uml.model.umlglossary.impl.ModelElementImpl
         * <em>Model Element</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see nexcore.tool.uml.model.umlglossary.impl.ModelElementImpl
         * @see nexcore.tool.uml.model.umlglossary.impl.UMLGlossaryPackageImpl#getModelElement()
         * @generated
         */
        EClass MODEL_ELEMENT = eINSTANCE.getModelElement();

        /**
         * The meta object literal for the '
         * {@link nexcore.tool.uml.model.umlglossary.impl.CategoryImpl
         * <em>Category</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @see nexcore.tool.uml.model.umlglossary.impl.CategoryImpl
         * @see nexcore.tool.uml.model.umlglossary.impl.UMLGlossaryPackageImpl#getCategory()
         * @generated
         */
        EClass CATEGORY = eINSTANCE.getCategory();

        /**
         * The meta object literal for the '<em><b>Words</b></em>' reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference CATEGORY__WORDS = eINSTANCE.getCategory_Words();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute CATEGORY__NAME = eINSTANCE.getCategory_Name();

        /**
         * The meta object literal for the '<em><b>Description</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute CATEGORY__DESCRIPTION = eINSTANCE.getCategory_Description();

    }

} // UMLGlossaryPackage
