/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.model.usecasedetail;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Flow Object</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link nexcore.tool.uml.model.usecasedetail.FlowObject#getFlowId <em>Flow
 * Id</em>}</li>
 * <li>{@link nexcore.tool.uml.model.usecasedetail.FlowObject#getFlowName <em>
 * Flow Name</em>}</li>
 * <li>{@link nexcore.tool.uml.model.usecasedetail.FlowObject#getFlowOverview
 * <em>Flow Overview</em>}</li>
 * <li>
 * {@link nexcore.tool.uml.model.usecasedetail.FlowObject#getFlowDescription
 * <em>Flow Description</em>}</li>
 * </ul>
 * </p>
 * 
 * @see nexcore.tool.uml.model.usecasedetail.UseCaseDetailPackage#getFlowObject()
 * @model
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.usecasedetail</li>
 * <li>설  명 : FlowObject</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public interface FlowObject extends EObject {
    /**
     * Returns the value of the '<em><b>Flow Id</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Flow Id</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Flow Id</em>' attribute.
     * @see #setFlowId(String)
     * @see nexcore.tool.uml.model.usecasedetail.UseCaseDetailPackage#getFlowObject_FlowId()
     * @model required="true"
     * @generated
     */
    String getFlowId();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.uml.model.usecasedetail.FlowObject#getFlowId
     * <em>Flow Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Flow Id</em>' attribute.
     * @see #getFlowId()
     * @generated
     */
    void setFlowId(String value);

    /**
     * Returns the value of the '<em><b>Flow Name</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Flow Name</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Flow Name</em>' attribute.
     * @see #setFlowName(String)
     * @see nexcore.tool.uml.model.usecasedetail.UseCaseDetailPackage#getFlowObject_FlowName()
     * @model required="true"
     * @generated
     */
    String getFlowName();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.uml.model.usecasedetail.FlowObject#getFlowName
     * <em>Flow Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Flow Name</em>' attribute.
     * @see #getFlowName()
     * @generated
     */
    void setFlowName(String value);

    /**
     * Returns the value of the '<em><b>Flow Overview</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Flow Overview</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Flow Overview</em>' attribute.
     * @see #setFlowOverview(String)
     * @see nexcore.tool.uml.model.usecasedetail.UseCaseDetailPackage#getFlowObject_FlowOverview()
     * @model
     * @generated
     */
    String getFlowOverview();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.uml.model.usecasedetail.FlowObject#getFlowOverview
     * <em>Flow Overview</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Flow Overview</em>' attribute.
     * @see #getFlowOverview()
     * @generated
     */
    void setFlowOverview(String value);

    /**
     * Returns the value of the '<em><b>Flow Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Flow Description</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Flow Description</em>' attribute.
     * @see #setFlowDescription(String)
     * @see nexcore.tool.uml.model.usecasedetail.UseCaseDetailPackage#getFlowObject_FlowDescription()
     * @model
     * @generated
     */
    String getFlowDescription();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.uml.model.usecasedetail.FlowObject#getFlowDescription
     * <em>Flow Description</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Flow Description</em>' attribute.
     * @see #getFlowDescription()
     * @generated
     */
    void setFlowDescription(String value);

} // FlowObject
