/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.mda.model.developer.transformation.impl;

import nexcore.tool.mda.model.developer.transformation.ClassRule;
import nexcore.tool.mda.model.developer.transformation.TransformationPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Class Rule</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.impl.ClassRuleImpl#getName
 * <em>Name</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.impl.ClassRuleImpl#getStereotype
 * <em>Stereotype</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.impl.ClassRuleImpl#isGenerateGetterSetter
 * <em>Generate Getter Setter</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.impl.ClassRuleImpl#getExtension
 * <em>Extension</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.impl.ClassRuleImpl#getVersion
 * <em>Version</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.impl.ClassRuleImpl#getTargetFolder
 * <em>Target Folder</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.impl.ClassRuleImpl#getPrefix
 * <em>Prefix</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.impl.ClassRuleImpl#getPostfix
 * <em>Postfix</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.impl.ClassRuleImpl#getTemplate
 * <em>Template</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.impl.ClassRuleImpl#getImplements
 * <em>Implements</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.impl.ClassRuleImpl#getExtends
 * <em>Extends</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ClassRuleImpl extends EObjectImpl implements ClassRule {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = "";

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
     * The default value of the '{@link #isGenerateGetterSetter()
     * <em>Generate Getter Setter</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #isGenerateGetterSetter()
     * @generated
     * @ordered
     */
    protected static final boolean GENERATE_GETTER_SETTER_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isGenerateGetterSetter()
     * <em>Generate Getter Setter</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #isGenerateGetterSetter()
     * @generated
     * @ordered
     */
    protected boolean generateGetterSetter = GENERATE_GETTER_SETTER_EDEFAULT;

    /**
     * The default value of the '{@link #getExtension() <em>Extension</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getExtension()
     * @generated
     * @ordered
     */
    protected static final String EXTENSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getExtension() <em>Extension</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getExtension()
     * @generated
     * @ordered
     */
    protected String extension = EXTENSION_EDEFAULT;

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
     * The default value of the '{@link #getTargetFolder()
     * <em>Target Folder</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getTargetFolder()
     * @generated
     * @ordered
     */
    protected static final String TARGET_FOLDER_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTargetFolder()
     * <em>Target Folder</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getTargetFolder()
     * @generated
     * @ordered
     */
    protected String targetFolder = TARGET_FOLDER_EDEFAULT;

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
     * The default value of the '{@link #getImplements() <em>Implements</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getImplements()
     * @generated
     * @ordered
     */
    protected static final String IMPLEMENTS_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getImplements() <em>Implements</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getImplements()
     * @generated
     * @ordered
     */
    protected String implements_ = IMPLEMENTS_EDEFAULT;

    /**
     * The default value of the '{@link #getExtends() <em>Extends</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getExtends()
     * @generated
     * @ordered
     */
    protected static final String EXTENDS_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getExtends() <em>Extends</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getExtends()
     * @generated
     * @ordered
     */
    protected String extends_ = EXTENDS_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected ClassRuleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TransformationPackage.Literals.CLASS_RULE;
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
            eNotify(new ENotificationImpl(this, Notification.SET, TransformationPackage.CLASS_RULE__NAME, oldName, name));
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
                TransformationPackage.CLASS_RULE__STEREOTYPE,
                oldStereotype,
                stereotype));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public boolean isGenerateGetterSetter() {
        return generateGetterSetter;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setGenerateGetterSetter(boolean newGenerateGetterSetter) {
        boolean oldGenerateGetterSetter = generateGetterSetter;
        generateGetterSetter = newGenerateGetterSetter;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.CLASS_RULE__GENERATE_GETTER_SETTER,
                oldGenerateGetterSetter,
                generateGetterSetter));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getExtension() {
        return extension;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setExtension(String newExtension) {
        String oldExtension = extension;
        extension = newExtension;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.CLASS_RULE__EXTENSION,
                oldExtension,
                extension));
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
                TransformationPackage.CLASS_RULE__VERSION,
                oldVersion,
                version));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getTargetFolder() {
        return targetFolder;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setTargetFolder(String newTargetFolder) {
        String oldTargetFolder = targetFolder;
        targetFolder = newTargetFolder;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.CLASS_RULE__TARGET_FOLDER,
                oldTargetFolder,
                targetFolder));
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
                TransformationPackage.CLASS_RULE__PREFIX,
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
                TransformationPackage.CLASS_RULE__POSTFIX,
                oldPostfix,
                postfix));
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
                TransformationPackage.CLASS_RULE__TEMPLATE,
                oldTemplate,
                template));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getImplements() {
        return implements_;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setImplements(String newImplements) {
        String oldImplements = implements_;
        implements_ = newImplements;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.CLASS_RULE__IMPLEMENTS,
                oldImplements,
                implements_));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getExtends() {
        return extends_;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setExtends(String newExtends) {
        String oldExtends = extends_;
        extends_ = newExtends;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.CLASS_RULE__EXTENDS,
                oldExtends,
                extends_));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case TransformationPackage.CLASS_RULE__NAME:
                return getName();
            case TransformationPackage.CLASS_RULE__STEREOTYPE:
                return getStereotype();
            case TransformationPackage.CLASS_RULE__GENERATE_GETTER_SETTER:
                return isGenerateGetterSetter() ? Boolean.TRUE : Boolean.FALSE;
            case TransformationPackage.CLASS_RULE__EXTENSION:
                return getExtension();
            case TransformationPackage.CLASS_RULE__VERSION:
                return getVersion();
            case TransformationPackage.CLASS_RULE__TARGET_FOLDER:
                return getTargetFolder();
            case TransformationPackage.CLASS_RULE__PREFIX:
                return getPrefix();
            case TransformationPackage.CLASS_RULE__POSTFIX:
                return getPostfix();
            case TransformationPackage.CLASS_RULE__TEMPLATE:
                return getTemplate();
            case TransformationPackage.CLASS_RULE__IMPLEMENTS:
                return getImplements();
            case TransformationPackage.CLASS_RULE__EXTENDS:
                return getExtends();
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
            case TransformationPackage.CLASS_RULE__NAME:
                setName((String) newValue);
                return;
            case TransformationPackage.CLASS_RULE__STEREOTYPE:
                setStereotype((String) newValue);
                return;
            case TransformationPackage.CLASS_RULE__GENERATE_GETTER_SETTER:
                setGenerateGetterSetter(((Boolean) newValue).booleanValue());
                return;
            case TransformationPackage.CLASS_RULE__EXTENSION:
                setExtension((String) newValue);
                return;
            case TransformationPackage.CLASS_RULE__VERSION:
                setVersion((String) newValue);
                return;
            case TransformationPackage.CLASS_RULE__TARGET_FOLDER:
                setTargetFolder((String) newValue);
                return;
            case TransformationPackage.CLASS_RULE__PREFIX:
                setPrefix((String) newValue);
                return;
            case TransformationPackage.CLASS_RULE__POSTFIX:
                setPostfix((String) newValue);
                return;
            case TransformationPackage.CLASS_RULE__TEMPLATE:
                setTemplate((String) newValue);
                return;
            case TransformationPackage.CLASS_RULE__IMPLEMENTS:
                setImplements((String) newValue);
                return;
            case TransformationPackage.CLASS_RULE__EXTENDS:
                setExtends((String) newValue);
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
            case TransformationPackage.CLASS_RULE__NAME:
                setName(NAME_EDEFAULT);
                return;
            case TransformationPackage.CLASS_RULE__STEREOTYPE:
                setStereotype(STEREOTYPE_EDEFAULT);
                return;
            case TransformationPackage.CLASS_RULE__GENERATE_GETTER_SETTER:
                setGenerateGetterSetter(GENERATE_GETTER_SETTER_EDEFAULT);
                return;
            case TransformationPackage.CLASS_RULE__EXTENSION:
                setExtension(EXTENSION_EDEFAULT);
                return;
            case TransformationPackage.CLASS_RULE__VERSION:
                setVersion(VERSION_EDEFAULT);
                return;
            case TransformationPackage.CLASS_RULE__TARGET_FOLDER:
                setTargetFolder(TARGET_FOLDER_EDEFAULT);
                return;
            case TransformationPackage.CLASS_RULE__PREFIX:
                setPrefix(PREFIX_EDEFAULT);
                return;
            case TransformationPackage.CLASS_RULE__POSTFIX:
                setPostfix(POSTFIX_EDEFAULT);
                return;
            case TransformationPackage.CLASS_RULE__TEMPLATE:
                setTemplate(TEMPLATE_EDEFAULT);
                return;
            case TransformationPackage.CLASS_RULE__IMPLEMENTS:
                setImplements(IMPLEMENTS_EDEFAULT);
                return;
            case TransformationPackage.CLASS_RULE__EXTENDS:
                setExtends(EXTENDS_EDEFAULT);
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
            case TransformationPackage.CLASS_RULE__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case TransformationPackage.CLASS_RULE__STEREOTYPE:
                return STEREOTYPE_EDEFAULT == null ? stereotype != null : !STEREOTYPE_EDEFAULT.equals(stereotype);
            case TransformationPackage.CLASS_RULE__GENERATE_GETTER_SETTER:
                return generateGetterSetter != GENERATE_GETTER_SETTER_EDEFAULT;
            case TransformationPackage.CLASS_RULE__EXTENSION:
                return EXTENSION_EDEFAULT == null ? extension != null : !EXTENSION_EDEFAULT.equals(extension);
            case TransformationPackage.CLASS_RULE__VERSION:
                return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
            case TransformationPackage.CLASS_RULE__TARGET_FOLDER:
                return TARGET_FOLDER_EDEFAULT == null ? targetFolder != null
                : !TARGET_FOLDER_EDEFAULT.equals(targetFolder);
            case TransformationPackage.CLASS_RULE__PREFIX:
                return PREFIX_EDEFAULT == null ? prefix != null : !PREFIX_EDEFAULT.equals(prefix);
            case TransformationPackage.CLASS_RULE__POSTFIX:
                return POSTFIX_EDEFAULT == null ? postfix != null : !POSTFIX_EDEFAULT.equals(postfix);
            case TransformationPackage.CLASS_RULE__TEMPLATE:
                return TEMPLATE_EDEFAULT == null ? template != null : !TEMPLATE_EDEFAULT.equals(template);
            case TransformationPackage.CLASS_RULE__IMPLEMENTS:
                return IMPLEMENTS_EDEFAULT == null ? implements_ != null : !IMPLEMENTS_EDEFAULT.equals(implements_);
            case TransformationPackage.CLASS_RULE__EXTENDS:
                return EXTENDS_EDEFAULT == null ? extends_ != null : !EXTENDS_EDEFAULT.equals(extends_);
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
        result.append(", generateGetterSetter: ");
        result.append(generateGetterSetter);
        result.append(", extension: ");
        result.append(extension);
        result.append(", version: ");
        result.append(version);
        result.append(", targetFolder: ");
        result.append(targetFolder);
        result.append(", prefix: ");
        result.append(prefix);
        result.append(", postfix: ");
        result.append(postfix);
        result.append(", template: ");
        result.append(template);
        result.append(", implements: ");
        result.append(implements_);
        result.append(", extends: ");
        result.append(extends_);
        result.append(')');
        return result.toString();
    }

} // ClassRuleImpl
