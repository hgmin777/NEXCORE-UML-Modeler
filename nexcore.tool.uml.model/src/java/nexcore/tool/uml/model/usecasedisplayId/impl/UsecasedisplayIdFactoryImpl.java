/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.model.usecasedisplayId.impl;

import nexcore.tool.uml.model.usecasedisplayId.UseCaseDisplayId;
import nexcore.tool.uml.model.usecasedisplayId.UsecasedisplayIdFactory;
import nexcore.tool.uml.model.usecasedisplayId.UsecasedisplayIdPackage;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.usecasedisplayId.impl</li>
 * <li>설  명 : UsecasedisplayIdFactoryImpl</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class UsecasedisplayIdFactoryImpl extends EFactoryImpl implements UsecasedisplayIdFactory {
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static UsecasedisplayIdFactory init() {
        try {
            UsecasedisplayIdFactory theUsecasedisplayIdFactory = (UsecasedisplayIdFactory)EPackage.Registry.INSTANCE.getEFactory("http://nexcore.skcc.com/tools/uml/usecasedisplayId"); 
            if (theUsecasedisplayIdFactory != null) {
                return theUsecasedisplayIdFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new UsecasedisplayIdFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public UsecasedisplayIdFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case UsecasedisplayIdPackage.USE_CASE_DISPLAY_ID: return createUseCaseDisplayId();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public UseCaseDisplayId createUseCaseDisplayId() {
        UseCaseDisplayIdImpl useCaseDisplayId = new UseCaseDisplayIdImpl();
        return useCaseDisplayId;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public UsecasedisplayIdPackage getUsecasedisplayIdPackage() {
        return (UsecasedisplayIdPackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static UsecasedisplayIdPackage getPackage() {
        return UsecasedisplayIdPackage.eINSTANCE;
    }

} //UsecasedisplayIdFactoryImpl
