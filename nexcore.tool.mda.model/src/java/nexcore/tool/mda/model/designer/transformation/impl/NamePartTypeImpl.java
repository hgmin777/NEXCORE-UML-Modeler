/*
 * Copyright (c) 2010 SK C&C Co., Ltd. All rights reserved.
 * 
 * This software is the confidential and proprietary information of SK C&C. You
 * shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK
 * C&C.
 */

package nexcore.tool.mda.model.designer.transformation.impl;

import nexcore.tool.mda.model.designer.transformation.CapitalizationType;
import nexcore.tool.mda.model.designer.transformation.NameKeywordType;
import nexcore.tool.mda.model.designer.transformation.NamePartType;
import nexcore.tool.mda.model.designer.transformation.TransformationPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Name Part Type</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.impl.NamePartTypeImpl#getName
 * <em>Name</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.impl.NamePartTypeImpl#getCapitalization
 * <em>Capitalization</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class NamePartTypeImpl extends EObjectImpl implements NamePartType {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = "Copyright (c) 2010 SK C&C Co., Ltd. All rights reserved.\r\n\r\nThis software is the confidential and proprietary information of SK C&C.\r\nYou shall not disclose such confidential information and shall use it\r\nonly in accordance with the terms of the license agreement you entered into\r\nwith SK C&C.";

    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final NameKeywordType NAME_EDEFAULT = NameKeywordType.BASE;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected NameKeywordType name = NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getCapitalization()
     * <em>Capitalization</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getCapitalization()
     * @generated
     * @ordered
     */
    protected static final CapitalizationType CAPITALIZATION_EDEFAULT = CapitalizationType.NONE;

    /**
     * The cached value of the '{@link #getCapitalization()
     * <em>Capitalization</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getCapitalization()
     * @generated
     * @ordered
     */
    protected CapitalizationType capitalization = CAPITALIZATION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected NamePartTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TransformationPackage.Literals.NAME_PART_TYPE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NameKeywordType getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setName(NameKeywordType newName) {
        NameKeywordType oldName = name;
        name = newName == null ? NAME_EDEFAULT : newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.NAME_PART_TYPE__NAME,
                oldName,
                name));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public CapitalizationType getCapitalization() {
        return capitalization;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setCapitalization(CapitalizationType newCapitalization) {
        CapitalizationType oldCapitalization = capitalization;
        capitalization = newCapitalization == null ? CAPITALIZATION_EDEFAULT : newCapitalization;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.NAME_PART_TYPE__CAPITALIZATION,
                oldCapitalization,
                capitalization));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case TransformationPackage.NAME_PART_TYPE__NAME:
                return getName();
            case TransformationPackage.NAME_PART_TYPE__CAPITALIZATION:
                return getCapitalization();
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
            case TransformationPackage.NAME_PART_TYPE__NAME:
                setName((NameKeywordType) newValue);
                return;
            case TransformationPackage.NAME_PART_TYPE__CAPITALIZATION:
                setCapitalization((CapitalizationType) newValue);
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
            case TransformationPackage.NAME_PART_TYPE__NAME:
                setName(NAME_EDEFAULT);
                return;
            case TransformationPackage.NAME_PART_TYPE__CAPITALIZATION:
                setCapitalization(CAPITALIZATION_EDEFAULT);
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
            case TransformationPackage.NAME_PART_TYPE__NAME:
                return name != NAME_EDEFAULT;
            case TransformationPackage.NAME_PART_TYPE__CAPITALIZATION:
                return capitalization != CAPITALIZATION_EDEFAULT;
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
        result.append(" (name: ");
        result.append(name);
        result.append(", capitalization: ");
        result.append(capitalization);
        result.append(')');
        return result.toString();
    }

} // NamePartTypeImpl
