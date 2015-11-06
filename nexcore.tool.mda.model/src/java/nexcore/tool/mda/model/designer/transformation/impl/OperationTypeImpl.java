/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.mda.model.designer.transformation.impl;

import nexcore.tool.mda.model.designer.transformation.NameType;
import nexcore.tool.mda.model.designer.transformation.OperationApplicationType;
import nexcore.tool.mda.model.designer.transformation.OperationType;
import nexcore.tool.mda.model.designer.transformation.TransformationPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Operation Type</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.impl.OperationTypeImpl#getOperationApplicationType
 * <em>Operation Application Type</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.impl.OperationTypeImpl#getAssignableOperationName
 * <em>Assignable Operation Name</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.impl.OperationTypeImpl#getUserDefinedOperationName
 * <em>User Defined Operation Name</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class OperationTypeImpl extends EObjectImpl implements OperationType {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = "";

    /**
     * The default value of the '{@link #getOperationApplicationType()
     * <em>Operation Application Type</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getOperationApplicationType()
     * @generated
     * @ordered
     */
    protected static final OperationApplicationType OPERATION_APPLICATION_TYPE_EDEFAULT = OperationApplicationType.NONE;

    /**
     * The cached value of the '{@link #getOperationApplicationType()
     * <em>Operation Application Type</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getOperationApplicationType()
     * @generated
     * @ordered
     */
    protected OperationApplicationType operationApplicationType = OPERATION_APPLICATION_TYPE_EDEFAULT;

    /**
     * The cached value of the '{@link #getAssignableOperationName()
     * <em>Assignable Operation Name</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getAssignableOperationName()
     * @generated
     * @ordered
     */
    protected NameType assignableOperationName;

    /**
     * The default value of the '{@link #getUserDefinedOperationName()
     * <em>User Defined Operation Name</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getUserDefinedOperationName()
     * @generated
     * @ordered
     */
    protected static final String USER_DEFINED_OPERATION_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getUserDefinedOperationName()
     * <em>User Defined Operation Name</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getUserDefinedOperationName()
     * @generated
     * @ordered
     */
    protected String userDefinedOperationName = USER_DEFINED_OPERATION_NAME_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected OperationTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TransformationPackage.Literals.OPERATION_TYPE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public OperationApplicationType getOperationApplicationType() {
        return operationApplicationType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setOperationApplicationType(OperationApplicationType newOperationApplicationType) {
        OperationApplicationType oldOperationApplicationType = operationApplicationType;
        operationApplicationType = newOperationApplicationType == null ? OPERATION_APPLICATION_TYPE_EDEFAULT
        : newOperationApplicationType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.OPERATION_TYPE__OPERATION_APPLICATION_TYPE,
                oldOperationApplicationType,
                operationApplicationType));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NameType getAssignableOperationName() {
        return assignableOperationName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetAssignableOperationName(NameType newAssignableOperationName, NotificationChain msgs) {
        NameType oldAssignableOperationName = assignableOperationName;
        assignableOperationName = newAssignableOperationName;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.OPERATION_TYPE__ASSIGNABLE_OPERATION_NAME,
                oldAssignableOperationName,
                newAssignableOperationName);
            if (msgs == null)
                msgs = notification;
            else
                msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setAssignableOperationName(NameType newAssignableOperationName) {
        if (newAssignableOperationName != assignableOperationName) {
            NotificationChain msgs = null;
            if (assignableOperationName != null)
                msgs = ((InternalEObject) assignableOperationName).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
                    - TransformationPackage.OPERATION_TYPE__ASSIGNABLE_OPERATION_NAME, null, msgs);
            if (newAssignableOperationName != null)
                msgs = ((InternalEObject) newAssignableOperationName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
                    - TransformationPackage.OPERATION_TYPE__ASSIGNABLE_OPERATION_NAME, null, msgs);
            msgs = basicSetAssignableOperationName(newAssignableOperationName, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.OPERATION_TYPE__ASSIGNABLE_OPERATION_NAME,
                newAssignableOperationName,
                newAssignableOperationName));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getUserDefinedOperationName() {
        return userDefinedOperationName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setUserDefinedOperationName(String newUserDefinedOperationName) {
        String oldUserDefinedOperationName = userDefinedOperationName;
        userDefinedOperationName = newUserDefinedOperationName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.OPERATION_TYPE__USER_DEFINED_OPERATION_NAME,
                oldUserDefinedOperationName,
                userDefinedOperationName));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String toString() {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case TransformationPackage.OPERATION_TYPE__ASSIGNABLE_OPERATION_NAME:
                return basicSetAssignableOperationName(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case TransformationPackage.OPERATION_TYPE__OPERATION_APPLICATION_TYPE:
                return getOperationApplicationType();
            case TransformationPackage.OPERATION_TYPE__ASSIGNABLE_OPERATION_NAME:
                return getAssignableOperationName();
            case TransformationPackage.OPERATION_TYPE__USER_DEFINED_OPERATION_NAME:
                return getUserDefinedOperationName();
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
            case TransformationPackage.OPERATION_TYPE__OPERATION_APPLICATION_TYPE:
                setOperationApplicationType((OperationApplicationType) newValue);
                return;
            case TransformationPackage.OPERATION_TYPE__ASSIGNABLE_OPERATION_NAME:
                setAssignableOperationName((NameType) newValue);
                return;
            case TransformationPackage.OPERATION_TYPE__USER_DEFINED_OPERATION_NAME:
                setUserDefinedOperationName((String) newValue);
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
            case TransformationPackage.OPERATION_TYPE__OPERATION_APPLICATION_TYPE:
                setOperationApplicationType(OPERATION_APPLICATION_TYPE_EDEFAULT);
                return;
            case TransformationPackage.OPERATION_TYPE__ASSIGNABLE_OPERATION_NAME:
                setAssignableOperationName((NameType) null);
                return;
            case TransformationPackage.OPERATION_TYPE__USER_DEFINED_OPERATION_NAME:
                setUserDefinedOperationName(USER_DEFINED_OPERATION_NAME_EDEFAULT);
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
            case TransformationPackage.OPERATION_TYPE__OPERATION_APPLICATION_TYPE:
                return operationApplicationType != OPERATION_APPLICATION_TYPE_EDEFAULT;
            case TransformationPackage.OPERATION_TYPE__ASSIGNABLE_OPERATION_NAME:
                return assignableOperationName != null;
            case TransformationPackage.OPERATION_TYPE__USER_DEFINED_OPERATION_NAME:
                return USER_DEFINED_OPERATION_NAME_EDEFAULT == null ? userDefinedOperationName != null
                : !USER_DEFINED_OPERATION_NAME_EDEFAULT.equals(userDefinedOperationName);
        }
        return super.eIsSet(featureID);
    }

} // OperationTypeImpl
