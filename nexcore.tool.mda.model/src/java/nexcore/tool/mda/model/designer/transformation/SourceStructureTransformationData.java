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
 * <em><b>Source Structure Transformation Data</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.SourceStructureTransformationData#getSourceType
 * <em>Source Type</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.SourceStructureTransformationData#getSourceLocation
 * <em>Source Location</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.SourceStructureTransformationData#getTargetStructureTransformationDataSet
 * <em>Target Structure Transformation Data Set</em>}</li>
 * </ul>
 * </p>
 * 
 * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getSourceStructureTransformationData()
 * @model
 * @generated
 */
public interface SourceStructureTransformationData extends EObject {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "";

    /**
     * Returns the value of the '<em><b>Source Type</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Source Type</em>' containment reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Source Type</em>' containment reference.
     * @see #setSourceType(SourceType)
     * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getSourceStructureTransformationData_SourceType()
     * @model containment="true" required="true"
     * @generated
     */
    SourceType getSourceType();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.designer.transformation.SourceStructureTransformationData#getSourceType
     * <em>Source Type</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Source Type</em>' containment
     *            reference.
     * @see #getSourceType()
     * @generated
     */
    void setSourceType(SourceType value);

    /**
     * Returns the value of the '<em><b>Source Location</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Source Location</em>' containment reference
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Source Location</em>' containment
     *         reference.
     * @see #setSourceLocation(LocationType)
     * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getSourceStructureTransformationData_SourceLocation()
     * @model containment="true" required="true"
     * @generated
     */
    LocationType getSourceLocation();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.designer.transformation.SourceStructureTransformationData#getSourceLocation
     * <em>Source Location</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Source Location</em>' containment
     *            reference.
     * @see #getSourceLocation()
     * @generated
     */
    void setSourceLocation(LocationType value);

    /**
     * Returns the value of the '
     * <em><b>Target Structure Transformation Data Set</b></em>' containment
     * reference list. The list contents are of type
     * {@link nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData}
     * . <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target Structure Transformation Data Set</em>'
     * containment reference list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '
     *         <em>Target Structure Transformation Data Set</em>' containment
     *         reference list.
     * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getSourceStructureTransformationData_TargetStructureTransformationDataSet()
     * @model containment="true" required="true"
     * @generated
     */
    EList<TargetStructureTransformationData> getTargetStructureTransformationDataSet();

} // SourceStructureTransformationData
