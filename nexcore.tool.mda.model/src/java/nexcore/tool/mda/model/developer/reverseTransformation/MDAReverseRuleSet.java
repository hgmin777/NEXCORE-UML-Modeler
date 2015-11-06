/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.mda.model.developer.reverseTransformation;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>MDA Reverse Rule Set</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.MDAReverseRuleSet#getSourceProjectType <em>Source Project Type</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.MDAReverseRuleSet#getSourceLanguage <em>Source Language</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.MDAReverseRuleSet#getSourceProjects <em>Source Projects</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.MDAReverseRuleSet#getTargetModelURI <em>Target Model URI</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.MDAReverseRuleSet#getMergeType <em>Merge Type</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.MDAReverseRuleSet#isReferenceModelChange <em>Reference Model Change</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.MDAReverseRuleSet#getProfileLocation <em>Profile Location</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.MDAReverseRuleSet#getClassRules <em>Class Rules</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.MDAReverseRuleSet#getClassDiagramRule <em>Class Diagram Rule</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.MDAReverseRuleSet#getSequenceDiagramRule <em>Sequence Diagram Rule</em>}</li>
 * </ul>
 * </p>
 *
 * @see nexcore.tool.mda.model.developer.reverseTransformation.ReverseTransformationPackage#getMDAReverseRuleSet()
 * @model
 * @generated
 */
public interface MDAReverseRuleSet extends EObject {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "";

    /**
     * Returns the value of the '<em><b>Source Project Type</b></em>' attribute.
     * The default value is <code>""</code>.
     * The literals are from the enumeration {@link nexcore.tool.mda.model.developer.reverseTransformation.SourceProjectType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Source Project Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Source Project Type</em>' attribute.
     * @see nexcore.tool.mda.model.developer.reverseTransformation.SourceProjectType
     * @see #setSourceProjectType(SourceProjectType)
     * @see nexcore.tool.mda.model.developer.reverseTransformation.ReverseTransformationPackage#getMDAReverseRuleSet_SourceProjectType()
     * @model default="" required="true"
     * @generated
     */
    SourceProjectType getSourceProjectType();

    /**
     * Sets the value of the '{@link nexcore.tool.mda.model.developer.reverseTransformation.MDAReverseRuleSet#getSourceProjectType <em>Source Project Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Source Project Type</em>' attribute.
     * @see nexcore.tool.mda.model.developer.reverseTransformation.SourceProjectType
     * @see #getSourceProjectType()
     * @generated
     */
    void setSourceProjectType(SourceProjectType value);

    /**
     * Returns the value of the '<em><b>Source Language</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Source Language</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Source Language</em>' attribute.
     * @see #setSourceLanguage(String)
     * @see nexcore.tool.mda.model.developer.reverseTransformation.ReverseTransformationPackage#getMDAReverseRuleSet_SourceLanguage()
     * @model required="true"
     * @generated
     */
    String getSourceLanguage();

    /**
     * Sets the value of the '{@link nexcore.tool.mda.model.developer.reverseTransformation.MDAReverseRuleSet#getSourceLanguage <em>Source Language</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Source Language</em>' attribute.
     * @see #getSourceLanguage()
     * @generated
     */
    void setSourceLanguage(String value);

    /**
     * Returns the value of the '<em><b>Source Projects</b></em>' attribute list.
     * The list contents are of type {@link java.lang.String}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Source Projects</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Source Projects</em>' attribute list.
     * @see nexcore.tool.mda.model.developer.reverseTransformation.ReverseTransformationPackage#getMDAReverseRuleSet_SourceProjects()
     * @model required="true"
     * @generated
     */
    EList<String> getSourceProjects();

    /**
     * Returns the value of the '<em><b>Target Model URI</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target Model URI</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Target Model URI</em>' attribute.
     * @see #setTargetModelURI(String)
     * @see nexcore.tool.mda.model.developer.reverseTransformation.ReverseTransformationPackage#getMDAReverseRuleSet_TargetModelURI()
     * @model required="true"
     * @generated
     */
    String getTargetModelURI();

    /**
     * Sets the value of the '{@link nexcore.tool.mda.model.developer.reverseTransformation.MDAReverseRuleSet#getTargetModelURI <em>Target Model URI</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target Model URI</em>' attribute.
     * @see #getTargetModelURI()
     * @generated
     */
    void setTargetModelURI(String value);

