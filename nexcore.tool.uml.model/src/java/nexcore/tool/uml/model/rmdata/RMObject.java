/**
 * Copyright (c) 2015 SK Holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK Holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK Holdings.
 * (http://www.apache.org/licenses/LICENSE-2.0)
 */
package nexcore.tool.uml.model.rmdata;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>RM Object</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link nexcore.tool.uml.model.rmdata.RMObject#getRMObjectId <em>RM Object
 * Id</em>}</li>
 * <li>{@link nexcore.tool.uml.model.rmdata.RMObject#getRMObjectName <em>RM
 * Object Name</em>}</li>
 * <li>{@link nexcore.tool.uml.model.rmdata.RMObject#getParentId <em>Parent Id
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @see nexcore.tool.uml.model.rmdata.RMDataPackage#getRMObject()
 * @model
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.rmdata</li>
 * <li>설  명 : RMObject</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public interface RMObject extends EObject {
    /**
     * Returns the value of the '<em><b>RM Object Id</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>RM Object Id</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>RM Object Id</em>' attribute.
     * @see #setRMObjectId(String)
     * @see nexcore.tool.uml.model.rmdata.RMDataPackage#getRMObject_RMObjectId()
     * @model required="true"
     * @generated
     */
    String getRMObjectId();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.uml.model.rmdata.RMObject#getRMObjectId
     * <em>RM Object Id</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>RM Object Id</em>' attribute.
     * @see #getRMObjectId()
     * @generated
     */
    void setRMObjectId(String value);

    /**
     * Returns the value of the '<em><b>RM Object Name</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>RM Object Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>RM Object Name</em>' attribute.
     * @see #setRMObjectName(String)
     * @see nexcore.tool.uml.model.rmdata.RMDataPackage#getRMObject_RMObjectName()
     * @model required="true"
     * @generated
     */
    String getRMObjectName();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.uml.model.rmdata.RMObject#getRMObjectName
     * <em>RM Object Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>RM Object Name</em>' attribute.
     * @see #getRMObjectName()
     * @generated
     */
    void setRMObjectName(String value);

    /**
     * Returns the value of the '<em><b>Parent Id</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Parent Id</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Parent Id</em>' attribute.
     * @see #setParentId(String)
     * @see nexcore.tool.uml.model.rmdata.RMDataPackage#getRMObject_ParentId()
     * @model
     * @generated
     */
    String getParentId();

    /**
     * Sets the value of the '
     * {@link nexcore.tool.uml.model.rmdata.RMObject#getParentId
     * <em>Parent Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Parent Id</em>' attribute.
     * @see #getParentId()
     * @generated
     */
    void setParentId(String value);

} // RMObject
