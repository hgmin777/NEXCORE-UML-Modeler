/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.mda.model.designer.transformation;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Parent Type</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.ParentType#getTypeName
 * <em>Type Name</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.ParentType#getExtensionType
 * <em>Extension Type</em>}</li>
 * </ul>
 * </p>
 * 
 * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getParentType()
 * @model
 * @generated
 */
public interface ParentType extends EObject {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "";

    /**
     * Returns the value of the '<em><b>Type Name</b></em>' attribute list. The
     * list contents are of type {@link java.lang.String}. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Type Name</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Type Name</em>' attribute list.
     * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getParentType_TypeName()
     * @model required="true"
     * @generated
     */
    EList<String> getTypeName();

    /**
     * Returns the value of the '<em><b>Extension Type</b></em>' attribute. The
     * literals are from the enumeration
     * {@link nexcore.tool.mda.model.designer.transformation.ExtensionType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Extension Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Extension Type</em>' attribute.
     * @see nexcore.tool.mda.model.designer.transformation.ExtensionType
     * @see #setExtensionType(ExtensionType)
     * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getParentType_ExtensionType()
     * @model required="true"
     * @generated
     */
    ExtensionType getExtensionType();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.designer.transformation.ParentType#getExtensionType
     * <em>Extension Type</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Extension Type</em>' attribute.
     * @see nexcore.tool.mda.model.designer.transformation.ExtensionType
     * @see #getExtensionType()
     * @generated
     */
    void setExtensionType(ExtensionType value);

} // ParentType
