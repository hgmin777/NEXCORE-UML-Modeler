/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.model.umldiagram.impl;

import java.util.Collection;

import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.ContainerNode;
import nexcore.tool.uml.model.umldiagram.UMLDiagramPackage;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Container Node</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.impl.ContainerNodeImpl#getNodeList <em>Node List</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.impl.ContainerNodeImpl#getConnectionList <em>Connection List</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.umldiagram.impl</li>
 * <li>설  명 : ContainerNodeImpl</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class ContainerNodeImpl extends AbstractNodeImpl implements ContainerNode {
    /**
     * The cached value of the '{@link #getNodeList() <em>Node List</em>}' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getNodeList()
     * @generated
     * @ordered
     */
    protected EList<AbstractNode> nodeList;

    /**
     * The cached value of the '{@link #getConnectionList() <em>Connection List</em>}' reference list.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getConnectionList()
     * @generated
     * @ordered
     */
    protected EList<AbstractConnection> connectionList;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected ContainerNodeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return UMLDiagramPackage.Literals.CONTAINER_NODE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EList<AbstractNode> getNodeList() {
        if (nodeList == null) {
            nodeList = new EObjectContainmentEList<AbstractNode>(AbstractNode.class, this, UMLDiagramPackage.CONTAINER_NODE__NODE_LIST);
        }
        return nodeList;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EList<AbstractConnection> getConnectionList() {
        if (connectionList == null) {
            connectionList = new EObjectEList<AbstractConnection>(AbstractConnection.class, this, UMLDiagramPackage.CONTAINER_NODE__CONNECTION_LIST);
        }
        return connectionList;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case UMLDiagramPackage.CONTAINER_NODE__NODE_LIST:
                return ((InternalEList<?>)getNodeList()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case UMLDiagramPackage.CONTAINER_NODE__NODE_LIST:
                return getNodeList();
            case UMLDiagramPackage.CONTAINER_NODE__CONNECTION_LIST:
                return getConnectionList();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case UMLDiagramPackage.CONTAINER_NODE__NODE_LIST:
                getNodeList().clear();
                getNodeList().addAll((Collection<? extends AbstractNode>)newValue);
                return;
            case UMLDiagramPackage.CONTAINER_NODE__CONNECTION_LIST:
                getConnectionList().clear();
                getConnectionList().addAll((Collection<? extends AbstractConnection>)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case UMLDiagramPackage.CONTAINER_NODE__NODE_LIST:
                getNodeList().clear();
                return;
            case UMLDiagramPackage.CONTAINER_NODE__CONNECTION_LIST:
                getConnectionList().clear();
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case UMLDiagramPackage.CONTAINER_NODE__NODE_LIST:
                return nodeList != null && !nodeList.isEmpty();
            case UMLDiagramPackage.CONTAINER_NODE__CONNECTION_LIST:
                return connectionList != null && !connectionList.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // ContainerNodeImpl
