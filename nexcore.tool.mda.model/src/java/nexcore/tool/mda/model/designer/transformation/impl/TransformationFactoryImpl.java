/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class TransformationFactoryImpl extends EFactoryImpl implements TransformationFactory {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = "";

    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public static TransformationFactory init() {
        try {
            TransformationFactory theTransformationFactory = (TransformationFactory) EPackage.Registry.INSTANCE.getEFactory("http://nexcore.skcc.com/tools/mda/designer/transformation");
            if (theTransformationFactory != null) {
                return theTransformationFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new TransformationFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public TransformationFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case TransformationPackage.RULE_SET:
                return createRuleSet();
            case TransformationPackage.SOURCE_STRUCTURE_TRANSFORMATION_DATA:
                return createSourceStructureTransformationData();
            case TransformationPackage.SOURCE_TYPE:
                return createSourceType();
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA:
                return createTargetStructureTransformationData();
            case TransformationPackage.DATA_NAME_TYPE:
                return createDataNameType();
            case TransformationPackage.LOCATION_TYPE:
                return createLocationType();
            case TransformationPackage.LOCATION_SEGMENT:
                return createLocationSegment();
            case TransformationPackage.NAME_TYPE:
                return createNameType();
            case TransformationPackage.NAME_PART_TYPE:
                return createNamePartType();
            case TransformationPackage.BEHAVIOR_TRANSFORMATION_DATA:
                return createBehaviorTransformationData();
            case TransformationPackage.SOURCE_RELATION_TYPE:
                return createSourceRelationType();
            case TransformationPackage.BEHAVIOR_TRANSFORMATION_DETAIL_DATA:
                return createBehaviorTransformationDetailData();
            case TransformationPackage.TARGET_RELATION_TYPE:
                return createTargetRelationType();
            case TransformationPackage.OPERATION_TYPE:
                return createOperationType();
            case TransformationPackage.PARENT_TYPE:
                return createParentType();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object createFromString(EDataType eDataType, String initialValue) {
        switch (eDataType.getClassifierID()) {
            case TransformationPackage.TARGET_CREATION_TYPE:
                return createTargetCreationTypeFromString(eDataType, initialValue);
            case TransformationPackage.LOCATION_KEYWORD_TYPE:
                return createLocationKeywordTypeFromString(eDataType, initialValue);
            case TransformationPackage.TARGET_TYPE:
                return createTargetTypeFromString(eDataType, initialValue);
            case TransformationPackage.NAME_KEYWORD_TYPE:
                return createNameKeywordTypeFromString(eDataType, initialValue);
            case TransformationPackage.CAPITALIZATION_TYPE:
                return createCapitalizationTypeFromString(eDataType, initialValue);
            case TransformationPackage.APPLICATION_TYPE:
                return createApplicationTypeFromString(eDataType, initialValue);
            case TransformationPackage.OPERATION_APPLICATION_TYPE:
                return createOperationApplicationTypeFromString(eDataType, initialValue);
            case TransformationPackage.EXTENSION_TYPE:
                return createExtensionTypeFromString(eDataType, initialValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName()
                    + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String convertToString(EDataType eDataType, Object instanceValue) {
        switch (eDataType.getClassifierID()) {
            case TransformationPackage.TARGET_CREATION_TYPE:
                return convertTargetCreationTypeToString(eDataType, instanceValue);
            case TransformationPackage.LOCATION_KEYWORD_TYPE:
                return convertLocationKeywordTypeToString(eDataType, instanceValue);
            case TransformationPackage.TARGET_TYPE:
                return convertTargetTypeToString(eDataType, instanceValue);
            case TransformationPackage.NAME_KEYWORD_TYPE:
                return convertNameKeywordTypeToString(eDataType, instanceValue);
            case TransformationPackage.CAPITALIZATION_TYPE:
                return convertCapitalizationTypeToString(eDataType, instanceValue);
            case TransformationPackage.APPLICATION_TYPE:
                return convertApplicationTypeToString(eDataType, instanceValue);
            case TransformationPackage.OPERATION_APPLICATION_TYPE:
                return convertOperationApplicationTypeToString(eDataType, instanceValue);
            case TransformationPackage.EXTENSION_TYPE:
                return convertExtensionTypeToString(eDataType, instanceValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName()
                    + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public RuleSet createRuleSet() {
        RuleSetImpl ruleSet = new RuleSetImpl();
        return ruleSet;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public SourceStructureTransformationData createSourceStructureTransformationData() {
        SourceStructureTransformationDataImpl sourceStructureTransformationData = new SourceStructureTransformationDataImpl();
        return sourceStructureTransformationData;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public SourceType createSourceType() {
        SourceTypeImpl sourceType = new SourceTypeImpl();
        return sourceType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public TargetStructureTransformationData createTargetStructureTransformationData() {
        TargetStructureTransformationDataImpl targetStructureTransformationData = new TargetStructureTransformationDataImpl();
        return targetStructureTransformationData;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public DataNameType createDataNameType() {
        DataNameTypeImpl dataNameType = new DataNameTypeImpl();
        return dataNameType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public LocationType createLocationType() {
        LocationTypeImpl locationType = new LocationTypeImpl();
        return locationType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public LocationSegment createLocationSegment() {
        LocationSegmentImpl locationSegment = new LocationSegmentImpl();
        return locationSegment;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NameType createNameType() {
        NameTypeImpl nameType = new NameTypeImpl();
        return nameType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NamePartType createNamePartType() {
        NamePartTypeImpl namePartType = new NamePartTypeImpl();
        return namePartType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public BehaviorTransformationData createBehaviorTransformationData() {
        BehaviorTransformationDataImpl behaviorTransformationData = new BehaviorTransformationDataImpl();
        return behaviorTransformationData;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public SourceRelationType createSourceRelationType() {
        SourceRelationTypeImpl sourceRelationType = new SourceRelationTypeImpl();
        return sourceRelationType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public BehaviorTransformationDetailData createBehaviorTransformationDetailData() {
        BehaviorTransformationDetailDataImpl behaviorTransformationDetailData = new BehaviorTransformationDetailDataImpl();
        return behaviorTransformationDetailData;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public TargetRelationType createTargetRelationType() {
        TargetRelationTypeImpl targetRelationType = new TargetRelationTypeImpl();
        return targetRelationType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public OperationType createOperationType() {
        OperationTypeImpl operationType = new OperationTypeImpl();
        return operationType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ParentType createParentType() {
        ParentTypeImpl parentType = new ParentTypeImpl();
        return parentType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public TargetCreationType createTargetCreationTypeFromString(EDataType eDataType, String initialValue) {
        TargetCreationType result = TargetCreationType.get(initialValue);
        if (result == null)
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '"
                + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertTargetCreationTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public LocationKeywordType createLocationKeywordTypeFromString(EDataType eDataType, String initialValue) {
        LocationKeywordType result = LocationKeywordType.get(initialValue);
        if (result == null)
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '"
                + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertLocationKeywordTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public TargetType createTargetTypeFromString(EDataType eDataType, String initialValue) {
        TargetType result = TargetType.get(initialValue);
        if (result == null)
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '"
                + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertTargetTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NameKeywordType createNameKeywordTypeFromString(EDataType eDataType, String initialValue) {
        NameKeywordType result = NameKeywordType.get(initialValue);
        if (result == null)
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '"
                + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertNameKeywordTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public CapitalizationType createCapitalizationTypeFromString(EDataType eDataType, String initialValue) {
        CapitalizationType result = CapitalizationType.get(initialValue);
        if (result == null)
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '"
                + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertCapitalizationTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ApplicationType createApplicationTypeFromString(EDataType eDataType, String initialValue) {
        ApplicationType result = ApplicationType.get(initialValue);
        if (result == null)
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '"
                + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertApplicationTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public OperationApplicationType createOperationApplicationTypeFromString(EDataType eDataType, String initialValue) {
        OperationApplicationType result = OperationApplicationType.get(initialValue);
        if (result == null)
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '"
                + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertOperationApplicationTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ExtensionType createExtensionTypeFromString(EDataType eDataType, String initialValue) {
        ExtensionType result = ExtensionType.get(initialValue);
        if (result == null)
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '"
                + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertExtensionTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public TransformationPackage getTransformationPackage() {
        return (TransformationPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @deprecated
     * @generated
     */
    @Deprecated
    public static TransformationPackage getPackage() {
        return TransformationPackage.eINSTANCE;
    }

} // TransformationFactoryImpl