    /**
     * Returns the value of the '<em><b>Merge Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Merge Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Merge Type</em>' attribute.
     * @see #setMergeType(String)
     * @see nexcore.tool.mda.model.developer.reverseTransformation.ReverseTransformationPackage#getMDAReverseRuleSet_MergeType()
     * @model required="true"
     * @generated
     */
    String getMergeType();

    /**
     * Sets the value of the '{@link nexcore.tool.mda.model.developer.reverseTransformation.MDAReverseRuleSet#getMergeType <em>Merge Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Merge Type</em>' attribute.
     * @see #getMergeType()
     * @generated
     */
    void setMergeType(String value);

    /**
     * Returns the value of the '<em><b>Reference Model Change</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Reference Model Change</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Reference Model Change</em>' attribute.
     * @see #setReferenceModelChange(boolean)
     * @see nexcore.tool.mda.model.developer.reverseTransformation.ReverseTransformationPackage#getMDAReverseRuleSet_ReferenceModelChange()
     * @model default="false" required="true"
     * @generated
     */
    boolean isReferenceModelChange();

    /**
     * Sets the value of the '{@link nexcore.tool.mda.model.developer.reverseTransformation.MDAReverseRuleSet#isReferenceModelChange <em>Reference Model Change</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Reference Model Change</em>' attribute.
     * @see #isReferenceModelChange()
     * @generated
     */
    void setReferenceModelChange(boolean value);

    /**
     * Returns the value of the '<em><b>Profile Location</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Profile Location</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Profile Location</em>' attribute.
     * @see #setProfileLocation(String)
     * @see nexcore.tool.mda.model.developer.reverseTransformation.ReverseTransformationPackage#getMDAReverseRuleSet_ProfileLocation()
     * @model
     * @generated
     */
    String getProfileLocation();

    /**
     * Sets the value of the '{@link nexcore.tool.mda.model.developer.reverseTransformation.MDAReverseRuleSet#getProfileLocation <em>Profile Location</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Profile Location</em>' attribute.
     * @see #getProfileLocation()
     * @generated
     */
    void setProfileLocation(String value);

    /**
     * Returns the value of the '<em><b>Class Rules</b></em>' containment reference list.
     * The list contents are of type {@link nexcore.tool.mda.model.developer.reverseTransformation.ClassRule}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Class Rules</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Class Rules</em>' containment reference list.
     * @see nexcore.tool.mda.model.developer.reverseTransformation.ReverseTransformationPackage#getMDAReverseRuleSet_ClassRules()
     * @model containment="true"
     * @generated
     */
    EList<ClassRule> getClassRules();

    /**
     * Returns the value of the '<em><b>Class Diagram Rule</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Class Diagram Rule</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Class Diagram Rule</em>' containment reference.
     * @see #setClassDiagramRule(ClassDiagramRule)
     * @see nexcore.tool.mda.model.developer.reverseTransformation.ReverseTransformationPackage#getMDAReverseRuleSet_ClassDiagramRule()
     * @model containment="true" required="true"
     * @generated
     */
    ClassDiagramRule getClassDiagramRule();

    /**
     * Sets the value of the '{@link nexcore.tool.mda.model.developer.reverseTransformation.MDAReverseRuleSet#getClassDiagramRule <em>Class Diagram Rule</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Class Diagram Rule</em>' containment reference.
     * @see #getClassDiagramRule()
     * @generated
     */
    void setClassDiagramRule(ClassDiagramRule value);

    /**
     * Returns the value of the '<em><b>Sequence Diagram Rule</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Sequence Diagram Rule</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Sequence Diagram Rule</em>' containment reference.
     * @see #setSequenceDiagramRule(SequenceDiagramRule)
     * @see nexcore.tool.mda.model.developer.reverseTransformation.ReverseTransformationPackage#getMDAReverseRuleSet_SequenceDiagramRule()
     * @model containment="true" required="true"
     * @generated
     */
    SequenceDiagramRule getSequenceDiagramRule();

    /**
     * Sets the value of the '{@link nexcore.tool.mda.model.developer.reverseTransformation.MDAReverseRuleSet#getSequenceDiagramRule <em>Sequence Diagram Rule</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Sequence Diagram Rule</em>' containment reference.
     * @see #getSequenceDiagramRule()
     * @generated
     */
    void setSequenceDiagramRule(SequenceDiagramRule value);

} // MDAReverseRuleSet
