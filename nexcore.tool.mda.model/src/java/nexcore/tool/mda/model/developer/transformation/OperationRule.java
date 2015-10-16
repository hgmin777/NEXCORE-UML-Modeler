/*
 * Copyright (c) 2010 SK C&C Co., Ltd. All rights reserved.
 * 
 * This software is the confidential and proprietary information of SK C&C. You
 * shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK
 * C&C.
 */

package nexcore.tool.mda.model.developer.transformation;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Operation Rule</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.OperationRule#getName
 * <em>Name</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.OperationRule#getStereotype
 * <em>Stereotype</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.OperationRule#getTemplate
 * <em>Template</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.OperationRule#getPrefix
 * <em>Prefix</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.OperationRule#getPostfix
 * <em>Postfix</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.OperationRule#getVersion
 * <em>Version</em>}</li>
 * </ul>
 * </p>
 * 
 * @see nexcore.tool.mda.model.developer.transformation.TransformationPackage#getOperationRule()
 * @model
 * @generated
 */
public interface OperationRule extends EObject {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "Copyright (c) 2010 SK C&C Co., Ltd. All rights reserved.\r\n\r\nThis software is the confidential and proprietary information of SK C&C.\r\nYou shall not disclose such confidential information and shall use it\r\nonly in accordance with the terms of the license agreement you entered into\r\nwith SK C&C.";

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
     * @see nexcore.tool.mda.model.developer.transformation.TransformationPackage#getOperationRule_Name()
     * @model required="true"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.developer.transformation.OperationRule#getName
     * <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Stereotype</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Stereotype</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Stereotype</em>' attribute.
     * @see #setStereotype(String)
     * @see nexcore.tool.mda.model.developer.transformation.TransformationPackage#getOperationRule_Stereotype()
     * @model required="true"
     * @generated
     */
    String getStereotype();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.developer.transformation.OperationRule#getStereotype
     * <em>Stereotype</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Stereotype</em>' attribute.
     * @see #getStereotype()
     * @generated
     */
    void setStereotype(String value);

    /**
     * Returns the value of the '<em><b>Template</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Template</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Template</em>' attribute.
     * @see #setTemplate(String)
     * @see nexcore.tool.mda.model.developer.transformation.TransformationPackage#getOperationRule_Template()
     * @model required="true"
     * @generated
     */
    String getTemplate();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.developer.transformation.OperationRule#getTemplate
     * <em>Template</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Template</em>' attribute.
     * @see #getTemplate()
     * @generated
     */
    void setTemplate(String value);

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
     * @see nexcore.tool.mda.model.developer.transformation.TransformationPackage#getOperationRule_Prefix()
     * @model required="true"
     * @generated
     */
    String getPrefix();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.developer.transformation.OperationRule#getPrefix
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
     * @see nexcore.tool.mda.model.developer.transformation.TransformationPackage#getOperationRule_Postfix()
     * @model required="true"
     * @generated
     */
    String getPostfix();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.developer.transformation.OperationRule#getPostfix
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
     * Returns the value of the '<em><b>Version</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Version</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Version</em>' attribute.
     * @see #setVersion(String)
     * @see nexcore.tool.mda.model.developer.transformation.TransformationPackage#getOperationRule_Version()
     * @model required="true"
     * @generated
     */
    String getVersion();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.developer.transformation.OperationRule#getVersion
     * <em>Version</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Version</em>' attribute.
     * @see #getVersion()
     * @generated
     */
    void setVersion(String value);

} // OperationRule
