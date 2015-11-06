/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.model.umlglossary;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Glossary</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link nexcore.tool.uml.model.umlglossary.Glossary#getWords <em>Words
 * </em>}</li>
 * <li>{@link nexcore.tool.uml.model.umlglossary.Glossary#getCategories <em>
 * Categories</em>}</li>
 * </ul>
 * </p>
 * 
 * @see nexcore.tool.uml.model.umlglossary.UMLGlossaryPackage#getGlossary()
 * @model
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.umlglossary</li>
 * <li>설  명 : Glossary</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public interface Glossary extends ModelElement {
    /**
     * Returns the value of the '<em><b>Words</b></em>' containment reference
     * list. The list contents are of type
     * {@link nexcore.tool.uml.model.umlglossary.Word}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Words</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Words</em>' containment reference list.
     * @see nexcore.tool.uml.model.umlglossary.UMLGlossaryPackage#getGlossary_Words()
     * @model containment="true"
     * @generated
     */
    EList<Word> getWords();

    /**
     * Returns the value of the '<em><b>Categories</b></em>' containment
     * reference list. The list contents are of type
     * {@link nexcore.tool.uml.model.umlglossary.Category}. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Categories</em>' containment reference list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Categories</em>' containment reference
     *         list.
     * @see nexcore.tool.uml.model.umlglossary.UMLGlossaryPackage#getGlossary_Categories()
     * @model containment="true"
     * @generated
     */
    EList<Category> getCategories();

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

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model
     * @generated
     */
    void add(Category data);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model
     * @generated
     */
    void remove(Category data);

} // Glossary
