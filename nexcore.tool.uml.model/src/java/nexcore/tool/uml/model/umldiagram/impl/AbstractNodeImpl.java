/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.model.umldiagram.impl;

import java.util.Collection;

import nexcore.tool.uml.model.umldiagram.AbstractConnection;
import nexcore.tool.uml.model.umldiagram.AbstractNode;
import nexcore.tool.uml.model.umldiagram.Map;
import nexcore.tool.uml.model.umldiagram.NodeType;
import nexcore.tool.uml.model.umldiagram.UMLDiagramPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Abstract Node</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.impl.AbstractNodeImpl#getWidth <em>Width</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.impl.AbstractNodeImpl#getHeight <em>Height</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.impl.AbstractNodeImpl#getIncomingConnectionList <em>Incoming Connection List</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.impl.AbstractNodeImpl#getSourceAnchorMap <em>Source Anchor Map</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.impl.AbstractNodeImpl#getOutgoingConnectionList <em>Outgoing Connection List</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.impl.AbstractNodeImpl#getTargetAnchorMap <em>Target Anchor Map</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.impl.AbstractNodeImpl#isIsContainer <em>Is Container</em>}</li>
 *   <li>{@link nexcore.tool.uml.model.umldiagram.impl.AbstractNodeImpl#getNodeType <em>Node Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.umldiagram.impl</li>
 * <li>설  명 : AbstractNodeImpl</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public abstract class AbstractNodeImpl extends AbstractViewImpl implements AbstractNode {
    /**
     * The default value of the '{@link #getWidth() <em>Width</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getWidth()
     * @generated
     * @ordered
     */
    protected static final int WIDTH_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getWidth() <em>Width</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getWidth()
     * @generated
     * @ordered
     */
    protected int width = WIDTH_EDEFAULT;

    /**
     * This is true if the Width attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean widthESet;

    /**
     * The default value of the '{@link #getHeight() <em>Height</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getHeight()
     * @generated
     * @ordered
     */
    protected static final int HEIGHT_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getHeight() <em>Height</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getHeight()
     * @generated
     * @ordered
     */
    protected int height = HEIGHT_EDEFAULT;

    /**
     * This is true if the Height attribute has been set.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean heightESet;

    /**
     * The cached value of the '{@link #getIncomingConnectionList() <em>Incoming Connection List</em>}' reference list.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #getIncomingConnectionList()
     * @generated
     * @ordered
     */
    protected EList<AbstractConnection> incomingConnectionList;

    /**
     * The cached value of the '{@link #getSourceAnchorMap()
     * <em>Source Anchor Map</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getSourceAnchorMap()
     * @generated
     * @ordered
     */
    protected EList<Map> sourceAnchorMap;

    /**
     * The cached value of the '{@link #getOutgoingConnectionList() <em>Outgoing Connection List</em>}' reference list.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #getOutgoingConnectionList()
     * @generated
     * @ordered
     */
    protected EList<AbstractConnection> outgoingConnectionList;

    /**
     * The cached value of the '{@link #getTargetAnchorMap()
     * <em>Target Anchor Map</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getTargetAnchorMap()
     * @generated
     * @ordered
     */
    protected EList<Map> targetAnchorMap;

    /**
     * The default value of the '{@link #isIsContainer() <em>Is Container</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #isIsContainer()
     * @generated
     * @ordered
     */
    protected static final boolean IS_CONTAINER_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isIsContainer() <em>Is Container</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #isIsContainer()
     * @generated
     * @ordered
     */
    protected boolean isContainer = IS_CONTAINER_EDEFAULT;

    /**
     * The default value of the '{@link #getNodeType() <em>Node Type</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getNodeType()
     * @generated
     * @ordered
     */
    protected static final NodeType NODE_TYPE_EDEFAULT = NodeType.NOTE;

    /**
     * The cached value of the '{@link #getNodeType() <em>Node Type</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getNodeType()
     * @generated
     * @ordered
     */
    protected NodeType nodeType = NODE_TYPE_EDEFAULT;

    /**
     * This is true if the Node Type attribute has been set.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean nodeTypeESet;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected AbstractNodeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return UMLDiagramPackage.Literals.ABSTRACT_NODE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public int getWidth() {
        return width;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setWidth(int newWidth) {
        int oldWidth = width;
        width = newWidth;
        boolean oldWidthESet = widthESet;
        widthESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UMLDiagramPackage.ABSTRACT_NODE__WIDTH, oldWidth, width, !oldWidthESet));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void unsetWidth() {
        int oldWidth = width;
        boolean oldWidthESet = widthESet;
        width = WIDTH_EDEFAULT;
        widthESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, UMLDiagramPackage.ABSTRACT_NODE__WIDTH, oldWidth, WIDTH_EDEFAULT, oldWidthESet));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetWidth() {
        return widthESet;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public int getHeight() {
        return height;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setHeight(int newHeight) {
        int oldHeight = height;
        height = newHeight;
        boolean oldHeightESet = heightESet;
        heightESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UMLDiagramPackage.ABSTRACT_NODE__HEIGHT, oldHeight, height, !oldHeightESet));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void unsetHeight() {
        int oldHeight = height;
        boolean oldHeightESet = heightESet;
        height = HEIGHT_EDEFAULT;
        heightESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, UMLDiagramPackage.ABSTRACT_NODE__HEIGHT, oldHeight, HEIGHT_EDEFAULT, oldHeightESet));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetHeight() {
        return heightESet;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EList<AbstractConnection> getIncomingConnectionList() {
        if (incomingConnectionList == null) {
            incomingConnectionList = new EObjectEList<AbstractConnection>(AbstractConnection.class, this, UMLDiagramPackage.ABSTRACT_NODE__INCOMING_CONNECTION_LIST);
        }
        return incomingConnectionList;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EList<Map> getSourceAnchorMap() {
        if (sourceAnchorMap == null) {
            sourceAnchorMap = new EObjectContainmentEList<Map>(Map.class, this, UMLDiagramPackage.ABSTRACT_NODE__SOURCE_ANCHOR_MAP);
        }
        return sourceAnchorMap;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EList<AbstractConnection> getOutgoingConnectionList() {
        if (outgoingConnectionList == null) {
            outgoingConnectionList = new EObjectEList<AbstractConnection>(AbstractConnection.class, this, UMLDiagramPackage.ABSTRACT_NODE__OUTGOING_CONNECTION_LIST);
        }
        return outgoingConnectionList;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EList<Map> getTargetAnchorMap() {
        if (targetAnchorMap == null) {
            targetAnchorMap = new EObjectContainmentEList<Map>(Map.class, this, UMLDiagramPackage.ABSTRACT_NODE__TARGET_ANCHOR_MAP);
        }
        return targetAnchorMap;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public boolean isIsContainer() {
        return isContainer;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setIsContainer(boolean newIsContainer) {
        boolean oldIsContainer = isContainer;
        isContainer = newIsContainer;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UMLDiagramPackage.ABSTRACT_NODE__IS_CONTAINER, oldIsContainer, isContainer));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public NodeType getNodeType() {
        return nodeType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setNodeType(NodeType newNodeType) {
        NodeType oldNodeType = nodeType;
        nodeType = newNodeType == null ? NODE_TYPE_EDEFAULT : newNodeType;
        boolean oldNodeTypeESet = nodeTypeESet;
        nodeTypeESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UMLDiagramPackage.ABSTRACT_NODE__NODE_TYPE, oldNodeType, nodeType, !oldNodeTypeESet));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void unsetNodeType() {
        NodeType oldNodeType = nodeType;
        boolean oldNodeTypeESet = nodeTypeESet;
        nodeType = NODE_TYPE_EDEFAULT;
        nodeTypeESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, UMLDiagramPackage.ABSTRACT_NODE__NODE_TYPE, oldNodeType, NODE_TYPE_EDEFAULT, oldNodeTypeESet));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetNodeType() {
        return nodeTypeESet;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case UMLDiagramPackage.ABSTRACT_NODE__SOURCE_ANCHOR_MAP:
                return ((InternalEList<?>)getSourceAnchorMap()).basicRemove(otherEnd, msgs);
            case UMLDiagramPackage.ABSTRACT_NODE__TARGET_ANCHOR_MAP:
                return ((InternalEList<?>)getTargetAnchorMap()).basicRemove(otherEnd, msgs);
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
            case UMLDiagramPackage.ABSTRACT_NODE__WIDTH:
                return new Integer(getWidth());
            case UMLDiagramPackage.ABSTRACT_NODE__HEIGHT:
                return new Integer(getHeight());
            case UMLDiagramPackage.ABSTRACT_NODE__INCOMING_CONNECTION_LIST:
                return getIncomingConnectionList();
            case UMLDiagramPackage.ABSTRACT_NODE__SOURCE_ANCHOR_MAP:
                return getSourceAnchorMap();
            case UMLDiagramPackage.ABSTRACT_NODE__OUTGOING_CONNECTION_LIST:
                return getOutgoingConnectionList();
            case UMLDiagramPackage.ABSTRACT_NODE__TARGET_ANCHOR_MAP:
                return getTargetAnchorMap();
            case UMLDiagramPackage.ABSTRACT_NODE__IS_CONTAINER:
                return isIsContainer() ? Boolean.TRUE : Boolean.FALSE;
            case UMLDiagramPackage.ABSTRACT_NODE__NODE_TYPE:
                return getNodeType();
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
            case UMLDiagramPackage.ABSTRACT_NODE__WIDTH:
                setWidth(((Integer)newValue).intValue());
                return;
            case UMLDiagramPackage.ABSTRACT_NODE__HEIGHT:
                setHeight(((Integer)newValue).intValue());
                return;
            case UMLDiagramPackage.ABSTRACT_NODE__INCOMING_CONNECTION_LIST:
                getIncomingConnectionList().clear();
                getIncomingConnectionList().addAll((Collection<? extends AbstractConnection>)newValue);
                return;
            case UMLDiagramPackage.ABSTRACT_NODE__SOURCE_ANCHOR_MAP:
                getSourceAnchorMap().clear();
                getSourceAnchorMap().addAll((Collection<? extends Map>)newValue);
                return;
            case UMLDiagramPackage.ABSTRACT_NODE__OUTGOING_CONNECTION_LIST:
                getOutgoingConnectionList().clear();
                getOutgoingConnectionList().addAll((Collection<? extends AbstractConnection>)newValue);
                return;
            case UMLDiagramPackage.ABSTRACT_NODE__TARGET_ANCHOR_MAP:
                getTargetAnchorMap().clear();
                getTargetAnchorMap().addAll((Collection<? extends Map>)newValue);
                return;
            case UMLDiagramPackage.ABSTRACT_NODE__IS_CONTAINER:
                setIsContainer(((Boolean)newValue).booleanValue());
                return;
            case UMLDiagramPackage.ABSTRACT_NODE__NODE_TYPE:
                setNodeType((NodeType)newValue);
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
            case UMLDiagramPackage.ABSTRACT_NODE__WIDTH:
                unsetWidth();
                return;
            case UMLDiagramPackage.ABSTRACT_NODE__HEIGHT:
                unsetHeight();
                return;
            case UMLDiagramPackage.ABSTRACT_NODE__INCOMING_CONNECTION_LIST:
                getIncomingConnectionList().clear();
                return;
            case UMLDiagramPackage.ABSTRACT_NODE__SOURCE_ANCHOR_MAP:
                getSourceAnchorMap().clear();
                return;
            case UMLDiagramPackage.ABSTRACT_NODE__OUTGOING_CONNECTION_LIST:
                getOutgoingConnectionList().clear();
                return;
            case UMLDiagramPackage.ABSTRACT_NODE__TARGET_ANCHOR_MAP:
                getTargetAnchorMap().clear();
                return;
            case UMLDiagramPackage.ABSTRACT_NODE__IS_CONTAINER:
                setIsContainer(IS_CONTAINER_EDEFAULT);
                return;
            case UMLDiagramPackage.ABSTRACT_NODE__NODE_TYPE:
                unsetNodeType();
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
            case UMLDiagramPackage.ABSTRACT_NODE__WIDTH:
                return isSetWidth();
            case UMLDiagramPackage.ABSTRACT_NODE__HEIGHT:
                return isSetHeight();
            case UMLDiagramPackage.ABSTRACT_NODE__INCOMING_CONNECTION_LIST:
                return incomingConnectionList != null && !incomingConnectionList.isEmpty();
            case UMLDiagramPackage.ABSTRACT_NODE__SOURCE_ANCHOR_MAP:
                return sourceAnchorMap != null && !sourceAnchorMap.isEmpty();
            case UMLDiagramPackage.ABSTRACT_NODE__OUTGOING_CONNECTION_LIST:
                return outgoingConnectionList != null && !outgoingConnectionList.isEmpty();
            case UMLDiagramPackage.ABSTRACT_NODE__TARGET_ANCHOR_MAP:
                return targetAnchorMap != null && !targetAnchorMap.isEmpty();
            case UMLDiagramPackage.ABSTRACT_NODE__IS_CONTAINER:
                return isContainer != IS_CONTAINER_EDEFAULT;
            case UMLDiagramPackage.ABSTRACT_NODE__NODE_TYPE:
                return isSetNodeType();
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
        result.append(" (width: ");
        if (widthESet) result.append(width); else result.append("<unset>");
        result.append(", height: ");
        if (heightESet) result.append(height); else result.append("<unset>");
        result.append(", isContainer: ");
        result.append(isContainer);
        result.append(", nodeType: ");
        if (nodeTypeESet) result.append(nodeType); else result.append("<unset>");
        result.append(')');
        return result.toString();
    }

} // AbstractNodeImpl
