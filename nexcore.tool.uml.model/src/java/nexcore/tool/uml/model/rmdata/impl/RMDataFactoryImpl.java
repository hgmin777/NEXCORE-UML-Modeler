/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.model.rmdata.impl;

import nexcore.tool.uml.model.rmdata.RMData;
import nexcore.tool.uml.model.rmdata.RMDataFactory;
import nexcore.tool.uml.model.rmdata.RMDataPackage;
import nexcore.tool.uml.model.rmdata.RMObject;

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
 * <li>서브 업무명 : nexcore.tool.uml.model.rmdata.impl</li>
 * <li>설  명 : RMDataFactoryImpl</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class RMDataFactoryImpl extends EFactoryImpl implements RMDataFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public static RMDataFactory init() {
        try {
            RMDataFactory theRMDataFactory = (RMDataFactory) EPackage.Registry.INSTANCE.getEFactory("http://nexcore.skcc.com/tools/uml/rmdata");
            if (theRMDataFactory != null) {
                return theRMDataFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new RMDataFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public RMDataFactoryImpl() {
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
            case RMDataPackage.RM_DATA:
                return createRMData();
            case RMDataPackage.RM_OBJECT:
                return createRMObject();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public RMData createRMData() {
        RMDataImpl rmData = new RMDataImpl();
        return rmData;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public RMObject createRMObject() {
        RMObjectImpl rmObject = new RMObjectImpl();
        return rmObject;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public RMDataPackage getRMDataPackage() {
        return (RMDataPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @deprecated
     * @generated
     */
    @Deprecated
    public static RMDataPackage getPackage() {
        return RMDataPackage.eINSTANCE;
    }

} // RMDataFactoryImpl
