/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.mda.model.developer.transformation;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>MDA Dev Rule Set</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getProfileLocation
 * <em>Profile Location</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getClassRule
 * <em>Class Rule</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getOperationRule
 * <em>Operation Rule</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getExternalTemplate
 * <em>External Template</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getTemplateType
 * <em>Template Type</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getTargetProjectLocation
 * <em>Target Project Location</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getDefaultTargetSourceFolder
 * <em>Default Target Source Folder</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getDefaultFileExtension
 * <em>Default File Extension</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getTemplatePluginId
 * <em>Template Plugin Id</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getTemplatePluginPath
 * <em>Template Plugin Path</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getTemplateName
 * <em>Template Name</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getTargetObjectNaming
 * <em>Target Object Naming</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getTargetProjectType
 * <em>Target Project Type</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#isMakeSelfOperation
 * <em>Make Self Operation</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getReplyMessageUse
 * <em>Reply Message Use</em>}</li>
 * </ul>
 * </p>
 * 
 * @see nexcore.tool.mda.model.developer.transformation.TransformationPackage#getMDADevRuleSet()
 * @model
 * @generated
 */
public interface MDADevRuleSet extends EObject {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "";

    /**
     * Returns the value of the '<em><b>Profile Location</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Profile Location</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Profile Location</em>' attribute.
     * @see #setProfileLocation(String)
     * @see nexcore.tool.mda.model.developer.transformation.TransformationPackage#getMDADevRuleSet_ProfileLocation()
     * @model required="true"
     * @generated
     */
    String getProfileLocation();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getProfileLocation
     * <em>Profile Location</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Profile Location</em>' attribute.
     * @see #getProfileLocation()
     * @generated
     */
    void setProfileLocation(String value);

    /**
     * Returns the value of the '<em><b>Class Rule</b></em>' containment
     * reference list. The list contents are of type
     * {@link nexcore.tool.mda.model.developer.transformation.ClassRule}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Class Rule</em>' containment reference list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Class Rule</em>' containment reference
     *         list.
     * @see nexcore.tool.mda.model.developer.transformation.TransformationPackage#getMDADevRuleSet_ClassRule()
     * @model containment="true"
     * @generated
     */
    EList<ClassRule> getClassRule();

    /**
     * Returns the value of the '<em><b>Operation Rule</b></em>' containment
     * reference list. The list contents are of type
     * {@link nexcore.tool.mda.model.developer.transformation.OperationRule}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Operation Rule</em>' containment reference
     * list isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Operation Rule</em>' containment reference
     *         list.
     * @see nexcore.tool.mda.model.developer.transformation.TransformationPackage#getMDADevRuleSet_OperationRule()
     * @model containment="true"
     * @generated
     */
    EList<OperationRule> getOperationRule();

    /**
     * Returns the value of the '<em><b>External Template</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>External Template</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>External Template</em>' attribute.
     * @see #setExternalTemplate(String)
     * @see nexcore.tool.mda.model.developer.transformation.TransformationPackage#getMDADevRuleSet_ExternalTemplate()
     * @model required="true"
     * @generated
     */
    String getExternalTemplate();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getExternalTemplate
     * <em>External Template</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>External Template</em>' attribute.
     * @see #getExternalTemplate()
     * @generated
     */
    void setExternalTemplate(String value);

    /**
     * Returns the value of the '<em><b>Template Type</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Template Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Template Type</em>' attribute.
     * @see #setTemplateType(String)
     * @see nexcore.tool.mda.model.developer.transformation.TransformationPackage#getMDADevRuleSet_TemplateType()
     * @model required="true"
     * @generated
     */
    String getTemplateType();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getTemplateType
     * <em>Template Type</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Template Type</em>' attribute.
     * @see #getTemplateType()
     * @generated
     */
    void setTemplateType(String value);

    /**
     * Returns the value of the '<em><b>Target Project Location</b></em>'
     * attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target Project Location</em>' attribute isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Target Project Location</em>' attribute.
     * @see #setTargetProjectLocation(String)
     * @see nexcore.tool.mda.model.developer.transformation.TransformationPackage#getMDADevRuleSet_TargetProjectLocation()
     * @model required="true"
     * @generated
     */
    String getTargetProjectLocation();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getTargetProjectLocation
     * <em>Target Project Location</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Target Project Location</em>'
     *            attribute.
     * @see #getTargetProjectLocation()
     * @generated
     */
    void setTargetProjectLocation(String value);

    /**
     * Returns the value of the '<em><b>Default Target Source Folder</b></em>'
     * attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Default Target Source Folder</em>' attribute
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Default Target Source Folder</em>'
     *         attribute.
     * @see #setDefaultTargetSourceFolder(String)
     * @see nexcore.tool.mda.model.developer.transformation.TransformationPackage#getMDADevRuleSet_DefaultTargetSourceFolder()
     * @model required="true"
     * @generated
     */
    String getDefaultTargetSourceFolder();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getDefaultTargetSourceFolder
     * <em>Default Target Source Folder</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Default Target Source Folder</em>'
     *            attribute.
     * @see #getDefaultTargetSourceFolder()
     * @generated
     */
    void setDefaultTargetSourceFolder(String value);

    /**
     * Returns the value of the '<em><b>Default File Extension</b></em>'
     * attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Default File Extension</em>' attribute isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Default File Extension</em>' attribute.
     * @see #setDefaultFileExtension(String)
     * @see nexcore.tool.mda.model.developer.transformation.TransformationPackage#getMDADevRuleSet_DefaultFileExtension()
     * @model required="true"
     * @generated
     */
    String getDefaultFileExtension();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getDefaultFileExtension
     * <em>Default File Extension</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Default File Extension</em>'
     *            attribute.
     * @see #getDefaultFileExtension()
     * @generated
     */
    void setDefaultFileExtension(String value);

