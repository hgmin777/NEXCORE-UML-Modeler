/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.model.rmdata.impl;

import nexcore.tool.uml.model.rmdata.RMDataPackage;
import nexcore.tool.uml.model.rmdata.RMObject;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>RM Object</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link nexcore.tool.uml.model.rmdata.impl.RMObjectImpl#getRMObjectId <em>
 * RM Object Id</em>}</li>
 * <li>{@link nexcore.tool.uml.model.rmdata.impl.RMObjectImpl#getRMObjectName
 * <em>RM Object Name</em>}</li>
 * <li>{@link nexcore.tool.uml.model.rmdata.impl.RMObjectImpl#getParentId <em>
 * Parent Id</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.rmdata.impl</li>
 * <li>설  명 : RMObjectImpl</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class RMObjectImpl extends EObjectImpl implements RMObject {
    /**
     * The default value of the '{@link #getRMObjectId() <em>RM Object Id</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getRMObjectId()
     * @generated
     * @ordered
     */
    protected static final String RM_OBJECT_ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getRMObjectId() <em>RM Object Id</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getRMObjectId()
     * @generated
     * @ordered
     */
    protected String rmObjectId = RM_OBJECT_ID_EDEFAULT;

    /**
     * The default value of the '{@link #getRMObjectName()
     * <em>RM Object Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getRMObjectName()
     * @generated
     * @ordered
     */
    protected static final String RM_OBJECT_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getRMObjectName()
     * <em>RM Object Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getRMObjectName()
     * @generated
     * @ordered
     */
    protected String rmObjectName = RM_OBJECT_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getParentId() <em>Parent Id</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getParentId()
     * @generated
     * @ordered
     */
    protected static final String PARENT_ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getParentId() <em>Parent Id</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getParentId()
     * @generated
     * @ordered
     */
    protected String parentId = PARENT_ID_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected RMObjectImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return RMDataPackage.Literals.RM_OBJECT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getRMObjectId() {
        return rmObjectId;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setRMObjectId(String newRMObjectId) {
        String oldRMObjectId = rmObjectId;
        rmObjectId = newRMObjectId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                RMDataPackage.RM_OBJECT__RM_OBJECT_ID,
                oldRMObjectId,
                rmObjectId));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getRMObjectName() {
        return rmObjectName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setRMObjectName(String newRMObjectName) {
        String oldRMObjectName = rmObjectName;
        rmObjectName = newRMObjectName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                RMDataPackage.RM_OBJECT__RM_OBJECT_NAME,
                oldRMObjectName,
                rmObjectName));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setParentId(String newParentId) {
        String oldParentId = parentId;
        parentId = newParentId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                RMDataPackage.RM_OBJECT__PARENT_ID,
                oldParentId,
                parentId));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case RMDataPackage.RM_OBJECT__RM_OBJECT_ID:
                return getRMObjectId();
            case RMDataPackage.RM_OBJECT__RM_OBJECT_NAME:
                return getRMObjectName();
            case RMDataPackage.RM_OBJECT__PARENT_ID:
                return getParentId();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case RMDataPackage.RM_OBJECT__RM_OBJECT_ID:
                setRMObjectId((String) newValue);
                return;
            case RMDataPackage.RM_OBJECT__RM_OBJECT_NAME:
                setRMObjectName((String) newValue);
                return;
            case RMDataPackage.RM_OBJECT__PARENT_ID:
                setParentId((String) newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case RMDataPackage.RM_OBJECT__RM_OBJECT_ID:
                setRMObjectId(RM_OBJECT_ID_EDEFAULT);
                return;
            case RMDataPackage.RM_OBJECT__RM_OBJECT_NAME:
                setRMObjectName(RM_OBJECT_NAME_EDEFAULT);
                return;
            case RMDataPackage.RM_OBJECT__PARENT_ID:
                setParentId(PARENT_ID_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case RMDataPackage.RM_OBJECT__RM_OBJECT_ID:
                return RM_OBJECT_ID_EDEFAULT == null ? rmObjectId != null : !RM_OBJECT_ID_EDEFAULT.equals(rmObjectId);
            case RMDataPackage.RM_OBJECT__RM_OBJECT_NAME:
                return RM_OBJECT_NAME_EDEFAULT == null ? rmObjectName != null
                : !RM_OBJECT_NAME_EDEFAULT.equals(rmObjectName);
            case RMDataPackage.RM_OBJECT__PARENT_ID:
                return PARENT_ID_EDEFAULT == null ? parentId != null : !PARENT_ID_EDEFAULT.equals(parentId);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy())
            return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (RMObjectId: ");
        result.append(rmObjectId);
        result.append(", RMObjectName: ");
        result.append(rmObjectName);
        result.append(", ParentId: ");
        result.append(parentId);
        result.append(')');
        return result.toString();
    }

} // RMObjectImpl
