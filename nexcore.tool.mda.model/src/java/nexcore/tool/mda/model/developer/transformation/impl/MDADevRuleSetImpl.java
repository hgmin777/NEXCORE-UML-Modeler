/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.mda.model.developer.transformation.impl;

import java.util.Collection;

import nexcore.tool.mda.model.common.IRuleSet;
import nexcore.tool.mda.model.developer.transformation.ClassRule;
import nexcore.tool.mda.model.developer.transformation.MDADevRuleSet;
import nexcore.tool.mda.model.developer.transformation.OperationRule;
import nexcore.tool.mda.model.developer.transformation.TargetObjectNameType;
import nexcore.tool.mda.model.developer.transformation.TargetProjectType;
import nexcore.tool.mda.model.developer.transformation.TransformationPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>MDA Dev Rule Set</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.impl.MDADevRuleSetImpl#getProfileLocation
 * <em>Profile Location</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.impl.MDADevRuleSetImpl#getClassRule
 * <em>Class Rule</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.impl.MDADevRuleSetImpl#getOperationRule
 * <em>Operation Rule</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.impl.MDADevRuleSetImpl#getExternalTemplate
 * <em>External Template</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.impl.MDADevRuleSetImpl#getTemplateType
 * <em>Template Type</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.impl.MDADevRuleSetImpl#getTargetProjectLocation
 * <em>Target Project Location</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.impl.MDADevRuleSetImpl#getDefaultTargetSourceFolder
 * <em>Default Target Source Folder</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.impl.MDADevRuleSetImpl#getDefaultFileExtension
 * <em>Default File Extension</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.impl.MDADevRuleSetImpl#getTemplatePluginId
 * <em>Template Plugin Id</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.impl.MDADevRuleSetImpl#getTemplatePluginPath
 * <em>Template Plugin Path</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.impl.MDADevRuleSetImpl#getTemplateName
 * <em>Template Name</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.impl.MDADevRuleSetImpl#getTargetObjectNaming
 * <em>Target Object Naming</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.impl.MDADevRuleSetImpl#getTargetProjectType
 * <em>Target Project Type</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.impl.MDADevRuleSetImpl#isMakeSelfOperation
 * <em>Make Self Operation</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.impl.MDADevRuleSetImpl#getReplyMessageUse
 * <em>Reply Message Use</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated NOT
 */
public class MDADevRuleSetImpl extends EObjectImpl implements MDADevRuleSet, IRuleSet {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = "";

    /**
     * The default value of the '{@link #getProfileLocation()
     * <em>Profile Location</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getProfileLocation()
     * @generated
     * @ordered
     */
    protected static final String PROFILE_LOCATION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getProfileLocation()
     * <em>Profile Location</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getProfileLocation()
     * @generated
     * @ordered
     */
    protected String profileLocation = PROFILE_LOCATION_EDEFAULT;

    /**
     * The cached value of the '{@link #getClassRule() <em>Class Rule</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getClassRule()
     * @generated
     * @ordered
     */
    protected EList<ClassRule> classRule;

    /**
     * The cached value of the '{@link #getOperationRule()
     * <em>Operation Rule</em>}' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getOperationRule()
     * @generated
     * @ordered
     */
    protected EList<OperationRule> operationRule;

    /**
     * The default value of the '{@link #getExternalTemplate()
     * <em>External Template</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getExternalTemplate()
     * @generated
     * @ordered
     */
    protected static final String EXTERNAL_TEMPLATE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getExternalTemplate()
     * <em>External Template</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getExternalTemplate()
     * @generated
     * @ordered
     */
    protected String externalTemplate = EXTERNAL_TEMPLATE_EDEFAULT;

    /**
     * The default value of the '{@link #getTemplateType()
     * <em>Template Type</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getTemplateType()
     * @generated
     * @ordered
     */
    protected static final String TEMPLATE_TYPE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTemplateType()
     * <em>Template Type</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getTemplateType()
     * @generated
     * @ordered
     */
    protected String templateType = TEMPLATE_TYPE_EDEFAULT;

