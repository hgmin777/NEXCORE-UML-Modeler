/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.model.rmdata;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>RM Data</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link nexcore.tool.uml.model.rmdata.RMData#getProjectList <em>Project
 * List</em>}</li>
 * <li>{@link nexcore.tool.uml.model.rmdata.RMData#getRequirementList <em>
 * Requirement List</em>}</li>
 * <li>{@link nexcore.tool.uml.model.rmdata.RMData#getUseCaseList <em>Use Case
 * List</em>}</li>
 * </ul>
 * </p>
 * 
 * @see nexcore.tool.uml.model.rmdata.RMDataPackage#getRMData()
 * @model
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.rmdata</li>
 * <li>설  명 : RMData</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public interface RMData extends EObject {
    /**
     * Returns the value of the '<em><b>Project List</b></em>' containment
     * reference list. The list contents are of type
     * {@link nexcore.tool.uml.model.rmdata.RMObject}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Project List</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Project List</em>' containment reference
     *         list.
     * @see nexcore.tool.uml.model.rmdata.RMDataPackage#getRMData_ProjectList()
     * @model containment="true"
     * @generated
     */
    EList<RMObject> getProjectList();

    /**
     * Returns the value of the '<em><b>Requirement List</b></em>' containment
     * reference list. The list contents are of type
     * {@link nexcore.tool.uml.model.rmdata.RMObject}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Requirement List</em>' containment reference
     * list isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Requirement List</em>' containment
     *         reference list.
     * @see nexcore.tool.uml.model.rmdata.RMDataPackage#getRMData_RequirementList()
     * @model containment="true"
     * @generated
     */
    EList<RMObject> getRequirementList();

    /**
     * Returns the value of the '<em><b>Use Case List</b></em>' containment
     * reference list. The list contents are of type
     * {@link nexcore.tool.uml.model.rmdata.RMObject}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Use Case List</em>' containment reference list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Use Case List</em>' containment reference
     *         list.
     * @see nexcore.tool.uml.model.rmdata.RMDataPackage#getRMData_UseCaseList()
     * @model containment="true"
     * @generated
     */
    EList<RMObject> getUseCaseList();

} // RMData
