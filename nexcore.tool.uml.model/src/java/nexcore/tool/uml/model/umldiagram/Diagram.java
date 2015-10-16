/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.model.umldiagram;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Diagram</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.Diagram#getType <em>Type</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.Diagram#getNodeList <em>Node List</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.Diagram#getConnectionList <em>Connection List</em>}</li>
 * </ul>
 * </p>
 *
 * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getDiagram()
 * @model
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.umldiagram</li>
 * <li>설  명 : Diagram</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public interface Diagram extends AbstractNode {
    /**
     * Returns the value of the '<em><b>Type</b></em>' attribute. The literals
     * are from the enumeration
     * {@link nexcore.tool.uml.model.umldiagram.DiagramType}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Type</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Type</em>' attribute.
     * @see nexcore.tool.uml.model.umldiagram.DiagramType
     * @see #isSetType()
     * @see #unsetType()
     * @see #setType(DiagramType)
     * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getDiagram_Type()
     * @model unsettable="true" required="true"
     * @generated
     */
    DiagramType getType();

    /**
     * Sets the value of the '{@link nexcore.tool.uml.model.umldiagram.Diagram#getType <em>Type</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' attribute.
     * @see nexcore.tool.uml.model.umldiagram.DiagramType
     * @see #isSetType()
     * @see #unsetType()
     * @see #getType()
     * @generated
     */
    void setType(DiagramType value);

    /**
     * Unsets the value of the '{@link nexcore.tool.uml.model.umldiagram.Diagram#getType <em>Type</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #isSetType()
     * @see #getType()
     * @see #setType(DiagramType)
     * @generated
     */
    void unsetType();

    /**
     * Returns whether the value of the '{@link nexcore.tool.uml.model.umldiagram.Diagram#getType <em>Type</em>}' attribute is set.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return whether the value of the '<em>Type</em>' attribute is set.
     * @see #unsetType()
     * @see #getType()
     * @see #setType(DiagramType)
     * @generated
     */
    boolean isSetType();

    /**
     * Returns the value of the '<em><b>Node List</b></em>' containment
     * reference list. The list contents are of type
     * {@link nexcore.tool.uml.model.umldiagram.AbstractNode}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Node List</em>' containment reference list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Node List</em>' containment reference list.
     * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getDiagram_NodeList()
     * @model containment="true"
     * @generated
     */
    EList<AbstractNode> getNodeList();

    /**
     * Returns the value of the '<em><b>Connection List</b></em>' containment
     * reference list. The list contents are of type
     * {@link nexcore.tool.uml.model.umldiagram.AbstractConnection}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Connection List</em>' containment reference
     * list isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Connection List</em>' containment reference
     *         list.
     * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getDiagram_ConnectionList()
     * @model containment="true"
     * @generated
     */
    EList<AbstractConnection> getConnectionList();

} // Diagram
