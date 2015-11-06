/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.mda.model.developer.reverseTransformation.impl;

import java.util.Collection;

import nexcore.tool.mda.model.developer.reverseTransformation.ClassRule;
import nexcore.tool.mda.model.developer.reverseTransformation.ElementRule;
import nexcore.tool.mda.model.developer.reverseTransformation.ReverseTransformationPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Class Rule</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.impl.ClassRuleImpl#getPackageName <em>Package Name</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.impl.ClassRuleImpl#isGenerateGetterSetter <em>Generate Getter Setter</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.impl.ClassRuleImpl#isGenerateConstructor <em>Generate Constructor</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.impl.ClassRuleImpl#isGenerateDestructor <em>Generate Destructor</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.impl.ClassRuleImpl#getOperationRules <em>Operation Rules</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.impl.ClassRuleImpl#getAttributeRules <em>Attribute Rules</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ClassRuleImpl extends ElementRuleImpl implements ClassRule {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "";

    /**
     * The default value of the '{@link #getPackageName() <em>Package Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPackageName()
     * @generated
     * @ordered
     */
    protected static final String PACKAGE_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPackageName() <em>Package Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPackageName()
     * @generated
     * @ordered
     */
    protected String packageName = PACKAGE_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #isGenerateGetterSetter() <em>Generate Getter Setter</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isGenerateGetterSetter()
     * @generated
     * @ordered
     */
    protected static final boolean GENERATE_GETTER_SETTER_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isGenerateGetterSetter() <em>Generate Getter Setter</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isGenerateGetterSetter()
     * @generated
     * @ordered
     */
    protected boolean generateGetterSetter = GENERATE_GETTER_SETTER_EDEFAULT;

    /**
     * The default value of the '{@link #isGenerateConstructor() <em>Generate Constructor</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isGenerateConstructor()
     * @generated
     * @ordered
     */
    protected static final boolean GENERATE_CONSTRUCTOR_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isGenerateConstructor() <em>Generate Constructor</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isGenerateConstructor()
     * @generated
     * @ordered
     */
    protected boolean generateConstructor = GENERATE_CONSTRUCTOR_EDEFAULT;

    /**
     * The default value of the '{@link #isGenerateDestructor() <em>Generate Destructor</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isGenerateDestructor()
     * @generated
     * @ordered
     */
    protected static final boolean GENERATE_DESTRUCTOR_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isGenerateDestructor() <em>Generate Destructor</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isGenerateDestructor()
     * @generated
     * @ordered
     */
    protected boolean generateDestructor = GENERATE_DESTRUCTOR_EDEFAULT;

    /**
     * The cached value of the '{@link #getOperationRules() <em>Operation Rules</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOperationRules()
     * @generated
     * @ordered
     */
    protected EList<ElementRule> operationRules;

    /**
     * The cached value of the '{@link #getAttributeRules() <em>Attribute Rules</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAttributeRules()
     * @generated
     * @ordered
     */
    protected EList<ElementRule> attributeRules;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ClassRuleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ReverseTransformationPackage.Literals.CLASS_RULE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPackageName(String newPackageName) {
        String oldPackageName = packageName;
        packageName = newPackageName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ReverseTransformationPackage.CLASS_RULE__PACKAGE_NAME, oldPackageName, packageName));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isGenerateGetterSetter() {
        return generateGetterSetter;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setGenerateGetterSetter(boolean newGenerateGetterSetter) {
        boolean oldGenerateGetterSetter = generateGetterSetter;
        generateGetterSetter = newGenerateGetterSetter;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ReverseTransformationPackage.CLASS_RULE__GENERATE_GETTER_SETTER, oldGenerateGetterSetter, generateGetterSetter));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isGenerateConstructor() {
        return generateConstructor;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setGenerateConstructor(boolean newGenerateConstructor) {
        boolean oldGenerateConstructor = generateConstructor;
        generateConstructor = newGenerateConstructor;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ReverseTransformationPackage.CLASS_RULE__GENERATE_CONSTRUCTOR, oldGenerateConstructor, generateConstructor));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isGenerateDestructor() {
        return generateDestructor;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setGenerateDestructor(boolean newGenerateDestructor) {
        boolean oldGenerateDestructor = generateDestructor;
        generateDestructor = newGenerateDestructor;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ReverseTransformationPackage.CLASS_RULE__GENERATE_DESTRUCTOR, oldGenerateDestructor, generateDestructor));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<ElementRule> getOperationRules() {
        if (operationRules == null) {
            operationRules = new EObjectContainmentEList<ElementRule>(ElementRule.class, this, ReverseTransformationPackage.CLASS_RULE__OPERATION_RULES);
        }
        return operationRules;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<ElementRule> getAttributeRules() {
        if (attributeRules == null) {
            attributeRules = new EObjectContainmentEList<ElementRule>(ElementRule.class, this, ReverseTransformationPackage.CLASS_RULE__ATTRIBUTE_RULES);
        }
        return attributeRules;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ReverseTransformationPackage.CLASS_RULE__OPERATION_RULES:
                return ((InternalEList<?>)getOperationRules()).basicRemove(otherEnd, msgs);
            case ReverseTransformationPackage.CLASS_RULE__ATTRIBUTE_RULES:
                return ((InternalEList<?>)getAttributeRules()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ReverseTransformationPackage.CLASS_RULE__PACKAGE_NAME:
                return getPackageName();
            case ReverseTransformationPackage.CLASS_RULE__GENERATE_GETTER_SETTER:
                return isGenerateGetterSetter();
            case ReverseTransformationPackage.CLASS_RULE__GENERATE_CONSTRUCTOR:
                return isGenerateConstructor();
            case ReverseTransformationPackage.CLASS_RULE__GENERATE_DESTRUCTOR:
                return isGenerateDestructor();
            case ReverseTransformationPackage.CLASS_RULE__OPERATION_RULES:
                return getOperationRules();
            case ReverseTransformationPackage.CLASS_RULE__ATTRIBUTE_RULES:
                return getAttributeRules();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case ReverseTransformationPackage.CLASS_RULE__PACKAGE_NAME:
                setPackageName((String)newValue);
                return;
            case ReverseTransformationPackage.CLASS_RULE__GENERATE_GETTER_SETTER:
                setGenerateGetterSetter((Boolean)newValue);
                return;
            case ReverseTransformationPackage.CLASS_RULE__GENERATE_CONSTRUCTOR:
                setGenerateConstructor((Boolean)newValue);
                return;
            case ReverseTransformationPackage.CLASS_RULE__GENERATE_DESTRUCTOR:
                setGenerateDestructor((Boolean)newValue);
                return;
            case ReverseTransformationPackage.CLASS_RULE__OPERATION_RULES:
                getOperationRules().clear();
                getOperationRules().addAll((Collection<? extends ElementRule>)newValue);
                return;
            case ReverseTransformationPackage.CLASS_RULE__ATTRIBUTE_RULES:
                getAttributeRules().clear();
                getAttributeRules().addAll((Collection<? extends ElementRule>)newValue);
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
            case ReverseTransformationPackage.CLASS_RULE__PACKAGE_NAME:
                setPackageName(PACKAGE_NAME_EDEFAULT);
                return;
            case ReverseTransformationPackage.CLASS_RULE__GENERATE_GETTER_SETTER:
                setGenerateGetterSetter(GENERATE_GETTER_SETTER_EDEFAULT);
                return;
            case ReverseTransformationPackage.CLASS_RULE__GENERATE_CONSTRUCTOR:
                setGenerateConstructor(GENERATE_CONSTRUCTOR_EDEFAULT);
                return;
            case ReverseTransformationPackage.CLASS_RULE__GENERATE_DESTRUCTOR:
                setGenerateDestructor(GENERATE_DESTRUCTOR_EDEFAULT);
                return;
            case ReverseTransformationPackage.CLASS_RULE__OPERATION_RULES:
                getOperationRules().clear();
                return;
            case ReverseTransformationPackage.CLASS_RULE__ATTRIBUTE_RULES:
                getAttributeRules().clear();
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
            case ReverseTransformationPackage.CLASS_RULE__PACKAGE_NAME:
                return PACKAGE_NAME_EDEFAULT == null ? packageName != null : !PACKAGE_NAME_EDEFAULT.equals(packageName);
            case ReverseTransformationPackage.CLASS_RULE__GENERATE_GETTER_SETTER:
                return generateGetterSetter != GENERATE_GETTER_SETTER_EDEFAULT;
            case ReverseTransformationPackage.CLASS_RULE__GENERATE_CONSTRUCTOR:
                return generateConstructor != GENERATE_CONSTRUCTOR_EDEFAULT;
            case ReverseTransformationPackage.CLASS_RULE__GENERATE_DESTRUCTOR:
                return generateDestructor != GENERATE_DESTRUCTOR_EDEFAULT;
            case ReverseTransformationPackage.CLASS_RULE__OPERATION_RULES:
                return operationRules != null && !operationRules.isEmpty();
            case ReverseTransformationPackage.CLASS_RULE__ATTRIBUTE_RULES:
                return attributeRules != null && !attributeRules.isEmpty();
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
        result.append(" (packageName: ");
        result.append(packageName);
        result.append(", generateGetterSetter: ");
        result.append(generateGetterSetter);
        result.append(", generateConstructor: ");
        result.append(generateConstructor);
        result.append(", generateDestructor: ");
        result.append(generateDestructor);
        result.append(')');
        return result.toString();
    }

} //ClassRuleImpl
