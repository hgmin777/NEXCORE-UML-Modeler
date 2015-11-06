/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.mda.model.designer.transformation.impl;

import nexcore.tool.mda.model.designer.transformation.DataNameType;
import nexcore.tool.mda.model.designer.transformation.TargetRelationType;
import nexcore.tool.mda.model.designer.transformation.TransformationPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Target Relation Type</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.impl.TargetRelationTypeImpl#getSource
 * <em>Source</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.impl.TargetRelationTypeImpl#getTarget
 * <em>Target</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class TargetRelationTypeImpl extends EObjectImpl implements TargetRelationType {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = "";

    /**
     * The cached value of the '{@link #getSource() <em>Source</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getSource()
     * @generated
     * @ordered
     */
    protected DataNameType source;

    /**
     * The cached value of the '{@link #getTarget() <em>Target</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getTarget()
     * @generated
     * @ordered
     */
    protected DataNameType target;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected TargetRelationTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TransformationPackage.Literals.TARGET_RELATION_TYPE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public DataNameType getSource() {
        return source;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetSource(DataNameType newSource, NotificationChain msgs) {
        DataNameType oldSource = source;
        source = newSource;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.TARGET_RELATION_TYPE__SOURCE,
                oldSource,
                newSource);
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
    public void setSource(DataNameType newSource) {
        if (newSource != source) {
            NotificationChain msgs = null;
            if (source != null)
                msgs = ((InternalEObject) source).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
                    - TransformationPackage.TARGET_RELATION_TYPE__SOURCE, null, msgs);
            if (newSource != null)
                msgs = ((InternalEObject) newSource).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
                    - TransformationPackage.TARGET_RELATION_TYPE__SOURCE, null, msgs);
            msgs = basicSetSource(newSource, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.TARGET_RELATION_TYPE__SOURCE,
                newSource,
                newSource));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public DataNameType getTarget() {
        return target;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetTarget(DataNameType newTarget, NotificationChain msgs) {
        DataNameType oldTarget = target;
        target = newTarget;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.TARGET_RELATION_TYPE__TARGET,
                oldTarget,
                newTarget);
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
    public void setTarget(DataNameType newTarget) {
        if (newTarget != target) {
            NotificationChain msgs = null;
            if (target != null)
                msgs = ((InternalEObject) target).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
                    - TransformationPackage.TARGET_RELATION_TYPE__TARGET, null, msgs);
            if (newTarget != null)
                msgs = ((InternalEObject) newTarget).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
                    - TransformationPackage.TARGET_RELATION_TYPE__TARGET, null, msgs);
            msgs = basicSetTarget(newTarget, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.TARGET_RELATION_TYPE__TARGET,
                newTarget,
                newTarget));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String toString() {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case TransformationPackage.TARGET_RELATION_TYPE__SOURCE:
                return basicSetSource(null, msgs);
            case TransformationPackage.TARGET_RELATION_TYPE__TARGET:
                return basicSetTarget(null, msgs);
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
            case TransformationPackage.TARGET_RELATION_TYPE__SOURCE:
                return getSource();
            case TransformationPackage.TARGET_RELATION_TYPE__TARGET:
                return getTarget();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case TransformationPackage.TARGET_RELATION_TYPE__SOURCE:
                setSource((DataNameType) newValue);
                return;
            case TransformationPackage.TARGET_RELATION_TYPE__TARGET:
                setTarget((DataNameType) newValue);
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
            case TransformationPackage.TARGET_RELATION_TYPE__SOURCE:
                setSource((DataNameType) null);
                return;
            case TransformationPackage.TARGET_RELATION_TYPE__TARGET:
                setTarget((DataNameType) null);
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
            case TransformationPackage.TARGET_RELATION_TYPE__SOURCE:
                return source != null;
            case TransformationPackage.TARGET_RELATION_TYPE__TARGET:
                return target != null;
        }
        return super.eIsSet(featureID);
    }

} // TargetRelationTypeImpl
