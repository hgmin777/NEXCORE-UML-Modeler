/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.model.umldiagram;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Bend Point</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.BendPoint#getFirstPosition <em>First Position</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.BendPoint#getSecondPosition <em>Second Position</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.BendPoint#getWeight <em>Weight</em>}</li>
 * </ul>
 * </p>
 *
 * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getBendPoint()
 * @model
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.umldiagram</li>
 * <li>설  명 : BendPoint</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public interface BendPoint extends EObject {
    /**
     * Returns the value of the '<em><b>First Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>First Position</em>' containment reference
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>First Position</em>' containment reference.
     * @see #setFirstPosition(Dimension)
     * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getBendPoint_FirstPosition()
     * @model containment="true" required="true"
     * @generated
     */
    Dimension getFirstPosition();

    /**
     * Sets the value of the '{@link nexcore.tool.uml.model.umldiagram.BendPoint#getFirstPosition <em>First Position</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>First Position</em>' containment reference.
     * @see #getFirstPosition()
     * @generated
     */
    void setFirstPosition(Dimension value);

    /**
     * Returns the value of the '<em><b>Second Position</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Second Position</em>' containment reference
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Second Position</em>' containment reference.
     * @see #setSecondPosition(Dimension)
     * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getBendPoint_SecondPosition()
     * @model containment="true" required="true"
     * @generated
     */
    Dimension getSecondPosition();

    /**
     * Sets the value of the '{@link nexcore.tool.uml.model.umldiagram.BendPoint#getSecondPosition <em>Second Position</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Second Position</em>' containment reference.
     * @see #getSecondPosition()
     * @generated
     */
    void setSecondPosition(Dimension value);

    /**
     * Returns the value of the '<em><b>Weight</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Weight</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Weight</em>' attribute.
     * @see #isSetWeight()
     * @see #unsetWeight()
     * @see #setWeight(float)
     * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getBendPoint_Weight()
     * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Float"
     * @generated
     */
    float getWeight();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.uml.model.umldiagram.BendPoint#getWeight
     * <em>Weight</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Weight</em>' attribute.
     * @see #isSetWeight()
     * @see #unsetWeight()
     * @see #getWeight()
     * @generated
     */
    void setWeight(float value);

    /**
     * Unsets the value of the '
     * {@link nexcore.tool.uml.model.umldiagram.BendPoint#getWeight
     * <em>Weight</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see #isSetWeight()
     * @see #getWeight()
     * @see #setWeight(float)
     * @generated
     */
    void unsetWeight();

    /**
     * Returns whether the value of the '{@link nexcore.tool.uml.model.umldiagram.BendPoint#getWeight <em>Weight</em>}' attribute is set.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return whether the value of the '<em>Weight</em>' attribute is set.
     * @see #unsetWeight()
     * @see #getWeight()
     * @see #setWeight(float)
     * @generated
     */
    boolean isSetWeight();

} // BendPoint
