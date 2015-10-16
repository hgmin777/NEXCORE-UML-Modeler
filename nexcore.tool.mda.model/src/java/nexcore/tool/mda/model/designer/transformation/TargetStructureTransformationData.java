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
 * <em><b>Target Structure Transformation Data</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData#getDataName
 * <em>Data Name</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData#getTargetCreationType
 * <em>Target Creation Type</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData#getTargetLocation
 * <em>Target Location</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData#getTargetApplicableStereotype
 * <em>Target Applicable Stereotype</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData#getTargetType
 * <em>Target Type</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData#getParentType
 * <em>Parent Type</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData#getTargetName
 * <em>Target Name</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData#getApplicationType
 * <em>Application Type</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData#isPropertyCreation
 * <em>Property Creation</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData#getPropertyApplicableStereotype
 * <em>Property Applicable Stereotype</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData#isOperationCreation
 * <em>Operation Creation</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData#getOperationApplicableStereotype
 * <em>Operation Applicable Stereotype</em>}</li>
 * </ul>
 * </p>
 * 
 * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getTargetStructureTransformationData()
 * @model
 * @generated
 */
public interface TargetStructureTransformationData extends EObject {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "Copyright (c) 2010 SK C&C Co., Ltd. All rights reserved.\r\n\r\nThis software is the confidential and proprietary information of SK C&C.\r\nYou shall not disclose such confidential information and shall use it\r\nonly in accordance with the terms of the license agreement you entered into\r\nwith SK C&C.";

    /**
     * Returns the value of the '<em><b>Data Name</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Data Name</em>' containment reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Data Name</em>' containment reference.
     * @see #setDataName(DataNameType)
     * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getTargetStructureTransformationData_DataName()
     * @model containment="true" required="true"
     * @generated
     */
    DataNameType getDataName();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData#getDataName
     * <em>Data Name</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Data Name</em>' containment
     *            reference.
     * @see #getDataName()
     * @generated
     */
    void setDataName(DataNameType value);

    /**
     * Returns the value of the '<em><b>Target Creation Type</b></em>'
     * attribute. The literals are from the enumeration
     * {@link nexcore.tool.mda.model.designer.transformation.TargetCreationType}
     * . <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target Creation Type</em>' attribute isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Target Creation Type</em>' attribute.
     * @see nexcore.tool.mda.model.designer.transformation.TargetCreationType
     * @see #setTargetCreationType(TargetCreationType)
     * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getTargetStructureTransformationData_TargetCreationType()
     * @model required="true"
     * @generated
     */
    TargetCreationType getTargetCreationType();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData#getTargetCreationType
     * <em>Target Creation Type</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Target Creation Type</em>'
     *            attribute.
     * @see nexcore.tool.mda.model.designer.transformation.TargetCreationType
     * @see #getTargetCreationType()
     * @generated
     */
    void setTargetCreationType(TargetCreationType value);

    /**
     * Returns the value of the '<em><b>Target Location</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target Location</em>' containment reference
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Target Location</em>' containment
     *         reference.
     * @see #setTargetLocation(LocationType)
     * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getTargetStructureTransformationData_TargetLocation()
     * @model containment="true" required="true"
     * @generated
     */
    LocationType getTargetLocation();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData#getTargetLocation
     * <em>Target Location</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Target Location</em>' containment
     *            reference.
     * @see #getTargetLocation()
     * @generated
     */
    void setTargetLocation(LocationType value);

    /**
     * Returns the value of the '<em><b>Target Applicable Stereotype</b></em>'
     * attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target Applicable Stereotype</em>' attribute
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Target Applicable Stereotype</em>'
     *         attribute.
     * @see #setTargetApplicableStereotype(String)
     * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getTargetStructureTransformationData_TargetApplicableStereotype()
     * @model required="true"
     * @generated
     */
    String getTargetApplicableStereotype();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData#getTargetApplicableStereotype
     * <em>Target Applicable Stereotype</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Target Applicable Stereotype</em>'
     *            attribute.
     * @see #getTargetApplicableStereotype()
     * @generated
     */
    void setTargetApplicableStereotype(String value);

