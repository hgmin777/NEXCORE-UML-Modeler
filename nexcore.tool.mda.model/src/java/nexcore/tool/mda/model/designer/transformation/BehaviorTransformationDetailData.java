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
 * <em><b>Behavior Transformation Detail Data</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.BehaviorTransformationDetailData#getSequence
 * <em>Sequence</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.BehaviorTransformationDetailData#getTargetRelation
 * <em>Target Relation</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.BehaviorTransformationDetailData#getOperation
 * <em>Operation</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.BehaviorTransformationDetailData#getOperationApplicableStereotype
 * <em>Operation Applicable Stereotype</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.BehaviorTransformationDetailData#getMessage
 * <em>Message</em>}</li>
 * </ul>
 * </p>
 * 
 * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getBehaviorTransformationDetailData()
 * @model
 * @generated
 */
public interface BehaviorTransformationDetailData extends EObject {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "";

    /**
     * Returns the value of the '<em><b>Sequence</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Sequence</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Sequence</em>' attribute.
     * @see #setSequence(int)
     * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getBehaviorTransformationDetailData_Sequence()
     * @model required="true"
     * @generated
     */
    int getSequence();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.designer.transformation.BehaviorTransformationDetailData#getSequence
     * <em>Sequence</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Sequence</em>' attribute.
     * @see #getSequence()
     * @generated
     */
    void setSequence(int value);

    /**
     * Returns the value of the '<em><b>Target Relation</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target Relation</em>' containment reference
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Target Relation</em>' containment
     *         reference.
     * @see #setTargetRelation(TargetRelationType)
     * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getBehaviorTransformationDetailData_TargetRelation()
     * @model containment="true" required="true"
     * @generated
     */
    TargetRelationType getTargetRelation();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.designer.transformation.BehaviorTransformationDetailData#getTargetRelation
     * <em>Target Relation</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Target Relation</em>' containment
     *            reference.
     * @see #getTargetRelation()
     * @generated
     */
    void setTargetRelation(TargetRelationType value);

    /**
     * Returns the value of the '<em><b>Operation</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Operation</em>' containment reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Operation</em>' containment reference.
     * @see #setOperation(OperationType)
     * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getBehaviorTransformationDetailData_Operation()
     * @model containment="true" required="true"
     * @generated
     */
    OperationType getOperation();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.designer.transformation.BehaviorTransformationDetailData#getOperation
     * <em>Operation</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Operation</em>' containment
     *            reference.
     * @see #getOperation()
     * @generated
     */
    void setOperation(OperationType value);

    /**
     * Returns the value of the '<em><b>Operation Applicable Stereotype</b></em>
     * ' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Operation Applicable Stereotype</em>'
     * attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Operation Applicable Stereotype</em>'
     *         attribute.
     * @see #setOperationApplicableStereotype(String)
     * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getBehaviorTransformationDetailData_OperationApplicableStereotype()
     * @model
     * @generated
     */
    String getOperationApplicableStereotype();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.designer.transformation.BehaviorTransformationDetailData#getOperationApplicableStereotype
     * <em>Operation Applicable Stereotype</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Operation Applicable Stereotype</em>
     *            ' attribute.
     * @see #getOperationApplicableStereotype()
     * @generated
     */
    void setOperationApplicableStereotype(String value);

    /**
     * Returns the value of the '<em><b>Message</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Message</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Message</em>' attribute.
     * @see #setMessage(String)
     * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getBehaviorTransformationDetailData_Message()
     * @model
     * @generated
     */
    String getMessage();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.designer.transformation.BehaviorTransformationDetailData#getMessage
     * <em>Message</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Message</em>' attribute.
     * @see #getMessage()
     * @generated
     */
    void setMessage(String value);

} // BehaviorTransformationDetailData
