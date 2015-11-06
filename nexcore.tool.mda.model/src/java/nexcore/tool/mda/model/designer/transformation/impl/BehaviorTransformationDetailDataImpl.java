/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.mda.model.designer.transformation.impl;

import nexcore.tool.mda.model.designer.transformation.BehaviorTransformationDetailData;
import nexcore.tool.mda.model.designer.transformation.OperationType;
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
 * <em><b>Behavior Transformation Detail Data</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.impl.BehaviorTransformationDetailDataImpl#getSequence
 * <em>Sequence</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.impl.BehaviorTransformationDetailDataImpl#getTargetRelation
 * <em>Target Relation</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.impl.BehaviorTransformationDetailDataImpl#getOperation
 * <em>Operation</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.impl.BehaviorTransformationDetailDataImpl#getOperationApplicableStereotype
 * <em>Operation Applicable Stereotype</em>}</li>
 * <li>
 * {@link nexcore.tool.mda.model.designer.transformation.impl.BehaviorTransformationDetailDataImpl#getMessage
 * <em>Message</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class BehaviorTransformationDetailDataImpl extends EObjectImpl implements BehaviorTransformationDetailData {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = "";

    /**
     * The default value of the '{@link #getSequence() <em>Sequence</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getSequence()
     * @generated
     * @ordered
     */
    protected static final int SEQUENCE_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getSequence() <em>Sequence</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getSequence()
     * @generated
     * @ordered
     */
    protected int sequence = SEQUENCE_EDEFAULT;

    /**
     * The cached value of the '{@link #getTargetRelation()
     * <em>Target Relation</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getTargetRelation()
     * @generated
     * @ordered
     */
    protected TargetRelationType targetRelation;

    /**
     * The cached value of the '{@link #getOperation() <em>Operation</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getOperation()
     * @generated
     * @ordered
     */
    protected OperationType operation;

    /**
     * The default value of the '{@link #getOperationApplicableStereotype()
     * <em>Operation Applicable Stereotype</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getOperationApplicableStereotype()
     * @generated
     * @ordered
     */
    protected static final String OPERATION_APPLICABLE_STEREOTYPE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getOperationApplicableStereotype()
     * <em>Operation Applicable Stereotype</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getOperationApplicableStereotype()
     * @generated
     * @ordered
     */
    protected String operationApplicableStereotype = OPERATION_APPLICABLE_STEREOTYPE_EDEFAULT;

    /**
     * The default value of the '{@link #getMessage() <em>Message</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getMessage()
     * @generated
     * @ordered
     */
    protected static final String MESSAGE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getMessage() <em>Message</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getMessage()
     * @generated
     * @ordered
     */
    protected String message = MESSAGE_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected BehaviorTransformationDetailDataImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TransformationPackage.Literals.BEHAVIOR_TRANSFORMATION_DETAIL_DATA;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public int getSequence() {
        return sequence;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setSequence(int newSequence) {
        int oldSequence = sequence;
        sequence = newSequence;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.BEHAVIOR_TRANSFORMATION_DETAIL_DATA__SEQUENCE,
                oldSequence,
                sequence));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public TargetRelationType getTargetRelation() {
        return targetRelation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetTargetRelation(TargetRelationType newTargetRelation, NotificationChain msgs) {
        TargetRelationType oldTargetRelation = targetRelation;
        targetRelation = newTargetRelation;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.BEHAVIOR_TRANSFORMATION_DETAIL_DATA__TARGET_RELATION,
                oldTargetRelation,
                newTargetRelation);
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
    public void setTargetRelation(TargetRelationType newTargetRelation) {
        if (newTargetRelation != targetRelation) {
            NotificationChain msgs = null;
            if (targetRelation != null)
                msgs = ((InternalEObject) targetRelation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
                    - TransformationPackage.BEHAVIOR_TRANSFORMATION_DETAIL_DATA__TARGET_RELATION, null, msgs);
            if (newTargetRelation != null)
                msgs = ((InternalEObject) newTargetRelation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
                    - TransformationPackage.BEHAVIOR_TRANSFORMATION_DETAIL_DATA__TARGET_RELATION, null, msgs);
            msgs = basicSetTargetRelation(newTargetRelation, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.BEHAVIOR_TRANSFORMATION_DETAIL_DATA__TARGET_RELATION,
                newTargetRelation,
                newTargetRelation));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public OperationType getOperation() {
        return operation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetOperation(OperationType newOperation, NotificationChain msgs) {
        OperationType oldOperation = operation;
        operation = newOperation;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.BEHAVIOR_TRANSFORMATION_DETAIL_DATA__OPERATION,
                oldOperation,
                newOperation);
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
    public void setOperation(OperationType newOperation) {
        if (newOperation != operation) {
            NotificationChain msgs = null;
            if (operation != null)
                msgs = ((InternalEObject) operation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
                    - TransformationPackage.BEHAVIOR_TRANSFORMATION_DETAIL_DATA__OPERATION, null, msgs);
            if (newOperation != null)
                msgs = ((InternalEObject) newOperation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
                    - TransformationPackage.BEHAVIOR_TRANSFORMATION_DETAIL_DATA__OPERATION, null, msgs);
            msgs = basicSetOperation(newOperation, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.BEHAVIOR_TRANSFORMATION_DETAIL_DATA__OPERATION,
                newOperation,
                newOperation));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getOperationApplicableStereotype() {
        return operationApplicableStereotype;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setOperationApplicableStereotype(String newOperationApplicableStereotype) {
        String oldOperationApplicableStereotype = operationApplicableStereotype;
        operationApplicableStereotype = newOperationApplicableStereotype;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.BEHAVIOR_TRANSFORMATION_DETAIL_DATA__OPERATION_APPLICABLE_STEREOTYPE,
                oldOperationApplicableStereotype,
                operationApplicableStereotype));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getMessage() {
        return message;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setMessage(String newMessage) {
        String oldMessage = message;
        message = newMessage;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                TransformationPackage.BEHAVIOR_TRANSFORMATION_DETAIL_DATA__MESSAGE,
                oldMessage,
                message));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case TransformationPackage.BEHAVIOR_TRANSFORMATION_DETAIL_DATA__TARGET_RELATION:
                return basicSetTargetRelation(null, msgs);
            case TransformationPackage.BEHAVIOR_TRANSFORMATION_DETAIL_DATA__OPERATION:
                return basicSetOperation(null, msgs);
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
            case TransformationPackage.BEHAVIOR_TRANSFORMATION_DETAIL_DATA__SEQUENCE:
                return new Integer(getSequence());
            case TransformationPackage.BEHAVIOR_TRANSFORMATION_DETAIL_DATA__TARGET_RELATION:
                return getTargetRelation();
            case TransformationPackage.BEHAVIOR_TRANSFORMATION_DETAIL_DATA__OPERATION:
                return getOperation();
            case TransformationPackage.BEHAVIOR_TRANSFORMATION_DETAIL_DATA__OPERATION_APPLICABLE_STEREOTYPE:
                return getOperationApplicableStereotype();
            case TransformationPackage.BEHAVIOR_TRANSFORMATION_DETAIL_DATA__MESSAGE:
                return getMessage();
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
            case TransformationPackage.BEHAVIOR_TRANSFORMATION_DETAIL_DATA__SEQUENCE:
                setSequence(((Integer) newValue).intValue());
                return;
            case TransformationPackage.BEHAVIOR_TRANSFORMATION_DETAIL_DATA__TARGET_RELATION:
                setTargetRelation((TargetRelationType) newValue);
                return;
            case TransformationPackage.BEHAVIOR_TRANSFORMATION_DETAIL_DATA__OPERATION:
                setOperation((OperationType) newValue);
                return;
            case TransformationPackage.BEHAVIOR_TRANSFORMATION_DETAIL_DATA__OPERATION_APPLICABLE_STEREOTYPE:
                setOperationApplicableStereotype((String) newValue);
                return;
            case TransformationPackage.BEHAVIOR_TRANSFORMATION_DETAIL_DATA__MESSAGE:
                setMessage((String) newValue);
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
            case TransformationPackage.BEHAVIOR_TRANSFORMATION_DETAIL_DATA__SEQUENCE:
                setSequence(SEQUENCE_EDEFAULT);
                return;
            case TransformationPackage.BEHAVIOR_TRANSFORMATION_DETAIL_DATA__TARGET_RELATION:
                setTargetRelation((TargetRelationType) null);
                return;
            case TransformationPackage.BEHAVIOR_TRANSFORMATION_DETAIL_DATA__OPERATION:
                setOperation((OperationType) null);
                return;
            case TransformationPackage.BEHAVIOR_TRANSFORMATION_DETAIL_DATA__OPERATION_APPLICABLE_STEREOTYPE:
                setOperationApplicableStereotype(OPERATION_APPLICABLE_STEREOTYPE_EDEFAULT);
                return;
            case TransformationPackage.BEHAVIOR_TRANSFORMATION_DETAIL_DATA__MESSAGE:
                setMessage(MESSAGE_EDEFAULT);
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
            case TransformationPackage.BEHAVIOR_TRANSFORMATION_DETAIL_DATA__SEQUENCE:
                return sequence != SEQUENCE_EDEFAULT;
            case TransformationPackage.BEHAVIOR_TRANSFORMATION_DETAIL_DATA__TARGET_RELATION:
                return targetRelation != null;
            case TransformationPackage.BEHAVIOR_TRANSFORMATION_DETAIL_DATA__OPERATION:
                return operation != null;
            case TransformationPackage.BEHAVIOR_TRANSFORMATION_DETAIL_DATA__OPERATION_APPLICABLE_STEREOTYPE:
                return OPERATION_APPLICABLE_STEREOTYPE_EDEFAULT == null ? operationApplicableStereotype != null
                : !OPERATION_APPLICABLE_STEREOTYPE_EDEFAULT.equals(operationApplicableStereotype);
            case TransformationPackage.BEHAVIOR_TRANSFORMATION_DETAIL_DATA__MESSAGE:
                return MESSAGE_EDEFAULT == null ? message != null : !MESSAGE_EDEFAULT.equals(message);
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
        result.append(" (sequence: ");
        result.append(sequence);
        result.append(", operationApplicableStereotype: ");
        result.append(operationApplicableStereotype);
        result.append(", message: ");
        result.append(message);
        result.append(')');
        return result.toString();
    }

} // BehaviorTransformationDetailDataImpl
