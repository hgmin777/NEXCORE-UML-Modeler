/**
 * Copyright (c) 2012 SK C&C Co., Ltd. All rights reserved. 
 * 
 * This software is the confidential and proprietary information of SK C&C. 
 * You shall not disclose such confidential information and shall use it 
 * only in accordance with the terms of the license agreement you entered into 
 * with SK C&C.
 */
package nexcore.tool.mda.model.developer.reverseTransformation.impl;

import nexcore.tool.mda.model.developer.reverseTransformation.ElementRule;
import nexcore.tool.mda.model.developer.reverseTransformation.ReverseTransformationPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Element Rule</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.impl.ElementRuleImpl#getName <em>Name</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.impl.ElementRuleImpl#getPrefix <em>Prefix</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.impl.ElementRuleImpl#getPostfix <em>Postfix</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.impl.ElementRuleImpl#isCreateYN <em>Create YN</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.impl.ElementRuleImpl#getStereotype <em>Stereotype</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ElementRuleImpl extends EObjectImpl implements ElementRule {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2012 SK C&C Co., Ltd. All rights reserved. \r\n\r\nThis software is the confidential and proprietary information of SK C&C. \r\nYou shall not disclose such confidential information and shall use it \r\nonly in accordance with the terms of the license agreement you entered into \r\nwith SK C&C.";

    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getPrefix() <em>Prefix</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPrefix()
     * @generated
     * @ordered
     */
    protected static final String PREFIX_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPrefix() <em>Prefix</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPrefix()
     * @generated
     * @ordered
     */
    protected String prefix = PREFIX_EDEFAULT;

    /**
     * The default value of the '{@link #getPostfix() <em>Postfix</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPostfix()
     * @generated
     * @ordered
     */
    protected static final String POSTFIX_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPostfix() <em>Postfix</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPostfix()
     * @generated
     * @ordered
     */
    protected String postfix = POSTFIX_EDEFAULT;

    /**
     * The default value of the '{@link #isCreateYN() <em>Create YN</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isCreateYN()
     * @generated
     * @ordered
     */
    protected static final boolean CREATE_YN_EDEFAULT = true;

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
     * The default value of the '{@link #getStereotype() <em>Stereotype</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStereotype()
     * @generated
     * @ordered
     */
    protected static final String STEREOTYPE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getStereotype() <em>Stereotype</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStereotype()
     * @generated
     * @ordered
     */
    protected String stereotype = STEREOTYPE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ElementRuleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ReverseTransformationPackage.Literals.ELEMENT_RULE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ReverseTransformationPackage.ELEMENT_RULE__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPrefix(String newPrefix) {
        String oldPrefix = prefix;
        prefix = newPrefix;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ReverseTransformationPackage.ELEMENT_RULE__PREFIX, oldPrefix, prefix));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getPostfix() {
        return postfix;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPostfix(String newPostfix) {
        String oldPostfix = postfix;
        postfix = newPostfix;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ReverseTransformationPackage.ELEMENT_RULE__POSTFIX, oldPostfix, postfix));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ReverseTransformationPackage.ELEMENT_RULE__CREATE_YN, oldCreateYN, createYN));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getStereotype() {
        return stereotype;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setStereotype(String newStereotype) {
        String oldStereotype = stereotype;
        stereotype = newStereotype;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ReverseTransformationPackage.ELEMENT_RULE__STEREOTYPE, oldStereotype, stereotype));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ReverseTransformationPackage.ELEMENT_RULE__NAME:
                return getName();
            case ReverseTransformationPackage.ELEMENT_RULE__PREFIX:
                return getPrefix();
            case ReverseTransformationPackage.ELEMENT_RULE__POSTFIX:
                return getPostfix();
            case ReverseTransformationPackage.ELEMENT_RULE__CREATE_YN:
                return isCreateYN();
            case ReverseTransformationPackage.ELEMENT_RULE__STEREOTYPE:
                return getStereotype();
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
            case ReverseTransformationPackage.ELEMENT_RULE__NAME:
                setName((String)newValue);
                return;
            case ReverseTransformationPackage.ELEMENT_RULE__PREFIX:
                setPrefix((String)newValue);
                return;
            case ReverseTransformationPackage.ELEMENT_RULE__POSTFIX:
                setPostfix((String)newValue);
                return;
            case ReverseTransformationPackage.ELEMENT_RULE__CREATE_YN:
                setCreateYN((Boolean)newValue);
                return;
            case ReverseTransformationPackage.ELEMENT_RULE__STEREOTYPE:
                setStereotype((String)newValue);
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
            case ReverseTransformationPackage.ELEMENT_RULE__NAME:
                setName(NAME_EDEFAULT);
                return;
            case ReverseTransformationPackage.ELEMENT_RULE__PREFIX:
                setPrefix(PREFIX_EDEFAULT);
                return;
            case ReverseTransformationPackage.ELEMENT_RULE__POSTFIX:
                setPostfix(POSTFIX_EDEFAULT);
                return;
            case ReverseTransformationPackage.ELEMENT_RULE__CREATE_YN:
                setCreateYN(CREATE_YN_EDEFAULT);
                return;
            case ReverseTransformationPackage.ELEMENT_RULE__STEREOTYPE:
                setStereotype(STEREOTYPE_EDEFAULT);
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
            case ReverseTransformationPackage.ELEMENT_RULE__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case ReverseTransformationPackage.ELEMENT_RULE__PREFIX:
                return PREFIX_EDEFAULT == null ? prefix != null : !PREFIX_EDEFAULT.equals(prefix);
            case ReverseTransformationPackage.ELEMENT_RULE__POSTFIX:
                return POSTFIX_EDEFAULT == null ? postfix != null : !POSTFIX_EDEFAULT.equals(postfix);
            case ReverseTransformationPackage.ELEMENT_RULE__CREATE_YN:
                return createYN != CREATE_YN_EDEFAULT;
            case ReverseTransformationPackage.ELEMENT_RULE__STEREOTYPE:
                return STEREOTYPE_EDEFAULT == null ? stereotype != null : !STEREOTYPE_EDEFAULT.equals(stereotype);
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
        result.append(" (name: ");
        result.append(name);
        result.append(", prefix: ");
        result.append(prefix);
        result.append(", postfix: ");
        result.append(postfix);
        result.append(", createYN: ");
        result.append(createYN);
        result.append(", stereotype: ");
        result.append(stereotype);
        result.append(')');
        return result.toString();
    }

} //ElementRuleImpl
