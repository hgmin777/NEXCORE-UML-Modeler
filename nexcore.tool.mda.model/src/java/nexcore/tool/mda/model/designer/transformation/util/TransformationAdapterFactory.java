/*
 * Copyright (c) 2010 SK C&C Co., Ltd. All rights reserved.
 * 
 * This software is the confidential and proprietary information of SK C&C. You
 * shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK
 * C&C.
 */

package nexcore.tool.mda.model.designer.transformation.util;

import nexcore.tool.mda.model.designer.transformation.BehaviorTransformationData;
import nexcore.tool.mda.model.designer.transformation.BehaviorTransformationDetailData;
import nexcore.tool.mda.model.designer.transformation.DataNameType;
import nexcore.tool.mda.model.designer.transformation.LocationSegment;
import nexcore.tool.mda.model.designer.transformation.LocationType;
import nexcore.tool.mda.model.designer.transformation.NamePartType;
import nexcore.tool.mda.model.designer.transformation.NameType;
import nexcore.tool.mda.model.designer.transformation.OperationType;
import nexcore.tool.mda.model.designer.transformation.ParentType;
import nexcore.tool.mda.model.designer.transformation.RuleSet;
import nexcore.tool.mda.model.designer.transformation.SourceRelationType;
import nexcore.tool.mda.model.designer.transformation.SourceStructureTransformationData;
import nexcore.tool.mda.model.designer.transformation.SourceType;
import nexcore.tool.mda.model.designer.transformation.TargetRelationType;
import nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData;
import nexcore.tool.mda.model.designer.transformation.TransformationPackage;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides
 * an adapter <code>createXXX</code> method for each class of the model. <!--
 * end-user-doc -->
 * 
 * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage
 * @generated
 */
public class TransformationAdapterFactory extends AdapterFactoryImpl {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = "Copyright (c) 2010 SK C&C Co., Ltd. All rights reserved.\r\n\r\nThis software is the confidential and proprietary information of SK C&C.\r\nYou shall not disclose such confidential information and shall use it\r\nonly in accordance with the terms of the license agreement you entered into\r\nwith SK C&C.";

