/*
 * Copyright (c) 2010 SK C&C Co., Ltd. All rights reserved.
 * 
 * This software is the confidential and proprietary information of SK C&C. You
 * shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK
 * C&C.
 */

package nexcore.tool.mda.model.designer.transformation.impl;

import nexcore.tool.mda.model.designer.transformation.ApplicationType;
import nexcore.tool.mda.model.designer.transformation.BehaviorTransformationData;
import nexcore.tool.mda.model.designer.transformation.BehaviorTransformationDetailData;
import nexcore.tool.mda.model.designer.transformation.CapitalizationType;
import nexcore.tool.mda.model.designer.transformation.DataNameType;
import nexcore.tool.mda.model.designer.transformation.ExtensionType;
import nexcore.tool.mda.model.designer.transformation.LocationKeywordType;
import nexcore.tool.mda.model.designer.transformation.LocationSegment;
import nexcore.tool.mda.model.designer.transformation.LocationType;
import nexcore.tool.mda.model.designer.transformation.NameKeywordType;
import nexcore.tool.mda.model.designer.transformation.NamePartType;
import nexcore.tool.mda.model.designer.transformation.NameType;
import nexcore.tool.mda.model.designer.transformation.OperationApplicationType;
import nexcore.tool.mda.model.designer.transformation.OperationType;
import nexcore.tool.mda.model.designer.transformation.ParentType;
import nexcore.tool.mda.model.designer.transformation.RuleSet;
import nexcore.tool.mda.model.designer.transformation.SourceRelationType;
import nexcore.tool.mda.model.designer.transformation.SourceStructureTransformationData;
import nexcore.tool.mda.model.designer.transformation.SourceType;
import nexcore.tool.mda.model.designer.transformation.TargetCreationType;
import nexcore.tool.mda.model.designer.transformation.TargetRelationType;
import nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData;
import nexcore.tool.mda.model.designer.transformation.TargetType;
import nexcore.tool.mda.model.designer.transformation.TransformationFactory;
import nexcore.tool.mda.model.designer.transformation.TransformationPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class TransformationPackageImpl extends EPackageImpl implements TransformationPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = "Copyright (c) 2010 SK C&C Co., Ltd. All rights reserved.\r\n\r\nThis software is the confidential and proprietary information of SK C&C.\r\nYou shall not disclose such confidential information and shall use it\r\nonly in accordance with the terms of the license agreement you entered into\r\nwith SK C&C.";

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass ruleSetEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass sourceStructureTransformationDataEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass sourceTypeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass targetStructureTransformationDataEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass dataNameTypeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass locationTypeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass locationSegmentEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass nameTypeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass namePartTypeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass behaviorTransformationDataEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass sourceRelationTypeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass behaviorTransformationDetailDataEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass targetRelationTypeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass operationTypeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass parentTypeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum targetCreationTypeEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum locationKeywordTypeEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum targetTypeEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum nameKeywordTypeEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum capitalizationTypeEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum applicationTypeEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum operationApplicationTypeEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum extensionTypeEEnum = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the
     * package package URI value.
     * <p>
     * Note: the correct way to create the package is via the static factory
     * method {@link #init init()}, which also performs initialization of the
     * package, or returns the registered package, if one already exists. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private TransformationPackageImpl() {
        super(eNS_URI, TransformationFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model,
     * and for any others upon which it depends. Simple dependencies are
     * satisfied by calling this method on all dependent packages before doing
     * anything else. This method drives initialization for interdependent
     * packages directly, in parallel with this package, itself.
     * <p>
     * Of this package and its interdependencies, all packages which have not
     * yet been registered by their URI values are first created and registered.
     * The packages are then initialized in two steps: meta-model objects for
     * all of the packages are created before any are initialized, since one
     * package's meta-model objects may refer to those of another.
     * <p>
     * Invocation of this method will not affect any packages that have already
     * been initialized. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static TransformationPackage init() {
        if (isInited)
            return (TransformationPackage) EPackage.Registry.INSTANCE.getEPackage(TransformationPackage.eNS_URI);

        // Obtain or create and register package
        TransformationPackageImpl theTransformationPackage = (TransformationPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof TransformationPackageImpl
        ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new TransformationPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        EcorePackage.eINSTANCE.eClass();

        // Create package meta-data objects
        theTransformationPackage.createPackageContents();

        // Initialize created meta-data
        theTransformationPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theTransformationPackage.freeze();

        return theTransformationPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getRuleSet() {
        return ruleSetEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getRuleSet_StructureRules() {
        return (EReference) ruleSetEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getRuleSet_BehaviorRules() {
        return (EReference) ruleSetEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getRuleSet_SourceModelLocation() {
        return (EAttribute) ruleSetEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getRuleSet_SourceProfileLocation() {
        return (EAttribute) ruleSetEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getRuleSet_TargetModelLocation() {
        return (EAttribute) ruleSetEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getRuleSet_TargetProfileLocation() {
        return (EAttribute) ruleSetEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getSourceStructureTransformationData() {
        return sourceStructureTransformationDataEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getSourceStructureTransformationData_SourceType() {
        return (EReference) sourceStructureTransformationDataEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getSourceStructureTransformationData_SourceLocation() {
        return (EReference) sourceStructureTransformationDataEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getSourceStructureTransformationData_TargetStructureTransformationDataSet() {
        return (EReference) sourceStructureTransformationDataEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getSourceType() {
        return sourceTypeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getSourceType_SourceName() {
        return (EAttribute) sourceTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTargetStructureTransformationData() {
        return targetStructureTransformationDataEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTargetStructureTransformationData_DataName() {
        return (EReference) targetStructureTransformationDataEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTargetStructureTransformationData_TargetCreationType() {
        return (EAttribute) targetStructureTransformationDataEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTargetStructureTransformationData_TargetLocation() {
        return (EReference) targetStructureTransformationDataEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTargetStructureTransformationData_TargetApplicableStereotype() {
        return (EAttribute) targetStructureTransformationDataEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTargetStructureTransformationData_TargetType() {
        return (EAttribute) targetStructureTransformationDataEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTargetStructureTransformationData_ParentType() {
        return (EReference) targetStructureTransformationDataEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTargetStructureTransformationData_TargetName() {
        return (EReference) targetStructureTransformationDataEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTargetStructureTransformationData_ApplicationType() {
        return (EAttribute) targetStructureTransformationDataEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTargetStructureTransformationData_PropertyCreation() {
        return (EAttribute) targetStructureTransformationDataEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTargetStructureTransformationData_PropertyApplicableStereotype() {
        return (EAttribute) targetStructureTransformationDataEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTargetStructureTransformationData_OperationCreation() {
        return (EAttribute) targetStructureTransformationDataEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTargetStructureTransformationData_OperationApplicableStereotype() {
        return (EAttribute) targetStructureTransformationDataEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDataNameType() {
        return dataNameTypeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDataNameType_DataName() {
        return (EAttribute) dataNameTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getLocationType() {
        return locationTypeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getLocationType_Location() {
        return (EReference) locationTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getLocationSegment() {
        return locationSegmentEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getLocationSegment_Sequence() {
        return (EAttribute) locationSegmentEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getLocationSegment_LocationName() {
        return (EAttribute) locationSegmentEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getLocationSegment_LocationKeyword() {
        return (EAttribute) locationSegmentEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getNameType() {
        return nameTypeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getNameType_Prefix() {
        return (EAttribute) nameTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getNameType_NamePart() {
        return (EReference) nameTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getNameType_Postfix() {
        return (EAttribute) nameTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getNamePartType() {
        return namePartTypeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getNamePartType_Name() {
        return (EAttribute) namePartTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getNamePartType_Capitalization() {
        return (EAttribute) namePartTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getBehaviorTransformationData() {
        return behaviorTransformationDataEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getBehaviorTransformationData_SourceRelation() {
        return (EReference) behaviorTransformationDataEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getBehaviorTransformationData_Remark() {
        return (EAttribute) behaviorTransformationDataEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getBehaviorTransformationData_Self() {
        return (EAttribute) behaviorTransformationDataEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getBehaviorTransformationData_BehaviorTransformationDetailDataSet() {
        return (EReference) behaviorTransformationDataEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getSourceRelationType() {
        return sourceRelationTypeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getSourceRelationType_Source() {
        return (EReference) sourceRelationTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getSourceRelationType_Target() {
        return (EReference) sourceRelationTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getSourceRelationType_SelfRelation() {
        return (EAttribute) sourceRelationTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getBehaviorTransformationDetailData() {
        return behaviorTransformationDetailDataEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getBehaviorTransformationDetailData_Sequence() {
        return (EAttribute) behaviorTransformationDetailDataEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getBehaviorTransformationDetailData_TargetRelation() {
        return (EReference) behaviorTransformationDetailDataEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getBehaviorTransformationDetailData_Operation() {
        return (EReference) behaviorTransformationDetailDataEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getBehaviorTransformationDetailData_OperationApplicableStereotype() {
        return (EAttribute) behaviorTransformationDetailDataEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getBehaviorTransformationDetailData_Message() {
        return (EAttribute) behaviorTransformationDetailDataEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTargetRelationType() {
        return targetRelationTypeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTargetRelationType_Source() {
        return (EReference) targetRelationTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTargetRelationType_Target() {
        return (EReference) targetRelationTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getOperationType() {
        return operationTypeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getOperationType_OperationApplicationType() {
        return (EAttribute) operationTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getOperationType_AssignableOperationName() {
        return (EReference) operationTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getOperationType_UserDefinedOperationName() {
        return (EAttribute) operationTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getParentType() {
        return parentTypeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getParentType_TypeName() {
        return (EAttribute) parentTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getParentType_ExtensionType() {
        return (EAttribute) parentTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EEnum getTargetCreationType() {
        return targetCreationTypeEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EEnum getLocationKeywordType() {
        return locationKeywordTypeEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EEnum getTargetType() {
        return targetTypeEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EEnum getNameKeywordType() {
        return nameKeywordTypeEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EEnum getCapitalizationType() {
        return capitalizationTypeEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EEnum getApplicationType() {
        return applicationTypeEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EEnum getOperationApplicationType() {
        return operationApplicationTypeEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EEnum getExtensionType() {
        return extensionTypeEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public TransformationFactory getTransformationFactory() {
        return (TransformationFactory) getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package. This method is guarded to
     * have no affect on any invocation but its first. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void createPackageContents() {
        if (isCreated)
            return;
        isCreated = true;

        // Create classes and their features
        ruleSetEClass = createEClass(RULE_SET);
        createEReference(ruleSetEClass, RULE_SET__STRUCTURE_RULES);
        createEReference(ruleSetEClass, RULE_SET__BEHAVIOR_RULES);
        createEAttribute(ruleSetEClass, RULE_SET__SOURCE_MODEL_LOCATION);
        createEAttribute(ruleSetEClass, RULE_SET__SOURCE_PROFILE_LOCATION);
        createEAttribute(ruleSetEClass, RULE_SET__TARGET_MODEL_LOCATION);
        createEAttribute(ruleSetEClass, RULE_SET__TARGET_PROFILE_LOCATION);

        sourceStructureTransformationDataEClass = createEClass(SOURCE_STRUCTURE_TRANSFORMATION_DATA);
        createEReference(sourceStructureTransformationDataEClass, SOURCE_STRUCTURE_TRANSFORMATION_DATA__SOURCE_TYPE);
        createEReference(sourceStructureTransformationDataEClass, SOURCE_STRUCTURE_TRANSFORMATION_DATA__SOURCE_LOCATION);
        createEReference(sourceStructureTransformationDataEClass,
            SOURCE_STRUCTURE_TRANSFORMATION_DATA__TARGET_STRUCTURE_TRANSFORMATION_DATA_SET);

        sourceTypeEClass = createEClass(SOURCE_TYPE);
        createEAttribute(sourceTypeEClass, SOURCE_TYPE__SOURCE_NAME);

        targetStructureTransformationDataEClass = createEClass(TARGET_STRUCTURE_TRANSFORMATION_DATA);
        createEReference(targetStructureTransformationDataEClass, TARGET_STRUCTURE_TRANSFORMATION_DATA__DATA_NAME);
        createEAttribute(targetStructureTransformationDataEClass,
            TARGET_STRUCTURE_TRANSFORMATION_DATA__TARGET_CREATION_TYPE);
        createEReference(targetStructureTransformationDataEClass, TARGET_STRUCTURE_TRANSFORMATION_DATA__TARGET_LOCATION);
        createEAttribute(targetStructureTransformationDataEClass,
            TARGET_STRUCTURE_TRANSFORMATION_DATA__TARGET_APPLICABLE_STEREOTYPE);
        createEAttribute(targetStructureTransformationDataEClass, TARGET_STRUCTURE_TRANSFORMATION_DATA__TARGET_TYPE);
        createEReference(targetStructureTransformationDataEClass, TARGET_STRUCTURE_TRANSFORMATION_DATA__PARENT_TYPE);
        createEReference(targetStructureTransformationDataEClass, TARGET_STRUCTURE_TRANSFORMATION_DATA__TARGET_NAME);
        createEAttribute(targetStructureTransformationDataEClass,
            TARGET_STRUCTURE_TRANSFORMATION_DATA__APPLICATION_TYPE);
        createEAttribute(targetStructureTransformationDataEClass,
            TARGET_STRUCTURE_TRANSFORMATION_DATA__PROPERTY_CREATION);
        createEAttribute(targetStructureTransformationDataEClass,
            TARGET_STRUCTURE_TRANSFORMATION_DATA__PROPERTY_APPLICABLE_STEREOTYPE);
        createEAttribute(targetStructureTransformationDataEClass,
            TARGET_STRUCTURE_TRANSFORMATION_DATA__OPERATION_CREATION);
        createEAttribute(targetStructureTransformationDataEClass,
            TARGET_STRUCTURE_TRANSFORMATION_DATA__OPERATION_APPLICABLE_STEREOTYPE);

        dataNameTypeEClass = createEClass(DATA_NAME_TYPE);
        createEAttribute(dataNameTypeEClass, DATA_NAME_TYPE__DATA_NAME);

        locationTypeEClass = createEClass(LOCATION_TYPE);
        createEReference(locationTypeEClass, LOCATION_TYPE__LOCATION);

        locationSegmentEClass = createEClass(LOCATION_SEGMENT);
        createEAttribute(locationSegmentEClass, LOCATION_SEGMENT__SEQUENCE);
        createEAttribute(locationSegmentEClass, LOCATION_SEGMENT__LOCATION_NAME);
        createEAttribute(locationSegmentEClass, LOCATION_SEGMENT__LOCATION_KEYWORD);

        nameTypeEClass = createEClass(NAME_TYPE);
        createEAttribute(nameTypeEClass, NAME_TYPE__PREFIX);
        createEReference(nameTypeEClass, NAME_TYPE__NAME_PART);
        createEAttribute(nameTypeEClass, NAME_TYPE__POSTFIX);

        namePartTypeEClass = createEClass(NAME_PART_TYPE);
        createEAttribute(namePartTypeEClass, NAME_PART_TYPE__NAME);
        createEAttribute(namePartTypeEClass, NAME_PART_TYPE__CAPITALIZATION);

        behaviorTransformationDataEClass = createEClass(BEHAVIOR_TRANSFORMATION_DATA);
        createEReference(behaviorTransformationDataEClass, BEHAVIOR_TRANSFORMATION_DATA__SOURCE_RELATION);
        createEAttribute(behaviorTransformationDataEClass, BEHAVIOR_TRANSFORMATION_DATA__REMARK);
        createEAttribute(behaviorTransformationDataEClass, BEHAVIOR_TRANSFORMATION_DATA__SELF);
        createEReference(behaviorTransformationDataEClass,
            BEHAVIOR_TRANSFORMATION_DATA__BEHAVIOR_TRANSFORMATION_DETAIL_DATA_SET);

        sourceRelationTypeEClass = createEClass(SOURCE_RELATION_TYPE);
        createEReference(sourceRelationTypeEClass, SOURCE_RELATION_TYPE__SOURCE);
        createEReference(sourceRelationTypeEClass, SOURCE_RELATION_TYPE__TARGET);
        createEAttribute(sourceRelationTypeEClass, SOURCE_RELATION_TYPE__SELF_RELATION);

        behaviorTransformationDetailDataEClass = createEClass(BEHAVIOR_TRANSFORMATION_DETAIL_DATA);
        createEAttribute(behaviorTransformationDetailDataEClass, BEHAVIOR_TRANSFORMATION_DETAIL_DATA__SEQUENCE);
        createEReference(behaviorTransformationDetailDataEClass, BEHAVIOR_TRANSFORMATION_DETAIL_DATA__TARGET_RELATION);
        createEReference(behaviorTransformationDetailDataEClass, BEHAVIOR_TRANSFORMATION_DETAIL_DATA__OPERATION);
        createEAttribute(behaviorTransformationDetailDataEClass,
            BEHAVIOR_TRANSFORMATION_DETAIL_DATA__OPERATION_APPLICABLE_STEREOTYPE);
        createEAttribute(behaviorTransformationDetailDataEClass, BEHAVIOR_TRANSFORMATION_DETAIL_DATA__MESSAGE);

        targetRelationTypeEClass = createEClass(TARGET_RELATION_TYPE);
        createEReference(targetRelationTypeEClass, TARGET_RELATION_TYPE__SOURCE);
        createEReference(targetRelationTypeEClass, TARGET_RELATION_TYPE__TARGET);

        operationTypeEClass = createEClass(OPERATION_TYPE);
        createEAttribute(operationTypeEClass, OPERATION_TYPE__OPERATION_APPLICATION_TYPE);
        createEReference(operationTypeEClass, OPERATION_TYPE__ASSIGNABLE_OPERATION_NAME);
        createEAttribute(operationTypeEClass, OPERATION_TYPE__USER_DEFINED_OPERATION_NAME);

        parentTypeEClass = createEClass(PARENT_TYPE);
        createEAttribute(parentTypeEClass, PARENT_TYPE__TYPE_NAME);
        createEAttribute(parentTypeEClass, PARENT_TYPE__EXTENSION_TYPE);

        // Create enums
        targetCreationTypeEEnum = createEEnum(TARGET_CREATION_TYPE);
        locationKeywordTypeEEnum = createEEnum(LOCATION_KEYWORD_TYPE);
        targetTypeEEnum = createEEnum(TARGET_TYPE);
        nameKeywordTypeEEnum = createEEnum(NAME_KEYWORD_TYPE);
        capitalizationTypeEEnum = createEEnum(CAPITALIZATION_TYPE);
        applicationTypeEEnum = createEEnum(APPLICATION_TYPE);
        operationApplicationTypeEEnum = createEEnum(OPERATION_APPLICATION_TYPE);
        extensionTypeEEnum = createEEnum(EXTENSION_TYPE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model. This
     * method is guarded to have no affect on any invocation but its first. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void initializePackageContents() {
        if (isInitialized)
            return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Obtain other dependent packages
        EcorePackage theEcorePackage = (EcorePackage) EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes

        // Initialize classes and features; add operations and parameters
        initEClass(ruleSetEClass, RuleSet.class, "RuleSet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getRuleSet_StructureRules(),
            this.getSourceStructureTransformationData(),
            null,
            "structureRules",
            null,
            0,
            -1,
            RuleSet.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            IS_COMPOSITE,
            !IS_RESOLVE_PROXIES,
            !IS_UNSETTABLE,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEReference(getRuleSet_BehaviorRules(),
            this.getBehaviorTransformationData(),
            null,
            "behaviorRules",
            null,
            0,
            -1,
            RuleSet.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            IS_COMPOSITE,
            !IS_RESOLVE_PROXIES,
            !IS_UNSETTABLE,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getRuleSet_SourceModelLocation(),
            ecorePackage.getEString(),
            "sourceModelLocation",
            null,
            1,
            1,
            RuleSet.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getRuleSet_SourceProfileLocation(),
            ecorePackage.getEString(),
            "sourceProfileLocation",
            null,
            1,
            1,
            RuleSet.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getRuleSet_TargetModelLocation(),
            ecorePackage.getEString(),
            "targetModelLocation",
            null,
            1,
            1,
            RuleSet.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getRuleSet_TargetProfileLocation(),
            ecorePackage.getEString(),
            "targetProfileLocation",
            null,
            1,
            1,
            RuleSet.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);

        initEClass(sourceStructureTransformationDataEClass,
            SourceStructureTransformationData.class,
            "SourceStructureTransformationData",
            !IS_ABSTRACT,
            !IS_INTERFACE,
            IS_GENERATED_INSTANCE_CLASS);
        initEReference(getSourceStructureTransformationData_SourceType(),
            this.getSourceType(),
            null,
            "sourceType",
            null,
            1,
            1,
            SourceStructureTransformationData.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            IS_COMPOSITE,
            !IS_RESOLVE_PROXIES,
            !IS_UNSETTABLE,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEReference(getSourceStructureTransformationData_SourceLocation(),
            this.getLocationType(),
            null,
            "sourceLocation",
            null,
            1,
            1,
            SourceStructureTransformationData.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            IS_COMPOSITE,
            !IS_RESOLVE_PROXIES,
            !IS_UNSETTABLE,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEReference(getSourceStructureTransformationData_TargetStructureTransformationDataSet(),
            this.getTargetStructureTransformationData(),
            null,
            "targetStructureTransformationDataSet",
            null,
            1,
            -1,
            SourceStructureTransformationData.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            IS_COMPOSITE,
            !IS_RESOLVE_PROXIES,
            !IS_UNSETTABLE,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);

        initEClass(sourceTypeEClass,
            SourceType.class,
            "SourceType",
            !IS_ABSTRACT,
            !IS_INTERFACE,
            IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getSourceType_SourceName(),
            ecorePackage.getEString(),
            "sourceName",
            null,
            1,
            1,
            SourceType.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);

        initEClass(targetStructureTransformationDataEClass,
            TargetStructureTransformationData.class,
            "TargetStructureTransformationData",
            !IS_ABSTRACT,
            !IS_INTERFACE,
            IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTargetStructureTransformationData_DataName(),
            this.getDataNameType(),
            null,
            "dataName",
            null,
            1,
            1,
            TargetStructureTransformationData.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            IS_COMPOSITE,
            !IS_RESOLVE_PROXIES,
            !IS_UNSETTABLE,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getTargetStructureTransformationData_TargetCreationType(),
            this.getTargetCreationType(),
            "targetCreationType",
            null,
            1,
            1,
            TargetStructureTransformationData.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEReference(getTargetStructureTransformationData_TargetLocation(),
            this.getLocationType(),
            null,
            "targetLocation",
            null,
            1,
            1,
            TargetStructureTransformationData.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            IS_COMPOSITE,
            !IS_RESOLVE_PROXIES,
            !IS_UNSETTABLE,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getTargetStructureTransformationData_TargetApplicableStereotype(),
            ecorePackage.getEString(),
            "targetApplicableStereotype",
            null,
            1,
            1,
            TargetStructureTransformationData.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getTargetStructureTransformationData_TargetType(),
            this.getTargetType(),
            "targetType",
            null,
            1,
            1,
            TargetStructureTransformationData.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEReference(getTargetStructureTransformationData_ParentType(),
            this.getParentType(),
            null,
            "parentType",
            null,
            0,
            -1,
            TargetStructureTransformationData.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            IS_COMPOSITE,
            !IS_RESOLVE_PROXIES,
            !IS_UNSETTABLE,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEReference(getTargetStructureTransformationData_TargetName(),
            this.getNameType(),
            null,
            "targetName",
            null,
            1,
            1,
            TargetStructureTransformationData.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            IS_COMPOSITE,
            !IS_RESOLVE_PROXIES,
            !IS_UNSETTABLE,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getTargetStructureTransformationData_ApplicationType(),
            this.getApplicationType(),
            "applicationType",
            null,
            1,
            1,
            TargetStructureTransformationData.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getTargetStructureTransformationData_PropertyCreation(),
            ecorePackage.getEBoolean(),
            "propertyCreation",
            null,
            1,
            1,
            TargetStructureTransformationData.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getTargetStructureTransformationData_PropertyApplicableStereotype(),
            ecorePackage.getEString(),
            "propertyApplicableStereotype",
            null,
            0,
            1,
            TargetStructureTransformationData.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getTargetStructureTransformationData_OperationCreation(),
            ecorePackage.getEBoolean(),
            "operationCreation",
            null,
            0,
            1,
            TargetStructureTransformationData.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getTargetStructureTransformationData_OperationApplicableStereotype(),
            ecorePackage.getEString(),
            "operationApplicableStereotype",
            null,
            0,
            1,
            TargetStructureTransformationData.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);

        initEClass(dataNameTypeEClass,
            DataNameType.class,
            "DataNameType",
            !IS_ABSTRACT,
            !IS_INTERFACE,
            IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDataNameType_DataName(),
            ecorePackage.getEString(),
            "dataName",
            null,
            1,
            1,
            DataNameType.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);

        initEClass(locationTypeEClass,
            LocationType.class,
            "LocationType",
            !IS_ABSTRACT,
            !IS_INTERFACE,
            IS_GENERATED_INSTANCE_CLASS);
        initEReference(getLocationType_Location(),
            this.getLocationSegment(),
            null,
            "location",
            null,
            1,
            -1,
            LocationType.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            IS_COMPOSITE,
            !IS_RESOLVE_PROXIES,
            !IS_UNSETTABLE,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);

        addEOperation(locationTypeEClass, theEcorePackage.getEString(), "toString", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(locationSegmentEClass,
            LocationSegment.class,
            "LocationSegment",
            !IS_ABSTRACT,
            !IS_INTERFACE,
            IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getLocationSegment_Sequence(),
            theEcorePackage.getEInt(),
            "sequence",
            null,
            1,
            1,
            LocationSegment.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getLocationSegment_LocationName(),
            theEcorePackage.getEString(),
            "locationName",
            null,
            0,
            1,
            LocationSegment.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getLocationSegment_LocationKeyword(),
            this.getLocationKeywordType(),
            "locationKeyword",
            null,
            0,
            1,
            LocationSegment.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);

        initEClass(nameTypeEClass, NameType.class, "NameType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getNameType_Prefix(),
            theEcorePackage.getEString(),
            "prefix",
            null,
            0,
            1,
            NameType.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEReference(getNameType_NamePart(),
            this.getNamePartType(),
            null,
            "namePart",
            null,
            1,
            1,
            NameType.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            IS_COMPOSITE,
            !IS_RESOLVE_PROXIES,
            !IS_UNSETTABLE,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getNameType_Postfix(),
            theEcorePackage.getEString(),
            "postfix",
            null,
            0,
            1,
            NameType.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);

        addEOperation(nameTypeEClass, theEcorePackage.getEString(), "toString", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(namePartTypeEClass,
            NamePartType.class,
            "NamePartType",
            !IS_ABSTRACT,
            !IS_INTERFACE,
            IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getNamePartType_Name(),
            this.getNameKeywordType(),
            "name",
            null,
            1,
            1,
            NamePartType.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getNamePartType_Capitalization(),
            this.getCapitalizationType(),
            "capitalization",
            null,
            1,
            1,
            NamePartType.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);

        initEClass(behaviorTransformationDataEClass,
            BehaviorTransformationData.class,
            "BehaviorTransformationData",
            !IS_ABSTRACT,
            !IS_INTERFACE,
            IS_GENERATED_INSTANCE_CLASS);
        initEReference(getBehaviorTransformationData_SourceRelation(),
            this.getSourceRelationType(),
            null,
            "sourceRelation",
            null,
            1,
            1,
            BehaviorTransformationData.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            IS_COMPOSITE,
            !IS_RESOLVE_PROXIES,
            !IS_UNSETTABLE,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getBehaviorTransformationData_Remark(),
            ecorePackage.getEString(),
            "remark",
            null,
            0,
            1,
            BehaviorTransformationData.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getBehaviorTransformationData_Self(),
            ecorePackage.getEBoolean(),
            "self",
            null,
            1,
            1,
            BehaviorTransformationData.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEReference(getBehaviorTransformationData_BehaviorTransformationDetailDataSet(),
            this.getBehaviorTransformationDetailData(),
            null,
            "behaviorTransformationDetailDataSet",
            null,
            1,
            -1,
            BehaviorTransformationData.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            IS_COMPOSITE,
            !IS_RESOLVE_PROXIES,
            !IS_UNSETTABLE,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);

        initEClass(sourceRelationTypeEClass,
            SourceRelationType.class,
            "SourceRelationType",
            !IS_ABSTRACT,
            !IS_INTERFACE,
            IS_GENERATED_INSTANCE_CLASS);
        initEReference(getSourceRelationType_Source(),
            this.getSourceType(),
            null,
            "source",
            null,
            1,
            1,
            SourceRelationType.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            IS_COMPOSITE,
            !IS_RESOLVE_PROXIES,
            !IS_UNSETTABLE,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEReference(getSourceRelationType_Target(),
            this.getSourceType(),
            null,
            "target",
            null,
            1,
            1,
            SourceRelationType.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            IS_COMPOSITE,
            !IS_RESOLVE_PROXIES,
            !IS_UNSETTABLE,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getSourceRelationType_SelfRelation(),
            ecorePackage.getEBoolean(),
            "selfRelation",
            null,
            1,
            1,
            SourceRelationType.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);

        addEOperation(sourceRelationTypeEClass, theEcorePackage.getEString(), "toString", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(behaviorTransformationDetailDataEClass,
            BehaviorTransformationDetailData.class,
            "BehaviorTransformationDetailData",
            !IS_ABSTRACT,
            !IS_INTERFACE,
            IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getBehaviorTransformationDetailData_Sequence(),
            theEcorePackage.getEInt(),
            "sequence",
            null,
            1,
            1,
            BehaviorTransformationDetailData.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEReference(getBehaviorTransformationDetailData_TargetRelation(),
            this.getTargetRelationType(),
            null,
            "targetRelation",
            null,
            1,
            1,
            BehaviorTransformationDetailData.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            IS_COMPOSITE,
            !IS_RESOLVE_PROXIES,
            !IS_UNSETTABLE,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEReference(getBehaviorTransformationDetailData_Operation(),
            this.getOperationType(),
            null,
            "operation",
            null,
            1,
            1,
            BehaviorTransformationDetailData.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            IS_COMPOSITE,
            !IS_RESOLVE_PROXIES,
            !IS_UNSETTABLE,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getBehaviorTransformationDetailData_OperationApplicableStereotype(),
            ecorePackage.getEString(),
            "operationApplicableStereotype",
            null,
            0,
            1,
            BehaviorTransformationDetailData.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getBehaviorTransformationDetailData_Message(),
            theEcorePackage.getEString(),
            "message",
            null,
            0,
            1,
            BehaviorTransformationDetailData.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);

        initEClass(targetRelationTypeEClass,
            TargetRelationType.class,
            "TargetRelationType",
            !IS_ABSTRACT,
            !IS_INTERFACE,
            IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTargetRelationType_Source(),
            this.getDataNameType(),
            null,
            "source",
            null,
            1,
            1,
            TargetRelationType.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            IS_COMPOSITE,
            !IS_RESOLVE_PROXIES,
            !IS_UNSETTABLE,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEReference(getTargetRelationType_Target(),
            this.getDataNameType(),
            null,
            "target",
            null,
            1,
            1,
            TargetRelationType.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            IS_COMPOSITE,
            !IS_RESOLVE_PROXIES,
            !IS_UNSETTABLE,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);

        addEOperation(targetRelationTypeEClass, theEcorePackage.getEString(), "toString", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(operationTypeEClass,
            OperationType.class,
            "OperationType",
            !IS_ABSTRACT,
            !IS_INTERFACE,
            IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getOperationType_OperationApplicationType(),
            this.getOperationApplicationType(),
            "operationApplicationType",
            null,
            1,
            1,
            OperationType.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEReference(getOperationType_AssignableOperationName(),
            this.getNameType(),
            null,
            "assignableOperationName",
            null,
            0,
            1,
            OperationType.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            IS_COMPOSITE,
            !IS_RESOLVE_PROXIES,
            !IS_UNSETTABLE,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getOperationType_UserDefinedOperationName(),
            ecorePackage.getEString(),
            "userDefinedOperationName",
            null,
            0,
            1,
            OperationType.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);

        addEOperation(operationTypeEClass, ecorePackage.getEString(), "toString", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(parentTypeEClass,
            ParentType.class,
            "ParentType",
            !IS_ABSTRACT,
            !IS_INTERFACE,
            IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getParentType_TypeName(),
            ecorePackage.getEString(),
            "typeName",
            null,
            1,
            -1,
            ParentType.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);
        initEAttribute(getParentType_ExtensionType(),
            this.getExtensionType(),
            "extensionType",
            null,
            1,
            1,
            ParentType.class,
            !IS_TRANSIENT,
            !IS_VOLATILE,
            IS_CHANGEABLE,
            !IS_UNSETTABLE,
            !IS_ID,
            IS_UNIQUE,
            !IS_DERIVED,
            IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(targetCreationTypeEEnum, TargetCreationType.class, "TargetCreationType");
        addEEnumLiteral(targetCreationTypeEEnum, TargetCreationType.SELF);
        addEEnumLiteral(targetCreationTypeEEnum, TargetCreationType.DERIVED);

        initEEnum(locationKeywordTypeEEnum, LocationKeywordType.class, "LocationKeywordType");
        addEEnumLiteral(locationKeywordTypeEEnum, LocationKeywordType.NONE);
        addEEnumLiteral(locationKeywordTypeEEnum, LocationKeywordType.BASE);
        addEEnumLiteral(locationKeywordTypeEEnum, LocationKeywordType.SELF);

        initEEnum(targetTypeEEnum, TargetType.class, "TargetType");
        addEEnumLiteral(targetTypeEEnum, TargetType.NONE);
        addEEnumLiteral(targetTypeEEnum, TargetType.COMPONENT);
        addEEnumLiteral(targetTypeEEnum, TargetType.INTERFACE);
        addEEnumLiteral(targetTypeEEnum, TargetType.CLASS);

        initEEnum(nameKeywordTypeEEnum, NameKeywordType.class, "NameKeywordType");
        addEEnumLiteral(nameKeywordTypeEEnum, NameKeywordType.BASE);
        addEEnumLiteral(nameKeywordTypeEEnum, NameKeywordType.SELF);
        addEEnumLiteral(nameKeywordTypeEEnum, NameKeywordType.OPERATION);

        initEEnum(capitalizationTypeEEnum, CapitalizationType.class, "CapitalizationType");
        addEEnumLiteral(capitalizationTypeEEnum, CapitalizationType.NONE);
        addEEnumLiteral(capitalizationTypeEEnum, CapitalizationType.FIRST);
        addEEnumLiteral(capitalizationTypeEEnum, CapitalizationType.ALL);

        initEEnum(applicationTypeEEnum, ApplicationType.class, "ApplicationType");
        addEEnumLiteral(applicationTypeEEnum, ApplicationType.MERGING);
        addEEnumLiteral(applicationTypeEEnum, ApplicationType.EACH);

        initEEnum(operationApplicationTypeEEnum, OperationApplicationType.class, "OperationApplicationType");
        addEEnumLiteral(operationApplicationTypeEEnum, OperationApplicationType.NONE);
        addEEnumLiteral(operationApplicationTypeEEnum, OperationApplicationType.SAME_TYPE);
        addEEnumLiteral(operationApplicationTypeEEnum, OperationApplicationType.USER_DEFINED);

        initEEnum(extensionTypeEEnum, ExtensionType.class, "ExtensionType");
        addEEnumLiteral(extensionTypeEEnum, ExtensionType.EXTENDS);
        addEEnumLiteral(extensionTypeEEnum, ExtensionType.IMPLEMENTS);

        // Create resource
        createResource(eNS_URI);
    }

} // TransformationPackageImpl
