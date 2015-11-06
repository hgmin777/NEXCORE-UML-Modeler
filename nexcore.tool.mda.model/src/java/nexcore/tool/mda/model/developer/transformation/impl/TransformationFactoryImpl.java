/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.mda.model.developer.transformation.impl;

import nexcore.tool.mda.model.developer.transformation.ClassRule;
import nexcore.tool.mda.model.developer.transformation.MDADevRuleSet;
import nexcore.tool.mda.model.developer.transformation.OperationRule;
import nexcore.tool.mda.model.developer.transformation.TargetObjectNameType;
import nexcore.tool.mda.model.developer.transformation.TargetProjectType;
import nexcore.tool.mda.model.developer.transformation.TransformationFactory;
import nexcore.tool.mda.model.developer.transformation.TransformationPackage;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
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
public class TransformationFactoryImpl extends EFactoryImpl implements TransformationFactory {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = "";

    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public static TransformationFactory init() {
        try {
            TransformationFactory theTransformationFactory = (TransformationFactory) EPackage.Registry.INSTANCE.getEFactory("http://nexcore.skcc.com/tools/mda/developer/transformation");
            if (theTransformationFactory != null) {
                return theTransformationFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new TransformationFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public TransformationFactoryImpl() {
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
            case TransformationPackage.MDA_DEV_RULE_SET:
                return createMDADevRuleSet();
            case TransformationPackage.CLASS_RULE:
                return createClassRule();
            case TransformationPackage.OPERATION_RULE:
                return createOperationRule();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object createFromString(EDataType eDataType, String initialValue) {
        switch (eDataType.getClassifierID()) {
            case TransformationPackage.TARGET_OBJECT_NAME_TYPE:
                return createTargetObjectNameTypeFromString(eDataType, initialValue);
            case TransformationPackage.TARGET_PROJECT_TYPE:
                return createTargetProjectTypeFromString(eDataType, initialValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName()
                    + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String convertToString(EDataType eDataType, Object instanceValue) {
        switch (eDataType.getClassifierID()) {
            case TransformationPackage.TARGET_OBJECT_NAME_TYPE:
                return convertTargetObjectNameTypeToString(eDataType, instanceValue);
            case TransformationPackage.TARGET_PROJECT_TYPE:
                return convertTargetProjectTypeToString(eDataType, instanceValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName()
                    + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public MDADevRuleSet createMDADevRuleSet() {
        MDADevRuleSetImpl mdaDevRuleSet = new MDADevRuleSetImpl();
        return mdaDevRuleSet;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ClassRule createClassRule() {
        ClassRuleImpl classRule = new ClassRuleImpl();
        return classRule;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public OperationRule createOperationRule() {
        OperationRuleImpl operationRule = new OperationRuleImpl();
        return operationRule;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public TargetObjectNameType createTargetObjectNameTypeFromString(EDataType eDataType, String initialValue) {
        TargetObjectNameType result = TargetObjectNameType.get(initialValue);
        if (result == null)
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '"
                + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertTargetObjectNameTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public TargetProjectType createTargetProjectTypeFromString(EDataType eDataType, String initialValue) {
        TargetProjectType result = TargetProjectType.get(initialValue);
        if (result == null)
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '"
                + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertTargetProjectTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public TransformationPackage getTransformationPackage() {
        return (TransformationPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @deprecated
     * @generated
     */
    @Deprecated
    public static TransformationPackage getPackage() {
        return TransformationPackage.eINSTANCE;
    }

} // TransformationFactoryImpl
