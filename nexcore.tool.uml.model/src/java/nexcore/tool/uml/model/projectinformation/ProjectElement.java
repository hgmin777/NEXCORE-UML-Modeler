/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.model.projectinformation;

import org.eclipse.emf.ecore.EAnnotation;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Project Element</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link nexcore.tool.uml.model.projectinformation.ProjectElement#getProjectName
 * <em>Project Name</em>}</li>
 * <li>
 * {@link nexcore.tool.uml.model.projectinformation.ProjectElement#getProjectCode
 * <em>Project Code</em>}</li>
 * <li>
 * {@link nexcore.tool.uml.model.projectinformation.ProjectElement#getProjectPhase
 * <em>Project Phase</em>}</li>
 * <li>
 * {@link nexcore.tool.uml.model.projectinformation.ProjectElement#getRelatedRMProjectId
 * <em>Related RM Project Id</em>}</li>
 * <li>
 * {@link nexcore.tool.uml.model.projectinformation.ProjectElement#getModelVersion
 * <em>Model Version</em>}</li>
 * </ul>
 * </p>
 * 
 * @see nexcore.tool.uml.model.projectinformation.ProjectInformationPackage#getProjectElement()
 * @model
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.projectinformation</li>
 * <li>설  명 : ProjectElement</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public interface ProjectElement extends EAnnotation {
    /**
     * Returns the value of the '<em><b>Project Name</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Project Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Project Name</em>' attribute.
     * @see #setProjectName(String)
     * @see nexcore.tool.uml.model.projectinformation.ProjectInformationPackage#getProjectElement_ProjectName()
     * @model
     * @generated
     */
    String getProjectName();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.uml.model.projectinformation.ProjectElement#getProjectName
     * <em>Project Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Project Name</em>' attribute.
     * @see #getProjectName()
     * @generated
     */
    void setProjectName(String value);

    /**
     * Returns the value of the '<em><b>Project Code</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Project Code</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Project Code</em>' attribute.
     * @see #setProjectCode(String)
     * @see nexcore.tool.uml.model.projectinformation.ProjectInformationPackage#getProjectElement_ProjectCode()
     * @model id="true"
     * @generated
     */
    String getProjectCode();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.uml.model.projectinformation.ProjectElement#getProjectCode
     * <em>Project Code</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Project Code</em>' attribute.
     * @see #getProjectCode()
     * @generated
     */
    void setProjectCode(String value);

    /**
     * Returns the value of the '<em><b>Project Phase</b></em>' attribute. The
     * default value is <code>"LogicalDesign"</code>. The literals are from the
     * enumeration
     * {@link nexcore.tool.uml.model.projectinformation.ProjectPhaseType}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Project Phase</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Project Phase</em>' attribute.
     * @see nexcore.tool.uml.model.projectinformation.ProjectPhaseType
     * @see #setProjectPhase(ProjectPhaseType)
     * @see nexcore.tool.uml.model.projectinformation.ProjectInformationPackage#getProjectElement_ProjectPhase()
     * @model default="LogicalDesign"
     * @generated
     */
    ProjectPhaseType getProjectPhase();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.uml.model.projectinformation.ProjectElement#getProjectPhase
     * <em>Project Phase</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Project Phase</em>' attribute.
     * @see nexcore.tool.uml.model.projectinformation.ProjectPhaseType
     * @see #getProjectPhase()
     * @generated
     */
    void setProjectPhase(ProjectPhaseType value);

    /**
     * Returns the value of the '<em><b>Related RM Project Id</b></em>'
     * attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Related RM Project Id</em>' attribute isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Related RM Project Id</em>' attribute.
     * @see #setRelatedRMProjectId(String)
     * @see nexcore.tool.uml.model.projectinformation.ProjectInformationPackage#getProjectElement_RelatedRMProjectId()
     * @model
     * @generated
     */
    String getRelatedRMProjectId();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.uml.model.projectinformation.ProjectElement#getRelatedRMProjectId
     * <em>Related RM Project Id</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Related RM Project Id</em>'
     *            attribute.
     * @see #getRelatedRMProjectId()
     * @generated
     */
    void setRelatedRMProjectId(String value);

    /**
     * Returns the value of the '<em><b>Model Version</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Model Version</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Model Version</em>' attribute.
     * @see #setModelVersion(String)
     * @see nexcore.tool.uml.model.projectinformation.ProjectInformationPackage#getProjectElement_ModelVersion()
     * @model
     * @generated
     */
    String getModelVersion();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.uml.model.projectinformation.ProjectElement#getModelVersion
     * <em>Model Version</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Model Version</em>' attribute.
     * @see #getModelVersion()
     * @generated
     */
    void setModelVersion(String value);

} // ProjectElement
