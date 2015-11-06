/**
 * Copyright (c) 2015 SK holdings Co., Ltd. All rights reserved.
 * This software is the confidential and proprietary information of SK holdings.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of the license agreement you entered into with SK holdings.
 * (http://www.eclipse.org/legal/epl-v10.html)
 */
package nexcore.tool.uml.model.usecasedisplayId;

import org.eclipse.emf.ecore.EAnnotation;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Use Case Display Id</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link nexcore.tool.uml.model.usecasedisplayId.UseCaseDisplayId#getDisplayId <em>Display Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see nexcore.tool.uml.model.usecasedisplayId.UsecasedisplayIdPackage#getUseCaseDisplayId()
 * @model
 * @generated
 */
/**
 * <ul>
 * <li>업무 그룹명 : nexcore.tool.uml.model</li>
 * <li>서브 업무명 : nexcore.tool.uml.model.usecasedisplayId</li>
 * <li>설  명 : UseCaseDisplayId</li>
 * <li>작성일 : 2015. 10. 6.</li>
 * <li>작성자 : 탁희수 </li>
 * </ul>
 */
public interface UseCaseDisplayId extends EAnnotation {
    /**
     * Returns the value of the '<em><b>Display Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Display Id</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Display Id</em>' attribute.
     * @see #setDisplayId(String)
     * @see nexcore.tool.uml.model.usecasedisplayId.UsecasedisplayIdPackage#getUseCaseDisplayId_DisplayId()
     * @model
     * @generated
     */
    String getDisplayId();

    /**
     * Sets the value of the '{@link nexcore.tool.uml.model.usecasedisplayId.UseCaseDisplayId#getDisplayId <em>Display Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Display Id</em>' attribute.
     * @see #getDisplayId()
     * @generated
     */
    void setDisplayId(String value);

} // UseCaseDisplayId
