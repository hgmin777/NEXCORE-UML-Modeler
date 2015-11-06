/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.model.modeldetail.impl;

import nexcore.tool.uml.model.modeldetail.ModelDetail;
import nexcore.tool.uml.model.modeldetail.ModelDetailPackage;
import nexcore.tool.uml.model.modeldetail.ModelType;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.EAnnotationImpl;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Model Detail</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link nexcore.tool.uml.model.modeldetail.impl.ModelDetailImpl#getModelType
 * <em>Model Type</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.modeldetail.impl</li>
 * <li>설  명 : ModelDetailImpl</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class ModelDetailImpl extends EAnnotationImpl implements ModelDetail {
    /**
     * The default value of the '{@link #getModelType() <em>Model Type</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getModelType()
     * @generated
     * @ordered
     */
    protected static final ModelType MODEL_TYPE_EDEFAULT = ModelType.GENERAL;

    /**
     * The cached value of the '{@link #getModelType() <em>Model Type</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getModelType()
     * @generated
     * @ordered
     */
    protected ModelType modelType = MODEL_TYPE_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected ModelDetailImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ModelDetailPackage.Literals.MODEL_DETAIL;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ModelType getModelType() {
        return modelType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setModelType(ModelType newModelType) {
        ModelType oldModelType = modelType;
        modelType = newModelType == null ? MODEL_TYPE_EDEFAULT : newModelType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                ModelDetailPackage.MODEL_DETAIL__MODEL_TYPE,
                oldModelType,
                modelType));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ModelDetailPackage.MODEL_DETAIL__MODEL_TYPE:
                return getModelType();
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
            case ModelDetailPackage.MODEL_DETAIL__MODEL_TYPE:
                setModelType((ModelType) newValue);
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
            case ModelDetailPackage.MODEL_DETAIL__MODEL_TYPE:
                setModelType(MODEL_TYPE_EDEFAULT);
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
            case ModelDetailPackage.MODEL_DETAIL__MODEL_TYPE:
                return modelType != MODEL_TYPE_EDEFAULT;
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
        result.append(" (ModelType: ");
        result.append(modelType);
        result.append(')');
        return result.toString();
    }

} // ModelDetailImpl
