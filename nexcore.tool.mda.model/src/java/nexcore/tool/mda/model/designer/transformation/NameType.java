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
 * <em><b>Name Type</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link nexcore.tool.mda.model.designer.transformation.NameType#getPrefix
 * <em>Prefix</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.NameType#getNamePart
 * <em>Name Part</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.NameType#getPostfix
 * <em>Postfix</em>}</li>
 * </ul>
 * </p>
 * 
 * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getNameType()
 * @model
 * @generated
 */
public interface NameType extends EObject {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "";

    /**
     * Returns the value of the '<em><b>Prefix</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Prefix</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Prefix</em>' attribute.
     * @see #setPrefix(String)
     * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getNameType_Prefix()
     * @model
     * @generated
     */
    String getPrefix();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.designer.transformation.NameType#getPrefix
     * <em>Prefix</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Prefix</em>' attribute.
     * @see #getPrefix()
     * @generated
     */
    void setPrefix(String value);

    /**
     * Returns the value of the '<em><b>Name Part</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name Part</em>' containment reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Name Part</em>' containment reference.
     * @see #setNamePart(NamePartType)
     * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getNameType_NamePart()
     * @model containment="true" required="true"
     * @generated
     */
    NamePartType getNamePart();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.designer.transformation.NameType#getNamePart
     * <em>Name Part</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Name Part</em>' containment
     *            reference.
     * @see #getNamePart()
     * @generated
     */
    void setNamePart(NamePartType value);

    /**
     * Returns the value of the '<em><b>Postfix</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Postfix</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Postfix</em>' attribute.
     * @see #setPostfix(String)
     * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getNameType_Postfix()
     * @model
     * @generated
     */
    String getPostfix();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.designer.transformation.NameType#getPostfix
     * <em>Postfix</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Postfix</em>' attribute.
     * @see #getPostfix()
     * @generated
     */
    void setPostfix(String value);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model
     * @generated
     */
    String toString();

} // NameType
