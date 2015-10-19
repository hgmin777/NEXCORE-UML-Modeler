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
import nexcore.tool.uml.model.umldiagram.Diagram;
import nexcore.tool.uml.model.umldiagram.DiagramType;
import nexcore.tool.uml.model.umldiagram.UMLDiagramPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Diagram</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.impl.DiagramImpl#getType <em>Type</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.impl.DiagramImpl#getNodeList <em>Node List</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.impl.DiagramImpl#getConnectionList <em>Connection List</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.umldiagram.impl</li>
 * <li>설  명 : DiagramImpl</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class DiagramImpl extends AbstractNodeImpl implements Diagram {
    /**
     * The default value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    protected static final DiagramType TYPE_EDEFAULT = DiagramType.USE_CASE_DIAGRAM;

    /**
     * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    protected DiagramType type = TYPE_EDEFAULT;

    /**
     * This is true if the Type attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean typeESet;

    /**
     * The cached value of the '{@link #getNodeList() <em>Node List</em>}' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getNodeList()
     * @generated
     * @ordered
     */
    protected EList<AbstractNode> nodeList;

    /**
     * The cached value of the '{@link #getConnectionList()
     * <em>Connection List</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getConnectionList()
     * @generated
     * @ordered
     */
    protected EList<AbstractConnection> connectionList;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected DiagramImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return UMLDiagramPackage.Literals.DIAGRAM;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public DiagramType getType() {
        return type;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setType(DiagramType newType) {
        DiagramType oldType = type;
        type = newType == null ? TYPE_EDEFAULT : newType;
        boolean oldTypeESet = typeESet;
        typeESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UMLDiagramPackage.DIAGRAM__TYPE, oldType, type, !oldTypeESet));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void unsetType() {
        DiagramType oldType = type;
        boolean oldTypeESet = typeESet;
        type = TYPE_EDEFAULT;
        typeESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, UMLDiagramPackage.DIAGRAM__TYPE, oldType, TYPE_EDEFAULT, oldTypeESet));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetType() {
        return typeESet;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EList<AbstractNode> getNodeList() {
        if (nodeList == null) {
            nodeList = new EObjectContainmentEList<AbstractNode>(AbstractNode.class, this, UMLDiagramPackage.DIAGRAM__NODE_LIST);
        }
        return nodeList;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EList<AbstractConnection> getConnectionList() {
        if (connectionList == null) {
            connectionList = new EObjectContainmentEList<AbstractConnection>(AbstractConnection.class, this, UMLDiagramPackage.DIAGRAM__CONNECTION_LIST);
        }
        return connectionList;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case UMLDiagramPackage.DIAGRAM__NODE_LIST:
                return ((InternalEList<?>)getNodeList()).basicRemove(otherEnd, msgs);
            case UMLDiagramPackage.DIAGRAM__CONNECTION_LIST:
                return ((InternalEList<?>)getConnectionList()).basicRemove(otherEnd, msgs);
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
            case UMLDiagramPackage.DIAGRAM__TYPE:
                return getType();
            case UMLDiagramPackage.DIAGRAM__NODE_LIST:
                return getNodeList();
            case UMLDiagramPackage.DIAGRAM__CONNECTION_LIST:
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
            case UMLDiagramPackage.DIAGRAM__TYPE:
                setType((DiagramType)newValue);
                return;
            case UMLDiagramPackage.DIAGRAM__NODE_LIST:
                getNodeList().clear();
                getNodeList().addAll((Collection<? extends AbstractNode>)newValue);
                return;
            case UMLDiagramPackage.DIAGRAM__CONNECTION_LIST:
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
            case UMLDiagramPackage.DIAGRAM__TYPE:
                unsetType();
                return;
            case UMLDiagramPackage.DIAGRAM__NODE_LIST:
                getNodeList().clear();
                return;
            case UMLDiagramPackage.DIAGRAM__CONNECTION_LIST:
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
            case UMLDiagramPackage.DIAGRAM__TYPE:
                return isSetType();
            case UMLDiagramPackage.DIAGRAM__NODE_LIST:
                return nodeList != null && !nodeList.isEmpty();
            case UMLDiagramPackage.DIAGRAM__CONNECTION_LIST:
                return connectionList != null && !connectionList.isEmpty();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (type: ");
        if (typeESet) result.append(type); else result.append("<unset>");
        result.append(')');
        return result.toString();
    }

} // DiagramImpl
