/*
 * Copyright (c) 2010 SK C&C Co., Ltd. All rights reserved.
 * 
 * This software is the confidential and proprietary information of SK C&C. You
 * shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK
 * C&C.
 */

package nexcore.tool.mda.model.developer.transformation;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Class Rule</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link nexcore.tool.mda.model.developer.transformation.ClassRule#getName
 * <em>Name</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.ClassRule#getStereotype
 * <em>Stereotype</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.ClassRule#isGenerateGetterSetter
 * <em>Generate Getter Setter</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.ClassRule#getExtension
 * <em>Extension</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.ClassRule#getVersion
 * <em>Version</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.ClassRule#getTargetFolder
 * <em>Target Folder</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.ClassRule#getPrefix
 * <em>Prefix</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.ClassRule#getPostfix
 * <em>Postfix</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.ClassRule#getTemplate
 * <em>Template</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.ClassRule#getImplements
 * <em>Implements</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.developer.transformation.ClassRule#getExtends
 * <em>Extends</em>}</li>
 * </ul>
 * </p>
 * 
 * @see nexcore.tool.mda.model.developer.transformation.TransformationPackage#getClassRule()
 * @model
 * @generated
 */
public interface ClassRule extends EObject {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "Copyright (c) 2010 SK C&C Co., Ltd. All rights reserved.\r\n\r\nThis software is the confidential and proprietary information of SK C&C.\r\nYou shall not disclose such confidential information and shall use it\r\nonly in accordance with the terms of the license agreement you entered into\r\nwith SK C&C.";

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see nexcore.tool.mda.model.developer.transformation.TransformationPackage#getClassRule_Name()
     * @model required="true"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.developer.transformation.ClassRule#getName
     * <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Stereotype</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Stereotype</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Stereotype</em>' attribute.
     * @see #setStereotype(String)
     * @see nexcore.tool.mda.model.developer.transformation.TransformationPackage#getClassRule_Stereotype()
     * @model required="true"
     * @generated
     */
    String getStereotype();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.developer.transformation.ClassRule#getStereotype
     * <em>Stereotype</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Stereotype</em>' attribute.
     * @see #getStereotype()
     * @generated
     */
    void setStereotype(String value);

    /**
     * Returns the value of the '<em><b>Generate Getter Setter</b></em>'
     * attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Generate Getter Setter</em>' attribute isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Generate Getter Setter</em>' attribute.
     * @see #setGenerateGetterSetter(boolean)
     * @see nexcore.tool.mda.model.developer.transformation.TransformationPackage#getClassRule_GenerateGetterSetter()
     * @model required="true"
     * @generated
     */
    boolean isGenerateGetterSetter();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.developer.transformation.ClassRule#isGenerateGetterSetter
     * <em>Generate Getter Setter</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Generate Getter Setter</em>'
     *            attribute.
     * @see #isGenerateGetterSetter()
     * @generated
     */
    void setGenerateGetterSetter(boolean value);

    /**
     * Returns the value of the '<em><b>Extension</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Extension</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Extension</em>' attribute.
     * @see #setExtension(String)
     * @see nexcore.tool.mda.model.developer.transformation.TransformationPackage#getClassRule_Extension()
     * @model required="true"
     * @generated
     */
    String getExtension();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.developer.transformation.ClassRule#getExtension
     * <em>Extension</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Extension</em>' attribute.
     * @see #getExtension()
     * @generated
     */
    void setExtension(String value);

    /**
     * Returns the value of the '<em><b>Version</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Version</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Version</em>' attribute.
     * @see #setVersion(String)
     * @see nexcore.tool.mda.model.developer.transformation.TransformationPackage#getClassRule_Version()
     * @model required="true"
     * @generated
     */
    String getVersion();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.developer.transformation.ClassRule#getVersion
     * <em>Version</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Version</em>' attribute.
     * @see #getVersion()
     * @generated
     */
    void setVersion(String value);

    /**
     * Returns the value of the '<em><b>Target Folder</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target Folder</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Target Folder</em>' attribute.
     * @see #setTargetFolder(String)
     * @see nexcore.tool.mda.model.developer.transformation.TransformationPackage#getClassRule_TargetFolder()
     * @model required="true"
     * @generated
     */
    String getTargetFolder();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.developer.transformation.ClassRule#getTargetFolder
     * <em>Target Folder</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Target Folder</em>' attribute.
     * @see #getTargetFolder()
     * @generated
     */
    void setTargetFolder(String value);

    /**
     * Returns the value of the '<em><b>Prefix</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Prefix</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Prefix</em>' attribute.
     * @see #setPrefix(String)
     * @see nexcore.tool.mda.model.developer.transformation.TransformationPackage#getClassRule_Prefix()
     * @model required="true"
     * @generated
     */
    String getPrefix();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.developer.transformation.ClassRule#getPrefix
     * <em>Prefix</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Prefix</em>' attribute.
     * @see #getPrefix()
     * @generated
     */
    void setPrefix(String value);

    /**
     * Returns the value of the '<em><b>Postfix</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Postfix</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Postfix</em>' attribute.
     * @see #setPostfix(String)
     * @see nexcore.tool.mda.model.developer.transformation.TransformationPackage#getClassRule_Postfix()
     * @model required="true"
     * @generated
     */
    String getPostfix();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.developer.transformation.ClassRule#getPostfix
     * <em>Postfix</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Postfix</em>' attribute.
     * @see #getPostfix()
     * @generated
     */
    void setPostfix(String value);

    /**
     * Returns the value of the '<em><b>Template</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Template</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Template</em>' attribute.
     * @see #setTemplate(String)
     * @see nexcore.tool.mda.model.developer.transformation.TransformationPackage#getClassRule_Template()
     * @model required="true"
     * @generated
     */
    String getTemplate();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.developer.transformation.ClassRule#getTemplate
     * <em>Template</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Template</em>' attribute.
     * @see #getTemplate()
     * @generated
     */
    void setTemplate(String value);

    /**
     * Returns the value of the '<em><b>Implements</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Implements</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Implements</em>' attribute.
     * @see #setImplements(String)
     * @see nexcore.tool.mda.model.developer.transformation.TransformationPackage#getClassRule_Implements()
     * @model
     * @generated
     */
    String getImplements();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.developer.transformation.ClassRule#getImplements
     * <em>Implements</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Implements</em>' attribute.
     * @see #getImplements()
     * @generated
     */
    void setImplements(String value);

    /**
     * Returns the value of the '<em><b>Extends</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Extends</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Extends</em>' attribute.
     * @see #setExtends(String)
     * @see nexcore.tool.mda.model.developer.transformation.TransformationPackage#getClassRule_Extends()
     * @model
     * @generated
     */
    String getExtends();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.mda.model.developer.transformation.ClassRule#getExtends
     * <em>Extends</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Extends</em>' attribute.
     * @see #getExtends()
     * @generated
     */
    void setExtends(String value);

} // ClassRule
