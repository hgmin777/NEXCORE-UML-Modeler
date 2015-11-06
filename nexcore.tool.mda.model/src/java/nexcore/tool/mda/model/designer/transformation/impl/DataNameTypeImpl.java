/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.mda.model.designer.transformation.impl;

import nexcore.tool.mda.model.designer.transformation.DataNameType;
import nexcore.tool.mda.model.designer.transformation.TransformationPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Data Name Type</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.impl.DataNameTypeImpl#getDataName
 * <em>Data Name</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class DataNameTypeImpl extends EObjectImpl implements DataNameType {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = "";

    /**
     * The default value of the '{@link #getDataName() <em>Data Name</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getDataName()
     * @generated
     * @ordered
     */
    protected static final String DATA_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDataName() <em>Data Name</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getDataName()
     * @generated
     * @ordered
     */
    protected String dataName = DATA_NAME_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected DataNameTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TransformationPackage.Literals.DATA_NAME_TYPE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getDataName() {
        return dataName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setDataName(String newDataName) {
        String oldDataName = dataName;
        dataName = newDataName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.DATA_NAME_TYPE__DATA_NAME,
                oldDataName,
                dataName));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case TransformationPackage.DATA_NAME_TYPE__DATA_NAME:
                return getDataName();
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
            case TransformationPackage.DATA_NAME_TYPE__DATA_NAME:
                setDataName((String) newValue);
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
            case TransformationPackage.DATA_NAME_TYPE__DATA_NAME:
                setDataName(DATA_NAME_EDEFAULT);
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
            case TransformationPackage.DATA_NAME_TYPE__DATA_NAME:
                return DATA_NAME_EDEFAULT == null ? dataName != null : !DATA_NAME_EDEFAULT.equals(dataName);
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
        result.append(" (dataName: ");
        result.append(dataName);
        result.append(')');
        return result.toString();
    }

} // DataNameTypeImpl
