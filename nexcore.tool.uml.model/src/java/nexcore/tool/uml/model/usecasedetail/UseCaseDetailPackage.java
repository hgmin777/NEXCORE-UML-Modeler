/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.model.usecasedetail;

import org.eclipse.emf.ecore.EAttribute;
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
 * @see nexcore.tool.uml.model.usecasedetail.UseCaseDetailFactory
 * @model kind="package"
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.usecasedetail</li>
 * <li>설  명 : UseCaseDetailPackage</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public interface UseCaseDetailPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNAME = "usecasedetail";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_URI = "http://nexcore.skcc.com/tools/uml/usecasedetail";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_PREFIX = "ucd";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    UseCaseDetailPackage eINSTANCE = nexcore.tool.uml.model.usecasedetail.impl.UseCaseDetailPackageImpl.init();

    /**
     * The meta object id for the '
     * {@link nexcore.tool.uml.model.usecasedetail.impl.UseCaseDetailImpl
     * <em>Use Case Detail</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see nexcore.tool.uml.model.usecasedetail.impl.UseCaseDetailImpl
     * @see nexcore.tool.uml.model.usecasedetail.impl.UseCaseDetailPackageImpl#getUseCaseDetail()
     * @generated
     */
    int USE_CASE_DETAIL = 0;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USE_CASE_DETAIL__EANNOTATIONS = EcorePackage.EANNOTATION__EANNOTATIONS;

    /**
     * The feature id for the '<em><b>Source</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USE_CASE_DETAIL__SOURCE = EcorePackage.EANNOTATION__SOURCE;

    /**
     * The feature id for the '<em><b>Details</b></em>' map. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USE_CASE_DETAIL__DETAILS = EcorePackage.EANNOTATION__DETAILS;

    /**
     * The feature id for the '<em><b>EModel Element</b></em>' container
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USE_CASE_DETAIL__EMODEL_ELEMENT = EcorePackage.EANNOTATION__EMODEL_ELEMENT;

    /**
     * The feature id for the '<em><b>Contents</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USE_CASE_DETAIL__CONTENTS = EcorePackage.EANNOTATION__CONTENTS;

    /**
     * The feature id for the '<em><b>References</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USE_CASE_DETAIL__REFERENCES = EcorePackage.EANNOTATION__REFERENCES;

    /**
     * The feature id for the '<em><b>Pre Condition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USE_CASE_DETAIL__PRE_CONDITION = EcorePackage.EANNOTATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Post Condition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USE_CASE_DETAIL__POST_CONDITION = EcorePackage.EANNOTATION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Basic Flow List</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USE_CASE_DETAIL__BASIC_FLOW_LIST = EcorePackage.EANNOTATION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Sub Flow List</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USE_CASE_DETAIL__SUB_FLOW_LIST = EcorePackage.EANNOTATION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Exception Flow List</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USE_CASE_DETAIL__EXCEPTION_FLOW_LIST = EcorePackage.EANNOTATION_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '<em>Use Case Detail</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USE_CASE_DETAIL_FEATURE_COUNT = EcorePackage.EANNOTATION_FEATURE_COUNT + 5;

    /**
     * The meta object id for the '
     * {@link nexcore.tool.uml.model.usecasedetail.impl.FlowObjectImpl
     * <em>Flow Object</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see nexcore.tool.uml.model.usecasedetail.impl.FlowObjectImpl
     * @see nexcore.tool.uml.model.usecasedetail.impl.UseCaseDetailPackageImpl#getFlowObject()
     * @generated
     */
    int FLOW_OBJECT = 1;

    /**
     * The feature id for the '<em><b>Flow Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLOW_OBJECT__FLOW_ID = 0;

    /**
     * The feature id for the '<em><b>Flow Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLOW_OBJECT__FLOW_NAME = 1;

    /**
     * The feature id for the '<em><b>Flow Overview</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLOW_OBJECT__FLOW_OVERVIEW = 2;

    /**
     * The feature id for the '<em><b>Flow Description</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLOW_OBJECT__FLOW_DESCRIPTION = 3;

    /**
     * The number of structural features of the '<em>Flow Object</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLOW_OBJECT_FEATURE_COUNT = 4;

    /**
     * Returns the meta object for class '
     * {@link nexcore.tool.uml.model.usecasedetail.UseCaseDetail
     * <em>Use Case Detail</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Use Case Detail</em>'.
     * @see nexcore.tool.uml.model.usecasedetail.UseCaseDetail
     * @generated
     */
    EClass getUseCaseDetail();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.uml.model.usecasedetail.UseCaseDetail#getPreCondition
     * <em>Pre Condition</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Pre Condition</em>'.
     * @see nexcore.tool.uml.model.usecasedetail.UseCaseDetail#getPreCondition()
     * @see #getUseCaseDetail()
     * @generated
     */
    EAttribute getUseCaseDetail_PreCondition();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.uml.model.usecasedetail.UseCaseDetail#getPostCondition
     * <em>Post Condition</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Post Condition</em>'.
     * @see nexcore.tool.uml.model.usecasedetail.UseCaseDetail#getPostCondition()
     * @see #getUseCaseDetail()
     * @generated
     */
    EAttribute getUseCaseDetail_PostCondition();

    /**
     * Returns the meta object for the containment reference list '
     * {@link nexcore.tool.uml.model.usecasedetail.UseCaseDetail#getBasicFlowList
     * <em>Basic Flow List</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Basic Flow List</em>'.
     * @see nexcore.tool.uml.model.usecasedetail.UseCaseDetail#getBasicFlowList()
     * @see #getUseCaseDetail()
     * @generated
     */
    EReference getUseCaseDetail_BasicFlowList();

    /**
     * Returns the meta object for the containment reference list '
     * {@link nexcore.tool.uml.model.usecasedetail.UseCaseDetail#getSubFlowList
     * <em>Sub Flow List</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Sub Flow List</em>'.
     * @see nexcore.tool.uml.model.usecasedetail.UseCaseDetail#getSubFlowList()
     * @see #getUseCaseDetail()
     * @generated
     */
    EReference getUseCaseDetail_SubFlowList();

    /**
     * Returns the meta object for the containment reference list '
     * {@link nexcore.tool.uml.model.usecasedetail.UseCaseDetail#getExceptionFlowList
     * <em>Exception Flow List</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Exception Flow List</em>'.
     * @see nexcore.tool.uml.model.usecasedetail.UseCaseDetail#getExceptionFlowList()
     * @see #getUseCaseDetail()
     * @generated
     */
    EReference getUseCaseDetail_ExceptionFlowList();

    /**
     * Returns the meta object for class '
     * {@link nexcore.tool.uml.model.usecasedetail.FlowObject
     * <em>Flow Object</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Flow Object</em>'.
     * @see nexcore.tool.uml.model.usecasedetail.FlowObject
     * @generated
     */
    EClass getFlowObject();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.uml.model.usecasedetail.FlowObject#getFlowId
     * <em>Flow Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Flow Id</em>'.
     * @see nexcore.tool.uml.model.usecasedetail.FlowObject#getFlowId()
     * @see #getFlowObject()
     * @generated
     */
    EAttribute getFlowObject_FlowId();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.uml.model.usecasedetail.FlowObject#getFlowName
     * <em>Flow Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Flow Name</em>'.
     * @see nexcore.tool.uml.model.usecasedetail.FlowObject#getFlowName()
     * @see #getFlowObject()
     * @generated
     */
    EAttribute getFlowObject_FlowName();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.uml.model.usecasedetail.FlowObject#getFlowOverview
     * <em>Flow Overview</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Flow Overview</em>'.
     * @see nexcore.tool.uml.model.usecasedetail.FlowObject#getFlowOverview()
     * @see #getFlowObject()
     * @generated
     */
    EAttribute getFlowObject_FlowOverview();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.uml.model.usecasedetail.FlowObject#getFlowDescription
     * <em>Flow Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Flow Description</em>'.
     * @see nexcore.tool.uml.model.usecasedetail.FlowObject#getFlowDescription()
     * @see #getFlowObject()
     * @generated
     */
    EAttribute getFlowObject_FlowDescription();

    /**
     * Returns the factory that creates the instances of the model. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the factory that creates the instances of the model.
     * @generated
     */
    UseCaseDetailFactory getUseCaseDetailFactory();

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
         * {@link nexcore.tool.uml.model.usecasedetail.impl.UseCaseDetailImpl
         * <em>Use Case Detail</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see nexcore.tool.uml.model.usecasedetail.impl.UseCaseDetailImpl
         * @see nexcore.tool.uml.model.usecasedetail.impl.UseCaseDetailPackageImpl#getUseCaseDetail()
         * @generated
         */
        EClass USE_CASE_DETAIL = eINSTANCE.getUseCaseDetail();

        /**
         * The meta object literal for the '<em><b>Pre Condition</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute USE_CASE_DETAIL__PRE_CONDITION = eINSTANCE.getUseCaseDetail_PreCondition();

        /**
         * The meta object literal for the '<em><b>Post Condition</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute USE_CASE_DETAIL__POST_CONDITION = eINSTANCE.getUseCaseDetail_PostCondition();

        /**
         * The meta object literal for the '<em><b>Basic Flow List</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference USE_CASE_DETAIL__BASIC_FLOW_LIST = eINSTANCE.getUseCaseDetail_BasicFlowList();

        /**
         * The meta object literal for the '<em><b>Sub Flow List</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference USE_CASE_DETAIL__SUB_FLOW_LIST = eINSTANCE.getUseCaseDetail_SubFlowList();

        /**
         * The meta object literal for the '<em><b>Exception Flow List</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference USE_CASE_DETAIL__EXCEPTION_FLOW_LIST = eINSTANCE.getUseCaseDetail_ExceptionFlowList();

        /**
         * The meta object literal for the '
         * {@link nexcore.tool.uml.model.usecasedetail.impl.FlowObjectImpl
         * <em>Flow Object</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see nexcore.tool.uml.model.usecasedetail.impl.FlowObjectImpl
         * @see nexcore.tool.uml.model.usecasedetail.impl.UseCaseDetailPackageImpl#getFlowObject()
         * @generated
         */
        EClass FLOW_OBJECT = eINSTANCE.getFlowObject();

        /**
         * The meta object literal for the '<em><b>Flow Id</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute FLOW_OBJECT__FLOW_ID = eINSTANCE.getFlowObject_FlowId();

        /**
         * The meta object literal for the '<em><b>Flow Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute FLOW_OBJECT__FLOW_NAME = eINSTANCE.getFlowObject_FlowName();

        /**
         * The meta object literal for the '<em><b>Flow Overview</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute FLOW_OBJECT__FLOW_OVERVIEW = eINSTANCE.getFlowObject_FlowOverview();

        /**
         * The meta object literal for the '<em><b>Flow Description</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute FLOW_OBJECT__FLOW_DESCRIPTION = eINSTANCE.getFlowObject_FlowDescription();

    }

} // UseCaseDetailPackage
