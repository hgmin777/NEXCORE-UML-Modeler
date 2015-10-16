/**
 * Copyright (c) 2012 SK C&C Co., Ltd. All rights reserved. 
 * 
 * This software is the confidential and proprietary information of SK C&C. 
 * You shall not disclose such confidential information and shall use it 
 * only in accordance with the terms of the license agreement you entered into 
 * with SK C&C.
 */
package nexcore.tool.mda.model.developer.reverseTransformation.impl;

import nexcore.tool.mda.model.developer.reverseTransformation.DiagramRule;
import nexcore.tool.mda.model.developer.reverseTransformation.ReverseTransformationPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Diagram Rule</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.impl.DiagramRuleImpl#getBasePackage <em>Base Package</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.impl.DiagramRuleImpl#getPackageDepth <em>Package Depth</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.impl.DiagramRuleImpl#isCreateYN <em>Create YN</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DiagramRuleImpl extends EObjectImpl implements DiagramRule {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2012 SK C&C Co., Ltd. All rights reserved. \r\n\r\nThis software is the confidential and proprietary information of SK C&C. \r\nYou shall not disclose such confidential information and shall use it \r\nonly in accordance with the terms of the license agreement you entered into \r\nwith SK C&C.";

    /**
     * The default value of the '{@link #getBasePackage() <em>Base Package</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBasePackage()
     * @generated
     * @ordered
     */
    protected static final String BASE_PACKAGE_EDEFAULT = "";

    /**
     * The cached value of the '{@link #getBasePackage() <em>Base Package</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBasePackage()
     * @generated
     * @ordered
     */
    protected String basePackage = BASE_PACKAGE_EDEFAULT;

    /**
     * The default value of the '{@link #getPackageDepth() <em>Package Depth</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPackageDepth()
     * @generated
     * @ordered
     */
    protected static final int PACKAGE_DEPTH_EDEFAULT = 1;

    /**
     * The cached value of the '{@link #getPackageDepth() <em>Package Depth</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPackageDepth()
     * @generated
     * @ordered
     */
    protected int packageDepth = PACKAGE_DEPTH_EDEFAULT;

    /**
     * The default value of the '{@link #isCreateYN() <em>Create YN</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isCreateYN()
     * @generated
     * @ordered
     */
    protected static final boolean CREATE_YN_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isCreateYN() <em>Create YN</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isCreateYN()
     * @generated
     * @ordered
     */
    protected boolean createYN = CREATE_YN_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected DiagramRuleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ReverseTransformationPackage.Literals.DIAGRAM_RULE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getBasePackage() {
        return basePackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setBasePackage(String newBasePackage) {
        String oldBasePackage = basePackage;
        basePackage = newBasePackage;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ReverseTransformationPackage.DIAGRAM_RULE__BASE_PACKAGE, oldBasePackage, basePackage));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getPackageDepth() {
        return packageDepth;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPackageDepth(int newPackageDepth) {
        int oldPackageDepth = packageDepth;
        packageDepth = newPackageDepth;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ReverseTransformationPackage.DIAGRAM_RULE__PACKAGE_DEPTH, oldPackageDepth, packageDepth));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isCreateYN() {
        return createYN;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCreateYN(boolean newCreateYN) {
        boolean oldCreateYN = createYN;
        createYN = newCreateYN;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ReverseTransformationPackage.DIAGRAM_RULE__CREATE_YN, oldCreateYN, createYN));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ReverseTransformationPackage.DIAGRAM_RULE__BASE_PACKAGE:
                return getBasePackage();
            case ReverseTransformationPackage.DIAGRAM_RULE__PACKAGE_DEPTH:
                return getPackageDepth();
            case ReverseTransformationPackage.DIAGRAM_RULE__CREATE_YN:
                return isCreateYN();
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
            case ReverseTransformationPackage.DIAGRAM_RULE__BASE_PACKAGE:
                setBasePackage((String)newValue);
                return;
            case ReverseTransformationPackage.DIAGRAM_RULE__PACKAGE_DEPTH:
                setPackageDepth((Integer)newValue);
                return;
            case ReverseTransformationPackage.DIAGRAM_RULE__CREATE_YN:
                setCreateYN((Boolean)newValue);
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
            case ReverseTransformationPackage.DIAGRAM_RULE__BASE_PACKAGE:
                setBasePackage(BASE_PACKAGE_EDEFAULT);
                return;
            case ReverseTransformationPackage.DIAGRAM_RULE__PACKAGE_DEPTH:
                setPackageDepth(PACKAGE_DEPTH_EDEFAULT);
                return;
            case ReverseTransformationPackage.DIAGRAM_RULE__CREATE_YN:
                setCreateYN(CREATE_YN_EDEFAULT);
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
            case ReverseTransformationPackage.DIAGRAM_RULE__BASE_PACKAGE:
                return BASE_PACKAGE_EDEFAULT == null ? basePackage != null : !BASE_PACKAGE_EDEFAULT.equals(basePackage);
            case ReverseTransformationPackage.DIAGRAM_RULE__PACKAGE_DEPTH:
                return packageDepth != PACKAGE_DEPTH_EDEFAULT;
            case ReverseTransformationPackage.DIAGRAM_RULE__CREATE_YN:
                return createYN != CREATE_YN_EDEFAULT;
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
        result.append(" (basePackage: ");
        result.append(basePackage);
        result.append(", packageDepth: ");
        result.append(packageDepth);
        result.append(", createYN: ");
        result.append(createYN);
        result.append(')');
        return result.toString();
    }

} //DiagramRuleImpl