    /**
     * The default value of the '{@link #getTargetProjectLocation()
     * <em>Target Project Location</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getTargetProjectLocation()
     * @generated
     * @ordered
     */
    protected static final String TARGET_PROJECT_LOCATION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTargetProjectLocation()
     * <em>Target Project Location</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getTargetProjectLocation()
     * @generated
     * @ordered
     */
    protected String targetProjectLocation = TARGET_PROJECT_LOCATION_EDEFAULT;

    /**
     * The default value of the '{@link #getDefaultTargetSourceFolder()
     * <em>Default Target Source Folder</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getDefaultTargetSourceFolder()
     * @generated
     * @ordered
     */
    protected static final String DEFAULT_TARGET_SOURCE_FOLDER_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDefaultTargetSourceFolder()
     * <em>Default Target Source Folder</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getDefaultTargetSourceFolder()
     * @generated
     * @ordered
     */
    protected String defaultTargetSourceFolder = DEFAULT_TARGET_SOURCE_FOLDER_EDEFAULT;

    /**
     * The default value of the '{@link #getDefaultFileExtension()
     * <em>Default File Extension</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getDefaultFileExtension()
     * @generated
     * @ordered
     */
    protected static final String DEFAULT_FILE_EXTENSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDefaultFileExtension()
     * <em>Default File Extension</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getDefaultFileExtension()
     * @generated
     * @ordered
     */
    protected String defaultFileExtension = DEFAULT_FILE_EXTENSION_EDEFAULT;

    /**
     * The default value of the '{@link #getTemplatePluginId()
     * <em>Template Plugin Id</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getTemplatePluginId()
     * @generated
     * @ordered
     */
    protected static final String TEMPLATE_PLUGIN_ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTemplatePluginId()
     * <em>Template Plugin Id</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getTemplatePluginId()
     * @generated
     * @ordered
     */
    protected String templatePluginId = TEMPLATE_PLUGIN_ID_EDEFAULT;

    /**
     * The default value of the '{@link #getTemplatePluginPath()
     * <em>Template Plugin Path</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getTemplatePluginPath()
     * @generated
     * @ordered
     */
    protected static final String TEMPLATE_PLUGIN_PATH_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTemplatePluginPath()
     * <em>Template Plugin Path</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getTemplatePluginPath()
     * @generated
     * @ordered
     */
    protected String templatePluginPath = TEMPLATE_PLUGIN_PATH_EDEFAULT;

    /**
     * The default value of the '{@link #getTemplateName()
     * <em>Template Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getTemplateName()
     * @generated
     * @ordered
     */
    protected static final String TEMPLATE_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTemplateName()
     * <em>Template Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getTemplateName()
     * @generated
     * @ordered
     */
    protected String templateName = TEMPLATE_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getTargetObjectNaming()
     * <em>Target Object Naming</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getTargetObjectNaming()
     * @generated
     * @ordered
     */
    protected static final TargetObjectNameType TARGET_OBJECT_NAMING_EDEFAULT = TargetObjectNameType.CLASS_NAME;

    /**
     * The cached value of the '{@link #getTargetObjectNaming()
     * <em>Target Object Naming</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getTargetObjectNaming()
     * @generated
     * @ordered
     */
    protected TargetObjectNameType targetObjectNaming = TARGET_OBJECT_NAMING_EDEFAULT;

    /**
     * The default value of the '{@link #getTargetProjectType()
     * <em>Target Project Type</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getTargetProjectType()
     * @generated
     * @ordered
     */
    protected static final TargetProjectType TARGET_PROJECT_TYPE_EDEFAULT = TargetProjectType.JAVA;

    /**
     * The cached value of the '{@link #getTargetProjectType()
     * <em>Target Project Type</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getTargetProjectType()
     * @generated
     * @ordered
     */
    protected TargetProjectType targetProjectType = TARGET_PROJECT_TYPE_EDEFAULT;

