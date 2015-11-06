/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.mda.model.designer.transformation;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Name Part Type</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.NamePartType#getName
 * <em>Name</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.NamePartType#getCapitalization
 * <em>Capitalization</em>}</li>
 * </ul>
 * </p>
 * 
 * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getNamePartType()
 * @model
 * @generated
 */
public interface NamePartType extends EObject {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "";

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute. The literals
     * are from the enumeration
     * {@link nexcore.tool.mda.model.designer.transformation.NameKeywordType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Name</em>' attribute.
     * @see nexcore.tool.mda.model.designer.transformation.NameKeywordType
     * @see #setName(NameKeywordType)
     * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getNamePartType_Name()
     * @model required="true"
     * @generated
     */
    NameKeywordType getName();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.designer.transformation.NamePartType#getName
     * <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Name</em>' attribute.
     * @see nexcore.tool.mda.model.designer.transformation.NameKeywordType
     * @see #getName()
     * @generated
     */
    void setName(NameKeywordType value);

    /**
     * Returns the value of the '<em><b>Capitalization</b></em>' attribute. The
     * literals are from the enumeration
     * {@link nexcore.tool.mda.model.designer.transformation.CapitalizationType}
     * . <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Capitalization</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Capitalization</em>' attribute.
     * @see nexcore.tool.mda.model.designer.transformation.CapitalizationType
     * @see #setCapitalization(CapitalizationType)
     * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getNamePartType_Capitalization()
     * @model required="true"
     * @generated
     */
    CapitalizationType getCapitalization();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.designer.transformation.NamePartType#getCapitalization
     * <em>Capitalization</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Capitalization</em>' attribute.
     * @see nexcore.tool.mda.model.designer.transformation.CapitalizationType
     * @see #getCapitalization()
     * @generated
     */
    void setCapitalization(CapitalizationType value);

} // NamePartType
