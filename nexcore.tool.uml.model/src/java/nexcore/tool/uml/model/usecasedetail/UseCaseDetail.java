/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.model.usecasedetail;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Use Case Detail</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link nexcore.tool.uml.model.usecasedetail.UseCaseDetail#getPreCondition
 * <em>Pre Condition</em>}</li>
 * <li>
 * {@link nexcore.tool.uml.model.usecasedetail.UseCaseDetail#getPostCondition
 * <em>Post Condition</em>}</li>
 * <li>
 * {@link nexcore.tool.uml.model.usecasedetail.UseCaseDetail#getBasicFlowList
 * <em>Basic Flow List</em>}</li>
 * <li>{@link nexcore.tool.uml.model.usecasedetail.UseCaseDetail#getSubFlowList
 * <em>Sub Flow List</em>}</li>
 * <li>
 * {@link nexcore.tool.uml.model.usecasedetail.UseCaseDetail#getExceptionFlowList
 * <em>Exception Flow List</em>}</li>
 * </ul>
 * </p>
 * 
 * @see nexcore.tool.uml.model.usecasedetail.UseCaseDetailPackage#getUseCaseDetail()
 * @model
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.usecasedetail</li>
 * <li>설  명 : UseCaseDetail</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public interface UseCaseDetail extends EAnnotation {
    /**
     * Returns the value of the '<em><b>Pre Condition</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Pre Condition</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Pre Condition</em>' attribute.
     * @see #setPreCondition(String)
     * @see nexcore.tool.uml.model.usecasedetail.UseCaseDetailPackage#getUseCaseDetail_PreCondition()
     * @model
     * @generated
     */
    String getPreCondition();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.uml.model.usecasedetail.UseCaseDetail#getPreCondition
     * <em>Pre Condition</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Pre Condition</em>' attribute.
     * @see #getPreCondition()
     * @generated
     */
    void setPreCondition(String value);

    /**
     * Returns the value of the '<em><b>Post Condition</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Post Condition</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Post Condition</em>' attribute.
     * @see #setPostCondition(String)
     * @see nexcore.tool.uml.model.usecasedetail.UseCaseDetailPackage#getUseCaseDetail_PostCondition()
     * @model
     * @generated
     */
    String getPostCondition();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.uml.model.usecasedetail.UseCaseDetail#getPostCondition
     * <em>Post Condition</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Post Condition</em>' attribute.
     * @see #getPostCondition()
     * @generated
     */
    void setPostCondition(String value);

    /**
     * Returns the value of the '<em><b>Basic Flow List</b></em>' containment
     * reference list. The list contents are of type
     * {@link nexcore.tool.uml.model.usecasedetail.FlowObject}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Basic Flow List</em>' containment reference
     * list isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Basic Flow List</em>' containment reference
     *         list.
     * @see nexcore.tool.uml.model.usecasedetail.UseCaseDetailPackage#getUseCaseDetail_BasicFlowList()
     * @model containment="true"
     * @generated
     */
    EList<FlowObject> getBasicFlowList();

    /**
     * Returns the value of the '<em><b>Sub Flow List</b></em>' containment
     * reference list. The list contents are of type
     * {@link nexcore.tool.uml.model.usecasedetail.FlowObject}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Sub Flow List</em>' containment reference list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Sub Flow List</em>' containment reference
     *         list.
     * @see nexcore.tool.uml.model.usecasedetail.UseCaseDetailPackage#getUseCaseDetail_SubFlowList()
     * @model containment="true"
     * @generated
     */
    EList<FlowObject> getSubFlowList();

    /**
     * Returns the value of the '<em><b>Exception Flow List</b></em>'
     * containment reference list. The list contents are of type
     * {@link nexcore.tool.uml.model.usecasedetail.FlowObject}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Exception Flow List</em>' containment
     * reference list isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Exception Flow List</em>' containment
     *         reference list.
     * @see nexcore.tool.uml.model.usecasedetail.UseCaseDetailPackage#getUseCaseDetail_ExceptionFlowList()
     * @model containment="true"
     * @generated
     */
    EList<FlowObject> getExceptionFlowList();

} // UseCaseDetail
