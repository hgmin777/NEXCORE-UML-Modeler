/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.mda.model.designer.transformation;

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
 * @see nexcore.tool.mda.model.designer.transformation.TransformationFactory
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
    String eNS_URI = "http://nexcore.skcc.com/tools/mda/designer/transformation";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_PREFIX = "dst";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    TransformationPackage eINSTANCE = nexcore.tool.mda.model.designer.transformation.impl.TransformationPackageImpl.init();

    /**
     * The meta object id for the '
     * {@link nexcore.tool.mda.model.designer.transformation.impl.RuleSetImpl
     * <em>Rule Set</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see nexcore.tool.mda.model.designer.transformation.impl.RuleSetImpl
     * @see nexcore.tool.mda.model.designer.transformation.impl.TransformationPackageImpl#getRuleSet()
     * @generated
     */
    int RULE_SET = 0;

    /**
     * The feature id for the '<em><b>Structure Rules</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RULE_SET__STRUCTURE_RULES = 0;

    /**
     * The feature id for the '<em><b>Behavior Rules</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RULE_SET__BEHAVIOR_RULES = 1;

    /**
     * The feature id for the '<em><b>Source Model Location</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RULE_SET__SOURCE_MODEL_LOCATION = 2;

    /**
     * The feature id for the '<em><b>Source Profile Location</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RULE_SET__SOURCE_PROFILE_LOCATION = 3;

    /**
     * The feature id for the '<em><b>Target Model Location</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RULE_SET__TARGET_MODEL_LOCATION = 4;

    /**
     * The feature id for the '<em><b>Target Profile Location</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RULE_SET__TARGET_PROFILE_LOCATION = 5;

    /**
     * The number of structural features of the '<em>Rule Set</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RULE_SET_FEATURE_COUNT = 6;

    /**
     * The meta object id for the '
     * {@link nexcore.tool.mda.model.designer.transformation.impl.SourceStructureTransformationDataImpl
     * <em>Source Structure Transformation Data</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see nexcore.tool.mda.model.designer.transformation.impl.SourceStructureTransformationDataImpl
     * @see nexcore.tool.mda.model.designer.transformation.impl.TransformationPackageImpl#getSourceStructureTransformationData()
     * @generated
     */
    int SOURCE_STRUCTURE_TRANSFORMATION_DATA = 1;

    /**
     * The feature id for the '<em><b>Source Type</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SOURCE_STRUCTURE_TRANSFORMATION_DATA__SOURCE_TYPE = 0;

    /**
     * The feature id for the '<em><b>Source Location</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SOURCE_STRUCTURE_TRANSFORMATION_DATA__SOURCE_LOCATION = 1;

    /**
     * The feature id for the '
     * <em><b>Target Structure Transformation Data Set</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SOURCE_STRUCTURE_TRANSFORMATION_DATA__TARGET_STRUCTURE_TRANSFORMATION_DATA_SET = 2;

    /**
     * The number of structural features of the '
     * <em>Source Structure Transformation Data</em>' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SOURCE_STRUCTURE_TRANSFORMATION_DATA_FEATURE_COUNT = 3;

    /**
     * The meta object id for the '
     * {@link nexcore.tool.mda.model.designer.transformation.impl.SourceTypeImpl
     * <em>Source Type</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see nexcore.tool.mda.model.designer.transformation.impl.SourceTypeImpl
     * @see nexcore.tool.mda.model.designer.transformation.impl.TransformationPackageImpl#getSourceType()
     * @generated
     */
    int SOURCE_TYPE = 2;

    /**
     * The feature id for the '<em><b>Source Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SOURCE_TYPE__SOURCE_NAME = 0;

    /**
     * The number of structural features of the '<em>Source Type</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SOURCE_TYPE_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '
     * {@link nexcore.tool.mda.model.designer.transformation.impl.TargetStructureTransformationDataImpl
     * <em>Target Structure Transformation Data</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see nexcore.tool.mda.model.designer.transformation.impl.TargetStructureTransformationDataImpl
     * @see nexcore.tool.mda.model.designer.transformation.impl.TransformationPackageImpl#getTargetStructureTransformationData()
     * @generated
     */
    int TARGET_STRUCTURE_TRANSFORMATION_DATA = 3;

    /**
     * The feature id for the '<em><b>Data Name</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TARGET_STRUCTURE_TRANSFORMATION_DATA__DATA_NAME = 0;

    /**
     * The feature id for the '<em><b>Target Creation Type</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TARGET_STRUCTURE_TRANSFORMATION_DATA__TARGET_CREATION_TYPE = 1;

    /**
     * The feature id for the '<em><b>Target Location</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TARGET_STRUCTURE_TRANSFORMATION_DATA__TARGET_LOCATION = 2;

    /**
     * The feature id for the '<em><b>Target Applicable Stereotype</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TARGET_STRUCTURE_TRANSFORMATION_DATA__TARGET_APPLICABLE_STEREOTYPE = 3;

    /**
     * The feature id for the '<em><b>Target Type</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TARGET_STRUCTURE_TRANSFORMATION_DATA__TARGET_TYPE = 4;

    /**
     * The feature id for the '<em><b>Parent Type</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TARGET_STRUCTURE_TRANSFORMATION_DATA__PARENT_TYPE = 5;

    /**
     * The feature id for the '<em><b>Target Name</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TARGET_STRUCTURE_TRANSFORMATION_DATA__TARGET_NAME = 6;

    /**
     * The feature id for the '<em><b>Application Type</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TARGET_STRUCTURE_TRANSFORMATION_DATA__APPLICATION_TYPE = 7;

    /**
     * The feature id for the '<em><b>Property Creation</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TARGET_STRUCTURE_TRANSFORMATION_DATA__PROPERTY_CREATION = 8;

    /**
     * The feature id for the '<em><b>Property Applicable Stereotype</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TARGET_STRUCTURE_TRANSFORMATION_DATA__PROPERTY_APPLICABLE_STEREOTYPE = 9;

    /**
     * The feature id for the '<em><b>Operation Creation</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TARGET_STRUCTURE_TRANSFORMATION_DATA__OPERATION_CREATION = 10;

    /**
     * The feature id for the '<em><b>Operation Applicable Stereotype</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TARGET_STRUCTURE_TRANSFORMATION_DATA__OPERATION_APPLICABLE_STEREOTYPE = 11;

    /**
     * The number of structural features of the '
     * <em>Target Structure Transformation Data</em>' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TARGET_STRUCTURE_TRANSFORMATION_DATA_FEATURE_COUNT = 12;

    /**
     * The meta object id for the '
     * {@link nexcore.tool.mda.model.designer.transformation.impl.DataNameTypeImpl
     * <em>Data Name Type</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see nexcore.tool.mda.model.designer.transformation.impl.DataNameTypeImpl
     * @see nexcore.tool.mda.model.designer.transformation.impl.TransformationPackageImpl#getDataNameType()
     * @generated
     */
    int DATA_NAME_TYPE = 4;

    /**
     * The feature id for the '<em><b>Data Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DATA_NAME_TYPE__DATA_NAME = 0;

    /**
     * The number of structural features of the '<em>Data Name Type</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DATA_NAME_TYPE_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '
     * {@link nexcore.tool.mda.model.designer.transformation.impl.LocationTypeImpl
     * <em>Location Type</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see nexcore.tool.mda.model.designer.transformation.impl.LocationTypeImpl
     * @see nexcore.tool.mda.model.designer.transformation.impl.TransformationPackageImpl#getLocationType()
     * @generated
     */
    int LOCATION_TYPE = 5;

    /**
     * The feature id for the '<em><b>Location</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOCATION_TYPE__LOCATION = 0;

    /**
     * The number of structural features of the '<em>Location Type</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOCATION_TYPE_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '
     * {@link nexcore.tool.mda.model.designer.transformation.impl.LocationSegmentImpl
     * <em>Location Segment</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see nexcore.tool.mda.model.designer.transformation.impl.LocationSegmentImpl
     * @see nexcore.tool.mda.model.designer.transformation.impl.TransformationPackageImpl#getLocationSegment()
     * @generated
     */
    int LOCATION_SEGMENT = 6;

    /**
     * The feature id for the '<em><b>Sequence</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOCATION_SEGMENT__SEQUENCE = 0;

    /**
     * The feature id for the '<em><b>Location Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOCATION_SEGMENT__LOCATION_NAME = 1;

    /**
     * The feature id for the '<em><b>Location Keyword</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOCATION_SEGMENT__LOCATION_KEYWORD = 2;

    /**
     * The number of structural features of the '<em>Location Segment</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOCATION_SEGMENT_FEATURE_COUNT = 3;

    /**
     * The meta object id for the '
     * {@link nexcore.tool.mda.model.designer.transformation.impl.NameTypeImpl
     * <em>Name Type</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see nexcore.tool.mda.model.designer.transformation.impl.NameTypeImpl
     * @see nexcore.tool.mda.model.designer.transformation.impl.TransformationPackageImpl#getNameType()
     * @generated
     */
    int NAME_TYPE = 7;

    /**
     * The feature id for the '<em><b>Prefix</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NAME_TYPE__PREFIX = 0;

    /**
     * The feature id for the '<em><b>Name Part</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NAME_TYPE__NAME_PART = 1;

    /**
     * The feature id for the '<em><b>Postfix</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NAME_TYPE__POSTFIX = 2;

    /**
     * The number of structural features of the '<em>Name Type</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NAME_TYPE_FEATURE_COUNT = 3;

    /**
     * The meta object id for the '
     * {@link nexcore.tool.mda.model.designer.transformation.impl.NamePartTypeImpl
     * <em>Name Part Type</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see nexcore.tool.mda.model.designer.transformation.impl.NamePartTypeImpl
     * @see nexcore.tool.mda.model.designer.transformation.impl.TransformationPackageImpl#getNamePartType()
     * @generated
     */
    int NAME_PART_TYPE = 8;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NAME_PART_TYPE__NAME = 0;

    /**
     * The feature id for the '<em><b>Capitalization</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NAME_PART_TYPE__CAPITALIZATION = 1;

    /**
     * The number of structural features of the '<em>Name Part Type</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NAME_PART_TYPE_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link nexcore.tool.mda.model.designer.transformation.impl.BehaviorTransformationDataImpl
     * <em>Behavior Transformation Data</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see nexcore.tool.mda.model.designer.transformation.impl.BehaviorTransformationDataImpl
     * @see nexcore.tool.mda.model.designer.transformation.impl.TransformationPackageImpl#getBehaviorTransformationData()
     * @generated
     */
    int BEHAVIOR_TRANSFORMATION_DATA = 9;

    /**
     * The feature id for the '<em><b>Source Relation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BEHAVIOR_TRANSFORMATION_DATA__SOURCE_RELATION = 0;

    /**
     * The feature id for the '<em><b>Remark</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BEHAVIOR_TRANSFORMATION_DATA__REMARK = 1;

    /**
     * The feature id for the '<em><b>Self</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BEHAVIOR_TRANSFORMATION_DATA__SELF = 2;

    /**
     * The feature id for the '
     * <em><b>Behavior Transformation Detail Data Set</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BEHAVIOR_TRANSFORMATION_DATA__BEHAVIOR_TRANSFORMATION_DETAIL_DATA_SET = 3;

    /**
     * The number of structural features of the '
     * <em>Behavior Transformation Data</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BEHAVIOR_TRANSFORMATION_DATA_FEATURE_COUNT = 4;

    /**
     * The meta object id for the '
     * {@link nexcore.tool.mda.model.designer.transformation.impl.SourceRelationTypeImpl
     * <em>Source Relation Type</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see nexcore.tool.mda.model.designer.transformation.impl.SourceRelationTypeImpl
     * @see nexcore.tool.mda.model.designer.transformation.impl.TransformationPackageImpl#getSourceRelationType()
     * @generated
     */
    int SOURCE_RELATION_TYPE = 10;

    /**
     * The feature id for the '<em><b>Source</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SOURCE_RELATION_TYPE__SOURCE = 0;

    /**
     * The feature id for the '<em><b>Target</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SOURCE_RELATION_TYPE__TARGET = 1;

    /**
     * The feature id for the '<em><b>Self Relation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SOURCE_RELATION_TYPE__SELF_RELATION = 2;

    /**
     * The number of structural features of the '<em>Source Relation Type</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SOURCE_RELATION_TYPE_FEATURE_COUNT = 3;

    /**
     * The meta object id for the '
     * {@link nexcore.tool.mda.model.designer.transformation.impl.BehaviorTransformationDetailDataImpl
     * <em>Behavior Transformation Detail Data</em>}' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see nexcore.tool.mda.model.designer.transformation.impl.BehaviorTransformationDetailDataImpl
     * @see nexcore.tool.mda.model.designer.transformation.impl.TransformationPackageImpl#getBehaviorTransformationDetailData()
     * @generated
     */
    int BEHAVIOR_TRANSFORMATION_DETAIL_DATA = 11;

    /**
     * The feature id for the '<em><b>Sequence</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BEHAVIOR_TRANSFORMATION_DETAIL_DATA__SEQUENCE = 0;

    /**
     * The feature id for the '<em><b>Target Relation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BEHAVIOR_TRANSFORMATION_DETAIL_DATA__TARGET_RELATION = 1;

    /**
     * The feature id for the '<em><b>Operation</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BEHAVIOR_TRANSFORMATION_DETAIL_DATA__OPERATION = 2;

    /**
     * The feature id for the '<em><b>Operation Applicable Stereotype</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BEHAVIOR_TRANSFORMATION_DETAIL_DATA__OPERATION_APPLICABLE_STEREOTYPE = 3;

    /**
     * The feature id for the '<em><b>Message</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BEHAVIOR_TRANSFORMATION_DETAIL_DATA__MESSAGE = 4;

    /**
     * The number of structural features of the '
     * <em>Behavior Transformation Detail Data</em>' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BEHAVIOR_TRANSFORMATION_DETAIL_DATA_FEATURE_COUNT = 5;

    /**
     * The meta object id for the '
     * {@link nexcore.tool.mda.model.designer.transformation.impl.TargetRelationTypeImpl
     * <em>Target Relation Type</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see nexcore.tool.mda.model.designer.transformation.impl.TargetRelationTypeImpl
     * @see nexcore.tool.mda.model.designer.transformation.impl.TransformationPackageImpl#getTargetRelationType()
     * @generated
     */
    int TARGET_RELATION_TYPE = 12;

    /**
     * The feature id for the '<em><b>Source</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TARGET_RELATION_TYPE__SOURCE = 0;

    /**
     * The feature id for the '<em><b>Target</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TARGET_RELATION_TYPE__TARGET = 1;

    /**
     * The number of structural features of the '<em>Target Relation Type</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TARGET_RELATION_TYPE_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link nexcore.tool.mda.model.designer.transformation.impl.OperationTypeImpl
     * <em>Operation Type</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see nexcore.tool.mda.model.designer.transformation.impl.OperationTypeImpl
     * @see nexcore.tool.mda.model.designer.transformation.impl.TransformationPackageImpl#getOperationType()
     * @generated
     */
    int OPERATION_TYPE = 13;

    /**
     * The feature id for the '<em><b>Operation Application Type</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERATION_TYPE__OPERATION_APPLICATION_TYPE = 0;

    /**
     * The feature id for the '<em><b>Assignable Operation Name</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERATION_TYPE__ASSIGNABLE_OPERATION_NAME = 1;

    /**
     * The feature id for the '<em><b>User Defined Operation Name</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERATION_TYPE__USER_DEFINED_OPERATION_NAME = 2;

    /**
     * The number of structural features of the '<em>Operation Type</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERATION_TYPE_FEATURE_COUNT = 3;

    /**
     * The meta object id for the '
     * {@link nexcore.tool.mda.model.designer.transformation.impl.ParentTypeImpl
     * <em>Parent Type</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see nexcore.tool.mda.model.designer.transformation.impl.ParentTypeImpl
     * @see nexcore.tool.mda.model.designer.transformation.impl.TransformationPackageImpl#getParentType()
     * @generated
     */
    int PARENT_TYPE = 14;

    /**
     * The feature id for the '<em><b>Type Name</b></em>' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PARENT_TYPE__TYPE_NAME = 0;

    /**
     * The feature id for the '<em><b>Extension Type</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PARENT_TYPE__EXTENSION_TYPE = 1;

    /**
     * The number of structural features of the '<em>Parent Type</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PARENT_TYPE_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link nexcore.tool.mda.model.designer.transformation.TargetCreationType
     * <em>Target Creation Type</em>}' enum. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see nexcore.tool.mda.model.designer.transformation.TargetCreationType
     * @see nexcore.tool.mda.model.designer.transformation.impl.TransformationPackageImpl#getTargetCreationType()
     * @generated
     */
    int TARGET_CREATION_TYPE = 15;

    /**
     * The meta object id for the '
     * {@link nexcore.tool.mda.model.designer.transformation.LocationKeywordType
     * <em>Location Keyword Type</em>}' enum. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see nexcore.tool.mda.model.designer.transformation.LocationKeywordType
     * @see nexcore.tool.mda.model.designer.transformation.impl.TransformationPackageImpl#getLocationKeywordType()
     * @generated
     */
    int LOCATION_KEYWORD_TYPE = 16;

    /**
     * The meta object id for the '
     * {@link nexcore.tool.mda.model.designer.transformation.TargetType
     * <em>Target Type</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see nexcore.tool.mda.model.designer.transformation.TargetType
     * @see nexcore.tool.mda.model.designer.transformation.impl.TransformationPackageImpl#getTargetType()
     * @generated
     */
    int TARGET_TYPE = 17;

    /**
     * The meta object id for the '
     * {@link nexcore.tool.mda.model.designer.transformation.NameKeywordType
     * <em>Name Keyword Type</em>}' enum. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see nexcore.tool.mda.model.designer.transformation.NameKeywordType
     * @see nexcore.tool.mda.model.designer.transformation.impl.TransformationPackageImpl#getNameKeywordType()
     * @generated
     */
    int NAME_KEYWORD_TYPE = 18;

    /**
     * The meta object id for the '
     * {@link nexcore.tool.mda.model.designer.transformation.CapitalizationType
     * <em>Capitalization Type</em>}' enum. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see nexcore.tool.mda.model.designer.transformation.CapitalizationType
     * @see nexcore.tool.mda.model.designer.transformation.impl.TransformationPackageImpl#getCapitalizationType()
     * @generated
     */
    int CAPITALIZATION_TYPE = 19;

    /**
     * The meta object id for the '
     * {@link nexcore.tool.mda.model.designer.transformation.ApplicationType
     * <em>Application Type</em>}' enum. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see nexcore.tool.mda.model.designer.transformation.ApplicationType
     * @see nexcore.tool.mda.model.designer.transformation.impl.TransformationPackageImpl#getApplicationType()
     * @generated
     */
    int APPLICATION_TYPE = 20;

    /**
     * The meta object id for the '
     * {@link nexcore.tool.mda.model.designer.transformation.OperationApplicationType
     * <em>Operation Application Type</em>}' enum. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see nexcore.tool.mda.model.designer.transformation.OperationApplicationType
     * @see nexcore.tool.mda.model.designer.transformation.impl.TransformationPackageImpl#getOperationApplicationType()
     * @generated
     */
    int OPERATION_APPLICATION_TYPE = 21;

    /**
     * The meta object id for the '
     * {@link nexcore.tool.mda.model.designer.transformation.ExtensionType
     * <em>Extension Type</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see nexcore.tool.mda.model.designer.transformation.ExtensionType
     * @see nexcore.tool.mda.model.designer.transformation.impl.TransformationPackageImpl#getExtensionType()
     * @generated
     */
    int EXTENSION_TYPE = 22;

    /**
     * Returns the meta object for class '
     * {@link nexcore.tool.mda.model.designer.transformation.RuleSet
     * <em>Rule Set</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Rule Set</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.RuleSet
     * @generated
     */
    EClass getRuleSet();

    /**
     * Returns the meta object for the containment reference list '
     * {@link nexcore.tool.mda.model.designer.transformation.RuleSet#getStructureRules
     * <em>Structure Rules</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Structure Rules</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.RuleSet#getStructureRules()
     * @see #getRuleSet()
     * @generated
     */
    EReference getRuleSet_StructureRules();

    /**
     * Returns the meta object for the containment reference list '
     * {@link nexcore.tool.mda.model.designer.transformation.RuleSet#getBehaviorRules
     * <em>Behavior Rules</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Behavior Rules</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.RuleSet#getBehaviorRules()
     * @see #getRuleSet()
     * @generated
     */
    EReference getRuleSet_BehaviorRules();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.designer.transformation.RuleSet#getSourceModelLocation
     * <em>Source Model Location</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Source Model Location</em>
     *         '.
     * @see nexcore.tool.mda.model.designer.transformation.RuleSet#getSourceModelLocation()
     * @see #getRuleSet()
     * @generated
     */
    EAttribute getRuleSet_SourceModelLocation();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.designer.transformation.RuleSet#getSourceProfileLocation
     * <em>Source Profile Location</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Source Profile Location</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.RuleSet#getSourceProfileLocation()
     * @see #getRuleSet()
     * @generated
     */
    EAttribute getRuleSet_SourceProfileLocation();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.designer.transformation.RuleSet#getTargetModelLocation
     * <em>Target Model Location</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Target Model Location</em>
     *         '.
     * @see nexcore.tool.mda.model.designer.transformation.RuleSet#getTargetModelLocation()
     * @see #getRuleSet()
     * @generated
     */
    EAttribute getRuleSet_TargetModelLocation();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.designer.transformation.RuleSet#getTargetProfileLocation
     * <em>Target Profile Location</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Target Profile Location</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.RuleSet#getTargetProfileLocation()
     * @see #getRuleSet()
     * @generated
     */
    EAttribute getRuleSet_TargetProfileLocation();

    /**
     * Returns the meta object for class '
     * {@link nexcore.tool.mda.model.designer.transformation.SourceStructureTransformationData
     * <em>Source Structure Transformation Data</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Source Structure Transformation Data</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.SourceStructureTransformationData
     * @generated
     */
    EClass getSourceStructureTransformationData();

    /**
     * Returns the meta object for the containment reference '
     * {@link nexcore.tool.mda.model.designer.transformation.SourceStructureTransformationData#getSourceType
     * <em>Source Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Source Type</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.SourceStructureTransformationData#getSourceType()
     * @see #getSourceStructureTransformationData()
     * @generated
     */
    EReference getSourceStructureTransformationData_SourceType();

    /**
     * Returns the meta object for the containment reference '
     * {@link nexcore.tool.mda.model.designer.transformation.SourceStructureTransformationData#getSourceLocation
     * <em>Source Location</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Source Location</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.SourceStructureTransformationData#getSourceLocation()
     * @see #getSourceStructureTransformationData()
     * @generated
     */
    EReference getSourceStructureTransformationData_SourceLocation();

    /**
     * Returns the meta object for the containment reference list '
     * {@link nexcore.tool.mda.model.designer.transformation.SourceStructureTransformationData#getTargetStructureTransformationDataSet
     * <em>Target Structure Transformation Data Set</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Target Structure Transformation Data Set</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.SourceStructureTransformationData#getTargetStructureTransformationDataSet()
     * @see #getSourceStructureTransformationData()
     * @generated
     */
    EReference getSourceStructureTransformationData_TargetStructureTransformationDataSet();

    /**
     * Returns the meta object for class '
     * {@link nexcore.tool.mda.model.designer.transformation.SourceType
     * <em>Source Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Source Type</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.SourceType
     * @generated
     */
    EClass getSourceType();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.designer.transformation.SourceType#getSourceName
     * <em>Source Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Source Name</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.SourceType#getSourceName()
     * @see #getSourceType()
     * @generated
     */
    EAttribute getSourceType_SourceName();

    /**
     * Returns the meta object for class '
     * {@link nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData
     * <em>Target Structure Transformation Data</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Target Structure Transformation Data</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData
     * @generated
     */
    EClass getTargetStructureTransformationData();

    /**
     * Returns the meta object for the containment reference '
     * {@link nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData#getDataName
     * <em>Data Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Data Name</em>
     *         '.
     * @see nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData#getDataName()
     * @see #getTargetStructureTransformationData()
     * @generated
     */
    EReference getTargetStructureTransformationData_DataName();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData#getTargetCreationType
     * <em>Target Creation Type</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Target Creation Type</em>
     *         '.
     * @see nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData#getTargetCreationType()
     * @see #getTargetStructureTransformationData()
     * @generated
     */
    EAttribute getTargetStructureTransformationData_TargetCreationType();

    /**
     * Returns the meta object for the containment reference '
     * {@link nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData#getTargetLocation
     * <em>Target Location</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Target Location</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData#getTargetLocation()
     * @see #getTargetStructureTransformationData()
     * @generated
     */
    EReference getTargetStructureTransformationData_TargetLocation();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData#getTargetApplicableStereotype
     * <em>Target Applicable Stereotype</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Target Applicable Stereotype</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData#getTargetApplicableStereotype()
     * @see #getTargetStructureTransformationData()
     * @generated
     */
    EAttribute getTargetStructureTransformationData_TargetApplicableStereotype();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData#getTargetType
     * <em>Target Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Target Type</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData#getTargetType()
     * @see #getTargetStructureTransformationData()
     * @generated
     */
    EAttribute getTargetStructureTransformationData_TargetType();

    /**
     * Returns the meta object for the containment reference list '
     * {@link nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData#getParentType
     * <em>Parent Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Parent Type</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData#getParentType()
     * @see #getTargetStructureTransformationData()
     * @generated
     */
    EReference getTargetStructureTransformationData_ParentType();

    /**
     * Returns the meta object for the containment reference '
     * {@link nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData#getTargetName
     * <em>Target Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Target Name</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData#getTargetName()
     * @see #getTargetStructureTransformationData()
     * @generated
     */
    EReference getTargetStructureTransformationData_TargetName();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData#getApplicationType
     * <em>Application Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Application Type</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData#getApplicationType()
     * @see #getTargetStructureTransformationData()
     * @generated
     */
    EAttribute getTargetStructureTransformationData_ApplicationType();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData#isPropertyCreation
     * <em>Property Creation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Property Creation</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData#isPropertyCreation()
     * @see #getTargetStructureTransformationData()
     * @generated
     */
    EAttribute getTargetStructureTransformationData_PropertyCreation();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData#getPropertyApplicableStereotype
     * <em>Property Applicable Stereotype</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Property Applicable Stereotype</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData#getPropertyApplicableStereotype()
     * @see #getTargetStructureTransformationData()
     * @generated
     */
    EAttribute getTargetStructureTransformationData_PropertyApplicableStereotype();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData#isOperationCreation
     * <em>Operation Creation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Operation Creation</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData#isOperationCreation()
     * @see #getTargetStructureTransformationData()
     * @generated
     */
    EAttribute getTargetStructureTransformationData_OperationCreation();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData#getOperationApplicableStereotype
     * <em>Operation Applicable Stereotype</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Operation Applicable Stereotype</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData#getOperationApplicableStereotype()
     * @see #getTargetStructureTransformationData()
     * @generated
     */
    EAttribute getTargetStructureTransformationData_OperationApplicableStereotype();

    /**
     * Returns the meta object for class '
     * {@link nexcore.tool.mda.model.designer.transformation.DataNameType
     * <em>Data Name Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Data Name Type</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.DataNameType
     * @generated
     */
    EClass getDataNameType();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.designer.transformation.DataNameType#getDataName
     * <em>Data Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Data Name</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.DataNameType#getDataName()
     * @see #getDataNameType()
     * @generated
     */
    EAttribute getDataNameType_DataName();

    /**
     * Returns the meta object for class '
     * {@link nexcore.tool.mda.model.designer.transformation.LocationType
     * <em>Location Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Location Type</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.LocationType
     * @generated
     */
    EClass getLocationType();

    /**
     * Returns the meta object for the containment reference list '
     * {@link nexcore.tool.mda.model.designer.transformation.LocationType#getLocation
     * <em>Location</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Location</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.LocationType#getLocation()
     * @see #getLocationType()
     * @generated
     */
    EReference getLocationType_Location();

    /**
     * Returns the meta object for class '
     * {@link nexcore.tool.mda.model.designer.transformation.LocationSegment
     * <em>Location Segment</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Location Segment</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.LocationSegment
     * @generated
     */
    EClass getLocationSegment();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.designer.transformation.LocationSegment#getSequence
     * <em>Sequence</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Sequence</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.LocationSegment#getSequence()
     * @see #getLocationSegment()
     * @generated
     */
    EAttribute getLocationSegment_Sequence();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.designer.transformation.LocationSegment#getLocationName
     * <em>Location Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Location Name</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.LocationSegment#getLocationName()
     * @see #getLocationSegment()
     * @generated
     */
    EAttribute getLocationSegment_LocationName();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.designer.transformation.LocationSegment#getLocationKeyword
     * <em>Location Keyword</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Location Keyword</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.LocationSegment#getLocationKeyword()
     * @see #getLocationSegment()
     * @generated
     */
    EAttribute getLocationSegment_LocationKeyword();

    /**
     * Returns the meta object for class '
     * {@link nexcore.tool.mda.model.designer.transformation.NameType
     * <em>Name Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Name Type</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.NameType
     * @generated
     */
    EClass getNameType();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.designer.transformation.NameType#getPrefix
     * <em>Prefix</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Prefix</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.NameType#getPrefix()
     * @see #getNameType()
     * @generated
     */
    EAttribute getNameType_Prefix();

    /**
     * Returns the meta object for the containment reference '
     * {@link nexcore.tool.mda.model.designer.transformation.NameType#getNamePart
     * <em>Name Part</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Name Part</em>
     *         '.
     * @see nexcore.tool.mda.model.designer.transformation.NameType#getNamePart()
     * @see #getNameType()
     * @generated
     */
    EReference getNameType_NamePart();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.designer.transformation.NameType#getPostfix
     * <em>Postfix</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Postfix</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.NameType#getPostfix()
     * @see #getNameType()
     * @generated
     */
    EAttribute getNameType_Postfix();

    /**
     * Returns the meta object for class '
     * {@link nexcore.tool.mda.model.designer.transformation.NamePartType
     * <em>Name Part Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Name Part Type</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.NamePartType
     * @generated
     */
    EClass getNamePartType();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.designer.transformation.NamePartType#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.NamePartType#getName()
     * @see #getNamePartType()
     * @generated
     */
    EAttribute getNamePartType_Name();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.designer.transformation.NamePartType#getCapitalization
     * <em>Capitalization</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Capitalization</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.NamePartType#getCapitalization()
     * @see #getNamePartType()
     * @generated
     */
    EAttribute getNamePartType_Capitalization();

    /**
     * Returns the meta object for class '
     * {@link nexcore.tool.mda.model.designer.transformation.BehaviorTransformationData
     * <em>Behavior Transformation Data</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Behavior Transformation Data</em>
     *         '.
     * @see nexcore.tool.mda.model.designer.transformation.BehaviorTransformationData
     * @generated
     */
    EClass getBehaviorTransformationData();

    /**
     * Returns the meta object for the containment reference '
     * {@link nexcore.tool.mda.model.designer.transformation.BehaviorTransformationData#getSourceRelation
     * <em>Source Relation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Source Relation</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.BehaviorTransformationData#getSourceRelation()
     * @see #getBehaviorTransformationData()
     * @generated
     */
    EReference getBehaviorTransformationData_SourceRelation();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.designer.transformation.BehaviorTransformationData#getRemark
     * <em>Remark</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Remark</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.BehaviorTransformationData#getRemark()
     * @see #getBehaviorTransformationData()
     * @generated
     */
    EAttribute getBehaviorTransformationData_Remark();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.designer.transformation.BehaviorTransformationData#isSelf
     * <em>Self</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Self</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.BehaviorTransformationData#isSelf()
     * @see #getBehaviorTransformationData()
     * @generated
     */
    EAttribute getBehaviorTransformationData_Self();

    /**
     * Returns the meta object for the containment reference list '
     * {@link nexcore.tool.mda.model.designer.transformation.BehaviorTransformationData#getBehaviorTransformationDetailDataSet
     * <em>Behavior Transformation Detail Data Set</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Behavior Transformation Detail Data Set</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.BehaviorTransformationData#getBehaviorTransformationDetailDataSet()
     * @see #getBehaviorTransformationData()
     * @generated
     */
    EReference getBehaviorTransformationData_BehaviorTransformationDetailDataSet();

    /**
     * Returns the meta object for class '
     * {@link nexcore.tool.mda.model.designer.transformation.SourceRelationType
     * <em>Source Relation Type</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Source Relation Type</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.SourceRelationType
     * @generated
     */
    EClass getSourceRelationType();

    /**
     * Returns the meta object for the containment reference '
     * {@link nexcore.tool.mda.model.designer.transformation.SourceRelationType#getSource
     * <em>Source</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Source</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.SourceRelationType#getSource()
     * @see #getSourceRelationType()
     * @generated
     */
    EReference getSourceRelationType_Source();

    /**
     * Returns the meta object for the containment reference '
     * {@link nexcore.tool.mda.model.designer.transformation.SourceRelationType#getTarget
     * <em>Target</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Target</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.SourceRelationType#getTarget()
     * @see #getSourceRelationType()
     * @generated
     */
    EReference getSourceRelationType_Target();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.designer.transformation.SourceRelationType#isSelfRelation
     * <em>Self Relation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Self Relation</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.SourceRelationType#isSelfRelation()
     * @see #getSourceRelationType()
     * @generated
     */
    EAttribute getSourceRelationType_SelfRelation();

    /**
     * Returns the meta object for class '
     * {@link nexcore.tool.mda.model.designer.transformation.BehaviorTransformationDetailData
     * <em>Behavior Transformation Detail Data</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Behavior Transformation Detail Data</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.BehaviorTransformationDetailData
     * @generated
     */
    EClass getBehaviorTransformationDetailData();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.designer.transformation.BehaviorTransformationDetailData#getSequence
     * <em>Sequence</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Sequence</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.BehaviorTransformationDetailData#getSequence()
     * @see #getBehaviorTransformationDetailData()
     * @generated
     */
    EAttribute getBehaviorTransformationDetailData_Sequence();

    /**
     * Returns the meta object for the containment reference '
     * {@link nexcore.tool.mda.model.designer.transformation.BehaviorTransformationDetailData#getTargetRelation
     * <em>Target Relation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Target Relation</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.BehaviorTransformationDetailData#getTargetRelation()
     * @see #getBehaviorTransformationDetailData()
     * @generated
     */
    EReference getBehaviorTransformationDetailData_TargetRelation();

    /**
     * Returns the meta object for the containment reference '
     * {@link nexcore.tool.mda.model.designer.transformation.BehaviorTransformationDetailData#getOperation
     * <em>Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Operation</em>
     *         '.
     * @see nexcore.tool.mda.model.designer.transformation.BehaviorTransformationDetailData#getOperation()
     * @see #getBehaviorTransformationDetailData()
     * @generated
     */
    EReference getBehaviorTransformationDetailData_Operation();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.designer.transformation.BehaviorTransformationDetailData#getOperationApplicableStereotype
     * <em>Operation Applicable Stereotype</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Operation Applicable Stereotype</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.BehaviorTransformationDetailData#getOperationApplicableStereotype()
     * @see #getBehaviorTransformationDetailData()
     * @generated
     */
    EAttribute getBehaviorTransformationDetailData_OperationApplicableStereotype();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.designer.transformation.BehaviorTransformationDetailData#getMessage
     * <em>Message</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Message</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.BehaviorTransformationDetailData#getMessage()
     * @see #getBehaviorTransformationDetailData()
     * @generated
     */
    EAttribute getBehaviorTransformationDetailData_Message();

    /**
     * Returns the meta object for class '
     * {@link nexcore.tool.mda.model.designer.transformation.TargetRelationType
     * <em>Target Relation Type</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Target Relation Type</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.TargetRelationType
     * @generated
     */
    EClass getTargetRelationType();

    /**
     * Returns the meta object for the containment reference '
     * {@link nexcore.tool.mda.model.designer.transformation.TargetRelationType#getSource
     * <em>Source</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Source</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.TargetRelationType#getSource()
     * @see #getTargetRelationType()
     * @generated
     */
    EReference getTargetRelationType_Source();

    /**
     * Returns the meta object for the containment reference '
     * {@link nexcore.tool.mda.model.designer.transformation.TargetRelationType#getTarget
     * <em>Target</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Target</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.TargetRelationType#getTarget()
     * @see #getTargetRelationType()
     * @generated
     */
    EReference getTargetRelationType_Target();

    /**
     * Returns the meta object for class '
     * {@link nexcore.tool.mda.model.designer.transformation.OperationType
     * <em>Operation Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Operation Type</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.OperationType
     * @generated
     */
    EClass getOperationType();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.designer.transformation.OperationType#getOperationApplicationType
     * <em>Operation Application Type</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Operation Application Type</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.OperationType#getOperationApplicationType()
     * @see #getOperationType()
     * @generated
     */
    EAttribute getOperationType_OperationApplicationType();

    /**
     * Returns the meta object for the containment reference '
     * {@link nexcore.tool.mda.model.designer.transformation.OperationType#getAssignableOperationName
     * <em>Assignable Operation Name</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Assignable Operation Name</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.OperationType#getAssignableOperationName()
     * @see #getOperationType()
     * @generated
     */
    EReference getOperationType_AssignableOperationName();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.designer.transformation.OperationType#getUserDefinedOperationName
     * <em>User Defined Operation Name</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>User Defined Operation Name</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.OperationType#getUserDefinedOperationName()
     * @see #getOperationType()
     * @generated
     */
    EAttribute getOperationType_UserDefinedOperationName();

    /**
     * Returns the meta object for class '
     * {@link nexcore.tool.mda.model.designer.transformation.ParentType
     * <em>Parent Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Parent Type</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.ParentType
     * @generated
     */
    EClass getParentType();

    /**
     * Returns the meta object for the attribute list '
     * {@link nexcore.tool.mda.model.designer.transformation.ParentType#getTypeName
     * <em>Type Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute list '<em>Type Name</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.ParentType#getTypeName()
     * @see #getParentType()
     * @generated
     */
    EAttribute getParentType_TypeName();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.mda.model.designer.transformation.ParentType#getExtensionType
     * <em>Extension Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Extension Type</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.ParentType#getExtensionType()
     * @see #getParentType()
     * @generated
     */
    EAttribute getParentType_ExtensionType();

    /**
     * Returns the meta object for enum '
     * {@link nexcore.tool.mda.model.designer.transformation.TargetCreationType
     * <em>Target Creation Type</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for enum '<em>Target Creation Type</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.TargetCreationType
     * @generated
     */
    EEnum getTargetCreationType();

    /**
     * Returns the meta object for enum '
     * {@link nexcore.tool.mda.model.designer.transformation.LocationKeywordType
     * <em>Location Keyword Type</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for enum '<em>Location Keyword Type</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.LocationKeywordType
     * @generated
     */
    EEnum getLocationKeywordType();

    /**
     * Returns the meta object for enum '
     * {@link nexcore.tool.mda.model.designer.transformation.TargetType
     * <em>Target Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>Target Type</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.TargetType
     * @generated
     */
    EEnum getTargetType();

    /**
     * Returns the meta object for enum '
     * {@link nexcore.tool.mda.model.designer.transformation.NameKeywordType
     * <em>Name Keyword Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for enum '<em>Name Keyword Type</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.NameKeywordType
     * @generated
     */
    EEnum getNameKeywordType();

    /**
     * Returns the meta object for enum '
     * {@link nexcore.tool.mda.model.designer.transformation.CapitalizationType
     * <em>Capitalization Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for enum '<em>Capitalization Type</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.CapitalizationType
     * @generated
     */
    EEnum getCapitalizationType();

    /**
     * Returns the meta object for enum '
     * {@link nexcore.tool.mda.model.designer.transformation.ApplicationType
     * <em>Application Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for enum '<em>Application Type</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.ApplicationType
     * @generated
     */
    EEnum getApplicationType();

    /**
     * Returns the meta object for enum '
     * {@link nexcore.tool.mda.model.designer.transformation.OperationApplicationType
     * <em>Operation Application Type</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for enum '<em>Operation Application Type</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.OperationApplicationType
     * @generated
     */
    EEnum getOperationApplicationType();

    /**
     * Returns the meta object for enum '
     * {@link nexcore.tool.mda.model.designer.transformation.ExtensionType
     * <em>Extension Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>Extension Type</em>'.
     * @see nexcore.tool.mda.model.designer.transformation.ExtensionType
     * @generated
     */
    EEnum getExtensionType();

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
         * {@link nexcore.tool.mda.model.designer.transformation.impl.RuleSetImpl
         * <em>Rule Set</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @see nexcore.tool.mda.model.designer.transformation.impl.RuleSetImpl
         * @see nexcore.tool.mda.model.designer.transformation.impl.TransformationPackageImpl#getRuleSet()
         * @generated
         */
        EClass RULE_SET = eINSTANCE.getRuleSet();

        /**
         * The meta object literal for the '<em><b>Structure Rules</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference RULE_SET__STRUCTURE_RULES = eINSTANCE.getRuleSet_StructureRules();

        /**
         * The meta object literal for the '<em><b>Behavior Rules</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference RULE_SET__BEHAVIOR_RULES = eINSTANCE.getRuleSet_BehaviorRules();

        /**
         * The meta object literal for the '
         * <em><b>Source Model Location</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute RULE_SET__SOURCE_MODEL_LOCATION = eINSTANCE.getRuleSet_SourceModelLocation();

        /**
         * The meta object literal for the '
         * <em><b>Source Profile Location</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute RULE_SET__SOURCE_PROFILE_LOCATION = eINSTANCE.getRuleSet_SourceProfileLocation();

        /**
         * The meta object literal for the '
         * <em><b>Target Model Location</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute RULE_SET__TARGET_MODEL_LOCATION = eINSTANCE.getRuleSet_TargetModelLocation();

        /**
         * The meta object literal for the '
         * <em><b>Target Profile Location</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute RULE_SET__TARGET_PROFILE_LOCATION = eINSTANCE.getRuleSet_TargetProfileLocation();

        /**
         * The meta object literal for the '
         * {@link nexcore.tool.mda.model.designer.transformation.impl.SourceStructureTransformationDataImpl
         * <em>Source Structure Transformation Data</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see nexcore.tool.mda.model.designer.transformation.impl.SourceStructureTransformationDataImpl
         * @see nexcore.tool.mda.model.designer.transformation.impl.TransformationPackageImpl#getSourceStructureTransformationData()
         * @generated
         */
        EClass SOURCE_STRUCTURE_TRANSFORMATION_DATA = eINSTANCE.getSourceStructureTransformationData();

        /**
         * The meta object literal for the '<em><b>Source Type</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference SOURCE_STRUCTURE_TRANSFORMATION_DATA__SOURCE_TYPE = eINSTANCE.getSourceStructureTransformationData_SourceType();

        /**
         * The meta object literal for the '<em><b>Source Location</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference SOURCE_STRUCTURE_TRANSFORMATION_DATA__SOURCE_LOCATION = eINSTANCE.getSourceStructureTransformationData_SourceLocation();

        /**
         * The meta object literal for the '
         * <em><b>Target Structure Transformation Data Set</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference SOURCE_STRUCTURE_TRANSFORMATION_DATA__TARGET_STRUCTURE_TRANSFORMATION_DATA_SET = eINSTANCE.getSourceStructureTransformationData_TargetStructureTransformationDataSet();

        /**
         * The meta object literal for the '
         * {@link nexcore.tool.mda.model.designer.transformation.impl.SourceTypeImpl
         * <em>Source Type</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see nexcore.tool.mda.model.designer.transformation.impl.SourceTypeImpl
         * @see nexcore.tool.mda.model.designer.transformation.impl.TransformationPackageImpl#getSourceType()
         * @generated
         */
        EClass SOURCE_TYPE = eINSTANCE.getSourceType();

        /**
         * The meta object literal for the '<em><b>Source Name</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute SOURCE_TYPE__SOURCE_NAME = eINSTANCE.getSourceType_SourceName();

        /**
         * The meta object literal for the '
         * {@link nexcore.tool.mda.model.designer.transformation.impl.TargetStructureTransformationDataImpl
         * <em>Target Structure Transformation Data</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see nexcore.tool.mda.model.designer.transformation.impl.TargetStructureTransformationDataImpl
         * @see nexcore.tool.mda.model.designer.transformation.impl.TransformationPackageImpl#getTargetStructureTransformationData()
         * @generated
         */
        EClass TARGET_STRUCTURE_TRANSFORMATION_DATA = eINSTANCE.getTargetStructureTransformationData();

        /**
         * The meta object literal for the '<em><b>Data Name</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference TARGET_STRUCTURE_TRANSFORMATION_DATA__DATA_NAME = eINSTANCE.getTargetStructureTransformationData_DataName();

        /**
         * The meta object literal for the '<em><b>Target Creation Type</b></em>
         * ' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TARGET_STRUCTURE_TRANSFORMATION_DATA__TARGET_CREATION_TYPE = eINSTANCE.getTargetStructureTransformationData_TargetCreationType();

        /**
         * The meta object literal for the '<em><b>Target Location</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference TARGET_STRUCTURE_TRANSFORMATION_DATA__TARGET_LOCATION = eINSTANCE.getTargetStructureTransformationData_TargetLocation();

        /**
         * The meta object literal for the '
         * <em><b>Target Applicable Stereotype</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TARGET_STRUCTURE_TRANSFORMATION_DATA__TARGET_APPLICABLE_STEREOTYPE = eINSTANCE.getTargetStructureTransformationData_TargetApplicableStereotype();

        /**
         * The meta object literal for the '<em><b>Target Type</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TARGET_STRUCTURE_TRANSFORMATION_DATA__TARGET_TYPE = eINSTANCE.getTargetStructureTransformationData_TargetType();

        /**
         * The meta object literal for the '<em><b>Parent Type</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference TARGET_STRUCTURE_TRANSFORMATION_DATA__PARENT_TYPE = eINSTANCE.getTargetStructureTransformationData_ParentType();

        /**
         * The meta object literal for the '<em><b>Target Name</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference TARGET_STRUCTURE_TRANSFORMATION_DATA__TARGET_NAME = eINSTANCE.getTargetStructureTransformationData_TargetName();

        /**
         * The meta object literal for the '<em><b>Application Type</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TARGET_STRUCTURE_TRANSFORMATION_DATA__APPLICATION_TYPE = eINSTANCE.getTargetStructureTransformationData_ApplicationType();

        /**
         * The meta object literal for the '<em><b>Property Creation</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TARGET_STRUCTURE_TRANSFORMATION_DATA__PROPERTY_CREATION = eINSTANCE.getTargetStructureTransformationData_PropertyCreation();

        /**
         * The meta object literal for the '
         * <em><b>Property Applicable Stereotype</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TARGET_STRUCTURE_TRANSFORMATION_DATA__PROPERTY_APPLICABLE_STEREOTYPE = eINSTANCE.getTargetStructureTransformationData_PropertyApplicableStereotype();

        /**
         * The meta object literal for the '<em><b>Operation Creation</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TARGET_STRUCTURE_TRANSFORMATION_DATA__OPERATION_CREATION = eINSTANCE.getTargetStructureTransformationData_OperationCreation();

        /**
         * The meta object literal for the '
         * <em><b>Operation Applicable Stereotype</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TARGET_STRUCTURE_TRANSFORMATION_DATA__OPERATION_APPLICABLE_STEREOTYPE = eINSTANCE.getTargetStructureTransformationData_OperationApplicableStereotype();

        /**
         * The meta object literal for the '
         * {@link nexcore.tool.mda.model.designer.transformation.impl.DataNameTypeImpl
         * <em>Data Name Type</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see nexcore.tool.mda.model.designer.transformation.impl.DataNameTypeImpl
         * @see nexcore.tool.mda.model.designer.transformation.impl.TransformationPackageImpl#getDataNameType()
         * @generated
         */
        EClass DATA_NAME_TYPE = eINSTANCE.getDataNameType();

        /**
         * The meta object literal for the '<em><b>Data Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DATA_NAME_TYPE__DATA_NAME = eINSTANCE.getDataNameType_DataName();

        /**
         * The meta object literal for the '
         * {@link nexcore.tool.mda.model.designer.transformation.impl.LocationTypeImpl
         * <em>Location Type</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see nexcore.tool.mda.model.designer.transformation.impl.LocationTypeImpl
         * @see nexcore.tool.mda.model.designer.transformation.impl.TransformationPackageImpl#getLocationType()
         * @generated
         */
        EClass LOCATION_TYPE = eINSTANCE.getLocationType();

        /**
         * The meta object literal for the '<em><b>Location</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference LOCATION_TYPE__LOCATION = eINSTANCE.getLocationType_Location();

        /**
         * The meta object literal for the '
         * {@link nexcore.tool.mda.model.designer.transformation.impl.LocationSegmentImpl
         * <em>Location Segment</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see nexcore.tool.mda.model.designer.transformation.impl.LocationSegmentImpl
         * @see nexcore.tool.mda.model.designer.transformation.impl.TransformationPackageImpl#getLocationSegment()
         * @generated
         */
        EClass LOCATION_SEGMENT = eINSTANCE.getLocationSegment();

        /**
         * The meta object literal for the '<em><b>Sequence</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute LOCATION_SEGMENT__SEQUENCE = eINSTANCE.getLocationSegment_Sequence();

        /**
         * The meta object literal for the '<em><b>Location Name</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute LOCATION_SEGMENT__LOCATION_NAME = eINSTANCE.getLocationSegment_LocationName();

        /**
         * The meta object literal for the '<em><b>Location Keyword</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute LOCATION_SEGMENT__LOCATION_KEYWORD = eINSTANCE.getLocationSegment_LocationKeyword();

        /**
         * The meta object literal for the '
         * {@link nexcore.tool.mda.model.designer.transformation.impl.NameTypeImpl
         * <em>Name Type</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @see nexcore.tool.mda.model.designer.transformation.impl.NameTypeImpl
         * @see nexcore.tool.mda.model.designer.transformation.impl.TransformationPackageImpl#getNameType()
         * @generated
         */
        EClass NAME_TYPE = eINSTANCE.getNameType();

        /**
         * The meta object literal for the '<em><b>Prefix</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute NAME_TYPE__PREFIX = eINSTANCE.getNameType_Prefix();

        /**
         * The meta object literal for the '<em><b>Name Part</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference NAME_TYPE__NAME_PART = eINSTANCE.getNameType_NamePart();

        /**
         * The meta object literal for the '<em><b>Postfix</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute NAME_TYPE__POSTFIX = eINSTANCE.getNameType_Postfix();

        /**
         * The meta object literal for the '
         * {@link nexcore.tool.mda.model.designer.transformation.impl.NamePartTypeImpl
         * <em>Name Part Type</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see nexcore.tool.mda.model.designer.transformation.impl.NamePartTypeImpl
         * @see nexcore.tool.mda.model.designer.transformation.impl.TransformationPackageImpl#getNamePartType()
         * @generated
         */
        EClass NAME_PART_TYPE = eINSTANCE.getNamePartType();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute NAME_PART_TYPE__NAME = eINSTANCE.getNamePartType_Name();

        /**
         * The meta object literal for the '<em><b>Capitalization</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute NAME_PART_TYPE__CAPITALIZATION = eINSTANCE.getNamePartType_Capitalization();

        /**
         * The meta object literal for the '
         * {@link nexcore.tool.mda.model.designer.transformation.impl.BehaviorTransformationDataImpl
         * <em>Behavior Transformation Data</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see nexcore.tool.mda.model.designer.transformation.impl.BehaviorTransformationDataImpl
         * @see nexcore.tool.mda.model.designer.transformation.impl.TransformationPackageImpl#getBehaviorTransformationData()
         * @generated
         */
        EClass BEHAVIOR_TRANSFORMATION_DATA = eINSTANCE.getBehaviorTransformationData();

        /**
         * The meta object literal for the '<em><b>Source Relation</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference BEHAVIOR_TRANSFORMATION_DATA__SOURCE_RELATION = eINSTANCE.getBehaviorTransformationData_SourceRelation();

        /**
         * The meta object literal for the '<em><b>Remark</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute BEHAVIOR_TRANSFORMATION_DATA__REMARK = eINSTANCE.getBehaviorTransformationData_Remark();

        /**
         * The meta object literal for the '<em><b>Self</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute BEHAVIOR_TRANSFORMATION_DATA__SELF = eINSTANCE.getBehaviorTransformationData_Self();

        /**
         * The meta object literal for the '
         * <em><b>Behavior Transformation Detail Data Set</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference BEHAVIOR_TRANSFORMATION_DATA__BEHAVIOR_TRANSFORMATION_DETAIL_DATA_SET = eINSTANCE.getBehaviorTransformationData_BehaviorTransformationDetailDataSet();

        /**
         * The meta object literal for the '
         * {@link nexcore.tool.mda.model.designer.transformation.impl.SourceRelationTypeImpl
         * <em>Source Relation Type</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see nexcore.tool.mda.model.designer.transformation.impl.SourceRelationTypeImpl
         * @see nexcore.tool.mda.model.designer.transformation.impl.TransformationPackageImpl#getSourceRelationType()
         * @generated
         */
        EClass SOURCE_RELATION_TYPE = eINSTANCE.getSourceRelationType();

        /**
         * The meta object literal for the '<em><b>Source</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference SOURCE_RELATION_TYPE__SOURCE = eINSTANCE.getSourceRelationType_Source();

        /**
         * The meta object literal for the '<em><b>Target</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference SOURCE_RELATION_TYPE__TARGET = eINSTANCE.getSourceRelationType_Target();

        /**
         * The meta object literal for the '<em><b>Self Relation</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute SOURCE_RELATION_TYPE__SELF_RELATION = eINSTANCE.getSourceRelationType_SelfRelation();

        /**
         * The meta object literal for the '
         * {@link nexcore.tool.mda.model.designer.transformation.impl.BehaviorTransformationDetailDataImpl
         * <em>Behavior Transformation Detail Data</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see nexcore.tool.mda.model.designer.transformation.impl.BehaviorTransformationDetailDataImpl
         * @see nexcore.tool.mda.model.designer.transformation.impl.TransformationPackageImpl#getBehaviorTransformationDetailData()
         * @generated
         */
        EClass BEHAVIOR_TRANSFORMATION_DETAIL_DATA = eINSTANCE.getBehaviorTransformationDetailData();

        /**
         * The meta object literal for the '<em><b>Sequence</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute BEHAVIOR_TRANSFORMATION_DETAIL_DATA__SEQUENCE = eINSTANCE.getBehaviorTransformationDetailData_Sequence();

        /**
         * The meta object literal for the '<em><b>Target Relation</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference BEHAVIOR_TRANSFORMATION_DETAIL_DATA__TARGET_RELATION = eINSTANCE.getBehaviorTransformationDetailData_TargetRelation();

        /**
         * The meta object literal for the '<em><b>Operation</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference BEHAVIOR_TRANSFORMATION_DETAIL_DATA__OPERATION = eINSTANCE.getBehaviorTransformationDetailData_Operation();

        /**
         * The meta object literal for the '
         * <em><b>Operation Applicable Stereotype</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute BEHAVIOR_TRANSFORMATION_DETAIL_DATA__OPERATION_APPLICABLE_STEREOTYPE = eINSTANCE.getBehaviorTransformationDetailData_OperationApplicableStereotype();

        /**
         * The meta object literal for the '<em><b>Message</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute BEHAVIOR_TRANSFORMATION_DETAIL_DATA__MESSAGE = eINSTANCE.getBehaviorTransformationDetailData_Message();

        /**
         * The meta object literal for the '
         * {@link nexcore.tool.mda.model.designer.transformation.impl.TargetRelationTypeImpl
         * <em>Target Relation Type</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see nexcore.tool.mda.model.designer.transformation.impl.TargetRelationTypeImpl
         * @see nexcore.tool.mda.model.designer.transformation.impl.TransformationPackageImpl#getTargetRelationType()
         * @generated
         */
        EClass TARGET_RELATION_TYPE = eINSTANCE.getTargetRelationType();

        /**
         * The meta object literal for the '<em><b>Source</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference TARGET_RELATION_TYPE__SOURCE = eINSTANCE.getTargetRelationType_Source();

        /**
         * The meta object literal for the '<em><b>Target</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference TARGET_RELATION_TYPE__TARGET = eINSTANCE.getTargetRelationType_Target();

        /**
         * The meta object literal for the '
         * {@link nexcore.tool.mda.model.designer.transformation.impl.OperationTypeImpl
         * <em>Operation Type</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see nexcore.tool.mda.model.designer.transformation.impl.OperationTypeImpl
         * @see nexcore.tool.mda.model.designer.transformation.impl.TransformationPackageImpl#getOperationType()
         * @generated
         */
        EClass OPERATION_TYPE = eINSTANCE.getOperationType();

        /**
         * The meta object literal for the '
         * <em><b>Operation Application Type</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute OPERATION_TYPE__OPERATION_APPLICATION_TYPE = eINSTANCE.getOperationType_OperationApplicationType();

        /**
         * The meta object literal for the '
         * <em><b>Assignable Operation Name</b></em>' containment reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference OPERATION_TYPE__ASSIGNABLE_OPERATION_NAME = eINSTANCE.getOperationType_AssignableOperationName();

        /**
         * The meta object literal for the '
         * <em><b>User Defined Operation Name</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute OPERATION_TYPE__USER_DEFINED_OPERATION_NAME = eINSTANCE.getOperationType_UserDefinedOperationName();

        /**
         * The meta object literal for the '
         * {@link nexcore.tool.mda.model.designer.transformation.impl.ParentTypeImpl
         * <em>Parent Type</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see nexcore.tool.mda.model.designer.transformation.impl.ParentTypeImpl
         * @see nexcore.tool.mda.model.designer.transformation.impl.TransformationPackageImpl#getParentType()
         * @generated
         */
        EClass PARENT_TYPE = eINSTANCE.getParentType();

        /**
         * The meta object literal for the '<em><b>Type Name</b></em>' attribute
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PARENT_TYPE__TYPE_NAME = eINSTANCE.getParentType_TypeName();

        /**
         * The meta object literal for the '<em><b>Extension Type</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PARENT_TYPE__EXTENSION_TYPE = eINSTANCE.getParentType_ExtensionType();

        /**
         * The meta object literal for the '
         * {@link nexcore.tool.mda.model.designer.transformation.TargetCreationType
         * <em>Target Creation Type</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see nexcore.tool.mda.model.designer.transformation.TargetCreationType
         * @see nexcore.tool.mda.model.designer.transformation.impl.TransformationPackageImpl#getTargetCreationType()
         * @generated
         */
        EEnum TARGET_CREATION_TYPE = eINSTANCE.getTargetCreationType();

        /**
         * The meta object literal for the '
         * {@link nexcore.tool.mda.model.designer.transformation.LocationKeywordType
         * <em>Location Keyword Type</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see nexcore.tool.mda.model.designer.transformation.LocationKeywordType
         * @see nexcore.tool.mda.model.designer.transformation.impl.TransformationPackageImpl#getLocationKeywordType()
         * @generated
         */
        EEnum LOCATION_KEYWORD_TYPE = eINSTANCE.getLocationKeywordType();

        /**
         * The meta object literal for the '
         * {@link nexcore.tool.mda.model.designer.transformation.TargetType
         * <em>Target Type</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see nexcore.tool.mda.model.designer.transformation.TargetType
         * @see nexcore.tool.mda.model.designer.transformation.impl.TransformationPackageImpl#getTargetType()
         * @generated
         */
        EEnum TARGET_TYPE = eINSTANCE.getTargetType();

        /**
         * The meta object literal for the '
         * {@link nexcore.tool.mda.model.designer.transformation.NameKeywordType
         * <em>Name Keyword Type</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see nexcore.tool.mda.model.designer.transformation.NameKeywordType
         * @see nexcore.tool.mda.model.designer.transformation.impl.TransformationPackageImpl#getNameKeywordType()
         * @generated
         */
        EEnum NAME_KEYWORD_TYPE = eINSTANCE.getNameKeywordType();

        /**
         * The meta object literal for the '
         * {@link nexcore.tool.mda.model.designer.transformation.CapitalizationType
         * <em>Capitalization Type</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see nexcore.tool.mda.model.designer.transformation.CapitalizationType
         * @see nexcore.tool.mda.model.designer.transformation.impl.TransformationPackageImpl#getCapitalizationType()
         * @generated
         */
        EEnum CAPITALIZATION_TYPE = eINSTANCE.getCapitalizationType();

        /**
         * The meta object literal for the '
         * {@link nexcore.tool.mda.model.designer.transformation.ApplicationType
         * <em>Application Type</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see nexcore.tool.mda.model.designer.transformation.ApplicationType
         * @see nexcore.tool.mda.model.designer.transformation.impl.TransformationPackageImpl#getApplicationType()
         * @generated
         */
        EEnum APPLICATION_TYPE = eINSTANCE.getApplicationType();

        /**
         * The meta object literal for the '
         * {@link nexcore.tool.mda.model.designer.transformation.OperationApplicationType
         * <em>Operation Application Type</em>}' enum. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see nexcore.tool.mda.model.designer.transformation.OperationApplicationType
         * @see nexcore.tool.mda.model.designer.transformation.impl.TransformationPackageImpl#getOperationApplicationType()
         * @generated
         */
        EEnum OPERATION_APPLICATION_TYPE = eINSTANCE.getOperationApplicationType();

        /**
         * The meta object literal for the '
         * {@link nexcore.tool.mda.model.designer.transformation.ExtensionType
         * <em>Extension Type</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see nexcore.tool.mda.model.designer.transformation.ExtensionType
         * @see nexcore.tool.mda.model.designer.transformation.impl.TransformationPackageImpl#getExtensionType()
         * @generated
         */
        EEnum EXTENSION_TYPE = eINSTANCE.getExtensionType();

    }

} // TransformationPackage
