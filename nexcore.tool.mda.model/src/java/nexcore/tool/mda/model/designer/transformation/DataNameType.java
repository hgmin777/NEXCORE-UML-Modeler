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
 * <em><b>Data Name Type</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.DataNameType#getDataName
 * <em>Data Name</em>}</li>
 * </ul>
 * </p>
 * 
 * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getDataNameType()
 * @model
 * @generated
 */
public interface DataNameType extends EObject {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "";

    /**
     * Returns the value of the '<em><b>Data Name</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Data Name</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Data Name</em>' attribute.
     * @see #setDataName(String)
     * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getDataNameType_DataName()
     * @model required="true"
     * @generated
     */
    String getDataName();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.designer.transformation.DataNameType#getDataName
     * <em>Data Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Data Name</em>' attribute.
     * @see #getDataName()
     * @generated
     */
    void setDataName(String value);

} // DataNameType
