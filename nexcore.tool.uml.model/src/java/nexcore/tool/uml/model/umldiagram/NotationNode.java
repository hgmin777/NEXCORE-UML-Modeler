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
 * <em><b>Notation Node</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.NotationNode#getRegions <em>Regions</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.NotationNode#getCompartmentList <em>Compartment List</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.NotationNode#getLabels <em>Labels</em>}</li>
 * </ul>
 * </p>
 *
 * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getNotationNode()
 * @model
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.umldiagram</li>
 * <li>설  명 : NotationNode</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public interface NotationNode extends AbstractNode {
    /**
     * Returns the value of the '<em><b>Regions</b></em>' attribute list. The
     * list contents are of type
     * {@link nexcore.tool.uml.model.umldiagram.RelationType}. The literals are
     * from the enumeration
     * {@link nexcore.tool.uml.model.umldiagram.RelationType}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Regions</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Regions</em>' attribute list.
     * @see nexcore.tool.uml.model.umldiagram.RelationType
     * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getNotationNode_Regions()
     * @model
     * @generated
     */
    EList<RelationType> getRegions();

    /**
     * Returns the value of the '<em><b>Compartment List</b></em>' containment
     * reference list. The list contents are of type
     * {@link nexcore.tool.uml.model.umldiagram.AbstractNode}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Compartment List</em>' containment reference
     * list isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Compartment List</em>' containment
     *         reference list.
     * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getNotationNode_CompartmentList()
     * @model containment="true"
     * @generated
     */
    EList<AbstractNode> getCompartmentList();

    /**
     * Returns the value of the '<em><b>Labels</b></em>' reference list.
     * The list contents are of type {@link nexcore.tool.uml.model.umldiagram.LabelNode}.
     * <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Labels</em>' reference list isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Labels</em>' reference list.
     * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getNotationNode_Labels()
     * @model resolveProxies="false"
     * @generated
     */
    EList<LabelNode> getLabels();

} // NotationNode
