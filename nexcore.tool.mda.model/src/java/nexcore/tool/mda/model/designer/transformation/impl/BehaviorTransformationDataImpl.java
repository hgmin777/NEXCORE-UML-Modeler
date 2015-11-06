/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.mda.model.designer.transformation.impl;

import java.util.Collection;

import nexcore.tool.mda.model.designer.transformation.BehaviorTransformationData;
import nexcore.tool.mda.model.designer.transformation.BehaviorTransformationDetailData;
import nexcore.tool.mda.model.designer.transformation.SourceRelationType;
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
 * <em><b>Behavior Transformation Data</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.impl.BehaviorTransformationDataImpl#getSourceRelation
 * <em>Source Relation</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.impl.BehaviorTransformationDataImpl#getRemark
 * <em>Remark</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.impl.BehaviorTransformationDataImpl#isSelf
 * <em>Self</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.impl.BehaviorTransformationDataImpl#getBehaviorTransformationDetailDataSet
 * <em>Behavior Transformation Detail Data Set</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class BehaviorTransformationDataImpl extends EObjectImpl implements BehaviorTransformationData {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = "";

    /**
     * The cached value of the '{@link #getSourceRelation()
     * <em>Source Relation</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getSourceRelation()
     * @generated
     * @ordered
     */
    protected SourceRelationType sourceRelation;

    /**
     * The default value of the '{@link #getRemark() <em>Remark</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getRemark()
     * @generated
     * @ordered
     */
    protected static final String REMARK_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getRemark() <em>Remark</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getRemark()
     * @generated
     * @ordered
     */
    protected String remark = REMARK_EDEFAULT;

    /**
     * The default value of the '{@link #isSelf() <em>Self</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isSelf()
     * @generated
     * @ordered
     */
    protected static final boolean SELF_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isSelf() <em>Self</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isSelf()
     * @generated
     * @ordered
     */
    protected boolean self = SELF_EDEFAULT;

    /**
     * The cached value of the '
     * {@link #getBehaviorTransformationDetailDataSet()
     * <em>Behavior Transformation Detail Data Set</em>}' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getBehaviorTransformationDetailDataSet()
     * @generated
     * @ordered
     */
    protected EList<BehaviorTransformationDetailData> behaviorTransformationDetailDataSet;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected BehaviorTransformationDataImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TransformationPackage.Literals.BEHAVIOR_TRANSFORMATION_DATA;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public SourceRelationType getSourceRelation() {
        return sourceRelation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetSourceRelation(SourceRelationType newSourceRelation, NotificationChain msgs) {
        SourceRelationType oldSourceRelation = sourceRelation;
        sourceRelation = newSourceRelation;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.BEHAVIOR_TRANSFORMATION_DATA__SOURCE_RELATION,
                oldSourceRelation,
                newSourceRelation);
            if (msgs == null)
                msgs = notification;
            else
                msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setSourceRelation(SourceRelationType newSourceRelation) {
        if (newSourceRelation != sourceRelation) {
            NotificationChain msgs = null;
            if (sourceRelation != null)
                msgs = ((InternalEObject) sourceRelation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
                    - TransformationPackage.BEHAVIOR_TRANSFORMATION_DATA__SOURCE_RELATION, null, msgs);
            if (newSourceRelation != null)
                msgs = ((InternalEObject) newSourceRelation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
                    - TransformationPackage.BEHAVIOR_TRANSFORMATION_DATA__SOURCE_RELATION, null, msgs);
            msgs = basicSetSourceRelation(newSourceRelation, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.BEHAVIOR_TRANSFORMATION_DATA__SOURCE_RELATION,
                newSourceRelation,
                newSourceRelation));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setRemark(String newRemark) {
        String oldRemark = remark;
        remark = newRemark;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.BEHAVIOR_TRANSFORMATION_DATA__REMARK,
                oldRemark,
                remark));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public boolean isSelf() {
        return self;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setSelf(boolean newSelf) {
        boolean oldSelf = self;
        self = newSelf;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.BEHAVIOR_TRANSFORMATION_DATA__SELF,
                oldSelf,
                self));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<BehaviorTransformationDetailData> getBehaviorTransformationDetailDataSet() {
        if (behaviorTransformationDetailDataSet == null) {
            behaviorTransformationDetailDataSet = new EObjectContainmentEList<BehaviorTransformationDetailData>(BehaviorTransformationDetailData.class,
                this,
                TransformationPackage.BEHAVIOR_TRANSFORMATION_DATA__BEHAVIOR_TRANSFORMATION_DETAIL_DATA_SET);
        }
        return behaviorTransformationDetailDataSet;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case TransformationPackage.BEHAVIOR_TRANSFORMATION_DATA__SOURCE_RELATION:
                return basicSetSourceRelation(null, msgs);
            case TransformationPackage.BEHAVIOR_TRANSFORMATION_DATA__BEHAVIOR_TRANSFORMATION_DETAIL_DATA_SET:
                return ((InternalEList<?>) getBehaviorTransformationDetailDataSet()).basicRemove(otherEnd, msgs);
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
            case TransformationPackage.BEHAVIOR_TRANSFORMATION_DATA__SOURCE_RELATION:
                return getSourceRelation();
            case TransformationPackage.BEHAVIOR_TRANSFORMATION_DATA__REMARK:
                return getRemark();
            case TransformationPackage.BEHAVIOR_TRANSFORMATION_DATA__SELF:
                return isSelf() ? Boolean.TRUE : Boolean.FALSE;
            case TransformationPackage.BEHAVIOR_TRANSFORMATION_DATA__BEHAVIOR_TRANSFORMATION_DETAIL_DATA_SET:
                return getBehaviorTransformationDetailDataSet();
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
            case TransformationPackage.BEHAVIOR_TRANSFORMATION_DATA__SOURCE_RELATION:
                setSourceRelation((SourceRelationType) newValue);
                return;
            case TransformationPackage.BEHAVIOR_TRANSFORMATION_DATA__REMARK:
                setRemark((String) newValue);
                return;
            case TransformationPackage.BEHAVIOR_TRANSFORMATION_DATA__SELF:
                setSelf(((Boolean) newValue).booleanValue());
                return;
            case TransformationPackage.BEHAVIOR_TRANSFORMATION_DATA__BEHAVIOR_TRANSFORMATION_DETAIL_DATA_SET:
                getBehaviorTransformationDetailDataSet().clear();
                getBehaviorTransformationDetailDataSet().addAll((Collection<? extends BehaviorTransformationDetailData>) newValue);
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
            case TransformationPackage.BEHAVIOR_TRANSFORMATION_DATA__SOURCE_RELATION:
                setSourceRelation((SourceRelationType) null);
                return;
            case TransformationPackage.BEHAVIOR_TRANSFORMATION_DATA__REMARK:
                setRemark(REMARK_EDEFAULT);
                return;
            case TransformationPackage.BEHAVIOR_TRANSFORMATION_DATA__SELF:
                setSelf(SELF_EDEFAULT);
                return;
            case TransformationPackage.BEHAVIOR_TRANSFORMATION_DATA__BEHAVIOR_TRANSFORMATION_DETAIL_DATA_SET:
                getBehaviorTransformationDetailDataSet().clear();
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
            case TransformationPackage.BEHAVIOR_TRANSFORMATION_DATA__SOURCE_RELATION:
                return sourceRelation != null;
            case TransformationPackage.BEHAVIOR_TRANSFORMATION_DATA__REMARK:
                return REMARK_EDEFAULT == null ? remark != null : !REMARK_EDEFAULT.equals(remark);
            case TransformationPackage.BEHAVIOR_TRANSFORMATION_DATA__SELF:
                return self != SELF_EDEFAULT;
            case TransformationPackage.BEHAVIOR_TRANSFORMATION_DATA__BEHAVIOR_TRANSFORMATION_DETAIL_DATA_SET:
                return behaviorTransformationDetailDataSet != null && !behaviorTransformationDetailDataSet.isEmpty();
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
        result.append(" (remark: ");
        result.append(remark);
        result.append(", self: ");
        result.append(self);
        result.append(')');
        return result.toString();
    }

} // BehaviorTransformationDataImpl
