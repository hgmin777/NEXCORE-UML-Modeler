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
import nexcore.tool.mda.model.developer.operationbody.MDAOperationBody;
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
 * An implementation of the model object '<em><b>MDA Operation Body</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link nexcore.tool.mda.model.developer.operationbody.impl.MDAOperationBodyImpl#getId <em>Id</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.operationbody.impl.MDAOperationBodyImpl#getName <em>Name</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.operationbody.impl.MDAOperationBodyImpl#getQualifiedName <em>Qualified Name</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.operationbody.impl.MDAOperationBodyImpl#getFragments <em>Fragments</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.operationbody.impl.MDAOperationBodyImpl#getMessages <em>Messages</em>}</li>
 *   <li>{@link nexcore.tool.mda.model.developer.operationbody.impl.MDAOperationBodyImpl#getOrder <em>Order</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MDAOperationBodyImpl extends EObjectImpl implements MDAOperationBody {
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
     * The default value of the '{@link #getQualifiedName() <em>Qualified Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getQualifiedName()
     * @generated
     * @ordered
     */
	protected static final String QUALIFIED_NAME_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getQualifiedName() <em>Qualified Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getQualifiedName()
     * @generated
     * @ordered
     */
	protected String qualifiedName = QUALIFIED_NAME_EDEFAULT;

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
     * The cached value of the '{@link #getMessages() <em>Messages</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getMessages()
     * @generated
     * @ordered
     */
	protected EList<MDAMessage> messages;

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
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected MDAOperationBodyImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return OperationbodyPackage.Literals.MDA_OPERATION_BODY;
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
            eNotify(new ENotificationImpl(this, Notification.SET, OperationbodyPackage.MDA_OPERATION_BODY__ID, oldId, id));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getQualifiedName() {
        return qualifiedName;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setQualifiedName(String newQualifiedName) {
        String oldQualifiedName = qualifiedName;
        qualifiedName = newQualifiedName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, OperationbodyPackage.MDA_OPERATION_BODY__QUALIFIED_NAME, oldQualifiedName, qualifiedName));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<MDACombinedFragment> getFragments() {
        if (fragments == null) {
            fragments = new EObjectContainmentEList<MDACombinedFragment>(MDACombinedFragment.class, this, OperationbodyPackage.MDA_OPERATION_BODY__FRAGMENTS);
        }
        return fragments;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<MDAMessage> getMessages() {
        if (messages == null) {
            messages = new EObjectContainmentEList<MDAMessage>(MDAMessage.class, this, OperationbodyPackage.MDA_OPERATION_BODY__MESSAGES);
        }
        return messages;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<String> getOrder() {
        if (order == null) {
            order = new EDataTypeUniqueEList<String>(String.class, this, OperationbodyPackage.MDA_OPERATION_BODY__ORDER);
        }
        return order;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case OperationbodyPackage.MDA_OPERATION_BODY__FRAGMENTS:
                return ((InternalEList<?>)getFragments()).basicRemove(otherEnd, msgs);
            case OperationbodyPackage.MDA_OPERATION_BODY__MESSAGES:
                return ((InternalEList<?>)getMessages()).basicRemove(otherEnd, msgs);
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
            case OperationbodyPackage.MDA_OPERATION_BODY__ID:
                return getId();
            case OperationbodyPackage.MDA_OPERATION_BODY__NAME:
                return getName();
            case OperationbodyPackage.MDA_OPERATION_BODY__QUALIFIED_NAME:
                return getQualifiedName();
            case OperationbodyPackage.MDA_OPERATION_BODY__FRAGMENTS:
                return getFragments();
            case OperationbodyPackage.MDA_OPERATION_BODY__MESSAGES:
                return getMessages();
            case OperationbodyPackage.MDA_OPERATION_BODY__ORDER:
                return getOrder();
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
            case OperationbodyPackage.MDA_OPERATION_BODY__ID:
                setId((String)newValue);
                return;
            case OperationbodyPackage.MDA_OPERATION_BODY__NAME:
                setName((String)newValue);
                return;
            case OperationbodyPackage.MDA_OPERATION_BODY__QUALIFIED_NAME:
                setQualifiedName((String)newValue);
                return;
            case OperationbodyPackage.MDA_OPERATION_BODY__FRAGMENTS:
                getFragments().clear();
                getFragments().addAll((Collection<? extends MDACombinedFragment>)newValue);
                return;
            case OperationbodyPackage.MDA_OPERATION_BODY__MESSAGES:
                getMessages().clear();
                getMessages().addAll((Collection<? extends MDAMessage>)newValue);
                return;
            case OperationbodyPackage.MDA_OPERATION_BODY__ORDER:
                getOrder().clear();
                getOrder().addAll((Collection<? extends String>)newValue);
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
            case OperationbodyPackage.MDA_OPERATION_BODY__ID:
                setId(ID_EDEFAULT);
                return;
            case OperationbodyPackage.MDA_OPERATION_BODY__NAME:
                setName(NAME_EDEFAULT);
                return;
            case OperationbodyPackage.MDA_OPERATION_BODY__QUALIFIED_NAME:
                setQualifiedName(QUALIFIED_NAME_EDEFAULT);
                return;
            case OperationbodyPackage.MDA_OPERATION_BODY__FRAGMENTS:
                getFragments().clear();
                return;
            case OperationbodyPackage.MDA_OPERATION_BODY__MESSAGES:
                getMessages().clear();
                return;
            case OperationbodyPackage.MDA_OPERATION_BODY__ORDER:
                getOrder().clear();
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
            case OperationbodyPackage.MDA_OPERATION_BODY__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case OperationbodyPackage.MDA_OPERATION_BODY__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case OperationbodyPackage.MDA_OPERATION_BODY__QUALIFIED_NAME:
                return QUALIFIED_NAME_EDEFAULT == null ? qualifiedName != null : !QUALIFIED_NAME_EDEFAULT.equals(qualifiedName);
            case OperationbodyPackage.MDA_OPERATION_BODY__FRAGMENTS:
                return fragments != null && !fragments.isEmpty();
            case OperationbodyPackage.MDA_OPERATION_BODY__MESSAGES:
                return messages != null && !messages.isEmpty();
            case OperationbodyPackage.MDA_OPERATION_BODY__ORDER:
                return order != null && !order.isEmpty();
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
        result.append(", qualifiedName: ");
        result.append(qualifiedName);
        result.append(", order: ");
        result.append(order);
        result.append(')');
        return result.toString();
    }

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setName(String value) {
		// TODO Auto-generated method stub
		
	}

} //MDAOperationBodyImpl
