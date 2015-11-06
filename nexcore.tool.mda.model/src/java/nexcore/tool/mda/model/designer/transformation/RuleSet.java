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
 * <em><b>Rule Set</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.RuleSet#getStructureRules
 * <em>Structure Rules</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.RuleSet#getBehaviorRules
 * <em>Behavior Rules</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.RuleSet#getSourceModelLocation
 * <em>Source Model Location</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.RuleSet#getSourceProfileLocation
 * <em>Source Profile Location</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.RuleSet#getTargetModelLocation
 * <em>Target Model Location</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.RuleSet#getTargetProfileLocation
 * <em>Target Profile Location</em>}</li>
 * </ul>
 * </p>
 * 
 * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getRuleSet()
 * @model
 * @generated
 */
public interface RuleSet extends EObject {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "";

    /**
     * Returns the value of the '<em><b>Structure Rules</b></em>' containment
     * reference list. The list contents are of type
     * {@link nexcore.tool.mda.model.designer.transformation.SourceStructureTransformationData}
     * . <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Structure Rules</em>' containment reference
     * list isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Structure Rules</em>' containment reference
     *         list.
     * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getRuleSet_StructureRules()
     * @model containment="true"
     * @generated
     */
    EList<SourceStructureTransformationData> getStructureRules();

    /**
     * Returns the value of the '<em><b>Behavior Rules</b></em>' containment
     * reference list. The list contents are of type
     * {@link nexcore.tool.mda.model.designer.transformation.BehaviorTransformationData}
     * . <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Behavior Rules</em>' containment reference
     * list isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Behavior Rules</em>' containment reference
     *         list.
     * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getRuleSet_BehaviorRules()
     * @model containment="true"
     * @generated
     */
    EList<BehaviorTransformationData> getBehaviorRules();

    /**
     * Returns the value of the '<em><b>Source Model Location</b></em>'
     * attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Source Model Location</em>' attribute isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Source Model Location</em>' attribute.
     * @see #setSourceModelLocation(String)
     * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getRuleSet_SourceModelLocation()
     * @model required="true"
     * @generated
     */
    String getSourceModelLocation();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.designer.transformation.RuleSet#getSourceModelLocation
     * <em>Source Model Location</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Source Model Location</em>'
     *            attribute.
     * @see #getSourceModelLocation()
     * @generated
     */
    void setSourceModelLocation(String value);

    /**
     * Returns the value of the '<em><b>Source Profile Location</b></em>'
     * attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Source Profile Location</em>' attribute isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Source Profile Location</em>' attribute.
     * @see #setSourceProfileLocation(String)
     * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getRuleSet_SourceProfileLocation()
     * @model required="true"
     * @generated
     */
    String getSourceProfileLocation();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.designer.transformation.RuleSet#getSourceProfileLocation
     * <em>Source Profile Location</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Source Profile Location</em>'
     *            attribute.
     * @see #getSourceProfileLocation()
     * @generated
     */
    void setSourceProfileLocation(String value);

    /**
     * Returns the value of the '<em><b>Target Model Location</b></em>'
     * attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target Model Location</em>' attribute isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Target Model Location</em>' attribute.
     * @see #setTargetModelLocation(String)
     * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getRuleSet_TargetModelLocation()
     * @model required="true"
     * @generated
     */
    String getTargetModelLocation();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.designer.transformation.RuleSet#getTargetModelLocation
     * <em>Target Model Location</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Target Model Location</em>'
     *            attribute.
     * @see #getTargetModelLocation()
     * @generated
     */
    void setTargetModelLocation(String value);

    /**
     * Returns the value of the '<em><b>Target Profile Location</b></em>'
     * attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target Profile Location</em>' attribute isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Target Profile Location</em>' attribute.
     * @see #setTargetProfileLocation(String)
     * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getRuleSet_TargetProfileLocation()
     * @model required="true"
     * @generated
     */
    String getTargetProfileLocation();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.designer.transformation.RuleSet#getTargetProfileLocation
     * <em>Target Profile Location</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Target Profile Location</em>'
     *            attribute.
     * @see #getTargetProfileLocation()
     * @generated
     */
    void setTargetProfileLocation(String value);

} // RuleSet
