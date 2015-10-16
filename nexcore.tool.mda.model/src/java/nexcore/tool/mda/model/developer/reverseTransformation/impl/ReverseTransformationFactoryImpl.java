/**
 * Copyright (c) 2012 SK C&C Co., Ltd. All rights reserved. 
 * 
 * This software is the confidential and proprietary information of SK C&C. 
 * You shall not disclose such confidential information and shall use it 
 * only in accordance with the terms of the license agreement you entered into 
 * with SK C&C.
 */
package nexcore.tool.mda.model.developer.reverseTransformation.impl;

import nexcore.tool.mda.model.developer.reverseTransformation.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
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
public class ReverseTransformationFactoryImpl extends EFactoryImpl implements ReverseTransformationFactory {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2012 SK C&C Co., Ltd. All rights reserved. \r\n\r\nThis software is the confidential and proprietary information of SK C&C. \r\nYou shall not disclose such confidential information and shall use it \r\nonly in accordance with the terms of the license agreement you entered into \r\nwith SK C&C.";

    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static ReverseTransformationFactory init() {
        try {
            ReverseTransformationFactory theReverseTransformationFactory = (ReverseTransformationFactory)EPackage.Registry.INSTANCE.getEFactory("http://nexcore.skcc.com/tools/mdac/reverseTransformation"); 
            if (theReverseTransformationFactory != null) {
                return theReverseTransformationFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new ReverseTransformationFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ReverseTransformationFactoryImpl() {
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
            case ReverseTransformationPackage.MDA_REVERSE_RULE_SET: return createMDAReverseRuleSet();
            case ReverseTransformationPackage.CLASS_RULE: return createClassRule();
            case ReverseTransformationPackage.DIAGRAM_RULE: return createDiagramRule();
            case ReverseTransformationPackage.CLASS_DIAGRAM_RULE: return createClassDiagramRule();
            case ReverseTransformationPackage.ELEMENT_RULE: return createElementRule();
            case ReverseTransformationPackage.SEQUENCE_DIAGRAM_RULE: return createSequenceDiagramRule();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object createFromString(EDataType eDataType, String initialValue) {
        switch (eDataType.getClassifierID()) {
            case ReverseTransformationPackage.SOURCE_PROJECT_TYPE:
                return createSourceProjectTypeFromString(eDataType, initialValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String convertToString(EDataType eDataType, Object instanceValue) {
        switch (eDataType.getClassifierID()) {
            case ReverseTransformationPackage.SOURCE_PROJECT_TYPE:
                return convertSourceProjectTypeToString(eDataType, instanceValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MDAReverseRuleSet createMDAReverseRuleSet() {
        MDAReverseRuleSetImpl mdaReverseRuleSet = new MDAReverseRuleSetImpl();
        return mdaReverseRuleSet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ClassRule createClassRule() {
        ClassRuleImpl classRule = new ClassRuleImpl();
        return classRule;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DiagramRule createDiagramRule() {
        DiagramRuleImpl diagramRule = new DiagramRuleImpl();
        return diagramRule;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ClassDiagramRule createClassDiagramRule() {
        ClassDiagramRuleImpl classDiagramRule = new ClassDiagramRuleImpl();
        return classDiagramRule;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ElementRule createElementRule() {
        ElementRuleImpl elementRule = new ElementRuleImpl();
        return elementRule;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SequenceDiagramRule createSequenceDiagramRule() {
        SequenceDiagramRuleImpl sequenceDiagramRule = new SequenceDiagramRuleImpl();
        return sequenceDiagramRule;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SourceProjectType createSourceProjectTypeFromString(EDataType eDataType, String initialValue) {
        SourceProjectType result = SourceProjectType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertSourceProjectTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ReverseTransformationPackage getReverseTransformationPackage() {
        return (ReverseTransformationPackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static ReverseTransformationPackage getPackage() {
        return ReverseTransformationPackage.eINSTANCE;
    }

} //ReverseTransformationFactoryImpl
