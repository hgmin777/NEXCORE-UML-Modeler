/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.model.umlfragment.impl;

import nexcore.tool.uml.model.umlfragment.DiagramGrabber;
import nexcore.tool.uml.model.umlfragment.UMLFragmentFactory;
import nexcore.tool.uml.model.umlfragment.UMLFragmentPackage;

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
 * <li>서브 업무명 : nexcore.tool.uml.model.umlfragment.impl</li>
 * <li>설  명 : UMLFragmentFactoryImpl</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class UMLFragmentFactoryImpl extends EFactoryImpl implements UMLFragmentFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public static UMLFragmentFactory init() {
        try {
            UMLFragmentFactory theUMLFragmentFactory = (UMLFragmentFactory) EPackage.Registry.INSTANCE.getEFactory("http://nexcore.skcc.com/tools/uml/umlfragment");
            if (theUMLFragmentFactory != null) {
                return theUMLFragmentFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new UMLFragmentFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public UMLFragmentFactoryImpl() {
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
            case UMLFragmentPackage.DIAGRAM_GRABBER:
                return createDiagramGrabber();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public DiagramGrabber createDiagramGrabber() {
        DiagramGrabberImpl diagramGrabber = new DiagramGrabberImpl();
        return diagramGrabber;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public UMLFragmentPackage getUMLFragmentPackage() {
        return (UMLFragmentPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @deprecated
     * @generated
     */
    @Deprecated
    public static UMLFragmentPackage getPackage() {
        return UMLFragmentPackage.eINSTANCE;
    }

} // UMLFragmentFactoryImpl
