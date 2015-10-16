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
 * <em><b>Source Relation Type</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.SourceRelationType#getSource
 * <em>Source</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.SourceRelationType#getTarget
 * <em>Target</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.SourceRelationType#isSelfRelation
 * <em>Self Relation</em>}</li>
 * </ul>
 * </p>
 * 
 * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getSourceRelationType()
 * @model
 * @generated
 */
public interface SourceRelationType extends EObject {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "Copyright (c) 2010 SK C&C Co., Ltd. All rights reserved.\r\n\r\nThis software is the confidential and proprietary information of SK C&C.\r\nYou shall not disclose such confidential information and shall use it\r\nonly in accordance with the terms of the license agreement you entered into\r\nwith SK C&C.";

    /**
     * Returns the value of the '<em><b>Source</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Source</em>' containment reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Source</em>' containment reference.
     * @see #setSource(SourceType)
     * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getSourceRelationType_Source()
     * @model containment="true" required="true"
     * @generated
     */
    SourceType getSource();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.designer.transformation.SourceRelationType#getSource
     * <em>Source</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Source</em>' containment reference.
     * @see #getSource()
     * @generated
     */
    void setSource(SourceType value);

    /**
     * Returns the value of the '<em><b>Target</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target</em>' containment reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Target</em>' containment reference.
     * @see #setTarget(SourceType)
     * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getSourceRelationType_Target()
     * @model containment="true" required="true"
     * @generated
     */
    SourceType getTarget();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.designer.transformation.SourceRelationType#getTarget
     * <em>Target</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Target</em>' containment reference.
     * @see #getTarget()
     * @generated
     */
    void setTarget(SourceType value);

    /**
     * Returns the value of the '<em><b>Self Relation</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Self Relation</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Self Relation</em>' attribute.
     * @see #setSelfRelation(boolean)
     * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getSourceRelationType_SelfRelation()
     * @model required="true"
     * @generated
     */
    boolean isSelfRelation();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.designer.transformation.SourceRelationType#isSelfRelation
     * <em>Self Relation</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Self Relation</em>' attribute.
     * @see #isSelfRelation()
     * @generated
     */
    void setSelfRelation(boolean value);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model
     * @generated
     */
    String toString();

} // SourceRelationType