    /**
     * The default value of the '{@link #isMakeSelfOperation()
     * <em>Make Self Operation</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #isMakeSelfOperation()
     * @generated
     * @ordered
     */
    protected static final boolean MAKE_SELF_OPERATION_EDEFAULT = true;

    /**
     * The cached value of the '{@link #isMakeSelfOperation()
     * <em>Make Self Operation</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #isMakeSelfOperation()
     * @generated
     * @ordered
     */
    protected boolean makeSelfOperation = MAKE_SELF_OPERATION_EDEFAULT;

    /**
     * The default value of the '{@link #getReplyMessageUse()
     * <em>Reply Message Use</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getReplyMessageUse()
     * @generated
     * @ordered
     */
    protected static final int REPLY_MESSAGE_USE_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getReplyMessageUse()
     * <em>Reply Message Use</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getReplyMessageUse()
     * @generated
     * @ordered
     */
    protected int replyMessageUse = REPLY_MESSAGE_USE_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected MDADevRuleSetImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TransformationPackage.Literals.MDA_DEV_RULE_SET;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getProfileLocation() {
        return profileLocation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setProfileLocation(String newProfileLocation) {
        String oldProfileLocation = profileLocation;
        profileLocation = newProfileLocation;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.MDA_DEV_RULE_SET__PROFILE_LOCATION,
                oldProfileLocation,
                profileLocation));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<ClassRule> getClassRule() {
        if (classRule == null) {
            classRule = new EObjectContainmentEList<ClassRule>(ClassRule.class,
                this,
                TransformationPackage.MDA_DEV_RULE_SET__CLASS_RULE);
        }
        return classRule;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<OperationRule> getOperationRule() {
        if (operationRule == null) {
            operationRule = new EObjectContainmentEList<OperationRule>(OperationRule.class,
                this,
                TransformationPackage.MDA_DEV_RULE_SET__OPERATION_RULE);
        }
        return operationRule;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getExternalTemplate() {
        return externalTemplate;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setExternalTemplate(String newExternalTemplate) {
        String oldExternalTemplate = externalTemplate;
        externalTemplate = newExternalTemplate;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.MDA_DEV_RULE_SET__EXTERNAL_TEMPLATE,
                oldExternalTemplate,
                externalTemplate));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getTemplateType() {
        return templateType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setTemplateType(String newTemplateType) {
        String oldTemplateType = templateType;
        templateType = newTemplateType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.MDA_DEV_RULE_SET__TEMPLATE_TYPE,
                oldTemplateType,
                templateType));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getTargetProjectLocation() {
        return targetProjectLocation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setTargetProjectLocation(String newTargetProjectLocation) {
        String oldTargetProjectLocation = targetProjectLocation;
        targetProjectLocation = newTargetProjectLocation;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.MDA_DEV_RULE_SET__TARGET_PROJECT_LOCATION,
                oldTargetProjectLocation,
                targetProjectLocation));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getDefaultTargetSourceFolder() {
        return defaultTargetSourceFolder;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setDefaultTargetSourceFolder(String newDefaultTargetSourceFolder) {
        String oldDefaultTargetSourceFolder = defaultTargetSourceFolder;
        defaultTargetSourceFolder = newDefaultTargetSourceFolder;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.MDA_DEV_RULE_SET__DEFAULT_TARGET_SOURCE_FOLDER,
                oldDefaultTargetSourceFolder,
                defaultTargetSourceFolder));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getDefaultFileExtension() {
        return defaultFileExtension;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setDefaultFileExtension(String newDefaultFileExtension) {
        String oldDefaultFileExtension = defaultFileExtension;
        defaultFileExtension = newDefaultFileExtension;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.MDA_DEV_RULE_SET__DEFAULT_FILE_EXTENSION,
                oldDefaultFileExtension,
                defaultFileExtension));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getTemplatePluginId() {
        return templatePluginId;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setTemplatePluginId(String newTemplatePluginId) {
        String oldTemplatePluginId = templatePluginId;
        templatePluginId = newTemplatePluginId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.MDA_DEV_RULE_SET__TEMPLATE_PLUGIN_ID,
                oldTemplatePluginId,
                templatePluginId));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getTemplatePluginPath() {
        return templatePluginPath;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setTemplatePluginPath(String newTemplatePluginPath) {
        String oldTemplatePluginPath = templatePluginPath;
        templatePluginPath = newTemplatePluginPath;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.MDA_DEV_RULE_SET__TEMPLATE_PLUGIN_PATH,
                oldTemplatePluginPath,
                templatePluginPath));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getTemplateName() {
        return templateName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setTemplateName(String newTemplateName) {
        String oldTemplateName = templateName;
        templateName = newTemplateName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.MDA_DEV_RULE_SET__TEMPLATE_NAME,
                oldTemplateName,
                templateName));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public TargetObjectNameType getTargetObjectNaming() {
        return targetObjectNaming;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setTargetObjectNaming(TargetObjectNameType newTargetObjectNaming) {
        TargetObjectNameType oldTargetObjectNaming = targetObjectNaming;
        targetObjectNaming = newTargetObjectNaming == null ? TARGET_OBJECT_NAMING_EDEFAULT : newTargetObjectNaming;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.MDA_DEV_RULE_SET__TARGET_OBJECT_NAMING,
                oldTargetObjectNaming,
                targetObjectNaming));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public TargetProjectType getTargetProjectType() {
        return targetProjectType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setTargetProjectType(TargetProjectType newTargetProjectType) {
        TargetProjectType oldTargetProjectType = targetProjectType;
        targetProjectType = newTargetProjectType == null ? TARGET_PROJECT_TYPE_EDEFAULT : newTargetProjectType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.MDA_DEV_RULE_SET__TARGET_PROJECT_TYPE,
                oldTargetProjectType,
                targetProjectType));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public boolean isMakeSelfOperation() {
        return makeSelfOperation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setMakeSelfOperation(boolean newMakeSelfOperation) {
        boolean oldMakeSelfOperation = makeSelfOperation;
        makeSelfOperation = newMakeSelfOperation;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.MDA_DEV_RULE_SET__MAKE_SELF_OPERATION,
                oldMakeSelfOperation,
                makeSelfOperation));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public int getReplyMessageUse() {
        return replyMessageUse;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setReplyMessageUse(int newReplyMessageUse) {
        int oldReplyMessageUse = replyMessageUse;
        replyMessageUse = newReplyMessageUse;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.MDA_DEV_RULE_SET__REPLY_MESSAGE_USE,
                oldReplyMessageUse,
                replyMessageUse));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case TransformationPackage.MDA_DEV_RULE_SET__CLASS_RULE:
                return ((InternalEList<?>) getClassRule()).basicRemove(otherEnd, msgs);
            case TransformationPackage.MDA_DEV_RULE_SET__OPERATION_RULE:
                return ((InternalEList<?>) getOperationRule()).basicRemove(otherEnd, msgs);
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
            case TransformationPackage.MDA_DEV_RULE_SET__PROFILE_LOCATION:
                return getProfileLocation();
            case TransformationPackage.MDA_DEV_RULE_SET__CLASS_RULE:
                return getClassRule();
            case TransformationPackage.MDA_DEV_RULE_SET__OPERATION_RULE:
                return getOperationRule();
            case TransformationPackage.MDA_DEV_RULE_SET__EXTERNAL_TEMPLATE:
                return getExternalTemplate();
            case TransformationPackage.MDA_DEV_RULE_SET__TEMPLATE_TYPE:
                return getTemplateType();
            case TransformationPackage.MDA_DEV_RULE_SET__TARGET_PROJECT_LOCATION:
                return getTargetProjectLocation();
            case TransformationPackage.MDA_DEV_RULE_SET__DEFAULT_TARGET_SOURCE_FOLDER:
                return getDefaultTargetSourceFolder();
            case TransformationPackage.MDA_DEV_RULE_SET__DEFAULT_FILE_EXTENSION:
                return getDefaultFileExtension();
            case TransformationPackage.MDA_DEV_RULE_SET__TEMPLATE_PLUGIN_ID:
                return getTemplatePluginId();
            case TransformationPackage.MDA_DEV_RULE_SET__TEMPLATE_PLUGIN_PATH:
                return getTemplatePluginPath();
            case TransformationPackage.MDA_DEV_RULE_SET__TEMPLATE_NAME:
                return getTemplateName();
            case TransformationPackage.MDA_DEV_RULE_SET__TARGET_OBJECT_NAMING:
                return getTargetObjectNaming();
            case TransformationPackage.MDA_DEV_RULE_SET__TARGET_PROJECT_TYPE:
                return getTargetProjectType();
            case TransformationPackage.MDA_DEV_RULE_SET__MAKE_SELF_OPERATION:
                return isMakeSelfOperation() ? Boolean.TRUE : Boolean.FALSE;
            case TransformationPackage.MDA_DEV_RULE_SET__REPLY_MESSAGE_USE:
                return new Integer(getReplyMessageUse());
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
            case TransformationPackage.MDA_DEV_RULE_SET__PROFILE_LOCATION:
                setProfileLocation((String) newValue);
                return;
            case TransformationPackage.MDA_DEV_RULE_SET__CLASS_RULE:
                getClassRule().clear();
                getClassRule().addAll((Collection<? extends ClassRule>) newValue);
                return;
            case TransformationPackage.MDA_DEV_RULE_SET__OPERATION_RULE:
                getOperationRule().clear();
                getOperationRule().addAll((Collection<? extends OperationRule>) newValue);
                return;
            case TransformationPackage.MDA_DEV_RULE_SET__EXTERNAL_TEMPLATE:
                setExternalTemplate((String) newValue);
                return;
            case TransformationPackage.MDA_DEV_RULE_SET__TEMPLATE_TYPE:
                setTemplateType((String) newValue);
                return;
            case TransformationPackage.MDA_DEV_RULE_SET__TARGET_PROJECT_LOCATION:
                setTargetProjectLocation((String) newValue);
                return;
            case TransformationPackage.MDA_DEV_RULE_SET__DEFAULT_TARGET_SOURCE_FOLDER:
                setDefaultTargetSourceFolder((String) newValue);
                return;
            case TransformationPackage.MDA_DEV_RULE_SET__DEFAULT_FILE_EXTENSION:
                setDefaultFileExtension((String) newValue);
                return;
            case TransformationPackage.MDA_DEV_RULE_SET__TEMPLATE_PLUGIN_ID:
                setTemplatePluginId((String) newValue);
                return;
            case TransformationPackage.MDA_DEV_RULE_SET__TEMPLATE_PLUGIN_PATH:
                setTemplatePluginPath((String) newValue);
                return;
            case TransformationPackage.MDA_DEV_RULE_SET__TEMPLATE_NAME:
                setTemplateName((String) newValue);
                return;
            case TransformationPackage.MDA_DEV_RULE_SET__TARGET_OBJECT_NAMING:
                setTargetObjectNaming((TargetObjectNameType) newValue);
                return;
            case TransformationPackage.MDA_DEV_RULE_SET__TARGET_PROJECT_TYPE:
                setTargetProjectType((TargetProjectType) newValue);
                return;
            case TransformationPackage.MDA_DEV_RULE_SET__MAKE_SELF_OPERATION:
                setMakeSelfOperation(((Boolean) newValue).booleanValue());
                return;
            case TransformationPackage.MDA_DEV_RULE_SET__REPLY_MESSAGE_USE:
                setReplyMessageUse(((Integer) newValue).intValue());
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
            case TransformationPackage.MDA_DEV_RULE_SET__PROFILE_LOCATION:
                setProfileLocation(PROFILE_LOCATION_EDEFAULT);
                return;
            case TransformationPackage.MDA_DEV_RULE_SET__CLASS_RULE:
                getClassRule().clear();
                return;
            case TransformationPackage.MDA_DEV_RULE_SET__OPERATION_RULE:
                getOperationRule().clear();
                return;
            case TransformationPackage.MDA_DEV_RULE_SET__EXTERNAL_TEMPLATE:
                setExternalTemplate(EXTERNAL_TEMPLATE_EDEFAULT);
                return;
            case TransformationPackage.MDA_DEV_RULE_SET__TEMPLATE_TYPE:
                setTemplateType(TEMPLATE_TYPE_EDEFAULT);
                return;
            case TransformationPackage.MDA_DEV_RULE_SET__TARGET_PROJECT_LOCATION:
                setTargetProjectLocation(TARGET_PROJECT_LOCATION_EDEFAULT);
                return;
            case TransformationPackage.MDA_DEV_RULE_SET__DEFAULT_TARGET_SOURCE_FOLDER:
                setDefaultTargetSourceFolder(DEFAULT_TARGET_SOURCE_FOLDER_EDEFAULT);
                return;
            case TransformationPackage.MDA_DEV_RULE_SET__DEFAULT_FILE_EXTENSION:
                setDefaultFileExtension(DEFAULT_FILE_EXTENSION_EDEFAULT);
                return;
            case TransformationPackage.MDA_DEV_RULE_SET__TEMPLATE_PLUGIN_ID:
                setTemplatePluginId(TEMPLATE_PLUGIN_ID_EDEFAULT);
                return;
            case TransformationPackage.MDA_DEV_RULE_SET__TEMPLATE_PLUGIN_PATH:
                setTemplatePluginPath(TEMPLATE_PLUGIN_PATH_EDEFAULT);
                return;
            case TransformationPackage.MDA_DEV_RULE_SET__TEMPLATE_NAME:
                setTemplateName(TEMPLATE_NAME_EDEFAULT);
                return;
            case TransformationPackage.MDA_DEV_RULE_SET__TARGET_OBJECT_NAMING:
                setTargetObjectNaming(TARGET_OBJECT_NAMING_EDEFAULT);
                return;
            case TransformationPackage.MDA_DEV_RULE_SET__TARGET_PROJECT_TYPE:
                setTargetProjectType(TARGET_PROJECT_TYPE_EDEFAULT);
                return;
            case TransformationPackage.MDA_DEV_RULE_SET__MAKE_SELF_OPERATION:
                setMakeSelfOperation(MAKE_SELF_OPERATION_EDEFAULT);
                return;
            case TransformationPackage.MDA_DEV_RULE_SET__REPLY_MESSAGE_USE:
                setReplyMessageUse(REPLY_MESSAGE_USE_EDEFAULT);
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
            case TransformationPackage.MDA_DEV_RULE_SET__PROFILE_LOCATION:
                return PROFILE_LOCATION_EDEFAULT == null ? profileLocation != null
                : !PROFILE_LOCATION_EDEFAULT.equals(profileLocation);
            case TransformationPackage.MDA_DEV_RULE_SET__CLASS_RULE:
                return classRule != null && !classRule.isEmpty();
            case TransformationPackage.MDA_DEV_RULE_SET__OPERATION_RULE:
                return operationRule != null && !operationRule.isEmpty();
            case TransformationPackage.MDA_DEV_RULE_SET__EXTERNAL_TEMPLATE:
                return EXTERNAL_TEMPLATE_EDEFAULT == null ? externalTemplate != null
                : !EXTERNAL_TEMPLATE_EDEFAULT.equals(externalTemplate);
            case TransformationPackage.MDA_DEV_RULE_SET__TEMPLATE_TYPE:
                return TEMPLATE_TYPE_EDEFAULT == null ? templateType != null
                : !TEMPLATE_TYPE_EDEFAULT.equals(templateType);
            case TransformationPackage.MDA_DEV_RULE_SET__TARGET_PROJECT_LOCATION:
                return TARGET_PROJECT_LOCATION_EDEFAULT == null ? targetProjectLocation != null
                : !TARGET_PROJECT_LOCATION_EDEFAULT.equals(targetProjectLocation);
            case TransformationPackage.MDA_DEV_RULE_SET__DEFAULT_TARGET_SOURCE_FOLDER:
                return DEFAULT_TARGET_SOURCE_FOLDER_EDEFAULT == null ? defaultTargetSourceFolder != null
                : !DEFAULT_TARGET_SOURCE_FOLDER_EDEFAULT.equals(defaultTargetSourceFolder);
            case TransformationPackage.MDA_DEV_RULE_SET__DEFAULT_FILE_EXTENSION:
                return DEFAULT_FILE_EXTENSION_EDEFAULT == null ? defaultFileExtension != null
                : !DEFAULT_FILE_EXTENSION_EDEFAULT.equals(defaultFileExtension);
            case TransformationPackage.MDA_DEV_RULE_SET__TEMPLATE_PLUGIN_ID:
                return TEMPLATE_PLUGIN_ID_EDEFAULT == null ? templatePluginId != null
                : !TEMPLATE_PLUGIN_ID_EDEFAULT.equals(templatePluginId);
            case TransformationPackage.MDA_DEV_RULE_SET__TEMPLATE_PLUGIN_PATH:
                return TEMPLATE_PLUGIN_PATH_EDEFAULT == null ? templatePluginPath != null
                : !TEMPLATE_PLUGIN_PATH_EDEFAULT.equals(templatePluginPath);
            case TransformationPackage.MDA_DEV_RULE_SET__TEMPLATE_NAME:
                return TEMPLATE_NAME_EDEFAULT == null ? templateName != null
                : !TEMPLATE_NAME_EDEFAULT.equals(templateName);
            case TransformationPackage.MDA_DEV_RULE_SET__TARGET_OBJECT_NAMING:
                return targetObjectNaming != TARGET_OBJECT_NAMING_EDEFAULT;
            case TransformationPackage.MDA_DEV_RULE_SET__TARGET_PROJECT_TYPE:
                return targetProjectType != TARGET_PROJECT_TYPE_EDEFAULT;
            case TransformationPackage.MDA_DEV_RULE_SET__MAKE_SELF_OPERATION:
                return makeSelfOperation != MAKE_SELF_OPERATION_EDEFAULT;
            case TransformationPackage.MDA_DEV_RULE_SET__REPLY_MESSAGE_USE:
                return replyMessageUse != REPLY_MESSAGE_USE_EDEFAULT;
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
        result.append(" (profileLocation: ");
        result.append(profileLocation);
        result.append(", externalTemplate: ");
        result.append(externalTemplate);
        result.append(", templateType: ");
        result.append(templateType);
        result.append(", targetProjectLocation: ");
        result.append(targetProjectLocation);
        result.append(", defaultTargetSourceFolder: ");
        result.append(defaultTargetSourceFolder);
        result.append(", defaultFileExtension: ");
        result.append(defaultFileExtension);
        result.append(", templatePluginId: ");
        result.append(templatePluginId);
        result.append(", templatePluginPath: ");
        result.append(templatePluginPath);
        result.append(", templateName: ");
        result.append(templateName);
        result.append(", targetObjectNaming: ");
        result.append(targetObjectNaming);
        result.append(", targetProjectType: ");
        result.append(targetProjectType);
        result.append(", makeSelfOperation: ");
        result.append(makeSelfOperation);
        result.append(", replyMessageUse: ");
        result.append(replyMessageUse);
        result.append(')');
        return result.toString();
    }

    /** 검증 여부 플래그 */
    boolean validated = false;

    /**
     * @see nexcore.tool.mda.core.infrastructure.model.common.IRuleSet#isValidated()
     */
    public boolean isValidated() {
        return validated;
    }

    /**
     * @see nexcore.tool.mda.core.infrastructure.model.common.IRuleSet#setValidated(boolean)
     */
    public void setValidated(boolean validated) {
        this.validated = validated;
    }

} // MDADevRuleSetImpl
