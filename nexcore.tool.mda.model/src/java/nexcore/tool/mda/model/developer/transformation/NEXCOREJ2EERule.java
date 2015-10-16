/**
 * Copyright (c) 2010 SK C&C Co., Ltd. All rights reserved.
 * 
 * This software is the confidential and proprietary information of SK C&C. You
 * shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK
 * C&C.
 * 
 * $Id$
 */
package nexcore.tool.mda.model.developer.transformation;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>NEXCOREJ2EE Rule</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.NEXCOREJ2EERule#getAccessType
 * <em>Access Type</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.NEXCOREJ2EERule#getPackageName
 * <em>Package Name</em>}</li>
 * </ul>
 * </p>
 * 
 * @see nexcore.tool.mda.model.developer.transformation.TransformationPackage#getNEXCOREJ2EERule()
 * @model
 * @generated
 */
public interface NEXCOREJ2EERule extends EObject {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "Copyright (c) 2010 SK C&C Co., Ltd. All rights reserved.\r\n\r\nThis software is the confidential and proprietary information of SK C&C.\r\nYou shall not disclose such confidential information and shall use it\r\nonly in accordance with the terms of the license agreement you entered into\r\nwith SK C&C.";

    /**
     * Returns the value of the '<em><b>Access Type</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Access Type</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Access Type</em>' attribute.
     * @see #setAccessType(String)
     * @see nexcore.tool.mda.model.developer.transformation.TransformationPackage#getNEXCOREJ2EERule_AccessType()
     * @model required="true"
     * @generated
     */
    String getAccessType();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.developer.transformation.NEXCOREJ2EERule#getAccessType
     * <em>Access Type</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Access Type</em>' attribute.
     * @see #getAccessType()
     * @generated
     */
    void setAccessType(String value);

    /**
     * Returns the value of the '<em><b>Package Name</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Package Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Package Name</em>' attribute.
     * @see #setPackageName(String)
     * @see nexcore.tool.mda.model.developer.transformation.TransformationPackage#getNEXCOREJ2EERule_PackageName()
     * @model required="true"
     * @generated
     */
    String getPackageName();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.developer.transformation.NEXCOREJ2EERule#getPackageName
     * <em>Package Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Package Name</em>' attribute.
     * @see #getPackageName()
     * @generated
     */
    void setPackageName(String value);

} // NEXCOREJ2EERule
