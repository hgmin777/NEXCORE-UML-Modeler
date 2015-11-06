/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.model.rmdata.impl;

import java.util.Collection;

import nexcore.tool.uml.model.rmdata.RMData;
import nexcore.tool.uml.model.rmdata.RMDataPackage;
import nexcore.tool.uml.model.rmdata.RMObject;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>RM Data</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link nexcore.tool.uml.model.rmdata.impl.RMDataImpl#getProjectList <em>
 * Project List</em>}</li>
 * <li>{@link nexcore.tool.uml.model.rmdata.impl.RMDataImpl#getRequirementList
 * <em>Requirement List</em>}</li>
 * <li>{@link nexcore.tool.uml.model.rmdata.impl.RMDataImpl#getUseCaseList <em>
 * Use Case List</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.rmdata.impl</li>
 * <li>설  명 : RMDataImpl</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class RMDataImpl extends EObjectImpl implements RMData {
    /**
     * The cached value of the '{@link #getProjectList() <em>Project List</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getProjectList()
     * @generated
     * @ordered
     */
    protected EList<RMObject> projectList;

    /**
     * The cached value of the '{@link #getRequirementList()
     * <em>Requirement List</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getRequirementList()
     * @generated
     * @ordered
     */
    protected EList<RMObject> requirementList;

    /**
     * The cached value of the '{@link #getUseCaseList() <em>Use Case List</em>}
     * ' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see #getUseCaseList()
     * @generated
     * @ordered
     */
    protected EList<RMObject> useCaseList;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected RMDataImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return RMDataPackage.Literals.RM_DATA;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<RMObject> getProjectList() {
        if (projectList == null) {
            projectList = new EObjectContainmentEList<RMObject>(RMObject.class,
                this,
                RMDataPackage.RM_DATA__PROJECT_LIST);
        }
        return projectList;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<RMObject> getRequirementList() {
        if (requirementList == null) {
            requirementList = new EObjectContainmentEList<RMObject>(RMObject.class,
                this,
                RMDataPackage.RM_DATA__REQUIREMENT_LIST);
        }
        return requirementList;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<RMObject> getUseCaseList() {
        if (useCaseList == null) {
            useCaseList = new EObjectContainmentEList<RMObject>(RMObject.class,
                this,
                RMDataPackage.RM_DATA__USE_CASE_LIST);
        }
        return useCaseList;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case RMDataPackage.RM_DATA__PROJECT_LIST:
                return ((InternalEList<?>) getProjectList()).basicRemove(otherEnd, msgs);
            case RMDataPackage.RM_DATA__REQUIREMENT_LIST:
                return ((InternalEList<?>) getRequirementList()).basicRemove(otherEnd, msgs);
            case RMDataPackage.RM_DATA__USE_CASE_LIST:
                return ((InternalEList<?>) getUseCaseList()).basicRemove(otherEnd, msgs);
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
            case RMDataPackage.RM_DATA__PROJECT_LIST:
                return getProjectList();
            case RMDataPackage.RM_DATA__REQUIREMENT_LIST:
                return getRequirementList();
            case RMDataPackage.RM_DATA__USE_CASE_LIST:
                return getUseCaseList();
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
            case RMDataPackage.RM_DATA__PROJECT_LIST:
                getProjectList().clear();
                getProjectList().addAll((Collection<? extends RMObject>) newValue);
                return;
            case RMDataPackage.RM_DATA__REQUIREMENT_LIST:
                getRequirementList().clear();
                getRequirementList().addAll((Collection<? extends RMObject>) newValue);
                return;
            case RMDataPackage.RM_DATA__USE_CASE_LIST:
                getUseCaseList().clear();
                getUseCaseList().addAll((Collection<? extends RMObject>) newValue);
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
            case RMDataPackage.RM_DATA__PROJECT_LIST:
                getProjectList().clear();
                return;
            case RMDataPackage.RM_DATA__REQUIREMENT_LIST:
                getRequirementList().clear();
                return;
            case RMDataPackage.RM_DATA__USE_CASE_LIST:
                getUseCaseList().clear();
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
            case RMDataPackage.RM_DATA__PROJECT_LIST:
                return projectList != null && !projectList.isEmpty();
            case RMDataPackage.RM_DATA__REQUIREMENT_LIST:
                return requirementList != null && !requirementList.isEmpty();
            case RMDataPackage.RM_DATA__USE_CASE_LIST:
                return useCaseList != null && !useCaseList.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // RMDataImpl
