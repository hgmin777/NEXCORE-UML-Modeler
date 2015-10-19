/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.model.umldiagram;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Abstract Node</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.AbstractNode#getWidth <em>Width</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.AbstractNode#getHeight <em>Height</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.AbstractNode#getIncomingConnectionList <em>Incoming Connection List</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.AbstractNode#getSourceAnchorMap <em>Source Anchor Map</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.AbstractNode#getOutgoingConnectionList <em>Outgoing Connection List</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.AbstractNode#getTargetAnchorMap <em>Target Anchor Map</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.AbstractNode#isIsContainer <em>Is Container</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.AbstractNode#getNodeType <em>Node Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getAbstractNode()
 * @model abstract="true"
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.umldiagram</li>
 * <li>설  명 : AbstractNode</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public interface AbstractNode extends AbstractView {
    /**
     * Returns the value of the '<em><b>Width</b></em>' attribute.
     * The default value is <code>"0"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Width</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Width</em>' attribute.
     * @see #isSetWidth()
     * @see #unsetWidth()
     * @see #setWidth(int)
     * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getAbstractNode_Width()
     * @model default="0" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Int"
     * @generated
     */
    int getWidth();

    /**
     * Sets the value of the '{@link nexcore.tool.uml.model.umldiagram.AbstractNode#getWidth <em>Width</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Width</em>' attribute.
     * @see #isSetWidth()
     * @see #unsetWidth()
     * @see #getWidth()
     * @generated
     */
    void setWidth(int value);

    /**
     * Unsets the value of the '{@link nexcore.tool.uml.model.umldiagram.AbstractNode#getWidth <em>Width</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #isSetWidth()
     * @see #getWidth()
     * @see #setWidth(int)
     * @generated
     */
    void unsetWidth();

    /**
     * Returns whether the value of the '{@link nexcore.tool.uml.model.umldiagram.AbstractNode#getWidth <em>Width</em>}' attribute is set.
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
     * Returns the value of the '<em><b>Height</b></em>' attribute.
     * The default value is <code>"0"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Height</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Height</em>' attribute.
     * @see #isSetHeight()
     * @see #unsetHeight()
     * @see #setHeight(int)
     * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getAbstractNode_Height()
     * @model default="0" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Int"
     * @generated
     */
    int getHeight();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.uml.model.umldiagram.AbstractNode#getHeight
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
     * {@link nexcore.tool.uml.model.umldiagram.AbstractNode#getHeight
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
     * Returns whether the value of the '{@link nexcore.tool.uml.model.umldiagram.AbstractNode#getHeight <em>Height</em>}' attribute is set.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return whether the value of the '<em>Height</em>' attribute is set.
     * @see #unsetHeight()
     * @see #getHeight()
     * @see #setHeight(int)
     * @generated
     */
    boolean isSetHeight();

    /**
     * Returns the value of the '<em><b>Incoming Connection List</b></em>'
     * reference list. The list contents are of type
     * {@link nexcore.tool.uml.model.umldiagram.AbstractConnection}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Incoming Connection List</em>' reference list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Incoming Connection List</em>' reference
     *         list.
     * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getAbstractNode_IncomingConnectionList()
     * @model resolveProxies="false"
     * @generated
     */
    EList<AbstractConnection> getIncomingConnectionList();

    /**
     * Returns the value of the '<em><b>Source Anchor Map</b></em>' containment reference list.
     * The list contents are of type {@link nexcore.tool.uml.model.umldiagram.Map}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Source Anchor Map</em>' containment reference
     * list isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Source Anchor Map</em>' containment reference list.
     * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getAbstractNode_SourceAnchorMap()
     * @model containment="true"
     * @generated
     */
    EList<Map> getSourceAnchorMap();

    /**
     * Returns the value of the '<em><b>Outgoing Connection List</b></em>'
     * reference list. The list contents are of type
     * {@link nexcore.tool.uml.model.umldiagram.AbstractConnection}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Outgoing Connection List</em>' reference list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Outgoing Connection List</em>' reference
     *         list.
     * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getAbstractNode_OutgoingConnectionList()
     * @model resolveProxies="false"
     * @generated
     */
    EList<AbstractConnection> getOutgoingConnectionList();

    /**
     * Returns the value of the '<em><b>Target Anchor Map</b></em>' containment reference list.
     * The list contents are of type {@link nexcore.tool.uml.model.umldiagram.Map}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target Anchor Map</em>' containment reference
     * list isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Target Anchor Map</em>' containment reference list.
     * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getAbstractNode_TargetAnchorMap()
     * @model containment="true"
     * @generated
     */
    EList<Map> getTargetAnchorMap();

    /**
     * Returns the value of the '<em><b>Is Container</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Is Container</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Is Container</em>' attribute.
     * @see #setIsContainer(boolean)
     * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getAbstractNode_IsContainer()
     * @model dataType="org.eclipse.emf.ecore.xml.type.Boolean" required="true"
     * @generated
     */
    boolean isIsContainer();

    /**
     * Sets the value of the '{@link nexcore.tool.uml.model.umldiagram.AbstractNode#isIsContainer <em>Is Container</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @param value the new value of the '<em>Is Container</em>' attribute.
     * @see #isIsContainer()
     * @generated
     */
    void setIsContainer(boolean value);

    /**
     * Returns the value of the '<em><b>Node Type</b></em>' attribute.
     * The literals are from the enumeration {@link nexcore.tool.uml.model.umldiagram.NodeType}.
     * <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Node Type</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Node Type</em>' attribute.
     * @see nexcore.tool.uml.model.umldiagram.NodeType
     * @see #isSetNodeType()
     * @see #unsetNodeType()
     * @see #setNodeType(NodeType)
     * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getAbstractNode_NodeType()
     * @model unsettable="true"
     * @generated
     */
    NodeType getNodeType();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.uml.model.umldiagram.AbstractNode#getNodeType
     * <em>Node Type</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Node Type</em>' attribute.
     * @see nexcore.tool.uml.model.umldiagram.NodeType
     * @see #isSetNodeType()
     * @see #unsetNodeType()
     * @see #getNodeType()
     * @generated
     */
    void setNodeType(NodeType value);

    /**
     * Unsets the value of the '
     * {@link nexcore.tool.uml.model.umldiagram.AbstractNode#getNodeType
     * <em>Node Type</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see #isSetNodeType()
     * @see #getNodeType()
     * @see #setNodeType(NodeType)
     * @generated
     */
    void unsetNodeType();

    /**
     * Returns whether the value of the '{@link nexcore.tool.uml.model.umldiagram.AbstractNode#getNodeType <em>Node Type</em>}' attribute is set.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return whether the value of the '<em>Node Type</em>' attribute is set.
     * @see #unsetNodeType()
     * @see #getNodeType()
     * @see #setNodeType(NodeType)
     * @generated
     */
    boolean isSetNodeType();

} // AbstractNode
