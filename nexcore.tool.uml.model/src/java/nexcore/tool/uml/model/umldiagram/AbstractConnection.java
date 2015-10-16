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
 * <em><b>Abstract Connection</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.AbstractConnection#getSource <em>Source</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.AbstractConnection#getSourceAnchor <em>Source Anchor</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.AbstractConnection#getTarget <em>Target</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.AbstractConnection#getTargetAnchor <em>Target Anchor</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.AbstractConnection#getBendPointList <em>Bend Point List</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.AbstractConnection#getLabels <em>Labels</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.AbstractConnection#getRelationType <em>Relation Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getAbstractConnection()
 * @model abstract="true"
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.umldiagram</li>
 * <li>설  명 : AbstractConnection</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public interface AbstractConnection extends AbstractView {
    /**
     * Returns the value of the '<em><b>Source</b></em>' reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Source</em>' reference isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Source</em>' reference.
     * @see #setSource(AbstractView)
     * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getAbstractConnection_Source()
     * @model resolveProxies="false" required="true"
     * @generated
     */
    AbstractView getSource();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.uml.model.umldiagram.AbstractConnection#getSource
     * <em>Source</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Source</em>' reference.
     * @see #getSource()
     * @generated
     */
    void setSource(AbstractView value);

    /**
     * Returns the value of the '<em><b>Source Anchor</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Source Anchor</em>' containment reference
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Source Anchor</em>' containment reference.
     * @see #setSourceAnchor(Dimension)
     * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getAbstractConnection_SourceAnchor()
     * @model containment="true"
     * @generated
     */
    Dimension getSourceAnchor();

    /**
     * Sets the value of the '{@link nexcore.tool.uml.model.umldiagram.AbstractConnection#getSourceAnchor <em>Source Anchor</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Source Anchor</em>' containment reference.
     * @see #getSourceAnchor()
     * @generated
     */
    void setSourceAnchor(Dimension value);

    /**
     * Returns the value of the '<em><b>Target</b></em>' reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target</em>' reference isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Target</em>' reference.
     * @see #setTarget(AbstractView)
     * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getAbstractConnection_Target()
     * @model resolveProxies="false" required="true"
     * @generated
     */
    AbstractView getTarget();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.uml.model.umldiagram.AbstractConnection#getTarget
     * <em>Target</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Target</em>' reference.
     * @see #getTarget()
     * @generated
     */
    void setTarget(AbstractView value);

    /**
     * Returns the value of the '<em><b>Target Anchor</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target Anchor</em>' containment reference
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Target Anchor</em>' containment reference.
     * @see #setTargetAnchor(Dimension)
     * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getAbstractConnection_TargetAnchor()
     * @model containment="true"
     * @generated
     */
    Dimension getTargetAnchor();

    /**
     * Sets the value of the '{@link nexcore.tool.uml.model.umldiagram.AbstractConnection#getTargetAnchor <em>Target Anchor</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target Anchor</em>' containment reference.
     * @see #getTargetAnchor()
     * @generated
     */
    void setTargetAnchor(Dimension value);

    /**
     * Returns the value of the '<em><b>Bend Point List</b></em>' containment reference list.
     * The list contents are of type {@link nexcore.tool.uml.model.umldiagram.BendPoint}.
     * <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Bend Point List</em>' containment reference
     * list isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Bend Point List</em>' containment reference list.
     * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getAbstractConnection_BendPointList()
     * @model containment="true"
     * @generated
     */
    EList<BendPoint> getBendPointList();

    /**
     * Returns the value of the '<em><b>Labels</b></em>' containment reference list.
     * The list contents are of type {@link nexcore.tool.uml.model.umldiagram.LabelNode}.
     * <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Labels</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Labels</em>' containment reference list.
     * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getAbstractConnection_Labels()
     * @model containment="true" upper="5"
     * @generated
     */
    EList<LabelNode> getLabels();

    /**
     * Returns the value of the '<em><b>Relation Type</b></em>' attribute. The
     * literals are from the enumeration
     * {@link nexcore.tool.uml.model.umldiagram.RelationType}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Relation Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Relation Type</em>' attribute.
     * @see nexcore.tool.uml.model.umldiagram.RelationType
     * @see #isSetRelationType()
     * @see #unsetRelationType()
     * @see #setRelationType(RelationType)
     * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getAbstractConnection_RelationType()
     * @model unsettable="true" required="true"
     * @generated
     */
    RelationType getRelationType();

    /**
     * Sets the value of the '{@link nexcore.tool.uml.model.umldiagram.AbstractConnection#getRelationType <em>Relation Type</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @param value the new value of the '<em>Relation Type</em>' attribute.
     * @see nexcore.tool.uml.model.umldiagram.RelationType
     * @see #isSetRelationType()
     * @see #unsetRelationType()
     * @see #getRelationType()
     * @generated
     */
    void setRelationType(RelationType value);

    /**
     * Unsets the value of the '{@link nexcore.tool.uml.model.umldiagram.AbstractConnection#getRelationType <em>Relation Type</em>}' attribute.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #isSetRelationType()
     * @see #getRelationType()
     * @see #setRelationType(RelationType)
     * @generated
     */
    void unsetRelationType();

    /**
     * Returns whether the value of the '{@link nexcore.tool.uml.model.umldiagram.AbstractConnection#getRelationType <em>Relation Type</em>}' attribute is set.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return whether the value of the '<em>Relation Type</em>' attribute is set.
     * @see #unsetRelationType()
     * @see #getRelationType()
     * @see #setRelationType(RelationType)
     * @generated
     */
    boolean isSetRelationType();

} // AbstractConnection
