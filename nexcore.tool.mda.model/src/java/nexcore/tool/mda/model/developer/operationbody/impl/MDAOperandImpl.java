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
import nexcore.tool.mda.model.developer.operationbody.MDAMessage;
import nexcore.tool.mda.model.developer.operationbody.MDAOperand;
import nexcore.tool.mda.model.developer.operationbody.OperationbodyPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>MDA Operand</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link nexcore.tool.mda.model.developer.operationbody.impl.MDAOperandImpl#getId <em>Id</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.operationbody.impl.MDAOperandImpl#getName <em>Name</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.operationbody.impl.MDAOperandImpl#getGuard <em>Guard</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.operationbody.impl.MDAOperandImpl#getMessages <em>Messages</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.operationbody.impl.MDAOperandImpl#getFragments <em>Fragments</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.operationbody.impl.MDAOperandImpl#getOrder <em>Order</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.operationbody.impl.MDAOperandImpl#getAllMessageCnt <em>All Message Cnt</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MDAOperandImpl extends EObjectImpl implements MDAOperand {
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
     * The default value of the '{@link #getGuard() <em>Guard</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getGuard()
     * @generated
     * @ordered
     */
	protected static final String GUARD_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getGuard() <em>Guard</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getGuard()
     * @generated
     * @ordered
     */
	protected String guard = GUARD_EDEFAULT;

	/**
     * The cached value of the '{@link #getMessages() <em>Messages</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getMessages()
     * @generated
     * @ordered
     */
	protected EList<MDAMessage> messages;

	/**
     * The cached value of the '{@link #getFragments() <em>Fragments</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getFragments()
     * @generated
     * @ordered
     */
	protected EList<MDACombinedFragment> fragments;

	/**
     * The cached value of the '{@link #getOrder() <em>Order</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOrder()
     * @generated
     * @ordered
     */
    protected EList<String> order;

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
	protected MDAOperandImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return OperationbodyPackage.Literals.MDA_OPERAND;
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
            eNotify(new ENotificationImpl(this, Notification.SET, OperationbodyPackage.MDA_OPERAND__ID, oldId, id));
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
            eNotify(new ENotificationImpl(this, Notification.SET, OperationbodyPackage.MDA_OPERAND__NAME, oldName, name));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getGuard() {
        return guard;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setGuard(String newGuard) {
        String oldGuard = guard;
        guard = newGuard;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OperationbodyPackage.MDA_OPERAND__GUARD, oldGuard, guard));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<MDAMessage> getMessages() {
        if (messages == null) {
            messages = new EObjectContainmentEList<MDAMessage>(MDAMessage.class, this, OperationbodyPackage.MDA_OPERAND__MESSAGES);
        }
        return messages;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<MDACombinedFragment> getFragments() {
        if (fragments == null) {
            fragments = new EObjectContainmentEList<MDACombinedFragment>(MDACombinedFragment.class, this, OperationbodyPackage.MDA_OPERAND__FRAGMENTS);
        }
        return fragments;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<String> getOrder() {
        if (order == null) {
            order = new EDataTypeUniqueEList<String>(String.class, this, OperationbodyPackage.MDA_OPERAND__ORDER);
        }
        return order;
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
            eNotify(new ENotificationImpl(this, Notification.SET, OperationbodyPackage.MDA_OPERAND__ALL_MESSAGE_CNT, oldAllMessageCnt, allMessageCnt));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case OperationbodyPackage.MDA_OPERAND__MESSAGES:
                return ((InternalEList<?>)getMessages()).basicRemove(otherEnd, msgs);
            case OperationbodyPackage.MDA_OPERAND__FRAGMENTS:
                return ((InternalEList<?>)getFragments()).basicRemove(otherEnd, msgs);
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
            case OperationbodyPackage.MDA_OPERAND__ID:
                return getId();
            case OperationbodyPackage.MDA_OPERAND__NAME:
                return getName();
            case OperationbodyPackage.MDA_OPERAND__GUARD:
                return getGuard();
            case OperationbodyPackage.MDA_OPERAND__MESSAGES:
                return getMessages();
            case OperationbodyPackage.MDA_OPERAND__FRAGMENTS:
                return getFragments();
            case OperationbodyPackage.MDA_OPERAND__ORDER:
                return getOrder();
            case OperationbodyPackage.MDA_OPERAND__ALL_MESSAGE_CNT:
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
            case OperationbodyPackage.MDA_OPERAND__ID:
                setId((String)newValue);
                return;
            case OperationbodyPackage.MDA_OPERAND__NAME:
                setName((String)newValue);
                return;
            case OperationbodyPackage.MDA_OPERAND__GUARD:
                setGuard((String)newValue);
                return;
            case OperationbodyPackage.MDA_OPERAND__MESSAGES:
                getMessages().clear();
                getMessages().addAll((Collection<? extends MDAMessage>)newValue);
                return;
            case OperationbodyPackage.MDA_OPERAND__FRAGMENTS:
                getFragments().clear();
                getFragments().addAll((Collection<? extends MDACombinedFragment>)newValue);
                return;
            case OperationbodyPackage.MDA_OPERAND__ORDER:
                getOrder().clear();
                getOrder().addAll((Collection<? extends String>)newValue);
                return;
            case OperationbodyPackage.MDA_OPERAND__ALL_MESSAGE_CNT:
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
            case OperationbodyPackage.MDA_OPERAND__ID:
                setId(ID_EDEFAULT);
                return;
            case OperationbodyPackage.MDA_OPERAND__NAME:
                setName(NAME_EDEFAULT);
                return;
            case OperationbodyPackage.MDA_OPERAND__GUARD:
                setGuard(GUARD_EDEFAULT);
                return;
            case OperationbodyPackage.MDA_OPERAND__MESSAGES:
                getMessages().clear();
                return;
            case OperationbodyPackage.MDA_OPERAND__FRAGMENTS:
                getFragments().clear();
                return;
            case OperationbodyPackage.MDA_OPERAND__ORDER:
                getOrder().clear();
                return;
            case OperationbodyPackage.MDA_OPERAND__ALL_MESSAGE_CNT:
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
            case OperationbodyPackage.MDA_OPERAND__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case OperationbodyPackage.MDA_OPERAND__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case OperationbodyPackage.MDA_OPERAND__GUARD:
                return GUARD_EDEFAULT == null ? guard != null : !GUARD_EDEFAULT.equals(guard);
            case OperationbodyPackage.MDA_OPERAND__MESSAGES:
                return messages != null && !messages.isEmpty();
            case OperationbodyPackage.MDA_OPERAND__FRAGMENTS:
                return fragments != null && !fragments.isEmpty();
            case OperationbodyPackage.MDA_OPERAND__ORDER:
                return order != null && !order.isEmpty();
            case OperationbodyPackage.MDA_OPERAND__ALL_MESSAGE_CNT:
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
        result.append(", guard: ");
        result.append(guard);
        result.append(", order: ");
        result.append(order);
        result.append(", allMessageCnt: ");
        result.append(allMessageCnt);
        result.append(')');
        return result.toString();
    }

} //MDAOperandImpl
