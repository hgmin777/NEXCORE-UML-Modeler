/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.model.umldiagram;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Region Type</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.RegionType#getRegionKind <em>Region Kind</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.RegionType#getRegionSize <em>Region Size</em>}</li>
 * </ul>
 * </p>
 *
 * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getRegionType()
 * @model
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.umldiagram</li>
 * <li>설  명 : RegionType</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public interface RegionType extends EObject {
    /**
     * Returns the value of the '<em><b>Region Kind</b></em>' attribute. The
     * literals are from the enumeration
     * {@link nexcore.tool.uml.model.umldiagram.RegionEnumType}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Region Kind</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Region Kind</em>' attribute.
     * @see nexcore.tool.uml.model.umldiagram.RegionEnumType
     * @see #setRegionKind(RegionEnumType)
     * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getRegionType_RegionKind()
     * @model required="true"
     * @generated
     */
    RegionEnumType getRegionKind();

    /**
     * Sets the value of the '{@link nexcore.tool.uml.model.umldiagram.RegionType#getRegionKind <em>Region Kind</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @param value the new value of the '<em>Region Kind</em>' attribute.
     * @see nexcore.tool.uml.model.umldiagram.RegionEnumType
     * @see #getRegionKind()
     * @generated
     */
    void setRegionKind(RegionEnumType value);

    /**
     * Returns the value of the '<em><b>Region Size</b></em>' reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Region Size</em>' reference isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Region Size</em>' reference.
     * @see #setRegionSize(Dimension)
     * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getRegionType_RegionSize()
     * @model required="true"
     * @generated
     */
    Dimension getRegionSize();

    /**
     * Sets the value of the '{@link nexcore.tool.uml.model.umldiagram.RegionType#getRegionSize <em>Region Size</em>}' reference.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @param value the new value of the '<em>Region Size</em>' reference.
     * @see #getRegionSize()
     * @generated
     */
    void setRegionSize(Dimension value);

} // RegionType
