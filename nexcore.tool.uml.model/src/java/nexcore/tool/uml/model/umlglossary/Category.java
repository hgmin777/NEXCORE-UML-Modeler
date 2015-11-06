/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.model.umlglossary;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Category</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link nexcore.tool.uml.model.umlglossary.Category#getName <em>Name</em>}
 * </li>
 * <li>{@link nexcore.tool.uml.model.umlglossary.Category#getDescription <em>
 * Description</em>}</li>
 * <li>{@link nexcore.tool.uml.model.umlglossary.Category#getWords <em>Words
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @see nexcore.tool.uml.model.umlglossary.UMLGlossaryPackage#getCategory()
 * @model
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.umlglossary</li>
 * <li>설  명 : Category</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public interface Category extends EObject {

    /**
     * NONE_CATEGORY
     */
    public static String NONE_CATEGORY = "<NONE>";

    /**
     * Returns the value of the '<em><b>Words</b></em>' reference list. The list
     * contents are of type {@link nexcore.tool.uml.model.umlglossary.Word}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Words</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Words</em>' reference list.
     * @see nexcore.tool.uml.model.umlglossary.UMLGlossaryPackage#getCategory_Words()
     * @model
     * @generated
     */
    EList<Word> getWords();

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see nexcore.tool.uml.model.umlglossary.UMLGlossaryPackage#getCategory_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.uml.model.umlglossary.Category#getName <em>Name</em>}
     * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

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
     * @see nexcore.tool.uml.model.umlglossary.UMLGlossaryPackage#getCategory_Description()
     * @model
     * @generated
     */
    String getDescription();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.uml.model.umlglossary.Category#getDescription
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
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model dataRequired="true"
     * @generated
     */
    void add(Word data);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model dataRequired="true"
     * @generated
     */
    void remove(Word data);

} // Category