    /**
     * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected static TransformationPackage modelPackage;

    /**
     * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public TransformationAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = TransformationPackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc --> This implementation returns <code>true</code> if
     * the object is either the model's package or is an instance object of the
     * model. <!-- end-user-doc -->
     * 
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(Object object) {
        if (object == modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject) object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected TransformationSwitch<Adapter> modelSwitch = new TransformationSwitch<Adapter>() {
        @Override
        public Adapter caseRuleSet(RuleSet object) {
            return createRuleSetAdapter();
        }

        @Override
        public Adapter caseSourceStructureTransformationData(SourceStructureTransformationData object) {
            return createSourceStructureTransformationDataAdapter();
        }

        @Override
        public Adapter caseSourceType(SourceType object) {
            return createSourceTypeAdapter();
        }

        @Override
        public Adapter caseTargetStructureTransformationData(TargetStructureTransformationData object) {
            return createTargetStructureTransformationDataAdapter();
        }

        @Override
        public Adapter caseDataNameType(DataNameType object) {
            return createDataNameTypeAdapter();
        }

        @Override
        public Adapter caseLocationType(LocationType object) {
            return createLocationTypeAdapter();
        }

        @Override
        public Adapter caseLocationSegment(LocationSegment object) {
            return createLocationSegmentAdapter();
        }

        @Override
        public Adapter caseNameType(NameType object) {
            return createNameTypeAdapter();
        }

        @Override
        public Adapter caseNamePartType(NamePartType object) {
            return createNamePartTypeAdapter();
        }

        @Override
        public Adapter caseBehaviorTransformationData(BehaviorTransformationData object) {
            return createBehaviorTransformationDataAdapter();
        }

        @Override
        public Adapter caseSourceRelationType(SourceRelationType object) {
            return createSourceRelationTypeAdapter();
        }

        @Override
        public Adapter caseBehaviorTransformationDetailData(BehaviorTransformationDetailData object) {
            return createBehaviorTransformationDetailDataAdapter();
        }

        @Override
        public Adapter caseTargetRelationType(TargetRelationType object) {
            return createTargetRelationTypeAdapter();
        }

        @Override
        public Adapter caseOperationType(OperationType object) {
            return createOperationTypeAdapter();
        }

        @Override
        public Adapter caseParentType(ParentType object) {
            return createParentTypeAdapter();
        }

        @Override
        public Adapter defaultCase(EObject object) {
            return createEObjectAdapter();
        }
    };

    /**
     * Creates an adapter for the <code>target</code>. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param target
     *            the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject) target);
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link nexcore.tool.mda.model.designer.transformation.RuleSet
     * <em>Rule Set</em>}'. <!-- begin-user-doc --> This default implementation
     * returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     * 
     * @return the new adapter.
     * @see nexcore.tool.mda.model.designer.transformation.RuleSet
     * @generated
     */
    public Adapter createRuleSetAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link nexcore.tool.mda.model.designer.transformation.SourceStructureTransformationData
     * <em>Source Structure Transformation Data</em>}'. <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the
     * cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see nexcore.tool.mda.model.designer.transformation.SourceStructureTransformationData
     * @generated
     */
    public Adapter createSourceStructureTransformationDataAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link nexcore.tool.mda.model.designer.transformation.SourceType
     * <em>Source Type</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see nexcore.tool.mda.model.designer.transformation.SourceType
     * @generated
     */
    public Adapter createSourceTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData
     * <em>Target Structure Transformation Data</em>}'. <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the
     * cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData
     * @generated
     */
    public Adapter createTargetStructureTransformationDataAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link nexcore.tool.mda.model.designer.transformation.DataNameType
     * <em>Data Name Type</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see nexcore.tool.mda.model.designer.transformation.DataNameType
     * @generated
     */
    public Adapter createDataNameTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link nexcore.tool.mda.model.designer.transformation.LocationType
     * <em>Location Type</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see nexcore.tool.mda.model.designer.transformation.LocationType
     * @generated
     */
    public Adapter createLocationTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link nexcore.tool.mda.model.designer.transformation.LocationSegment
     * <em>Location Segment</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see nexcore.tool.mda.model.designer.transformation.LocationSegment
     * @generated
     */
    public Adapter createLocationSegmentAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link nexcore.tool.mda.model.designer.transformation.NameType
     * <em>Name Type</em>}'. <!-- begin-user-doc --> This default implementation
     * returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     * 
     * @return the new adapter.
     * @see nexcore.tool.mda.model.designer.transformation.NameType
     * @generated
     */
    public Adapter createNameTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link nexcore.tool.mda.model.designer.transformation.NamePartType
     * <em>Name Part Type</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see nexcore.tool.mda.model.designer.transformation.NamePartType
     * @generated
     */
    public Adapter createNamePartTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link nexcore.tool.mda.model.designer.transformation.BehaviorTransformationData
     * <em>Behavior Transformation Data</em>}'. <!-- begin-user-doc --> This
     * default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases
     * anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see nexcore.tool.mda.model.designer.transformation.BehaviorTransformationData
     * @generated
     */
    public Adapter createBehaviorTransformationDataAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link nexcore.tool.mda.model.designer.transformation.SourceRelationType
     * <em>Source Relation Type</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see nexcore.tool.mda.model.designer.transformation.SourceRelationType
     * @generated
     */
    public Adapter createSourceRelationTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link nexcore.tool.mda.model.designer.transformation.BehaviorTransformationDetailData
     * <em>Behavior Transformation Detail Data</em>}'. <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the
     * cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see nexcore.tool.mda.model.designer.transformation.BehaviorTransformationDetailData
     * @generated
     */
    public Adapter createBehaviorTransformationDetailDataAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link nexcore.tool.mda.model.designer.transformation.TargetRelationType
     * <em>Target Relation Type</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see nexcore.tool.mda.model.designer.transformation.TargetRelationType
     * @generated
     */
    public Adapter createTargetRelationTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link nexcore.tool.mda.model.designer.transformation.OperationType
     * <em>Operation Type</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see nexcore.tool.mda.model.designer.transformation.OperationType
     * @generated
     */
    public Adapter createOperationTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link nexcore.tool.mda.model.designer.transformation.ParentType
     * <em>Parent Type</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see nexcore.tool.mda.model.designer.transformation.ParentType
     * @generated
     */
    public Adapter createParentTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case. <!-- begin-user-doc --> This
     * default implementation returns null. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} // TransformationAdapterFactory