    /**
     * Returns the value of the '<em><b>Template Plugin Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Template Plugin Id</em>' attribute isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Template Plugin Id</em>' attribute.
     * @see #setTemplatePluginId(String)
     * @see nexcore.tool.mda.model.developer.transformation.TransformationPackage#getMDADevRuleSet_TemplatePluginId()
     * @model required="true"
     * @generated
     */
    String getTemplatePluginId();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getTemplatePluginId
     * <em>Template Plugin Id</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Template Plugin Id</em>' attribute.
     * @see #getTemplatePluginId()
     * @generated
     */
    void setTemplatePluginId(String value);

    /**
     * Returns the value of the '<em><b>Template Plugin Path</b></em>'
     * attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Template Plugin Path</em>' attribute isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Template Plugin Path</em>' attribute.
     * @see #setTemplatePluginPath(String)
     * @see nexcore.tool.mda.model.developer.transformation.TransformationPackage#getMDADevRuleSet_TemplatePluginPath()
     * @model required="true"
     * @generated
     */
    String getTemplatePluginPath();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getTemplatePluginPath
     * <em>Template Plugin Path</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Template Plugin Path</em>'
     *            attribute.
     * @see #getTemplatePluginPath()
     * @generated
     */
    void setTemplatePluginPath(String value);

    /**
     * Returns the value of the '<em><b>Template Name</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Template Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Template Name</em>' attribute.
     * @see #setTemplateName(String)
     * @see nexcore.tool.mda.model.developer.transformation.TransformationPackage#getMDADevRuleSet_TemplateName()
     * @model required="true"
     * @generated
     */
    String getTemplateName();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getTemplateName
     * <em>Template Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Template Name</em>' attribute.
     * @see #getTemplateName()
     * @generated
     */
    void setTemplateName(String value);

    /**
     * Returns the value of the '<em><b>Target Object Naming</b></em>'
     * attribute. The default value is <code>""</code>. The literals are from
     * the enumeration
     * {@link nexcore.tool.mda.model.developer.transformation.TargetObjectNameType}
     * . <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target Object Naming</em>' attribute isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Target Object Naming</em>' attribute.
     * @see nexcore.tool.mda.model.developer.transformation.TargetObjectNameType
     * @see #setTargetObjectNaming(TargetObjectNameType)
     * @see nexcore.tool.mda.model.developer.transformation.TransformationPackage#getMDADevRuleSet_TargetObjectNaming()
     * @model default="" required="true"
     * @generated
     */
    TargetObjectNameType getTargetObjectNaming();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getTargetObjectNaming
     * <em>Target Object Naming</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Target Object Naming</em>'
     *            attribute.
     * @see nexcore.tool.mda.model.developer.transformation.TargetObjectNameType
     * @see #getTargetObjectNaming()
     * @generated
     */
    void setTargetObjectNaming(TargetObjectNameType value);

    /**
     * Returns the value of the '<em><b>Target Project Type</b></em>' attribute.
     * The literals are from the enumeration
     * {@link nexcore.tool.mda.model.developer.transformation.TargetProjectType}
     * . <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target Project Type</em>' attribute isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Target Project Type</em>' attribute.
     * @see nexcore.tool.mda.model.developer.transformation.TargetProjectType
     * @see #setTargetProjectType(TargetProjectType)
     * @see nexcore.tool.mda.model.developer.transformation.TransformationPackage#getMDADevRuleSet_TargetProjectType()
     * @model required="true"
     * @generated
     */
    TargetProjectType getTargetProjectType();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getTargetProjectType
     * <em>Target Project Type</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Target Project Type</em>' attribute.
     * @see nexcore.tool.mda.model.developer.transformation.TargetProjectType
     * @see #getTargetProjectType()
     * @generated
     */
    void setTargetProjectType(TargetProjectType value);

    /**
     * Returns the value of the '<em><b>Make Self Operation</b></em>' attribute.
     * The default value is <code>"true"</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Make Self Operation</em>' attribute isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Make Self Operation</em>' attribute.
     * @see #setMakeSelfOperation(boolean)
     * @see nexcore.tool.mda.model.developer.transformation.TransformationPackage#getMDADevRuleSet_MakeSelfOperation()
     * @model default="true" required="true"
     * @generated
     */
    boolean isMakeSelfOperation();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#isMakeSelfOperation
     * <em>Make Self Operation</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Make Self Operation</em>' attribute.
     * @see #isMakeSelfOperation()
     * @generated
     */
    void setMakeSelfOperation(boolean value);

    /**
     * Returns the value of the '<em><b>Reply Message Use</b></em>' attribute.
     * The default value is <code>"0"</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Reply Message Use</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Reply Message Use</em>' attribute.
     * @see #setReplyMessageUse(int)
     * @see nexcore.tool.mda.model.developer.transformation.TransformationPackage#getMDADevRuleSet_ReplyMessageUse()
     * @model default="0" required="true"
     * @generated
     */
    int getReplyMessageUse();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.developer.transformation.MDADevRuleSet#getReplyMessageUse
     * <em>Reply Message Use</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Reply Message Use</em>' attribute.
     * @see #getReplyMessageUse()
     * @generated
     */
    void setReplyMessageUse(int value);

} // MDADevRuleSet
