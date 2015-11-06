/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.mda.model.designer.transformation.impl;

import nexcore.tool.mda.model.designer.transformation.NamePartType;
import nexcore.tool.mda.model.designer.transformation.NameType;
import nexcore.tool.mda.model.designer.transformation.TransformationPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Name Type</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.impl.NameTypeImpl#getPrefix
 * <em>Prefix</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.impl.NameTypeImpl#getNamePart
 * <em>Name Part</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.impl.NameTypeImpl#getPostfix
 * <em>Postfix</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class NameTypeImpl extends EObjectImpl implements NameType {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = "";

    /**
     * The default value of the '{@link #getPrefix() <em>Prefix</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getPrefix()
     * @generated
     * @ordered
     */
    protected static final String PREFIX_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPrefix() <em>Prefix</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getPrefix()
     * @generated
     * @ordered
     */
    protected String prefix = PREFIX_EDEFAULT;

    /**
     * The cached value of the '{@link #getNamePart() <em>Name Part</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getNamePart()
     * @generated
     * @ordered
     */
    protected NamePartType namePart;

    /**
     * The default value of the '{@link #getPostfix() <em>Postfix</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getPostfix()
     * @generated
     * @ordered
     */
    protected static final String POSTFIX_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPostfix() <em>Postfix</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getPostfix()
     * @generated
     * @ordered
     */
    protected String postfix = POSTFIX_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected NameTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TransformationPackage.Literals.NAME_TYPE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setPrefix(String newPrefix) {
        String oldPrefix = prefix;
        prefix = newPrefix;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.NAME_TYPE__PREFIX,
                oldPrefix,
                prefix));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NamePartType getNamePart() {
        return namePart;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetNamePart(NamePartType newNamePart, NotificationChain msgs) {
        NamePartType oldNamePart = namePart;
        namePart = newNamePart;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.NAME_TYPE__NAME_PART,
                oldNamePart,
                newNamePart);
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
    public void setNamePart(NamePartType newNamePart) {
        if (newNamePart != namePart) {
            NotificationChain msgs = null;
            if (namePart != null)
                msgs = ((InternalEObject) namePart).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
                    - TransformationPackage.NAME_TYPE__NAME_PART, null, msgs);
            if (newNamePart != null)
                msgs = ((InternalEObject) newNamePart).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
                    - TransformationPackage.NAME_TYPE__NAME_PART, null, msgs);
            msgs = basicSetNamePart(newNamePart, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.NAME_TYPE__NAME_PART,
                newNamePart,
                newNamePart));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getPostfix() {
        return postfix;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setPostfix(String newPostfix) {
        String oldPostfix = postfix;
        postfix = newPostfix;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.NAME_TYPE__POSTFIX,
                oldPostfix,
                postfix));
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
            case TransformationPackage.NAME_TYPE__NAME_PART:
                return basicSetNamePart(null, msgs);
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
            case TransformationPackage.NAME_TYPE__PREFIX:
                return getPrefix();
            case TransformationPackage.NAME_TYPE__NAME_PART:
                return getNamePart();
            case TransformationPackage.NAME_TYPE__POSTFIX:
                return getPostfix();
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
            case TransformationPackage.NAME_TYPE__PREFIX:
                setPrefix((String) newValue);
                return;
            case TransformationPackage.NAME_TYPE__NAME_PART:
                setNamePart((NamePartType) newValue);
                return;
            case TransformationPackage.NAME_TYPE__POSTFIX:
                setPostfix((String) newValue);
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
            case TransformationPackage.NAME_TYPE__PREFIX:
                setPrefix(PREFIX_EDEFAULT);
                return;
            case TransformationPackage.NAME_TYPE__NAME_PART:
                setNamePart((NamePartType) null);
                return;
            case TransformationPackage.NAME_TYPE__POSTFIX:
                setPostfix(POSTFIX_EDEFAULT);
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
            case TransformationPackage.NAME_TYPE__PREFIX:
                return PREFIX_EDEFAULT == null ? prefix != null : !PREFIX_EDEFAULT.equals(prefix);
            case TransformationPackage.NAME_TYPE__NAME_PART:
                return namePart != null;
            case TransformationPackage.NAME_TYPE__POSTFIX:
                return POSTFIX_EDEFAULT == null ? postfix != null : !POSTFIX_EDEFAULT.equals(postfix);
        }
        return super.eIsSet(featureID);
    }

} // NameTypeImpl
