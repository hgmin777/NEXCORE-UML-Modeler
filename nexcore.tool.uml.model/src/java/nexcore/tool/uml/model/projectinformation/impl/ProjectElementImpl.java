/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.model.projectinformation.impl;

import nexcore.tool.uml.model.projectinformation.ProjectElement;
import nexcore.tool.uml.model.projectinformation.ProjectInformationPackage;
import nexcore.tool.uml.model.projectinformation.ProjectPhaseType;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.EAnnotationImpl;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Project Element</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link nexcore.tool.uml.model.projectinformation.impl.ProjectElementImpl#getProjectName
 * <em>Project Name</em>}</li>
 * <li>
 * {@link nexcore.tool.uml.model.projectinformation.impl.ProjectElementImpl#getProjectCode
 * <em>Project Code</em>}</li>
 * <li>
 * {@link nexcore.tool.uml.model.projectinformation.impl.ProjectElementImpl#getProjectPhase
 * <em>Project Phase</em>}</li>
 * <li>
 * {@link nexcore.tool.uml.model.projectinformation.impl.ProjectElementImpl#getRelatedRMProjectId
 * <em>Related RM Project Id</em>}</li>
 * <li>
 * {@link nexcore.tool.uml.model.projectinformation.impl.ProjectElementImpl#getModelVersion
 * <em>Model Version</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.projectinformation.impl</li>
 * <li>설  명 : ProjectElementImpl</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public class ProjectElementImpl extends EAnnotationImpl implements ProjectElement {
    /**
     * The default value of the '{@link #getProjectName() <em>Project Name</em>}
     * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getProjectName()
     * @generated
     * @ordered
     */
    protected static final String PROJECT_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getProjectName() <em>Project Name</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getProjectName()
     * @generated
     * @ordered
     */
    protected String projectName = PROJECT_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getProjectCode() <em>Project Code</em>}
     * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getProjectCode()
     * @generated
     * @ordered
     */
    protected static final String PROJECT_CODE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getProjectCode() <em>Project Code</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getProjectCode()
     * @generated
     * @ordered
     */
    protected String projectCode = PROJECT_CODE_EDEFAULT;

    /**
     * The default value of the '{@link #getProjectPhase()
     * <em>Project Phase</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getProjectPhase()
     * @generated
     * @ordered
     */
    protected static final ProjectPhaseType PROJECT_PHASE_EDEFAULT = ProjectPhaseType.LOGICAL_DESIGN;

    /**
     * The cached value of the '{@link #getProjectPhase()
     * <em>Project Phase</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getProjectPhase()
     * @generated
     * @ordered
     */
    protected ProjectPhaseType projectPhase = PROJECT_PHASE_EDEFAULT;

    /**
     * The default value of the '{@link #getRelatedRMProjectId()
     * <em>Related RM Project Id</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getRelatedRMProjectId()
     * @generated
     * @ordered
     */
    protected static final String RELATED_RM_PROJECT_ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getRelatedRMProjectId()
     * <em>Related RM Project Id</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getRelatedRMProjectId()
     * @generated
     * @ordered
     */
    protected String relatedRMProjectId = RELATED_RM_PROJECT_ID_EDEFAULT;

    /**
     * The default value of the '{@link #getModelVersion()
     * <em>Model Version</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getModelVersion()
     * @generated
     * @ordered
     */
    protected static final String MODEL_VERSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getModelVersion()
     * <em>Model Version</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getModelVersion()
     * @generated
     * @ordered
     */
    protected String modelVersion = MODEL_VERSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected ProjectElementImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ProjectInformationPackage.Literals.PROJECT_ELEMENT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setProjectName(String newProjectName) {
        String oldProjectName = projectName;
        projectName = newProjectName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                ProjectInformationPackage.PROJECT_ELEMENT__PROJECT_NAME,
                oldProjectName,
                projectName));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getProjectCode() {
        return projectCode;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setProjectCode(String newProjectCode) {
        String oldProjectCode = projectCode;
        projectCode = newProjectCode;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                ProjectInformationPackage.PROJECT_ELEMENT__PROJECT_CODE,
                oldProjectCode,
                projectCode));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ProjectPhaseType getProjectPhase() {
        return projectPhase;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setProjectPhase(ProjectPhaseType newProjectPhase) {
        ProjectPhaseType oldProjectPhase = projectPhase;
        projectPhase = newProjectPhase == null ? PROJECT_PHASE_EDEFAULT : newProjectPhase;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                ProjectInformationPackage.PROJECT_ELEMENT__PROJECT_PHASE,
                oldProjectPhase,
                projectPhase));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getRelatedRMProjectId() {
        return relatedRMProjectId;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setRelatedRMProjectId(String newRelatedRMProjectId) {
        String oldRelatedRMProjectId = relatedRMProjectId;
        relatedRMProjectId = newRelatedRMProjectId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                ProjectInformationPackage.PROJECT_ELEMENT__RELATED_RM_PROJECT_ID,
                oldRelatedRMProjectId,
                relatedRMProjectId));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getModelVersion() {
        return modelVersion;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setModelVersion(String newModelVersion) {
        String oldModelVersion = modelVersion;
        modelVersion = newModelVersion;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this,
                Notification.SET,
                ProjectInformationPackage.PROJECT_ELEMENT__MODEL_VERSION,
                oldModelVersion,
                modelVersion));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ProjectInformationPackage.PROJECT_ELEMENT__PROJECT_NAME:
                return getProjectName();
            case ProjectInformationPackage.PROJECT_ELEMENT__PROJECT_CODE:
                return getProjectCode();
            case ProjectInformationPackage.PROJECT_ELEMENT__PROJECT_PHASE:
                return getProjectPhase();
            case ProjectInformationPackage.PROJECT_ELEMENT__RELATED_RM_PROJECT_ID:
                return getRelatedRMProjectId();
            case ProjectInformationPackage.PROJECT_ELEMENT__MODEL_VERSION:
                return getModelVersion();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case ProjectInformationPackage.PROJECT_ELEMENT__PROJECT_NAME:
                setProjectName((String) newValue);
                return;
            case ProjectInformationPackage.PROJECT_ELEMENT__PROJECT_CODE:
                setProjectCode((String) newValue);
                return;
            case ProjectInformationPackage.PROJECT_ELEMENT__PROJECT_PHASE:
                setProjectPhase((ProjectPhaseType) newValue);
                return;
            case ProjectInformationPackage.PROJECT_ELEMENT__RELATED_RM_PROJECT_ID:
                setRelatedRMProjectId((String) newValue);
                return;
            case ProjectInformationPackage.PROJECT_ELEMENT__MODEL_VERSION:
                setModelVersion((String) newValue);
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
            case ProjectInformationPackage.PROJECT_ELEMENT__PROJECT_NAME:
                setProjectName(PROJECT_NAME_EDEFAULT);
                return;
            case ProjectInformationPackage.PROJECT_ELEMENT__PROJECT_CODE:
                setProjectCode(PROJECT_CODE_EDEFAULT);
                return;
            case ProjectInformationPackage.PROJECT_ELEMENT__PROJECT_PHASE:
                setProjectPhase(PROJECT_PHASE_EDEFAULT);
                return;
            case ProjectInformationPackage.PROJECT_ELEMENT__RELATED_RM_PROJECT_ID:
                setRelatedRMProjectId(RELATED_RM_PROJECT_ID_EDEFAULT);
                return;
            case ProjectInformationPackage.PROJECT_ELEMENT__MODEL_VERSION:
                setModelVersion(MODEL_VERSION_EDEFAULT);
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
            case ProjectInformationPackage.PROJECT_ELEMENT__PROJECT_NAME:
                return PROJECT_NAME_EDEFAULT == null ? projectName != null : !PROJECT_NAME_EDEFAULT.equals(projectName);
            case ProjectInformationPackage.PROJECT_ELEMENT__PROJECT_CODE:
                return PROJECT_CODE_EDEFAULT == null ? projectCode != null : !PROJECT_CODE_EDEFAULT.equals(projectCode);
            case ProjectInformationPackage.PROJECT_ELEMENT__PROJECT_PHASE:
                return projectPhase != PROJECT_PHASE_EDEFAULT;
            case ProjectInformationPackage.PROJECT_ELEMENT__RELATED_RM_PROJECT_ID:
                return RELATED_RM_PROJECT_ID_EDEFAULT == null ? relatedRMProjectId != null
                : !RELATED_RM_PROJECT_ID_EDEFAULT.equals(relatedRMProjectId);
            case ProjectInformationPackage.PROJECT_ELEMENT__MODEL_VERSION:
                return MODEL_VERSION_EDEFAULT == null ? modelVersion != null
                : !MODEL_VERSION_EDEFAULT.equals(modelVersion);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy())
            return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (ProjectName: ");
        result.append(projectName);
        result.append(", ProjectCode: ");
        result.append(projectCode);
        result.append(", ProjectPhase: ");
        result.append(projectPhase);
        result.append(", RelatedRMProjectId: ");
        result.append(relatedRMProjectId);
        result.append(", ModelVersion: ");
        result.append(modelVersion);
        result.append(')');
        return result.toString();
    }

} // ProjectElementImpl
