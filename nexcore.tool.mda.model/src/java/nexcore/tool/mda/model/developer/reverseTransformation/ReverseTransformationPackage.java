/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.mda.model.developer.reverseTransformation;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see nexcore.tool.mda.model.developer.reverseTransformation.ReverseTransformationFactory
 * @model kind="package"
 * @generated
 */
public interface ReverseTransformationPackage extends EPackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "";

    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "reverseTransformation";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://nexcore.skcc.com/tools/mdac/reverseTransformation";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "re";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    ReverseTransformationPackage eINSTANCE = nexcore.tool.mda.model.developer.reverseTransformation.impl.ReverseTransformationPackageImpl.init();

    /**
     * The meta object id for the '{@link nexcore.tool.mda.model.developer.reverseTransformation.impl.MDAReverseRuleSetImpl <em>MDA Reverse Rule Set</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see nexcore.tool.mda.model.developer.reverseTransformation.impl.MDAReverseRuleSetImpl
     * @see nexcore.tool.mda.model.developer.reverseTransformation.impl.ReverseTransformationPackageImpl#getMDAReverseRuleSet()
     * @generated
     */
    int MDA_REVERSE_RULE_SET = 0;

    /**
     * The feature id for the '<em><b>Source Project Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MDA_REVERSE_RULE_SET__SOURCE_PROJECT_TYPE = 0;

    /**
     * The feature id for the '<em><b>Source Language</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MDA_REVERSE_RULE_SET__SOURCE_LANGUAGE = 1;

    /**
     * The feature id for the '<em><b>Source Projects</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MDA_REVERSE_RULE_SET__SOURCE_PROJECTS = 2;

    /**
     * The feature id for the '<em><b>Target Model URI</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MDA_REVERSE_RULE_SET__TARGET_MODEL_URI = 3;

    /**
     * The feature id for the '<em><b>Merge Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MDA_REVERSE_RULE_SET__MERGE_TYPE = 4;

    /**
     * The feature id for the '<em><b>Reference Model Change</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MDA_REVERSE_RULE_SET__REFERENCE_MODEL_CHANGE = 5;

    /**
     * The feature id for the '<em><b>Profile Location</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MDA_REVERSE_RULE_SET__PROFILE_LOCATION = 6;

    /**
     * The feature id for the '<em><b>Class Rules</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MDA_REVERSE_RULE_SET__CLASS_RULES = 7;

    /**
     * The feature id for the '<em><b>Class Diagram Rule</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MDA_REVERSE_RULE_SET__CLASS_DIAGRAM_RULE = 8;

    /**
     * The feature id for the '<em><b>Sequence Diagram Rule</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MDA_REVERSE_RULE_SET__SEQUENCE_DIAGRAM_RULE = 9;

    /**
     * The number of structural features of the '<em>MDA Reverse Rule Set</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MDA_REVERSE_RULE_SET_FEATURE_COUNT = 10;

    /**
     * The meta object id for the '{@link nexcore.tool.mda.model.developer.reverseTransformation.impl.ElementRuleImpl <em>Element Rule</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see nexcore.tool.mda.model.developer.reverseTransformation.impl.ElementRuleImpl
     * @see nexcore.tool.mda.model.developer.reverseTransformation.impl.ReverseTransformationPackageImpl#getElementRule()
     * @generated
     */
    int ELEMENT_RULE = 4;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ELEMENT_RULE__NAME = 0;

    /**
     * The feature id for the '<em><b>Prefix</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ELEMENT_RULE__PREFIX = 1;

    /**
     * The feature id for the '<em><b>Postfix</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ELEMENT_RULE__POSTFIX = 2;

    /**
     * The feature id for the '<em><b>Create YN</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ELEMENT_RULE__CREATE_YN = 3;

    /**
     * The feature id for the '<em><b>Stereotype</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ELEMENT_RULE__STEREOTYPE = 4;

    /**
     * The number of structural features of the '<em>Element Rule</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ELEMENT_RULE_FEATURE_COUNT = 5;

    /**
     * The meta object id for the '{@link nexcore.tool.mda.model.developer.reverseTransformation.impl.ClassRuleImpl <em>Class Rule</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see nexcore.tool.mda.model.developer.reverseTransformation.impl.ClassRuleImpl
     * @see nexcore.tool.mda.model.developer.reverseTransformation.impl.ReverseTransformationPackageImpl#getClassRule()
     * @generated
     */
    int CLASS_RULE = 1;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_RULE__NAME = ELEMENT_RULE__NAME;

    /**
     * The feature id for the '<em><b>Prefix</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_RULE__PREFIX = ELEMENT_RULE__PREFIX;

    /**
     * The feature id for the '<em><b>Postfix</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_RULE__POSTFIX = ELEMENT_RULE__POSTFIX;

    /**
     * The feature id for the '<em><b>Create YN</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_RULE__CREATE_YN = ELEMENT_RULE__CREATE_YN;

    /**
     * The feature id for the '<em><b>Stereotype</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_RULE__STEREOTYPE = ELEMENT_RULE__STEREOTYPE;

    /**
     * The feature id for the '<em><b>Package Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_RULE__PACKAGE_NAME = ELEMENT_RULE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Generate Getter Setter</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_RULE__GENERATE_GETTER_SETTER = ELEMENT_RULE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Generate Constructor</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_RULE__GENERATE_CONSTRUCTOR = ELEMENT_RULE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Generate Destructor</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_RULE__GENERATE_DESTRUCTOR = ELEMENT_RULE_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Operation Rules</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_RULE__OPERATION_RULES = ELEMENT_RULE_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Attribute Rules</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_RULE__ATTRIBUTE_RULES = ELEMENT_RULE_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '<em>Class Rule</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_RULE_FEATURE_COUNT = ELEMENT_RULE_FEATURE_COUNT + 6;

    /**
     * The meta object id for the '{@link nexcore.tool.mda.model.developer.reverseTransformation.impl.DiagramRuleImpl <em>Diagram Rule</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see nexcore.tool.mda.model.developer.reverseTransformation.impl.DiagramRuleImpl
     * @see nexcore.tool.mda.model.developer.reverseTransformation.impl.ReverseTransformationPackageImpl#getDiagramRule()
     * @generated
     */
    int DIAGRAM_RULE = 2;

    /**
     * The feature id for the '<em><b>Base Package</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIAGRAM_RULE__BASE_PACKAGE = 0;

    /**
     * The feature id for the '<em><b>Package Depth</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIAGRAM_RULE__PACKAGE_DEPTH = 1;

    /**
     * The feature id for the '<em><b>Create YN</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIAGRAM_RULE__CREATE_YN = 2;

    /**
     * The number of structural features of the '<em>Diagram Rule</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIAGRAM_RULE_FEATURE_COUNT = 3;

    /**
     * The meta object id for the '{@link nexcore.tool.mda.model.developer.reverseTransformation.impl.ClassDiagramRuleImpl <em>Class Diagram Rule</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see nexcore.tool.mda.model.developer.reverseTransformation.impl.ClassDiagramRuleImpl
     * @see nexcore.tool.mda.model.developer.reverseTransformation.impl.ReverseTransformationPackageImpl#getClassDiagramRule()
     * @generated
     */
    int CLASS_DIAGRAM_RULE = 3;

    /**
     * The feature id for the '<em><b>Base Package</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_DIAGRAM_RULE__BASE_PACKAGE = DIAGRAM_RULE__BASE_PACKAGE;

    /**
     * The feature id for the '<em><b>Package Depth</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_DIAGRAM_RULE__PACKAGE_DEPTH = DIAGRAM_RULE__PACKAGE_DEPTH;

    /**
     * The feature id for the '<em><b>Create YN</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_DIAGRAM_RULE__CREATE_YN = DIAGRAM_RULE__CREATE_YN;

    /**
     * The number of structural features of the '<em>Class Diagram Rule</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CLASS_DIAGRAM_RULE_FEATURE_COUNT = DIAGRAM_RULE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link nexcore.tool.mda.model.developer.reverseTransformation.impl.SequenceDiagramRuleImpl <em>Sequence Diagram Rule</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see nexcore.tool.mda.model.developer.reverseTransformation.impl.SequenceDiagramRuleImpl
     * @see nexcore.tool.mda.model.developer.reverseTransformation.impl.ReverseTransformationPackageImpl#getSequenceDiagramRule()
     * @generated
     */
    int SEQUENCE_DIAGRAM_RULE = 5;

    /**
     * The feature id for the '<em><b>Base Package</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEQUENCE_DIAGRAM_RULE__BASE_PACKAGE = DIAGRAM_RULE__BASE_PACKAGE;

    /**
     * The feature id for the '<em><b>Package Depth</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEQUENCE_DIAGRAM_RULE__PACKAGE_DEPTH = DIAGRAM_RULE__PACKAGE_DEPTH;

    /**
     * The feature id for the '<em><b>Create YN</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEQUENCE_DIAGRAM_RULE__CREATE_YN = DIAGRAM_RULE__CREATE_YN;

    /**
     * The feature id for the '<em><b>Create VOPCYN</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEQUENCE_DIAGRAM_RULE__CREATE_VOPCYN = DIAGRAM_RULE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Message Call Depth</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEQUENCE_DIAGRAM_RULE__MESSAGE_CALL_DEPTH = DIAGRAM_RULE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Sequence Diagram Rule</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEQUENCE_DIAGRAM_RULE_FEATURE_COUNT = DIAGRAM_RULE_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link nexcore.tool.mda.model.developer.reverseTransformation.SourceProjectType <em>Source Project Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see nexcore.tool.mda.model.developer.reverseTransformation.SourceProjectType
     * @see nexcore.tool.mda.model.developer.reverseTransformation.impl.ReverseTransformationPackageImpl#getSourceProjectType()
     * @generated
     */
    int SOURCE_PROJECT_TYPE = 6;


    /**
     * Returns the meta object for class '{@link nexcore.tool.mda.model.developer.reverseTransformation.MDAReverseRuleSet <em>MDA Reverse Rule Set</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>MDA Reverse Rule Set</em>'.
     * @see nexcore.tool.mda.model.developer.reverseTransformation.MDAReverseRuleSet
     * @generated
     */
    EClass getMDAReverseRuleSet();

    /**
     * Returns the meta object for the attribute '{@link nexcore.tool.mda.model.developer.reverseTransformation.MDAReverseRuleSet#getSourceProjectType <em>Source Project Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Source Project Type</em>'.
     * @see nexcore.tool.mda.model.developer.reverseTransformation.MDAReverseRuleSet#getSourceProjectType()
     * @see #getMDAReverseRuleSet()
     * @generated
     */
    EAttribute getMDAReverseRuleSet_SourceProjectType();

    /**
     * Returns the meta object for the attribute '{@link nexcore.tool.mda.model.developer.reverseTransformation.MDAReverseRuleSet#getSourceLanguage <em>Source Language</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Source Language</em>'.
     * @see nexcore.tool.mda.model.developer.reverseTransformation.MDAReverseRuleSet#getSourceLanguage()
     * @see #getMDAReverseRuleSet()
     * @generated
     */
    EAttribute getMDAReverseRuleSet_SourceLanguage();

    /**
     * Returns the meta object for the attribute list '{@link nexcore.tool.mda.model.developer.reverseTransformation.MDAReverseRuleSet#getSourceProjects <em>Source Projects</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Source Projects</em>'.
     * @see nexcore.tool.mda.model.developer.reverseTransformation.MDAReverseRuleSet#getSourceProjects()
     * @see #getMDAReverseRuleSet()
     * @generated
     */
    EAttribute getMDAReverseRuleSet_SourceProjects();

    /**
     * Returns the meta object for the attribute '{@link nexcore.tool.mda.model.developer.reverseTransformation.MDAReverseRuleSet#getTargetModelURI <em>Target Model URI</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Target Model URI</em>'.
     * @see nexcore.tool.mda.model.developer.reverseTransformation.MDAReverseRuleSet#getTargetModelURI()
     * @see #getMDAReverseRuleSet()
     * @generated
     */
    EAttribute getMDAReverseRuleSet_TargetModelURI();

    /**
     * Returns the meta object for the attribute '{@link nexcore.tool.mda.model.developer.reverseTransformation.MDAReverseRuleSet#getMergeType <em>Merge Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Merge Type</em>'.
     * @see nexcore.tool.mda.model.developer.reverseTransformation.MDAReverseRuleSet#getMergeType()
     * @see #getMDAReverseRuleSet()
     * @generated
     */
    EAttribute getMDAReverseRuleSet_MergeType();

    /**
     * Returns the meta object for the attribute '{@link nexcore.tool.mda.model.developer.reverseTransformation.MDAReverseRuleSet#isReferenceModelChange <em>Reference Model Change</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Reference Model Change</em>'.
     * @see nexcore.tool.mda.model.developer.reverseTransformation.MDAReverseRuleSet#isReferenceModelChange()
     * @see #getMDAReverseRuleSet()
     * @generated
     */
    EAttribute getMDAReverseRuleSet_ReferenceModelChange();

    /**
     * Returns the meta object for the attribute '{@link nexcore.tool.mda.model.developer.reverseTransformation.MDAReverseRuleSet#getProfileLocation <em>Profile Location</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Profile Location</em>'.
     * @see nexcore.tool.mda.model.developer.reverseTransformation.MDAReverseRuleSet#getProfileLocation()
     * @see #getMDAReverseRuleSet()
     * @generated
     */
    EAttribute getMDAReverseRuleSet_ProfileLocation();

    /**
     * Returns the meta object for the containment reference list '{@link nexcore.tool.mda.model.developer.reverseTransformation.MDAReverseRuleSet#getClassRules <em>Class Rules</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Class Rules</em>'.
     * @see nexcore.tool.mda.model.developer.reverseTransformation.MDAReverseRuleSet#getClassRules()
     * @see #getMDAReverseRuleSet()
     * @generated
     */
    EReference getMDAReverseRuleSet_ClassRules();

    /**
     * Returns the meta object for the containment reference '{@link nexcore.tool.mda.model.developer.reverseTransformation.MDAReverseRuleSet#getClassDiagramRule <em>Class Diagram Rule</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Class Diagram Rule</em>'.
     * @see nexcore.tool.mda.model.developer.reverseTransformation.MDAReverseRuleSet#getClassDiagramRule()
     * @see #getMDAReverseRuleSet()
     * @generated
     */
    EReference getMDAReverseRuleSet_ClassDiagramRule();

    /**
     * Returns the meta object for the containment reference '{@link nexcore.tool.mda.model.developer.reverseTransformation.MDAReverseRuleSet#getSequenceDiagramRule <em>Sequence Diagram Rule</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Sequence Diagram Rule</em>'.
     * @see nexcore.tool.mda.model.developer.reverseTransformation.MDAReverseRuleSet#getSequenceDiagramRule()
     * @see #getMDAReverseRuleSet()
     * @generated
     */
    EReference getMDAReverseRuleSet_SequenceDiagramRule();

    /**
     * Returns the meta object for class '{@link nexcore.tool.mda.model.developer.reverseTransformation.ClassRule <em>Class Rule</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Class Rule</em>'.
     * @see nexcore.tool.mda.model.developer.reverseTransformation.ClassRule
     * @generated
     */
    EClass getClassRule();

    /**
     * Returns the meta object for the attribute '{@link nexcore.tool.mda.model.developer.reverseTransformation.ClassRule#getPackageName <em>Package Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Package Name</em>'.
     * @see nexcore.tool.mda.model.developer.reverseTransformation.ClassRule#getPackageName()
     * @see #getClassRule()
     * @generated
     */
    EAttribute getClassRule_PackageName();

    /**
     * Returns the meta object for the attribute '{@link nexcore.tool.mda.model.developer.reverseTransformation.ClassRule#isGenerateGetterSetter <em>Generate Getter Setter</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Generate Getter Setter</em>'.
     * @see nexcore.tool.mda.model.developer.reverseTransformation.ClassRule#isGenerateGetterSetter()
     * @see #getClassRule()
     * @generated
     */
    EAttribute getClassRule_GenerateGetterSetter();

    /**
     * Returns the meta object for the attribute '{@link nexcore.tool.mda.model.developer.reverseTransformation.ClassRule#isGenerateConstructor <em>Generate Constructor</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Generate Constructor</em>'.
     * @see nexcore.tool.mda.model.developer.reverseTransformation.ClassRule#isGenerateConstructor()
     * @see #getClassRule()
     * @generated
     */
    EAttribute getClassRule_GenerateConstructor();

    /**
     * Returns the meta object for the attribute '{@link nexcore.tool.mda.model.developer.reverseTransformation.ClassRule#isGenerateDestructor <em>Generate Destructor</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Generate Destructor</em>'.
     * @see nexcore.tool.mda.model.developer.reverseTransformation.ClassRule#isGenerateDestructor()
     * @see #getClassRule()
     * @generated
     */
    EAttribute getClassRule_GenerateDestructor();

    /**
     * Returns the meta object for the containment reference list '{@link nexcore.tool.mda.model.developer.reverseTransformation.ClassRule#getOperationRules <em>Operation Rules</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Operation Rules</em>'.
     * @see nexcore.tool.mda.model.developer.reverseTransformation.ClassRule#getOperationRules()
     * @see #getClassRule()
     * @generated
     */
    EReference getClassRule_OperationRules();

    /**
     * Returns the meta object for the containment reference list '{@link nexcore.tool.mda.model.developer.reverseTransformation.ClassRule#getAttributeRules <em>Attribute Rules</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Attribute Rules</em>'.
     * @see nexcore.tool.mda.model.developer.reverseTransformation.ClassRule#getAttributeRules()
     * @see #getClassRule()
     * @generated
     */
    EReference getClassRule_AttributeRules();

    /**
     * Returns the meta object for class '{@link nexcore.tool.mda.model.developer.reverseTransformation.DiagramRule <em>Diagram Rule</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Diagram Rule</em>'.
     * @see nexcore.tool.mda.model.developer.reverseTransformation.DiagramRule
     * @generated
     */
    EClass getDiagramRule();

    /**
     * Returns the meta object for the attribute '{@link nexcore.tool.mda.model.developer.reverseTransformation.DiagramRule#getBasePackage <em>Base Package</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Base Package</em>'.
     * @see nexcore.tool.mda.model.developer.reverseTransformation.DiagramRule#getBasePackage()
     * @see #getDiagramRule()
     * @generated
     */
    EAttribute getDiagramRule_BasePackage();

    /**
     * Returns the meta object for the attribute '{@link nexcore.tool.mda.model.developer.reverseTransformation.DiagramRule#getPackageDepth <em>Package Depth</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Package Depth</em>'.
     * @see nexcore.tool.mda.model.developer.reverseTransformation.DiagramRule#getPackageDepth()
     * @see #getDiagramRule()
     * @generated
     */
    EAttribute getDiagramRule_PackageDepth();

    /**
     * Returns the meta object for the attribute '{@link nexcore.tool.mda.model.developer.reverseTransformation.DiagramRule#isCreateYN <em>Create YN</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Create YN</em>'.
     * @see nexcore.tool.mda.model.developer.reverseTransformation.DiagramRule#isCreateYN()
     * @see #getDiagramRule()
     * @generated
     */
    EAttribute getDiagramRule_CreateYN();

    /**
     * Returns the meta object for class '{@link nexcore.tool.mda.model.developer.reverseTransformation.ClassDiagramRule <em>Class Diagram Rule</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Class Diagram Rule</em>'.
     * @see nexcore.tool.mda.model.developer.reverseTransformation.ClassDiagramRule
     * @generated
     */
    EClass getClassDiagramRule();

    /**
     * Returns the meta object for class '{@link nexcore.tool.mda.model.developer.reverseTransformation.ElementRule <em>Element Rule</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Element Rule</em>'.
     * @see nexcore.tool.mda.model.developer.reverseTransformation.ElementRule
     * @generated
     */
    EClass getElementRule();

    /**
     * Returns the meta object for the attribute '{@link nexcore.tool.mda.model.developer.reverseTransformation.ElementRule#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see nexcore.tool.mda.model.developer.reverseTransformation.ElementRule#getName()
     * @see #getElementRule()
     * @generated
     */
    EAttribute getElementRule_Name();

    /**
     * Returns the meta object for the attribute '{@link nexcore.tool.mda.model.developer.reverseTransformation.ElementRule#getPrefix <em>Prefix</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Prefix</em>'.
     * @see nexcore.tool.mda.model.developer.reverseTransformation.ElementRule#getPrefix()
     * @see #getElementRule()
     * @generated
     */
    EAttribute getElementRule_Prefix();

    /**
     * Returns the meta object for the attribute '{@link nexcore.tool.mda.model.developer.reverseTransformation.ElementRule#getPostfix <em>Postfix</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Postfix</em>'.
     * @see nexcore.tool.mda.model.developer.reverseTransformation.ElementRule#getPostfix()
     * @see #getElementRule()
     * @generated
     */
    EAttribute getElementRule_Postfix();

    /**
     * Returns the meta object for the attribute '{@link nexcore.tool.mda.model.developer.reverseTransformation.ElementRule#isCreateYN <em>Create YN</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Create YN</em>'.
     * @see nexcore.tool.mda.model.developer.reverseTransformation.ElementRule#isCreateYN()
     * @see #getElementRule()
     * @generated
     */
    EAttribute getElementRule_CreateYN();

    /**
     * Returns the meta object for the attribute '{@link nexcore.tool.mda.model.developer.reverseTransformation.ElementRule#getStereotype <em>Stereotype</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Stereotype</em>'.
     * @see nexcore.tool.mda.model.developer.reverseTransformation.ElementRule#getStereotype()
     * @see #getElementRule()
     * @generated
     */
    EAttribute getElementRule_Stereotype();

    /**
     * Returns the meta object for class '{@link nexcore.tool.mda.model.developer.reverseTransformation.SequenceDiagramRule <em>Sequence Diagram Rule</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Sequence Diagram Rule</em>'.
     * @see nexcore.tool.mda.model.developer.reverseTransformation.SequenceDiagramRule
     * @generated
     */
    EClass getSequenceDiagramRule();

    /**
     * Returns the meta object for the attribute '{@link nexcore.tool.mda.model.developer.reverseTransformation.SequenceDiagramRule#isCreateVOPCYN <em>Create VOPCYN</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Create VOPCYN</em>'.
     * @see nexcore.tool.mda.model.developer.reverseTransformation.SequenceDiagramRule#isCreateVOPCYN()
     * @see #getSequenceDiagramRule()
     * @generated
     */
    EAttribute getSequenceDiagramRule_CreateVOPCYN();

    /**
     * Returns the meta object for the attribute '{@link nexcore.tool.mda.model.developer.reverseTransformation.SequenceDiagramRule#getMessageCallDepth <em>Message Call Depth</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Message Call Depth</em>'.
     * @see nexcore.tool.mda.model.developer.reverseTransformation.SequenceDiagramRule#getMessageCallDepth()
     * @see #getSequenceDiagramRule()
     * @generated
     */
    EAttribute getSequenceDiagramRule_MessageCallDepth();

    /**
     * Returns the meta object for enum '{@link nexcore.tool.mda.model.developer.reverseTransformation.SourceProjectType <em>Source Project Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Source Project Type</em>'.
     * @see nexcore.tool.mda.model.developer.reverseTransformation.SourceProjectType
     * @generated
     */
    EEnum getSourceProjectType();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    ReverseTransformationFactory getReverseTransformationFactory();

    /**
     * <!-- begin-user-doc -->
     * Defines literals for the meta objects that represent
     * <ul>
     *   <li>each class,</li>
     *   <li>each feature of each class,</li>
     *   <li>each enum,</li>
     *   <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '{@link nexcore.tool.mda.model.developer.reverseTransformation.impl.MDAReverseRuleSetImpl <em>MDA Reverse Rule Set</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see nexcore.tool.mda.model.developer.reverseTransformation.impl.MDAReverseRuleSetImpl
         * @see nexcore.tool.mda.model.developer.reverseTransformation.impl.ReverseTransformationPackageImpl#getMDAReverseRuleSet()
         * @generated
         */
        EClass MDA_REVERSE_RULE_SET = eINSTANCE.getMDAReverseRuleSet();

        /**
         * The meta object literal for the '<em><b>Source Project Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute MDA_REVERSE_RULE_SET__SOURCE_PROJECT_TYPE = eINSTANCE.getMDAReverseRuleSet_SourceProjectType();

        /**
         * The meta object literal for the '<em><b>Source Language</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute MDA_REVERSE_RULE_SET__SOURCE_LANGUAGE = eINSTANCE.getMDAReverseRuleSet_SourceLanguage();

        /**
         * The meta object literal for the '<em><b>Source Projects</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute MDA_REVERSE_RULE_SET__SOURCE_PROJECTS = eINSTANCE.getMDAReverseRuleSet_SourceProjects();

        /**
         * The meta object literal for the '<em><b>Target Model URI</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute MDA_REVERSE_RULE_SET__TARGET_MODEL_URI = eINSTANCE.getMDAReverseRuleSet_TargetModelURI();

        /**
         * The meta object literal for the '<em><b>Merge Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute MDA_REVERSE_RULE_SET__MERGE_TYPE = eINSTANCE.getMDAReverseRuleSet_MergeType();

        /**
         * The meta object literal for the '<em><b>Reference Model Change</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute MDA_REVERSE_RULE_SET__REFERENCE_MODEL_CHANGE = eINSTANCE.getMDAReverseRuleSet_ReferenceModelChange();

        /**
         * The meta object literal for the '<em><b>Profile Location</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute MDA_REVERSE_RULE_SET__PROFILE_LOCATION = eINSTANCE.getMDAReverseRuleSet_ProfileLocation();

        /**
         * The meta object literal for the '<em><b>Class Rules</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference MDA_REVERSE_RULE_SET__CLASS_RULES = eINSTANCE.getMDAReverseRuleSet_ClassRules();

        /**
         * The meta object literal for the '<em><b>Class Diagram Rule</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference MDA_REVERSE_RULE_SET__CLASS_DIAGRAM_RULE = eINSTANCE.getMDAReverseRuleSet_ClassDiagramRule();

        /**
         * The meta object literal for the '<em><b>Sequence Diagram Rule</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference MDA_REVERSE_RULE_SET__SEQUENCE_DIAGRAM_RULE = eINSTANCE.getMDAReverseRuleSet_SequenceDiagramRule();

        /**
         * The meta object literal for the '{@link nexcore.tool.mda.model.developer.reverseTransformation.impl.ClassRuleImpl <em>Class Rule</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see nexcore.tool.mda.model.developer.reverseTransformation.impl.ClassRuleImpl
         * @see nexcore.tool.mda.model.developer.reverseTransformation.impl.ReverseTransformationPackageImpl#getClassRule()
         * @generated
         */
        EClass CLASS_RULE = eINSTANCE.getClassRule();

        /**
         * The meta object literal for the '<em><b>Package Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute CLASS_RULE__PACKAGE_NAME = eINSTANCE.getClassRule_PackageName();

        /**
         * The meta object literal for the '<em><b>Generate Getter Setter</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute CLASS_RULE__GENERATE_GETTER_SETTER = eINSTANCE.getClassRule_GenerateGetterSetter();

        /**
         * The meta object literal for the '<em><b>Generate Constructor</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute CLASS_RULE__GENERATE_CONSTRUCTOR = eINSTANCE.getClassRule_GenerateConstructor();

        /**
         * The meta object literal for the '<em><b>Generate Destructor</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute CLASS_RULE__GENERATE_DESTRUCTOR = eINSTANCE.getClassRule_GenerateDestructor();

        /**
         * The meta object literal for the '<em><b>Operation Rules</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CLASS_RULE__OPERATION_RULES = eINSTANCE.getClassRule_OperationRules();

        /**
         * The meta object literal for the '<em><b>Attribute Rules</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CLASS_RULE__ATTRIBUTE_RULES = eINSTANCE.getClassRule_AttributeRules();

        /**
         * The meta object literal for the '{@link nexcore.tool.mda.model.developer.reverseTransformation.impl.DiagramRuleImpl <em>Diagram Rule</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see nexcore.tool.mda.model.developer.reverseTransformation.impl.DiagramRuleImpl
         * @see nexcore.tool.mda.model.developer.reverseTransformation.impl.ReverseTransformationPackageImpl#getDiagramRule()
         * @generated
         */
        EClass DIAGRAM_RULE = eINSTANCE.getDiagramRule();

        /**
         * The meta object literal for the '<em><b>Base Package</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DIAGRAM_RULE__BASE_PACKAGE = eINSTANCE.getDiagramRule_BasePackage();

        /**
         * The meta object literal for the '<em><b>Package Depth</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DIAGRAM_RULE__PACKAGE_DEPTH = eINSTANCE.getDiagramRule_PackageDepth();

        /**
         * The meta object literal for the '<em><b>Create YN</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DIAGRAM_RULE__CREATE_YN = eINSTANCE.getDiagramRule_CreateYN();

        /**
         * The meta object literal for the '{@link nexcore.tool.mda.model.developer.reverseTransformation.impl.ClassDiagramRuleImpl <em>Class Diagram Rule</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see nexcore.tool.mda.model.developer.reverseTransformation.impl.ClassDiagramRuleImpl
         * @see nexcore.tool.mda.model.developer.reverseTransformation.impl.ReverseTransformationPackageImpl#getClassDiagramRule()
         * @generated
         */
        EClass CLASS_DIAGRAM_RULE = eINSTANCE.getClassDiagramRule();

        /**
         * The meta object literal for the '{@link nexcore.tool.mda.model.developer.reverseTransformation.impl.ElementRuleImpl <em>Element Rule</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see nexcore.tool.mda.model.developer.reverseTransformation.impl.ElementRuleImpl
         * @see nexcore.tool.mda.model.developer.reverseTransformation.impl.ReverseTransformationPackageImpl#getElementRule()
         * @generated
         */
        EClass ELEMENT_RULE = eINSTANCE.getElementRule();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ELEMENT_RULE__NAME = eINSTANCE.getElementRule_Name();

        /**
         * The meta object literal for the '<em><b>Prefix</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ELEMENT_RULE__PREFIX = eINSTANCE.getElementRule_Prefix();

        /**
         * The meta object literal for the '<em><b>Postfix</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ELEMENT_RULE__POSTFIX = eINSTANCE.getElementRule_Postfix();

        /**
         * The meta object literal for the '<em><b>Create YN</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ELEMENT_RULE__CREATE_YN = eINSTANCE.getElementRule_CreateYN();

        /**
         * The meta object literal for the '<em><b>Stereotype</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ELEMENT_RULE__STEREOTYPE = eINSTANCE.getElementRule_Stereotype();

        /**
         * The meta object literal for the '{@link nexcore.tool.mda.model.developer.reverseTransformation.impl.SequenceDiagramRuleImpl <em>Sequence Diagram Rule</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see nexcore.tool.mda.model.developer.reverseTransformation.impl.SequenceDiagramRuleImpl
         * @see nexcore.tool.mda.model.developer.reverseTransformation.impl.ReverseTransformationPackageImpl#getSequenceDiagramRule()
         * @generated
         */
        EClass SEQUENCE_DIAGRAM_RULE = eINSTANCE.getSequenceDiagramRule();

        /**
         * The meta object literal for the '<em><b>Create VOPCYN</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SEQUENCE_DIAGRAM_RULE__CREATE_VOPCYN = eINSTANCE.getSequenceDiagramRule_CreateVOPCYN();

        /**
         * The meta object literal for the '<em><b>Message Call Depth</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SEQUENCE_DIAGRAM_RULE__MESSAGE_CALL_DEPTH = eINSTANCE.getSequenceDiagramRule_MessageCallDepth();

        /**
         * The meta object literal for the '{@link nexcore.tool.mda.model.developer.reverseTransformation.SourceProjectType <em>Source Project Type</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see nexcore.tool.mda.model.developer.reverseTransformation.SourceProjectType
         * @see nexcore.tool.mda.model.developer.reverseTransformation.impl.ReverseTransformationPackageImpl#getSourceProjectType()
         * @generated
         */
        EEnum SOURCE_PROJECT_TYPE = eINSTANCE.getSourceProjectType();

    }

} //ReverseTransformationPackage
