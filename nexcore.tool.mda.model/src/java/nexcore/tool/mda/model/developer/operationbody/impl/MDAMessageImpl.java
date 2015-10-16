/**
 * Copyright (c) 2012 SK C&C Co., Ltd. All rights reserved. 
 * 
 * This software is the confidential and proprietary information of SK C&C. 
 * You shall not disclose such confidential information and shall use it 
 * only in accordance with the terms of the license agreement you entered into 
 * with SK C&C.
 */
package nexcore.tool.mda.model.developer.operationbody.impl;

import nexcore.tool.mda.model.developer.operationbody.MDAMessage;
import nexcore.tool.mda.model.developer.operationbody.OperationbodyPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>MDA Message</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link nexcore.tool.mda.model.developer.operationbody.impl.MDAMessageImpl#getId <em>Id</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.operationbody.impl.MDAMessageImpl#getName <em>Name</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.operationbody.impl.MDAMessageImpl#getMessageSort <em>Message Sort</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.operationbody.impl.MDAMessageImpl#getOperationQName <em>Operation QName</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MDAMessageImpl extends EObjectImpl implements MDAMessage {
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
     * The default value of the '{@link #getMessageSort() <em>Message Sort</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getMessageSort()
     * @generated
     * @ordered
     */
	protected static final String MESSAGE_SORT_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getMessageSort() <em>Message Sort</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getMessageSort()
     * @generated
     * @ordered
     */
	protected String messageSort = MESSAGE_SORT_EDEFAULT;

	/**
     * The default value of the '{@link #getOperationQName() <em>Operation QName</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getOperationQName()
     * @generated
     * @ordered
     */
	protected static final String OPERATION_QNAME_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getOperationQName() <em>Operation QName</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getOperationQName()
     * @generated
     * @ordered
     */
	protected String operationQName = OPERATION_QNAME_EDEFAULT;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected MDAMessageImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return OperationbodyPackage.Literals.MDA_MESSAGE;
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
            eNotify(new ENotificationImpl(this, Notification.SET, OperationbodyPackage.MDA_MESSAGE__ID, oldId, id));
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
            eNotify(new ENotificationImpl(this, Notification.SET, OperationbodyPackage.MDA_MESSAGE__NAME, oldName, name));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getMessageSort() {
        return messageSort;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setMessageSort(String newMessageSort) {
        String oldMessageSort = messageSort;
        messageSort = newMessageSort;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OperationbodyPackage.MDA_MESSAGE__MESSAGE_SORT, oldMessageSort, messageSort));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getOperationQName() {
        return operationQName;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setOperationQName(String newOperationQName) {
        String oldOperationQName = operationQName;
        operationQName = newOperationQName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OperationbodyPackage.MDA_MESSAGE__OPERATION_QNAME, oldOperationQName, operationQName));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case OperationbodyPackage.MDA_MESSAGE__ID:
                return getId();
            case OperationbodyPackage.MDA_MESSAGE__NAME:
                return getName();
            case OperationbodyPackage.MDA_MESSAGE__MESSAGE_SORT:
                return getMessageSort();
            case OperationbodyPackage.MDA_MESSAGE__OPERATION_QNAME:
                return getOperationQName();
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
            case OperationbodyPackage.MDA_MESSAGE__ID:
                setId((String)newValue);
                return;
            case OperationbodyPackage.MDA_MESSAGE__NAME:
                setName((String)newValue);
                return;
            case OperationbodyPackage.MDA_MESSAGE__MESSAGE_SORT:
                setMessageSort((String)newValue);
                return;
            case OperationbodyPackage.MDA_MESSAGE__OPERATION_QNAME:
                setOperationQName((String)newValue);
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
            case OperationbodyPackage.MDA_MESSAGE__ID:
                setId(ID_EDEFAULT);
                return;
            case OperationbodyPackage.MDA_MESSAGE__NAME:
                setName(NAME_EDEFAULT);
                return;
            case OperationbodyPackage.MDA_MESSAGE__MESSAGE_SORT:
                setMessageSort(MESSAGE_SORT_EDEFAULT);
                return;
            case OperationbodyPackage.MDA_MESSAGE__OPERATION_QNAME:
                setOperationQName(OPERATION_QNAME_EDEFAULT);
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
            case OperationbodyPackage.MDA_MESSAGE__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case OperationbodyPackage.MDA_MESSAGE__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case OperationbodyPackage.MDA_MESSAGE__MESSAGE_SORT:
                return MESSAGE_SORT_EDEFAULT == null ? messageSort != null : !MESSAGE_SORT_EDEFAULT.equals(messageSort);
            case OperationbodyPackage.MDA_MESSAGE__OPERATION_QNAME:
                return OPERATION_QNAME_EDEFAULT == null ? operationQName != null : !OPERATION_QNAME_EDEFAULT.equals(operationQName);
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
        result.append(", messageSort: ");
        result.append(messageSort);
        result.append(", operationQName: ");
        result.append(operationQName);
        result.append(')');
        return result.toString();
    }

} //MDAMessageImpl
