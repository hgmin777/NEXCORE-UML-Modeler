/*
 * Copyright (c) 2010 SK C&C Co., Ltd. All rights reserved.
 * 
 * This software is the confidential and proprietary information of SK C&C. You
 * shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK
 * C&C.
 */

package nexcore.tool.mda.model.designer.transformation.impl;

import java.util.Collection;

import nexcore.tool.mda.model.common.IRuleSet;
import nexcore.tool.mda.model.designer.transformation.BehaviorTransformationData;
import nexcore.tool.mda.model.designer.transformation.RuleSet;
import nexcore.tool.mda.model.designer.transformation.SourceStructureTransformationData;
import nexcore.tool.mda.model.designer.transformation.TransformationPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Rule Set</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.impl.RuleSetImpl#getStructureRules
 * <em>Structure Rules</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.impl.RuleSetImpl#getBehaviorRules
 * <em>Behavior Rules</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.impl.RuleSetImpl#getSourceModelLocation
 * <em>Source Model Location</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.impl.RuleSetImpl#getSourceProfileLocation
 * <em>Source Profile Location</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.impl.RuleSetImpl#getTargetModelLocation
 * <em>Target Model Location</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.impl.RuleSetImpl#getTargetProfileLocation
 * <em>Target Profile Location</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated NOT
 */
public class RuleSetImpl extends EObjectImpl implements RuleSet, IRuleSet {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = "Copyright (c) 2010 SK C&C Co., Ltd. All rights reserved.\r\n\r\nThis software is the confidential and proprietary information of SK C&C.\r\nYou shall not disclose such confidential information and shall use it\r\nonly in accordance with the terms of the license agreement you entered into\r\nwith SK C&C.";

    /**
     * The cached value of the '{@link #getStructureRules()
     * <em>Structure Rules</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getStructureRules()
     * @generated
     * @ordered
     */
    protected EList<SourceStructureTransformationData> structureRules;

    /**
     * The cached value of the '{@link #getBehaviorRules()
     * <em>Behavior Rules</em>}' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getBehaviorRules()
     * @generated
     * @ordered
     */
    protected EList<BehaviorTransformationData> behaviorRules;

    /**
     * The default value of the '{@link #getSourceModelLocation()
     * <em>Source Model Location</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getSourceModelLocation()
     * @generated
     * @ordered
     */
    protected static final String SOURCE_MODEL_LOCATION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSourceModelLocation()
     * <em>Source Model Location</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getSourceModelLocation()
     * @generated
     * @ordered
     */
    protected String sourceModelLocation = SOURCE_MODEL_LOCATION_EDEFAULT;

    /**
     * The default value of the '{@link #getSourceProfileLocation()
     * <em>Source Profile Location</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getSourceProfileLocation()
     * @generated
     * @ordered
     */
    protected static final String SOURCE_PROFILE_LOCATION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSourceProfileLocation()
     * <em>Source Profile Location</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getSourceProfileLocation()
     * @generated
     * @ordered
     */
    protected String sourceProfileLocation = SOURCE_PROFILE_LOCATION_EDEFAULT;

    /**
     * The default value of the '{@link #getTargetModelLocation()
     * <em>Target Model Location</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getTargetModelLocation()
     * @generated
     * @ordered
     */
    protected static final String TARGET_MODEL_LOCATION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTargetModelLocation()
     * <em>Target Model Location</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getTargetModelLocation()
     * @generated
     * @ordered
     */
    protected String targetModelLocation = TARGET_MODEL_LOCATION_EDEFAULT;

