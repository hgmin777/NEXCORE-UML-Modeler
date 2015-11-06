/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.mda.model.developer.transformation;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains
 * accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * 
 * @see nexcore.tool.mda.model.developer.transformation.TransformationFactory
 * @model kind="package"
 * @generated
 */
public interface TransformationPackage extends EPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "";

    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNAME = "transformation";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_URI = "http://nexcore.skcc.com/tools/mda/developer/transformation";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_PREFIX = "dvt";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    TransformationPackage eINSTANCE = nexcore.tool.mda.model.developer.transformation.impl.TransformationPackageImpl.init();

    /**
     * The meta object id for the '
     * {@link nexcore.tool.mda.model.developer.transformation.impl.MDADevRuleSetImpl
     * <em>MDA Dev Rule Set</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see nexcore.tool.mda.model.developer.transformation.impl.MDADevRuleSetImpl
     * @see nexcore.tool.mda.model.developer.transformation.impl.TransformationPackageImpl#getMDADevRuleSet()
     * @generated
     */
    int MDA_DEV_RULE_SET = 0;

    /**
     * The feature id for the '<em><b>Profile Location</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MDA_DEV_RULE_SET__PROFILE_LOCATION = 0;

    /**
     * The feature id for the '<em><b>Class Rule</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MDA_DEV_RULE_SET__CLASS_RULE = 1;

    /**
     * The feature id for the '<em><b>Operation Rule</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MDA_DEV_RULE_SET__OPERATION_RULE = 2;

    /**
     * The feature id for the '<em><b>External Template</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MDA_DEV_RULE_SET__EXTERNAL_TEMPLATE = 3;

    /**
     * The feature id for the '<em><b>Template Type</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MDA_DEV_RULE_SET__TEMPLATE_TYPE = 4;

    /**
     * The feature id for the '<em><b>Target Project Location</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MDA_DEV_RULE_SET__TARGET_PROJECT_LOCATION = 5;

    /**
     * The feature id for the '<em><b>Default Target Source Folder</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MDA_DEV_RULE_SET__DEFAULT_TARGET_SOURCE_FOLDER = 6;

    /**
     * The feature id for the '<em><b>Default File Extension</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MDA_DEV_RULE_SET__DEFAULT_FILE_EXTENSION = 7;

    /**
     * The feature id for the '<em><b>Template Plugin Id</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MDA_DEV_RULE_SET__TEMPLATE_PLUGIN_ID = 8;

    /**
     * The feature id for the '<em><b>Template Plugin Path</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MDA_DEV_RULE_SET__TEMPLATE_PLUGIN_PATH = 9;

    /**
     * The feature id for the '<em><b>Template Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MDA_DEV_RULE_SET__TEMPLATE_NAME = 10;

    /**
     * The feature id for the '<em><b>Target Object Naming</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MDA_DEV_RULE_SET__TARGET_OBJECT_NAMING = 11;

    /**
     * The feature id for the '<em><b>Target Project Type</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MDA_DEV_RULE_SET__TARGET_PROJECT_TYPE = 12;

    /**
     * The feature id for the '<em><b>Make Self Operation</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MDA_DEV_RULE_SET__MAKE_SELF_OPERATION = 13;

    /**
     * The feature id for the '<em><b>Reply Message Use</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MDA_DEV_RULE_SET__REPLY_MESSAGE_USE = 14;

    /**
     * The number of structural features of the '<em>MDA Dev Rule Set</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MDA_DEV_RULE_SET_FEATURE_COUNT = 15;

    /**
     * The meta object id for the '
     * {@link nexcore.tool.mda.model.developer.transformation.impl.ClassRuleImpl
     * <em>Class Rule</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see nexcore.tool.mda.model.developer.transformation.impl.ClassRuleImpl
     * @see nexcore.tool.mda.model.developer.transformation.impl.TransformationPackageImpl#getClassRule()
     * @generated
     */
    int CLASS_RULE = 1;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CLASS_RULE__NAME = 0;

    /**
     * The feature id for the '<em><b>Stereotype</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CLASS_RULE__STEREOTYPE = 1;

    /**
     * The feature id for the '<em><b>Generate Getter Setter</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CLASS_RULE__GENERATE_GETTER_SETTER = 2;

    /**
     * The feature id for the '<em><b>Extension</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CLASS_RULE__EXTENSION = 3;

    /**
     * The feature id for the '<em><b>Version</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CLASS_RULE__VERSION = 4;

    /**
     * The feature id for the '<em><b>Target Folder</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CLASS_RULE__TARGET_FOLDER = 5;

    /**
     * The feature id for the '<em><b>Prefix</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CLASS_RULE__PREFIX = 6;

    /**
     * The feature id for the '<em><b>Postfix</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CLASS_RULE__POSTFIX = 7;

    /**
     * The feature id for the '<em><b>Template</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CLASS_RULE__TEMPLATE = 8;

    /**
     * The feature id for the '<em><b>Implements</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CLASS_RULE__IMPLEMENTS = 9;

    /**
     * The feature id for the '<em><b>Extends</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CLASS_RULE__EXTENDS = 10;

    /**
     * The number of structural features of the '<em>Class Rule</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CLASS_RULE_FEATURE_COUNT = 11;

    /**
     * The meta object id for the '
     * {@link nexcore.tool.mda.model.developer.transformation.impl.OperationRuleImpl
     * <em>Operation Rule</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see nexcore.tool.mda.model.developer.transformation.impl.OperationRuleImpl
     * @see nexcore.tool.mda.model.developer.transformation.impl.TransformationPackageImpl#getOperationRule()
     * @generated
     */
    int OPERATION_RULE = 2;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERATION_RULE__NAME = 0;

    /**
     * The feature id for the '<em><b>Stereotype</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERATION_RULE__STEREOTYPE = 1;

    /**
     * The feature id for the '<em><b>Template</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERATION_RULE__TEMPLATE = 2;

    /**
     * The feature id for the '<em><b>Prefix</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERATION_RULE__PREFIX = 3;

    /**
     * The feature id for the '<em><b>Postfix</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERATION_RULE__POSTFIX = 4;

    /**
     * The feature id for the '<em><b>Version</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERATION_RULE__VERSION = 5;

    /**
     * The number of structural features of the '<em>Operation Rule</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERATION_RULE_FEATURE_COUNT = 6;

    /**
     * The meta object id for the '
     * {@link nexcore.tool.mda.model.developer.transformation.TargetObjectNameType
     * <em>Target Object Name Type</em>}' enum. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see nexcore.tool.mda.model.developer.transformation.TargetObjectNameType
     * @see nexcore.tool.mda.model.developer.transformation.impl.TransformationPackageImpl#getTargetObjectNameType()
     * @generated
     */
    int TARGET_OBJECT_NAME_TYPE = 3;

    /**
     * The meta object id for the '
     * {@link nexcore.tool.mda.model.developer.transformation.TargetProjectType
     * <em>Target Project Type</em>}' enum. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see nexcore.tool.mda.model.developer.transformation.TargetProjectType
     * @see nexcore.tool.mda.model.developer.transformation.impl.TransformationPackageImpl#getTargetProjectType()
     * @generated
     */
    int TARGET_PROJECT_TYPE = 4;

    /**
     * Returns the meta object for class '
     * {@link nexcore.tool.mda.model.developer.transformation.MDADevRuleSet
     * <em>MDA Dev Rule Set</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>MDA Dev Rule Set</em>'.
     * @see nexcore.tool.mda.model.developer.transformation.MDADevRuleSet
     * @generated
     */
    EClass getMDADevRuleSet();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getProfileLocation
     * <em>Profile Location</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Profile Location</em>'.
     * @see nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getProfileLocation()
     * @see #getMDADevRuleSet()
     * @generated
     */
    EAttribute getMDADevRuleSet_ProfileLocation();

    /**
     * Returns the meta object for the containment reference list '
     * {@link nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getClassRule
     * <em>Class Rule</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Class Rule</em>'.
     * @see nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getClassRule()
     * @see #getMDADevRuleSet()
     * @generated
     */
    EReference getMDADevRuleSet_ClassRule();

    /**
     * Returns the meta object for the containment reference list '
     * {@link nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getOperationRule
     * <em>Operation Rule</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Operation Rule</em>'.
     * @see nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getOperationRule()
     * @see #getMDADevRuleSet()
     * @generated
     */
    EReference getMDADevRuleSet_OperationRule();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getExternalTemplate
     * <em>External Template</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>External Template</em>'.
     * @see nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getExternalTemplate()
     * @see #getMDADevRuleSet()
     * @generated
     */
    EAttribute getMDADevRuleSet_ExternalTemplate();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getTemplateType
     * <em>Template Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Template Type</em>'.
     * @see nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getTemplateType()
     * @see #getMDADevRuleSet()
     * @generated
     */
    EAttribute getMDADevRuleSet_TemplateType();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getTargetProjectLocation
     * <em>Target Project Location</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Target Project Location</em>'.
     * @see nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getTargetProjectLocation()
     * @see #getMDADevRuleSet()
     * @generated
     */
    EAttribute getMDADevRuleSet_TargetProjectLocation();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getDefaultTargetSourceFolder
     * <em>Default Target Source Folder</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Default Target Source Folder</em>'.
     * @see nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getDefaultTargetSourceFolder()
     * @see #getMDADevRuleSet()
     * @generated
     */
    EAttribute getMDADevRuleSet_DefaultTargetSourceFolder();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getDefaultFileExtension
     * <em>Default File Extension</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Default File Extension</em>'.
     * @see nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getDefaultFileExtension()
     * @see #getMDADevRuleSet()
     * @generated
     */
    EAttribute getMDADevRuleSet_DefaultFileExtension();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getTemplatePluginId
     * <em>Template Plugin Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Template Plugin Id</em>'.
     * @see nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getTemplatePluginId()
     * @see #getMDADevRuleSet()
     * @generated
     */
    EAttribute getMDADevRuleSet_TemplatePluginId();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getTemplatePluginPath
     * <em>Template Plugin Path</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Template Plugin Path</em>
     *         '.
     * @see nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getTemplatePluginPath()
     * @see #getMDADevRuleSet()
     * @generated
     */
    EAttribute getMDADevRuleSet_TemplatePluginPath();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getTemplateName
     * <em>Template Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Template Name</em>'.
     * @see nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getTemplateName()
     * @see #getMDADevRuleSet()
     * @generated
     */
    EAttribute getMDADevRuleSet_TemplateName();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getTargetObjectNaming
     * <em>Target Object Naming</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Target Object Naming</em>
     *         '.
     * @see nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getTargetObjectNaming()
     * @see #getMDADevRuleSet()
     * @generated
     */
    EAttribute getMDADevRuleSet_TargetObjectNaming();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getTargetProjectType
     * <em>Target Project Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Target Project Type</em>'.
     * @see nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getTargetProjectType()
     * @see #getMDADevRuleSet()
     * @generated
     */
    EAttribute getMDADevRuleSet_TargetProjectType();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#isMakeSelfOperation
     * <em>Make Self Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Make Self Operation</em>'.
     * @see nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#isMakeSelfOperation()
     * @see #getMDADevRuleSet()
     * @generated
     */
    EAttribute getMDADevRuleSet_MakeSelfOperation();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getReplyMessageUse
     * <em>Reply Message Use</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Reply Message Use</em>'.
     * @see nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getReplyMessageUse()
     * @see #getMDADevRuleSet()
     * @generated
     */
    EAttribute getMDADevRuleSet_ReplyMessageUse();

    /**
     * Returns the meta object for class '
     * {@link nexcore.tool.mda.model.developer.transformation.ClassRule
     * <em>Class Rule</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Class Rule</em>'.
     * @see nexcore.tool.mda.model.developer.transformation.ClassRule
     * @generated
     */
    EClass getClassRule();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.developer.transformation.ClassRule#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see nexcore.tool.mda.model.developer.transformation.ClassRule#getName()
     * @see #getClassRule()
     * @generated
     */
    EAttribute getClassRule_Name();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.developer.transformation.ClassRule#getStereotype
     * <em>Stereotype</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Stereotype</em>'.
     * @see nexcore.tool.mda.model.developer.transformation.ClassRule#getStereotype()
     * @see #getClassRule()
     * @generated
     */
    EAttribute getClassRule_Stereotype();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.developer.transformation.ClassRule#isGenerateGetterSetter
     * <em>Generate Getter Setter</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Generate Getter Setter</em>'.
     * @see nexcore.tool.mda.model.developer.transformation.ClassRule#isGenerateGetterSetter()
     * @see #getClassRule()
     * @generated
     */
    EAttribute getClassRule_GenerateGetterSetter();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.developer.transformation.ClassRule#getExtension
     * <em>Extension</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Extension</em>'.
     * @see nexcore.tool.mda.model.developer.transformation.ClassRule#getExtension()
     * @see #getClassRule()
     * @generated
     */
    EAttribute getClassRule_Extension();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.developer.transformation.ClassRule#getVersion
     * <em>Version</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Version</em>'.
     * @see nexcore.tool.mda.model.developer.transformation.ClassRule#getVersion()
     * @see #getClassRule()
     * @generated
     */
    EAttribute getClassRule_Version();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.developer.transformation.ClassRule#getTargetFolder
     * <em>Target Folder</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Target Folder</em>'.
     * @see nexcore.tool.mda.model.developer.transformation.ClassRule#getTargetFolder()
     * @see #getClassRule()
     * @generated
     */
    EAttribute getClassRule_TargetFolder();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.developer.transformation.ClassRule#getPrefix
     * <em>Prefix</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Prefix</em>'.
     * @see nexcore.tool.mda.model.developer.transformation.ClassRule#getPrefix()
     * @see #getClassRule()
     * @generated
     */
    EAttribute getClassRule_Prefix();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.developer.transformation.ClassRule#getPostfix
     * <em>Postfix</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Postfix</em>'.
     * @see nexcore.tool.mda.model.developer.transformation.ClassRule#getPostfix()
     * @see #getClassRule()
     * @generated
     */
    EAttribute getClassRule_Postfix();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.developer.transformation.ClassRule#getTemplate
     * <em>Template</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Template</em>'.
     * @see nexcore.tool.mda.model.developer.transformation.ClassRule#getTemplate()
     * @see #getClassRule()
     * @generated
     */
    EAttribute getClassRule_Template();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.developer.transformation.ClassRule#getImplements
     * <em>Implements</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Implements</em>'.
     * @see nexcore.tool.mda.model.developer.transformation.ClassRule#getImplements()
     * @see #getClassRule()
     * @generated
     */
    EAttribute getClassRule_Implements();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.developer.transformation.ClassRule#getExtends
     * <em>Extends</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Extends</em>'.
     * @see nexcore.tool.mda.model.developer.transformation.ClassRule#getExtends()
     * @see #getClassRule()
     * @generated
     */
    EAttribute getClassRule_Extends();

    /**
     * Returns the meta object for class '
     * {@link nexcore.tool.mda.model.developer.transformation.OperationRule
     * <em>Operation Rule</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Operation Rule</em>'.
     * @see nexcore.tool.mda.model.developer.transformation.OperationRule
     * @generated
     */
    EClass getOperationRule();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.developer.transformation.OperationRule#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see nexcore.tool.mda.model.developer.transformation.OperationRule#getName()
     * @see #getOperationRule()
     * @generated
     */
    EAttribute getOperationRule_Name();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.developer.transformation.OperationRule#getStereotype
     * <em>Stereotype</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Stereotype</em>'.
     * @see nexcore.tool.mda.model.developer.transformation.OperationRule#getStereotype()
     * @see #getOperationRule()
     * @generated
     */
    EAttribute getOperationRule_Stereotype();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.developer.transformation.OperationRule#getTemplate
     * <em>Template</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Template</em>'.
     * @see nexcore.tool.mda.model.developer.transformation.OperationRule#getTemplate()
     * @see #getOperationRule()
     * @generated
     */
    EAttribute getOperationRule_Template();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.developer.transformation.OperationRule#getPrefix
     * <em>Prefix</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Prefix</em>'.
     * @see nexcore.tool.mda.model.developer.transformation.OperationRule#getPrefix()
     * @see #getOperationRule()
     * @generated
     */
    EAttribute getOperationRule_Prefix();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.developer.transformation.OperationRule#getPostfix
     * <em>Postfix</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Postfix</em>'.
     * @see nexcore.tool.mda.model.developer.transformation.OperationRule#getPostfix()
     * @see #getOperationRule()
     * @generated
     */
    EAttribute getOperationRule_Postfix();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.developer.transformation.OperationRule#getVersion
     * <em>Version</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Version</em>'.
     * @see nexcore.tool.mda.model.developer.transformation.OperationRule#getVersion()
     * @see #getOperationRule()
     * @generated
     */
    EAttribute getOperationRule_Version();

    /**
     * Returns the meta object for enum '
     * {@link nexcore.tool.mda.model.developer.transformation.TargetObjectNameType
     * <em>Target Object Name Type</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for enum '<em>Target Object Name Type</em>'.
     * @see nexcore.tool.mda.model.developer.transformation.TargetObjectNameType
     * @generated
     */
    EEnum getTargetObjectNameType();

    /**
     * Returns the meta object for enum '
     * {@link nexcore.tool.mda.model.developer.transformation.TargetProjectType
     * <em>Target Project Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for enum '<em>Target Project Type</em>'.
     * @see nexcore.tool.mda.model.developer.transformation.TargetProjectType
     * @generated
     */
    EEnum getTargetProjectType();

    /**
     * Returns the factory that creates the instances of the model. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the factory that creates the instances of the model.
     * @generated
     */
    TransformationFactory getTransformationFactory();

    /**
     * <!-- begin-user-doc --> Defines literals for the meta objects that
     * represent
     * <ul>
     * <li>each class,</li>
     * <li>each feature of each class,</li>
     * <li>each enum,</li>
     * <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '
         * {@link nexcore.tool.mda.model.developer.transformation.impl.MDADevRuleSetImpl
         * <em>MDA Dev Rule Set</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see nexcore.tool.mda.model.developer.transformation.impl.MDADevRuleSetImpl
         * @see nexcore.tool.mda.model.developer.transformation.impl.TransformationPackageImpl#getMDADevRuleSet()
         * @generated
         */
        EClass MDA_DEV_RULE_SET = eINSTANCE.getMDADevRuleSet();

        /**
         * The meta object literal for the '<em><b>Profile Location</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute MDA_DEV_RULE_SET__PROFILE_LOCATION = eINSTANCE.getMDADevRuleSet_ProfileLocation();

        /**
         * The meta object literal for the '<em><b>Class Rule</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference MDA_DEV_RULE_SET__CLASS_RULE = eINSTANCE.getMDADevRuleSet_ClassRule();

        /**
         * The meta object literal for the '<em><b>Operation Rule</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference MDA_DEV_RULE_SET__OPERATION_RULE = eINSTANCE.getMDADevRuleSet_OperationRule();

        /**
         * The meta object literal for the '<em><b>External Template</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute MDA_DEV_RULE_SET__EXTERNAL_TEMPLATE = eINSTANCE.getMDADevRuleSet_ExternalTemplate();

        /**
         * The meta object literal for the '<em><b>Template Type</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute MDA_DEV_RULE_SET__TEMPLATE_TYPE = eINSTANCE.getMDADevRuleSet_TemplateType();

        /**
         * The meta object literal for the '
         * <em><b>Target Project Location</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute MDA_DEV_RULE_SET__TARGET_PROJECT_LOCATION = eINSTANCE.getMDADevRuleSet_TargetProjectLocation();

        /**
         * The meta object literal for the '
         * <em><b>Default Target Source Folder</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute MDA_DEV_RULE_SET__DEFAULT_TARGET_SOURCE_FOLDER = eINSTANCE.getMDADevRuleSet_DefaultTargetSourceFolder();

        /**
         * The meta object literal for the '
         * <em><b>Default File Extension</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute MDA_DEV_RULE_SET__DEFAULT_FILE_EXTENSION = eINSTANCE.getMDADevRuleSet_DefaultFileExtension();

        /**
         * The meta object literal for the '<em><b>Template Plugin Id</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute MDA_DEV_RULE_SET__TEMPLATE_PLUGIN_ID = eINSTANCE.getMDADevRuleSet_TemplatePluginId();

        /**
         * The meta object literal for the '<em><b>Template Plugin Path</b></em>
         * ' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute MDA_DEV_RULE_SET__TEMPLATE_PLUGIN_PATH = eINSTANCE.getMDADevRuleSet_TemplatePluginPath();

        /**
         * The meta object literal for the '<em><b>Template Name</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute MDA_DEV_RULE_SET__TEMPLATE_NAME = eINSTANCE.getMDADevRuleSet_TemplateName();

        /**
         * The meta object literal for the '<em><b>Target Object Naming</b></em>
         * ' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute MDA_DEV_RULE_SET__TARGET_OBJECT_NAMING = eINSTANCE.getMDADevRuleSet_TargetObjectNaming();

        /**
         * The meta object literal for the '<em><b>Target Project Type</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute MDA_DEV_RULE_SET__TARGET_PROJECT_TYPE = eINSTANCE.getMDADevRuleSet_TargetProjectType();

        /**
         * The meta object literal for the '<em><b>Make Self Operation</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute MDA_DEV_RULE_SET__MAKE_SELF_OPERATION = eINSTANCE.getMDADevRuleSet_MakeSelfOperation();

        /**
         * The meta object literal for the '<em><b>Reply Message Use</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute MDA_DEV_RULE_SET__REPLY_MESSAGE_USE = eINSTANCE.getMDADevRuleSet_ReplyMessageUse();

        /**
         * The meta object literal for the '
         * {@link nexcore.tool.mda.model.developer.transformation.impl.ClassRuleImpl
         * <em>Class Rule</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see nexcore.tool.mda.model.developer.transformation.impl.ClassRuleImpl
         * @see nexcore.tool.mda.model.developer.transformation.impl.TransformationPackageImpl#getClassRule()
         * @generated
         */
        EClass CLASS_RULE = eINSTANCE.getClassRule();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute CLASS_RULE__NAME = eINSTANCE.getClassRule_Name();

        /**
         * The meta object literal for the '<em><b>Stereotype</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute CLASS_RULE__STEREOTYPE = eINSTANCE.getClassRule_Stereotype();

        /**
         * The meta object literal for the '
         * <em><b>Generate Getter Setter</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute CLASS_RULE__GENERATE_GETTER_SETTER = eINSTANCE.getClassRule_GenerateGetterSetter();

        /**
         * The meta object literal for the '<em><b>Extension</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute CLASS_RULE__EXTENSION = eINSTANCE.getClassRule_Extension();

        /**
         * The meta object literal for the '<em><b>Version</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute CLASS_RULE__VERSION = eINSTANCE.getClassRule_Version();

        /**
         * The meta object literal for the '<em><b>Target Folder</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute CLASS_RULE__TARGET_FOLDER = eINSTANCE.getClassRule_TargetFolder();

        /**
         * The meta object literal for the '<em><b>Prefix</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute CLASS_RULE__PREFIX = eINSTANCE.getClassRule_Prefix();

        /**
         * The meta object literal for the '<em><b>Postfix</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute CLASS_RULE__POSTFIX = eINSTANCE.getClassRule_Postfix();

        /**
         * The meta object literal for the '<em><b>Template</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute CLASS_RULE__TEMPLATE = eINSTANCE.getClassRule_Template();

        /**
         * The meta object literal for the '<em><b>Implements</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute CLASS_RULE__IMPLEMENTS = eINSTANCE.getClassRule_Implements();

        /**
         * The meta object literal for the '<em><b>Extends</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute CLASS_RULE__EXTENDS = eINSTANCE.getClassRule_Extends();

        /**
         * The meta object literal for the '
         * {@link nexcore.tool.mda.model.developer.transformation.impl.OperationRuleImpl
         * <em>Operation Rule</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see nexcore.tool.mda.model.developer.transformation.impl.OperationRuleImpl
         * @see nexcore.tool.mda.model.developer.transformation.impl.TransformationPackageImpl#getOperationRule()
         * @generated
         */
        EClass OPERATION_RULE = eINSTANCE.getOperationRule();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute OPERATION_RULE__NAME = eINSTANCE.getOperationRule_Name();

        /**
         * The meta object literal for the '<em><b>Stereotype</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute OPERATION_RULE__STEREOTYPE = eINSTANCE.getOperationRule_Stereotype();

        /**
         * The meta object literal for the '<em><b>Template</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute OPERATION_RULE__TEMPLATE = eINSTANCE.getOperationRule_Template();

        /**
         * The meta object literal for the '<em><b>Prefix</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute OPERATION_RULE__PREFIX = eINSTANCE.getOperationRule_Prefix();

        /**
         * The meta object literal for the '<em><b>Postfix</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute OPERATION_RULE__POSTFIX = eINSTANCE.getOperationRule_Postfix();

        /**
         * The meta object literal for the '<em><b>Version</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute OPERATION_RULE__VERSION = eINSTANCE.getOperationRule_Version();

        /**
         * The meta object literal for the '
         * {@link nexcore.tool.mda.model.developer.transformation.TargetObjectNameType
         * <em>Target Object Name Type</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see nexcore.tool.mda.model.developer.transformation.TargetObjectNameType
         * @see nexcore.tool.mda.model.developer.transformation.impl.TransformationPackageImpl#getTargetObjectNameType()
         * @generated
         */
        EEnum TARGET_OBJECT_NAME_TYPE = eINSTANCE.getTargetObjectNameType();

        /**
         * The meta object literal for the '
         * {@link nexcore.tool.mda.model.developer.transformation.TargetProjectType
         * <em>Target Project Type</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see nexcore.tool.mda.model.developer.transformation.TargetProjectType
         * @see nexcore.tool.mda.model.developer.transformation.impl.TransformationPackageImpl#getTargetProjectType()
         * @generated
         */
        EEnum TARGET_PROJECT_TYPE = eINSTANCE.getTargetProjectType();

    }

} // TransformationPackage