    /**
     * Returns the value of the '<em><b>Target Type</b></em>' attribute. The
     * literals are from the enumeration
     * {@link nexcore.tool.mda.model.designer.transformation.TargetType}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target Type</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Target Type</em>' attribute.
     * @see nexcore.tool.mda.model.designer.transformation.TargetType
     * @see #setTargetType(TargetType)
     * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getTargetStructureTransformationData_TargetType()
     * @model required="true"
     * @generated
     */
    TargetType getTargetType();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData#getTargetType
     * <em>Target Type</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Target Type</em>' attribute.
     * @see nexcore.tool.mda.model.designer.transformation.TargetType
     * @see #getTargetType()
     * @generated
     */
    void setTargetType(TargetType value);

    /**
     * Returns the value of the '<em><b>Parent Type</b></em>' containment
     * reference list. The list contents are of type
     * {@link nexcore.tool.mda.model.designer.transformation.ParentType}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Parent Type</em>' containment reference list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Parent Type</em>' containment reference
     *         list.
     * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getTargetStructureTransformationData_ParentType()
     * @model containment="true"
     * @generated
     */
    EList<ParentType> getParentType();

    /**
     * Returns the value of the '<em><b>Target Name</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target Name</em>' containment reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Target Name</em>' containment reference.
     * @see #setTargetName(NameType)
     * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getTargetStructureTransformationData_TargetName()
     * @model containment="true" required="true"
     * @generated
     */
    NameType getTargetName();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData#getTargetName
     * <em>Target Name</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Target Name</em>' containment
     *            reference.
     * @see #getTargetName()
     * @generated
     */
    void setTargetName(NameType value);

    /**
     * Returns the value of the '<em><b>Application Type</b></em>' attribute.
     * The literals are from the enumeration
     * {@link nexcore.tool.mda.model.designer.transformation.ApplicationType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Application Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Application Type</em>' attribute.
     * @see nexcore.tool.mda.model.designer.transformation.ApplicationType
     * @see #setApplicationType(ApplicationType)
     * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getTargetStructureTransformationData_ApplicationType()
     * @model required="true"
     * @generated
     */
    ApplicationType getApplicationType();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData#getApplicationType
     * <em>Application Type</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Application Type</em>' attribute.
     * @see nexcore.tool.mda.model.designer.transformation.ApplicationType
     * @see #getApplicationType()
     * @generated
     */
    void setApplicationType(ApplicationType value);

    /**
     * Returns the value of the '<em><b>Property Creation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Property Creation</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Property Creation</em>' attribute.
     * @see #setPropertyCreation(boolean)
     * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getTargetStructureTransformationData_PropertyCreation()
     * @model required="true"
     * @generated
     */
    boolean isPropertyCreation();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData#isPropertyCreation
     * <em>Property Creation</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Property Creation</em>' attribute.
     * @see #isPropertyCreation()
     * @generated
     */
    void setPropertyCreation(boolean value);

    /**
     * Returns the value of the '<em><b>Property Applicable Stereotype</b></em>'
     * attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Property Applicable Stereotype</em>' attribute
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Property Applicable Stereotype</em>'
     *         attribute.
     * @see #setPropertyApplicableStereotype(String)
     * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getTargetStructureTransformationData_PropertyApplicableStereotype()
     * @model
     * @generated
     */
    String getPropertyApplicableStereotype();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData#getPropertyApplicableStereotype
     * <em>Property Applicable Stereotype</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Property Applicable Stereotype</em>'
     *            attribute.
     * @see #getPropertyApplicableStereotype()
     * @generated
     */
    void setPropertyApplicableStereotype(String value);

    /**
     * Returns the value of the '<em><b>Operation Creation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Operation Creation</em>' attribute isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Operation Creation</em>' attribute.
     * @see #setOperationCreation(boolean)
     * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getTargetStructureTransformationData_OperationCreation()
     * @model
     * @generated
     */
    boolean isOperationCreation();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData#isOperationCreation
     * <em>Operation Creation</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Operation Creation</em>' attribute.
     * @see #isOperationCreation()
     * @generated
     */
    void setOperationCreation(boolean value);

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
     * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#getTargetStructureTransformationData_OperationApplicableStereotype()
     * @model
     * @generated
     */
    String getOperationApplicableStereotype();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData#getOperationApplicableStereotype
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

} // TargetStructureTransformationData