    /**
     * The default value of the '{@link #getTargetProfileLocation()
     * <em>Target Profile Location</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getTargetProfileLocation()
     * @generated
     * @ordered
     */
    protected static final String TARGET_PROFILE_LOCATION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTargetProfileLocation()
     * <em>Target Profile Location</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getTargetProfileLocation()
     * @generated
     * @ordered
     */
    protected String targetProfileLocation = TARGET_PROFILE_LOCATION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected RuleSetImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TransformationPackage.Literals.RULE_SET;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<SourceStructureTransformationData> getStructureRules() {
        if (structureRules == null) {
            structureRules = new EObjectContainmentEList<SourceStructureTransformationData>(SourceStructureTransformationData.class,
                this,
                TransformationPackage.RULE_SET__STRUCTURE_RULES);
        }
        return structureRules;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<BehaviorTransformationData> getBehaviorRules() {
        if (behaviorRules == null) {
            behaviorRules = new EObjectContainmentEList<BehaviorTransformationData>(BehaviorTransformationData.class,
                this,
                TransformationPackage.RULE_SET__BEHAVIOR_RULES);
        }
        return behaviorRules;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getSourceModelLocation() {
        return sourceModelLocation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setSourceModelLocation(String newSourceModelLocation) {
        String oldSourceModelLocation = sourceModelLocation;
        sourceModelLocation = newSourceModelLocation;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.RULE_SET__SOURCE_MODEL_LOCATION,
                oldSourceModelLocation,
                sourceModelLocation));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getSourceProfileLocation() {
        return sourceProfileLocation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setSourceProfileLocation(String newSourceProfileLocation) {
        String oldSourceProfileLocation = sourceProfileLocation;
        sourceProfileLocation = newSourceProfileLocation;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.RULE_SET__SOURCE_PROFILE_LOCATION,
                oldSourceProfileLocation,
                sourceProfileLocation));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getTargetModelLocation() {
        return targetModelLocation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setTargetModelLocation(String newTargetModelLocation) {
        String oldTargetModelLocation = targetModelLocation;
        targetModelLocation = newTargetModelLocation;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.RULE_SET__TARGET_MODEL_LOCATION,
                oldTargetModelLocation,
                targetModelLocation));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getTargetProfileLocation() {
        return targetProfileLocation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setTargetProfileLocation(String newTargetProfileLocation) {
        String oldTargetProfileLocation = targetProfileLocation;
        targetProfileLocation = newTargetProfileLocation;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.RULE_SET__TARGET_PROFILE_LOCATION,
                oldTargetProfileLocation,
                targetProfileLocation));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case TransformationPackage.RULE_SET__STRUCTURE_RULES:
                return ((InternalEList<?>) getStructureRules()).basicRemove(otherEnd, msgs);
            case TransformationPackage.RULE_SET__BEHAVIOR_RULES:
                return ((InternalEList<?>) getBehaviorRules()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case TransformationPackage.RULE_SET__STRUCTURE_RULES:
                return getStructureRules();
            case TransformationPackage.RULE_SET__BEHAVIOR_RULES:
                return getBehaviorRules();
            case TransformationPackage.RULE_SET__SOURCE_MODEL_LOCATION:
                return getSourceModelLocation();
            case TransformationPackage.RULE_SET__SOURCE_PROFILE_LOCATION:
                return getSourceProfileLocation();
            case TransformationPackage.RULE_SET__TARGET_MODEL_LOCATION:
                return getTargetModelLocation();
            case TransformationPackage.RULE_SET__TARGET_PROFILE_LOCATION:
                return getTargetProfileLocation();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case TransformationPackage.RULE_SET__STRUCTURE_RULES:
                getStructureRules().clear();
                getStructureRules().addAll((Collection<? extends SourceStructureTransformationData>) newValue);
                return;
            case TransformationPackage.RULE_SET__BEHAVIOR_RULES:
                getBehaviorRules().clear();
                getBehaviorRules().addAll((Collection<? extends BehaviorTransformationData>) newValue);
                return;
            case TransformationPackage.RULE_SET__SOURCE_MODEL_LOCATION:
                setSourceModelLocation((String) newValue);
                return;
            case TransformationPackage.RULE_SET__SOURCE_PROFILE_LOCATION:
                setSourceProfileLocation((String) newValue);
                return;
            case TransformationPackage.RULE_SET__TARGET_MODEL_LOCATION:
                setTargetModelLocation((String) newValue);
                return;
            case TransformationPackage.RULE_SET__TARGET_PROFILE_LOCATION:
                setTargetProfileLocation((String) newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case TransformationPackage.RULE_SET__STRUCTURE_RULES:
                getStructureRules().clear();
                return;
            case TransformationPackage.RULE_SET__BEHAVIOR_RULES:
                getBehaviorRules().clear();
                return;
            case TransformationPackage.RULE_SET__SOURCE_MODEL_LOCATION:
                setSourceModelLocation(SOURCE_MODEL_LOCATION_EDEFAULT);
                return;
            case TransformationPackage.RULE_SET__SOURCE_PROFILE_LOCATION:
                setSourceProfileLocation(SOURCE_PROFILE_LOCATION_EDEFAULT);
                return;
            case TransformationPackage.RULE_SET__TARGET_MODEL_LOCATION:
                setTargetModelLocation(TARGET_MODEL_LOCATION_EDEFAULT);
                return;
            case TransformationPackage.RULE_SET__TARGET_PROFILE_LOCATION:
                setTargetProfileLocation(TARGET_PROFILE_LOCATION_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case TransformationPackage.RULE_SET__STRUCTURE_RULES:
                return structureRules != null && !structureRules.isEmpty();
            case TransformationPackage.RULE_SET__BEHAVIOR_RULES:
                return behaviorRules != null && !behaviorRules.isEmpty();
            case TransformationPackage.RULE_SET__SOURCE_MODEL_LOCATION:
                return SOURCE_MODEL_LOCATION_EDEFAULT == null ? sourceModelLocation != null
                : !SOURCE_MODEL_LOCATION_EDEFAULT.equals(sourceModelLocation);
            case TransformationPackage.RULE_SET__SOURCE_PROFILE_LOCATION:
                return SOURCE_PROFILE_LOCATION_EDEFAULT == null ? sourceProfileLocation != null
                : !SOURCE_PROFILE_LOCATION_EDEFAULT.equals(sourceProfileLocation);
            case TransformationPackage.RULE_SET__TARGET_MODEL_LOCATION:
                return TARGET_MODEL_LOCATION_EDEFAULT == null ? targetModelLocation != null
                : !TARGET_MODEL_LOCATION_EDEFAULT.equals(targetModelLocation);
            case TransformationPackage.RULE_SET__TARGET_PROFILE_LOCATION:
                return TARGET_PROFILE_LOCATION_EDEFAULT == null ? targetProfileLocation != null
                : !TARGET_PROFILE_LOCATION_EDEFAULT.equals(targetProfileLocation);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy())
            return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (sourceModelLocation: ");
        result.append(sourceModelLocation);
        result.append(", sourceProfileLocation: ");
        result.append(sourceProfileLocation);
        result.append(", targetModelLocation: ");
        result.append(targetModelLocation);
        result.append(", targetProfileLocation: ");
        result.append(targetProfileLocation);
        result.append(')');
        return result.toString();
    }

    /** 검증 여부 플래그 */
    boolean validated = false;

    /**
     * @see nexcore.tool.mda.core.infrastructure.model.common.IRuleSet#isValidated()
     */
    public boolean isValidated() {
        return validated;
    }

    /**
     * @see nexcore.tool.mda.core.infrastructure.model.common.IRuleSet#setValidated(boolean)
     */
    public void setValidated(boolean validated) {
        this.validated = validated;
    }

} // RuleSetImpl
