/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.mda.model.designer.transformation.util;

import java.util.List;

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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance
 * hierarchy. It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the
 * inheritance hierarchy until a non-null result is returned, which is the
 * result of the switch. <!-- end-user-doc -->
 * 
 * @see nexcore.tool.mda.model.designer.transformation.TransformationPackage
 * @generated
 */
public class TransformationSwitch<T> {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = "";

    /**
     * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected static TransformationPackage modelPackage;

    /**
     * Creates an instance of the switch. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public TransformationSwitch() {
        if (modelPackage == null) {
            modelPackage = TransformationPackage.eINSTANCE;
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns
     * a non null result; it yields that result. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the first non-null result returned by a <code>caseXXX</code>
     *         call.
     * @generated
     */
    public T doSwitch(EObject theEObject) {
        return doSwitch(theEObject.eClass(), theEObject);
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns
     * a non null result; it yields that result. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the first non-null result returned by a <code>caseXXX</code>
     *         call.
     * @generated
     */
    protected T doSwitch(EClass theEClass, EObject theEObject) {
        if (theEClass.eContainer() == modelPackage) {
            return doSwitch(theEClass.getClassifierID(), theEObject);
        } else {
            List<EClass> eSuperTypes = theEClass.getESuperTypes();
            return eSuperTypes.isEmpty() ? defaultCase(theEObject) : doSwitch(eSuperTypes.get(0), theEObject);
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns
     * a non null result; it yields that result. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the first non-null result returned by a <code>caseXXX</code>
     *         call.
     * @generated
     */
    protected T doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
            case TransformationPackage.RULE_SET: {
                RuleSet ruleSet = (RuleSet) theEObject;
                T result = caseRuleSet(ruleSet);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case TransformationPackage.SOURCE_STRUCTURE_TRANSFORMATION_DATA: {
                SourceStructureTransformationData sourceStructureTransformationData = (SourceStructureTransformationData) theEObject;
                T result = caseSourceStructureTransformationData(sourceStructureTransformationData);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case TransformationPackage.SOURCE_TYPE: {
                SourceType sourceType = (SourceType) theEObject;
                T result = caseSourceType(sourceType);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case TransformationPackage.TARGET_STRUCTURE_TRANSFORMATION_DATA: {
                TargetStructureTransformationData targetStructureTransformationData = (TargetStructureTransformationData) theEObject;
                T result = caseTargetStructureTransformationData(targetStructureTransformationData);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case TransformationPackage.DATA_NAME_TYPE: {
                DataNameType dataNameType = (DataNameType) theEObject;
                T result = caseDataNameType(dataNameType);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case TransformationPackage.LOCATION_TYPE: {
                LocationType locationType = (LocationType) theEObject;
                T result = caseLocationType(locationType);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case TransformationPackage.LOCATION_SEGMENT: {
                LocationSegment locationSegment = (LocationSegment) theEObject;
                T result = caseLocationSegment(locationSegment);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case TransformationPackage.NAME_TYPE: {
                NameType nameType = (NameType) theEObject;
                T result = caseNameType(nameType);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case TransformationPackage.NAME_PART_TYPE: {
                NamePartType namePartType = (NamePartType) theEObject;
                T result = caseNamePartType(namePartType);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case TransformationPackage.BEHAVIOR_TRANSFORMATION_DATA: {
                BehaviorTransformationData behaviorTransformationData = (BehaviorTransformationData) theEObject;
                T result = caseBehaviorTransformationData(behaviorTransformationData);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case TransformationPackage.SOURCE_RELATION_TYPE: {
                SourceRelationType sourceRelationType = (SourceRelationType) theEObject;
                T result = caseSourceRelationType(sourceRelationType);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case TransformationPackage.BEHAVIOR_TRANSFORMATION_DETAIL_DATA: {
                BehaviorTransformationDetailData behaviorTransformationDetailData = (BehaviorTransformationDetailData) theEObject;
                T result = caseBehaviorTransformationDetailData(behaviorTransformationDetailData);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case TransformationPackage.TARGET_RELATION_TYPE: {
                TargetRelationType targetRelationType = (TargetRelationType) theEObject;
                T result = caseTargetRelationType(targetRelationType);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case TransformationPackage.OPERATION_TYPE: {
                OperationType operationType = (OperationType) theEObject;
                T result = caseOperationType(operationType);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case TransformationPackage.PARENT_TYPE: {
                ParentType parentType = (ParentType) theEObject;
                T result = caseParentType(parentType);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            default:
                return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Rule Set</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Rule Set</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRuleSet(RuleSet object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Source Structure Transformation Data</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Source Structure Transformation Data</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSourceStructureTransformationData(SourceStructureTransformationData object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Source Type</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Source Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSourceType(SourceType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Target Structure Transformation Data</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Target Structure Transformation Data</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTargetStructureTransformationData(TargetStructureTransformationData object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Data Name Type</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Data Name Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDataNameType(DataNameType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Location Type</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Location Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLocationType(LocationType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Location Segment</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Location Segment</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLocationSegment(LocationSegment object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Name Type</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Name Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNameType(NameType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Name Part Type</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Name Part Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNamePartType(NamePartType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Behavior Transformation Data</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Behavior Transformation Data</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBehaviorTransformationData(BehaviorTransformationData object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Source Relation Type</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Source Relation Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSourceRelationType(SourceRelationType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Behavior Transformation Detail Data</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Behavior Transformation Detail Data</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBehaviorTransformationDetailData(BehaviorTransformationDetailData object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Target Relation Type</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Target Relation Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTargetRelationType(TargetRelationType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Operation Type</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Operation Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseOperationType(OperationType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Parent Type</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Parent Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseParentType(ParentType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>EObject</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch, but this is
     * the last case anyway. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    public T defaultCase(EObject object) {
        return null;
    }

} // TransformationSwitch
