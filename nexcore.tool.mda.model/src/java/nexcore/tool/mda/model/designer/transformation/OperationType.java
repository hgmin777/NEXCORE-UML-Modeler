/*
 * Copyright (c) 2010 SK C&C Co., Ltd. All rights reserved.
 * 
 * This software is the confidential and proprietary information of SK C&C. You
 * shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK
 * C&C.
 */

package nexcore.tool.mda.model.designer.transformation;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Operation Type</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.OperationType#getOperationApplicationType
 * <em>Operation Application Type</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.OperationType#getAssignableOperationName
 * <em>Assignable Operation Name</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.OperationType#getUserDefinedOperationName
 * <em>User Defined Operation Name</em>}</li>
 * </ul>
 * </p>
 * 
 * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getOperationType()
 * @model
 * @generated
 */
public interface OperationType extends EObject {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "Copyright (c) 2010 SK C&C Co., Ltd. All rights reserved.\r\n\r\nThis software is the confidential and proprietary information of SK C&C.\r\nYou shall not disclose such confidential information and shall use it\r\nonly in accordance with the terms of the license agreement you entered into\r\nwith SK C&C.";

    /**
     * Returns the value of the '<em><b>Operation Application Type</b></em>'
     * attribute. The literals are from the enumeration
     * {@link nexcore.tool.mda.model.designer.transformation.OperationApplicationType}
     * . <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Operation Application Type</em>' attribute
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Operation Application Type</em>' attribute.
     * @see nexcore.tool.mda.model.designer.transformation.OperationApplicationType
     * @see #setOperationApplicationType(OperationApplicationType)
     * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getOperationType_OperationApplicationType()
     * @model required="true"
     * @generated
     */
    OperationApplicationType getOperationApplicationType();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.designer.transformation.OperationType#getOperationApplicationType
     * <em>Operation Application Type</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Operation Application Type</em>'
     *            attribute.
     * @see nexcore.tool.mda.model.designer.transformation.OperationApplicationType
     * @see #getOperationApplicationType()
     * @generated
     */
    void setOperationApplicationType(OperationApplicationType value);

    /**
     * Returns the value of the '<em><b>Assignable Operation Name</b></em>'
     * containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Assignable Operation Name</em>' containment
     * reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Assignable Operation Name</em>' containment
     *         reference.
     * @see #setAssignableOperationName(NameType)
     * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getOperationType_AssignableOperationName()
     * @model containment="true"
     * @generated
     */
    NameType getAssignableOperationName();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.designer.transformation.OperationType#getAssignableOperationName
     * <em>Assignable Operation Name</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Assignable Operation Name</em>'
     *            containment reference.
     * @see #getAssignableOperationName()
     * @generated
     */
    void setAssignableOperationName(NameType value);

    /**
     * Returns the value of the '<em><b>User Defined Operation Name</b></em>'
     * attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>User Defined Operation Name</em>' attribute
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>User Defined Operation Name</em>'
     *         attribute.
     * @see #setUserDefinedOperationName(String)
     * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getOperationType_UserDefinedOperationName()
     * @model
     * @generated
     */
    String getUserDefinedOperationName();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.designer.transformation.OperationType#getUserDefinedOperationName
     * <em>User Defined Operation Name</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>User Defined Operation Name</em>'
     *            attribute.
     * @see #getUserDefinedOperationName()
     * @generated
     */
    void setUserDefinedOperationName(String value);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model
     * @generated
     */
    String toString();

} // OperationType
