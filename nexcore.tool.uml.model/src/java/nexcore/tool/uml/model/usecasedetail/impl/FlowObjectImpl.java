/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.model.usecasedetail.impl;

import nexcore.tool.uml.model.usecasedetail.FlowObject;
import nexcore.tool.uml.model.usecasedetail.UseCaseDetailPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Flow Object</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link nexcore.tool.uml.model.usecasedetail.impl.FlowObjectImpl#getFlowId
 * <em>Flow Id</em>}</li>
 * <li>
 * {@link nexcore.tool.uml.model.usecasedetail.impl.FlowObjectImpl#getFlowName
 * <em>Flow Name</em>}</li>
 * <li>
 * {@link nexcore.tool.uml.model.usecasedetail.impl.FlowObjectImpl#getFlowOverview
 * <em>Flow Overview</em>}</li>
 * <li>
 * {@link nexcore.tool.uml.model.usecasedetail.impl.FlowObjectImpl#getFlowDescription
 * <em>Flow Description</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.usecasedetail.impl</li>
 * <li>설  명 : FlowObjectImpl</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class FlowObjectImpl extends EObjectImpl implements FlowObject {
    /**
     * The default value of the '{@link #getFlowId() <em>Flow Id</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getFlowId()
     * @generated
     * @ordered
     */
    protected static final String FLOW_ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFlowId() <em>Flow Id</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getFlowId()
     * @generated
     * @ordered
     */
    protected String flowId = FLOW_ID_EDEFAULT;

    /**
     * The default value of the '{@link #getFlowName() <em>Flow Name</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getFlowName()
     * @generated
     * @ordered
     */
    protected static final String FLOW_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFlowName() <em>Flow Name</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getFlowName()
     * @generated
     * @ordered
     */
    protected String flowName = FLOW_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getFlowOverview()
     * <em>Flow Overview</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getFlowOverview()
     * @generated
     * @ordered
     */
    protected static final String FLOW_OVERVIEW_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFlowOverview()
     * <em>Flow Overview</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getFlowOverview()
     * @generated
     * @ordered
     */
    protected String flowOverview = FLOW_OVERVIEW_EDEFAULT;

    /**
     * The default value of the '{@link #getFlowDescription()
     * <em>Flow Description</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getFlowDescription()
     * @generated
     * @ordered
     */
    protected static final String FLOW_DESCRIPTION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFlowDescription()
     * <em>Flow Description</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getFlowDescription()
     * @generated
     * @ordered
     */
    protected String flowDescription = FLOW_DESCRIPTION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected FlowObjectImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return UseCaseDetailPackage.Literals.FLOW_OBJECT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getFlowId() {
        return flowId;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setFlowId(String newFlowId) {
        String oldFlowId = flowId;
        flowId = newFlowId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                UseCaseDetailPackage.FLOW_OBJECT__FLOW_ID,
                oldFlowId,
                flowId));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getFlowName() {
        return flowName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setFlowName(String newFlowName) {
        String oldFlowName = flowName;
        flowName = newFlowName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                UseCaseDetailPackage.FLOW_OBJECT__FLOW_NAME,
                oldFlowName,
                flowName));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getFlowOverview() {
        return flowOverview;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setFlowOverview(String newFlowOverview) {
        String oldFlowOverview = flowOverview;
        flowOverview = newFlowOverview;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                UseCaseDetailPackage.FLOW_OBJECT__FLOW_OVERVIEW,
                oldFlowOverview,
                flowOverview));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getFlowDescription() {
        return flowDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setFlowDescription(String newFlowDescription) {
        String oldFlowDescription = flowDescription;
        flowDescription = newFlowDescription;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                UseCaseDetailPackage.FLOW_OBJECT__FLOW_DESCRIPTION,
                oldFlowDescription,
                flowDescription));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case UseCaseDetailPackage.FLOW_OBJECT__FLOW_ID:
                return getFlowId();
            case UseCaseDetailPackage.FLOW_OBJECT__FLOW_NAME:
                return getFlowName();
            case UseCaseDetailPackage.FLOW_OBJECT__FLOW_OVERVIEW:
                return getFlowOverview();
            case UseCaseDetailPackage.FLOW_OBJECT__FLOW_DESCRIPTION:
                return getFlowDescription();
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
            case UseCaseDetailPackage.FLOW_OBJECT__FLOW_ID:
                setFlowId((String) newValue);
                return;
            case UseCaseDetailPackage.FLOW_OBJECT__FLOW_NAME:
                setFlowName((String) newValue);
                return;
            case UseCaseDetailPackage.FLOW_OBJECT__FLOW_OVERVIEW:
                setFlowOverview((String) newValue);
                return;
            case UseCaseDetailPackage.FLOW_OBJECT__FLOW_DESCRIPTION:
                setFlowDescription((String) newValue);
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
            case UseCaseDetailPackage.FLOW_OBJECT__FLOW_ID:
                setFlowId(FLOW_ID_EDEFAULT);
                return;
            case UseCaseDetailPackage.FLOW_OBJECT__FLOW_NAME:
                setFlowName(FLOW_NAME_EDEFAULT);
                return;
            case UseCaseDetailPackage.FLOW_OBJECT__FLOW_OVERVIEW:
                setFlowOverview(FLOW_OVERVIEW_EDEFAULT);
                return;
            case UseCaseDetailPackage.FLOW_OBJECT__FLOW_DESCRIPTION:
                setFlowDescription(FLOW_DESCRIPTION_EDEFAULT);
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
            case UseCaseDetailPackage.FLOW_OBJECT__FLOW_ID:
                return FLOW_ID_EDEFAULT == null ? flowId != null : !FLOW_ID_EDEFAULT.equals(flowId);
            case UseCaseDetailPackage.FLOW_OBJECT__FLOW_NAME:
                return FLOW_NAME_EDEFAULT == null ? flowName != null : !FLOW_NAME_EDEFAULT.equals(flowName);
            case UseCaseDetailPackage.FLOW_OBJECT__FLOW_OVERVIEW:
                return FLOW_OVERVIEW_EDEFAULT == null ? flowOverview != null
                : !FLOW_OVERVIEW_EDEFAULT.equals(flowOverview);
            case UseCaseDetailPackage.FLOW_OBJECT__FLOW_DESCRIPTION:
                return FLOW_DESCRIPTION_EDEFAULT == null ? flowDescription != null
                : !FLOW_DESCRIPTION_EDEFAULT.equals(flowDescription);
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
        result.append(" (FlowId: ");
        result.append(flowId);
        result.append(", FlowName: ");
        result.append(flowName);
        result.append(", FlowOverview: ");
        result.append(flowOverview);
        result.append(", FlowDescription: ");
        result.append(flowDescription);
        result.append(')');
        return result.toString();
    }

} // FlowObjectImpl
