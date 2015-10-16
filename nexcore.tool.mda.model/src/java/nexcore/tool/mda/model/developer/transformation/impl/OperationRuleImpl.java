/*
 * Copyright (c) 2010 SK C&C Co., Ltd. All rights reserved.
 * 
 * This software is the confidential and proprietary information of SK C&C. You
 * shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK
 * C&C.
 */

package nexcore.tool.mda.model.developer.transformation.impl;

import nexcore.tool.mda.model.developer.transformation.OperationRule;
import nexcore.tool.mda.model.developer.transformation.TransformationPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Operation Rule</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.impl.OperationRuleImpl#getName
 * <em>Name</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.impl.OperationRuleImpl#getStereotype
 * <em>Stereotype</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.impl.OperationRuleImpl#getTemplate
 * <em>Template</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.impl.OperationRuleImpl#getPrefix
 * <em>Prefix</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.impl.OperationRuleImpl#getPostfix
 * <em>Postfix</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.impl.OperationRuleImpl#getVersion
 * <em>Version</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class OperationRuleImpl extends EObjectImpl implements OperationRule {
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
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getStereotype() <em>Stereotype</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getStereotype()
     * @generated
     * @ordered
     */
    protected static final String STEREOTYPE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getStereotype() <em>Stereotype</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getStereotype()
     * @generated
     * @ordered
     */
    protected String stereotype = STEREOTYPE_EDEFAULT;

    /**
     * The default value of the '{@link #getTemplate() <em>Template</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getTemplate()
     * @generated
     * @ordered
     */
    protected static final String TEMPLATE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTemplate() <em>Template</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getTemplate()
     * @generated
     * @ordered
     */
    protected String template = TEMPLATE_EDEFAULT;

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
     * The default value of the '{@link #getVersion() <em>Version</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getVersion()
     * @generated
     * @ordered
     */
    protected static final String VERSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getVersion() <em>Version</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getVersion()
     * @generated
     * @ordered
     */
    protected String version = VERSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected OperationRuleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TransformationPackage.Literals.OPERATION_RULE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.OPERATION_RULE__NAME,
                oldName,
                name));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getStereotype() {
        return stereotype;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setStereotype(String newStereotype) {
        String oldStereotype = stereotype;
        stereotype = newStereotype;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.OPERATION_RULE__STEREOTYPE,
                oldStereotype,
                stereotype));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getTemplate() {
        return template;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setTemplate(String newTemplate) {
        String oldTemplate = template;
        template = newTemplate;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.OPERATION_RULE__TEMPLATE,
                oldTemplate,
                template));
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
                TransformationPackage.OPERATION_RULE__PREFIX,
                oldPrefix,
                prefix));
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
                TransformationPackage.OPERATION_RULE__POSTFIX,
                oldPostfix,
                postfix));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getVersion() {
        return version;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setVersion(String newVersion) {
        String oldVersion = version;
        version = newVersion;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.OPERATION_RULE__VERSION,
                oldVersion,
                version));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case TransformationPackage.OPERATION_RULE__NAME:
                return getName();
            case TransformationPackage.OPERATION_RULE__STEREOTYPE:
                return getStereotype();
            case TransformationPackage.OPERATION_RULE__TEMPLATE:
                return getTemplate();
            case TransformationPackage.OPERATION_RULE__PREFIX:
                return getPrefix();
            case TransformationPackage.OPERATION_RULE__POSTFIX:
                return getPostfix();
            case TransformationPackage.OPERATION_RULE__VERSION:
                return getVersion();
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
            case TransformationPackage.OPERATION_RULE__NAME:
                setName((String) newValue);
                return;
            case TransformationPackage.OPERATION_RULE__STEREOTYPE:
                setStereotype((String) newValue);
                return;
            case TransformationPackage.OPERATION_RULE__TEMPLATE:
                setTemplate((String) newValue);
                return;
            case TransformationPackage.OPERATION_RULE__PREFIX:
                setPrefix((String) newValue);
                return;
            case TransformationPackage.OPERATION_RULE__POSTFIX:
                setPostfix((String) newValue);
                return;
            case TransformationPackage.OPERATION_RULE__VERSION:
                setVersion((String) newValue);
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
            case TransformationPackage.OPERATION_RULE__NAME:
                setName(NAME_EDEFAULT);
                return;
            case TransformationPackage.OPERATION_RULE__STEREOTYPE:
                setStereotype(STEREOTYPE_EDEFAULT);
                return;
            case TransformationPackage.OPERATION_RULE__TEMPLATE:
                setTemplate(TEMPLATE_EDEFAULT);
                return;
            case TransformationPackage.OPERATION_RULE__PREFIX:
                setPrefix(PREFIX_EDEFAULT);
                return;
            case TransformationPackage.OPERATION_RULE__POSTFIX:
                setPostfix(POSTFIX_EDEFAULT);
                return;
            case TransformationPackage.OPERATION_RULE__VERSION:
                setVersion(VERSION_EDEFAULT);
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
            case TransformationPackage.OPERATION_RULE__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case TransformationPackage.OPERATION_RULE__STEREOTYPE:
                return STEREOTYPE_EDEFAULT == null ? stereotype != null : !STEREOTYPE_EDEFAULT.equals(stereotype);
            case TransformationPackage.OPERATION_RULE__TEMPLATE:
                return TEMPLATE_EDEFAULT == null ? template != null : !TEMPLATE_EDEFAULT.equals(template);
            case TransformationPackage.OPERATION_RULE__PREFIX:
                return PREFIX_EDEFAULT == null ? prefix != null : !PREFIX_EDEFAULT.equals(prefix);
            case TransformationPackage.OPERATION_RULE__POSTFIX:
                return POSTFIX_EDEFAULT == null ? postfix != null : !POSTFIX_EDEFAULT.equals(postfix);
            case TransformationPackage.OPERATION_RULE__VERSION:
                return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
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
        result.append(", stereotype: ");
        result.append(stereotype);
        result.append(", template: ");
        result.append(template);
        result.append(", prefix: ");
        result.append(prefix);
        result.append(", postfix: ");
        result.append(postfix);
        result.append(", version: ");
        result.append(version);
        result.append(')');
        return result.toString();
    }

} // OperationRuleImpl
