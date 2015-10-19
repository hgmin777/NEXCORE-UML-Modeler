/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.model.umlfragment;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains
 * accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * 
 * @see nexcore.tool.uml.model.umlfragment.UMLFragmentFactory
 * @model kind="package"
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.umlfragment</li>
 * <li>설  명 : UMLFragmentPackage</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public interface UMLFragmentPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNAME = "umlfragment";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_URI = "http://nexcore.skcc.com/tools/uml/umlfragment";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_PREFIX = "umf";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    UMLFragmentPackage eINSTANCE = nexcore.tool.uml.model.umlfragment.impl.UMLFragmentPackageImpl.init();

    /**
     * The meta object id for the '
     * {@link nexcore.tool.uml.model.umlfragment.impl.DiagramGrabberImpl
     * <em>Diagram Grabber</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see nexcore.tool.uml.model.umlfragment.impl.DiagramGrabberImpl
     * @see nexcore.tool.uml.model.umlfragment.impl.UMLFragmentPackageImpl#getDiagramGrabber()
     * @generated
     */
    int DIAGRAM_GRABBER = 0;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_GRABBER__EANNOTATIONS = EcorePackage.EANNOTATION__EANNOTATIONS;

    /**
     * The feature id for the '<em><b>Source</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_GRABBER__SOURCE = EcorePackage.EANNOTATION__SOURCE;

    /**
     * The feature id for the '<em><b>Details</b></em>' map. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_GRABBER__DETAILS = EcorePackage.EANNOTATION__DETAILS;

    /**
     * The feature id for the '<em><b>EModel Element</b></em>' container
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_GRABBER__EMODEL_ELEMENT = EcorePackage.EANNOTATION__EMODEL_ELEMENT;

    /**
     * The feature id for the '<em><b>Contents</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_GRABBER__CONTENTS = EcorePackage.EANNOTATION__CONTENTS;

    /**
     * The feature id for the '<em><b>References</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_GRABBER__REFERENCES = EcorePackage.EANNOTATION__REFERENCES;

    /**
     * The feature id for the '<em><b>Grabbed Diagrams</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_GRABBER__GRABBED_DIAGRAMS = EcorePackage.EANNOTATION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Diagram Grabber</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_GRABBER_FEATURE_COUNT = EcorePackage.EANNOTATION_FEATURE_COUNT + 1;

    /**
     * Returns the meta object for class '
     * {@link nexcore.tool.uml.model.umlfragment.DiagramGrabber
     * <em>Diagram Grabber</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Diagram Grabber</em>'.
     * @see nexcore.tool.uml.model.umlfragment.DiagramGrabber
     * @generated
     */
    EClass getDiagramGrabber();

    /**
     * Returns the meta object for the reference list '
     * {@link nexcore.tool.uml.model.umlfragment.DiagramGrabber#getGrabbedDiagrams
     * <em>Grabbed Diagrams</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference list '<em>Grabbed Diagrams</em>
     *         '.
     * @see nexcore.tool.uml.model.umlfragment.DiagramGrabber#getGrabbedDiagrams()
     * @see #getDiagramGrabber()
     * @generated
     */
    EReference getDiagramGrabber_GrabbedDiagrams();

    /**
     * Returns the factory that creates the instances of the model. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the factory that creates the instances of the model.
     * @generated
     */
    UMLFragmentFactory getUMLFragmentFactory();

    /**
     * <!-- begin-user-doc --> Defines literals for the meta objects that
     * represent
     * <ul>
     * <li>each class,</li>
     * <li>each feature of each class,</li>
     * <li>each enum,</li>
     * <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '
         * {@link nexcore.tool.uml.model.umlfragment.impl.DiagramGrabberImpl
         * <em>Diagram Grabber</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see nexcore.tool.uml.model.umlfragment.impl.DiagramGrabberImpl
         * @see nexcore.tool.uml.model.umlfragment.impl.UMLFragmentPackageImpl#getDiagramGrabber()
         * @generated
         */
        EClass DIAGRAM_GRABBER = eINSTANCE.getDiagramGrabber();

        /**
         * The meta object literal for the '<em><b>Grabbed Diagrams</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DIAGRAM_GRABBER__GRABBED_DIAGRAMS = eINSTANCE.getDiagramGrabber_GrabbedDiagrams();

    }

} // UMLFragmentPackage
