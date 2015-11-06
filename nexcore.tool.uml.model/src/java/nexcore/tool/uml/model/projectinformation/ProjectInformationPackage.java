/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.model.projectinformation;

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
 * @see nexcore.tool.uml.model.projectinformation.ProjectInformationFactory
 * @model kind="package"
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.projectinformation</li>
 * <li>설  명 : ProjectInformationPackage</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public interface ProjectInformationPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNAME = "projectinformation";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_URI = "http://nexcore.skcc.com/tools/uml/projectinformation";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_PREFIX = "pi";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    ProjectInformationPackage eINSTANCE = nexcore.tool.uml.model.projectinformation.impl.ProjectInformationPackageImpl.init();

    /**
     * The meta object id for the '
     * {@link nexcore.tool.uml.model.projectinformation.impl.ProjectElementImpl
     * <em>Project Element</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see nexcore.tool.uml.model.projectinformation.impl.ProjectElementImpl
     * @see nexcore.tool.uml.model.projectinformation.impl.ProjectInformationPackageImpl#getProjectElement()
     * @generated
     */
    int PROJECT_ELEMENT = 0;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PROJECT_ELEMENT__EANNOTATIONS = EcorePackage.EANNOTATION__EANNOTATIONS;

    /**
     * The feature id for the '<em><b>Source</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PROJECT_ELEMENT__SOURCE = EcorePackage.EANNOTATION__SOURCE;

    /**
     * The feature id for the '<em><b>Details</b></em>' map. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PROJECT_ELEMENT__DETAILS = EcorePackage.EANNOTATION__DETAILS;

    /**
     * The feature id for the '<em><b>EModel Element</b></em>' container
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PROJECT_ELEMENT__EMODEL_ELEMENT = EcorePackage.EANNOTATION__EMODEL_ELEMENT;

    /**
     * The feature id for the '<em><b>Contents</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PROJECT_ELEMENT__CONTENTS = EcorePackage.EANNOTATION__CONTENTS;

    /**
     * The feature id for the '<em><b>References</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PROJECT_ELEMENT__REFERENCES = EcorePackage.EANNOTATION__REFERENCES;

    /**
     * The feature id for the '<em><b>Project Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PROJECT_ELEMENT__PROJECT_NAME = EcorePackage.EANNOTATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Project Code</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PROJECT_ELEMENT__PROJECT_CODE = EcorePackage.EANNOTATION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Project Phase</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PROJECT_ELEMENT__PROJECT_PHASE = EcorePackage.EANNOTATION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Related RM Project Id</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PROJECT_ELEMENT__RELATED_RM_PROJECT_ID = EcorePackage.EANNOTATION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Model Version</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PROJECT_ELEMENT__MODEL_VERSION = EcorePackage.EANNOTATION_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '<em>Project Element</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PROJECT_ELEMENT_FEATURE_COUNT = EcorePackage.EANNOTATION_FEATURE_COUNT + 5;

    /**
     * The meta object id for the '
     * {@link nexcore.tool.uml.model.projectinformation.ProjectPhaseType
     * <em>Project Phase Type</em>}' enum. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see nexcore.tool.uml.model.projectinformation.ProjectPhaseType
     * @see nexcore.tool.uml.model.projectinformation.impl.ProjectInformationPackageImpl#getProjectPhaseType()
     * @generated
     */
    int PROJECT_PHASE_TYPE = 1;

    /**
     * Returns the meta object for class '
     * {@link nexcore.tool.uml.model.projectinformation.ProjectElement
     * <em>Project Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Project Element</em>'.
     * @see nexcore.tool.uml.model.projectinformation.ProjectElement
     * @generated
     */
    EClass getProjectElement();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.uml.model.projectinformation.ProjectElement#getProjectName
     * <em>Project Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Project Name</em>'.
     * @see nexcore.tool.uml.model.projectinformation.ProjectElement#getProjectName()
     * @see #getProjectElement()
     * @generated
     */
    EAttribute getProjectElement_ProjectName();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.uml.model.projectinformation.ProjectElement#getProjectCode
     * <em>Project Code</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Project Code</em>'.
     * @see nexcore.tool.uml.model.projectinformation.ProjectElement#getProjectCode()
     * @see #getProjectElement()
     * @generated
     */
    EAttribute getProjectElement_ProjectCode();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.uml.model.projectinformation.ProjectElement#getProjectPhase
     * <em>Project Phase</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Project Phase</em>'.
     * @see nexcore.tool.uml.model.projectinformation.ProjectElement#getProjectPhase()
     * @see #getProjectElement()
     * @generated
     */
    EAttribute getProjectElement_ProjectPhase();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.uml.model.projectinformation.ProjectElement#getRelatedRMProjectId
     * <em>Related RM Project Id</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Related RM Project Id</em>
     *         '.
     * @see nexcore.tool.uml.model.projectinformation.ProjectElement#getRelatedRMProjectId()
     * @see #getProjectElement()
     * @generated
     */
    EAttribute getProjectElement_RelatedRMProjectId();

    /**
     * Returns the meta object for the attribute '
     * {@link nexcore.tool.uml.model.projectinformation.ProjectElement#getModelVersion
     * <em>Model Version</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Model Version</em>'.
     * @see nexcore.tool.uml.model.projectinformation.ProjectElement#getModelVersion()
     * @see #getProjectElement()
     * @generated
     */
    EAttribute getProjectElement_ModelVersion();

    /**
     * Returns the meta object for enum '
     * {@link nexcore.tool.uml.model.projectinformation.ProjectPhaseType
     * <em>Project Phase Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for enum '<em>Project Phase Type</em>'.
     * @see nexcore.tool.uml.model.projectinformation.ProjectPhaseType
     * @generated
     */
    EEnum getProjectPhaseType();

    /**
     * Returns the factory that creates the instances of the model. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the factory that creates the instances of the model.
     * @generated
     */
    ProjectInformationFactory getProjectInformationFactory();

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
         * {@link nexcore.tool.uml.model.projectinformation.impl.ProjectElementImpl
         * <em>Project Element</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see nexcore.tool.uml.model.projectinformation.impl.ProjectElementImpl
         * @see nexcore.tool.uml.model.projectinformation.impl.ProjectInformationPackageImpl#getProjectElement()
         * @generated
         */
        EClass PROJECT_ELEMENT = eINSTANCE.getProjectElement();

        /**
         * The meta object literal for the '<em><b>Project Name</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PROJECT_ELEMENT__PROJECT_NAME = eINSTANCE.getProjectElement_ProjectName();

        /**
         * The meta object literal for the '<em><b>Project Code</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PROJECT_ELEMENT__PROJECT_CODE = eINSTANCE.getProjectElement_ProjectCode();

        /**
         * The meta object literal for the '<em><b>Project Phase</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PROJECT_ELEMENT__PROJECT_PHASE = eINSTANCE.getProjectElement_ProjectPhase();

        /**
         * The meta object literal for the '
         * <em><b>Related RM Project Id</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PROJECT_ELEMENT__RELATED_RM_PROJECT_ID = eINSTANCE.getProjectElement_RelatedRMProjectId();

        /**
         * The meta object literal for the '<em><b>Model Version</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PROJECT_ELEMENT__MODEL_VERSION = eINSTANCE.getProjectElement_ModelVersion();

        /**
         * The meta object literal for the '
         * {@link nexcore.tool.uml.model.projectinformation.ProjectPhaseType
         * <em>Project Phase Type</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see nexcore.tool.uml.model.projectinformation.ProjectPhaseType
         * @see nexcore.tool.uml.model.projectinformation.impl.ProjectInformationPackageImpl#getProjectPhaseType()
         * @generated
         */
        EEnum PROJECT_PHASE_TYPE = eINSTANCE.getProjectPhaseType();

    }

} // ProjectInformationPackage
