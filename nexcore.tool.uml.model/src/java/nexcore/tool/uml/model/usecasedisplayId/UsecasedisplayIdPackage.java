/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.model.usecasedisplayId;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see nexcore.tool.uml.model.usecasedisplayId.UsecasedisplayIdFactory
 * @model kind="package"
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.usecasedisplayId</li>
 * <li>설  명 : UsecasedisplayIdPackage</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public interface UsecasedisplayIdPackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "usecasedisplayId";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://nexcore.skcc.com/tools/uml/usecasedisplayId";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "ucdisplay";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    UsecasedisplayIdPackage eINSTANCE = nexcore.tool.uml.model.usecasedisplayId.impl.UsecasedisplayIdPackageImpl.init();

    /**
     * The meta object id for the '{@link nexcore.tool.uml.model.usecasedisplayId.impl.UseCaseDisplayIdImpl <em>Use Case Display Id</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see nexcore.tool.uml.model.usecasedisplayId.impl.UseCaseDisplayIdImpl
     * @see nexcore.tool.uml.model.usecasedisplayId.impl.UsecasedisplayIdPackageImpl#getUseCaseDisplayId()
     * @generated
     */
    int USE_CASE_DISPLAY_ID = 0;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USE_CASE_DISPLAY_ID__EANNOTATIONS = EcorePackage.EANNOTATION__EANNOTATIONS;

    /**
     * The feature id for the '<em><b>Source</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USE_CASE_DISPLAY_ID__SOURCE = EcorePackage.EANNOTATION__SOURCE;

    /**
     * The feature id for the '<em><b>Details</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USE_CASE_DISPLAY_ID__DETAILS = EcorePackage.EANNOTATION__DETAILS;

    /**
     * The feature id for the '<em><b>EModel Element</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USE_CASE_DISPLAY_ID__EMODEL_ELEMENT = EcorePackage.EANNOTATION__EMODEL_ELEMENT;

    /**
     * The feature id for the '<em><b>Contents</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USE_CASE_DISPLAY_ID__CONTENTS = EcorePackage.EANNOTATION__CONTENTS;

    /**
     * The feature id for the '<em><b>References</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USE_CASE_DISPLAY_ID__REFERENCES = EcorePackage.EANNOTATION__REFERENCES;

    /**
     * The feature id for the '<em><b>Display Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USE_CASE_DISPLAY_ID__DISPLAY_ID = EcorePackage.EANNOTATION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Use Case Display Id</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USE_CASE_DISPLAY_ID_FEATURE_COUNT = EcorePackage.EANNOTATION_FEATURE_COUNT + 1;


    /**
     * Returns the meta object for class '{@link nexcore.tool.uml.model.usecasedisplayId.UseCaseDisplayId <em>Use Case Display Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Use Case Display Id</em>'.
     * @see nexcore.tool.uml.model.usecasedisplayId.UseCaseDisplayId
     * @generated
     */
    EClass getUseCaseDisplayId();

    /**
     * Returns the meta object for the attribute '{@link nexcore.tool.uml.model.usecasedisplayId.UseCaseDisplayId#getDisplayId <em>Display Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Display Id</em>'.
     * @see nexcore.tool.uml.model.usecasedisplayId.UseCaseDisplayId#getDisplayId()
     * @see #getUseCaseDisplayId()
     * @generated
     */
    EAttribute getUseCaseDisplayId_DisplayId();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    UsecasedisplayIdFactory getUsecasedisplayIdFactory();

    /**
     * <!-- begin-user-doc -->
     * Defines literals for the meta objects that represent
     * <ul>
     *   <li>each class,</li>
     *   <li>each feature of each class,</li>
     *   <li>each enum,</li>
     *   <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '{@link nexcore.tool.uml.model.usecasedisplayId.impl.UseCaseDisplayIdImpl <em>Use Case Display Id</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see nexcore.tool.uml.model.usecasedisplayId.impl.UseCaseDisplayIdImpl
         * @see nexcore.tool.uml.model.usecasedisplayId.impl.UsecasedisplayIdPackageImpl#getUseCaseDisplayId()
         * @generated
         */
        EClass USE_CASE_DISPLAY_ID = eINSTANCE.getUseCaseDisplayId();

        /**
         * The meta object literal for the '<em><b>Display Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute USE_CASE_DISPLAY_ID__DISPLAY_ID = eINSTANCE.getUseCaseDisplayId_DisplayId();

    }

} //UsecasedisplayIdPackage
