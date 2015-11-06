/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.model.umldiagram;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Container Node</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.ContainerNode#getNodeList <em>Node List</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.ContainerNode#getConnectionList <em>Connection List</em>}</li>
 * </ul>
 * </p>
 *
 * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getContainerNode()
 * @model
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.umldiagram</li>
 * <li>설  명 : ContainerNode</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public interface ContainerNode extends AbstractNode {
    /**
     * Returns the value of the '<em><b>Node List</b></em>' reference list. The
     * list contents are of type
     * {@link nexcore.tool.uml.model.umldiagram.AbstractNode}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Node List</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Node List</em>' reference list.
     * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getContainerNode_NodeList()
     * @model resolveProxies="false"
     * @generated
     */
    EList<AbstractNode> getNodeList();

    /**
     * Returns the value of the '<em><b>Connection List</b></em>' reference
     * list. The list contents are of type
     * {@link nexcore.tool.uml.model.umldiagram.AbstractConnection}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Connection List</em>' reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Connection List</em>' reference list.
     * @see nexcore.tool.uml.model.umldiagram.UMLDiagramPackage#getContainerNode_ConnectionList()
     * @model resolveProxies="false"
     * @generated
     */
    EList<AbstractConnection> getConnectionList();

} // ContainerNode
