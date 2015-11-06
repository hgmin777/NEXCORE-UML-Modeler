/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.mda.model.developer.reverseTransformation.impl;

import nexcore.tool.mda.model.developer.reverseTransformation.ReverseTransformationPackage;
import nexcore.tool.mda.model.developer.reverseTransformation.SequenceDiagramRule;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sequence Diagram Rule</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.impl.SequenceDiagramRuleImpl#isCreateVOPCYN <em>Create VOPCYN</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.reverseTransformation.impl.SequenceDiagramRuleImpl#getMessageCallDepth <em>Message Call Depth</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SequenceDiagramRuleImpl extends DiagramRuleImpl implements SequenceDiagramRule {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "";

    /**
     * The default value of the '{@link #isCreateVOPCYN() <em>Create VOPCYN</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isCreateVOPCYN()
     * @generated
     * @ordered
     */
    protected static final boolean CREATE_VOPCYN_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isCreateVOPCYN() <em>Create VOPCYN</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isCreateVOPCYN()
     * @generated
     * @ordered
     */
    protected boolean createVOPCYN = CREATE_VOPCYN_EDEFAULT;

    /**
     * The default value of the '{@link #getMessageCallDepth() <em>Message Call Depth</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMessageCallDepth()
     * @generated
     * @ordered
     */
    protected static final int MESSAGE_CALL_DEPTH_EDEFAULT = 1;

    /**
     * The cached value of the '{@link #getMessageCallDepth() <em>Message Call Depth</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMessageCallDepth()
     * @generated
     * @ordered
     */
    protected int messageCallDepth = MESSAGE_CALL_DEPTH_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SequenceDiagramRuleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ReverseTransformationPackage.Literals.SEQUENCE_DIAGRAM_RULE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isCreateVOPCYN() {
        return createVOPCYN;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCreateVOPCYN(boolean newCreateVOPCYN) {
        boolean oldCreateVOPCYN = createVOPCYN;
        createVOPCYN = newCreateVOPCYN;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ReverseTransformationPackage.SEQUENCE_DIAGRAM_RULE__CREATE_VOPCYN, oldCreateVOPCYN, createVOPCYN));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getMessageCallDepth() {
        return messageCallDepth;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMessageCallDepth(int newMessageCallDepth) {
        int oldMessageCallDepth = messageCallDepth;
        messageCallDepth = newMessageCallDepth;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ReverseTransformationPackage.SEQUENCE_DIAGRAM_RULE__MESSAGE_CALL_DEPTH, oldMessageCallDepth, messageCallDepth));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ReverseTransformationPackage.SEQUENCE_DIAGRAM_RULE__CREATE_VOPCYN:
                return isCreateVOPCYN();
            case ReverseTransformationPackage.SEQUENCE_DIAGRAM_RULE__MESSAGE_CALL_DEPTH:
                return getMessageCallDepth();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case ReverseTransformationPackage.SEQUENCE_DIAGRAM_RULE__CREATE_VOPCYN:
                setCreateVOPCYN((Boolean)newValue);
                return;
            case ReverseTransformationPackage.SEQUENCE_DIAGRAM_RULE__MESSAGE_CALL_DEPTH:
                setMessageCallDepth((Integer)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case ReverseTransformationPackage.SEQUENCE_DIAGRAM_RULE__CREATE_VOPCYN:
                setCreateVOPCYN(CREATE_VOPCYN_EDEFAULT);
                return;
            case ReverseTransformationPackage.SEQUENCE_DIAGRAM_RULE__MESSAGE_CALL_DEPTH:
                setMessageCallDepth(MESSAGE_CALL_DEPTH_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case ReverseTransformationPackage.SEQUENCE_DIAGRAM_RULE__CREATE_VOPCYN:
                return createVOPCYN != CREATE_VOPCYN_EDEFAULT;
            case ReverseTransformationPackage.SEQUENCE_DIAGRAM_RULE__MESSAGE_CALL_DEPTH:
                return messageCallDepth != MESSAGE_CALL_DEPTH_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (createVOPCYN: ");
        result.append(createVOPCYN);
        result.append(", messageCallDepth: ");
        result.append(messageCallDepth);
        result.append(')');
        return result.toString();
    }

} //SequenceDiagramRuleImpl
