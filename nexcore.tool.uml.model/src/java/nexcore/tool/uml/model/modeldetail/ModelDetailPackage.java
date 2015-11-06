/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.model.modeldetail;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
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
 * @see nexcore.tool.uml.model.modeldetail.ModelDetailFactory
 * @model kind="package"
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.modeldetail</li>
 * <li>설  명 : ModelDetailPackage</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public interface ModelDetailPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNAME = "modeldetail";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_URI = "http://nexcore.skcc.com/tools/uml/modeldetail";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_PREFIX = "mdt";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    ModelDetailPackage eINSTANCE = nexcore.tool.uml.model.modeldetail.impl.ModelDetailPackageImpl.init();

    /**
     * The meta object id for the '
     * {@link nexcore.tool.uml.model.modeldetail.impl.ModelDetailImpl
     * <em>Model Detail</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see nexcore.tool.uml.model.modeldetail.impl.ModelDetailImpl
     * @see nexcore.tool.uml.model.modeldetail.impl.ModelDetailPackageImpl#getModelDetail()
     * @generated
     */
    int MODEL_DETAIL = 0;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MODEL_DETAIL__EANNOTATIONS = EcorePackage.EANNOTATION__EANNOTATIONS;

    /**
     * The feature id for the '<em><b>Source</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MODEL_DETAIL__SOURCE = EcorePackage.EANNOTATION__SOURCE;

    /**
     * The feature id for the '<em><b>Details</b></em>' map. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MODEL_DETAIL__DETAILS = EcorePackage.EANNOTATION__DETAILS;

    /**
     * The feature id for the '<em><b>EModel Element</b></em>' container
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MODEL_DETAIL__EMODEL_ELEMENT = EcorePackage.EANNOTATION__EMODEL_ELEMENT;

    /**
     * The feature id for the '<em><b>Contents</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MODEL_DETAIL__CONTENTS = EcorePackage.EANNOTATION__CONTENTS;

    /**
     * The feature id for the '<em><b>References</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MODEL_DETAIL__REFERENCES = EcorePackage.EANNOTATION__REFERENCES;

    /**
     * The feature id for the '<em><b>Model Type</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MODEL_DETAIL__MODEL_TYPE = EcorePackage.EANNOTATION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Model Detail</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MODEL_DETAIL_FEATURE_COUNT = EcorePackage.EANNOTATION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link nexcore.tool.uml.model.modeldetail.ModelType <em>Model Type</em>}'
     * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see nexcore.tool.uml.model.modeldetail.ModelType
     * @see nexcore.tool.uml.model.modeldetail.impl.ModelDetailPackageImpl#getModelType()
     * @generated
     */
    int MODEL_TYPE = 1;

    /**
     * Returns the meta object for class '
     * {@link nexcore.tool.uml.model.modeldetail.ModelDetail
     * <em>Model Detail</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Model Detail</em>'.
     * @see nexcore.tool.uml.model.modeldetail.ModelDetail
     * @generated
     */
    EClass getModelDetail();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.uml.model.modeldetail.ModelDetail#getModelType
     * <em>Model Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Model Type</em>'.
     * @see nexcore.tool.uml.model.modeldetail.ModelDetail#getModelType()
     * @see #getModelDetail()
     * @generated
     */
    EAttribute getModelDetail_ModelType();

    /**
     * Returns the meta object for enum '
     * {@link nexcore.tool.uml.model.modeldetail.ModelType <em>Model Type</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>Model Type</em>'.
     * @see nexcore.tool.uml.model.modeldetail.ModelType
     * @generated
     */
    EEnum getModelType();

    /**
     * Returns the factory that creates the instances of the model. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the factory that creates the instances of the model.
     * @generated
     */
    ModelDetailFactory getModelDetailFactory();

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
         * {@link nexcore.tool.uml.model.modeldetail.impl.ModelDetailImpl
         * <em>Model Detail</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see nexcore.tool.uml.model.modeldetail.impl.ModelDetailImpl
         * @see nexcore.tool.uml.model.modeldetail.impl.ModelDetailPackageImpl#getModelDetail()
         * @generated
         */
        EClass MODEL_DETAIL = eINSTANCE.getModelDetail();

        /**
         * The meta object literal for the '<em><b>Model Type</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute MODEL_DETAIL__MODEL_TYPE = eINSTANCE.getModelDetail_ModelType();

        /**
         * The meta object literal for the '
         * {@link nexcore.tool.uml.model.modeldetail.ModelType
         * <em>Model Type</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @see nexcore.tool.uml.model.modeldetail.ModelType
         * @see nexcore.tool.uml.model.modeldetail.impl.ModelDetailPackageImpl#getModelType()
         * @generated
         */
        EEnum MODEL_TYPE = eINSTANCE.getModelType();

    }

} // ModelDetailPackage
