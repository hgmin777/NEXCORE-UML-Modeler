/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.model.usecasedetail.impl;

import nexcore.tool.uml.model.usecasedetail.FlowObject;
import nexcore.tool.uml.model.usecasedetail.UseCaseDetail;
import nexcore.tool.uml.model.usecasedetail.UseCaseDetailFactory;
import nexcore.tool.uml.model.usecasedetail.UseCaseDetailPackage;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.usecasedetail.impl</li>
 * <li>설  명 : UseCaseDetailFactoryImpl</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class UseCaseDetailFactoryImpl extends EFactoryImpl implements UseCaseDetailFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public static UseCaseDetailFactory init() {
        try {
            UseCaseDetailFactory theUseCaseDetailFactory = (UseCaseDetailFactory) EPackage.Registry.INSTANCE.getEFactory("http://nexcore.skcc.com/tools/uml/usecasedetail");
            if (theUseCaseDetailFactory != null) {
                return theUseCaseDetailFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new UseCaseDetailFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public UseCaseDetailFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case UseCaseDetailPackage.USE_CASE_DETAIL:
                return createUseCaseDetail();
            case UseCaseDetailPackage.FLOW_OBJECT:
                return createFlowObject();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public UseCaseDetail createUseCaseDetail() {
        UseCaseDetailImpl useCaseDetail = new UseCaseDetailImpl();
        return useCaseDetail;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public FlowObject createFlowObject() {
        FlowObjectImpl flowObject = new FlowObjectImpl();
        return flowObject;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public UseCaseDetailPackage getUseCaseDetailPackage() {
        return (UseCaseDetailPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @deprecated
     * @generated
     */
    @Deprecated
    public static UseCaseDetailPackage getPackage() {
        return UseCaseDetailPackage.eINSTANCE;
    }

} // UseCaseDetailFactoryImpl
