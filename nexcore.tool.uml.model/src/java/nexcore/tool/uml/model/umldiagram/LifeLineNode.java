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
 * <em><b>Life Line Node</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.LifeLineNode#getLine <em>Line</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.LifeLineNode#getBehaviorList <em>Behavior List</em>}</li>
 * </ul>
 * </p>
 *
 * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getLifeLineNode()
 * @model
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.umldiagram</li>
 * <li>설  명 : LifeLineNode</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public interface LifeLineNode extends NotationNode {
    /**
     * Returns the value of the '<em><b>Line</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Line</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Line</em>' containment reference.
     * @see #setLine(Line)
     * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getLifeLineNode_Line()
     * @model containment="true" required="true"
     * @generated
     */
    Line getLine();

    /**
     * Sets the value of the '{@link nexcore.tool.uml.model.umldiagram.LifeLineNode#getLine <em>Line</em>}' containment reference.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @param value the new value of the '<em>Line</em>' containment reference.
     * @see #getLine()
     * @generated
     */
    void setLine(Line value);

    /**
     * Returns the value of the '<em><b>Behavior List</b></em>' containment
     * reference list. The list contents are of type
     * {@link nexcore.tool.uml.model.umldiagram.NotationNode}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Behavior List</em>' containment reference list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Behavior List</em>' containment reference
     *         list.
     * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getLifeLineNode_BehaviorList()
     * @model containment="true"
     * @generated
     */
    EList<NotationNode> getBehaviorList();

} // LifeLineNode
