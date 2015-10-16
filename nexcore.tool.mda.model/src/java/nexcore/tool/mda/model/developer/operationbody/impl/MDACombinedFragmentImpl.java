/**
 * Copyright (c) 2012 SK C&C Co., Ltd. All rights reserved. 
 * 
 * This software is the confidential and proprietary information of SK C&C. 
 * You shall not disclose such confidential information and shall use it 
 * only in accordance with the terms of the license agreement you entered into 
 * with SK C&C.
 */
package nexcore.tool.mda.model.developer.operationbody.impl;

import java.util.Collection;

import nexcore.tool.mda.model.developer.operationbody.MDACombinedFragment;
import nexcore.tool.mda.model.developer.operationbody.MDAOperand;
import nexcore.tool.mda.model.developer.operationbody.OperationbodyPackage;

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
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>MDA Combined Fragment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link nexcore.tool.mda.model.developer.operationbody.impl.MDACombinedFragmentImpl#getId <em>Id</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.operationbody.impl.MDACombinedFragmentImpl#getName <em>Name</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.operationbody.impl.MDACombinedFragmentImpl#getInteractionOperator <em>Interaction Operator</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.operationbody.impl.MDACombinedFragmentImpl#getOperands <em>Operands</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.operationbody.impl.MDACombinedFragmentImpl#getAllMessageCnt <em>All Message Cnt</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MDACombinedFragmentImpl extends EObjectImpl implements MDACombinedFragment {
	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static final String copyright = "Copyright (c) 2012 SK C&C Co., Ltd. All rights reserved. \r\n\r\nThis software is the confidential and proprietary information of SK C&C. \r\nYou shall not disclose such confidential information and shall use it \r\nonly in accordance with the terms of the license agreement you entered into \r\nwith SK C&C.";

	/**
     * The default value of the '{@link #getId() <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getId()
     * @generated
     * @ordered
     */
	protected static final String ID_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getId()
     * @generated
     * @ordered
     */
	protected String id = ID_EDEFAULT;

	/**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
	protected static final String NAME_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
	protected String name = NAME_EDEFAULT;

	/**
     * The default value of the '{@link #getInteractionOperator() <em>Interaction Operator</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getInteractionOperator()
     * @generated
     * @ordered
     */
	protected static final String INTERACTION_OPERATOR_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getInteractionOperator() <em>Interaction Operator</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getInteractionOperator()
     * @generated
     * @ordered
     */
	protected String interactionOperator = INTERACTION_OPERATOR_EDEFAULT;

	/**
     * The cached value of the '{@link #getOperands() <em>Operands</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getOperands()
     * @generated
     * @ordered
     */
	protected EList<MDAOperand> operands;

	/**
     * The default value of the '{@link #getAllMessageCnt() <em>All Message Cnt</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAllMessageCnt()
     * @generated
     * @ordered
     */
    protected static final int ALL_MESSAGE_CNT_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getAllMessageCnt() <em>All Message Cnt</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAllMessageCnt()
     * @generated
     * @ordered
     */
    protected int allMessageCnt = ALL_MESSAGE_CNT_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected MDACombinedFragmentImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return OperationbodyPackage.Literals.MDA_COMBINED_FRAGMENT;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getId() {
        return id;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setId(String newId) {
        String oldId = id;
        id = newId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OperationbodyPackage.MDA_COMBINED_FRAGMENT__ID, oldId, id));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getName() {
        return name;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OperationbodyPackage.MDA_COMBINED_FRAGMENT__NAME, oldName, name));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getInteractionOperator() {
        return interactionOperator;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setInteractionOperator(String newInteractionOperator) {
        String oldInteractionOperator = interactionOperator;
        interactionOperator = newInteractionOperator;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OperationbodyPackage.MDA_COMBINED_FRAGMENT__INTERACTION_OPERATOR, oldInteractionOperator, interactionOperator));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<MDAOperand> getOperands() {
        if (operands == null) {
            operands = new EObjectContainmentEList<MDAOperand>(MDAOperand.class, this, OperationbodyPackage.MDA_COMBINED_FRAGMENT__OPERANDS);
        }
        return operands;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getAllMessageCnt() {
        return allMessageCnt;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setAllMessageCnt(int newAllMessageCnt) {
        int oldAllMessageCnt = allMessageCnt;
        allMessageCnt = newAllMessageCnt;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OperationbodyPackage.MDA_COMBINED_FRAGMENT__ALL_MESSAGE_CNT, oldAllMessageCnt, allMessageCnt));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case OperationbodyPackage.MDA_COMBINED_FRAGMENT__OPERANDS:
                return ((InternalEList<?>)getOperands()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case OperationbodyPackage.MDA_COMBINED_FRAGMENT__ID:
                return getId();
            case OperationbodyPackage.MDA_COMBINED_FRAGMENT__NAME:
                return getName();
            case OperationbodyPackage.MDA_COMBINED_FRAGMENT__INTERACTION_OPERATOR:
                return getInteractionOperator();
            case OperationbodyPackage.MDA_COMBINED_FRAGMENT__OPERANDS:
                return getOperands();
            case OperationbodyPackage.MDA_COMBINED_FRAGMENT__ALL_MESSAGE_CNT:
                return getAllMessageCnt();
        }
        return super.eGet(featureID, resolve, coreType);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case OperationbodyPackage.MDA_COMBINED_FRAGMENT__ID:
                setId((String)newValue);
                return;
            case OperationbodyPackage.MDA_COMBINED_FRAGMENT__NAME:
                setName((String)newValue);
                return;
            case OperationbodyPackage.MDA_COMBINED_FRAGMENT__INTERACTION_OPERATOR:
                setInteractionOperator((String)newValue);
                return;
            case OperationbodyPackage.MDA_COMBINED_FRAGMENT__OPERANDS:
                getOperands().clear();
                getOperands().addAll((Collection<? extends MDAOperand>)newValue);
                return;
            case OperationbodyPackage.MDA_COMBINED_FRAGMENT__ALL_MESSAGE_CNT:
                setAllMessageCnt((Integer)newValue);
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
            case OperationbodyPackage.MDA_COMBINED_FRAGMENT__ID:
                setId(ID_EDEFAULT);
                return;
            case OperationbodyPackage.MDA_COMBINED_FRAGMENT__NAME:
                setName(NAME_EDEFAULT);
                return;
            case OperationbodyPackage.MDA_COMBINED_FRAGMENT__INTERACTION_OPERATOR:
                setInteractionOperator(INTERACTION_OPERATOR_EDEFAULT);
                return;
            case OperationbodyPackage.MDA_COMBINED_FRAGMENT__OPERANDS:
                getOperands().clear();
                return;
            case OperationbodyPackage.MDA_COMBINED_FRAGMENT__ALL_MESSAGE_CNT:
                setAllMessageCnt(ALL_MESSAGE_CNT_EDEFAULT);
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
            case OperationbodyPackage.MDA_COMBINED_FRAGMENT__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case OperationbodyPackage.MDA_COMBINED_FRAGMENT__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case OperationbodyPackage.MDA_COMBINED_FRAGMENT__INTERACTION_OPERATOR:
                return INTERACTION_OPERATOR_EDEFAULT == null ? interactionOperator != null : !INTERACTION_OPERATOR_EDEFAULT.equals(interactionOperator);
            case OperationbodyPackage.MDA_COMBINED_FRAGMENT__OPERANDS:
                return operands != null && !operands.isEmpty();
            case OperationbodyPackage.MDA_COMBINED_FRAGMENT__ALL_MESSAGE_CNT:
                return allMessageCnt != ALL_MESSAGE_CNT_EDEFAULT;
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
        result.append(" (id: ");
        result.append(id);
        result.append(", name: ");
        result.append(name);
        result.append(", interactionOperator: ");
        result.append(interactionOperator);
        result.append(", allMessageCnt: ");
        result.append(allMessageCnt);
        result.append(')');
        return result.toString();
    }

} //MDACombinedFragmentImpl
