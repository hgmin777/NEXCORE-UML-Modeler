/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.model.usecasedisplayId.impl;

import nexcore.tool.uml.model.usecasedisplayId.UseCaseDisplayId;
import nexcore.tool.uml.model.usecasedisplayId.UsecasedisplayIdPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.EAnnotationImpl;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Use Case Display Id</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link nexcore.tool.uml.model.usecasedisplayId.impl.UseCaseDisplayIdImpl#getDisplayId <em>Display Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.usecasedisplayId.impl</li>
 * <li>설  명 : UseCaseDisplayIdImpl</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class UseCaseDisplayIdImpl extends EAnnotationImpl implements UseCaseDisplayId {
    /**
     * The default value of the '{@link #getDisplayId() <em>Display Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDisplayId()
     * @generated
     * @ordered
     */
    protected static final String DISPLAY_ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDisplayId() <em>Display Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDisplayId()
     * @generated
     * @ordered
     */
    protected String displayId = DISPLAY_ID_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected UseCaseDisplayIdImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return UsecasedisplayIdPackage.Literals.USE_CASE_DISPLAY_ID;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getDisplayId() {
        return displayId;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDisplayId(String newDisplayId) {
        String oldDisplayId = displayId;
        displayId = newDisplayId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UsecasedisplayIdPackage.USE_CASE_DISPLAY_ID__DISPLAY_ID, oldDisplayId, displayId));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case UsecasedisplayIdPackage.USE_CASE_DISPLAY_ID__DISPLAY_ID:
                return getDisplayId();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case UsecasedisplayIdPackage.USE_CASE_DISPLAY_ID__DISPLAY_ID:
                setDisplayId((String)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case UsecasedisplayIdPackage.USE_CASE_DISPLAY_ID__DISPLAY_ID:
                setDisplayId(DISPLAY_ID_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case UsecasedisplayIdPackage.USE_CASE_DISPLAY_ID__DISPLAY_ID:
                return DISPLAY_ID_EDEFAULT == null ? displayId != null : !DISPLAY_ID_EDEFAULT.equals(displayId);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (DisplayId: ");
        result.append(displayId);
        result.append(')');
        return result.toString();
    }

} //UseCaseDisplayIdImpl
