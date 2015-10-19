/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.model.usecasedetail.impl;

import java.util.Collection;

import nexcore.tool.uml.model.usecasedetail.FlowObject;
import nexcore.tool.uml.model.usecasedetail.UseCaseDetail;
import nexcore.tool.uml.model.usecasedetail.UseCaseDetailPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EAnnotationImpl;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Use Case Detail</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link nexcore.tool.uml.model.usecasedetail.impl.UseCaseDetailImpl#getPreCondition
 * <em>Pre Condition</em>}</li>
 * <li>
 * {@link nexcore.tool.uml.model.usecasedetail.impl.UseCaseDetailImpl#getPostCondition
 * <em>Post Condition</em>}</li>
 * <li>
 * {@link nexcore.tool.uml.model.usecasedetail.impl.UseCaseDetailImpl#getBasicFlowList
 * <em>Basic Flow List</em>}</li>
 * <li>
 * {@link nexcore.tool.uml.model.usecasedetail.impl.UseCaseDetailImpl#getSubFlowList
 * <em>Sub Flow List</em>}</li>
 * <li>
 * {@link nexcore.tool.uml.model.usecasedetail.impl.UseCaseDetailImpl#getExceptionFlowList
 * <em>Exception Flow List</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.usecasedetail.impl</li>
 * <li>설  명 : UseCaseDetailImpl</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class UseCaseDetailImpl extends EAnnotationImpl implements UseCaseDetail {
    /**
     * The default value of the '{@link #getPreCondition()
     * <em>Pre Condition</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getPreCondition()
     * @generated
     * @ordered
     */
    protected static final String PRE_CONDITION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPreCondition()
     * <em>Pre Condition</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getPreCondition()
     * @generated
     * @ordered
     */
    protected String preCondition = PRE_CONDITION_EDEFAULT;

    /**
     * The default value of the '{@link #getPostCondition()
     * <em>Post Condition</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getPostCondition()
     * @generated
     * @ordered
     */
    protected static final String POST_CONDITION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPostCondition()
     * <em>Post Condition</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getPostCondition()
     * @generated
     * @ordered
     */
    protected String postCondition = POST_CONDITION_EDEFAULT;

    /**
     * The cached value of the '{@link #getBasicFlowList()
     * <em>Basic Flow List</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getBasicFlowList()
     * @generated
     * @ordered
     */
    protected EList<FlowObject> basicFlowList;

    /**
     * The cached value of the '{@link #getSubFlowList() <em>Sub Flow List</em>}
     * ' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see #getSubFlowList()
     * @generated
     * @ordered
     */
    protected EList<FlowObject> subFlowList;

    /**
     * The cached value of the '{@link #getExceptionFlowList()
     * <em>Exception Flow List</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getExceptionFlowList()
     * @generated
     * @ordered
     */
    protected EList<FlowObject> exceptionFlowList;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected UseCaseDetailImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return UseCaseDetailPackage.Literals.USE_CASE_DETAIL;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getPreCondition() {
        return preCondition;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setPreCondition(String newPreCondition) {
        String oldPreCondition = preCondition;
        preCondition = newPreCondition;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                UseCaseDetailPackage.USE_CASE_DETAIL__PRE_CONDITION,
                oldPreCondition,
                preCondition));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getPostCondition() {
        return postCondition;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setPostCondition(String newPostCondition) {
        String oldPostCondition = postCondition;
        postCondition = newPostCondition;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                UseCaseDetailPackage.USE_CASE_DETAIL__POST_CONDITION,
                oldPostCondition,
                postCondition));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<FlowObject> getBasicFlowList() {
        if (basicFlowList == null) {
            basicFlowList = new EObjectContainmentEList<FlowObject>(FlowObject.class,
                this,
                UseCaseDetailPackage.USE_CASE_DETAIL__BASIC_FLOW_LIST);
        }
        return basicFlowList;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<FlowObject> getSubFlowList() {
        if (subFlowList == null) {
            subFlowList = new EObjectContainmentEList<FlowObject>(FlowObject.class,
                this,
                UseCaseDetailPackage.USE_CASE_DETAIL__SUB_FLOW_LIST);
        }
        return subFlowList;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<FlowObject> getExceptionFlowList() {
        if (exceptionFlowList == null) {
            exceptionFlowList = new EObjectContainmentEList<FlowObject>(FlowObject.class,
                this,
                UseCaseDetailPackage.USE_CASE_DETAIL__EXCEPTION_FLOW_LIST);
        }
        return exceptionFlowList;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case UseCaseDetailPackage.USE_CASE_DETAIL__BASIC_FLOW_LIST:
                return ((InternalEList<?>) getBasicFlowList()).basicRemove(otherEnd, msgs);
            case UseCaseDetailPackage.USE_CASE_DETAIL__SUB_FLOW_LIST:
                return ((InternalEList<?>) getSubFlowList()).basicRemove(otherEnd, msgs);
            case UseCaseDetailPackage.USE_CASE_DETAIL__EXCEPTION_FLOW_LIST:
                return ((InternalEList<?>) getExceptionFlowList()).basicRemove(otherEnd, msgs);
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
            case UseCaseDetailPackage.USE_CASE_DETAIL__PRE_CONDITION:
                return getPreCondition();
            case UseCaseDetailPackage.USE_CASE_DETAIL__POST_CONDITION:
                return getPostCondition();
            case UseCaseDetailPackage.USE_CASE_DETAIL__BASIC_FLOW_LIST:
                return getBasicFlowList();
            case UseCaseDetailPackage.USE_CASE_DETAIL__SUB_FLOW_LIST:
                return getSubFlowList();
            case UseCaseDetailPackage.USE_CASE_DETAIL__EXCEPTION_FLOW_LIST:
                return getExceptionFlowList();
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
            case UseCaseDetailPackage.USE_CASE_DETAIL__PRE_CONDITION:
                setPreCondition((String) newValue);
                return;
            case UseCaseDetailPackage.USE_CASE_DETAIL__POST_CONDITION:
                setPostCondition((String) newValue);
                return;
            case UseCaseDetailPackage.USE_CASE_DETAIL__BASIC_FLOW_LIST:
                getBasicFlowList().clear();
                getBasicFlowList().addAll((Collection<? extends FlowObject>) newValue);
                return;
            case UseCaseDetailPackage.USE_CASE_DETAIL__SUB_FLOW_LIST:
                getSubFlowList().clear();
                getSubFlowList().addAll((Collection<? extends FlowObject>) newValue);
                return;
            case UseCaseDetailPackage.USE_CASE_DETAIL__EXCEPTION_FLOW_LIST:
                getExceptionFlowList().clear();
                getExceptionFlowList().addAll((Collection<? extends FlowObject>) newValue);
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
            case UseCaseDetailPackage.USE_CASE_DETAIL__PRE_CONDITION:
                setPreCondition(PRE_CONDITION_EDEFAULT);
                return;
            case UseCaseDetailPackage.USE_CASE_DETAIL__POST_CONDITION:
                setPostCondition(POST_CONDITION_EDEFAULT);
                return;
            case UseCaseDetailPackage.USE_CASE_DETAIL__BASIC_FLOW_LIST:
                getBasicFlowList().clear();
                return;
            case UseCaseDetailPackage.USE_CASE_DETAIL__SUB_FLOW_LIST:
                getSubFlowList().clear();
                return;
            case UseCaseDetailPackage.USE_CASE_DETAIL__EXCEPTION_FLOW_LIST:
                getExceptionFlowList().clear();
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
            case UseCaseDetailPackage.USE_CASE_DETAIL__PRE_CONDITION:
                return PRE_CONDITION_EDEFAULT == null ? preCondition != null
                : !PRE_CONDITION_EDEFAULT.equals(preCondition);
            case UseCaseDetailPackage.USE_CASE_DETAIL__POST_CONDITION:
                return POST_CONDITION_EDEFAULT == null ? postCondition != null
                : !POST_CONDITION_EDEFAULT.equals(postCondition);
            case UseCaseDetailPackage.USE_CASE_DETAIL__BASIC_FLOW_LIST:
                return basicFlowList != null && !basicFlowList.isEmpty();
            case UseCaseDetailPackage.USE_CASE_DETAIL__SUB_FLOW_LIST:
                return subFlowList != null && !subFlowList.isEmpty();
            case UseCaseDetailPackage.USE_CASE_DETAIL__EXCEPTION_FLOW_LIST:
                return exceptionFlowList != null && !exceptionFlowList.isEmpty();
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
        result.append(" (PreCondition: ");
        result.append(preCondition);
        result.append(", PostCondition: ");
        result.append(postCondition);
        result.append(')');
        return result.toString();
    }

} // UseCaseDetailImpl
