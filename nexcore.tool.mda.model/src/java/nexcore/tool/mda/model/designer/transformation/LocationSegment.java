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
 * <em><b>Location Segment</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.LocationSegment#getSequence
 * <em>Sequence</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.LocationSegment#getLocationName
 * <em>Location Name</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.LocationSegment#getLocationKeyword
 * <em>Location Keyword</em>}</li>
 * </ul>
 * </p>
 * 
 * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getLocationSegment()
 * @model
 * @generated
 */
public interface LocationSegment extends EObject {
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
     * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getLocationSegment_Sequence()
     * @model required="true"
     * @generated
     */
    int getSequence();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.designer.transformation.LocationSegment#getSequence
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
     * Returns the value of the '<em><b>Location Name</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Location Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Location Name</em>' attribute.
     * @see #setLocationName(String)
     * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getLocationSegment_LocationName()
     * @model
     * @generated
     */
    String getLocationName();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.designer.transformation.LocationSegment#getLocationName
     * <em>Location Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Location Name</em>' attribute.
     * @see #getLocationName()
     * @generated
     */
    void setLocationName(String value);

    /**
     * Returns the value of the '<em><b>Location Keyword</b></em>' attribute.
     * The literals are from the enumeration
     * {@link nexcore.tool.mda.model.designer.transformation.LocationKeywordType}
     * . <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Location Keyword</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Location Keyword</em>' attribute.
     * @see nexcore.tool.mda.model.designer.transformation.LocationKeywordType
     * @see #setLocationKeyword(LocationKeywordType)
     * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getLocationSegment_LocationKeyword()
     * @model
     * @generated
     */
    LocationKeywordType getLocationKeyword();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.designer.transformation.LocationSegment#getLocationKeyword
     * <em>Location Keyword</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Location Keyword</em>' attribute.
     * @see nexcore.tool.mda.model.designer.transformation.LocationKeywordType
     * @see #getLocationKeyword()
     * @generated
     */
    void setLocationKeyword(LocationKeywordType value);

} // LocationSegment
