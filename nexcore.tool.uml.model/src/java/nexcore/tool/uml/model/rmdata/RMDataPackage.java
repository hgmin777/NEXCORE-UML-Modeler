/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.model.rmdata;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
 * @see nexcore.tool.uml.model.rmdata.RMDataFactory
 * @model kind="package"
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.rmdata</li>
 * <li>설  명 : RMDataPackage</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public interface RMDataPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNAME = "rmdata";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_URI = "http://nexcore.skcc.com/tools/uml/rmdata";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_PREFIX = "rmd";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    RMDataPackage eINSTANCE = nexcore.tool.uml.model.rmdata.impl.RMDataPackageImpl.init();

    /**
     * The meta object id for the '
     * {@link nexcore.tool.uml.model.rmdata.impl.RMDataImpl <em>RM Data</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see nexcore.tool.uml.model.rmdata.impl.RMDataImpl
     * @see nexcore.tool.uml.model.rmdata.impl.RMDataPackageImpl#getRMData()
     * @generated
     */
    int RM_DATA = 0;

    /**
     * The feature id for the '<em><b>Project List</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RM_DATA__PROJECT_LIST = 0;

    /**
     * The feature id for the '<em><b>Requirement List</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RM_DATA__REQUIREMENT_LIST = 1;

    /**
     * The feature id for the '<em><b>Use Case List</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RM_DATA__USE_CASE_LIST = 2;

    /**
     * The number of structural features of the '<em>RM Data</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RM_DATA_FEATURE_COUNT = 3;

    /**
     * The meta object id for the '
     * {@link nexcore.tool.uml.model.rmdata.impl.RMObjectImpl
     * <em>RM Object</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see nexcore.tool.uml.model.rmdata.impl.RMObjectImpl
     * @see nexcore.tool.uml.model.rmdata.impl.RMDataPackageImpl#getRMObject()
     * @generated
     */
    int RM_OBJECT = 1;

    /**
     * The feature id for the '<em><b>RM Object Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RM_OBJECT__RM_OBJECT_ID = 0;

    /**
     * The feature id for the '<em><b>RM Object Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RM_OBJECT__RM_OBJECT_NAME = 1;

    /**
     * The feature id for the '<em><b>Parent Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RM_OBJECT__PARENT_ID = 2;

    /**
     * The number of structural features of the '<em>RM Object</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RM_OBJECT_FEATURE_COUNT = 3;

    /**
     * Returns the meta object for class '
     * {@link nexcore.tool.uml.model.rmdata.RMData <em>RM Data</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>RM Data</em>'.
     * @see nexcore.tool.uml.model.rmdata.RMData
     * @generated
     */
    EClass getRMData();

    /**
     * Returns the meta object for the containment reference list '
     * {@link nexcore.tool.uml.model.rmdata.RMData#getProjectList
     * <em>Project List</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Project List</em>'.
     * @see nexcore.tool.uml.model.rmdata.RMData#getProjectList()
     * @see #getRMData()
     * @generated
     */
    EReference getRMData_ProjectList();

    /**
     * Returns the meta object for the containment reference list '
     * {@link nexcore.tool.uml.model.rmdata.RMData#getRequirementList
     * <em>Requirement List</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Requirement List</em>'.
     * @see nexcore.tool.uml.model.rmdata.RMData#getRequirementList()
     * @see #getRMData()
     * @generated
     */
    EReference getRMData_RequirementList();

    /**
     * Returns the meta object for the containment reference list '
     * {@link nexcore.tool.uml.model.rmdata.RMData#getUseCaseList
     * <em>Use Case List</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Use Case List</em>'.
     * @see nexcore.tool.uml.model.rmdata.RMData#getUseCaseList()
     * @see #getRMData()
     * @generated
     */
    EReference getRMData_UseCaseList();

    /**
     * Returns the meta object for class '
     * {@link nexcore.tool.uml.model.rmdata.RMObject <em>RM Object</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>RM Object</em>'.
     * @see nexcore.tool.uml.model.rmdata.RMObject
     * @generated
     */
    EClass getRMObject();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.uml.model.rmdata.RMObject#getRMObjectId
     * <em>RM Object Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>RM Object Id</em>'.
     * @see nexcore.tool.uml.model.rmdata.RMObject#getRMObjectId()
     * @see #getRMObject()
     * @generated
     */
    EAttribute getRMObject_RMObjectId();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.uml.model.rmdata.RMObject#getRMObjectName
     * <em>RM Object Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>RM Object Name</em>'.
     * @see nexcore.tool.uml.model.rmdata.RMObject#getRMObjectName()
     * @see #getRMObject()
     * @generated
     */
    EAttribute getRMObject_RMObjectName();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.uml.model.rmdata.RMObject#getParentId
     * <em>Parent Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Parent Id</em>'.
     * @see nexcore.tool.uml.model.rmdata.RMObject#getParentId()
     * @see #getRMObject()
     * @generated
     */
    EAttribute getRMObject_ParentId();

    /**
     * Returns the factory that creates the instances of the model. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the factory that creates the instances of the model.
     * @generated
     */
    RMDataFactory getRMDataFactory();

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
         * {@link nexcore.tool.uml.model.rmdata.impl.RMDataImpl
         * <em>RM Data</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @see nexcore.tool.uml.model.rmdata.impl.RMDataImpl
         * @see nexcore.tool.uml.model.rmdata.impl.RMDataPackageImpl#getRMData()
         * @generated
         */
        EClass RM_DATA = eINSTANCE.getRMData();

        /**
         * The meta object literal for the '<em><b>Project List</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference RM_DATA__PROJECT_LIST = eINSTANCE.getRMData_ProjectList();

        /**
         * The meta object literal for the '<em><b>Requirement List</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference RM_DATA__REQUIREMENT_LIST = eINSTANCE.getRMData_RequirementList();

        /**
         * The meta object literal for the '<em><b>Use Case List</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference RM_DATA__USE_CASE_LIST = eINSTANCE.getRMData_UseCaseList();

        /**
         * The meta object literal for the '
         * {@link nexcore.tool.uml.model.rmdata.impl.RMObjectImpl
         * <em>RM Object</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @see nexcore.tool.uml.model.rmdata.impl.RMObjectImpl
         * @see nexcore.tool.uml.model.rmdata.impl.RMDataPackageImpl#getRMObject()
         * @generated
         */
        EClass RM_OBJECT = eINSTANCE.getRMObject();

        /**
         * The meta object literal for the '<em><b>RM Object Id</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute RM_OBJECT__RM_OBJECT_ID = eINSTANCE.getRMObject_RMObjectId();

        /**
         * The meta object literal for the '<em><b>RM Object Name</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute RM_OBJECT__RM_OBJECT_NAME = eINSTANCE.getRMObject_RMObjectName();

        /**
         * The meta object literal for the '<em><b>Parent Id</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute RM_OBJECT__PARENT_ID = eINSTANCE.getRMObject_ParentId();

    }

} // RMDataPackage
