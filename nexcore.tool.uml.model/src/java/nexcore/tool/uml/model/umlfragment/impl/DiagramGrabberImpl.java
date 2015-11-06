/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.model.umlfragment.impl;

import java.util.Collection;

import nexcore.tool.uml.model.umlfragment.DiagramGrabber;
import nexcore.tool.uml.model.umlfragment.UMLFragmentPackage;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EAnnotationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Diagram Grabber</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link nexcore.tool.uml.model.umlfragment.impl.DiagramGrabberImpl#getGrabbedDiagrams
 * <em>Grabbed Diagrams</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.umlfragment.impl</li>
 * <li>설  명 : DiagramGrabberImpl</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class DiagramGrabberImpl extends EAnnotationImpl implements DiagramGrabber {
    /**
     * The cached value of the '{@link #getGrabbedDiagrams()
     * <em>Grabbed Diagrams</em>}' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getGrabbedDiagrams()
     * @generated
     * @ordered
     */
    protected EList<EObject> grabbedDiagrams;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected DiagramGrabberImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return UMLFragmentPackage.Literals.DIAGRAM_GRABBER;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<EObject> getGrabbedDiagrams() {
        if (grabbedDiagrams == null) {
            grabbedDiagrams = new EObjectResolvingEList<EObject>(EObject.class,
                this,
                UMLFragmentPackage.DIAGRAM_GRABBER__GRABBED_DIAGRAMS);
        }
        return grabbedDiagrams;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case UMLFragmentPackage.DIAGRAM_GRABBER__GRABBED_DIAGRAMS:
                return getGrabbedDiagrams();
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
            case UMLFragmentPackage.DIAGRAM_GRABBER__GRABBED_DIAGRAMS:
                getGrabbedDiagrams().clear();
                getGrabbedDiagrams().addAll((Collection<? extends EObject>) newValue);
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
            case UMLFragmentPackage.DIAGRAM_GRABBER__GRABBED_DIAGRAMS:
                getGrabbedDiagrams().clear();
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
            case UMLFragmentPackage.DIAGRAM_GRABBER__GRABBED_DIAGRAMS:
                return grabbedDiagrams != null && !grabbedDiagrams.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // DiagramGrabberImpl
