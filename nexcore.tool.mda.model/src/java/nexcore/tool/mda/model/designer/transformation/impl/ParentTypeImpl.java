/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.mda.model.designer.transformation.impl;

import java.util.Collection;

import nexcore.tool.mda.model.designer.transformation.ExtensionType;
import nexcore.tool.mda.model.designer.transformation.ParentType;
import nexcore.tool.mda.model.designer.transformation.TransformationPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Parent Type</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.impl.ParentTypeImpl#getTypeName
 * <em>Type Name</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.impl.ParentTypeImpl#getExtensionType
 * <em>Extension Type</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ParentTypeImpl extends EObjectImpl implements ParentType {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = "";

    /**
     * The cached value of the '{@link #getTypeName() <em>Type Name</em>}'
     * attribute list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getTypeName()
     * @generated
     * @ordered
     */
    protected EList<String> typeName;

    /**
     * The default value of the '{@link #getExtensionType()
     * <em>Extension Type</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getExtensionType()
     * @generated
     * @ordered
     */
    protected static final ExtensionType EXTENSION_TYPE_EDEFAULT = ExtensionType.EXTENDS;

    /**
     * The cached value of the '{@link #getExtensionType()
     * <em>Extension Type</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getExtensionType()
     * @generated
     * @ordered
     */
    protected ExtensionType extensionType = EXTENSION_TYPE_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected ParentTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TransformationPackage.Literals.PARENT_TYPE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<String> getTypeName() {
        if (typeName == null) {
            typeName = new EDataTypeUniqueEList<String>(String.class,
                this,
                TransformationPackage.PARENT_TYPE__TYPE_NAME);
        }
        return typeName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ExtensionType getExtensionType() {
        return extensionType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setExtensionType(ExtensionType newExtensionType) {
        ExtensionType oldExtensionType = extensionType;
        extensionType = newExtensionType == null ? EXTENSION_TYPE_EDEFAULT : newExtensionType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.PARENT_TYPE__EXTENSION_TYPE,
                oldExtensionType,
                extensionType));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case TransformationPackage.PARENT_TYPE__TYPE_NAME:
                return getTypeName();
            case TransformationPackage.PARENT_TYPE__EXTENSION_TYPE:
                return getExtensionType();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case TransformationPackage.PARENT_TYPE__TYPE_NAME:
                getTypeName().clear();
                getTypeName().addAll((Collection<? extends String>) newValue);
                return;
            case TransformationPackage.PARENT_TYPE__EXTENSION_TYPE:
                setExtensionType((ExtensionType) newValue);
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
            case TransformationPackage.PARENT_TYPE__TYPE_NAME:
                getTypeName().clear();
                return;
            case TransformationPackage.PARENT_TYPE__EXTENSION_TYPE:
                setExtensionType(EXTENSION_TYPE_EDEFAULT);
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
            case TransformationPackage.PARENT_TYPE__TYPE_NAME:
                return typeName != null && !typeName.isEmpty();
            case TransformationPackage.PARENT_TYPE__EXTENSION_TYPE:
                return extensionType != EXTENSION_TYPE_EDEFAULT;
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
        result.append(" (typeName: ");
        result.append(typeName);
        result.append(", extensionType: ");
        result.append(extensionType);
        result.append(')');
        return result.toString();
    }

} // ParentTypeImpl
