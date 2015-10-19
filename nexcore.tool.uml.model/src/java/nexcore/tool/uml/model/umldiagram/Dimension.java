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
 * <em><b>Dimension</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.Dimension#getWidth <em>Width</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.Dimension#getHeight <em>Height</em>}</li>
 * </ul>
 * </p>
 *
 * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getDimension()
 * @model
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.umldiagram</li>
 * <li>설  명 : Dimension</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public interface Dimension extends EObject {
    /**
     * Returns the value of the '<em><b>Width</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Width</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Width</em>' attribute.
     * @see #isSetWidth()
     * @see #unsetWidth()
     * @see #setWidth(int)
     * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getDimension_Width()
     * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Int"
     *        required="true" extendedMetaData="kind='element' name='width'"
     * @generated
     */
    int getWidth();

    /**
     * Sets the value of the '{@link nexcore.tool.uml.model.umldiagram.Dimension#getWidth <em>Width</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Width</em>' attribute.
     * @see #isSetWidth()
     * @see #unsetWidth()
     * @see #getWidth()
     * @generated
     */
    void setWidth(int value);

    /**
     * Unsets the value of the '{@link nexcore.tool.uml.model.umldiagram.Dimension#getWidth <em>Width</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #isSetWidth()
     * @see #getWidth()
     * @see #setWidth(int)
     * @generated
     */
    void unsetWidth();

    /**
     * Returns whether the value of the '{@link nexcore.tool.uml.model.umldiagram.Dimension#getWidth <em>Width</em>}' attribute is set.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return whether the value of the '<em>Width</em>' attribute is set.
     * @see #unsetWidth()
     * @see #getWidth()
     * @see #setWidth(int)
     * @generated
     */
    boolean isSetWidth();

    /**
     * Returns the value of the '<em><b>Height</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Height</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Height</em>' attribute.
     * @see #isSetHeight()
     * @see #unsetHeight()
     * @see #setHeight(int)
     * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getDimension_Height()
     * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Int"
     *        required="true" extendedMetaData="kind='element' name='height'"
     * @generated
     */
    int getHeight();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.uml.model.umldiagram.Dimension#getHeight
     * <em>Height</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Height</em>' attribute.
     * @see #isSetHeight()
     * @see #unsetHeight()
     * @see #getHeight()
     * @generated
     */
    void setHeight(int value);

    /**
     * Unsets the value of the '
     * {@link nexcore.tool.uml.model.umldiagram.Dimension#getHeight
     * <em>Height</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see #isSetHeight()
     * @see #getHeight()
     * @see #setHeight(int)
     * @generated
     */
    void unsetHeight();

    /**
     * Returns whether the value of the '{@link nexcore.tool.uml.model.umldiagram.Dimension#getHeight <em>Height</em>}' attribute is set.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return whether the value of the '<em>Height</em>' attribute is set.
     * @see #unsetHeight()
     * @see #getHeight()
     * @see #setHeight(int)
     * @generated
     */
    boolean isSetHeight();

} // Dimension
