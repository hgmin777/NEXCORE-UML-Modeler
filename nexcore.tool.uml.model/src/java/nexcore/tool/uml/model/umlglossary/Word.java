/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.model.umlglossary;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Word</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link nexcore.tool.uml.model.umlglossary.Word#getSection <em>Section
 * </em>}</li>
 * <li>{@link nexcore.tool.uml.model.umlglossary.Word#getCategory <em>Category
 * </em>}</li>
 * <li>{@link nexcore.tool.uml.model.umlglossary.Word#getKoreanName <em>Korean
 * Name</em>}</li>
 * <li>{@link nexcore.tool.uml.model.umlglossary.Word#getEnglishFullName <em>
 * English Full Name</em>}</li>
 * <li>{@link nexcore.tool.uml.model.umlglossary.Word#getAbbreviation <em>
 * Abbreviation</em>}</li>
 * <li>{@link nexcore.tool.uml.model.umlglossary.Word#getDescription <em>
 * Description</em>}</li>
 * <li>{@link nexcore.tool.uml.model.umlglossary.Word#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 * 
 * @see nexcore.tool.uml.model.umlglossary.UMLGlossaryPackage#getWord()
 * @model
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.umlglossary</li>
 * <li>설  명 : Word</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public interface Word extends EObject {
    /**
     * Returns the value of the '<em><b>Section</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Section</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Section</em>' attribute.
     * @see #setSection(String)
     * @see nexcore.tool.uml.model.umlglossary.UMLGlossaryPackage#getWord_Section()
     * @model
     * @generated
     */
    String getSection();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.uml.model.umlglossary.Word#getSection
     * <em>Section</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Section</em>' attribute.
     * @see #getSection()
     * @generated
     */
    void setSection(String value);

    /**
     * Returns the value of the '<em><b>Korean Name</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Korean Name</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Korean Name</em>' attribute.
     * @see #setKoreanName(String)
     * @see nexcore.tool.uml.model.umlglossary.UMLGlossaryPackage#getWord_KoreanName()
     * @model
     * @generated
     */
    String getKoreanName();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.uml.model.umlglossary.Word#getKoreanName
     * <em>Korean Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Korean Name</em>' attribute.
     * @see #getKoreanName()
     * @generated
     */
    void setKoreanName(String value);

    /**
     * Returns the value of the '<em><b>English Full Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>English Full Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>English Full Name</em>' attribute.
     * @see #setEnglishFullName(String)
     * @see nexcore.tool.uml.model.umlglossary.UMLGlossaryPackage#getWord_EnglishFullName()
     * @model
     * @generated
     */
    String getEnglishFullName();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.uml.model.umlglossary.Word#getEnglishFullName
     * <em>English Full Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>English Full Name</em>' attribute.
     * @see #getEnglishFullName()
     * @generated
     */
    void setEnglishFullName(String value);

    /**
     * Returns the value of the '<em><b>Abbreviation</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Abbreviation</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Abbreviation</em>' attribute.
     * @see #setAbbreviation(String)
     * @see nexcore.tool.uml.model.umlglossary.UMLGlossaryPackage#getWord_Abbreviation()
     * @model
     * @generated
     */
    String getAbbreviation();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.uml.model.umlglossary.Word#getAbbreviation
     * <em>Abbreviation</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Abbreviation</em>' attribute.
     * @see #getAbbreviation()
     * @generated
     */
    void setAbbreviation(String value);

    /**
     * Returns the value of the '<em><b>Description</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Description</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Description</em>' attribute.
     * @see #setDescription(String)
     * @see nexcore.tool.uml.model.umlglossary.UMLGlossaryPackage#getWord_Description()
     * @model
     * @generated
     */
    String getDescription();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.uml.model.umlglossary.Word#getDescription
     * <em>Description</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Description</em>' attribute.
     * @see #getDescription()
     * @generated
     */
    void setDescription(String value);

    /**
     * Returns the value of the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Id</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Id</em>' attribute.
     * @see #setId(String)
     * @see nexcore.tool.uml.model.umlglossary.UMLGlossaryPackage#getWord_Id()
     * @model id="true"
     * @generated
     */
    String getId();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.uml.model.umlglossary.Word#getId <em>Id</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(String value);

    /**
     * Returns the value of the '<em><b>Category</b></em>' reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Category</em>' containment reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Category</em>' reference.
     * @see #setCategory(Category)
     * @see nexcore.tool.uml.model.umlglossary.UMLGlossaryPackage#getWord_Category()
     * @model
     * @generated
     */
    Category getCategory();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.uml.model.umlglossary.Word#getCategory
     * <em>Category</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Category</em>' reference.
     * @see #getCategory()
     * @generated
     */
    void setCategory(Category value);

} // Word
