/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */

package nexcore.tool.mda.model.developer.reverseTransformation.util;

import nexcore.tool.mda.model.developer.reverseTransformation.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see nexcore.tool.mda.model.developer.reverseTransformation.ReverseTransformationPackage
 * @generated
 */
public class ReverseTransformationAdapterFactory extends AdapterFactoryImpl {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "";

    /**
     * The cached model package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static ReverseTransformationPackage modelPackage;

    /**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ReverseTransformationAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = ReverseTransformationPackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc -->
     * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
     * <!-- end-user-doc -->
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(Object object) {
        if (object == modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject)object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ReverseTransformationSwitch<Adapter> modelSwitch =
        new ReverseTransformationSwitch<Adapter>() {
            @Override
            public Adapter caseMDAReverseRuleSet(MDAReverseRuleSet object) {
                return createMDAReverseRuleSetAdapter();
            }
            @Override
            public Adapter caseClassRule(ClassRule object) {
                return createClassRuleAdapter();
            }
            @Override
            public Adapter caseDiagramRule(DiagramRule object) {
                return createDiagramRuleAdapter();
            }
            @Override
            public Adapter caseClassDiagramRule(ClassDiagramRule object) {
                return createClassDiagramRuleAdapter();
            }
            @Override
            public Adapter caseElementRule(ElementRule object) {
                return createElementRuleAdapter();
            }
            @Override
            public Adapter caseSequenceDiagramRule(SequenceDiagramRule object) {
                return createSequenceDiagramRuleAdapter();
            }
            @Override
            public Adapter defaultCase(EObject object) {
                return createEObjectAdapter();
            }
        };

    /**
     * Creates an adapter for the <code>target</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param target the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject)target);
    }


    /**
     * Creates a new adapter for an object of class '{@link nexcore.tool.mda.model.developer.reverseTransformation.MDAReverseRuleSet <em>MDA Reverse Rule Set</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see nexcore.tool.mda.model.developer.reverseTransformation.MDAReverseRuleSet
     * @generated
     */
    public Adapter createMDAReverseRuleSetAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link nexcore.tool.mda.model.developer.reverseTransformation.ClassRule <em>Class Rule</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see nexcore.tool.mda.model.developer.reverseTransformation.ClassRule
     * @generated
     */
    public Adapter createClassRuleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link nexcore.tool.mda.model.developer.reverseTransformation.DiagramRule <em>Diagram Rule</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see nexcore.tool.mda.model.developer.reverseTransformation.DiagramRule
     * @generated
     */
    public Adapter createDiagramRuleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link nexcore.tool.mda.model.developer.reverseTransformation.ClassDiagramRule <em>Class Diagram Rule</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see nexcore.tool.mda.model.developer.reverseTransformation.ClassDiagramRule
     * @generated
     */
    public Adapter createClassDiagramRuleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link nexcore.tool.mda.model.developer.reverseTransformation.ElementRule <em>Element Rule</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see nexcore.tool.mda.model.developer.reverseTransformation.ElementRule
     * @generated
     */
    public Adapter createElementRuleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link nexcore.tool.mda.model.developer.reverseTransformation.SequenceDiagramRule <em>Sequence Diagram Rule</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see nexcore.tool.mda.model.developer.reverseTransformation.SequenceDiagramRule
     * @generated
     */
    public Adapter createSequenceDiagramRuleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case.
     * <!-- begin-user-doc -->
     * This default implementation returns null.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} //ReverseTransformationAdapterFactory
