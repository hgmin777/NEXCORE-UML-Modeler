/*
 * Copyright (c) 2010 SK C&C Co., Ltd. All rights reserved.
 * 
 * This software is the confidential and proprietary information of SK C&C. You
 * shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK
 * C&C.
 */

package nexcore.tool.mda.model.designer.transformation;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Behavior Transformation Data</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.BehaviorTransformationData#getSourceRelation
 * <em>Source Relation</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.BehaviorTransformationData#getRemark
 * <em>Remark</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.BehaviorTransformationData#isSelf
 * <em>Self</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.BehaviorTransformationData#getBehaviorTransformationDetailDataSet
 * <em>Behavior Transformation Detail Data Set</em>}</li>
 * </ul>
 * </p>
 * 
 * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getBehaviorTransformationData()
 * @model
 * @generated
 */
public interface BehaviorTransformationData extends EObject {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "Copyright (c) 2010 SK C&C Co., Ltd. All rights reserved.\r\n\r\nThis software is the confidential and proprietary information of SK C&C.\r\nYou shall not disclose such confidential information and shall use it\r\nonly in accordance with the terms of the license agreement you entered into\r\nwith SK C&C.";

    /**
     * Returns the value of the '<em><b>Source Relation</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Source Relation</em>' containment reference
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Source Relation</em>' containment
     *         reference.
     * @see #setSourceRelation(SourceRelationType)
     * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getBehaviorTransformationData_SourceRelation()
     * @model containment="true" required="true"
     * @generated
     */
    SourceRelationType getSourceRelation();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.designer.transformation.BehaviorTransformationData#getSourceRelation
     * <em>Source Relation</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Source Relation</em>' containment
     *            reference.
     * @see #getSourceRelation()
     * @generated
     */
    void setSourceRelation(SourceRelationType value);

    /**
     * Returns the value of the '<em><b>Remark</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Remark</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Remark</em>' attribute.
     * @see #setRemark(String)
     * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getBehaviorTransformationData_Remark()
     * @model
     * @generated
     */
    String getRemark();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.designer.transformation.BehaviorTransformationData#getRemark
     * <em>Remark</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Remark</em>' attribute.
     * @see #getRemark()
     * @generated
     */
    void setRemark(String value);

    /**
     * Returns the value of the '<em><b>Self</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Self</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Self</em>' attribute.
     * @see #setSelf(boolean)
     * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getBehaviorTransformationData_Self()
     * @model required="true"
     * @generated
     */
    boolean isSelf();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.designer.transformation.BehaviorTransformationData#isSelf
     * <em>Self</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Self</em>' attribute.
     * @see #isSelf()
     * @generated
     */
    void setSelf(boolean value);

    /**
     * Returns the value of the '
     * <em><b>Behavior Transformation Detail Data Set</b></em>' containment
     * reference list. The list contents are of type
     * {@link nexcore.tool.mda.model.designer.transformation.BehaviorTransformationDetailData}
     * . <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Behavior Transformation Detail Data Set</em>'
     * containment reference list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '
     *         <em>Behavior Transformation Detail Data Set</em>' containment
     *         reference list.
     * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getBehaviorTransformationData_BehaviorTransformationDetailDataSet()
     * @model containment="true" required="true"
     * @generated
     */
    EList<BehaviorTransformationDetailData> getBehaviorTransformationDetailDataSet();

} // BehaviorTransformationData
