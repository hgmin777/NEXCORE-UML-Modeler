/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.mda.model.designer.transformation.impl;

import java.util.Collection;

import nexcore.tool.mda.model.designer.transformation.LocationType;
import nexcore.tool.mda.model.designer.transformation.SourceStructureTransformationData;
import nexcore.tool.mda.model.designer.transformation.SourceType;
import nexcore.tool.mda.model.designer.transformation.TargetStructureTransformationData;
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
 * <em><b>Source Structure Transformation Data</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.impl.SourceStructureTransformationDataImpl#getSourceType
 * <em>Source Type</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.impl.SourceStructureTransformationDataImpl#getSourceLocation
 * <em>Source Location</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.impl.SourceStructureTransformationDataImpl#getTargetStructureTransformationDataSet
 * <em>Target Structure Transformation Data Set</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class SourceStructureTransformationDataImpl extends EObjectImpl implements SourceStructureTransformationData {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = "";

    /**
     * The cached value of the '{@link #getSourceType() <em>Source Type</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getSourceType()
     * @generated
     * @ordered
     */
    protected SourceType sourceType;

    /**
     * The cached value of the '{@link #getSourceLocation()
     * <em>Source Location</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getSourceLocation()
     * @generated
     * @ordered
     */
    protected LocationType sourceLocation;

    /**
     * The cached value of the '
     * {@link #getTargetStructureTransformationDataSet()
     * <em>Target Structure Transformation Data Set</em>}' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getTargetStructureTransformationDataSet()
     * @generated
     * @ordered
     */
    protected EList<TargetStructureTransformationData> targetStructureTransformationDataSet;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected SourceStructureTransformationDataImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TransformationPackage.Literals.SOURCE_STRUCTURE_TRANSFORMATION_DATA;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public SourceType getSourceType() {
        return sourceType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetSourceType(SourceType newSourceType, NotificationChain msgs) {
        SourceType oldSourceType = sourceType;
        sourceType = newSourceType;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.SOURCE_STRUCTURE_TRANSFORMATION_DATA__SOURCE_TYPE,
                oldSourceType,
                newSourceType);
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
    public void setSourceType(SourceType newSourceType) {
        if (newSourceType != sourceType) {
            NotificationChain msgs = null;
            if (sourceType != null)
                msgs = ((InternalEObject) sourceType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
                    - TransformationPackage.SOURCE_STRUCTURE_TRANSFORMATION_DATA__SOURCE_TYPE, null, msgs);
            if (newSourceType != null)
                msgs = ((InternalEObject) newSourceType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
                    - TransformationPackage.SOURCE_STRUCTURE_TRANSFORMATION_DATA__SOURCE_TYPE, null, msgs);
            msgs = basicSetSourceType(newSourceType, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.SOURCE_STRUCTURE_TRANSFORMATION_DATA__SOURCE_TYPE,
                newSourceType,
                newSourceType));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public LocationType getSourceLocation() {
        return sourceLocation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetSourceLocation(LocationType newSourceLocation, NotificationChain msgs) {
        LocationType oldSourceLocation = sourceLocation;
        sourceLocation = newSourceLocation;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.SOURCE_STRUCTURE_TRANSFORMATION_DATA__SOURCE_LOCATION,
                oldSourceLocation,
                newSourceLocation);
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
    public void setSourceLocation(LocationType newSourceLocation) {
        if (newSourceLocation != sourceLocation) {
            NotificationChain msgs = null;
            if (sourceLocation != null)
                msgs = ((InternalEObject) sourceLocation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
                    - TransformationPackage.SOURCE_STRUCTURE_TRANSFORMATION_DATA__SOURCE_LOCATION, null, msgs);
            if (newSourceLocation != null)
                msgs = ((InternalEObject) newSourceLocation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
                    - TransformationPackage.SOURCE_STRUCTURE_TRANSFORMATION_DATA__SOURCE_LOCATION, null, msgs);
            msgs = basicSetSourceLocation(newSourceLocation, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.SOURCE_STRUCTURE_TRANSFORMATION_DATA__SOURCE_LOCATION,
                newSourceLocation,
                newSourceLocation));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<TargetStructureTransformationData> getTargetStructureTransformationDataSet() {
        if (targetStructureTransformationDataSet == null) {
            targetStructureTransformationDataSet = new EObjectContainmentEList<TargetStructureTransformationData>(TargetStructureTransformationData.class,
                this,
                TransformationPackage.SOURCE_STRUCTURE_TRANSFORMATION_DATA__TARGET_STRUCTURE_TRANSFORMATION_DATA_SET);
        }
        return targetStructureTransformationDataSet;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case TransformationPackage.SOURCE_STRUCTURE_TRANSFORMATION_DATA__SOURCE_TYPE:
                return basicSetSourceType(null, msgs);
            case TransformationPackage.SOURCE_STRUCTURE_TRANSFORMATION_DATA__SOURCE_LOCATION:
                return basicSetSourceLocation(null, msgs);
            case TransformationPackage.SOURCE_STRUCTURE_TRANSFORMATION_DATA__TARGET_STRUCTURE_TRANSFORMATION_DATA_SET:
                return ((InternalEList<?>) getTargetStructureTransformationDataSet()).basicRemove(otherEnd, msgs);
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
            case TransformationPackage.SOURCE_STRUCTURE_TRANSFORMATION_DATA__SOURCE_TYPE:
                return getSourceType();
            case TransformationPackage.SOURCE_STRUCTURE_TRANSFORMATION_DATA__SOURCE_LOCATION:
                return getSourceLocation();
            case TransformationPackage.SOURCE_STRUCTURE_TRANSFORMATION_DATA__TARGET_STRUCTURE_TRANSFORMATION_DATA_SET:
                return getTargetStructureTransformationDataSet();
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
            case TransformationPackage.SOURCE_STRUCTURE_TRANSFORMATION_DATA__SOURCE_TYPE:
                setSourceType((SourceType) newValue);
                return;
            case TransformationPackage.SOURCE_STRUCTURE_TRANSFORMATION_DATA__SOURCE_LOCATION:
                setSourceLocation((LocationType) newValue);
                return;
            case TransformationPackage.SOURCE_STRUCTURE_TRANSFORMATION_DATA__TARGET_STRUCTURE_TRANSFORMATION_DATA_SET:
                getTargetStructureTransformationDataSet().clear();
                getTargetStructureTransformationDataSet().addAll((Collection<? extends TargetStructureTransformationData>) newValue);
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
            case TransformationPackage.SOURCE_STRUCTURE_TRANSFORMATION_DATA__SOURCE_TYPE:
                setSourceType((SourceType) null);
                return;
            case TransformationPackage.SOURCE_STRUCTURE_TRANSFORMATION_DATA__SOURCE_LOCATION:
                setSourceLocation((LocationType) null);
                return;
            case TransformationPackage.SOURCE_STRUCTURE_TRANSFORMATION_DATA__TARGET_STRUCTURE_TRANSFORMATION_DATA_SET:
                getTargetStructureTransformationDataSet().clear();
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
            case TransformationPackage.SOURCE_STRUCTURE_TRANSFORMATION_DATA__SOURCE_TYPE:
                return sourceType != null;
            case TransformationPackage.SOURCE_STRUCTURE_TRANSFORMATION_DATA__SOURCE_LOCATION:
                return sourceLocation != null;
            case TransformationPackage.SOURCE_STRUCTURE_TRANSFORMATION_DATA__TARGET_STRUCTURE_TRANSFORMATION_DATA_SET:
                return targetStructureTransformationDataSet != null && !targetStructureTransformationDataSet.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // SourceStructureTransformationDataImpl
